package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.fatscale.multiusers.WeightDataManager;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataSourceFetchOption;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataClientListener;
import com.huawei.hihealth.data.type.HiSyncType;
import com.huawei.hihealth.dictionary.utils.ProductMapParseUtil;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.JsbMapKeyNames;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.toolbar.HealthToolBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.adapter.WeightExpandableListViewAdapter;
import com.huawei.ui.main.stories.health.util.TrendFragmentCallback;
import defpackage.cfe;
import defpackage.cfi;
import defpackage.jcf;
import defpackage.koq;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.nsy;
import defpackage.qjz;
import defpackage.qkh;
import defpackage.qko;
import defpackage.qsj;
import defpackage.rrb;
import defpackage.rrf;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes.dex */
public class HealthDataHistoryActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private List<cfe> f10038a;
    private WeightExpandableListViewAdapter b;
    private cfi c;
    private HealthDataHistoryActivity d;
    private View e;
    private a g;
    private ExpandableListView h;
    private boolean i;
    private qjz j;
    private HealthProgressBar l;
    private qkh n;
    private View p;
    private long q;
    private volatile long r;
    private CustomTitleBar s;
    private String v;
    private LinearLayout w;
    private LinearLayout x;
    private HealthToolBar y;
    private final List<cfe> m = new CopyOnWriteArrayList();
    private boolean k = false;
    private boolean o = false;
    private Handler f = new d(this);
    private HealthToolBar.OnSingleTapListener t = new HealthToolBar.OnSingleTapListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.HealthDataHistoryActivity.3
        @Override // com.huawei.ui.commonui.toolbar.HealthToolBar.OnSingleTapListener
        public void onSingleTap(int i) {
            if (i != 1) {
                if (i == 2) {
                    HealthDataHistoryActivity.this.b(!r3.k);
                    return;
                } else {
                    LogUtil.h("HealthWeight_HealthDataHistoryActivity", "unkonw click");
                    return;
                }
            }
            if (HealthDataHistoryActivity.this.y.d(2)) {
                if (HealthDataHistoryActivity.this.b.c() != 0) {
                    HealthDataHistoryActivity.this.e(true, 0);
                }
            } else if (HealthDataHistoryActivity.this.b.a() != 0) {
                HealthDataHistoryActivity.this.d(true);
                HealthDataHistoryActivity.this.e(false);
            }
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_health_data_history);
        this.d = this;
        this.i = true;
        if (this.f != null) {
            this.j = new qjz(this, this.f);
        }
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        this.q = intent.getLongExtra("HealthDataHistory", 0L);
        this.c = MultiUsersManager.INSTANCE.getCurrentUser();
        this.v = intent.getStringExtra(JsbMapKeyNames.H5_USER_ID);
        this.n = qkh.c();
        nsn.a();
        d();
        c();
    }

    /* loaded from: classes6.dex */
    public static class d extends BaseHandler<HealthDataHistoryActivity> {
        d(HealthDataHistoryActivity healthDataHistoryActivity) {
            super(healthDataHistoryActivity);
        }

        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dzA_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(HealthDataHistoryActivity healthDataHistoryActivity, Message message) {
            LogUtil.a("HealthWeight_HealthDataHistoryActivity", "handleMessageWhenReferenceNotNull()");
            if (healthDataHistoryActivity == null || message == null) {
                LogUtil.h("HealthWeight_HealthDataHistoryActivity", "handleMessageWhenReferenceNotNull obj or msg is null");
                return;
            }
            if (message.what == -1) {
                healthDataHistoryActivity.a(((Boolean) message.obj).booleanValue());
                return;
            }
            if (message.what == 2) {
                cfi cfiVar = healthDataHistoryActivity.c;
                List<cfe> list = healthDataHistoryActivity.f10038a;
                if (cfiVar != null && list != null) {
                    WeightDataManager.INSTANCE.removeMapData(cfiVar.i(), list);
                }
                healthDataHistoryActivity.c(false);
                healthDataHistoryActivity.d(false);
                healthDataHistoryActivity.f();
                healthDataHistoryActivity.a();
                return;
            }
            if (message.what == 3) {
                View view = healthDataHistoryActivity.p;
                if (view != null) {
                    view.setVisibility(8);
                }
                ArrayList arrayList = (ArrayList) message.obj;
                if (koq.c(arrayList)) {
                    healthDataHistoryActivity.m.addAll(arrayList);
                }
                LinearLayout linearLayout = healthDataHistoryActivity.x;
                if (linearLayout != null) {
                    linearLayout.setVisibility(koq.c(healthDataHistoryActivity.m) ? 0 : 8);
                }
                LinearLayout linearLayout2 = healthDataHistoryActivity.w;
                if (linearLayout2 != null) {
                    linearLayout2.setVisibility(koq.c(healthDataHistoryActivity.m) ? 8 : 0);
                }
                if (koq.b(healthDataHistoryActivity.m)) {
                    WeightExpandableListViewAdapter weightExpandableListViewAdapter = healthDataHistoryActivity.b;
                    if (weightExpandableListViewAdapter != null) {
                        weightExpandableListViewAdapter.d((ArrayList<String[]>) null, (ArrayList<List<cfe>>) null);
                        return;
                    }
                    return;
                }
                ArrayList arrayList2 = new ArrayList();
                ArrayList arrayList3 = new ArrayList();
                ArrayList arrayList4 = new ArrayList();
                healthDataHistoryActivity.e(healthDataHistoryActivity.m, arrayList4, arrayList2);
                healthDataHistoryActivity.a(arrayList3, arrayList4, arrayList2);
                LogUtil.a("HealthWeight_HealthDataHistoryActivity", "handleMessageWhenReferenceNotNull size ", Integer.valueOf(arrayList.size()), " hasMore", Boolean.valueOf(healthDataHistoryActivity.i));
                if (healthDataHistoryActivity.i) {
                    cfe cfeVar = (cfe) healthDataHistoryActivity.m.get(healthDataHistoryActivity.m.size() - 1);
                    healthDataHistoryActivity.b(cfeVar == null ? System.currentTimeMillis() : cfeVar.au() - 1);
                    return;
                }
                return;
            }
            LogUtil.a("HealthWeight_HealthDataHistoryActivity", "Not get this message");
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.y.d(2)) {
            return;
        }
        a();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        this.m.clear();
        this.p.setVisibility(0);
        this.f10038a = null;
        this.r = System.currentTimeMillis();
        this.g = new a(this.r);
        b(System.currentTimeMillis());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(long j) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeRange(0L, j);
        hiAggregateOption.setGroupUnitType(0);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setCount(500);
        LogUtil.a("HealthWeight_HealthDataHistoryActivity", "initData endTime ", Long.valueOf(j));
        qsj.c(this.c, hiAggregateOption, this.g);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(ArrayList<String[]> arrayList, ArrayList<List<cfe>> arrayList2, List<Double> list) {
        int size = arrayList2.size();
        boolean z = false;
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            String[] strArr = new String[2];
            String d2 = this.n.d(this.q);
            String d3 = this.n.d(arrayList2.get(i2).get(0).au());
            strArr[0] = d3;
            if (d2.equals(d3)) {
                i = i2;
                z = true;
            }
            int size2 = arrayList2.get(i2).size();
            if (size2 == 0) {
                LogUtil.h("HealthWeight_HealthDataHistoryActivity", "setItem daySize = 0");
            } else {
                strArr[1] = (list.get(i2).doubleValue() / size2) + "";
                arrayList.add(strArr);
            }
        }
        this.b.d(arrayList, arrayList2);
        if (z) {
            this.h.expandGroup(i);
            this.h.smoothScrollToPositionFromTop(i, 0);
        } else {
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                this.h.expandGroup(i3);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<cfe> list, List<List<cfe>> list2, List<Double> list3) {
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            cfe cfeVar = list.get(i);
            if (cfeVar != null) {
                String d2 = this.n.d(cfeVar.au());
                if (arrayList.contains(d2)) {
                    int indexOf = arrayList.indexOf(d2);
                    e(cfeVar, list2.get(indexOf));
                    list3.set(indexOf, Double.valueOf(list3.get(indexOf).doubleValue() + cfeVar.ax()));
                } else {
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.add(list.get(i));
                    arrayList.add(d2);
                    list2.add(arrayList2);
                    list3.add(Double.valueOf(cfeVar.ax()));
                }
            }
        }
    }

    private void e(cfe cfeVar, List<cfe> list) {
        if (list == null) {
            return;
        }
        int size = list.size() - 1;
        if (koq.b(list, size)) {
            LogUtil.b("HealthWeight_HealthDataHistoryActivity", "out of bound, return");
            return;
        }
        if (cfeVar.au() > list.get(size).au()) {
            LogUtil.b("HealthWeight_HealthDataHistoryActivity", "add weight bean twice, return");
        } else {
            list.add(cfeVar);
        }
    }

    private void c() {
        this.s.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.HealthDataHistoryActivity.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (HealthDataHistoryActivity.this.o) {
                    HealthDataHistoryActivity.this.b();
                } else {
                    if (HealthDataHistoryActivity.this.getIntent().getBooleanExtra("moveTaskToBack", false)) {
                        HealthDataHistoryActivity.this.moveTaskToBack(true);
                    }
                    HealthDataHistoryActivity.this.finish();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.h.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.HealthDataHistoryActivity.4
            @Override // android.widget.AdapterView.OnItemLongClickListener
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long j) {
                LogUtil.a("HealthWeight_HealthDataHistoryActivity", Integer.toString(i) + " " + Long.toString(j));
                long expandableListPosition = HealthDataHistoryActivity.this.h.getExpandableListPosition(i);
                int packedPositionGroup = ExpandableListView.getPackedPositionGroup(expandableListPosition);
                int packedPositionChild = ExpandableListView.getPackedPositionChild(expandableListPosition);
                if (packedPositionGroup == -1 || packedPositionChild == -1) {
                    return true;
                }
                HealthDataHistoryActivity.this.e(false, i);
                return true;
            }
        });
        this.h.setOnChildClickListener(new ExpandableListView.OnChildClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.HealthDataHistoryActivity.2
            @Override // android.widget.ExpandableListView.OnChildClickListener
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i2, long j) {
                qsj.e(HealthDataHistoryActivity.this.d, HealthDataHistoryActivity.this.b.getChild(i, i2), HealthDataHistoryActivity.this.c);
                ViewClickInstrumentation.childClickOnExpandableListView(expandableListView, view, i, i2);
                return true;
            }
        });
        this.h.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.HealthDataHistoryActivity$$ExternalSyntheticLambda0
            @Override // android.widget.ExpandableListView.OnGroupCollapseListener
            public final void onGroupCollapse(int i) {
                HealthDataHistoryActivity.this.b(i);
            }
        });
        this.h.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.HealthDataHistoryActivity$$ExternalSyntheticLambda1
            @Override // android.widget.ExpandableListView.OnGroupExpandListener
            public final void onGroupExpand(int i) {
                HealthDataHistoryActivity.this.a(i);
            }
        });
    }

    /* synthetic */ void b(int i) {
        jcf.bEk_(this.h, nsf.h(R$string.accessibility_collapsed));
    }

    /* synthetic */ void a(int i) {
        jcf.bEk_(this.h, nsf.h(R$string.accessibility_expanded));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        int i;
        this.k = z;
        Resources resources = getResources();
        if (z) {
            i = R$string.IDS_hw_show_main_health_page_healthdata_bloodpresure_cancel_select;
        } else {
            i = R$string.IDS_hw_show_main_health_page_healthdata_bloodpresure_select;
        }
        this.y.setIconTitle(2, resources.getString(i));
        if (z) {
            i(true);
            e(true);
            this.y.setIcon(2, R.drawable._2131430281_res_0x7f0b0b89);
        } else {
            i(false);
            e(false);
            this.y.setIcon(2, R.drawable._2131430296_res_0x7f0b0b98);
        }
    }

    private void i(boolean z) {
        int groupCount = this.b.getGroupCount();
        for (int i = 0; i < groupCount; i++) {
            int childrenCount = this.b.getChildrenCount(i);
            for (int i2 = 0; i2 < childrenCount; i2++) {
                this.b.b().get(i).set(i2, Boolean.valueOf(z));
            }
        }
        this.b.notifyDataSetChanged();
    }

    private void d() {
        this.p = (LinearLayout) findViewById(R.id.hw_history_loading);
        HealthProgressBar healthProgressBar = (HealthProgressBar) findViewById(R.id.hw_device_history_loading_img);
        this.l = healthProgressBar;
        healthProgressBar.setLayerType(1, null);
        this.s = (CustomTitleBar) nsy.cMc_(this.d, R.id.health_healthdata_history_title_layout);
        this.h = (ExpandableListView) findViewById(R.id.hw_show_health_data_history_listview);
        this.w = (LinearLayout) findViewById(R.id.hw_show_health_data_history_empty_layout);
        this.x = (LinearLayout) findViewById(R.id.hw_show_health_data_history_layout);
        this.y = (HealthToolBar) findViewById(R.id.buttomview);
        View inflate = View.inflate(this.d, R.layout.hw_toolbar_bottomview, null);
        this.e = inflate;
        this.y.cHc_(inflate);
        this.y.setOnSingleTapListener(this.t);
        this.y.cHf_(this);
        this.y.setIcon(1, R.drawable._2131430279_res_0x7f0b0b87);
        this.y.setIconTitle(1, getResources().getString(R$string.IDS_hw_health_show_healthdata_heart_delete));
        if (this.f != null) {
            WeightExpandableListViewAdapter weightExpandableListViewAdapter = new WeightExpandableListViewAdapter(this.f);
            this.b = weightExpandableListViewAdapter;
            this.h.setAdapter(weightExpandableListViewAdapter);
            this.h.setSelector(new ColorDrawable(0));
        }
        cancelAdaptRingRegion();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(boolean z) {
        if (z) {
            if (!this.k && this.b.c() == this.b.a()) {
                this.k = true;
                this.y.setIcon(2, R.drawable._2131430281_res_0x7f0b0b89);
                this.y.setIconTitle(2, getResources().getString(R$string.IDS_hw_show_main_health_page_healthdata_bloodpresure_cancel_select));
            }
            e(true);
            return;
        }
        if (this.k && this.b.c() == this.b.a() - 1) {
            this.k = false;
            this.y.setIcon(2, R.drawable._2131430296_res_0x7f0b0b98);
            this.y.setIconTitle(2, getResources().getString(R$string.IDS_hw_show_main_health_page_healthdata_bloodpresure_select));
        }
        if (this.b.c() == 0) {
            e(false);
        } else {
            e(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final boolean z, final int i) {
        LogUtil.c("HealthWeight_HealthDataHistoryActivity", "showDeleteDialog ", "enter");
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.d);
        builder.e(getString(R$string.IDS_hw_health_show_healthdata_delete)).czE_(getString(R$string.IDS_hw_common_ui_dialog_confirm).toUpperCase(Locale.ROOT), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.HealthDataHistoryActivity.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("HealthWeight_HealthDataHistoryActivity", "it is positive");
                if (z) {
                    HealthDataHistoryActivity.this.e();
                    HealthDataHistoryActivity.this.c(false);
                    HealthDataHistoryActivity.this.d(false);
                } else {
                    HealthDataHistoryActivity.this.d(i);
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czA_(getString(R$string.IDS_hw_show_cancel), new View.OnClickListener() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.HealthDataHistoryActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.c("HealthWeight_HealthDataHistoryActivity", "it is negative");
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.e().show();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        LogUtil.a("HealthWeight_HealthDataHistoryActivity", "cancelSelect mAdapter.isMultiCheck()");
        if (this.b.e()) {
            e(false);
            LogUtil.a("HealthWeight_HealthDataHistoryActivity", "cancelSelect isMultiCheck");
            int groupCount = this.b.getGroupCount();
            for (int i = 0; i < groupCount; i++) {
                int childrenCount = this.b.getChildrenCount(i);
                for (int i2 = 0; i2 < childrenCount; i2++) {
                    this.b.b().get(i).set(i2, false);
                }
            }
            this.b.notifyDataSetChanged();
            c(false);
            d(false);
            return;
        }
        LogUtil.a("HealthWeight_HealthDataHistoryActivity", "cancelSelect finish");
        if (getIntent().getBooleanExtra("moveTaskToBack", false)) {
            moveTaskToBack(true);
        }
        super.onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (int groupCount = this.b.getGroupCount() - 1; groupCount >= 0; groupCount--) {
            for (int childrenCount = this.b.getChildrenCount(groupCount) - 1; childrenCount >= 0; childrenCount--) {
                cfe child = this.b.getChild(groupCount, childrenCount);
                if (child != null && this.b.b().get(groupCount).get(childrenCount).booleanValue()) {
                    HiTimeInterval hiTimeInterval = new HiTimeInterval();
                    hiTimeInterval.setStartTime(child.au());
                    hiTimeInterval.setEndTime(child.av());
                    arrayList.add(hiTimeInterval);
                    arrayList2.add(child);
                }
            }
        }
        this.f10038a = arrayList2;
        qjz qjzVar = this.j;
        if (qjzVar != null) {
            qjzVar.b(arrayList);
        }
        LogUtil.a("HealthWeight_HealthDataHistoryActivity", "deleteDatas ", "end");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(boolean z) {
        LogUtil.a("HealthWeight_HealthDataHistoryActivity", "refreshTop isSelect ", Boolean.valueOf(z));
        if (z) {
            return;
        }
        e(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        LogUtil.a("HealthWeight_HealthDataHistoryActivity", "refreshBottom isSelect ", Boolean.valueOf(z));
        this.b.a(z);
        this.k = false;
        this.y.setIcon(2, R.drawable._2131430296_res_0x7f0b0b98);
        this.y.setIconTitle(2, getResources().getString(R$string.IDS_hw_show_main_health_page_healthdata_bloodpresure_select));
        if (!z) {
            this.s.setTitleText(getString(R$string.IDS_hw_base_health_data_history_record));
            if (!LanguageUtil.bc(this.d)) {
                this.s.setLeftButtonDrawable(getResources().getDrawable(R.drawable._2131428438_res_0x7f0b0456), nsf.h(R$string.accessibility_go_back));
            } else {
                this.s.setLeftButtonDrawable(getResources().getDrawable(R.mipmap._2131820922_res_0x7f11017a), nsf.h(R$string.accessibility_go_back));
            }
            this.o = true;
            this.y.setIconVisible(2, 8);
            this.b.notifyDataSetChanged();
            return;
        }
        this.y.setIconVisible(2, 0);
        this.b.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(boolean z) {
        WeightExpandableListViewAdapter weightExpandableListViewAdapter;
        this.o = true;
        Resources resources = BaseApplication.getContext().getResources();
        if (resources == null) {
            LogUtil.h("HealthWeight_HealthDataHistoryActivity", "setSelectNumVisibility resources is null");
            return;
        }
        this.s.setLeftButtonDrawable(resources.getDrawable(R.drawable._2131428439_res_0x7f0b0457), nsf.h(R$string.accessibility_close));
        if (z && (weightExpandableListViewAdapter = this.b) != null) {
            int c = weightExpandableListViewAdapter.c();
            this.s.setTitleText(resources.getQuantityString(R.plurals._2130903044_res_0x7f030004, UnitUtil.e(c), Integer.valueOf(c)));
        } else {
            this.s.setTitleText(resources.getString(R$string.IDS_hw_show_main_health_page_healthdata_selectNone));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i) {
        LogUtil.a("HealthWeight_HealthDataHistoryActivity", "deleteData ", "enter");
        long expandableListPosition = this.h.getExpandableListPosition(i);
        int packedPositionGroup = ExpandableListView.getPackedPositionGroup(expandableListPosition);
        int packedPositionChild = ExpandableListView.getPackedPositionChild(expandableListPosition);
        if (packedPositionGroup == -1 || packedPositionChild == -1) {
            LogUtil.b("HealthWeight_HealthDataHistoryActivity", "deleteData error, groupPosition=", Integer.valueOf(packedPositionGroup), ", childPosition=", Integer.valueOf(packedPositionChild));
            return;
        }
        cfe child = this.b.getChild(packedPositionGroup, packedPositionChild);
        if (child == null) {
            LogUtil.b("HealthWeight_HealthDataHistoryActivity", "deleteData item is null");
            return;
        }
        ArrayList arrayList = new ArrayList();
        HiTimeInterval hiTimeInterval = new HiTimeInterval();
        hiTimeInterval.setStartTime(child.au());
        hiTimeInterval.setEndTime(child.av());
        arrayList.add(hiTimeInterval);
        qjz qjzVar = this.j;
        if (qjzVar != null) {
            qjzVar.b(arrayList);
        }
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(this.b.getChild(packedPositionGroup, packedPositionChild));
        this.f10038a = arrayList2;
        LogUtil.a("HealthWeight_HealthDataHistoryActivity", "deleteData ", "end");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        LogUtil.a("HealthWeight_HealthDataHistoryActivity", "syncData  ");
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(HiSyncType.HiSyncDataType.c);
        hiSyncOption.setSyncScope(1);
        hiSyncOption.setSyncMethod(2);
        HiHealthNativeApi.a(this).synCloud(hiSyncOption, null);
    }

    protected int[] a(List<cfe> list) {
        HashSet hashSet = new HashSet(16);
        Iterator<cfe> it = list.iterator();
        while (it.hasNext()) {
            hashSet.add(Integer.valueOf(it.next().k()));
        }
        int[] iArr = new int[hashSet.size()];
        Iterator it2 = hashSet.iterator();
        int i = 0;
        while (it2.hasNext()) {
            iArr[i] = ((Integer) it2.next()).intValue();
            i++;
        }
        return iArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<cfe> list, long j) {
        if (koq.b(list)) {
            LogUtil.b("HealthWeight_HealthDataHistoryActivity", "getTypeMap is null");
            d(list);
            return;
        }
        int[] a2 = a(list);
        HiDataSourceFetchOption e = HiDataSourceFetchOption.builder().a(1).c(a2).e();
        LogUtil.a("HealthWeight_HealthDataHistoryActivity", "start setDataSourceType");
        HiHealthNativeApi.a(this.d).fetchDataSource(e, new b(list, new HashMap(16), a2, j));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<cfe> list) {
        Message obtain = Message.obtain();
        obtain.what = 3;
        obtain.obj = list;
        this.f.sendMessage(obtain);
    }

    private boolean j() {
        cfi mainUser = MultiUsersManager.INSTANCE.getMainUser();
        if (mainUser == null || this.c == null || mainUser.i() == null || this.c.i() == null) {
            return false;
        }
        return this.c.i().equals(mainUser.i());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(ArrayList<cfe> arrayList, HealthDataHistoryActivity healthDataHistoryActivity) {
        if (healthDataHistoryActivity.j()) {
            List<cfe> arrayList2 = new ArrayList<>();
            if (koq.c(arrayList)) {
                arrayList2 = arrayList.subList(0, 1);
            }
            if (!qko.b(qko.d(), arrayList2)) {
                LogUtil.a("HealthWeight_HealthDataHistoryActivity", "HealthDataHistoryCallback getWeight same cache");
            } else {
                qko.a(arrayList2);
            }
        }
    }

    /* loaded from: classes6.dex */
    static class a implements TrendFragmentCallback {
        private final WeakReference<HealthDataHistoryActivity> c;
        private final long e;

        private a(HealthDataHistoryActivity healthDataHistoryActivity, long j) {
            this.c = new WeakReference<>(healthDataHistoryActivity);
            this.e = j;
        }

        @Override // com.huawei.ui.main.stories.health.util.TrendFragmentCallback
        public void getWeight(ArrayList<cfe> arrayList, boolean z) {
            if (this.c == null) {
                LogUtil.h("HealthWeight_HealthDataHistoryActivity", "weakRef is null");
                return;
            }
            LogUtil.a("HealthWeight_HealthDataHistoryActivity", "weightBeanList.size()", Integer.valueOf(arrayList.size()));
            HealthDataHistoryActivity healthDataHistoryActivity = this.c.get();
            if (healthDataHistoryActivity != null) {
                healthDataHistoryActivity.i = z;
                if (healthDataHistoryActivity.r != this.e) {
                    LogUtil.h("HealthWeight_HealthDataHistoryActivity", "request weight bean, request time is not same");
                    return;
                }
                c(arrayList);
                if (healthDataHistoryActivity.f != null) {
                    healthDataHistoryActivity.a(arrayList, this.e);
                    HealthDataHistoryActivity.e(arrayList, healthDataHistoryActivity);
                    return;
                }
                return;
            }
            LogUtil.h("HealthWeight_HealthDataHistoryActivity", "dataHistoryActivity is null");
        }

        private void c(List<cfe> list) {
            if (koq.b(list)) {
                return;
            }
            int i = 0;
            while (i < list.size() - 1) {
                int i2 = i + 1;
                if (koq.d(list, i2)) {
                    d(list.get(i), list.get(i2));
                }
                i = i2;
            }
        }

        private void d(cfe cfeVar, cfe cfeVar2) {
            if (cfeVar == null || cfeVar2 == null) {
                return;
            }
            double min = Math.min(cfeVar.ax(), cfeVar2.ax());
            if (min != 0.0d && Math.abs(cfeVar.ax() - cfeVar2.ax()) / min >= 0.4d) {
                ReleaseLogUtil.d("HealthWeight_HealthDataHistoryActivity", "weight data may be wrong, diff = ", Double.valueOf(cfeVar.ax() - cfeVar2.ax()), " isManual1 = ", Boolean.valueOf(cfeVar.r()), " isManual2 = ", Boolean.valueOf(cfeVar2.r()));
            }
        }
    }

    /* loaded from: classes6.dex */
    static class b implements HiDataClientListener {

        /* renamed from: a, reason: collision with root package name */
        private final HashMap<Integer, Boolean> f10040a;
        private final List<cfe> b;
        private final long c;
        private final int[] d;
        private final WeakReference<HealthDataHistoryActivity> e;

        private b(HealthDataHistoryActivity healthDataHistoryActivity, List<cfe> list, HashMap<Integer, Boolean> hashMap, int[] iArr, long j) {
            this.e = new WeakReference<>(healthDataHistoryActivity);
            this.b = list;
            this.f10040a = hashMap;
            this.d = iArr;
            this.c = j;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataClientListener
        public void onResult(List<HiHealthClient> list) {
            HealthDataHistoryActivity healthDataHistoryActivity = this.e.get();
            if (healthDataHistoryActivity == null) {
                return;
            }
            if (healthDataHistoryActivity.r != this.c) {
                LogUtil.b("HealthWeight_HealthDataHistoryActivity", "request data client, request time is not same");
                return;
            }
            if (koq.b(list)) {
                LogUtil.b("HealthWeight_HealthDataHistoryActivity", "getDataSourceType clientList is null or size is 0");
                healthDataHistoryActivity.d(this.b);
                return;
            }
            ProductMapParseUtil.b(BaseApplication.getContext());
            int i = 0;
            for (HiHealthClient hiHealthClient : list) {
                boolean c = rrb.c(hiHealthClient);
                if (!c) {
                    c = healthDataHistoryActivity.d(hiHealthClient);
                }
                this.f10040a.put(Integer.valueOf(this.d[i]), Boolean.valueOf(c));
                i++;
            }
            LogUtil.a("HealthWeight_HealthDataHistoryActivity", "end setDataSourceType");
            for (cfe cfeVar : this.b) {
                if (this.f10040a.containsKey(Integer.valueOf(cfeVar.k()))) {
                    cfeVar.b(this.f10040a.get(Integer.valueOf(cfeVar.k())).booleanValue());
                } else {
                    cfeVar.b(false);
                }
            }
            healthDataHistoryActivity.d(this.b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(HiHealthClient hiHealthClient) {
        HiDeviceInfo hiDeviceInfo = hiHealthClient.getHiDeviceInfo();
        if (hiDeviceInfo == null) {
            return false;
        }
        String e = rrf.e(String.valueOf(hiDeviceInfo.getDeviceType()));
        LogUtil.a("HealthWeight_HealthDataHistoryActivity", "isFromWatch category ", e);
        return "06D".equals(e);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
