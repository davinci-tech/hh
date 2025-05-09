package com.huawei.hihealth.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hihealth.data.constant.HiDataUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class HiStressRelaxMetaData extends HiCommonStressMetaData {
    public static final Parcelable.Creator<HiStressRelaxMetaData> CREATOR = new Parcelable.Creator<HiStressRelaxMetaData>() { // from class: com.huawei.hihealth.data.model.HiStressRelaxMetaData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HiStressRelaxMetaData createFromParcel(Parcel parcel) {
            return new HiStressRelaxMetaData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HiStressRelaxMetaData[] newArray(int i) {
            return new HiStressRelaxMetaData[i];
        }
    };
    private List<Float> feature;
    private List<String> featureAtts;
    private List<Integer> subLevelCoded;
    private Integer totalLevel;

    @Override // com.huawei.hihealth.data.model.HiCommonStressMetaData, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HiStressRelaxMetaData() {
    }

    protected HiStressRelaxMetaData(Parcel parcel) {
        super(parcel);
        this.totalLevel = (Integer) parcel.readValue(Integer.class.getClassLoader());
        ArrayList arrayList = new ArrayList(10);
        this.feature = arrayList;
        parcel.readList(arrayList, Float.class.getClassLoader());
        this.featureAtts = parcel.createStringArrayList();
        ArrayList arrayList2 = new ArrayList(10);
        this.subLevelCoded = arrayList2;
        parcel.readList(arrayList2, Integer.class.getClassLoader());
    }

    public int fetchRelaxMaxHeartrate() {
        return HiDataUtil.c(this.maxHeartRate);
    }

    public void configRelaxMaxHeartrate(int i) {
        this.maxHeartRate = Integer.valueOf(i);
    }

    public int fetchRelaxMinHeartrate() {
        return HiDataUtil.c(this.minHeartRate);
    }

    public void configRelaxMinHeartrate(int i) {
        this.minHeartRate = Integer.valueOf(i);
    }

    public int fetchRelaxMeanHeartrate() {
        return HiDataUtil.c(this.meanHeartRate);
    }

    public void configRelaxMeanHeartrate(int i) {
        this.meanHeartRate = Integer.valueOf(i);
    }

    public int fetchRelaxFrontPressure() {
        return HiDataUtil.c(this.frontPressure);
    }

    public void configRelaxFrontPressure(int i) {
        this.frontPressure = Integer.valueOf(i);
    }

    public int fetchRelaxRearPressure() {
        return HiDataUtil.c(this.rearPressure);
    }

    public void configRelaxRearPressure(int i) {
        this.rearPressure = Integer.valueOf(i);
    }

    public int fetchRelaxDeltaPressure() {
        return HiDataUtil.c(this.deltaPressure);
    }

    public void configRelaxDeltaPressure(int i) {
        this.deltaPressure = Integer.valueOf(i);
    }

    public long fetchRelaxStartTime() {
        return HiDataUtil.d(this.startTime);
    }

    public void configRelaxStartTime(long j) {
        this.startTime = Long.valueOf(j);
    }

    public long fetchRelaxEndTime() {
        return HiDataUtil.d(this.endTime);
    }

    public void configRelaxEndTime(long j) {
        this.endTime = Long.valueOf(j);
    }

    public int fetchRelaxAlgorithmVer() {
        return HiDataUtil.c(this.algorithmVer);
    }

    public void configRelaxAlgorithmVer(int i) {
        this.algorithmVer = Integer.valueOf(i);
    }

    public int fetchRelaxDevNo() {
        return HiDataUtil.c(this.devNo);
    }

    public void configRelaxDevNo(int i) {
        this.devNo = Integer.valueOf(i);
    }

    public int fetchRelaxTotalLevel() {
        return HiDataUtil.c(this.totalLevel);
    }

    public void configRelaxTotalLevel(int i) {
        this.totalLevel = Integer.valueOf(i);
    }

    public List<Float> fetchRelaxFeature() {
        return this.feature;
    }

    public void configRelaxFeature(List<Float> list) {
        this.feature = list;
    }

    public List<String> fetchRelaxFeatureAtts() {
        return this.featureAtts;
    }

    public void configRelaxFeatureAtts(List<String> list) {
        this.featureAtts = list;
    }

    public List<Integer> fetchRelaxSubLevelCoded() {
        return this.subLevelCoded;
    }

    public void configRelaxSubLevelCoded(List<Integer> list) {
        this.subLevelCoded = list;
    }

    @Override // com.huawei.hihealth.data.model.HiCommonStressMetaData, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeValue(this.totalLevel);
        parcel.writeList(this.feature);
        parcel.writeStringList(this.featureAtts);
        parcel.writeList(this.subLevelCoded);
    }
}
