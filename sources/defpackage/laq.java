package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.btsportdevice.callback.MessageOrStateCallback;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.IBaseCommonCallback;
import com.huawei.health.device.api.BackgroundSportApi;
import com.huawei.health.device.base.AbstractFitnessClient;
import com.huawei.health.device.datatype.SkippingTargetMode;
import com.huawei.health.device.model.DeviceInformation;
import com.huawei.health.sportservice.SportLaunchParams;
import com.huawei.health.suggestion.CoachController;
import com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle;
import com.huawei.hihealth.CharacteristicConstant;
import com.huawei.hihealth.StartSportParam;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.activity.IndoorEquipConnectedActivity;
import com.huawei.indoorequip.activity.IndoorEquipConnectedManager;
import com.huawei.indoorequip.datastruct.QrCodeOrNfcInfo;
import com.huawei.indoorequip.device.DeviceRemovedListenerManager;
import com.huawei.indoorequip.viewmodel.BackgroundSportViewModel;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.share.HiHealthError;
import defpackage.laq;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

@ApiDefine(uri = BackgroundSportApi.class)
/* loaded from: classes5.dex */
public class laq implements BackgroundSportApi {

    /* renamed from: a, reason: collision with root package name */
    private BackgroundSportViewModel f14728a;
    private IBaseCommonCallback b;
    private BackgroundSportViewModel d;
    private boolean e;
    private Handler f;
    private IBaseCommonCallback h;
    private DeviceInformation i;
    private String j;
    private kzc k;
    private int l;
    private boolean m;
    private IndoorEquipConnectedManager n;
    private boolean o;
    private QrCodeOrNfcInfo p;
    private int q;
    private String r;
    private boolean s;
    private int t;
    private int w;
    private int x;
    private Observer g = new Observer() { // from class: laq.1
        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            ReleaseLogUtil.e("Track_BackgroundSportApiImpl", "DeviceControlObserver notify, the args is " + Arrays.toString(objArr));
            Object obj = objArr[0];
            if (obj instanceof String) {
                Object obj2 = objArr[1];
                if (obj2 instanceof Integer) {
                    int intValue = ((Integer) obj2).intValue();
                    if ("INDOOR_BIKE_RESISTANCE_CONTROL".equals((String) obj)) {
                        LogUtil.a("BackgroundSportApiImpl", "indoor bike resistance control is ", Integer.valueOf(intValue));
                        laq laqVar = laq.this;
                        laqVar.c(intValue, laqVar.h);
                        laq.this.m = true;
                        return;
                    }
                    return;
                }
            }
            ReleaseLogUtil.c("Track_BackgroundSportApiImpl", "the args type is not right");
        }
    };
    private CourseLinkageLifecycle c = new CourseLinkageLifecycle() { // from class: laq.3
        @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
        public void start(int i, Bundle bundle) {
        }

        @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
        public void resume(int i) {
            if (laq.this.d != null) {
                laq.this.d.onResumeSport();
            }
        }

        @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
        public void pause(int i) {
            if (laq.this.d != null) {
                laq.this.d.onPauseSport();
            }
        }

        @Override // com.huawei.health.suggestion.fit.callback.CourseLinkageLifecycle
        public void stop(int i) {
            if (laq.this.d != null) {
                laq.this.d.m134x32b3e3a1();
                laq.this.e = false;
                laq.this.d();
            }
        }
    };

    private boolean a(int i, int i2) {
        return i > 0 && i <= 655 && i2 > 0 && i2 <= 32767;
    }

    @Override // com.huawei.health.device.api.BackgroundSportApi
    public void startSportEnhance(StartSportParam startSportParam, IBaseCommonCallback iBaseCommonCallback) {
        ReleaseLogUtil.e("Track_BackgroundSportApiImpl", "startSportEnhance");
        if (b(iBaseCommonCallback)) {
            if (this.e && (CoachController.d().a() == 2 || CoachController.d().a() == 1)) {
                ReleaseLogUtil.e("Track_BackgroundSportApiImpl", "startSportEnhance is already start");
                d(101, HiHealthError.getErrorMessage(101), iBaseCommonCallback);
                return;
            }
            this.e = true;
            this.b = iBaseCommonCallback;
            this.w = startSportParam.getInt(BleConstants.SPORT_TYPE);
            int i = startSportParam.getInt("isSaveData", CharacteristicConstant.SportRecordSaveModel.SAVE.getValue());
            this.l = i;
            ReleaseLogUtil.e("Track_BackgroundSportApiImpl", "startSportEnhance mIsSaveData: ", Integer.valueOf(i));
            if (fhw.b.contains(Integer.valueOf(this.w))) {
                d(iBaseCommonCallback);
                return;
            }
            c(startSportParam);
            if (this.o) {
                if (this.s) {
                    ReleaseLogUtil.e("Track_BackgroundSportApiImpl", "repeat error");
                    d(101, HiHealthError.getErrorMessage(101), iBaseCommonCallback);
                    return;
                } else {
                    ReleaseLogUtil.e("Track_BackgroundSportApiImpl", "then start sport");
                    c(String.valueOf(this.w));
                    c();
                    d(0, HiHealthError.getErrorMessage(0), iBaseCommonCallback);
                    return;
                }
            }
            ReleaseLogUtil.e("Track_BackgroundSportApiImpl", "device is not connected, start connecting");
            QrCodeOrNfcInfo analysisQrCodeOrNfc = QrCodeOrNfcInfo.analysisQrCodeOrNfc(b());
            this.p = analysisQrCodeOrNfc;
            if (analysisQrCodeOrNfc != null) {
                analysisQrCodeOrNfc.setDataSource(1);
            }
            this.k = kzc.n();
            this.i = new DeviceInformation();
            e();
            if (this.j.equals("262")) {
                return;
            }
            e(b());
        }
    }

    private void d(IBaseCommonCallback iBaseCommonCallback) {
        LogUtil.a("BackgroundSportApiImpl", "start sport ", Integer.valueOf(this.w), " with wear");
        if (this.d != null) {
            LogUtil.a("BackgroundSportApiImpl", "mBackgroundWearSportViewModel != null");
        } else {
            LogUtil.a("BackgroundSportApiImpl", "mBackgroundWearSportViewModel == null");
            this.d = new BackgroundSportViewModel();
            SportLaunchParams sportLaunchParams = new SportLaunchParams();
            sportLaunchParams.setSportType(this.w);
            sportLaunchParams.addExtra("map_tracking_sport_type_sportting", Integer.valueOf(this.w));
            sportLaunchParams.addExtra("KEY_IS_VOICE_ENABLE", false);
            sportLaunchParams.addExtra("keySportRecordIsToSave", Integer.valueOf(this.l));
            CoachController.d().d(CoachController.StatusSource.NEW_LINK_WEAR, this.c);
            this.d.d(sportLaunchParams);
            this.d.requestData();
            this.d.onPreSport();
        }
        this.d.onStartSport();
        d(0, "start sport by wear successfully", iBaseCommonCallback);
    }

    private boolean b(IBaseCommonCallback iBaseCommonCallback) {
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter == null) {
            ReleaseLogUtil.d("Track_BackgroundSportApiImpl", "bluetoothAdapter is null");
            d(1005, HiHealthError.getErrorMessage(1005), iBaseCommonCallback);
            return false;
        }
        if (defaultAdapter.isEnabled()) {
            return true;
        }
        ReleaseLogUtil.d("Track_BackgroundSportApiImpl", "bluetooth is not enable");
        d(1005, HiHealthError.getErrorMessage(1005), iBaseCommonCallback);
        return false;
    }

    private String b() {
        StringBuilder sb = new StringBuilder(16);
        sb.append("ftmp=");
        sb.append(this.x);
        sb.append("&t=");
        sb.append(this.j);
        sb.append("&ble=");
        sb.append(this.r);
        sb.append("&stype=");
        sb.append(this.w);
        return sb.toString();
    }

    private void e(String str) {
        ReleaseLogUtil.e("BackgroundSportApiImpl", "connectBtByNfc");
        if (lbv.a()) {
            this.k.j(false);
            this.n.b(str);
        }
    }

    private void e() {
        LogUtil.a("BackgroundSportApiImpl", "initConnectedManager");
        if (this.n == null) {
            if (Looper.myLooper() == null) {
                Looper.prepare();
            }
            if (this.f == null) {
                this.f = new Handler();
            }
            IndoorEquipConnectedManager indoorEquipConnectedManager = new IndoorEquipConnectedManager(BaseApplication.e(), new IndoorEquipConnectedManager.OnNfcConnectListener() { // from class: laq.5
                @Override // com.huawei.indoorequip.activity.IndoorEquipConnectedManager.OnNfcConnectListener
                public void onStartTimeChange(long j) {
                    laq.this.b(j);
                }

                @Override // com.huawei.indoorequip.activity.IndoorEquipConnectedManager.OnNfcConnectListener
                public void onQrCodeOrNfcInfoSet(QrCodeOrNfcInfo qrCodeOrNfcInfo) {
                    laq.this.b(qrCodeOrNfcInfo);
                }
            }, this.f);
            this.n = indoorEquipConnectedManager;
            indoorEquipConnectedManager.init();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(QrCodeOrNfcInfo qrCodeOrNfcInfo) {
        if (this.f14728a != null) {
            LogUtil.a("BackgroundSportApiImpl", "mBackgroundSportViewModel setQrCodeOrNfcInfo");
            this.f14728a.e(qrCodeOrNfcInfo);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(long j) {
        if (this.f14728a != null) {
            LogUtil.a("BackgroundSportApiImpl", "mBackgroundSportViewModel set time");
            this.f14728a.a(j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogUtil.a("BackgroundSportApiImpl", "initViewModel");
        if (this.f14728a != null) {
            ReleaseLogUtil.e("BackgroundSportApiImpl", "mBackgroundSportViewModel != null");
            return;
        }
        this.f14728a = new BackgroundSportViewModel();
        b(this.p);
        int a2 = lbv.a(this.j);
        LogUtil.a("BackgroundSportApiImpl", "initViewModel with sport type: ", Integer.valueOf(a2));
        SportLaunchParams sportLaunchParams = new SportLaunchParams();
        sportLaunchParams.setSportType(lbv.a(this.j));
        sportLaunchParams.addExtra("deviceMac", this.r);
        sportLaunchParams.addExtra("map_tracking_sport_type_sportting", Integer.valueOf(a2));
        sportLaunchParams.addExtra("KEY_IS_VOICE_ENABLE", false);
        sportLaunchParams.addExtra("keySportRecordIsToSave", Integer.valueOf(this.l));
        if (this.j.equals("262")) {
            sportLaunchParams.setSportTarget(this.q);
            sportLaunchParams.setTrackType(this.q);
            sportLaunchParams.setTargetValue(this.t);
        }
        this.f14728a.d(sportLaunchParams);
        this.f14728a.requestData();
        this.f14728a.onPreSport();
        if (this.w == 265) {
            lau.d().a(true);
        }
    }

    private void c(StartSportParam startSportParam) {
        LogUtil.a("BackgroundSportApiImpl", "initParam");
        this.x = startSportParam.getInt("ftmp");
        this.j = String.valueOf(startSportParam.getInt("deviceType"));
        this.r = startSportParam.getString("macAddress");
        boolean z = startSportParam.getBoolean("isConnectSportDevice");
        this.s = z;
        ReleaseLogUtil.e("BackgroundSportApiImpl", "isConnectSportDevice = ", Boolean.valueOf(z));
        if (this.j.equals("262")) {
            dds c = dds.c();
            c.g();
            c.a(true, this.r);
            this.q = startSportParam.getInt("ropeStartMode");
            this.t = startSportParam.getInt("ropeGoal");
            if (this.q == 6) {
                this.t = 0;
            }
            c.c(new SkippingTargetMode(this.q, this.t, null));
            c.e("BackgroundSportApiImpl", new MessageOrStateCallback() { // from class: laq.4
                @Override // com.huawei.btsportdevice.callback.MessageOrStateCallback
                public void onNewMessage(int i, Bundle bundle) {
                    laq.this.bVf_(i, bundle);
                }

                @Override // com.huawei.btsportdevice.callback.MessageOrStateCallback
                public void onStateChange(String str) {
                    ReleaseLogUtil.e("BackgroundSportApiImpl", "rope_state ", str);
                    if (AbstractFitnessClient.ACTION_READ_SUCCESS.equals(str)) {
                        laq.this.c();
                        laq.this.f14728a.onStartSport();
                    }
                }
            }, false);
            return;
        }
        lau.d().f();
        lau.d().e(getClass().getSimpleName(), new AnonymousClass2());
    }

    /* renamed from: laq$2, reason: invalid class name */
    class AnonymousClass2 implements MessageOrStateCallback {
        AnonymousClass2() {
        }

        @Override // com.huawei.btsportdevice.callback.MessageOrStateCallback
        public void onNewMessage(int i, Bundle bundle) {
            laq.this.bVf_(i, bundle);
        }

        @Override // com.huawei.btsportdevice.callback.MessageOrStateCallback
        public void onStateChange(String str) {
            ReleaseLogUtil.e("BackgroundSportApiImpl", "state ", str);
            laq laqVar = laq.this;
            laqVar.a(str, laqVar.b);
            if (AbstractFitnessClient.ACTION_READ_SUCCESS.equals(str)) {
                ReleaseLogUtil.e("BackgroundSportApiImpl", "connected");
                laq.this.o = true;
                if (!laq.this.s) {
                    laq laqVar2 = laq.this;
                    laqVar2.c(String.valueOf(laqVar2.w));
                    laq.this.c();
                    laq.this.s = false;
                } else {
                    ReleaseLogUtil.e("BackgroundSportApiImpl", "no need to start sport");
                }
                DeviceRemovedListenerManager.c().e(laq.this.r, new DeviceRemovedListenerManager.IDeviceRemovedListener() { // from class: lav
                    @Override // com.huawei.indoorequip.device.DeviceRemovedListenerManager.IDeviceRemovedListener
                    public final void onDeviceRemoved(String str2) {
                        laq.AnonymousClass2.this.e(str2);
                    }
                });
                return;
            }
            LogUtil.a("BackgroundSportApiImpl", "not connected");
        }

        /* synthetic */ void e(String str) {
            ReleaseLogUtil.c("BackgroundSportApiImpl", "Connected device removed, stop sport");
            if (laq.this.b != null) {
                laq.this.d(150, HiHealthError.getErrorMessage(150), laq.this.b);
            }
            laq.this.stopSportEnhance(null);
        }
    }

    @Override // com.huawei.health.device.api.BackgroundSportApi
    public void stopSportEnhance(IBaseCommonCallback iBaseCommonCallback) {
        ReleaseLogUtil.e("BackgroundSportApiImpl", "stopSportEnhance");
        this.e = false;
        d();
        if (iBaseCommonCallback != null) {
            try {
                iBaseCommonCallback.onResponse(0, "success");
            } catch (RemoteException e) {
                ReleaseLogUtil.c("BackgroundSportApiImpl", "stopSportEnhance RemoteException: ", e.getMessage());
                return;
            }
        }
        gtx.c(BaseApplication.e()).a(0);
    }

    @Override // com.huawei.health.device.api.BackgroundSportApi
    public void pauseSportEnhance(IBaseCommonCallback iBaseCommonCallback) {
        BackgroundSportViewModel backgroundSportViewModel = this.d;
        if (backgroundSportViewModel != null) {
            backgroundSportViewModel.onPauseSport();
            d(0, "pause sport successfully", iBaseCommonCallback);
        } else {
            d(4, "api state exception", iBaseCommonCallback);
        }
    }

    @Override // com.huawei.health.device.api.BackgroundSportApi
    public void resumeSportEnhance(IBaseCommonCallback iBaseCommonCallback) {
        BackgroundSportViewModel backgroundSportViewModel = this.d;
        if (backgroundSportViewModel != null) {
            backgroundSportViewModel.onResumeSport();
            d(0, "resume sport successfully", iBaseCommonCallback);
        } else {
            d(4, "api state exception", iBaseCommonCallback);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (this.f14728a != null) {
            ReleaseLogUtil.e("BackgroundSportApiImpl", "mBackgroundSportViewModel releaseResource");
            this.f14728a.m134x32b3e3a1();
            this.f14728a.unregisterAll();
            this.f14728a.a(true);
            this.f14728a = null;
        }
        if (this.d != null) {
            ReleaseLogUtil.e("BackgroundSportApiImpl", "mBackgroundWearSportViewModel releaseResource");
            this.d.m134x32b3e3a1();
            this.d.unregisterAll();
            this.d.a(true);
            this.d = null;
        }
        Handler handler = this.f;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.f = null;
        }
        if ("262".equals(this.j)) {
            dds.c().h();
        } else if (!TextUtils.isEmpty(this.j)) {
            if (this.w == 265) {
                lau.d().a(false);
            }
            lau.d().o();
        } else {
            ReleaseLogUtil.e("BackgroundSportApiImpl", "wear app sport");
        }
        this.o = false;
        DeviceRemovedListenerManager.c().a(this.r);
        Observer observer = this.g;
        if (observer != null) {
            ObserverManagerUtil.e(observer, "DEVICE_CONTROL");
        }
        ReleaseLogUtil.e("BackgroundSportApiImpl", "reset connect status");
        this.s = false;
        IndoorEquipConnectedActivity.e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void a(String str, IBaseCommonCallback iBaseCommonCallback) {
        char c;
        LogUtil.a("BackgroundSportApiImpl", "StateChangeInCallback :", str);
        str.hashCode();
        switch (str.hashCode()) {
            case -2027013399:
                if (str.equals(AbstractFitnessClient.ACTION_PAIR_UNSUPPORTED)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -912943761:
                if (str.equals(AbstractFitnessClient.ACTION_GATT_STATE_CONNECTED)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -780096011:
                if (str.equals(AbstractFitnessClient.ACTION_GATT_STATE_DISCONNECTED)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -697798346:
                if (str.equals(AbstractFitnessClient.ACTION_INVALID_DEVICE_INFO)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -536360164:
                if (str.equals(AbstractFitnessClient.ACTION_GATT_STATE_RECONNECTED)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 91311644:
                if (str.equals(AbstractFitnessClient.ACTION_SERVICE_DISCOVERIED)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 235334345:
                if (str.equals(AbstractFitnessClient.ACTION_SERVICE_REDISCOVERIED)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 325932403:
                if (str.equals(AbstractFitnessClient.ACTION_READ_SUCCESS)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 552708325:
                if (str.equals(AbstractFitnessClient.ACTION_GATT_STATE_RECONNECTING)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 588127911:
                if (str.equals(AbstractFitnessClient.ACTION_FAILED_UNLOCK_BT_MODULE)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 1400671776:
                if (str.equals(AbstractFitnessClient.ACTION_SERVICE_NOT_SUPPORT)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 1586831660:
                if (str.equals(AbstractFitnessClient.ACTION_GATT_STATE_DISCONNECTING)) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 1763518706:
                if (str.equals(AbstractFitnessClient.ACTION_GATT_STATE_CONNECTING)) {
                    c = '\f';
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                c(iBaseCommonCallback);
                break;
            case 1:
            case '\b':
            case '\f':
                this.k.c(str);
                break;
            case 2:
                this.k.c(str);
                d("DisConnect");
                c(iBaseCommonCallback);
                break;
            case 3:
                a(iBaseCommonCallback, "InvalidDIS");
                break;
            case 4:
                this.k.c(AbstractFitnessClient.ACTION_GATT_STATE_CONNECTED);
                break;
            case 5:
                this.k.c(str);
                d("DiscoverServSuccess");
                break;
            case 6:
                this.k.c(AbstractFitnessClient.ACTION_SERVICE_DISCOVERIED);
                d("ReDiscoverServSuccess");
                break;
            case 7:
                d(0, "connect success", iBaseCommonCallback);
                break;
            case '\t':
                a(iBaseCommonCallback, "UnlockFailed");
                break;
            case '\n':
                a(iBaseCommonCallback, "ServiceUnsupport");
                break;
            case 11:
                this.k.c(str);
                c(iBaseCommonCallback);
                break;
            default:
                LogUtil.a("BackgroundSportApiImpl", "onStateChange(call back in activity), state is unknow (it is ", str, ").");
                break;
        }
    }

    private void a(IBaseCommonCallback iBaseCommonCallback, String str) {
        d(str);
        a();
        c(iBaseCommonCallback);
    }

    private void a() {
        ReleaseLogUtil.e("BackgroundSportApiImpl", "disconnect, hasFoundServiceSuccessBefore is ", Boolean.valueOf(this.k.s()));
        this.n.d(this.k.s());
    }

    private void c(IBaseCommonCallback iBaseCommonCallback) {
        ReleaseLogUtil.d("BackgroundSportApiImpl", "connect fail");
        this.e = false;
        this.o = false;
        d(4, "connect fail", iBaseCommonCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, String str, IBaseCommonCallback iBaseCommonCallback) {
        if (iBaseCommonCallback != null) {
            try {
                iBaseCommonCallback.onResponse(i, str);
            } catch (RemoteException e) {
                LogUtil.b("BackgroundSportApiImpl", "onResponse RemoteException", e.getMessage());
            }
        }
    }

    private void d(String str) {
        QrCodeOrNfcInfo qrCodeOrNfcInfo = this.p;
        String str2 = Constants.LINK;
        String btMac = qrCodeOrNfcInfo != null ? qrCodeOrNfcInfo.getBtMac() : Constants.LINK;
        lao laoVar = new lao();
        laoVar.i(str);
        laoVar.g(lau.d().g() + "");
        laoVar.j((this.p == null || lau.d().g()) ? Constants.LINK : this.p.getBtName());
        DeviceInformation deviceInformation = this.i;
        laoVar.a(deviceInformation != null ? Integer.toString(deviceInformation.getDeviceType()) : Constants.LINK);
        DeviceInformation deviceInformation2 = this.i;
        laoVar.b(deviceInformation2 != null ? deviceInformation2.getManufacturerString() : Constants.LINK);
        DeviceInformation deviceInformation3 = this.i;
        if (deviceInformation3 != null) {
            str2 = deviceInformation3.getModelString();
        }
        laoVar.e(str2);
        laoVar.c(btMac);
        lbv.b(BaseApplication.e(), laoVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bVf_(int i, Bundle bundle) {
        if (i == 305) {
            LogUtil.a("BackgroundSportApiImpl", "in handleMessage, case is MSG_BT_SERVICE_DISCOVER_SHOW");
            this.k.d(true);
        } else if (i == 309) {
            LogUtil.a("BackgroundSportApiImpl", "in handleMessage, case is MSG_BT_SERVICE_REDISCOVER_SHOW");
            this.k.d(true);
        } else {
            if (i != 902) {
                return;
            }
            bVe_(bundle);
        }
    }

    private void bVe_(Bundle bundle) {
        if (bundle.containsKey("com.huawei.health.fitness.KEY_MESSAGE_FOR_CALLBACK_FITNESS_DATA")) {
            Serializable serializable = bundle.getSerializable("com.huawei.health.fitness.KEY_MESSAGE_FOR_CALLBACK_FITNESS_DATA");
            if (serializable instanceof HashMap) {
                for (Map.Entry entry : ((HashMap) serializable).entrySet()) {
                    if (entry != null && entry.getKey() != null) {
                        d((Integer) entry.getKey(), entry.getValue());
                    }
                }
            }
        }
    }

    private void d(Integer num, Object obj) {
        switch (num.intValue()) {
            case 20005:
                this.i.setDeviceType(((Integer) obj).intValue());
                break;
            case 20006:
                this.i.setManufacturerString(obj.toString());
                break;
            case 20007:
                this.i.setModelString(obj.toString());
                break;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str) {
        LogUtil.a("BackgroundSportApiImpl", "sendModeTypeToRower modeType = ", str);
        if ("290".equals(str)) {
            lau.d().b(21, new int[]{3});
        } else if ("291".equals(str)) {
            LogUtil.a("BackgroundSportApiImpl", "sportType is SPORT_TYPE_ROWERSTRENGTH ");
            lau.d().b(21, new int[]{4});
        } else {
            LogUtil.a("BackgroundSportApiImpl", "sportType is other type ");
        }
    }

    @Override // com.huawei.health.device.api.BackgroundSportApi
    public void sendDeviceControlinstruction(String str, final IBaseCommonCallback iBaseCommonCallback) {
        LogUtil.a("BackgroundSportApiImpl", "enter sendDeviceControlinstruction, controlOptions", str);
        this.h = iBaseCommonCallback;
        this.m = false;
        try {
            JSONObject jSONObject = new JSONObject(str);
            int optInt = jSONObject.optInt("cadence", -1);
            int optInt2 = jSONObject.optInt("power", -1);
            if (optInt == -1) {
                optInt = 60;
            }
            if (!a(optInt, optInt2)) {
                d(2, "param invalid", iBaseCommonCallback);
                return;
            }
            ObserverManagerUtil.e(this.g, "DEVICE_CONTROL");
            ObserverManagerUtil.d(this.g, "DEVICE_CONTROL");
            boolean e = lau.d().e(optInt, optInt2);
            LogUtil.a("BackgroundSportApiImpl", "sendDeviceControlinstruction result:", Boolean.valueOf(e));
            if (!e) {
                d(HiHealthError.ERR_WRONG_DEVICE, "Failed to send the command", iBaseCommonCallback);
            } else {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: las
                    @Override // java.lang.Runnable
                    public final void run() {
                        laq.this.e(iBaseCommonCallback);
                    }
                }, 5000L);
            }
        } catch (JSONException e2) {
            ReleaseLogUtil.c("BackgroundSportApiImpl", "occur JSONException:", ExceptionUtils.d(e2));
            d(2, "param invalid", iBaseCommonCallback);
        }
    }

    /* synthetic */ void e(IBaseCommonCallback iBaseCommonCallback) {
        LogUtil.a("BackgroundSportApiImpl", "mIsDeviceControlHadCallback = ", Boolean.valueOf(this.m));
        if (this.m) {
            return;
        }
        LogUtil.a("BackgroundSportApiImpl", "Fail, response time out");
        d(HiHealthError.ERR_WRONG_DEVICE, "Fail, response time out", iBaseCommonCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, IBaseCommonCallback iBaseCommonCallback) {
        if (i == 1) {
            d(0, "Success", iBaseCommonCallback);
        } else {
            d(1, "Fail", iBaseCommonCallback);
        }
    }
}
