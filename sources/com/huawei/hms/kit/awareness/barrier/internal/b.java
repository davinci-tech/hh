package com.huawei.hms.kit.awareness.barrier.internal;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.barrier.BarrierStatus;
import com.huawei.hms.kit.awareness.barrier.BarrierStatusMap;

/* loaded from: classes4.dex */
public class b extends com.huawei.hms.kit.awareness.a.a.f {
    public static final Parcelable.Creator<b> CREATOR = new Parcelable.Creator<b>() { // from class: com.huawei.hms.kit.awareness.barrier.internal.b.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public b[] newArray(int i) {
            return new b[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public b createFromParcel(Parcel parcel) {
            return new b(parcel);
        }
    };
    private final e b;

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

    public BarrierStatusMap b() {
        return this.b;
    }

    public void a(String str, BarrierStatus barrierStatus) {
        if (barrierStatus instanceof d) {
            this.b.a(str, (d) barrierStatus);
        }
    }

    public static b a() {
        return new b();
    }

    private b(Parcel parcel) {
        super(parcel);
        this.b = (e) parcel.readParcelable(e.class.getClassLoader());
        a(parcel.readInt());
    }

    private b() {
        this.b = e.a();
    }
}
