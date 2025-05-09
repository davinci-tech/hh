package com.huawei.health.fitnessadvice.api;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.ViewGroup;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.suggestion.PluginSuggestion;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.ui.commonui.base.BaseFragment;
import java.util.Map;

/* loaded from: classes.dex */
public interface FitnessAdviceApi {
    public static final int ROPE_SKIP_RECOMMEND_ACTIVITY_PROVIDER = 7;
    public static final int ROPE_SKIP_RECOMMEND_COURSE_PROVIDER = 6;
    public static final int YOGA_MY_COURSE_PROVIDER = 2;
    public static final int YOGA_RECOMMEND_ACTIVITY_PROVIDER = 5;
    public static final int YOGA_RECOMMEND_COURSE_PROVIDER = 3;
    public static final int YOGA_SERIES_COURSE_PROVIDER = 4;
    public static final int YOGA_STATISTIC_PROVIDER = 1;

    void createAiFitnessPlanCardView(ViewGroup viewGroup, boolean z);

    Object createFitnessDataProvider(int i);

    BaseFragment createFitnessInstanceFragment(int i, int i2);

    BaseFragment createIntelligencePlanTabFragment();

    BaseFragment createPlanRecommendFragment(int i);

    BaseFragment createRunRecommendFragment();

    void deleteFitnessCard();

    void donateBySportRemind(Map<String, String> map);

    Class<?> getAiFitnessServiceH5RepositoryApi();

    Class<?> getAiSportVoiceH5RepositoryApi();

    Class<?> getBodySenseCourseH5RepositoryApi();

    Class<? extends JsBaseModule> getBodySenseJsBridgeApi();

    Class<? extends JsBaseModule> getCourseDetailH5Bridge();

    void getDeviceSportStatus(IBaseResponseCallback iBaseResponseCallback);

    Class<?> getIntelligentPlanH5RepositoryApi();

    PluginSuggestion getPluginSuggestion();

    void jumpFitnessPage(Context context, Uri uri, Bundle bundle);

    void jumpToAllCourse(Context context, int i, int i2, String str);

    void launchMyPlanH5();

    void launchPlanTab();

    void launchTrainDetail(Bundle bundle);

    void sendFitnessLinkCommand(int i);

    void startAddHeartRateDevice(Context context);

    void startMoreDeviceActivity(Context context);

    boolean uploadFitnessRecordData(WorkoutRecord workoutRecord);
}
