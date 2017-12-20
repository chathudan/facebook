package com.facebook.chathudan.friends;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.facebook.chathudan.R;
import com.facebook.chathudan.data.model.Friend;
import java.util.ArrayList;

public class FriendListActivity extends AppCompatActivity implements FriendsListView {

  @BindView(R.id.recycle_friends_list) RecyclerView recycleFriendsList;
  @BindView(R.id.swipe_container) SwipeRefreshLayout swipeContainer;
  private ArrayList<Friend> mFriendsList = new ArrayList<Friend>();
  private FriendsListPresenter mPresenter;
  private FriendsAdapter mFriendsAdapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_friend_list);
    ButterKnife.bind(this);

    mPresenter = new FriendsListPresenter(this);
    mPresenter.onGetFBFriendsList();
  }

  @Override public void initialise() {
    swipeContainer.setRefreshing(true);


    final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
    recycleFriendsList.setLayoutManager(linearLayoutManager);

    mFriendsAdapter = new FriendsAdapter(mFriendsList);
    recycleFriendsList.setAdapter(mFriendsAdapter);

    recycleFriendsList.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override
      public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int totalItemsCount = linearLayoutManager.getItemCount();
        int visibleItemsCount = recycleFriendsList.getChildCount();
        int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

        mPresenter.onLoadMore(totalItemsCount, visibleItemsCount, firstVisibleItemPosition);
      }
    });
  }

  @Override public void loadFriends(ArrayList<Friend> friendsList) {
    mFriendsList.removeAll(friendsList);
    mFriendsList.addAll(friendsList);
    swipeContainer.setRefreshing(false);

    if ((friendsList != null) && (friendsList.size() > 0)) {
      recycleFriendsList.setVisibility(View.VISIBLE);
      mFriendsAdapter.notifyDataSetChanged();
    } else {
      recycleFriendsList.setVisibility(View.GONE);
    }
  }

  @Override public void showError() {

  }
}
