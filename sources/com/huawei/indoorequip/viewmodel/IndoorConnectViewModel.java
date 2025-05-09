package com.huawei.indoorequip.viewmodel;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.basesport.viewmodel.BaseSportingViewModel;
import com.huawei.health.device.api.EcologyDeviceApi;
import com.huawei.health.device.base.AbstractFitnessClient;
import com.huawei.health.device.model.DeviceInformation;
import com.huawei.health.sportservice.ISportDataSaveStatusCallback;
import com.huawei.health.sportservice.SportDataNotify;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.indoorequip.activity.IndoorEquipConnectedManager;
import com.huawei.indoorequip.datastruct.QrCodeOrNfcInfo;
import com.huawei.indoorequip.datastruct.SupportDataRange;
import com.huawei.indoorequip.viewmodel.IndoorConnectViewModel;
import com.huawei.openalliance.ad.constant.Constants;
import defpackage.cei;
import defpackage.gso;
import defpackage.gwa;
import defpackage.gwk;
import defpackage.gww;
import defpackage.hab;
import defpackage.kzc;
import defpackage.lao;
import defpackage.lau;
import defpackage.lbc;
import defpackage.lbd;
import defpackage.lbv;
import defpackage.mxb;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class IndoorConnectViewModel extends BaseSportingViewModel implements SportDataNotify, Handler.Callback {
    protected IndoorEquipConnectedManager c;
    protected kzc e;
    protected String f;
    protected SupportDataRange h;
    protected QrCodeOrNfcInfo i;
    protected Handler k;
    private lbd s;
    private c u;
    protected DeviceInformation d = null;

    /* renamed from: a, reason: collision with root package name */
    protected long f6441a = 0;
    protected int j = 0;
    protected long n = 0;
    protected volatile boolean b = false;
    protected boolean g = false;
    private int w = 0;
    private boolean l = false;
    private int q = -1;
    private float y = 0.0f;
    private boolean o = true;
    private volatile boolean r = false;
    private boolean p = true;
    private boolean m = false;
    private final Runnable t = new Runnable() { // from class: lcg
        @Override // java.lang.Runnable
        public final void run() {
            IndoorConnectViewModel.this.g();
        }
    };

    public interface DataChangeHandle<T> {
        void changeHandle(T t, Map<String, Object> map);
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        return false;
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public void initFragment() {
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public void initReceivedDataHandlers() {
    }

    public /* synthetic */ void g() {
        if (this.k == null || this.mSportDataOutputApi == null) {
            LogUtil.a("Track_IndoorConnectViewModel", "mShowSportDetailRunnable run failed");
            return;
        }
        LogUtil.a("Track_IndoorConnectViewModel", "mShowSportDetailRunnable running");
        this.mSportDataOutputApi.unregisterSportDataSavedCallback("Track_IndoorConnectViewModel");
        o();
        this.k.sendEmptyMessage(908);
    }

    public void bVY_(Bundle bundle, Handler handler) {
        if (bundle != null) {
            bundle.putInt("sport_data_source_sportting", 5);
            this.l = bundle.getBoolean("ExitApp", false);
            this.q = bundle.getInt("sport_target_type_sportting", -1);
            this.y = bundle.getFloat("sport_target_value_sportting", 0.0f);
        }
        LogUtil.c("Track_IndoorConnectViewModel", "init ExitApp ", Boolean.valueOf(this.l));
        this.k = handler;
        this.e = kzc.n();
        DeviceInformation e = lau.d().e();
        if (e != null) {
            this.d = e;
        } else {
            this.d = new DeviceInformation();
        }
        LogUtil.a("Track_IndoorConnectViewModel", "init: mDeviceInformation = ", this.d.getFitnessHashMap());
        this.i = new QrCodeOrNfcInfo();
        this.h = new SupportDataRange();
        this.s = lbd.d(BaseApplication.e());
        super.init(bundle);
        this.mSportDataOutputApi.registerDeviceDataCallback(this.e.l());
        requestData();
    }

    public DeviceInformation c() {
        return this.d;
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel, com.huawei.health.sportservice.SportLifecycle
    public void onPreSport() {
        lbd lbdVar = this.s;
        if (lbdVar != null) {
            lbdVar.b();
        }
        super.onPreSport();
    }

    public void bVX_(int i, Bundle bundle) {
        if (i == 302) {
            LogUtil.a("Track_IndoorConnectViewModel", "in handleMessage, case is MSG_BT_CONNECTED_SHOW");
            return;
        }
        if (i == 309) {
            LogUtil.a("Track_IndoorConnectViewModel", "in handleMessage, case is MSG_BT_SERVICE_REDISCOVER_SHOW");
            this.e.d(true);
            return;
        }
        if (i == 905) {
            LogUtil.a("Track_IndoorConnectViewModel", "in handleMessage, case is FINISH_THIS_SEGMENT");
            bVV_(bundle);
            return;
        }
        if (i == 912) {
            LogUtil.a("Track_IndoorConnectViewModel", "in handleMessage, case is RESPONSE_CONTROL_RESULT");
            bVW_(bundle);
            return;
        }
        if (i == 305) {
            LogUtil.a("Track_IndoorConnectViewModel", "in handleMessage, case is MSG_BT_SERVICE_DISCOVER_SHOW");
            this.e.d(true);
            return;
        }
        if (i == 306) {
            l();
            return;
        }
        if (i == 901) {
            LogUtil.a("Track_IndoorConnectViewModel", "in onNewMessage, newCase is INIT_ALL_VARS_IN_SERVICE");
            this.k.sendEmptyMessage(509);
            return;
        }
        if (i == 902) {
            bVU_(bundle);
            t();
            if (!this.p || lau.d().g()) {
                return;
            }
            LogUtil.a("Track_IndoorConnectViewModel", "in DATA_AVAILABLE Csafe, case is ACTION_READ_SUCCESS");
            this.k.sendEmptyMessage(617);
            this.p = false;
            return;
        }
        this.k.sendEmptyMessage(i);
    }

    private void t() {
        DeviceInformation deviceInformation = this.d;
        if (deviceInformation == null || this.k == null || deviceInformation.getDeviceType() == 0 || TextUtils.isEmpty(this.d.getModelString()) || TextUtils.isEmpty(this.d.getManufacturerString())) {
            return;
        }
        Message obtainMessage = this.k.obtainMessage();
        obtainMessage.what = 1007;
        obtainMessage.obj = this.d;
        this.k.sendMessage(obtainMessage);
    }

    private void bVU_(Bundle bundle) {
        if (bundle.containsKey("com.huawei.health.fitness.KEY_MESSAGE_FOR_CALLBACK_FITNESS_DATA")) {
            Serializable serializable = bundle.getSerializable("com.huawei.health.fitness.KEY_MESSAGE_FOR_CALLBACK_FITNESS_DATA");
            if (serializable instanceof HashMap) {
                for (Map.Entry entry : ((HashMap) serializable).entrySet()) {
                    if (entry != null && entry.getKey() != null && entry.getValue() != null) {
                        e((Integer) entry.getKey(), entry.getValue());
                    }
                }
            }
        }
    }

    private void e(Integer num, Object obj) {
        int intValue = num.intValue();
        if (intValue == 10001) {
            int intValue2 = ((Integer) obj).intValue();
            this.w = intValue2;
            LogUtil.a("Track_IndoorConnectViewModel", "convertDataFromBle INCREMENT_LEVEL :", Integer.valueOf(intValue2));
            if (this.o) {
                this.o = false;
                s();
                return;
            }
            return;
        }
        if (intValue == 20010) {
            this.d.setSerialNumber(obj.toString());
            LogUtil.a("Track_IndoorConnectViewModel", "convertDataFromBle BLE_MANUFACTURER = ", CommonUtil.l(obj.toString()));
            return;
        }
        if (intValue != 20014) {
            switch (intValue) {
                case 20005:
                    this.d.setDeviceType(((Integer) obj).intValue());
                    LogUtil.a("Track_IndoorConnectViewModel", "convertDataFromBle BLE_DEVICE_TYPE = ", obj);
                    break;
                case 20006:
                    this.d.setManufacturerString(obj.toString());
                    LogUtil.a("Track_IndoorConnectViewModel", "convertDataFromBle BLE_MANUFACTURER = ", num.toString());
                    break;
                case 20007:
                    LogUtil.a("Track_IndoorConnectViewModel", "convertDataFromBle BLE_MODEL :", obj.toString());
                    this.d.setModelString(obj.toString());
                    break;
                default:
                    switch (intValue) {
                        case 30006:
                            if (obj instanceof Integer) {
                                Integer num2 = (Integer) obj;
                                LogUtil.a("Track_IndoorConnectViewModel", "convertDataFromBle MIN_LEVEL", Integer.valueOf(num2.intValue()));
                                this.h.setMinLevel(num2.intValue());
                                break;
                            }
                            break;
                        case 30007:
                            if (obj instanceof Integer) {
                                Integer num3 = (Integer) obj;
                                LogUtil.a("Track_IndoorConnectViewModel", "convertDataFromBle MAX_LEVEL", Integer.valueOf(num3.intValue()));
                                this.h.setMaxLevel(num3.intValue());
                                break;
                            }
                            break;
                        case 30008:
                            Integer num4 = (Integer) obj;
                            LogUtil.a("Track_IndoorConnectViewModel", "convertDataFromBle INCREMENT_LEVEL :", Integer.valueOf(num4.intValue()));
                            this.h.setMinIncrementLevel(num4.intValue());
                            break;
                    }
            }
            return;
        }
        LogUtil.a("Track_IndoorConnectViewModel", "convertDataFromBle BLE_DEVICE_NAME = ", obj.toString());
        this.d.setBleDeviceName(obj.toString());
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void c(String str) {
        char c2;
        LogUtil.a("Track_IndoorConnectViewModel", "StateChangeInCallback :", str);
        a(str);
        str.hashCode();
        switch (str.hashCode()) {
            case -2027013399:
                if (str.equals(AbstractFitnessClient.ACTION_PAIR_UNSUPPORTED)) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -1080260320:
                if (str.equals(AbstractFitnessClient.ACTION_REQUEST_MACHINE_CONTROL_SUCCESS)) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case -697798346:
                if (str.equals(AbstractFitnessClient.ACTION_INVALID_DEVICE_INFO)) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 91311644:
                if (str.equals(AbstractFitnessClient.ACTION_SERVICE_DISCOVERIED)) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 235334345:
                if (str.equals(AbstractFitnessClient.ACTION_SERVICE_REDISCOVERIED)) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 325932403:
                if (str.equals(AbstractFitnessClient.ACTION_READ_SUCCESS)) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            case 588127911:
                if (str.equals(AbstractFitnessClient.ACTION_FAILED_UNLOCK_BT_MODULE)) {
                    c2 = 6;
                    break;
                }
                c2 = 65535;
                break;
            case 1400671776:
                if (str.equals(AbstractFitnessClient.ACTION_SERVICE_NOT_SUPPORT)) {
                    c2 = 7;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        switch (c2) {
            case 0:
                LogUtil.a("Track_IndoorConnectViewModel", "onStateChange(call back in activity), state is ACTION_PAIR_UNSUPPORTED");
                this.k.sendEmptyMessage(313);
                break;
            case 1:
                LogUtil.a("Track_IndoorConnectViewModel", "in disposeOnStateChangeInCallback, case is ACTION_REQUEST_MACHINE_CONTROL_SUCCESS");
                break;
            case 2:
                LogUtil.a("Track_IndoorConnectViewModel", "onStateChange(call back in activity), state is ACTION_INVALID_DEVICE_INFO");
                this.k.sendEmptyMessage(306);
                d("InvalidDIS");
                break;
            case 3:
                b(str);
                break;
            case 4:
                this.e.c(AbstractFitnessClient.ACTION_SERVICE_DISCOVERIED);
                LogUtil.a("Track_IndoorConnectViewModel", "onStateChange(call back in activity), state is ACTION_SERVICE_REDISCOVERIED");
                this.k.sendEmptyMessage(309);
                d("ReDiscoverServSuccess");
                break;
            case 5:
                ReleaseLogUtil.e("R_Track_IndoorConnectViewModel", "in disposeOnStateChangeInCallback, case is ACTION_READ_SUCCESS");
                this.k.sendEmptyMessage(617);
                q();
                h();
                break;
            case 6:
                LogUtil.a("Track_IndoorConnectViewModel", "onStateChange(call back in activity), state is ACTION_FAILED_UNLOCK_BT_MODULE");
                l();
                this.k.sendEmptyMessage(306);
                d("UnlockFailed");
                break;
            case 7:
                LogUtil.a("Track_IndoorConnectViewModel", "onStateChange(call back in activity), state is ACTION_SERVICE_NOT_SUPPORT");
                this.k.sendEmptyMessage(306);
                d("ServiceUnsupport");
                break;
            default:
                LogUtil.a("Track_IndoorConnectViewModel", "onStateChange(call back in activity), state is unknow (it is ", str, ").");
                break;
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void a(String str) {
        char c2;
        str.hashCode();
        switch (str.hashCode()) {
            case -912943761:
                if (str.equals(AbstractFitnessClient.ACTION_GATT_STATE_CONNECTED)) {
                    c2 = 0;
                    break;
                }
                c2 = 65535;
                break;
            case -780096011:
                if (str.equals(AbstractFitnessClient.ACTION_GATT_STATE_DISCONNECTED)) {
                    c2 = 1;
                    break;
                }
                c2 = 65535;
                break;
            case -536360164:
                if (str.equals(AbstractFitnessClient.ACTION_GATT_STATE_RECONNECTED)) {
                    c2 = 2;
                    break;
                }
                c2 = 65535;
                break;
            case 552708325:
                if (str.equals(AbstractFitnessClient.ACTION_GATT_STATE_RECONNECTING)) {
                    c2 = 3;
                    break;
                }
                c2 = 65535;
                break;
            case 1586831660:
                if (str.equals(AbstractFitnessClient.ACTION_GATT_STATE_DISCONNECTING)) {
                    c2 = 4;
                    break;
                }
                c2 = 65535;
                break;
            case 1763518706:
                if (str.equals(AbstractFitnessClient.ACTION_GATT_STATE_CONNECTING)) {
                    c2 = 5;
                    break;
                }
                c2 = 65535;
                break;
            default:
                c2 = 65535;
                break;
        }
        if (c2 == 0) {
            ReleaseLogUtil.e("R_Track_IndoorConnectViewModel", "onStateChange(call back in activity), state is ACTION_GATT_CONNECTED");
            this.e.c(str);
            this.k.sendEmptyMessage(302);
            this.f6441a = SystemClock.elapsedRealtime() - this.n;
            return;
        }
        if (c2 == 1) {
            LogUtil.a("Track_IndoorConnectViewModel", "onStateChange(call back in activity), state is ACTION_GATT_DISCONNECTED");
            this.e.c(str);
            this.k.sendEmptyMessage(304);
            d("DisConnect");
            return;
        }
        if (c2 == 2) {
            LogUtil.a("Track_IndoorConnectViewModel", "onStateChange(call back in activity), state is ACTION_GATT_STATE_RECONNECTED");
            this.e.c(AbstractFitnessClient.ACTION_GATT_STATE_CONNECTED);
            return;
        }
        if (c2 == 3) {
            LogUtil.a("Track_IndoorConnectViewModel", "onStateChange(call back in activity), state is ACTION_GATT_STATE_RECONNECTING");
            this.e.c(str);
            this.k.sendEmptyMessage(307);
        } else if (c2 == 4) {
            LogUtil.a("Track_IndoorConnectViewModel", "onStateChange(call back in activity), state is ACTION_GATT_DISCONNECTING");
            this.e.c(str);
        } else {
            if (c2 != 5) {
                return;
            }
            LogUtil.a("Track_IndoorConnectViewModel", "onStateChange(call back in activity), state is ACTION_GATT_CONNECTING");
            this.e.c(str);
            this.k.sendEmptyMessage(301);
        }
    }

    private void q() {
        DeviceInformation deviceInformation = this.d;
        if (deviceInformation == null) {
            return;
        }
        String d = lbv.d(deviceInformation.getBleDeviceName());
        if (TextUtils.isEmpty(this.d.getSerialNumber()) || TextUtils.isEmpty(d)) {
            return;
        }
        try {
            EcologyDeviceApi ecologyDeviceApi = (EcologyDeviceApi) Services.c("EcologyDevice", EcologyDeviceApi.class);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("sn", this.d.getSerialNumber());
            ecologyDeviceApi.uploadDataToHota(d, jSONObject.toString());
        } catch (JSONException unused) {
            LogUtil.b("Track_IndoorConnectViewModel", "uploadDataToHota JSONException");
        }
    }

    private void s() {
        if (this.w == 15) {
            LogUtil.a("Track_IndoorConnectViewModel", "first traning status is TRAINING_STATUS_POST_WORKOUT");
            this.r = true;
            a(true, true);
        }
    }

    private void b(String str) {
        String str2;
        this.e.c(str);
        LogUtil.a("Track_IndoorConnectViewModel", "onStateChange(call back in activity), state is ACTION_GATT_SERVICES_DISCOVERED");
        this.k.sendEmptyMessage(305);
        long j = this.f6441a;
        if (j <= 0 || j >= 5000) {
            str2 = "DiscoverServSuccess";
        } else {
            str2 = "DiscoverServSuccess[Took:" + this.f6441a + " ms]";
        }
        d(str2);
    }

    public void h() {
        DeviceInformation deviceInformation;
        LogUtil.a("Track_IndoorConnectViewModel", "reportDeviceInfo");
        lbd lbdVar = this.s;
        if (lbdVar == null || (deviceInformation = this.d) == null) {
            return;
        }
        lbdVar.b(deviceInformation, lau.d().b());
    }

    private void d(String str) {
        QrCodeOrNfcInfo qrCodeOrNfcInfo = this.i;
        String btMac = qrCodeOrNfcInfo != null ? qrCodeOrNfcInfo.getBtMac() : "";
        if (TextUtils.isEmpty(btMac)) {
            btMac = b() ? cei.b().getCurrentMacAddress() : lau.d().b();
        }
        lao laoVar = new lao();
        laoVar.i(str);
        laoVar.g(lau.d().g() + "");
        laoVar.j((this.i == null || lau.d().g()) ? Constants.LINK : this.i.getBtName());
        laoVar.a(this.mSportDataOutputApi != null ? String.valueOf(this.mSportDataOutputApi.getSportType()) : Constants.LINK);
        DeviceInformation deviceInformation = this.d;
        laoVar.b(deviceInformation != null ? deviceInformation.getManufacturerString() : Constants.LINK);
        DeviceInformation deviceInformation2 = this.d;
        laoVar.e(deviceInformation2 != null ? deviceInformation2.getModelString() : Constants.LINK);
        if (TextUtils.isEmpty(btMac)) {
            btMac = Constants.LINK;
        }
        laoVar.c(btMac);
        lbv.b(BaseApplication.e(), laoVar);
    }

    private void l() {
        LogUtil.a("Track_IndoorConnectViewModel", "in hadndleMessage, case is MSG_BT_SERVICE_NOT_SUPPORT, hasFoundServiceSuccessBefore is ", Boolean.valueOf(this.e.s()));
        this.c.d(this.e.s());
    }

    private void bVW_(Bundle bundle) {
        String string = bundle != null ? bundle.getString("com.huawei.health.fitness.KEY_MESSAGE_FOR_CALLBACK") : "";
        if (TextUtils.isEmpty(string)) {
            return;
        }
        LogUtil.a("Track_IndoorConnectViewModel", "control machine is ", string);
        if ("set_target_success".equals(string)) {
            this.k.sendEmptyMessage(618);
        } else if ("set_target_failed".equals(string)) {
            this.k.sendEmptyMessage(619);
        }
    }

    private void bVV_(Bundle bundle) {
        if (bundle != null) {
            boolean z = bundle.getBoolean("com.huawei.health.fitness.KEY_MESSAGE_BOOLEAN_ALLOWED_TO_SHOW_UI");
            boolean z2 = bundle.getBoolean("com.huawei.health.fitness.KEY_MESSAGE_BOOLEAN_HAS_NOT_DISCONNECTED");
            if (this.b) {
                return;
            }
            a(z2, z);
        }
    }

    public void e(boolean z) {
        ReleaseLogUtil.e("Track_IndoorConnectViewModel", "in finishThisSessionOnStopButton ? ", Boolean.valueOf(z), " mIsFinishPageHasBeenShowed ? ", Boolean.valueOf(this.b));
        if (this.k == null || this.mSportDataOutputApi == null) {
            return;
        }
        boolean z2 = !isToSave();
        LogUtil.a("Track_IndoorConnectViewModel", "finishThisSessionOnStopButton isTooShort ? ", Boolean.valueOf(z2));
        if (z && z2) {
            LogUtil.a("Track_IndoorConnectViewModel", "MSG_SHOW_TIPS_FOR_TOO_SHORT_TWO_BUTTON");
            this.k.sendEmptyMessage(TypedValues.PositionType.TYPE_DRAWPATH);
            return;
        }
        d(true, true, z, z2);
        i(z2);
        if (!this.b) {
            LogUtil.a("Track_IndoorConnectViewModel", "finishThisSessionOnStopButton onStopSport");
            this.b = true;
            this.e.a(false);
            m134x32b3e3a1();
            k();
        } else {
            LogUtil.a("Track_IndoorConnectViewModel", "Unknown state in finishThisSession,and index is one");
        }
        b(true);
        j(z2);
    }

    private void k() {
        boolean registerSportDataSavedCallback = this.mSportDataOutputApi.registerSportDataSavedCallback("Track_IndoorConnectViewModel", new ISportDataSaveStatusCallback() { // from class: lcn
            @Override // com.huawei.health.sportservice.ISportDataSaveStatusCallback
            public final void onFinish(boolean z) {
                IndoorConnectViewModel.this.c(z);
            }
        });
        LogUtil.a("Track_IndoorConnectViewModel", "showSportDetailActivityWhenSportDataSaved isRegisterSuccess " + registerSportDataSavedCallback);
        if (registerSportDataSavedCallback) {
            this.k.postDelayed(this.t, 1000L);
        } else {
            this.t.run();
        }
    }

    public /* synthetic */ void c(boolean z) {
        LogUtil.a("Track_IndoorConnectViewModel", "sportDataSavedCallback invoked. isSaved " + z);
        this.k.removeCallbacks(this.t);
        this.k.post(this.t);
    }

    public void a(boolean z, boolean z2) {
        ReleaseLogUtil.e("Track_IndoorConnectViewModel", "in finishThisSession", " hasDisconnected ? ", Boolean.valueOf(z), " hasAllowedToShowUi ? ", Boolean.valueOf(z2), " mIsFinishPageHasBeenShowed ? ", Boolean.valueOf(this.b));
        if (this.k == null || this.mSportDataOutputApi == null) {
            return;
        }
        boolean z3 = !isToSave();
        LogUtil.a("Track_IndoorConnectViewModel", "finishThisSession isTooShort ? ", Boolean.valueOf(z3));
        d(z, z2, false, z3);
        i(z3);
        if (z3 && z2) {
            if (lau.d().g() && this.r) {
                this.k.sendEmptyMessage(513);
                LogUtil.a("Track_IndoorConnectViewModel", "MSG_SHOW_TIPS_FOR_STATUS_POST_WORKOUT");
            } else {
                LogUtil.a("Track_IndoorConnectViewModel", "MSG_SHOW_TIPS_FOR_TOO_SHORT");
                this.k.sendEmptyMessage(501);
            }
            p();
        } else if (!this.b && z2) {
            LogUtil.a("Track_IndoorConnectViewModel", "finishThisSession onStopSport");
            this.b = true;
            this.e.a(false);
            p();
            k();
        } else {
            LogUtil.a("Track_IndoorConnectViewModel", "Unknown state in finishThisSessionLowerHalf,and index is one");
            this.k.sendEmptyMessage(908);
        }
        b(z);
        j(z3);
    }

    private void p() {
        if (this.g) {
            this.g = false;
            m134x32b3e3a1();
        }
    }

    private void o() {
        MotionPathSimplify motionPathSimplify;
        Bundle aTo_;
        int targetSportStatus = getTargetSportStatus();
        boolean d = d();
        ReleaseLogUtil.e("Track_IndoorConnectViewModel", "mTargetType ", Integer.valueOf(this.q), " mTargetValue ", Float.valueOf(this.y), "target status is ", Integer.valueOf(targetSportStatus), " isSportTargetSport ", Boolean.valueOf(d));
        if (d && targetSportStatus == 2) {
            LogUtil.a("Track_IndoorConnectViewModel", "showSportDetail, start over target");
            return;
        }
        if (d && targetSportStatus == 1) {
            motionPathSimplify = gwk.e(BaseApplication.e(), "device_target_motion_simplify.txt");
        } else {
            motionPathSimplify = getMotionPathSimplify();
        }
        if (motionPathSimplify != null) {
            boolean z = gwa.c(getSportType()) || getSportType() == 283;
            gso e = gso.e();
            if (d && targetSportStatus == 1) {
                aTo_ = e.aTo_("device_target_motion_path.txt", motionPathSimplify, Collections.EMPTY_LIST, z, Boolean.FALSE.booleanValue());
            } else {
                aTo_ = e.aTo_("motion_path2.txt", motionPathSimplify, Collections.EMPTY_LIST, z, Boolean.FALSE.booleanValue());
            }
            LogUtil.c("Track_IndoorConnectViewModel", "ExitApp ", Boolean.valueOf(this.l));
            if (aTo_ != null) {
                aTo_.putBoolean("ExitApp", this.l);
            }
            if (getSportType() == 283) {
                ((EcologyDeviceApi) Services.c("EcologyDevice", EcologyDeviceApi.class)).ropeDataSetDeviceType(motionPathSimplify);
            }
            e.aTq_(aTo_, motionPathSimplify);
            ReleaseLogUtil.e("Track_IndoorConnectViewModel", "showTrackMap");
        }
    }

    private void i(boolean z) {
        this.e.j(true);
        if (!lau.d().g()) {
            lau.d().q();
        }
        if (getSportType() == 264) {
            hab.d(z);
        }
    }

    public void d(boolean z) {
        this.m = z;
    }

    private void b(boolean z) {
        if (!z || b()) {
            return;
        }
        if (lbc.a(getSportType())) {
            LogUtil.a("Track_IndoorConnectViewModel", "finish by user");
            lau.d().p();
        } else if (!this.m) {
            lau.d().c(false);
        }
        if (this.m) {
            lau.d().k();
        } else {
            lau.d().o();
        }
    }

    private void d(boolean z, boolean z2, boolean z3, boolean z4) {
        QrCodeOrNfcInfo qrCodeOrNfcInfo = this.i;
        String str = Constants.LINK;
        String btMac = qrCodeOrNfcInfo != null ? qrCodeOrNfcInfo.getBtMac() : Constants.LINK;
        if (TextUtils.isEmpty(btMac)) {
            btMac = b() ? cei.b().getCurrentMacAddress() : lau.d().b();
        }
        LogUtil.a("Track_IndoorConnectViewModel", "stopSportTickBi: mDeviceInformation = ", this.d.getFitnessHashMap());
        lao laoVar = new lao();
        String bleDeviceName = this.d.getBleDeviceName();
        if (!TextUtils.isEmpty(bleDeviceName)) {
            laoVar.j(bleDeviceName);
        } else {
            QrCodeOrNfcInfo qrCodeOrNfcInfo2 = this.i;
            laoVar.j(qrCodeOrNfcInfo2 != null ? qrCodeOrNfcInfo2.getBtName() : Constants.LINK);
        }
        laoVar.d(z);
        laoVar.e(z2);
        laoVar.a(false);
        laoVar.c(z3);
        laoVar.b(z4);
        laoVar.c(btMac);
        Object data = this.mSportDataOutputApi.getData("TIME_ONE_SECOND_DURATION");
        laoVar.b((data instanceof Long ? ((Long) data).longValue() : 0L) / 1000);
        String modelString = this.d.getModelString();
        if (TextUtils.isEmpty(modelString)) {
            modelString = Constants.LINK;
        }
        String serialNumber = this.d.getSerialNumber();
        if (!TextUtils.isEmpty(serialNumber)) {
            str = serialNumber;
        }
        laoVar.e(modelString);
        laoVar.a(String.valueOf(this.mSportDataOutputApi.getSportType()));
        laoVar.d(str);
        lbv.a(BaseApplication.e(), laoVar, getSportTarget());
    }

    private void j(boolean z) {
        if (!b() || z) {
            LogUtil.a("Track_IndoorConnectViewModel", "ropeStopBiEventForPerformance is not rope or isNotNeedSave = true");
            return;
        }
        Object data = this.mSportDataOutputApi.getData("MOTION_PATH_SIMPLIFY_DATA");
        if (!(data instanceof MotionPathSimplify)) {
            LogUtil.a("Track_IndoorConnectViewModel", "ropeStopBiEventForPerformance objectSimplify not instanceof MotionPathSimplify");
        } else if (((MotionPathSimplify) data).getExtendDataFloat("enduranceAbilityRank") > 0.0f) {
            lbv.e(cei.b().getProductId(), cei.b().getCurrentMacAddress(), "rope_performance_detail");
        }
    }

    public boolean b() {
        return getSportType() == 283;
    }

    public boolean d() {
        return (getSportType() == 264 || getSportType() == 265) && (getSportTarget() == 0 || getSportTarget() == 1 || getSportTarget() == 2);
    }

    public void a(IndoorEquipConnectedManager indoorEquipConnectedManager) {
        this.c = indoorEquipConnectedManager;
    }

    public void a(long j) {
        this.n = j;
    }

    protected <T> void e(String str, String str2, Class<T> cls, final DataChangeHandle<T> dataChangeHandle) {
        this.mReceivedDataHandlers.add(new BaseSportingViewModel.ReceivedDataHandler<T>(this, str, str2, cls) { // from class: com.huawei.indoorequip.viewmodel.IndoorConnectViewModel.2
            @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel.ReceivedDataHandler
            public void handleInner(T t, Map<String, Object> map) {
                DataChangeHandle dataChangeHandle2 = dataChangeHandle;
                if (dataChangeHandle2 != null) {
                    dataChangeHandle2.changeHandle(t, map);
                }
            }
        });
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public List<String> getSubscribeTagList() {
        return new ArrayList();
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel, androidx.lifecycle.ViewModel
    public void onCleared() {
        a(false);
    }

    public void a(boolean z) {
        if (z) {
            this.e.a(0L);
            super.onCleared();
        } else if (this.mExtendHandler != null) {
            this.mExtendHandler.quit(true);
        }
    }

    public void e(QrCodeOrNfcInfo qrCodeOrNfcInfo) {
        this.i = qrCodeOrNfcInfo;
    }

    public QrCodeOrNfcInfo e() {
        return this.i;
    }

    public SupportDataRange a() {
        return this.h;
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public void unregisterAll() {
        LogUtil.a("Track_IndoorConnectViewModel", "unregisterAll");
        super.unregisterAll();
    }

    public boolean j() {
        if (this.mSportDataOutputApi == null) {
            return false;
        }
        LogUtil.a("Track_IndoorConnectViewModel", "mViewModel.isSportServiceRunning() = ", Boolean.valueOf(this.mSportDataOutputApi.isSportServiceRunning()));
        return this.mSportDataOutputApi.isSportServiceRunning();
    }

    public void i() {
        LogUtil.a("Track_IndoorConnectViewModel", "registerVoiceServiceReceiver");
        if (this.u == null) {
            this.u = new c();
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("checkserviceaction");
        LocalBroadcastManager.getInstance(BaseApplication.e()).registerReceiver(this.u, intentFilter);
    }

    public void m() {
        LogUtil.a("Track_IndoorConnectViewModel", "unregisterBroadcastReceiver");
        LocalBroadcastManager.getInstance(BaseApplication.e()).unregisterReceiver(this.u);
    }

    class c extends BroadcastReceiver {
        private c() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.h("Track_IndoorConnectViewModel", "intent is null");
                return;
            }
            Bundle extras = intent.getExtras();
            if (extras == null) {
                LogUtil.h("Track_IndoorConnectViewModel", "bundle is null");
            } else if (extras.getInt("SERVICETYPE") == 1 && IndoorConnectViewModel.this.m) {
                IndoorConnectViewModel.this.f();
            }
        }
    }

    public void f() {
        if (n() && gww.a()) {
            if (getSportType() == 264) {
                LogUtil.a("Track_IndoorConnectViewModel", "startSportGuide play voice START_RUN");
                gso.e().a(9, 0);
            } else {
                LogUtil.a("Track_IndoorConnectViewModel", "startSportGuide play voice START_RUN_OTHERS");
                gso.e().a(29, 0);
            }
        }
    }

    private boolean n() {
        if (mxb.a().c(BaseApplication.e())) {
            return true;
        }
        return lbv.a(BaseApplication.e());
    }

    @Override // com.huawei.health.basesport.viewmodel.BaseSportingViewModel
    public String getTag() {
        return "Track_IndoorConnectViewModel";
    }
}
