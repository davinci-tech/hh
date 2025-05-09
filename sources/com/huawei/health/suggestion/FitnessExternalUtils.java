package com.huawei.health.suggestion;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.huawei.health.fitnessadvice.api.FitnessAdviceApi;
import com.huawei.health.h5pro.jsbridge.base.JsBaseModule;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.sport.utils.SportSupportUtil;
import com.huawei.health.suggestion.h5pro.AiFitnessServiceH5Repository;
import com.huawei.health.suggestion.h5pro.AiSportVoiceH5Repository;
import com.huawei.health.suggestion.h5pro.BodySenseCourseH5Repository;
import com.huawei.health.suggestion.h5pro.BodySenseH5Bridge;
import com.huawei.health.suggestion.h5pro.BodySenseManager;
import com.huawei.health.suggestion.h5pro.CourseDetailH5Bridge;
import com.huawei.health.suggestion.h5pro.IntelligentPlanH5Repository;
import com.huawei.health.suggestion.ui.AiFitnessPlanCardFragment;
import com.huawei.health.suggestion.ui.plan.fragment.IntelligencePlanFragment;
import com.huawei.health.suggestion.ui.run.activity.fragment.RunPlanFragment;
import com.huawei.health.suggestion.ui.run.activity.fragment.RunRecommendFragment;
import com.huawei.health.suggestion.ui.tabfragments.provider.RopeSkipRecommendCourseProvider;
import com.huawei.health.suggestion.ui.tabfragments.provider.YogaMyCourseProvider;
import com.huawei.health.suggestion.ui.tabfragments.provider.YogaRecommendCourseProvider;
import com.huawei.health.suggestion.ui.tabfragments.provider.YogaSeriesCourseProvider;
import com.huawei.health.suggestion.ui.tabfragments.provider.YogaStatisticProvider;
import com.huawei.health.suggestion.util.JumpUtil;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hmf.annotation.Singleton;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseFragment;
import defpackage.arx;
import defpackage.cap;
import defpackage.fil;
import defpackage.fny;
import defpackage.ftb;
import defpackage.fyr;
import defpackage.ggu;
import defpackage.ggx;
import defpackage.mmp;
import defpackage.mod;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Map;

@ApiDefine(uri = FitnessAdviceApi.class)
@Singleton
/* loaded from: classes4.dex */
public class FitnessExternalUtils implements FitnessAdviceApi {
    public static boolean e() {
        return false;
    }

    @Override // com.huawei.health.fitnessadvice.api.FitnessAdviceApi
    public PluginSuggestion getPluginSuggestion() {
        return (PluginSuggestion) Services.a("PluginFitnessAdvice", PluginSuggestion.class);
    }

    @Override // com.huawei.health.fitnessadvice.api.FitnessAdviceApi
    public BaseFragment createPlanRecommendFragment(int i) {
        return a(i);
    }

    @Override // com.huawei.health.fitnessadvice.api.FitnessAdviceApi
    public BaseFragment createRunRecommendFragment() {
        return RunRecommendFragment.a();
    }

    @Override // com.huawei.health.fitnessadvice.api.FitnessAdviceApi
    public BaseFragment createFitnessInstanceFragment(int i, int i2) {
        if (i == 0) {
            return a(i2);
        }
        if (i == 2) {
            return RunRecommendFragment.a();
        }
        if (i == 3) {
            if (a()) {
                return AiFitnessPlanCardFragment.a();
            }
            return null;
        }
        return new BaseFragment();
    }

    @Override // com.huawei.health.fitnessadvice.api.FitnessAdviceApi
    public boolean uploadFitnessRecordData(WorkoutRecord workoutRecord) {
        return fil.d(workoutRecord);
    }

    @Override // com.huawei.health.fitnessadvice.api.FitnessAdviceApi
    public Object createFitnessDataProvider(int i) {
        if (i == 1) {
            return new YogaStatisticProvider();
        }
        if (i == 2) {
            return new YogaMyCourseProvider();
        }
        if (i == 3) {
            return new YogaRecommendCourseProvider();
        }
        if (i == 4) {
            return new YogaSeriesCourseProvider();
        }
        if (i != 6) {
            return null;
        }
        return new RopeSkipRecommendCourseProvider();
    }

    @Override // com.huawei.health.fitnessadvice.api.FitnessAdviceApi
    public BaseFragment createIntelligencePlanTabFragment() {
        if (a()) {
            return IntelligencePlanFragment.a();
        }
        return null;
    }

    @Override // com.huawei.health.fitnessadvice.api.FitnessAdviceApi
    public void createAiFitnessPlanCardView(ViewGroup viewGroup, boolean z) {
        if (a()) {
            ggu.aMu_(viewGroup, z);
        }
    }

    public static boolean a() {
        return SportSupportUtil.j();
    }

