package com.huawei.ui.commonui.base.view;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes9.dex */
public abstract class BindableTextView extends BaseBindableView {
    private static final String TAG = "NoticeBindableView";

    public BindableTextView(Activity activity, View view) {
        super(activity, view);
    }

    public BindableTextView setText(String str) {
        if (this.mRoot instanceof TextView) {
            ((TextView) this.mRoot).setText(str);
        } else {
            LogUtil.h(TAG, "root view is not text view, can not set text");
        }
        return this;
    }

    public BindableTextView setTextColor(int i) {
        if (this.mRoot instanceof TextView) {
            ((TextView) this.mRoot).setTextColor(i);
        } else {
            LogUtil.h(TAG, "root view is not text view, can not set text");
        }
        return this;
    }

    public BindableTextView setClickAble(boolean z) {
        this.mRoot.setClickable(z);
        return this;
    }
}
