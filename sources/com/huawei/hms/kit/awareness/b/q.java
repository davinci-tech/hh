package com.huawei.hms.kit.awareness.b;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.status.DonateStatus;

/* loaded from: classes4.dex */
public class q implements Parcelable, DonateStatus {
    public static final Parcelable.Creator<q> CREATOR = new Parcelable.Creator<q>() { // from class: com.huawei.hms.kit.awareness.b.q.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public q[] newArray(int i) {
            return new q[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public q createFromParcel(Parcel parcel) {
            return new q(parcel.readInt());
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private final int f4847a;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f4847a);
    }

    @Override // com.huawei.hms.kit.awareness.status.DonateStatus
    public int getStatus() {
        return this.f4847a;
    }

    public q(int i) {
        this.f4847a = i;
    }
}
