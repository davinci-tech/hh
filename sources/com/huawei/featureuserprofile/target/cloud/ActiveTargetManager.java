package com.huawei.featureuserprofile.target.cloud;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Resources;
import android.text.TextUtils;
import com.huawei.basefitnessadvice.model.FitnessSummaryRecord;
import com.huawei.basefitnessadvice.model.FitnessTrackRecord;
import com.huawei.featureuserprofile.target.cloud.ActiveTargetManager;
import com.huawei.featureuserprofile.target.js.ActiveTargetService;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.design.pattern.Observer;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.courseplanservice.api.RecordApi;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.h5pro.service.H5ProServiceManager;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSportStatDataAggregateOption;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.healthdatacloud.HealthDataCloudFactory;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ResultCallback;
import com.huawei.openalliance.ad.constant.ParamConstants;
import defpackage.buo;
import defpackage.bup;
import defpackage.buq;
import defpackage.bus;
import defpackage.but;
import defpackage.bzs;
import defpackage.eme;
import defpackage.jah;
import defpackage.jdl;
import defpackage.koq;
import defpackage.kwy;
import defpackage.lqi;
import defpackage.lql;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.ToIntFunction;

/* loaded from: classes.dex */
public class ActiveTargetManager {
    private static final Object c = new Object();
    private static final int[] d = {0, 1, 2, 3, 4, 5};
    private static volatile ActiveTargetManager e = null;

    /* renamed from: a, reason: collision with root package name */
    private final MessageCenterApi f2037a;
    private String b;
    private final RecordApi f;
    private d h;
    private final HealthDataCloudFactory i;
    private c j;

    /* loaded from: classes3.dex */
    public interface ManagerCallback {
        void onSuccess(buo buoVar);
    }

    private boolean a(double d2, double d3, double d4) {
        if (d3 == 0.0d) {
            return false;
        }
        if (d4 > d2) {
            if (d3 > d2) {
                return false;
            }
        } else if (d3 < d2) {
            return false;
        }
        return true;
    }

