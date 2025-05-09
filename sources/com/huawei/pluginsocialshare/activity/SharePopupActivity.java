package com.huawei.pluginsocialshare.activity;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.fragment.app.Fragment;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.hihonor.assistant.cardmgrsdk.CardMgrSdkConst;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.application.BroadcastManager;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.socialshare.model.SaveBitampCallback;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.kit.awareness.status.CapabilityStatus;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginsocialshare.activity.SharePopupActivity;
import com.huawei.pluginsocialshare.view.EditShareCustomFragment;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.subtab.HealthSubTabWidget;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import com.huawei.uikit.hwsubtab.widget.HwSubTabWidget;
import com.huawei.watchface.mvp.model.latona.provider.WatchFaceProvider;
import defpackage.fdu;
import defpackage.fdz;
import defpackage.feh;
import defpackage.ixx;
import defpackage.jdx;
import defpackage.koq;
import defpackage.mto;
import defpackage.mue;
import defpackage.muw;
import defpackage.mvs;
import defpackage.mwa;
import defpackage.mwb;
import defpackage.mwd;
import defpackage.nqx;
import defpackage.nrf;
import defpackage.nrh;
import defpackage.nrt;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* loaded from: classes6.dex */
public class SharePopupActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private RelativeLayout f8517a;
    private CustomTitleBar aa;
    private SharedPreferences ab;
    private SystemLocaleChangeReceiver ac;
    private nqx ad;
    private HealthDivider af;
    private HealthSwitchButton ag;
    private RelativeLayout ah;
    private HealthViewPager ai;
    private ViewStub aj;
    private LinearLayout ak;
    private LinearLayout al;
    private LinearLayout am;
    private LinearLayout an;
    private LinearLayout ar;
    private Context b;
    private Animation c;
    private boolean d;
    private Animation e;
    private fdu f;
    private View h;
    private HealthTextView j;
    private int k;
    private boolean m;
    private boolean n;
    private mue p;
    private mto q;
    private ImageView r;
    private LinearLayout s;
    private fdu u;
    private LinearLayout v;
    private LinearLayout w;
    private RelativeLayout x;
    private LinearLayout y;
    private HealthSubTabWidget z;
    private boolean t = false;
    private boolean o = false;
    private boolean l = false;
    private int g = -1;
    private int ae = -1;
    private Handler i = new Handler() { // from class: com.huawei.pluginsocialshare.activity.SharePopupActivity.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message == null) {
                LogUtil.h("Share_SharePopupActivity", "receive empty msg");
            } else {
                if (message.what == 1) {
                    if (message.obj instanceof Bitmap) {
                        SharePopupActivity.this.r.setImageBitmap((Bitmap) message.obj);
                        return;
                    }
                    return;
                }
                LogUtil.h("Share_SharePopupActivity", "msg unknown");
            }
        }
    };

    private void cpi_(Activity activity) {
        View decorView = getWindow().getDecorView();
        int systemUiVisibility = decorView.getSystemUiVisibility();
        if (nrt.a(activity)) {
            decorView.setSystemUiVisibility((systemUiVisibility & (-8193)) | HealthData.WEIGHT);
        } else {
            decorView.setSystemUiVisibility(systemUiVisibility | 9984);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (CommonUtil.ba()) {
            getWindow().setNavigationBarColor(getResources().getColor(R.color._2131296937_res_0x7f0902a9));
            cpi_(this);
        }
        getWindow().setFormat(-2);
        this.b = this;
        if (!LoginInit.getInstance(this).getIsLogined()) {
            finish();
            return;
        }
        setContentView(R.layout.activity_share_main);
        cancelAdaptRingRegion();
        w();
        if (n()) {
            fdu d = mto.d();
            this.f = d;
            this.k = d.u();
            s();
            mwd.b(this.u);
            return;
        }
        LogUtil.b("Share_SharePopupActivity", "initShareData fail, finish");
        finish();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onMultiWindowModeChanged(boolean z, Configuration configuration) {
        super.onMultiWindowModeChanged(z, configuration);
        LogUtil.a("Share_SharePopupActivity", "onMultiWindowModeChanged isInMultiWindowMode = ", Boolean.valueOf(z));
        if (z) {
            finish();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (CommonUtil.ba()) {
            ab();
        }
        this.l = false;
    }

    @Override // android.app.Activity
    public void finish() {
        if (this.o) {
            LogUtil.a("Share_SharePopupActivity", "finish when is already finishing");
            return;
        }
        this.o = true;
        LogUtil.a("Share_SharePopupActivity", "start finish");
        y();
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.remove("androidx.lifecycle.BundlableSavedStateRegistry.key");
    }

    private void z() {
        boolean r = nsn.r();
        if (r) {
            a(R.id.activity_share_layout_trable, R.id.activity_share_layout_trable_inflated);
        } else {
            a(R.id.activity_share_layout, R.id.activity_share_layout_inflated);
        }
        View view = this.h;
        if (view != null) {
            this.am = (LinearLayout) view.findViewById(R.id.share_wechat_friends_layout);
            this.ak = (LinearLayout) this.h.findViewById(R.id.share_wechat_chat_layout);
            this.ar = (LinearLayout) this.h.findViewById(R.id.share_weibo_layout);
            this.al = (LinearLayout) this.h.findViewById(R.id.share_family_group_layout);
            this.y = (LinearLayout) this.h.findViewById(R.id.share_save_to_local_layout);
            this.an = (LinearLayout) this.h.findViewById(R.id.share_more_layout);
            this.w = (LinearLayout) this.h.findViewById(R.id.share_save_pdf_layout);
            this.v = (LinearLayout) this.h.findViewById(R.id.share_print_layout);
            if (r) {
                return;
            }
            int c = nsn.c(this.b, 16.0f);
            cph_(this.w, c);
            cph_(this.v, c);
            cph_(this.ak, c);
            cph_(this.am, c);
            cph_(this.ar, c);
            cph_(this.al, c);
            cph_(this.y, c);
            cph_(this.an, 0);
        }
    }

    private void a(int i, int i2) {
        if (this.aj == null) {
            this.aj = (ViewStub) findViewById(i);
        }
        if (this.aj.getParent() != null) {
            this.h = this.aj.inflate();
        } else {
            this.h = findViewById(i2);
        }
    }

    private void v() {
        setIntent(null);
        ImageView imageView = this.r;
        if (imageView != null) {
            imageView.setImageBitmap(null);
        }
        mto mtoVar = this.q;
        if (mtoVar != null) {
            mtoVar.e();
            this.q = null;
        }
        Handler handler = this.i;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.i = null;
        }
        RelativeLayout relativeLayout = this.ah;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(8);
        }
        HealthDivider healthDivider = this.af;
        if (healthDivider != null) {
            healthDivider.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        super.finish();
    }

    private void y() {
        boolean z = !BaseApplication.j() && this.o;
        v();
        if (this.f8517a == null || this.x == null || z) {
            d();
            return;
        }
        Animation loadAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim._2130772018_res_0x7f010032);
        this.c = loadAnimation;
        this.f8517a.startAnimation(loadAnimation);
        RelativeLayout relativeLayout = this.ah;
        if (relativeLayout != null) {
            relativeLayout.startAnimation(this.c);
        }
        if (this.t) {
            this.e = AnimationUtils.loadAnimation(getApplicationContext(), R.anim._2130772070_res_0x7f010066);
        } else {
            this.e = AnimationUtils.loadAnimation(getApplicationContext(), R.anim._2130772064_res_0x7f010060);
        }
        this.e.setFillAfter(true);
        this.x.startAnimation(this.e);
        this.e.setAnimationListener(new Animation.AnimationListener() { // from class: com.huawei.pluginsocialshare.activity.SharePopupActivity.3
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                SharePopupActivity.this.d();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ad() {
        v();
        LogUtil.a("Share_SharePopupActivity", "super.finish");
        super.finish();
    }

    private void h() {
        z();
        ImageView imageView = (ImageView) findViewById(R.id.share_watermark_icon);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.share_watermark_text);
        this.x = (RelativeLayout) findViewById(R.id.share_main);
        this.f8517a = (RelativeLayout) findViewById(R.id.dst_selector);
        this.s = (LinearLayout) findViewById(R.id.oversea_share_layout);
        this.r = (ImageView) findViewById(R.id.preview_img);
        imageView.setImageResource(R.mipmap._2131820756_res_0x7f1100d4);
        healthTextView.setText(R.string.IDS_app_name_health);
        cpe_(this.w).setImageResource(R.drawable.share_save_pdf_icon);
        cpe_(this.v).setImageResource(R.drawable.share_print_icon);
        cpe_(this.am).setImageResource(R.drawable.share_wechat_friends_icon);
        cpe_(this.ak).setImageResource(R.drawable.share_wechat_chat_icon);
        cpe_(this.ar).setImageResource(R.drawable.share_weibo_icon);
        cpe_(this.al).setImageResource(R.mipmap._2131821471_res_0x7f11039f);
        cpe_(this.an).setImageResource(R.drawable.share_more_icon);
        cpe_(this.y).setImageResource(R.drawable.share_save_local);
        cpf_(this.w).setText(R.string._2130850337_res_0x7f023221);
        cpf_(this.v).setText(R.string._2130850334_res_0x7f02321e);
        cpf_(this.am).setText(R.string._2130843622_res_0x7f0217e6);
        cpf_(this.ak).setText(R.string._2130843623_res_0x7f0217e7);
        cpf_(this.ar).setText(R.string._2130843624_res_0x7f0217e8);
        cpf_(this.al).setText(R.string.IDS_hwh_family_health_zone);
        cpf_(this.an).setText(R.string._2130844034_res_0x7f021982);
        cpf_(this.y).setText(R.string._2130842376_res_0x7f021308);
        k();
        aa();
        this.w.setOnClickListener(this);
        this.v.setOnClickListener(this);
        this.am.setOnClickListener(this);
        this.ak.setOnClickListener(this);
        this.y.setOnClickListener(this);
        this.ar.setOnClickListener(this);
        this.al.setOnClickListener(this);
        this.an.setOnClickListener(this);
    }

    private void aa() {
        if (this.u.u() == 7) {
            this.am.setVisibility(8);
            this.ak.setVisibility(8);
            this.al.setVisibility(8);
        }
        if (EnvironmentInfo.k()) {
            this.an.setVisibility(8);
            this.y.setVisibility(8);
        }
        int u = this.u.u();
        boolean z = true;
        this.ar.setVisibility(u == 2 || u == 0 || u == 7 || a(u) ? 8 : 0);
        if (u == 8 || u == 9 || a(u)) {
            this.am.setVisibility(8);
        }
        if (a(u)) {
            this.an.setVisibility(8);
        }
        if (u != 0 && u != 2 && u != 11 && (u != 10 || this.u.awm_() != null)) {
            z = false;
        }
        this.y.setVisibility(z ? 8 : 0);
        if (this.u.j() && this.u.u() != 7) {
            this.al.setVisibility(0);
        } else {
            this.al.setVisibility(8);
        }
        this.w.setVisibility(this.u.ad() ? 0 : 8);
        this.v.setVisibility(this.u.z() ? 0 : 8);
    }

    private boolean a(int i) {
        return i == 10 && this.u.awm_() != null;
    }

    private void k() {
        if (Utils.o() && this.u.u() != 7) {
            this.h.setVisibility(8);
            this.s.setVisibility(0);
            HealthButton healthButton = (HealthButton) findViewById(R.id.oversea_save_local_btn);
            HealthButton healthButton2 = (HealthButton) findViewById(R.id.oversea_share_btn);
            healthButton.setOnClickListener(this);
            healthButton2.setOnClickListener(this);
            return;
        }
        this.h.setVisibility(0);
        this.s.setVisibility(8);
    }

    private void cph_(LinearLayout linearLayout, int i) {
        if (linearLayout == null) {
            LogUtil.h("Share_SharePopupActivity", "setLayoutParams() layout is null.");
        } else if (linearLayout.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
            layoutParams.setMarginEnd(i);
            linearLayout.setLayoutParams(layoutParams);
        }
    }

    private boolean n() {
        fdu d = mto.d();
        this.u = d;
        if (d == null) {
            LogUtil.h("Share_SharePopupActivity", "mShareContent is null");
            return false;
        }
        boolean z = d.u() == 6;
        this.n = z;
        if (z) {
            Iterator<fdz> it = this.u.b().iterator();
            while (it.hasNext()) {
                fdz next = it.next();
                String e = next.e();
                if (!next.h() && next.aws_() == null && !mwa.d(e)) {
                    return false;
                }
                if (next.h()) {
                    next.a(this.u.aa());
                }
            }
        }
        m();
        return true;
    }

    private void s() {
        int i = this.b.getResources().getConfiguration().uiMode;
        if (mwd.e() != 0 && (i & 48) != mwd.e() && !this.n) {
            ad();
        }
        h();
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        this.f8517a.setLayoutAnimation(new LayoutAnimationController(AnimationUtils.loadAnimation(getApplicationContext(), R.anim._2130772019_res_0x7f010033)));
        int u = this.u.u();
        this.aa = (CustomTitleBar) findViewById(R.id.share_title_main_bar);
        t();
        if (u == 1 || u == 4 || u == 5 || (u == 10 && this.u.awm_() != null)) {
            p();
            this.t = true;
            q();
            if (!CommonUtil.bv() && !CommonUtil.as() && this.u.ac()) {
                this.ah.setVisibility(0);
                this.af.setVisibility(0);
            }
            overridePendingTransition(R.anim._2130772016_res_0x7f010030, 0);
            return;
        }
        if (u == 7) {
            p();
            this.t = true;
            q();
            overridePendingTransition(R.anim._2130772016_res_0x7f010030, 0);
            return;
        }
        if (u == 6) {
            p();
            this.t = true;
            if (!CommonUtil.bv() && !CommonUtil.as() && this.u.ac()) {
                this.ah.setVisibility(0);
                this.af.setVisibility(0);
            }
            l();
            overridePendingTransition(R.anim._2130772016_res_0x7f010030, 0);
            return;
        }
        this.t = false;
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.background_layout);
        relativeLayout.setVisibility(0);
        relativeLayout.setOnClickListener(this);
        overridePendingTransition(R.anim._2130772063_res_0x7f01005f, 0);
    }

    private void p() {
        this.aa.setVisibility(0);
        Context context = this.b;
        if (context != null) {
            this.aa.setTitleBarBackgroundColor(context.getResources().getColor(R.color._2131296657_res_0x7f090191));
        }
    }

    private void q() {
        ((RelativeLayout) findViewById(R.id.img_preview)).setVisibility(0);
        jdx.b(new Runnable() { // from class: mua
            @Override // java.lang.Runnable
            public final void run() {
                SharePopupActivity.this.c();
            }
        });
    }

    public /* synthetic */ void c() {
        Bitmap cqu_ = mwd.cqu_(this.u);
        if (cqu_ == null || this.i == null) {
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.obj = cqu_;
        this.i.sendMessage(obtain);
    }

    private void l() {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.multiple_img_preview);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.guide_user_customiz_share_content);
        this.j = healthTextView;
        healthTextView.setOnClickListener(this);
        int i = 0;
        relativeLayout.setVisibility(0);
        this.ai = (HealthViewPager) findViewById(R.id.cardViewPager);
        if (LanguageUtil.bc(this.b)) {
            this.ai.setRotationY(180.0f);
        }
        this.ai.setOffscreenPageLimit(5);
        mue mueVar = new mue(getSupportFragmentManager(), this.u.b());
        this.p = mueVar;
        this.ai.setAdapter(mueVar);
        this.ai.setCurrentItem(this.u.d());
        this.ai.setPageMargin(0);
        this.ai.setPageTransformer(true, new mwb());
        g();
        if (this.u.o()) {
            r();
            f();
        } else if (this.ai.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.ai.getLayoutParams();
            layoutParams.setMarginStart(nsn.c(this.b, 24.0f));
            layoutParams.setMarginEnd(nsn.c(this.b, 24.0f));
            this.ai.setLayoutParams(layoutParams);
        }
        if (this.p != null) {
            while (true) {
                if (i < this.p.getCount()) {
                    if (this.ai.getCurrentItem() == i && (this.p.getItem(i) instanceof EditShareCustomFragment)) {
                        b((EditShareCustomFragment) this.p.getItem(i));
                        break;
                    }
                    i++;
                } else {
                    break;
                }
            }
        }
        relativeLayout.setOnTouchListener(new View.OnTouchListener() { // from class: com.huawei.pluginsocialshare.activity.SharePopupActivity.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                SharePopupActivity.this.ai.onTouchEvent(motionEvent);
                return true;
            }
        });
        b();
        e(this.u.d());
    }

    private void t() {
        if (CommonUtil.bv() || CommonUtil.as()) {
            return;
        }
        this.ah = (RelativeLayout) findViewById(R.id.rl_share_userinfo);
        this.af = (HealthDivider) findViewById(R.id.userinfo_divder);
        HealthSwitchButton healthSwitchButton = (HealthSwitchButton) findViewById(R.id.share_userinfo_switch);
        this.ag = healthSwitchButton;
        healthSwitchButton.setChecked(mwd.i());
        this.ag.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.pluginsocialshare.activity.SharePopupActivity$$ExternalSyntheticLambda1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                SharePopupActivity.this.cpl_(compoundButton, z);
            }
        });
    }

    /* synthetic */ void cpl_(CompoundButton compoundButton, boolean z) {
        if (this.d) {
            this.d = false;
            ViewClickInstrumentation.clickOnView(compoundButton);
        } else if (z) {
            mwd.e(true);
            ViewClickInstrumentation.clickOnView(compoundButton);
        } else {
            mwd.cqJ_(this, R.string._2130847207_res_0x7f0225e7, R.string._2130847202_res_0x7f0225e2, new View.OnClickListener() { // from class: mtz
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SharePopupActivity.cpg_(view);
                }
            }, new View.OnClickListener() { // from class: mtw
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SharePopupActivity.this.cpk_(view);
                }
            });
            ViewClickInstrumentation.clickOnView(compoundButton);
        }
    }

    public static /* synthetic */ void cpg_(View view) {
        mwd.e(false);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void cpk_(View view) {
        this.ag.setChecked(true);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        if (i == this.g || TextUtils.isEmpty(this.u.p())) {
            return;
        }
        ArrayList<fdz> b = this.u.b();
        if (i >= b.size()) {
            ReleaseLogUtil.d("Share_SharePopupActivity", "setWebpageTab position:", Integer.valueOf(i), " shareSportData.size(): ", Integer.valueOf(b.size()));
            return;
        }
        if (b.get(i).d() != 2) {
            this.u.a(this.k);
        } else {
            this.u.a(2);
            ac();
        }
        aa();
    }

    private void ac() {
        if (TextUtils.isEmpty(this.u.p())) {
            this.u.awp_(nrf.cHL_());
        } else {
            jdx.b(new Runnable() { // from class: mtu
                @Override // java.lang.Runnable
                public final void run() {
                    SharePopupActivity.this.e();
                }
            });
        }
    }

    public /* synthetic */ void e() {
        Bitmap cHT_ = nrf.cHT_(this.b, this.u.p());
        if (cHT_ == null) {
            cHT_ = nrf.cHL_();
        }
        this.u.awp_(cHT_);
    }

    private void b() {
        this.ai.addOnPageChangeListener(new HealthViewPager.OnPageChangeListener() { // from class: com.huawei.pluginsocialshare.activity.SharePopupActivity.5
            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // com.huawei.uikit.hwviewpager.widget.HwViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                if (i == SharePopupActivity.this.g) {
                    if (SharePopupActivity.this.p.getItem(i) instanceof EditShareCustomFragment) {
                        EditShareCustomFragment editShareCustomFragment = (EditShareCustomFragment) SharePopupActivity.this.p.getItem(i);
                        editShareCustomFragment.setUserVisibleHint(true);
                        SharePopupActivity.this.b(editShareCustomFragment);
                        editShareCustomFragment.a();
                    }
                    SharePopupActivity.this.j();
                    SharePopupActivity.this.a(i, true);
                    return;
                }
                LogUtil.h("Share_SharePopupActivity", "Hide pop position not equal to mEditPageCurrentPosition.");
                SharePopupActivity.this.a();
                SharePopupActivity.this.a(i, false);
                SharePopupActivity.this.e(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (Utils.o() && this.u.u() != 7) {
            this.s.setVisibility(0);
            this.h.setVisibility(8);
        } else {
            this.h.setVisibility(0);
            this.s.setVisibility(8);
        }
        this.aa.setTitleText(getResources().getString(R.string._2130842881_res_0x7f021501));
        this.aa.setRightTextVisible(4);
        if (CommonUtil.bv() || CommonUtil.as() || !this.u.ac()) {
            return;
        }
        this.ah.setVisibility(0);
        this.af.setVisibility(0);
        boolean i = mwd.i();
        if (this.ag.isChecked() != i) {
            this.d = true;
            this.ag.setChecked(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final EditShareCustomFragment editShareCustomFragment) {
        this.h.setVisibility(8);
        this.s.setVisibility(8);
        this.aa.setTitleText(getResources().getString(R.string._2130850338_res_0x7f023222));
        this.aa.setRightTextContent(getResources().getString(R.string._2130841395_res_0x7f020f33));
        this.aa.setRightTextVisible(0);
        this.aa.setRightTextOnClickListener(new View.OnClickListener() { // from class: mty
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SharePopupActivity.this.cpj_(editShareCustomFragment, view);
            }
        });
        RelativeLayout relativeLayout = this.ah;
        if (relativeLayout == null || this.af == null) {
            return;
        }
        relativeLayout.setVisibility(8);
        this.af.setVisibility(8);
    }

    public /* synthetic */ void cpj_(EditShareCustomFragment editShareCustomFragment, View view) {
        editShareCustomFragment.e();
        if (n()) {
            this.t = true;
            startActivity(new Intent(this.b, (Class<?>) CustomPreviewActivity.class));
        } else {
            LogUtil.b("Share_SharePopupActivity", "initShareData fail, finish");
            finish();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, boolean z) {
        HashMap hashMap = new HashMap(5);
        hashMap.put("click", 1);
        this.ae = i;
        fdu i2 = i();
        if (i2 != null && i2.l() != null && i2.l().containsKey("trackShareIndex")) {
            hashMap.put("type", i2.l().get("trackShareIndex"));
            if (z) {
                a(i, hashMap);
            }
        } else {
            LogUtil.h("Share_SharePopupActivity", "biShareTypeKey shareContent is null");
            hashMap.put("type", -1);
        }
        ixx.d().d(this.b.getApplicationContext(), AnalyticsValue.SHARE_TRACK_INDEX_2040102.value(), hashMap, 0);
    }

    private void a(int i, Map<String, Object> map) {
        ArrayList<fdz> b = this.u.b();
        if (koq.d(b, i)) {
            fdz fdzVar = b.get(i);
            String b2 = SharedPreferenceManager.b(this.b, Integer.toString(20002), "shareLastRecommend" + fdzVar.c());
            String b3 = SharedPreferenceManager.b(this.b, Integer.toString(20002), "shareLastBackground" + fdzVar.c());
            String b4 = SharedPreferenceManager.b(this.b, Integer.toString(20002), "shareLastDataMark" + fdzVar.c());
            LogUtil.a("Share_SharePopupActivity", "lastRecommendId --", b2, "--lastBackgroundId--", b3, "--lastDataMarkId--", b4);
            map.put(CardMgrSdkConst.KEY_RECOMMEND, b2);
            map.put(WatchFaceProvider.BACKGROUND_LABEL, b3);
            map.put("dataMark", b4);
        }
    }

    private void r() {
        String str;
        HealthSubTabWidget healthSubTabWidget = (HealthSubTabWidget) findViewById(R.id.share_tab);
        this.z = healthSubTabWidget;
        healthSubTabWidget.setVisibility(0);
        this.ad = new nqx(this, this.ai, this.z);
        ArrayList<fdz> b = this.u.b();
        if (b.size() > 0) {
            fdz fdzVar = b.get(0);
            str = SharedPreferenceManager.b(this.b, Integer.toString(CapabilityStatus.AWA_CAP_CODE_DARK_MODE), "shareLastBackground" + fdzVar.c());
            if ("1001".equals(str)) {
                int i = 0;
                while (true) {
                    if (i >= b.size()) {
                        break;
                    }
                    if (b.get(i).h()) {
                        str = String.valueOf(i);
                        break;
                    }
                    i++;
                }
            }
        } else {
            str = "";
        }
        if (!str.isEmpty()) {
            this.u.d(Integer.parseInt(str));
        }
        int i2 = 0;
        while (i2 < b.size()) {
            this.ad.c(this.z.c(this.p.getPageTitle(i2)), this.p.getItem(i2), i2 == this.u.d());
            i2++;
        }
    }

    private fdu i() {
        fdu fduVar;
        fdu fduVar2;
        HashMap hashMap = new HashMap(16);
        int i = this.ae;
        if (i != -1 && i != this.g) {
            this.u = this.f;
        }
        if (this.u.u() != 6) {
            fduVar2 = this.u;
            c(hashMap, fduVar2.l());
        } else {
            int currentItem = this.ai.getCurrentItem();
            ArrayList<fdz> b = this.u.b();
            if (!koq.d(b, currentItem)) {
                LogUtil.b("Share_SharePopupActivity", "convertShareContent: position out of range:", Integer.valueOf(currentItem));
                return null;
            }
            fdz fdzVar = b.get(currentItem);
            if (fdzVar.aws_() != null) {
                fduVar = new fdu(1);
                fduVar.awp_(fdzVar.aws_());
            } else {
                String e = fdzVar.e();
                if (TextUtils.isEmpty(e)) {
                    return null;
                }
                fdu fduVar3 = new fdu(4);
                fduVar3.d(e);
                fduVar = fduVar3;
            }
            fduVar.b(this.u.aa());
            fduVar.e(this.u.g());
            fduVar.i(this.u.ab());
            SaveBitampCallback k = this.u.k();
            if (k != null) {
                fduVar.c(k);
            }
            c(hashMap, this.u.l());
            c(hashMap, fdzVar.b());
            fduVar2 = fduVar;
        }
        fduVar2.b((Map<String, Object>) hashMap);
        return fduVar2;
    }

    private void c(Map<String, Object> map, Map<String, Object> map2) {
        if (map == null || map2 == null) {
            return;
        }
        map.putAll(map2);
    }

    private HealthTextView cpf_(LinearLayout linearLayout) {
        return (HealthTextView) linearLayout.findViewById(R.id.share_tv);
    }

    private ImageView cpe_(LinearLayout linearLayout) {
        if (linearLayout.findViewById(R.id.share_img) instanceof ImageView) {
            return (ImageView) linearLayout.findViewById(R.id.share_img);
        }
        return null;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        cpd_(view);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void cpd_(View view) {
        if (nsn.a(1500)) {
            LogUtil.a("Share_SharePopupActivity", "onClick() if (isClickFast())");
            return;
        }
        fdu i = i();
        if (i == null) {
            LogUtil.b("Share_SharePopupActivity", "shareContent == null");
            return;
        }
        o();
        int id = view.getId();
        if (id == R.id.share_save_to_local_layout || id == R.id.oversea_save_local_btn) {
            LogUtil.a("Share_SharePopupActivity", "share_save_to_local_layout");
            this.q.e(this.b, 4, i);
            x();
            return;
        }
        if (!CommonUtil.aa(com.huawei.hwcommonmodel.application.BaseApplication.getContext())) {
            nrh.b(com.huawei.hwcommonmodel.application.BaseApplication.getContext(), R.string._2130841392_res_0x7f020f30);
            return;
        }
        if (id == R.id.share_wechat_friends_layout) {
            LogUtil.a("Share_SharePopupActivity", "share_wechat_friends_layout");
            this.q.e(this.b, 1, i);
            x();
            return;
        }
        if (id == R.id.share_wechat_chat_layout) {
            LogUtil.a("Share_SharePopupActivity", "share_wechat_chat_layout");
            this.q.e(this.b, 2, i);
            x();
            return;
        }
        if (id == R.id.share_weibo_layout) {
            LogUtil.a("Share_SharePopupActivity", "share_weibo_layout");
            this.q.e(this.b, 3, i);
            x();
            return;
        }
        if (id == R.id.share_family_group_layout) {
            this.q.e(this.b, 6, i);
            x();
            return;
        }
        if (id == R.id.share_more_layout || id == R.id.oversea_share_btn) {
            this.q.e(this.b, 5, i);
            x();
            return;
        }
        if (id == R.id.share_save_pdf_layout) {
            LogUtil.a("Share_SharePopupActivity", "share_save_pdf_layout");
            this.q.e(this.b, 7, i);
            x();
        } else if (id == R.id.share_print_layout) {
            LogUtil.a("Share_SharePopupActivity", "share_print_layout");
            this.q.e(this.b, 8, i);
            x();
        } else if (id == R.id.background_layout) {
            LogUtil.a("Share_SharePopupActivity", "click background_layout, destroy!");
            finish();
        } else if (id == R.id.guide_user_customiz_share_content) {
            LogUtil.a("Share_SharePopupActivity", "click hide guide user share pop!");
            j();
        } else {
            LogUtil.a("Share_SharePopupActivity", "click outside activity! viewId:", Integer.valueOf(id));
        }
    }

    private void o() {
        if (this.q == null) {
            this.q = mto.b();
        }
    }

    private void x() {
        fdu d = mto.d();
        if (d == null) {
            return;
        }
        ArrayList<fdz> b = d.b();
        if (b.size() > 0 && this.ai != null) {
            fdz fdzVar = b.get(0);
            SharedPreferenceManager.e(this.b, Integer.toString(CapabilityStatus.AWA_CAP_CODE_DARK_MODE), "shareLastBackground" + fdzVar.c(), String.valueOf(this.ai.getCurrentItem()), new StorageParams());
        }
        HashMap<String, String> s = d.s();
        if (s.size() == 0) {
            return;
        }
        String str = s.get("currentSportType");
        String str2 = s.get("shareLastRecommend" + str);
        String str3 = s.get("shareLastBackground" + str);
        String str4 = s.get("shareLastDataMark" + str);
        SharedPreferenceManager.e(this.b, Integer.toString(20002), "shareLastRecommend" + str, str2, new StorageParams());
        SharedPreferenceManager.e(this.b, Integer.toString(20002), "shareLastBackground" + str, str3, new StorageParams());
        SharedPreferenceManager.e(this.b, Integer.toString(20002), "shareLastDataMark" + str, str4, new StorageParams());
        mvs.d(Integer.parseInt(str), mto.a());
    }

    private void m() {
        SharedPreferenceManager.e(this.b, Integer.toString(PrebakedEffectId.RT_SPEED_UP), "SHARE_POP_UP_ACTIVITY_SHARE_TYPE", String.valueOf(0), new StorageParams());
    }

    private void ab() {
        if (nsn.ab(this.b)) {
            int q = nsn.q(this.b);
            View findViewById = findViewById(R.id.navigation_white);
            if (findViewById.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) findViewById.getLayoutParams();
                layoutParams.height = q;
                findViewById.setLayoutParams(layoutParams);
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("Share_SharePopupActivity", "onDestroy");
        Animation animation = this.c;
        if (animation != null) {
            animation.cancel();
            this.c = null;
        }
        Animation animation2 = this.e;
        if (animation2 != null) {
            animation2.cancel();
            this.e = null;
        }
        ag();
        mwd.a(this.b.getResources().getConfiguration().uiMode & 48);
        fdu fduVar = this.u;
        if (fduVar == null) {
            return;
        }
        ArrayList<fdz> b = fduVar.b();
        if (!TextUtils.isEmpty(this.u.i())) {
            mwa.a(this.u.i());
        }
        if (koq.b(b)) {
            return;
        }
        for (fdz fdzVar : b) {
            if (fdzVar != null && !TextUtils.isEmpty(fdzVar.e())) {
                mwa.a(fdzVar.e());
                a(fdzVar.g());
            }
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.pluginsocialshare.activity.SharePopupActivity.4
            @Override // java.lang.Runnable
            public void run() {
                mvs.c();
            }
        });
    }

    private void a(feh fehVar) {
        if (fehVar == null) {
            return;
        }
        String c = fehVar.c();
        if (TextUtils.isEmpty(c)) {
            return;
        }
        mwa.a(c);
    }

    private void g() {
        fdu fduVar = this.u;
        if (fduVar == null) {
            LogUtil.h("Share_SharePopupActivity", "mShareContent is null.");
            return;
        }
        ArrayList<fdz> b = fduVar.b();
        int i = 0;
        while (true) {
            if (i >= b.size()) {
                i = -1;
                break;
            } else if (b.get(i).h()) {
                break;
            } else {
                i++;
            }
        }
        if (i == -1) {
            return;
        }
        this.g = i;
    }

    private void f() {
        if (!mwd.j()) {
            LogUtil.h("Share_SharePopupActivity", "The account language is not supported.");
            return;
        }
        SharedPreferences sharedPreferences = this.b.getSharedPreferences(Integer.toString(20002), 0);
        this.ab = sharedPreferences;
        if (sharedPreferences == null) {
            return;
        }
        this.m = sharedPreferences.getBoolean("is_first_time_guide_user_customiz_share", true);
        u();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        if (!mwd.j()) {
            LogUtil.h("Share_SharePopupActivity", "The account language is not supported.");
            return;
        }
        this.j.setVisibility(8);
        if (this.ab == null) {
            LogUtil.h("Share_SharePopupActivity", "Hide bubble sharedPreferences is null.");
            this.ab = this.b.getSharedPreferences(Integer.toString(20002), 0);
        }
        SharedPreferences.Editor edit = this.ab.edit();
        if (edit == null) {
            return;
        }
        edit.putBoolean("is_first_time_guide_user_customiz_share", false);
        edit.commit();
    }

    private void u() {
        HealthSubTabWidget healthSubTabWidget = this.z;
        if (healthSubTabWidget == null) {
            return;
        }
        healthSubTabWidget.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.huawei.pluginsocialshare.activity.SharePopupActivity.7
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                HwSubTabWidget.SubTabView c = SharePopupActivity.this.z.c(SharePopupActivity.this.g);
                if (c != null) {
                    SharePopupActivity.this.z.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    c.getLocationInWindow(new int[2]);
                    int width = (int) (((SharePopupActivity.this.z.getWidth() - r1[0]) - (c.getWidth() * 0.5f)) - nsn.c(SharePopupActivity.this.b, 28.0f));
                    int c2 = (int) (nsn.c(SharePopupActivity.this.b, 8.0f) - ((c.getHeight() - nsn.c(SharePopupActivity.this.b, 14.0f)) * 0.5f));
                    ViewGroup.LayoutParams layoutParams = SharePopupActivity.this.j.getLayoutParams();
                    RelativeLayout.LayoutParams layoutParams2 = layoutParams instanceof RelativeLayout.LayoutParams ? (RelativeLayout.LayoutParams) layoutParams : null;
                    if (layoutParams2 != null) {
                        layoutParams2.rightMargin = width;
                        layoutParams2.leftMargin = nsn.c(SharePopupActivity.this.b, 6.0f);
                        layoutParams2.topMargin = c2;
                    }
                    SharePopupActivity.this.j.setLayoutParams(layoutParams2);
                    if (SharePopupActivity.this.m) {
                        SharePopupActivity.this.j.setVisibility(0);
                        return;
                    } else {
                        SharePopupActivity.this.j.setVisibility(8);
                        return;
                    }
                }
                LogUtil.h("Share_SharePopupActivity", "Customiz share tabView is null.");
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        int i3;
        super.onActivityResult(i, i2, intent);
        if (i != 4 && intent == null) {
            LogUtil.h("Share_SharePopupActivity", "data == null and it's not camera callback");
            return;
        }
        if (i2 != -1) {
            LogUtil.b("Share_SharePopupActivity", "onActivityResult resultCode != Activity.RESULT_OK");
            return;
        }
        if (i == 5) {
            muw.cpP_(this.b, intent);
            return;
        }
        mue mueVar = this.p;
        if (mueVar == null || (i3 = this.g) == -1) {
            return;
        }
        Fragment item = mueVar.getItem(i3);
        if (item instanceof EditShareCustomFragment) {
            LogUtil.a("Share_SharePopupActivity", "onActivityResult dealCameraResult");
            ((EditShareCustomFragment) item).cra_(i, intent);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        if (nsn.l()) {
            ad();
        } else {
            u();
        }
    }

    public class SystemLocaleChangeReceiver extends BroadcastReceiver {
        public SystemLocaleChangeReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                LogUtil.b("Share_SharePopupActivity", "SystemLocaleChangeReceiver intent is null");
            } else if ("android.intent.action.LOCALE_CHANGED".equals(intent.getAction())) {
                LogUtil.a("Share_SharePopupActivity", "SystemLocaleChangeReceiver language change");
                SharePopupActivity.this.ad();
            }
        }
    }

    private void w() {
        if (this.ac == null) {
            LogUtil.b("Share_SharePopupActivity", "Enter registerSystemLanguageChange()");
            SystemLocaleChangeReceiver systemLocaleChangeReceiver = new SystemLocaleChangeReceiver();
            this.ac = systemLocaleChangeReceiver;
            BroadcastManager.wk_(systemLocaleChangeReceiver);
        }
    }

    private void ag() {
        if (this.ac != null) {
            LogUtil.a("Share_SharePopupActivity", "Enter unregisterSystemLanguageChange()");
            BroadcastManager.wy_(this.ac);
            this.ac = null;
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, android.view.ContextThemeWrapper, android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        return !this.l ? mwd.cqG_(super.getResources()) : super.getResources();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        mwd.cqI_(getResources());
        this.l = true;
    }
}
