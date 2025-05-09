package com.huawei.ui.main.stories.discover.provider;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.marketing.datatype.RecommendResourceInfo;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ResultCallback;
import com.huawei.ui.main.stories.discover.provider.MemberCardManager;
import defpackage.gmz;
import defpackage.koq;
import defpackage.rhv;
import defpackage.rub;
import health.compact.a.HiBroadcastManager;
import health.compact.a.SharedPreferenceManager;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes9.dex */
public class MemberCardManager {
    private Context e;
    private boolean c = false;
    private Map<String, MemberBaseCardReader> b = new ConcurrentHashMap();
    private boolean i = false;
    private boolean d = false;
    private rhv j = new rhv();

    /* renamed from: a, reason: collision with root package name */
    private BroadcastReceiver f9703a = new BroadcastReceiver() { // from class: com.huawei.ui.main.stories.discover.provider.MemberCardManager.1
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, final Intent intent) {
            LogUtil.a("VIPCard_MemberCardManager", "enter mCloudAsycReceiver");
            if (intent == null) {
                LogUtil.b("VIPCard_MemberCardManager", "intent is null");
            } else if (intent.getIntExtra("com.huawei.hihealth.action_sync_status", 6) == 2) {
                ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.main.stories.discover.provider.MemberCardManager.1.2
                    @Override // java.lang.Runnable
                    public void run() {
                        MemberCardManager.this.doE_(intent);
                    }
                });
            }
        }
    };

    public MemberCardManager(Context context) {
        LogUtil.a("VIPCard_MemberCardManager", "MemberCardManager");
        this.e = context;
        a("vipPageSleep", new MemberSleepCardReader(this.e));
        a("vipPageBMI", new MemberWeightCardReader(this.e));
        a("vipPageFitness", new MemberSportsCardReader(this.e));
        d();
    }

    private void d() {
        LogUtil.a("VIPCard_MemberCardManager", "registerSyncStatus");
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.hihealth.action_sync");
        HiBroadcastManager.bwj_(this.e, this.f9703a, intentFilter);
    }

    public void b(List<RecommendResourceInfo> list, MemberCardListener memberCardListener) {
        if (koq.b(list)) {
            LogUtil.a("VIPCard_MemberCardManager", "recommendResource is empty");
            return;
        }
        HashMap hashMap = new HashMap();
        ArrayList<String> arrayList = new ArrayList();
        for (Map.Entry<String, MemberBaseCardReader> entry : this.b.entrySet()) {
            hashMap.put(entry.getKey(), new ArrayList());
            arrayList.add(entry.getKey());
        }
        for (RecommendResourceInfo recommendResourceInfo : list) {
            for (String str : arrayList) {
                if (recommendResourceInfo.getLabelScenario() != null && recommendResourceInfo.getLabelScenario().contains(str)) {
                    ((List) hashMap.get(str)).add(recommendResourceInfo);
                }
            }
        }
        for (String str2 : arrayList) {
            if (koq.b((Collection) hashMap.get(str2))) {
                hashMap.remove(str2);
                LogUtil.a("VIPCard_MemberCardManager", "remove label ", str2);
            }
        }
        for (Map.Entry entry2 : hashMap.entrySet()) {
            MemberBaseCardReader memberBaseCardReader = this.b.get(entry2.getKey());
            if (memberBaseCardReader != null) {
                LogUtil.a("VIPCard_MemberCardManager", "reader key = ", entry2.getKey());
                memberBaseCardReader.registerCardListener(memberCardListener);
                memberBaseCardReader.initCardReader((List) entry2.getValue());
            }
        }
        this.c = true;
    }

    public void c() {
        LogUtil.a("VIPCard_MemberCardManager", "release");
        Iterator<Map.Entry<String, MemberBaseCardReader>> it = this.b.entrySet().iterator();
        while (it.hasNext()) {
            it.next().getValue().unRegisterListener();
        }
        this.b.clear();
        this.c = false;
        HiBroadcastManager.bwm_(this.e, this.f9703a);
    }

    public void b() {
        LogUtil.a("VIPCard_MemberCardManager", "preProcess");
        ThreadPoolManager.d().execute(new Runnable() { // from class: pgg
            @Override // java.lang.Runnable
            public final void run() {
                MemberCardManager.this.a();
            }
        });
    }

    public /* synthetic */ void a() {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        b(countDownLatch);
        try {
            countDownLatch.await(2000L, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            LogUtil.b("VIPCard_MemberCardManager", "InterruptedException");
        }
        e();
    }

    private void e() {
        for (Map.Entry<String, MemberBaseCardReader> entry : this.b.entrySet()) {
            entry.getValue().setPrivacyStatus(this.i, this.d, this.j);
            entry.getValue().queryLabel();
        }
    }

    static class a implements ResultCallback<rhv> {

        /* renamed from: a, reason: collision with root package name */
        private CountDownLatch f9705a;
        private WeakReference<MemberCardManager> c;

        a(MemberCardManager memberCardManager, CountDownLatch countDownLatch) {
            this.c = new WeakReference<>(memberCardManager);
            this.f9705a = countDownLatch;
        }

        @Override // com.huawei.networkclient.ResultCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onSuccess(rhv rhvVar) {
            MemberCardManager memberCardManager = this.c.get();
            if (memberCardManager != null && rhvVar != null) {
                memberCardManager.j = rhvVar;
                CountDownLatch countDownLatch = this.f9705a;
                if (countDownLatch != null) {
                    countDownLatch.countDown();
                }
                LogUtil.a("VIPCard_MemberCardManager", "queryUserLabelConfig onSuccess ", HiJsonUtil.e(rhvVar));
                return;
            }
            LogUtil.b("VIPCard_MemberCardManager", "manager is null or GetLabelsRsp is null");
        }

        @Override // com.huawei.networkclient.ResultCallback
        public void onFailure(Throwable th) {
            CountDownLatch countDownLatch = this.f9705a;
            if (countDownLatch != null) {
                countDownLatch.countDown();
            }
            LogUtil.h("VIPCard_MemberCardManager", "queryUserLabelConfig onFailure ", th.getMessage());
        }
    }

    private void b(CountDownLatch countDownLatch) {
        this.d = false;
        this.i = false;
        this.j = new rhv();
        String b = SharedPreferenceManager.b(this.e, Integer.toString(10000), "health_product_recommend");
        if ("1".equals(b)) {
            this.d = true;
        }
        String c = gmz.d().c(12);
        String b2 = SharedPreferenceManager.b(this.e, Integer.toString(10000), "personalized_recommend");
        if ("true".equals(c) || "1".equals(b2)) {
            this.i = true;
        }
        rub.a(1, new a(this, countDownLatch));
        LogUtil.a("VIPCard_MemberCardManager", "privacyHealthData:", b, "personalInitValue:", c, "personalSwitchValue:", b2);
    }

    private void a(String str, Object obj) {
        if (obj instanceof MemberBaseCardReader) {
            if (this.b.get(str) == null) {
                this.b.put(str, (MemberBaseCardReader) obj);
                return;
            }
            return;
        }
        LogUtil.a("VIPCard_MemberCardManager", "other types waiting to add");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doE_(Intent intent) {
        int intExtra = intent.getIntExtra("com.huawei.hihealth.action_sync_status", 6);
        if ((intExtra == 1000 || intExtra == 2) && this.c) {
            LogUtil.a("VIPCard_MemberCardManager", "queryLabel after finish asyc cloud");
            for (Map.Entry<String, MemberBaseCardReader> entry : this.b.entrySet()) {
                entry.getValue().setDataChangeTrue();
                entry.getValue().queryLabel();
            }
        }
    }
}
