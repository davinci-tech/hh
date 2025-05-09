package com.huawei.health.suggestion.ui.polymericpage.model;

import androidx.lifecycle.MutableLiveData;
import com.huawei.basefitnessadvice.model.NavigationFilter;
import java.util.Map;

/* loaded from: classes4.dex */
public interface PolymericDataStrategy {
    void getNavigationData(MutableLiveData<NavigationFilter> mutableLiveData);

    void getPolymericData(MutableLiveData<Map<String, Object>> mutableLiveData, Object obj);

    void init(Map<String, Object> map);
}
