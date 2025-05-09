package com.huawei.openalliance.ad;

import android.content.DialogInterface;
import com.huawei.openalliance.ad.constant.RewardMethods;

/* loaded from: classes5.dex */
public class uh implements DialogInterface.OnCancelListener {

    /* renamed from: a, reason: collision with root package name */
    private com.huawei.openalliance.ad.views.interfaces.j f7555a;

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        this.f7555a.k();
        this.f7555a.setDownloadDialog(null);
        this.f7555a.a("130");
        this.f7555a.b(RewardMethods.SHOW_DOWNLOAD_CONFIRM);
        this.f7555a.resumeView();
    }

    public uh(com.huawei.openalliance.ad.views.interfaces.j jVar) {
        this.f7555a = jVar;
    }
}
