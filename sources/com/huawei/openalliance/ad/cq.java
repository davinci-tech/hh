package com.huawei.openalliance.ad;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowManager;

/* loaded from: classes5.dex */
public interface cq {
    int a(Context context);

    int a(ApplicationInfo applicationInfo);

    Rect a(WindowInsets windowInsets);

    com.huawei.openalliance.ad.utils.bp a();

    void a(ActionBar actionBar, boolean z, Drawable drawable, View.OnClickListener onClickListener);

    void a(WindowManager.LayoutParams layoutParams);

    boolean a(Activity activity);

    boolean b();

    int c();

    boolean d();

    String e();

    String f();

    boolean g();
}
