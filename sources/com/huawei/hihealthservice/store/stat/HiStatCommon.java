package com.huawei.hihealthservice.store.stat;

import android.content.Context;
import com.huawei.hihealth.HiHealthData;
import defpackage.ijb;
import defpackage.ijd;
import defpackage.iwu;

/* loaded from: classes7.dex */
public abstract class HiStatCommon {
    public Context mContext;
    public ijd mDataStatManager;

    public abstract boolean stat(HiHealthData hiHealthData);

    public HiStatCommon(Context context) {
        if (context == null) {
            throw new iwu("HiStatCommon context = null");
        }
        this.mContext = context;
        this.mDataStatManager = ijd.c(context);
    }

    protected HiStatCommon(Context context, String str) {
        if (context == null) {
            throw new iwu("HiStatCommon context = null");
        }
        this.mContext = context.getApplicationContext();
        this.mDataStatManager = ijb.b();
    }
}
