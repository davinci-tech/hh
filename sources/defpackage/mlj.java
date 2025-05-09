package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.RecentMonthRecord;
import com.huawei.pluginachievement.manager.model.UserAchieveWrapper;

/* loaded from: classes6.dex */
public class mlj {
    public static boolean b(UserAchieveWrapper userAchieveWrapper) {
        RecentMonthRecord acquireRecentMonthRecord;
        if (userAchieveWrapper != null) {
            return userAchieveWrapper.getContentType() == 3 && (acquireRecentMonthRecord = userAchieveWrapper.acquireRecentMonthRecord()) != null && acquireRecentMonthRecord.acquireMinReportNo() == 0;
        }
        LogUtil.h("PLGACHIEVE_LevelUtil", "isHistoryMonthReport: wrapper is null");
        return false;
    }
}
