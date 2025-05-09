package com.huawei.hms.kit.awareness.a.a;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.status.HeadsetStatus;

/* loaded from: classes9.dex */
public class e extends f {
    public static final Parcelable.Creator<e> CREATOR = new Parcelable.Creator<e>() { // from class: com.huawei.hms.kit.awareness.a.a.e.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public e[] newArray(int i) {
            return new e[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public e createFromParcel(Parcel parcel) {
            return new e(parcel);
        }
    };
    private final com.huawei.hms.kit.awareness.b.f b;

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

    public HeadsetStatus a() {
        return this.b;
    }

    private e(Parcel parcel) {
        super(parcel);
        this.b = (com.huawei.hms.kit.awareness.b.f) parcel.readParcelable(com.huawei.hms.kit.awareness.b.f.class.getClassLoader());
        a(parcel.readInt());
    }

    public e(int i) {
        this.b = new com.huawei.hms.kit.awareness.b.f(i);
    }
}
