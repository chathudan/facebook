package com.facebook.chathudan.login;

import android.support.annotation.NonNull;
import com.facebook.AccessToken;

/**
 * Created by Chathura Wijesinghe <cdanasiri@gmail.com> on 12/18/17.
 */

public class LoginPrecenter implements LoginContract.Presenter{

  private final LoginContract.View mLoginView;

  public LoginPrecenter(@NonNull LoginContract.View loginView){
    this.mLoginView = loginView;

    mLoginView.setPresenter(this);
  }

  @Override public void start() {
    initialise();
  }

  @Override public void initialise() {
    checkToken();
  }

  @Override public void checkToken() {
    AccessToken accessToken = AccessToken.getCurrentAccessToken();
    if (accessToken!=null){

    }
  }

  @Override public void login() {

  }
}
