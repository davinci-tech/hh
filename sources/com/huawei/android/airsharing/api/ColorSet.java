package com.huawei.android.airsharing.api;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class ColorSet implements Parcelable {
    public static final Parcelable.Creator<ColorSet> CREATOR = new Parcelable.Creator<ColorSet>() { // from class: com.huawei.android.airsharing.api.ColorSet.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public ColorSet[] newArray(int i) {
            return new ColorSet[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: dt_, reason: merged with bridge method [inline-methods] */
        public ColorSet createFromParcel(Parcel parcel) {
            ColorSet colorSet = new ColorSet();
            colorSet.setTitleColor(parcel.readInt());
            colorSet.setSubtitleColor(parcel.readInt());
            colorSet.setDialogBackgroundColor(parcel.readInt());
            colorSet.setButtonColor(parcel.readInt());
            colorSet.setDisableButtonColor(parcel.readInt());
            colorSet.setContentTextColor(parcel.readInt());
            colorSet.setDividerColor(parcel.readInt());
            return colorSet;
        }
    };
    private int mTitleColor = 0;
    private int mSubtitleColor = 0;
    private int mDialogBackgroundColor = 0;
    private int mButtonColor = 0;
    private int mDisableButtonColor = 0;
    private int mContentTextColor = 0;
    private int mDividerColor = 0;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public void setTitleColor(int i) {
        this.mTitleColor = i;
    }

    public int getTitleColor() {
        return this.mTitleColor;
    }

    public void setSubtitleColor(int i) {
        this.mSubtitleColor = i;
    }

    public int getSubtitleColor() {
        return this.mSubtitleColor;
    }

    public void setDialogBackgroundColor(int i) {
        this.mDialogBackgroundColor = i;
    }

    public int getDialogBackgroundColor() {
        return this.mDialogBackgroundColor;
    }

    public void setButtonColor(int i) {
        this.mButtonColor = i;
    }

    public int getButtonColor() {
        return this.mButtonColor;
    }

    public void setDisableButtonColor(int i) {
        this.mDisableButtonColor = i;
    }

    public int getDisableButtonColor() {
        return this.mDisableButtonColor;
    }

    public void setContentTextColor(int i) {
        this.mContentTextColor = i;
    }

    public int getContentTextColor() {
        return this.mContentTextColor;
    }

    public void setDividerColor(int i) {
        this.mDividerColor = i;
    }

    public int getDividerColor() {
        return this.mDividerColor;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeInt(this.mTitleColor);
        parcel.writeInt(this.mSubtitleColor);
        parcel.writeInt(this.mDialogBackgroundColor);
        parcel.writeInt(this.mButtonColor);
        parcel.writeInt(this.mDisableButtonColor);
        parcel.writeInt(this.mContentTextColor);
        parcel.writeInt(this.mDividerColor);
    }

    public String toString() {
        return "ColorSet[ titleColor:" + this.mTitleColor + "subtitleColor:" + this.mSubtitleColor + "dialogBackgroundColor: " + this.mDialogBackgroundColor + "buttonColor:" + this.mButtonColor + "disableButtonColor:" + this.mDisableButtonColor + "contentTextColor:" + this.mContentTextColor + "dividerColor" + this.mDividerColor + " ]";
    }
}
