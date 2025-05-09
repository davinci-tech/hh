package defpackage;

import android.text.TextUtils;
import com.huawei.devicesdk.entity.DeviceInfo;
import com.huawei.health.device.model.HealthDevice;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class dgw {

    /* renamed from: a, reason: collision with root package name */
    private ArrayList<HealthDevice> f11653a = new ArrayList<>(10);
    private ArrayList<dhc> e = new ArrayList<>(10);

    public ArrayList<HealthDevice> e() {
        return this.f11653a;
    }

    public void a(ArrayList<HealthDevice> arrayList) {
        this.f11653a = arrayList;
    }

    public ArrayList<dhc> d() {
        return this.e;
    }

    public void d(ArrayList<dhc> arrayList) {
        this.e = arrayList;
    }

    public int a() {
        if (dkq.c().d()) {
            ArrayList<dhc> arrayList = this.e;
            if (arrayList != null) {
                return arrayList.size();
            }
            return 0;
        }
        ArrayList<HealthDevice> arrayList2 = this.f11653a;
        if (arrayList2 != null) {
            return arrayList2.size();
        }
        return 0;
    }

    public List<String> c() {
        ArrayList arrayList = new ArrayList(10);
        if (dkq.c().d()) {
            Iterator<dhc> it = this.e.iterator();
            while (it.hasNext()) {
                DeviceInfo a2 = it.next().a();
                if (a2 != null) {
                    arrayList.add(a2.getDeviceMac());
                }
            }
        } else {
            Iterator<HealthDevice> it2 = this.f11653a.iterator();
            while (it2.hasNext()) {
                HealthDevice next = it2.next();
                if (next != null) {
                    arrayList.add(next.getAddress());
                }
            }
        }
        return arrayList;
    }

    public String a(int i) {
        if (dkq.c().d()) {
            return b(i).a().getDeviceMac();
        }
        String address = d(i).getAddress();
        return TextUtils.isEmpty(address) ? d(i).getUniqueId() : address;
    }

    public String c(int i) {
        if (dkq.c().d()) {
            return b(i).a() != null ? b(i).a().getDeviceName() : "empty_name";
        }
        return d(i).getDeviceName();
    }

    private dhc b(int i) {
        dhc dhcVar;
        return (!koq.d(this.e, i) || (dhcVar = this.e.get(i)) == null) ? new dhc("empty_name", new DeviceInfo(), "") : dhcVar;
    }

    private HealthDevice d(int i) {
        HealthDevice healthDevice;
        return (!koq.d(this.f11653a, i) || (healthDevice = this.f11653a.get(i)) == null) ? new HealthDevice() { // from class: dgw.4
            @Override // com.huawei.health.device.model.HealthDevice
            public String getUniqueId() {
                return "empty_mac";
            }

            @Override // com.huawei.health.device.model.HealthDevice
            public String getDeviceName() {
                return "empty_name";
            }

            @Override // com.huawei.health.device.model.HealthDevice
            public String getAddress() {
                return "empty_mac";
            }
        } : healthDevice;
    }
}
