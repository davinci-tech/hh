package defpackage;

import android.os.Looper;
import android.text.TextUtils;
import android.util.SparseArray;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.gson.reflect.TypeToken;
import com.huawei.basichealthmodel.bean.ChallengeConfigBean;
import com.huawei.basichealthmodel.bean.TaskConfigBean;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.framework.network.download.internal.constants.ExceptionCode;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* loaded from: classes.dex */
public class bcm {
    public static List<Integer> nx_(SparseArray<TaskConfigBean> sparseArray) {
        if (sparseArray == null) {
            sparseArray = awq.e().kD_();
        }
        if (sparseArray.size() == 0) {
            LogUtil.h("HealthLife_TaskHelper", "getTaskIdList is null.");
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < sparseArray.size(); i++) {
            int keyAt = sparseArray.keyAt(i);
            if (keyAt != 1 && keyAt != 100001) {
                arrayList.add(Integer.valueOf(keyAt));
            }
        }
        return arrayList;
    }

    public static int a(int i, String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        String[] split = str.split(" ");
        if (split.length < 2) {
            return 0;
        }
        String[] split2 = split[1].split(":");
        if (split2.length < 2) {
            return 0;
        }
        return ((int) (jdl.e(DateFormatUtil.c(CommonUtil.m(BaseApplication.getContext(), split[0])), CommonUtil.h(split2[0]), CommonUtil.h(split2[1])) - DateFormatUtil.c(i))) / 60000;
    }

    public static int d(int i, String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        String[] split = str.split(":");
        if (split.length < 2) {
            return 0;
        }
        int h = CommonUtil.h(split[0]);
        int h2 = CommonUtil.h(split[1]);
        long c = DateFormatUtil.c(i);
        return ((int) (jdl.e(c, h, h2) - c)) / 60000;
    }

    public static int c(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        String[] split = str.split(":");
        if (split.length < 2) {
            return 0;
        }
        return (CommonUtil.h(split[0]) * 60) + CommonUtil.h(split[1]);
    }

