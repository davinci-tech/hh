package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class jgf {

    /* renamed from: a, reason: collision with root package name */
    private static volatile jgf f13807a;
    private final Object b = new Object();
    private List<IBaseResponseCallback> c = new ArrayList(16);

    public static jgf b() {
        if (f13807a == null) {
            synchronized (jgf.class) {
                if (f13807a == null) {
                    f13807a = new jgf();
                }
            }
        }
        return f13807a;
    }

    public void d(IBaseResponseCallback iBaseResponseCallback) {
        synchronized (this.b) {
            if (!this.c.contains(iBaseResponseCallback)) {
                this.c.add(iBaseResponseCallback);
            }
        }
    }

    public void c(IBaseResponseCallback iBaseResponseCallback) {
        synchronized (this.b) {
            this.c.remove(iBaseResponseCallback);
        }
    }

    public void b(String str) {
        synchronized (this.b) {
            if (!koq.b(this.c)) {
                Iterator<IBaseResponseCallback> it = this.c.iterator();
                while (it.hasNext()) {
                    it.next().d(1, str);
                }
            }
        }
    }

    public boolean a(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DeleteDeviceManager", "isUpdate, deviceId is null");
            return false;
        }
        DeviceInfo e = jpt.e(str, "DeleteDeviceManager");
        if (e == null || !((e.getDeviceConnectState() == 2 || e.getDeviceConnectState() == 1) && jkj.d().c(str) == 6)) {
            return false;
        }
        LogUtil.h("DeleteDeviceManager", "Enter unbind ，device is OTAing");
        return true;
    }
}
