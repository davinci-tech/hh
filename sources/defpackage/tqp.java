package defpackage;

import android.os.Process;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.phdkit.DataReceiveListener;
import com.huawei.phdkit.DeviceData;
import com.huawei.phdkit.DiscoveryListener;
import com.huawei.phdkit.DvLiteDevice;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.monitor.MonitorData;
import com.huawei.wearengine.monitor.MonitorDataCallback;
import com.huawei.wearengine.monitor.MonitorItem;
import com.huawei.wearengine.sensor.AsyncReadCallback;
import com.huawei.wearengine.sensor.AsyncStopCallback;
import com.huawei.wearengine.sensor.DataResult;
import com.huawei.wearengine.sensor.Sensor;
import defpackage.mbg;
import defpackage.tos;
import defpackage.tqp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes9.dex */
public class tqp {
    private final toz n;
    private static final Object c = new Object();
    private static final Object d = new Object();
    private static final String[] j = {Sensor.NAME_ACC, Sensor.NAME_PPG, Sensor.NAME_ECG, Sensor.NAME_HR, Sensor.NAME_GYRO, Sensor.NAME_MAG};
    private static final String[] e = {Sensor.NAME_ACC, Sensor.NAME_PPG, Sensor.NAME_ECG};
    private static final String[] b = {Sensor.NAME_PPG, Sensor.NAME_ECG, Sensor.NAME_HR};

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f17350a = {Sensor.NAME_ACC, Sensor.NAME_GYRO, Sensor.NAME_MAG};
    private Map<String, List<Sensor>> l = new ConcurrentHashMap();
    private Map<String, List<Sensor>> k = new ConcurrentHashMap();
    private Map<String, AsyncReadCallback> f = new ConcurrentHashMap();
    private Map<String, tqr> h = new ConcurrentHashMap();
    private AtomicInteger p = new AtomicInteger(20000);
    private Map<Integer, AsyncReadCallback> g = new ConcurrentHashMap();
    private Map<Integer, AsyncStopCallback> m = new ConcurrentHashMap();
    private tou i = new tou(0);
    private ConcurrentHashMap<String, MonitorDataCallback> o = new ConcurrentHashMap<>(16);
    private mbk t = mbk.d(tot.a());

    public static int a(int i) {
        switch (i) {
            case 1:
                return 6;
            case 2:
            default:
                return -1;
            case 3:
                return 2;
            case 4:
                return 3;
            case 5:
                return 5;
            case 6:
                return 1;
            case 7:
                return 0;
            case 8:
                return 4;
        }
    }

    public static int b(int i) {
        int i2 = 2;
        if (i != 2 && i != 3) {
            i2 = 4;
            if (i != 4 && i != 5 && i != 6) {
                return 0;
            }
        }
        return i2;
    }

    private int c(int i) {
        if (i == 2) {
            return 3;
        }
        if (i != 3) {
            return i;
        }
        return 2;
    }

    private int d(int i) {
        int i2 = 13;
        if (i != 13) {
            i2 = 300;
            if (i != 300) {
                switch (i) {
                    case 0:
                    case 1:
                    case 3:
                        return 0;
                    case 2:
                        break;
                    case 4:
                        return 301;
                    case 5:
                        return 302;
                    case 6:
                        return 303;
                    default:
                        return 12;
                }
            }
        }
        return i2;
    }

    public static int e(int i) {
        if (i == 0) {
            return 3;
        }
        if (i != 1) {
            return i != 2 ? 0 : 7;
        }
        return 5;
    }

    public tqp(toz tozVar) {
        this.n = tozVar;
    }

    public List<Sensor> e(Device device) {
        List<Sensor> b2;
        tos.a("SensorCoreManager", "synchronizeGetSensors");
        if (device == null) {
            tos.e("SensorCoreManager", "synchronizeGetSensors parameters is invalid");
            throw new IllegalStateException(String.valueOf(5));
        }
        synchronized (c) {
            b2 = b(device);
            this.l.put(device.getUuid(), b2);
            synchronized (d) {
                this.k.put(device.getUuid(), b2);
            }
        }
        return b2;
    }