    public static String a(int i, int i2) {
        Object valueOf;
        Object valueOf2;
        int i3 = i2 / 60;
        int i4 = i2 % 60;
        if (i3 >= 24) {
            i = DateFormatUtil.b(jdl.y(DateFormatUtil.c(i)));
            i3 -= 24;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        sb.append(" ");
        if (i3 < 10) {
            valueOf = "0" + i3;
        } else {
            valueOf = Integer.valueOf(i3);
        }
        sb.append(valueOf);
        sb.append(":");
        if (i4 < 10) {
            valueOf2 = "0" + i4;
        } else {
            valueOf2 = Integer.valueOf(i4);
        }
        sb.append(valueOf2);
        return sb.toString();
    }

    public static List<HealthLifeBean> nz_(List<HealthLifeBean> list, SparseArray<TaskConfigBean> sparseArray) {
        if (koq.b(list)) {
            LogUtil.b("HealthLife_TaskHelper", "jsonArrayToHealthTaskRecordDbBeanList records is null");
            return new ArrayList(16);
        }
        if (sparseArray == null) {
            LogUtil.b("HealthLife_TaskHelper", "jsonArrayToHealthTaskRecordDbBeanList taskConfigSparseArray is null");
            return list;
        }
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        for (HealthLifeBean healthLifeBean : list) {
            if (healthLifeBean != null) {
                healthLifeBean.setHuid(accountInfo);
                healthLifeBean.setSyncStatus(1);
                TaskConfigBean taskConfigBean = sparseArray.get(healthLifeBean.getId());
                if (taskConfigBean != null) {
                    healthLifeBean.setType(taskConfigBean.getType());
                }
            }
        }
        return list;
    }

    public static List<HealthLifeBean> e(List<HealthLifeBean> list) {
        if (koq.b(list)) {
            return new ArrayList(16);
        }
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
        for (HealthLifeBean healthLifeBean : list) {
            if (healthLifeBean != null) {
                healthLifeBean.setHuid(accountInfo);
                healthLifeBean.setSyncStatus(1);
            }
        }
        return list;
    }

    public static HealthLifeBean a(String str, int i, int i2, int i3, String str2) {
        HealthLifeBean a2 = auz.a(i3, i, str2);
        if (!azi.j(a2)) {
            a2 = new HealthLifeBean();
            a2.setId(i3);
            a2.setRecordDay(i);
            long currentTimeMillis = System.currentTimeMillis();
            a2.setTimestamp(currentTimeMillis);
            a2.setTimeZone(jdl.q(currentTimeMillis));
            a2.setResult("");
            if (i2 >= 1) {
                a2.setSyncStatus(0);
            }
            a2.setIsUpdated(true);
        } else if (a2.getComplete() == 0 && i2 >= 1) {
            a2.setComplete(i2);
            long currentTimeMillis2 = System.currentTimeMillis();
            a2.setTimestamp(currentTimeMillis2);
            a2.setTimeZone(jdl.q(currentTimeMillis2));
            a2.setIsUpdated(true);
            a2.setSyncStatus(0);
        } else {
            LogUtil.c("HealthLife_TaskHelper", "getCloverBean others.");
        }
        a2.setTarget(str);
        return a2;
    }

    public static void e(final String str) {
        if (HandlerExecutor.c()) {
            azi.b(ThreadPoolManager.d(), "HealthModelGetTaskList", new Runnable() { // from class: bcq
                @Override // java.lang.Runnable
                public final void run() {
                    bcm.b(str);
                }
            });
            return;
        }
        long currentTimeMillis = System.currentTimeMillis();
        int b = DateFormatUtil.b(currentTimeMillis);
        SparseArray<HealthLifeBean> kE_ = awq.e().kE_(str, b, true);
        if (kE_ == null || kE_.size() == 0) {
            LogUtil.h("HealthLife_TaskHelper", "refreshCurrentEffectiveTaskRecord currentRecord is empty");
        } else if (currentTimeMillis > jdl.e(currentTimeMillis, 1, 0) || azi.t() >= b) {
            nB_(kE_);
        } else {
            nr_(str, currentTimeMillis, kE_);
        }
    }

    static /* synthetic */ void b(String str) {
        LogUtil.a("HealthLife_TaskHelper", "refreshCurrentEffectiveTaskRecord is in MainLooper");
        e(str);
    }

    private static void nr_(String str, long j, SparseArray<HealthLifeBean> sparseArray) {
        SparseArray<HealthLifeBean> kE_ = awq.e().kE_(str, DateFormatUtil.b(jdl.v(j)), true);
        if (kE_ == null || kE_.size() == 0) {
            LogUtil.h("HealthLife_TaskHelper", "getBeforeEffectiveTaskRecord lastRecord is empty");
            return;
        }
        HealthLifeBean healthLifeBean = kE_.get(1);
        if (healthLifeBean == null || TextUtils.isEmpty(healthLifeBean.getTarget())) {
            LogUtil.h("HealthLife_TaskHelper", "getBeforeEffectiveTaskRecord effectiveRecord is Invalid");
            return;
        }
        if (!Arrays.asList(healthLifeBean.getTarget().split(",")).contains(String.valueOf(7))) {
            nB_(sparseArray);
            return;
        }
        HealthLifeBean healthLifeBean2 = sparseArray.get(7);
        HealthLifeBean healthLifeBean3 = kE_.get(7);
        if (healthLifeBean2 == null || healthLifeBean3 == null) {
            LogUtil.h("HealthLife_TaskHelper", "getBeforeEffectiveTaskRecord currentSleepBean or lastSleepBean is null");
            return;
        }
        String target = healthLifeBean2.getTarget();
        String target2 = healthLifeBean3.getTarget();
        LogUtil.a("HealthLife_TaskHelper", "getBeforeEffectiveTaskRecord currentSleepTarget=", target, ",lastSleepTarget=", target2);
        if (Objects.equals(target, target2)) {
            nB_(sparseArray);
        } else {
            nB_(kE_);
        }
    }

    private static void nB_(SparseArray<HealthLifeBean> sparseArray) {
        HealthLifeBean healthLifeBean;
        HealthLifeBean healthLifeBean2 = sparseArray.get(1);
        if (healthLifeBean2 == null || TextUtils.isEmpty(healthLifeBean2.getTarget())) {
            LogUtil.h("HealthLife_TaskHelper", "saveEffectiveTaskRecord effectiveRecord is Invalid");
            return;
        }
        if (Arrays.asList(healthLifeBean2.getTarget().split(",")).contains(String.valueOf(7)) && (healthLifeBean = sparseArray.get(7)) != null) {
            healthLifeBean.setAddStatus(1);
        }
        HashMap hashMap = new HashMap();
        int size = sparseArray.size();
        for (int i = 0; i < size; i++) {
            int keyAt = sparseArray.keyAt(i);
            hashMap.put(Integer.valueOf(keyAt), sparseArray.get(keyAt));
        }
        bao.e("health_model_task_subscription_detail", HiJsonUtil.d(hashMap, new TypeToken<HashMap<Integer, HealthLifeBean>>() { // from class: bcm.1
        }.getType()));
    }

    public static int e(int i) {
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis > jdl.e(currentTimeMillis, 1, 0) || azi.t() == i) {
            LogUtil.a("HealthLife_TaskHelper", "getSleepTaskRefreshDay currentTime > 1");
            return i;
        }
        HealthLifeBean d = d(7);
        if (d == null || d.getAddStatus() != 1) {
            LogUtil.h("HealthLife_TaskHelper", "getSleepTaskRefreshDay sleep task is not subscription");
            return i;
        }
        String target = d.getTarget();
        if (b(i, 23, 0) < d(i, target)) {
            long d2 = azi.d(currentTimeMillis < jdl.e(currentTimeMillis, 1, 0) ? currentTimeMillis - 86400000 : currentTimeMillis, target, 60);
            if (currentTimeMillis < d2) {
                i = DateFormatUtil.b(jdl.v(currentTimeMillis));
            }
            LogUtil.a("HealthLife_TaskHelper", "getSleepTaskRefreshDay resultDay ", Integer.valueOf(i), " endPunchInTime ", Long.valueOf(d2));
        }
        return i;
    }

