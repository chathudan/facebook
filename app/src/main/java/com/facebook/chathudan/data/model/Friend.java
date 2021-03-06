package com.facebook.chathudan.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The Friend model which keep facebook friend's details
 *
 * @author Chathura Wijesinghe (cdanasiri@gmail.com)
 */
public class Friend {
  @SerializedName("id") @Expose private String id;
  @SerializedName("name") @Expose private String name;
  @SerializedName("picture") @Expose private String picture;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }
}
