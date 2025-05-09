package defpackage;

import android.content.Context;
import android.os.Looper;
import android.text.TextUtils;
import com.google.protobuf.DescriptorProtos;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.basefitnessadvice.model.WorkoutListBean;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.course.api.CourseApi;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.ContentVideo;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import com.huawei.hwsmartinteractmgr.data.SmartMsgDbObject;
import com.huawei.hwsmartinteractmgr.smarter.BaseSmarter;
import com.huawei.hwsmartinteractmgr.smarter.BloodPressureSmarter;
import com.huawei.hwsmartinteractmgr.smarter.WeightSmarterHelper;
import com.huawei.hwsmartinteractmgr.userlabel.LabelObserver;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.pluginfitnessadvice.Workout;
import com.huawei.ui.commonui.base.CommonUiBaseResponse;
import health.compact.a.HiDateUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class kwc extends BaseSmarter implements LabelObserver {

    /* renamed from: a, reason: collision with root package name */
    private static volatile kwc f14649a;
    private static final Object d = new Object();
    private int b;
    private kvs c;
    private WeightSmarterHelper i;
    private int j;

    private kwc(Context context) {
        super(context);
        this.b = 0;
        this.j = 5;
        this.e = context.getApplicationContext();
        this.c = kvs.b(this.e);
        this.i = new WeightSmarterHelper(this.e);
    }

    public static kwc e(Context context) {
        if (f14649a == null) {
            synchronized (d) {
                if (f14649a == null) {
                    if (context == null) {
                        f14649a = new kwc(BaseApplication.getContext());
                    } else {
                        f14649a = new kwc(context);
                    }
                }
            }
        }
        return f14649a;
    }

    @Override // com.huawei.hwsmartinteractmgr.smarter.BaseSmarter
    public void d() {
        LogUtil.a("SMART_WeightSmarter", "startTimerCheck");
        super.d();
        b();
        a();
        b(false);
        e();
    }

    public void a() {
        boolean c = kwn.c(30001, "ai-weight-005");
        LogUtil.a("SMART_WeightSmarter", "setOrDeleteMeasureMsg isRuleOpen = ", Boolean.valueOf(c));
        String b = SharedPreferenceManager.b(this.e, Integer.toString(10021), "ask_user_measure_weight");
        if (c && !"1".equals(b)) {
            if (WeightSmarterHelper.b(this.e)) {
                String b2 = kwn.b(30001, "ai-weight-005", "recently_num_days_no_data");
                String b3 = kwn.b(30001, "ai-weight-005", "recommended_time");
                String d2 = BloodPressureSmarter.d(b3);
                LogUtil.a("SMART_WeightSmarter", "setOrDeleteMeasureMsg showTime = ", d2, "dayStr = ", b2, "recommendTime = ", b3);
                try {
                    this.j = Integer.parseInt(b2);
                } catch (NumberFormatException e) {
                    LogUtil.b("SMART_WeightSmarter", "setOrDeleteMeasureMsg NumberFormatException exception = ", e.getMessage());
                }
                e(d2);
                return;
            }
            kwn.c(this.e, 20005, 3);
            return;
        }
        kwn.c(this.e, 20005, 3);
    }

    private void e(final String str) {
        final long currentTimeMillis = System.currentTimeMillis();
        e(this.e, new IBaseResponseCallback() { // from class: kwc.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (obj instanceof List) {
                    List list = (List) obj;
                    if (list.isEmpty()) {
                        return;
                    }
                    LogUtil.a("SMART_WeightSmarter", " setOrDeleteMeasureMsg,size = ", Integer.valueOf(list.size()));
                    long startTime = ((HiHealthData) list.get(0)).getStartTime();
                    kwc.this.b = (int) ((currentTimeMillis - startTime) / 86400000);
                    LogUtil.a("SMART_WeightSmarter", " setOrDeleteMeasureMsg,intervalDay = ", Integer.valueOf(kwc.this.b));
                    if (kwc.this.j <= kwc.this.b) {
                        kwc kwcVar = kwc.this;
                        kwcVar.c(kwcVar.b, str, kwc.this.j);
                    } else {
                        LogUtil.a("SMART_WeightSmarter", " setOrDeleteMeasureMsg, MSG_STATUS_EXPIRED ");
                        kwn.c(kwc.this.e, 20005, 3);
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i, String str, int i2) {
        LogUtil.a("SMART_WeightSmarter", "setMeasureMsg ");
        SmartMsgDbObject smartMsgDbObject = new SmartMsgDbObject();
        smartMsgDbObject.setMsgType(20005);
        smartMsgDbObject.setMsgSrc(2);
        d(smartMsgDbObject, str, i, SmartMsgConstant.SHOW_METHOD_SMART_CARD_HI_BOARD);
        smartMsgDbObject.setMessagePriority(kwn.d(30001, "ai-weight-005"));
        SmartMsgDbObject a2 = this.c.a(20005);
        if (a2 == null) {
            this.c.a(smartMsgDbObject);
            return;
        }
        long updateTime = a2.getUpdateTime();
        LogUtil.a("SMART_WeightSmarter", "setMeasureMsg createTime = ", new Date(updateTime));
        if (System.currentTimeMillis() - updateTime > i2 * 86400000) {
            LogUtil.a("SMART_WeightSmarter", "setMeasureMsg update");
            this.c.d(20005);
            this.c.a(smartMsgDbObject);
        }
    }

    public void e(Context context, final IBaseResponseCallback iBaseResponseCallback) {
        kot.a().b(context, 0L, System.currentTimeMillis(), 2, new IBaseResponseCallback() { // from class: kwc.5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                if (iBaseResponseCallback2 == null) {
                    return;
                }
                if (!(obj instanceof List)) {
                    iBaseResponseCallback2.d(-1, obj);
                    return;
                }
                List list = (List) obj;
                ArrayList arrayList = new ArrayList(2);
                if (list.isEmpty()) {
                    iBaseResponseCallback.d(-1, arrayList);
                    return;
                }
                arrayList.add((HiHealthData) list.get(0));
                if (list.size() > 1) {
                    arrayList.add((HiHealthData) list.get(1));
                }
                iBaseResponseCallback.d(0, arrayList);
            }
        });
    }

    public void d(Context context, final IBaseResponseCallback iBaseResponseCallback) {
        kot.a().b(context, 0L, System.currentTimeMillis(), DescriptorProtos.Edition.EDITION_99999_TEST_ONLY_VALUE, new IBaseResponseCallback() { // from class: kwc.6
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i, Object obj) {
                if (iBaseResponseCallback == null) {
                    LogUtil.a("SMART_WeightSmarter", "getLatestMultiUserWeightData callback = null");
                    return;
                }
                ArrayList arrayList = new ArrayList(2);
                if (!(obj instanceof List)) {
                    iBaseResponseCallback.d(-1, arrayList);
                    return;
                }
                List list = (List) obj;
                if (!list.isEmpty()) {
                    arrayList.add((HiHealthData) list.get(0));
                }
                iBaseResponseCallback.d(0, arrayList);
            }
        });
    }

    private void c(final CommonUiBaseResponse commonUiBaseResponse) {
        boolean c = kwn.c(30001, "ai-weight-001");
        String b = SharedPreferenceManager.b(this.e, Integer.toString(10021), "ask_user_set_weight_target");
        LogUtil.a("SMART_WeightSmarter", "isNeedSetWeightGoal isRuleOpen = ", Boolean.valueOf(c));
        if (c && !"1".equals(b)) {
            kor.a().b(this.e, new IBaseResponseCallback() { // from class: kwc.7
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("SMART_WeightSmarter", "isNeedSetWeightGoal getWeightGoal errCode = ", Integer.valueOf(i));
                    if (i == 100001) {
                        kwc.this.i.e("ai-weight-001", commonUiBaseResponse);
                        return;
                    }
                    CommonUiBaseResponse commonUiBaseResponse2 = commonUiBaseResponse;
                    if (commonUiBaseResponse2 != null) {
                        commonUiBaseResponse2.onResponse(100001, null);
                    }
                }
            });
            return;
        }
        LogUtil.a("SMART_WeightSmarter", "isNeedSetWeightGoal rule close");
        if (commonUiBaseResponse != null) {
            commonUiBaseResponse.onResponse(100001, null);
        }
    }

    public void b() {
        c(new CommonUiBaseResponse() { // from class: kwc.9
            @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
            public void onResponse(int i, Object obj) {
                LogUtil.a("SMART_WeightSmarter", "setOrDeleteSetWeightGoalMsg errCode = ", Integer.valueOf(i));
                if (i == 100001) {
                    kwn.c(kwc.this.e, 20000, 3);
                } else if (i == 0 && kwc.this.c.a(20000) == null) {
                    kwc.this.c();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        SmartMsgDbObject smartMsgDbObject = new SmartMsgDbObject();
        smartMsgDbObject.setMsgType(20000);
        smartMsgDbObject.setMsgSrc(2);
        smartMsgDbObject.setMsgContentType(2);
        smartMsgDbObject.setMsgContent(new JSONObject().toString());
        smartMsgDbObject.setShowTime(SmartMsgConstant.DEFAULT_SHOW_TIME);
        smartMsgDbObject.setShowMethod(SmartMsgConstant.SHOW_METHOD_SMART_CARD_HI_BOARD);
        smartMsgDbObject.setStatus(1);
        smartMsgDbObject.setMessagePriority(kwn.d(30001, "ai-weight-001"));
        this.c.d(20000);
        LogUtil.a("SMART_WeightSmarter", "setWeightGoalMsg isInserted = ", Boolean.valueOf(this.c.a(smartMsgDbObject)));
    }

    public void e() {
        LogUtil.a("SMART_WeightSmarter", "setOrDeleteSuggestVideo ");
        this.i.d(new CommonUiBaseResponse() { // from class: kwc.8
            @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
            public void onResponse(int i, Object obj) {
                LogUtil.a("SMART_WeightSmarter", "setOrDeleteSuggestVideo errCode = ", Integer.valueOf(i));
                if (i == 0) {
                    kwc.this.e(new IBaseResponseCallback() { // from class: kwc.8.2
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public void d(int i2, Object obj2) {
                            if (i2 == 0 && (obj2 instanceof FitWorkout)) {
                                kwc.this.b((FitWorkout) obj2);
                            }
                        }
                    });
                } else if (i == 100001) {
                    kwn.c(kwc.this.e, 20006, 3);
                } else {
                    LogUtil.a("SMART_WeightSmarter", "setOrDeleteSuggestVideo other errCode ");
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("SMART_WeightSmarter", "judgeFitVideoUpdate");
        CourseApi courseApi = (CourseApi) Services.a("CoursePlanService", CourseApi.class);
        if (courseApi == null) {
            LogUtil.h("SMART_WeightSmarter", "judgeFitVideoUpdate : courseApi is null.");
        } else {
            b(iBaseResponseCallback, courseApi, new WorkoutListBean(0, 1, -1, new Integer[]{1}, (Integer[]) null, (Integer[]) null, (Integer[]) null, 0));
        }
    }

    private void b(final IBaseResponseCallback iBaseResponseCallback, CourseApi courseApi, WorkoutListBean workoutListBean) {
        courseApi.getRecommendCourses("FITNESS_COURSE", workoutListBean, new UiCallback<List<Workout>>() { // from class: kwc.10
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.a("SMART_WeightSmarter", "suggestWeightVideo errorCode = ", Integer.valueOf(i), "errorInfo = ", str);
                IBaseResponseCallback iBaseResponseCallback2 = iBaseResponseCallback;
                if (iBaseResponseCallback2 != null) {
                    iBaseResponseCallback2.d(100001, null);
                }
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(List<Workout> list) {
                if (iBaseResponseCallback == null) {
                    return;
                }
                if (!koq.b(list)) {
                    List<FitWorkout> a2 = mod.a(list);
                    if (koq.b(a2, 0)) {
                        LogUtil.b("SMART_WeightSmarter", "fitWorkouts out of bound, return");
                        return;
                    }
                    FitWorkout fitWorkout = a2.get(0);
                    String acquireId = fitWorkout.acquireId();
                    LogUtil.a("SMART_WeightSmarter", "judgeSuggestWeightVideo id = ", acquireId);
                    SmartMsgDbObject a3 = kwc.this.c.a(20006);
                    LogUtil.a("SMART_WeightSmarter", "isUpdate getName= ", fitWorkout.acquireName());
                    if (a3 == null) {
                        iBaseResponseCallback.d(0, fitWorkout);
                        return;
                    }
                    FitWorkout fitWorkout2 = ((ContentVideo) HiJsonUtil.e(a3.getMsgContent(), ContentVideo.class)).getFitWorkout();
                    if (fitWorkout2 == null) {
                        kvs.b(kwc.this.e).d(20006);
                        iBaseResponseCallback.d(100001, null);
                        return;
                    } else if (acquireId != null && acquireId.equals(fitWorkout2.acquireId())) {
                        iBaseResponseCallback.d(100001, null);
                        return;
                    } else {
                        iBaseResponseCallback.d(0, fitWorkout);
                        return;
                    }
                }
                iBaseResponseCallback.d(100001, null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(FitWorkout fitWorkout) {
        SmartMsgDbObject smartMsgDbObject = new SmartMsgDbObject();
        smartMsgDbObject.setMsgType(20006);
        smartMsgDbObject.setMsgSrc(2);
        smartMsgDbObject.setMsgContentType(2);
        smartMsgDbObject.setMsgContent(HiJsonUtil.d(new ContentVideo(fitWorkout), ContentVideo.class));
        smartMsgDbObject.setShowTime(SmartMsgConstant.DEFAULT_SHOW_TIME);
        smartMsgDbObject.setShowMethod(SmartMsgConstant.SHOW_METHOD_SMART_CARD_HI_BOARD);
        smartMsgDbObject.setStatus(1);
        smartMsgDbObject.setMessagePriority(kwn.d(30001, "ai-weight-006"));
        this.c.d(20006);
        LogUtil.a("SMART_WeightSmarter", "setSuggestVideoMsg isInserted = ", Boolean.valueOf(this.c.a(smartMsgDbObject)));
    }

    private void b(final CommonUiBaseResponse commonUiBaseResponse) {
        boolean c = kwn.c(30001, "ai-weight-007");
        LogUtil.a("SMART_WeightSmarter", "judgeSuggestWeightWeekly isRuleOpen = ", Boolean.valueOf(c));
        if (c) {
            long[] p = HiDateUtil.p(System.currentTimeMillis() - 604800000);
            kot.a().b(this.e, p[0], p[1], 0, new IBaseResponseCallback() { // from class: kwc.12
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (!(obj instanceof List)) {
                        commonUiBaseResponse.onResponse(100001, null);
                        return;
                    }
                    final List list = (List) obj;
                    if (!list.isEmpty()) {
                        kwc.this.i.e("ai-weight-007", new CommonUiBaseResponse() { // from class: kwc.12.1
                            @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
                            public void onResponse(int i2, Object obj2) {
                                if (i2 == 0) {
                                    if (koq.b(list, 0)) {
                                        LogUtil.b("SMART_WeightSmarter", "dataList out of bound, return");
                                        return;
                                    } else {
                                        commonUiBaseResponse.onResponse(0, list.get(0));
                                        return;
                                    }
                                }
                                commonUiBaseResponse.onResponse(100001, null);
                            }
                        });
                    } else {
                        LogUtil.a("SMART_WeightSmarter", "judgeSuggestWeightWeekly,getWeightData = null ");
                        commonUiBaseResponse.onResponse(100001, null);
                    }
                }
            });
        } else {
            commonUiBaseResponse.onResponse(100001, null);
        }
    }

    public void b(final boolean z) {
        LogUtil.a("SMART_WeightSmarter", "setOrDeleteWeightWeekly isParamChanged = ", Boolean.valueOf(z));
        b(new CommonUiBaseResponse() { // from class: kwc.11
            @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
            public void onResponse(int i, Object obj) {
                LogUtil.a("SMART_WeightSmarter", "setOrDeleteWeightWeekly errCode = ", Integer.valueOf(i));
                if (!(obj instanceof HiHealthData)) {
                    LogUtil.a("SMART_WeightSmarter", "setOrDeleteWeightWeekly objData is null");
                    return;
                }
                if (i == 0) {
                    kwc.this.d((HiHealthData) obj);
                } else if (z) {
                    kwn.c(kwc.this.e, 20007, 3);
                    SharedPreferenceManager.e(kwc.this.e, Integer.toString(10006), "health_weight_weekly_start_time_key", "", new StorageParams());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(HiHealthData hiHealthData) {
        final long[] p = HiDateUtil.p(hiHealthData.getStartTime());
        LogUtil.a("SMART_WeightSmarter", "setOrDeleteWeightWeekly,timePeriod = ", Arrays.toString(p));
        this.i.e(p, new CommonUiBaseResponse() { // from class: kwc.4
            @Override // com.huawei.ui.commonui.base.CommonUiBaseResponse
            public void onResponse(int i, Object obj) {
                if (obj == null) {
                    return;
                }
                final double[] dArr = (double[]) obj;
                if (dArr.length == 2) {
                    kor.a().b(kwc.this.e, new IBaseResponseCallback() { // from class: kwc.4.3
                        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                        /* renamed from: onResponse */
                        public void d(int i2, Object obj2) {
                            if (!(obj2 instanceof Double)) {
                                LogUtil.h("SMART_WeightSmarter", "objData is not Double");
                            } else {
                                kwc.this.i.b(p, dArr, ((Double) obj2).doubleValue());
                            }
                        }
                    });
                } else {
                    LogUtil.a("SMART_WeightSmarter", "setOrDeleteWeightWeekly,this week has no data");
                }
            }
        });
    }

    public void d(final double d2, final long j, boolean z) {
        double d3;
        if (z || Looper.myLooper() == Looper.getMainLooper()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: kwc.2
                @Override // java.lang.Runnable
                public void run() {
                    kwc.this.d(d2, j, false);
                }
            });
            return;
        }
        LogUtil.a("SMART_WeightSmarter", "saveAndUpToCloud");
        String b = SharedPreferenceManager.b(this.e, Integer.toString(10006), "health_last_weight");
        LogUtil.c("SMART_WeightSmarter", "saveAndUpToCloud laststr=", b);
        if (TextUtils.isEmpty(b)) {
            this.i.a(d2);
            this.i.e(d2, j);
        } else {
            try {
                d3 = Double.parseDouble(b);
            } catch (NumberFormatException e) {
                LogUtil.b("SMART_WeightSmarter", "NumberFormatException = ", e.getMessage());
                d3 = 0.0d;
            }
            if (Math.abs(d2 - d3) > 0.05d) {
                this.i.e(d2, j);
                this.i.a(d2);
            }
        }
        LogUtil.a("SMART_WeightSmarter", "saveAndUpToCloud result = ", Integer.valueOf(SharedPreferenceManager.e(this.e, Integer.toString(10006), "health_last_weight", d2 > 0.0d ? String.valueOf(d2) : "", new StorageParams(1))));
    }

    public void c(Context context, final double d2) {
        if (context == null) {
            LogUtil.a("SMART_WeightSmarter", "setWeightFlag context == null");
        } else {
            kot.a().b(context.getApplicationContext(), 0L, System.currentTimeMillis(), 1, new IBaseResponseCallback() { // from class: kwc.3
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    if (obj instanceof List) {
                        List list = (List) obj;
                        if (list.isEmpty()) {
                            return;
                        }
                        if (d2 > ((HiHealthData) list.get(0)).getDouble("bodyWeight")) {
                            SharedPreferenceManager.e(kwc.this.e, Integer.toString(10006), "health_weight_flag", "weight_gain", new StorageParams());
                        } else {
                            SharedPreferenceManager.e(kwc.this.e, Integer.toString(10006), "health_weight_flag", "weight_loss", new StorageParams());
                        }
                    }
                }
            });
        }
    }

    @Override // com.huawei.hwsmartinteractmgr.userlabel.LabelObserver
    public void onChange(Map<Integer, List<String>> map) {
        if (map == null) {
            return;
        }
        List<String> list = map.get(1);
        List<String> list2 = map.get(2);
        LogUtil.a("SMART_WeightSmarter", "onChange");
        boolean[] e = WeightSmarterHelper.e(list, list2);
        int length = e.length;
        if (length > 0 && e[0]) {
            b();
            e();
            b(true);
        }
        if (length <= 1 || !e[1]) {
            return;
        }
        e();
    }
}
