package com.huawei.openalliance.ad;

import android.content.Context;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.huawei.health.R;

/* loaded from: classes9.dex */
public class ti extends tj {
    @Override // com.huawei.openalliance.ad.tj
    protected int getLayoutId() {
        return R.layout.hiad_advertiser_info_dialog;
    }

    @Override // com.huawei.openalliance.ad.tj
    protected void a() {
        this.c = (RelativeLayout) findViewById(R.id.haid_advertiser_info_dialog_root);
        this.k = findViewById(R.id.margin_view);
        this.l = findViewById(R.id.anchor_view);
        this.g = (com.huawei.openalliance.ad.views.h) findViewById(R.id.top_advertiser_view);
        this.m = (ImageView) findViewById(R.id.top_advertiser_iv);
        this.h = (com.huawei.openalliance.ad.views.h) findViewById(R.id.bottom_advertiser_view);
        this.n = (ImageView) findViewById(R.id.bottom_advertiser_iv);
    }

    public ti(Context context, int[] iArr, int[] iArr2) {
        super(context, iArr, iArr2);
    }
}
