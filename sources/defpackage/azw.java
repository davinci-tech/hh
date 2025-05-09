package defpackage;

import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.basichealthmodel.R$string;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes.dex */
public class azw {
    private static volatile azw c;

    /* renamed from: a, reason: collision with root package name */
    private ResponseCallback<String> f297a;
    private List<String> b;
    private long d;
    private CountDownLatch e;
    private String f;
    private List<String> h;
    private int i;
    private int j;
    private SparseArray<HealthLifeBean> k;
    private List<String> l;
    private String m;
    private String n;
    private SparseArray<HealthLifeBean> o;
    private ArrayList<HealthLifeBean> g = new ArrayList<>(16);
    private int t = -1;
    private int q = 0;

    public static azw b() {
        if (c == null) {
            synchronized (azw.class) {
                if (c == null) {
                    c = new azw();
                }
            }
        }
        return c;
    }

    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void c(final List<String> list, final List<String> list2, final List<String> list3, final int i, final ResponseCallback<String> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("HealthLife_ChallengeH5Utils", "addHealthLifeConfig callback is null");
            return;
        }
        if (koq.b(list) || koq.b(list2) || koq.b(list3)) {
            LogUtil.h("HealthLife_ChallengeH5Utils", "addHealthLifeConfig params is error");
            responseCallback.onResponse(-1, "");
            return;
        }
        if (HandlerExecutor.c()) {
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: bab
                @Override // java.lang.Runnable
                public final void run() {
                    azw.this.c(list, list2, list3, i, responseCallback);
                }
            });
            return;
        }
        this.l = list;
        this.b = list2;
        this.i = i;
        this.f297a = responseCallback;
        this.h = list3;
        this.t = azi.aa() ? -1 : 0;
        this.q = azi.q();
        this.e = new CountDownLatch(2);
        this.d = System.currentTimeMillis();
        String p = azi.p();
        d(p, DateFormatUtil.b(this.d));
        d(p, DateFormatUtil.b(jdl.y(this.d)));
        try {
            this.e.await();
        } catch (InterruptedException e2) {
            LogUtil.b("HealthLife_ChallengeH5Utils", "getSubscriptionTask exception ", LogAnonymous.b((Throwable) e2));
        }
        mq_(this.j, this.k);
    }

    private void d(String str, final int i) {
        awq.e().a(str, i, new ResponseCallback() { // from class: bac
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i2, Object obj) {
                azw.this.mr_(i, i2, (SparseArray) obj);
            }
        });
    }

    /* synthetic */ void mr_(int i, int i2, SparseArray sparseArray) {
        LogUtil.a("HealthLife_ChallengeH5Utils", "date = ", Integer.valueOf(i), ", resultCode = ", Integer.valueOf(i2));
        if (i != DateFormatUtil.b(System.currentTimeMillis())) {
            this.j = i2;
            this.k = sparseArray;
        } else if (i2 == 0) {
            this.o = sparseArray;
            this.g = bax.mw_(sparseArray);
        }
        this.e.countDown();
    }

    private void mq_(int i, SparseArray<HealthLifeBean> sparseArray) {
        SparseArray<HealthLifeBean> sparseArray2;
        if (i == 0 && sparseArray != null) {
            HealthLifeBean healthLifeBean = sparseArray.get(1);
            if (healthLifeBean != null) {
                this.n = healthLifeBean.getTarget();
            }
            if (koq.b(this.g) || (sparseArray2 = this.o) == null) {
                LogUtil.h("HealthLife_ChallengeH5Utils", "mTaskList or mTodayTaskList is null");
                this.f297a.onResponse(-1, "");
                return;
            }
            HealthLifeBean healthLifeBean2 = sparseArray2.get(1);
            if (healthLifeBean2 == null) {
                LogUtil.h("HealthLife_ChallengeH5Utils", "todayDbBean is null");
                this.f297a.onResponse(-1, "");
                return;
            }
            String target = healthLifeBean2.getTarget();
            if (TextUtils.isEmpty(this.n)) {
                this.n = target;
            }
            LogUtil.a("HealthLife_ChallengeH5Utils", "todayTarget ", target, "mTargetList ", this.n);
            d(target);
            return;
        }
        LogUtil.h("HealthLife_ChallengeH5Utils", "setTomorrowConfigData resultCode code:", Integer.valueOf(i));
        this.f297a.onResponse(i, "");
    }

    private void d(String str) {
        ArrayList<String> arrayList = new ArrayList<>(16);
        ArrayList<String> arrayList2 = new ArrayList<>(16);
        if (!azi.aa()) {
            e(this.g, "");
            e(arrayList2, arrayList);
            return;
        }
        List<String> a2 = a();
        if (koq.c(a2)) {
            for (String str2 : a2) {
                if (!this.l.contains(str2)) {
                    this.l.add(str2);
                }
            }
        }
        ArrayList arrayList3 = new ArrayList(Arrays.asList(this.n.split(",")));
        for (String str3 : this.l) {
            if (!arrayList3.contains(str3)) {
                arrayList2.add(str3);
            }
        }
        String[] split = this.n.split(",");
        arrayList.addAll(this.l);
        for (String str4 : split) {
            if (!arrayList.contains(str4)) {
                arrayList.add(str4);
            }
        }
        if (!str.equals(this.n)) {
            str = this.n;
        }
        e(this.g, str);
        e(arrayList2, arrayList);
    }

    private List<String> a() {
        int id;
        ArrayList arrayList = new ArrayList(10);
        if (TextUtils.isEmpty(this.n)) {
            return arrayList;
        }
        LogUtil.a("HealthLife_ChallengeH5Utils", "reservedTaskList mTargetList= ", this.n);
        ArrayList arrayList2 = new ArrayList(Arrays.asList(this.n.split(",")));
        Iterator<HealthLifeBean> it = this.g.iterator();
        while (it.hasNext()) {
            HealthLifeBean next = it.next();
            if (next != null && (id = next.getId()) != 1 && id != 100001 && next.getDataSource() != 1) {
                int challengeId = next.getChallengeId();
                LogUtil.a("HealthLife_ChallengeH5Utils", "reservedTaskList challengeId= ", Integer.valueOf(challengeId));
                if (!bat.e(challengeId)) {
                    if (bat.e()) {
                        int e2 = nsn.e(bao.e("health_model_challenge_id"));
                        boolean d = bah.d(e2, id);
                        LogUtil.a("HealthLife_ChallengeH5Utils", "reservedTaskList taskId= ", Integer.valueOf(id), ",isPlanTaskId= ", Boolean.valueOf(d));
                        if (d && e2 != 200001) {
                        }
                    }
                    if (arrayList2.contains(String.valueOf(id))) {
                        arrayList.add(String.valueOf(id));
                    }
                }
            }
        }
        LogUtil.a("HealthLife_ChallengeH5Utils", "reservedTaskList reservedTaskList= ", arrayList.toString());
        return arrayList;
    }

    private void e(ArrayList<HealthLifeBean> arrayList, String str) {
        Iterator<HealthLifeBean> it = arrayList.iterator();
        while (it.hasNext()) {
            HealthLifeBean next = it.next();
            if (next != null) {
                int id = next.getId();
                if (id == 1) {
                    next.setLastTarget(str);
                    next.setChallengeId(this.i);
                    next.setDataSource(0);
                    c(next);
                    e(next);
                } else {
                    e(next);
                    next.setChallengeId(this.h.contains(String.valueOf(id)) ? this.i : 0);
                    next.setTaskType(this.b.contains(String.valueOf(id)) ? 1 : 0);
                    next.setDataSource(0);
                }
            }
        }
    }

    private void e(HealthLifeBean healthLifeBean) {
        if (healthLifeBean.getRecordDay() != DateFormatUtil.b(this.d)) {
            healthLifeBean.setRecordDay(DateFormatUtil.b(this.d));
        }
    }

    private void c(HealthLifeBean healthLifeBean) {
        ArrayList arrayList = new ArrayList(this.l.size());
        Iterator<String> it = this.l.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(CommonUtil.h(it.next())));
        }
        Collections.sort(arrayList);
        StringBuilder sb = new StringBuilder();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            int intValue = ((Integer) arrayList.get(i)).intValue();
            if (intValue == 4) {
                LogUtil.a("HealthLife_ChallengeH5Utils", "updateTarget taskId = 4");
            } else {
                sb.append(intValue);
                if (i != size - 1) {
                    sb.append(",");
                }
            }
        }
        String sb2 = sb.toString();
        this.m = sb2;
        healthLifeBean.setTarget(sb2);
    }

    private void e(ArrayList<String> arrayList, ArrayList<String> arrayList2) {
        if (!azi.aa() || !azi.ae() || azi.h(DateFormatUtil.b(System.currentTimeMillis()))) {
            this.f = this.m;
        } else {
            b(arrayList);
        }
        awq.e().e(!azi.ae(), "", c(arrayList2), new e());
    }

    private void b(ArrayList<String> arrayList) {
        if (koq.b(arrayList)) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            if (!TextUtils.isEmpty(next)) {
                sb.append(next);
                sb.append(",");
            }
        }
        int length = sb.length();
        if (length > 1) {
            this.f = sb.substring(0, length - 1);
        }
        LogUtil.a("HealthLife_ChallengeH5Utils", "setNewTaskId rewardTaskIds = ", this.f);
    }

    private ArrayList<HealthLifeBean> c(ArrayList<String> arrayList) {
        if (koq.b(arrayList)) {
            return this.g;
        }
        ArrayList<HealthLifeBean> arrayList2 = new ArrayList<>(this.g);
        HealthLifeBean healthLifeBean = this.o.get(1);
        if (healthLifeBean != null) {
            healthLifeBean.setLastTarget(this.m);
            healthLifeBean.setTarget(this.m);
            healthLifeBean.setChallengeId(this.i);
            healthLifeBean.setDataSource(0);
            healthLifeBean.setRecordDay(DateFormatUtil.b(jdl.y(this.d)));
            arrayList2.add(healthLifeBean);
        }
        Iterator<String> it = arrayList.iterator();
        while (it.hasNext()) {
            String next = it.next();
            HealthLifeBean healthLifeBean2 = this.o.get(CommonUtil.h(next));
            if (healthLifeBean2 != null) {
                healthLifeBean2.setRecordDay(DateFormatUtil.b(jdl.y(this.d)));
                healthLifeBean2.setChallengeId(this.h.contains(next) ? this.i : 0);
                healthLifeBean2.setTaskType(this.b.contains(next) ? 1 : 0);
                healthLifeBean2.setDataSource(0);
                SparseArray<HealthLifeBean> sparseArray = this.k;
                if (sparseArray != null && sparseArray.get(CommonUtil.h(next)) != null) {
                    healthLifeBean2.setTarget(this.k.get(CommonUtil.h(next)).getTarget());
                }
                arrayList2.add(healthLifeBean2);
            }
        }
        LogUtil.a("HealthLife_ChallengeH5Utils", "updateList tomorrowList ", arrayList2.toString());
        return arrayList2;
    }

    /* loaded from: classes8.dex */
    static class e implements ResponseCallback<SparseArray<HealthLifeBean>> {
        private final WeakReference<azw> d;

        private e(azw azwVar) {
            this.d = new WeakReference<>(azwVar);
        }

        @Override // com.huawei.hwbasemgr.ResponseCallback
        /* renamed from: ms_, reason: merged with bridge method [inline-methods] */
        public void onResponse(int i, SparseArray<HealthLifeBean> sparseArray) {
            azw azwVar = this.d.get();
            if (azwVar != null) {
                azwVar.c(i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:11:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void c(int r4) {
        /*
            r3 = this;
            java.lang.String r0 = "taskSubscriptionHandler error code:"
            java.lang.Integer r1 = java.lang.Integer.valueOf(r4)
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r1}
            java.lang.String r1 = "HealthLife_ChallengeH5Utils"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            if (r4 == 0) goto L22
            r0 = 1003(0x3eb, float:1.406E-42)
            if (r4 == r0) goto L1f
            switch(r4) {
                case 12030008: goto L1c;
                case 12030009: goto L22;
                case 12030010: goto L1f;
                default: goto L19;
            }
        L19:
            int r0 = com.huawei.basichealthmodel.R$string.IDS_health_model_edit_error
            goto L26
        L1c:
            int r0 = com.huawei.basichealthmodel.R$string.IDS_health_model_user_status_out
            goto L26
        L1f:
            int r0 = com.huawei.basichealthmodel.R$string.IDS_health_model_edit_error_time
            goto L26
        L22:
            r3.d()
            r0 = 0
        L26:
            if (r0 == 0) goto L39
            com.huawei.hwbasemgr.ResponseCallback<java.lang.String> r1 = r3.f297a
            android.content.Context r2 = com.huawei.haf.application.BaseApplication.e()
            android.content.res.Resources r2 = r2.getResources()
            java.lang.String r0 = r2.getString(r0)
            r1.onResponse(r4, r0)
        L39:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.azw.c(int):void");
    }

    private void d() {
        final boolean z = true;
        if (koq.c(this.l) && this.l.contains(String.valueOf(12))) {
            awq.e().a(true);
        }
        if (TextUtils.isEmpty(this.f)) {
            a(this.i, this.f297a);
            return;
        }
        azi.aj();
        c();
        if (azi.ae() && !azi.h(DateFormatUtil.b(System.currentTimeMillis()))) {
            z = false;
        }
        awq.e().c(z, this.f, new ResponseCallback() { // from class: azy
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                azw.this.a(z, i, obj);
            }
        });
    }

    /* synthetic */ void a(boolean z, int i, Object obj) {
        LogUtil.a("HealthLife_ChallengeH5Utils", "onSuccessCallback resultCode = ", Integer.valueOf(i), ",mNewTaskId = ", this.f, ",isNeedRewardClover = ", Boolean.valueOf(z));
        if (i == 0) {
            a(this.i, this.f297a);
        }
    }

    private void c() {
        if (this.t == 0) {
            bao.e(DateFormatUtil.b(System.currentTimeMillis()), false);
        }
        if (this.q <= 0) {
            bao.a(DateFormatUtil.b(System.currentTimeMillis()), false);
        }
        int d = CommonUtil.d(BaseApplication.getContext());
        if (d <= 0) {
            LogUtil.h("HealthLife_ChallengeH5Utils", "upgradeUserStatus appVersion less than or equal to zero");
        } else {
            bao.e("health_model_update_guide_version_code", String.valueOf(d));
            LogUtil.a("HealthLife_ChallengeH5Utils", "upgradeUserStatus");
        }
    }

    public void a(final int i, final ResponseCallback<String> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("HealthLife_ChallengeH5Utils", "updateChallenge callback is null");
            return;
        }
        boolean i2 = Utils.i();
        LogUtil.a("HealthLife_ChallengeH5Utils", "updateChallenge planId ", Integer.valueOf(i), " ifAllowLogin ", Boolean.valueOf(i2));
        if (!i2) {
            d(i, responseCallback);
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: baa
                @Override // java.lang.Runnable
                public final void run() {
                    azw.this.e(i, responseCallback);
                }
            });
        }
    }

    /* synthetic */ void e(final int i, final ResponseCallback responseCallback) {
        aug.d().c(i, 1, new IBaseResponseCallback() { // from class: azz
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i2, Object obj) {
                azw.this.c(responseCallback, i, i2, obj);
            }
        });
    }

    /* synthetic */ void c(ResponseCallback responseCallback, int i, int i2, Object obj) {
        LogUtil.a("HealthLife_ChallengeH5Utils", "updateChallenge errorCode ", Integer.valueOf(i2), " objData ", obj);
        if (i2 == -1 || ((obj instanceof auh) && ((auh) obj).a() != 0)) {
            responseCallback.onResponse(i2, com.huawei.haf.application.BaseApplication.e().getResources().getString(R$string.IDS_health_model_edit_error));
        } else {
            d(i, (ResponseCallback<String>) responseCallback);
        }
    }

    private void d(int i, ResponseCallback<String> responseCallback) {
        String str;
        String e2 = bao.e("health_model_challenge_id");
        int h = CommonUtil.h(bao.e("health_model_current_challenge_join_time"));
        bao.e("health_model_challenge_id", String.valueOf(i));
        bao.e("health_model_challenge_join", String.valueOf(1));
        bao.e("health_model_first_join_challenge", String.valueOf(1));
        bao.e("health_model_current_challenge_join_time", String.valueOf(DateFormatUtil.b(this.d)));
        responseCallback.onResponse(0, "");
        if (azi.ad()) {
            axf.e();
            e2 = String.valueOf(1);
            azi.n(0);
            h = 0;
        }
        if (h <= 0) {
            str = "";
        } else {
            str = String.valueOf(jdl.e(DateFormatUtil.c(h), this.d));
        }
        aza.c("", str, e2, this.l);
    }
}
