package defpackage;

import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import com.google.gson.Gson;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.marketing.datatype.templates.TopBannerTemplate;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginachievement.manager.model.MessageObject;
import com.huawei.ui.main.stories.userprofile.activity.PersonalCenterFragment;
import com.huawei.ui.main.stories.userprofile.activity.interfaces.PersonalCenterUiApi;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class say {

    /* renamed from: a, reason: collision with root package name */
    private final Context f16994a;
    private MarketingApi b;
    private ResourceBriefInfo c;
    private TopBannerTemplate d;
    private final Handler e;
    private volatile MessageObject f;
    private final PersonalCenterUiApi g;

    public say(PersonalCenterFragment personalCenterFragment, Handler handler) {
        this.f16994a = personalCenterFragment.getContext();
        this.g = personalCenterFragment;
        this.e = handler;
    }

    public MessageObject b() {
        return this.f;
    }

    public void a() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: saz
            @Override // java.lang.Runnable
            public final void run() {
                say.this.c();
            }
        });
    }

    /* synthetic */ void c() {
        LogUtil.a("MyBannerAdManager", " fetchBannerAd into");
        MarketingApi d = d();
        this.b = d;
        if (d == null) {
            LogUtil.h("MyBannerAdManager", " fetchBannerAd mMarketingApi is null,return");
            return;
        }
        LogUtil.a("MyBannerAdManager", " fetchBannerAd into valid");
        b bVar = new b(this);
        this.b.getResourceResultInfo(8001).addOnSuccessListener(bVar).addOnFailureListener(bVar);
    }

    private MarketingApi d() {
        if (this.b == null) {
            this.b = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        }
        return this.b;
    }

    static class b implements OnSuccessListener<Map<Integer, ResourceResultInfo>>, OnFailureListener {
        private final WeakReference<say> c;

        b(say sayVar) {
            this.c = new WeakReference<>(sayVar);
        }

        @Override // com.huawei.hmf.tasks.OnSuccessListener
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onSuccess(final Map<Integer, ResourceResultInfo> map) {
            final say sayVar = this.c.get();
            if (sayVar == null) {
                LogUtil.h("MyBannerAdManager", " OnAdDataSuccessListener myBannerAdManager is null");
            } else {
                ThreadPoolManager.d().execute(new Runnable() { // from class: sbf
                    @Override // java.lang.Runnable
                    public final void run() {
                        say.this.b((Map<Integer, ResourceResultInfo>) map);
                    }
                });
            }
        }

        @Override // com.huawei.hmf.tasks.OnFailureListener
        public void onFailure(Exception exc) {
            LogUtil.b("MyBannerAdManager", "Banner AD Load Failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Map<Integer, ResourceResultInfo> map) {
        if (this.b == null || map == null) {
            LogUtil.h("MyBannerAdManager", "mMarketingApi is null.");
            this.e.sendEmptyMessage(com.huawei.hms.kit.awareness.barrier.internal.e.a.A);
            return;
        }
        LogUtil.a("MyBannerAdManager", "handleData resultInfoMap size = ", Integer.valueOf(map.size()));
        Map<Integer, ResourceResultInfo> filterMarketingRules = this.b.filterMarketingRules(map);
        if (filterMarketingRules == null || !filterMarketingRules.containsKey(8001) || filterMarketingRules.get(8001) == null) {
            LogUtil.h("MyBannerAdManager", "filterResult is invalid data.");
            this.e.sendEmptyMessage(com.huawei.hms.kit.awareness.barrier.internal.e.a.A);
            return;
        }
        List<ResourceBriefInfo> resources = filterMarketingRules.get(8001).getResources();
        if (koq.b(resources)) {
            LogUtil.h("MyBannerAdManager", "resInfoList is empty.");
            this.e.sendEmptyMessage(com.huawei.hms.kit.awareness.barrier.internal.e.a.A);
            return;
        }
        Gson gson = new Gson();
        ResourceBriefInfo resourceBriefInfo = null;
        TopBannerTemplate topBannerTemplate = null;
        for (ResourceBriefInfo resourceBriefInfo2 : resources) {
            if (resourceBriefInfo2 == null || resourceBriefInfo2.getContentType() != 30 || resourceBriefInfo2.getContent() == null || TextUtils.isEmpty(resourceBriefInfo2.getContent().getContent())) {
                LogUtil.h("MyBannerAdManager", "resource brief info invalid");
            } else {
                String content = resourceBriefInfo2.getContent().getContent();
                LogUtil.a("MyBannerAdManager", " marketing2.0: ", content);
                TopBannerTemplate topBannerTemplate2 = (TopBannerTemplate) gson.fromJson(content, TopBannerTemplate.class);
                if (topBannerTemplate2 == null) {
                    LogUtil.h("MyBannerAdManager", "resource template parse exception");
                } else if (resourceBriefInfo == null || resourceBriefInfo.getPriority() < resourceBriefInfo2.getPriority()) {
                    resourceBriefInfo = resourceBriefInfo2;
                    topBannerTemplate = topBannerTemplate2;
                }
            }
        }
        a(resourceBriefInfo, topBannerTemplate);
    }

    private void a(ResourceBriefInfo resourceBriefInfo, TopBannerTemplate topBannerTemplate) {
        if (topBannerTemplate == null || resourceBriefInfo == null || TextUtils.isEmpty(topBannerTemplate.getPicture()) || TextUtils.isEmpty(topBannerTemplate.getLinkValue())) {
            LogUtil.h("MyBannerAdManager", " tmpTemplate or tmpHealthInfo is null, or tmpTemplate data invalid.");
            return;
        }
        MessageObject messageObject = new MessageObject();
        messageObject.setImgUri(topBannerTemplate.getPicture());
        messageObject.setDetailUri(topBannerTemplate.getLinkValue());
        messageObject.setMsgId(resourceBriefInfo.getResourceId());
        messageObject.setMetaData(resourceBriefInfo.getResourceName());
        messageObject.setCreateTime(resourceBriefInfo.getEffectiveTime());
        messageObject.setExpireTime(resourceBriefInfo.getExpirationTime());
        messageObject.setWeight(resourceBriefInfo.getPriority());
        messageObject.setMsgType(resourceBriefInfo.getContentType());
        messageObject.setResBriefInfo(resourceBriefInfo);
        this.f = messageObject;
        ResourceBriefInfo resourceBriefInfo2 = this.c;
        if (resourceBriefInfo2 == null || !(resourceBriefInfo2.equals(resourceBriefInfo) || topBannerTemplate.getPicture().equals(this.d.getPicture()) || topBannerTemplate.getLinkValue().equals(this.d.getLinkValue()))) {
            Handler handler = this.e;
            handler.sendMessage(handler.obtainMessage(com.huawei.hms.kit.awareness.barrier.internal.e.a.A, topBannerTemplate.getPicture()));
            this.c = resourceBriefInfo;
            this.d = topBannerTemplate;
            b(this.f, 0L, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(MessageObject messageObject, long j, int i) {
        if (messageObject == null) {
            LogUtil.h("MyBannerAdManager", "biEvent msgObj is null");
            return;
        }
        LogUtil.h("MyBannerAdManager", "biEvent event = ", Integer.valueOf(i));
        HashMap hashMap = new HashMap();
        hashMap.put("resourcePositionId", 8001);
        hashMap.put("resourceId", messageObject.getMsgId());
        hashMap.put("resourceName", messageObject.getMetaData());
        hashMap.put("itemCardName", "ADV_BANNER");
        hashMap.put("itemId", "");
        hashMap.put("pullOrder", 1);
        if (i == 2) {
            hashMap.put("durationTime", Long.valueOf(System.currentTimeMillis() - j));
        }
        if (messageObject.getResBriefInfo() != null) {
            hashMap.put("algId", "");
            hashMap.put("smartRecommend", Boolean.valueOf(messageObject.getResBriefInfo().getSmartRecommend()));
        }
        hashMap.put("event", Integer.valueOf(i));
        ixx.d().d(this.f16994a, AnalyticsValue.MARKETING_RESOURCE.value(), hashMap, 0);
        if (i != 4) {
            if (i == 2) {
                eil.a(messageObject.getDetailUri(), 8001, b(messageObject));
                return;
            } else {
                LogUtil.h("MyBannerAdManager", "biEvent error event = ", Integer.valueOf(i));
                return;
            }
        }
        this.b = d();
        ResourceBriefInfo resBriefInfo = messageObject.getResBriefInfo();
        MarketingApi marketingApi = this.b;
        if (marketingApi != null && resBriefInfo != null) {
            marketingApi.recordResourcePresent(8001, 1, resBriefInfo);
        }
        this.g.updateBannerAd(null, null);
        this.f = null;
    }

    private ResourceBriefInfo b(MessageObject messageObject) {
        return new ResourceBriefInfo.Builder().setResourceId(messageObject.getMsgId()).setResourceName(messageObject.getMetaData()).build();
    }

    public static class a implements View.OnClickListener {
        private final WeakReference<say> e;

        public a(say sayVar) {
            this.e = new WeakReference<>(sayVar);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (nsn.cLk_(view)) {
                LogUtil.a("MyBannerAdManager", "click too fast");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            say sayVar = this.e.get();
            if (sayVar == null) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            Object tag = view.getTag(R.layout.item_personal_center_banner_ad);
            long longValue = tag instanceof Long ? ((Long) tag).longValue() : 0L;
            int id = view.getId();
            if (id == R.id.item_banner_ad_img) {
                sayVar.b(sayVar.f, longValue, 2);
            } else if (id == R.id.item_banner_ad_close_icon) {
                sayVar.b(sayVar.f, longValue, 4);
            } else {
                LogUtil.a("MyBannerAdManager", "onClick id:", Integer.valueOf(id));
            }
            ViewClickInstrumentation.clickOnView(view);
        }
    }
}
