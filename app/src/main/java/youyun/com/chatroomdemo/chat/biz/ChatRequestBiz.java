package youyun.com.chatroomdemo.chat.biz;

import java.util.List;

/**
 * Created by Bill on 2016/6/24.
 */
public interface ChatRequestBiz {

    /**
     * 发送消息
     * @param roomId
     * @param text
     * @param listener
     */
    void sendText(String roomId, String text, OnChatRequestListener listener);

    /**
     * @消息
     * @param roomId
     * @param text
     * @param atIdList
     * @param listener
     */
    void sendTextAtMsg(String roomId, String thirdUid, String text, List<String> atIdList, OnChatRequestListener listener);

    /**
     * 创建聊天室
     * @param name 聊天室名称
     * @param listener
     */
    void createChatRoom(String name, OnChatRequestListener listener);

    /**
     * 进入房间
     * @param roomId 房间Id
     * @param thirdUid  第三方uid
     * @param listener
     */
    void enterChatRoom(String roomId, String thirdUid, OnChatRequestListener listener);

    /**
     * 退出房间
     * @param roomId 房间Id
     * thirdUid  第三方uid
     * @param listener
     */
    void exitChatRoom(String roomId, String thirdUid, OnChatRequestListener listener);

    /**
     * 获取群列表
     * @param uid
     * @param listener
     */
    void getRoomList(String uid, OnChatRequestListener listener);

    /**
     * 获取房间用户
     * @param roomId
     * @param listener
     */
    void getRoomUserList(String roomId, OnChatRequestListener listener);

    /**
     * 禁言
     * @param uids
     * @param status
     * @param roomId
     * @param listener
     */
    void getGagUsers(String uids, boolean status, String roomId, OnChatRequestListener listener);

    /**
     * 历史记录
     * @param toUid
     * @param timestamp
     * @param num
     * @param listener
     */
    void getHistory(String toUid, long timestamp, int num, OnChatRequestListener listener);
}
