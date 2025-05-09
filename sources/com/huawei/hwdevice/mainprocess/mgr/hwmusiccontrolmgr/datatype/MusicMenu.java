package com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class MusicMenu implements Parcelable {
    public static final Parcelable.Creator<MusicMenu> CREATOR = new Parcelable.Creator<MusicMenu>() { // from class: com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicMenu.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: bHB_, reason: merged with bridge method [inline-methods] */
        public MusicMenu createFromParcel(Parcel parcel) {
            return new MusicMenu(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public MusicMenu[] newArray(int i) {
            return new MusicMenu[i];
        }
    };
    private String mMemuNewName;
    private String mMenuIndex;
    private int mMenuMusicCount;
    private String mMenuName;
    private ArrayList<MusicSong> mMusicSongsList;
    private List<Integer> mMusicStructSongIndexList;
    private int mSortStyle;
    private int mSortType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MusicMenu() {
        this.mMusicSongsList = new ArrayList<>(16);
        this.mSortType = 0;
        this.mSortStyle = 0;
        this.mMusicStructSongIndexList = new ArrayList(16);
    }

    public MusicMenu(Parcel parcel) {
        this.mMusicSongsList = new ArrayList<>(16);
        this.mSortType = 0;
        this.mSortStyle = 0;
        this.mMusicStructSongIndexList = new ArrayList(16);
        this.mMenuIndex = parcel.readString();
        this.mMenuName = parcel.readString();
        this.mMemuNewName = parcel.readString();
        this.mMusicSongsList = parcel.createTypedArrayList(MusicSong.CREATOR);
        this.mSortType = parcel.readInt();
        this.mSortStyle = parcel.readInt();
        this.mMenuMusicCount = parcel.readInt();
        ArrayList arrayList = new ArrayList(16);
        parcel.readList(arrayList, Integer.class.getClassLoader());
        this.mMusicStructSongIndexList.addAll(arrayList);
    }

    public String getMenuIndex() {
        return this.mMenuIndex;
    }

    public void setMenuIndex(String str) {
        this.mMenuIndex = str;
    }

    public String getMenuName() {
        return this.mMenuName;
    }

    public void setMenuName(String str) {
        this.mMenuName = str;
    }

    public String getMemuNewName() {
        return this.mMemuNewName;
    }

    public void setMemuNewName(String str) {
        this.mMemuNewName = str;
    }

    public ArrayList<MusicSong> getMusicSongsList() {
        return this.mMusicSongsList;
    }

    public ArrayList<String> getMusicSongsFileNameList() {
        ArrayList<String> arrayList = new ArrayList<>(16);
        ArrayList<MusicSong> arrayList2 = this.mMusicSongsList;
        if (arrayList2 == null) {
            return arrayList;
        }
        Iterator<MusicSong> it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getSongName());
        }
        return arrayList;
    }

    public int getMusicSongsNum() {
        ArrayList<MusicSong> arrayList = this.mMusicSongsList;
        if (arrayList == null) {
            return 0;
        }
        return arrayList.size();
    }

    public int getMenuMusicCount() {
        return this.mMenuMusicCount;
    }

    public void setMenuMusicCount(int i) {
        this.mMenuMusicCount = i;
    }

    public List<Integer> getMusicStructSongIndexList() {
        return this.mMusicStructSongIndexList;
    }

    public void setMusicStructSongIndexList(List<Integer> list) {
        this.mMusicStructSongIndexList = list;
    }

    public void addRealMusicCount(List<MusicSong> list) {
        this.mMusicSongsList.addAll(list);
        this.mMenuMusicCount += list.size();
    }

    public void removeRealMusicCount(List<MusicSong> list) {
        this.mMusicSongsList.removeAll(list);
        this.mMenuMusicCount -= list.size();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mMenuIndex);
        parcel.writeString(this.mMenuName);
        parcel.writeString(this.mMemuNewName);
        parcel.writeTypedList(this.mMusicSongsList);
        parcel.writeInt(this.mSortType);
        parcel.writeInt(this.mSortStyle);
        parcel.writeInt(this.mMenuMusicCount);
        parcel.writeList(this.mMusicStructSongIndexList);
    }

    public void fillMusicMenuDataByBlueTooth(MusicFolderStruct musicFolderStruct) {
        if (musicFolderStruct == null) {
            return;
        }
        setMenuName(musicFolderStruct.getFolderName());
        setMenuIndex(String.valueOf(musicFolderStruct.getFolderIndex()));
        setMenuMusicCount(musicFolderStruct.getFolderMusicCount());
        setMusicStructSongIndexList(musicFolderStruct.getMusicIndexList());
        List<MusicStruct> musicStructList = musicFolderStruct.getMusicStructList();
        if (musicStructList == null) {
            return;
        }
        for (MusicStruct musicStruct : musicStructList) {
            MusicSong musicSong = new MusicSong();
            musicSong.setFileName(musicStruct.getFileName());
            musicSong.setSongName(musicStruct.getMusicName());
            musicSong.setSongSingerName(musicStruct.getMusicSinger());
            musicSong.setSongIndex(String.valueOf(musicStruct.getMusicIndex()));
            this.mMusicSongsList.add(musicSong);
        }
    }
}
