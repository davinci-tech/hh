package defpackage;

import android.text.TextUtils;
import com.huawei.hihealth.HiGoalInfo;
import com.huawei.hihealth.HiSampleConfig;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginhealthzone.interactors.HealthZonePushReceiver;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes.dex */
public class nip {

    /* renamed from: a, reason: collision with root package name */
    private static int f15309a;
    public static final HashMap<String, String> b;
    private static final String[] c;
    private static final String[] d;
    private static AtomicBoolean e;

    static {
        HashMap<String, String> hashMap = new HashMap<>();
        b = hashMap;
        f15309a = 10000;
        d = new String[]{"10", "15", HealthZonePushReceiver.DEAUTH_EVENT_NOTIFY, "25", HealthZonePushReceiver.SLEEP_SCORE_NOTIFY, HealthZonePushReceiver.BODY_TEMPERATURE_LOW_NOTIFY, HealthZonePushReceiver.FAMILY_CARE_NOTIFY, "45", "50", "55", "60", "65", "70", "75", "80", "85", "90"};
        c = new String[]{"6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"};
        e = new AtomicBoolean(true);
        hashMap.put("active_sp_key_achieved_goal_step", "900200006");
        hashMap.put("active_sp_key_achieved_goal_calorie", "900200007");
        hashMap.put("active_sp_key_achieved_goal_intensity", "900200008");
        hashMap.put("active_sp_key_achieved_goal_stand", "900200009");
    }

    public static void e(String str, int i) {
        a(str, i, null);
    }

