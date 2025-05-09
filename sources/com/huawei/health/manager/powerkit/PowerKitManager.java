package com.huawei.health.manager.powerkit;

import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.android.powerkit.PowerKitConnection;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.utils.ScreenUtil;
import com.huawei.hianalytics.framework.policy.IStoragePolicy;
import defpackage.abi;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class PowerKitManager {
    private static volatile PowerKitManager c;
    private static final Object e = new Object();
    private List<PowerKitCallback> b;
    private volatile CountDownLatch d;
    private volatile abi i;
    private volatile boolean f = true;

    /* renamed from: a, reason: collision with root package name */
    private volatile boolean f2791a = false;
    private final Map<Integer, List<String>> g = new ConcurrentHashMap(3);

    interface PowerKitCallback {
        void connected();
    }

    private PowerKitManager() {
    }

    public static PowerKitManager e() {
        if (c == null) {
            synchronized (PowerKitManager.class) {
                if (c == null) {
                    c = new PowerKitManager();
                }
            }
        }
        return c;
    }

    public static final class ResourceType {
        private ResourceType() {
        }
    }

    public static final class PowerMode {
        private PowerMode() {
        }
    }

    public static final class PowerOptimizeType {
        private PowerOptimizeType() {
        }
    }

    public static final class StateType {
        private StateType() {
        }
    }

    public static final class EventType {
        private EventType() {
        }
    }

    public boolean b() {
        if (!CommonUtil.ar() || ScreenUtil.a()) {
            return false;
        }
        abi d = d();
        if (!this.f2791a || d == null) {
            return false;
        }
        try {
            return d.b();
        } catch (RemoteException e2) {
            LogUtil.e("PowerKitManager", "calling isUserSleeping err, ", LogUtil.a(e2));
            return false;
        }
    }

    public void a(String str, int i, String str2) {
        d(str, i, 0L, str2);
    }

    public void d(String str, int i, long j, String str2) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("moduleTag is empty");
        }
        LogUtil.c("PowerKitManager", "applyForResourceUse moduleTag=", str, ", resType=", Integer.valueOf(i), ", timeout=", Long.valueOf(j), ", reason=", str2);
        synchronized (e) {
            a(str, i, j, str2, true);
        }
    }

    public void e(String str, int i, String str2) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("moduleTag is empty");
        }
        LogUtil.c("PowerKitManager", "unapplyForResourceUse moduleTag=", str, ", resType=", Integer.valueOf(i), ", reason=", str2);
        synchronized (e) {
            a(str, i, 0L, str2, false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public abi d() {
        if (this.i != null || !this.f) {
            return this.i;
        }
        return h();
    }

    private abi h() {
        synchronized (this) {
            if (this.i == null && this.f) {
                if (!CommonUtil.ax()) {
                    this.f = false;
                    return this.i;
                }
                if (this.d == null) {
                    this.d = new CountDownLatch(1);
                }
                try {
                    this.i = abi.c(BaseApplication.e(), new InnerPowerKitConnection(this));
                    if (!this.d.await(3L, TimeUnit.SECONDS)) {
                        LogUtil.a("PowerKitManager", "bind service await timeout");
                    }
                    e = null;
                } catch (SecurityException e2) {
                    e = e2;
                } catch (Exception e3) {
                    e = e3;
                }
                if (e != null) {
                    this.f = false;
                    this.i = null;
                    LogUtil.e("PowerKitManager", "getHuaweiPowerKit Exception = ", LogUtil.a(e));
                }
                this.d = null;
                return this.i;
            }
            return this.i;
        }
    }

    private void a(final String str, final int i, final long j, final String str2, final boolean z) {
        if (d() == null) {
            return;
        }
        if (this.f2791a) {
            c(str, i, j, str2, z);
            return;
        }
        PowerKitCallback powerKitCallback = new PowerKitCallback() { // from class: com.huawei.health.manager.powerkit.PowerKitManager.1
            @Override // com.huawei.health.manager.powerkit.PowerKitManager.PowerKitCallback
            public void connected() {
                abi d = PowerKitManager.this.d();
                if (!PowerKitManager.this.f2791a || d == null) {
                    return;
                }
                PowerKitManager.this.c(str, i, j, str2, z);
            }
        };
        if (this.b == null) {
            this.b = new ArrayList(3);
        }
        this.b.add(powerKitCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, int i, long j, String str2, boolean z) {
        abi d;
        if (!EnvironmentInfo.r()) {
            if (z) {
                e(str, i);
            } else if (!a(str, i)) {
                LogUtil.a("PowerKitManager", "handlePowerKitResource can't unapply, resoureType=", Integer.valueOf(i), ", moduleTag=", str);
                return;
            }
        }
        try {
            d = d();
        } catch (RemoteException e2) {
            e = e2;
        } catch (Exception e3) {
            e = e3;
        }
        if (d == null) {
            return;
        }
        if (i == 100) {
            e(str, d, str2, z);
        } else if (i == 512) {
            e(d, j, str2, z);
        } else if (i == 65535) {
            b(d, j, str2, z);
        }
        e = null;
        if (e != null) {
            a(str, i);
            LogUtil.e("PowerKitManager", "handlePowerKitResource exception = ", LogUtil.a(e));
        }
    }

    private void e(abi abiVar, long j, String str, boolean z) throws RemoteException {
        if (TextUtils.isEmpty(str)) {
            str = IStoragePolicy.PolicyType.NETWORK;
        }
        String str2 = str;
        if (z) {
            String d = BaseApplication.d();
            if (j <= 0) {
                j = 180000;
            }
            LogUtil.c("PowerKitManager", "handleNetworkResource isOpen is ", Boolean.valueOf(abiVar.b(d, 512, j, str2)));
            return;
        }
        LogUtil.c("PowerKitManager", "handleNetworkResource isClose is ", Boolean.valueOf(abiVar.d(BaseApplication.d(), 512, str2)));
    }

    private void b(abi abiVar, long j, String str, boolean z) throws RemoteException {
        if (TextUtils.isEmpty(str)) {
            str = "ALL";
        }
        String str2 = str;
        if (z) {
            String d = BaseApplication.d();
            if (j <= 0) {
                j = 600000;
            }
            LogUtil.c("PowerKitManager", "handleAllResource isOpen is ", Boolean.valueOf(abiVar.b(d, 65535, j, str2)));
            return;
        }
        LogUtil.c("PowerKitManager", "handleAllResource isClose is ", Boolean.valueOf(abiVar.d(BaseApplication.d(), 65535, str2)));
    }

    private void e(String str, abi abiVar, String str2, boolean z) throws RemoteException {
        if (TextUtils.isEmpty(str2)) {
            str2 = "ALL";
        }
        String str3 = str2;
        if (z) {
            LogUtil.c("PowerKitManager", "applyForHuaweiMusicUnfreeze isOpen is ", Boolean.valueOf(abiVar.b(str, 65535, 5000L, str3)));
        } else {
            LogUtil.c("PowerKitManager", "applyForHuaweiMusicUnfreeze isClose is ", Boolean.valueOf(abiVar.d(str, 65535, str3)));
        }
    }

    private void e(String str, int i) {
        int d;
        List<String> list = this.g.get(Integer.valueOf(i));
        if (list == null) {
            list = new ArrayList<>(3);
            this.g.put(Integer.valueOf(i), list);
            d = 0;
        } else {
            d = d(str, list);
        }
        if (d < 5) {
            if (list.size() < 30) {
                list.add(str);
                return;
            } else {
                LogUtil.d("PowerKitManager", "putModuleTag, resType=", Integer.valueOf(i), ", modules=", list);
                return;
            }
        }
        LogUtil.d("PowerKitManager", "putModuleTag multiple calls, resType=", Integer.valueOf(i), ", module=", str, ", size=", Integer.valueOf(list.size()));
    }

    private boolean a(String str, int i) {
        List<String> list = this.g.get(Integer.valueOf(i));
        if (list != null) {
            if (!list.remove(str)) {
                LogUtil.d("PowerKitManager", "removeModuleTag incorrect matching call, resType=", Integer.valueOf(i), ", module=", str);
            }
            if (list.isEmpty()) {
                this.g.remove(Integer.valueOf(i));
            }
        }
        return list == null || list.isEmpty();
    }

    private int d(String str, List<String> list) {
        Iterator<String> it = list.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (str.equals(it.next())) {
                i++;
            }
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.f2791a = true;
        CountDownLatch countDownLatch = this.d;
        if (countDownLatch != null) {
            countDownLatch.countDown();
        }
        abi d = d();
        if (d != null) {
            try {
                LogUtil.c("PowerKitManager", "PowerKit Version=", d.c());
            } catch (RemoteException e2) {
                LogUtil.e("PowerKitManager", "calling getPowerKitVersion err, ", LogUtil.a(e2));
            }
        }
        synchronized (e) {
            List<PowerKitCallback> list = this.b;
            if (list != null) {
                Iterator<PowerKitCallback> it = list.iterator();
                while (it.hasNext()) {
                    it.next().connected();
                }
                this.b.clear();
                this.b = null;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        this.f2791a = false;
        synchronized (e) {
            List<PowerKitCallback> list = this.b;
            if (list != null) {
                list.clear();
                this.b = null;
            }
        }
    }

    static class InnerPowerKitConnection implements PowerKitConnection {
        private PowerKitManager e;

        InnerPowerKitConnection(PowerKitManager powerKitManager) {
            this.e = powerKitManager;
        }

        @Override // com.huawei.android.powerkit.PowerKitConnection
        public void onServiceConnected() {
            LogUtil.c("PowerKitManager", "PowerKitConnection service connected");
            PowerKitManager powerKitManager = this.e;
            if (powerKitManager != null) {
                powerKitManager.c();
            }
        }

        @Override // com.huawei.android.powerkit.PowerKitConnection
        public void onServiceDisconnected() {
            LogUtil.c("PowerKitManager", "PowerKitConnection service disconnected");
            this.e.a();
        }
    }

    public boolean e(List<String> list) {
        if (!CommonUtil.av()) {
            return true;
        }
        abi d = d();
        if (!this.f2791a || d == null) {
            return true;
        }
        try {
            return d.a(list);
        } catch (RemoteException e2) {
            LogUtil.e("PowerKitManager", "calling applyForAlarmExemption ex=, ", LogUtil.a(e2));
            return true;
        }
    }

    public boolean a(List<String> list) {
        if (!CommonUtil.av()) {
            return true;
        }
        abi d = d();
        if (!this.f2791a || d == null) {
            return true;
        }
        try {
            return d.d(list);
        } catch (RemoteException e2) {
            LogUtil.e("PowerKitManager", "calling unApplyForAlarmExemption ex=, ", LogUtil.a(e2));
            return true;
        }
    }
}
