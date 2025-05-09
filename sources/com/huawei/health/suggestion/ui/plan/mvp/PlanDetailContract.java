package com.huawei.health.suggestion.ui.plan.mvp;

import android.content.Context;
import android.view.View;
import java.util.List;

/* loaded from: classes4.dex */
public interface PlanDetailContract {

    public interface Ipresenter {
        void getData();

        void onDestroy();

        void onResume();

        void viewCurrentPlan();
    }

    public interface Iview {
        Context acquireContext();

        void addTradeView(View view);

        void bindData(List<String> list);

        void bindTitleText(String str);

        void finishView();

        void recycleTradeView(View view);

        void showDialog();
    }
}
