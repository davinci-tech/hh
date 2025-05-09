package com.huawei.hms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.common.parcel.ParcelReader;
import com.huawei.hms.common.parcel.ParcelWrite;
import com.huawei.hms.common.util.Objects;

/* loaded from: classes4.dex */
public class PatternItem implements Parcelable {
    public static final Parcelable.Creator<PatternItem> CREATOR = new Parcelable.Creator<PatternItem>() { // from class: com.huawei.hms.maps.model.PatternItem.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PatternItem[] newArray(int i) {
            return new PatternItem[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PatternItem createFromParcel(Parcel parcel) {
            return PatternItem.a(parcel);
        }
    };
    public static final int TYPE_DASH = 0;
    public static final int TYPE_DOT = 1;
    public static final int TYPE_GAP = 2;
    public float length;
    public int type;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ParcelWrite parcelWrite = new ParcelWrite(parcel);
        int beginObjectHeader = parcelWrite.beginObjectHeader();
        parcelWrite.writeInt(2, this.type);
        parcelWrite.writeFloat(3, this.length);
        parcelWrite.finishObjectHeader(beginObjectHeader);
    }

    public String toString() {
        return "The patternItem type is" + this.type + " and length is" + String.valueOf(this.length);
    }

    public int hashCode() {
        return Objects.hashCode(Integer.valueOf(this.type), Float.valueOf(this.length));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PatternItem)) {
            return false;
        }
        PatternItem patternItem = (PatternItem) obj;
        return this.type == patternItem.type && Objects.equal(Float.valueOf(this.length), Float.valueOf(patternItem.length));
    }

    protected static PatternItem a(Parcel parcel) {
        ParcelReader parcelReader = new ParcelReader(parcel);
        int readInt = parcelReader.readInt(2, 0);
        float readFloat = parcelReader.readFloat(3, 0.0f);
        return readInt == 0 ? new Dash(readFloat) : readInt == 1 ? new Dot() : readInt == 2 ? new Gap(readFloat) : new PatternItem(readInt, readFloat);
    }

    public PatternItem(int i, float f) {
        this.type = i;
        this.length = f;
    }
}
