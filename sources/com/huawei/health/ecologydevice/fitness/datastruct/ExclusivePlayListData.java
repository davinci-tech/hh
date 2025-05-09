package com.huawei.health.ecologydevice.fitness.datastruct;

import com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.CourseModel;
import defpackage.cyf;
import defpackage.cyw;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public class ExclusivePlayListData extends BaseRopeData {
    private static final String TAG = "PlayListData";

    public ExclusivePlayListData() {
        super(31);
    }

    public int getVersionCode() {
        Object obj = this.mFitnessData.get(40067);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public void setVersionCode(int i) {
        this.mFitnessData.put(40067, Integer.valueOf(i));
    }

    public int getRemainingMemory() {
        Object obj = this.mFitnessData.get(40068);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public void setRemainingMemory(int i) {
        this.mFitnessData.put(40068, Integer.valueOf(i));
    }

    public void setMusicId(int i) {
        this.mFitnessData.put(40070, Integer.valueOf(i));
    }

    public List<CourseModel.Course> getCourseList() {
        Object obj = this.mFitnessData.get(40066);
        return obj instanceof List ? (List) obj : Collections.emptyList();
    }

    public void setPlayListEntityList(List<CourseModel.Course> list) {
        this.mFitnessData.put(40066, list);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:11:0x0099, code lost:
    
        return r11;
     */
    @Override // com.huawei.health.ecologydevice.fitness.datastruct.BaseRopeData
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.health.ecologydevice.fitness.datastruct.BaseRopeData parseData() {
        /*
            r11 = this;
            int r0 = r11.mDataLength
            int r1 = r11.mOffset
            int r2 = r11.mCode
            r11.setOpCode(r2)
            int r2 = r11.mCode
            java.lang.String r3 = "PlayListData"
            r4 = 18
            switch(r2) {
                case 0: goto L5f;
                case 1: goto L5f;
                case 2: goto L5f;
                case 3: goto L5f;
                case 4: goto L5f;
                case 5: goto L46;
                case 6: goto L2d;
                case 7: goto L14;
                default: goto L12;
            }
        L12:
            goto L99
        L14:
            byte[] r0 = r11.mData
            int r1 = r11.mOffset
            int r0 = defpackage.cyw.e(r0, r4, r1)
            r11.setRemainingMemory(r0)
            java.lang.String r1 = "playList remainingMemory = "
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.Object[] r0 = new java.lang.Object[]{r1, r0}
            health.compact.a.util.LogUtil.d(r3, r0)
            goto L99
        L2d:
            byte[] r0 = r11.mData
            int r1 = r11.mOffset
            int r0 = defpackage.cyw.e(r0, r4, r1)
            r11.setVersionCode(r0)
            java.lang.String r1 = "playList versionCode = "
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.Object[] r0 = new java.lang.Object[]{r1, r0}
            health.compact.a.util.LogUtil.d(r3, r0)
            goto L99
        L46:
            byte[] r0 = r11.mData
            int r1 = r11.mOffset
            int r0 = defpackage.cyw.e(r0, r4, r1)
            r11.setMusicId(r0)
            java.lang.String r1 = "The playback is successful. musicId is "
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.Object[] r0 = new java.lang.Object[]{r1, r0}
            health.compact.a.util.LogUtil.d(r3, r0)
            goto L99
        L5f:
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
        L64:
            if (r0 <= 0) goto L96
            byte[] r5 = r11.mData
            int r5 = defpackage.cyw.e(r5, r4, r1)
            int r6 = r1 + 2
            byte[] r7 = r11.mData
            r8 = 17
            int r6 = defpackage.cyw.e(r7, r8, r6)
            int r1 = r1 + 3
            java.lang.Integer r7 = java.lang.Integer.valueOf(r5)
            java.lang.String r8 = ", status is "
            java.lang.Integer r9 = java.lang.Integer.valueOf(r6)
            java.lang.String r10 = "BaseRopeData course music id is "
            java.lang.Object[] r7 = new java.lang.Object[]{r10, r7, r8, r9}
            health.compact.a.util.LogUtil.d(r3, r7)
            com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.CourseModel$Course r7 = new com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.CourseModel$Course
            r7.<init>(r5, r6)
            r2.add(r7)
            int r0 = r0 + (-3)
            goto L64
        L96:
            r11.setPlayListEntityList(r2)
        L99:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.ecologydevice.fitness.datastruct.ExclusivePlayListData.parseData():com.huawei.health.ecologydevice.fitness.datastruct.BaseRopeData");
    }

    @Override // com.huawei.health.ecologydevice.fitness.datastruct.BaseRopeData
    public cyf getTransmitCommand() {
        byte[] bArr;
        cyf.c cVar = new cyf.c();
        cVar.d(this.mCommand);
        cVar.b(this.mCode);
        int i = this.mCode;
        if (i == 1 || i == 2 || i == 3 || i == 4) {
            bArr = new byte[this.mPara.length * 2];
            cyw.e(bArr, 0, this.mPara, 0, this.mPara.length);
        } else if (i != 5) {
            bArr = new byte[0];
        } else {
            bArr = new byte[2];
            cyw.c(bArr, this.mPara[0], 0);
        }
        cVar.e(bArr);
        return cVar.d();
    }
}
