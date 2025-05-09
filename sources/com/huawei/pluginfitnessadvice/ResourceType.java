package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes6.dex */
public class ResourceType implements Parcelable {
    public static final Parcelable.Creator<ResourceType> CREATOR = new Parcelable.Creator<ResourceType>() { // from class: com.huawei.pluginfitnessadvice.ResourceType.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: cma_, reason: merged with bridge method [inline-methods] */
        public ResourceType createFromParcel(Parcel parcel) {
            return new ResourceType(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public ResourceType[] newArray(int i) {
            return new ResourceType[i];
        }
    };
    private static final String TAG = "Suggestion_ResourceType";

    @SerializedName("resourceCode")
    private int mResourceCode;

    @SerializedName("subResourceCode")
    private int mSubResourceCode;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    private ResourceType(Parcel parcel) {
        this.mResourceCode = parcel.readInt();
        this.mSubResourceCode = parcel.readInt();
    }

    public int getResourceCode() {
        return this.mResourceCode;
    }

    public void setResourceCode(int i) {
        this.mResourceCode = i;
    }

    public int getSubResourceCode() {
        return this.mSubResourceCode;
    }

    public void setSubResourceCode(int i) {
        this.mSubResourceCode = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            LogUtil.h(TAG, "writeToParcel dest == null");
        } else {
            parcel.writeInt(this.mResourceCode);
            parcel.writeInt(this.mSubResourceCode);
        }
    }

    public String toString() {
        return new Gson().toJson(this);
    }
}
