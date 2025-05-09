package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.huawei.basefitnessadvice.callback.DataCallback;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.FitnessDayPlan;
import com.huawei.basefitnessadvice.model.FitnessPlanCourse;
import com.huawei.basefitnessadvice.model.FitnessWeekPlan;
import com.huawei.basefitnessadvice.model.PlanInfo;
import com.huawei.basefitnessadvice.model.intplan.IntPlan;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.health.userlabelmgr.api.UserLabelServiceApi;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginfitnessadvice.FitnessPackageInfo;
import com.huawei.up.model.UserInfomation;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class euh {

    /* renamed from: a, reason: collision with root package name */
    private ConcurrentHashMap<String, Double> f12329a;
    private Handler c;
    private ArrayList<FitnessPackageInfo> e;

    static class e {
        static euh d = new euh();
    }

    private euh() {
        this.c = new Handler(Looper.getMainLooper());
        this.f12329a = new ConcurrentHashMap<>(10);
    }

    public static euh a() {
        return e.d;
    }

    public FitnessPackageInfo d(String str) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d("PlanInfoManager", "getFitnessPkgInfoById invalid planTempId");
            return null;
        }
        ArrayList<FitnessPackageInfo> arrayList = this.e;
        if (arrayList == null) {
            ReleaseLogUtil.c("PlanInfoManager", "getFitnessPkgInfoById mAllFitnessPackageInfo == null");
            return null;
        }
        Iterator<FitnessPackageInfo> it = arrayList.iterator();
        while (it.hasNext()) {
            FitnessPackageInfo next = it.next();
            if (next != null && str.equals(next.acquirePlanTempId())) {
                return next;
            }
        }
        return null;
    }

    public List<FitnessPackageInfo> b(int i) {
        if (this.e == null) {
            ReleaseLogUtil.c("PlanInfoManager", "getFitnessPkgInfoById mAllFitnessPackageInfo == null");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<FitnessPackageInfo> it = this.e.iterator();
        while (it.hasNext()) {
            FitnessPackageInfo next = it.next();
            if (next != null && next.getPlanType() == i) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public void b(final int i, int i2, final UiCallback<List<FitnessPackageInfo>> uiCallback) {
        LogUtil.a("PlanInfoManager", "get filter FitnessPkg");
        if (uiCallback == null) {
            ReleaseLogUtil.d("PlanInfoManager", "getAllFitnessPkg invalid parameters, callback is null");
            return;
        }
        ArrayList<FitnessPackageInfo> arrayList = this.e;
        if (arrayList == null) {
            if (!TextUtils.isEmpty(ash.b("allFitnessPkgs"))) {
                ash.d("allFitnessPkgs");
            }
            eqa.a().getFitnessPkgInfo(0, 50, i2, new DataCallback() { // from class: euh.3
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onFailure(int i3, String str) {
                    euh.this.a(i3, str, i, uiCallback);
                }

                /* JADX WARN: Multi-variable type inference failed */
                /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.String] */
                /* JADX WARN: Type inference failed for: r0v1 */
                /* JADX WARN: Type inference failed for: r0v8, types: [com.huawei.basefitnessadvice.callback.UiCallback] */
                /* JADX WARN: Type inference failed for: r1v0, types: [java.lang.String] */
                /* JADX WARN: Type inference failed for: r1v1 */
                /* JADX WARN: Type inference failed for: r1v5, types: [android.os.Handler] */
                @Override // com.huawei.basefitnessadvice.callback.DataCallback
                public void onSuccess(JSONObject jSONObject) {
                    FileInputStream fileInputStream;
                    Object obj = "fis IOException";
                    String str = "PlanInfoManager";
                    if (jSONObject == null) {
                        LogUtil.b("PlanInfoManager", "getFitnessPackageInfo onSuccess data is null.");
                        return;
                    }
                    LogUtil.a("PlanInfoManager", "getFitnessPkgInfo onSuccess");
                    euh euhVar = null;
                    FileInputStream fileInputStream2 = null;
                    try {
                        try {
                            fileInputStream = new FileInputStream(jSONObject.toString());
                        } catch (Throwable th) {
                            th = th;
                            fileInputStream = euhVar;
                        }
                    } catch (IOException unused) {
                    }
                    try {
                        FileUtils.a(fileInputStream, new File(euh.this.d()));
                        try {
                            fileInputStream.close();
                        } catch (IOException unused2) {
                            LogUtil.b("PlanInfoManager", "fis IOException");
                        }
                    } catch (IOException unused3) {
                        fileInputStream2 = fileInputStream;
                        LogUtil.b("PlanInfoManager", "getAllFitnessPkg() onSuccess IOException");
                        if (fileInputStream2 != null) {
                            try {
                                fileInputStream2.close();
                            } catch (IOException unused4) {
                                LogUtil.b("PlanInfoManager", "fis IOException");
                            }
                        }
                        ArrayList b = euh.this.b(jSONObject);
                        euh.this.e = b;
                        euh euhVar2 = euh.this;
                        euhVar2.d((ArrayList<FitnessPackageInfo>) euhVar2.e);
                        obj = uiCallback;
                        str = euh.this.c;
                        euhVar = euh.this;
                        obj.onSuccess(str, euhVar.d(i, (ArrayList<FitnessPackageInfo>) b));
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException unused5) {
                                LogUtil.b(str, obj);
                            }
                        }
                        throw th;
                    }
                    ArrayList b2 = euh.this.b(jSONObject);
                    euh.this.e = b2;
                    euh euhVar22 = euh.this;
                    euhVar22.d((ArrayList<FitnessPackageInfo>) euhVar22.e);
                    obj = uiCallback;
                    str = euh.this.c;
                    euhVar = euh.this;
                    obj.onSuccess(str, euhVar.d(i, (ArrayList<FitnessPackageInfo>) b2));
                }
            });
            return;
        }
        uiCallback.onSuccess(this.c, d(i, arrayList));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String d() throws IOException {
        return BaseApplication.getContext().getFilesDir().getCanonicalPath() + File.separator + "fitness" + File.separator + "tempCache" + File.separator + "allFitnessPkgs.json";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, String str, int i2, UiCallback<List<FitnessPackageInfo>> uiCallback) {
        String str2;
        ReleaseLogUtil.d("PlanInfoManager", "getFitnessPkgInfos errorCode = ", Integer.valueOf(i));
        try {
            str2 = CommonUtil.t(d());
        } catch (IOException unused) {
            ReleaseLogUtil.c("PlanInfoManager", "getAllFitnessPkg local IOException");
            str2 = "";
        }
        ArrayList<FitnessPackageInfo> arrayList = null;
        if (TextUtils.isEmpty(str2)) {
            ReleaseLogUtil.c("PlanInfoManager", "getAllFitnessPkg local has no data");
            this.e = null;
            uiCallback.onFailure(this.c, i, str);
        } else {
            try {
                arrayList = b(new JSONObject(str2));
                this.e = arrayList;
                d(arrayList);
            } catch (JSONException e2) {
                LogUtil.b("PlanInfoManager", "getFitnessPkgInfos JSONException = ", LogAnonymous.b((Throwable) e2));
            }
            uiCallback.onSuccess(this.c, d(i2, arrayList));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(ArrayList<FitnessPackageInfo> arrayList) {
        if (arrayList != null) {
            for (int i = 0; i < arrayList.size(); i++) {
                FitnessPackageInfo fitnessPackageInfo = arrayList.get(i);
                for (int i2 = 0; i2 < fitnessPackageInfo.acquireFitnessWeekPlanList().size(); i2++) {
                    a(fitnessPackageInfo, i2);
                }
            }
        }
    }

    private void a(FitnessPackageInfo fitnessPackageInfo, int i) {
        if (fitnessPackageInfo == null || koq.b(fitnessPackageInfo.acquireFitnessWeekPlanList(), i)) {
            LogUtil.h("PlanInfoManager", "invalid parameters");
            return;
        }
        FitnessWeekPlan fitnessWeekPlan = fitnessPackageInfo.acquireFitnessWeekPlanList().get(i);
        if (fitnessWeekPlan == null || koq.b(fitnessWeekPlan.acquireWeekList())) {
            LogUtil.h("PlanInfoManager", "buildWeekPlan, weekPlan == null");
            return;
        }
        for (FitnessDayPlan fitnessDayPlan : fitnessWeekPlan.acquireWeekList()) {
            if (fitnessDayPlan != null && !koq.b(fitnessDayPlan.acquireDayPlanCourses())) {
                for (FitnessPlanCourse fitnessPlanCourse : fitnessDayPlan.acquireDayPlanCourses()) {
                    if (fitnessPlanCourse != null) {
                        this.f12329a.put(fitnessPlanCourse.acquireCourseId(), Double.valueOf(((fitnessPlanCourse.acquireGender() == 2 && ggg.a() == 0) || fitnessPlanCourse.acquireGender() == 0) ? fitnessPlanCourse.acquireWorkoutRealCalMan() : fitnessPlanCourse.acquireWorkoutRealCal()));
                    }
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<FitnessPackageInfo> d(int i, ArrayList<FitnessPackageInfo> arrayList) {
        UserProfileMgrApi userProfileMgrApi;
        UserInfomation userInfo;
        if (i == -1 && (userProfileMgrApi = (UserProfileMgrApi) Services.a("HWUserProfileMgr", UserProfileMgrApi.class)) != null && (userInfo = userProfileMgrApi.getUserInfo()) != null) {
            i = userInfo.getGenderOrDefaultValue();
        }
        ArrayList<FitnessPackageInfo> arrayList2 = new ArrayList<>(10);
        if (arrayList != null && !arrayList.isEmpty()) {
            Iterator<FitnessPackageInfo> it = arrayList.iterator();
            while (it.hasNext()) {
                FitnessPackageInfo next = it.next();
                if (d(i, next)) {
                    arrayList2.add(next);
                }
            }
        }
        return arrayList2;
    }

    private boolean d(int i, FitnessPackageInfo fitnessPackageInfo) {
        if (fitnessPackageInfo == null || fitnessPackageInfo.acquirePlanTempId() == null) {
            ReleaseLogUtil.d("PlanInfoManager", "isFilterPackage fitness plan or plan id is null");
            return false;
        }
        String trim = fitnessPackageInfo.acquirePlanTempId().trim();
        if (trim.length() < 5) {
            LogUtil.h("PlanInfoManager", "fitness plan id length error ", trim);
            return false;
        }
        return evf.d(trim, i);
    }

    public double c(String str) {
        Double d;
        ConcurrentHashMap<String, Double> concurrentHashMap = this.f12329a;
        if (concurrentHashMap == null || !concurrentHashMap.containsKey(str) || (d = this.f12329a.get(str)) == null) {
            return 0.0d;
        }
        return d.doubleValue();
    }

    public void d(final int i, int i2, final UiCallback<List<PlanInfo>> uiCallback) {
        if (uiCallback == null) {
            LogUtil.h("PlanInfoManager", "callback == null");
        } else if (!evg.d(BaseApplication.getContext())) {
            LogUtil.a("PlanInfoManager", "recommendation not allowed");
            uiCallback.onSuccess(this.c, new ArrayList(0));
        } else {
            b(i2, -1, new UiCallback<List<FitnessPackageInfo>>() { // from class: euh.4
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i3, String str) {
                    ReleaseLogUtil.d("PlanInfoManager", "getRecommendedPlans, errorCode = ", Integer.valueOf(i3));
                    uiCallback.onFailure(i3, str);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<FitnessPackageInfo> list) {
                    euh.this.d(i, list, (UiCallback<List<PlanInfo>>) uiCallback);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final int i, final List<FitnessPackageInfo> list, final UiCallback<List<PlanInfo>> uiCallback) {
        asc.e().b(new Runnable() { // from class: eul
            @Override // java.lang.Runnable
            public final void run() {
                euh.this.c(list, i, uiCallback);
            }
        });
    }

    /* synthetic */ void c(List list, int i, UiCallback uiCallback) {
        List<PlanInfo> arrayList = new ArrayList<>(10);
        Iterator it = list.iterator();
        while (it.hasNext()) {
            FitnessPackageInfo fitnessPackageInfo = (FitnessPackageInfo) it.next();
            if (i == -1 || c(i, fitnessPackageInfo)) {
                arrayList.add(fitnessPackageInfo);
            }
        }
        LogUtil.a("PlanInfoManager", "doRecommend in size:", Integer.valueOf(arrayList.size()));
        List<String> allLabels = ((UserLabelServiceApi) Services.c("HWUserLabelMgr", UserLabelServiceApi.class)).getAllLabels(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011));
        ArrayList arrayList2 = new ArrayList(10);
        if (koq.e((Object) allLabels, String.class)) {
            a(arrayList2, allLabels, arrayList);
        }
        for (PlanInfo planInfo : arrayList) {
            if (!arrayList2.contains(planInfo)) {
                arrayList2.add(planInfo);
            }
        }
        LogUtil.a("PlanInfoManager", "doRecommend out size:", Integer.valueOf(arrayList2.size()));
        uiCallback.onSuccess(this.c, arrayList2);
    }

    private boolean c(int i, FitnessPackageInfo fitnessPackageInfo) {
        int i2 = (fitnessPackageInfo.getPlanType() == IntPlan.PlanType.FIT_PLAN.getType() || fitnessPackageInfo.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN.getType()) ? 3 : -1;
        if (fitnessPackageInfo.getPlanType() == IntPlan.PlanType.RUN_PLAN.getType() || fitnessPackageInfo.getPlanType() == IntPlan.PlanType.AI_RUN_PLAN.getType()) {
            i2 = 0;
        }
        return i2 == i;
    }

    private void a(List<PlanInfo> list, List<String> list2, List<PlanInfo> list3) {
        if (koq.b(list2) || koq.b(list3)) {
            LogUtil.h("PlanInfoManager", "userLabels or plans are empty");
            return;
        }
        for (PlanInfo planInfo : list3) {
            if (planInfo != null) {
                int i = 0;
                for (String str : list2) {
                    List<String> labels = planInfo.getLabels();
                    if (!koq.b(labels) && labels.contains(str)) {
                        i++;
                    }
                }
                if (i > 0) {
                    list.add(planInfo);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ArrayList<FitnessPackageInfo> b(JSONObject jSONObject) {
        if (jSONObject == null) {
            ReleaseLogUtil.d("PlanInfoManager", "convertFitnessPackageInfo, data == null");
            return null;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("fitnessPackageInfoList");
        if (optJSONArray == null) {
            ReleaseLogUtil.d("PlanInfoManager", "convertFitnessPackageInfo has no key fitnessPackageInfoList");
            return null;
        }
        ArrayList<FitnessPackageInfo> arrayList = new ArrayList<>(10);
        for (int i = 0; i < optJSONArray.length(); i++) {
            FitnessPackageInfo a2 = a(optJSONArray.optJSONObject(i));
            if (a2 != null && !e(a2)) {
                arrayList.add(a2);
            }
        }
        return arrayList;
    }

    private boolean e(FitnessPackageInfo fitnessPackageInfo) {
        return fitnessPackageInfo.getPlanType() == IntPlan.PlanType.AI_FITNESS_PLAN.getType() && CommonUtil.bb();
    }

    private FitnessPackageInfo a(JSONObject jSONObject) {
        return (FitnessPackageInfo) new Gson().fromJson(jSONObject.toString(), FitnessPackageInfo.class);
    }
}
