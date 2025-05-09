package com.huawei.ui.homehealth.vmall;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.marketing.datatype.templates.H5GeneralTemplate;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homehealth.vmall.VmallOperation;
import defpackage.koq;
import defpackage.owx;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class VmallOperation {

    public interface VmallResourceCallback {
        void obtainVmallResource(owx owxVar);
    }

    public void a(final VmallResourceCallback vmallResourceCallback) {
        owx e = e();
        if (e != null) {
            ReleaseLogUtil.e("VmallOperation", "load  resource from cache");
            vmallResourceCallback.obtainVmallResource(e);
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: owv
            @Override // java.lang.Runnable
            public final void run() {
                VmallOperation.this.d(vmallResourceCallback);
            }
        });
    }

    public /* synthetic */ void d(VmallResourceCallback vmallResourceCallback) {
        d(4002, vmallResourceCallback);
    }

    private void d(final int i, final VmallResourceCallback vmallResourceCallback) {
        final MarketingApi marketingApi = (MarketingApi) Services.c("FeatureMarketing", MarketingApi.class);
        marketingApi.getResourceResultInfo(i).addOnSuccessListener(new OnSuccessListener() { // from class: own
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            public final void onSuccess(Object obj) {
                VmallOperation.this.d(marketingApi, i, vmallResourceCallback, (Map) obj);
            }
        });
    }

    public /* synthetic */ void d(MarketingApi marketingApi, int i, VmallResourceCallback vmallResourceCallback, Map map) {
        a(i, vmallResourceCallback, marketingApi.filterMarketingRules((Map<Integer, ResourceResultInfo>) map));
    }

    private void a(int i, final VmallResourceCallback vmallResourceCallback, Map<Integer, ResourceResultInfo> map) {
        if (map.containsKey(Integer.valueOf(i))) {
            ResourceResultInfo resourceResultInfo = map.get(Integer.valueOf(i));
            if (resourceResultInfo == null) {
                LogUtil.h("VmallOperation", "filterResultInfoMap = null");
                a((owx) null);
                HandlerExecutor.a(new Runnable() { // from class: owu
                    @Override // java.lang.Runnable
                    public final void run() {
                        VmallOperation.VmallResourceCallback.this.obtainVmallResource(null);
                    }
                });
                return;
            }
            List<ResourceBriefInfo> resources = resourceResultInfo.getResources();
            if (koq.b(resourceResultInfo.getResources())) {
                LogUtil.a("VmallOperation", "filterResultInfoMap is empty");
                a((owx) null);
                HandlerExecutor.a(new Runnable() { // from class: ows
                    @Override // java.lang.Runnable
                    public final void run() {
                        VmallOperation.VmallResourceCallback.this.obtainVmallResource(null);
                    }
                });
                return;
            }
            LogUtil.a("VmallOperation", "filterResultInfoMap size:", Integer.valueOf(resources.size()));
            LogUtil.a("VmallOperation", "filterResultInfoMap thread:", Thread.currentThread().getName());
            Gson gson = new Gson();
            for (ResourceBriefInfo resourceBriefInfo : resources) {
                if (resourceBriefInfo.getContentType() == 46) {
                    String content = resourceBriefInfo.getContent().getContent();
                    LogUtil.a("VmallOperation", content);
                    H5GeneralTemplate h5GeneralTemplate = (H5GeneralTemplate) gson.fromJson(content, H5GeneralTemplate.class);
                    final owx owxVar = new owx(h5GeneralTemplate.getName(), h5GeneralTemplate.getNameVisibility(), h5GeneralTemplate.getLinkValue());
                    a(owxVar);
                    HandlerExecutor.a(new Runnable() { // from class: owt
                        @Override // java.lang.Runnable
                        public final void run() {
                            VmallOperation.VmallResourceCallback.this.obtainVmallResource(owxVar);
                        }
                    });
                    return;
                }
            }
            HandlerExecutor.a(new Runnable() { // from class: oww
                @Override // java.lang.Runnable
                public final void run() {
                    VmallOperation.VmallResourceCallback.this.obtainVmallResource(null);
                }
            });
        }
    }

    private void a(owx owxVar) {
        if (owxVar == null) {
            LogUtil.a("VmallOperation", "cacheVmallResource resource == null, isHonor:", Boolean.valueOf(CommonUtil.bf()));
            if (CommonUtil.bf()) {
                SharedPreferenceManager.c(BaseApplication.getContext(), "VMALL_RESOURCE_SP", "VMALL_RESOURCE_SP");
                return;
            }
            return;
        }
        if (owxVar.e()) {
            LogUtil.a("VmallOperation", "cacheVmallResource");
            SharedPreferenceManager.e(BaseApplication.getContext(), "VMALL_RESOURCE_SP", "VMALL_RESOURCE_SP", new Gson().toJson(owxVar), new StorageParams());
        }
    }

    private owx e() {
        String b = SharedPreferenceManager.b(BaseApplication.getContext(), "VMALL_RESOURCE_SP", "VMALL_RESOURCE_SP");
        LogUtil.a("VmallOperation", "cache resource:", new Gson().toJson(b));
        if (TextUtils.isEmpty(b)) {
            return null;
        }
        return (owx) new Gson().fromJson(b, owx.class);
    }
}
