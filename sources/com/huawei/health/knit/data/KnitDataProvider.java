package com.huawei.health.knit.data;

import android.content.Context;
import android.os.Bundle;
import com.huawei.health.knit.section.model.SectionBean;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes3.dex */
public interface KnitDataProvider<T> extends IKnitLifeCycle {
    List<KnitDataProvider<T>> getDataProviderList();

    Bundle getExtra();

    int getGroupId();

    boolean isActive(Context context);

    void loadData(Context context, SectionBean sectionBean);

    void loadDataLite(Context context);

    void loadDefaultData(SectionBean sectionBean);

    void parseParams(Context context, HashMap<String, Object> hashMap, T t);

    void setExtra(Bundle bundle);

    void setGroupId(int i);

    void setIsActive(boolean z);

    void updateGpsSignal(int i);
}
