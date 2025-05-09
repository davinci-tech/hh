package defpackage;

import android.content.Context;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceCapability;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.marketing.utils.EcgFilterManager;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.stories.fitness.activity.heartrate.WarningHRActivity;
import health.compact.a.GRSManager;
import health.compact.a.Utils;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class pxz {
    public static boolean d() {
        return a() || j();
    }

    public static boolean a() {
        if (!Utils.o() && !c()) {
            DeviceCapability d = cvs.d();
            if (d != null && d.isSupportEcgAuth()) {
                return true;
            }
            LogUtil.h("HealthHeartRate_HeartRateUtils", "capability is null or not support ecg");
        }
        return false;
    }

    public static boolean j() {
        EcgFilterManager a2 = EcgFilterManager.a();
        if (EcgFilterManager.d() && a2.n()) {
            return a2.i();
        }
        return false;
    }

    public static boolean h() {
        return eii.e();
    }

    public static boolean c() {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "HealthHeartRate_HeartRateUtils");
        return deviceInfo == null || deviceInfo.getDeviceConnectState() != 2;
    }

    public static boolean b() {
        DeviceCapability d = cvs.d();
        return d != null && d.isSupportHeartRateRaiseAlarm();
    }

    public static boolean e() {
        DeviceCapability d = cvs.d();
        return d != null && d.isSupportHeartRateDownAlarm();
    }

    public static void a(String str, String str2, HealthTextView healthTextView, int i, int i2) {
        Context context = BaseApplication.getContext();
        SpannableString spannableString = new SpannableString(str);
        int length = str.length() - str2.length();
        c cVar = new c(context, i, i2);
        if (length < 0 || spannableString.toString().length() > str.length()) {
            LogUtil.h("HealthHeartRate_HeartRateUtils", "HeartRateUtils setSpan IndexOutOfBounds ");
            return;
        }
        spannableString.setSpan(cVar, length, spannableString.toString().length(), 18);
        spannableString.setSpan(new TextAppearanceSpan(context, R.style.heart_rate_content_text), length, spannableString.toString().length(), 33);
        if (healthTextView == null) {
            LogUtil.h("HealthHeartRate_HeartRateUtils", "healthHwTextView is null");
            return;
        }
        healthTextView.setMovementMethod(LinkMovementMethod.getInstance());
        healthTextView.setHighlightColor(context.getResources().getColor(android.R.color.transparent));
        healthTextView.setText(spannableString);
    }

    public static class c extends ClickableSpan {

        /* renamed from: a, reason: collision with root package name */
        private int f16341a;
        private Context c;
        private int e;

        public c(Context context, int i, int i2) {
            this.c = context;
            this.f16341a = i;
            this.e = i2;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View view) {
            LogUtil.a("MyClickableSpan", "ClickableSpan_onClick");
            if (this.c != null) {
                if (this.e == 0) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("click", 1);
                    hashMap.put("type", Integer.valueOf(this.f16341a));
                    ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_HOME_HEART_RATE_RECODE_JUMP_2090013.value(), hashMap, 0);
                    WarningHRActivity.b(this.c);
                }
                int i = this.e;
                if (i == 1 || i == 2) {
                    LogUtil.a("MyClickableSpan", "heart rate ecg collection or diagram measure");
                    pxz.e(this.e);
                }
            }
            ViewClickInstrumentation.clickOnView(view);
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint textPaint) {
            if (textPaint != null) {
                super.updateDrawState(textPaint);
                textPaint.setUnderlineText(false);
            }
        }
    }

    public static void e(final int i) {
        final Context context = BaseApplication.getContext();
        GRSManager.a(context).e("domainContentcenterDbankcdnNew", new GrsQueryCallback() { // from class: pxz.4
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str) {
                LogUtil.c("HealthHeartRate_HeartRateUtils", "Url is :", str + "/sandbox/cch5/health/weixinScan/dist/index.html#/measureGuide");
                bzs.e().initH5Pro();
                H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
                String str2 = i == 2 ? "?support=1" : "";
                H5ProClient.startH5LightApp(context, str + "/sandbox/cch5/health/weixinScan/dist/index.html#/measureGuide" + str2, builder.build());
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i2) {
                LogUtil.b("HealthHeartRate_HeartRateUtils", "onCallBackFail errorCode = ", Integer.valueOf(i2));
            }
        });
    }

    public static void a(HiDataReadResultListener hiDataReadResultListener) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(0L, System.currentTimeMillis());
        hiDataReadOption.setType(new int[]{2101, 2102});
        HiHealthManager.d(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, hiDataReadResultListener);
    }
}
