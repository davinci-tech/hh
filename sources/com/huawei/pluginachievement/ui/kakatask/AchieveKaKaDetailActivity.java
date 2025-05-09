package com.huawei.pluginachievement.ui.kakatask;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.framework.network.download.internal.constants.ExceptionCode;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.pluginachievement.impl.AchieveObserver;
import com.huawei.pluginachievement.manager.db.IAchieveDBMgr;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.pluginachievement.manager.model.KakaDelayInfo;
import com.huawei.pluginachievement.manager.model.KakaLineRecord;
import com.huawei.pluginachievement.manager.model.PersonalData;
import com.huawei.pluginachievement.manager.model.ResultCode;
import com.huawei.pluginachievement.manager.model.UserAchieveWrapper;
import com.huawei.pluginachievement.ui.adapter.AchieveKaKaAdapter;
import com.huawei.pluginachievement.ui.kakatask.AchieveKaKaDetailActivity;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.jah;
import defpackage.mcv;
import defpackage.mcz;
import defpackage.meh;
import defpackage.mer;
import defpackage.mfm;
import defpackage.mfn;
import defpackage.mle;
import defpackage.mmb;
import defpackage.nrh;
import defpackage.nrt;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* loaded from: classes.dex */
public class AchieveKaKaDetailActivity extends BaseActivity implements View.OnClickListener, AchieveObserver {

