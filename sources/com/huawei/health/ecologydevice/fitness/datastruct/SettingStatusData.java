package com.huawei.health.ecologydevice.fitness.datastruct;

import defpackage.cyf;
import defpackage.cyw;

/* loaded from: classes3.dex */
public class SettingStatusData extends BaseRopeData {
    public SettingStatusData() {
        super(26);
    }

    public int getSettingVolume() {
        Object obj = this.mFitnessData.get(40024);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public void setSettingVolume(int i) {
        this.mFitnessData.put(40024, Integer.valueOf(i));
    }

    public int getSettingVoiceCoachingCorpus() {
        Object obj = this.mFitnessData.get(40025);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public void setSettingVoiceCoachingCorpus(int i) {
        this.mFitnessData.put(40025, Integer.valueOf(i));
    }

    public int getSettingVoiceCv() {
        Object obj = this.mFitnessData.get(40026);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public void setSettingVoiceCv(int i) {
        this.mFitnessData.put(40026, Integer.valueOf(i));
    }

    public int getSettingBeatSoundType() {
        Object obj = this.mFitnessData.get(40027);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public void setSettingBeatSoundType(int i) {
        this.mFitnessData.put(40027, Integer.valueOf(i));
    }

    public int getSettingBeatSound() {
        Object obj = this.mFitnessData.get(40028);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public void setSettingBeatSound(int i) {
        this.mFitnessData.put(40028, Integer.valueOf(i));
    }

    public int getSettingHeartRateUpperLimit() {
        Object obj = this.mFitnessData.get(40029);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public void setSettingHeartRateUpperLimit(int i) {
        this.mFitnessData.put(40029, Integer.valueOf(i));
    }

    public int getSettingReserveHeartRate() {
        Object obj = this.mFitnessData.get(40030);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public void setSettingReserveHeartRate(int i) {
        this.mFitnessData.put(40030, Integer.valueOf(i));
    }

    public int getSettingAerobicBase() {
        Object obj = this.mFitnessData.get(40031);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public void setSettingAerobicBase(int i) {
        this.mFitnessData.put(40031, Integer.valueOf(i));
    }

    public int getSettingAerobicAdvances() {
        Object obj = this.mFitnessData.get(40032);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public void setSettingAerobicAdvances(int i) {
        this.mFitnessData.put(40032, Integer.valueOf(i));
    }

    public int getSettingLactateThreshold() {
        Object obj = this.mFitnessData.get(40033);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public void setSettingLactateThreshold(int i) {
        this.mFitnessData.put(40033, Integer.valueOf(i));
    }

    public int getSettingAnaerobicFoundation() {
        Object obj = this.mFitnessData.get(40034);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public void setSettingAnaerobicFoundation(int i) {
        this.mFitnessData.put(40034, Integer.valueOf(i));
    }

    public int getSettingAnaerobicAdvanced() {
        Object obj = this.mFitnessData.get(40035);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public void setSettingAnaerobicAdvanced(int i) {
        this.mFitnessData.put(40035, Integer.valueOf(i));
    }

    public int getSettingMaximumRangeValue() {
        Object obj = this.mFitnessData.get(40036);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public void setSettingMaximumRangeValue(int i) {
        this.mFitnessData.put(40036, Integer.valueOf(i));
    }

    public int getSettingBestNumberSingleJumps() {
        Object obj = this.mFitnessData.get(40037);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public void setSettingBestNumberSingleJumps(int i) {
        this.mFitnessData.put(40037, Integer.valueOf(i));
    }

    public int getSettingBestNumberConsecutiveJumps() {
        Object obj = this.mFitnessData.get(40038);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public void setSettingBestNumberConsecutiveJumps(int i) {
        this.mFitnessData.put(40038, Integer.valueOf(i));
    }

    public int getSettingBestSkippingDuration() {
        Object obj = this.mFitnessData.get(40039);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public void setSettingBestSkippingDuration(int i) {
        this.mFitnessData.put(40039, Integer.valueOf(i));
    }

    public int getSettingBestSkippingSpeed() {
        Object obj = this.mFitnessData.get(40040);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public void setSettingBestSkippingSpeed(int i) {
        this.mFitnessData.put(40040, Integer.valueOf(i));
    }

    public int getSettingVoiceBroadcastFrequency() {
        Object obj = this.mFitnessData.get(40041);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public void setSettingVoiceBroadcastFrequency(int i) {
        this.mFitnessData.put(40041, Integer.valueOf(i));
    }

    public int getWeight() {
        Object obj = this.mFitnessData.get(40042);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public void setWeight(int i) {
        this.mFitnessData.put(40042, Integer.valueOf(i));
    }

    public int getHeight() {
        Object obj = this.mFitnessData.get(40043);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public void setHeight(int i) {
        this.mFitnessData.put(40043, Integer.valueOf(i));
    }

    public int getPlayMode() {
        Object obj = this.mFitnessData.get(40069);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public void setPlayMode(int i) {
        this.mFitnessData.put(40069, Integer.valueOf(i));
    }

    public int getSex() {
        Object obj = this.mFitnessData.get(40044);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public void setSex(int i) {
        this.mFitnessData.put(40044, Integer.valueOf(i));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x005f, code lost:
    
        return r3;
     */
    @Override // com.huawei.health.ecologydevice.fitness.datastruct.BaseRopeData
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.health.ecologydevice.fitness.datastruct.BaseRopeData parseData() {
        /*
            r3 = this;
            int r0 = r3.mCode
            switch(r0) {
                case 0: goto L56;
                case 1: goto L4e;
                case 2: goto L46;
                case 3: goto L3e;
                case 4: goto L36;
                case 5: goto L2e;
                case 6: goto L26;
                case 7: goto L1e;
                case 8: goto L16;
                case 9: goto Le;
                case 10: goto L6;
                default: goto L5;
            }
        L5:
            goto L5f
        L6:
            byte[] r0 = r3.mData
            int r1 = r3.mOffset
            r3.parseSettingPlayMode(r0, r1)
            goto L5f
        Le:
            byte[] r0 = r3.mData
            int r1 = r3.mOffset
            r3.parseSettingCalories(r0, r1)
            goto L5f
        L16:
            byte[] r0 = r3.mData
            int r1 = r3.mOffset
            r3.parseSettingVoiceBroadcastFrequency(r0, r1)
            goto L5f
        L1e:
            byte[] r0 = r3.mData
            int r1 = r3.mOffset
            r3.parseSettingEnquireBestSkipData(r0, r1)
            goto L5f
        L26:
            byte[] r0 = r3.mData
            int r1 = r3.mOffset
            r3.parseSettingEnquireHeartRateRange(r0, r1)
            goto L5f
        L2e:
            byte[] r0 = r3.mData
            int r1 = r3.mOffset
            r3.parseSettingHeartRateUpperLimit(r0, r1)
            goto L5f
        L36:
            byte[] r0 = r3.mData
            int r1 = r3.mOffset
            r3.parseSettingBeatSound(r0, r1)
            goto L5f
        L3e:
            byte[] r0 = r3.mData
            int r1 = r3.mOffset
            r3.parseSettingVoiceCv(r0, r1)
            goto L5f
        L46:
            byte[] r0 = r3.mData
            int r1 = r3.mOffset
            r3.parseSettingVoiceCoachingCorpus(r0, r1)
            goto L5f
        L4e:
            byte[] r0 = r3.mData
            int r1 = r3.mOffset
            r3.parseSettingVolume(r0, r1)
            goto L5f
        L56:
            byte[] r0 = r3.mData
            int r1 = r3.mOffset
            int r2 = r3.mFlag
            r3.parseSettingEnquire(r0, r1, r2)
        L5f:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.ecologydevice.fitness.datastruct.SettingStatusData.parseData():com.huawei.health.ecologydevice.fitness.datastruct.BaseRopeData");
    }

    private void parseSettingPlayMode(byte[] bArr, int i) {
        setPlayMode(cyw.e(bArr, 17, i));
    }

    private void parseSettingEnquire(byte[] bArr, int i, int i2) {
        if ((i2 & 1) != 0) {
            i = parseSettingVolume(bArr, i);
        }
        if ((i2 & 2) != 0) {
            i = parseSettingVoiceCoachingCorpus(bArr, i);
        }
        if ((i2 & 4) != 0) {
            i = parseSettingVoiceCv(bArr, i);
        }
        if ((i2 & 8) != 0) {
            i = parseSettingBeatSound(bArr, i);
        }
        if ((i2 & 16) != 0) {
            i = parseSettingHeartRateUpperLimit(bArr, i);
        }
        if ((i2 & 32) != 0) {
            i = parseSettingEnquireHeartRateRange(bArr, i);
        }
        if ((i2 & 64) != 0) {
            i = parseSettingEnquireBestSkipData(bArr, i);
        }
        if ((i2 & 128) != 0) {
            i = parseSettingVoiceBroadcastFrequency(bArr, i);
        }
        if ((i2 & 256) != 0) {
            i = parseSettingCalories(bArr, i);
        }
        if ((i2 & 512) != 0) {
            parseSettingPlayMode(bArr, i);
        }
    }

    private int parseSettingVolume(byte[] bArr, int i) {
        setSettingVolume(cyw.e(bArr, 17, i));
        return i + 1;
    }

    private int parseSettingVoiceCoachingCorpus(byte[] bArr, int i) {
        setSettingVoiceCoachingCorpus(cyw.e(bArr, 17, i));
        return i + 1;
    }

    private int parseSettingVoiceCv(byte[] bArr, int i) {
        setSettingVoiceCv(cyw.e(bArr, 17, i));
        return i + 1;
    }

    private int parseSettingBeatSound(byte[] bArr, int i) {
        setSettingBeatSoundType(cyw.e(bArr, 17, i));
        setSettingBeatSound(cyw.e(bArr, 18, i + 1));
        return i + 3;
    }

    private int parseSettingHeartRateUpperLimit(byte[] bArr, int i) {
        setSettingHeartRateUpperLimit(cyw.e(bArr, 17, i));
        return i + 1;
    }

    private int parseSettingEnquireHeartRateRange(byte[] bArr, int i) {
        setSettingReserveHeartRate(cyw.e(bArr, 17, i));
        setSettingAerobicBase(cyw.e(bArr, 18, i + 1));
        setSettingAerobicAdvances(cyw.e(bArr, 18, i + 3));
        setSettingLactateThreshold(cyw.e(bArr, 18, i + 5));
        setSettingAnaerobicFoundation(cyw.e(bArr, 18, i + 7));
        setSettingAnaerobicAdvanced(cyw.e(bArr, 18, i + 9));
        setSettingMaximumRangeValue(cyw.e(bArr, 18, i + 11));
        return i + 13;
    }

    private int parseSettingEnquireBestSkipData(byte[] bArr, int i) {
        setSettingBestNumberSingleJumps(cyw.e(bArr, 18, i));
        setSettingBestNumberConsecutiveJumps(cyw.e(bArr, 18, i + 2));
        setSettingBestSkippingDuration(cyw.e(bArr, 18, i + 4));
        setSettingBestSkippingSpeed(cyw.e(bArr, 18, i + 6));
        return i + 8;
    }

    private int parseSettingVoiceBroadcastFrequency(byte[] bArr, int i) {
        setSettingVoiceBroadcastFrequency(cyw.e(bArr, 18, i));
        return i + 2;
    }

    private int parseSettingCalories(byte[] bArr, int i) {
        setWeight(cyw.e(bArr, 18, i));
        setHeight(cyw.e(bArr, 17, i + 2));
        setSex(cyw.e(bArr, 17, i + 3));
        return i + 4;
    }

    @Override // com.huawei.health.ecologydevice.fitness.datastruct.BaseRopeData
    public cyf getTransmitCommand() {
        byte[] bArr;
        cyf.c cVar = new cyf.c();
        cVar.d(this.mCommand);
        cVar.b(this.mCode);
        switch (this.mCode) {
            case 0:
                cVar.a(this.mPara[0]);
                bArr = new byte[0];
                break;
            case 1:
            case 2:
            case 3:
            case 5:
            case 10:
                bArr = new byte[1];
                cyw.a(bArr, this.mPara[0], 0);
                break;
            case 4:
                bArr = setSettingBeatSoundPara(this.mPara, 0, 0);
                break;
            case 6:
                bArr = setSettingHeartRateZonePara(this.mPara, 0, 0);
                break;
            case 7:
                bArr = setSettingSkippingRopeAchievementsPara(this.mPara, 0, 0);
                break;
            case 8:
                bArr = new byte[2];
                cyw.c(bArr, this.mPara[0], 0);
                break;
            case 9:
                bArr = new byte[4];
                cyw.c(bArr, this.mPara[0], 0);
                cyw.d(bArr, 2, this.mPara, 1, 2);
                break;
            default:
                bArr = new byte[0];
                break;
        }
        cVar.e(bArr);
        return cVar.d();
    }

    private byte[] setSettingBeatSoundPara(int[] iArr, int i, int i2) {
        byte[] bArr = new byte[3];
        cyw.a(bArr, iArr[0], i);
        cyw.c(bArr, iArr[i2 + 1], i + 1);
        return bArr;
    }

    private byte[] setSettingHeartRateZonePara(int[] iArr, int i, int i2) {
        byte[] bArr = new byte[13];
        cyw.a(bArr, iArr[i2], i);
        cyw.e(bArr, i + 1, iArr, i2 + 1, 6);
        return bArr;
    }

    private byte[] setSettingSkippingRopeAchievementsPara(int[] iArr, int i, int i2) {
        byte[] bArr = new byte[8];
        cyw.e(bArr, i, iArr, i2, 4);
        return bArr;
    }
}
