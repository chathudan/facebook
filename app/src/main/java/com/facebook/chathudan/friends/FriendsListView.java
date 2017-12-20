package com.facebook.chathudan.friends;

import com.facebook.chathudan.data.model.Friend;
import java.util.ArrayList;

/**
 * Created by Chathura Wijesinghe <cdanasiri@gmail.com> on 12/20/17.
 */

public interface FriendsListView {

  void initialise();

  void loadFriends(ArrayList<Friend> friendsList);

  void showError();
}
