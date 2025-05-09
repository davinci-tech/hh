package com.huawei.hms.kit.awareness.b;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.status.ScreenStatus;

/* loaded from: classes9.dex */
public class o implements Parcelable, ScreenStatus {
    public static final Parcelable.Creator<o> CREATOR = new Parcelable.Creator<o>() { // from class: com.huawei.hms.kit.awareness.b.o.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public o[] newArray(int i) {
            return new o[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public o createFromParcel(Parcel parcel) {
            return new o(parcel.readInt());
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private final int f4845a;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f4845a);
    }

    @Override // com.huawei.hms.kit.awareness.status.ScreenStatus
    public int getStatus() {
        return this.f4845a;
    }

    public o(int i) {
        this.f4845a = i;
    }
}
