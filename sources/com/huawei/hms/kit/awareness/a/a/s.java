package com.huawei.hms.kit.awareness.a.a;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.status.ScreenStatus;

/* loaded from: classes9.dex */
public class s extends f {
    public static final Parcelable.Creator<s> CREATOR = new Parcelable.Creator<s>() { // from class: com.huawei.hms.kit.awareness.a.a.s.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public s[] newArray(int i) {
            return new s[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public s createFromParcel(Parcel parcel) {
            return new s(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private final com.huawei.hms.kit.awareness.b.o f4807a;

    @Override // com.huawei.hms.kit.awareness.a.a.f, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.huawei.hms.kit.awareness.a.a.f, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.f4807a, i);
        parcel.writeInt(c());
    }

    public ScreenStatus a() {
        return this.f4807a;
    }

    private s(Parcel parcel) {
        super(parcel);
        this.f4807a = (com.huawei.hms.kit.awareness.b.o) parcel.readParcelable(com.huawei.hms.kit.awareness.b.o.class.getClassLoader());
        a(parcel.readInt());
    }

    public s(int i) {
        this.f4807a = new com.huawei.hms.kit.awareness.b.o(i);
    }
}
