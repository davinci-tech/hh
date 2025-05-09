package com.huawei.ui.main.stories.fitness.activity.heartrate;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.heartrate.BradycardiaAlarmActivity;
import com.huawei.ui.main.stories.fitness.activity.heartrate.adapter.HeartRateAbnormalAdapter;
import defpackage.gnm;
import defpackage.psc;
import java.util.List;

/* loaded from: classes6.dex */
public class BradycardiaAlarmActivity extends BaseActivity {
    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_warning_heart_rate);
        d();
    }

    private void d() {
        getWindow().setBackgroundDrawableResource(R$color.colorSubBackground);
        ((CustomTitleBar) findViewById(R.id.fitness_detail_titlebar)).setTitleText(getResources().getString(R$string.IDS_heartrate_bradycardia_alarm));
        HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.heart_rate_recycler);
        healthRecycleView.setLayoutManager(new HealthLinearLayoutManager(this));
        final HeartRateAbnormalAdapter heartRateAbnormalAdapter = new HeartRateAbnormalAdapter();
        healthRecycleView.setAdapter(heartRateAbnormalAdapter);
        ThreadPoolManager.d().execute(new Runnable() { // from class: pqv
            @Override // java.lang.Runnable
            public final void run() {
                BradycardiaAlarmActivity.this.b(heartRateAbnormalAdapter);
            }
        });
    }

    public /* synthetic */ void b(final HeartRateAbnormalAdapter heartRateAbnormalAdapter) {
        psc.a("BRADYCARDIA_DETAIL", 2102, new ResponseCallback() { // from class: pqs
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                BradycardiaAlarmActivity.this.c(heartRateAbnormalAdapter, i, (List) obj);
            }
        });
    }

    public /* synthetic */ void c(final HeartRateAbnormalAdapter heartRateAbnormalAdapter, int i, final List list) {
        if (list == null || list.size() == 0) {
            LogUtil.h("HealthHeartRate_BradycardiaAlarmActivity", "queryModelUnitByDetail data is empty");
        } else {
            runOnUiThread(new Runnable() { // from class: pqt
                @Override // java.lang.Runnable
                public final void run() {
                    BradycardiaAlarmActivity.d(HeartRateAbnormalAdapter.this, list);
                }
            });
        }
    }

    public static /* synthetic */ void d(HeartRateAbnormalAdapter heartRateAbnormalAdapter, List list) {
        heartRateAbnormalAdapter.e(list);
        if (heartRateAbnormalAdapter.b()) {
            heartRateAbnormalAdapter.b(0);
        }
    }

    public static void b(Context context) {
        if (context == null) {
            return;
        }
        Intent intent = new Intent(context, (Class<?>) BradycardiaAlarmActivity.class);
        intent.addFlags(268435456);
        gnm.aPB_(context, intent);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
