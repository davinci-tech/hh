package com.hihonor.assistant.cardmgrsdk.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes8.dex */
public class ImageDescriptor extends ImageEntity {
    public static final Parcelable.Creator<ImageDescriptor> CREATOR = new Parcelable.Creator<ImageDescriptor>() { // from class: com.hihonor.assistant.cardmgrsdk.model.ImageDescriptor.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ImageDescriptor[] newArray(int i) {
            return new ImageDescriptor[i];
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public ImageDescriptor createFromParcel(Parcel parcel) {
            return new ImageDescriptor(parcel);
        }
    };
    public Uri imageUri;
    public String sizeDescriptor;

    @Override // com.hihonor.assistant.cardmgrsdk.model.ImageEntity, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.hihonor.assistant.cardmgrsdk.model.ImageEntity, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeParcelable(this.imageUri, i);
        parcel.writeString(this.sizeDescriptor);
    }

    public void setSizeDescriptor(String str) {
        this.sizeDescriptor = str;
    }

    public void setImageUri(Uri uri) {
        this.imageUri = uri;
    }

    public String getSizeDescriptor() {
        return this.sizeDescriptor;
    }

    public Uri getImageUri() {
        return this.imageUri;
    }

    public ImageDescriptor(Parcel parcel) {
        super(parcel);
        this.sizeDescriptor = "normal";
        this.imageUri = (Uri) parcel.readParcelable(Uri.class.getClassLoader());
        this.sizeDescriptor = parcel.readString();
    }

    public ImageDescriptor(int i, byte[] bArr, Uri uri) {
        super(i, bArr);
        this.sizeDescriptor = "normal";
        this.imageUri = uri;
    }

    public ImageDescriptor() {
        super(0, null);
        this.sizeDescriptor = "normal";
    }
}
