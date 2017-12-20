package com.facebook.chathudan.friends;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.chathudan.R;

/**
 * Created by Chathura Wijesinghe <cdanasiri@gmail.com> on 12/20/17.
 */

public class FriendsAdaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return null;
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

  }

  @Override public int getItemCount() {
    return 0;
  }


  public class FriendViewHolder extends RecyclerView.ViewHolder {
    public TextView name;
    public ImageView avatar;

    public FriendViewHolder(View view) {
      super(view);
      name = (TextView) view.findViewById(R.id.title);
      avatar = (ImageView) view.findViewById(R.id.avatar);
    }
  }
}
