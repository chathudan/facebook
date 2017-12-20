package com.facebook.chathudan.data.network.usecase;

import com.facebook.chathudan.data.model.FriendsListResponse;
import com.facebook.chathudan.data.network.FacebookAPI;
import com.facebook.chathudan.data.network.service.FriendList;
import retrofit2.Call;
import retrofit2.Callback;

public class GetFriends {

  /**
   * This function will return friends list
   *
   * @param userId facebook user id
   * @param accessToken the facebook access token which returns after successful authentication
   * @param limit number of facebook friends for each request
   * @param afterPage pagination page number
   * @param friendsListCallback FriendListResponse retrofit callback
   */
  public void getFriendsList(String userId, String accessToken, int limit, String afterPage,
      Callback<FriendsListResponse> friendsListCallback) {
    FriendList facebookListService = FacebookAPI.getService().create(FriendList.class);
    Call<FriendsListResponse> call =
        facebookListService.getFriendsList(userId, accessToken, limit, afterPage);
    call.enqueue(friendsListCallback);
  }
}
