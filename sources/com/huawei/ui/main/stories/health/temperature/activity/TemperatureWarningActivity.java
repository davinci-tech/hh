package com.huawei.ui.main.stories.health.temperature.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.adapter.BaseGroupDataAdapter;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.listview.HealthExpandableListView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.temperature.activity.TemperatureWarningActivity;
import com.huawei.ui.main.stories.health.temperature.adapter.TemperatureWaringGroupDataAdapter;
import defpackage.koq;
import defpackage.nsf;
import defpackage.qoi;
import defpackage.qon;
import defpackage.qpk;
import defpackage.qpm;
import defpackage.qpr;
import health.compact.a.LanguageUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class TemperatureWarningActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private HealthExpandableListView f10235a;
    private RelativeLayout b;
    private TemperatureWaringGroupDataAdapter d;
    private CustomTitleBar g;
    private HealthToolBar j;
    private boolean c = false;
    private boolean e = false;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_temperature_warning);
        g();
        e();
    }

    private void e() {
        qpk.d().a(new c(this));
    }

    private void g() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.title_bar);
        this.g = customTitleBar;
        customTitleBar.setTitleBarBackgroundColor(ContextCompat.getColor(this, R.color._2131299296_res_0x7f090be0));
        this.b = (RelativeLayout) findViewById(R.id.temperature_warning_loading);
        this.f10235a = (HealthExpandableListView) findViewById(R.id.temperature_warning_exp_list);
        TemperatureWaringGroupDataAdapter temperatureWaringGroupDataAdapter = new TemperatureWaringGroupDataAdapter();
        this.d = temperatureWaringGroupDataAdapter;
        this.f10235a.setAdapter(temperatureWaringGroupDataAdapter);
        this.d.setOnLongClickListener(new BaseGroupDataAdapter.OnLongClickListener() { // from class: qnx
            @Override // com.huawei.ui.commonui.adapter.BaseGroupDataAdapter.OnLongClickListener
            public final void onLongClickListener(int i, int i2) {
                TemperatureWarningActivity.this.a(i, i2);
            }
        });
        this.d.setOnCheckedChangeListener(new BaseGroupDataAdapter.OnCheckedChangeListener() { // from class: qod
            @Override // com.huawei.ui.commonui.adapter.BaseGroupDataAdapter.OnCheckedChangeListener
            public final void onCheckedChanged(int i, int i2, boolean z) {
                TemperatureWarningActivity.this.a(i, i2, z);
            }
        });
        this.j = (HealthToolBar) findViewById(R.id.temperature_tool_bar);
        h();
        f();
    }

    public /* synthetic */ void a(int i, int i2) {
        this.d.switchCheckStatus(i, i2);
        qoi child = this.d.getChild(i, i2);
        if (child == null) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new HiTimeInterval(child.e(), child.b()));
        a(arrayList);
    }

    public /* synthetic */ void a(int i, int i2, boolean z) {
        c();
    }

    private void h() {
        this.g.setRightButtonDrawable(ContextCompat.getDrawable(this, R.drawable._2131430279_res_0x7f0b0b87), nsf.h(R$string.IDS_contact_delete));
        this.g.setRightButtonVisibility(0);
        this.g.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: qoj
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TemperatureWarningActivity.this.dGA_(view);
            }
        });
        this.g.setRightButtonOnClickListener(new View.OnClickListener() { // from class: qoh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TemperatureWarningActivity.this.dGB_(view);
            }
        });
    }

    public /* synthetic */ void dGA_(View view) {
        if (this.c) {
            b();
        } else {
            finish();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dGB_(View view) {
        d(true);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void f() {
        View inflate = View.inflate(this, R.layout.hw_toolbar_bottomview, null);
        inflate.setBackgroundColor(ContextCompat.getColor(this, R.color._2131297799_res_0x7f090607));
        this.j.cHc_(inflate);
        this.j.setIcon(1, R.drawable._2131430279_res_0x7f0b0b87);
        this.j.setIconTitle(1, getString(R$string.IDS_contact_delete));
        this.j.setIcon(2, R.drawable._2131430296_res_0x7f0b0b98);
        this.j.setIconTitle(2, getResources().getString(R$string.IDS_my_route_select_all));
        this.j.setOnSingleTapListener(new HealthToolBar.OnSingleTapListener() { // from class: qog
            @Override // com.huawei.ui.commonui.toolbar.HealthToolBar.OnSingleTapListener
            public final void onSingleTap(int i) {
                TemperatureWarningActivity.this.c(i);
            }
        });
        this.j.setVisibility(8);
    }

    public /* synthetic */ void c(int i) {
        if (i == 1) {
            if (!this.c) {
                d(true);
                return;
            } else {
                d();
                return;
            }
        }
        if (i == 2) {
            b(!this.e);
        } else {
            LogUtil.h("TemperatureWarningActivity", "initToolBar undefined type");
        }
    }

    private void d(boolean z) {
        this.c = z;
        if (z) {
            this.d.setState(z ? 2 : 1);
            this.g.setTitleText(getString(R$string.IDS_hw_show_main_health_page_healthdata_selectNone));
            this.g.setLeftButtonDrawable(ContextCompat.getDrawable(this, R.drawable._2131428766_res_0x7f0b059e), nsf.h(R$string.accessibility_close));
            this.g.setRightButtonVisibility(8);
            this.j.setVisibility(0);
            return;
        }
        this.d.setState(1);
        this.g.setTitleText(getString(R$string.IDS_temperature_warning));
        this.g.setRightButtonVisibility(0);
        this.j.setVisibility(8);
    }

    private void d() {
        List<qoi> checkedList = this.d.getCheckedList();
        if (koq.c(checkedList)) {
            ArrayList arrayList = new ArrayList();
            for (qoi qoiVar : checkedList) {
                arrayList.add(new HiTimeInterval(qoiVar.e(), qoiVar.b()));
            }
            a(arrayList);
        }
    }

    public void a(final List<HiTimeInterval> list) {
        new NoTitleCustomAlertDialog.Builder(this).e(getString(R$string.IDS_hw_health_show_healthdata_delete)).czE_(getString(R$string.IDS_hw_health_show_common_dialog_ok_button), new View.OnClickListener() { // from class: qoe
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TemperatureWarningActivity.this.dGy_(list, view);
            }
        }).czA_(getString(R$string.IDS_hw_health_show_common_dialog_cancle_button), new View.OnClickListener() { // from class: qob
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TemperatureWarningActivity.this.dGz_(view);
            }
        }).e().show();
    }

    public /* synthetic */ void dGy_(List list, View view) {
        qpk.d().e(this, (List<HiTimeInterval>) list, new IBaseResponseCallback() { // from class: qof
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                TemperatureWarningActivity.this.a(i, obj);
            }
        });
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void a(int i, Object obj) {
        runOnUiThread(new Runnable() { // from class: qoc
            @Override // java.lang.Runnable
            public final void run() {
                TemperatureWarningActivity.this.a();
            }
        });
    }

    public /* synthetic */ void a() {
        this.d.updateDataWithRemoveChecked();
        b();
        if (this.d.getGroupCount() == 0) {
            qpm.d(false);
            finish();
        }
    }

    public /* synthetic */ void dGz_(View view) {
        b();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b(boolean z) {
        int i;
        this.e = z;
        if (z) {
            i = R$string.IDS_my_route_cancel_select_all;
        } else {
            i = R$string.IDS_my_route_select_all;
        }
        this.j.setIconTitle(2, getString(i));
        if (z) {
            this.j.setIcon(2, R.drawable._2131430281_res_0x7f0b0b89);
        } else {
            this.j.setIcon(2, R.drawable._2131430296_res_0x7f0b0b98);
        }
        this.d.setAllChecked(z);
        c();
    }

    public void c() {
        if (!this.c) {
            this.g.setTitleText(getString(R$string.IDS_temperature_warning));
        }
        if (koq.b(this.d.getCheckedList())) {
            this.g.setTitleText(getString(R$string.IDS_hw_show_main_health_page_healthdata_selectNone));
            return;
        }
        int checkedCount = this.d.getCheckedCount();
        boolean z = checkedCount == this.d.getTotalCount();
        this.g.setTitleText(getResources().getQuantityString(R.plurals._2130903044_res_0x7f030004, 0, Integer.valueOf(checkedCount)));
        this.j.setIcon(2, z ? R.drawable._2131430281_res_0x7f0b0b89 : R.drawable._2131430296_res_0x7f0b0b98);
        this.j.setIconTitle(2, getString(z ? R$string.IDS_my_route_cancel_select_all : R$string.IDS_my_route_select_all));
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.c) {
            b();
        } else {
            super.onBackPressed();
        }
    }

    private void b() {
        LogUtil.a("TemperatureWarningActivity", "cancelSelect");
        b(false);
        d(false);
        if (!LanguageUtil.bc(this)) {
            this.g.setLeftButtonDrawable(ContextCompat.getDrawable(this, R.drawable._2131428438_res_0x7f0b0456), nsf.h(R$string.accessibility_go_back));
        } else {
            this.g.setLeftButtonDrawable(ContextCompat.getDrawable(this, R.mipmap._2131820922_res_0x7f11017a), nsf.h(R$string.accessibility_go_back));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final List<qon> list) {
        HandlerExecutor.e(new Runnable() { // from class: qoa
            @Override // java.lang.Runnable
            public final void run() {
                TemperatureWarningActivity.this.b(list);
            }
        });
    }

    public /* synthetic */ void b(List list) {
        if (koq.b(list)) {
            qpm.d(false);
            finish();
            return;
        }
        this.d.refresh(list);
        this.b.setVisibility(8);
        this.f10235a.setVisibility(0);
        for (int i = 0; i < list.size(); i++) {
            this.f10235a.expandGroup(i);
        }
    }

    static class c implements IBaseResponseCallback {
        private final WeakReference<TemperatureWarningActivity> d;

        c(TemperatureWarningActivity temperatureWarningActivity) {
            this.d = new WeakReference<>(temperatureWarningActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            TemperatureWarningActivity temperatureWarningActivity = this.d.get();
            if (temperatureWarningActivity == null) {
                LogUtil.h("TemperatureWarningActivity", "getWarningData activity is null");
                return;
            }
            if (i != 0) {
                LogUtil.h("TemperatureWarningActivity", "getWarningData is error");
            } else if (obj instanceof List) {
                List list = (List) obj;
                LogUtil.a("TemperatureWarningActivity", "getWarningData size: ", Integer.valueOf(list.size()));
                temperatureWarningActivity.c(qpr.b((List<HiHealthData>) list));
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
