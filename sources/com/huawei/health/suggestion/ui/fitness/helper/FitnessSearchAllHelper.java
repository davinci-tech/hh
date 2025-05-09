package com.huawei.health.suggestion.ui.fitness.helper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.suggestion.ui.SearchViewInterface;
import com.huawei.health.suggestion.ui.fragment.FitSearchFragmentFlowLayout;
import com.huawei.health.suggestion.ui.fragment.FitSearchFragmentRecyclerView;
import com.huawei.health.suggestion.ui.fragment.FitnessSearchFragmentBar;
import com.huawei.health.suggestion.ui.fragment.SearchRecyclerViewFragment;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import defpackage.gge;
import defpackage.ggr;
import defpackage.ggs;
import defpackage.ghb;
import defpackage.koq;
import defpackage.mod;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes8.dex */
public class FitnessSearchAllHelper implements FitnessSearchFragmentBar.OnQueryTextListener, FitSearchFragmentRecyclerView.LoadMoreListener, SearchRecyclerViewFragment.LoadMoreListener {
    private WeakReference<Activity> b;
    private int g;
    private SearchViewInterface l;
    private int c = 0;
    private boolean o = false;
    private boolean j = false;
    private boolean h = true;
    private boolean k = false;

    /* renamed from: a, reason: collision with root package name */
    private String f3156a = null;
    private String e = null;
    private int i = 0;
    private Handler f = new Handler(Looper.getMainLooper());
    private String d = "";

    /* JADX WARN: Multi-variable type inference failed */
    public FitnessSearchAllHelper(Activity activity, int i) {
        this.b = new WeakReference<>(activity);
        this.g = i;
        if (activity instanceof SearchViewInterface) {
            this.l = (SearchViewInterface) activity;
        }
    }

    @Override // com.huawei.health.suggestion.ui.fragment.FitSearchFragmentRecyclerView.LoadMoreListener
    public void loadMore() {
        LogUtil.a("Suggestion_FitSearchAllHelper", "loadMore()");
        d();
    }

    private void d() {
        synchronized (this) {
            c(this.f3156a, 0);
        }
    }

    @Override // com.huawei.health.suggestion.ui.fragment.FitnessSearchFragmentBar.OnQueryTextListener
    public boolean onQueryTextSubmit(String str) {
        LogUtil.a("Suggestion_FitSearchAllHelper", "onQueryTextSubmit query= ", str);
        c(str, 1);
        return false;
    }

    @Override // com.huawei.health.suggestion.ui.fragment.FitnessSearchFragmentBar.OnQueryTextListener
    public boolean onQueryTextChange(String str) {
        LogUtil.a("Suggestion_FitSearchAllHelper", "onQueryTextChange newText= ", str);
        c(str, 2);
        return false;
    }

    public void b(String str) {
        this.d = str;
    }

    private void c(String str, int i) {
        synchronized (this) {
            if (TextUtils.isEmpty(str)) {
                this.o = true;
                this.f3156a = null;
                this.e = null;
                this.i = 0;
                LogUtil.a("Suggestion_FitSearchAllHelper", "postSearch TextUtils.isEmpty(query)");
                return;
            }
            this.o = false;
            if (this.j) {
                this.e = str;
                this.i = i;
                LogUtil.a("Suggestion_FitSearchAllHelper", "(mIsLoad = true)");
                return;
            }
            if (str.equals(this.f3156a)) {
                if (!this.h) {
                    LogUtil.a("Suggestion_FitSearchAllHelper", "mHasMoreData = true");
                    c();
                    return;
                } else if (i != 0) {
                    LogUtil.a("Suggestion_FitSearchAllHelper", "type != QUERY_TEXT_LOAD_MORE");
                    this.l.hideLoadView();
                    return;
                } else {
                    this.c++;
                    this.l.showLoadMore();
                }
            } else {
                this.h = true;
                this.k = false;
                this.c = 0;
                this.f3156a = str;
                this.l.clear();
            }
            this.j = true;
            d(this.c * 10, str);
        }
    }

    private void c() {
        this.l.hideLoadMore();
        this.l.hideLoadView();
        if (this.k) {
            LogUtil.a("Suggestion_FitSearchAllHelper", "setViewControl() showSearchNoShow");
            this.l.showSearchNoShow();
        }
    }

