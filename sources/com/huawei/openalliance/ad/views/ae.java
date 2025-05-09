package com.huawei.openalliance.ad.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.openalliance.ad.utils.ao;

/* loaded from: classes9.dex */
public class ae extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private AutoScaleSizeRelativeLayout f8030a;
    private CornerImageView b;

    public AutoScaleSizeRelativeLayout getRoundLayout() {
        return this.f8030a;
    }

    public CornerImageView getIcon() {
        return this.b;
    }

    private void a(Context context) {
        ((RelativeLayout) LayoutInflater.from(context).inflate(R.layout.hiad_dest_icon_view, this)).setBackgroundColor(0);
        AutoScaleSizeRelativeLayout autoScaleSizeRelativeLayout = (AutoScaleSizeRelativeLayout) findViewById(R.id.hiad_round_layout);
        this.f8030a = autoScaleSizeRelativeLayout;
        autoScaleSizeRelativeLayout.setBackgroundColor(-1);
        this.f8030a.setRectCornerRadius(ao.a(context, 8.0f));
        CornerImageView cornerImageView = (CornerImageView) findViewById(R.id.hiad_dest_icon);
        this.b = cornerImageView;
        cornerImageView.setRectCornerRadius(ao.a(context, 6.0f));
    }

    public ae(Context context) {
        super(context);
        a(context);
    }
}
