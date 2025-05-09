package com.huawei.openalliance.ad.views;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.openalliance.ad.utils.ao;
import com.huawei.openalliance.ad.utils.dk;

/* loaded from: classes5.dex */
public class ExtandAppDownloadButtonStyle extends AppDownloadButtonStyle {
    public ExtandAppDownloadButtonStyle(Context context) {
        super(context);
        boolean n = ao.n(context);
        this.normalStyle.setBackground(context.getResources().getDrawable(n ? R.drawable._2131428539_res_0x7f0b04bb : R.drawable._2131428538_res_0x7f0b04ba));
        this.normalStyle.setTextColor(context.getResources().getColor(R.color._2131297944_res_0x7f090698));
        this.processingStyle.setBackground(dk.b(context, n ? R.drawable._2131428535_res_0x7f0b04b7 : R.drawable._2131428534_res_0x7f0b04b6));
        this.processingStyle.setTextColor(context.getResources().getColor(R.color._2131297957_res_0x7f0906a5));
        this.installingStyle.setBackground(context.getResources().getDrawable(n ? R.drawable._2131428531_res_0x7f0b04b3 : R.drawable._2131428530_res_0x7f0b04b2));
        this.installingStyle.setTextColor(context.getResources().getColor(R.color._2131297914_res_0x7f09067a));
        this.f7789a.setBackground(context.getResources().getDrawable(n ? R.drawable._2131428568_res_0x7f0b04d8 : R.drawable._2131428567_res_0x7f0b04d7));
        this.f7789a.setTextColor(context.getResources().getColor(R.color._2131297960_res_0x7f0906a8));
    }
}
