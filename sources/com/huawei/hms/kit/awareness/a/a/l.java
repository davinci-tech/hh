package com.huawei.hms.kit.awareness.a.a;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.status.BluetoothStatus;

/* loaded from: classes9.dex */
public class l extends f {
    public static final Parcelable.Creator<l> CREATOR = new Parcelable.Creator<l>() { // from class: com.huawei.hms.kit.awareness.a.a.l.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public l[] newArray(int i) {
            return new l[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public l createFromParcel(Parcel parcel) {
            return new l(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private final com.huawei.hms.kit.awareness.b.j f4800a;

    @Override // com.huawei.hms.kit.awareness.a.a.f, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.huawei.hms.kit.awareness.a.a.f, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.f4800a, i);
        parcel.writeInt(c());
    }

    public BluetoothStatus a() {
        return this.f4800a;
    }

    private l(Parcel parcel) {
        super(parcel);
        this.f4800a = (com.huawei.hms.kit.awareness.b.j) parcel.readParcelable(com.huawei.hms.kit.awareness.b.j.class.getClassLoader());
        a(parcel.readInt());
    }

    public l(int i) {
        this.f4800a = new com.huawei.hms.kit.awareness.b.j(i);
    }
}
