package com.huawei.hms.kit.awareness.b;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.status.DarkModeStatus;

/* loaded from: classes9.dex */
public class n implements Parcelable, DarkModeStatus {
    public static final Parcelable.Creator<n> CREATOR = new Parcelable.Creator<n>() { // from class: com.huawei.hms.kit.awareness.b.n.1
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

    /* renamed from: a, reason: collision with root package name */
    private final int f4844a;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f4844a);
    }

    @Override // com.huawei.hms.kit.awareness.status.DarkModeStatus
    public boolean isDarkModeOn() {
        return this.f4844a == 1;
    }

    private n(Parcel parcel) {
        this.f4844a = parcel.readInt();
    }

    public n(int i) {
        this.f4844a = i;
    }
}
