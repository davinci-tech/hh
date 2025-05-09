package defpackage;

import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseArray;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hihealth.HiDataDeleteOption;
import com.huawei.hihealth.HiDataInsertOption;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiTimeInterval;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataOperateListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.health.weight.callback.FastingLiteCbk;
import defpackage.qvf;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: classes.dex */
public class qve {
    private static final List<Integer> c = Arrays.asList(1, 2, 3, 4, 5, 6, 7);

    public static qui c() {
        qui e = e("custom.weight_fasting_lite_plan");
        qui e2 = e("custom.weight_fasting_lite_setting");
        if (e == null || (e2 != null && e2.c())) {
            e = e2;
        }
        if (e != null) {
            sdk.c().b(e.c() && e.a());
        }
        return e;
    }

    public static qui e() {
        qui quiVar;
        qui quiVar2;
        String a2 = rag.a("custom.weight_fasting_lite_plan");
        if (TextUtils.isEmpty(a2)) {
            quiVar = e("custom.weight_fasting_lite_plan");
            rag.c("custom.weight_fasting_lite_plan", HiJsonUtil.e(quiVar));
        } else {
            quiVar = (qui) HiJsonUtil.e(a2, qui.class);
        }
        String a3 = rag.a("custom.weight_fasting_lite_setting");
        if (TextUtils.isEmpty(a3)) {
            quiVar2 = e("custom.weight_fasting_lite_setting");
            rag.c("custom.weight_fasting_lite_setting", HiJsonUtil.e(quiVar2));
        } else {
            quiVar2 = (qui) HiJsonUtil.e(a3, qui.class);
        }
        if (quiVar == null || (quiVar2 != null && quiVar2.c())) {
            quiVar = quiVar2;
        }
        if (quiVar != null) {
            sdk.c().b(quiVar.c() && quiVar.a());
        }
        return quiVar;
    }

    public static qui e(final String str) {
        final AtomicReference atomicReference = new AtomicReference();
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        ThreadPoolManager.d().execute(new Runnable() { // from class: qve.2
            @Override // java.lang.Runnable
            public void run() {
                HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference(str);
                if (userPreference == null || TextUtils.isEmpty(userPreference.getValue())) {
                    LogUtil.h("FastingLiteTaskHelper", "getAppSettingByKey hiUserPreference is null, key = ", str);
                    atomicReference.set(null);
                    countDownLatch.countDown();
                } else {
                    try {
                        LogUtil.a("FastingLiteTaskHelper", "getAppSettingByKey key = ", str, ", value = ", userPreference.getValue());
                        atomicReference.set((qui) HiJsonUtil.e(userPreference.getValue(), qui.class));
                    } catch (JsonSyntaxException e) {
                        LogUtil.b("FastingLiteTaskHelper", "getAppSettingByKey jsonException ", ExceptionUtils.d(e));
                        atomicReference.set(null);
                    }
                    countDownLatch.countDown();
                }
            }
        });
        try {
            LogUtil.a("FastingLiteTaskHelper", "getAppSettingByKey await ", Boolean.valueOf(countDownLatch.await(1L, TimeUnit.SECONDS)));
        } catch (InterruptedException e) {
            LogUtil.b("FastingLiteTaskHelper", "getAppSettingByKey exception ", ExceptionUtils.d(e));
        }
        return (qui) atomicReference.get();
    }

    public static void a(String str) {
        HiUserPreference hiUserPreference = new HiUserPreference();
        hiUserPreference.setKey(str);
        hiUserPreference.setValue("");
        LogUtil.a("FastingLiteTaskHelper", "clear currentTask isSuccess = ", Boolean.valueOf(HiHealthManager.d(BaseApplication.getContext()).setUserPreference(hiUserPreference)), ", ", str);
    }

    public static long e(qvc qvcVar) {
        if (qvcVar != null && qvcVar.j() == 1 && qvcVar.f() > 0) {
            long c2 = DateFormatUtil.c(qvcVar.f());
            Pair<Integer, Integer> dJb_ = qvcVar.dJb_();
            if (c2 != 0 && dJb_ != null) {
                return jdl.e(c2, ((Integer) dJb_.first).intValue(), ((Integer) dJb_.second).intValue());
            }
        }
        return -1L;
    }

    public static ArrayList<Integer> c(qvc qvcVar) {
        ArrayList<Integer> arrayList = new ArrayList<>(7);
        if (qvcVar != null && !TextUtils.isEmpty(qvcVar.h())) {
            for (String str : qvcVar.h().split(",")) {
                if (!c.contains(Integer.valueOf(nsn.e(str)))) {
                    return new ArrayList<>(7);
                }
                arrayList.add(Integer.valueOf(nsn.e(str)));
            }
        }
        return arrayList;
    }

