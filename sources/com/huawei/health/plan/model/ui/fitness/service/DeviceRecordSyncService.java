package com.huawei.health.plan.model.ui.fitness.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import com.google.gson.Gson;
import com.huawei.basefitnessadvice.model.RateInfo;
import com.huawei.health.courseplanservice.api.OnStateListener;
import com.huawei.health.device.model.RecordAction;
import com.huawei.health.devicemgr.api.constant.DataCallbackType;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.asc;
import defpackage.ash;
import defpackage.ckd;
import defpackage.ckf;
import defpackage.ckl;
import defpackage.cun;
import defpackage.etr;
import defpackage.gic;
import defpackage.koq;
import defpackage.kxe;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class DeviceRecordSyncService extends Service {

    /* renamed from: a, reason: collision with root package name */
    private int f2933a;
    private e b;
    private int j;
    private final IBinder d = new DeviceDataSyncBinder();
    private boolean e = false;
    private boolean i = false;
    private IBaseResponseCallback c = new IBaseResponseCallback() { // from class: com.huawei.health.plan.model.ui.fitness.service.DeviceRecordSyncService.3
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (i != 5) {
                if (i == 7) {
                    a(obj);
                    return;
                } else {
                    if (i != 127) {
                        return;
                    }
                    int intValue = ((Integer) obj).intValue();
                    LogUtil.b("Suggestion_DeviceRecordSyncService", "sync fail error code:", Integer.valueOf(intValue));
                    DeviceRecordSyncService.this.d(2, String.valueOf(intValue));
                    return;
                }
            }
            if (koq.e(obj, ckl.class)) {
                for (ckl cklVar : (List) obj) {
                    if (cklVar != null) {
                        DeviceRecordSyncService.this.c(cklVar);
                    }
                }
                DeviceRecordSyncService.this.i = true;
                HashMap hashMap = new HashMap(16);
                hashMap.put(1, String.valueOf(5));
                hashMap.put(2, String.valueOf(100000));
                cun.c().sendDeviceData(36, 12, hashMap, null, "Suggestion_DeviceRecordSyncService");
                if (DeviceRecordSyncService.this.e && DeviceRecordSyncService.this.i) {
                    DeviceRecordSyncService deviceRecordSyncService = DeviceRecordSyncService.this;
                    deviceRecordSyncService.c(deviceRecordSyncService.f2933a);
                    DeviceRecordSyncService.this.b();
                }
            }
        }

        private void a(Object obj) {
            if (koq.e(obj, ckf.class)) {
                for (ckf ckfVar : (List) obj) {
                    if (ckfVar != null) {
                        DeviceRecordSyncService.this.b(ckfVar);
                    }
                }
                DeviceRecordSyncService.this.e = true;
                HashMap hashMap = new HashMap(16);
                hashMap.put(1, String.valueOf(7));
                hashMap.put(2, String.valueOf(100000));
                cun.c().sendDeviceData(36, 12, hashMap, null, "Suggestion_DeviceRecordSyncService");
                if (DeviceRecordSyncService.this.e && DeviceRecordSyncService.this.i) {
                    DeviceRecordSyncService deviceRecordSyncService = DeviceRecordSyncService.this;
                    deviceRecordSyncService.c(deviceRecordSyncService.f2933a);
                    DeviceRecordSyncService.this.b();
                }
            }
        }
    };

    public class DeviceDataSyncBinder extends Binder {

        /* renamed from: a, reason: collision with root package name */
        OnStateListener f2934a;

        public DeviceDataSyncBinder() {
        }

        public OnStateListener getListener() {
            return this.f2934a;
        }

        public void setListener(OnStateListener onStateListener) {
            this.f2934a = onStateListener;
        }

        public void dataSync() {
            DeviceRecordSyncService.this.b.sendEmptyMessage(0);
        }
    }

    static class e extends Handler {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<DeviceRecordSyncService> f2935a;

        e(DeviceRecordSyncService deviceRecordSyncService) {
            this.f2935a = new WeakReference<>(deviceRecordSyncService);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            DeviceRecordSyncService deviceRecordSyncService = this.f2935a.get();
            if (deviceRecordSyncService == null) {
                LogUtil.h("Suggestion_DeviceRecordSyncService", "handleMessage, service == null");
                super.handleMessage(message);
            } else {
                if (message.what != 0) {
                    return;
                }
                deviceRecordSyncService.a();
            }
        }
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        this.b = new e(this);
        LogUtil.a("Suggestion_DeviceRecordSyncService", "start data sync service");
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        if (intent != null) {
            super.onStartCommand(intent, i, i2);
        }
        this.b.sendEmptyMessage(0);
        LogUtil.a("Suggestion_DeviceRecordSyncService", "onStartCommand ");
        return 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, String str) {
        IBinder iBinder = this.d;
        if (iBinder instanceof DeviceDataSyncBinder) {
            OnStateListener listener = ((DeviceDataSyncBinder) iBinder).getListener();
            LogUtil.a("Suggestion_DeviceRecordSyncService", "onStateListener:", listener);
            if (listener != null) {
                listener.onFailure(i, str);
            }
            stopSelf();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        IBinder iBinder = this.d;
        if (iBinder instanceof DeviceDataSyncBinder) {
            OnStateListener listener = ((DeviceDataSyncBinder) iBinder).getListener();
            if (listener != null) {
                listener.onFinish();
            }
            stopSelf();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "Suggestion_DeviceRecordSyncService");
        if (deviceInfo != null) {
            Map<String, DeviceCapability> queryDeviceCapability = cun.c().queryDeviceCapability(1, deviceInfo.getDeviceIdentify(), "Suggestion_DeviceRecordSyncService");
            if (queryDeviceCapability == null) {
                LogUtil.h("Suggestion_DeviceRecordSyncService", "dataSync deviceCapabilityHashMaps is null!");
                return;
            }
            DeviceCapability deviceCapability = queryDeviceCapability.get(deviceInfo.getDeviceIdentify());
            if (deviceCapability == null) {
                LogUtil.h("Suggestion_DeviceRecordSyncService", "dataSync deviceCapability is null!");
                return;
            }
            if (!deviceCapability.isSupportPosture()) {
                LogUtil.a("Suggestion_DeviceRecordSyncService", "device is not support!");
                d(1, "device is not support");
                return;
            } else {
                this.j = e();
                this.f2933a = (int) (System.currentTimeMillis() / 1000);
                LogUtil.a("Suggestion_DeviceRecordSyncService", "enter queryDeviceRecords");
                asc.e().b(new Runnable() { // from class: com.huawei.health.plan.model.ui.fitness.service.DeviceRecordSyncService.1
                    @Override // java.lang.Runnable
                    public void run() {
                        cun.c().registerDataCallback(DeviceRecordSyncService.this.c, DataCallbackType.FITNESS_POSTURE_MANAGER, "Suggestion_DeviceRecordSyncService");
                        HashMap hashMap = new HashMap(16);
                        hashMap.put(1, String.valueOf(DeviceRecordSyncService.this.j));
                        hashMap.put(2, String.valueOf(DeviceRecordSyncService.this.f2933a));
                        cun.c().sendDeviceData(36, 7, hashMap, null, "Suggestion_DeviceRecordSyncService");
                        cun.c().sendDeviceData(36, 5, hashMap, null, "Suggestion_DeviceRecordSyncService");
                    }
                });
                return;
            }
        }
        LogUtil.a("Suggestion_DeviceRecordSyncService", "device is not connected!");
        d(0, "device is not connected");
    }

    private int e() {
        LogUtil.a("Suggestion_DeviceRecordSyncService", "getLastSyncTime:", ash.b("deviceLastSyncTime"));
        return gic.e((Object) ash.b("deviceLastSyncTime"));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        LogUtil.a("Suggestion_DeviceRecordSyncService", "setLastSyncTime:", Integer.valueOf(i));
        ash.a("deviceLastSyncTime", String.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(ckf ckfVar) {
        LogUtil.a("Suggestion_DeviceRecordSyncService", "courseRecord:", ckfVar.toString());
        WorkoutRecord workoutRecord = new WorkoutRecord();
        workoutRecord.saveWorkoutId(ckfVar.b());
        workoutRecord.saveWorkoutName(ckfVar.g());
        workoutRecord.saveExerciseTime(ckfVar.j() * 1000);
        workoutRecord.setDuration(ckfVar.n() * 1000);
        workoutRecord.saveFinishRate(ckfVar.c());
        workoutRecord.saveActionSummary(new Gson().toJson(c(ckfVar.h())));
        LogUtil.a("Suggestion_DeviceRecordSyncService", "saveActionSummary", new Gson().toJson(ckfVar.h()));
        workoutRecord.saveActualCalorie(ckfVar.a() * 1000.0f);
        RateInfo rateInfo = new RateInfo();
        rateInfo.saveMaxRate(ckfVar.m());
        rateInfo.saveMinRate(ckfVar.o());
        rateInfo.saveLimit(ckfVar.k());
        rateInfo.setAnaerobic(ckfVar.e());
        rateInfo.setAerobic(ckfVar.d());
        rateInfo.saveFatBurning(ckfVar.i());
        rateInfo.saveWarmUp(ckfVar.l());
        workoutRecord.saveExtend(false, 0, rateInfo);
        workoutRecord.saveWearType(ckfVar.f());
        etr.b().updatePlanProgress(workoutRecord);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(ckl cklVar) {
        LogUtil.a("Suggestion_DeviceRecordSyncService", "postureRecord:", cklVar.toString());
        WorkoutRecord workoutRecord = new WorkoutRecord();
        workoutRecord.saveWorkoutId("D" + cklVar.k());
        workoutRecord.saveWorkoutName(cklVar.o());
        workoutRecord.saveExerciseTime(((long) cklVar.h()) * 1000);
        workoutRecord.setDuration(cklVar.t() * 1000);
        workoutRecord.saveActionSummary("[]");
        if (cklVar.g() != 0) {
            if (cklVar.f() == 0) {
                workoutRecord.saveFinishRate((cklVar.i() * 100.0f) / cklVar.g());
            } else if (cklVar.f() == 1) {
                workoutRecord.saveFinishRate((cklVar.t() * 100.0f) / cklVar.g());
            }
        }
        workoutRecord.saveActualCalorie(cklVar.a() * 1000.0f);
        RateInfo rateInfo = new RateInfo();
        rateInfo.saveMaxRate(cklVar.n());
        rateInfo.saveMinRate(cklVar.l());
        rateInfo.saveLimit(cklVar.m());
        rateInfo.setAnaerobic(cklVar.d());
        rateInfo.setAerobic(cklVar.e());
        rateInfo.saveFatBurning(cklVar.j());
        rateInfo.saveWarmUp(cklVar.q());
        workoutRecord.saveExtend(true, cklVar.i(), cklVar.c(), rateInfo);
        workoutRecord.saveWearType(cklVar.b());
        etr.b().updatePlanProgress(workoutRecord);
    }

    private List<RecordAction> c(List<ckd> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (ckd ckdVar : list) {
            if (ckdVar != null) {
                arrayList.add(new RecordAction("", ckdVar.a(), ckdVar.b(), ckdVar.e(), kxe.e(ckdVar.d())));
            }
        }
        return arrayList;
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
        cun.c().unregisterDataCallback(0);
        LogUtil.a("Suggestion_DeviceRecordSyncService", "destroy data sync service");
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        LogUtil.a("Suggestion_DeviceRecordSyncService", "onBind ");
        return this.d;
    }
}
