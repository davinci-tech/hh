package com.huawei.featureuserprofile.todo.activity;

import android.view.View;

/* loaded from: classes.dex */
public interface OnItemClickListener<T> {
    void onItemClick(View view, int i, T t);

    void onOtherClick();
}
