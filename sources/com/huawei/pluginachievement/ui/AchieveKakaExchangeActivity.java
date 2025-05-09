package com.huawei.pluginachievement.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.impl.AchieveObserver;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.pluginachievement.manager.model.KakaRedeemInfo;
import com.huawei.pluginachievement.manager.model.KakaRedeemResult;
import com.huawei.pluginachievement.manager.model.PersonalData;
import com.huawei.pluginachievement.manager.model.ResultCode;
import com.huawei.pluginachievement.manager.model.UserAchieveWrapper;
import com.huawei.pluginachievement.ui.AchieveKakaExchangeActivity;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.ixx;
import defpackage.mcx;
import defpackage.meh;
import defpackage.mle;
import defpackage.mmc;
import defpackage.nrr;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes.dex */
public class AchieveKakaExchangeActivity extends BaseActivity implements View.OnClickListener, AchieveObserver {

    /* renamed from: a, reason: collision with root package name */
    private meh f8382a;
    private HealthTextView aa;
    private RelativeLayout ab;
    private HealthRadioButton ac;
    private CustomTextAlertDialog ad;
    private LinearLayout ae;
    private HealthRadioButton af;
    private HealthRadioButton ag;
    private HealthRadioButton ah;
    private HealthRadioButton ai;
    private RelativeLayout aj;
    private NoTitleCustomAlertDialog ak;
    private RelativeLayout al;
    private Integer am;
    private NoTitleCustomAlertDialog an;
    private RelativeLayout ao;
    private int ap;
    private RelativeLayout ar;
    private RelativeLayout as;
    private HealthButton av;
    private ImageView aw;
    private int b;
    private LinearLayout c;
    private Context d;
    private HealthDivider g;
    private HealthTextView h;
    private HealthDivider i;
    private HealthTextView k;
    private HealthButton l;
    private HealthTextView m;
    private HealthTextView n;
    private HealthDivider o;
    private HealthTextView p;
    private HealthTextView q;
    private HealthTextView r;
    private HealthTextView s;
    private HealthTextView t;
    private KakaRedeemInfo u;
    private LinearLayout v;
    private HealthTextView w;
    private HealthTextView x;
    private NoTitleCustomAlertDialog y;
    private int j = 2;
    private int z = 1;
    private int e = 0;
    private Dialog f = null;
    private SparseArray<HealthRadioButton> aq = new SparseArray<>(2);

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
        super.initViewTahiti();
        ViewGroup.LayoutParams layoutParams = ((HealthCardView) findViewById(R.id.vmall_image_card)).getLayoutParams();
        if (layoutParams == null || !nsn.ag(this.d)) {
            return;
        }
        HealthColumnSystem healthColumnSystem = new HealthColumnSystem(this.d, 1);
        layoutParams.width = ((b(this.d) - nrr.b(this.d)) - (healthColumnSystem.c() * 2)) / 2;
    }

    private int b(Context context) {
        if (context == null) {
            LogUtil.b("PLGACHIEVE_AchieveKakaExchangeActivity", "getWindowWidth() context is null!!");
            return 0;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.achievement_kaka_exchange_point);
        getWindow().getDecorView().setBackgroundResource(R.color._2131299296_res_0x7f090be0);
        this.d = this;
        k();
        meh c = meh.c(BaseApplication.e());
        this.f8382a = c;
        c.b((AchieveObserver) this);
        h();
        c();
    }

    private void k() {
        ((LinearLayout) findViewById(R.id.kaka_exchange_tips)).setOnClickListener(this);
        this.x = (HealthTextView) findViewById(R.id.my_kaka_value);
        this.aa = (HealthTextView) findViewById(R.id.kaka_exchange_points_value);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.kaka_exchange_tips_text);
        this.h = healthTextView;
        healthTextView.setVisibility(8);
        ImageView imageView = (ImageView) findViewById(R.id.vmall_image);
        this.aw = imageView;
        imageView.setOnClickListener(this);
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.achieve_exchange_network_error);
        this.ab = relativeLayout;
        HealthButton healthButton = (HealthButton) relativeLayout.findViewById(R.id.btn_no_net_work);
        this.av = healthButton;
        healthButton.setOnClickListener(this);
        this.c = (LinearLayout) findViewById(R.id.achieve_kaka_exchange_content);
        HealthButton healthButton2 = (HealthButton) findViewById(R.id.kaka_exchange_points_button);
        this.l = healthButton2;
        healthButton2.setOnClickListener(this);
        j();
        d(R.drawable.button_background_emphasize_disable, false);
        if (!mcx.d(this.d)) {
            this.ab.setVisibility(0);
            this.c.setVisibility(8);
            this.ab.setOnClickListener(this);
        }
        initViewTahiti();
        ((HealthSubHeader) findViewById(R.id.mall_title)).setSubHeaderBackgroundColor(ContextCompat.getColor(this, R.color._2131296971_res_0x7f0902cb));
    }

    private void j() {
        this.ae = (LinearLayout) findViewById(R.id.remain_layout);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.exchange_remain_tip);
        this.v = linearLayout;
        linearLayout.setOnClickListener(this);
        this.k = (HealthTextView) findViewById(R.id.remian_digit_1);
        this.n = (HealthTextView) findViewById(R.id.remian_digit_2);
        this.s = (HealthTextView) findViewById(R.id.remian_digit_3);
        this.t = (HealthTextView) findViewById(R.id.remian_digit_4);
        this.r = (HealthTextView) findViewById(R.id.remian_digit_5);
        this.p = (HealthTextView) findViewById(R.id.remian_digit_6);
        this.q = (HealthTextView) findViewById(R.id.remian_digit_7);
        this.w = (HealthTextView) findViewById(R.id.remian_digit_8);
        Typeface createFromAsset = Typeface.createFromAsset(this.d.getAssets(), "font/HarmonyOSCondensedClockProportional-Medium.ttf");
        this.k.setTypeface(createFromAsset);
        this.n.setTypeface(createFromAsset);
        this.s.setTypeface(createFromAsset);
        this.t.setTypeface(createFromAsset);
        this.r.setTypeface(createFromAsset);
        this.p.setTypeface(createFromAsset);
        this.q.setTypeface(createFromAsset);
        this.w.setTypeface(createFromAsset);
    }

    private void h() {
        if (!mcx.d(this.d)) {
            q();
            return;
        }
        this.ab.setVisibility(8);
        this.c.setVisibility(0);
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.pluginachievement.ui.AchieveKakaExchangeActivity.2
            @Override // java.lang.Runnable
            public void run() {
                AchieveKakaExchangeActivity.this.n();
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() == R.id.kaka_exchange_tips) {
            g();
            d();
        } else if (view.getId() == R.id.kaka_exchange_points_button) {
            if (!mcx.d(this.d)) {
                q();
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            t();
        } else if (view.getId() == R.id.achieve_exchange_network_error) {
            h();
        } else if (view.getId() == R.id.btn_no_net_work) {
            i();
        } else if (view.getId() == R.id.vmall_image) {
            f();
        } else if (view.getId() == R.id.exchange_remain_tip) {
            this.an = a(R.string._2130841069_res_0x7f020ded, this.an);
        } else {
            cha_(view);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private NoTitleCustomAlertDialog a(int i, NoTitleCustomAlertDialog noTitleCustomAlertDialog) {
        LogUtil.a("PLGACHIEVE_AchieveKakaExchangeActivity", "showRemainTipDialog");
        String string = this.d.getResources().getString(R.string._2130840841_res_0x7f020d09);
        String string2 = this.d.getResources().getString(i, string);
        if (noTitleCustomAlertDialog == null) {
            noTitleCustomAlertDialog = new NoTitleCustomAlertDialog.Builder(this.d).czx_(chc_(string2, string)).czB_(R.string._2130840732_res_0x7f020c9c, R.color._2131298666_res_0x7f09096a, new View.OnClickListener() { // from class: com.huawei.pluginachievement.ui.AchieveKakaExchangeActivity.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    AchieveKakaExchangeActivity.this.a();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).e();
            noTitleCustomAlertDialog.setCancelable(false);
        }
        if (!isFinishing() || !isDestroyed()) {
            noTitleCustomAlertDialog.show();
        }
        return noTitleCustomAlertDialog;
    }

    private void t() {
        if (this.f == null) {
            LogUtil.a("PLGACHIEVE_AchieveKakaExchangeActivity", "showKakaExchangeValueDialog()");
            Object systemService = this.d.getSystemService("layout_inflater");
            if (!(systemService instanceof LayoutInflater)) {
                return;
            }
            View inflate = ((LayoutInflater) systemService).inflate(R.layout.achieve_kaka_exchange_select_value_view, (ViewGroup) null);
            cgZ_(inflate);
            CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.d);
            builder.czg_(inflate).czc_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: com.huawei.pluginachievement.ui.AchieveKakaExchangeActivity.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).cze_(R$string.IDS_settings_button_ok, new View.OnClickListener() { // from class: com.huawei.pluginachievement.ui.AchieveKakaExchangeActivity.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    AchieveKakaExchangeActivity.this.b();
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            this.f = builder.e();
        }
        m();
        if (isFinishing()) {
            return;
        }
        this.f.show();
    }

    private void cgZ_(View view) {
        this.m = (HealthTextView) view.findViewById(R.id.kaka_exc_dialog_title);
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.kaka_exchange_select_one);
        this.ar = relativeLayout;
        relativeLayout.setOnClickListener(this);
        RelativeLayout relativeLayout2 = (RelativeLayout) view.findViewById(R.id.kaka_exchange_select_two);
        this.ao = relativeLayout2;
        relativeLayout2.setOnClickListener(this);
        RelativeLayout relativeLayout3 = (RelativeLayout) view.findViewById(R.id.kaka_exchange_select_three);
        this.as = relativeLayout3;
        relativeLayout3.setOnClickListener(this);
        RelativeLayout relativeLayout4 = (RelativeLayout) view.findViewById(R.id.kaka_exchange_select_four);
        this.al = relativeLayout4;
        relativeLayout4.setOnClickListener(this);
        RelativeLayout relativeLayout5 = (RelativeLayout) view.findViewById(R.id.kaka_exchange_select_five);
        this.aj = relativeLayout5;
        relativeLayout5.setOnClickListener(this);
        this.ag = (HealthRadioButton) view.findViewById(R.id.kaka_exchange_value_button_one);
        this.ai = (HealthRadioButton) view.findViewById(R.id.kaka_exchange_value_button_two);
        this.ah = (HealthRadioButton) view.findViewById(R.id.kaka_exchange_value_button_three);
        this.af = (HealthRadioButton) view.findViewById(R.id.kaka_exchange_value_button_four);
        this.ac = (HealthRadioButton) view.findViewById(R.id.kaka_exchange_value_button_five);
        this.o = (HealthDivider) view.findViewById(R.id.kaka_exchange_picker_divide_line2);
        this.g = (HealthDivider) view.findViewById(R.id.kaka_exchange_picker_divide_line3);
        this.i = (HealthDivider) view.findViewById(R.id.kaka_exchange_picker_divide_line4);
        chb_(view, R.id.kaka_exchange_value_one, 100);
        chb_(view, R.id.kaka_exchange_value_two, 200);
        chb_(view, R.id.kaka_exchange_value_three, 300);
        chb_(view, R.id.kaka_exchange_value_four, 400);
        chb_(view, R.id.kaka_exchange_value_five, 500);
    }

    private void m() {
        String quantityString = getResources().getQuantityString(R.plurals._2130903180_res_0x7f03008c, this.j);
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(Locale.ROOT, quantityString, Integer.valueOf(this.j)));
        if (this.am != null) {
            String quantityString2 = getResources().getQuantityString(R.plurals._2130903192_res_0x7f030098, this.am.intValue());
            sb.append(System.lineSeparator());
            sb.append(String.format(Locale.ENGLISH, quantityString2, this.am));
        }
        this.m.setText(sb);
        int i = this.b / 100;
        if (i == 1 || i == 2) {
            this.o.setVisibility(8);
            this.as.setVisibility(8);
            this.g.setVisibility(8);
            this.al.setVisibility(8);
            this.i.setVisibility(8);
            this.aj.setVisibility(8);
            return;
        }
        if (i != 3) {
            if (i != 4) {
                return;
            }
            this.i.setVisibility(8);
            this.aj.setVisibility(8);
            return;
        }
        this.g.setVisibility(8);
        this.al.setVisibility(8);
        this.i.setVisibility(8);
        this.aj.setVisibility(8);
    }

    private void chb_(View view, int i, int i2) {
        ((HealthTextView) view.findViewById(i)).setText(UnitUtil.e(i2, 1, 0));
    }

    private void cha_(View view) {
        if (view.getId() == R.id.kaka_exchange_select_one) {
            d(this.ag, 100);
            return;
        }
        if (view.getId() == R.id.kaka_exchange_select_two) {
            d(this.ai, 200);
            return;
        }
        if (view.getId() == R.id.kaka_exchange_select_three) {
            d(this.ah, 300);
            return;
        }
        if (view.getId() == R.id.kaka_exchange_select_four) {
            d(this.af, 400);
        } else if (view.getId() == R.id.kaka_exchange_select_five) {
            d(this.ac, 500);
        } else {
            LogUtil.a("PLGACHIEVE_AchieveKakaExchangeActivity", "setDialogSelectStatus invalid");
        }
    }

    private void d(HealthRadioButton healthRadioButton, int i) {
        this.ap = i;
        LogUtil.a("PLGACHIEVE_AchieveKakaExchangeActivity", "refreshRadioButton mSelectValue = ", Integer.valueOf(i));
        HealthRadioButton healthRadioButton2 = this.aq.get(0);
        if (healthRadioButton2 != null) {
            this.aq.put(1, healthRadioButton2);
            healthRadioButton2.setChecked(false);
        }
        this.aq.put(0, healthRadioButton);
        healthRadioButton.setChecked(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        HashMap hashMap = new HashMap(4);
        hashMap.put("type", "1");
        meh mehVar = this.f8382a;
        if (mehVar != null) {
            mehVar.a(15, hashMap);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (!mcx.d(this.d)) {
            q();
            return;
        }
        LogUtil.a("PLGACHIEVE_AchieveKakaExchangeActivity", "exchangePoints mSelectValue = ", Integer.valueOf(this.ap));
        int i = this.ap;
        if (i == 0) {
            return;
        }
        if (i > this.e) {
            mmc.b().b(this.d, String.format(Locale.ROOT, getString(R.string._2130840821_res_0x7f020cf5), Integer.valueOf(this.ap)));
            b(0, 1);
            return;
        }
        HashMap hashMap = new HashMap(4);
        String e = mle.e();
        hashMap.put("type", "1");
        hashMap.put("timeZone", e);
        hashMap.put("kaka", String.valueOf(this.ap));
        meh mehVar = this.f8382a;
        if (mehVar != null) {
            mehVar.a(16, hashMap);
        }
    }

    @Override // com.huawei.pluginachievement.impl.AchieveObserver
    public void onDataChanged(int i, UserAchieveWrapper userAchieveWrapper) {
        if (userAchieveWrapper == null) {
            LogUtil.b("PLGACHIEVE_AchieveKakaExchangeActivity", "onDataChanged null");
            return;
        }
        int contentType = userAchieveWrapper.getContentType();
        if (contentType != 16 && contentType != 15) {
            LogUtil.b("PLGACHIEVE_AchieveKakaExchangeActivity", "ContentType error");
            return;
        }
        final String resultCode = userAchieveWrapper.getResultCode();
        if (!"0".equals(resultCode)) {
            LogUtil.b("PLGACHIEVE_AchieveKakaExchangeActivity", "resultCode error:", resultCode);
            if (contentType == 16) {
                KakaRedeemResult redeemResult = userAchieveWrapper.getRedeemResult();
                if (redeemResult == null) {
                    LogUtil.a("PLGACHIEVE_AchieveKakaExchangeActivity", "kakaRedeemResult null");
                    return;
                }
                this.am = redeemResult.getRemainingKaka();
            }
            runOnUiThread(new Runnable() { // from class: mia
                @Override // java.lang.Runnable
                public final void run() {
                    AchieveKakaExchangeActivity.this.d(resultCode);
                }
            });
            return;
        }
        LogUtil.a("PLGACHIEVE_AchieveKakaExchangeActivity", "onDataChanged type: ", Integer.valueOf(contentType));
        if (contentType == 16) {
            final KakaRedeemResult redeemResult2 = userAchieveWrapper.getRedeemResult();
            if (redeemResult2 == null) {
                LogUtil.a("PLGACHIEVE_AchieveKakaExchangeActivity", "kakaRedeemResult null");
                return;
            } else {
                runOnUiThread(new Runnable() { // from class: mhz
                    @Override // java.lang.Runnable
                    public final void run() {
                        AchieveKakaExchangeActivity.this.d(redeemResult2);
                    }
                });
                return;
            }
        }
        final KakaRedeemInfo redeemInfo = userAchieveWrapper.getRedeemInfo();
        if (redeemInfo == null) {
            LogUtil.a("PLGACHIEVE_AchieveKakaExchangeActivity", "kakaRedeemInfo null");
        } else {
            runOnUiThread(new Runnable() { // from class: mib
                @Override // java.lang.Runnable
                public final void run() {
                    AchieveKakaExchangeActivity.this.c(redeemInfo);
                }
            });
        }
    }

    public /* synthetic */ void d(String str) {
        a(str);
        b(0, 1);
        l();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void c(KakaRedeemInfo kakaRedeemInfo) {
        LogUtil.a("PLGACHIEVE_AchieveKakaExchangeActivity", "kakaRedeemInfo: ", kakaRedeemInfo.toString());
        this.e = kakaRedeemInfo.getKakaSum();
        int dailyLimit = kakaRedeemInfo.getDailyLimit();
        this.j = dailyLimit;
        this.u = kakaRedeemInfo;
        boolean z = dailyLimit - kakaRedeemInfo.getRedeemTimes() > 0;
        boolean z2 = this.e >= 200;
        this.z = kakaRedeemInfo.getExchangeProportion();
        o();
        this.am = kakaRedeemInfo.getRemainingKaka();
        b(z, z2);
    }

    private void o() {
        int i = this.z;
        int i2 = this.e;
        this.b = ((i > 0 ? i2 / i : i2 * Math.abs(i)) / 100) * 100;
        this.x.setText(String.valueOf(UnitUtil.e(this.e, 1, 0)));
        this.aa.setText(String.valueOf(UnitUtil.e(this.b, 1, 0)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void d(KakaRedeemResult kakaRedeemResult) {
        LogUtil.a("PLGACHIEVE_AchieveKakaExchangeActivity", "kakaRedeemInfo: ", kakaRedeemResult.toString());
        this.e = kakaRedeemResult.getKakaSum();
        boolean z = this.j - kakaRedeemResult.getRedeemTimes() > 0;
        boolean z2 = this.e >= 200;
        this.am = kakaRedeemResult.getRemainingKaka();
        b(z, z2);
        o();
        r();
    }

    private void b(boolean z, boolean z2) {
        l();
        LogUtil.a("PLGACHIEVE_AchieveKakaExchangeActivity", "refreshExchangeStatus: ", Boolean.valueOf(z), "isRedeemSum", Boolean.valueOf(z2));
        if (!z || !z2) {
            d(R.drawable.button_background_emphasize_disable, false);
            this.h.setVisibility(0);
            if (!z) {
                this.h.setText(getString(R.string._2130840823_res_0x7f020cf7));
                return;
            } else {
                if (z2) {
                    return;
                }
                this.h.setText(String.format(Locale.ROOT, getString(R.string._2130840821_res_0x7f020cf5), 200));
                return;
            }
        }
        d(R.drawable.button_background_emphasize, true);
    }

    private void d(int i, boolean z) {
        this.l.setBackground(ContextCompat.getDrawable(this.d, i));
        this.l.setClickable(z);
    }

    private void q() {
        LogUtil.a("PLGACHIEVE_AchieveKakaExchangeActivity", "showNetworkErrorDialog()");
        a();
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.d);
        builder.b(R.string._2130840731_res_0x7f020c9b).d(R.string._2130840733_res_0x7f020c9d).cyU_(R.string._2130840732_res_0x7f020c9c, new View.OnClickListener() { // from class: com.huawei.pluginachievement.ui.AchieveKakaExchangeActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AchieveKakaExchangeActivity.this.a();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        this.ad = a2;
        a2.setCancelable(true);
        if (isFinishing()) {
            return;
        }
        this.ad.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        CustomTextAlertDialog customTextAlertDialog = this.ad;
        if (customTextAlertDialog == null || !customTextAlertDialog.isShowing()) {
            return;
        }
        this.ad.dismiss();
    }

    private void r() {
        LogUtil.a("PLGACHIEVE_AchieveKakaExchangeActivity", "showExchangeSuccessDialog");
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.d).e(String.format(Locale.ROOT, this.d.getResources().getQuantityString(R.plurals._2130903179_res_0x7f03008b, this.ap), Integer.valueOf(this.ap))).czB_(R.string._2130840824_res_0x7f020cf8, R.color._2131298666_res_0x7f09096a, new View.OnClickListener() { // from class: com.huawei.pluginachievement.ui.AchieveKakaExchangeActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AchieveKakaExchangeActivity.this.y.dismiss();
                AchieveKakaExchangeActivity.this.f();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czy_(R$string.IDS_settings_button_cancal, R.color._2131298666_res_0x7f09096a, new View.OnClickListener() { // from class: com.huawei.pluginachievement.ui.AchieveKakaExchangeActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                AchieveKakaExchangeActivity.this.y.dismiss();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        this.y = e;
        e.setCancelable(false);
        if (!isFinishing() || !isDestroyed()) {
            this.y.show();
        }
        b(1, this.ap);
    }

    private void a(String str) {
        char c;
        String string;
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == -1948319258) {
            if (str.equals(ResultCode.KAKA_EXCHANGE_SUM_NO_ENOUGH)) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != -1948319166) {
            if (hashCode == 50424247 && str.equals("50002")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str.equals(ResultCode.KAKA_EXCHANGE_REMAIN_NO_ENOUGH)) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            string = getString(R.string._2130840823_res_0x7f020cf7);
        } else if (c == 1) {
            this.ak = a(R.string._2130841015_res_0x7f020db7, this.ak);
            string = "";
        } else if (c == 2) {
            string = String.format(Locale.ROOT, getString(R.string._2130840821_res_0x7f020cf5), Integer.valueOf(this.ap));
        } else {
            string = getString(R.string._2130840733_res_0x7f020c9d);
        }
        if (TextUtils.isEmpty(string)) {
            return;
        }
        mmc.b().b(this.d, string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (!mcx.d(this.d)) {
            q();
        } else {
            mle.b("&cid=181365");
            e();
        }
    }

    private void i() {
        CommonUtil.q(this.d);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        meh mehVar = this.f8382a;
        if (mehVar != null) {
            mehVar.c((AchieveObserver) this);
        }
        this.f8382a = null;
    }

    private void c() {
        HashMap hashMap = new HashMap(1);
        String value = AnalyticsValue.HEALTH_KAKA_EXCHANGE_ENTER_2040074.value();
        hashMap.put("click", 1);
        ixx.d().d(this.d, value, hashMap, 0);
    }

    private void b(int i, int i2) {
        HashMap hashMap = new HashMap(2);
        hashMap.put("type", Integer.valueOf(i));
        hashMap.put("value", Integer.valueOf(i2));
        ixx d = ixx.d();
        LogUtil.c("PLGACHIEVE_AchieveKakaExchangeActivity", "doBiKakaExchangeResults type = ", Integer.valueOf(i), " value = ", Integer.valueOf(i2), " mSelectValue =", Integer.valueOf(this.ap));
        d.d(this.d, AnalyticsValue.HEALTH_KAKA_EXCHANGE_RESULTS_2040075.value(), hashMap, 0);
    }

    private void e() {
        HashMap hashMap = new HashMap(1);
        String value = AnalyticsValue.HEALTH_KAKA_EXCHANGE_VMALL_2040076.value();
        hashMap.put("click", 1);
        ixx.d().d(this.d, value, hashMap, 0);
    }

    private void d() {
        HashMap hashMap = new HashMap(1);
        String value = AnalyticsValue.HEALTH_KAKA_EXCHANGE_RULE_2040077.value();
        hashMap.put("click", "1");
        ixx.d().d(this.d, value, hashMap, 0);
    }

    private SpannableString chc_(String str, String str2) {
        int lastIndexOf = str.lastIndexOf(str2);
        SpannableString spannableString = new SpannableString(str);
        if (lastIndexOf == -1) {
            LogUtil.h("PLGACHIEVE_AchieveKakaExchangeActivity", "splicingString not find linkName in originalStr");
            return spannableString;
        }
        spannableString.setSpan(new ClickableSpan() { // from class: com.huawei.pluginachievement.ui.AchieveKakaExchangeActivity.7
            @Override // android.text.style.ClickableSpan
            public void onClick(View view) {
                AchieveKakaExchangeActivity.this.g();
                if (AchieveKakaExchangeActivity.this.an != null && AchieveKakaExchangeActivity.this.an.isShowing()) {
                    AchieveKakaExchangeActivity.this.an.dismiss();
                }
                if (AchieveKakaExchangeActivity.this.ak != null && AchieveKakaExchangeActivity.this.ak.isShowing()) {
                    AchieveKakaExchangeActivity.this.ak.dismiss();
                }
                ViewClickInstrumentation.clickOnView(view);
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setUnderlineText(false);
                textPaint.setColor(ContextCompat.getColor(AchieveKakaExchangeActivity.this.getBaseContext(), R.color._2131296651_res_0x7f09018b));
            }
        }, lastIndexOf, str2.length() + lastIndexOf, 33);
        return spannableString;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        Intent intent = new Intent();
        intent.putExtra(KakaConstants.EXCHANGE_REDEEM_INFO, this.u);
        intent.setClassName(this.d, PersonalData.CLASS_NAME_PERSONAL_KAKA_EXCHANGE_POINT_RULE);
        this.d.startActivity(intent);
    }

    private void l() {
        if (this.am != null) {
            this.ae.setVisibility(0);
            b(this.am.intValue());
        } else {
            this.ae.setVisibility(8);
        }
    }

    private void b(int i) {
        String e;
        LogUtil.a("PLGACHIEVE_AchieveKakaExchangeActivity", "showRemainDigit remainKaka = ", Integer.valueOf(i));
        int length = String.valueOf(i).toCharArray().length;
        if (length > 6) {
            this.q.setVisibility(0);
            this.w.setVisibility(0);
        } else {
            this.q.setVisibility(8);
            this.w.setVisibility(8);
        }
        ArrayList arrayList = new ArrayList(8);
        for (int i2 = 0; i2 < 8; i2++) {
            if (i2 < length) {
                e = UnitUtil.e(Character.getNumericValue(r10[(length - i2) - 1]), 1, 0);
            } else {
                e = UnitUtil.e(0.0d, 1, 0);
            }
            arrayList.add(e);
        }
        this.k.setText((CharSequence) arrayList.get(0));
        this.n.setText((CharSequence) arrayList.get(1));
        this.s.setText((CharSequence) arrayList.get(2));
        this.t.setText((CharSequence) arrayList.get(3));
        this.r.setText((CharSequence) arrayList.get(4));
        this.p.setText((CharSequence) arrayList.get(5));
        this.q.setText((CharSequence) arrayList.get(6));
        this.w.setText((CharSequence) arrayList.get(7));
    }
}
