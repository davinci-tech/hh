package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import android.view.View;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.FitnessTotalData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.thirdloginbasemodule.ThirdLoginDataStorageUtil;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.me.activity.PrivacyCenterActivity;
import com.tencent.mm.opensdk.modelbiz.JumpToBizProfile;
import com.tencent.mm.opensdk.modelbiz.WXLaunchMiniProgram;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.KeyValDbManager;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.WhiteBoxManager;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class sbk {
    private static volatile sbk b;

    /* renamed from: a, reason: collision with root package name */
    private String f17008a;
    private String c;
    private Timer f;
    private String g;
    private Handler n;
    private CommonDialog21 h = null;
    private String i = "2.0";
    private String e = "";
    private Context d = BaseApplication.getContext();
    private IWXAPI j = j();

    private sbk() {
    }

    private void i() {
        if (TextUtils.isEmpty(this.f17008a)) {
            this.f17008a = GRSManager.a(this.d).getUrl("healthCloudUrl");
        }
    }

    public static sbk a(Context context) {
        if (b == null) {
            synchronized (sbk.class) {
                if (b == null) {
                    b = new sbk();
                }
            }
        }
        return b;
    }

    public void dUY_(Handler handler) {
        LogUtil.a("UIME_WeChatInteactors", "setWeChatHandler()");
        this.n = handler;
    }

    private void g() {
        WhiteBoxManager d = WhiteBoxManager.d();
        try {
            this.e = new String(d.a(Base64.decode(d.d(1, 17) + d.d(1, 1017) + d.d(1, 2017), 0)), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LogUtil.b("UIME_WeChatInteactors", "initAppID appid UnsupportedEncodingException, exception is ", e.getMessage());
        } catch (Exception unused) {
            LogUtil.b("UIME_WeChatInteactors", "initAppID Exception");
        }
    }

    private IWXAPI j() {
        if (TextUtils.isEmpty(this.e)) {
            g();
            IWXAPI createWXAPI = WXAPIFactory.createWXAPI(this.d, this.e, false);
            this.j = createWXAPI;
            createWXAPI.registerApp(this.e);
        }
        return this.j;
    }

    public void e(Context context) {
        LogUtil.a("UIME_WeChatInteactors", "go2WeChatHandle start");
        if (context == null) {
            LogUtil.b("UIME_WeChatInteactors", "context is null!");
            return;
        }
        if (this.n == null) {
            this.n = new Handler();
        }
        if ("false".equals(rvo.e(context.getApplicationContext()).a(3))) {
            LogUtil.h("UIME_WeChatInteactors", "syncSportStatus is false.");
            d(context, R$string.IDS_hw_healt_data_share_wechat_rank_list);
            return;
        }
        this.c = LoginInit.getInstance(context).getAccountInfo(1011);
        this.g = LoginInit.getInstance(context).getAccountInfo(1008);
        try {
            this.i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.b("UIME_WeChatInteactors", "go2WeChatHandle getAppVersionName() Exception is ", e.getMessage());
        }
        LogUtil.a("UIME_WeChatInteactors", "go2WeChatHandle getAppVersionName() return is ", this.i);
        if (!CommonUtil.aa(context.getApplicationContext())) {
            LogUtil.a("UIME_WeChatInteactors", "go2WeChatHandle no network!");
            nrh.b(context.getApplicationContext(), R$string.IDS_connect_network);
        } else {
            h(context);
        }
    }

    private void h(Context context) {
        if (!j().isWXAppInstalled()) {
            LogUtil.a("UIME_WeChatInteactors", "go2WeChatHandle WeChat is not installed! start install it ");
            try {
                Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("market://details?id=com.tencent.mm"));
                intent.addFlags(268435456);
                context.startActivity(intent);
                return;
            } catch (ActivityNotFoundException e) {
                LogUtil.b("UIME_WeChatInteactors", "go2WeChatHandle install Exception exception = ", e.getMessage());
                j(context);
                return;
            }
        }
        f(context);
        n(context);
    }

    private void n(final Context context) {
        final Activity wa_;
        if (context instanceof Activity) {
            wa_ = (Activity) context;
        } else {
            wa_ = com.huawei.haf.application.BaseApplication.wa_();
        }
        jdx.b(new Runnable() { // from class: sbi
            @Override // java.lang.Runnable
            public final void run() {
                sbk.this.dUX_(context, wa_);
            }
        });
    }

    /* synthetic */ void dUX_(final Context context, Activity activity) {
        final boolean b2 = b(context);
        if (activity == null) {
            LogUtil.h("UIME_WeChatInteactors", "activity is null");
        } else {
            activity.runOnUiThread(new Runnable() { // from class: sbj
                @Override // java.lang.Runnable
                public final void run() {
                    sbk.this.e(context, b2);
                }
            });
        }
    }

    /* synthetic */ void e(Context context, boolean z) {
        Intent intent = new Intent();
        if (!(context instanceof Activity)) {
            intent.setFlags(268435456);
        }
        if (z) {
            intent.setClassName(context, "com.huawei.ui.thirdpartservice.activity.wechat.WeChatHealthActivity");
        } else {
            intent.setClassName(context, "com.huawei.ui.thirdpartservice.activity.wechat.WeChatConnectActivity");
        }
        if (context == null) {
            BaseApplication.getContext().startActivity(intent);
        } else {
            context.startActivity(intent);
        }
        c();
    }

    public boolean b(Context context) {
        return "true".equals(i(context));
    }

    private void j(Context context) {
        if (context instanceof Activity) {
            String upperCase = context.getString(R$string.IDS_common_notification_know_tips).toUpperCase(Locale.ENGLISH);
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(context);
            builder.e(context.getString(R$string.IDS_hw_data_share_app_not_install)).czE_(upperCase, new View.OnClickListener() { // from class: sbk.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            NoTitleCustomAlertDialog e = builder.e();
            e.setCancelable(false);
            Activity activity = (Activity) context;
            if (activity.isDestroyed() || activity.isFinishing()) {
                return;
            }
            e.show();
        }
    }

    public void b(Context context, final String str) {
        LogUtil.a("UIME_WeChatInteactors", "bindWeChatRank() CustomTextAlertDialog enter");
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(context);
        builder.b(context.getString(R$string.IDS_hw_wechat_rank_show_common_title)).e(context.getString(R$string.IDS_hw_wechat_rank_bind_title_common_dialog_content_new)).cyS_(context.getString(R$string.IDS_hw_common_ui_dialog_cancel), new View.OnClickListener() { // from class: sbk.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("UIME_WeChatInteactors", "bindTitle setNegativeButton");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        boolean z = LanguageUtil.f(context) || LanguageUtil.ao(context) || LanguageUtil.w(context);
        boolean z2 = LanguageUtil.t(context) || LanguageUtil.ba(context) || LanguageUtil.bb(context);
        if (z || z2 || LanguageUtil.az(context)) {
            builder.cyV_(context.getString(R$string.IDS_hw_wechat_rank_bind_enter).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: sbk.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a("UIME_WeChatInteactors", "bindTitle setPositiveButton");
                    sbk.this.d(str);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        } else {
            builder.cyV_(context.getString(R$string.IDS_hw_wechat_rank_bind_enter).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: sbk.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a("UIME_WeChatInteactors", "bindTitle setPositiveButton");
                    sbk.this.d(str);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        builder.a().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str) {
        if (this.n == null) {
            LogUtil.b("UIME_WeChatInteactors", "weChatHandler is null!");
            return;
        }
        if (str == null || "".equals(str)) {
            LogUtil.a("UIME_WeChatInteactors", "jumpToWechat() get ticket fail!");
            this.n.sendEmptyMessage(203);
        } else if (!str.startsWith(BaseApplication.getContext().getResources().getString(R$string.wechat_url))) {
            LogUtil.a("UIME_WeChatInteactors", "jumpToWechat() get invalid ticket!");
            this.n.sendEmptyMessage(203);
        } else {
            LogUtil.a("UIME_WeChatInteactors", "jumpToWechat() OK! being start weChat!");
            b(str);
        }
    }

    private void b(String str) {
        Message obtain = Message.obtain();
        obtain.what = 201;
        obtain.obj = str;
        this.n.sendMessage(obtain);
    }

    public void a(String str) {
        LogUtil.a("UIME_WeChatInteactors", "enter jumpToHwPublic");
        JumpToBizProfile.Req req = new JumpToBizProfile.Req();
        req.toUserName = "gh_b0429e7fa788";
        req.profileType = 1;
        req.extMsg = str;
        j().sendReq(req);
    }

    private void f(Context context) {
        if (context instanceof Activity) {
            Activity activity = (Activity) context;
            if (activity.isDestroyed() || activity.isFinishing()) {
                return;
            }
            CommonDialog21 commonDialog21 = this.h;
            if (commonDialog21 == null) {
                commonDialog21 = CommonDialog21.d(context, R.style.app_update_dialogActivity);
            }
            this.h = commonDialog21;
            commonDialog21.e(context.getString(R$string.IDS_wechat_public_jump_dialog));
            commonDialog21.setCancelable(false);
            commonDialog21.a();
            commonDialog21.show();
            LogUtil.a("UIME_WeChatInteactors", "mJumpToHwPublicDialog.show()");
            Timer timer = this.f;
            if (timer != null) {
                timer.cancel();
            }
            this.f = new Timer("UIME_WeChatInteactors");
            this.f.schedule(new TimerTask() { // from class: sbk.7
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    if (sbk.this.n == null) {
                        return;
                    }
                    sbk.this.n.sendEmptyMessage(202);
                }
            }, OpAnalyticsConstants.H5_LOADING_DELAY);
        }
    }

    public void c() {
        try {
            a();
        } catch (IllegalArgumentException e) {
            LogUtil.b("UIME_WeChatInteactors", "dismissJumpToHwPublicDialog exception is ", e.getMessage());
        }
    }

    private void a() {
        LogUtil.a("UIME_WeChatInteactors", "handleMessage ", "dismissJumpToHwPublicDialog ");
        CommonDialog21 commonDialog21 = this.h;
        if (commonDialog21 == null || !commonDialog21.isShowing()) {
            return;
        }
        LogUtil.a("UIME_WeChatInteactors", "dismissJumpToHwPublicDialog ", "cancel ");
        this.h.cancel();
        Timer timer = this.f;
        if (timer != null) {
            timer.cancel();
        }
        this.h = null;
    }

    private boolean f() {
        return TextUtils.isEmpty(this.g) || TextUtils.isEmpty(this.c);
    }

    public String i(Context context) {
        i();
        String str = "";
        if (context == null || Utils.o()) {
            LogUtil.b("UIME_WeChatInteactors", "isUserBinded Context is null || isOversea.");
            return "";
        }
        try {
            str = new JSONObject(d(context)).optString("result");
        } catch (JSONException e) {
            LogUtil.a("UIME_WeChatInteactors", "isUserBinded JSONException ! exception is ", e.getMessage());
        }
        LogUtil.a("UIME_WeChatInteactors", "isUserBinded ans is ", str);
        String str2 = "BIND_WECHAT_RANK";
        KeyValDbManager.b(context).e("BIND_WECHAT_RANK", str);
        if (!TextUtils.isEmpty(this.c)) {
            str2 = "BIND_WECHAT_RANK" + bgv.e(this.c);
        }
        SharedPreferenceManager.c(Integer.toString(10000), str2, System.currentTimeMillis() + "_" + str);
        return str;
    }

    public String d(Context context) {
        if (context == null) {
            LogUtil.b("UIME_WeChatInteactors", "getUserBinded fail: context is null");
            return "";
        }
        this.c = LoginInit.getInstance(context).getAccountInfo(1011);
        this.g = LoginInit.getInstance(context).getAccountInfo(1008);
        if (TextUtils.isEmpty(this.f17008a) || f()) {
            LogUtil.b("UIME_WeChatInteactors", "getUserBinded GRSUtils.HEALTH_CLOUD_URL failed or !isBasicParamValid");
            return "";
        }
        try {
            this.i = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            LogUtil.b("UIME_WeChatInteactors", "getUserBinded getAppVersionName() Exception = ", e.getMessage());
        }
        return e(this.c);
    }

    /*  JADX ERROR: Type inference failed
        jadx.core.utils.exceptions.JadxOverflowException: Type inference error: updates count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:77)
        */
    private java.lang.String e(java.lang.String r14) {
        /*
            Method dump skipped, instructions count: 380
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.sbk.e(java.lang.String):java.lang.String");
    }

    private String d() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("source", 1);
            jSONObject.put("huid", this.c);
            LogUtil.c("UIME_WeChatInteactors", "huid=", this.c);
            jSONObject.put("token", this.g);
            jSONObject.put("tokenType", ThirdLoginDataStorageUtil.getTokenTypeValue());
            LogUtil.c("UIME_WeChatInteactors", "token=", this.g);
            jSONObject.put("appId", BaseApplication.getAppPackage());
            jSONObject.put("appType", 1);
            jSONObject.put("environment", 1);
            jSONObject.put("ts", System.currentTimeMillis());
            LogUtil.a("UIME_WeChatInteactors", "ts ", Long.valueOf(System.currentTimeMillis()));
        } catch (JSONException unused) {
            LogUtil.a("UIME_WeChatInteactors", "JSONException e");
        }
        return jSONObject.toString();
    }

    private String b(InputStreamReader inputStreamReader) {
        char[] cArr = new char[1024];
        StringBuilder sb = new StringBuilder();
        int i = 0;
        do {
            try {
                int read = inputStreamReader.read(cArr, 0, 1024);
                if (read == -1) {
                    break;
                }
                sb.append(cArr, 0, read);
                i += read;
            } catch (IOException unused) {
                LogUtil.b("UIME_WeChatInteactors", "inputStreamReader2String IOException");
            }
        } while (i <= 10485760);
        return sb.toString();
    }

    public static void d(final Context context, int i) {
        if (context instanceof Activity) {
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(context);
            builder.e(context.getString(R$string.IDS_hw_healt_data_share_wechat_tips_privacy_setting, context.getString(i))).czE_(context.getString(R$string.IDS_hw_open_pressure_auto_detection).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: sbk.8
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    context.startActivity(new Intent(context, (Class<?>) PrivacyCenterActivity.class));
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).czA_(context.getString(R$string.IDS_hw_show_cancel), new View.OnClickListener() { // from class: sbk.9
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            NoTitleCustomAlertDialog e = builder.e();
            Activity activity = (Activity) context;
            if (activity.isDestroyed() || activity.isFinishing()) {
                return;
            }
            e.show();
        }
    }

    public static void c(final Context context) {
        if (context == null) {
            LogUtil.b("UIME_WeChatInteactors", "jumpWechatHelp error: context is null");
        } else {
            GRSManager.a(context).e("domainTipsResDbankcdn", new GrsQueryCallback() { // from class: sbk.10
                @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                public void onCallBackSuccess(String str) {
                    if (TextUtils.isEmpty(str)) {
                        LogUtil.b("UIME_WeChatInteactors", "jumpWechatHelp error: url is empty");
                        return;
                    }
                    StringBuilder sb = new StringBuilder();
                    sb.append(str);
                    sb.append(LanguageUtil.m(context) ? "/SmartWear/WeChat/EMUI8.0/C001B001/zh-CN/index.html" : "/SmartWear/WeChat/EMUI8.0/C001B001/en-US/index.html");
                    String sb2 = sb.toString();
                    Intent intent = new Intent(context, (Class<?>) WebViewActivity.class);
                    intent.putExtra("url", sb2);
                    context.startActivity(intent);
                }

                @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                public void onCallBackFail(int i) {
                    LogUtil.b("UIME_WeChatInteactors", "grs get fail:", Integer.valueOf(i));
                }
            });
        }
    }

    public void g(Context context) {
        if (context == null) {
            LogUtil.b("UIME_WeChatInteactors", "showDeviceAlreadyBinded fail: context is null");
            return;
        }
        LogUtil.a("UIME_WeChatInteactors", "showDeviceAlreadyBinded enter");
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(context);
        builder.b(context.getString(R$string.IDS_hw_wechat_rank_show_common_title)).e(context.getString(R$string.IDS_hw_wechat_rank_bind_title_common_dialog_content_new)).cyS_(context.getString(R$string.IDS_hw_common_ui_dialog_cancel), new View.OnClickListener() { // from class: sbk.12
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("UIME_WeChatInteactors", "showDeviceAlreadyBinded setNegativeButton");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.cyV_(context.getString(R$string.IDS_thirdparty_wechat_qrcode_view_help).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: sbk.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("UIME_WeChatInteactors", "showDeviceAlreadyBinded setPositiveButton");
                sbk.c(sbk.this.d);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.a().show();
    }

    public void e() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: sbh
            @Override // java.lang.Runnable
            public final void run() {
                sbk.this.b();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (!mer.b(this.d).j() && "true".equals(i(this.d))) {
            LogUtil.a("UIME_WeChatInteactors", "checkWeChatAndAliBind done");
            SharedPreferenceManager.e(this.d, Integer.toString(10000), "wechat_red_dot_show", "true", new StorageParams());
            mer.b(BaseApplication.getContext()).e();
        }
    }

    public void c(final String str) {
        final String accountInfo = LoginInit.getInstance(this.d).getAccountInfo(1011);
        if (accountInfo == null || "".equals(accountInfo)) {
            LogUtil.c("UIME_WeChatInteactors", "handleMessage MESSAGE_ID_GETQRCODETICKET get unvalid userId");
            c();
        } else {
            LogUtil.c("UIME_WeChatInteactors", "MESSAGE_ID_GETQRCODETICKET userid is:" + accountInfo);
            kor.a().c(new IBaseResponseCallback() { // from class: sbk.5
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    int i2 = 0;
                    if (i == 0 && (obj instanceof List)) {
                        List list = (List) obj;
                        int i3 = 0;
                        while (true) {
                            if (i3 >= list.size()) {
                                break;
                            }
                            FitnessTotalData fitnessTotalData = (FitnessTotalData) list.get(i3);
                            if (fitnessTotalData.getSportType() == 221) {
                                i2 = fitnessTotalData.getSteps();
                                break;
                            }
                            i3++;
                        }
                    }
                    LogUtil.c("UIME_WeChatInteactors", "wechat_total_step = " + i2);
                    String str2 = str + "#" + accountInfo + "#" + i2;
                    LogUtil.c("UIME_WeChatInteactors", "MESSAGE_ID_GETQRCODETICKET jumpToHwPublic trdTicket = ", str2);
                    sbk.this.n.sendMessage(sbk.this.n.obtainMessage(a.z, str2));
                }
            });
        }
    }

    public void e(String str, String str2, int i) {
        if (!j().isWXAppInstalled()) {
            LogUtil.a("UIME_WeChatInteactors", "WeChat is not installed");
            nrh.b(this.d, R$string.IDS_plugin_socialshare_install_wechat);
            return;
        }
        nrh.b(this.d, R$string.IDS_hw_go_wechat_for_consultation);
        WXLaunchMiniProgram.Req req = new WXLaunchMiniProgram.Req();
        req.userName = str;
        req.path = str2;
        req.miniprogramType = i;
        j().sendReq(req);
    }
}
