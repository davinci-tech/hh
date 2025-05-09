package com.huawei.health.suggestion.ui.fitness.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.BadParcelableException;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.view.FilterFlowLayout;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.Attribute;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.bottomsheet.HealthBottomSheet;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.scrollview.HealthScrollView;
import com.huawei.uikit.hwbottomsheet.widget.HwBottomSheet;
import defpackage.koq;
import defpackage.nsn;
import health.compact.a.LogAnonymous;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class CourseMoreFilterActivity extends Activity implements View.OnClickListener {
    private FilterFlowLayout b;
    private HealthBottomSheet c;
    private List<Attribute> d;
    private List<Attribute> f;
    private Intent h;
    private FilterFlowLayout j;
    private List<Attribute> k;
    private FilterFlowLayout n;

    /* renamed from: a, reason: collision with root package name */
    private List<Integer> f3078a = new ArrayList(10);
    private List<Integer> m = new ArrayList(10);
    private List<Integer> i = new ArrayList(10);
    private FilterFlowLayout.OnCheckChangeListener o = new FilterFlowLayout.OnCheckChangeListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.CourseMoreFilterActivity.1
        @Override // com.huawei.health.suggestion.ui.view.FilterFlowLayout.OnCheckChangeListener
        public void onChecked(int i) {
            CourseMoreFilterActivity.this.f3078a.add(Integer.valueOf(i));
        }

        @Override // com.huawei.health.suggestion.ui.view.FilterFlowLayout.OnCheckChangeListener
        public void onCancelCheck(int i) {
            CourseMoreFilterActivity courseMoreFilterActivity = CourseMoreFilterActivity.this;
            courseMoreFilterActivity.b(i, courseMoreFilterActivity.f3078a);
        }
    };
    private FilterFlowLayout.OnCheckChangeListener g = new FilterFlowLayout.OnCheckChangeListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.CourseMoreFilterActivity.4
        @Override // com.huawei.health.suggestion.ui.view.FilterFlowLayout.OnCheckChangeListener
        public void onChecked(int i) {
            CourseMoreFilterActivity.this.m.add(Integer.valueOf(i));
        }

        @Override // com.huawei.health.suggestion.ui.view.FilterFlowLayout.OnCheckChangeListener
        public void onCancelCheck(int i) {
            CourseMoreFilterActivity courseMoreFilterActivity = CourseMoreFilterActivity.this;
            courseMoreFilterActivity.b(i, courseMoreFilterActivity.m);
        }
    };
    private FilterFlowLayout.OnCheckChangeListener e = new FilterFlowLayout.OnCheckChangeListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.CourseMoreFilterActivity.2
        @Override // com.huawei.health.suggestion.ui.view.FilterFlowLayout.OnCheckChangeListener
        public void onChecked(int i) {
            CourseMoreFilterActivity.this.i.add(Integer.valueOf(i));
        }

        @Override // com.huawei.health.suggestion.ui.view.FilterFlowLayout.OnCheckChangeListener
        public void onCancelCheck(int i) {
            CourseMoreFilterActivity courseMoreFilterActivity = CourseMoreFilterActivity.this;
            courseMoreFilterActivity.b(i, courseMoreFilterActivity.i);
        }
    };

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        BaseActivity.setDisplaySideMode(this);
        setContentView(R.layout.activity_course_more_filter);
        d();
        b();
    }

    private void b() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("Suggestion_CourseMoreFilterActivity", "initData() getIntent is null");
            return;
        }
        Bundle bundleExtra = intent.getBundleExtra("RESULT_KEY_SELECTED_DATA");
        if (bundleExtra == null) {
            LogUtil.h("Suggestion_CourseMoreFilterActivity", "initData() bundle is null");
            return;
        }
        try {
            this.d = bundleExtra.getParcelableArrayList("more_filter_hot");
            this.k = bundleExtra.getParcelableArrayList("more_filter_parts");
            this.f = bundleExtra.getParcelableArrayList("more_filter_equipments");
            ArrayList<Integer> integerArrayList = bundleExtra.getIntegerArrayList("more_filter_hot_selected");
            if (koq.c(integerArrayList)) {
                this.f3078a.addAll(integerArrayList);
            }
            ArrayList<Integer> integerArrayList2 = bundleExtra.getIntegerArrayList("more_filter_parts_selected");
            if (koq.c(integerArrayList2)) {
                this.m.addAll(integerArrayList2);
            }
            ArrayList<Integer> integerArrayList3 = bundleExtra.getIntegerArrayList("more_filter_equipments_selected");
            if (koq.c(integerArrayList3)) {
                this.i.addAll(integerArrayList3);
            }
        } catch (BadParcelableException e) {
            LogUtil.b("Suggestion_CourseMoreFilterActivity", "get intent data failed with BadParcelableException", LogAnonymous.b((Throwable) e));
        }
        g();
    }

    private void d() {
        HealthBottomSheet healthBottomSheet = (HealthBottomSheet) findViewById(R.id.sliding_layout);
        this.c = healthBottomSheet;
        healthBottomSheet.setIndicateSafeInsetsEnabled(true);
        this.c.setForceShowIndicateEnabled(false);
        this.c.setSheetHeight(0);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.title);
        healthTextView.setText(R.string._2130848521_res_0x7f022b09);
        if (nsn.r()) {
            healthTextView.setTextSize(0, getResources().getDimensionPixelSize(R.dimen._2131362996_res_0x7f0a04b4));
        }
        findViewById(R.id.content).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.CourseMoreFilterActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                CourseMoreFilterActivity.this.e();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.c.d(new HwBottomSheet.SheetSlideListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.CourseMoreFilterActivity.3
            @Override // com.huawei.uikit.hwbottomsheet.widget.HwBottomSheet.SheetSlideListener
            public void onSheetSlide(View view, float f) {
            }

            @Override // com.huawei.uikit.hwbottomsheet.widget.HwBottomSheet.SheetSlideListener
            public void onSheetStateChanged(View view, HwBottomSheet.SheetState sheetState, HwBottomSheet.SheetState sheetState2) {
                if (sheetState2 == HwBottomSheet.SheetState.COLLAPSED || sheetState2 == HwBottomSheet.SheetState.HIDDEN) {
                    CourseMoreFilterActivity courseMoreFilterActivity = CourseMoreFilterActivity.this;
                    courseMoreFilterActivity.setResult(-1, courseMoreFilterActivity.h);
                    CourseMoreFilterActivity.this.finish();
                }
            }
        });
        ((HealthScrollView) findViewById(R.id.health_dialog_scrollview)).setScrollViewVerticalDirectionEvent(true);
        this.b = (FilterFlowLayout) findViewById(R.id.workout_filter_classify_group);
        this.n = (FilterFlowLayout) findViewById(R.id.workout_filter_training_points_group);
        this.j = (FilterFlowLayout) findViewById(R.id.workout_filter_equipments_group);
        ((HealthButton) findViewById(R.id.recom_popu_dialog_confirm)).setOnClickListener(this);
        ((HealthButton) findViewById(R.id.recom_popu_dialog_reset)).setOnClickListener(this);
        findViewById(R.id.content).setOnClickListener(this);
        int c = c();
        this.c.setHeightGap(c);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.drag_View);
        viewGroup.setPadding(viewGroup.getPaddingLeft(), viewGroup.getPaddingTop(), viewGroup.getPaddingRight(), c);
    }

    private int c() {
        int j = nsn.j();
        return (int) ((j - ((j - r1) * 0.8f)) - nsn.r(this));
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (this.c.getSheetState() == HwBottomSheet.SheetState.EXPANDED) {
            e();
        } else {
            super.onBackPressed();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (nsn.a(500)) {
            LogUtil.h("Suggestion_CourseMoreFilterActivity", "onClick() view click too fast.");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (id == R.id.content || id == R.id.recom_popup_dialog_space) {
            e();
        }
        if (id == R.id.recom_popu_dialog_reset) {
            LogUtil.c("Suggestion_CourseMoreFilterActivity", " click reset");
            this.f3078a.clear();
            this.m.clear();
            this.i.clear();
            g();
        }
        if (id == R.id.recom_popu_dialog_confirm) {
            e();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a() {
        this.h = new Intent();
        Bundle bundle = new Bundle();
        bundle.putIntegerArrayList("more_filter_hot", new ArrayList<>(this.f3078a));
        bundle.putIntegerArrayList("more_filter_parts", new ArrayList<>(this.m));
        bundle.putIntegerArrayList("more_filter_equipments", new ArrayList<>(this.i));
        this.h.putExtra("RESULT_KEY_SELECTED_DATA", bundle);
    }

    private void g() {
        this.b.d(this, this.d, this.f3078a);
        this.b.setOnCheckedChangeListener(this.o);
        this.n.d(this, this.k, this.m);
        this.n.setOnCheckedChangeListener(this.g);
        this.j.d(this, this.f, this.i);
        this.j.setOnCheckedChangeListener(this.e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, List<Integer> list) {
        if (koq.b(list)) {
            LogUtil.h("Suggestion_CourseMoreFilterActivity", "setCancelCheck() status is null");
            return;
        }
        int indexOf = list.indexOf(Integer.valueOf(i));
        LogUtil.c("Suggestion_CourseMoreFilterActivity", "setCancelCheck() => index : ", Integer.valueOf(indexOf));
        if (koq.d(list, indexOf)) {
            list.remove(indexOf);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        a();
        setResult(-1, this.h);
        finish();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
