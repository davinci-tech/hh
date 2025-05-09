package com.huawei.ui.commonui.base.view;

import android.app.Activity;
import android.view.View;

/* loaded from: classes9.dex */
public abstract class BaseBindableView extends AbstractBindableView {
    protected View mRoot;

    public BaseBindableView(Activity activity, int i) {
        super(activity);
        View findViewById = activity.findViewById(i);
        this.mRoot = findViewById;
        findViewById.setOnClickListener(this);
    }

    public BaseBindableView(Activity activity, View view) {
        super(activity);
        this.mRoot = view;
        view.setOnClickListener(this);
    }

    public View getView() {
        return this.mRoot;
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        View view = this.mRoot;
        if (view != null) {
            view.setVisibility(i);
        }
    }
}
