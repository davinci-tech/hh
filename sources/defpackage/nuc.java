package defpackage;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.devicepair.model.StartPairOption;
import com.huawei.devicesdk.callback.DeviceScanCallback;
import com.huawei.devicesdk.entity.ScanMode;
import com.huawei.health.R;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.ui.privacy.HonorDeviceShowPrivacyActivity;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction;
import com.huawei.hwdevice.outofprocess.util.NotificationContentProviderUtil;
import com.huawei.hwversionmgr.manager.HwVersionManager;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.AdShowExtras;
import com.huawei.secure.android.common.SafeIntent;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.device.activity.adddevice.DevicePairGuideSecondActivity;
import com.huawei.ui.device.activity.adddevice.OneKeyScanActivity;
import com.huawei.ui.device.activity.pairing.DevicePairGuideActivity;
import com.huawei.ui.device.interactors.CompatibilityInteractor;
import com.huawei.ui.device.utlis.BluetoothPermisionUtils;
import com.huawei.unitedevice.entity.UniteDevice;
import health.compact.a.CommonUtil;
import health.compact.a.CompileParameterUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.util.LogUtil;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

/* loaded from: classes6.dex */
public class nuc {
    public static final List<String> c = new ArrayList<String>() { // from class: nuc.5
        {
            add("wear_watch");
            add("wear_band");
            add("SMART_HEADPHONES");
            add("HDK_HEART_RATE");
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private int f15495a;
    private String b;
    private String d;
    private String e;
    private boolean f;
    private oae g;
    private boolean h = false;
    private CustomViewDialog i;
    private String j;
    private String k;
    private String l;
    private nyq m;

    private boolean e(int i, int i2) {
        boolean z = i == 0 && i2 == 1;
        if (i == 1 && i2 == 2) {
            return true;
        }
        return z;
    }

    private boolean c() {
        return this.f;
    }

    public void c(boolean z) {
        this.f = z;
    }

    public void c(int i, String str) {
        this.f15495a = i;
        this.e = str;
    }

    public void a(boolean z) {
        this.h = z;
    }

    public void e(String str) {
        this.b = str;
    }

    public ArrayList<nyr> c(Map<nyr, Integer> map) {
        ArrayList<nyr> arrayList = new ArrayList<>(16);
        if (map != null && !map.isEmpty()) {
            ArrayList<Map.Entry> arrayList2 = new ArrayList(map.entrySet());
            Collections.sort(arrayList2, new Comparator<Map.Entry<nyr, Integer>>() { // from class: nuc.9
                @Override // java.util.Comparator
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public int compare(Map.Entry<nyr, Integer> entry, Map.Entry<nyr, Integer> entry2) {
                    if (entry == null || entry2 == null || entry.getValue() == null || entry2.getValue() == null) {
                        return 0;
                    }
                    return entry.getValue().intValue() - entry2.getValue().intValue();
                }
            });
            for (Map.Entry entry : arrayList2) {
                if (entry.getKey() != null && !TextUtils.isEmpty(((nyr) entry.getKey()).b())) {
                    arrayList.add((nyr) entry.getKey());
                    LogUtil.d("DeviceEntranceUtil", "sortItem itemList name:", ((nyr) entry.getKey()).b(), " rssi ", Integer.valueOf(((nyr) entry.getKey()).o()));
                }
            }
            LogUtil.d("DeviceEntranceUtil", "sortItem itemList num:", Integer.valueOf(arrayList.size()));
        }
        return arrayList;
    }

    public void d(ScanMode scanMode, List<bjf> list, final OneKeyScanActivity.a aVar) {
        ReleaseLogUtil.e("DeviceEntranceUtil", "scanDevice:", Integer.valueOf(list.size()));
        snq.c().scanDevice(scanMode, list, new DeviceScanCallback() { // from class: nuc.8
            @Override // com.huawei.devicesdk.callback.DeviceScanCallback
            public void scanResult(UniteDevice uniteDevice, byte[] bArr, String str, int i) {
                if (uniteDevice != null && uniteDevice.getDeviceInfo() != null) {
                    LogUtil.d("DeviceEntranceUtil", "scanResult name:", uniteDevice.getDeviceInfo().getDeviceName(), " addition :", str, " status :", Integer.valueOf(i));
                    try {
                        nyr nyrVar = new nyr();
                        nyrVar.c(uniteDevice.getDeviceInfo().getDeviceName());
                        nyrVar.a(uniteDevice.getDeviceInfo().getDeviceMac());
                        bji bjiVar = (bji) new Gson().fromJson(str, bji.class);
                        if (nuc.this.c(nyrVar, bjiVar)) {
                            return;
                        }
                        if (nuc.this.d(nyrVar, bjiVar)) {
                            LogUtil.d("DeviceEntranceUtil", "isHideByProduct true");
                            return;
                        }
                        int abs = Math.abs(bjiVar.a());
                        LogUtil.d("DeviceEntranceUtil", "scanResult rssi:", Integer.valueOf(abs));
                        nyrVar.e(abs);
                        nyrVar.d(bjiVar.d());
                        if (aVar != null) {
                            Message obtain = Message.obtain();
                            obtain.what = 1;
                            obtain.obj = nyrVar;
                            aVar.sendMessage(obtain);
                            return;
                        }
                        return;
                    } catch (JsonSyntaxException unused) {
                        LogUtil.e("DeviceEntranceUtil", "scanResult JsonSyntaxException");
                        return;
                    }
                }
                LogUtil.d("DeviceEntranceUtil", "scanResult device is null,", " addition :", str, " status :", Integer.valueOf(i));
                if (i == 21 && aVar != null) {
                    Message obtain2 = Message.obtain();
                    obtain2.what = 2;
                    aVar.removeMessages(3);
                    aVar.sendMessage(obtain2);
                    return;
                }
                if (i != 22 || aVar == null) {
                    return;
                }
                Message obtain3 = Message.obtain();
                obtain3.what = 3;
                aVar.removeMessages(3);
                aVar.sendMessage(obtain3);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(nyr nyrVar, bji bjiVar) {
        cve cveVar;
        List<cve> deviceInfoByBluetooth = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getDeviceInfoByBluetooth(nyrVar.b());
        if (!koq.b(deviceInfoByBluetooth) && (cveVar = deviceInfoByBluetooth.get(0)) != null && !TextUtils.isEmpty(cveVar.r()) && cveVar.r().startsWith("wear_")) {
            LogUtil.d("DeviceEntranceUtil", "scanResult is wear device, bt type: ", Integer.valueOf(cveVar.d()), " scan bt type: ", Integer.valueOf(bjiVar.d()));
            if (e(cveVar.d(), bjiVar.d())) {
                LogUtil.d("DeviceEntranceUtil", "scanResult need intercept");
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(nyr nyrVar, bji bjiVar) {
        List<cve> deviceInfoByBluetooth = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).getDeviceInfoByBluetooth(nyrVar.b());
        if (koq.b(deviceInfoByBluetooth)) {
            LogUtil.c("DeviceEntranceUtil", "isHideByProduct infoBeans is null");
            return false;
        }
        cve cveVar = deviceInfoByBluetooth.get(0);
        if (cveVar == null || TextUtils.isEmpty(cveVar.r()) || !cveVar.r().startsWith("wear_")) {
            return false;
        }
        LogUtil.d("DeviceEntranceUtil", "isHideByProduct info.getDeviceName():", cveVar.n(), "; item.getDeviceName():", nyrVar.b());
        if (bjiVar.d() == 2) {
            return !(!"IDS_huawei_watch_gt_series".equals(cveVar.n()) || nyrVar.b().startsWith("fortuna-") || nyrVar.b().startsWith("HUAWEI WATCH GT-")) || nyrVar.b().startsWith("HUAWEI Yoda-") || nyrVar.b().startsWith("HUAWEI WATCH FIT 2-") || nyrVar.b().startsWith("HUAWEI WATCH B5-") || nyrVar.b().startsWith("HUAWEI WATCH B7-") || nyrVar.b().startsWith("HUAWEI WATCH FIT 3-") || nyrVar.b().startsWith("HUAWEI SOLO-") || nyrVar.b().startsWith("HUAWEI WATCH D2-") || nyrVar.b().startsWith("HUAWEI LUCA-") || nyrVar.b().startsWith("HUAWEI WATCH FIT 4-") || nyrVar.b().startsWith("HUAWEI SEIYA-");
        }
        return false;
    }

    public void b(Context context, nyr nyrVar, boolean z, boolean z2) {
        if (context == null || nyrVar == null) {
            return;
        }
        List<String> k = nyrVar.k();
        if (koq.b(k)) {
            LogUtil.c("DeviceEntranceUtil", "uuidList is null");
            return;
        }
        Intent intent = new Intent();
        intent.setFlags(268435456);
        intent.setAction("SWITCH_PLUGINDEVICE");
        intent.setPackage(BaseApplication.getAppPackage());
        intent.setClassName(context, "com.huawei.health.device.ui.DeviceMainActivity");
        intent.putExtra("is_go_rope_jump", z2);
        if (k instanceof ArrayList) {
            intent.putStringArrayListExtra("uuid_list", (ArrayList) k);
        }
        intent.putExtra("KEY_INTENT_COURSE_ENTRANCE", this.f15495a);
        intent.putExtra("KEY_INTENT_EQUIPMENT_TYPE", this.e);
        intent.putExtra("DeviceType", nyrVar.i());
        intent.putExtra("DeviceName", nyrVar.b());
        intent.putExtra("DeviceIconPath", nyrVar.c());
        intent.putExtra("arg1", "DeviceBindWaitingFragment");
        intent.putExtra(AdShowExtras.DOWNLOAD_SOURCE, cvy.c(nyrVar.g()));
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", nyrVar.h());
        if (k.size() > 1) {
            contentValues.put("productId", "");
        } else {
            contentValues.put("productId", k.get(0));
        }
        intent.putExtra("commonDeviceInfo", contentValues);
        if ((context instanceof Activity) && z) {
            ((Activity) context).startActivityForResult(intent, 4);
            return;
        }
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.e("DeviceEntranceUtil", "ActivityNotFoundException e:", e.getMessage());
        }
    }

    public void d(Context context, nyr nyrVar, boolean z, boolean z2) {
        Intent intent;
        if (context == null || nyrVar == null) {
            return;
        }
        if (nyrVar.i().equals("HDK_WEIGHT")) {
            intent = cOq_(context, nyrVar);
        } else {
            Intent cOp_ = cOp_(nyrVar.k().get(0), nyrVar.h(), nyrVar.i(), nyrVar.n(), nyrVar.c());
            cOp_.putExtra(AdShowExtras.DOWNLOAD_SOURCE, cvy.c(nyrVar.g()));
            cOp_.putExtra("is_go_rope_jump", z2);
            intent = cOp_;
        }
        if ((context instanceof Activity) && z) {
            ((Activity) context).startActivityForResult(intent, 4);
            return;
        }
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.e("DeviceEntranceUtil", "ActivityNotFoundException e:", e.getMessage());
        }
    }

    public void d(Context context, nyr nyrVar, String str) {
        if (nyrVar == null) {
            LogUtil.c("DeviceEntranceUtil", "showPrivacyActivity itemData is null");
            return;
        }
        Intent intent = new Intent(BaseApplication.getContext(), (Class<?>) HonorDeviceShowPrivacyActivity.class);
        Map<String, String> j = nyrVar.j();
        if (j == null) {
            this.k = nyrVar.c();
        } else {
            this.k = j.get(str);
        }
        String str2 = this.k;
        if (str2 != null) {
            LogUtil.d("DeviceEntranceUtil", "showPrivacyActivity mPrivacyImagePath not null and path = ", str2);
            intent.putExtra("image_path", this.k);
        } else {
            LogUtil.c("DeviceEntranceUtil", "showPrivacyActivity mPrivacyImagePath is null");
        }
        if (context instanceof Activity) {
            ((Activity) context).startActivityForResult(intent, 1001);
            return;
        }
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.e("DeviceEntranceUtil", "ActivityNotFoundException e:", e.getMessage());
        }
    }

    public void cOv_(Context context, nyr nyrVar, List<String> list, BroadcastReceiver broadcastReceiver, boolean z) {
        String b = b(nyrVar);
        if (CompileParameterUtil.a("IS_LUPIN_SUPPORT_PRIVACY", false) && "25c6df38-ca23-11e9-a32f-2a2ae2dbcce4".equals(b)) {
            boolean e = SharedPreferenceManager.e(BaseApplication.getContext(), LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
            if (dks.d(BaseApplication.getContext()) && !e) {
                d(context, nyrVar, b);
                return;
            } else {
                cOr_(context, nyrVar, list, broadcastReceiver, z);
                return;
            }
        }
        cOr_(context, nyrVar, list, broadcastReceiver, z);
    }

    private void cOr_(Context context, nyr nyrVar, List<String> list, BroadcastReceiver broadcastReceiver, boolean z) {
        if (!koq.b(list)) {
            LogUtil.d("DeviceEntranceUtil", "thirdPartyAndPrivacy openThirdParty uuidlist no null");
            d(context, nyrVar, true, z);
        } else {
            LogUtil.d("DeviceEntranceUtil", "thirdPartyAndPrivacy openThirdParty uuidlist null");
            b(context, nyrVar, false, z);
        }
        cOu_(broadcastReceiver);
    }

    public void cOu_(BroadcastReceiver broadcastReceiver) {
        try {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.huawei.health.action.PAIR_DEVICE_SUCCESS");
            LocalBroadcastManager.getInstance(BaseApplication.getContext()).registerReceiver(broadcastReceiver, intentFilter);
        } catch (IllegalArgumentException unused) {
            LogUtil.e("DeviceEntranceUtil", "resisterReceiver, broadcastReceiver is error");
        }
    }

    public void cOt_(Context context, nyr nyrVar, List<String> list, Intent intent, BroadcastReceiver broadcastReceiver) {
        if (intent == null) {
            LogUtil.c("DeviceEntranceUtil", "data is null");
            return;
        }
        int intExtra = intent.getIntExtra("honor_show_privacy_key", 0);
        LogUtil.d("DeviceEntranceUtil", "privacy privacyStatus", Integer.valueOf(intExtra));
        if (intExtra == 1) {
            LogUtil.d("DeviceEntranceUtil", "user agree privacy");
            if (!koq.b(list)) {
                d(context, nyrVar, true, false);
            } else {
                b(context, nyrVar, false, false);
            }
            cOu_(broadcastReceiver);
            return;
        }
        LogUtil.c("DeviceEntranceUtil", "user do not agree privacy");
    }

    public void a(Context context, nyq nyqVar) {
        if (context == null || nyqVar == null) {
            return;
        }
        try {
            Intent cOp_ = cOp_(nyqVar.s().get(0), null, nyqVar.f(), nyqVar.h(), nyqVar.i());
            cOp_.putExtra(AdShowExtras.DOWNLOAD_SOURCE, cvy.c(nyqVar.g()));
            context.startActivity(cOp_);
        } catch (ActivityNotFoundException e) {
            LogUtil.e("DeviceEntranceUtil", "ActivityNotFoundException e:", e.getMessage());
        }
    }

    public void e(Context context, dcz dczVar) {
        if (context == null || dczVar == null) {
            return;
        }
        if (!a(dczVar)) {
            LogUtil.c("DeviceEntranceUtil", "startThirdPartyH5PairGuide device resource can not use");
            return;
        }
        boolean equals = "true".equals(gmz.d().c(402));
        if ("H5".equals(dczVar.b()) && equals) {
            b(context, dczVar.t());
        } else if ("H5".equals(dczVar.b()) && !equals) {
            e(context, dczVar.t());
        } else {
            LogUtil.c("DeviceEntranceUtil", "startThirdPartyH5PairGuide else");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0043, code lost:
    
        if (r0 >= r2) goto L15;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x0033, code lost:
    
        if (r0 >= java.lang.Long.parseLong(r1)) goto L9;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean a(defpackage.dcz r8) {
        /*
            r7 = this;
            android.content.Context r0 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            int r0 = health.compact.a.CommonUtil.d(r0)
            java.lang.String r1 = "isDeviceResourceCanUsing currentVersion ="
            java.lang.Integer r2 = java.lang.Integer.valueOf(r0)
            java.lang.Object[] r1 = new java.lang.Object[]{r1, r2}
            java.lang.String r2 = "DeviceEntranceUtil"
            health.compact.a.util.LogUtil.d(r2, r1)
            java.lang.String r1 = r8.a()
            java.lang.String r8 = r8.c()
            boolean r3 = health.compact.a.CloudUtils.d()     // Catch: java.lang.NumberFormatException -> L46
            r4 = 1
            if (r3 != 0) goto L36
            boolean r8 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.NumberFormatException -> L46
            if (r8 != 0) goto L35
            long r5 = (long) r0     // Catch: java.lang.NumberFormatException -> L46
            long r0 = java.lang.Long.parseLong(r1)     // Catch: java.lang.NumberFormatException -> L46
            int r8 = (r5 > r0 ? 1 : (r5 == r0 ? 0 : -1))
            if (r8 < 0) goto L4f
        L35:
            return r4
        L36:
            boolean r1 = android.text.TextUtils.isEmpty(r8)     // Catch: java.lang.NumberFormatException -> L46
            if (r1 != 0) goto L45
            long r0 = (long) r0     // Catch: java.lang.NumberFormatException -> L46
            long r2 = java.lang.Long.parseLong(r8)     // Catch: java.lang.NumberFormatException -> L46
            int r8 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r8 < 0) goto L4f
        L45:
            return r4
        L46:
            java.lang.String r8 = "isDeviceResourceCanUsing NumberFormatException"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            health.compact.a.util.LogUtil.e(r2, r8)
        L4f:
            r8 = 0
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.nuc.a(dcz):boolean");
    }

    private void e(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.e("DeviceEntranceUtil", "startHealthKitActivity productId is null");
            return;
        }
        Intent intent = new Intent();
        intent.setPackage(context.getPackageName());
        intent.setClassName(context, "com.huawei.ui.thirdpartservice.activity.healthkit.HealthKitActivity");
        intent.putExtra("productId", str);
        intent.putExtra("device_list_to_health_kit_authorization", true);
        dcz d = ResourceManager.e().d(str);
        ContentValues contentValues = new ContentValues();
        if (d == null) {
            LogUtil.e("DeviceEntranceUtil", "startHealthKitActivity deviceProductInfo is null");
            return;
        }
        contentValues.put("name", d.n().b());
        contentValues.put("deviceType", d.l().name());
        intent.putExtra("commonDeviceInfo", contentValues);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.e("DeviceEntranceUtil", "ActivityNotFoundException e:", e.getMessage());
        }
    }

    private void b(Context context, String str) {
        Intent intent = new Intent();
        intent.setPackage(context.getPackageName());
        intent.setClassName(context, "com.huawei.operation.activity.WebViewActivity");
        intent.putExtra("url", dcq.b().c(str) + "#/type=4/uuidpre=" + dks.b(str));
        intent.putExtra("productId", str);
        dcz d = ResourceManager.e().d(str);
        ContentValues contentValues = new ContentValues();
        if (d == null) {
            return;
        }
        contentValues.put("name", dcx.d(str, d.n().b()));
        contentValues.put("deviceType", d.l().name());
        intent.putExtra("commonDeviceInfo", contentValues);
        LogUtil.d("DeviceEntranceUtil", "sannuo_type=4_device list page to H5 interface,Productid=", str);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.e("DeviceEntranceUtil", "ActivityNotFoundException e:", e.getMessage());
        }
    }

    private Intent cOq_(Context context, nyr nyrVar) {
        String str;
        Intent intent = new Intent();
        intent.setFlags(268435456);
        intent.setAction("SWITCH_PLUGINDEVICE");
        intent.setPackage(BaseApplication.getAppPackage());
        intent.setClassName(context, "com.huawei.health.device.ui.DeviceMainActivity");
        List<String> k = nyrVar.k();
        if (dfe.a().e()) {
            str = nyrVar.k().get(0);
        } else {
            if (!koq.b(k)) {
                if (k.size() == 1) {
                    str = k.get(0);
                } else if (k.size() >= 2) {
                    str = b(nyrVar);
                } else {
                    LogUtil.c("DeviceEntranceUtil", "more devices");
                }
            } else {
                LogUtil.c("DeviceEntranceUtil", "uuidLis is null");
            }
            str = "";
        }
        intent.putExtra("productId", str);
        intent.putExtra("DeviceType", nyrVar.i());
        intent.putExtra("is_invalidation", c());
        intent.putExtra("DeviceName", nyrVar.b());
        intent.putExtra("DeviceIconPath", nyrVar.c());
        intent.putExtra("arg1", "DeviceBindWaitingFragment");
        intent.putExtra(AdShowExtras.DOWNLOAD_SOURCE, cvy.c(nyrVar.g()));
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", nyrVar.h());
        contentValues.put("productId", str);
        intent.putExtra("commonDeviceInfo", contentValues);
        return intent;
    }

    private Intent cOp_(String str, String str2, String str3, String str4, String str5) {
        Intent intent = new Intent();
        intent.setFlags(268435456);
        intent.setAction("SWITCH_PLUGINDEVICE");
        intent.setPackage(BaseApplication.getAppPackage());
        intent.setClassName(BaseApplication.getContext(), "com.huawei.health.device.ui.DeviceMainActivity");
        intent.putExtra("productId", str);
        intent.putExtra("KEY_INTENT_COURSE_ENTRANCE", this.f15495a);
        intent.putExtra("KEY_INTENT_EQUIPMENT_TYPE", this.e);
        intent.putExtra("fittings_host_sn", this.b);
        intent.putExtra("arg1", "DeviceBindWaitingFragment");
        intent.putExtra("DeviceType", str3);
        intent.putExtra("DeviceName", str4);
        intent.putExtra("DeviceIconPath", str5);
        intent.putExtra("isJumpFromFittings", this.h);
        ContentValues contentValues = new ContentValues();
        contentValues.put("uniqueId", str2);
        contentValues.put("productId", str);
        intent.putExtra("commonDeviceInfo", contentValues);
        return intent;
    }

    public String b(nyr nyrVar) {
        String str = "";
        if (nyrVar == null) {
            LogUtil.d("DeviceEntranceUtil", "getProductId itemData is null");
            return "";
        }
        List<String> k = nyrVar.k();
        String b = nyrVar.b();
        String a2 = a(b);
        if (!TextUtils.isEmpty(a2)) {
            return a2;
        }
        LogUtil.c("DeviceEntranceUtil", "ScanDeviceItem deviceName:", b);
        blt.a(nyrVar.h());
        for (String str2 : k) {
            LogUtil.c("DeviceEntranceUtil", "uuidList uuid:", str2);
            dcz d = ResourceManager.e().d(str2);
            if (d != null) {
                boolean a3 = d.w().a(b);
                LogUtil.c("DeviceEntranceUtil", "getProductId deviceNameMatched:", Boolean.valueOf(a3));
                if (a3) {
                    LogUtil.c("DeviceEntranceUtil", "getProductId productId:", str2);
                    str = str2;
                }
            } else {
                LogUtil.c("DeviceEntranceUtil", "productInfo is null");
            }
        }
        if (!TextUtils.isEmpty(str)) {
            return str;
        }
        LogUtil.c("DeviceEntranceUtil", "productId is null");
        return k.get(0);
    }

    private String a(String str) {
        String str2 = "";
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (str.contains("Dobby") || str.contains("HUAWEI Scale 3 BLE-")) {
            return "c943c933-442e-4c34-bcd0-66597f24aaed";
        }
        if (str.contains("Hagrid-B29") || str.contains("HUAWEI Scale 3 Pro-")) {
            str2 = "b29df4e3-b1f7-4e40-960d-4cfb63ccca05";
        } else if (str.contains("HAG") || str.contains("HUAWEI Scale 2 Pro-")) {
            str2 = "8358eb90-b40d-11e9-a2a3-2a2ae2dbcce4";
        } else {
            LogUtil.c("DeviceEntranceUtil", "other hagr device");
        }
        if (str.contains("CH100")) {
            str2 = "33123f39-7fc1-420b-9882-a4b0d6c61100";
        }
        if (str.contains("CH18")) {
            str2 = "34fa0346-d46c-439d-9cb0-2f696618846b";
        }
        if (str.contains("CH100S")) {
            str2 = "ccd1f0f8-8c57-4bd7-a884-0ef38482f15f";
        }
        if (str.contains("AH100")) {
            str2 = "7a1063dd-0e0f-4a72-9939-461476ff0259";
        }
        return (str.contains("LUP-") || str.contains("AH111") || str.contains("HONOR Scale 2-")) ? "25c6df38-ca23-11e9-a32f-2a2ae2dbcce4" : str2;
    }

    public void e(Context context) {
        if (context != null) {
            NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(context).e(context.getResources().getString(R.string.IDS_main_device_ota_error_message)).czC_(R.string._2130841554_res_0x7f020fd2, new View.OnClickListener() { // from class: nuc.11
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.d("DeviceEntranceUtil", "showTipDialog,click known button");
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).e();
            e.setCancelable(false);
            e.show();
        }
    }

    public int d(String str) {
        if ("EMPTY".equals(str)) {
            return 0;
        }
        if ("EXACT".equals(str)) {
            return 1;
        }
        if ("PREFIX".equals(str)) {
            return 2;
        }
        if ("SUFFIX".equals(str)) {
            return 3;
        }
        return "CONTAIN".equals(str) ? 4 : 0;
    }

    public void d(Context context, OneKeyScanActivity.a aVar, HealthTextView healthTextView) {
        if (context == null || aVar == null || healthTextView == null) {
            LogUtil.c("DeviceEntranceUtil", "checkPermission, error ");
            return;
        }
        boolean c2 = BluetoothPermisionUtils.c(context);
        LogUtil.d("DeviceEntranceUtil", "checkPermission, isCheckPermission: ", Boolean.valueOf(c2));
        if (c2) {
            Message obtain = Message.obtain();
            obtain.what = 4;
            aVar.sendMessage(obtain);
        } else {
            b(context, aVar, healthTextView);
            b(context, aVar);
        }
    }

    public void b(final Context context, final OneKeyScanActivity.a aVar, final HealthTextView healthTextView) {
        LogUtil.d("DeviceEntranceUtil", "enter setScanClick");
        if (context == null || aVar == null || healthTextView == null) {
            LogUtil.c("DeviceEntranceUtil", "setScanClick, context or handler or textview is null");
            return;
        }
        String string = context.getResources().getString(R.string._2130844147_res_0x7f0219f3);
        String string2 = context.getResources().getString(R.string._2130844148_res_0x7f0219f4);
        if (Build.VERSION.SDK_INT > 30) {
            string2 = context.getResources().getString(R.string._2130847209_res_0x7f0225e9);
        }
        String format = String.format(Locale.ROOT, string, string2);
        SpannableString spannableString = new SpannableString(format);
        int indexOf = format.indexOf(string2);
        if (indexOf < 0) {
            return;
        }
        int length = string2.length();
        final int color = BaseApplication.getContext().getResources().getColor(R.color._2131296651_res_0x7f09018b);
        spannableString.setSpan(new ClickableSpan() { // from class: nuc.14
            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setUnderlineText(false);
                textPaint.setColor(color);
            }

            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                nuc.this.d(context, aVar, healthTextView);
                ViewClickInstrumentation.clickOnView(view);
            }
        }, indexOf, length + indexOf, 34);
        healthTextView.setText(spannableString);
        healthTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void c(final Context context, final OneKeyScanActivity.a aVar, final HealthTextView healthTextView) {
        if (context == null || aVar == null || healthTextView == null) {
            LogUtil.c("DeviceEntranceUtil", "checkGpsService, context or handler or textview is null");
            return;
        }
        String string = context.getResources().getString(R.string._2130844462_res_0x7f021b2e);
        String string2 = context.getResources().getString(R.string._2130844165_res_0x7f021a05);
        healthTextView.setTextColor(context.getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        String format = String.format(Locale.ROOT, string, string2);
        SpannableString spannableString = new SpannableString(format);
        int indexOf = format.indexOf(string2);
        if (indexOf < 0) {
            return;
        }
        int length = string2.length();
        final int color = BaseApplication.getContext().getResources().getColor(R.color._2131296651_res_0x7f09018b);
        spannableString.setSpan(new ClickableSpan() { // from class: nuc.12
            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setUnderlineText(false);
                textPaint.setColor(color);
            }

            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                LogUtil.d("DeviceEntranceUtil", "setScanClick onClick");
                nuc.this.d(context, aVar, healthTextView);
                ViewClickInstrumentation.clickOnView(view);
            }
        }, indexOf, length + indexOf, 34);
        healthTextView.setText(spannableString);
        healthTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public void c(final Context context, HealthTextView healthTextView) {
        if (context == null || healthTextView == null) {
            LogUtil.c("DeviceEntranceUtil", "checkGpsService, context or handler or textview is null");
            return;
        }
        LogUtil.d("DeviceEntranceUtil", "enter checkBluetooth");
        String string = context.getResources().getString(R.string._2130844462_res_0x7f021b2e);
        String string2 = context.getResources().getString(R.string._2130841229_res_0x7f020e8d);
        healthTextView.setTextColor(context.getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        String format = String.format(Locale.ROOT, string, string2);
        SpannableString spannableString = new SpannableString(format);
        int indexOf = format.indexOf(string2);
        if (indexOf < 0) {
            return;
        }
        int length = string2.length();
        final int color = BaseApplication.getContext().getResources().getColor(R.color._2131296651_res_0x7f09018b);
        spannableString.setSpan(new ClickableSpan() { // from class: nuc.15
            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setUnderlineText(false);
                textPaint.setColor(color);
            }

            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                LogUtil.d("DeviceEntranceUtil", "setScanClick onClick");
                nuc.this.h(context);
                ViewClickInstrumentation.clickOnView(view);
            }
        }, indexOf, length + indexOf, 34);
        healthTextView.setText(spannableString);
        healthTextView.setMovementMethod(LinkMovementMethod.getInstance());
        h(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(Context context) {
        try {
            Intent intent = new Intent("android.bluetooth.adapter.action.REQUEST_ENABLE");
            if (context instanceof Activity) {
                ((Activity) context).startActivityForResult(intent, 102);
            } else {
                context.startActivity(intent);
            }
        } catch (SecurityException unused) {
            LogUtil.d("DeviceEntranceUtil", "openBluetooth is error");
        }
    }

    public void b(final Context context, final OneKeyScanActivity.a aVar) {
        PermissionUtil.PermissionType permissionType = PermissionUtil.PermissionType.LOCATION;
        if (Build.VERSION.SDK_INT > 30) {
            permissionType = PermissionUtil.PermissionType.SCAN;
        }
        PermissionUtil.b(context, permissionType, new PermissionsResultAction() { // from class: nuc.13
            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onGranted() {
                LogUtil.d("DeviceEntranceUtil", "requestPermission onGranted");
                Message obtain = Message.obtain();
                obtain.what = 4;
                aVar.sendMessage(obtain);
            }

            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onDenied(String str) {
                LogUtil.d("DeviceEntranceUtil", "requestPermission onDenied");
                if (Arrays.asList(PermissionUtil.c(PermissionUtil.PermissionType.SCAN)).contains(str)) {
                    nuc.this.c(context);
                }
            }

            @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
            public void onForeverDenied(PermissionUtil.PermissionType permissionType2) {
                super.onForeverDenied(permissionType2);
                LogUtil.d("DeviceEntranceUtil", "requestPermission onForeverDenied");
                nuc.this.c(context);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final Context context) {
        if (context == null) {
            LogUtil.c("DeviceEntranceUtil", "createGpsDialog, context is null");
            return;
        }
        if (context instanceof Activity) {
            LogUtil.d("DeviceEntranceUtil", "enter createGpsDialog");
            if (((Activity) context).isFinishing()) {
                LogUtil.c("DeviceEntranceUtil", "createGpsDialog,activity is finishing");
                return;
            }
            CustomViewDialog customViewDialog = this.i;
            String str = null;
            if (customViewDialog != null) {
                if (customViewDialog.isShowing()) {
                    this.i.dismiss();
                }
                this.i = null;
            }
            View inflate = View.inflate(context, R.layout.dialog_gps_location_permission_layout, null);
            if (Build.VERSION.SDK_INT > 30) {
                ((HealthTextView) inflate.findViewById(R.id.content)).setText(R.string._2130846461_res_0x7f0222fd);
                str = BaseApplication.getContext().getString(R.string._2130842089_res_0x7f0211e9);
                ((HealthTextView) inflate.findViewById(R.id.content_tip)).setVisibility(8);
            }
            CustomViewDialog e = new CustomViewDialog.Builder(context).c(true).a(str).czg_(inflate).czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: nuc.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (nuc.this.i != null) {
                        nuc.this.i.dismiss();
                        nuc.this.i = null;
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).cze_(R.string._2130842041_res_0x7f0211b9, new View.OnClickListener() { // from class: nuc.17
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    if (nuc.this.i != null) {
                        nuc.this.i.dismiss();
                        nuc.this.i = null;
                    }
                    obb.cTW_((Activity) context, 2);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).e();
            this.i = e;
            e.setCancelable(false);
            this.i.show();
        }
    }

    public void b(Context context, DeviceInfo deviceInfo, List<String> list) {
        if (context == null || deviceInfo == null) {
            LogUtil.c("DeviceEntranceUtil", "handleConnect error");
            return;
        }
        if (!NotificationContentProviderUtil.e()) {
            LogUtil.d("DeviceEntranceUtil", "gotoConnect NOTIFICATION_INITIALIZE");
            if (jjb.b().c()) {
                NotificationContentProviderUtil.a(1);
            } else {
                NotificationContentProviderUtil.a(0);
            }
        }
        CompatibilityInteractor compatibilityInteractor = new CompatibilityInteractor();
        if (compatibilityInteractor.a(BaseApplication.getContext())) {
            a(context, deviceInfo, list);
            return;
        }
        List<DeviceInfo> h = dwo.d().h();
        if (h == null) {
            a(context, deviceInfo, list);
            return;
        }
        if (h.size() > 0 && CompatibilityInteractor.c(BaseApplication.getContext())) {
            if (compatibilityInteractor.d(h)) {
                c(compatibilityInteractor, list, h, context, deviceInfo);
                return;
            } else {
                a(context, deviceInfo, list);
                return;
            }
        }
        a(context, deviceInfo, list);
    }

    private void c(final CompatibilityInteractor compatibilityInteractor, final List<String> list, List<DeviceInfo> list2, final Context context, final DeviceInfo deviceInfo) {
        if (compatibilityInteractor.a(list2) == null) {
            a(context, deviceInfo, list);
        } else {
            compatibilityInteractor.a(context, (DeviceInfo) null, new IBaseResponseCallback() { // from class: nuc.3
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (i != 0) {
                        nuc.this.a(context, deviceInfo, (List<String>) list);
                    } else {
                        if (obj == null || !(obj instanceof String) || compatibilityInteractor == null || !"SURE".equals(obj)) {
                            return;
                        }
                        CommonUtil.ak(BaseApplication.getContext());
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Context context, DeviceInfo deviceInfo, List<String> list) {
        if (context instanceof Activity) {
            cOo_((Activity) context, deviceInfo, list);
        }
    }

    private void cOo_(Activity activity, DeviceInfo deviceInfo, List<String> list) {
        if (koq.b(list)) {
            LogUtil.c("DeviceEntranceUtil", "uuidList is empty");
            return;
        }
        if (activity == null || deviceInfo == null) {
            return;
        }
        try {
            String deviceUdid = deviceInfo.getDeviceUdid();
            if (bgb.d().isSupportH5Pair(deviceUdid)) {
                bgb.d().startPair(activity, StartPairOption.builder().c(deviceInfo.getUuid()).e(list).d(deviceUdid).b("wear_watch").a(false).c());
                return;
            }
            Intent intent = new Intent(activity, (Class<?>) DevicePairGuideActivity.class);
            intent.putExtra("pairGuideSelectName", deviceInfo.getDeviceUdid());
            intent.putExtra("pairGuideFromScanList", true);
            intent.putExtra("isHeartRateDevice", 0);
            if (list instanceof ArrayList) {
                intent.putStringArrayListExtra("uuid_list", (ArrayList) list);
            }
            intent.putExtra("kind_id", deviceInfo.getDeviceModel());
            intent.putExtra("DOWNLOAD_RESOURCE", true);
            intent.putExtra("pairGuideSelectAddress", deviceInfo.getUuid());
            intent.putExtra("pairGuideDeviceMode", 100001);
            activity.startActivityForResult(intent, 1);
        } catch (ActivityNotFoundException unused) {
            LogUtil.e("DeviceEntranceUtil", "enterDevicePairGuide error");
        }
    }

    public void a(Context context, final String str, final DeviceInfo deviceInfo, final OneKeyScanActivity.a aVar) {
        if (context == null || deviceInfo == null || aVar == null) {
            LogUtil.c("DeviceEntranceUtil", "showReplaceDeviceDialog, parameter is null");
            return;
        }
        oae c2 = oae.c(BaseApplication.getContext());
        int productType = deviceInfo.getProductType();
        String deviceName = deviceInfo.getDeviceName();
        String b = c2.b(productType);
        this.d = b;
        d(b, productType, c2);
        LogUtil.d("DeviceEntranceUtil", "new device ", str, ", old device ", this.d, " currentDeviceName ", deviceName);
        this.l = str;
        if (!TextUtils.isEmpty(deviceName)) {
            this.d = deviceName;
        }
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(context).b(R.string.IDS_device_replace_dialog_title_notification).e(String.format(Locale.ROOT, context.getResources().getString(R.string.IDS_replace_device_dialog_content), this.d, this.l)).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: nuc.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.d("DeviceEntranceUtil", "showReplaceDeviceDialog():Click not to switch the device");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyU_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: nuc.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.d("DeviceEntranceUtil", "showReplaceDeviceDialog():Click to agree to switch the device");
                nuc nucVar = nuc.this;
                nucVar.d(nucVar.d, str);
                nuc.this.c(aVar, deviceInfo.getDeviceIdentify());
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a();
        a2.setCancelable(false);
        a2.show();
    }

    private void d(String str, int i, oae oaeVar) {
        if (TextUtils.isEmpty(str)) {
            jfu.n();
            this.d = oaeVar.b(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, String str2) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("old_device", str);
        hashMap.put("new_device", str2);
        d(AnalyticsValue.ONE_KEY_SCAN_CLICK_CONNECT_REPLACE_DEVICE.value(), hashMap);
    }

    public boolean c(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            return cjx.e().h(str) ? cjx.e().c(str, str2) != null : cjx.e().c(str2) != null;
        }
        LogUtil.c("DeviceEntranceUtil", "isBondedThirdPartyDevice productId or uniqueId is null");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(OneKeyScanActivity.a aVar, String str) {
        Message obtain = Message.obtain();
        obtain.what = 5;
        aVar.sendMessage(obtain);
        oaf.b(BaseApplication.getContext()).h(str);
        LogUtil.d("DeviceEntranceUtil", "Clear the upgrade inter data");
    }

    public void a(Context context) {
        if (context == null) {
            LogUtil.c("DeviceEntranceUtil", "got jumpToNetSettings error, context is null");
            return;
        }
        try {
            SafeIntent safeIntent = new SafeIntent(new Intent());
            safeIntent.setAction("android.settings.WIRELESS_SETTINGS");
            context.startActivity(safeIntent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.e("DeviceEntranceUtil", "jump2NetSettings error");
        }
    }

    public void c(String str) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        d(str, hashMap);
    }

    public void d(String str, Map<String, Object> map) {
        ixx.d().d(BaseApplication.getContext(), str, map, 0);
    }

    public List<BluetoothDevice> b() {
        ArrayList arrayList = new ArrayList(10);
        try {
            Object systemService = BaseApplication.getContext().getSystemService("bluetooth");
            return systemService instanceof BluetoothManager ? ((BluetoothManager) systemService).getConnectedDevices(7) : arrayList;
        } catch (SecurityException unused) {
            LogUtil.e("DeviceEntranceUtil", "getDeviceList SecurityException");
            return arrayList;
        }
    }

    public List<BluetoothDevice> e() {
        Method declaredMethod;
        Set<BluetoothDevice> bondedDevices;
        BluetoothAdapter defaultAdapter = BluetoothAdapter.getDefaultAdapter();
        ArrayList arrayList = new ArrayList(10);
        try {
            declaredMethod = BluetoothAdapter.class.getDeclaredMethod("getConnectionState", new Class[0]);
            declaredMethod.setAccessible(true);
            bondedDevices = defaultAdapter.getBondedDevices();
        } catch (IllegalAccessException unused) {
            LogUtil.e("DeviceEntranceUtil", "getConnectedDeviceList IllegalAccessException");
        } catch (IllegalArgumentException unused2) {
            LogUtil.e("DeviceEntranceUtil", "getConnectedDeviceList IllegalArgumentException");
        } catch (NoSuchMethodException unused3) {
            LogUtil.e("DeviceEntranceUtil", "getConnectedDeviceList NoSuchMethodException");
        } catch (SecurityException unused4) {
            LogUtil.e("DeviceEntranceUtil", "getConnectedDeviceList SecurityException");
        } catch (InvocationTargetException unused5) {
            LogUtil.e("DeviceEntranceUtil", "getConnectedDeviceList InvocationTargetException");
        }
        if (bondedDevices == null) {
            LogUtil.d("DeviceEntranceUtil", "getConnectedDeviceList() devices is null");
            return arrayList;
        }
        for (BluetoothDevice bluetoothDevice : bondedDevices) {
            Method declaredMethod2 = BluetoothDevice.class.getDeclaredMethod("isConnected", new Class[0]);
            declaredMethod.setAccessible(true);
            if (declaredMethod2.invoke(bluetoothDevice, new Object[0]) instanceof Boolean) {
                boolean booleanValue = ((Boolean) declaredMethod2.invoke(bluetoothDevice, new Object[0])).booleanValue();
                LogUtil.d("DeviceEntranceUtil", "getConnectDevice:", bluetoothDevice.getName(), " connect status:", Boolean.valueOf(booleanValue));
                if (booleanValue) {
                    arrayList.add(bluetoothDevice);
                }
            }
        }
        return arrayList;
    }

    public void b(Context context, HealthTextView healthTextView) {
        if (context == null || healthTextView == null) {
            LogUtil.c("DeviceEntranceUtil", "setSecondText, parameter is null");
            return;
        }
        String string = context.getResources().getString(R.string.IDS_add_device_manully);
        if (!CommonUtil.bh() && Build.VERSION.SDK_INT > 30) {
            try {
                Set<BluetoothDevice> bondedDevices = BluetoothAdapter.getDefaultAdapter().getBondedDevices();
                if (bondedDevices == null || bondedDevices.isEmpty()) {
                    string = context.getResources().getString(R.string.IDS_add_device_exception);
                }
            } catch (SecurityException unused) {
                string = context.getResources().getString(R.string.IDS_add_device_exception);
            }
        }
        healthTextView.setTextColor(context.getResources().getColor(R.color._2131299241_res_0x7f090ba9));
        healthTextView.setText(string);
    }

    public void b(nyq nyqVar, Context context) {
        if (nyqVar == null || context == null) {
            LogUtil.c("DeviceEntranceUtil", "startIntentPairGuideSecond, parameter is null");
            return;
        }
        Intent intent = new Intent(context, (Class<?>) DevicePairGuideSecondActivity.class);
        List<String> s = nyqVar.s();
        if (s instanceof ArrayList) {
            intent.putStringArrayListExtra("uuid_list", (ArrayList) s);
        }
        intent.putExtra("kind_id", nyqVar.f());
        Map<String, String> k = nyqVar.k();
        if (k instanceof Serializable) {
            intent.putExtra("pair_guide_array", (Serializable) k);
        }
        Map<String, cvm> o = nyqVar.o();
        if (o != null && (o instanceof Map)) {
            LogUtil.c("DeviceEntranceUtil", "startIntentPairGuideSecond, resourceBeanMap:", o.toString());
            intent.putExtra("second_resource", (Serializable) o);
        }
        if (context instanceof Activity) {
            try {
                ((Activity) context).startActivity(intent);
            } catch (ActivityNotFoundException e) {
                LogUtil.e("DeviceEntranceUtil", "ActivityNotFoundException e:", e.getMessage());
            }
        }
    }

    public void e(final nyq nyqVar, Context context, final oae oaeVar) {
        if (nyqVar == null || context == null || oaeVar == null) {
            LogUtil.c("DeviceEntranceUtil", "enterPairGuide, parameter is null");
        } else {
            final WeakReference weakReference = new WeakReference(context);
            LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: nud
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    nuc.this.a(nyqVar, oaeVar, weakReference, i, obj);
                }
            }, "");
        }
    }

    /* synthetic */ void a(nyq nyqVar, oae oaeVar, WeakReference weakReference, int i, Object obj) {
        if (i == 0) {
            this.j = nyqVar.h();
            this.m = nyqVar;
            this.g = oaeVar;
            f((Context) weakReference.get());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context) {
        List<String> s = this.m.s();
        if (s == null || s.isEmpty()) {
            LogUtil.c("DeviceEntranceUtil", "enterLeoPairGuide uuid is empty");
            return;
        }
        String str = s.get(0);
        boolean d = cup.d(str);
        cuw c2 = jfu.c(str, d);
        if (c2 == null) {
            LogUtil.d("DeviceEntranceUtil", "enterLeoPairGuide, deviceInfoNew is null ");
            return;
        }
        int m = c2.m();
        Intent intent = new Intent(context, (Class<?>) DevicePairGuideActivity.class);
        if (m == 10) {
            ntt.cNM_(intent, m, this.g);
            intent.putExtra("pairGuideProductType", 10);
            intent.putExtra("pairGuideProductName", c2.f());
            intent.putExtra("IS_PROC", d);
        } else {
            intent.putExtra("pairGuideSelectName", this.m.h());
            intent.putExtra("pairGuideFromScanList", true);
            intent.putExtra("isHeartRateDevice", false);
            if (s instanceof ArrayList) {
                intent.putStringArrayListExtra("uuid_list", (ArrayList) s);
            }
            intent.putExtra("kind_id", this.m.f());
            intent.putExtra("DOWNLOAD_RESOURCE", true);
            intent.putExtra("pairGuideSelectAddress", "");
        }
        intent.putExtra("pairGuideFromScanList", false);
        intent.putExtra("pairGuideDeviceMode", 100001);
        if (context instanceof Activity) {
            ((Activity) context).startActivityForResult(intent, 1);
        }
    }

    private void f(Context context) {
        if (context == null) {
            LogUtil.c("DeviceEntranceUtil", "startPair context is null");
            return;
        }
        DeviceInfo d = jpt.d("DeviceEntranceUtil");
        if (d != null && HwVersionManager.c(BaseApplication.getContext()).o(d.getDeviceIdentify())) {
            LogUtil.d("DeviceEntranceUtil", "wear device OTA is in progress");
            e(context);
        } else {
            d(context);
        }
    }

    private void d(Context context) {
        LogUtil.d("DeviceEntranceUtil", "enter handleDialogByConnectedDevice");
        List<DeviceInfo> c2 = oae.c(BaseApplication.getContext()).c();
        if (c2 == null || c2.isEmpty()) {
            LogUtil.c("DeviceEntranceUtil", "onClick connectedDeviceInfo is null");
            b(context);
        } else if (c2.size() == 1) {
            a(context, c2);
        } else if (c2.size() >= 2) {
            d(context, c2);
        } else {
            LogUtil.d("DeviceEntranceUtil", "more devices");
        }
    }

    private void d(Context context, List<DeviceInfo> list) {
        for (DeviceInfo deviceInfo : list) {
            if (!cvt.a(deviceInfo.getProductType(), deviceInfo.getAutoDetectSwitchStatus())) {
                LogUtil.d("DeviceEntranceUtil", "onClick user has connect other device, and also wants to connect other device");
                a(context, this.j, deviceInfo);
                return;
            }
        }
    }

    private void a(Context context, List<DeviceInfo> list) {
        LogUtil.d("DeviceEntranceUtil", "onClick has one connected device");
        DeviceInfo deviceInfo = list.get(0);
        if (deviceInfo == null) {
            LogUtil.c("DeviceEntranceUtil", "onClick connected is null");
        } else if (cvt.c(deviceInfo.getProductType())) {
            e(context, deviceInfo);
        } else {
            LogUtil.d("DeviceEntranceUtil", "onClick user has connect other device, and also wants to connect other device");
            a(context, this.j, deviceInfo);
        }
    }

    private void e(Context context, DeviceInfo deviceInfo) {
        if (deviceInfo.getAutoDetectSwitchStatus() == 0) {
            LogUtil.d("DeviceEntranceUtil", "onClick user has connect aw70 band mode, and also wants to connect other device");
            a(context, this.j, deviceInfo);
        } else if (deviceInfo.getAutoDetectSwitchStatus() == 1) {
            LogUtil.d("DeviceEntranceUtil", "onClick user has connect aw70 run mode, and also wants to connect other device");
            b(context);
        } else {
            LogUtil.d("DeviceEntranceUtil", "onClick user has connect aw70 unknown mode, and also wants to connect other device");
        }
    }

    private void a(final Context context, final String str, final DeviceInfo deviceInfo) {
        if (context == null || deviceInfo == null) {
            LogUtil.c("DeviceEntranceUtil", "showReplacePairDeviceDialog, parameter is null");
            return;
        }
        oae c2 = oae.c(BaseApplication.getContext());
        int productType = deviceInfo.getProductType();
        String b = c2.b(productType);
        this.d = b;
        d(b, productType, c2);
        String deviceName = deviceInfo.getDeviceName();
        LogUtil.d("DeviceEntranceUtil", "new device ", str, ", old device ", this.d);
        this.l = str;
        if (!TextUtils.isEmpty(deviceName)) {
            this.d = deviceName;
        }
        CustomTextAlertDialog a2 = new CustomTextAlertDialog.Builder(context).b(R.string.IDS_device_replace_dialog_title_notification).e(String.format(Locale.ROOT, context.getResources().getString(R.string.IDS_replace_device_dialog_content), this.d, this.l)).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: nuc.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.d("DeviceEntranceUtil", "showReplacePairDeviceDialog():Click not to switch the device");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyU_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: nuc.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.d("DeviceEntranceUtil", "showReplacePairDeviceDialog():Click to agree to switch the device");
                nuc nucVar = nuc.this;
                nucVar.d(nucVar.d, str);
                nuc.this.b(context);
                oaf.b(BaseApplication.getContext()).h(deviceInfo.getDeviceIdentify());
                ViewClickInstrumentation.clickOnView(view);
            }
        }).a();
        a2.setCancelable(false);
        a2.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public cve e(List<cve> list, List<String> list2) {
        for (cve cveVar : list) {
            if (cveVar != null && !koq.b(cveVar.ac())) {
                ArrayList arrayList = new ArrayList(cveVar.ac());
                arrayList.retainAll(list2);
                if (arrayList.size() != 0) {
                    return cveVar;
                }
            }
        }
        return new cve();
    }

    public void cOs_(final Handler handler, final List<String> list, final List<cve> list2) {
        handler.post(new Runnable() { // from class: nuc.6
            @Override // java.lang.Runnable
            public void run() {
                List list3 = list;
                if (list3 != null) {
                    cve e = nuc.this.e((List<cve>) list2, (List<String>) list3);
                    if (list.size() <= 0 || e == null) {
                        return;
                    }
                    String str = (String) list.get(0);
                    if (!dfe.a().j(str) || koq.b(e.e())) {
                        return;
                    }
                    HealthDevice b = dfe.a().b(str);
                    nyr nyrVar = new nyr();
                    nyrVar.c(e.e().get(0));
                    nyrVar.a(b.getAddress());
                    Message obtain = Message.obtain();
                    obtain.what = 1;
                    obtain.obj = nyrVar;
                    handler.sendMessage(obtain);
                }
            }
        });
    }
}
