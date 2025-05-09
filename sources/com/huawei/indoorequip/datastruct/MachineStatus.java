package com.huawei.indoorequip.datastruct;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.health.device.model.FitnessData;

/* loaded from: classes5.dex */
public class MachineStatus extends FitnessData {
    public static final int CONTROL_INFORMATION_PAUSE = 2;
    public static final int CONTROL_INFORMATION_STOP = 1;
    public static final Parcelable.Creator<MachineStatus> CREATOR = new Parcelable.Creator<MachineStatus>() { // from class: com.huawei.indoorequip.datastruct.MachineStatus.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MachineStatus createFromParcel(Parcel parcel) {
            return new MachineStatus(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MachineStatus[] newArray(int i) {
            return new MachineStatus[i];
        }
    };
    public static final int OP_CODE_CONTROL_PERMISSION_LOST = 255;
    public static final int OP_CODE_INDOOR_BIKE_SIM_PARAMETER_CHANGE = 18;
    public static final int OP_CODE_SPIN_DOWN_STATUS = 20;
    public static final int OP_CODE_START_OR_RESUME_BY_USER = 4;
    public static final int OP_CODE_STOP_BY_SAFETY = 3;
    public static final int OP_CODE_STOP_OR_PAUSE_BY_USER = 2;
    public static final int OP_CODE_TARGET_CADENCE_CHANGE = 21;
    public static final int OP_CODE_TARGET_DISTANCE_CHANGE = 13;
    public static final int OP_CODE_TARGET_EXPENDED_ENERGY_CHANGE = 10;
    public static final int OP_CODE_TARGET_EXPENDED_ENERGY_CHANGE2 = 11;
    public static final int OP_CODE_TARGET_HEART_RATE_CHANGE = 9;
    public static final int OP_CODE_TARGET_INCLINE_CHANGE = 6;
    public static final int OP_CODE_TARGET_POWER_CHANGE = 8;
    public static final int OP_CODE_TARGET_RESISTANCE_CHANGE = 7;
    public static final int OP_CODE_TARGET_SPEED_CHANGE = 5;
    public static final int OP_CODE_TARGET_STRIDES_NUMBER_CHANGE = 12;
    public static final int OP_CODE_TARGET_TIME_FIFTH_HRZONE_CHANGE = 17;
    public static final int OP_CODE_TARGET_TIME_SECOND_HRZONE_CHANGE = 15;
    public static final int OP_CODE_TARGET_TIME_THIRD_HRZONE_CHANGE = 16;
    public static final int OP_CODE_TARGET_TRAINING_TIME_CHANGE = 14;
    public static final int OP_CODE_WHEEL_CIRCUMFERENCE_CHANGE = 19;
    public static final int ROPE_CONTROL_INTERMITTENT_REST = 2;

    @Override // com.huawei.health.device.datatype.AbstractFitnessData, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected MachineStatus(Parcel parcel) {
        super(parcel);
    }

    public MachineStatus() {
        clearData();
        setFitnessDataType(2);
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override // com.huawei.health.device.datatype.AbstractFitnessData, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }
}
