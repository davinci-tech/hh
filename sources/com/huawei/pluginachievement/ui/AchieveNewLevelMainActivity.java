package com.huawei.pluginachievement.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.google.android.gms.fitness.FitnessStatusCodes;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.h5pro.utils.GeneralUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.impl.AchieveCallback;
import com.huawei.pluginachievement.impl.AchieveObserver;
import com.huawei.pluginachievement.manager.model.LevelInfoDesc;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginachievement.manager.model.PersonalData;
import com.huawei.pluginachievement.manager.model.TotalRecord;
import com.huawei.pluginachievement.manager.model.UserAchieveWrapper;
import com.huawei.pluginachievement.manager.service.AchieveLevelInfoObserver;
import com.huawei.pluginachievement.ui.AchieveNewLevelMainActivity;
import com.huawei.pluginachievement.ui.kakatask.AchieveKaKaTaskClickListener;
import com.huawei.pluginachievement.ui.level.AchieveLevelTaskRvAdapter;
import com.huawei.pluginachievement.ui.level.LevelHorizontalRecyclerViewAdapter;
import com.huawei.pluginachievement.ui.views.LevelProgress;
import com.huawei.pluginachievement.ui.views.SpeedRecyclerView;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.commonui.scrollview.ScrollViewListener;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import defpackage.ixx;
import defpackage.koq;
import defpackage.mct;
import defpackage.mcv;
import defpackage.mcx;
import defpackage.mcz;
import defpackage.mdf;
import defpackage.meh;
import defpackage.mer;
import defpackage.mfc;
import defpackage.mfg;
import defpackage.mfm;
import defpackage.mfp;
import defpackage.mht;
import defpackage.mji;
import defpackage.mjv;
import defpackage.mkg;
import defpackage.mkj;
import defpackage.mlc;
import defpackage.mle;
import defpackage.mlg;
import defpackage.mlo;
import defpackage.mlu;
import defpackage.nqf;
import defpackage.nrt;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes9.dex */
public class AchieveNewLevelMainActivity extends BaseActivity implements AchieveObserver, AchieveKaKaTaskClickListener {

    /* renamed from: a, reason: collision with root package name */
    private AchieveLevelTaskRvAdapter f8402a;
    private HealthRecycleView aa;
    private ImageView ab;
    private HealthTextView ac;
    private LevelProgress ad;
    private HealthTextView ae;
    private HealthTextView af;
    private HealthTextView ag;
    private RelativeLayout ah;
    private HealthTextView ai;
    private View aj;
    private View ak;
    private ImageView al;
    private int am;
    private ArrayList<String> an;
    private HealthTextView ao;
    private int ap;
    private int aq;
    private FrameLayout ar;
    private int as;
    private HealthScrollView at;
    private LinearLayout au;
    private meh av;
    private LinearLayout aw;
    private LinearLayout ax;
    private int ay;
    private SpeedRecyclerView az;
    private LinearLayout b;
    private LinearLayout bc;
    private ImageView bf;
    private HealthTextView bg;
    private String bh;
    private LinearLayout c;
    private HealthTextView d;
    private ImageView e;
    private mjv f;
    private Context g;
    private RelativeLayout j;
    private ImageView k;
    private int l;
    private int m;
    private long n;
    private Handler o;
    private CustomTitleBar q;
    private LevelHorizontalRecyclerViewAdapter r;
    private List<String> t;
    private HealthTextView u;
    private ImageView v;
    private LinearLayout w;
    private int x;
    private HealthTextView y;
    private HealthTextView z;
    private NoTitleCustomAlertDialog i = null;
    private ArrayList<mkg> bb = new ArrayList<>(0);
    private boolean s = false;
    private String ba = "";
    private String h = "";
    private boolean p = false;