    public static int b(int i, int i2, int i3) {
        long c = DateFormatUtil.c(i);
        return ((int) (jdl.e(c, i2, i3) - c)) / 60000;
    }

    public static HealthLifeBean d(int i) {
        String e = bao.e("health_model_task_subscription_detail");
        if (TextUtils.isEmpty(e)) {
            LogUtil.h("HealthLife_TaskHelper", "getSleepTaskBean effectiveRecordJson is empty");
            return null;
        }
        HashMap hashMap = (HashMap) HiJsonUtil.b(e, new TypeToken<HashMap<Integer, HealthLifeBean>>() { // from class: bcm.4
        }.getType());
        if (hashMap == null || hashMap.size() == 0) {
            LogUtil.h("HealthLife_TaskHelper", "getSleepTaskBean effectiveRecord is empty");
            return null;
        }
        return (HealthLifeBean) hashMap.get(Integer.valueOf(i));
    }

    public static boolean e() {
        HealthLifeBean d = d(7);
        if (d != null) {
            return d.getAddStatus() == 1;
        }
        LogUtil.h("HealthLife_TaskHelper", "getSleepTaskRefreshDay sleepBean is null");
        return false;
    }

    public static SparseArray<SparseArray<HealthLifeBean>> nu_(int i, int i2, String str) {
        SparseArray<SparseArray<HealthLifeBean>> sparseArray = new SparseArray<>();
        List<HealthLifeBean> d = auz.d(i, i2, str);
        if (koq.c(d)) {
            for (HealthLifeBean healthLifeBean : d) {
                if (healthLifeBean != null) {
                    int recordDay = healthLifeBean.getRecordDay();
                    SparseArray<HealthLifeBean> sparseArray2 = sparseArray.get(recordDay);
                    if (sparseArray2 == null) {
                        sparseArray2 = new SparseArray<>();
                    }
                    sparseArray2.put(healthLifeBean.getId(), healthLifeBean);
                    sparseArray.put(recordDay, sparseArray2);
                }
            }
        }
        LogUtil.a("HealthLife_TaskHelper", "getListSparseArrayFromDb taskList: ", Integer.valueOf(sparseArray.size()));
        return sparseArray;
    }

