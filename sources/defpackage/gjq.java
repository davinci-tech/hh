package defpackage;

import android.os.Bundle;
import com.huawei.health.threeCircle.remind.handler.BaseWeekMonthDataHandler;
import com.huawei.health.threeCircle.remind.model.OnRemindHandle;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import health.compact.a.LogUtil;
import java.util.Map;
import java.util.TimeZone;

/* loaded from: classes4.dex */
public class gjq extends BaseWeekMonthDataHandler implements OnRemindHandle {
    @Override // com.huawei.health.threeCircle.remind.model.OnRemindHandle
    public void destroy() {
    }

    @Override // com.huawei.health.threeCircle.remind.model.OnRemindHandle
    public boolean isActiveTrigger() {
        return false;
    }

    @Override // com.huawei.health.threeCircle.remind.model.OnRemindHandle
    public void registerAlarm() {
    }

    @Override // com.huawei.health.threeCircle.remind.model.OnRemindHandle
    public void onTrigger(Map<String, Integer> map, gjr gjrVar) {
        acquireSummaryData(map, gjrVar, DateFormatUtil.c(jdl.c(System.currentTimeMillis(), 2, -1), TimeZone.getDefault()));
    }

    @Override // com.huawei.health.threeCircle.remind.model.OnRemindHandle
    public void onTrigger(Bundle bundle, Map<String, Integer> map, gjr gjrVar) {
        if (bundle == null) {
            LogUtil.a("R_TC_WeekSummaryHandler", "onTrigger data = null");
        } else {
            acquireSummaryData(map, gjrVar, bundle.getInt("reportDate"));
        }
    }

    @Override // com.huawei.health.threeCircle.remind.handler.BaseWeekMonthDataHandler
    public String getQueryType() {
        return "weekReport";
    }

    @Override // com.huawei.health.threeCircle.remind.handler.BaseWeekMonthDataHandler
    public String getLogTag() {
        return "R_TC_WeekSummaryHandler";
    }
}
