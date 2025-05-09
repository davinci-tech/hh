package com.huawei.hmf.orb.aidl;

import android.app.PendingIntent;
import com.huawei.hmf.annotation.NamedMethod;

/* loaded from: classes9.dex */
public interface IRemoteActivity {
    public static final String Uri = "com.huawei.hmf.orb.aidl.IRemoteActivity";

    @NamedMethod("getActivity")
    PendingIntent getActivity(int i);
}
