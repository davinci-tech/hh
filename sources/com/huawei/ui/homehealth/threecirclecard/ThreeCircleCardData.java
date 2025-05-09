package com.huawei.ui.homehealth.threecirclecard;

import android.content.Context;
import android.content.res.Configuration;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.homehealth.stepscard.StepsBaseCardViewHolder;
import com.huawei.ui.homehealth.threecirclecard.ThreeCircleCardData;
import com.huawei.ui.homehealth.threecirclecard.model.StepsViewModel;
import com.huawei.ui.main.stories.me.util.StepCounterSupportUtil;
import defpackage.dsm;
import defpackage.ggl;
import defpackage.ixx;
import defpackage.jdl;
import defpackage.jfa;
import defpackage.nkx;
import defpackage.nsn;
import defpackage.oun;
import defpackage.owg;
import defpackage.pwj;
import defpackage.pxp;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class ThreeCircleCardData extends TwoModelBaseCard {
    private HealthTextView f;
    private final int[] h;
    private Context i;
    private volatile boolean j;
    private ThreeCircleCardViewHolder k;
    private final int[] l;
    private long o;

    private void q() {
    }

    private void x() {
    }

    @Override // com.huawei.ui.homehealth.threecirclecard.TwoModelBaseCard
    protected void b() {
    }

    public void j() {
    }

    public ThreeCircleCardData(Context context) {
        super(context);
        this.j = false;
        this.o = System.currentTimeMillis();
        this.h = new int[]{-1, -1, -1};
        this.l = new int[]{-1, -1, -1};
        CommonUtil.a("SCUI_ThreeCircleCardData", "ThreeCircleCardData");
        this.i = context == null ? BaseApplication.getContext() : context;
    }

    @Override // com.huawei.ui.homehealth.threecirclecard.TwoModelBaseCard, com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public RecyclerView.ViewHolder getCardViewHolder(ViewGroup viewGroup, LayoutInflater layoutInflater) {
        ThreeCircleCardViewHolder threeCircleCardViewHolder = new ThreeCircleCardViewHolder(viewGroup, this.i, false);
        this.k = threeCircleCardViewHolder;
        dir_(threeCircleCardViewHolder.getHomeStepCardLayout());
        i();
        n();
        d("threeCircleCard");
        q();
        d();
        return this.k;
    }

    protected void d() {
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: ouo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ThreeCircleCardData.this.dhW_(view);
            }
        };
        View.OnClickListener onClickListener2 = new View.OnClickListener() { // from class: ouk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ThreeCircleCardData.this.dhX_(view);
            }
        };
        View.OnClickListener onClickListener3 = new View.OnClickListener() { // from class: oup
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ThreeCircleCardData.this.dhY_(view);
            }
        };
        View.OnClickListener onClickListener4 = new View.OnClickListener() { // from class: ous
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ThreeCircleCardData.this.dhZ_(view);
            }
        };
        View.OnClickListener onClickListener5 = new View.OnClickListener() { // from class: com.huawei.ui.homehealth.threecirclecard.ThreeCircleCardData.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (!nsn.o()) {
                    owg.b(ThreeCircleCardData.this.i, ThreeCircleCardData.this.e, ThreeCircleCardData.this.d, ThreeCircleCardData.this.f9627a, "1");
                    ViewClickInstrumentation.clickOnView(view);
                } else {
                    ViewClickInstrumentation.clickOnView(view);
                }
            }
        };
        Context context = this.i;
        if (context instanceof BaseActivity) {
            onClickListener = nkx.cwZ_(onClickListener, (BaseActivity) context, true, AnalyticsValue.HEALTH_HOME_STEP_DETAIL_2010002.value());
            onClickListener2 = nkx.cwZ_(onClickListener2, (BaseActivity) this.i, true, AnalyticsValue.HEALTH_HOME_CAL_DETAIL_2010006.value());
            onClickListener3 = nkx.cwZ_(onClickListener3, (BaseActivity) this.i, true, AnalyticsValue.HEALTH_TIME_STRENGTH_CLICK_2010098.value());
            onClickListener4 = nkx.cwZ_(onClickListener4, (BaseActivity) this.i, true, AnalyticsValue.HEALTH_HOME_STEP_DETAIL_2010002.value());
            onClickListener5 = nkx.cwZ_(onClickListener5, (BaseActivity) this.i, true, AnalyticsValue.HEALTH_HOME_CIRCLE_RING_1040091.value());
        }
        LogUtil.a("SCUI_ThreeCircleCardData", "setOnClickListener THREE_CIRCLE_CARD");
        c().setThreeCircleDataListener(0, onClickListener2);
        c().setThreeCircleDataListener(1, onClickListener3);
        c().setThreeCircleDataListener(2, onClickListener4);
        c().setThreeCircleLayoutListener(onClickListener5);
        c().setBottomClickListener(onClickListener);
        m();
    }

    public /* synthetic */ void dhW_(View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        pxp.a(1);
        owg.b(this.c, this.i, this.d[3], this.d[4]);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dhX_(View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        ReleaseLogUtil.e("SCUI_ThreeCircleCardData", "click into calories = ", Integer.valueOf(this.d[0]));
        p();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dhY_(View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            l();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public /* synthetic */ void dhZ_(View view) {
        if (nsn.o()) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        ReleaseLogUtil.e("SCUI_ThreeCircleCardData", "click into activeHours = ", Integer.valueOf(this.d[2]));
        owg.a(this.i);
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.ui.homehealth.threecirclecard.TwoModelBaseCard
    public void a(int i, int[] iArr, int[] iArr2) {
        super.a(i, iArr, iArr2);
        if (c() != null) {
            int i2 = iArr[3];
            if (i2 <= 0) {
                LogUtil.a("SCUI_ThreeCircleCardData", "clearStepData tmpStep", Integer.valueOf(i2));
                this.o = a(this.o);
            } else {
                a(i2, iArr2[3]);
            }
            LogUtil.a("SCUI_ThreeCircleCardData", "processMsgDetail curData:", Arrays.toString(iArr), " mLast:", Arrays.toString(this.h), "  curGoalValue:", Arrays.toString(iArr2), " mLastGoal:", Arrays.toString(this.l));
            if (a(iArr, this.h) || c(iArr2, this.l)) {
                v();
            }
        }
    }

    private long a(long j) {
        if (this.c == null) {
            return j;
        }
        u();
        if (!jdl.ac(j)) {
            j = System.currentTimeMillis();
            for (int i = 0; i < 3; i++) {
                c().updateProgress(i, 0, this.f9627a[i], false);
                c().setTextByPosition(i, 0, this.f9627a[i]);
            }
            if (r()) {
                pwj.e().j();
                c().setFitnessDataOriginIcon2Visible(8);
            }
            ObserverManagerUtil.c("closeStepsTips", new Object[0]);
        }
        return j;
    }

    private boolean r() {
        return this.d[3] == 0 && this.d[1] == 0 && this.d[2] == 0 && this.d[0] == 0;
    }

    private void a(int i, int i2) {
        c().updateBottomData(i, i2);
    }

    private void v() {
        if (c() == null) {
            LogUtil.a("SCUI_ThreeCircleCardData", "obj.getViewHolder() is null");
            return;
        }
        for (int i = 0; i < 3; i++) {
            e(this.d[i], i, this.f9627a[i]);
        }
    }

    private void e(int i, int i2, int i3) {
        c().updateProgress(i2, i, i3, false);
        LogUtil.a("SCUI_ThreeCircleCardData", "updateThreeCircle , update goal", Integer.valueOf(i3), "value", Integer.valueOf(i));
        c().setTextByPosition(i2, i, i3);
    }

    private void u() {
        if (StepCounterSupportUtil.a(BaseApplication.getContext()) == 3) {
            c().updateBottomData(-1, this.f9627a[3]);
        } else {
            c().updateBottomData(0, this.f9627a[3]);
        }
    }

    private boolean a(int[] iArr, int[] iArr2) {
        boolean z = false;
        for (int i = 0; i <= 2; i++) {
            int i2 = iArr[i];
            if (i2 != iArr2[i]) {
                z = true;
            }
            iArr2[i] = i2;
        }
        return z;
    }

    private boolean c(int[] iArr, int[] iArr2) {
        boolean z = false;
        for (int i = 0; i <= 2; i++) {
            int i2 = iArr[i];
            if (i2 != iArr2[i]) {
                z = true;
            }
            iArr2[i] = i2;
        }
        return z;
    }

    private void n() {
        LogUtil.a("SCUI_ThreeCircleCardData", "initSwitchBubble");
        if (oun.b()) {
            LogUtil.h("SCUI_ThreeCircleCardData", "already switch before");
            return;
        }
        ThreeCircleCardViewHolder threeCircleCardViewHolder = this.k;
        if (threeCircleCardViewHolder == null) {
            return;
        }
        HealthTextView bubbleText = threeCircleCardViewHolder.getBubbleText();
        this.f = bubbleText;
        if (bubbleText == null) {
            LogUtil.h("SCUI_ThreeCircleCardData", "bubble is null");
            return;
        }
        bubbleText.setText(this.i.getResources().getString(R.string._2130846370_res_0x7f0222a2));
        if (LanguageUtil.bc(this.i)) {
            this.f.setBackgroundResource(R.drawable._2131431520_res_0x7f0b1060);
        } else {
            this.f.setBackgroundResource(R.drawable._2131431784_res_0x7f0b1168);
        }
        this.f.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.homehealth.threecirclecard.ThreeCircleCardData.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("SCUI_ThreeCircleCardData", "bubble is clicked");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        HandlerExecutor.d(new Runnable() { // from class: com.huawei.ui.homehealth.threecirclecard.ThreeCircleCardData.2
            @Override // java.lang.Runnable
            public void run() {
                ThreeCircleCardData.this.f();
            }
        }, 2000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        LogUtil.a("SCUI_ThreeCircleCardData", "checkSyncStatusAndShowBubble");
        if (((StepsViewModel) new ViewModelProvider((ViewModelStoreOwner) this.i).get(StepsViewModel.class)).b()) {
            LogUtil.a("SCUI_ThreeCircleCardData", "is syncing, show bubble delay");
            this.j = true;
            return;
        }
        HealthTextView healthTextView = this.f;
        if (healthTextView == null) {
            LogUtil.h("SCUI_ThreeCircleCardData", "bubble is null");
        } else {
            healthTextView.setVisibility(0);
        }
    }

    public void a() {
        LogUtil.a("SCUI_ThreeCircleCardData", "checkDelayStatusAndShowBubble");
        if (!this.j) {
            LogUtil.a("SCUI_ThreeCircleCardData", "no delay");
            return;
        }
        HealthTextView healthTextView = this.f;
        if (healthTextView == null) {
            LogUtil.h("SCUI_ThreeCircleCardData", "bubble is null");
        } else {
            healthTextView.setVisibility(0);
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void onResume() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        super.onResume();
        i();
        ReleaseLogUtil.e("SCUI_ThreeCircleCardData", "finished, time cost: " + (SystemClock.elapsedRealtime() - elapsedRealtime));
    }

    @Override // com.huawei.ui.homehealth.threecirclecard.TwoModelBaseCard, com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void onDestroy() {
        LogUtil.a("SCUI_ThreeCircleCardData", "onDestroy");
        super.onDestroy();
    }

    protected void i() {
        y();
    }

    private void y() {
        if (c() == null || c().getThreeLeafIcon() == null) {
            LogUtil.a("SCUI_ThreeCircleCardData", "getViewHolder() is null.");
            return;
        }
        c().getThreeLeafIcon().setVisibility(0);
        long d = jfa.d(dsm.c, "perfect_three_leaf", 0L);
        int a2 = ggl.a(new Date(d));
        int a3 = ggl.a(new Date());
        LogUtil.a("SCUI_ThreeCircleCardData", "getPerfectTimeMills:", Long.valueOf(d), " perfectDate:", Integer.valueOf(a2), " todayDate:", Integer.valueOf(a3));
        if (d != 0 && a2 == a3) {
            c().getThreeLeafIcon().setBackground(this.i.getDrawable(R.drawable._2131430970_res_0x7f0b0e3a));
        } else {
            c().getThreeLeafIcon().setBackground(this.i.getDrawable(R.drawable._2131431768_res_0x7f0b1158));
        }
        c().getThreeLeafIcon().setContentDescription(this.i.getResources().getString(R.string.IDS_health_clover_title));
    }

    @Override // com.huawei.ui.homehealth.threecirclecard.TwoModelBaseCard, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        new HashMap().put("click", "1");
        if (view.getId() == R.id.activityLayout) {
            ReleaseLogUtil.e("SCUI_ThreeCircleCardData", "click into activityLayout");
            x();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LogUtil.a("SCUI_ThreeCircleCardData", "oonConfigurationChanged");
        ThreeCircleCardViewHolder threeCircleCardViewHolder = this.k;
        if (threeCircleCardViewHolder == null) {
            LogUtil.a("SCUI_ThreeCircleCardData", "onConfigurationChanged holder is null");
        } else {
            threeCircleCardViewHolder.updateLayout();
            HandlerExecutor.d(new Runnable() { // from class: ouq
                @Override // java.lang.Runnable
                public final void run() {
                    ThreeCircleCardData.this.g();
                }
            }, 100L);
        }
    }

    public /* synthetic */ void g() {
        this.k.j();
    }

    public void h() {
        for (int i = 0; i < 3; i++) {
            c().updateProgress(i, 0, this.f9627a[i], false);
        }
    }

    @Override // com.huawei.ui.homehealth.threecirclecard.TwoModelBaseCard
    public StepsBaseCardViewHolder c() {
        return this.k;
    }

    @Override // com.huawei.ui.homehealth.threecirclecard.TwoModelBaseCard
    public void b(String str, int i) {
        if (c() instanceof ThreeCircleCardViewHolder) {
            ((ThreeCircleCardViewHolder) c()).j();
        }
        ThreeCircleCardViewHolder threeCircleCardViewHolder = this.k;
        if (threeCircleCardViewHolder != null) {
            threeCircleCardViewHolder.updateMsgRedDotVisibility(8);
        }
        if (!"threeCircleCard".equals(str) || this.g == null) {
            return;
        }
        this.g.b(str, i);
    }

    @Override // com.huawei.ui.homehealth.threecirclecard.TwoModelBaseCard
    protected void e() {
        d("calorie", this.d[0], this.f9627a[0]);
        d("MVPA", this.d[1], this.f9627a[1]);
        d("activity", this.d[2], this.f9627a[2]);
        d("walk", this.d[3], this.f9627a[3]);
    }

    private void d(String str, int i, int i2) {
        if (i2 <= 0) {
            return;
        }
        HashMap hashMap = new HashMap(3);
        hashMap.put("click", 1);
        hashMap.put(ParsedFieldTag.TASK_TYPE, str);
        hashMap.put("taskFinishRate", Double.valueOf(UnitUtil.a((i / i2) * 100.0f, 0)));
        ixx.d().d(this.i, AnalyticsValue.HEALTH_EXIT_APP_MODE_COMPLETE_2010117.value(), hashMap, 0);
    }

    @Override // com.huawei.ui.homehealth.threecirclecard.TwoModelBaseCard, com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public String getCardName() {
        return "SCUI_ThreeCircleCardData";
    }
}
