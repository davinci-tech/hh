package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class Cover implements Parcelable {
    public static final Parcelable.Creator<Cover> CREATOR = new Parcelable.Creator<Cover>() { // from class: com.huawei.pluginfitnessadvice.Cover.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: clN_, reason: merged with bridge method [inline-methods] */
        public Cover createFromParcel(Parcel parcel) {
            return new Cover(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public Cover[] newArray(int i) {
            return new Cover[i];
        }
    };
    private static final String TAG = "Cover";

    @SerializedName("coordinates")
    private List<Coordinate> mCoordinates;

    @SerializedName(CommonConstant.KEY_GENDER)
    private int mGender;

    @SerializedName("id")
    private String mId;

    @SerializedName("url")
    private String mUrl;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Cover() {
    }

    protected Cover(Parcel parcel) {
        if (parcel == null) {
            LogUtil.h(TAG, "Cover inParcel == null");
            return;
        }
        this.mId = parcel.readString();
        this.mUrl = parcel.readString();
        this.mGender = parcel.readInt();
        ArrayList arrayList = new ArrayList();
        this.mCoordinates = arrayList;
        parcel.readList(arrayList, Coordinate.class.getClassLoader());
    }

    public String getId() {
        return this.mId;
    }

    public void setId(String str) {
        this.mId = str;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public void saveUrl(String str) {
        this.mUrl = str;
    }

    public int getGender() {
        return this.mGender;
    }

    public void saveGender(int i) {
        this.mGender = i;
    }

    public List<Coordinate> getCoordinates() {
        return this.mCoordinates;
    }

    public void setCoordinates(List<Coordinate> list) {
        this.mCoordinates = list;
    }

    public String toString() {
        return new Gson().toJson(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            LogUtil.h(TAG, "writeToParcel destParcel == null");
            return;
        }
        parcel.writeString(this.mId);
        parcel.writeString(this.mUrl);
        parcel.writeInt(this.mGender);
        parcel.writeList(this.mCoordinates);
    }
}
