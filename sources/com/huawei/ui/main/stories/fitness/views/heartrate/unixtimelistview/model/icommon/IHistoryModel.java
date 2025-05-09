package com.huawei.ui.main.stories.fitness.views.heartrate.unixtimelistview.model.icommon;

import com.huawei.ui.main.stories.fitness.views.heartrate.unixtimelistview.common.UserView;
import java.util.Comparator;

/* loaded from: classes6.dex */
public interface IHistoryModel {
    public static final Comparator sHistoryModelComparator = new Comparator<IHistoryModel>() { // from class: com.huawei.ui.main.stories.fitness.views.heartrate.unixtimelistview.model.icommon.IHistoryModel.1
        @Override // java.util.Comparator
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public int compare(IHistoryModel iHistoryModel, IHistoryModel iHistoryModel2) {
            if (iHistoryModel.queryUserView() == iHistoryModel2.queryUserView()) {
                return iHistoryModel.queryModelStartTime() < iHistoryModel2.queryModelStartTime() ? 1 : -1;
            }
            throw new RuntimeException("userView1 diff userView2");
        }
    };

    long generateViewFlag(UserView userView);

    boolean hasSameDirectParent(IHistoryModel iHistoryModel);

    long queryModelEndTime();

    long queryModelStartTime();

    UserView queryUserView();
}
