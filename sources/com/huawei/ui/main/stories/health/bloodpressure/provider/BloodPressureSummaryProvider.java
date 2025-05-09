package com.huawei.ui.main.stories.health.bloodpressure.provider;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.core.util.Consumer;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.h5pro.core.H5ProWebView;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import defpackage.bzs;
import defpackage.nhj;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.ReleaseLogUtil;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes9.dex */
public class BloodPressureSummaryProvider extends BaseKnitDataProvider<HiHealthData> {
    private H5ProWebView b;
    private BroadcastReceiver d;

    /* renamed from: a, reason: collision with root package name */
    private final List<H5ProWebView> f10162a = new ArrayList();
    private DataInfos e = DataInfos.NoDataPlaceHolder;
    private boolean c = true;

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "R_BloodPressureSummaryProvider_" + getGroupId();
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return nhj.n();
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        super.loadDefaultData(sectionBean);
        Bundle extra = getExtra();
        Serializable serializable = extra != null ? extra.getSerializable("key_bundle_health_line_chart_data_infos") : null;
        this.e = serializable instanceof DataInfos ? (DataInfos) serializable : DataInfos.NoDataPlaceHolder;
        sectionBean.e(new HiHealthData());
        ReleaseLogUtil.b(getLogTag(), "loadDefaultData");
        nhj.o();
        d(nhj.b());
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, HiHealthData hiHealthData) {
        super.parseParams(context, hashMap, hiHealthData);
        if (hashMap == null) {
            ReleaseLogUtil.a(getLogTag(), "parseParams outParams is null");
            return;
        }
        ReleaseLogUtil.b(getLogTag(), "parseParams");
        hashMap.put("PAGE_TYPE", 1077);
        hashMap.put("PAGE_ID", "BloodPressure_0001");
        hashMap.put("CALL_BACK", new e(this));
        if ((context instanceof Activity) && this.e == DataInfos.BloodPressureDayDetail && this.c) {
            this.c = false;
            boolean booleanExtra = ((Activity) context).getIntent().getBooleanExtra("is_scroll_to_summary", false);
            LogUtil.a("R_BloodPressureSummaryProvider", "isCardShowSummary = ", Boolean.valueOf(booleanExtra));
            hashMap.put("is_scroll_to_summary", Boolean.valueOf(booleanExtra));
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        super.onDestroy();
        ReleaseLogUtil.b(getLogTag(), "onDestroy");
        if (this.d != null) {
            BroadcastManagerUtil.bFK_(BaseApplication.getContext(), this.d);
            this.d = null;
        }
        nhj.c(getLogTag(), this.b, this.f10162a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(H5ProWebView h5ProWebView) {
        if (h5ProWebView == null) {
            ReleaseLogUtil.a(getLogTag(), "loadEmbeddedH5 h5ProWebView is null");
            return;
        }
        bzs.e().loadEmbeddedH5(h5ProWebView, "com.huawei.health.h5.health-trend", new H5ProLaunchOption.Builder().addPath("#/highLightCardView?domain=bloodPressure"));
        ReleaseLogUtil.b(getLogTag(), "loadEmbeddedH5");
        this.f10162a.add(h5ProWebView);
        this.b = h5ProWebView;
    }

    private void d(final List<Integer> list) {
        if (this.d == null) {
            this.d = new BroadcastReceiver() { // from class: com.huawei.ui.main.stories.health.bloodpressure.provider.BloodPressureSummaryProvider.5
                @Override // android.content.BroadcastReceiver
                public void onReceive(Context context, Intent intent) {
                    if (intent == null) {
                        ReleaseLogUtil.a("R_BloodPressureSummaryProvider", "registerSyncStatusBroadcastReceiver onReceive intent is null");
                        return;
                    }
                    String action = intent.getAction();
                    if (!"com.huawei.hihealth.action_sync_data_result".equals(action)) {
                        ReleaseLogUtil.a("R_BloodPressureSummaryProvider", "registerSyncStatusBroadcastReceiver onReceive action ", action);
                        return;
                    }
                    boolean booleanExtra = intent.getBooleanExtra("sync_data_result_success", true);
                    String stringExtra = intent.getStringExtra("sync_data_result_type");
                    boolean e2 = nhj.e((List<Integer>) list, stringExtra);
                    ReleaseLogUtil.b("R_BloodPressureSummaryProvider", "registerSyncStatusBroadcastReceiver onReceive isSyncBloodPressureSummaryType ", Boolean.valueOf(e2), " syncDataResultType ", stringExtra, "syncDataTypeList ", list, " isSyncSuccess ", Boolean.valueOf(booleanExtra));
                    if (e2) {
                        nhj.b(BloodPressureSummaryProvider.this.getLogTag(), BloodPressureSummaryProvider.this.b);
                    }
                }
            };
            BroadcastManagerUtil.bFE_(BaseApplication.getContext(), this.d, new IntentFilter("com.huawei.hihealth.action_sync_data_result"));
        }
    }

    static class e implements Consumer<H5ProWebView> {
        private final WeakReference<BloodPressureSummaryProvider> e;

        e(BloodPressureSummaryProvider bloodPressureSummaryProvider) {
            this.e = new WeakReference<>(bloodPressureSummaryProvider);
        }

        @Override // androidx.core.util.Consumer
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void accept(H5ProWebView h5ProWebView) {
            BloodPressureSummaryProvider bloodPressureSummaryProvider = this.e.get();
            if (bloodPressureSummaryProvider != null) {
                bloodPressureSummaryProvider.a(h5ProWebView);
            } else {
                ReleaseLogUtil.a("R_BloodPressureSummaryProvider", "InnerConsumer accept provider is null");
            }
        }
    }
}
