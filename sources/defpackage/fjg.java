package defpackage;

import com.huawei.health.suggestion.fit.heartrate.HeartRateInterface;
import com.huawei.hihealth.ResultUtils;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class fjg implements HeartRateInterface {
    private boolean f;
    private long c = 0;
    private int e = 1;
    private int d = -1;

    /* renamed from: a, reason: collision with root package name */
    private List<HeartRateData> f12540a = new CopyOnWriteArrayList();
    private List<HeartRateData> b = new ArrayList();

    private boolean d(int i) {
        return i > 0 && i < 220;
    }

    @Override // com.huawei.health.suggestion.fit.heartrate.HeartRateInterface
    public void pushHeartRate(int i, long j) {
        this.c = j;
        if (d(i)) {
            this.d = i;
        } else {
            this.d = -1;
        }
    }

    @Override // com.huawei.health.suggestion.fit.heartrate.HeartRateInterface
    public void saveHeartRate() {
        HeartRateData heartRateData;
        boolean z = true;
        int i = this.e + 1;
        this.e = i;
        if (i == 5) {
            LogUtil.c("Suggestion_HeartRateInterfaceImpl", "mHeartRateTempTime:", Long.valueOf(this.c), " mHeartRateTemp:", Integer.valueOf(this.d));
            this.e = 0;
            long currentTimeMillis = System.currentTimeMillis() - this.c;
            if (currentTimeMillis < TimeUnit.SECONDS.toMillis(20L) || this.f) {
                z = false;
            } else {
                LogUtil.b("Suggestion_HeartRateInterfaceImpl", "diff time > 20");
            }
            this.f = false;
            if (this.d == -1) {
                LogUtil.b("Suggestion_HeartRateInterfaceImpl", "HEART_NONE");
            } else if (!z) {
                if (currentTimeMillis > TimeUnit.SECONDS.toMillis(5L) && currentTimeMillis < TimeUnit.SECONDS.toMillis(20L)) {
                    heartRateData = new HeartRateData(this.c + TimeUnit.SECONDS.toMillis((currentTimeMillis / TimeUnit.SECONDS.toMillis(5L)) * 5), this.d);
                } else {
                    heartRateData = new HeartRateData(this.c, this.d);
                }
                c(heartRateData);
                return;
            }
            b(new HeartRateData(System.currentTimeMillis(), -1));
        }
    }

    private void b(HeartRateData heartRateData) {
        this.b.add(heartRateData);
    }

    private void c(HeartRateData heartRateData) {
        this.f12540a.add(heartRateData);
    }

    @Override // com.huawei.health.suggestion.fit.heartrate.HeartRateInterface
    public int getAverageHeartRate() {
        int i = 0;
        if (koq.b(this.f12540a)) {
            return 0;
        }
        Iterator<HeartRateData> it = this.f12540a.iterator();
        while (it.hasNext()) {
            i += it.next().acquireHeartRate();
        }
        return i / this.f12540a.size();
    }

    @Override // com.huawei.health.suggestion.fit.heartrate.HeartRateInterface
    public List<HeartRateData> getHeartRateList() {
        return a(this.f12540a);
    }

    @Override // com.huawei.health.suggestion.fit.heartrate.HeartRateInterface
    public List<HeartRateData> getInvalidHeartRateList() {
        return a(this.b);
    }

    private List<HeartRateData> a(List<HeartRateData> list) {
        List list2 = (List) ResultUtils.a(list);
        ArrayList arrayList = new ArrayList();
        if (list2.isEmpty()) {
            LogUtil.h("Suggestion_HeartRateInterfaceImpl", "getListTemp : heartRateList is empty!");
            return arrayList;
        }
        arrayList.addAll(list2);
        return arrayList;
    }

    public void c(boolean z) {
        this.f = z;
    }
}
