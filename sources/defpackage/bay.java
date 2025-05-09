package defpackage;

import android.util.SparseIntArray;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.Serializable;
import java.util.Comparator;

/* loaded from: classes3.dex */
public class bay implements Comparator<HealthLifeBean>, Serializable {
    private final SparseIntArray d = azi.lS_();

    @Override // java.util.Comparator
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public int compare(HealthLifeBean healthLifeBean, HealthLifeBean healthLifeBean2) {
        int i;
        SparseIntArray sparseIntArray = this.d;
        int i2 = 0;
        if (sparseIntArray == null) {
            LogUtil.h("HealthLife_HealthModelComparator", "compare mSparseIntArray is null");
            return 0;
        }
        if (healthLifeBean == null) {
            LogUtil.h("HealthLife_HealthModelComparator", "compare beforeBean is null");
            i = 0;
        } else {
            i = sparseIntArray.get(healthLifeBean.getId());
        }
        if (healthLifeBean2 == null) {
            LogUtil.h("HealthLife_HealthModelComparator", "compare afterBean is null");
        } else {
            i2 = this.d.get(healthLifeBean2.getId());
        }
        return i - i2;
    }
}
