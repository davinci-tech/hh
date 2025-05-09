package defpackage;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.os.SystemInfo;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.login.ui.login.util.ILoginCallback;
import com.huawei.operation.utils.Constants;
import com.huawei.phoneservice.faq.base.constants.FaqConstants;
import com.huawei.phoneservice.faq.base.entity.Builder;
import com.huawei.phoneservice.faq.base.util.SdkListener;
import com.huawei.phoneservice.feedback.utils.SdkFeedbackProblemManager;
import com.huawei.phoneservice.feedbackcommon.utils.b;
import health.compact.a.CommonUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import health.compact.a.WhiteBoxManager;
import java.lang.ref.WeakReference;
import java.util.Locale;

/* loaded from: classes5.dex */
public class ixj implements SdkListener {

    /* renamed from: a, reason: collision with root package name */
    private static volatile boolean f13650a;
    private static final Object b = new Object();
    private static String c;
    private static volatile ixj e;
    private WeakReference<Activity> d;
    private WeakReference<Handler> f;
    private volatile boolean j = false;
    private volatile boolean g = false;
    private volatile boolean i = false;
    private boolean h = false;

    private ixj() {
        WhiteBoxManager d = WhiteBoxManager.d();
        d.d(1, 34);
        d.d(1, 1034);
        d.d(1, 2034);
    }

    public static ixj b() {
        if (e == null) {
            synchronized (b) {
                if (e == null) {
                    e = new ixj();
                }
            }
        }
        return e;
    }

    public static boolean e() {
        String e2 = jah.c().e("common_config_feedbacksdk_switch");
        LogUtil.a("HwFeedbackApi", "isFeedbackSwitchOpen , COMMON_CONFIG_FEEDBACKSDK_SWITCH = ", e2);
        return "on".equalsIgnoreCase(e2);
    }

    public static boolean c() {
        String e2 = jah.c().e("common_config_feedbacksdk_switch");
        LogUtil.a("HwFeedbackApi", "isFeedbackSwitchClose , COMMON_CONFIG_FEEDBACKSDK_SWITCH = ", e2);
        return "off".equalsIgnoreCase(e2);
    }

    private boolean j() {
        String e2 = jah.c().e("common_config_feedbacksdk_switch");
        LogUtil.a("HwFeedbackApi", "isFeedbackSwitchValidate , COMMON_CONFIG_FEEDBACKSDK_SWITCH = ", e2);
        if (Utils.o()) {
            return "on".equalsIgnoreCase(e2);
        }
        return !"off".equalsIgnoreCase(e2);
    }

    public void e(Context context) {
        LogUtil.a("HwFeedbackApi", "enter resetSdkLanguage");
        b.h().saveSdk("language", b(context));
    }

    public int bCP_(Activity activity, boolean z) {
        return bCQ_(activity, z, false, "", "");
    }

