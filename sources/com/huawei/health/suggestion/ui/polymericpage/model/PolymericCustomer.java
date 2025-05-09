package com.huawei.health.suggestion.ui.polymericpage.model;

import android.content.Context;
import android.content.Intent;
import com.huawei.basefitnessadvice.model.FilterCondition;
import com.huawei.basefitnessadvice.model.Navigation;
import com.huawei.health.suggestion.ui.polymericpage.view.PolymericDataFragment;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public interface PolymericCustomer {
    Map<String, Object> acquireFragmentParam();

    PolymericDataFragment createPolymericDataFragment();

    void getPolymericIntentData(Intent intent);

    String getTitleText(Context context);

    Navigation initActiveNavigation(List<Navigation> list);

    List<FilterCondition> initFilterConditions(Navigation navigation);

    boolean isSupportFilter();
}