    /* renamed from: a, reason: collision with root package name */
    private ListView f8437a;
    private CustomTitleBar ab;
    private HealthScrollView d;
    private HealthTextView e;
    private HealthButton g;
    private HealthTextView i;
    private boolean j;
    private ExecutorService k;
    private HealthCardView l;
    private View m;
    private KakaLineRecord q;
    private KakaDelayInfo r;
    private AchieveKaKaAdapter s;
    private LinearLayout t;
    private HealthButton u;
    private HealthTextView v;
    private meh w;
    private RelativeLayout x;
    private LinearLayout y;
    private boolean p = false;
    private boolean n = false;
    private int c = 0;
    private int f = 0;
    private int h = 0;
    private int o = 2;
    private Handler b = new Handler() { // from class: com.huawei.pluginachievement.ui.kakatask.AchieveKaKaDetailActivity.3
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            LogUtil.a("PLGACHIEVE_AchieveKaKaDetailActivity", "mHanlder, case :", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 1) {
                if (AchieveKaKaDetailActivity.this.k == null || AchieveKaKaDetailActivity.this.k.isShutdown()) {
                    LogUtil.a("PLGACHIEVE_AchieveKaKaDetailActivity", "mExecutor is shutdown");
                    AchieveKaKaDetailActivity.this.k = Executors.newSingleThreadExecutor();
                }
                AchieveKaKaDetailActivity.this.k.execute(new Runnable() { // from class: com.huawei.pluginachievement.ui.kakatask.AchieveKaKaDetailActivity.3.2
                    @Override // java.lang.Runnable
                    public void run() {
                        AchieveKaKaDetailActivity.this.a();
                        AchieveKaKaDetailActivity.this.j = false;
                    }
                });
                AchieveKaKaDetailActivity.this.d();
            } else if (i == 26) {
                AchieveKaKaDetailActivity.this.l.setVisibility(8);
                MessageCenterApi messageCenterApi = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
                if (messageCenterApi != null) {
                    messageCenterApi.setMessageRead("17");
                }
                AchieveKaKaDetailActivity achieveKaKaDetailActivity = AchieveKaKaDetailActivity.this;
                achieveKaKaDetailActivity.b(achieveKaKaDetailActivity.getString(R.string._2130849001_res_0x7f022ce9));
                AchieveKaKaDetailActivity.this.v();
            } else if (i != 1000) {
                switch (i) {
                    case 1102:
                        AchieveKaKaDetailActivity.this.p();
                        break;
                    case 1103:
                        AchieveKaKaDetailActivity.this.j = true;
                        AchieveKaKaDetailActivity.this.o();
                        break;
                    case ExceptionCode.CHECK_FILE_HASH_FAILED /* 1104 */:
                        AchieveKaKaDetailActivity.this.e();
                        AchieveKaKaDetailActivity.this.e.setVisibility(0);
                        break;
                }
            } else {
                AchieveKaKaDetailActivity.this.b(message.obj + "");
                LogUtil.a("PLGACHIEVE_AchieveKaKaDetailActivity", "ERROR_TIP :", message.obj);
            }
            super.handleMessage(message);
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.achieve_kaka_detail);
        clearBackgroundDrawable();
        this.k = Executors.newSingleThreadExecutor();
        m();
    }

    private void m() {
        this.ab = (CustomTitleBar) mfm.cgL_(this, R.id.title);
        this.d = (HealthScrollView) mfm.cgL_(this, R.id.kk_rlayout);
        this.x = (RelativeLayout) mfm.cgL_(this, R.id.record_layout);
        this.e = (HealthTextView) mfm.cgL_(this, R.id.kk_text_no_tip);
        this.f8437a = (ListView) mfm.cgL_(this, R.id.kk_listview);
        Bundle bundle = null;
        View inflate = LayoutInflater.from(this).inflate(R.layout.achieve_kaka_foot, (ViewGroup) null);
        this.m = inflate;
        this.y = (LinearLayout) mfm.cgM_(inflate, R.id.more_layout);
        this.v = (HealthTextView) mfm.cgM_(this.m, R.id.rule_read_more);
        this.y.setOnClickListener(this);
        ListView listView = (ListView) mfm.cgL_(this, R.id.kk_listview);
        this.f8437a = listView;
        listView.setOverScrollMode(2);
        this.s = new AchieveKaKaAdapter(this);
        this.f8437a.addFooterView(this.m);
        this.f8437a.setAdapter((ListAdapter) this.s);
        Drawable drawable = ContextCompat.getDrawable(this, nrt.a(this) ? R.drawable._2131431570_res_0x7f0b1092 : R.drawable._2131427842_res_0x7f0b0202);
        if (nrt.a(this) && drawable != null) {
            drawable.setAlpha(153);
        }
        this.v.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, drawable, (Drawable) null);
        this.t = (LinearLayout) findViewById(R.id.kk_detail_loading);
        o();
        Intent intent = getIntent();
        if (intent != null) {
            bundle = intent.getBundleExtra("tag");
            this.f = intent.getIntExtra("from", 0);
            this.h = intent.getIntExtra("totalNum", 0);
        }
        if (bundle != null) {
            LogUtil.a("PLGACHIEVE_AchieveKaKaDetailActivity", "initView tag =", "");
            String string = bundle.getString("tag");
            if (!TextUtils.isEmpty(string) && "rule".equals(string)) {
                f();
                r();
            } else if (!TextUtils.isEmpty(string) && JsbMapKeyNames.H5_TEXT_DETAIL.equals(string)) {
                q();
            } else {
                LogUtil.c("PLGACHIEVE_AchieveKaKaDetailActivity", "initView tag is not matching");
            }
            l();
        }
    }

    private void f() {
        if (this.w == null) {
            meh c = meh.c(getApplicationContext());
            this.w = c;
            c.b((AchieveObserver) this);
        }
        HashMap hashMap = new HashMap(2);
        hashMap.put("timestamp", "0");
        this.w.a(1, hashMap);
    }

    private void q() {
        this.ab.setTitleText(getString(R.string._2130839503_res_0x7f0207cf));
        this.ab.setTitleBarBackgroundColor(getColor(R.color._2131298689_res_0x7f090981));
        u();
        i();
        mle.b(4, 0, this.h);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            h();
            return false;
        }
        return super.onKeyDown(i, keyEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        this.ab.setTitleText(getString(R.string._2130840678_res_0x7f020c66));
        this.ab.setTitleBarBackgroundColor(getColor(R.color._2131296971_res_0x7f0902cb));
        this.ab.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.pluginachievement.ui.kakatask.AchieveKaKaDetailActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AchieveKaKaDetailActivity.this.h();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.d.setVisibility(0);
        this.x.setVisibility(8);
        k();
        mle.b(2, 0, this.h);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (this.c == 1) {
            this.c = 0;
            q();
            this.d.setVisibility(8);
            this.x.setVisibility(0);
            return;
        }
        finish();
    }

    private void l() {
        this.l = (HealthCardView) mfm.cgL_(this, R.id.delay_layout);
        this.i = (HealthTextView) mfm.cgL_(this, R.id.delay_content);
        this.u = (HealthButton) mfm.cgL_(this, R.id.use_btn);
        this.g = (HealthButton) mfm.cgL_(this, R.id.delay_btn);
        this.u.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.pluginachievement.ui.kakatask.AchieveKaKaDetailActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AchieveKaKaDetailActivity.this.n = true;
                mcv.d(BaseApplication.getContext()).n(AchieveKaKaDetailActivity.this);
                mle.a(2, AchieveKaKaDetailActivity.this.f);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.g.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.pluginachievement.ui.kakatask.AchieveKaKaDetailActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                mle.a(4, AchieveKaKaDetailActivity.this.f);
                AchieveKaKaDetailActivity.this.b();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        KakaDelayInfo kakaDelayInfo = this.r;
        if (kakaDelayInfo == null) {
            this.l.setVisibility(8);
            return;
        }
        if (kakaDelayInfo.getDelayRate() != 0) {
            int delayRate = this.r.getDelayRate();
            this.o = delayRate;
            LogUtil.a("PLGACHIEVE_AchieveKaKaDetailActivity", "dealKakaDelayDialog rate ", Integer.valueOf(delayRate));
            d(this.o);
        }
        if (this.r.getExpirationKaka() != 0) {
            this.l.setVisibility(0);
            mle.a(1, this.f);
            c(this.r);
            return;
        }
        this.l.setVisibility(8);
    }

    private void c(KakaDelayInfo kakaDelayInfo) {
        if (kakaDelayInfo == null) {
            LogUtil.h("PLGACHIEVE_AchieveKaKaDetailActivity", "initKakaDelayContent kakaDelayInfo is null.");
            return;
        }
        String str = kakaDelayInfo.getExpirationKaka() + getString(R.string._2130842559_res_0x7f0213bf);
        String c = mfn.c(this, kakaDelayInfo.getUpdateTime());
        String string = getString(R.string._2130840678_res_0x7f020c66);
        String string2 = getString(R.string._2130848999_res_0x7f022ce7, new Object[]{str, c, string});
        SpannableString spannableString = new SpannableString(string2);
        int indexOf = string2.indexOf(str);
        if (indexOf == -1) {
            LogUtil.b("PLGACHIEVE_AchieveKaKaDetailActivity", "initKakaDelayContent indexOne not find in brief");
            return;
        }
        spannableString.setSpan(new ForegroundColorSpan(BaseApplication.getContext().getColor(R.color._2131296651_res_0x7f09018b)), indexOf, str.length() + indexOf, 33);
        int indexOf2 = string2.indexOf(string);
        if (indexOf2 == -1) {
            LogUtil.b("PLGACHIEVE_AchieveKaKaDetailActivity", "initKakaDelayContent indexSec not find in brief");
            return;
        }
        spannableString.setSpan(new ClickableSpan() { // from class: com.huawei.pluginachievement.ui.kakatask.AchieveKaKaDetailActivity.1
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                AchieveKaKaDetailActivity.this.c = 1;
                AchieveKaKaDetailActivity.this.r();
                mle.a(3, AchieveKaKaDetailActivity.this.f);
                ViewClickInstrumentation.clickOnView(view);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setUnderlineText(false);
                textPaint.setColor(ContextCompat.getColor(AchieveKaKaDetailActivity.this.getBaseContext(), R.color._2131296651_res_0x7f09018b));
            }
        }, indexOf2, string.length() + indexOf2, 33);
        this.i.setText(spannableString);
        this.i.setHighlightColor(ContextCompat.getColor(this, android.R.color.transparent));
        this.i.setMovementMethod(LinkMovementMethod.getInstance());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        KakaDelayInfo kakaDelayInfo = this.r;
        if (kakaDelayInfo == null || kakaDelayInfo.getExpirationKaka() == 0 || this.r.getDelayRate() == 0) {
            LogUtil.h("PLGACHIEVE_AchieveKaKaDetailActivity", "alertDelayDialog mKakaDelayInfo is null or expirationKaka == 0.");
            return;
        }
        String string = getString(R.string._2130849003_res_0x7f022ceb, new Object[]{this.r.getExpirationKaka() + getString(R.string._2130842559_res_0x7f0213bf), ((int) Math.ceil((this.r.getExpirationKaka() * 1.0d) / this.r.getDelayRate())) + getString(R.string._2130842559_res_0x7f0213bf), mfn.c(this, mfn.c(this.r.getUpdateTime()))});
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this);
        builder.e(R.string._2130849002_res_0x7f022cea).c(string).cyo_(R.string._2130849000_res_0x7f022ce8, new DialogInterface.OnClickListener() { // from class: mju
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AchieveKaKaDetailActivity.this.ciT_(dialogInterface, i);
            }
        }).cyn_(R.string._2130845098_res_0x7f021daa, new DialogInterface.OnClickListener() { // from class: mjs
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                AchieveKaKaDetailActivity.this.ciU_(dialogInterface, i);
            }
        });
        builder.b().setTextColor(getColor(R.color._2131296651_res_0x7f09018b));
        builder.d().setTextColor(getColor(R.color._2131299088_res_0x7f090b10));
        builder.c().show();
    }

    public /* synthetic */ void ciT_(DialogInterface dialogInterface, int i) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("kakaExpire", String.valueOf(this.r.getExpirationKaka()));
        meh.c(getApplicationContext()).a(26, hashMap);
        mle.a(5, this.f);
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    public /* synthetic */ void ciU_(DialogInterface dialogInterface, int i) {
        LogUtil.a("PLGACHIEVE_AchieveKaKaDetailActivity", "dialog cancel");
        mle.a(6, this.f);
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    private void k() {
        String e;
        String e2;
        String e3;
        c(R.id.kk_rule_text1, getString(R.string._2130840826_res_0x7f020cfa, new Object[]{1}));
        String string = getString(R.string._2130840840_res_0x7f020d08);
        c(getString(R.string._2130840900_res_0x7f020d44, new Object[]{string}), string, R.id.kk_rule_text2);
        c(R.id.kk_rule_text3, getString(R.string._2130840828_res_0x7f020cfc, new Object[]{2}));
        if (t()) {
            e = String.valueOf(2.1d);
        } else {
            e = UnitUtil.e(2.1d, 1, 1);
        }
        c(R.id.kk_rule_task_text1, getString(R.string._2130840894_res_0x7f020d3e, new Object[]{e}));
        if (t()) {
            e2 = String.valueOf(2.3d);
        } else {
            e2 = UnitUtil.e(2.3d, 1, 1);
        }
        c(R.id.kk_rule_task_text2, getString(R.string._2130840830_res_0x7f020cfe));
        c(R.id.kk_rule_task_text3, getString(R.string._2130840831_res_0x7f020cff, new Object[]{e2}));
        if (t()) {
            e3 = String.valueOf(2.2d);
        } else {
            e3 = UnitUtil.e(2.2d, 1, 1);
        }
        c(R.id.kk_rule_task_text4, getString(R.string._2130840895_res_0x7f020d3f, new Object[]{e3}));
        n();
    }

    private void n() {
        c(R.id.kk_rule_text6, getString(R.string._2130840833_res_0x7f020d01, new Object[]{50, 1, 50, 1}));
        c(R.id.kk_rule_text7, getString(R.string._2130840834_res_0x7f020d02, new Object[]{500, 10}));
        c(R.id.kk_rule_text10, getString(R.string._2130840837_res_0x7f020d05, new Object[]{50, 50}));
        c(R.id.kk_rule_text11, getString(R.string._2130840838_res_0x7f020d06, new Object[]{3}));
        c(R.id.kk_rule_text12, getString(R.string._2130840857_res_0x7f020d19, new Object[]{30}));
        c(R.id.kk_health_rule_text13, getString(R.string._2130840873_res_0x7f020d29, new Object[]{20}));
        String string = getString(R.string._2130840841_res_0x7f020d09);
        c(getString(R.string._2130840858_res_0x7f020d1a, new Object[]{1, 1, 200, 200, string}), string, R.id.kk_rule_text13);
        c(R.id.kk_rule_text14, getString(R.string._2130840839_res_0x7f020d07, new Object[]{4}));
        d(this.o);
        c(R.id.kk_rule_text16, getString(R.string._2130840859_res_0x7f020d1b));
        c(R.id.kk_rule_text17, getString(R.string._2130840860_res_0x7f020d1c, new Object[]{Integer.valueOf(KakaConstants.HOT_LINE), "8:00", "20:00"}));
        c(R.id.kk_rule_text22, getString(R.string._2130840896_res_0x7f020d40, new Object[]{1, 2, 3, 5, 7, 9, 10, 10, 10}));
        c(R.id.kk_rule_text25, getString(R.string._2130840899_res_0x7f020d43, new Object[]{5, 30}));
    }

    private void d(int i) {
        c(R.id.kk_rule_text15, getString(R.string._2130841076_res_0x7f020df4, new Object[]{12, 12, 12, 31, 24, Integer.valueOf(i), 1, 1}));
    }

    private void u() {
        this.f8437a.setVisibility(8);
        this.t.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.f8437a.setVisibility(0);
        this.t.setVisibility(8);
    }

    private void c(String str, String str2, final int i) {
        int indexOf = str.indexOf(str2);
        if (indexOf == -1) {
            LogUtil.b("PLGACHIEVE_AchieveKaKaDetailActivity", "initKakaBrief not find linkName in brief");
            return;
        }
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new ClickableSpan() { // from class: com.huawei.pluginachievement.ui.kakatask.AchieveKaKaDetailActivity.7
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                int i2 = i;
                if (i2 == R.id.kk_rule_text2) {
                    AchieveKaKaDetailActivity.this.g();
                } else if (i2 == R.id.kk_rule_text13) {
                    AchieveKaKaDetailActivity.this.j();
                } else {
                    LogUtil.h("PLGACHIEVE_AchieveKaKaDetailActivity", "initKakaBrief cannot find the view");
                }
                ViewClickInstrumentation.clickOnView(view);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setUnderlineText(false);
                textPaint.setColor(ContextCompat.getColor(AchieveKaKaDetailActivity.this.getBaseContext(), R.color._2131296651_res_0x7f09018b));
            }
        }, indexOf, str2.length() + indexOf, 33);
        HealthTextView healthTextView = (HealthTextView) findViewById(i);
        healthTextView.setText(spannableString);
        healthTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void c(int i, CharSequence charSequence) {
        ((HealthTextView) findViewById(i)).setText(charSequence);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        String e = jah.c().e("domain_msale_vmall");
        if (TextUtils.isEmpty(e)) {
            LogUtil.h("PLGACHIEVE_AchieveKaKaDetailActivity", "gotoPointsMall host is empty");
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.operation.activity.WebViewActivity");
        intent.putExtra("url", e + KakaConstants.HUAWEI_PONITS_RULES_PATH);
        mle.ckk_(this, intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        Intent intent = new Intent();
        intent.setClassName(this, PersonalData.CLASS_NAME_PERSONAL_KAKA_EXCHANGE_POINT_RULE);
        startActivity(intent);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.n) {
            mle.b(4, 0, this.h);
            mle.a(1, this.f);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        mmb.d(this.k);
        KakaLineRecord kakaLineRecord = this.q;
        if (kakaLineRecord != null && kakaLineRecord.getKakaLineRecords() != null) {
            this.q.getKakaLineRecords().clear();
        }
        meh mehVar = this.w;
        if (mehVar != null) {
            mehVar.c((AchieveObserver) this);
        }
        this.w = null;
        Handler handler = this.b;
        if (handler != null) {
            handler.removeMessages(1);
            this.b.removeMessages(1102);
            this.b.removeMessages(1103);
        }
    }

    private void i() {
        this.w = meh.c(getApplicationContext());
        LogUtil.a("PLGACHIEVE_AchieveKaKaDetailActivity", "getData()");
        this.w.b((AchieveObserver) this);
        this.k.execute(new Runnable() { // from class: com.huawei.pluginachievement.ui.kakatask.AchieveKaKaDetailActivity.6
            @Override // java.lang.Runnable
            public void run() {
                AchieveKaKaDetailActivity.this.v();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        a();
        c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        HashMap hashMap = new HashMap(8);
        hashMap.put("page", String.valueOf(1));
        hashMap.put(IAchieveDBMgr.PARAM_PAGE_SIZE, String.valueOf(10));
        meh mehVar = this.w;
        mcz d = mehVar != null ? mehVar.d(6, hashMap) : null;
        if (d instanceof KakaLineRecord) {
            KakaLineRecord kakaLineRecord = (KakaLineRecord) d;
            this.q = kakaLineRecord;
            if (kakaLineRecord.getKakaLineRecords() != null) {
                d(1102, (int) null);
                return;
            }
        }
        if (this.p) {
            this.b.sendEmptyMessage(ExceptionCode.CHECK_FILE_HASH_FAILED);
        }
    }

    private void c() {
        mer b = mer.b(getApplicationContext());
        long d = b != null ? b.d(this.w) : 0L;
        HashMap hashMap = new HashMap(8);
        hashMap.put("timestamp", String.valueOf(d));
        meh mehVar = this.w;
        if (mehVar != null) {
            mehVar.a(1, hashMap);
        }
        LogUtil.a("PLGACHIEVE_AchieveKaKaDetailActivity", "doRefreshCloudKakaLine() timeKakaMax=", Long.valueOf(d));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void p() {
        int i;
        e();
        this.e.setVisibility(0);
        if (this.q == null) {
            return;
        }
        o();
        if (this.q.getKakaLineRecords() == null || this.q.getKakaLineRecords().size() == 0) {
            i = 0;
        } else {
            i = this.q.getKakaLineRecords().size();
            this.e.setVisibility(8);
            if (i % 10 == 0 && !this.j) {
                w();
            }
        }
        if (this.q.getTotalNum() == i) {
            o();
        }
        this.j = false;
        this.s.c(this.q.getKakaLineRecords());
        if (this.q.getKakaLineRecords() == null || i > 10) {
            return;
        }
        this.f8437a.setSelection(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> void d(int i, T t) {
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = t;
        this.b.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        nrh.d(BaseApplication.getContext(), str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        if (this.m.getVisibility() == 0) {
            this.y.setVisibility(8);
            this.m.setVisibility(8);
            View view = this.m;
            view.setPadding(0, -view.getHeight(), 0, 0);
        }
    }

    private void w() {
        if (this.m.getVisibility() != 0) {
            this.m.setVisibility(0);
            this.y.setVisibility(0);
            this.m.setPadding(0, 0, 0, 0);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        new HashMap(8).put("click", "1");
        if (R.id.more_layout == view.getId()) {
            s();
            mle.b(3, 0, this.h);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void s() {
        KakaLineRecord kakaLineRecord = this.q;
        if (kakaLineRecord == null || kakaLineRecord.getKakaLineRecords() == null) {
            this.j = true;
            o();
        } else {
            this.k.execute(new Runnable() { // from class: com.huawei.pluginachievement.ui.kakatask.AchieveKaKaDetailActivity.10
                @Override // java.lang.Runnable
                public void run() {
                    int size = (AchieveKaKaDetailActivity.this.q.getKakaLineRecords().size() / 10) + 1;
                    if (AchieveKaKaDetailActivity.this.q.getKakaLineRecords().size() % 10 == 0) {
                        size = AchieveKaKaDetailActivity.this.q.getKakaLineRecords().size() / 10;
                    }
                    HashMap hashMap = new HashMap(8);
                    hashMap.put("page", String.valueOf(size + 1));
                    hashMap.put(IAchieveDBMgr.PARAM_PAGE_SIZE, String.valueOf(10));
                    mcz d = AchieveKaKaDetailActivity.this.w != null ? AchieveKaKaDetailActivity.this.w.d(6, hashMap) : null;
                    if (!(d instanceof KakaLineRecord)) {
                        AchieveKaKaDetailActivity.this.d(1103, (int) null);
                        return;
                    }
                    KakaLineRecord kakaLineRecord2 = (KakaLineRecord) d;
                    if (kakaLineRecord2.getKakaLineRecords() != null) {
                        if (kakaLineRecord2.getKakaLineRecords().size() < 10) {
                            AchieveKaKaDetailActivity.this.j = true;
                        }
                        AchieveKaKaDetailActivity.this.q.getKakaLineRecords().addAll(kakaLineRecord2.getKakaLineRecords());
                        AchieveKaKaDetailActivity.this.d(1102, (int) null);
                    }
                }
            });
        }
    }

    @Override // com.huawei.pluginachievement.impl.AchieveObserver
    public void onDataChanged(int i, UserAchieveWrapper userAchieveWrapper) {
        LogUtil.a("PLGACHIEVE_AchieveKaKaDetailActivity", "onDataChanged error=", Integer.valueOf(i));
        if (i == -1) {
            LogUtil.a("PLGACHIEVE_AchieveKaKaDetailActivity", "showNetworkErrorDialog error=", Integer.valueOf(i));
            this.b.sendEmptyMessage(1001);
            return;
        }
        if (userAchieveWrapper == null) {
            return;
        }
        LogUtil.a("PLGACHIEVE_AchieveKaKaDetailActivity", "onDataChanged resultCode=", userAchieveWrapper.getResultCode());
        if (!"0".equals(userAchieveWrapper.getResultCode())) {
            c(userAchieveWrapper);
            return;
        }
        if (userAchieveWrapper.getContentType() == 1) {
            this.p = true;
            e(userAchieveWrapper.getKakaLineRecord());
            d(1, (int) null);
        } else if (userAchieveWrapper.getContentType() == 26) {
            d(26, (int) null);
        } else {
            LogUtil.c("PLGACHIEVE_AchieveKaKaDetailActivity", "onDataChanged content type:", Integer.valueOf(userAchieveWrapper.getContentType()));
        }
    }

    private void c(UserAchieveWrapper userAchieveWrapper) {
        String string;
        String resultCode = userAchieveWrapper.getResultCode();
        resultCode.hashCode();
        if (resultCode.equals("12030003")) {
            string = getString(R.string._2130849006_res_0x7f022cee);
        } else if (resultCode.equals(ResultCode.CODE_NOT_DELAY_NUM)) {
            string = getString(R.string._2130849007_res_0x7f022cef);
        } else {
            LogUtil.h("PLGACHIEVE_AchieveKaKaDetailActivity", "unknow error:", resultCode);
            string = null;
        }
        if (string != null) {
            d(1000, (int) string);
        }
    }

    private void e(KakaLineRecord kakaLineRecord) {
        if (kakaLineRecord != null) {
            this.r = kakaLineRecord.getKakaDelayInfo();
        }
    }

    private boolean t() {
        return LanguageUtil.ao(this) || LanguageUtil.az(this) || LanguageUtil.ad(this) || LanguageUtil.aj(this) || LanguageUtil.at(this) || LanguageUtil.bb(this) || LanguageUtil.av(this) || LanguageUtil.bd(this) || LanguageUtil.z(this) || LanguageUtil.an(this) || LanguageUtil.aw(this) || LanguageUtil.bo(this) || LanguageUtil.bm(this);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
