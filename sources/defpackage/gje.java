package defpackage;

import android.os.Bundle;
import com.huawei.health.threeCircle.remind.handler.BaseWeekMonthDataHandler;
import com.huawei.health.threeCircle.remind.model.OnRemindHandle;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LogUtil;
import java.util.Map;
import java.util.TimeZone;

/* loaded from: classes4.dex */
public class gje extends BaseWeekMonthDataHandler implements OnRemindHandle {
    @Override // com.huawei.health.threeCircle.remind.model.OnRemindHandle
    public void destroy() {
    }

    @Override // com.huawei.health.threeCircle.remind.model.OnRemindHandle
    public void registerAlarm() {
    }

    @Override // com.huawei.health.threeCircle.remind.model.OnRemindHandle
    public void onTrigger(Map<String, Integer> map, gjr gjrVar) {
        long currentTimeMillis = System.currentTimeMillis();
        if (gjz.e("R_TC_MonthEncourageHandler", currentTimeMillis) <= 0 || CommonUtil.cc() || gjrVar.d()) {
            acquireEncourageData(map, DateFormatUtil.c(currentTimeMillis, TimeZone.getDefault()), gjrVar);
            gjz.e("R_TC_MonthEncourageHandler", currentTimeMillis, 1);
        }
    }

    @Override // com.huawei.health.threeCircle.remind.model.OnRemindHandle
    public boolean isActiveTrigger() {
        return gjz.b(8, 12) && gjz.b();
    }

    @Override // com.huawei.health.threeCircle.remind.model.OnRemindHandle
    public void onTrigger(Bundle bundle, Map<String, Integer> map, gjr gjrVar) {
        if (bundle == null) {
            LogUtil.a("R_TC_MonthEncourageHandler", "onTrigger data = null");
        } else {
            acquireEncourageData(map, bundle.getInt("reportDate"), gjrVar);
        }
    }

    @Override // com.huawei.health.threeCircle.remind.handler.BaseWeekMonthDataHandler
    public String getQueryType() {
        return "monthEncourage";
    }

    @Override // com.huawei.health.threeCircle.remind.handler.BaseWeekMonthDataHandler
    public String getLogTag() {
        return "R_TC_MonthEncourageHandler";
    }
}