    public static SparseArray<HealthLifeBean> nA_(String str, auo auoVar, SparseArray<TaskConfigBean> sparseArray) {
        LogUtil.a("HealthLife_TaskHelper", "parseConfigJsonObject enter");
        SparseArray<HealthLifeBean> sparseArray2 = new SparseArray<>();
        if (auoVar != null) {
            LogUtil.c("HealthLife_TaskHelper", "parseConfigJsonObject result = ", auoVar.toString());
            int a2 = auoVar.a();
            if (a2 != 0) {
                LogUtil.h("HealthLife_TaskHelper", "parseConfigJsonObject, resultCode = ", Integer.valueOf(a2));
                return sparseArray2;
            }
            List<HealthLifeBean> e = e(auoVar.e());
            aus.c(e, str);
            for (HealthLifeBean healthLifeBean : e) {
                if (healthLifeBean != null) {
                    int id = healthLifeBean.getId();
                    if (sparseArray2.get(id) == null || healthLifeBean.getRecordDay() > sparseArray2.get(id).getRecordDay()) {
                        sparseArray2.put(id, healthLifeBean);
                    }
                }
            }
            return nw_(sparseArray2, sparseArray);
        }
        LogUtil.h("HealthLife_TaskHelper", "parseConfigJsonObject result is null");
        return sparseArray2;
    }

    private static SparseArray<HealthLifeBean> nw_(SparseArray<HealthLifeBean> sparseArray, SparseArray<TaskConfigBean> sparseArray2) {
        if (sparseArray == null) {
            return new SparseArray<>();
        }
        HealthLifeBean healthLifeBean = sparseArray.get(1);
        if (healthLifeBean == null) {
            LogUtil.h("HealthLife_TaskHelper", "parseConfigJsonObject cloverBean is null");
            return new SparseArray<>();
        }
        ArrayList arrayList = new ArrayList(16);
        String target = healthLifeBean.getTarget();
        if (TextUtils.isEmpty(target)) {
            LogUtil.h("HealthLife_TaskHelper", "parseConfigJsonObject idList is null");
            return new SparseArray<>();
        }
        arrayList.addAll(Arrays.asList(target.split(",")));
        if (TextUtils.isEmpty(target)) {
            LogUtil.h("HealthLife_TaskHelper", "parseConfigJsonObject idList is null");
            return new SparseArray<>();
        }
        List<Integer> nx_ = nx_(sparseArray2);
        for (int i = 0; i < sparseArray.size(); i++) {
            int keyAt = sparseArray.keyAt(i);
            HealthLifeBean healthLifeBean2 = sparseArray.get(keyAt);
            if (arrayList.contains(String.valueOf(keyAt))) {
                healthLifeBean2.setAddStatus(1);
            } else {
                healthLifeBean2.setAddStatus(0);
            }
            nx_.remove(Integer.valueOf(keyAt));
            sparseArray.put(keyAt, healthLifeBean2);
        }
        if (sparseArray2 == null) {
            LogUtil.h("HealthLife_TaskHelper", "taskConfigSparseArray is null");
            return sparseArray;
        }
        nv_(nx_, sparseArray2, sparseArray);
        LogUtil.a("HealthLife_TaskHelper", "getSubscription from configs allIdList:", nx_.toString());
        return sparseArray;
    }

