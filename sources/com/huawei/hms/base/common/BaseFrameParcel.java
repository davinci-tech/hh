package com.huawei.hms.base.common;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes9.dex */
public class BaseFrameParcel implements Parcelable {
    public static final Parcelable.Creator<BaseFrameParcel> CREATOR = new Parcelable.Creator<BaseFrameParcel>() { // from class: com.huawei.hms.base.common.BaseFrameParcel.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BaseFrameParcel createFromParcel(Parcel parcel) {
            return new BaseFrameParcel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public BaseFrameParcel[] newArray(int i) {
            return new BaseFrameParcel[i];
        }
    };
    public Bitmap bitmap;
    public byte[] bytes;
    public int format;
    public int height;
    public int rotation;
    public int width;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public BaseFrameParcel() {
    }

    protected BaseFrameParcel(Parcel parcel) {
        ParcelReader parcelReader = new ParcelReader(parcel);
        this.width = parcelReader.readInt(2, 0);
        this.height = parcelReader.readInt(3, 0);
        this.bitmap = (Bitmap) parcelReader.readParcelable(4, Bitmap.CREATOR, null);
        this.format = parcelReader.readInt(5, 0);
        this.rotation = parcelReader.readInt(6, 0);
        this.bytes = parcelReader.createByteArray(7, null);
        parcelReader.finish();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        ParcelWriter parcelWriter = new ParcelWriter(parcel);
        int beginObjectHeader = parcelWriter.beginObjectHeader();
        parcelWriter.writeInt(2, this.width);
        parcelWriter.writeInt(3, this.height);
        parcelWriter.writeParcelable(4, this.bitmap, i, false);
        parcelWriter.writeInt(5, this.format);
        parcelWriter.writeInt(6, this.rotation);
        parcelWriter.writeByteArray(7, this.bytes, false);
        parcelWriter.finishObjectHeader(beginObjectHeader);
    }
}
