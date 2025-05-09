package com.huawei.pluginmessagecenter.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
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
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.login.ui.login.util.SharedPreferenceUtil;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.utils.OperationUtilsApi;
import com.huawei.pluginachievement.AchieveDataApi;
import com.huawei.pluginmessagecenter.adapter.MessageCenterListAdapter;
import com.huawei.pluginmessagecenter.provider.data.MessageChangeEvent;
import com.huawei.pluginmessagecenter.service.MessageObserver;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.progressbar.HealthProgressBar;
import com.huawei.ui.main.stories.soical.SocialFragmentFactoryApi;
import defpackage.ixx;
import defpackage.koq;
import defpackage.mqw;
import defpackage.mrc;
import defpackage.mrq;
import defpackage.mrr;
import defpackage.nsn;
import health.compact.a.Services;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public class MessageCenterActivity extends BaseActivity {
    private AchieveDataApi b;
    private String c;
    private ExecutorService h;
    private ListView i;
    private HealthProgressBar k;
    private MessageCenterListAdapter l;
    private MarketingApi m;
    private LinearLayout n;
    private LinearLayout o;
    private RelativeLayout p;
    private String r;
    private boolean g = false;
    private boolean j = false;
    private boolean f = false;
    private final AtomicInteger d = new AtomicInteger(Integer.MAX_VALUE);
    private volatile boolean e = false;
    private Map<Integer, List<MessageObject>> s = new ConcurrentHashMap(16);
    private e t = new e(this);
    private List<MessageObject> q = new ArrayList(16);

    /* renamed from: a, reason: collision with root package name */
    private List<MessageObject> f8496a = new ArrayList(16);
    private Handler y = new Handler() { // from class: com.huawei.pluginmessagecenter.activity.MessageCenterActivity.5
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            int i = message.what;
            if (i == 0) {
                LogUtil.a("UIDV_MessageCenterActivity", "refresh message");
                MessageCenterActivity.this.c((List<MessageObject>) message.obj);
                MessageCenterActivity.this.e();
                return;
            }
            if (i == 1) {
                MessageCenterActivity.this.k();
                MessageCenterActivity.this.e();
            } else if (i == 2) {
                MessageCenterActivity.this.e();
            } else {
                if (i != 3) {
                    return;
                }
                MessageCenterActivity.this.h();
            }
        }
    };

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ReleaseLogUtil.e("UIDV_MessageCenterActivity", "onCreate to enter");
        setContentView(R.layout.activity_message_center_list);
        getWindow().setBackgroundDrawable(null);
        a();
        this.i = (ListView) findViewById(R.id.items_listView1);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.hw_messagecenter_loading);
        this.o = linearLayout;
        HealthProgressBar healthProgressBar = (HealthProgressBar) linearLayout.findViewById(R.id.hw_messagecenter_loading_hpb);
        this.k = healthProgressBar;
        healthProgressBar.setLayerType(1, null);
        this.f = ((OperationUtilsApi) Services.c("PluginOperation", OperationUtilsApi.class)).isOperation(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010));
        if (!Utils.o() || this.f) {
            mqw.b(this).c(this.t);
        }
        this.p = (RelativeLayout) findViewById(R.id.messageCenter_layout_no_message);
        f();
        this.g = false;
        MessageCenterListAdapter messageCenterListAdapter = new MessageCenterListAdapter(this, new ArrayList());
        this.l = messageCenterListAdapter;
        this.i.setAdapter((ListAdapter) messageCenterListAdapter);
        i();
        this.h = Executors.newSingleThreadExecutor();
        int c2 = c();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        this.h.execute(new b(this, c2, countDownLatch));
        d(c2, countDownLatch);
        if (this.y != null) {
            LogUtil.a("UIDV_MessageCenterActivity", "send delay message");
            this.y.removeMessages(2);
            this.y.sendEmptyMessageDelayed(2, OpAnalyticsConstants.H5_LOADING_DELAY);
        }
        this.b = (AchieveDataApi) Services.a("PluginAchievement", AchieveDataApi.class);
        this.n = (LinearLayout) findViewById(R.id.msg_marketing_layout);
        final MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        marketingApi.getResourceResultInfo(4170).addOnSuccessListener(new OnSuccessListener<Map<Integer, ResourceResultInfo>>() { // from class: com.huawei.pluginmessagecenter.activity.MessageCenterActivity.3
            @Override // com.huawei.hmf.tasks.OnSuccessListener
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(Map<Integer, ResourceResultInfo> map) {
                List<View> marketingViewList = marketingApi.getMarketingViewList(BaseApplication.getContext(), marketingApi.filterMarketingRules(map));
                LogUtil.a("UIDV_MessageCenterActivity", "testmarketing viewList.SIZE : ", Integer.valueOf(marketingViewList.size()));
                Iterator<View> it = marketingViewList.iterator();
                while (it.hasNext()) {
                    MessageCenterActivity.this.n.addView(it.next());
                }
            }
        });
        LogUtil.a("UIDV_MessageCenterActivity", "Leave onCreate!");
    }

    private void f() {
        this.i.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.huawei.pluginmessagecenter.activity.MessageCenterActivity.4
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                if (nsn.cLk_(view)) {
                    LogUtil.h("UIDV_MessageCenterActivity", "click too fast");
                    ViewClickInstrumentation.clickOnListView(adapterView, view, i);
                    return;
                }
                LogUtil.a("UIDV_MessageCenterActivity", "start onItemClick position = ", Integer.valueOf(i));
                MessageObject messageObject = adapterView.getItemAtPosition(i) instanceof MessageObject ? (MessageObject) adapterView.getItemAtPosition(i) : null;
                if (messageObject != null) {
                    MessageCenterActivity.this.b(messageObject, i);
                    ViewClickInstrumentation.clickOnListView(adapterView, view, i);
                } else {
                    LogUtil.h("UIDV_MessageCenterActivity", "messageObject is null");
                    ViewClickInstrumentation.clickOnListView(adapterView, view, i);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(MessageObject messageObject, int i) {
        LogUtil.a("UIDV_MessageCenterActivity", "onItemClick position = ", Integer.valueOf(i), " messageObject = ", messageObject.getMsgId(), "type = ", messageObject.getType());
        Intent intent = new Intent();
        intent.putExtra("msgId", messageObject.getMsgId());
        intent.putExtra(CommonUtil.DETAIL_URI, messageObject.getDetailUri());
        intent.putExtra("imagUri", messageObject.getImgUri());
        intent.putExtra("module", messageObject.getModule());
        if (String.valueOf(70).equals(messageObject.getModule())) {
            intent.putExtra("messages", this.r);
            b();
            j();
        }
        if (String.valueOf(80).equals(messageObject.getModule())) {
            intent.putExtra("asset", this.c);
            g();
        }
        intent.setClass(this, MessageDetailActivity.class);
        startActivity(intent);
        LogUtil.a("UIDV_MessageCenterActivity", "Leave onItemClick position = ", Integer.valueOf(i));
    }

    private void j() {
        LogUtil.a("UIDV_MessageCenterActivity", "enter setLocalMessageRead");
        if (koq.c(this.q)) {
            for (MessageObject messageObject : this.q) {
                if (messageObject.getReadFlag() == 0) {
                    messageObject.setReadFlag(1);
                }
            }
            m();
        }
    }

    private void g() {
        LogUtil.a("UIDV_MessageCenterActivity", "enter setAssetMessageRead");
        if (koq.c(this.f8496a)) {
            for (MessageObject messageObject : this.f8496a) {
                if (messageObject.getReadFlag() == 0) {
                    messageObject.setReadFlag(1);
                }
            }
            m();
        }
    }

    private void a() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.h("UIDV_MessageCenterActivity", "getData intent is null");
            return;
        }
        this.r = intent.getExtras() != null ? intent.getExtras().getString("messages") : intent.getStringExtra("messages");
        this.c = intent.getExtras() != null ? intent.getExtras().getString("asset") : intent.getStringExtra("asset");
        try {
            this.q = (List) new Gson().fromJson(this.r, new TypeToken<List<MessageObject>>() { // from class: com.huawei.pluginmessagecenter.activity.MessageCenterActivity.2
            }.getType());
            this.f8496a = (List) new Gson().fromJson(this.c, new TypeToken<List<MessageObject>>() { // from class: com.huawei.pluginmessagecenter.activity.MessageCenterActivity.1
            }.getType());
        } catch (JsonParseException unused) {
            LogUtil.a("UIDV_MessageCenterActivity", "getData JsonParseException");
        }
    }

    private void i() {
        this.i.setVisibility(8);
        this.p.setVisibility(8);
        this.o.setVisibility(0);
        this.k.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        this.o.setVisibility(8);
        this.k.setVisibility(8);
    }

    private int c() {
        return this.d.incrementAndGet();
    }

    /* loaded from: classes8.dex */
    static class b implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private final int f8498a;
        private final WeakReference<MessageCenterActivity> b;
        private final CountDownLatch e;

        b(MessageCenterActivity messageCenterActivity, int i, CountDownLatch countDownLatch) {
            this.b = new WeakReference<>(messageCenterActivity);
            this.f8498a = i;
            this.e = countDownLatch;
        }

        @Override // java.lang.Runnable
        public void run() {
            MessageCenterActivity messageCenterActivity = this.b.get();
            if (messageCenterActivity == null || messageCenterActivity.isDestroyed() || messageCenterActivity.isFinishing()) {
                return;
            }
            messageCenterActivity.c(this.f8498a, this.e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, CountDownLatch countDownLatch) {
        boolean isOperation = ((OperationUtilsApi) Services.c("PluginOperation", OperationUtilsApi.class)).isOperation(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010));
        if (!Utils.o() || isOperation) {
            LogUtil.a("UIDV_MessageCenterActivity", "Start messageCenterRunnable!");
            List<MessageObject> e2 = e(i, countDownLatch);
            if (!e2.isEmpty()) {
                LogUtil.a("UIDV_MessageCenterActivity", "messageObjectList size = ", Integer.valueOf(e2.size()));
                if (this.y != null) {
                    if (e2.size() <= 0 && d() == null) {
                        this.y.sendEmptyMessage(1);
                    } else {
                        Handler handler = this.y;
                        handler.sendMessage(handler.obtainMessage(0, i, 0, e2));
                    }
                    LogUtil.a("UIDV_MessageCenterActivity", "showReport_MessageCenter generateReportHealthData.");
                    AchieveDataApi achieveDataApi = (AchieveDataApi) Services.a("PluginAchievement", AchieveDataApi.class);
                    if (achieveDataApi != null) {
                        achieveDataApi.generateReportHealthData(getApplicationContext());
                    } else {
                        LogUtil.a("UIDV_MessageCenterActivity", "handleMessageCenter achieveDetailApi is null");
                    }
                }
                LogUtil.a("UIDV_MessageCenterActivity", "Leave messageCenterRunnable!");
                return;
            }
            LogUtil.a("UIDV_MessageCenterActivity", "dorefresh");
            mqw.b(this).d();
        }
    }

    private List<MessageObject> e(int i, CountDownLatch countDownLatch) {
        List<MessageObject> b2 = mqw.b(this).b();
        a(b2);
        d(b2);
        countDownLatch.countDown();
        if (countDownLatch.getCount() < 2) {
            try {
                LogUtil.a("UIDV_MessageCenterActivity", "handleMessageCenter reached = ", Boolean.valueOf(countDownLatch.await(PreConnectManager.CONNECT_INTERNAL, TimeUnit.MILLISECONDS)));
            } catch (InterruptedException e2) {
                LogUtil.b("UIDV_MessageCenterActivity", e2.getMessage());
            }
        }
        List<MessageObject> remove = this.s.remove(Integer.valueOf(i));
        if (koq.c(remove)) {
            LogUtil.a("UIDV_MessageCenterActivity", "theOtherMsgList size: ", Integer.valueOf(remove.size()));
            b2.addAll(remove);
        }
        if (koq.c(this.q)) {
            LogUtil.a("UIDV_MessageCenterActivity", "mMessageObjects.size:", Integer.valueOf(this.q.size()));
            b2.addAll(this.q);
        } else {
            LogUtil.a("UIDV_MessageCenterActivity", "mMessageObjects is empty");
        }
        if (koq.c(this.f8496a)) {
            LogUtil.a("UIDV_MessageCenterActivity", "mAssetMessageObjects.size:", Integer.valueOf(this.f8496a.size()));
            b2.addAll(this.f8496a);
        } else {
            LogUtil.a("UIDV_MessageCenterActivity", "mAssetMessageObjects is empty");
        }
        MessageObject d = d();
        if (d != null) {
            boolean e3 = mrc.e(BaseApplication.getContext()).e(d);
            b2.add(d);
            LogUtil.a("UIDV_MessageCenterActivity", "showMessageCenterList, insert KakaMessages to DB success is: ", Boolean.valueOf(e3));
        }
        List<MessageObject> b3 = mqw.b(this).b(mrq.c(b2));
        LogUtil.a("UIDV_MessageCenterActivity", "mixedMessage resultMessages: ", b3);
        return b3;
    }

    private void a(List<MessageObject> list) {
        if (!health.compact.a.CommonUtil.as()) {
            LogUtil.h("UIDV_MessageCenterActivity", "version is not beta");
            return;
        }
        if (koq.b(list)) {
            LogUtil.h("UIDV_MessageCenterActivity", "filterUpdateAppMsg, messageObjects is empty");
            return;
        }
        Iterator<MessageObject> it = list.iterator();
        while (it.hasNext()) {
            MessageObject next = it.next();
            if (next == null) {
                LogUtil.h("UIDV_MessageCenterActivity", "filterUpdateAppMsg, msgObj is null");
            } else if ("device_app_update".equals(next.getType()) && String.valueOf(19).equals(next.getModule())) {
                it.remove();
                mqw.b(BaseApplication.getContext()).e(next.getMsgId());
            }
        }
    }

    private void d(List<MessageObject> list) {
        if (koq.b(list)) {
            LogUtil.h("UIDV_MessageCenterActivity", "resetBenefitMsgReceiveTime, messageObjects is empty");
            return;
        }
        for (MessageObject messageObject : list) {
            if (messageObject == null) {
                LogUtil.h("UIDV_MessageCenterActivity", "resetBenefitMsgReceiveTime, msgObj is null");
            } else if (String.valueOf(90).equals(messageObject.getModule())) {
                messageObject.setReceiveTime(messageObject.getCreateTime());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        LogUtil.a("UIDV_MessageCenterActivity", "removeKakaMessage");
        if (d() == null) {
            LogUtil.a("UIDV_MessageCenterActivity", "removeKakaMessage = null");
            this.l.c();
            this.l.notifyDataSetChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        LogUtil.a("UIDV_MessageCenterActivity", "showNoMessages");
        this.i.setVisibility(8);
        this.p.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<MessageObject> list) {
        LogUtil.a("UIDV_MessageCenterActivity", "showMessageCenterList");
        ArrayList arrayList = new ArrayList(list.size());
        arrayList.addAll(list);
        this.i.setVisibility(0);
        this.p.setVisibility(8);
        this.l.d(arrayList);
        this.l.notifyDataSetChanged();
    }

    private MessageObject d() {
        AchieveDataApi achieveDataApi = this.b;
        if (achieveDataApi != null && achieveDataApi.getKakaTaskRedDot(BaseApplication.getContext())) {
            MessageObject messageObject = new MessageObject();
            messageObject.setPosition(1);
            messageObject.setMsgType(2);
            messageObject.setDetailUri("messagecenter://kakaMessage?module=17&type=unclaimedKaka");
            messageObject.setModule(String.valueOf(17));
            messageObject.setMsgTitle(BaseApplication.getContext().getString(R.string._2130847701_res_0x7f0227d5));
            messageObject.setMsgContent(BaseApplication.getContext().getString(R.string._2130843311_res_0x7f0216af));
            messageObject.setExpireTime(0L);
            messageObject.setMsgId("kakaMessage");
            messageObject.setReceiveTime(System.currentTimeMillis());
            messageObject.setReadFlag(0);
            messageObject.setType("unclaimedKaka");
            this.j = true;
            return messageObject;
        }
        this.j = false;
        return null;
    }

    /* loaded from: classes8.dex */
    static class e implements MessageObserver {
        WeakReference<MessageCenterActivity> d;

        e(MessageCenterActivity messageCenterActivity) {
            this.d = new WeakReference<>(messageCenterActivity);
        }

        @Override // com.huawei.pluginmessagecenter.service.MessageObserver
        public void onChange(int i, MessageChangeEvent messageChangeEvent) {
            LogUtil.a("UIDV_MessageCenterActivity", "Enter onChange() flag: ", Integer.valueOf(i));
            MessageCenterActivity messageCenterActivity = this.d.get();
            if (messageCenterActivity != null) {
                if (!Utils.o() || messageCenterActivity.f) {
                    messageCenterActivity.m();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        ExecutorService executorService = this.h;
        if (executorService == null || executorService.isShutdown()) {
            LogUtil.a("UIDV_MessageCenterActivity", "executorService is shutdown");
            this.h = Executors.newSingleThreadExecutor();
        }
        int c2 = c();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        this.h.execute(new c(this, c2, countDownLatch));
        d(c2, countDownLatch);
    }

    private void n() {
        ExecutorService executorService = this.h;
        if (executorService == null || executorService.isShutdown()) {
            LogUtil.a("UIDV_MessageCenterActivity", "executorService is shutdown");
            this.h = Executors.newSingleThreadExecutor();
        }
        int c2 = c();
        CountDownLatch countDownLatch = new CountDownLatch(2);
        this.h.execute(new c(this, c2, countDownLatch));
        d(c2, countDownLatch);
    }

    /* loaded from: classes8.dex */
    static class c implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private final int f8499a;
        private final WeakReference<MessageCenterActivity> d;
        private final CountDownLatch e;

        c(MessageCenterActivity messageCenterActivity, int i, CountDownLatch countDownLatch) {
            this.d = new WeakReference<>(messageCenterActivity);
            this.f8499a = i;
            this.e = countDownLatch;
        }

        @Override // java.lang.Runnable
        public void run() {
            MessageCenterActivity messageCenterActivity = this.d.get();
            if (messageCenterActivity == null || messageCenterActivity.isFinishing() || messageCenterActivity.isDestroyed()) {
                return;
            }
            try {
                messageCenterActivity.a(this.f8499a, this.e);
            } catch (Exception e) {
                LogUtil.b("UIDV_MessageCenterActivity", "handle message exception ", e.getMessage());
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, CountDownLatch countDownLatch) {
        LogUtil.a("UIDV_MessageCenterActivity", "handleMessageList");
        List<MessageObject> e2 = e(i, countDownLatch);
        LogUtil.a("UIDV_MessageCenterActivity", "onChange() getMessageList.size:", Integer.valueOf(e2.size()));
        if (this.y != null) {
            if (e2.size() <= 0 && d() == null) {
                this.y.sendEmptyMessage(1);
                return;
            } else {
                Handler handler = this.y;
                handler.sendMessage(handler.obtainMessage(0, i, 0, e2));
                return;
            }
        }
        LogUtil.a("UIDV_MessageCenterActivity", "messageCenterHandler is null");
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.g && (!Utils.o() || this.f)) {
            if (this.j) {
                AchieveDataApi achieveDataApi = this.b;
                if (achieveDataApi != null) {
                    achieveDataApi.dealKakaTask(BaseApplication.getContext(), this.y, 3);
                } else {
                    LogUtil.b("UIDV_MessageCenterActivity", "onResume mAchieveDataApi is null.");
                }
            }
            mqw.b(this).d();
        }
        if (!Utils.o() || this.f) {
            return;
        }
        n();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        this.g = true;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.e = true;
        super.onDestroy();
        if (!Utils.o() && this.f) {
            mqw.b(this).a(this.t);
        }
        this.y.removeMessages(2);
        e();
        this.s.clear();
    }

    private void d(int i, CountDownLatch countDownLatch) {
        if (this.m == null) {
            this.m = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        }
        a aVar = new a(this, countDownLatch, i);
        this.m.getResourceResultInfo(11001).addOnSuccessListener(aVar).addOnFailureListener(aVar);
    }

    /* loaded from: classes8.dex */
    static class a implements OnSuccessListener<Map<Integer, ResourceResultInfo>>, OnFailureListener {
        private final CountDownLatch b;
        private final int c;
        private final WeakReference<MessageCenterActivity> e;

        a(MessageCenterActivity messageCenterActivity, CountDownLatch countDownLatch, int i) {
            this.e = new WeakReference<>(messageCenterActivity);
            this.b = countDownLatch;
            this.c = i;
        }

        @Override // com.huawei.hmf.tasks.OnFailureListener
        public void onFailure(Exception exc) {
            MessageCenterActivity messageCenterActivity = this.e.get();
            if (messageCenterActivity == null || messageCenterActivity.isFinishing() || messageCenterActivity.isDestroyed()) {
                return;
            }
            this.b.countDown();
        }

        @Override // com.huawei.hmf.tasks.OnSuccessListener
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onSuccess(Map<Integer, ResourceResultInfo> map) {
            MessageCenterActivity messageCenterActivity = this.e.get();
            if (messageCenterActivity == null || messageCenterActivity.isFinishing() || messageCenterActivity.isDestroyed()) {
                return;
            }
            messageCenterActivity.s.put(Integer.valueOf(this.c), messageCenterActivity.d(map));
            this.b.countDown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<MessageObject> d(Map<Integer, ResourceResultInfo> map) {
        Map<Integer, ResourceResultInfo> filterMarketingRules;
        ArrayList arrayList = new ArrayList();
        MarketingApi marketingApi = this.m;
        if (marketingApi != null && (filterMarketingRules = marketingApi.filterMarketingRules(map)) != null && filterMarketingRules.containsKey(11001) && filterMarketingRules.get(11001) != null) {
            List<ResourceBriefInfo> resources = filterMarketingRules.get(11001).getResources();
            if (koq.b(resources) || this.e) {
                return arrayList;
            }
            Gson gson = new Gson();
            SocialFragmentFactoryApi socialFragmentFactoryApi = (SocialFragmentFactoryApi) Services.a("OperationBundle", SocialFragmentFactoryApi.class);
            if (socialFragmentFactoryApi == null) {
                LogUtil.a("UIDV_MessageCenterActivity", "socialFragmentFactoryApi is null");
                return arrayList;
            }
            for (ResourceBriefInfo resourceBriefInfo : resources) {
                if (resourceBriefInfo == null || resourceBriefInfo.getContentType() != 13 || resourceBriefInfo.getContent() == null || TextUtils.isEmpty(resourceBriefInfo.getContent().getContent()) || !socialFragmentFactoryApi.isValidResource(resourceBriefInfo.getEffectiveTime(), resourceBriefInfo.getExpirationTime())) {
                    LogUtil.a("UIDV_MessageCenterActivity", "resource brief info invalid");
                } else {
                    String content = resourceBriefInfo.getContent().getContent();
                    LogUtil.a("UIDV_MessageCenterActivity", " marketing2.0: ", content);
                    InternalLetterTemplate internalLetterTemplate = (InternalLetterTemplate) gson.fromJson(content, InternalLetterTemplate.class);
                    if (internalLetterTemplate == null) {
                        LogUtil.a("UIDV_MessageCenterActivity", "resource template parse exception");
                    } else {
                        arrayList.add(a(resourceBriefInfo, internalLetterTemplate));
                    }
                }
            }
        }
        return arrayList;
    }

    private MessageObject a(ResourceBriefInfo resourceBriefInfo, InternalLetterTemplate internalLetterTemplate) {
        MessageObject messageObject = new MessageObject();
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
        String valueOf = String.valueOf(0);
        messageObject.setReadFlag(!TextUtils.equals(mrr.c(resourceBriefInfo.getResourceId(), valueOf), valueOf) ? 1 : 0);
        return messageObject;
    }

    private void b() {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        ixx.d().d(com.huawei.haf.application.BaseApplication.e(), AnalyticsValue.VIP_MESSAGE_CLICK.value(), hashMap, 0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
