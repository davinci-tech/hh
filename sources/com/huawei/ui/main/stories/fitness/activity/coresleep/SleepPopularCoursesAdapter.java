package com.huawei.ui.main.stories.fitness.activity.coresleep;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.audio.SleepAudioGroup;
import com.huawei.pluginfitnessadvice.audio.SleepAudioInfo;
import com.huawei.pluginfitnessadvice.audio.SleepAudioPackage;
import com.huawei.pluginfitnessadvice.audio.SleepAudioSeries;
import com.huawei.ui.commonui.cardview.HealthCardView;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.coresleep.SleepPopularCoursesAdapter;
import com.huawei.ui.main.stories.fitness.activity.coresleep.model.SleepSeriesCourseBean;
import defpackage.ffv;
import defpackage.jdw;
import defpackage.koq;
import defpackage.nrf;
import defpackage.nsy;
import defpackage.trg;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class SleepPopularCoursesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int c = (int) BaseApplication.getContext().getResources().getDimension(R.dimen._2131363122_res_0x7f0a0532);

    /* renamed from: a, reason: collision with root package name */
    private final Context f9827a;
    private boolean b;
    private final List<SleepSeriesCourseBean> d = new ArrayList();
    private final Handler e = new Handler();
    private String g;
    private String j;

    public SleepPopularCoursesAdapter(Context context) {
        if (context == null) {
            this.f9827a = BaseApplication.getContext();
        } else {
            this.f9827a = context;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(this.f9827a).inflate(R.layout.sleep_popular_courses_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        if (!(viewHolder instanceof MyViewHolder)) {
            LogUtil.b("SleepPopularCoursesAdapter", "onBindViewHolder holder not instanceof MyViewHolder");
            return;
        }
        MyViewHolder myViewHolder = (MyViewHolder) viewHolder;
        c(myViewHolder, i);
        a(myViewHolder, i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (koq.b(this.d)) {
            return 0;
        }
        return this.d.size();
    }

    private void c(MyViewHolder myViewHolder, int i) {
        LogUtil.a("SleepPopularCoursesAdapter", "parseAndBindMarketingDataOnView");
        if (koq.b(this.d)) {
            LogUtil.b("SleepPopularCoursesAdapter", "mGridContents is null");
            return;
        }
        if (trg.a(this.d, i)) {
            LogUtil.b("SleepPopularCoursesAdapter", "invalid position: ", Integer.valueOf(i));
            return;
        }
        LogUtil.a("SleepPopularCoursesAdapter", "onBindViewHolder: ", Integer.valueOf(i));
        final SleepSeriesCourseBean sleepSeriesCourseBean = this.d.get(i);
        if (sleepSeriesCourseBean == null) {
            LogUtil.b("SleepPopularCoursesAdapter", "sleepSeriesCourseBean is null");
            return;
        }
        String theme = sleepSeriesCourseBean.getTheme();
        String desc = sleepSeriesCourseBean.getDesc();
        String pictureUrl = sleepSeriesCourseBean.getPictureUrl();
        this.g = sleepSeriesCourseBean.getPrice();
        if (pictureUrl != null) {
            if (!TextUtils.isEmpty(pictureUrl)) {
                nrf.cIS_(myViewHolder.b, pictureUrl, c, 0, R.drawable._2131431376_res_0x7f0b0fd0);
            } else {
                nrf.cIM_(R.drawable._2131431376_res_0x7f0b0fd0, myViewHolder.b, c);
            }
        }
        myViewHolder.f9828a.setText(theme);
        myViewHolder.d.setText(desc);
        myViewHolder.c.setOnClickListener(new View.OnClickListener() { // from class: pol
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SleepPopularCoursesAdapter.this.dro_(sleepSeriesCourseBean, view);
            }
        });
    }

    public /* synthetic */ void dro_(SleepSeriesCourseBean sleepSeriesCourseBean, View view) {
        String linkValue = sleepSeriesCourseBean.getLinkValue();
        if (linkValue == null) {
            LogUtil.b("SleepPopularCoursesAdapter", "url is null");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(linkValue));
        intent.setPackage(this.f9827a.getPackageName());
        intent.setFlags(268435456);
        jdw.bGh_(intent, this.f9827a);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a(MyViewHolder myViewHolder, int i) {
        LogUtil.a("SleepPopularCoursesAdapter", "parseAndBindAudioDataOnView");
        if (koq.b(this.d)) {
            LogUtil.b("SleepPopularCoursesAdapter", "mGridContents is null");
            return;
        }
        if (trg.a(this.d, i)) {
            LogUtil.b("SleepPopularCoursesAdapter", "invalid position: ", Integer.valueOf(i));
            return;
        }
        if (this.d.get(i) == null) {
            LogUtil.b("SleepPopularCoursesAdapter", "mGridContents.get(position) is null");
            return;
        }
        SleepAudioPackage sleepAudioPackage = this.d.get(i).getSleepAudioPackage();
        if (sleepAudioPackage == null) {
            LogUtil.b("SleepPopularCoursesAdapter", "parseAndBindAudioDataOnView failed sleepAudioPackage is null!");
            return;
        }
        SleepAudioSeries sleepAudioSeries = sleepAudioPackage.getSleepAudioSeries();
        if (sleepAudioSeries == null) {
            LogUtil.b("SleepPopularCoursesAdapter", "sleepAudio is null");
            return;
        }
        String b = ffv.b(sleepAudioSeries.getHeatCount());
        LogUtil.a("SleepPopularCoursesAdapter", "count", b);
        if (TextUtils.isEmpty(b)) {
            myViewHolder.e.setVisibility(8);
        } else {
            myViewHolder.e.setVisibility(0);
            myViewHolder.e.setText(b);
        }
        String e = ffv.e(sleepAudioSeries);
        this.j = e;
        d(myViewHolder, e);
        c(myViewHolder, sleepAudioPackage, i, sleepAudioSeries.getDisplayStyle());
    }

    private void d(MyViewHolder myViewHolder, String str) {
        LogUtil.a("SleepPopularCoursesAdapter", "textFirst: ", str);
        if (TextUtils.isEmpty(str)) {
            myViewHolder.f.setVisibility(8);
        } else {
            ffv.b(myViewHolder.f, str);
        }
    }

    private void b(MyViewHolder myViewHolder, String str) {
        LogUtil.a("SleepPopularCoursesAdapter", "textSecond: ", str);
        if (TextUtils.isEmpty(str)) {
            myViewHolder.j.setVisibility(8);
        } else {
            myViewHolder.j.setVisibility(0);
            myViewHolder.j.setText(str);
        }
    }

    private void c(MyViewHolder myViewHolder, SleepAudioPackage sleepAudioPackage, int i, int i2) {
        LogUtil.a("SleepPopularCoursesAdapter", "setTextSecondByGroup");
        if (i2 == 1) {
            LogUtil.a("SleepPopularCoursesAdapter", "displayStyle is no group");
            List<SleepAudioInfo> sleepAudioInfoList = sleepAudioPackage.getSleepAudioInfoList();
            if (koq.b(sleepAudioInfoList)) {
                LogUtil.b("SleepPopularCoursesAdapter", "sleepAudioInfoList is null");
                return;
            }
            if (trg.a(sleepAudioInfoList, sleepAudioInfoList.size() - 1)) {
                LogUtil.b("SleepPopularCoursesAdapter", "sleepAudioInfoList invalid position: ", Integer.valueOf(i));
                return;
            }
            String c2 = ffv.c(sleepAudioInfoList);
            LogUtil.a("SleepPopularCoursesAdapter", "textSecond no group: ", c2);
            b(myViewHolder, c2);
            d(myViewHolder, this.j, c2);
            return;
        }
        if (i2 == 2) {
            LogUtil.a("SleepPopularCoursesAdapter", "displayStyle is grouped");
            List<SleepAudioGroup> sleepAudioGroupList = sleepAudioPackage.getSleepAudioGroupList();
            if (koq.b(sleepAudioGroupList)) {
                LogUtil.b("SleepPopularCoursesAdapter", "sleepAudioInfoList is null");
                return;
            }
            if (trg.a(sleepAudioGroupList, sleepAudioGroupList.size() - 1)) {
                LogUtil.b("SleepPopularCoursesAdapter", "sleepAudioInfoList invalid position: ", Integer.valueOf(i));
                return;
            }
            String a2 = ffv.a(sleepAudioGroupList);
            LogUtil.a("SleepPopularCoursesAdapter", "textSecond group: ", a2);
            b(myViewHolder, a2);
            d(myViewHolder, this.j, a2);
            return;
        }
        LogUtil.b("SleepPopularCoursesAdapter", "displayStyle is invalid");
    }

    private void d(MyViewHolder myViewHolder, String str, String str2) {
        LogUtil.a("SleepPopularCoursesAdapter", "setTextVisibility");
        boolean equals = str.equals(BaseApplication.getContext().getResources().getString(R$string.IDS_sleep_decompression_free));
        boolean equals2 = str.equals(BaseApplication.getContext().getResources().getString(R$string.IDS_sleep_decompression_purchased));
        boolean equals3 = str.equals(BaseApplication.getContext().getResources().getString(com.huawei.health.servicesui.R$string.IDS_sug_series_course_pay));
        boolean equals4 = str2.equals(BaseApplication.getContext().getResources().getString(R$string.IDS_sleep_decompression_not_played));
        boolean equals5 = "".equals(str2);
        if (equals && !equals4 && !equals5) {
            LogUtil.a("SleepPopularCoursesAdapter", "free courses display second");
            myViewHolder.f.setVisibility(8);
            myViewHolder.j.setVisibility(0);
            return;
        }
        if (equals && equals4) {
            LogUtil.a("SleepPopularCoursesAdapter", "free courses display first");
            myViewHolder.f.setVisibility(0);
            myViewHolder.j.setVisibility(8);
        } else if (equals2 && !equals5) {
            LogUtil.a("SleepPopularCoursesAdapter", "purchased courses and display both");
            myViewHolder.f.setVisibility(0);
            myViewHolder.j.setVisibility(0);
        } else {
            if (equals3) {
                LogUtil.a("SleepPopularCoursesAdapter", "not purchased courses and display first");
                myViewHolder.f.setVisibility(0);
                myViewHolder.j.setVisibility(8);
                return;
            }
            a(myViewHolder, str, str2);
        }
    }

    private void a(MyViewHolder myViewHolder, String str, String str2) {
        boolean equals = str.equals(BaseApplication.getContext().getResources().getString(R$string.IDS_vip));
        boolean equals2 = str2.equals(BaseApplication.getContext().getResources().getString(R$string.IDS_sleep_decompression_not_played));
        boolean equals3 = "".equals(str2);
        if (!this.b) {
            LogUtil.a("SleepPopularCoursesAdapter", "user is not member, member courses and display first");
            myViewHolder.f.setVisibility(0);
            myViewHolder.j.setVisibility(8);
        } else if (equals && !equals2 && !equals3) {
            LogUtil.a("SleepPopularCoursesAdapter", "user is member, member courses and display both");
            myViewHolder.f.setVisibility(0);
            myViewHolder.j.setVisibility(0);
        } else if (equals && equals2) {
            LogUtil.a("SleepPopularCoursesAdapter", "user is member, member courses and display first");
            myViewHolder.f.setVisibility(0);
            myViewHolder.j.setVisibility(8);
        }
    }

    public void d(List<SleepSeriesCourseBean> list) {
        LogUtil.a("SleepPopularCoursesAdapter", "setData sleepSeriesCourseBeans = ", list);
        this.d.clear();
        if (list != null) {
            this.d.addAll(list);
        }
        notifyDataSetChanged();
    }

    public void c(final boolean z) {
        LogUtil.a("SleepPopularCoursesAdapter", "notifyVipStatusChanged");
        if (Looper.myLooper() == Looper.getMainLooper()) {
            b(z);
        } else {
            this.e.post(new Runnable() { // from class: poo
                @Override // java.lang.Runnable
                public final void run() {
                    SleepPopularCoursesAdapter.this.b(z);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void b(boolean z) {
        LogUtil.a("SleepPopularCoursesAdapter", "notifyVipStatusChangedInternal, isVip = ", Boolean.valueOf(z), ", mIsVip = ", Boolean.valueOf(this.b));
        boolean z2 = this.b != z;
        this.b = z;
        if (z2) {
            LogUtil.a("SleepPopularCoursesAdapter", "refresh");
            notifyDataSetChanged();
        }
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private final HealthTextView f9828a;
        private final ImageView b;
        private final HealthCardView c;
        private final HealthTextView d;
        private final HealthTextView e;
        private final HealthTextView f;
        private final HealthTextView j;

        public MyViewHolder(View view) {
            super(view);
            this.b = (ImageView) nsy.cMd_(view, R.id.course_recycle_item_picture);
            this.e = (HealthTextView) nsy.cMd_(view, R.id.sleep_course_heat_count);
            this.f9828a = (HealthTextView) nsy.cMd_(view, R.id.sleep_course_title);
            this.d = (HealthTextView) nsy.cMd_(view, R.id.sleep_course_sub_title);
            this.f = (HealthTextView) nsy.cMd_(view, R.id.course_play_or_not);
            this.j = (HealthTextView) nsy.cMd_(view, R.id.course_play_progress);
            this.c = (HealthCardView) nsy.cMd_(view, R.id.sleep_course_device_card);
        }
    }
}