    static class d extends BaseHandler<AchieveNewLevelMainActivity> {
        d(AchieveNewLevelMainActivity achieveNewLevelMainActivity) {
            super(achieveNewLevelMainActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: chP_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(AchieveNewLevelMainActivity achieveNewLevelMainActivity, Message message) {
            if (achieveNewLevelMainActivity == null) {
                LogUtil.h("PLGACHIEVE_AchieveNewLevelMainActivity", "handleMessageWhenReferenceNotNull activity is null.");
                return;
            }
            int i = message.what;
            if (i == 0) {
                achieveNewLevelMainActivity.b(((Integer) message.obj).intValue(), achieveNewLevelMainActivity.ay, achieveNewLevelMainActivity.an);
                return;
            }
            if (i == 1) {
                int intValue = ((Integer) message.obj).intValue();
                LogUtil.a("PLGACHIEVE_AchieveNewLevelMainActivity", "sport total days :", Integer.valueOf(intValue));
                achieveNewLevelMainActivity.ai.setText(achieveNewLevelMainActivity.getResources().getString(R.string._2130841064_res_0x7f020de8, achieveNewLevelMainActivity.getResources().getQuantityString(R.plurals._2130903164_res_0x7f03007c, intValue, UnitUtil.e(intValue, 1, 0))));
                return;
            }
            if (i == 11) {
                achieveNewLevelMainActivity.b(achieveNewLevelMainActivity.av, (AchieveCallback) null);
                return;
            }
            if (i == 1000) {
                achieveNewLevelMainActivity.a(message.obj + "");
                LogUtil.a("PLGACHIEVE_AchieveNewLevelMainActivity", "ERROR_TIP :", message.obj);
                return;
            }
            if (i != 1002) {
                if (i == 5005) {
                    if (achieveNewLevelMainActivity.f != null) {
                        achieveNewLevelMainActivity.f.d(achieveNewLevelMainActivity.bb);
                        achieveNewLevelMainActivity.f8402a.b(achieveNewLevelMainActivity.bb, achieveNewLevelMainActivity.f.e() + 1);
                        achieveNewLevelMainActivity.a();
                        return;
                    }
                    LogUtil.h("PLGACHIEVE_AchieveNewLevelMainActivity", "handleMessageWhenReferenceNotNull mCardScaleHelper is null.");
                    return;
                }
                LogUtil.h("PLGACHIEVE_AchieveNewLevelMainActivity", "handlerCheckInMsg message = ", Integer.valueOf(message.what));
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().getDecorView().setSystemUiVisibility(1280);
        getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color._2131299296_res_0x7f090be0));
        setContentView(R.layout.achieve_new_level_layout);
        clearBackgroundDrawable();
        LogUtil.a("PLGACHIEVE_AchieveNewLevelMainActivity", "onCreate");
        this.g = this;
        this.o = new d(this);
        f();
        nqf.d(this.g);
        mct.b(this.g, "levelTypeUpgraded", "");
        SharedPreferenceManager.e(String.valueOf(20003), "new_level_first_use", false);
        mer.b(this.g).d();
    }

    private void f() {
        this.j = (RelativeLayout) findViewById(R.id.all_back);
        this.u = (HealthTextView) findViewById(R.id.level_badge_name);
        this.y = (HealthTextView) findViewById(R.id.level_badge_dec);
        this.ax = (LinearLayout) findViewById(R.id.sign_grid_layout1);
        this.au = (LinearLayout) findViewById(R.id.sign_grid_layout2);
        this.w = (LinearLayout) findViewById(R.id.level_badge_dec_layout);
        this.al = (ImageView) findViewById(R.id.lockIcon);
        this.v = (ImageView) findViewById(R.id.level_arrow);
        this.ao = (HealthTextView) findViewById(R.id.rocket_tips);
        this.ar = (FrameLayout) findViewById(R.id.outSideLayout);
        this.ab = (ImageView) findViewById(R.id.level_bg_light);
        this.ad = (LevelProgress) findViewById(R.id.levelView);
        this.aj = findViewById(R.id.maskStart);
        this.ak = findViewById(R.id.maskEnd);
        this.aj.setAlpha(0.35f);
        this.ak.setAlpha(0.35f);
        ((HealthSubHeader) findViewById(R.id.achieve_new_level_rule_title)).setSubHeaderBackgroundColor(0);
        HealthScrollView healthScrollView = (HealthScrollView) findViewById(R.id.fragment_social_sroll);
        this.at = healthScrollView;
        healthScrollView.setScrollViewVerticalDirectionEvent(true);
        HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.achieve_level_listview);
        this.aa = healthRecycleView;
        healthRecycleView.setLayoutManager(new LinearLayoutManager(this, 1, false));
        this.aa.setIsScroll(false);
        AchieveLevelTaskRvAdapter achieveLevelTaskRvAdapter = new AchieveLevelTaskRvAdapter(this, null, 0);
        this.f8402a = achieveLevelTaskRvAdapter;
        achieveLevelTaskRvAdapter.c(this);
        this.aa.setAdapter(this.f8402a);
        h();
        i();
        j();
        k();
        this.w.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.pluginachievement.ui.AchieveNewLevelMainActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (mer.e(500)) {
                    LogUtil.h("PLGACHIEVE_AchieveNewLevelMainActivity", "levelRecord enter isFastClick!");
                    ViewClickInstrumentation.clickOnView(view);
                    return;
                }
                Intent intent = new Intent();
                intent.setClassName(AchieveNewLevelMainActivity.this.g, PersonalData.CLASS_NAME_PERSONAL_LEVEL_RECORD);
                AchieveNewLevelMainActivity.this.startActivity(intent);
                AchieveNewLevelMainActivity.this.c();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        m();
    }

