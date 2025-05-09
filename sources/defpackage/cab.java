package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.basesport.helper.HeartRateConfigHelper;
import com.huawei.health.sport.model.CourseEnvParams;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartZoneConf;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes3.dex */
public class cab extends CourseEnvParams {
    HeartRateConfigHelper b;
    private AtomicInteger g = new AtomicInteger(0);

    public void e() {
        this.g.set(0);
        this.e = ggx.e(BaseApplication.e(), ggx.a());
        this.g.incrementAndGet();
        this.b = new HeartRateConfigHelper(10001, new HeartRateConfigHelper.OnConfigHelperListener() { // from class: bzz
            @Override // com.huawei.health.basesport.helper.HeartRateConfigHelper.OnConfigHelperListener
            public final void onInitComplete() {
                cab.this.a();
            }
        });
        ThreadPoolManager.d().execute(new Runnable() { // from class: cae
            @Override // java.lang.Runnable
            public final void run() {
                cab.this.b();
            }
        });
    }

    /* synthetic */ void a() {
        this.g.incrementAndGet();
        h();
    }

    /* synthetic */ void b() {
        this.j = fgc.b();
        this.g.incrementAndGet();
        h();
    }

    public boolean d() {
        return this.g.get() >= 3;
    }

    private void h() {
        if (!d() || this.c == null) {
            return;
        }
        this.c.onInitComplete();
    }

    @Override // com.huawei.health.sport.model.CourseEnvParams
    public HeartZoneConf c() {
        return this.b.a();
    }
}