    public int bCQ_(Activity activity, boolean z, boolean z2, String str, String str2) {
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            LogUtil.a("HwFeedbackApi", "init activity is null, finish or destroyed");
            return -1;
        }
        this.d = new WeakReference<>(activity);
        this.h = z;
        ReleaseLogUtil.b("R_HwFeedbackApi", "init mIsInit = ", Boolean.valueOf(this.g));
        if (this.g) {
            if (this.h) {
                this.h = false;
                bCN_(activity, z2, str, str2);
            }
            return 0;
        }
        bCO_(this.h, null);
        return -1;
    }

    public void bCO_(final boolean z, final Handler handler) {
        if (!f()) {
            LogUtil.a("HwFeedbackApi", "not need init sdk");
        } else if (HandlerExecutor.c()) {
            i();
            jdx.b(new Runnable() { // from class: ixj.2
                @Override // java.lang.Runnable
                public void run() {
                    ixj.this.bCM_(z, handler);
                }
            });
        } else {
            HandlerExecutor.a(new Runnable() { // from class: ixj.3
                @Override // java.lang.Runnable
                public void run() {
                    ixj.this.bCO_(z, handler);
                }
            });
        }
    }

    private boolean f() {
        return !((SystemInfo.b(true) || CommonUtil.h(BaseApplication.e()) == 0) && Utils.o()) && Utils.i() && LoginInit.getInstance(BaseApplication.e()).getIsLogined() && !CommonUtil.bp() && j();
    }

    private void i() {
        if (f13650a) {
            return;
        }
        f13650a = true;
        b.h().init(BaseApplication.vZ_());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bCM_(boolean z, Handler handler) {
        LogUtil.a("HwFeedbackApi", "initAsync()");
        this.f = new WeakReference<>(handler);
        this.h = z;
        Application vZ_ = BaseApplication.vZ_();
        this.j = true;
        SharedPreferenceManager.e(Integer.toString(PrebakedEffectId.RT_FLY), "Initialized_Feedback", false);
        Builder builder = new Builder();
        b(LoginInit.getInstance(vZ_).getAccountInfo(1015));
        if (TextUtils.isEmpty(c)) {
            c(vZ_);
        }
        builder.set("channel", "1020").set("language", b(vZ_)).set("country", a(vZ_)).set("appVersion", CommonUtil.e(vZ_)).set("accessToken", c).set(FaqConstants.FAQ_LOG_SERVER_APPID, "1019").set(FaqConstants.FAQ_SHASN, CommonUtil.a((Context) vZ_, false)).set(FaqConstants.FAQ_ROMVERSION, CommonUtil.ai()).set("osVersion", Build.VERSION.RELEASE).set("countryCode", Constants.DEFAULT_MCC_CODE).set(FaqConstants.FAQ_USE_OLD_DOMAIN, "Y").set(FaqConstants.FAQ_UPLOAD_FLAG, "2");
        if (CommonUtil.bv()) {
            builder.set(FaqConstants.FAQ_LOG_SERVER_LOG_PATH, BaseApplication.d());
        } else {
            builder.set(FaqConstants.FAQ_LOG_SERVER_LOG_PATH, "huaweisystem/" + BaseApplication.d()).set(FaqConstants.FAQ_LOG_SERVER_LOG_SDCARD, "Y");
        }
        if (!CommonUtil.bh()) {
            builder.set(FaqConstants.FAQ_EMUIVERSION, "10.1.0");
        }
        b.h().init(vZ_, builder, this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(String str) {
        c = str;
    }

    @Override // com.huawei.phoneservice.faq.base.util.SdkListener
    public void onSdkInit(int i, int i2, String str) {
        ReleaseLogUtil.b("R_HwFeedbackApi", "init onSdkInit result:", Integer.valueOf(i), " code:", Integer.valueOf(i2), " msg:", str);
        this.g = i == 0;
        this.j = false;
        if (!this.g && 3 == i2) {
            this.i = true;
        }
        SharedPreferenceManager.e(Integer.toString(PrebakedEffectId.RT_FLY), "Initialized_Feedback", this.g);
        WeakReference<Handler> weakReference = this.f;
        if (weakReference == null) {
            LogUtil.h("HwFeedbackApi", "init onSdkInit mHandlerWeakReference is null");
            return;
        }
        Handler handler = weakReference.get();
        if (handler != null) {
            Message obtainMessage = handler.obtainMessage();
            obtainMessage.what = 16;
            obtainMessage.obj = Boolean.valueOf(this.g);
            handler.sendMessage(obtainMessage);
        }
        WeakReference<Activity> weakReference2 = this.d;
        if (weakReference2 == null) {
            LogUtil.h("HwFeedbackApi", "init onSdkInit mActivityWeakReference is null");
            return;
        }
        Activity activity = weakReference2.get();
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
            LogUtil.a("HwFeedbackApi", "init onSdkInit activity is null, finish or destroyed");
        } else if (this.g && this.h) {
            this.h = false;
            bCN_(activity, false, "", "");
        }
    }

    public boolean h() {
        return this.j;
    }

    public boolean a() {
        return this.g;
    }

    public boolean d() {
        return this.i;
    }

    @Override // com.huawei.phoneservice.faq.base.util.SdkListener
    public void onSdkErr(String str, String str2) {
        LogUtil.h("HwFeedbackApi", "init onSdkErr key:", str);
        String accountInfo = LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1015);
        if (TextUtils.isEmpty(accountInfo) || accountInfo.equals(str2)) {
            c(BaseApplication.e());
        } else {
            b.h().saveSdk("accessToken", accountInfo);
        }
    }

    @Override // com.huawei.phoneservice.faq.base.util.SdkListener
    public boolean haveSdkErr(String str) {
        LogUtil.h("HwFeedbackApi", "init haveSdkErr key:", str);
        return "accessToken".equals(str);
    }

    private String b(Context context) {
        Resources resources;
        Configuration configuration;
        String language = Locale.getDefault().getLanguage();
        if (!TextUtils.isEmpty(language)) {
            return language;
        }
        if (context != null && (resources = context.getResources()) != null && (configuration = resources.getConfiguration()) != null && configuration.locale != null) {
            String language2 = configuration.locale.getLanguage();
            if (!TextUtils.isEmpty(language2)) {
                return language2;
            }
        }
        return MLAsrConstants.LAN_ZH;
    }

    private String a(Context context) {
        if (context != null) {
            LoginInit loginInit = LoginInit.getInstance(context);
            if (loginInit.getIsLogined()) {
                String accountInfo = loginInit.getAccountInfo(1010);
                if (!TextUtils.isEmpty(accountInfo)) {
                    return accountInfo;
                }
            }
        }
        String country = Locale.getDefault().getCountry();
        return TextUtils.isEmpty(country) ? "CN" : country;
    }

    private void c(Context context) {
        WeakReference<Activity> weakReference = this.d;
        if (weakReference == null) {
            LogUtil.h("HwFeedbackApi", "obtainToken mActivityWeakReference == null");
        } else if (weakReference.get() == null) {
            LogUtil.h("HwFeedbackApi", "init onSdkErr activity is null");
        } else {
            LoginInit.getInstance(context).refreshAccessToken(new ILoginCallback() { // from class: ixj.1
                @Override // com.huawei.login.ui.login.util.ILoginCallback
                public void onLoginSuccess(Object obj) {
                    if (!(obj instanceof String)) {
                        LogUtil.h("HwFeedbackApi", "obtainToken object convert fail");
                        return;
                    }
                    String str = (String) obj;
                    ixj.b(str);
                    b.h().saveSdk("accessToken", str);
                }

                @Override // com.huawei.login.ui.login.util.ILoginCallback
                public void onLoginFailed(Object obj) {
                    LogUtil.h("HwFeedbackApi", "onLoginFailed:", obj);
                }
            });
        }
    }

    private void bCN_(Activity activity, boolean z, String str, String str2) {
        String accountInfo = LoginInit.getInstance(activity).getAccountInfo(1015);
        if (TextUtils.isEmpty(c) && TextUtils.isEmpty(accountInfo)) {
            c(activity.getApplicationContext());
        } else if (TextUtils.isEmpty(c) && !TextUtils.isEmpty(accountInfo)) {
            b(accountInfo);
            b.h().saveSdk("accessToken", accountInfo);
        } else {
            LogUtil.a("HwFeedbackApi", "startActivity , init sdk complete");
        }
        if (!TextUtils.isEmpty(str2) && !TextUtils.isEmpty(str)) {
            boolean contains = str2.contains(Constants.LEFT_BRACKET_ONLY);
            if (z && contains) {
                str2 = str2.substring(0, str2.indexOf(Constants.LEFT_BRACKET_ONLY));
            }
            b.h().saveSdk("model", str);
            b.h().saveSdk(FaqConstants.FAQ_SOFT_VERSION, str2);
        }
        ReleaseLogUtil.b("R_HwFeedbackApi", "gotoFeedback");
        SdkFeedbackProblemManager.getManager().gotoFeedback(activity, null, -1);
    }

    public void g() {
        ReleaseLogUtil.b("R_HwFeedbackApi", "resetInitFlag");
        this.g = false;
        this.j = false;
    }
}
