package com.huawei.hms.kit.awareness.barrier.internal.c;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public final class e extends com.huawei.hms.kit.awareness.barrier.internal.d.f {
    public static final Parcelable.Creator<e> CREATOR = new Parcelable.Creator<e>() { // from class: com.huawei.hms.kit.awareness.barrier.internal.c.e.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public e[] newArray(int i) {
            return new e[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public e createFromParcel(Parcel parcel) {
            return new e();
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.huawei.hms.kit.awareness.barrier.internal.d.f
    public boolean g() {
        return false;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
    }

    static final class a {

        /* renamed from: a, reason: collision with root package name */
        private static final e f4863a = new e();

        private a() {
        }
    }

    public static com.huawei.hms.kit.awareness.barrier.internal.d.f a() {
        return a.f4863a;
    }

    private e() {
    }
}
