package com.huawei.ui.main.stories.fitness.views.coresleep;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Looper;
import android.os.Message;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.content.ContextCompat;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiDataFilter;
import com.huawei.hihealth.ResultUtils;
import com.huawei.hwcommonmodel.fitnessdatatype.SleepTotalData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.ConfigMapDefValues;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.commonui.chart.HealthRingChart;
import com.huawei.ui.commonui.chart.HealthRingChartAdapter;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.ratingbar.HealthStarRatingBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.coresleep.DaySleepActivity;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SleepDurationActivity;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SleepRateActivity;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SleepScoreTimesActivity;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SleepTimeActivity;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SleepViewCallback;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SleepWeekExpandListProvider;
import com.huawei.ui.main.stories.fitness.views.coresleep.CoreSleepServiceView;
import com.huawei.ui.main.stories.recommendcloud.util.RecommendControl;
import com.huawei.ui.main.stories.utils.FitnessUtils;
import defpackage.gnm;
import defpackage.mfm;
import defpackage.nkz;
import defpackage.nld;
import defpackage.ppj;
import defpackage.pqp;
import defpackage.pxd;
import defpackage.scn;
import defpackage.scx;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Locale;

/* loaded from: classes9.dex */
public class CoreSleepServiceView extends LinearLayout {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f9971a;
    private LinearLayout aa;
    private LinearLayout ab;
    private Context ac;
    private LinearLayout ad;
    private HealthTextView ae;
    private LinearLayout af;
    private HealthRingChart ag;
    private LinearLayout ah;
    private View ai;
    private HealthTextView aj;
    private ImageView ak;
    private ImageView al;
    private LinearLayout am;
    private int an;
    private HealthTextView ao;
    private HealthTextView ap;
    private LinearLayout aq;
    private int ar;
    private HealthTextView as;
    private int at;
    private LinearLayout au;
    private HealthTextView av;
    private HealthTextView aw;
    private ImageView ax;
    private LinearLayout ay;
    private HealthTextView az;
    private HealthTextView b;
    private HealthTextView ba;
    private HealthTextView bb;
    private ImageView bc;
    private int bd;
    private LinearLayout be;
    private HealthTextView bf;
    private HealthTextView bg;
    private ImageView bh;
    private int bi;
    private FitnessUtils bj;
    private boolean bk;
    private pxd bl;
    private HealthTextView bm;
    private ImageView bn;
    private boolean bo;
    private boolean bp;
    private boolean bq;
    private LinearLayout br;
    private ImageView bs;
    private HealthTextView bt;
    private int bu;
    private HealthTextView bv;
    private HealthTextView bw;
    private HealthTextView bx;
    private HealthDivider by;
    private HealthTextView bz;
    private HealthTextView c;
    private LinearLayout ca;
    private HealthTextView cb;
    private View cc;
    private e cd;
    private LinearLayout ce;
    private HealthTextView cf;
    private HealthDivider cg;
    private RecommendControl ch;
    private int ci;
    private HealthTextView cj;
    private String ck;
    private String cl;
    private View cm;
    private LinearLayout cn;
    private HealthTextView co;
    private HealthTextView cp;
    private HealthStarRatingBar cq;
    private HealthTextView cr;
    private SleepTotalData cs;
    private SleepViewCallback ct;
    private HealthRingChart cu;
    private LinearLayout cv;
    private LinearLayout cw;
    private LinearLayout cx;
    private int cy;
    private LinearLayout cz;
    private HealthTextView d;
    private HealthTextView da;
    private HealthTextView db;
    private int dc;
    private int dd;
    private LinearLayout de;
    private ImageView df;
    private HealthTextView dg;
    private ImageView dh;
    private HealthTextView di;
    private int dj;
    private HealthTextView dk;
    private HealthTextView dl;
    private HealthTextView dm;
    private LinearLayout e;
    private ImageView f;
    private HealthTextView g;
    private HealthTextView h;
    private LinearLayout i;
    private HealthTextView j;
    private int k;
    private HealthTextView l;
    private HealthTextView m;
    private LinearLayout n;
    private ImageView o;
    private HealthTextView p;
    private HealthTextView q;
    private HealthTextView r;
    private LinearLayout s;
    private HealthTextView t;
    private HealthTextView u;
    private HealthTextView v;
    private LinearLayout w;
    private HealthTextView x;
    private LinearLayout y;
    private View z;

    public CoreSleepServiceView(Context context) {
        super(context, null);
        this.an = 0;
        this.bp = true;
        this.cy = 0;
        this.at = 0;
        this.ci = 0;
        this.bu = 0;
        this.ar = 0;
        this.k = 0;
        this.dd = 0;
        this.bd = 0;
        this.bi = 0;
        this.dc = 0;
        this.dj = 0;
        this.cs = new SleepTotalData();
        this.bq = true;
        this.ck = Constants.LINK;
    }

