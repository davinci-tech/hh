package com.huawei.hms.kit.awareness.a.a;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.status.WeatherStatus;

/* loaded from: classes9.dex */
public class j extends f {
    public static final Parcelable.Creator<j> CREATOR = new Parcelable.Creator<j>() { // from class: com.huawei.hms.kit.awareness.a.a.j.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public j[] newArray(int i) {
            return new j[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public j createFromParcel(Parcel parcel) {
            return new j(parcel);
        }
    };
    private final com.huawei.hms.kit.awareness.b.h b;

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

    public WeatherStatus a() {
        return this.b;
    }

    public j(com.huawei.hms.kit.awareness.barrier.internal.b.i iVar) {
        this.b = new com.huawei.hms.kit.awareness.b.h(iVar);
        a(0);
    }

    private j(Parcel parcel) {
        super(parcel);
        this.b = (com.huawei.hms.kit.awareness.b.h) parcel.readParcelable(com.huawei.hms.kit.awareness.b.h.class.getClassLoader());
        a(parcel.readInt());
    }

    public j() {
        this.b = null;
    }
}
