package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.mediamanager.MediaManager;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDataSourceFetchOption;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataClientListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.model.HiBloodPressureMetaData;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.datepicker.HealthDatePickerDialog;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.flowlayout.HealthFlowLayout;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.popupview.PopViewList;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.scrollview.ScrollScaleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.timepicker.HealthTimePickerDialog;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.InputBloodPressureActivity;
import com.huawei.ui.main.stories.health.adapter.CustomActionDialogAdapter;
import com.huawei.ui.main.stories.health.bloodpressure.BloodPressureMeasureActionManager;
import com.huawei.ui.main.stories.health.util.BloodPressureUtil;
import com.huawei.ui.main.stories.privacy.template.model.bean.PageModelArgs;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import com.huawei.ui.main.stories.privacy.template.view.showdata.PrivacyDetailActivity;
import defpackage.gmn;
import defpackage.ixx;
import defpackage.koq;
import defpackage.nrh;
import defpackage.nro;
import defpackage.nrz;
import defpackage.nsf;
import defpackage.nsj;
import defpackage.nsn;
import defpackage.qkh;
import defpackage.qkv;
import defpackage.qrc;
import defpackage.rju;
import health.compact.a.LanguageUtil;
import java.lang.ref.WeakReference;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public class InputBloodPressureActivity extends BaseActivity implements View.OnClickListener {

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f10049a = {"KnitHealthData_Edit_" + DataInfos.BloodPressureDayDetail, "KnitHealthData_Edit_" + DataInfos.BloodPressureWeekDetail, "KnitHealthData_Edit_" + DataInfos.BloodPressureMonthDetail};
    private long aa;
    private List<String> ag;
    private HealthTextView ai;
    private int aj;
    private HealthTextView ak;
    private ScrollScaleView am;
    private long an;
    private long ao;
    private ScrollScaleView ap;
    private View as;
    private HealthButton at;
    private LinearLayout au;
    private HealthTextView av;
    private HealthTextView aw;
    private ImageView b;
    private CustomTitleBar bb;
    private List<String> c;
    private int d;
    private BloodPressureMeasureActionManager e;
    private HealthTextView f;
    private HealthTextView g;
    private Context h;
    private int i;
    private View j;
    private LinearLayout k;
    private long l;
    private HealthFlowLayout m;
    private String o;
    private HealthSubHeader p;
    private boolean q;
    private ScrollScaleView r;
    private HiHealthData s;
    private HealthTextView u;
    private ImageView v;
    private HealthTextView w;
    private HealthTextView x;
    private HealthTextView y;
    private ImageView z;
    private final Handler n = new i(this);
    private double al = 0.0d;
    private double ar = 0.0d;
    private double aq = 0.0d;
    private double t = 0.0d;
    private double ah = 0.0d;
    private double ax = 75.0d;
    private boolean af = false;
    private boolean ae = false;
    private boolean ac = true;
    private boolean ad = false;
    private boolean ab = false;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.health_data_inputbloodpressure);
        this.h = this;
        l();
        g();
        h();
        i();
    }

    private void dAs_(View view, int i2) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams instanceof ConstraintLayout.LayoutParams) {
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
            layoutParams2.bottomMargin = 0;
            layoutParams2.topToTop = i2;
            view.setLayoutParams(layoutParams2);
        }
    }

    private void dAt_(View view, View view2, View view3) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        ViewGroup.LayoutParams layoutParams2 = view2.getLayoutParams();
        ViewGroup.LayoutParams layoutParams3 = view3.getLayoutParams();
        if ((layoutParams instanceof ConstraintLayout.LayoutParams) && (layoutParams2 instanceof ConstraintLayout.LayoutParams) && (layoutParams3 instanceof ConstraintLayout.LayoutParams)) {
            ConstraintLayout.LayoutParams layoutParams4 = (ConstraintLayout.LayoutParams) layoutParams;
            layoutParams4.setMarginStart(0);
            layoutParams4.startToEnd = -1;
            layoutParams4.endToEnd = 0;
            layoutParams4.startToStart = 0;
            view.setLayoutParams(layoutParams4);
            ConstraintLayout.LayoutParams layoutParams5 = (ConstraintLayout.LayoutParams) layoutParams2;
            layoutParams5.setMarginStart(2);
            layoutParams5.startToEnd = view.getId();
            layoutParams5.startToStart = -1;
            layoutParams5.endToEnd = -1;
            view2.setLayoutParams(layoutParams5);
            ConstraintLayout.LayoutParams layoutParams6 = (ConstraintLayout.LayoutParams) layoutParams3;
            layoutParams6.bottomMargin = 0;
            layoutParams6.bottomToBottom = view.getId();
            layoutParams6.endToStart = view.getId();
            view3.setLayoutParams(layoutParams6);
        }
    }

    private void i() {
        String b2 = rju.c.b(this.aj);
        if (TextUtils.isEmpty(b2)) {
            return;
        }
        findViewById(R.id.measure_exception_card).setVisibility(0);
        ((HealthTextView) findViewById(R.id.measure_exception_desc)).setText(b2);
        ImageView imageView = (ImageView) findViewById(R.id.measure_exception_icon);
        int i2 = this.aj;
        if (i2 == 2) {
            imageView.setImageResource(R.drawable._2131429839_res_0x7f0b09cf);
            return;
        }
        if (i2 == 3) {
            imageView.setImageResource(R.drawable._2131429836_res_0x7f0b09cc);
            return;
        }
        if (i2 == 4) {
            imageView.setImageResource(R.drawable._2131429835_res_0x7f0b09cb);
            return;
        }
        if (i2 == 5) {
            imageView.setImageResource(R.drawable._2131429838_res_0x7f0b09ce);
        } else if (i2 == 9) {
            imageView.setImageResource(R.drawable.ic_bp_abnomal_heart_rate);
        } else {
            if (i2 != 10) {
                return;
            }
            imageView.setImageResource(R.drawable._2131429837_res_0x7f0b09cd);
        }
    }

    private void l() {
        b();
        if (LanguageUtil.b(this.h)) {
            dAs_(this.u, R.id.input_blood_pressure_high);
            dAs_(this.ak, R.id.input_blood_pressure_low);
            dAs_(this.aw, R.id.input_blood_pressure_bmp_number);
        }
        if (LanguageUtil.bp(this.h)) {
            dAt_(this.u, this.w, this.x);
            dAt_(this.ak, this.y, this.ai);
        }
        this.r.setData(c(40, 300), 10, 40);
        this.r.setOnSelectedListener(new ScrollScaleView.OnSelectedListener() { // from class: qda
            @Override // com.huawei.ui.commonui.scrollview.ScrollScaleView.OnSelectedListener
            public final void onSelected(List list, int i2) {
                InputBloodPressureActivity.this.d(list, i2);
            }
        });
        this.am.setData(c(30, 200), 10, 40);
        this.am.setOnSelectedListener(new ScrollScaleView.OnSelectedListener() { // from class: qdi
            @Override // com.huawei.ui.commonui.scrollview.ScrollScaleView.OnSelectedListener
            public final void onSelected(List list, int i2) {
                InputBloodPressureActivity.this.b(list, i2);
            }
        });
        this.ap.setData(c(30, 180), 10, 40);
        this.ap.setOnSelectedListener(new ScrollScaleView.OnSelectedListener() { // from class: qdg
            @Override // com.huawei.ui.commonui.scrollview.ScrollScaleView.OnSelectedListener
            public final void onSelected(List list, int i2) {
                InputBloodPressureActivity.this.c(list, i2);
            }
        });
        this.p.setSubHeaderBackgroundColor(ContextCompat.getColor(this, R.color._2131296971_res_0x7f0902cb));
        BloodPressureMeasureActionManager bloodPressureMeasureActionManager = new BloodPressureMeasureActionManager(this);
        this.e = bloodPressureMeasureActionManager;
        bloodPressureMeasureActionManager.e(new BloodPressureMeasureActionManager.CustomActionChangeListener() { // from class: qdk
            @Override // com.huawei.ui.main.stories.health.bloodpressure.BloodPressureMeasureActionManager.CustomActionChangeListener
            public final void onAddAction(String str) {
                InputBloodPressureActivity.this.b(str);
            }
        });
        this.e.e(this.m);
        a((HiHealthData) null);
        k();
        j();
    }

    public /* synthetic */ void d(List list, int i2) {
        double d2 = i2 + 40;
        this.t = d2;
        this.w.setText(BloodPressureUtil.a(d2));
    }

    public /* synthetic */ void b(List list, int i2) {
        double d2 = i2 + 30;
        this.ah = d2;
        this.y.setText(BloodPressureUtil.a(d2));
    }

    public /* synthetic */ void c(List list, int i2) {
        double d2 = i2 + 30;
        this.ax = d2;
        this.av.setText(BloodPressureUtil.a(d2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(String str) {
        this.c.add(str);
        this.ag.add(str);
        this.q = !this.c.isEmpty();
        d(true);
        BloodPressureUtil.d(this.h, this.ag);
    }

    private void d(List<String> list) {
        this.c.removeAll(list);
        this.ag.removeAll(list);
        List<qkv> b2 = BloodPressureUtil.b();
        List<String> arrayList = new ArrayList<>();
        b(this.s, b2, arrayList);
        for (String str : this.c) {
            qkv qkvVar = new qkv(str);
            qkvVar.b(true);
            if (arrayList.contains(str)) {
                qkvVar.d(true);
            }
            for (qkv qkvVar2 : this.e.c()) {
                if (qkvVar2.i() && qkvVar2.d().equals(str)) {
                    qkvVar.c(true);
                }
            }
            b2.add(qkvVar);
        }
        BloodPressureUtil.d(b2);
        this.q = !this.c.isEmpty();
        this.e.c(b2);
        d(true);
        BloodPressureUtil.d(this.h, this.ag);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(HiHealthData hiHealthData) {
        this.s = hiHealthData;
        List<qkv> b2 = BloodPressureUtil.b();
        List<String> arrayList = new ArrayList<>();
        b(hiHealthData, b2, arrayList);
        this.c = new ArrayList();
        List<String> c2 = BloodPressureUtil.c(this.h);
        this.ag = new ArrayList(c2);
        e(b2, arrayList, c2);
        for (String str : arrayList) {
            if (!c2.contains(str)) {
                qkv qkvVar = new qkv(str);
                this.c.add(str);
                qkvVar.b(true);
                qkvVar.d(true);
                qkvVar.c(true);
                b2.add(qkvVar);
            }
        }
        for (String str2 : c2) {
            qkv qkvVar2 = new qkv(str2);
            qkvVar2.b(true);
            if (arrayList.contains(str2)) {
                qkvVar2.c(true);
            }
            b2.add(qkvVar2);
            this.c.add(str2);
        }
        BloodPressureUtil.d(b2);
        this.q = !this.c.isEmpty();
        this.e.c(b2);
    }

    private void e(List<qkv> list, List<String> list2, List<String> list3) {
        Iterator<qkv> it = list.iterator();
        while (it.hasNext()) {
            String d2 = it.next().d();
            list2.remove(d2);
            list3.remove(d2);
        }
    }

    private void b(HiHealthData hiHealthData, List<qkv> list, List<String> list2) {
        if (hiHealthData == null) {
            return;
        }
        BloodPressureUtil.d(BloodPressureUtil.a(hiHealthData.getInt("point_value")), list);
        if (TextUtils.isEmpty(hiHealthData.getMetaData())) {
            return;
        }
        HiBloodPressureMetaData hiBloodPressureMetaData = (HiBloodPressureMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiBloodPressureMetaData.class);
        List<String> activityActions = hiBloodPressureMetaData.getActivityActions();
        if (hiBloodPressureMetaData == null || koq.b(activityActions)) {
            return;
        }
        list2.addAll(activityActions);
    }

    private List<String> c(int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        while (i2 <= i3) {
            arrayList.add(BloodPressureUtil.a(i2));
            i2 += 10;
        }
        return arrayList;
    }

    private void b() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.input_blood_press_title);
        this.bb = customTitleBar;
        customTitleBar.setTitleBarBackgroundColor(0);
        this.k = (LinearLayout) findViewById(R.id.input_blood_pressure_date_layout);
        this.au = (LinearLayout) findViewById(R.id.input_blood_pressure_time_layout);
        ((HealthTextView) findViewById(R.id.input_blood_pressure_text)).setText(getString(R$string.IDS_hw_show_main_home_page_bloodpressure));
        ((HealthTextView) findViewById(R.id.input_blood_pressure_bmp)).setText(getString(R$string.IDS_hw_health_show_pulse_heart_bmp));
        this.g = (HealthTextView) findViewById(R.id.input_blood_pressure_date);
        this.f = (HealthTextView) findViewById(R.id.input_blood_pressure_time);
        this.at = (HealthButton) findViewById(R.id.input_blood_pressure_confirm);
        this.w = (HealthTextView) findViewById(R.id.input_blood_pressure_high);
        this.y = (HealthTextView) findViewById(R.id.input_blood_pressure_low);
        this.x = (HealthTextView) findViewById(R.id.text_blood_pressure_high);
        this.ai = (HealthTextView) findViewById(R.id.text_blood_pressure_low);
        this.av = (HealthTextView) findViewById(R.id.input_blood_pressure_bmp_number);
        this.b = (ImageView) findViewById(R.id.input_blood_pressure_bmp_add);
        this.as = findViewById(R.id.input_blood_pressure_bmp_layout);
        ScrollScaleView scrollScaleView = (ScrollScaleView) findViewById(R.id.input_blood_pressure_high_scale);
        this.r = scrollScaleView;
        scrollScaleView.setCustomStartColor(getColor(R.color._2131296532_res_0x7f090114));
        ScrollScaleView scrollScaleView2 = (ScrollScaleView) findViewById(R.id.input_blood_pressure_low_scale);
        this.am = scrollScaleView2;
        scrollScaleView2.setCustomStartColor(getColor(R.color._2131296532_res_0x7f090114));
        ScrollScaleView scrollScaleView3 = (ScrollScaleView) findViewById(R.id.input_blood_pressure_bmp_scale);
        this.ap = scrollScaleView3;
        scrollScaleView3.setCustomStartColor(getColor(R.color._2131296532_res_0x7f090114));
        this.v = (ImageView) findViewById(R.id.input_blood_pressure_date_arrow);
        this.z = (ImageView) findViewById(R.id.input_blood_pressure_time_arrow);
        if (LanguageUtil.bc(this.h)) {
            this.v.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            this.z.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            this.v.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
            this.z.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
        }
        this.u = (HealthTextView) findViewById(R.id.input_blood_pressure_high_unit);
        this.ak = (HealthTextView) findViewById(R.id.input_blood_pressure_low_unit);
        this.aw = (HealthTextView) findViewById(R.id.input_blood_pressure_bpm_unit);
        this.m = (HealthFlowLayout) findViewById(R.id.measure_action_flow_layout);
        this.p = (HealthSubHeader) findViewById(R.id.blood_measure_hwSubHeader);
        this.aw.setText(BaseApplication.getContext().getResources().getQuantityString(R.plurals._2130903084_res_0x7f03002c, 1).replace("%d", ""));
        s();
    }

    private void s() {
        View findViewById = findViewById(R.id.measure_activity_custom);
        this.j = findViewById;
        findViewById.setOnClickListener(new View.OnClickListener() { // from class: qdc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InputBloodPressureActivity.this.dAy_(view);
            }
        });
    }

    public /* synthetic */ void dAy_(View view) {
        if (nsn.a(1000)) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        HealthRecycleView healthRecycleView = new HealthRecycleView(this.h);
        healthRecycleView.setScrollBarStyle(R.style.health_scrollbar);
        healthRecycleView.setLayoutManager(new LinearLayoutManager(this.h));
        final CustomActionDialogAdapter customActionDialogAdapter = new CustomActionDialogAdapter(this.h);
        customActionDialogAdapter.c(this.c);
        healthRecycleView.setAdapter(customActionDialogAdapter);
        CustomAlertDialog.Builder cyn_ = new CustomAlertDialog.Builder(this.h).cyp_(healthRecycleView).e(R$string.IDS_custom_delete_tag).cyo_(R$string.IDS_settings_button_ok, new DialogInterface.OnClickListener() { // from class: qde
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                InputBloodPressureActivity.this.dAx_(customActionDialogAdapter, dialogInterface, i2);
            }
        }).cyn_(R$string.IDS_settings_button_cancal, new DialogInterface.OnClickListener() { // from class: qdj
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i2);
            }
        });
        cyn_.b().setTextColor(this.h.getColor(R.color._2131296518_res_0x7f090106));
        cyn_.b().setText(this.h.getString(R$string.IDS_hw_show_healthdata_bloodsugar_delete));
        cyn_.d().setTextColor(this.h.getColor(R.color._2131296532_res_0x7f090114));
        CustomAlertDialog c2 = cyn_.c();
        c2.setCanceledOnTouchOutside(false);
        if (this.c.size() > 5) {
            WindowManager.LayoutParams attributes = c2.getWindow().getAttributes();
            attributes.height = nsn.c(this.h, 340.0f);
            c2.getWindow().setAttributes(attributes);
        }
        c2.show();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dAx_(CustomActionDialogAdapter customActionDialogAdapter, DialogInterface dialogInterface, int i2) {
        d(customActionDialogAdapter.c());
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i2);
    }

    private void j() {
        this.k.setOnClickListener(this);
        this.au.setOnClickListener(this);
        this.b.setOnClickListener(this);
        this.at.setOnClickListener(this);
        this.bb.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: qdd
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InputBloodPressureActivity.this.dAu_(view);
            }
        });
    }

    public /* synthetic */ void dAu_(View view) {
        LogUtil.a("InputBloodPressureActivity", "initClickEvent onClick mIsShowInputting = ", Boolean.valueOf(this.ad));
        if (!this.ad) {
            setResult(0);
            finish();
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        d(false);
        this.ad = false;
        if (this.ac) {
            this.bb.setRightSoftkeyVisibility(8);
            this.bb.setRightButtonVisibility(8);
        } else {
            this.bb.setRightSoftkeyBackground(nrz.cKl_(this.h, R.drawable._2131430288_res_0x7f0b0b90), nsf.h(R$string.IDS_hwh_open_service_toolbar_edit));
            this.bb.setRightSoftkeyVisibility(0);
            this.bb.setRightButtonDrawable(ContextCompat.getDrawable(this.h, R.drawable._2131430194_res_0x7f0b0b32), nsf.h(R$string.accessibility_more_item));
            this.bb.setRightButtonVisibility(0);
        }
        d(false, 8);
        e();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void f() {
        if (LanguageUtil.bc(this.h)) {
            this.bb.setLeftButtonDrawable(getResources().getDrawable(R.drawable._2131428443_res_0x7f0b045b), nsf.h(R$string.accessibility_go_back));
        } else {
            this.bb.setLeftButtonDrawable(getResources().getDrawable(R.drawable._2131428438_res_0x7f0b0456), nsf.h(R$string.accessibility_go_back));
        }
    }

    private void k() {
        Intent intent = getIntent();
        if (intent != null) {
            this.ac = intent.getBooleanExtra("isShowInput", false);
            this.ab = intent.getBooleanExtra("isInputData", true);
        }
        if (this.ac) {
            d(true);
            d(true, 0);
            this.bb.setRightSoftkeyVisibility(8);
            this.bb.setRightButtonVisibility(8);
            this.bb.setTitleText(this.h.getString(R$string.IDS_hw_health_show_healthdata_input));
            this.at.setVisibility(0);
            this.b.setVisibility(0);
            return;
        }
        d(false);
        d(false, 8);
        this.bb.setRightButtonVisibility(0);
        this.bb.setRightButtonDrawable(ContextCompat.getDrawable(this.h, R.drawable._2131430194_res_0x7f0b0b32), nsf.h(R$string.accessibility_more_item));
        this.bb.setRightSoftkeyVisibility(0);
        this.bb.setRightSoftkeyBackground(nrz.cKl_(this.h, R.drawable._2131430288_res_0x7f0b0b90), nsf.h(R$string.IDS_hwh_open_service_toolbar_edit));
        this.bb.setTitleText(this.h.getString(R$string.IDS_blood_pressure_detail));
        this.at.setVisibility(8);
        this.bb.setRightSoftkeyOnClickListener(new View.OnClickListener() { // from class: qdh
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InputBloodPressureActivity.this.dAv_(view);
            }
        });
        this.bb.setRightButtonOnClickListener(new View.OnClickListener() { // from class: qdn
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                InputBloodPressureActivity.this.dAw_(view);
            }
        });
        this.b.setVisibility(8);
    }

    public /* synthetic */ void dAv_(View view) {
        this.ad = true;
        this.bb.setRightSoftkeyVisibility(8);
        this.bb.setRightButtonDrawable(ContextCompat.getDrawable(this, R.drawable._2131430292_res_0x7f0b0b94), nsf.h(R$string.IDS_contact_confirm));
        if (this.ab) {
            this.b.setVisibility(0);
            d(true, 0);
        }
        this.bb.setLeftButtonDrawable(ContextCompat.getDrawable(this, R.drawable._2131430274_res_0x7f0b0b82), nsf.h(R$string.accessibility_close));
        d(true);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dAw_(View view) {
        d();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d(boolean z) {
        this.j.setVisibility((this.q && z) ? 0 : 8);
        this.e.b(z);
    }

    private void d(boolean z, int i2) {
        a(z, i2);
    }

    private void a(boolean z, int i2) {
        this.r.setNoScroll(z);
        this.am.setNoScroll(z);
        this.ap.setNoScroll(z);
        this.k.setEnabled(z);
        this.au.setEnabled(z);
        this.v.setVisibility(i2);
        this.z.setVisibility(i2);
        f();
    }

    private void d() {
        LogUtil.a("InputBloodPressureActivity", "showNotInput onClick mIsShowInputting = ", Boolean.valueOf(this.ad));
        if (!this.ad) {
            p();
            return;
        }
        if (this.ab && m()) {
            return;
        }
        this.ad = false;
        f();
        this.r.setNoScroll(false);
        this.am.setNoScroll(false);
        this.ap.setNoScroll(false);
        c();
        if (this.ac) {
            this.bb.setRightSoftkeyVisibility(8);
            this.bb.setRightButtonVisibility(8);
            this.b.setVisibility(0);
        } else {
            this.bb.setRightSoftkeyBackground(ContextCompat.getDrawable(this.h, R.drawable._2131430288_res_0x7f0b0b90), nsf.h(R$string.IDS_hwh_open_service_toolbar_edit));
            this.bb.setRightSoftkeyVisibility(0);
            this.bb.setRightButtonDrawable(ContextCompat.getDrawable(this.h, R.drawable._2131430194_res_0x7f0b0b32), nsf.h(R$string.accessibility_more_item));
            this.bb.setRightButtonVisibility(0);
            this.b.setVisibility(8);
            a(false, 8);
        }
    }

    private void p() {
        final PrivacyDataModel privacyDataModel = new PrivacyDataModel();
        privacyDataModel.setStartTime(this.l);
        privacyDataModel.setEndTime(this.l);
        privacyDataModel.setModifyTime(this.l);
        privacyDataModel.setClientId(this.i);
        String string = getResources().getString(R$string.IDS_hw_health_show_healthdata_mmhg_str);
        privacyDataModel.setDataTitle(BloodPressureUtil.a(this.t) + "/" + BloodPressureUtil.a(this.t) + " " + string);
        privacyDataModel.putInt(BleConstants.BLOODPRESSURE_DIASTOLIC, (int) this.ah);
        privacyDataModel.putInt(BleConstants.BLOODPRESSURE_SYSTOLIC, (int) this.t);
        int i2 = (int) this.ax;
        if (i2 == 0) {
            privacyDataModel.putInt(BleConstants.BLOODPRESSURE_SPHYGMUS, Integer.MIN_VALUE);
        } else {
            privacyDataModel.putInt(BleConstants.BLOODPRESSURE_SPHYGMUS, i2);
        }
        privacyDataModel.putInt("measureAbnormal", this.aj);
        final PopViewList popViewList = new PopViewList(this.h, this.bb, new ArrayList(Arrays.asList(getResources().getString(R$string.IDS_privacy_data_detail))));
        popViewList.e(new PopViewList.PopViewClickListener() { // from class: qdb
            @Override // com.huawei.ui.commonui.popupview.PopViewList.PopViewClickListener
            public final void setOnClick(int i3) {
                InputBloodPressureActivity.this.a(popViewList, privacyDataModel, i3);
            }
        });
    }

    public /* synthetic */ void a(PopViewList popViewList, PrivacyDataModel privacyDataModel, int i2) {
        popViewList.b();
        PageModelArgs pageModelArgs = new PageModelArgs();
        pageModelArgs.setDataSource(3);
        pageModelArgs.setPageType(107);
        Intent intent = new Intent();
        intent.putExtra("extra_privacy_data_model", privacyDataModel);
        intent.putExtra("extra_page_model_args", pageModelArgs);
        intent.setClass(this.h, PrivacyDetailActivity.class);
        startActivity(intent);
    }

    private void c() {
        if (this.ab) {
            n();
            qkh.c().b(this.an, new e(new a(this, this.aa, new double[]{this.t, this.ah, this.ax}, this.e.c())));
            LogUtil.a("InputBloodPressureActivity", "delete data: ", Long.toString(this.an));
            return;
        }
        o();
    }

    private void o() {
        HiHealthNativeApi.a(this.h).fetchDataSource(HiDataSourceFetchOption.builder().a(1).c(new int[]{this.i}).e(), new c(new a(this, this.aa, new double[]{this.t, this.ah, this.ax}, this.e.c())));
    }

    private void g() {
        Intent intent = getIntent();
        if (intent != null) {
            this.t = intent.getDoubleExtra(MediaManager.ROPE_MEDIA_HIGH, 128.0d);
            this.ah = intent.getDoubleExtra("low", 88.0d);
            this.l = intent.getLongExtra("deletetime", -1L);
            this.ax = intent.getDoubleExtra("bmp", 0.0d);
            this.aj = intent.getIntExtra("measureAbnormal", 1);
            this.i = intent.getIntExtra("clientId", 0);
            this.d = intent.getIntExtra("BI_Tag", 0);
            this.af = intent.getBooleanExtra("isStartNewActivity", false);
            this.o = intent.getStringExtra("from");
            this.al = this.t;
            this.ar = this.ah;
            this.an = this.l;
            a(this.ax);
            if (this.l != -1) {
                if (Double.compare(this.ax, 0.0d) == 0) {
                    qkh c2 = qkh.c();
                    Context applicationContext = getApplicationContext();
                    long j2 = this.l;
                    c2.c(applicationContext, j2, j2, new d(this));
                }
                HiDataReadOption hiDataReadOption = new HiDataReadOption();
                hiDataReadOption.setType(new int[]{DicDataTypeUtil.DataType.BLOOD_BEFORE_ACTIVITY.value()});
                hiDataReadOption.setStartTime(this.l);
                hiDataReadOption.setEndTime(this.l);
                HiHealthNativeApi.a(this.h).readHiHealthData(hiDataReadOption, new j(this));
            }
            if (this.ac) {
                this.aa = System.currentTimeMillis();
            } else {
                this.aa = this.l;
            }
            this.aq = this.ax;
            this.ao = this.aa;
        }
    }

    private void h() {
        int i2 = (int) (this.t - 40.0d);
        int i3 = (int) (this.ah - 30.0d);
        this.r.setSelectedPosition(i2);
        this.am.setSelectedPosition(i3);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(this.aa);
        this.g.setText(new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "yyyyMd")).format(calendar.getTime()));
        this.f.setText(nsj.c(this.h, this.aa, 1));
        this.w.setText(BloodPressureUtil.a(this.t));
        this.y.setText(BloodPressureUtil.a(this.ah));
    }

    private void e() {
        this.t = this.al;
        this.ah = this.ar;
        double d2 = this.aq;
        this.ax = d2;
        this.l = this.an;
        this.aa = this.ao;
        a(d2);
        h();
    }

    private boolean m() {
        long currentTimeMillis = System.currentTimeMillis();
        long j2 = this.aa;
        if (currentTimeMillis < j2) {
            nrh.e(this.h, R$string.IDS_hw_health_show_healthdata_timeerror);
            return true;
        }
        if (this.ah > this.t) {
            nrh.e(this.h, R$string.IDS_hw_health_show_healthdata_bloodpresserror);
            return true;
        }
        LogUtil.a("InputBloodPressureActivity", "isDataError mInsertTime = ", Long.valueOf(j2));
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(double d2) {
        if (((int) d2) != 0) {
            this.as.setVisibility(0);
            this.ap.setSelectedPosition((int) (d2 - 30.0d));
            this.b.setBackgroundResource(R.drawable._2131430275_res_0x7f0b0b83);
            this.av.setText(BloodPressureUtil.a(d2));
            this.ae = true;
        } else {
            this.as.setVisibility(8);
            this.b.setBackgroundResource(R.drawable._2131430269_res_0x7f0b0b7d);
            if (!this.ac && !this.ad) {
                this.b.setVisibility(8);
            }
            this.ae = false;
        }
        this.aq = d2;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.k) {
            if (this.ac) {
                t();
            } else if (this.ad) {
                t();
            }
        } else if (view == this.au) {
            if (this.ac) {
                q();
            } else if (this.ad) {
                q();
            }
        } else if (view == this.b) {
            if (this.ae) {
                a(0.0d);
                this.ax = 0.0d;
            } else {
                a(75.0d);
                this.ax = 75.0d;
            }
        } else if (view == this.at && !m()) {
            this.at.setEnabled(false);
            this.at.setAlpha(0.6f);
            Context context = this.h;
            long j2 = this.aa;
            gmn.e(context, new f(this, j2, new a(this, j2, new double[]{this.t, this.ah, this.ax}, this.e.c())));
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void t() {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(this.aa);
        HealthDatePickerDialog healthDatePickerDialog = new HealthDatePickerDialog(this, new HealthDatePickerDialog.DateSelectedListener() { // from class: qdf
            @Override // com.huawei.ui.commonui.datepicker.HealthDatePickerDialog.DateSelectedListener
            public final void onDateSelected(int i2, int i3, int i4) {
                InputBloodPressureActivity.this.a(calendar, i2, i3, i4);
            }
        }, new GregorianCalendar(calendar.get(1), calendar.get(2), calendar.get(5)));
        Calendar calendar2 = Calendar.getInstance();
        healthDatePickerDialog.d(new GregorianCalendar(2014, 0, 1), new GregorianCalendar(calendar2.get(1), calendar2.get(2), calendar2.get(5)));
        healthDatePickerDialog.c(true);
        healthDatePickerDialog.a(getColor(R.color._2131296532_res_0x7f090114));
        healthDatePickerDialog.show();
    }

    public /* synthetic */ void a(Calendar calendar, int i2, int i3, int i4) {
        calendar.set(i2, i3, i4);
        this.aa = calendar.getTimeInMillis();
        this.g.setText(new SimpleDateFormat(DateFormat.getBestDateTimePattern(Locale.getDefault(), "yyyyMd")).format(Long.valueOf(this.aa)));
    }

    private void q() {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(this.aa);
        HealthTimePickerDialog healthTimePickerDialog = new HealthTimePickerDialog(this, new HealthTimePickerDialog.TimeSelectedListener() { // from class: qdp
            @Override // com.huawei.ui.commonui.timepicker.HealthTimePickerDialog.TimeSelectedListener
            public final void onTimeSelected(int i2, int i3) {
                InputBloodPressureActivity.this.c(calendar, i2, i3);
            }
        });
        healthTimePickerDialog.e(0, 0, 0, calendar.get(11), calendar.get(12));
        healthTimePickerDialog.b(getString(R$string.IDS_hw_health_show_healthdata_measure_time));
        healthTimePickerDialog.a(getColor(R.color._2131296532_res_0x7f090114));
        healthTimePickerDialog.d(getColor(R.color._2131296532_res_0x7f090114));
        healthTimePickerDialog.show();
    }

    public /* synthetic */ void c(Calendar calendar, int i2, int i3) {
        calendar.set(11, i2);
        calendar.set(12, i3);
        long timeInMillis = calendar.getTimeInMillis();
        this.aa = timeInMillis;
        this.f.setText(nsj.c(this.h, timeInMillis, 1));
        calendar.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        if (TextUtils.isEmpty(this.o)) {
            hashMap.put("from", 1);
        } else {
            String str = this.o;
            str.hashCode();
            if (str.equals("/todoTask/TodoListActivity")) {
                hashMap.put("from", 3);
            } else if (str.equals("/PluginHealthModel/HealthModelActivity")) {
                hashMap.put("from", 2);
            } else {
                hashMap.put("from", 1);
            }
        }
        if (this.d == 1) {
            hashMap.put("type", "1");
        } else {
            hashMap.put("type", "2");
        }
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_HEALTH_BLOODPRESS_INPUT_2030027.value(), hashMap, 0);
    }

    /* loaded from: classes6.dex */
    static class c implements HiDataClientListener {
        private final a d;

        c(a aVar) {
            this.d = aVar;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataClientListener
        public void onResult(List<HiHealthClient> list) {
            String str;
            if (koq.c(list)) {
                str = list.get(0).getDeviceUuid();
            } else {
                LogUtil.h("InputBloodPressureActivity", "Failed to read deviceUuid");
                str = "-1";
            }
            this.d.b(str);
        }
    }

    /* loaded from: classes6.dex */
    static class d implements CommonUiBaseResponse {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<InputBloodPressureActivity> f10051a;

        d(InputBloodPressureActivity inputBloodPressureActivity) {
            this.f10051a = new WeakReference<>(inputBloodPressureActivity);
        }

        @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
        public void onResponse(int i, Object obj) {
            InputBloodPressureActivity inputBloodPressureActivity = this.f10051a.get();
            if (inputBloodPressureActivity != null) {
                Message.obtain(inputBloodPressureActivity.n, 2, obj).sendToTarget();
            }
        }
    }

    /* loaded from: classes6.dex */
    public static class i extends BaseHandler<InputBloodPressureActivity> {
        i(InputBloodPressureActivity inputBloodPressureActivity) {
            super(inputBloodPressureActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dAz_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(InputBloodPressureActivity inputBloodPressureActivity, Message message) {
            int i = message.what;
            if (i != 1) {
                if (i == 2) {
                    inputBloodPressureActivity.a(((Double) message.obj).doubleValue());
                    return;
                }
                if (i == 3) {
                    if (!(message.obj instanceof List)) {
                        LogUtil.h("InputBloodPressureActivity", "msg.obj is not List");
                        return;
                    }
                    List list = (List) message.obj;
                    if (!koq.b(list)) {
                        inputBloodPressureActivity.a((HiHealthData) list.get(0));
                        return;
                    } else {
                        LogUtil.a("InputBloodPressureActivity", "refreshBloodPressure no data");
                        inputBloodPressureActivity.at.setEnabled(false);
                        return;
                    }
                }
                LogUtil.h("InputBloodPressureActivity", "handleMessage what undefined");
                return;
            }
            nro.e(inputBloodPressureActivity.getApplicationContext(), 8);
            if (inputBloodPressureActivity.af) {
                Intent intent = new Intent(inputBloodPressureActivity, (Class<?>) KnitBloodPressureActivity.class);
                intent.putExtra("edit", true);
                intent.putExtra("time", inputBloodPressureActivity.aa);
                inputBloodPressureActivity.setResult(0, intent);
                inputBloodPressureActivity.finish();
                inputBloodPressureActivity.startActivity(intent);
            } else {
                Intent intent2 = new Intent();
                intent2.putExtra("edit", true);
                intent2.putExtra("time", inputBloodPressureActivity.aa);
                inputBloodPressureActivity.setResult(0, intent2);
                inputBloodPressureActivity.finish();
            }
            for (String str : InputBloodPressureActivity.f10049a) {
                LogUtil.a("InputBloodPressureActivity", "InsertTime: " + new Date(inputBloodPressureActivity.aa));
                ObserverManagerUtil.c(str, Long.valueOf(inputBloodPressureActivity.aa));
            }
        }
    }

    /* loaded from: classes6.dex */
    static class f implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        private final a f10052a;
        private final WeakReference<InputBloodPressureActivity> d;
        private final long e;

        f(InputBloodPressureActivity inputBloodPressureActivity, long j, a aVar) {
            this.d = new WeakReference<>(inputBloodPressureActivity);
            this.f10052a = aVar;
            this.e = j;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            InputBloodPressureActivity inputBloodPressureActivity = this.d.get();
            if (inputBloodPressureActivity != null) {
                LogUtil.h("InputBloodPressureActivity", "activity is notNull");
                inputBloodPressureActivity.n();
            }
            qkh.c().b(this.e, new e(this.f10052a));
        }
    }

    /* loaded from: classes6.dex */
    static class b implements IBaseResponseCallback {
        WeakReference<InputBloodPressureActivity> c;

        b(InputBloodPressureActivity inputBloodPressureActivity) {
            this.c = new WeakReference<>(inputBloodPressureActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            if (i == 0) {
                LogUtil.a("InputBloodPressureActivity", "onResponse: start sync cloud");
                qrc.a();
            }
            InputBloodPressureActivity inputBloodPressureActivity = this.c.get();
            if (inputBloodPressureActivity == null) {
                LogUtil.h("InputBloodPressureActivity", "activity is null");
            } else {
                LogUtil.a("InputBloodPressureActivity", "onResponse, errorCode = ", Integer.valueOf(i), ", data = ", obj);
                Message.obtain(inputBloodPressureActivity.n, 1).sendToTarget();
            }
        }
    }

    /* loaded from: classes6.dex */
    static class e implements IBaseResponseCallback {
        private final a d;

        e(a aVar) {
            this.d = aVar;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("InputBloodPressureActivity", "DeleteDataCallback onResponse Success");
            if (i == 0) {
                LogUtil.a("InputBloodPressureActivity", "DeleteDataResponseCallback onResponse delete successful");
            } else {
                LogUtil.a("InputBloodPressureActivity", "DeleteDataResponseCallback onResponse delete failed");
            }
            this.d.b("-1");
        }
    }

    /* loaded from: classes6.dex */
    static class a {

        /* renamed from: a, reason: collision with root package name */
        private final double[] f10050a;
        private final long b;
        private final WeakReference<InputBloodPressureActivity> c;
        private final List<qkv> d;

        a(InputBloodPressureActivity inputBloodPressureActivity, long j, double[] dArr, List<qkv> list) {
            this.c = new WeakReference<>(inputBloodPressureActivity);
            this.b = j;
            this.f10050a = dArr;
            this.d = list;
        }

        protected void b(String str) {
            double[] copyOf = Arrays.copyOf(this.f10050a, 4);
            copyOf[3] = BloodPressureUtil.a(this.d);
            qkh.c().e(this.b, copyOf, BloodPressureUtil.e(this.d), str, new b(this.c.get()));
        }
    }

    /* loaded from: classes6.dex */
    static class j implements HiDataReadResultListener {
        WeakReference<InputBloodPressureActivity> d;

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        j(InputBloodPressureActivity inputBloodPressureActivity) {
            this.d = new WeakReference<>(inputBloodPressureActivity);
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            LogUtil.a("InputBloodPressureActivity", "ReadComplete onResponse Success");
            InputBloodPressureActivity inputBloodPressureActivity = this.d.get();
            if (inputBloodPressureActivity == null || inputBloodPressureActivity.isFinishing()) {
                LogUtil.h("InputBloodPressureActivity", "activity is not exist");
                return;
            }
            Object obj2 = obj instanceof SparseArray ? ((SparseArray) obj).get(DicDataTypeUtil.DataType.BLOOD_BEFORE_ACTIVITY.value()) : null;
            Message obtainMessage = inputBloodPressureActivity.n.obtainMessage();
            obtainMessage.what = 3;
            obtainMessage.arg1 = i;
            obtainMessage.obj = obj2;
            inputBloodPressureActivity.n.sendMessage(obtainMessage);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
