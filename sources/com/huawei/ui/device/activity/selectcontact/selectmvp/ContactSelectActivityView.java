package com.huawei.ui.device.activity.selectcontact.selectmvp;

import android.content.Context;
import com.huawei.datatype.Contact;
import com.huawei.ui.main.stories.fitness.activity.bloodoxygen.base.CommonBaseView;
import defpackage.nxv;
import java.util.List;

/* loaded from: classes6.dex */
public interface ContactSelectActivityView extends CommonBaseView {
    void changeHealthToolBarState(boolean z);

    void dismissLoadingView();

    void finishSelect(List<Contact> list);

    Context getContext();

    void initHealthToolBar();

    void initListView();

    void initSideBarView();

    void initTitleBar();

    void setAdapter(List<nxv> list);

    void setCustomTitleBar(int i);

    void showUpperLimitMessage(int i);
}
