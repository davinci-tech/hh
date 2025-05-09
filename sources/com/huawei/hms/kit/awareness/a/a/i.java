package com.huawei.hms.kit.awareness.a.a;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.status.TimeCategories;

/* loaded from: classes9.dex */
public class i extends f {
    public static final Parcelable.Creator<i> CREATOR = new Parcelable.Creator<i>() { // from class: com.huawei.hms.kit.awareness.a.a.i.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public i[] newArray(int i) {
            return new i[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public i createFromParcel(Parcel parcel) {
            return new i(parcel);
        }
    };
    private com.huawei.hms.kit.awareness.b.g b;

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

    public TimeCategories a() {
        return this.b;
    }

    public i(int[] iArr) {
        this.b = new com.huawei.hms.kit.awareness.b.g(iArr);
    }

    private i(Parcel parcel) {
        super(parcel);
        this.b = (com.huawei.hms.kit.awareness.b.g) parcel.readParcelable(com.huawei.hms.kit.awareness.b.g.class.getClassLoader());
        a(parcel.readInt());
    }

    public i() {
    }
}
