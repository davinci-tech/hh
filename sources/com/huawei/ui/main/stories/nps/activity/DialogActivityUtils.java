package com.huawei.ui.main.stories.nps.activity;

import android.view.View;

/* loaded from: classes7.dex */
public class DialogActivityUtils {
    private static final Object LOCK = new Object();
    private static DialogActivityUtils sInstance;
    private String mMessage;
    private View.OnClickListener mNegativeListener;
    private String mNegativeText;
    private View.OnClickListener mPositionListener;
    private String mPositionText;
    private String mTitle;

    private DialogActivityUtils() {
    }

    public static DialogActivityUtils getInstance() {
        DialogActivityUtils dialogActivityUtils;
        synchronized (LOCK) {
            if (sInstance == null) {
                sInstance = new DialogActivityUtils();
            }
            dialogActivityUtils = sInstance;
        }
        return dialogActivityUtils;
    }

    public void setTitle(String str) {
        this.mTitle = str;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public void setMessage(String str) {
        this.mMessage = str;
    }

    public String getMessage() {
        return this.mMessage;
    }

    public void setPositiveButton(String str, View.OnClickListener onClickListener) {
        this.mPositionText = str;
        this.mPositionListener = onClickListener;
    }

    public String getPositionText() {
        return this.mPositionText;
    }

    public View.OnClickListener getPositionListener() {
        return this.mPositionListener;
    }

    public void setNegativeButton(String str, View.OnClickListener onClickListener) {
        this.mNegativeText = str;
        this.mNegativeListener = onClickListener;
    }

    public String getNegativeText() {
        return this.mNegativeText;
    }

    public View.OnClickListener getNegativeListener() {
        return this.mNegativeListener;
    }
}