    public List<Sensor> c(List<Sensor> list, Device device) {
        List<String> asList;
        if (list == null) {
            tos.e("SensorCoreManager", "filterSensorList sensorList == null ");
            throw new IllegalStateException(String.valueOf(12));
        }
        if (device == null) {
            tos.e("SensorCoreManager", "filterSensorList device == null ");
            throw new IllegalStateException(String.valueOf(12));
        }
        if (device.getModel() == null) {
            tos.e("SensorCoreManager", "model == null ");
            throw new IllegalStateException(String.valueOf(12));
        }
        if (device.getModel().contains("Vidar") || device.getModel().contains("Kanon")) {
            asList = Arrays.asList(e);
        } else {
            asList = Arrays.asList(j);
        }
        return d(list, asList);
    }

    public List<Sensor> c(List<Sensor> list) {
        return d(list, Arrays.asList(b));
    }

    public List<Sensor> e(List<Sensor> list) {
        return d(list, Arrays.asList(f17350a));
    }

    public int b(Device device, Sensor sensor, AsyncReadCallback asyncReadCallback, String str) {
        if (device == null || asyncReadCallback == null || str == null) {
            tos.e("SensorCoreManager", "doAsyncReadSensor parameters is invalid");
            throw new IllegalStateException(String.valueOf(5));
        }
        c(device.getUuid(), sensor, asyncReadCallback, str);
        int b2 = b();
        this.g.put(Integer.valueOf(b2), asyncReadCallback);
        tos.a("SensorCoreManager", "doAsyncReadSensor controlSensor ENABLE_SENSOR");
        c(device.getUuid(), sensor, b2, "ENABLE");
        a(device);
        return 0;
    }

    private void a(final Device device) {
        if (this.o.containsKey(device.getUuid())) {
            return;
        }
        final int myPid = Process.myPid();
        MonitorDataCallback.Stub stub = new MonitorDataCallback.Stub() { // from class: com.huawei.wearengine.sensor.SensorCoreManager$1
            @Override // com.huawei.wearengine.monitor.MonitorDataCallback
            public void onChanged(int i, MonitorItem monitorItem, MonitorData monitorData) {
                if (monitorItem == null || monitorData == null) {
                    tos.d("SensorCoreManager", "registerConnectMonitor monitorDataCallback");
                    return;
                }
                if (i == 0 && MonitorItem.MONITOR_ITEM_CONNECTION.getName().equals(monitorItem.getName()) && monitorData.asInt() == 3) {
                    tqp.this.d(device.getUuid());
                    tqp.this.e(myPid, device.getUuid());
                    tos.a("SensorCoreManager", "registerConnectMonitor monitorDataCallback onChanged errorCode:" + i + " pid:" + myPid);
                }
            }
        };
        this.o.put(device.getUuid(), stub);
        tos.a("SensorCoreManager", "registerConnectMonitor result:" + this.n.d(device, new ArrayList(Collections.singleton(MonitorItem.MONITOR_ITEM_CONNECTION)), new tpk(myPid, System.identityHashCode(stub), stub, device, "com.huawei.wearengine.internal")));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        boolean z = false;
        for (Map.Entry<String, tqr> entry : this.h.entrySet()) {
            if (entry.getKey().startsWith(str)) {
                this.h.remove(entry.getKey());
                z = true;
            }
        }
        if (z) {
            return;
        }
        for (Map.Entry<String, AsyncReadCallback> entry2 : this.f.entrySet()) {
            if (entry2.getKey().startsWith(str)) {
                this.f.remove(entry2.getKey());
            }
        }
    }

