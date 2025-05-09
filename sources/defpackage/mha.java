package defpackage;

import com.huawei.hmf.tasks.OnCompleteListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hmf.tasks.Tasks;
import com.huawei.pluginachievement.report.iterface.PageAccessible;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes6.dex */
public class mha implements PageAccessible {
    @Override // com.huawei.pluginachievement.report.iterface.PageAccessible
    public List<String> getAnnualPageByYear() {
        return new ArrayList();
    }

    @Override // com.huawei.pluginachievement.report.iterface.PageAccessible
    public List<Integer> getAnnualPageArrayByType(String str) {
        return new ArrayList(2);
    }

    @Override // com.huawei.pluginachievement.report.iterface.PageAccessible
    public Task<Void> calculateAnnualData(final int i) {
        return Tasks.callInBackground(new Callable<Void>() { // from class: mha.1
            @Override // java.util.concurrent.Callable
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public Void call() {
                CommonUtil.a("Annual2019", "compute start");
                mha mhaVar = mha.this;
                Task a2 = mhaVar.a((Callable<Void>) mhaVar.e(i));
                CountDownLatch countDownLatch = new CountDownLatch(5);
                mha.this.c((Task<Void>) a2, countDownLatch);
                mha mhaVar2 = mha.this;
                mha.this.c((Task<Void>) mhaVar2.a((Callable<Void>) mhaVar2.c(i)), countDownLatch);
                mha mhaVar3 = mha.this;
                mha.this.c((Task<Void>) mhaVar3.a((Callable<Void>) mhaVar3.d(i)), countDownLatch);
                mha mhaVar4 = mha.this;
                mha.this.c((Task<Void>) mhaVar4.a((Callable<Void>) mhaVar4.a(i)), countDownLatch);
                mha mhaVar5 = mha.this;
                mha.this.c((Task<Void>) mhaVar5.a((Callable<Void>) mhaVar5.h(i)), countDownLatch);
                new mgf().a(i);
                mht.c(countDownLatch, 8000L);
                CommonUtil.a("Annual2019", "compute end!");
                return null;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Task<Void> task, final CountDownLatch countDownLatch) {
        task.addOnCompleteListener(new OnCompleteListener<Void>() { // from class: mha.3
            @Override // com.huawei.hmf.tasks.OnCompleteListener
            public void onComplete(Task<Void> task2) {
                countDownLatch.countDown();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Task<Void> a(Callable<Void> callable) {
        return Tasks.callInBackground(callable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Callable<Void> c(final int i) {
        return new Callable<Void>() { // from class: mha.4
            @Override // java.util.concurrent.Callable
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public Void call() {
                mgk mgkVar = new mgk();
                mgkVar.a(2);
                mgkVar.d(2015, i);
                mgkVar.a();
                return null;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Callable<Void> e(final int i) {
        return new Callable() { // from class: mhd
            @Override // java.util.concurrent.Callable
            public final Object call() {
                return mha.b(i);
            }
        };
    }

    static /* synthetic */ Void b(int i) throws Exception {
        new mgn().compute(i);
        new mgq().compute(i);
        new mfx().compute(i);
        new mfu().compute(i);
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Callable<Void> d(final int i) {
        return new Callable<Void>() { // from class: mha.5
            @Override // java.util.concurrent.Callable
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public Void call() {
                new mgb().e(i);
                return null;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Callable<Void> h(final int i) {
        return new Callable<Void>() { // from class: mha.2
            @Override // java.util.concurrent.Callable
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public Void call() {
                new mgl().a(i);
                return null;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Callable<Void> a(final int i) {
        return new Callable<Void>() { // from class: mha.8
            @Override // java.util.concurrent.Callable
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public Void call() {
                new mge().a(i);
                return null;
            }
        };
    }
}
