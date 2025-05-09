package defpackage;

import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.HashMap;

/* loaded from: classes7.dex */
public class sci {
    public static void e() {
        d(0, 1);
    }

    public static void d() {
        d(1, 4);
    }

    private static void d(int i, int i2) {
        long d = jec.d(System.currentTimeMillis());
        while (i < i2) {
            HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
            for (int i3 = 0; i3 < 7; i3++) {
                long j = d - (((i * 7) + i3) * 86400000);
                hiDataInsertOption.addData(a(j, 7, 2008));
                hiDataInsertOption.addData(a(j, 9, 2009));
                hiDataInsertOption.addData(a(j, 10, 2010));
                hiDataInsertOption.addData(a(j, 13, 2011));
                hiDataInsertOption.addData(a(j, 16, 2012));
                hiDataInsertOption.addData(a(j, 20, 2013));
                hiDataInsertOption.addData(a(j, 22, 2015));
                hiDataInsertOption.addData(a(j, 23, 2014));
            }
            e(hiDataInsertOption, new IBaseResponseCallback() { // from class: sci.1
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i4, Object obj) {
                }
            }, 1);
            i++;
        }
    }

    private static HiHealthData a(long j, int i, int i2) {
        long j2 = j + (i * 3600000);
        return a(j2, j2, i2, scy.c(1, 15));
    }

    private static HiHealthData a(long j, long j2, int i, double d) {
        HiHealthData hiHealthData = new HiHealthData(10001);
        hiHealthData.setDeviceUuid("-1");
        hiHealthData.setStartTime(j);
        hiHealthData.setEndTime(j2);
        hiHealthData.setType(i);
        hiHealthData.setValue(d);
        return hiHealthData;
    }

    private static HiHealthData c(long j, long j2, double d, double d2, double d3) {
        HiHealthData hiHealthData = new HiHealthData(DicDataTypeUtil.DataType.BLOOD_PRESSURE_SET.value());
        hiHealthData.setDeviceUuid("-1");
        hiHealthData.setStartTime(j);
        hiHealthData.setEndTime(j2);
        hiHealthData.setTimeInterval(j, j2);
        HashMap hashMap = new HashMap(16);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.BLOOD_PRESSURE_SYSTOLIC.value()), Double.valueOf(d));
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.BLOOD_PRESSURE_DIASTOLIC.value()), Double.valueOf(d2));
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.BLOOD_PRESSURE_SPHYGMUS.value()), Double.valueOf(d3));
        hiHealthData.setFieldsValue(HiJsonUtil.e(hashMap));
        return hiHealthData;
    }

    private static void e(int i, int i2) {
        long d = jec.d(System.currentTimeMillis());
        for (int i3 = i; i3 < i2; i3++) {
            HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
            for (int i4 = 0; i4 < 7; i4++) {
                long j = d - (((i3 * 7) + i4) * 86400000);
                long j2 = j + 28800000;
                hiDataInsertOption.addData(c(j2, j2, scy.c(60, 160), Math.min(r10 - 5, scy.c(40, 160)), 80.0d));
                long j3 = j + 43200000;
                hiDataInsertOption.addData(c(j3, j3, scy.c(60, 160), Math.min(r10 - 5, scy.c(40, 100)), 80.0d));
                long j4 = j + 72000000;
                hiDataInsertOption.addData(c(j4, j4, scy.c(60, 160), Math.min(r6 - 5, scy.c(40, 100)), 80.0d));
            }
            e(hiDataInsertOption, new IBaseResponseCallback() { // from class: sci.2
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i5, Object obj) {
                }
            }, 1);
        }
    }

    public static void c() {
        e(0, 1);
    }

    public static void a() {
        e(1, 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(final HiDataInsertOption hiDataInsertOption, final IBaseResponseCallback iBaseResponseCallback, final int i) {
        if (i < 0) {
            LogUtil.a("Login_BPAndBSMockUtil", "tryTime < 0 ");
        } else {
            HiHealthNativeApi.a(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: sci.3
                @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
                public void onResult(int i2, Object obj) {
                    LogUtil.a("Login_BPAndBSMockUtil", "insertHiHealthData errorCode = ", Integer.valueOf(i2));
                    IBaseResponseCallback.this.d(i2, null);
                    if (i2 != 0) {
                        int i3 = i - 1;
                        LogUtil.a("Login_BPAndBSMockUtil", "count = ", Integer.valueOf(i3));
                        sci.e(hiDataInsertOption, IBaseResponseCallback.this, i3);
                    }
                }
            });
        }
    }
}
