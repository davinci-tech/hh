package com.huawei.openalliance.ad;

import android.app.Dialog;
import android.content.res.Resources;
import com.huawei.health.R;

/* loaded from: classes5.dex */
public class uu implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    private final com.huawei.openalliance.ad.views.interfaces.k f7755a;

    @Override // java.lang.Runnable
    public void run() {
        if (a(this.f7755a.getNonwifiDialog())) {
            ho.a("RewardViewNonWifiDlgR", "NonWifiDialog already shown.");
            return;
        }
        ho.b("RewardViewNonWifiDlgR", "pop up dialog");
        Resources resources = this.f7755a.getResources();
        String string = resources.getString(R.string._2130851053_res_0x7f0234ed);
        String string2 = resources.getString(R.string._2130851147_res_0x7f02354b);
        String string3 = resources.getString(R.string._2130851146_res_0x7f02354a);
        com.huawei.openalliance.ad.views.interfaces.k kVar = this.f7755a;
        kVar.setNonwifiDialog(com.huawei.openalliance.ad.utils.y.a(kVar.getContext(), "", string, string2, string3, new ut(this.f7755a)));
        this.f7755a.getNonwifiDialog().setCancelable(false);
        this.f7755a.pauseView();
    }

    public static boolean a(Dialog dialog) {
        return dialog != null && dialog.isShowing();
    }

    public uu(com.huawei.openalliance.ad.views.interfaces.k kVar) {
        this.f7755a = kVar;
    }
}
