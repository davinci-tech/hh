package defpackage;

import android.bluetooth.BluetoothAdapter;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbtsdk.btcommon.BluetoothSwitchStateUtil;
import com.huawei.hwbtsdk.btdatatype.callback.BtSwitchStateCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.HealthButtonBarLayout;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.webview.WebViewActivity;
import com.huawei.ui.homehealth.device.callback.HonorPrivacyCallback;
import com.huawei.ui.homehealth.device.sitting.RecommendedItem;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.SharedPreferenceManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes6.dex */
public class ogq {
    private static CustomAlertDialog c;

    public static void b(Context context, c cVar, HonorPrivacyCallback honorPrivacyCallback) {
        if (context == null) {
            LogUtil.h("HonorPrivacyUpdateUtils", "showUpdateDialog context is null.");
            return;
        }
        if (cVar == null) {
            LogUtil.h("HonorPrivacyUpdateUtils", "showUpdateDialog parameter is null.");
            return;
        }
        if (cVar.d() == null) {
            LogUtil.h("HonorPrivacyUpdateUtils", "showUpdateDialog deviceGroupInfo is null.");
            return;
        }
        if (honorPrivacyCallback == null) {
            LogUtil.h("HonorPrivacyUpdateUtils", "showUpdateDialog callback is null.");
            return;
        }
        LogUtil.a("HonorPrivacyUpdateUtils", "showUpdateDialog position: ", Integer.valueOf(cVar.a()), ", clickZoneType: ", Integer.valueOf(cVar.c()));
        View cZO_ = cZO_(context, cVar, honorPrivacyCallback);
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(context);
        builder.cyp_(cZO_);
        CustomAlertDialog c2 = builder.c();
        c = c2;
        c2.setCancelable(false);
        c.show();
    }

