package com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.jdy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class MusicFolderStruct implements Parcelable {
    public static final Parcelable.Creator<MusicFolderStruct> CREATOR = new Parcelable.Creator<MusicFolderStruct>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicFolderStruct.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public MusicFolderStruct[] newArray(int i) {
            return new MusicFolderStruct[i];
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: bHA_, reason: merged with bridge method [inline-methods] */
        public MusicFolderStruct createFromParcel(Parcel parcel) {
            if (parcel == null) {
                return null;
            }
            String readString = parcel.readString();
            int readInt = parcel.readInt();
            int readInt2 = parcel.readInt();
            int readInt3 = parcel.readInt();
            ArrayList createTypedArrayList = parcel.createTypedArrayList(MusicStruct.CREATOR);
            MusicFolderStruct musicFolderStruct = new MusicFolderStruct();
            musicFolderStruct.setFolderName(readString);
            musicFolderStruct.setFolderIndex(readInt);
            musicFolderStruct.setFolderMusicAssociationFrameCount(readInt2);
            musicFolderStruct.setMusicStructList(createTypedArrayList);
            musicFolderStruct.setFolderMusicCount(readInt3);
            ArrayList arrayList = new ArrayList(16);
            parcel.readList(arrayList, Integer.class.getClassLoader());
            musicFolderStruct.setMusicIndexList(arrayList);
            return musicFolderStruct;
        }
    };
    private int mFolderIndex;
    private int mFolderMusicAssociationFrameCount;
    private int mFolderMusicCount;
    private String mFolderName;
    private List<Integer> mMusicIndexList;
    private List<MusicStruct> mMusicStructList;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getFolderMusicCount() {
        return this.mFolderMusicCount;
    }

    public void setFolderMusicCount(int i) {
        this.mFolderMusicCount = i;
    }

    public List<MusicStruct> getMusicStructList() {
        return (List) jdy.d(this.mMusicStructList);
    }

    public void setMusicStructList(List<MusicStruct> list) {
        this.mMusicStructList = (List) jdy.d(list);
    }

    public String getFolderName() {
        return (String) jdy.d(this.mFolderName);
    }

    public void setFolderName(String str) {
        this.mFolderName = (String) jdy.d(str);
    }

    public int getFolderIndex() {
        return ((Integer) jdy.d(Integer.valueOf(this.mFolderIndex))).intValue();
    }

    public void setFolderIndex(int i) {
        this.mFolderIndex = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public int getFolderMusicAssociationFrameCount() {
        return ((Integer) jdy.d(Integer.valueOf(this.mFolderMusicAssociationFrameCount))).intValue();
    }

    public void setFolderMusicAssociationFrameCount(int i) {
        this.mFolderMusicAssociationFrameCount = ((Integer) jdy.d(Integer.valueOf(i))).intValue();
    }

    public List<Integer> getMusicIndexList() {
        return (List) jdy.d(this.mMusicIndexList);
    }

    public void setMusicIndexList(List<Integer> list) {
        this.mMusicIndexList = (List) jdy.d(list);
        setFolderMusicCount(list.size());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeString(this.mFolderName);
        parcel.writeInt(this.mFolderIndex);
        parcel.writeInt(this.mFolderMusicAssociationFrameCount);
        parcel.writeTypedList(this.mMusicStructList);
        parcel.writeInt(this.mFolderMusicCount);
        parcel.writeList(this.mMusicIndexList);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder(16);
        sb.append(" folderName:");
        sb.append(this.mFolderName);
        sb.append(" folderIndex:");
        sb.append(this.mFolderIndex);
        sb.append(" folderMusicAssociationFrameCount:");
        sb.append(this.mFolderMusicAssociationFrameCount);
        sb.append(" folderMusicCount:");
        sb.append(this.mFolderMusicCount);
        sb.append(" {");
        Iterator<MusicStruct> it = this.mMusicStructList.iterator();
        while (it.hasNext()) {
            sb.append(it.next().toString());
        }
        sb.append("}");
        return sb.toString();
    }
}
