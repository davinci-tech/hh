package defpackage;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.appgallery.agd.api.AgdConstant;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class jxi {

    public static class c extends Handler {
        private WeakReference<jwy> e;

        public c(jwy jwyVar, Looper looper) {
            super(looper);
            this.e = new WeakReference<>(jwyVar);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            WeakReference<jwy> weakReference = this.e;
            if (weakReference == null) {
                LogUtil.h("HwBasicDataUtil", "HwFitnessManagerHandler mFitnessManager is null");
                return;
            }
            jwy jwyVar = weakReference.get();
            if (jwyVar == null) {
                LogUtil.h("HwBasicDataUtil", "HwFitnessManagerHandler hwFitnessManager is null");
            } else if (jwyVar.o == null) {
                LogUtil.h("HwBasicDataUtil", "HwFitnessManagerHandler mManagerHelper is null");
            } else {
                jwyVar.o.bKI_(message);
            }
        }
    }

    public static boolean e() {
        int e = CommonUtil.e(new SimpleDateFormat("HH").format(new Date()), -1);
        return e >= 0 && e < 6;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0044  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean c(com.huawei.health.devicemgr.business.entity.DeviceInfo r3) {
        /*
            java.lang.String r0 = "HwBasicDataUtil"
            com.huawei.health.devicemgr.business.entity.DeviceInfo r1 = b(r0)
            java.lang.String r2 = "Step_HwBasicDataUtil"
            if (r3 != 0) goto L17
            if (r1 != 0) goto L17
            java.lang.String r3 = "sync data wrong, no device connected."
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r2, r3)
            goto L35
        L17:
            if (r3 == 0) goto L37
            android.content.Context r1 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            com.huawei.hwversionmgr.manager.HwVersionManager r1 = com.huawei.hwversionmgr.manager.HwVersionManager.c(r1)
            java.lang.String r3 = r3.getDeviceIdentify()
            boolean r3 = r1.o(r3)
            if (r3 == 0) goto L37
            java.lang.String r3 = "sync data wrong, device ota."
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.e(r2, r3)
        L35:
            r3 = 0
            goto L42
        L37:
            java.lang.String r3 = "syncFitnessDetailData is other."
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            com.huawei.hwlogsmodel.LogUtil.h(r0, r3)
            r3 = 1
        L42:
            if (r3 != 0) goto L49
            java.lang.String r0 = "com.huawei.health.fitness_detail_sync_fail"
            a(r0)
        L49:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jxi.c(com.huawei.health.devicemgr.business.entity.DeviceInfo):boolean");
    }

    public static void a(String str) {
        LogUtil.a("HwBasicDataUtil", "sendSyncCompleteBroadcastToHealth.", str);
        try {
            BroadcastManagerUtil.bFH_(BaseApplication.getContext(), new Intent(str), LocalBroadcast.c, true);
        } catch (Exception e) {
            ReleaseLogUtil.c("HwBasicDataUtil", "sendBroadcast RuntimeException", ExceptionUtils.d(e));
        }
    }

    public static void e(final DeviceInfo deviceInfo) {
        LogUtil.a("HwBasicDataUtil", "registerDeviceToHiHealth enter.");
        if (deviceInfo == null) {
            LogUtil.h("HwBasicDataUtil", "registerDeviceToHiHealth deviceInfo is null");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: jxi.1
                @Override // java.lang.Runnable
                public void run() {
                    keg.d(DeviceInfo.this);
                }
            });
        }
    }

    public static long b(jwy jwyVar) {
        LogUtil.a("HwBasicDataUtil", "getLastSyncTime enter.");
        return new jwt().e(jwyVar);
    }

    public static void e(long j, jwy jwyVar) {
        LogUtil.a("HwBasicDataUtil", "setLastSyncTime time :", Long.valueOf(j));
        jwt jwtVar = new jwt();
        jxq jxqVar = new jxq();
        jxqVar.a(j);
        jwtVar.b(jwyVar, jxqVar);
    }

    public static long d(long j) {
        String format = new SimpleDateFormat("yyyyMMdd").format(new Date(j * 1000));
        try {
            return new SimpleDateFormat("yyyyMMddhhmm").parse(format + AgdConstant.INSTALL_TYPE_DEFAULT).getTime() / 1000;
        } catch (ParseException e) {
            LogUtil.b("HwBasicDataUtil", "getBeginOfDate ParseException : ", ExceptionUtils.d(e));
            return j;
        }
    }

    public static void d(String str, int i) {
        LogUtil.a("HwBasicDataUtil", "setDefaultDeviceReportThreshold.");
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HwBasicDataUtil", "setDefaultDeviceReportThreshold deviceIdentify is null");
            return;
        }
        DeviceCapability c2 = c(str);
        if (c2 == null) {
            LogUtil.h("HwBasicDataUtil", "setDefaultDeviceReportThreshold deviceCapability is null");
        } else if (!c2.isSupportThreshold()) {
            LogUtil.h("HwBasicDataUtil", "setDefaultDeviceReportThreshold is not support");
        } else {
            jxk.b(jqe.d(i));
        }
    }

    public static void b(int i, DeviceInfo deviceInfo) {
        LogUtil.a("HwBasicDataUtil", "getHealthDataByFrame dataIndex :", Integer.valueOf(i));
        if (b(deviceInfo) == 0) {
            jxk.a(i, deviceInfo);
        } else if (b(deviceInfo) == 1) {
            jxk.b(i, deviceInfo);
        } else {
            LogUtil.h("HwBasicDataUtil", "getHealthDataByFrame unknown type.");
        }
    }

    public static int b(DeviceInfo deviceInfo) {
        int i = -1;
        if (deviceInfo == null) {
            return -1;
        }
        DeviceCapability c2 = c(deviceInfo.getDeviceIdentify());
        if (c2 != null) {
            i = c2.getFitnessFrameType();
        } else {
            LogUtil.h("HwBasicDataUtil", "getDeviceDataType deviceCapability is null.");
        }
        LogUtil.c("HwBasicDataUtil", "getDeviceDataType deviceDataType :", Integer.valueOf(i));
        return i;
    }

    public static DeviceCapability c(String str) {
        DeviceCapability deviceCapability = null;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HwBasicDataUtil", "getCapability mac is null.");
            return null;
        }
        Map<String, DeviceCapability> queryDeviceCapability = jsz.b(BaseApplication.getContext()).queryDeviceCapability(2, str, "HwBasicDataUtil");
        if (queryDeviceCapability != null && !queryDeviceCapability.isEmpty()) {
            Iterator<Map.Entry<String, DeviceCapability>> it = queryDeviceCapability.entrySet().iterator();
            while (it.hasNext() && (deviceCapability = it.next().getValue()) == null) {
            }
        }
        return deviceCapability;
    }

    public static boolean a(DeviceInfo deviceInfo) {
        return deviceInfo != null && deviceInfo.getProductType() == 7;
    }

    public static int d(DeviceInfo deviceInfo) {
        if (deviceInfo != null) {
            return deviceInfo.getDeviceProtocol();
        }
        return -1;
    }

    public static DeviceInfo d(String str) {
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ACTIVE_DEVICES, null, str);
        if (deviceList == null) {
            LogUtil.h("HwBasicDataUtil", "getActiveDeviceInfo deviceInfoList is null.", str);
            return null;
        }
        Iterator<DeviceInfo> it = deviceList.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DeviceInfo next = it.next();
            if (cvt.c(next.getProductType())) {
                deviceInfo = next;
                break;
            }
        }
        LogUtil.a("HwBasicDataUtil", "getActiveDeviceInfo deviceInfo ", deviceInfo, ", tag is ", str);
        return deviceInfo;
    }

    public static String d() {
        DeviceInfo d = d("HwBasicDataUtil");
        if (d == null) {
            return "";
        }
        return d.getSecurityUuid() + "#ANDROID21";
    }

    public static DeviceInfo b(String str) {
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_AW70_DEVICES, null, str);
        if (deviceList != null && !deviceList.isEmpty()) {
            deviceInfo = deviceList.get(0);
        }
        LogUtil.a("HwBasicDataUtil", "getConnectAw70DeviceInfo() deviceInfo ", deviceInfo, " , tag is ", str);
        return deviceInfo;
    }

    public static DeviceInfo e(String str) {
        DeviceInfo deviceInfo = null;
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, str);
        if (deviceList != null && !deviceList.isEmpty()) {
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
        LogUtil.a("HwBasicDataUtil", "getConnectDeviceInfo() deviceInfo ", deviceInfo, " , tag is ", str);
        return deviceInfo;
    }

    public static void d(boolean z) {
        LogUtil.a("HwBasicDataUtil", "setFitnessTemperatureControl() in isControlFlag = ", Boolean.valueOf(z));
        SharedPreferenceManager.e("fitness_module", "key_fitness_is_foreground", z);
    }

    public static boolean c() {
        return SharedPreferenceManager.a("fitness_module", "key_fitness_is_foreground", false);
    }
}
