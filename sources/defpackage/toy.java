package defpackage;

import android.os.IBinder;
import com.huawei.wearengine.core.device.VirtualDevice;
import com.huawei.wearengine.device.Device;

/* loaded from: classes9.dex */
public class toy {
    private volatile VirtualDevice b;

    public static class e {
        public static final toy b = new toy();
    }

    private toy() {
        this.b = new tpe();
    }

    public static toy b() {
        return e.b;
    }

    public VirtualDevice[] a() {
        this.b.initModule();
        return new VirtualDevice[]{this.b};
    }

    public VirtualDevice d(Device device) {
        if (device == null || device.getProductType() != 0) {
            return null;
        }
        this.b.initModule();
        return this.b;
    }

    public void fcZ_(String str, IBinder iBinder) {
        tos.a("DevicePolicyManager", "setHiWearEngineService enter");
        this.b.setBinder(str, iBinder);
    }
}
