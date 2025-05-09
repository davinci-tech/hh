package com.huawei.ui.homehealth.operationcard;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.marketing.datatype.SingleDailyMomentContent;
import com.huawei.health.marketing.datatype.templates.DailyRecommendTemplate;
import com.huawei.health.marketing.request.ActivityIdInfo;
import com.huawei.health.marketing.request.ColumnRequestUtils;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginmessagecenter.provider.data.MessageChangeEvent;
import com.huawei.pluginmessagecenter.service.MessageObserver;
import com.huawei.ui.homehealth.operationcard.OperationCardData;
import defpackage.bzs;
import defpackage.koq;
import defpackage.nrv;
import defpackage.onh;
import health.compact.a.GRSManager;
import health.compact.a.Services;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes6.dex */
public class OperationCardData extends AbstractBaseCardData {

    /* renamed from: a, reason: collision with root package name */
    private final Handler f9485a;
    private ResourceResultInfo d;
    private Context e;
    private OperationCardViewHolder f;
    private MessageObserver g;
    private MessageCenterApi h;
    private List<MessageObject> i;
    private CopyOnWriteArrayList<MessageObject> n;
    private Map<Integer, ResourceResultInfo> b = new HashMap();
    private int c = 0;
    private boolean j = true;

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void refreshCardData() {
    }

    static /* synthetic */ int b(OperationCardData operationCardData) {
        int i = operationCardData.c;
        operationCardData.c = i + 1;
        return i;
    }

    static class a extends BaseHandler<OperationCardData> {
        a(OperationCardData operationCardData) {
            super(operationCardData);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: ddj_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(OperationCardData operationCardData, Message message) {
            if (message.what != 0) {
                return;
            }
            if (operationCardData.f != null) {
                if (operationCardData.n.size() != 0) {
                    LogUtil.a("OperationCardData", "handleMessage UPDATE mThreadList size is ", Integer.valueOf(operationCardData.n.size()));
                    operationCardData.f.d(0);
                    operationCardData.i.clear();
                    operationCardData.i.addAll(operationCardData.n);
                    LogUtil.a("OperationCardData", "handleMessage UPDATE mList size is ", Integer.valueOf(operationCardData.i.size()));
                    boolean a2 = operationCardData.f.a();
                    LogUtil.a("OperationCardData", "handleMessage UPDATE isNotify is ", Boolean.valueOf(a2));
                    if (!a2) {
                        if (operationCardData.c < 5) {
                            LogUtil.a("OperationCardData", "handleMessage UPDATE again");
                            OperationCardData.b(operationCardData);
                            sendEmptyMessageDelayed(0, 300L);
                            return;
                        }
                        return;
                    }
                    operationCardData.c = 0;
                    return;
                }
                LogUtil.h("OperationCardData", "handleMessage UPDATE mThreadList size is zero");
                operationCardData.f.d(8);
                return;
            }
            LogUtil.h("OperationCardData", "handleMessage UPDATE mOperationCardViewHolder is null");
        }
    }

    public static class c implements MessageObserver {
        private final WeakReference<OperationCardData> c;

        c(OperationCardData operationCardData) {
            this.c = new WeakReference<>(operationCardData);
        }

        @Override // com.huawei.pluginmessagecenter.service.MessageObserver
        public void onChange(int i, MessageChangeEvent messageChangeEvent) {
            LogUtil.a("OperationCardData", "MessageObserver onChange start");
            final OperationCardData operationCardData = this.c.get();
            if (operationCardData != null) {
                ThreadPoolManager.d().c("OperationCardData", new Runnable() { // from class: onq
                    @Override // java.lang.Runnable
                    public final void run() {
                        OperationCardData.c.d(OperationCardData.this);
                    }
                });
                LogUtil.a("OperationCardData", "MessageObserver onChange end");
            } else {
                LogUtil.h("OperationCardData", "cardData is null");
            }
        }

        public static /* synthetic */ void d(OperationCardData operationCardData) {
            operationCardData.j = false;
            operationCardData.i();
        }
    }

    public OperationCardData(Context context) {
        LogUtil.a("OperationCardData", "OperationCardData");
        this.e = context;
        this.i = new ArrayList();
        this.n = new CopyOnWriteArrayList<>();
        this.f9485a = new a(this);
        a();
    }

