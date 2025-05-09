package com.huawei.hms.kit.awareness.b;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.kit.awareness.status.TimeCategories;
import java.util.Collections;

/* loaded from: classes9.dex */
public final class g implements Parcelable, TimeCategories {
    public static final Parcelable.Creator<g> CREATOR = new Parcelable.Creator<g>() { // from class: com.huawei.hms.kit.awareness.b.g.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public g[] newArray(int i) {
            return new g[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public g createFromParcel(Parcel parcel) {
            int readInt = parcel.readInt();
            if (readInt < 0 || readInt > 10) {
                return new g(new int[0]);
            }
            int[] iArr = new int[readInt];
            parcel.readIntArray(iArr);
            return new g(iArr);
        }
    };
    private static final String b = "TimeCategoriesImpl";
    private static final int c = 10;

    /* renamed from: a, reason: collision with root package name */
    private final int[] f4837a;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (this.f4837a.length > 10) {
            com.huawei.hms.kit.awareness.b.a.c.c(b, "timeIntervalArray.length is bigger than INVALID_ARR_LENGTH: " + Collections.singletonList(this.f4837a), new Object[0]);
        }
        parcel.writeInt(this.f4837a.length);
        parcel.writeIntArray(this.f4837a);
    }

    @Override // com.huawei.hms.kit.awareness.status.TimeCategories
    public boolean isTimeCategory(int i) {
        for (int i2 : this.f4837a) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    @Override // com.huawei.hms.kit.awareness.status.TimeCategories
    public int[] getTimeCategories() {
        return (int[]) this.f4837a.clone();
    }

    public g(int[] iArr) {
        this.f4837a = (int[]) iArr.clone();
    }
}
