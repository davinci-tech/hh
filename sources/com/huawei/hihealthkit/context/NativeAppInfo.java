package com.huawei.hihealthkit.context;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes4.dex */
public class NativeAppInfo extends OutOfBandData {
    public static final Parcelable.Creator<NativeAppInfo> CREATOR = new Parcelable.Creator<NativeAppInfo>() { // from class: com.huawei.hihealthkit.context.NativeAppInfo.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: bwT_, reason: merged with bridge method [inline-methods] */
        public NativeAppInfo createFromParcel(Parcel parcel) {
            return new NativeAppInfo();
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public NativeAppInfo[] newArray(int i) {
            return new NativeAppInfo[i];
        }
    };

    public NativeAppInfo() {
        this.tag = 3;
    }
}
