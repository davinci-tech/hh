package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.location.LocationRequestCompat;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.arkuix.utils.ArkUIXConstants;
import com.huawei.health.device.callback.WeightInsertStatusCallback;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.weight.bean.WeightTargetDifferences;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiGoalInfo;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.datepicker.HealthDatePicker;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.edittext.HealthEditText;
import com.huawei.ui.commonui.edittext.HealthErrorTipTextLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.scrollview.ScrollScaleView;
import com.huawei.ui.commonui.seekbar.HealthSeekBar;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.WeightGoalActivity;
import com.huawei.ui.main.stories.health.model.weight.card.CardConstants;
import com.huawei.ui.main.stories.health.views.WeightScrollScaleView;
import com.huawei.uikit.hwdatepicker.widget.HwDatePicker;
import defpackage.cfi;
import defpackage.ckm;
import defpackage.dfd;
import defpackage.doj;
import defpackage.gni;
import defpackage.gnm;
import defpackage.grz;
import defpackage.gsi;
import defpackage.ixx;
import defpackage.jdl;
import defpackage.kot;
import defpackage.nrh;
import defpackage.nry;
import defpackage.nsn;
import defpackage.qpz;
import defpackage.qry;
import defpackage.qsj;
import defpackage.qzw;
import defpackage.rag;
import defpackage.sdk;
import health.compact.a.AuthorizationUtils;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class WeightGoalActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private Context f10099a;
    private HiUserInfo ab;
    private int ad;
    private HealthTextView ae;
    private ImageView af;
    private LinearLayout ag;
    private HealthCardView ah;
    private View al;
    private View am;
    private HealthTextView an;
    private LinearLayout ao;
    private WeightScrollScaleView ap;
    private HealthTextView aq;
    private HealthTextView ar;
    private ImageView as;
    private HealthTextView at;
    private HealthCardView ax;
    private HealthTextView ay;
    private int az;
    private HealthTextView ba;
    private HealthTextView bb;
    private HealthTextView bc;
    private float bd;
    private HealthButton bg;
    private HealthTextView bi;
    private double bj;
    private HealthSeekBar bl;
    private double bm;
    private HealthTextView bn;
    private HealthTextView bo;
    private HealthTextView bp;
    private HealthTextView bq;
    private View br;
    private HealthCardView bs;
    private LinearLayout bt;
    private ImageView bu;
    private ImageView bv;
    private HealthTextView bw;
    private LinearLayout bx;
    private LinearLayout by;
    private ImageView bz;
    private CustomTitleBar ca;
    private LinearLayout cb;
    private HealthTextView cc;
    private float cd;
    private WeightScrollScaleView cf;
    private float cg;
    private HealthTextView ch;
    private HealthTextView ci;
    private FrameLayout cj;
    private LinearLayout ck;
    private LinearLayout cl;
    private HealthTextView cm;
    private WeightTargetDifferences cn;
    private HealthTextView cp;
    private gsi cq;
    private HealthTextView cr;
    private int d;
    private boolean e;
    private IntPlan f;
    private HealthCardView g;
    private HealthTextView h;
    private WeightScrollScaleView i;
    private HealthEditText l;
    private HealthDatePicker m;
    private int n;
    private HealthTextView o;
    private ImageView p;
    private HealthCardView q;
    private HealthTextView r;
    private LinearLayout s;
    private ImageView t;
    private int u;
    private LinearLayout w;
    private double x;
    private int bh = 0;
    private final i ce = new i();
    private double k = 0.0d;
    private int au = 1;
    private final HiGoalInfo aa = new HiGoalInfo();
    private long bk = 0;
    private double j = MultiUsersManager.INSTANCE.getMainUser().m();
    private double ak = 0.0d;
    private double aj = 0.0d;
    private boolean aw = true;
    private int co = 0;
    private boolean av = false;
    private int v = 0;
    private final int[] be = {10, 250, 22, 552};
    private final dfd z = new dfd(10006);
    private double ai = 10.0d;
    private double ac = 251.0d;
    private double bf = 10.0d;
    private double b = 10.0d;
    private double c = 251.0d;
    private float cu = MultiUsersManager.INSTANCE.getMainUser().f();
    private Boolean y = false;

    /* loaded from: classes6.dex */
    static class i extends BaseHandler<WeightGoalActivity> {
        private i(WeightGoalActivity weightGoalActivity) {
            super(weightGoalActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dBM_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(WeightGoalActivity weightGoalActivity, Message message) {
            int i = message.what;
            if (i == 2) {
                weightGoalActivity.ag();
                return;
            }
            if (i == 3) {
                if (message.obj instanceof WeightTargetDifferences) {
                    WeightTargetDifferences weightTargetDifferences = (WeightTargetDifferences) message.obj;
                    weightGoalActivity.a(weightTargetDifferences);
                    weightGoalActivity.c(weightTargetDifferences);
                    return;
                }
                return;
            }
            if (i == 4) {
                if (weightGoalActivity == null || weightGoalActivity.isFinishing()) {
                    LogUtil.b("HealthWeight_WeightGoalActivity", "activity is null or finishing");
                    return;
                } else {
                    Object obj = message.obj;
                    weightGoalActivity.e(weightGoalActivity, obj instanceof Float ? ((Float) obj).floatValue() : 0.0f);
                    return;
                }
            }
            LogUtil.a("HealthWeight_WeightGoalActivity", "handleMessageWhenReferenceNotNull default");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(WeightGoalActivity weightGoalActivity, float f) {
        cfi mainUser = MultiUsersManager.INSTANCE.getMainUser();
        if (mainUser != null) {
            mainUser.c((float) this.j);
            mainUser.d(f);
        }
        Intent intent = new Intent(weightGoalActivity, (Class<?>) WeightGoalPreviewActivity.class);
        intent.putExtra("WEIGHT_GOAL_ACTION", 0);
        intent.putExtra("IS_WEIGHT_CURRENT_GOAL_EDIT_TYPE", weightGoalActivity.av);
        intent.putExtra("weightManager", this.cq);
        intent.putExtra("startWeight", this.j);
        intent.putExtra("targetWeight", f);
        gnm.aPB_(BaseApplication.getActivity(), intent);
        qsj.dIi_(this, true);
        weightGoalActivity.finish();
    }

    /* loaded from: classes6.dex */
    static class d implements WeightInsertStatusCallback {
        WeakReference<i> b;
        float d;

        d(i iVar, float f) {
            this.b = new WeakReference<>(iVar);
            this.d = f;
        }

        @Override // com.huawei.health.device.callback.WeightInsertStatusCallback
        public void isSuccess(boolean z) {
            i iVar = this.b.get();
            if (iVar != null) {
                LogUtil.a("HealthWeight_WeightGoalActivity", "InsertWeightResponseCallback,insert successful");
                Message obtainMessage = iVar.obtainMessage();
                obtainMessage.what = 4;
                obtainMessage.obj = Float.valueOf(this.d);
                iVar.sendMessage(obtainMessage);
                return;
            }
            LogUtil.b("HealthWeight_WeightGoalActivity", "handler is null");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.health_data_weight_goal_set);
        this.f10099a = this;
        int a2 = UnitUtil.a();
        this.co = a2;
        LogUtil.a("HealthWeight_WeightGoalActivity", "onCreate mWeightShowUnit ", Integer.valueOf(a2));
        r();
        l();
        f();
    }

    private void l() {
        this.cb.setOnClickListener(this);
        this.w.setOnClickListener(this);
        this.ck.setOnClickListener(this);
        this.by.setOnClickListener(this);
        this.ao.setOnClickListener(this);
        this.bg.setOnClickListener(this);
        this.as.setOnClickListener(this);
        this.cp.setOnClickListener(this);
        this.ci.setOnClickListener(this);
        this.ch.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.ae.setOnClickListener(this);
        this.an.setOnClickListener(this);
        this.p.setOnClickListener(this);
        this.bv.setOnClickListener(this);
    }

    private void r() {
        this.ca = (CustomTitleBar) findViewById(R.id.health_healthdata_weightgoal_title_layout);
        this.br = findViewById(R.id.health_data_start_weight_and_date_cardView);
        this.ca.setTitleBarBackgroundColor(getResources().getColor(R.color._2131299296_res_0x7f090be0));
        this.cl = (LinearLayout) findViewById(R.id.hw_weight_management_goal_content);
        this.cj = (FrameLayout) findViewById(R.id.hw_weight_management_goal_cardview);
        this.cb = (LinearLayout) findViewById(R.id.health_data_target_weight_title);
        this.w = (LinearLayout) findViewById(R.id.health_data_finish_title);
        this.bw = (HealthTextView) findViewById(R.id.hw_target_weight_text);
        this.bt = (LinearLayout) findViewById(R.id.health_data_target_weight_content);
        this.s = (LinearLayout) findViewById(R.id.hw_weight_management_finish_date_content);
        this.bu = (ImageView) findViewById(R.id.hw_target_weight_icon);
        this.t = (ImageView) findViewById(R.id.hw_finish_date_icon);
        this.r = (HealthTextView) findViewById(R.id.hw_finish_date_text);
        this.ck = (LinearLayout) findViewById(R.id.hw_show_health_data_weight_layout);
        this.cr = (HealthTextView) findViewById(R.id.hw_show_health_data_weight_mid_weight);
        this.by = (LinearLayout) findViewById(R.id.health_data_weight_change_title);
        this.ao = (LinearLayout) findViewById(R.id.health_data_hold_weight_title);
        this.bx = (LinearLayout) findViewById(R.id.health_data_weight_change_content);
        this.ag = (LinearLayout) findViewById(R.id.health_data_hold_weight_content);
        this.bz = (ImageView) findViewById(R.id.hw_health_current_weight_icon);
        this.af = (ImageView) findViewById(R.id.hw_hold_weight_icon);
        this.cc = (HealthTextView) findViewById(R.id.health_data_weight_change_text);
        this.bg = (HealthButton) findViewById(R.id.hw_show_health_data_weight_goal);
        this.as = (ImageView) findViewById(R.id.hw_show_health_data_weight_suggestion_icon);
        this.cm = (HealthTextView) findViewById(R.id.hw_show_health_data_mid_weight);
        this.cf = (WeightScrollScaleView) findViewById(R.id.health_healthdata_weight_goal_scale);
        this.i = (WeightScrollScaleView) findViewById(R.id.health_data_expect_current_weight_scale);
        this.ap = (WeightScrollScaleView) findViewById(R.id.health_healthdata_hold_weight_goal_scale);
        this.h = (HealthTextView) findViewById(R.id.hw_health_data_current_weight);
        this.aq = (HealthTextView) findViewById(R.id.hw_hold_weight_text);
        this.g = (HealthCardView) findViewById(R.id.health_data_current_weight_cardview);
        this.cp = (HealthTextView) findViewById(R.id.hw_weight_management_reduce_text);
        this.ci = (HealthTextView) findViewById(R.id.hw_weight_management_gain_text);
        this.ch = (HealthTextView) findViewById(R.id.hw_weight_management_hold_text);
        this.q = (HealthCardView) findViewById(R.id.health_data_target_finish_date_cardview);
        this.bs = (HealthCardView) findViewById(R.id.health_data_target_weight_cardview);
        this.ah = (HealthCardView) findViewById(R.id.health_data_target_hold_weight_cardview);
        this.m = (HealthDatePicker) findViewById(R.id.select_finish_date_view);
        this.ba = (HealthTextView) findViewById(R.id.health_data_lose_weight_rate_progress);
        this.at = (HealthTextView) findViewById(R.id.health_data_lose_weight_rate_description);
        this.ax = (HealthCardView) findViewById(R.id.health_data_lose_weight_rate_cardview);
        this.bc = (HealthTextView) findViewById(R.id.health_data_lose_weight_rate_description_recommend);
        t();
        q();
        this.bb = (HealthTextView) findViewById(R.id.need_weight_value);
        this.o = (HealthTextView) findViewById(R.id.finish_date_tips);
        this.ay = (HealthTextView) findViewById(R.id.need_weight_hold_value);
        this.bp = (HealthTextView) findViewById(R.id.start_weight_text);
        this.bq = (HealthTextView) findViewById(R.id.start_weight_time);
        o();
        p();
        d();
    }

    private void t() {
        this.bl = (HealthSeekBar) findViewById(R.id.lose_weight_rate_card_seek_bar);
        if (LanguageUtil.bp(this.f10099a)) {
            this.bl.setRotation(180.0f);
        }
        this.bl.setTouchable(true);
        this.bl.setOnSeekBarChangeListener(new HealthSeekBar.OnSeekBarChangeListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.WeightGoalActivity.1
            @Override // com.huawei.ui.commonui.seekbar.HealthSeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(HealthSeekBar healthSeekBar) {
            }

            @Override // com.huawei.ui.commonui.seekbar.HealthSeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(HealthSeekBar healthSeekBar) {
            }

            @Override // com.huawei.ui.commonui.seekbar.HealthSeekBar.OnSeekBarChangeListener
            public void onProgressChanged(HealthSeekBar healthSeekBar, int i2, boolean z) {
                String string;
                if (i2 <= 30) {
                    WeightGoalActivity.this.au = 1;
                    healthSeekBar.setProgress(0);
                    string = BaseApplication.getContext().getResources().getString(R$string.IDS_hwh_home_lose_weight_rate_normal);
                    WeightGoalActivity.this.at.setText(string);
                } else if (i2 <= 60) {
                    WeightGoalActivity.this.au = 2;
                    healthSeekBar.setProgress(45);
                    string = BaseApplication.getContext().getResources().getString(R$string.IDS_hwh_home_lose_weight_rate_quick);
                } else {
                    WeightGoalActivity.this.au = 3;
                    healthSeekBar.setProgress(90);
                    string = BaseApplication.getContext().getResources().getString(R$string.IDS_hwh_home_lose_weight_rate_radical);
                }
                LogUtil.a("HealthWeight_WeightGoalActivity", "onProgressChanged mLoseWeightRate ", Integer.valueOf(WeightGoalActivity.this.au));
                WeightGoalActivity.this.at.setText(string);
                WeightGoalActivity weightGoalActivity = WeightGoalActivity.this;
                weightGoalActivity.dBJ_(weightGoalActivity.bc, WeightGoalActivity.this.au != 1 ? 8 : 0);
                WeightGoalActivity.this.g();
                WeightGoalActivity.this.an();
                WeightGoalActivity.this.av();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        double[] c2 = qsj.c(this.au, this.j);
        double d2 = c2[0];
        double d3 = c2[1];
        LogUtil.a("HealthWeight_WeightGoalActivity", "getLoseWeightRange low ", Double.valueOf(d2), " high ", Double.valueOf(d3));
        this.ba.setText(dBL_(String.format(BaseApplication.getContext().getResources().getString(R$string.IDS_hwh_home_avg_weekly_weight_loss_kg), getResources().getString(R$string.IDS_hw_pressure_grade_range, UnitUtil.e(d2, 1, 1), qsj.e(d3, 1)))));
    }

    private void d() {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        hashMap.put(CommonUtil.PAGE_TYPE, Integer.valueOf(this.av ? 2 : 1));
        hashMap.put("from", Integer.valueOf(this.v));
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.WEIGHT_EDIT_GOAL_CLICKED_2040148.value(), hashMap, 0);
    }

    private void q() {
        View inflate;
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.weight_range_layout);
        if (LanguageUtil.ac(this.f10099a)) {
            inflate = View.inflate(this.f10099a, R.layout.health_data_weight_goal_set_scope_hebrew, null);
        } else {
            inflate = View.inflate(this.f10099a, R.layout.health_data_weight_goal_set_scope, null);
        }
        relativeLayout.addView(inflate);
        this.ae = (HealthTextView) inflate.findViewById(R.id.hw_weight_management_hold_left_value);
        this.an = (HealthTextView) inflate.findViewById(R.id.hw_weight_management_hold_right_value);
        this.al = inflate.findViewById(R.id.hw_weight_management_hold_left_view);
        this.am = inflate.findViewById(R.id.hw_weight_management_hold_right_view);
        this.ar = (HealthTextView) inflate.findViewById(R.id.hw_weight_management_hold_unit);
    }

    private void o() {
        this.p = (ImageView) findViewById(R.id.hw_show_finish_date_suggestion_icon);
        this.bo = (HealthTextView) findViewById(R.id.start_current_weight_title);
        this.bv = (ImageView) findViewById(R.id.hold_data_weight_suggestion_icon);
        this.bn = (HealthTextView) findViewById(R.id.hold_health_data_mid_weight);
    }

    private void p() {
        CardConstants.e(this.cp);
        CardConstants.e(this.ci);
        CardConstants.e(this.ch);
        dBI_(this.bu);
        dBI_(this.t);
        dBI_(this.bz);
        dBI_(this.af);
        am();
        this.cj.setVisibility(0);
        aj();
        ah();
        cancelAdaptRingRegion();
        setViewSafeRegion(false, findViewById(R.id.health_data_weight_goal_rl));
        this.m.setmIsSupportLunarSwitch(false);
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(System.currentTimeMillis());
        c(gregorianCalendar);
        GregorianCalendar gregorianCalendar2 = new GregorianCalendar();
        gregorianCalendar2.setTimeInMillis(LocationRequestCompat.PASSIVE_INTERVAL);
        this.m.a(gregorianCalendar, gregorianCalendar2);
        if (getIntent() != null && getIntent().getExtras() != null) {
            if (getIntent().getExtras().getInt("WEIGHT_GOAL_ACTION", 0) == 1) {
                this.av = true;
                b();
            }
            this.v = getIntent().getExtras().getInt("from", 0);
            aa();
        }
        n();
        this.m.setSpinnersSelectorPaintColor(getColor(R.color._2131299374_res_0x7f090c2e));
        b(this.ap);
        b(this.i);
        b(this.cf);
        dBK_(this.aq, this.ag, this.af, true);
    }

    private void aa() {
        if (getIntent().getExtras().getBoolean("IS_SELECT_KEEP_WEIGHT", false)) {
            this.ch.postDelayed(new Runnable() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.WeightGoalActivity.5
                @Override // java.lang.Runnable
                public void run() {
                    WeightGoalActivity.this.ch.performClick();
                }
            }, 100L);
        }
    }

    private void n() {
        Uri zs_ = AppRouterUtils.zs_(getIntent());
        if (zs_ == null) {
            LogUtil.h("HealthWeight_WeightGoalActivity", "mSchemeData == null");
            return;
        }
        if (AuthorizationUtils.a(this)) {
            int h = health.compact.a.CommonUtil.h(zs_.getQueryParameter(ArkUIXConstants.FROM_TYPE));
            LogUtil.a("HealthWeight_WeightGoalActivity", "initSchemeData weightGoalAction", Integer.valueOf(h));
            if (h == 1) {
                this.av = true;
                b();
            }
            this.v = health.compact.a.CommonUtil.h(zs_.getQueryParameter("from"));
        }
    }

    private void b() {
        this.cl.setVisibility(8);
        this.p.setVisibility(0);
        this.bo.setText(R$string.IDS_hwh_home_current_weight);
        this.ca.setTitleText(getResources().getString(R$string.IDS_hwh_home_adjust_current_target));
    }

    private void c(GregorianCalendar gregorianCalendar) {
        this.m.c(gregorianCalendar.get(1), gregorianCalendar.get(2) + 1, gregorianCalendar.get(5), new HwDatePicker.OnDateChangedListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.WeightGoalActivity.2
            @Override // com.huawei.uikit.hwdatepicker.widget.HwDatePicker.OnDateChangedListener
            public void onDateChanged(HwDatePicker hwDatePicker, int i2, int i3, int i4, GregorianCalendar gregorianCalendar2) {
                long h = WeightGoalActivity.this.h();
                WeightGoalActivity.this.e(h);
                WeightGoalActivity.this.r.setText(DateFormatUtil.d(h, DateFormatUtil.DateFormatType.DATE_FORMAT_YYYYMD));
            }
        });
    }

    private void e() {
        WeightTargetDifferences b2 = rag.b();
        if (b2 == null || this.cg == 0.0f) {
            LogUtil.h("HealthWeight_WeightGoalActivity", "fetchDailyWeightGoal weightTargetDifferences ", b2);
            Message obtainMessage = this.ce.obtainMessage();
            obtainMessage.what = 2;
            this.ce.sendMessage(obtainMessage);
            return;
        }
        Message obtainMessage2 = this.ce.obtainMessage();
        obtainMessage2.what = 3;
        obtainMessage2.obj = b2;
        this.ce.sendMessage(obtainMessage2);
    }

    private void aj() {
        this.i.setOnSelectedListener(new ScrollScaleView.OnSelectedListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.WeightGoalActivity.3
            @Override // com.huawei.ui.commonui.scrollview.ScrollScaleView.OnSelectedListener
            public void onSelected(List<String> list, int i2) {
                WeightGoalActivity.this.c(i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i2) {
        i(i2);
        e(i2);
        au();
        w();
        g();
    }

    private void w() {
        if (ac()) {
            LogUtil.a("HealthWeight_WeightGoalActivity", "limitHoldWeightScrollView noSetHeight()");
            return;
        }
        double d2 = this.bm;
        double d3 = this.j;
        if (d2 <= d3 && this.bj >= d3) {
            aq();
            double d4 = this.bm;
            this.ai = d4;
            double d5 = this.bj;
            this.ac = d5;
            this.ap.setData(rag.c((int) d4, ((int) Math.ceil(d5)) + 1), 10, 40);
            this.ap.setSelectedPosition(a(this.bm, (int) this.ai));
            return;
        }
        if (d3 < d2) {
            this.ak = d3;
            this.ai = d3;
            double d6 = this.bj;
            this.ac = d6;
            this.ap.setData(rag.c((int) d3, ((int) Math.ceil(d6)) + 1), 10, 40);
            aq();
            this.ap.setSelectedPosition(a(this.ak, (int) this.ai));
            return;
        }
        this.aj = d3;
        this.ai = d2;
        this.ac = d3;
        this.ap.setData(rag.c((int) d2, ((int) Math.ceil(d3)) + 1), 10, 40);
        ap();
        this.ap.setSelectedPosition(a(this.aj, (int) this.ai));
    }

    private void e(int i2) {
        int d2 = d(this.x);
        if (this.d == 0 && i2 <= d2) {
            this.cf.setSelectedPosition(i2 + 1);
        }
        if (this.d == 1 && i2 >= d2) {
            this.cf.setSelectedPosition(d2 - 1);
        }
        if (this.av) {
            if (this.j > qsj.c(this.cu, this.au, this.ad)) {
                av();
                return;
            }
            return;
        }
        av();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void av() {
        double c2 = qsj.c(this.av ? this.cu : this.j, this.au, this.ad);
        this.bf = c2;
        LogUtil.a("HealthWeight_WeightGoalActivity", "updateWeightGoalScroll mReduceScaleMinValue: ", Double.valueOf(c2));
        if (this.av && this.j <= this.bf) {
            this.bf = 10.0d;
        }
        double d2 = this.x;
        if (d2 < 10.0d || d2 > this.bf) {
            return;
        }
        LogUtil.a("HealthWeight_WeightGoalActivity", "updateWeightGoalScroll mWeightGoal: ", Float.valueOf(this.cg));
        this.cf.setSelectedPosition(d(this.bf));
    }

    private void ah() {
        this.ap.setOnSelectedListener(new ScrollScaleView.OnSelectedListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.WeightGoalActivity.7
            @Override // com.huawei.ui.commonui.scrollview.ScrollScaleView.OnSelectedListener
            public void onSelected(List<String> list, int i2) {
                int b2 = WeightGoalActivity.this.aw ? WeightGoalActivity.this.b(i2) : WeightGoalActivity.this.a(i2);
                if (b2 != i2) {
                    WeightGoalActivity.this.ap.setSelectedPosition(b2);
                }
                WeightGoalActivity.this.g(b2);
                WeightGoalActivity.this.au();
            }
        });
    }

    private void x() {
        LogUtil.a("HealthWeight_WeightGoalActivity", "mHoldWeightRangeHighValue = ", Double.valueOf(this.aj), " mHoldScaleMinValue = ", Double.valueOf(this.ai), " mHoldWeightRangeLowValue = ", Double.valueOf(this.ak), "mHoldScaleMaxValue = ", Double.valueOf(this.ac), "mHoldScaleMinValue = ", Double.valueOf(this.ai));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a(int i2) {
        int a2 = a(this.ak, (int) this.ai) + 1;
        if (i2 < a2) {
            i2 = a2;
        }
        int a3 = a(this.ac, (int) this.ai);
        return i2 > a3 ? a3 : i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int b(int i2) {
        int a2 = a(this.aj, (int) this.ai) - 1;
        if (i2 > a2) {
            i2 = a2;
        }
        int a3 = a(this.ai, (int) r0);
        return i2 < a3 ? a3 : i2;
    }

    private void b(ScrollScaleView scrollScaleView) {
        scrollScaleView.setData(rag.d(), 10, 40);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(WeightTargetDifferences weightTargetDifferences) {
        if (this.av) {
            b(weightTargetDifferences);
        } else {
            au();
        }
        if (!this.av || weightTargetDifferences.d() == WeightTargetDifferences.WeightTargetType.WEIGHT_KEE) {
            return;
        }
        this.br.setVisibility(0);
        if (MultiUsersManager.INSTANCE.getMainUser() != null) {
            this.bp.setText(qsj.e(UnitUtil.c(MultiUsersManager.INSTANCE.getMainUser().f(), 1), 1));
        } else {
            LogUtil.b("HealthWeight_WeightGoalActivity", "mainUser is null ,retur");
        }
        if (weightTargetDifferences.e() != 0) {
            this.bq.setText(getResources().getString(R.string._2130839866_res_0x7f02093a, DateFormatUtil.d(weightTargetDifferences.e(), DateFormatUtil.DateFormatType.DATE_FORMAT_YYYYMD)));
        }
    }

    private void b(final WeightTargetDifferences weightTargetDifferences) {
        if (System.currentTimeMillis() < weightTargetDifferences.b()) {
            this.m.post(new Runnable() { // from class: qfs
                @Override // java.lang.Runnable
                public final void run() {
                    WeightGoalActivity.this.e(weightTargetDifferences);
                }
            });
        }
    }

    public /* synthetic */ void e(WeightTargetDifferences weightTargetDifferences) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(weightTargetDifferences.b());
        this.m.a(calendar.get(1), calendar.get(2) + 1, calendar.get(5));
        e(h());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(long j2) {
        LogUtil.a("HealthWeight_WeightGoalActivity", "setFinishDateTipsView finishDate:", Long.valueOf(j2));
        int d2 = jdl.d(DateFormatUtil.b(System.currentTimeMillis()), DateFormatUtil.b(j2));
        double d3 = d2;
        String quantityString = getResources().getQuantityString(R.plurals._2130903355_res_0x7f03013b, d2, UnitUtil.e(d3, 1, 0));
        double abs = Math.abs(this.x - this.j) / (d3 / 7.0d);
        int i2 = this.d;
        if (i2 == 1) {
            a(quantityString, abs);
        } else if (i2 == 0) {
            e(quantityString, abs);
            o(abs);
        } else {
            LogUtil.a("HealthWeight_WeightGoalActivity", "other targetType");
        }
    }

    private void o(double d2) {
        if (d2 > 1.0d) {
            this.p.setVisibility(8);
            this.o.setText(getString(R$string.IDS_weight_loss_diff_four_hint, new Object[]{qsj.e(UnitUtil.c(1.0d, 0), 0)}));
            this.o.setTextColor(getColor(R.color._2131298958_res_0x7f090a8e));
        }
    }

    private void e(String str, double d2) {
        double c2 = UnitUtil.c(d2, 2);
        if (this.av) {
            this.o.setText(getString(R$string.IDS_hwh_home_avg_weekly_weight_loss_kg, new Object[]{qsj.e(c2, 1)}));
            this.p.setVisibility(0);
        } else {
            this.o.setText(dBL_(getResources().getString(R$string.IDS_hwh_home_days_to_achieve_target, str, qsj.e(c2, 2))));
        }
        this.o.setTextColor(getColor(R.color._2131299236_res_0x7f090ba4));
    }

    private void a(String str, double d2) {
        double c2 = UnitUtil.c(d2, 2);
        if (this.av) {
            this.o.setText(getString(R$string.IDS_hwh_home_avg_weekly_weight_increase_kg, new Object[]{qsj.e(c2, 1)}));
            this.p.setVisibility(0);
        } else {
            this.o.setText(dBL_(getResources().getString(R$string.IDS_hwh_home_days_to_achieve_target_increase, str, qsj.e(c2, 2))));
        }
        this.o.setTextColor(getColor(R.color._2131299236_res_0x7f090ba4));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void au() {
        int i2 = this.d;
        if (i2 == 1) {
            e(0.05d, 5.0d);
        } else if (i2 == 0) {
            e(0.05d, 2.0d);
        } else {
            LogUtil.a("HealthWeight_WeightGoalActivity", "updateDatePicker");
        }
    }

    private void e(double d2, double d3) {
        GregorianCalendar e2 = e(d3, false);
        GregorianCalendar e3 = e(d2, true);
        this.m.a(e2, e3);
        Calendar k = k();
        LogUtil.a("HealthWeight_WeightGoalActivity", "startTime:", Long.valueOf(e2.getTimeInMillis()), "endDate:", Long.valueOf(e3.getTimeInMillis()));
        this.m.a(k.get(1), k.get(2) + 1, k.get(5));
    }

    private Calendar k() {
        GregorianCalendar e2 = e(0.5d, false);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(e2.getTimeInMillis());
        return calendar;
    }

    private GregorianCalendar e(double d2, boolean z) {
        double abs = Math.abs(this.x - this.j);
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(rag.e(abs, d2, z));
        return gregorianCalendar;
    }

    private SpannableString dBL_(String str) {
        return UnitUtil.bCR_(this, "\\d", str, R.style.health_data_value_primary, R.style.health_data_value_secondary);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(WeightTargetDifferences weightTargetDifferences) {
        al();
        this.cn = weightTargetDifferences;
        g(weightTargetDifferences.a());
        int i2 = this.d;
        if (i2 == -1) {
            z();
            ak();
        } else if (i2 == 0) {
            af();
        } else if (i2 != 1) {
            if (i2 == 2) {
                d(weightTargetDifferences);
                c(this.ak, this.aj);
            } else {
                LogUtil.a("HealthWeight_WeightGoalActivity", "setGoalAndChangeWeight mCurrentGoalType is hold");
            }
        }
        ao();
        at();
    }

    private void af() {
        kot.a().b(new c(this));
    }

    /* loaded from: classes6.dex */
    static class c implements ResponseCallback<gsi> {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<WeightGoalActivity> f10101a;

        c(WeightGoalActivity weightGoalActivity) {
            this.f10101a = new WeakReference<>(weightGoalActivity);
        }

        @Override // com.huawei.hwbasemgr.ResponseCallback
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, gsi gsiVar) {
            WeightGoalActivity weightGoalActivity = this.f10101a.get();
            if (weightGoalActivity == null || weightGoalActivity.isFinishing() || weightGoalActivity.isDestroyed()) {
                ReleaseLogUtil.d("HealthWeight_WeightGoalActivity", "InnerGetWeightManager activity ", weightGoalActivity);
                return;
            }
            LogUtil.a("HealthWeight_WeightGoalActivity", "InnerGetWeightManager weightManager ", gsiVar);
            if (gsiVar == null) {
                weightGoalActivity.m();
                return;
            }
            if (gsiVar.b() != null) {
                weightGoalActivity.au = gsiVar.b().a();
            }
            weightGoalActivity.az = weightGoalActivity.au;
            weightGoalActivity.ae();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        this.au = qsj.a(UnitUtil.a((this.cn.a() / 1000.0d) * 7.0d, 1), this.j);
        ae();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ae() {
        if (!HandlerExecutor.c()) {
            HandlerExecutor.a(new Runnable() { // from class: qfo
                @Override // java.lang.Runnable
                public final void run() {
                    WeightGoalActivity.this.ae();
                }
            });
            return;
        }
        LogUtil.a("HealthWeight_WeightGoalActivity", "setLoseRateProgress mLoseWeightRate ", Integer.valueOf(this.au));
        int i2 = this.au;
        if (i2 == 1) {
            this.bl.setProgress(0);
        } else if (i2 == 2) {
            this.bl.setProgress(45);
        } else {
            if (i2 != 3) {
                return;
            }
            this.bl.setProgress(90);
        }
    }

    private void ao() {
        if (!this.av) {
            int i2 = this.d;
            if (i2 == 1 || i2 == 0) {
                this.k = c(i2 == 0);
                return;
            }
        }
        this.k = this.cg;
    }

    private double c(boolean z) {
        double d2 = this.bm;
        double d3 = (this.bj + d2) / 2.0d;
        return z ? (d2 <= 0.0d || this.j <= d3) ? this.j - 1.0d : d3 : (d2 <= 0.0d || this.j >= d3) ? this.j + 1.0d : d3;
    }

    private void d(WeightTargetDifferences weightTargetDifferences) {
        if (weightTargetDifferences.c() == 0) {
            i();
        } else {
            v();
        }
    }

    private void i() {
        if (this.cg == 0.0f) {
            this.cg = this.cd;
        }
        if (v()) {
            return;
        }
        if (this.ak == 0.0d || this.aj == 0.0d) {
            double d2 = this.cg;
            this.ak = d2 - 1.0d;
            this.aj = d2 + 1.0d;
        }
    }

    private boolean v() {
        if (!ac() && !health.compact.a.CommonUtil.c(this.bm)) {
            this.ak = this.bm;
            this.aj = this.bj;
            w();
            x();
            return true;
        }
        LogUtil.a("HealthWeight_WeightGoalActivity", "no set height.");
        return false;
    }

    private void c(double d2, double d3) {
        double d4 = this.aw ? d2 : d3;
        String e2 = UnitUtil.e(UnitUtil.a(d2), 1, 1);
        String e3 = UnitUtil.e(UnitUtil.a(d3), 1, 1);
        this.ap.setSelectedPosition(rag.c(d4, (int) this.ai));
        this.ae.setText(e2);
        this.an.setText(e3);
        this.ar.setText(qsj.d(UnitUtil.c(d3, 1)));
        this.aq.setText(getResources().getString(R$string.IDS_hw_pressure_grade_range, UnitUtil.e(UnitUtil.c(d2, 1), 1, 1), qsj.e(UnitUtil.c(d3, 1), 1)));
    }

    private void i(int i2) {
        double a2 = rag.a(i2, true);
        this.j = a2;
        String e2 = qsj.e(UnitUtil.a(a2), 1);
        this.cc.setText(UnitUtil.bCR_(this.f10099a, "\\d+.\\d+|\\d+", e2, R.style.health_weight_goal_value_text, R.style.health_data_weight_unit));
        this.h.setText(e2);
        y();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g(int i2) {
        double d2 = rag.d((int) this.ai, i2, true);
        if (this.aw) {
            this.ak = d2;
        } else {
            this.aj = d2;
        }
        (this.aw ? this.ae : this.an).setText(UnitUtil.e(UnitUtil.a(d2), 1, 1));
    }

    private void am() {
        this.cf.setOnSelectedListener(new ScrollScaleView.OnSelectedListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.WeightGoalActivity.6
            @Override // com.huawei.ui.commonui.scrollview.ScrollScaleView.OnSelectedListener
            public void onSelected(List<String> list, int i2) {
                WeightGoalActivity.this.h(i2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(int i2) {
        int j2 = j();
        int d2 = d(this.bf);
        if (j2 == d2) {
            int d3 = d(10.0d);
            this.bf = 10.0d;
            d2 = d3;
        }
        int i3 = this.d;
        if (i3 == 0 && i2 >= j2) {
            int i4 = j2 - 1;
            this.cf.setSelectedPosition(i4);
            f(this.cf.c(i4));
        } else if (i3 == 0 && i2 <= d2) {
            this.cf.setSelectedPosition(d2);
            f(this.cf.c(d2));
        } else if (i3 == 1 && i2 <= j2) {
            int i5 = j2 + 1;
            this.cf.setSelectedPosition(i5);
            f(this.cf.c(i5));
        } else {
            f(i2);
        }
        au();
        g();
    }

    private int j() {
        if (!this.av) {
            return d(this.j);
        }
        float f = MultiUsersManager.INSTANCE.getMainUser().f();
        if (f <= 0.0f) {
            return d(this.j);
        }
        int i2 = this.d;
        if (i2 == 0) {
            return d(Math.min(f, this.j));
        }
        if (i2 == 1) {
            return d(Math.max(f, this.j));
        }
        return d(this.j);
    }

    private void at() {
        al();
        int i2 = this.d;
        if (i2 != 2 && i2 != -1) {
            this.aa.setGoalType(5);
            this.aa.setGoalValue(new BigDecimal(this.k).doubleValue());
            i(UnitUtil.a(this.k));
            this.cf.setSelectedPosition(d(this.k));
            return;
        }
        LogUtil.h("HealthWeight_WeightGoalActivity", "updateScrollView GoalType is illegal and not Oversea");
    }

    private void y() {
        double a2 = UnitUtil.a(Math.abs(this.x - this.j));
        int i2 = this.d;
        if (i2 == 1) {
            this.bb.setText(dBL_(getString(R$string.IDS_hwh_home_you_need_to_increase_weight, new Object[]{qsj.e(a2, 1)})));
        } else if (i2 == 2) {
            this.ay.setText(dBL_(getString(R$string.IDS_hwh_home_standard_weight_range_value, new Object[]{UnitUtil.e(this.ak, 1, 1), qsj.e(this.aj, 1)})));
        } else {
            an();
        }
        e(h());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void an() {
        String quantityString;
        double a2 = UnitUtil.a(Math.abs(this.x - this.j));
        String string = BaseApplication.getContext().getResources().getString(R$string.IDS_hwh_home_you_need_to_lose_weight_weekly);
        int e2 = qsj.e(this.au, this.j, this.aa.getGoalValue());
        LogUtil.a("HealthWeight_WeightGoalActivity", "setWeightDayText day ", Integer.valueOf(e2), " mCurrentWeight ", Double.valueOf(this.j), " mGoalWeight ", Double.valueOf(this.x));
        if (this.x != 0.0d) {
            quantityString = BaseApplication.getContext().getResources().getQuantityString(R.plurals.IDS_user_profile_achieve_num_day, e2, UnitUtil.e(e2, 1, 0));
        } else {
            quantityString = BaseApplication.getContext().getResources().getQuantityString(R.plurals.IDS_user_profile_achieve_num_day, e2, "");
        }
        this.bb.setText(dBL_(String.format(string, qsj.e(a2, 1), quantityString)));
    }

    private void g(double d2) {
        if (d2 > 0.0d) {
            this.n = 0;
            a(this.cp, this.ci, this.ch, 0);
        } else if (d2 < 0.0d) {
            this.n = 1;
            a(this.ci, this.cp, this.ch, 1);
        } else {
            this.n = 2;
            a(this.ch, this.cp, this.ci, 2);
        }
        this.d = this.n;
    }

    private void f(int i2) {
        double doubleValue;
        int i3 = this.co;
        if (i3 == 3) {
            doubleValue = new BigDecimal((i2 + HeartRateThresholdConfig.HEART_RATE_LIMIT) / 10.0d).setScale(1, 4).doubleValue();
            this.aa.setGoalType(5);
            this.aa.setGoalValue(new BigDecimal(UnitUtil.i(doubleValue)).doubleValue());
        } else if (i3 == 1) {
            doubleValue = new BigDecimal(((UnitUtil.b(10.0d) * 10.0d) + i2) / 10.0d).setScale(1, 4).doubleValue();
            this.aa.setGoalType(5);
            this.aa.setGoalValue(BigDecimal.valueOf(UnitUtil.c(doubleValue)).doubleValue());
        } else {
            doubleValue = new BigDecimal((i2 + 100) / 10.0d).setScale(1, 4).doubleValue();
            this.aa.setGoalType(5);
            this.aa.setGoalValue(doubleValue);
        }
        b(doubleValue);
        y();
    }

    private void b(double d2) {
        i(d2);
        float f = this.cd;
        if (f > 10.0f && f < 251.0f) {
            as();
        } else {
            LogUtil.a("HealthWeight_WeightGoalActivity", "setSelectScrollViewData mWeightData less more 10");
        }
    }

    private void as() {
        double goalValue = this.aa.getGoalValue();
        double d2 = this.j;
        if (goalValue > d2) {
            a(this.ci, this.cp, this.ch, 1);
        } else if (goalValue < d2) {
            a(this.cp, this.ci, this.ch, 0);
        } else {
            LogUtil.a("HealthWeight_WeightGoalActivity", "setWeightManagementState goalValue is equals mWeightData");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ag() {
        al();
        z();
        if (this.av) {
            this.cl.setVisibility(0);
        }
        if (this.cg == 0.0f) {
            this.bw.setText("--");
            this.n = -1;
            return;
        }
        ak();
        float f = this.cg;
        this.k = f;
        float f2 = this.cd;
        if (f < f2) {
            this.n = 0;
        } else if (f > f2) {
            this.n = 1;
        } else {
            this.n = 2;
        }
    }

    private void z() {
        this.d = -1;
        this.cb.setEnabled(false);
        dBI_(this.bu);
        this.bu.setVisibility(8);
        this.bt.setVisibility(8);
        this.bw.setVisibility(0);
        HealthTextView healthTextView = this.bi;
        if (healthTextView != null) {
            healthTextView.setTextAppearance(R.style.weight_management_goal_normal);
            this.bi.setBackgroundResource(R.drawable._2131432067_res_0x7f0b1283);
            this.bi = null;
        }
        a(false);
    }

    private void ak() {
        this.bw.setText(qsj.e(UnitUtil.a(this.cg, 0), 0));
    }

    private void al() {
        String string;
        if (ac()) {
            int i2 = this.co;
            if (i2 == 3) {
                string = getString(R$string.IDS_hwh_home_standard_weight_range_no_data_imp);
            } else if (i2 == 1) {
                string = getString(R$string.IDS_hwh_home_standard_weight_catty_range_no_data);
            } else {
                string = getString(R$string.IDS_hwh_home_standard_weight_range_no_data);
            }
            this.cm.setText(string);
            return;
        }
        aw();
    }

    private void i(double d2) {
        double a2;
        int i2 = 1;
        int i3 = d2 == ((double) Double.valueOf(d2).intValue()) ? 1 : 0;
        if (this.d == 2) {
            boolean z = Math.round(Math.pow(10.0d, 2.0d) * d2) % 10 == 0;
            if (i3 != 0) {
                i2 = 0;
            } else if (!z) {
                i2 = 2;
            }
            a2 = UnitUtil.a(d2, i2);
            this.bw.setText(qsj.e(a2, i2));
        } else {
            int i4 = i3 ^ 1;
            a2 = UnitUtil.a(d2, i4);
            String e2 = qsj.e(a2, i4);
            this.cr.setText(UnitUtil.bCR_(this.f10099a, "\\d+.\\d+|\\d+", e2, R.style.health_weight_goal_value_text, R.style.health_data_weight_unit));
            this.bw.setText(e2);
        }
        this.x = UnitUtil.d(a2);
    }

    private int a(double d2, double d3) {
        int i2 = this.co;
        if (i2 == 3) {
            double h = UnitUtil.h(d2);
            if (h >= 552.0d) {
                h = 551.0d;
            }
            return qsj.c((float) ((h - ((int) UnitUtil.h(d3))) * 10.0d));
        }
        if (i2 == 1) {
            double b2 = UnitUtil.b(d2);
            if (b2 >= UnitUtil.b(251.0d)) {
                b2 = UnitUtil.b(251.0d) - 1.0d;
            }
            return qsj.c((float) ((b2 - UnitUtil.b(d3)) * 10.0d));
        }
        if (d2 >= 251.0d) {
            d2 = 250.0d;
        }
        return qsj.c((float) ((d2 - d3) * 10.0d));
    }

    private int d(double d2) {
        return a(d2, 10.0d);
    }

    private void aw() {
        double[] b2 = doj.b((byte) this.u);
        double pow = Math.pow(this.ad / 100.0d, 2.0d);
        double d2 = b2[0];
        double pow2 = Math.pow(this.ad / 100.0d, 2.0d);
        double d3 = b2[1];
        this.b = UnitUtil.a(pow * d2, 1);
        double a2 = UnitUtil.a(pow2 * d3, 1);
        this.c = a2;
        this.ai = this.b;
        this.ac = a2;
        double[] e2 = doj.e(Utils.o(), (byte) this.u, this.ab.getAge());
        double pow3 = Math.pow(this.ad / 100.0d, 2.0d) * e2[0];
        this.bm = a(pow3).intValue();
        double pow4 = Math.pow(this.ad / 100.0d, 2.0d) * e2[1];
        this.bj = a(pow4).intValue();
        double a3 = UnitUtil.a(pow3);
        double a4 = UnitUtil.a(pow4);
        int i2 = this.co;
        if (i2 == 3) {
            this.cm.setText(String.format(BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903048_res_0x7f030008, 0), Float.valueOf(a(a3).floatValue()), Float.valueOf(a(a4).floatValue())));
        } else if (i2 == 1) {
            this.cm.setText(String.format(BaseApplication.getContext().getResources().getString(R$string.IDS_hwh_home_standard_weight_range_value), UnitUtil.e(a(a3).intValue(), 1, 0), qsj.e(a4, 0)));
        } else {
            this.cm.setText(String.format(BaseApplication.getContext().getString(R$string.IDS_hwh_home_standard_weight_range), Integer.valueOf(a(a3).intValue()), Integer.valueOf(a(a4).intValue())));
        }
        this.bn.setText(getString(R$string.IDS_hwh_home_standard_weight_range_value, new Object[]{UnitUtil.e(a(a3).intValue(), 1, 0), qsj.e(a(a4).intValue(), 0)}));
        b(a3, a4);
        this.ap.setData(rag.c((int) Math.floor(this.ai), ((int) Math.ceil(this.ac)) + 1), 10, 40);
    }

    private void b(double d2, double d3) {
        if (LanguageUtil.bp(this.f10099a) || LanguageUtil.ac(this.f10099a) || LanguageUtil.y(this.f10099a)) {
            this.bn.setText(getString(R$string.IDS_hwh_home_standard_weight_range_value, new Object[]{e(d3), c(d2)}));
        }
        if (LanguageUtil.ai(this.f10099a)) {
            this.bn.setText(getString(R$string.IDS_hwh_home_standard_weight_range_value, new Object[]{c(d2), e(d3)}));
        }
    }

    private String e(double d2) {
        return UnitUtil.e(a(d2).intValue(), 1, 0);
    }

    private String c(double d2) {
        return qsj.e(a(d2).intValue(), 0);
    }

    private BigDecimal a(double d2) {
        return new BigDecimal(d2).setScale(0, 4);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LogUtil.a("HealthWeight_WeightGoalActivity", "onResume mHiUserInfo ", this.ab);
        ThreadPoolManager.d().d("getHeightIsNotSet", new Runnable() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.WeightGoalActivity.8
            @Override // java.lang.Runnable
            public void run() {
                WeightGoalActivity.this.e = sdk.c().a();
                WeightGoalActivity.this.s();
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == null) {
            LogUtil.h("HealthWeight_WeightGoalActivity", "onclick failure because view is null!");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            dBF_(view);
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void dBF_(View view) {
        if (this.bg == view) {
            if (nsn.o()) {
                LogUtil.b("HealthWeight_WeightGoalActivity", "fast click save goals, return");
                return;
            }
            double goalValue = this.aa.getGoalValue();
            if (this.d != 2 && Math.abs(goalValue - this.j) <= 1.0E-6d) {
                ad();
                return;
            } else {
                ab();
                return;
            }
        }
        if (this.as == view || this.bv == view) {
            qry.a(this.f10099a, !ac());
            return;
        }
        if (this.ck == view) {
            ar();
            return;
        }
        HealthTextView healthTextView = this.cp;
        if (healthTextView == view) {
            if (this.d != 0) {
                a(healthTextView, this.ci, this.ch, 0);
                ai();
                au();
                return;
            }
            return;
        }
        HealthTextView healthTextView2 = this.ci;
        if (healthTextView2 == view) {
            if (this.d != 1) {
                a(healthTextView2, healthTextView, this.ch, 1);
                ai();
                au();
                return;
            }
            return;
        }
        HealthTextView healthTextView3 = this.ch;
        if (healthTextView3 == view) {
            if (this.d != 2) {
                a(healthTextView3, healthTextView, healthTextView2, 2);
                i();
                c(this.ak, this.aj);
                return;
            }
            return;
        }
        if (view == this.p) {
            qpz.e(this);
        } else {
            dBG_(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dBJ_(View view, int i2) {
        if (view.getVisibility() != i2) {
            view.setVisibility(i2);
        }
    }

    private void dBG_(View view) {
        if (this.cb == view) {
            dBK_(this.bw, this.bt, this.bu, this.bt.getVisibility() != 0);
            return;
        }
        if (this.w == view) {
            dBK_(this.r, this.s, this.t, this.s.getVisibility() != 0);
            return;
        }
        if (this.by == view) {
            dBK_(this.h, this.bx, this.bz, this.bx.getVisibility() != 0);
            return;
        }
        if (this.ao == view) {
            dBK_(this.aq, this.ag, this.af, this.ag.getVisibility() != 0);
            return;
        }
        if (this.ae == view) {
            aq();
        } else if (this.an == view) {
            ap();
        } else {
            LogUtil.a("HealthWeight_WeightGoalActivity", "onclick failure");
        }
    }

    private void ap() {
        this.aw = false;
        this.al.setVisibility(8);
        this.am.setVisibility(0);
        this.ae.setTextColor(getColor(R.color._2131299241_res_0x7f090ba9));
        this.an.setTextColor(getColor(R.color._2131298171_res_0x7f09077b));
        c(this.ak, this.aj);
    }

    private void aq() {
        this.aw = true;
        this.al.setVisibility(0);
        this.am.setVisibility(8);
        this.an.setTextColor(getColor(R.color._2131299241_res_0x7f090ba9));
        this.ae.setTextColor(getColor(R.color._2131298171_res_0x7f09077b));
        c(this.ak, this.aj);
    }

    private void dBK_(HealthTextView healthTextView, LinearLayout linearLayout, ImageView imageView, boolean z) {
        if (z) {
            if (healthTextView != null) {
                healthTextView.setVisibility(8);
            }
            if (linearLayout != null) {
                linearLayout.setVisibility(0);
            }
            if (imageView != null) {
                dBH_(imageView);
                return;
            }
            return;
        }
        if (healthTextView != null) {
            healthTextView.setVisibility(0);
        }
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
        if (imageView != null) {
            dBI_(imageView);
        }
    }

    private void dBI_(ImageView imageView) {
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            imageView.setImageResource(R.drawable._2131429719_res_0x7f0b0957);
        } else {
            imageView.setImageResource(R.drawable._2131429713_res_0x7f0b0951);
        }
    }

    private void dBH_(ImageView imageView) {
        if (LanguageUtil.bc(BaseApplication.getContext())) {
            imageView.setImageResource(R.drawable._2131429713_res_0x7f0b0951);
        } else {
            imageView.setImageResource(R.drawable._2131429719_res_0x7f0b0957);
        }
    }

    private void ai() {
        int i2 = 0;
        double c2 = c(this.d == 0);
        i(UnitUtil.a(c2));
        int d2 = d(c2);
        if (d2 <= 0) {
            LogUtil.a("HealthWeight_WeightGoalActivity", "setNewGoalType newGoalPosition is min");
        } else {
            LogUtil.a("HealthWeight_WeightGoalActivity", "setNewGoalType newGoalPosition is normal");
            i2 = d2;
        }
        this.cf.setSelectedPosition(i2);
    }

    private void a(HealthTextView healthTextView, HealthTextView healthTextView2, HealthTextView healthTextView3, int i2) {
        this.d = i2;
        dBJ_(this.bc, this.au == 1 ? 0 : 8);
        g();
        a(true);
        this.bi = healthTextView;
        healthTextView.setTextAppearance(R.style.weight_management_goal_select);
        this.bi.setTextColor(this.f10099a.getResources().getColor(R.color._2131299377_res_0x7f090c31));
        this.bi.setBackgroundResource(R.drawable._2131432068_res_0x7f0b1284);
        healthTextView2.setTextAppearance(R.style.weight_management_goal_normal);
        healthTextView2.setBackgroundResource(R.drawable.function_set_big_card_button_background);
        healthTextView3.setTextAppearance(R.style.weight_management_goal_normal);
        healthTextView3.setBackgroundResource(R.drawable.function_set_big_card_button_background);
        if (i2 == 2) {
            this.cb.setEnabled(false);
            this.bw.setVisibility(0);
            dBI_(this.bu);
            this.bu.setVisibility(8);
            this.q.setVisibility(8);
            this.bs.setVisibility(8);
            this.ah.setVisibility(0);
            this.ax.setVisibility(8);
            u();
            i(UnitUtil.a(this.cd));
            return;
        }
        if (i2 == 0) {
            this.cb.setEnabled(true);
            this.bu.setVisibility(0);
            this.q.setVisibility(8);
            this.bs.setVisibility(0);
            this.ah.setVisibility(8);
            this.ax.setVisibility(0);
            return;
        }
        this.cb.setEnabled(true);
        this.bu.setVisibility(0);
        this.q.setVisibility(0);
        this.bs.setVisibility(0);
        this.ah.setVisibility(8);
        this.ax.setVisibility(8);
    }

    private void u() {
        this.aa.setGoalType(5);
        this.aa.setGoalValue(new BigDecimal(this.cd).doubleValue());
    }

    private void ad() {
        qry.b(this.f10099a);
    }

    private void h(double d2) {
        j(d2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(float f) {
        this.z.b(new d(this.ce, f));
        final ckm ckmVar = new ckm();
        ckmVar.setBodyFatRat(0.0f);
        ckmVar.setWeight((float) this.j);
        ckmVar.setStartTime(this.bk);
        ckmVar.e(true);
        ckmVar.setEndTime(this.bk);
        this.z.b(MultiUsersManager.INSTANCE.getMainUser());
        final cfi mainUser = MultiUsersManager.INSTANCE.getMainUser();
        if (mainUser == null) {
            LogUtil.b("HealthWeight_WeightGoalActivity", "main user is null");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: qfp
                @Override // java.lang.Runnable
                public final void run() {
                    WeightGoalActivity.this.e(mainUser, ckmVar);
                }
            });
        }
    }

    public /* synthetic */ void e(cfi cfiVar, ckm ckmVar) {
        this.z.b(cfiVar);
        this.z.onDataChanged(rag.a(), ckmVar);
    }

    private void j(double d2) {
        if (!health.compact.a.CommonUtil.aa(BaseApplication.getContext())) {
            nrh.b(this, R$string.IDS_network_connect_error);
            LogUtil.h("HealthWeight_WeightGoalActivity", "saveWeightTargetDifferences No network");
        } else {
            e(d2, this.f, this.y);
        }
    }

    private void e(double d2, IntPlan intPlan, Boolean bool) {
        int i2;
        int i3;
        long j2;
        WeightTargetDifferences weightTargetDifferences;
        int i4 = 0;
        if (intPlan != null && intPlan.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN) {
            qsj.b(intPlan.getPlanId(), (Context) this, false);
            return;
        }
        LogUtil.a("HealthWeight_WeightGoalActivity", "saveGoals mCurrentGoalType ", Integer.valueOf(this.d));
        Bundle bundle = new Bundle();
        bundle.putInt("age", gni.e());
        bundle.putInt(CommonConstant.KEY_GENDER, this.u);
        bundle.putInt("height", this.ad);
        bundle.putDouble("weight", this.j);
        bundle.putDouble("targetWeight", d2);
        bundle.putInt("fatBurnChoice", this.au);
        if (this.av) {
            bundle.putBoolean("targetSettingChanged", (this.az == this.au && Float.compare(this.bd, (float) d2) == 0) ? false : true);
            bundle.putBoolean("isRefreshInitWeight", false);
            bundle.putDouble("startWeight", b(this.cq));
        } else {
            bundle.putBoolean("targetSettingChanged", true);
            bundle.putBoolean("isRefreshInitWeight", true);
        }
        int i5 = this.d;
        if (i5 == 0) {
            i2 = 0;
            i4 = 1;
        } else {
            if (i5 == 1) {
                j2 = h();
                i3 = 2;
                bundle.putInt("weightManagerType", i3);
                bundle.putLong("targetFinishDate", j2);
                bundle.putInt("maintainTargetWeightRange", i4);
                long currentTimeMillis = System.currentTimeMillis();
                bundle.putLong("targetBeginDate", (this.av || (weightTargetDifferences = this.cn) == null || weightTargetDifferences.e() == 0) ? currentTimeMillis : this.cn.e());
                bundle.putLong("modifiedTime", currentTimeMillis);
                bundle.putSerializable("intPlan", (Serializable) intPlan);
                bundle.putBoolean("getIntPlan", bool.booleanValue());
                bundle.putBoolean("waitSaveWeight", true);
                qzw.dJr_(bundle, new e(d2));
            }
            i2 = (int) (((this.aj * 1000.0d) - (this.ak * 1000.0d)) / 2.0d);
            ReleaseLogUtil.e("HealthWeight_WeightGoalActivity", "saveWeightGoal mCurrentGoalType ", Integer.valueOf(i5));
        }
        j2 = 0;
        int i6 = i2;
        i3 = i4;
        i4 = i6;
        bundle.putInt("weightManagerType", i3);
        bundle.putLong("targetFinishDate", j2);
        bundle.putInt("maintainTargetWeightRange", i4);
        long currentTimeMillis2 = System.currentTimeMillis();
        bundle.putLong("targetBeginDate", (this.av || (weightTargetDifferences = this.cn) == null || weightTargetDifferences.e() == 0) ? currentTimeMillis2 : this.cn.e());
        bundle.putLong("modifiedTime", currentTimeMillis2);
        bundle.putSerializable("intPlan", (Serializable) intPlan);
        bundle.putBoolean("getIntPlan", bool.booleanValue());
        bundle.putBoolean("waitSaveWeight", true);
        qzw.dJr_(bundle, new e(d2));
    }

    private double b(gsi gsiVar) {
        float f = MultiUsersManager.INSTANCE.getMainUser().f();
        if (f > 0.0f) {
            return f;
        }
        ReleaseLogUtil.d("HealthWeight_WeightGoalActivity", "getStartWeight startWeight ", Float.valueOf(f));
        if (gsiVar == null) {
            ReleaseLogUtil.d("HealthWeight_WeightGoalActivity", "getStartWeight weightManager is null");
            return 0.0d;
        }
        return gsiVar.d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:17:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0046  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void f(double r40) {
        /*
            Method dump skipped, instructions count: 307
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.health.activity.healthdata.WeightGoalActivity.f(double):void");
    }

    private void a(long j2) {
        cfi mainUser = MultiUsersManager.INSTANCE.getMainUser();
        if (mainUser == null) {
            LogUtil.h("HealthWeight_WeightGoalActivity", "createWeightDialogTargetId user is null");
            return;
        }
        qry.d(j2 + mainUser.i());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long h() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(1, this.m.getYear());
        calendar.set(2, this.m.getMonth() - 1);
        calendar.set(5, this.m.getDayOfMonth());
        Date time = calendar.getTime();
        LogUtil.a("HealthWeight_WeightGoalActivity", "finisDate:", Long.valueOf(time.getTime()));
        return time.getTime();
    }

    private long d(int i2) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(5, i2 - 1);
        Date time = calendar.getTime();
        LogUtil.a("HealthWeight_WeightGoalActivity", "loseWeight finishDate: ", Long.valueOf(time.getTime()));
        return time.getTime();
    }

    private void a(int i2, int i3, double d2, WeightTargetDifferences weightTargetDifferences) {
        HashMap hashMap = new HashMap(16);
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(i2));
        if (i2 == 0) {
            double abs = Math.abs((UnitUtil.a((weightTargetDifferences.a() / 1000.0d) * 7.0d, 1) / 7.0d) * 7700.0d);
            if (abs < 400.0d) {
                hashMap.put("difficulty", 1);
            } else if (abs <= 600.0d) {
                hashMap.put("difficulty", 2);
            } else {
                hashMap.put("difficulty", 3);
            }
        }
        hashMap.put("target", Double.valueOf(Math.abs(UnitUtil.a(d2, 1))));
        if (UnitUtil.a() == 1) {
            hashMap.put("unit", "catty");
        }
        if (UnitUtil.a() == 2) {
            hashMap.put("unit", "kilogram");
        }
        if (UnitUtil.a() == 3) {
            hashMap.put("unit", "Pounds");
        }
        hashMap.put("days", Integer.valueOf(i3));
        hashMap.put(FunctionSetBeanReader.BI_ELEMENT, Double.valueOf(Math.abs(UnitUtil.a((weightTargetDifferences.a() / 1000.0d) * 7.0d, 1))));
        b(hashMap, i2);
    }

    private void b(Map<String, Object> map, int i2) {
        if (this.av) {
            map.put("setType", 1);
            if (i2 != 2) {
                double d2 = this.x;
                double d3 = this.cg;
                if (d2 > d3) {
                    map.put("adjustType", 1);
                } else if (d2 < d3) {
                    map.put("adjustType", -1);
                } else {
                    map.put("adjustType", 0);
                }
            }
        } else {
            map.put("from", Integer.valueOf(this.v));
            map.put("setType", 0);
        }
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.WEIGHT_SAVE_GOAL_CLICKED_2040149.value(), map, 0);
    }

    private void ab() {
        double goalValue = this.aa.getGoalValue();
        if (goalValue <= 0.0d) {
            goalValue = MultiUsersManager.INSTANCE.getMainUser().k();
        }
        int i2 = this.d;
        if (i2 != 1 && i2 != 0) {
            goalValue = (this.ak + this.aj) / 2.0d;
        }
        LogUtil.a("HealthWeight_WeightGoalActivity", "saveData goalValue ", Double.valueOf(goalValue), " mCurrentGoalType ", Integer.valueOf(this.d), " mHoldWeightRangeLowValue ", Double.valueOf(this.ak), " mHoldWeightRangeHighValue ", Double.valueOf(this.aj));
        h(goalValue);
    }

    private boolean ac() {
        int i2 = this.ad;
        return (i2 == 170 && this.e) || i2 == 0;
    }

    private void a(boolean z) {
        LogUtil.a("HealthWeight_WeightGoalActivity", "setSaveClick isClick:", Boolean.valueOf(z));
        if (z) {
            this.bg.setEnabled(true);
            this.bg.setBackgroundResource(R.drawable.weight_button_background);
        } else {
            this.bg.setEnabled(false);
            this.bg.setBackgroundResource(R.drawable.weight_button_background_disable);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        HiHealthManager.d(BaseApplication.getContext()).fetchUserData(new b());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Object obj) {
        LogUtil.a("HealthWeight_WeightGoalActivity", "fetchUserData onSuccess,data = ", obj);
        OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_WEIGHT_GET_USER_INFORMATION_85070011.value(), nsn.e("0"));
        if (obj == null) {
            return;
        }
        List list = (List) obj;
        if (list.size() > 0) {
            HiUserInfo hiUserInfo = (HiUserInfo) list.get(0);
            this.ab = hiUserInfo;
            this.ad = hiUserInfo.getHeight();
            float k = MultiUsersManager.INSTANCE.getMainUser().k();
            this.cg = k;
            this.bd = k;
            float a2 = grz.a();
            this.cd = a2;
            this.j = a2;
            this.u = this.ab.getGender();
            boolean c2 = c();
            LogUtil.a("HealthWeight_WeightGoalActivity", "processUserInformation isUserInfoValid ", Boolean.valueOf(c2), " mWeightData: ", Float.valueOf(this.cd));
            if (c2) {
                e();
            } else {
                ax();
            }
        } else {
            double m = MultiUsersManager.INSTANCE.getMainUser().m();
            this.j = m;
            LogUtil.a("HealthWeight_WeightGoalActivity", "processUserInformation mCurrentWeight ", Double.valueOf(m));
        }
        HandlerExecutor.e(new Runnable() { // from class: qfr
            @Override // java.lang.Runnable
            public final void run() {
                WeightGoalActivity.this.a();
            }
        });
    }

    public /* synthetic */ void a() {
        int d2 = rag.d(this.j);
        this.bh = d2;
        this.i.setSelectedPosition(d2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ax() {
        if (!HandlerExecutor.c()) {
            HandlerExecutor.e(new Runnable() { // from class: qfq
                @Override // java.lang.Runnable
                public final void run() {
                    WeightGoalActivity.this.ax();
                }
            });
        } else {
            LogUtil.a("HealthWeight_WeightGoalActivity", "showSettingDialog  weight ", Float.valueOf(this.cd), " height ", Integer.valueOf(this.ad), " gender ", Integer.valueOf(this.u));
            nry.d(this, false, new j(this), getColor(R.color._2131298733_res_0x7f0909ad));
        }
    }

    /* loaded from: classes6.dex */
    static class j implements IBaseResponseCallback {
        private WeakReference<WeightGoalActivity> b;

        j(WeightGoalActivity weightGoalActivity) {
            this.b = new WeakReference<>(weightGoalActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            WeightGoalActivity weightGoalActivity = this.b.get();
            LogUtil.a("HealthWeight_WeightGoalActivity", "ShowUserInfoCallBack errorCode ", Integer.valueOf(i), " activity ", weightGoalActivity);
            if (weightGoalActivity == null || weightGoalActivity.isFinishing() || weightGoalActivity.isDestroyed() || i == 0) {
                return;
            }
            weightGoalActivity.finish();
        }
    }

    private boolean c() {
        return this.ab.isWeightValid() && this.ab.isHeightValid() && this.ab.isBirthdayValid() && this.ab.isGenderValid();
    }

    private void ar() {
        View inflate = View.inflate(this, R.layout.health_healthdata_edit_goal_dialog, null);
        double goalValue = this.aa.getGoalValue();
        if (goalValue <= 0.0d) {
            goalValue = MultiUsersManager.INSTANCE.getMainUser().k();
        }
        double a2 = UnitUtil.a(goalValue);
        HealthEditText healthEditText = (HealthEditText) inflate.findViewById(R.id.edit_goal);
        this.l = healthEditText;
        try {
            healthEditText.setText(String.valueOf(Integer.parseInt(String.valueOf(new BigDecimal(a2).setScale(0, 4)))));
        } catch (NumberFormatException e2) {
            LogUtil.b("HealthWeight_WeightGoalActivity", "showEditDialog NumberFormatException ", e2.getMessage());
        }
        this.l.setFocusableInTouchMode(true);
        this.l.setFocusable(true);
        this.l.requestFocus();
        final HealthErrorTipTextLayout healthErrorTipTextLayout = (HealthErrorTipTextLayout) inflate.findViewById(R.id.edit_goal_layout);
        final CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.f10099a);
        builder.d(R$string.IDS_hw_show_main_home_page_step_goal);
        builder.czg_(inflate);
        builder.b(false);
        builder.czc_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.WeightGoalActivity.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.cze_(R$string.IDS_settings_button_ok, new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.WeightGoalActivity.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                WeightGoalActivity.this.c(WeightGoalActivity.this.l.getText().toString().trim(), healthErrorTipTextLayout, builder);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomViewDialog e3 = builder.e();
        e3.setCanceledOnTouchOutside(true);
        e3.show();
        i iVar = this.ce;
        if (iVar != null) {
            iVar.postDelayed(new Runnable() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.WeightGoalActivity.4
                @Override // java.lang.Runnable
                public void run() {
                    ((InputMethodManager) WeightGoalActivity.this.f10099a.getSystemService("input_method")).toggleSoftInput(0, 2);
                }
            }, 100L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, HealthErrorTipTextLayout healthErrorTipTextLayout, CustomViewDialog.Builder builder) {
        int i2;
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (c(str)) {
            try {
                i2 = Integer.parseInt(str);
            } catch (NumberFormatException unused) {
                LogUtil.b("HealthWeight_WeightGoalActivity", "setDialogText:NumberFormatException for string ", str);
                i2 = 0;
            }
            e(healthErrorTipTextLayout, builder, i2);
            return;
        }
        healthErrorTipTextLayout.setError(this.f10099a.getResources().getString(R$string.IDS_hwh_home_weight_goal_set_int));
    }

    private void e(HealthErrorTipTextLayout healthErrorTipTextLayout, CustomViewDialog.Builder builder, int i2) {
        int i3 = this.co;
        if (i3 == 3) {
            if (i2 >= 552 || i2 < 22) {
                healthErrorTipTextLayout.setError(String.format(this.f10099a.getResources().getString(R$string.IDS_hwh_home_weight_goal_set_range), Integer.valueOf(this.be[2]), Integer.valueOf(this.be[3])));
                return;
            }
            healthErrorTipTextLayout.setError(null);
            builder.b(true);
            this.aa.setGoalValue(UnitUtil.i(i2));
            j(i2);
            this.cf.setIsShowText(true);
            this.cf.setSelectedPosition((i2 - 22) * 10);
            return;
        }
        if (i3 != 1) {
            if (i2 >= 251 || i2 < 10) {
                healthErrorTipTextLayout.setError(String.format(this.f10099a.getResources().getString(R$string.IDS_hwh_home_weight_goal_set_range), Integer.valueOf(this.be[0]), Integer.valueOf(this.be[1])));
                return;
            }
            healthErrorTipTextLayout.setError(null);
            builder.b(true);
            this.aa.setGoalValue(i2);
            j(i2);
            this.cf.setIsShowText(true);
            this.cf.setSelectedPosition((i2 - 10) * 10);
            return;
        }
        double d2 = i2;
        if (d2 >= UnitUtil.b(251.0d) || d2 < UnitUtil.b(10.0d)) {
            healthErrorTipTextLayout.setError(String.format(this.f10099a.getResources().getString(R$string.IDS_hwh_home_weight_goal_set_range), Integer.valueOf((int) UnitUtil.b(this.be[0])), Integer.valueOf((int) UnitUtil.b(this.be[1]))));
            return;
        }
        healthErrorTipTextLayout.setError(null);
        builder.b(true);
        this.aa.setGoalValue(UnitUtil.c(d2));
        j(i2);
        this.cf.setIsShowText(true);
        this.cf.setSelectedPosition((int) ((d2 - UnitUtil.b(10.0d)) * 10.0d));
    }

    private boolean c(String str) {
        return Pattern.compile("[0-9]*").matcher(str).matches();
    }

    private void j(int i2) {
        int i3 = this.co;
        boolean z = false;
        boolean z2 = i3 == 3 && i2 == 132;
        boolean z3 = i3 == 2 && i2 == 60;
        if (i3 == 1 && i2 == 120) {
            z = true;
        }
        if (z2 || z3 || z) {
            i(i2);
            a(true);
        }
    }

    /* loaded from: classes6.dex */
    public static class b implements HiCommonListener {
        private final WeakReference<WeightGoalActivity> e;

        private b(WeightGoalActivity weightGoalActivity) {
            this.e = new WeakReference<>(weightGoalActivity);
        }

        @Override // com.huawei.hihealth.data.listener.HiCommonListener
        public void onSuccess(int i, final Object obj) {
            final WeightGoalActivity weightGoalActivity = this.e.get();
            if (weightGoalActivity == null || weightGoalActivity.isFinishing()) {
                LogUtil.h("HealthWeight_WeightGoalActivity", "FetchDataListener onSuccess activity is null or finishing");
            } else {
                ThreadPoolManager.d().execute(new Runnable() { // from class: qft
                    @Override // java.lang.Runnable
                    public final void run() {
                        WeightGoalActivity.this.c(obj);
                    }
                });
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiCommonListener
        public void onFailure(int i, Object obj) {
            LogUtil.a("HealthWeight_WeightGoalActivity", "fetchUserData onFailure");
            OpAnalyticsUtil.getInstance().setEventOneErrorCode(OperationKey.HEALTH_APP_WEIGHT_GET_USER_INFORMATION_85070011.value(), i);
        }
    }

    /* loaded from: classes6.dex */
    static class e implements ResponseCallback<gsi> {
        private final double c;
        private final WeakReference<WeightGoalActivity> e;

        private e(WeightGoalActivity weightGoalActivity, double d) {
            this.e = new WeakReference<>(weightGoalActivity);
            this.c = d;
        }

        @Override // com.huawei.hwbasemgr.ResponseCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, gsi gsiVar) {
            LogUtil.a("HealthWeight_WeightGoalActivity", "SaveWeightCallback onResponse errorCode ", Integer.valueOf(i), " weightManager ", gsiVar);
            WeightGoalActivity weightGoalActivity = this.e.get();
            if (weightGoalActivity == null || weightGoalActivity.isFinishing()) {
                LogUtil.h("HealthWeight_WeightGoalActivity", "SaveWeightCallBack onSuccess activity is null or finishing");
                return;
            }
            if (i != 0) {
                return;
            }
            if (gsiVar != null) {
                weightGoalActivity.cq = gsiVar;
            }
            weightGoalActivity.f(this.c);
            if (rag.d(weightGoalActivity.j) == weightGoalActivity.bh) {
                LogUtil.a("HealthWeight_WeightGoalActivity", "currentWeight & origin weight is same, jump to weightGoalPreviewActivity");
                weightGoalActivity.e(weightGoalActivity, (float) this.c);
            } else {
                LogUtil.a("HealthWeight_WeightGoalActivity", "currentWeight & origin weight is not same, insert weight data");
                weightGoalActivity.b((float) this.c);
            }
        }
    }

    private void f() {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi != null) {
            planApi.b(new a(this));
        }
    }

    /* loaded from: classes6.dex */
    static class a extends UiCallback<IntPlan> {
        WeakReference<WeightGoalActivity> b;

        a(WeightGoalActivity weightGoalActivity) {
            this.b = new WeakReference<>(weightGoalActivity);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            WeightGoalActivity weightGoalActivity = this.b.get();
            if (weightGoalActivity == null || weightGoalActivity.isFinishing()) {
                LogUtil.h("HealthWeight_WeightGoalActivity", "GetIntPlanCallback onFailure activity is null or finishing");
                return;
            }
            LogUtil.b("HealthWeight_WeightGoalActivity", "getCurrentPlan onFailure");
            weightGoalActivity.f = null;
            weightGoalActivity.y = false;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onSuccess(IntPlan intPlan) {
            WeightGoalActivity weightGoalActivity = this.b.get();
            if (weightGoalActivity != null && !weightGoalActivity.isFinishing()) {
                weightGoalActivity.y = true;
                if (intPlan != null && intPlan.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN) {
                    weightGoalActivity.f = intPlan;
                    return;
                } else {
                    LogUtil.h("HealthWeight_WeightGoalActivity", "getCurrentIntPlan onSuccess intPlan is not exist");
                    weightGoalActivity.f = null;
                    return;
                }
            }
            LogUtil.h("HealthWeight_WeightGoalActivity", "GetIntPlanCallback onSuccess activity is null or finishing");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
