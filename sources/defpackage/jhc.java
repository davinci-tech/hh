package defpackage;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.huawei.appgallery.agd.api.AgdConstant;
import com.huawei.datatype.RunPaceZoneConfig;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.healthdatamgr.api.HealthDataMgrApi;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf;
import com.huawei.hwcommonmodel.fitnessdatatype.MotionGoal;
import com.huawei.hwcommonmodel.fitnessdatatype.StudentHeartRateZoneMgr;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.up.model.UserInfomation;
import health.compact.a.LocalBroadcast;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import java.lang.ref.WeakReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* loaded from: classes5.dex */
public class jhc {
    public static void a(Context context) {
        if (context == null) {
            LogUtil.h("HwFitnessManagerRunnable", "sendDetailSyncSuccessBroadcastToHealth context is null");
            return;
        }
        LogUtil.a("05", 1, "HwFitnessManagerRunnable", "sendDetailSyncSuccessBroadcastToHealth.");
        Intent intent = new Intent("com.huawei.health.fitness_detail_sync_success");
        intent.setPackage(BaseApplication.getContext().getPackageName());
        context.sendBroadcast(intent, LocalBroadcast.c);
    }

    public static void d(Context context) {
        if (context == null) {
            LogUtil.h("HwFitnessManagerRunnable", "sendDetailSyncFailBroadcastToHealth context is null");
            return;
        }
        LogUtil.a("05", 1, "HwFitnessManagerRunnable", "sendDetailSyncFailBroadcastToHealth.");
        Intent intent = new Intent("com.huawei.health.fitness_detail_sync_fail");
        intent.setPackage(BaseApplication.getContext().getPackageName());
        context.sendBroadcast(intent, LocalBroadcast.c);
    }

    public static void h(DeviceInfo deviceInfo) {
        LogUtil.a("05", 1, "HwFitnessManagerRunnable", "registerDeviceToHiHealth enter.");
        if (deviceInfo != null) {
            jpp.i(deviceInfo);
        } else {
            LogUtil.h("HwFitnessManagerRunnable", "registerDeviceToHiHealth deviceInfo is null");
        }
    }

    public static void a(DeviceInfo deviceInfo) {
        if (deviceInfo != null && jhb.a() && jhb.d(deviceInfo.getDeviceIdentify())) {
            jho.e(deviceInfo);
        }
    }

