package com.huawei.basichealthmodel.medicine;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class MedicineBean implements Parcelable {
    public static final Parcelable.Creator<MedicineBean> CREATOR = new Parcelable.Creator<MedicineBean>() { // from class: com.huawei.basichealthmodel.medicine.MedicineBean.1
        @Override // android.os.Parcelable.Creator
        /* renamed from: kH_, reason: merged with bridge method [inline-methods] */
        public MedicineBean createFromParcel(Parcel parcel) {
            return new MedicineBean(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public MedicineBean[] newArray(int i) {
            return new MedicineBean[i];
        }
    };

    @SerializedName("id")
    private int mId;

    @SerializedName("name")
    private String mName;

    @SerializedName("remarks")
    private String mRemarks;

    @SerializedName("source")
    private int mSource;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MedicineBean() {
    }

    private MedicineBean(Parcel parcel) {
        if (parcel == null) {
            return;
        }
        this.mId = parcel.readInt();
        this.mName = parcel.readString();
        this.mRemarks = parcel.readString();
        this.mSource = parcel.readInt();
    }

    public int getId() {
        return this.mId;
    }

    public String getName() {
        return this.mName;
    }

    public String getRemarks() {
        return this.mRemarks;
    }

    public int getSource() {
        return this.mSource;
    }

    public String toString() {
        return "MedicineBean{mId=" + this.mId + ", mName=" + this.mName + ", mRemarks=" + this.mRemarks + ", mSource=" + this.mSource + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeInt(this.mId);
        parcel.writeString(this.mName);
        parcel.writeString(this.mRemarks);
        parcel.writeInt(this.mSource);
    }
}
