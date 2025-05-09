package com.huawei.pluginfitnessadvice;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.Serializable;

/* loaded from: classes6.dex */
public class Attribute implements Parcelable, Serializable {
    public static final Parcelable.Creator<Attribute> CREATOR = new Parcelable.Creator<Attribute>() { // from class: com.huawei.pluginfitnessadvice.Attribute.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: clB_, reason: merged with bridge method [inline-methods] */
        public Attribute createFromParcel(Parcel parcel) {
            return new Attribute(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public Attribute[] newArray(int i) {
            return new Attribute[i];
        }
    };
    private static final String TAG = "Attribute";
    private static final long serialVersionUID = 6971252714553133879L;

    @SerializedName("id")
    private String mId;

    @SerializedName("name")
    private String mName;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Attribute() {
    }

    public Attribute(int i, String str) {
        this.mId = String.valueOf(i);
        this.mName = str;
    }

    public Attribute(String str, String str2) {
        this.mId = str;
        this.mName = str2;
    }

    protected Attribute(Parcel parcel) {
        if (parcel == null) {
            LogUtil.h(TAG, "Attribute in == null");
        } else {
            this.mId = parcel.readString();
            this.mName = parcel.readString();
        }
    }

    public String getId() {
        return this.mId;
    }

    public void saveId(String str) {
        this.mId = str;
    }

    public void setId(String str) {
        this.mId = str;
    }

    public String getName() {
        return this.mName;
    }

    public void saveName(String str) {
        this.mName = str;
    }

    public void setName(String str) {
        this.mName = str;
    }

    public String toString() {
        return new Gson().toJson(this);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            LogUtil.h(TAG, "writeToParcel dest == null");
        } else {
            parcel.writeString(this.mId);
            parcel.writeString(this.mName);
        }
    }
}
