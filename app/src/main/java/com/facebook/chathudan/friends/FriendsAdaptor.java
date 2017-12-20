package com.facebook.chathudan.friends;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.facebook.chathudan.R;
import com.facebook.chathudan.data.model.Friend;
import com.facebook.chathudan.data.network.service.FriendList;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

/**
 * Facebook friends adaptor
 *
 * @author Chathura Wijesinghe (cdanasiri@gmail.com)
 */


public class FriendsAdaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

  private final int TYPE_FRIEND = 0;

  private final ArrayList<Friend> mFriendList;
  private Context mContext;

  public FriendsAdaptor(ArrayList<Friend> friendsList) {
    this.mFriendList = friendsList;
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    mContext = parent.getContext();
    LayoutInflater inflater = LayoutInflater.from(mContext);
    //TODO add loading for pagination / friends limit
    //if (viewType == TYPE_FRIEND) {
    return new FriendViewHolder(inflater.inflate(R.layout.item_friend, parent, false));
    //}
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    FriendViewHolder friendHolder = ((FriendViewHolder) holder);
    String friendName = mFriendList.get(position).getName();
    friendHolder.name.setText(position + "  " + friendName);
    String avatarUrl = mFriendList.get(position).getPicture();
    if (avatarUrl != null) {
      if (avatarUrl.trim().length() > 0) {
        Picasso.with(mContext).load(avatarUrl).into(friendHolder.avatar);
      }
    }

    Animation anim = AnimationUtils.loadAnimation(mContext, android.R.anim.fade_in);
    friendHolder.name.setAnimation(anim);
  }

  @Override public int getItemCount() {
    return mFriendList.size();
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
