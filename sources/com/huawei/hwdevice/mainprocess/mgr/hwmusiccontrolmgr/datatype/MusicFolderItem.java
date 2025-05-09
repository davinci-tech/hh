package com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes5.dex */
public class MusicFolderItem implements Parcelable {
    public static final Parcelable.Creator<MusicFolderItem> CREATOR = new Parcelable.Creator<MusicFolderItem>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicFolderItem.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: bHz_, reason: merged with bridge method [inline-methods] */
        public MusicFolderItem createFromParcel(Parcel parcel) {
            if (parcel == null) {
                return null;
            }
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            MusicFolderItem musicFolderItem = new MusicFolderItem();
            musicFolderItem.setFolderName(readString);
            musicFolderItem.setNumber(readString2);
            return musicFolderItem;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public MusicFolderItem[] newArray(int i) {
            return new MusicFolderItem[i];
        }
    };
    private String folderName;
    private String number;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getFolderName() {
        return this.folderName;
    }

    public void setFolderName(String str) {
        this.folderName = str;
    }

    public String getNumber() {
        return this.number;
    }

    public void setNumber(String str) {
        this.number = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeString(this.folderName);
        parcel.writeString(this.number);
    }
}
