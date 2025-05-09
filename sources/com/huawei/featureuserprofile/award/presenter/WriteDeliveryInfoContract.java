package com.huawei.featureuserprofile.award.presenter;

import com.huawei.featureuserprofile.award.BasePresenter;
import com.huawei.featureuserprofile.award.BaseView;
import com.huawei.featureuserprofile.award.model.SaveInfoItem;

/* loaded from: classes7.dex */
public interface WriteDeliveryInfoContract {

    public interface Presenter extends BasePresenter {
        void saveInfo(int i, SaveInfoItem saveInfoItem, String str, int i2);
    }

    /* loaded from: classes.dex */
    public interface View extends BaseView<Presenter> {
        void hideLoading();

        void onModifyDataFailed(String str);

        void onModifyDataSuccess(String str);

        void showLoading();
    }
}
