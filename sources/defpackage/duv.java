package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes3.dex */
public class duv {

    static class c {
        private static final duv d = new duv();
    }

    private duv() {
    }

    public static final duv e() {
        return c.d;
    }

    public void c() {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        newSingleThreadExecutor.execute(new Runnable() { // from class: duv.3
            @Override // java.lang.Runnable
            public void run() {
                if (!duv.this.d()) {
                    LogUtil.h("WearDeviceInteractor", "startPhoneService status Invalid");
                    return;
                }
                if (CommonUtil.ai(BaseApplication.getContext())) {
                    LogUtil.a("WearDeviceInteractor", "startPhoneService is running");
                    return;
                }
                Intent intent = new Intent();
                intent.setAction("com.huawei.bone.action.StartPhoneService");
                intent.setPackage(BaseApplication.getContext().getPackageName());
                intent.putExtra("isFromCallbackService", true);
                try {
                    BaseApplication.getContext().startService(intent);
                } catch (IllegalStateException e) {
                    LogUtil.b("WearDeviceInteractor", "startPhoneService IllegalStateException:", ExceptionUtils.d(e));
                    sqo.w("startPhoneService IllegalStateException:" + e.getMessage());
                } catch (SecurityException e2) {
                    ReleaseLogUtil.c("R_WearDeviceInteractor", "startPhoneService SecurityException:", ExceptionUtils.d(e2));
                    sqo.w("startPhoneService SecurityException:" + e2.getMessage());
                }
                jhg.c(BaseApplication.getContext());
            }
        });
        newSingleThreadExecutor.shutdown();
    }

    public void b() {
        LogUtil.c("WearDeviceInteractor", "Enter unbindAllDevice");
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "WearDeviceInteractor");
        if (deviceList != null && deviceList.size() > 0) {
            Iterator<DeviceInfo> it = deviceList.iterator();
            while (it.hasNext()) {
                d(it.next());
            }
        }
        f();
        g();
    }

    private void g() {
        LogUtil.a("WearDeviceInteractor", "Enter sendUnbindDevicesBroadcast()");
        Intent intent = new Intent("com.huawei.bone.action.UNBIND_DEVICE");
        intent.setPackage(BaseApplication.getContext().getPackageName());
        BaseApplication.getContext().sendOrderedBroadcast(intent, LocalBroadcast.c);
    }

    private void d(DeviceInfo deviceInfo) {
        if (deviceInfo == null || TextUtils.isEmpty(deviceInfo.getDeviceIdentify())) {
            LogUtil.b("WearDeviceInteractor", "deleteDevice parameter error");
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(deviceInfo.getDeviceIdentify());
        try {
            jfq.c().c((List<String>) arrayList, true);
        } catch (RemoteException unused) {
            LogUtil.b("WearDeviceInteractor", "deleteDevice RemoteException");
        }
    }

    private void f() {
        LogUtil.a("WearDeviceInteractor", "Enter sendDeviceListChangeBroadcast()");
        if (CommonUtil.ce()) {
            cvw.c(cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "WearDeviceInteractor"), "WearDeviceInteractor");
        }
    }

    public void a() {
        LogUtil.a("WearDeviceInteractor", "user refresh data in health homePage.Agree to do coreSleep sync.");
        if (d()) {
            jhj.b(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d() {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null && defaultAdapter.getState() == 10) {
            LogUtil.h("WearDeviceInteractor", "switch not on, not need start service!");
            return false;
        }
        if (jpp.a()) {
            return true;
        }
        LogUtil.h("WearDeviceInteractor", "no device, return");
        return false;
    }
}
