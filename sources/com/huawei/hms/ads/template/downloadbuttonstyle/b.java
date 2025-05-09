package com.huawei.hms.ads.template.downloadbuttonstyle;

import android.content.Context;
import android.content.res.Resources;
import com.huawei.health.R;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.views.AppDownloadButton;
import com.huawei.openalliance.ad.views.ExtandAppDownloadButtonStyleHm;

/* loaded from: classes4.dex */
public class b extends com.huawei.hms.ads.template.downloadbuttonstyle.a {
    protected int d;
    protected int e;

    @Override // com.huawei.hms.ads.template.downloadbuttonstyle.a
    protected void b(Context context) {
        AppDownloadButton appDownloadButton = this.c.get();
        if (appDownloadButton == null) {
            ho.b("DefaultDwnButtonStyle", "btn is null");
            return;
        }
        appDownloadButton.setMinWidth((int) context.getResources().getDimension(R.dimen._2131363319_res_0x7f0a05f7));
        appDownloadButton.setPadding(0, 0, 0, this.e);
        appDownloadButton.setMaxWidth((int) context.getResources().getDimension(R.dimen._2131363259_res_0x7f0a05bb));
        appDownloadButton.setFontFamily(Constants.FONT);
        appDownloadButton.setTextSize(this.d);
        appDownloadButton.setVisibility(0);
        appDownloadButton.updateLayoutHeight();
        appDownloadButton.refreshStatusAsync(null);
        appDownloadButton.setVisibility(0);
    }

    protected static class a extends ExtandAppDownloadButtonStyleHm {
        public a(Context context, int i) {
            super(context);
            Resources resources = context.getResources();
            this.normalStyle.setBackground(resources.getDrawable(R.drawable._2131428577_res_0x7f0b04e1));
            this.normalStyle.setTextSize(i);
            this.normalStyle.setTextColor(resources.getColor(R.color._2131297939_res_0x7f090693));
            this.processingStyle.setBackground(resources.getDrawable(R.drawable._2131428577_res_0x7f0b04e1));
            this.processingStyle.setTextSize(i);
            this.processingStyle.setTextColor(resources.getColor(R.color._2131297943_res_0x7f090697));
            this.installingStyle.setBackground(resources.getDrawable(R.drawable._2131428577_res_0x7f0b04e1));
            this.installingStyle.setTextSize(i);
            this.installingStyle.setTextColor(resources.getColor(R.color._2131297937_res_0x7f090691));
            this.cancelBtnDrawable = resources.getDrawable(R.drawable._2131428497_res_0x7f0b0491);
        }
    }

    /* renamed from: com.huawei.hms.ads.template.downloadbuttonstyle.b$b, reason: collision with other inner class name */
    protected static class C0081b extends ExtandAppDownloadButtonStyleHm {
        public C0081b(Context context, int i) {
            super(context);
            Resources resources = context.getResources();
            this.normalStyle.setBackground(resources.getDrawable(R.drawable._2131428576_res_0x7f0b04e0));
            this.normalStyle.setTextSize(i);
            this.normalStyle.setTextColor(resources.getColor(R.color._2131297938_res_0x7f090692));
            this.processingStyle.setBackground(resources.getDrawable(R.drawable._2131428576_res_0x7f0b04e0));
            this.processingStyle.setTextSize(i);
            this.processingStyle.setTextColor(resources.getColor(R.color._2131297942_res_0x7f090696));
            this.installingStyle.setBackground(resources.getDrawable(R.drawable._2131428576_res_0x7f0b04e0));
            this.installingStyle.setTextSize(i);
            this.installingStyle.setTextColor(resources.getColor(R.color._2131297936_res_0x7f090690));
        }
    }

    @Override // com.huawei.hms.ads.template.downloadbuttonstyle.a
    public void a() {
        AppDownloadButton appDownloadButton = this.c.get();
        if (appDownloadButton == null) {
            ho.b("DefaultDwnButtonStyle", "btn is null");
        } else {
            appDownloadButton.setAppDownloadButtonStyle(1 == this.b ? new a(this.f4344a, this.d) : new C0081b(this.f4344a, this.d));
        }
    }

    public b(Context context, AppDownloadButton appDownloadButton) {
        super(context, appDownloadButton);
        this.d = (int) context.getResources().getDimension(R.dimen._2131363254_res_0x7f0a05b6);
        this.e = (int) context.getResources().getDimension(R.dimen._2131363323_res_0x7f0a05fb);
    }
}
