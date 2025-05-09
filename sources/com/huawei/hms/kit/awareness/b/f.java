package com.huawei.hms.kit.awareness.b;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.status.HeadsetStatus;

/* loaded from: classes9.dex */
public class f implements Parcelable, HeadsetStatus {
    public static final Parcelable.Creator<f> CREATOR = new Parcelable.Creator<f>() { // from class: com.huawei.hms.kit.awareness.b.f.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public f[] newArray(int i) {
            return new f[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public f createFromParcel(Parcel parcel) {
            return new f(parcel.readInt());
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private final int f4836a;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f4836a);
    }

    @Override // com.huawei.hms.kit.awareness.status.HeadsetStatus
    public int getStatus() {
        return this.f4836a;
    }

    public f(int i) {
        this.f4836a = i;
    }
}
