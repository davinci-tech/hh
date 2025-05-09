package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes6.dex */
public class BelongInfo implements Parcelable {
    public static final Parcelable.Creator<BelongInfo> CREATOR = new Parcelable.Creator<BelongInfo>() { // from class: com.huawei.pluginfitnessadvice.BelongInfo.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: clF_, reason: merged with bridge method [inline-methods] */
        public BelongInfo createFromParcel(Parcel parcel) {
            return new BelongInfo(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public BelongInfo[] newArray(int i) {
            return new BelongInfo[i];
        }
    };
    public static final int PLAN = 1;
    public static final int SERIES_COURSE = 0;
    private static final String TAG = "Suggestion_BelongInfo";

    @SerializedName("commodityFlag")
    private Integer mCommodityFlag;

    @SerializedName("id")
    private String mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("type")
    private int mType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private BelongInfo(Parcel parcel) {
        this.mType = parcel.readInt();
        this.mId = parcel.readString();
        this.mName = parcel.readString();
        this.mCommodityFlag = Integer.valueOf(parcel.readInt());
    }

    public int getType() {
        return this.mType;
    }

    public BelongInfo setType(int i) {
        this.mType = i;
        return this;
    }

    public String getId() {
        return this.mId;
    }

    public BelongInfo setId(String str) {
        this.mId = str;
        return this;
    }

    public String getName() {
        return this.mName;
    }

    public BelongInfo setName(String str) {
        this.mName = str;
        return this;
    }

    public Integer getCommodityFlag() {
        return this.mCommodityFlag;
    }

    public BelongInfo setCommodityFlag(int i) {
        this.mCommodityFlag = Integer.valueOf(i);
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            LogUtil.h(TAG, "writeToParcel dest == null");
            return;
        }
        parcel.writeInt(this.mType);
        parcel.writeString(this.mId);
        parcel.writeString(this.mName);
        parcel.writeInt(this.mCommodityFlag.intValue());
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