    public static void a() {
        qui e = e("custom.weight_fasting_lite_plan");
        if (e != null) {
            e.a(false);
            HiUserPreference hiUserPreference = new HiUserPreference();
            hiUserPreference.setKey("custom.weight_fasting_lite_plan");
            String e2 = HiJsonUtil.e(e);
            hiUserPreference.setValue(e2);
            boolean userPreference = HiHealthManager.d(BaseApplication.getContext()).setUserPreference(hiUserPreference);
            if (userPreference) {
                rag.c("custom.weight_fasting_lite_plan", e2);
            }
            LogUtil.a("FastingLiteTaskHelper", "resetAppSetting new isSuccess ", Boolean.valueOf(userPreference));
        }
        qui e3 = e("custom.weight_fasting_lite_setting");
        if (e3 == null || e3.d() == null || e3.d().b() == null) {
            return;
        }
        e3.a(false);
        e3.d().b().a(0);
        HiUserPreference hiUserPreference2 = new HiUserPreference();
        hiUserPreference2.setKey("custom.weight_fasting_lite_setting");
        String e4 = HiJsonUtil.e(e3);
        hiUserPreference2.setValue(e4);
        boolean userPreference2 = HiHealthManager.d(BaseApplication.getContext()).setUserPreference(hiUserPreference2);
        if (userPreference2) {
            rag.c("custom.weight_fasting_lite_setting", e4);
        }
        LogUtil.a("FastingLiteTaskHelper", "resetAppSetting old isSuccess ", Boolean.valueOf(userPreference2));
    }

