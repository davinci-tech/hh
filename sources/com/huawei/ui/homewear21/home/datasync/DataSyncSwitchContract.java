package com.huawei.ui.homewear21.home.datasync;

import defpackage.pef;
import java.util.List;

/* loaded from: classes6.dex */
public interface DataSyncSwitchContract {

    public interface Presenter extends BasePresenter {
        void onDestroy();

        void startQueryData();
    }

    public interface View extends BaseView<Presenter> {
        void refreshList(List<pef> list);
    }
}
