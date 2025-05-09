package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import com.huawei.hwdevice.phoneprocess.mgr.hwtwsmgr.BaseTwsTask;
import com.huawei.hwdevice.phoneprocess.mgr.hwtwsmgr.HwTwsDeleteDeviceTask;
import com.huawei.hwdevice.phoneprocess.mgr.hwtwsmgr.HwTwsGetDeviceTask;
import com.huawei.hwdevice.phoneprocess.mgr.hwtwsmgr.HwTwsPutDeviceTask;
import com.huawei.hwdevice.phoneprocess.mgr.hwtwsmgr.TwsExecutorListener;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.Utils;
import java.util.List;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: classes.dex */
public class kff extends HwBaseManager implements ParserInterface {
    private static final Object c = new Object();
    private static volatile kff d;

    /* renamed from: a, reason: collision with root package name */
    private TwsExecutorListener f14338a;
    private Context b;
    private kfh e;
    private LinkedBlockingQueue<BaseTwsTask> f;
    private cwl j;

    private kff(Context context) {
        super(context);
        this.f = new LinkedBlockingQueue<>(16);
        this.j = new cwl();
        this.f14338a = new TwsExecutorListener() { // from class: kff.1
            @Override // com.huawei.hwdevice.phoneprocess.mgr.hwtwsmgr.TwsExecutorListener
            public BaseTwsTask onGetNextTask() {
                return (BaseTwsTask) kff.this.f.poll();
            }

            @Override // com.huawei.hwdevice.phoneprocess.mgr.hwtwsmgr.TwsExecutorListener
            public void onTaskBegin(BaseTwsTask baseTwsTask) {
                if (baseTwsTask != null) {
                    LogUtil.a("HwTwsManger", "onTaskBegin name:", baseTwsTask.getTaskName());
                }
            }

            @Override // com.huawei.hwdevice.phoneprocess.mgr.hwtwsmgr.TwsExecutorListener
            public void onTaskFinish(BaseTwsTask baseTwsTask) {
                if (baseTwsTask != null) {
                    LogUtil.a("HwTwsManger", "onTaskFinish name:", baseTwsTask.getTaskName());
                }
            }

            @Override // com.huawei.hwdevice.phoneprocess.mgr.hwtwsmgr.TwsExecutorListener
            public void onTaskTimeout(BaseTwsTask baseTwsTask) {
                if (baseTwsTask != null) {
                    LogUtil.a("HwTwsManger", "onTaskTimeout name:", baseTwsTask.getTaskName());
                }
            }

            @Override // com.huawei.hwdevice.phoneprocess.mgr.hwtwsmgr.TwsExecutorListener
            public void onAllTaskFinish() {
                LogUtil.a("HwTwsManger", "onAllTaskFinish");
                kff.this.e.c();
            }
        };
        this.b = context;
        this.e = new kfh(this.f14338a);
    }

    public static kff c() {
        kff kffVar;
        synchronized (c) {
            if (d == null) {
                d = new kff(BaseApplication.getContext());
            }
            kffVar = d;
        }
        return kffVar;
    }

    public void d() {
        if (!g()) {
            LogUtil.h("HwTwsManger", "triggerByDeviceConnect but not support Tws");
            return;
        }
        LogUtil.a("HwTwsManger", "triggerByDeviceConnect");
        if (this.f.offer(new d(this.j, this.b))) {
            this.e.a();
        }
    }

    public void e() {
        if (!g()) {
            LogUtil.h("HwTwsManger", "triggerByLogin but not support Tws");
            return;
        }
        LogUtil.a("HwTwsManger", "triggerByLogin");
        if (this.f.offer(new c(this.j, this.b))) {
            this.e.a();
        }
    }

