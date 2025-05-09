package com.huawei.indoorequip.datastruct;

import android.os.Parcel;
import android.os.Parcelable;
import com.huawei.health.device.model.FitnessData;

/* loaded from: classes5.dex */
public class MachineControlPointResponse extends FitnessData {
    public static final int BACKGROUND_ROWING_MODEL = 3;
    public static final int BACKGROUND_ROWING_STRENGTH_TRAINING = 4;
    public static final Parcelable.Creator<MachineControlPointResponse> CREATOR = new Parcelable.Creator<MachineControlPointResponse>() { // from class: com.huawei.indoorequip.datastruct.MachineControlPointResponse.1
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MachineControlPointResponse createFromParcel(Parcel parcel) {
            return new MachineControlPointResponse(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public MachineControlPointResponse[] newArray(int i) {
            return new MachineControlPointResponse[i];
        }
    };
    public static final int OP_CODE_EXTENSION_MULTI_DATA = 164;
    public static final int OP_CODE_EXTENSION_SET_DYNAMIC_ENERGY = 162;
    public static final int OP_CODE_EXTENSION_SET_HEART_RATE = 160;
    public static final int OP_CODE_EXTENSION_SET_STEP_COUNT = 163;
    public static final int OP_CODE_EXTENSION_SET_SUPPRESS_AUTO_PAUSE = 166;
    public static final int OP_CODE_EXTENSION_SET_TARGETED_EXPENDED_ENERGY = 167;
    public static final int OP_CODE_EXTENSION_SET_TOTAL_ENERGY = 161;
    public static final int OP_CODE_EXTENSION_SET_UNLOCK_CODE = 165;
    public static final int OP_CODE_REQUEST_CONTROL = 0;
    public static final int OP_CODE_RESET = 1;
    public static final int OP_CODE_SET_BIKE_SIMULATION_PARA = 17;
    public static final int OP_CODE_SET_RESPONSE_CODE = 128;
    public static final int OP_CODE_SET_ROWING_MODE_CONTROL = 21;
    public static final int OP_CODE_SET_SPIN_DOWN_CONTROL = 19;
    public static final int OP_CODE_SET_TARGETED_CADENCE = 20;
    public static final int OP_CODE_SET_TARGET_DISTANCE = 12;
    public static final int OP_CODE_SET_TARGET_ENERGY = 9;
    public static final int OP_CODE_SET_TARGET_FIVE_HEART_RATE_ZONE = 16;
    public static final int OP_CODE_SET_TARGET_HEART_RATE = 6;
    public static final int OP_CODE_SET_TARGET_INCLINATION = 3;
    public static final int OP_CODE_SET_TARGET_POWER = 5;
    public static final int OP_CODE_SET_TARGET_RESISTANCE_LEVEL = 4;
    public static final int OP_CODE_SET_TARGET_SPEED = 2;
    public static final int OP_CODE_SET_TARGET_STEPS = 10;
    public static final int OP_CODE_SET_TARGET_STRIDES = 11;
    public static final int OP_CODE_SET_TARGET_THREE_HEART_RATE_ZONE = 15;
    public static final int OP_CODE_SET_TARGET_TRAINING_TIME = 13;
    public static final int OP_CODE_SET_TARGET_TWO_HEART_RATE_ZONE = 14;
    public static final int OP_CODE_SET_WHEEL_CIRCUMFERENCE = 18;
    public static final int OP_CODE_START_OR_RESUME = 7;
    public static final int OP_CODE_STOP_OR_PAUSE = 8;
    public static final int PARAMETER_PAUSE = 2;
    public static final int PARAMETER_STOP = 1;
    public static final int RESULT_CODE_CONTROL_NOT_PERMITTED = 5;
    public static final int RESULT_CODE_INVALID_PARAMETER = 3;
    public static final int RESULT_CODE_OPCODE_NOT_SUPPORT = 2;
    public static final int RESULT_CODE_OPERATION_FAILED = 4;
    public static final int RESULT_CODE_SUCCESS = 1;
    public static final int ROWING_MODEL = 1;
    public static final int ROWING_STRENGTH_TRAINING = 2;
    private int mRequestOpCode;
    private int mResponseOpCode;
    private int mResultCode;
    private int mResultIntParameter;
    private byte[] mResultParameter;

    @Override // com.huawei.health.device.datatype.AbstractFitnessData, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public MachineControlPointResponse() {
        setFitnessDataType(7);
    }

    protected MachineControlPointResponse(Parcel parcel) {
        this.mResponseOpCode = parcel.readInt();
        this.mRequestOpCode = parcel.readInt();
        this.mResultCode = parcel.readInt();
        this.mResultIntParameter = parcel.readInt();
        this.mResultParameter = parcel.createByteArray();
    }

    @Override // com.huawei.health.device.datatype.AbstractFitnessData, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mResponseOpCode);
        parcel.writeInt(this.mRequestOpCode);
        parcel.writeInt(this.mResultCode);
        parcel.writeInt(this.mResultIntParameter);
        parcel.writeByteArray(this.mResultParameter);
    }

    public int getResponseOpCode() {
        return this.mResponseOpCode;
    }

    public void setResponseOpCode(int i) {
        this.mResponseOpCode = i;
    }

    public int getRequestOpCode() {
        return this.mRequestOpCode;
    }

    public void setRequestOpCode(int i) {
        this.mRequestOpCode = i;
    }

    public int getResultCode() {
        return this.mResultCode;
    }

    public void setResultCode(int i) {
        this.mResultCode = i;
    }

    public int getResultIntParameter() {
        return this.mResultIntParameter;
    }

    public void setResultIntParameter(int i) {
        this.mResultIntParameter = i;
    }

    public byte[] getResultParameter() {
        byte[] bArr = this.mResultParameter;
        if (bArr == null) {
            return new byte[0];
        }
        int length = bArr.length;
        byte[] bArr2 = new byte[length];
        System.arraycopy(bArr, 0, bArr2, 0, length);
        return bArr2;
    }

    public void setResultParameter(byte[] bArr) {
        if (bArr != null) {
            int length = bArr.length;
            byte[] bArr2 = new byte[length];
            System.arraycopy(bArr, 0, bArr2, 0, length);
            this.mResultParameter = bArr2;
        }
    }
}
