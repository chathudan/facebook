package com.facebook.chathudan.login;

/**
 * Created by Chathura Wijesinghe <cdanasiri@gmail.com> on 12/18/17.
 */

public interface LoginView {

  void initialiseFacebook();

  void showFriends();

  void updateAuthSuccess(boolean success);
}
