package com.huawei.hms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.common.parcel.ParcelReader;
import com.huawei.hms.common.parcel.ParcelWrite;
import java.util.Arrays;

/* loaded from: classes9.dex */
public class VisibleRegion implements Parcelable {
    public static final Parcelable.Creator<VisibleRegion> CREATOR = new Parcelable.Creator<VisibleRegion>() { // from class: com.huawei.hms.maps.model.VisibleRegion.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public VisibleRegion[] newArray(int i) {
            return new VisibleRegion[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public VisibleRegion createFromParcel(Parcel parcel) {
            return new VisibleRegion(parcel);
        }
    };
    public final LatLng farLeft;
    public final LatLng farRight;
    public final LatLngBounds latLngBounds;
    public final LatLng nearLeft;
    public final LatLng nearRight;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ParcelWrite parcelWrite = new ParcelWrite(parcel);
        int beginObjectHeader = parcelWrite.beginObjectHeader();
        parcelWrite.writeParcelable(2, this.farLeft, i, false);
        parcelWrite.writeParcelable(3, this.farRight, i, false);
        parcelWrite.writeParcelable(4, this.latLngBounds, i, false);
        parcelWrite.writeParcelable(5, this.nearLeft, i, false);
        parcelWrite.writeParcelable(6, this.nearRight, i, false);
        parcelWrite.finishObjectHeader(beginObjectHeader);
    }

    public String toString() {
        return "VisibleRegion{farLeft=" + this.farLeft + ", farRight=" + this.farRight + ", latLngBounds=" + this.latLngBounds + ", nearLeft=" + this.nearLeft + ", nearRight=" + this.nearRight + '}';
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.nearLeft, this.nearRight, this.farLeft, this.farRight, this.latLngBounds});
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof VisibleRegion)) {
            return false;
        }
        VisibleRegion visibleRegion = (VisibleRegion) obj;
        return (this.nearLeft.equals(visibleRegion.nearLeft) && this.nearRight.equals(visibleRegion.nearRight)) && (this.farRight.equals(visibleRegion.farRight) && this.latLngBounds.equals(visibleRegion.latLngBounds));
    }

    public VisibleRegion(LatLng latLng, LatLng latLng2, LatLng latLng3, LatLng latLng4, LatLngBounds latLngBounds) {
        this.nearLeft = latLng;
        this.farLeft = latLng3;
        this.nearRight = latLng2;
        this.farRight = latLng4;
        this.latLngBounds = latLngBounds;
    }

    protected VisibleRegion(Parcel parcel) {
        ParcelReader parcelReader = new ParcelReader(parcel);
        this.farLeft = (LatLng) parcelReader.readParcelable(2, LatLng.CREATOR, null);
        this.farRight = (LatLng) parcelReader.readParcelable(3, LatLng.CREATOR, null);
        this.latLngBounds = (LatLngBounds) parcelReader.readParcelable(4, LatLngBounds.CREATOR, null);
        this.nearLeft = (LatLng) parcelReader.readParcelable(5, LatLng.CREATOR, null);
        this.nearRight = (LatLng) parcelReader.readParcelable(6, LatLng.CREATOR, null);
    }
}
