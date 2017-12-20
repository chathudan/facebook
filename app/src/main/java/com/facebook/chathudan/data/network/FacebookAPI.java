package com.facebook.chathudan.data.network;

import com.facebook.chathudan.data.model.FriendsListResponse;
import com.facebook.chathudan.data.network.utils.FriendDeserializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Chathura Wijesinghe <cdanasiri@gmail.com> on 12/20/17.
 */

public class FacebookAPI {
  public static final String BASE_URL = "https://graph.facebook.com/";
  private static Retrofit retrofit = null;

  private static Gson gson =
      new GsonBuilder().registerTypeAdapter(FriendsListResponse.class, new FriendDeserializer())
          .create();

  public static Retrofit getService() {
    retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build();
    return retrofit;
  }
}
