package com.huawei.featureuserprofile.award.presenter;

import com.huawei.featureuserprofile.award.BasePresenter;
import com.huawei.featureuserprofile.award.BaseView;

/* loaded from: classes8.dex */
public interface MyAwardContract {

    public interface Presenter extends BasePresenter {
        void requestAwardData();
    }

    public interface View extends BaseView<Presenter> {
        void hideLoading();

        void onRequestDataFailed(String str);

        void onRequestDataSuccess(String str);

        void showLoading();
    }
}
