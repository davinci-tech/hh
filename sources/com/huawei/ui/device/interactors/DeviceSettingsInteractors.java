package com.huawei.ui.device.interactors;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.text.format.DateFormat;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.google.gson.Gson;
import com.huawei.datatype.Contact;
import com.huawei.datatype.DataDeviceAvoidDisturbInfo;
import com.huawei.datatype.EventAlarmInfo;
import com.huawei.datatype.SmartAlarmInfo;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.service.HandleIntentService;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cun;
import defpackage.cvs;
import defpackage.jec;
import defpackage.jfq;
import defpackage.jfy;
import defpackage.jgh;
import defpackage.jgl;
import defpackage.jhg;
import defpackage.jiu;
import defpackage.jkh;
import defpackage.jld;
import defpackage.jog;
import defpackage.joj;
import defpackage.jpp;
import defpackage.jpt;
import defpackage.jqi;
import defpackage.jqy;
import defpackage.knl;
import defpackage.oaf;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class DeviceSettingsInteractors {
    private static volatile DeviceSettingsInteractors b;
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private Context f9302a;
    private jgl c;
    private jfq d;
    private jiu f;

    public DeviceSettingsInteractors(Context context) {
        this.d = null;
        this.f = null;
        if (context == null) {
            LogUtil.h("DeviceSettingsInteractors", "DeviceSettingsInteractors is null");
            return;
        }
        this.f9302a = context;
        this.d = jfq.c();
        this.f = jiu.c();
        this.c = jgl.d(this.f9302a);
    }

    public static DeviceSettingsInteractors d(Context context) {
        DeviceSettingsInteractors deviceSettingsInteractors;
        synchronized (e) {
            if (b == null) {
                b = new DeviceSettingsInteractors(BaseApplication.getContext());
            }
            deviceSettingsInteractors = b;
        }
        return deviceSettingsInteractors;
    }

    public DeviceCapability e() {
        LogUtil.a("DeviceSettingsInteractors", "serviceCapabilityNegotiation()");
        return cvs.d();
    }

    public DeviceCapability e(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DeviceSettingsInteractors", "capabilityNegotiation id is empty");
            return null;
        }
        LogUtil.a("DeviceSettingsInteractors", "serviceCapabilityNegotiation(id):", CommonUtil.l(str));
        if (cvs.e(str) == null) {
            DeviceInfo e2 = jpt.e(str, "DeviceSettingsInteractors");
            if (e2 != null && e2.getDeviceConnectState() == 2) {
                Map<String, DeviceCapability> a2 = this.d.a(3, "", "DeviceSettingsInteractors");
                if (a2 != null && a2.get(str) != null) {
                    cvs.a(str, a2.get(str));
                    return a2.get(str);
                }
                LogUtil.h("DeviceSettingsInteractors", "serviceCapabilityNegotiation(id) cap is null");
                return null;
            }
            LogUtil.h("DeviceSettingsInteractors", "serviceCapabilityNegotiation(id) device is not connected");
            return null;
        }
        return cvs.e(str);
    }

    public List<DeviceInfo> c(String str) {
        if (TextUtils.isEmpty(str) || str.length() < 3) {
            LogUtil.h("DeviceSettingsInteractors", "getDeviceInfoAsSmartLifeMac id is empty");
            return new ArrayList();
        }
        if (this.d == null) {
            LogUtil.h("DeviceSettingsInteractors", "getDeviceInfoAsSmartLifeMac() mHwDeviceConfigManager is null");
            return new ArrayList();
        }
        String substring = str.substring(str.length() - 3, str.length());
        List<DeviceInfo> deviceList = cun.c().getDeviceList(HwGetDevicesMode.ALL_DEVICES, null, "DeviceSettingsInteractors");
        ArrayList arrayList = new ArrayList(16);
        if (deviceList != null) {
            for (DeviceInfo deviceInfo : deviceList) {
                String replaceAll = deviceInfo.getDeviceIdentify().replaceAll(":", "");
                int length = replaceAll.length();
                if (length < 3) {
                    LogUtil.h("DeviceSettingsInteractors", "deviceMac length too short");
                } else if (substring.equals(replaceAll.substring(length - 3, length))) {
                    LogUtil.a("DeviceSettingsInteractors", " id equals device mac");
                    arrayList.add(deviceInfo);
                }
            }
            LogUtil.a("DeviceSettingsInteractors", "not find submac = ", str);
            return arrayList;
        }
        LogUtil.h("DeviceSettingsInteractors", "getDeviceInfoAsSmartLifeMac() deviceInfoList is null");
        return arrayList;
    }

    public void c(IBaseResponseCallback iBaseResponseCallback) {
        jog.c().e(iBaseResponseCallback);
    }

    public void a(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("DeviceSettingsInteractors", "getDevicePhoneInfo");
        jog.c().b(iBaseResponseCallback);
    }

    public void e(String str, boolean z, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("DeviceSettingsInteractors", "setBluetoothOffalertSwitchStatus() isChecked ", Boolean.valueOf(z));
        this.f.d(str, z, iBaseResponseCallback);
    }

    public void d(String str, boolean z, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("DeviceSettingsInteractors", "factoryReset()");
        jog.c().e(str, z, iBaseResponseCallback);
        jgh.d(this.f9302a).a("");
    }

    public void e(String str, int i, IBaseResponseCallback iBaseResponseCallback) {
        this.c.e(str, i, iBaseResponseCallback);
    }

    public void c(String str, IBaseResponseCallback iBaseResponseCallback) {
        this.c.b(str, iBaseResponseCallback);
    }

    public void e(boolean z) {
        jhg.c(this.f9302a).b(z);
    }

    public void a(int i) {
        jhg.c(this.f9302a).a(i);
    }

    public void c(int i) {
        jhg.c(this.f9302a).d(i);
    }

    public void d(int i, int i2, boolean z) {
        jhg.c(this.f9302a).a(i, i2, z);
    }

    public void a(int i, int i2, boolean z) {
        jhg.c(this.f9302a).e(i, i2, z);
    }

    public void b(int i) {
        jhg.c(this.f9302a).b(i);
    }

    public void e(int i, int i2, boolean z) {
        jhg.c(this.f9302a).b(i, i2, z);
    }

    public void d(int i, int i2, int i3) {
        jhg.c(this.f9302a).e(i, i2, i3);
    }

    public void d(String str, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("DeviceSettingsInteractors", "getDeviceEventAlarm()");
        jgh.d(this.f9302a).a(str, iBaseResponseCallback);
    }

    public List<EventAlarmInfo> d(Object obj) {
        LogUtil.a("DeviceSettingsInteractors", "getEventAlarmList()");
        return jgh.d(this.f9302a).d(obj);
    }

    public void a(String str, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("DeviceSettingsInteractors", "getDeviceSmartAlarmList()");
        jgh.d(this.f9302a).c(str, iBaseResponseCallback);
    }

    public List<SmartAlarmInfo> a(Object obj) {
        LogUtil.a("DeviceSettingsInteractors", "getDeviceEventAlarm()");
        return jgh.d(this.f9302a).b(obj);
    }

    public void b(List<EventAlarmInfo> list) {
        if (list == null) {
            LogUtil.h("DeviceSettingsInteractors", "saveEventAlarmInfoList is null");
            return;
        }
        StorageParams storageParams = new StorageParams(0);
        String json = new Gson().toJson(list);
        LogUtil.a("DeviceSettingsInteractors", "saveEventAlarmLists json:", json);
        SharedPreferenceManager.e(this.f9302a, String.valueOf(10022), "DEVICE_EVENT_ALARM_INFO", json, storageParams);
        e(list);
    }

    public void c(List<SmartAlarmInfo> list) {
        if (list == null) {
            LogUtil.h("DeviceSettingsInteractors", "saveEventAlarmInfoList is null");
            return;
        }
        StorageParams storageParams = new StorageParams(0);
        String json = new Gson().toJson(list);
        LogUtil.a("DeviceSettingsInteractors", "saveEventAlarmLists json:", json);
        SharedPreferenceManager.e(this.f9302a, String.valueOf(10022), "DEVICE_SMART_ALARM_INFO", json, storageParams);
    }

    public void c(String str, List<EventAlarmInfo> list, IBaseResponseCallback iBaseResponseCallback) {
        jgh.d(this.f9302a).b(str, list, iBaseResponseCallback);
    }

    public void a(String str, List<SmartAlarmInfo> list, IBaseResponseCallback iBaseResponseCallback) {
        jgh.d(this.f9302a).d(str, list, iBaseResponseCallback);
    }

    public void b(List<EventAlarmInfo> list, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("DeviceSettingsInteractors", "setEventAlarm()");
        jgh.d(this.f9302a).c(list, iBaseResponseCallback, false);
        e(list);
    }

    public void e(List<EventAlarmInfo> list) {
        if (list == null) {
            LogUtil.h("DeviceSettingsInteractors", "saveOnceAlarm eventAlarmInfoList is null");
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        for (int i = 0; i < list.size(); i++) {
            EventAlarmInfo eventAlarmInfo = list.get(i);
            if (eventAlarmInfo.getEventAlarmRepeat() == 0 && eventAlarmInfo.getEventAlarmEnable() == 1) {
                arrayList.add(eventAlarmInfo.resetAlarm(eventAlarmInfo, i));
            }
        }
        LogUtil.a("DeviceSettingsInteractors", "once eventAlarm saveAlarmInfoList.size():", Integer.valueOf(arrayList.size()));
        if (arrayList.size() > 0) {
            StorageParams storageParams = new StorageParams(0);
            String json = new Gson().toJson(arrayList);
            LogUtil.a("DeviceSettingsInteractors", "once eventAlarm saveOnceAlarm() json:", json);
            SharedPreferenceManager.e(this.f9302a, String.valueOf(10022), "ONCE_EVENT_ALARM_INFO", json, storageParams);
        }
    }

    public void e(List<SmartAlarmInfo> list, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("DeviceSettingsInteractors", "setSmartAlarm()");
        jgh.d(this.f9302a).b(list, iBaseResponseCallback, false);
        a(list);
    }

    public void a(List<SmartAlarmInfo> list) {
        if (list == null) {
            LogUtil.h("DeviceSettingsInteractors", "saveOnceAlarm eventAlarmInfoList is null");
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        for (int i = 0; i < list.size(); i++) {
            SmartAlarmInfo smartAlarmInfo = list.get(i);
            if (smartAlarmInfo.getSmartAlarmRepeat() == 0 && smartAlarmInfo.getSmartAlarmEnable() == 1) {
                arrayList.add(smartAlarmInfo.resetSmartAlarm(smartAlarmInfo, i));
            }
        }
        LogUtil.a("DeviceSettingsInteractors", "once saveOnceSmartAlarm saveAlarmInfoList.size():", Integer.valueOf(arrayList.size()));
        if (arrayList.size() > 0) {
            StorageParams storageParams = new StorageParams(0);
            String json = new Gson().toJson(arrayList);
            LogUtil.a("DeviceSettingsInteractors", "once saveOnceSmartAlarm() json:", json);
            SharedPreferenceManager.e(this.f9302a, String.valueOf(10022), "ONCE_SMART_ALARM_INFO", json, storageParams);
        }
    }

    public String a(String str) {
        LogUtil.a("DeviceSettingsInteractors", "getDeviceName by id");
        DeviceInfo e2 = jpt.e(str, "DeviceSettingsInteractors");
        return e2 != null ? e2.getDeviceName() : "";
    }

    public DeviceInfo a() {
        DeviceInfo a2 = jpt.a("DeviceSettingsInteractors");
        if (a2 != null) {
            return a2;
        }
        return null;
    }

    public void b(boolean z) {
        ReleaseLogUtil.e("R_DeviceSettingsInteractors", "setWeatherReportSwitch setSwitchSetting.WEATHER_SWITCH_STATUS isChecked:", Boolean.valueOf(z));
        jqy.a("weather_switch_status", String.valueOf(z));
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null && defaultAdapter.getState() == 10) {
            LogUtil.h("DeviceSettingsInteractors", "setWeatherReportSwitch switch not on, not need start service!");
            return;
        }
        if (!jpp.a()) {
            LogUtil.h("DeviceSettingsInteractors", "setWeatherReportSwitch have no device so do not start PhoneService.");
            return;
        }
        Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) HandleIntentService.class);
        intent.setAction("com.huawei.health.ACTION_WEATHER_PUSH");
        Bundle bundle = new Bundle();
        bundle.putBoolean("com.huawei.health.ACTION_WEATHER_PUSH_VALUE", z);
        intent.putExtras(bundle);
        LogUtil.a("DeviceSettingsInteractors", "start to push notification msg.");
        BaseApplication.getContext().startService(intent);
    }

    public void d() {
        LogUtil.a("DeviceSettingsInteractors", "pushLocalPressure");
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null && defaultAdapter.getState() == 10) {
            LogUtil.a("DeviceSettingsInteractors", "pushLocalPressure switch not on, not need start service!");
            return;
        }
        if (!jpp.a()) {
            LogUtil.a("DeviceSettingsInteractors", "pushLocalPressure have no device so do not start PhoneService.");
            return;
        }
        Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) HandleIntentService.class);
        intent.setAction("com.huawei.health.ACTION_LOCAL_PRESSURE_PUSH");
        LogUtil.a("DeviceSettingsInteractors", "start to push pushLocalPressure msg.");
        BaseApplication.getContext().startService(intent);
    }

    public void c() {
        LogUtil.a("DeviceSettingsInteractors", "pushWeatherData");
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null && defaultAdapter.getState() == 10) {
            LogUtil.h("DeviceSettingsInteractors", "pushWeatherData switch not on, not need start service!");
            return;
        }
        if (!jpp.a()) {
            LogUtil.h("DeviceSettingsInteractors", "pushWeatherData have no device so do not start PhoneService.");
            return;
        }
        Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) HandleIntentService.class);
        intent.setAction("com.huawei.health.ACTION_WEATHER_DATA_PUSH");
        LogUtil.a("DeviceSettingsInteractors", "start to push weather data msg.");
        BaseApplication.getContext().startService(intent);
    }

    public void c(boolean z) {
        LogUtil.a("DeviceSettingsInteractors", "setWeatherReportUnit weatherReportUnit ", Boolean.valueOf(z));
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        if (defaultAdapter != null && defaultAdapter.getState() == 10) {
            LogUtil.h("DeviceSettingsInteractors", "setWeatherReportUnit switch not on, not need start service!");
        } else {
            ReleaseLogUtil.e("R_DeviceSettingsInteractors", "setWeatherReportUnit setSwitchSetting.WEATHER_SWITCH_UNIT_STATUS isChecked:", Boolean.valueOf(z));
            jqi.a().setSwitchSetting("weather_switch_unit_status", String.valueOf(z), new IBaseResponseCallback() { // from class: com.huawei.ui.device.interactors.DeviceSettingsInteractors.3
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    ReleaseLogUtil.e("R_DeviceSettingsInteractors", "setSwitchSetting.WEATHER_SWITCH_UNIT_STATUS errorCode:", Integer.valueOf(i));
                    if (i == 0) {
                        if (!jpp.a()) {
                            LogUtil.h("DeviceSettingsInteractors", "setWeatherReportUnit have no device so do not start PhoneService.");
                            return;
                        }
                        Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) HandleIntentService.class);
                        intent.setAction("com.huawei.health.ACTION_WEATHER_DATA_PUSH");
                        LogUtil.a("DeviceSettingsInteractors", "start to push weather unit data msg.");
                        BaseApplication.getContext().startService(intent);
                    }
                }
            });
        }
    }

    public void cTD_(String str, final Handler handler, boolean z) {
        if (handler == null || TextUtils.isEmpty(str)) {
            LogUtil.h("DeviceSettingsInteractors", "restoreFactory handler or id is null");
            return;
        }
        final DeviceInfo e2 = jpt.e(str, "DeviceSettingsInteractors");
        if (e2 == null) {
            LogUtil.h("DeviceSettingsInteractors", "restoreFactory currentDeviceInfo is null");
            handler.removeMessages(11);
            handler.sendEmptyMessage(11);
        } else {
            LogUtil.a("DeviceSettingsInteractors", "restoreFactory productType:", Integer.valueOf(e2.getProductType()));
            d(str, z, new IBaseResponseCallback() { // from class: com.huawei.ui.device.interactors.DeviceSettingsInteractors.2
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("DeviceSettingsInteractors", "mRestoreFactoryCallback,err_code:", Integer.valueOf(i));
                    if (i == 0) {
                        LogUtil.a("DeviceSettingsInteractors", "objData is :", obj);
                        d(((Integer) obj).intValue());
                    } else {
                        b();
                    }
                }

                private void d(int i) {
                    if (i == 100000) {
                        LogUtil.a("DeviceSettingsInteractors", "restoreFactory reset success");
                        handler.sendEmptyMessage(8);
                        DeviceSettingsInteractors.this.d(e2);
                    } else if (i == 101901) {
                        LogUtil.a("DeviceSettingsInteractors", "restoreFactory reset failed delete esim failed");
                        handler.sendEmptyMessage(10);
                    } else {
                        LogUtil.a("DeviceSettingsInteractors", "restoreFactory reset failed others");
                        b();
                    }
                }

                private void b() {
                    handler.removeMessages(11);
                    handler.sendEmptyMessage(11);
                    LogUtil.h("DeviceSettingsInteractors", "mRestoreFactory failed.");
                }
            });
        }
    }

    public void b() {
        LogUtil.a("DeviceSettingsInteractors", "enter handleRestoreFactorySuccess()");
    }

    public void a(Context context, IBaseResponseCallback iBaseResponseCallback) {
        if (context == null) {
            LogUtil.h("DeviceSettingsInteractors", "getNoDisturbInfo context is null");
        } else if (iBaseResponseCallback == null) {
            LogUtil.h("DeviceSettingsInteractors", "getNoDisturbInfo() hwDeviceConfigManager is null or callback is null");
        } else {
            jog.c().d(iBaseResponseCallback);
        }
    }

    public void d(String str, DataDeviceAvoidDisturbInfo dataDeviceAvoidDisturbInfo, IBaseResponseCallback iBaseResponseCallback) {
        if (dataDeviceAvoidDisturbInfo == null) {
            LogUtil.h("DeviceSettingsInteractors", "setNoDisturb2Device() hwDeviceConfigManager or dataDeviceAvoidDisturbInfo is null");
            return;
        }
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(dataDeviceAvoidDisturbInfo);
        jog.c().a(str, arrayList, iBaseResponseCallback, false);
    }

    public List<Contact> a(Context context) {
        jfy b2 = jfy.b();
        if (b2 == null) {
            LogUtil.h("DeviceSettingsInteractors", "getContact() hwAddressBookManager is null");
            return new ArrayList();
        }
        return b2.c();
    }

    public void c(Context context, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null && context == null) {
            LogUtil.h("DeviceSettingsInteractors", "updateContact callback or context is null");
            return;
        }
        jfy b2 = jfy.b();
        if (b2 == null) {
            LogUtil.h("DeviceSettingsInteractors", "getContact() hwAddressBookManager is null");
            iBaseResponseCallback.d(0, null);
        } else {
            b2.a(iBaseResponseCallback);
        }
    }

    public void a(Context context, List<Contact> list, IBaseResponseCallback iBaseResponseCallback) {
        jfy b2 = jfy.b();
        if (b2 == null || list == null) {
            LogUtil.h("DeviceSettingsInteractors", "hwAddressBookManager is null or list is null");
        } else {
            LogUtil.a("DeviceSettingsInteractors", "before list.size:", Integer.valueOf(list.size()));
            b2.b(list, iBaseResponseCallback, false);
        }
    }

    public int b(Context context) {
        jfy b2 = jfy.b();
        if (b2 == null) {
            LogUtil.h("DeviceSettingsInteractors", "hwAddressBookManager is null");
            return 0;
        }
        return b2.e();
    }

    public void b(Context context, String str, IBaseResponseCallback iBaseResponseCallback) {
        jkh c = jkh.c(context);
        if (c == null) {
            LogUtil.h("DeviceSettingsInteractors", "getOneLevelMenu() hwOneLevelMenuManager is null");
        } else {
            c.b(str, iBaseResponseCallback);
        }
    }

    public void d(Context context, String str, List<Integer> list, IBaseResponseCallback iBaseResponseCallback) {
        jkh c = jkh.c(context);
        if (c == null || list == null) {
            LogUtil.h("DeviceSettingsInteractors", "hwOneLevelMenuManager is null or list is null");
        } else {
            LogUtil.a("DeviceSettingsInteractors", "before list.size :", Integer.valueOf(list.size()));
            c.a(str, list, iBaseResponseCallback);
        }
    }

    public String b(Context context, int i, int i2) {
        String d;
        String b2 = jec.b(i);
        String b3 = jec.b(i2);
        String d2 = d(b2);
        if (i > i2) {
            d = context.getResources().getString(R.string._2130842215_res_0x7f021267, d(b3));
        } else {
            d = d(b3);
        }
        return d2 + System.lineSeparator() + d;
    }

    public String d(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("DeviceSettingsInteractors", "getTimeStr time is null");
        }
        String str2 = "";
        try {
            str2 = DateFormat.getTimeFormat(this.f9302a.getApplicationContext()).format(Long.valueOf(new SimpleDateFormat("HH:mm").parse(str).getTime()));
            LogUtil.a("DeviceSettingsInteractors", "getOffsetTimeStr dateFormat time:", str2);
            return str2;
        } catch (ParseException e2) {
            LogUtil.b("DeviceSettingsInteractors", "e.getMessage() is:", e2.getMessage());
            return str2;
        }
    }

    public void e(int i) {
        LogUtil.a("DeviceSettingsInteractors", "push data to device: (5.32.9)------", Integer.valueOf(i));
        jld.b().a(i);
    }

    public void a(boolean z) {
        LogUtil.a("DeviceSettingsInteractors", "setPressAutoMonitorSwitch isChecked :", Boolean.valueOf(z));
        jld.b().d(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(DeviceInfo deviceInfo) {
        LogUtil.a("DeviceSettingsInteractors", "delete shared preference when reset");
        String d = knl.d(deviceInfo.getDeviceIdentify());
        String b2 = SharedPreferenceManager.b(this.f9302a, String.valueOf(PrebakedEffectId.RT_COIN_DROP), d);
        LogUtil.a("DeviceSettingsInteractors", "sharedPreferenceResult is ", b2);
        if ("0".equals(b2)) {
            SharedPreferenceManager.c(this.f9302a, String.valueOf(PrebakedEffectId.RT_COIN_DROP), d);
        }
        joj.a().a(deviceInfo);
        oaf.b(BaseApplication.getContext()).a(deviceInfo);
    }
}
