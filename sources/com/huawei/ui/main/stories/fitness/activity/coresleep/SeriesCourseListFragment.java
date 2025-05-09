package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.Context;
import android.content.res.Configuration;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.health.vip.api.VipApi;
import com.huawei.health.vip.api.VipCallback;
import com.huawei.health.vip.datatypes.UserMemberInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.audio.SleepAudioGroup;
import com.huawei.pluginfitnessadvice.audio.SleepAudioPackage;
import com.huawei.pluginfitnessadvice.audio.SleepAudioSeries;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.main.stories.health.adapter.SleepSeriesCourseListAdapter;
import defpackage.gpn;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class SeriesCourseListFragment extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private List<SleepAudioGroup> f9799a = new ArrayList();
    private SleepAudioPackage b;
    private Context c;
    private View d;
    private SleepSeriesCourseListAdapter e;
    private RecyclerView j;

    static class d implements VipCallback {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<SeriesCourseListFragment> f9800a;

        private d(SeriesCourseListFragment seriesCourseListFragment) {
            this.f9800a = new WeakReference<>(seriesCourseListFragment);
        }

        @Override // com.huawei.health.vip.api.VipCallback
        public void onSuccess(Object obj) {
            UserMemberInfo userMemberInfo;
            SeriesCourseListFragment seriesCourseListFragment = this.f9800a.get();
            if (seriesCourseListFragment == null) {
                return;
            }
            SleepSeriesCourseListAdapter sleepSeriesCourseListAdapter = seriesCourseListFragment.e;
            if (obj == null) {
                LogUtil.a("SeriesCourseListFragment", "getVipInfo onSuccess result is null");
                sleepSeriesCourseListAdapter.a(false);
                return;
            }
            if (obj instanceof UserMemberInfo) {
                userMemberInfo = (UserMemberInfo) obj;
                LogUtil.a("SeriesCourseListFragment", "memberInfo: ", userMemberInfo.toString());
            } else {
                userMemberInfo = null;
            }
            if (userMemberInfo == null || userMemberInfo.getMemberFlag() != 1) {
                LogUtil.h("SeriesCourseListFragment", "TradeViewApi memberInfo == null or != VipConstants.MEMBER_FLAG_VIP");
                sleepSeriesCourseListAdapter.a(false);
            } else if (gpn.d(userMemberInfo)) {
                LogUtil.h("SeriesCourseListFragment", "isVipExpired");
                sleepSeriesCourseListAdapter.a(false);
            } else {
                sleepSeriesCourseListAdapter.a(true);
            }
        }

        @Override // com.huawei.health.vip.api.VipCallback
        public void onFailure(int i, String str) {
            SeriesCourseListFragment seriesCourseListFragment = this.f9800a.get();
            if (seriesCourseListFragment == null) {
                return;
            }
            SleepSeriesCourseListAdapter sleepSeriesCourseListAdapter = seriesCourseListFragment.e;
            LogUtil.h("SeriesCourseListFragment", "getVipInfo errorCode=", Integer.valueOf(i), " errMsg=", str);
            sleepSeriesCourseListAdapter.a(false);
        }
    }

    public static SeriesCourseListFragment c(String str, SleepAudioPackage sleepAudioPackage) {
        Bundle bundle = new Bundle();
        bundle.putString("topicId", str);
        bundle.putParcelable("packageInfo", sleepAudioPackage);
        SeriesCourseListFragment seriesCourseListFragment = new SeriesCourseListFragment();
        seriesCourseListFragment.setArguments(bundle);
        return seriesCourseListFragment;
    }

    public void e(SleepAudioPackage sleepAudioPackage) {
        this.b = sleepAudioPackage;
        d();
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            try {
                this.b = (SleepAudioPackage) arguments.getParcelable("packageInfo");
            } catch (BadParcelableException unused) {
                health.compact.a.LogUtil.e("SeriesCourseListFragment", "BadParcelableException");
            }
        }
        this.c = getContext();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.d == null) {
            this.d = layoutInflater.inflate(R.layout.series_course_list_fragment_layout, viewGroup, false);
            b();
        }
        d();
        return this.d;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        VipApi vipApi = (VipApi) Services.a("vip", VipApi.class);
        if (vipApi == null) {
            LogUtil.h("SeriesCourseListFragment", "refreshVipStateData VipApi is null");
        } else {
            vipApi.getVipInfo(new d());
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        LogUtil.a("SeriesCourseListFragment", "onConfigurationChanged");
        this.j.setLayoutManager(new LinearLayoutManager(this.c, 1, false));
    }

    private void b() {
        RecyclerView recyclerView = (RecyclerView) this.d.findViewById(R.id.series_recycle_view);
        this.j = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.c, 1, false));
        if (this.e == null) {
            this.e = new SleepSeriesCourseListAdapter(this.c);
        }
        this.j.setAdapter(this.e);
    }

    private void d() {
        SleepAudioPackage sleepAudioPackage = this.b;
        if (sleepAudioPackage == null) {
            LogUtil.a("SeriesCourseListFragment", "data is null");
            return;
        }
        SleepAudioSeries sleepAudioSeries = sleepAudioPackage.getSleepAudioSeries();
        if (sleepAudioSeries == null) {
            LogUtil.a("SeriesCourseListFragment", "seriesInfo is null");
            return;
        }
        int displayStyle = sleepAudioSeries.getDisplayStyle();
        this.f9799a.clear();
        if (displayStyle == 1) {
            SleepAudioGroup sleepAudioGroup = new SleepAudioGroup();
            sleepAudioGroup.setFirstTitle("");
            sleepAudioGroup.setSleepAudioInfoList(this.b.getSleepAudioInfoList());
            this.f9799a.add(sleepAudioGroup);
        } else if (displayStyle == 2) {
            this.f9799a.addAll(this.b.getSleepAudioGroupList());
        } else {
            LogUtil.a("SeriesCourseListFragment", "displayStyle is invalid");
            return;
        }
        SleepSeriesCourseListAdapter sleepSeriesCourseListAdapter = this.e;
        if (sleepSeriesCourseListAdapter != null) {
            sleepSeriesCourseListAdapter.a(displayStyle, this.f9799a, sleepAudioSeries);
        }
    }
}