    public CoreSleepServiceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.an = 0;
        this.bp = true;
        this.cy = 0;
        this.at = 0;
        this.ci = 0;
        this.bu = 0;
        this.ar = 0;
        this.k = 0;
        this.dd = 0;
        this.bd = 0;
        this.bi = 0;
        this.dc = 0;
        this.dj = 0;
        this.cs = new SleepTotalData();
        this.bq = true;
        this.ck = Constants.LINK;
        this.ac = context;
        if (this.ch == null) {
            this.ch = RecommendControl.newInstance(context);
        }
        this.bl = new pxd();
        this.bj = new FitnessUtils(this.ac);
        this.cd = new e(this);
        View inflate = LayoutInflater.from(this.ac).inflate(R.layout.layout_sleep_service_info_layout, (ViewGroup) this, false);
        this.ai = inflate;
        addView(inflate);
        dwJ_(this.ai);
    }

    public void setSleepData(SleepTotalData sleepTotalData, int i, boolean z, String str) {
        LogUtil.a("UIHLH_CoreSleepServiceView", "enter setSleepData() isCoreSleep:", Boolean.valueOf(z));
        if (sleepTotalData == null) {
            LogUtil.h("UIHLH_CoreSleepServiceView", "setSleepData sleepData is null");
            return;
        }
        LogUtil.a("UIHLH_CoreSleepServiceView", "sleepData:", sleepTotalData.toString());
        this.cs = sleepTotalData;
        a(i, z);
        c();
        f();
        this.cl = str;
        if (z) {
            if (i == 1) {
                int deepSleepTime = sleepTotalData.getDeepSleepTime() + sleepTotalData.getShallowSleepTime() + sleepTotalData.getSlumberSleepTime() + sleepTotalData.getNoonSleepTime();
                if (sleepTotalData.getNoonSleepTime() == deepSleepTime && deepSleepTime != 0) {
                    this.bq = false;
                } else {
                    this.bq = true;
                }
            }
            setCoreSleepData(i);
            e(sleepTotalData, i);
            d(i, z);
            return;
        }
        LogUtil.a("R_Sleep_UIHLH_CoreSleepServiceView", "isHasSleepData", Boolean.valueOf(this.bp));
        if (this.bp) {
            a(sleepTotalData, i);
            d(i, z);
        } else {
            i();
        }
    }

    public void setHasSleepData(boolean z) {
        this.bp = ((Boolean) ResultUtils.a(Boolean.valueOf(z))).booleanValue();
    }

    private void setCoreSleepData(int i) {
        int totalSleepTime = this.cs.getTotalSleepTime();
        int deepSleepPart = this.cs.getDeepSleepPart();
        int breathQualityData = this.cs.getBreathQualityData();
        int score = this.cs.getScore();
        int wakeupTimes = this.cs.getWakeupTimes();
        String string = this.ac.getString(R$string.IDS_fitness_core_sleep_night_sleep);
        String string2 = this.ac.getString(R$string.IDS_fitness_core_sleep_deep_sleep_percent);
        String string3 = this.ac.getString(R$string.IDS_fitness_core_sleep_light_sleep_percent);
        String string4 = this.ac.getString(R$string.IDS_fitness_core_sleep_rem_sleep_percent);
        String string5 = this.ac.getString(R$string.IDS_fitness_core_sleep_deep_sleep_continuity);
        String string6 = this.ac.getString(R$string.IDS_details_sleep_sleep_latency_time);
        String string7 = this.ac.getString(R$string.IDS_fitness_core_sleep_rdi_score);
        if (i != 1) {
            totalSleepTime = this.cs.getDailyTotalSleepTime();
            deepSleepPart = this.cs.getDailySleepPart();
            breathQualityData = this.cs.getDailyBreathQuality();
            score = this.cs.getDailyScore();
            wakeupTimes = this.cs.getDailyWakeupTimes();
            string = this.ac.getString(R$string.IDS_fitness_core_sleep_avg_night_sleep);
            string2 = this.ac.getString(R$string.IDS_fitness_core_sleep_avg_deep_sleep_percent);
            string3 = this.ac.getString(R$string.IDS_fitness_core_sleep_avg_light_sleep_percent);
            string4 = this.ac.getString(R$string.IDS_fitness_core_sleep_avg_rem_sleep_percent);
            string5 = this.ac.getString(R$string.IDS_fitness_core_sleep_avg_deep_sleep_continuity);
            string6 = this.ac.getString(R$string.IDS_fitness_core_sleep_sleep_avg_latency_time);
            string7 = this.ac.getString(R$string.IDS_fitness_core_sleep_avg_rdi_score);
        }
        String str = string7;
        String str2 = string6;
        String str3 = string5;
        int i2 = wakeupTimes;
        String str4 = string2;
        int i3 = deepSleepPart;
        String str5 = string3;
        int i4 = breathQualityData;
        if (score == 0) {
            g();
        } else {
            setSleepScoreData(score);
        }
        this.da.setText(this.cs.getSuggestTitle());
        this.cr.setText(this.cs.getSuggestContent());
        this.ck = scn.a(this.ac);
        String string8 = this.ac.getString(R$string.IDS_sleep_referece_title_string);
        a(totalSleepTime, string, string8);
        a(str4, str5, string4, string8, i);
        String d = d(string8);
        d(i3, str3, d);
        c(i4, str, d, i);
        b(i2, str2, string8);
        b(string8, d);
        e(string8, d);
    }

    private void a(int i, boolean z) {
        if (i == 4) {
            this.bk = false;
        } else {
            this.bk = z;
        }
    }

    private void c() {
        if (Utils.o()) {
            return;
        }
        this.ay.setVisibility(0);
        if (this.bo) {
            LogUtil.h("UIHLH_CoreSleepServiceView", "setDefaultFold expanded");
            setExpand(0);
        } else {
            e();
            setExpand(8);
            h();
        }
    }

    private void d() {
        this.ab.setVisibility(0);
        this.ay.setVisibility(8);
    }

    private void e() {
        this.bo = false;
    }

    private void h() {
        if (this.bc.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.bc.getLayoutParams();
            layoutParams.topMargin = (int) getResources().getDimension(R.dimen._2131362566_res_0x7f0a0306);
            this.bc.setLayoutParams(layoutParams);
        }
    }

    private void b() {
        if (this.bc.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.bc.getLayoutParams();
            layoutParams.topMargin = 0;
            this.bc.setLayoutParams(layoutParams);
        }
    }

    public void setSleepViewCallback(SleepViewCallback sleepViewCallback) {
        this.ct = sleepViewCallback;
    }

    private void f() {
        this.ay.setOnClickListener(new View.OnClickListener() { // from class: qae
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CoreSleepServiceView.this.dwK_(view);
            }
        });
    }

    public /* synthetic */ void dwK_(View view) {
        if (this.bo) {
            this.bc.setImageDrawable(ContextCompat.getDrawable(this.ac, R.drawable._2131428279_res_0x7f0b03b7));
            setExpand(8);
            h();
            SleepViewCallback sleepViewCallback = this.ct;
            if (sleepViewCallback != null) {
                sleepViewCallback.sleepToTop();
            }
        } else {
            setExpand(0);
            this.bc.setImageDrawable(ContextCompat.getDrawable(this.ac, R.drawable._2131428280_res_0x7f0b03b8));
            b();
        }
        this.bo = !this.bo;
        ViewClickInstrumentation.clickOnView(view);
    }

    private void setExpand(int i) {
        if (this.bk) {
            this.ab.setVisibility(i);
        } else {
            this.s.setVisibility(i);
        }
    }

    private void g() {
        LogUtil.h("UIHLH_CoreSleepServiceView", "setNoSleepScore sleepScore == 0");
        this.cj.setText("--");
        this.cp.setVisibility(8);
        this.cq.setVisibility(0);
        this.cq.setStar(0);
        this.co.setVisibility(8);
    }

    private void setSleepScoreData(int i) {
        this.cj.setText(UnitUtil.e(i, 1, 0));
        this.cp.setText(this.ac.getResources().getQuantityString(R.plurals._2130903042_res_0x7f030002, i));
        this.cp.setVisibility(0);
        setSleepScoreRating(i);
        e(i);
    }

    private void setSleepScoreRating(int i) {
        this.cq.setVisibility(0);
        this.cq.setStar((i + 9) / 10);
    }

    private void e(final int i) {
        if (Utils.l()) {
            LogUtil.h("UIHLH_CoreSleepServiceView", "fetch HEALTH_CLOUD from no_cloud user login");
            this.co.setVisibility(8);
        } else {
            pqp.b(new CommonUiBaseResponse() { // from class: qaf
                @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
                public final void onResponse(int i2, Object obj) {
                    CoreSleepServiceView.this.b(i, i2, obj);
                }
            });
        }
    }

    public /* synthetic */ void b(int i, int i2, Object obj) {
        if (this.cd != null) {
            Message obtain = Message.obtain();
            obtain.what = 1001;
            obtain.arg1 = i2;
            obtain.arg2 = i;
            obtain.obj = obj;
            this.cd.sendMessage(obtain);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, int i2, ppj ppjVar) {
        if (i == -1) {
            this.co.setVisibility(8);
            LogUtil.h("UIHLH_CoreSleepServiceView", "refreshUserRate failure");
            return;
        }
        if (ppjVar.c() == null) {
            this.co.setVisibility(8);
            return;
        }
        this.co.setVisibility(0);
        this.co.setText(this.ac.getResources().getString(R$string.IDS_core_sleep_user_rate, UnitUtil.e(r5.get(String.valueOf(i2)).intValue(), 2, 0)));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        e eVar = this.cd;
        if (eVar != null) {
            eVar.removeCallbacksAndMessages(null);
        }
    }

    static class e extends BaseHandler<CoreSleepServiceView> {
        e(CoreSleepServiceView coreSleepServiceView) {
            super(Looper.getMainLooper(), coreSleepServiceView);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dwL_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(CoreSleepServiceView coreSleepServiceView, Message message) {
            if (message == null) {
                LogUtil.b("UIHLH_CoreSleepServiceView", "msg is null");
                return;
            }
            if (message.what == 1001) {
                Object obj = message.obj;
                if (obj instanceof ppj) {
                    coreSleepServiceView.b(message.arg1, message.arg2, (ppj) obj);
                    return;
                }
                return;
            }
            LogUtil.h("UIHLH_CoreSleepServiceView", "handler message default");
        }
    }

    private String d(String str) {
        return String.format(str, UnitUtil.e(70.0d, 1, 0) + this.ck + UnitUtil.e(100.0d, 1, 0) + " ") + this.ac.getResources().getQuantityString(R.plurals._2130903042_res_0x7f030002, 100);
    }

    private void c(final int i, String str, String str2, int i2) {
        if (i2 == 1 && i < 70 && this.bq) {
            scx.e(this.ac, this.cl, "EXCE_BREATH__SCORE_ERROR");
        }
        this.m.setText(str2);
        String e2 = UnitUtil.e(i, 1, 0);
        String str3 = str + "  " + e2 + " " + this.ac.getResources().getQuantityString(R.plurals._2130903042_res_0x7f030002, CommonUtil.m(this.ac, e2));
        int b = scn.b(i);
        this.k = b;
        scn.d(this.l, b);
        this.p.setText(str3);
        this.n.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.views.coresleep.CoreSleepServiceView.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SleepScoreTimesActivity.b(CoreSleepServiceView.this.ac, "TYPE_BREATH_QUALITY", i, CoreSleepServiceView.this.k);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void d(final int i, String str, String str2) {
        this.ao.setText(str2);
        String quantityString = this.ac.getResources().getQuantityString(R.plurals._2130903042_res_0x7f030002, i);
        if (i < 20) {
            this.as.setText(str);
        } else {
            this.as.setText(str + "  " + UnitUtil.e(i, 1, 0) + " " + quantityString);
        }
        int h = scn.h(i);
        this.ar = h;
        scn.d(this.aj, h);
        this.cn.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.views.coresleep.CoreSleepServiceView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("UIHLH_CoreSleepServiceView", "sleepPartActivity");
                SleepScoreTimesActivity.b(CoreSleepServiceView.this.ac, "TYPE_DEEP_SLEEP_CONTINUITY", i, CoreSleepServiceView.this.ar);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void a(final int i, String str, String str2) {
        String str3;
        int f = scn.f(i);
        this.cy = f;
        scn.d(this.cb, f);
        String str4 = UnitUtil.e(6.0d, 1, 0) + this.ck + UnitUtil.e(10.0d, 1, 0) + " ";
        String str5 = String.format(str2, str4) + this.ac.getString(R$string.IDS_messagecenter_time_hour_value);
        if (LanguageUtil.q(this.ac)) {
            StringBuilder sb = new StringBuilder();
            sb.append(String.format(str2 + " ", str4));
            sb.append(this.ac.getString(R$string.IDS_messagecenter_time_hour_value));
            str5 = sb.toString();
        }
        this.bx.setText(str5);
        LogUtil.a("UIHLH_CoreSleepServiceView", " totalTime:", Integer.valueOf(i));
        int i2 = i / 60;
        int i3 = i % 60;
        if (i2 == 0) {
            str3 = str + "  " + this.ac.getResources().getString(R$string.IDS_hw_show_set_target_sport_time_unit, UnitUtil.e(i3, 1, 0));
        } else {
            str3 = str + "  " + this.ac.getResources().getString(R$string.IDS_h_min_unit, UnitUtil.e(i2, 1, 0), UnitUtil.e(i3, 1, 0));
        }
        this.bz.setText(str3);
        this.ca.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.views.coresleep.CoreSleepServiceView.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("UIHLH_CoreSleepServiceView", "TotalDataActivity");
                int i4 = i;
                SleepDurationActivity.b(CoreSleepServiceView.this.ac, "TYPE_NIGHT_SLEEP_TIME", i4 / 60, i4 % 60, CoreSleepServiceView.this.cy);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void a(String str, String str2, String str3, String str4, int i) {
        final int deepSleepRateNum = this.cs.getDeepSleepRateNum();
        if (i == 1) {
            if (deepSleepRateNum > 90 && this.bq) {
                scx.e(this.ac, this.cl, "EXCE_DEEPSLEEP__MIX _ERROR");
            } else if (deepSleepRateNum < 5 && this.bq) {
                scx.e(this.ac, this.cl, "EXCE_DEEPSLEEP__MIN_ERROR");
            } else {
                LogUtil.a("UIHLH_CoreSleepServiceView", "deepSleepRateNum is ok");
            }
        }
        int i2 = scn.i(deepSleepRateNum);
        this.at = i2;
        scn.d(this.ap, i2);
        this.aw.setText(String.format(str4, UnitUtil.e(20.0d, 1, 0) + this.ck + UnitUtil.e(60.0d, 2, 0)));
        this.av.setText(str + "  " + UnitUtil.e(deepSleepRateNum, 2, 0));
        this.aq.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.views.coresleep.CoreSleepServiceView.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("UIHLH_CoreSleepServiceView", "DeepSleepRateActivity");
                SleepRateActivity.d(CoreSleepServiceView.this.ac, "TYPE_DEEP_SLEEP", deepSleepRateNum, CoreSleepServiceView.this.at);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        int lightSleepRateNum = this.cs.getLightSleepRateNum();
        if (i == 1) {
            if (lightSleepRateNum > 90 && this.bq) {
                scx.e(this.ac, this.cl, "EXCE_LIGHTSLEEP__MIX _ERROR");
            } else if (lightSleepRateNum < 5 && this.bq) {
                scx.e(this.ac, this.cl, "EXCE_LIGHTSLEEP__MIN_ERROR");
            } else {
                LogUtil.a("UIHLH_CoreSleepServiceView", "lightSleepRateNum is ok");
            }
        }
        int g = scn.g(lightSleepRateNum);
        this.bu = g;
        scn.d(this.bt, g);
        this.bw.setText(String.format(str4, "< " + UnitUtil.e(55.0d, 2, 0)));
        this.bv.setText(str2 + "  " + UnitUtil.e(lightSleepRateNum, 2, 0));
        a(str3, str4, i, lightSleepRateNum);
    }

    private void a(String str, String str2, int i, final int i2) {
        this.br.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.views.coresleep.CoreSleepServiceView.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("UIHLH_CoreSleepServiceView", "LightSleepRateActivity");
                SleepRateActivity.d(CoreSleepServiceView.this.ac, "TYPE_LIGHT_SLEEP", i2, CoreSleepServiceView.this.bu);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        final int slumSleepRateNum = this.cs.getSlumSleepRateNum();
        if (i == 1) {
            if (slumSleepRateNum > 90 && this.bq) {
                scx.e(this.ac, this.cl, "EXCE_REMSLEEP__MIX _ERROR");
            } else if (slumSleepRateNum < 5 && this.bq) {
                scx.e(this.ac, this.cl, "EXCE_REMLEEP__MIN_ERROR");
            } else {
                LogUtil.a("UIHLH_CoreSleepServiceView", "dreamSleepRateNum is ok");
            }
        }
        int j = scn.j(slumSleepRateNum);
        this.ci = j;
        scn.d(this.ba, j);
        this.bb.setText(String.format(str2, UnitUtil.e(10.0d, 1, 0) + this.ck + UnitUtil.e(30.0d, 2, 0)));
        this.az.setText(str + "  " + UnitUtil.e(slumSleepRateNum, 2, 0));
        this.au.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.views.coresleep.CoreSleepServiceView.10
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("UIHLH_CoreSleepServiceView", "SlumSleepRateActivity");
                SleepRateActivity.d(CoreSleepServiceView.this.ac, "TYPE_SLUM_SLEEP_RATE", slumSleepRateNum, CoreSleepServiceView.this.ci);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void b(final int i, String str, String str2) {
        int s = scn.s(i);
        this.dd = s;
        scn.d(this.dg, s);
        String quantityString = this.ac.getResources().getQuantityString(R.plurals._2130903213_res_0x7f0300ad, 1, UnitUtil.e(i, 1, 0));
        this.dl.setText(String.format(str2, UnitUtil.e(0.0d, 1, 0) + this.ck + this.ac.getResources().getQuantityString(R.plurals._2130903213_res_0x7f0300ad, 1, UnitUtil.e(1.0d, 1, 0))));
        LogUtil.a("UIHLH_CoreSleepServiceView", " wakeTimes:", Integer.valueOf(i));
        this.dm.setText(str + "  " + quantityString);
        this.cx.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.views.coresleep.CoreSleepServiceView.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SleepScoreTimesActivity.b(CoreSleepServiceView.this.ac, "TYPE_WAKE_UP_TIMES", i, CoreSleepServiceView.this.dd);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void b(String str, String str2) {
        String str3;
        final int a2 = FitnessUtils.a((int) Math.sqrt(this.cs.getDailyFallScore()));
        this.bd = scn.k(a2);
        String quantityString = this.ac.getResources().getQuantityString(R.plurals._2130903042_res_0x7f030002, a2);
        String e2 = UnitUtil.e(a2, 1, 0);
        String str4 = this.ac.getString(R$string.IDS_fitness_core_sleep_start_sleep_regularity) + "  " + e2 + " " + quantityString;
        scn.d(this.bf, this.bd);
        this.bm.setText(str4);
        this.bg.setText(str2);
        this.be.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.views.coresleep.CoreSleepServiceView.11
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SleepScoreTimesActivity.b(CoreSleepServiceView.this.ac, "TYPE_FALL_SLEEPING_REGULAR", a2, CoreSleepServiceView.this.bd);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        int dailyFallTime = this.cs.getDailyFallTime() % ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL;
        int d = scn.d(dailyFallTime);
        this.bi = d;
        scn.e(this.d, d, true);
        final String str5 = String.format(Locale.ROOT, "%02d", Integer.valueOf(dailyFallTime / 60)) + ":" + String.format(Locale.ROOT, "%02d", Integer.valueOf(dailyFallTime % 60));
        if (LanguageUtil.b(this.ac)) {
            str3 = String.format(Locale.getDefault(), "%1$s", HiDataFilter.DataFilterExpression.LESS_THAN) + UnitUtil.e(0);
        } else {
            str3 = "< 00:00";
        }
        this.c.setText(String.format(str, str3));
        this.b.setText(this.ac.getString(R$string.IDS_fitness_core_sleep_avg_start_sleep) + "  " + UnitUtil.e(c(str5)));
        this.e.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.views.coresleep.CoreSleepServiceView.14
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent(CoreSleepServiceView.this.ac, (Class<?>) SleepTimeActivity.class);
                intent.setFlags(536870912);
                intent.putExtra("type", 2);
                intent.putExtra("sleepTimeScore", str5);
                intent.putExtra("sleepTimeStatus", CoreSleepServiceView.this.bi);
                gnm.aPB_(CoreSleepServiceView.this.ac, intent);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void e(String str, String str2) {
        final int a2 = FitnessUtils.a((int) Math.sqrt(this.cs.getDailyWakeUpScore()));
        int q = scn.q(a2);
        this.dc = q;
        scn.d(this.db, q);
        int c = scn.c(this.cs.getDailyWakeUpTime() % ConfigMapDefValues.DEFAULT_APPLIST_INTERVAL);
        this.dj = c;
        scn.e(this.g, c, true);
        String quantityString = this.ac.getResources().getQuantityString(R.plurals._2130903042_res_0x7f030002, a2);
        this.dk.setText(this.ac.getString(R$string.IDS_fitness_core_sleep_end_sleep_regularity) + "  " + UnitUtil.e(a2, 1, 0) + " " + quantityString);
        this.di.setText(str2);
        this.de.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.views.coresleep.CoreSleepServiceView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SleepScoreTimesActivity.b(CoreSleepServiceView.this.ac, "TYPE_WAKE_UP_REGULAR", a2, CoreSleepServiceView.this.dc);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.h.setText(String.format(str, String.format(Locale.getDefault(), "%1$s", HiDataFilter.DataFilterExpression.BIGGER_THAN) + " " + UnitUtil.e(c("6:0"))));
        int dailyWakeUpTime = this.cs.getDailyWakeUpTime();
        final String str3 = String.format(Locale.ROOT, "%02d", Integer.valueOf(dailyWakeUpTime / 60)) + ":" + String.format(Locale.ROOT, "%02d", Integer.valueOf(dailyWakeUpTime % 60));
        this.j.setText(this.ac.getString(R$string.IDS_fitness_core_sleep_avg_end_sleep) + "  " + UnitUtil.e(c(str3)));
        this.i.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.views.coresleep.CoreSleepServiceView.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent(CoreSleepServiceView.this.ac, (Class<?>) SleepTimeActivity.class);
                intent.setFlags(536870912);
                intent.putExtra("type", 4);
                intent.putExtra("sleepTimeScore", str3);
                intent.putExtra("sleepTimeStatus", CoreSleepServiceView.this.dj);
                gnm.aPB_(CoreSleepServiceView.this.ac, intent);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void e(SleepTotalData sleepTotalData, int i) {
        int dailyDeepSleepTime;
        int dailyNoonSleepTime;
        this.y.setVisibility(8);
        this.aa.setVisibility(0);
        this.by.setVisibility(0);
        this.w.setVisibility(8);
        if (i == 1) {
            dailyDeepSleepTime = sleepTotalData.getDeepSleepTime() + sleepTotalData.getShallowSleepTime() + sleepTotalData.getSlumberSleepTime() + sleepTotalData.getNoonSleepTime();
            if (dailyDeepSleepTime / 60 >= 15) {
                scx.e(this.ac, this.cl, "EXCE_TRUSLEEP__TIME_ERROR");
            }
            dailyNoonSleepTime = sleepTotalData.getNoonSleepTime();
        } else {
            dailyDeepSleepTime = sleepTotalData.getDailyDeepSleepTime() + sleepTotalData.getDailyShallowSleepTime() + sleepTotalData.getDailySlumberTime() + sleepTotalData.getDailyNoonSleepTime();
            dailyNoonSleepTime = sleepTotalData.getDailyNoonSleepTime();
        }
        if (i == 1) {
            a(sleepTotalData, dailyDeepSleepTime, dailyNoonSleepTime);
            return;
        }
        if (i == 3 || i == 2) {
            b(dailyDeepSleepTime, dailyNoonSleepTime);
            return;
        }
        if (dailyDeepSleepTime == dailyNoonSleepTime && dailyNoonSleepTime != 0) {
            this.by.setVisibility(8);
            this.ay.setVisibility(8);
            this.y.setVisibility(8);
        } else {
            this.y.setVisibility(0);
        }
        this.ce.setVisibility(8);
        this.aa.setVisibility(8);
        this.u.setText(R$string.IDS_core_sleep_average_nightly_sleep);
        a(sleepTotalData.getDailyDeepSleepTime() + sleepTotalData.getDailyShallowSleepTime() + sleepTotalData.getDailySlumberTime(), i);
    }

    private void b(int i, final int i2) {
        if (i2 > 45) {
            this.an = 71;
        } else {
            this.an = 73;
        }
        if (i2 != 0) {
            LinearLayout linearLayout = (LinearLayout) this.ce.getParent();
            linearLayout.removeView(this.ce);
            this.am.removeAllViews();
            View drD_ = SleepWeekExpandListProvider.drD_(this.ac);
            this.am.addView(drD_);
            HealthTextView healthTextView = (HealthTextView) drD_.findViewById(R.id.sleep_item_left_text);
            ImageView imageView = (ImageView) drD_.findViewById(R.id.sleep_item_right_image);
            View findViewById = drD_.findViewById(R.id.list_day_sleep_item_divider);
            healthTextView.setText(this.ac.getString(R$string.IDS_fitness_core_sleep_avg_noontime_sleep));
            findViewById.setVisibility(8);
            if (LanguageUtil.bc(this.ac)) {
                imageView.setBackgroundResource(R.drawable._2131427841_res_0x7f0b0201);
            }
            drD_.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.fitness.views.coresleep.CoreSleepServiceView.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    CoreSleepServiceView.this.d(i2);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            HealthTextView healthTextView2 = (HealthTextView) drD_.findViewById(R.id.sleep_item_right_text);
            c(healthTextView2, i2, 13, 13);
            healthTextView2.setAutoTextInfo(10, 1, 1);
            this.ce.setVisibility(0);
            this.cm.setVisibility(8);
            this.cz.setVisibility(8);
            e(i, i2);
            linearLayout.addView(this.ce);
            return;
        }
        a();
    }

    private void a() {
        this.ce.setVisibility(8);
        this.cm.setVisibility(0);
        this.cz.setVisibility(0);
        this.cf.setVisibility(8);
        this.cg.setVisibility(8);
        this.af.setVisibility(0);
        this.cv.setVisibility(0);
        this.ae.setText(R$string.IDS_fitness_core_sleep_avg_sleep_score);
        this.z.setVisibility(0);
        this.z.setBackgroundColor(this.ac.getResources().getColor(R.color._2131296657_res_0x7f090191));
        this.ad.setBackgroundColor(this.ac.getResources().getColor(R.color._2131296657_res_0x7f090191));
        this.ad.setVisibility(0);
    }

    private void e(int i, int i2) {
        if (i2 == i) {
            d();
            this.cf.setVisibility(0);
            if (i2 > 180) {
                this.cf.setText(this.ac.getString(R$string.IDS_core_sleep_suggesttion_nullstatus_content3));
            } else {
                this.cf.setText(this.ac.getString(R$string.IDS_core_sleep_suggesttion_nullstatus_content, this.ac.getResources().getQuantityString(R.plurals._2130903265_res_0x7f0300e1, 3, 3)));
            }
            this.cg.setVisibility(8);
            this.af.setVisibility(8);
            this.cv.setVisibility(8);
            this.z.setVisibility(8);
            this.ad.setVisibility(8);
            this.ce.setBackgroundDrawable(this.ac.getResources().getDrawable(R.drawable._2131427904_res_0x7f0b0240));
            return;
        }
        this.cf.setVisibility(8);
        this.cg.setVisibility(0);
        this.af.setVisibility(0);
        this.cv.setVisibility(0);
        this.ae.setText(R$string.IDS_fitness_core_sleep_avg_sleep_score);
        this.z.setVisibility(0);
        this.z.setBackgroundColor(this.ac.getResources().getColor(R.color._2131296657_res_0x7f090191));
        this.ad.setBackgroundColor(this.ac.getResources().getColor(R.color._2131296657_res_0x7f090191));
        this.ad.setVisibility(0);
        this.ce.setBackgroundColor(this.ac.getResources().getColor(R.color._2131296657_res_0x7f090191));
    }

    private void a(SleepTotalData sleepTotalData, int i, int i2) {
        this.cm.setVisibility(8);
        this.ah.setVisibility(8);
        if (i2 == i && i != 0) {
            d();
            this.cj.setText("--");
            this.cp.setVisibility(8);
            this.cg.setVisibility(8);
            this.af.setVisibility(8);
            this.cv.setVisibility(8);
            this.cf.setVisibility(0);
            this.z.setVisibility(8);
            this.ad.setVisibility(8);
            if (i2 > 180) {
                this.cf.setText(this.ac.getResources().getString(R$string.IDS_core_sleep_suggesttion_nullstatus_content3));
            } else {
                this.cf.setText(this.ac.getResources().getString(R$string.IDS_core_sleep_suggesttion_nullstatus_content, this.ac.getResources().getQuantityString(R.plurals._2130903265_res_0x7f0300e1, 3, 3)));
            }
            this.ce.setBackgroundDrawable(this.ac.getResources().getDrawable(R.drawable._2131427904_res_0x7f0b0240));
        } else {
            this.cf.setVisibility(8);
            if (i2 == 0) {
                this.cg.setVisibility(8);
            } else {
                this.cg.setVisibility(0);
            }
            this.af.setVisibility(0);
            this.cv.setVisibility(0);
            this.ae.setText(R$string.IDS_hw_show_main_home_page_sleep_score);
            this.z.setVisibility(0);
            this.z.setBackgroundColor(this.ac.getResources().getColor(R.color._2131296657_res_0x7f090191));
            this.ad.setBackgroundColor(this.ac.getResources().getColor(R.color._2131296657_res_0x7f090191));
            this.ce.setBackgroundColor(this.ac.getResources().getColor(R.color._2131296657_res_0x7f090191));
            this.ad.setVisibility(0);
        }
        if (sleepTotalData.getShallowSleepTime() > 0 && sleepTotalData.getDeepSleepTime() == 0 && sleepTotalData.getSlumberSleepTime() == 0) {
            this.da.setText(this.ac.getString(R$string.IDS_core_sleep_suggesttion_novalidData_tital));
            this.cr.setText(this.ac.getString(R$string.IDS_core_sleep_suggesttion_novalidData_content));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        DaySleepActivity.a(this.ac, UnitUtil.e((i - r0) / 60.0d, 1, 0), UnitUtil.e(i % 60, 1, 0), this.an, (ArrayList) this.bl.h());
    }

    private int c(String str) {
        String[] split = str.split(":");
        try {
            return (Integer.parseInt(split[0]) * 3600) + (Integer.parseInt(split[1]) * 60);
        } catch (IndexOutOfBoundsException | NumberFormatException e2) {
            LogUtil.b("R_Sleep_UIHLH_CoreSleepServiceView", "getSeconds exception = ", e2.getMessage());
            return 0;
        }
    }

    private void i() {
        LogUtil.a("UIHLH_CoreSleepServiceView", "enter updateNoDataTotalDatasUi():");
        this.y.setVisibility(8);
        this.aa.setVisibility(8);
        this.by.setVisibility(8);
        this.ay.setVisibility(8);
    }

    private void a(SleepTotalData sleepTotalData, int i) {
        LogUtil.a("UIHLH_CoreSleepServiceView", "enter updateCommonTotalDatasUi():");
        this.y.setVisibility(0);
        this.cp.setVisibility(8);
        this.cw.setVisibility(8);
        this.aa.setVisibility(8);
        this.by.setVisibility(0);
        this.ce.setVisibility(8);
        String string = this.ac.getResources().getString(R$string.IDS_awake_times);
        if (i == 3 || i == 2) {
            this.w.setVisibility(0);
            this.cf.setVisibility(8);
            this.cc.setVisibility(0);
            this.u.setText(R$string.IDS_fitness_average_sleep_data_title);
            this.x.setText(R$string.IDS_fitness_core_sleep_sleep_avg_latency_time);
            this.v.setText(UnitUtil.e(sleepTotalData.getDailyWakeupTimes(), 1, 0) + string);
            a(sleepTotalData.getDailyDeepSleepTime() + sleepTotalData.getDailyShallowSleepTime(), i);
            return;
        }
        if (i == 4) {
            this.w.setVisibility(8);
            this.cc.setVisibility(0);
            this.u.setText(R$string.IDS_fitness_daily_average_sleep_data_title);
            a(sleepTotalData.getDailyDeepSleepTime() + sleepTotalData.getDailyShallowSleepTime(), i);
            return;
        }
        this.w.setVisibility(0);
        this.cf.setVisibility(8);
        this.cc.setVisibility(0);
        this.x.setText(R$string.IDS_details_sleep_sleep_latency_time);
        this.v.setText(UnitUtil.e(sleepTotalData.getWakeupTimes(), 1, 0) + string);
        a(sleepTotalData.getDeepSleepTime() + sleepTotalData.getShallowSleepTime(), i);
    }

    private void dwJ_(View view) {
        this.cw = (LinearLayout) view.findViewById(R.id.fitness_detail_suggest_text);
        this.aa = (LinearLayout) view.findViewById(R.id.sleep_core_sleep_data_layout);
        this.y = (LinearLayout) view.findViewById(R.id.sleep_common_sleep_total_layout);
        this.ah = (LinearLayout) view.findViewById(R.id.core_sleep_list_month_items);
        this.af = (LinearLayout) view.findViewById(R.id.sleep_core_sleep_suggest_title);
        this.cv = (LinearLayout) view.findViewById(R.id.fitness_detail_suggest_content_layout);
        this.cj = (HealthTextView) view.findViewById(R.id.sleep_scoring);
        this.q = (HealthTextView) view.findViewById(R.id.common_sleep_sleep_hour_time);
        this.r = (HealthTextView) view.findViewById(R.id.common_sleep_sleep_hour_unit);
        this.t = (HealthTextView) view.findViewById(R.id.common_sleep_sleep_minute_time);
        this.u = (HealthTextView) view.findViewById(R.id.common_sleep_total_sleep_title);
        this.da = (HealthTextView) view.findViewById(R.id.suggest_title_tv);
        this.cr = (HealthTextView) view.findViewById(R.id.suggest_content_tv);
        this.cf = (HealthTextView) view.findViewById(R.id.noon_sleep_suggest_title);
        this.cg = (HealthDivider) view.findViewById(R.id.core_sleep_noon_layout_list_divider);
        this.by = (HealthDivider) view.findViewById(R.id.list_sleep_item_listdivider_image_up);
        this.ay = (LinearLayout) view.findViewById(R.id.sleep_expand_btn_layout);
        this.ad = (LinearLayout) view.findViewById(R.id.core_sleep_list_items);
        dwH_(view);
        dwG_(view);
        j();
        this.bc = (ImageView) view.findViewById(R.id.sleep_service_expand);
        this.ab = (LinearLayout) view.findViewById(R.id.core_sleep_expand);
        this.s = (LinearLayout) view.findViewById(R.id.common_sleep_expand);
        this.ce = (LinearLayout) view.findViewById(R.id.core_sleep_noon_layout);
        this.am = (LinearLayout) view.findViewById(R.id.list_day_sleep_item);
        this.cm = view.findViewById(R.id.core_sleep_noon_v);
        this.cz = (LinearLayout) view.findViewById(R.id.list_total_sleep_item);
        this.ae = (HealthTextView) view.findViewById(R.id.sleep_core_sleep_data_title);
        this.cp = (HealthTextView) view.findViewById(R.id.sleep_scoring_unit);
        this.cq = (HealthStarRatingBar) view.findViewById(R.id.sleep_scoring_rating);
        this.co = (HealthTextView) view.findViewById(R.id.sleep_scoring_user_rate);
    }

    private void dwF_(View view) {
        this.cc = mfm.cgM_(view, R.id.core_sleep_pie_chart);
        this.z = mfm.cgM_(view, R.id.core_sleep_core_pie_chart);
        this.cu = (HealthRingChart) this.cc.findViewById(R.id.day_detail_sleep_rate_recycle);
        this.ag = (HealthRingChart) this.z.findViewById(R.id.core_day_detail_sleep_rate_recycle);
    }

    private void dwH_(View view) {
        dwF_(view);
        this.cc.setVisibility(0);
        this.z.setVisibility(0);
        this.ag = (HealthRingChart) this.z.findViewById(R.id.core_day_detail_sleep_rate_recycle);
    }

    private void dwG_(View view) {
        dwI_(view);
        this.bs = (ImageView) view.findViewById(R.id.core_sleep_total_sleep_level_arrow);
        this.ak = (ImageView) view.findViewById(R.id.core_sleep_deep_sleep_level_arrow);
        this.bn = (ImageView) view.findViewById(R.id.core_sleep_light_sleep_level_arrow);
        this.ax = (ImageView) view.findViewById(R.id.core_sleep_dream_sleep_level_arrow);
        this.al = (ImageView) view.findViewById(R.id.core_sleep_deep_sleep_continuity_level_arrow);
        this.dh = (ImageView) view.findViewById(R.id.core_sleep_wake_up_time_arrow);
        this.o = (ImageView) view.findViewById(R.id.core_sleep_breath_arrow);
        this.bh = (ImageView) view.findViewById(R.id.core_sleep_fall_score_arrow);
        this.f9971a = (ImageView) view.findViewById(R.id.fall_asleep_time_arrow);
        this.df = (ImageView) view.findViewById(R.id.wake_up_regularity_arrow);
        this.f = (ImageView) view.findViewById(R.id.wake_up_time_arrow);
        this.bz = (HealthTextView) view.findViewById(R.id.core_sleep_total_sleep_title);
        this.av = (HealthTextView) view.findViewById(R.id.core_sleep_deep_sleep_title);
        this.bv = (HealthTextView) view.findViewById(R.id.core_sleep_light_sleep_title);
        this.az = (HealthTextView) view.findViewById(R.id.core_sleep_dream_sleep_title);
        this.as = (HealthTextView) view.findViewById(R.id.core_sleep_deep_sleep_continuity_title);
        this.dm = (HealthTextView) view.findViewById(R.id.core_sleep_wake_up_times_title);
        this.p = (HealthTextView) view.findViewById(R.id.core_sleep_rdi_score_title);
        this.x = (HealthTextView) view.findViewById(R.id.list_wake_up_times_item_title);
        this.bm = (HealthTextView) view.findViewById(R.id.list_fall_asleep_regularity_title);
        this.b = (HealthTextView) view.findViewById(R.id.list_fall_asleep_time_title);
        this.dk = (HealthTextView) view.findViewById(R.id.list_wake_up_regularity_item_title);
        this.j = (HealthTextView) view.findViewById(R.id.list_wake_up_time_item_title);
        this.bx = (HealthTextView) view.findViewById(R.id.core_sleep_total_sleep_beyond_title);
        this.aw = (HealthTextView) view.findViewById(R.id.core_sleep_deep_sleep_beyond_title);
        this.bw = (HealthTextView) view.findViewById(R.id.core_sleep_light_sleep_beyond_title);
        this.bb = (HealthTextView) view.findViewById(R.id.core_sleep_dream_sleep_beyond_title);
        this.ao = (HealthTextView) view.findViewById(R.id.core_sleep_deep_sleep_continuity_beyond_title);
        this.dl = (HealthTextView) view.findViewById(R.id.core_sleep_wake_up_times_beyond_title);
        this.m = (HealthTextView) view.findViewById(R.id.core_sleep_rdi_score_beyond_title);
        this.bg = (HealthTextView) view.findViewById(R.id.list_fall_asleep_regularity_beyond_title);
        this.c = (HealthTextView) view.findViewById(R.id.list_fall_asleep_time_beyond_title);
        this.di = (HealthTextView) view.findViewById(R.id.list_wake_up_regularity_item_beyond_title);
        this.h = (HealthTextView) view.findViewById(R.id.list_wake_up_time_item_beyond_title);
        this.bj.dVX_(this.bs, this.ak, this.bn, this.ax, this.al, this.dh, this.o, this.bh, this.df, this.f9971a, this.f);
    }

    private void dwI_(View view) {
        this.ca = (LinearLayout) view.findViewById(R.id.total_sleep_layout_item);
        this.aq = (LinearLayout) view.findViewById(R.id.deep_sleep_layout_item);
        this.br = (LinearLayout) view.findViewById(R.id.light_sleep_layout_item);
        this.au = (LinearLayout) view.findViewById(R.id.dream_sleep_layout_item);
        this.cn = (LinearLayout) view.findViewById(R.id.list_sleep_part_item);
        this.cx = (LinearLayout) view.findViewById(R.id.list_wake_times_item);
        this.n = (LinearLayout) view.findViewById(R.id.list_breath_quality_item);
        this.w = (LinearLayout) view.findViewById(R.id.list_wake_up_times_item);
        this.be = (LinearLayout) view.findViewById(R.id.list_fall_asleep_regularity_item);
        this.e = (LinearLayout) view.findViewById(R.id.list_fall_asleep_time_item);
        this.de = (LinearLayout) view.findViewById(R.id.list_wake_up_regularity_item);
        this.i = (LinearLayout) view.findViewById(R.id.list_wake_up_time_item);
        this.cb = (HealthTextView) view.findViewById(R.id.total_sleep_level_tv);
        this.ap = (HealthTextView) view.findViewById(R.id.deep_sleep_level_tv);
        this.bt = (HealthTextView) view.findViewById(R.id.light_sleep_level_tv);
        this.ba = (HealthTextView) view.findViewById(R.id.dream_sleep_level_tv);
        this.aj = (HealthTextView) view.findViewById(R.id.deep_sleep_continuity_level_tv);
        this.dg = (HealthTextView) view.findViewById(R.id.wakeup_time_tv);
        this.l = (HealthTextView) view.findViewById(R.id.breath_quality_tv);
        this.v = (HealthTextView) view.findViewById(R.id.list_wake_up_times_item_level);
        this.bf = (HealthTextView) view.findViewById(R.id.fall_score_tv);
        this.d = (HealthTextView) view.findViewById(R.id.daily_fall_time_tv);
        this.db = (HealthTextView) view.findViewById(R.id.wake_up_regular_tv);
        this.g = (HealthTextView) view.findViewById(R.id.wake_up_time_tv);
    }

    private void j() {
        this.bs.setVisibility(0);
        this.ak.setVisibility(0);
        this.bn.setVisibility(0);
        this.ax.setVisibility(0);
        this.al.setVisibility(0);
        this.dh.setVisibility(0);
        this.o.setVisibility(0);
        this.bh.setVisibility(0);
        this.df.setVisibility(0);
        this.f9971a.setVisibility(0);
        this.f.setVisibility(0);
        this.ca.setEnabled(true);
        this.aq.setEnabled(true);
        this.br.setEnabled(true);
        this.au.setEnabled(true);
        this.cn.setEnabled(true);
        this.cx.setEnabled(true);
        this.n.setEnabled(true);
        this.be.setEnabled(true);
        this.de.setEnabled(true);
        this.e.setEnabled(true);
        this.i.setEnabled(true);
    }

    private void d(int i, boolean z) {
        int dailyDeepSleepTime;
        int dailyShallowSleepTime;
        int dailySlumberTime;
        LogUtil.a("R_Sleep_UIHLH_CoreSleepServiceView", "refreshPieChart, isCoreSleep = ", Boolean.valueOf(z));
        if (i == 2 || i == 3 || i == 4) {
            dailyDeepSleepTime = this.cs.getDailyDeepSleepTime();
            dailyShallowSleepTime = this.cs.getDailyShallowSleepTime();
            dailySlumberTime = this.cs.getDailySlumberTime();
        } else {
            dailyDeepSleepTime = this.cs.getDeepSleepTime();
            dailyShallowSleepTime = this.cs.getShallowSleepTime();
            dailySlumberTime = this.cs.getSlumberSleepTime();
        }
        ArrayList arrayList = new ArrayList(16);
        arrayList.add(Integer.valueOf(Math.round(dailyDeepSleepTime / 1.0f)));
        arrayList.add(Integer.valueOf(Math.round(dailyShallowSleepTime / 1.0f)));
        if (z) {
            arrayList.add(Integer.valueOf(Math.round(dailySlumberTime / 1.0f)));
        }
        HealthRingChartAdapter healthRingChartAdapter = new HealthRingChartAdapter(this.ac, new nld().b(true), scn.c(this.ac, arrayList, z, i));
        healthRingChartAdapter.a(new HealthRingChartAdapter.DataFormatter() { // from class: qag
            @Override // com.huawei.ui.commonui.chart.HealthRingChartAdapter.DataFormatter
            public final String format(nkz nkzVar) {
                return CoreSleepServiceView.this.c(nkzVar);
            }
        });
        if (z) {
            LogUtil.a("UIHLH_CoreSleepServiceView", "DSM: ", Integer.valueOf(dailyDeepSleepTime), ",LSM:", Integer.valueOf(dailyShallowSleepTime), ",RSM:", Integer.valueOf(dailySlumberTime));
            this.ag.setAdapter(healthRingChartAdapter);
            setDescForRingChart(this.ag);
        }
        this.cu.setAdapter(healthRingChartAdapter);
        setDescForRingChart(this.cu);
    }

    public /* synthetic */ String c(nkz nkzVar) {
        return scn.d(this.ac, (int) nkzVar.i());
    }

    private void setDescForRingChart(HealthRingChart healthRingChart) {
        if (LanguageUtil.j(this.ac)) {
            healthRingChart.setDesc(getResources().getString(R$string.IDS_hw_show_main_home_page_sleep), getResources().getString(R$string.IDS_core_sleep_ratio));
        }
    }

    private void a(int i, int i2) {
        int i3 = i / 60;
        if (i2 == 1 && i3 >= 15) {
            scx.e(this.ac, this.cl, "EXCE_NORMALSLEEP__TIME_ERROR");
        }
        int i4 = i % 60;
        if (i3 == 0) {
            this.q.setVisibility(8);
            this.r.setVisibility(8);
            this.t.setText(UnitUtil.e(i4, 1, 0));
            return;
        }
        this.q.setVisibility(0);
        this.r.setVisibility(0);
        String e2 = UnitUtil.e(i3, 1, 0);
        String e3 = UnitUtil.e(i4, 1, 0);
        this.q.setText(e2);
        this.t.setText(e3);
    }

    private void c(HealthTextView healthTextView, int i, int i2, int i3) {
        if (i == 0) {
            healthTextView.setText("");
            return;
        }
        int i4 = i / 60;
        int i5 = i % 60;
        if (i4 == 0) {
            String e2 = UnitUtil.e(i5, 1, 0);
            String string = this.ac.getString(R$string.IDS_hw_show_set_target_sport_time_unit, e2);
            SpannableString spannableString = new SpannableString(string);
            spannableString.setSpan(new AbsoluteSizeSpan(i2, true), 0, string.length(), 17);
            spannableString.setSpan(new AbsoluteSizeSpan(i3, true), 0, e2.length(), 17);
            spannableString.setSpan(Typeface.create(Constants.FONT, 0), 0, e2.length(), 17);
            healthTextView.setText(spannableString);
            return;
        }
        String e3 = UnitUtil.e(i4, 1, 0);
        String e4 = UnitUtil.e(i5, 1, 0);
        String string2 = this.ac.getResources().getString(R$string.IDS_h_min_unit, e3, e4);
        SpannableString spannableString2 = new SpannableString(string2);
        spannableString2.setSpan(new AbsoluteSizeSpan(i2, true), 0, string2.length(), 17);
        int indexOf = spannableString2.toString().indexOf(e3);
        spannableString2.setSpan(new AbsoluteSizeSpan(i3, true), indexOf, e3.length() + indexOf, 17);
        spannableString2.setSpan(Typeface.create(Constants.FONT, 0), indexOf, e3.length() + indexOf, 17);
        int indexOf2 = spannableString2.toString().indexOf(e4, indexOf + e3.length() + 1);
        spannableString2.setSpan(new AbsoluteSizeSpan(i3, true), indexOf2, e4.length() + indexOf2, 17);
        spannableString2.setSpan(Typeface.create(Constants.FONT, 0), indexOf2, e4.length() + indexOf2, 17);
        healthTextView.setText(spannableString2);
    }
}
