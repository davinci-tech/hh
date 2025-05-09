package com.huawei.hms.iapfull.pay;

import android.content.DialogInterface;
import com.huawei.hms.iapfull.y0;

/* loaded from: classes4.dex */
class e implements DialogInterface.OnClickListener {
    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialogInterface, int i) {
        y0.b("PayActivity", "cancel by user");
    }

    e(PayActivity payActivity) {
    }
}
