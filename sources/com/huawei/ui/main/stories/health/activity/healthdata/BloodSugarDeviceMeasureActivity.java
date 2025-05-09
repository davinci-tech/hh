package com.huawei.ui.main.stories.health.activity.healthdata;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import androidx.fragment.app.FragmentTransaction;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiDataSourceFetchOption;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataClientListener;
import com.huawei.hihealth.data.model.HiBloodSugarMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.health.activity.healthdata.BloodSugarDeviceMeasureActivity;
import com.huawei.ui.main.stories.health.fragment.BloodSugarMeasureDetailFragment;
import com.huawei.ui.main.stories.health.fragment.BloodSugarMeasureEmptyFragment;
import com.huawei.ui.main.stories.health.fragment.BloodSugarMeasureMutipleFragment;
import com.huawei.ui.main.stories.template.health.module.HealthDataDetailActivity;
import defpackage.deb;
import defpackage.dks;
import defpackage.ixx;
import defpackage.koq;
import defpackage.kor;
import defpackage.nrh;
import defpackage.nsf;
import defpackage.qkg;
import defpackage.qkh;
import defpackage.qqk;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes6.dex */
public class BloodSugarDeviceMeasureActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private boolean f10024a;
    private CountDownLatch b;
    private List<deb> c;
    private List<DataConfirmRefreshListener> d;
    private int e;
    private long f;
    private String g;
    private int h;
    private String i;
    private boolean k;
    private int l;
    private List<List<qkg>> m;
    private long o;
    private CustomTitleBar q;
    private List<BaseFragment> j = new ArrayList(3);
    private int s = -1;
    private Handler n = new e(this);

    public interface DataConfirmRefreshListener {
        void refresh();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_blood_sugar_device_measure);
        clearBackgroundDrawable();
        o();
        k();
        m();
    }

    public void c(String str, Map<String, Object> map) {
        ixx.d().d(this, str, map, 0);
    }

    private void q() {
        String value = AnalyticsValue.HEALTH_BLOOD_SUGAR_NOTIFICATION_2040083.value();
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        c(value, hashMap);
    }

    private void o() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        String stringExtra = intent.getStringExtra("entrance");
        LogUtil.a("BloodSugarDeviceMeasureActivity", "entrance=", stringExtra);
        this.i = stringExtra;
        this.g = intent.getStringExtra("product_name");
        if ("jump_from_blood_sugar_notify".equals(this.i)) {
            q();
            long longExtra = intent.getLongExtra("start_time", 0L);
            this.o = longExtra;
            long longExtra2 = intent.getLongExtra("end_time", longExtra);
            this.f = longExtra2;
            if (this.o != longExtra2) {
                this.k = true;
                this.c = b(intent.getStringExtra("blood_sugar_data_list"));
            }
        } else if ("jump_from_blood_sugar_history".equals(this.i) || "jump_from_blood_sugar_home".equals(this.i) || "jump_from_blood_sugar_feedback".equals(this.i)) {
            long longExtra3 = intent.getLongExtra("start_time", 0L);
            this.o = longExtra3;
            this.f = longExtra3;
            this.s = intent.getIntExtra("time_period", -1);
            this.g = getString(R$string.IDS_hw_show_healthdata_bloodsugar_measurement_record);
        } else {
            LogUtil.a("BloodSugarDeviceMeasureActivity", "jump_from_blood_sugar_initiative_measure");
        }
        LogUtil.a("BloodSugarDeviceMeasureActivity", "mDeviceName=", this.g, ", startTime=", Long.valueOf(this.o), ", endTime=", Long.valueOf(this.f));
    }

    private List<deb> b(String str) {
        JsonArray asJsonArray = JsonParser.parseString(str).getAsJsonArray();
        Gson gson = new Gson();
        ArrayList arrayList = new ArrayList();
        Iterator<JsonElement> it = asJsonArray.iterator();
        while (it.hasNext()) {
            arrayList.add((deb) gson.fromJson(it.next(), deb.class));
        }
        return arrayList;
    }

    private void k() {
        this.q = (CustomTitleBar) findViewById(R.id.titlebar);
        if ("jump_from_blood_sugar_notify".equals(this.i) || "jump_from_blood_sugar_initiative_measure".equals(this.i)) {
            if (!TextUtils.isEmpty(this.g)) {
                this.q.setTitleText(this.g);
            }
            this.q.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: qcf
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    BloodSugarDeviceMeasureActivity.this.dzd_(view);
                }
            });
        } else {
            if ("jump_from_blood_sugar_history".equals(this.i) || "jump_from_blood_sugar_home".equals(this.i) || "jump_from_blood_sugar_feedback".equals(this.i)) {
                this.q.setTitleText(this.g);
                this.q.setLeftButtonDrawable(getDrawable(R.drawable._2131430274_res_0x7f0b0b82), nsf.h(R$string.accessibility_close));
                this.q.setRightButtonDrawable(getDrawable(R.drawable._2131430292_res_0x7f0b0b94), nsf.h(R$string.IDS_contact_confirm));
                this.q.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: qce
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        BloodSugarDeviceMeasureActivity.this.dze_(view);
                    }
                });
                this.q.setRightButtonVisibility(0);
                return;
            }
            LogUtil.h("BloodSugarDeviceMeasureActivity", "initTitleBar else");
        }
    }

    public /* synthetic */ void dzd_(View view) {
        j();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void dze_(View view) {
        j();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void m() {
        if ("jump_from_blood_sugar_notify".equals(this.i) || "jump_from_blood_sugar_history".equals(this.i) || "jump_from_blood_sugar_home".equals(this.i) || "jump_from_blood_sugar_feedback".equals(this.i)) {
            a(this.o, this.f, this.s, 0);
        } else if ("jump_from_blood_sugar_initiative_measure".equals(this.i)) {
            l();
            w();
        } else {
            LogUtil.h("BloodSugarDeviceMeasureActivity", "initView else");
        }
    }

    private void w() {
        if (this.l == 0) {
            b(4);
        } else if (f()) {
            b(1);
        } else {
            b(0);
        }
    }

    private void p() {
        char c;
        HashMap hashMap;
        String str = this.i;
        str.hashCode();
        int hashCode = str.hashCode();
        if (hashCode == -824305694) {
            if (str.equals("jump_from_blood_sugar_history")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != -758500271) {
            if (hashCode == 1397670459 && str.equals("jump_from_blood_sugar_notify")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str.equals("jump_from_blood_sugar_home")) {
                c = 1;
            }
            c = 65535;
        }
        if (c == 0) {
            hashMap = new HashMap();
            hashMap.put("type", 3);
        } else if (c == 1) {
            hashMap = new HashMap();
            hashMap.put("type", 2);
        } else if (c != 2) {
            hashMap = null;
        } else {
            hashMap = new HashMap();
            hashMap.put("type", 1);
        }
        if (hashMap != null) {
            String value = AnalyticsValue.HEALTH_HEALTH_BLOODSUGAR_CARD_CONFIRMED_2030071.value();
            hashMap.put("click", "1");
            c(value, hashMap);
        }
    }

    private <T extends BaseFragment> T a(Class<? extends T> cls) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        T t = null;
        for (BaseFragment baseFragment : this.j) {
            if (cls.isInstance(baseFragment)) {
                t = cls.cast(baseFragment);
            } else {
                beginTransaction.hide(baseFragment);
            }
        }
        if (t == null) {
            try {
                t = cls.newInstance();
            } catch (IllegalAccessException | InstantiationException e2) {
                LogUtil.b("BloodSugarDeviceMeasureActivity", e2);
            }
            beginTransaction.add(R.id.hw_show_healthdata_bloodsugar_measure_fragment_container, t);
            this.j.add(t);
        }
        beginTransaction.show(t);
        beginTransaction.commitAllowingStateLoss();
        return t;
    }

    public void b(int i) {
        LogUtil.a("BloodSugarDeviceMeasureActivity", "switchPage=", Integer.valueOf(i));
        if (i == 0) {
            p();
            a(BloodSugarMeasureDetailFragment.class);
            return;
        }
        if (i == 1) {
            a(BloodSugarMeasureMutipleFragment.class);
            return;
        }
        if (i == 2) {
            if ("jump_from_blood_sugar_feedback".equals(this.i)) {
                setResult(200);
                finish();
                return;
            } else {
                v();
                return;
            }
        }
        if (i == 3) {
            HealthDataDetailActivity.a(this, "BloodSugarCardConstructor", 8);
            finish();
        } else if (i == 4) {
            a(BloodSugarMeasureEmptyFragment.class);
        } else {
            if (i != 5) {
                return;
            }
            finish();
        }
    }

    private void v() {
        qkg u = u();
        if (u == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("titleName", this.g);
        intent.putExtra("time", u.h());
        intent.putExtra("bloodTimePeriod", BigDecimal.valueOf(u.o()).intValue());
        intent.putExtra("bloodNum", u.m());
        intent.setClass(this, BloodSugarFeedbackActivity.class);
        startActivityForResult(intent, 1);
    }

    private qkg u() {
        if (!koq.d(this.m, this.h)) {
            return null;
        }
        List<qkg> list = this.m.get(this.h);
        qkg remove = list.remove(this.e);
        if (list.isEmpty()) {
            this.m.remove(this.h);
        }
        this.l--;
        return remove;
    }

    @Override // androidx.fragment.app.FragmentActivity
    public void onResumeFragments() {
        super.onResumeFragments();
        if (this.f10024a) {
            this.f10024a = false;
            s();
        }
    }

    private void s() {
        if (this.k) {
            int i = this.l;
            if (i == 0) {
                a(BloodSugarMeasureEmptyFragment.class);
                return;
            } else {
                LogUtil.a("BloodSugarDeviceMeasureActivity", "current mHealthDataCount = ", Integer.valueOf(i));
                a(BloodSugarMeasureMutipleFragment.class);
                return;
            }
        }
        if ("jump_from_blood_sugar_notify".equals(this.i)) {
            b(3);
        }
        finish();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i != 1) {
            super.onActivityResult(i, i2, intent);
        } else {
            this.f10024a = true;
        }
    }

    private void j() {
        if ("jump_from_blood_sugar_history".equals(this.i) || "jump_from_blood_sugar_home".equals(this.i) || "jump_from_blood_sugar_feedback".equals(this.i)) {
            b(5);
            return;
        }
        if ("jump_from_blood_sugar_initiative_measure".equals(this.i)) {
            x();
            return;
        }
        BaseFragment baseFragment = null;
        for (BaseFragment baseFragment2 : this.j) {
            if (!baseFragment2.isHidden()) {
                baseFragment = baseFragment2;
            }
        }
        if (baseFragment instanceof BloodSugarMeasureDetailFragment) {
            if (this.k) {
                if (this.l == 0) {
                    b(4);
                    return;
                } else {
                    b(1);
                    return;
                }
            }
            b(3);
            return;
        }
        b(3);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        j();
    }

    private void x() {
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this);
        builder.e(getString(R$string.IDS_hw_show_healthdata_bloodsugar_is_save_data)).czE_(getString(R$string.IDS_hw_show_healthdata_bloodsugar_certain), new View.OnClickListener() { // from class: qcg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BloodSugarDeviceMeasureActivity.this.dzf_(view);
            }
        }).czA_(getString(R$string.IDS_hw_health_show_common_dialog_cancle_button), new View.OnClickListener() { // from class: qcd
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BloodSugarDeviceMeasureActivity.this.dzg_(view);
            }
        });
        builder.e().show();
    }

    public /* synthetic */ void dzf_(View view) {
        qkg d2 = d();
        if (d2 == null) {
            ViewClickInstrumentation.clickOnView(view);
        } else {
            d(d2, BigDecimal.valueOf(d2.o()).intValue(), d2.h());
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public /* synthetic */ void dzg_(View view) {
        b(5);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void l() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        int intExtra = intent.getIntExtra("time_period", 0);
        float floatExtra = intent.getFloatExtra("blood_sugar_value", 0.0f);
        long longExtra = intent.getLongExtra("blood_sugar_time", 0L);
        String stringExtra = intent.getStringExtra("blood_sugar_unique_id");
        qkg qkgVar = new qkg();
        qkgVar.c(intExtra);
        qkgVar.b(floatExtra);
        qkgVar.a(longExtra);
        qkgVar.d(stringExtra);
        HiBloodSugarMetaData hiBloodSugarMetaData = new HiBloodSugarMetaData();
        hiBloodSugarMetaData.setConfirmed(false);
        qkgVar.b(HiJsonUtil.e(hiBloodSugarMetaData));
        ArrayList arrayList = new ArrayList();
        arrayList.add(qkgVar);
        ArrayList arrayList2 = new ArrayList();
        this.m = arrayList2;
        arrayList2.add(arrayList);
        this.l = c(this.m);
    }

    private void a(long j, long j2, int i, int i2) {
        qkh.c().b(BaseApplication.getContext(), j, j2, 0, new b(i, i2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<List<qkg>> list, int i, final int i2) {
        LogUtil.a("BloodSugarDeviceMeasureActivity", "count=", Integer.valueOf(c(list)));
        if ("jump_from_blood_sugar_notify".equals(this.i)) {
            this.m = e(list);
        } else {
            this.m = b(list, i);
        }
        int c = c(this.m);
        this.l = c;
        LogUtil.a("BloodSugarDeviceMeasureActivity", "mHealthDataCount=", Integer.valueOf(c));
        runOnUiThread(new Runnable() { // from class: qcj
            @Override // java.lang.Runnable
            public final void run() {
                BloodSugarDeviceMeasureActivity.this.e(i2);
            }
        });
    }

    public /* synthetic */ void e(int i) {
        if (i == 1) {
            t();
        } else {
            w();
        }
    }

    private boolean c(qkg qkgVar) {
        Iterator<deb> it = this.c.iterator();
        while (it.hasNext()) {
            deb next = it.next();
            if (qkgVar.h() == next.getEndTime() && BigDecimal.valueOf(qkgVar.o()).intValue() == deb.e(next.getEndTime())) {
                it.remove();
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x005f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.util.List<java.util.List<defpackage.qkg>> e(java.util.List<java.util.List<defpackage.qkg>> r7) {
        /*
            r6 = this;
            if (r7 != 0) goto L7
            java.util.List r7 = java.util.Collections.emptyList()
            return r7
        L7:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Iterator r7 = r7.iterator()
        L10:
            boolean r1 = r7.hasNext()
            if (r1 == 0) goto L6e
            java.lang.Object r1 = r7.next()
            java.util.List r1 = (java.util.List) r1
            java.util.Iterator r1 = r1.iterator()
            r2 = 0
        L21:
            boolean r3 = r1.hasNext()
            if (r3 == 0) goto L68
            java.lang.Object r3 = r1.next()
            qkg r3 = (defpackage.qkg) r3
            java.lang.String r4 = r3.f()
            java.lang.Class<com.huawei.hihealth.data.model.HiBloodSugarMetaData> r5 = com.huawei.hihealth.data.model.HiBloodSugarMetaData.class
            java.lang.Object r4 = com.huawei.hihealth.util.HiJsonUtil.e(r4, r5)
            com.huawei.hihealth.data.model.HiBloodSugarMetaData r4 = (com.huawei.hihealth.data.model.HiBloodSugarMetaData) r4
            if (r4 != 0) goto L3c
            goto L21
        L3c:
            java.util.List<deb> r5 = r6.c
            boolean r5 = defpackage.koq.b(r5)
            if (r5 != 0) goto L4b
            boolean r4 = r6.c(r3)
            if (r4 != 0) goto L5d
            goto L21
        L4b:
            boolean r4 = r4.getConfirmed()
            if (r4 == 0) goto L52
            goto L21
        L52:
            java.lang.String r4 = "Add data to list"
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            java.lang.String r5 = "BloodSugarDeviceMeasureActivity"
            com.huawei.hwlogsmodel.LogUtil.a(r5, r4)
        L5d:
            if (r2 != 0) goto L64
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
        L64:
            r2.add(r3)
            goto L21
        L68:
            if (r2 == 0) goto L10
            r0.add(r2)
            goto L10
        L6e:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.health.activity.healthdata.BloodSugarDeviceMeasureActivity.e(java.util.List):java.util.List");
    }

    private List<List<qkg>> b(List<List<qkg>> list, int i) {
        if (list == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Iterator<List<qkg>> it = list.iterator();
        while (it.hasNext()) {
            ArrayList arrayList2 = null;
            for (qkg qkgVar : it.next()) {
                if (i == -1 || i == qkgVar.o()) {
                    if (arrayList2 == null) {
                        arrayList2 = new ArrayList();
                    }
                    arrayList2.add(qkgVar);
                }
            }
            if (arrayList2 != null) {
                arrayList.add(arrayList2);
            }
        }
        return arrayList;
    }

    public void c() {
        if (this.m == null) {
            return;
        }
        this.b = new CountDownLatch(this.l);
        for (int i = 0; i < this.m.size(); i++) {
            for (int i2 = 0; i2 < this.m.get(i).size(); i2++) {
                qkg qkgVar = this.m.get(i).get(i2);
                if (qkgVar != null) {
                    d(qkgVar, BigDecimal.valueOf(qkgVar.o()).intValue(), qkgVar.h());
                }
            }
        }
    }

    public void b() {
        this.b = null;
        this.c = null;
        a(this.o, this.f, this.s, 1);
    }

    private void t() {
        if (this.l == 0) {
            b(4);
        } else {
            r();
        }
    }

    public void b(int i, long j) {
        if (j > System.currentTimeMillis()) {
            nrh.d(this, getResources().getString(R$string.IDS_hw_health_show_healthdata_timeerror));
            return;
        }
        qkg d2 = d();
        if (d2 == null) {
            return;
        }
        d(d2, i, j);
    }

    private void d(final qkg qkgVar, final int i, final long j) {
        if (i == BigDecimal.valueOf(qkgVar.o()).intValue() && j == qkgVar.h()) {
            a(qkgVar, i, j);
            e(qkgVar);
        } else {
            int intValue = BigDecimal.valueOf(qkgVar.o()).intValue();
            long h = qkgVar.h();
            qqk.a().c();
            qkh.c().e(BaseApplication.getContext(), intValue, h, h, new IBaseResponseCallback() { // from class: com.huawei.ui.main.stories.health.activity.healthdata.BloodSugarDeviceMeasureActivity.2
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    if (i2 != 0) {
                        LogUtil.h("BloodSugarDeviceMeasureActivity", "Failed to deleteBloodSuagrData, errorCode=", Integer.valueOf(i2));
                    } else {
                        LogUtil.a("BloodSugarDeviceMeasureActivity", "Succeeded to deleteBloodSuagrData");
                    }
                    BloodSugarDeviceMeasureActivity.this.a(qkgVar, i, j);
                    BloodSugarDeviceMeasureActivity.this.e(qkgVar);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(qkg qkgVar, int i, long j) {
        qkgVar.c(i);
        qkgVar.a(j);
        HiBloodSugarMetaData hiBloodSugarMetaData = (HiBloodSugarMetaData) HiJsonUtil.e(qkgVar.f(), HiBloodSugarMetaData.class);
        if (hiBloodSugarMetaData != null) {
            hiBloodSugarMetaData.setConfirmed(true);
            qkgVar.b(HiJsonUtil.e(hiBloodSugarMetaData));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(qkg qkgVar) {
        if (qkgVar == null) {
            LogUtil.b("BloodSugarDeviceMeasureActivity", "insert healthData is null");
            return;
        }
        HiHealthData hiHealthData = new HiHealthData(10001);
        String j = qkgVar.j();
        LogUtil.a("BloodSugarDeviceMeasureActivity", "time=", Long.valueOf(qkgVar.h()), "uuid=", j);
        hiHealthData.setValue(dks.i(j) ? qkgVar.n() : qkgVar.m());
        hiHealthData.setType((int) qkgVar.o());
        hiHealthData.setDeviceUuid(j);
        hiHealthData.setMetaData(qkgVar.f());
        hiHealthData.setStartTime(qkgVar.h());
        hiHealthData.setEndTime(qkgVar.h());
        HiHealthNativeApi.a(BaseApplication.getContext()).fetchDataSource(HiDataSourceFetchOption.builder().a(1).c(new int[]{qkgVar.a()}).e(), new d(this, hiHealthData));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        CountDownLatch countDownLatch = this.b;
        if (countDownLatch != null) {
            countDownLatch.countDown();
            if (this.b.getCount() == 0) {
                Message obtainMessage = this.n.obtainMessage();
                obtainMessage.what = 1;
                this.n.sendMessage(obtainMessage);
                return;
            }
            return;
        }
        runOnUiThread(new Runnable() { // from class: qcc
            @Override // java.lang.Runnable
            public final void run() {
                BloodSugarDeviceMeasureActivity.this.i();
            }
        });
    }

    public /* synthetic */ void i() {
        b(2);
    }

    public static class d implements HiDataClientListener {
        private final HiHealthData d;
        private final WeakReference<BloodSugarDeviceMeasureActivity> e;

        d(BloodSugarDeviceMeasureActivity bloodSugarDeviceMeasureActivity, HiHealthData hiHealthData) {
            this.e = new WeakReference<>(bloodSugarDeviceMeasureActivity);
            this.d = hiHealthData;
        }

        @Override // com.huawei.hihealth.data.listener.HiDataClientListener
        public void onResult(List<HiHealthClient> list) {
            kor.a().d(BaseApplication.getContext(), this.d, koq.b(list) ? null : list.get(0).getPackageName(), new IBaseResponseCallback() { // from class: qcl
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    BloodSugarDeviceMeasureActivity.d.this.d(i, obj);
                }
            });
        }

        public /* synthetic */ void d(int i, Object obj) {
            LogUtil.a("BloodSugarDeviceMeasureActivity", "DataClientListener onResponse errorCode ", Integer.valueOf(i));
            BloodSugarDeviceMeasureActivity bloodSugarDeviceMeasureActivity = this.e.get();
            if (bloodSugarDeviceMeasureActivity != null && !bloodSugarDeviceMeasureActivity.isFinishing()) {
                bloodSugarDeviceMeasureActivity.n();
            } else {
                LogUtil.h("BloodSugarDeviceMeasureActivity", "DataClientListener onResult onResponse activity ", bloodSugarDeviceMeasureActivity);
            }
        }
    }

    static class e extends BaseHandler<BloodSugarDeviceMeasureActivity> {
        e(BloodSugarDeviceMeasureActivity bloodSugarDeviceMeasureActivity) {
            super(bloodSugarDeviceMeasureActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dzh_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(BloodSugarDeviceMeasureActivity bloodSugarDeviceMeasureActivity, Message message) {
            if (message == null) {
                LogUtil.h("BloodSugarDeviceMeasureActivity", "handleMessageWhenReferenceNotNull message is null");
            } else if (message.what == 1) {
                bloodSugarDeviceMeasureActivity.b();
            } else {
                LogUtil.h("BloodSugarDeviceMeasureActivity", "handleMessageWhenReferenceNotNull message is error");
            }
        }
    }

    private int c(List<List<qkg>> list) {
        int i = 0;
        if (list == null) {
            return 0;
        }
        Iterator<List<qkg>> it = list.iterator();
        while (it.hasNext()) {
            i += it.next().size();
        }
        return i;
    }

    public int a() {
        return this.l;
    }

    public List<List<qkg>> g() {
        return this.m;
    }

    public void c(int i, int i2) {
        this.h = i;
        this.e = i2;
        b(0);
    }

    public qkg d() {
        if (koq.d(this.m, this.h) && koq.d(this.m.get(this.h), this.e)) {
            return this.m.get(this.h).get(this.e);
        }
        return null;
    }

    public String e() {
        return this.i;
    }

    public boolean f() {
        return this.k;
    }

    public CustomTitleBar h() {
        return this.q;
    }

    public void c(DataConfirmRefreshListener dataConfirmRefreshListener) {
        if (this.d == null) {
            this.d = new ArrayList();
        }
        this.d.add(dataConfirmRefreshListener);
    }

    private void r() {
        Iterator<DataConfirmRefreshListener> it = this.d.iterator();
        while (it.hasNext()) {
            it.next().refresh();
        }
    }

    static class b implements CommonUiBaseResponse {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<BloodSugarDeviceMeasureActivity> f10026a;
        private final int b;
        private final int e;

        private b(BloodSugarDeviceMeasureActivity bloodSugarDeviceMeasureActivity, int i, int i2) {
            this.f10026a = new WeakReference<>(bloodSugarDeviceMeasureActivity);
            this.e = i;
            this.b = i2;
        }

        @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
        public void onResponse(int i, Object obj) {
            BloodSugarDeviceMeasureActivity bloodSugarDeviceMeasureActivity = this.f10026a.get();
            if (bloodSugarDeviceMeasureActivity == null) {
                return;
            }
            if (i != 0) {
                LogUtil.h("BloodSugarDeviceMeasureActivity", "Failed to readBloodSugarData, errorCode=", Integer.valueOf(i));
            } else {
                LogUtil.a("BloodSugarDeviceMeasureActivity", "Succeeded to readBloodSugarData");
            }
            if (obj instanceof List) {
                bloodSugarDeviceMeasureActivity.e((List) obj, this.e, this.b);
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
