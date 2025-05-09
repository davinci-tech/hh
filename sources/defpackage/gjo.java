package defpackage;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.health.threeCircle.remind.model.OnRemindHandle;
import com.huawei.health.threeCircle.remind.model.ThreeCircleRemindData;
import com.huawei.health.threeCircle.remind.receiver.SportRemindReceiver;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.threecircle.api.ThreeCircleApi;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.SharedPerferenceUtils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class gjo implements OnRemindHandle {
    private int a(int i, int i2, int i3, int i4) {
        int i5 = i > 0 ? 1 : 0;
        if (i2 > 0) {
            i5++;
        }
        return i3 > 0 ? i5 + 1 : i5;
    }

    @Override // com.huawei.health.threeCircle.remind.model.OnRemindHandle
    public void onTrigger(final Map<String, Integer> map, final gjr gjrVar) {
        if (gjrVar == null) {
            return;
        }
        if (gjz.e("TC_YestSummHdlr", System.currentTimeMillis()) <= 0 || CommonUtil.cc() || gjrVar.d()) {
            nix.c().c(jdl.v(System.currentTimeMillis()), jdl.g(System.currentTimeMillis()), new ResponseCallback() { // from class: gjt
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    gjo.this.b(gjrVar, map, i, (List) obj);
                }
            });
        }
    }

    /* synthetic */ void b(gjr gjrVar, Map map, int i, List list) {
        if (koq.b(list)) {
            ReleaseLogUtil.d("R_TC_YestSummHdlr", "can not get yesterday three circle data.");
            gjrVar.onResponse(0, c(0, map));
            return;
        }
        HiHealthData hiHealthData = (HiHealthData) list.get(0);
        if (hiHealthData == null) {
            ReleaseLogUtil.d("R_TC_YestSummHdlr", "get yesterday three circle data is null.");
            gjrVar.onResponse(0, c(0, map));
            return;
        }
        int i2 = hiHealthData.getInt("durationGoalState");
        int i3 = hiHealthData.getInt("activeGoalState");
        int i4 = hiHealthData.getInt("calorieGoalState");
        int i5 = hiHealthData.getInt("stepGoalState");
        ReleaseLogUtil.d("R_TC_YestSummHdlr", " intensityState:", Integer.valueOf(i2), " activeState:", Integer.valueOf(i3), " caloriesState:", Integer.valueOf(i4), " stepState:", Integer.valueOf(i5));
        gjrVar.onResponse(0, c(a(i2, i3, i4, i5), map));
    }

    @Override // com.huawei.health.threeCircle.remind.model.OnRemindHandle
    public void onTrigger(Bundle bundle, Map<String, Integer> map, gjr gjrVar) {
        LogUtil.a("TC_YestSummHdlr", "onTrigger data.");
    }

    private ThreeCircleRemindData c(int i, Map<String, Integer> map) {
        Integer num;
        ThreeCircleRemindData.c cVar = new ThreeCircleRemindData.c();
        cVar.b("YesterdaySummary");
        cVar.e((map == null || (num = map.get("YesterdaySummary")) == null) ? 0 : num.intValue());
        String str = i == 0 ? "ZeroGoal" : i == 1 ? "OneGoal" : i == 2 ? "TwoGoals" : "All";
        ThreeCircleApi threeCircleApi = (ThreeCircleApi) Services.c("DailyActivity", ThreeCircleApi.class);
        cVar.c(str);
        cVar.a(threeCircleApi.getPromptMessage(threeCircleApi.queryRules("YesterdaySummary", str), new HashMap()));
        gjz.e("TC_YestSummHdlr", System.currentTimeMillis(), 1);
        return cVar.d();
    }

    @Override // com.huawei.health.threeCircle.remind.model.OnRemindHandle
    public void registerAlarm() {
        long timeInMillis = gjz.b(1, 8, 12).getTimeInMillis();
        Intent intent = new Intent(BaseApplication.e(), (Class<?>) SportRemindReceiver.class);
        intent.putExtra("remindType", new String[]{"YesterdaySummary", "ActiveWeek", "PerfectWeek", "PerfectMonth"});
        try {
            if (Build.VERSION.SDK_INT > 30 && CommonUtil.bh()) {
                sqa.ekz_(101011, intent, 335544320, 0, timeInMillis);
            } else {
                sqa.ekx_(101011, intent, 335544320, 0, timeInMillis);
            }
            SharedPerferenceUtils.i(BaseApplication.e(), DateFormatUtil.b(System.currentTimeMillis()));
        } catch (IllegalStateException e) {
            SharedPerferenceUtils.i(BaseApplication.e(), -1);
            ReleaseLogUtil.c("TC_YestSummHdlr", "registerAlarm：", ExceptionUtils.d(e));
        }
    }

    @Override // com.huawei.health.threeCircle.remind.model.OnRemindHandle
    public boolean isActiveTrigger() {
        return gjz.b(8, 12);
    }

    @Override // com.huawei.health.threeCircle.remind.model.OnRemindHandle
    public void destroy() {
        LogUtil.a("TC_YestSummHdlr", "destroy");
    }
}
