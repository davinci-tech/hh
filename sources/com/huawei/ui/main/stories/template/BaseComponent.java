package com.huawei.ui.main.stories.template;

import android.content.Context;
import android.view.View;
import com.huawei.ui.main.stories.template.Constants;
import com.huawei.ui.main.stories.template.health.contract.DataDetailFragmentContract;
import java.util.List;

/* loaded from: classes7.dex */
public interface BaseComponent {
    View getView(Context context);

    void initComponent(List<ComponentParam> list);

    void onCreate();

    void onDayWeekYear(int i);

    void onDestory();

    void onPause();

    void onResume();

    void onStop();

    void refreshView(boolean z);

    void setDateStamp(long j);

    void setPageType(Constants.PageType pageType);

    void setPresenter(DataDetailFragmentContract.DetailFragmentPresenter detailFragmentPresenter);
}
