package com.huawei.health.developerkit;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerCenter;
import com.huawei.health.IBaseCommonCallback;
import com.huawei.health.ITrackDataForDeveloper;
import com.huawei.health.ITrackManagerForDeveloper;
import com.huawei.health.developerkit.TrackDeveloperKitProxy;
import com.huawei.health.device.api.BackgroundSportApi;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.ui.measure.fragment.device.DeviceBindWaitingFragment;
import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.health.sportservice.SportDataOutputApi;
import com.huawei.health.sportservice.SportLifecycle;
import com.huawei.health.suggestion.CoachController;
import com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle;
import com.huawei.health.suggestion.ui.fitness.activity.SelectDeviceActivity;
import com.huawei.healthcloud.plugintrack.manager.inteface.ISportStateChangeListener;
import com.huawei.hihealth.CharacteristicConstant;
import com.huawei.hihealth.StartSportParam;
import com.huawei.hihealth.StopSportParam;
import com.huawei.hihealthservice.hihealthkit.util.HiHealthKitDataChecker;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwfoundationmodel.trackmodel.ISportDataCallback;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.activity.IndoorEquipConnectedActivity;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.share.HiHealthError;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import com.huawei.ui.device.activity.adddevice.OneKeyScanActivity;
import defpackage.bzk;
import defpackage.cap;
import defpackage.ceo;
import defpackage.dum;
import defpackage.ffs;
import defpackage.fgm;
import defpackage.fhw;
import defpackage.fpq;
import defpackage.ggx;
import defpackage.gij;
import defpackage.gnm;
import defpackage.gso;
import defpackage.gtx;
import defpackage.gwl;
import defpackage.gxf;
import defpackage.ima;
import defpackage.ipd;
import defpackage.ktj;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class TrackDeveloperKitProxy extends ITrackManagerForDeveloper.Stub {

    /* renamed from: a, reason: collision with root package name */
    private static final List<Integer> f2216a;
    private boolean e;
    private IBaseCommonCallback h;
    private double k;
    private int l;
    private RemoteCallbackList<e> o = new RemoteCallbackList<>();
    private int f = CharacteristicConstant.SportRecordSaveModel.SAVE.getValue();
    private int i = 3;
    private Observer b = new Observer() { // from class: com.huawei.health.developerkit.TrackDeveloperKitProxy.1
        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            LogUtil.a("Track_TrackDeveloperKitProxy", "already select device and begin to start device, args is " + Arrays.toString(objArr));
            try {
                if (objArr.length == 0) {
                    TrackDeveloperKitProxy.this.h.onResponse(HiHealthError.ERR_WRONG_DEVICE, "connect wrong device");
                    TrackDeveloperKitProxy.this.e = false;
                    ReleaseLogUtil.c("Track_TrackDeveloperKitProxy", "mDeviceAssociateObserver device info is null, connect wrong device");
                } else {
                    Object obj = objArr[0];
                    if ((obj instanceof String) && (objArr[1] instanceof Integer)) {
                        StartSportParam startSportParam = new StartSportParam((String) objArr[0], 1, ((Integer) objArr[1]).intValue(), TrackDeveloperKitProxy.this.l);
                        startSportParam.putInt("isBackground", 1);
                        startSportParam.putBoolean("isConnectSportDevice", TrackDeveloperKitProxy.this.e);
                        startSportParam.putInt("isSaveData", TrackDeveloperKitProxy.this.f);
                        try {
                            TrackDeveloperKitProxy trackDeveloperKitProxy = TrackDeveloperKitProxy.this;
                            trackDeveloperKitProxy.startSportEnhance(startSportParam, trackDeveloperKitProxy.h);
                        } catch (RemoteException e2) {
                            ReleaseLogUtil.c("Track_TrackDeveloperKitProxy", "mDeviceAssociateObserver RemoteException: ", e2.getMessage());
                        }
                        TrackDeveloperKitProxy.this.e = false;
                    } else if (obj instanceof Integer) {
                        TrackDeveloperKitProxy.this.h.onResponse(152, "cancel select device");
                        TrackDeveloperKitProxy.this.e = false;
                        ReleaseLogUtil.d("Track_TrackDeveloperKitProxy", "mDeviceAssociateObserver cancel select device");
                    } else {
                        ReleaseLogUtil.d("Track_TrackDeveloperKitProxy", "mDeviceAssociateObserver args type is not right");
                        TrackDeveloperKitProxy.this.e = false;
                    }
                }
            } catch (RemoteException e3) {
                ReleaseLogUtil.c("Track_TrackDeveloperKitProxy", "mDeviceAssociateObserver callback  RemoteException: " + e3.getMessage());
                TrackDeveloperKitProxy.this.e = false;
            }
            if (TrackDeveloperKitProxy.this.b != null) {
                ObserverManagerUtil.e(TrackDeveloperKitProxy.this.b, "DEVICE_ASSOCIATION");
            }
        }
    };
    private int j = 4;
    private Handler d = new Handler(Looper.getMainLooper()) { // from class: com.huawei.health.developerkit.TrackDeveloperKitProxy.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
            }
            super.handleMessage(message);
            IBaseCommonCallback iBaseCommonCallback = (IBaseCommonCallback) message.obj;
            switch (message.what) {
                case 201:
                    if (gso.e().k()) {
                        LogUtil.a("Track_TrackDeveloperKitProxy", "sport started success");
                        TrackDeveloperKitProxy.this.b(iBaseCommonCallback, 0, ipd.b(0));
                        break;
                    } else {
                        ReleaseLogUtil.c("Track_TrackDeveloperKitProxy", "sport started failed");
                        TrackDeveloperKitProxy.this.b(iBaseCommonCallback, 4, ipd.b(4));
                        break;
                    }
                case 202:
                    if (!gso.e().k()) {
                        LogUtil.a("Track_TrackDeveloperKitProxy", "stopped sport success");
                        gtx.c(BaseApplication.getContext()).a(0);
                        TrackDeveloperKitProxy.this.b(iBaseCommonCallback, 0, ipd.b(0));
                        break;
                    } else {
                        ReleaseLogUtil.c("Track_TrackDeveloperKitProxy", "stopped sport failed");
                        TrackDeveloperKitProxy.this.b(iBaseCommonCallback, 4, ipd.b(4));
                        break;
                    }
                case 203:
                    if (gso.e().i() == 2) {
                        LogUtil.a("Track_TrackDeveloperKitProxy", "pause sport success");
                        TrackDeveloperKitProxy.this.b(iBaseCommonCallback, 0, ipd.b(0));
                        break;
                    } else {
                        ReleaseLogUtil.c("Track_TrackDeveloperKitProxy", "pause sport failed");
                        TrackDeveloperKitProxy.this.b(iBaseCommonCallback, 4, ipd.b(4));
                        break;
                    }
                case 204:
                    if (gso.e().k()) {
                        LogUtil.a("Track_TrackDeveloperKitProxy", "resume sport success");
                        TrackDeveloperKitProxy.this.b(iBaseCommonCallback, 0, ipd.b(0));
                        break;
                    } else {
                        ReleaseLogUtil.c("Track_TrackDeveloperKitProxy", "resume sport failed");
                        TrackDeveloperKitProxy.this.b(iBaseCommonCallback, 4, ipd.b(4));
                        break;
                    }
            }
        }
    };
    private final SportDataOutputApi g = (SportDataOutputApi) Services.a("SportService", SportDataOutputApi.class);
    private final BackgroundSportApi c = (BackgroundSportApi) Services.c("PluginLinkIndoorEquip", BackgroundSportApi.class);

    static {
        ArrayList arrayList = new ArrayList();
        f2216a = arrayList;
        arrayList.add(257);
        arrayList.add(258);
        arrayList.add(259);
    }

    @Override // com.huawei.health.ITrackManagerForDeveloper
    public boolean registerRealtimeSportCallback(ITrackDataForDeveloper iTrackDataForDeveloper) throws RemoteException {
        int i;
        e eVar;
        ReleaseLogUtil.e("Track_TrackDeveloperKitProxy", "registerRealtimeSportCallback at ", Long.valueOf(System.currentTimeMillis()));
        if (iTrackDataForDeveloper != null) {
            try {
                i = this.o.beginBroadcast();
            } catch (IllegalStateException e2) {
                ReleaseLogUtil.c("Track_TrackDeveloperKitProxy", "beginBroadcast()", LogAnonymous.b((Throwable) e2));
                i = 0;
            }
            LogUtil.a("Track_TrackDeveloperKitProxy", "registerDataCallback:Report client count ", Integer.valueOf(i));
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    eVar = null;
                    break;
                }
                if (this.o.getBroadcastItem(i2).asBinder() == iTrackDataForDeveloper.asBinder()) {
                    eVar = this.o.getBroadcastItem(i2);
                    break;
                }
                i2++;
            }
            try {
                this.o.finishBroadcast();
            } catch (IllegalStateException e3) {
                ReleaseLogUtil.c("Track_TrackDeveloperKitProxy", "finishBroadcast()", LogAnonymous.b((Throwable) e3));
            }
            if (eVar == null) {
                e eVar2 = new e(iTrackDataForDeveloper, this);
                gtx.c(BaseApplication.getContext()).a(eVar2, 1L);
                gtx.c(BaseApplication.getContext()).c(eVar2);
                CoachController.d().d(CoachController.StatusSource.APP, eVar2);
                CoachController.d().e(eVar2);
                SportDataOutputApi sportDataOutputApi = this.g;
                if (sportDataOutputApi != null) {
                    sportDataOutputApi.registerSportStatus(eVar2.c(), eVar2);
                    int status = this.g.getStatus();
                    if (status == 1) {
                        eVar2.onStartSport();
                    }
                    if (status == 2) {
                        eVar2.onPauseSport();
                    }
                    a();
                    this.o.register(eVar2);
                }
                return true;
            }
            ReleaseLogUtil.d("Track_TrackDeveloperKitProxy", "registerDataCallback callBack is exist or sportmanager is null");
        } else {
            ReleaseLogUtil.d("Track_TrackDeveloperKitProxy", "registerDataCallback callback is null");
        }
        return false;
    }

    private void a() {
        int i;
        if (gtx.c(BaseApplication.getContext()).bc()) {
            LogUtil.h("Track_TrackDeveloperKitProxy", "getSportData mSportManager is null");
            i = gtx.c(BaseApplication.getContext()).m();
        } else {
            i = 4;
        }
        if (i == 1) {
            this.j = 2;
        } else {
            if (i != 2) {
                return;
            }
            this.j = 1;
        }
    }

    @Override // com.huawei.health.ITrackManagerForDeveloper
    public boolean unregisterRealtimeSportCallback(ITrackDataForDeveloper iTrackDataForDeveloper) throws RemoteException {
        int i;
        e eVar;
        ReleaseLogUtil.e("Track_TrackDeveloperKitProxy", "unRegisterDataCallback at", Long.valueOf(System.currentTimeMillis()));
        if (iTrackDataForDeveloper != null) {
            try {
                i = this.o.beginBroadcast();
            } catch (IllegalStateException e2) {
                ReleaseLogUtil.c("Track_TrackDeveloperKitProxy", "beginBroadcast()", LogAnonymous.b((Throwable) e2));
                i = 0;
            }
            LogUtil.c("Track_TrackDeveloperKitProxy", "unRegisterDataCallback:Report client count ", Integer.valueOf(i));
            int i2 = 0;
            while (true) {
                if (i2 >= i) {
                    eVar = null;
                    break;
                }
                if (this.o.getBroadcastItem(i2).asBinder() == iTrackDataForDeveloper.asBinder()) {
                    eVar = this.o.getBroadcastItem(i2);
                    break;
                }
                i2++;
            }
            try {
                this.o.finishBroadcast();
            } catch (IllegalStateException e3) {
                ReleaseLogUtil.c("Track_TrackDeveloperKitProxy", "finishBroadcast()", LogAnonymous.b((Throwable) e3));
            }
            if (eVar != null) {
                gtx.c(BaseApplication.getContext()).e(eVar);
                gtx.c(BaseApplication.getContext()).b(eVar);
                SportDataOutputApi sportDataOutputApi = this.g;
                if (sportDataOutputApi != null) {
                    sportDataOutputApi.unRegisterSportStatus(eVar.c());
                    if (eVar.e() != null) {
                        this.g.cancelSubscribeNotify(eVar.e());
                    }
                }
                this.o.unregister(eVar);
                LogUtil.a("Track_TrackDeveloperKitProxy", "Unregister the callback on service");
                d(i);
                return true;
            }
            ReleaseLogUtil.d("Track_TrackDeveloperKitProxy", "unregisterRealtimeSportCallback callBack is exist or sportmanager is null");
        } else {
            ReleaseLogUtil.d("Track_TrackDeveloperKitProxy", "unregisterRealtimeSportCallback callback is null");
        }
        return false;
    }

    private void d(int i) {
        LogUtil.a("Track_TrackDeveloperKitProxy", "checkTrackDataReport ", Integer.valueOf(i));
        if (i != 1) {
            return;
        }
        onServiceDestroy();
        this.o = new RemoteCallbackList<>();
    }

    @Override // com.huawei.health.ITrackManagerForDeveloper
    public int getSportState() throws RemoteException {
        ReleaseLogUtil.e("Track_TrackDeveloperKitProxy", "enter getSportState");
        return this.j;
    }

    @Override // com.huawei.health.ITrackManagerForDeveloper
    public Bundle getSportData() throws RemoteException {
        ReleaseLogUtil.e("Track_TrackDeveloperKitProxy", "enter getSportData");
        if (!gtx.c(BaseApplication.getContext()).bc()) {
            ReleaseLogUtil.d("Track_TrackDeveloperKitProxy", "getSportData mSportManager is null");
            return null;
        }
        return Dv_(gtx.c(BaseApplication.getContext()).aUd_());
    }

    @Override // com.huawei.health.ITrackManagerForDeveloper
    public void connectSportDevice(int i, IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("Track_TrackDeveloperKitProxy", "connectSportDevice ", Integer.valueOf(i));
        if (iBaseCommonCallback == null) {
            return;
        }
        if (!fhw.e.containsKey(Integer.valueOf(i))) {
            ReleaseLogUtil.c("Track_TrackDeveloperKitProxy", "sportType: ", Integer.valueOf(i), " is not supported");
            iBaseCommonCallback.onResponse(2, ipd.b(2));
        } else if (d() || gso.e().k()) {
            LogUtil.h("Track_TrackDeveloperKitProxy", "sport has been started before");
            iBaseCommonCallback.onResponse(4, "sport has been started before");
        } else {
            this.e = true;
            a(i, iBaseCommonCallback);
        }
    }

    @Override // com.huawei.health.ITrackManagerForDeveloper
    public void startSport(int i, IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("Track_TrackDeveloperKitProxy", "start sport ", Integer.valueOf(i));
        startSportEnhance(new StartSportParam(i), iBaseCommonCallback);
    }

    private void c(final int i, final IBaseCommonCallback iBaseCommonCallback, final int i2) throws RemoteException {
        ReleaseLogUtil.e("Track_TrackDeveloperKitProxy", "dealWithWearAppSport isSaveData: ", Integer.valueOf(i2));
        if (!ggx.a(com.huawei.haf.application.BaseApplication.e().getApplicationContext(), gij.b())) {
            int i3 = this.i;
            if (i3 > 0) {
                ReleaseLogUtil.e("Track_TrackDeveloperKitProxy", "isConnectedHeartRateDeviceWear is false, retry times: ", Integer.valueOf(i3));
                HandlerCenter.d().e(new Runnable() { // from class: ceg
                    @Override // java.lang.Runnable
                    public final void run() {
                        TrackDeveloperKitProxy.this.b(i, iBaseCommonCallback, i2);
                    }
                }, this.i * 1000);
                this.i--;
                return;
            } else {
                this.i = 3;
                ReleaseLogUtil.c("Track_TrackDeveloperKitProxy", "Trying to retry but still unable to connect");
                iBaseCommonCallback.onResponse(150, ipd.b(150));
                return;
            }
        }
        this.i = 3;
        if (!fpq.b()) {
            ReleaseLogUtil.c("Track_TrackDeveloperKitProxy", "device is not supported");
            iBaseCommonCallback.onResponse(153, ipd.b(153));
        } else {
            cap.b(new IBaseResponseCallback() { // from class: cel
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i4, Object obj) {
                    TrackDeveloperKitProxy.this.c(i, i2, iBaseCommonCallback, i4, obj);
                }
            });
        }
    }

    public /* synthetic */ void b(int i, IBaseCommonCallback iBaseCommonCallback, int i2) {
        try {
            c(i, iBaseCommonCallback, i2);
        } catch (RemoteException e2) {
            ReleaseLogUtil.c("Track_TrackDeveloperKitProxy", "dealWithWearAppSport RemoteException: ", e2.getMessage());
        }
    }

    public /* synthetic */ void c(int i, int i2, IBaseCommonCallback iBaseCommonCallback, int i3, Object obj) {
        ReleaseLogUtil.e("Track_TrackDeveloperKitProxy", "getDeviceSportStatus has response code = ", Integer.valueOf(i3));
        try {
            if ((obj instanceof Integer) && i3 == 100000 && ((Integer) obj).intValue() == 0) {
                StartSportParam startSportParam = new StartSportParam();
                startSportParam.putInt(BleConstants.SPORT_TYPE, i);
                startSportParam.putInt("isBackground", CharacteristicConstant.SportModeType.BACKGROUND_SPORT_MODE.getType());
                startSportParam.putInt("isSaveData", i2);
                gtx.c(gxf.d()).e(i);
                this.c.startSportEnhance(startSportParam, iBaseCommonCallback);
            } else {
                iBaseCommonCallback.onResponse(4, "device status failed to get");
                ReleaseLogUtil.d("Track_TrackDeveloperKitProxy", "getDeviceSportStatus errorCode:", Integer.valueOf(i3), " objData:", obj);
            }
        } catch (RemoteException e2) {
            ReleaseLogUtil.d("Track_TrackDeveloperKitProxy", "getDeviceSportStatus RemoteException: ", e2.getMessage());
        }
    }

    private void a(int i, IBaseCommonCallback iBaseCommonCallback) {
        ObserverManagerUtil.e(this.b, "DEVICE_ASSOCIATION");
        ObserverManagerUtil.d(this.b, "DEVICE_ASSOCIATION");
        this.h = iBaseCommonCallback;
        this.l = i;
        ArrayList<ContentValues> d = ceo.d().d((HealthDevice.HealthDeviceKind) fhw.e.get(Integer.valueOf(i)));
        ReleaseLogUtil.e("Track_TrackDeveloperKitProxy", "device list length is " + d.size() + "detail is " + d.toString());
        if (!d.isEmpty() && d.size() > 1) {
            LogUtil.a("Track_TrackDeveloperKitProxy", "come to select one device");
            Intent intent = new Intent(gxf.d(), (Class<?>) SelectDeviceActivity.class);
            intent.addFlags(1409318912);
            intent.putExtra("SPORT_TYPE", i);
            gnm.aPB_(gxf.d(), intent);
            return;
        }
        if (d.size() == 1) {
            LogUtil.a("Track_TrackDeveloperKitProxy", "only one device bonded, startSportEnhance");
            ObserverManagerUtil.c("DEVICE_ASSOCIATION", d.get(0).getAsString("uniqueId"), fhw.f12519a.get(Integer.valueOf(i)));
            return;
        }
        LogUtil.a("Track_TrackDeveloperKitProxy", "no devices bonded,so jump to onekeyscan page");
        ObserverManagerUtil.e(DeviceBindWaitingFragment.sSkipProductIntroductionObserver, "SKIP_PRODUCT_INTRODUCTION_FRAGMENT");
        ObserverManagerUtil.e("SKIP_START_EXERCISE_UI");
        ObserverManagerUtil.d(DeviceBindWaitingFragment.sSkipProductIntroductionObserver, "SKIP_PRODUCT_INTRODUCTION_FRAGMENT");
        ObserverManagerUtil.d(IndoorEquipConnectedActivity.b, "SKIP_START_EXERCISE_UI");
        Intent intent2 = new Intent(gxf.d(), (Class<?>) OneKeyScanActivity.class);
        intent2.addFlags(1409318912);
        intent2.putExtra("SPORT_TYPE", i);
        gnm.aPB_(gxf.d(), intent2);
    }

    @Override // com.huawei.health.ITrackManagerForDeveloper
    public void startSportEnhance(StartSportParam startSportParam, IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("Track_TrackDeveloperKitProxy", "enter startSportEnhance");
        if (iBaseCommonCallback == null) {
            ReleaseLogUtil.c("Track_TrackDeveloperKitProxy", "startSportEnhance callback is null");
            return;
        }
        if (this.g == null) {
            ReleaseLogUtil.d("Track_TrackDeveloperKitProxy", "mSportDataOutputApi is null in startSportEnhance()");
            iBaseCommonCallback.onResponse(4, "Failed to initialize the interface");
            return;
        }
        int i = startSportParam.getInt(BleConstants.SPORT_TYPE);
        if (d() || gso.e().k()) {
            ReleaseLogUtil.d("Track_TrackDeveloperKitProxy", "sport has been started before");
            iBaseCommonCallback.onResponse(4, "sport has been started before");
            return;
        }
        if (dum.d() != null) {
            ReleaseLogUtil.e("Track_TrackDeveloperKitProxy", "getInstance of Mediator success");
        } else {
            ReleaseLogUtil.e("Track_TrackDeveloperKitProxy", "getInstance of Mediator failed");
        }
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
        if (fhw.b.contains(Integer.valueOf(i))) {
            ReleaseLogUtil.e("Track_TrackDeveloperKitProxy", "start other sport");
            c(i, iBaseCommonCallback, startSportParam.getInt("isSaveData", CharacteristicConstant.SportRecordSaveModel.SAVE.getValue()));
        } else if (f2216a.contains(Integer.valueOf(i))) {
            e(startSportParam, iBaseCommonCallback);
        } else {
            d(startSportParam, iBaseCommonCallback);
        }
    }

    private void e(StartSportParam startSportParam, IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("Track_TrackDeveloperKitProxy", "start track sport");
        int i = startSportParam.getInt(BleConstants.SPORT_TYPE);
        String[] c = PermissionUtil.c(PermissionUtil.PermissionType.LOCATION_WITH_BACKGROUND);
        if (i != 264 && (!ktj.e(BaseApplication.getContext()) || !PermissionUtil.e(BaseApplication.getContext(), c))) {
            ReleaseLogUtil.d("Track_TrackDeveloperKitProxy", "gps or location not enabled");
            iBaseCommonCallback.onResponse(1006, "gps or location not enabled");
            return;
        }
        Bundle bundle = new Bundle();
        int i2 = startSportParam.getInt("isSaveData", CharacteristicConstant.SportRecordSaveModel.SAVE.getValue());
        ReleaseLogUtil.e("Track_TrackDeveloperKitProxy", "track sport isSaveData: ", Integer.valueOf(i2));
        bundle.putInt("keySportRecordIsToSave", i2);
        gso.e().aTr_(i, true, false, bundle);
        Message obtain = Message.obtain();
        obtain.obj = iBaseCommonCallback;
        obtain.what = 201;
        this.d.sendMessageDelayed(obtain, 3000L);
    }

    private void d(StartSportParam startSportParam, IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
        int i = startSportParam.getInt(BleConstants.SPORT_TYPE);
        ReleaseLogUtil.e("Track_TrackDeveloperKitProxy", "start aerobic sport");
        if (TextUtils.isEmpty(startSportParam.getString("macAddress"))) {
            this.f = startSportParam.getInt("isSaveData", CharacteristicConstant.SportRecordSaveModel.SAVE.getValue());
            a(i, iBaseCommonCallback);
            return;
        }
        if (startSportParam.getInt("isBackground") == CharacteristicConstant.SportModeType.BACKGROUND_SPORT_MODE.getType()) {
            gtx.c(gxf.d()).e(i);
            LogUtil.a("Track_TrackDeveloperKitProxy", "backgroundSportApi.startSportEnhance");
            this.c.startSportEnhance(startSportParam, iBaseCommonCallback);
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.indoorequip.activity.StartSportTransitActivity");
        intent.setPackage(BaseApplication.getAppPackage());
        intent.setFlags(268435456);
        intent.putExtra("startSportParam", ima.a().b(startSportParam));
        if (gxf.d().getPackageManager().resolveActivity(intent, 0) != null) {
            gnm.aPB_(gxf.d(), intent);
            iBaseCommonCallback.onResponse(0, ipd.b(0));
        } else {
            LogUtil.h("Track_TrackDeveloperKitProxy", "com.huawei.indoorequip.activity.StartSportTransitActivity", " is not exist");
            iBaseCommonCallback.onResponse(4, ipd.b(4));
        }
    }

    private boolean d() {
        int status = this.g.getStatus();
        LogUtil.a("Track_TrackDeveloperKitProxy", "Aerobic sport status is ", Integer.valueOf(status));
        return (status == 0 || status == 3) ? false : true;
    }

    @Override // com.huawei.health.ITrackManagerForDeveloper
    public void stopSport(IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("Track_TrackDeveloperKitProxy", "enter stop sport");
        this.k = 0.0d;
        if (iBaseCommonCallback == null) {
            ReleaseLogUtil.d("Track_TrackDeveloperKitProxy", "stopSport callback is null");
            return;
        }
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
        gtx c = gtx.c(gxf.d());
        int n = c.n();
        LogUtil.a("Track_TrackDeveloperKitProxy", "stop sportType = ", Integer.valueOf(n));
        if (fhw.e.containsKey(Integer.valueOf(n)) || fhw.b.contains(Integer.valueOf(n)) || n == 290 || n == 291) {
            e(c, iBaseCommonCallback);
        } else {
            c(c, iBaseCommonCallback);
        }
    }

    @Override // com.huawei.health.ITrackManagerForDeveloper
    public void stopSportEnhance(StopSportParam stopSportParam, IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("Track_TrackDeveloperKitProxy", "enter stop sport enhance");
        int n = gtx.c(gxf.d()).n();
        ReleaseLogUtil.e("Track_TrackDeveloperKitProxy", "stop sportType = ", Integer.valueOf(n));
        String string = stopSportParam.getString("source");
        ReleaseLogUtil.e("Track_TrackDeveloperKitProxy", "stop sport source = ", string);
        if (CharacteristicConstant.StopSportSource.AIDL_DISCONNECTED.getSource().equals(string) && fhw.b.contains(Integer.valueOf(n))) {
            ReleaseLogUtil.e("Track_TrackDeveloperKitProxy", "Other sport does not need to be stopped while AIDL is disconnected.");
            iBaseCommonCallback.onResponse(1024, ipd.b(1024));
        } else {
            stopSport(iBaseCommonCallback);
        }
    }

    private void c(gtx gtxVar, IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
        if (gso.e().k()) {
            if (!gtxVar.u()) {
                ReleaseLogUtil.d("Track_TrackDeveloperKitProxy", "sport has started from foreground");
                iBaseCommonCallback.onResponse(4, "can't stop sport invoked from foreground");
                return;
            }
            gso.e();
            gso.a(false);
            Message obtain = Message.obtain();
            obtain.obj = iBaseCommonCallback;
            obtain.what = 202;
            this.d.sendMessageDelayed(obtain, 3000L);
            return;
        }
        ReleaseLogUtil.d("Track_TrackDeveloperKitProxy", "sport stopped before");
        iBaseCommonCallback.onResponse(0, "sport stopped before");
    }

    private void e(gtx gtxVar, IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("Track_TrackDeveloperKitProxy", "enter stopAerobicSport()");
        if (this.g == null) {
            ReleaseLogUtil.d("Track_TrackDeveloperKitProxy", "mSportDataOutputApi is null in stopAerobicSport()");
            iBaseCommonCallback.onResponse(4, "Failed to initialize the interface");
        } else if (!gtxVar.u()) {
            ReleaseLogUtil.d("Track_TrackDeveloperKitProxy", "sport has started from foreground");
            iBaseCommonCallback.onResponse(4, "can't stop sport invoked from foreground");
        } else {
            this.c.stopSportEnhance(iBaseCommonCallback);
        }
    }

    @Override // com.huawei.health.ITrackManagerForDeveloper
    public void sendDeviceControlinstruction(String str, IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
        LogUtil.a("Track_TrackDeveloperKitProxy", "enter sendDeviceControlinstruction");
        if (iBaseCommonCallback == null) {
            ReleaseLogUtil.c("Track_TrackDeveloperKitProxy", "sendDeviceControlinstruction callback is null");
            return;
        }
        if (this.g == null) {
            ReleaseLogUtil.d("Track_TrackDeveloperKitProxy", "mSportDataOutputApi is null in sendDeviceControlinstruction()");
            iBaseCommonCallback.onResponse(4, "Failed to initialize the interface");
            return;
        }
        if (Looper.myLooper() == null) {
            Looper.prepare();
        }
        if (!gtx.c(gxf.d()).u()) {
            ReleaseLogUtil.d("Track_TrackDeveloperKitProxy", "sport should be start background");
            iBaseCommonCallback.onResponse(4, "sport should be start background");
        } else {
            b(str, iBaseCommonCallback);
        }
    }

    private void b(String str, IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("Track_TrackDeveloperKitProxy", "sendDeviceControlInstructionImpl controlOptions:", str);
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (!jSONObject.has("model")) {
                this.c.sendDeviceControlinstruction(str, iBaseCommonCallback);
                return;
            }
            int i = jSONObject.getInt("model");
            if (i == 1) {
                this.c.sendDeviceControlinstruction(str, iBaseCommonCallback);
                return;
            }
            if (i != 4) {
                ReleaseLogUtil.d("Track_TrackDeveloperKitProxy", "sendDeviceControlinstructionImpl not supported model:", Integer.valueOf(i));
                iBaseCommonCallback.onResponse(2, ipd.b(2));
                return;
            }
            int intValue = ((Integer) this.g.getData("CADENCE_DATA")).intValue();
            double d = jSONObject.getDouble("grade") * 10000.0d;
            int e2 = (int) bzk.e(b(jSONObject), intValue, d);
            ReleaseLogUtil.e("Track_TrackDeveloperKitProxy", "sendDeviceControlinstructionImpl track resistance cadence:", Integer.valueOf(intValue), " power:", Integer.valueOf(e2), " ratio:", Double.valueOf(d));
            if (!HiHealthKitDataChecker.c(e2)) {
                ReleaseLogUtil.c("Track_TrackDeveloperKitProxy", "The calculated power is invalid.");
                iBaseCommonCallback.onResponse(2, ipd.b(2));
                return;
            }
            JSONObject jSONObject2 = new JSONObject();
            if (intValue > 0) {
                jSONObject2.put("cadence", intValue);
            }
            jSONObject2.put("power", e2);
            this.c.sendDeviceControlinstruction(jSONObject2.toString(), iBaseCommonCallback);
        } catch (JSONException e3) {
            ReleaseLogUtil.c("Track_TrackDeveloperKitProxy", "sendDeviceControlinstructionImpl JSONException:", ExceptionUtils.d(e3));
            iBaseCommonCallback.onResponse(2, ipd.b(2));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:6:0x0025 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private double b(org.json.JSONObject r6) {
        /*
            r5 = this;
            java.lang.String r0 = "weight"
            boolean r1 = r6.has(r0)
            java.lang.String r2 = "Track_TrackDeveloperKitProxy"
            r3 = 0
            if (r1 == 0) goto L20
            double r0 = r6.getDouble(r0)     // Catch: org.json.JSONException -> L12
            goto L21
        L12:
            r6 = move-exception
            java.lang.String r0 = "getWeight JSONException:"
            java.lang.String r6 = r6.getMessage()
            java.lang.Object[] r6 = new java.lang.Object[]{r0, r6}
            health.compact.a.hwlogsmodel.ReleaseLogUtil.c(r2, r6)
        L20:
            r0 = r3
        L21:
            int r6 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r6 <= 0) goto L26
            return r0
        L26:
            double r0 = r5.k
            int r6 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r6 > 0) goto L3f
            android.content.Context r6 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            com.huawei.login.ui.login.LoginInit r6 = com.huawei.login.ui.login.LoginInit.getInstance(r6)
            com.huawei.up.model.UserInfomation r6 = r6.getUserInfoFromDbSync()
            float r6 = r6.getWeightOrDefaultValue()
            double r0 = (double) r6
            r5.k = r0
        L3f:
            java.lang.String r6 = "getWeight user weight:"
            java.lang.Double r3 = java.lang.Double.valueOf(r0)
            java.lang.Object[] r6 = new java.lang.Object[]{r6, r3}
            com.huawei.hwlogsmodel.LogUtil.a(r2, r6)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.developerkit.TrackDeveloperKitProxy.b(org.json.JSONObject):double");
    }

    @Override // com.huawei.health.ITrackManagerForDeveloper
    public void pauseSport(IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("Track_TrackDeveloperKitProxy", "enter pauseSport");
        int n = gtx.c(BaseApplication.getContext()).n();
        if (fhw.b.contains(Integer.valueOf(n))) {
            if (this.g.getStatus() == 1) {
                this.c.pauseSportEnhance(iBaseCommonCallback);
                return;
            } else {
                ReleaseLogUtil.e("HWhealthLinkage_Track_TrackDeveloperKitProxy", "failed to pause sport");
                b(iBaseCommonCallback, 4, ipd.b(4));
                return;
            }
        }
        if (gso.e().k() && fhw.d.contains(Integer.valueOf(n))) {
            LogUtil.a("Track_TrackDeveloperKitProxy", "pauseSport,current sport type is ", Integer.valueOf(n));
            gso.e().v();
            Message obtain = Message.obtain();
            obtain.obj = iBaseCommonCallback;
            obtain.what = 203;
            this.d.sendMessageDelayed(obtain, 3000L);
            return;
        }
        ReleaseLogUtil.c("Track_TrackDeveloperKitProxy", "pauseSport parameter error");
        b(iBaseCommonCallback, 7, ipd.b(7));
    }

    @Override // com.huawei.health.ITrackManagerForDeveloper
    public void resumeSport(IBaseCommonCallback iBaseCommonCallback) throws RemoteException {
        ReleaseLogUtil.e("Track_TrackDeveloperKitProxy", "enter resumeSport");
        int n = gtx.c(BaseApplication.getContext()).n();
        int m = gtx.c(BaseApplication.getContext()).m();
        if (fhw.b.contains(Integer.valueOf(n))) {
            if (this.g.getStatus() == 2) {
                this.c.resumeSportEnhance(iBaseCommonCallback);
                return;
            } else {
                ReleaseLogUtil.e("HWhealthLinkage_Track_TrackDeveloperKitProxy", "failed to resume sport");
                b(iBaseCommonCallback, 4, ipd.b(4));
                return;
            }
        }
        if (m == 2 && fhw.d.contains(Integer.valueOf(n))) {
            LogUtil.a("Track_TrackDeveloperKitProxy", "resume sport, current sport type is ", Integer.valueOf(n));
            gso.e().y();
            Message obtain = Message.obtain();
            obtain.obj = iBaseCommonCallback;
            obtain.what = 204;
            this.d.sendMessageDelayed(obtain, 3000L);
            return;
        }
        ReleaseLogUtil.c("Track_TrackDeveloperKitProxy", "resumeSport parameter error");
        b(iBaseCommonCallback, 7, ipd.b(7));
    }

    public void onServiceDestroy() {
        ReleaseLogUtil.e("Track_TrackDeveloperKitProxy", "onServiceDestroy");
        this.o.kill();
    }

    static class e implements ISportDataCallback, CourseLinkageLifecycle, ISportStateChangeListener, SportLifecycle, SportDataNotify, IInterface {

        /* renamed from: a, reason: collision with root package name */
        private ITrackDataForDeveloper f2217a;
        private int b;
        private TrackDeveloperKitProxy c;
        private SportDataOutputApi e = (SportDataOutputApi) Services.a("SportService", SportDataOutputApi.class);
        private fgm d = new fgm();

        @Override // com.huawei.hwfoundationmodel.trackmodel.ISportDataCallback
        public void onSummary(MotionPathSimplify motionPathSimplify) {
        }

        e(ITrackDataForDeveloper iTrackDataForDeveloper, TrackDeveloperKitProxy trackDeveloperKitProxy) {
            this.f2217a = iTrackDataForDeveloper;
            this.c = trackDeveloperKitProxy;
            SportDataOutputApi sportDataOutputApi = this.e;
            if (sportDataOutputApi != null) {
                this.b = sportDataOutputApi.getSportType();
                b();
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            ITrackDataForDeveloper iTrackDataForDeveloper = this.f2217a;
            if (iTrackDataForDeveloper != null) {
                return iTrackDataForDeveloper.asBinder();
            }
            return null;
        }

        private void b() {
            LogUtil.a("Track_LocalToRemoteProxy", "current sportType is ", Integer.valueOf(this.b));
            this.d.a(d());
            this.d.c("Track_LocalToRemoteProxy");
            this.e.subscribeNotify(this.d, this);
        }

        public fgm e() {
            return this.d;
        }

        private List<String> d() {
            ArrayList arrayList = new ArrayList();
            arrayList.add("TIME_ONE_SECOND_DURATION");
            arrayList.add("CALORIES_DATA");
            arrayList.add("HEART_RATE_DATA");
            arrayList.add("SKIP_NUM_DATA");
            arrayList.add("SKIP_SPEED_DATA");
            arrayList.add("STUMBLING_ROPE_DATA");
            arrayList.add("CONTINUOUS_SKIPPING_JUMP_DATA");
            arrayList.add("SKIPPING_AVG_SPEED_DATA");
            arrayList.add("PROGRESS_DATA");
            arrayList.add("DISTANCE_DATA");
            arrayList.add("SPEED_DATA");
            arrayList.add("RUNNING_POSTURE_DATA");
            arrayList.add("STEP_DATA");
            arrayList.add("STEP_RATE_DATA");
            arrayList.add("DISTANCE_DATA");
            arrayList.add("SPEED_DATA");
            arrayList.add("STEP_DATA");
            arrayList.add("STEP_RATE_DATA");
            arrayList.add("DISTANCE_DATA");
            arrayList.add("SPEED_DATA");
            arrayList.add("CADENCE_DATA");
            arrayList.add("POWER_DATA");
            arrayList.add("RESISTANCE_LEVEL_DATA");
            arrayList.add("DISTANCE_DATA");
            arrayList.add("SPEED_DATA");
            arrayList.add("CADENCE_DATA");
            arrayList.add("POWER_DATA");
            arrayList.add("RESISTANCE_LEVEL_DATA");
            arrayList.add("STEP_DATA");
            arrayList.add("DISTANCE_DATA");
            arrayList.add("SPEED_DATA");
            arrayList.add("PACE_DATA");
            arrayList.add("TEMPO_DATA");
            arrayList.add("POWER_DATA");
            arrayList.add("PADDLE_DATA");
            arrayList.add("WEIGHT_DATA");
            arrayList.add("GROUP_COUNT_DATA");
            arrayList.add("ACTION_GROUP_DATA");
            return arrayList;
        }

        @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ISportStateChangeListener, com.huawei.health.sportservice.SportLifecycle
        public void onStartSport() {
            ReleaseLogUtil.e("Track_LocalToRemoteProxy", "onStartSport ", this.f2217a, " ", this.c);
            if (this.f2217a == null || this.c == null) {
                return;
            }
            LogUtil.a("Track_LocalToRemoteProxy", "onStartSport");
            this.c.j = 0;
            try {
                this.f2217a.onStateChanged(this.c.j);
            } catch (RemoteException | IllegalStateException e) {
                ReleaseLogUtil.c("Track_LocalToRemoteProxy", "onStartSport RemoteException: ", e.getMessage());
            }
            this.c.j = 2;
        }

        @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ISportStateChangeListener, com.huawei.health.sportservice.SportLifecycle
        public void onPauseSport() {
            ReleaseLogUtil.e("Track_LocalToRemoteProxy", "onPauseSport ", this.f2217a, " ", this.c);
            if (this.f2217a == null || this.c == null) {
                return;
            }
            LogUtil.a("Track_LocalToRemoteProxy", "onPauseSport");
            this.c.j = 1;
            try {
                this.f2217a.onStateChanged(1);
            } catch (RemoteException | IllegalStateException e) {
                ReleaseLogUtil.c("Track_LocalToRemoteProxy", "onPauseSport RemoteException: ", e.getMessage());
            }
        }

        @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ISportStateChangeListener, com.huawei.health.sportservice.SportLifecycle
        public void onResumeSport() {
            ReleaseLogUtil.e("Track_LocalToRemoteProxy", "onResumeSport ", this.f2217a, " ", this.c);
            if (this.f2217a == null || this.c == null) {
                return;
            }
            LogUtil.a("Track_LocalToRemoteProxy", "onResumeSport");
            this.c.j = 3;
            try {
                this.f2217a.onStateChanged(this.c.j);
            } catch (RemoteException | IllegalStateException e) {
                ReleaseLogUtil.c("Track_LocalToRemoteProxy", "onResumeSport RemoteException: ", e.getMessage());
            }
            this.c.j = 2;
        }

        @Override // com.huawei.healthcloud.plugintrack.manager.inteface.ISportStateChangeListener, com.huawei.health.sportservice.SportLifecycle
        /* renamed from: onStopSport */
        public void m134x32b3e3a1() {
            ReleaseLogUtil.e("Track_LocalToRemoteProxy", "onStopSport ", this.f2217a, " ", this.c);
            if (this.f2217a == null || this.c == null) {
                return;
            }
            LogUtil.a("Track_LocalToRemoteProxy", "onStopSport");
            this.c.j = 4;
            try {
                this.f2217a.onStateChanged(this.c.j);
            } catch (RemoteException | IllegalStateException e) {
                ReleaseLogUtil.c("Track_LocalToRemoteProxy", "onStopSport RemoteException: ", e.getMessage());
            }
        }

        @Override // com.huawei.hwfoundationmodel.trackmodel.ISportDataCallback
        public void getSportInfo(Bundle bundle) {
            if (this.f2217a == null || this.c.j == 4 || this.c.j == 1) {
                return;
            }
            try {
                this.f2217a.onDataChange(this.c.Dv_(bundle));
            } catch (RemoteException e) {
                ReleaseLogUtil.c("Track_LocalToRemoteProxy", "getSportInfo RemoteException: ", e.getMessage());
            }
        }

        @Override // com.huawei.health.sportservice.SportDataNotify
        public void onChange(List<String> list, Map<String, Object> map) {
            SportDataOutputApi sportDataOutputApi = (SportDataOutputApi) Services.a("SportService", SportDataOutputApi.class);
            this.e = sportDataOutputApi;
            int sportType = sportDataOutputApi.getSportType();
            this.b = sportType;
            getSportInfo(this.c.Dw_(map, sportType));
        }

        @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
        public void start(int i, Bundle bundle) {
            onStartSport();
        }

        @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
        public void resume(int i) {
            onResumeSport();
        }

        @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
        public void pause(int i) {
            onPauseSport();
        }

        @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
        public void stop(int i) {
            m134x32b3e3a1();
        }

        public String c() {
            return "Track_LocalToRemoteProxy";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bundle Dv_(Bundle bundle) {
        if (bundle == null) {
            return bundle;
        }
        Bundle deepCopy = bundle.deepCopy();
        deepCopy.putInt("sportState", this.j);
        return deepCopy;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bundle Dw_(Map<String, Object> map, int i) {
        Bundle bundle = new Bundle();
        if (map != null) {
            LogUtil.a("Track_TrackDeveloperKitProxy", "getBundleInstrumentsData current sportType is", Integer.valueOf(i));
            try {
                bundle.putInt(BleConstants.SPORT_TYPE, i);
                bundle.putInt("duration", Math.round((((Long) map.get("TIME_ONE_SECOND_DURATION")).longValue() * 1.0f) / 1000.0f));
                bundle.putInt("calorie", map.get("CALORIES_DATA") != null ? ((Integer) map.get("CALORIES_DATA")).intValue() * 1000 : -1);
                bundle.putInt(IndoorEquipManagerApi.KEY_HEART_RATE, map.get("HEART_RATE_DATA") != null ? ((Integer) map.get("HEART_RATE_DATA")).intValue() : -1);
                if (i == 264) {
                    DB_(map, bundle);
                } else if (i == 265) {
                    Dy_(map, bundle);
                } else if (i == 273) {
                    Dx_(map, bundle);
                } else if (i == 274) {
                    Dz_(map, bundle);
                } else {
                    if (i != 283) {
                        return bundle;
                    }
                    DA_(map, bundle);
                }
            } catch (ClassCastException unused) {
            }
        }
        return bundle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(IBaseCommonCallback iBaseCommonCallback, int i, String str) {
        if (iBaseCommonCallback != null) {
            try {
                iBaseCommonCallback.onResponse(i, str);
            } catch (RemoteException unused) {
                LogUtil.h("Track_TrackDeveloperKitProxy", "callback remote Exception");
            }
        }
    }

    private void DB_(Map<String, Object> map, Bundle bundle) {
        bundle.putInt("distance", map.get("DISTANCE_DATA") != null ? ((Integer) map.get("DISTANCE_DATA")).intValue() : -1);
        bundle.putInt(MedalConstants.EVENT_STEPS, map.get("STEP_DATA") != null ? ((Integer) map.get("STEP_DATA")).intValue() : -1);
        bundle.putInt("stepRate", map.get("STEP_RATE_DATA") != null ? ((Integer) map.get("STEP_RATE_DATA")).intValue() : -1);
        bundle.putFloat("speed", map.get("SPEED_DATA") != null ? ((Integer) map.get("SPEED_DATA")).intValue() / 360.0f : -1.0f);
        if (map.get("RUNNING_POSTURE_DATA") == null || !(map.get("RUNNING_POSTURE_DATA") instanceof ffs)) {
            return;
        }
        gwl.aVa_(bundle, (ffs) map.get("RUNNING_POSTURE_DATA"));
    }

    private void Dx_(Map<String, Object> map, Bundle bundle) {
        bundle.putInt("distance", map.get("DISTANCE_DATA") != null ? ((Integer) map.get("DISTANCE_DATA")).intValue() : -1);
        bundle.putInt(MedalConstants.EVENT_STEPS, map.get("STEP_DATA") != null ? ((Integer) map.get("STEP_DATA")).intValue() : -1);
        bundle.putFloat("speed", map.get("SPEED_DATA") != null ? ((Integer) map.get("SPEED_DATA")).intValue() / 360.0f : -1.0f);
        bundle.putInt("resistanceLevel", map.get("RESISTANCE_LEVEL_DATA") != null ? ((Integer) map.get("RESISTANCE_LEVEL_DATA")).intValue() : -1);
        bundle.putInt("power", map.get("POWER_DATA") != null ? ((Integer) map.get("POWER_DATA")).intValue() : -1);
        bundle.putInt("cadence", map.get("CADENCE_DATA") != null ? ((Integer) map.get("CADENCE_DATA")).intValue() : -1);
    }

    private void Dz_(Map<String, Object> map, Bundle bundle) {
        bundle.putInt("distance", map.get("DISTANCE_DATA") != null ? ((Integer) map.get("DISTANCE_DATA")).intValue() : -1);
        bundle.putInt("power", map.get("POWER_DATA") != null ? ((Integer) map.get("POWER_DATA")).intValue() : -1);
        bundle.putInt("totalPaddle", map.get("PADDLE_DATA") != null ? ((Integer) map.get("PADDLE_DATA")).intValue() : -1);
        bundle.putInt(BleConstants.AVERAGE_PACE, map.get("PACE_DATA") != null ? ((Integer) map.get("PACE_DATA")).intValue() : -1);
        bundle.putFloat("paddleFrequency", map.get("TEMPO_DATA") != null ? ((Float) map.get("TEMPO_DATA")).floatValue() : -1.0f);
        bundle.putInt("resistanceLevel", map.get("RESISTANCE_LEVEL_DATA") != null ? ((Integer) map.get("RESISTANCE_LEVEL_DATA")).intValue() : -1);
        bundle.putInt("weight", map.get("WEIGHT_DATA") != null ? ((Integer) map.get("WEIGHT_DATA")).intValue() : -1);
        bundle.putInt("totalActionGroups", map.get("ACTION_GROUP_DATA") != null ? ((Integer) map.get("ACTION_GROUP_DATA")).intValue() : -1);
        bundle.putInt("numberOfActionsPerGroup", map.get("GROUP_COUNT_DATA") != null ? ((Integer) map.get("GROUP_COUNT_DATA")).intValue() : -1);
    }

    private void Dy_(Map<String, Object> map, Bundle bundle) {
        bundle.putInt("distance", map.get("DISTANCE_DATA") != null ? ((Integer) map.get("DISTANCE_DATA")).intValue() : -1);
        bundle.putFloat("speed", map.get("SPEED_DATA") != null ? ((Integer) map.get("SPEED_DATA")).intValue() / 360.0f : -1.0f);
        bundle.putInt("resistanceLevel", map.get("RESISTANCE_LEVEL_DATA") != null ? ((Integer) map.get("RESISTANCE_LEVEL_DATA")).intValue() : -1);
        bundle.putInt("power", map.get("POWER_DATA") != null ? ((Integer) map.get("POWER_DATA")).intValue() : -1);
        bundle.putInt("cadence", map.get("CADENCE_DATA") != null ? ((Integer) map.get("CADENCE_DATA")).intValue() : -1);
    }

    private void DA_(Map<String, Object> map, Bundle bundle) {
        bundle.putInt("skippingNum", map.get("SKIP_NUM_DATA") != null ? ((Integer) map.get("SKIP_NUM_DATA")).intValue() : -1);
        bundle.putInt("skippingSpeed", map.get("SKIPPING_AVG_SPEED_DATA") != null ? ((Integer) map.get("SKIPPING_AVG_SPEED_DATA")).intValue() : -1);
    }
}
