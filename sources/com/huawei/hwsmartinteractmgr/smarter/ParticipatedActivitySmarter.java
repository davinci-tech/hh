package com.huawei.hwsmartinteractmgr.smarter;

import android.content.Context;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.kvs;

/* loaded from: classes5.dex */
public class ParticipatedActivitySmarter extends BaseSmarter {
    public ParticipatedActivitySmarter(Context context) {
        super(context);
    }

    public void b() {
        LogUtil.a("SMART_ParticipatedActivitySmarter", "deleteResult = ", Integer.valueOf(kvs.b(this.e).d(100000)));
    }
}
