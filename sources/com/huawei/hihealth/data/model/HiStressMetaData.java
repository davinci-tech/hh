package com.huawei.hihealth.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hihealth.data.constant.HiDataUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class HiStressMetaData implements Parcelable {
    public static final Parcelable.Creator<HiStressMetaData> CREATOR = new Parcelable.Creator<HiStressMetaData>() { // from class: com.huawei.hihealth.data.model.HiStressMetaData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HiStressMetaData createFromParcel(Parcel parcel) {
            return new HiStressMetaData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HiStressMetaData[] newArray(int i) {
            return new HiStressMetaData[i];
        }
    };
    private Integer accFlag;
    private Integer algorithmVer;
    private Integer calibFlag;
    private Integer devNo;
    private Long endTime;
    private List<Float> feature;
    private List<String> featureAtts;
    private Integer grade;
    private Integer measureType;
    private Integer ppgFlag;
    private Integer score;
    private Long startTime;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HiStressMetaData() {
    }

    protected HiStressMetaData(Parcel parcel) {
        this.startTime = (Long) parcel.readValue(Long.class.getClassLoader());
        this.endTime = (Long) parcel.readValue(Long.class.getClassLoader());
        this.algorithmVer = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.devNo = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.score = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.grade = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.calibFlag = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.measureType = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.accFlag = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.ppgFlag = (Integer) parcel.readValue(Integer.class.getClassLoader());
        ArrayList arrayList = new ArrayList(10);
        this.feature = arrayList;
        parcel.readList(arrayList, Float.class.getClassLoader());
        this.featureAtts = parcel.createStringArrayList();
    }

    public long fetchStressStartTime() {
        return HiDataUtil.d(this.startTime);
    }

    public void configStressStartTime(long j) {
        this.startTime = Long.valueOf(j);
    }

    public long fetchStressEndTime() {
        return HiDataUtil.d(this.endTime);
    }

    public void configStressEndTime(long j) {
        this.endTime = Long.valueOf(j);
    }

    public int fetchStressAlgorithmVer() {
        return HiDataUtil.c(this.algorithmVer);
    }

    public void configStressAlgorithmVer(int i) {
        this.algorithmVer = Integer.valueOf(i);
    }

    public int fetchStressDevNo() {
        return HiDataUtil.c(this.devNo);
    }

    public void configStressDevNo(int i) {
        this.devNo = Integer.valueOf(i);
    }

    public int fetchStressScore() {
        return HiDataUtil.c(this.score);
    }

    public void configStressScore(int i) {
        this.score = Integer.valueOf(i);
    }

    public int fetchStressGrade() {
        return HiDataUtil.c(this.grade);
    }

    public void configStressGrade(int i) {
        this.grade = Integer.valueOf(i);
    }

    public int fetchStressCalibFlag() {
        return HiDataUtil.c(this.calibFlag);
    }

    public void configStressCalibFlag(int i) {
        this.calibFlag = Integer.valueOf(i);
    }

    public int fetchStressMeasureType() {
        return HiDataUtil.c(this.measureType);
    }

    public void configStressMeasureType(int i) {
        this.measureType = Integer.valueOf(i);
    }

    public int fetchStressAccFlag() {
        return HiDataUtil.c(this.accFlag);
    }

    public void configStressAccFlag(int i) {
        this.accFlag = Integer.valueOf(i);
    }

    public int fetchStressPpgFlag() {
        return HiDataUtil.c(this.ppgFlag);
    }

    public void configStressPpgFlag(int i) {
        this.ppgFlag = Integer.valueOf(i);
    }

    public List<Float> fetchStressFeature() {
        return this.feature;
    }

    public void configStressFeature(List<Float> list) {
        this.feature = list;
    }

    public List<String> fetchStressFeatureAtts() {
        return this.featureAtts;
    }

    public void configStressFeatureAtts(List<String> list) {
        this.featureAtts = list;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeValue(this.startTime);
        parcel.writeValue(this.endTime);
        parcel.writeValue(this.algorithmVer);
        parcel.writeValue(this.devNo);
        parcel.writeValue(this.score);
        parcel.writeValue(this.grade);
        parcel.writeValue(this.calibFlag);
        parcel.writeValue(this.measureType);
        parcel.writeValue(this.accFlag);
        parcel.writeValue(this.ppgFlag);
        parcel.writeList(this.feature);
        parcel.writeStringList(this.featureAtts);
    }

    public String toString() {
        return "HiStressMetaData{startTime=" + this.startTime + ", endTime=" + this.endTime + ", algorithmVer=" + this.algorithmVer + ", devNo=" + this.devNo + ", score=" + this.score + ", grade=" + this.grade + ", calibFlag=" + this.calibFlag + ", measureType=" + this.measureType + ", accFlag=" + this.accFlag + ", ppgFlag=" + this.ppgFlag + ", feature=" + this.feature + ", featureAtts=" + this.featureAtts + '}';
    }
}
