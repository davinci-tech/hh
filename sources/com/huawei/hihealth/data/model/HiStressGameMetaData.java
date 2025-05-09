package com.huawei.hihealth.data.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hihealth.data.constant.HiDataUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class HiStressGameMetaData extends HiCommonStressMetaData {
    public static final Parcelable.Creator<HiStressGameMetaData> CREATOR = new Parcelable.Creator<HiStressGameMetaData>() { // from class: com.huawei.hihealth.data.model.HiStressGameMetaData.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HiStressGameMetaData createFromParcel(Parcel parcel) {
            return new HiStressGameMetaData(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public HiStressGameMetaData[] newArray(int i) {
            return new HiStressGameMetaData[i];
        }
    };
    private List<Float> bubbleR;
    private List<Integer> bubbleX;
    private List<Float> bubbleY;
    private List<Float> raderValue;
    private List<String> raderValueAtts;
    private List<Integer> statePercent;
    private List<String> statePercentAtts;
    private Integer totalTime;

    @Override // com.huawei.hihealth.data.model.HiCommonStressMetaData, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public HiStressGameMetaData() {
    }

    protected HiStressGameMetaData(Parcel parcel) {
        super(parcel);
        this.totalTime = (Integer) parcel.readValue(Integer.class.getClassLoader());
        ArrayList arrayList = new ArrayList(10);
        this.statePercent = arrayList;
        parcel.readList(arrayList, Integer.class.getClassLoader());
        this.statePercentAtts = parcel.createStringArrayList();
        ArrayList arrayList2 = new ArrayList(10);
        this.raderValue = arrayList2;
        parcel.readList(arrayList2, Float.class.getClassLoader());
        this.raderValueAtts = parcel.createStringArrayList();
        ArrayList arrayList3 = new ArrayList(10);
        this.bubbleX = arrayList3;
        parcel.readList(arrayList3, Integer.class.getClassLoader());
        ArrayList arrayList4 = new ArrayList(10);
        this.bubbleY = arrayList4;
        parcel.readList(arrayList4, Float.class.getClassLoader());
        ArrayList arrayList5 = new ArrayList(10);
        this.bubbleR = arrayList5;
        parcel.readList(arrayList5, Float.class.getClassLoader());
    }

    public int fetchGameMaxHeartrate() {
        return HiDataUtil.c(this.maxHeartRate);
    }

    public void configGameMaxHeartrate(int i) {
        this.maxHeartRate = Integer.valueOf(i);
    }

    public int fetchGameMinHeartrate() {
        return HiDataUtil.c(this.minHeartRate);
    }

    public void configGameMinHeartrate(int i) {
        this.minHeartRate = Integer.valueOf(i);
    }

    public int fetchGameMeanHeartrate() {
        return HiDataUtil.c(this.meanHeartRate);
    }

    public void configGameMeanHeartrate(int i) {
        this.meanHeartRate = Integer.valueOf(i);
    }

    public int fetchGameFrontPressure() {
        return HiDataUtil.c(this.frontPressure);
    }

    public void configGameFrontPressure(int i) {
        this.frontPressure = Integer.valueOf(i);
    }

    public int fetchGameRearPressure() {
        return HiDataUtil.c(this.rearPressure);
    }

    public void configGameRearPressure(int i) {
        this.rearPressure = Integer.valueOf(i);
    }

    public int fetchGameDeltaPressure() {
        return HiDataUtil.c(this.deltaPressure);
    }

    public void configGameDeltaPressure(int i) {
        this.deltaPressure = Integer.valueOf(i);
    }

    public long fetchGameStartTime() {
        return HiDataUtil.d(this.startTime);
    }

    public void configGameStartTime(long j) {
        this.startTime = Long.valueOf(j);
    }

    public long fetchGameEndTime() {
        return HiDataUtil.d(this.endTime);
    }

    public void configGameEndTime(long j) {
        this.endTime = Long.valueOf(j);
    }

    public int fetchGameAlgorithmVer() {
        return HiDataUtil.c(this.algorithmVer);
    }

    public void configGameAlgorithmVer(int i) {
        this.algorithmVer = Integer.valueOf(i);
    }

    public int fetchGameDevNo() {
        return HiDataUtil.c(this.devNo);
    }

    public void configGameDevNo(int i) {
        this.devNo = Integer.valueOf(i);
    }

    public int fetchGameTotalTime() {
        return HiDataUtil.c(this.totalTime);
    }

    public void configGameTotalTime(int i) {
        this.totalTime = Integer.valueOf(i);
    }

    public List<Integer> fetchGameStatePercent() {
        return this.statePercent;
    }

    public void configGameStatePercent(List<Integer> list) {
        this.statePercent = list;
    }

    public List<String> fetchGameStatePercentAtts() {
        return this.statePercentAtts;
    }

    public void configGameStatePercentAtts(List<String> list) {
        this.statePercentAtts = list;
    }

    public List<Float> fetchGameRaderValue() {
        return this.raderValue;
    }

    public void configGameRaderValue(List<Float> list) {
        this.raderValue = list;
    }

    public List<String> fetchGameRaderValueAtts() {
        return this.raderValueAtts;
    }

    public void configGameRaderValueAtts(List<String> list) {
        this.raderValueAtts = list;
    }

    public List<Integer> fetchGameBubbleX() {
        return this.bubbleX;
    }

    public void configGameBubbleX(List<Integer> list) {
        this.bubbleX = list;
    }

    public List<Float> fetchGameBubbleY() {
        return this.bubbleY;
    }

    public void configGameBubbleY(List<Float> list) {
        this.bubbleY = list;
    }

    public List<Float> fetchGameBubbleR() {
        return this.bubbleR;
    }

    public void configGameBubbleR(List<Float> list) {
        this.bubbleR = list;
    }

    @Override // com.huawei.hihealth.data.model.HiCommonStressMetaData, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeValue(this.totalTime);
        parcel.writeList(this.statePercent);
        parcel.writeStringList(this.statePercentAtts);
        parcel.writeList(this.raderValue);
        parcel.writeStringList(this.raderValueAtts);
        parcel.writeList(this.bubbleX);
        parcel.writeList(this.bubbleY);
        parcel.writeList(this.bubbleR);
    }
}
