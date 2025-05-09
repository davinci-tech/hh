package com.huawei.health.ecologydevice.fitness.datastruct;

import com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.CourseModel;
import defpackage.cyf;
import defpackage.cyw;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public class RopeVoiceCourseOperationsData extends BaseRopeData {
    private static final String TAG = "RopeVoiceCourseOperationsData";

    public RopeVoiceCourseOperationsData() {
        super(28);
    }

    public int getVersionCode() {
        Object obj = this.mFitnessData.get(40045);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public void setVersionCode(int i) {
        this.mFitnessData.put(40045, Integer.valueOf(i));
    }

    public int getRemainingMemory() {
        Object obj = this.mFitnessData.get(40046);
        if (obj instanceof Integer) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    public void setRemainingMemory(int i) {
        this.mFitnessData.put(40046, Integer.valueOf(i));
    }

    public List<CourseModel.Course> getCourseList() {
        Object obj = this.mFitnessData.get(40047);
        return obj instanceof List ? (List) obj : Collections.emptyList();
    }

    public void setCourseIdList(List<CourseModel.Course> list) {
        this.mFitnessData.put(40047, list);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:10:0x0060, code lost:
    
        return r10;
     */
    @Override // com.huawei.health.ecologydevice.fitness.datastruct.BaseRopeData
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.health.ecologydevice.fitness.datastruct.BaseRopeData parseData() {
        /*
            r10 = this;
            int r0 = r10.mDataLength
            int r1 = r10.mOffset
            int r2 = r10.mCode
            r3 = 18
            switch(r2) {
                case 0: goto L24;
                case 1: goto L24;
                case 2: goto L24;
                case 3: goto L24;
                case 4: goto L24;
                case 5: goto L24;
                case 6: goto L24;
                case 7: goto L18;
                case 8: goto Lc;
                default: goto Lb;
            }
        Lb:
            goto L60
        Lc:
            byte[] r0 = r10.mData
            int r1 = r10.mOffset
            int r0 = defpackage.cyw.e(r0, r3, r1)
            r10.setRemainingMemory(r0)
            goto L60
        L18:
            byte[] r0 = r10.mData
            int r1 = r10.mOffset
            int r0 = defpackage.cyw.e(r0, r3, r1)
            r10.setVersionCode(r0)
            goto L60
        L24:
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
        L29:
            if (r0 <= 0) goto L5d
            byte[] r4 = r10.mData
            int r4 = defpackage.cyw.e(r4, r3, r1)
            int r5 = r1 + 2
            byte[] r6 = r10.mData
            r7 = 17
            int r5 = defpackage.cyw.e(r6, r7, r5)
            int r1 = r1 + 3
            java.lang.Integer r6 = java.lang.Integer.valueOf(r4)
            java.lang.String r7 = ", status is "
            java.lang.Integer r8 = java.lang.Integer.valueOf(r5)
            java.lang.String r9 = "BaseRopeData course id is "
            java.lang.Object[] r6 = new java.lang.Object[]{r9, r6, r7, r8}
            java.lang.String r7 = "RopeVoiceCourseOperationsData"
            health.compact.a.util.LogUtil.d(r7, r6)
            com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.CourseModel$Course r6 = new com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.CourseModel$Course
            r6.<init>(r4, r5)
            r2.add(r6)
            int r0 = r0 + (-3)
            goto L29
        L5d:
            r10.setCourseIdList(r2)
        L60:
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.ecologydevice.fitness.datastruct.RopeVoiceCourseOperationsData.parseData():com.huawei.health.ecologydevice.fitness.datastruct.BaseRopeData");
    }

    @Override // com.huawei.health.ecologydevice.fitness.datastruct.BaseRopeData
    public cyf getTransmitCommand() {
        byte[] bArr;
        cyf.c cVar = new cyf.c();
        cVar.d(this.mCommand);
        cVar.b(this.mCode);
        switch (this.mCode) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                bArr = new byte[this.mPara.length * 2];
                cyw.e(bArr, 0, this.mPara, 0, this.mPara.length);
                break;
            default:
                bArr = new byte[0];
                break;
        }
        cVar.e(bArr);
        return cVar.d();
    }
}
