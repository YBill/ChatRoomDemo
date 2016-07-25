package youyun.com.chatroomdemo.chat.biz;

import android.util.Log;

import com.ioyouyun.wchat.WeimiInstance;
import com.ioyouyun.wchat.message.ConvType;
import com.ioyouyun.wchat.message.HistoryMessage;
import com.ioyouyun.wchat.message.WChatException;
import com.ioyouyun.wchat.util.HttpCallback;

import java.util.List;

import youyun.com.chatroomdemo.Util;

/**
 * Created by Bill on 2016/6/24.
 */
public class ChatRequestBizImpl implements ChatRequestBiz{

    @Override
    public void sendText(String roomId, String text, OnChatRequestListener listener) {
        try {
            String msgId = Util.genLocalMsgId();
            boolean result = WeimiInstance.getInstance().sendText(msgId, roomId, text, ConvType.room, null, 120);
            if(listener != null){
                if(result)
                    listener.onSuccess(text);
                else
                    listener.onFaild();
            }
        } catch (WChatException e) {
            if(listener != null)
                listener.onFaild();
            e.printStackTrace();
        }
    }

    @Override
    public void sendTextAtMsg(String roomId, String text, byte[] padding, OnChatRequestListener listener) {
        try {
            String msgId = Util.genLocalMsgId();
            boolean result = WeimiInstance.getInstance().sendTextExt(msgId, roomId, text, ConvType.room, padding, 120);
            if(listener != null){
                if(result)
                    listener.onSuccess(text);
                else
                    listener.onFaild();
            }
        } catch (WChatException e) {
            if(listener != null)
                listener.onFaild();
            e.printStackTrace();
        }
    }

    @Override
    public void createChatRoom(String name, final OnChatRequestListener listener) {
        WeimiInstance.getInstance().shortCreateRoom(name, new HttpCallback() {
            @Override
            public void onResponse(String s) {
                if(listener != null){
                    listener.onSuccess(s);
                }
            }

            @Override
            public void onResponseHistory(List list) {

            }

            @Override
            public void onError(Exception e) {
                if(listener != null){
                    listener.onFaild();
                }
            }
        }, 120);

    }

    @Override
    public void enterChatRoom(String roomId, String thirdUid, final OnChatRequestListener listener) {
        WeimiInstance.getInstance().shortEnterRoom(roomId, thirdUid, new HttpCallback() {
            @Override
            public void onResponse(String s) {
                if(listener != null){
                    listener.onSuccess(s);
                }
            }

            @Override
            public void onResponseHistory(List list) {

            }

            @Override
            public void onError(Exception e) {
                if(listener != null){
                    listener.onFaild();
                }
            }
        }, 120);
    }

    @Override
    public void exitChatRoom(String roomId, String thirdUid, final OnChatRequestListener listener) {
        WeimiInstance.getInstance().shortExitRoom(roomId, thirdUid, new HttpCallback() {
            @Override
            public void onResponse(String s) {
                if(listener != null){
                    listener.onSuccess(s);
                }
            }

            @Override
            public void onResponseHistory(List list) {

            }

            @Override
            public void onError(Exception e) {
                if(listener != null){
                    listener.onFaild();
                }
            }
        }, 120);
    }

    @Override
    public void getRoomList(String uid, final OnChatRequestListener listener) {
        WeimiInstance.getInstance().shortRoomList(uid, new HttpCallback() {
            @Override
            public void onResponse(String s) {
                if(listener != null){
                    listener.onSuccess(s);
                }
            }

            @Override
            public void onResponseHistory(List list) {

            }

            @Override
            public void onError(Exception e) {
                if(listener != null){
                    listener.onFaild();
                }
            }
        }, 120);
    }

    @Override
    public void getRoomUserList(String roomId, final OnChatRequestListener listener) {
        WeimiInstance.getInstance().shortRoomUserList(roomId, new HttpCallback() {
            @Override
            public void onResponse(String s) {
                if(listener != null){
                    listener.onSuccess(s);
                }
            }

            @Override
            public void onResponseHistory(List list) {

            }

            @Override
            public void onError(Exception e) {
                if(listener != null){
                    listener.onFaild();
                }
            }
        }, 120);
    }

    @Override
    public void getGagUsers(String uids, boolean status, String roomId, long gagTime, final OnChatRequestListener listener) {
        WeimiInstance.getInstance().shortUsersGag(uids, status, roomId, gagTime, new HttpCallback() {
            @Override
            public void onResponse(String result) {
                if(listener != null){
                    listener.onSuccess(result);
                }
            }

            @Override
            public void onResponseHistory(List historyMessage) {

            }

            @Override
            public void onError(Exception e) {
                if(listener != null){
                    listener.onFaild();
                }
            }
        }, 120);
    }

    @Override
    public void getHistory(String toUid, long timestamp, int num, final OnChatRequestListener listener) {
        WeimiInstance.getInstance().shortGetHistoryByTime(toUid, timestamp, num, ConvType.room, new HttpCallback() {
            @Override
            public void onResponse(String result) {
                Log.v("Bill", "getHistory onResponse");
            }

            @Override
            public void onResponseHistory(List historyMessage) {
                Log.v("Bill", "getHistory onResponseHistory:" + historyMessage);
                if(historyMessage != null)
                    Log.v("Bill", "getHistory onResponseHistory size:" + historyMessage.size());
                if(listener != null){
                    listener.onSuccess(historyMessage);
                }
            }

            @Override
            public void onError(Exception e) {
                Log.v("Bill", "getHistory onError");
                if(listener != null){
                    listener.onFaild();
                }
            }
        }, 120);
    }
}
