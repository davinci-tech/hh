package com.huawei.ui.main.stories.health.sleep;

import android.content.Context;
import androidx.core.util.Consumer;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.h5pro.core.H5ProWebView;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hihealth.HiHealthData;
import defpackage.bzs;
import defpackage.nhj;
import health.compact.a.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes9.dex */
public class SleepSummaryProvider extends BaseKnitDataProvider<HiHealthData> {

    /* renamed from: a, reason: collision with root package name */
    private H5ProWebView f10231a;
    private final e b = new e(this);
    private final List<H5ProWebView> e = new ArrayList();

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "R_SleepSummaryProvider_" + getGroupId();
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return nhj.n();
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        super.loadDefaultData(sectionBean);
        sectionBean.e(new HiHealthData());
        ReleaseLogUtil.b(getLogTag(), "loadDefaultData");
        ObserverManagerUtil.d(this.b, "SLEEP_SUMMARY_SYNC_STATUS");
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, HiHealthData hiHealthData) {
        super.parseParams(context, hashMap, hiHealthData);
        if (hashMap == null) {
            ReleaseLogUtil.a(getLogTag(), "parseParams outParams is null");
            return;
        }
        ReleaseLogUtil.b(getLogTag(), "parseParams");
        hashMap.put("PAGE_TYPE", 1073);
        hashMap.put("PAGE_ID", "Sleep_0001");
        hashMap.put("CALL_BACK", new c(this));
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        super.onDestroy();
        ReleaseLogUtil.b(getLogTag(), "onDestroy");
        ObserverManagerUtil.c(this.b);
        nhj.c(getLogTag(), this.f10231a, this.e);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(H5ProWebView h5ProWebView) {
        if (h5ProWebView == null) {
            ReleaseLogUtil.a(getLogTag(), "loadEmbeddedH5 h5ProWebView is null");
            return;
        }
        bzs.e().loadEmbeddedH5(h5ProWebView, "com.huawei.health.h5.health-trend", new H5ProLaunchOption.Builder().addPath("#/highLightCardView?domain=sleep"));
        ReleaseLogUtil.b(getLogTag(), "loadEmbeddedH5");
        this.e.add(h5ProWebView);
        this.f10231a = h5ProWebView;
    }

    static class c implements Consumer<H5ProWebView> {
        private final WeakReference<SleepSummaryProvider> b;

        c(SleepSummaryProvider sleepSummaryProvider) {
            this.b = new WeakReference<>(sleepSummaryProvider);
        }

        @Override // androidx.core.util.Consumer
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void accept(H5ProWebView h5ProWebView) {
            SleepSummaryProvider sleepSummaryProvider = this.b.get();
            if (sleepSummaryProvider != null) {
                sleepSummaryProvider.a(h5ProWebView);
            } else {
                ReleaseLogUtil.a("R_SleepSummaryProvider", "InnerConsumer accept provider is null");
            }
        }
    }

    static class e implements Observer {
        private final WeakReference<SleepSummaryProvider> c;

        e(SleepSummaryProvider sleepSummaryProvider) {
            this.c = new WeakReference<>(sleepSummaryProvider);
        }

        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            SleepSummaryProvider sleepSummaryProvider = this.c.get();
            if (sleepSummaryProvider == null || objArr == null) {
                ReleaseLogUtil.a("R_SleepSummaryProvider", "InnerObserver notify provider ", sleepSummaryProvider, " objects ", objArr, " label ", str);
                return;
            }
            if (objArr.length == 0) {
                ReleaseLogUtil.a(sleepSummaryProvider.getLogTag(), "InnerObserver notify length is 0");
                return;
            }
            Object obj = objArr[0];
            if (!(obj instanceof Boolean)) {
                ReleaseLogUtil.a(sleepSummaryProvider.getLogTag(), "InnerObserver notify object ", obj);
                return;
            }
            boolean booleanValue = ((Boolean) obj).booleanValue();
            ReleaseLogUtil.b(sleepSummaryProvider.getLogTag(), "InnerObserver notify isSyncSuccess ", Boolean.valueOf(booleanValue));
            if (booleanValue) {
                nhj.b(sleepSummaryProvider.getLogTag(), sleepSummaryProvider.f10231a);
            }
        }
    }
}
