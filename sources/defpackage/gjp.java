package defpackage;

import android.os.Bundle;
import com.huawei.health.threeCircle.remind.handler.BaseWeekMonthDataHandler;
import com.huawei.health.threeCircle.remind.model.OnRemindHandle;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LogUtil;
import java.util.Map;

/* loaded from: classes4.dex */
public class gjp extends BaseWeekMonthDataHandler implements OnRemindHandle {
    @Override // com.huawei.health.threeCircle.remind.model.OnRemindHandle
    public void destroy() {
    }

    @Override // com.huawei.health.threeCircle.remind.model.OnRemindHandle
    public void registerAlarm() {
    }

    @Override // com.huawei.health.threeCircle.remind.model.OnRemindHandle
    public void onTrigger(Map<String, Integer> map, gjr gjrVar) {
        long currentTimeMillis = System.currentTimeMillis();
        if (gjz.e("R_TC_WkEncrgHdlr", currentTimeMillis) <= 0 || CommonUtil.cc() || gjrVar.d()) {
            acquireEncourageData(map, DateFormatUtil.b(currentTimeMillis), gjrVar);
            gjz.e("R_TC_WkEncrgHdlr", currentTimeMillis, 1);
        }
    }

    @Override // com.huawei.health.threeCircle.remind.model.OnRemindHandle
    public boolean isActiveTrigger() {
        return gjz.b(8, 12) && gjz.a();
    }

    @Override // com.huawei.health.threeCircle.remind.model.OnRemindHandle
    public void onTrigger(Bundle bundle, Map<String, Integer> map, gjr gjrVar) {
        if (bundle == null) {
            LogUtil.a("R_TC_WkEncrgHdlr", "onTrigger data = null");
        } else {
            acquireEncourageData(map, bundle.getInt("reportDate"), gjrVar);
        }
    }

    @Override // com.huawei.health.threeCircle.remind.handler.BaseWeekMonthDataHandler
    public String getQueryType() {
        return "weekEncourage";
    }

    @Override // com.huawei.health.threeCircle.remind.handler.BaseWeekMonthDataHandler
    public String getLogTag() {
        return "R_TC_WkEncrgHdlr";
    }
}
