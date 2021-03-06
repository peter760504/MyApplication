package com.example.peter.myapplication.log;

import com.daimajia.swipe.SwipeLayout;
import com.example.peter.myapplication.data.TargetEntity;

/**
 * Created by peter on 2016/4/11.
 */
public interface LogSwipeAdapterCallback {
    void onMethodCallback(TargetEntity targetEntity);
    void removeItemOnMethodCallback(TargetEntity targetEntity);

    void onSwipeLayoutStartOpenCallback(SwipeLayout swipeLayout);
    void onSwipeLayoutOpenCallback(SwipeLayout swipeLayout);
    void onSwipeLayoutCloseCallback(SwipeLayout swipeLayout);
    void onSwipeLayoutStartCloseCallback(SwipeLayout swipeLayout);


}
