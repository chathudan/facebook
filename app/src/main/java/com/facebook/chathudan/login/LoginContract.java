package com.facebook.chathudan.login;

import com.facebook.chathudan.BasePresenter;
import com.facebook.chathudan.BaseView;

/**
 * Created by Chathura Wijesinghe <cdanasiri@gmail.com> on 12/18/17.
 */

public interface LoginContract {

  interface View extends BaseView<Presenter> {
    boolean isLogin();
  }

  interface Presenter extends BasePresenter {

    void initialise();

    void checkToken();

    void login();

  }

}