    private boolean g(String str) {
        Iterator<Map.Entry<String, tqr>> it = this.h.entrySet().iterator();
        while (it.hasNext()) {
            if (it.next().getKey().startsWith(str)) {
                return true;
            }
        }
        Iterator<Map.Entry<String, AsyncReadCallback>> it2 = this.f.entrySet().iterator();
        while (it2.hasNext()) {
            if (it2.next().getKey().startsWith(str)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, String str) {
        MonitorDataCallback monitorDataCallback = this.o.get(str);
        tpk b2 = this.n.f().b(new tpk(i, System.identityHashCode(monitorDataCallback), monitorDataCallback, null, null));
        this.o.remove(str);
        try {
            if (b2 == null) {
                tos.e("SensorCoreManager", "unregisterListener map do not have the listener");
                return;
            }
            tos.a("SensorCoreManager", "unRegisterConnectMonitor result:" + this.n.e(b2));
        } catch (IllegalStateException unused) {
            tos.e("SensorCoreManager", "unregisterListener illegalStateException");
        }
    }

    private boolean a(Sensor sensor) {
        if (sensor == null || sensor.getSupportFrequencyList() == null || sensor.getSupportFrequencyList().isEmpty()) {
            return false;
        }
        int type = sensor.getType();
        return type == 2 || type == 3 || type == 4;
    }

    public int b(Device device, Sensor sensor, AsyncStopCallback asyncStopCallback, String str) throws RemoteException {
        if (device == null || sensor == null || asyncStopCallback == null || str == null) {
            tos.e("SensorCoreManager", "doStopAsyncReadSensor parameters is invalid");
            throw new IllegalStateException(String.valueOf(5));
        }
        c(device.getUuid(), sensor, str);
        if (a(sensor)) {
            tqr tqrVar = this.h.get(d(device.getUuid(), sensor.getId()));
            if (tqrVar != null && tqrVar.e() > 0) {
                tos.a("SensorCoreManager", "stopAsyncRead asyncReadCallbacks not null, callback asyncStopCallback myself");
                asyncStopCallback.onStopResult(0);
                return 0;
            }
        } else if (a(device.getUuid(), sensor.getId()).size() > 0) {
            tos.a("SensorCoreManager", "stopAsyncRead asyncReadCallbacks not null, callback asyncStopCallback myself");
            asyncStopCallback.onStopResult(0);
            return 0;
        }
        int b2 = b();
        this.m.put(Integer.valueOf(b2), asyncStopCallback);
        c(device.getUuid(), sensor, b2, "DISABLE");
        if (!g(device.getUuid())) {
            e(Process.myPid(), device.getUuid());
        }
        return 0;
    }

    public void a(String str) {
        if (str == null) {
            tos.e("SensorCoreManager", "clean parameters is invalid");
            throw new IllegalStateException(String.valueOf(5));
        }
        i(str);
    }

    public void b(String str) {
        if (TextUtils.isEmpty(str)) {
            tos.e("SensorCoreManager", "cleanByDevice deviceId isEmpty");
        } else {
            j(str);
        }
    }

    private void i(String str) {
        for (Map.Entry<String, List<Sensor>> entry : this.l.entrySet()) {
            List<Sensor> value = entry.getValue();
            if (value != null) {
                String key = entry.getKey();
                for (Sensor sensor : value) {
                    if (a(sensor)) {
                        d(key, sensor, str);
                    } else {
                        e(str, key, sensor);
                    }
                }
            }
        }
    }

    private void j(String str) {
        List<Sensor> list = this.l.get(str);
        if (list == null || list.isEmpty()) {
            return;
        }
        for (Sensor sensor : list) {
            if (a(sensor)) {
                d(str, sensor, "");
            } else {
                e("", str, sensor);
            }
        }
    }

    private void e(String str, String str2, Sensor sensor) {
        if (a(str2, sensor.getId()).size() == 0) {
            tos.b("SensorCoreManager", "stopSensor other app used， no need stop");
            return;
        }
        c(str2, sensor, str);
        if (a(str2, sensor.getId()).size() > 0) {
            tos.b("SensorCoreManager", "stopSensor other app used， no need stop");
            return;
        }
        tos.b("SensorCoreManager", "stopSensors turely stop deviceId: " + str2 + " sensorName: " + sensor.getName());
        c(str2, sensor, b(), "DISABLE");
    }

    private void d(String str, Sensor sensor, String str2) {
        tqr tqrVar = this.h.get(d(str, sensor.getId()));
        if (tqrVar == null || tqrVar.e() == 0) {
            tos.b("SensorCoreManager", "stopMultiFreSensor other app used， no need stop");
            return;
        }
        c(str, sensor, str2);
        if (tqrVar.e() > 0) {
            tos.b("SensorCoreManager", "stopMultiFreSensor other app used， no need stop");
            return;
        }
        tos.b("SensorCoreManager", "stopMultiFreSensor stop deviceId: " + str + " sensorName: " + sensor.getName());
        c(str, sensor, b(), "DISABLE");
    }

    private List<Sensor> d(List<Sensor> list, List<String> list2) {
        ArrayList arrayList = new ArrayList();
        if (list != null && list.size() > 0) {
            for (Sensor sensor : list) {
                if (list2.contains(sensor.getName())) {
                    arrayList.add(sensor);
                }
            }
        }
        return arrayList;
    }

    private void c(String str, Sensor sensor, int i, String str2) {
        tos.a("SensorCoreManager", "controlSensor: " + sensor.getName() + ", seq = " + i + ", cmd = " + str2);
        StringBuilder sb = new StringBuilder("controlSensor: ");
        sb.append(str2);
        tos.b("SensorCoreManager", sb.toString());
        this.t.b(str, mbq.b(sensor, i, str2));
    }

    private List<AsyncReadCallback> a(String str, int i) {
        LinkedList linkedList = new LinkedList();
        for (Map.Entry<String, AsyncReadCallback> entry : this.f.entrySet()) {
            if (entry.getKey().startsWith(str + Constants.LINK + i)) {
                linkedList.add(entry.getValue());
            }
        }
        return linkedList;
    }

    private void c(String str, Sensor sensor, AsyncReadCallback asyncReadCallback, String str2) {
        tos.a("SensorCoreManager", "registerAsyncReadCallback");
        tos.b("SensorCoreManager", "registerAsyncReadCallback deviceId " + str + " sensorId " + sensor.getId());
        if (!a(sensor)) {
            this.f.put(c(str, sensor.getId(), str2), asyncReadCallback);
        } else {
            a(str, sensor, asyncReadCallback, str2);
        }
    }

    private void a(String str, Sensor sensor, AsyncReadCallback asyncReadCallback, String str2) {
        tqr tqrVar;
        tos.a("SensorCoreManager", "enter registerMultiFreReadCallback");
        String d2 = d(str, sensor.getId());
        int a2 = tsg.a(sensor.getExtendJson());
        tqr e2 = e(str, sensor.getType());
        tqq e3 = e(sensor, a2);
        if (this.h.get(d2) != null) {
            tqrVar = this.h.get(d2);
            if (e3 != null && tqrVar.a() > e3.d()) {
                tqrVar.c(e3);
                tos.a("SensorCoreManager", "update asyncReadGroup Frequency");
            }
        } else {
            if (e2 != null && e2.a() == 1) {
                tos.a("SensorCoreManager", "change Frequency to high");
                a2 = 1;
            }
            tqr tqrVar2 = new tqr(d2, a2);
            this.h.put(d2, tqrVar2);
            tos.a("SensorCoreManager", "add new asyncReadGroup");
            tqrVar = tqrVar2;
        }
        if (tsg.a(sensor.getExtendJson()) == 1) {
            tqrVar.b(true);
        }
        if (e3 != null && e2 != null && e2.a() > e3.d()) {
            e2.c(e3);
            tos.a("SensorCoreManager", "update otherReadGroup Frequency");
        }
        tqrVar.b(str2, sensor, asyncReadCallback);
    }

    private tqr e(String str, int i) {
        Sensor sensor;
        if (i != 2 && i != 3) {
            return null;
        }
        List<Sensor> list = this.l.get(str);
        int c2 = c(i);
        Iterator<Sensor> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                sensor = null;
                break;
            }
            sensor = it.next();
            if (sensor.getType() == c2) {
                break;
            }
        }
        if (sensor == null) {
            return null;
        }
        return this.h.get(d(str, sensor.getId()));
    }

