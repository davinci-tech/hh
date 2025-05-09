package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.basefitnessadvice.model.PlanPoster;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.suggestion.callback.JudgeCallback;
import com.huawei.health.suggestion.ui.plan.mvp.PlanDetailContract;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.health.suggestion.util.ResourceJudgeUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.utils.WebViewHelp;
import com.huawei.pluginfitnessadvice.FitnessPackageInfo;
import com.huawei.trade.TradeViewApi;
import com.huawei.trade.datatype.TradeViewInfo;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes4.dex */
public class fyj implements PlanDetailContract.Ipresenter {

    /* renamed from: a, reason: collision with root package name */
    private String f12702a;
    private FitnessPackageInfo b;
    private Handler c;
    private PlanApi d;
    private View e;
    private TradeViewApi f;
    private PlanDetailContract.Iview i;

    public fyj(String str, PlanDetailContract.Iview iview) {
        this.f12702a = str;
        this.i = iview;
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        this.d = planApi;
        if (this.i == null) {
            LogUtil.h("Suggestion_PlanDetailPresenter", "constructor : mView is null.");
        } else if (planApi == null) {
            LogUtil.h("Suggestion_PlanDetailPresenter", "constructor : planApi is null.");
            this.i.finishView();
        } else {
            this.c = new e(Looper.getMainLooper(), this);
        }
    }

    @Override // com.huawei.health.suggestion.ui.plan.mvp.PlanDetailContract.Ipresenter
    public void getData() {
        if (this.d == null) {
            this.d = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        }
        PlanApi planApi = this.d;
        if (planApi == null) {
            LogUtil.h("Suggestion_PlanDetailPresenter", "getData : planApi is null.");
            this.i.finishView();
            return;
        }
        FitnessPackageInfo fitnessPkgInfoByTempId = planApi.getFitnessPkgInfoByTempId(this.f12702a);
        this.b = fitnessPkgInfoByTempId;
        if (fitnessPkgInfoByTempId == null) {
            LogUtil.h("Suggestion_PlanDetailPresenter", "null is mFitnessPackageInfo");
            this.i.finishView();
        } else {
            this.i.bindData(d());
            this.i.bindTitleText(this.b.acquireName());
            b();
        }
    }

    @Override // com.huawei.health.suggestion.ui.plan.mvp.PlanDetailContract.Ipresenter
    public void onDestroy() {
        f();
        Handler handler = this.c;
        if (handler != null) {
            handler.removeMessages(1);
            this.c.removeMessages(2);
            this.c.removeMessages(3);
            this.c = null;
        }
        this.i = null;
    }

    @Override // com.huawei.health.suggestion.ui.plan.mvp.PlanDetailContract.Ipresenter
    public void onResume() {
        PlanDetailContract.Iview iview = this.i;
        if (iview != null && CommonUtil.aa(iview.acquireContext())) {
            b();
        }
    }

    private List<String> d() {
        if (this.i == null) {
            return new ArrayList();
        }
        FitnessPackageInfo fitnessPackageInfo = this.b;
        if (fitnessPackageInfo == null) {
            return new ArrayList();
        }
        PlanPoster planPoster = fitnessPackageInfo.getPlanPoster();
        if (planPoster == null) {
            return new ArrayList();
        }
        if (nsn.ag(this.i.acquireContext()) || nsn.ae(this.i.acquireContext())) {
            if (koq.b(planPoster.getPicturesForPad())) {
                return planPoster.getPicturesForPhone();
            }
            return planPoster.getPicturesForPad();
        }
        if (koq.b(planPoster.getPicturesForPhone())) {
            return planPoster.getPicturesForPad();
        }
        return planPoster.getPicturesForPhone();
    }

    private void b() {
        if (TextUtils.isEmpty(this.f12702a)) {
            LogUtil.h("Suggestion_PlanDetailPresenter", "judgeResource: mPlanTempId is Empty");
        } else {
            ResourceJudgeUtil.c(2, this.f12702a, 2, new JudgeCallback() { // from class: fyp
                @Override // com.huawei.health.suggestion.callback.JudgeCallback
                public final void onJudgeCallback(Object obj) {
                    fyj.this.c((Integer) obj);
                }
            });
        }
    }

