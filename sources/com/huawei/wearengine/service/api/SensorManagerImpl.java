package com.huawei.wearengine.service.api;

import android.content.Context;
import android.os.Binder;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.wearengine.SensorManager;
import com.huawei.wearengine.auth.Permission;
import com.huawei.wearengine.common.WearEngineBiOperate;
import com.huawei.wearengine.core.common.ClientBinderDied;
import com.huawei.wearengine.core.device.PowerModeChangeManager;
import com.huawei.wearengine.device.Device;
import com.huawei.wearengine.sensor.AsyncReadCallback;
import com.huawei.wearengine.sensor.AsyncStopCallback;
import com.huawei.wearengine.sensor.Sensor;
import com.huawei.wearengine.utils.DeviceProcessor;
import defpackage.toh;
import defpackage.tom;
import defpackage.top;
import defpackage.tor;
import defpackage.tos;
import defpackage.tot;
import defpackage.toz;
import defpackage.tqo;
import defpackage.tqp;
import defpackage.tri;
import defpackage.trj;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class SensorManagerImpl extends SensorManager.Stub implements ClientBinderDied, PowerModeChangeManager.HandlePowerModeChange {
    private static ArrayList<String> d;

    /* renamed from: a, reason: collision with root package name */
    private toz f11247a;
    private tor b;
    private toh c;
    private tqp e;

    static {
        ArrayList<String> arrayList = new ArrayList<>();
        d = arrayList;
        arrayList.add(Sensor.NAME_HR);
        d.add(Sensor.NAME_ECG);
        d.add(Sensor.NAME_PPG);
    }

    public SensorManagerImpl(toh tohVar, tor torVar) {
        this.f11247a = new toz(torVar);
        this.c = tohVar;
        this.b = torVar;
        this.e = new tqp(this.f11247a);
    }

    @Override // com.huawei.wearengine.SensorManager
    public List<Sensor> getSensorList(Device device) throws RemoteException {
        List<Sensor> c;
        tos.a("SensorManagerImpl", "getSensorList enter");
        tos.b("SensorManagerImpl", "getSensorList pid " + Binder.getCallingPid());
        tom.e(device, "device must not be null!");
        tom.e(device.getUuid(), "device must not be null!");
        WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
        wearEngineBiOperate.init();
        Context a2 = tot.a();
        String c2 = tri.c(Binder.getCallingUid(), a2, this.b.getApplicationIdByPid(Integer.valueOf(Binder.getCallingPid())));
        try {
            PowerModeChangeManager.a().b(true);
            if (tri.d(c2) <= 5) {
                this.c.a(c2, "getSensorList", tqo.e, Permission.SENSOR);
                wearEngineBiOperate.biApiCalling(a2, c2, "getSensorList", String.valueOf(0));
                c = this.e.c(a(device, c2), device);
            } else {
                this.c.a("getSensorList");
                this.c.c("getSensorList");
                List<Sensor> d2 = this.c.d(c2, "getSensorList", a(device, c2), this.e);
                wearEngineBiOperate.biApiCalling(a2, c2, "getSensorList", String.valueOf(0));
                c = this.e.c(d2, device);
            }
            c(c, c2);
            return c;
        } catch (IllegalStateException e) {
            wearEngineBiOperate.biApiCalling(a2, c2, "getSensorList", String.valueOf(trj.b(e)));
            throw e;
        }
    }

    private void c(List<Sensor> list, String str) {
        boolean c = toh.c(tot.d(), str);
        for (Sensor sensor : list) {
            if (!c && sensor != null && !TextUtils.isEmpty(sensor.getExtendJson()) && sensor.getExtendJson().contains("SensorChipType")) {
                try {
                    JSONObject jSONObject = new JSONObject(sensor.getExtendJson());
                    jSONObject.remove("SensorChipType");
                    sensor.setExtendJson(jSONObject.toString());
                } catch (JSONException unused) {
                    tos.e("SensorManagerImpl", "deleteChipType JSONException");
                }
            }
        }
    }

    private List<Sensor> a(Device device, String str) throws RemoteException {
        Device processInputDevice = DeviceProcessor.processInputDevice(str, device);
        if (!this.f11247a.c(processInputDevice.getUuid())) {
            tos.e("SensorManagerImpl", "getSensorList device is invalid");
            throw new IllegalStateException(String.valueOf(16));
        }
        return this.e.e(processInputDevice);
    }

    @Override // com.huawei.wearengine.SensorManager
    public int asyncRead(Device device, Sensor sensor, AsyncReadCallback asyncReadCallback) throws RemoteException {
        tos.a("SensorManagerImpl", "asyncRead enter");
        tom.e(device, "device must not be null!");
        tom.e(device.getUuid(), "device must not be null!");
        tom.e(sensor, "sensor must not be null!");
        tom.e(asyncReadCallback, "asyncReadCallback must not be null!");
        WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
        wearEngineBiOperate.init();
        Context a2 = tot.a();
        String c = tri.c(Binder.getCallingUid(), a2, this.b.getApplicationIdByPid(Integer.valueOf(Binder.getCallingPid())));
        try {
            PowerModeChangeManager.a().b(true);
            Permission c2 = c(c, sensor);
            this.c.a(c, "asyncRead", a(c2), c2);
            Device processInputDevice = DeviceProcessor.processInputDevice(c, device);
            if (!this.f11247a.c(processInputDevice.getUuid())) {
                tos.e("SensorManagerImpl", "asyncRead device is invalid");
                throw new IllegalStateException(String.valueOf(16));
            }
            if (this.e.c(processInputDevice.getUuid())) {
                tos.e("SensorManagerImpl", "asyncRead deviceDataListener is null");
                this.e.e(processInputDevice);
            }
            this.e.b(processInputDevice, sensor, asyncReadCallback, c);
            wearEngineBiOperate.biApiCalling(a2, c, "asyncRead", String.valueOf(0));
            return 0;
        } catch (IllegalStateException e) {
            tos.e("SensorManagerImpl", "asyncRead illegalStateException");
            wearEngineBiOperate.biApiCalling(a2, c, "asyncRead", String.valueOf(trj.b(e)));
            throw e;
        }
    }

    @Override // com.huawei.wearengine.SensorManager
    public int asyncReadSensors(Device device, List<Sensor> list, AsyncReadCallback asyncReadCallback) throws RemoteException {
        tos.a("SensorManagerImpl", "asyncReadSensors enter");
        tos.b("SensorManagerImpl", "asyncReadSensors asyncReadCallback" + asyncReadCallback);
        tom.e(device, "device must not be null!");
        tom.e(device.getUuid(), "device must not be null!");
        tom.e(list, "sensor must not be null!");
        tom.e(asyncReadCallback, "asyncReadCallback must not be null!");
        WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
        wearEngineBiOperate.init();
        Context a2 = tot.a();
        String c = tri.c(Binder.getCallingUid(), a2, this.b.getApplicationIdByPid(Integer.valueOf(Binder.getCallingPid())));
        try {
            PowerModeChangeManager.a().b(true);
            for (Permission permission : a(c, list)) {
                this.c.a(c, "asyncReadSensors", a(permission), permission);
            }
            Device processInputDevice = DeviceProcessor.processInputDevice(c, device);
            if (!this.f11247a.c(processInputDevice.getUuid())) {
                tos.e("SensorManagerImpl", "asyncReadSensors device is invalid");
                throw new IllegalStateException(String.valueOf(16));
            }
            if (this.e.c(processInputDevice.getUuid())) {
                tos.e("SensorManagerImpl", "asyncReadSensors deviceDataListener is null");
                this.e.e(processInputDevice);
            }
            List<Sensor> e = e(list);
            if (e.size() == 0) {
                tos.e("SensorManagerImpl", "asyncReadSensors sensors invalid argument");
                throw new IllegalStateException(String.valueOf(5));
            }
            Iterator<Sensor> it = e.iterator();
            while (it.hasNext()) {
                this.e.b(processInputDevice, it.next(), asyncReadCallback, c);
            }
            wearEngineBiOperate.biApiCalling(a2, c, "asyncReadSensors", String.valueOf(0));
            return 0;
        } catch (IllegalStateException e2) {
            tos.e("SensorManagerImpl", "asyncReadSensors illegalStateException");
            wearEngineBiOperate.biApiCalling(a2, c, "asyncReadSensors", String.valueOf(trj.b(e2)));
            throw e2;
        }
    }

    @Override // com.huawei.wearengine.SensorManager
    public int stopAsyncRead(Device device, Sensor sensor, AsyncStopCallback asyncStopCallback) throws RemoteException {
        tos.a("SensorManagerImpl", "stopAsyncRead enter");
        tom.e(device, "device must not be null!");
        tom.e(device.getUuid(), "device must not be null!");
        tom.e(sensor, "sensor must not be null!");
        tom.e(asyncStopCallback, "asyncStopCallback must not be null!");
        WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
        wearEngineBiOperate.init();
        Context a2 = tot.a();
        String c = tri.c(Binder.getCallingUid(), a2, this.b.getApplicationIdByPid(Integer.valueOf(Binder.getCallingPid())));
        try {
            PowerModeChangeManager.a().b(true);
            Permission c2 = c(c, sensor);
            this.c.a(c, "stopAsyncRead", a(c2), c2);
            Device processInputDevice = DeviceProcessor.processInputDevice(c, device);
            if (!this.f11247a.c(processInputDevice.getUuid())) {
                tos.e("SensorManagerImpl", "stopAsyncRead device is invalid");
                throw new IllegalStateException(String.valueOf(16));
            }
            if (this.e.c(processInputDevice.getUuid())) {
                tos.e("SensorManagerImpl", "stopAsyncRead deviceDataListener is null");
                this.e.e(processInputDevice);
            }
            this.e.b(processInputDevice, sensor, asyncStopCallback, c);
            wearEngineBiOperate.biApiCalling(a2, c, "stopAsyncRead", String.valueOf(0));
            return 0;
        } catch (IllegalStateException e) {
            tos.e("SensorManagerImpl", "stopAsyncRead illegalStateException");
            wearEngineBiOperate.biApiCalling(a2, c, "stopAsyncRead", String.valueOf(trj.b(e)));
            throw e;
        }
    }

    @Override // com.huawei.wearengine.SensorManager
    public int stopAsyncReadSensors(Device device, List<Sensor> list, AsyncStopCallback asyncStopCallback) throws RemoteException {
        tos.a("SensorManagerImpl", "stopAsyncReadSensors enter");
        tom.e(device, "device must not be null!");
        tom.e(device.getUuid(), "device must not be null!");
        tom.e(list, "sensor must not be null!");
        tom.e(asyncStopCallback, "asyncReadCallback must not be null!");
        WearEngineBiOperate wearEngineBiOperate = new WearEngineBiOperate();
        wearEngineBiOperate.init();
        Context a2 = tot.a();
        String c = tri.c(Binder.getCallingUid(), a2, this.b.getApplicationIdByPid(Integer.valueOf(Binder.getCallingPid())));
        try {
            PowerModeChangeManager.a().b(true);
            for (Permission permission : a(c, list)) {
                this.c.a(c, "stopAsyncReadSensors", a(permission), permission);
            }
            Device processInputDevice = DeviceProcessor.processInputDevice(c, device);
            if (!this.f11247a.c(processInputDevice.getUuid())) {
                tos.e("SensorManagerImpl", "stopAsyncReadSensors device is invalid");
                throw new IllegalStateException(String.valueOf(16));
            }
            if (this.e.c(processInputDevice.getUuid())) {
                tos.e("SensorManagerImpl", "stopAsyncReadSensors deviceDataListener is null");
                this.e.e(processInputDevice);
            }
            List<Sensor> e = e(list);
            if (e.size() == 0) {
                tos.e("SensorManagerImpl", "stopAsyncReadSensors sensors invalid argument");
                throw new IllegalStateException(String.valueOf(5));
            }
            Iterator<Sensor> it = e.iterator();
            while (it.hasNext()) {
                this.e.b(processInputDevice, it.next(), asyncStopCallback, c);
            }
            wearEngineBiOperate.biApiCalling(a2, c, "stopAsyncReadSensors", String.valueOf(0));
            return 0;
        } catch (IllegalStateException e2) {
            tos.e("SensorManagerImpl", "stopAsyncReadSensors illegalStateException");
            wearEngineBiOperate.biApiCalling(a2, c, "stopAsyncReadSensors", String.valueOf(trj.b(e2)));
            throw e2;
        }
    }

    @Override // com.huawei.wearengine.core.common.ClientBinderDied
    public void handleClientBinderDied(String str) {
        tos.b("SensorManagerImpl", "handleClientBinderDied clientPkgName is: " + str);
        if (!a()) {
            tos.e("SensorManagerImpl", "handleClientBinderDied checkPermission failed");
        } else {
            this.e.a(str);
        }
    }

    private boolean a() {
        return Binder.getCallingUid() == getCallingUid();
    }

    private List<Sensor> e(List<Sensor> list) {
        if (list == null || list.size() == 0 || list.size() > 128) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (Sensor sensor : list) {
            Iterator it = arrayList.iterator();
            while (true) {
                if (it.hasNext()) {
                    if (sensor.getId() == ((Sensor) it.next()).getId()) {
                        break;
                    }
                } else {
                    arrayList.add(sensor);
                    break;
                }
            }
        }
        return arrayList;
    }

    void b(String str) {
        if (!a()) {
            tos.e("SensorManagerImpl", "removeListener checkPermission failed");
        } else {
            this.e.a(str);
        }
    }

    private void c(String str) {
        this.e.b(str);
    }

    private Permission c(String str, Sensor sensor) {
        top.a(sensor, "getSensorPermission sensor must not be null.");
        top.a(sensor.getName(), "getSensorPermission sensor name must not be null.");
        if (tri.d(str) <= 5) {
            return Permission.SENSOR;
        }
        return d.contains(sensor.getName()) ? Permission.SENSOR : Permission.MOTION_SENSOR;
    }

    private List<Permission> a(String str, List<Sensor> list) {
        top.a(list, "getSensorPermissions sensors must not be null.");
        ArrayList arrayList = new ArrayList();
        if (tri.d(str) <= 5) {
            arrayList.add(Permission.SENSOR);
            return arrayList;
        }
        for (Sensor sensor : list) {
            top.a(sensor, "getSensorPermissions sensor must not be null.");
            top.a(sensor.getName(), "getSensorPermissions sensor name must not be null.");
            if (d.contains(sensor.getName())) {
                if (!arrayList.contains(Permission.SENSOR)) {
                    arrayList.add(Permission.SENSOR);
                }
            } else if (!arrayList.contains(Permission.MOTION_SENSOR)) {
                arrayList.add(Permission.MOTION_SENSOR);
            }
        }
        return arrayList;
    }

    private tqo a(Permission permission) {
        top.a(permission, "getSensorScope permission must not be null.");
        return Permission.SENSOR.getName().equals(permission.getName()) ? tqo.e : tqo.c;
    }

    @Override // com.huawei.wearengine.core.device.PowerModeChangeManager.HandlePowerModeChange
    public void startClearData(String str) {
        tos.a("SensorManagerImpl", "startClearData");
        tos.b("SensorManagerImpl", "startClearData deviceId is: " + str);
        c(str);
    }
}
