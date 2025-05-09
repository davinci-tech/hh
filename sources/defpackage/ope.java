package defpackage;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.amap.api.col.p0003sl.it;
import com.huawei.haf.common.utils.NetworkUtil;
import com.huawei.health.R;
import com.huawei.health.device.wifi.interfaces.CommBaseCallbackInterface;
import com.huawei.health.suggestion.model.FitRunPlayAudio;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler;
import com.huawei.ui.thirdpartservice.activity.healthkit.HealthKitActivity;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class ope {

    /* renamed from: a, reason: collision with root package name */
    private static ope f15832a;
    private static final Object d = new Object();
    private b b;
    private QrCodeBaseHandler c;
    private NoTitleCustomAlertDialog e;

    public static ope e() {
        ope opeVar;
        synchronized (d) {
            if (f15832a == null) {
                f15832a = new ope();
            }
            opeVar = f15832a;
        }
        return opeVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:48:0x0083, code lost:
    
        if (r4.equals("activeEcgService") == false) goto L52;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static final boolean e(java.lang.String r4) {
        /*
            r0 = 0
            if (r4 != 0) goto L4
            return r0
        L4:
            r4.hashCode()
            int r1 = r4.hashCode()
            r2 = -2117024110(0xffffffff81d0c692, float:-7.669207E-38)
            r3 = 1
            if (r1 == r2) goto L7d
            r0 = -1571809300(0xffffffffa25017ec, float:-2.820192E-18)
            if (r1 == r0) goto L72
            r0 = 102(0x66, float:1.43E-43)
            if (r1 == r0) goto L67
            r0 = 115(0x73, float:1.61E-43)
            if (r1 == r0) goto L5c
            r0 = 3208(0xc88, float:4.495E-42)
            if (r1 == r0) goto L51
            r0 = 629233382(0x258156e6, float:2.2436818E-16)
            if (r1 == r0) goto L46
            r0 = 99
            if (r1 == r0) goto L3b
            r0 = 100
            if (r1 == r0) goto L30
            goto L85
        L30:
            java.lang.String r0 = "d"
            boolean r0 = r4.equals(r0)
            if (r0 != 0) goto L39
            goto L85
        L39:
            r0 = 3
            goto L86
        L3b:
            java.lang.String r0 = "c"
            boolean r0 = r4.equals(r0)
            if (r0 != 0) goto L44
            goto L85
        L44:
            r0 = 2
            goto L86
        L46:
            java.lang.String r0 = "deeplink"
            boolean r0 = r4.equals(r0)
            if (r0 != 0) goto L4f
            goto L85
        L4f:
            r0 = 7
            goto L86
        L51:
            java.lang.String r0 = "dl"
            boolean r0 = r4.equals(r0)
            if (r0 != 0) goto L5a
            goto L85
        L5a:
            r0 = 6
            goto L86
        L5c:
            java.lang.String r0 = "s"
            boolean r0 = r4.equals(r0)
            if (r0 != 0) goto L65
            goto L85
        L65:
            r0 = 5
            goto L86
        L67:
            java.lang.String r0 = "f"
            boolean r0 = r4.equals(r0)
            if (r0 != 0) goto L70
            goto L85
        L70:
            r0 = 4
            goto L86
        L72:
            java.lang.String r0 = "activeEcgWatch"
            boolean r0 = r4.equals(r0)
            if (r0 != 0) goto L7b
            goto L85
        L7b:
            r0 = r3
            goto L86
        L7d:
            java.lang.String r1 = "activeEcgService"
            boolean r1 = r4.equals(r1)
            if (r1 != 0) goto L86
        L85:
            r0 = -1
        L86:
            switch(r0) {
                case 0: goto L90;
                case 1: goto L90;
                case 2: goto L90;
                case 3: goto L90;
                case 4: goto L90;
                case 5: goto L90;
                case 6: goto L90;
                case 7: goto L90;
                default: goto L89;
            }
        L89:
            java.lang.String r0 = "connect"
            boolean r4 = r0.equalsIgnoreCase(r4)
            return r4
        L90:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ope.e(java.lang.String):boolean");
    }

    public static void deL_(Activity activity) {
        if (activity == null) {
            return;
        }
        activity.runOnUiThread(new Runnable() { // from class: ope.4
            @Override // java.lang.Runnable
            public void run() {
                nrh.b(BaseApplication.getContext(), R.string._2130844123_res_0x7f0219db);
            }
        });
        activity.finish();
    }

    public static void deJ_(Bundle bundle, String str, String str2) {
        HashMap hashMap = new HashMap(3);
        if (bundle == null) {
            hashMap.put("src", "app");
        } else {
            hashMap.put("src", bundle.getString("src", "app"));
        }
        hashMap.put("action", str);
        hashMap.put("type", str2);
        LogUtil.a("QrCodeHandleManager", "reportQrEvent: ", hashMap.toString());
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_QRCODE_2060054.value(), hashMap, 0);
    }

    public void deP_(Activity activity, String str, Bundle bundle) {
        if (activity == null || TextUtils.isEmpty(str)) {
            LogUtil.h("R_QrCode_QrCodeHandleManager", "activity is null or content is empty");
            return;
        }
        deJ_(bundle, "gl", "");
        SharedPreferenceManager.e(activity, String.valueOf(10000), "key_sn_sino_blood_sugar", str, (StorageParams) null);
        if (new shx().b(402)) {
            if (!NetworkUtil.i()) {
                deK_(activity);
                return;
            } else {
                dij.UY_(activity);
                return;
            }
        }
        LogUtil.a("QrCodeHandleManager", "showHealthKit enter");
        Intent intent = new Intent(activity, (Class<?>) HealthKitActivity.class);
        intent.putExtra("key_health_kit_authorization_to_bind_device", true);
        activity.startActivity(intent);
        activity.finish();
    }

    private void deK_(final Activity activity) {
        LogUtil.a("QrCodeHandleManager", "showNetworkOpenDialog()");
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(activity).e(activity.getString(R.string._2130841884_res_0x7f02111c)).czz_(R.string._2130844419_res_0x7f021b03, new View.OnClickListener() { // from class: opd
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ope.this.deQ_(view);
            }
        }).czC_(R.string._2130844162_res_0x7f021a02, new View.OnClickListener() { // from class: opc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ope.deI_(activity, view);
            }
        }).e();
        this.e = e;
        e.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: opg
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                ope.this.deR_(activity, dialogInterface);
            }
        });
        this.e.setCanceledOnTouchOutside(false);
        this.e.show();
        c();
    }

    /* synthetic */ void deQ_(View view) {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.e;
        if (noTitleCustomAlertDialog != null) {
            noTitleCustomAlertDialog.dismiss();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void deI_(Activity activity, View view) {
        LogUtil.a("QrCodeHandleManager", "showNetworkOpenDialog() onDialogButtonClick Yes");
        ctv.Mj_(activity, new Intent("android.settings.SETTINGS"));
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void deR_(Activity activity, DialogInterface dialogInterface) {
        LogUtil.a("QrCodeHandleManager", "showNetworkOpenDialog() onDismiss");
        if (NetworkUtil.i()) {
            dij.UY_(activity);
        } else {
            activity.finish();
        }
        d();
    }

    private void c() {
        if (this.b == null) {
            IntentFilter intentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
            intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
            this.b = new b(this);
            BaseApplication.getContext().registerReceiver(this.b, intentFilter);
        }
    }

    private void d() {
        if (this.b != null) {
            BaseApplication.getContext().unregisterReceiver(this.b);
            this.b = null;
        }
    }

    public void deO_(Activity activity, String str, String str2, Bundle bundle, CommBaseCallbackInterface commBaseCallbackInterface) {
        LogUtil.a("QrCodeHandleManager", "handleQrCode in");
        if (!deM_(activity, str, str2, commBaseCallbackInterface)) {
            if (commBaseCallbackInterface != null) {
                commBaseCallbackInterface.onResult(-3, "handleQrCode verify fail", null);
            }
            LogUtil.h("QrCodeHandleManager", "handleQrCode verify fail");
            return;
        }
        QrCodeBaseHandler deF_ = deF_(str, str2, bundle, activity, commBaseCallbackInterface);
        this.c = deF_;
        if (deF_ != null) {
            if (this.c.verify(deF_.parser(str, str2))) {
                this.c.execute();
                return;
            }
            LogUtil.b("R_QrCode_QrCodeHandleManager", "Data validation failed");
        }
        activity.finish();
    }

    public QrCodeBaseHandler a() {
        return this.c;
    }

    public void deN_(String str, String str2, Bundle bundle, Activity activity) {
        if (!e(str)) {
            LogUtil.h("QrCodeHandleManager", "other action");
            deS_(bundle, activity);
        } else {
            deO_(activity, str, str2, bundle, new CommBaseCallbackInterface() { // from class: ope.1
                @Override // com.huawei.health.device.wifi.interfaces.CommBaseCallbackInterface
                public void onResult(int i, String str3, Object obj) {
                    LogUtil.a("QrCodeHandleManager", "handleQrByAction callback: ", Integer.valueOf(i), " msg: ", str3);
                }
            });
        }
    }

    public void deS_(Bundle bundle, Activity activity) {
        deJ_(bundle, "unknown", "");
        opf.deU_(activity);
    }

    private boolean g(String str) {
        return "c".equals(str) || "connect".equalsIgnoreCase(str);
    }

    private boolean b(String str) {
        return FitRunPlayAudio.PLAY_TYPE_D.equals(str);
    }

    private boolean h(String str) {
        return "deeplink".equals(str);
    }

    private boolean deM_(Activity activity, String str, String str2, CommBaseCallbackInterface commBaseCallbackInterface) {
        if (activity == null) {
            LogUtil.b("R_QrCode_QrCodeHandleManager", "verify activity is null");
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("R_QrCode_QrCodeHandleManager", "verify action is null");
            return false;
        }
        if (TextUtils.isEmpty(str2) && !"activeEcgService".equals(str) && !"activeEcgWatch".equals(str)) {
            LogUtil.b("R_QrCode_QrCodeHandleManager", "verify data is null and not Ecg action");
            return false;
        }
        if (commBaseCallbackInterface != null) {
            return true;
        }
        LogUtil.b("R_QrCode_QrCodeHandleManager", "verify callback is null");
        return false;
    }

    private boolean i(String str) {
        return it.i.equals(str);
    }

    private QrCodeBaseHandler deF_(String str, String str2, Bundle bundle, Activity activity, CommBaseCallbackInterface commBaseCallbackInterface) {
        if ("s".equals(str) || "sschemeQrCode".equals(str)) {
            deJ_(bundle, "s", "");
            return new oop(activity, commBaseCallbackInterface, bundle);
        }
        if (i(str)) {
            deJ_(bundle, it.i, "");
            return new ooa(activity, commBaseCallbackInterface);
        }
        if ("activeEcgService".equals(str) || "activeEcgWatch".equals(str)) {
            deJ_(bundle, str, "");
            return new ons(activity, commBaseCallbackInterface);
        }
        return deG_(str, str2, bundle, activity, commBaseCallbackInterface);
    }

    private QrCodeBaseHandler deG_(String str, String str2, Bundle bundle, Activity activity, CommBaseCallbackInterface commBaseCallbackInterface) {
        String a2 = a(FitRunPlayAudio.PLAY_TYPE_T, str2);
        if (f(a2) && Utils.o()) {
            LogUtil.a("QrCodeHandleManager", "is oversea not support this device");
            nrh.b(activity, R.string.ie_device_unsupported);
            if (activity != null) {
                LogUtil.a("QrCodeHandleManager", "getQrCodeOtherHandler activity != null");
                activity.finish();
            }
            return null;
        }
        if (g(str)) {
            deJ_(bundle, "c", a2);
            if ((!TextUtils.isEmpty(a2) && c(a2)) || a2.equals("262")) {
                return new ooc(activity, commBaseCallbackInterface, bundle);
            }
            if (!TextUtils.isEmpty(a2) && (a(a2) || d(a2))) {
                return new oof(activity, commBaseCallbackInterface);
            }
            LogUtil.h("QrCodeHandleManager", "getQrCodeHandler is other type");
            if ("54".equals(a2)) {
                return new ooi(activity, commBaseCallbackInterface);
            }
            if (!TextUtils.isEmpty(a2)) {
                return new onv(activity, commBaseCallbackInterface);
            }
            LogUtil.h("R_QrCode_QrCodeHandleManager", "getQrCodeHandler unknown connect type");
            opf.deU_(activity);
        }
        if (b(str)) {
            deJ_(bundle, FitRunPlayAudio.PLAY_TYPE_D, a2);
            if ("70".equals(a2)) {
                return new ooh(activity, commBaseCallbackInterface);
            }
            if (j(a2)) {
                return new ony(activity, commBaseCallbackInterface);
            }
            LogUtil.h("R_QrCode_QrCodeHandleManager", "getQrCodeHandler unknown disConnect type");
            opf.deU_(activity);
        }
        if (deH_(str, str2, bundle)) {
            return new ool(activity, commBaseCallbackInterface);
        }
        return null;
    }

    private boolean j(String str) {
        return "274".equals(str);
    }

    private boolean deH_(String str, String str2, Bundle bundle) {
        if (!h(str)) {
            return false;
        }
        String a2 = a(BleConstants.KEY_PATH, str2);
        String a3 = a("type", str2);
        deJ_(bundle, str, "");
        if (!"openwith".equals(a2) || !"aar".equals(a3)) {
            return false;
        }
        LogUtil.a("QrCodeHandleManager", "return WatchFaceQrCodeHandler");
        return true;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static boolean c(String str) {
        char c;
        str.hashCode();
        switch (str.hashCode()) {
            case 1630:
                if (str.equals("31")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 49750:
                if (str.equals(BleConstants.SPORT_TYPE_BIKE)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 49772:
                if (str.equals("260")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 49773:
                if (str.equals("261")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 49776:
                if (str.equals(BleConstants.SPORT_TYPE_TREADMILL)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        return c == 0 || c == 1 || c == 2 || c == 3 || c == 4;
    }

    public static boolean a(String str) {
        str.hashCode();
        return str.equals("68") || str.equals("69");
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static boolean d(String str) {
        char c;
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == 49778) {
            if (str.equals("266")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 49780) {
            switch (hashCode) {
                case 49804:
                    if (str.equals("271")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 49805:
                    if (str.equals("272")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 49806:
                    if (str.equals("273")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
        } else {
            if (str.equals(BleConstants.TYPE_FASCIA_GUN_INDEX)) {
                c = 1;
            }
            c = 65535;
        }
        return c == 0 || c == 1 || c == 2 || c == 3 || c == 4;
    }

    private static boolean f(String str) {
        if (c(str) || str.equals("262") || a(str) || d(str) || "70".equals(str) || "274".equals(str)) {
            return true;
        }
        LogUtil.a("QrCodeHandleManager", "isEcologyDeviceByType return false");
        return false;
    }

    public static String a(String str, String str2) {
        int length;
        if (TextUtils.isEmpty(str2) || TextUtils.isEmpty(str)) {
            return "";
        }
        String str3 = "&" + str + "=";
        int indexOf = str2.indexOf(str3);
        if (indexOf == -1 || (length = indexOf + str3.length()) >= str2.length()) {
            return "";
        }
        String substring = str2.substring(length);
        if (substring.startsWith("&")) {
            return "";
        }
        int indexOf2 = substring.indexOf("&");
        return (indexOf2 == -1 || "address".equals(str)) ? substring : substring.substring(0, indexOf2);
    }

    static class b extends BroadcastReceiver {
        private WeakReference<ope> d;

        b(ope opeVar) {
            this.d = new WeakReference<>(opeVar);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ope opeVar;
            LogUtil.a("QrCodeHandleManager", "NetWorkChangeBroadcastReceiver onReceive Action...", intent.getAction(), ", is connect:", Boolean.valueOf(NetworkUtil.i()));
            if ("android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction()) && NetworkUtil.i() && (opeVar = this.d.get()) != null && opeVar.e != null && opeVar.e.isShowing()) {
                opeVar.e.dismiss();
            }
        }
    }
}
