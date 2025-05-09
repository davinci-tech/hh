package com.huawei.ui.main.stories.privacy.template.contract;

import com.huawei.ui.main.stories.privacy.template.view.component.BaseComponent;
import com.huawei.ui.main.stories.template.BasePresenter;
import com.huawei.ui.main.stories.template.BaseView;

/* loaded from: classes7.dex */
public class PrivacyDetailFragmentContract {

    public interface PrivacyFragmentPresenter<V extends PrivacyFragmentView> extends BasePresenter<V> {
        void addComponents(BaseComponent baseComponent);
    }

    public interface PrivacyFragmentView extends BaseView {
    }
}
