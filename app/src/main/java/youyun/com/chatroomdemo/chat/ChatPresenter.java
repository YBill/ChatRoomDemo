package youyun.com.chatroomdemo.chat;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.ioyouyun.wchat.message.HistoryMessage;
import com.ioyouyun.wchat.message.NoticeType;
import com.ioyouyun.wchat.message.TextMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import youyun.com.chatroomdemo.ReceiveMsgRunnable;
import youyun.com.chatroomdemo.chat.biz.ChatRequestBiz;
import youyun.com.chatroomdemo.chat.biz.ChatRequestBizImpl;
import youyun.com.chatroomdemo.chat.biz.OnChatRequestListener;

/**
 * Created by Bill on 2016/6/24.
 */
public class ChatPresenter {

    private Activity activity;
    private ChatView chatView;
    private ChatRequestBiz chatRequestBiz;
    private MsgHandler handler;

    public ChatPresenter(ChatView chatView, Activity activity){
        this.chatView = chatView;
        this.activity = activity;
        chatRequestBiz = new ChatRequestBizImpl();
        handler = new MsgHandler(activity);
        ReceiveMsgRunnable runnable = new ReceiveMsgRunnable(activity, handler);
        Thread msgThread = new Thread(runnable);
        msgThread.start();
    }

    /**
     * 发送文本
     * @param roomId
     * @param text
     */
    public void sendText(@NonNull String roomId, @NonNull String text){
        Log.v("Bill", "text:" + text);
        if(text.startsWith("@")){
            String uid = text.substring(text.indexOf("@")+1, text.length());
            Log.v("Bill", "uid:" + uid);
            List list = new ArrayList();
            list.add(uid);
            chatRequestBiz.sendTextAtMsg(roomId, roomId, text, list, new OnChatRequestListener() {
                @Override
                public void onSuccess(String response) {
                    sendMessage(response);
                }

                @Override
                public void onSuccess(List<HistoryMessage> list) {

                }

                @Override
                public void onFaild() {

                }
            });
        }else{
            chatRequestBiz.sendText(roomId, text, new OnChatRequestListener() {
                @Override
                public void onSuccess(String response) {
                    sendMessage(response);
                }

                @Override
                public void onSuccess(List<HistoryMessage> list) {

                }

                @Override
                public void onFaild() {

                }
            });
        }
    }

    /**
     * 创建聊天室
     * @param roomName
     */
    public void createChatRoom(String roomName){
        chatRequestBiz.createChatRoom(roomName, new OnChatRequestListener() {
            @Override
            public void onSuccess(String response) {
                Log.v("Bill", "createRoom:" + response);
                String roomId = resolveCreateJson(response);
                sendMessage(roomId);
            }

            @Override
            public void onSuccess(List<HistoryMessage> list) {

            }

            @Override
            public void onFaild() {

            }
        });
    }

    /**
     * 进入房间
     * @param roomId
     */
    public void enterChatRoom(String roomId){
        chatRequestBiz.enterChatRoom(roomId, roomId, new OnChatRequestListener() {
            @Override
            public void onSuccess(String response) {
                Log.v("Bill", "enterChatRoom:" + response);
                if(resolveJson(response)){
                    sendMessage("加入聊天室成功");
                }
            }

            @Override
            public void onSuccess(List<HistoryMessage> list) {

            }

            @Override
            public void onFaild() {

            }
        });
    }

    /**
     * 退出房间
     * @param roomId
     */
    public void exitChatRoom(String roomId){
        chatRequestBiz.exitChatRoom(roomId, roomId, new OnChatRequestListener() {
            @Override
            public void onSuccess(String response) {
                Log.v("Bill", "exitChatRoom:" + response);
                if(resolveJson(response)){
                    sendMessage("退出聊天室成功");
                }
            }

            @Override
            public void onSuccess(List<HistoryMessage> list) {

            }

            @Override
            public void onFaild() {

            }
        });
    }

    /**
     * 获取房间列表
     * @param uid
     */
    public void getRoomList(String uid){
        chatRequestBiz.getRoomList(uid, new OnChatRequestListener() {
            @Override
            public void onSuccess(String response) {
                sendMessage(response);
            }

            @Override
            public void onSuccess(List<HistoryMessage> list) {

            }

            @Override
            public void onFaild() {

            }
        });
    }

    /**
     * 获取用户列表
     * @param roomId
     */
    public void getUserList(String roomId){
        chatRequestBiz.getRoomUserList(roomId, new OnChatRequestListener() {
            @Override
            public void onSuccess(String response) {
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj == null)
                        return;
                    int apistatus = obj.optInt("apistatus");

                    if(apistatus == 1){
                        JSONObject resultObj = obj.optJSONObject("result");
                        sendMessage(resultObj.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onSuccess(List<HistoryMessage> list) {

            }

            @Override
            public void onFaild() {

            }
        });
    }

    /**
     * 禁言
     * @param uids
     * @param status
     * @param roomId
     */
    public void gagUsers(String uids, boolean status, String roomId){
        chatRequestBiz.getGagUsers(uids, status, roomId, new OnChatRequestListener() {
            @Override
            public void onSuccess(String response) {
                sendMessage(response);
            }

            @Override
            public void onSuccess(List<HistoryMessage> list) {

            }

            @Override
            public void onFaild() {

            }
        });
    }

    /**
     * 获取历史记录
     * @param toUid
     * @param timestamp
     * @param num
     */
    public void getHistory(String toUid, long timestamp, int num){
        chatRequestBiz.getHistory(toUid, timestamp, num, new OnChatRequestListener() {
            @Override
            public void onSuccess(String response) {

            }

            @Override
            public void onSuccess(List<HistoryMessage> list) {
                if(list == null)
                    return;
                for (int i = 0; i < list.size(); i++) {
                    HistoryMessage historyMessage = list.get(i);
                    if(NoticeType.textmessage == historyMessage.type){
                        if(historyMessage.message != null){
                            TextMessage textMessage = (TextMessage) historyMessage.message;
                            sendMessage("history:" + textMessage.text);
                        }
                    }
                }
            }

            @Override
            public void onFaild() {

            }
        });
    }

    /**
     * handler
     * @param msg
     */
    private void sendMessage(String msg){
        Message message = handler.obtainMessage();
        message.obj = msg;
        handler.sendMessage(message);
    }

    private void show(String msg){
        if(chatView != null)
            chatView.showText(msg);
    }

    /**
     * 解析样式：{"apistatus": 1,"result": true}
     * @param json
     * @return
     */
    private boolean resolveJson(String json){
        boolean result = false;
        try {
            JSONObject obj = new JSONObject(json);
            if(obj == null)
                return false;
            int apistatus = obj.optInt("apistatus");
            if(apistatus == 1){
                result = obj.optBoolean("result");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 解析创建房间Json
     * @param json
     * @return roomId
     */
    private String resolveCreateJson(String json){
        String gid = "";
        try {
            JSONObject obj = new JSONObject(json);
            if(obj == null)
                return "";
            int apistatus = obj.optInt("apistatus");
            if(apistatus == 1){
                JSONObject resultObj = obj.optJSONObject("result");
                if(resultObj != null)
                    gid = resultObj.optString("gid");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return gid;
    }

    public void onDestory(){
        chatView = null;
    }

    class MsgHandler extends Handler{

        private WeakReference<Activity> weakReference;

        public MsgHandler(Activity activity){
            weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(weakReference.get() == null || msg == null)
                return;

            show(msg.obj.toString());

        }
    }

}
