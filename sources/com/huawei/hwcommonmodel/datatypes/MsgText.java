package com.huawei.hwcommonmodel.datatypes;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.jdy;

/* loaded from: classes5.dex */
public class MsgText implements Parcelable {
    public static final Parcelable.Creator<MsgText> CREATOR = new Parcelable.Creator<MsgText>() { // from class: com.huawei.hwcommonmodel.datatypes.MsgText.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: bFj_, reason: merged with bridge method [inline-methods] */
        public MsgText createFromParcel(Parcel parcel) {
            return new MsgText(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public MsgText[] newArray(int i) {
            return new MsgText[i];
        }
    };
    private String textContent;
    private int textType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MsgText() {
    }

    public MsgText(int i, String str) {
        this.textType = i;
        this.textContent = str;
    }

    protected MsgText(Parcel parcel) {
        this.textType = parcel.readInt();
        this.textContent = parcel.readString();
    }

    public int getTextType() {
        return ((Integer) jdy.d(Integer.valueOf(this.textType))).intValue();
    }

    public void setTextType(int i) {
        this.textType = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public String getTextContent() {
        return (String) jdy.d(this.textContent);
    }

    public void setTextContent(String str) {
        this.textContent = (String) jdy.d(str);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.textType);
        parcel.writeString(this.textContent);
    }
}
