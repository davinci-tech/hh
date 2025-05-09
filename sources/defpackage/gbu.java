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
public class gbu implements PolymericDataStrategy {
    private Map<String, Object> b;
    private CourseApi d;

    @Override // com.huawei.health.suggestion.ui.polymericpage.model.PolymericDataStrategy
    public void init(Map<String, Object> map) {
        this.d = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (map == null) {
            this.b = new HashMap();
        } else {
            this.b = map;
        }
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.model.PolymericDataStrategy
    public void getNavigationData(final MutableLiveData<NavigationFilter> mutableLiveData) {
        int i;
        if (this.d == null) {
            return;
        }
        Map<String, Object> map = this.b;
        if (map == null || !(map.get("COURSE_PAGE_TYPE") instanceof Integer) || (i = ((Integer) this.b.get("COURSE_PAGE_TYPE")).intValue()) == 0) {
            i = 1;
        }
        this.d.getAggregatePageByType(i, new UiCallback<NavigationFilter>() { // from class: gbu.3
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i2, String str) {
                LogUtil.b("Suggestion_FitnessCourseHandler", "getAggregatePageByType:", Integer.valueOf(i2), str);
                mutableLiveData.postValue(null);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onSuccess(NavigationFilter navigationFilter) {
                if (navigationFilter == null || navigationFilter.getLeftNavigations() == null) {
                    LogUtil.b("Suggestion_FitnessCourseHandler", "getAggregatePageByType data empty.");
                }
                mutableLiveData.postValue(navigationFilter);
            }
        });
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.model.PolymericDataStrategy
    public void getPolymericData(final MutableLiveData<Map<String, Object>> mutableLiveData, Object obj) {
        String str;
        if (!(obj instanceof WorkoutListBean)) {
            LogUtil.b("Suggestion_FitnessCourseHandler", "error param.");
            return;
        }
        if (this.d == null) {
            LogUtil.h("Suggestion_FitnessCourseHandler", "getFilterWorkoutData() courseApi is null.");
            return;
        }
        final WorkoutListBean workoutListBean = (WorkoutListBean) obj;
        workoutListBean.setWorkoutType(new Integer[]{1});
        workoutListBean.setCourseType(2);
        workoutListBean.setSupportWear(-1);
        if ((this.b.get("KEY_SUG_COURSE_REPLACE_STATUS") instanceof Boolean) && ((Boolean) this.b.get("KEY_SUG_COURSE_REPLACE_STATUS")).booleanValue() && (this.b.get("KEY_SUG_COURSE_REPLACE_ID") instanceof String) && (str = (String) this.b.get("KEY_SUG_COURSE_REPLACE_ID")) != null) {
            workoutListBean.setReplacedWorkoutId(str);
        }
        final HashMap hashMap = new HashMap();
        this.d.getCoursesByFilter(workoutListBean, new UiPagingCallback<List<Workout>>() { // from class: gbu.5
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.huawei.health.suggestion.ui.callback.UiPagingCallback
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void onSuccessFirst(List<Workout> list) {
                Object[] objArr = new Object[2];
                objArr[0] = "getCoursesByFilter, onSuccess:";
                objArr[1] = Integer.valueOf(list != null ? list.size() : 0);
                LogUtil.a("Suggestion_FitnessCourseHandler", objArr);
                hashMap.put("visible_course_list", mod.a(list));
                hashMap.put("is_success", true);
                hashMap.put("data_owner_id", workoutListBean.getSearchCondition());
                hashMap.put("order", workoutListBean.getWorkoutRank());
                mutableLiveData.postValue(hashMap);
            }

            @Override // com.huawei.health.suggestion.ui.callback.UiPagingCallback
            public void onFailureFirst(int i, String str2) {
                LogUtil.h("Suggestion_FitnessCourseHandler", "getCoursesByFilter() ==Failed-- errorCode:", Integer.valueOf(i), "errorInfo:", str2);
                hashMap.put("is_success", false);
                hashMap.put("data_owner_id", workoutListBean.getSearchCondition());
                hashMap.put("order", workoutListBean.getWorkoutRank());
                mutableLiveData.postValue(hashMap);
            }
        });
    }
}
