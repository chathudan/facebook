package com.facebook.chathudan.friends;

import com.facebook.AccessToken;
import com.facebook.chathudan.data.model.Friend;
import com.facebook.chathudan.data.model.FriendsListResponse;
import com.facebook.chathudan.data.network.usecase.GetFriends;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.facebook.chathudan.Constants.PAGE_SIZE;

/**
 * Created by Chathura Wijesinghe <cdanasiri@gmail.com> on 12/20/17.
 */

public class FriendsListPresenter {
  FriendsListView mFriendsListView;
  private AccessToken mToken;
  private String userId;
  private String nextPageId;

  private GetFriends mGetFriendsUseCase = new GetFriends();
  private ArrayList<Friend> mFriendsList = new ArrayList<Friend>();
  private boolean isLoading;

  public FriendsListPresenter(FriendsListView friendsListView) {
    this.mFriendsListView = friendsListView;
    start();
  }

  public void start() {
    initialise();
  }

  private void initialise() {
    mFriendsListView.initialise();
  }

  public void onGetFBFriendsList() {
    mToken = AccessToken.getCurrentAccessToken();
    if(mToken != null) {
      userId = mToken.getUserId();
      mGetFriendsUseCase.getFriendsList(userId, mToken.getToken(), PAGE_SIZE, nextPageId, mFriendsListResponseCallback);
    }else {
      mFriendsListView.showError();
    }
  }

  private final Callback<FriendsListResponse> mFriendsListResponseCallback = new Callback<FriendsListResponse>() {
    @Override
    public void onResponse(Call<FriendsListResponse> call, Response<FriendsListResponse> response) {
      isLoading = false;
      if (response.isSuccessful()){
        FriendsListResponse listResponse = response.body();

        ArrayList<Friend> newFriends = listResponse.getFriendList();

        // To set correct insert index
        int index = 0;
        if (mFriendsList.size() > 0) {
          index = mFriendsList.size() - 1;
        }
        mFriendsList.addAll(index, newFriends);

        if ((nextPageId != null)) {
          // remove all friends for loading  friends
          if (index == 0) {
            mFriendsList.add(null);
          }
        } else {
          // remove friends from loading
          index = mFriendsList.size() - 1;
          if (mFriendsList.get(index) == null) {
            mFriendsList.remove(index);
          }
        }

        mFriendsListView.loadFriends(mFriendsList);
      }else {
      //  TODO handle me , response isn't success
      }
    }

    @Override public void onFailure(Call<FriendsListResponse> call, Throwable t) {
      isLoading = false;
    //  TODO handle me
    }
  };

  public void onLoadMore(int totalItemsCount, int visibleItemsCount, int firstVisibleItemPosition) {
    if ((nextPageId != null) && !isLoading && (visibleItemsCount + firstVisibleItemPosition >= totalItemsCount)) {
      // Load more friends
      isLoading = true;
      mGetFriendsUseCase.getFriendsList(userId, mToken.getToken(), PAGE_SIZE, nextPageId, mFriendsListResponseCallback);
    }
  }
}
