package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes6.dex */
public class Classify implements Parcelable {
    public static final Parcelable.Creator<Classify> CREATOR = new Parcelable.Creator<Classify>() { // from class: com.huawei.pluginfitnessadvice.Classify.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: clI_, reason: merged with bridge method [inline-methods] */
        public Classify createFromParcel(Parcel parcel) {
            return new Classify(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public Classify[] newArray(int i) {
            return new Classify[i];
        }
    };
    private static final String TAG = "Classify";

    @SerializedName("classifyId")
    private int mClassifyId;

    @SerializedName("classifyName")
    private String mClassifyName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Classify() {
    }

    public Classify(int i, String str) {
        this.mClassifyId = i;
        this.mClassifyName = str;
    }

    protected Classify(Parcel parcel) {
        if (parcel == null) {
            LogUtil.h(TAG, "Attribute in == null");
        } else {
            this.mClassifyId = parcel.readInt();
            this.mClassifyName = parcel.readString();
        }
    }

    public int getClassifyId() {
        return this.mClassifyId;
    }

    public void saveClassifyId(int i) {
        this.mClassifyId = i;
    }

    public String getClassifyName() {
        return this.mClassifyName;
    }

    public void saveClassifyName(String str) {
        this.mClassifyName = str;
    }

    public String toString() {
        return new Gson().toJson(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            LogUtil.h(TAG, "writeToParcel dest == null");
        } else {
            parcel.writeInt(this.mClassifyId);
            parcel.writeString(this.mClassifyName);
        }
    }
}
