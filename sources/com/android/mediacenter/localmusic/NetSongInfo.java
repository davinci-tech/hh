package com.android.mediacenter.localmusic;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class NetSongInfo implements Parcelable {
    public static final Parcelable.Creator<NetSongInfo> CREATOR = new Parcelable.Creator<NetSongInfo>() { // from class: com.android.mediacenter.localmusic.NetSongInfo.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NetSongInfo createFromParcel(Parcel parcel) {
            return new NetSongInfo();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public NetSongInfo[] newArray(int i) {
            return new NetSongInfo[i];
        }
    };

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
    }
}
