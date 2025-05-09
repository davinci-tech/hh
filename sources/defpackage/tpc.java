package defpackage;

import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.Tasks;
import com.huawei.wearengine.device.Device;
import java.util.List;
import java.util.concurrent.Callable;

/* loaded from: classes7.dex */
public final class tpc {
    private static volatile tpc e;

    /* renamed from: a, reason: collision with root package name */
    private tpd f17297a = tpd.d();

    private tpc() {
    }

    public static tpc d() {
        if (e == null) {
            synchronized (tpc.class) {
                if (e == null) {
                    e = new tpc();
                }
            }
        }
        return e;
    }

    public Task<List<Device>> b() {
        return Tasks.callInBackground(new Callable<List<Device>>() { // from class: tpc.3
            @Override // java.util.concurrent.Callable
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public List<Device> call() {
                List<Device> bondedDevices = tpc.this.f17297a.getBondedDevices();
                if (bondedDevices != null) {
                    return bondedDevices;
                }
                throw new tnx(12);
            }
        });
    }

    public Task<List<Device>> c() {
        return Tasks.callInBackground(new Callable<List<Device>>() { // from class: tpc.2
            @Override // java.util.concurrent.Callable
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public List<Device> call() {
                List<Device> commonDevice = tpc.this.f17297a.getCommonDevice();
                if (commonDevice != null) {
                    return commonDevice;
                }
                throw new tnx(12);
            }
        });
    }

    public Task<Boolean> a() {
        return Tasks.callInBackground(new Callable<Boolean>() { // from class: tpc.5
            @Override // java.util.concurrent.Callable
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public Boolean call() {
                return Boolean.valueOf(tpc.this.f17297a.hasAvailableDevices());
            }
        });
    }
}
