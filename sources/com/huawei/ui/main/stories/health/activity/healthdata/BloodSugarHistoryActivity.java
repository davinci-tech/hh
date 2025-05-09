package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.core.content.ContextCompat;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiDataSourceFetchOption;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataClientListener;
import com.huawei.hihealth.data.model.HiBloodSugarMetaData;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.listview.HealthExpandableListView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.BloodSugarHistoryActivity;
import com.huawei.ui.main.stories.health.adapter.BloodSugarHistoryExpandableListViewAdapter;
import com.huawei.ui.main.stories.health.util.BloodSugarTimeUtils;
import defpackage.dks;
import defpackage.ixx;
import defpackage.koq;
import defpackage.kor;
import defpackage.nsf;
import defpackage.qkb;
import defpackage.qkg;
import defpackage.qkh;
import defpackage.rrb;
import defpackage.rzl;
import defpackage.scj;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes6.dex */
public class BloodSugarHistoryActivity extends BaseActivity {
    private HealthToolBar aa;
    private Context f;
    private BloodSugarHistoryExpandableListViewAdapter g;
    private e h;
    private LinearLayout i;
    private HealthExpandableListView j;
    private LinearLayout k;
    private i m;
    private d n;
    private qkh o;
    private RelativeLayout r;
    private Resources t;
    private String w;
    private CustomTitleBar x;
    private String y;
    private int v = 0;
    private Handler l = new c(this);
    private List<ArrayList<qkg>> d = new ArrayList(10);
    private List<qkb> c = new CopyOnWriteArrayList();
    private List<qkb> e = new ArrayList(10);
    private Map<Long, qkb> u = new HashMap();
    private boolean q = false;
    private int b = 0;
    private int ac = 0;
    private boolean p = false;
    private boolean s = true;

    /* renamed from: a, reason: collision with root package name */
    private int f10030a = 0;

    static /* synthetic */ int d(BloodSugarHistoryActivity bloodSugarHistoryActivity) {
        int i2 = bloodSugarHistoryActivity.ac;
        bloodSugarHistoryActivity.ac = i2 - 1;
        return i2;
    }

    private static int e(List<qkb> list, ArrayList<List<Boolean>> arrayList) {
        int i2 = 0;
        for (int i3 = 0; i3 < arrayList.size(); i3++) {
            List<Boolean> list2 = arrayList.get(i3);
            if (koq.b(list, i3)) {
                return 0;
            }
            for (int i4 = 0; i4 < list2.size(); i4++) {
                if (list2.get(i4).booleanValue()) {
                    ArrayList<qkb.e> d2 = list.get(i3).d();
                    if (!koq.b(d2, i4) && !d2.get(i4).n()) {
                        i2++;
                    }
                }
            }
        }
        return i2;
    }

