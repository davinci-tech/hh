package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.outofprocess.mgr.device.ProfileAgent;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.profile.client.profile.ProfileClient;
import com.huawei.profile.profile.DeviceProfile;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes5.dex */
public class jnw {
    private static jnw e;
    private boolean c = false;
    private boolean d = false;
    private Map<String, IBaseResponseCallback> h = new ConcurrentHashMap(16);
    private List<DeviceProfile> j;
    private static final Object b = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static final Object f13976a = new Object();

    public static jnw e() {
        jnw jnwVar;
        synchronized (b) {
            if (e == null) {
                e = new jnw();
            }
            jnwVar = e;
        }
        return jnwVar;
    }

    public void d(final String str, final IBaseResponseCallback iBaseResponseCallback) {
        ReleaseLogUtil.e("R_IntellLifeCloudDevice", "getDevicesFromCloud mode:", str);
        if (!bfe.b.contains(str)) {
            a(iBaseResponseCallback, -1);
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: jnz
                @Override // java.lang.Runnable
                public final void run() {
                    jnw.this.e(iBaseResponseCallback, str);
                }
            });
        }
    }

    /* synthetic */ void e(IBaseResponseCallback iBaseResponseCallback, String str) {
        if (Utils.o() || !tri.d(BaseApplication.getContext())) {
            ReleaseLogUtil.e("R_IntellLifeCloudDevice", "oversea or not authorized");
            synchronized (f13976a) {
                a(iBaseResponseCallback, -1);
            }
            return;
        }
        Object obj = f13976a;
        synchronized (obj) {
            this.h.put(str, iBaseResponseCallback);
            ReleaseLogUtil.e("R_IntellLifeCloudDevice", "getDevicesFromCloud isGettingDevice ", Boolean.valueOf(this.d));
            if (this.d) {
                return;
            }
            this.d = true;
            try {
                if (!d(BaseApplication.getContext(), str)) {
                    LogUtil.a("IntellLifeCloudDevice", "getDevicesFromCloud isLessTenMinutes");
                    synchronized (obj) {
                        Iterator<Map.Entry<String, IBaseResponseCallback>> it = this.h.entrySet().iterator();
                        while (it.hasNext()) {
                            a(it.next().getValue(), 0);
                        }
                        this.h.clear();
                    }
                    return;
                }
                this.c = false;
                c(str);
            } finally {
                this.d = false;
            }
        }
    }

    private void a(IBaseResponseCallback iBaseResponseCallback, int i) {
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(i, this.j);
        } else {
            LogUtil.h("IntellLifeCloudDevice", "callbackResponse callback is null");
        }
    }

    private void c(String str) {
        ReleaseLogUtil.e("R_IntellLifeCloudDevice", "getDevicesFromCloudData start, mode ", str);
        List<DeviceProfile> devices = ProfileAgent.PROFILE_AGENT.getClientAgent().getDevices(true, Arrays.asList(ProfileClient.CLOUD_LOCAL_DOMAIN));
        this.j = devices;
        if (devices != null) {
            ReleaseLogUtil.e("R_IntellLifeCloudDevice", "getDevicesFromCloudData getDevices success, mode ", str);
            synchronized (f13976a) {
                Iterator<Map.Entry<String, IBaseResponseCallback>> it = this.h.entrySet().iterator();
                while (it.hasNext()) {
                    a(it.next().getValue(), 0);
                }
                this.h.clear();
            }
            KeyValDbManager.b(BaseApplication.getContext()).e("profile_devices_last_query_time_01" + str, String.valueOf(System.currentTimeMillis()));
            return;
        }
        ReleaseLogUtil.e("R_IntellLifeCloudDevice", "getDevicesFromCloudData null, isRetryGetDevice:", Boolean.valueOf(this.c));
        if (this.c) {
            synchronized (f13976a) {
                Iterator<Map.Entry<String, IBaseResponseCallback>> it2 = this.h.entrySet().iterator();
                while (it2.hasNext()) {
                    a(it2.next().getValue(), -1);
                }
                this.h.clear();
            }
            KeyValDbManager.b(BaseApplication.getContext()).e("profile_devices_last_query_time_01" + str, String.valueOf(System.currentTimeMillis()));
            return;
        }
        try {
            Thread.sleep(20000L);
        } catch (InterruptedException e2) {
            LogUtil.b("IntellLifeCloudDevice", "InterruptedException: ", e2.getMessage());
        }
        c(str);
        this.c = true;
    }

    private boolean d(Context context, String str) {
        if (!CommonUtil.ag(context)) {
            LogUtil.a("IntellLifeCloudDevice", "beta isMoreTenMinutes true");
            return true;
        }
        if (context == null) {
            LogUtil.h("IntellLifeCloudDevice", "isMoreTenMinutes context is null");
            return false;
        }
        String e2 = KeyValDbManager.b(context).e("profile_devices_last_query_time_01" + str);
        long currentTimeMillis = System.currentTimeMillis();
        if (!TextUtils.isEmpty(e2)) {
            LogUtil.a("IntellLifeCloudDevice", "nowTime:", Long.valueOf(currentTimeMillis), " lastScanTime:", e2);
            if (currentTimeMillis - CommonUtil.n(context, e2) <= 600000.0f) {
                return false;
            }
            KeyValDbManager.b(context).e("profile_devices_last_query_time_01" + str, String.valueOf(currentTimeMillis));
            return true;
        }
        LogUtil.a("IntellLifeCloudDevice", "first update nowTime:", Long.valueOf(currentTimeMillis));
        KeyValDbManager.b(context).e("profile_devices_last_query_time_01" + str, String.valueOf(currentTimeMillis));
        return true;
    }
}
