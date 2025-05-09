package com.huawei.ui.main.stories.health.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.audio.SleepAudioGroup;
import com.huawei.pluginfitnessadvice.audio.SleepAudioInfo;
import com.huawei.pluginfitnessadvice.audio.SleepAudioSeries;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.bkz;
import defpackage.nrt;
import defpackage.trg;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class SleepSeriesCourseListAdapter extends RecyclerView.Adapter<SeriesListViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private SleepAudioSeries f10137a;
    private Context d;
    private int e;
    private List<SleepAudioGroup> c = new ArrayList();
    private boolean b = false;

    public SleepSeriesCourseListAdapter(Context context) {
        this.d = context;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: dDd_, reason: merged with bridge method [inline-methods] */
    public SeriesListViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new SeriesListViewHolder(LayoutInflater.from(this.d).inflate(R.layout.sleep_series_course_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(SeriesListViewHolder seriesListViewHolder, int i) {
        if (trg.a(this.c, i)) {
            LogUtil.a("SleepSeriesCourseListAdapter", "invalid position: ", Integer.valueOf(i));
            return;
        }
        LogUtil.a("SleepSeriesCourseListAdapter", "onBindViewHolder: ", Integer.valueOf(i));
        int i2 = this.e;
        if (i2 == 1) {
            seriesListViewHolder.h.setVisibility(8);
            SleepAudioGroup sleepAudioGroup = this.c.get(0);
            if (sleepAudioGroup == null) {
                LogUtil.a("SleepSeriesCourseListAdapter", "group is null");
                return;
            }
            List<SleepAudioInfo> sleepAudioInfoList = sleepAudioGroup.getSleepAudioInfoList();
            if (bkz.e(sleepAudioInfoList)) {
                LogUtil.a("SleepSeriesCourseListAdapter", "courseList is null");
                return;
            }
            SleepCourseItemListAdapter sleepCourseItemListAdapter = new SleepCourseItemListAdapter(this.d, sleepAudioInfoList, this.f10137a, this.b);
            seriesListViewHolder.e.setNestedScrollingEnabled(false);
            seriesListViewHolder.e.a(false);
            seriesListViewHolder.e.setLayoutManager(new LinearLayoutManager(this.d, 1, false));
            seriesListViewHolder.e.setAdapter(sleepCourseItemListAdapter);
            return;
        }
        if (i2 == 2) {
            SleepAudioGroup sleepAudioGroup2 = this.c.get(i);
            if (sleepAudioGroup2 == null) {
                LogUtil.a("SleepSeriesCourseListAdapter", "group is null");
                return;
            }
            seriesListViewHolder.f10139a.setText(sleepAudioGroup2.getFirstTitle());
            seriesListViewHolder.d.setOnClickListener(new a(seriesListViewHolder));
            SleepCourseItemListAdapter sleepCourseItemListAdapter2 = new SleepCourseItemListAdapter(this.d, sleepAudioGroup2.getSleepAudioInfoList(), this.f10137a, this.b);
            seriesListViewHolder.e.setNestedScrollingEnabled(false);
            seriesListViewHolder.e.a(false);
            seriesListViewHolder.e.setLayoutManager(new LinearLayoutManager(this.d, 1, false));
            seriesListViewHolder.e.setAdapter(sleepCourseItemListAdapter2);
            return;
        }
        LogUtil.a("SleepSeriesCourseListAdapter", "mDisplayStyle is invalid");
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        int i = this.e;
        if (i == 1) {
            return 1;
        }
        if (i == 2) {
            return this.c.size();
        }
        return 0;
    }

    public void a(int i, List<SleepAudioGroup> list, SleepAudioSeries sleepAudioSeries) {
        if (bkz.e(list)) {
            LogUtil.a("SleepSeriesCourseListAdapter", "data list is empty");
            return;
        }
        this.e = i;
        this.f10137a = sleepAudioSeries;
        this.c.clear();
        int i2 = this.e;
        if (i2 == 1) {
            this.c.add(list.get(0));
        } else if (i2 == 2) {
            this.c.addAll(list);
        } else {
            LogUtil.a("SleepSeriesCourseListAdapter", "displayStyle is invalid");
            return;
        }
        notifyDataSetChanged();
    }

    public void a(final boolean z) {
        if (!HandlerExecutor.c()) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.main.stories.health.adapter.SleepSeriesCourseListAdapter.2
                @Override // java.lang.Runnable
                public void run() {
                    SleepSeriesCourseListAdapter.this.a(z);
                }
            });
            return;
        }
        LogUtil.a("SleepSeriesCourseListAdapter", "refreshVipInfo, isVip: ", Boolean.valueOf(z));
        if (this.b != z) {
            this.b = z;
            notifyDataSetChanged();
        }
    }

    class a implements View.OnClickListener {
        private SeriesListViewHolder d;
        private boolean e = true;

        public a(SeriesListViewHolder seriesListViewHolder) {
            this.d = seriesListViewHolder;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (this.e) {
                this.d.c.setScaleY(-1.0f);
                if (nrt.a(SleepSeriesCourseListAdapter.this.d)) {
                    this.d.c.setBackground(SleepSeriesCourseListAdapter.this.d.getResources().getDrawable(R.drawable._2131427549_res_0x7f0b00dd));
                } else {
                    this.d.c.setBackground(SleepSeriesCourseListAdapter.this.d.getResources().getDrawable(R.drawable._2131427887_res_0x7f0b022f));
                }
                this.d.b.setVisibility(8);
            } else {
                this.d.c.setScaleY(1.0f);
                if (nrt.a(SleepSeriesCourseListAdapter.this.d)) {
                    this.d.c.setBackground(SleepSeriesCourseListAdapter.this.d.getResources().getDrawable(R.drawable._2131427549_res_0x7f0b00dd));
                } else {
                    this.d.c.setBackground(SleepSeriesCourseListAdapter.this.d.getResources().getDrawable(R.drawable._2131427887_res_0x7f0b022f));
                }
                this.d.b.setVisibility(0);
            }
            this.e = !this.e;
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public static class SeriesListViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthTextView f10139a;
        private RelativeLayout b;
        private ImageView c;
        private LinearLayout d;
        private HealthRecycleView e;
        private LinearLayout h;

        public SeriesListViewHolder(View view) {
            super(view);
            this.h = (LinearLayout) view.findViewById(R.id.title_layout);
            this.f10139a = (HealthTextView) view.findViewById(R.id.series_title);
            this.c = (ImageView) view.findViewById(R.id.array_img);
            if (nrt.a(view.getContext())) {
                this.c.setBackground(view.getContext().getDrawable(R.drawable._2131427549_res_0x7f0b00dd));
            } else {
                this.c.setBackground(view.getContext().getDrawable(R.drawable._2131427887_res_0x7f0b022f));
            }
            this.d = (LinearLayout) view.findViewById(R.id.array_layout);
            this.b = (RelativeLayout) view.findViewById(R.id.course_recycle_view_layout);
            this.e = (HealthRecycleView) view.findViewById(R.id.course_recycle_view);
        }
    }
}
