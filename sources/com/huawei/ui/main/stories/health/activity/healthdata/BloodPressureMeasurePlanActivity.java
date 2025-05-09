package com.huawei.ui.main.stories.health.activity.healthdata;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.health.bloodpressure.adapter.BloodPressureTargetAdapter;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.adapter.GroupBtnSelectedAdapter;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.recycleview.RecyclerItemDecoration;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import com.huawei.ui.commonui.timepicker.HealthTimePickerDialog;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.BloodPressureMeasurePlanActivity;
import com.huawei.uikit.hwtextview.widget.HwTextView;
import com.huawei.uikit.hwtimepicker.widget.HwTimePicker;
import com.huawei.uikit.hwtimepicker.widget.HwTimePickerDialog;
import defpackage.cbk;
import defpackage.jcf;
import defpackage.koq;
import defpackage.nrh;
import defpackage.nsf;
import defpackage.nsj;
import defpackage.nsn;
import defpackage.qhc;
import defpackage.qif;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

/* loaded from: classes9.dex */
public class BloodPressureMeasurePlanActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private HealthTextView f10022a;
    private HealthTextView c;
    private HealthTextView d;
    private Context e;
    private HealthTextView f;
    private LinearLayout g;
    private HealthTextView h;
    private boolean i;
    private GridLayoutManager l;
    private Set<cbk> m;
    private boolean n;
    private HealthButton o;
    private HealthSwitchButton p;
    private HealthRecycleView r;
    private BloodPressureTargetAdapter s;
    private ConstraintLayout t;
    private GroupBtnSelectedAdapter u;
    private ImageView w;
    private Dialog x;
    private HealthRecycleView y;
    private boolean[] k = new boolean[7];
    private int b = 127;
    private int q = 0;
    private boolean j = false;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        LogUtil.a("BloodPressureMeasurePlanActivity", "onCreate");
        setContentView(R.layout.health_data_bloodpressure_plan);
        this.e = this;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LogUtil.a("BloodPressureMeasurePlanActivity", "onResume");
        g();
        qif.c(new e(this));
    }

    private void g() {
        HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.recycleview_target_layout);
        this.r = healthRecycleView;
        healthRecycleView.setIsScroll(false);
        this.r.setLayoutManager(new LinearLayoutManager(this.e));
        this.c = (HealthTextView) findViewById(R.id.blood_pressure_add_time);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.blood_pressure_customize_tips);
        this.h = healthTextView;
        healthTextView.setText(getString(R$string.IDS_blood_pressure_customize_tips, new Object[]{10}));
        this.d = (HealthTextView) findViewById(R.id.delete_plan);
        this.f10022a = (HealthTextView) findViewById(R.id.delete_cancel);
        this.f = (HealthTextView) findViewById(R.id.delete_confirm);
        this.t = (ConstraintLayout) findViewById(R.id.plan_normal_layout);
        this.g = (LinearLayout) findViewById(R.id.plan_delete_edit_layout);
        HealthRecycleView healthRecycleView2 = (HealthRecycleView) findViewById(R.id.recycleview_week_layout);
        this.y = healthRecycleView2;
        healthRecycleView2.setIsConsumption(true);
        this.y.setIsScroll(false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.e, 4) { // from class: com.huawei.ui.main.stories.health.activity.healthdata.BloodPressureMeasurePlanActivity.1
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        };
        this.l = gridLayoutManager;
        this.y.setLayoutManager(gridLayoutManager);
        this.w = (ImageView) findViewById(R.id.text_remind_tips);
        this.p = (HealthSwitchButton) findViewById(R.id.switch_reminder);
        HealthButton healthButton = (HealthButton) findViewById(R.id.target_settings_confirm);
        this.o = healthButton;
        healthButton.setEnabled(false);
    }

    private void b() {
        LogUtil.a("BloodPressureMeasurePlanActivity", "PlanSet = ", this.m);
        Set<cbk> set = this.m;
        if (set == null || set.size() < 2) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new cbk(0, 7, 0, 0));
            arrayList.add(new cbk(9, 22, 0, 9));
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                ((cbk) it.next()).b(this.b);
            }
            this.m = new HashSet(arrayList);
            this.s = new BloodPressureTargetAdapter(this, arrayList);
            this.n = false;
        } else {
            ArrayList arrayList2 = new ArrayList(this.m);
            Collections.sort(arrayList2, qhc.a());
            this.b = ((cbk) arrayList2.get(0)).d();
            Iterator it2 = arrayList2.iterator();
            boolean z = false;
            while (it2.hasNext()) {
                cbk cbkVar = (cbk) it2.next();
                if (cbkVar.f() != cbkVar.b()) {
                    cbkVar.d(cbkVar.b());
                    z = true;
                }
                if (cbkVar.d() == 0) {
                    this.b = 127;
                    cbkVar.b(127);
                    z = true;
                }
            }
            this.n = ((cbk) arrayList2.get(0)).i();
            this.s = new BloodPressureTargetAdapter(this, arrayList2);
            if (z) {
                LogUtil.a("BloodPressureMeasurePlanActivity", "Correct old plan data.");
                this.s.a(this.b, this.n, 1);
            }
        }
        i();
    }

    private void h() {
        this.s.b(new BloodPressureTargetAdapter.OnItemClickListener() { // from class: qby
            @Override // com.huawei.health.health.bloodpressure.adapter.BloodPressureTargetAdapter.OnItemClickListener
            public final void onItemClick(View view, int i) {
                BloodPressureMeasurePlanActivity.this.dyX_(view, i);
            }
        });
        this.r.setAdapter(this.s);
        this.c.setOnClickListener(new View.OnClickListener() { // from class: qbt
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BloodPressureMeasurePlanActivity.this.dyY_(view);
            }
        });
        GroupBtnSelectedAdapter groupBtnSelectedAdapter = new GroupBtnSelectedAdapter(this.e, new String[]{this.e.getString(R$string.IDS_monday), this.e.getString(R$string.IDS_tuesday), this.e.getString(R$string.IDS_wednesday), this.e.getString(R$string.IDS_thursday), this.e.getString(R$string.IDS_friday), this.e.getString(R$string.IDS_saturday), this.e.getString(R$string.IDS_sunday)}, this.k, R.layout.item_measure_plan_select);
        this.u = groupBtnSelectedAdapter;
        groupBtnSelectedAdapter.c(new GroupBtnSelectedAdapter.OnItemClickListener() { // from class: qbv
            @Override // com.huawei.ui.commonui.adapter.GroupBtnSelectedAdapter.OnItemClickListener
            public final void onItemClick(View view, HealthButton healthButton, int i) {
                BloodPressureMeasurePlanActivity.this.dyZ_(view, healthButton, i);
            }
        });
        j();
    }

    public /* synthetic */ void dyX_(View view, int i) {
        LogUtil.a("BloodPressureMeasurePlanActivity", "position:", Integer.valueOf(i), "mShowMode", Integer.valueOf(this.q));
        List<cbk> c = this.s.c();
        if (koq.b(c, i)) {
            LogUtil.h("BloodPressureMeasurePlanActivity", "initEvent position is out of mTargetAdapter.getTargetList().");
        } else {
            if (this.q == 0) {
                if (nsn.o()) {
                    return;
                }
                d(qhc.a(c.get(i)), i);
                return;
            }
            this.s.e(i);
        }
    }

    public /* synthetic */ void dyY_(View view) {
        if (!nsn.o()) {
            LogUtil.a("BloodPressureMeasurePlanActivity", "click addTime");
            d(getString(R$string.IDS_blood_pressure_add_time), -1);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dyZ_(View view, HealthButton healthButton, int i) {
        this.k[i] = !r0[i];
        if (!e()) {
            this.k[i] = !r0[i];
        }
        this.u.cwF_(view, healthButton, i);
        jcf.bEk_(view, nsf.h(this.k[i] ? R$string.accessibility_checked : R$string.accessibility_Unchecked));
    }

    private void j() {
        this.y.setAdapter(this.u);
        if (!this.i) {
            this.i = true;
            ArrayList arrayList = new ArrayList(16);
            arrayList.add(0);
            arrayList.add(0);
            arrayList.add(0);
            arrayList.add(0);
            this.y.addItemDecoration(new RecyclerItemDecoration(0, 0, arrayList));
        }
        this.p.setChecked(this.n);
        this.p.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.BloodPressureMeasurePlanActivity$$ExternalSyntheticLambda0
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                BloodPressureMeasurePlanActivity.this.dza_(compoundButton, z);
            }
        });
        this.o.setOnClickListener(new View.OnClickListener() { // from class: qbs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BloodPressureMeasurePlanActivity.this.dzb_(view);
            }
        });
        this.w.setOnClickListener(new View.OnClickListener() { // from class: qbw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BloodPressureMeasurePlanActivity.this.dzc_(view);
            }
        });
    }

    /* synthetic */ void dza_(CompoundButton compoundButton, boolean z) {
        this.n = z;
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    public /* synthetic */ void dzb_(View view) {
        if (!nsn.o()) {
            this.o.setEnabled(false);
            this.s.d(d(), this.n);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dzc_(View view) {
        if (!nsn.o()) {
            View inflate = View.inflate(this.e, R.layout.dialog_reminder_rule, null);
            HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.reminder_rule_dialog_title);
            healthTextView.setText(R$string.IDS_blood_pressure_remind_title);
            healthTextView.setZoomSize(R.dimen._2131362922_res_0x7f0a046a, 1.5f);
            HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.reminder_rule_dialog_content);
            healthTextView2.setText(R$string.IDS_blood_pressure_remind_tips);
            healthTextView2.setZoomSize(R.dimen._2131362906_res_0x7f0a045a, 1.5f);
            new CustomViewDialog.Builder(this.e).c(false).czg_(inflate).cze_(R$string.IDS_user_permission_know, new View.OnClickListener() { // from class: qcb
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    ViewClickInstrumentation.clickOnView(view2);
                }
            }).e().show();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        BloodPressureTargetAdapter bloodPressureTargetAdapter = this.s;
        if (bloodPressureTargetAdapter != null && bloodPressureTargetAdapter.getItemCount() <= 2) {
            this.d.setVisibility(8);
        } else {
            this.d.setVisibility(0);
        }
        LogUtil.c("BloodPressureMeasurePlanActivity", Integer.valueOf(this.s.getItemCount()));
        this.d.setOnClickListener(new View.OnClickListener() { // from class: qbu
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BloodPressureMeasurePlanActivity.this.dyU_(view);
            }
        });
        this.f10022a.setOnClickListener(new View.OnClickListener() { // from class: qbx
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BloodPressureMeasurePlanActivity.this.dyV_(view);
            }
        });
        this.f.setOnClickListener(new View.OnClickListener() { // from class: qca
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BloodPressureMeasurePlanActivity.this.dyW_(view);
            }
        });
    }

    public /* synthetic */ void dyU_(View view) {
        f();
        this.t.setVisibility(8);
        this.g.setVisibility(0);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dyV_(View view) {
        f();
        this.s.a();
        this.t.setVisibility(0);
        this.g.setVisibility(8);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dyW_(View view) {
        f();
        this.s.b();
        a();
        this.t.setVisibility(0);
        this.g.setVisibility(8);
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(final int i, final Object obj) {
        LogUtil.a("BloodPressureMeasurePlanActivity", "initPlan, code = ", Integer.valueOf(i), " ,data = ", obj);
        new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: qbz
            @Override // java.lang.Runnable
            public final void run() {
                BloodPressureMeasurePlanActivity.this.d(i, obj);
            }
        });
    }

    public /* synthetic */ void d(int i, Object obj) {
        if (i == 0) {
            if (obj instanceof Set) {
                this.m = (Set) obj;
            }
        } else {
            LogUtil.h("BloodPressureMeasurePlanActivity", "getAlarm failed");
        }
        b();
        h();
        a();
        this.u.notifyDataSetChanged();
        this.s.notifyDataSetChanged();
        this.o.setEnabled(true);
        c();
    }

    private void f() {
        int i = this.q == 0 ? 1 : 0;
        this.q = i;
        if (i == 1) {
            this.j = true;
        } else {
            this.j = false;
        }
        this.s.a(i);
    }

    private int d() {
        int i = 0;
        int i2 = 0;
        while (true) {
            boolean[] zArr = this.k;
            if (i >= zArr.length) {
                return i2;
            }
            int i3 = 1;
            if (!zArr[i]) {
                i3 = 0;
            }
            i2 |= i3 << i;
            i++;
        }
    }

    private void i() {
        String format = String.format(Locale.ROOT, "%07d", Integer.valueOf(Integer.toBinaryString(this.b)));
        LogUtil.a("BloodPressureMeasurePlanActivity", "setSelectedDays, mDayOfWeek = ", Integer.valueOf(this.b));
        int i = 0;
        while (true) {
            boolean[] zArr = this.k;
            if (i >= zArr.length) {
                return;
            }
            int i2 = i + 1;
            zArr[(zArr.length - i) - 1] = "1".equals(format.substring(i, i2));
            i = i2;
        }
    }

    private boolean e() {
        for (boolean z : this.k) {
            if (z) {
                return true;
            }
        }
        return false;
    }

    private void d(String str, final int i) {
        d(new HealthTimePickerDialog(this, new HwTimePickerDialog.OnButtonClickCallback() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.BloodPressureMeasurePlanActivity.2
            @Override // com.huawei.uikit.hwtimepicker.widget.HwTimePickerDialog.OnButtonClickCallback
            public void onPositiveButtonClick(HwTimePicker hwTimePicker) {
                int hour = hwTimePicker.getHour();
                int minute = hwTimePicker.getMinute();
                LogUtil.a("BloodPressureMeasurePlanActivity", "hour = ", Integer.valueOf(hour), ", minute = ", Integer.valueOf(minute));
                int i2 = i;
                if (i2 != 0 || (hour >= 6 && hour <= 9 && (hour != 9 || minute <= 0))) {
                    if (i2 == BloodPressureMeasurePlanActivity.this.s.getItemCount() - 1 && hour >= 2 && hour < 20) {
                        BloodPressureMeasurePlanActivity bloodPressureMeasurePlanActivity = BloodPressureMeasurePlanActivity.this;
                        nrh.d(bloodPressureMeasurePlanActivity, bloodPressureMeasurePlanActivity.getResources().getString(com.huawei.ui.commonui.R$string.IDS_blood_pressure_sleep_toast, nsj.c(BloodPressureMeasurePlanActivity.this.e, qhc.b(20, 0), 1), nsj.c(BloodPressureMeasurePlanActivity.this.e, qhc.b(1, 59), 1)));
                        return;
                    }
                    if (i == -1) {
                        BloodPressureMeasurePlanActivity.this.s.a(new cbk(10, hour, minute, 10));
                    } else {
                        BloodPressureTargetAdapter bloodPressureTargetAdapter = BloodPressureMeasurePlanActivity.this.s;
                        int i3 = i;
                        bloodPressureTargetAdapter.d(new cbk(i3, hour, minute, i3), i);
                    }
                    BloodPressureMeasurePlanActivity.this.x.dismiss();
                    BloodPressureMeasurePlanActivity.this.c();
                    LogUtil.c("BloodPressureMeasurePlanActivity", "onPositiveButtonClick," + BloodPressureMeasurePlanActivity.this.s.getItemCount());
                    BloodPressureMeasurePlanActivity.this.a();
                    return;
                }
                BloodPressureMeasurePlanActivity bloodPressureMeasurePlanActivity2 = BloodPressureMeasurePlanActivity.this;
                nrh.d(bloodPressureMeasurePlanActivity2, bloodPressureMeasurePlanActivity2.getResources().getString(com.huawei.ui.commonui.R$string.IDS_blood_pressure_wakeup_toast, nsj.c(BloodPressureMeasurePlanActivity.this.e, qhc.b(6, 0), 1), nsj.c(BloodPressureMeasurePlanActivity.this.e, qhc.b(9, 0), 1)));
            }

            @Override // com.huawei.uikit.hwtimepicker.widget.HwTimePickerDialog.OnButtonClickCallback
            public void onNegativeButtonClick(HwTimePicker hwTimePicker) {
                BloodPressureMeasurePlanActivity.this.x.dismiss();
            }
        }), i, str);
    }

    private void d(HealthTimePickerDialog healthTimePickerDialog, int i, String str) {
        healthTimePickerDialog.e(HealthTimePickerDialog.ButtonId.LEFT, ContextCompat.getColor(this, R$color.textColorSecondary));
        Calendar calendar = Calendar.getInstance();
        int i2 = calendar.get(11);
        int i3 = calendar.get(12);
        calendar.clear();
        if (i == -1) {
            healthTimePickerDialog.e(0, 0, 0, i2, i3);
        } else if (i <= this.s.getItemCount() - 1 && i >= 0) {
            cbk cbkVar = this.s.c().get(i);
            healthTimePickerDialog.e(0, 0, 0, cbkVar.a(), cbkVar.e());
        } else {
            LogUtil.h("BloodPressureMeasurePlanActivity", "position invalid :", Integer.valueOf(i));
            healthTimePickerDialog.e(0, 0, 0, i2, i3);
        }
        healthTimePickerDialog.b(str);
        healthTimePickerDialog.a(getColor(R.color._2131296532_res_0x7f090114));
        healthTimePickerDialog.e(HealthTimePickerDialog.ButtonId.RIGHT, getColor(R.color._2131296532_res_0x7f090114));
        healthTimePickerDialog.show();
        dyT_(healthTimePickerDialog, GravityCompat.START);
        this.x = healthTimePickerDialog;
    }

    private void dyT_(Dialog dialog, int i) {
        HwTextView hwTextView = (HwTextView) dialog.findViewById(R.id.hwtimepicker_title);
        if (hwTextView != null) {
            hwTextView.setGravity(i);
        } else {
            LogUtil.h("BloodPressureMeasurePlanActivity", "Dialog title is null.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (this.s.c().size() >= 10) {
            this.c.setVisibility(8);
            this.h.setVisibility(0);
        } else {
            this.c.setVisibility(0);
            this.h.setVisibility(8);
        }
    }

    static class e implements ResponseCallback<List<cbk>> {
        private WeakReference<BloodPressureMeasurePlanActivity> c;

        e(BloodPressureMeasurePlanActivity bloodPressureMeasurePlanActivity) {
            this.c = new WeakReference<>(bloodPressureMeasurePlanActivity);
        }

        @Override // com.huawei.hwbasemgr.ResponseCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, List<cbk> list) {
            BloodPressureMeasurePlanActivity bloodPressureMeasurePlanActivity = this.c.get();
            if (bloodPressureMeasurePlanActivity != null) {
                bloodPressureMeasurePlanActivity.c(i, new HashSet(list));
                qhc.b();
            } else {
                LogUtil.h("BloodPressureMeasurePlanActivity", "BloodPressureSimpleCallback measurePlanActivity is null");
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
