package defpackage;

import android.text.TextUtils;
import android.util.SparseArray;
import com.huawei.basichealthmodel.bean.ChallengeConfigBean;
import com.huawei.basichealthmodel.bean.ConfigBean;
import com.huawei.basichealthmodel.bean.TaskConfigBean;
import com.huawei.basichealthmodel.listener.TaskDataListener;
import com.huawei.basichealthmodel.listener.TaskDayDataListener;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.GRSManager;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public class awq {
    private static final Object b = new Object();
    private static volatile awq c;

    /* renamed from: a, reason: collision with root package name */
    private ArrayList<Integer> f265a;
    private ConfigBean g;
    private boolean h;
    private awb i;
    private boolean j;
    private int l;
    private SparseArray<HealthLifeBean> f = new SparseArray<>();
    private SparseArray<TaskConfigBean> n = null;
    private SparseArray<ChallengeConfigBean> e = null;
    private final SparseArray<HealthLifeBean> k = new SparseArray<>();
    private final SparseArray<avr> d = new SparseArray<>();

    private awq() {
        g();
    }

    public static awq e() {
        if (c == null) {
            synchronized (b) {
                if (c == null) {
                    c = new awq();
                }
            }
        }
        return c;
    }

    private static void d(HealthLifeBean healthLifeBean) {
        healthLifeBean.setComplete(4);
        healthLifeBean.setSyncStatus(0);
        long currentTimeMillis = System.currentTimeMillis();
        healthLifeBean.setTimeZone(jdl.q(currentTimeMillis));
        healthLifeBean.setTimestamp(currentTimeMillis);
        aza.d(healthLifeBean.getId(), healthLifeBean.getComplete());
    }

    private void g() {
        ConfigBean b2;
        this.l = DateFormatUtil.b(jdl.d(System.currentTimeMillis(), -60));
        this.h = Utils.o();
        this.j = Utils.i();
        LogUtil.a("HealthLife_TaskManager", "initTask mIsOversea= ", Boolean.valueOf(this.h));
        if (this.i == null) {
            this.i = new awb();
        }
        if (this.g == null) {
            this.g = this.i.d(this.h, this.j);
        }
        if (this.g != null && (b2 = this.i.b(this.h)) != null) {
            this.g.setChallengeList(b2.getChallengeList());
            this.g.setChallengeMotivationImage(b2.getChallengeMotivationImage());
        }
        this.n = awb.kg_(this.g);
        this.e = awb.kf_(this.g);
        f();
    }

    private void f() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: awu
            @Override // java.lang.Runnable
            public final void run() {
                awq.this.i();
            }
        });
    }

    /* synthetic */ void i() {
        kA_(kE_(azi.p(), DateFormatUtil.b(System.currentTimeMillis()), Utils.k()));
    }

    private void a(HealthLifeBean healthLifeBean) {
        boolean z = false;
        if (healthLifeBean == null) {
            azi.b(false);
            return;
        }
        azi.n(healthLifeBean.getDataSource());
        String target = healthLifeBean.getTarget();
        if (TextUtils.isEmpty(target)) {
            azi.b(false);
            return;
        }
        String[] split = target.split(",");
        int length = split.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            if (String.valueOf(12).equals(split[i])) {
                z = true;
                break;
            }
            i++;
        }
        azi.b(z);
        LogUtil.a("HealthLife_TaskManager", "setIsSubscribeBloodPressure cloverBean ", healthLifeBean, " target ", target, " isSubscribeBloodPressure ", Boolean.valueOf(z));
    }

    public void a(boolean z) {
        azi.b(z);
    }

    public ArrayList<Integer> b() {
        if (koq.b(this.f265a)) {
            LogUtil.h("HealthLife_TaskManager", "getBasicTaskIdList default basicIdList");
            this.f265a = azi.d();
        }
        return this.f265a;
    }

    private void kz_(int i, SparseArray<HealthLifeBean> sparseArray) {
        if (sparseArray == null) {
            LogUtil.h("HealthLife_TaskManager", "getBasicTaskIdFormCloud default basicIdList");
            this.f265a = azi.d();
            return;
        }
        HealthLifeBean healthLifeBean = sparseArray.get(1);
        int b2 = DateFormatUtil.b(System.currentTimeMillis());
        if (healthLifeBean != null) {
            if (b2 == i) {
                a(healthLifeBean);
            }
            b2 = healthLifeBean.getRecordDay();
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        int size = sparseArray.size();
        for (int i2 = 0; i2 < size; i2++) {
            HealthLifeBean valueAt = sparseArray.valueAt(i2);
            if (valueAt != null && valueAt.getTaskType() == 1 && valueAt.getRecordDay() == b2) {
                arrayList.add(Integer.valueOf(valueAt.getId()));
            }
        }
        azi.a(this.l <= b2, "HealthLife_TaskManager", "upDateBasicTaskIdList basicIdList ", arrayList.toString());
        if (arrayList.size() >= 3) {
            this.f265a = arrayList;
        } else {
            this.f265a = azi.d();
        }
    }

    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void b(final int i, final TaskDayDataListener taskDayDataListener) {
        int indexOfKey;
        if (taskDayDataListener == null) {
            LogUtil.h("HealthLife_TaskManager", "getTaskList listener is null recordDay ", Integer.valueOf(i));
            return;
        }
        if (HandlerExecutor.c()) {
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: awm
                @Override // java.lang.Runnable
                public final void run() {
                    awq.this.b(i, taskDayDataListener);
                }
            });
            return;
        }
        String p = azi.p();
        int e = e(i);
        SparseArray<HealthLifeBean> nL_ = bda.nL_(kw_(e, kE_(p, e, false), p));
        nL_.remove(1);
        int size = nL_.size();
        LogUtil.a("HealthLife_TaskManager", "getTaskList recordDay ", Integer.valueOf(e), " size ", Integer.valueOf(size));
        if (size <= 0) {
            taskDayDataListener.onAllDataChange(-1, new ArrayList());
            return;
        }
        awk awkVar = new awk(size, e, new TaskDayDataListener() { // from class: awq.3
            @Override // com.huawei.basichealthmodel.listener.TaskDayDataListener
            public void onDataChange(int i2, HealthLifeBean healthLifeBean) {
            }

            @Override // com.huawei.basichealthmodel.listener.TaskDayDataListener
            public void onAllDataChange(int i2, List<HealthLifeBean> list) {
                bda.c(list);
                awq.this.f = bcm.nq_(list);
                bda.e(list);
                taskDayDataListener.onAllDataChange(i2, list);
            }
        });
        int size2 = this.d.size();
        for (int i2 = 0; i2 < size; i2++) {
            int keyAt = nL_.keyAt(i2);
            avr avrVar = (size2 <= 0 || (indexOfKey = this.d.indexOfKey(keyAt)) < 0 || indexOfKey >= size2) ? null : this.d.get(keyAt);
            if (avrVar == null) {
                avrVar = a(keyAt);
            }
            if (avrVar == null) {
                LogUtil.h("HealthLife_TaskManager", "getTaskList manager is null taskId ", Integer.valueOf(keyAt));
            } else {
                avrVar.d(nL_.get(keyAt), awkVar);
            }
        }
    }

    public int e(int i) {
        int t = azi.t();
        long currentTimeMillis = System.currentTimeMillis();
        long e = jdl.e(currentTimeMillis, 2, 30);
        boolean ac = azi.ac();
        boolean e2 = bcm.e();
        azi.a(this.l <= i, "HealthLife_TaskManager", "getRecordDay recordDay ", Integer.valueOf(i), " isSubscribeBloodPressure ", Boolean.valueOf(ac), " isSubscriptionSleep ", Boolean.valueOf(e2));
        if (i <= t) {
            return i;
        }
        if (!ac && !e2) {
            return i;
        }
        if (!ac) {
            return bcm.e(i);
        }
        if (currentTimeMillis >= e) {
            return i;
        }
        long g = CommonUtil.g(bao.e("bloodPressureMeasurePlanSaveTime"));
        if (DateFormatUtil.b(g) == i && g < e) {
            LogUtil.a("HealthLife_TaskManager", "getRecordDay time ", Long.valueOf(g), " recordDay ", Integer.valueOf(i));
            return e2 ? bcm.e(i) : i;
        }
        List<cbk> b2 = azi.b(bao.e("bloodPressureMeasurePlan"));
        if (koq.b(b2)) {
            return e2 ? bcm.e(i) : i;
        }
        return a(e2, b2, i);
    }

    private int a(boolean z, List<cbk> list, int i) {
        cbk cbkVar = null;
        cbk cbkVar2 = null;
        for (cbk cbkVar3 : list) {
            if (cbkVar3 != null) {
                int b2 = cbkVar3.b();
                if (b2 == 0) {
                    cbkVar = cbkVar3;
                } else if (b2 != 9) {
                    LogUtil.c("HealthLife_TaskManager", "getRecordDay alarmId ", Integer.valueOf(b2));
                } else {
                    cbkVar2 = cbkVar3;
                }
            }
        }
        if (cbkVar == null || cbkVar2 == null) {
            return z ? bcm.e(i) : i;
        }
        if (cbkVar.a() > cbkVar2.a()) {
            return DateFormatUtil.b(jdl.v(DateFormatUtil.c(i)));
        }
        return z ? bcm.e(i) : i;
    }

    private SparseArray<HealthLifeBean> kw_(int i, SparseArray<HealthLifeBean> sparseArray, String str) {
        List<HealthLifeBean> kv_ = kv_(i, bcm.nu_(i, i, str).get(i), sparseArray);
        LogUtil.a("HealthLife_TaskManager", "initHealthTaskBean healthTaskRecordDbBeans ", kv_.toString());
        return kt_(kv_);
    }

    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void d(final int i) {
        SparseArray<HealthLifeBean> kw_;
        HealthLifeBean healthLifeBean;
        String d;
        if (HandlerExecutor.c()) {
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: awp
                @Override // java.lang.Runnable
                public final void run() {
                    awq.this.d(i);
                }
            });
            return;
        }
        int e = e(DateFormatUtil.b(System.currentTimeMillis()));
        SparseArray<HealthLifeBean> sparseArray = this.f;
        int i2 = 1;
        if (sparseArray != null && sparseArray.get(1) != null && e == this.f.get(1).getRecordDay()) {
            LogUtil.a("HealthLife_TaskManager", "refreshTask mHealthTaskBeanList");
            kw_ = this.f;
        } else {
            String p = azi.p();
            kw_ = kw_(e, kE_(p, e, false), p);
        }
        LogUtil.a("HealthLife_TaskManager", "refreshTask recordDay ", Integer.valueOf(e), " sparseArray ", kw_);
        if (kw_ == null || (healthLifeBean = kw_.get(i)) == null) {
            return;
        }
        if (i == 5) {
            d = String.valueOf(CommonUtils.h(healthLifeBean.getResult()) + 1);
        } else {
            d = azi.d(healthLifeBean);
            i2 = 2;
        }
        if (TextUtils.isEmpty(d)) {
            LogUtil.h("HealthLife_TaskManager", "refreshTask result ", d);
        } else {
            c(i2, d, healthLifeBean, new TaskDayDataListener() { // from class: awq.1
                @Override // com.huawei.basichealthmodel.listener.TaskDayDataListener
                public void onAllDataChange(int i3, List<HealthLifeBean> list) {
                }

                @Override // com.huawei.basichealthmodel.listener.TaskDayDataListener
                public void onDataChange(int i3, HealthLifeBean healthLifeBean2) {
                    LogUtil.a("HealthLife_TaskManager", "refreshTask result", Integer.valueOf(i3), " healthTaskBean ", healthLifeBean2);
                    if (healthLifeBean2 == null || awq.this.f == null) {
                        return;
                    }
                    awq.this.f.append(healthLifeBean2.getId(), healthLifeBean2);
                }
            });
        }
    }

    public void c(int i, String str, HealthLifeBean healthLifeBean, final TaskDayDataListener taskDayDataListener) {
        LogUtil.a("HealthLife_TaskManager", "refreshTaskById enter");
        if (healthLifeBean == null || taskDayDataListener == null) {
            if (taskDayDataListener != null) {
                taskDayDataListener.onDataChange(-1, null);
            }
            LogUtil.h("HealthLife_TaskManager", "triggerHealthTaskManager healthTaskBean or taskSubscriptionListener is null");
        } else {
            if (i == 1 || i == 2) {
                avr avrVar = this.d.get(healthLifeBean.getId());
                if (avrVar == null) {
                    avrVar = new avr();
                }
                avrVar.c(i, str, healthLifeBean, new TaskDayDataListener() { // from class: awq.5
                    @Override // com.huawei.basichealthmodel.listener.TaskDayDataListener
                    public void onAllDataChange(int i2, List<HealthLifeBean> list) {
                    }

                    @Override // com.huawei.basichealthmodel.listener.TaskDayDataListener
                    public void onDataChange(int i2, HealthLifeBean healthLifeBean2) {
                        if (i2 == 0 && awq.this.f != null && healthLifeBean2 != null) {
                            awq.this.f.append(healthLifeBean2.getId(), healthLifeBean2);
                        }
                        taskDayDataListener.onDataChange(i2, healthLifeBean2);
                        avm.a().j();
                        if (healthLifeBean2 != null) {
                            int recordDay = healthLifeBean2.getRecordDay();
                            awj.a().b(azi.p(), recordDay, recordDay, 4);
                        }
                    }
                });
                return;
            }
            b(i, str, healthLifeBean, taskDayDataListener);
        }
    }

    public void c(final boolean z, final String str, final ResponseCallback<Object> responseCallback) {
        int i;
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("HealthLife_TaskManager", "rewardTaskIds ids is empty ");
            if (responseCallback != null) {
                responseCallback.onResponse(-1, "");
                return;
            }
            return;
        }
        if (HandlerExecutor.c()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: awx
                @Override // java.lang.Runnable
                public final void run() {
                    awq.this.e(z, str, responseCallback);
                }
            });
            return;
        }
        int b2 = DateFormatUtil.b(System.currentTimeMillis());
        String p = azi.p();
        List<HealthLifeBean> b3 = b(str.split(","), b2, p);
        if (z) {
            Iterator<HealthLifeBean> it = b3.iterator();
            while (true) {
                if (!it.hasNext()) {
                    i = 1;
                    break;
                }
                HealthLifeBean next = it.next();
                if (next != null && next.getComplete() == 4) {
                    i = 4;
                    break;
                }
            }
            b3.add(bcm.a(str, b2, i, 1, p));
            b3.add(bcm.a(str, b2, 1, 100001, p));
        }
        LogUtil.c("HealthLife_TaskManager", "rewardTaskIds beanList ", b3.toString());
        long a2 = auz.a(b3, p);
        LogUtil.a("HealthLife_TaskManager", "rewardTaskIds insertTaskList codeInsert ", Long.valueOf(a2), " codeDelete ", Long.valueOf((!z || a2 < 0) ? -1L : bcm.d(p, b2, str)));
        awj.a().b(p, b2, b2, 4);
        if (responseCallback != null) {
            responseCallback.onResponse(0, "");
        }
    }

    /* synthetic */ void e(boolean z, String str, ResponseCallback responseCallback) {
        c(z, str, (ResponseCallback<Object>) responseCallback);
    }

    private List<HealthLifeBean> b(String[] strArr, int i, String str) {
        ArrayList arrayList = new ArrayList();
        for (String str2 : strArr) {
            int h = CommonUtil.h(str2);
            if (h > 0) {
                HealthLifeBean a2 = auz.a(h, i, str);
                if (azi.j(a2)) {
                    if (a2.getComplete() < 1) {
                        LogUtil.c("HealthLife_TaskManager", "healthTaskRecordDbBean id ", Integer.valueOf(a2.getId()));
                        d(a2);
                        arrayList.add(a2);
                    }
                } else {
                    HealthLifeBean healthLifeBean = new HealthLifeBean();
                    healthLifeBean.setId(h);
                    healthLifeBean.setRecordDay(i);
                    healthLifeBean.setResult("");
                    d(healthLifeBean);
                    SparseArray<TaskConfigBean> kD_ = kD_();
                    if (kD_ == null || kD_.get(h) == null) {
                        LogUtil.h("HealthLife_TaskManager", "rewardTaskIds mTaskConfigList or healthTaskConfig is null");
                        arrayList.add(healthLifeBean);
                    } else {
                        TaskConfigBean taskConfigBean = kD_.get(h);
                        healthLifeBean.setType(taskConfigBean.getType());
                        if (h == 11 || h == 12) {
                            healthLifeBean.setTarget(String.valueOf(0));
                        } else {
                            healthLifeBean.setTarget(taskConfigBean.getDefaultGoal());
                        }
                        arrayList.add(healthLifeBean);
                    }
                }
            }
        }
        return arrayList;
    }

    private void b(int i, String str, final HealthLifeBean healthLifeBean, final TaskDayDataListener taskDayDataListener) {
        int complete = healthLifeBean.getComplete();
        if (i == 3) {
            if (complete <= 0) {
                complete = 2;
            }
            healthLifeBean.setRest(1);
        } else {
            if (i != 4) {
                return;
            }
            if (complete <= 0) {
                complete = 3;
            }
            healthLifeBean.setResult(str);
        }
        if (healthLifeBean.getComplete() <= 0) {
            aza.d(healthLifeBean.getId(), complete);
        }
        long currentTimeMillis = System.currentTimeMillis();
        healthLifeBean.setComplete(complete);
        healthLifeBean.setSyncStatus(0);
        healthLifeBean.setTimestamp(currentTimeMillis);
        healthLifeBean.setTimeZone(jdl.q(currentTimeMillis));
        azi.b(ThreadPoolManager.d(), "HealthModelRefreshTask", new Runnable() { // from class: awz
            @Override // java.lang.Runnable
            public final void run() {
                awq.this.a(healthLifeBean, taskDayDataListener);
            }
        });
    }

    /* synthetic */ void a(HealthLifeBean healthLifeBean, TaskDayDataListener taskDayDataListener) {
        if (auz.a(healthLifeBean, azi.p()) < 0) {
            taskDayDataListener.onDataChange(-1, healthLifeBean);
            return;
        }
        SparseArray<HealthLifeBean> sparseArray = this.f;
        if (sparseArray != null) {
            sparseArray.append(healthLifeBean.getId(), healthLifeBean);
        }
        taskDayDataListener.onDataChange(0, healthLifeBean);
        avm.a().j();
        awj.a().b(azi.p(), healthLifeBean.getRecordDay(), healthLifeBean.getRecordDay(), 4);
    }

    public ConfigBean a() {
        ConfigBean b2;
        if (this.g == null) {
            this.g = new awb().d(this.h, this.j);
        }
        ConfigBean configBean = this.g;
        if (configBean != null && koq.b(configBean.getChallengeList()) && (b2 = this.i.b(this.h)) != null) {
            this.g.setChallengeList(b2.getChallengeList());
            this.g.setChallengeMotivationImage(b2.getChallengeMotivationImage());
        }
        return this.g;
    }

    public SparseArray<TaskConfigBean> kD_() {
        AtomicBoolean e = awb.e();
        if (this.n == null || (e != null && e.get())) {
            LogUtil.a("HealthLife_TaskManager", "getHealthTaskConfigList load task mIsOversea=", Boolean.valueOf(this.h));
            ConfigBean d = this.i.d(this.h, this.j);
            this.g = d;
            this.n = awb.kg_(d);
        }
        return this.n;
    }

    public SparseArray<TaskConfigBean> kC_(boolean z) {
        SparseArray<TaskConfigBean> sparseArray;
        kD_();
        boolean b2 = bah.b();
        boolean isLogined = LoginInit.getInstance(BaseApplication.getContext()).getIsLogined();
        LogUtil.a("HealthLife_TaskManager", "getGoalPagerTaskConfigList isJoin=", Boolean.valueOf(b2), ",isLogin=", Boolean.valueOf(isLogined));
        if (!isLogined || b2 || !z || (sparseArray = this.n) == null || sparseArray.size() == 0) {
            return this.n;
        }
        SparseArray<TaskConfigBean> sparseArray2 = new SparseArray<>();
        b();
        int size = this.n.size();
        for (int i = 0; i < size; i++) {
            int keyAt = this.n.keyAt(i);
            if (this.f265a.contains(Integer.valueOf(keyAt)) || keyAt == 1 || keyAt == 100001) {
                sparseArray2.put(keyAt, this.n.get(keyAt));
            }
        }
        return sparseArray2;
    }

    public SparseArray<ChallengeConfigBean> kB_() {
        if (this.e == null) {
            this.e = awb.kf_(this.i.b(this.h));
        }
        return this.e;
    }

    public ChallengeConfigBean b(int i) {
        SparseArray<ChallengeConfigBean> kB_ = kB_();
        if (kB_ == null) {
            LogUtil.h("HealthLife_TaskManager", "challengeList is null");
            return null;
        }
        return kB_.get(i);
    }

    public void a(final String str, final int i, final ResponseCallback<SparseArray<HealthLifeBean>> responseCallback) {
        if (responseCallback == null) {
            LogUtil.h("HealthLife_TaskManager", "getTaskSubscriptionListFromCloud callback is null.");
        } else {
            final int t = azi.t();
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: aws
                @Override // java.lang.Runnable
                public final void run() {
                    awq.this.a(t, str, i, responseCallback);
                }
            });
        }
    }

    /* synthetic */ void a(int i, String str, int i2, ResponseCallback responseCallback) {
        boolean z;
        if (i == 0 || !this.j) {
            LogUtil.a("HealthLife_TaskManager", "getTaskSubscriptionListFromCloud from config");
            z = false;
        } else {
            z = true;
        }
        responseCallback.onResponse(0, kE_(str, i2, z));
    }

    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void a(final String str, final int i, final TaskDataListener taskDataListener) {
        if (HandlerExecutor.c()) {
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: awn
                @Override // java.lang.Runnable
                public final void run() {
                    awq.this.a(str, i, taskDataListener);
                }
            });
        } else {
            int e = e(i);
            d(str, e, azi.f(e), taskDataListener);
        }
    }

    private void d(String str, int i, List<Integer> list, TaskDataListener taskDataListener) {
        if (koq.b(list) || list.size() != 7) {
            LogUtil.h("HealthLife_TaskManager", "getWeekTaskRecords weekDayList ", list);
            return;
        }
        int t = azi.t();
        int intValue = list.get(0).intValue();
        if (t > intValue) {
            intValue = t;
        }
        if (t <= 0) {
            intValue = i;
        }
        int intValue2 = list.get(6).intValue();
        int e = e(DateFormatUtil.b(System.currentTimeMillis()));
        if (intValue2 > e) {
            intValue2 = e;
        }
        azi.a(this.l <= i, "HealthLife_TaskManager", "getWeekTaskRecords weekStartDay ", Integer.valueOf(intValue), " weekEndDay ", Integer.valueOf(intValue2), " currentDay ", Integer.valueOf(e), " recordDay ", Integer.valueOf(i));
        SparseArray<SparseArray<HealthLifeBean>> nu_ = bcm.nu_(intValue, intValue2, str);
        SparseArray<List<HealthLifeBean>> sparseArray = new SparseArray<>();
        long c2 = DateFormatUtil.c(intValue);
        int e2 = e(list, intValue2);
        for (int i2 = 0; i2 <= e2; i2++) {
            int b2 = DateFormatUtil.b(jdl.d(c2, i2));
            if (b2 <= e) {
                sparseArray.append(b2, kv_(b2, nu_.get(b2), kE_(str, b2, false)));
            }
        }
        ky_(str, i, ku_(sparseArray), taskDataListener);
    }

    private int e(List<Integer> list, int i) {
        if (koq.b(list)) {
            return 0;
        }
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            if (list.get(i2).intValue() == i) {
                return i2;
            }
        }
        return 0;
    }

    private List<HealthLifeBean> kv_(int i, SparseArray<HealthLifeBean> sparseArray, SparseArray<HealthLifeBean> sparseArray2) {
        ArrayList arrayList = new ArrayList();
        if (sparseArray2.size() == 0) {
            LogUtil.h("HealthLife_TaskManager", "subscriptionDbBeans is null in day: ", Integer.valueOf(i));
            return arrayList;
        }
        SparseArray<TaskConfigBean> kD_ = kD_();
        for (int i2 = 0; i2 < sparseArray2.size(); i2++) {
            int keyAt = sparseArray2.keyAt(i2);
            HealthLifeBean healthLifeBean = sparseArray2.get(keyAt);
            if (healthLifeBean == null) {
                LogUtil.h("HealthLife_TaskManager", "subscriptionDbBean is null in id: ", Integer.valueOf(keyAt));
            } else if (keyAt == 1 || healthLifeBean.getAddStatus() == 1) {
                HealthLifeBean healthLifeBean2 = sparseArray != null ? sparseArray.get(keyAt) : null;
                if (healthLifeBean2 == null) {
                    long currentTimeMillis = System.currentTimeMillis();
                    HealthLifeBean healthLifeBean3 = new HealthLifeBean();
                    healthLifeBean3.setTimeZone(jdl.q(currentTimeMillis));
                    healthLifeBean3.setTimestamp(currentTimeMillis);
                    healthLifeBean3.setRecordDay(i);
                    healthLifeBean3.setId(keyAt);
                    healthLifeBean2 = healthLifeBean3;
                }
                healthLifeBean2.setChallengeId(healthLifeBean.getChallengeId());
                healthLifeBean2.setTaskType(healthLifeBean.getTaskType());
                healthLifeBean2.setDataSource(healthLifeBean.getDataSource());
                healthLifeBean2.setIsUpdated(true);
                if (keyAt == 1) {
                    arrayList.add(healthLifeBean2);
                } else if (kD_ == null || kD_.get(keyAt) == null) {
                    c(healthLifeBean, healthLifeBean2, arrayList);
                } else {
                    TaskConfigBean taskConfigBean = kD_.get(keyAt);
                    if (keyAt != 11 && keyAt != 12) {
                        healthLifeBean2.setTarget(taskConfigBean.getDefaultGoal());
                    }
                    healthLifeBean2.setType(taskConfigBean.getType());
                    c(healthLifeBean, healthLifeBean2, arrayList);
                }
            }
        }
        return arrayList;
    }

    private void c(HealthLifeBean healthLifeBean, HealthLifeBean healthLifeBean2, List<HealthLifeBean> list) {
        String target = healthLifeBean.getTarget();
        if (!TextUtils.isEmpty(target)) {
            healthLifeBean2.setTarget(target);
        }
        list.add(healthLifeBean2);
    }

    private SparseArray<List<HealthLifeBean>> ku_(SparseArray<List<HealthLifeBean>> sparseArray) {
        ArrayList<HealthLifeBean> arrayList = new ArrayList();
        for (int i = 0; i < sparseArray.size(); i++) {
            arrayList.addAll(sparseArray.get(sparseArray.keyAt(i)));
        }
        SparseArray<List<HealthLifeBean>> sparseArray2 = new SparseArray<>();
        for (HealthLifeBean healthLifeBean : arrayList) {
            List<HealthLifeBean> list = sparseArray2.get(healthLifeBean.getId());
            if (koq.b(list)) {
                list = new ArrayList<>();
            }
            list.add(healthLifeBean);
            sparseArray2.append(healthLifeBean.getId(), list);
        }
        return sparseArray2;
    }

    private SparseArray<HealthLifeBean> kt_(List<HealthLifeBean> list) {
        if (koq.b(list)) {
            LogUtil.h("HealthLife_TaskManager", "convertListToSparseArray healthTaskRecordDbBeans is empty.");
            return new SparseArray<>();
        }
        SparseArray<HealthLifeBean> sparseArray = new SparseArray<>(list.size());
        for (HealthLifeBean healthLifeBean : list) {
            sparseArray.append(healthLifeBean.getId(), healthLifeBean);
        }
        return sparseArray;
    }

    private void ky_(String str, int i, SparseArray<List<HealthLifeBean>> sparseArray, TaskDataListener taskDataListener) {
        azi.a(this.l <= i, "HealthLife_TaskManager", "triggerUpdateWeekData sparseArrayIdList ", sparseArray);
        awl awlVar = new awl(str, i, taskDataListener, sparseArray.size());
        for (int i2 = 0; i2 < sparseArray.size(); i2++) {
            int keyAt = sparseArray.keyAt(i2);
            avr a2 = a(keyAt);
            List<HealthLifeBean> list = sparseArray.get(keyAt);
            SparseArray<HealthLifeBean> sparseArray2 = new SparseArray<>(list.size());
            for (HealthLifeBean healthLifeBean : list) {
                sparseArray2.append(healthLifeBean.getRecordDay(), healthLifeBean);
            }
            if (a2 != null) {
                a2.getRecord(sparseArray2, awlVar);
            }
        }
    }

    public void e(ArrayList<HealthLifeBean> arrayList, ResponseCallback<SparseArray<HealthLifeBean>> responseCallback) {
        e(false, "", arrayList, responseCallback);
    }

    public void e(final boolean z, final String str, final ArrayList<HealthLifeBean> arrayList, final ResponseCallback<SparseArray<HealthLifeBean>> responseCallback) {
        if (HandlerExecutor.c()) {
            azi.b(ThreadPoolManager.d(), "HealthModelUpdateSubscriptionList", new Runnable() { // from class: awt
                @Override // java.lang.Runnable
                public final void run() {
                    awq.this.a(z, str, arrayList, responseCallback);
                }
            });
            return;
        }
        if (responseCallback == null) {
            LogUtil.h("HealthLife_TaskManager", "updateTaskSubscriptionList callback is null.");
            return;
        }
        if (koq.b(arrayList)) {
            LogUtil.h("HealthLife_TaskManager", "updateTaskSubscriptionList subscriptionDbBeans is null.");
            responseCallback.onResponse(-1, null);
            return;
        }
        a(arrayList);
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        Iterator<HealthLifeBean> it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.add(bax.b(it.next()));
        }
        if (!this.j) {
            d(z, str, arrayList, responseCallback);
        } else {
            aug.d().d(z, arrayList2, new IBaseResponseCallback() { // from class: awr
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    awq.this.d(responseCallback, z, str, arrayList, i, obj);
                }
            });
        }
    }

    /* synthetic */ void a(boolean z, String str, ArrayList arrayList, ResponseCallback responseCallback) {
        e(z, str, (ArrayList<HealthLifeBean>) arrayList, (ResponseCallback<SparseArray<HealthLifeBean>>) responseCallback);
    }

    /* synthetic */ void d(ResponseCallback responseCallback, boolean z, String str, ArrayList arrayList, int i, Object obj) {
        if (i == -1) {
            LogUtil.h("HealthLife_TaskManager", "updateTaskSubscriptionList addHealthLifeConfig onFailure ");
            responseCallback.onResponse(-1, null);
        } else {
            if (!(obj instanceof auh)) {
                LogUtil.h("HealthLife_TaskManager", "updateTaskSubscriptionList onSuccess data is null");
                responseCallback.onResponse(-1, null);
                return;
            }
            int a2 = ((auh) obj).a();
            if (a2 != 0) {
                LogUtil.h("HealthLife_TaskManager", "updateTaskSubscriptionList onSuccess resultCode is ", Integer.valueOf(a2));
                responseCallback.onResponse(a2, null);
            } else {
                d(z, str, (ArrayList<HealthLifeBean>) arrayList, (ResponseCallback<SparseArray<HealthLifeBean>>) responseCallback);
            }
        }
    }

    private void a(ArrayList<HealthLifeBean> arrayList) {
        if (azi.y()) {
            LogUtil.a("HealthLife_TaskManager", "compatibleOldVersion basic changed");
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        Iterator<HealthLifeBean> it = arrayList.iterator();
        HealthLifeBean healthLifeBean = null;
        int i = 0;
        HealthLifeBean healthLifeBean2 = null;
        while (it.hasNext()) {
            HealthLifeBean next = it.next();
            if (next != null) {
                int challengeId = next.getChallengeId();
                if (i == 0 && challengeId > 0) {
                    i = challengeId;
                }
                if (next.getId() == 1) {
                    if (next.getRecordDay() == DateFormatUtil.b(currentTimeMillis)) {
                        healthLifeBean = next;
                    } else {
                        healthLifeBean2 = next;
                    }
                }
            }
        }
        a(arrayList, healthLifeBean, healthLifeBean2, i);
    }

    private void a(ArrayList<HealthLifeBean> arrayList, HealthLifeBean healthLifeBean, HealthLifeBean healthLifeBean2, int i) {
        if (healthLifeBean == null || healthLifeBean2 == null) {
            LogUtil.h("HealthLife_TaskManager", "compatibleOldVersion todayCloverBean or tomorrowCloverBean is null");
            return;
        }
        String target = healthLifeBean2.getTarget();
        if (TextUtils.isEmpty(target)) {
            LogUtil.h("HealthLife_TaskManager", "compatibleOldVersion target is empty");
            return;
        }
        ArrayList arrayList2 = new ArrayList(Arrays.asList(target.split(",")));
        if (koq.b(arrayList2)) {
            LogUtil.h("HealthLife_TaskManager", "compatibleOldVersion targetList is empty");
            return;
        }
        List<Integer> i2 = i(i);
        SparseArray<TaskConfigBean> kD_ = kD_();
        if (kD_ == null || kD_.size() == 0) {
            LogUtil.h("HealthLife_TaskManager", "compatibleOldVersion taskConfigList is empty");
            return;
        }
        int recordDay = healthLifeBean.getRecordDay();
        TimeZone d = jdl.d(healthLifeBean.getTimeZone());
        int c2 = DateFormatUtil.c(jdl.s(DateFormatUtil.c(recordDay, d), d), d);
        Iterator<Integer> it = i2.iterator();
        boolean z = false;
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            if (!arrayList2.contains(String.valueOf(intValue))) {
                arrayList2.add(String.valueOf(intValue));
                TaskConfigBean taskConfigBean = kD_.get(intValue);
                c(arrayList, taskConfigBean, intValue, recordDay, i);
                c(arrayList, taskConfigBean, intValue, c2, i);
                LogUtil.a("HealthLife_TaskManager", "compatibleOldVersion basicId ", Integer.valueOf(intValue));
                z = true;
            }
        }
        if (z) {
            String replace = arrayList2.toString().replace("[", "").replace("]", "").replace(" ", "");
            healthLifeBean.setLastTarget(healthLifeBean2.getTarget());
            healthLifeBean2.setLastTarget(replace);
            healthLifeBean.setTarget(replace);
            healthLifeBean2.setTarget(replace);
        }
    }

    private void c(ArrayList<HealthLifeBean> arrayList, TaskConfigBean taskConfigBean, int i, int i2, int i3) {
        Iterator<HealthLifeBean> it = arrayList.iterator();
        boolean z = false;
        while (it.hasNext()) {
            HealthLifeBean next = it.next();
            if (next != null && next.getId() == i && next.getRecordDay() == i2) {
                z = true;
                next.setTaskType(1);
                next.setChallengeId(i3);
            }
        }
        if (z) {
            return;
        }
        arrayList.add(d(taskConfigBean, i, i2, i3));
    }

    private HealthLifeBean d(TaskConfigBean taskConfigBean, int i, int i2, int i3) {
        HealthLifeBean healthLifeBean = new HealthLifeBean();
        healthLifeBean.setId(i);
        healthLifeBean.setRecordDay(i2);
        healthLifeBean.setTaskType(1);
        long c2 = DateFormatUtil.c(i2);
        healthLifeBean.setTimeZone(jdl.q(c2));
        healthLifeBean.setTimestamp(c2);
        healthLifeBean.setChallengeId(i3);
        if (taskConfigBean != null) {
            healthLifeBean.setTarget(taskConfigBean.getDefaultGoal());
        }
        LogUtil.a("HealthLife_TaskManager", "compatibleOldVersion lack lifeBean = ", healthLifeBean.toString());
        return healthLifeBean;
    }

    private List<Integer> i(int i) {
        ArrayList<Integer> b2 = b();
        ArrayList<Integer> a2 = azi.a();
        if (koq.c(a2) && a2.contains(Integer.valueOf(i))) {
            b2.clear();
            Iterator<Integer> it = azi.b().iterator();
            while (it.hasNext()) {
                int intValue = it.next().intValue();
                if (intValue != 2) {
                    b2.add(Integer.valueOf(intValue));
                }
            }
            LogUtil.a("HealthLife_TaskManager", "handleBasicTask basicIdList=", b2);
        }
        return b2;
    }

    private void d(boolean z, String str, ArrayList<HealthLifeBean> arrayList, ResponseCallback<SparseArray<HealthLifeBean>> responseCallback) {
        if (z) {
            LogUtil.a("HealthLife_TaskManager", "insertTaskComplete result ", Integer.valueOf(aus.e(DateFormatUtil.b(System.currentTimeMillis()))));
        }
        a(str, arrayList, responseCallback);
    }

    private void kA_(SparseArray<HealthLifeBean> sparseArray) {
        avr a2;
        if (sparseArray == null || sparseArray.size() == 0 || sparseArray.get(1) == null) {
            LogUtil.h("HealthLife_TaskManager", "updateSubscriptionList bean is null or not valid");
            return;
        }
        this.k.clear();
        for (int i = 0; i < sparseArray.size(); i++) {
            int keyAt = sparseArray.keyAt(i);
            HealthLifeBean healthLifeBean = sparseArray.get(keyAt);
            this.k.append(keyAt, healthLifeBean);
            if (healthLifeBean != null && healthLifeBean.getAddStatus() == 1 && (a2 = a(keyAt)) != null) {
                this.d.append(keyAt, a2);
            }
        }
        LogUtil.a("HealthLife_TaskManager", "updateSubscriptionList mAddedTaskManagerMap size:", Integer.valueOf(this.d.size()));
    }

    private void a(String str, ArrayList<HealthLifeBean> arrayList, ResponseCallback<SparseArray<HealthLifeBean>> responseCallback) {
        int i;
        if (koq.b(arrayList)) {
            LogUtil.h("HealthLife_TaskManager", "insertTaskSubscriptionList subscriptionDbBeans is null");
            responseCallback.onResponse(-1, null);
            return;
        }
        String p = azi.p();
        long c2 = aus.c(arrayList, p);
        if (c2 <= -1) {
            LogUtil.h("HealthLife_TaskManager", "insertTaskSubscriptionList subscriptionDbBeans result:", Long.valueOf(c2));
            responseCallback.onResponse(-1, null);
            return;
        }
        SparseArray<HealthLifeBean> sparseArray = new SparseArray<>(arrayList.size());
        Iterator<HealthLifeBean> it = arrayList.iterator();
        while (it.hasNext()) {
            HealthLifeBean next = it.next();
            sparseArray.append(next.getId(), next);
        }
        if (sparseArray.get(1) != null) {
            i = sparseArray.get(1).getRecordDay();
            if (auz.b(p, i) != 0) {
                LogUtil.h("HealthLife_TaskManager", "deleteAfter fail, day is ", Integer.valueOf(i));
            }
        } else {
            i = 0;
        }
        if (i > 0) {
            SparseArray<HealthLifeBean> kw_ = kw_(i, sparseArray, p);
            ArrayList arrayList2 = new ArrayList(10);
            for (int i2 = 0; i2 < kw_.size(); i2++) {
                arrayList2.add(kw_.valueAt(i2));
            }
            auz.a(arrayList2, p);
        }
        if (!niv.a(com.huawei.haf.application.BaseApplication.e()) && !"UPDATE_GOAL".equals(str) && !"cloverLife".equals(str) && !"EXIT_HEALTH_GOAL".equals(str)) {
            LogUtil.a("HealthLife_TaskManager", "insertTaskSubscriptionList from ", str);
            kx_(sparseArray);
        }
        responseCallback.onResponse(0, sparseArray);
    }

    private void kx_(SparseArray<HealthLifeBean> sparseArray) {
        if (sparseArray == null || sparseArray.size() == 0) {
            LogUtil.h("HealthLife_TaskManager", "refreshMotionGoal sparseArray is empty");
            return;
        }
        HealthLifeBean healthLifeBean = sparseArray.get(2);
        HealthLifeBean healthLifeBean2 = sparseArray.get(3);
        if (healthLifeBean == null || healthLifeBean2 == null) {
            LogUtil.h("HealthLife_TaskManager", "refreshMotionGoal stepBean or intensityBean is null");
            return;
        }
        final int h = CommonUtils.h(healthLifeBean.getTarget());
        final int h2 = CommonUtils.h(healthLifeBean2.getTarget());
        ArrayList arrayList = new ArrayList(2);
        arrayList.add("900200006");
        arrayList.add("900200008");
        nip.a(arrayList, new IBaseResponseCallback() { // from class: awv
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                awq.e(h, h2, i, obj);
            }
        });
    }

    static /* synthetic */ void e(int i, int i2, int i3, Object obj) {
        if (!(obj instanceof Map)) {
            LogUtil.h("HealthLife_TaskManager", "refreshMotionGoal onResponse object instanceof Map false");
            return;
        }
        HashMap hashMap = (HashMap) obj;
        int e = nip.e(hashMap, "900200006", 0);
        int e2 = nip.e(hashMap, "900200008", 0);
        LogUtil.a("HealthLife_TaskManager", "refreshMotionGoal onResponse stepTarget ", Integer.valueOf(i), " intensityTarget ", Integer.valueOf(i2), " stepGoal ", Integer.valueOf(e), " intensityGoal ", Integer.valueOf(e2));
        if (i > 0 && i != e) {
            nip.e("900200006", i);
        }
        if (i2 <= 0 || i2 == e2) {
            return;
        }
        nip.e("900200008", i2);
    }

    private avr a(int i) {
        switch (i) {
            case 2:
                return new awh();
            case 3:
                return new awf();
            case 4:
                return new aww();
            case 5:
                return new avv();
            case 6:
                return new awy();
            case 7:
                return new awg();
            case 8:
            case 9:
                return new avr();
            case 10:
                return new avz();
            case 11:
                return new awe();
            case 12:
                return new avp();
            case 13:
                return new avu();
            case 14:
                return new axa();
            default:
                LogUtil.c("HealthLife_TaskManager", "getBaseHealthTaskById id ", Integer.valueOf(i));
                if (i >= 1) {
                    return new avr();
                }
                return null;
        }
    }

    public SparseArray<HealthLifeBean> kE_(String str, int i, boolean z) {
        List<HealthLifeBean> jS_;
        SparseArray<TaskConfigBean> kD_ = kD_();
        if (z && CommonUtil.aa(BaseApplication.getContext())) {
            String url = GRSManager.a(BaseApplication.getContext()).getUrl("achievementUrl");
            auo c2 = aue.e().c(url, i, i);
            azi.a(this.l <= i, "HealthLife_TaskManager", "getHealthTaskSubscriptionList host ", url, " result ", c2);
            if (c2 == null || c2.a() != 0 || koq.b(c2.e())) {
                LogUtil.h("HealthLife_TaskManager", "getHealthTaskSubscriptionList getHealthLifeConfigSync result is empty");
                jS_ = aus.jS_(str, i, kD_, false);
            } else {
                SparseArray<HealthLifeBean> nA_ = bcm.nA_(str, c2, kD_);
                if (nA_.size() <= 3) {
                    jS_ = aus.jS_(str, i, kD_, false);
                } else {
                    ks_(nA_);
                    kz_(i, nA_);
                    return nA_;
                }
            }
        } else {
            jS_ = aus.jS_(str, i, kD_, false);
        }
        azi.a(this.l <= i, "HealthLife_TaskManager", "getHealthTaskSubscriptionList recordDay ", Integer.valueOf(i), " isFromCloud ", Boolean.valueOf(z), " taskSubscriptionList ", jS_);
        SparseArray<HealthLifeBean> mv_ = bax.mv_(jS_);
        kz_(i, mv_);
        return mv_;
    }

    private void ks_(SparseArray<HealthLifeBean> sparseArray) {
        HealthLifeBean healthLifeBean = sparseArray.get(1);
        if (healthLifeBean == null) {
            LogUtil.a("HealthLife_TaskManager", "handleDoctorCloudData cloverBean is null");
            return;
        }
        int recordDay = healthLifeBean.getRecordDay();
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            HealthLifeBean valueAt = sparseArray.valueAt(i);
            if (valueAt != null && valueAt.getRecordDay() != recordDay) {
                valueAt.setTaskType(0);
                valueAt.setChallengeId(0);
                valueAt.setDataSource(0);
            }
        }
    }
}
