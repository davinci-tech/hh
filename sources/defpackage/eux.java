package defpackage;

import android.bluetooth.BluetoothAdapter;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.Topic;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.healthdatamgr.api.HealthDataMgrApi;
import com.huawei.health.suggestion.RunCourseRecommendCallback;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.up.model.UserInfomation;
import defpackage.eux;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes8.dex */
public class eux {
    private fij g = null;
    private List<Integer> c = new ArrayList();
    private List<Integer> i = new ArrayList();
    private List<Long> d = new ArrayList();
    private List<Integer> b = new ArrayList();

    /* renamed from: a, reason: collision with root package name */
    private List<Integer> f12345a = new ArrayList();
    private List<RunCourseRecommendCallback> e = Collections.synchronizedList(new ArrayList());

    private double d(int i, int i2) {
        return i == i2 ? 0.01d : -0.01d;
    }

    private void e(int i) {
        if (i == 100) {
            if (e()) {
                ReleaseLogUtil.e("Suggestion_RunCourseRecommend", "start Refresh");
                b(true, this.e);
                return;
            } else {
                ReleaseLogUtil.e("Suggestion_RunCourseRecommend", "wait... Refresh");
                return;
            }
        }
        if (i != 101) {
            return;
        }
        if (a()) {
            ReleaseLogUtil.e("Suggestion_RunCourseRecommend", "start Refresh", 101);
            b(false, this.e);
        } else {
            ReleaseLogUtil.e("Suggestion_RunCourseRecommend", "wait... Refresh");
        }
    }

