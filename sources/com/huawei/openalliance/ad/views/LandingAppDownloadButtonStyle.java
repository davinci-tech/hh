package com.huawei.openalliance.ad.views;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.openalliance.ad.utils.dk;

/* loaded from: classes9.dex */
public class LandingAppDownloadButtonStyle extends AppDownloadButtonStyle {
    public LandingAppDownloadButtonStyle(Context context) {
        super(context);
        this.normalStyle.setBackground(context.getResources().getDrawable(R.drawable._2131428563_res_0x7f0b04d3));
        this.normalStyle.setTextColor(context.getResources().getColor(R.color._2131297960_res_0x7f0906a8));
        this.processingStyle.setBackground(dk.b(context, R.drawable._2131428494_res_0x7f0b048e));
        this.processingStyle.setTextColor(context.getResources().getColor(R.color._2131297917_res_0x7f09067d));
    }
}
