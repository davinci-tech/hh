package com.huawei.hms.iapfull.pay;

import android.content.DialogInterface;
import android.content.Intent;
import com.huawei.hms.iapfull.y0;

/* loaded from: classes4.dex */
class d implements DialogInterface.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ PayActivity f4747a;

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        y0.b("PayActivity", "cancel by user");
        this.f4747a.m.setErrMsg("payment cancelled by user");
        this.f4747a.m.setReturnCode(30000);
        Intent intent = new Intent();
        intent.putExtras(this.f4747a.m.toBundle());
        this.f4747a.setResult(-1, intent);
        this.f4747a.finish();
    }

    d(PayActivity payActivity) {
        this.f4747a = payActivity;
    }
}
