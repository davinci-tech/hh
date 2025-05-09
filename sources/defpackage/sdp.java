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
import java.security.SecureRandom;
import java.util.HashMap;

/* loaded from: classes7.dex */
public class sdp {

    /* renamed from: a, reason: collision with root package name */
    private static final Float f17035a;
    private static final Float c;
    private static final Float d;
    private static final Float e;

    static {
        Float valueOf = Float.valueOf(34.0f);
        c = valueOf;
        f17035a = Float.valueOf(42.0f);
        e = valueOf;
        d = Float.valueOf(40.0f);
    }

    public static void b() {
        b(DicDataTypeUtil.DataType.BODY_TEMPERATURE_SET.value(), 0, 1);
    }

    public static void d() {
        b(DicDataTypeUtil.DataType.BODY_TEMPERATURE_SET.value(), 1, 4);
    }

    private static void b(int i, int i2, int i3) {
        long d2 = jec.d(System.currentTimeMillis());
        while (i2 < i3) {
            HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
            for (int i4 = 0; i4 < 7; i4++) {
                long j = d2 - (((i2 * 7) + i4) * 86400000);
                hiDataInsertOption.addData(e(i, j, 8));
                hiDataInsertOption.addData(e(i, j, 9));
                hiDataInsertOption.addData(e(i, j, 10));
                hiDataInsertOption.addData(e(i, j, 11));
                hiDataInsertOption.addData(e(i, j, 12));
                hiDataInsertOption.addData(e(i, j, 13));
                hiDataInsertOption.addData(e(i, j, 14));
                hiDataInsertOption.addData(e(i, j, 15));
                hiDataInsertOption.addData(e(i, j, 16));
                hiDataInsertOption.addData(e(i, j, 16));
                hiDataInsertOption.addData(e(i, j, 19));
                hiDataInsertOption.addData(e(i, j, 20));
            }
            d(hiDataInsertOption, new IBaseResponseCallback() { // from class: sdp.3
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i5, Object obj) {
                }
            }, 1);
            i2++;
        }
    }

    public static Float a(Float f, Float f2) {
        return Float.valueOf((float) (new SecureRandom().nextInt((int) (f2.floatValue() - f.floatValue())) + f.floatValue() + Math.random()));
    }

    private static HiHealthData e(int i, long j, int i2) {
        long j2 = j + (i2 * 3600000);
        return e(i, j2, j2, a(c, f17035a).floatValue());
    }

    private static HiHealthData e(int i, long j, long j2, float f) {
        HiHealthData hiHealthData = new HiHealthData(i);
        hiHealthData.setDeviceUuid("-1");
        hiHealthData.setStartTime(j);
        hiHealthData.setEndTime(j2);
        hiHealthData.setTimeInterval(j, j2);
        HashMap hashMap = new HashMap(16);
        hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.BODY_TEMPERATURE.value()), Float.valueOf(f));
        int value = DicDataTypeUtil.DataType.SKIN_TEMPERATURE.value();
        hashMap.put(Integer.valueOf(value), a(e, d));
        hiHealthData.setFieldsValue(HiJsonUtil.e(hashMap));
        return hiHealthData;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(final HiDataInsertOption hiDataInsertOption, final IBaseResponseCallback iBaseResponseCallback, final int i) {
        if (i < 0) {
            LogUtil.a("Login_TemperatureUtil", "tryTime < 0 ");
        } else {
            HiHealthNativeApi.a(BaseApplication.getContext()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: sdp.5
                @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
                public void onResult(int i2, Object obj) {
                    LogUtil.a("Login_TemperatureUtil", "insertHiHealthData errorCode = ", Integer.valueOf(i2));
                    IBaseResponseCallback.this.d(i2, null);
                    if (i2 != 0) {
                        int i3 = i - 1;
                        LogUtil.a("Login_TemperatureUtil", "count = ", Integer.valueOf(i3));
                        sdp.d(hiDataInsertOption, IBaseResponseCallback.this, i3);
                    }
                }
            });
        }
    }
}
