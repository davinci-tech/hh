package defpackage;

import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.dfx.DfxUtils;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.CommonUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes3.dex */
public class bcr {
    private static volatile bcr b;
    private final String d = "2,5,6";
    private SparseArray<HealthLifeBean> c = new SparseArray<>();

    /* renamed from: a, reason: collision with root package name */
    private SparseArray<HealthLifeBean> f324a = new SparseArray<>();

    private bcr() {
    }

    public static bcr a() {
        if (b == null) {
            synchronized (bcr.class) {
                if (b == null) {
                    b = new bcr();
                }
            }
        }
        return b;
    }

    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void c(final ResponseCallback<List<HealthLifeBean>> responseCallback, final String str) {
        if (Boolean.TRUE.toString().equals(bao.e("health_model_is_join_future"))) {
            LogUtil.a("HealthLife_TaskSubscriptionHelper", "joinHealthLife join future");
            return;
        }
        if (azi.aa()) {
            if (azi.ae()) {
                return;
            }
            LogUtil.a("HealthLife_TaskSubscriptionHelper", "joinHealthLife not upgrade");
            e(str);
            return;
        }
        LogUtil.a("HealthLife_TaskSubscriptionHelper", "joinHealthLife un joined");
        if (!CommonUtil.aa(BaseApplication.e())) {
            LogUtil.a("HealthLife_TaskSubscriptionHelper", "joinHealthLife isNetworkConnected is false");
            return;
        }
        if (HandlerExecutor.c()) {
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: bcs
                @Override // java.lang.Runnable
                public final void run() {
                    bcr.this.c(responseCallback, str);
                }
            });
        } else if (!LoginInit.getInstance(BaseApplication.e()).getIsLogined() || !Utils.i()) {
            LogUtil.a("HealthLife_TaskSubscriptionHelper", "joinHealthLife isLogin is false");
        } else {
            nip.d("900200006", new IBaseResponseCallback() { // from class: bct
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    bcr.this.c(responseCallback, str, i, obj);
                }
            });
        }
    }

    /* synthetic */ void c(ResponseCallback responseCallback, String str, int i, Object obj) {
        d(responseCallback, str, obj instanceof Integer ? ((Integer) obj).intValue() : 0);
    }

    private void d(final ResponseCallback<List<HealthLifeBean>> responseCallback, final String str, final int i) {
        final ArrayList<Integer> d = azi.d();
        final int b2 = DateFormatUtil.b(System.currentTimeMillis());
        awq.e().a(azi.p(), b2, new ResponseCallback() { // from class: bcy
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i2, Object obj) {
                bcr.this.nI_(b2, i, responseCallback, d, str, i2, (SparseArray) obj);
            }
        });
    }

    /* synthetic */ void nI_(int i, int i2, ResponseCallback responseCallback, List list, String str, int i3, SparseArray sparseArray) {
        LogUtil.a("HealthLife_TaskSubscriptionHelper", "join recordDay=", Integer.valueOf(i), ",resultCode=", Integer.valueOf(i3), ",stepGoal=", Integer.valueOf(i2));
        if (sparseArray == null || sparseArray.size() == 0) {
            LogUtil.h("HealthLife_TaskSubscriptionHelper", "join array is empty");
            b((ResponseCallback<List<HealthLifeBean>>) responseCallback, -1, Collections.emptyList());
            return;
        }
        ArrayList<HealthLifeBean> arrayList = new ArrayList<>();
        int size = sparseArray.size();
        for (int i4 = 0; i4 < size; i4++) {
            HealthLifeBean healthLifeBean = (HealthLifeBean) sparseArray.valueAt(i4);
            if (healthLifeBean != null) {
                int keyAt = sparseArray.keyAt(i4);
                healthLifeBean.setRecordDay(i);
                if (keyAt == 1) {
                    healthLifeBean.setLastTarget("");
                    healthLifeBean.setTarget("2,5,6");
                    arrayList.add(healthLifeBean);
                } else {
                    if (keyAt == 2 && i2 > 0) {
                        healthLifeBean.setTarget(String.valueOf(i2));
                    }
                    if (list.contains(Integer.valueOf(keyAt))) {
                        healthLifeBean.setTaskType(1);
                        arrayList.add(healthLifeBean);
                    }
                }
            }
        }
        awq.e().e(true, str, arrayList, (ResponseCallback<SparseArray<HealthLifeBean>>) new c(this, responseCallback, str));
    }

    static class c implements ResponseCallback<SparseArray<HealthLifeBean>> {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<ResponseCallback<List<HealthLifeBean>>> f325a;
        private final WeakReference<bcr> b;
        private final String d;

        c(bcr bcrVar, ResponseCallback<List<HealthLifeBean>> responseCallback, String str) {
            this.b = new WeakReference<>(bcrVar);
            this.f325a = new WeakReference<>(responseCallback);
            this.d = str;
        }

        @Override // com.huawei.hwbasemgr.ResponseCallback
        /* renamed from: nJ_, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, SparseArray<HealthLifeBean> sparseArray) {
            bcr bcrVar = this.b.get();
            if (bcrVar != null) {
                bcrVar.nF_(i, this.f325a.get(), this.d, sparseArray);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void nF_(int i, ResponseCallback<List<HealthLifeBean>> responseCallback, String str, SparseArray<HealthLifeBean> sparseArray) {
        LogUtil.a("HealthLife_TaskSubscriptionHelper", "taskSubscriptionHandler resultCode:", Integer.valueOf(i));
        b();
        ArrayList<HealthLifeBean> mw_ = bax.mw_(sparseArray);
        if (i == 0 || i == 12030009) {
            e(responseCallback, str, mw_);
        } else {
            b(responseCallback, i, mw_);
        }
    }

    private void e(ResponseCallback<List<HealthLifeBean>> responseCallback, String str, List<HealthLifeBean> list) {
        str.hashCode();
        if (str.equals("cloverLife")) {
            c();
            b(responseCallback, 0, list);
        } else {
            if (str.equals("EXIT_HEALTH_GOAL")) {
                d();
                bby.mZ_(list, bax.mv_(list), bby.e());
                a(responseCallback);
                return;
            }
            b(responseCallback, 0, list);
        }
    }

    private void d() {
        HiUserPreference hiUserPreference = new HiUserPreference();
        hiUserPreference.setKey("health_model_is_agree_protocol");
        hiUserPreference.setValue("");
        boolean userPreference = HiHealthManager.d(BaseApplication.e()).setUserPreference(hiUserPreference);
        bao.e("health_model_is_agree_protocol", "");
        LogUtil.a("HealthLife_TaskSubscriptionHelper", "clearProtocol isSuccess=", Boolean.valueOf(userPreference));
    }

    private void b(ResponseCallback<List<HealthLifeBean>> responseCallback, int i, List<HealthLifeBean> list) {
        if (responseCallback != null) {
            responseCallback.onResponse(i, list);
        }
    }

    private void c() {
        azi.aj();
        int b2 = DateFormatUtil.b(System.currentTimeMillis());
        bao.e(b2, false);
        bao.a(b2, false);
        int d = CommonUtil.d(com.huawei.hwcommonmodel.application.BaseApplication.getContext());
        if (d <= 0) {
            LogUtil.h("HealthLife_TaskSubscriptionHelper", "upgradeUserStatus appVersion less than or equal to zero");
        } else {
            bao.e("health_model_update_guide_version_code", String.valueOf(d));
            LogUtil.a("HealthLife_TaskSubscriptionHelper", "upgradeUserStatus");
        }
    }

    public void b(final List<HealthLifeBean> list, final ResponseCallback<List<HealthLifeBean>> responseCallback, final String str) {
        boolean z = azi.ad() || Boolean.TRUE.toString().equals(bao.e("health_model_is_join_future"));
        if (!azi.aa() || koq.b(list) || z) {
            LogUtil.h("HealthLife_TaskSubscriptionHelper", "syncUpdateHealthGoal not join health life or lifeBean is null");
            b(responseCallback, -1, Collections.emptyList());
            return;
        }
        if (HandlerExecutor.c()) {
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: bcz
                @Override // java.lang.Runnable
                public final void run() {
                    bcr.this.e(list, responseCallback, str);
                }
            });
            return;
        }
        CountDownLatch countDownLatch = new CountDownLatch(2);
        long currentTimeMillis = System.currentTimeMillis();
        int b2 = DateFormatUtil.b(currentTimeMillis);
        int b3 = DateFormatUtil.b(jdl.y(currentTimeMillis));
        String p = azi.p();
        b(countDownLatch, p, b2);
        b(countDownLatch, p, b3);
        azi.d(countDownLatch, "HealthLife_TaskSubscriptionHelper");
        if (this.c.size() == 0 || this.f324a.size() == 0) {
            LogUtil.h("HealthLife_TaskSubscriptionHelper", "syncUpdateHealthGoal data is empty");
            b(responseCallback, -1, Collections.emptyList());
        } else {
            nG_(this.c, b2);
            nG_(this.f324a, b3);
            nD_(bax.mv_(list), this.c, this.f324a, responseCallback, str);
        }
    }

    /* synthetic */ void e(List list, ResponseCallback responseCallback, String str) {
        b((List<HealthLifeBean>) list, (ResponseCallback<List<HealthLifeBean>>) responseCallback, str);
    }

    private void b(final CountDownLatch countDownLatch, String str, final int i) {
        awq.e().a(str, i, new ResponseCallback() { // from class: bcx
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i2, Object obj) {
                bcr.this.nH_(i, countDownLatch, i2, (SparseArray) obj);
            }
        });
    }

    /* synthetic */ void nH_(int i, CountDownLatch countDownLatch, int i2, SparseArray sparseArray) {
        LogUtil.a("HealthLife_TaskSubscriptionHelper", "date ", Integer.valueOf(i), " resultCode ", Integer.valueOf(i2), " array ", sparseArray);
        if (sparseArray == null || sparseArray.size() <= 0) {
            countDownLatch.countDown();
            return;
        }
        if (i == DateFormatUtil.b(System.currentTimeMillis())) {
            this.c = sparseArray;
        } else {
            this.f324a = sparseArray;
        }
        countDownLatch.countDown();
    }

    private void nD_(SparseArray<HealthLifeBean> sparseArray, SparseArray<HealthLifeBean> sparseArray2, SparseArray<HealthLifeBean> sparseArray3, ResponseCallback<List<HealthLifeBean>> responseCallback, String str) {
        if (sparseArray2 == null || sparseArray2.size() <= 0 || sparseArray3 == null || sparseArray3.size() <= 0) {
            ReleaseLogUtil.a("HealthLife_TaskSubscriptionHelper", "handleSubscriptionTask todayTaskArray ", sparseArray2, " tomorrowTaskArray ", sparseArray3, " stackTraceInfo ", DfxUtils.d(Thread.currentThread(), null));
            return;
        }
        HealthLifeBean healthLifeBean = sparseArray2.get(1);
        if (healthLifeBean == null) {
            LogUtil.h("HealthLife_TaskSubscriptionHelper", "handleSubscriptionTask cloverBean is null");
            b(responseCallback, -1, Collections.emptyList());
            b();
            return;
        }
        healthLifeBean.setLastTarget(healthLifeBean.getTarget());
        HealthLifeBean healthLifeBean2 = sparseArray3.get(1);
        if (healthLifeBean2 != null) {
            healthLifeBean2.setLastTarget(healthLifeBean.getTarget());
        }
        int size = sparseArray3.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            int keyAt = sparseArray3.keyAt(i);
            HealthLifeBean healthLifeBean3 = sparseArray3.get(keyAt);
            HealthLifeBean healthLifeBean4 = sparseArray.get(keyAt);
            if (healthLifeBean4 != null && healthLifeBean3 != null) {
                String target = healthLifeBean3.getTarget();
                String target2 = healthLifeBean4.getTarget();
                if (!TextUtils.isEmpty(target) && target.equals(target2)) {
                    LogUtil.a("HealthLife_TaskSubscriptionHelper", "handleSubscriptionTask target same taskId=", Integer.valueOf(keyAt));
                } else {
                    healthLifeBean3.setTarget(target2);
                    LogUtil.a("HealthLife_TaskSubscriptionHelper", "handleSubscriptionTask modify taskId=", Integer.valueOf(keyAt));
                    z = true;
                }
            }
        }
        if (!z) {
            b(responseCallback, 0, Collections.emptyList());
            b();
        } else {
            ArrayList<HealthLifeBean> arrayList = new ArrayList<>();
            arrayList.addAll(bax.mw_(sparseArray2));
            arrayList.addAll(bax.mw_(sparseArray3));
            awq.e().e(true, str, arrayList, (ResponseCallback<SparseArray<HealthLifeBean>>) new c(this, responseCallback, str));
        }
    }

    private void nG_(SparseArray<HealthLifeBean> sparseArray, int i) {
        int size = sparseArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            HealthLifeBean valueAt = sparseArray.valueAt(i2);
            if (valueAt != null) {
                valueAt.setRecordDay(i);
            }
        }
    }

    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void d(final ResponseCallback<List<HealthLifeBean>> responseCallback, final String str) {
        if (HandlerExecutor.c()) {
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: bcw
                @Override // java.lang.Runnable
                public final void run() {
                    bcr.this.d(responseCallback, str);
                }
            });
            return;
        }
        CountDownLatch countDownLatch = new CountDownLatch(2);
        long currentTimeMillis = System.currentTimeMillis();
        int b2 = DateFormatUtil.b(currentTimeMillis);
        int b3 = DateFormatUtil.b(jdl.y(currentTimeMillis));
        String p = azi.p();
        b(countDownLatch, p, b2);
        b(countDownLatch, p, b3);
        azi.d(countDownLatch, "HealthLife_TaskSubscriptionHelper");
        if (this.c.size() == 0 || this.f324a.size() == 0) {
            LogUtil.h("HealthLife_TaskSubscriptionHelper", "updateHealthLifeTarget data is empty");
            b(responseCallback, -1, Collections.emptyList());
            return;
        }
        nG_(this.c, b2);
        nG_(this.f324a, b3);
        nE_(this.c);
        nE_(this.f324a);
        HealthLifeBean healthLifeBean = this.c.get(1);
        HealthLifeBean healthLifeBean2 = this.f324a.get(1);
        if (healthLifeBean == null || healthLifeBean2 == null) {
            LogUtil.h("HealthLife_TaskSubscriptionHelper", "handleSubscriptionTask todayBean or tomorrowBean is null");
            b(responseCallback, -1, Collections.emptyList());
            b();
            return;
        }
        healthLifeBean.setLastTarget(healthLifeBean2.getTarget());
        healthLifeBean.setTarget("2,5,6");
        healthLifeBean2.setLastTarget("2,5,6");
        healthLifeBean2.setTarget("2,5,6");
        ArrayList<HealthLifeBean> arrayList = new ArrayList<>();
        arrayList.addAll(bax.mw_(this.c));
        arrayList.addAll(bax.mw_(this.f324a));
        awq.e().e(true, str, arrayList, (ResponseCallback<SparseArray<HealthLifeBean>>) new c(this, responseCallback, str));
    }

    private void nE_(SparseArray<HealthLifeBean> sparseArray) {
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            HealthLifeBean valueAt = sparseArray.valueAt(i);
            if (valueAt != null) {
                valueAt.setChallengeId(0);
                valueAt.setDataSource(0);
                int id = valueAt.getId();
                int i2 = (id == 2 || id == 5 || id == 6) ? 1 : 0;
                valueAt.setAddStatus(i2);
                valueAt.setTaskType(i2);
            }
        }
    }

    public void a(final ResponseCallback<List<HealthLifeBean>> responseCallback) {
        bam.c("HealthLife_TaskSubscriptionHelper", new ResponseCallback() { // from class: bcv
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                bcr.this.e(responseCallback, i, (drx) obj);
            }
        });
    }

    /* synthetic */ void e(final ResponseCallback responseCallback, int i, drx drxVar) {
        LogUtil.a("HealthLife_TaskSubscriptionHelper", "exitChallenge errorCode ", Integer.valueOf(i), " object ", drxVar);
        if (drxVar == null) {
            b((ResponseCallback<List<HealthLifeBean>>) responseCallback, 0, Collections.emptyList());
        } else {
            aug.d().c(drxVar.c(), 0, new IBaseResponseCallback() { // from class: bdd
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i2, Object obj) {
                    bcr.this.c(responseCallback, i2, obj);
                }
            });
        }
    }

    /* synthetic */ void c(ResponseCallback responseCallback, int i, Object obj) {
        LogUtil.a("HealthLife_TaskSubscriptionHelper", "exitChallenge code ", Integer.valueOf(i), " updateObject ", obj);
        if (i == 0) {
            bat.b();
        }
        b((ResponseCallback<List<HealthLifeBean>>) responseCallback, i, Collections.emptyList());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void e(final String str) {
        if (HandlerExecutor.c()) {
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: bdb
                @Override // java.lang.Runnable
                public final void run() {
                    bcr.this.e(str);
                }
            });
            return;
        }
        CountDownLatch countDownLatch = new CountDownLatch(2);
        long currentTimeMillis = System.currentTimeMillis();
        int b2 = DateFormatUtil.b(currentTimeMillis);
        int b3 = DateFormatUtil.b(jdl.y(currentTimeMillis));
        String p = azi.p();
        b(countDownLatch, p, b2);
        b(countDownLatch, p, b3);
        azi.d(countDownLatch, "HealthLife_TaskSubscriptionHelper");
        if (this.c.size() == 0 || this.f324a.size() == 0) {
            LogUtil.h("HealthLife_TaskSubscriptionHelper", "updateHealthLifeTarget data is empty");
            return;
        }
        nG_(this.c, b2);
        nG_(this.f324a, b3);
        nE_(this.c);
        nE_(this.f324a);
        ArrayList<HealthLifeBean> arrayList = new ArrayList<>();
        arrayList.addAll(bax.mw_(this.c));
        arrayList.addAll(bax.mw_(this.f324a));
        awq.e().e(true, str, arrayList, (ResponseCallback<SparseArray<HealthLifeBean>>) new c(this, null, ""));
    }

    private void b() {
        this.c.clear();
        this.f324a.clear();
    }
}
