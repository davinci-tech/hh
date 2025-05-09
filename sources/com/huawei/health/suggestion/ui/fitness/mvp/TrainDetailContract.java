package com.huawei.health.suggestion.ui.fitness.mvp;

import android.app.Activity;
import android.content.Intent;
import android.text.SpannableString;
import android.view.View;
import androidx.collection.ArrayMap;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.RecommendationWorkout;
import com.huawei.trade.datatype.ProductSummary;
import com.huawei.trade.datatype.TradeViewInfo;
import java.lang.ref.WeakReference;
import java.util.List;

/* loaded from: classes4.dex */
public interface TrainDetailContract {

    public interface Ipresenter {
        void getRecommendationList(List<RecommendationWorkout> list);

        void initNoticeContent(String str, String str2);

        void initTradeView(FitWorkout fitWorkout);

        boolean isCoursePaid(int i);

        boolean isFreeCourses(int i);

        boolean isPayCourses(int i);

        boolean isVipCourses(int i);

        void judgeVipState();

        void onActivityResult(int i, int i2, Intent intent);

        void onDestroy();

        void onResume();

        void refreshCourseData();
    }

    /* loaded from: classes.dex */
    public interface Iview {
        WeakReference<Activity> acquireActivity();

        String acquireWorkoutName();

        void addTradeView(View view);

        void bindProductCourseSummary(ProductSummary productSummary, ProductSummary productSummary2, TradeViewInfo tradeViewInfo);

        void bindRecommendationData(List<FitWorkout> list, ArrayMap<String, String> arrayMap);

        void changBuyState(boolean z);

        void changeVipState(boolean z);

        void hideDownloadView();

        void notifyActivityResult(int i, int i2, Intent intent);

        void recycleTradeView(View view);

        void setNoticeContent(SpannableString spannableString);

        void showDownloadView();
    }
}
