package defpackage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Parcelable;
import android.text.TextUtils;
import com.huawei.health.devicemgr.api.constant.DeviceStateConstants;
import com.huawei.health.devicemgr.api.constant.ExecuteMode;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.StorageParams;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes4.dex */
public class gsy {

    /* renamed from: a, reason: collision with root package name */
    private gww f12918a;
    private Map<String, IBaseResponseCallback> b;
    private a c;
    private String d;
    private String e;
    private String j;

    private gsy() {
        this.b = new ConcurrentHashMap();
    }

    static class d {
        private static final gsy b = new gsy();
    }

    public static gsy b() {
        return d.b;
    }

    static class a extends BroadcastReceiver {
        private final WeakReference<gsy> d;

        a(gsy gsyVar) {
            this.d = new WeakReference<>(gsyVar);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("Track_BoltDeviceManager", "onReceive intent is null");
                return;
            }
            if (intent.getAction() == null) {
                LogUtil.h("Track_BoltDeviceManager", "onReceive getAction is null");
                return;
            }
            LogUtil.a("Track_BoltDeviceManager", "ConnectionStateBroadcastReceiver onReceive() getAction: ", intent.getAction());
            if ("com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                Parcelable parcelableExtra = intent.getParcelableExtra("deviceinfo");
                if (!(parcelableExtra instanceof DeviceInfo)) {
                    LogUtil.h("Track_BoltDeviceManager", "object is not instanceof DeviceInfo !");
                    return;
                }
                DeviceInfo deviceInfo = (DeviceInfo) parcelableExtra;
                if (deviceInfo.getProductType() != 75) {
                    return;
                }
                gsy.c(deviceInfo);
                gsy gsyVar = this.d.get();
                if (gsyVar != null) {
                    gsyVar.a();
                } else {
                    LogUtil.h("Track_BoltDeviceManager", "BoltConnectionStateReceiver deviceManager is null");
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(DeviceInfo deviceInfo) {
        gww e = e();
        String x = e.x();
        LogUtil.a("Track_BoltDeviceManager", "preemptionBoltIdentify = ", CommonUtil.l(x));
        if (deviceInfo.getDeviceConnectState() == 2 && x.equals(deviceInfo.getDeviceIdentify())) {
            d(deviceInfo.getDeviceIdentify(), e);
        }
    }

    private static void d(String str, gww gwwVar) {
        int deviceStateById = cun.c().getDeviceStateById(DeviceStateConstants.WEAR_PLACE, str, "", "Track_BoltDeviceManager");
        LogUtil.a("Track_BoltDeviceManager", "wearPlaceCode = ", Integer.valueOf(deviceStateById));
        if (deviceStateById == 13) {
            gwwVar.l(str);
        } else if (deviceStateById == 8) {
            gwwVar.o(str);
        } else if (deviceStateById == 53) {
            gwwVar.k(str);
        }
    }

    public void c(String str, IBaseResponseCallback iBaseResponseCallback) {
        if (TextUtils.isEmpty(str) || iBaseResponseCallback == null) {
            LogUtil.h("Track_BoltDeviceManager", "registerConnectionStatusCallback failed with params error. tag:", str, " callback:", iBaseResponseCallback);
        } else {
            if (this.b.containsKey(str)) {
                LogUtil.h("Track_BoltDeviceManager", "registerConnectionStatusCallback ", str, " is save");
                return;
            }
            this.b.put(str, iBaseResponseCallback);
            LogUtil.a("Track_BoltDeviceManager", "registerConnectionStatusCallback tag:", str, " callback:", iBaseResponseCallback, " register callback size:", Integer.valueOf(this.b.size()));
            j();
        }
    }

    public void e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Track_BoltDeviceManager", "unregisterConnectionStatusCallback failed with params error. tag:");
        } else {
            LogUtil.a("Track_BoltDeviceManager", "unregisterConnectionStatusCallback tag:", str, " callback:", this.b.remove(str), " register callback size:", Integer.valueOf(this.b.size()));
            f();
        }
    }

    private void j() {
        if (this.c != null) {
            return;
        }
        if (!this.b.isEmpty()) {
            this.c = new a(this);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
            BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.c, intentFilter, LocalBroadcast.c, null);
            LogUtil.a("Track_BoltDeviceManager", "registerConnectionStatusBroadcast : registerReceiver");
            return;
        }
        LogUtil.h("Track_BoltDeviceManager", "mCallbacks is empty");
    }

