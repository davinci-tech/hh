package defpackage;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Pair;
import androidx.webkit.ProxyConfig;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.courseplanservice.api.RecordApi;
import com.huawei.health.pluginhealthzone.FamilyHealthZoneApi;
import com.huawei.health.sport.utils.SportSupportUtil;
import com.huawei.health.suggestion.ResultCallback;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hmf.tasks.Tasks;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginachievement.PluginAchieveAdapter;
import com.huawei.pluginachievement.impl.AchieveCallback;
import com.huawei.pluginachievement.manager.db.IAchieveDBMgr;
import com.huawei.pluginachievement.manager.model.AchieveInfo;
import com.huawei.pluginachievement.manager.model.KakaConstants;
import com.huawei.pluginachievement.manager.model.KakaLineRecord;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import com.huawei.pluginachievement.manager.model.SingleDayRecord;
import com.huawei.pluginachievement.manager.model.TrackData;
import com.huawei.skinner.internal.OnRegisterSkinAttrsListener;
import health.compact.a.CommonUtil;
import health.compact.a.GRSManager;
import health.compact.a.HiDateUtil;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class mer {

    /* renamed from: a, reason: collision with root package name */
    private static long f14924a = 0;
    private static volatile meh b = null;
    private static volatile mes c = null;
    private static boolean d = false;
    private static volatile mer e;
    private Context g;

    private mer(Context context) {
        this.g = context.getApplicationContext();
    }

    public static mer b(Context context) {
        if (context == null) {
            context = BaseApplication.getContext();
        }
        b = meh.c(context.getApplicationContext());
        c = mes.c(context.getApplicationContext());
        if (e == null) {
            synchronized (mer.class) {
                if (e == null) {
                    e = new mer(context);
                }
            }
        }
        return e;
    }

    public void c(ArrayList<TrackData> arrayList) {
        if (koq.b(arrayList)) {
            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "dealTrackData list is empty");
            return;
        }
        HashMap hashMap = new HashMap(2);
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "dealTrackData trackData size = ", Integer.valueOf(arrayList.size()));
        long t = HiDateUtil.t(System.currentTimeMillis());
        long f = HiDateUtil.f(System.currentTimeMillis());
        Iterator<TrackData> it = arrayList.iterator();
        TrackData trackData = null;
        int i = 0;
        boolean z = false;
        boolean z2 = false;
        while (it.hasNext()) {
            TrackData next = it.next();
            if (next != null && mfg.e(next.acquireType())) {
                long acquireEndTime = next.acquireEndTime();
                if (acquireEndTime >= t && acquireEndTime <= f) {
                    if (!z && mfg.a(next.acquireType())) {
                        c(String.valueOf(20003), hashMap);
                        z = true;
                    } else if (!z2 && mfg.f(next.acquireType())) {
                        c(String.valueOf(20005), hashMap);
                        z2 = true;
                    } else if (mfg.d(next.acquireType())) {
                        int totalTime = (int) next.getTotalTime();
                        if (totalTime > i) {
                            i = totalTime;
                        }
                    } else if (!mfg.b(next.acquireType())) {
                        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "dealTrackData trackData need not process");
                    } else if (trackData == null || trackData.acquireDistance() < next.acquireDistance()) {
                        trackData = next;
                    }
                }
            }
        }
        if (trackData != null) {
            hashMap.put(KakaConstants.TRACK_TIME, Long.valueOf(trackData.acquireEndTime()));
            hashMap.put(KakaConstants.TRACK_DISTANCE, Float.valueOf(trackData.acquireDistance()));
            c(String.valueOf(KakaConstants.TASK_RUN_BEHAVIOR), hashMap);
        }
        if (i > 0) {
            hashMap.put(KakaConstants.ROPE_DURATION, Integer.valueOf(i));
            c(String.valueOf(20006), hashMap);
        }
    }

    public void c(String str, Map<String, Object> map) {
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "setEvent");
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "setEvent key is empty");
            return;
        }
        if (mle.d(str)) {
            c.c(str);
            return;
        }
        if (mle.l(str)) {
            c.c(str);
        }
        if (String.valueOf(1500).equals(str)) {
            a(map);
            c.c(str);
        } else {
            if (String.valueOf(1).equals(str)) {
                d(map);
                return;
            }
            if (String.valueOf(SmartMsgConstant.MSG_TYPE_REDUCE_FAT_USER).equals(str)) {
                if (map == null || !map.containsKey("share_key")) {
                    return;
                }
                c(((Integer) map.get("share_key")).intValue());
                return;
            }
            e(str, map);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final String str, final Map<String, Object> map) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: mer.1
            @Override // java.lang.Runnable
            public void run() {
                mer.this.d(str, (Map<String, Object>) map);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(String str, Map<String, Object> map) {
        int a2 = a(str);
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "dealTaskFinishWithKey taskRuleId=", Integer.valueOf(a2));
        if (!mle.k(String.valueOf(a2))) {
            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "dealTaskFinish invalid key");
            return;
        }
        List<mdf> a3 = a(0, -1, d(a2));
        if (koq.c(a3)) {
            for (mdf mdfVar : a3) {
                e(mdfVar.ag(), mdfVar, map);
            }
            return;
        }
        e(a2, (mdf) null, map);
    }

    private void e(int i, mdf mdfVar, Map<String, Object> map) {
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "dealTaskFinish taskRuleId=", Integer.valueOf(i));
        if (i == 20001) {
            j(mdfVar, map);
            return;
        }
        if (i == 20002) {
            h(mdfVar, map);
            return;
        }
        if (i == 20004) {
            a(mdfVar, map);
            return;
        }
        if (i == 30002) {
            i(mdfVar, map);
            return;
        }
        if (i == 30005) {
            a(mdfVar);
            c.c(String.valueOf(KakaConstants.TASK_ENTER_TODAY_WEIGHT));
            return;
        }
        if (i != 30009) {
            if (i == 30018) {
                d(mdfVar, map);
                return;
            }
            if (i == 40003) {
                e(mdfVar, map);
                return;
            }
            if (i != 20006) {
                if (i != 20007 && i != 30013 && i != 30014) {
                    switch (i) {
                        case SmartMsgConstant.MSG_TYPE_BLOOD_SUGAR_USER /* 40006 */:
                        case SmartMsgConstant.MSG_TYPE_SLEEP_USER /* 40007 */:
                        case 40008:
                        case 40009:
                            break;
                        default:
                            switch (i) {
                                case 40012:
                                    c(mdfVar, map);
                                    break;
                                case 40013:
                                    f(mdfVar, map);
                                    break;
                                default:
                                    a(mdfVar);
                                    LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "other key");
                                    break;
                            }
                    }
                    return;
                }
                b(mdfVar, map);
                return;
            }
        }
        g(mdfVar, map);
    }

    private void b(mdf mdfVar, Map<String, Object> map) {
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "enter dealDesignatedTask");
        if (mdfVar == null) {
            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "dealDesignatedTask kakaTaskInfo is null");
            return;
        }
        if (TextUtils.isEmpty(mdfVar.a()) || map == null) {
            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "dealDesignatedTask map is null");
            a(mdfVar);
            return;
        }
        Object obj = map.get(ParsedFieldTag.TASK_KEY);
        if (obj instanceof String) {
            String str = (String) obj;
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "dealDesignatedTask taskKey: ", str);
            if (str.equals(mdfVar.a())) {
                a(mdfVar);
                LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "dealDesignatedTask finish!");
            }
        }
    }

    private void f(mdf mdfVar, Map<String, Object> map) {
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "enter dealTimeHeadLinesTask");
        if (map == null || mdfVar == null) {
            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "dealTimeHeadLinesTask map or kakaTaskInfo is null");
            return;
        }
        Object obj = map.get(KakaConstants.SLEEP_MUSIC_DURATION);
        if (obj instanceof Long) {
            long longValue = ((Long) obj).longValue();
            int ad = mdfVar.ad();
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "dealTimeHeadLinesTask level ", Integer.valueOf(ad), " duration ", Long.valueOf(longValue));
            if (ad * 60 <= longValue) {
                a(mdfVar);
                LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "dealTimeHeadLinesTask finish!");
            }
        }
    }

    private void c(mdf mdfVar, Map<String, Object> map) {
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "enter dealHeadLinesTask");
        if (map == null || mdfVar == null) {
            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "dealHeadLinesTask map or kakaTaskInfo is null");
            return;
        }
        if (map.get(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID) instanceof String) {
            String str = (String) map.get(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID);
            String a2 = mdfVar.a();
            if (a2 == null || !a2.equals(str)) {
                return;
            }
            a(mdfVar);
        }
    }

    private void a(mkj mkjVar) {
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "enter dealTaskFinishByTaskRecord");
        if (mkjVar == null || mkjVar.n() == 0) {
            return;
        }
        HashMap hashMap = new HashMap(2);
        String j = mkjVar.j();
        if (j == null) {
            j = "";
        }
        hashMap.put(ParsedFieldTag.TASK_KEY, j);
        e(String.valueOf(mkjVar.n()), hashMap);
    }

    private void e(final mdf mdfVar, Map<String, Object> map) {
        if (map == null || map.size() == 0) {
            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "dealActivityShareTask map null");
            return;
        }
        final String valueOf = String.valueOf(map.get("url"));
        if (TextUtils.isEmpty(valueOf)) {
            return;
        }
        Tasks.callInBackground(new Callable<Boolean>() { // from class: mer.15
            @Override // java.util.concurrent.Callable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public Boolean call() throws Exception {
                String url = GRSManager.a(mer.this.g).getUrl("activityUrl");
                String url2 = GRSManager.a(mer.this.g).getUrl("domainContentcenterDbankcdnNew");
                if (valueOf.contains(url) || ((valueOf.contains(url2) && valueOf.contains("activityId")) || (valueOf.contains("activityId") && valueOf.contains("groupChallenge")))) {
                    LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "dealActivityShareTask end");
                    mer.this.a(mdfVar);
                }
                return true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(mdf mdfVar) {
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "enter dealNoParamTask");
        if (b == null || mdfVar == null) {
            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "dealNoParamTask mAchieveDataManager or kakaTaskInfo is empty");
            return;
        }
        int ag = mdfVar.ag();
        if (mle.c(mdfVar) && !mle.a(ag)) {
            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "dealNoParamTask isKakaTask isFinished or lapsed. taskRuleId == ", Integer.valueOf(ag));
            return;
        }
        long q = mdfVar.q();
        long currentTimeMillis = System.currentTimeMillis();
        String p = mdfVar.p();
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "dealNoParamTask KakaTaskInfo taskRule =", Integer.valueOf(ag), " taskStatus = ", p, " taskSyncTimestamp = ", Long.valueOf(q), " curTimestamp =", Long.valueOf(currentTimeMillis));
        if (mle.e(currentTimeMillis, q)) {
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "curTimestamp is invalid =", Long.valueOf(currentTimeMillis));
            return;
        }
        if (String.valueOf(0).equals(p)) {
            e(mdfVar);
            return;
        }
        if (String.valueOf(1).equals(p)) {
            mdfVar.c(System.currentTimeMillis());
            b.e(mdfVar);
            a(mdfVar, 1);
        } else if (String.valueOf(2).equals(p)) {
            e(mdfVar, q, currentTimeMillis);
        } else {
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "taskStatus is lapsed =", p);
        }
    }

    private void e(mdf mdfVar) {
        mct.b(this.g, "taskReachInfo", mdfVar.h());
        mdfVar.h(-1);
        d(mdfVar, 1);
        a(mdfVar, 1);
    }

    private void e(mdf mdfVar, long j, long j2) {
        if (!mle.d(mdfVar.ag()) && mle.a(j, j2)) {
            mct.b(this.g, "taskReachInfo", mdfVar.h());
            mdfVar.h(-1);
            d(mdfVar, 1);
            a(mdfVar, 1);
            return;
        }
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "taskStatus is TASK_PICKED");
        if (String.valueOf(30008).equals(mdfVar.h())) {
            a(mdfVar, 1);
        }
    }

    public void b(String str, int i) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "updateUserTaskStatusToDB fail taskId is invalid");
            return;
        }
        HashMap hashMap = new HashMap(2);
        hashMap.put("taskId", str);
        mcz d2 = b.d(12, hashMap);
        if (d2 instanceof mdf) {
            mdf mdfVar = (mdf) d2;
            mdfVar.h(0);
            d(mdfVar, i);
        }
    }

    public void d(mdf mdfVar, int i) {
        if (mdfVar.ag() == 30008) {
            int j = mdfVar.j();
            if (i == 1) {
                mdfVar.b(j + 1);
            } else {
                mdfVar.b(j - 1);
            }
        }
        if (i == 1) {
            mdfVar.c(System.currentTimeMillis());
        }
        mdfVar.i(String.valueOf(i));
        mdfVar.f(0);
        b.e(mdfVar);
    }

    public void a(mdf mdfVar, int i) {
        c(mdfVar, i, "");
    }

    public void c(mdf mdfVar, int i, String str) {
        if (TextUtils.isEmpty(mdfVar.h())) {
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "updateBinderaskStatusToCloud fail taskId is invalid");
            return;
        }
        if (i == 2) {
            t();
        }
        mle.b(mdfVar);
        if (mdfVar.ac() == 2) {
            i = 2;
        }
        final int ac = mdfVar.ac();
        final String h = mdfVar.h();
        b.c(12, d(mdfVar, i, str), new AchieveCallback() { // from class: mer.11
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public void onResponse(int i2, Object obj) {
                LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "updateTaskStatus resCode:", Integer.valueOf(i2), " result:", obj instanceof String ? (String) obj : "");
                if (i2 != 200 && ac == 2) {
                    String b2 = mct.b(BaseApplication.getContext(), "_uploadLevelTask");
                    LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "updateTaskStatus taskIds ", b2);
                    if (b2.contains(h)) {
                        return;
                    }
                    StringBuilder sb = new StringBuilder();
                    if (TextUtils.isEmpty(b2)) {
                        sb.append(mhs.e(System.currentTimeMillis()));
                    }
                    sb.append(b2);
                    sb.append(",");
                    sb.append(h);
                    mct.b(BaseApplication.getContext(), "_uploadLevelTask", sb.toString());
                    LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "updateTaskStatus newTaskIds = ", sb.toString());
                }
            }
        });
    }

    public void d() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: mer.13
            @Override // java.lang.Runnable
            public void run() {
                mer.this.k();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void k() {
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "dealTaskNotDealEvent getData()");
        String b2 = mct.b(this.g, "_uploadLevelTask");
        if (TextUtils.isEmpty(b2)) {
            return;
        }
        String e2 = mhs.e(System.currentTimeMillis());
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "dealTaskNotDealEvent taskIds:", b2, " currentDate:", e2);
        if (!b2.contains(e2)) {
            mct.b(BaseApplication.getContext(), "_uploadLevelTask", "");
            return;
        }
        List<mdf> a2 = a(2);
        if (koq.b(a2)) {
            return;
        }
        boolean z = false;
        for (mdf mdfVar : a2) {
            if (b2.contains(mdfVar.h())) {
                b.c(12, d(mdfVar, 2, ""), new AchieveCallback() { // from class: mer.14
                    @Override // com.huawei.pluginachievement.impl.AchieveCallback
                    public void onResponse(int i, Object obj) {
                        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "dealTaskNotDealEvent resCode:", Integer.valueOf(i), " result:", obj instanceof String ? (String) obj : "");
                        if (i == 200) {
                            mct.b(BaseApplication.getContext(), "_uploadLevelTask", "");
                        }
                    }
                });
                z = true;
            }
        }
        if (z) {
            return;
        }
        mct.b(BaseApplication.getContext(), "_uploadLevelTask", "");
    }

    private JSONObject d(mdf mdfVar, int i, String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(ParsedFieldTag.KAKA_TASK_UUID, mdfVar.h());
            jSONObject2.put("status", String.valueOf(i));
            if (!TextUtils.isEmpty(str)) {
                jSONObject2.put("productId", str);
            }
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject2);
            jSONObject.put(ParsedFieldTag.KAKA_TASKS, jSONArray);
            String d2 = HiDateUtil.d((String) null);
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "getParam getCurrentTimezone=", d2);
            jSONObject.put("timeZone", d2);
            jSONObject.put(ParsedFieldTag.KAKA_TASK_SCENARIO, mdfVar.ac());
        } catch (JSONException e2) {
            LogUtil.b("PLGACHIEVE_AchieveKakaDataManager", "JSONException  ", e2.getMessage());
        }
        return jSONObject;
    }

    public boolean j() {
        String b2 = mct.b(this.g, "wechatBindStatusV3");
        return (TextUtils.isEmpty(b2) || "false".equals(b2)) ? false : true;
    }

    private void t() {
        mct.b(this.g, "taskReachInfo", "");
    }

    public boolean c() {
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "getKakaTaskRedDot enter");
        if (Utils.o() || LoginInit.getInstance(BaseApplication.getContext()).isKidAccount()) {
            return false;
        }
        return l();
    }

    public void cgp_(final Handler handler, final int i) {
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "dealKakaTask enter");
        if (!e(100)) {
            ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
            newSingleThreadExecutor.execute(new Runnable() { // from class: mer.19
                @Override // java.lang.Runnable
                public void run() {
                    mer.this.cgo_(handler, i);
                }
            });
            newSingleThreadExecutor.shutdown();
            return;
        }
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "dealKakaTask isFastClick!");
    }

    public static boolean e(int i) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (elapsedRealtime - f14924a < i) {
            return true;
        }
        f14924a = elapsedRealtime;
        return false;
    }

    private boolean l() {
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "getReachResult taskReachInfo = ", mct.b(this.g, "taskFinishInfo"));
        return !TextUtils.isEmpty(r0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cgo_(Handler handler, int i) {
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "aquireKakaTask enter");
        ArrayList<mkg> o = o();
        if (o == null || o.isEmpty()) {
            if (handler != null) {
                LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "sendMessage MSG_UPDATE_MEDAL_RED_POINT! taskRecyclerViewData is empty");
                handler.sendEmptyMessage(i);
            } else {
                LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "aquireKakaTask handler is null");
            }
            i();
            return;
        }
        Iterator<mkg> it = o.iterator();
        boolean z = false;
        while (it.hasNext()) {
            mkj b2 = it.next().b();
            if (b2 != null && "1".equals(b2.f())) {
                LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "getRedDotResult is true ", Integer.valueOf(b2.n()));
                z = true;
            }
        }
        if (z) {
            s();
        } else {
            i();
        }
        if (handler != null) {
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "sendMessage MSG_UPDATE_MEDAL_RED_POINT!isFinished = ", Boolean.valueOf(z), " what = ", Integer.valueOf(i));
            handler.sendEmptyMessage(i);
        }
    }

    private void i() {
        mct.b(this.g, "taskFinishInfo", "");
    }

    private void s() {
        mct.b(this.g, "taskFinishInfo", "SHOW");
    }

    private ArrayList<mkg> o() {
        ArrayList<mkg> arrayList = new ArrayList<>(0);
        HashMap hashMap = new HashMap(2);
        hashMap.put(ParsedFieldTag.KAKA_TASK_SCENARIO, String.valueOf(0));
        if (b != null) {
            List<mcz> b2 = b.b(12, hashMap);
            if (b2 == null) {
                return arrayList;
            }
            ArrayList arrayList2 = new ArrayList(b2.size());
            for (mcz mczVar : b2) {
                if (mczVar instanceof mdf) {
                    arrayList2.add((mdf) mczVar);
                }
            }
            return mji.c((List<mdf>) arrayList2);
        }
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "mService is null");
        return arrayList;
    }

    private void a(Map<String, Object> map) {
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "enter dealBinderDeviceTask");
        if (map == null) {
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "dealBinderDeviceTask map is null");
            return;
        }
        if (map.get("productId") == null) {
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "dealBinderDeviceTask productId is null");
            return;
        }
        final PluginAchieveAdapter adapter = mcv.d(this.g).getAdapter();
        if (adapter == null) {
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "pluginAchieveAdapter is null");
        } else {
            final String valueOf = String.valueOf(map.get("deviceId"));
            ThreadPoolManager.d().execute(new Runnable() { // from class: mer.16
                @Override // java.lang.Runnable
                public void run() {
                    List<mcz> m = mer.this.m();
                    if (m == null) {
                        LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "kakas is null");
                        return;
                    }
                    for (mcz mczVar : m) {
                        if (mczVar instanceof mdf) {
                            mdf mdfVar = (mdf) mczVar;
                            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "dealBinderDeviceTask update task ruleid = ", Integer.valueOf(mdfVar.ag()), "Scenario ", Integer.valueOf(mdfVar.ac()));
                            if (!mer.this.a(mdfVar.ag(), valueOf, adapter)) {
                                LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "dealBinderDeviceTask device Already bound");
                                return;
                            } else {
                                mer.this.c(mdfVar, 1, valueOf);
                                mdfVar.h(-1);
                                mer.this.d(mdfVar, 1);
                            }
                        }
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(int i, String str, PluginAchieveAdapter pluginAchieveAdapter) {
        if (i == 10004) {
            return true;
        }
        String encryptData = pluginAchieveAdapter.getEncryptData(str, this.g);
        String b2 = mct.b(this.g, "bindDeviceProductId");
        if (a(b2, str, pluginAchieveAdapter)) {
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "dealBinderDeviceTask device Already bind");
            return false;
        }
        if (!TextUtils.isEmpty(b2)) {
            encryptData = b2.concat(",").concat(encryptData);
        }
        mct.b(this.g, "bindDeviceProductId", encryptData);
        return true;
    }

    private List<mcz> g(int i) {
        HashMap hashMap = new HashMap(2);
        hashMap.put(ParsedFieldTag.KAKA_TASK_RULE, String.valueOf(i));
        return b.b(12, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<mcz> m() {
        String b2 = mct.b(this.g, "isFirstBindDeviceFinishedV2");
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "getBinderDeviceTask isFirstBindDeviceFinished ", b2);
        if ("finished".equals(b2)) {
            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "getBinderDeviceTask TASK_BIND_DEVICE！");
            return g(30008);
        }
        List<mcz> g = g(10004);
        boolean c2 = c(g);
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "getBinderDeviceTask isUnFinish ", Boolean.valueOf(c2), " kakas.size ", Integer.valueOf(g.size()));
        if (c2) {
            return g;
        }
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "first bind device is finished");
        mct.b(this.g, "isFirstBindDeviceFinishedV2", "finished");
        return g(30008);
    }

    private boolean c(List<mcz> list) {
        if (list == null) {
            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "kakas is null");
            return false;
        }
        for (mcz mczVar : list) {
            if (mczVar instanceof mdf) {
                mdf mdfVar = (mdf) mczVar;
                if (String.valueOf(0).equals(mdfVar.p())) {
                    LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "first bind device is unfinished ruleid = ", Integer.valueOf(mdfVar.ag()));
                    return true;
                }
            }
        }
        return false;
    }

    public long f() {
        String b2 = mct.b(this.g, "kakaSyncDate");
        if (!TextUtils.isEmpty(b2)) {
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "syncDate=", b2);
            try {
                return Long.parseLong(b2);
            } catch (NumberFormatException e2) {
                LogUtil.b("PLGACHIEVE_AchieveKakaDataManager", "NumberFormatException", e2.getMessage());
            }
        }
        return 0L;
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0049  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x007d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(defpackage.mdf r7, java.util.Map<java.lang.String, java.lang.Object> r8) {
        /*
            r6 = this;
            java.lang.String r0 = "enter dealFitnessTask"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "PLGACHIEVE_AchieveKakaDataManager"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            java.util.Date r0 = new java.util.Date
            long r2 = java.lang.System.currentTimeMillis()
            r0.<init>(r2)
            r2 = 0
            if (r8 == 0) goto L3c
            java.lang.String r3 = "fitness_duration"
            java.lang.Object r4 = r8.get(r3)
            if (r4 == 0) goto L3c
            java.lang.Object r8 = r8.get(r3)
            java.lang.String r8 = java.lang.String.valueOf(r8)
            int r8 = java.lang.Integer.parseInt(r8)     // Catch: java.lang.NumberFormatException -> L30
            int r3 = r8 / 1000
            int r3 = r3 / 60
            goto L3d
        L30:
            r8 = r2
        L31:
            java.lang.String r3 = "NumberFormatException"
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            com.huawei.hwlogsmodel.LogUtil.b(r1, r3)
            r3 = r8
            goto L3d
        L3c:
            r3 = r2
        L3d:
            android.content.Context r8 = r6.g
            mcv r8 = defpackage.mcv.d(r8)
            com.huawei.pluginachievement.PluginAchieveAdapter r8 = r8.getAdapter()
            if (r8 == 0) goto L4d
            int r2 = r8.getTotalFitDuration(r0, r0)
        L4d:
            java.lang.Integer r8 = java.lang.Integer.valueOf(r2)
            java.lang.String r0 = "singleDuration="
            java.lang.Integer r4 = java.lang.Integer.valueOf(r3)
            java.lang.String r5 = "duration="
            java.lang.Object[] r8 = new java.lang.Object[]{r5, r8, r0, r4}
            com.huawei.hwlogsmodel.LogUtil.c(r1, r8)
            r8 = 10
            if (r3 >= r8) goto L66
            if (r2 < r8) goto L71
        L66:
            mes r8 = defpackage.mer.c
            r0 = 1300(0x514, float:1.822E-42)
            java.lang.String r0 = java.lang.String.valueOf(r0)
            r8.c(r0)
        L71:
            if (r7 != 0) goto L7d
            java.lang.String r7 = "dealFitnessTask kakaTaskInfo is null"
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            com.huawei.hwlogsmodel.LogUtil.h(r1, r7)
            return
        L7d:
            int r8 = r7.ad()
            java.lang.String r0 = " fitLevelTime="
            java.lang.Integer r4 = java.lang.Integer.valueOf(r8)
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r4}
            com.huawei.hwlogsmodel.LogUtil.c(r1, r0)
            if (r3 >= r8) goto L92
            if (r2 < r8) goto L95
        L92:
            r6.a(r7)
        L95:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.mer.a(mdf, java.util.Map):void");
    }

    private void i(mdf mdfVar, Map<String, Object> map) {
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "enter dealSleepTask");
        if (map == null || mdfVar == null) {
            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "dealSleepTask map or kakaTaskInfo is null");
            return;
        }
        if (map.get(KakaConstants.SLEEP_DURATION) != null) {
            int intValue = ((Integer) map.get(KakaConstants.SLEEP_DURATION)).intValue();
            int ad = mdfVar.ad();
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "dealSleepTask hourLevel:", Integer.valueOf(ad), " sleepDuration ", Integer.valueOf(intValue));
            if (ad * 60 <= intValue) {
                a(mdfVar);
            }
        }
    }

    private void h(mdf mdfVar, Map<String, Object> map) {
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "enter dealSingleTrackTask");
        if (map == null) {
            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "dealSingleTrackTask map is null");
            return;
        }
        if (map.get(KakaConstants.TRACK_TIME) == null || map.get(KakaConstants.TRACK_DISTANCE) == null) {
            return;
        }
        try {
            Object obj = map.get(KakaConstants.TRACK_TIME);
            Object obj2 = map.get(KakaConstants.TRACK_DISTANCE);
            long parseLong = Long.parseLong(String.valueOf(obj));
            double d2 = mlg.d((long) Float.parseFloat(String.valueOf(obj2)));
            if (mle.a(parseLong)) {
                LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "dealSingleTrackTask track is valid");
                if (d2 >= 3.0d) {
                    c.c(String.valueOf(KakaConstants.TASK_RUN_BEHAVIOR));
                }
                if (mdfVar == null) {
                    LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "dealSingleTrackTask kakaTaskInfo is null");
                    return;
                }
                int ad = mdfVar.ad();
                if (d2 < ad) {
                    LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "dealSingleTrackTask track distance < ", Integer.valueOf(ad));
                } else {
                    a(mdfVar);
                    LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "dealSingleTrackTask run success! trackTime ", Long.valueOf(parseLong));
                }
            }
        } catch (NumberFormatException unused) {
            LogUtil.b("PLGACHIEVE_AchieveKakaDataManager", "dealSingleTrackTask NumberFormatException");
        }
    }

    private void j(mdf mdfVar, Map<String, Object> map) {
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "enter dealStepsChangeTask");
        if (map == null || mdfVar == null) {
            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "dealStepsChangeTask map or kakaTaskInfo is null");
            return;
        }
        if (map.get("step") != null) {
            try {
                final int parseInt = Integer.parseInt(String.valueOf(map.get("step")));
                ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
                newSingleThreadExecutor.execute(new Runnable() { // from class: mer.18
                    @Override // java.lang.Runnable
                    public void run() {
                        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "generateHistoryBestMsg() enter");
                        mer.b.b(parseInt);
                    }
                });
                newSingleThreadExecutor.shutdown();
                if (parseInt >= mdfVar.ad()) {
                    LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "dealNoParamTask STEPS_TASK_GOAL.");
                    a(mdfVar);
                }
            } catch (NumberFormatException unused) {
                LogUtil.b("PLGACHIEVE_AchieveKakaDataManager", "dealSingleTrackTask NumberFormatException");
            }
        }
    }

    private void g(mdf mdfVar, Map<String, Object> map) {
        String str;
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "enter dealOneDurationParamTask");
        if (map == null || mdfVar == null) {
            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "dealOneDurationParamTask map or kakaTaskInfo is null");
            return;
        }
        int ag = mdfVar.ag();
        if (ag == 20006) {
            str = KakaConstants.ROPE_DURATION;
        } else {
            if (ag != 30009) {
                LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "dealOneDurationParamTask error taskRuleId");
                return;
            }
            str = KakaConstants.SLEEP_MUSIC_DURATION;
        }
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "dealOneDurationParamTask map = ", map.toString());
        if (TextUtils.isEmpty(str) || map.get(str) == null) {
            return;
        }
        int intValue = ((Integer) map.get(str)).intValue();
        int ad = mdfVar.ad();
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "dealOneDurationParamTask duration = ", Integer.valueOf(intValue), " minuteLevel= ", Integer.valueOf(ad));
        if (ad * 60000 <= intValue) {
            a(mdfVar);
        }
    }

    private void d(mdf mdfVar, Map<String, Object> map) {
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "enter dealAssignSleepMusicParamTask");
        if (map != null && mdfVar != null) {
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "dealAssignSleepMusicParamTask map = ", map.toString());
            if (TextUtils.isEmpty(KakaConstants.SLEEP_MUSIC_ID) || map.get(KakaConstants.SLEEP_MUSIC_ID) == null) {
                LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "dealAssignSleepMusicParamTask paramPlayIdKey is null");
                return;
            }
            String a2 = mdfVar.a();
            if (a2 == null || !a2.equals(String.valueOf(map.get(KakaConstants.SLEEP_MUSIC_ID)))) {
                LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "dealAssignSleepMusicParamTask taskKey is wrong!");
                return;
            }
            if (TextUtils.isEmpty(KakaConstants.SLEEP_MUSIC_DURATION) || map.get(KakaConstants.SLEEP_MUSIC_DURATION) == null) {
                return;
            }
            int intValue = ((Integer) map.get(KakaConstants.SLEEP_MUSIC_DURATION)).intValue();
            int ad = mdfVar.ad();
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "dealAssignSleepMusicParamTask duration = ", Integer.valueOf(intValue), " minuteLevel= ", Integer.valueOf(ad));
            if (ad * 60000 <= intValue) {
                a(mdfVar);
                return;
            }
            return;
        }
        LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "dealAssignSleepMusicParamTask map or kakaTaskInfo is null");
    }

    public void b(mkj mkjVar, Context context) {
        if (mkjVar == null) {
            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "gotoTaskPageByTaskID taskRecord is empty");
            return;
        }
        if (!mle.k(String.valueOf(mkjVar.n()))) {
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "gotoTaskPageByTaskID key is invalid");
            return;
        }
        PluginAchieveAdapter adapter = mcv.d(this.g).getAdapter();
        if (adapter == null) {
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "pluginAchieveAdapter is null");
        } else {
            d(adapter, mkjVar, context);
        }
    }

    private void d(PluginAchieveAdapter pluginAchieveAdapter, mkj mkjVar, Context context) {
        if (mkjVar == null) {
            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "gotoTaskPageByTaskID taskRecord is empty");
            return;
        }
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "gotoTaskPage taskRule = ", Integer.valueOf(mkjVar.n()));
        if (e(pluginAchieveAdapter, mkjVar, context)) {
            return;
        }
        int n = mkjVar.n();
        if (n == 10004 || n == 30008) {
            pluginAchieveAdapter.gotoAllDeviceBinding(context);
            return;
        }
        if (n != 30015) {
            if (n == 30011) {
                pluginAchieveAdapter.goToPhysiologicalCycle(context);
                a(mkjVar);
                return;
            }
            if (n != 30012) {
                switch (n) {
                    case 20001:
                        pluginAchieveAdapter.gotoStepRecord(context);
                        break;
                    case 20002:
                        b(KakaConstants.RUN_URL + (mkjVar.i() * 1000));
                        break;
                    case 20003:
                        b(KakaConstants.RIDE_URL);
                        break;
                    case 20004:
                        pluginAchieveAdapter.gotoFitness();
                        break;
                    default:
                        switch (n) {
                            case 20006:
                                b(KakaConstants.SKIP_ROPE_URL + (mkjVar.i() * 60000));
                                break;
                            case 20007:
                                pluginAchieveAdapter.gotoTrainDetailActivity(mkjVar.a(), mkjVar.j());
                                break;
                            case 20008:
                                pluginAchieveAdapter.gotoFitnessAllCourses();
                                break;
                            default:
                                a(pluginAchieveAdapter, mkjVar.n(), context);
                                break;
                        }
                }
                return;
            }
        }
        Bundle bundle = new Bundle();
        bundle.putBoolean("pressure_is_have_datas", d);
        eyv.atT_(context, bundle);
    }

    private boolean e(PluginAchieveAdapter pluginAchieveAdapter, mkj mkjVar, Context context) {
        if (TextUtils.isEmpty(mkjVar.d())) {
            return false;
        }
        if (mkjVar.n() == 20006 && SportSupportUtil.h()) {
            return false;
        }
        c(context, pluginAchieveAdapter, mkjVar);
        if (mle.c(mkjVar.n())) {
            a(mkjVar);
        }
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "task hasOperationUrl");
        return true;
    }

    private void d(Context context) {
        dsl.ZK_(context, null);
    }

    private void a(PluginAchieveAdapter pluginAchieveAdapter, int i, Context context) {
        if (i == 10002) {
            a(KakaConstants.USER_INFO_URL, context);
        }
        if (i == 10003) {
            pluginAchieveAdapter.gotoWeChatActivity(context);
            return;
        }
        if (i == 10005) {
            mle.c(context, 5);
            return;
        }
        if (i == 20009) {
            if (CommonUtil.cc()) {
                mle.e(context, KakaConstants.TEST_REDUCE_PLAN_URL);
                return;
            } else {
                mle.e(context, KakaConstants.REDUCE_PLAN_URL);
                return;
            }
        }
        if (i == 30016) {
            mle.e(context, KakaConstants.DIET_REPORT_URL);
            return;
        }
        if (i == 30017) {
            mle.e(context, KakaConstants.DIET_RECIPE_URL);
            return;
        }
        switch (i) {
            case 40001:
                mcv.d(context).j(context);
                break;
            case 40002:
                pluginAchieveAdapter.gotoRunRecord(context);
                break;
            case 40003:
            case 40004:
            case SmartMsgConstant.MSG_TYPE_RIDE_USER /* 40005 */:
                LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "error, taskDetailUrl is empty");
                break;
            default:
                d(pluginAchieveAdapter, i, context);
                break;
        }
    }

    private void d(PluginAchieveAdapter pluginAchieveAdapter, int i, Context context) {
        if (i == 1200) {
            pluginAchieveAdapter.startMyTargetActivity(context);
            return;
        }
        if (i != 30009) {
            if (i != 30010) {
                switch (i) {
                    case 30001:
                        d(context);
                        break;
                    case 30002:
                        break;
                    case OnRegisterSkinAttrsListener.REGISTER_BY_SCAN /* 30003 */:
                        pluginAchieveAdapter.gotoMeasureDeivice(context, "HDK_BLOOD_PRESSURE");
                        break;
                    case 30004:
                        pluginAchieveAdapter.gotoMeasureDeivice(context, "HDK_BLOOD_SUGAR");
                        break;
                    case 30005:
                        pluginAchieveAdapter.gotoMeasureDeivice(context, "HDK_WEIGHT");
                        break;
                    case 30006:
                        pluginAchieveAdapter.gotoMeasureDeivice(context, "HDK_WEAR");
                        break;
                    case 30007:
                        ((FamilyHealthZoneApi) Services.a("PluginHealthZone", FamilyHealthZoneApi.class)).gotoFamilyHealth(context, knl.d(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011) + "sIsFirstHealth"));
                        break;
                    default:
                        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "invalid key");
                        break;
                }
                return;
            }
            mxv.d(BaseApplication.getContext(), "com.huawei.health.ecg.collection");
            return;
        }
        pluginAchieveAdapter.gotoSleepDetailPage(context);
    }

    private void c(Context context, PluginAchieveAdapter pluginAchieveAdapter, mkj mkjVar) {
        if (mkjVar == null || context == null) {
            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "handleTaskTodoSkip messageObject is null");
            return;
        }
        String d2 = mkjVar.d();
        if (!d2.contains("pullfrom=kaka") && d2.contains("?")) {
            d2 = d2 + "&pullfrom=kaka";
        }
        LogUtil.c("PLGACHIEVE_AchieveKakaDataManager", "handleTaskTodoSkip btnDetailUrl: ", d2);
        if (d2.startsWith("huaweischeme")) {
            Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(d2));
            intent.setPackage(context.getPackageName());
            jdw.bGh_(intent, context);
            return;
        }
        pluginAchieveAdapter.startWebActivityForResult(context, d2);
    }

    public void b(String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "url is null");
            return;
        }
        if (e(str) || str.startsWith("mailto:") || str.startsWith(KakaConstants.SCHEME_TEL)) {
            try {
                Intent intent = new Intent();
                intent.setAction(CommonConstant.ACTION.HWID_SCHEME_URL);
                intent.setFlags(268435456);
                intent.setData(Uri.parse(str));
                this.g.startActivity(intent);
                LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "jump to scheme view");
            } catch (ActivityNotFoundException e2) {
                LogUtil.b("PLGACHIEVE_AchieveKakaDataManager", e2.getMessage());
            }
        }
    }

    private boolean e(String str) {
        return (str.startsWith("http") || str.startsWith(ProxyConfig.MATCH_HTTPS)) || (str.startsWith("huaweihealth") || str.startsWith("huaweischeme") || str.startsWith("geo:"));
    }

    private void a(String str, Context context) {
        try {
            Intent intent = new Intent();
            intent.addFlags(268435456);
            intent.setPackage(BaseApplication.getAppPackage());
            intent.setClassName(BaseApplication.getAppPackage(), str);
            context.startActivity(intent);
        } catch (ActivityNotFoundException | IllegalArgumentException e2) {
            LogUtil.b("PLGACHIEVE_AchieveKakaDataManager", e2.getMessage());
        }
    }

    public void b(int i) {
        mcz d2 = b.d(5, new HashMap(2));
        if (d2 instanceof AchieveInfo) {
            AchieveInfo achieveInfo = (AchieveInfo) d2;
            int userPoint = achieveInfo.getUserPoint();
            if (i > userPoint) {
                LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "kakaTotalNum=", Integer.valueOf(i));
                achieveInfo.setUserPoint(i);
                b.e(achieveInfo);
                return;
            }
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "kaka=", Integer.valueOf(userPoint));
        }
    }

    private boolean a(String str, String str2, PluginAchieveAdapter pluginAchieveAdapter) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        for (String str3 : str.split(",")) {
            if (!TextUtils.isEmpty(str3) && pluginAchieveAdapter.decryptData(str3, this.g).equals(str2)) {
                return true;
            }
        }
        return false;
    }

    public void e() {
        ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
        newSingleThreadExecutor.execute(new Runnable() { // from class: mer.17
            @Override // java.lang.Runnable
            public void run() {
                HashMap hashMap = new HashMap(2);
                hashMap.put(ParsedFieldTag.KAKA_TASK_RULE, String.valueOf(10003));
                List<mcz> b2 = mer.b.b(12, hashMap);
                if (b2 == null) {
                    LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "kakas is null");
                    return;
                }
                for (mcz mczVar : b2) {
                    if (mczVar instanceof mdf) {
                        mdf mdfVar = (mdf) mczVar;
                        if (String.valueOf(0).equals(mdfVar.p())) {
                            mer.this.a(mdfVar, 1);
                            mdfVar.i(String.valueOf(1));
                            mer.b.e(mdfVar);
                        }
                    }
                }
                mct.b(mer.this.g, "wechatBindStatusV3", "true");
            }
        });
        newSingleThreadExecutor.shutdown();
    }

    public long d(meh mehVar) {
        HashMap hashMap = new HashMap(3);
        hashMap.put("page", String.valueOf(1));
        hashMap.put(IAchieveDBMgr.PARAM_PAGE_SIZE, String.valueOf(1));
        mcz d2 = mehVar != null ? mehVar.d(6, hashMap) : null;
        if (d2 == null || !(d2 instanceof KakaLineRecord)) {
            return 0L;
        }
        KakaLineRecord kakaLineRecord = (KakaLineRecord) d2;
        if (kakaLineRecord.getKakaLineRecords() == null || kakaLineRecord.getKakaLineRecords().size() == 0) {
            return 0L;
        }
        return kakaLineRecord.getKakaLineRecords().get(0).getDate();
    }

    public boolean c(int i) {
        String str;
        if (i == 6) {
            str = String.valueOf(KakaConstants.TASK_SHARE_RUN_ACHIEVEMENT);
        } else if (i == 2) {
            str = String.valueOf(KakaConstants.TASK_SHARE_FITNESS_ACHIEVEMENT);
        } else if (i == 4) {
            str = String.valueOf(KakaConstants.TASK_SHARE_STEPS_ACHIEVEMENT);
        } else if (i == 5) {
            str = String.valueOf(KakaConstants.TASK_SHARE_MEDAL);
        } else if (i == 40002) {
            str = String.valueOf(i);
        } else if (i == 1100064) {
            str = String.valueOf(40010);
        } else {
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "key not in (TRACK，FITNESS，STEP，MEDAL)");
            str = "";
        }
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "dealShareTask key ", str);
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        e(str, (Map<String, Object>) null);
        return true;
    }

    public void b() {
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "enter dealLoginTask");
        if (!n()) {
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "dealLoginTask return");
        } else if (LoginInit.getInstance(BaseApplication.getContext()).getIsLogined()) {
            ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
            newSingleThreadExecutor.execute(new Runnable() { // from class: mer.4
                @Override // java.lang.Runnable
                public void run() {
                    HashMap hashMap = new HashMap(2);
                    hashMap.put(ParsedFieldTag.KAKA_TASK_RULE, String.valueOf(10001));
                    mcz d2 = mer.b.d(12, hashMap);
                    if (d2 != null) {
                        mer.this.d((mdf) d2);
                    } else {
                        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "kakas is null");
                    }
                }
            });
            newSingleThreadExecutor.shutdown();
        }
    }

    private boolean n() {
        long j;
        String b2 = mct.b(this.g, "kakaLoginTaskV2");
        if (TextUtils.isEmpty(b2)) {
            return true;
        }
        try {
            j = Long.parseLong(b2);
        } catch (NumberFormatException e2) {
            LogUtil.b("PLGACHIEVE_AchieveKakaDataManager", "judgeRequireLoginTask", e2.getMessage());
            j = -1;
        }
        if (j != 0) {
            return true;
        }
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "all loginTask is finish");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(mdf mdfVar) {
        if (mdfVar == null) {
            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "dealTodayLoginTask kakaTaskInfo is null");
            return;
        }
        if (String.valueOf(0).equals(mdfVar.p())) {
            mct.b(this.g, "kakaLoginTaskV2", String.valueOf(0));
            mdfVar.i(String.valueOf(1));
            b.e(mdfVar);
            a(mdfVar, 1);
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "dealTodayLoginTask Id=", Integer.valueOf(mdfVar.ag()));
        }
    }

    public Map<Integer, Pair<Double, Long>> b(long j, int i) {
        long j2;
        HashMap hashMap = new HashMap(11);
        mcz d2 = b.d(2, new HashMap(2));
        SingleDayRecord singleDayRecord = d2 instanceof SingleDayRecord ? (SingleDayRecord) d2 : null;
        if (i == 1) {
            j2 = 604799999 + j;
        } else if (i == 0) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(j);
            calendar.set(5, calendar.getActualMaximum(5));
            calendar.set(11, 23);
            calendar.set(12, 59);
            calendar.set(13, 59);
            calendar.set(14, 999);
            j2 = calendar.getTimeInMillis();
        } else {
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "report type is not WEEK and MONTH.");
            j2 = 0;
        }
        long j3 = j2;
        b(j, j3, singleDayRecord, hashMap, 2);
        b(j, j3, singleDayRecord, hashMap, 1);
        b(j, j3, singleDayRecord, hashMap, 3);
        b(j, j3, singleDayRecord, hashMap, 4);
        b(j, j3, singleDayRecord, hashMap, 5);
        b(j, j3, singleDayRecord, hashMap, 6);
        b(j, j3, singleDayRecord, hashMap, 7);
        b(j, j3, singleDayRecord, hashMap, 8);
        b(j, j3, singleDayRecord, hashMap, 9);
        b(j, j3, singleDayRecord, hashMap, 10);
        b(j, j3, singleDayRecord, hashMap, 11);
        return hashMap;
    }

    private void b(long j, long j2, SingleDayRecord singleDayRecord, Map map, int i) {
        Pair<Double, Long> cku_ = mlg.cku_(mlg.b(i, singleDayRecord), j, j2, i);
        if (cku_ != null) {
            LogUtil.c("PLGACHIEVE_AchieveKakaDataManager", "structBreakInfoByType=", Integer.valueOf(i), "first=", cku_.first);
            map.put(Integer.valueOf(i), cku_);
        }
    }

    private void d(Map<String, Object> map) {
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "enter dealDeleteTrackData");
        if (map == null) {
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "dealDeleteTrackData map is null");
            return;
        }
        if (map.get("startTime") == null || map.get("type") == null) {
            return;
        }
        try {
            Object obj = map.get("startTime");
            Object obj2 = map.get("type");
            final long parseLong = Long.parseLong(String.valueOf(obj));
            final int parseInt = Integer.parseInt(String.valueOf(obj2));
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "dealDeleteTrackData type=", Integer.valueOf(parseInt), "trackTime=", Long.valueOf(parseLong));
            ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
            newSingleThreadExecutor.execute(new Runnable() { // from class: mer.2
                @Override // java.lang.Runnable
                public void run() {
                    mcz d2 = mer.b.d(2, new HashMap(2));
                    SingleDayRecord singleDayRecord = d2 instanceof SingleDayRecord ? (SingleDayRecord) d2 : null;
                    if (singleDayRecord != null) {
                        if (mer.this.c(singleDayRecord, parseInt, parseLong) > 0) {
                            singleDayRecord.setDelete(true);
                            mer.b.e(singleDayRecord);
                            mer.this.g();
                            return;
                        }
                        return;
                    }
                    LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "singleDayRecord is null");
                }
            });
            newSingleThreadExecutor.shutdown();
        } catch (NumberFormatException unused) {
            LogUtil.b("PLGACHIEVE_AchieveKakaDataManager", "dealSingleTrackTask NumberFormatException");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        if (Utils.o()) {
            mda.a(this.g);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int c(SingleDayRecord singleDayRecord, int i, long j) {
        int i2;
        int d2;
        int d3;
        if (mfg.b(i)) {
            int d4 = d(singleDayRecord, 3, j);
            int d5 = d(singleDayRecord, 4, j);
            int d6 = d(singleDayRecord, 5, j);
            d2 = d4 + d5 + d6 + d(singleDayRecord, 6, j) + d(singleDayRecord, 7, j) + d(singleDayRecord, 8, j);
            d3 = d(singleDayRecord, 9, j);
        } else {
            if (mfg.g(i)) {
                i2 = d(singleDayRecord, 1, j);
            } else if (mfg.a(i)) {
                d2 = d(singleDayRecord, 10, j);
                d3 = d(singleDayRecord, 11, j);
            } else if (mfg.d(i)) {
                d2 = d(singleDayRecord, 12, j) + d(singleDayRecord, 13, j) + d(singleDayRecord, 14, j);
                d3 = d(singleDayRecord, 15, j);
            } else {
                LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "type is not in (run，walk，cycle)");
                i2 = 0;
            }
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "count=", Integer.valueOf(i2));
            return i2;
        }
        i2 = d3 + d2;
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "count=", Integer.valueOf(i2));
        return i2;
    }

    private int d(SingleDayRecord singleDayRecord, int i, long j) {
        String e2 = mlg.e(j, mlg.b(i, singleDayRecord));
        if (TextUtils.isEmpty(e2)) {
            return 0;
        }
        mlg.e(i, singleDayRecord, e2);
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "str=", mlg.b(i, singleDayRecord));
        return 1;
    }

    public List<mdf> a(int i, int i2, int[] iArr) {
        int i3;
        ArrayList arrayList = new ArrayList(2);
        if (i == 1 || i == 2) {
            i3 = 17;
        } else if (i == 0) {
            if (!mle.a(f(), System.currentTimeMillis())) {
                LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "can getKakaListFromDb");
                List<mdf> a2 = a(i2);
                return koq.c(a2) ? mle.b(iArr, a2) : a2;
            }
            i3 = 11;
        } else {
            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "getKakaTaskLists error type = ", Integer.valueOf(i));
            return arrayList;
        }
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "getKakaTaskLists contentType = ", Integer.valueOf(i3));
        meu meuVar = new meu(BaseApplication.getContext(), i3);
        meuVar.e(iArr);
        meh.c(BaseApplication.getContext()).b(meuVar);
        HashMap hashMap = new HashMap(2);
        hashMap.put("type", String.valueOf(i));
        hashMap.put(ParsedFieldTag.KAKA_TASK_SCENARIO, String.valueOf(i2));
        hashMap.put("timeZone", HiDateUtil.d((String) null));
        meh.c(BaseApplication.getContext()).a(i3, hashMap);
        try {
            meuVar.a(PreConnectManager.CONNECT_INTERNAL, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            LogUtil.b("PLGACHIEVE_AchieveKakaDataManager", "AchieveKakaObserver.await(): catch a InterruptedException");
        }
        if (meuVar.b() instanceof ArrayList) {
            arrayList = (ArrayList) meuVar.b();
        } else {
            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "getKakaTaskLists error");
        }
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "kakaTaskInfoList == ", arrayList.toString());
        meh.c(BaseApplication.getContext()).c(meuVar);
        return arrayList;
    }

    private List<mdf> a(int i) {
        ArrayList arrayList = new ArrayList(2);
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "can getKakaListFromDb");
        HashMap hashMap = new HashMap(2);
        if (i != -1) {
            hashMap.put(ParsedFieldTag.KAKA_TASK_SCENARIO, String.valueOf(i));
        }
        List<mcz> b2 = b.b(12, hashMap);
        if (b2 == null) {
            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "getKakaListFromDb kaka lists in dabase is empty");
            return arrayList;
        }
        for (mcz mczVar : b2) {
            if (mczVar instanceof mdf) {
                mdf mdfVar = (mdf) mczVar;
                if (!String.valueOf(3).equals(mdfVar.p())) {
                    arrayList.add(mdfVar);
                }
            }
        }
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "getKakaListFromDb kakaTaskInfos size = ", Integer.valueOf(arrayList.size()));
        return arrayList;
    }

    public mde d(int i, String[] strArr) {
        ArrayList arrayList = new ArrayList(2);
        for (String str : strArr) {
            mdg mdgVar = new mdg();
            mdgVar.b(str);
            mdgVar.e(2);
            arrayList.add(mdgVar);
        }
        return b(arrayList, i, 0);
    }

    public mde b(List<mdg> list, int i, int i2) {
        int i3;
        String d2 = HiDateUtil.d((String) null);
        if (i2 == 1) {
            d2 = CommonUtil.am();
            i3 = 18;
        } else {
            i3 = 12;
        }
        meu meuVar = new meu(BaseApplication.getContext(), i3);
        meh.c(BaseApplication.getContext()).b(meuVar);
        JSONObject jSONObject = new JSONObject();
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "updateUserTaskStatus getCurrentTimezone=", d2);
        try {
            jSONObject.put("timeZone", d2);
            jSONObject.put(ParsedFieldTag.KAKA_TASK_SCENARIO, i);
            jSONObject.put(ParsedFieldTag.KAKA_TASKS, d(i3, list));
        } catch (JSONException e2) {
            LogUtil.b("PLGACHIEVE_AchieveKakaDataManager", "JSONException  ", e2.getMessage());
        }
        b.c(i3, jSONObject);
        try {
            meuVar.a(PreConnectManager.CONNECT_INTERNAL, TimeUnit.MILLISECONDS);
        } catch (InterruptedException unused) {
            LogUtil.b("PLGACHIEVE_AchieveKakaDataManager", "AchieveKakaObserver.await(): catch a InterruptedException");
        }
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "updateKakaTaskStatus success");
        if (meuVar.b() instanceof mde) {
            mde mdeVar = (mde) meuVar.b();
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "kakaUpdateReturnBody == ", mdeVar.toString());
            return mdeVar;
        }
        LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "updateKakaTaskStatus error ");
        return null;
    }

    private JSONArray d(int i, List<mdg> list) {
        JSONArray jSONArray = new JSONArray();
        try {
            for (mdg mdgVar : list) {
                JSONObject jSONObject = new JSONObject();
                if (i == 12) {
                    jSONObject.put(ParsedFieldTag.KAKA_TASK_UUID, mdgVar.d());
                } else {
                    jSONObject.put("taskId", mdgVar.d());
                    jSONObject.put(ParsedFieldTag.KAKA_VALUE_TYPE, mdgVar.a());
                    jSONObject.put("timeZone", mdgVar.c());
                }
                jSONObject.put("status", mdgVar.b());
                jSONObject.put("productId", mdgVar.e());
                jSONArray.put(jSONObject);
            }
        } catch (JSONException e2) {
            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "JSONException", e2.getMessage());
        }
        return jSONArray;
    }

    private int a(String str) {
        int e2 = nsn.e(str);
        return KakaConstants.TASK_ID_TO_NEW_RULE_ID.containsKey(Integer.valueOf(e2)) ? KakaConstants.TASK_ID_TO_NEW_RULE_ID.get(Integer.valueOf(e2)).intValue() : e2;
    }

    public void j(final CountDownLatch countDownLatch) {
        PluginAchieveAdapter adapter = mcv.d(this.g).getAdapter();
        if (adapter == null) {
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "updateSleepTask pluginAchieveAdapter is null");
            mle.b(countDownLatch);
            return;
        }
        long n = jec.n(new Date()) * 1000;
        long j = n - 14400000;
        long j2 = 72000000 + n;
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "updateSleepTask startTime = ", Long.valueOf(j), " endTime = ", Long.valueOf(j2));
        adapter.getLatestSleepDatas(j, j2, new AchieveCallback() { // from class: mer.5
            @Override // com.huawei.pluginachievement.impl.AchieveCallback
            public void onResponse(int i, Object obj) {
                mer.this.e(countDownLatch, (CountDownLatch) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> void e(CountDownLatch countDownLatch, T t) {
        if (t instanceof List) {
            List list = (List) t;
            if (koq.b(list)) {
                LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "sleep data is empty! ");
                mle.b(countDownLatch);
                return;
            }
            HiHealthData hiHealthData = (HiHealthData) list.get(0);
            LogUtil.c("PLGACHIEVE_AchieveKakaDataManager", "sleep data == ", hiHealthData);
            long startTime = hiHealthData.getStartTime();
            if (mle.a(startTime, System.currentTimeMillis())) {
                LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "today have no sleep data. startTime ", Long.valueOf(startTime));
                mle.b(countDownLatch);
                return;
            }
            int i = hiHealthData.getInt("core_sleep_shallow_key");
            int i2 = hiHealthData.getInt("core_sleep_deep_key");
            int i3 = hiHealthData.getInt("core_sleep_wake_dream_key");
            int i4 = hiHealthData.getInt("core_sleep_day_sleep_time_key");
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "lightSum:", Integer.valueOf(i), ", deepSum:", Integer.valueOf(i2), ", dreamSum:", Integer.valueOf(i3), ", noonSum:", Integer.valueOf(i4), ", StartTime:", Long.valueOf(hiHealthData.getStartTime()));
            int i5 = i + i2 + i3 + i4;
            if (i5 == 0) {
                int i6 = hiHealthData.getInt("sleep_deep_key");
                int i7 = hiHealthData.getInt("sleep_shallow_key");
                int i8 = (i6 + i7) / 60;
                LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "updateSleepTask lightSum:", Integer.valueOf(i7), ", deepSum:", Integer.valueOf(i6), ", sleepSum:", Integer.valueOf(i8));
                i5 = i8;
            }
            if (i5 > 0) {
                HashMap hashMap = new HashMap(2);
                hashMap.put(KakaConstants.SLEEP_DURATION, Integer.valueOf(i5));
                c(String.valueOf(30002), hashMap);
            }
        } else {
            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "getSleepReport data is not correct");
        }
        mle.b(countDownLatch);
    }

    public void e(CountDownLatch countDownLatch) {
        HashMap hashMap = new HashMap();
        hashMap.put(KakaConstants.FITNESS_DURATION, 1000);
        c(String.valueOf(KakaConstants.TASK_FITNESS_BEHAVIOR), hashMap);
        mle.b(countDownLatch);
    }

    public void a(final CountDownLatch countDownLatch, final int i) {
        PluginAchieveAdapter adapter = mcv.d(this.g).getAdapter();
        if (adapter == null) {
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "checkHealthMeasureRecordTask pluginAchieveAdapter is null");
            mle.b(countDownLatch);
        } else {
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "checkHealthMeasureRecordTask dataTypeId = ", Integer.valueOf(i));
            adapter.getLatestMesureDatas(HiDateUtil.t(System.currentTimeMillis()), HiDateUtil.f(System.currentTimeMillis()), i, new AchieveCallback() { // from class: mer.3
                @Override // com.huawei.pluginachievement.impl.AchieveCallback
                public void onResponse(int i2, Object obj) {
                    if (obj instanceof List) {
                        List list = (List) obj;
                        if (!koq.b(list)) {
                            mer.this.a(i, (List<HiHealthData>) list);
                        } else {
                            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "checkHealthMeasureRecordTask measureList is empty");
                            mle.b(countDownLatch);
                            return;
                        }
                    } else {
                        LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "checkHealthMeasureRecordTask data is not correct");
                    }
                    mle.b(countDownLatch);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, List<HiHealthData> list) {
        HiHealthData hiHealthData;
        Iterator<HiHealthData> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                hiHealthData = null;
                break;
            }
            hiHealthData = it.next();
            if (hiHealthData != null && c(hiHealthData, i)) {
                LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "checkHealthMeasureRecordTask has record, dataTypeId = ", Integer.valueOf(i));
                break;
            }
        }
        if (hiHealthData == null) {
            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "checkHealthMeasureRecordTask has no record, dataTypeId = ", Integer.valueOf(i));
            return;
        }
        int i2 = i != 2103 ? i != 10006 ? i != 10001 ? i != 10002 ? 0 : OnRegisterSkinAttrsListener.REGISTER_BY_SCAN : 30004 : 30005 : 30006;
        if (i2 != 0) {
            e(String.valueOf(i2), (Map<String, Object>) null);
        }
    }

    private boolean c(HiHealthData hiHealthData, int i) {
        if (!mle.a(hiHealthData.getStartTime())) {
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "isMeasureDataInvalid not today data");
            return false;
        }
        int i2 = hiHealthData.getInt("trackdata_deviceType");
        String string = hiHealthData.getString("device_uniquecode");
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "isMeasureDataInvalid dataType=", Integer.valueOf(i2), " uniqueId= ", string);
        if (i2 <= 1 || i2 == 10001 || "-1".equals(string)) {
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "isMeasureDataInvalid is input data, dataType=", Integer.valueOf(i2));
            return false;
        }
        String string2 = hiHealthData.getString("metadata");
        if (i != 10006 || !"-1".equals(string2)) {
            return true;
        }
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "isMeasureDataInvalid is unclaimed data");
        return false;
    }

    public void d(final CountDownLatch countDownLatch) {
        PluginAchieveAdapter adapter = mcv.d(this.g).getAdapter();
        if (adapter == null) {
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "checkEcgMesureTask pluginAchieveAdapter is null");
            mle.b(countDownLatch);
        } else {
            adapter.getLatestMesureDatas(HiDateUtil.t(System.currentTimeMillis()), HiDateUtil.f(System.currentTimeMillis()), 31002, new AchieveCallback() { // from class: mer.9
                @Override // com.huawei.pluginachievement.impl.AchieveCallback
                public void onResponse(int i, Object obj) {
                    if (obj instanceof List) {
                        List list = (List) obj;
                        if (koq.b(list)) {
                            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "checkEcgMesureTask oxygenMeasureList is empty");
                            mle.b(countDownLatch);
                            return;
                        }
                        HiHealthData hiHealthData = (HiHealthData) list.get(0);
                        if (hiHealthData == null) {
                            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "checkEcgMesureTask healthData is null");
                            mle.b(countDownLatch);
                            return;
                        } else if (mle.a(hiHealthData.getStartTime())) {
                            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "checkEcgMesureTask has record");
                            mer.this.e(String.valueOf(30010), (Map<String, Object>) null);
                        }
                    } else {
                        LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "checkEcgMesureTask data is not correct");
                    }
                    mle.b(countDownLatch);
                }
            });
        }
    }

    public void b(final CountDownLatch countDownLatch) {
        PluginAchieveAdapter adapter = mcv.d(this.g).getAdapter();
        if (adapter == null) {
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "checkPressureDetectionTask pluginAchieveAdapter is null");
            mle.b(countDownLatch);
        } else {
            adapter.getLatestMesureDatas(0L, System.currentTimeMillis(), 2034, new AchieveCallback() { // from class: mer.8
                @Override // com.huawei.pluginachievement.impl.AchieveCallback
                public void onResponse(int i, Object obj) {
                    LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "checkPressureDetectionTask errCode = ", Integer.valueOf(i));
                    boolean unused = mer.d = false;
                    if (obj instanceof List) {
                        List list = (List) obj;
                        if (koq.b(list)) {
                            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "checkPressureDetectionTask list is empty");
                            mle.b(countDownLatch);
                            return;
                        }
                        HiHealthData hiHealthData = (HiHealthData) list.get(0);
                        if (hiHealthData != null) {
                            boolean unused2 = mer.d = true;
                            int intValue = hiHealthData.getIntValue();
                            if (intValue <= 0 || intValue >= 100) {
                                LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "checkPressureDetectionTask is not stressData,stressScore= ", Integer.valueOf(intValue));
                                mle.b(countDownLatch);
                                return;
                            } else if (mle.a(hiHealthData.getStartTime())) {
                                LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "checkPressureDetectionTask has record");
                                mer.this.e(String.valueOf(30012), (Map<String, Object>) null);
                            }
                        } else {
                            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "checkPressureDetectionTask healthData is null");
                            mle.b(countDownLatch);
                            return;
                        }
                    } else {
                        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "checkPressureDetectionTask has no data");
                    }
                    mle.b(countDownLatch);
                }
            });
        }
    }

    public void a(final CountDownLatch countDownLatch) {
        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "checkBreatheTask start");
        long t = HiDateUtil.t(System.currentTimeMillis());
        long f = HiDateUtil.f(System.currentTimeMillis());
        PluginAchieveAdapter adapter = mcv.d(this.g).getAdapter();
        if (adapter == null) {
            LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "checkBreatheTask pluginAchieveAdapter is null");
            mle.b(countDownLatch);
        } else {
            adapter.getLatestMesureDatas(t, f, DicDataTypeUtil.DataType.BREATH_TRAIN_SET.value(), new AchieveCallback() { // from class: mer.10
                @Override // com.huawei.pluginachievement.impl.AchieveCallback
                public void onResponse(int i, Object obj) {
                    if (obj instanceof List) {
                        List list = (List) obj;
                        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "checkBreatheTask onResult errorCode ", Integer.valueOf(i));
                        if (koq.b(list)) {
                            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "checkBreatheTask dataList is empty");
                            mle.b(countDownLatch);
                            return;
                        }
                        HiHealthData hiHealthData = (HiHealthData) list.get(0);
                        if (hiHealthData == null) {
                            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "checkBreatheTask dataList is empty");
                            mle.b(countDownLatch);
                            return;
                        } else {
                            LogUtil.c("PLGACHIEVE_AchieveKakaDataManager", "checkBreatheTask onResult hiHealthData = ", hiHealthData.toString());
                            if (mle.a(hiHealthData.getStartTime())) {
                                LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "checkBreathTask has record");
                                mer.this.e(String.valueOf(30015), (Map<String, Object>) null);
                            }
                            mle.b(countDownLatch);
                        }
                    } else {
                        LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "checkBreatheTask data is not correct");
                    }
                    mle.b(countDownLatch);
                }
            });
        }
    }

    public void c(CountDownLatch countDownLatch) {
        HashMap hashMap = new HashMap(2);
        hashMap.put(ParsedFieldTag.KAKA_TASK_RULE, String.valueOf(20007));
        hashMap.put(ParsedFieldTag.KAKA_TASK_SCENARIO, String.valueOf(1));
        mcz d2 = b.d(12, hashMap);
        if (d2 instanceof mdf) {
            d((mdf) d2, countDownLatch);
        }
    }

    public void c(final mdf mdfVar) {
        if (mdfVar == null) {
            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "taskInfo is empty.");
        } else if (!String.valueOf(0).equals(mdfVar.p())) {
            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "taskInfo is finished, mCourseId == ", mdfVar.a());
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: mer.7
                @Override // java.lang.Runnable
                public void run() {
                    LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "mCourseId == ", mdfVar.a());
                    mer.this.d(mdfVar, (CountDownLatch) null);
                }
            });
        }
    }

    public void d(final mdf mdfVar, final CountDownLatch countDownLatch) {
        if (mdfVar == null || TextUtils.isEmpty(mdfVar.a())) {
            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "checkCourseFinished workoutId is empty.");
            mle.b(countDownLatch);
            return;
        }
        long t = HiDateUtil.t(System.currentTimeMillis());
        long f = HiDateUtil.f(System.currentTimeMillis());
        RecordApi recordApi = (RecordApi) Services.a("CoursePlanService", RecordApi.class);
        if (recordApi == null) {
            LogUtil.h("PLGACHIEVE_AchieveKakaDataManager", "checkCourseFinished recordApi is null.");
            mle.b(countDownLatch);
        } else {
            final String a2 = mdfVar.a();
            recordApi.acquireExerciseFinished(t, f, a2, new ResultCallback() { // from class: mer.6
                @Override // com.huawei.health.suggestion.ResultCallback
                public void onResult(int i, Object obj) {
                    LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "acquireExerciseFinished errorCode = ", Integer.valueOf(i));
                    if (obj instanceof Boolean) {
                        boolean booleanValue = ((Boolean) obj).booleanValue();
                        LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "checkCourseFinished courseId == ", a2, " finished = ", Boolean.valueOf(booleanValue));
                        if (booleanValue) {
                            HashMap hashMap = new HashMap(2);
                            hashMap.put(ParsedFieldTag.TASK_KEY, a2);
                            mer.this.e(String.valueOf(mdfVar.ag()), hashMap);
                        }
                    }
                    mle.b(countDownLatch);
                }
            });
        }
    }

    public void h() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: mer.12
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("PLGACHIEVE_AchieveKakaDataManager", "updateEachTaskStatus");
                mer.this.j(null);
                mer.this.e((CountDownLatch) null);
                mer.this.a((CountDownLatch) null, 2103);
                mer.this.a((CountDownLatch) null, 10002);
                mer.this.a((CountDownLatch) null, 10001);
                mer.this.a((CountDownLatch) null, 10006);
                mer.this.d((CountDownLatch) null);
                mer.this.b((CountDownLatch) null);
                mer.this.a((CountDownLatch) null);
            }
        });
    }

    private int[] d(int i) {
        int i2;
        if (i == 40012) {
            i2 = 40011;
        } else {
            if (i != 30009) {
                return new int[]{i};
            }
            i2 = 30018;
        }
        return new int[]{i, i2};
    }
}
