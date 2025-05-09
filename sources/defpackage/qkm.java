package defpackage;

import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes6.dex */
public class qkm {
    private static void c(int i, int i2) {
        long d = jec.d(System.currentTimeMillis());
        int i3 = i;
        while (i3 < i2) {
            HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
            int i4 = 0;
            while (i4 < 7) {
                long j = d - (((i3 * 7) + i4) * 86400000);
                for (int i5 = 0; i5 < 1440; i5 += 25) {
                    long j2 = j + (i5 * 60000);
                    hiDataInsertOption.addData(e(j2, j2, 2002, b(i5)));
                }
                hiDataInsertOption.addData(e(j, j, 2018, scy.c(60, 70)));
                hiDataInsertOption.addData(e(j, j, 2018, scy.c(60, 70)));
                hiDataInsertOption.addData(e(j, j, 2018, scy.c(60, 70)));
                i4++;
                d = d;
            }
            c(hiDataInsertOption, new IBaseResponseCallback() { // from class: qkm.5
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i6, Object obj) {
                }
            }, 1);
            i3++;
            d = d;
        }
    }

    private static double b(int i) {
        int c;
        if (i < 300) {
            c = scy.c(40, 90);
        } else if (i < 660) {
            c = scy.c(60, 200);
        } else if (i < 1020) {
            c = scy.c(80, 140);
        } else {
            c = scy.c(60, 200);
        }
        return c;
    }

    public static void a() {
        c(0, 1);
    }

    public static void d() {
        c(1, 4);
    }

    private static HiHealthData e(long j, long j2, int i, double d) {
        HiHealthData hiHealthData = new HiHealthData(i);
        hiHealthData.setDeviceUuid("-1");
        hiHealthData.setStartTime(j);
        hiHealthData.setEndTime(j2);
        hiHealthData.setValue(d);
        return hiHealthData;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(final HiDataInsertOption hiDataInsertOption, final IBaseResponseCallback iBaseResponseCallback, final int i) {
        if (i < 0) {
            LogUtil.a("HeartDataMockUtil", "tryTime < 0 ");
        } else {
            HiHealthNativeApi.a(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: qkm.4
                @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
                public void onResult(int i2, Object obj) {
                    LogUtil.a("HeartDataMockUtil", "insertHiHealthData errorCode = ", Integer.valueOf(i2));
                    IBaseResponseCallback.this.d(i2, null);
                    if (i2 != 0) {
                        int i3 = i - 1;
                        LogUtil.a("HeartDataMockUtil", "count = ", Integer.valueOf(i3));
                        qkm.c(hiDataInsertOption, IBaseResponseCallback.this, i3);
                    }
                }
            });
        }
    }
}
