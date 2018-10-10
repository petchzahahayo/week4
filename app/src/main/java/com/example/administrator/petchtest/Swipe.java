package com.example.administrator.petchtest;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class Swipe implements View.OnTouchListener {

    private final GestureDetector gestureDetector;
    public Swipe(Context context) {
        gestureDetector = new GestureDetector(context, new GestureListener());

    }
    public void onSwipeLeft() {
    }


    public void onSwipeRight() {
    }


    public void onSwipeTop() {
    }

    public void onSwipeBottom() {
    }

    public boolean onTouch(View v, MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }
    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {


        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float distanceX = e2.getX() - e1.getX();
            float distanceY = e2.getY() - e1.getY();
            if (Math.abs(distanceY) < Math.abs(distanceX )) {
                if (e2.getX() > e1.getX()) {
                    onSwipeRight();

                }
                else if(e2.getX() < e1.getX()) {
                    onSwipeLeft();
                }
                return true;
            }
            else if (Math.abs(distanceY) > Math.abs(distanceX )) {
                if (e2.getY() > e1.getY()) {
                    onSwipeBottom();
                }
                else if (e2.getY() < e1.getY()){
                    onSwipeTop();
                }
                return true;
            }
            return true;
        }
    }

}