    private void d(final int i, final String str) {
        LogUtil.a("Suggestion_FitSearchAllHelper", "postSearch query = ", str);
        int i2 = this.g;
        String str2 = i2 == 1 ? "RUNNING_COURSE" : i2 == 2 ? "FITNESS_COURSE" : "";
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("Suggestion_FitSearchAllHelper", "querySearchRecords : courseApi is null.");
        } else {
            courseApi.searchCourseList(i, 10, str, str2, new UiCallback<List<Workout>>() { // from class: com.huawei.health.suggestion.ui.fitness.helper.FitnessSearchAllHelper.5
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i3, String str3) {
                    LogUtil.b("Suggestion_FitSearchAllHelper", "postSearch onFailure query= ", str, ", errorCode=", Integer.valueOf(i3), ", errorInfo=", str3);
                    FitnessSearchAllHelper.this.a(null, str);
                    FitnessSearchAllHelper.this.b(null, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<Workout> list) {
                    Object[] objArr = new Object[6];
                    objArr[0] = "postSearch onSuccess query= ";
                    objArr[1] = str;
                    objArr[2] = ", pageStart=";
                    objArr[3] = Integer.valueOf(i);
                    objArr[4] = ", data=";
                    objArr[5] = Integer.valueOf(koq.c(list) ? list.size() : 0);
                    LogUtil.a("Suggestion_FitSearchAllHelper", objArr);
                    FitnessSearchAllHelper.this.a(mod.a(list), str);
                    FitnessSearchAllHelper.this.b(mod.a(list), str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<FitWorkout> list, String str) {
        ggr.b(gge.c(this.d), str, koq.c(list) ? list.size() : 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final List<FitWorkout> list, final String str) {
        synchronized (this) {
            this.f.post(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.helper.FitnessSearchAllHelper.2
                @Override // java.lang.Runnable
                public void run() {
                    FitnessSearchAllHelper.this.c((List<FitWorkout>) list, str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<FitWorkout> list, String str) {
        synchronized (this) {
            LogUtil.a("Suggestion_FitSearchAllHelper", "addDataInner query= ", str, ",mCurrentText=", this.f3156a);
            Activity activity = this.b.get();
            if (activity == null) {
                LogUtil.h("Suggestion_FitSearchAllHelper", "addDataInner activity is null");
                return;
            }
            if (!activity.isFinishing() && !activity.isDestroyed()) {
                this.l.hideLoadView();
                if (this.c == 0 && koq.b(list)) {
                    LogUtil.a("Suggestion_FitSearchAllHelper", "addDataInner show No data");
                    this.l.showSearchNoShow();
                    this.k = true;
                }
                if (koq.b(list) || list.size() < 10) {
                    this.h = false;
                }
                this.j = false;
                if (this.o) {
                    LogUtil.a("Suggestion_FitSearchAllHelper", "mIsNullText = true");
                    return;
                }
                if (str.equals(this.f3156a)) {
                    int i = this.g;
                    if (i == 1) {
                        list = ggs.d(list, "RUNNING_COURSE");
                    } else if (i == 2) {
                        list = ggs.d(list, "FITNESS_COURSE");
                    } else if (koq.b(list)) {
                        list = Collections.emptyList();
                    }
                    this.l.addData(list);
                }
                if (!TextUtils.isEmpty(this.e)) {
                    String str2 = this.e;
                    int i2 = this.i;
                    this.e = null;
                    this.i = 0;
                    if (!str2.equals(this.f3156a)) {
                        c(str2, i2);
                    }
                }
                return;
            }
            LogUtil.h("Suggestion_FitSearchAllHelper", "addDataInner activity.isFinishing() || activity.isDestroyed()");
        }
    }

    public static class SearchAllFowAdapter implements FitSearchFragmentFlowLayout.FowAdapter {
        private Context b;
        private int c;
        private LinkedList<String> e = new LinkedList<>();

        public SearchAllFowAdapter(Context context) {
            this.b = context.getApplicationContext();
            c();
        }

        public SearchAllFowAdapter(Context context, int i) {
            this.b = context.getApplicationContext();
            this.c = i;
            c();
        }

        @Override // com.huawei.health.suggestion.ui.fragment.FitSearchFragmentFlowLayout.FowAdapter
        public LinkedList<String> getShowData() {
            return a();
        }

        private LinkedList<String> a() {
            LinkedList<String> linkedList;
            synchronized (this) {
                linkedList = this.e;
            }
            return linkedList;
        }

        @Override // com.huawei.health.suggestion.ui.fragment.FitSearchFragmentFlowLayout.FowAdapter
        public void saveShowData(String str) {
            c(str);
        }

        private void c() {
            String string;
            synchronized (this) {
                SharedPreferences sharedPreferences = this.b.getApplicationContext().getSharedPreferences("com.huawei.health.suggestion.ui.fragment.SearchAllFowAdapter", 0);
                int i = this.c;
                if (i == 1) {
                    string = sharedPreferences.getString("flow_layout_run_key", null);
                } else if (i == 2) {
                    string = sharedPreferences.getString("flow_layout_fitness_key", null);
                } else {
                    string = sharedPreferences.getString("flow_layout_key", null);
                }
                d(string);
            }
        }

        private void d(String str) {
            if (str != null) {
                Iterator it = ghb.c(str, String[].class).iterator();
                while (it.hasNext()) {
                    this.e.add((String) it.next());
                }
            }
        }

        private void c(String str) {
            synchronized (this) {
                if (TextUtils.isEmpty(str)) {
                    return;
                }
                Iterator<String> it = this.e.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    } else if (str.equals(it.next())) {
                        it.remove();
                        break;
                    }
                }
                this.e.add(0, str);
                ArrayList arrayList = new ArrayList(10);
                Iterator<String> it2 = this.e.iterator();
                while (it2.hasNext()) {
                    arrayList.add(it2.next());
                }
                SharedPreferences sharedPreferences = this.b.getApplicationContext().getSharedPreferences("com.huawei.health.suggestion.ui.fragment.SearchAllFowAdapter", 0);
                if (sharedPreferences == null) {
                    LogUtil.h("Suggestion_FitSearchAllHelper", "saveFlowLayoutValue SharedPreferences can not null");
                    return;
                }
                int i = this.c;
                if (i == 1) {
                    sharedPreferences.edit().putString("flow_layout_run_key", ghb.e(arrayList)).commit();
                } else if (i == 2) {
                    sharedPreferences.edit().putString("flow_layout_fitness_key", ghb.e(arrayList)).commit();
                } else {
                    sharedPreferences.edit().putString("flow_layout_key", ghb.e(arrayList)).commit();
                }
            }
        }
    }
}
