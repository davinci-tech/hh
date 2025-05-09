package com.huawei.health.connectivity.extendstepcounter;

import android.content.Context;
import com.huawei.health.icommon.ISimpleResultCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.LogicalStepCounter;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;

/* loaded from: classes.dex */
public abstract class ExtendStepCounter {
    public static final int BAD_STEP = 1000000;
    public static final long ONE_MINUTE_MILLS = 60000;
    private static final String TAG = "Step_ExtStepCnt";
    private static final String TAG_RELEASE = "R_Step_ExtStepCnt";
    private StringBuilder mStringBuilder = new StringBuilder(16);
    private long mLastPrintTime = 0;

    public abstract void init(ISimpleResultCallback iSimpleResultCallback);

    public abstract void startStepCounter();

    public abstract void stopStepCounter();

    public void dataReport(Context context, long j, int[] iArr, int[] iArr2, int[] iArr3) {
        if (context == null) {
            ReleaseLogUtil.d(TAG, "dataReport context is null.");
            return;
        }
        if (String.valueOf(j).length() != 13) {
            ReleaseLogUtil.d(TAG, "dataReport invalid time report drop");
            return;
        }
        if (iArr == null) {
            ReleaseLogUtil.d(TAG, "dataReport invalid data report");
            return;
        }
        if (iArr.length == 0) {
            ReleaseLogUtil.d(TAG, "dataReport invalid data report2");
            return;
        }
        for (int i = 0; i < iArr.length; i++) {
            if (getCorrectData(iArr, i, 0) > 1000000) {
                ReleaseLogUtil.c(TAG, "bad step too large");
            } else {
                StringBuilder sb = this.mStringBuilder;
                sb.append("onExtend: ");
                long j2 = j + (i * 60000);
                sb.append(j2);
                sb.append(" ");
                sb.append(getCorrectData(iArr, i, 0));
                sb.append(" ");
                sb.append(getCorrectData(iArr2, i, 0));
                sb.append(" ");
                sb.append(getMotionTypeData(iArr3, i, 0));
                sb.append(" ");
                LogicalStepCounter.c(context).e(j2, getCorrectData(iArr, i, 0), getCorrectData(iArr2, i, 0), getMotionTypeData(iArr3, i, 0));
            }
        }
        long b = LogUtil.b(5000, this.mLastPrintTime);
        if (b != -1) {
            this.mLastPrintTime = b;
            ReleaseLogUtil.e(TAG, "dtRpt mStrBuld=", this.mStringBuilder.toString());
            StringBuilder sb2 = this.mStringBuilder;
            sb2.delete(0, sb2.length());
        }
    }

    private int getCorrectData(int[] iArr, int i, int i2) {
        return (iArr == null || i < 0 || i >= iArr.length) ? i2 : iArr[i];
    }

    private int getMotionTypeData(int[] iArr, int i, int i2) {
        if (iArr == null || i < 0 || i >= iArr.length) {
            return i2;
        }
        if (iArr[i] == 2) {
            iArr[i] = 4;
        }
        return iArr[i];
    }
}
