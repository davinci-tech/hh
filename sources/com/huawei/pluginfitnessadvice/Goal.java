package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes6.dex */
public class Goal implements Parcelable {
    public static final Parcelable.Creator<Goal> CREATOR = new Parcelable.Creator<Goal>() { // from class: com.huawei.pluginfitnessadvice.Goal.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: clT_, reason: merged with bridge method [inline-methods] */
        public Goal createFromParcel(Parcel parcel) {
            return new Goal(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public Goal[] newArray(int i) {
            return new Goal[i];
        }
    };
    private static final String TAG = "Goal";

    @SerializedName("id")
    private String mId;

    @SerializedName("name")
    private String mName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Goal() {
    }

    protected Goal(Parcel parcel) {
        if (parcel == null) {
            LogUtil.h(TAG, "Goal in == null");
        } else {
            this.mId = parcel.readString();
            this.mName = parcel.readString();
        }
    }

    public String toString() {
        return new Gson().toJson(this);
    }

    public void setId(String str) {
        this.mId = str;
    }

    public String getId() {
        return this.mId;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public String getName() {
        return this.mName;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            LogUtil.h(TAG, "writeToParcel dest == null");
        } else {
            parcel.writeString(this.mName);
            parcel.writeString(this.mId);
        }
    }
}
