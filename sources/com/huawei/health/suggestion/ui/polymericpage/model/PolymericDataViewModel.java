package com.huawei.health.suggestion.ui.polymericpage.model;

import android.text.TextUtils;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import com.huawei.basefitnessadvice.model.FilterCondition;
import com.huawei.basefitnessadvice.model.Navigation;
import com.huawei.basefitnessadvice.model.NavigationFilter;
import com.huawei.basefitnessadvice.model.SearchCondition;
import com.huawei.basefitnessadvice.model.WorkoutListBean;
import com.huawei.haf.application.BaseApplication;
import com.huawei.health.vip.datatypes.UserMemberInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import defpackage.gbu;
import defpackage.gbv;
import defpackage.gby;
import defpackage.gpn;
import defpackage.koq;
import defpackage.moj;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class PolymericDataViewModel extends ViewModel {

    /* renamed from: a, reason: collision with root package name */
    private Map<String, Object> f3313a;
    private PolymericDataStrategy b;
    private MutableLiveData<Map<String, Object>> c;
    private String d;
    private MutableLiveData<NavigationFilter> e;
    private WorkoutListBean g;

    public void a(String str, Map<String, Object> map) {
        if (str == null) {
            LogUtil.b("Suggestion_PolymericDataViewModel", "pageType is null or empty in init");
            this.d = "";
        } else {
            this.d = str;
        }
        if (map == null) {
            this.f3313a = new HashMap();
        } else {
            this.f3313a = map;
        }
        this.f3313a.put("PAGE_TYPE", this.d);
        LogUtil.a("Suggestion_PolymericDataViewModel", "init view model.", this.d);
        if (this.d.equals("RUNNING_COURSE")) {
            this.b = new gby();
        } else if (this.d.equals("FITNESS_COURSE") || this.d.equals("")) {
            this.b = new gbu();
        } else if (this.d.equals("HEALTH_COURSE")) {
            this.b = new gbv();
        } else {
            LogUtil.b("Suggestion_PolymericDataViewModel", "page type un know.");
            return;
        }
        this.b.init(this.f3313a);
    }

    public String d() {
        return TextUtils.isEmpty(this.d) ? "" : this.d;
    }

    public int c() {
        WorkoutListBean workoutListBean = this.g;
        if (workoutListBean == null || workoutListBean.getSearchCondition() == null) {
            return 0;
        }
        return this.g.getSearchCondition().getLeftNavigationId();
    }

    public SearchCondition a() {
        WorkoutListBean workoutListBean = this.g;
        if (workoutListBean == null) {
            return null;
        }
        return workoutListBean.getSearchCondition();
    }

    public Navigation c(int i) {
        NavigationFilter value = this.e.getValue();
        if (value == null || koq.b(value.getLeftNavigations())) {
            return null;
        }
        for (Navigation navigation : value.getLeftNavigations()) {
            if (navigation.getValue() == i) {
                return navigation;
            }
        }
        return null;
    }

    public void b() {
        LogUtil.a("Suggestion_PolymericDataViewModel", "getNavigationData.");
        if (this.e == null) {
            this.e = new MutableLiveData<>();
        }
        PolymericDataStrategy polymericDataStrategy = this.b;
        if (polymericDataStrategy == null) {
            this.e.postValue(null);
        } else {
            polymericDataStrategy.getNavigationData(this.e);
        }
    }

    public void c(Navigation navigation, List<FilterCondition> list, boolean z, int i) {
        if (this.b == null || navigation == null) {
            return;
        }
        WorkoutListBean b = b(navigation, list, z, i);
        this.g = b;
        LogUtil.a("Suggestion_PolymericDataViewModel", "doFilter:", moj.e(b));
        this.b.getPolymericData(this.c, this.g);
    }

    private WorkoutListBean b(Navigation navigation, List<FilterCondition> list, boolean z, int i) {
        WorkoutListBean workoutListBean = new WorkoutListBean();
        workoutListBean.setCourseType(navigation.getCategoryId());
        if (LoginInit.getInstance(BaseApplication.e()).isKidAccount()) {
            workoutListBean.setCommodityFlag(0);
        }
        b(navigation, list, workoutListBean);
        if (navigation.getValue() == 9999) {
            workoutListBean.setUseCache(false);
        } else {
            workoutListBean.setUseCache(z);
        }
        workoutListBean.setPageStart(i * 10);
        workoutListBean.setPageSize(10);
        return workoutListBean;
    }

    private void b(Navigation navigation, List<FilterCondition> list, WorkoutListBean workoutListBean) {
        ArrayList arrayList;
        FilterCondition filterCondition = null;
        if (koq.b(list)) {
            arrayList = new ArrayList(0);
        } else {
            ArrayList arrayList2 = new ArrayList(list.size());
            for (FilterCondition filterCondition2 : list) {
                if (filterCondition2 == null || filterCondition2.getType() != 1000612) {
                    arrayList2.add(filterCondition2);
                } else {
                    filterCondition = filterCondition2;
                }
            }
            arrayList = arrayList2;
        }
        workoutListBean.setSearchCondition(new SearchCondition.Builder().leftNavigationId(navigation.getValue()).filterConditions(arrayList).build());
        b(workoutListBean, filterCondition);
    }

    private void b(WorkoutListBean workoutListBean, FilterCondition filterCondition) {
        if (filterCondition != null && koq.c(filterCondition.getFilterOptions())) {
            workoutListBean.setWorkoutRank(Integer.valueOf(filterCondition.getFilterOptions().get(0).getCategoryId()));
        } else {
            a(workoutListBean);
        }
    }

    private void a(WorkoutListBean workoutListBean) {
        boolean booleanValue = this.f3313a.get("KEY_SUG_COURSE_REPLACE_STATUS") instanceof Boolean ? ((Boolean) this.f3313a.get("KEY_SUG_COURSE_REPLACE_STATUS")).booleanValue() : false;
        boolean e = e();
        ReleaseLogUtil.e("Suggestion_PolymericDataViewModel", "setDefaultWorkoutRank isReplaceCourseOperation", Boolean.valueOf(booleanValue), " isVip:", Boolean.valueOf(e), " mPageType:", this.d);
        if (e && !booleanValue) {
            workoutListBean.setWorkoutRank(6);
            return;
        }
        if (booleanValue) {
            workoutListBean.setWorkoutRank(5);
        } else if ("HEALTH_COURSE".equals(this.d)) {
            workoutListBean.setWorkoutRank(0);
        } else {
            workoutListBean.setWorkoutRank(1);
        }
    }

    private boolean e() {
        UserMemberInfo a2 = gpn.a();
        if (a2 != null) {
            return a2.getMemberFlag() == 1 && !gpn.d(a2);
        }
        LogUtil.h("Suggestion_PolymericDataViewModel", "isVip userMemberInfo == null");
        return false;
    }

    public void d(int i, boolean z) {
        if (this.g == null || this.b == null) {
            LogUtil.b("Suggestion_PolymericDataViewModel", "mSelectItems == null or mPolymericDataHandler == null.");
            return;
        }
        LogUtil.a("Suggestion_PolymericDataViewModel", "loadMoreData:", Integer.valueOf(i), " isUseCache:", Boolean.valueOf(z));
        this.g.setUseCache(z);
        this.g.setPageStart(i * 10);
        this.b.getPolymericData(this.c, this.g);
    }

    public void a(LifecycleOwner lifecycleOwner, Observer<Map<String, Object>> observer) {
        if (lifecycleOwner == null || observer == null) {
            LogUtil.h("Suggestion_PolymericDataViewModel", "observePolymericData() is null");
            return;
        }
        if (this.c == null) {
            this.c = new MutableLiveData<>();
        }
        this.c.observe(lifecycleOwner, observer);
    }

    public void c(LifecycleOwner lifecycleOwner, Observer<NavigationFilter> observer) {
        if (lifecycleOwner == null || observer == null) {
            LogUtil.h("Suggestion_PolymericDataViewModel", "observePolymericData() is null");
            return;
        }
        if (this.e == null) {
            this.e = new MutableLiveData<>();
        }
        this.e.observe(lifecycleOwner, observer);
    }

    public void a(Observer<Map<String, Object>> observer) {
        if (observer == null) {
            LogUtil.h("Suggestion_PolymericDataViewModel", "removeNavigationObserve() is null");
            return;
        }
        MutableLiveData<Map<String, Object>> mutableLiveData = this.c;
        if (mutableLiveData != null) {
            mutableLiveData.removeObserver(observer);
        }
    }

    public void b(Observer<NavigationFilter> observer) {
        if (observer == null) {
            LogUtil.h("Suggestion_PolymericDataViewModel", "removeNavigationObserve() is null");
            return;
        }
        MutableLiveData<NavigationFilter> mutableLiveData = this.e;
        if (mutableLiveData != null) {
            mutableLiveData.removeObserver(observer);
        }
    }
}
