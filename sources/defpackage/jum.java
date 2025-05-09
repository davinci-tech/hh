package defpackage;

import android.content.Context;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.unitedevice.constant.ConnectState;
import com.huawei.unitedevice.constant.WearEngineModule;
import com.huawei.unitedevice.manager.EngineLogicBaseManager;
import defpackage.spn;

/* loaded from: classes5.dex */
public class jum extends EngineLogicBaseManager {

    /* renamed from: a, reason: collision with root package name */
    private static final Object f14096a = new Object();
    private static volatile jum e;

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onDeviceConnectionChange(ConnectState connectState, DeviceInfo deviceInfo) {
    }

    private jum(Context context) {
        super(context);
    }

    public static jum a() {
        if (e == null) {
            synchronized (f14096a) {
                if (e == null) {
                    LogUtil.a("HiWearEngineBtProxyManager", "getInstance()");
                    e = new jum(BaseApplication.getContext());
                }
            }
        }
        return e;
    }

    public void a(byte[] bArr) {
        spn.b bVar = new spn.b();
        try {
            bVar.c(bArr);
            bVar.d(false);
        } catch (tnx unused) {
            LogUtil.b("HiWearEngineBtProxyManager", "sendMessage WearEngineException");
        }
        c(bVar.e());
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public void onReceiveDeviceCommand(DeviceInfo deviceInfo, int i, spn spnVar) {
        LogUtil.a("HiWearEngineBtProxyManager", "onReceiveDeviceCommand errorCode ", Integer.valueOf(i));
        if (spnVar != null) {
            byte[] b = spnVar.b();
            if (b == null || b.length <= 1) {
                LogUtil.h("HiWearEngineBtProxyManager", "data illegal");
                return;
            } else {
                juj.d().a(deviceInfo, b);
                return;
            }
        }
        LogUtil.b("HiWearEngineBtProxyManager", "onReceiveDeviceCommand Message is null");
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public WearEngineModule getManagerModule() {
        return WearEngineModule.BTPROXY_MODULE;
    }

    private void c(spn spnVar) {
        sendComand(spnVar, null);
    }

    public static void e() {
        if (e == null) {
            LogUtil.h("HiWearEngineBtProxyManager", "current instance is null");
            return;
        }
        synchronized (f14096a) {
            e = null;
        }
    }

    public void c() {
        onDestroy();
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearPkgName() {
        return "com.huawei.health.btproxy";
    }

    @Override // com.huawei.unitedevice.manager.EngineLogicBaseManager
    public String getWearFingerprint() {
        return "BtproxyDistributeNet";
    }
}
