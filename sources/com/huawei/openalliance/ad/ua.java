package com.huawei.openalliance.ad;

import android.app.Dialog;
import android.content.res.Resources;
import com.huawei.health.R;
import com.huawei.openalliance.ad.views.r;
import java.lang.ref.WeakReference;

/* loaded from: classes9.dex */
public class ua implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    private WeakReference<r> f7548a;

    @Override // java.lang.Runnable
    public void run() {
        r rVar = this.f7548a.get();
        if (rVar == null) {
            ho.c("InterstitialViewNonWifi", "container is null.");
            return;
        }
        if (a(rVar.getNonwifiDialog())) {
            ho.b("InterstitialViewNonWifi", "NonWifiDialog already shown.");
            return;
        }
        ho.b("InterstitialViewNonWifi", "pop up dialog");
        Resources resources = rVar.getResources();
        rVar.setNonwifiDialog(com.huawei.openalliance.ad.utils.y.a(rVar.getContext(), "", resources.getString(R.string._2130851053_res_0x7f0234ed), resources.getString(R.string._2130851147_res_0x7f02354b), resources.getString(R.string._2130851146_res_0x7f02354a), new tz(rVar)));
        rVar.getNonwifiDialog().setCancelable(false);
    }

    public static boolean a(Dialog dialog) {
        return dialog != null && dialog.isShowing();
    }

    public ua(r rVar) {
        this.f7548a = new WeakReference<>(rVar);
    }
}