    private tqq e(Sensor sensor, int i) {
        if (sensor == null) {
            return null;
        }
        for (tqq tqqVar : sensor.getSupportFrequencyList()) {
            if (tqqVar.d() == i) {
                return tqqVar;
            }
        }
        return null;
    }

    private String d(String str, int i) {
        return str + Constants.LINK + i;
    }

    private String c(String str, int i, String str2) {
        return d(str, i) + Constants.LINK + str2;
    }

    private void c(String str, Sensor sensor, String str2) {
        tos.a("SensorCoreManager", "unRegisterAsyncReadCallback");
        tos.b("SensorCoreManager", "unRegisterAsyncReadCallback deviceId " + str + " sensorId " + sensor.getId() + " packageName " + str2);
        if (a(sensor)) {
            String d2 = d(str, sensor.getId());
            if (TextUtils.isEmpty(str2)) {
                this.h.remove(d2);
            }
            tqr tqrVar = this.h.get(d2);
            if (tqrVar != null) {
                tqrVar.d(str2);
                if (tqrVar.e() == 0) {
                    this.h.remove(d2);
                    tos.a("SensorCoreManager", "remove groupKey");
                    if (tsg.a(sensor.getExtendJson()) == 1) {
                        a(str, sensor);
                        return;
                    }
                    return;
                }
                return;
            }
            return;
        }
        b(str, sensor, str2);
    }