    private static String e() {
        HiBloodSugarMetaData hiBloodSugarMetaData = new HiBloodSugarMetaData();
        hiBloodSugarMetaData.setConfirmed(true);
        return rzl.a(hiBloodSugarMetaData);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.health_data_historybloodsugar);
        clearBackgroundDrawable();
        this.f = this;
        this.s = true;
        this.t = BaseApplication.getContext().getResources();
        this.o = qkh.c();
        this.n = new d(this);
        this.h = new e(this);
        this.m = new i(this);
        this.w = this.t.getString(R$string.IDS_hw_show_healthdata_bloodsugar_select_all);
        this.y = this.t.getString(R$string.IDS_hw_show_healthdata_bloodsugar_unselected_all);
        i();
        s();
        a();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i2, int i3, Intent intent) {
        super.onActivityResult(i2, i3, intent);
        if (i2 == 100) {
            h();
        }
    }

    private void i() {
        this.r = (RelativeLayout) findViewById(R.id.hw_blood_sugar_loading);
        this.i = (LinearLayout) findViewById(R.id.hw_show_health_data_bloodsugar_empty_layout);
        this.k = (LinearLayout) findViewById(R.id.layout_blood_sugar_has_data);
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.health_blood_sugar_history_title_layout);
        this.x = customTitleBar;
        customTitleBar.setTitleBarBackgroundColor(ContextCompat.getColor(this, R.color._2131298121_res_0x7f090749));
        this.j = (HealthExpandableListView) findViewById(R.id.list_blood_sugar_history_simplify);
        this.aa = (HealthToolBar) findViewById(R.id.blood_sugar_history_toolbar);
        this.aa.cHc_(View.inflate(this.f, R.layout.hw_toolbar_bottomview, null));
        this.aa.setBackgroundColor(ContextCompat.getColor(this, R.color._2131296690_res_0x7f0901b2));
        j(0);
        this.aa.cHf_(this);
        BaseActivity.cancelLayoutById(this.j);
        BloodSugarHistoryExpandableListViewAdapter bloodSugarHistoryExpandableListViewAdapter = new BloodSugarHistoryExpandableListViewAdapter(this.f);
        this.g = bloodSugarHistoryExpandableListViewAdapter;
        this.j.setAdapter(bloodSugarHistoryExpandableListViewAdapter);
        this.j.setSelector(new ColorDrawable(0));
        j();
    }

    private void j() {
        m();
    }

    private void m() {
        this.j.setOnChildClickListener(new ExpandableListView.OnChildClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.BloodSugarHistoryActivity$$ExternalSyntheticLambda4
            @Override // android.widget.ExpandableListView.OnChildClickListener
            public final boolean onChildClick(ExpandableListView expandableListView, View view, int i2, int i3, long j) {
                return BloodSugarHistoryActivity.this.dzo_(expandableListView, view, i2, i3, j);
            }
        });
        this.g.d(new BloodSugarHistoryExpandableListViewAdapter.OnItemClickListener() { // from class: qcu
            @Override // com.huawei.ui.main.stories.health.adapter.BloodSugarHistoryExpandableListViewAdapter.OnItemClickListener
            public final void onItemClickListener(int i2, int i3) {
                BloodSugarHistoryActivity.this.a(i2, i3);
            }
        });
    }

    /* synthetic */ boolean dzo_(ExpandableListView expandableListView, View view, int i2, int i3, long j) {
        boolean a2 = a(i2, i3);
        ViewClickInstrumentation.childClickOnExpandableListView(expandableListView, view, i2, i3);
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public boolean a(int i2, int i3) {
        if (this.v == 0) {
            e(this.e, i2, i3);
            return true;
        }
        return c(i2, i3);
    }

    private void e(List<qkb> list, int i2, int i3) {
        if (koq.b(list, i2)) {
            return;
        }
        ArrayList<qkb.e> d2 = list.get(i2).d();
        if (koq.b(d2, i3)) {
            LogUtil.b("BloodSugarHistoryActivity", "startDetailPage list is null or isOutOfBounds");
            return;
        }
        qkb.e eVar = d2.get(i3);
        Intent intent = new Intent();
        if (scj.d(eVar.b()) && !eVar.n()) {
            c();
            intent.setClass(this.f, BloodSugarDeviceMeasureActivity.class);
            intent.putExtra("entrance", "jump_from_blood_sugar_history");
            intent.putExtra("start_time", eVar.j());
            intent.putExtra("time_period", eVar.a());
        } else {
            intent.setClass(this.f, BloodSugarFeedbackActivity.class);
            intent.putExtra("bloodTimePeriod", eVar.a());
            if (eVar.j() != eVar.m()) {
                intent.putExtra("showDefaultTime", eVar.m());
            }
            intent.putExtra("time", eVar.j());
            intent.putExtra("bloodNum", eVar.f());
            intent.putExtra("isEdit", true);
            intent.putExtra("clientId", eVar.c());
            intent.putExtra(ParsedFieldTag.TASK_MODIFY_TIME, eVar.h());
            intent.putExtra("pageFrom", 4);
            if (scj.d(eVar.b())) {
                intent.putExtra("bloodSugarDataIsFromMeter", true);
                intent.putExtra("titleName", this.f.getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_measurement_record));
            } else {
                intent.putExtra("bloodSugarDataIsFromMeter", false);
                intent.putExtra("titleName", this.f.getResources().getString(R$string.IDS_hw_show_healthdata_bloodsugar_history));
            }
        }
        startActivityForResult(intent, 100);
    }

    private boolean c(int i2, int i3) {
        ArrayList<List<Boolean>> b2 = this.g.b();
        if (koq.b(b2, i2)) {
            return true;
        }
        List<Boolean> list = b2.get(i2);
        if (koq.b(list, i3) || koq.b(this.e, i2) || koq.b(this.e.get(i2).d(), i3)) {
            return true;
        }
        Boolean bool = list.get(i3);
        list.set(i3, Boolean.valueOf(!bool.booleanValue()));
        b2.set(i2, list);
        this.g.e(b2);
        if (!bool.booleanValue()) {
            this.b++;
        } else {
            this.b--;
        }
        Iterator<List<Boolean>> it = b2.iterator();
        int i4 = 0;
        while (it.hasNext()) {
            i4 += it.next().size();
        }
        if (this.b == i4) {
            this.q = true;
            this.aa.setIconTitle(3, this.y);
            this.aa.setIcon(3, R.drawable._2131430281_res_0x7f0b0b89);
        } else {
            this.q = false;
            this.aa.setIconTitle(3, this.w);
            this.aa.setIcon(3, R.drawable._2131430296_res_0x7f0b0b98);
        }
        t();
        return true;
    }

    private void a(long j, qkb.e eVar) {
        qkb qkbVar = this.u.get(Long.valueOf(j));
        if (qkbVar == null) {
            qkbVar = new qkb();
            qkbVar.d(new ArrayList());
            this.u.put(Long.valueOf(j), qkbVar);
        }
        qkbVar.d().add(eVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i(int i2) {
        if (i2 == 0) {
            this.q = false;
            this.b = 0;
            this.ac = 0;
        }
        this.v = i2;
        this.g.b(i2);
        j(i2);
        b(i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        i(0);
        this.g.a();
    }

    private void j(int i2) {
        if (i2 == 1) {
            p();
            return;
        }
        this.aa.setIcon(2, R.drawable._2131430296_res_0x7f0b0b98);
        this.aa.setIconTitle(2, this.t.getString(R$string.IDS_hw_show_healthdata_bloodsugar_select));
        this.aa.setIconVisible(1, 8);
        this.aa.setIconVisible(3, 8);
        this.aa.setOnSingleTapListener(new HealthToolBar.OnSingleTapListener() { // from class: qct
            @Override // com.huawei.ui.commonui.toolbar.HealthToolBar.OnSingleTapListener
            public final void onSingleTap(int i3) {
                BloodSugarHistoryActivity.this.c(i3);
            }
        });
    }

    public /* synthetic */ void c(int i2) {
        if (i2 == 2) {
            i(1);
        }
    }

    private void p() {
        if (this.p) {
            this.aa.setIcon(1, R.drawable._2131429763_res_0x7f0b0983);
            this.aa.setIconTitle(1, this.t.getString(R$string.IDS_hw_show_healthdata_bloodsugar_confirm_time_segment));
            this.aa.setIcon(2, R.drawable._2131430270_res_0x7f0b0b7e);
            this.aa.setIconTitle(2, this.t.getString(R$string.IDS_hw_show_healthdata_bloodsugar_delete));
            this.aa.setIcon(3, R.drawable._2131430296_res_0x7f0b0b98);
            this.aa.setIconTitle(3, this.w);
            this.aa.setIconVisible(1, 0);
            this.aa.setIconVisible(3, 0);
            this.aa.setOnSingleTapListener(new HealthToolBar.OnSingleTapListener() { // from class: qcv
                @Override // com.huawei.ui.commonui.toolbar.HealthToolBar.OnSingleTapListener
                public final void onSingleTap(int i2) {
                    BloodSugarHistoryActivity.this.e(i2);
                }
            });
            return;
        }
        q();
    }

    public /* synthetic */ void e(int i2) {
        if (i2 == 1) {
            int e2 = e(this.e, this.g.b());
            this.ac = e2;
            if (e2 <= 0) {
                return;
            }
            b(this.b, this.e, this.g.b());
            return;
        }
        if (i2 != 2) {
            if (i2 != 3) {
                return;
            }
            r();
        } else {
            int i3 = this.b;
            if (i3 <= 0) {
                this.b = 0;
            } else {
                d(i3, this.e, this.g.b());
            }
        }
    }

    private void q() {
        this.aa.setIconVisible(2, 8);
        this.aa.setIcon(1, R.drawable._2131430270_res_0x7f0b0b7e);
        this.aa.setIconTitle(1, this.t.getString(R$string.IDS_hw_show_healthdata_bloodsugar_delete));
        this.aa.setIcon(3, R.drawable._2131430296_res_0x7f0b0b98);
        this.aa.setIconTitle(3, this.w);
        this.aa.setIconVisible(1, 0);
        this.aa.setIconVisible(3, 0);
        this.aa.setOnSingleTapListener(new HealthToolBar.OnSingleTapListener() { // from class: qcn
            @Override // com.huawei.ui.commonui.toolbar.HealthToolBar.OnSingleTapListener
            public final void onSingleTap(int i2) {
                BloodSugarHistoryActivity.this.a(i2);
            }
        });
    }

    public /* synthetic */ void a(int i2) {
        if (i2 != 1) {
            if (i2 != 3) {
                return;
            }
            r();
        } else {
            int i3 = this.b;
            if (i3 <= 0) {
                this.b = 0;
            } else {
                d(i3, this.e, this.g.b());
            }
        }
    }

    private void b(int i2) {
        if (i2 == 1) {
            this.x.setLeftButtonDrawable(this.t.getDrawable(R.drawable._2131430298_res_0x7f0b0b9a), nsf.h(R$string.accessibility_close));
            this.x.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: qcm
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BloodSugarHistoryActivity.this.dzp_(view);
                }
            });
            this.x.setTitleText(this.t.getString(R$string.IDS_hw_show_healthdata_bloodsugar_not_select));
        } else {
            this.x.setLeftButtonDrawable(this.t.getDrawable(R.drawable._2131428438_res_0x7f0b0456), nsf.h(R$string.accessibility_go_back));
            this.x.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: qco
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BloodSugarHistoryActivity.this.dzq_(view);
                }
            });
            this.x.setTitleText(this.t.getString(R$string.IDS_hw_base_health_data_history_record));
        }
    }

    public /* synthetic */ void dzp_(View view) {
        i(0);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dzq_(View view) {
        onBackPressed();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void t() {
        int i2 = this.b;
        if (i2 > 0) {
            this.x.setTitleText(this.t.getQuantityString(R.plurals._2130903064_res_0x7f030018, i2, Integer.valueOf(i2)));
        } else {
            this.x.setTitleText(this.t.getString(R$string.IDS_hw_show_healthdata_bloodsugar_not_select));
        }
    }

    private void r() {
        int i2 = 0;
        if (this.q) {
            this.g.e();
            this.q = false;
            this.aa.setIconTitle(3, this.w);
            this.aa.setIcon(3, R.drawable._2131430296_res_0x7f0b0b98);
            this.b = 0;
            this.ac = 0;
        } else {
            this.g.d();
            this.q = true;
            this.aa.setIconTitle(3, this.y);
            this.aa.setIcon(3, R.drawable._2131430281_res_0x7f0b0b89);
            ArrayList<List<Boolean>> b2 = this.g.b();
            if (b2 == null || b2.size() == 0) {
                return;
            }
            Iterator<List<Boolean>> it = b2.iterator();
            while (it.hasNext()) {
                i2 += it.next().size();
            }
            this.b = i2;
        }
        t();
    }

    private void s() {
        if (this.g.c() == null) {
            return;
        }
        for (int i2 = 0; i2 <= this.g.c().size(); i2++) {
            d(i2);
        }
    }

    private void d(int i2) {
        if (this.j == null || !koq.d(this.g.c(), i2)) {
            return;
        }
        this.j.expandGroup(i2);
    }

    private void a() {
        String value = AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_CARD_DATA_2030070.value();
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        hashMap.put("type", "1");
        ixx.d().d(this.f, value, hashMap, 0);
    }

    private void c() {
        String value = AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_CARD_CONFIRMED_2030071.value();
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        hashMap.put("type", "3");
        ixx.d().d(this.f, value, hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        y();
        this.o.b(this.f, 0L, System.currentTimeMillis(), 0, this.n);
        if (this.v == 1) {
            i(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f(int i2) {
        this.p = false;
        this.e.clear();
        this.f10030a = 0;
        if (i2 == 1) {
            this.c.clear();
            n();
            return;
        }
        for (qkb qkbVar : this.c) {
            qkb qkbVar2 = this.u.get(Long.valueOf(qkbVar.a()));
            Iterator<qkb.e> it = qkbVar.d().iterator();
            ArrayList<qkb.e> d2 = qkbVar2 != null ? qkbVar2.d() : null;
            while (it.hasNext()) {
                qkb.e next = it.next();
                if (d2 != null && d2.contains(next)) {
                    it.remove();
                } else {
                    boolean c2 = c(next.i());
                    next.c(c2);
                    if (!c2) {
                        this.p = true;
                    }
                }
            }
            if (qkbVar.d().isEmpty()) {
                this.c.remove(qkbVar);
            }
            this.f10030a += qkbVar.d().size();
        }
        k();
    }

    private void d(final int i2, final List<qkb> list, final ArrayList<List<Boolean>> arrayList) {
        if (i2 <= 0) {
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.f);
        builder.e(this.t.getQuantityString(R.plurals._2130903065_res_0x7f030019, i2, Integer.valueOf(i2))).czE_(this.f.getString(R$string.IDS_hw_health_show_common_dialog_ok_button), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.BloodSugarHistoryActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                BloodSugarHistoryActivity.this.c(i2, list, arrayList);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czA_(this.f.getString(R$string.IDS_hw_health_show_common_dialog_cancle_button), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.BloodSugarHistoryActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.e().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i2, List<qkb> list, ArrayList<List<Boolean>> arrayList) {
        y();
        this.h.c(i2);
        int i3 = 0;
        boolean z = i2 == this.f10030a;
        this.h.c(z);
        ArrayList arrayList2 = new ArrayList();
        HashSet hashSet = new HashSet();
        for (int i4 = 0; i4 < arrayList.size(); i4++) {
            List<Boolean> list2 = arrayList.get(i4);
            for (int i5 = 0; i5 < list2.size(); i5++) {
                if (list2.get(i5).booleanValue()) {
                    qkb qkbVar = list.get(i4);
                    qkb.e eVar = qkbVar.d().get(i5);
                    if (!z) {
                        a(qkbVar.a(), eVar);
                    }
                    HiTimeInterval hiTimeInterval = new HiTimeInterval();
                    hiTimeInterval.setStartTime(eVar.j());
                    hiTimeInterval.setEndTime(eVar.j());
                    arrayList2.add(hiTimeInterval);
                    hashSet.add(Integer.valueOf(eVar.a()));
                }
            }
        }
        int[] iArr = new int[hashSet.size()];
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            iArr[i3] = ((Integer) it.next()).intValue();
            i3++;
        }
        kor.a().a(this.f, arrayList2, iArr, this.h);
    }

    private void b(int i2, final List<qkb> list, final ArrayList<List<Boolean>> arrayList) {
        if (i2 <= 0) {
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.f);
        Resources resources = this.t;
        int i3 = this.ac;
        builder.e(resources.getQuantityString(R.plurals._2130903346_res_0x7f030132, i3, Integer.valueOf(i3))).czE_(this.f.getString(R$string.IDS_hw_health_show_common_dialog_ok_button), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.BloodSugarHistoryActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                for (int i4 = 0; i4 < arrayList.size(); i4++) {
                    BloodSugarHistoryActivity.this.b((qkb) list.get(i4), (List<Boolean>) arrayList.get(i4));
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czA_(this.f.getString(R$string.IDS_hw_health_show_common_dialog_cancle_button), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.BloodSugarHistoryActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.e().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(qkb qkbVar, List<Boolean> list) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (list.get(i2).booleanValue()) {
                qkb.e eVar = qkbVar.d().get(i2);
                if (!eVar.n()) {
                    LogUtil.a("BloodSugarHistoryActivity", "insertDeviceData clientId:", Integer.valueOf(eVar.c()));
                    d(eVar, this.m);
                    eVar.c(true);
                }
            }
        }
    }

    private void d(qkb.e eVar, IBaseResponseCallback iBaseResponseCallback) {
        String str = "BloodSugarHistoryActivity";
        if (eVar == null || iBaseResponseCallback == null) {
            LogUtil.b("BloodSugarHistoryActivity", "insertDeviceData params is null");
            return;
        }
        String i2 = eVar.i();
        if (!TextUtils.isEmpty(i2)) {
            try {
                HiBloodSugarMetaData hiBloodSugarMetaData = (HiBloodSugarMetaData) rzl.d(i2, HiBloodSugarMetaData.class);
                if (hiBloodSugarMetaData == null) {
                    str = e();
                } else {
                    hiBloodSugarMetaData.setConfirmed(true);
                    str = rzl.a(hiBloodSugarMetaData);
                }
            } catch (JsonSyntaxException e2) {
                LogUtil.b(str, e2.getMessage());
                str = e();
            } catch (IllegalStateException e3) {
                LogUtil.b(str, e3.getMessage());
                str = e();
            }
        } else {
            str = e();
        }
        HiHealthData hiHealthData = new HiHealthData(10001);
        hiHealthData.setDeviceUuid(eVar.e());
        hiHealthData.setStartTime(eVar.j());
        hiHealthData.setEndTime(eVar.j());
        hiHealthData.setType(eVar.a());
        hiHealthData.setValue(dks.i(eVar.e()) ? eVar.g() : eVar.f());
        hiHealthData.setMetaData(str);
        HiHealthNativeApi.a(BaseApplication.getContext()).fetchDataSource(HiDataSourceFetchOption.builder().a(1).c(new int[]{eVar.c()}).e(), new b(this, hiHealthData, iBaseResponseCallback));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        if (!koq.b(this.d)) {
            g();
        } else {
            n();
        }
    }

    private void g() {
        ArrayList arrayList = new ArrayList(this.c);
        Collections.sort(arrayList, new a());
        this.c.clear();
        this.c.addAll(arrayList);
        k();
    }

    private void k() {
        this.e.addAll(this.c);
        if (this.g == null) {
            this.g = new BloodSugarHistoryExpandableListViewAdapter(this);
        }
        this.g.d(this.e);
        this.r.setVisibility(8);
        this.k.setVisibility(0);
        this.i.setVisibility(8);
        s();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        this.g.d(this.e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        if (koq.b(this.c)) {
            LogUtil.b("BloodSugarHistoryActivity", "getTypeMap is null");
            return;
        }
        int[] a2 = a(this.c);
        HiDataSourceFetchOption e2 = HiDataSourceFetchOption.builder().a(1).c(a2).e();
        LogUtil.a("BloodSugarHistoryActivity", "start setDataSourceType");
        HiHealthNativeApi.a(this.f).fetchDataSource(e2, new b(this, a2, null));
    }

    protected int[] a(List<qkb> list) {
        HashSet hashSet = new HashSet(16);
        Iterator<qkb> it = list.iterator();
        while (it.hasNext()) {
            Iterator<qkb.e> it2 = it.next().d().iterator();
            while (it2.hasNext()) {
                hashSet.add(Integer.valueOf(it2.next().c()));
            }
        }
        int[] iArr = new int[hashSet.size()];
        Iterator it3 = hashSet.iterator();
        int i2 = 0;
        while (it3.hasNext()) {
            iArr[i2] = ((Integer) it3.next()).intValue();
            i2++;
        }
        return iArr;
    }

    private void y() {
        if (this.r.getVisibility() == 0) {
            return;
        }
        this.r.setVisibility(0);
        this.k.setVisibility(8);
        this.i.setVisibility(8);
        this.x.setTitleBarBackgroundColor(ContextCompat.getColor(this, R.color._2131298121_res_0x7f090749));
    }

    private void n() {
        this.r.setVisibility(8);
        this.i.setVisibility(0);
        this.k.setVisibility(8);
        this.x.setTitleBarBackgroundColor(ContextCompat.getColor(this, R.color._2131296602_res_0x7f09015a));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<ArrayList<qkg>> list) {
        this.c.clear();
        this.e.clear();
        this.f10030a = 0;
        for (ArrayList<qkg> arrayList : list) {
            qkb qkbVar = new qkb();
            if (koq.b(arrayList)) {
                LogUtil.b("BloodSugarHistoryActivity", "addHistoryData healthDataList is empty");
                n();
                return;
            }
            qkbVar.b(arrayList.get(0).h());
            ArrayList<qkb.e> arrayList2 = new ArrayList<>();
            for (qkg qkgVar : arrayList) {
                if (qkgVar == null) {
                    n();
                    LogUtil.b("BloodSugarHistoryActivity", "addHistoryData healthData is null");
                    return;
                } else if (DateFormatUtil.b(qkgVar.h(), DateFormatUtil.DateFormatType.DATE_FORMAT_YYYYMD).equals(DateFormatUtil.b(qkbVar.a(), DateFormatUtil.DateFormatType.DATE_FORMAT_YYYYMD))) {
                    a(qkgVar, arrayList2, arrayList);
                }
            }
            qkbVar.d(arrayList2);
            this.c.add(qkbVar);
            this.f10030a += arrayList.size();
        }
    }

    private void a(qkg qkgVar, ArrayList<qkb.e> arrayList, List<qkg> list) {
        qkb.e eVar = new qkb.e();
        eVar.d(qkgVar.h());
        eVar.e(qkgVar.h());
        int o = (int) qkgVar.o();
        double m = qkgVar.m();
        eVar.b(o);
        eVar.e(m);
        eVar.e(qkgVar.c());
        eVar.d(qkgVar.j());
        String f = qkgVar.f();
        eVar.a(f);
        eVar.d(qkgVar.a());
        eVar.a(qkgVar.i());
        if (!TextUtils.isEmpty(f)) {
            boolean c2 = c(f);
            eVar.c(c2);
            if (!c2) {
                this.p = true;
            }
        } else {
            eVar.c(true);
            if (scj.d(eVar.b())) {
                eVar.e(qkgVar.h());
            } else {
                eVar.e(BloodSugarTimeUtils.c(o, list.get(0).h()));
            }
        }
        arrayList.add(eVar);
    }

    private boolean c(String str) {
        try {
            HiBloodSugarMetaData hiBloodSugarMetaData = (HiBloodSugarMetaData) rzl.d(str, HiBloodSugarMetaData.class);
            if (hiBloodSugarMetaData != null) {
                return hiBloodSugarMetaData.getConfirmed();
            }
            return true;
        } catch (JsonSyntaxException unused) {
            LogUtil.b("BloodSugarHistoryActivity", "fromJson JsonSyntaxException");
            return true;
        } catch (IllegalStateException unused2) {
            LogUtil.b("BloodSugarHistoryActivity", "fromJson IllegalStateException");
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        this.p = false;
        for (qkb qkbVar : this.e) {
            if (qkbVar != null) {
                Iterator<qkb.e> it = qkbVar.d().iterator();
                while (it.hasNext()) {
                    qkb.e next = it.next();
                    if (next != null && !next.n()) {
                        this.p = true;
                        return;
                    }
                }
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.s) {
            h();
            this.s = false;
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        HealthExpandableListView healthExpandableListView = this.j;
        if (healthExpandableListView != null) {
            healthExpandableListView.setOnGroupClickListener(null);
            this.j.setOnChildClickListener(null);
            this.j.setOnItemLongClickListener(null);
            this.j = null;
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.v == 1) {
            i(0);
        } else {
            super.onBackPressed();
        }
    }

    static class d implements CommonUiBaseResponse {
        WeakReference<BloodSugarHistoryActivity> e;

        d(BloodSugarHistoryActivity bloodSugarHistoryActivity) {
            this.e = new WeakReference<>(bloodSugarHistoryActivity);
        }

        @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
        public void onResponse(int i, Object obj) {
            BloodSugarHistoryActivity bloodSugarHistoryActivity = this.e.get();
            if (bloodSugarHistoryActivity != null) {
                bloodSugarHistoryActivity.p = false;
                if (obj instanceof List) {
                    bloodSugarHistoryActivity.c((List<ArrayList<qkg>>) obj);
                    bloodSugarHistoryActivity.d();
                }
                Message obtainMessage = bloodSugarHistoryActivity.l.obtainMessage();
                obtainMessage.what = 1;
                obtainMessage.obj = obj;
                bloodSugarHistoryActivity.l.sendMessage(obtainMessage);
            }
        }
    }

    static class c extends BaseHandler<BloodSugarHistoryActivity> {
        c(BloodSugarHistoryActivity bloodSugarHistoryActivity) {
            super(bloodSugarHistoryActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dzr_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(BloodSugarHistoryActivity bloodSugarHistoryActivity, Message message) {
            if (message == null) {
                LogUtil.h("BloodSugarHistoryActivity", "handleMessageWhenReferenceNotNull msg is null");
                bloodSugarHistoryActivity.o();
                return;
            }
            int i = message.what;
            if (i == 1) {
                LogUtil.a("BloodSugarHistoryActivity", "UPDATE_HISTORY");
                Object obj = message.obj;
                if (obj instanceof List) {
                    bloodSugarHistoryActivity.d.clear();
                    bloodSugarHistoryActivity.d.addAll((List) obj);
                } else {
                    LogUtil.h("BloodSugarHistoryActivity", "CASE UPDATE_HISTORY : objFromBloodSugarUpdate data is null or not List");
                }
                bloodSugarHistoryActivity.o();
                return;
            }
            if (i == 2) {
                LogUtil.a("BloodSugarHistoryActivity", "DELETE_RECORDS");
                bloodSugarHistoryActivity.i(0);
                bloodSugarHistoryActivity.b();
                if (message.arg1 == 0) {
                    bloodSugarHistoryActivity.f(message.arg2);
                } else {
                    bloodSugarHistoryActivity.h();
                }
                bloodSugarHistoryActivity.u.clear();
                return;
            }
            if (i != 3) {
                if (i != 4) {
                    return;
                }
                bloodSugarHistoryActivity.l();
            } else {
                BloodSugarHistoryActivity.d(bloodSugarHistoryActivity);
                bloodSugarHistoryActivity.i(0);
                if (bloodSugarHistoryActivity.ac <= 0) {
                    bloodSugarHistoryActivity.b();
                    bloodSugarHistoryActivity.f();
                }
            }
        }
    }

    static class e implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        private int f10033a = 0;
        private final WeakReference<BloodSugarHistoryActivity> c;
        private int d;

        e(BloodSugarHistoryActivity bloodSugarHistoryActivity) {
            this.c = new WeakReference<>(bloodSugarHistoryActivity);
        }

        public void c(int i) {
            this.d = i;
        }

        public void c(boolean z) {
            this.f10033a = z ? 1 : 0;
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            BloodSugarHistoryActivity bloodSugarHistoryActivity = this.c.get();
            if (bloodSugarHistoryActivity == null) {
                return;
            }
            if (i == 0) {
                LogUtil.a("BloodSugarHistoryActivity", "DeleteDataResponseCallback delete successful");
                this.d = 0;
            } else {
                LogUtil.b("BloodSugarHistoryActivity", "DeleteDataResponseCallback delete failed");
            }
            Message obtainMessage = bloodSugarHistoryActivity.l.obtainMessage();
            obtainMessage.what = 2;
            obtainMessage.arg1 = this.d;
            obtainMessage.arg2 = this.f10033a;
            bloodSugarHistoryActivity.l.sendMessage(obtainMessage);
        }
    }

    static class a implements Comparator<qkb>, Serializable {
        private static final long serialVersionUID = 3968849440072396774L;

        private a() {
        }

        @Override // java.util.Comparator
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public int compare(qkb qkbVar, qkb qkbVar2) {
            long a2 = qkbVar.a();
            long a3 = qkbVar2.a();
            if (Long.compare(a2, a3) < 0) {
                return 1;
            }
            return a3 == a2 ? 0 : -1;
        }
    }

    static class i implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<BloodSugarHistoryActivity> f10034a;

        i(BloodSugarHistoryActivity bloodSugarHistoryActivity) {
            this.f10034a = new WeakReference<>(bloodSugarHistoryActivity);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            Message obtainMessage;
            BloodSugarHistoryActivity bloodSugarHistoryActivity = this.f10034a.get();
            if (bloodSugarHistoryActivity == null) {
                return;
            }
            if (i == 0) {
                LogUtil.a("BloodSugarHistoryActivity", "InsertBloodSugarResponseCallback, insert successful");
                obtainMessage = bloodSugarHistoryActivity.l.obtainMessage(3, 0, 0);
            } else {
                LogUtil.b("BloodSugarHistoryActivity", "InsertBloodSugarResponseCallback, insert fail");
                obtainMessage = bloodSugarHistoryActivity.l.obtainMessage(3, -1, 0);
            }
            bloodSugarHistoryActivity.l.sendMessage(obtainMessage);
        }
    }

    static class b implements HiDataClientListener {
        private final WeakReference<BloodSugarHistoryActivity> c;
        private final IBaseResponseCallback d;
        private final Object e;

        b(BloodSugarHistoryActivity bloodSugarHistoryActivity, Object obj, IBaseResponseCallback iBaseResponseCallback) {
            this.c = new WeakReference<>(bloodSugarHistoryActivity);
            this.e = obj;
            this.d = iBaseResponseCallback;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataClientListener
        public void onResult(List<HiHealthClient> list) {
            BloodSugarHistoryActivity bloodSugarHistoryActivity = this.c.get();
            if (bloodSugarHistoryActivity == null) {
                return;
            }
            c(list);
            c(bloodSugarHistoryActivity, list);
        }

        private void c(List<HiHealthClient> list) {
            Object obj = this.e;
            if (obj instanceof HiHealthData) {
                kor.a().d(BaseApplication.getContext(), (HiHealthData) obj, koq.b(list) ? null : list.get(0).getPackageName(), this.d);
            }
        }

        private void c(BloodSugarHistoryActivity bloodSugarHistoryActivity, List<HiHealthClient> list) {
            Object obj = this.e;
            if (obj instanceof int[]) {
                int[] iArr = (int[]) obj;
                if (list == null || list.size() <= 0) {
                    LogUtil.b("BloodSugarHistoryActivity", "getDataSourceType clientList is null or size is 0");
                    return;
                }
                HashMap hashMap = new HashMap(16);
                Iterator<HiHealthClient> it = list.iterator();
                int i = 0;
                while (it.hasNext()) {
                    hashMap.put(Integer.valueOf(iArr[i]), Boolean.valueOf(rrb.c(it.next())));
                    i++;
                }
                LogUtil.a("BloodSugarHistoryActivity", "end setDataSourceType");
                for (qkb qkbVar : bloodSugarHistoryActivity.c) {
                    if (koq.b(qkbVar.d())) {
                        LogUtil.h("BloodSugarHistoryActivity", "data.getOneDayList() is null");
                        return;
                    }
                    Iterator<qkb.e> it2 = qkbVar.d().iterator();
                    while (it2.hasNext()) {
                        qkb.e next = it2.next();
                        Boolean bool = (Boolean) hashMap.get(Integer.valueOf(next.c()));
                        if (bool != null) {
                            next.d(bool.booleanValue());
                        } else {
                            next.d(false);
                        }
                    }
                }
                bloodSugarHistoryActivity.l.sendEmptyMessage(4);
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
