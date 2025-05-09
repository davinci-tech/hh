package com.huawei.pluginachievement.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.TextAppearanceSpan;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hiai.awareness.AwarenessConstants;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.impl.AchieveObserver;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.pluginachievement.manager.model.KakaRedeemInfo;
import com.huawei.pluginachievement.manager.model.PersonalData;
import com.huawei.pluginachievement.manager.model.UserAchieveWrapper;
import com.huawei.pluginachievement.ui.AchieveKakaExchangeRuleActivity;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.jah;
import defpackage.meh;
import defpackage.mle;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes9.dex */
public class AchieveKakaExchangeRuleActivity extends BaseActivity implements AchieveObserver {
    private meh c;
    private int b = 1;

    /* renamed from: a, reason: collision with root package name */
    private int f8386a = 1;
    private int d = AwarenessConstants.REGISTER_SUCCESS_CODE;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
        super.initViewTahiti();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.achieve_kaka_exchange_rule);
        clearBackgroundDrawable();
        meh c = meh.c((Context) this);
        this.c = c;
        c.b((AchieveObserver) this);
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(KakaConstants.EXCHANGE_REDEEM_INFO)) {
            KakaRedeemInfo kakaRedeemInfo = (KakaRedeemInfo) intent.getParcelableExtra(KakaConstants.EXCHANGE_REDEEM_INFO);
            if (kakaRedeemInfo != null) {
                this.b = kakaRedeemInfo.getDailyLimit();
                this.f8386a = kakaRedeemInfo.getExchangeProportion();
                this.d = kakaRedeemInfo.getRedeemableKaka();
            }
        } else {
            j();
        }
        c();
    }

    private void c() {
        String valueOf;
        String valueOf2;
        b(R.id.kk_exchange_rule_text1, getString(R.string._2130840842_res_0x7f020d0a, new Object[]{1}));
        a();
        b(R.id.kk_exchange_rule_text3, getString(R.string._2130840844_res_0x7f020d0c, new Object[]{2}));
        if (LanguageUtil.ao(this) || LanguageUtil.az(this)) {
            valueOf = String.valueOf(2.1d);
        } else {
            valueOf = UnitUtil.e(2.1d, 1, 1);
        }
        int i = this.f8386a;
        int i2 = i > 0 ? i : 1;
        int abs = i > 0 ? 1 : Math.abs(i);
        b(R.id.kk_exchange_rule_text4, getString(R.string._2130840845_res_0x7f020d0d, new Object[]{valueOf, Integer.valueOf(abs), Integer.valueOf(i2)}));
        b(R.id.kk_exchange_rule_text5, getString(R.string._2130840846_res_0x7f020d0e, new Object[]{Integer.valueOf(i2), Integer.valueOf(abs)}));
        if (LanguageUtil.ao(this) || LanguageUtil.az(this)) {
            valueOf2 = String.valueOf(2.2d);
        } else {
            valueOf2 = UnitUtil.e(2.2d, 1, 1);
        }
        b(R.id.kk_exchange_rule_text6, getString(R.string._2130840847_res_0x7f020d0f, new Object[]{valueOf2}));
        b(getString(R.string._2130840850_res_0x7f020d12), getString(R.string._2130840851_res_0x7f020d13, new Object[]{200}), R.id.kk_exchange_rule_text9);
        b(R.id.kk_exchange_rule_text11, getString(R.string._2130840852_res_0x7f020d14, new Object[]{200}));
        b(getString(R.string._2130840853_res_0x7f020d15), getString(R.string._2130840854_res_0x7f020d16, new Object[]{Integer.valueOf(this.b)}), R.id.kk_exchange_rule_text12);
        g();
        b(R.id.kk_exchange_rule_text16, getString(R.string._2130840856_res_0x7f020d18, new Object[]{3}));
        d();
    }

    private void a() {
        GRSManager.a(this).e("domainMVmall", new GrsQueryCallback() { // from class: com.huawei.pluginachievement.ui.AchieveKakaExchangeRuleActivity.3
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(final String str) {
                AchieveKakaExchangeRuleActivity.this.runOnUiThread(new Runnable() { // from class: com.huawei.pluginachievement.ui.AchieveKakaExchangeRuleActivity.3.4
                    @Override // java.lang.Runnable
                    public void run() {
                        AchieveKakaExchangeRuleActivity.this.b(R.id.kk_exchange_rule_text2, AchieveKakaExchangeRuleActivity.this.getString(R.string._2130840843_res_0x7f020d0b, new Object[]{str}));
                    }
                });
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.h("PLGACHIEVE_AchieveKakaExchangeRuleActivity", "onCallBackFail index = ", Integer.valueOf(i));
            }
        });
    }

    private void g() {
        Resources resources = getResources();
        int i = this.d;
        b(getString(R.string._2130840855_res_0x7f020d17), getString(R.string._2130841016_res_0x7f020db8, new Object[]{UnitUtil.e(100.0d, 1, 0), UnitUtil.e(200.0d, 1, 0), UnitUtil.e(300.0d, 1, 0), UnitUtil.e(400.0d, 1, 0), UnitUtil.e(500.0d, 1, 0), resources.getQuantityString(R.plurals._2130903178_res_0x7f03008a, i, Integer.valueOf(i)), mle.b(0, 0)}), R.id.kk_exchange_rule_text14);
    }

    private void d() {
        String string = getString(R.string._2130840678_res_0x7f020c66);
        a(getString(R.string._2130840861_res_0x7f020d1d, new Object[]{string}), string, R.id.kk_exchange_rule_text17);
        String string2 = getString(R.string._2130840840_res_0x7f020d08);
        a(getString(R.string._2130840862_res_0x7f020d1e, new Object[]{string2}), string2, R.id.kk_exchange_rule_text18);
        b(R.id.kk_exchange_rule_text19, getString(R.string._2130840863_res_0x7f020d1f));
        b(R.id.kk_exchange_rule_text23, getString(R.string._2130841017_res_0x7f020db9, new Object[]{getString(R.string._2130840820_res_0x7f020cf4), getString(R.string._2130841014_res_0x7f020db6)}));
        b(R.id.kk_exchange_rule_text24, getString(R.string._2130841018_res_0x7f020dba, new Object[]{getString(R.string._2130840820_res_0x7f020cf4)}));
        b(R.id.kk_exchange_rule_text20, getString(R.string._2130840864_res_0x7f020d20));
        i();
    }

    private void i() {
        b(R.id.kk_exchange_rule_text21, getString(R.string._2130840865_res_0x7f020d21, new Object[]{String.format(Locale.ENGLISH, "%d", Integer.valueOf(KakaConstants.HOT_LINE)), UnitUtil.a(a(8), 1), UnitUtil.a(a(20), 1)}));
    }

    private Date a(int i) {
        Date date = new Date();
        date.setHours(i);
        date.setMinutes(0);
        return date;
    }

    private void b(String str, String str2, int i) {
        if (!LanguageUtil.j(this)) {
            str2 = "\u3000" + str2;
        }
        int length = str.length();
        int length2 = str2.length();
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(str + str2);
        spannableStringBuilder.setSpan(new TextAppearanceSpan(this, R.style.achieve_kaka_rule_detail_txt), length, length2 + length, 33);
        HealthTextView healthTextView = (HealthTextView) findViewById(i);
        if (healthTextView != null) {
            healthTextView.setText(spannableStringBuilder);
        }
    }

    private void a(String str, String str2, final int i) {
        int lastIndexOf = str.lastIndexOf(str2);
        if (lastIndexOf == -1) {
            LogUtil.b("PLGACHIEVE_AchieveKakaExchangeRuleActivity", "initRedeemedBrief not find linkName in brief");
            return;
        }
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new ClickableSpan() { // from class: com.huawei.pluginachievement.ui.AchieveKakaExchangeRuleActivity.1
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                int i2 = i;
                if (i2 == R.id.kk_exchange_rule_text17) {
                    AchieveKakaExchangeRuleActivity.this.b();
                } else if (i2 == R.id.kk_exchange_rule_text18) {
                    AchieveKakaExchangeRuleActivity.this.e();
                } else {
                    LogUtil.h("PLGACHIEVE_AchieveKakaExchangeRuleActivity", "initRedeemedBrief cannot find the view");
                }
                ViewClickInstrumentation.clickOnView(view);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setUnderlineText(false);
                textPaint.setColor(ContextCompat.getColor(AchieveKakaExchangeRuleActivity.this.getBaseContext(), R.color._2131296651_res_0x7f09018b));
            }
        }, lastIndexOf, str2.length() + lastIndexOf, 33);
        HealthTextView healthTextView = (HealthTextView) findViewById(i);
        if (healthTextView == null) {
            LogUtil.h("PLGACHIEVE_AchieveKakaExchangeRuleActivity", "initRedeemedBrief: briefView is null, " + i);
        } else {
            healthTextView.setText(spannableString);
            healthTextView.setMovementMethod(LinkMovementMethod.getInstance());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        Intent intent = new Intent();
        intent.setClassName(this, PersonalData.CLASS_NAME_PERSONAL_KAKA_DETAIL);
        Bundle bundle = new Bundle();
        bundle.putString("tag", "rule");
        intent.putExtra("tag", bundle);
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        String e = jah.c().e("domain_msale_vmall");
        if (TextUtils.isEmpty(e)) {
            LogUtil.h("PLGACHIEVE_AchieveKakaExchangeRuleActivity", "gotoPointsMall domainHost is empty!");
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(BaseApplication.d(), "com.huawei.operation.activity.WebViewActivity");
        intent.putExtra("url", e + KakaConstants.HUAWEI_PONITS_RULES_PATH);
        mle.ckk_(this, intent);
    }

    private void j() {
        HashMap hashMap = new HashMap(8);
        hashMap.put("type", "1");
        meh mehVar = this.c;
        if (mehVar != null) {
            mehVar.a(15, hashMap);
        }
    }

    @Override // com.huawei.pluginachievement.impl.AchieveObserver
    public void onDataChanged(int i, final UserAchieveWrapper userAchieveWrapper) {
        if (userAchieveWrapper == null) {
            LogUtil.b("PLGACHIEVE_AchieveKakaExchangeRuleActivity", "onDataChanged null");
            return;
        }
        if (userAchieveWrapper.getContentType() != 15) {
            LogUtil.b("PLGACHIEVE_AchieveKakaExchangeRuleActivity", "ContentType error");
            return;
        }
        String resultCode = userAchieveWrapper.getResultCode();
        if (!"0".equals(resultCode)) {
            LogUtil.b("PLGACHIEVE_AchieveKakaExchangeRuleActivity", "resultCode error:", resultCode);
        } else {
            runOnUiThread(new Runnable() { // from class: mig
                @Override // java.lang.Runnable
                public final void run() {
                    AchieveKakaExchangeRuleActivity.this.b(userAchieveWrapper);
                }
            });
        }
    }

    public /* synthetic */ void b(UserAchieveWrapper userAchieveWrapper) {
        d(userAchieveWrapper.getRedeemInfo());
    }

    private void d(KakaRedeemInfo kakaRedeemInfo) {
        if (kakaRedeemInfo == null) {
            LogUtil.a("PLGACHIEVE_AchieveKakaExchangeRuleActivity", "redeemInfo null");
            return;
        }
        LogUtil.a("PLGACHIEVE_AchieveKakaExchangeRuleActivity", "initParameterInfo: ", kakaRedeemInfo.toString());
        this.b = kakaRedeemInfo.getDailyLimit();
        this.f8386a = kakaRedeemInfo.getExchangeProportion();
        this.d = kakaRedeemInfo.getRedeemableKaka();
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, CharSequence charSequence) {
        HealthTextView healthTextView = (HealthTextView) findViewById(i);
        if (healthTextView != null) {
            healthTextView.setText(charSequence);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        meh mehVar = this.c;
        if (mehVar != null) {
            mehVar.c((AchieveObserver) this);
        }
        this.c = null;
    }
}
