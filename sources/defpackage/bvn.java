package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.featureuserprofile.todo.activity.TodoActivtiesResCallBack;
import com.huawei.featureuserprofile.todo.datatype.HistoriesDetailsBean;
import com.huawei.featureuserprofile.todo.datatype.HistoryBean;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.marketing.request.ActivityInfoListFactory;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.networkclient.ResultCallback;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.operation.utils.OperationUtilsApi;
import com.huawei.pluginachievement.manager.db.IAchieveDBMgr;
import com.huawei.ui.main.stories.soical.interactor.OperationInteractorsApi;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class bvn {

    /* renamed from: a, reason: collision with root package name */
    private OperationUtilsApi f521a;
    private final OperationInteractorsApi b;
    private Context c;
    private final CopyOnWriteArrayList<ceb> e;

    static class e {
        public static bvn d = new bvn();
    }

    private bvn() {
        this.e = new CopyOnWriteArrayList<>();
        this.c = BaseApplication.getContext();
        this.b = (OperationInteractorsApi) Services.a("OperationBundle", OperationInteractorsApi.class);
    }

    public static bvn b() {
        return e.d;
    }

    public void a(final int i, boolean z, final TodoActivtiesResCallBack<List<ceb>> todoActivtiesResCallBack) {
        LogUtil.a("TodoCardInteractors", "getActivityFromCloud()");
        if (z) {
            c(i, todoActivtiesResCallBack);
        }
        final long currentTimeMillis = System.currentTimeMillis();
        c(new IBaseResponseCallback() { // from class: bvn.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                LogUtil.a("TodoCardInteractors", "getActivityFromCloud resCode = " + i2);
                long currentTimeMillis2 = System.currentTimeMillis();
                bvn.this.a(obj, i, (TodoActivtiesResCallBack<List<ceb>>) todoActivtiesResCallBack);
                bvn.this.d(i2, currentTimeMillis, currentTimeMillis2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, long j, long j2) {
        String str = i == 200 ? "0" : "1";
        long j3 = j2 - j;
        if (!"0".equals(str) || j3 >= 5000) {
            LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>();
            linkedHashMap.put("module", "3");
            linkedHashMap.put("status", str);
            linkedHashMap.put(OpAnalyticsConstants.DELAY, String.valueOf(j3));
            linkedHashMap.put(OpAnalyticsConstants.ERROR_CODE, String.valueOf(i));
            OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_REQUEST_MODULE_85030001.value(), linkedHashMap);
        }
    }

    public void d(String str, final TodoActivtiesResCallBack<HistoryBean> todoActivtiesResCallBack) {
        c(str, new IBaseResponseCallback() { // from class: bvn.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                LogUtil.a("TodoCardInteractors", "getHistoryFromCloud resCode = ", Integer.valueOf(i));
                if (obj instanceof HistoryBean) {
                    HistoryBean historyBean = (HistoryBean) obj;
                    LogUtil.c("TodoCardInteractors", "getHistoryFromCloud historyBean = ", historyBean.toString());
                    todoActivtiesResCallBack.onFinished(historyBean);
                    return;
                }
                todoActivtiesResCallBack.onFinished(null);
            }
        });
    }

    private void c(String str, final IBaseResponseCallback iBaseResponseCallback) {
        ActivityInfoListFactory activityInfoListFactory = new ActivityInfoListFactory(BaseApplication.getContext());
        final Map<String, String> headers = activityInfoListFactory.getHeaders();
        if (headers.get("x-huid") == null) {
            LogUtil.h("TodoCardInteractors", "getHistory fail, x-huid is null");
            return;
        }
        final Map<String, Object> body = activityInfoListFactory.getBody(null);
        body.put("activityId", str);
        GRSManager.a(this.c).e("activityUrl", new GrsQueryCallback() { // from class: bvn.5
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str2) {
                LogUtil.c("TodoCardInteractors", "GRSManager onCallBackSuccess ACTIVITY_KEY url = ", str2);
                LogUtil.c("TodoCardInteractors", "HttpPost request url:", str2, "/activity/getHistory", " params:", body.toString(), ",headers:", headers.toString());
                lqi.d().b(str2 + "/activity/getHistory", headers, lql.b(body), HistoryBean.class, new ResultCallback<HistoryBean>() { // from class: bvn.5.3
                    @Override // com.huawei.networkclient.ResultCallback
                    /* renamed from: c, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(HistoryBean historyBean) {
                        LogUtil.a("TodoCardInteractors", "getHistory success. data: ", historyBean.getResultCode());
                        iBaseResponseCallback.d(historyBean.getResultCode().intValue(), historyBean);
                    }

                    @Override // com.huawei.networkclient.ResultCallback
                    public void onFailure(Throwable th) {
                        LogUtil.h("TodoCardInteractors", "getHistory onFailure. throwable: ", th.toString());
                        iBaseResponseCallback.d(-1, null);
                    }
                });
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.c("TodoCardInteractors", "GRSManager onCallBackFail ACTIVITY_KEY code = ", Integer.valueOf(i));
                iBaseResponseCallback.d(-1, null);
            }
        });
    }

    public void c(int i, TodoActivtiesResCallBack<List<ceb>> todoActivtiesResCallBack) {
        String str;
        String str2;
        if (i == 0) {
            str = SharedPreferenceManager.b(this.c, Integer.toString(10026), "HOME_TODO_ACTIVITY_SAVE_TIME");
            str2 = "HOME_TODO_ACTIVITY_SAVE";
        } else if (i == 1) {
            str = SharedPreferenceManager.b(this.c, Integer.toString(10026), "HOME_PREVIEW_ACTIVITY_SAVE_TIME");
            str2 = "HOME_PREVIEW_ACTIVITY_SAVE";
        } else {
            LogUtil.h("TodoCardInteractors", "no branch!");
            str = "";
            str2 = "";
        }
        if (e(str) && !TextUtils.isEmpty(str2)) {
            try {
                c(new JSONArray(SharedPreferenceManager.b(this.c, Integer.toString(10026), str2)), i, true, todoActivtiesResCallBack);
                return;
            } catch (JSONException e2) {
                LogUtil.b("TodoCardInteractors", "数据解析出错：", e2.getMessage());
            }
        }
        todoActivtiesResCallBack.onFinished(null);
    }

    private boolean e(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        }
        try {
            if (jdl.d(Long.parseLong(str), System.currentTimeMillis())) {
                return true;
            }
            d();
            return false;
        } catch (NumberFormatException e2) {
            LogUtil.b("TodoCardInteractors", "缓存记录时间数字格式转换异常", e2.getMessage());
            return true;
        }
    }

    private void d() {
        StorageParams storageParams = new StorageParams();
        SharedPreferenceManager.e(this.c, Integer.toString(10026), "HOME_PREVIEW_ACTIVITY_SAVE", "", storageParams);
        SharedPreferenceManager.e(this.c, Integer.toString(10026), "HOME_PREVIEW_USED_DATA_SAVE", "", storageParams);
        SharedPreferenceManager.e(this.c, Integer.toString(10026), "HOME_TODO_ACTIVITY_SAVE", "", storageParams);
    }

    private void c(int i, JSONArray jSONArray) {
        if (jSONArray == null) {
            LogUtil.h("TodoCardInteractors", "saveCache saveOperationActivitys is null, return");
            return;
        }
        LogUtil.c("TodoCardInteractors", "saveOperationActivitys = + ", jSONArray);
        StorageParams storageParams = new StorageParams();
        Date time = Calendar.getInstance(TimeZone.getTimeZone("GMT")).getTime();
        String str = "";
        if (i == 0) {
            SharedPreferenceManager.e(this.c, Integer.toString(10026), "HOME_TODO_ACTIVITY_SAVE_TIME", time.getTime() + "", storageParams);
            str = "HOME_TODO_ACTIVITY_SAVE";
        } else if (i == 1) {
            SharedPreferenceManager.e(this.c, Integer.toString(10026), "HOME_PREVIEW_ACTIVITY_SAVE_TIME", time.getTime() + "", storageParams);
            str = "HOME_PREVIEW_ACTIVITY_SAVE";
        } else {
            LogUtil.h("TodoCardInteractors", "no branch!");
        }
        if (TextUtils.isEmpty(str)) {
            return;
        }
        SharedPreferenceManager.e(this.c, Integer.toString(10026), str, jSONArray.toString(), storageParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Object obj, int i, TodoActivtiesResCallBack<List<ceb>> todoActivtiesResCallBack) {
        if (!koq.e(obj, ceb.class)) {
            todoActivtiesResCallBack.onFinished(null);
            LogUtil.h("TodoCardInteractors", "processActivties result type is error");
            return;
        }
        List<ceb> list = (List) obj;
        if (koq.b(list)) {
            LogUtil.a("TodoCardInteractors", "processActivties: operationObjects is empty");
            todoActivtiesResCallBack.onFinished(new ArrayList());
            c(i, new JSONArray());
        } else {
            LogUtil.c("TodoCardInteractors", "processActivties result = " + obj.toString());
            a(list, todoActivtiesResCallBack);
        }
    }

    private void c(JSONArray jSONArray, int i, boolean z, TodoActivtiesResCallBack<List<ceb>> todoActivtiesResCallBack) throws JSONException {
        LogUtil.a("TodoCardInteractors", "getActivitiseResSucc：type=" + i);
        if (i != 0) {
            if (i == 1) {
                d(jSONArray, z, todoActivtiesResCallBack);
                return;
            } else {
                LogUtil.h("TodoCardInteractors", "no branch!");
                todoActivtiesResCallBack.onFinished(null);
                return;
            }
        }
        this.e.clear();
        for (int i2 = 0; i2 < jSONArray.length(); i2++) {
            ceb expoundOperationActivity = this.b.expoundOperationActivity(jSONArray.getJSONObject(i2));
            if (expoundOperationActivity != null && !a(expoundOperationActivity) && expoundOperationActivity.h() == 0) {
                this.e.add(expoundOperationActivity);
            }
        }
        todoActivtiesResCallBack.onFinished(new ArrayList(this.e));
    }

    private void d(JSONArray jSONArray, boolean z, TodoActivtiesResCallBack<List<ceb>> todoActivtiesResCallBack) throws JSONException {
        int d;
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArray2 = new JSONArray();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            ceb expoundOperationActivity = this.b.expoundOperationActivity(jSONObject);
            if (expoundOperationActivity != null && !TextUtils.isEmpty(expoundOperationActivity.s()) && (d = d(expoundOperationActivity.s(), expoundOperationActivity.v())) <= 3 && d >= 1 && expoundOperationActivity.j() == 3) {
                jSONArray2.put(jSONObject);
                arrayList.add(expoundOperationActivity);
            }
        }
        if (!z) {
            c(1, jSONArray2);
        }
        Collections.sort(arrayList, new bvi());
        todoActivtiesResCallBack.onFinished(arrayList);
    }

    private void a(List<ceb> list, final TodoActivtiesResCallBack<List<ceb>> todoActivtiesResCallBack) {
        LogUtil.a("TodoCardInteractors", "todoActivitiseResSucc");
        JSONArray jSONArray = new JSONArray();
        this.e.clear();
        for (ceb cebVar : list) {
            if (cebVar != null && !a(cebVar) && cebVar.h() == 0) {
                jSONArray.put(nrv.a(cebVar));
                this.e.add(cebVar);
            }
        }
        e(new HttpResCallback() { // from class: bvn.4
            @Override // com.huawei.hwcommonmodel.utils.httputils.pluginoperation.HttpResCallback
            public void onFinished(int i, String str) {
                if (i != 200) {
                    LogUtil.a("TodoCardInteractors", "handleUserActivity, resCode = ", Integer.valueOf(i));
                }
                bvn.this.a((TodoActivtiesResCallBack<List<ceb>>) todoActivtiesResCallBack, 0);
            }
        });
        c(0, jSONArray);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final TodoActivtiesResCallBack<List<ceb>> todoActivtiesResCallBack, final int i) {
        LogUtil.a("TodoCardInteractors", "getHistoryFromCloudCircle:mTodoActivityList:", Integer.valueOf(this.e.size()), ",index: ", Integer.valueOf(i));
        if (this.e.size() <= i) {
            todoActivtiesResCallBack.onFinished(null);
            return;
        }
        final ceb cebVar = this.e.get(i);
        if (cebVar != null && !TextUtils.isEmpty(cebVar.c())) {
            d(cebVar.c(), new TodoActivtiesResCallBack<HistoryBean>() { // from class: bvn.1
                @Override // com.huawei.featureuserprofile.todo.activity.TodoActivtiesResCallBack
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void onFinished(HistoryBean historyBean) {
                    bvn.this.e(historyBean, i, cebVar, todoActivtiesResCallBack);
                }
            });
        } else {
            todoActivtiesResCallBack.onFinished(null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(HistoryBean historyBean, int i, ceb cebVar, TodoActivtiesResCallBack<List<ceb>> todoActivtiesResCallBack) {
        if (historyBean != null) {
            LogUtil.a("TodoCardInteractors", "dealHistoryActivity: historyBean = ", historyBean.toString());
            List<HistoriesDetailsBean> histories = historyBean.getHistories();
            if (histories != null) {
                b(histories);
            }
            historyBean.setHistories(histories);
            if (c(historyBean)) {
                LogUtil.a("TodoCardInteractors", "成绩达标，删除数据：", Integer.valueOf(i));
                if (this.e.size() > i) {
                    this.e.remove(i);
                }
                if (this.e.size() > i) {
                    a(todoActivtiesResCallBack, i);
                    return;
                }
            } else {
                cebVar.g(2);
                cebVar.a(historyBean.getActivityTarget());
                cebVar.j(historyBean.getContinuity());
                cebVar.f(historyBean.getTargetDays());
                cebVar.e(0);
                int i2 = i + 1;
                if (this.e.size() > i2) {
                    a(todoActivtiesResCallBack, i2);
                    return;
                }
            }
        } else {
            cebVar.g(1);
            int i3 = i + 1;
            if (this.e.size() > i3) {
                a(todoActivtiesResCallBack, i3);
                return;
            }
        }
        LogUtil.a("TodoCardInteractors", "历史数据获取完毕，回调结果");
        a(this.e);
        LogUtil.a("TodoCardInteractors", "dealHistoryActivity，mTodoActivityList.size = ", Integer.valueOf(this.e.size()));
        todoActivtiesResCallBack.onFinished(new ArrayList(this.e));
    }

    private void e(HttpResCallback httpResCallback) {
        ArrayList arrayList = new ArrayList();
        if (koq.b(this.e)) {
            LogUtil.h("TodoCardInteractors", "handleUserActivity, mTodoActivityList is null");
            httpResCallback.onFinished(-1, null);
            return;
        }
        for (int i = 0; i < this.e.size(); i++) {
            ceb cebVar = this.e.get(i);
            if (cebVar != null && !TextUtils.isEmpty(cebVar.c())) {
                arrayList.add(cebVar.c());
            }
        }
        if (arrayList.size() == 0) {
            httpResCallback.onFinished(-1, null);
        } else {
            c(1, arrayList.get(0), arrayList, httpResCallback);
        }
    }

    private void c(int i, String str, List<String> list, final HttpResCallback httpResCallback) {
        OperationInteractorsApi operationInteractorsApi = (OperationInteractorsApi) Services.a("OperationBundle", OperationInteractorsApi.class);
        if (operationInteractorsApi == null) {
            LogUtil.b("TodoCardInteractors", "getActivityInfo, operationApi is null");
            httpResCallback.onFinished(-1, null);
        } else {
            operationInteractorsApi.getUserActivityInfo(i, str, list, new IBaseResponseCallback() { // from class: bvn.8
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i2, Object obj) {
                    if (!(obj instanceof List)) {
                        LogUtil.h("TodoCardInteractors", "objData isn't instanceof List");
                        httpResCallback.onFinished(-1, null);
                        return;
                    }
                    List<rwz> list2 = (List) obj;
                    if (!koq.b(list2)) {
                        Iterator it = bvn.this.e.iterator();
                        while (it.hasNext()) {
                            ceb cebVar = (ceb) it.next();
                            if (cebVar != null && bvn.this.c(cebVar.f())) {
                                for (rwz rwzVar : list2) {
                                    if (cebVar.c().equals(rwzVar.d())) {
                                        if (rwzVar.b() != 0) {
                                            bvn.this.e.remove(cebVar);
                                        } else {
                                            cebVar.e(0);
                                        }
                                    }
                                }
                            }
                        }
                        httpResCallback.onFinished(200, null);
                        return;
                    }
                    LogUtil.a("TodoCardInteractors", "getActivityInfo, infoList is null");
                    httpResCallback.onFinished(-1, null);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean c(int i) {
        if (this.f521a == null) {
            this.f521a = (OperationUtilsApi) Services.a("PluginOperation", OperationUtilsApi.class);
        }
        OperationUtilsApi operationUtilsApi = this.f521a;
        if (operationUtilsApi != null) {
            return operationUtilsApi.isChallengeActivity(i);
        }
        return false;
    }

    private void b(List<HistoriesDetailsBean> list) {
        if (koq.b(list)) {
            LogUtil.h("TodoCardInteractors", "filterAbnormalData historiesDetailsBeanList is empty");
            return;
        }
        Iterator<HistoriesDetailsBean> it = list.iterator();
        LogUtil.a("TodoCardInteractors", "filterAbnormalData start: histories size = ", Integer.valueOf(list.size()));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        while (it.hasNext()) {
            HistoriesDetailsBean next = it.next();
            if (next == null) {
                LogUtil.h("TodoCardInteractors", "filterAbnormalData HistoriesDetailsBean is null");
                it.remove();
            } else if (TextUtils.isEmpty(next.getActivityDate())) {
                LogUtil.a("TodoCardInteractors", "filterAbnormalData histories activityDate is empty");
                it.remove();
            } else {
                try {
                    Date parse = simpleDateFormat.parse(next.getActivityDate());
                    Date parse2 = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
                    if (parse != null && parse.compareTo(parse2) > 0) {
                        it.remove();
                    }
                } catch (ParseException e2) {
                    LogUtil.b("TodoCardInteractors", "filterAbnormalData exception ", e2.getMessage());
                }
            }
        }
        LogUtil.a("TodoCardInteractors", "filterAbnormalData end : histories size = ", Integer.valueOf(list.size()));
    }

    private void a(List<ceb> list) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(list);
        JSONArray jSONArray = new JSONArray();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            JSONObject activityBeanToJSONObject = this.b.activityBeanToJSONObject((ceb) it.next());
            if (activityBeanToJSONObject != null) {
                jSONArray.put(activityBeanToJSONObject);
            }
        }
        c(0, jSONArray);
    }

    private boolean c(HistoryBean historyBean) {
        boolean z = false;
        if (historyBean == null) {
            return false;
        }
        if (historyBean.getStatus() > 0) {
            return true;
        }
        List<HistoriesDetailsBean> histories = historyBean.getHistories();
        if (koq.b(histories)) {
            LogUtil.h("TodoCardInteractors", "isComplete detailsList is empty ");
            return false;
        }
        HistoriesDetailsBean historiesDetailsBean = histories.get(0);
        if (historiesDetailsBean == null) {
            return false;
        }
        String activityDate = historiesDetailsBean.getActivityDate();
        if (!TextUtils.isEmpty(activityDate)) {
            try {
                if (jdl.d(DateFormatUtil.d(activityDate, DateFormatUtil.DateFormatType.DATE_FORMAT_YYYY_MM_DD).getTime(), System.currentTimeMillis())) {
                    if (historiesDetailsBean.getCompleteFlag() == 1) {
                        z = true;
                    }
                }
            } catch (ParseException unused) {
                LogUtil.b("TodoCardInteractors", "Covert Time Format Exception");
            }
        }
        LogUtil.a("TodoCardInteractors", "isComplete = ", Boolean.valueOf(z));
        return z;
    }

    private boolean a(ceb cebVar) {
        boolean z = d(cebVar.t(), cebVar.v()) > 0 || d(cebVar.n(), cebVar.v()) < 0 || cebVar.f() == 400;
        if (TextUtils.isEmpty(cebVar.s()) || cebVar.j() != 3 || d(cebVar.s(), cebVar.v()) <= 0) {
            return z;
        }
        return true;
    }

    private void c(final IBaseResponseCallback iBaseResponseCallback) {
        if (CommonUtil.bu() || LoginInit.getInstance(this.c).isKidAccount()) {
            LogUtil.a("TodoCardInteractors", "isStoreDemoVersion or kidAcccount getOperationList return.");
            iBaseResponseCallback.d(-1, null);
            return;
        }
        ActivityInfoListFactory activityInfoListFactory = new ActivityInfoListFactory(BaseApplication.getContext());
        final Map<String, String> headers = activityInfoListFactory.getHeaders();
        if (headers.get("x-huid") == null) {
            LogUtil.b("TodoCardInteractors", "getActivityInfo fail, x-huid is null");
        } else {
            final Map<String, Object> b = b(activityInfoListFactory.getBody(null));
            ThreadPoolManager.d().execute(new Runnable() { // from class: bvn.6
                @Override // java.lang.Runnable
                public void run() {
                    lqi.d().b(GRSManager.a(BaseApplication.getContext()).getUrl("activityUrl") + "/activity/getActivities", headers, lql.b(b), bvd.class, new ResultCallback<bvd>() { // from class: bvn.6.1
                        @Override // com.huawei.networkclient.ResultCallback
                        /* renamed from: d, reason: merged with bridge method [inline-methods] */
                        public void onSuccess(bvd bvdVar) {
                            LogUtil.a("TodoCardInteractors", "getOperationList success. data: ", bvdVar.getResultCode());
                            iBaseResponseCallback.d(bvdVar.getResultCode().intValue(), bvdVar.d());
                        }

                        @Override // com.huawei.networkclient.ResultCallback
                        public void onFailure(Throwable th) {
                            LogUtil.h("TodoCardInteractors", "getOperationList onFailure. throwable: ", th.toString());
                            iBaseResponseCallback.d(-1, null);
                        }
                    });
                }
            });
        }
    }

    private Map<String, Object> b(Map<String, Object> map) {
        map.put("pageNo", "0");
        map.put(IAchieveDBMgr.PARAM_PAGE_SIZE, "1000");
        map.put("joinStatus", "1");
        map.put("finishFlag", "1");
        if (CommonUtil.as() || CommonUtil.cc()) {
            map.put("isBeta", "1");
        }
        return map;
    }

    private int d(String str, String str2) {
        try {
            int c = jdl.c(System.currentTimeMillis(), jdl.d(str2), DateFormatUtil.e(str, DateFormatUtil.DateFormatType.DEFAULT_DATE_FORMAT, jdl.b()).getTime(), jdl.d(str2));
            return c > 0 ? c - 1 : c + 1;
        } catch (ParseException e2) {
            LogUtil.b("TodoCardInteractors", "格式日期出错:" + e2.getMessage());
            return 0;
        }
    }
}
