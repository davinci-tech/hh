package com.huawei.hms.ads.template.downloadbuttonstyle;

import android.content.Context;
import com.huawei.health.R;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.utils.dd;
import com.huawei.openalliance.ad.utils.dk;
import com.huawei.openalliance.ad.views.AppDownloadButton;
import com.huawei.openalliance.ad.views.ExtandAppDownloadButtonStyleHm;

/* loaded from: classes4.dex */
public class e extends com.huawei.hms.ads.template.downloadbuttonstyle.a {
    private RemoteButtonStyleAttr d;

    private boolean a(int i) {
        return (i & 48) == 32;
    }

    @Override // com.huawei.hms.ads.template.downloadbuttonstyle.a
    protected void b(Context context) {
        AppDownloadButton appDownloadButton = this.c.get();
        if (appDownloadButton == null) {
            ho.b("RemoteButtonStyle", "btn is null");
            return;
        }
        if (this.d.t() != -111111) {
            appDownloadButton.setMinWidth(this.d.t());
        }
        if (this.d.s() != -111111) {
            appDownloadButton.setMaxWidth(this.d.s());
        }
        appDownloadButton.setPaddingRelative(this.d.w(), this.d.y(), this.d.x(), this.d.z());
        appDownloadButton.setResetWidth(this.d.r());
        appDownloadButton.setFixedWidth(this.d.q());
        appDownloadButton.setFontFamily(this.d.v());
        appDownloadButton.setTextSize(this.d.u());
        appDownloadButton.updateLayoutHeight();
        appDownloadButton.setVisibility(0);
        appDownloadButton.updateLayoutHeight();
        appDownloadButton.refreshStatusAsync(null);
        appDownloadButton.setVisibility(0);
    }

    @Override // com.huawei.hms.ads.template.downloadbuttonstyle.a
    public void a() {
        AppDownloadButton appDownloadButton = this.c.get();
        if (appDownloadButton == null) {
            ho.b("RemoteButtonStyle", "btn is null");
            return;
        }
        boolean a2 = a(this.f4344a.getResources().getConfiguration().uiMode);
        boolean p = dd.p(this.f4344a);
        if (ho.a()) {
            ho.a("RemoteButtonStyle", "mediaUiMode %s, emui9DarkMode %s, isNight %s", Integer.valueOf(this.b), Boolean.valueOf(p), Boolean.valueOf(a2));
        }
        appDownloadButton.setAppDownloadButtonStyle((a2 || 1 == this.b || p) ? new a(this.f4344a, this.d) : new b(this.f4344a, this.d));
    }

    protected static class a extends ExtandAppDownloadButtonStyleHm {
        public a(Context context, RemoteButtonStyleAttr remoteButtonStyleAttr) {
            super(context);
            this.normalStyle.setBackground(remoteButtonStyleAttr.h());
            this.normalStyle.setTextSize((int) remoteButtonStyleAttr.u());
            this.normalStyle.setTextColor(remoteButtonStyleAttr.k());
            this.processingStyle.setBackground(dk.a(remoteButtonStyleAttr.l()));
            this.processingStyle.setTextSize((int) remoteButtonStyleAttr.u());
            this.processingStyle.setTextColor(remoteButtonStyleAttr.m());
            this.installingStyle.setBackground(remoteButtonStyleAttr.n());
            this.installingStyle.setTextSize((int) remoteButtonStyleAttr.u());
            this.installingStyle.setTextColor(remoteButtonStyleAttr.o());
            this.cancelBtnStyle.setBackground(remoteButtonStyleAttr.j());
            this.cancelBtnDrawable = remoteButtonStyleAttr.p() == null ? context.getResources().getDrawable(R.drawable._2131428497_res_0x7f0b0491) : remoteButtonStyleAttr.p();
        }
    }

    protected static class b extends ExtandAppDownloadButtonStyleHm {
        public b(Context context, RemoteButtonStyleAttr remoteButtonStyleAttr) {
            super(context);
            this.normalStyle.setBackground(remoteButtonStyleAttr.a());
            this.normalStyle.setTextSize((int) remoteButtonStyleAttr.u());
            this.normalStyle.setTextColor(remoteButtonStyleAttr.b());
            this.processingStyle.setBackground(dk.a(remoteButtonStyleAttr.c()));
            this.processingStyle.setTextSize((int) remoteButtonStyleAttr.u());
            this.processingStyle.setTextColor(remoteButtonStyleAttr.d());
            this.installingStyle.setBackground(remoteButtonStyleAttr.e());
            this.installingStyle.setTextSize((int) remoteButtonStyleAttr.u());
            this.installingStyle.setTextColor(remoteButtonStyleAttr.f());
            this.cancelBtnStyle.setBackground(remoteButtonStyleAttr.i());
            this.cancelBtnDrawable = remoteButtonStyleAttr.g() == null ? context.getResources().getDrawable(R.drawable._2131428496_res_0x7f0b0490) : remoteButtonStyleAttr.g();
        }
    }

    public e(Context context, AppDownloadButton appDownloadButton, RemoteButtonStyleAttr remoteButtonStyleAttr) {
        super(context, appDownloadButton);
        this.d = remoteButtonStyleAttr;
    }
}
