package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.callback.WeightInsertStatusCallback;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.fatscale.multiusers.WeightDataManager;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.WeightBaseResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.datepicker.HealthDatePickerDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.scrollview.ScrollScaleView;
import com.huawei.ui.commonui.subheader.HealthSubHeader;
import com.huawei.ui.commonui.timepicker.HealthTimePickerDialog;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.InputWeightActivity;
import com.huawei.ui.main.stories.health.adapter.HealthDataStyleA02Adapter;
import com.huawei.ui.main.stories.health.util.BaseHealthClickListener;
import com.huawei.ui.main.stories.health.views.healthdata.MarketingView;
import com.huawei.ui.main.stories.privacy.template.model.bean.PageModelArgs;
import com.huawei.ui.main.stories.privacy.template.model.bean.PrivacyDataModel;
import com.huawei.ui.main.stories.privacy.template.view.showdata.PrivacyDetailActivity;
import defpackage.cfe;
import defpackage.cfi;
import defpackage.ckm;
import defpackage.dfd;
import defpackage.dfg;
import defpackage.dks;
import defpackage.gmn;
import defpackage.ixx;
import defpackage.jcf;
import defpackage.mcv;
import defpackage.nqc;
import defpackage.nro;
import defpackage.nry;
import defpackage.nsf;
import defpackage.nsj;
import defpackage.qkh;
import defpackage.qsj;
import defpackage.qyr;
import defpackage.rag;
import defpackage.scn;
import health.compact.a.CommonUtils;
import health.compact.a.EnvironmentInfo;
import health.compact.a.LanguageUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* loaded from: classes.dex */
public class InputWeightActivity extends BaseActivity implements View.OnClickListener, BaseHealthClickListener {
    private long aa;
    private int ab;
    private ImageView ae;
    private boolean af;
    private boolean ag;
    private MarketingView ah;
    private long ai;
    private View aj;
    private View ak;
    private HealthTextView al;
    private HealthTextView am;
    private LinearLayoutManager an;
    private int ao;
    private HealthTextView ap;
    private int ar;
    private HealthButton as;
    private HealthTextView at;
    private LinearLayout au;
    private HealthTextView av;
    private d aw;
    private CustomTitleBar ax;
    private ScrollScaleView ay;
    private HealthSubHeader ba;
    private String bb;
    private int bc;
    private boolean d;
    private ScrollScaleView f;
    private ImageView g;
    private LinearLayout h;
    private HealthButton j;
    private Context k;
    private HealthTextView l;
    private String m;
    private LinearLayout n;
    private int o;
    private int p;
    private HealthDataStyleA02Adapter r;
    private a s;
    private String v;
    private qkh w;
    private dfd x;
    private HealthSubHeader y;
    private ImageView z;
    private int aq = 0;
    private double az = 0.0d;
    private double i = 1.0d;
    private double ad = 0.0d;
    private double ac = 1.0d;
    private long q = -1;
    private boolean c = false;

