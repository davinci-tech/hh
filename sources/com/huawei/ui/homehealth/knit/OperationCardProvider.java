package com.huawei.ui.homehealth.knit;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Looper;
import android.text.TextUtils;
import android.view.View;
import com.google.gson.Gson;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.marketing.datatype.templates.DailyRecommendTemplate;
import com.huawei.health.marketrouter.api.MarketRouterApi;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginmessagecenter.provider.data.MessageChangeEvent;
import com.huawei.pluginmessagecenter.service.MessageObserver;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import defpackage.bzs;
import defpackage.eil;
import defpackage.ixx;
import defpackage.koq;
import defpackage.mcv;
import defpackage.onh;
import health.compact.a.GRSManager;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class OperationCardProvider extends BaseKnitDataProvider<Object> {

    /* renamed from: a, reason: collision with root package name */
    private Context f9478a;
    private MessageCenterApi f;
    private SectionBean h;
    private List<MessageObject> c = new ArrayList();
    private List<MessageObject> j = new ArrayList();
    private boolean e = true;
    private MessageObserver g = new MessageObserver() { // from class: com.huawei.ui.homehealth.knit.OperationCardProvider.4
        @Override // com.huawei.pluginmessagecenter.service.MessageObserver
        public void onChange(int i, MessageChangeEvent messageChangeEvent) {
            LogUtil.a("OperationCardProvider", "MessageObserver onChange start");
            ThreadPoolManager.d().c("OperationCardProvider", new Runnable() { // from class: com.huawei.ui.homehealth.knit.OperationCardProvider.4.5
                @Override // java.lang.Runnable
                public void run() {
                    OperationCardProvider.this.e = false;
                    OperationCardProvider.this.h();
                }
            });
            LogUtil.a("OperationCardProvider", "MessageObserver onChange end");
        }
    };
    private Map<String, Object> d = new HashMap();
    private long b = System.currentTimeMillis();

    public OperationCardProvider() {
        e();
        f();
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void parseParams(Context context, HashMap<String, Object> hashMap, Object obj) {
        LogUtil.a("OperationCardProvider", "operation card parseParams, size: ", Integer.valueOf(this.d.size()));
        hashMap.putAll(this.d);
        this.d.remove("NOTIFY_UPDATE");
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void e(Context context, SectionBean sectionBean) {
        LogUtil.a("OperationCardProvider", "operation card loadData");
        this.f9478a = context;
        this.h = sectionBean;
        d();
    }

    private void e() {
        this.d.put("DATA_LIST", this.c);
        this.d.put("THREAD_LIST", this.j);
        this.d.put("CLICK_EVENT_LISTENER", b());
    }

    private OnClickSectionListener b() {
        return new OnClickSectionListener() { // from class: com.huawei.ui.homehealth.knit.OperationCardProvider.2
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, int i2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, String str) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
                if (koq.d(OperationCardProvider.this.c, i)) {
                    MessageObject messageObject = (MessageObject) OperationCardProvider.this.c.get(i);
                    final String detailUri = messageObject.getDetailUri();
                    if (!Utils.o() || bzs.e().switchToMarketingTwo()) {
                        OperationCardProvider.this.a(messageObject, 2);
                        OperationCardProvider.this.d(eil.c(detailUri, messageObject.getCategory()), messageObject);
                        LogUtil.a("OperationCardProvider", "is marking 2.0");
                    } else {
                        OperationCardProvider.this.b(messageObject);
                        if (TextUtils.isEmpty(detailUri)) {
                            return;
                        }
                        GRSManager.a(OperationCardProvider.this.f9478a).e("messageCenterUrl", new GrsQueryCallback() { // from class: com.huawei.ui.homehealth.knit.OperationCardProvider.2.4
                            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                            public void onCallBackSuccess(String str) {
                                LogUtil.c("OperationCardProvider", "GRSManager onCallBackSuccess url = ", str);
                                OperationCardProvider.this.a(OperationCardProvider.this.b(detailUri, str + "/messageH5/html/launchFitness.html?url="), detailUri);
                            }

                            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
                            public void onCallBackFail(int i2) {
                                LogUtil.c("OperationCardProvider", "GRSManager onCallBackFail code = ", Integer.valueOf(i2));
                                OperationCardProvider.this.a(detailUri);
                            }
                        });
                    }
                }
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String b(String str, String str2) {
        String queryParameter = (TextUtils.isEmpty(str) || !str.startsWith(str2)) ? Constants.SUFFIX_HTML : Uri.parse(str.replace(str2, "")).getQueryParameter("type");
        LogUtil.c("OperationCardProvider", "跳转类型：", queryParameter);
        return queryParameter;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str, String str2) {
        str.hashCode();
        if (str.equals(Constants.SUFFIX_HTML)) {
            a(str2);
        } else if (str.equals("medal")) {
            mcv.d(this.f9478a).j(this.f9478a);
        } else {
            a(str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        Intent intent = new Intent(this.f9478a, (Class<?>) WebViewActivity.class);
        intent.putExtra("url", str);
        try {
            this.f9478a.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            LogUtil.b("OperationCardProvider", "ActivityNotFoundException", e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, MessageObject messageObject) {
        MarketRouterApi marketRouterApi = (MarketRouterApi) Services.a("FeatureMarketing", MarketRouterApi.class);
        if (marketRouterApi != null) {
            marketRouterApi.router(c(str, messageObject));
        }
    }

    private String c(String str, MessageObject messageObject) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append(str.contains("?") ? "&pullfrom=" : "?pullfrom=");
        return sb.toString() + "1001&resourceName=" + messageObject.getMsgFrom() + "&resourceId=" + messageObject.getMsgId() + "&pullOrder=1&itemId=" + messageObject.getMsgId();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(MessageObject messageObject) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", "1");
        hashMap.put("msgId", messageObject.getMsgId());
        hashMap.put(CommonUtil.MSG_TITLE, messageObject.getMsgTitle());
        ixx.d().d(this.f9478a, AnalyticsValue.HEALTH_HOME_OPERATION_CARD_DATA_2010085.value(), hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(MessageObject messageObject, int i) {
        if (messageObject == null) {
            LogUtil.h("OperationCardProvider", "biEvent messageObject is null");
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("resourcePositionId", 1001);
        hashMap.put("resourceId", messageObject.getMsgId());
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put("resourceName", messageObject.getMsgFrom());
        hashMap.put("itemCardName", messageObject.getMsgTitle());
        hashMap.put("itemId", "");
        hashMap.put("pullOrder", 1);
        if (i == 2) {
            hashMap.put("durationTime", Long.valueOf(System.currentTimeMillis() - this.b));
            this.b = System.currentTimeMillis();
        }
        if (messageObject.getResBriefInfo() != null) {
            hashMap.put("algId", "");
            hashMap.put("smartRecommend", Boolean.valueOf(messageObject.getResBriefInfo().getSmartRecommend()));
        }
        ixx.d().d(this.f9478a, AnalyticsValue.MARKETING_RESOURCE.value(), hashMap, 0);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        g();
        this.g = null;
        this.j.clear();
        this.c.clear();
    }

    private void f() {
        LogUtil.a("OperationCardProvider", "registerObserver");
        if (this.g != null) {
            LogUtil.a("OperationCardProvider", "registerObserver mMessageCenter and mMessageObs is not null");
            this.f = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
            ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.knit.OperationCardProvider.5
                @Override // java.lang.Runnable
                public void run() {
                    OperationCardProvider.this.f.registerMessageObserver(OperationCardProvider.this.g);
                }
            });
        }
    }

    private void g() {
        LogUtil.a("OperationCardProvider", "unregisterObserver");
        if (this.g == null || this.f == null) {
            return;
        }
        LogUtil.a("OperationCardProvider", "unregisterObserver mMessageCenter and mMessageObs is not null");
        this.f.unregisterMessageObserver(this.g);
    }

    private void d() {
        ThreadPoolManager.d().c("OperationCardProvider", new Runnable() { // from class: com.huawei.ui.homehealth.knit.OperationCardProvider.1
            @Override // java.lang.Runnable
            public void run() {
                if (OperationCardProvider.this.e) {
                    OperationCardProvider.this.h();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        LogUtil.a("OperationCardProvider", "enter operationCardMessage");
        this.j.clear();
        if (Utils.o()) {
            if (this.f == null) {
                this.f = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
            }
            List<MessageObject> messages = this.f.getMessages(30);
            if (koq.c(messages)) {
                d(messages);
                Collections.sort(messages, new onh());
                for (int i = 0; i < messages.size() && i < 3; i++) {
                    this.j.add(messages.get(i));
                    LogUtil.a("OperationCardProvider", "messageObjectList id:", messages.get(i).getMsgId());
                }
            }
            LogUtil.a("OperationCardProvider", "operationCardMessage mThreadList size is ", Integer.valueOf(this.j.size()));
            c();
        }
        a();
    }

    private void a() {
        LogUtil.a("OperationCardProvider", "enter getBannerData");
        final MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi != null) {
            marketingApi.getResourceResultInfo(1001).addOnSuccessListener(new OnSuccessListener<Map<Integer, ResourceResultInfo>>() { // from class: com.huawei.ui.homehealth.knit.OperationCardProvider.3
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void onSuccess(Map<Integer, ResourceResultInfo> map) {
                    Map<Integer, ResourceResultInfo> filterMarketingRules = marketingApi.filterMarketingRules(map);
                    if (filterMarketingRules.containsKey(1001)) {
                        ResourceResultInfo resourceResultInfo = filterMarketingRules.get(1001);
                        if (resourceResultInfo == null) {
                            LogUtil.h("OperationCardProvider", "resourceResultInfo = null");
                            return;
                        }
                        List<ResourceBriefInfo> resources = resourceResultInfo.getResources();
                        if (koq.b(resources)) {
                            LogUtil.a("OperationCardProvider", "resourceBriefInfoList is empty");
                        } else {
                            LogUtil.a("OperationCardProvider", "resourceBriefInfoList size:", Integer.valueOf(resources.size()));
                            OperationCardProvider.this.a(resources);
                        }
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<ResourceBriefInfo> list) {
        ArrayList arrayList = new ArrayList();
        for (ResourceBriefInfo resourceBriefInfo : list) {
            if (resourceBriefInfo == null || resourceBriefInfo.getContent() == null || resourceBriefInfo.getContent().getContent() == null) {
                LogUtil.a("OperationCardProvider", "resourceBriefInfo = null");
            } else {
                MessageObject messageObject = new MessageObject();
                Gson gson = new Gson();
                String content = resourceBriefInfo.getContent().getContent();
                LogUtil.a("OperationCardProvider", content);
                DailyRecommendTemplate dailyRecommendTemplate = (DailyRecommendTemplate) gson.fromJson(content, DailyRecommendTemplate.class);
                messageObject.setImgUri(dailyRecommendTemplate.getPicture());
                messageObject.setDetailUri(dailyRecommendTemplate.getLinkValue());
                messageObject.setMsgTitle(dailyRecommendTemplate.getTheme());
                messageObject.setMsgContent(dailyRecommendTemplate.getDescription());
                messageObject.setMsgId(resourceBriefInfo.getResourceId());
                messageObject.setWeight(resourceBriefInfo.getPriority());
                messageObject.setMsgFrom(resourceBriefInfo.getResourceName());
                messageObject.setCategory(resourceBriefInfo.getCategory());
                arrayList.add(messageObject);
            }
        }
        LogUtil.a("OperationCardProvider", "messageObjectList:", Integer.valueOf(arrayList.size()));
        Collections.sort(arrayList, new onh());
        this.j.clear();
        for (int i = 0; i < arrayList.size() && i < 3; i++) {
            this.j.add((MessageObject) arrayList.get(i));
            LogUtil.a("OperationCardProvider", "messageObjectList id:", ((MessageObject) arrayList.get(i)).getMsgId());
        }
        c();
    }

    private void d(List<MessageObject> list) {
        String url = GRSManager.a(this.f9478a).getUrl("domainClubHuawei");
        Iterator<MessageObject> it = list.iterator();
        while (it.hasNext()) {
            String detailUri = it.next().getDetailUri();
            if (!TextUtils.isEmpty(detailUri)) {
                if (detailUri.contains(url + "/")) {
                    it.remove();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.homehealth.knit.OperationCardProvider.8
                @Override // java.lang.Runnable
                public void run() {
                    OperationCardProvider.this.c();
                }
            });
        } else {
            this.d.put("NOTIFY_UPDATE", true);
            i();
        }
    }

    private void i() {
        LogUtil.a("OperationCardProvider", "operation card refreshSection");
        SectionBean sectionBean = this.h;
        if (sectionBean != null) {
            sectionBean.e(new Object());
        }
    }
}
