package com.huawei.android.airsharing.api;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class DialogInfo implements Parcelable {
    public static final int BUTTON_CANCEL = 2;
    public static final int BUTTON_CONFIRM = 1;
    public static final int BUTTON_CONTINUE = 3;
    public static final int BUTTON_UNDERSTAND = 4;
    public static final Parcelable.Creator<DialogInfo> CREATOR = new Parcelable.Creator<DialogInfo>() { // from class: com.huawei.android.airsharing.api.DialogInfo.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public DialogInfo[] newArray(int i) {
            return new DialogInfo[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: dy_, reason: merged with bridge method [inline-methods] */
        public DialogInfo createFromParcel(Parcel parcel) {
            DialogInfo dialogInfo = new DialogInfo();
            dialogInfo.setDialogType(parcel.readInt());
            dialogInfo.setButtonClickEvent(parcel.readInt());
            dialogInfo.setJsonStringInfo(parcel.readString());
            return dialogInfo;
        }
    };
    public static final int DIALOG_AGREE_CONNECTION = 3;
    public static final int DIALOG_AUTH = 1;
    public static final int DIALOG_UNCERTIFIED = 2;
    private int mButtonClickEvent;
    private int mDialogType;
    private String mJsonData;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public DialogInfo() {
        this(0, 0);
    }

    public DialogInfo(int i, int i2) {
        this.mJsonData = "";
        this.mDialogType = i;
        this.mButtonClickEvent = i2;
    }

    public void setDialogType(int i) {
        this.mDialogType = i;
    }

    public int getDialogType() {
        return this.mDialogType;
    }

    public void setButtonClickEvent(int i) {
        this.mButtonClickEvent = i;
    }

    public int getButtonClickEvent() {
        return this.mButtonClickEvent;
    }

    public void setJsonStringInfo(String str) {
        this.mJsonData = str;
    }

    public String getJsonStringInfo() {
        return this.mJsonData;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeInt(this.mDialogType);
        parcel.writeInt(this.mButtonClickEvent);
        parcel.writeString(this.mJsonData);
    }

    public String toString() {
        return "DialogInfo[ dialogType:" + this.mDialogType + "buttonClickEvent:" + this.mButtonClickEvent + "jsonData: " + this.mJsonData + " ]";
    }
}
