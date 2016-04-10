package com.epicodus.loglegal.util;

public interface ItemTouchHelperAdapter {
    // Maybe don't need? Check after implementation
    boolean onItemMove(int fromPosition, int toPosition);

    // probably need
    void onItemDismiss(int position);
}
