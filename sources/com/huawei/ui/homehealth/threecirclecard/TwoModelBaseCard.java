package com.huawei.ui.homehealth.threecirclecard;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.R;
import com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData;
import com.huawei.healthcloud.plugintrack.callback.CommonSingleCallback;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.motion.HealthOpenSDK;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder;
import com.huawei.ui.homehealth.stepscard.StepsCardViewHolder;
import com.huawei.ui.homehealth.threecirclecard.TwoModelBaseCard;
import defpackage.ixx;
import defpackage.koq;
import defpackage.nrz;
import defpackage.nsn;
import defpackage.oum;
import defpackage.oun;
import defpackage.owg;
import defpackage.pwj;
import defpackage.pxp;
import health.compact.a.LanguageUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class TwoModelBaseCard extends AbstractBaseCardData implements View.OnClickListener {
    protected Context b;
    protected int e;
    protected oun g;
    private CommonSingleCallback<Boolean> h;
    private final Observer j;
    private StepsCardViewHolder i = null;
    protected HealthOpenSDK c = null;
    private int f = 0;

    /* renamed from: a, reason: collision with root package name */
    protected int[] f9627a = {270, 25, 12, 10000};
    protected int[] d = {0, 0, 0, 0, 0, 0};

    protected void b() {
    }

    protected void b(String str, int i) {
    }

    protected void e() {
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void refreshCardData() {
    }

    public TwoModelBaseCard(Context context) {
        this.b = context;
        Observer observer = new Observer() { // from class: ovf
            @Override // com.huawei.haf.design.pattern.Observer
            public final void notify(String str, Object[] objArr) {
                TwoModelBaseCard.this.e(str, objArr);
            }
        };
        this.j = observer;
        ObserverManagerUtil.d(observer, "EXIT_APP");
    }

    public /* synthetic */ void e(String str, Object[] objArr) {
        if ("EXIT_APP".equals(str)) {
            LogUtil.a("Step_TwoModelBaseCard", "StepsCardData EXIT_APP");
            e();
        }
    }

    public void a(int i, int[] iArr, int[] iArr2) {
        this.e = i;
        this.d = iArr;
        this.f9627a = iArr2;
    }

    protected void dir_(View view) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
            marginLayoutParams.setMarginStart(this.b.getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e) + ((Integer) safeRegionWidth.first).intValue());
            marginLayoutParams.setMarginEnd(this.b.getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d) + ((Integer) safeRegionWidth.second).intValue());
            view.setLayoutParams(marginLayoutParams);
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void onDestroy() {
        super.onDestroy();
        if (this.h != null) {
            pwj.e().b(this.h);
            this.h = null;
        }
        ObserverManagerUtil.c(this.j);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public RecyclerView.ViewHolder getCardViewHolder(ViewGroup viewGroup, LayoutInflater layoutInflater) {
        if (layoutInflater == null) {
            LogUtil.b("Step_TwoModelBaseCard", "getCardViewHolder layoutInflater == null");
            layoutInflater = LayoutInflater.from(BaseApplication.getContext());
        }
        StepsCardViewHolder stepsCardViewHolder = new StepsCardViewHolder(layoutInflater.inflate(R.layout.layout_step_card_container, viewGroup, false), this.b, false);
        BaseActivity.setViewSafeRegion(true, stepsCardViewHolder.getHomeStepCardLayout());
        return stepsCardViewHolder;
    }

    public void g_() {
        if (c() != null) {
            if (koq.c(pwj.e().d())) {
                if (this.f == this.d[3]) {
                    LogUtil.a("Step_TwoModelBaseCard", "processOriginListData mLastShowTipsStep == mSteps");
                    return;
                }
                LogUtil.a("Step_TwoModelBaseCard", "processMsgUpdateDataOriginal show Original icon ");
                int i = this.d[3];
                this.f = i;
                owg.a(this.b, i);
                if ("".equals(SharedPreferenceManager.b(this.b, String.valueOf(10000), "data_origin_icon_red_dot"))) {
                    d();
                } else {
                    c().getFitnessDataOriginIcon().setBackgroundResource(R.drawable._2131430002_res_0x7f0b0a72);
                }
                c().setFitnessDataOriginIcon2Visible(0);
                return;
            }
            LogUtil.a("Step_TwoModelBaseCard", "processMsgUpdateDataOriginal hide Original icon ");
            c().setFitnessDataOriginIcon2Visible(8);
        }
    }

    private void d() {
        if (this.b == null || c() == null) {
            ReleaseLogUtil.d("Step_TwoModelBaseCard", "isRtlImage obj is null");
            return;
        }
        if (LanguageUtil.bc(this.b)) {
            BitmapDrawable cKn_ = nrz.cKn_(this.b, R.drawable._2131430003_res_0x7f0b0a73);
            if (cKn_ == null) {
                c().getFitnessDataOriginIcon().setBackgroundResource(R.drawable._2131430003_res_0x7f0b0a73);
                return;
            } else {
                c().getFitnessDataOriginIcon().setBackground(cKn_);
                return;
            }
        }
        c().getFitnessDataOriginIcon().setBackgroundResource(R.drawable._2131430003_res_0x7f0b0a73);
    }

    public void onClick(View view) {
        if (nsn.o()) {
            LogUtil.a("Step_TwoModelBaseCard", "click too fast");
            ViewClickInstrumentation.clickOnView(view);
        } else {
            if (view == null) {
                LogUtil.b("Step_TwoModelBaseCard", "onClick view == null");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            if (view.getId() == R.id.hw_health_fitness_data_origin_icon || view.getId() == R.id.hw_health_fitness_data_textview || view.getId() == R.id.hw_health_fitness_data_origin_icon2_layout || view.getId() == R.id.bottom_step_tip_layout) {
                j();
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void j() {
        if (c() == null || c().getFitnessDataOriginIcon() == null || c().getFitnessDataOriginIcon().getVisibility() != 0) {
            return;
        }
        o();
    }

    protected void o() {
        LogUtil.a("Step_TwoModelBaseCard", "showFitnessDataOriginDialog()");
        if (c() == null) {
            LogUtil.b("Step_TwoModelBaseCard", "showFitnessDataOriginDialog mStepsCardViewHolder == null");
            return;
        }
        c().getFitnessDataOriginIcon().setBackgroundResource(R.drawable._2131430002_res_0x7f0b0a72);
        if ("".equals(SharedPreferenceManager.b(this.b, String.valueOf(10000), "data_origin_icon_red_dot"))) {
            SharedPreferenceManager.e(this.b, String.valueOf(10000), "data_origin_icon_red_dot", "false", new StorageParams());
        }
        pwj.e().a(this.b, owg.d());
    }

    public void e(oun ounVar) {
        this.g = ounVar;
    }

    public void e(HealthOpenSDK healthOpenSDK) {
        this.c = healthOpenSDK;
    }

    public StepsBaseCardViewHolder c() {
        return this.i;
    }

    protected void d(final String str) {
        if (c() == null || c().getTwoModelSwitchArea() == null) {
            LogUtil.h("Step_TwoModelBaseCard", "setCardChangedListener getViewHolder() is null.");
        } else {
            LogUtil.a("Step_TwoModelBaseCard", "setCardChangedListener");
            c().getTwoModelSwitchArea().setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homehealth.threecirclecard.TwoModelBaseCard.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a("Step_TwoModelBaseCard", "switch is onClick");
                    if (!oum.b || Math.abs(System.currentTimeMillis() - oum.d) < 2000) {
                        LogUtil.b("Step_TwoModelBaseCard", "click too fast");
                        ViewClickInstrumentation.clickOnView(view);
                    } else {
                        if (TwoModelBaseCard.this.g == null) {
                            LogUtil.b("Step_TwoModelBaseCard", "mStepsCardManager is null");
                            ViewClickInstrumentation.clickOnView(view);
                            return;
                        }
                        oum.d = System.currentTimeMillis();
                        TwoModelBaseCard.this.a();
                        if (!nsn.aa(TwoModelBaseCard.this.b)) {
                            TwoModelBaseCard.this.b();
                        }
                        TwoModelBaseCard.this.g.d(str);
                        ViewClickInstrumentation.clickOnView(view);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (c() == null) {
            return;
        }
        HealthTextView bubbleText = c().getBubbleText();
        if (bubbleText == null) {
            LogUtil.h("Step_TwoModelBaseCard", "bubble is null");
        } else {
            bubbleText.setVisibility(8);
            this.g.b(true);
        }
    }

    public void e(int i) {
        if (c() == null) {
            return;
        }
        c().updateMsgRedDotVisibility(i);
    }

    public void a(String str) {
        if (c() == null) {
            return;
        }
        c().setMsgRedDotText(str);
    }

    protected void m() {
        if (c().getFitnessDataOriginIconLayout() != null) {
            c().getFitnessDataOriginIconLayout().setOnClickListener(this);
        } else if (c().getFitnessDataOriginIcon() != null) {
            c().getFitnessDataOriginIcon().setOnClickListener(this);
        }
        c().setFitnessDataOriginIcon2Visible(8);
    }

    protected void t() {
        ReleaseLogUtil.e("Step_TwoModelBaseCard", "click into steps = ", Integer.valueOf(this.d[3]));
        HealthOpenSDK healthOpenSDK = this.c;
        Context context = this.b;
        int[] iArr = this.d;
        owg.d(healthOpenSDK, context, iArr[3], iArr[4]);
    }

    protected void s() {
        owg.b(this.c, this.b, this.d[1]);
    }

    protected void p() {
        pxp.a();
        owg.a(this.c, this.b, this.d[0]);
    }

    protected void l() {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        ixx.d().d(this.b, AnalyticsValue.HEALTH_TIME_STRENGTH_CLICK_2010098.value(), hashMap, 0);
        pxp.a(4);
        s();
    }

    public void k() {
        if (c() == null || c().getFitnessDataOriginIcon() == null) {
            LogUtil.h("Step_TwoModelBaseCard", "holder or view is null");
        } else {
            c().getFitnessDataOriginIcon().setBackgroundResource(R.drawable._2131430002_res_0x7f0b0a72);
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public String getCardName() {
        return "TwoModelBaseCard";
    }
}
