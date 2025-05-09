package defpackage;

import com.huawei.android.airsharing.api.IEventListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.SingleDayRecord;
import com.huawei.pluginachievement.report.constant.EnumAnnualType;
import com.huawei.pluginachievement.report.iterface.BaseCalculator;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class mfx extends BaseCalculator {
    @Override // com.huawei.pluginachievement.report.iterface.BaseCalculator
    public void compute(int i) {
        d(i);
    }

    public void d(int i) {
        SingleDayRecord c = c();
        if (c == null) {
            LogUtil.h("PLGACHIEVE_BestRecordCalculator", "singleDayRecord is null");
            return;
        }
        a(i, c);
        e(i, c);
        d(i, c);
    }

    private SingleDayRecord c() {
        mcz d = meh.c(BaseApplication.getContext()).d(2, new HashMap(2));
        if (d instanceof SingleDayRecord) {
            return (SingleDayRecord) d;
        }
        return null;
    }

    private void a(int i, SingleDayRecord singleDayRecord) {
        mke e = mgv.e(singleDayRecord.acquireBestStepDay(), i);
        if (e != null) {
            insertData(i, EnumAnnualType.REPORT_STEP.value(), 3003, String.valueOf(e.a()));
            insertData(i, EnumAnnualType.REPORT_STEP.value(), IEventListener.EVENT_ID_DEVICE_CONN_FAIL, String.valueOf(e.d()));
        }
    }

    private void e(int i, SingleDayRecord singleDayRecord) {
        mka a2 = mgv.a(singleDayRecord.acquireBestRunDistance(), i);
        if (a2 != null) {
            insertData(i, EnumAnnualType.REPORT_RUN.value(), 2003, String.valueOf(a2.b()));
            insertData(i, EnumAnnualType.REPORT_RUN.value(), 2004, String.valueOf(a2.e()));
        }
    }

    private void d(int i, SingleDayRecord singleDayRecord) {
        mka a2 = mgv.a(singleDayRecord.acquireBestCycleDistance(), i);
        if (a2 != null) {
            insertData(i, EnumAnnualType.REPORT_CYCLE.value(), 1003, String.valueOf(a2.e()));
            insertData(i, EnumAnnualType.REPORT_CYCLE.value(), 1004, String.valueOf(a2.e()));
        }
    }
}