    public static void a(HiDataReadOption hiDataReadOption, final FastingLiteCbk<List<qva>> fastingLiteCbk) {
        if (fastingLiteCbk == null) {
            LogUtil.h("FastingLiteTaskHelper", "callback is null");
        } else {
            HiHealthNativeApi.a(com.huawei.haf.application.BaseApplication.e()).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: qve.4
                @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                public void onResultIntent(int i, Object obj, int i2, int i3) {
                }

                @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                public void onResult(Object obj, int i, int i2) {
                    if (obj instanceof SparseArray) {
                        SparseArray sparseArray = (SparseArray) obj;
                        if (sparseArray.size() > 0) {
                            if (!(sparseArray.get(DicDataTypeUtil.DataType.LIGHT_FASTING.value()) instanceof List)) {
                                FastingLiteCbk.this.onSuccess(new ArrayList(16));
                                return;
                            }
                            ArrayList arrayList = new ArrayList(16);
                            Iterator it = ((List) sparseArray.get(DicDataTypeUtil.DataType.LIGHT_FASTING.value())).iterator();
                            while (it.hasNext()) {
                                try {
                                    arrayList.add((qva) HiJsonUtil.e(((HiHealthData) it.next()).getMetaData(), qva.class));
                                } catch (JsonSyntaxException unused) {
                                    LogUtil.b("FastingLiteTaskHelper", "parse task fail");
                                }
                            }
                            FastingLiteCbk.this.onSuccess(arrayList);
                            return;
                        }
                    }
                    FastingLiteCbk.this.onSuccess(new ArrayList(16));
                }
            });
        }
    }

    public static void c(qvf qvfVar) {
        HiUserPreference userPreference;
        if (qvfVar == null) {
            LogUtil.h("FastingLiteTaskHelper", "can not save empty taskJson");
            return;
        }
        qui e = e("custom.weight_fasting_lite_setting");
        HiUserPreference hiUserPreference = new HiUserPreference();
        if (e != null && e.c()) {
            hiUserPreference.setKey("custom.weight_fasting_lite_current_task");
            userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("custom.weight_fasting_lite_current_task");
        } else {
            hiUserPreference.setKey("custom.weight_fasting_lite_plan_task");
            userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("custom.weight_fasting_lite_plan_task");
        }
        qvf qvfVar2 = (userPreference == null || TextUtils.isEmpty(userPreference.getValue())) ? null : (qvf) new Gson().fromJson(userPreference.getValue(), qvf.class);
        if (qvfVar2 != null && qvfVar.toString().equals(qvfVar2.toString())) {
            LogUtil.a("FastingLiteTaskHelper", "saveCurrentTask newTask is equals originTask");
            return;
        }
        String json = new Gson().toJson(qvfVar);
        LogUtil.c("FastingLiteTaskHelper", "saveCurrentTask task json ", json);
        hiUserPreference.setValue(json);
        LogUtil.a("FastingLiteTaskHelper", "saveCurrentTaskToUserPreference isSuccess= ", Boolean.valueOf(HiHealthManager.d(BaseApplication.getContext()).setUserPreference(hiUserPreference)));
    }

    public static void c(qvf qvfVar, final IBaseResponseCallback iBaseResponseCallback) {
        if (qvfVar == null) {
            LogUtil.h("FastingLiteTaskHelper", "saveFastingLiteTask is null");
            if (iBaseResponseCallback != null) {
                iBaseResponseCallback.d(-1, "record is null");
                return;
            }
            return;
        }
        List<qvf.d> g = qvfVar.g();
        if (koq.b(g)) {
            LogUtil.a("FastingLiteTaskHelper", "saveFastingLitePhaseList fastingLiteWindows is empty");
            return;
        }
        List<HiHealthData> b = b(g);
        if (koq.b(b)) {
            LogUtil.a("FastingLiteTaskHelper", "saveFastingLitePhaseList healthDataList is empty");
            return;
        }
        HiDataInsertOption hiDataInsertOption = new HiDataInsertOption();
        hiDataInsertOption.setDatas(b);
        HiHealthNativeApi.a(com.huawei.haf.application.BaseApplication.e()).insertHiHealthData(hiDataInsertOption, new HiDataOperateListener() { // from class: qvg
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public final void onResult(int i, Object obj) {
                qve.b(IBaseResponseCallback.this, i, obj);
            }
        });
    }

    static /* synthetic */ void b(IBaseResponseCallback iBaseResponseCallback, int i, Object obj) {
        LogUtil.a("FastingLiteTaskHelper", "insert fastingLitePhase List ", Integer.valueOf(i));
        if (iBaseResponseCallback == null) {
            LogUtil.h("FastingLiteTaskHelper", "insert callback is null");
        } else {
            iBaseResponseCallback.d(i, obj);
        }
    }

    public static void d(qvf qvfVar, FastingLiteCbk<List<qvf.d>> fastingLiteCbk) {
        e(qvfVar.m(), System.currentTimeMillis(), true, fastingLiteCbk);
    }

    public static void e(final long j, long j2, final boolean z, final FastingLiteCbk<List<qvf.d>> fastingLiteCbk) {
        if (fastingLiteCbk == null) {
            LogUtil.h("FastingLiteTaskHelper", "callback is null");
            return;
        }
        int[] iArr = {DicDataTypeUtil.DataType.FASTING_LITE_PHASE_SET.value()};
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setType(iArr);
        hiDataReadOption.setSortOrder(0);
        hiDataReadOption.setStartTime(j);
        hiDataReadOption.setEndTime(j2);
        HiHealthNativeApi.a(com.huawei.haf.application.BaseApplication.e()).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: qve.5
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                if (obj instanceof SparseArray) {
                    SparseArray sparseArray = (SparseArray) obj;
                    if (sparseArray.size() > 0) {
                        if (!(sparseArray.get(DicDataTypeUtil.DataType.FASTING_LITE_PHASE_SET.value()) instanceof List)) {
                            FastingLiteCbk.this.onSuccess(new ArrayList(16));
                            return;
                        }
                        List list = (List) sparseArray.get(DicDataTypeUtil.DataType.FASTING_LITE_PHASE_SET.value());
                        if (!koq.b(list)) {
                            FastingLiteCbk.this.onSuccess(qve.a((List<HiHealthData>) list, j, z));
                            return;
                        } else {
                            FastingLiteCbk.this.onSuccess(new ArrayList(16));
                            return;
                        }
                    }
                }
                FastingLiteCbk.this.onSuccess(new ArrayList(16));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<qvf.d> a(List<HiHealthData> list, long j, boolean z) {
        ArrayList arrayList = new ArrayList();
        for (HiHealthData hiHealthData : list) {
            qvf.d a2 = a(arrayList, hiHealthData.getStartTime(), hiHealthData.getEndTime());
            if (hiHealthData.getType() == DicDataTypeUtil.DataType.FASTINGLITE_STARTTIME.value()) {
                a2.c((long) hiHealthData.getValue(), false);
            } else if (hiHealthData.getType() == DicDataTypeUtil.DataType.FASTINGLITE_ENDTIME.value()) {
                a2.d((long) hiHealthData.getValue(), false);
            } else if (hiHealthData.getType() == DicDataTypeUtil.DataType.NEXT_EAT_WINDOW_TIME.value()) {
                a2.b((long) hiHealthData.getValue(), false);
            } else if (hiHealthData.getType() == DicDataTypeUtil.DataType.FASTINGLITE_RECORD_ID.value()) {
                a2.a((long) hiHealthData.getValue(), false);
            } else if (hiHealthData.getType() == DicDataTypeUtil.DataType.FASTINGLITE_IS_EATING.value()) {
                a2.d(hiHealthData.getIntValue() == 1);
            } else {
                LogUtil.h("FastingLiteTaskHelper", "HiHealthData getType is Error");
            }
        }
        if (koq.b(arrayList)) {
            return arrayList;
        }
        Collections.sort(arrayList, new Comparator() { // from class: qvd
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                int compare;
                compare = Long.compare(((qvf.d) obj).f(), ((qvf.d) obj2).f());
                return compare;
            }
        });
        if (!z) {
            return arrayList;
        }
        ArrayList arrayList2 = new ArrayList();
        d(arrayList, arrayList2, j);
        return arrayList2;
    }

    private static void d(List<qvf.d> list, List<qvf.d> list2, long j) {
        ArrayList arrayList = new ArrayList();
        for (qvf.d dVar : list) {
            if (dVar.b() != j) {
                LogUtil.h("FastingLiteTaskHelper", "fillValidWindows currentWindow is not belong currentTask");
            } else if (dVar.f() < j || dVar.c() == 0 || dVar.a() == 0) {
                arrayList.add(dVar);
            } else if (koq.b(list2)) {
                list2.add(dVar);
            } else {
                qvf.d dVar2 = list2.get(list2.size() - 1);
                if (dVar2.c() == dVar.f() || dVar2.a() == dVar.f()) {
                    list2.add(dVar);
                } else {
                    arrayList.add(dVar);
                }
            }
        }
        d(arrayList);
    }

    private static qvf.d a(List<qvf.d> list, long j, long j2) {
        if (koq.b(list)) {
            return d(list, j, j2);
        }
        for (qvf.d dVar : list) {
            if (dVar.d() == j) {
                return dVar;
            }
        }
        return d(list, j, j2);
    }

    private static qvf.d d(List<qvf.d> list, long j, long j2) {
        qvf.d dVar = new qvf.d(j, j2);
        list.add(dVar);
        return dVar;
    }

    private static List<HiHealthData> b(List<qvf.d> list) {
        ArrayList arrayList = new ArrayList();
        for (qvf.d dVar : list) {
            if (!dVar.j()) {
                LogUtil.h("FastingLiteTaskHelper", "getHiHealthData window is not change");
            } else {
                HiHealthData hiHealthData = new HiHealthData();
                hiHealthData.setType(DicDataTypeUtil.DataType.FASTING_LITE_PHASE_SET.value());
                hiHealthData.setStartTime(dVar.d());
                hiHealthData.setEndTime(dVar.e());
                HashMap hashMap = new HashMap();
                hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.FASTINGLITE_STARTTIME.value()), Long.valueOf(dVar.f()));
                hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.FASTINGLITE_ENDTIME.value()), Long.valueOf(dVar.c()));
                hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.NEXT_EAT_WINDOW_TIME.value()), Long.valueOf(dVar.a()));
                hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.FASTINGLITE_RECORD_ID.value()), Long.valueOf(dVar.b()));
                hashMap.put(Integer.valueOf(DicDataTypeUtil.DataType.FASTINGLITE_IS_EATING.value()), Integer.valueOf(dVar.g() ? 1 : 0));
                hiHealthData.setFieldsValue(HiJsonUtil.e(hashMap));
                hiHealthData.setDeviceUuid("-1");
                arrayList.add(hiHealthData);
            }
        }
        return arrayList;
    }

    private static void d(List<qvf.d> list) {
        if (koq.b(list)) {
            LogUtil.h("FastingLiteTaskHelper", "deleteFastingLiteWindows errorWindows is empty");
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (qvf.d dVar : list) {
            HiTimeInterval hiTimeInterval = new HiTimeInterval();
            hiTimeInterval.setStartTime(dVar.d());
            hiTimeInterval.setEndTime(dVar.e());
            arrayList.add(hiTimeInterval);
        }
        HiDataDeleteOption hiDataDeleteOption = new HiDataDeleteOption();
        hiDataDeleteOption.setTimes(arrayList);
        hiDataDeleteOption.setTypes(new int[]{DicDataTypeUtil.DataType.FASTINGLITE_STARTTIME.value(), DicDataTypeUtil.DataType.FASTINGLITE_ENDTIME.value(), DicDataTypeUtil.DataType.NEXT_EAT_WINDOW_TIME.value(), DicDataTypeUtil.DataType.FASTINGLITE_RECORD_ID.value(), DicDataTypeUtil.DataType.FASTINGLITE_IS_EATING.value()});
        HiHealthNativeApi.a(com.huawei.haf.application.BaseApplication.e()).deleteHiHealthData(hiDataDeleteOption, new HiDataOperateListener() { // from class: qve.1
            @Override // com.huawei.hihealth.data.listener.HiDataOperateListener
            public void onResult(int i, Object obj) {
                LogUtil.a("FastingLiteTaskHelper", "deleteHiHealthData fastingLitePhase List ", Integer.valueOf(i));
            }
        });
    }
}
