package com.huawei.health.suggestion.ui.plan.viewmodel;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.SparseBooleanArray;
import androidx.core.util.Consumer;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.huawei.basefitnessadvice.model.Plan;
import com.huawei.health.plan.model.PlanStatistics;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.gbg;
import defpackage.gdw;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public class PlanTabDataModel extends ViewModel {
    private int f;
    private boolean e = false;

    /* renamed from: a, reason: collision with root package name */
    private boolean f3301a = false;
    private boolean c = false;
    private boolean b = false;
    private Handler d = new Handler(Looper.getMainLooper()) { // from class: com.huawei.health.suggestion.ui.plan.viewmodel.PlanTabDataModel.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            Object obj = message.obj;
            if (obj instanceof gdw) {
                gdw gdwVar = (gdw) obj;
                PlanTabDataModel.this.j.put(gdwVar.a(), true);
                LogUtil.a("Suggestion_PlanTabDataModel", "handleMessage msg.itemData: ", Integer.valueOf(gdwVar.a()));
                PlanTabDataModel.this.i.setValue(gdwVar);
            }
        }
    };
    private MutableLiveData<gdw> i = new MutableLiveData<>();
    private SparseBooleanArray j = new SparseBooleanArray();
    private gbg h = new gbg();

    public void c(int i) {
        LogUtil.a("Suggestion_PlanTabDataModel", "loadViewList", Integer.valueOf(i));
        ArrayList arrayList = new ArrayList();
        this.f = i;
        int i2 = 0;
        arrayList.add(new gdw(0));
        arrayList.add(new gdw(1));
        if (i == 0) {
            arrayList.add(new gdw(2));
        } else {
            arrayList.add(new gdw(3));
        }
        while (i2 < arrayList.size()) {
            gdw gdwVar = (gdw) arrayList.get(i2);
            i2++;
            e(gdwVar, i2);
        }
    }

    private void e(gdw gdwVar, int i) {
        if (gdwVar == null) {
            LogUtil.h("Suggestion_PlanTabDataModel", "viewConfig is null");
            return;
        }
        int a2 = gdwVar.a();
        gdw gdwVar2 = new gdw(i, a2);
        LogUtil.a("Suggestion_PlanTabDataModel", "resolveViewConfig viewId: ", Integer.valueOf(i), " viewType: ", Integer.valueOf(a2), "mPlanType", Integer.valueOf(this.f));
        if (a2 == 0) {
            a(this.f, gdwVar2);
            return;
        }
        if (a2 == 1) {
            b(this.f, gdwVar2);
            return;
        }
        if (a2 == 2) {
            b(gdwVar2);
        } else if (a2 == 3) {
            a(gdwVar2);
        } else {
            LogUtil.h("Suggestion_PlanTabDataModel", "unSupport viewType: ", Integer.valueOf(a2));
        }
    }

    public void a(int i) {
        if (!this.j.get(1)) {
            LogUtil.h("Suggestion_PlanTabDataModel", "refreshPlanStatisticsView: ", 1);
        } else {
            b(i, new gdw(1));
        }
    }

    public void e(int i) {
        if (!this.j.get(0)) {
            LogUtil.h("Suggestion_PlanTabDataModel", "refreshMyPlanView ", 0);
        } else {
            a(i, new gdw(0));
        }
    }

    public void b(int i, final gdw gdwVar) {
        final int i2 = i == 0 ? 2 : 3;
        this.h.b(new Consumer() { // from class: com.huawei.health.suggestion.ui.plan.viewmodel.PlanTabDataModel$$ExternalSyntheticLambda2
            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                PlanTabDataModel.this.b(i2, gdwVar, (PlanStatistics) obj);
            }
        }, i2);
    }

    /* synthetic */ void b(int i, gdw gdwVar, PlanStatistics planStatistics) {
        if (planStatistics != null && planStatistics.acquireTotalPlan() != 0) {
            e(i, true);
        } else {
            e(i, false);
        }
        gdwVar.b(planStatistics);
        e(1, gdwVar);
    }

    private void e(int i, boolean z) {
        if (i == 2) {
            this.b = z;
        } else {
            this.c = z;
        }
    }

    private void a(int i, final gdw gdwVar) {
        if (i == 0) {
            this.h.c(new Consumer() { // from class: com.huawei.health.suggestion.ui.plan.viewmodel.PlanTabDataModel$$ExternalSyntheticLambda0
                @Override // androidx.core.util.Consumer
                public final void accept(Object obj) {
                    PlanTabDataModel.this.a(gdwVar, (Plan) obj);
                }
            });
        } else {
            this.h.e(new Consumer() { // from class: com.huawei.health.suggestion.ui.plan.viewmodel.PlanTabDataModel$$ExternalSyntheticLambda1
                @Override // androidx.core.util.Consumer
                public final void accept(Object obj) {
                    PlanTabDataModel.this.b(gdwVar, (Plan) obj);
                }
            });
        }
        e(0, gdwVar);
    }

    /* synthetic */ void a(gdw gdwVar, Plan plan) {
        if (plan == null) {
            this.f3301a = false;
        } else {
            this.f3301a = true;
        }
        gdwVar.b(plan);
        e(0, gdwVar);
    }

    /* synthetic */ void b(gdw gdwVar, Plan plan) {
        if (plan == null) {
            this.e = false;
        } else {
            this.e = true;
        }
        gdwVar.b(plan);
        e(0, gdwVar);
    }

    private void a(final gdw gdwVar) {
        this.h.a(new Consumer() { // from class: com.huawei.health.suggestion.ui.plan.viewmodel.PlanTabDataModel$$ExternalSyntheticLambda3
            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                PlanTabDataModel.this.e(gdwVar, (List) obj);
            }
        });
    }

    /* synthetic */ void e(gdw gdwVar, List list) {
        gdwVar.b(list);
        e(3, gdwVar);
    }

    private void b(final gdw gdwVar) {
        this.h.d(new Consumer() { // from class: com.huawei.health.suggestion.ui.plan.viewmodel.PlanTabDataModel$$ExternalSyntheticLambda4
            @Override // androidx.core.util.Consumer
            public final void accept(Object obj) {
                PlanTabDataModel.this.c(gdwVar, (List) obj);
            }
        });
    }

    /* synthetic */ void c(gdw gdwVar, List list) {
        gdwVar.b(list);
        e(2, gdwVar);
    }

    public boolean d(int i) {
        if (i == 0) {
            return this.b;
        }
        return this.c;
    }

    public boolean b(int i) {
        if (i == 0) {
            return this.f3301a;
        }
        return this.e;
    }

    public void d(Observer<gdw> observer) {
        this.i.observeForever(observer);
    }

    public void c(LifecycleOwner lifecycleOwner) {
        this.i.removeObservers(lifecycleOwner);
    }

    private void e(int i, gdw gdwVar) {
        Message obtainMessage = this.d.obtainMessage(i);
        obtainMessage.obj = gdwVar;
        this.d.sendMessage(obtainMessage);
    }
}
