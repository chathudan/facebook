package com.facebook.chathudan.data.network.service;

import com.facebook.chathudan.data.model.FriendsListResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Retrofit annotation that fetch taggable friends
 *
 * @author Chathura Wijesinghe (cdanasiri@gmail.com)
 */

public interface FriendList {
  @GET("v2.11/{user_id}/taggable_friends") Call<FriendsListResponse> getFriendsList(
      @Path("user_id") String userId, @Query("access_token") String accessToken,
      @Query("limit") int limit, @Query("after") String afterPage);
}
