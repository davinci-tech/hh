package com.huawei.hms.iapfull.pay;

import android.content.DialogInterface;
import com.huawei.hms.iapfull.y0;

/* loaded from: classes4.dex */
class a implements DialogInterface.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ PayActivity f4744a;

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        y0.b("PayActivity", "user click continue");
        this.f4744a.c();
    }

    a(PayActivity payActivity) {
        this.f4744a = payActivity;
    }
}