    public static void a(final String str, final int i, final ResponseCallback<njc> responseCallback) {
        d(str, new IBaseResponseCallback() { // from class: nim
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i2, Object obj) {
                nip.d(str, i, responseCallback, i2, obj);
            }
        });
    }

    static /* synthetic */ void d(String str, int i, ResponseCallback responseCallback, int i2, Object obj) {
        if (!(obj instanceof njc)) {
            LogUtil.a("SCUI_ActiveGoalUtil", "enter first time save model key ", str, " goalVal ", Integer.valueOf(i));
            d(str, b(i, System.currentTimeMillis()), (ResponseCallback<njc>) responseCallback);
            return;
        }
        njc njcVar = (njc) obj;
        if (njcVar.e() != i) {
            LogUtil.a("SCUI_ActiveGoalUtil", "enter not the first time save model key ", str, " goalVal ", Integer.valueOf(i), " activeGoalModel is ", njcVar);
            d(str, b(i, System.currentTimeMillis()), (ResponseCallback<njc>) responseCallback);
        }
    }

    public static void d(final String str, final IBaseResponseCallback iBaseResponseCallback) {
        if (TextUtils.isEmpty(str) || iBaseResponseCallback == null) {
            LogUtil.h("SCUI_ActiveGoalUtil", "getActiveGoalModel key or callback is null");
            return;
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(str);
        a(arrayList, new IBaseResponseCallback() { // from class: nis
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                nip.b(str, iBaseResponseCallback, i, obj);
            }
        });
    }

    static /* synthetic */ void b(String str, IBaseResponseCallback iBaseResponseCallback, int i, Object obj) {
        if (obj instanceof HashMap) {
            HashMap hashMap = (HashMap) obj;
            if (hashMap.containsKey(str)) {
                iBaseResponseCallback.d(0, hashMap.get(str));
                return;
            } else {
                iBaseResponseCallback.d(0, null);
                return;
            }
        }
        iBaseResponseCallback.d(0, null);
    }

    private static void d(final String str, final njc njcVar, final ResponseCallback<njc> responseCallback) {
        jdx.b(new Runnable() { // from class: nip.5
            @Override // java.lang.Runnable
            public void run() {
                LogUtil.a("SCUI_ActiveGoalUtil", "saveActiveGoalModel start save model and sync cloud");
                nip.e(str, njcVar);
                nip.d(njcVar, str, (ResponseCallback<njc>) responseCallback);
                nip.g();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(String str, njc njcVar) {
        if ("900200006".equals(str)) {
            nit.a(njcVar.e(), new IBaseResponseCallback() { // from class: nip.3
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a("SCUI_ActiveGoalUtil", "saveMotionGoal end errorCode ", Integer.valueOf(i));
                }
            });
        }
    }

    public static void a(List<String> list, final IBaseResponseCallback iBaseResponseCallback) {
        if (koq.b(list) || iBaseResponseCallback == null) {
            LogUtil.h("SCUI_ActiveGoalUtil", "getActiveGoals keys or callback is null");
            return;
        }
        final HashMap hashMap = new HashMap(16);
        ReleaseLogUtil.e("SCUI_ActiveGoalUtil", "getActiveGoals configIdList ", list.toString());
        njj.d("9002", list, new HiDataReadResultListener() { // from class: nip.1
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                ReleaseLogUtil.e("SCUI_ActiveGoalUtil", "onResult errorCode: ", Integer.valueOf(i), ", data: ", obj);
                if (!koq.e(obj, HiSampleConfig.class)) {
                    LogUtil.h("SCUI_ActiveGoalUtil", "list getActiveGoals isListTypeMatch false ");
                    nip.e(IBaseResponseCallback.this);
                    return;
                }
                List<HiSampleConfig> list2 = (List) obj;
                if (koq.b(list2)) {
                    LogUtil.h("SCUI_ActiveGoalUtil", "list getActiveGoals list is empty ");
                    nip.e(IBaseResponseCallback.this);
                    return;
                }
                for (HiSampleConfig hiSampleConfig : list2) {
                    if (hiSampleConfig != null) {
                        String configData = hiSampleConfig.getConfigData();
                        if (TextUtils.isEmpty(configData)) {
                            hashMap.put(hiSampleConfig.getConfigId(), Integer.valueOf(nip.d(hiSampleConfig.getConfigId())));
                        } else {
                            hashMap.put(hiSampleConfig.getConfigId(), Integer.valueOf(((njc) HiJsonUtil.e(configData, njc.class)).e()));
                        }
                    }
                }
                LogUtil.a("SCUI_ActiveGoalUtil", "list getActiveGoals return ", hashMap);
                IBaseResponseCallback.this.d(0, hashMap);
            }
        });
    }

    public static int e(HashMap<String, Integer> hashMap, String str, int i) {
        Integer num;
        return (!hashMap.containsKey(str) || (num = hashMap.get(str)) == null) ? i : num.intValue();
    }

    private static void c(final List<HiUserPreference> list, final List<String> list2) {
        LogUtil.a("SCUI_ActiveGoalUtil", "initSampleConfigGoal start ", Boolean.valueOf(!e.get()));
        if (e.get()) {
            if (!SharedPreferenceManager.a("HiHealthService", "pullAllStatus", false)) {
                LogUtil.a("SCUI_ActiveGoalUtil", "data is not sync finish.");
            } else {
                d(new IBaseResponseCallback() { // from class: nip.2
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public void d(int i, Object obj) {
                        LogUtil.a("SCUI_ActiveGoalUtil", "initSampleConfigGoal motionGoal end ");
                        if (koq.b(list)) {
                            nip.d(nip.b(nip.f15309a, 1L), "900200006", (ResponseCallback<njc>) null);
                            nip.d(nip.b(25, 1L), "900200008", (ResponseCallback<njc>) null);
                            nip.d(nip.b(12, 1L), "900200009", (ResponseCallback<njc>) null);
                            nip.d(nip.b(270000, 1L), "900200007", (ResponseCallback<njc>) null);
                            nip.e.set(false);
                            LogUtil.a("SCUI_ActiveGoalUtil", "initSampleConfigGoal preferencesList is null, write data then end ");
                            return;
                        }
                        list2.add("active_sp_key_achieved_goal_calorie");
                        for (String str : list2) {
                            nip.d(nip.b(nip.c(0, str), 1L), nip.b.get(str), (ResponseCallback<njc>) null);
                        }
                        for (HiUserPreference hiUserPreference : list) {
                            if (hiUserPreference != null) {
                                nip.d(nip.b(((nin) HiJsonUtil.e(hiUserPreference.getValue(), nin.class)).a(), 1L), nip.b.get(hiUserPreference.getKey()), (ResponseCallback<njc>) null);
                            }
                        }
                        nip.e.set(false);
                    }
                });
            }
        }
    }

    public static int d(String str) {
        if ("900200006".equals(str) || "active_sp_key_achieved_goal_step".equals(str)) {
            return f15309a;
        }
        if ("900200008".equals(str) || "active_sp_key_achieved_goal_intensity".equals(str)) {
            return 25;
        }
        if ("900200009".equals(str) || "active_sp_key_achieved_goal_stand".equals(str)) {
            return 12;
        }
        return ("900200007".equals(str) || "active_sp_key_achieved_goal_calorie".equals(str)) ? 270000 : 0;
    }

    private static void d(final IBaseResponseCallback iBaseResponseCallback) {
        HiHealthNativeApi.a(BaseApplication.getContext()).fetchGoalInfo(0, 2, new HiCommonListener() { // from class: nip.4
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                List list;
                if (koq.e(obj, HiGoalInfo.class)) {
                    list = (List) obj;
                } else {
                    LogUtil.h("SCUI_ActiveGoalUtil", "data is not list<HiGoalInfo> type");
                    list = null;
                }
                if (koq.c(list) && list.get(0) != null) {
                    int unused = nip.f15309a = (int) ((HiGoalInfo) list.get(0)).getGoalValue();
                }
                LogUtil.a("SCUI_ActiveGoalUtil", "getStepGoalByMotionGoal StepMotionGoal ", Integer.valueOf(nip.f15309a));
                IBaseResponseCallback.this.d(0, Integer.valueOf(nip.f15309a));
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                IBaseResponseCallback.this.d(-1, Integer.valueOf(nip.f15309a));
            }
        });
    }

    public static HashMap<String, Integer> e(IBaseResponseCallback iBaseResponseCallback) {
        ArrayList arrayList = new ArrayList(3);
        arrayList.add("active_sp_key_achieved_goal_step");
        arrayList.add("active_sp_key_achieved_goal_intensity");
        arrayList.add("active_sp_key_achieved_goal_stand");
        List<HiUserPreference> userPreferenceList = HiHealthManager.d(BaseApplication.getContext()).getUserPreferenceList(arrayList);
        c(userPreferenceList, arrayList);
        HashMap<String, Integer> b2 = b();
        for (HiUserPreference hiUserPreference : userPreferenceList) {
            if (hiUserPreference != null) {
                b2.put(b.get(hiUserPreference.getKey()), Integer.valueOf(c(((nin) HiJsonUtil.e(hiUserPreference.getValue(), nin.class)).a(), hiUserPreference.getKey())));
            }
        }
        iBaseResponseCallback.d(0, b2);
        LogUtil.a("SCUI_ActiveGoalUtil", "getUserPreferenceGoal map is ", b2.toString());
        return b2;
    }

    private static HashMap<String, Integer> b() {
        HashMap<String, Integer> hashMap = new HashMap<>(4);
        hashMap.put("900200006", 10000);
        hashMap.put("900200009", 12);
        hashMap.put("900200008", 25);
        hashMap.put("900200007", 270000);
        return hashMap;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int c(int i, String str) {
        return i == 0 ? d(str) : i;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static njc b(int i, long j) {
        njc njcVar = new njc();
        njcVar.e(i);
        njcVar.c(j);
        return njcVar;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(final njc njcVar, String str, final ResponseCallback<njc> responseCallback) {
        ReleaseLogUtil.e("SCUI_ActiveGoalUtil", "saveSampleConfig enter key ", str, " goal ", njcVar.toString());
        njj.a("9002", str, HiJsonUtil.e(njcVar), new HiDataOperateListener() { // from class: nip.8
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                LogUtil.a("SCUI_ActiveGoalUtil", "saveSampleConfig errorCode: ", Integer.valueOf(i), ", object: ", obj);
                ResponseCallback responseCallback2 = ResponseCallback.this;
                if (responseCallback2 != null) {
                    responseCallback2.onResponse(i, njcVar);
                }
            }
        }, njcVar.d());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void g() {
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncDataType(900000000);
        hiSyncOption.setSyncMethod(2);
        HiHealthNativeApi.a(BaseApplication.getContext()).synCloud(hiSyncOption, null);
    }

    private static String[] e() {
        ArrayList arrayList = new ArrayList();
        for (int i = 50; i <= 5000; i += 10) {
            arrayList.add(String.valueOf(i));
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static String[] a(int i) {
        if (i == 0) {
            return e();
        }
        if (i == 1) {
            return d;
        }
        if (i == 2) {
            return c;
        }
        LogUtil.a("SCUI_ActiveGoalUtil", "getPickArray ERROR.");
        return new String[0];
    }

    public static int c(int i) {
        if (i == 0) {
            return 270;
        }
        if (i == 1) {
            return 25;
        }
        if (i == 2) {
            return 12;
        }
        LogUtil.h("SCUI_ActiveGoalUtil", "getPickArray ERROR. type = ", Integer.valueOf(i));
        return 0;
    }

    public static int c(int i, String[] strArr) {
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i3 >= strArr.length - 1) {
                break;
            }
            if (i > CommonUtil.m(com.huawei.haf.application.BaseApplication.e(), strArr[i3])) {
                int i4 = i3 + 1;
                if (i <= CommonUtil.m(com.huawei.haf.application.BaseApplication.e(), strArr[i4])) {
                    i2 = i4;
                    break;
                }
            }
            i3++;
        }
        LogUtil.a("SCUI_ActiveGoalUtil", "getDefaultPosition position is ", Integer.valueOf(i2));
        return i2;
    }
}
