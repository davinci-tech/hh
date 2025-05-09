package com.huawei.hms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.location.AMapLocation;
import com.huawei.hms.common.parcel.ParcelReader;
import com.huawei.hms.common.parcel.ParcelWrite;

/* loaded from: classes4.dex */
public class CoordinateType implements Parcelable {
    public static final Parcelable.Creator<CoordinateType> CREATOR = new Parcelable.Creator<CoordinateType>() { // from class: com.huawei.hms.maps.model.CoordinateType.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CoordinateType[] newArray(int i) {
            return new CoordinateType[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public CoordinateType createFromParcel(Parcel parcel) {
            return new CoordinateType(parcel);
        }
    };
    private int coordinateType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ParcelWrite parcelWrite = new ParcelWrite(parcel);
        int beginObjectHeader = parcelWrite.beginObjectHeader();
        parcelWrite.writeInt(2, this.coordinateType);
        parcelWrite.finishObjectHeader(beginObjectHeader);
    }

    public void setCoordinateType(int i) {
        this.coordinateType = i;
    }

    public int getCoordinateType() {
        return this.coordinateType;
    }

    public CoordinateType(CoordinateTypeEnum coordinateTypeEnum) {
        int i;
        if (coordinateTypeEnum != null) {
            if (coordinateTypeEnum == CoordinateTypeEnum.WGS84) {
                i = 0;
            } else if (coordinateTypeEnum == CoordinateTypeEnum.GCJ02) {
                i = 1;
            }
            this.coordinateType = i;
        }
        i = -1;
        this.coordinateType = i;
    }

    public enum CoordinateTypeEnum {
        WGS84(AMapLocation.COORD_TYPE_WGS84),
        GCJ02(AMapLocation.COORD_TYPE_GCJ02),
        UNKNOWN("UNKNOWN");

        private final String coordinateTypeName;

        CoordinateTypeEnum(String str) {
            this.coordinateTypeName = str;
        }
    }

    protected CoordinateType(Parcel parcel) {
        this.coordinateType = new ParcelReader(parcel).readInt(2, 0);
    }

    public CoordinateType() {
    }
}
