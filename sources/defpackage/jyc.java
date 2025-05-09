package defpackage;

import android.content.Context;
import com.google.gson.Gson;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.deviceconnect.callback.PingCallback;
import com.huawei.health.deviceconnect.callback.SendCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.hwcalendarmgr.calendardb.DeviceCalendarSyncInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.maps.offlinedata.health.p2p.OfflineMapWearEngineManager;
import com.huawei.unitedevice.constant.ConnectState;
import com.huawei.unitedevice.constant.WearEngineModule;
import com.huawei.unitedevice.manager.EngineLogicBaseManager;
import defpackage.spn;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes5.dex */
public class jyc extends EngineLogicBaseManager {

    /* renamed from: a, reason: collision with root package name */
    private static int f14184a = 1;
    private static int c = 2;
    private static int e = 1;
    private static volatile jyc f = null;
    private static int g = 3;
    private static int h = 3;
    private AtomicInteger i;
    private String j;
    private static final Object b = new Object();
    protected static final Object d = new Object();

    private jyc(Context context) {
        super(context);
        this.j = "";
        this.i = new AtomicInteger(0);
    }

    public static jyc b() {
        jyc jycVar;
        synchronized (b) {
            LogUtil.a("HiWearEngineCalendarManager", "getInstance()");
            if (f == null) {
                f = new jyc(BaseApplication.getContext());
            }
            jycVar = f;
        }
        return jycVar;
    }

    public void e(PingCallback pingCallback) {
        LogUtil.a("HiWearEngineCalendarManager", "pingDevice");
        pingComand(pingCallback, getWearPkgName());
    }

    public void a(String str, int i) {
        ReleaseLogUtil.e("R_HiWearEngineCalendarManager", "sendDataToDeviceByHiWear: ", str, " , type: ", Integer.valueOf(i));
        final spn.b bVar = new spn.b();
        try {
            StringBuffer stringBuffer = new StringBuffer(16);
            stringBuffer.append(str);
            String b2 = blq.b(i);
            String a2 = blq.a(b2.length() / 2);
            stringBuffer.append(blq.b(1));
            stringBuffer.append(a2);
            stringBuffer.append(b2);
            bVar.c(blq.a(stringBuffer.toString()));
        } catch (tnx unused) {
            LogUtil.b("HiWearEngineCalendarManager", "sendMessage WearEngineException");
            sqo.g("sendMessage WearEngineException");
        }
        e(new PingCallback() { // from class: jyc.3
            @Override // com.huawei.health.deviceconnect.callback.PingCallback
            public void onPingResult(int i2) {
                if (i2 == 202) {
                    jyc.this.a(bVar.e());
                } else {
                    LogUtil.b("HiWearEngineCalendarManager", "ping device calendar fail");
                }
            }
        });
    }

