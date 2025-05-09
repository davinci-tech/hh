package com.huawei.ui.main.stories.privacy.template.contract;

import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.stories.template.BasePresenter;
import com.huawei.ui.main.stories.template.BaseView;

/* loaded from: classes7.dex */
public class PrivacyActivityContract {

    public interface PrivacyActivityPresenter<V extends PrivacyActivityView> extends BasePresenter<V> {
        void initPage(int i, String str, long j);
    }

    /* loaded from: classes.dex */
    public interface PrivacyActivityView extends BaseView {
        CustomTitleBar getCustomTitleBar();

        CustomTitleBar getSpinnerCustomTitleBar();

        String getTitleBarContent();

        void setTitle(String str);

        void showAllDataView();

        void showDoubleGroupDataView();

        void showSegmentDataView();
    }
}
