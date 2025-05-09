package com.huawei.health.suggestion.customizecourse.manager.adapter.interfaceadapter;

/* loaded from: classes4.dex */
public interface ItemEventListener {
    public static final int ITEM_CLICK_TYPE_DEL_ITEM = 1;
    public static final int ITEM_CLICK_TYPE_DEL_MULTI = 2;
    public static final int ITEM_CLICK_TYPE_EDIT_ACTION = 4;
    public static final int ITEM_CLICK_TYPE_EDIT_INTENSITY = 7;
    public static final int ITEM_CLICK_TYPE_EDIT_INTENSITY_VALUE = 8;
    public static final int ITEM_CLICK_TYPE_EDIT_REPEAT = 3;
    public static final int ITEM_CLICK_TYPE_EDIT_TARGET = 5;
    public static final int ITEM_CLICK_TYPE_EDIT_TARGET_VALUE = 6;
    public static final int ITEM_CLICK_TYPE_MULTI_OPEN = 9;
    public static final int ITEM_CLICK_TYPE_NOTIFY_ITEM = 0;
    public static final int ITEM_CLICK_TYPE_REFRESH_ACTION_TOTAL_NUM = 10;

    void onComItemClick(int i, int i2);
}
