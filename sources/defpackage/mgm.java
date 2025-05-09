package defpackage;

import android.content.Context;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.PluginAchieveAdapter;
import com.huawei.pluginachievement.impl.AchieveCallback;
import com.huawei.pluginachievement.report.constant.EnumAnnualType;
import com.huawei.pluginachievement.report.iterface.BaseCalculator;
import health.compact.a.UnitUtil;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes6.dex */
public class mgm extends BaseCalculator {
    private PluginAchieveAdapter d = getPluginAchieveAdapter();
    private Context e = BaseApplication.getContext();

    @Override // com.huawei.pluginachievement.report.iterface.BaseCalculator
    public void compute(int i) {
        b(i);
    }

    public void b(final int i) {
        if (this.d == null) {
            LogUtil.a("PLGACHIEVE_OperationActivityCalculator", "getAnnualActivityData() achieveAdapter is null,return");
            return;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        this.d.getAnnualActivityReport(this.e, i, new AchieveCallback() { // from class: mgm.1
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public void onResponse(int i2, Object obj) {
                countDownLatch.countDown();
                List<kvm> d = mhp.d(obj);
                if (koq.b(d)) {
                    LogUtil.h("PLGACHIEVE_OperationActivityCalculator", "getAnnualActivityData allActivitySimpleList is empty");
                } else {
                    mgm.this.b(i, mhp.c(d, i));
                }
            }
        });
        mht.c(countDownLatch, 8000L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, List<kvm> list) {
        int e;
        if (koq.b(list)) {
            LogUtil.h("PLGACHIEVE_OperationActivityCalculator", "generateActivityReport currentYearActivityList is empty");
            return;
        }
        int size = list.size();
        e(i, 10001, String.valueOf(size));
        e(i, 10002, UnitUtil.e((size <= 0 || mhp.d(list) > size) ? 0.0f : (r1 * 1.0f) / size, 1, 2));
        if (i < 2022) {
            e = mhp.a(list);
        } else {
            e = mhp.e(list);
        }
        e(i, 10003, String.valueOf(e));
    }

    private void e(int i, int i2, String str) {
        insertData(i, EnumAnnualType.REPORT_ACTIVITY.value(), i2, str);
    }
}
