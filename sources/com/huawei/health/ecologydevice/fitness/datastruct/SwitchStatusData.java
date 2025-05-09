package com.huawei.health.ecologydevice.fitness.datastruct;

import defpackage.cyf;
import defpackage.cyw;
import health.compact.a.util.LogUtil;

/* loaded from: classes3.dex */
public class SwitchStatusData extends BaseRopeData {
    private static final String TAG = "PDROPE_SwitchStatusData";

    public SwitchStatusData() {
        super(25);
    }

    public boolean isOpenSpeechSoundSwitch() {
        Object obj = this.mFitnessData.get(40017);
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        return false;
    }

    public void setOpenSpeechSoundSwitch(boolean z) {
        this.mFitnessData.put(40017, Boolean.valueOf(z));
    }

    public boolean isOpenSpeechSoundTrainSwitch() {
        Object obj = this.mFitnessData.get(40018);
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        return false;
    }

    public void setOpenSpeechSoundTrainSwitch(boolean z) {
        this.mFitnessData.put(40018, Boolean.valueOf(z));
    }

    public boolean isOpenSpeechSoundCadenceSwitch() {
        Object obj = this.mFitnessData.get(40019);
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        return false;
    }

    public void setOpenSpeechSoundCadenceSwitch(boolean z) {
        this.mFitnessData.put(40019, Boolean.valueOf(z));
    }

    public boolean isOpenSpeechSoundMotorSwitch() {
        Object obj = this.mFitnessData.get(40020);
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        return false;
    }

    public void setOpenSpeechSoundMotorSwitch(boolean z) {
        this.mFitnessData.put(40020, Boolean.valueOf(z));
    }

    public boolean isOpenSpeechSoundHeartRateMonitoringSwitch() {
        Object obj = this.mFitnessData.get(40021);
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        return false;
    }

    public void setOpenSpeechSoundHeartRateMonitoringSwitch(boolean z) {
        this.mFitnessData.put(40021, Boolean.valueOf(z));
    }

    public String getBrMacAddress() {
        Object obj = this.mFitnessData.get(40022);
        return obj instanceof String ? (String) obj : "";
    }

    public void setBrMacAddress(String str) {
        this.mFitnessData.put(40022, str);
    }

    public boolean isOpenBr() {
        Object obj = this.mFitnessData.get(40023);
        if (obj instanceof Boolean) {
            return ((Boolean) obj).booleanValue();
        }
        return false;
    }

    public void setOpenBr(boolean z) {
        this.mFitnessData.put(40023, Boolean.valueOf(z));
    }

    @Override // com.huawei.health.ecologydevice.fitness.datastruct.BaseRopeData
    public BaseRopeData parseData() {
        int i = this.mCode;
        if (i == 0) {
            parseSwitchEnquireData(this.mData, this.mOffset, this.mFlag);
        } else if (i == 1) {
            parseSwitchStatusData(this.mData, this.mOffset);
        } else if (i == 2 || i == 3) {
            parseBrSettingResultData(this.mData, this.mOffset);
        }
        return this;
    }

    private void parseSwitchEnquireData(byte[] bArr, int i, int i2) {
        boolean z = (i2 & 1) != 0;
        boolean z2 = ((i2 & 2) != 0) || ((i2 & 4) != 0);
        LogUtil.d(TAG, "flag: ", Integer.valueOf(i2), " isHasSwitchSettingResult: ", Boolean.valueOf(z), " isHasBrSettingResult: ", Boolean.valueOf(z2));
        if (z) {
            parseSwitchStatusData(bArr, i);
            i += 2;
        }
        if (z2) {
            parseBrSettingResultData(bArr, i);
        }
    }

    private void parseSwitchStatusData(byte[] bArr, int i) {
        int e = cyw.e(bArr, 18, i);
        boolean z = (e & 1) != 0;
        setOpenSpeechSoundSwitch(z);
        boolean z2 = (e & 2) != 0;
        setOpenSpeechSoundTrainSwitch(z2);
        boolean z3 = (e & 4) != 0;
        setOpenSpeechSoundCadenceSwitch(z3);
        boolean z4 = (e & 8) != 0;
        setOpenSpeechSoundMotorSwitch(z4);
        boolean z5 = (e & 16) != 0;
        setOpenSpeechSoundHeartRateMonitoringSwitch(z5);
        LogUtil.d(TAG, "isOpenSpeechSound= ", Boolean.valueOf(z), " isOpenSpeechSoundTrain= ", Boolean.valueOf(z2), " isOpenSpeechSoundCadence= ", Boolean.valueOf(z3), " isOpenSpeechSoundMotor= ", Boolean.valueOf(z4), " isOpenSpeechSoundHeartRateMonitoring= ", Boolean.valueOf(z5));
    }

    private void parseBrSettingResultData(byte[] bArr, int i) {
        String b = cyw.b(cyw.b(bArr, i, 17), 0);
        int e = cyw.e(bArr, 17, i + 17);
        Object[] objArr = new Object[2];
        objArr[0] = "isOpenBr: ";
        objArr[1] = Boolean.valueOf(e != 0);
        LogUtil.d(TAG, objArr);
        setBrMacAddress(b);
        setOpenBr(e != 0);
    }

    @Override // com.huawei.health.ecologydevice.fitness.datastruct.BaseRopeData
    public cyf getTransmitCommand() {
        byte[] bArr;
        cyf.c cVar = new cyf.c();
        cVar.d(this.mCommand);
        cVar.b(this.mCode);
        int i = this.mCode;
        if (i == 0) {
            cVar.a(this.mPara[0]);
            bArr = new byte[0];
        } else if (i != 1) {
            bArr = new byte[0];
        } else {
            bArr = new byte[2];
            cyw.c(bArr, this.mPara[0], 0);
        }
        cVar.e(bArr);
        return cVar.d();
    }
}
