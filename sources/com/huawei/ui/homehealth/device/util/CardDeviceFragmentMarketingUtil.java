package com.huawei.ui.homehealth.device.util;

import android.content.Context;
import android.os.Message;
import android.text.TextUtils;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceContentBase;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.marketing.datatype.templates.GridTemplate;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homehealth.device.callback.IMarketingCallback;
import com.huawei.ui.homehealth.device.util.CardDeviceFragmentMarketingUtil;
import defpackage.jdx;
import defpackage.koq;
import defpackage.nrv;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class CardDeviceFragmentMarketingUtil {

    /* renamed from: a, reason: collision with root package name */
    private ResourceBriefInfo f9410a;
    private IMarketingCallback c;
    private boolean e = false;
    private final c b = new c(this);

    public CardDeviceFragmentMarketingUtil(Context context) {
    }

    public void b(IMarketingCallback iMarketingCallback) {
        this.c = iMarketingCallback;
        ResourceBriefInfo e = e();
        this.f9410a = e;
        if (e != null) {
            d(e, true);
        }
        final MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        Task<Map<Integer, ResourceResultInfo>> resourceResultInfo = marketingApi.getResourceResultInfo(4050);
        resourceResultInfo.addOnSuccessListener(new OnSuccessListener() { // from class: ogl
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                CardDeviceFragmentMarketingUtil.this.c(marketingApi, (Map) obj);
            }
        });
        resourceResultInfo.addOnFailureListener(new OnFailureListener() { // from class: ogk
            @Override // com.huawei.hmf.tasks.OnFailureListener
            public final void onFailure(Exception exc) {
                LogUtil.b("CardDeviceFragmentMarketingUtil", "requestMarketResource onFailure");
            }
        });
    }

    public /* synthetic */ void c(final MarketingApi marketingApi, final Map map) {
        LogUtil.a("CardDeviceFragmentMarketingUtil", "requestMarketResource onSuccess");
        if (map == null || map.isEmpty()) {
            LogUtil.b("CardDeviceFragmentMarketingUtil", "marketingResponse is invalid");
            this.b.sendEmptyMessage(100);
        } else {
            jdx.b(new Runnable() { // from class: com.huawei.ui.homehealth.device.util.CardDeviceFragmentMarketingUtil.5
                @Override // java.lang.Runnable
                public void run() {
                    final Map<Integer, ResourceResultInfo> filterMarketingRules = marketingApi.filterMarketingRules(map);
                    HandlerExecutor.e(new Runnable() { // from class: com.huawei.ui.homehealth.device.util.CardDeviceFragmentMarketingUtil.5.4
                        @Override // java.lang.Runnable
                        public void run() {
                            Map map2 = filterMarketingRules;
                            if (map2 == null || map2.isEmpty()) {
                                LogUtil.b("CardDeviceFragmentMarketingUtil", "filterResultInfoMap is invalid");
                                CardDeviceFragmentMarketingUtil.this.b.sendEmptyMessage(100);
                                return;
                            }
                            ResourceResultInfo resourceResultInfo = (ResourceResultInfo) filterMarketingRules.get(4050);
                            if (resourceResultInfo != null) {
                                Message obtainMessage = CardDeviceFragmentMarketingUtil.this.b.obtainMessage(200);
                                obtainMessage.obj = resourceResultInfo;
                                CardDeviceFragmentMarketingUtil.this.b.sendMessage(obtainMessage);
                            } else {
                                LogUtil.b("CardDeviceFragmentMarketingUtil", "resourceResultInfo is invalid");
                                CardDeviceFragmentMarketingUtil.this.b.sendEmptyMessage(100);
                            }
                        }
                    });
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Object obj) {
        List<ResourceBriefInfo> resources = obj instanceof ResourceResultInfo ? ((ResourceResultInfo) obj).getResources() : null;
        List<ResourceBriefInfo> a2 = resources != null ? a(resources) : null;
        if (koq.b(a2)) {
            LogUtil.a("CardDeviceFragmentMarketingUtil", "cardResource is null");
            d();
            return;
        }
        ResourceBriefInfo resourceBriefInfo = a2.get(0);
        LogUtil.a("CardDeviceFragmentMarketingUtil", "briefInfo: ", resourceBriefInfo);
        d(resourceBriefInfo);
        if (this.e && resourceBriefInfo != null && this.f9410a != null && TextUtils.equals(resourceBriefInfo.toString(), this.f9410a.toString())) {
            LogUtil.a("CardDeviceFragmentMarketingUtil", "resource is same with cache");
        } else {
            d(resourceBriefInfo, false);
        }
    }

    private void d(ResourceBriefInfo resourceBriefInfo, boolean z) {
        ResourceContentBase content = resourceBriefInfo != null ? resourceBriefInfo.getContent() : null;
        String content2 = content != null ? content.getContent() : null;
        if (TextUtils.isEmpty(content2)) {
            LogUtil.a("CardDeviceFragmentMarketingUtil", "content is null");
            d();
            return;
        }
        GridTemplate gridTemplate = (GridTemplate) nrv.b(content2, GridTemplate.class);
        if (gridTemplate == null) {
            LogUtil.a("CardDeviceFragmentMarketingUtil", "gridTemplate is null");
            d();
        } else if (this.c != null) {
            if (z) {
                this.e = true;
                LogUtil.a("CardDeviceFragmentMarketingUtil", "return cached resource");
            }
            this.c.onSuccess(gridTemplate, resourceBriefInfo);
        }
    }

    private List<ResourceBriefInfo> a(List<ResourceBriefInfo> list) {
        ArrayList arrayList = new ArrayList();
        for (ResourceBriefInfo resourceBriefInfo : list) {
            if (resourceBriefInfo != null && resourceBriefInfo.getContentType() == 36 && a(resourceBriefInfo)) {
                arrayList.add(resourceBriefInfo);
            }
        }
        return arrayList;
    }

    private boolean a(ResourceBriefInfo resourceBriefInfo) {
        long effectiveTime = resourceBriefInfo.getEffectiveTime();
        long expirationTime = resourceBriefInfo.getExpirationTime();
        if (effectiveTime != 0 && expirationTime != 0) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis <= effectiveTime || currentTimeMillis >= expirationTime) {
                return false;
            }
        }
        return true;
    }

    private void d(ResourceBriefInfo resourceBriefInfo) {
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "CACHE_KEY_DEVICE_FUNCTION_MARKETING", nrv.a(resourceBriefInfo), (StorageParams) null);
    }

    private ResourceBriefInfo e() {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), String.valueOf(10000), "CACHE_KEY_DEVICE_FUNCTION_MARKETING");
        if (TextUtils.isEmpty(b)) {
            return null;
        }
        try {
            return (ResourceBriefInfo) nrv.b(b, ResourceBriefInfo.class);
        } catch (JsonSyntaxException unused) {
            LogUtil.b("CardDeviceFragmentMarketingUtil", "getResourceCache json syntax exception: ", b);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "CACHE_KEY_DEVICE_FUNCTION_MARKETING", (String) null, (StorageParams) null);
    }

    static class c extends BaseHandler<CardDeviceFragmentMarketingUtil> {
        private WeakReference<CardDeviceFragmentMarketingUtil> b;

        public c(CardDeviceFragmentMarketingUtil cardDeviceFragmentMarketingUtil) {
            super(cardDeviceFragmentMarketingUtil);
            this.b = new WeakReference<>(cardDeviceFragmentMarketingUtil);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: cZx_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(CardDeviceFragmentMarketingUtil cardDeviceFragmentMarketingUtil, Message message) {
            if (message == null || cardDeviceFragmentMarketingUtil == null) {
                return;
            }
            CardDeviceFragmentMarketingUtil cardDeviceFragmentMarketingUtil2 = this.b.get();
            int i = message.what;
            if (i != 100) {
                if (i == 200 && cardDeviceFragmentMarketingUtil2 != null) {
                    cardDeviceFragmentMarketingUtil2.d(message.obj);
                    return;
                }
                return;
            }
            LogUtil.a("CardDeviceFragmentMarketingUtil", "data is invalid");
            if (cardDeviceFragmentMarketingUtil2 != null) {
                cardDeviceFragmentMarketingUtil2.d();
            }
        }
    }
}
