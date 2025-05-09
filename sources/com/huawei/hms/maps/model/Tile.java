package com.huawei.hms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hms.common.parcel.ParcelReader;
import com.huawei.hms.common.parcel.ParcelWrite;

/* loaded from: classes9.dex */
public class Tile implements Parcelable {
    public static final Parcelable.Creator<Tile> CREATOR = new Parcelable.Creator<Tile>() { // from class: com.huawei.hms.maps.model.Tile.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Tile[] newArray(int i) {
            return new Tile[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Tile createFromParcel(Parcel parcel) {
            return new Tile(parcel);
        }
    };
    public final byte[] data;
    public final int height;
    public final int width;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ParcelWrite parcelWrite = new ParcelWrite(parcel);
        int beginObjectHeader = parcelWrite.beginObjectHeader();
        parcelWrite.writeByteArray(2, this.data, false);
        parcelWrite.writeInt(3, this.height);
        parcelWrite.writeInt(4, this.width);
        parcelWrite.finishObjectHeader(beginObjectHeader);
    }

    protected Tile(Parcel parcel) {
        ParcelReader parcelReader = new ParcelReader(parcel);
        this.data = parcelReader.createByteArray(2, null);
        this.height = parcelReader.readInt(3, 0);
        this.width = parcelReader.readInt(4, 0);
    }

    public Tile(int i, int i2, byte[] bArr) {
        this.width = i;
        this.height = i2;
        this.data = bArr;
    }
}