    private void b(String str, Sensor sensor, String str2) {
        String c2 = c(str, sensor.getId(), str2);
        Iterator<Map.Entry<String, AsyncReadCallback>> it = this.f.entrySet().iterator();
        while (it.hasNext()) {
            String key = it.next().getKey();
            if (!TextUtils.isEmpty(str2)) {
                if (key.equals(c2)) {
                    it.remove();
                }
            } else if (key.startsWith(c2)) {
                it.remove();
            }
        }
    }

    private void a(String str, Sensor sensor) {
        tqr e2 = e(str, sensor.getType());
        tqq e3 = e(sensor, 2);
        if (e2 == null || e2.c() || !e2.a(e3)) {
            return;
        }
        e2.c(e3);
        tos.a("SensorCoreManager", "downFrequency asyncReadGroup Frequency");
    }

    private List<Sensor> b(Device device) {
        this.l.remove(device.getUuid());
        this.l.put(device.getUuid(), new ArrayList());
        this.i = new tou(1);
        h(device.getUuid());
        this.t.b(c(device).getUdid(), mbq.d());
        try {
            if (!this.i.e(OpAnalyticsConstants.H5_LOADING_DELAY, TimeUnit.MILLISECONDS)) {
                tos.e("SensorCoreManager", "getSensorListByDevice asyncToSyncListener timeout");
                throw new IllegalStateException(String.valueOf(12));
            }
            return this.l.get(device.getUuid());
        } catch (InterruptedException unused) {
            tos.e("SensorCoreManager", "asynToSyncListener await error");
            throw new IllegalStateException(String.valueOf(12));
        }
    }

    private void h(String str) {
        tos.a("SensorCoreManager", "getDataReceiveListener enter");
        if (this.t.c().get(str) != null) {
            tos.a("SensorCoreManager", "getDataReceiveListener deviceDataListener is not null");
            return;
        }
        DataReceiveListener e2 = e(str);
        this.t.a(str, e2);
        this.t.c().put(str, e2);
    }

