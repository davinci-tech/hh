package com.huawei.ui.main.stories.discover.provider;

import android.content.Context;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.marketing.datatype.QueryRecommendResources;
import com.huawei.health.marketing.datatype.RecommendCardBean;
import com.huawei.health.marketing.datatype.RecommendResourceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.eip;
import defpackage.jdx;
import defpackage.koq;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes9.dex */
public class RecommendCardMajorProvider extends BaseKnitDataProvider<Hashtable<Integer, RecommendCardBean>> {
    private MemberCardListener d;
    private MemberCardManager e;
    private SectionBean h;
    private List<RecommendResourceInfo> c = new CopyOnWriteArrayList();

    /* renamed from: a, reason: collision with root package name */
    private List<String> f9710a = new ArrayList<String>() { // from class: com.huawei.ui.main.stories.discover.provider.RecommendCardMajorProvider.5
        {
            add("vipPageFitness");
            add("vipPageBMI");
            add("vipPageSleep");
        }
    };
    private Hashtable<Integer, RecommendCardBean> b = new Hashtable<>();

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public /* synthetic */ void parseParams(Context context, HashMap hashMap, Object obj) {
        b(context, (HashMap<String, Object>) hashMap, (Hashtable<Integer, RecommendCardBean>) obj);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        LogUtil.a("RecommendCardMajorProvider", "loadDefaultData");
        this.h = sectionBean;
        super.loadDefaultData(sectionBean);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(final Context context, final SectionBean sectionBean) {
        LogUtil.a("RecommendCardMajorProvider", "loadData");
        if (HandlerExecutor.c()) {
            jdx.b(new Runnable() { // from class: com.huawei.ui.main.stories.discover.provider.RecommendCardMajorProvider.2
                @Override // java.lang.Runnable
                public void run() {
                    RecommendCardMajorProvider.this.loadData(context, sectionBean);
                }
            });
            return;
        }
        if (this.e == null) {
            this.e = new MemberCardManager(context);
        }
        this.e.b();
        this.h = sectionBean;
        d(context, this.f9710a);
    }

    public void b(Context context, HashMap<String, Object> hashMap, Hashtable<Integer, RecommendCardBean> hashtable) {
        LogUtil.a("RecommendCardMajorProvider", "parseParams");
        hashMap.put("TOP_CARD_BEAN", hashtable);
    }

    private void d(final Context context, List<String> list) {
        new eip().d(list, 1, new IBaseResponseCallback() { // from class: com.huawei.ui.main.stories.discover.provider.RecommendCardMajorProvider.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                List b = RecommendCardMajorProvider.this.b(obj);
                if (RecommendCardMajorProvider.this.c != null && RecommendCardMajorProvider.this.c.isEmpty() && !koq.b(b)) {
                    RecommendCardMajorProvider.this.c.clear();
                    RecommendCardMajorProvider.this.c.addAll(b);
                }
                RecommendCardMajorProvider recommendCardMajorProvider = RecommendCardMajorProvider.this;
                recommendCardMajorProvider.c(context, (List<RecommendResourceInfo>) recommendCardMajorProvider.c);
                LogUtil.a("RecommendCardMajorProvider", "mResourceInfoList: ", RecommendCardMajorProvider.this.c);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(Context context, List<RecommendResourceInfo> list) {
        LogUtil.a("RecommendCardMajorProvider", "sendRecommendResource");
        c cVar = new c(this);
        this.d = cVar;
        this.e.b(this.c, cVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<RecommendResourceInfo> b(Object obj) {
        ArrayList arrayList = new ArrayList();
        if (!(obj instanceof QueryRecommendResources)) {
            LogUtil.a("RecommendCardMajorProvider", "objData not instanceof QueryRecommendResources");
            return arrayList;
        }
        QueryRecommendResources queryRecommendResources = (QueryRecommendResources) obj;
        if (queryRecommendResources.getTotalNum() == 0) {
            LogUtil.a("RecommendCardMajorProvider", "resources is null");
            return arrayList;
        }
        return queryRecommendResources.getRecommendResourceInfos();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, RecommendCardBean recommendCardBean) {
        if (this.b == null || this.h == null) {
            LogUtil.b("RecommendCardMajorProvider", "mRecommendCardBeansMap or mSectionBean is null");
            return;
        }
        LogUtil.a("RecommendCardMajorProvider", "index = ", Integer.valueOf(i), " recommendCardBean = ", recommendCardBean);
        this.b.put(Integer.valueOf(i), recommendCardBean);
        this.h.e(this.b);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        super.onDestroy();
        MemberCardManager memberCardManager = this.e;
        if (memberCardManager != null) {
            memberCardManager.c();
        }
    }

    static class c implements MemberCardListener {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<RecommendCardMajorProvider> f9712a;

        c(RecommendCardMajorProvider recommendCardMajorProvider) {
            this.f9712a = new WeakReference<>(recommendCardMajorProvider);
        }

        @Override // com.huawei.ui.main.stories.discover.provider.MemberCardListener
        public void onResponse(final int i, final RecommendCardBean recommendCardBean) {
            WeakReference<RecommendCardMajorProvider> weakReference = this.f9712a;
            if (weakReference == null) {
                LogUtil.a("RecommendCardMajorProvider", "onResponse mCardProviderReference == null");
                return;
            }
            final RecommendCardMajorProvider recommendCardMajorProvider = weakReference.get();
            if (recommendCardMajorProvider == null) {
                LogUtil.a("RecommendCardMajorProvider", "onResponse mCardProvider == null");
            } else {
                HandlerExecutor.e(new Runnable() { // from class: com.huawei.ui.main.stories.discover.provider.RecommendCardMajorProvider.c.2
                    @Override // java.lang.Runnable
                    public void run() {
                        recommendCardMajorProvider.c(i, recommendCardBean);
                    }
                });
            }
        }
    }
}
