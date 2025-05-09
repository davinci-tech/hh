package com.huawei.pluginmessagecenter.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.webkit.ProxyConfig;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.marketing.datatype.templates.InternalLetterTemplate;
import com.huawei.health.messagecenter.model.CommonUtil;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.health.vip.api.VipApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.util.SharedPreferenceUtil;
import com.huawei.pluginmessagecenter.adapter.MessageDetailAdapter;
import com.huawei.pluginmessagecenter.listener.OnItemVisibleListener;
import com.huawei.trade.PayApi;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.main.stories.soical.SocialFragmentFactoryApi;
import defpackage.ixx;
import defpackage.koq;
import defpackage.mqw;
import defpackage.mrj;
import defpackage.mrq;
import defpackage.mrr;
import defpackage.nsn;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* loaded from: classes8.dex */
public class MessageDetailActivity extends BaseActivity {
    private ListView b;
    private CustomTitleBar c;
    private int d;
    private MarketingApi f;
    private MessageDetailAdapter i;

    /* renamed from: a, reason: collision with root package name */
    private ExecutorService f8500a = Executors.newSingleThreadExecutor();
    private volatile boolean e = false;
    private Map<Integer, List<MessageObject>> h = new ConcurrentHashMap(16);
    private Handler g = new Handler() { // from class: com.huawei.pluginmessagecenter.activity.MessageDetailActivity.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what != 0) {
                return;
            }
            MessageDetailActivity.this.e((List<MessageObject>) message.obj);
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        String stringExtra = intent.getStringExtra("module");
        setContentView(R.layout.activity_message_datail_list);
        LogUtil.a("UIDV_MessageDetailActivity", "module", stringExtra);
        getWindow().setBackgroundDrawable(null);
        this.b = (ListView) findViewById(R.id.items_detail_listView);
        this.c = (CustomTitleBar) findViewById(R.id.message_detail_title);
        if (stringExtra != null) {
            b(stringExtra);
        }
        this.b.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.huawei.pluginmessagecenter.activity.MessageDetailActivity.5
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                MessageDetailActivity.this.cnV_(adapterView, view, i, j);
                ViewClickInstrumentation.clickOnListView(adapterView, view, i);
            }
        });
        MessageDetailAdapter messageDetailAdapter = new MessageDetailAdapter(this, new ArrayList(0));
        this.i = messageDetailAdapter;
        messageDetailAdapter.d(new d(this));
        this.b.setAdapter((ListAdapter) this.i);
        if (String.valueOf(14).equals(stringExtra)) {
            int a2 = a();
            CountDownLatch countDownLatch = new CountDownLatch(2);
            c cVar = new c(this, stringExtra, a2, countDownLatch);
            b(a2, countDownLatch);
            this.f8500a.execute(cVar);
            return;
        }
        if (String.valueOf(80).equals(stringExtra)) {
            d();
        } else {
            this.f8500a.execute(new c(this, stringExtra));
        }
    }

    private void d() {
        List<MessageObject> b = b();
        if (koq.b(b)) {
            LogUtil.h("UIDV_MessageDetailActivity", "showAssetMessage messageObjects is empty");
            finish();
        } else {
            Handler handler = this.g;
            handler.sendMessage(handler.obtainMessage(0, b));
            c(b);
        }
    }

    private void d(List<MessageObject> list) {
        LogUtil.a("UIDV_MessageDetailActivity", "setMemberMessageRead");
        ArrayList arrayList = new ArrayList();
        for (MessageObject messageObject : list) {
            if (messageObject.getReadFlag() == 0) {
                arrayList.add(messageObject.getMsgId());
            }
        }
        if (!koq.b(arrayList)) {
            LogUtil.a("UIDV_MessageDetailActivity", "messageIds:", arrayList.toString());
            VipApi vipApi = (VipApi) Services.a("vip", VipApi.class);
            if (vipApi == null) {
                LogUtil.h("UIDV_MessageDetailActivity", "getVipMessages VipApi is null");
                return;
            } else {
                vipApi.setVipMessageRead(arrayList);
                return;
            }
        }
        LogUtil.h("UIDV_MessageDetailActivity", "messageIds is empty");
    }

    private void c(List<MessageObject> list) {
        LogUtil.a("UIDV_MessageDetailActivity", "setAssetMessageRead");
        ArrayList arrayList = new ArrayList();
        for (MessageObject messageObject : list) {
            if (messageObject.getReadFlag() == 0) {
                arrayList.add(messageObject.getMsgId());
            }
        }
        if (!koq.b(arrayList)) {
            LogUtil.a("UIDV_MessageDetailActivity", "setAssetMessageRead messageIds:", arrayList.toString());
            PayApi payApi = (PayApi) Services.a("TradeService", PayApi.class);
            if (payApi == null) {
                LogUtil.h("UIDV_MessageDetailActivity", "setAssetMessageRead VipApi is null");
                return;
            } else {
                payApi.assetMsgVisited(arrayList, null);
                return;
            }
        }
        LogUtil.h("UIDV_MessageDetailActivity", " setAssetMessageRead messageIds is empty");
    }

    private List<MessageObject> e() {
        LogUtil.a("UIDV_MessageDetailActivity", "enter getData");
        ArrayList arrayList = new ArrayList();
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("UIDV_MessageDetailActivity", "getData intent is null");
            return arrayList;
        }
        String stringExtra = intent.getStringExtra("messages");
        if (TextUtils.isEmpty(stringExtra)) {
            LogUtil.h("UIDV_MessageDetailActivity", "json is empty");
            return arrayList;
        }
        try {
            return (List) HiJsonUtil.b(stringExtra, new TypeToken<List<MessageObject>>() { // from class: com.huawei.pluginmessagecenter.activity.MessageDetailActivity.3
            }.getType());
        } catch (JsonParseException unused) {
            LogUtil.a("UIDV_MessageDetailActivity", "getData JsonParseException");
            return arrayList;
        }
    }

    private List<MessageObject> b() {
        LogUtil.a("UIDV_MessageDetailActivity", "enter getAssetData");
        ArrayList arrayList = new ArrayList();
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("UIDV_MessageDetailActivity", "getAssetData intent is null");
            return arrayList;
        }
        try {
            return (List) HiJsonUtil.b(intent.getStringExtra("asset"), new TypeToken<List<MessageObject>>() { // from class: com.huawei.pluginmessagecenter.activity.MessageDetailActivity.4
            }.getType());
        } catch (JsonParseException unused) {
            LogUtil.a("UIDV_MessageDetailActivity", "getAssetData JsonParseException");
            return arrayList;
        }
    }

    static class c implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private int f8503a;
        private final WeakReference<MessageDetailActivity> b;
        private final String c;
        private CountDownLatch e;

        c(MessageDetailActivity messageDetailActivity, String str, int i, CountDownLatch countDownLatch) {
            this.f8503a = 0;
            this.b = new WeakReference<>(messageDetailActivity);
            this.c = str;
            this.f8503a = i;
            this.e = countDownLatch;
        }

        c(MessageDetailActivity messageDetailActivity, String str) {
            this(messageDetailActivity, str, 0, null);
        }

        @Override // java.lang.Runnable
        public void run() {
            MessageDetailActivity messageDetailActivity = this.b.get();
            if (messageDetailActivity == null || messageDetailActivity.isFinishing() || messageDetailActivity.isDestroyed()) {
                return;
            }
            messageDetailActivity.c(this.c, this.f8503a, this.e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(String str, int i, CountDownLatch countDownLatch) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        List<MessageObject> c2 = mqw.b(this).c(str);
        if (String.valueOf(14).equals(str) && countDownLatch != null) {
            countDownLatch.countDown();
            if (countDownLatch.getCount() < 2) {
                try {
                    LogUtil.a("UIDV_MessageDetailActivity", "handleMessageCenter reached = ", Boolean.valueOf(countDownLatch.await(PreConnectManager.CONNECT_INTERNAL, TimeUnit.MILLISECONDS)));
                } catch (InterruptedException e) {
                    LogUtil.b("UIDV_MessageDetailActivity", e.getMessage());
                }
            }
            Collection<? extends MessageObject> collection = (List) this.h.remove(Integer.valueOf(i));
            if (koq.c(collection)) {
                c2.addAll(collection);
                Collections.sort(c2, new Comparator<MessageObject>() { // from class: com.huawei.pluginmessagecenter.activity.MessageDetailActivity.1
                    @Override // java.util.Comparator
                    /* renamed from: d, reason: merged with bridge method [inline-methods] */
                    public int compare(MessageObject messageObject, MessageObject messageObject2) {
                        return Long.compare(messageObject2.getReceiveTime(), messageObject.getReceiveTime());
                    }
                });
                mrr.b();
            }
        }
        if (String.valueOf(70).equals(str)) {
            List<MessageObject> e2 = e();
            if (koq.c(e2)) {
                c2.addAll(e2);
                d(e2);
            }
            mrq.d(c2);
            c();
        }
        Handler handler = this.g;
        handler.sendMessage(handler.obtainMessage(0, c2));
        if (String.valueOf(90).equals(str)) {
            d(c2);
        }
        Iterator<MessageObject> it = c2.iterator();
        while (it.hasNext()) {
            b(it.next());
        }
    }

    private void b(MessageObject messageObject) {
        if (messageObject.getPageType() == 43) {
            messageObject.setReadFlag(1);
            mrr.b(messageObject.getMsgId(), 1);
        } else if (messageObject.getReadFlag() == 0) {
            mqw.b(this).a(messageObject.getMsgId());
        }
    }

    private void b(String str) {
        Context context = BaseApplication.getContext();
        if (context != null) {
            try {
                this.c.setTitleText(context.getString(mrq.d(str)));
            } catch (Resources.NotFoundException unused) {
                LogUtil.b("UIDV_MessageDetailActivity", "Resources NotFound");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cnV_(AdapterView<?> adapterView, View view, int i, long j) {
        if (nsn.cLk_(view)) {
            LogUtil.h("UIDV_MessageDetailActivity", "click too fast");
            return;
        }
        MessageObject messageObject = adapterView.getItemAtPosition(i) instanceof MessageObject ? (MessageObject) adapterView.getItemAtPosition(i) : null;
        if (messageObject == null) {
            LogUtil.h("UIDV_MessageDetailActivity", "messageObject is null");
            return;
        }
        if (messageObject.getNewMsg() == 1) {
            LogUtil.a("UIDV_MessageDetailActivity", "messegeItemClick, this is a new type message");
            messageObject.jumpToTargetPage(this);
        } else {
            Intent intent = new Intent();
            intent.putExtra("msgId", messageObject.getMsgId());
            String detailUri = messageObject.getDetailUri();
            intent.putExtra(CommonUtil.DETAIL_URI, detailUri);
            intent.putExtra(MessageConstant.MESSAGE_TITLE, messageObject.getMsgTitle());
            if (String.valueOf(17).equals(messageObject.getModule())) {
                mrj.com_(this, Uri.parse(detailUri), 1);
                return;
            } else {
                intent.setClass(this, DispatchSkipEventActivity.class);
                startActivity(intent);
            }
        }
        Object tag = view.getTag(R.layout.message_detail_layout);
        long longValue = tag instanceof Long ? ((Long) tag).longValue() : 0L;
        if (messageObject.getPageType() == 43) {
            e(messageObject, longValue, 2);
        } else {
            e(messageObject);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<MessageObject> list) {
        LogUtil.a("UIDV_MessageDetailActivity", "showMessageCenterList");
        this.b.setVisibility(0);
        this.i.d(list);
        this.i.notifyDataSetChanged();
    }

    private void e(MessageObject messageObject) {
        String scheme;
        String host;
        if ("kakaMessage".equals(messageObject.getMsgId())) {
            HashMap hashMap = new HashMap();
            hashMap.put("from", "2");
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SUCCESSES_KAKA_1100007.value(), hashMap, 0);
        }
        Uri parse = Uri.parse(messageObject.getDetailUri());
        if (parse == null || (scheme = parse.getScheme()) == null || (host = parse.getHost()) == null) {
            return;
        }
        if (MessageConstant.NEW_MEDAL_TYPE.equals(messageObject.getType()) || MessageConstant.ACQUIRE_MEDAL_TYPE.equals(messageObject.getType())) {
            HashMap hashMap2 = new HashMap(3);
            hashMap2.put("from", "2");
            hashMap2.put("click", "1");
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.HEALTH_MINE_MY_MEDAL_2040012.value(), hashMap2, 0);
        }
        if ("sportReport".equals(host)) {
            HashMap hashMap3 = new HashMap(3);
            if (health.compact.a.CommonUtil.m(BaseApplication.getContext(), parse.getQueryParameter("report_stype")) == 0) {
                hashMap3.put("report", "1");
            } else {
                hashMap3.put("report", "0");
            }
            hashMap3.put("from", "2");
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SUCCESSES_REPORT_1100009.value(), hashMap3, 0);
        }
        cnU_(parse, scheme);
    }

    private void cnU_(Uri uri, String str) {
        if ("http".equals(str) || ProxyConfig.MATCH_HTTPS.equals(str)) {
            HashMap hashMap = new HashMap(3);
            hashMap.put("click", "1");
            hashMap.put("type", "cloud");
            hashMap.put("from", "2");
            hashMap.put("activityId", uri.getQueryParameter("activityId"));
            ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SUCCESSES_ACTIVITY_1100005.value(), hashMap, 0);
            return;
        }
        HashMap hashMap2 = new HashMap(3);
        hashMap2.put("click", "1");
        hashMap2.put("type", "local");
        hashMap2.put("from", "2");
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.SUCCESSES_ACTIVITY_1100005.value(), hashMap2, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(MessageObject messageObject, long j, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("resourcePositionId", 11001);
        hashMap.put("resourceId", messageObject.getMsgId());
        hashMap.put("resourceName", messageObject.getMsgFrom());
        hashMap.put("itemCardName", messageObject.getMsgTitle());
        hashMap.put("pullOrder", 1);
        if (messageObject.getResBriefInfo() != null) {
            hashMap.put("algId", "");
            hashMap.put("smartRecommend", Boolean.valueOf(messageObject.getResBriefInfo().getSmartRecommend()));
        }
        if (i == 2) {
            hashMap.put("durationTime", Long.valueOf(System.currentTimeMillis() - j));
        }
        hashMap.put("event", Integer.valueOf(i));
        ixx.d().d(this, AnalyticsValue.MARKETING_RESOURCE.value(), hashMap, 0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.e = true;
        this.h.clear();
        super.onDestroy();
    }

    private int a() {
        int i = this.d + 1;
        this.d = i;
        if (i >= Integer.MAX_VALUE) {
            this.d = 0;
        }
        return this.d;
    }

    private void b(int i, CountDownLatch countDownLatch) {
        if (this.f == null) {
            this.f = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        }
        a aVar = new a(this, countDownLatch, i);
        this.f.getResourceResultInfo(11001).addOnSuccessListener(aVar).addOnFailureListener(aVar);
    }

    static class a implements OnSuccessListener<Map<Integer, ResourceResultInfo>>, OnFailureListener {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<MessageDetailActivity> f8502a;
        private final CountDownLatch b;
        private final int e;

        a(MessageDetailActivity messageDetailActivity, CountDownLatch countDownLatch, int i) {
            this.f8502a = new WeakReference<>(messageDetailActivity);
            this.b = countDownLatch;
            this.e = i;
        }

        @Override // com.huawei.hmf.tasks.OnFailureListener
        public void onFailure(Exception exc) {
            MessageDetailActivity messageDetailActivity = this.f8502a.get();
            if (messageDetailActivity == null || messageDetailActivity.isFinishing() || messageDetailActivity.isDestroyed()) {
                return;
            }
            this.b.countDown();
        }

        @Override // com.huawei.hmf.tasks.OnSuccessListener
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onSuccess(Map<Integer, ResourceResultInfo> map) {
            MessageDetailActivity messageDetailActivity = this.f8502a.get();
            if (messageDetailActivity == null || messageDetailActivity.isFinishing() || messageDetailActivity.isDestroyed()) {
                return;
            }
            messageDetailActivity.h.put(Integer.valueOf(this.e), messageDetailActivity.b(map));
            this.b.countDown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<MessageObject> b(Map<Integer, ResourceResultInfo> map) {
        Map<Integer, ResourceResultInfo> filterMarketingRules;
        ArrayList arrayList = new ArrayList();
        MarketingApi marketingApi = this.f;
        if (marketingApi != null && (filterMarketingRules = marketingApi.filterMarketingRules(map)) != null && filterMarketingRules.containsKey(11001) && filterMarketingRules.get(11001) != null) {
            List<ResourceBriefInfo> resources = filterMarketingRules.get(11001).getResources();
            if (koq.b(resources) || this.e) {
                return arrayList;
            }
            SocialFragmentFactoryApi socialFragmentFactoryApi = (SocialFragmentFactoryApi) Services.a("OperationBundle", SocialFragmentFactoryApi.class);
            if (socialFragmentFactoryApi == null) {
                LogUtil.a("UIDV_MessageDetailActivity", "socialFragmentFactoryApi is null");
                return arrayList;
            }
            String string = getString(R.string._2130843304_res_0x7f0216a8);
            Gson gson = new Gson();
            for (ResourceBriefInfo resourceBriefInfo : resources) {
                if (resourceBriefInfo == null || resourceBriefInfo.getContentType() != 13 || resourceBriefInfo.getContent() == null || TextUtils.isEmpty(resourceBriefInfo.getContent().getContent()) || !socialFragmentFactoryApi.isValidResource(resourceBriefInfo.getEffectiveTime(), resourceBriefInfo.getExpirationTime())) {
                    LogUtil.a("UIDV_MessageDetailActivity", "resource brief info invalid");
                } else {
                    String content = resourceBriefInfo.getContent().getContent();
                    LogUtil.a("UIDV_MessageDetailActivity", " marketing2.0: ", content);
                    InternalLetterTemplate internalLetterTemplate = (InternalLetterTemplate) gson.fromJson(content, InternalLetterTemplate.class);
                    if (internalLetterTemplate == null) {
                        LogUtil.a("UIDV_MessageDetailActivity", "resource template parse exception");
                    } else {
                        arrayList.add(a(resourceBriefInfo, internalLetterTemplate, string));
                    }
                }
            }
        }
        return arrayList;
    }

    private MessageObject a(ResourceBriefInfo resourceBriefInfo, InternalLetterTemplate internalLetterTemplate, String str) {
        MessageObject messageObject = new MessageObject();
        messageObject.setInfoClassify(str);
        messageObject.setWeight(resourceBriefInfo.getPriority());
        messageObject.setMsgId(resourceBriefInfo.getResourceId());
        messageObject.setModule(String.valueOf(14));
        messageObject.setCreateTime(resourceBriefInfo.getEffectiveTime());
        messageObject.setExpireTime(resourceBriefInfo.getExpirationTime());
        messageObject.setMsgFrom(resourceBriefInfo.getResourceName());
        messageObject.setMsgTitle(internalLetterTemplate.getTheme());
        messageObject.setMsgContent(internalLetterTemplate.getSummary());
        messageObject.setHarmonyImgUrl(internalLetterTemplate.getPicture());
        messageObject.setImgUri(internalLetterTemplate.getPicture());
        messageObject.setImgBigUri(internalLetterTemplate.getPicture());
        messageObject.setMsgPosition(11);
        messageObject.setPosition(1);
        messageObject.setDetailUri(internalLetterTemplate.getLinkValue());
        messageObject.setHuid(SharedPreferenceUtil.getInstance(this).getUserID());
        messageObject.setReceiveTime(resourceBriefInfo.getModifyTime());
        messageObject.setPageType(43);
        return messageObject;
    }

    static class d implements OnItemVisibleListener<View, MessageObject> {
        private final WeakReference<MessageDetailActivity> d;

        d(MessageDetailActivity messageDetailActivity) {
            this.d = new WeakReference<>(messageDetailActivity);
        }

        @Override // com.huawei.pluginmessagecenter.listener.OnItemVisibleListener
        /* renamed from: cnW_, reason: merged with bridge method [inline-methods] */
        public void onItemVisible(View view, int i, MessageObject messageObject) {
            if (messageObject == null) {
                LogUtil.b("UIDV_MessageDetailActivity", "onItemClicked Wrong Position: ", Integer.valueOf(i));
                return;
            }
            MessageDetailActivity messageDetailActivity = this.d.get();
            if (messageDetailActivity == null || messageDetailActivity.isFinishing() || messageDetailActivity.isDestroyed()) {
                return;
            }
            messageDetailActivity.e(messageObject, 0L, 1);
        }
    }

    private void c() {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        ixx.d().d(com.huawei.haf.application.BaseApplication.e(), AnalyticsValue.VIP_MESSAGES.value(), hashMap, 0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
