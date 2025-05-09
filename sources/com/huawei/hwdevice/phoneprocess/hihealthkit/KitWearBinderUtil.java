package com.huawei.hwdevice.phoneprocess.hihealthkit;

import android.text.TextUtils;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.hihealthkit.AuthSupport;
import com.huawei.hwdevice.phoneprocess.hihealthkit.KitWearBinderUtil;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cun;
import defpackage.cvt;
import defpackage.cwi;
import defpackage.jst;
import defpackage.jsz;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes5.dex */
public class KitWearBinderUtil {
    private static final byte[] e = {123, 2, 0, 5};
    private static final int d = 4;
    private static final int b = 6;

    private static boolean b(byte[] bArr) {
        if (bArr == null || bArr.length < b) {
            return false;
        }
        for (int i = 0; i < d; i++) {
            if (e[i] != bArr[i + 2]) {
                return false;
            }
        }
        LogUtil.a("HwKitWearBinderUtil", "isForTheme fix.");
        return true;
    }

    public static int a(String str) {
        FileInputStream fileInputStream = null;
        try {
            try {
                fileInputStream = BaseApplication.getContext().openFileInput(str);
                int available = fileInputStream.available();
                if (fileInputStream == null) {
                    return available;
                }
                try {
                    fileInputStream.close();
                    return available;
                } catch (IOException unused) {
                    LogUtil.b("HwKitWearBinderUtil", "openFileInput close error IOException");
                    return available;
                }
            } catch (IOException unused2) {
                LogUtil.b("HwKitWearBinderUtil", "openFileInput IOException");
                if (fileInputStream != null) {
                    try {
                        fileInputStream.close();
                    } catch (IOException unused3) {
                        LogUtil.b("HwKitWearBinderUtil", "openFileInput close error IOException");
                    }
                }
                return 0;
            }
        } catch (Throwable th) {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException unused4) {
                    LogUtil.b("HwKitWearBinderUtil", "openFileInput close error IOException");
                }
            }
            throw th;
        }
    }

    public static boolean d(DeviceInfo deviceInfo, byte[] bArr) {
        if (deviceInfo == null) {
            LogUtil.h("HwKitWearBinderUtil", "device info is null.");
            return false;
        }
        if (!b(bArr)) {
            return false;
        }
        DeviceCapability d2 = jst.d(deviceInfo.getDeviceIdentify());
        if (d2 == null) {
            LogUtil.h("HwKitWearBinderUtil", "isForThemeData capability is null. please check.");
            return false;
        }
        if (!d2.isSupportWatchFaceAppId()) {
            return false;
        }
        KitWearBinder.handleDataReceive(bArr);
        return true;
    }

    public static DeviceInfo d(String str) {
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, str);
        if (deviceList.size() > 0) {
            Iterator<DeviceInfo> it = deviceList.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                DeviceInfo next = it.next();
                if (!cvt.c(next.getProductType())) {
                    deviceInfo = next;
                    break;
                }
            }
        }
        Object[] objArr = new Object[4];
        objArr[0] = "getConnectMainNotAw70Device caller: ";
        objArr[1] = str;
        objArr[2] = ", has device: ";
        objArr[3] = Boolean.valueOf(deviceInfo != null);
        ReleaseLogUtil.e("R_HwKitWearBinderUtil", objArr);
        return deviceInfo;
    }

    public static boolean a(DeviceInfo deviceInfo) {
        return cwi.c(deviceInfo, 66);
    }

    public static DevicePermissionResult b(DeviceInfo deviceInfo) {
        if (deviceInfo == null) {
            ReleaseLogUtil.c("R_HwKitWearBinderUtil", "getAuthorizationStatus deviceInfo is null");
            return DevicePermissionResult.OTHER_ERROR;
        }
        if (TextUtils.isEmpty(deviceInfo.getDeviceIdentify())) {
            ReleaseLogUtil.c("R_HwKitWearBinderUtil", "getAuthorizationStatus deviceIdentify is null");
            return DevicePermissionResult.OTHER_ERROR;
        }
        DeviceCommand deviceCommand = new DeviceCommand();
        deviceCommand.setServiceID(37);
        deviceCommand.setCommandID(15);
        jsz.b(BaseApplication.getContext()).sendDeviceData(deviceCommand);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final DevicePermissionResult[] devicePermissionResultArr = {DevicePermissionResult.OTHER_ERROR};
        AuthSupport.d().e(deviceInfo.getDeviceIdentify(), new AuthSupport.IAuthCallback() { // from class: jts
            @Override // com.huawei.hwdevice.phoneprocess.hihealthkit.AuthSupport.IAuthCallback
            public final void onResult(int i, String str) {
                KitWearBinderUtil.e(devicePermissionResultArr, countDownLatch, i, str);
            }
        });
        try {
            boolean await = countDownLatch.await(5000L, TimeUnit.MILLISECONDS);
            ReleaseLogUtil.e("HwKitWearBinderUtil", "getAuthorizationStatus isOnTime ", Boolean.valueOf(await));
            if (!await) {
                AuthSupport.d().d(deviceInfo.getDeviceIdentify());
            }
        } catch (InterruptedException e2) {
            ReleaseLogUtil.c("HwKitWearBinderUtil", "getAuthorizationStatus InterruptedException: ", e2.getMessage());
            AuthSupport.d().d(deviceInfo.getDeviceIdentify());
        }
        ReleaseLogUtil.e("HwKitWearBinderUtil", "getAuthorizationStatus: ", Integer.valueOf(devicePermissionResultArr[0].getValue()));
        return devicePermissionResultArr[0];
    }

    public static /* synthetic */ void e(DevicePermissionResult[] devicePermissionResultArr, CountDownLatch countDownLatch, int i, String str) {
        ReleaseLogUtil.e("R_HwKitWearBinderUtil", "auth resultCode: ", Integer.valueOf(i), ", msg: ", str);
        if (i == 100) {
            devicePermissionResultArr[0] = DevicePermissionResult.GRANTED;
        } else if (i == 1) {
            devicePermissionResultArr[0] = DevicePermissionResult.OTHER_ERROR;
        } else {
            devicePermissionResultArr[0] = DevicePermissionResult.DENIED;
        }
        countDownLatch.countDown();
    }

    public enum DevicePermissionResult {
        GRANTED(100),
        DENIED(1025),
        OTHER_ERROR(1);

        int value;

        DevicePermissionResult(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }
}
