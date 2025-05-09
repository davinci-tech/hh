package com.huawei.ui.main.stories.fitness.activity.bloodoxygen.detailactivity;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.linechart.utils.ResponseCallback;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.bloodoxygen.adapter.BloodOxygenHistoryRecordAdapter;
import com.huawei.ui.main.stories.fitness.activity.bloodoxygen.detailactivity.BloodOxygenHistoryRecordActivity;
import defpackage.nsf;
import defpackage.nsj;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class BloodOxygenHistoryRecordActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private LinearLayout f9755a;
    private ExpandableListView b;
    private HealthProgressBar c;
    private Context d;
    private CustomTitleBar e;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_blood_oxygen_history_layout);
        this.d = this;
        b();
        c();
        e();
    }

    private void b() {
        this.f9755a = (LinearLayout) findViewById(R.id.blood_oxygen_history_data_loading);
        HealthProgressBar healthProgressBar = (HealthProgressBar) findViewById(R.id.blood_oxygen_history_data_loading_img);
        this.c = healthProgressBar;
        healthProgressBar.setLayerType(1, null);
        this.e = (CustomTitleBar) findViewById(R.id.blood_oxygen_history_data_title);
        this.b = (ExpandableListView) findViewById(R.id.blood_oxygen_history_data_layout);
        this.f9755a.setVisibility(0);
        this.e.setTitleText(getString(R$string.IDS_hw_health_blood_oxygen_lower_spo2));
        if (!LanguageUtil.bc(this.d)) {
            this.e.setLeftButtonDrawable(getResources().getDrawable(R.drawable._2131428438_res_0x7f0b0456), nsf.h(R$string.accessibility_go_back));
        } else {
            this.e.setLeftButtonDrawable(getResources().getDrawable(R.mipmap._2131820922_res_0x7f11017a), nsf.h(R$string.accessibility_go_back));
        }
        cancelAdaptRingRegion();
    }

    private void c() {
        this.e.setLeftButtonOnClickListener(new View.OnClickListener() { // from class: pkg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BloodOxygenHistoryRecordActivity.this.dqx_(view);
            }
        });
    }

    public /* synthetic */ void dqx_(View view) {
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* renamed from: com.huawei.ui.main.stories.fitness.activity.bloodoxygen.detailactivity.BloodOxygenHistoryRecordActivity$3, reason: invalid class name */
    public class AnonymousClass3 implements ResponseCallback<List<HiHealthData>> {
        AnonymousClass3() {
        }

        @Override // com.huawei.ui.commonui.linechart.utils.ResponseCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onResult(int i, List<HiHealthData> list) {
            if (i == 0) {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                BloodOxygenHistoryRecordActivity.this.b(list, arrayList, arrayList2);
                final BloodOxygenHistoryRecordAdapter bloodOxygenHistoryRecordAdapter = new BloodOxygenHistoryRecordAdapter(BloodOxygenHistoryRecordActivity.this.d, arrayList, arrayList2);
                BloodOxygenHistoryRecordActivity.this.runOnUiThread(new Runnable() { // from class: pkk
                    @Override // java.lang.Runnable
                    public final void run() {
                        BloodOxygenHistoryRecordActivity.AnonymousClass3.this.d(bloodOxygenHistoryRecordAdapter);
                    }
                });
            }
        }

        public /* synthetic */ void d(BloodOxygenHistoryRecordAdapter bloodOxygenHistoryRecordAdapter) {
            BloodOxygenHistoryRecordActivity.this.b.setAdapter(bloodOxygenHistoryRecordAdapter);
            BloodOxygenHistoryRecordActivity.this.f9755a.setVisibility(8);
            if (bloodOxygenHistoryRecordAdapter.getGroupCount() > 0) {
                BloodOxygenHistoryRecordActivity.this.b.expandGroup(0);
            }
        }
    }

    private void e() {
        e(this.d, 0L, System.currentTimeMillis(), new AnonymousClass3());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<HiHealthData> list, List<String> list2, List<List<HiHealthData>> list3) {
        if (list == null) {
            LogUtil.h("BloodOxygenHistoryRecordActivity", "classifyDataByMonth healthDataList is null");
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            HiHealthData hiHealthData = list.get(i);
            if (hiHealthData == null) {
                LogUtil.h("BloodOxygenHistoryRecordActivity", "healthDataList hiHealthData.get(", Integer.valueOf(i), ") is null");
            } else {
                String c = nsj.c(this.d, hiHealthData.getEndTime(), 36);
                if (list2.contains(c)) {
                    list3.get(list2.indexOf(c)).add(hiHealthData);
                } else {
                    list2.add(c);
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(hiHealthData);
                    list3.add(arrayList);
                }
            }
        }
    }

    private void e(Context context, long j, long j2, final ResponseCallback<List<HiHealthData>> responseCallback) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(j);
        hiDataReadOption.setEndTime(j2);
        LogUtil.a("BloodOxygenHistoryRecordActivity", "startTime：", Long.valueOf(j), "endTime", Long.valueOf(j2));
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setType(new int[]{2107});
        HiHealthNativeApi.a(context).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: com.huawei.ui.main.stories.fitness.activity.bloodoxygen.detailactivity.BloodOxygenHistoryRecordActivity.4
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                List list;
                if (obj == null) {
                    LogUtil.h("BloodOxygenHistoryRecordActivity", "blood oxygen remind data is null");
                    responseCallback.onResult(-1, null);
                    return;
                }
                if (obj instanceof SparseArray) {
                    SparseArray sparseArray = (SparseArray) obj;
                    if (sparseArray.size() == 0) {
                        list = new ArrayList();
                    } else {
                        Object obj2 = sparseArray.get(2107);
                        list = BloodOxygenHistoryRecordActivity.this.e(obj2) ? (List) obj2 : null;
                        if (list == null) {
                            LogUtil.h("BloodOxygenHistoryRecordActivity", "bloodOxygenRemindRecordList is null");
                            responseCallback.onResult(-1, null);
                            return;
                        }
                    }
                    responseCallback.onResult(0, list);
                    return;
                }
                LogUtil.h("BloodOxygenHistoryRecordActivity", "data is not SparseArray<Object> type");
                responseCallback.onResult(-1, null);
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
                LogUtil.c("BloodOxygenHistoryRecordActivity", "requestDayOxygenData onResultIntent");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean e(Object obj) {
        if (!(obj instanceof List)) {
            return false;
        }
        Iterator it = ((List) obj).iterator();
        boolean z = true;
        while (it.hasNext()) {
            if (!(it.next() instanceof HiHealthData)) {
                z = false;
            }
        }
        return z;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