    private DataReceiveListener e(final String str) {
        return new DataReceiveListener.Stub() { // from class: com.huawei.wearengine.sensor.SensorCoreManager$2
            ConcurrentHashMap<String, Long> lastSendHeartTimeMap = new ConcurrentHashMap<>(16);

            @Override // com.huawei.phdkit.DataReceiveListener
            public void onDataChanged(DeviceData deviceData) throws RemoteException {
                Map map;
                if (deviceData == null) {
                    tos.e("SensorCoreManager", "onDataChanged deviceData is null");
                    return;
                }
                tos.b("SensorCoreManager", "onDataChanged " + Arrays.toString(deviceData.getDataContents()));
                tos.b("SensorCoreManager", "onDataChanged " + mbg.c(deviceData.getDataContents()));
                int linkType = deviceData.getLinkType();
                if (linkType == 1) {
                    tos.a("SensorCoreManager", "getSensorList onDataChanged LINK_TYPE_DATA");
                    tqp.this.e(str, (ConcurrentHashMap<String, Long>) this.lastSendHeartTimeMap, deviceData);
                } else {
                    if (linkType == 2) {
                        tos.a("SensorCoreManager", "getSensorList onDataChanged LINK_TYPE_CMD");
                        tqp tqpVar = tqp.this;
                        byte[] dataContents = deviceData.getDataContents();
                        map = tqp.this.l;
                        tqpVar.c(dataContents, (List<Sensor>) map.get(str));
                        return;
                    }
                    tos.a("SensorCoreManager", "handleDataChanged default");
                }
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str, ConcurrentHashMap<String, Long> concurrentHashMap, DeviceData deviceData) throws RemoteException {
        boolean d2;
        String str2 = str + Constants.LINK + ((int) deviceData.getDataContents()[0]);
        long longValue = concurrentHashMap.get(str2) == null ? 0L : concurrentHashMap.get(str2).longValue();
        if (deviceData.getDataContents()[0] == 126) {
            d2 = d(deviceData, str);
        } else {
            d2 = d(str, deviceData.getDataContents());
        }
        if (!d2 || System.currentTimeMillis() - longValue < 1000) {
            return;
        }
        this.t.d(str, mbq.a(deviceData.getDataContents()[0]));
        concurrentHashMap.put(str2, Long.valueOf(System.currentTimeMillis()));
    }

    private boolean d(DeviceData deviceData, String str) throws RemoteException {
        String c2;
        tos.a("SensorCoreManager", "handlerAccGyro enter");
        try {
            c2 = mbg.c(mbq.b(deviceData.getDataContents()));
        } catch (StringIndexOutOfBoundsException unused) {
            tos.e("SensorCoreManager", "handleCmd default");
        }
        if (TextUtils.isEmpty(c2)) {
            tos.d("SensorCoreManager", "agStr is null");
            return false;
        }
        String[] split = c2.split("3B3B");
        tos.b("SensorCoreManager", "valueArr length: " + split.length + "; agStr = " + c2);
        if (split.length == 2) {
            return d(str, mbg.a(split[0])) && d(str, mbg.a(split[1]));
        }
        tos.e("SensorCoreManager", "valueArr is Empty");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(byte[] bArr, List<Sensor> list) throws RemoteException {
        if (bArr == null) {
            return;
        }
        tos.b("SensorCoreManager", "handleCmd dataContents " + Arrays.toString(bArr));
        tos.b("SensorCoreManager", "handleCmd dataContents " + mbg.c(bArr));
        int a2 = mbq.a(bArr);
        if (a2 == 1) {
            tos.a("SensorCoreManager", "handleCmd SENSOR_SYNC");
            mbq.e(bArr, list, this.i);
        } else if (a2 == 2) {
            tos.a("SensorCoreManager", "handleCmd SENSOR_ENABLE");
            mbq.a(bArr, this.g, this.m);
        } else {
            tos.b("SensorCoreManager", "handleCmd default");
        }
    }

    private boolean d(String str, byte[] bArr) {
        tos.a("SensorCoreManager", "doAsyncReadCallback");
        if (bArr == null || bArr.length < 1) {
            tos.a("SensorCoreManager", "doAsyncReadCallback value is wrong data");
            return false;
        }
        byte b2 = bArr[0];
        Sensor b3 = b(str, b2);
        if (b3 == null) {
            tos.a("SensorCoreManager", "doAsyncReadCallback can not find sensor!");
            return false;
        }
        DataResult dataResult = new DataResult();
        int b4 = b(b3, bArr, dataResult);
        if (a(b3)) {
            tqr tqrVar = this.h.get(d(str, b3.getId()));
            if (tqrVar == null) {
                tos.a("SensorCoreManager", "asyncReadGroup has no sensor to callback");
                return false;
            }
            tos.a("SensorCoreManager", "doAsyncReadCallback multiFre => type: " + b3.getType());
            c(b4, dataResult);
            tqrVar.a(b4, dataResult);
        } else {
            List<AsyncReadCallback> a2 = a(str, b2);
            if (a2.isEmpty()) {
                tos.a("SensorCoreManager", "doAsyncReadCallback has no sensor to callback");
                return false;
            }
            for (AsyncReadCallback asyncReadCallback : a2) {
                try {
                    tos.a("SensorCoreManager", "doAsyncReadCallback => type: " + b3.getType());
                    c(b4, dataResult);
                    asyncReadCallback.onReadResult(d(b4), dataResult);
                } catch (Exception unused) {
                    tos.a("SensorCoreManager", "doAsyncReadCallback onReadResult Exception");
                }
            }
        }
        tos.a("SensorCoreManager", "doAsyncReadCallback has callback");
        if (b4 != 13) {
            return true;
        }
        c(str, b3, b(), "DISABLE");
        tos.e("SensorCoreManager", "Device version is not supported");
        return false;
    }

    private void c(int i, DataResult dataResult) {
        if (dataResult != null) {
            StringBuilder sb = new StringBuilder("errorCode:[");
            sb.append(i);
            sb.append("]");
            if (dataResult.getSensor() != null) {
                sb.append("name:[");
                sb.append(dataResult.getSensor().getName());
                sb.append("]sensorId:[");
                sb.append(dataResult.getSensor().getId());
                sb.append("]sensorType:[");
                sb.append(dataResult.getSensor().getType());
                sb.append("]accuracy:[");
                sb.append(dataResult.getSensor().getAccuracy());
                sb.append("]resolution:[");
                sb.append(dataResult.getSensor().getResolution());
                sb.append("]");
            }
            sb.append("get value:[");
            sb.append(Arrays.toString(dataResult.asFloats()));
            sb.append("]value size:[");
            sb.append(dataResult.asFloats().length);
            sb.append("]Timestamp:[");
            sb.append(dataResult.getTimestamp());
            sb.append("]");
            tos.a("SensorCoreManager", "doAsyncReadCallback => result: " + sb.toString());
        }
    }

    private int b(Sensor sensor, byte[] bArr, DataResult dataResult) {
        tos.a("SensorCoreManager", "buildSensorData");
        dataResult.setSensor(sensor);
        switch (sensor.getType()) {
            case 0:
            case 1:
                tos.a("SensorCoreManager", "buildSensorData for multi channel sensor");
                return mbq.e(sensor.getType(), mbq.b(bArr), dataResult);
            case 2:
            case 3:
            case 4:
            case 5:
                tos.a("SensorCoreManager", "buildSensorData for none channel sensor");
                return mbq.c(sensor.getType(), sensor.isSupportUtc(), mbq.b(bArr), dataResult);
            case 6:
                tos.a("SensorCoreManager", "buildSensorData for Heart Rate sensor");
                return mbq.b(sensor.getType(), mbq.b(bArr), dataResult);
            default:
                dataResult.setValues(mbq.e(4, bArr, false));
                return 0;
        }
    }

    private Sensor b(String str, int i) {
        List<Sensor> list;
        tos.a("SensorCoreManager", "getSensorById sensorId :" + i);
        synchronized (d) {
            list = this.k.get(str);
        }
        tos.a("SensorCoreManager", "getSensorById after");
        if (list == null) {
            return null;
        }
        for (Sensor sensor : list) {
            if (sensor.getId() == i) {
                return sensor;
            }
        }
        return null;
    }

    private DvLiteDevice c(final Device device) {
        final DvLiteDevice[] dvLiteDeviceArr = new DvLiteDevice[1];
        final tou touVar = new tou(1);
        this.t.b(0, new DiscoveryListener.Stub() { // from class: com.huawei.wearengine.sensor.SensorCoreManager$3
            @Override // com.huawei.phdkit.DiscoveryListener
            public void onDeviceFound(List<DvLiteDevice> list) throws RemoteException {
                tos.a("SensorCoreManager", "getSensorList onDeviceFound");
                if (list != null) {
                    tqp.this.b(device, dvLiteDeviceArr, touVar, (List<DvLiteDevice>) list);
                } else {
                    tos.e("SensorCoreManager", "getSensorList onDeviceFound devices is null");
                }
            }
        });
        try {
            if (touVar.e(OpAnalyticsConstants.H5_LOADING_DELAY, TimeUnit.MILLISECONDS)) {
                return dvLiteDeviceArr[0];
            }
            tos.e("SensorCoreManager", "getDvLiteDevice asyncToSyncListener timeout");
            throw new IllegalStateException(String.valueOf(12));
        } catch (InterruptedException unused) {
            tos.e("SensorCoreManager", "asynToSyncListener await error");
            throw new IllegalStateException(String.valueOf(12));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Device device, DvLiteDevice[] dvLiteDeviceArr, tou touVar, List<DvLiteDevice> list) {
        int i = 0;
        while (true) {
            if (i >= list.size()) {
                break;
            }
            if (device.getUuid().equals(list.get(i).getUdid())) {
                tos.b("SensorCoreManager", "handleDeviceFound got the device " + device.getUuid());
                dvLiteDeviceArr[0] = list.get(i);
                break;
            }
            i++;
        }
        touVar.onFinish();
    }

    private int b() {
        if (this.p.get() >= 30000) {
            this.p.set(20000);
        }
        return this.p.getAndIncrement();
    }

    public boolean c(String str) {
        return this.t.c().get(str) == null;
    }
}
