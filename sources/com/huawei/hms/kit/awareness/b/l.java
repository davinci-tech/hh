package com.huawei.hms.kit.awareness.b;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.status.ZenModeStatus;

/* loaded from: classes9.dex */
public class l implements Parcelable, ZenModeStatus {
    public static final Parcelable.Creator<l> CREATOR = new Parcelable.Creator<l>() { // from class: com.huawei.hms.kit.awareness.b.l.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public l[] newArray(int i) {
            return new l[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public l createFromParcel(Parcel parcel) {
            return new l(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private final int f4842a;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f4842a);
    }

    @Override // com.huawei.hms.kit.awareness.status.ZenModeStatus
    public boolean isZenModeOn() {
        return this.f4842a != 0;
    }

    private l(Parcel parcel) {
        this.f4842a = parcel.readInt();
    }

    public l(int i) {
        this.f4842a = i;
    }
}
