package com.huawei.hms.kit.awareness.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.status.ServiceOpenIdStatus;

/* loaded from: classes4.dex */
public class ServiceOpenIdStatusImpl implements Parcelable, ServiceOpenIdStatus {
    public static final Parcelable.Creator<ServiceOpenIdStatusImpl> CREATOR = new Parcelable.Creator<ServiceOpenIdStatusImpl>() { // from class: com.huawei.hms.kit.awareness.internal.ServiceOpenIdStatusImpl.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ServiceOpenIdStatusImpl[] newArray(int i) {
            return new ServiceOpenIdStatusImpl[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public ServiceOpenIdStatusImpl createFromParcel(Parcel parcel) {
            return new ServiceOpenIdStatusImpl(parcel);
        }
    };
    private final String reason;
    private final int result;
    private final String sid;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.result);
        parcel.writeString(this.reason);
        parcel.writeString(this.sid);
    }

    @Override // com.huawei.hms.kit.awareness.status.ServiceOpenIdStatus
    public String getSid() {
        return this.sid;
    }

    @Override // com.huawei.hms.kit.awareness.status.ServiceOpenIdStatus
    public int getResult() {
        return this.result;
    }

    @Override // com.huawei.hms.kit.awareness.status.ServiceOpenIdStatus
    public String getReason() {
        return this.reason;
    }

    private ServiceOpenIdStatusImpl(Parcel parcel) {
        this.result = parcel.readInt();
        this.reason = parcel.readString();
        this.sid = parcel.readString();
    }

    public ServiceOpenIdStatusImpl(int i, String str, String str2) {
        this.result = i;
        this.reason = str;
        this.sid = str2;
    }
}
