package com.huawei.hms.kit.awareness.a.a;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.status.BeaconStatus;

/* loaded from: classes9.dex */
public class c extends f {
    public static final Parcelable.Creator<c> CREATOR = new Parcelable.Creator<c>() { // from class: com.huawei.hms.kit.awareness.a.a.c.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public c[] newArray(int i) {
            return new c[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public c createFromParcel(Parcel parcel) {
            return new c(parcel);
        }
    };
    private final com.huawei.hms.kit.awareness.b.d b;

    @Override // com.huawei.hms.kit.awareness.a.a.f, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.huawei.hms.kit.awareness.a.a.f, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.b, i);
        parcel.writeInt(c());
    }

    public BeaconStatus a() {
        return this.b;
    }

    public c(com.huawei.hms.kit.awareness.b.d dVar) {
        this.b = dVar;
    }

    private c(Parcel parcel) {
        super(parcel);
        this.b = (com.huawei.hms.kit.awareness.b.d) parcel.readParcelable(com.huawei.hms.kit.awareness.b.d.class.getClassLoader());
        a(parcel.readInt());
    }
}
