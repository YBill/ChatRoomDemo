package youyun.com.chatroomdemo;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.ioyouyun.wchat.message.NoticeType;
import com.ioyouyun.wchat.message.NotifyCenter;
import com.ioyouyun.wchat.message.TextMessage;
import com.ioyouyun.wchat.message.WeimiNotice;

/**
 * Created by Bill on 2016/6/24.
 */
public class ReceiveMsgRunnable implements Runnable {

    private Context context;
    private Handler handler;

    public ReceiveMsgRunnable(Context context, Handler handler) {
        this.context = context.getApplicationContext();
        this.handler = handler;
    }


    @Override
    public void run() {
        WeimiNotice notice;
        while (true) {
            try {
                notice = NotifyCenter.clientNotifyChannel.take();

            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }

            NoticeType type = notice.getNoticeType();
            Log.v("", "noticeType:" + type);
            switch (type) {
                case textmessage:
                    textMessageMethod(notice);
                    break;

            }
        }
    }

    private void textMessageMethod(WeimiNotice notice) {
        TextMessage textMessage = (TextMessage) notice.getObject();
        Log.v("Bill", "text:" + textMessage.text);
        Log.v("Bill", "type:" + textMessage.convType);
        Message message = handler.obtainMessage();
        message.obj = textMessage.text;
        handler.sendMessage(message);

    }

}
