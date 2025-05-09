package com.huawei.hms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.common.parcel.ParcelReader;
import com.huawei.hms.common.parcel.ParcelWrite;
import com.huawei.hms.common.util.Objects;

@Deprecated
/* loaded from: classes9.dex */
public class StreetViewSource implements Parcelable {
    public static final int TYPE_DEFAULT = 0;
    public static final int TYPE_OUTDOOR = 1;

    /* renamed from: a, reason: collision with root package name */
    private final int f5011a;
    public static final StreetViewSource DEFAULT = new StreetViewSource(0);
    public static final StreetViewSource OUTDOOR = new StreetViewSource(1);
    public static final Parcelable.Creator<StreetViewSource> CREATOR = new Parcelable.Creator<StreetViewSource>() { // from class: com.huawei.hms.maps.model.StreetViewSource.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public StreetViewSource[] newArray(int i) {
            return new StreetViewSource[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public StreetViewSource createFromParcel(Parcel parcel) {
            return new StreetViewSource(parcel);
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ParcelWrite parcelWrite = new ParcelWrite(parcel);
        int beginObjectHeader = parcelWrite.beginObjectHeader();
        parcelWrite.writeInt(2, this.f5011a);
        parcelWrite.finishObjectHeader(beginObjectHeader);
    }

    public final String toString() {
        return "Type is:" + this.f5011a;
    }

    public final int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.f5011a));
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof StreetViewSource) && this.f5011a == ((StreetViewSource) obj).f5011a;
    }

    protected StreetViewSource(Parcel parcel) {
        this.f5011a = new ParcelReader(parcel).readInt(2, 0);
    }

    public StreetViewSource(int i) {
        this.f5011a = i;
    }
}
