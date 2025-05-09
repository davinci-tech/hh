package com.huawei.wearengine.sensor;

import android.os.Parcel;
import android.os.Parcelable;
import defpackage.Ctry;
import defpackage.tnx;
import defpackage.tov;
import defpackage.tqq;
import defpackage.tra;
import defpackage.tsg;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class Sensor implements Parcelable {
    public static final Parcelable.Creator<Sensor> CREATOR = new Parcelable.Creator<Sensor>() { // from class: com.huawei.wearengine.sensor.Sensor.4
        @Override // android.os.Parcelable.Creator
        /* renamed from: feI_, reason: merged with bridge method [inline-methods] */
        public Sensor createFromParcel(Parcel parcel) {
            if (parcel == null) {
                return null;
            }
            return new Sensor(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readFloat(), parcel.readString());
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public Sensor[] newArray(int i) {
            return new Sensor[i];
        }
    };
    public static final String NAME_ACC = "ACC";
    public static final String NAME_ECG = "ECG";
    public static final String NAME_GYRO = "GYRO";
    public static final String NAME_HR = "HR";
    public static final String NAME_MAG = "MAG";
    public static final String NAME_PPG = "PPG";
    public static final String NAME_PRESSURE = "PRESSURE";
    public static final int PPG_CHANNEL_AMB = 9;
    public static final int PPG_CHANNEL_AMBDUMMY = 2;
    public static final int PPG_CHANNEL_AMBGREEN = 4;
    public static final int PPG_CHANNEL_AMBIR = 6;
    public static final int PPG_CHANNEL_AMBR = 8;
    public static final int PPG_CHANNEL_DUMMY = 1;
    public static final int PPG_CHANNEL_GREEN = 3;
    public static final int PPG_CHANNEL_IR = 5;
    public static final int PPG_CHANNEL_RED = 7;
    public static final int PPG_CHANNEL_UNKNOWN = 0;
    private static final String TAG = "Sensor";
    public static final int TYPE_ACC = 2;
    public static final int TYPE_ECG = 0;
    public static final int TYPE_GYRO = 3;
    public static final int TYPE_HR = 6;
    public static final int TYPE_MAG = 4;
    public static final int TYPE_PPG = 1;
    public static final int TYPE_PRESSURE = 5;
    private int mAccuracy;
    private String mExtendJson;
    private int mId;
    private String mName;
    private float mResolution;
    private int mType;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public Sensor(int i, int i2, int i3, float f, String str) {
        this.mId = i;
        this.mType = i2;
        this.mAccuracy = i3;
        this.mResolution = f;
        this.mName = getNameByType(i2);
        this.mExtendJson = str;
    }

    public String getName() {
        return this.mName;
    }

    public int getId() {
        return this.mId;
    }

    public int getType() {
        return this.mType;
    }

    public int getAccuracy() {
        return this.mAccuracy;
    }

    public String getExtendJson() {
        return this.mExtendJson;
    }

    public void setExtendJson(String str) {
        this.mExtendJson = str;
    }

    public void setFrequencyType(SupportFrequency supportFrequency) throws tnx {
        if (!tra.a("set_frequency_type")) {
            tov.d(TAG, "setFrequencyType Health version is low");
            throw new tnx(14);
        }
        if (supportFrequency == null) {
            throw new tnx(5);
        }
        int value = supportFrequency.getValue();
        Iterator<tqq> it = getSupportFrequencyList().iterator();
        while (it.hasNext()) {
            if (it.next().d() == value) {
                tsg.a(this, value);
                return;
            }
        }
        throw new tnx(5);
    }

    public float getResolution() {
        return this.mResolution;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        if (parcel == null) {
            return;
        }
        parcel.writeInt(this.mId);
        parcel.writeInt(this.mType);
        parcel.writeInt(this.mAccuracy);
        parcel.writeFloat(this.mResolution);
        parcel.writeString(this.mExtendJson);
    }

    public List<tqq> getSupportFrequencyList() {
        String d = Ctry.d(this.mExtendJson, "getSupportFrequencyList", "SupportFrequencyList");
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray jSONArray = new JSONArray(d);
            for (int i = 0; i < jSONArray.length(); i++) {
                if (jSONArray.get(i) instanceof JSONObject) {
                    JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                    tqq tqqVar = new tqq();
                    tqqVar.a(jSONObject.getInt("FrequencyType"));
                    tqqVar.e(jSONObject.getInt("AcqFrequency"));
                    tqqVar.c(jSONObject.getInt("RepPeriod"));
                    arrayList.add(tqqVar);
                }
            }
        } catch (JSONException unused) {
            tov.c(TAG, "getSupportFrequencyList json exception");
        }
        return arrayList;
    }

    public boolean isSupportUtc() {
        return Ctry.a(this.mExtendJson, "isSupportUTC", "isSupportUTC");
    }

    private String getNameByType(int i) {
        switch (i) {
            case 0:
                return NAME_ECG;
            case 1:
                return NAME_PPG;
            case 2:
                return NAME_ACC;
            case 3:
                return NAME_GYRO;
            case 4:
                return NAME_MAG;
            case 5:
                return NAME_PRESSURE;
            case 6:
                return NAME_HR;
            default:
                return "";
        }
    }
}
