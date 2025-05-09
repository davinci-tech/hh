package com.huawei.health.ecologydevice.fitness.datastruct;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import defpackage.ckh;

/* loaded from: classes3.dex */
public class RopeRealData extends BaseRopeData {
    public static final Parcelable.Creator<RopeRealData> CREATOR = new Parcelable.Creator<RopeRealData>() { // from class: com.huawei.health.ecologydevice.fitness.datastruct.RopeRealData.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: RY_, reason: merged with bridge method [inline-methods] */
        public RopeRealData createFromParcel(Parcel parcel) {
            return new RopeRealData(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public RopeRealData[] newArray(int i) {
            return new RopeRealData[i];
        }
    };
    private long mUpdateTime;

    public RopeRealData() {
        super(22);
    }

    public RopeRealData(Parcel parcel) {
        super(parcel);
        this.mUpdateTime = parcel.readLong();
    }

    @Override // com.huawei.health.ecologydevice.fitness.datastruct.BaseRopeData, com.huawei.health.device.datatype.AbstractFitnessData, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeLong(this.mUpdateTime);
    }

    public int getStatus() {
        Object obj = this.mFitnessData.get(Integer.valueOf(SmartMsgConstant.MSG_TYPE_RIDE_USER));
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 65535;
    }

    public void setStatus(int i) {
        this.mFitnessData.put(Integer.valueOf(SmartMsgConstant.MSG_TYPE_RIDE_USER), Integer.valueOf(i));
    }

    public int getElapsedTime() {
        Object obj = this.mFitnessData.get(2);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 65535;
    }

    public void setElapsedTime(int i) {
        this.mFitnessData.put(2, Integer.valueOf(i));
    }

    public int getTotalSkip() {
        Object obj = this.mFitnessData.get(40001);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 65535;
    }

    public void setTotalSkip(int i) {
        this.mFitnessData.put(40001, Integer.valueOf(i));
    }

    public int getInstantaneousSpeed() {
        Object obj = this.mFitnessData.get(40004);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 65535;
    }

    public void setInstantaneousSpeed(int i) {
        this.mFitnessData.put(40004, Integer.valueOf(i));
    }

    public int getInterruptTimes() {
        Object obj = this.mFitnessData.get(40002);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 65535;
    }

    public void setInterruptTimes(int i) {
        if (i == 65535) {
            this.mFitnessData.put(40002, -1);
        } else {
            this.mFitnessData.put(40002, Integer.valueOf(i));
        }
    }

    public int getCurrentContinueSkip() {
        Object obj = this.mFitnessData.get(40003);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 65535;
    }

    public void setCurrentContinueSkip(int i) {
        if (i == 65535) {
            this.mFitnessData.put(40003, -1);
        } else {
            this.mFitnessData.put(40003, Integer.valueOf(i));
        }
    }

    public int getTotalEnergy() {
        Object obj = this.mFitnessData.get(6);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 65535;
    }

    public void setTotalEnergy(int i) {
        this.mFitnessData.put(6, Integer.valueOf(i));
    }

    public long getUpdateTime() {
        return this.mUpdateTime;
    }

    public void setUpdateTime(long j) {
        this.mUpdateTime = j;
    }

    public int getRopeSkippingMode() {
        Object obj = this.mFitnessData.get(40009);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return -1;
    }

    public void setRopeSkippingMode(int i) {
        this.mFitnessData.put(40009, Integer.valueOf(i));
    }

    public int getRopeIntermissionGroupNo() {
        Object obj = this.mFitnessData.get(40011);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return -1;
    }

    public void setRopeIntermissionGroupNo(int i) {
        this.mFitnessData.put(40011, Integer.valueOf(i));
    }

    public int getInstantaneousVelocity() {
        Object obj = this.mFitnessData.get(40010);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return -1;
    }

    public void setSkippingSpeedSeconds(int i) {
        this.mFitnessData.put(40010, Integer.valueOf(i));
    }

    public ckh getRopeSkippingTrick() {
        Object obj = this.mFitnessData.get(40010);
        if (obj instanceof ckh) {
            return (ckh) obj;
        }
        return null;
    }

    public void setRopeSkippingTrick(ckh ckhVar) {
        this.mFitnessData.put(40012, ckhVar);
    }

    public void setRopeIntermissionTime(int i) {
        this.mFitnessData.put(40051, Integer.valueOf(i));
    }

    public int getRopeIntermissionTime() {
        Object obj = this.mFitnessData.get(40051);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return -1;
    }

    public void setRopeIntermissionRestTime(int i) {
        this.mFitnessData.put(40052, Integer.valueOf(i));
    }

    public int getRopeIntermissionRestTime() {
        Object obj = this.mFitnessData.get(40052);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return -1;
    }
}
