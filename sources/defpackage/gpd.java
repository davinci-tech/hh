package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.TypefaceSpan;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.google.gson.Gson;
import com.huawei.health.R;
import com.huawei.health.interactor.MainInteractors;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.hwid.common.constants.CommonConstant;
import com.huawei.hwcloudmodel.agreement.AgrHttp;
import com.huawei.hwcloudmodel.https.HttpResCallBack;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider;
import health.compact.a.AuthorizationUtils;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* loaded from: classes4.dex */
public class gpd {

    /* renamed from: a, reason: collision with root package name */
    private SpannableString f12892a;
    private int b;
    private Context c;
    private String d;
    private String e;
    private List<Integer> f = new ArrayList(2);
    private View g;
    private String h;
    private String i;

    public gpd(Context context, String str, int i, String str2) {
        if (context == null) {
            LogUtil.h("SignAgreementDialog", "SignAgreementDialog context is null");
            return;
        }
        this.c = context;
        this.d = str;
        this.b = i;
        this.h = str2;
    }

    public void d() {
        if (dzn.b()) {
            ReleaseLogUtil.e("R_SignAgreementDialog", "showAgreementDialog isBaseServiceModel is true");
            return;
        }
        String string = this.c.getString(R.string._2130841275_res_0x7f020ebb);
        String string2 = this.c.getString(R.string._2130841274_res_0x7f020eba);
        ArrayList arrayList = new ArrayList();
        int i = this.b;
        if (i == 1) {
            arrayList.add(118);
            this.i = this.c.getString(R.string._2130841243_res_0x7f020e9b);
            this.e = this.c.getString(R.string._2130841154_res_0x7f020e42, string);
            this.f12892a = new SpannableString(this.c.getString(R.string._2130841245_res_0x7f020e9d, string));
            b(string, "HealthUserAgreement");
        } else if (i == 2) {
            arrayList.add(10009);
            this.i = this.c.getString(R.string._2130841244_res_0x7f020e9c);
            this.e = this.c.getString(R.string._2130841154_res_0x7f020e42, string2);
            this.f12892a = new SpannableString(this.c.getString(R.string._2130841245_res_0x7f020e9d, string2));
            b(string2, "HealthPrivacy");
        } else if (i == 3) {
            arrayList.add(118);
            arrayList.add(10009);
            this.i = this.c.getString(R.string._2130841242_res_0x7f020e9a);
            this.e = this.c.getString(R.string._2130841155_res_0x7f020e43, string, string2);
            this.f12892a = new SpannableString(this.c.getString(R.string._2130841246_res_0x7f020e9e, string, string2));
            b(string, "HealthUserAgreement");
            b(string2, "HealthPrivacy");
        } else {
            this.i = this.c.getString(R.string._2130841243_res_0x7f020e9b);
            this.e = this.c.getString(R.string._2130841154_res_0x7f020e42, string);
            this.f12892a = new SpannableString(this.c.getString(R.string._2130841245_res_0x7f020e9d, string));
            b(string, "HealthUserAgreement");
        }
        this.f = arrayList;
        a(this.c);
        a();
    }

    private void a(Context context) {
        View inflate = View.inflate(context, R.layout.hw_health_agr_sign_dialog, null);
        this.g = inflate;
        ((HealthTextView) inflate.findViewById(R.id.hw_health_notice_change_text)).setText(this.e);
        HealthTextView healthTextView = (HealthTextView) this.g.findViewById(R.id.hw_health_agr_sign_service);
        healthTextView.setMovementMethod(LinkMovementMethod.getInstance());
        healthTextView.setHighlightColor(ContextCompat.getColor(context, android.R.color.transparent));
        healthTextView.setText(this.f12892a);
    }

    private void b(String str, String str2) {
        int indexOf = this.f12892a.toString().indexOf(str);
        if (indexOf != -1) {
            rvq rvqVar = new rvq(this.c, str2);
            this.f12892a.setSpan(new TypefaceSpan(Constants.FONT), indexOf, str.length() + indexOf, 33);
            this.f12892a.setSpan(rvqVar, indexOf, str.length() + indexOf, 17);
        }
    }

