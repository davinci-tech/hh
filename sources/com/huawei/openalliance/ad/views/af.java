package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.health.R;

/* loaded from: classes9.dex */
public class af extends AutoScaleSizeRelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f8031a;

    public ImageView getCloseBtn() {
        return this.f8031a;
    }

    private void a(Context context) {
        ((RelativeLayout) LayoutInflater.from(context).inflate(R.layout.hiad_splash_icon_close_view, this)).setBackgroundColor(0);
        this.f8031a = (ImageView) findViewById(R.id.icon_close);
    }

    public af(Context context) {
        super(context);
        a(context);
    }
}
