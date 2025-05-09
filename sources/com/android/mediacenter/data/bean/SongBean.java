package com.android.mediacenter.data.bean;

import android.os.Parcel;
import android.os.Parcelable;

/* loaded from: classes2.dex */
public class SongBean implements Parcelable {
    public static final Parcelable.Creator<SongBean> CREATOR = new Parcelable.Creator<SongBean>() { // from class: com.android.mediacenter.data.bean.SongBean.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SongBean createFromParcel(Parcel parcel) {
            return new SongBean();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SongBean[] newArray(int i) {
            return new SongBean[i];
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
