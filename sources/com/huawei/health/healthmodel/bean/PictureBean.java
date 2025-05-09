package com.huawei.health.healthmodel.bean;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.huawei.featureuserprofile.route.hwgpxmodel.Wpt;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;

/* loaded from: classes3.dex */
public class PictureBean implements Parcelable {
    public static final Parcelable.Creator<PictureBean> CREATOR = new Parcelable.Creator<PictureBean>() { // from class: com.huawei.health.healthmodel.bean.PictureBean.5
        @Override // android.os.Parcelable.Creator
        /* renamed from: ZF_, reason: merged with bridge method [inline-methods] */
        public PictureBean createFromParcel(Parcel parcel) {
            return new PictureBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public PictureBean[] newArray(int i) {
            return new PictureBean[i];
        }
    };

    @SerializedName(Wpt.MODE)
    private int mMode;

    @SerializedName(BleConstants.KEY_PATH)
    private String mPath;

    @SerializedName(ParsedFieldTag.KAKA_TASK_SCENARIO)
    private int mScenario;

    @SerializedName("size")
    private long mSize;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PictureBean() {
    }

    public PictureBean(Parcel parcel) {
        if (parcel == null) {
            return;
        }
        this.mPath = parcel.readString();
        this.mSize = parcel.readLong();
        this.mMode = parcel.readInt();
        this.mScenario = parcel.readInt();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeString(this.mPath);
        parcel.writeLong(this.mSize);
        parcel.writeInt(this.mMode);
        parcel.writeInt(this.mScenario);
    }

    public String getPath() {
        return this.mPath;
    }

    public void setPath(String str) {
        this.mPath = str;
    }

    public long getSize() {
        return this.mSize;
    }

    public void setSize(long j) {
        this.mSize = j;
    }

    public int getMode() {
        return this.mMode;
    }

    public void setMode(int i) {
        this.mMode = i;
    }

    public int getScenario() {
        return this.mScenario;
    }

    public void setScenario(int i) {
        this.mScenario = i;
    }

    public String toString() {
        return "PictureBean{mPath=" + this.mPath + ", mSize=" + this.mSize + ", mMode=" + this.mMode + ", mScenario=" + this.mScenario + "}";
    }
}
