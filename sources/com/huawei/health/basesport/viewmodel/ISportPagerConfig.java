package com.huawei.health.basesport.viewmodel;

import androidx.fragment.app.Fragment;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public interface ISportPagerConfig {
    default int getCount() {
        return 0;
    }

    default int getDefaultItem() {
        return 0;
    }

    default boolean isCanScroll() {
        return false;
    }

    default List<Fragment> getFragments() {
        return Collections.EMPTY_LIST;
    }
}
