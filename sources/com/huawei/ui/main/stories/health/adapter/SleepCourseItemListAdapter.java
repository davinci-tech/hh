package com.huawei.ui.main.stories.health.adapter;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.R;
import com.huawei.health.servicesui.R$string;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.audio.SleepAudioInfo;
import com.huawei.pluginfitnessadvice.audio.SleepAudioSeries;
import com.huawei.ui.commonui.divider.HealthDivider;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ffv;
import defpackage.nrf;
import defpackage.nrr;
import defpackage.nrt;
import defpackage.trg;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class SleepCourseItemListAdapter extends RecyclerView.Adapter<CourseViewHolder> {

    /* renamed from: a, reason: collision with root package name */
    private SleepAudioSeries f10126a;
    private boolean b;
    private Context d;
    private List<SleepAudioInfo> e;

    public SleepCourseItemListAdapter(Context context, List<SleepAudioInfo> list, SleepAudioSeries sleepAudioSeries, boolean z) {
        ArrayList arrayList = new ArrayList();
        this.e = arrayList;
        this.b = z;
        this.d = context;
        this.f10126a = sleepAudioSeries;
        arrayList.clear();
        this.e.addAll(list);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: dCK_, reason: merged with bridge method [inline-methods] */
    public CourseViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new CourseViewHolder(LayoutInflater.from(this.d).inflate(R.layout.sleep_course_item, viewGroup, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(CourseViewHolder courseViewHolder, int i) {
        if (trg.a(this.e, i)) {
            LogUtil.a("SleepCourseItemListAdapter", "invalid position: ", Integer.valueOf(i));
            return;
        }
        LogUtil.a("SleepCourseItemListAdapter", "onBindViewHolder: ", Integer.valueOf(i));
        SleepAudioInfo sleepAudioInfo = this.e.get(i);
        courseViewHolder.k.setText(sleepAudioInfo.getName());
        if (ffv.d(sleepAudioInfo)) {
            e(this.b, courseViewHolder, sleepAudioInfo);
        } else {
            c(courseViewHolder, ffv.a(sleepAudioInfo, this.f10126a));
        }
        courseViewHolder.d.setVisibility(8);
        String secondTitle = sleepAudioInfo.getSecondTitle();
        if (TextUtils.isEmpty(secondTitle)) {
            courseViewHolder.c.setVisibility(8);
        } else {
            courseViewHolder.c.setVisibility(0);
            courseViewHolder.c.setText(secondTitle);
        }
        String a2 = ffv.a(sleepAudioInfo.getAudioDuration());
        if (TextUtils.isEmpty(a2)) {
            courseViewHolder.f.setVisibility(8);
        } else {
            courseViewHolder.f.setVisibility(0);
            courseViewHolder.f.setText(a2);
        }
        String b = ffv.b(sleepAudioInfo.getAudioDuration(), sleepAudioInfo.getPlayRecord());
        if (TextUtils.isEmpty(b)) {
            courseViewHolder.h.setVisibility(8);
        } else {
            courseViewHolder.h.setVisibility(0);
            courseViewHolder.h.setText(b);
        }
        c(courseViewHolder, i);
    }

    private void c(CourseViewHolder courseViewHolder, String str) {
        if (TextUtils.isEmpty(str)) {
            courseViewHolder.b.setVisibility(8);
        } else {
            courseViewHolder.b.setVisibility(0);
            ffv.b(courseViewHolder.b, str);
        }
    }

    private void c(CourseViewHolder courseViewHolder, int i) {
        SleepAudioInfo sleepAudioInfo = this.e.get(i);
        if (ffv.d(sleepAudioInfo)) {
            if (!ffv.a(sleepAudioInfo)) {
                e(!this.b, courseViewHolder);
            } else {
                dCJ_(courseViewHolder.j);
            }
        } else if (ffv.b(sleepAudioInfo, this.f10126a)) {
            courseViewHolder.j.setBackground(this.d.getResources().getDrawable(R.drawable._2131427894_res_0x7f0b0236));
        } else {
            dCJ_(courseViewHolder.j);
        }
        String smallImage = sleepAudioInfo.getSmallImage();
        if (TextUtils.isEmpty(smallImage)) {
            courseViewHolder.e.setVisibility(8);
            a(courseViewHolder.f10130a, false);
        } else {
            courseViewHolder.e.setVisibility(0);
            a(courseViewHolder.f10130a, true);
            nrf.cIS_(courseViewHolder.e, smallImage, nrf.e, 0, R.drawable._2131427609_res_0x7f0b0119);
        }
        if (i == this.e.size() - 1) {
            courseViewHolder.f10130a.setVisibility(8);
        }
        courseViewHolder.g.setOnClickListener(dCI_(this.e.get(i)));
        courseViewHolder.i.setOnClickListener(dCH_(this.e.get(i)));
    }

    private void a(HealthDivider healthDivider, boolean z) {
        if (healthDivider.getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) healthDivider.getLayoutParams();
            if (z) {
                layoutParams.setMarginStart(nrr.e(this.d, 64.0f));
            } else {
                layoutParams.setMarginStart(nrr.e(this.d, 0.0f));
            }
            healthDivider.setLayoutParams(layoutParams);
        }
    }

    private void dCJ_(ImageView imageView) {
        if (nrt.a(this.d)) {
            imageView.setBackground(this.d.getResources().getDrawable(R.drawable._2131431079_res_0x7f0b0ea7));
        } else {
            imageView.setBackground(this.d.getResources().getDrawable(R.drawable._2131427896_res_0x7f0b0238));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final boolean z, final CourseViewHolder courseViewHolder) {
        if (!HandlerExecutor.c()) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.main.stories.health.adapter.SleepCourseItemListAdapter.5
                @Override // java.lang.Runnable
                public void run() {
                    SleepCourseItemListAdapter.this.e(z, courseViewHolder);
                }
            });
            return;
        }
        LogUtil.a("SleepCourseItemListAdapter", "updateLockInner, isLock: ", Boolean.valueOf(z));
        if (z) {
            courseViewHolder.j.setBackground(this.d.getResources().getDrawable(R.drawable._2131427894_res_0x7f0b0236));
        } else {
            dCJ_(courseViewHolder.j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final boolean z, final CourseViewHolder courseViewHolder, final SleepAudioInfo sleepAudioInfo) {
        if (!HandlerExecutor.c()) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.main.stories.health.adapter.SleepCourseItemListAdapter.3
                @Override // java.lang.Runnable
                public void run() {
                    SleepCourseItemListAdapter.this.e(z, courseViewHolder, sleepAudioInfo);
                }
            });
            return;
        }
        LogUtil.a("SleepCourseItemListAdapter", "updateTag1Inner, isVip: ", Boolean.valueOf(z));
        if (!z) {
            if (ffv.a(sleepAudioInfo)) {
                c(courseViewHolder, BaseApplication.getContext().getResources().getString(R$string.IDS_sleep_decompression_try_listening));
                return;
            } else {
                c(courseViewHolder, BaseApplication.getContext().getResources().getString(R$string.IDS_vip));
                return;
            }
        }
        c(courseViewHolder, BaseApplication.getContext().getResources().getString(R$string.IDS_vip));
    }

    private View.OnClickListener dCH_(final SleepAudioInfo sleepAudioInfo) {
        return new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.adapter.SleepCourseItemListAdapter.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("SleepCourseItemListAdapter", "item is clicked");
                Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.sleeping-music?h5pro=true&urlType=4&pName=com.huawei.health&path=AudioDetailSeries&isImmerse=true&id=" + sleepAudioInfo.getId() + "&courseId=" + SleepCourseItemListAdapter.this.f10126a.getId() + "&from=" + SleepCourseItemListAdapter.this.f10126a.getFrom() + SleepCourseItemListAdapter.this.b()));
                intent.addCategory("android.intent.category.DEFAULT").addFlags(268435456);
                try {
                    SleepCourseItemListAdapter.this.d.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    LogUtil.b("SleepCourseItemListAdapter", "ActivityNotFoundException", e.getMessage());
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String b() {
        return "&pullfrom=" + this.f10126a.getPullFrom() + "&resourceId=" + this.f10126a.getResourceId() + "&resourceName=" + this.f10126a.getResourceName();
    }

    private View.OnClickListener dCI_(final SleepAudioInfo sleepAudioInfo) {
        return new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.adapter.SleepCourseItemListAdapter.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("SleepCourseItemListAdapter", "Play icon is clicked");
                Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("huaweihealth://huaweihealth.app/openwith?address=com.huawei.health.h5.sleeping-music?h5pro=true&urlType=4&pName=com.huawei.health&path=AudioDetailSeries&isImmerse=true&id=" + sleepAudioInfo.getId() + "&courseId=" + SleepCourseItemListAdapter.this.f10126a.getId() + "&from=" + SleepCourseItemListAdapter.this.f10126a.getFrom() + "&playing=true" + SleepCourseItemListAdapter.this.b()));
                intent.addCategory("android.intent.category.DEFAULT").addFlags(268435456);
                try {
                    SleepCourseItemListAdapter.this.d.startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    LogUtil.b("SleepCourseItemListAdapter", "ActivityNotFoundException", e.getMessage());
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        };
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.e.size();
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder {

        /* renamed from: a, reason: collision with root package name */
        private HealthDivider f10130a;
        private HealthTextView b;
        private HealthTextView c;
        private HealthTextView d;
        private ImageView e;
        private HealthTextView f;
        private RelativeLayout g;
        private HealthTextView h;
        private LinearLayout i;
        private ImageView j;
        private HealthTextView k;

        public CourseViewHolder(View view) {
            super(view);
            this.k = (HealthTextView) view.findViewById(R.id.course_title);
            this.e = (ImageView) view.findViewById(R.id.course_image);
            this.b = (HealthTextView) view.findViewById(R.id.course_tag1);
            this.d = (HealthTextView) view.findViewById(R.id.course_tag2);
            this.c = (HealthTextView) view.findViewById(R.id.day_num);
            this.f = (HealthTextView) view.findViewById(R.id.minute);
            this.h = (HealthTextView) view.findViewById(R.id.playRecord);
            this.j = (ImageView) view.findViewById(R.id.play_image);
            this.g = (RelativeLayout) view.findViewById(R.id.play_icon_layout);
            this.f10130a = (HealthDivider) view.findViewById(R.id.divider);
            this.i = (LinearLayout) view.findViewById(R.id.recyclerview_item);
        }
    }
}