    public void b() {
        if (!g()) {
            LogUtil.h("HwTwsManger", "triggerByBroadcast but not support Tws");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: kff.3
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        Thread.sleep(2000L);
                    } catch (InterruptedException e2) {
                        LogUtil.b("HwTwsManger", "triggerByBroadcast InterruptedException ", e2.getMessage());
                    }
                    LogUtil.a("HwTwsManger", "triggerByBroadcast");
                    if (kff.this.f.offer(new e(kff.this.j, kff.this.b))) {
                        kff.this.e.a();
                    }
                }
            });
        }
    }

    private boolean g() {
        boolean z;
        DeviceInfo h = h();
        if (h != null) {
            String deviceIdentify = h.getDeviceIdentify();
            Map<String, DeviceCapability> queryDeviceCapability = jsz.b(this.b).queryDeviceCapability(1, deviceIdentify, "HwTwsManger");
            if (queryDeviceCapability != null && !queryDeviceCapability.isEmpty()) {
                DeviceCapability deviceCapability = queryDeviceCapability.get(deviceIdentify);
                if (deviceCapability != null && deviceCapability.isSupportTws()) {
                    z = true;
                    LogUtil.a("HwTwsManger", "isSupportTws:", Boolean.valueOf(z), ",isOversea:", Boolean.valueOf(Utils.o()), ",isEmui100:", Boolean.valueOf(CommonUtil.ar()), ",isSystemSupportFreezing:", Boolean.valueOf(EnvironmentInfo.r()));
                    return Utils.o() && z && (CommonUtil.ar() || EnvironmentInfo.r());
                }
            } else {
                LogUtil.h("HwTwsManger", "isSupportTws queryDeviceCapability is null. ");
            }
        } else {
            LogUtil.h("HwTwsManger", "deviceInfo is null");
        }
        z = false;
        LogUtil.a("HwTwsManger", "isSupportTws:", Boolean.valueOf(z), ",isOversea:", Boolean.valueOf(Utils.o()), ",isEmui100:", Boolean.valueOf(CommonUtil.ar()), ",isSystemSupportFreezing:", Boolean.valueOf(EnvironmentInfo.r()));
        if (Utils.o()) {
        }
    }

    private static DeviceInfo h() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "HwTwsManger");
        if (deviceList.size() <= 0) {
            return null;
        }
        for (DeviceInfo deviceInfo : deviceList) {
            if (!cvt.c(deviceInfo.getProductType())) {
                return deviceInfo;
            }
        }
        return null;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 43;
    }

    @Override // com.huawei.hwdevice.phoneprocess.binder.ParserInterface
    public void getResult(DeviceInfo deviceInfo, byte[] bArr) {
        b(bArr);
    }

    private void b(byte[] bArr) {
        if (!g()) {
            LogUtil.h("HwTwsManger", "onDataReceived callback but not support Tws");
        }
        LogUtil.a("HwTwsManger", "getResult(): ", cvx.d(bArr));
        if (bArr == null || bArr.length <= 0) {
            LogUtil.h("HwTwsManger", "getResult() but data is null");
            return;
        }
        switch (bArr[1]) {
            case 18:
                c(bArr);
                break;
            case 19:
                e(bArr);
                break;
            case 20:
                a(bArr);
                break;
            case 21:
                d(bArr);
                break;
            default:
                LogUtil.h("HwTwsManger", "getResult find default");
                break;
        }
    }

    private void c(byte[] bArr) {
        this.e.d("Wait_5_43_18", bArr);
    }

    private void e(byte[] bArr) {
        String d2 = cvx.d(bArr);
        if (TextUtils.isEmpty(d2) || d2.length() < 4) {
            LogUtil.h("HwTwsManger", "handleTws19 data is error");
        }
    }

    private void a(byte[] bArr) {
        String d2 = cvx.d(bArr);
        if (TextUtils.isEmpty(d2) || d2.length() < 4) {
            LogUtil.h("HwTwsManger", "handleTws20 data is error");
            return;
        }
        if (this.f.offer(new a(d2.substring(4), this.j, this.b))) {
            this.e.a();
        }
    }

    private void d(byte[] bArr) {
        String d2 = cvx.d(bArr);
        if (TextUtils.isEmpty(d2) || d2.length() < 4) {
            LogUtil.h("HwTwsManger", "handleTws21 data is error");
            return;
        }
        if (this.f.offer(new b(d2.substring(4), this.j, this.b))) {
            this.e.a();
        }
    }

    /* loaded from: classes5.dex */
    static class d extends HwTwsGetDeviceTask {
        d(cwl cwlVar, Context context) {
            super(cwlVar, context);
        }

        @Override // com.huawei.hwdevice.phoneprocess.mgr.hwtwsmgr.HwTwsGetDeviceTask, com.huawei.hwdevice.phoneprocess.mgr.hwtwsmgr.BaseTwsTask
        public String getTaskName() {
            return "DeviceConnect";
        }
    }

    /* loaded from: classes5.dex */
    static class c extends HwTwsGetDeviceTask {
        c(cwl cwlVar, Context context) {
            super(cwlVar, context);
        }

        @Override // com.huawei.hwdevice.phoneprocess.mgr.hwtwsmgr.HwTwsGetDeviceTask, com.huawei.hwdevice.phoneprocess.mgr.hwtwsmgr.BaseTwsTask
        public String getTaskName() {
            return "LoginTask";
        }
    }

    /* loaded from: classes5.dex */
    static class e extends HwTwsGetDeviceTask {
        e(cwl cwlVar, Context context) {
            super(cwlVar, context);
        }

        @Override // com.huawei.hwdevice.phoneprocess.mgr.hwtwsmgr.HwTwsGetDeviceTask, com.huawei.hwdevice.phoneprocess.mgr.hwtwsmgr.BaseTwsTask
        public String getTaskName() {
            return "BroadcastTask";
        }
    }

    /* loaded from: classes5.dex */
    static class a extends HwTwsPutDeviceTask {
        a(String str, cwl cwlVar, Context context) {
            super(str, cwlVar, context);
        }

        @Override // com.huawei.hwdevice.phoneprocess.mgr.hwtwsmgr.BaseTwsTask
        public String getTaskName() {
            return "DevicePairTwsTask";
        }
    }

    /* loaded from: classes5.dex */
    static class b extends HwTwsDeleteDeviceTask {
        b(String str, cwl cwlVar, Context context) {
            super(str, cwlVar, context);
        }

        @Override // com.huawei.hwdevice.phoneprocess.mgr.hwtwsmgr.BaseTwsTask
        public String getTaskName() {
            return "DeviceDeleteTwsTask";
        }
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public void onDestroy() {
        super.onDestroy();
        a();
    }

    private void a() {
        this.e.d();
    }
}