    public void d(String str, final boolean z) {
        LogUtil.a("HiWearEngineCalendarManager", "sendCalendarFileToDevice");
        spn.b bVar = new spn.b();
        bVar.a(new File(str));
        sendComand(bVar.e(), new SendCallback() { // from class: jyc.2
            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onFileTransferReport(String str2) {
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendResult(int i) {
                ReleaseLogUtil.e("R_HiWearEngineCalendarManager", "sendCalendarFileToDevice errorCode:", Integer.valueOf(i));
                jyc.this.c(i, z);
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendProgress(long j) {
                LogUtil.a("HiWearEngineCalendarManager", "sendCalendarFileToDevice progress:", Long.valueOf(j));
            }
        });
    }

    public void c(String str, String str2, String str3) {
        LogUtil.a("HiWearEngineCalendarManager", "responseToDeviceByHiWear: 04, eventId", str + "event_uuid", str2, "deviceUuid", str3);
        if (kat.b(str2) || kat.b(str3)) {
            LogUtil.b("HiWearEngineCalendarManager", "invalid params");
            return;
        }
        spn.b bVar = new spn.b();
        try {
            byte[] a2 = blq.a("04");
            bms bmsVar = new bms();
            bmsVar.d(1, str).d(2, str2).d(3, str3);
            byte[] d2 = bmsVar.d();
            byte[] copyOf = Arrays.copyOf(a2, a2.length + d2.length);
            System.arraycopy(d2, 0, copyOf, a2.length, d2.length);
            bVar.c(copyOf);
        } catch (tnx unused) {
            LogUtil.b("HiWearEngineCalendarManager", "sendMessage WearEngineException");
        }
        a(bVar.e());
    }

    private void d() {
        LogUtil.a("HiWearEngineCalendarManager", "tryAgainUpdated: try again SYNC UPDATE failed count:", Integer.valueOf(this.i.get()));
        if (this.i.incrementAndGet() > 3) {
            this.i.set(0);
            jyk.b().e();
        } else {
            a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 1);
        }
    }

    public void a(spn spnVar) {
        LogUtil.a("HiWearEngineCalendarManager", "sendCommandToDevice");
        sendComand(spnVar, new SendCallback() { // from class: jyc.4
            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendResult(int i) {
                ReleaseLogUtil.e("R_HiWearEngineCalendarManager", "sendCommandToDevice errorCode:", Integer.valueOf(i));
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onSendProgress(long j) {
                LogUtil.c("HiWearEngineCalendarManager", "sendCommandToDevice progress:", Long.valueOf(j));
            }

            @Override // com.huawei.health.deviceconnect.callback.SendCallback
            public void onFileTransferReport(String str) {
                LogUtil.a("HiWearEngineCalendarManager", "sendCommandToDevice onFileTransferReport transferWay: ", str);
            }
        });
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onReceiveDeviceCommand(DeviceInfo deviceInfo, int i, spn spnVar) {
        ReleaseLogUtil.e("R_HiWearEngineCalendarManager", "onReceiveDeviceCommand errorCode:", Integer.valueOf(i), Integer.valueOf(spnVar.d()));
        if (spnVar.d() == 2) {
            LogUtil.a("HiWearEngineCalendarManager", "onReceiveDeviceCommand MESSAGE_TYPE_FILE SUCCESS.");
            try {
                DeviceCalendarSyncInfo deviceCalendarSyncInfo = (DeviceCalendarSyncInfo) new Gson().fromJson(jyl.e(spnVar.a()), DeviceCalendarSyncInfo.class);
                LogUtil.a("HiWearEngineCalendarManager", "receive event count = ", Integer.valueOf(deviceCalendarSyncInfo.getCount()));
                LogUtil.a("HiWearEngineCalendarManager", "insert device's event to phone succeed?", Boolean.valueOf(jyk.b().c(deviceInfo, deviceCalendarSyncInfo, BaseApplication.getContext())));
                return;
            } catch (Exception e2) {
                LogUtil.b("HiWearEngineCalendarManager", "receive json and parse, catch exception", ExceptionUtils.d(e2));
                return;
            }
        }
        byte[] b2 = spnVar.b();
        blt.d("HiWearEngineCalendarManager", b2, "onReceiveDeviceCommand: ");
        if (b2 == null) {
            return;
        }
        byte b3 = b2[0];
        int length = b2.length;
        int i2 = e;
        int i3 = length - i2;
        byte[] bArr = new byte[i3];
        System.arraycopy(b2, i2, bArr, 0, i3);
        if (b3 == f14184a) {
            b(bArr, deviceInfo);
        } else if (b3 == g) {
            a(bArr);
        } else {
            LogUtil.b("HiWearEngineCalendarManager", "wrong commandId in payload");
            sqo.g("wrong commandId in payload");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, boolean z) {
        LogUtil.a("HiWearEngineCalendarManager", "onTransferredSucceed:");
        jyk b2 = jyk.b();
        String e2 = jyi.e(z);
        if (i == 207) {
            b2.c(this.j, e2, z);
            jyo.e(true);
            b2.e();
            jyk.b().d().remove(e2);
            return;
        }
        jyk.b().d().remove(e2);
        jyk.b().j();
        d();
    }

    private void b(byte[] bArr, final DeviceInfo deviceInfo) {
        try {
            int i = -1;
            int i2 = -1;
            final int i3 = -1;
            for (bmu bmuVar : new bmt().b(bArr).d()) {
                byte a2 = bmuVar.a();
                if (a2 == 1) {
                    i = blq.d(bmuVar.c(), -1);
                    LogUtil.a("HiWearEngineCalendarManager", "operate value is: ", Integer.valueOf(i));
                } else if (a2 == 2) {
                    this.j = new String(bmuVar.c(), StandardCharsets.UTF_8).trim();
                    LogUtil.a("HiWearEngineCalendarManager", "major version");
                } else if (a2 == 3) {
                    i2 = blq.d(bmuVar.c(), -1);
                    LogUtil.a("HiWearEngineCalendarManager", "minor version is: ", Integer.valueOf(i2));
                } else if (a2 == 4) {
                    i3 = blq.d(bmuVar.c(), -1);
                    LogUtil.a("HiWearEngineCalendarManager", "schedule_count value is: ", Integer.valueOf(i3));
                }
            }
            if (i != -1 && this.j != null && i2 != -1 && i3 != -1) {
                if (deviceInfo == null) {
                    return;
                }
                if (i == h) {
                    a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 3);
                }
                if (i == c || i == h) {
                    ThreadPoolManager.d().execute(new Runnable() { // from class: jyc.5
                        @Override // java.lang.Runnable
                        public void run() {
                            String deviceIdentify = deviceInfo.getDeviceIdentify();
                            boolean a3 = jyo.a();
                            String d2 = jyo.d(deviceIdentify, a3);
                            LogUtil.a("HiWearEngineCalendarManager", "calendarVersionRequest getSingleFrameDevice: ", Integer.valueOf(deviceInfo.getSingleFrameDevice()));
                            if (jyo.a() && CommonUtil.bh() && deviceInfo.getSingleFrameDevice() == 0) {
                                jye.e(BaseApplication.getContext());
                                ReleaseLogUtil.d("R_HiWearEngineCalendarManager", "onDeviceDisconnected: do not support calendar synchronization.");
                            } else {
                                if (deviceInfo.getSingleFrameDevice() == 1 && CommonUtil.bv()) {
                                    LogUtil.a("HiWearEngineCalendarManager", "calendarVersionRequest, The current device has a single framework and is release version.");
                                    return;
                                }
                                Boolean bool = jyk.b().d().get(d2);
                                if (bool == null || !bool.booleanValue()) {
                                    jyc.this.a(d2, i3, a3);
                                } else {
                                    LogUtil.a("HiWearEngineCalendarManager", "SyncCalendarData data syncing");
                                }
                            }
                        }
                    });
                    return;
                }
                return;
            }
            LogUtil.b("HiWearEngineCalendarManager", "payload data lake parameters ");
        } catch (bmk e2) {
            LogUtil.b("HiWearEngineCalendarManager", "calendarVersionRequest Exception ", ExceptionUtils.d(e2));
            sqo.g("calendarVersionRequest Exception");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final String str, final int i, final boolean z) {
        synchronized (d) {
            ArrayList arrayList = new ArrayList();
            arrayList.add("900100019");
            njj.d("9001", arrayList, new HiDataReadResultListener() { // from class: jyc.1
                @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                public void onResultIntent(int i2, Object obj, int i3, int i4) {
                }

                @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                public void onResult(Object obj, int i2, int i3) {
                    LogUtil.a("HiWearEngineCalendarManager", "onResult errorCode: ", Integer.valueOf(i2), ", data: ", obj);
                    jyk.b().d().put(str, true);
                    if (obj instanceof List) {
                        List list = (List) obj;
                        if (!koq.b(list) && koq.e(obj, HiSampleConfig.class)) {
                            boolean equals = "1".equals(dsl.c(((HiSampleConfig) list.get(0)).getConfigData(), "calendarToDeviceSwitch"));
                            ReleaseLogUtil.e("R_HiWearEngineCalendarManager", "getSampleConfig  CalendarSwitch: ", Boolean.valueOf(equals));
                            if (equals) {
                                jyk.b().c(str, jyc.this.j, i, z);
                                jyk.b().b(BaseApplication.getContext());
                                return;
                            }
                        }
                    }
                    jyc.b().a(HiAnalyticsConstant.KeyAndValue.NUMBER_01, 7);
                    jyk.b().d().remove(str);
                }
            });
        }
    }

    private void a(byte[] bArr) {
        blt.d("HiWearEngineCalendarManager", bArr, "calendarVersionRequest: ");
        try {
            String str = "";
            String str2 = "";
            String str3 = str2;
            String str4 = str3;
            int i = -1;
            for (bmu bmuVar : new bmt().b(bArr).d()) {
                switch (bmuVar.a()) {
                    case 1:
                        str = new String(bmuVar.c(), StandardCharsets.UTF_8).trim();
                        LogUtil.a("HiWearEngineCalendarManager", "eventId value is: ", str);
                        break;
                    case 2:
                        str2 = new String(bmuVar.c(), StandardCharsets.UTF_8).trim();
                        LogUtil.a("HiWearEngineCalendarManager", "calendarId version is: ", str2);
                        break;
                    case 3:
                        str4 = new String(bmuVar.c(), StandardCharsets.UTF_8).trim();
                        LogUtil.a("HiWearEngineCalendarManager", "account_name version is: ", str4);
                        break;
                    case 4:
                        str3 = new String(bmuVar.c(), StandardCharsets.UTF_8).trim();
                        LogUtil.a("HiWearEngineCalendarManager", "account_type value is: ", str3);
                        break;
                    case 5:
                        i = blq.d(bmuVar.c(), -1);
                        LogUtil.a("HiWearEngineCalendarManager", "state value is: ", Integer.valueOf(i));
                        break;
                    case 6:
                        LogUtil.a("HiWearEngineCalendarManager", "eventUuid value is: ", new String(bmuVar.c(), StandardCharsets.UTF_8).trim());
                        break;
                }
            }
            if (!kat.b(str) && !kat.b(str2) && i != -1 && !kat.b(str3) && !kat.b(str4)) {
                return;
            }
            LogUtil.b("HiWearEngineCalendarManager", "payload data lake parameters ");
        } catch (bmk unused) {
            LogUtil.b("HiWearEngineCalendarManager", "catch TlvException");
            sqo.g("catch TlvException");
        }
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public WearEngineModule getManagerModule() {
        return WearEngineModule.CALENDAR_MODULE;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearPkgName() {
        return jyo.a() ? "com.huawei.ohos.calendar" : "in.huawei.calendar";
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearFingerprint() {
        return jyo.a() ? "com.huawei.ohos.calendar_BCgpfcWNSKWgvxsSILxooQZyAmKYsFQnMTibnfrKQqK9M0ABtXH+GbsOscsnVvVc5qIDiFEyEOYMSF7gJ7Vb5Mc=" : OfflineMapWearEngineManager.WEAR_FINGERPRINT;
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onDeviceConnectionChange(ConnectState connectState, DeviceInfo deviceInfo) {
        LogUtil.h("HiWearEngineCalendarManager", "onDeviceConnectionChange");
    }
}
