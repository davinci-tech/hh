package defpackage;

import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import com.huawei.health.fitnessadvice.api.FitnessAdviceApi;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.main.stories.configuredpage.RequestRunCourseRecommend;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class pez {

    /* renamed from: a, reason: collision with root package name */
    private static volatile pez f16103a;
    private static final Object b = new Object();
    private List<RequestRunCourseRecommend> d = new ArrayList();

    private pez() {
    }

    public static pez c() {
        if (f16103a == null) {
            synchronized (b) {
                if (f16103a == null) {
                    f16103a = new pez();
                }
            }
        }
        return f16103a;
    }

    public void d(int i, FragmentManager fragmentManager, ArrayList<FitWorkout> arrayList) {
        if (koq.b(arrayList)) {
            LogUtil.h("ConfiguredListPage", "recommendWorkoutList isEmpty");
            return;
        }
        FitnessAdviceApi fitnessAdviceApi = (FitnessAdviceApi) Services.a("PluginFitnessAdvice", FitnessAdviceApi.class);
        BaseFragment createFitnessInstanceFragment = fitnessAdviceApi != null ? fitnessAdviceApi.createFitnessInstanceFragment(2, 0) : null;
        if (createFitnessInstanceFragment == null) {
            LogUtil.h("ConfiguredListPage", "addCourseRecommendFragment runCourseRecommendFragment == null");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("recommendCourse", arrayList);
        createFitnessInstanceFragment.setArguments(bundle);
        fragmentManager.beginTransaction().replace(i, createFitnessInstanceFragment).commitAllowingStateLoss();
    }
}
