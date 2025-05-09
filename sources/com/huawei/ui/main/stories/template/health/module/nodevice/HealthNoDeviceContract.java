package com.huawei.ui.main.stories.template.health.module.nodevice;

import com.huawei.ui.main.stories.template.BasePresenter;
import com.huawei.ui.main.stories.template.BaseView;
import defpackage.cdy;
import java.util.List;

/* loaded from: classes7.dex */
public class HealthNoDeviceContract {

    public interface Presenter extends BasePresenter {
        void requestActivityInfo(int i);
    }

    public interface View extends BaseView {
        void onResponsePageModule(List<cdy> list);
    }
}
