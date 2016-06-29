package youyun.com.chatroomdemo;

import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.ioyouyun.wchat.WeimiInstance;
import com.ioyouyun.wchat.data.AuthResultData;
import com.ioyouyun.wchat.message.WChatException;

import java.math.BigInteger;
import java.security.SecureRandom;

import youyun.com.chatroomdemo.chat.ChatActivity;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void handleLogin(View v) {
        login();
    }

    private void login() {
        new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    String clientIdDefault;
                    String clientSecretDefault;
                    String udid = generateOpenUDID();
                    clientIdDefault = "1-20046-8c351702e261c7f607ea8020dcb80f41-android";
                    clientSecretDefault = "db25eb5508619a34c0ce38b63b4b4c0a";
                    final AuthResultData authResultData = WeimiInstance.getInstance().registerApp(
                            getApplicationContext(), udid, clientIdDefault, clientSecretDefault, 30);

                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            if (authResultData.success) {
                                String uid = WeimiInstance.getInstance().getUID();
                                Toast.makeText(LoginActivity.this, "登录成功 userId:" + uid, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, ChatActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });


                } catch (WChatException e) {
                    e.printStackTrace();
                }

            }

        }).start();
    }

    /**
     * 这里用ANDROID_ID当设备唯一Id
     *
     * @return
     */
    private String generateOpenUDID() {
        // Try to get the ANDROID_ID
        String OpenUDID = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        if (OpenUDID == null || OpenUDID.equals("9774d56d682e549c")
                | OpenUDID.length() < 15) {
            // if ANDROID_ID is null, or it's equals to the GalaxyTab generic
            // ANDROID_ID or bad, generates a new one
            final SecureRandom random = new SecureRandom();
            OpenUDID = new BigInteger(64, random).toString(16);
        }
        return OpenUDID;
    }

}
