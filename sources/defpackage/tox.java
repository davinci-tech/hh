package defpackage;

import android.text.TextUtils;
import com.huawei.wearengine.core.device.VirtualDevice;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.monitor.MonitorData;
import com.huawei.wearengine.monitor.MonitorDataCallback;
import com.huawei.wearengine.monitor.MonitorItem;
import com.huawei.wearengine.monitor.MonitorMessage;
import com.huawei.wearengine.monitor.SwitchStatusCallback;
import defpackage.tos;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes9.dex */
public class tox {
    private volatile Map<tpk, Map<String, List<String>>> e = new ConcurrentHashMap();

    public int b(String str, List<MonitorItem> list, tpk tpkVar) {
        if (!d(str, list, tpkVar)) {
            tos.e("MonitorCallbackManager", "HandleMonitorCallbackProxy parameters is invalid");
            return 5;
        }
        tos.b("MonitorCallbackManager", "registerMonitor monitorCallbackProxy pid is:" + tpkVar.b() + ", hashcode is:" + tpkVar.a());
        tpk b = b(tpkVar);
        if (b == null) {
            Iterator<MonitorItem> it = list.iterator();
            int i = 0;
            while (it.hasNext()) {
                i = a(tpkVar.e(), c(str, it.next().getName()));
                if (i >= 10) {
                    tos.e("MonitorCallbackManager", "monitor data callback is exceed 10!");
                    return 11;
                }
            }
            tos.a("MonitorCallbackManager", "registerMonitor new monitor, ListSize：" + i);
            c(str, list, tpkVar);
        } else {
            tos.a("MonitorCallbackManager", "registerMonitor already has monitor");
            a(str, list, b);
        }
        tos.b("MonitorCallbackManager", "handleMonitorCallbackProxy mDeviceMonitorCallbackMap is:" + this.e.toString());
        return 0;
    }

    private int a(String str, List<tpk> list) {
        int i = 0;
        for (tpk tpkVar : list) {
            if (tpkVar != null && tpkVar.e().equals(str)) {
                i++;
            }
        }
        return i;
    }

    private void a(String str, List<MonitorItem> list, tpk tpkVar) {
        Map<String, List<String>> map = this.e.get(tpkVar);
        List<String> list2 = map.get(str);
        if (list2 == null) {
            list2 = new ArrayList<>();
        }
        for (MonitorItem monitorItem : list) {
            if (!list2.contains(monitorItem.getName())) {
                list2.add(monitorItem.getName());
            }
        }
        map.put(str, list2);
    }

    private boolean d(String str, List<MonitorItem> list, tpk tpkVar) {
        if (str == null || tpkVar == null || list == null) {
            tos.e("MonitorCallbackManager", "isValidParameters parameter is null");
            return false;
        }
        if (TextUtils.isEmpty(tpkVar.e())) {
            tos.e("MonitorCallbackManager", "isValidParameters srcPkgName is null");
            return false;
        }
        for (MonitorItem monitorItem : list) {
            if (monitorItem == null || TextUtils.isEmpty(monitorItem.getName())) {
                tos.e("MonitorCallbackManager", "isValidParameters MonitorItem’name is null");
                return false;
            }
        }
        return true;
    }

    public tpk b(tpk tpkVar) {
        if (tpkVar == null) {
            return null;
        }
        tos.b("MonitorCallbackManager", "getContainMonitor monitorCallbackProxy pid is:" + tpkVar.b() + ", hashcode is:" + tpkVar.a());
        for (tpk tpkVar2 : this.e.keySet()) {
            if (tpkVar2.equals(tpkVar)) {
                tos.a("MonitorCallbackManager", "getContainMonitor get the same monitorCallbackProxy");
                return tpkVar2;
            }
        }
        tos.a("MonitorCallbackManager", "getContainMonitor no same monitorCallbackProxy");
        return null;
    }

