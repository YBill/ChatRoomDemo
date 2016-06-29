package youyun.com.chatroomdemo.chat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ioyouyun.wchat.WeimiInstance;

import youyun.com.chatroomdemo.R;

public class ChatActivity extends AppCompatActivity implements ChatView, View.OnClickListener {

    private EditText inputEdit, sendEdit;
    private Button createChatRoomBtn, enterChatRoomBtn, exitChatRoomBtn, roomListBtn, sendBtn;
    private TextView logText;
    private Button userListBtn;
    private Button clearBtn;
    private Button noGagBtn;
    private Button gagBtn;
    private Button historyBtn;
    private ChatPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initView();
        addListener();
        presenter = new ChatPresenter(this, this);
        setTitle(WeimiInstance.getInstance().getUID());
    }

    private void addListener() {
        createChatRoomBtn.setOnClickListener(this);
        enterChatRoomBtn.setOnClickListener(this);
        exitChatRoomBtn.setOnClickListener(this);
        roomListBtn.setOnClickListener(this);
        sendBtn.setOnClickListener(this);
        userListBtn.setOnClickListener(this);
        clearBtn.setOnClickListener(this);
        gagBtn.setOnClickListener(this);
        noGagBtn.setOnClickListener(this);
        historyBtn.setOnClickListener(this);
    }

    private void initView() {
        sendEdit = (EditText) findViewById(R.id.et_text);
        inputEdit = (EditText) findViewById(R.id.et_input);
        createChatRoomBtn = (Button) findViewById(R.id.btn_create_room);
        enterChatRoomBtn = (Button) findViewById(R.id.btn_enter_room);
        exitChatRoomBtn = (Button) findViewById(R.id.btn_exit_room);
        roomListBtn = (Button) findViewById(R.id.btn_get_room_list);
        sendBtn = (Button) findViewById(R.id.btn_send);
        userListBtn = (Button) findViewById(R.id.btn_room_list);
        clearBtn = (Button) findViewById(R.id.btn_clear_log);
        gagBtn = (Button) findViewById(R.id.btn_gag);
        noGagBtn = (Button) findViewById(R.id.btn_no_gag);
        historyBtn = (Button) findViewById(R.id.btn_history);
        logText = (TextView) findViewById(R.id.tv_log);
        logText.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_create_room:
                String roomName = inputEdit.getText().toString();
                if(!TextUtils.isEmpty(roomName))
                    presenter.createChatRoom(roomName);
                break;
            case R.id.btn_enter_room:
                String roomId = inputEdit.getText().toString();
                if(!TextUtils.isEmpty(roomId))
                    presenter.enterChatRoom(roomId);
                break;
            case R.id.btn_exit_room:
                String roomId2 = inputEdit.getText().toString();
                if(!TextUtils.isEmpty(roomId2))
                    presenter.exitChatRoom(roomId2);
                break;
            case R.id.btn_send:
                String roomId3 = inputEdit.getText().toString();
                String text = sendEdit.getText().toString();
                if(!TextUtils.isEmpty(roomId3) && !TextUtils.isEmpty(text))
                    presenter.sendText(roomId3, text);
                break;
            case R.id.btn_get_room_list:
                String userId = inputEdit.getText().toString();
                if(!TextUtils.isEmpty(userId))
                    presenter.getRoomList(userId);
                break;
            case R.id.btn_room_list:
                String roomId4 = inputEdit.getText().toString();
                if(!TextUtils.isEmpty(roomId4))
                    presenter.getUserList(roomId4);
                break;
            case R.id.btn_clear_log:
                logText.setText("");
                break;
            case R.id.btn_gag:
                String roomId5 = inputEdit.getText().toString();
                String uids = sendEdit.getText().toString();
                if(!TextUtils.isEmpty(roomId5) && !TextUtils.isEmpty(uids))
                    presenter.gagUsers(uids, true, roomId5);
                break;
            case R.id.btn_no_gag:
                String roomId6 = inputEdit.getText().toString();
                String uids2 = sendEdit.getText().toString();
                if(!TextUtils.isEmpty(roomId6) && !TextUtils.isEmpty(uids2))
                    presenter.gagUsers(uids2, false, roomId6);
                break;
            case R.id.btn_history:
                String uid = inputEdit.getText().toString();
                if(!TextUtils.isEmpty(uid))
                    presenter.getHistory(uid, System.currentTimeMillis()/1000, 10);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        presenter.onDestory();
    }

    @Override
    public void showText(String text) {
        logText.append(text + "\n");
    }
}
