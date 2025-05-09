package com.huawei.hms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.common.parcel.ParcelReader;
import com.huawei.hms.common.parcel.ParcelWrite;

/* loaded from: classes9.dex */
public class PointOfInterest implements Parcelable {
    public static final Parcelable.Creator<PointOfInterest> CREATOR = new Parcelable.Creator<PointOfInterest>() { // from class: com.huawei.hms.maps.model.PointOfInterest.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointOfInterest[] newArray(int i) {
            return new PointOfInterest[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public PointOfInterest createFromParcel(Parcel parcel) {
            return new PointOfInterest(parcel);
        }
    };
    public final LatLng latLng;
    public final String name;
    public final String placeId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ParcelWrite parcelWrite = new ParcelWrite(parcel);
        int beginObjectHeader = parcelWrite.beginObjectHeader();
        parcelWrite.writeParcelable(2, this.latLng, i, false);
        parcelWrite.writeString(3, this.name, false);
        parcelWrite.writeString(4, this.placeId, false);
        parcelWrite.finishObjectHeader(beginObjectHeader);
    }

    public PointOfInterest(LatLng latLng, String str, String str2) {
        this.latLng = latLng;
        this.placeId = str;
        this.name = str2;
    }

    protected PointOfInterest(Parcel parcel) {
        ParcelReader parcelReader = new ParcelReader(parcel);
        this.latLng = (LatLng) parcelReader.readParcelable(2, LatLng.CREATOR, null);
        this.name = parcelReader.createString(3, null);
        this.placeId = parcelReader.createString(4, null);
    }
}
