package com.facebook.chathudan.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.chathudan.R;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

public class LoginActivity extends AppCompatActivity implements LoginView{

  @BindView(R.id.textView) TextView textToken;
  @BindView(R.id.login_button) LoginButton buttonLogin;
  private CallbackManager mCallbackManager;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    mCallbackManager = CallbackManager.Factory.create();

    buttonLogin.setReadPermissions("email", "public_profile", "user_friends");

    // Facebook Callback registration
    buttonLogin.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
      @Override public void onSuccess(final LoginResult loginResult) {
        runOnUiThread(new Runnable() {
          @Override public void run() {
            textToken.setText("Success Login"
                + "\n"
                + "Friend = "
                + loginResult.getAccessToken().getUserId()
                + "\n"
                + "Token = "
                + loginResult.getAccessToken().getToken());
          }
        });

      }

      @Override public void onCancel() {
      // TODO handle facebook authorisation on cancel
      }

      @Override public void onError(FacebookException exception) {
      // TODO handle upon error
      }
    });

    //LoginManager.getInstance()
    //    .registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
    //      @Override public void onSuccess(LoginResult loginResult) {
    //        // App code
    //      }
    //
    //      @Override public void onCancel() {
    //        // App code
    //      }
    //
    //      @Override public void onError(FacebookException exception) {
    //        // App code
    //      }
    //    });
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    mCallbackManager.onActivityResult(requestCode, resultCode, data);
    super.onActivityResult(requestCode, resultCode, data);
  }

  @Override public void initialiseFacebook() {

  }

  @Override public void login() {

  }
}
