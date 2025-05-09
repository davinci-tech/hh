package com.huawei.hms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.common.parcel.ParcelReader;
import com.huawei.hms.common.parcel.ParcelWrite;
import com.huawei.hms.common.util.Objects;

@Deprecated
/* loaded from: classes9.dex */
public class StreetViewPanoramaLink implements Parcelable {
    public static final Parcelable.Creator<StreetViewPanoramaLink> CREATOR = new Parcelable.Creator<StreetViewPanoramaLink>() { // from class: com.huawei.hms.maps.model.StreetViewPanoramaLink.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StreetViewPanoramaLink[] newArray(int i) {
            return new StreetViewPanoramaLink[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StreetViewPanoramaLink createFromParcel(Parcel parcel) {
            return new StreetViewPanoramaLink(parcel);
        }
    };
    public final float bearing;
    public final String panoId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ParcelWrite parcelWrite = new ParcelWrite(parcel);
        int beginObjectHeader = parcelWrite.beginObjectHeader();
        parcelWrite.writeFloat(2, this.bearing);
        parcelWrite.writeString(3, this.panoId, false);
        parcelWrite.finishObjectHeader(beginObjectHeader);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("panoId", this.panoId).add("bearing", Float.valueOf(this.bearing)).toString();
    }

    public int hashCode() {
        return Objects.hashCode(this.panoId, Float.valueOf(this.bearing));
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StreetViewPanoramaLink)) {
            return false;
        }
        StreetViewPanoramaLink streetViewPanoramaLink = (StreetViewPanoramaLink) obj;
        return this.panoId.equals(streetViewPanoramaLink.panoId) && Float.floatToIntBits(this.bearing) == Float.floatToIntBits(streetViewPanoramaLink.bearing);
    }

    protected StreetViewPanoramaLink(Parcel parcel) {
        ParcelReader parcelReader = new ParcelReader(parcel);
        this.bearing = parcelReader.readFloat(2, 0.0f);
        this.panoId = parcelReader.createString(3, null);
    }
}
