package com.huawei.hms.kit.awareness.capture.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.a.a.f;
import com.huawei.hms.kit.awareness.internal.ServiceOpenIdStatusImpl;
import com.huawei.hms.kit.awareness.status.ServiceOpenIdStatus;

/* loaded from: classes4.dex */
public class SidInternalResult extends f {
    public static final Parcelable.Creator<SidInternalResult> CREATOR = new Parcelable.Creator<SidInternalResult>() { // from class: com.huawei.hms.kit.awareness.capture.internal.SidInternalResult.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public SidInternalResult[] newArray(int i) {
            return new SidInternalResult[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public SidInternalResult createFromParcel(Parcel parcel) {
            return new SidInternalResult(parcel);
        }
    };
    private final ServiceOpenIdStatusImpl sidStatus;

    @Override // com.huawei.hms.kit.awareness.a.a.f, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.huawei.hms.kit.awareness.a.a.f, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.sidStatus, i);
        parcel.writeInt(c());
    }

    public ServiceOpenIdStatus getSidStatus() {
        return this.sidStatus;
    }

    public SidInternalResult(ServiceOpenIdStatusImpl serviceOpenIdStatusImpl) {
        this.sidStatus = serviceOpenIdStatusImpl;
    }

    private SidInternalResult(Parcel parcel) {
        super(parcel);
        this.sidStatus = (ServiceOpenIdStatusImpl) parcel.readParcelable(ServiceOpenIdStatusImpl.class.getClassLoader());
        a(parcel.readInt());
    }
}
