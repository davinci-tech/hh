package com.huawei.ui.main.stories.me.views.privacy;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hms.support.hwid.common.constants.CommonConstant;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ResultCallback;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.flowlayout.HealthFlowLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.sleep.SleepBaseBarLineChartProvider;
import com.huawei.ui.main.stories.me.views.builder.LabelBuilder;
import com.huawei.ui.main.stories.me.views.builder.PrivacyClickableSpan;
import com.huawei.ui.main.stories.me.views.privacy.ContentRecommendActivity;
import defpackage.gmz;
import defpackage.ixx;
import defpackage.nmk;
import defpackage.nrh;
import defpackage.nsn;
import defpackage.rhv;
import defpackage.rhz;
import defpackage.rub;
import defpackage.rvo;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;

/* loaded from: classes7.dex */
public class ContentRecommendActivity extends BaseActivity implements LabelBuilder.LabelListener, CompoundButton.OnCheckedChangeListener {

    /* renamed from: a, reason: collision with root package name */
    private Context f10378a;
    private HealthSwitchButton c;
    private ImageView e;
    private rvo f;
    private HealthTextView g;
    private LinearLayout i;
    private LinearLayout j;
    private RelativeLayout k;
    private HealthFlowLayout l;
    private HealthSwitchButton m;
    private ImageView o;
    private gmz q;
    private HealthTextView r;
    private HealthFlowLayout s;
    private HealthTextView t;
    private ArrayList<rhz> v = new ArrayList<>();
    private int n = 1;
    private boolean h = false;
    private int p = -1;
    private d d = new d(this);
    private boolean b = true;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_content_recommend);
        getWindow().getDecorView().setBackground(null);
        this.f10378a = this;
        this.f = rvo.e(getApplicationContext());
        this.q = gmz.d();
        this.f = rvo.e(getApplicationContext());
        c();
        f();
    }

    private void c() {
        this.e = (ImageView) findViewById(R.id.iv_health_service_recommendation);
        this.o = (ImageView) findViewById(R.id.iv_personalizedRecommendation);
        this.g = (HealthTextView) findViewById(R.id.htv_privacy_issues);
        this.l = (HealthFlowLayout) findViewById(R.id.selected_flow_layout);
        this.s = (HealthFlowLayout) findViewById(R.id.unselected_flow_layout);
        this.c = (HealthSwitchButton) findViewById(R.id.goods_switch);
        this.m = (HealthSwitchButton) findViewById(R.id.personalization_switch);
        this.k = (RelativeLayout) findViewById(R.id.rel_loading);
        this.i = (LinearLayout) findViewById(R.id.ll_used_label);
        this.t = (HealthTextView) findViewById(R.id.tv_used_label_second_title);
        this.j = (LinearLayout) findViewById(R.id.ll_unused_label);
        this.r = (HealthTextView) findViewById(R.id.tv_unused_label_second_title);
        a();
        LabelBuilder labelBuilder = new LabelBuilder(this, R.drawable._2131430209_res_0x7f0b0b41, this);
        LabelBuilder labelBuilder2 = new LabelBuilder(this, R.drawable._2131430208_res_0x7f0b0b40, this);
        this.l.setTextViewBuilder(labelBuilder);
        this.s.setTextViewBuilder(labelBuilder2);
        PrivacyClickableSpan privacyClickableSpan = new PrivacyClickableSpan(this);
        String string = getString(R$string.IDS_hwh_personal_recommend_privacy_issues_start);
        String string2 = getString(R$string.IDS_hwh_personal_recommend_privacy_issues_middle);
        String string3 = getString(R$string.IDS_hwh_personal_recommend_privacy_issues_end);
        String string4 = getString(R$string.IDS_hwh_personal_recommend_privacy_issues, new Object[]{string, string2, string3});
        SpannableString spannableString = new SpannableString(string4);
        spannableString.setSpan(privacyClickableSpan, string.length(), string4.length() - string3.length(), 33);
        this.g.setMovementMethod(LinkMovementMethod.getInstance());
        this.g.setText(spannableString);
        this.c.setChecked("1".equals(SharedPreferenceManager.b(this.f10378a, Integer.toString(10000), "health_product_recommend")));
        c(this.f, this.q);
        i();
        d();
        b();
    }

    private void a() {
        this.t.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.ui.main.stories.me.views.privacy.ContentRecommendActivity.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (ContentRecommendActivity.this.t.getLineCount() > 1) {
                    ContentRecommendActivity.this.i.setOrientation(1);
                }
                ContentRecommendActivity.this.t.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
        this.r.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.ui.main.stories.me.views.privacy.ContentRecommendActivity.5
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                if (ContentRecommendActivity.this.r.getLineCount() > 1) {
                    ContentRecommendActivity.this.j.setOrientation(1);
                }
                ContentRecommendActivity.this.r.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    private void i() {
        this.o.setOnClickListener(new View.OnClickListener() { // from class: rij
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ContentRecommendActivity.this.dOM_(view);
            }
        });
        this.e.setOnClickListener(new View.OnClickListener() { // from class: rii
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ContentRecommendActivity.this.dON_(view);
            }
        });
    }

    public /* synthetic */ void dOM_(View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            e(this.f10378a.getString(R$string.IDS_personalized_recommendation_exclamation_mark));
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public /* synthetic */ void dON_(View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            e(this.f10378a.getString(R$string.IDS_healthService_recommendations_exclamation_mark));
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void d() {
        this.m.setOnCheckedChangeListener(this);
        this.c.setOnCheckedChangeListener(this);
    }

    private void b() {
        this.k.setVisibility(0);
        rub.a(this.n, new ResultCallback<rhv>() { // from class: com.huawei.ui.main.stories.me.views.privacy.ContentRecommendActivity.4
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(rhv rhvVar) {
                LogUtil.d("ContentRecommendActivity", "queryUserLabelConfig onSuccess ", HiJsonUtil.e(rhvVar));
                if (rhvVar.getResultCode().intValue() == 0) {
                    ContentRecommendActivity.this.v.addAll(rhvVar.b());
                }
                ContentRecommendActivity.this.d.sendEmptyMessage(rhvVar.getResultCode().intValue());
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                ContentRecommendActivity.this.d.sendEmptyMessage(9999);
                LogUtil.c("ContentRecommendActivity", "queryUserLabelConfig onFailure ", th.getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        ArrayList<nmk> arrayList = new ArrayList<>();
        ArrayList<nmk> arrayList2 = new ArrayList<>();
        Iterator<rhz> it = this.v.iterator();
        while (it.hasNext()) {
            rhz next = it.next();
            if (next.l() == 0) {
                arrayList.add(next);
            } else {
                arrayList2.add(next);
            }
        }
        this.l.e(arrayList, false);
        this.s.e(arrayList2, false);
    }

    @Override // com.huawei.ui.main.stories.me.views.builder.LabelBuilder.LabelListener
    public void onItemClick(final rhz rhzVar, final int i) {
        this.k.setVisibility(0);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new rhz(rhzVar.f(), rhzVar.h(), rhzVar.i(), i));
        LogUtil.b("ContentRecommendActivity", HiJsonUtil.e(arrayList));
        rub.a(arrayList, new ResultCallback<CloudCommonReponse>() { // from class: com.huawei.ui.main.stories.me.views.privacy.ContentRecommendActivity.2
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(CloudCommonReponse cloudCommonReponse) {
                LogUtil.d("ContentRecommendActivity", "saveUserLabelConfig onSuccess ", HiJsonUtil.e(cloudCommonReponse));
                if (cloudCommonReponse.getResultCode().intValue() == 0) {
                    rhzVar.d(i);
                }
                ContentRecommendActivity.this.d.sendEmptyMessage(cloudCommonReponse.getResultCode().intValue());
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                ContentRecommendActivity.this.d.sendEmptyMessage(9999);
                LogUtil.c("ContentRecommendActivity", "saveUserLabelConfig onFailure ", th.getMessage());
            }
        });
    }

    private void f() {
        if (this.e != null) {
            final HealthTextView healthTextView = (HealthTextView) findViewById(R.id.healthService_title);
            final HealthTextView healthTextView2 = (HealthTextView) findViewById(R.id.personalized_title);
            this.e.post(new Runnable() { // from class: rif
                @Override // java.lang.Runnable
                public final void run() {
                    ContentRecommendActivity.this.e(healthTextView, healthTextView2);
                }
            });
        }
    }

    public /* synthetic */ void e(HealthTextView healthTextView, HealthTextView healthTextView2) {
        int width = this.e.getWidth() + BaseApplication.getContext().getResources().getDimensionPixelSize(R.dimen._2131363122_res_0x7f0a0532);
        if (LanguageUtil.bc(this.f10378a)) {
            healthTextView.setPadding(width, 0, 0, 0);
            healthTextView2.setPadding(width, 0, 0, 0);
        } else {
            healthTextView.setPadding(0, 0, width, 0);
            healthTextView2.setPadding(0, 0, width, 0);
        }
    }

    static class d extends BaseHandler<ContentRecommendActivity> {
        d(ContentRecommendActivity contentRecommendActivity) {
            super(contentRecommendActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dOQ_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(ContentRecommendActivity contentRecommendActivity, Message message) {
            contentRecommendActivity.k.setVisibility(8);
            LogUtil.d("ContentRecommendActivity", "SETTING_USER_LABEL_SUCCESS");
            int i = message.what;
            if (i == 0) {
                contentRecommendActivity.j();
            } else if (i == 1002 || i == 1004) {
                LoginInit.tryLoginWhenTokenInvalid();
            } else {
                nrh.c(contentRecommendActivity, contentRecommendActivity.getString(R$string.IDS_hwh_home_group_server_exception));
            }
        }
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        int id = compoundButton.getId();
        this.p = id;
        if (id == R.id.goods_switch) {
            if (z) {
                a(this.f10378a.getString(R$string.IDS_hw_healthService_agreement), this.f10378a.getString(R$string.IDS_hw_confirm_enable), AnalyticsValue.HEALTH_MINE_SETTINGS_RECOMMEND_AD_2040044.value(), 1);
            } else {
                e(z, AnalyticsValue.HEALTH_MINE_SETTINGS_RECOMMEND_AD_2040044.value(), "2", false);
            }
        } else if (id == R.id.personalization_switch) {
            if (!z) {
                if (this.b) {
                    this.b = true;
                    a(this.f10378a.getString(R$string.IDS_close_personalized_recommendation_tips), this.f10378a.getString(R$string.IDS_beta_continue_use), AnalyticsValue.HEALTH_PERSONALIZED_RECOMMENDATION_2041098.value(), 2);
                }
            } else {
                c(AnalyticsValue.HEALTH_PERSONALIZED_RECOMMENDATION_2041098.value(), "1", a.K);
            }
        }
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.d("ContentRecommendActivity", "onActivityResult requestCode = ", Integer.valueOf(i), ", resultCode = ", Integer.valueOf(i2));
        boolean z = i2 == -1;
        if (i == 210) {
            if (z) {
                e(true, AnalyticsValue.HEALTH_MINE_SETTINGS_RECOMMEND_AD_2040044.value(), "1", true);
                return;
            } else {
                c(false);
                return;
            }
        }
        if (i != 212) {
            LogUtil.d("ContentRecommendActivity", "default");
        } else if (z) {
            e(true, AnalyticsValue.HEALTH_PERSONALIZED_RECOMMENDATION_2041098.value(), "1", true);
        } else {
            this.b = false;
            c(false);
        }
    }

    private void c(rvo rvoVar, gmz gmzVar) {
        String c = gmzVar.c(12);
        String b = SharedPreferenceManager.b(this.f10378a, Integer.toString(10000), "personalized_recommend");
        if (TextUtils.isEmpty(b)) {
            this.m.setChecked("true".equals(c));
            if (Utils.o()) {
                return;
            }
            rvoVar.e(12, true);
            return;
        }
        this.m.setChecked("1".equals(b));
    }

    private void a(String str, String str2, final String str3, final int i) {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this);
        builder.e(str).czE_(str2.toUpperCase(Locale.getDefault()), new View.OnClickListener() { // from class: rig
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ContentRecommendActivity.this.dOO_(i, str3, view);
            }
        }).czA_(this.f10378a.getString(R$string.IDS_hw_still_off).toUpperCase(Locale.getDefault()), new View.OnClickListener() { // from class: rih
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ContentRecommendActivity.this.dOP_(i, str3, view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCancelable(false);
        e.show();
    }

    public /* synthetic */ void dOO_(int i, String str, View view) {
        if (1 == i) {
            c(str, "1", a.C);
        } else {
            a(true);
            c(true);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dOP_(int i, String str, View view) {
        if (2 == i) {
            e(false, str, "2", false);
        } else {
            a(true);
            c(false);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a(boolean z) {
        this.h = z;
    }

    private boolean e() {
        return this.h;
    }

    private void e(boolean z, String str, String str2, boolean z2) {
        if (!e()) {
            com.huawei.hwlogsmodel.LogUtil.a("ContentRecommendActivity", "setSwitchStatusAndBiEvent");
            c(z);
            c(str, str2);
            e(z2);
        }
        a(false);
    }

    private void c(String str, String str2, int i) {
        if (LoginInit.getInstance(this.f10378a).isKidAccount() && (i != 212 || !e())) {
            b(i);
        } else {
            e(true, str, str2, true);
        }
    }

    private void c(boolean z) {
        int i = this.p;
        if (i == R.id.goods_switch) {
            this.c.setChecked(z);
        } else if (i == R.id.personalization_switch) {
            this.m.setChecked(z);
        }
    }

    private void c(String str, String str2) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", "1");
        if (str2 != null) {
            hashMap.put("type", str2);
        }
        ixx.d().d(this.f10378a, str, hashMap, 0);
    }

    private void e(boolean z) {
        int i;
        int i2 = this.p;
        if (i2 == R.id.goods_switch) {
            i = 10;
        } else if (i2 == R.id.personalization_switch) {
            i = 12;
        } else {
            com.huawei.hwlogsmodel.LogUtil.h("ContentRecommendActivity", "savePersonalPrivacySettings else");
            i = -1;
        }
        com.huawei.hwlogsmodel.LogUtil.a("ContentRecommendActivity", "privacyId = ", Integer.valueOf(i), ", isOpen = ", Boolean.valueOf(z));
        this.f.e(i, z);
    }

    private void b(int i) {
        Intent intent = new Intent();
        intent.putExtra("requestCode", SleepBaseBarLineChartProvider.MSG_UPDATE_DATA_ORIGINAL_ICON);
        intent.putExtra(CommonConstant.REALNAMERESULT.KEY_VERIFY_TYPE, "1");
        intent.setClassName(this.f10378a, "com.huawei.health.HuaweiLoginActivity");
        startActivityForResult(intent, i);
    }

    private void e(String str) {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this);
        builder.e(str);
        builder.czz_(R$string.sug_dialog_yes, new View.OnClickListener() { // from class: rie
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        e.setCanceledOnTouchOutside(false);
        e.show();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
