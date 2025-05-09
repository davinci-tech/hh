package com.huawei.health.suggestion.ui.polymericpage.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DefaultItemAnimator;
import com.google.gson.reflect.TypeToken;
import com.huawei.basefitnessadvice.model.FilterCondition;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.suggestion.ui.polymericpage.adapter.PolymericFilterMoreAdapter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.bottomsheet.HealthBottomSheet;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.uikit.hwbottomsheet.widget.HwBottomSheet;
import defpackage.koq;
import defpackage.moj;
import defpackage.nsn;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class PolymericMoreFilterActivity extends Activity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private HealthBottomSheet f3305a;
    private PolymericFilterMoreAdapter b;
    private HealthRecycleView d;
    private Intent e;
    private List<FilterCondition> c = new ArrayList();
    private Map<Long, List<Long>> i = new HashMap();

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        BaseActivity.setDisplaySideMode(this);
        setContentView(R.layout.activity_more_filter_course);
        b();
        e();
    }

    private void b() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("Suggestion_AggregateMoreFilterActivity", "initData() getIntent is null");
            return;
        }
        Bundle bundleExtra = intent.getBundleExtra("RESULT_KEY_SELECTED_DATA");
        if (bundleExtra == null) {
            LogUtil.b("Suggestion_AggregateMoreFilterActivity", "bundle null.", "RESULT_KEY_SELECTED_DATA");
            return;
        }
        this.c = bundleExtra.getParcelableArrayList("MORE_FILTER_CONDITIONS");
        String string = bundleExtra.getString("MORE_FILTER_SELECTED_DATA");
        if (koq.b(this.c)) {
            LogUtil.h("Suggestion_AggregateMoreFilterActivity", "mFilterConditions size: 0 selectedString:", string);
        } else {
            LogUtil.a("Suggestion_AggregateMoreFilterActivity", "mFilterConditions size:", Integer.valueOf(this.c.size()), "selectedString: ", string);
        }
        Map<Long, List<Long>> map = (Map) moj.b(string, new TypeToken<Map<Long, List<Long>>>() { // from class: com.huawei.health.suggestion.ui.polymericpage.activity.PolymericMoreFilterActivity.4
        }.getType());
        if (map != null) {
            this.i = map;
        } else {
            this.i = new HashMap();
        }
    }

    private void e() {
        HealthBottomSheet healthBottomSheet = (HealthBottomSheet) findViewById(R.id.sliding_layout);
        this.f3305a = healthBottomSheet;
        healthBottomSheet.setIndicateSafeInsetsEnabled(true);
        this.f3305a.setForceShowIndicateEnabled(false);
        this.f3305a.setSheetHeight(0);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.title);
        healthTextView.setText(R.string._2130848521_res_0x7f022b09);
        if (nsn.r()) {
            healthTextView.setTextSize(0, getResources().getDimensionPixelSize(R.dimen._2131362996_res_0x7f0a04b4));
        }
        findViewById(R.id.content).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.polymericpage.activity.PolymericMoreFilterActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PolymericMoreFilterActivity.this.a();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.f3305a.d(new HwBottomSheet.SheetSlideListener() { // from class: com.huawei.health.suggestion.ui.polymericpage.activity.PolymericMoreFilterActivity.5
            @Override // com.huawei.uikit.hwbottomsheet.widget.HwBottomSheet.SheetSlideListener
            public void onSheetSlide(View view, float f) {
            }

            @Override // com.huawei.uikit.hwbottomsheet.widget.HwBottomSheet.SheetSlideListener
            public void onSheetStateChanged(View view, HwBottomSheet.SheetState sheetState, HwBottomSheet.SheetState sheetState2) {
                if (sheetState2 == HwBottomSheet.SheetState.COLLAPSED || sheetState2 == HwBottomSheet.SheetState.HIDDEN) {
                    PolymericMoreFilterActivity polymericMoreFilterActivity = PolymericMoreFilterActivity.this;
                    polymericMoreFilterActivity.setResult(-1, polymericMoreFilterActivity.e);
                    PolymericMoreFilterActivity.this.finish();
                }
            }
        });
        this.d = (HealthRecycleView) findViewById(R.id.recycleview_filter_more);
        this.d.setLayoutManager(new HealthLinearLayoutManager(this, 1, false));
        PolymericFilterMoreAdapter polymericFilterMoreAdapter = new PolymericFilterMoreAdapter(this, this.c, this.i);
        this.b = polymericFilterMoreAdapter;
        this.d.setAdapter(polymericFilterMoreAdapter);
        this.d.setNestedScrollingEnabled(false);
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setSupportsChangeAnimations(false);
        this.d.setItemAnimator(defaultItemAnimator);
        ((HealthButton) findViewById(R.id.recom_popu_dialog_confirm)).setOnClickListener(this);
        ((HealthButton) findViewById(R.id.recom_popu_dialog_reset)).setOnClickListener(this);
        findViewById(R.id.content).setOnClickListener(this);
        int c = c();
        this.f3305a.setHeightGap(c);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.drag_View);
        viewGroup.setPadding(viewGroup.getPaddingLeft(), viewGroup.getPaddingTop(), viewGroup.getPaddingRight(), c);
    }

    private int c() {
        int j = nsn.j();
        return (int) ((j - ((j - r1) * 0.8f)) - nsn.r(this));
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        if (this.f3305a.getSheetState() == HwBottomSheet.SheetState.EXPANDED) {
            a();
        } else {
            super.onBackPressed();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (nsn.a(500)) {
            LogUtil.h("Suggestion_AggregateMoreFilterActivity", "onClick() view click too fast.");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        int id = view.getId();
        if (id == R.id.content || id == R.id.recom_popup_dialog_space) {
            a();
        }
        if (id == R.id.recom_popu_dialog_reset) {
            LogUtil.c("Suggestion_AggregateMoreFilterActivity", " click reset");
            this.i.clear();
            this.b.c();
            this.b.notifyDataSetChanged();
        }
        if (id == R.id.recom_popu_dialog_confirm) {
            a();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        this.e = new Intent();
        Map<Long, List<Long>> b = this.b.b();
        this.i = b;
        LogUtil.a("Suggestion_AggregateMoreFilterActivity", "mSelectedIdMap:", moj.e(b));
        this.e.putExtra("MORE_FILTER_SELECTED_DATA", moj.e(this.i));
        setResult(-1, this.e);
        finish();
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
