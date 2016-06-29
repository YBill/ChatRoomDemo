package youyun.com.chatroomdemo.chat.biz;

import com.ioyouyun.wchat.message.HistoryMessage;

import java.util.List;

/**
 * Created by Bill on 2016/6/24.
 */
public interface OnChatRequestListener {

    void onSuccess(String response);

    void onSuccess(List<HistoryMessage> list);

    void onFaild();
}
