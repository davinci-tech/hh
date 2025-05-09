package com.huawei.ui.device.interactors;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.ui.measure.fragment.device.DeviceCategoryFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.datatypes.HealthSupportModel;
import com.huawei.hwcommonmodel.datatypes.HuaweiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.device.activity.adddevice.AddDeviceIntroActivity;
import com.huawei.ui.device.activity.pairing.DevicePairGuideActivity;
import defpackage.cvw;
import defpackage.dul;
import defpackage.dwo;
import defpackage.ixx;
import defpackage.iyl;
import defpackage.jdx;
import defpackage.jfu;
import defpackage.jpp;
import defpackage.oae;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class CompatibilityInteractor {
    private static ThreadPoolManager b = ThreadPoolManager.e(3, 3, "CompatibilityInteractor");

    /* renamed from: a, reason: collision with root package name */
    private Gson f9291a = new Gson();
    private CustomAlertDialog c = null;
    private CustomAlertDialog e = null;

    public enum WearDeviceState {
        NO_BIND_DEVICE,
        EXIST_TYPE
    }

    public boolean a(Context context) {
        return CommonUtil.al(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public WearDeviceState c(List<DeviceInfo> list) {
        LogUtil.a("CompatibilityInteractor", "Enter getWearDeviceState");
        WearDeviceState wearDeviceState = WearDeviceState.NO_BIND_DEVICE;
        if (list != null) {
            LogUtil.a("CompatibilityInteractor", "deviceInfoList size:", Integer.valueOf(list.size()));
            if (list.size() > 0) {
                for (DeviceInfo deviceInfo : list) {
                    LogUtil.a("CompatibilityInteractor", "deviceInfoList deviceInfo:", Integer.valueOf(deviceInfo.getProductType()));
                    if (dul.c().b(d()).contains(Integer.valueOf(dul.c().c(deviceInfo.getProductType())))) {
                        wearDeviceState = WearDeviceState.EXIST_TYPE;
                    }
                }
            }
            LogUtil.a("CompatibilityInteractor", "getWearDeviceState wearDeviceState:", wearDeviceState);
            return wearDeviceState;
        }
        LogUtil.a("CompatibilityInteractor", "deviceInfoList is null");
        return WearDeviceState.NO_BIND_DEVICE;
    }

    public void d(final BaseHandler baseHandler, final long j, final long j2, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("CompatibilityInteractor", "getDeviceListFromWear time:", Long.valueOf(j), " currentTime:", Long.valueOf(j2));
        Message obtain = Message.obtain();
        obtain.what = 100;
        obtain.obj = iBaseResponseCallback;
        baseHandler.sendMessageDelayed(obtain, 500L);
        dwo.d().b(new IBaseResponseCallback() { // from class: com.huawei.ui.device.interactors.CompatibilityInteractor.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("CompatibilityInteractor", "getDeviceListFromWear onResponse errorCode:", Integer.valueOf(i));
                baseHandler.removeMessages(100, iBaseResponseCallback);
                if (Math.abs(System.currentTimeMillis() - j2) > j) {
                    LogUtil.a("CompatibilityInteractor", "getDeviceListFromWear outTime:", Long.valueOf(Math.abs(System.currentTimeMillis() - j2)));
                } else {
                    iBaseResponseCallback.d(i, obj);
                }
            }
        });
    }

    public void a(BaseHandler baseHandler, int i, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.c("CompatibilityInteractor", "isHuaweiWearBinded productId:", Integer.valueOf(i), " healthType:" + jpp.d(i));
        WearDeviceState wearDeviceState = WearDeviceState.NO_BIND_DEVICE;
        List<DeviceInfo> h = dwo.d().h();
        if (h != null && !a(BaseApplication.getContext())) {
            iBaseResponseCallback.d(0, c(h));
        } else {
            d(baseHandler, 700L, System.currentTimeMillis(), new IBaseResponseCallback() { // from class: com.huawei.ui.device.interactors.CompatibilityInteractor.9
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    String str = (String) obj;
                    LogUtil.a("CompatibilityInteractor", "isHuaweiWearBinded onResponse errorCode:", Integer.valueOf(i2), "  objData:", str);
                    CompatibilityInteractor.this.b("getDeviceList");
                    WearDeviceState wearDeviceState2 = WearDeviceState.NO_BIND_DEVICE;
                    if (i2 == 0 && obj != null) {
                        Gson gson = new Gson();
                        if (!(obj instanceof String)) {
                            str = null;
                        }
                        if (str != null) {
                            wearDeviceState2 = CompatibilityInteractor.this.c((List<DeviceInfo>) gson.fromJson(str, new TypeToken<List<DeviceInfo>>() { // from class: com.huawei.ui.device.interactors.CompatibilityInteractor.9.5
                            }.getType()));
                        }
                    }
                    iBaseResponseCallback.d(0, wearDeviceState2);
                    LogUtil.a("CompatibilityInteractor", "isHuaweiWearBinded wearDeviceState:", wearDeviceState2);
                }
            });
        }
    }

    public void c() {
        jdx.b(new Runnable() { // from class: com.huawei.ui.device.interactors.CompatibilityInteractor.8
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("CompatibilityInteractor", "Enter sendDeviceListChangeBroadcast()");
                cvw.c(oae.c(BaseApplication.getContext()).j(), "CompatibilityInteractor");
            }
        });
    }

    public boolean a() {
        return iyl.d().g() == 3;
    }

    private boolean b() {
        boolean e = !CommonUtil.bh() ? true : e(CommonUtil.r());
        LogUtil.a("CompatibilityInteractor", "isNeedNote:", Boolean.valueOf(e));
        return e;
    }

    private boolean e(String str) {
        boolean z = (str == null || str.length() == 0 || str.compareTo("8.0.0") <= 0) ? false : true;
        LogUtil.a("CompatibilityInteractor", "isLargerThen8_0_0 version:", str, " res:", Boolean.valueOf(z));
        return z;
    }

    public void d(Context context, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("CompatibilityInteractor", "Enter checkCompatibility");
        if (!CommonUtil.ce()) {
            LogUtil.a("CompatibilityInteractor", "not support ,return");
            iBaseResponseCallback.d(0, true);
            return;
        }
        c();
        if (context == null) {
            LogUtil.a("CompatibilityInteractor", "activityContext is null ,return");
            iBaseResponseCallback.d(0, true);
            return;
        }
        if (!b()) {
            LogUtil.a("CompatibilityInteractor", "not huawei or emui version low");
            iBaseResponseCallback.d(0, true);
            return;
        }
        e();
        if (!(context instanceof Activity)) {
            LogUtil.a("CompatibilityInteractor", "activityContext is not activity,return");
            iBaseResponseCallback.d(0, true);
        } else if (!a()) {
            LogUtil.a("CompatibilityInteractor", "system bluetooth is off,return");
            iBaseResponseCallback.d(0, true);
        } else {
            if (e(context, iBaseResponseCallback)) {
                return;
            }
            if (a(context)) {
                c(context, iBaseResponseCallback);
            } else {
                b(context, iBaseResponseCallback);
            }
        }
    }

    private boolean e(Context context, IBaseResponseCallback iBaseResponseCallback) {
        if (Math.abs(System.currentTimeMillis() - CommonUtil.n(BaseApplication.getContext(), SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10024), "THE_WEAR_HOMEFRAGMENT_SHOW_MIGRATE_TIME"))) < 259200000) {
            LogUtil.a("CompatibilityInteractor", "time doesn't match,return");
            iBaseResponseCallback.d(0, true);
            return true;
        }
        List<DeviceInfo> j = oae.c(context.getApplicationContext()).j();
        if (j != null && !j.isEmpty()) {
            LogUtil.a("CompatibilityInteractor", "exist bind device,return");
            iBaseResponseCallback.d(0, true);
            return true;
        }
        if ("true".equalsIgnoreCase(SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "wear_join_notice_to_migrate"))) {
            return false;
        }
        LogUtil.a("CompatibilityInteractor", "cloud switch is close,return");
        iBaseResponseCallback.d(0, true);
        return true;
    }

    private void b(final Context context, final IBaseResponseCallback iBaseResponseCallback) {
        b.execute(new Runnable() { // from class: com.huawei.ui.device.interactors.CompatibilityInteractor.10
            @Override // java.lang.Runnable
            public void run() {
                List<DeviceInfo> h = dwo.d().h();
                if (h != null && !h.isEmpty()) {
                    if (CompatibilityInteractor.this.e(h)) {
                        DeviceInfo a2 = CompatibilityInteractor.this.a(h);
                        if (a2 != null) {
                            iBaseResponseCallback.d(0, false);
                            CompatibilityInteractor.this.d(context, a2, false);
                            return;
                        } else {
                            iBaseResponseCallback.d(0, true);
                            return;
                        }
                    }
                    iBaseResponseCallback.d(0, true);
                    return;
                }
                iBaseResponseCallback.d(0, true);
                LogUtil.a("CompatibilityInteractor", "no device 2");
            }
        });
    }

    private void c(final Context context, final IBaseResponseCallback iBaseResponseCallback) {
        final long currentTimeMillis = System.currentTimeMillis();
        b.execute(new Runnable() { // from class: com.huawei.ui.device.interactors.CompatibilityInteractor.7
            @Override // java.lang.Runnable
            public void run() {
                if (CommonUtil.ce()) {
                    cvw.c(oae.c(BaseApplication.getContext()).j(), "CompatibilityInteractor");
                }
                dwo.d().b(new IBaseResponseCallback() { // from class: com.huawei.ui.device.interactors.CompatibilityInteractor.7.1
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        LogUtil.a("CompatibilityInteractor", "getDeviceListFromWear onResponse errorCode:", Integer.valueOf(i));
                        if (Math.abs(System.currentTimeMillis() - currentTimeMillis) > 500) {
                            LogUtil.a("CompatibilityInteractor", "getDeviceListFromWear outtime:", Long.valueOf(Math.abs(System.currentTimeMillis() - currentTimeMillis)));
                            iBaseResponseCallback.d(0, true);
                            return;
                        }
                        if (i != 0 || obj == null) {
                            return;
                        }
                        List<DeviceInfo> list = (List) new Gson().fromJson((String) obj, new TypeToken<List<DeviceInfo>>() { // from class: com.huawei.ui.device.interactors.CompatibilityInteractor.7.1.4
                        }.getType());
                        if (list == null || list.isEmpty()) {
                            LogUtil.a("CompatibilityInteractor", "no device");
                            iBaseResponseCallback.d(0, true);
                        } else if (CompatibilityInteractor.this.e(list)) {
                            DeviceInfo a2 = CompatibilityInteractor.this.a(list);
                            if (a2 == null) {
                                iBaseResponseCallback.d(0, true);
                            } else {
                                iBaseResponseCallback.d(0, false);
                                CompatibilityInteractor.this.d(context, a2, true);
                            }
                        }
                    }
                });
            }
        });
    }

    public void d(Context context, DeviceInfo deviceInfo, boolean z) {
        LogUtil.a("CompatibilityInteractor", "Enter showDialogToMigrate:", " isNewVersion:", Boolean.valueOf(z));
        StorageParams storageParams = new StorageParams();
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10024), "THE_WEAR_HOMEFRAGMENT_SHOW_MIGRATE_TIME", System.currentTimeMillis() + "", storageParams);
        e(context, deviceInfo, z);
    }

    public void a(final Context context, final DeviceInfo deviceInfo, final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.b("CompatibilityInteractor", "Enter showDialogToMigrateLowVersion");
        ((Activity) context).runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.interactors.CompatibilityInteractor.12
            @Override // java.lang.Runnable
            public void run() {
                CompatibilityInteractor.this.e(context, deviceInfo, iBaseResponseCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final Context context, final DeviceInfo deviceInfo, final IBaseResponseCallback iBaseResponseCallback) {
        CustomAlertDialog.Builder e = new CustomAlertDialog.Builder(context).e(R.string.IDS_service_area_notice_title);
        e.cyo_(R.string._2130842298_res_0x7f0212ba, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.interactors.CompatibilityInteractor.11
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                CompatibilityInteractor.this.a(deviceInfo, context, iBaseResponseCallback);
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
        e.cyn_(R.string._2130841130_res_0x7f020e2a, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.interactors.CompatibilityInteractor.14
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                LogUtil.a("CompatibilityInteractor", "showDialogToMigrateLowVersion setNegativeButton onclick");
                if (CompatibilityInteractor.this.e != null) {
                    CompatibilityInteractor.this.e.dismiss();
                    CompatibilityInteractor.this.e = null;
                }
                IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                if (iBaseResponseCallback2 != null) {
                    iBaseResponseCallback2.d(0, "CANCLE");
                }
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
        List<DeviceInfo> h = dwo.d().h();
        if (h == null) {
            LogUtil.a("CompatibilityInteractor", "showDialogToMigrate deviceInfoList is null");
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(0, "CANCLE");
                return;
            }
            return;
        }
        e.cyp_(cTC_(context, h));
        if (this.e == null) {
            CustomAlertDialog c = e.c();
            this.e = c;
            c.setCanceledOnTouchOutside(false);
            this.e.show();
        }
    }

    private View cTC_(Context context, List<DeviceInfo> list) {
        String string = context.getResources().getString(R.string._2130842296_res_0x7f0212b8);
        String string2 = context.getResources().getString(R.string._2130841377_res_0x7f020f21);
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append(String.format(Locale.ENGLISH, string, string2));
        for (DeviceInfo deviceInfo : list) {
            if (deviceInfo != null && e(deviceInfo.getProductType())) {
                stringBuffer.append(System.lineSeparator());
                stringBuffer.append(deviceInfo.getDeviceName());
            }
        }
        stringBuffer.append(System.lineSeparator());
        stringBuffer.append(String.format(Locale.ENGLISH, context.getResources().getString(R.string._2130842300_res_0x7f0212bc), string2));
        stringBuffer.append(System.lineSeparator());
        stringBuffer.append(System.lineSeparator());
        stringBuffer.append(String.format(Locale.ENGLISH, context.getResources().getString(R.string._2130842297_res_0x7f0212b9), string2));
        View inflate = LayoutInflater.from(BaseApplication.getContext()).inflate(R.layout.dialog_migrate_low_version, (ViewGroup) null);
        HealthCheckBox healthCheckBox = inflate.findViewById(R.id.compatibility_remind) instanceof HealthCheckBox ? (HealthCheckBox) inflate.findViewById(R.id.compatibility_remind) : null;
        if (healthCheckBox != null) {
            healthCheckBox.setVisibility(8);
        }
        ((HealthTextView) inflate.findViewById(R.id.compatibility_content)).setText(stringBuffer.toString());
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(DeviceInfo deviceInfo, Context context, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("CompatibilityInteractor", "showDialogToMigrate setPositiveButton onclick");
        if (deviceInfo != null) {
            String b2 = oae.c(BaseApplication.getContext()).b(dul.c().c(deviceInfo.getProductType()));
            LogUtil.a("CompatibilityInteractor", "productName:", b2);
            boolean equals = "PORSCHE DESIGN".equals(b2);
            Intent intent = new Intent();
            intent.putExtra(DeviceCategoryFragment.DEVICE_TYPE, dul.c().c(deviceInfo.getProductType()));
            intent.putExtra("dname", b2);
            intent.putExtra("isPorc", equals);
            intent.putExtra("isFromWear", true);
            intent.setClass(context, AddDeviceIntroActivity.class);
            try {
                context.startActivity(intent);
            } catch (ActivityNotFoundException unused) {
                LogUtil.b("CompatibilityInteractor", "posiviteButtonClick startActivity has ActivityNotFoundException");
            }
        }
        CommonUtil.ak(BaseApplication.getContext());
        CustomAlertDialog customAlertDialog = this.e;
        if (customAlertDialog != null) {
            customAlertDialog.dismiss();
            this.e = null;
        }
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(0, "SURE");
        }
    }

    public void e(final Context context, final DeviceInfo deviceInfo, final boolean z) {
        LogUtil.b("CompatibilityInteractor", "Enter showDialogToMigrate");
        if (context instanceof Activity) {
            ((Activity) context).runOnUiThread(new Runnable() { // from class: com.huawei.ui.device.interactors.CompatibilityInteractor.15
                @Override // java.lang.Runnable
                public void run() {
                    CompatibilityInteractor.this.b(context, z, deviceInfo);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final Context context, final boolean z, final DeviceInfo deviceInfo) {
        CustomAlertDialog.Builder e = new CustomAlertDialog.Builder(context).e(R.string.IDS_service_area_notice_title);
        e.cyo_(R.string.IDS_device_start_paring_title, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.interactors.CompatibilityInteractor.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                CompatibilityInteractor.this.a(z, deviceInfo, context);
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
        e.cyn_(R.string.IDS_device_auto_scan_cancel_button, new DialogInterface.OnClickListener() { // from class: com.huawei.ui.device.interactors.CompatibilityInteractor.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                LogUtil.a("CompatibilityInteractor", "showDialogToMigrate setNegativeButton onclick");
                if (CompatibilityInteractor.this.c != null) {
                    CompatibilityInteractor.this.c.dismiss();
                    CompatibilityInteractor.this.c = null;
                }
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        });
        View inflate = LayoutInflater.from(BaseApplication.getContext()).inflate(R.layout.dialog_migrate_low_version, (ViewGroup) null);
        HealthCheckBox healthCheckBox = (HealthCheckBox) inflate.findViewById(R.id.compatibility_remind);
        ((HealthTextView) inflate.findViewById(R.id.compatibility_content)).setText(String.format(Locale.ENGLISH, context.getResources().getString(R.string._2130842338_res_0x7f0212e2), context.getResources().getString(R.string.IDS_app_name_health), deviceInfo.getDeviceName()));
        healthCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.device.interactors.CompatibilityInteractor.4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                CompatibilityInteractor.this.c(z2);
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        });
        e.cyp_(inflate);
        if (this.c == null) {
            CustomAlertDialog c = e.c();
            this.c = c;
            c.setCanceledOnTouchOutside(false);
            this.c.show();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z) {
        StorageParams storageParams = new StorageParams();
        LogUtil.a("CompatibilityInteractor", "showDialogToMigrate isChecked:", Boolean.valueOf(z));
        if (z) {
            LogUtil.a("CompatibilityInteractor", "showDialogToMigrate setNegativeButton onclick");
            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10024), "wear_join_not_notice_any_more", "false", storageParams);
        } else {
            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10024), "wear_join_not_notice_any_more", "true", storageParams);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z, DeviceInfo deviceInfo, Context context) {
        LogUtil.a("CompatibilityInteractor", "showDialogToMigrate setPositiveButton onclick");
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HOME_1010048.value(), new HashMap(16), 0);
        if (z) {
            Intent intent = new Intent();
            int c = dul.c().c(deviceInfo.getProductType());
            intent.putExtra("pairGuideProductType", c);
            intent.putExtra("pairGuideProductName", deviceInfo.getDeviceName());
            if (c == 10 || c == 3) {
                intent.putExtra("pairGuideFromScanList", false);
            } else {
                intent.putExtra("pairGuideFromScanList", true);
            }
            intent.putExtra("pairGuideSelectName", deviceInfo.getDeviceName());
            intent.putExtra("pairGuideSelectAddress", deviceInfo.getDeviceIdentify());
            intent.setClass(context, DevicePairGuideActivity.class);
            try {
                context.startActivity(intent);
            } catch (ActivityNotFoundException unused) {
                LogUtil.b("CompatibilityInteractor", "uploadClick startActivity has ActivityNotFoundException");
            }
            CustomAlertDialog customAlertDialog = this.c;
            if (customAlertDialog != null) {
                customAlertDialog.dismiss();
                this.c = null;
                return;
            }
            return;
        }
        a(context, deviceInfo, (IBaseResponseCallback) null);
    }

    public DeviceInfo a(List<DeviceInfo> list) {
        DeviceInfo deviceInfo;
        Iterator<DeviceInfo> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                deviceInfo = null;
                break;
            }
            deviceInfo = it.next();
            if (deviceInfo.getDeviceActiveState() == 1) {
                break;
            }
        }
        LogUtil.a("CompatibilityInteractor", "getAchievedDevice:");
        return deviceInfo;
    }

    public boolean e(List<DeviceInfo> list) {
        Iterator<DeviceInfo> it = list.iterator();
        boolean z = false;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            DeviceInfo next = it.next();
            LogUtil.a("CompatibilityInteractor", "isAchievedDeviceSupportedInHealth():", next.toString());
            if (next.getDeviceActiveState() != 1 || !e(next.getProductType())) {
                break;
            }
            if (dul.c().c(next.getProductType()) != 10) {
                z = true;
                break;
            }
            LogUtil.a("CompatibilityInteractor", "isAchievedDeviceSupportedInHealth is leo:");
            String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10024), next.getUuid());
            LogUtil.a("CompatibilityInteractor", "isAchievedDeviceSupportedInHealth:", next.getUuid(), " result:", b2);
            if ("true".equals(b2)) {
                z = true;
            }
        }
        LogUtil.a("CompatibilityInteractor", "isAchievedDeviceSupportedInHealth:", Boolean.valueOf(z));
        return z;
    }

    private void e() {
        LogUtil.a("CompatibilityInteractor", "Enter getLeoStatus");
        if (a(BaseApplication.getContext())) {
            jdx.b(new Runnable() { // from class: com.huawei.ui.device.interactors.CompatibilityInteractor.5
                @Override // java.lang.Runnable
                public void run() {
                    List<DeviceInfo> h;
                    if (!CompatibilityInteractor.this.a(BaseApplication.getContext()) || (h = dwo.d().h()) == null || h.isEmpty()) {
                        return;
                    }
                    for (DeviceInfo deviceInfo : h) {
                        if (dul.c().c(deviceInfo.getProductType()) == 10) {
                            LogUtil.a("CompatibilityInteractor", "getLeoStatus uuid:", deviceInfo.getUuid());
                            final String uuid = deviceInfo.getUuid();
                            dwo.d().c(uuid, new IBaseResponseCallback() { // from class: com.huawei.ui.device.interactors.CompatibilityInteractor.5.1
                                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                                /* renamed from: onResponse */
                                public void d(int i, Object obj) {
                                    LogUtil.a("CompatibilityInteractor", "not getLeoStatus ,errorCode ", Integer.valueOf(i), " objData", obj, " name:", uuid);
                                    if (i != 0 || obj == null) {
                                        return;
                                    }
                                    SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10024), uuid, "" + obj, new StorageParams(0));
                                }
                            });
                        }
                    }
                }
            });
        }
    }

    public boolean d(List<DeviceInfo> list) {
        boolean z = false;
        for (DeviceInfo deviceInfo : list) {
            if (e(deviceInfo.getProductType())) {
                if (dul.c().c(deviceInfo.getProductType()) == 10) {
                    String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10024), deviceInfo.getUuid());
                    LogUtil.a("CompatibilityInteractor", "isDeviceSupportedInHealth:", deviceInfo.getUuid(), " result:", b2);
                    if ("true".equals(b2)) {
                    }
                }
                z = true;
            }
        }
        LogUtil.a("CompatibilityInteractor", "isDeviceSupportedInHealth:", Boolean.valueOf(z));
        return z;
    }

    public boolean b(List<DeviceInfo> list) {
        Iterator<DeviceInfo> it = list.iterator();
        boolean z = false;
        while (it.hasNext()) {
            if (e(it.next().getProductType())) {
                z = true;
            }
        }
        LogUtil.a("CompatibilityInteractor", "isDeviceSupportedInHealthForLeo:", Boolean.valueOf(z));
        return z;
    }

    public boolean e(int i) {
        boolean l = jfu.l(dul.c().c(i));
        LogUtil.a("CompatibilityInteractor", "isProductedSuppopted healthproduct:", Integer.valueOf(i), " isSupport:", Boolean.valueOf(l), " wearType:", Integer.valueOf(dul.c().c(i)));
        return l;
    }

    public static boolean c(Context context) {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo("com.huawei.bone", 0);
        } catch (PackageManager.NameNotFoundException unused) {
            LogUtil.c("CompatibilityInteractor", "checkHealth packageInfo == NULL");
            packageInfo = null;
        }
        return packageInfo != null;
    }

    public void b(int i, HealthSupportModel healthSupportModel) {
        if (healthSupportModel != null && a(BaseApplication.getContext())) {
            final StorageParams storageParams = new StorageParams();
            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10024), "wear_join_supported_device", this.f9291a.toJson(healthSupportModel), storageParams);
            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10024), "wear_join_send_supported_falg", "false", storageParams);
            HealthSupportModel c = cvw.c();
            HuaweiHealthData huaweiHealthData = new HuaweiHealthData();
            huaweiHealthData.setCommandType(i);
            huaweiHealthData.setData(this.f9291a.toJson(c));
            dwo.d().d(this.f9291a.toJson(huaweiHealthData), new IBaseResponseCallback() { // from class: com.huawei.ui.device.interactors.CompatibilityInteractor.6
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    LogUtil.a("CompatibilityInteractor", "sendHealthDataTohealth onResponse:", Integer.valueOf(i2));
                    SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10024), "wear_join_send_supported_falg", "true", storageParams);
                }
            });
            return;
        }
        StorageParams storageParams2 = new StorageParams();
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10024), "wear_join_send_supported_falg", "true", storageParams2);
        if (healthSupportModel != null) {
            SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10024), "wear_join_supported_device", this.f9291a.toJson(healthSupportModel), storageParams2);
        }
    }

    public String d() {
        HealthSupportModel c = cvw.c();
        ArrayList arrayList = new ArrayList(16);
        if (c.isSupportLeo()) {
            arrayList.add(10);
        }
        if (c.isSupportMetis()) {
            arrayList.add(8);
        }
        if (c.isSupportA2()) {
            arrayList.add(12);
        }
        if (c.isSupportNyx()) {
            arrayList.add(13);
        }
        if (c.isSupportB3Lite()) {
            arrayList.add(14);
        }
        if (c.isSupportB3()) {
            arrayList.add(7);
        }
        if (c.isSupportR1()) {
            arrayList.add(11);
        }
        if (c.isSupportEris()) {
            arrayList.add(15);
        }
        StringBuffer stringBuffer = new StringBuffer(16);
        if (arrayList.size() > 1) {
            for (int i = 0; i < arrayList.size(); i++) {
                if (i < arrayList.size() - 1) {
                    stringBuffer.append(arrayList.get(i) + ",");
                } else {
                    stringBuffer.append(arrayList.get(i));
                }
            }
        } else if (1 == arrayList.size()) {
            stringBuffer.append(arrayList.get(0));
        } else {
            LogUtil.a("CompatibilityInteractor", "productTypeLis other size");
        }
        String stringBuffer2 = stringBuffer.toString();
        LogUtil.a("CompatibilityInteractor", "getSupportTypeList:", stringBuffer2);
        return stringBuffer2;
    }

    public void b(String str) {
        dwo.d().e(str);
    }
}
