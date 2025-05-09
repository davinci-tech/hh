package defpackage;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.TypefaceSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.common.util.VersionIsCloud;
import com.huawei.haf.dynamic.DynamicResourcesLoader;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.MainActivityHandlerMsg;
import com.huawei.health.R;
import com.huawei.health.interactor.MainInteractors;
import com.huawei.health.utils.MainInteractorsUtils;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcloudmodel.agreement.AgrConstant;
import com.huawei.hwcloudmodel.agreement.AgrHttp;
import com.huawei.hwcloudmodel.https.HttpResCallBack;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.login.ui.login.util.SharedPreferenceUtil;
import com.huawei.openalliance.ad.constant.Action;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.openalliance.ad.inter.HiAd;
import com.huawei.operation.utils.Constants;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.viewpager.HealthPagerAdapter;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import defpackage.dzn;
import health.compact.a.AuthorizationUtils;
import health.compact.a.CloudImpl;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.KeyValDbManager;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class dzn {
    private static int c;

    /* renamed from: a, reason: collision with root package name */
    private Handler f11912a;
    private Context d;
    private View f;
    private View g;
    private View h;
    private View i;
    private View j;
    private View k;
    private MainInteractorsUtils o = new MainInteractorsUtils();
    private boolean e = true;
    private boolean b = true;

    public dzn(Context context, Handler handler) {
        if (context == null) {
            this.d = BaseApplication.getContext();
        } else {
            this.d = context;
        }
        this.f11912a = handler;
        this.o.j(this.d);
        String b2 = SharedPreferenceManager.b(this.d, Integer.toString(10000), "hw_health_show_update_terms");
        if (!TextUtils.isEmpty(b2)) {
            SharedPreferenceManager.e(this.d, Integer.toString(10000), "hw_health_show_update_terms", "", (StorageParams) null);
            SharedPreferenceManager.e(this.d, Integer.toString(10005), "hw_health_terms_authorize_china", b2, (StorageParams) null);
            SharedPreferenceManager.e(this.d, Integer.toString(10005), "key_has_agreement_authorization_msg", "true", (StorageParams) null);
        }
        t();
        a(this.d);
    }

    public void n() {
        this.d = null;
        this.f11912a = null;
    }

    private void t() {
        String b2 = SharedPreferenceManager.b(this.d, Integer.toString(10000), "hw_health_show_update_ove_terms");
        if (TextUtils.isEmpty(b2)) {
            return;
        }
        SharedPreferenceManager.e(this.d, Integer.toString(10000), "hw_health_show_update_ove_terms", "", (StorageParams) null);
        if (Utils.l()) {
            SharedPreferenceManager.e(this.d, Integer.toString(10005), "hw_health_terms_authorize_no_cloud", b2, (StorageParams) null);
        } else if (CommonUtil.h(LoginInit.getInstance(this.d).getAccountInfo(1009)) == 7) {
            SharedPreferenceManager.e(this.d, Integer.toString(10005), "hw_health_terms_authorize_europe", b2, (StorageParams) null);
        } else if (CommonUtil.h(LoginInit.getInstance(this.d).getAccountInfo(1009)) == 5) {
            SharedPreferenceManager.e(this.d, Integer.toString(10005), "hw_health_terms_authorize_hong_kong", b2, (StorageParams) null);
        } else {
            if (CloudImpl.c(this.d).getSiteIdByCountry(SharedPreferenceManager.b(this.d, Integer.toString(PrebakedEffectId.RT_SNIPER_RIFLE), "select_country")) == 5) {
                SharedPreferenceManager.e(this.d, Integer.toString(10005), "hw_health_terms_authorize_hong_kong", b2, (StorageParams) null);
            } else {
                SharedPreferenceManager.e(this.d, Integer.toString(10005), "hw_health_terms_authorize_europe", b2, (StorageParams) null);
            }
        }
        SharedPreferenceManager.e(this.d, Integer.toString(10005), "key_has_agreement_authorization_msg", "true", (StorageParams) null);
    }

    private void a(Context context) {
        if (context == null) {
            return;
        }
        if (!"true".equals(SharedPreferenceManager.b(context, Integer.toString(10005), "key_has_agreement_authorization_msg")) && !sbc.i()) {
            VersionIsCloud.getIfAllowLoginByMcc(context);
            return;
        }
        if (TextUtils.isEmpty(SharedPreferenceManager.b(context, Integer.toString(PrebakedEffectId.RT_SNIPER_RIFLE), "select_country"))) {
            String accountInfo = LoginInit.getInstance(context).getAccountInfo(1010);
            if (TextUtils.isEmpty(accountInfo)) {
                VersionIsCloud.getIfAllowLoginByMcc(context);
            } else {
                SharedPreferenceManager.e(context, Integer.toString(PrebakedEffectId.RT_SNIPER_RIFLE), "select_country", accountInfo, (StorageParams) null);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        if (this.f11912a != null) {
            ReleaseLogUtil.e("R_PrivacyInteractors", "setStartPageVisible");
            Message obtain = Message.obtain(this.f11912a);
            obtain.what = MainActivityHandlerMsg.REFRESH_START_PAGE;
            obtain.arg1 = 0;
            obtain.sendToTarget();
        }
    }

    private void abx_(boolean z, MainInteractors mainInteractors, ViewStub viewStub) {
        View view = this.h;
        if (view != null) {
            view.setVisibility(0);
            return;
        }
        if (viewStub == null) {
            LogUtil.h("PrivacyInteractors", "setPrivacyStartPageVisibility ViewStub is loaded fail.");
            return;
        }
        View inflate = viewStub.inflate();
        this.h = inflate;
        inflate.setVisibility(0);
        LogUtil.a("PrivacyInteractors", "init start page ok.");
        this.h.setPadding(0, nsn.r(this.d), 0, 0);
        if (nsn.r()) {
            HealthTextView healthTextView = (HealthTextView) this.h.findViewById(R.id.text2);
            HealthTextView healthTextView2 = (HealthTextView) this.h.findViewById(R.id.text3);
            healthTextView.setTextSize(1, 60.0f);
            healthTextView2.setTextSize(1, 28.0f);
        }
        abh_(this.h);
        ImageView imageView = (ImageView) this.h.findViewById(R.id.icon1);
        if (CommonUtil.as() && CommonUtil.y(this.d)) {
            imageView.setImageResource(R.mipmap._2131820755_res_0x7f1100d3);
            LogUtil.a("PrivacyInteractors", "init start page icon ok.");
        } else if (CommonUtil.bu()) {
            Bitmap abD_ = abD_("healthbasic", "healthlogo_ic_about_app_icon_demo.webp");
            if (abD_ == null) {
                imageView.setImageResource(R.mipmap._2131820756_res_0x7f1100d4);
            } else {
                imageView.setImageBitmap(abD_);
            }
        } else {
            LogUtil.a("PrivacyInteractors", "No need to change start page icon");
        }
        abg_(this.h, z, mainInteractors);
    }

    private void abv_(boolean z, MainInteractors mainInteractors, ViewStub viewStub) {
        View view = this.g;
        if (view != null) {
            view.setVisibility(0);
            return;
        }
        if (viewStub == null) {
            LogUtil.h("PrivacyInteractors", "setPrivacyStartPageVisibility ViewStub is loaded fail.");
            return;
        }
        View inflate = viewStub.inflate();
        this.g = inflate;
        inflate.setVisibility(0);
        LogUtil.a("PrivacyInteractors", "init start page ok.");
        this.g.setPadding(0, nsn.r(this.d), 0, 0);
        if (nsn.r()) {
            HealthTextView healthTextView = (HealthTextView) this.g.findViewById(R.id.text2);
            HealthTextView healthTextView2 = (HealthTextView) this.g.findViewById(R.id.text3);
            healthTextView.setTextSize(1, 60.0f);
            healthTextView2.setTextSize(1, 28.0f);
        }
        abh_(this.g);
        ImageView imageView = (ImageView) this.g.findViewById(R.id.icon1);
        if (CommonUtil.as() && CommonUtil.y(this.d)) {
            imageView.setImageResource(R.mipmap._2131820755_res_0x7f1100d3);
            LogUtil.a("PrivacyInteractors", "init start page icon ok.");
        } else if (CommonUtil.bu()) {
            Bitmap abD_ = abD_("healthbasic", "healthlogo_ic_about_app_icon_demo.webp");
            if (abD_ == null) {
                imageView.setImageResource(R.mipmap._2131820756_res_0x7f1100d4);
            } else {
                imageView.setImageBitmap(abD_);
            }
        } else {
            LogUtil.a("PrivacyInteractors", "No need to change start page icon");
        }
        abg_(this.g, z, mainInteractors);
    }

    public void abK_(Context context, boolean z, ViewStub viewStub) {
        View view;
        View view2 = this.h;
        if (view2 != null && this.g != null) {
            view2.setVisibility(z ? 8 : 0);
            this.g.setVisibility(z ? 0 : 8);
            return;
        }
        if (z && view2 != null) {
            view2.setVisibility(8);
        } else if (!z && (view = this.g) != null) {
            view.setVisibility(8);
        } else {
            LogUtil.h("PrivacyInteractors", "setPrivacyBetaUserAgreementVisibility do not need change");
        }
        if (!z) {
            abJ_(context, z, viewStub, this.h);
        } else {
            abJ_(context, z, viewStub, this.g);
        }
    }

    public void abJ_(Context context, boolean z, ViewStub viewStub, View view) {
        if (view != null) {
            view.setVisibility(0);
            return;
        }
        if (viewStub == null) {
            LogUtil.h("PrivacyInteractors", "setPrivacyStartPageVisibility ViewStub is loaded fail.");
            return;
        }
        View inflate = viewStub.inflate();
        inflate.setVisibility(0);
        inflate.setPadding(0, nsn.r(this.d), 0, 0);
        boolean c2 = sbc.c();
        abd_(inflate, c2);
        if (!c2) {
            abe_(context, inflate, z);
        }
        abf_(context, inflate, c2);
    }

    private void abe_(Context context, View view, boolean z) {
        TextView textView = (TextView) view.findViewById(R.id.beta_user_agreement_title);
        textView.setText(R.string._2130850351_res_0x7f02322f);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        view.findViewById(R.id.textView_content).setVisibility(0);
        view.findViewById(R.id.textView_content2).setVisibility(0);
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010);
        int c2 = nsn.c(context, 8.0f);
        if (CommonUtil.c(R.array._2130968671_res_0x7f04005f, accountInfo)) {
            layoutParams.setMargins(0, c2, 0, c2 / 2);
            b((HealthButton) view.findViewById(R.id.button2));
        } else {
            view.findViewById(R.id.textView_content1).setVisibility(0);
            layoutParams.setMargins(0, c2 * 2, 0, c2 / 2);
        }
        textView.setLayoutParams(layoutParams);
        if (z) {
            return;
        }
        ImageView imageView = (ImageView) view.findViewById(R.id.icon1);
        if (imageView.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) imageView.getLayoutParams();
            layoutParams2.setMargins(0, nsn.c(context, sbc.c() ? 112.0f : 80.0f), 0, 0);
            imageView.setLayoutParams(layoutParams2);
        }
    }

    private void abf_(final Context context, final View view, boolean z) {
        HealthButton healthButton = (HealthButton) view.findViewById(R.id.button1);
        if (!z) {
            healthButton.setText(R.string._2130847902_res_0x7f02289e);
        }
        HealthButton healthButton2 = (HealthButton) view.findViewById(R.id.button2);
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.interactor.PrivacyInteractors$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                dzn.abm_(context, view2);
            }
        });
        healthButton2.setOnClickListener(new View.OnClickListener() { // from class: dzw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                dzn.this.abI_(view, view2);
            }
        });
        aaW_(view, 0);
    }

    public static /* synthetic */ void abm_(Context context, View view) {
        LogUtil.a("PrivacyInteractors", "initBetaUserAgreementButton cancel");
        Intent intent = new Intent();
        intent.setAction("com.huawei.commonui.CLEAN_ACTIVITY");
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* synthetic */ void abI_(View view, View view2) {
        if (nsn.a(500)) {
            LogUtil.a("PrivacyInteractors", "onClick isFastClick true");
            ViewClickInstrumentation.clickOnView(view2);
            return;
        }
        StorageParams storageParams = new StorageParams();
        if (sbc.f()) {
            SharedPreferenceManager.e(this.d, Integer.toString(10000), "health_oversea_beta_user_agree", "1", storageParams);
        } else {
            SharedPreferenceManager.e(this.d, Integer.toString(10000), "health_beta_user_agree", "1", storageParams);
        }
        aaW_(view, 8);
        this.h = null;
        this.g = null;
        this.f11912a.sendEmptyMessageDelayed(MainActivityHandlerMsg.AFTER_LAUNCH_CHECK, 500L);
        ViewClickInstrumentation.clickOnView(view2);
    }

    private void abd_(View view, boolean z) {
        String string;
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.textView);
        healthTextView.setMovementMethod(LinkMovementMethod.getInstance());
        healthTextView.setHighlightColor(0);
        String string2 = this.d.getString(z ? R.string._2130837581_res_0x7f02004d : R.string._2130843317_res_0x7f0216b5);
        String string3 = this.d.getString(R.string._2130850354_res_0x7f023232);
        if (z) {
            string = this.d.getString(R.string._2130837579_res_0x7f02004b, string2);
        } else {
            string = this.d.getString(R.string._2130850353_res_0x7f023231, string3, string2);
        }
        SpannableString spannableString = new SpannableString(string);
        if (!z) {
            sds.dWm_(spannableString, string3);
            HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.textView_content2);
            SpannableString spannableString2 = new SpannableString(this.d.getString(R.string._2130850355_res_0x7f023233));
            sds.dWm_(spannableString2, this.d.getString(R.string._2130850355_res_0x7f023233));
            healthTextView2.setText(spannableString2);
        }
        if (nsn.r()) {
            healthTextView.setTextSize(1, 26.0f);
        }
        abn_(spannableString, string2, z ? "BetaUserAgreement" : "HealthPrivacy");
        healthTextView.setText(spannableString);
    }

    public void d(final Context context) {
        LogUtil.a("PrivacyInteractors", "signAgreement");
        if (!sbc.c()) {
            LogUtil.a("PrivacyInteractors", "signAgreement isChinaBeta = false");
        } else if (byh.d()) {
            LogUtil.a("PrivacyInteractors", "signAgreement isSignBetaPrivacy = true");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: dzp
                @Override // java.lang.Runnable
                public final void run() {
                    dzn.this.e(context);
                }
            });
        }
    }

    /* synthetic */ void e(Context context) {
        LoginInit loginInit = LoginInit.getInstance(this.d);
        String url = GRSManager.a(context).getUrl("agreementservice");
        String accountInfo = loginInit.getAccountInfo(1015);
        String accountInfo2 = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010);
        String country = BaseApplication.getContext().getResources().getConfiguration().locale.getCountry();
        String language = BaseApplication.getContext().getResources().getConfiguration().locale.getLanguage();
        AgrHttp agrHttp = new AgrHttp();
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(AgrConstant.BETA_USER_AGREEMENT_CODE));
        LogUtil.c("PrivacyInteractors", "signAgreement: url " + url, " token ", accountInfo);
        agrHttp.signHttpReq(accountInfo, url, true, arrayList, accountInfo2, language + "_" + country, new HttpResCallBack() { // from class: dzv
            @Override // com.huawei.hwcloudmodel.https.HttpResCallBack
            public final void onFinished(int i, String str) {
                dzn.a(i, str);
            }
        });
    }

    static /* synthetic */ void a(int i, String str) {
        ReleaseLogUtil.e("PrivacyInteractors", "signAgreement: resultCode " + i, " result ", str);
        e(true);
    }

    public void abC_(final Handler handler) {
        long n = CommonUtil.n(this.d, SharedPreferenceManager.b(this.d, Integer.toString(10000), "beta_agr_last_query_time"));
        float currentTimeMillis = (System.currentTimeMillis() - n) / 60000.0f;
        if (n != 0 && currentTimeMillis <= 1440.0f) {
            LogUtil.a("PrivacyInteractors", "agrQueryBeta lastQueryTime:", Long.valueOf(n));
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: dzs
                @Override // java.lang.Runnable
                public final void run() {
                    dzn.abl_(handler);
                }
            });
        }
    }

    static /* synthetic */ void abl_(Handler handler) {
        LogUtil.a("PrivacyInteractors", "agrQueryBeta MainActivty_signAgrHttp aToken");
        LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
        String url = GRSManager.a(BaseApplication.getContext()).getUrl("agreementservice");
        new AgrHttp().queryHttpBetaReq(loginInit.getAccountInfo(1015), url, new b(handler));
    }

    static class b implements HttpResCallBack {
        private final WeakReference<Handler> d;

        b(Handler handler) {
            this.d = new WeakReference<>(handler);
        }

        @Override // com.huawei.hwcloudmodel.https.HttpResCallBack
        public void onFinished(int i, String str) {
            Handler handler = this.d.get();
            if (handler == null) {
                LogUtil.a("PrivacyInteractors", "agrQueryBeta handler == null");
            } else if (i == 200) {
                dzn.abc_(handler, str);
                LogUtil.a("PrivacyInteractors", "agrQueryBeta response data is ", str);
            }
        }
    }

    public static void abc_(Handler handler, String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("PrivacyInteractors", "handleAgrBetaResult resultBundle == null");
            return;
        }
        LogUtil.a("PrivacyInteractors", "enter_handleAgrBetaResult");
        if (!sds.c(str)) {
            LogUtil.h("PrivacyInteractors", "handleAgrBetaResult privacy query failed");
            return;
        }
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), "beta_agr_last_query_time", Long.toString(System.currentTimeMillis()), (StorageParams) null);
        try {
            JSONArray jSONArray = new JSONObject(str).getJSONArray(Constants.VMALL_SIGN_INFO);
            LogUtil.a("PrivacyInteractors", "handleAgrBetaResult agr_query_sign_response JSONArray : ", Integer.valueOf(jSONArray.length()));
            ArrayList arrayList = new ArrayList();
            if (jSONArray.length() > 0) {
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject jSONObject = jSONArray.getJSONObject(i);
                    if (jSONObject.optBoolean("needSign")) {
                        int optInt = jSONObject.optInt(Constants.VMALL_ARG_TYPE);
                        LogUtil.a("PrivacyInteractors", "handleAgrBetaResult agr_query_sign_response agrType = ", Integer.valueOf(optInt));
                        if (optInt == 10000050) {
                            arrayList.add(Integer.valueOf(optInt));
                        }
                    }
                }
            } else {
                arrayList.add(Integer.valueOf(AgrConstant.BETA_USER_AGREEMENT_CODE));
            }
            LogUtil.a("PrivacyInteractors", "handleAgrBetaResult QueryAgrResInfo list size ", Integer.valueOf(arrayList.size()));
            if (arrayList.size() > 0) {
                e(false);
                handler.sendEmptyMessage(MainActivityHandlerMsg.BETA_AGREEMENTS_DIALOG);
            }
        } catch (JSONException e) {
            LogUtil.b("PrivacyInteractors", "handleAgrBetaResult exception ", LogAnonymous.b((Throwable) e));
        }
    }

    private static void e(boolean z) {
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), "beta_agr_if_agree_authorize", z ? "1" : "0", new StorageParams());
    }

    public void abM_(boolean z, MainInteractors mainInteractors, ViewStub viewStub, boolean z2) {
        View view;
        ReleaseLogUtil.e("R_PrivacyInteractors", "show privacy dialog in China");
        View view2 = this.h;
        if (view2 != null && this.g != null) {
            view2.setVisibility(z2 ? 8 : 0);
            this.g.setVisibility(z2 ? 0 : 8);
            return;
        }
        if (z2 && view2 != null) {
            view2.setVisibility(8);
        } else if (!z2 && (view = this.g) != null) {
            view.setVisibility(8);
        } else {
            LogUtil.h("PrivacyInteractors", "do not need change");
        }
        if (!z2) {
            abx_(z, mainInteractors, viewStub);
        } else {
            abv_(z, mainInteractors, viewStub);
        }
    }

    public void abN_(ViewStub viewStub, MainInteractors mainInteractors, boolean z) {
        View view;
        ReleaseLogUtil.e("R_PrivacyInteractors", "show privacy dialog in HongKong or Russia");
        View view2 = this.j;
        if (view2 != null && this.f != null) {
            view2.setVisibility(z ? 8 : 0);
            this.f.setVisibility(z ? 0 : 8);
            return;
        }
        if (z && view2 != null) {
            view2.setVisibility(8);
        } else if (!z && (view = this.f) != null) {
            view.setVisibility(8);
        } else {
            LogUtil.h("PrivacyInteractors", "do not need change");
        }
        if (!z) {
            abz_(viewStub, mainInteractors);
        } else {
            abA_(viewStub, mainInteractors);
        }
    }

    private void abz_(ViewStub viewStub, MainInteractors mainInteractors) {
        View view = this.j;
        if (view != null) {
            view.setVisibility(0);
        } else {
            if (viewStub == null) {
                LogUtil.h("PrivacyInteractors", "showHongKongPrivacyUi ViewStub is null");
                return;
            }
            LogUtil.a("PrivacyInteractors", "init start page ok.");
            View inflate = viewStub.inflate();
            this.j = inflate;
            inflate.setVisibility(0);
            this.j.setPadding(0, nsn.r(this.d), 0, 0);
            abi_(this.j);
            abg_(this.j, false, mainInteractors);
        }
        if (CommonUtil.cd()) {
            LogUtil.a("PrivacyInteractors", "showHongKongPrivacyUi isSupportPrivacyCenter = true");
            return;
        }
        HealthTextView healthTextView = (HealthTextView) this.j.findViewById(R.id.hw_health_service_item_six);
        if (c == 8) {
            healthTextView.setText(this.d.getString(R.string._2130841285_res_0x7f020ec5, this.d.getResources().getString(R.string._2130841288_res_0x7f020ec8)));
        } else {
            healthTextView.setText(this.d.getString(R.string._2130841285_res_0x7f020ec5, this.d.getResources().getString(R.string._2130841287_res_0x7f020ec7)));
        }
        if (nsn.r()) {
            healthTextView.setTextSize(1, 26.0f);
        }
    }

    private void abA_(ViewStub viewStub, MainInteractors mainInteractors) {
        View view = this.f;
        if (view != null) {
            view.setVisibility(0);
        } else {
            if (viewStub == null) {
                LogUtil.h("PrivacyInteractors", "showHongKongPrivacyUi ViewStub is null");
                return;
            }
            LogUtil.a("PrivacyInteractors", "init start page ok.");
            View inflate = viewStub.inflate();
            this.f = inflate;
            inflate.setVisibility(0);
            this.f.setPadding(0, nsn.r(this.d), 0, 0);
            abi_(this.f);
            abg_(this.f, false, mainInteractors);
        }
        if (CommonUtil.cd()) {
            LogUtil.a("PrivacyInteractors", "showHongKongPrivacyUi isSupportPrivacyCenter = true");
            return;
        }
        HealthTextView healthTextView = (HealthTextView) this.f.findViewById(R.id.hw_health_service_item_six);
        if (c == 8) {
            healthTextView.setText(this.d.getString(R.string._2130841285_res_0x7f020ec5, this.d.getResources().getString(R.string._2130841288_res_0x7f020ec8)));
        } else {
            healthTextView.setText(this.d.getString(R.string._2130841285_res_0x7f020ec5, this.d.getResources().getString(R.string._2130841287_res_0x7f020ec7)));
        }
        if (nsn.r()) {
            healthTextView.setTextSize(1, 26.0f);
        }
    }

    public void abL_(ViewStub viewStub, MainInteractors mainInteractors, boolean z) {
        View view;
        ReleaseLogUtil.e("R_PrivacyInteractors", "show privacy dialog in Europe or no Cloud");
        View view2 = this.i;
        if (view2 != null && this.k != null) {
            view2.setVisibility(z ? 8 : 0);
            this.k.setVisibility(z ? 0 : 8);
            return;
        }
        if (z && view2 != null) {
            view2.setVisibility(8);
        } else if (!z && (view = this.k) != null) {
            view.setVisibility(8);
        } else {
            LogUtil.h("PrivacyInteractors", "do not need change");
        }
        if (!z) {
            abw_(viewStub, mainInteractors);
        } else {
            abu_(viewStub, mainInteractors);
        }
    }

    private void abw_(ViewStub viewStub, MainInteractors mainInteractors) {
        View view = this.i;
        if (view != null) {
            view.setVisibility(0);
        } else {
            if (viewStub == null) {
                LogUtil.h("PrivacyInteractors", "setPrivacyStartPageOverSeaVisibility ViewStub is loaded fail.");
                return;
            }
            View inflate = viewStub.inflate();
            this.i = inflate;
            inflate.setVisibility(0);
            this.i.setPadding(0, nsn.r(this.d), 0, 0);
        }
        if (nsn.r()) {
            ((HealthTextView) this.i.findViewById(R.id.text2)).setTextSize(1, 60.0f);
        }
        HealthViewPager healthViewPager = (HealthViewPager) this.i.findViewById(R.id.viewpager);
        healthViewPager.setAdapter(aaY_(mainInteractors, healthViewPager, this.i));
    }

    private void abu_(ViewStub viewStub, MainInteractors mainInteractors) {
        View view = this.k;
        if (view != null) {
            view.setVisibility(0);
        } else {
            if (viewStub == null) {
                LogUtil.h("PrivacyInteractors", "setPrivacyStartPageOverSeaVisibility ViewStub is loaded fail.");
                return;
            }
            View inflate = viewStub.inflate();
            this.k = inflate;
            inflate.setVisibility(0);
            this.k.setPadding(0, nsn.r(this.d), 0, 0);
        }
        HealthViewPager healthViewPager = (HealthViewPager) this.k.findViewById(R.id.viewpager);
        healthViewPager.setAdapter(aaZ_(mainInteractors, healthViewPager, this.k));
    }

    private void abg_(final View view, final boolean z, final MainInteractors mainInteractors) {
        HealthButton healthButton = (HealthButton) view.findViewById(R.id.button1);
        healthButton.setText(this.d.getString(R.string._2130837555_res_0x7f020033).toUpperCase(Locale.ENGLISH));
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: dzn.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SharedPreferenceManager.e(dzn.this.d, Integer.toString(10000), "agr_if_privacy_authorize", "0", new StorageParams());
                if (!dzn.d()) {
                    dzn.this.o();
                    ViewClickInstrumentation.clickOnView(view2);
                } else {
                    dzn.this.aby_(z, view, mainInteractors);
                    ViewClickInstrumentation.clickOnView(view2);
                }
            }
        });
        HealthButton healthButton2 = (HealthButton) view.findViewById(R.id.button2);
        healthButton2.setText(this.d.getString(R.string._2130839489_res_0x7f0207c1).toUpperCase(Locale.ENGLISH));
        healthButton2.setOnClickListener(new View.OnClickListener() { // from class: dzn.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                if (!nsn.a(500)) {
                    SharedPreferenceManager.e(dzn.this.d, Integer.toString(10000), "agr_if_privacy_authorize", "1", new StorageParams());
                    dzn.this.w();
                    dzn.this.aaV_(z, view, mainInteractors);
                    dzn.this.p();
                    ViewClickInstrumentation.clickOnView(view2);
                    return;
                }
                LogUtil.a("PrivacyInteractors", "onClick isFastClick true");
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        a(healthButton);
        a(healthButton2);
    }

    private void aaW_(final View view, final int i) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(i == 0 ? 0.0f : 1.0f, i != 0 ? 0.0f : 1.0f);
        alphaAnimation.setDuration(500L);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: dzn.4
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(i);
            }
        });
        if (view != null) {
            view.startAnimation(alphaAnimation);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        LogUtil.a("PrivacyInteractors", "enter sendAgreementMessage");
        Handler handler = this.f11912a;
        if (handler != null) {
            Message obtain = Message.obtain(handler);
            obtain.what = MainActivityHandlerMsg.ON_AGREEMENT_GRANTED;
            obtain.sendToTarget();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aby_(final boolean z, final View view, final MainInteractors mainInteractors) {
        CustomTextAlertDialog.Builder cyR_ = new CustomTextAlertDialog.Builder(this.d).b(this.d.getString(R.string._2130837570_res_0x7f020042)).cyQ_(aba_()).cyU_(R.string.IDS_device_release_user_profile_agree_and_continue, new View.OnClickListener() { // from class: dzn.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                SharedPreferenceManager.e(Integer.toString(PrebakedEffectId.RT_FLY), "is_base_service_model", true);
                KeyValDbManager.b(dzn.this.d).e("key_wether_to_auth", String.valueOf(true));
                AuthorizationUtils.d();
                SharedPreferenceManager.e(dzn.this.d, Integer.toString(10000), "key_wether_to_auth", String.valueOf(true), (StorageParams) null);
                dzn.this.r();
                dzn.this.q();
                LoginInit.getInstance(dzn.this.d).setAccountInfo(1009, Integer.toString(CloudImpl.c(dzn.this.d).getSiteIdByCountry(SharedPreferenceManager.b(dzn.this.d, Integer.toString(PrebakedEffectId.RT_SNIPER_RIFLE), "select_country"))), null);
                dzn.this.aaV_(z, view, mainInteractors);
                ViewClickInstrumentation.clickOnView(view2);
            }
        }).cyR_(R.string._2130837574_res_0x7f020046, new View.OnClickListener() { // from class: dzn.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                dzn.this.o();
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        CustomTextAlertDialog a2 = cyR_.a();
        cyR_.e().setBackground(this.d.getDrawable(R.drawable.button_background_emphasize));
        cyR_.e().setTextColor(this.d.getColor(R.color._2131299386_res_0x7f090c3a));
        a2.setCancelable(false);
        a2.show();
    }

    private SpannableString aba_() {
        String string = this.d.getResources().getString(R.string._2130837571_res_0x7f020043);
        String string2 = this.d.getResources().getString(R.string._2130837554_res_0x7f020032);
        String string3 = this.d.getResources().getString(R.string._2130837572_res_0x7f020044);
        String string4 = this.d.getString(R.string._2130837573_res_0x7f020045, string, string2, string3);
        SpannableString spannableString = new SpannableString(string4);
        sds.dWm_(spannableString, string);
        int indexOf = string4.indexOf(string2);
        int length = string2.length() + indexOf;
        spannableString.setSpan(new rvq(this.d, "HealthUserAgreement", false), indexOf, length, 33);
        spannableString.setSpan(new TypefaceSpan(com.huawei.openalliance.ad.constant.Constants.FONT), indexOf, length, 33);
        int indexOf2 = string4.indexOf(string3);
        int length2 = string3.length() + indexOf2;
        spannableString.setSpan(new rvq(this.d, "HealthPrivacy", false), indexOf2, length2, 33);
        spannableString.setSpan(new TypefaceSpan(com.huawei.openalliance.ad.constant.Constants.FONT), indexOf2, length2, 33);
        return spannableString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        Intent intent = new Intent(this.d, (Class<?>) goj.class);
        intent.setAction(Action.ACTION_HW_ACCOUNT_LOGOUT);
        intent.putExtra(JsbMapKeyNames.H5_USER_ID, LoginInit.getInstance(this.d).getAccountInfo(1011));
        goj.a().aQB_(this.d, intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void aaV_(boolean z, final View view, final MainInteractors mainInteractors) {
        if (z) {
            LogUtil.a("PrivacyInteractors", "isJustShowUi true");
            view.setVisibility(8);
            return;
        }
        if ((view == this.h || view == this.g) && l()) {
            LogUtil.a("PrivacyInteractors", "isNeedShowPermissionLayout true");
            view.findViewById(R.id.layout_privacy_notice).setVisibility(8);
            view.findViewById(R.id.checkbox).setVisibility(8);
            final View findViewById = view.findViewById(R.id.layout_third_part_permission);
            findViewById.setVisibility(0);
            findViewById.findViewById(R.id.ok_button).setOnClickListener(new View.OnClickListener() { // from class: dzn.7
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    LogUtil.a("PrivacyInteractors", "thirdPartPermission onClick");
                    findViewById.setVisibility(8);
                    view.setVisibility(8);
                    view.findViewById(R.id.layout_privacy_notice).setVisibility(0);
                    view.findViewById(R.id.checkbox).setVisibility(0);
                    dzn.this.s();
                    mainInteractors.j();
                    ViewClickInstrumentation.clickOnView(view2);
                }
            });
            e(mainInteractors);
            mainInteractors.b(false);
            return;
        }
        LogUtil.a("PrivacyInteractors", "isNeedShowPermissionLayout false");
        view.setVisibility(8);
        s();
        e(mainInteractors);
        mainInteractors.b(true);
    }

    private boolean l() {
        if (d() && !CommonUtil.av()) {
            return (CommonUtil.au() && CommonUtil.ca()) ? false : true;
        }
        return false;
    }

    private void e(MainInteractors mainInteractors) {
        this.o.g(this.d);
        this.o.x();
        if (Utils.o()) {
            return;
        }
        HiAd.getInstance(this.d).enableUserInfo(true);
    }

    private HealthPagerAdapter aaY_(MainInteractors mainInteractors, HealthViewPager healthViewPager, View view) {
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.privacy_statement_europe_page_one, (ViewGroup) null);
        View inflate2 = LayoutInflater.from(this.d).inflate(R.layout.privacy_statement_europe_page_two, (ViewGroup) null);
        if (nsn.r()) {
            HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.text_one);
            HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.text_two);
            HealthTextView healthTextView3 = (HealthTextView) inflate.findViewById(R.id.text_three);
            HealthTextView healthTextView4 = (HealthTextView) inflate.findViewById(R.id.text_four);
            healthTextView.setTextSize(1, 26.0f);
            healthTextView2.setTextSize(1, 26.0f);
            healthTextView3.setTextSize(1, 26.0f);
            healthTextView4.setTextSize(1, 26.0f);
            ((HealthTextView) inflate.findViewById(R.id.loading_text)).setTextSize(1, 26.0f);
            HealthTextView healthTextView5 = (HealthTextView) inflate.findViewById(R.id.map_text);
            HealthTextView healthTextView6 = (HealthTextView) inflate.findViewById(R.id.weather_text);
            HealthTextView healthTextView7 = (HealthTextView) inflate.findViewById(R.id.cloud_text);
            HealthTextView healthTextView8 = (HealthTextView) inflate.findViewById(R.id.exit_text);
            healthTextView5.setTextSize(1, 26.0f);
            healthTextView6.setTextSize(1, 26.0f);
            healthTextView7.setTextSize(1, 26.0f);
            healthTextView8.setTextSize(1, 26.0f);
        }
        ImageView imageView = (ImageView) inflate.findViewById(R.id.icon_privacy_center);
        if (!CommonUtil.cd()) {
            imageView.setVisibility(8);
        }
        abj_(healthViewPager, inflate);
        abk_(mainInteractors, view, inflate2);
        aaX_(inflate, inflate2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(inflate);
        arrayList.add(inflate2);
        return new rvl(arrayList);
    }

    private HealthPagerAdapter aaZ_(MainInteractors mainInteractors, HealthViewPager healthViewPager, View view) {
        View inflate = LayoutInflater.from(this.d).inflate(R.layout.privacy_statement_europe_page_one_half_screen, (ViewGroup) null);
        View inflate2 = LayoutInflater.from(this.d).inflate(R.layout.privacy_statement_europe_page_two_half_screen, (ViewGroup) null);
        if (nsn.r()) {
            ((HealthTextView) inflate.findViewById(R.id.text2)).setTextSize(1, 60.0f);
            ((HealthTextView) inflate2.findViewById(R.id.text2)).setTextSize(1, 60.0f);
            HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.text_one);
            HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.text_two);
            HealthTextView healthTextView3 = (HealthTextView) inflate.findViewById(R.id.text_three);
            HealthTextView healthTextView4 = (HealthTextView) inflate.findViewById(R.id.text_four);
            healthTextView.setTextSize(1, 26.0f);
            healthTextView2.setTextSize(1, 26.0f);
            healthTextView3.setTextSize(1, 26.0f);
            healthTextView4.setTextSize(1, 26.0f);
            ((HealthTextView) inflate.findViewById(R.id.loading_text)).setTextSize(1, 26.0f);
            HealthTextView healthTextView5 = (HealthTextView) inflate.findViewById(R.id.map_text);
            HealthTextView healthTextView6 = (HealthTextView) inflate.findViewById(R.id.weather_text);
            HealthTextView healthTextView7 = (HealthTextView) inflate.findViewById(R.id.cloud_text);
            HealthTextView healthTextView8 = (HealthTextView) inflate.findViewById(R.id.exit_text);
            healthTextView5.setTextSize(1, 26.0f);
            healthTextView6.setTextSize(1, 26.0f);
            healthTextView7.setTextSize(1, 26.0f);
            healthTextView8.setTextSize(1, 26.0f);
        }
        ImageView imageView = (ImageView) inflate.findViewById(R.id.icon_privacy_center);
        if (!CommonUtil.cd()) {
            imageView.setVisibility(8);
        }
        abj_(healthViewPager, inflate);
        abk_(mainInteractors, view, inflate2);
        aaX_(inflate, inflate2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(inflate);
        arrayList.add(inflate2);
        return new rvl(arrayList);
    }

    private void abj_(final HealthViewPager healthViewPager, View view) {
        HealthButton healthButton = (HealthButton) view.findViewById(R.id.hw_health_privacy_dialog_cancel);
        healthButton.setText(this.d.getString(R.string._2130841130_res_0x7f020e2a).toUpperCase(Locale.getDefault()));
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: dzn.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                dzn.this.o();
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        HealthButton healthButton2 = (HealthButton) view.findViewById(R.id.hw_health_privacy_dialog_next);
        healthButton2.setText(this.d.getString(R.string._2130841128_res_0x7f020e28).toUpperCase(Locale.getDefault()));
        healthButton2.setOnClickListener(new View.OnClickListener() { // from class: dzn.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                healthViewPager.setCurrentItem(1);
                ViewClickInstrumentation.clickOnView(view2);
            }
        });
        b(healthButton2);
        a(healthButton);
        a(healthButton2);
    }

    private void abk_(final MainInteractors mainInteractors, final View view, View view2) {
        HealthButton healthButton = (HealthButton) view2.findViewById(R.id.hw_health_agreement_dialog_disagree);
        healthButton.setText(this.d.getString(R.string._2130841129_res_0x7f020e29).toUpperCase(Locale.getDefault()));
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: dzn.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view3) {
                dzn.this.o();
                ViewClickInstrumentation.clickOnView(view3);
            }
        });
        HealthButton healthButton2 = (HealthButton) view2.findViewById(R.id.hw_health_agreement_dialog_agree);
        healthButton2.setText(this.d.getString(R.string._2130839489_res_0x7f0207c1).toUpperCase(Locale.getDefault()));
        healthButton2.setOnClickListener(new View.OnClickListener() { // from class: dzn.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view3) {
                view.setVisibility(8);
                dzn.this.s();
                dzn.this.w();
                dzn.this.o.g(dzn.this.d);
                dzn.this.o.x();
                mainInteractors.b(true);
                ViewClickInstrumentation.clickOnView(view3);
            }
        });
        b(healthButton2);
        a(healthButton);
        a(healthButton2);
    }

    private void b(HealthButton healthButton) {
        if (Utils.i()) {
            healthButton.setBackgroundResource(R.drawable._2131428850_res_0x7f0b05f2);
            healthButton.setTextColor(this.d.getColor(R.color._2131296927_res_0x7f09029f));
        }
    }

    private void a(HealthButton healthButton) {
        if (healthButton == null) {
            LogUtil.h("PrivacyInteractors", "setAutoText healthButton is null");
        } else {
            healthButton.setSingleLine();
            healthButton.setAutoTextInfo(10, 1, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        if (this.d == null) {
            return;
        }
        ReleaseLogUtil.e("R_PrivacyInteractors", "cancelTermsAuth, key_wether_to_auth is false");
        KeyValDbManager.b(this.d).e("key_wether_to_auth", String.valueOf(false));
        SharedPreferenceManager.e(this.d, Integer.toString(10000), "key_wether_to_auth", String.valueOf(false), (StorageParams) null);
        AuthorizationUtils.d();
        SharedPreferenceManager.e(Integer.toString(10000), "is_agr_change_user", false);
        knx.b(false);
        MainInteractors.d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w() {
        if (this.d == null) {
            return;
        }
        ReleaseLogUtil.e("R_PrivacyInteractors", "updateTermsAuthVersion, key_wether_to_auth is true");
        SharedPreferenceManager.e(this.d, Integer.toString(10005), "key_has_agreement_authorization_msg", "true", (StorageParams) null);
        KeyValDbManager.b(this.d).e("key_wether_to_auth", String.valueOf(true));
        SharedPreferenceManager.e(this.d, Integer.toString(10000), "key_wether_to_auth", String.valueOf(true), (StorageParams) null);
        r();
        SharedPreferenceManager.e(this.d, Integer.toString(10000), "agree_app_version_name", CommonUtil.e(this.d), (StorageParams) null);
        SharedPreferenceManager.e(this.d, Integer.toString(10000), "agree_date", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), (StorageParams) null);
        SharedPreferenceManager.e(Integer.toString(10000), "is_agr_change_user", false);
        AuthorizationUtils.d();
        knx.b(!Utils.o());
        if (Utils.l()) {
            a(0);
            return;
        }
        a(c);
        b(true);
        if (c == 1) {
            LogUtil.a("PrivacyInteractors", "privacy recommend is ", Boolean.valueOf(this.e), " ad recommend is ", Boolean.valueOf(this.b));
            SharedPreferenceManager.e(Integer.toString(10000), "privacy_personalized_recommend", this.e);
            SharedPreferenceManager.e(Integer.toString(10000), "privacy_personalized_ad_recommend", this.b);
            SharedPreferenceManager.e(Integer.toString(10000), "privacy_first_login", true);
        }
    }

    private void a(int i) {
        Context context = this.d;
        if (context == null) {
            return;
        }
        if (i == 1) {
            SharedPreferenceManager.e(context, Integer.toString(10005), "hw_health_terms_authorize_china", "5", (StorageParams) null);
        } else if (i == 8) {
            SharedPreferenceManager.e(context, Integer.toString(10005), "hw_health_terms_authorize_russia", "1", (StorageParams) null);
        } else if (i == 5) {
            SharedPreferenceManager.e(context, Integer.toString(10005), "hw_health_terms_authorize_hong_kong", "1", (StorageParams) null);
        } else if (i == 7) {
            SharedPreferenceManager.e(context, Integer.toString(10005), "hw_health_terms_authorize_europe", "1", (StorageParams) null);
        } else {
            SharedPreferenceManager.e(context, Integer.toString(10005), "hw_health_terms_authorize_no_cloud", "1", (StorageParams) null);
        }
        LoginInit.getInstance(this.d).setAccountInfo(1009, Integer.toString(i), null);
        e(i);
    }

    private void e(int i) {
        if (i == 0 || !"0".equals(LoginInit.getInstance(this.d).getAccountInfo(1011))) {
            return;
        }
        LoginInit.getInstance(this.d).setIsLogined(false);
        LoginInit.getInstance(this.d).setAccountInfo(1011, "", null);
        SharedPreferenceUtil.getInstance(BaseApplication.getContext()).setCountryCode(SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(PrebakedEffectId.RT_SNIPER_RIFLE), "select_country"));
        jfa.b();
    }

    private void aaX_(View view, View view2) {
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.hw_health_service_item_all);
        HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.hw_health_privacy_eu_text_two);
        LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.hw_health_privacy_eu_cloud);
        HealthTextView healthTextView3 = (HealthTextView) view.findViewById(R.id.hw_health_privacy_eu_text_one);
        LinearLayout linearLayout2 = (LinearLayout) view.findViewById(R.id.hw_health_privacy_eu_no_cloud);
        HealthTextView healthTextView4 = (HealthTextView) view2.findViewById(R.id.hw_health_ove_agreement_agree_text);
        if (nsn.r()) {
            healthTextView3.setTextSize(1, 26.0f);
            healthTextView.setTextSize(1, 26.0f);
            healthTextView2.setTextSize(1, 26.0f);
            abr_(view);
            abs_(view2);
            healthTextView4.setTextSize(1, 26.0f);
            abp_(linearLayout);
            abq_(linearLayout2);
        }
        healthTextView.setText(this.d.getString(R.string._2130841283_res_0x7f020ec3, this.d.getResources().getString(R.string._2130841158_res_0x7f020e46)));
        healthTextView2.setMovementMethod(LinkMovementMethod.getInstance());
        healthTextView2.setHighlightColor(this.d.getResources().getColor(android.R.color.transparent));
        HealthTextView healthTextView5 = (HealthTextView) view2.findViewById(R.id.hw_health_ove_agreement_agree_text);
        healthTextView5.setMovementMethod(LinkMovementMethod.getInstance());
        healthTextView5.setHighlightColor(this.d.getResources().getColor(android.R.color.transparent));
        abB_(linearLayout, healthTextView3, linearLayout2);
        String string = this.d.getString(R.string._2130837554_res_0x7f020032);
        String string2 = this.d.getString(R.string._2130841166_res_0x7f020e4e);
        SpannableString spannableString = new SpannableString(this.d.getString(R.string._2130841178_res_0x7f020e5a, string));
        SpannableString spannableString2 = new SpannableString(this.d.getString(R.string._2130841165_res_0x7f020e4d, string2));
        int indexOf = spannableString.toString().indexOf(string);
        if (indexOf != -1) {
            rvq rvqVar = new rvq(this.d, "HealthUserAgreement", false);
            spannableString.setSpan(new TypefaceSpan(com.huawei.openalliance.ad.constant.Constants.FONT), indexOf, string.length() + indexOf, 33);
            spannableString.setSpan(rvqVar, indexOf, string.length() + indexOf, 17);
        }
        healthTextView5.setText(spannableString);
        int indexOf2 = spannableString2.toString().indexOf(string2);
        if (indexOf2 != -1) {
            rvq rvqVar2 = new rvq(this.d, "HealthPrivacy", false);
            spannableString2.setSpan(new TypefaceSpan(com.huawei.openalliance.ad.constant.Constants.FONT), indexOf2, string2.length() + indexOf2, 33);
            spannableString2.setSpan(rvqVar2, indexOf2, string2.length() + indexOf2, 17);
        }
        healthTextView2.setText(spannableString2);
    }

    private void abB_(LinearLayout linearLayout, HealthTextView healthTextView, LinearLayout linearLayout2) {
        if (Utils.i()) {
            linearLayout2.setVisibility(8);
            healthTextView.setText(this.d.getString(R.string._2130841361_res_0x7f020f11, this.d.getResources().getString(R.string._2130841289_res_0x7f020ec9)));
            return;
        }
        linearLayout.setVisibility(8);
    }

    private void abr_(View view) {
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.text_one);
        HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.text_two);
        HealthTextView healthTextView3 = (HealthTextView) view.findViewById(R.id.text_three);
        HealthTextView healthTextView4 = (HealthTextView) view.findViewById(R.id.text_four);
        healthTextView.setTextSize(1, 26.0f);
        healthTextView2.setTextSize(1, 26.0f);
        healthTextView3.setTextSize(1, 26.0f);
        healthTextView4.setTextSize(1, 26.0f);
    }

    private void abs_(View view) {
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.child_text);
        HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.page_two_online_text);
        HealthTextView healthTextView3 = (HealthTextView) view.findViewById(R.id.page_two_text_one);
        HealthTextView healthTextView4 = (HealthTextView) view.findViewById(R.id.page_two_text_two);
        healthTextView3.setTextSize(1, 26.0f);
        healthTextView4.setTextSize(1, 26.0f);
        healthTextView2.setTextSize(1, 20.0f);
        healthTextView.setTextSize(1, 26.0f);
    }

    private void abp_(LinearLayout linearLayout) {
        HealthTextView healthTextView = (HealthTextView) linearLayout.findViewById(R.id.hw_health_privacy_eu_text_one);
        HealthTextView healthTextView2 = (HealthTextView) linearLayout.findViewById(R.id.hw_health_privacy_eu_text_two);
        d(healthTextView, 26);
        d(healthTextView2, 26);
    }

    private void abq_(LinearLayout linearLayout) {
        HealthTextView healthTextView = (HealthTextView) linearLayout.findViewById(R.id.loading_text);
        HealthTextView healthTextView2 = (HealthTextView) linearLayout.findViewById(R.id.cloud_text);
        d(healthTextView, 26);
        d(healthTextView2, 26);
        HealthTextView healthTextView3 = (HealthTextView) linearLayout.findViewById(R.id.map_text);
        HealthTextView healthTextView4 = (HealthTextView) linearLayout.findViewById(R.id.weather_text);
        HealthTextView healthTextView5 = (HealthTextView) linearLayout.findViewById(R.id.exit_text);
        d(healthTextView3, 26);
        d(healthTextView4, 26);
        d(healthTextView5, 26);
    }

    private void abh_(View view) {
        SpannableString spannableString;
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.textView);
        healthTextView.setMovementMethod(LinkMovementMethod.getInstance());
        healthTextView.setHighlightColor(0);
        String string = this.d.getResources().getString(R.string._2130837571_res_0x7f020043);
        String string2 = this.d.getString(R.string._2130837568_res_0x7f020040, string);
        String string3 = this.d.getString(R.string._2130837554_res_0x7f020032);
        String trim = this.d.getString(R.string._2130837572_res_0x7f020044).trim();
        String trim2 = this.d.getString(R.string._2130841375_res_0x7f020f1f).trim();
        String string4 = this.d.getString(R.string._2130837562_res_0x7f02003a);
        view.findViewById(R.id.scrollview_privacy).setVisibility(8);
        if (LoginInit.getInstance(BaseApplication.getContext()).isKidAccount()) {
            spannableString = new SpannableString(this.d.getString(R.string._2130837578_res_0x7f02004a, string2, string3, trim));
        } else {
            Context context = this.d;
            Object[] objArr = new Object[4];
            objArr[0] = string2;
            objArr[1] = string3;
            objArr[2] = string4;
            if (!LanguageUtil.bi(context)) {
                trim2 = trim;
            }
            objArr[3] = trim2;
            spannableString = new SpannableString(context.getString(R.string._2130837567_res_0x7f02003f, objArr));
        }
        if (nsn.r()) {
            healthTextView.setTextSize(1, 26.0f);
        }
        abt_(spannableString, string4);
        sds.dWm_(spannableString, string);
        abn_(spannableString, string3, "HealthUserAgreement");
        abn_(spannableString, trim, "HealthPrivacy");
        healthTextView.setText(spannableString);
    }

    private void abi_(View view) {
        if (CommonUtil.cd()) {
            view.findViewById(R.id.scrollview_privacy).setVisibility(8);
            HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.hw_health_service_item_one);
            String string = this.d.getResources().getString(R.string._2130837571_res_0x7f020043);
            SpannableString spannableString = new SpannableString(this.d.getString(R.string._2130841310_res_0x7f020ede, string, this.d.getResources().getString(R.string.IDS_hwh_device_oversea)));
            sds.dWm_(spannableString, string);
            healthTextView.setText(spannableString);
            if (nsn.r()) {
                d(healthTextView, 26);
            }
        } else {
            view.findViewById(R.id.icon_privacy_center).setVisibility(8);
            HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.hw_health_service_item_one);
            healthTextView2.setText(new SpannableString(this.d.getString(R.string._2130841283_res_0x7f020ec3, this.d.getResources().getString(R.string._2130841284_res_0x7f020ec4))));
            HealthTextView healthTextView3 = (HealthTextView) view.findViewById(R.id.hw_health_service_item_two);
            healthTextView3.setText(sds.dWj_());
            if (nsn.r()) {
                d(healthTextView2, 26);
                d(healthTextView3, 26);
            }
        }
        String string2 = this.d.getString(R.string._2130837554_res_0x7f020032);
        String trim = this.d.getString(R.string._2130837572_res_0x7f020044).trim();
        SpannableString spannableString2 = new SpannableString(this.d.getString(R.string._2130841217_res_0x7f020e81, string2, trim));
        HealthTextView healthTextView4 = (HealthTextView) view.findViewById(R.id.textView);
        healthTextView4.setMovementMethod(LinkMovementMethod.getInstance());
        healthTextView4.setHighlightColor(0);
        abn_(spannableString2, string2, "HealthUserAgreement");
        abn_(spannableString2, trim, "HealthPrivacy");
        healthTextView4.setText(spannableString2);
        if (nsn.r()) {
            abo_(view);
        }
    }

    private void abo_(View view) {
        if (view == null) {
            LogUtil.h("PrivacyInteractors", "setLargeFont, layout is null");
            return;
        }
        d((HealthTextView) view.findViewById(R.id.text2), 60);
        d((HealthTextView) view.findViewById(R.id.text3), 28);
        d((HealthTextView) view.findViewById(R.id.hw_health_service_item_one), 26);
        d((HealthTextView) view.findViewById(R.id.hw_health_service_item_two), 26);
        d((HealthTextView) view.findViewById(R.id.hw_health_service_item_six), 26);
        d((HealthTextView) view.findViewById(R.id.hw_health_service_item_seven), 26);
        d((HealthTextView) view.findViewById(R.id.agreement_text), 26);
        d((HealthTextView) view.findViewById(R.id.textView), 26);
    }

    private void abn_(SpannableString spannableString, String str, String str2) {
        int indexOf = spannableString.toString().indexOf(str);
        if (indexOf != -1) {
            rvq rvqVar = new rvq(this.d, str2, false);
            int length = str.length() + indexOf;
            spannableString.setSpan(new TypefaceSpan(com.huawei.openalliance.ad.constant.Constants.FONT), indexOf, length, 33);
            spannableString.setSpan(rvqVar, indexOf, length, 17);
        }
    }

    private void abt_(SpannableString spannableString, String str) {
        int indexOf = spannableString.toString().indexOf(str);
        if (indexOf != -1) {
            int length = str.length() + indexOf;
            spannableString.setSpan(new TypefaceSpan(com.huawei.openalliance.ad.constant.Constants.FONT), indexOf, length, 33);
            spannableString.setSpan(new AnonymousClass5(this.d, ""), indexOf, length, 33);
        }
    }

    /* renamed from: dzn$5, reason: invalid class name */
    class AnonymousClass5 extends rvq {
        AnonymousClass5(Context context, String str) {
            super(context, str);
        }

        @Override // defpackage.rvq, android.text.style.ClickableSpan
        public void onClick(View view) {
            CustomAlertDialog c = new CustomAlertDialog.Builder(dzn.this.d).cyp_(dzn.this.abb_()).cyo_(R.string._2130841237_res_0x7f020e95, new DialogInterface.OnClickListener() { // from class: dzt
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    dzn.AnonymousClass5.abO_(dialogInterface, i);
                }
            }).c();
            c.setCancelable(false);
            c.show();
            ViewClickInstrumentation.clickOnView(view);
        }

        static /* synthetic */ void abO_(DialogInterface dialogInterface, int i) {
            LogUtil.a("PrivacyInteractors", "setPermissionDescription ok");
            ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public View abb_() {
        View inflate = View.inflate(this.d, R.layout.dialog_privacy_value_added_services, null);
        final HealthCheckBox healthCheckBox = (HealthCheckBox) inflate.findViewById(R.id.privacy_recommend_box);
        final HealthCheckBox healthCheckBox2 = (HealthCheckBox) inflate.findViewById(R.id.privacy_ad_recommend_box);
        LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.privacy_ad_recommend_layout);
        if (LoginInit.getInstance(BaseApplication.getContext()).isMinorAccount()) {
            LogUtil.a("PrivacyInteractors", "getLayoutByContent isMinorAccount true, hide ad recommend");
            linearLayout.setVisibility(8);
            this.b = false;
        } else {
            healthCheckBox2.setChecked(this.b);
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: dzr
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    dzn.this.abE_(healthCheckBox2, view);
                }
            });
            healthCheckBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.health.interactor.PrivacyInteractors$$ExternalSyntheticLambda3
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    dzn.this.abF_(compoundButton, z);
                }
            });
        }
        healthCheckBox.setChecked(this.e);
        inflate.findViewById(R.id.privacy_recommend_layout).setOnClickListener(new View.OnClickListener() { // from class: dzq
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                dzn.this.abG_(healthCheckBox, view);
            }
        });
        healthCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.health.interactor.PrivacyInteractors$$ExternalSyntheticLambda5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                dzn.this.abH_(compoundButton, z);
            }
        });
        return inflate;
    }

    /* synthetic */ void abE_(HealthCheckBox healthCheckBox, View view) {
        boolean z = !healthCheckBox.isChecked();
        healthCheckBox.setChecked(z);
        this.b = z;
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void abF_(CompoundButton compoundButton, boolean z) {
        this.b = z;
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    /* synthetic */ void abG_(HealthCheckBox healthCheckBox, View view) {
        boolean z = !healthCheckBox.isChecked();
        healthCheckBox.setChecked(z);
        this.e = z;
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void abH_(CompoundButton compoundButton, boolean z) {
        this.e = z;
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:30:0x003d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r6v10, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r6v2 */
    /* JADX WARN: Type inference failed for: r6v6, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r6v7, types: [java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r6v9, types: [java.io.InputStream] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public android.graphics.Bitmap abD_(java.lang.String r6, java.lang.String r7) {
        /*
            r5 = this;
            java.lang.String r0 = "getBitmap finally catch IOException"
            java.lang.String r1 = "PrivacyInteractors"
            r2 = 0
            java.io.InputStream r6 = defpackage.moh.e(r6, r7)     // Catch: java.lang.Throwable -> L1e java.lang.Exception -> L20
            android.graphics.Bitmap r2 = android.graphics.BitmapFactory.decodeStream(r6)     // Catch: java.lang.Throwable -> L1b java.lang.Exception -> L21
            if (r6 == 0) goto L39
            r6.close()     // Catch: java.io.IOException -> L13
            goto L39
        L13:
            java.lang.Object[] r6 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r6)
            goto L39
        L1b:
            r7 = move-exception
            r2 = r6
            goto L3a
        L1e:
            r6 = move-exception
            goto L3b
        L20:
            r6 = r2
        L21:
            r7 = 1
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch: java.lang.Throwable -> L1b
            java.lang.String r3 = "getBitmap Exception"
            r4 = 0
            r7[r4] = r3     // Catch: java.lang.Throwable -> L1b
            com.huawei.hwlogsmodel.LogUtil.b(r1, r7)     // Catch: java.lang.Throwable -> L1b
            if (r6 == 0) goto L39
            r6.close()     // Catch: java.io.IOException -> L32
            goto L39
        L32:
            java.lang.Object[] r6 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r6)
        L39:
            return r2
        L3a:
            r6 = r7
        L3b:
            if (r2 == 0) goto L48
            r2.close()     // Catch: java.io.IOException -> L41
            goto L48
        L41:
            java.lang.Object[] r7 = new java.lang.Object[]{r0}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r7)
        L48:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.dzn.abD_(java.lang.String, java.lang.String):android.graphics.Bitmap");
    }

    public static boolean d() {
        return c == 1;
    }

    public static boolean h() {
        int i = c;
        return i == 8 || i == 5;
    }

    public static boolean i() {
        boolean a2;
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        if (!TextUtils.isEmpty(accountInfo) && !"0".equals(accountInfo)) {
            a2 = e();
        } else {
            a2 = a();
        }
        return !a2;
    }

    public static boolean a() {
        if (b()) {
            return false;
        }
        Context context = BaseApplication.getContext();
        SharedPreferenceManager.e(context, Integer.toString(PrebakedEffectId.RT_SNIPER_RIFLE), "key_privacy_notice_state", "privacy_notice_state_before_login", (StorageParams) null);
        if (!Utils.o()) {
            return c(1, false);
        }
        String b2 = SharedPreferenceManager.b(context, Integer.toString(PrebakedEffectId.RT_SNIPER_RIFLE), "select_country");
        LogUtil.a("PrivacyInteractors", "isNeedShowBeforeLogin countryCode=", b2);
        if ("cn".equalsIgnoreCase(b2)) {
            return c(1, false);
        }
        int siteIdByCountry = CloudImpl.c(context).getSiteIdByCountry(b2);
        if (siteIdByCountry != 0 && Utils.l()) {
            String accountInfo = LoginInit.getInstance(context).getAccountInfo(1010);
            LogUtil.a("PrivacyInteractors", "isNeedShowBeforeLogin loginCountry = ", accountInfo);
            if (!TextUtils.isEmpty(accountInfo)) {
                SharedPreferenceManager.e(context, Integer.toString(PrebakedEffectId.RT_SNIPER_RIFLE), "key_privacy_notice_state", "", (StorageParams) null);
            }
            return c(0, false);
        }
        return c(siteIdByCountry, false);
    }

    public static boolean e() {
        if (b()) {
            return false;
        }
        Context context = BaseApplication.getContext();
        SharedPreferenceManager.e(context, Integer.toString(PrebakedEffectId.RT_SNIPER_RIFLE), "key_privacy_notice_state", "", (StorageParams) null);
        if (Utils.l()) {
            return c(0, true);
        }
        LoginInit loginInit = LoginInit.getInstance(context);
        if (loginInit.getIsLogined()) {
            return c(CommonUtil.h(loginInit.getAccountInfo(1009)), true);
        }
        return false;
    }

    public static boolean c(int i, boolean z) {
        boolean equals;
        c = i;
        LogUtil.a("PrivacyInteractors", "isNeedShowPrivacyUi siteId = ", Integer.valueOf(i));
        Context context = BaseApplication.getContext();
        if (z && !AuthorizationUtils.a(context)) {
            LogUtil.a("PrivacyInteractors", "isNeedShowPrivacyUi key_wether_to_auth is false");
            return true;
        }
        if (i == 1) {
            equals = "5".equals(SharedPreferenceManager.b(context, Integer.toString(10005), "hw_health_terms_authorize_china"));
        } else if (i == 8) {
            equals = "1".equals(SharedPreferenceManager.b(context, Integer.toString(10005), "hw_health_terms_authorize_russia"));
        } else if (i == 5) {
            equals = "1".equals(SharedPreferenceManager.b(context, Integer.toString(10005), "hw_health_terms_authorize_hong_kong"));
        } else if (i == 7) {
            equals = "1".equals(SharedPreferenceManager.b(context, Integer.toString(10005), "hw_health_terms_authorize_europe"));
        } else {
            equals = "1".equals(SharedPreferenceManager.b(context, Integer.toString(10005), "hw_health_terms_authorize_no_cloud"));
        }
        return !equals;
    }

    public static boolean c(Context context) {
        if (!Utils.l() || context == null) {
            return false;
        }
        if (TextUtils.isEmpty(SharedPreferenceManager.b(context, Integer.toString(10005), "hw_health_terms_authorize_no_cloud"))) {
            return false;
        }
        return !"1".equals(r3);
    }

    public static void b(Context context) {
        if (context == null) {
            return;
        }
        LoginInit loginInit = LoginInit.getInstance(context);
        if (loginInit.getIsLogined()) {
            String b2 = SharedPreferenceManager.b(context, Integer.toString(10000), "agr_last_user_id");
            String accountInfo = loginInit.getAccountInfo(1011);
            boolean isEmpty = TextUtils.isEmpty(accountInfo);
            String str = accountInfo;
            if (isEmpty) {
                str = Constants.NULL;
            }
            if (!TextUtils.isEmpty(b2) && !Constants.NULL.equals(str) && !Constants.NULL.equals(b2) && !str.equals(b2)) {
                LogUtil.a("PrivacyInteractors", "initAgreementQueryInfo change user is true");
                SharedPreferenceManager.e(Integer.toString(10000), "is_agr_change_user", true);
                b(false);
            }
            if (TextUtils.isEmpty(b2) || !b2.equals(str)) {
                LogUtil.a("PrivacyInteractors", "initAgreementQueryInfo queryTime reset");
                SharedPreferenceManager.e(context, Integer.toString(10000), "agr_last_user_id", str, new StorageParams(1));
                SharedPreferenceManager.e(context, Integer.toString(10000), "agr_last_query_time", "", (StorageParams) null);
                SharedPreferenceManager.e(context, Integer.toString(10000), "beta_agr_last_query_time", "", (StorageParams) null);
                SharedPreferenceManager.e(context, Integer.toString(10000), "if_first_agr_sign", "", (StorageParams) null);
            }
        }
    }

    public static void b(boolean z) {
        LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
        if (!loginInit.getIsLogined()) {
            LogUtil.h("PrivacyInteractors", "saveUserSignAgr do not login");
            return;
        }
        String accountInfo = loginInit.getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.h("PrivacyInteractors", "saveUserSignAgr signUserId is empty");
            return;
        }
        LogUtil.a("PrivacyInteractors", "saveUserSignAgr isAgree is ", Boolean.valueOf(z));
        SharedPreferenceManager.e(Integer.toString(10000), "is_agr_sign" + accountInfo, z);
    }

    public static boolean f() {
        LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
        boolean z = false;
        if (!loginInit.getIsLogined()) {
            LogUtil.h("PrivacyInteractors", "saveUserSignAgr do not login");
            return false;
        }
        String accountInfo = loginInit.getAccountInfo(1011);
        if (TextUtils.isEmpty(accountInfo)) {
            LogUtil.h("PrivacyInteractors", "saveUserSignAgr signUserId is empty");
            return false;
        }
        if (SharedPreferenceManager.e(Integer.toString(10000), "is_agr_sign" + accountInfo)) {
            z = SharedPreferenceManager.a(Integer.toString(10000), "is_agr_sign" + accountInfo, false);
        }
        LogUtil.a("PrivacyInteractors", "isUserSignAgr, isSignAgr is ", Boolean.valueOf(z));
        return z;
    }

    public static boolean c() {
        boolean a2 = SharedPreferenceManager.e(Integer.toString(10000), "is_agr_change_user") ? SharedPreferenceManager.a(Integer.toString(10000), "is_agr_change_user", false) : false;
        LogUtil.a("PrivacyInteractors", "isChangeNewUser, isChangeUser is ", Boolean.valueOf(a2));
        return a2;
    }

    public static void j() {
        Context context = BaseApplication.getContext();
        LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
        int h = CommonUtil.h(loginInit.getAccountInfo(1009));
        boolean a2 = SharedPreferenceManager.a(Integer.toString(10000), "privacy_first_login", false);
        if (h != 1 || !a2) {
            LogUtil.a("PrivacyInteractors", "updateRecommendation is not support");
            return;
        }
        v();
        if (!SharedPreferenceManager.e(Integer.toString(10000), "privacy_personalized_recommend")) {
            LogUtil.a("PrivacyInteractors", "updateRecommendation not contain key");
            return;
        }
        boolean a3 = SharedPreferenceManager.a(Integer.toString(10000), "privacy_personalized_recommend", true);
        LogUtil.a("PrivacyInteractors", "set recommendation is ", Boolean.valueOf(a3));
        SharedPreferenceManager.e(Integer.toString(10000), "privacy_first_login", false);
        if (!loginInit.isKidAccount()) {
            rvo.e(context).e(12, a3);
        } else if (!a3) {
            rvo.e(context).e(12, false);
        } else {
            LogUtil.a("PrivacyInteractors", "set recommendation do not update");
        }
    }

    private static void v() {
        if (!SharedPreferenceManager.e(Integer.toString(10000), "privacy_personalized_ad_recommend")) {
            LogUtil.a("PrivacyInteractors", "updateAdRecommendation not contain key");
            return;
        }
        Context context = BaseApplication.getContext();
        boolean z = SharedPreferenceManager.a(Integer.toString(10000), "privacy_personalized_ad_recommend", true) && !LoginInit.getInstance(BaseApplication.getContext()).isMinorAccount();
        LogUtil.a("PrivacyInteractors", "set ad recommendation is ", Boolean.valueOf(z));
        SharedPreferenceManager.e(Integer.toString(10000), "privacy_first_login", false);
        rvo.e(context).e(13, z);
        rvo.e(context).e(14, z);
        rvo.e(context).e(15, z);
    }

    private static void d(HealthTextView healthTextView, int i) {
        if (healthTextView != null) {
            healthTextView.setTextSize(1, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        HandlerExecutor.e(new Runnable() { // from class: dzo
            @Override // java.lang.Runnable
            public final void run() {
                DynamicResourcesLoader.c(BaseApplication.getContext());
            }
        });
    }

    public static boolean b() {
        return SharedPreferenceManager.a(Integer.toString(PrebakedEffectId.RT_FLY), "is_base_service_model", false);
    }
}
