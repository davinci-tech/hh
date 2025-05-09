package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.watchface.videoedit.gles.Constant;

/* loaded from: classes6.dex */
public class CoachBar implements Parcelable {
    public static final Parcelable.Creator<CoachBar> CREATOR = new Parcelable.Creator<CoachBar>() { // from class: com.huawei.pluginfitnessadvice.CoachBar.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: clK_, reason: merged with bridge method [inline-methods] */
        public CoachBar createFromParcel(Parcel parcel) {
            return new CoachBar(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public CoachBar[] newArray(int i) {
            return new CoachBar[i];
        }
    };

    @SerializedName("intro")
    private String mDetailInformation;

    @SerializedName(Constant.TYPE_PHOTO)
    private String mImage;

    @SerializedName("imageDesc")
    private String mImageDesc;

    @SerializedName("imageName")
    private String mImageName;

    @SerializedName("name")
    private String mName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected CoachBar(Parcel parcel) {
        this.mName = parcel.readString();
        this.mImage = parcel.readString();
        this.mImageName = parcel.readString();
        this.mImageDesc = parcel.readString();
        this.mDetailInformation = parcel.readString();
    }

    private CoachBar(e eVar) {
        this.mName = eVar.b;
        this.mImage = eVar.e;
        this.mImageName = eVar.d;
        this.mImageDesc = eVar.f8472a;
        this.mDetailInformation = eVar.c;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mName);
        parcel.writeString(this.mImage);
        parcel.writeString(this.mImageName);
        parcel.writeString(this.mImageDesc);
        parcel.writeString(this.mDetailInformation);
    }

    /* loaded from: classes9.dex */
    public static final class e {

        /* renamed from: a, reason: collision with root package name */
        private String f8472a;
        private String b;
        private String c;
        private String d;
        private String e;
    }

    public String getName() {
        return this.mName;
    }

    public String getImage() {
        return this.mImage;
    }

    public String getImageName() {
        return this.mImageName;
    }

    public String getImageDesc() {
        return this.mImageDesc;
    }

    public String getDetailInformation() {
        return this.mDetailInformation;
    }

    public String toString() {
        return "CoachBar{mName='" + this.mName + "', mImage='" + this.mImage + "', mImageName='" + this.mImageName + "', mImageDesc='" + this.mImageDesc + "', mDetailInformation='" + this.mDetailInformation + "'}";
    }
}
