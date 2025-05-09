package com.huawei.hms.kit.awareness.a.a;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class h extends f {
    public static final Parcelable.Creator<h> CREATOR = new Parcelable.Creator<h>() { // from class: com.huawei.hms.kit.awareness.a.a.h.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public h[] newArray(int i) {
            return new h[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public h createFromParcel(Parcel parcel) {
            return new h(parcel);
        }
    };

    @Override // com.huawei.hms.kit.awareness.a.a.f, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.huawei.hms.kit.awareness.a.a.f, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeInt(c());
    }

    public static h b(int i) {
        return new h(i);
    }

    private h(Parcel parcel) {
        super(parcel);
        a(parcel.readInt());
    }

    private h(int i) {
        a(i);
    }
}
