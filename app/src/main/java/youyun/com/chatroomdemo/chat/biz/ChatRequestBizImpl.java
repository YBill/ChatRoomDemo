package youyun.com.chatroomdemo.chat.biz;

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
    public void sendText(String roomId, String text) {
        try {
            String msgId = Util.genLocalMsgId();
            WeimiInstance.getInstance().sendText(msgId, roomId, text, ConvType.room, null, 120);
        } catch (WChatException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createChatRoom(String name, final OnChatRequestListener listener) {
        WeimiInstance.getInstance().shortCreateRoom(name, "3", new HttpCallback() {
            @Override
            public void onResponse(String s) {
                if(listener != null){
                    listener.onSuccess(s);
                }
            }

            @Override
            public void onResponseHistory(List<HistoryMessage> list) {

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
    public void enterChatRoom(String roomId, final OnChatRequestListener listener) {
        WeimiInstance.getInstance().shortEnterRoom(roomId, new HttpCallback() {
            @Override
            public void onResponse(String s) {
                if(listener != null){
                    listener.onSuccess(s);
                }
            }

            @Override
            public void onResponseHistory(List<HistoryMessage> list) {

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
    public void exitChatRoom(String roomId, final OnChatRequestListener listener) {
        WeimiInstance.getInstance().shortExitRoom(roomId, new HttpCallback() {
            @Override
            public void onResponse(String s) {
                if(listener != null){
                    listener.onSuccess(s);
                }
            }

            @Override
            public void onResponseHistory(List<HistoryMessage> list) {

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
            public void onResponseHistory(List<HistoryMessage> list) {

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
            public void onResponseHistory(List<HistoryMessage> list) {

            }

            @Override
            public void onError(Exception e) {
                if(listener != null){
                    listener.onFaild();
                }
            }
        }, 120);
    }
}
