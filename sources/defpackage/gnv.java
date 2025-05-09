package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.callback.DataReceiveCallback;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.interactor.MainInteractors;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.model.ecgservice.EcgServiceActivationData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.h5pro.H5proUtil;
import com.huawei.operation.h5pro.ble.BleJsInteractionCompact;
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.HealthButtonBarLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.gnv;
import health.compact.a.CompileParameterUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes4.dex */
public class gnv {
    private static String a() {
        XmlResourceParser xml;
        try {
            xml = BaseApplication.getContext().getResources().getXml(R.xml._2132082691_res_0x7f150003);
            try {
            } catch (Throwable th) {
                if (xml != null) {
                    try {
                        xml.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (Resources.NotFoundException | IOException | XmlPullParserException unused) {
            LogUtil.b("Ecg_EcgUtils", "get ecg_url_config xml file failed.");
        }
        if (xml == null) {
            LogUtil.h("Ecg_EcgUtils", "xmlResourceParser is null");
            if (xml != null) {
                xml.close();
            }
            return "";
        }
        String str = CompileParameterUtil.a("IS_TEST_VERSION", false) ? BleConstants.WEIGHT_KEY : "debug";
        for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
            if (eventType == 2) {
                String aPP_ = aPP_(xml, str);
                if (!TextUtils.isEmpty(aPP_)) {
                    if (xml != null) {
                        xml.close();
                    }
                    return aPP_;
                }
            }
        }
        if (xml != null) {
            xml.close();
        }
        return "";
    }

    private static String aPP_(XmlResourceParser xmlResourceParser, String str) throws IOException, XmlPullParserException {
        if ("ecg".equals(xmlResourceParser.getName())) {
            return str.equals(xmlResourceParser.getAttributeValue(null, "buildConfig")) ? xmlResourceParser.nextText() : "";
        }
        return "";
    }

    public static void aPW_(Activity activity, Intent intent) {
        Bundle bundleExtra = intent.getBundleExtra("ecgBundle");
        if (activity == null || bundleExtra == null) {
            return;
        }
        LogUtil.a("Ecg_EcgUtils", "skip ecgPage from MainActivity");
        aPZ_(activity, bundleExtra);
        activity.finish();
    }

    public static void aPT_(Activity activity, Intent intent) {
        if (activity == null || intent == null) {
            LogUtil.h("Ecg_EcgUtils", "jumpToMainActivity activity or intent is null");
            return;
        }
        int intExtra = intent.getIntExtra("ecgNotification", -1);
        if (intExtra != 1 && intExtra != 2) {
            LogUtil.h("Ecg_EcgUtils", "ecgNotification type is undefined");
            return;
        }
        LogUtil.a("Ecg_EcgUtils", "pullEcgQuick from EcgPushReceiver");
        if (intExtra == 1) {
            HashMap hashMap = new HashMap(1);
            hashMap.put("click", 1);
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.ECG_QUICK_APP_NOTIFICATION_RECODE_JUMP_11000314.value(), hashMap, 0);
        }
        aPX_(activity, intent);
    }

    private static void aPX_(Activity activity, Intent intent) {
        String stringExtra = intent.getStringExtra("ecgPageType");
        boolean isLogined = LoginInit.getInstance(BaseApplication.getContext()).getIsLogined();
        if (!MainInteractors.a() || !isLogined) {
            LogUtil.a("Ecg_EcgUtils", "StartHealth to MainActivity");
            Intent launchIntentForPackage = activity.getPackageManager().getLaunchIntentForPackage(activity.getPackageName());
            if (launchIntentForPackage == null) {
                LogUtil.b("Ecg_EcgUtils", "launchIntentForPackage is null");
                activity.finish();
                return;
            }
            launchIntentForPackage.putExtra("pullEcgQuick", 1);
            launchIntentForPackage.putExtra("ecgPageType", stringExtra);
            if ("donate".equals(stringExtra)) {
                launchIntentForPackage.putExtra("donateId", intent.getStringExtra("donateId"));
            }
            launchIntentForPackage.putExtra("needLogin", !isLogined);
            activity.startActivity(launchIntentForPackage);
            if (intent.getIntExtra("ecgNotification", -1) == 1) {
                activity.finish();
                return;
            }
            return;
        }
        if ("ecgStrap".equals(stringExtra)) {
            H5proUtil.initH5pro();
            H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
            builder.addCustomizeJsModule(BleConstants.BLE_JSINTERACTION, BleJsInteractionCompact.class);
            builder.addCustomizeJsModule(BleConstants.BLE_HI_LINK, BleJsInteractionCompact.class);
            builder.showStatusBar();
            builder.setStatusBarTextBlack(true);
            H5ProClient.startH5MiniProgram(activity, "com.huawei.health.device.203358f2-9be7-4846-85dc-d22dbc53359e", builder.build());
            activity.finish();
            return;
        }
        mxv.crB_(activity, intent.getStringExtra("donateId"), stringExtra);
    }

    public static void aPV_(Activity activity, Uri uri) {
        if (activity == null) {
            LogUtil.h("Ecg_EcgUtils", "goToSkipEcgPageFromEcgQuick activity is null");
            return;
        }
        Bundle aQo_ = aQo_(uri);
        if (aQo_ == null) {
            LogUtil.h("Ecg_EcgUtils", "parsesSchemeData failed");
            activity.finish();
            return;
        }
        boolean isLogined = LoginInit.getInstance(BaseApplication.getContext()).getIsLogined();
        if (!MainInteractors.a() || !isLogined) {
            LogUtil.h("Ecg_EcgUtils", "Huawei Health is not logged in or the process is killed");
            aPU_(activity, aQo_, isLogined);
        } else {
            aPZ_(activity, aQo_);
        }
        activity.finish();
    }

    private static Bundle aQo_(Uri uri) {
        try {
            Bundle bundle = new Bundle();
            String queryParameter = uri.getQueryParameter("ecgH5Page");
            if (!TextUtils.isEmpty(queryParameter)) {
                bundle.putString("ecgH5Page", queryParameter);
                bundle.putString("donateId", uri.getQueryParameter("donateId"));
                return bundle;
            }
            bundle.putString("ecgPage", uri.getQueryParameter("ecgPage"));
            bundle.putString("orderId", uri.getQueryParameter("orderId"));
            bundle.putString("startTime", uri.getQueryParameter("startTime"));
            bundle.putString("endTime", uri.getQueryParameter("endTime"));
            bundle.putString("deviceCode", uri.getQueryParameter("deviceCode"));
            bundle.putString("validationCode", uri.getQueryParameter("validationCode"));
            bundle.putString("toUrl", uri.getQueryParameter("toUrl"));
            bundle.putString("openId", uri.getQueryParameter("openId"));
            bundle.putString("sourcesUrl", uri.getQueryParameter("sourcesUrl"));
            bundle.putString("reportId", uri.getQueryParameter("reportId"));
            bundle.putString("hlwToken", uri.getQueryParameter("hlwToken"));
            bundle.putString("cardNumber", uri.getQueryParameter("cardNumber"));
            return bundle;
        } catch (IllegalArgumentException unused) {
            LogUtil.b("Ecg_EcgUtils", "getEcgData IllegalArgumentException,will finish");
            return null;
        }
    }

    private static String aPR_(Bundle bundle, String str) {
        if (bundle == null) {
            LogUtil.b("Ecg_EcgUtils", "getStringFromBundle bundle is null,key:", str);
            return "";
        }
        if (bundle.containsKey(str)) {
            String string = bundle.getString(str);
            if (!TextUtils.isEmpty(string)) {
                return string;
            }
            LogUtil.a("Ecg_EcgUtils", "getStringFromBundle isEmpty,key:", str);
            return "";
        }
        LogUtil.b("Ecg_EcgUtils", "getStringFromBundle not containsKey,key:", str);
        return "";
    }

    private static void aPU_(Activity activity, Bundle bundle, boolean z) {
        LogUtil.a("Ecg_EcgUtils", "jump to mainActivity");
        Intent launchIntentForPackage = activity.getPackageManager().getLaunchIntentForPackage(activity.getPackageName());
        if (launchIntentForPackage == null) {
            LogUtil.h("Ecg_EcgUtils", "launchIntentForPackage is null");
            return;
        }
        launchIntentForPackage.putExtra("ecgBundle", bundle);
        launchIntentForPackage.putExtra("needLogin", !z);
        activity.startActivity(launchIntentForPackage);
    }

    private static void aPZ_(Activity activity, Bundle bundle) {
        String aPR_ = aPR_(bundle, "ecgH5Page");
        LogUtil.a("Ecg_EcgUtils", "ecgH5Page = ", aPR_);
        if ("donate".equals(aPR_)) {
            mxv.crt_(activity, aPR_(bundle, "donateId"), "donate");
        } else {
            aQs_(activity, bundle);
        }
    }

    private static String c() {
        String e = jah.c().e("ecg_helowin");
        if (!TextUtils.isEmpty(e)) {
            return e;
        }
        LogUtil.h("Ecg_EcgUtils", "ecgHelowinUrl is empty,will get it from XmlFile");
        return a();
    }

    private static void aQs_(final Activity activity, final Bundle bundle) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: gny
            @Override // java.lang.Runnable
            public final void run() {
                gnv.aQn_(bundle, activity);
            }
        });
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    static /* synthetic */ void aQn_(Bundle bundle, Activity activity) {
        char c;
        String c2 = c();
        if (TextUtils.isEmpty(c2)) {
        }
        String aPR_ = aPR_(bundle, "ecgPage");
        LogUtil.a("Ecg_EcgUtils", "ecgPage = ", aPR_);
        String str = c2 + "/hwhealth-h5/#/hw_authorized";
        aPR_.hashCode();
        switch (aPR_.hashCode()) {
            case -1655966981:
                if (aPR_.equals("activite")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1500711525:
                if (aPR_.equals("authorized")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -847291680:
                if (aPR_.equals("packagePayment")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 101142:
                if (aPR_.equals("faq")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 160146085:
                if (aPR_.equals("reportDetail")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 1418213207:
                if (aPR_.equals("historicalBillQuery")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 1703976755:
                if (aPR_.equals("submitAlgorithmAnalysis")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 1931132914:
                if (aPR_.equals("reportList")) {
                    c = 7;
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
                aQc_(activity, str, c2 + "/hwhealth-h5/#/Carmiactivation", bundle);
                break;
            case 1:
                aPS_(activity, c2, bundle);
                break;
            case 2:
                aQc_(activity, str, c2 + "/hwhealth-h5/#/financed", bundle);
                break;
            case 3:
                aQc_(activity, str, c2 + "/hwhealth-h5/#/server", bundle);
                break;
            case 4:
                aQc_(activity, str, c2 + "/hwhealth-h5/#/hw_detail", bundle);
                break;
            case 5:
                aQc_(activity, str, c2 + "/hwhealth-h5/#/hw_order", bundle);
                break;
            case 6:
                aQc_(activity, str, c2 + "/hwhealth-h5/#/export", bundle);
                break;
            case 7:
                aQc_(activity, str, c2 + "/hwhealth-h5/#/", bundle);
                break;
            default:
                LogUtil.h("Ecg_EcgUtils", "ecgPage is error:", aPR_);
                break;
        }
    }

    private static void aPS_(Activity activity, String str, Bundle bundle) {
        String str2 = str + "/hwhealth-h5/#/hw_authorized";
        String aPR_ = aPR_(bundle, "toUrl");
        if (aPR_ != null && (aPR_.startsWith("hap://app/com.huawei.health.ecg.collection/") || aPR_.startsWith("hwfastapp://com.huawei.health.ecg.collection/"))) {
            aQc_(activity, str2, aPR_, bundle);
        } else {
            LogUtil.h("Ecg_EcgUtils", "the toUrl is illegal");
            aQc_(activity, str2, "", bundle);
        }
    }

    private static void aQc_(final Activity activity, final String str, final String str2, final Bundle bundle) {
        activity.runOnUiThread(new Runnable() { // from class: gnz
            @Override // java.lang.Runnable
            public final void run() {
                gnv.aQe_(str, bundle, str2, activity);
            }
        });
    }

    static /* synthetic */ void aQe_(String str, Bundle bundle, String str2, Activity activity) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Ecg_EcgUtils", "webUrl is null");
            return;
        }
        H5proUtil.initH5pro();
        H5ProLaunchOption h5ProLaunchOption = new H5ProLaunchOption();
        h5ProLaunchOption.addCustomizeArg("orderId", aPR_(bundle, "orderId"));
        h5ProLaunchOption.addCustomizeArg("startTime", aPR_(bundle, "startTime"));
        h5ProLaunchOption.addCustomizeArg("endTime", aPR_(bundle, "endTime"));
        h5ProLaunchOption.addCustomizeArg("deviceCode", aPR_(bundle, "deviceCode"));
        h5ProLaunchOption.addCustomizeArg("validationCode", aPR_(bundle, "validationCode"));
        if (!TextUtils.isEmpty(str2)) {
            h5ProLaunchOption.addCustomizeArg("toUrl", str2);
        }
        h5ProLaunchOption.addCustomizeArg("openId", aPR_(bundle, "openId"));
        h5ProLaunchOption.addCustomizeArg("sourcesUrl", aPR_(bundle, "sourcesUrl"));
        h5ProLaunchOption.addCustomizeArg("reportId", aPR_(bundle, "reportId"));
        h5ProLaunchOption.addCustomizeArg("hlwToken", aPR_(bundle, "hlwToken"));
        h5ProLaunchOption.addCustomizeArg("cardNumber", aPR_(bundle, "cardNumber"));
        H5ProClient.startH5LightApp(activity, str, h5ProLaunchOption);
    }

    public static void aQa_(Activity activity, Intent intent) {
        if (aPQ_(intent) == 1) {
            LogUtil.a("Ecg_EcgUtils", "jump to HwSchemeBasicHealthActivity,pull ecgQuick");
            intent.putExtra("ecgNotification", 2);
            intent.setClassName(activity, "com.huawei.health.browseraction.HwSchemeBasicHealthActivity");
            activity.startActivity(intent);
        }
    }

    public static void aQb_(Activity activity, Intent intent) {
        if (aPO_(intent) == null || intent == null) {
            return;
        }
        LogUtil.a("Ecg_EcgUtils", "jump to HwSchemeBasicHealthActivity");
        intent.setClassName(activity, "com.huawei.health.browseraction.HwSchemeBasicHealthActivity");
        activity.startActivity(intent);
    }

    public static Bundle aPO_(Intent intent) {
        if (intent == null) {
            LogUtil.h("Ecg_EcgUtils", "getEcgBundle intent is null");
            return null;
        }
        return intent.getBundleExtra("ecgBundle");
    }

    public static int aPQ_(Intent intent) {
        if (intent == null) {
            LogUtil.h("Ecg_EcgUtils", "getPullEcgQuick intent is null");
            return -1;
        }
        return intent.getIntExtra("pullEcgQuick", -1);
    }

    public static void c(final Context context) {
        View inflate = View.inflate(context, R.layout.reminder_with_checkbox_dialog, null);
        TextView textView = (TextView) inflate.findViewById(R.id.reminder_context);
        ((HealthCheckBox) inflate.findViewById(R.id.no_more_reminder_checkbox)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.health.utils.EcgUtils$$ExternalSyntheticLambda4
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                gnv.aQf_(context, compoundButton, z);
            }
        });
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(context);
        final String str = cwo.b() ? "com.huawei.health.ecg.collection" : "com.huawei.health.h5.ecgce";
        if ("com.huawei.health.ecg.collection".equals(str)) {
            textView.setText(R.string._2130844460_res_0x7f021b2c);
        } else {
            textView.setText(R.string._2130845959_res_0x7f022107);
        }
        builder.d(R.string.IDS_device_replace_dialog_title_notification).czg_(inflate).czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: gnx
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cze_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: gob
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                gnv.aQh_(str, view);
            }
        });
        CustomViewDialog e = builder.e();
        e.setCancelable(false);
        e.show();
        SharedPreferenceManager.e(context, Integer.toString(10000), "last_time_show_activate_ecg_dialog", String.valueOf(System.currentTimeMillis()), (StorageParams) null);
    }

    public static /* synthetic */ void aQf_(Context context, CompoundButton compoundButton, boolean z) {
        SharedPreferenceManager.e(context, Integer.toString(10000), "last_time_show_activate_ecg_dialog", String.valueOf(z ? -1L : System.currentTimeMillis()), (StorageParams) null);
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    static /* synthetic */ void aQh_(String str, View view) {
        mxv.b(BaseApplication.getContext(), str, 7, null);
        ViewClickInstrumentation.clickOnView(view);
    }

    public static void b(final Context context) {
        View inflate = View.inflate(context, R.layout.dialog_first_ecg_service_tip, null);
        ((HealthTextView) mfm.cgM_(inflate, R.id.ecg_collect_text)).setText(context.getResources().getString(R.string._2130838365_res_0x7f02035d, "599"));
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(context);
        builder.czg_(inflate).czc_(R.string._2130838404_res_0x7f020384, new View.OnClickListener() { // from class: com.huawei.health.utils.EcgUtils$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                gnv.aQi_(context, view);
            }
        }).cze_(R.string._2130838367_res_0x7f02035f, new View.OnClickListener() { // from class: com.huawei.health.utils.EcgUtils$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                gnv.aQj_(context, view);
            }
        });
        CustomViewDialog e = builder.e();
        HealthButton healthButton = (HealthButton) e.findViewById(R.id.dialog_btn_positive);
        healthButton.setTextColor(context.getResources().getColor(R.color._2131298273_res_0x7f0907e1, null));
        healthButton.setBackgroundResource(R.drawable.button_background_emphasize);
        ((HealthButtonBarLayout) e.findViewById(R.id.button_bar)).setDividerDrawable(context.getResources().getDrawable(R.drawable._2131427926_res_0x7f0b0256, null));
        e.setCancelable(false);
        e.show();
        SharedPreferenceManager.e(context, Integer.toString(1), "KEY_SHOW_ECG_SERVICE_ACTIVATION_TIP_FLAG", String.valueOf(System.currentTimeMillis()), (StorageParams) null);
        b(context, 1);
    }

