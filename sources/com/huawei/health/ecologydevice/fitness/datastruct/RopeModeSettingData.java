package com.huawei.health.ecologydevice.fitness.datastruct;

import defpackage.cyf;
import defpackage.cyw;
import health.compact.a.util.LogUtil;

/* loaded from: classes3.dex */
public class RopeModeSettingData extends BaseRopeData {
    private static final String TAG = "PDROPE_RopeModeSettingData";

    public RopeModeSettingData() {
        super(27);
    }

    public long getReceiveTimeMillis() {
        Object obj = this.mFitnessData.get(40048);
        if (obj instanceof Long) {
            return ((Long) obj).longValue();
        }
        return 0L;
    }

    public void setReceiveTimeMillis(long j) {
        this.mFitnessData.put(40048, Long.valueOf(j));
    }

    public int getConfigTimeJumpTime() {
        Object obj = this.mFitnessData.get(40049);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public void setConfigTimeJumpTime(int i) {
        this.mFitnessData.put(40049, Integer.valueOf(i));
    }

    public int getConfigNumberJumpCount() {
        Object obj = this.mFitnessData.get(40050);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public void setConfigNumberJumpCount(int i) {
        this.mFitnessData.put(40050, Integer.valueOf(i));
    }

    public int getConfigIntermittentJumpTime() {
        Object obj = this.mFitnessData.get(40055);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public void setConfigIntermittentJumpTime(int i) {
        this.mFitnessData.put(40055, Integer.valueOf(i));
    }

    public int getConfigIntermittentJumpSingleCount() {
        Object obj = this.mFitnessData.get(40053);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public void setConfigIntermittentJumpSingleCount(int i) {
        this.mFitnessData.put(40053, Integer.valueOf(i));
    }

    public int getConfigIntermittentJumpResetTime() {
        Object obj = this.mFitnessData.get(40056);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public void setConfigIntermittentJumpResetTime(int i) {
        this.mFitnessData.put(40056, Integer.valueOf(i));
    }

    public int getConfigIntermittentJumpCount() {
        Object obj = this.mFitnessData.get(40054);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public void setConfigIntermittentJumpCount(int i) {
        this.mFitnessData.put(40054, Integer.valueOf(i));
    }

    public int getCourseId() {
        Object obj = this.mFitnessData.get(40060);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public void setCourseId(int i) {
        this.mFitnessData.put(40060, Integer.valueOf(i));
    }

    public void setMusicId(int i) {
        this.mFitnessData.put(40070, Integer.valueOf(i));
    }

    public int getMusicId() {
        Object obj = this.mFitnessData.get(40070);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    @Override // com.huawei.health.ecologydevice.fitness.datastruct.BaseRopeData
    public BaseRopeData parseData() {
        int i = this.mCode;
        if (i == 0) {
            parseRopeModeSetting(this.mData, this.mOffset, this.mFlag);
        } else if (i == 2) {
            parseTimeJump(this.mData, this.mOffset);
        } else if (i == 3) {
            parseNumberJump(this.mData, this.mOffset);
        } else if (i == 5) {
            parseConfigIntermittentJump(this.mData, this.mOffset);
        } else if (i == 6) {
            parseCourseId(this.mData, this.mOffset);
        } else if (i == 7) {
            parseMusicId(this.mData, this.mOffset);
        }
        setOpCode(this.mCode);
        setReceiveTimeMillis(System.currentTimeMillis());
        return this;
    }

    private void parseRopeModeSetting(byte[] bArr, int i, int i2) {
        if ((i2 & 1) != 0) {
            LogUtil.d(TAG, "has free rope");
        }
        if ((i2 & 2) != 0) {
            i = parseTimeJump(bArr, i);
        }
        if ((i2 & 4) != 0) {
            i = parseNumberJump(bArr, i);
        }
        if ((i2 & 8) != 0) {
            LogUtil.d(TAG, "has fancy jump rope");
        }
        if ((i2 & 16) != 0) {
            i = parseConfigIntermittentJump(bArr, i);
        }
        if ((i2 & 32) != 0) {
            parseCourseId(bArr, i);
        }
        if ((i2 & 64) != 0) {
            parseMusicId(bArr, i);
        }
    }

    private int parseTimeJump(byte[] bArr, int i) {
        int e = cyw.e(bArr, 18, i);
        LogUtil.d(TAG, "parseTimeJump() jumpTime = ", Integer.valueOf(e));
        setConfigTimeJumpTime(e);
        return i + 2;
    }

    private int parseNumberJump(byte[] bArr, int i) {
        int e = cyw.e(bArr, 18, i);
        LogUtil.d(TAG, "parseNumberJump() jumpCount = ", Integer.valueOf(e));
        setConfigNumberJumpCount(e);
        return i + 2;
    }

    private int parseConfigIntermittentJump(byte[] bArr, int i) {
        int e = cyw.e(bArr, 18, i);
        setConfigIntermittentJumpTime(e);
        int e2 = cyw.e(bArr, 18, i + 2);
        setConfigIntermittentJumpSingleCount(e2);
        int e3 = cyw.e(bArr, 18, i + 4);
        setConfigIntermittentJumpResetTime(e3);
        int e4 = cyw.e(bArr, 17, i + 6);
        setConfigIntermittentJumpCount(e4);
        LogUtil.d(TAG, "parseConfigIntermittentJump() jumpTime = ", Integer.valueOf(e), " jumpCount = ", Integer.valueOf(e2), " resetTime = ", Integer.valueOf(e3), " jumpTimes = ", Integer.valueOf(e4));
        return i + 7;
    }

    private void parseCourseId(byte[] bArr, int i) {
        int e = cyw.e(bArr, 18, i);
        setCourseId(e);
        LogUtil.d(TAG, "parseCourseId() courseId = ", Integer.valueOf(e));
    }

    private void parseMusicId(byte[] bArr, int i) {
        int e = cyw.e(bArr, 18, i);
        setMusicId(e);
        LogUtil.d(TAG, "parseMusicId() musicId = ", Integer.valueOf(e));
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
        } else if (i == 2 || i == 3) {
            bArr = new byte[2];
            cyw.c(bArr, this.mPara[0], 0);
        } else if (i == 5) {
            bArr = new byte[7];
            cyw.e(bArr, 0, this.mPara, 0, 3);
            cyw.a(bArr, this.mPara[3], 6);
        } else if (i == 6 || i == 7) {
            bArr = new byte[2];
            cyw.c(bArr, this.mPara[0], 0);
        } else {
            bArr = new byte[0];
        }
        cVar.e(bArr);
        return cVar.d();
    }
}