    private static void nv_(List<Integer> list, SparseArray<TaskConfigBean> sparseArray, SparseArray<HealthLifeBean> sparseArray2) {
        long currentTimeMillis = System.currentTimeMillis();
        int b = DateFormatUtil.b(currentTimeMillis);
        String q = jdl.q(currentTimeMillis);
        for (Integer num : list) {
            if (azi.c(num.intValue())) {
                HealthLifeBean healthLifeBean = new HealthLifeBean();
                healthLifeBean.setTimeZone(q);
                healthLifeBean.setId(num.intValue());
                healthLifeBean.setRecordDay(b);
                healthLifeBean.setTimestamp(currentTimeMillis);
                healthLifeBean.setAddStatus(0);
                TaskConfigBean taskConfigBean = sparseArray.get(num.intValue());
                if (taskConfigBean == null || "false".equals(taskConfigBean.getSupportTask())) {
                    LogUtil.h("HealthLife_TaskHelper", "getSubscriptionArray config is null, id=", num);
                } else {
                    if (num.intValue() != 11 && num.intValue() != 12) {
                        healthLifeBean.setTarget(taskConfigBean.getDefaultGoal());
                    }
                    sparseArray2.put(num.intValue(), healthLifeBean);
                }
            }
        }
    }

    public static SparseArray<HealthLifeBean> nq_(List<HealthLifeBean> list) {
        if (koq.b(list)) {
            LogUtil.h("HealthLife_TaskHelper", "convertListToSparseArray healthTaskRecordDbBeans is empty.");
            return new SparseArray<>();
        }
        SparseArray<HealthLifeBean> sparseArray = new SparseArray<>(list.size());
        for (HealthLifeBean healthLifeBean : list) {
            sparseArray.put(healthLifeBean.getId(), healthLifeBean);
        }
        return sparseArray;
    }

