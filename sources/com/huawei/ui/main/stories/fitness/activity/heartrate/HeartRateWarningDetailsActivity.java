package com.huawei.ui.main.stories.fitness.activity.heartrate;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.format.DateUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$color;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.heartrate.adapter.WarningDetailAdapter;
import com.huawei.ui.main.stories.fitness.activity.heartrate.helper.HeartRateDetailData;
import com.huawei.ui.main.stories.fitness.activity.heartrate.helper.WarningListData;
import defpackage.gnm;
import defpackage.koq;
import defpackage.nsj;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Iterator;

/* loaded from: classes6.dex */
public class HeartRateWarningDetailsActivity extends BaseActivity {
    private ArrayList<HeartRateDetailData> e = new ArrayList<>(16);
    private ArrayList<WarningListData> c = new ArrayList<>(16);

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_heart_rate_warning_details);
        getWindow().setBackgroundDrawableResource(R$color.colorSubBackground);
        b();
        WarningDetailAdapter warningDetailAdapter = new WarningDetailAdapter(this.c);
        HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.heart_rate_alert_recycler);
        healthRecycleView.setLayoutManager(new LinearLayoutManager(this));
        healthRecycleView.setAdapter(warningDetailAdapter);
    }

    private void b() {
        Intent intent = getIntent();
        if (intent != null) {
            try {
                this.e.clear();
                this.e = intent.getParcelableArrayListExtra("alert_list_data");
            } catch (ArrayIndexOutOfBoundsException unused) {
                LogUtil.b("HeartRateWarningDetailsActivity", "getPassedData ArrayIndexOutOfBoundsException");
            }
        }
        this.c.clear();
        if (koq.b(this.e)) {
            LogUtil.h("HeartRateWarningDetailsActivity", "getPassedData mHeartRateDetailList is empty");
            return;
        }
        Iterator<HeartRateDetailData> it = this.e.iterator();
        while (it.hasNext()) {
            HeartRateDetailData next = it.next();
            WarningListData warningListData = new WarningListData();
            Object[] objArr = new Object[1];
            StringBuilder sb = new StringBuilder("detailData : ");
            sb.append(next == null);
            objArr[0] = sb.toString();
            LogUtil.a("HeartRateWarningDetailsActivity", objArr);
            if (next != null) {
                warningListData.setDate(DateUtils.formatDateTime(BaseApplication.getContext(), next.getTime(), 131092));
                warningListData.setTime(nsj.e(BaseApplication.getContext(), next.getTime(), next.getTime(), 1));
                warningListData.setTitle(UnitUtil.e(next.getValue(), 1, 0) + BaseApplication.getContext().getResources().getString(R$string.IDS_main_watch_heart_rate_unit_string));
                this.c.add(warningListData);
            }
        }
    }

    public static void a(Context context, ArrayList<HeartRateDetailData> arrayList) {
        if (context == null || arrayList == null) {
            return;
        }
        Intent intent = new Intent(context, (Class<?>) HeartRateWarningDetailsActivity.class);
        intent.addFlags(268435456);
        intent.putParcelableArrayListExtra("alert_list_data", arrayList);
        gnm.aPB_(context, intent);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