    private static View cZO_(final Context context, final c cVar, final HonorPrivacyCallback honorPrivacyCallback) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dialog_honor_privacy_update_view, (ViewGroup) null);
        ((HealthTextView) inflate.findViewById(R.id.honor_privacy_main_title_tv)).setText(context.getResources().getString(R.string._2130844138_res_0x7f0219ea));
        ((HealthTextView) inflate.findViewById(R.id.honor_privacy_vice_title_tv)).setText(context.getResources().getString(R.string._2130844139_res_0x7f0219eb));
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.honor_privacy_message_tv);
        healthTextView.setMovementMethod(LinkMovementMethod.getInstance());
        healthTextView.setHighlightColor(ContextCompat.getColor(context, android.R.color.transparent));
        healthTextView.setText(cZN_(context));
        ((HealthButtonBarLayout) inflate.findViewById(R.id.honor_privacy_button_bar)).setDividerDrawable(context.getResources().getDrawable(R.drawable._2131427926_res_0x7f0b0256, null));
        HealthButton healthButton = (HealthButton) inflate.findViewById(R.id.honor_privacy_btn_negative);
        HealthButton healthButton2 = (HealthButton) inflate.findViewById(R.id.honor_privacy_btn_positive);
        healthButton.setText(context.getResources().getString(R.string._2130841129_res_0x7f020e29));
        healthButton2.setText(context.getResources().getString(R.string._2130839489_res_0x7f0207c1));
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: ogq.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("HonorPrivacyUpdateUtils", "getUpdateDialogView click no agree.");
                ogq.c(context, cVar, honorPrivacyCallback);
                ogq.a();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        healthButton2.setOnClickListener(new View.OnClickListener() { // from class: ogq.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("HonorPrivacyUpdateUtils", "getUpdateDialogView click agree.");
                cof.d(true);
                c.this.c(true);
                honorPrivacyCallback.signResult(c.this);
                oau.b(AnalyticsValue.HONOR_PRIVACY_UPDATE_AGREE.value());
                ogq.a();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a() {
        CustomAlertDialog customAlertDialog = c;
        if (customAlertDialog == null || !customAlertDialog.isShowing()) {
            return;
        }
        c.dismiss();
        c = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(final Context context, final c cVar, final HonorPrivacyCallback honorPrivacyCallback) {
        LogUtil.a("HonorPrivacyUpdateUtils", "Enter showConfirmDialog.");
        String string = context.getResources().getString(R.string._2130845159_res_0x7f021de7);
        String string2 = context.getResources().getString(R.string._2130845605_res_0x7f021fa5);
        String string3 = context.getResources().getString(R.string._2130837555_res_0x7f020033);
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(context);
        builder.e(string);
        builder.czA_(string2, new View.OnClickListener() { // from class: ogq.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("HonorPrivacyUpdateUtils", "showConfirmDialog click no agree.");
                ogq.e(context, cVar, honorPrivacyCallback);
                oau.b(AnalyticsValue.HONOR_PRIVACY_UPDATE_NO_AGREE.value());
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czE_(string3, new View.OnClickListener() { // from class: ogq.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("HonorPrivacyUpdateUtils", "showConfirmDialog click cancel.");
                c.this.c(false);
                honorPrivacyCallback.signResult(c.this);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        e.show();
    }

    private static SpannableString cZN_(Context context) {
        LogUtil.a("HonorPrivacyUpdateUtils", "Enter getPrivacyContent.");
        String string = context.getResources().getString(R.string._2130844142_res_0x7f0219ee);
        String string2 = context.getResources().getString(R.string._2130844144_res_0x7f0219f0);
        String string3 = context.getResources().getString(R.string._2130844145_res_0x7f0219f1, string2, string);
        if (nsn.ae(context)) {
            string3 = context.getResources().getString(R.string._2130844400_res_0x7f021af0, string2, string);
        }
        int[] iArr = new int[2];
        if (string3.lastIndexOf(string2) != -1) {
            iArr[0] = string3.lastIndexOf(string2);
        }
        if (string3.lastIndexOf(string) != -1) {
            iArr[1] = string3.lastIndexOf(string);
        }
        SpannableString spannableString = new SpannableString(string3);
        cZP_(context, spannableString, iArr[0], string2.length(), 2);
        cZP_(context, spannableString, iArr[1], string.length(), 1);
        return spannableString;
    }

    private static void cZP_(final Context context, SpannableString spannableString, int i, int i2, final int i3) {
        nsi.cKG_(spannableString, i, i2 + i, new ClickableSpan() { // from class: ogq.5
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                String string;
                LogUtil.a("HonorPrivacyUpdateUtils", "setContentClick onClick.");
                int i4 = i3;
                if (i4 == 1) {
                    string = context.getResources().getString(R.string._2130844142_res_0x7f0219ee);
                } else {
                    string = i4 == 2 ? context.getResources().getString(R.string._2130844144_res_0x7f0219f0) : "";
                }
                try {
                    Intent intent = new Intent(context, (Class<?>) WebViewActivity.class);
                    intent.putExtra("WebViewActivity.REQUEST_URL_KEY", ogq.b(context, i3));
                    intent.putExtra(Constants.JUMP_MODE_KEY, 10);
                    intent.putExtra("WebViewActivity.TITLE", string);
                    context.startActivity(intent);
                } catch (ActivityNotFoundException unused) {
                    LogUtil.b("HonorPrivacyUpdateUtils", "setContentClick ActivityNotFoundException");
                }
                ViewClickInstrumentation.clickOnView(view);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(context.getResources().getColor(R.color._2131296927_res_0x7f09029f));
                textPaint.setUnderlineText(false);
            }
        });
    }

    public static void e(final Context context, final c cVar, final HonorPrivacyCallback honorPrivacyCallback) {
        if (BluetoothAdapter.getDefaultAdapter() != null && !BluetoothAdapter.getDefaultAdapter().isEnabled()) {
            NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(context).e(context.getResources().getString(R.string.IDS_device_bluetooth_open_request)).czC_(R.string.IDS_device_bt_right_btn_info, new View.OnClickListener() { // from class: ogq.9
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a("HonorPrivacyUpdateUtils", "checkBluetoothStatus open bluetooth.");
                    new BluetoothSwitchStateUtil(context).d(new BtSwitchStateCallback() { // from class: ogq.9.3
                        @Override // com.huawei.hwbtsdk.btdatatype.callback.BtSwitchStateCallback
                        public void onBtSwitchStateCallback(int i) {
                            LogUtil.a("HonorPrivacyUpdateUtils", "checkBluetoothStatus bluetooth state is on.");
                            ogq.d(context, cVar.d());
                            cVar.c(false);
                            honorPrivacyCallback.signResult(cVar);
                        }
                    });
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).czz_(R.string.IDS_device_bt_left_btn_info, new View.OnClickListener() { // from class: ogq.8
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a("HonorPrivacyUpdateUtils", "checkBluetoothStatus click barred.");
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).e();
            e.setCancelable(false);
            e.show();
        } else {
            d(context, cVar.d());
            cVar.c(false);
            honorPrivacyCallback.signResult(cVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(Context context, cjv cjvVar) {
        LogUtil.h("HonorPrivacyUpdateUtils", "Enter deleteHonorWearDevice.");
        ArrayList arrayList = new ArrayList(16);
        if (cjvVar.i() instanceof cpm) {
            cpm cpmVar = (cpm) cjvVar.i();
            if (cpmVar != null) {
                arrayList.add(cpmVar.a());
                oae.c(context).e(arrayList, true);
                return;
            } else {
                LogUtil.h("HonorPrivacyUpdateUtils", "deleteHonorWearDevice deviceInfoForWear is null.");
                return;
            }
        }
        LogUtil.h("HonorPrivacyUpdateUtils", "deleteHonorWearDevice object is not DeviceInfoForWear.");
    }

    public static boolean b(Context context, cjv cjvVar) {
        if (context == null) {
            LogUtil.h("HonorPrivacyUpdateUtils", "isShowUpdateDialog context is null.");
            return false;
        }
        if (!d(cjvVar)) {
            LogUtil.h("HonorPrivacyUpdateUtils", "isShowUpdateDialog current device is not honor.");
            return false;
        }
        if (!dks.d(context)) {
            LogUtil.h("HonorPrivacyUpdateUtils", "isShowUpdateDialog current zone no need sign.");
            return false;
        }
        if (d(context)) {
            return true;
        }
        LogUtil.h("HonorPrivacyUpdateUtils", "isShowUpdateDialog no exist update.");
        return false;
    }

    public static void c() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: ogp
            @Override // java.lang.Runnable
            public final void run() {
                ogq.b();
            }
        });
    }

    static /* synthetic */ void b() {
        if (!dks.d(BaseApplication.getContext())) {
            LogUtil.h("HonorPrivacyUpdateUtils", "querySignResult current zone no need sign.");
            return;
        }
        if (!c(BaseApplication.getContext())) {
            LogUtil.h("HonorPrivacyUpdateUtils", "querySignResult current no honor device.");
        } else if (!e(BaseApplication.getContext())) {
            LogUtil.h("HonorPrivacyUpdateUtils", "querySignResult no more than one day.");
        } else {
            cof.d();
        }
    }

    private static boolean e(Context context) {
        String b = SharedPreferenceManager.b(context, String.valueOf(10008), "honor_privacy_query_time");
        if (!TextUtils.isEmpty(b)) {
            long currentTimeMillis = System.currentTimeMillis();
            LogUtil.a("HonorPrivacyUpdateUtils", "isQueryMoreThanOneDay nowTime: ", Long.valueOf(currentTimeMillis), ", lastScanTime: ", b);
            long n = CommonUtil.n(context, b);
            if (n != 0 && currentTimeMillis - n > 86400000) {
                LogUtil.a("HonorPrivacyUpdateUtils", "isQueryMoreThanOneDay interval was more than one day.");
                return true;
            }
            LogUtil.h("HonorPrivacyUpdateUtils", "isQueryMoreThanOneDay interval was less than one day.");
            return false;
        }
        LogUtil.a("HonorPrivacyUpdateUtils", "isQueryMoreThanOneDay first query.");
        return true;
    }

    private static boolean d(cjv cjvVar) {
        if (cjvVar == null) {
            LogUtil.h("HonorPrivacyUpdateUtils", "isHonorWearDevice deviceGroupInfo is null.");
            return false;
        }
        if (cjvVar.a() != 1) {
            LogUtil.h("HonorPrivacyUpdateUtils", "isHonorDevice is not wear device.");
            return false;
        }
        if (!(cjvVar.i() instanceof cpm)) {
            LogUtil.h("HonorPrivacyUpdateUtils", "isHonorDevice object is not DeviceInfoForWear.");
            return false;
        }
        cpm cpmVar = (cpm) cjvVar.i();
        if (cpmVar == null) {
            LogUtil.h("HonorPrivacyUpdateUtils", "isHonorWearDevice deviceInfoForWear is null.");
            return false;
        }
        if (cpmVar.g()) {
            LogUtil.h("HonorPrivacyUpdateUtils", "isHonorDevice is CloudDevice");
            return false;
        }
        if (jfu.n(cpmVar.i())) {
            LogUtil.h("HonorPrivacyUpdateUtils", "isHonorWearDevice is honor wear device.");
            return true;
        }
        LogUtil.h("HonorPrivacyUpdateUtils", "isHonorWearDevice is not honor wear device.");
        return false;
    }

    private static boolean c(Context context) {
        int a2 = SharedPreferenceManager.a(context);
        LogUtil.a("HonorPrivacyUpdateUtils", "isBondHonorWearDevice wearDeviceCount: ", Integer.valueOf(a2));
        return a2 > 0;
    }

    private static boolean d(Context context) {
        return !SharedPreferenceManager.e(context, LoginInit.getInstance(context).getAccountInfo(1011));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(Context context, int i) {
        StringBuilder sb;
        String language = Locale.getDefault().getLanguage();
        String script = Locale.getDefault().getScript();
        String commonCountryCode = GRSManager.a(context).getCommonCountryCode();
        if (TextUtils.isEmpty(commonCountryCode)) {
            commonCountryCode = SharedPreferenceManager.b(context, Integer.toString(PrebakedEffectId.RT_SNIPER_RIFLE), "select_country");
            if (TextUtils.isEmpty(commonCountryCode)) {
                commonCountryCode = "GB";
            }
        }
        String country = Locale.getDefault().getCountry();
        if (TextUtils.isEmpty(script)) {
            sb = new StringBuilder();
            sb.append(language);
        } else {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(language);
            sb2.append("_");
            sb2.append(script);
            sb = sb2;
        }
        sb.append("_");
        sb.append(country);
        String sb3 = sb.toString();
        String e = jah.c().e("domain_honor");
        if (i == 1) {
            return e + "/minisite/cloudservice/Health/privacy-statement.htm?country=" + commonCountryCode + com.huawei.openalliance.ad.constant.Constants.LANGUAGE + sb3;
        }
        if (i == 2) {
            return e + "/minisite/cloudservice/Health/terms.htm?country=" + commonCountryCode + com.huawei.openalliance.ad.constant.Constants.LANGUAGE + sb3;
        }
        LogUtil.h("HonorPrivacyUpdateUtils", "getHonorPrivacyUrl error type");
        return "";
    }

    public static cjv e(List<cjv> list, RecommendedItem recommendedItem) {
        if (list == null || list.size() == 0) {
            LogUtil.h("HonorPrivacyUpdateUtils", "getDeviceInfoByMac productInfoList is empty.");
            return null;
        }
        if (recommendedItem == null || TextUtils.isEmpty(recommendedItem.getMac())) {
            LogUtil.h("HonorPrivacyUpdateUtils", "getDeviceInfoByMac mac is empty.");
            return null;
        }
        for (cjv cjvVar : list) {
            if ((cjvVar.i() instanceof cpm) && knl.a(((cpm) cjvVar.i()).a()).equals(recommendedItem.getMac())) {
                return cjvVar;
            }
        }
        return null;
    }

    public static class c {

        /* renamed from: a, reason: collision with root package name */
        private boolean f15657a = false;
        private int b;
        private cjv c;
        private RecommendedItem d;
        private int e;

        public cjv d() {
            return this.c;
        }

        public void b(cjv cjvVar) {
            this.c = cjvVar;
        }

        public RecommendedItem e() {
            return this.d;
        }

        public void e(RecommendedItem recommendedItem) {
            this.d = recommendedItem;
        }

        public int a() {
            return this.b;
        }

        public void c(int i) {
            this.b = i;
        }

        public int c() {
            return this.e;
        }

        public void b(int i) {
            this.e = i;
        }

        public boolean b() {
            return this.f15657a;
        }

        public void c(boolean z) {
            this.f15657a = z;
        }
    }
}
