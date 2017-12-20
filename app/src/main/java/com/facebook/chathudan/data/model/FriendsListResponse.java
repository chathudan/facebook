package com.facebook.chathudan.data.model;

import java.util.ArrayList;

/**
 * Created by Chathura Wijesinghe <cdanasiri@gmail.com> on 12/20/17.
 */

public class FriendsListResponse {
  private ArrayList<Friend> friendList;
  private String nextPageId;
  private String previousPageId;

  public ArrayList<Friend> getFriendList() {
    return friendList;
  }

  public void setFriendList(ArrayList<Friend> friendList) {
    this.friendList = friendList;
  }

  public String getNextPageId() {
    return nextPageId;
  }

  public void setNextPageId(String nextPageId) {
    this.nextPageId = nextPageId;
  }

  public String getPreviousPageId() {
    return previousPageId;
  }

  public void setPreviousPageId(String previousPageId) {
    this.previousPageId = previousPageId;
  }

}
