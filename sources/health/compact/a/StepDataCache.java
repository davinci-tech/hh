package health.compact.a;

import android.util.SparseArray;
import com.huawei.health.manager.util.TimeUtil;

/* loaded from: classes.dex */
public class StepDataCache {
    private SparseArray<OneMinuteStepData> b = new SparseArray<>();

    protected void a(int i) {
    }

    public void e(int i, long j, int i2, int i3, int i4) {
        int i5;
        com.huawei.hwlogsmodel.LogUtil.c("Step_DataCache", "setSteps()");
        int a2 = (int) TimeUtil.a(j);
        SparseArray<OneMinuteStepData> sparseArray = this.b;
        if (sparseArray == null) {
            com.huawei.hwlogsmodel.LogUtil.b("Step_DataCache", "setSteps mCache == null");
            return;
        }
        OneMinuteStepData oneMinuteStepData = sparseArray.get(a2);
        com.huawei.hwlogsmodel.LogUtil.c("Step_DataCache", "size ", Integer.valueOf(this.b.size()));
        if (oneMinuteStepData != null) {
            i5 = oneMinuteStepData.b();
            oneMinuteStepData.e(oneMinuteStepData.e(), a2, i2, i3, i4);
        } else {
            oneMinuteStepData = new OneMinuteStepData(i, a2, i2, i3, i4);
            i5 = 0;
        }
        c(oneMinuteStepData);
        this.b.put(a2, oneMinuteStepData);
        e(a2, i2, i5);
    }

    public void d(OneMinuteStepData oneMinuteStepData) {
        SparseArray<OneMinuteStepData> sparseArray;
        if (oneMinuteStepData == null || (sparseArray = this.b) == null) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_DataCache", "data == null or mCache == null");
            return;
        }
        OneMinuteStepData oneMinuteStepData2 = sparseArray.get((int) oneMinuteStepData.a());
        int b = oneMinuteStepData2 != null ? oneMinuteStepData2.b() : 0;
        c(oneMinuteStepData);
        this.b.put((int) oneMinuteStepData.a(), oneMinuteStepData);
        e((int) oneMinuteStepData.a(), oneMinuteStepData.b(), b);
    }

    public OneMinuteStepData d(long j) {
        return this.b.get((int) TimeUtil.a(j));
    }

    private void e(int i, int i2, int i3) {
        if (i2 < i3) {
            com.huawei.hwlogsmodel.LogUtil.h("Step_DataCache", "mcache insert step<oldStep");
        }
        a(i);
    }

    public SparseArray<OneMinuteStepData> akL_() {
        return this.b;
    }

    public void e() {
        com.huawei.hwlogsmodel.LogUtil.a("Step_DataCache", "clear()");
        SparseArray<OneMinuteStepData> sparseArray = this.b;
        if (sparseArray == null || sparseArray.size() <= 0) {
            return;
        }
        this.b.clear();
    }

    public int b() {
        SparseArray<OneMinuteStepData> sparseArray = this.b;
        if (sparseArray == null || sparseArray.size() <= 0) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.b.size(); i2++) {
            if (this.b.valueAt(i2).i()) {
                i += this.b.valueAt(i2).b();
            }
        }
        return i;
    }

    private void c(OneMinuteStepData oneMinuteStepData) {
        oneMinuteStepData.h();
    }
}