    public static /* synthetic */ void aQi_(Context context, View view) {
        b(context, 3);
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void aQj_(Context context, View view) {
        mxv.b(BaseApplication.getContext(), "com.huawei.health.ecg.collection", 9, "interpretation");
        b(context, 2);
        ViewClickInstrumentation.clickOnView(view);
    }

    private static void b(Context context, int i) {
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(i));
        hashMap.put("dialogType", 0);
        hashMap.put("position", 2);
        ixx.d().d(context, AnalyticsValue.ECG_DIALOG_SHOW_COUNT.value(), hashMap, 0);
    }

    public static void aQr_(final Context context, final Bundle bundle) {
        String string;
        int i;
        int i2;
        if (bundle == null) {
            LogUtil.h("Ecg_EcgUtils", "showGiftCardDialog bundle is null");
            return;
        }
        int i3 = bundle.getInt(HealthZonePushReceiver.DETAIL_TYPE);
        if (i3 == 301 || i3 == 305) {
            String string2 = bundle.getString("nickname");
            if (TextUtils.isEmpty(string2)) {
                string = context.getResources().getString(cwo.b() ? R.string._2130845088_res_0x7f021da0 : R.string._2130845862_res_0x7f0220a6);
            } else {
                string = context.getResources().getString(cwo.b() ? R.string._2130844717_res_0x7f021c2d : R.string._2130845861_res_0x7f0220a5, string2);
            }
            i = R.string._2130844718_res_0x7f021c2e;
            i2 = R.string._2130844719_res_0x7f021c2f;
        } else {
            i2 = R.string._2130838122_res_0x7f02026a;
            i = R.string._2130844722_res_0x7f021c32;
            if (i3 == 302) {
                string = context.getResources().getString(R.string._2130844720_res_0x7f021c30);
            } else if (i3 == 303) {
                string = context.getResources().getString(R.string._2130844721_res_0x7f021c31);
            } else {
                LogUtil.h("Ecg_EcgUtils", "showGiftCardDialog detailType is invalid");
                return;
            }
        }
        View inflate = View.inflate(context, R.layout.dialog_first_ecg_service_tip, null);
        ((HealthTextView) inflate.findViewById(R.id.ecg_collect_text)).setText(string);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(context);
        builder.czg_(inflate).czc_(i, new View.OnClickListener() { // from class: goa
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cze_(i2, new View.OnClickListener() { // from class: goe
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                gnv.aQm_(context, bundle, view);
            }
        });
        CustomViewDialog e = builder.e();
        e.setCancelable(false);
        e.show();
        ((MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class)).setMessageRead(bundle.getString("msgId"));
    }

    static /* synthetic */ void aQm_(Context context, Bundle bundle, View view) {
        if (context instanceof Activity) {
            mxv.crt_((Activity) context, bundle.getString("donateId"), "donate");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public static void aQq_(boolean z, boolean z2, final Handler handler) {
        if (z) {
            LogUtil.a("Ecg_EcgUtils", "showActivateEcgDialog is Oversea version");
            return;
        }
        if (z2) {
            LogUtil.a("Ecg_EcgUtils", "showActivateEcgDialog isAccountOut");
        } else if (owq.e()) {
            LogUtil.a("Ecg_EcgUtils", "showActivateEcgDialog isCloudDataSyncing");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: gns
                @Override // java.lang.Runnable
                public final void run() {
                    gnv.aQk_(handler);
                }
            });
        }
    }

    static /* synthetic */ void aQk_(Handler handler) {
        if (aPY_(handler)) {
            return;
        }
        DeviceInfo a2 = jpt.a("Ecg_EcgUtils");
        if (a2 == null || a2.getDeviceConnectState() != 2) {
            LogUtil.a("Ecg_EcgUtils", "showActivateEcgDialog deviceInfo is null or deviceInfo != DEVICE_CONNECTED");
            return;
        }
        DeviceCapability d = cvs.d();
        if (d == null || !d.isSupportEcgAuth()) {
            LogUtil.a("Ecg_EcgUtils", "showActivateEcgDialog device not support ecg");
        } else if (cwi.c(a2, 45)) {
            aPN_(a2, handler);
        } else {
            aPM_(handler);
        }
    }

    private static void aPN_(DeviceInfo deviceInfo, final Handler handler) {
        jgc.a().e(new IBaseResponseCallback() { // from class: god
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                gnv.aQd_(handler, i, obj);
            }
        }, deviceInfo);
    }

