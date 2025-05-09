package com.huawei.hms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.common.parcel.ParcelReader;
import com.huawei.hms.common.parcel.ParcelWrite;
import com.huawei.hms.common.util.Objects;

@Deprecated
/* loaded from: classes9.dex */
public class StreetViewPanoramaLocation implements Parcelable {
    public static final Parcelable.Creator<StreetViewPanoramaLocation> CREATOR = new Parcelable.Creator<StreetViewPanoramaLocation>() { // from class: com.huawei.hms.maps.model.StreetViewPanoramaLocation.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StreetViewPanoramaLocation[] newArray(int i) {
            return new StreetViewPanoramaLocation[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public StreetViewPanoramaLocation createFromParcel(Parcel parcel) {
            return new StreetViewPanoramaLocation(parcel);
        }
    };
    public StreetViewPanoramaLink[] links;
    public final String panoId;
    public final LatLng position;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ParcelWrite parcelWrite = new ParcelWrite(parcel);
        int beginObjectHeader = parcelWrite.beginObjectHeader();
        parcelWrite.writeTypedArray(2, this.links, i, false);
        parcelWrite.writeString(3, this.panoId, false);
        parcelWrite.writeParcelable(4, this.position, i, false);
        parcelWrite.finishObjectHeader(beginObjectHeader);
    }

    public String toString() {
        return Objects.toStringHelper(this).add("panoId", this.panoId).add("position", this.position.toString()).toString();
    }

    public int hashCode() {
        return Objects.hashCode(this.position, this.panoId);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StreetViewPanoramaLocation)) {
            return false;
        }
        StreetViewPanoramaLocation streetViewPanoramaLocation = (StreetViewPanoramaLocation) obj;
        return this.panoId.equals(streetViewPanoramaLocation.panoId) && this.position.equals(streetViewPanoramaLocation.position);
    }

    public StreetViewPanoramaLocation(StreetViewPanoramaLink[] streetViewPanoramaLinkArr, LatLng latLng, String str) {
        this.links = streetViewPanoramaLinkArr;
        this.position = latLng;
        this.panoId = str;
    }

    protected StreetViewPanoramaLocation(Parcel parcel) {
        ParcelReader parcelReader = new ParcelReader(parcel);
        this.links = (StreetViewPanoramaLink[]) parcelReader.createTypedArray(2, StreetViewPanoramaLink.CREATOR, null);
        this.panoId = parcelReader.createString(3, null);
        this.position = (LatLng) parcelReader.readParcelable(4, LatLng.CREATOR, null);
    }
}
