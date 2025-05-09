package com.huawei.hms.kit.awareness.b;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.status.CapabilityStatus;
import java.util.Arrays;

/* loaded from: classes9.dex */
public class k implements Parcelable, CapabilityStatus {
    public static final Parcelable.Creator<k> CREATOR = new Parcelable.Creator<k>() { // from class: com.huawei.hms.kit.awareness.b.k.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public k[] newArray(int i) {
            return new k[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public k createFromParcel(Parcel parcel) {
            return new k(parcel);
        }
    };
    private static final int b = 100;

    /* renamed from: a, reason: collision with root package name */
    private final int[] f4841a;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f4841a.length);
        parcel.writeIntArray(this.f4841a);
    }

    @Override // com.huawei.hms.kit.awareness.status.CapabilityStatus
    public int[] getCapabilities() {
        int[] iArr = this.f4841a;
        return Arrays.copyOf(iArr, iArr.length);
    }

    public k(int[] iArr) {
        this.f4841a = Arrays.copyOf(iArr, iArr.length);
    }

    private k(Parcel parcel) {
        int readInt = parcel.readInt();
        if (readInt <= 0 || readInt > 100) {
            this.f4841a = new int[0];
            return;
        }
        int[] iArr = new int[readInt];
        this.f4841a = iArr;
        parcel.readIntArray(iArr);
    }

    public k() {
        this.f4841a = new int[0];
    }
}
