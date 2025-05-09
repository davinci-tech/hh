package defpackage;

import android.util.SparseArray;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes3.dex */
public class cwo {
    private static boolean a() {
        boolean z = false;
        if (!g()) {
            return false;
        }
        DeviceCapability d = cvs.d();
        if (d != null && d.isSupportEcgAuth()) {
            z = true;
        }
        LogUtil.a("EcgFilterUtil", "hasEcgCollectionDevice: ", Boolean.valueOf(z));
        return z;
    }

    private static boolean i() {
        DeviceInfo e = e();
        boolean z = d(e) && cwi.c(e, 106);
        LogUtil.a("EcgFilterUtil", "hasEcgDiagramDevice: ", Boolean.valueOf(z));
        return z;
    }

    private static DeviceInfo e() {
        return cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "EcgFilterUtil");
    }

    private static boolean g() {
        return d(e());
    }

    private static boolean d(DeviceInfo deviceInfo) {
        return deviceInfo != null && deviceInfo.getDeviceConnectState() == 2;
    }

    private static boolean d() {
        return d(new int[]{31002});
    }

    private static boolean c() {
        return d(new int[]{DicDataTypeUtil.DataType.ELECTROCARDIOGRAM.value()});
    }

    private static boolean d(int[] iArr) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(0L);
        hiDataReadOption.setEndTime(System.currentTimeMillis());
        hiDataReadOption.setCount(1);
        hiDataReadOption.setType(iArr);
        final Object[] objArr = {null};
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        HiHealthNativeApi.a(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: cwo.5
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                objArr[0] = obj;
                countDownLatch.countDown();
            }
        });
        try {
            if (!countDownLatch.await(1000L, TimeUnit.MILLISECONDS)) {
                LogUtil.b("EcgFilterUtil", "wait time out");
            }
            boolean e = e(objArr[0]);
            LogUtil.a("EcgFilterUtil", "hasEcgData dataTypes: ", Arrays.toString(iArr), " result: ", Boolean.valueOf(e));
            return e;
        } catch (InterruptedException unused) {
            LogUtil.b("EcgFilterUtil", "hasEcgCollectionData InterruptedException");
            return false;
        }
    }

    private static boolean e(Object obj) {
        return (obj instanceof SparseArray) && ((SparseArray) obj).size() > 0;
    }

    public static boolean b() {
        boolean i = i();
        return (!i && a()) || !(i || c() || !d());
    }
}
