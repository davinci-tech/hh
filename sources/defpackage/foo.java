package defpackage;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.suggestion.ui.BaseFitnessSearchActivity;
import com.huawei.health.suggestion.ui.fitness.activity.FitnessActionLibraryActivity;
import com.huawei.health.suggestion.ui.fitness.activity.FitnessActionTypeActivity;
import com.huawei.health.suggestion.ui.fitness.helper.FitnessSearchAllHelper;
import com.huawei.health.suggestion.ui.fragment.FitSearchFragmentRecyclerView;
import com.huawei.health.suggestion.ui.fragment.FitnessSearchFragmentBar;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.AtomicAction;
import health.compact.a.Services;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes8.dex */
public class foo extends FitnessSearchAllHelper implements FitnessSearchFragmentBar.OnQueryTextListener, FitSearchFragmentRecyclerView.LoadMoreListener {

    /* renamed from: a, reason: collision with root package name */
    private Handler f12585a;
    private int b;
    private BaseFitnessSearchActivity c;
    private int d;

    @Override // com.huawei.health.suggestion.ui.fitness.helper.FitnessSearchAllHelper, com.huawei.health.suggestion.ui.fragment.FitSearchFragmentRecyclerView.LoadMoreListener
    public void loadMore() {
    }

    public foo(Activity activity) {
        super(activity, 2);
        this.d = 0;
        this.b = 0;
        this.f12585a = new Handler(Looper.getMainLooper());
        if ((activity instanceof FitnessActionTypeActivity) || (activity instanceof FitnessActionLibraryActivity)) {
            this.c = (BaseFitnessSearchActivity) activity;
        }
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.FitnessSearchAllHelper, com.huawei.health.suggestion.ui.fragment.FitnessSearchFragmentBar.OnQueryTextListener
    public boolean onQueryTextSubmit(String str) {
        d(str);
        return false;
    }

    @Override // com.huawei.health.suggestion.ui.fitness.helper.FitnessSearchAllHelper, com.huawei.health.suggestion.ui.fragment.FitnessSearchFragmentBar.OnQueryTextListener
    public boolean onQueryTextChange(String str) {
        BaseFitnessSearchActivity baseFitnessSearchActivity;
        if (!TextUtils.isEmpty(str) || (baseFitnessSearchActivity = this.c) == null) {
            return false;
        }
        baseFitnessSearchActivity.updateViewState();
        return false;
    }

    private void d(String str) {
        c();
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Suggestion_FitnessActionSearchHelper", "queryRecords : courseApi is null.");
        } else {
            courseApi.searchCourseActionList(this.d, 50, this.b, str, new UiCallback<List<AtomicAction>>() { // from class: foo.3
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str2) {
                    LogUtil.h("Suggestion_FitnessActionSearchHelper", "searchActionList Search Action Lists Fauile");
                    foo.this.a(null);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<AtomicAction> list) {
                    foo.this.a(list);
                }
            });
        }
    }

    private void c() {
        HashMap hashMap = new HashMap(1);
        hashMap.put("click", 1);
        gge.e(AnalyticsValue.HEALTH_FITNESS_ACTION_SEARCH_1130033.value(), hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final List<AtomicAction> list) {
        this.f12585a.post(new Runnable() { // from class: foo.2
            @Override // java.lang.Runnable
            public void run() {
                foo.this.c.clear();
                foo.this.c.resetData(list);
            }
        });
    }

    public void c(int i) {
        this.b = i;
    }
}
