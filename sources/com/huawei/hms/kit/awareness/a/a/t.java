package com.huawei.hms.kit.awareness.a.a;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.status.WifiStatus;

/* loaded from: classes9.dex */
public class t extends f {
    private final com.huawei.hms.kit.awareness.b.p b;

    /* renamed from: a, reason: collision with root package name */
    private static final com.huawei.hms.kit.awareness.b.p f4808a = new com.huawei.hms.kit.awareness.b.p();
    public static final Parcelable.Creator<t> CREATOR = new Parcelable.Creator<t>() { // from class: com.huawei.hms.kit.awareness.a.a.t.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public t[] newArray(int i) {
            return new t[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public t createFromParcel(Parcel parcel) {
            return new t(parcel);
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

    public WifiStatus a() {
        return this.b;
    }

    public t(com.huawei.hms.kit.awareness.b.p pVar) {
        this.b = pVar;
    }

    private t(Parcel parcel) {
        super(parcel);
        com.huawei.hms.kit.awareness.b.p pVar = (com.huawei.hms.kit.awareness.b.p) parcel.readParcelable(com.huawei.hms.kit.awareness.b.p.class.getClassLoader());
        this.b = pVar == null ? f4808a : pVar;
        a(parcel.readInt());
    }
}
