package com.huawei.ui.main.stories.template.health.contract;

import com.huawei.ui.commonui.linechart.common.DateType;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import com.huawei.ui.main.stories.template.BasePresenter;
import com.huawei.ui.main.stories.template.BaseView;

/* loaded from: classes7.dex */
public class DataDetailActivityContract {

    public interface DetailActivityPresenter<V extends DetailActivityView> extends BasePresenter<V> {
        void initPage(String str, long j, String str2);

        void notifyChartDateStatus(DateType dateType, boolean z, boolean z2);

        void notifyViewPagerChange(int i);

        void setChartStartTimeAndEndTime(long j, long j2);
    }

    /* loaded from: classes2.dex */
    public interface DetailActivityView extends BaseView {
        CustomTitleBar getCustomTitleBar();

        HealthToolBar getHealthToolBar();

        void setTitle(String str);

        void showDataView(long j);

        void showNoDataView(boolean z);
    }
}
