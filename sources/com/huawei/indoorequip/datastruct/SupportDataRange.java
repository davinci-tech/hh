package com.huawei.indoorequip.datastruct;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.health.device.model.FitnessData;
import com.huawei.skinner.internal.OnRegisterSkinAttrsListener;
import java.io.Serializable;

/* loaded from: classes5.dex */
public class SupportDataRange extends FitnessData implements Serializable {
    public static final Parcelable.Creator<SupportDataRange> CREATOR = new Parcelable.Creator<SupportDataRange>() { // from class: com.huawei.indoorequip.datastruct.SupportDataRange.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SupportDataRange createFromParcel(Parcel parcel) {
            return new SupportDataRange(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public SupportDataRange[] newArray(int i) {
            return new SupportDataRange[i];
        }
    };
    private static final long serialVersionUID = 999185932301604245L;

    public SupportDataRange(Parcel parcel) {
        super(parcel);
    }

    public SupportDataRange() {
        clearData();
        setFitnessDataType(12);
    }

    public int getMinSpeed() {
        return ((Integer) this.mFitnessData.get(30000)).intValue();
    }

    public void setMinSpeed(int i) {
        this.mFitnessData.put(30000, Integer.valueOf(i));
    }

    public int getMaxSpeed() {
        return ((Integer) this.mFitnessData.get(30001)).intValue();
    }

    public void setMaxSpeed(int i) {
        this.mFitnessData.put(30001, Integer.valueOf(i));
    }

    public int getMinIncrementSpeed() {
        return ((Integer) this.mFitnessData.get(30002)).intValue();
    }

    public void setMinIncrementSpeed(int i) {
        this.mFitnessData.put(30002, Integer.valueOf(i));
    }

    public int getMinInclination() {
        return ((Integer) this.mFitnessData.get(Integer.valueOf(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN))).intValue();
    }

    public void setMinInclination(int i) {
        this.mFitnessData.put(Integer.valueOf(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN), Integer.valueOf(i));
    }

    public int getMaxInclination() {
        return ((Integer) this.mFitnessData.get(30004)).intValue();
    }

    public void setMaxInclination(int i) {
        this.mFitnessData.put(30004, Integer.valueOf(i));
    }

    public int getMinIncreInclination() {
        return ((Integer) this.mFitnessData.get(30005)).intValue();
    }

    public void setMinIncreInclination(int i) {
        this.mFitnessData.put(30005, Integer.valueOf(i));
    }

    public int getMinLevel() {
        if (this.mFitnessData.get(30006) instanceof Number) {
            return ((Number) this.mFitnessData.get(30006)).intValue();
        }
        return 0;
    }

    public void setMinLevel(int i) {
        this.mFitnessData.put(30006, Integer.valueOf(i));
    }

    public int getMaxLevel() {
        if (this.mFitnessData.get(30007) instanceof Number) {
            return ((Number) this.mFitnessData.get(30007)).intValue();
        }
        return 0;
    }

    public void setMaxLevel(int i) {
        this.mFitnessData.put(30007, Integer.valueOf(i));
    }

    public int getMinIncrementLevel() {
        if (this.mFitnessData.get(30008) instanceof Number) {
            return ((Number) this.mFitnessData.get(30008)).intValue();
        }
        return 0;
    }

    public void setMinIncrementLevel(int i) {
        this.mFitnessData.put(30008, Integer.valueOf(i));
    }

    public int getMinHeartRate() {
        return ((Integer) this.mFitnessData.get(30009)).intValue();
    }

    public void setMinHeartRate(int i) {
        this.mFitnessData.put(30009, Integer.valueOf(i));
    }

    public int getMaxHeartRate() {
        return ((Integer) this.mFitnessData.get(30010)).intValue();
    }

    public void setMaxHeartRate(int i) {
        this.mFitnessData.put(30010, Integer.valueOf(i));
    }

    public int getMinIncrementHeartRate() {
        return ((Integer) this.mFitnessData.get(30011)).intValue();
    }

    public void setMinIncrementHeartRate(int i) {
        this.mFitnessData.put(30011, Integer.valueOf(i));
    }

    public int getMinPower() {
        return ((Integer) this.mFitnessData.get(30012)).intValue();
    }

    public void setMinPower(int i) {
        this.mFitnessData.put(30012, Integer.valueOf(i));
    }

    public int getMaxPower() {
        return ((Integer) this.mFitnessData.get(30013)).intValue();
    }

    public void setMaxPower(int i) {
        this.mFitnessData.put(30013, Integer.valueOf(i));
    }

    public int getMinIncrementPower() {
        return ((Integer) this.mFitnessData.get(30014)).intValue();
    }

    public void setMinIncrementPower(int i) {
        this.mFitnessData.put(30014, Integer.valueOf(i));
    }
}