    static /* synthetic */ void aQd_(Handler handler, int i, Object obj) {
        if (!(obj instanceof EcgServiceActivationData)) {
            LogUtil.h("Ecg_EcgUtils", "showActivateEcgCardDialog getEcgIv object error.");
            return;
        }
        EcgServiceActivationData ecgServiceActivationData = (EcgServiceActivationData) obj;
        if (ecgServiceActivationData.getStatus() == 0 || (ecgServiceActivationData.getStatus() == 1 && ecgServiceActivationData.getProbationUser() == 0)) {
            LogUtil.a("Ecg_EcgUtils", "showActivateEcgCardDialog allowed");
            if (nsn.h(SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(1), "KEY_SHOW_ECG_SERVICE_ACTIVATION_TIP_FLAG")) < 0 || (System.currentTimeMillis() - r4) / 60000.0f < 1440.0f) {
                LogUtil.a("Ecg_EcgUtils", "showActivateEcgCardDialog in time range");
            } else if (handler != null) {
                handler.sendEmptyMessage(4021);
            }
        }
    }

    private static void aPM_(Handler handler) {
        if (nsn.h(SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(10000), "last_time_show_activate_ecg_dialog")) < 0 || (System.currentTimeMillis() - r0) / 60000.0f < 10080.0f) {
            LogUtil.a("Ecg_EcgUtils", "showActivateEcgAccountDialog in time range");
        } else if (handler != null) {
            aQp_(handler);
        }
    }

    private static void aQp_(final Handler handler) {
        cuk.b().registerDeviceSampleListener("hw.health.ecganalysis", new DataReceiveCallback() { // from class: gnv.1
            @Override // com.huawei.health.devicemgr.business.callback.DataReceiveCallback
            public void onDataReceived(int i, DeviceInfo deviceInfo, cvr cvrVar) {
                if (!(cvrVar instanceof cvq)) {
                    LogUtil.b("Ecg_EcgUtils", "Received a message with wrong format");
                    return;
                }
                List<cvn> configInfoList = ((cvq) cvrVar).getConfigInfoList();
                if (configInfoList == null || configInfoList.size() == 0) {
                    LogUtil.b("Ecg_EcgUtils", "Received a message are missing config info list");
                    return;
                }
                cvn cvnVar = configInfoList.get(0);
                if (cvnVar == null) {
                    LogUtil.b("Ecg_EcgUtils", "Received a message are missing config info");
                    return;
                }
                byte[] b = cvnVar.b();
                if (b == null) {
                    LogUtil.b("Ecg_EcgUtils", "Received a message are missing byte data");
                    return;
                }
                if (b[b.length - 1] == 0) {
                    handler.sendEmptyMessage(4020);
                }
                cuk.b().unRegisterDeviceSampleListener("hw.health.ecganalysis");
            }
        });
        cvn cvnVar = new cvn();
        cvnVar.e(900300006L);
        cvnVar.d(2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(cvnVar);
        cvq cvqVar = new cvq();
        cvqVar.setConfigInfoList(arrayList);
        cvqVar.setSrcPkgName("hw.health.ecganalysis");
        cvqVar.setWearPkgName("hw.watch.health.ecganalysis");
        cvqVar.setCommandId(0);
        cuk.b().sendSampleConfigCommand(cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "Ecg_EcgUtils"), cvqVar, "Ecg_EcgUtils");
    }

    private static boolean aPY_(Handler handler) {
        List<MessageObject> messages = ((MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class)).getMessages(String.valueOf(19), MessageConstant.ECG_MEDAL_TYPE);
        for (int i = 0; i < messages.size(); i++) {
            MessageObject messageObject = messages.get(i);
            if (messageObject != null && messageObject.getMsgFrom() != null && messageObject.getReadFlag() != 1) {
                String[] split = messageObject.getMsgFrom().split(Constants.LINK);
                if (split.length < 2) {
                    LogUtil.h("Ecg_EcgUtils", "msgFrom is invalid");
                } else {
                    int e = nsn.e(split[0]);
                    if ((e == 301 || e == 302 || e == 303 || e == 305) && handler != null) {
                        Bundle bundle = new Bundle();
                        bundle.putInt(HealthZonePushReceiver.DETAIL_TYPE, e);
                        bundle.putString("donateId", split[1]);
                        if (split.length > 2) {
                            bundle.putString("nickname", split[2]);
                        }
                        bundle.putString("msgId", messageObject.getMsgId());
                        Message obtainMessage = handler.obtainMessage(4025);
                        obtainMessage.setData(bundle);
                        handler.sendMessageDelayed(obtainMessage, 3000L);
                        return true;
                    }
                }
            }
        }
        LogUtil.a("Ecg_EcgUtils", "isShowGiftCardDialog messageObjects is null or have read");
        return false;
    }
}
