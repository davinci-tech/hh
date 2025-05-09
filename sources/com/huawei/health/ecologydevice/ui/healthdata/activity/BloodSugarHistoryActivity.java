package com.huawei.health.ecologydevice.ui.healthdata.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import androidx.fragment.app.FragmentTransaction;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.open.data.model.CommonUiResponse;
import com.huawei.health.ecologydevice.ui.healthdata.activity.BloodSugarHistoryActivity;
import com.huawei.health.ecologydevice.ui.healthdata.fragment.BloodSugarBaseFragment;
import com.huawei.health.ecologydevice.ui.healthdata.fragment.BloodSugarDetailFragment;
import com.huawei.health.ecologydevice.ui.healthdata.fragment.BloodSugarFeedbackFragment;
import com.huawei.health.ecologydevice.ui.healthdata.fragment.BloodSugarMultipleFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiBloodSugarMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.dei;
import defpackage.diy;
import defpackage.dks;
import defpackage.koq;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes8.dex */
public class BloodSugarHistoryActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private List<List<HiHealthData>> f2318a;
    private CountDownLatch b;
    private List<DataConfirmRefreshListener> c;
    private int d;
    private int e;
    private dcz f;
    private String h;
    private int j;
    private String l;
    private final List<BaseFragment> g = new ArrayList(3);
    private Handler i = new d(this);

    public interface DataConfirmRefreshListener {
        void refresh();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_blood_sugar_history);
        clearBackgroundDrawable();
        n();
        k();
        l();
    }

    private void n() {
        Intent intent = getIntent();
        if (intent == null) {
            return;
        }
        ContentValues contentValues = (ContentValues) intent.getParcelableExtra("commonDeviceInfo");
        if (contentValues != null) {
            this.h = contentValues.getAsString("productId");
            this.l = contentValues.getAsString("uniqueId");
        }
        this.f = ResourceManager.e().b(this.h);
    }

    private void k() {
        CustomTitleBar customTitleBar = (CustomTitleBar) findViewById(R.id.titlebar);
        dcz dczVar = this.f;
        String d2 = dczVar != null ? dcx.d(this.h, dczVar.n().b()) : "";
        if (!TextUtils.isEmpty(d2)) {
            customTitleBar.setTitleText(d2);
        }
        customTitleBar.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: dfp
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BloodSugarHistoryActivity.this.Ug_(view);
            }
        });
    }

    public /* synthetic */ void Ug_(View view) {
        b();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void l() {
        d(0);
    }

    public void e(int i) {
        LogUtil.a("BloodSugarHistoryActivity", "switchPage=", Integer.valueOf(i));
        if (i == 0) {
            d(BloodSugarDetailFragment.class);
            return;
        }
        if (i == 1) {
            d(BloodSugarFeedbackFragment.class);
            return;
        }
        if (i == 2) {
            d(BloodSugarMultipleFragment.class);
            return;
        }
        if (i == 3) {
            diy.b(this, HealthDevice.HealthDeviceKind.HDK_BLOOD_SUGAR);
        } else if (i == 4) {
            diy.e(this);
        } else {
            if (i != 5) {
                return;
            }
            finish();
        }
    }

    private <T extends BaseFragment> T d(Class<? extends T> cls) {
        FragmentTransaction beginTransaction = getSupportFragmentManager().beginTransaction();
        T t = null;
        for (BaseFragment baseFragment : this.g) {
            if (cls.isInstance(baseFragment)) {
                t = cls.cast(baseFragment);
            } else {
                beginTransaction.hide(baseFragment);
            }
        }
        if (t == null) {
            try {
                t = cls.newInstance();
            } catch (IllegalAccessException | InstantiationException e) {
                LogUtil.b("BloodSugarHistoryActivity", e);
            }
            beginTransaction.add(R.id.blood_sugar_fragment_container, t);
            this.g.add(t);
        }
        beginTransaction.show(t);
        beginTransaction.commitAllowingStateLoss();
        return t;
    }

    public void b() {
        BaseFragment baseFragment = null;
        for (BaseFragment baseFragment2 : this.g) {
            if (!baseFragment2.isHidden()) {
                baseFragment = baseFragment2;
            }
        }
        if (baseFragment instanceof BloodSugarBaseFragment) {
            if (this.e == 0) {
                i();
                return;
            } else {
                o();
                return;
            }
        }
        j();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        b();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Handler handler = this.i;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.i = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final int i) {
        dei.c().e(this.l, new CommonUiResponse() { // from class: dfu
            @Override // com.huawei.health.ecologydevice.open.data.model.CommonUiResponse
            public final void onResponse(int i2, Object obj) {
                BloodSugarHistoryActivity.this.c(i, i2, (List) obj);
            }
        });
    }

    public /* synthetic */ void c(int i, int i2, List list) {
        if (i2 != 0) {
            LogUtil.h("BloodSugarHistoryActivity", "Failed to readBloodSugarData, errorCode = ", Integer.valueOf(i2));
        } else {
            LogUtil.a("BloodSugarHistoryActivity", "Succeeded to readBloodSugarData");
        }
        c((List<List<HiHealthData>>) list, i);
    }

    private void c(List<List<HiHealthData>> list, final int i) {
        LogUtil.a("BloodSugarHistoryActivity", "loadDataResult the count of BloodSugar is ", Integer.valueOf(a(list)));
        List<List<HiHealthData>> b = b(list);
        this.f2318a = b;
        int a2 = a(b);
        this.e = a2;
        LogUtil.a("BloodSugarHistoryActivity", "loadDataResult mBloodSugarListsCount = ", Integer.valueOf(a2));
        runOnUiThread(new Runnable() { // from class: dfr
            @Override // java.lang.Runnable
            public final void run() {
                BloodSugarHistoryActivity.this.a(i);
            }
        });
    }

    public /* synthetic */ void a(int i) {
        if (i == 1) {
            p();
        } else {
            t();
        }
    }

    private List<List<HiHealthData>> b(List<List<HiHealthData>> list) {
        if (list == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        Iterator<List<HiHealthData>> it = list.iterator();
        while (it.hasNext()) {
            a(it.next(), arrayList);
        }
        return arrayList;
    }

    private void a(List<HiHealthData> list, List<List<HiHealthData>> list2) {
        ArrayList arrayList = null;
        for (HiHealthData hiHealthData : list) {
            HiBloodSugarMetaData hiBloodSugarMetaData = (HiBloodSugarMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiBloodSugarMetaData.class);
            if (hiBloodSugarMetaData != null && !hiBloodSugarMetaData.getConfirmed()) {
                LogUtil.a("BloodSugarHistoryActivity", "Add data to list");
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(hiHealthData);
            }
        }
        if (arrayList != null) {
            list2.add(arrayList);
        }
    }

    private void t() {
        if (d() == 0) {
            i();
        } else if (d() > 1) {
            o();
        } else {
            f();
        }
    }

    private void p() {
        m();
    }

    public void a() {
        if (this.f2318a == null) {
            return;
        }
        this.b = new CountDownLatch(this.e);
        for (int i = 0; i < this.f2318a.size(); i++) {
            for (int i2 = 0; i2 < this.f2318a.get(i).size(); i2++) {
                b(this.f2318a.get(i).get(i2), BigDecimal.valueOf(r3.getType()).intValue());
            }
        }
    }

    public void c(int i) {
        HiHealthData c = c();
        if (c == null) {
            LogUtil.h("BloodSugarHistoryActivity", "bloodSugar is null");
        } else {
            b(c, i);
        }
    }

    private void b(final HiHealthData hiHealthData, final int i) {
        int intValue = BigDecimal.valueOf(hiHealthData.getType()).intValue();
        if (i == intValue) {
            LogUtil.c("BloodSugarHistoryActivity", "The timePeriod is not modified");
            d(hiHealthData, i);
            d(hiHealthData);
        } else {
            LogUtil.c("BloodSugarHistoryActivity", "The timePeriod is modified");
            long endTime = hiHealthData.getEndTime();
            dei.c().a(endTime, endTime, intValue, new IBaseResponseCallback() { // from class: dft
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i2, Object obj) {
                    BloodSugarHistoryActivity.this.a(hiHealthData, i, i2, obj);
                }
            });
        }
    }

    public /* synthetic */ void a(HiHealthData hiHealthData, int i, int i2, Object obj) {
        e(hiHealthData, i, i2);
    }

    private void e(HiHealthData hiHealthData, int i, int i2) {
        if (i2 != 0) {
            LogUtil.h("BloodSugarHistoryActivity", "Failed to deleteBloodSugarData, errorCode=", Integer.valueOf(i2));
            return;
        }
        LogUtil.a("BloodSugarHistoryActivity", "Succeeded to deleteBloodSugarData");
        d(hiHealthData, i);
        d(hiHealthData);
    }

    private void d(HiHealthData hiHealthData, int i) {
        hiHealthData.setType(i);
        HiBloodSugarMetaData hiBloodSugarMetaData = (HiBloodSugarMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiBloodSugarMetaData.class);
        if (hiBloodSugarMetaData != null) {
            hiBloodSugarMetaData.setConfirmed(true);
            hiHealthData.setMetaData(HiJsonUtil.e(hiBloodSugarMetaData));
        }
    }

    private void d(final HiHealthData hiHealthData) {
        dei.c().e(hiHealthData, new IBaseResponseCallback() { // from class: dfo
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                BloodSugarHistoryActivity.this.e(hiHealthData, i, obj);
            }
        });
    }

    public /* synthetic */ void e(HiHealthData hiHealthData, int i, Object obj) {
        if (i != 0) {
            LogUtil.h("BloodSugarHistoryActivity", "Failed to getBloodSugarDataOrigin, errorCode=", Integer.valueOf(i));
        } else {
            c(hiHealthData, String.valueOf(obj));
        }
    }

    private void c(HiHealthData hiHealthData, String str) {
        dei.c().e(hiHealthData, str, new IBaseResponseCallback() { // from class: dfw
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                BloodSugarHistoryActivity.this.d(i, obj);
            }
        });
    }

    public /* synthetic */ void d(int i, Object obj) {
        b(i);
    }

    private void b(int i) {
        if (i != 0) {
            LogUtil.h("BloodSugarHistoryActivity", "Failed to insertBloodSugarData, errorCode=", Integer.valueOf(i));
        } else {
            LogUtil.a("BloodSugarHistoryActivity", "Succeeded to insertBloodSugarData");
        }
        CountDownLatch countDownLatch = this.b;
        if (countDownLatch != null) {
            countDownLatch.countDown();
            if (this.b.getCount() == 0) {
                dks.Wz_(this.i, 2, null);
                this.b = null;
                return;
            }
            return;
        }
        dks.Wz_(this.i, 1, null);
    }

    public void j() {
        e(4);
        e(5);
    }

    public void i() {
        e(3);
        e(5);
    }

    public void f() {
        e(0);
    }

    public void h() {
        e(1);
    }

    public void o() {
        e(2);
    }

    private int a(List<List<HiHealthData>> list) {
        int i = 0;
        if (list == null) {
            return 0;
        }
        Iterator<List<HiHealthData>> it = list.iterator();
        while (it.hasNext()) {
            i += it.next().size();
        }
        return i;
    }

    public int d() {
        return this.e;
    }

    public List<List<HiHealthData>> e() {
        return this.f2318a;
    }

    public void a(int i, int i2) {
        this.j = i;
        this.d = i2;
        f();
    }

    public HiHealthData g() {
        if (!koq.d(this.f2318a, this.j)) {
            return null;
        }
        List<HiHealthData> list = this.f2318a.get(this.j);
        if (!koq.d(list, this.d)) {
            return null;
        }
        HiHealthData remove = list.remove(this.d);
        if (list.isEmpty()) {
            this.f2318a.remove(this.j);
        }
        this.e--;
        return remove;
    }

    public HiHealthData c() {
        if (koq.d(this.f2318a, this.j) && koq.d(this.f2318a.get(this.j), this.d)) {
            return this.f2318a.get(this.j).get(this.d);
        }
        return null;
    }

    public void b(DataConfirmRefreshListener dataConfirmRefreshListener) {
        if (this.c == null) {
            this.c = new ArrayList();
        }
        this.c.add(dataConfirmRefreshListener);
    }

    private void m() {
        Iterator<DataConfirmRefreshListener> it = this.c.iterator();
        while (it.hasNext()) {
            it.next().refresh();
        }
    }

    static class d extends BaseHandler<BloodSugarHistoryActivity> {
        d(BloodSugarHistoryActivity bloodSugarHistoryActivity) {
            super(bloodSugarHistoryActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: Uh_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(BloodSugarHistoryActivity bloodSugarHistoryActivity, Message message) {
            if (message.what == 1) {
                bloodSugarHistoryActivity.h();
            } else if (message.what == 2) {
                bloodSugarHistoryActivity.d(1);
            } else {
                LogUtil.h("BloodSugarHistoryActivity", "undefined");
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
