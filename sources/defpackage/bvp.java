package defpackage;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.SparseArray;
import com.google.gson.JsonSyntaxException;
import com.google.json.JsonSanitizer;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.intplan.IntAction;
import com.huawei.basefitnessadvice.model.intplan.IntDayPlan;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.featureuserprofile.todo.activity.TodoActivtiesResCallBack;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.router.AppRouterUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.healthmodel.bean.HealthLifeTaskBean;
import com.huawei.health.nps.api.NpsExternalApi;
import com.huawei.health.plan.api.PlanApi;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.health.todo.api.TodoDataApi;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.OperationUtilsApi;
import defpackage.bvp;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class bvp {
    private static final List<Integer> c = Arrays.asList(12);
    private static volatile bvp d;
    private bva j;
    private volatile AtomicBoolean e = new AtomicBoolean(true);

    /* renamed from: a, reason: collision with root package name */
    private final List<gka> f529a = new ArrayList(10);
    private final List<IBaseResponseCallback> b = new ArrayList(0);

    private bvp() {
    }

    public static bvp c() {
        if (d == null) {
            synchronized (bvp.class) {
                if (d == null) {
                    d = new bvp();
                }
            }
        }
        return d;
    }

    public List<gka> d() {
        ArrayList arrayList;
        synchronized (this.f529a) {
            LogUtil.a("TodoDataManager", "mTodoCardRecyModels size = ", Integer.valueOf(this.f529a.size()));
            arrayList = new ArrayList(this.f529a);
        }
        return arrayList;
    }

    public void vO_(Activity activity, boolean z) {
        synchronized (this) {
            if (this.j == null && activity != null && bvw.e(BaseApplication.getContext())) {
                bva bvaVar = new bva(activity);
                this.j = bvaVar;
                bvaVar.a();
            }
            bva bvaVar2 = this.j;
            if (bvaVar2 != null) {
                bvaVar2.c(z);
            }
        }
    }

    public void e() {
        synchronized (this) {
            if (this.j != null) {
                this.j = null;
            }
        }
    }

    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public final void a(final IBaseResponseCallback iBaseResponseCallback) {
        if (HandlerExecutor.c()) {
            LogUtil.a("TodoDataManager", "getAllTodoData isMainTread");
            ThreadPoolManager.d().execute(new Runnable() { // from class: bvv
                @Override // java.lang.Runnable
                public final void run() {
                    bvp.this.a(iBaseResponseCallback);
                }
            });
            return;
        }
        if (iBaseResponseCallback != null) {
            synchronized (this.b) {
                if (!this.b.contains(iBaseResponseCallback)) {
                    this.b.add(iBaseResponseCallback);
                }
            }
        }
        if (!this.e.getAndSet(false)) {
            LogUtil.h("TodoDataManager", "getPlanData mIsRequestReached:", this.e);
        } else {
            CommonUtil.u("TodoDataManager-getPlanData enter");
            i();
        }
    }

    private void i() {
        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();
        CountDownLatch countDownLatch = new CountDownLatch(4);
        a(copyOnWriteArrayList, countDownLatch);
        b(copyOnWriteArrayList, countDownLatch);
        d(copyOnWriteArrayList, countDownLatch);
        e(copyOnWriteArrayList, countDownLatch);
        try {
            countDownLatch.await(PreConnectManager.CONNECT_INTERNAL, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            LogUtil.b("TodoDataManager", "getPlanData InterruptedException");
        }
        gka j = j();
        if (j != null) {
            copyOnWriteArrayList.add(j);
        }
        ArrayList arrayList = new ArrayList(copyOnWriteArrayList);
        Collections.sort(arrayList, new bvy(false));
        LogUtil.a("TodoDataManager", "copyList size = ", Integer.valueOf(arrayList.size()));
        List<gka> d2 = d(new ArrayList<>(arrayList));
        SharedPreferenceManager.b(String.valueOf(10026), "today_todo_count", koq.c(d2) ? d2.size() : 0);
        synchronized (this.f529a) {
            this.f529a.clear();
            this.f529a.addAll(arrayList);
        }
        synchronized (this.b) {
            Iterator<IBaseResponseCallback> it = this.b.iterator();
            while (it.hasNext()) {
                it.next().d(0, new ArrayList(arrayList));
            }
            this.b.clear();
        }
        this.e.getAndSet(true);
    }

    private void b(List<gka> list, CountDownLatch countDownLatch) {
        CommonUtil.u("TodoDataManagergetFitnessPlanData enter");
        PlanApi planApi = (PlanApi) Services.a("CoursePlanService", PlanApi.class);
        if (planApi == null) {
            LogUtil.h("TodoDataManager", "getCurrentFitnessPlan, getCurrentPlan : planApi is null.");
            countDownLatch.countDown();
        } else {
            planApi.b(new AnonymousClass2(countDownLatch, list));
        }
    }

    /* renamed from: bvp$2, reason: invalid class name */
    class AnonymousClass2 extends UiCallback<IntPlan> {
        final /* synthetic */ List b;
        final /* synthetic */ CountDownLatch d;

        AnonymousClass2(CountDownLatch countDownLatch, List list) {
            this.d = countDownLatch;
            this.b = list;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.a("TodoDataManager", "getCurrentIntPlan, onFailure, errorCode = ", Integer.valueOf(i), ", errorInfo = ", str);
            this.d.countDown();
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(final IntPlan intPlan) {
            if (intPlan == null) {
                LogUtil.h("TodoDataManager", "getCurrentFitnessPlan, data is null.");
            }
            CommonUtil.u("TodoDataManager-getCurrentFitnessPlan end");
            ThreadPoolManager d = ThreadPoolManager.d();
            final List list = this.b;
            final CountDownLatch countDownLatch = this.d;
            d.execute(new Runnable() { // from class: bvr
                @Override // java.lang.Runnable
                public final void run() {
                    bvp.AnonymousClass2.this.c(intPlan, list, countDownLatch);
                }
            });
        }

        /* synthetic */ void c(IntPlan intPlan, List list, CountDownLatch countDownLatch) {
            gka d = bvp.this.d(intPlan);
            if (d != null) {
                list.add(d);
            }
            countDownLatch.countDown();
        }
    }

    private void d(final List<gka> list, final CountDownLatch countDownLatch) {
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010);
        OperationUtilsApi operationUtilsApi = (OperationUtilsApi) Services.c("PluginOperation", OperationUtilsApi.class);
        if (Utils.o() && !operationUtilsApi.isOperation(accountInfo)) {
            LogUtil.a("TodoDataManager", "Utils.isOversea() no activity data");
            countDownLatch.countDown();
        } else {
            bvn.b().a(0, false, new TodoActivtiesResCallBack() { // from class: bvs
                @Override // com.huawei.featureuserprofile.todo.activity.TodoActivtiesResCallBack
                public final void onFinished(Object obj) {
                    bvp.this.a(list, countDownLatch, (List) obj);
                }
            });
        }
    }

    /* synthetic */ void a(List list, CountDownLatch countDownLatch, List list2) {
        list.addAll(a((List<ceb>) list2));
        countDownLatch.countDown();
    }

    private void a(List<gka> list, CountDownLatch countDownLatch) {
        if (!dsl.n()) {
            b(c, list, countDownLatch);
        } else {
            LogUtil.a("TodoDataManager", "getHealthLifeTask start todoCardRecyModels.size = ", Integer.valueOf(list.size()));
            d(list, countDownLatch, f());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public gka d(IntPlan intPlan) {
        boolean z;
        LogUtil.a("TodoDataManager", "handlerFitnessPlanData");
        if (intPlan == null) {
            LogUtil.h("TodoDataManager", "handlerFitnessPlanData, intPlan is null.");
            return null;
        }
        if (!Utils.j() && intPlan.getPlanType() == IntPlan.PlanType.FIT_PLAN) {
            LogUtil.a("TodoDataManager", "not in chinese or isOversea, filter fitness plan");
            return null;
        }
        IntDayPlan dayInfo = intPlan.getDayInfo(ase.g(intPlan), jdl.n(System.currentTimeMillis()));
        if (dayInfo == null) {
            return null;
        }
        List<IntAction> c2 = c(dayInfo);
        if (koq.b(c2)) {
            return null;
        }
        if (intPlan.getPlanType() == IntPlan.PlanType.FIT_PLAN || intPlan.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN) {
            Iterator<IntAction> it = c2.iterator();
            while (it.hasNext()) {
                if (it.next().getPunchFlag() == 0) {
                    z = false;
                    break;
                }
            }
        }
        z = true;
        return d(intPlan, (intPlan.getPlanType() == IntPlan.PlanType.AI_RUN_PLAN && dayInfo.getPunchFlag() == 0) ? false : z);
    }

    private List<IntAction> c(IntDayPlan intDayPlan) {
        ArrayList arrayList = new ArrayList(10);
        if (intDayPlan == null) {
            return arrayList;
        }
        for (int i = 0; i < intDayPlan.getInPlanActionCnt(); i++) {
            IntAction inPlanAction = intDayPlan.getInPlanAction(i);
            if (inPlanAction != null && inPlanAction.getActionId() != null && (inPlanAction.getActionType() == IntAction.ActionType.RUN || inPlanAction.getActionType() == IntAction.ActionType.WORKOUT)) {
                arrayList.add(inPlanAction);
            }
        }
        return arrayList;
    }

    private gka d(IntPlan intPlan, boolean z) {
        gka gkaVar = new gka();
        gkaVar.d(intPlan);
        gkaVar.c(intPlan.getMetaInfo().getDescription());
        gkaVar.a(intPlan.getMetaInfo().getName());
        gkaVar.c(intPlan.getMetaInfo().getPlanStatus());
        gkaVar.b(intPlan.getPlanTimeInfo().getCreateTime());
        gkaVar.b(HealthData.BLOODPRESURE);
        gkaVar.e(R.drawable._2131430526_res_0x7f0b0c7e);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("planType", intPlan.getPlanType().getType());
            gkaVar.e(jSONObject.toString());
        } catch (JSONException e) {
            LogUtil.b("TodoDataManager", ExceptionUtils.d(e));
        }
        if (z) {
            gkaVar.c(1);
        } else {
            gkaVar.c(0);
        }
        return gkaVar;
    }

    private List<gka> a(List<ceb> list) {
        ArrayList arrayList = new ArrayList(10);
        if (koq.b(list)) {
            LogUtil.a("TodoDataManager", "mActivityList is null");
            return arrayList;
        }
        for (ceb cebVar : list) {
            if (cebVar != null) {
                arrayList.add(b(cebVar));
            }
        }
        return arrayList;
    }

    private void d(List<gka> list, CountDownLatch countDownLatch, List<HealthLifeTaskBean> list2) {
        if (koq.b(list2)) {
            LogUtil.h("TodoDataManager", "handleHealthLifeTaskData. todoTaskList is empty");
            b(c, list, countDownLatch);
            return;
        }
        ArrayList arrayList = new ArrayList(10);
        LogUtil.a("TodoDataManager", "handleHealthLifeTaskData. todoTaskList.size = ", Integer.valueOf(list2.size()));
        ArrayList arrayList2 = new ArrayList(c);
        for (HealthLifeTaskBean healthLifeTaskBean : list2) {
            if (healthLifeTaskBean != null) {
                arrayList.add(d(healthLifeTaskBean));
                if (arrayList2.contains(Integer.valueOf(healthLifeTaskBean.getId()))) {
                    arrayList2.remove(Integer.valueOf(healthLifeTaskBean.getId()));
                }
            }
        }
        list.addAll(arrayList);
        b(arrayList2, list, countDownLatch);
    }

    private gka j() {
        NpsExternalApi npsExternalApi = (NpsExternalApi) Services.c("Main", NpsExternalApi.class);
        if (npsExternalApi.isShowNps()) {
            LogUtil.a("TodoDataManager", "showHealthNps");
            gka gkaVar = new gka();
            gkaVar.a(BaseApplication.getContext().getResources().getString(R.string._2130847320_res_0x7f022658));
            gkaVar.b(2048);
            return gkaVar;
        }
        if (npsExternalApi.isShowDeviceNps()) {
            LogUtil.a("TodoDataManager", "ShowDeviceNps");
            gka gkaVar2 = new gka();
            if (npsExternalApi.isWeightDeviceNps()) {
                gkaVar2.a(BaseApplication.getContext().getResources().getString(R.string._2130838115_res_0x7f020263));
            } else {
                gkaVar2.a(BaseApplication.getContext().getResources().getString(R.string._2130837995_res_0x7f0201eb));
            }
            gkaVar2.b(2304);
            return gkaVar2;
        }
        LogUtil.a("TodoDataManager", "no nps data");
        return null;
    }

    private gka b(ceb cebVar) {
        gka gkaVar = new gka();
        gkaVar.c(cebVar.b());
        if (TextUtils.isEmpty(cebVar.e())) {
            gkaVar.a(cebVar.b());
        } else {
            gkaVar.a(cebVar.e());
        }
        gkaVar.b(HealthData.WEIGHT);
        gkaVar.c(cebVar.k());
        gkaVar.e(R.drawable._2131430520_res_0x7f0b0c78);
        try {
            gkaVar.b(DateFormatUtil.e(cebVar.n(), DateFormatUtil.DateFormatType.DEFAULT_DATE_FORMAT, jdl.b()).getTime());
        } catch (ParseException e) {
            LogUtil.b("TodoDataManager", "parse error:" + e.getMessage());
        }
        gkaVar.d(cebVar);
        return gkaVar;
    }

    private gka d(HealthLifeTaskBean healthLifeTaskBean) {
        gka gkaVar = new gka();
        gkaVar.b(256);
        gkaVar.d(healthLifeTaskBean);
        gkaVar.a(healthLifeTaskBean.getName());
        gkaVar.c(healthLifeTaskBean.getComplete() > 0 ? 1 : 0);
        return gkaVar;
    }

    public List<gka> d(List<gka> list) {
        if (koq.b(list)) {
            LogUtil.h("TodoDataManager", "todoModels is empty.");
            return list;
        }
        Iterator<gka> it = list.iterator();
        while (it.hasNext()) {
            gka next = it.next();
            if (next == null || next.k() == 256 || next.k() == 512 || next.i() == 1) {
                it.remove();
            }
        }
        return list;
    }

    public List<gka> a() {
        ArrayList arrayList = new ArrayList(10);
        arrayList.addAll(h());
        arrayList.addAll(b());
        gka j = j();
        if (j != null) {
            arrayList.add(j);
        }
        Collections.sort(arrayList, new bvy(false));
        return arrayList;
    }

    private List<gka> b() {
        final ArrayList arrayList = new ArrayList(10);
        String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1010);
        OperationUtilsApi operationUtilsApi = (OperationUtilsApi) Services.c("PluginOperation", OperationUtilsApi.class);
        if (!Utils.o() || operationUtilsApi.isOperation(accountInfo)) {
            bvn.b().c(0, new TodoActivtiesResCallBack() { // from class: bvm
                @Override // com.huawei.featureuserprofile.todo.activity.TodoActivtiesResCallBack
                public final void onFinished(Object obj) {
                    bvp.this.a(arrayList, (List) obj);
                }
            });
        }
        return arrayList;
    }

    /* synthetic */ void a(List list, List list2) {
        if (koq.b(list2)) {
            LogUtil.a("TodoDataManager", "mActivityList is null");
            return;
        }
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            ceb cebVar = (ceb) it.next();
            if (cebVar != null) {
                list.add(b(cebVar));
            }
        }
    }

    private List<HealthLifeTaskBean> f() {
        List<HealthLifeTaskBean> e = dsl.e();
        if (koq.b(e)) {
            LogUtil.a("TodoDataManager", "getHealthLifeDayRecordCache listCache ", e);
            return Collections.emptyList();
        }
        HealthLifeTaskBean healthLifeTaskBean = e.get(0);
        int recordDay = healthLifeTaskBean != null ? healthLifeTaskBean.getRecordDay() : 0;
        LogUtil.a("TodoDataManager", "getHealthLifeDayRecordCache recordDay ", Integer.valueOf(recordDay));
        return recordDay == DateFormatUtil.b(System.currentTimeMillis()) ? e : Collections.emptyList();
    }

    private List<gka> h() {
        List<HealthLifeTaskBean> f = f();
        if (koq.b(f)) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList(10);
        for (HealthLifeTaskBean healthLifeTaskBean : f) {
            if (healthLifeTaskBean != null) {
                gka gkaVar = new gka();
                gkaVar.b(256);
                gkaVar.d(healthLifeTaskBean);
                gkaVar.a(healthLifeTaskBean.getName());
                gkaVar.c(healthLifeTaskBean.getComplete() > 0 ? 1 : 0);
                arrayList.add(gkaVar);
            }
        }
        return arrayList;
    }

    private void b(final List<Integer> list, final List<gka> list2, final CountDownLatch countDownLatch) {
        if (koq.b(list)) {
            LogUtil.h("TodoDataManager", "queryMeasureTask. planIdList is empty,return");
            countDownLatch.countDown();
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: bvu
                @Override // java.lang.Runnable
                public final void run() {
                    bvp.this.e(list, list2, countDownLatch);
                }
            });
        }
    }

    /* synthetic */ void e(List list, List list2, CountDownLatch countDownLatch) {
        LogUtil.a("TodoDataManager", "queryMeasureTask. start planIdList.size = ", Integer.valueOf(list.size()));
        list2.addAll(e(dsl.c((List<Integer>) list)));
        countDownLatch.countDown();
    }

    private List<gka> e(List<HealthLifeTaskBean> list) {
        LogUtil.a("TodoDataManager", "handleMeasureTaskData. todoTaskList.size = ", Integer.valueOf(list.size()));
        ArrayList arrayList = new ArrayList(10);
        if (koq.b(list)) {
            return arrayList;
        }
        for (HealthLifeTaskBean healthLifeTaskBean : list) {
            if (healthLifeTaskBean != null && healthLifeTaskBean.getComplete() == 0) {
                arrayList.add(c(healthLifeTaskBean));
            }
        }
        return arrayList;
    }

    private gka c(HealthLifeTaskBean healthLifeTaskBean) {
        gka gkaVar = new gka();
        gkaVar.b(512);
        gkaVar.d(healthLifeTaskBean);
        gkaVar.c(healthLifeTaskBean.getName());
        gkaVar.a(healthLifeTaskBean.getName());
        gkaVar.c(healthLifeTaskBean.getComplete() > 0 ? 1 : 0);
        return gkaVar;
    }

    private void e(final List<gka> list, final CountDownLatch countDownLatch) {
        if (!gpn.b()) {
            LogUtil.h("TodoDataManager", "is calBalance todo task Operated false ");
            countDownLatch.countDown();
        } else {
            ((TodoDataApi) Services.c("HWUserProfileMgr", TodoDataApi.class)).getTodoListFromCloud(String.valueOf(HealthData.TIMELINE), new IBaseResponseCallback() { // from class: bvt
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    bvp.this.e(list, countDownLatch, i, obj);
                }
            });
        }
    }

    /* synthetic */ void e(List list, CountDownLatch countDownLatch, int i, Object obj) {
        if (obj instanceof List) {
            c(list, countDownLatch, (List) obj);
        } else {
            countDownLatch.countDown();
        }
    }

    private void c(List<gka> list, CountDownLatch countDownLatch, List<gka> list2) {
        if (koq.b(list2)) {
            countDownLatch.countDown();
            return;
        }
        ArrayList arrayList = new ArrayList(list2.size());
        boolean z = true;
        for (gka gkaVar : list2) {
            if (!b(gkaVar)) {
                LogUtil.h("TodoDataManager", "not support todo task  = ", gkaVar.n());
            } else {
                arrayList.add(gkaVar);
                if (gkaVar.i() == 0) {
                    z = false;
                }
            }
        }
        LogUtil.a("TodoDataManager", "processCalBalanceTasks isAllTaskFinished = ", Boolean.valueOf(z));
        if (z) {
            list.addAll(arrayList);
            countDownLatch.countDown();
        } else {
            b(list, countDownLatch, arrayList);
        }
    }

    private boolean b(gka gkaVar) {
        Context context = BaseApplication.getContext();
        int m = CommonUtil.m(context, bvw.d(BleConstants.SPORT_TYPE, gkaVar.m()));
        if (!gjw.c.containsKey(Integer.valueOf(m))) {
            return false;
        }
        gkaVar.a(context.getString(R.string._2130846051_res_0x7f022163, context.getString(gjw.c.get(Integer.valueOf(m)).intValue())));
        return true;
    }

    private void b(final List<gka> list, final CountDownLatch countDownLatch, final List<gka> list2) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        long b = DateFormatUtil.b(System.currentTimeMillis());
        hiDataReadOption.setTimeInterval(String.valueOf(b), String.valueOf(b), HiDataReadOption.TimeFormatType.DATE_FORMAT_YYYY_MM_DD);
        hiDataReadOption.setType(new int[]{257, 258, 259});
        hiDataReadOption.setSortOrder(0);
        HiHealthNativeApi.a(BaseApplication.getContext()).fetchSequenceDataBySportType(hiDataReadOption, new HiDataReadResultListener() { // from class: bvp.4
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                if (!(obj instanceof SparseArray)) {
                    LogUtil.h("TodoDataManager", "checkCalBalanceTaskStatus no sports");
                    list.addAll(list2);
                    countDownLatch.countDown();
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (!koq.e(sparseArray.get(i), HiHealthData.class)) {
                    LogUtil.h("TodoDataManager", "checkCalBalanceTaskStatus onResult obj not instanceof List");
                    list.addAll(list2);
                    countDownLatch.countDown();
                    return;
                }
                List list3 = (List) sparseArray.get(i);
                if (koq.b(list3)) {
                    LogUtil.h("TodoDataManager", "checkCalBalanceTaskStatus sport list is null");
                    list.addAll(list2);
                    countDownLatch.countDown();
                } else {
                    LogUtil.a("TodoDataManager", "todoLists list size = ", Integer.valueOf(list3.size()));
                    bvp.this.c(list3, list2);
                    list.addAll(list2);
                    countDownLatch.countDown();
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
                LogUtil.a("TodoDataManager", "checkCalBalanceTaskStatus get sport list onResultIntent");
                list.addAll(list2);
                countDownLatch.countDown();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<HiHealthData> list, List<gka> list2) {
        for (gka gkaVar : list2) {
            if (koq.b(list)) {
                return;
            }
            if (gkaVar.i() != 1) {
                String c2 = gkaVar.c();
                list.removeAll(e(list, gkaVar));
                String c3 = gkaVar.c();
                if (!TextUtils.isEmpty(c2) && !c2.equals(c3)) {
                    ((TodoDataApi) Services.c("HWUserProfileMgr", TodoDataApi.class)).updateTodoInfo(gkaVar, new IBaseResponseCallback() { // from class: bvo
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public final void d(int i, Object obj) {
                            LogUtil.a("TodoDataManager", "updateTodoInfo resultCode = ", Integer.valueOf(i));
                        }
                    });
                }
            }
        }
    }

    private List<HiHealthData> e(List<HiHealthData> list, gka gkaVar) {
        ArrayList arrayList = new ArrayList(10);
        long h = gkaVar.h();
        int m = CommonUtil.m(BaseApplication.getContext(), gkaVar.g()) * 1000;
        int m2 = CommonUtil.m(BaseApplication.getContext(), bvw.d(BleConstants.SPORT_TYPE, gkaVar.m()));
        Iterator<HiHealthData> it = list.iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            HiHealthData next = it.next();
            HiTrackMetaData e = e(JsonSanitizer.sanitize(next.getMetaData()));
            if (e != null) {
                long startTime = next.getStartTime();
                int sportType = e.getSportType();
                int totalCalories = e.getTotalCalories();
                if (startTime >= h && m2 == sportType) {
                    i += totalCalories;
                    arrayList.add(next);
                    if (i >= m) {
                        gkaVar.c(1);
                        i = m;
                        break;
                    }
                }
            }
        }
        gkaVar.b(String.valueOf(i / 1000));
        e(gkaVar, (m - i) * 1000);
        return arrayList;
    }

    private HiTrackMetaData e(String str) {
        try {
            HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(str, HiTrackMetaData.class);
            int abnormalTrack = hiTrackMetaData.getAbnormalTrack();
            int duplicated = hiTrackMetaData.getDuplicated();
            int sportDataSource = hiTrackMetaData.getSportDataSource();
            if (abnormalTrack == 0 && duplicated == 0 && sportDataSource != 2) {
                return hiTrackMetaData;
            }
            LogUtil.h("TodoDataManager", "getHiTrackMetaData trackDataIsInvalid!");
            return null;
        } catch (JsonSyntaxException unused) {
            LogUtil.b("TodoDataManager", "trackMetaData is error");
            return null;
        }
    }

    private void e(gka gkaVar, int i) {
        String e = gkaVar.e();
        if (TextUtils.isEmpty(e)) {
            LogUtil.h("TodoDataManager", "getNewUrlForCalBalanceTask old linkUrl is empty");
            return;
        }
        StringBuilder sb = new StringBuilder("huaweischeme://healthapp/track?");
        Map<String, String> zv_ = AppRouterUtils.zv_(Uri.parse(e));
        if (!zv_.containsKey(WorkoutRecord.Extend.COURSE_TARGET_VALUE)) {
            LogUtil.h("TodoDataManager", "getNewUrlForCalBalanceTask no targetValue");
            return;
        }
        if (CommonUtil.m(BaseApplication.getContext(), zv_.get(WorkoutRecord.Extend.COURSE_TARGET_VALUE)) == i) {
            LogUtil.h("TodoDataManager", "getNewUrlForCalBalanceTask targetValue not change");
            return;
        }
        for (Map.Entry<String, String> entry : zv_.entrySet()) {
            if (WorkoutRecord.Extend.COURSE_TARGET_VALUE.equals(entry.getKey())) {
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(i);
                sb.append("&");
            } else {
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(entry.getValue());
                sb.append("&");
            }
        }
        LogUtil.a("TodoDataManager", "getNewUrlForCalBalanceTask newTargetValue = ", Integer.valueOf(i));
        LogUtil.c("TodoDataManager", "getNewUrlForCalBalanceTask newLink = ", sb.toString());
        gkaVar.d(sb.toString().substring(0, sb.length() - 1));
    }
}
