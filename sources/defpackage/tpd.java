package defpackage;

import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.wearengine.DeviceManager;
import com.huawei.wearengine.WearEngineBinderClient;
import com.huawei.wearengine.WearEngineClientInner;
import com.huawei.wearengine.device.Device;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes7.dex */
public class tpd implements DeviceManager, WearEngineBinderClient {
    private static volatile tpd b;
    private final Object d = new Object();
    private IBinder.DeathRecipient c = new IBinder.DeathRecipient() { // from class: tpd.5
        @Override // android.os.IBinder.DeathRecipient
        public void binderDied() {
            tov.b("DeviceServiceProxy", "binderDied enter");
            if (tpd.this.f17300a != null) {
                tpd.this.f17300a.asBinder().unlinkToDeath(tpd.this.c, 0);
                tpd.this.f17300a = null;
            }
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private DeviceManager f17300a = null;

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return null;
    }

    private tpd() {
        a();
    }

    private void b(String str) throws RemoteException {
        tov.b("DeviceServiceProxy", "enter syncCheckConnStatus");
        synchronized (this.d) {
            if (this.f17300a == null) {
                tov.b("DeviceServiceProxy", "syncCheckConnStatusm DeviceManager is null.");
                if (TextUtils.isEmpty(str)) {
                    WearEngineClientInner.c().d();
                } else {
                    WearEngineClientInner.c().e(str);
                }
                b();
            }
            tov.b("DeviceServiceProxy", "syncCheckConnStatus DeviceManager is not null.");
        }
    }

    private void b() throws RemoteException {
        IBinder fcR_ = WearEngineClientInner.c().fcR_(1);
        if (fcR_ == null) {
            throw new tnx(2);
        }
        DeviceManager asInterface = DeviceManager.Stub.asInterface(fcR_);
        this.f17300a = asInterface;
        asInterface.asBinder().linkToDeath(this.c, 0);
    }

    public static tpd d() {
        if (b == null) {
            synchronized (tpd.class) {
                if (b == null) {
                    b = new tpd();
                }
            }
        }
        return b;
    }

    @Override // com.huawei.wearengine.DeviceManager
    public List<Device> getBondedDevices() {
        try {
            b("getBondedDevices");
            if (this.f17300a != null) {
                if (tra.a("device_get_bonded_device_ex")) {
                    return getBondedDeviceEx();
                }
                return this.f17300a.getBondedDevices();
            }
            throw new tnx(6);
        } catch (RemoteException unused) {
            tov.d("DeviceServiceProxy", "getBondedDevices RemoteException");
            throw new tnx(12);
        } catch (IllegalStateException e) {
            throw tnx.e(e);
        }
    }

    @Override // com.huawei.wearengine.DeviceManager
    public List<Device> getAllBondedDevices() {
        try {
            b("getAllBondedDevices");
            if (this.f17300a != null) {
                if (!tra.a("device_get_all_bonded_device")) {
                    tov.d("DeviceServiceProxy", "getAllBondedDevices Health version is low");
                    throw new tnx(14);
                }
                return this.f17300a.getAllBondedDevices();
            }
            throw new tnx(6);
        } catch (RemoteException unused) {
            tov.d("DeviceServiceProxy", "getAllBondedDevices RemoteException");
            throw new tnx(12);
        } catch (IllegalStateException e) {
            throw tnx.e(e);
        }
    }

    @Override // com.huawei.wearengine.DeviceManager
    public List<Device> getCommonDevice() {
        try {
            b("getCommonDevice");
            if (this.f17300a != null) {
                if (!tra.a("device_get_common_device")) {
                    tov.d("DeviceServiceProxy", "getCommonDevice Health version is low");
                    throw new tnx(14);
                }
                return this.f17300a.getCommonDevice();
            }
            throw new tnx(6);
        } catch (RemoteException unused) {
            tov.d("DeviceServiceProxy", "getCommonDevice RemoteException");
            throw new tnx(12);
        } catch (IllegalStateException e) {
            throw tnx.e(e);
        }
    }

    @Override // com.huawei.wearengine.DeviceManager
    public boolean hasAvailableDevices() {
        try {
            b("hasAvailableDevices");
            DeviceManager deviceManager = this.f17300a;
            if (deviceManager != null) {
                return deviceManager.hasAvailableDevices();
            }
            throw new tnx(6);
        } catch (RemoteException unused) {
            tov.d("DeviceServiceProxy", "hasAvailableDevices RemoteException");
            throw new tnx(12);
        } catch (IllegalStateException e) {
            throw tnx.e(e);
        } catch (tnx e2) {
            if (e2.c() == 16) {
                return false;
            }
            throw e2;
        }
    }

    @Override // com.huawei.wearengine.DeviceManager
    public int queryDeviceCapability(Device device, int i) {
        try {
            b(null);
            if (this.f17300a != null) {
                if (!tra.a("query_device_capability")) {
                    tov.d("DeviceServiceProxy", "queryDeviceCapability Health version is low");
                    throw new tnx(14);
                }
                return this.f17300a.queryDeviceCapability(device, i);
            }
            throw new tnx(6);
        } catch (RemoteException unused) {
            tov.d("DeviceServiceProxy", "queryDeviceCapability RemoteException");
            throw new tnx(12);
        } catch (IllegalStateException e) {
            throw tnx.e(e);
        }
    }

    @Override // com.huawei.wearengine.DeviceManager
    public String getHiLinkDeviceId(Device device) {
        try {
            b(null);
            if (this.f17300a != null) {
                if (!tra.a("device_get_hi_link_device_id")) {
                    tov.d("DeviceServiceProxy", "getHiLinkDeviceId Health version is low");
                    throw new tnx(14);
                }
                return this.f17300a.getHiLinkDeviceId(device);
            }
            throw new tnx(6);
        } catch (RemoteException unused) {
            tov.d("DeviceServiceProxy", "getHiLinkDeviceId RemoteException");
            throw new tnx(12);
        } catch (IllegalStateException e) {
            throw tnx.e(e);
        }
    }

    @Override // com.huawei.wearengine.DeviceManager
    public List<Device> getBondedDeviceEx() {
        try {
            b(null);
            DeviceManager deviceManager = this.f17300a;
            if (deviceManager != null) {
                return deviceManager.getBondedDeviceEx();
            }
            throw new tnx(6);
        } catch (RemoteException unused) {
            tov.d("DeviceServiceProxy", "getBondedDeviceEx RemoteException");
            throw new tnx(12);
        } catch (IllegalStateException e) {
            throw tnx.e(e);
        }
    }

    @Override // com.huawei.wearengine.DeviceManager
    public String getDeviceSn(Device device) {
        try {
            b(null);
            if (this.f17300a != null) {
                if (!tra.a(PluginPayAdapter.KEY_DEVICE_INFO_SN)) {
                    tov.d("DeviceServiceProxy", "queryDeviceCapability Health version is low");
                    throw new tnx(14);
                }
                return this.f17300a.getDeviceSn(device);
            }
            throw new tnx(6);
        } catch (RemoteException unused) {
            tov.d("DeviceServiceProxy", "getDeviceSn RemoteException");
            throw new tnx(12);
        } catch (IllegalStateException e) {
            throw tnx.e(e);
        }
    }

    @Override // com.huawei.wearengine.WearEngineBinderClient
    public void clearBinderProxy() {
        this.f17300a = null;
        tov.b("DeviceServiceProxy", "clearBinderProxy");
    }

    private void a() {
        WearEngineClientInner.c().a(new tob(new WeakReference(this)));
    }
}
