package com.huawei.health.device.datatype;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.HashMap;

/* loaded from: classes3.dex */
public abstract class AbstractFitnessData implements Parcelable, Cloneable {
    public static final short DEFAULT_VALUE_7FFF = Short.MAX_VALUE;
    public static final short DEFAULT_VALUE_FF = 255;
    public static final int DEFAULT_VALUE_FFFF = 65535;
    public static final int DEFAULT_VALUE_NULL = -1;
    public static final int FITNESS_DATA_BUTTERY_STATUS = 21;
    public static final int FITNESS_DATA_CONTROL_POINT_RESPONSE = 7;
    public static final int FITNESS_DATA_CROSS_TRAINER_DATA = 6;
    private static final int FITNESS_DATA_DEFAULT_NUM = 20;
    public static final int FITNESS_DATA_DEFAULT_TRAINER_DATA = 99;
    public static final int FITNESS_DATA_DEVICE_INFORMATION = 5;
    public static final int FITNESS_DATA_HISTORY_DATA = 20;
    public static final int FITNESS_DATA_INDOORBIKE_DATA = 10;
    public static final int FITNESS_DATA_LOG_OPERATIONS_DATA = 30;
    public static final int FITNESS_DATA_MACHINE_FEATURE = 1;
    public static final int FITNESS_DATA_MACHINE_STATUS = 2;
    public static final int FITNESS_DATA_ROPE_CONFIG_SETTING_DATA = 26;
    public static final int FITNESS_DATA_ROPE_CONFIG_SONGS = 31;
    public static final int FITNESS_DATA_ROPE_CONFIG_SWITCH_DATA = 25;
    public static final int FITNESS_DATA_ROPE_CONFIG_VOICE_COURSE_OPERATIONS = 28;
    public static final int FITNESS_DATA_ROPE_DATA = 22;
    public static final int FITNESS_DATA_ROPE_DEVICE_INFORMATION = 23;
    public static final int FITNESS_DATA_ROPE_MODE_SETTING_DATA = 27;
    public static final int FITNESS_DATA_ROPE_TRAINING_STATUS = 24;
    public static final int FITNESS_DATA_ROWER_DATA = 11;
    public static final int FITNESS_DATA_TRAINING_STATUS = 4;
    public static final int FITNESS_DATA_TREADMILL_DATA = 3;
    public static final int FITNESS_DATA_VIDEO_COURSE_OPERATIONS_DATA = 29;
    protected HashMap<Integer, Object> mFitnessData;
    protected int mFitnessDataType;
    protected int mFlags;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public AbstractFitnessData() {
        this.mFitnessData = new HashMap<>(20);
    }

    public AbstractFitnessData(Parcel parcel) {
        this.mFitnessData = new HashMap<>(20);
        this.mFlags = parcel.readInt();
        this.mFitnessDataType = parcel.readInt();
        this.mFitnessData = parcel.readHashMap(HashMap.class.getClassLoader());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mFlags);
        parcel.writeInt(this.mFitnessDataType);
        parcel.writeMap(this.mFitnessData);
    }

    public void clearData() {
        HashMap<Integer, Object> hashMap = new HashMap<>(20);
        this.mFitnessData = hashMap;
        hashMap.clear();
    }

    public int getFitnessDataType() {
        return this.mFitnessDataType;
    }

    public void setFitnessDataType(int i) {
        this.mFitnessDataType = i;
    }

    public void setFitnessHashMap(HashMap<Integer, Object> hashMap) {
        this.mFitnessData.putAll(hashMap);
    }

    public HashMap<Integer, Object> getFitnessHashMap() {
        return this.mFitnessData;
    }

    public int getTrainingStatus() {
        Object obj = this.mFitnessData.get(10001);
        if (obj != null && (obj instanceof Integer)) {
            return ((Integer) obj).intValue();
        }
        return -1;
    }

    public void setTrainingStatus(int i) {
        this.mFitnessData.put(10001, Integer.valueOf(i));
    }

    public int getOpCode() {
        Object obj = this.mFitnessData.get(20008);
        if (obj != null && (obj instanceof Integer)) {
            return ((Integer) obj).intValue();
        }
        return -1;
    }

    public void setOpCode(int i) {
        this.mFitnessData.put(20008, Integer.valueOf(i));
    }

    public int getMachineStatusCharacteristic() {
        Object obj = this.mFitnessData.get(20009);
        if (obj != null && (obj instanceof Integer)) {
            return ((Integer) obj).intValue();
        }
        return -1;
    }

    public void setMachineStatusCharacteristic(int i) {
        this.mFitnessData.put(20009, Integer.valueOf(i));
    }
}
