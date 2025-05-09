package com.huawei.ui.main.stories.fitness.views.heartrate.unixtimelistview.model.base;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.fitness.views.heartrate.unixtimelistview.common.UserView;
import com.huawei.ui.main.stories.fitness.views.heartrate.unixtimelistview.model.icommon.IHistoryModel;
import java.util.Calendar;

/* loaded from: classes6.dex */
public abstract class BaseHistoryModel implements IHistoryModel {
    private static final String TAG = "BaseHistoryModel";

    @Override // com.huawei.ui.main.stories.fitness.views.heartrate.unixtimelistview.model.icommon.IHistoryModel
    public abstract long queryModelEndTime();

    @Override // com.huawei.ui.main.stories.fitness.views.heartrate.unixtimelistview.model.icommon.IHistoryModel
    public abstract long queryModelStartTime();

    @Override // com.huawei.ui.main.stories.fitness.views.heartrate.unixtimelistview.model.icommon.IHistoryModel
    public boolean hasSameDirectParent(IHistoryModel iHistoryModel) {
        UserView queryUserView = iHistoryModel.queryUserView();
        if (queryUserView != queryUserView()) {
            return false;
        }
        UserView upper = queryUserView.upper();
        return iHistoryModel.generateViewFlag(upper) == generateViewFlag(upper);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.heartrate.unixtimelistview.model.icommon.IHistoryModel
    public long generateViewFlag(UserView userView) {
        long queryModelStartTime = queryModelStartTime();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(queryModelStartTime);
        calendar.set(14, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(11, 0);
        calendar.set(5, 1);
        if (userView == UserView.MONTH_DATAS) {
            return calendar.getTimeInMillis();
        }
        calendar.set(2, 0);
        if (userView == UserView.YEAR_DATAS) {
            return calendar.getTimeInMillis();
        }
        calendar.set(1, 0);
        if (userView == UserView.ALL_DATAS) {
            return calendar.getTimeInMillis();
        }
        LogUtil.h(TAG, "generateViewFlag not compat");
        return -1L;
    }

    public int hashCode() {
        return super.hashCode();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof IHistoryModel)) {
            return true;
        }
        IHistoryModel iHistoryModel = (IHistoryModel) obj;
        return queryModelStartTime() == iHistoryModel.queryModelStartTime() && queryModelEndTime() == iHistoryModel.queryModelEndTime();
    }
}
