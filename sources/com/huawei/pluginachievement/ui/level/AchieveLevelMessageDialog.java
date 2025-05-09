package com.huawei.pluginachievement.ui.level;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.autonavi.amap.mapcore.AMapEngineUtils;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ixx;
import defpackage.mcv;
import defpackage.mcx;
import defpackage.mfm;
import defpackage.mfp;
import defpackage.mjj;
import defpackage.mlc;
import defpackage.mlg;
import defpackage.nqf;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/* loaded from: classes9.dex */
public class AchieveLevelMessageDialog extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f8445a;
    private LinearLayout b;
    private ImageView c;
    private ImageView d;
    private HealthTextView e;
    private ImageView h;
    private ImageView i;
    private Context l;
    private HealthTextView m;
    private HealthTextView n;
    private HealthButton q;
    private String k = "";
    private String j = "";
    private HashMap<Integer, Integer> o = new HashMap<>(0);
    private HashMap<Integer, Integer> f = new HashMap<>(0);
    private int g = 1;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addFlags(AMapEngineUtils.HALF_MAX_P20_WIDTH);
        setContentView(R.layout.achieve_level_message_dialog);
        this.l = this;
        h();
        nqf.d(this.l);
        overridePendingTransition(R.anim._2130772016_res_0x7f010030, R.anim._2130772017_res_0x7f010031);
        a();
    }

    private void h() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundleExtra = intent.getBundleExtra("tag");
            if (bundleExtra != null) {
                int i = bundleExtra.getInt("level");
                this.g = i;
                if (i <= 1 || i > 20) {
                    finish();
                    return;
                }
                mlc.e(this.o);
                mlc.b(this.f);
                this.i = (ImageView) mfm.cgL_(this, R.id.achieve_level_animation_up_before);
                this.h = (ImageView) mfm.cgL_(this, R.id.achieve_level_animation_up_after);
                int intValue = this.o.get(Integer.valueOf(this.g - 1)).intValue();
                if (intValue != -1) {
                    this.i.setImageResource(intValue);
                }
                int intValue2 = this.o.get(Integer.valueOf(this.g)).intValue();
                if (intValue2 != -1) {
                    this.h.setImageResource(intValue2);
                }
                this.h.setVisibility(4);
                this.n = (HealthTextView) mfm.cgL_(this, R.id.achieve_level_dialog_date);
                String a2 = a(String.valueOf(System.currentTimeMillis()));
                this.k = a2;
                LogUtil.a("PLGACHIEVE_AchieveLevelMessageDialog", "level up time =", a2);
                String str = this.k;
                this.j = str;
                this.n.setText(str);
                HealthButton healthButton = (HealthButton) mfm.cgL_(this, R.id.achieve_level_dialog_share_button);
                this.q = healthButton;
                healthButton.setOnClickListener(this);
                this.m = (HealthTextView) mfm.cgL_(this, R.id.level_mgs);
                String format = String.format(Locale.ROOT, BaseApplication.getContext().getResources().getString(R.string._2130840776_res_0x7f020cc8), BaseApplication.getContext().getResources().getString(this.f.get(Integer.valueOf(this.g)).intValue()));
                e();
                this.m.setText(format);
                ImageView imageView = (ImageView) mfm.cgL_(this, R.id.achieve_level_dialog_title_ImageView);
                this.c = imageView;
                imageView.setOnClickListener(this);
                d();
                return;
            }
            finish();
            return;
        }
        finish();
    }

    private void d() {
        this.c.setVisibility(4);
        this.m.setVisibility(4);
        this.q.setVisibility(4);
        this.n.setVisibility(4);
        AnimationSet ciE_ = mjj.ciE_(200);
        ciE_.setStartOffset(500L);
        this.i.startAnimation(ciE_);
        ciE_.setAnimationListener(new Animation.AnimationListener() { // from class: com.huawei.pluginachievement.ui.level.AchieveLevelMessageDialog.1
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                AchieveLevelMessageDialog.this.b();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        this.i.setVisibility(8);
        this.h.setVisibility(0);
        AnimationSet ciF_ = mjj.ciF_(500, 300);
        this.h.startAnimation(ciF_);
        ciF_.setAnimationListener(new Animation.AnimationListener() { // from class: com.huawei.pluginachievement.ui.level.AchieveLevelMessageDialog.3
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                AchieveLevelMessageDialog.this.c();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.c.setVisibility(0);
        this.m.setVisibility(0);
        this.q.setVisibility(0);
        this.n.setVisibility(0);
        AnimationSet ciD_ = mjj.ciD_(1000);
        this.q.startAnimation(ciD_);
        this.m.startAnimation(ciD_);
        this.c.startAnimation(ciD_);
    }

    private void a() {
        HashMap hashMap = new HashMap(4);
        if (!Utils.o()) {
            hashMap.put("level", String.valueOf(this.g));
        }
        ixx.d().d(this.l, AnalyticsValue.LEVEL_MESSAGE_DIALOG_1100022.value(), hashMap, 0);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (R.id.achieve_level_dialog_title_ImageView == view.getId()) {
            finish();
        } else if (R.id.achieve_level_dialog_share_button == view.getId()) {
            i();
        } else {
            LogUtil.c("PLGACHIEVE_AchieveLevelMessageDialog", "onClick view is not matching");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return UnitUtil.a(new Date(Long.parseLong(str)), 20);
        } catch (NumberFormatException unused) {
            LogUtil.b("PLGACHIEVE_AchieveLevelMessageDialog", "setGainTime NumberFormatException");
            return "";
        }
    }

    private void e() {
        LinearLayout linearLayout = (LinearLayout) mfm.cgL_(this, R.id.achieve_level_share_ll);
        this.b = linearLayout;
        HealthTextView healthTextView = (HealthTextView) mfm.cgM_(linearLayout, R.id.achieve_level_share_date);
        this.f8445a = healthTextView;
        healthTextView.setText(this.j);
        ImageView imageView = (ImageView) mfm.cgM_(this.b, R.id.achieve_level_share_image);
        this.d = imageView;
        imageView.setImageResource(this.o.get(Integer.valueOf(this.g)).intValue());
        this.e = (HealthTextView) mfm.cgM_(this.b, R.id.achieve_level_share_text_level);
        this.e.setText(String.format(Locale.ROOT, BaseApplication.getContext().getResources().getString(R.string._2130840776_res_0x7f020cc8), BaseApplication.getContext().getResources().getString(this.f.get(Integer.valueOf(this.g)).intValue())));
    }

    private void i() {
        if (!mcx.d(this.l)) {
            mlg.e(this.l);
            return;
        }
        if (mcv.d(this.l).getAdapter() != null) {
            HashMap hashMap = new HashMap(4);
            hashMap.put("click", "1");
            if (!Utils.o()) {
                hashMap.put("level", String.valueOf(this.g));
            }
            Bitmap cgJ_ = mfp.cgJ_(this.b);
            if (cgJ_ != null) {
                mcx.cfN_(this.l, cgJ_, AnalyticsValue.LEVEL_SHARE_1100023.value(), hashMap);
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
