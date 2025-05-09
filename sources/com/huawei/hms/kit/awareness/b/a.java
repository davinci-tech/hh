package com.huawei.hms.kit.awareness.b;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.status.AmbientLightStatus;

/* loaded from: classes9.dex */
public class a implements Parcelable, AmbientLightStatus {
    public static final Parcelable.Creator<a> CREATOR = new Parcelable.Creator<a>() { // from class: com.huawei.hms.kit.awareness.b.a.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public a[] newArray(int i) {
            return new a[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public a createFromParcel(Parcel parcel) {
            return new a(parcel);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private final float f4819a;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeFloat(this.f4819a);
    }

    @Override // com.huawei.hms.kit.awareness.status.AmbientLightStatus
    public float getLightIntensity() {
        return this.f4819a;
    }

    private a(Parcel parcel) {
        this.f4819a = parcel.readFloat();
    }

    public a(float f) {
        this.f4819a = f;
    }
}
