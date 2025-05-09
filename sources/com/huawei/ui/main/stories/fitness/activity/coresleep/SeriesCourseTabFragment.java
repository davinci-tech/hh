package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.health.R;
import com.huawei.health.course.api.CourseApi;
import com.huawei.health.vip.api.VipApi;
import com.huawei.health.vip.api.VipCallback;
import com.huawei.health.vip.datatypes.UserMemberInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.audio.SleepAudioPackage;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.ui.main.stories.fitness.activity.coresleep.model.SleepSeriesCourseBean;
import com.huawei.ui.main.stories.soical.views.RecyclerItemDecoration;
import defpackage.gpn;
import defpackage.koq;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class SeriesCourseTabFragment extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private SleepPopularCoursesAdapter f9803a;
    private RecyclerView b;
    private HealthScrollView c;
    private Context d;
    private List<SleepSeriesCourseBean> h;
    private View j;
    private final VipCallback i = new VipCallback() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseTabFragment.1
        @Override // com.huawei.health.vip.api.VipCallback
        public void onSuccess(Object obj) {
            if (obj == null) {
                ReleaseLogUtil.c("SeriesCourseTabFragment", "getVipInfo onSuccess result is null");
                SeriesCourseTabFragment.this.f9803a.c(false);
                return;
            }
            UserMemberInfo userMemberInfo = obj instanceof UserMemberInfo ? (UserMemberInfo) obj : null;
            if (userMemberInfo == null || userMemberInfo.getMemberFlag() != 1) {
                ReleaseLogUtil.e("SeriesCourseTabFragment", "TradeViewApi memberInfo == null or user is not member");
                SeriesCourseTabFragment.this.f9803a.c(false);
                return;
            }
            LogUtil.a("SeriesCourseTabFragment", "memberInfo: ", userMemberInfo.toString());
            if (gpn.d(userMemberInfo)) {
                ReleaseLogUtil.e("SeriesCourseTabFragment", "member is expired");
                SeriesCourseTabFragment.this.f9803a.c(false);
            } else {
                ReleaseLogUtil.e("SeriesCourseTabFragment", "user is member");
                SeriesCourseTabFragment.this.f9803a.c(true);
            }
        }

        @Override // com.huawei.health.vip.api.VipCallback
        public void onFailure(int i, String str) {
            ReleaseLogUtil.c("SeriesCourseTabFragment", "getVipInfo errorCode=", Integer.valueOf(i), " errMsg=", str);
            SeriesCourseTabFragment.this.f9803a.c(false);
        }
    };
    private final RecyclerView.OnScrollListener e = new RecyclerView.OnScrollListener() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseTabFragment.5
        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            super.onScrollStateChanged(recyclerView, i);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            super.onScrolled(recyclerView, i, i2);
            if (recyclerView.computeVerticalScrollOffset() <= 0) {
                if (SeriesCourseTabFragment.this.c != null) {
                    LogUtil.a("SeriesCourseTabFragment", "mGlobalScrollView != null");
                    SeriesCourseTabFragment.this.c.setEnabled(true);
                    SeriesCourseTabFragment.this.c.setScrollViewVerticalDirectionEvent(true);
                    SeriesCourseTabFragment.this.c.setIsForceScrollListener(true);
                    SeriesCourseTabFragment.this.c.setScrollViewIntercepts(true);
                    return;
                }
                LogUtil.b("SeriesCourseTabFragment", "mGlobalScrollView is null");
            }
        }
    };

    public static SeriesCourseTabFragment c(List<SleepSeriesCourseBean> list) {
        LogUtil.a("SeriesCourseTabFragment", "SeriesCourseTabFragment newInstance");
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("packageInfo", (ArrayList) list);
        SeriesCourseTabFragment seriesCourseTabFragment = new SeriesCourseTabFragment();
        seriesCourseTabFragment.setArguments(bundle);
        return seriesCourseTabFragment;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        LogUtil.a("SeriesCourseTabFragment", "onCreate");
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments == null) {
            ReleaseLogUtil.c("SeriesCourseTabFragment", "bundle is null");
        } else {
            this.h = arguments.getParcelableArrayList("packageInfo");
            this.d = getContext();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        LogUtil.a("SeriesCourseTabFragment", "onCreateView");
        if (this.j == null) {
            this.j = layoutInflater.inflate(R.layout.series_course_tab_fragment_layout, viewGroup, false);
            d();
        }
        b(d(this.h));
        return this.j;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        LogUtil.a("SeriesCourseTabFragment", "onResume");
        super.onResume();
        VipApi vipApi = (VipApi) Services.a("vip", VipApi.class);
        if (vipApi == null) {
            ReleaseLogUtil.c("SeriesCourseTabFragment", "VipApi is null");
        } else {
            vipApi.getVipInfo(this.i);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LogUtil.a("SeriesCourseTabFragment", "onConfigurationChanged");
        this.b.setLayoutManager(new LinearLayoutManager(this.d, 1, false));
    }

    private void d() {
        LogUtil.a("SeriesCourseTabFragment", "initView");
        RecyclerView recyclerView = (RecyclerView) this.j.findViewById(R.id.series_tab_recycle_view);
        this.b = recyclerView;
        recyclerView.setOnScrollListener(this.e);
        this.b.setNestedScrollingEnabled(false);
        this.b.setLayoutManager(new LinearLayoutManager(this.d, 1, false));
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen._2131363794_res_0x7f0a07d2);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen._2131362009_res_0x7f0a00d9);
        int dimensionPixelSize3 = getResources().getDimensionPixelSize(R.dimen._2131362007_res_0x7f0a00d7);
        int dimensionPixelSize4 = getResources().getDimensionPixelSize(R.dimen._2131362008_res_0x7f0a00d8);
        this.b.addItemDecoration(new RecyclerItemDecoration(0, dimensionPixelSize4, new int[]{dimensionPixelSize2, dimensionPixelSize, dimensionPixelSize3, dimensionPixelSize4}));
        if (this.f9803a == null) {
            this.f9803a = new SleepPopularCoursesAdapter(this.d);
        }
        this.b.setAdapter(this.f9803a);
    }

    private void b(String str) {
        LogUtil.a("SeriesCourseTabFragment", "queryAudiosPackage");
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            ReleaseLogUtil.c("SeriesCourseTabFragment", "mCourseApi is null");
        } else {
            courseApi.queryAudiosPackageBySeriesId(str, new UiCallback<List<SleepAudioPackage>>() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseTabFragment.4
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str2) {
                    LogUtil.b("SeriesCourseTabFragment", "query series audio course failed, errorCode: ", Integer.valueOf(i), " errorInfo: ", str2);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<SleepAudioPackage> list) {
                    if (SeriesCourseTabFragment.this.h == null) {
                        ReleaseLogUtil.c("SeriesCourseTabFragment", "mSeriesBeans is null");
                        return;
                    }
                    if (!koq.b(list)) {
                        if (list.size() != SeriesCourseTabFragment.this.h.size()) {
                            ReleaseLogUtil.c("SeriesCourseTabFragment", "query series audio course request failed, packageList not same size with ", "mSeriesBeans! packageList size = ", Integer.valueOf(list.size()), ", mSeriesBeans size = ", Integer.valueOf(SeriesCourseTabFragment.this.h.size()));
                            return;
                        }
                        ReleaseLogUtil.e("SeriesCourseTabFragment", "query series audio course request successful");
                        LogUtil.a("SeriesCourseTabFragment", "packageList: ", list);
                        for (int i = 0; i < list.size(); i++) {
                            ((SleepSeriesCourseBean) SeriesCourseTabFragment.this.h.get(i)).setSleepAudioPackage(list.get(i));
                        }
                        if (SeriesCourseTabFragment.this.f9803a != null) {
                            SeriesCourseTabFragment.this.f9803a.d(SeriesCourseTabFragment.this.h);
                            return;
                        }
                        return;
                    }
                    ReleaseLogUtil.c("SeriesCourseTabFragment", "query series audio course request failed, packageList is empty!");
                }
            });
        }
    }

    private String d(List<SleepSeriesCourseBean> list) {
        LogUtil.a("SeriesCourseTabFragment", "getCourseId");
        StringBuilder sb = new StringBuilder();
        sb.append(list.get(0).getDataId());
        sb.append(",");
        int size = list.size();
        for (int i = 1; i < size; i++) {
            SleepSeriesCourseBean sleepSeriesCourseBean = list.get(i);
            if (sleepSeriesCourseBean == null) {
                ReleaseLogUtil.c("SeriesCourseTabFragment", "sleepSeriesCourseBean is null");
                return "";
            }
            sb.append(sleepSeriesCourseBean.getDataId());
            sb.append(",");
        }
        String sb2 = sb.toString();
        return sb2.endsWith(",") ? sb2.substring(0, sb2.length() - 1) : sb2;
    }
}