    public static long a(long j) {
        String format = new SimpleDateFormat("yyyyMMdd").format(new Date(j * 1000));
        try {
            return new SimpleDateFormat("yyyyMMddhhmm").parse(format + AgdConstant.INSTALL_TYPE_DEFAULT).getTime() / 1000;
        } catch (ParseException e2) {
            LogUtil.b("HwFitnessManagerRunnable", "getBeginOfDate ParseException : ", ExceptionUtils.d(e2));
            return j;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x004a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static boolean b(com.huawei.health.devicemgr.business.entity.DeviceInfo r3, android.content.Context r4) {
        /*
            java.lang.String r0 = "HwFitnessManagerRunnable"
            com.huawei.health.devicemgr.business.entity.DeviceInfo r1 = defpackage.jpu.e(r0)
            java.lang.String r2 = "R_HeartRate_HwFitnessManagerRunnable"
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
            goto L48
        L37:
            r3 = 1
            java.lang.Integer r1 = java.lang.Integer.valueOf(r3)
            java.lang.String r2 = "syncFitnessDetailData is other."
            java.lang.Object[] r0 = new java.lang.Object[]{r1, r0, r2}
            java.lang.String r1 = "05"
            com.huawei.hwlogsmodel.LogUtil.h(r1, r0)
        L48:
            if (r3 != 0) goto L4d
            d(r4)
        L4d:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jhc.b(com.huawei.health.devicemgr.business.entity.DeviceInfo, android.content.Context):boolean");
    }

    public static long d(jhg jhgVar) {
        LogUtil.a("05", 1, "HwFitnessManagerRunnable", "getLastSyncTime enter.");
        return new jhh().b(jhgVar);
    }

    public static void e(long j, jhg jhgVar) {
        LogUtil.a("HwFitnessManagerRunnable", "setLastSyncTime time :", Long.valueOf(j));
        jhh jhhVar = new jhh();
        jib jibVar = new jib();
        jibVar.e(j);
        jhhVar.b(jhgVar, jibVar);
    }

    public static void e(final UserInfomation userInfomation) {
        LogUtil.a("05", 1, "setDeviceUserInfo.");
        ((HealthDataMgrApi) Services.a("HWHealthDataMgr", HealthDataMgrApi.class)).getLastVo2maxForMaxMet(new IBaseResponseCallback() { // from class: jhc.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "HwFitnessManagerRunnable");
                if (deviceList == null || deviceList.isEmpty()) {
                    LogUtil.h("HwFitnessManagerRunnable", "setDeviceUserInfo deviceInfoList is null");
                    return;
                }
                for (int i2 = 0; i2 < deviceList.size(); i2++) {
                    DeviceInfo deviceInfo = deviceList.get(i2);
                    if (deviceInfo != null) {
                        jhc.b(obj, UserInfomation.this, deviceInfo);
                    } else {
                        LogUtil.h("HwFitnessManagerRunnable", "ACTION_FITNESS_USERINFO_UPDATED currentInfo is null");
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(Object obj, UserInfomation userInfomation, DeviceInfo deviceInfo) {
        if (obj instanceof String) {
            String str = (String) obj;
            String[] split = str.split(Constants.LINK);
            if (split.length < 2) {
                LogUtil.h("HwFitnessManagerRunnable", "data.length < 2");
                return;
            }
            if (str.contains(Constants.LINK)) {
                try {
                    userInfomation.setMaxVo2(Integer.parseInt(split[1]));
                    userInfomation.setVo2Time(Long.parseLong(split[0]) / 1000);
                } catch (NumberFormatException e2) {
                    LogUtil.b("HwFitnessManagerRunnable", "setDeviceUserInfo Exception : ", ExceptionUtils.d(e2));
                }
                LogUtil.a("HwFitnessManagerRunnable", "setDeviceUserInfo information setMaxVo2 :", split[1], ", setVo2Time :", split[0]);
                jho.c(userInfomation, deviceInfo);
                jin.e(userInfomation);
                return;
            }
            jho.c(userInfomation, deviceInfo);
            jin.e(userInfomation);
            LogUtil.a("HwFitnessManagerRunnable", "setDeviceUserInfo getVo2MaxValue not contains split symble.");
            return;
        }
        jho.c(userInfomation, deviceInfo);
        jin.e(userInfomation);
        LogUtil.h("HwFitnessManagerRunnable", "setDeviceUserInfo objectData is not string.");
    }

    public static int c(DeviceInfo deviceInfo) {
        if (deviceInfo != null) {
            return deviceInfo.getDeviceProtocol();
        }
        return -1;
    }

    public static int a() {
        int i;
        DeviceCapability d2 = cvs.d();
        if (d2 != null) {
            i = d2.getFitnessFrameType();
        } else {
            LogUtil.h("HwFitnessManagerRunnable", "getDeviceDataType deviceCapability is null.");
            i = -1;
        }
        LogUtil.c("HwFitnessManagerRunnable", "getDeviceDataType deviceDataType :", Integer.valueOf(i));
        return i;
    }

    public static boolean d(DeviceInfo deviceInfo) {
        return deviceInfo != null && deviceInfo.getProductType() == 7;
    }

    public static RunPaceZoneConfig d() {
        LogUtil.a("HwFitnessManagerRunnable", "getRunPaceZoneConfig enter.");
        ffg a2 = fhq.a();
        RunPaceZoneConfig runPaceZoneConfig = new RunPaceZoneConfig();
        runPaceZoneConfig.setEasyPaceZoneMinValue(a2.j());
        runPaceZoneConfig.setMarathonPaceZoneMinValue(a2.b());
        runPaceZoneConfig.setLactatePaceZoneMinValue(a2.d());
        runPaceZoneConfig.setAnaerobicPaceZoneMinValue(a2.c());
        runPaceZoneConfig.setMaxOxygenPaceZoneMinValue(a2.e());
        runPaceZoneConfig.setMaxOxygenPaceZoneMaxValue(a2.a());
        return runPaceZoneConfig;
    }

    public static void b(int i, IBaseResponseCallback iBaseResponseCallback, Context context) {
        LogUtil.a("HwFitnessManagerRunnable", "saveCoreSleepButton enable :", Integer.valueOf(i));
        jgz.b(context, i, iBaseResponseCallback);
    }

    public static void d(int i, IBaseResponseCallback iBaseResponseCallback, Context context) {
        LogUtil.a("HwFitnessManagerRunnable", "saveHeartRateButton enable :", Integer.valueOf(i));
        jgz.a(context, i, iBaseResponseCallback);
    }

    public static void a(boolean z, Context context) {
        LogUtil.a("05", 1, "HwFitnessManagerRunnable", "changeCoreSleepButtonStatusValue isEnabled: ", Boolean.valueOf(z));
        b(z ? 1 : 0, new IBaseResponseCallback() { // from class: jhc.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (i == 0) {
                    LogUtil.a("HwFitnessManagerRunnable", "changeCoreSleepButtonStatusValue saveCoreSleepButton success.");
                    joy.d().e();
                }
            }
        }, context);
    }

    public static void f() {
        LogUtil.a("HwFitnessManagerRunnable", "processRunPaceZoneConfig enter");
        DeviceInfo a2 = jpt.a("HwFitnessManagerRunnable");
        if (a2 == null || a2.getDeviceConnectState() != 2) {
            LogUtil.h("HwFitnessManagerRunnable", "processRunPaceZoneConfig deviceInfo is null or disconnected.");
        } else {
            g();
        }
    }

    public static void g() {
        LogUtil.a("HwFitnessManagerRunnable", "enter setRunPaceZone");
        DeviceCapability d2 = cvs.d();
        if (d2 != null && d2.isSupportRunPaceSetCapability()) {
            jrq.b("HwFitnessManagerRunnable", new b());
        } else {
            LogUtil.h("HwFitnessManagerRunnable", "run pace deviceCapability is null or not support.");
        }
    }

    static class b implements Runnable {
        private b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            jho.a(jhc.d());
        }
    }

    public static class a implements Runnable {
        private int b;
        private Object e;

        a(int i, Object obj) {
            this.b = i;
            this.e = obj;
        }

        @Override // java.lang.Runnable
        public void run() {
            LogUtil.a("05", 1, "HwFitnessManagerRunnable", "setDeviceFitnessGoal objectData :", this.e);
            if (this.b == 0) {
                Object obj = this.e;
                if (obj instanceof MotionGoal) {
                    ArrayList arrayList = new ArrayList(16);
                    arrayList.add((MotionGoal) obj);
                    jho.d(arrayList);
                }
            }
        }
    }

    public static void a(int i, DeviceInfo deviceInfo) {
        LogUtil.a("05", 1, "HwFitnessManagerRunnable", "getHealthDataByFrame dataIndex :", Integer.valueOf(i));
        if (a() == 0) {
            jho.e(i, deviceInfo);
        } else if (a() == 1) {
            jho.a(i, deviceInfo);
        } else {
            LogUtil.h("HwFitnessManagerRunnable", "getHealthDataByFrame unknown type.");
        }
    }

    public static class c extends Handler {
        private WeakReference<jhg> c;

        c(jhg jhgVar, Looper looper) {
            super(looper);
            this.c = new WeakReference<>(jhgVar);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            WeakReference<jhg> weakReference = this.c;
            if (weakReference == null) {
                LogUtil.h("HwFitnessManagerRunnable", "HwFitnessManagerHandler mFitnessManager is null");
                return;
            }
            jhg jhgVar = weakReference.get();
            if (jhgVar == null) {
                LogUtil.h("HwFitnessManagerRunnable", "HwFitnessManagerHandler hwFitnessManager is null");
            } else if (jhgVar.u == null) {
                LogUtil.h("HwFitnessManagerRunnable", "HwFitnessManagerHandler mManagerHelper is null");
            } else {
                jhgVar.u.bHf_(message);
            }
        }
    }

    public static class h implements Runnable {
        private WeakReference<jhg> b;
        private IBaseResponseCallback c;
        private DeviceInfo e;

        h(jhg jhgVar, IBaseResponseCallback iBaseResponseCallback, DeviceInfo deviceInfo) {
            this.c = null;
            this.b = new WeakReference<>(jhgVar);
            this.c = iBaseResponseCallback;
            this.e = deviceInfo;
        }

        @Override // java.lang.Runnable
        public void run() {
            jhg jhgVar = this.b.get();
            if (jhgVar != null) {
                jhgVar.c(this.c, this.e);
            }
        }
    }

    public static class d implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
            DeviceInfo d;
            LogUtil.a("05", 1, "HwFitnessManagerRunnable", "SetDeviceHeartRateZoneConfigurationRunnable enter.");
            DeviceCapability d2 = cvs.d();
            if (d2 == null || !d2.isSupportHeartRateZone()) {
                LogUtil.h("HwFitnessManagerRunnable", "deviceCapability is null or not support.");
                return;
            }
            HeartZoneConf heartZoneConfForBluetoothSend = ((HealthDataMgrApi) Services.a("HWHealthDataMgr", HealthDataMgrApi.class)).getHeartZoneConfForBluetoothSend();
            if (heartZoneConfForBluetoothSend == null) {
                LogUtil.h("HwFitnessManagerRunnable", "timeout, object is null.");
                return;
            }
            if (jhb.e() && (d = jpt.d("HwFitnessManagerRunnable")) != null && jhb.d(d.getDeviceIdentify())) {
                LogUtil.h("HwFitnessManagerRunnable", "SetDeviceHeartRateZoneConfigurationRunnable fara is family pair mode");
            } else {
                LogUtil.c("HwFitnessManagerRunnable", "SetDeviceHeartRateZoneConfigurationRunnable hrZoneConf :", heartZoneConfForBluetoothSend);
                jho.c(heartZoneConfForBluetoothSend, (StudentHeartRateZoneMgr) null, false);
            }
        }
    }

    public static class e implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private boolean f13847a;
        private WeakReference<jhg> b;
        private DeviceInfo c;
        private IBaseResponseCallback e;

        e(jhg jhgVar, IBaseResponseCallback iBaseResponseCallback, boolean z, DeviceInfo deviceInfo) {
            LogUtil.a("05", 1, "HwFitnessManagerRunnable", " SyncFitnessDetailDataRunnable :", Boolean.valueOf(z));
            this.b = new WeakReference<>(jhgVar);
            this.e = iBaseResponseCallback;
            this.f13847a = z;
            this.c = deviceInfo;
        }

        @Override // java.lang.Runnable
        public void run() {
            jhg jhgVar = this.b.get();
            if (jhgVar != null) {
                jhgVar.u.a(this.e, this.f13847a, this.c);
            }
        }
    }

    public static void c() {
        LogUtil.a("HwFitnessManagerRunnable", "Enter handleStudentInfoUpdate.");
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_DEVICES, null, "HwFitnessManagerRunnable");
        if (deviceList == null || deviceList.isEmpty()) {
            return;
        }
        for (int i = 0; i < deviceList.size(); i++) {
            DeviceInfo deviceInfo = deviceList.get(i);
            if (deviceInfo != null && jhb.d(deviceInfo.getDeviceIdentify())) {
                jho.a(jhb.b(), deviceInfo);
            }
        }
    }

    public static void b() {
        LogUtil.a("05", 1, "HwFitnessManagerRunnable", "handleUserInfoChange enter.");
        LoginInit.getInstance(BaseApplication.getContext()).getUserInfoFromDb(new IBaseResponseCallback() { // from class: jhe
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                jhc.e(i, obj);
            }
        });
    }

    static /* synthetic */ void e(int i, Object obj) {
        if (i == 0 && (obj instanceof UserInfomation)) {
            LogUtil.a("HwFitnessManagerRunnable", "handleUserInfoChange getUserInfo onResponse userInfomation");
            e((UserInfomation) obj);
        } else {
            LogUtil.h("HwFitnessManagerRunnable", "handleUserInfoChange userInfo is null");
        }
    }

    public static void d(boolean z) {
        LogUtil.a("HwFitnessManagerRunnable", "setFitnessTemperatureControl() in isControlFlag = ", Boolean.valueOf(z));
        SharedPreferenceManager.e("fitness_module", "key_fitness_is_foreground", z);
    }

    public static boolean e() {
        DeviceInfo d2 = jpt.d("HwFitnessManagerRunnable");
        if (d2 == null) {
            LogUtil.h("HwFitnessManagerRunnable", "isSupportGoalListSync deviceInfo is null");
            return false;
        }
        return cwi.c(d2, 137);
    }

    public static boolean e(DeviceInfo deviceInfo) {
        return cwi.c(deviceInfo, 137);
    }

    public static boolean j() {
        DeviceInfo d2 = jpt.d("HwFitnessManagerRunnable");
        if (d2 != null) {
            return cwi.c(d2, 154) || cwi.c(d2, 156);
        }
        LogUtil.h("HwFitnessManagerRunnable", "isSupportSportGoalDataSync deviceInfo is null");
        return false;
    }

    public static boolean b(DeviceInfo deviceInfo) {
        return cwi.c(deviceInfo, 154) || cwi.c(deviceInfo, 156);
    }

    public static boolean h() {
        for (DeviceInfo deviceInfo : cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "HwFitnessManagerRunnable")) {
            if (cwi.c(deviceInfo, 154) || cwi.c(deviceInfo, 156)) {
                return true;
            }
        }
        return false;
    }
}
