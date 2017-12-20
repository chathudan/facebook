package com.facebook.chathudan.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.chathudan.R;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import static com.facebook.chathudan.Constants.FACBOOK_PERMISSONS;

public class LoginActivity extends AppCompatActivity implements LoginView {

  @BindView(R.id.textView) TextView txtToken;
  @BindView(R.id.button_login) LoginButton btnLogin;
  @BindView(R.id.button_show_friends) Button btnShowFriends;

  private CallbackManager mCallbackManager;
  private LoginPresenter mLoginPresenter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);

    mLoginPresenter = new LoginPresenter(this);
  }

  @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    mCallbackManager.onActivityResult(requestCode, resultCode, data);
    super.onActivityResult(requestCode, resultCode, data);
  }

  @Override public void initialiseFacebook() {

    mCallbackManager = CallbackManager.Factory.create();
    // Request permission from Facebook users
    btnLogin.setReadPermissions(FACBOOK_PERMISSONS);
    // Facebook Callback registration
    btnLogin.registerCallback(mCallbackManager, facebookCallback);
  }

  @Override public void showFriends() {
  }

  @Override public void updateAuthSuccess(boolean success) {
    if (success) {
      btnShowFriends.setVisibility(View.VISIBLE);
    } else {
      btnShowFriends.setVisibility(View.GONE);
    }
  }

  FacebookCallback facebookCallback = new FacebookCallback<LoginResult>() {
    @Override public void onSuccess(final LoginResult loginResult) {
      //TODO handle LoginResult
      mLoginPresenter.onAuthSuccess(true);
    }

    @Override public void onCancel() {
      mLoginPresenter.onAuthSuccess(false);
    }

    @Override public void onError(FacebookException exception) {
      //TODO handle exception and notify to the user
      mLoginPresenter.onAuthSuccess(false);
    }
  };

  @OnClick(R.id.button_show_friends) public void onViewClicked() {
    showFriends();
  }
}
