package defpackage;

import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.AchieveUserLevelInfo;
import com.huawei.pluginachievement.report.constant.EnumAnnualType;
import com.huawei.pluginachievement.report.iterface.BaseCalculator;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: classes6.dex */
public class mfu extends BaseCalculator {
    @Override // com.huawei.pluginachievement.report.iterface.BaseCalculator
    public void compute(int i) {
        c(i);
        e(i);
    }

    public int e(int i) {
        mcz d = meh.c(BaseApplication.getContext()).d(14, new HashMap(2));
        if (!(d instanceof AchieveUserLevelInfo)) {
            return 1;
        }
        AchieveUserLevelInfo achieveUserLevelInfo = (AchieveUserLevelInfo) d;
        insertData(i, EnumAnnualType.REPORT_SUMARY.value(), 6002, String.valueOf(achieveUserLevelInfo.acquireUserLevel()));
        return achieveUserLevelInfo.acquireUserLevel();
    }

    private ArrayList<String> d(int i) {
        return mgv.b(i, meh.c(BaseApplication.getContext()).b(8, new HashMap(2)));
    }

    public void c(int i) {
        ArrayList<String> d = d(i);
        if (koq.b(d)) {
            LogUtil.a("PLGACHIEVE_AchievementCalculator", "gainList null");
            return;
        }
        int size = d.size();
        insertData(i, EnumAnnualType.REPORT_SUMARY.value(), 6001, String.valueOf(size));
        c(i, size, d);
    }

    private void c(int i, int i2, ArrayList<String> arrayList) {
        StringBuilder sb = new StringBuilder(i2);
        for (int i3 = 0; i3 < i2; i3++) {
            sb.append(arrayList.get(i3));
            if (i3 != i2 - 1) {
                sb.append(",");
            }
        }
        insertData(i, EnumAnnualType.REPORT_REWARD.value(), 5001, sb.toString());
    }
}