    private void m() {
        if ("1".equals(this.bh)) {
            this.ad.setOnDownActionListener(new LevelProgress.OnDownActionListener() { // from class: com.huawei.pluginachievement.ui.AchieveNewLevelMainActivity.3
                @Override // com.huawei.pluginachievement.ui.views.LevelProgress.OnDownActionListener
                public void onDown(int i, int i2) {
                    if ("1".equals(AchieveNewLevelMainActivity.this.bh)) {
                        if (AchieveNewLevelMainActivity.this.ar.getVisibility() == 8) {
                            AchieveNewLevelMainActivity.this.ao.setVisibility(0);
                            AchieveNewLevelMainActivity.this.ar.setVisibility(0);
                            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
                            if (AchieveNewLevelMainActivity.this.aq == 0) {
                                AchieveNewLevelMainActivity.this.aq = (i - (r1.ao.getWidth() / 2)) - 15;
                            }
                            layoutParams.leftMargin = AchieveNewLevelMainActivity.this.aq;
                            layoutParams.topMargin = 250;
                            if (AchieveNewLevelMainActivity.this.aq < 0) {
                                layoutParams.leftMargin = 0;
                            }
                            AchieveNewLevelMainActivity.this.ao.setLayoutParams(layoutParams);
                            return;
                        }
                        AchieveNewLevelMainActivity.this.ao.setVisibility(8);
                        AchieveNewLevelMainActivity.this.ar.setVisibility(8);
                    }
                }
            });
            this.ar.setOnTouchListener(new View.OnTouchListener() { // from class: miu
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return AchieveNewLevelMainActivity.this.chM_(view, motionEvent);
                }
            });
        }
    }

    public /* synthetic */ boolean chM_(View view, MotionEvent motionEvent) {
        this.ao.setVisibility(8);
        this.ar.setVisibility(8);
        return false;
    }

    private void j() {
        Bitmap cgv_;
        this.c = (LinearLayout) findViewById(R.id.achieve_level_share_ll);
        this.ah = (RelativeLayout) findViewById(R.id.level_share_bg);
        this.e = (ImageView) findViewById(R.id.achieve_level_share_image);
        this.d = (HealthTextView) findViewById(R.id.level_share_name);
        this.aw = (LinearLayout) findViewById(R.id.sign_share_grid_layout1);
        this.bc = (LinearLayout) findViewById(R.id.sign_share_grid_layout2);
        this.bf = (ImageView) findViewById(R.id.vip_image);
        this.k = (ImageView) findViewById(R.id.head_imageview);
        this.bg = (HealthTextView) findViewById(R.id.name_textview);
        this.ai = (HealthTextView) findViewById(R.id.level_share_dec);
        this.b = (LinearLayout) findViewById(R.id.account_layout);
        if (mfg.c(0)) {
            this.b.setVisibility(0);
            this.aw.setVisibility(0);
            this.bc.setVisibility(0);
        } else {
            this.b.setVisibility(4);
            this.aw.setVisibility(8);
            this.bc.setVisibility(8);
        }
        d();
        this.bg.setText(mcx.b());
        String c = mcx.c(this.g);
        String c2 = CommonUtil.c(c);
        if (GeneralUtil.isSafePath(c) && !TextUtils.isEmpty(c2) && (cgv_ = mfc.cgv_(this, c2)) != null) {
            this.k.setImageBitmap(cgv_);
        }
        String b = SharedPreferenceManager.b(this.g, Integer.toString(10000), "MAIN_VIP_KEY");
        this.bh = b;
        if ("1".equals(b)) {
            this.bf.setVisibility(0);
        }
    }

    private void d() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.pluginachievement.ui.AchieveNewLevelMainActivity.4
            @Override // java.lang.Runnable
            public void run() {
                mcz d2 = meh.c(AchieveNewLevelMainActivity.this.g).d(1, new HashMap(16));
                TotalRecord totalRecord = d2 instanceof TotalRecord ? (TotalRecord) d2 : null;
                if (totalRecord != null) {
                    AchieveNewLevelMainActivity.this.d(1, (int) Integer.valueOf(totalRecord.getDays()));
                } else {
                    LogUtil.h("PLGACHIEVE_AchieveNewLevelMainActivity", "computeDomesticData no TotalRecordDB.");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        if (!mcx.d(this.g)) {
            mlg.e(this.g);
            return;
        }
        this.b.setVisibility(mcx.e() ? 0 : 4);
        this.ah.setBackgroundResource(mlc.a(this.am));
        this.e.setImageResource(mlc.d(this.am));
        this.d.setText(mlc.b(this.am) + " " + mlc.c(this.g, this.x));
        if (mcv.d(this.g).getAdapter() != null) {
            HashMap hashMap = new HashMap(3);
            hashMap.put("click", "1");
            hashMap.put("level", Integer.valueOf(this.x));
            Bitmap cgJ_ = mfp.cgJ_(this.c);
            if (cgJ_ != null) {
                mcx.cfN_(this.g, cgJ_, AnalyticsValue.LEVEL_SHARE_1100073.value(), hashMap);
            }
        }
    }

    public void d(List<String> list, int i) {
        Drawable drawable = ContextCompat.getDrawable(BaseApplication.getContext(), R.mipmap._2131821391_res_0x7f11034f);
        if (drawable == null || i >= mjv.a().length) {
            LogUtil.h("PLGACHIEVE_AchieveNewLevelMainActivity", "setBadgeDesignation isOutOfBounds!");
            return;
        }
        if (koq.b(list)) {
            LogUtil.h("PLGACHIEVE_AchieveNewLevelMainActivity", "setBadgeDesignation labelList isEmpty!");
            return;
        }
        int color = BaseApplication.getContext().getResources().getColor(mjv.a()[i].intValue());
        Drawable ckC_ = mlo.ckC_(drawable, color);
        this.aw.removeAllViews();
        this.bc.removeAllViews();
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (i2 < 3) {
                this.aw.addView(this.f.ciX_(mlc.e().get(Integer.valueOf(mfg.b(list.get(i2)))), ckC_, color));
            } else {
                this.bc.addView(this.f.ciX_(mlc.e().get(Integer.valueOf(mfg.b(list.get(i2)))), ckC_, color));
            }
        }
    }

