package com.huawei.ui.main.stories.fitness.activity.active.provider;

import android.content.Context;
import android.os.Bundle;
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
public class ActiveRecordSummaryProvider extends BaseKnitDataProvider<HiHealthData> {
    private H5ProWebView c;
    private int e;

    /* renamed from: a, reason: collision with root package name */
    private final d f9729a = new d(this);
    private final List<H5ProWebView> d = new ArrayList();

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "R_ActiveRecordSummaryProvider_" + getGroupId();
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return nhj.n();
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        super.loadDefaultData(sectionBean);
        Bundle extra = getExtra();
        if (extra == null) {
            ReleaseLogUtil.b(getLogTag(), "loadDefaultData extra is null");
        } else {
            this.e = extra.getInt("card_id");
        }
        sectionBean.e(new HiHealthData());
        ObserverManagerUtil.d(this.f9729a, "ACTIVE_RECORD_SUMMARY_SYNC_STATUS");
        ReleaseLogUtil.b(getLogTag(), "loadDefaultData mCardId ", Integer.valueOf(this.e));
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
        hashMap.put("PAGE_TYPE", 1075);
        hashMap.put("PAGE_ID", "Rings_0001");
        hashMap.put("CALL_BACK", new b(this));
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        super.onDestroy();
        ReleaseLogUtil.b(getLogTag(), "onDestroy");
        ObserverManagerUtil.c(this.f9729a);
        nhj.c(getLogTag(), this.c, this.d);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(H5ProWebView h5ProWebView) {
        String str;
        if (h5ProWebView == null) {
            ReleaseLogUtil.a(getLogTag(), "loadEmbeddedH5 h5ProWebView is null");
            return;
        }
        if (this.e > 0) {
            str = "#/highLightCardView?domain=activityRings&bgColor=grayBlack&cardId=" + this.e;
        } else {
            str = "#/highLightCardView?domain=activityRings&bgColor=grayBlack";
        }
        bzs.e().loadEmbeddedH5(h5ProWebView, "com.huawei.health.h5.health-trend", new H5ProLaunchOption.Builder().addPath(str));
        ReleaseLogUtil.b(getLogTag(), "loadEmbeddedH5 mCardId ", Integer.valueOf(this.e));
        this.d.add(h5ProWebView);
        this.c = h5ProWebView;
    }

    static class b implements Consumer<H5ProWebView> {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<ActiveRecordSummaryProvider> f9730a;

        b(ActiveRecordSummaryProvider activeRecordSummaryProvider) {
            this.f9730a = new WeakReference<>(activeRecordSummaryProvider);
        }

        @Override // androidx.core.util.Consumer
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void accept(H5ProWebView h5ProWebView) {
            ActiveRecordSummaryProvider activeRecordSummaryProvider = this.f9730a.get();
            if (activeRecordSummaryProvider != null) {
                activeRecordSummaryProvider.a(h5ProWebView);
            } else {
                ReleaseLogUtil.a("R_ActiveRecordSummaryProvider", "InnerConsumer accept provider is null");
            }
        }
    }

    static class d implements Observer {
        private final WeakReference<ActiveRecordSummaryProvider> b;

        d(ActiveRecordSummaryProvider activeRecordSummaryProvider) {
            this.b = new WeakReference<>(activeRecordSummaryProvider);
        }

        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            ActiveRecordSummaryProvider activeRecordSummaryProvider = this.b.get();
            if (activeRecordSummaryProvider == null || objArr == null) {
                ReleaseLogUtil.a("R_ActiveRecordSummaryProvider", "InnerObserver notify provider ", activeRecordSummaryProvider, " objects ", objArr, " label ", str);
                return;
            }
            if (objArr.length == 0) {
                ReleaseLogUtil.a(activeRecordSummaryProvider.getLogTag(), "InnerObserver notify length is 0");
                return;
            }
            Object obj = objArr[0];
            if (!(obj instanceof Boolean)) {
                ReleaseLogUtil.a(activeRecordSummaryProvider.getLogTag(), "InnerObserver notify object ", obj);
                return;
            }
            boolean booleanValue = ((Boolean) obj).booleanValue();
            ReleaseLogUtil.b(activeRecordSummaryProvider.getLogTag(), "InnerObserver notify isSyncSuccess ", Boolean.valueOf(booleanValue));
            if (booleanValue) {
                nhj.b(activeRecordSummaryProvider.getLogTag(), activeRecordSummaryProvider.c);
            }
        }
    }
}
