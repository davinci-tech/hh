package defpackage;

import android.content.Context;
import android.os.RemoteException;
import android.text.TextUtils;
import androidx.core.app.NotificationManagerCompat;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.syncmgr.SyncOption;
import com.huawei.ui.main.stories.nps.interactors.HwNpsManager;
import health.compact.a.CommonUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class oxa {

    /* renamed from: a, reason: collision with root package name */
    private static oxa f15997a;
    private static final Object c = new Object();
    private static jfq d;
    private Context e;

    private oxa(Context context) {
        this.e = context;
    }

    public static oxa a() {
        oxa oxaVar;
        synchronized (c) {
            if (f15997a == null) {
                f15997a = new oxa(BaseApplication.getContext());
            }
            if (d == null) {
                d = jfq.c();
            }
            if (d != null) {
                jgh.d(BaseApplication.getContext());
            }
            oxaVar = f15997a;
        }
        return oxaVar;
    }

    public int c() {
        DeviceInfo a2 = jpt.a("DeviceStateInteractors");
        if (a2 != null) {
            return a2.getDeviceConnectState();
        }
        return 0;
    }

    public void b(final int i) {
        DeviceInfo a2 = jpt.a("DeviceStateInteractors");
        if (a2 == null) {
            ReleaseLogUtil.d("R_DeviceStateInteractors", "syncAllDataWithRetry deviceInfo is null retryTimes is : ", Integer.valueOf(i));
            if (i > 0) {
                HandlerCenter.d().e(new Runnable() { // from class: owy
                    @Override // java.lang.Runnable
                    public final void run() {
                        oxa.this.e(i);
                    }
                }, 2000L);
                return;
            }
            return;
        }
        if (a2.getDeviceConnectState() == 0) {
            b();
        }
    }

    /* synthetic */ void e(int i) {
        b(i - 1);
    }

    public DeviceInfo f() {
        DeviceInfo a2 = jpt.a("DeviceStateInteractors");
        if (a2 != null) {
            return a2;
        }
        return null;
    }

    public List<DeviceInfo> h() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ACTIVE_DEVICES, null, "DeviceStateInteractors");
        if (deviceList == null || deviceList.size() == 0) {
            return deviceList;
        }
        ArrayList arrayList = new ArrayList(10);
        for (DeviceInfo deviceInfo : deviceList) {
            if ("main_relationship".equals(deviceInfo.getRelationship()) || cvt.c(deviceInfo.getProductType())) {
                arrayList.add(deviceInfo);
            }
        }
        return arrayList;
    }

    public DeviceInfo a(String str) {
        DeviceInfo e = jpt.e(str, "DeviceStateInteractors");
        if (e != null) {
            return e;
        }
        return null;
    }

    public DeviceInfo b(String str) {
        DeviceInfo b = jpt.b(str, "DeviceStateInteractors");
        if (b != null) {
            return b;
        }
        return null;
    }

    public boolean e() {
        List<DeviceInfo> i = i();
        boolean z = false;
        if (i != null && i.size() > 0) {
            Iterator<DeviceInfo> it = i.iterator();
            while (it.hasNext()) {
                LogUtil.a("DeviceStateInteractors", "checkEnableDevice info ", it.next());
            }
            List<DeviceInfo> h = h();
            if (h == null || h.isEmpty()) {
                DeviceInfo deviceInfo = i.get(0);
                deviceInfo.setDeviceActiveState(1);
                e(i, deviceInfo.getDeviceIdentify());
                z = true;
            } else {
                Iterator<DeviceInfo> it2 = h.iterator();
                while (it2.hasNext()) {
                    ReleaseLogUtil.e("R_DeviceStateInteractors", "checkEnableDevice info device", it2.next());
                }
            }
        }
        LogUtil.a("DeviceStateInteractors", "checkEnableDevices res:" + z);
        return z;
    }

    public int j() {
        DeviceInfo a2 = jpt.a("DeviceStateInteractors");
        if (a2 != null) {
            return a2.getProductType();
        }
        return -1;
    }

    public void d() {
        jog.c().d();
    }

    public void d(String str) {
        jog.c().d(str);
    }

    public List<DeviceInfo> i() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "DeviceStateInteractors");
        if (deviceList != null) {
            return deviceList;
        }
        LogUtil.b("DeviceStateInteractors", "getUsedDeviceList null");
        return new ArrayList(10);
    }

    public void c(List<String> list, boolean z) {
        try {
            LogUtil.a("DeviceStateInteractors", "unPair");
            d.c(list, z);
        } catch (RemoteException unused) {
            LogUtil.b("DeviceStateInteractors", "unPair remoteException");
            jez.d(BaseApplication.getContext(), null);
        }
    }

    public void e(List<DeviceInfo> list, String str) {
        try {
            LogUtil.b("DeviceStateInteractors", "setUsedDeviceList ");
            d.e(list, str);
        } catch (RemoteException e) {
            LogUtil.b("DeviceStateInteractors", "RemoteException = " + e.getMessage());
            jez.d(BaseApplication.getContext(), null);
        }
    }

    public boolean g() {
        return iyl.d().g() == 3;
    }

    public void c(boolean z) {
        try {
            d.b(z);
        } catch (RemoteException e) {
            LogUtil.b("DeviceStateInteractors", "openSystemBluetoothSwitch RemoteException = " + e.getMessage());
            jez.d(BaseApplication.getContext(), null);
        }
    }

    public void a(Context context) {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "DeviceStateInteractors");
        if (deviceList != null && deviceList.size() > 0 && deviceList.get(0).getSingleFrameDevice() == 1) {
            ReleaseLogUtil.d("R_DeviceStateInteractors", "device is SingleFrameDevice, nps return");
            return;
        }
        LogUtil.a("DeviceStateInteractors", "nps startNps qstnSurveyActivate###");
        if (context == null) {
            LogUtil.a("DeviceStateInteractors", "startNps activityContext is null");
        }
        b(context);
    }

    private void b(Context context) {
        LogUtil.a("DeviceStateInteractors", "Enter qstnSurveyActivate");
        if (context == null) {
            LogUtil.a("DeviceStateInteractors", "qstnSurveyActivate activityContext is null");
        }
        boolean e = knx.e();
        LogUtil.a("DeviceStateInteractors", "qstnSurveyInit,isCheckHi" + e + ",Utils.isOversea()" + Utils.o());
        if (e || Utils.o()) {
            if (CommonUtil.as()) {
                LogUtil.h("DeviceStateInteractors", "qstnSurveyActivate isBetaVersion");
                return;
            }
            if (CommonUtil.aa(this.e)) {
                LogUtil.a("DeviceStateInteractors", "testnps, qstnSurveyActivate qstnSurvey.activatedQstnSurvey");
                HwNpsManager.getInstance().activatedQuestionSurvey();
            } else {
                LogUtil.a("DeviceStateInteractors", "testnps, Network is disabled");
            }
            DeviceInfo f = oae.c(this.e).f();
            if (m()) {
                HwNpsManager.getInstance().sendNotify(f);
            }
        }
    }

    private boolean m() {
        if (CommonUtil.bh() || NotificationManagerCompat.from(BaseApplication.getContext()).areNotificationsEnabled()) {
            return true;
        }
        LogUtil.h("DeviceStateInteractors", "notifyMsg notification bar permission is not enabled.");
        return false;
    }

    private void l() {
        LogUtil.a("DeviceStateInteractors", "Enter refreshAllCardsData");
        LogUtil.a("DeviceStateInteractors", "PullDownRefreshcore trigger sleep sync");
        nhu.c().startSynCoreSleep(SyncOption.builder().e(true).c(), new IBaseResponseCallback() { // from class: oxa.5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("DeviceStateInteractors", "PullDownRefreshcore trigger sleep sync has been done errorCode: ", Integer.valueOf(i));
            }
        });
        jhg.c(this.e.getApplicationContext());
        LogUtil.a("DeviceStateInteractors", "refreshAllCardsData syncFitnessDetailData!!!");
        ThreadPoolManager.d().execute(new Runnable() { // from class: oxa.1
            @Override // java.lang.Runnable
            public void run() {
                nhu.a().startSynBasicData(null);
                nhu.h().syncStressDetailData();
            }
        });
        LogUtil.a("DeviceStateInteractors", "Leave refreshAllCardsData");
    }

    public int d(int i) {
        LogUtil.a("DeviceStateInteractors", "Enter getIdImage productType :", Integer.valueOf(i));
        int i2 = !jfu.h(i) ? R.mipmap._2131820674_res_0x7f110082 : R.mipmap._2131820664_res_0x7f110078;
        cuw c2 = jfu.c(i);
        return c2.e() != 0 ? c2.e() : i2;
    }

    public void b() {
        LogUtil.a("DeviceStateInteractors", "Enter dataSync()");
        l();
        LogUtil.a("DeviceStateInteractors", "Leave dataSync()");
    }

    public void c(String str) {
        int i;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DeviceStateInteractors", "mac is empty.");
            return;
        }
        LogUtil.a("DeviceStateInteractors", "Enter unbindDevice");
        List<DeviceInfo> i2 = i();
        if (i2.size() > 0) {
            Iterator<DeviceInfo> it = i2.iterator();
            while (true) {
                if (!it.hasNext()) {
                    i = -1;
                    break;
                }
                DeviceInfo next = it.next();
                if (next.getDeviceIdentify().equals(str)) {
                    i = i2.indexOf(next);
                    break;
                }
            }
            if (i != -1) {
                i2.remove(i);
                LogUtil.a("DeviceStateInteractors", "deleteDevice");
                ArrayList arrayList = new ArrayList(16);
                arrayList.add(str);
                c(arrayList, true);
                return;
            }
            LogUtil.a("DeviceStateInteractors", "device is not deleted.");
        }
    }
}
