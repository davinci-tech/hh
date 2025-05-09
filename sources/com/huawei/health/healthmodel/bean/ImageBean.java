package com.huawei.health.healthmodel.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class ImageBean implements Parcelable {
    public static final Parcelable.Creator<ImageBean> CREATOR = new Parcelable.Creator<ImageBean>() { // from class: com.huawei.health.healthmodel.bean.ImageBean.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: ZE_, reason: merged with bridge method [inline-methods] */
        public ImageBean createFromParcel(Parcel parcel) {
            return new ImageBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public ImageBean[] newArray(int i) {
            return new ImageBean[i];
        }
    };

    @SerializedName("id")
    private int mId;

    @SerializedName("imageScenario")
    private String mImageScenario;

    @SerializedName("order")
    private int mOrder;

    @SerializedName("pictureList")
    private ArrayList<PictureBean> mPictureBeanList;

    @SerializedName("textList")
    private ArrayList<TextBean> mTextList;

    @SerializedName("versionRange")
    private String mVersionRange;

    @SerializedName("versionRangeBeta")
    private String mVersionRangeBeta;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ImageBean() {
    }

    public ImageBean(Parcel parcel) {
        if (parcel == null) {
            return;
        }
        this.mVersionRange = parcel.readString();
        this.mVersionRangeBeta = parcel.readString();
        this.mId = parcel.readInt();
        this.mOrder = parcel.readInt();
        this.mImageScenario = parcel.readString();
        this.mPictureBeanList = parcel.createTypedArrayList(PictureBean.CREATOR);
        this.mTextList = parcel.createTypedArrayList(TextBean.CREATOR);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeString(this.mVersionRange);
        parcel.writeString(this.mVersionRangeBeta);
        parcel.writeInt(this.mId);
        parcel.writeInt(this.mOrder);
        parcel.writeString(this.mImageScenario);
        parcel.writeTypedList(this.mPictureBeanList);
        parcel.writeTypedList(this.mTextList);
    }

    public String getVersionRange() {
        return this.mVersionRange;
    }

    public void setVersionRange(String str) {
        this.mVersionRange = str;
    }

    public String getVersionRangeBeta() {
        return this.mVersionRangeBeta;
    }

    public void setVersionRangeBeta(String str) {
        this.mVersionRangeBeta = str;
    }

    public int getId() {
        return this.mId;
    }

    public void setId(int i) {
        this.mId = i;
    }

    public int getOrder() {
        return this.mOrder;
    }

    public void setOrder(int i) {
        this.mOrder = i;
    }

    public String getImageScenario() {
        return this.mImageScenario;
    }

    public void setImageScenario(String str) {
        this.mImageScenario = str;
    }

    public ArrayList<PictureBean> getPictureList() {
        return this.mPictureBeanList;
    }

    public void setPictureList(ArrayList<PictureBean> arrayList) {
        this.mPictureBeanList = arrayList;
    }

    public ArrayList<TextBean> getTextList() {
        return this.mTextList;
    }

    public void setTextList(ArrayList<TextBean> arrayList) {
        this.mTextList = arrayList;
    }

    public String toString() {
        return "ImageBean{mVersionRange=" + this.mVersionRange + ", mVersionRangeBeta=" + this.mVersionRangeBeta + ", mId=" + this.mId + ", mOrder=" + this.mOrder + ", mImageScenario=" + this.mImageScenario + ", mPictureBeanList=" + this.mPictureBeanList + ", mTextList=" + this.mTextList + "}";
    }
}
