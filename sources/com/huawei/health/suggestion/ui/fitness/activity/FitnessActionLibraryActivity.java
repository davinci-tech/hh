package com.huawei.health.suggestion.ui.fitness.activity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.suggestion.common.SearchShowMode;
import com.huawei.health.suggestion.ui.BaseActivity;
import com.huawei.health.suggestion.ui.BaseFitnessSearchActivity;
import com.huawei.health.suggestion.ui.fitness.adapter.FitnessActionRecyAdapter;
import com.huawei.health.suggestion.ui.view.PullRecyclerViewGroup;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.arx;
import defpackage.foo;
import defpackage.fqa;
import defpackage.ggs;
import defpackage.nrz;
import defpackage.nsf;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class FitnessActionLibraryActivity extends BaseFitnessSearchActivity {

    /* renamed from: a, reason: collision with root package name */
    private Context f3082a;
    private FitnessActionRecyAdapter b;
    private foo c;
    private int d = 0;
    private List<fqa> e;
    private HealthRecycleView f;
    private PullRecyclerViewGroup j;

    @Override // com.huawei.health.suggestion.ui.BaseFitnessSearchActivity
    public void initNormalModeLayout() {
    }

    @Override // com.huawei.health.suggestion.ui.BaseFitnessSearchActivity
    public void initNormalViewController() {
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity, com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.f3082a = getApplicationContext();
        BaseActivity.setViewSafeRegion(true, this.j);
        FitnessActionRecyAdapter fitnessActionRecyAdapter = new FitnessActionRecyAdapter(this.f, this);
        this.b = fitnessActionRecyAdapter;
        this.f.setAdapter(fitnessActionRecyAdapter);
        this.f.setLayoutManager(new LinearLayoutManager(arx.b()));
        ggs.e(this, this.f, ggs.c(this.f3082a));
    }

    @Override // com.huawei.health.suggestion.ui.BaseStateActivity
    public void initData() {
        c();
    }

    private void c() {
        this.e = new ArrayList(10);
        int[] iArr = {R.string._2130848541_res_0x7f022b1d, R.string._2130848542_res_0x7f022b1e, R.string._2130848543_res_0x7f022b1f, R.string._2130848548_res_0x7f022b24, R.string._2130848544_res_0x7f022b20, R.string._2130848545_res_0x7f022b21, R.string._2130848546_res_0x7f022b22, R.string._2130848547_res_0x7f022b23, R.string._2130848549_res_0x7f022b25, R.string._2130848550_res_0x7f022b26};
        int[] iArr2 = {R.drawable._2131428211_res_0x7f0b0373, R.drawable._2131428212_res_0x7f0b0374, R.drawable._2131428216_res_0x7f0b0378, R.drawable._2131428210_res_0x7f0b0372, R.drawable._2131428209_res_0x7f0b0371, R.drawable._2131428208_res_0x7f0b0370, R.drawable._2131428217_res_0x7f0b0379, R.drawable._2131428214_res_0x7f0b0376, R.drawable._2131428213_res_0x7f0b0375, R.drawable._2131428215_res_0x7f0b0377};
        int[] iArr3 = {1, 2, 16, 4, 128, 256, 32, 64, 8, 512};
        for (int i = 0; i < 10; i++) {
            fqa fqaVar = new fqa();
            fqaVar.d(iArr[i]);
            fqaVar.e(iArr2[i]);
            fqaVar.c(iArr3[i]);
            this.e.add(fqaVar);
        }
        runOnUiThread(new Runnable() { // from class: com.huawei.health.suggestion.ui.fitness.activity.FitnessActionLibraryActivity.4
            @Override // java.lang.Runnable
            public void run() {
                FitnessActionLibraryActivity.this.b.e(FitnessActionLibraryActivity.this.e);
            }
        });
    }

    @Override // com.huawei.health.suggestion.ui.BaseFitnessSearchActivity, com.huawei.health.suggestion.ui.BaseStateActivity
    public void initLayout() {
        setContentView(R.layout.sug_activity_action_library);
        super.initLayout();
    }

    @Override // com.huawei.health.suggestion.ui.BaseFitnessSearchActivity, com.huawei.health.suggestion.ui.BaseStateActivity
    public void initViewController() {
        super.initViewController();
        this.j = (PullRecyclerViewGroup) findViewById(R.id.normal_content_layout);
        this.f = (HealthRecycleView) findViewById(R.id.recyclerview_action_library);
    }

    @Override // com.huawei.health.suggestion.ui.BaseFitnessSearchActivity
    public void initTitleBarSearchController() {
        if (this.mTitleBar == null) {
            LogUtil.h("FitnessActionLibraryActivity", "initTitleBarSearchController TitleBar can not be null");
            return;
        }
        this.mTitleBar.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.suggestion.ui.fitness.activity.FitnessActionLibraryActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SearchShowMode.NORMAL.equals(FitnessActionLibraryActivity.this.mShowModeStatus)) {
                    FitnessActionLibraryActivity.this.switchToSearchMode();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.mTitleBar.setTitleBarBackgroundColor(getResources().getColor(R$color.fitness_action_library_normal));
        if (LanguageUtil.bc(this)) {
            this.mTitleBar.setRightButtonDrawable(nrz.cKn_(this, R.drawable._2131431370_res_0x7f0b0fca), nsf.h(R.string._2130847322_res_0x7f02265a));
        }
    }

    @Override // com.huawei.health.suggestion.ui.BaseFitnessSearchActivity
    public void initSearchViewController() {
        super.initSearchViewController();
        foo fooVar = new foo(this);
        this.c = fooVar;
        fooVar.c(this.d);
        setOnQueryTextListener(this.c);
        setLoadMoreListener(this.c);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
        HealthRecycleView healthRecycleView = this.f;
        if (healthRecycleView == null || this.b == null) {
            return;
        }
        healthRecycleView.setAdapter(null);
        this.f.setLayoutManager(null);
        this.f.setAdapter(this.b);
        ggs.e(this, this.f, ggs.c(this.f3082a));
        this.b.notifyDataSetChanged();
    }
}