    public static boolean d() {
        return SportSupportUtil.d();
    }

    public static boolean b() {
        return (Utils.o() || CommonUtil.bu()) ? false : true;
    }

    private BaseFragment a(int i) {
        LogUtil.a("Suggestion_FitnessExternalUtils", "isAiRunPlanOpen ：", Boolean.valueOf(a()));
        return RunPlanFragment.a();
    }

    @Override // com.huawei.health.fitnessadvice.api.FitnessAdviceApi
    public void launchMyPlanH5() {
        JumpUtil.a(arx.b());
    }

    @Override // com.huawei.health.fitnessadvice.api.FitnessAdviceApi
    public void launchPlanTab() {
        JumpUtil.c(arx.b());
    }

    @Override // com.huawei.health.fitnessadvice.api.FitnessAdviceApi
    public void launchTrainDetail(Bundle bundle) {
        if (bundle == null) {
            ReleaseLogUtil.c("Suggestion_FitnessExternalUtils", "launchTrainDetail bundle is null.");
            return;
        }
        ReleaseLogUtil.e("Suggestion_FitnessExternalUtils", "launchTrainDetail bundle:", bundle.toString());
        String string = bundle.getString("workoutid");
        String string2 = bundle.getString("version");
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
            return;
        }
        mmp mmpVar = new mmp(string);
        mmpVar.c(true);
        mmpVar.r(string2);
        mod.c(BaseApplication.getContext(), mmpVar);
    }

    @Override // com.huawei.health.fitnessadvice.api.FitnessAdviceApi
    public void jumpFitnessPage(Context context, Uri uri, Bundle bundle) {
        new ftb(context, uri, bundle).b();
    }

    @Override // com.huawei.health.fitnessadvice.api.FitnessAdviceApi
    public Class<?> getIntelligentPlanH5RepositoryApi() {
        return IntelligentPlanH5Repository.class;
    }

    @Override // com.huawei.health.fitnessadvice.api.FitnessAdviceApi
    public Class<?> getBodySenseCourseH5RepositoryApi() {
        return BodySenseCourseH5Repository.class;
    }

    @Override // com.huawei.health.fitnessadvice.api.FitnessAdviceApi
    public Class<?> getAiFitnessServiceH5RepositoryApi() {
        return AiFitnessServiceH5Repository.class;
    }

    @Override // com.huawei.health.fitnessadvice.api.FitnessAdviceApi
    public Class<? extends JsBaseModule> getBodySenseJsBridgeApi() {
        return BodySenseH5Bridge.class;
    }

    @Override // com.huawei.health.fitnessadvice.api.FitnessAdviceApi
    public void jumpToAllCourse(Context context, int i, int i2, String str) {
        JumpUtil.b(context, i, i2, str);
    }

    @Override // com.huawei.health.fitnessadvice.api.FitnessAdviceApi
    public Class<?> getAiSportVoiceH5RepositoryApi() {
        return AiSportVoiceH5Repository.class;
    }

    @Override // com.huawei.health.fitnessadvice.api.FitnessAdviceApi
    public void sendFitnessLinkCommand(int i) {
        if (i == 1) {
            BodySenseManager.getInstance().resumeFitnessLink();
            return;
        }
        if (i == 2) {
            BodySenseManager.getInstance().pauseFitnessLink();
        } else if (i == 3) {
            BodySenseManager.getInstance().stopFitnessLink();
        } else {
            if (i != 4) {
                return;
            }
            BodySenseManager.getInstance().startFitnessLink();
        }
    }

    @Override // com.huawei.health.fitnessadvice.api.FitnessAdviceApi
    public void donateBySportRemind(Map<String, String> map) {
        fyr.d(map);
    }

    @Override // com.huawei.health.fitnessadvice.api.FitnessAdviceApi
    public void deleteFitnessCard() {
        fyr.d();
    }

    @Override // com.huawei.health.fitnessadvice.api.FitnessAdviceApi
    public Class<? extends JsBaseModule> getCourseDetailH5Bridge() {
        return CourseDetailH5Bridge.class;
    }

    @Override // com.huawei.health.fitnessadvice.api.FitnessAdviceApi
    public void startMoreDeviceActivity(Context context) {
        ggx.b(context);
    }

    @Override // com.huawei.health.fitnessadvice.api.FitnessAdviceApi
    public void startAddHeartRateDevice(Context context) {
        new fny(new Handler(), context).b(context);
    }

    @Override // com.huawei.health.fitnessadvice.api.FitnessAdviceApi
    public void getDeviceSportStatus(IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            ReleaseLogUtil.d("Suggestion_FitnessExternalUtils", "getDeviceSportStatus callback == null");
        } else if (cap.a()) {
            cap.b(iBaseResponseCallback);
        } else {
            iBaseResponseCallback.d(100000, 0);
        }
    }
}
