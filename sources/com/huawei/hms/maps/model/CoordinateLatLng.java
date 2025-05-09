package com.huawei.hms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.common.parcel.ParcelReader;
import com.huawei.hms.common.parcel.ParcelWrite;
import com.huawei.hms.maps.model.CoordinateType;

/* loaded from: classes4.dex */
public class CoordinateLatLng implements Parcelable {
    public static final Parcelable.Creator<CoordinateLatLng> CREATOR = new Parcelable.Creator<CoordinateLatLng>() { // from class: com.huawei.hms.maps.model.CoordinateLatLng.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public CoordinateLatLng[] newArray(int i) {
            return new CoordinateLatLng[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public CoordinateLatLng createFromParcel(Parcel parcel) {
            return new CoordinateLatLng(parcel);
        }
    };
    private CoordinateType coordinateType;
    private LatLng location;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ParcelWrite parcelWrite = new ParcelWrite(parcel);
        int beginObjectHeader = parcelWrite.beginObjectHeader();
        parcelWrite.writeParcelable(2, this.coordinateType, i, false);
        parcelWrite.writeParcelable(3, this.location, i, false);
        parcelWrite.finishObjectHeader(beginObjectHeader);
    }

    public void setLocation(LatLng latLng) {
        this.location = latLng;
    }

    public void setCoordinateType(CoordinateType coordinateType) {
        this.coordinateType = coordinateType;
    }

    public LatLng getLocation() {
        return this.location;
    }

    public CoordinateType.CoordinateTypeEnum getCoordinateType() {
        CoordinateType coordinateType = this.coordinateType;
        if (coordinateType != null) {
            if (coordinateType.getCoordinateType() == 0) {
                return CoordinateType.CoordinateTypeEnum.WGS84;
            }
            if (this.coordinateType.getCoordinateType() == 1) {
                return CoordinateType.CoordinateTypeEnum.GCJ02;
            }
        }
        return CoordinateType.CoordinateTypeEnum.UNKNOWN;
    }

    public CoordinateLatLng(CoordinateType coordinateType, LatLng latLng) {
        this.coordinateType = coordinateType;
        this.location = latLng;
    }

    protected CoordinateLatLng(Parcel parcel) {
        ParcelReader parcelReader = new ParcelReader(parcel);
        this.coordinateType = (CoordinateType) parcelReader.readParcelable(2, CoordinateType.CREATOR, null);
        this.location = (LatLng) parcelReader.readParcelable(3, LatLng.CREATOR, null);
    }

    public CoordinateLatLng() {
    }
}
