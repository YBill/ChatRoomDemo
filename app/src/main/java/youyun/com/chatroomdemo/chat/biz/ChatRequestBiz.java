package youyun.com.chatroomdemo.chat.biz;

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
     * 创建聊天室
     * @param name 聊天室名称
     * @param listener
     */
    void createChatRoom(String name, OnChatRequestListener listener);

    /**
     * 进入房间
     * @param roomId 房间Id
     * @param listener
     */
    void enterChatRoom(String roomId, OnChatRequestListener listener);

    /**
     * 退出房间
     * @param roomId 房间Id
     * @param listener
     */
    void exitChatRoom(String roomId, OnChatRequestListener listener);

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
}
