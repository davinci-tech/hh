package com.huawei.health.suggestion.ui.run.activity.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.fragment.app.FragmentActivity;
import com.google.gson.GsonBuilder;
import com.huawei.health.R;
import com.huawei.health.basesport.helper.HeartRateConfigHelper;
import com.huawei.health.suggestion.customizecourse.manager.adapter.CustomCourseDetailActionAdapter;
import com.huawei.health.suggestion.ui.run.activity.fragment.CustomCourseDetailFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import defpackage.asc;
import defpackage.ffg;
import defpackage.fhq;
import defpackage.ggs;
import defpackage.mmz;
import health.compact.a.CommonUtil;
import health.compact.a.utils.StringUtils;
import java.util.Map;

/* loaded from: classes4.dex */
public class CustomCourseDetailFragment extends BaseFragment {

    /* renamed from: a, reason: collision with root package name */
    private CustomCourseDetailActionAdapter f3324a;
    private ffg b;
    private FitWorkout c;
    private HeartRateConfigHelper d;
    private RelativeLayout e;

    public static CustomCourseDetailFragment b(FitWorkout fitWorkout) {
        Bundle bundle = new Bundle();
        bundle.putString("Suggestion_Course_FitWorkout", new GsonBuilder().serializeSpecialFloatingPointValues().create().toJson(fitWorkout).replaceAll("NaN", "0"));
        bundle.putInt("Suggestion_Defined_Type", fitWorkout.getCourseDefineType());
        CustomCourseDetailFragment customCourseDetailFragment = new CustomCourseDetailFragment();
        customCourseDetailFragment.setArguments(bundle);
        return customCourseDetailFragment;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Bundle arguments = getArguments();
        if (arguments != null) {
            String string = arguments.getString("Suggestion_Course_FitWorkout");
            if (StringUtils.i(string)) {
                this.c = (FitWorkout) new GsonBuilder().serializeSpecialFloatingPointValues().create().fromJson(CommonUtil.z(string.replaceAll("NaN", "0")), FitWorkout.class);
            }
        }
        View inflate = layoutInflater.inflate(R.layout.sug_fragment_costom_course_detail, viewGroup, false);
        ((HealthSubHeader) inflate.findViewById(R.id.sug_action_title)).setSubHeaderBackgroundColor(getResources().getColor(R.color._2131296971_res_0x7f0902cb));
        aKj_(inflate);
        aKi_(inflate);
        HealthRecycleView healthRecycleView = (HealthRecycleView) inflate.findViewById(R.id.sug_course_detail_recycle_view);
        healthRecycleView.setLayoutManager(new HealthLinearLayoutManager(getContext()));
        CustomCourseDetailActionAdapter customCourseDetailActionAdapter = new CustomCourseDetailActionAdapter(getContext());
        this.f3324a = customCourseDetailActionAdapter;
        healthRecycleView.setAdapter(customCourseDetailActionAdapter);
        healthRecycleView.a(false);
        healthRecycleView.d(false);
        d();
        return inflate;
    }

    private void d() {
        asc.e().b(new Runnable() { // from class: gcr
            @Override // java.lang.Runnable
            public final void run() {
                CustomCourseDetailFragment.this.b();
            }
        });
    }

    public /* synthetic */ void b() {
        LogUtil.a("Suggestion_CustomCourseDetailFragment", "initConfigInfo, init PaceZoneConfig - init HeartRateConfigHelper");
        this.b = fhq.a();
        this.d = new HeartRateConfigHelper(258, new HeartRateConfigHelper.OnConfigHelperListener() { // from class: gcp
            @Override // com.huawei.health.basesport.helper.HeartRateConfigHelper.OnConfigHelperListener
            public final void onInitComplete() {
                CustomCourseDetailFragment.this.c();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void c() {
        FitWorkout fitWorkout;
        CustomCourseDetailActionAdapter customCourseDetailActionAdapter = this.f3324a;
        if (customCourseDetailActionAdapter != null) {
            customCourseDetailActionAdapter.e(this.d, this.b);
            FragmentActivity activity = getActivity();
            if (activity == null || activity.isFinishing() || (fitWorkout = this.c) == null || fitWorkout.getCourseActions() == null) {
                return;
            }
            activity.runOnUiThread(new Runnable() { // from class: gcq
                @Override // java.lang.Runnable
                public final void run() {
                    CustomCourseDetailFragment.this.a();
                }
            });
        }
    }

    public /* synthetic */ void a() {
        this.f3324a.d(this.c);
    }

    private void aKi_(View view) {
        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.bind_device_tip);
        this.e = relativeLayout;
        relativeLayout.setOnClickListener(new View.OnClickListener() { // from class: gcs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                CustomCourseDetailFragment.this.aKk_(view2);
            }
        });
    }

    public /* synthetic */ void aKk_(View view) {
        this.e.setVisibility(8);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void aKj_(View view) {
        mmz mmzVar;
        HealthTextView healthTextView = (HealthTextView) view.findViewById(R.id.sug_course_description);
        if (this.c != null) {
            HealthTextView healthTextView2 = (HealthTextView) view.findViewById(R.id.custom_name);
            LogUtil.a("Suggestion_CustomCourseDetailFragment", "title:", this.c.acquireName());
            if (!TextUtils.isEmpty(this.c.acquireName())) {
                healthTextView2.setText(this.c.acquireName());
            }
            LogUtil.a("Suggestion_CustomCourseDetailFragment", "description:", this.c.acquireDescription());
            Map<String, mmz> d = ggs.d(this.c.acquireExtendSeaMap());
            String a2 = (d == null || (mmzVar = d.get("courseDesc")) == null) ? null : mmzVar.a();
            if (TextUtils.isEmpty(a2)) {
                a2 = this.c.acquireDescription();
            }
            if (!TextUtils.isEmpty(a2)) {
                healthTextView.setText(a2);
                return;
            }
        }
        healthTextView.setVisibility(8);
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
    }
}
