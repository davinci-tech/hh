package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.pluginfitnessadvice.audio.SleepAudioPackage;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.stories.health.adapter.SeriesSummaryAdapter;
import health.compact.a.LogUtil;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class SeriesCourseSummaryFragment extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private SeriesSummaryAdapter f9801a;
    private View b;
    private HealthCardView c;
    private HealthTextView d;
    private Context e;
    private SleepAudioPackage f;
    private RecyclerView g;
    private ImageView h;
    private HealthTextView i;
    private ImageView j;
    private HealthTextView n;

    public static SeriesCourseSummaryFragment d(String str, SleepAudioPackage sleepAudioPackage) {
        Bundle bundle = new Bundle();
        bundle.putString("topicId", str);
        bundle.putParcelable("packageInfo", sleepAudioPackage);
        SeriesCourseSummaryFragment seriesCourseSummaryFragment = new SeriesCourseSummaryFragment();
        seriesCourseSummaryFragment.setArguments(bundle);
        return seriesCourseSummaryFragment;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.c("SeriesCourseSummaryFragment", "onCreate");
        Bundle arguments = getArguments();
        if (arguments != null) {
            try {
                this.f = (SleepAudioPackage) arguments.getParcelable("packageInfo");
            } catch (BadParcelableException unused) {
                LogUtil.e("SeriesCourseSummaryFragment", "BadParcelableException");
            }
        }
        this.e = getContext();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.b == null) {
            this.b = layoutInflater.inflate(R.layout.series_course_summary_fragment_layout, viewGroup, false);
            b();
        }
        return this.b;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        com.huawei.hwlogsmodel.LogUtil.a("SeriesCourseSummaryFragment", "onConfigurationChanged");
        this.g.setLayoutManager(new LinearLayoutManager(this.e, 1, false));
    }

    private void b() {
        this.h = (ImageView) this.b.findViewById(R.id.head_img);
        this.g = (RecyclerView) this.b.findViewById(R.id.summary_recycle_view);
        HealthCardView healthCardView = (HealthCardView) this.b.findViewById(R.id.card_view);
        this.c = healthCardView;
        ViewGroup.LayoutParams layoutParams = healthCardView.getLayoutParams();
        if (layoutParams instanceof FrameLayout.LayoutParams) {
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
            Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
            int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e);
            int dimensionPixelSize2 = getResources().getDimensionPixelSize(R.dimen._2131362365_res_0x7f0a023d);
            layoutParams2.setMargins(dimensionPixelSize + ((Integer) safeRegionWidth.first).intValue(), getResources().getDimensionPixelSize(R.dimen._2131362366_res_0x7f0a023e), dimensionPixelSize2 + ((Integer) safeRegionWidth.second).intValue(), getResources().getDimensionPixelSize(R.dimen._2131362363_res_0x7f0a023b));
            this.c.setLayoutParams(layoutParams2);
        }
        this.j = (ImageView) this.b.findViewById(R.id.left_img);
        this.d = (HealthTextView) this.b.findViewById(R.id.course_from);
        this.n = (HealthTextView) this.b.findViewById(R.id.title);
        this.i = (HealthTextView) this.b.findViewById(R.id.des);
        this.g.setLayoutManager(new LinearLayoutManager(this.e, 1, false));
        if (this.f9801a == null) {
            this.f9801a = new SeriesSummaryAdapter(this.e);
        }
        this.g.setAdapter(this.f9801a);
    }

    public void e(final Map<String, Drawable> map, final SeriesCourseListActivity seriesCourseListActivity, final List<String> list) {
        if (!HandlerExecutor.b()) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.main.stories.fitness.activity.coresleep.SeriesCourseSummaryFragment.3
                @Override // java.lang.Runnable
                public void run() {
                    SeriesCourseSummaryFragment.this.e(map, seriesCourseListActivity, list);
                }
            });
            return;
        }
        LogUtil.c("SeriesCourseSummaryFragment", "merge");
        if (map == null || map.isEmpty()) {
            LogUtil.e("SeriesCourseSummaryFragment", "drawables is null or empty");
            return;
        }
        if (seriesCourseListActivity == null) {
            LogUtil.e("SeriesCourseSummaryFragment", "activity is null");
            return;
        }
        if (list == null || list.isEmpty()) {
            LogUtil.e("SeriesCourseSummaryFragment", "urlList is null or empty");
            return;
        }
        SeriesSummaryAdapter seriesSummaryAdapter = this.f9801a;
        if (seriesSummaryAdapter == null) {
            LogUtil.e("SeriesCourseSummaryFragment", "mAdapter is null");
        } else {
            seriesSummaryAdapter.b(map, seriesCourseListActivity, list);
        }
    }
}
