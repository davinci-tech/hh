package com.huawei.healthcloud.plugintrack.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.amap.api.maps.model.LatLng;

/* loaded from: classes8.dex */
public class HotTrackInfo implements Parcelable {
    public static final Parcelable.Creator<HotTrackInfo> CREATOR = new Parcelable.Creator<HotTrackInfo>() { // from class: com.huawei.healthcloud.plugintrack.model.HotTrackInfo.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: aVG_, reason: merged with bridge method [inline-methods] */
        public HotTrackInfo createFromParcel(Parcel parcel) {
            return new HotTrackInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public HotTrackInfo[] newArray(int i) {
            return new HotTrackInfo[i];
        }
    };
    private String avatar;
    private LatLng[] coords;
    private String id;
    private String name;
    private int punchCount;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HotTrackInfo() {
    }

    public HotTrackInfo(Parcel parcel) {
        this.id = parcel.readString();
        this.punchCount = parcel.readInt();
        this.name = parcel.readString();
        this.avatar = parcel.readString();
        this.coords = (LatLng[]) parcel.createTypedArray(LatLng.CREATOR);
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getId() {
        return this.id;
    }

    public void setPunchCount(int i) {
        this.punchCount = i;
    }

    public int getPunchCount() {
        return this.punchCount;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setCoords(LatLng[] latLngArr) {
        this.coords = latLngArr;
    }

    public LatLng[] getCoords() {
        return this.coords;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.id);
        parcel.writeInt(this.punchCount);
        parcel.writeString(this.name);
        parcel.writeString(this.avatar);
        parcel.writeTypedArray(this.coords, i);
    }
}
