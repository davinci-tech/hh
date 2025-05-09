package com.huawei.basichealthmodel.medicine;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;

/* loaded from: classes3.dex */
public class MedicineRecord implements Parcelable {
    public static final Parcelable.Creator<MedicineRecord> CREATOR = new Parcelable.Creator<MedicineRecord>() { // from class: com.huawei.basichealthmodel.medicine.MedicineRecord.2
        @Override // android.os.Parcelable.Creator
        /* renamed from: kI_, reason: merged with bridge method [inline-methods] */
        public MedicineRecord createFromParcel(Parcel parcel) {
            return new MedicineRecord(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public MedicineRecord[] newArray(int i) {
            return new MedicineRecord[i];
        }
    };
    private long mEndTime;

    @SerializedName("medicationMark")
    private String mMedicationMark;

    @SerializedName("medicationName")
    private String mMedicationName;

    @SerializedName("medicationPunchDrug")
    private int mMedicationPunchDrug;

    @SerializedName("medicationPunchExpectedTime")
    private int mMedicineTime;

    @SerializedName("medicationPunchTaskId")
    private int mRecordId;

    @SerializedName("medicationPunchTime")
    private long mRecordTime;

    @SerializedName("medicationPunchSource")
    private int mSource;
    private long mStartTime;

    @SerializedName("medicationPunchStatus")
    private int mStatus;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MedicineRecord() {
    }

    private MedicineRecord(Parcel parcel) {
        if (parcel == null) {
            return;
        }
        this.mRecordId = parcel.readInt();
        this.mMedicineTime = parcel.readInt();
        this.mRecordTime = parcel.readLong();
        this.mStatus = parcel.readInt();
        this.mSource = parcel.readInt();
        this.mStartTime = parcel.readLong();
        this.mEndTime = parcel.readLong();
        this.mMedicationName = parcel.readString();
        this.mMedicationMark = parcel.readString();
        this.mMedicationPunchDrug = parcel.readInt();
    }

    public int getRecordId() {
        return this.mRecordId;
    }

    public void setRecordId(int i) {
        this.mRecordId = i;
    }

    public int getMedicineTime() {
        return this.mMedicineTime;
    }

    public void setMedicineTime(int i) {
        this.mMedicineTime = i;
    }

    public long getRecordTime() {
        return this.mRecordTime;
    }

    public void setRecordTime(long j) {
        this.mRecordTime = j;
    }

    public int getStatus() {
        return this.mStatus;
    }

    public void setStatus(int i) {
        this.mStatus = i;
    }

    public int getSource() {
        return this.mSource;
    }

    public void setSource(int i) {
        this.mSource = i;
    }

    public long getStartTime() {
        return this.mStartTime;
    }

    public void setStartTime(long j) {
        this.mStartTime = j;
    }

    public long getEndTime() {
        return this.mEndTime;
    }

    public void setEndTime(long j) {
        this.mEndTime = j;
    }

    public String getName() {
        return this.mMedicationName;
    }

    public void setName(String str) {
        this.mMedicationName = str;
    }

    public String getMark() {
        return this.mMedicationMark;
    }

    public void setMark(String str) {
        this.mMedicationMark = str;
    }

    public int getDrug() {
        return this.mMedicationPunchDrug;
    }

    public void setDrug(int i) {
        this.mMedicationPunchDrug = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeInt(this.mRecordId);
        parcel.writeInt(this.mMedicineTime);
        parcel.writeLong(this.mRecordTime);
        parcel.writeInt(this.mStatus);
        parcel.writeInt(this.mSource);
        parcel.writeLong(this.mStartTime);
        parcel.writeLong(this.mEndTime);
        parcel.writeString(this.mMedicationName);
        parcel.writeString(this.mMedicationMark);
        parcel.writeInt(this.mMedicationPunchDrug);
    }

    public String toString() {
        return "MedicineRecord{mRecordId=" + this.mRecordId + ", mMedicineTime=" + this.mMedicineTime + ", mRecordTime=" + this.mRecordTime + ", mStatus=" + this.mStatus + ", mSource=" + this.mSource + ", mStartTime=" + this.mStartTime + ", mEndTime=" + this.mEndTime + ", mMedicationName=" + this.mMedicationName + ", mMedicationMark=" + this.mMedicationMark + ", mMedicationPunchDrug=" + this.mMedicationPunchDrug + "}";
    }
}
