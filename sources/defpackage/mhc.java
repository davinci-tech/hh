package defpackage;

import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.Tasks;
import com.huawei.hms.support.api.entity.auth.AuthCode;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.report.constant.EnumAnnualType;
import com.huawei.pluginachievement.report.iterface.PageAccessible;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes6.dex */
public class mhc implements PageAccessible {
    @Override // com.huawei.pluginachievement.report.iterface.PageAccessible
    public List<String> getAnnualPageByYear() {
        return mgt.c();
    }

    @Override // com.huawei.pluginachievement.report.iterface.PageAccessible
    public List<Integer> getAnnualPageArrayByType(String str) {
        List<mgr> e = mgt.e(str);
        if (!koq.b(e)) {
            ArrayList arrayList = new ArrayList(e.size());
            for (int i = 0; i < e.size(); i++) {
                arrayList.add(Integer.valueOf(e.get(i).c()));
            }
            return arrayList;
        }
        return new ArrayList(2);
    }

    @Override // com.huawei.pluginachievement.report.iterface.PageAccessible
    public Task<Void> calculateAnnualData(final int i) {
        return Tasks.callInBackground(new Callable<Void>() { // from class: mhc.1
            @Override // java.util.concurrent.Callable
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public Void call() {
                CommonUtil.a("Annual2020", "compute start");
                CountDownLatch countDownLatch = new CountDownLatch(10);
                mhc mhcVar = mhc.this;
                mhcVar.a((Task<Void>) mhcVar.e((Callable<Void>) mhcVar.f(i)), countDownLatch);
                mhc mhcVar2 = mhc.this;
                mhcVar2.a((Task<Void>) mhcVar2.e((Callable<Void>) mhcVar2.l(i)), countDownLatch);
                mhc mhcVar3 = mhc.this;
                mhcVar3.a((Task<Void>) mhcVar3.e((Callable<Void>) mhcVar3.j(i)), countDownLatch);
                mhc mhcVar4 = mhc.this;
                mhcVar4.a((Task<Void>) mhcVar4.e((Callable<Void>) mhcVar4.g(i)), countDownLatch);
                mhc mhcVar5 = mhc.this;
                mhcVar5.a((Task<Void>) mhcVar5.e((Callable<Void>) mhcVar5.m(i)), countDownLatch);
                mhc mhcVar6 = mhc.this;
                mhcVar6.a((Task<Void>) mhcVar6.e((Callable<Void>) mhcVar6.i(i)), countDownLatch);
                mhc mhcVar7 = mhc.this;
                mhcVar7.a((Task<Void>) mhcVar7.e((Callable<Void>) mhcVar7.h(i)), countDownLatch);
                mhc mhcVar8 = mhc.this;
                mhcVar8.a((Task<Void>) mhcVar8.e((Callable<Void>) mhcVar8.b(i)), countDownLatch);
                mhc mhcVar9 = mhc.this;
                mhcVar9.a((Task<Void>) mhcVar9.e((Callable<Void>) mhcVar9.o(i)), countDownLatch);
                mhc mhcVar10 = mhc.this;
                mhcVar10.a((Task<Void>) mhcVar10.e((Callable<Void>) mhcVar10.c(i)), countDownLatch);
                mht.c(countDownLatch, 8000L);
                CommonUtil.a("Annual2020", "compute end");
                return null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Task<Void> task, final CountDownLatch countDownLatch) {
        task.addOnCompleteListener(new OnCompleteListener<Void>() { // from class: mhc.4
            @Override // com.huawei.hmf.tasks.OnCompleteListener
            public void onComplete(Task<Void> task2) {
                countDownLatch.countDown();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Task<Void> e(Callable<Void> callable) {
        return Tasks.callInBackground(callable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Callable<Void> m(final int i) {
        return new Callable<Void>() { // from class: mhc.2
            @Override // java.util.concurrent.Callable
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public Void call() {
                new mgl().compute(i);
                return null;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Callable<Void> g(final int i) {
        return new Callable<Void>() { // from class: mhc.3
            @Override // java.util.concurrent.Callable
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public Void call() {
                new mge().c(i);
                new mga().compute(i);
                return null;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Callable<Void> j(final int i) {
        return new Callable<Void>() { // from class: mhc.5
            @Override // java.util.concurrent.Callable
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public Void call() {
                new mgb().compute(i);
                return null;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Callable<Void> l(final int i) {
        return new Callable<Void>() { // from class: mhc.7
            @Override // java.util.concurrent.Callable
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public Void call() {
                mgk mgkVar = new mgk();
                mgkVar.a(i >= 2022 ? 3 : 2);
                int i2 = i;
                mgkVar.d(i2 - 1, i2);
                mgkVar.a();
                return null;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Callable<Void> f(final int i) {
        return new Callable<Void>() { // from class: mhc.9
            @Override // java.util.concurrent.Callable
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public Void call() {
                new mgn().compute(i);
                new mgq().e(i);
                new mfy().compute(i);
                new mfw().compute(i);
                return null;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Callable<Void> i(final int i) {
        return new Callable<Void>() { // from class: mhc.8
            @Override // java.util.concurrent.Callable
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public Void call() {
                new mfu().compute(i);
                new mgm().compute(i);
                return null;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Callable<Void> h(final int i) {
        return new Callable() { // from class: mhi
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return mhc.a(i);
            }
        };
    }

    static /* synthetic */ Void a(int i) throws Exception {
        LogUtil.a("Annual2020", "makeInitialCallable start");
        mgf mgfVar = new mgf();
        mgfVar.compute(i);
        mgfVar.insertData(i, EnumAnnualType.REPORT_SUMARY.value(), AuthCode.StatusCode.PERMISSION_EXPIRED, mgfVar.getStarAcquired());
        mgfVar.clearStarData();
        LogUtil.a("Annual2020", "makeInitialCallable end");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Callable<Void> b(final int i) {
        return new Callable() { // from class: mhe
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return mhc.e(i);
            }
        };
    }

    static /* synthetic */ Void e(int i) throws Exception {
        if (i < 2022) {
            return null;
        }
        LogUtil.a("Annual2020", "make2022DataTaskCallable start");
        new mgd().compute(i);
        new mgc().compute(i);
        LogUtil.a("Annual2020", "make2022DataTaskCallable end");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Callable<Void> o(final int i) {
        return new Callable<Void>() { // from class: mhc.10
            @Override // java.util.concurrent.Callable
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public Void call() {
                if (i < 2023) {
                    return null;
                }
                new mgg().compute(i);
                return null;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Callable<Void> c(final int i) {
        return new Callable() { // from class: mhg
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return mhc.d(i);
            }
        };
    }

    static /* synthetic */ Void d(int i) throws Exception {
        if (i <= 2023) {
            return null;
        }
        LogUtil.a("Annual2020", "make2024DataTaskCallable start");
        new mfv().compute(i);
        new mft().compute(i);
        new mgj().compute(i);
        LogUtil.a("Annual2020", "make2024DataTaskCallable end");
        return null;
    }
}
