package defpackage;

import com.huawei.health.algorithm.api.BreathTrainApi;
import com.huawei.health.breathe.bean.BreatheBean;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Services;

/* loaded from: classes3.dex */
public class cbl {
    private static BreathTrainApi e() {
        return (BreathTrainApi) Services.a("BreathTrainService", BreathTrainApi.class);
    }

    public static void a(BreatheBean breatheBean) {
        BreathTrainApi e = e();
        if (e == null) {
            LogUtil.h("BreatheUtils", "insert breathTrainApi is null");
        } else {
            e.insert(breatheBean);
        }
    }
}
