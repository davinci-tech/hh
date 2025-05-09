package defpackage;

import android.text.TextUtils;
import com.huawei.health.device.callback.IDeviceEventHandler;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.hwcloudmodel.model.unite.DevInfo;
import com.huawei.hwcloudmodel.model.unite.DeviceDetailInfo;
import com.huawei.hwcloudmodel.model.unite.ServiceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class ctk extends MeasurableDevice {

    /* renamed from: a, reason: collision with root package name */
    private String f11461a;
    private List<d> c;
    private String d;
    private a e = new a();

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public boolean connectAsync(IHealthDeviceCallback iHealthDeviceCallback) {
        return false;
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public boolean connectSync(int i) {
        return false;
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public void disconnect() {
    }

    public ctk() {
    }

    public ctk(String str) {
        this.d = str;
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public String getProductId() {
        return this.d;
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public void setProductId(String str) {
        this.d = str;
    }

    public List<d> c() {
        return this.c;
    }

    public void a(List<d> list) {
        this.c = list;
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public boolean doCreateBond(IDeviceEventHandler iDeviceEventHandler) {
        long b;
        if (b() == null) {
            LogUtil.h("WiFiDevice", "doCreateBond device info is null");
            return false;
        }
        String str = b().m;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("WiFiDevice", "doCreateBond serial number is null");
            return false;
        }
        ctk h = ctq.h(str);
        Object[] objArr = new Object[2];
        objArr[0] = "deleteDevice check huawei device ";
        objArr[1] = h == null ? "is null" : "is not null";
        LogUtil.a("WiFiDevice", objArr);
        if (h != null) {
            b = ctq.c(this);
        } else {
            b = ctq.b(this);
        }
        if (b >= 0) {
            iDeviceEventHandler.onStateChanged(7);
            return true;
        }
        LogUtil.h("WiFiDevice", "doCreateBond Failed to save device information to WiFiBindDevice database");
        if (ceo.d().c(b().m, false) != null) {
            LogUtil.a("WiFiDevice", "doCreateBond delete device information in device database");
            cjx.e().k(b().m);
        }
        iDeviceEventHandler.onStateChanged(8);
        return false;
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public boolean doRemoveBond() {
        return ctq.b(getSerialNumber()) >= 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(StringBuffer stringBuffer, String str, String str2, String str3) {
        if (str != null && str.length() < 512) {
            stringBuffer.append(str);
        }
        if (str2 != null && str2.length() < 512) {
            stringBuffer.append(str2);
        }
        if (str3 == null || str3.length() >= 512) {
            return;
        }
        stringBuffer.append(str3);
    }

    public void a(String str) {
        this.f11461a = str;
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private long f11462a;
        private String b;
        private String c;
        private String d;
        private String e;
        private String f;
        private String g;
        private String h;
        private String i;
        private String j;
        private String l;
        private String m;
        private int n;
        private int o;

        public String a() {
            return this.d;
        }

        public void b(String str) {
            this.d = str;
        }

        public long d() {
            return this.f11462a;
        }

        public void e(long j) {
            this.f11462a = j;
        }

        public String m() {
            return this.m;
        }

        public void h(String str) {
            this.m = str;
        }

        public String g() {
            return this.j;
        }

        public void g(String str) {
            this.j = str;
        }

        public String c() {
            return this.c;
        }

        public void e(String str) {
            this.c = str;
        }

        public String h() {
            return this.g;
        }

        public void f(String str) {
            this.g = str;
        }

        public String f() {
            return this.h;
        }

        public void j(String str) {
            this.h = str;
        }

        public String b() {
            return this.e;
        }

        public void a(String str) {
            this.e = str;
        }

        public String j() {
            return this.f;
        }

        public void i(String str) {
            this.f = str;
        }

        public String e() {
            return this.b;
        }

        public void c(String str) {
            this.b = str;
        }

        public String i() {
            return this.i;
        }

        public void d(String str) {
            this.i = str;
        }

        public String o() {
            return this.l;
        }

        public void l(String str) {
            this.l = str;
        }

        public int n() {
            return this.n;
        }

        public void e(int i) {
            this.n = i;
        }

        public int k() {
            return this.o;
        }

        public void d(int i) {
            this.o = i;
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer(1024);
            stringBuffer.append("DeviceInfo{");
            ctk.a(stringBuffer, "deviceId ='", cpw.d(this.d), "'");
            ctk.a(stringBuffer, ", sn ='", cpw.d(this.m), "'");
            ctk.a(stringBuffer, ", model ='", this.j, "'");
            ctk.a(stringBuffer, ", deviceType ='", this.c, "'");
            ctk.a(stringBuffer, ", manu ='", this.g, "'");
            ctk.a(stringBuffer, ", proId ='", this.h, "'");
            ctk.a(stringBuffer, ", hiv =", this.e, "'");
            ctk.a(stringBuffer, ", mac =", this.f, "'");
            ctk.a(stringBuffer, ", fwv =", this.b, "'");
            ctk.a(stringBuffer, ", hwv =", this.i, "'");
            ctk.a(stringBuffer, ", swv =", this.l, "'");
            stringBuffer.append(", protType =").append(this.n).append('\'');
            stringBuffer.append(", source =").append(this.o).append('\'');
            stringBuffer.append('}');
            return stringBuffer.toString();
        }

        public boolean l() {
            return TextUtils.isEmpty(this.d) || TextUtils.isEmpty(this.m) || TextUtils.isEmpty(this.j) || TextUtils.isEmpty(this.c) || TextUtils.isEmpty(this.g) || TextUtils.isEmpty(this.e);
        }
    }

    public boolean f() {
        return TextUtils.isEmpty(this.d) || this.e.l();
    }

    public static class d {

        /* renamed from: a, reason: collision with root package name */
        private String f11463a;
        private String c;

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer("ServiceInfo{sid ='");
            stringBuffer.append(this.c).append("'st ='");
            stringBuffer.append(this.f11463a);
            stringBuffer.append('}');
            return stringBuffer.toString();
        }
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice, com.huawei.health.device.model.HealthDevice
    public String getDeviceName() {
        String deviceName = super.getDeviceName();
        if (!TextUtils.isEmpty(deviceName)) {
            return deviceName;
        }
        a aVar = this.e;
        if (aVar != null && !TextUtils.isEmpty(aVar.j)) {
            return this.e.j;
        }
        LogUtil.h("WiFiDevice", "can not get deviceName, return default");
        return "WiFiDevice";
    }

    public String d() {
        a aVar = this.e;
        if (aVar != null) {
            return aVar.d;
        }
        LogUtil.h("WiFiDevice", "getDeviceId mDeviceInfo is null");
        return "";
    }

    public long a() {
        a aVar = this.e;
        if (aVar != null) {
            return aVar.f11462a;
        }
        LogUtil.h("WiFiDevice", "getDeviceCode mDeviceInfo is null");
        return 0L;
    }

    @Override // com.huawei.health.device.model.HealthDevice
    public String getAddress() {
        a aVar = this.e;
        if (aVar != null) {
            return aVar.f;
        }
        LogUtil.h("WiFiDevice", "getAddress mDeviceInfo is null");
        return "";
    }

    public String e() {
        return this.f11461a;
    }

    @Override // com.huawei.health.device.model.HealthDevice
    public String getUniqueId() {
        if (!TextUtils.isEmpty(this.f11461a)) {
            return this.f11461a;
        }
        a aVar = this.e;
        if (aVar != null) {
            return aVar.d;
        }
        LogUtil.h("WiFiDevice", "getUniqueId mDeviceInfo is null");
        return "";
    }

    @Override // com.huawei.health.device.connectivity.comm.MeasurableDevice
    public String getSerialNumber() {
        a aVar = this.e;
        if (aVar != null && !TextUtils.isEmpty(aVar.m)) {
            return this.e.m;
        }
        LogUtil.h("WiFiDevice", "can not get SerialNumber from mDeviceInfo");
        return super.getSerialNumber();
    }

    public String toString() {
        StringBuffer stringBuffer = new StringBuffer(512);
        stringBuffer.append("WiFiDevice{");
        a(stringBuffer, "mProductId ='", this.d, "'");
        a(stringBuffer, ", mDeviceInfo ='", this.e.toString(), "'");
        a(stringBuffer, "uniqueId ='", getUniqueId(), "'");
        a(stringBuffer, "sn ='", getSerialNumber(), null);
        stringBuffer.append('}');
        return stringBuffer.toString();
    }

    public a b() {
        return this.e;
    }

    public void b(a aVar) {
        this.e = aVar;
    }

    public void a(DeviceDetailInfo deviceDetailInfo) {
        a aVar;
        if (deviceDetailInfo != null && (aVar = this.e) != null) {
            aVar.d = deviceDetailInfo.getDevId();
            if (deviceDetailInfo.getDeviceCode() != null) {
                this.e.f11462a = deviceDetailInfo.getDeviceCode().longValue();
            }
            DevInfo devInfo = deviceDetailInfo.getDevInfo();
            if (devInfo != null) {
                this.e.m = devInfo.getSn();
                this.e.j = devInfo.getModel();
                this.e.c = devInfo.getDevType();
                this.e.g = devInfo.getManu();
                this.e.h = devInfo.getProdId();
                this.e.e = devInfo.getHiv();
                this.e.f = devInfo.getMac();
                this.e.b = devInfo.getFwv();
                this.e.i = devInfo.getHwv();
                this.e.l = devInfo.getSwv();
                this.e.n = devInfo.getProtType();
            }
            List<ServiceInfo> services = deviceDetailInfo.getServices();
            if (services == null || services.size() <= 0) {
                return;
            }
            this.c = new ArrayList(16);
            for (ServiceInfo serviceInfo : services) {
                d dVar = new d();
                dVar.c = serviceInfo.sid;
                dVar.f11463a = serviceInfo.st;
                this.c.add(dVar);
            }
            return;
        }
        LogUtil.h("WiFiDevice", "setDeviceInfo device or mDeviceInfo is null");
    }
}
