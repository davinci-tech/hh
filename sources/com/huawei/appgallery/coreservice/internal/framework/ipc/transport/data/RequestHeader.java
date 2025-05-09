package com.huawei.appgallery.coreservice.internal.framework.ipc.transport.data;

import android.os.Parcelable;
import com.huawei.appgallery.coreservice.internal.support.parcelable.AutoParcelable;
import com.huawei.appgallery.coreservice.internal.support.parcelable.EnableAutoParcel;

/* loaded from: classes2.dex */
public class RequestHeader extends AutoParcelable {
    public static final Parcelable.Creator<RequestHeader> CREATOR = new AutoParcelable.AutoCreator(RequestHeader.class);

    @EnableAutoParcel(3)
    private String mMediaPkg;

    @EnableAutoParcel(2)
    private String mPackageName;

    @EnableAutoParcel(4)
    private int mRequireVersionCode;

    @EnableAutoParcel(1)
    private int mSdkVersion = 9;

    public void b(String str) {
        this.mPackageName = str;
    }

    public void a(String str) {
        this.mMediaPkg = str;
    }

    public void a(int i) {
        this.mRequireVersionCode = i;
    }
}
