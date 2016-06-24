package youyun.com.chatroomdemo;

import java.util.UUID;

public class Util {
	
	public final static String MSG_ID_PRE = UUID.randomUUID() + "";
	public static int msg_p = 0;

	public static String genLocalMsgId() {
		msg_p++;
		String msgId = MSG_ID_PRE + msg_p;
		return msgId;
	}
}