    private ActiveTargetManager() {
        this.b = "";
        LogUtil.a("ActiveTargetManager", "ActiveTargetManager");
        this.i = new HealthDataCloudFactory(BaseApplication.e());
        this.b = LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1011);
        this.f = (RecordApi) Services.c("CoursePlanService", RecordApi.class);
        this.f2037a = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
    }

    public static ActiveTargetManager e() {
        if (e == null) {
            synchronized (c) {
                if (e == null) {
                    e = new ActiveTargetManager();
                }
            }
        }
        return e;
    }

    public boolean b() {
        if (!Utils.o() && !CommonUtil.bu()) {
            String e2 = jah.c().e("status_show_active_target");
            LogUtil.a("ActiveTargetManager", "isActiveTargetSwitchOpen , support = ", e2);
            if (!TextUtils.isEmpty(e2)) {
                if (this.j == null) {
                    c cVar = new c();
                    this.j = cVar;
                    ObserverManagerUtil.d(cVar, "observer_sport_list_data_change");
                }
                if ("0".equals(e2)) {
                    return true;
                }
                if (!CommonUtil.bv() && "1".equals(e2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void b(Context context) {
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        builder.addPath("#/?from=1");
        H5ProServiceManager.getInstance().registerService(ActiveTargetService.class);
        bzs.e().loadH5ProApp(context, "com.huawei.health.h5.my-annual-flag", builder);
    }

    public void c(final IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("ActiveTargetManager", "enter getActiveTargetStatus");
        ThreadPoolManager.d().execute(new Runnable() { // from class: buj
            @Override // java.lang.Runnable
            public final void run() {
                ActiveTargetManager.this.d(iBaseResponseCallback);
            }
        });
    }

    public /* synthetic */ void d(final IBaseResponseCallback iBaseResponseCallback) {
        bup bupVar = new bup();
        lqi.d().b(bupVar.getUrl(), this.i.getHeaders(), lql.b(this.i.getBody(bupVar)), buq.class, new ResultCallback<buq>() { // from class: com.huawei.featureuserprofile.target.cloud.ActiveTargetManager.5
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(buq buqVar) {
                ReleaseLogUtil.e("R_ActiveTargetManager", "getActiveTargetStatus onSuccess");
                LogUtil.a("ActiveTargetManager", "getActiveTargetStatus data is ", buqVar);
                ActiveTargetManager.this.c(buqVar, iBaseResponseCallback);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                ReleaseLogUtil.d("R_ActiveTargetManager", "getActiveTargetStatus throwable ", th.getMessage());
                iBaseResponseCallback.d(-1, -1);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(buq buqVar, IBaseResponseCallback iBaseResponseCallback) {
        List<buo> a2 = buqVar.a();
        int i = -1;
        if (!koq.b(a2)) {
            for (buo buoVar : a2) {
                if (jdl.g(buoVar.f(), System.currentTimeMillis())) {
                    i = Math.max(0, i) + (1 - buoVar.g());
                }
            }
        }
        iBaseResponseCallback.d(0, Integer.valueOf(i));
    }

    public boolean d() {
        int i = 0;
        while (true) {
            int[] iArr = d;
            if (i < iArr.length) {
                String b = SharedPreferenceManager.b(BaseApplication.e(), Integer.toString(10051), f(iArr[i]));
                LogUtil.a("ActiveTargetManager", f(iArr[i]), ", value = ", b);
                if (ParamConstants.CallbackMethod.ON_SHOW.equals(b)) {
                    return true;
                }
                i++;
            } else {
                return ParamConstants.CallbackMethod.ON_SHOW.equals(SharedPreferenceManager.b(BaseApplication.e(), Integer.toString(10051), "key_target_red_need_show"));
            }
        }
    }

    public void c() {
        Context e2 = BaseApplication.e();
        int i = 0;
        while (true) {
            int[] iArr = d;
            if (i >= iArr.length) {
                break;
            }
            if (ParamConstants.CallbackMethod.ON_SHOW.equals(SharedPreferenceManager.b(e2, Integer.toString(10051), f(iArr[i])))) {
                LogUtil.a("ActiveTargetManager", f(iArr[i]), ", value = no");
                SharedPreferenceManager.e(e2, Integer.toString(10051), f(iArr[i]), "no", new StorageParams());
            }
            i++;
        }
        if (ParamConstants.CallbackMethod.ON_SHOW.equals(SharedPreferenceManager.b(BaseApplication.e(), Integer.toString(10051), "key_target_red_need_show"))) {
            LogUtil.a("ActiveTargetManager", "key_target_red_need_show value = no");
            SharedPreferenceManager.e(e2, Integer.toString(10051), "key_target_red_need_show", "no", new StorageParams());
        }
    }

    public void h() {
        LogUtil.a("ActiveTargetManager", "enter updateActiveTargets");
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.featureuserprofile.target.cloud.ActiveTargetManager.2
            @Override // java.lang.Runnable
            public void run() {
                bup bupVar = new bup();
                lqi.d().b(bupVar.getUrl(), ActiveTargetManager.this.i.getHeaders(), lql.b(ActiveTargetManager.this.i.getBody(bupVar)), buq.class, new ResultCallback<buq>() { // from class: com.huawei.featureuserprofile.target.cloud.ActiveTargetManager.2.4
                    @Override // com.huawei.networkclient.ResultCallback
                    /* renamed from: b, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(buq buqVar) {
                        ReleaseLogUtil.e("R_ActiveTargetManager", "updateActiveTargets success");
                        LogUtil.a("ActiveTargetManager", "updateActiveTargets data is ", buqVar);
                        List<buo> a2 = buqVar.a();
                        if (koq.b(a2)) {
                            ActiveTargetManager.this.j();
                        } else {
                            ActiveTargetManager.this.e(a2);
                        }
                    }

                    @Override // com.huawei.networkclient.ResultCallback
                    public void onFailure(Throwable th) {
                        ReleaseLogUtil.d("R_ActiveTargetManager", "updateActiveTargets throwable ", th.getMessage());
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        Context e2 = BaseApplication.e();
        if (TextUtils.isEmpty(SharedPreferenceManager.b(e2, Integer.toString(10051), "key_target_red_need_show"))) {
            SharedPreferenceManager.e(e2, Integer.toString(10051), "key_target_red_need_show", ParamConstants.CallbackMethod.ON_SHOW, new StorageParams());
        }
    }

    public void a(final ResultCallback<buq> resultCallback) {
        ReleaseLogUtil.e("R_ActiveTargetManager", "enter getActiveTargets");
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.featureuserprofile.target.cloud.ActiveTargetManager.1
            @Override // java.lang.Runnable
            public void run() {
                bup bupVar = new bup();
                lqi.d().b(bupVar.getUrl(), ActiveTargetManager.this.i.getHeaders(), lql.b(ActiveTargetManager.this.i.getBody(bupVar)), buq.class, new ResultCallback<buq>() { // from class: com.huawei.featureuserprofile.target.cloud.ActiveTargetManager.1.4
                    @Override // com.huawei.networkclient.ResultCallback
                    /* renamed from: a, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(buq buqVar) {
                        ReleaseLogUtil.e("R_ActiveTargetManager", "getActiveTargets onSuccess");
                        List<buo> a2 = buqVar.a();
                        if (koq.c(a2)) {
                            buqVar.d(ActiveTargetManager.this.e(a2));
                        }
                        LogUtil.a("ActiveTargetManager", "data is ", buqVar);
                        resultCallback.onSuccess(buqVar);
                    }

                    @Override // com.huawei.networkclient.ResultCallback
                    public void onFailure(Throwable th) {
                        ReleaseLogUtil.e("R_ActiveTargetManager", "getActiveTargets onFailure");
                        resultCallback.onFailure(th);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<buo> e(List<buo> list) {
        final List<buo> synchronizedList = Collections.synchronizedList(new ArrayList(16));
        final CountDownLatch countDownLatch = new CountDownLatch(list.size());
        e(list, new ManagerCallback() { // from class: buf
            @Override // com.huawei.featureuserprofile.target.cloud.ActiveTargetManager.ManagerCallback
            public final void onSuccess(buo buoVar) {
                ActiveTargetManager.d(synchronizedList, countDownLatch, buoVar);
            }
        });
        try {
            countDownLatch.await(3L, TimeUnit.SECONDS);
        } catch (InterruptedException e2) {
            LogUtil.b("ActiveTargetManager", "updateCompleteValue e ", LogAnonymous.b((Throwable) e2));
        }
        a(synchronizedList);
        return synchronizedList;
    }

    public static /* synthetic */ void d(List list, CountDownLatch countDownLatch, buo buoVar) {
        list.add(buoVar);
        countDownLatch.countDown();
    }

    private void a(List<buo> list) {
        if (TextUtils.isEmpty(this.b)) {
            this.b = LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1011);
        }
        final String str = this.b + "is_delete_wrong_target_message_finished";
        if (SharedPreferenceManager.a(Integer.toString(10051), str, false)) {
            LogUtil.a("ActiveTargetManager", "delete wrong message finished");
            return;
        }
        if (koq.b(list)) {
            LogUtil.a("ActiveTargetManager", "list is null");
            SharedPreferenceManager.e(Integer.toString(10051), str, true);
            return;
        }
        HashSet hashSet = new HashSet(Arrays.asList(0, 1, 2, 3, 4, 5));
        for (buo buoVar : list) {
            if (jdl.g(buoVar.f(), System.currentTimeMillis()) && buoVar.g() == 1) {
                hashSet.remove(Integer.valueOf(buoVar.b()));
            }
        }
        if (koq.b(hashSet)) {
            LogUtil.a("ActiveTargetManager", "deleteTypeSet is null");
            SharedPreferenceManager.e(Integer.toString(10051), str, true);
            return;
        }
        final HashSet hashSet2 = new HashSet();
        Iterator it = hashSet.iterator();
        while (it.hasNext()) {
            int intValue = ((Integer) it.next()).intValue();
            SharedPreferenceManager.e(BaseApplication.e(), Integer.toString(10051), f(intValue), "", new StorageParams());
            hashSet2.add(c(intValue));
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: bud
            @Override // java.lang.Runnable
            public final void run() {
                ActiveTargetManager.this.c(str, hashSet2);
            }
        });
    }

    public /* synthetic */ void c(String str, HashSet hashSet) {
        List<MessageObject> messages = this.f2037a.getMessages(String.valueOf(100), MessageConstant.ACTIVE_TARGET_TYPE);
        if (koq.b(messages)) {
            LogUtil.a("ActiveTargetManager", "deleteWrongMessage messageList is empty");
            SharedPreferenceManager.e(Integer.toString(10051), str, true);
            return;
        }
        for (MessageObject messageObject : messages) {
            if (hashSet.contains(messageObject.getMsgContent()) && jdl.g(messageObject.getCreateTime(), System.currentTimeMillis())) {
                LogUtil.a("ActiveTargetManager", "deleteWrongMessage delete message is ", Boolean.valueOf(this.f2037a.deleteMessage(messageObject.getMsgId())));
            }
        }
        SharedPreferenceManager.e(Integer.toString(10051), str, true);
    }

    private void e(List<buo> list, ManagerCallback managerCallback) {
        for (buo buoVar : list) {
            if (System.currentTimeMillis() > buoVar.a()) {
                if (buoVar.b() == 1 && jdl.ab(buoVar.f()) == 2022) {
                    e(buoVar, managerCallback);
                } else {
                    LogUtil.a("ActiveTargetManager", "not current year goalType is ", Integer.valueOf(buoVar.b()), ", status is ", Integer.valueOf(buoVar.g()));
                    managerCallback.onSuccess(buoVar);
                }
            } else if (buoVar.g() == 0) {
                e(buoVar, managerCallback);
            } else if (buoVar.g() == 1) {
                e(buoVar, managerCallback);
            } else {
                LogUtil.a("ActiveTargetManager", "no match patch Type is ", Integer.valueOf(buoVar.b()), ", status is ", Integer.valueOf(buoVar.g()));
                managerCallback.onSuccess(buoVar);
            }
        }
    }

    private void e(buo buoVar, ManagerCallback managerCallback) {
        int b = buoVar.b();
        if (b != 0) {
            if (b == 1) {
                b(buoVar, managerCallback);
                return;
            }
            if (b != 2 && b != 3) {
                if (b == 4) {
                    c(buoVar, managerCallback);
                    return;
                } else if (b != 5) {
                    LogUtil.h("ActiveTargetManager", "not support");
                    managerCallback.onSuccess(buoVar);
                    return;
                }
            }
        }
        b(buoVar, b(buoVar.b()), managerCallback);
    }

    private void b(buo buoVar, ManagerCallback managerCallback) {
        final CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        final AtomicReference atomicReference = new AtomicReference(Double.valueOf(0.0d));
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        c(buoVar.f(), buoVar.a(), new ManagerCallback() { // from class: buk
            @Override // com.huawei.featureuserprofile.target.cloud.ActiveTargetManager.ManagerCallback
            public final void onSuccess(buo buoVar2) {
                ActiveTargetManager.e(copyOnWriteArrayList, atomicReference, countDownLatch, buoVar2);
            }
        });
        d(buoVar.f(), buoVar.a(), new AnonymousClass8(copyOnWriteArrayList, atomicReference, countDownLatch));
        try {
            LogUtil.a("ActiveTargetManager", "readLocalFitnessData isOnTime:", Boolean.valueOf(countDownLatch.await(3L, TimeUnit.SECONDS)));
        } catch (InterruptedException e2) {
            LogUtil.b("ActiveTargetManager", "readLocalFitnessData exception ", LogAnonymous.b((Throwable) e2));
        }
        double doubleValue = ((Double) atomicReference.get()).doubleValue() / 60000.0d;
        List<Long> c2 = c(copyOnWriteArrayList);
        e(buoVar, doubleValue);
        buoVar.d(doubleValue);
        buoVar.c(c2);
        managerCallback.onSuccess(buoVar);
        b(buoVar, doubleValue);
    }

    public static /* synthetic */ void e(List list, AtomicReference atomicReference, CountDownLatch countDownLatch, buo buoVar) {
        list.addAll(buoVar.h());
        atomicReference.set(Double.valueOf(((Double) atomicReference.get()).doubleValue() + buoVar.e()));
        countDownLatch.countDown();
    }

    /* renamed from: com.huawei.featureuserprofile.target.cloud.ActiveTargetManager$8, reason: invalid class name */
    public class AnonymousClass8 implements HiAggregateListener {
        final /* synthetic */ List b;
        final /* synthetic */ CountDownLatch c;
        final /* synthetic */ AtomicReference e;

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
        }

        AnonymousClass8(List list, AtomicReference atomicReference, CountDownLatch countDownLatch) {
            this.b = list;
            this.e = atomicReference;
            this.c = countDownLatch;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            ActiveTargetManager activeTargetManager = ActiveTargetManager.this;
            final List list2 = this.b;
            final AtomicReference atomicReference = this.e;
            final CountDownLatch countDownLatch = this.c;
            activeTargetManager.d(list, new ManagerCallback() { // from class: bur
                @Override // com.huawei.featureuserprofile.target.cloud.ActiveTargetManager.ManagerCallback
                public final void onSuccess(buo buoVar) {
                    ActiveTargetManager.AnonymousClass8.c(list2, atomicReference, countDownLatch, buoVar);
                }
            });
        }

        public static /* synthetic */ void c(List list, AtomicReference atomicReference, CountDownLatch countDownLatch, buo buoVar) {
            list.addAll(buoVar.h());
            atomicReference.set(Double.valueOf(((Double) atomicReference.get()).doubleValue() + buoVar.e()));
            countDownLatch.countDown();
        }
    }

    private List<Long> c(List<Long> list) {
        ArrayList arrayList = new ArrayList();
        for (Long l : list) {
            if (!arrayList.contains(l)) {
                arrayList.add(l);
            }
        }
        return arrayList;
    }

    private void c(long j, long j2, final ManagerCallback managerCallback) {
        this.f.acquireSummaryFitnessRecord(new kwy.a().a(j).e(Math.min(System.currentTimeMillis(), j2)).c(3).d(), new IBaseResponseCallback() { // from class: bug
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                ActiveTargetManager.this.c(managerCallback, i, obj);
            }
        });
    }

    public /* synthetic */ void c(ManagerCallback managerCallback, int i, Object obj) {
        buo buoVar = new buo();
        if (obj == null) {
            LogUtil.h("ActiveTargetManager", "parsingFitnessData objectData is null");
            managerCallback.onSuccess(b(buoVar));
            return;
        }
        if (!koq.e(obj, FitnessTrackRecord.class)) {
            LogUtil.h("ActiveTargetManager", "objectData is not match FitnessTrackRecord");
            managerCallback.onSuccess(b(buoVar));
            return;
        }
        List list = (List) obj;
        if (koq.b(list)) {
            LogUtil.h("ActiveTargetManager", "records is empty");
            managerCallback.onSuccess(b(buoVar));
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        double d2 = 0.0d;
        while (it.hasNext()) {
            arrayList.add(Long.valueOf(((FitnessTrackRecord) it.next()).acquireMonthZeroTime()));
            d2 += r4.acquireSumExerciseTime();
        }
        LogUtil.a("ActiveTargetManager", "sumTime is ", Double.valueOf(d2));
        buoVar.d(d2);
        buoVar.c(arrayList);
        managerCallback.onSuccess(buoVar);
    }

    private void b(final buo buoVar, int i, final ManagerCallback managerCallback) {
        c(buoVar.f(), buoVar.a(), i, new HiAggregateListener() { // from class: com.huawei.featureuserprofile.target.cloud.ActiveTargetManager.10
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i2, List<HiHealthData> list, int i3, int i4) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i2, int i3) {
                LogUtil.a("ActiveTargetManager", "readLocalData datas errorCode=", Integer.valueOf(i2));
                ActiveTargetManager.this.d(list, buoVar, managerCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<HiHealthData> list, buo buoVar, ManagerCallback managerCallback) {
        if (koq.b(list)) {
            LogUtil.a("ActiveTargetManager", "parsingData is empty");
            a(buoVar, managerCallback);
            return;
        }
        LogUtil.a("ActiveTargetManager", "parsingData datas size=", Integer.valueOf(list.size()));
        ArrayList arrayList = new ArrayList();
        double d2 = 0.0d;
        for (HiHealthData hiHealthData : list) {
            arrayList.add(Long.valueOf(hiHealthData.getStartTime()));
            d2 += hiHealthData.getDouble("Sum");
        }
        if (buoVar.b() == 0 || buoVar.b() == 3 || buoVar.b() == 5) {
            d2 /= 1000.0d;
        }
        LogUtil.a("ActiveTargetManager", "distance is ", Double.valueOf(d2), ", goal type is ", Integer.valueOf(buoVar.b()));
        e(buoVar, d2);
        buoVar.d(d2);
        buoVar.c(arrayList);
        managerCallback.onSuccess(buoVar);
        b(buoVar, d2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<HiHealthData> list, ManagerCallback managerCallback) {
        buo buoVar = new buo();
        if (koq.b(list)) {
            LogUtil.a("ActiveTargetManager", "parsingFitnessData is empty");
            managerCallback.onSuccess(b(buoVar));
            return;
        }
        ArrayList arrayList = new ArrayList();
        double d2 = 0.0d;
        for (HiHealthData hiHealthData : list) {
            arrayList.add(Long.valueOf(hiHealthData.getStartTime()));
            d2 += hiHealthData.getDouble("duration_sum");
        }
        LogUtil.a("ActiveTargetManager", "sumTime is ", Double.valueOf(d2));
        buoVar.c(arrayList);
        buoVar.d(d2);
        managerCallback.onSuccess(buoVar);
    }

    private void e(buo buoVar, double d2) {
        int b = buoVar.b();
        if (b != 4 ? d2 >= buoVar.d() : d(buoVar, d2)) {
            buoVar.b(1);
            a(buoVar);
        } else {
            if (System.currentTimeMillis() >= buoVar.a()) {
                buoVar.b(2);
                return;
            }
            if (buoVar.g() == 1) {
                LogUtil.a("ActiveTargetManager", "after delete sport data, remove message, old goal:", buoVar.toString());
                SharedPreferenceManager.e(BaseApplication.e(), Integer.toString(10051), f(b), "", new StorageParams());
                a(b);
            }
            buoVar.b(0);
            LogUtil.a("ActiveTargetManager", "updateActiveStatus status is doing");
        }
    }

    private void c(final buo buoVar, final ManagerCallback managerCallback) {
        b(buoVar.f(), buoVar.a(), new HiAggregateListener() { // from class: com.huawei.featureuserprofile.target.cloud.ActiveTargetManager.7
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                LogUtil.a("ActiveTargetManager", "readWeightData errorCode=", Integer.valueOf(i));
                ActiveTargetManager.this.e(list, buoVar, managerCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<HiHealthData> list, buo buoVar, ManagerCallback managerCallback) {
        if (koq.b(list)) {
            LogUtil.h("ActiveTargetManager", "parsingWeightData is empty");
            a(buoVar, managerCallback);
            return;
        }
        LogUtil.a("ActiveTargetManager", "parsingWeightData datas size:", Integer.valueOf(list.size()));
        HashSet hashSet = new HashSet();
        ArrayList arrayList = new ArrayList(list.size());
        for (HiHealthData hiHealthData : list) {
            if (a(hiHealthData.getDouble("bodyWeight"))) {
                arrayList.add(hiHealthData);
                hashSet.add(Long.valueOf(jdl.t(hiHealthData.getStartTime())));
            }
        }
        double d2 = ((HiHealthData) arrayList.get(arrayList.size() - 1)).getDouble("bodyWeight");
        LogUtil.a("ActiveTargetManager", "parsingWeightData lastWeight:", Double.valueOf(d2), " last data time:", Long.valueOf(((HiHealthData) arrayList.get(arrayList.size() - 1)).getStartTime()));
        e(buoVar, d2);
        buoVar.d(d2);
        buoVar.c(new ArrayList(hashSet));
        managerCallback.onSuccess(buoVar);
        b(buoVar, d2);
    }

    private boolean a(double d2) {
        return Double.compare(d2, 0.0d) > 0 && Double.compare(d2, 250.0d) <= 0;
    }

    private buo b(buo buoVar) {
        buoVar.d(0.0d);
        buoVar.c(new ArrayList());
        if (System.currentTimeMillis() <= buoVar.a()) {
            buoVar.b(0);
        }
        return buoVar;
    }

    private void a(buo buoVar, ManagerCallback managerCallback) {
        int g = buoVar.g();
        double e2 = buoVar.e();
        managerCallback.onSuccess(b(buoVar));
        if (g == 1 || e2 >= 0.0d) {
            b(buoVar, 0.0d);
        }
        int b = buoVar.b();
        if (g == 1) {
            LogUtil.a("ActiveTargetManager", "processNoDataList, remove message, old goal:", buoVar.toString());
            SharedPreferenceManager.e(BaseApplication.e(), Integer.toString(10051), f(b), "", new StorageParams());
            a(b);
        }
    }

    private void b(buo buoVar, double d2) {
        bus busVar = new bus();
        busVar.a(buoVar.i());
        busVar.c(d2);
        busVar.a(buoVar.h());
        busVar.b(buoVar.g());
        LogUtil.a("ActiveTargetManager", "parsingData updateGoal is ", busVar);
        ArrayList arrayList = new ArrayList();
        arrayList.add(busVar);
        a(arrayList, new ResultCallback<CloudCommonReponse>() { // from class: com.huawei.featureuserprofile.target.cloud.ActiveTargetManager.6
            @Override // com.huawei.networkclient.ResultCallback
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(CloudCommonReponse cloudCommonReponse) {
                ReleaseLogUtil.e("R_ActiveTargetManager", "updateTargetToCloud success");
                LogUtil.a("ActiveTargetManager", "data is ", cloudCommonReponse);
            }

            @Override // com.huawei.networkclient.ResultCallback
            public void onFailure(Throwable th) {
                ReleaseLogUtil.d("R_ActiveTargetManager", "updateTargetToCloud throwable is ", th);
            }
        });
    }

    private boolean d(buo buoVar, double d2) {
        double c2 = buoVar.c();
        double d3 = buoVar.d();
        double d4 = buoVar.d();
        return c2 <= d3 ? d2 >= d4 : d2 <= d4;
    }

    private void a(buo buoVar) {
        if (!jdl.g(buoVar.a(), System.currentTimeMillis())) {
            LogUtil.h("ActiveTargetManager", "Not current year, don't show point or message");
            return;
        }
        final int b = buoVar.b();
        LogUtil.a("ActiveTargetManager", "updateShowSharePreference, goalType:", Integer.valueOf(b));
        ThreadPoolManager.d().c("ActiveTargetManager" + b, new Runnable() { // from class: bum
            @Override // java.lang.Runnable
            public final void run() {
                ActiveTargetManager.this.d(b);
            }
        });
    }

    public /* synthetic */ void d(int i) {
        Context e2 = BaseApplication.e();
        if (TextUtils.isEmpty(SharedPreferenceManager.b(e2, Integer.toString(10051), f(i)))) {
            LogUtil.a("ActiveTargetManager", "updateShowSharePreference serialExecute, goalType:", Integer.valueOf(i));
            SharedPreferenceManager.e(e2, Integer.toString(10051), f(i), ParamConstants.CallbackMethod.ON_SHOW, new StorageParams());
            g(i);
        }
    }

    private void g(int i) {
        LogUtil.a("ActiveTargetManager", "sendMessage goalType is ", Integer.valueOf(i));
        if (TextUtils.isEmpty(this.b)) {
            this.b = LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1011);
        }
        Resources resources = BaseApplication.e().getResources();
        MessageObject messageObject = new MessageObject();
        messageObject.setModule(String.valueOf(100));
        messageObject.setType(MessageConstant.ACTIVE_TARGET_TYPE);
        messageObject.setMsgType(1);
        messageObject.setPosition(1);
        messageObject.setMsgPosition(11);
        messageObject.setHuid(this.b);
        messageObject.setMsgContent(c(i));
        String string = resources.getString(R.string._2130845610_res_0x7f021faa);
        messageObject.setMsgTitle(string);
        messageObject.setMetadata(string);
        messageObject.setExpireTime(jdl.b(System.currentTimeMillis()));
        messageObject.setReadFlag(0);
        messageObject.setDetailUri("messagecenter://activeTarget");
        messageObject.setCreateTime(System.currentTimeMillis());
        messageObject.setMsgId(this.f2037a.getMessageId(String.valueOf(100), MessageConstant.ACTIVE_TARGET_TYPE));
        this.f2037a.insertMessage(messageObject);
    }

    private String c(int i) {
        Resources resources = BaseApplication.e().getResources();
        if (i == 0) {
            return resources.getString(R.string._2130845735_res_0x7f022027);
        }
        if (i == 1) {
            return resources.getString(R.string._2130845736_res_0x7f022028);
        }
        if (i == 2) {
            return resources.getString(R.string._2130845737_res_0x7f022029);
        }
        if (i == 3) {
            return resources.getString(R.string._2130845738_res_0x7f02202a);
        }
        if (i == 4) {
            return resources.getString(R.string._2130845739_res_0x7f02202b);
        }
        if (i == 5) {
            return resources.getString(R.string._2130845740_res_0x7f02202c);
        }
        LogUtil.h("ActiveTargetManager", "getMsgContent goalType is other");
        return "";
    }

    private String f(int i) {
        if (TextUtils.isEmpty(this.b)) {
            this.b = LoginInit.getInstance(BaseApplication.e()).getAccountInfo(1011);
        }
        return this.b + "key_target_red_need_show" + jdl.ab(System.currentTimeMillis()) + i;
    }

    private void a(final List<bus> list, final ResultCallback<CloudCommonReponse> resultCallback) {
        LogUtil.a("ActiveTargetManager", "enter updateActiveTarget");
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.featureuserprofile.target.cloud.ActiveTargetManager.9
            @Override // java.lang.Runnable
            public void run() {
                but butVar = new but();
                butVar.b(list);
                lqi.d().b(butVar.getUrl(), ActiveTargetManager.this.i.getHeaders(), lql.b(ActiveTargetManager.this.i.getBody(butVar)), CloudCommonReponse.class, resultCallback);
            }
        });
    }

    public void c(final int i, final ActiveTargetService.ActiveTargetsCallback activeTargetsCallback) {
        long aa = jdl.aa(System.currentTimeMillis());
        long i2 = jdl.i(System.currentTimeMillis());
        if (i != 0) {
            if (i == 1) {
                d(aa, i2, activeTargetsCallback);
                return;
            } else if (i != 2 && i != 3) {
                if (i == 4) {
                    b(aa, i2, new HiAggregateListener() { // from class: com.huawei.featureuserprofile.target.cloud.ActiveTargetManager.3
                        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
                        public void onResultIntent(int i3, List<HiHealthData> list, int i4, int i5) {
                        }

                        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
                        public void onResult(List<HiHealthData> list, int i3, int i4) {
                            ActiveTargetManager.this.b(list, activeTargetsCallback);
                        }
                    });
                    return;
                } else if (i != 5) {
                    return;
                }
            }
        }
        c(aa, i2, b(i), new HiAggregateListener() { // from class: com.huawei.featureuserprofile.target.cloud.ActiveTargetManager.13
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i3, List<HiHealthData> list, int i4, int i5) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i3, int i4) {
                ActiveTargetManager.this.d(i, list, activeTargetsCallback);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, List<HiHealthData> list, ActiveTargetService.ActiveTargetsCallback activeTargetsCallback) {
        double d2 = 0.0d;
        if (koq.b(list)) {
            LogUtil.h("ActiveTargetManager", "parsingData is empty");
            activeTargetsCallback.onSuccess(Double.valueOf(0.0d));
            return;
        }
        Iterator<HiHealthData> it = list.iterator();
        while (it.hasNext()) {
            d2 += it.next().getDouble("Sum");
        }
        if (i == 0 || i == 3 || i == 5) {
            d2 /= 1000.0d;
        }
        LogUtil.a("ActiveTargetManager", "parsingTotalData sum:", Double.valueOf(d2));
        activeTargetsCallback.onSuccess(Double.valueOf(d2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<HiHealthData> list, ActiveTargetService.ActiveTargetsCallback activeTargetsCallback) {
        double d2 = 0.0d;
        if (koq.b(list)) {
            LogUtil.h("ActiveTargetManager", "parsingLastWeightData is empty");
            activeTargetsCallback.onSuccess(Double.valueOf(0.0d));
            return;
        }
        int size = list.size() - 1;
        while (true) {
            if (size < 0) {
                break;
            }
            double d3 = list.get(size).getDouble("bodyWeight");
            if (a(d3)) {
                d2 = d3;
                break;
            }
            size--;
        }
        LogUtil.a("ActiveTargetManager", "parsingLastWeightData lastWeight:", Double.valueOf(d2));
        activeTargetsCallback.onSuccess(Double.valueOf(d2));
    }

    private void d(long j, long j2, ActiveTargetService.ActiveTargetsCallback activeTargetsCallback) {
        final AtomicReference atomicReference = new AtomicReference(Double.valueOf(0.0d));
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        this.f.acquireSummaryFitnessRecord(j, j2, new IBaseResponseCallback() { // from class: bui
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                ActiveTargetManager.a(atomicReference, countDownLatch, i, obj);
            }
        });
        d(j, j2, new AnonymousClass4(atomicReference, countDownLatch));
        try {
            LogUtil.a("ActiveTargetManager", "getLastYearFitnessData isOnTime:", Boolean.valueOf(countDownLatch.await(3L, TimeUnit.SECONDS)));
        } catch (InterruptedException e2) {
            LogUtil.b("ActiveTargetManager", "getLastYearFitnessData exception ", LogAnonymous.b((Throwable) e2));
        }
        activeTargetsCallback.onSuccess(Double.valueOf(((Double) atomicReference.get()).doubleValue() / 60000.0d));
    }

    public static /* synthetic */ void a(AtomicReference atomicReference, CountDownLatch countDownLatch, int i, Object obj) {
        FitnessSummaryRecord fitnessSummaryRecord = obj instanceof FitnessSummaryRecord ? (FitnessSummaryRecord) obj : null;
        if (fitnessSummaryRecord != null) {
            LogUtil.a("ActiveTargetManager", "record.acquireRecordsSumTime() :", Long.valueOf(fitnessSummaryRecord.acquireRecordsSumTime()));
            atomicReference.set(Double.valueOf(((Double) atomicReference.get()).doubleValue() + fitnessSummaryRecord.acquireRecordsSumTime()));
        }
        countDownLatch.countDown();
    }

    /* renamed from: com.huawei.featureuserprofile.target.cloud.ActiveTargetManager$4, reason: invalid class name */
    public class AnonymousClass4 implements HiAggregateListener {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ CountDownLatch f2041a;
        final /* synthetic */ AtomicReference e;

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
        }

        AnonymousClass4(AtomicReference atomicReference, CountDownLatch countDownLatch) {
            this.e = atomicReference;
            this.f2041a = countDownLatch;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            ActiveTargetManager activeTargetManager = ActiveTargetManager.this;
            final AtomicReference atomicReference = this.e;
            final CountDownLatch countDownLatch = this.f2041a;
            activeTargetManager.d(list, new ManagerCallback() { // from class: bun
                @Override // com.huawei.featureuserprofile.target.cloud.ActiveTargetManager.ManagerCallback
                public final void onSuccess(buo buoVar) {
                    ActiveTargetManager.AnonymousClass4.c(atomicReference, countDownLatch, buoVar);
                }
            });
        }

        public static /* synthetic */ void c(AtomicReference atomicReference, CountDownLatch countDownLatch, buo buoVar) {
            atomicReference.set(Double.valueOf(((Double) atomicReference.get()).doubleValue() + buoVar.e()));
            countDownLatch.countDown();
        }
    }

    private void d(long j, long j2, HiAggregateListener hiAggregateListener) {
        HiSportStatDataAggregateOption hiSportStatDataAggregateOption = new HiSportStatDataAggregateOption();
        hiSportStatDataAggregateOption.setReadType(0);
        hiSportStatDataAggregateOption.setConstantsKey(new String[]{"duration_sum"});
        hiSportStatDataAggregateOption.setType(new int[]{4});
        hiSportStatDataAggregateOption.setAggregateType(0);
        hiSportStatDataAggregateOption.setHiHealthTypes(i());
        hiSportStatDataAggregateOption.setTimeRange(j, Math.min(jdl.e(System.currentTimeMillis()), j2));
        hiSportStatDataAggregateOption.setGroupUnitType(3);
        HiHealthManager.d(BaseApplication.e()).aggregateSportStatData(hiSportStatDataAggregateOption, hiAggregateListener);
    }

    private void c(long j, long j2, int i, HiAggregateListener hiAggregateListener) {
        HiSportStatDataAggregateOption hiSportStatDataAggregateOption = new HiSportStatDataAggregateOption();
        hiSportStatDataAggregateOption.setReadType(0);
        hiSportStatDataAggregateOption.setConstantsKey(new String[]{"Sum"});
        hiSportStatDataAggregateOption.setType(new int[]{i});
        hiSportStatDataAggregateOption.setAggregateType(0);
        hiSportStatDataAggregateOption.setTimeRange(j, Math.min(System.currentTimeMillis(), j2));
        HiHealthManager.d(BaseApplication.e()).aggregateSportStatData(hiSportStatDataAggregateOption, hiAggregateListener);
    }

    private void b(long j, long j2, HiAggregateListener hiAggregateListener) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setReadType(0);
        hiAggregateOption.setConstantsKey(new String[]{"Sum"});
        hiAggregateOption.setType(new int[]{10006});
        hiAggregateOption.setTimeRange(j, Math.min(System.currentTimeMillis(), j2));
        hiAggregateOption.setAggregateType(4);
        hiAggregateOption.setFilter("NULL");
        HiHealthManager.d(BaseApplication.e()).aggregateHiHealthData(hiAggregateOption, hiAggregateListener);
    }

    private int b(int i) {
        if (i == 0) {
            return 42102;
        }
        if (i == 5) {
            return 42202;
        }
        if (i == 2) {
            return 40002;
        }
        if (i == 3) {
            return 42152;
        }
        LogUtil.c("ActiveTargetManager", "getDataType not sport type");
        return i;
    }

    public void b(int i, double d2, double d3, double d4) {
        if (i != 4 ? d3 < d2 : !a(d2, d3, d4)) {
            SharedPreferenceManager.e(BaseApplication.e(), Integer.toString(10051), f(i), "", new StorageParams());
            a(i);
        } else {
            LogUtil.a("ActiveTargetManager", "changeTargetRedPoint target is complete, type = ", Integer.valueOf(i));
        }
    }

    private void a(final int i) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: bue
            @Override // java.lang.Runnable
            public final void run() {
                ActiveTargetManager.this.e(i);
            }
        });
    }

    public /* synthetic */ void e(int i) {
        List<MessageObject> messages = this.f2037a.getMessages(String.valueOf(100), MessageConstant.ACTIVE_TARGET_TYPE);
        if (koq.b(messages)) {
            LogUtil.a("ActiveTargetManager", "deleteMessage messageList is empty");
            return;
        }
        for (MessageObject messageObject : messages) {
            if (c(i).equals(messageObject.getMsgContent()) && jdl.g(messageObject.getCreateTime(), System.currentTimeMillis())) {
                LogUtil.a("ActiveTargetManager", "deleteMessage delete message is ", Boolean.valueOf(this.f2037a.deleteMessage(messageObject.getMsgId())));
            }
        }
    }

    private int[] i() {
        List<Integer> fitnessSportTypeList = eme.b().getFitnessSportTypeList("FITNESS_SPORT");
        fitnessSportTypeList.add(Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_OTHER_SPORT));
        int[] iArr = new int[fitnessSportTypeList.size()];
        return fitnessSportTypeList.stream().mapToInt(new ToIntFunction() { // from class: bul
            @Override // java.util.function.ToIntFunction
            public final int applyAsInt(Object obj) {
                int intValue;
                intValue = ((Integer) obj).intValue();
                return intValue;
            }
        }).toArray();
    }

    public void f() {
        LogUtil.a("ActiveTargetManager", "releaseManager");
        if (this.j != null) {
            ObserverManagerUtil.e("observer_sport_list_data_change");
            this.j = null;
        }
    }

    public void a() {
        try {
            if (this.h != null) {
                LogUtil.c("ActiveTargetManager", "mSyncCloudDataReceiver is register");
                return;
            }
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("sync_cloud_data_action");
            this.h = new d();
            BroadcastManagerUtil.bFz_(BaseApplication.e(), this.h, intentFilter);
        } catch (Exception unused) {
            LogUtil.b("ActiveTargetManager", "registerSyncCloudDataReceiver Exception");
        }
    }

    /* loaded from: classes3.dex */
    class d extends BroadcastReceiver {
        private d() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LogUtil.a("ActiveTargetManager", "SyncCloudDataReceiver onReceive to enter, action = ", intent.getAction());
            String stringExtra = intent.getStringExtra("sync_cloud_data_status");
            if ("sync_cloud_data_finish".equals(stringExtra) || "sync_cloud_data_backstage".equals(stringExtra)) {
                ActiveTargetManager.this.h();
            }
        }
    }

    /* loaded from: classes3.dex */
    class c implements Observer {
        private c() {
        }

        @Override // com.huawei.haf.design.pattern.Observer
        public void notify(String str, Object... objArr) {
            LogUtil.a("ActiveTargetManager", "RecordChangedReceiver notify, label = ", str);
            if ("observer_sport_list_data_change".equals(str)) {
                ActiveTargetManager.this.h();
            }
        }
    }
}
