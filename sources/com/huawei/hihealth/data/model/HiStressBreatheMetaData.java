package com.huawei.hihealth.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hihealth.data.constant.HiDataUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class HiStressBreatheMetaData extends HiCommonStressMetaData {
    public static final Parcelable.Creator<HiStressBreatheMetaData> CREATOR = new Parcelable.Creator<HiStressBreatheMetaData>() { // from class: com.huawei.hihealth.data.model.HiStressBreatheMetaData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HiStressBreatheMetaData createFromParcel(Parcel parcel) {
            return new HiStressBreatheMetaData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HiStressBreatheMetaData[] newArray(int i) {
            return new HiStressBreatheMetaData[i];
        }
    };
    private List<Float> subBalance;
    private List<Integer> subGrade;
    private List<Integer> subScore;
    private Float totalBalance;
    private Integer totalGrade;
    private Integer totalScore;

    @Override // com.huawei.hihealth.data.model.HiCommonStressMetaData, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HiStressBreatheMetaData() {
    }

    protected HiStressBreatheMetaData(Parcel parcel) {
        super(parcel);
        this.totalScore = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.totalGrade = (Integer) parcel.readValue(Integer.class.getClassLoader());
        this.totalBalance = (Float) parcel.readValue(Float.class.getClassLoader());
        ArrayList arrayList = new ArrayList(10);
        this.subScore = arrayList;
        parcel.readList(arrayList, Integer.class.getClassLoader());
        ArrayList arrayList2 = new ArrayList(10);
        this.subGrade = arrayList2;
        parcel.readList(arrayList2, Integer.class.getClassLoader());
        ArrayList arrayList3 = new ArrayList(10);
        this.subBalance = arrayList3;
        parcel.readList(arrayList3, Float.class.getClassLoader());
    }

    public int fetchBreatheMaxHeartrate() {
        return HiDataUtil.c(this.maxHeartRate);
    }

    public void configBreatheMaxHeartrate(int i) {
        this.maxHeartRate = Integer.valueOf(i);
    }

    public int fetchBreatheMinHeartrate() {
        return HiDataUtil.c(this.minHeartRate);
    }

    public void configBreatheMinHeartrate(int i) {
        this.minHeartRate = Integer.valueOf(i);
    }

    public int fetchBreatheMeanHeartrate() {
        return HiDataUtil.c(this.meanHeartRate);
    }

    public void configBreatheMeanHeartrate(int i) {
        this.meanHeartRate = Integer.valueOf(i);
    }

    public int fetchBreatheFrontPressure() {
        return HiDataUtil.c(this.frontPressure);
    }

    public void configBreatheFrontPressure(int i) {
        this.frontPressure = Integer.valueOf(i);
    }

    public int fetchBreatheRearPressure() {
        return HiDataUtil.c(this.rearPressure);
    }

    public void configBreatheRearPressure(int i) {
        this.rearPressure = Integer.valueOf(i);
    }

    public int fetchBreatheDeltaPressure() {
        return HiDataUtil.c(this.deltaPressure);
    }

    public void configBreatheDeltaPressure(int i) {
        this.deltaPressure = Integer.valueOf(i);
    }

    public long fetchBreatheStartTime() {
        return HiDataUtil.d(this.startTime);
    }

    public void configBreatheStartTime(long j) {
        this.startTime = Long.valueOf(j);
    }

    public long fetchBreatheEndTime() {
        return HiDataUtil.d(this.endTime);
    }

    public void configBreatheEndTime(long j) {
        this.endTime = Long.valueOf(j);
    }

    public int fetchBreatheAlgorithmVer() {
        return HiDataUtil.c(this.algorithmVer);
    }

    public void configBreatheAlgorithmVer(int i) {
        this.algorithmVer = Integer.valueOf(i);
    }

    public int fetchBreatheDevNo() {
        return HiDataUtil.c(this.devNo);
    }

    public void configBreatheDevNo(int i) {
        this.devNo = Integer.valueOf(i);
    }

    public int fetchBreatheTotalScore() {
        return HiDataUtil.c(this.totalScore);
    }

    public void configBreatheTotalScore(int i) {
        this.totalScore = Integer.valueOf(i);
    }

    public int fetchBreatheTotalGrade() {
        return HiDataUtil.c(this.totalGrade);
    }

    public void configBreatheTotalGrade(int i) {
        this.totalGrade = Integer.valueOf(i);
    }

    public float fetchBreatheTotalBalance() {
        return HiDataUtil.b(this.totalBalance);
    }

    public void configBreatheTotalBalance(float f) {
        this.totalBalance = Float.valueOf(f);
    }

    public List<Integer> fetchBreatheSubScore() {
        return this.subScore;
    }

    public void configBreatheSubScore(List<Integer> list) {
        this.subScore = list;
    }

    public List<Integer> fetchBreatheSubGrade() {
        return this.subGrade;
    }

    public void configBreatheSubGrade(List<Integer> list) {
        this.subGrade = list;
    }

    public List<Float> fetchBreatheSubBalance() {
        return this.subBalance;
    }

    public void configBreatheSubBalance(List<Float> list) {
        this.subBalance = list;
    }

    @Override // com.huawei.hihealth.data.model.HiCommonStressMetaData, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeValue(this.totalScore);
        parcel.writeValue(this.totalGrade);
        parcel.writeValue(this.totalBalance);
        parcel.writeList(this.subScore);
        parcel.writeList(this.subGrade);
        parcel.writeList(this.subBalance);
    }
}
