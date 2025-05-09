package com.huawei.hms.kit.awareness.a.a;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.status.ZenModeStatus;

/* loaded from: classes9.dex */
public class o extends f {
    public static final Parcelable.Creator<o> CREATOR = new Parcelable.Creator<o>() { // from class: com.huawei.hms.kit.awareness.a.a.o.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public o[] newArray(int i) {
            return new o[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public o createFromParcel(Parcel parcel) {
            return new o(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private final com.huawei.hms.kit.awareness.b.l f4803a;

    @Override // com.huawei.hms.kit.awareness.a.a.f, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.huawei.hms.kit.awareness.a.a.f, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.f4803a, i);
        parcel.writeInt(c());
    }

    public ZenModeStatus a() {
        return this.f4803a;
    }

    private o(Parcel parcel) {
        super(parcel);
        this.f4803a = (com.huawei.hms.kit.awareness.b.l) parcel.readParcelable(com.huawei.hms.kit.awareness.b.l.class.getClassLoader());
        a(parcel.readInt());
    }

    public o(int i) {
        this.f4803a = new com.huawei.hms.kit.awareness.b.l(i);
    }
}
