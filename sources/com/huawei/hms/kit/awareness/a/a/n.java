package com.huawei.hms.kit.awareness.a.a;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.status.CapabilityStatus;

/* loaded from: classes9.dex */
public class n extends f {
    private final com.huawei.hms.kit.awareness.b.k b;

    /* renamed from: a, reason: collision with root package name */
    private static final com.huawei.hms.kit.awareness.b.k f4802a = new com.huawei.hms.kit.awareness.b.k();
    public static final Parcelable.Creator<n> CREATOR = new Parcelable.Creator<n>() { // from class: com.huawei.hms.kit.awareness.a.a.n.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public n[] newArray(int i) {
            return new n[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public n createFromParcel(Parcel parcel) {
            return new n(parcel);
        }
    };

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

    public CapabilityStatus a() {
        return this.b;
    }

    public n(com.huawei.hms.kit.awareness.b.k kVar) {
        this.b = kVar;
    }

    private n(Parcel parcel) {
        super(parcel);
        com.huawei.hms.kit.awareness.b.k kVar = (com.huawei.hms.kit.awareness.b.k) parcel.readParcelable(com.huawei.hms.kit.awareness.b.k.class.getClassLoader());
        this.b = kVar == null ? f4802a : kVar;
        a(parcel.readInt());
    }
}
