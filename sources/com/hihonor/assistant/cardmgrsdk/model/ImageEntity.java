package com.hihonor.assistant.cardmgrsdk.model;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class ImageEntity implements Parcelable {
    public static final Parcelable.Creator<ImageEntity> CREATOR = new Parcelable.Creator<ImageEntity>() { // from class: com.hihonor.assistant.cardmgrsdk.model.ImageEntity.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ImageEntity[] newArray(int i) {
            return new ImageEntity[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ImageEntity createFromParcel(Parcel parcel) {
            return new ImageEntity(parcel);
        }
    };
    public byte[] imageBytes;
    public int viewId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.viewId);
        parcel.writeByteArray(this.imageBytes);
    }

    public void setViewId(int i) {
        this.viewId = i;
    }

    public void setImageBytes(byte[] bArr) {
        this.imageBytes = bArr;
    }

    public int getViewId() {
        return this.viewId;
    }

    public byte[] getImageBytes() {
        return this.imageBytes;
    }

    public ImageEntity(Parcel parcel) {
        this.viewId = parcel.readInt();
        this.imageBytes = parcel.createByteArray();
    }

    public ImageEntity(int i, byte[] bArr) {
        this.viewId = i;
        this.imageBytes = bArr;
    }
}
