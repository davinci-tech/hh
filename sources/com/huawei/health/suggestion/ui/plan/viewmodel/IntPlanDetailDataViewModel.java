package com.huawei.health.suggestion.ui.plan.viewmodel;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.View;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.suggestion.ui.plan.viewholder.IntAiDialogViewHolder;
import com.huawei.health.suggestion.ui.plan.viewholder.OverseaAiDialogViewHolder;
import com.huawei.health.suggestion.ui.plan.viewmodel.IntPlanDetailDataViewModel;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitnessPackageInfo;
import defpackage.ase;
import defpackage.ash;
import defpackage.fyw;
import defpackage.gdw;
import defpackage.koq;
import defpackage.mmw;
import defpackage.mnw;
import defpackage.mtl;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class IntPlanDetailDataViewModel extends ViewModel {

    /* renamed from: a, reason: collision with root package name */
    private List<Integer> f3300a;
    private IntPlan b;
    private boolean h = false;
    private Handler d = new Handler(Looper.getMainLooper()) { // from class: com.huawei.health.suggestion.ui.plan.viewmodel.IntPlanDetailDataViewModel.3
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            Object obj = message.obj;
            if (obj instanceof gdw) {
                gdw gdwVar = (gdw) obj;
                int i = message.what;
                if (i == 0) {
                    IntPlanDetailDataViewModel.this.g.put(gdwVar.a(), true);
                    IntPlanDetailDataViewModel.this.j.setValue(gdwVar);
                    LogUtil.a("Suggestion_PlanDetailDataViewModel", "handleMessage add ItemData type:", Integer.valueOf(gdwVar.a()));
                } else {
                    if (i != 1) {
                        return;
                    }
                    IntPlanDetailDataViewModel.this.g.put(gdwVar.a(), false);
                    LogUtil.a("Suggestion_PlanDetailDataViewModel", "handleMessage delete ItemData type:", Integer.valueOf(gdwVar.a()));
                    IntPlanDetailDataViewModel.this.c.setValue(gdwVar);
                }
            }
        }
    };
    private MutableLiveData<gdw> j = new MutableLiveData<>();
    private MutableLiveData<gdw> c = new MutableLiveData<>();
    private SparseBooleanArray g = new SparseBooleanArray();
    private MutableLiveData<Boolean> e = new MutableLiveData<>();

    /* JADX INFO: Access modifiers changed from: private */
    public void e(gdw gdwVar, int i) {
        LogUtil.a("Suggestion_PlanDetailDataViewModel", "freshCurrentPlan viewType:", Integer.valueOf(gdwVar.a()), ",viewId:", Integer.valueOf(gdwVar.c()));
        b(gdwVar, i);
    }

    public void a(IntPlan intPlan) {
        if (intPlan == null) {
            return;
        }
        this.b = intPlan;
        j();
    }

    private void j() {
        int i;
        boolean k;
        if (this.b == null) {
            LogUtil.h("Suggestion_PlanDetailDataViewModel", "freshPlanDetail() mCurrentPlan is null");
            return;
        }
        this.h = false;
        h();
        ArrayList arrayList = new ArrayList();
        if (!ase.f() || this.b.getPlanType() != IntPlan.PlanType.AI_FITNESS_PLAN) {
            arrayList.add(new gdw(1, 1));
        }
        if (this.b.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN) {
            String e = mtl.e();
            if (TextUtils.isEmpty(e)) {
                k = Utils.o();
                ReleaseLogUtil.a("Suggestion_PlanDetailDataViewModel", "freshPlanDetail cacheJson ", e, " isH5AiFitnessPlan ", Boolean.valueOf(k));
            } else {
                k = ase.k(this.b);
            }
            i = k ? 8 : 4;
        } else {
            i = 2;
        }
        arrayList.add(new gdw(2, i));
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            ((gdw) arrayList.get(i2)).b(this.b);
        }
        gdw gdwVar = new gdw(3, 5);
        arrayList.add(gdwVar);
        int i3 = AnonymousClass2.e[this.b.getPlanType().ordinal()];
        gdwVar.b(Integer.valueOf(i3 != 1 ? i3 != 2 ? i3 != 3 ? -1 : 4046 : 4047 : 4041));
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            e((gdw) arrayList.get(i4), 0);
        }
    }

    /* renamed from: com.huawei.health.suggestion.ui.plan.viewmodel.IntPlanDetailDataViewModel$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[IntPlan.PlanType.values().length];
            e = iArr;
            try {
                iArr[IntPlan.PlanType.AI_FITNESS_PLAN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                e[IntPlan.PlanType.FIT_PLAN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                e[IntPlan.PlanType.AI_RUN_PLAN.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private void h() {
        ((PlanApi) Services.a("CoursePlanService", PlanApi.class)).getAllFitnessPackage(-1, new UiCallback<List<FitnessPackageInfo>>() { // from class: com.huawei.health.suggestion.ui.plan.viewmodel.IntPlanDetailDataViewModel.4
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.b("Suggestion_PlanDetailDataViewModel", "loadAllPlanList on freshPlanDetail fail.");
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List<FitnessPackageInfo> list) {
                LogUtil.a("Suggestion_PlanDetailDataViewModel", "loadAllPlanList on freshPlanDetail success.");
            }
        });
    }

    public void d(IntAiDialogViewHolder.e eVar) {
        if (this.h || ase.k(this.b)) {
            return;
        }
        gdw gdwVar = new gdw(0, 0);
        b(eVar);
        gdwVar.b(eVar);
        e(gdwVar, 0);
    }

    public void b() {
        e(new gdw(0, 0), 1);
    }

    public void d(final boolean z) {
        this.h = true;
        this.e.postValue(false);
        final PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.h("Suggestion_PlanDetailDataViewModel", "loadAllPlanList planApi is null.");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: gbh
                @Override // java.lang.Runnable
                public final void run() {
                    IntPlanDetailDataViewModel.this.a(planApi, z);
                }
            });
        }
    }

    public /* synthetic */ void a(PlanApi planApi, boolean z) {
        b(planApi);
        if (!fyw.d()) {
            a(planApi);
        } else {
            a(z);
        }
    }

    private void b(PlanApi planApi) {
        planApi.getAllFitnessPackage(-1, new UiCallback<List<FitnessPackageInfo>>() { // from class: com.huawei.health.suggestion.ui.plan.viewmodel.IntPlanDetailDataViewModel.1
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.b("Suggestion_PlanDetailDataViewModel", "loadAllPlanList onFailure.");
                IntPlanDetailDataViewModel.this.h = false;
                IntPlanDetailDataViewModel.this.e.postValue(true);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List<FitnessPackageInfo> list) {
                if (list == null) {
                    LogUtil.h("Suggestion_PlanDetailDataViewModel", "loadDomesticAllPlanList onSuccess data is null.");
                    return;
                }
                if (!fyw.d()) {
                    IntPlanDetailDataViewModel.this.h = true;
                    LogUtil.h("Suggestion_PlanDetailDataViewModel", "loadDomesticAllPlanList onSuccess size ", Integer.valueOf(list.size()));
                    for (int i = 0; i < list.size(); i++) {
                        FitnessPackageInfo fitnessPackageInfo = list.get(i);
                        if (fitnessPackageInfo != null && fitnessPackageInfo.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN.getType()) {
                            gdw gdwVar = new gdw(i + 1, 3);
                            gdwVar.b(fitnessPackageInfo);
                            LogUtil.a("Suggestion_PlanDetailDataViewModel", "loadDomesticAllPlanList onSuccess info = ", fitnessPackageInfo.toString());
                            IntPlanDetailDataViewModel.this.e(gdwVar, 0);
                        }
                    }
                    if (list.size() % 2 != 0) {
                        IntPlanDetailDataViewModel.this.e(new gdw(list.size() + 1, 7), 0);
                        return;
                    }
                    return;
                }
                LogUtil.h("Suggestion_PlanDetailDataViewModel", "loadDomesticAllPlanList onSuccess size " + list.size());
            }
        });
    }

    private void a(boolean z) {
        gdw gdwVar = new gdw(0, 5);
        gdwVar.b(Integer.valueOf(z ? -4048 : 4048));
        e(gdwVar, 0);
    }

    private void a(PlanApi planApi) {
        planApi.getAllPlans(0, new UiCallback<mnw>() { // from class: com.huawei.health.suggestion.ui.plan.viewmodel.IntPlanDetailDataViewModel.5
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.b("Suggestion_PlanDetailDataViewModel", "loadAllPlanList onFailure.");
                IntPlanDetailDataViewModel.this.h = false;
                IntPlanDetailDataViewModel.this.e.postValue(true);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccess(mnw mnwVar) {
                if (mnwVar != null && !koq.b(mnwVar.b())) {
                    IntPlanDetailDataViewModel.this.h = true;
                    List<mmw> b = mnwVar.b();
                    IntPlanDetailDataViewModel.this.e.postValue(Boolean.valueOf(mnwVar.b().size() == 0));
                    for (int i = 0; i < b.size(); i++) {
                        mmw mmwVar = b.get(i);
                        if (mmwVar != null) {
                            gdw gdwVar = new gdw(i + 10, 3);
                            gdwVar.b(mmwVar);
                            IntPlanDetailDataViewModel.this.e(gdwVar, 0);
                        }
                    }
                    IntPlanDetailDataViewModel.this.i();
                    return;
                }
                LogUtil.h("Suggestion_PlanDetailDataViewModel", "onSuccess getRunPlanInfoList is empty");
            }
        });
    }

    public void d() {
        e(new gdw(-1, 2), 1);
        e(new gdw(-1, 4), 1);
        e(new gdw(-1, 8), 1);
        e(new gdw(-1, 1), 1);
        e(new gdw(-1, 5), 1);
    }

    public void e() {
        gdw gdwVar;
        if (fyw.d()) {
            gdwVar = new gdw(-1, 5);
        } else {
            gdwVar = new gdw(-1, 3);
            c();
        }
        e(gdwVar, 1);
        this.h = false;
    }

    public void a() {
        e(new gdw(-1, 5), 1);
        gdw gdwVar = new gdw(-1, 3);
        c();
        e(gdwVar, 1);
        this.h = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (Utils.o()) {
            String b = ash.b("read_oversea_dialog");
            if (this.h) {
                if (b == null || b.equals("")) {
                    OverseaAiDialogViewHolder.e eVar = new OverseaAiDialogViewHolder.e();
                    eVar.f3295a = BaseApplication.e().getString(R.string._2130848690_res_0x7f022bb2);
                    eVar.e = BaseApplication.e().getString(R.string._2130848409_res_0x7f022a99);
                    eVar.b = new View.OnClickListener() { // from class: gbj
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            IntPlanDetailDataViewModel.this.aJN_(view);
                        }
                    };
                    gdw gdwVar = new gdw(0, 6);
                    gdwVar.b(eVar);
                    e(gdwVar, 0);
                    ash.a("read_oversea_dialog", "1");
                }
            }
        }
    }

    public /* synthetic */ void aJN_(View view) {
        c();
        ViewClickInstrumentation.clickOnView(view);
    }

    public void c() {
        e(new gdw(-1, 6), 1);
    }

    public void e(Observer<gdw> observer) {
        this.j.observeForever(observer);
    }

    public void a(Observer<Boolean> observer) {
        this.e.observeForever(observer);
    }

    public void d(Observer<gdw> observer) {
        this.c.observeForever(observer);
    }

    public void c(Observer<gdw> observer) {
        this.j.removeObserver(observer);
    }

    public void g(Observer<Boolean> observer) {
        this.e.removeObserver(observer);
    }

    public void b(Observer<gdw> observer) {
        this.c.removeObserver(observer);
    }

    private void b(gdw gdwVar, int i) {
        Message obtainMessage = this.d.obtainMessage();
        obtainMessage.what = i;
        obtainMessage.obj = gdwVar;
        this.d.sendMessage(obtainMessage);
    }

    private void b(IntAiDialogViewHolder.e eVar) {
        IntPlan intPlan = this.b;
        if (intPlan != null && ase.l(intPlan)) {
            eVar.c = R.drawable._2131428309_res_0x7f0b03d5;
        } else {
            eVar.c = R.drawable._2131429700_res_0x7f0b0944;
        }
    }

    public void d(List<Integer> list) {
        this.f3300a = list;
    }

    @Override // androidx.lifecycle.ViewModel
    public void onCleared() {
        super.onCleared();
    }
}
