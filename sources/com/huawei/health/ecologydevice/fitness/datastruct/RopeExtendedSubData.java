package com.huawei.health.ecologydevice.fitness.datastruct;

import defpackage.cyf;
import defpackage.cyw;

/* loaded from: classes3.dex */
public class RopeExtendedSubData extends BaseRopeData {
    public static final int ALL_DATA_FLAG = 31;
    public static final int ENERGY_POSITION = 3;
    public static final int FLAG_POSITION = 0;
    private static final int ONE_BYTE_PARA_COUNT = 2;
    public static final int PARA_LENGTH = 6;
    private static final int ROPE_CONTROL_ROPE_EXTENDED_LENGTH = 10;
    public static final int ROPE_INTERMITTENT_GROUP_NO_POSITION = 4;
    public static final int ROPE_MACHINE_STATUS_POSITION = 5;
    public static final int SKIPPING_NUM_POSITION = 2;
    public static final int SKIPPING_SPEED_POSITION = 1;
    private static final int TWO_BYTE_PARA_COUNT = 4;

    @Override // com.huawei.health.ecologydevice.fitness.datastruct.BaseRopeData
    public cyf getTransmitCommand() {
        cyf.c cVar = new cyf.c();
        cVar.d(this.mCommand);
        cVar.b(this.mCode);
        cVar.e(this.mCode == 1 ? getCommandCodePara() : new byte[0]);
        return cVar.d();
    }

    private byte[] getCommandCodePara() {
        byte[] bArr = new byte[10];
        cyw.e(bArr, 0, this.mPara, 0, 4);
        cyw.d(bArr, 8, this.mPara, 4, 2);
        return bArr;
    }
}
