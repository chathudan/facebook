package com.facebook.chathudan.login;

import android.support.annotation.NonNull;
import com.facebook.AccessToken;


public class LoginPresenter {

  private final LoginView mLoginView;

  public LoginPresenter(@NonNull LoginView loginView) {
    this.mLoginView = loginView;
    start();
  }

  public void start() {
    initialise();
  }

  public void initialise() {
    mLoginView.initialiseFacebook();
    checkToken();
  }

  public void checkToken() {
    AccessToken accessToken = AccessToken.getCurrentAccessToken();
    if (accessToken != null) {
    //  TODO update auth related stuff here
      onAuthSuccess(true);
    }
  }

  public void onAuthSuccess(boolean success){
    mLoginView.updateAuthSuccess(success);
  }
}
