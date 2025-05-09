package defpackage;

import androidx.lifecycle.MutableLiveData;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.NavigationFilter;
import com.huawei.basefitnessadvice.model.WorkoutListBean;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.suggestion.ui.callback.UiPagingCallback;
import com.huawei.health.suggestion.ui.polymericpage.model.PolymericDataStrategy;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import health.compact.a.Services;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class gby implements PolymericDataStrategy {
    private CourseApi e;

    @Override // com.huawei.health.suggestion.ui.polymericpage.model.PolymericDataStrategy
    public void init(Map<String, Object> map) {
        this.e = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.model.PolymericDataStrategy
    public void getNavigationData(final MutableLiveData<NavigationFilter> mutableLiveData) {
        CourseApi courseApi = this.e;
        if (courseApi == null) {
            mutableLiveData.postValue(null);
        } else {
            courseApi.getAggregatePageByType(3, new UiCallback<NavigationFilter>() { // from class: gby.2
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.b("Suggestion_RunCourseHandler", "getAggregatePageByType:", Integer.valueOf(i), str);
                    mutableLiveData.postValue(null);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void onSuccess(NavigationFilter navigationFilter) {
                    if (navigationFilter == null || navigationFilter.getLeftNavigations() == null) {
                        LogUtil.b("Suggestion_RunCourseHandler", "getAggregatePageByType data empty.");
                    } else {
                        mutableLiveData.postValue(navigationFilter);
                    }
                }
            });
        }
    }

    @Override // com.huawei.health.suggestion.ui.polymericpage.model.PolymericDataStrategy
    public void getPolymericData(final MutableLiveData<Map<String, Object>> mutableLiveData, Object obj) {
        if (obj instanceof WorkoutListBean) {
            if (this.e == null) {
                LogUtil.h("Suggestion_RunCourseHandler", "getFilterWorkoutData() courseApi is null.");
                return;
            }
            final WorkoutListBean workoutListBean = (WorkoutListBean) obj;
            workoutListBean.setWorkoutType(new Integer[]{3, 2});
            workoutListBean.setCourseType(2);
            workoutListBean.setSupportWear(-1);
            if (workoutListBean.getSearchCondition() != null && workoutListBean.getSearchCondition().getLeftNavigationId() == 9999) {
                workoutListBean.setUserDefinedType(1);
            }
            final HashMap hashMap = new HashMap();
            this.e.getCoursesByFilter(workoutListBean, new UiPagingCallback<List<Workout>>() { // from class: gby.1
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.huawei.health.suggestion.ui.callback.UiPagingCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onSuccessFirst(List<Workout> list) {
                    Object[] objArr = new Object[2];
                    objArr[0] = "getCoursesByFilter, onSuccess:";
                    objArr[1] = Integer.valueOf(list != null ? list.size() : 0);
                    LogUtil.a("Suggestion_RunCourseHandler", objArr);
                    List<FitWorkout> a2 = mod.a(list);
                    if (workoutListBean.getUserDefinedType() != null && workoutListBean.getUserDefinedType().equals(1)) {
                        a2 = gby.this.a(a2);
                    }
                    hashMap.put("visible_course_list", a2);
                    hashMap.put("is_success", true);
                    hashMap.put("data_owner_id", workoutListBean.getSearchCondition());
                    hashMap.put("order", workoutListBean.getWorkoutRank());
                    mutableLiveData.postValue(hashMap);
                }

                @Override // com.huawei.health.suggestion.ui.callback.UiPagingCallback
                public void onFailureFirst(int i, String str) {
                    LogUtil.h("Suggestion_RunCourseHandler", "getCoursesByFilter() ==Failed-- errorCode:", Integer.valueOf(i), "errorInfo:", str);
                    if (i == -1) {
                        hashMap.put("is_success", true);
                    } else {
                        hashMap.put("is_success", false);
                    }
                    hashMap.put("data_owner_id", workoutListBean.getSearchCondition());
                    hashMap.put("order", workoutListBean.getWorkoutRank());
                    hashMap.put("is_success", false);
                    mutableLiveData.postValue(hashMap);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<FitWorkout> a(List<FitWorkout> list) {
        LogUtil.a("Suggestion_RunCourseHandler", "handleCoursesData.");
        if (koq.b(list)) {
            LogUtil.h("Suggestion_RunCourseHandler", "handleCoursesData is null.");
            return list;
        }
        Iterator<FitWorkout> it = list.iterator();
        while (it.hasNext()) {
            it.next().setCourseDefineType(1);
        }
        List<FitWorkout> b = gds.b(list);
        fot.a().a(b);
        return b;
    }
}
