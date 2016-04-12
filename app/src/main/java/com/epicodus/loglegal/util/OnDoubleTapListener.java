package com.epicodus.loglegal.util;


import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class OnDoubleTapListener implements View.OnTouchListener {
    private GestureDetector gestureDetector;

    public OnDoubleTapListener(Context context) {
        gestureDetector = new GestureDetector(context, new GestureListener());
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return gestureDetector.onTouchEvent(motionEvent);
    }

    private final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent motionEvent) {
            return true;
        }

        @Override
        public boolean onDoubleTap(MotionEvent motionEvent) {
            OnDoubleTapListener.this.onDoubleTap(motionEvent);
            return super.onDoubleTap(motionEvent);
        }
    }

    public void onDoubleTap(MotionEvent motionEvent) {
        // To be overridden when implementing listener
    }
}
