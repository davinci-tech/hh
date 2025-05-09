package com.huawei.health.device.manager;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.core.app.NotificationManagerCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.device.callback.IHealthDeviceCallback;
import com.huawei.health.device.manager.DeviceMonitorReceiver;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.MediaManager;
import com.huawei.health.h5pro.utils.GsonUtil;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.wisecloud.drmclient.license.HwDrmConstant;
import defpackage.bzw;
import defpackage.cet;
import defpackage.cjx;
import defpackage.cjz;
import defpackage.cke;
import defpackage.ckm;
import defpackage.cpp;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.deb;
import defpackage.deo;
import defpackage.did;
import defpackage.dij;
import defpackage.dis;
import defpackage.dks;
import defpackage.ixx;
import defpackage.jdh;
import defpackage.koq;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes8.dex */
public class DeviceMonitorReceiver extends BroadcastReceiver {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f2227a = true;
    private String c;
    private String e;
    private cke d = new cke(MessageConstant.BLOOD_PRESSURE_TYPE);
    private boolean b = false;

    public static void b(boolean z) {
        f2227a = z;
    }

    public static boolean b() {
        return f2227a;
    }

    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        ReleaseLogUtil.e("R_Plugin_DeviceMonitorReceiver", "onReceive action ", action, ", this = ", this);
        c(action);
        if (!b()) {
            ReleaseLogUtil.d("R_Plugin_DeviceMonitorReceiver", "onReceive backgroundMeasureChannel blocked");
            return;
        }
        String stringExtra = intent.getStringExtra("productId");
        String stringExtra2 = intent.getStringExtra("uniqueId");
        if (TextUtils.isEmpty(stringExtra)) {
            ReleaseLogUtil.d("R_Plugin_DeviceMonitorReceiver", "onReceive productId is empty");
            return;
        }
        if (TextUtils.isEmpty(stringExtra2)) {
            ReleaseLogUtil.d("R_Plugin_DeviceMonitorReceiver", "onReceive uniqueId is empty");
            return;
        }
        String stringExtra3 = intent.getStringExtra("kind");
        if (!TextUtils.isEmpty(stringExtra3) && !dks.f(stringExtra3)) {
            ReleaseLogUtil.d("R_Plugin_DeviceMonitorReceiver", "onReceive kind is invalid");
            return;
        }
        this.c = stringExtra;
        this.e = stringExtra2;
        if ("com.huawei.health.action.DEVICE_DISCONNECTED".equals(action)) {
            LogUtil.a("Plugin_DeviceMonitorReceiver", "onReceive action is Disconnect");
            cjx.e().d(this.c, this.e);
        }
        if ("com.huawei.health.action.DEVICE_CONNECTED".equals(action) && did.c().d(this.c)) {
            did.c().c(this.c, this.e);
        } else {
            FX_(context, stringExtra, stringExtra2, stringExtra3, intent);
        }
    }

    private void c(String str) {
        if (str != null) {
            if ("com.huawei.health.action.DEVICE_OCCUPIED".equals(str)) {
                LogUtil.a("Plugin_DeviceMonitorReceiver", "onReceive action occupied");
                b(false);
                this.b = false;
            } else {
                if ("com.huawei.health.action.DEVICE_AVAILABLE".equals(str)) {
                    LogUtil.a("Plugin_DeviceMonitorReceiver", "onReceive action available");
                    b(true);
                    if (this.b) {
                        return;
                    }
                    this.b = true;
                    return;
                }
                LogUtil.a("Plugin_DeviceMonitorReceiver", "onReceive other action = ", str);
            }
        }
    }

    private void FX_(Context context, String str, String str2, String str3, Intent intent) {
        if (intent.getBooleanExtra("autotest", false)) {
            Gb_(context, intent, new d(context, str3), str3);
        }
        if ("825c82bd-84fe-44a0-9884-6a764bd73183".equals(str)) {
            LogUtil.a("Plugin_DeviceMonitorReceiver", "onReceive product is Omuron bloodPressure");
            Ga_(context, intent);
        } else {
            cjx.e().e(str, str2, new d(context, str3));
        }
    }

    private void Gb_(Context context, Intent intent, IHealthDeviceCallback iHealthDeviceCallback, String str) {
        if ("825c82bd-84fe-44a0-9884-6a764bd73183".equals(intent.getStringExtra("productId"))) {
            FV_(context, intent, str);
            return;
        }
        Bundle bundleExtra = intent.getBundleExtra("healthdata");
        if (bundleExtra == null) {
            LogUtil.h("Plugin_DeviceMonitorReceiver", "refactorDeviceAutoTestData: healthdata bundle is null");
            return;
        }
        String string = bundleExtra.getString("product_bluetoothname");
        String string2 = bundleExtra.getString("product_mac");
        cet cetVar = new cet(string, string2, string2);
        HealthDevice.HealthDeviceKind c = dks.c(str);
        if (c == HealthDevice.HealthDeviceKind.HDK_BLOOD_SUGAR) {
            FW_(bundleExtra, iHealthDeviceCallback, cetVar);
            return;
        }
        if (c == HealthDevice.HealthDeviceKind.HDK_BLOOD_PRESSURE) {
            iHealthDeviceCallback.onDataChanged(cetVar, FZ_(bundleExtra));
        } else if (c == HealthDevice.HealthDeviceKind.HDK_WEIGHT) {
            FY_(bundleExtra, iHealthDeviceCallback, cetVar);
        } else {
            LogUtil.a("Plugin_DeviceMonitorReceiver", "refactorDeviceAutoTestData other kind = ", c);
        }
    }

    private void FY_(Bundle bundle, IHealthDeviceCallback iHealthDeviceCallback, cet cetVar) {
        float f;
        if (bundle == null) {
            LogUtil.h("Plugin_DeviceMonitorReceiver", "refactorDeviceAutoTestData: healthdata bundle is null");
            return;
        }
        ckm ckmVar = new ckm();
        try {
            f = Float.parseFloat(bundle.getString("weight"));
        } catch (NumberFormatException e) {
            LogUtil.b("Plugin_DeviceMonitorReceiver", "refactorDeviceAutoTestData e=", e.getMessage());
            f = 0.0f;
        }
        ckmVar.setWeight(f);
        ckmVar.setBodyFatRat(20.0f);
        ckmVar.setStartTime(new Date().getTime());
        ckmVar.setEndTime(new Date().getTime());
        iHealthDeviceCallback.onDataChanged(cetVar, ckmVar);
    }

    private void FW_(Bundle bundle, IHealthDeviceCallback iHealthDeviceCallback, cet cetVar) {
        float f;
        if (bundle == null) {
            LogUtil.h("Plugin_DeviceMonitorReceiver", "refactorDeviceAutoTestData: healthdata bundle is null");
            return;
        }
        deb debVar = new deb();
        try {
            f = Float.parseFloat(bundle.getString("bloodsugar"));
        } catch (NumberFormatException e) {
            LogUtil.b("Plugin_DeviceMonitorReceiver", "refactorDeviceAutoTestData e=", e.getMessage());
            f = 0.0f;
        }
        debVar.setBloodSugar(f);
        debVar.setStartTime(new Date().getTime());
        debVar.setEndTime(debVar.getStartTime());
        debVar.setSequenceNumber(0);
        List<HealthData> arrayList = new ArrayList<>();
        arrayList.add(debVar);
        iHealthDeviceCallback.onDataChanged(cetVar, arrayList);
    }

    private void FV_(final Context context, Intent intent, String str) {
        Bundle bundleExtra = intent.getBundleExtra("healthdata");
        if (bundleExtra == null) {
            LogUtil.h("Plugin_DeviceMonitorReceiver", "autoTestForOmuron: healthdata bundle is null");
            return;
        }
        String string = bundleExtra.getString("product_mac");
        final int b = b(dks.c(str));
        cjz cjzVar = new cjz(string, b);
        deo FZ_ = FZ_(bundleExtra);
        ArrayList arrayList = new ArrayList();
        arrayList.add(FZ_);
        b(cjzVar, arrayList, new IBaseResponseCallback() { // from class: cjt
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                DeviceMonitorReceiver.this.b(b, context, i, obj);
            }
        });
    }

    public /* synthetic */ void b(int i, Context context, int i2, Object obj) {
        LogUtil.a("Plugin_DeviceMonitorReceiver", "autoTestForOmuron onResponse errCode = ", Integer.valueOf(i2));
        if (i2 == 0) {
            d(i);
            b(context, "com.huawei.ui.main.stories.health.activity.healthdata.KnitBloodPressureActivity");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context, String str) {
        if (context != null) {
            Intent intent = new Intent("data_put_to_engine_success");
            intent.putExtra("data_kind", str);
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
    }

    private deo FZ_(Bundle bundle) {
        int i;
        int i2;
        String string;
        deo deoVar = new deo();
        deoVar.setStartTime(new Date().getTime());
        deoVar.setEndTime(new Date().getTime());
        int i3 = 0;
        if (bundle != null) {
            try {
                String string2 = bundle.getString("low");
                String string3 = bundle.getString(MediaManager.ROPE_MEDIA_HIGH);
                string = bundle.getString("heart");
                i = Integer.parseInt(string2);
                try {
                    i2 = Integer.parseInt(string3);
                } catch (NumberFormatException e) {
                    e = e;
                    i2 = 0;
                } catch (Exception unused) {
                    i2 = 0;
                }
            } catch (NumberFormatException e2) {
                e = e2;
                i = 0;
                i2 = 0;
            } catch (Exception unused2) {
                i = 0;
                i2 = 0;
            }
            try {
                i3 = Integer.parseInt(string);
            } catch (NumberFormatException e3) {
                e = e3;
                LogUtil.b("Plugin_DeviceMonitorReceiver", "getHeartRateAndBloodPressure e=", e.getMessage());
                deoVar.setSystolic((short) i2);
                deoVar.setDiastolic((short) i);
                deoVar.setHeartRate((short) i3);
                return deoVar;
            } catch (Exception unused3) {
                LogUtil.b("Plugin_DeviceMonitorReceiver", "getHeartRateAndBloodPressure Exception");
                deoVar.setSystolic((short) i2);
                deoVar.setDiastolic((short) i);
                deoVar.setHeartRate((short) i3);
                return deoVar;
            }
        } else {
            i = 0;
            i2 = 0;
        }
        deoVar.setSystolic((short) i2);
        deoVar.setDiastolic((short) i);
        deoVar.setHeartRate((short) i3);
        return deoVar;
    }

    private void Ga_(Context context, Intent intent) {
        ArrayList<Bundle> parcelableArrayListExtra = intent.getParcelableArrayListExtra("bloodPressureDataList");
        if (parcelableArrayListExtra == null) {
            ReleaseLogUtil.d("R_Plugin_DeviceMonitorReceiver", "refactorBloodPressureData arrayList is null");
            return;
        }
        String stringExtra = intent.getStringExtra("address");
        ArrayList arrayList = new ArrayList();
        int e = e(arrayList, parcelableArrayListExtra, stringExtra);
        if (e > 0) {
            Gc_(context, intent, e, stringExtra, arrayList);
            a(d(), KakaConstants.TASK_MEASURE_TODAY_BLOOD_PRESSURE, arrayList.get(0));
        } else {
            ReleaseLogUtil.d("R_Plugin_DeviceMonitorReceiver", "refactorBloodPressureData no valid data");
        }
    }

    private int e(List<HealthData> list, ArrayList<Bundle> arrayList, String str) {
        long d2 = this.d.d(cpp.a(), str);
        LogUtil.c("Plugin_DeviceMonitorReceiver", "newest timeStamp is ", Long.valueOf(d2));
        Iterator<Bundle> it = arrayList.iterator();
        int i = 0;
        short s = 0;
        long j = 0;
        short s2 = 0;
        short s3 = 0;
        while (it.hasNext()) {
            Bundle next = it.next();
            if (next != null) {
                try {
                    s = next.getShort(BleConstants.BLOODPRESSURE_SYSTOLIC);
                    s2 = next.getShort(BleConstants.BLOODPRESSURE_DIASTOLIC);
                    s3 = next.getShort(IndoorEquipManagerApi.KEY_HEART_RATE);
                    j = next.getLong("time");
                } catch (Exception unused) {
                    LogUtil.b("Plugin_DeviceMonitorReceiver", "refactorBloodPressureData Exception");
                }
                LogUtil.c("Plugin_DeviceMonitorReceiver", "current time is ", Long.valueOf(j));
                if (j > d2) {
                    this.d.c(cpp.a(), str, j);
                    i++;
                    deo deoVar = new deo();
                    deoVar.setStartTime(j);
                    deoVar.setEndTime(j);
                    deoVar.setSystolic(s);
                    deoVar.setDiastolic(s2);
                    deoVar.setHeartRate(s3);
                    list.add(deoVar);
                    d(dks.e(this.c, d()), str, AnalyticsValue.HEALTH_PLUGIN_DEVICE_OMRON_MEASURE_SUCCEED_2060016, deoVar);
                }
            }
        }
        return i;
    }

    private void Gc_(final Context context, Intent intent, int i, String str, List<HealthData> list) {
        LogUtil.c("Plugin_DeviceMonitorReceiver", "writeDataToHiHealth the number of data is ", Integer.valueOf(i));
        final int b = b(dks.c(intent.getStringExtra("kind")));
        b(new cjz(str, b), list, new IBaseResponseCallback() { // from class: cju
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i2, Object obj) {
                DeviceMonitorReceiver.this.a(b, context, i2, obj);
            }
        });
    }

    public /* synthetic */ void a(int i, Context context, int i2, Object obj) {
        LogUtil.a("Plugin_DeviceMonitorReceiver", "writeDataToHiHealth onResponse errCode = ", Integer.valueOf(i2));
        if (i2 == 0) {
            d(i);
            b(context, "com.huawei.ui.main.stories.health.activity.healthdata.KnitBloodPressureActivity");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, String str) {
        Intent intent = new Intent();
        intent.setClassName(BaseApplication.getAppPackage(), str);
        intent.setFlags(AppRouterExtras.COLDSTART);
        intent.putExtra("refreshCard", true);
        Notification.Builder xf_ = jdh.c().xf_();
        nsn.cLB_(xf_);
        xf_.setContentTitle(cpp.a().getResources().getString(R.string.IDS_device_weight_measure));
        xf_.setContentText(String.format(Locale.ENGLISH, cpp.a().getResources().getString(R.string.IDS_device_recive), String.valueOf(i)));
        xf_.setAutoCancel(true);
        xf_.setContentIntent(PendingIntent.getActivity(cpp.a(), 0, intent, 201326592));
        Notification build = xf_.build();
        build.flags |= 16;
        jdh.c().xh_(20181114, build);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<HealthData> list, String str, String str2) {
        if (!CommonUtil.bh() && !NotificationManagerCompat.from(BaseApplication.getContext()).areNotificationsEnabled()) {
            LogUtil.h("Plugin_DeviceMonitorReceiver", "setNotification permission is not enabled.");
            return;
        }
        if (koq.b(list)) {
            return;
        }
        if (!(list.get(0) instanceof deb)) {
            LogUtil.b("Plugin_DeviceMonitorReceiver", "Not sugar blood data");
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(BaseApplication.getAppPackage(), str);
        intent.setFlags(AppRouterExtras.COLDSTART);
        intent.putExtra("refreshCard", true);
        intent.putExtra("product_name", str2);
        intent.putExtra("entrance", "jump_from_blood_sugar_notify");
        Notification.Builder xf_ = jdh.c().xf_();
        nsn.cLB_(xf_);
        deb debVar = (deb) list.get(0);
        long startTime = debVar.getStartTime();
        long endTime = debVar.getEndTime();
        if (list.size() > 1) {
            String quantityString = cpp.a().getResources().getQuantityString(R.plurals._2130903162_res_0x7f03007a, list.size(), Integer.valueOf(list.size()));
            xf_.setContentText(quantityString).setStyle(new Notification.BigTextStyle().bigText(quantityString));
            for (HealthData healthData : list) {
                startTime = Math.min(healthData.getStartTime(), startTime);
                endTime = Math.max(healthData.getEndTime(), endTime);
            }
        } else {
            String b = b(startTime, String.valueOf(debVar.getBloodSugar()));
            xf_.setContentText(b).setStyle(new Notification.BigTextStyle().bigText(b));
        }
        xf_.setContentTitle(cpp.a().getResources().getString(R.string.IDS_device_recive_blood_sugar_title));
        intent.putExtra("start_time", startTime);
        intent.putExtra("end_time", endTime);
        if (list.size() > 1) {
            intent.putExtra("blood_sugar_data_list", GsonUtil.contentValueToJson(list));
        }
        xf_.setAutoCancel(true);
        xf_.setContentIntent(PendingIntent.getActivity(cpp.a(), 0, intent, 201326592));
        Notification build = xf_.build();
        build.flags |= 16;
        jdh.c().xh_(20181114, build);
    }

    private String b(long j, String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm", Locale.ENGLISH);
        String format = simpleDateFormat.format(new Date(j));
        String substring = format.substring(0, 10);
        String substring2 = format.substring(11);
        String substring3 = simpleDateFormat.format(new Date(System.currentTimeMillis())).substring(0, 10);
        if (substring3.equals(substring)) {
            return String.format(Locale.ENGLISH, cpp.a().getResources().getString(R.string.IDS_device_recive_blood_sugar_single_today), substring2, str);
        }
        if (substring.regionMatches(0, substring3, 0, 4)) {
            return String.format(Locale.ENGLISH, cpp.a().getResources().getString(R.string.IDS_device_recive_blood_sugar_single), substring.substring(5), substring2, str);
        }
        return String.format(Locale.ENGLISH, cpp.a().getResources().getString(R.string.IDS_device_recive_blood_sugar_single), substring, substring2, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int b(HealthDevice.HealthDeviceKind healthDeviceKind) {
        if (healthDeviceKind == HealthDevice.HealthDeviceKind.HDK_BLOOD_PRESSURE) {
            return 10002;
        }
        if (healthDeviceKind == HealthDevice.HealthDeviceKind.HDK_BLOOD_SUGAR) {
            return 10001;
        }
        if (healthDeviceKind == HealthDevice.HealthDeviceKind.HDK_WEIGHT) {
            return 10006;
        }
        return healthDeviceKind == HealthDevice.HealthDeviceKind.HDK_HEART_RATE ? 50001 : -1;
    }

    public class d implements IHealthDeviceCallback {

        /* renamed from: a, reason: collision with root package name */
        private int f2228a;
        private Context b;

        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onFailed(HealthDevice healthDevice, int i) {
        }

        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onProgressChanged(HealthDevice healthDevice, HealthData healthData) {
        }

        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onStatusChanged(HealthDevice healthDevice, int i) {
        }

        d(Context context, String str) {
            HealthDevice.HealthDeviceKind healthDeviceKind;
            this.b = context;
            if (str == null) {
                healthDeviceKind = HealthDevice.HealthDeviceKind.HDK_UNKNOWN;
            } else {
                try {
                    healthDeviceKind = HealthDevice.HealthDeviceKind.valueOf(str);
                } catch (IllegalArgumentException unused) {
                    healthDeviceKind = HealthDevice.HealthDeviceKind.HDK_UNKNOWN;
                }
            }
            this.f2228a = DeviceMonitorReceiver.this.b(healthDeviceKind);
        }

        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onDataChanged(HealthDevice healthDevice, HealthData healthData) {
            LogUtil.a("Plugin_DeviceMonitorReceiver", "BackgroundDataReceiverCallback onDataChanged ");
            if (DeviceMonitorReceiver.this.a(healthDevice, healthData)) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            if (healthData instanceof deo) {
                cjz cjzVar = new cjz(healthDevice.getUniqueId(), this.f2228a);
                arrayList.add(healthData);
                DeviceMonitorReceiver.this.b(cjzVar, arrayList, new IBaseResponseCallback() { // from class: cjs
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj) {
                        DeviceMonitorReceiver.d.this.a(i, obj);
                    }
                });
                String d = DeviceMonitorReceiver.this.d();
                DeviceMonitorReceiver deviceMonitorReceiver = DeviceMonitorReceiver.this;
                deviceMonitorReceiver.d(dks.e(deviceMonitorReceiver.c, DeviceMonitorReceiver.this.d()), healthDevice.getAddress(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_OMRON_MEASURE_SUCCEED_2060016, healthData);
                DeviceMonitorReceiver.this.a(d, KakaConstants.TASK_MEASURE_TODAY_BLOOD_PRESSURE, healthData);
                return;
            }
            if (healthData instanceof ckm) {
                cjz cjzVar2 = new cjz(healthDevice.getUniqueId(), this.f2228a);
                arrayList.add(healthData);
                DeviceMonitorReceiver.this.b(cjzVar2, arrayList, new IBaseResponseCallback() { // from class: cjw
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj) {
                        DeviceMonitorReceiver.d.this.d(i, obj);
                    }
                });
                String d2 = DeviceMonitorReceiver.this.d();
                DeviceMonitorReceiver deviceMonitorReceiver2 = DeviceMonitorReceiver.this;
                deviceMonitorReceiver2.d(dks.e(deviceMonitorReceiver2.c, DeviceMonitorReceiver.this.d()), healthDevice.getAddress(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_YUYUE_MEASURE_SUCCEED_2060016, healthData);
                DeviceMonitorReceiver.this.a(d2, KakaConstants.TASK_ENTER_TODAY_WEIGHT, healthData);
                return;
            }
            LogUtil.a("Plugin_DeviceMonitorReceiver", "BackgroundDataReceiverCallback onDataChanged other dataType");
        }

        public /* synthetic */ void a(int i, Object obj) {
            LogUtil.a("Plugin_DeviceMonitorReceiver", "DeviceMonitorReceiver onDataChanged onResponse errCode = ", Integer.valueOf(i));
            if (i == 0) {
                DeviceMonitorReceiver.this.d(this.f2228a);
                DeviceMonitorReceiver.this.b(this.b, "com.huawei.ui.main.stories.health.activity.healthdata.KnitBloodPressureActivity");
            }
        }

        public /* synthetic */ void d(int i, Object obj) {
            LogUtil.a("Plugin_DeviceMonitorReceiver", "BackgroundDataReceiverCallback onDataChanged onResponse errCode = ", Integer.valueOf(i));
            if (i == 0) {
                DeviceMonitorReceiver.this.d(this.f2228a);
                DeviceMonitorReceiver.this.b(this.b, "com.huawei.ui.main.stories.health.activity.healthdata.WeightActivity");
                DeviceMonitorReceiver.this.c(1, "com.huawei.ui.main.stories.health.activity.healthdata.WeightActivity");
            }
        }

        @Override // com.huawei.health.device.callback.IHealthDeviceCallback
        public void onDataChanged(HealthDevice healthDevice, final List<HealthData> list) {
            LogUtil.c("Plugin_DeviceMonitorReceiver", "BackgroundDataReceiverCallback onDataChanged");
            if (healthDevice == null) {
                LogUtil.h("Plugin_DeviceMonitorReceiver", "BackgroundDataReceiverCallback onDataChanged device is null");
                return;
            }
            if (!koq.b(list)) {
                LogUtil.a("Plugin_DeviceMonitorReceiver", "BackgroundDataReceiverCallback dataList size is ", Integer.valueOf(list.size()));
                if (list.get(0) instanceof deb) {
                    LogUtil.c("Plugin_DeviceMonitorReceiver", "BackgroundDataReceiverCallback onDataChanged data is bloodSugar");
                    final String d = DeviceMonitorReceiver.this.d();
                    DeviceMonitorReceiver deviceMonitorReceiver = DeviceMonitorReceiver.this;
                    deviceMonitorReceiver.c(dks.e(deviceMonitorReceiver.c, d), healthDevice.getAddress(), AnalyticsValue.HEALTH_PLUGIN_DEVICE_JOHNSON_MEASURE_SUCCEED_2060015, list);
                    DeviceMonitorReceiver.this.a(d, KakaConstants.TASK_MEASURE_TODAY_BLOOD_SUGAR, list.get(0));
                    DeviceMonitorReceiver.this.b(new cjz(healthDevice.getUniqueId(), this.f2228a), list, new IBaseResponseCallback() { // from class: ckb
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public final void d(int i, Object obj) {
                            DeviceMonitorReceiver.d.this.e(d, list, i, obj);
                        }
                    });
                    return;
                }
                return;
            }
            LogUtil.h("Plugin_DeviceMonitorReceiver", "BackgroundDataReceiverCallback onDataChanged data is null or empty");
        }

        public /* synthetic */ void e(String str, List list, int i, Object obj) {
            LogUtil.a("Plugin_DeviceMonitorReceiver", "BackgroundDataReceiverCallback onDataChanged onResponse errCode = ", Integer.valueOf(i));
            if (i == 0) {
                DeviceMonitorReceiver.this.d(this.f2228a);
                String d = dcx.d(DeviceMonitorReceiver.this.c, str);
                DeviceMonitorReceiver.this.b(this.b, "com.huawei.ui.main.stories.health.activity.healthdata.BloodSugarDeviceMeasureActivity");
                DeviceMonitorReceiver.this.b((List<HealthData>) list, "com.huawei.ui.main.stories.health.activity.healthdata.BloodSugarDeviceMeasureActivity", d);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(HealthDevice healthDevice, HealthData healthData) {
        if (healthDevice == null) {
            LogUtil.h("Plugin_DeviceMonitorReceiver", "isParamInvalided device is null");
            return true;
        }
        if (healthData == null) {
            LogUtil.h("Plugin_DeviceMonitorReceiver", "isParamInvalided data is null");
            return true;
        }
        if (b()) {
            return false;
        }
        LogUtil.a("Plugin_DeviceMonitorReceiver", "isParamInvalided receiver setNotification block");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, int i, HealthData healthData) {
        HashMap hashMap = new HashMap(16);
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, str);
        hashMap.put(DeviceCategoryFragment.DEVICE_TYPE, ResourceManager.e().d(this.c).l().name());
        hashMap.put("measure_time", new SimpleDateFormat(HwDrmConstant.TIME_FORMAT).format(Long.valueOf(healthData.getEndTime())));
        bzw.e().setEvent(BaseApplication.getContext(), String.valueOf(i), hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, String str2, AnalyticsValue analyticsValue, List<HealthData> list) {
        Iterator<HealthData> it = list.iterator();
        while (it.hasNext()) {
            d(str, str2, analyticsValue, it.next());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, String str2, AnalyticsValue analyticsValue, HealthData healthData) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("macAddress", dis.b(str2));
        hashMap.put(PluginPayAdapter.KEY_DEVICE_INFO_NAME, str);
        hashMap.put(DeviceCategoryFragment.DEVICE_TYPE, ResourceManager.e().d(this.c).l().name());
        hashMap.put("measure_time", new SimpleDateFormat(HwDrmConstant.TIME_FORMAT).format(Long.valueOf(healthData.getEndTime())));
        hashMap.put("prodId", dij.e(this.c));
        ixx.d().d(cpp.a(), analyticsValue.value(), hashMap, 0);
        ixx.d().c(cpp.a());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String d() {
        dcz d2 = ResourceManager.e().d(this.c);
        if (d2 != null) {
            return d2.n().b();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(cjz cjzVar, List<HealthData> list, IBaseResponseCallback iBaseResponseCallback) {
        ArrayList arrayList = new ArrayList();
        for (HealthData healthData : list) {
            if (b(healthData)) {
                arrayList.add(cjzVar.d(healthData));
            }
        }
        cjzVar.a(arrayList, iBaseResponseCallback);
    }

    private boolean b(HealthData healthData) {
        if (healthData == null) {
            return false;
        }
        return !(healthData instanceof deb) || ((double) Math.abs(((deb) healthData).getBloodSugar())) >= 1.0E-6d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        LogUtil.a("Plugin_DeviceMonitorReceiver", "syncCloudAfterInsert: type = ", Integer.valueOf(i));
        if (i == 10001 || i == 10002 || i == 10006) {
            HiSyncOption hiSyncOption = new HiSyncOption();
            hiSyncOption.setSyncModel(2);
            hiSyncOption.setSyncAction(0);
            hiSyncOption.setSyncDataType(20000);
            hiSyncOption.setSyncMethod(2);
            hiSyncOption.setSyncScope(1);
            HiHealthNativeApi.a(cpp.a()).synCloud(hiSyncOption, null);
        }
    }
}