    private void o() {
        Drawable drawable;
        Drawable drawable2;
        Drawable drawable3;
        ImageView imageView = (ImageView) findViewById(R.id.level_bg_back);
        if (LanguageUtil.bc(this)) {
            drawable = nrz.cKn_(this, R.mipmap._2131821376_res_0x7f110340);
        } else {
            drawable = ContextCompat.getDrawable(this, R.mipmap._2131821376_res_0x7f110340);
        }
        if (nrt.a(this)) {
            drawable2 = getResources().getDrawable(R.mipmap._2131821350_res_0x7f110326);
            drawable3 = getResources().getDrawable(R.mipmap._2131821349_res_0x7f110325);
            imageView.setBackgroundResource(R.mipmap._2131821355_res_0x7f11032b);
            this.v.setImageDrawable(mlo.ckC_(drawable, BaseApplication.getContext().getResources().getColor(R.color._2131297000_res_0x7f0902e8)));
        } else {
            drawable2 = getResources().getDrawable(R.mipmap._2131821352_res_0x7f110328);
            drawable3 = getResources().getDrawable(R.mipmap._2131821351_res_0x7f110327);
            imageView.setBackgroundResource(R.mipmap._2131821353_res_0x7f110329);
            this.v.setImageDrawable(drawable);
        }
        drawable2.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
        drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen._2131365106_res_0x7f0a0cf2);
        final HealthTextView healthTextView = (HealthTextView) findViewById(R.id.rule_read_more);
        HealthTextView healthTextView2 = (HealthTextView) findViewById(R.id.rule_pack_up);
        healthTextView.setCompoundDrawablePadding(dimensionPixelSize);
        healthTextView2.setCompoundDrawablePadding(dimensionPixelSize);
        healthTextView.setCompoundDrawables(null, null, drawable3, null);
        healthTextView2.setCompoundDrawables(null, null, drawable2, null);
        final LinearLayout linearLayout = (LinearLayout) findViewById(R.id.rule_layout);
        healthTextView.setOnClickListener(new View.OnClickListener() { // from class: miw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AchieveNewLevelMainActivity.this.chN_(linearLayout, healthTextView, view);
            }
        });
        healthTextView2.setOnClickListener(new View.OnClickListener() { // from class: mjb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AchieveNewLevelMainActivity.this.chO_(healthTextView, linearLayout, view);
            }
        });
    }

    public /* synthetic */ void chN_(LinearLayout linearLayout, HealthTextView healthTextView, View view) {
        linearLayout.setVisibility(0);
        healthTextView.setVisibility(8);
        this.ap = 0;
        mlc.a(3, this.x, 0L, this.ba, this.p);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void chO_(HealthTextView healthTextView, LinearLayout linearLayout, View view) {
        healthTextView.setVisibility(0);
        linearLayout.setVisibility(8);
        this.ap = 0;
        ViewClickInstrumentation.clickOnView(view);
    }

    private void k() {
        this.at.setScrollViewListener(new ScrollViewListener() { // from class: com.huawei.pluginachievement.ui.AchieveNewLevelMainActivity.1
            @Override // com.huawei.ui.commonui.scrollview.ScrollViewListener
            public void onScrollChanged(HealthScrollView healthScrollView, int i, int i2, int i3, int i4) {
                AchieveNewLevelMainActivity.this.e(healthScrollView, i2);
                if (nrt.a(BaseApplication.getContext())) {
                    LogUtil.a("PLGACHIEVE_AchieveNewLevelMainActivity", "setScrollViewListening setImageAlpha.");
                    if (i2 <= 0) {
                        AchieveNewLevelMainActivity.this.ab.setImageAlpha(255);
                    } else if (i2 <= 0 || i2 > 100) {
                        AchieveNewLevelMainActivity.this.ab.setImageAlpha(0);
                    } else {
                        AchieveNewLevelMainActivity.this.ab.setImageAlpha((int) (255.0f - ((i2 / 100.0f) * 255.0f)));
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(HealthScrollView healthScrollView, int i) {
        int bottom = healthScrollView.getChildAt(healthScrollView.getChildCount() - 1).getBottom();
        int height = healthScrollView.getHeight();
        int scrollY = healthScrollView.getScrollY();
        if (this.ap == 0 && bottom - (height + scrollY) == 0) {
            this.ap = 1;
            mlc.a(4, this.x, 0L, this.h, this.p);
        }
        if (this.as != 0 || i < 100) {
            return;
        }
        this.as = 1;
        mlc.a(2, this.x, 0L, this.h, this.p);
    }

    private void i() {
        this.z = (HealthTextView) findViewById(R.id.level_rule_dec1);
        this.ag = (HealthTextView) findViewById(R.id.level_rule_dec2);
        this.af = (HealthTextView) findViewById(R.id.level_rule_dec3);
        this.ac = (HealthTextView) findViewById(R.id.level_rule_dec03);
        this.ae = (HealthTextView) findViewById(R.id.level_rule_dec4);
        this.z.setText(getResources().getString(R.string._2130841035_res_0x7f020dcb, 7, 20));
        this.ag.setText(getResources().getString(R.string._2130841036_res_0x7f020dcc, 7, 20, 0, Integer.valueOf(mlc.k(1)), Integer.valueOf(mlc.k(1)), Integer.valueOf(mlc.k(2)), Integer.valueOf(mlc.k(2)), Integer.valueOf(mlc.k(3)), Integer.valueOf(mlc.k(3)), Integer.valueOf(mlc.k(4)), Integer.valueOf(mlc.k(4)), Integer.valueOf(mlc.k(5)), Integer.valueOf(mlc.k(5)), Integer.valueOf(mlc.k(6)), Integer.valueOf(mlc.k(6))));
        this.af.setText(getResources().getString(R.string._2130850222_res_0x7f0231ae, 1, 3, 2, 3));
        this.ac.setText(getResources().getString(R.string._2130841059_res_0x7f020de3, "1.1"));
        this.ae.setText(getResources().getString(R.string._2130841038_res_0x7f020dce, 1, 2, 3));
        o();
    }

    private void h() {
        CustomTitleBar customTitleBar = (CustomTitleBar) mfm.cgL_(this, R.id.level_titlebar);
        this.q = customTitleBar;
        customTitleBar.setRightButtonVisibility(0);
        if (LanguageUtil.bc(this.g)) {
            if (nrt.a(this)) {
                this.q.setRightButtonDrawable(nrz.cKn_(this.g, R.drawable._2131431670_res_0x7f0b10f6), nsf.h(R.string._2130850657_res_0x7f023361));
                this.q.setLeftButtonDrawable(this.g.getResources().getDrawable(R.drawable._2131429734_res_0x7f0b0966), nsf.h(R.string._2130850617_res_0x7f023339));
            } else {
                this.q.setRightButtonDrawable(nrz.cKn_(this.g, R.drawable._2131430035_res_0x7f0b0a93), nsf.h(R.string._2130850657_res_0x7f023361));
            }
        } else if (nrt.a(this)) {
            this.q.setLeftButtonDrawable(this.g.getResources().getDrawable(R.drawable._2131429733_res_0x7f0b0965), nsf.h(R.string._2130850617_res_0x7f023339));
            this.q.setRightButtonDrawable(this.g.getResources().getDrawable(R.drawable._2131431670_res_0x7f0b10f6), nsf.h(R.string._2130850657_res_0x7f023361));
        } else {
            this.q.setRightButtonDrawable(this.g.getResources().getDrawable(R.drawable._2131430035_res_0x7f0b0a93), nsf.h(R.string._2130850657_res_0x7f023361));
        }
        this.q.setRightButtonOnClickListener(new View.OnClickListener() { // from class: miz
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AchieveNewLevelMainActivity.this.chL_(view);
            }
        });
    }

    public /* synthetic */ void chL_(View view) {
        LogUtil.a("PLGACHIEVE_AchieveNewLevelMainActivity", "initTitleBar onClick share information");
        if (PermissionUtil.c()) {
            q();
            ViewClickInstrumentation.clickOnView(view);
        } else {
            PermissionUtil.b(this.g, PermissionUtil.PermissionType.STORAGE, new CustomPermissionAction(this.g) { // from class: com.huawei.pluginachievement.ui.AchieveNewLevelMainActivity.5
                @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
                public void onGranted() {
                    AchieveNewLevelMainActivity.this.q();
                }
            });
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void c(int i, int i2, List<LevelInfoDesc> list, double d2) {
        ViewStub viewStub = (ViewStub) findViewById(R.id.speedRecyclerView);
        if (viewStub == null) {
            LogUtil.h("PLGACHIEVE_AchieveNewLevelMainActivity", "initRecyclerView ViewStub is loaded fail.");
            return;
        }
        this.az = (SpeedRecyclerView) viewStub.inflate();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, 0, false);
        this.az.setLayoutManager(linearLayoutManager);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.topMargin = nsn.c(this, 4.0f);
        this.az.setLayoutParams(layoutParams);
        LevelHorizontalRecyclerViewAdapter levelHorizontalRecyclerViewAdapter = new LevelHorizontalRecyclerViewAdapter(this, list);
        this.r = levelHorizontalRecyclerViewAdapter;
        this.az.setAdapter(levelHorizontalRecyclerViewAdapter);
        if (this.f == null) {
            this.f = new mjv();
        }
        linearLayoutManager.scrollToPositionWithOffset(i2 - 1, mlu.e(BaseApplication.getContext()) + (mlu.a(BaseApplication.getContext()) / 2));
        this.f.d(list, i2, i, d2);
        this.f.ciZ_(this.ax, this.au);
        this.f.ciY_(this.al, this.j, this.ad, this.f8402a);
        this.f.cja_(this.aj, this.ak);
        this.f.c(this.az, this.u, this.y);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        mer.b(this.g).h();
        g();
    }

    private void g() {
        meh c = meh.c(getApplicationContext());
        this.av = c;
        c.b((AchieveObserver) this);
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.pluginachievement.ui.AchieveNewLevelMainActivity.9
            @Override // java.lang.Runnable
            public void run() {
                AchieveNewLevelMainActivity.this.b();
                AchieveNewLevelMainActivity.this.e();
                AchieveNewLevelMainActivity.this.n();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        String b = mct.b(BaseApplication.getContext(), "AchieveLevelLabel");
        LogUtil.a("PLGACHIEVE_AchieveNewLevelMainActivity", "LevelType refreshLevelFromSp labels = ", b);
        ArrayList<String> c = mlc.c(b);
        this.t = c;
        LogUtil.a("PLGACHIEVE_AchieveNewLevelMainActivity", "LevelType refreshLevelFromSp mLabelList = ", c.toString());
        String b2 = mct.b(BaseApplication.getContext(), "levelTypeData");
        LogUtil.a("PLGACHIEVE_AchieveNewLevelMainActivity", "refreshLevelFromSp levelDataStr = ", b2);
        if (!TextUtils.isEmpty(b2)) {
            ArrayList<String> c2 = mlc.c(b2);
            this.an = c2;
            if (c2.size() >= 2) {
                this.x = mfg.b(this.an.get(0));
                this.ay = mfg.b(this.an.get(1));
                LogUtil.a("PLGACHIEVE_AchieveNewLevelMainActivity", "LevelType refreshLevelFromSp level = ", Integer.valueOf(this.x), " mUserExperience ", Integer.valueOf(this.ay));
                d(0, (int) Integer.valueOf(this.x));
                return;
            }
            return;
        }
        d(0, 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, int i2, ArrayList<String> arrayList) {
        double d2;
        int h = mlc.h(i);
        if (this.am != h) {
            this.s = true;
            this.am = h;
        } else {
            this.s = false;
        }
        LogUtil.a("PLGACHIEVE_AchieveNewLevelMainActivity", "LevelType ud mLevelType = ", Integer.valueOf(h), " level ", Integer.valueOf(i), " isTypeChange = ", Boolean.valueOf(this.s));
        int c = mlc.c(i + 1);
        int c2 = mlc.c(i);
        LogUtil.a("PLGACHIEVE_AchieveNewLevelMainActivity", "LevelType nextLevelValue ", Integer.valueOf(c), " currLevelValue ", Integer.valueOf(c2));
        int i3 = c - c2;
        if (i3 != 0) {
            double d3 = (((i2 - c2) * 1.0d) / i3) * 1.0d;
            LogUtil.a("PLGACHIEVE_AchieveNewLevelMainActivity", "LevelType ud userV =", Integer.valueOf(i2), " levelGrowthV ", Integer.valueOf(i3), " percent ", Double.valueOf(d3));
            d2 = d3;
        } else {
            d2 = 0.0d;
        }
        List<LevelInfoDesc> b = mlc.b(i, i2, arrayList, this.t);
        int i4 = this.l;
        if (i4 == 0) {
            this.l = i4 + 1;
            c(i, h, b, d2);
        } else {
            this.f.d(b, h, i, d2);
            b(i, d2);
            if (this.s) {
                this.az.smoothScrollToPosition(h - 1);
            }
        }
        e(this.f.e(), b);
    }

    private void b(int i, double d2) {
        this.ad.d(i, d2, this.f.e());
        if (this.f.e() >= mjv.b().length) {
            return;
        }
        int color = getResources().getColor(mjv.b()[this.f.e()].intValue());
        this.aj.setBackgroundColor(color);
        this.ak.setBackgroundColor(color);
    }

    private void e(int i, List<LevelInfoDesc> list) {
        int dimensionPixelOffset;
        if (koq.b(list, i)) {
            LogUtil.h("PLGACHIEVE_AchieveNewLevelMainActivity", "refreshLayout isOutOfBounds!");
            return;
        }
        this.u.setText(list.get(i).getName());
        this.y.setText(list.get(i).getDescription());
        if (i < mjv.b().length) {
            this.j.setBackgroundColor(getColor(mjv.b()[i].intValue()));
        }
        this.f.d(list.get(i).getLabelList(), i);
        if (i < this.am) {
            this.al.setVisibility(8);
        }
        if (this.t == null) {
            LogUtil.h("PLGACHIEVE_AchieveNewLevelMainActivity", "refreshLayout mLabelList is null!");
            return;
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
        if (this.t.size() > 3) {
            dimensionPixelOffset = getResources().getDimensionPixelOffset(nsn.p() ? R.dimen._2131362866_res_0x7f0a0432 : R.dimen._2131363131_res_0x7f0a053b);
        } else if (this.t.size() == 0) {
            dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen._2131363083_res_0x7f0a050b);
        } else {
            dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen._2131363103_res_0x7f0a051f);
        }
        layoutParams.setMargins(0, dimensionPixelOffset, 0, 0);
        this.aa.setLayoutParams(layoutParams);
        d(this.t, this.am - 1);
        ArrayList<String> arrayList = this.an;
        String str = (arrayList == null || arrayList.size() <= 9) ? "1.1" : this.an.get(9);
        String str2 = mlg.e(mht.d(str), 0.0d) != 0 ? str : "1.1";
        LogUtil.a("PLGACHIEVE_AchieveNewLevelMainActivity", "parseLevelList ruleRate ", str2);
        this.ao.setText(getResources().getString(R.string._2130841070_res_0x7f020dee, getResources().getString(R.string._2130850221_res_0x7f0231ad, str2)));
        this.ac.setText(getResources().getString(R.string._2130841059_res_0x7f020de3, str2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.pluginachievement.ui.AchieveNewLevelMainActivity.7
            @Override // java.lang.Runnable
            public void run() {
                AchieveNewLevelMainActivity achieveNewLevelMainActivity = AchieveNewLevelMainActivity.this;
                achieveNewLevelMainActivity.b(achieveNewLevelMainActivity.av, new AchieveCallback() { // from class: com.huawei.pluginachievement.ui.AchieveNewLevelMainActivity.7.4
                    @Override // com.huawei.pluginachievement.impl.AchieveCallback
                    public void onResponse(int i, Object obj) {
                        if (i == 200) {
                            AchieveNewLevelMainActivity.this.l();
                        }
                    }
                });
            }
        });
    }

    public void e() {
        AchieveLevelInfoObserver achieveLevelInfoObserver = new AchieveLevelInfoObserver(BaseApplication.getContext());
        HashMap hashMap = new HashMap(8);
        this.av.b(achieveLevelInfoObserver);
        this.av.a(13, hashMap);
        meh.c((Context) this).f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        this.av.a(11, new HashMap(2));
    }

    @Override // com.huawei.pluginachievement.impl.AchieveObserver
    public void onDataChanged(int i, UserAchieveWrapper userAchieveWrapper) {
        LogUtil.a("PLGACHIEVE_AchieveNewLevelMainActivity", "onDataChanged error=", Integer.valueOf(i));
        if (i == -1) {
            return;
        }
        d(1002, (int) 0);
        if (userAchieveWrapper == null) {
            return;
        }
        LogUtil.a("PLGACHIEVE_AchieveNewLevelMainActivity", "onDataChanged resultCode=", userAchieveWrapper.getResultCode());
        if ("0".equals(userAchieveWrapper.getResultCode())) {
            if (userAchieveWrapper.getContentType() == 11) {
                d(11, (int) null);
            } else if (userAchieveWrapper.getContentType() == 13) {
                b();
            } else {
                LogUtil.c("PLGACHIEVE_AchieveNewLevelMainActivity", "onDataChanged content type:", Integer.valueOf(userAchieveWrapper.getContentType()));
            }
        }
    }

    @Override // com.huawei.pluginachievement.ui.kakatask.AchieveKaKaTaskClickListener
    public void onTaskClick(String str, String str2, int i) {
        LogUtil.a("PLGACHIEVE_AchieveNewLevelMainActivity", "onTaskClick code =", str, " taskId =", str2, "position =", Integer.valueOf(i));
        if (koq.b(this.bb, i)) {
            LogUtil.h("PLGACHIEVE_AchieveNewLevelMainActivity", "onTaskClick position is out of taskRecyclerViewDatas bounds,return");
            return;
        }
        mkj b = this.bb.get(i).b();
        if (b == null) {
            LogUtil.h("PLGACHIEVE_AchieveNewLevelMainActivity", "onTaskClick achieveTask is null");
            return;
        }
        if ("0".equals(str)) {
            c(b);
            mer.b(this.g).b(b, this.g);
        } else if ("3".equals(str)) {
            e(b, i);
        } else {
            LogUtil.a("PLGACHIEVE_AchieveNewLevelMainActivity", "task code:", str);
        }
    }

    private void c(mkj mkjVar) {
        if (mkjVar == null) {
            LogUtil.h("PLGACHIEVE_AchieveNewLevelMainActivity", "doTaskClickBi achieveTask is null");
            return;
        }
        HashMap hashMap = new HashMap(8);
        hashMap.put("click", 1);
        hashMap.put("currentLevel", Integer.valueOf(this.x));
        hashMap.put(ParsedFieldTag.TASK_NAME, mkjVar.a());
        hashMap.put("taskid", mkjVar.e());
        hashMap.put(ParsedFieldTag.TASK_TYPE, mle.d(mkjVar.n()) ? "beginnerTasks" : "dailyTasks");
        if (mkjVar.k() == 0) {
            hashMap.put("isVip", "false");
        } else {
            hashMap.put("isVip", "true");
        }
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.LEVEL_SHARE_1100071.value(), hashMap, 0);
    }

    private void e(final mkj mkjVar, int i) {
        String string;
        if (koq.b(this.bb, i)) {
            LogUtil.h("PLGACHIEVE_AchieveNewLevelMainActivity", "dealTaskFinished IndexOutOfBoundsException");
            return;
        }
        if (mkjVar.n() == 10001) {
            string = getString(R.string._2130841938_res_0x7f021152);
        } else {
            string = getString(R.string._2130840768_res_0x7f020cc0);
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.g);
        builder.e(mkjVar.c());
        builder.czE_(string, new View.OnClickListener() { // from class: com.huawei.pluginachievement.ui.AchieveNewLevelMainActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (mkjVar.n() != 10001) {
                    mer.b(AchieveNewLevelMainActivity.this.g).b(mkjVar, AchieveNewLevelMainActivity.this.g);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        this.i = e;
        e.setCancelable(true);
        this.i.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> void d(int i, T t) {
        Handler handler = this.o;
        if (handler == null) {
            LogUtil.h("PLGACHIEVE_AchieveNewLevelMainActivity", "sendHandlerMessage handler is null!");
            return;
        }
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = t;
        handler.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        Toast.makeText(BaseApplication.getContext(), str, 0).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(meh mehVar, AchieveCallback achieveCallback) {
        LogUtil.a("PLGACHIEVE_AchieveNewLevelMainActivity", "freshLevelTask");
        if (mehVar == null) {
            LogUtil.h("PLGACHIEVE_AchieveNewLevelMainActivity", "freshLevelTask service == null");
            return;
        }
        HashMap hashMap = new HashMap(2);
        hashMap.put(ParsedFieldTag.KAKA_TASK_SCENARIO, String.valueOf(2));
        List<mcz> b = mehVar.b(12, hashMap);
        if (b != null) {
            LogUtil.a("PLGACHIEVE_AchieveNewLevelMainActivity", "freshLevelTask tasks size = ", Integer.valueOf(b.size()));
            ArrayList arrayList = new ArrayList(b.size());
            for (mcz mczVar : b) {
                if (mczVar instanceof mdf) {
                    mdf mdfVar = (mdf) mczVar;
                    if (mdfVar.ag() == 20007) {
                        mer.b(BaseApplication.getContext()).c(mdfVar);
                    }
                    if (mdfVar.ag() == 20002 && String.valueOf(0).equals(mdfVar.p())) {
                        meh.c(BaseApplication.getContext()).e((CountDownLatch) null);
                    }
                    if (mdfVar.ag() == 10004 && nsn.e(mdfVar.p()) < 1) {
                        mct.b(this.g, "isFirstBindDeviceFinishedV2", "");
                    }
                    arrayList.add(mdfVar);
                }
            }
            this.bb = mji.c((List<mdf>) arrayList);
            d(FitnessStatusCodes.UNKNOWN_AUTH_ERROR, (int) null);
            e(achieveCallback);
            return;
        }
        LogUtil.h("PLGACHIEVE_AchieveNewLevelMainActivity", "freshLevelTask tasks == null");
        d(FitnessStatusCodes.UNKNOWN_AUTH_ERROR, (int) null);
        e(achieveCallback);
    }

    private void e(AchieveCallback achieveCallback) {
        if (achieveCallback != null) {
            achieveCallback.onResponse(200, 0);
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        meh mehVar = this.av;
        if (mehVar != null) {
            mehVar.c((AchieveObserver) this);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        mlc.a(5, this.x, (SystemClock.elapsedRealtime() - this.n) / 1000, this.ba, this.p);
        meh mehVar = this.av;
        if (mehVar != null) {
            mehVar.c((AchieveObserver) this);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        HashMap hashMap = new HashMap(8);
        hashMap.put("click", 1);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.LEVEL_SHARE_1100072.value(), hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (this.ba != null) {
            this.ba = mlc.d(this.bb, 0);
            this.h = mlc.d(this.bb, 1);
            boolean c = mlc.c(this.bb);
            this.p = c;
            this.f.c(this.ba, c);
        }
        int i = this.m;
        if (i == 0) {
            this.m = i + 1;
            mlc.a(0, this.x, 0L, this.ba, this.p);
            this.n = SystemClock.elapsedRealtime();
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
