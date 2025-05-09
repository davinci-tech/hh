package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.PlanPoster;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.suggestion.callback.JudgeCallback;
import com.huawei.health.suggestion.ui.plan.mvp.PlanDetailContract;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class fye implements PlanDetailContract.Ipresenter {

    /* renamed from: a, reason: collision with root package name */
    private PlanDetailContract.Iview f12696a;
    private String b;
    private mmw c;
    private Handler d;

    @Override // com.huawei.health.suggestion.ui.plan.mvp.PlanDetailContract.Ipresenter
    public void onResume() {
    }

    public fye(String str, PlanDetailContract.Iview iview) {
        this.f12696a = iview;
        if (iview == null) {
            LogUtil.h("PlanDetailPresenter", "constructor : mView is null.");
        } else {
            this.b = str;
            this.d = new b(Looper.getMainLooper(), this);
        }
    }

    @Override // com.huawei.health.suggestion.ui.plan.mvp.PlanDetailContract.Ipresenter
    public void getData() {
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.b("PlanDetailPresenter", "Jump getPlanData : planApi is null.");
            this.f12696a.finishView();
        } else {
            planApi.getAllPlans(0, new UiCallback<mnw>() { // from class: fye.5
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.b("PlanDetailPresenter", "No current plan now.", Integer.valueOf(i), " ", str);
                    if (fye.this.f12696a != null) {
                        fye.this.f12696a.finishView();
                    }
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void onSuccess(mnw mnwVar) {
                    if (fye.this.f12696a == null) {
                        LogUtil.h("PlanDetailPresenter", "onSuccess mView is null");
                        return;
                    }
                    if (mnwVar == null || koq.b(mnwVar.b())) {
                        LogUtil.h("PlanDetailPresenter", "onSuccess getRunPlanInfoList is empty");
                        JumpUtil.a(fye.this.f12696a.acquireContext(), (mmw) null);
                        fye.this.f12696a.finishView();
                        return;
                    }
                    fye fyeVar = fye.this;
                    fyeVar.c = ghq.a(fyeVar.b, mnwVar.b());
                    if (fye.this.c != null) {
                        fye.this.f12696a.bindTitleText(fye.this.c.i());
                        fye.this.f12696a.bindData(fye.this.c());
                        if (fye.this.d != null) {
                            fye.this.d.sendEmptyMessage(1);
                            return;
                        }
                        return;
                    }
                    LogUtil.h("PlanDetailPresenter", "onSuccess mRunPlanInfo is null");
                    JumpUtil.a(fye.this.f12696a.acquireContext(), (mmw) null);
                    fye.this.f12696a.finishView();
                }
            });
        }
    }

    @Override // com.huawei.health.suggestion.ui.plan.mvp.PlanDetailContract.Ipresenter
    public void onDestroy() {
        Handler handler = this.d;
        if (handler != null) {
            handler.removeMessages(1);
            this.d = null;
        }
        this.f12696a = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<String> c() {
        if (this.f12696a == null) {
            return new ArrayList();
        }
        mmw mmwVar = this.c;
        if (mmwVar == null) {
            return e();
        }
        PlanPoster planPoster = mmwVar.getPlanPoster();
        if (planPoster == null) {
            return e();
        }
        if (koq.b(planPoster.getPicturesForPhone()) && koq.b(planPoster.getPicturesForPad())) {
            return e();
        }
        if (nsn.ag(this.f12696a.acquireContext()) || nsn.ae(this.f12696a.acquireContext())) {
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

    private List<String> e() {
        ArrayList arrayList = new ArrayList();
        if (nsn.ag(this.f12696a.acquireContext()) || nsn.ae(this.f12696a.acquireContext())) {
            arrayList.add(nsa.d(BaseApplication.getContext().getResources().getString(R.string._2130851521_res_0x7f0236c1)));
        } else {
            arrayList.add(nsa.d(BaseApplication.getContext().getResources().getString(R.string._2130851522_res_0x7f0236c2)));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        PlanDetailContract.Iview iview = this.f12696a;
        if (iview == null) {
            LogUtil.h("PlanDetailPresenter", "addTradeView: mView is null");
            return;
        }
        View inflate = LayoutInflater.from(iview.acquireContext()).inflate(R.layout.sug_view_plan_create_btn, (ViewGroup) null);
        if (inflate == null) {
            return;
        }
        inflate.findViewById(R.id.sug_plan_create_btn).setOnClickListener(new View.OnClickListener() { // from class: fyk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                fye.this.aIp_(view);
            }
        });
        this.f12696a.addTradeView(inflate);
    }

    /* synthetic */ void aIp_(View view) {
        d();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d() {
        if (this.f12696a == null) {
            LogUtil.h("PlanDetailPresenter", "jump2CreateView: mView is null");
        } else {
            ghq.e(new JudgeCallback() { // from class: fyi
                @Override // com.huawei.health.suggestion.callback.JudgeCallback
                public final void onJudgeCallback(Object obj) {
                    fye.this.a((Boolean) obj);
                }
            });
        }
    }

    /* synthetic */ void a(Boolean bool) {
        if (bool.booleanValue()) {
            this.f12696a.showDialog();
            return;
        }
        mmw mmwVar = this.c;
        if (mmwVar == null) {
            return;
        }
        JumpUtil.b(String.valueOf(mmwVar.c()), this.f12696a.acquireContext());
        this.f12696a.finishView();
    }

    static class b extends BaseHandler<fye> {
        b(Looper looper, fye fyeVar) {
            super(looper, fyeVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: aIq_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(fye fyeVar, Message message) {
            if (message.what == 1) {
                fyeVar.a();
            }
        }
    }

    @Override // com.huawei.health.suggestion.ui.plan.mvp.PlanDetailContract.Ipresenter
    public void viewCurrentPlan() {
        PlanDetailContract.Iview iview = this.f12696a;
        if (iview == null) {
            return;
        }
        JumpUtil.c(iview.acquireContext());
    }
}
