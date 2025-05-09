package defpackage;

import com.huawei.hwcloudmodel.model.unite.HealthDetail;
import java.io.Serializable;
import java.util.Comparator;

/* loaded from: classes7.dex */
public class iuu implements Comparator<HealthDetail>, Serializable {
    private static final long serialVersionUID = 537036553028345437L;

    public static iuu e() {
        return a.b;
    }

    static class a {
        private static final iuu b = new iuu();
    }

    @Override // java.util.Comparator
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public int compare(HealthDetail healthDetail, HealthDetail healthDetail2) {
        if (healthDetail == null || healthDetail2 == null) {
            return 0;
        }
        int d = d(Long.valueOf(healthDetail.getVersion()), Long.valueOf(healthDetail2.getVersion()));
        return d != 0 ? d : d(healthDetail.getStartTime(), healthDetail2.getStartTime());
    }

    private int d(Long l, Long l2) {
        long longValue = l == null ? 0L : l.longValue();
        long longValue2 = l2 != null ? l2.longValue() : 0L;
        if (longValue > longValue2) {
            return 1;
        }
        return longValue < longValue2 ? -1 : 0;
    }
}
