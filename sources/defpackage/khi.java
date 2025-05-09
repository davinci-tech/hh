package defpackage;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.unitedevice.entity.UniteDevice;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.HarmonyBuild;
import health.compact.a.LocalBroadcast;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;
import java.util.Locale;

/* loaded from: classes5.dex */
public class khi implements DataReceiveCallback {
    private static final Object c = new Object();
    private static khi d;

    /* renamed from: a, reason: collision with root package name */
    private snq f14371a;
    private Context e = BaseApplication.getContext();

    public static khi c() {
        khi khiVar;
        synchronized (c) {
            if (d == null) {
                d = new khi();
            }
            khiVar = d;
        }
        return khiVar;
    }

    public void d() {
        cuk.b().registerDeviceSampleListener("hw.unitedevice.smartedge", this);
    }

    private khi() {
        snq c2 = snq.c();
        this.f14371a = c2;
        c2.a(this.e);
    }

    @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
        LogUtil.a("HwNotificationLiveMgr", "onDataReceived enter");
        if (deviceInfo == null || cvrVar == null) {
            LogUtil.a("HwNotificationLiveMgr", "device or message is null.");
            return;
        }
        if (cvrVar instanceof cvp) {
            cvp cvpVar = (cvp) cvrVar;
            if (cvrVar.getCommandId() != 2) {
                LogUtil.h("HwNotificationLiveMgr", "onDataReceived, message commandId: ", Integer.valueOf(cvrVar.getCommandId()));
                return;
            }
            if (cvpVar.e() != 800100014) {
                ReleaseLogUtil.d("Notify_HwNotificationLiveMgr", "event id reported is not LEVEL_EVENT_ID");
                return;
            }
            if (!b() || !khs.i()) {
                ReleaseLogUtil.d("Notify_HwNotificationLiveMgr", "onDataReceived phone is later harmony 4 or capability is not support");
                return;
            }
            String d2 = cvx.d(cvpVar.c());
            if (TextUtils.isEmpty(d2)) {
                return;
            }
            String upperCase = cvx.e(d2).toUpperCase(Locale.ENGLISH);
            LogUtil.a("HwNotificationLiveMgr", "onDataReceived  eventData=", upperCase);
            SharedPreferenceManager.c("NOTIFY_LIVE_LEVEL_VALUE", "NOTIFY_LIVE_LEVEL_VALUE", upperCase);
            Intent intent = new Intent("com.huawei.health.action.ACTION_NOTIFICATION_LEVEL");
            intent.putExtra("action_type", "NOTIFICATION_LEVEL");
            BroadcastManagerUtil.bFH_(BaseApplication.getContext(), intent, LocalBroadcast.c, true);
            khg.d().c();
        }
    }

    public void d(String str) {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "HwNotificationLiveMgr");
        DeviceInfo deviceInfo = deviceList.size() > 0 ? deviceList.get(0) : null;
        if (deviceInfo == null) {
            LogUtil.h("HwNotificationLiveMgr", "isSupportSyncIcon deviceInfo is null");
            return;
        }
        if (!cwi.c(deviceInfo, 84)) {
            LogUtil.h("HwNotificationLiveMgr", "sendSampleEventCommand capability is not support.");
            return;
        }
        UniteDevice e = spu.e(deviceInfo.getDeviceIdentify());
        bir e2 = spy.e(e(str));
        if (e2 == null) {
            LogUtil.h("HwNotificationLiveMgr", "sendEventConfigCommand commandMessage is invalid.");
        } else {
            this.f14371a.sendCommand(e, e2);
        }
    }

    private static cvp e(String str) {
        cvp cvpVar = new cvp();
        cvpVar.setSrcPkgName("hw.unitedevice.smartedge");
        cvpVar.setWearPkgName("hw.watch.message.smartedge");
        cvpVar.setCommandId(2);
        cvpVar.a(800100013L);
        cvpVar.b(0);
        cvpVar.e(cvx.a(cvx.c(str)));
        return cvpVar;
    }

    private boolean b() {
        String str;
        return HarmonyBuild.d && (str = HarmonyBuild.b) != null && !str.isEmpty() && str.charAt(0) >= '4' && str.charAt(0) <= '9';
    }
}