    public static String nt_(SparseArray<TaskConfigBean> sparseArray) {
        if (sparseArray == null || sparseArray.size() < 3) {
            LogUtil.h("HealthLife_TaskHelper", "getDefaultTask configs is null or invalid size");
            return "2,3,5,6,7";
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int i2 = 0;
        while (i < sparseArray.size()) {
            int keyAt = sparseArray.keyAt(i);
            TaskConfigBean taskConfigBean = sparseArray.get(keyAt);
            if (taskConfigBean != null && String.valueOf(true).equals(taskConfigBean.getDefaultTask()) && azi.c(keyAt)) {
                i2++;
                sb.append(keyAt);
                sb.append(i == sparseArray.size() - 1 ? "" : ",");
            }
            i++;
        }
        String sb2 = i2 >= 3 ? sb.toString() : "2,3,5,6,7";
        LogUtil.c("HealthLife_TaskHelper", "getDefaultTask configs is ", sb2);
        return sb2;
    }

    private static List<Integer> ns_(int i, SparseArray<TaskConfigBean> sparseArray) {
        if (sparseArray == null || sparseArray.size() == 0) {
            LogUtil.h("HealthLife_TaskHelper", "getDefaultAlarmTime configs is null or invalid size");
            return c(i);
        }
        TaskConfigBean taskConfigBean = sparseArray.get(i);
        if (taskConfigBean == null) {
            LogUtil.h("HealthLife_TaskHelper", "getDefaultAlarmTime healthTaskConfig is null");
            return c(i);
        }
        List<Integer> d = azi.d(taskConfigBean.getDefaultAlarmTime());
        if (d.size() > 0) {
            return d;
        }
        LogUtil.h("HealthLife_TaskHelper", "getDefaultAlarmTime failed");
        return c(i);
    }

    private static List<Integer> c(int i) {
        Integer valueOf = Integer.valueOf(HwExerciseConstants.NINE_MINUTES_PACE);
        switch (i) {
            case 1:
                return Collections.singletonList(valueOf);
            case 2:
                return Collections.singletonList(1170);
            case 3:
                return Collections.singletonList(1050);
            case 4:
            case 6:
            case 12:
            default:
                return Collections.singletonList(0);
            case 5:
                return Collections.singletonList(900);
            case 7:
                return Collections.singletonList(30);
            case 8:
                return Collections.singletonList(600);
            case 9:
                return Collections.singletonList(1320);
            case 10:
                return Arrays.asList(480, 1080);
            case 11:
                return Collections.singletonList(480);
            case 13:
                return Arrays.asList(Integer.valueOf(TypedValues.PositionType.TYPE_POSITION_TYPE), 720, Integer.valueOf(ExceptionCode.SERVER_EXCEPTION));
            case 14:
                return Collections.singletonList(valueOf);
        }
    }

    public static List<Calendar> e(int i, String str, ayh ayhVar) {
        if (!bby.c().contains(Integer.valueOf(i))) {
            LogUtil.h("HealthLife_TaskHelper", "getConfigCalendar invalid id ", Integer.valueOf(i));
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        Calendar s = azi.s();
        SparseArray<TaskConfigBean> kD_ = awq.e().kD_();
        if (i == 1) {
            List<Integer> ns_ = ns_(i, kD_);
            if (koq.c(ns_)) {
                s.add(12, ns_.get(0).intValue());
            }
            s.add(5, 1);
            arrayList.add(s);
            return arrayList;
        }
        if (i == 7 || i == 6) {
            return c(i, str);
        }
        if (ayhVar == null || koq.b(ayhVar.c())) {
            return a(i);
        }
        return bby.e(ayhVar.c());
    }

    public static List<Calendar> c(int i, String str) {
        if (i != 7 && i != 6) {
            return new ArrayList();
        }
        SparseArray<TaskConfigBean> kD_ = awq.e().kD_();
        if (TextUtils.isEmpty(str)) {
            if (kD_ == null || kD_.size() <= 0) {
                LogUtil.h("HealthLife_TaskHelper", "getSlumberCalendars configArray ", kD_);
                return new ArrayList();
            }
            TaskConfigBean taskConfigBean = kD_.get(i);
            if (taskConfigBean == null) {
                LogUtil.h("HealthLife_TaskHelper", "getSlumberCalendars healthTaskConfig is null");
                return new ArrayList();
            }
            str = taskConfigBean.getDefaultGoal();
        }
        Calendar f = azi.f(str);
        List<Integer> ns_ = ns_(i, kD_);
        if (koq.c(ns_)) {
            f.add(12, -ns_.get(0).intValue());
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(f);
        return arrayList;
    }

    public static List<Calendar> a(int i) {
        LogUtil.c("HealthLife_TaskHelper", "getConfigCalendar id", Integer.valueOf(i));
        if (!bby.c().contains(Integer.valueOf(i))) {
            LogUtil.h("HealthLife_TaskHelper", "getConfigCalendar invalid id ", Integer.valueOf(i));
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        SparseArray<TaskConfigBean> kD_ = awq.e().kD_();
        if (i == 10) {
            List<Integer> ns_ = ns_(i, kD_);
            if (koq.c(ns_)) {
                Iterator<Integer> it = ns_.iterator();
                while (it.hasNext()) {
                    int intValue = it.next().intValue();
                    Calendar s = azi.s();
                    s.add(12, intValue);
                    arrayList.add(s);
                }
            }
        } else if (i == 7 || i == 6) {
            if (kD_ == null || kD_.size() == 0 || kD_.get(i) == null) {
                LogUtil.h("HealthLife_TaskHelper", "getConfigCalendar configs invalid");
                return arrayList;
            }
            Calendar f = azi.f(kD_.get(i).getDefaultGoal());
            List<Integer> ns_2 = ns_(i, kD_);
            if (koq.c(ns_2)) {
                f.add(12, -ns_2.get(0).intValue());
            }
            arrayList.add(f);
        } else {
            if (i == 13) {
                return ny_(kD_, i, arrayList);
            }
            Calendar s2 = azi.s();
            List<Integer> ns_3 = ns_(i, kD_);
            if (koq.c(ns_3)) {
                s2.add(12, ns_3.get(0).intValue());
            }
            arrayList.add(s2);
        }
        return arrayList;
    }

    private static List<Calendar> ny_(SparseArray<TaskConfigBean> sparseArray, int i, List<Calendar> list) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (sparseArray == null || sparseArray.size() == 0) {
            LogUtil.h("HealthLife_TaskHelper", "getConfigCalendar diet configs is null or invalid size");
            a(true, list, c(i), arrayList2);
            return list;
        }
        TaskConfigBean taskConfigBean = sparseArray.get(i);
        if (taskConfigBean == null) {
            LogUtil.h("HealthLife_TaskHelper", "getConfigCalendar diet healthTaskConfig is null");
            a(true, list, c(i), arrayList2);
            return list;
        }
        String defaultAlarmTime = taskConfigBean.getDefaultAlarmTime();
        List<Calendar> e = azi.e(defaultAlarmTime);
        String[] split = defaultAlarmTime.split(",");
        if (split.length < 3) {
            a(true, list, arrayList, e);
            LogUtil.h("HealthLife_TaskHelper", "getConfigCalendar diet dietTime < 3");
            return list;
        }
        for (String str : split) {
            int e2 = nsn.e(str);
            Calendar s = azi.s();
            s.add(11, e2 / 60);
            s.add(12, e2 % 60);
            list.add(s);
        }
        return list;
    }

    private static void a(boolean z, List<Calendar> list, List<Integer> list2, List<Calendar> list3) {
        if (z) {
            if (koq.c(list2)) {
                Iterator<Integer> it = list2.iterator();
                while (it.hasNext()) {
                    int intValue = it.next().intValue();
                    Calendar s = azi.s();
                    s.add(11, intValue / 60);
                    s.add(12, intValue % 60);
                    list.add(s);
                }
                return;
            }
            return;
        }
        if (koq.c(list3)) {
            list.addAll(list3);
        }
    }

    public static int d(String str, int i, String str2) {
        int i2 = -1;
        for (Integer num : nx_(awq.e().kD_())) {
            if (!str2.contains(String.valueOf(num))) {
                i2 = auz.e(str, i, num.intValue());
            }
        }
        return i2;
    }

    public static int a() {
        String[] c = c();
        if (c == null || c.length <= 1) {
            return 0;
        }
        return CommonUtil.h(c[0]);
    }

    public static String[] c() {
        HiUserPreference c = azi.c("health_model_device_celebrating");
        if (c == null) {
            return null;
        }
        String value = c.getValue();
        LogUtil.a("HealthLife_TaskHelper", "getCelebratingSaved is ", value);
        if (TextUtils.isEmpty(value)) {
            return null;
        }
        return value.split("_");
    }

    public static void d(final String str) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: bcu
                @Override // java.lang.Runnable
                public final void run() {
                    bcm.d(str);
                }
            });
            return;
        }
        LogUtil.a("HealthLife_TaskHelper", "saveCelebratingFlag is ", str);
        if (str == null) {
            str = "";
        }
        HiHealthManager.d(BaseApplication.getContext()).setUserPreference(new HiUserPreference("health_model_device_celebrating", str));
    }

    public static List<ChallengeConfigBean> c(List<ChallengeConfigBean> list) {
        ArrayList arrayList = new ArrayList(10);
        if (koq.b(list)) {
            return arrayList;
        }
        for (ChallengeConfigBean challengeConfigBean : list) {
            if (challengeConfigBean != null) {
                String versionRange = challengeConfigBean.getVersionRange();
                String versionRangeBeta = challengeConfigBean.getVersionRangeBeta();
                if (TextUtils.isEmpty(versionRange) || TextUtils.isEmpty(versionRangeBeta)) {
                    arrayList.add(challengeConfigBean);
                } else {
                    int d = CommonUtil.d(BaseApplication.getContext());
                    boolean as = CommonUtil.as();
                    boolean e = azi.e(challengeConfigBean.getVersionRange(), d);
                    boolean e2 = azi.e(challengeConfigBean.getVersionRangeBeta(), d);
                    if (as && e2) {
                        arrayList.add(challengeConfigBean);
                    } else if (e) {
                        arrayList.add(challengeConfigBean);
                    } else {
                        LogUtil.a("HealthLife_TaskHelper", "handleChallengeList other");
                    }
                }
            }
        }
        return arrayList;
    }
}
