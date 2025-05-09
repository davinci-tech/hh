package com.huawei.hwcommonmodel.datatypes;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes5.dex */
public class MsgImage implements Parcelable {
    public static final Parcelable.Creator<MsgImage> CREATOR = new Parcelable.Creator<MsgImage>() { // from class: com.huawei.hwcommonmodel.datatypes.MsgImage.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: bFi_, reason: merged with bridge method [inline-methods] */
        public MsgImage createFromParcel(Parcel parcel) {
            return new MsgImage(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public MsgImage[] newArray(int i) {
            return new MsgImage[i];
        }
    };
    private Bitmap imageBitmap;
    private int imageType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MsgImage() {
    }

    public MsgImage(Bitmap bitmap, int i) {
        this.imageBitmap = bitmap;
        this.imageType = i;
    }

    protected MsgImage(Parcel parcel) {
        this.imageBitmap = (Bitmap) parcel.readParcelable(Bitmap.class.getClassLoader());
        this.imageType = parcel.readInt();
    }

    public int getImageType() {
        return this.imageType;
    }

    public void setImageType(int i) {
        this.imageType = i;
    }

    public Bitmap getImageBitmap() {
        return this.imageBitmap;
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.imageBitmap = bitmap;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.imageBitmap, 0);
        parcel.writeInt(this.imageType);
    }
}