    private List<tpk> c(String str, String str2) {
        tos.a("MonitorCallbackManager", "getMonitorDataCallbackList enter");
        tos.b("MonitorCallbackManager", "getMonitorDataCallbackList deviceId: " + str + ", monitorItemType: " + str2);
        ArrayList arrayList = new ArrayList();
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            tos.e("MonitorCallbackManager", "getMonitorDataCallbackList parameters is invalid");
            return arrayList;
        }
        if (this.e.isEmpty()) {
            tos.e("MonitorCallbackManager", "getMonitorDataCallbackList deviceMonitorCallbackMap is null");
            return arrayList;
        }
        tos.b("MonitorCallbackManager", "getMonitorDataCallbackList map is：" + this.e.toString());
        for (Map.Entry<tpk, Map<String, List<String>>> entry : this.e.entrySet()) {
            Map<String, List<String>> value = entry.getValue();
            if (!value.containsKey(str)) {
                tos.a("MonitorCallbackManager", "getMonitorDataCallbackList not contains deviceId");
            } else {
                List<String> list = value.get(str);
                if (list != null && list.contains(str2)) {
                    tos.a("MonitorCallbackManager", "getMonitorDataCallbackList deviceMonitorCallbackMap hit");
                    arrayList.add(entry.getKey());
                }
            }
        }
        tos.a("MonitorCallbackManager", "getMonitorDataCallbackList monitorItemType:" + str2 + ", size: " + arrayList.size());
        return arrayList;
    }

    public boolean e(String str) {
        tos.a("MonitorCallbackManager", "isSwitchStatusOpen enter, monitorItemType: " + str);
        return MonitorItem.MONITOR_ITEM_CONNECTION.getName().equals(str) || MonitorItem.MONITOR_ITEM_POWER_MODE.getName().equals(str);
    }

    private void c(String str, List<MonitorItem> list, tpk tpkVar) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();
        ArrayList arrayList = new ArrayList();
        Iterator<MonitorItem> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getName());
        }
        concurrentHashMap.put(str, arrayList);
        this.e.put(tpkVar, concurrentHashMap);
    }

    public void a(int i, MonitorMessage monitorMessage) {
        tos.a("MonitorCallbackManager", "handleReceive enter");
        if (monitorMessage == null) {
            tos.e("MonitorCallbackManager", "handleReceive monitorMessage is null");
            return;
        }
        if (this.e.isEmpty()) {
            tos.e("MonitorCallbackManager", "handleReceive deviceMonitorCallbackMap is null");
            return;
        }
        String deviceId = monitorMessage.getDeviceId();
        String monitorItemType = monitorMessage.getMonitorItemType();
        if (TextUtils.isEmpty(deviceId) || TextUtils.isEmpty(monitorItemType)) {
            tos.e("MonitorCallbackManager", "getMonitorList parameters is invalid");
            return;
        }
        MonitorData e = e(monitorMessage);
        if (e == null) {
            tos.e("MonitorCallbackManager", "getMonitorList parameter monitorData is invalid");
            return;
        }
        List<tpk> c = c(deviceId, monitorItemType);
        if (c.isEmpty()) {
            tos.e("MonitorCallbackManager", "handleReceive monitorList is empty");
            return;
        }
        MonitorItem monitorItem = new MonitorItem();
        monitorItem.setName(monitorItemType);
        Iterator<tpk> it = c.iterator();
        while (it.hasNext()) {
            MonitorDataCallback d = it.next().d();
            if (d == null) {
                tos.d("MonitorCallbackManager", "handleReceive monitorDataCallback is null continue");
            } else {
                try {
                    d.onChanged(i, monitorItem, e);
                } catch (Exception unused) {
                    tos.d("MonitorCallbackManager", "handleReceive exception");
                }
            }
        }
    }

    public MonitorData e(MonitorMessage monitorMessage) {
        HashMap hashMap = null;
        if (monitorMessage == null) {
            return null;
        }
        HashMap<String, MonitorMessage> mapData = monitorMessage.getMapData();
        if (mapData != null) {
            hashMap = new HashMap(16);
            for (Map.Entry<String, MonitorMessage> entry : mapData.entrySet()) {
                hashMap.put(entry.getKey(), e(entry.getValue()));
            }
        }
        return new MonitorData(monitorMessage.isBooleanData(), monitorMessage.getIntData(), monitorMessage.getStringData(), hashMap);
    }

    public void d(tpk tpkVar) {
        if (tpkVar == null) {
            tos.d("MonitorCallbackManager", "removeMonitorCallback monitorInfo is null!");
            return;
        }
        this.e.remove(tpkVar);
        tos.a("MonitorCallbackManager", "removeMonitorCallback deviceMonitorCallbackMap size is:" + this.e.size());
    }

    public Map<String, List<String>> c(tpk tpkVar) {
        HashMap hashMap = new HashMap(16);
        if (tpkVar == null) {
            tos.d("MonitorCallbackManager", "getNeedCloseMonitorCallback monitorInfo is null!");
            return hashMap;
        }
        Map<String, List<String>> map = this.e.get(tpkVar);
        if (map == null) {
            tos.d("MonitorCallbackManager", "getNeedCloseMonitorCallback needCloseSwitchMap is null!");
            return Collections.emptyMap();
        }
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            List<String> value = entry.getValue();
            for (int size = value.size() - 1; size >= 0; size--) {
                if (c(entry.getKey(), value.get(size)).size() > 1) {
                    value.remove(size);
                }
            }
        }
        tos.b("MonitorCallbackManager", "getNeedCloseMonitorCallback :" + map);
        return map;
    }

    public int e() {
        return this.e.size();
    }

    public Set<String> a(String str) {
        HashSet hashSet = new HashSet();
        if (TextUtils.isEmpty(str)) {
            return hashSet;
        }
        for (Map<String, List<String>> map : this.e.values()) {
            if (map != null && map.get(str) != null) {
                hashSet.addAll(map.get(str));
            }
        }
        return hashSet;
    }

    public void c() {
        if (this.e.isEmpty()) {
            tos.e("MonitorCallbackManager", "clearDiedMonitor deviceMonitorCallbackMap is null");
            return;
        }
        Iterator<Map.Entry<tpk, Map<String, List<String>>>> it = this.e.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<tpk, Map<String, List<String>>> next = it.next();
            if (!next.getKey().d().asBinder().pingBinder()) {
                tos.b("MonitorCallbackManager", "clearDiedMonitor hit died monitor and remove");
                c(next.getKey().c(), next.getValue());
                it.remove();
            }
        }
        tos.a("MonitorCallbackManager", "clearDiedMonitor deviceMonitorCallbackMap size is:" + this.e.size());
    }

    public void b(String str) {
        if (TextUtils.isEmpty(str)) {
            tos.d("MonitorCallbackManager", "clearDiedMonitorByDevice deviceId isEmpty");
            return;
        }
        if (this.e.isEmpty()) {
            tos.d("MonitorCallbackManager", "clearDiedMonitorByDevice map isEmpty");
            return;
        }
        tos.a("MonitorCallbackManager", "clearDiedMonitorByDevice before is:" + this.e);
        Iterator<Map.Entry<tpk, Map<String, List<String>>>> it = this.e.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<tpk, Map<String, List<String>>> next = it.next();
            if (next.getValue() != null) {
                c(str, next.getValue());
                if (next.getValue().size() == 0) {
                    it.remove();
                }
            }
        }
        tos.a("MonitorCallbackManager", "clearDiedMonitorByDevice after is:" + this.e);
    }

    private void c(String str, Map<String, List<String>> map) {
        Iterator<Map.Entry<String, List<String>>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            if (str.equals(it.next().getKey())) {
                it.remove();
            }
        }
    }

    public void d(String str) {
        if (TextUtils.isEmpty(str)) {
            tos.e("MonitorCallbackManager", "clearDiedMonitorByClientName srcPkgName is null or empty");
            return;
        }
        tos.a("MonitorCallbackManager", "clearDiedMonitorByClientName start, srcPkgName is:" + str);
        if (this.e.isEmpty()) {
            tos.e("MonitorCallbackManager", "clearDiedMonitorByClientName deviceMonitorCallbackMap is null");
            return;
        }
        Iterator<Map.Entry<tpk, Map<String, List<String>>>> it = this.e.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<tpk, Map<String, List<String>>> next = it.next();
            if (str.equals(next.getKey().e())) {
                c(next.getKey().c(), next.getValue());
                it.remove();
            }
        }
        tos.a("MonitorCallbackManager", "clearDiedMonitor deviceMonitorCallbackMap size is:" + this.e.size());
    }

    private void c(Device device, Map<String, List<String>> map) {
        VirtualDevice d = toy.b().d(device);
        if (d == null) {
            tos.e("MonitorCallbackManager", "deviceAdapter is null");
            return;
        }
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            List<String> value = entry.getValue();
            for (int size = value.size() - 1; size >= 0; size--) {
                String key = entry.getKey();
                String str = value.get(size);
                if (c(key, str).size() <= 1) {
                    SwitchStatusCallback b = b();
                    if (!e(str)) {
                        d.switchMonitorStatus(2, key, str, b);
                    }
                }
            }
        }
    }

    private SwitchStatusCallback b() {
        return new SwitchStatusCallback.Stub() { // from class: com.huawei.wearengine.core.device.MonitorCallbackManager$1
            @Override // com.huawei.wearengine.monitor.SwitchStatusCallback
            public void onResult(int i) {
                tos.b("MonitorCallbackManager", "getMonitorQueryCallback result:" + i);
            }
        };
    }
}
