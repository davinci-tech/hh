package com.huawei.wearengine.sensor;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.Ctry;
import java.util.List;

/* loaded from: classes9.dex */
public class DataResult implements Parcelable {
    public static final Parcelable.Creator<DataResult> CREATOR = new Parcelable.Creator<DataResult>() { // from class: com.huawei.wearengine.sensor.DataResult.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: feH_, reason: merged with bridge method [inline-methods] */
        public DataResult createFromParcel(Parcel parcel) {
            DataResult dataResult = new DataResult();
            if (parcel != null) {
                dataResult.setTimestamp(parcel.readLong());
                dataResult.setValues(parcel.createFloatArray());
                dataResult.setListValues(parcel.readArrayList(DataResult.class.getClassLoader()));
                dataResult.setChannel(parcel.readInt());
                dataResult.setSensor((Sensor) parcel.readParcelable(Sensor.class.getClassLoader()));
                dataResult.setExtendJson(parcel.readString());
            }
            return dataResult;
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public DataResult[] newArray(int i) {
            return (i > 65535 || i < 0) ? new DataResult[0] : new DataResult[i];
        }
    };
    public static final float MAX_SIGNED_SHORT = 32767.0f;
    public static final float MAX_UNSIGNED_SHORT = 65535.0f;
    public static final String UTC = "UTC";
    private int mChannel;
    private String mExtendJson = "";
    private List<DataResult> mListValues;
    private Sensor mSensor;
    private long mTimestamp;
    private float[] mValues;

    private static float unsignedShort2SignedShort(float f) {
        return (f < 0.0f || f > 65535.0f || f <= 32767.0f) ? f : f - 65536.0f;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public float[] asFloats() {
        if (this.mValues == null) {
            return new float[0];
        }
        if (this.mSensor.getType() == 2 || this.mSensor.getType() == 3) {
            return unsignedShorts2SignedShorts((float[]) this.mValues.clone());
        }
        return (float[]) this.mValues.clone();
    }

    private float[] unsignedShorts2SignedShorts(float[] fArr) {
        for (int i = 0; i < fArr.length; i++) {
            fArr[i] = unsignedShort2SignedShort(fArr[i]);
        }
        return fArr;
    }

    public List<DataResult> asList() {
        return this.mListValues;
    }

    public void setListValues(List<DataResult> list) {
        this.mListValues = list;
    }

    public void setChannel(int i) {
        this.mChannel = i;
    }

    public int getChannel() {
        return this.mChannel;
    }

    public void setTimestamp(long j) {
        this.mTimestamp = j;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public void setValues(float[] fArr) {
        this.mValues = fArr == null ? null : (float[]) fArr.clone();
    }

    public Sensor getSensor() {
        return this.mSensor;
    }

    public void setSensor(Sensor sensor) {
        this.mSensor = sensor;
    }

    public void setExtendJson(String str) {
        this.mExtendJson = str;
    }

    public long getUtc() {
        return Ctry.e(this.mExtendJson, "getUtc", UTC, 0L);
    }

    public void setUtc(long j) {
        this.mExtendJson = Ctry.e(this.mExtendJson, "setUtc", UTC, Long.valueOf(j));
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeLong(this.mTimestamp);
        parcel.writeFloatArray(this.mValues);
        parcel.writeList(this.mListValues);
        parcel.writeInt(this.mChannel);
        parcel.writeParcelable(this.mSensor, i);
        parcel.writeString(this.mExtendJson);
    }

    public void readFromParcel(Parcel parcel) {
        if (parcel == null) {
            return;
        }
        this.mTimestamp = parcel.readLong();
        this.mValues = parcel.createFloatArray();
        this.mListValues = parcel.readArrayList(DataResult.class.getClassLoader());
        this.mChannel = parcel.readInt();
        this.mSensor = (Sensor) parcel.readParcelable(Sensor.class.getClassLoader());
        this.mExtendJson = parcel.readString();
    }
}
