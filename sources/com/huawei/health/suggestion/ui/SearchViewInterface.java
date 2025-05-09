package com.huawei.health.suggestion.ui;

import com.huawei.pluginfitnessadvice.FitWorkout;
import java.util.List;

/* loaded from: classes8.dex */
public interface SearchViewInterface {
    void addData(List<FitWorkout> list);

    void clear();

    void hideLoadMore();

    void hideLoadView();

    void showLoadMore();

    void showSearchNoShow();
}