    private void a() {
        String upperCase = this.c.getString(R.string._2130839489_res_0x7f0207c1).toUpperCase(Locale.ROOT);
        b(this.c, 0);
        CustomViewDialog.Builder czg_ = new CustomViewDialog.Builder(this.c).a(this.i).czg_(this.g);
        if (this.b == 2 && CommonUtil.h(LoginInit.getInstance(this.c).getAccountInfo(1009)) == 7) {
            upperCase = this.c.getString(R.string._2130837556_res_0x7f020034).toUpperCase(Locale.ENGLISH);
        } else {
            czg_.czd_(this.c.getString(R.string._2130837555_res_0x7f020033).toUpperCase(Locale.ENGLISH), new View.OnClickListener() { // from class: gpe
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    gpd.this.aRp_(view);
                }
            });
        }
        czg_.czf_(upperCase, new View.OnClickListener() { // from class: gpd.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (LoginInit.getInstance(gpd.this.c).isKidAccount()) {
                    Intent intent = new Intent();
                    intent.putExtra("requestCode", SleepBaseBarLineChartProvider.MSG_UPDATE_DATA_ORIGINAL_ICON);
                    intent.putExtra(CommonConstant.REALNAMERESULT.KEY_VERIFY_TYPE, "1");
                    intent.setClassName(gpd.this.c, "com.huawei.health.HuaweiLoginActivity");
                    ((Activity) gpd.this.c).startActivityForResult(intent, 6013);
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                gpd.this.c();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomViewDialog e = czg_.e();
        e.setCancelable(false);
        e.show();
    }

    /* synthetic */ void aRp_(View view) {
        b();
        ViewClickInstrumentation.clickOnView(view);
    }

    public void c() {
        e(new HttpResCallBack() { // from class: gpf
            @Override // com.huawei.hwcloudmodel.https.HttpResCallBack
            public final void onFinished(int i, String str) {
                LogUtil.a("SignAgreementDialog", "AgrSignDialog_signAgrHttp onFinished ", str);
            }
        });
        b(this.c, 2);
        SharedPreferenceManager.e(this.c, Integer.toString(10000), "agr_if_agree_authorize", "1", new StorageParams());
        ReleaseLogUtil.e("R_SignAgreementDialog", "showUserAgreeDialog, key_wether_to_auth is true");
        KeyValDbManager.b(this.c).e("key_wether_to_auth", String.valueOf(true));
        SharedPreferenceManager.e(this.c, Integer.toString(10000), "key_wether_to_auth", String.valueOf(true), new StorageParams());
        AuthorizationUtils.d();
        SharedPreferenceManager.e(BaseApplication.getContext(), Integer.toString(10000), "agr_authorized_versions", new Gson().toJson(this.f), new StorageParams());
    }

    public void b() {
        b(this.c, 1);
        ixx.d().c(this.c);
        MainInteractors.d();
    }

    private void b(Context context, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("type", Integer.valueOf(i));
        ixx.d().d(context, AnalyticsValue.HEALTH_SHOW_SIGN_AGREEMENT_2040081.value(), hashMap, 0);
    }

    private void e(HttpResCallBack httpResCallBack) {
        if (TextUtils.isEmpty(this.d) || TextUtils.isEmpty(this.h) || koq.b(this.f)) {
            LogUtil.h("SignAgreementDialog", "mAgreementUrl, mToken or mSignTypeTmp is empty.");
            return;
        }
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010);
        String country = BaseApplication.getContext().getResources().getConfiguration().locale.getCountry();
        String language = BaseApplication.getContext().getResources().getConfiguration().locale.getLanguage();
        new AgrHttp().signHttpReq(this.h, this.d, true, this.f, accountInfo, language + "_" + country, httpResCallBack);
    }
}