    /* synthetic */ void c(Integer num) {
        if (num.intValue() == 2) {
            Handler handler = this.c;
            if (handler != null) {
                handler.sendEmptyMessage(1);
                return;
            }
            return;
        }
        Handler handler2 = this.c;
        if (handler2 != null) {
            handler2.sendEmptyMessage(2);
            this.c.sendEmptyMessage(3);
        }
    }

    private void e() {
        if (this.i == null) {
            LogUtil.h("Suggestion_PlanDetailPresenter", "jump2CreateView: mView is null");
        } else {
            ghq.e(new JudgeCallback() { // from class: fyl
                @Override // com.huawei.health.suggestion.callback.JudgeCallback
                public final void onJudgeCallback(Object obj) {
                    fyj.this.a((Boolean) obj);
                }
            });
        }
    }

    /* synthetic */ void a(Boolean bool) {
        if (bool.booleanValue()) {
            this.i.showDialog();
        } else {
            JumpUtil.b(IntPlan.PlanType.FIT_PLAN.getType(), this.i.acquireContext(), this.f12702a);
            this.i.finishView();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        if (this.b == null) {
            LogUtil.h("Suggestion_PlanDetailPresenter", "addTradeView: mFitnessPackageInfo is null");
            return;
        }
        if (this.e != null) {
            LogUtil.a("Suggestion_PlanDetailPresenter", "addTradeView: mTradeView is not null");
            return;
        }
        if (this.f == null) {
            this.f = (TradeViewApi) Services.a("TradeService", TradeViewApi.class);
        }
        if (this.f == null) {
            LogUtil.h("Suggestion_PlanDetailPresenter", "TradeViewApi is null");
            return;
        }
        TradeViewInfo tradeViewInfo = new TradeViewInfo(this.f12702a, 2);
        HashMap hashMap = new HashMap(16);
        hashMap.put(WebViewHelp.BI_KEY_PULL_FROM, fhu.e().d());
        hashMap.put("resourceId", fhu.e().b());
        hashMap.put("resourceName", fhu.e().j());
        hashMap.put("pullOrder", fhu.e().c());
        tradeViewInfo.setBiParams(hashMap);
        View tradeView = this.f.getTradeView(this.i.acquireContext(), tradeViewInfo);
        this.e = tradeView;
        this.i.addTradeView(tradeView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        PlanDetailContract.Iview iview = this.i;
        if (iview == null) {
            LogUtil.h("Suggestion_PlanDetailPresenter", "addTradeView: mView is null");
            return;
        }
        iview.recycleTradeView(this.e);
        View inflate = LayoutInflater.from(this.i.acquireContext()).inflate(R.layout.sug_view_plan_create_btn, (ViewGroup) null);
        this.e = inflate;
        if (inflate == null) {
            return;
        }
        if (inflate.findViewById(R.id.sug_plan_create_btn) != null) {
            this.e.findViewById(R.id.sug_plan_create_btn).setOnClickListener(new View.OnClickListener() { // from class: fym
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    fyj.this.aIr_(view);
                }
            });
        }
        this.i.addTradeView(this.e);
    }

    /* synthetic */ void aIr_(View view) {
        e();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        View view = this.e;
        if (view == null) {
            return;
        }
        this.i.recycleTradeView(view);
        TradeViewApi tradeViewApi = this.f;
        if (tradeViewApi != null) {
            tradeViewApi.cancelView(this.e);
            this.f = null;
        }
        this.e = null;
    }

    static class e extends BaseHandler<fyj> {
        e(Looper looper, fyj fyjVar) {
            super(looper, fyjVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: aIs_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(fyj fyjVar, Message message) {
            if (message.what == 1) {
                fyjVar.a();
            } else if (message.what == 2) {
                fyjVar.f();
            } else if (message.what == 3) {
                fyjVar.c();
            }
        }
    }

    @Override // com.huawei.health.suggestion.ui.plan.mvp.PlanDetailContract.Ipresenter
    public void viewCurrentPlan() {
        PlanDetailContract.Iview iview = this.i;
        if (iview != null) {
            JumpUtil.c(iview.acquireContext());
        }
    }
}
