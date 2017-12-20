package com.facebook.chathudan.data.network.utils;

import com.facebook.chathudan.data.model.Friend;
import com.facebook.chathudan.data.model.FriendsListResponse;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Chathura Wijesinghe <cdanasiri@gmail.com> on 12/20/17.
 */

public class FriendDeserializer implements JsonDeserializer<FriendsListResponse> {
  private final String ID = "id";
  private final String NAME = "name";
  private final String PICTURE = "picture";
  private final String URL = "url";
  private final String AFTER = "after";
  private final String BEFORE = "before";
  private final String DATA = "data";
  private final String PAGING = "paging";
  private final String CURSORS = "cursors";
  private final String NEXT = "next";

  @Override public FriendsListResponse deserialize(JsonElement json, Type typeOfT,
      JsonDeserializationContext context) throws JsonParseException {
    FriendsListResponse response = new FriendsListResponse();
    JsonObject friends = json.getAsJsonObject();
    JsonArray friendsArray = (JsonArray) friends.get(DATA);
    ArrayList<Friend> friendList = new ArrayList<>();
    if (friendsArray != null) {
      for (int i = 0; i < friendsArray.size(); i++) {
        JsonObject itemJsonObject = friendsArray.get(i).getAsJsonObject();
        Friend friend = new Friend();
        friend.setId(itemJsonObject.get(ID).getAsString());
        friend.setName(itemJsonObject.get(NAME).getAsString());
        String picUrl = itemJsonObject.getAsJsonObject(PICTURE).getAsJsonObject(DATA).get(URL).getAsString();
        friend.setPicture(picUrl);
        friendList.add(friend);
      }
      JsonObject pagingObject = friends.getAsJsonObject(PAGING);
      if (pagingObject.has(NEXT)) {
        JsonObject cursorsObject = pagingObject.getAsJsonObject(CURSORS);
        String nextPageId = cursorsObject.get(AFTER).getAsString();
        String previousPageId = cursorsObject.get(BEFORE).getAsString();
        response.setNextPageId(nextPageId);
        response.setPreviousPageId(previousPageId);
      }
      response.setFriendList(friendList);
    }
    return response;
  }
}