    public void d(final RunCourseRecommendCallback runCourseRecommendCallback) {
        if (runCourseRecommendCallback == null) {
            LogUtil.h("Suggestion_RunCourseRecommend", "getRunCourseRecommend callback == null");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: eux.1
                @Override // java.lang.Runnable
                public void run() {
                    eux.this.c(runCourseRecommendCallback);
                }
            });
        }
    }

    private boolean e(boolean z) {
        if (!z) {
            LogUtil.h("Suggestion_RunCourseRecommend", "isConnectedHeartRateDeviceWear()=false, bluetoothIsEnabled=false");
            return false;
        }
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, "Suggestion_RunCourseRecommend");
        if (deviceInfo != null && deviceInfo.getDeviceConnectState() == 2 && (cux.e(deviceInfo.getProductType()) || cus.e().d(deviceInfo.getProductType()))) {
            LogUtil.a("Suggestion_RunCourseRecommend", "isConnectedHeartRateDeviceWear()=true, isSupportHeartRate Wear=true, realtime_hearrate");
            return true;
        }
        LogUtil.h("Suggestion_RunCourseRecommend", "isConnectedHeartRateDeviceWear()=false, failed");
        return false;
    }

    private boolean d() {
        if (BluetoothAdapter.getDefaultAdapter() != null) {
            return BluetoothAdapter.getDefaultAdapter().isEnabled();
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(RunCourseRecommendCallback runCourseRecommendCallback) {
        long j;
        if (!e(d())) {
            b(runCourseRecommendCallback);
            return;
        }
        fik a2 = eva.a(eva.a());
        if (a2 != null) {
            j = a2.c();
        } else {
            LogUtil.a("Suggestion_RunCourseRecommend", "sp time is null");
            j = 0;
        }
        c(runCourseRecommendCallback, j, a2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(RunCourseRecommendCallback runCourseRecommendCallback) {
        long currentTimeMillis = System.currentTimeMillis();
        fij c = eva.c(eva.e());
        long j = 0;
        if (c != null && c.h() != 0) {
            j = c.h();
            this.g = c;
        }
        LogUtil.a("Suggestion_RunCourseRecommend", "userInfoLastRefreshTime= ", Long.valueOf(j));
        if (currentTimeMillis <= 2592000000L + j) {
            c();
        } else {
            d(runCourseRecommendCallback, j);
        }
    }

    private void b(final RunCourseRecommendCallback runCourseRecommendCallback, fik fikVar) {
        LogUtil.a("Suggestion_RunCourseRecommend", "refresh time<7");
        if (koq.b(fikVar.b())) {
            eva.b(new RunCourseRecommendCallback() { // from class: eux.5
                @Override // com.huawei.health.suggestion.RunCourseRecommendCallback
                public void onSuccess(List<String> list) {
                }

                @Override // com.huawei.health.suggestion.RunCourseRecommendCallback
                public void onSuccess(String str) {
                    fik a2 = eva.a(str);
                    if (a2 != null && !koq.b(a2.b())) {
                        eux.this.d(runCourseRecommendCallback, a2.b());
                    } else {
                        LogUtil.a("Suggestion_RunCourseRecommend", "getCourseFromDb tempBean == null");
                        eux.this.a(runCourseRecommendCallback);
                    }
                }

                @Override // com.huawei.health.suggestion.RunCourseRecommendCallback
                public void onFailure(int i, String str) {
                    eux.this.a(runCourseRecommendCallback);
                }
            });
        } else {
            d(runCourseRecommendCallback, fikVar.b());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(RunCourseRecommendCallback runCourseRecommendCallback, List<String> list) {
        if (koq.b(list) || list.size() < 3) {
            LogUtil.h("Suggestion_RunCourseRecommend", "sp data error");
            h();
            this.e.add(runCourseRecommendCallback);
            return;
        }
        runCourseRecommendCallback.onSuccess(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        LogUtil.a("Suggestion_RunCourseRecommend", "startRequestData");
        f();
        j();
        i();
        a(true);
        d(true);
    }

    private void b(RunCourseRecommendCallback runCourseRecommendCallback) {
        LogUtil.a("Suggestion_RunCourseRecommend", "!isConnectedDevice,time<7");
        ArrayList arrayList = new ArrayList(3);
        arrayList.add("R008R");
        arrayList.add("R009R");
        arrayList.add("R010R");
        runCourseRecommendCallback.onSuccess(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        LogUtil.a("Suggestion_RunCourseRecommend", "userFitness RefreshTime <30");
        if (this.g != null) {
            a(false);
            d(false);
        } else {
            LogUtil.a("Suggestion_RunCourseRecommend", "userInfo == null");
            h();
        }
    }

    private void c(final RunCourseRecommendCallback runCourseRecommendCallback, long j, fik fikVar) {
        if (System.currentTimeMillis() <= 604800000 + j) {
            b(runCourseRecommendCallback, fikVar);
        } else if (j != 0) {
            LogUtil.a("Suggestion_RunCourseRecommend", "lastWeekRefreshTime != 0 ");
            a(runCourseRecommendCallback);
        } else {
            eva.b(new RunCourseRecommendCallback() { // from class: eux.10
                @Override // com.huawei.health.suggestion.RunCourseRecommendCallback
                public void onSuccess(List<String> list) {
                }

                @Override // com.huawei.health.suggestion.RunCourseRecommendCallback
                public void onSuccess(String str) {
                    LogUtil.a("Suggestion_RunCourseRecommend", "getCourseFromDb str");
                    fik a2 = eva.a(str);
                    if (a2 == null) {
                        eux.this.h();
                        LogUtil.a("Suggestion_RunCourseRecommend", "isRefreshCourse lastWeekRefreshTime == null");
                    } else if (System.currentTimeMillis() > a2.c() + 604800000) {
                        eux.this.a(runCourseRecommendCallback);
                        LogUtil.a("Suggestion_RunCourseRecommend", "isRefreshCourse isOverWeek");
                    } else if (koq.b(a2.b())) {
                        LogUtil.a("Suggestion_RunCourseRecommend", "RunCourseRecommendBean is null");
                        eux.this.h();
                    } else {
                        runCourseRecommendCallback.onSuccess(a2.b());
                        eva.e(a2.b(), a2.c());
                    }
                }

                @Override // com.huawei.health.suggestion.RunCourseRecommendCallback
                public void onFailure(int i, String str) {
                    LogUtil.a("Suggestion_RunCourseRecommend", "errorCode", Integer.valueOf(i), " errorInfo= ", str);
                    eux.this.a(runCourseRecommendCallback);
                }
            });
        }
    }

    private void d(RunCourseRecommendCallback runCourseRecommendCallback, long j) {
        if (j != 0) {
            h();
            LogUtil.a("Suggestion_RunCourseRecommend", "all refresh,userInfoLastRefreshTime>30");
        } else {
            eva.a(new RunCourseRecommendCallback() { // from class: eux.9
                @Override // com.huawei.health.suggestion.RunCourseRecommendCallback
                public void onSuccess(List<String> list) {
                }

                @Override // com.huawei.health.suggestion.RunCourseRecommendCallback
                public void onSuccess(String str) {
                    LogUtil.a("Suggestion_RunCourseRecommend", "refreshUserInfoAndRecommendCourse str");
                    eux.this.g = eva.c(str);
                    if (eux.this.g == null) {
                        eux.this.h();
                        LogUtil.a("Suggestion_RunCourseRecommend", "isRefreshCourse startRequestData");
                        return;
                    }
                    LogUtil.a("Suggestion_RunCourseRecommend", "isRefreshCourse mUserInfo.getRefreshMonthInfo()=", Long.valueOf(eux.this.g.h()));
                    if (System.currentTimeMillis() <= eux.this.g.h() + 2592000000L) {
                        eux.this.c();
                        eva.i(eva.c(eux.this.g));
                    } else {
                        LogUtil.a("Suggestion_RunCourseRecommend", "isRefreshUserInfo > 30");
                        eux.this.h();
                    }
                }

                @Override // com.huawei.health.suggestion.RunCourseRecommendCallback
                public void onFailure(int i, String str) {
                    LogUtil.a("Suggestion_RunCourseRecommend", "refreshUserInfoAndRecommendCourse errorCode", Integer.valueOf(i), " errorInfo= ", str);
                    eux.this.h();
                }
            });
        }
    }

    private boolean a() {
        return (this.b.size() == 0 || this.f12345a.size() == 0) ? false : true;
    }

    private boolean e() {
        LogUtil.h("Suggestion_RunCourseRecommend", "mUserBodyInfo:", Integer.valueOf(this.c.size()), " mVo2max:", Integer.valueOf(this.i.size()), " mDistanceData:", Integer.valueOf(this.d.size()), " mLastUseCourse:", Integer.valueOf(this.b.size()), " mLastWatchCourse:", Integer.valueOf(this.f12345a.size()));
        boolean z = (this.c.size() == 0 || this.i.size() == 0 || this.d.size() == 0) ? false : true;
        if (!z || this.b.size() == 0 || this.f12345a.size() == 0) {
            return z;
        }
        return true;
    }

    private void b(final boolean z, final List<RunCourseRecommendCallback> list) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: eux.8
            @Override // java.lang.Runnable
            public void run() {
                if (ffq.c() != 204) {
                    eux.this.d(z, (List<RunCourseRecommendCallback>) list, 0);
                } else {
                    LogUtil.a("Suggestion_RunCourseRecommend", "isOversea");
                    eux.this.a(z, (List<RunCourseRecommendCallback>) list, ffq.c());
                }
            }
        });
    }

    /* renamed from: eux$7, reason: invalid class name */
    class AnonymousClass7 extends UiCallback<List<Topic>> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f12348a;
        final /* synthetic */ int b;
        final /* synthetic */ List d;

        AnonymousClass7(List list, boolean z, int i) {
            this.d = list;
            this.f12348a = z;
            this.b = i;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.b("Suggestion_RunCourseRecommend", "getFitnessCourseTopicList()  errorCode:", Integer.valueOf(i), " error info ", str);
            eux.a((List<RunCourseRecommendCallback>) this.d, i, str);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onSuccess(final List<Topic> list) {
            if (koq.b(list)) {
                LogUtil.h("Suggestion_RunCourseRecommend", "initRunCourseTopic data null");
                eux.a((List<RunCourseRecommendCallback>) this.d, 0, "topic list is null");
                return;
            }
            ThreadPoolManager d = ThreadPoolManager.d();
            final boolean z = this.f12348a;
            final List list2 = this.d;
            final int i = this.b;
            d.execute(new Runnable() { // from class: euz
                @Override // java.lang.Runnable
                public final void run() {
                    eux.AnonymousClass7.this.b(list, z, list2, i);
                }
            });
        }

        /* synthetic */ void b(List list, boolean z, List list2, int i) {
            Iterator it = list.iterator();
            while (it.hasNext()) {
                Topic topic = (Topic) it.next();
                if (topic != null && "SF006".equals(topic.acquireSerialNum())) {
                    eux.this.a(z, (List<RunCourseRecommendCallback>) list2, topic.acquireId());
                    return;
                }
            }
            eux.this.d(z, (List<RunCourseRecommendCallback>) list2, i + 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z, List<RunCourseRecommendCallback> list, int i) {
        etr.b().getFitnessCourseTopicList(i, new AnonymousClass7(list, z, i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(final boolean z, final List<RunCourseRecommendCallback> list, int i) {
        final ArrayList arrayList = new ArrayList();
        etr.b().getWorkoutsByTopicId(0, i, new UiCallback<List<FitWorkout>>() { // from class: eux.6
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i2, String str) {
                LogUtil.b("Suggestion_RunCourseRecommend", str, "==Failed--errorcode:", Integer.valueOf(i2));
                eux.a((List<RunCourseRecommendCallback>) list, i2, str);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List<FitWorkout> list2) {
                List<FitWorkout> b = eux.this.b(list2);
                ArrayList arrayList2 = new ArrayList();
                for (FitWorkout fitWorkout : b) {
                    if (fitWorkout != null && fitWorkout.getType() == 2) {
                        fig figVar = new fig();
                        figVar.b(eva.d(fitWorkout.acquireDifficulty()));
                        if (fitWorkout.acquireMeasurementType() == 1) {
                            figVar.d().d(fitWorkout.acquireDuration());
                        } else {
                            LogUtil.h("Suggestion_RunCourseRecommend", "TYPE_MEASUREMENT_DISTANCE", Integer.valueOf(fitWorkout.acquireMeasurementType()));
                            figVar.d().d(0);
                        }
                        figVar.d().d(fitWorkout.acquireId());
                        figVar.d().c(fitWorkout.acquireId());
                        arrayList2.add(figVar);
                    }
                }
                LogUtil.a("Suggestion_RunCourseRecommend", "fitBeanList.size= ", Integer.valueOf(arrayList2.size()));
                if (arrayList2.size() == 0) {
                    eux.a((List<RunCourseRecommendCallback>) list, 0, "run course is 0");
                } else {
                    eux.c((List<RunCourseRecommendCallback>) list, (List<String>) eux.this.b((List<String>) arrayList, arrayList2, z));
                    eva.e((List<String>) arrayList, System.currentTimeMillis());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(List<RunCourseRecommendCallback> list, int i, String str) {
        if (koq.b(list)) {
            return;
        }
        for (RunCourseRecommendCallback runCourseRecommendCallback : list) {
            if (runCourseRecommendCallback != null) {
                runCourseRecommendCallback.onFailure(i, str);
            }
        }
        list.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(List<RunCourseRecommendCallback> list, List<String> list2) {
        if (koq.b(list)) {
            return;
        }
        for (RunCourseRecommendCallback runCourseRecommendCallback : list) {
            if (runCourseRecommendCallback != null) {
                runCourseRecommendCallback.onSuccess(list2);
            }
        }
        list.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<FitWorkout> b(List<FitWorkout> list) {
        ArrayList arrayList = new ArrayList(10);
        if (koq.b(list)) {
            LogUtil.h("Suggestion_RunCourseRecommend", "removeDupWorkouts() workouts is null");
            return arrayList;
        }
        for (FitWorkout fitWorkout : list) {
            if (!arrayList.contains(fitWorkout)) {
                arrayList.add(fitWorkout);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<String> b(List<String> list, List<fig> list2, boolean z) {
        fij fijVar;
        TreeMap treeMap = new TreeMap(new Comparator<Double>() { // from class: eux.11
            @Override // java.util.Comparator
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(Double d, Double d2) {
                return (int) (d2.doubleValue() - d.doubleValue());
            }
        });
        if (z) {
            fijVar = b();
            fijVar.e(eva.b(fijVar));
        } else {
            fijVar = this.g;
        }
        fij fijVar2 = fijVar;
        fin b = b(fijVar2);
        if (z) {
            String c = eva.c(list2.get(0).b());
            eva.i(c);
            eva.g(c);
        }
        a(z, fijVar2, b);
        for (fig figVar : list2) {
            if (figVar != null) {
                figVar.a(fijVar2);
                figVar.e(b);
                double b2 = eva.b(figVar);
                if (treeMap.containsKey(Double.valueOf(b2))) {
                    c(treeMap, b2, figVar, fijVar2.i());
                }
                treeMap.put(Double.valueOf(b2), c(figVar));
            }
        }
        LogUtil.a("Suggestion_RunCourseRecommend", "tempResult recommendInfo = ", Integer.valueOf(treeMap.size()));
        int i = 0;
        for (String str : treeMap.values()) {
            if (str != null) {
                i++;
                if (i > 3) {
                    break;
                }
                list.add(str.split("_")[0]);
            }
        }
        LogUtil.a("Suggestion_RunCourseRecommend", "courseIdList.size= ", Integer.valueOf(list.size()));
        return list;
    }

    private String c(fig figVar) {
        StringBuilder sb = new StringBuilder(7);
        sb.append(figVar.d().d());
        sb.append("_");
        sb.append(figVar.d().e());
        sb.append("_");
        sb.append(figVar.e());
        sb.append("_");
        sb.append(figVar.d().a());
        return sb.toString();
    }

    private void c(Map<Double, String> map, double d, fig figVar, int i) {
        String[] split = map.get(Double.valueOf(d)).split("_");
        if (split.length != 4) {
            map.put(Double.valueOf(d - 0.01d), map.get(Double.valueOf(d)));
            return;
        }
        String num = Integer.toString(figVar.c().d());
        String str = split[1];
        String e = figVar.d().e();
        double e2 = e(num, str) + d;
        if (!d(e2, e(num, e) + d)) {
            map.put(Double.valueOf(e2), map.get(Double.valueOf(d)));
            return;
        }
        int a2 = a(split[2]);
        int e3 = figVar.e();
        double d2 = d(i, a2) + d;
        if (!d(d2, d(i, e3) + d)) {
            map.put(Double.valueOf(d2), map.get(Double.valueOf(d)));
        } else {
            map.put(Double.valueOf(e(figVar.b().j(), a(split[3]), figVar.d().a()) + d), map.get(Double.valueOf(d)));
        }
    }

    private int a(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            LogUtil.b("Suggestion_RunCourseRecommend", "NumberFormatException");
            return 0;
        }
    }

    private boolean d(double d, double d2) {
        return new BigDecimal(d).compareTo(new BigDecimal(d2)) == 0;
    }

    private double e(String str, String str2) {
        return str.equals(str2) ? 0.01d : -0.01d;
    }

    private double e(long j, int i, int i2) {
        return Math.abs(j - ((long) i)) < Math.abs(j - ((long) i2)) ? 0.01d : -0.01d;
    }

    private void a(boolean z, fij fijVar, fin finVar) {
        if (z) {
            eva.a(fijVar.i(), finVar.d(), fijVar.j());
        } else {
            eva.a(finVar.d());
        }
    }

    private fin b(fij fijVar) {
        fin finVar = new fin();
        if (this.b.size() == 2 && this.b.get(0).intValue() != 0) {
            finVar.d(this.b.get(0).intValue());
            finVar.b(this.b.get(1).intValue());
        } else if (this.f12345a.size() == 2 && this.f12345a.get(0).intValue() != 0) {
            finVar.d(this.f12345a.get(0).intValue());
            finVar.b(this.f12345a.get(1).intValue());
        } else {
            finVar.d(0);
            finVar.b(0);
            LogUtil.h("Suggestion_RunCourseRecommend", "mLastUseCourse.size() != 2");
        }
        int[] e = eva.e(fijVar, fijVar.i(), finVar);
        finVar.d(e[0]);
        finVar.b(e[1]);
        return finVar;
    }

    private fij b() {
        fij fijVar = new fij();
        if (this.c.size() == 3) {
            fijVar.c(this.c.get(0).intValue());
            fijVar.d(this.c.get(1).intValue());
            fijVar.d(this.c.get(2).intValue());
        } else {
            LogUtil.h("Suggestion_RunCourseRecommend", "mUserBodyInfo.size() != 3");
        }
        if (this.i.size() == 2) {
            if (this.i.get(0).intValue() == 0) {
                fijVar.d(false);
            } else {
                fijVar.d(true);
            }
            fijVar.b(this.i.get(1).intValue());
        } else {
            LogUtil.h("Suggestion_RunCourseRecommend", "mVo2max.size() != 2");
        }
        if (this.d.size() == 3) {
            fijVar.a(this.d.get(0).intValue());
            fijVar.e(this.d.get(1).longValue());
            fijVar.a(this.d.get(2).longValue());
        } else {
            LogUtil.h("Suggestion_RunCourseRecommend", "mDistanceData.size() != 3");
        }
        return fijVar;
    }

    private void f() {
        fij fijVar = this.g;
        if (fijVar != null) {
            this.c.add(Integer.valueOf(fijVar.a()));
            this.c.add(Integer.valueOf(this.g.c()));
            this.c.add(Integer.valueOf((int) this.g.d()));
        }
        UserProfileMgrApi userProfileMgrApi = (UserProfileMgrApi) Services.a("HWUserProfileMgr", UserProfileMgrApi.class);
        if (userProfileMgrApi == null) {
            LogUtil.h("Suggestion_RunCourseRecommend", "startRequestUserInfo : userProfileMgrApi is null.");
            return;
        }
        UserInfomation userInfo = userProfileMgrApi.getUserInfo();
        if (userInfo != null) {
            float weightOrDefaultValue = userInfo.getWeightOrDefaultValue();
            int ageOrDefaultValue = userInfo.getAgeOrDefaultValue();
            int genderOrDefaultValue = userInfo.getGenderOrDefaultValue();
            int heightOrDefaultValue = userInfo.getHeightOrDefaultValue();
            if (heightOrDefaultValue != 0) {
                c(ageOrDefaultValue, genderOrDefaultValue, (int) (weightOrDefaultValue / Math.pow(heightOrDefaultValue / 100.0d, 2.0d)));
                return;
            } else {
                c(0, 0, 0);
                return;
            }
        }
        c(0, 0, 0);
    }

    private void j() {
        euu euuVar = new euu(new RunCourseRecommendCallback() { // from class: eux.13
            @Override // com.huawei.health.suggestion.RunCourseRecommendCallback
            public void onSuccess(List<String> list) {
            }

            @Override // com.huawei.health.suggestion.RunCourseRecommendCallback
            public void onSuccess(String str) {
                fij c = eva.c(str);
                if (c == null) {
                    eux.this.e(0, 0);
                } else {
                    eux.this.e(1, c.f());
                }
            }

            @Override // com.huawei.health.suggestion.RunCourseRecommendCallback
            public void onFailure(int i, String str) {
                eux.this.e(0, 0);
            }
        });
        HealthDataMgrApi healthDataMgrApi = (HealthDataMgrApi) Services.a("HWHealthDataMgr", HealthDataMgrApi.class);
        if (healthDataMgrApi == null) {
            LogUtil.h("Suggestion_RunCourseRecommend", "getLastVo2max() : healthDataMgrApi is null.");
        } else {
            healthDataMgrApi.getLastVo2max(euuVar);
        }
    }

    private void i() {
        eut eutVar = new eut(new RunCourseRecommendCallback() { // from class: eux.4
            @Override // com.huawei.health.suggestion.RunCourseRecommendCallback
            public void onSuccess(List<String> list) {
            }

            @Override // com.huawei.health.suggestion.RunCourseRecommendCallback
            public void onSuccess(String str) {
                fij c = eva.c(str);
                if (c != null) {
                    eux.this.b(c.e(), c.b(), c.j());
                } else {
                    eux.this.b(0L, 0L, 0L);
                }
            }

            @Override // com.huawei.health.suggestion.RunCourseRecommendCallback
            public void onFailure(int i, String str) {
                eux.this.b(0L, 0L, 0L);
            }
        });
        HealthDataMgrApi healthDataMgrApi = (HealthDataMgrApi) Services.a("HWHealthDataMgr", HealthDataMgrApi.class);
        if (healthDataMgrApi == null) {
            LogUtil.h("Suggestion_RunCourseRecommend", "requestTrackSimplifyData : healthDataMgrApi is null.");
            return;
        }
        healthDataMgrApi.requestTrackSimplifyData(System.currentTimeMillis() - 2592000000L, System.currentTimeMillis(), 258, false, eutVar);
    }

    private void c(final boolean z) {
        eva.b("last_use_run_course", new RunCourseRecommendCallback() { // from class: eux.3
            @Override // com.huawei.health.suggestion.RunCourseRecommendCallback
            public void onSuccess(List<String> list) {
            }

            @Override // com.huawei.health.suggestion.RunCourseRecommendCallback
            public void onSuccess(String str) {
                fin b = eva.b(str);
                if (b == null) {
                    eux.this.b(0, 0, z);
                    return;
                }
                eux.this.b(b.d(), b.c(), z);
                LogUtil.a("Suggestion_RunCourseRecommend", "update mUsePurpose onSuccess");
                eva.e("last_use_run_course", Integer.toString(b.d()), System.currentTimeMillis(), b);
            }

            @Override // com.huawei.health.suggestion.RunCourseRecommendCallback
            public void onFailure(int i, String str) {
                LogUtil.a("Suggestion_RunCourseRecommend", "update mUsePurpose onFailure");
                eux.this.b(0, 0, z);
            }
        });
    }

    private void b(final boolean z) {
        eva.b("last_watch_run_course", new RunCourseRecommendCallback() { // from class: eux.2
            @Override // com.huawei.health.suggestion.RunCourseRecommendCallback
            public void onSuccess(List<String> list) {
            }

            @Override // com.huawei.health.suggestion.RunCourseRecommendCallback
            public void onSuccess(String str) {
                LogUtil.a("Suggestion_RunCourseRecommend", "update mWatchPurpose onSuccess");
                fin b = eva.b(str);
                if (b == null) {
                    eux.this.a(0, 0, z);
                    return;
                }
                eux.this.a(b.d(), b.c(), z);
                int d = b.d();
                eva.e("last_watch_run_course", String.valueOf(d), System.currentTimeMillis(), b);
            }

            @Override // com.huawei.health.suggestion.RunCourseRecommendCallback
            public void onFailure(int i, String str) {
                LogUtil.a("Suggestion_RunCourseRecommend", "update mWatchPurpose onFailure");
                eux.this.a(0, 0, z);
            }
        });
    }

    private void a(boolean z) {
        fin d = eva.d("last_use_run_course");
        if (d == null) {
            LogUtil.a("Suggestion_RunCourseRecommend", "spArray = null");
            c(z);
            return;
        }
        int d2 = d.d();
        int i = 0;
        if (c(d.a())) {
            b(0, 0, z);
            return;
        }
        if (d2 <= 0 || d2 >= 5) {
            d2 = 0;
        } else {
            LogUtil.a("Suggestion_RunCourseRecommend", "lastPurpose = ", Integer.valueOf(d2));
        }
        long e = d.e();
        int c = d.c();
        if (c(e)) {
            c = d2;
        }
        if (c > 0 && c < 5) {
            LogUtil.a("Suggestion_RunCourseRecommend", "penultimatePurpose = ", Integer.valueOf(c));
            i = c;
        }
        b(d2, i, z);
    }

    private boolean c(long j) {
        return System.currentTimeMillis() > j + 2592000000L;
    }

    private void d(boolean z) {
        fin d = eva.d("last_watch_run_course");
        if (d == null) {
            LogUtil.a("Suggestion_RunCourseRecommend", "startRequestIsUserWatched spArray =null ");
            b(z);
            return;
        }
        int d2 = d.d();
        int i = 0;
        if (c(d.a())) {
            a(0, 0, z);
            return;
        }
        if (d2 <= 0 || d2 >= 5) {
            d2 = 0;
        } else {
            LogUtil.a("Suggestion_RunCourseRecommend", "lastPurpose = ", Integer.valueOf(d2));
        }
        long e = d.e();
        int c = d.c();
        if (c(e)) {
            c = d2;
        }
        if (c > 0 && c < 5) {
            LogUtil.a("Suggestion_RunCourseRecommend", "penultimatePurpose = ", Integer.valueOf(c));
            i = c;
        }
        a(d2, i, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, int i2, boolean z) {
        this.b.add(Integer.valueOf(i));
        this.b.add(Integer.valueOf(i2));
        if (z) {
            e(100);
        } else {
            e(101);
        }
        ReleaseLogUtil.e("Suggestion_RunCourseRecommend", "use info=");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2, boolean z) {
        this.f12345a.add(Integer.valueOf(i));
        this.f12345a.add(Integer.valueOf(i2));
        if (z) {
            e(100);
        } else {
            e(101);
        }
        ReleaseLogUtil.e("Suggestion_RunCourseRecommend", "watch info=");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(long j, long j2, long j3) {
        this.d.add(Long.valueOf(j));
        this.d.add(Long.valueOf(j2));
        this.d.add(Long.valueOf(j3));
        e(100);
        ReleaseLogUtil.e("Suggestion_RunCourseRecommend", "distance");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, int i2) {
        this.i.add(Integer.valueOf(i));
        this.i.add(Integer.valueOf(i2));
        e(100);
        ReleaseLogUtil.e("Suggestion_RunCourseRecommend", "vo2max info=", Integer.valueOf(i));
    }

    private void c(int i, int i2, int i3) {
        this.c.add(Integer.valueOf(i));
        this.c.add(Integer.valueOf(i2));
        this.c.add(Integer.valueOf(i3));
        e(100);
    }
}