    private void a() {
        if (Utils.o() && !bzs.e().switchToMarketingTwo()) {
            g();
        } else {
            c();
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void onConfigurationChanged(Configuration configuration) {
        OperationCardViewHolder operationCardViewHolder = this.f;
        if (operationCardViewHolder != null) {
            operationCardViewHolder.a();
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public RecyclerView.ViewHolder getCardViewHolder(ViewGroup viewGroup, LayoutInflater layoutInflater) {
        this.f = new OperationCardViewHolder(layoutInflater.inflate(R.layout.home_item_layout_operation, viewGroup, false), this.e, false);
        d();
        return this.f;
    }

    private void d() {
        this.f.d(this.i, this.b);
        this.f.d(8);
        if (!koq.b(this.n) || !this.b.isEmpty()) {
            LogUtil.a("OperationCardData", "data is ready,show");
            this.f9485a.sendEmptyMessage(0);
        } else {
            c();
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public void onDestroy() {
        super.onDestroy();
        f();
        this.f = null;
        this.f9485a.removeCallbacksAndMessages(null);
        this.g = null;
        this.mNotifyCardManager = null;
        this.n.clear();
        this.i.clear();
    }

    private void g() {
        LogUtil.a("OperationCardData", "registerObserver");
        this.g = new c(this);
        this.h = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
        ThreadPoolManager.d().execute(new Runnable() { // from class: ono
            @Override // java.lang.Runnable
            public final void run() {
                OperationCardData.this.b();
            }
        });
    }

    public /* synthetic */ void b() {
        this.h.registerMessageObserver(this.g);
    }

    private void f() {
        LogUtil.a("OperationCardData", "unregisterObserver");
        if (this.g == null || this.h == null) {
            return;
        }
        LogUtil.a("OperationCardData", "unregisterObserver mMessageCenter and mMessageObs is not null");
        this.h.unregisterMessageObserver(this.g);
    }

    private void c() {
        ThreadPoolManager.d().c("OperationCardData", new Runnable() { // from class: com.huawei.ui.homehealth.operationcard.OperationCardData.2
            @Override // java.lang.Runnable
            public void run() {
                if (OperationCardData.this.j) {
                    OperationCardData.this.i();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        ReleaseLogUtil.e("R_OperationCardData", "enter operationCardMessage");
        this.n.clear();
        if (Utils.o() && !bzs.e().switchToMarketingTwo()) {
            ReleaseLogUtil.e("R_OperationCardData", "get operationCardMessage from messageCenter");
            if (this.h == null) {
                this.h = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
            }
            List<MessageObject> messages = this.h.getMessages(30);
            if (koq.c(messages)) {
                a(messages);
                Collections.sort(messages, new onh());
                for (int i = 0; i < messages.size() && i < 3; i++) {
                    this.n.add(messages.get(i));
                    LogUtil.a("OperationCardData", "messageObjectList id:", messages.get(i).getMsgId());
                }
            }
            ReleaseLogUtil.e("R_OperationCardData", "operationCardMessage mThreadList size is ", Integer.valueOf(this.n.size()));
            this.f9485a.sendEmptyMessage(0);
            return;
        }
        e();
    }

    private void e() {
        ReleaseLogUtil.e("R_OperationCardData", "enter getBannerData");
        ArrayList arrayList = new ArrayList();
        arrayList.add(1001);
        arrayList.add(4167);
        final MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi != null) {
            marketingApi.getResourceResultInfo(arrayList).addOnSuccessListener(new OnSuccessListener<Map<Integer, ResourceResultInfo>>() { // from class: com.huawei.ui.homehealth.operationcard.OperationCardData.5
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(Map<Integer, ResourceResultInfo> map) {
                    Map<Integer, ResourceResultInfo> filterMarketingRules = marketingApi.filterMarketingRules(map);
                    boolean z = false;
                    for (Map.Entry<Integer, ResourceResultInfo> entry : filterMarketingRules.entrySet()) {
                        if (entry.getKey().intValue() != 1001) {
                            if (OperationCardData.this.b.containsKey(entry.getKey()) && !z) {
                                if (!nrv.a((ResourceResultInfo) OperationCardData.this.b.get(entry.getKey())).equals(nrv.a(entry.getValue()))) {
                                    ReleaseLogUtil.d("R_OperationCardData", "other resourceResultInfo is diff");
                                    z = true;
                                }
                            }
                            OperationCardData.this.b.put(entry.getKey(), entry.getValue());
                        } else {
                            ResourceResultInfo resourceResultInfo = filterMarketingRules.get(1001);
                            if (resourceResultInfo == null) {
                                ReleaseLogUtil.d("R_OperationCardData", "resourceResultInfo = null");
                            } else {
                                List<ResourceBriefInfo> resources = resourceResultInfo.getResources();
                                if (!koq.b(resources)) {
                                    if (!nrv.a(OperationCardData.this.d).equals(nrv.a(resourceResultInfo))) {
                                        ReleaseLogUtil.d("R_OperationCardData", "banner resourceResultInfo is diff");
                                        if (OperationCardData.this.f != null) {
                                            OperationCardData.this.d = resourceResultInfo;
                                        }
                                        ReleaseLogUtil.e("R_OperationCardData", "resourceBriefInfoList size:", Integer.valueOf(resources.size()));
                                        OperationCardData.this.e(resources);
                                        z = true;
                                    }
                                } else {
                                    ReleaseLogUtil.e("R_OperationCardData", "resourceBriefInfoList is empty");
                                }
                            }
                        }
                    }
                    ReleaseLogUtil.d("R_OperationCardData", "getBannerData isNeedUpdate:", Boolean.valueOf(z));
                    if (z) {
                        OperationCardData.this.f9485a.sendEmptyMessage(0);
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<ResourceBriefInfo> list) {
        ArrayList arrayList = new ArrayList();
        for (ResourceBriefInfo resourceBriefInfo : list) {
            DailyRecommendTemplate a2 = a(resourceBriefInfo);
            if (a2 != null) {
                MessageObject messageObject = new MessageObject();
                messageObject.setImgUri(a2.getPicture());
                messageObject.setDetailUri(a2.getLinkValue());
                messageObject.setMsgTitle(a2.getThemeVisibility() ? a2.getTheme() : "");
                messageObject.setMsgContent(a2.getDescriptionVisibility() ? a2.getDescription() : "");
                messageObject.setMsgId(resourceBriefInfo.getResourceId());
                messageObject.setWeight(resourceBriefInfo.getPriority());
                messageObject.setMsgFrom(resourceBriefInfo.getResourceName());
                messageObject.setCategory(resourceBriefInfo.getCategory());
                messageObject.setResBriefInfo(resourceBriefInfo);
                messageObject.setDynamicDataId(a2.getDynamicDataId());
                arrayList.add(messageObject);
            }
        }
        LogUtil.a("OperationCardData", "messageObjectList:", Integer.valueOf(arrayList.size()));
        Collections.sort(arrayList, new onh());
        this.n.clear();
        ArrayList arrayList2 = new ArrayList(10);
        for (int i = 0; i < arrayList.size() && i < 3; i++) {
            MessageObject messageObject2 = (MessageObject) arrayList.get(i);
            this.n.add(messageObject2);
            LogUtil.a("OperationCardData", "messageObjectList id:", messageObject2.getMsgId());
            String category = messageObject2.getCategory();
            String dynamicDataId = messageObject2.getDynamicDataId();
            if (TextUtils.equals(category, SingleDailyMomentContent.ACTIVITY_TYPE) && !TextUtils.isEmpty(dynamicDataId) && !arrayList2.contains(dynamicDataId)) {
                arrayList2.add(dynamicDataId);
            }
        }
        if (koq.b(arrayList2)) {
            return;
        }
        c(arrayList2);
    }

    private void c(final List<String> list) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: onp
                @Override // java.lang.Runnable
                public final void run() {
                    OperationCardData.this.d(list);
                }
            });
            return;
        }
        List<ActivityIdInfo> requestActivityInfo = ColumnRequestUtils.requestActivityInfo(this.e, 1001, list);
        HashSet hashSet = new HashSet(10);
        for (ActivityIdInfo activityIdInfo : requestActivityInfo) {
            String activityId = activityIdInfo.getActivityId();
            int numberOfPeople = activityIdInfo.getNumberOfPeople();
            Iterator<MessageObject> it = this.n.iterator();
            while (true) {
                if (it.hasNext()) {
                    MessageObject next = it.next();
                    String msgId = next.getMsgId();
                    if (!hashSet.contains(msgId) && TextUtils.equals(activityId, next.getDynamicDataId())) {
                        next.setActivityJoinNum(numberOfPeople);
                        hashSet.add(msgId);
                        break;
                    }
                }
            }
        }
        this.f9485a.sendEmptyMessage(0);
    }

    public /* synthetic */ void d(List list) {
        c((List<String>) list);
    }

    public static DailyRecommendTemplate a(ResourceBriefInfo resourceBriefInfo) {
        if (resourceBriefInfo == null || resourceBriefInfo.getContent() == null || resourceBriefInfo.getContent().getContent() == null) {
            LogUtil.a("OperationCardData", "resourceBriefInfo = null");
            return null;
        }
        String content = resourceBriefInfo.getContent().getContent();
        LogUtil.a("OperationCardData", "popUpTemplateStr: ", content);
        try {
            return (DailyRecommendTemplate) new Gson().fromJson(content, DailyRecommendTemplate.class);
        } catch (JsonSyntaxException unused) {
            LogUtil.b("OperationCardData", "getDailyRecommendTemplate json syntax exception");
            return null;
        }
    }

    private void a(List<MessageObject> list) {
        String url = GRSManager.a(this.e).getUrl("domainClubHuawei");
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

    @Override // com.huawei.health.health.utils.functionsetcard.AbstractBaseCardData
    public String getCardName() {
        return "OperationCardData";
    }
}
