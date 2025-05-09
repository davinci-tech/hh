package com.huawei.hms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.common.parcel.ParcelReader;
import com.huawei.hms.common.parcel.ParcelWrite;
import java.util.Arrays;

@Deprecated
/* loaded from: classes9.dex */
public class StreetViewPanoramaOrientation implements Parcelable {
    public static final Parcelable.Creator<StreetViewPanoramaOrientation> CREATOR = new Parcelable.Creator<StreetViewPanoramaOrientation>() { // from class: com.huawei.hms.maps.model.StreetViewPanoramaOrientation.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StreetViewPanoramaOrientation[] newArray(int i) {
            return new StreetViewPanoramaOrientation[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StreetViewPanoramaOrientation createFromParcel(Parcel parcel) {
            return new StreetViewPanoramaOrientation(parcel);
        }
    };
    public final float bearing;
    public final float tilt;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String toString() {
        return null;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ParcelWrite parcelWrite = new ParcelWrite(parcel);
        int beginObjectHeader = parcelWrite.beginObjectHeader();
        parcelWrite.writeFloat(2, this.bearing);
        parcelWrite.writeFloat(3, this.tilt);
        parcelWrite.finishObjectHeader(beginObjectHeader);
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Float.valueOf(this.tilt), Float.valueOf(this.bearing)});
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StreetViewPanoramaOrientation)) {
            return false;
        }
        StreetViewPanoramaOrientation streetViewPanoramaOrientation = (StreetViewPanoramaOrientation) obj;
        return Float.floatToIntBits(this.tilt) == Float.floatToIntBits(streetViewPanoramaOrientation.tilt) && Float.floatToIntBits(this.bearing) == Float.floatToIntBits(streetViewPanoramaOrientation.bearing);
    }

    public static class Builder {
        public float bearing;
        public float tilt;

        public Builder tilt(float f) {
            this.tilt = f;
            return this;
        }

        public StreetViewPanoramaOrientation build() {
            return new StreetViewPanoramaOrientation(this.tilt, this.bearing);
        }

        public Builder bearing(float f) {
            this.bearing = f;
            return this;
        }

        public Builder(StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
            this.bearing = streetViewPanoramaOrientation.bearing;
            this.tilt = streetViewPanoramaOrientation.tilt;
        }

        public Builder() {
        }
    }

    public static Builder builder(StreetViewPanoramaOrientation streetViewPanoramaOrientation) {
        return new Builder(streetViewPanoramaOrientation);
    }

    public static Builder builder() {
        return new Builder();
    }

    protected StreetViewPanoramaOrientation(Parcel parcel) {
        ParcelReader parcelReader = new ParcelReader(parcel);
        this.bearing = parcelReader.readFloat(2, 0.0f);
        this.tilt = parcelReader.readFloat(3, 0.0f);
    }

    public StreetViewPanoramaOrientation(float f, float f2) {
        this.tilt = f;
        this.bearing = f2;
    }
}
