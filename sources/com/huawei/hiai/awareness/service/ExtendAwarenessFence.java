package com.huawei.hiai.awareness.service;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

@Deprecated
/* loaded from: classes4.dex */
public class ExtendAwarenessFence extends AwarenessFence implements Parcelable {
    public static final Parcelable.Creator<ExtendAwarenessFence> CREATOR = new Parcelable.Creator<ExtendAwarenessFence>() { // from class: com.huawei.hiai.awareness.service.ExtendAwarenessFence.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ExtendAwarenessFence createFromParcel(Parcel parcel) {
            return new ExtendAwarenessFence(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ExtendAwarenessFence[] newArray(int i) {
            return new ExtendAwarenessFence[i];
        }
    };
    private static final String TAG = "ExtendAwarenessFence";
    protected Bundle registerBundle;

    @Override // com.huawei.hiai.awareness.service.AwarenessFence, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ExtendAwarenessFence(Parcel parcel) {
        super(parcel);
        this.registerBundle = parcel.readBundle();
    }

    public ExtendAwarenessFence(int i, int i2, int i3, String str) {
        super(i, i2, i3, str);
    }

    @Override // com.huawei.hiai.awareness.service.AwarenessFence, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeBundle(this.registerBundle);
    }

    public Bundle getRegisterBundle() {
        return this.registerBundle;
    }

    public void setRegisterBundle(Bundle bundle) {
        this.registerBundle = bundle;
    }
}
