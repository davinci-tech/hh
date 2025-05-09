package defpackage;

import androidx.lifecycle.MutableLiveData;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.NavigationFilter;
import com.huawei.basefitnessadvice.model.WorkoutListBean;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.suggestion.ui.callback.UiPagingCallback;
import com.huawei.health.suggestion.ui.polymericpage.model.PolymericDataStrategy;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.Workout;
import health.compact.a.Services;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class gbv implements PolymericDataStrategy {
    private Map<String, Object> c;
    private CourseApi d;

    @Override // com.huawei.health.suggestion.ui.polymericpage.model.PolymericDataStrategy
    public void init(Map<String, Object> map) {
        this.d = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        this.c = map;
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.model.PolymericDataStrategy
    public void getNavigationData(final MutableLiveData<NavigationFilter> mutableLiveData) {
        int i;
        if (this.d == null) {
            mutableLiveData.postValue(null);
            return;
        }
        Map<String, Object> map = this.c;
        if (map == null || !(map.get("COURSE_PAGE_TYPE") instanceof Integer) || (i = ((Integer) this.c.get("COURSE_PAGE_TYPE")).intValue()) == 0) {
            i = 2;
        }
        this.d.getAggregatePageByType(i, new UiCallback<NavigationFilter>() { // from class: gbv.4
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i2, String str) {
                LogUtil.b("Suggestion_HealthCourseHandler", "getAggregatePageByType:", Integer.valueOf(i2), str);
                mutableLiveData.postValue(null);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(NavigationFilter navigationFilter) {
                if (navigationFilter == null || navigationFilter.getLeftNavigations() == null) {
                    LogUtil.b("Suggestion_HealthCourseHandler", "getAggregatePageByType data empty.");
                }
                mutableLiveData.postValue(navigationFilter);
            }
        });
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.model.PolymericDataStrategy
    public void getPolymericData(final MutableLiveData<Map<String, Object>> mutableLiveData, Object obj) {
        if (!(obj instanceof WorkoutListBean)) {
            LogUtil.b("Suggestion_HealthCourseHandler", "error param.");
            return;
        }
        if (this.d == null) {
            LogUtil.h("Suggestion_HealthCourseHandler", "getFilterWorkoutData() courseApi is null.");
            return;
        }
        final WorkoutListBean workoutListBean = (WorkoutListBean) obj;
        if (workoutListBean.getCourseType() >= 100063001 && workoutListBean.getCourseType() <= 100063999) {
            workoutListBean.setCourseType(1);
        } else {
            workoutListBean.setCourseType(0);
        }
        final HashMap hashMap = new HashMap();
        this.d.getCoursesByFilter(workoutListBean, new UiPagingCallback<List<Workout>>() { // from class: gbv.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.huawei.health.suggestion.ui.callback.UiPagingCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccessFirst(List<Workout> list) {
                Object[] objArr = new Object[2];
                objArr[0] = "getCoursesByFilter, onSuccess:";
                objArr[1] = Integer.valueOf(list != null ? list.size() : 0);
                LogUtil.a("Suggestion_HealthCourseHandler", objArr);
                hashMap.put("visible_course_list", mod.e(list));
                hashMap.put("is_success", true);
                hashMap.put("data_owner_id", workoutListBean.getSearchCondition());
                hashMap.put("order", workoutListBean.getWorkoutRank());
                mutableLiveData.postValue(hashMap);
            }

            @Override // com.huawei.health.suggestion.ui.callback.UiPagingCallback
            public void onFailureFirst(int i, String str) {
                LogUtil.h("Suggestion_HealthCourseHandler", "getCoursesByFilter() ==Failed-- errorCode:", Integer.valueOf(i), "errorInfo:", str);
                hashMap.put("is_success", false);
                hashMap.put("data_owner_id", workoutListBean.getSearchCondition());
                hashMap.put("order", workoutListBean.getWorkoutRank());
                mutableLiveData.postValue(hashMap);
            }
        });
    }
}
