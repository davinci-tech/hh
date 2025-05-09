package com.huawei.hms.ads.template.downloadbuttonstyle;

import android.content.Context;
import android.content.res.Resources;
import com.huawei.health.R;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.views.AppDownloadButton;
import com.huawei.openalliance.ad.views.ExtandAppDownloadButtonStyleHm;

/* loaded from: classes4.dex */
public class d extends com.huawei.hms.ads.template.downloadbuttonstyle.a {
    @Override // com.huawei.hms.ads.template.downloadbuttonstyle.a
    protected void b(Context context) {
        AppDownloadButton appDownloadButton = this.c.get();
        if (appDownloadButton == null) {
            ho.b("IconDwnButtonStyle", "btn is null");
            return;
        }
        appDownloadButton.setMinWidth((int) context.getResources().getDimension(R.dimen._2131363314_res_0x7f0a05f2));
        appDownloadButton.setMaxWidth((int) context.getResources().getDimension(R.dimen._2131363314_res_0x7f0a05f2));
        appDownloadButton.setFixedWidth(false);
        int dimension = (int) context.getResources().getDimension(R.dimen._2131363334_res_0x7f0a0606);
        appDownloadButton.setPadding(dimension, dimension, dimension, dimension);
        appDownloadButton.setFontFamily(Constants.FONT);
        appDownloadButton.setTextSize((int) context.getResources().getDimension(R.dimen._2131363254_res_0x7f0a05b6));
        appDownloadButton.updateLayoutHeight();
        appDownloadButton.refreshStatusAsync(null);
        appDownloadButton.setVisibility(0);
    }

    static class a extends ExtandAppDownloadButtonStyleHm {
        public a(Context context) {
            super(context);
            Resources resources = context.getResources();
            this.normalStyle.setBackground(resources.getDrawable(R.drawable._2131428493_res_0x7f0b048d));
            this.normalStyle.setTextColor(context.getResources().getColor(R.color._2131297952_res_0x7f0906a0));
            this.processingStyle.setBackground(resources.getDrawable(R.drawable._2131428495_res_0x7f0b048f));
            this.processingStyle.setTextColor(resources.getColor(R.color._2131297921_res_0x7f090681));
            this.installingStyle.setBackground(resources.getDrawable(R.drawable._2131428491_res_0x7f0b048b));
            this.installingStyle.setTextColor(resources.getColor(R.color._2131297914_res_0x7f09067a));
            this.cancelBtnDrawable = resources.getDrawable(R.drawable._2131428497_res_0x7f0b0491);
        }
    }

    static class b extends ExtandAppDownloadButtonStyleHm {
        public b(Context context) {
            super(context);
            Resources resources = context.getResources();
            this.normalStyle.setBackground(resources.getDrawable(R.drawable._2131428492_res_0x7f0b048c));
            this.normalStyle.setTextColor(context.getResources().getColor(R.color._2131297951_res_0x7f09069f));
            this.processingStyle.setBackground(resources.getDrawable(R.drawable._2131428494_res_0x7f0b048e));
            this.processingStyle.setTextColor(resources.getColor(R.color._2131297920_res_0x7f090680));
            this.installingStyle.setBackground(resources.getDrawable(R.drawable._2131428490_res_0x7f0b048a));
            this.installingStyle.setTextColor(resources.getColor(R.color._2131297914_res_0x7f09067a));
        }
    }

    @Override // com.huawei.hms.ads.template.downloadbuttonstyle.a
    public void a() {
        AppDownloadButton appDownloadButton = this.c.get();
        if (appDownloadButton == null) {
            ho.b("IconDwnButtonStyle", "btn is null");
        } else {
            appDownloadButton.setAppDownloadButtonStyle(1 == this.b ? new a(this.f4344a) : new b(this.f4344a));
        }
    }

    public d(Context context, AppDownloadButton appDownloadButton) {
        super(context, appDownloadButton);
    }
}