    private void f() {
        if (this.b.size() != 0 || this.c == null) {
            return;
        }
        LogUtil.a("Track_BoltDeviceManager", "unregisterConnectionStatusBroadcast() enter.");
        BaseApplication.getContext().unregisterReceiver(this.c);
        this.c = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        LogUtil.a("Track_BoltDeviceManager", "distributeConnectionStatusChanged enter.");
        HashMap hashMap = new HashMap(this.b);
        Iterator it = hashMap.values().iterator();
        while (it.hasNext()) {
            ((IBaseResponseCallback) it.next()).d(0, null);
        }
        LogUtil.a("Track_BoltDeviceManager", "distributeConnectionStatusChanged callback:", hashMap.keySet());
    }

    public List<b> a(boolean z) {
        LogUtil.a("Track_BoltDeviceManager", "getBoltDeviceList() isRecognizeRadioFrequencyId: ", Boolean.valueOf(z));
        ArrayList arrayList = new ArrayList(10);
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "Track_BoltDeviceManager");
        if (koq.b(deviceList)) {
            LogUtil.h("Track_BoltDeviceManager", "getBoltDeviceList() deviceInfoList is empty");
            return arrayList;
        }
        for (DeviceInfo deviceInfo : deviceList) {
            if (deviceInfo != null && deviceInfo.getProductType() == 75) {
                c(z, deviceInfo);
                String deviceIdentify = deviceInfo.getDeviceIdentify();
                int b2 = b(deviceIdentify);
                int a2 = a(deviceIdentify);
                LogUtil.a("Track_BoltDeviceManager", "getBoltDeviceList() wearPlaceCode: ", Integer.valueOf(a2), ", batteryInfo: ", Integer.valueOf(b2), ", deviceIdentify: ", CommonUtil.l(deviceIdentify));
                arrayList.add(new b(deviceInfo, b2, a2));
            }
        }
        LogUtil.a("Track_BoltDeviceManager", "getBoltDeviceList() boltDeviceInfoList: ", arrayList);
        return arrayList;
    }

    private void c(boolean z, DeviceInfo deviceInfo) {
        if (z) {
            cun.c().sendDeviceData(5055, 900100001, null, deviceInfo, "Track_BoltDeviceManager");
            cun.c().sendDeviceData(1, 8, null, deviceInfo, "Track_BoltDeviceManager");
            LogUtil.a("Track_BoltDeviceManager", "setRadioFrequencyId : sendDeviceData get new position and battery power");
        }
    }

    private int a(String str) {
        int deviceStateById = cun.c().getDeviceStateById(DeviceStateConstants.WEAR_PLACE, str, "", "Track_BoltDeviceManager");
        if (deviceStateById == 13) {
            return 0;
        }
        if (deviceStateById == 8) {
            return 1;
        }
        if (deviceStateById == 53) {
            return 2;
        }
        if (deviceStateById == 240) {
            return 3;
        }
        LogUtil.h("Track_BoltDeviceManager", "getWearPlaceCode() wearPlaceCode is -1");
        return -1;
    }

    public List<b> e(int i) {
        LogUtil.a("Track_BoltDeviceManager", "getCurrentConnectedList() sportType: ", Integer.valueOf(i));
        d();
        Map<Integer, List<b>> a2 = a(a(false), true);
        ArrayList arrayList = new ArrayList(2);
        StringBuilder sb = new StringBuilder();
        Iterator<b> it = a2.get(0).iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            b next = it.next();
            if (next.e().getDeviceIdentify().equals(this.d)) {
                sb.append("FOOT deviceName: ");
                sb.append(next.e().getDeviceName());
                sb.append(", deviceId: ");
                sb.append(CommonUtil.l(this.d));
                arrayList.add(next);
                break;
            }
        }
        if (i == 259) {
            Iterator<b> it2 = a2.get(2).iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                b next2 = it2.next();
                if (next2.e().getDeviceIdentify().equals(this.e)) {
                    sb.append(", BIKE deviceName: ");
                    sb.append(next2.e().getDeviceName());
                    sb.append(", deviceId: ");
                    sb.append(CommonUtil.l(this.e));
                    arrayList.add(next2);
                    break;
                }
            }
            return arrayList;
        }
        Iterator<b> it3 = a2.get(1).iterator();
        while (true) {
            if (!it3.hasNext()) {
                break;
            }
            b next3 = it3.next();
            if (next3.e().getDeviceIdentify().equals(this.j)) {
                sb.append(", WAIST deviceName: ");
                sb.append(next3.e().getDeviceName());
                sb.append(", deviceId: ");
                sb.append(CommonUtil.l(this.j));
                arrayList.add(next3);
                break;
            }
        }
        LogUtil.a("Track_BoltDeviceManager", "getCurrentConnectedList() info: ", sb.toString(), ", boltDeviceInfoList: ", arrayList);
        return arrayList;
    }

    private void d() {
        if (this.f12918a == null) {
            this.f12918a = e();
        }
        this.j = this.f12918a.ad();
        this.d = this.f12918a.y();
        this.e = this.f12918a.u();
        LogUtil.a("Track_BoltDeviceManager", "acquireDeviceIdentify() mWearWaistBoltIdentify = ", CommonUtil.l(this.j), ", mWearFootBoltIdentify = ", CommonUtil.l(this.d), ", mWearBikeBoltIdentify = ", CommonUtil.l(this.e));
    }

    public List<b> b(List<b> list) {
        ArrayList arrayList = new ArrayList(10);
        if (list == null) {
            return arrayList;
        }
        for (b bVar : list) {
            if (bVar.b == -1 && bVar.e.getDeviceConnectState() == 2) {
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }

    public boolean b(List<b> list, int i) {
        int d2;
        if (koq.b(list)) {
            return false;
        }
        int d3 = d(list, 0);
        if (i == 259) {
            d2 = d(list, 2);
        } else {
            d2 = d(list, 1);
        }
        return d3 > 0 && d2 > 0;
    }

    public List<b> e(List<b> list, int i) {
        ArrayList arrayList = new ArrayList(10);
        if (koq.b(list)) {
            return arrayList;
        }
        int i2 = i == 259 ? 1 : 2;
        for (b bVar : list) {
            if (bVar != null && bVar.b != -1 && bVar.b != i2 && bVar.e.getDeviceConnectState() != 2) {
                arrayList.add(bVar);
            }
        }
        return arrayList;
    }

    private int d(List<b> list, int i) {
        int i2 = 0;
        if (koq.b(list)) {
            return 0;
        }
        for (b bVar : list) {
            if (bVar != null && bVar.e.getDeviceConnectState() == 2 && bVar.b == i) {
                i2++;
            }
        }
        return i2;
    }

    public Map<Integer, List<b>> a(List<b> list, boolean z) {
        LogUtil.a("Track_BoltDeviceManager", "getWearingPositionBoltList() boltDeviceInfoList: ", Integer.valueOf(list.size()), ", isFilteringConnected: ", Boolean.valueOf(z));
        HashMap hashMap = new HashMap();
        if (list == null) {
            LogUtil.h("Track_BoltDeviceManager", "getWearingPositionBoltList() boltDeviceInfoList is null, return");
            return hashMap;
        }
        ArrayList arrayList = new ArrayList(10);
        ArrayList arrayList2 = new ArrayList(10);
        ArrayList arrayList3 = new ArrayList(10);
        ArrayList arrayList4 = new ArrayList(10);
        for (b bVar : list) {
            if (!z || bVar.e().getDeviceConnectState() == 2) {
                if (bVar.b == 0) {
                    arrayList.add(bVar);
                } else if (bVar.b == 1) {
                    arrayList2.add(bVar);
                } else if (bVar.b == 2) {
                    arrayList3.add(bVar);
                } else if (bVar.b == -1) {
                    arrayList4.add(bVar);
                }
            }
        }
        hashMap.put(0, arrayList);
        hashMap.put(1, arrayList2);
        hashMap.put(2, arrayList3);
        hashMap.put(-1, arrayList4);
        LogUtil.a("Track_BoltDeviceManager", "getWearingPositionBoltList() map: ", hashMap);
        return hashMap;
    }

    public void c() {
        List<b> a2 = b().a(false);
        for (int i = 0; i < a2.size(); i++) {
            b bVar = a2.get(i);
            if (bVar.e().getDeviceConnectState() == 3) {
                String deviceIdentify = bVar.e().getDeviceIdentify();
                cul culVar = new cul();
                culVar.d(deviceIdentify);
                cun.c().executeDeviceRelatedLogic(ExecuteMode.NOTIFY_CONNECT_DEVICE, culVar, "Track_BoltDeviceManager");
                LogUtil.a("Track_BoltDeviceManager", "connectBoltDevice : deviceIdentify = ", CommonUtil.l(deviceIdentify));
            }
        }
    }

    public void e(List<b> list) {
        LogUtil.a("Track_BoltDeviceManager", " disconnectDevice enter ");
        if (koq.b(list)) {
            LogUtil.b("Track_BoltDeviceManager", " disconnectDevice boltDeviceInfoList is null ");
            return;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (b bVar : list) {
            if (bVar.e().getDeviceConnectState() == 2) {
                LogUtil.a("Track_BoltDeviceManager", "disconnectDevice device is ", bVar.e().getDeviceName());
                arrayList.add(bVar.e().getDeviceIdentify());
            }
        }
        if (koq.c(arrayList)) {
            cun.c().unPair(arrayList, false);
        }
    }

    public void d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Track_BoltDeviceManager", "preemptionBoltDevice deviceIdentify is empty");
            return;
        }
        cul culVar = new cul();
        culVar.d(str);
        cun.c().executeDeviceRelatedLogic(ExecuteMode.NOTIFY_SEIZE_DEVICE, culVar, "Track_BoltDeviceManager");
        LogUtil.a("Track_BoltDeviceManager", "preemptionBoltDevice : deviceIdentify = ", CommonUtil.l(str));
    }

    public static gww e() {
        return new gww(BaseApplication.getContext(), new StorageParams(), Integer.toString(20002));
    }

    public int b(String str) {
        return cun.c().getDeviceStateById(DeviceStateConstants.BATTERY, str, "", "Track_BoltDeviceManager");
    }

    public static void a(DeviceInfo deviceInfo) {
        cuk.b().sendSampleConfigCommand(deviceInfo, b(2), "Track_BoltDeviceManager");
    }

    private static cvq b(int i) {
        cvq cvqVar = new cvq();
        cvqVar.setSrcPkgName("hw.unitedevice.finddevice");
        cvqVar.setWearPkgName("findDevice");
        cvqVar.setCommandId(1);
        ArrayList arrayList = new ArrayList(5);
        cvn cvnVar = new cvn();
        cvnVar.e(900100002L);
        cvnVar.d(i);
        arrayList.add(cvnVar);
        cvqVar.setConfigInfoList(arrayList);
        return cvqVar;
    }

    public static class b implements Comparable<b> {

        /* renamed from: a, reason: collision with root package name */
        private boolean f12919a;
        private final int b;
        private final int c;
        private boolean d;
        private final DeviceInfo e;

        public b(DeviceInfo deviceInfo, int i, int i2) {
            this.e = deviceInfo;
            this.c = i;
            this.b = i2;
        }

        public boolean d() {
            return this.d;
        }

        public void e(boolean z) {
            this.d = z;
        }

        public boolean c() {
            return this.f12919a;
        }

        public void a(boolean z) {
            this.f12919a = z;
        }

        public DeviceInfo e() {
            return this.e;
        }

        public int a() {
            return this.c;
        }

        public int b() {
            return this.b;
        }

        @Override // java.lang.Comparable
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public int compareTo(b bVar) {
            return (int) (bVar.e.getLastConnectedTime() - this.e.getLastConnectedTime());
        }

        public boolean equals(Object obj) {
            return (obj instanceof b) && ((b) obj).e.getLastConnectedTime() == this.e.getLastConnectedTime();
        }

        public int hashCode() {
            DeviceInfo deviceInfo = this.e;
            if (deviceInfo == null) {
                return 0;
            }
            return (int) deviceInfo.getLastConnectedTime();
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (this.e != null) {
                sb.append("{deviceName=");
                sb.append(this.e.getDeviceName());
                sb.append(", deviceId=");
                sb.append(CommonUtil.l(this.e.getDeviceIdentify()));
                sb.append(", deviceConnectState=");
                sb.append(this.e.getDeviceConnectState());
            }
            sb.append(", mBatteryInfo=");
            sb.append(this.c);
            sb.append(", mWearingPosition=");
            sb.append(this.b);
            sb.append(", mIsChecked=");
            sb.append(this.d);
            sb.append(", mIsPreempting=");
            sb.append(this.f12919a);
            sb.append("}");
            return sb.toString();
        }
    }
}
