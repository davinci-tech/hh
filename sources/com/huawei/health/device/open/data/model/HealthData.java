package com.huawei.health.device.open.data.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* loaded from: classes3.dex */
public class HealthData implements Serializable, Cloneable {
    public static final int ALTITUDE = 2560;
    public static final int BLOODPRESURE = 1536;
    public static final int BLOODSUGAR = 1280;
    public static final int BLOODSUGAR_BEFORE_DAWN = 1288;
    public static final int BLOODSUGAR_SLEEP_BEFORE = 1287;
    private static final int DURATION_TYPE_SET_INITIAL_CAPACITY = 2;
    public static final int ECG = 3072;
    private static final int HEALTH_DATA_TYPE_SET_INITIAL_CAPACITY = 11;
    public static final int HEARTRATE = 2048;
    private static final int HIGH_BYTE_ORDER_TRANSFORMATION_CONSTANT = 65280;
    public static final double INVALID_VALUES_DOUBLE = Double.MIN_VALUE;
    public static final float INVALID_VALUES_FLOAT = Float.MIN_VALUE;
    public static final int INVALID_VALUES_INT = Integer.MIN_VALUE;
    public static final long INVALID_VALUES_LONG = Long.MIN_VALUE;
    public static final short INVALID_VALUES_SHORT = Short.MIN_VALUE;
    public static final int MEASUREMENT_BLOOD_SUGAR = 8;
    public static final int MEASUREMENT_CALORIES = 3;
    public static final int MEASUREMENT_DIASTOLIC = 7;
    public static final int MEASUREMENT_DISTANCES = 2;
    public static final int MEASUREMENT_DURATION = 4;
    private static final int MEASUREMENT_MAP_INITIAL_CAPACITY = 5;
    public static final int MEASUREMENT_RECORD = 5;
    public static final int MEASUREMENT_STEPS = 1;
    public static final int MEASUREMENT_SYSTOLIC = 6;
    public static final int MEASUREMENT_WEIGHT = 9;
    public static final int MOTION_PATH = 1024;
    public static final int SLEEP = 512;
    public static final int SPORT = 256;
    public static final int STAND = 2816;
    public static final int TIMELINE = 768;
    public static final int WEIGHT = 1792;
    private static final long serialVersionUID = -6871919384088407514L;
    private long mEndTime;
    private long mHuid;
    private String mRecordId;
    private Object mReferData;
    private long mStartTime;
    private int mSubType;
    private int mType;
    private static Set<Integer> sDurationTypeSet = new HashSet(2);
    private static Map<Integer, int[]> sMeasurementMap = new HashMap(5);
    private static Set<Integer> sHealthDataTypeSet = new HashSet(11);

    public static int getDataTypeBySubType(int i) {
        return i & 65280;
    }

    public void init() {
    }

    static {
        sDurationTypeSet.add(256);
        sDurationTypeSet.add(512);
        sMeasurementMap.put(256, new int[]{1, 2, 3, 4, 5});
        sMeasurementMap.put(512, new int[]{4});
        Map<Integer, int[]> map = sMeasurementMap;
        Integer valueOf = Integer.valueOf(BLOODPRESURE);
        map.put(valueOf, new int[]{6, 7, 5});
        sMeasurementMap.put(1280, new int[]{8, 5});
        Map<Integer, int[]> map2 = sMeasurementMap;
        Integer valueOf2 = Integer.valueOf(WEIGHT);
        map2.put(valueOf2, new int[]{9, 5});
        sHealthDataTypeSet.add(256);
        sHealthDataTypeSet.add(512);
        sHealthDataTypeSet.add(1024);
        sHealthDataTypeSet.add(1280);
        sHealthDataTypeSet.add(valueOf);
        sHealthDataTypeSet.add(valueOf2);
        sHealthDataTypeSet.add(Integer.valueOf(TIMELINE));
        sHealthDataTypeSet.add(2048);
        sHealthDataTypeSet.add(Integer.valueOf(STAND));
        sHealthDataTypeSet.add(Integer.valueOf(ALTITUDE));
        sHealthDataTypeSet.add(Integer.valueOf(ECG));
    }

    public static boolean isDurationType(int i) {
        return sDurationTypeSet.contains(Integer.valueOf(getDataTypeBySubType(i)));
    }

    public String getRecordId() {
        return this.mRecordId;
    }

    public void setRecordId(String str) {
        this.mRecordId = str;
    }

    public int getType() {
        return this.mType;
    }

    public void setType(int i) {
        this.mType = i;
    }

    public void setSubType(int i) {
        this.mSubType = i;
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

    public boolean isDuration() {
        int i = this.mSubType;
        if (i != 0) {
            return isDurationType(i);
        }
        return isDurationType(this.mType);
    }

    public void setReferData(Object obj) {
        this.mReferData = obj;
    }

    public String toString() {
        return "HealthData [huid=" + this.mHuid + ", type=" + this.mType + ", subType=" + this.mSubType + ", device=, recordId=" + this.mRecordId + ", startTime=" + this.mStartTime + ", endTime=" + this.mEndTime + ", location=, referData=" + this.mReferData + "]";
    }

    @Override // 
    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public HealthData mo76clone() {
        try {
            if (super.clone() instanceof HealthData) {
                return (HealthData) super.clone();
            }
            return new HealthData();
        } catch (CloneNotSupportedException unused) {
            return new HealthData();
        }
    }
}
