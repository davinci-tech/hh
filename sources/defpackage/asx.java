package defpackage;

import android.os.Handler;
import android.os.Message;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationManagerCompat;
import com.huawei.haf.handler.ExtendHandler;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.outofprocess.util.NotificationContentProviderUtil;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentUtils;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class asx implements DataReceiveCallback {
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private ExtendHandler f216a;
    private IBaseResponseCallback b;

    static class a {
        static final asx e = new asx();
    }

    private asx() {
        this.f216a = null;
        LogUtil.a("SleepNotificationManager", "create SleepNotificationManager.");
    }

    public static asx e() {
        return a.e;
    }

    private void c() {
        this.f216a = HandlerCenter.yt_(new b(), "SleepNotificationManager");
    }

    public void a(ast astVar, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("SleepNotificationManager", "sendSleepNotificationEvent enter");
        this.b = iBaseResponseCallback;
        boolean z = ActivityCompat.checkSelfPermission(BaseApplication.getContext(), "android.permission.START_ACTIVITIES_FROM_BACKGROUND") == 0;
        LogUtil.a("SleepNotificationManager", "sendSleepNotificationEvent hasGrantedPermission: ", Boolean.valueOf(z));
        if (!z) {
            b(101);
            return;
        }
        boolean e2 = NotificationContentProviderUtil.e();
        boolean d = NotificationContentProviderUtil.d(BaseApplication.getAppPackage());
        boolean areNotificationsEnabled = NotificationManagerCompat.from(BaseApplication.getContext()).areNotificationsEnabled();
        LogUtil.a("SleepNotificationManager", "sendSleepNotificationEvent isNotificationStatus:", Boolean.valueOf(e2), ", isHealthNotificationStatus:", Boolean.valueOf(d), ", isHealthAppNotification:", Boolean.valueOf(areNotificationsEnabled));
        if (!e2 || !d || !areNotificationsEnabled) {
            b(101);
            return;
        }
        if (astVar == null) {
            LogUtil.h("SleepNotificationManager", "sendSleepNotificationEvent notificationData is null");
            b(101);
            return;
        }
        DeviceInfo b2 = b();
        if (b2 == null || b2.getDeviceConnectState() != 2) {
            LogUtil.h("SleepNotificationManager", "sendSleepNotificationEvent deviceInfo is null or disconnect");
            b(101);
            return;
        }
        d();
        if (this.f216a == null) {
            c();
        }
        this.f216a.sendEmptyMessage(1, 2000L);
        boolean sendSampleEventCommand = cuk.b().sendSampleEventCommand(b2, a(b2, astVar), "SleepNotificationManager");
        LogUtil.a("SleepNotificationManager", "sendSampleEventCommand return isSendSuccess:", Boolean.valueOf(sendSampleEventCommand));
        if (sendSampleEventCommand) {
            return;
        }
        b(101);
    }

    private DeviceInfo b() {
        if (EnvironmentUtils.c()) {
            return jpt.a("SleepNotificationManager");
        }
        DeviceInfo deviceInfo = null;
        if (EnvironmentUtils.e()) {
            List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "SleepNotificationManager");
            if (deviceList == null || deviceList.isEmpty()) {
                LogUtil.a("SleepNotificationManager", "getDeviceInfo() deviceInfoList is null or empty");
                return null;
            }
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
            LogUtil.a("SleepNotificationManager", "getDeviceInfo() deviceInfo:", deviceInfo, " , tag is ", "SleepNotificationManager");
            return deviceInfo;
        }
        LogUtil.h("SleepNotificationManager", "getDeviceInfo else, return null");
        return null;
    }

    private cvp a(DeviceInfo deviceInfo, ast astVar) {
        LogUtil.a("SleepNotificationManager", "getSampleEvent notificationData");
        cvp cvpVar = new cvp();
        cvpVar.setSrcPkgName("hw.unitedevice.sleepnotification");
        cvpVar.setWearPkgName("hw.watch.health.sleep");
        cvpVar.setCommandId(2);
        cvpVar.a(80030001L);
        cvpVar.b(0);
        cvpVar.e(cvx.a(b(deviceInfo, astVar)));
        return cvpVar;
    }

    private String b(DeviceInfo deviceInfo, ast astVar) {
        StringBuilder sb = new StringBuilder(16);
        String c = cvx.c(astVar.i());
        sb.append(cvx.e(6) + cvx.d(c.length() / 2) + c);
        String c2 = cvx.c(astVar.d());
        sb.append(cvx.e(7) + cvx.d(c2.length() / 2) + c2);
        String c3 = cvx.c(astVar.b());
        sb.append(cvx.e(8) + cvx.d(c3.length() / 2) + c3);
        String c4 = cvx.c(astVar.a());
        sb.append(cvx.e(9) + cvx.d(c4.length() / 2) + c4);
        String c5 = cvx.c(kkj.a(deviceInfo));
        sb.append(cvx.e(10) + cvx.d(c5.length() / 2) + c5);
        String c6 = cvx.c(astVar.c());
        sb.append(cvx.e(11) + cvx.d(c6.length() / 2) + c6);
        String c7 = cvx.c(astVar.e());
        sb.append(cvx.e(12) + cvx.d(c7.length() / 2) + c7);
        LogUtil.a("SleepNotificationManager", "getEventData tlv:", sb.toString());
        return sb.toString();
    }

    private void d() {
        cuk.b().registerDeviceSampleListener("hw.unitedevice.sleepnotification", this);
    }

    private void g() {
        cuk.b().unRegisterDeviceSampleListener("hw.unitedevice.sleepnotification");
    }

    @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
        LogUtil.a("SleepNotificationManager", "onDataReceived: ", Integer.valueOf(i));
        if (!(cvrVar instanceof cvp)) {
            LogUtil.a("SleepNotificationManager", "onDataReceived message is error");
            b(101);
            return;
        }
        cvp cvpVar = (cvp) cvrVar;
        if (cvrVar.getCommandId() != 2) {
            LogUtil.h("SleepNotificationManager", "onDataReceived, message commandId: ", Integer.valueOf(cvrVar.getCommandId()));
            b(101);
            return;
        }
        if (cvpVar.e() != 80030001) {
            LogUtil.h("SleepNotificationManager", "onDataReceived, sampleEvent EventId is error");
            b(101);
            return;
        }
        LogUtil.a("SleepNotificationManager", "onDataReceived eventData:", cvx.d(cvpVar.c()));
        if (CommonUtil.w(r6) == 1) {
            b(100);
        } else {
            b(101);
        }
        if (this.f216a == null) {
            c();
        }
        this.f216a.removeMessages(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        this.b.d(i, null);
    }

    public void a() {
        g();
        synchronized (e) {
            ExtendHandler extendHandler = this.f216a;
            if (extendHandler != null) {
                extendHandler.removeTasksAndMessages();
                this.f216a.quit(true);
                this.f216a = null;
            }
        }
    }

    class b implements Handler.Callback {
        private b() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            if (message.what == 1) {
                LogUtil.a("SleepNotificationManager", "SleepNotificationHandler time out");
                asx.this.b(101);
                return true;
            }
            LogUtil.h("SleepNotificationManager", "SleepNotificationHandler default");
            return false;
        }
    }
}