    /* renamed from: a, reason: collision with root package name */
    private boolean f10055a = true;
    private boolean b = false;
    private boolean e = false;
    private Handler u = new c(this);
    private long t = -1;

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
    }

    /* loaded from: classes6.dex */
    static class e implements WeightInsertStatusCallback {

        /* renamed from: a, reason: collision with root package name */
        c f10061a;
        WeakReference<c> c;

        e(c cVar) {
            WeakReference<c> weakReference = new WeakReference<>(cVar);
            this.c = weakReference;
            this.f10061a = weakReference.get();
        }

        @Override // com.huawei.health.device.callback.WeightInsertStatusCallback
        public void isSuccess(boolean z) {
            if (this.f10061a != null) {
                LogUtil.a("UIHLH_InputWeightActivity", "InsertWeightResponseCallback,insert successful ");
                this.f10061a.sendMessage(this.f10061a.obtainMessage(3, 0, 0));
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.health_data_inputweight);
        this.k = this;
        this.s = new a(this);
        this.aw = new d(this);
        this.x = new dfd(10006);
        j();
        i();
        a(UnitUtil.c(this.ad, 1), false);
        d(this.ac, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(double d2, boolean z) {
        int a2 = UnitUtil.a();
        String a3 = nsf.a(a2 != 1 ? a2 != 3 ? R.plurals._2130903215_res_0x7f0300af : R.plurals._2130903216_res_0x7f0300b0 : R.plurals._2130903105_res_0x7f030041, UnitUtil.e(d2, Locale.getDefault()), UnitUtil.e(d2, 1, 1));
        jcf.bEB_(this.aj, a3, false);
        jcf.bEB_(this.ay, nsf.b(R$string.accessibility_input_weight, a3), false);
        if (z) {
            jcf.bEk_(this.ay, a3);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(double d2, boolean z) {
        String e2 = UnitUtil.e(d2, 2, 1);
        jcf.bEB_(this.ak, e2, false);
        jcf.bEB_(this.f, nsf.b(R$string.accessibility_input_weight, e2), false);
        if (z) {
            jcf.bEk_(this.f, e2);
        }
    }

    private void i() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.a("UIHLH_InputWeightActivity", "intent is null");
            return;
        }
        this.az = CommonUtils.a(intent.getStringExtra("weight"));
        this.i = CommonUtils.a(intent.getStringExtra("bodyFat"));
        this.q = intent.getLongExtra("deleteTime", -1L);
        this.t = intent.getLongExtra("deleteEndTime", -1L);
        this.o = intent.getIntExtra("clientId", 0);
        this.v = intent.getStringExtra("from");
        double d2 = this.az;
        if (d2 > 0.0d && d2 < 10.0d) {
            this.az = 10.0d;
        }
        this.ad = this.az;
        this.ac = this.i;
        this.c = intent.getBooleanExtra("isShowBodyFat", false);
        this.f10055a = intent.getBooleanExtra("isShowInput", false);
        this.af = intent.getBooleanExtra("isFromPlan", false);
        this.bb = intent.getStringExtra(JsbMapKeyNames.H5_USER_ID);
        this.ag = intent.getIntExtra("source", 0) == 7;
        h();
        if (this.c) {
            this.h.setVisibility(0);
            this.g.setBackgroundResource(R.drawable._2131430157_res_0x7f0b0b0d);
        } else {
            if (!this.f10055a) {
                this.y.setVisibility(4);
            }
            this.h.setVisibility(8);
            this.g.setBackgroundResource(R.drawable._2131430153_res_0x7f0b0b09);
        }
        double d3 = this.az;
        if (d3 <= 0.0d) {
            ReleaseLogUtil.a("UIHLH_InputWeightActivity", "initData mWeight ", Double.valueOf(d3));
            this.az = 65.0d;
        }
        if (Math.abs(this.i) < 0.5d) {
            ReleaseLogUtil.a("UIHLH_InputWeightActivity", "initData mBodyFat ", Double.valueOf(this.i));
            this.i = 20.0d;
        }
        f();
        g();
    }

    private void f() {
        int doubleValue = (int) ((new BigDecimal(Double.toString(this.i)).setScale(1, RoundingMode.HALF_UP).doubleValue() - 1.0d) * 10.0d);
        LogUtil.c("UIHLH_InputWeightActivity", "bodyfatposition == ", Integer.valueOf(doubleValue));
        this.ay.setSelectedPosition(rag.d(this.az));
        this.f.setSelectedPosition(doubleValue);
    }

    private void g() {
        long j = this.q;
        if (j != -1) {
            this.ai = j;
        } else {
            this.ai = System.currentTimeMillis();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(this.ai);
        this.bc = calendar.get(1);
        this.ar = calendar.get(2);
        this.p = calendar.get(5);
        this.ab = calendar.get(11);
        this.ao = calendar.get(12);
        qkh c2 = qkh.c();
        this.w = c2;
        c2.b();
        long j2 = this.ai;
        this.aa = j2;
        this.at.setText(DateFormatUtil.d(j2, DateFormatUtil.DateFormatType.DATE_FORMAT_YYYYMD));
        this.av.setText(nsj.c(this.k, this.ai, 1));
        this.am.setText(UnitUtil.e(UnitUtil.c(this.az, 1), 1, 1));
        d(this.ap, UnitUtil.a(this.az));
        double d2 = this.i;
        if (d2 > 0.0d) {
            String e2 = UnitUtil.e(d2, 1, 1);
            this.m = e2;
            this.al.setText(e2);
        }
        this.r = new HealthDataStyleA02Adapter(0, this.c);
        this.y.setLayoutManager(this.an);
        this.r.b(this);
        this.y.setAdapter(this.r);
        this.y.setSubHeaderBackgroundColor(getColor(R.color._2131296690_res_0x7f0901b2));
        this.ba.setSubHeaderBackgroundColor(getColor(R.color._2131296690_res_0x7f0901b2));
        this.ba.setHeadTitleTextColor(getColor(R.color._2131299236_res_0x7f090ba4));
    }

    private void h() {
        if (this.f10055a) {
            this.ay.setNoScroll(true);
            this.f.setNoScroll(true);
            this.ax.setRightButtonVisibility(0);
            if (LanguageUtil.bc(this.k)) {
                this.ax.setLeftButtonDrawable(getResources().getDrawable(R.mipmap._2131820922_res_0x7f11017a), nsf.h(R$string.accessibility_go_back));
            } else {
                this.ax.setLeftButtonDrawable(getResources().getDrawable(R.drawable._2131428438_res_0x7f0b0456), nsf.h(R$string.accessibility_go_back));
            }
            this.ax.setRightSoftkeyVisibility(8);
            this.ax.setRightButtonVisibility(8);
            this.ax.setTitleText(this.k.getString(R$string.IDS_hw_health_show_healthdata_input));
            this.as.setVisibility(0);
            this.z.setVisibility(0);
            this.ae.setVisibility(0);
            this.j.setVisibility(0);
            if (EnvironmentInfo.k()) {
                this.j.setVisibility(8);
                return;
            }
            return;
        }
        this.ay.setNoScroll(false);
        this.f.setNoScroll(false);
        if (LanguageUtil.bc(this.k)) {
            this.ax.setLeftButtonDrawable(getResources().getDrawable(R.mipmap._2131820922_res_0x7f11017a), nsf.h(R$string.accessibility_go_back));
        } else {
            this.ax.setLeftButtonDrawable(getResources().getDrawable(R.drawable._2131428438_res_0x7f0b0456), nsf.h(R$string.accessibility_go_back));
        }
        this.ax.setRightSoftkeyBackground(getResources().getDrawable(R.drawable._2131430288_res_0x7f0b0b90), nsf.h(R$string.IDS_hwh_open_service_toolbar_edit));
        this.ax.setRightSoftkeyVisibility(0);
        this.ax.setRightButtonVisibility(0);
        this.ax.setRightButtonDrawable(getResources().getDrawable(R.drawable._2131430194_res_0x7f0b0b32), nsf.h(R$string.accessibility_more_item));
        this.ax.setTitleText(this.k.getString(R$string.IDS_hw_base_health_data_history_record));
        this.as.setVisibility(8);
        this.z.setVisibility(8);
        this.ae.setVisibility(8);
        this.j.setVisibility(4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(HealthTextView healthTextView, double d2) {
        healthTextView.setText(qsj.e(d2, false));
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.n) {
            if (this.f10055a) {
                t();
            } else if (this.b) {
                t();
            }
        } else if (view == this.au) {
            if (this.f10055a) {
                u();
            } else if (this.b) {
                u();
            }
        } else {
            ImageView imageView = this.g;
            if (view == imageView) {
                if (this.c) {
                    imageView.setBackgroundResource(R.drawable._2131430153_res_0x7f0b0b09);
                    this.g.setContentDescription(this.k.getString(R$string.IDS_hw_health_talkback_add_bodyfat_rate));
                    this.h.setVisibility(8);
                    this.c = false;
                } else {
                    imageView.setContentDescription(this.k.getString(R$string.IDS_hw_health_talkback_cancel_add_bodyfat_rate));
                    this.g.setBackgroundResource(R.drawable._2131430157_res_0x7f0b0b0d);
                    this.h.setVisibility(0);
                    this.c = true;
                }
            } else {
                HealthButton healthButton = this.as;
                if (view == healthButton) {
                    this.aq = 1;
                    healthButton.setClickable(false);
                    this.as.setBackgroundResource(R.drawable.weight_button_background_disable);
                    l();
                } else if (view == this.j) {
                    qyr.e();
                    dks.e(this.k, "HDK_WEIGHT");
                }
            }
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void l() {
        if (this.w != null) {
            if (!this.c) {
                this.i = 0.0d;
            }
            if (System.currentTimeMillis() < this.ai) {
                Toast.makeText(this.k, getString(R$string.IDS_hw_health_show_healthdata_timeerror), 0).show();
                if (this.aq == 0) {
                    y();
                }
                q();
                return;
            }
            m();
            dfg.d().g();
            if (this.q != -1) {
                ArrayList arrayList = new ArrayList();
                cfe cfeVar = new cfe();
                cfeVar.b(this.q);
                cfeVar.d(this.t);
                arrayList.add(cfeVar);
                WeightDataManager.INSTANCE.removeMapData(MultiUsersManager.INSTANCE.getCurrentUser().i(), arrayList);
                this.w.a(this.k.getApplicationContext(), this.q, this.t, this.s);
                LogUtil.a("UIHLH_InputWeightActivity", "delete data: ", Long.toString(this.q));
                return;
            }
            n();
        }
    }

    private void m() {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        Intent intent = getIntent();
        int intExtra = intent == null ? 0 : intent.getIntExtra("BITag", 0);
        if (intExtra == 1) {
            hashMap.put("type", "1");
        } else {
            hashMap.put("type", "2");
        }
        if ("/PluginHealthModel/HealthModelActivity".equals(this.v)) {
            hashMap.put("from", 2);
        } else if ("/todoTask/TodoListActivity".equals(this.v)) {
            hashMap.put("from", 3);
        } else if ("/PluginHealthModel/IntelligentPlanView".equals(this.v)) {
            hashMap.put("from", 5);
        } else if (this.ag) {
            hashMap.put("from", 7);
        } else {
            hashMap.put("from", 1);
        }
        if (this.i == 0.0d) {
            hashMap.put("dataType", new String[]{"weight"});
        } else {
            hashMap.put("dataType", new String[]{"weight", "fatRate"});
        }
        HashMap hashMap2 = new HashMap();
        if (intExtra == 1) {
            hashMap2.put("type", "1");
        } else {
            hashMap2.put("type", "2");
        }
        mcv.d(this.k).c(this.k.getApplicationContext(), String.valueOf(KakaConstants.TASK_ENTER_TODAY_WEIGHT), hashMap2);
        ixx.d().d(this.k.getApplicationContext(), AnalyticsValue.HEALTH_HEALTH_WEIGHT_INPUT_2030017.value(), hashMap, 0);
        if (this.af) {
            HashMap hashMap3 = new HashMap(2);
            hashMap3.put("click", 1);
            hashMap3.put("type", 2);
            ixx.d().d(this.k, AnalyticsValue.INT_PLAN_1120034.value(), hashMap3, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        this.d = false;
        final ckm ckmVar = new ckm();
        ckmVar.setBodyFatRat((float) this.i);
        ckmVar.setWeight((float) this.az);
        ckmVar.setStartTime(this.ai);
        ckmVar.e(true);
        ckmVar.setEndTime(this.ai);
        this.x.b(new e((c) this.u));
        if ("/PluginHealthModel/HealthModelActivity".equals(this.v) || "/todoTask/TodoListActivity".equals(this.v) || "/HWUserProfileMgr/UserInfoActivity".equals(this.v)) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: qdm
                @Override // java.lang.Runnable
                public final void run() {
                    InputWeightActivity.this.a(ckmVar);
                }
            });
        } else {
            MultiUsersManager.INSTANCE.getCurrentUser(new WeightBaseResponseCallback<cfi>() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.InputWeightActivity.4
                @Override // com.huawei.hwbasemgr.WeightBaseResponseCallback
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void onResponse(int i, cfi cfiVar) {
                    if (cfiVar != null && i == 0) {
                        InputWeightActivity.this.x.b(cfiVar);
                        InputWeightActivity.this.x.onDataChanged(rag.a(), ckmVar);
                    } else {
                        LogUtil.h("UIHLH_InputWeightActivity", "setUserInfo : currentUser is null return");
                        InputWeightActivity.this.a();
                        InputWeightActivity.this.q();
                    }
                }
            });
        }
    }

    public /* synthetic */ void a(ckm ckmVar) {
        cfi mainUser = MultiUsersManager.INSTANCE.getMainUser();
        if (mainUser == null) {
            LogUtil.h("UIHLH_InputWeightActivity", "insert mainUser is null");
            a();
            q();
        } else {
            this.x.b(mainUser);
            this.x.onDataChanged(rag.a(), ckmVar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        runOnUiThread(new Runnable() { // from class: qdo
            @Override // java.lang.Runnable
            public final void run() {
                InputWeightActivity.this.e();
            }
        });
    }

    public /* synthetic */ void e() {
        nry.d(this.k, false, null, getColor(R.color._2131298733_res_0x7f0909ad));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q() {
        this.as.setClickable(true);
        this.as.setBackgroundResource(R.drawable.weight_button_background);
    }

    private void t() {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(this.ai);
        HealthDatePickerDialog healthDatePickerDialog = new HealthDatePickerDialog(this, new HealthDatePickerDialog.DateSelectedListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.InputWeightActivity.5
            @Override // com.huawei.ui.commonui.datepicker.HealthDatePickerDialog.DateSelectedListener
            public void onDateSelected(int i, int i2, int i3) {
                InputWeightActivity.this.bc = i;
                InputWeightActivity.this.ar = i2;
                InputWeightActivity.this.p = i3;
                calendar.set(InputWeightActivity.this.bc, InputWeightActivity.this.ar, InputWeightActivity.this.p);
                calendar.set(11, InputWeightActivity.this.ab);
                calendar.set(12, InputWeightActivity.this.ao);
                InputWeightActivity.this.ai = calendar.getTimeInMillis();
                InputWeightActivity.this.at.setText(DateFormatUtil.d(InputWeightActivity.this.ai, DateFormatUtil.DateFormatType.DATE_FORMAT_YYYYMD));
                calendar.clear();
            }
        }, new GregorianCalendar(this.bc, this.ar, this.p));
        Calendar calendar2 = Calendar.getInstance();
        healthDatePickerDialog.d(new GregorianCalendar(2014, 0, 1), new GregorianCalendar(calendar2.get(1), calendar2.get(2), calendar2.get(5)));
        healthDatePickerDialog.c(true);
        healthDatePickerDialog.a(ContextCompat.getColor(this, R.color._2131299374_res_0x7f090c2e));
        healthDatePickerDialog.show();
    }

    private void u() {
        HealthTimePickerDialog healthTimePickerDialog = new HealthTimePickerDialog(this, new HealthTimePickerDialog.TimeSelectedListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.InputWeightActivity.3
            @Override // com.huawei.ui.commonui.timepicker.HealthTimePickerDialog.TimeSelectedListener
            public void onTimeSelected(int i, int i2) {
                LogUtil.c("UIHLH_InputWeightActivity", "hour=", Integer.valueOf(i), ", minute=", Integer.valueOf(i2));
                InputWeightActivity.this.ab = i;
                InputWeightActivity.this.ao = i2;
                Calendar calendar = Calendar.getInstance();
                calendar.set(1, InputWeightActivity.this.bc);
                calendar.set(2, InputWeightActivity.this.ar);
                calendar.set(5, InputWeightActivity.this.p);
                calendar.set(11, InputWeightActivity.this.ab);
                calendar.set(12, InputWeightActivity.this.ao);
                InputWeightActivity.this.ai = calendar.getTimeInMillis();
                InputWeightActivity.this.av.setText(nsj.c(InputWeightActivity.this.k, InputWeightActivity.this.ai, 1));
                calendar.clear();
            }
        });
        healthTimePickerDialog.b(getString(R$string.IDS_hw_health_show_healthdata_set_time));
        healthTimePickerDialog.e().setSpinnersSelectorPaintColor(ContextCompat.getColor(this, R.color._2131299374_res_0x7f090c2e));
        healthTimePickerDialog.d(ContextCompat.getColor(this, R.color._2131299374_res_0x7f090c2e));
        healthTimePickerDialog.show();
    }

    private void j() {
        this.y = (HealthSubHeader) findViewById(R.id.fat_hwSubHeader);
        this.ba = (HealthSubHeader) findViewById(R.id.weight_hwSubHeader);
        this.an = new LinearLayoutManager(this.k);
        this.ax = (CustomTitleBar) findViewById(R.id.health_healthdata_inputweight_title_layout);
        this.n = (LinearLayout) findViewById(R.id.hw_show_health_data_inputweight_top_datelayout);
        this.au = (LinearLayout) findViewById(R.id.hw_show_health_data_inputweight_top_timelayout);
        this.h = (LinearLayout) findViewById(R.id.hw_show_health_data_inputweight_mid_bodyfatlayout_desc);
        this.g = (ImageView) findViewById(R.id.hw_show_health_data_inputweight_mid_add_bodyfat_tv);
        this.z = (ImageView) findViewById(R.id.hw_health_input_weight_date);
        this.ae = (ImageView) findViewById(R.id.hw_health_input_weight_time);
        this.ah = (MarketingView) findViewById(R.id.input_weight_marketing);
        if (LanguageUtil.bc(this.k)) {
            this.z.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
            this.ae.setImageResource(R.drawable._2131427841_res_0x7f0b0201);
        } else {
            this.z.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
            this.ae.setImageResource(R.drawable._2131427842_res_0x7f0b0202);
        }
        this.at = (HealthTextView) findViewById(R.id.hw_show_health_data_inputweight_top_date);
        this.av = (HealthTextView) findViewById(R.id.hw_show_health_data_inputweight_top_time);
        HealthTextView healthTextView = (HealthTextView) findViewById(R.id.hw_show_health_data_inputweight_bady_fat_rata_text);
        this.l = healthTextView;
        healthTextView.setText(getString(R$string.IDS_hw_health_show_healthdata_bodyfat_rate).toUpperCase(Locale.ENGLISH));
        this.aj = findViewById(R.id.input_weight_mid_weight_layout);
        this.am = (HealthTextView) findViewById(R.id.hw_show_health_data_inputweight_mid_weight);
        HealthTextView healthTextView2 = (HealthTextView) findViewById(R.id.hw_show_health_data_inputweight_mid_weight_unit);
        this.ap = healthTextView2;
        scn.c(healthTextView2);
        this.ak = findViewById(R.id.input_weight_mid_body_fat_layout);
        this.al = (HealthTextView) findViewById(R.id.hw_show_health_data_inputweight_mid_bodyfat);
        this.j = (HealthButton) findViewById(R.id.hw_show_health_data_inputweight_bind_device);
        HealthButton healthButton = (HealthButton) findViewById(R.id.hw_show_health_data_inputweight_confirm);
        this.as = healthButton;
        healthButton.setText(getString(R$string.IDS_hw_health_show_healthdata_confirm).toUpperCase(Locale.ENGLISH));
        o();
        b();
        this.n.setOnClickListener(this);
        this.au.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.as.setOnClickListener(this);
        this.j.setOnClickListener(this);
        p();
        MarketingView marketingView = this.ah;
        if (marketingView != null) {
            marketingView.e(4151);
        }
        View findViewById = findViewById(R.id.hw_show_health_data_input_weight_rl);
        cancelAdaptRingRegion();
        setViewSafeRegion(false, findViewById);
    }

    private void p() {
        this.ax.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.InputWeightActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                InputWeightActivity.this.d();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.ax.setRightSoftkeyOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.InputWeightActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                InputWeightActivity.this.aq = 0;
                InputWeightActivity.this.y();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.ax.setRightButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.InputWeightActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                InputWeightActivity.this.c();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.aq = 0;
        if (this.b) {
            if (LanguageUtil.bc(this.k)) {
                this.ax.setLeftButtonDrawable(getResources().getDrawable(R.mipmap._2131820922_res_0x7f11017a), nsf.h(R$string.accessibility_go_back));
            } else {
                this.ax.setLeftButtonDrawable(getResources().getDrawable(R.drawable._2131428438_res_0x7f0b0456), nsf.h(R$string.accessibility_go_back));
            }
            this.ay.setNoScroll(false);
            this.f.setNoScroll(false);
            this.z.setVisibility(8);
            this.ae.setVisibility(8);
            this.j.setVisibility(4);
            if (this.f10055a) {
                this.ax.setRightSoftkeyVisibility(8);
                this.ax.setRightButtonVisibility(8);
            } else {
                this.ax.setRightSoftkeyBackground(getResources().getDrawable(R.drawable._2131430288_res_0x7f0b0b90), nsf.h(R$string.IDS_hwh_open_service_toolbar_edit));
                this.ax.setRightSoftkeyVisibility(0);
                this.ax.setRightButtonDrawable(getResources().getDrawable(R.drawable._2131430194_res_0x7f0b0b32), nsf.h(R$string.accessibility_more_item));
                this.ax.setRightButtonVisibility(0);
            }
            if (this.c) {
                this.y.setVisibility(0);
            } else {
                this.y.setVisibility(4);
            }
            this.b = false;
            l();
            return;
        }
        r();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (this.b) {
            x();
            this.z.setVisibility(8);
            this.ae.setVisibility(8);
            this.j.setVisibility(4);
            this.ay.setNoScroll(false);
            this.f.setNoScroll(false);
            if (this.c) {
                this.y.setVisibility(0);
            } else {
                this.y.setVisibility(4);
            }
            this.b = false;
            v();
            w();
            this.at.setText(DateFormatUtil.d(this.aa, DateFormatUtil.DateFormatType.DATE_FORMAT_YYYYMD));
            this.av.setText(nsj.c(this.k, this.aa, 1));
            return;
        }
        this.b = true;
        setResult(0);
        if (this.ag) {
            qsj.dIi_(this, false);
        }
        finish();
    }

    private void v() {
        double a2 = UnitUtil.a(this.ad);
        this.am.setText(UnitUtil.e(a2, 1, 1));
        d(this.ap, UnitUtil.a(this.ad));
        double d2 = this.ac;
        if (d2 > 0.0d) {
            this.al.setText(UnitUtil.e(d2, 2, 1));
            d(this.ac, false);
        } else {
            this.c = false;
            LogUtil.c("UIHLH_InputWeightActivity", "mBodyFatLayout.getVisibility() : ", Integer.valueOf(this.h.getVisibility()));
            if (this.h.getVisibility() == 0) {
                this.e = true;
            }
            this.h.setVisibility(8);
            if (!this.f10055a) {
                this.y.setVisibility(4);
            }
        }
        a(a2, false);
    }

    private void w() {
        if (Math.abs(this.ad) < 0.5d) {
            this.ad = 20.0d;
        }
        this.ay.setSelectedPosition(rag.d(this.ad));
        this.f.setSelectedPosition((int) ((new BigDecimal(Double.toString(this.ac)).setScale(1, RoundingMode.HALF_UP).doubleValue() - 1.0d) * 10.0d));
    }

    private void x() {
        if (LanguageUtil.bc(this.k)) {
            this.ax.setLeftButtonDrawable(getResources().getDrawable(R.mipmap._2131820922_res_0x7f11017a), nsf.h(R$string.accessibility_go_back));
        } else {
            this.ax.setLeftButtonDrawable(getResources().getDrawable(R.drawable._2131428438_res_0x7f0b0456), nsf.h(R$string.accessibility_go_back));
        }
        if (this.f10055a) {
            this.ax.setRightSoftkeyVisibility(8);
            this.ax.setRightButtonVisibility(8);
        } else {
            this.ax.setRightSoftkeyVisibility(0);
            this.ax.setRightSoftkeyBackground(getResources().getDrawable(R.drawable._2131430288_res_0x7f0b0b90), nsf.h(R$string.IDS_hwh_open_service_toolbar_edit));
            this.ax.setRightButtonDrawable(getResources().getDrawable(R.drawable._2131430194_res_0x7f0b0b32), nsf.h(R$string.accessibility_more_item));
        }
    }

    private void b() {
        ArrayList arrayList = new ArrayList();
        for (int i = 1; i < 50; i++) {
            arrayList.add(UnitUtil.e(i, 1, 0));
        }
        ScrollScaleView scrollScaleView = (ScrollScaleView) findViewById(R.id.health_healthdata_imputweight_bodyfat_scale);
        this.f = scrollScaleView;
        scrollScaleView.setData(arrayList, 10, 40);
        this.f.setCustomStartColor(getColor(R.color._2131299374_res_0x7f090c2e));
        this.f.setOnSelectedListener(new ScrollScaleView.OnSelectedListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.InputWeightActivity.10
            @Override // com.huawei.ui.commonui.scrollview.ScrollScaleView.OnSelectedListener
            public void onSelected(List<String> list, int i2) {
                InputWeightActivity.this.i = new BigDecimal((i2 * 0.1d) + 1.0d).setScale(1, 4).doubleValue();
                if (InputWeightActivity.this.i > 0.0d) {
                    InputWeightActivity inputWeightActivity = InputWeightActivity.this;
                    inputWeightActivity.m = UnitUtil.e(inputWeightActivity.i, 1, 1);
                    InputWeightActivity.this.al.setText(InputWeightActivity.this.m);
                    InputWeightActivity inputWeightActivity2 = InputWeightActivity.this;
                    inputWeightActivity2.d(inputWeightActivity2.i, true);
                }
            }
        });
    }

    private void o() {
        ArrayList<String> d2 = rag.d();
        ScrollScaleView scrollScaleView = (ScrollScaleView) findViewById(R.id.health_healthdata_imputweight_weight_scale);
        this.ay = scrollScaleView;
        scrollScaleView.setData(d2, 10, 40);
        this.ay.setCustomStartColor(getColor(R.color._2131299374_res_0x7f090c2e));
        this.ay.setOnSelectedListener(new ScrollScaleView.OnSelectedListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.InputWeightActivity.9
            @Override // com.huawei.ui.commonui.scrollview.ScrollScaleView.OnSelectedListener
            public void onSelected(List<String> list, int i) {
                double a2 = rag.a(i);
                int a3 = UnitUtil.a();
                if (a3 == 1) {
                    InputWeightActivity.this.am.setText(UnitUtil.e(a2, 1, 1));
                    InputWeightActivity inputWeightActivity = InputWeightActivity.this;
                    inputWeightActivity.d(inputWeightActivity.ap, a2);
                    InputWeightActivity.this.az = UnitUtil.c(a2);
                } else if (a3 == 3) {
                    InputWeightActivity.this.am.setText(UnitUtil.e(a2, 1, 1));
                    InputWeightActivity inputWeightActivity2 = InputWeightActivity.this;
                    inputWeightActivity2.d(inputWeightActivity2.ap, a2);
                    InputWeightActivity.this.az = UnitUtil.i(a2);
                } else {
                    InputWeightActivity.this.az = a2;
                    InputWeightActivity.this.am.setText(UnitUtil.e(InputWeightActivity.this.az, 1, 1));
                    InputWeightActivity inputWeightActivity3 = InputWeightActivity.this;
                    inputWeightActivity3.d(inputWeightActivity3.ap, InputWeightActivity.this.az);
                }
                InputWeightActivity.this.a(a2, true);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        this.y.setVisibility(0);
        this.z.setVisibility(0);
        this.ae.setVisibility(0);
        this.ay.setNoScroll(true);
        this.f.setNoScroll(true);
        this.ax.setLeftButtonDrawable(ContextCompat.getDrawable(this, R.drawable._2131430274_res_0x7f0b0b82), nsf.h(R$string.accessibility_close));
        this.ax.setRightButtonDrawable(ContextCompat.getDrawable(this, R.drawable._2131430292_res_0x7f0b0b94), nsf.h(R$string.IDS_contact_confirm));
        this.ax.setRightSoftkeyVisibility(8);
        this.b = true;
        if (this.e) {
            this.h.setVisibility(0);
            this.c = true;
            this.e = false;
        }
    }

    private void r() {
        View inflate = LayoutInflater.from(this).inflate(R.layout.common_toolbar_popupwindow, (ViewGroup) null);
        final nqc nqcVar = new nqc(this, inflate);
        nqcVar.cEh_(this.ax, 17);
        ((HealthTextView) inflate.findViewById(R.id.all_data_declare_text)).setText(R$string.IDS_privacy_data_detail);
        inflate.findViewById(R.id.popup_declare_ll).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.InputWeightActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                nqcVar.b();
                PageModelArgs pageModelArgs = new PageModelArgs();
                pageModelArgs.setDataSource(3);
                pageModelArgs.setPageType(109);
                PrivacyDataModel privacyDataModel = new PrivacyDataModel();
                privacyDataModel.setClientId(InputWeightActivity.this.o);
                privacyDataModel.setStartTime(InputWeightActivity.this.q);
                privacyDataModel.setEndTime(InputWeightActivity.this.q);
                privacyDataModel.setModifyTime(InputWeightActivity.this.q);
                double a2 = UnitUtil.a(InputWeightActivity.this.az);
                privacyDataModel.setDataTitle(UnitUtil.e(a2, 1, 1) + " " + qsj.e(a2, false));
                privacyDataModel.setDoubleValue(a2);
                Intent intent = new Intent();
                intent.putExtra("extra_privacy_data_model", privacyDataModel);
                intent.putExtra("extra_page_model_args", pageModelArgs);
                intent.setClass(InputWeightActivity.this.k, PrivacyDetailActivity.class);
                InputWeightActivity.this.startActivity(intent);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    @Override // com.huawei.ui.main.stories.health.util.BaseHealthClickListener
    public void setClickAdd() {
        if (this.c) {
            this.h.setVisibility(8);
            this.c = false;
        } else {
            this.h.setVisibility(0);
            this.c = true;
        }
    }

    /* loaded from: classes6.dex */
    public static class c extends BaseHandler<InputWeightActivity> {
        public c(InputWeightActivity inputWeightActivity) {
            super(inputWeightActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dAH_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(InputWeightActivity inputWeightActivity, Message message) {
            int i = message.what;
            if (i == 1) {
                inputWeightActivity.k();
                return;
            }
            if (i != 3) {
                if (i == 4 && !inputWeightActivity.d) {
                    inputWeightActivity.s();
                    return;
                }
                return;
            }
            inputWeightActivity.u.removeMessages(4);
            inputWeightActivity.q();
            if (message.arg1 == 0) {
                nro.e(inputWeightActivity.getApplicationContext(), 6);
                gmn.e(inputWeightActivity.k, inputWeightActivity.aw);
            } else {
                Toast.makeText(inputWeightActivity.k, inputWeightActivity.k.getResources().getString(R$string.IDS_music_management_operation_failed), 0).show();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        qsj.a(this.k);
    }

    /* loaded from: classes6.dex */
    static class a implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<InputWeightActivity> f10060a;

        a(InputWeightActivity inputWeightActivity) {
            this.f10060a = new WeakReference<>(inputWeightActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            InputWeightActivity inputWeightActivity = this.f10060a.get();
            if (inputWeightActivity != null) {
                if (i == 0) {
                    LogUtil.a("UIHLH_InputWeightActivity", "delete successful");
                    inputWeightActivity.n();
                } else {
                    LogUtil.h("UIHLH_InputWeightActivity", "delete failed");
                }
            }
        }
    }

    /* loaded from: classes6.dex */
    static class d implements IBaseResponseCallback {
        private WeakReference<InputWeightActivity> b;

        d(InputWeightActivity inputWeightActivity) {
            this.b = new WeakReference<>(inputWeightActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.c("UIHLH_InputWeightActivity", "enter showHealthDataSyncDialogResponseCallback");
            InputWeightActivity inputWeightActivity = this.b.get();
            if (inputWeightActivity != null && inputWeightActivity.aq == 1) {
                if ("/PluginHealthModel/HealthModelActivity".equals(inputWeightActivity.v) || "/todoTask/TodoListActivity".equals(inputWeightActivity.v)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("from", "6");
                    rag.dJB_("6", bundle);
                }
                Intent intent = new Intent();
                intent.putExtra("weight_data", inputWeightActivity.az);
                inputWeightActivity.setResult(1214, intent);
                LogUtil.a("UIHLH_InputWeightActivity", "inputWeightActivity Weight: ", Double.valueOf(inputWeightActivity.az));
                if (inputWeightActivity.ag) {
                    qsj.dIi_(inputWeightActivity, true);
                }
                inputWeightActivity.finish();
            }
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.ag) {
            qsj.dIi_(this, false);
        }
        super.onBackPressed();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
