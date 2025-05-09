package com.huawei.hmf.orb.aidl.impl;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class GetServiceRequest implements Parcelable {
    public static final Parcelable.Creator<GetServiceRequest> CREATOR = new Parcelable.Creator<GetServiceRequest>() { // from class: com.huawei.hmf.orb.aidl.impl.GetServiceRequest.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GetServiceRequest createFromParcel(Parcel parcel) {
            return new GetServiceRequest(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public GetServiceRequest[] newArray(int i) {
            return new GetServiceRequest[i];
        }
    };
    public static final int REMOTE_MODULE_INVOKER = 0;
    private Intent mBindIntent;
    private int mServiceId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public GetServiceRequest(int i, Intent intent) {
        this.mServiceId = i;
        this.mBindIntent = intent;
    }

    public Intent getBindIntent() {
        return this.mBindIntent;
    }

    public int getServiceId() {
        return this.mServiceId;
    }

    protected GetServiceRequest(Parcel parcel) {
        this.mBindIntent = (Intent) parcel.readParcelable(Intent.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mBindIntent, i);
    }
}
