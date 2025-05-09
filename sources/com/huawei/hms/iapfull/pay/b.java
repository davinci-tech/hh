package com.huawei.hms.iapfull.pay;

import android.content.DialogInterface;
import android.content.Intent;
import com.huawei.hms.iapfull.y0;

/* loaded from: classes4.dex */
class b implements DialogInterface.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ PayActivity f4745a;

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        y0.b("PayActivity", "user click cancel");
        this.f4745a.m.setErrMsg("Device has been rooted, payment cancelled by user");
        this.f4745a.m.setReturnCode(30000);
        Intent intent = new Intent();
        intent.putExtras(this.f4745a.m.toBundle());
        this.f4745a.setResult(-1, intent);
        this.f4745a.finish();
    }

    b(PayActivity payActivity) {
        this.f4745a = payActivity;
    }
}
