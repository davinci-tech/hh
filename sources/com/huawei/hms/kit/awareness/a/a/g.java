package com.huawei.hms.kit.awareness.a.a;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class g extends f {
    public static final Parcelable.Creator<g> CREATOR = new Parcelable.Creator<g>() { // from class: com.huawei.hms.kit.awareness.a.a.g.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public g[] newArray(int i) {
            return new g[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public g createFromParcel(Parcel parcel) {
            return new g(parcel);
        }
    };
    private final Location b;

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

    public Location a() {
        return this.b;
    }

    g(Parcel parcel) {
        super(parcel);
        this.b = (Location) parcel.readParcelable(Location.class.getClassLoader());
        a(parcel.readInt());
    }

    public g(Location location) {
        this.b = location;
    }
}
