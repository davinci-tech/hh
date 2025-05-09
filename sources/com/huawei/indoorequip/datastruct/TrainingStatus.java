package com.huawei.indoorequip.datastruct;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.health.device.model.FitnessData;

/* loaded from: classes5.dex */
public class TrainingStatus extends FitnessData {
    public static final Parcelable.Creator<TrainingStatus> CREATOR = new Parcelable.Creator<TrainingStatus>() { // from class: com.huawei.indoorequip.datastruct.TrainingStatus.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TrainingStatus createFromParcel(Parcel parcel) {
            return new TrainingStatus(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public TrainingStatus[] newArray(int i) {
            return new TrainingStatus[i];
        }
    };
    public static final int FLAGS_EXTENDED_STRING = 2;
    public static final int FLAGS_TRAINING_STATUS_STRING = 1;
    public static final int TRAINING_STATUS_COOL_DOWN = 11;
    public static final int TRAINING_STATUS_FITNESS_TEST = 8;
    public static final int TRAINING_STATUS_HEART_RATE_CONTROL = 7;
    public static final int TRAINING_STATUS_HIGH_INTENSITY_INTERVAL = 4;
    public static final int TRAINING_STATUS_IDLE = 1;
    public static final int TRAINING_STATUS_ISOMETRIC = 6;
    public static final int TRAINING_STATUS_LOW_INTENSITY_INTERVAL = 3;
    public static final int TRAINING_STATUS_MANUAL_MODE = 13;
    public static final int TRAINING_STATUS_OTHER = 0;
    public static final int TRAINING_STATUS_POST_WORKOUT = 15;
    public static final int TRAINING_STATUS_PRE_WORKOUT = 14;
    public static final int TRAINING_STATUS_RECOVERY_INTERVAL = 5;
    public static final int TRAINING_STATUS_SPEED_OUTSIDE_HIGH = 10;
    public static final int TRAINING_STATUS_SPEED_OUTSIDE_LOW = 9;
    public static final int TRAINING_STATUS_WARNING_UP = 2;
    public static final int TRAINING_STATUS_WATT_CONTROL = 12;
    private int mFlags;
    private int mTrainingStatus;
    private String mTrainingStatusString;

    @Override // com.huawei.health.device.datatype.AbstractFitnessData, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    protected TrainingStatus(Parcel parcel) {
        this.mFlags = parcel.readInt();
        this.mTrainingStatus = parcel.readInt();
        this.mTrainingStatusString = parcel.readString();
    }

    public TrainingStatus() {
        clearData();
        setFitnessDataType(4);
    }

    public String getTrainingStatusString() {
        return this.mTrainingStatusString;
    }

    public void setTrainingStatusString(String str) {
        this.mTrainingStatusString = str;
    }

    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override // com.huawei.health.device.datatype.AbstractFitnessData, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mFlags);
        parcel.writeInt(this.mTrainingStatus);
        parcel.writeString(this.mTrainingStatusString);
    }
}
