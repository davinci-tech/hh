package defpackage;

import android.content.Context;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.binder.ParserInterface;
import com.huawei.hwdevice.phoneprocess.framework.hwdevicemgr.dmsmanager.HwDeviceMgrInterface;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes5.dex */
public class kdh extends HwBaseManager implements ParserInterface {
    private static kdh c;
    private HwDeviceMgrInterface f;
    private static final Object d = new Object();
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static final Object f14302a = new Object();
    private static Map<Integer, List<IBaseResponseCallback>> b = new HashMap(16);

    private kdh(Context context) {
        super(context);
        LogUtil.a("HwSleepBreatheMgr", "HwSleepBreatheMgr Constructor");
        HwDeviceMgrInterface b2 = jsz.b(context);
        this.f = b2;
        if (b2 == null) {
            LogUtil.h("HwSleepBreatheMgr", "mHwDeviceMgr is null");
        }
    }

    public static kdh d() {
        kdh kdhVar;
        LogUtil.a("HwSleepBreatheMgr", "enter HwSleepBreatheMgr");
        synchronized (f14302a) {
            if (c == null) {
                c = new kdh(BaseApplication.getContext());
            }
            kdhVar = c;
        }
        return kdhVar;
    }

    @Override // com.huawei.hwdevice.phoneprocess.binder.ParserInterface
    public void getResult(DeviceInfo deviceInfo, byte[] bArr) {
        if (bArr == null || bArr.length <= 2) {
            LogUtil.h("HwSleepBreatheMgr", "onResponse error dataInfos");
            return;
        }
        blt.d("HwSleepBreatheMgr", bArr, "onResponse receive bt data: ");
        byte b2 = bArr[1];
        if (b2 == 1) {
            d(bArr);
        } else if (b2 == 2) {
            a(bArr);
        } else {
            LogUtil.h("HwSleepBreatheMgr", "onResponse receive bt error data");
        }
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 45;
    }

    private void d(int i, IBaseResponseCallback iBaseResponseCallback) {
        synchronized (e) {
            if (iBaseResponseCallback != null) {
                List<IBaseResponseCallback> list = b.get(Integer.valueOf(i));
                if (list == null) {
                    list = new ArrayList<>(16);
                    b.put(Integer.valueOf(i), list);
                }
                list.add(iBaseResponseCallback);
            }
        }
    }

    public void c(int i, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("HwSleepBreatheMgr", "setSleepBreatheSwitch callback is null");
            return;
        }
        synchronized (d) {
            if (this.f == null) {
                LogUtil.h("HwSleepBreatheMgr", "setSleepBreatheSwitch mHwDeviceMgr is null");
                return;
            }
            DeviceInfo b2 = b();
            if (b2 == null) {
                LogUtil.h("05", 1, "HwSleepBreatheMgr", "setSleepBreatheSwitch get device info error");
                iBaseResponseCallback.d(300004, null);
            } else {
                LogUtil.a("05", 1, "HwSleepBreatheMgr", "setSleepBreatheSwitch support");
                d(1, iBaseResponseCallback);
                kdg.c(i, b2);
            }
        }
    }

    public void e(IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("HwSleepBreatheMgr", "clearSleepBreatheData callback is null");
            return;
        }
        synchronized (d) {
            if (this.f == null) {
                LogUtil.h("HwSleepBreatheMgr", "clearSleepBreatheData mHwDeviceMgr is null");
                return;
            }
            DeviceInfo b2 = b();
            if (b2 == null) {
                LogUtil.h("05", 1, "HwSleepBreatheMgr", "clearSleepBreatheData get device info error");
                iBaseResponseCallback.d(300004, null);
            } else {
                LogUtil.a("05", 1, "HwSleepBreatheMgr", "clearSleepBreatheData support");
                d(2, iBaseResponseCallback);
                kdg.b(b2);
            }
        }
    }

    private void d(byte[] bArr) {
        int i;
        LogUtil.a("05", 0, "HwSleepBreatheMgr", "setSleepBreatheMeasureCode is:", cvx.d(bArr));
        try {
            i = kdb.a(bArr);
        } catch (cwg unused) {
            LogUtil.b("HwSleepBreatheMgr", "setSleepBreatheMeasureCode Exception");
            i = 201000;
        }
        b(1, i, "");
    }

    private void a(byte[] bArr) {
        int i;
        LogUtil.a("05", 1, "HwSleepBreatheMgr", "clearSleepBreatheDataCode data is:", cvx.d(bArr));
        try {
            i = kdb.a(bArr);
        } catch (cwg unused) {
            LogUtil.b("HwSleepBreatheMgr", "clearSleepBreatheDataCode Exception");
            i = 201000;
        }
        b(2, i, "");
    }

    private void b(int i, int i2, Object obj) {
        LogUtil.a("05", 1, "HwSleepBreatheMgr", "callback cmd is:", Integer.valueOf(i), "error is ", Integer.valueOf(i2));
        synchronized (e) {
            List<IBaseResponseCallback> list = b.get(Integer.valueOf(i));
            if (list != null) {
                Iterator<IBaseResponseCallback> it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    IBaseResponseCallback next = it.next();
                    if (next != null) {
                        next.d(i2, obj);
                        it.remove();
                        break;
                    }
                    it.remove();
                }
            }
        }
    }

    private DeviceInfo b() {
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.CONNECTED_MAIN_DEVICES, null, "HwSleepBreatheMgr");
        if (deviceList == null || deviceList.size() <= 0) {
            return null;
        }
        for (DeviceInfo deviceInfo : deviceList) {
            if (!cvt.c(deviceInfo.getProductType())) {
                return deviceInfo;
            }
        }
        return null;
    }
}
