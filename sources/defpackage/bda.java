package defpackage;

import android.text.TextUtils;
import android.util.SparseArray;
import android.util.SparseIntArray;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.healthmodel.bean.HealthLifeBean;
import com.huawei.health.healthmodel.bean.HealthLifeTaskBean;
import com.huawei.health.healthmodel.bean.ImageBean;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListenerEx;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hihealth.data.type.HiSubscribeType;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtils;
import health.compact.a.LogAnonymous;
import health.compact.a.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/* loaded from: classes3.dex */
public class bda {
    private static HealthLifeBean a() {
        return (HealthLifeBean) HiJsonUtil.e(bao.g("step_intensity_bean"), HealthLifeBean.class);
    }

    public static void nQ_(SparseArray<List<HealthLifeBean>> sparseArray) {
        if (sparseArray == null) {
            LogUtil.h("HealthLife_TaskUtils", "refreshWeekRecordSparseArray sparseArray is null");
            return;
        }
        int size = sparseArray.size();
        if (size <= 0) {
            LogUtil.h("HealthLife_TaskUtils", "refreshWeekRecordSparseArray size ", Integer.valueOf(size));
            return;
        }
        HealthLifeBean a2 = a();
        if (a2 == null) {
            LogUtil.h("HealthLife_TaskUtils", "refreshWeekRecordSparseArray basicSportBean is null");
            return;
        }
        int recordDay = a2.getRecordDay();
        List<HealthLifeBean> list = null;
        for (int i = 0; i < size; i++) {
            int keyAt = sparseArray.keyAt(i);
            if (keyAt == recordDay) {
                list = sparseArray.get(keyAt);
            }
        }
        c(list);
    }

    public static void c(List<HealthLifeBean> list) {
        HealthLifeBean healthLifeBean;
        if (koq.b(list)) {
            LogUtil.h("HealthLife_TaskUtils", "refreshDayRecordList list ", list);
            return;
        }
        HealthLifeBean a2 = a();
        if (a2 == null) {
            LogUtil.h("HealthLife_TaskUtils", "refreshDayRecordList basicSportBean is null");
            return;
        }
        int id = a2.getId();
        int recordDay = a2.getRecordDay();
        Iterator<HealthLifeBean> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                healthLifeBean = null;
                break;
            }
            healthLifeBean = it.next();
            if (healthLifeBean != null && healthLifeBean.getId() == id && healthLifeBean.getRecordDay() == recordDay) {
                LogUtil.a("HealthLife_TaskUtils", "refreshDayRecordList bean ", healthLifeBean, " lifeBean ", healthLifeBean);
                break;
            }
        }
        if (healthLifeBean == null) {
            LogUtil.h("HealthLife_TaskUtils", "refreshDayRecordList bean is null");
            return;
        }
        String result = a2.getResult();
        int h = CommonUtils.h(result);
        int h2 = CommonUtils.h(healthLifeBean.getResult());
        LogUtil.a("HealthLife_TaskUtils", "refreshDayRecordList basicSportResult ", Integer.valueOf(h), " result ", Integer.valueOf(h2));
        if (h > h2) {
            healthLifeBean.setResult(result);
        }
        if (healthLifeBean.getComplete() <= 0) {
            healthLifeBean.setComplete(a2.getComplete());
        }
        healthLifeBean.setTimeZone(a2.getTimeZone());
        healthLifeBean.setIsUpdated(a2.getIsUpdated());
        healthLifeBean.setTimestamp(a2.getTimestamp());
        healthLifeBean.setSyncStatus(a2.getSyncStatus());
    }

    public static void b(List<HealthLifeBean> list) {
        int id;
        if (koq.b(list)) {
            ReleaseLogUtil.a("R_HealthLife_TaskUtils", "refreshLastCache list ", list);
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (HealthLifeBean healthLifeBean : list) {
            if (healthLifeBean != null && (id = healthLifeBean.getId()) != 1 && id != 100001) {
                arrayList.add(healthLifeBean);
            }
        }
        if (koq.b(arrayList)) {
            ReleaseLogUtil.a("R_HealthLife_TaskUtils", "refreshLastCache beanList ", arrayList);
        } else {
            bao.b("health_life_data_cache_last", HiJsonUtil.e(arrayList));
        }
    }

    public static List<HealthLifeBean> c() {
        String g = bao.g("health_life_data_cache_last");
        if (TextUtils.isEmpty(g)) {
            return Collections.emptyList();
        }
        try {
            return (List) HiJsonUtil.b(g, new TypeToken<List<HealthLifeBean>>() { // from class: bda.5
            }.getType());
        } catch (JsonParseException | IllegalStateException e) {
            ReleaseLogUtil.c("R_HealthLife_TaskUtils", "getLastCache exception ", LogAnonymous.b(e));
            return Collections.emptyList();
        }
    }

    public static void e(List<HealthLifeBean> list) {
        if (koq.b(list)) {
            ReleaseLogUtil.a("R_HealthLife_TaskUtils", "setCache list ", list);
        } else {
            bao.e("health_model_data_cache", HiJsonUtil.e(list));
            i(list);
        }
    }

    public static List<HealthLifeBean> d() {
        String e = bao.e("health_model_data_cache");
        if (TextUtils.isEmpty(e)) {
            return Collections.emptyList();
        }
        try {
            return (List) HiJsonUtil.b(e, new TypeToken<List<HealthLifeBean>>() { // from class: bda.4
            }.getType());
        } catch (JsonParseException | IllegalStateException e2) {
            ReleaseLogUtil.c("R_HealthLife_TaskUtils", "getCache exception ", LogAnonymous.b(e2));
            return Collections.emptyList();
        }
    }

    public static void nR_(SparseArray<List<HealthLifeBean>> sparseArray) {
        if (sparseArray == null) {
            LogUtil.h("HealthLife_TaskUtils", "setWeekCache sparseArray is null");
            return;
        }
        int size = sparseArray.size();
        if (size <= 0) {
            LogUtil.h("HealthLife_TaskUtils", "setWeekCache size ", Integer.valueOf(size));
            return;
        }
        HashMap hashMap = new HashMap();
        for (int i = 0; i < size; i++) {
            int keyAt = sparseArray.keyAt(i);
            hashMap.put(Integer.valueOf(keyAt), sparseArray.get(keyAt));
        }
        String d = HiJsonUtil.d(hashMap, new TypeToken<HashMap<Integer, List<HealthLifeBean>>>() { // from class: bda.3
        }.getType());
        bao.e("health_model_week_task_records_cache", d);
        LogUtil.a("HealthLife_TaskUtils", "setWeekCache size ", Integer.valueOf(size), " hashMap ", hashMap, " json ", d);
    }

    public static SparseArray<List<HealthLifeBean>> nM_() {
        HashMap hashMap;
        try {
            hashMap = (HashMap) HiJsonUtil.b(bao.e("health_model_week_task_records_cache"), new TypeToken<HashMap<Integer, List<HealthLifeBean>>>() { // from class: bda.1
            }.getType());
        } catch (JsonParseException e) {
            LogUtil.b("HealthLife_TaskUtils", "getWeekCache exception ", LogAnonymous.b((Throwable) e));
            hashMap = null;
        }
        if (hashMap == null || hashMap.size() <= 0) {
            return new SparseArray<>();
        }
        SparseArray<List<HealthLifeBean>> sparseArray = new SparseArray<>();
        for (Map.Entry entry : hashMap.entrySet()) {
            if (entry != null) {
                List<HealthLifeBean> list = (List) entry.getValue();
                if (!koq.b(list)) {
                    sparseArray.append(((Integer) entry.getKey()).intValue(), list);
                }
            }
        }
        return sparseArray;
    }

    private static void i(List<HealthLifeBean> list) {
        int i;
        int i2;
        Iterator<HealthLifeBean> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                i2 = 0;
                break;
            }
            HealthLifeBean next = it.next();
            if (next != null) {
                i2 = next.getRecordDay();
                break;
            }
        }
        if (i2 <= 0) {
            return;
        }
        SparseArray<List<HealthLifeBean>> nM_ = nM_();
        int size = nM_.size();
        SparseArray sparseArray = new SparseArray();
        if (size <= 0) {
            sparseArray.append(i2, list);
            LogUtil.a("HealthLife_TaskUtils", "refreshWeekCache recordDay ", Integer.valueOf(i2), " list ", list);
        } else {
            int b = DateFormatUtil.b(jdl.c(DateFormatUtil.c(i2), 1, 0));
            int b2 = DateFormatUtil.b(jdl.c(DateFormatUtil.c(nM_.keyAt(0)), 1, 0));
            if (b == b2) {
                for (i = 0; i < size; i++) {
                    int keyAt = nM_.keyAt(i);
                    sparseArray.append(keyAt, keyAt == i2 ? list : nM_.get(keyAt));
                }
            } else {
                sparseArray.append(i2, list);
            }
            LogUtil.a("HealthLife_TaskUtils", "refreshWeekCache weekStartDateForRecordDay ", Integer.valueOf(b), " weekStartDateForDate ", Integer.valueOf(b2), " recordDay ", Integer.valueOf(i2), " list ", list);
        }
        nR_(sparseArray);
    }

    public static ArrayList<Integer> d(String str, HiSubscribeListener hiSubscribeListener) {
        if (hiSubscribeListener == null) {
            LogUtil.h("HealthLife_TaskUtils", "subscribeHiHealthData listener is null source ", str);
            return new ArrayList<>();
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(200);
        arrayList.add(14);
        arrayList.add(2);
        arrayList.add(1);
        arrayList.add(Integer.valueOf(HiSubscribeType.c));
        arrayList.add(Integer.valueOf(HiSubscribeType.f4119a));
        arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD.value()));
        arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.BREATH_TRAIN_SET.value()));
        HiHealthNativeApi.a(BaseApplication.e()).subscribeHiHealthData(arrayList, hiSubscribeListener);
        return arrayList;
    }

    public static void e(String str, List<Integer> list, HiUnSubscribeListener hiUnSubscribeListener) {
        if (koq.b(list) || hiUnSubscribeListener == null) {
            LogUtil.h("HealthLife_TaskUtils", "unSubscribeHiHealthData list ", list, " listener ", hiUnSubscribeListener, " source ", str);
        } else {
            HiHealthNativeApi.a(BaseApplication.e()).unSubscribeHiHealthData(list, hiUnSubscribeListener);
        }
    }

    private static List<HiAggregateOption> nK_(SparseArray<HealthLifeBean> sparseArray) {
        if (sparseArray == null) {
            LogUtil.h("HealthLife_TaskUtils", "getDayHiAggregateOptionList recordArray is null");
            return Collections.emptyList();
        }
        int size = sparseArray.size();
        if (size <= 0) {
            LogUtil.h("HealthLife_TaskUtils", "getDayHiAggregateOptionList size ", Integer.valueOf(size));
            return Collections.emptyList();
        }
        HealthLifeBean valueAt = sparseArray.valueAt(0);
        if (valueAt == null) {
            LogUtil.h("HealthLife_TaskUtils", "getDayHiAggregateOptionList bean is null");
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < size; i++) {
            arrayList.add(Integer.valueOf(sparseArray.keyAt(i)));
        }
        int recordDay = valueAt.getRecordDay();
        ArrayList arrayList2 = new ArrayList();
        if (arrayList.contains(2)) {
            arrayList2.add(bch.c(recordDay, recordDay));
        }
        if (arrayList.contains(3)) {
            arrayList2.add(bbm.b(recordDay, recordDay));
        }
        if (arrayList.contains(5)) {
            arrayList2.add(azt.b(recordDay, recordDay));
        }
        if (arrayList.contains(10)) {
            arrayList2.add(baj.d(recordDay, recordDay));
        }
        if (arrayList.contains(14)) {
            arrayList2.add(bdi.c(recordDay, recordDay));
        }
        return arrayList2;
    }

    public static SparseArray<HealthLifeBean> nL_(final SparseArray<HealthLifeBean> sparseArray) {
        List<HiAggregateOption> nK_ = nK_(sparseArray);
        if (koq.b(nK_)) {
            LogUtil.h("HealthLife_TaskUtils", "getDayRecordArray hiAggregateOptionList ", nK_);
            return sparseArray;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        LogUtil.a("HealthLife_TaskUtils", "getDayRecordArray start");
        HiHealthNativeApi.a(BaseApplication.e()).aggregateHiHealthDataEx(nK_, new HiAggregateListenerEx() { // from class: bdc
            @Override // com.huawei.hihealth.data.listener.HiAggregateListenerEx
            public final void onResult(SparseArray sparseArray2, int i, int i2) {
                bda.nN_(sparseArray, countDownLatch, sparseArray2, i, i2);
            }
        });
        azi.d(countDownLatch, "getDayRecordArray");
        return sparseArray;
    }

    static /* synthetic */ void nN_(SparseArray sparseArray, CountDownLatch countDownLatch, SparseArray sparseArray2, int i, int i2) {
        LogUtil.a("HealthLife_TaskUtils", "getDayRecordArray end");
        nP_(sparseArray2, sparseArray);
        countDownLatch.countDown();
    }

    private static void nP_(SparseArray<List<HiHealthData>> sparseArray, SparseArray<HealthLifeBean> sparseArray2) {
        HiHealthData hiHealthData;
        if (sparseArray == null || sparseArray2 == null) {
            LogUtil.h("HealthLife_TaskUtils", "refreshDayRecord dataArray ", sparseArray, " recordArray ", sparseArray2);
            return;
        }
        int size = sparseArray.size();
        if (size <= 0) {
            LogUtil.h("HealthLife_TaskUtils", "refreshDayRecord size ", Integer.valueOf(size));
            return;
        }
        for (int i = 0; i < size; i++) {
            List<HiHealthData> list = sparseArray.get(sparseArray.keyAt(i));
            if (!koq.b(list) && (hiHealthData = list.get(0)) != null) {
                nO_(hiHealthData.getType(), list, sparseArray2);
            }
        }
    }

    private static void nO_(int i, List<HiHealthData> list, SparseArray<HealthLifeBean> sparseArray) {
        if (i == 10006) {
            bdi.d(list, sparseArray.get(14));
            return;
        }
        if (i == 40002) {
            bch.b(list, sparseArray.get(2));
            return;
        }
        if (i == 200004) {
            baj.b(list, sparseArray.get(10));
            return;
        }
        if (i == 500001) {
            azt.e(list, sparseArray.get(5));
            return;
        }
        switch (i) {
            case 47101:
            case 47102:
            case 47103:
            case 47104:
            case 47105:
                break;
            default:
                switch (i) {
                    case 47107:
                    case 47108:
                    case 47109:
                        break;
                    default:
                        LogUtil.h("HealthLife_TaskUtils", "refreshDayRecord default type ", Integer.valueOf(i));
                        break;
                }
                return;
        }
        bbm.c(list, sparseArray.get(3));
    }

    public static void d(final int i, final List<Integer> list, final ResponseCallback<List<HealthLifeTaskBean>> responseCallback) {
        LogUtil.a("HealthLife_TaskUtils", "getDayRecordArray date ", Integer.valueOf(i), " idList ", list, " callback ", responseCallback);
        if (responseCallback == null) {
            return;
        }
        if (HandlerExecutor.c()) {
            azi.d(ThreadPoolManager.d(), new Runnable() { // from class: bdg
                @Override // java.lang.Runnable
                public final void run() {
                    bda.d(i, list, responseCallback);
                }
            });
            return;
        }
        if (koq.b(list) || i <= 0) {
            responseCallback.onResponse(-1, Collections.emptyList());
            return;
        }
        ArrayList<ImageBean> a2 = bad.b().a("1");
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            if (intValue == 12) {
                e(i, a2, responseCallback);
            } else {
                LogUtil.a("HealthLife_TaskUtils", "getDayRecordArray id ", Integer.valueOf(intValue));
            }
        }
    }

    private static void e(final int i, final List<ImageBean> list, final ResponseCallback<List<HealthLifeTaskBean>> responseCallback) {
        azp.c(d(i), (ResponseCallback<HealthLifeBean>) new ResponseCallback() { // from class: bde
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i2, Object obj) {
                bda.c(list, i, responseCallback, i2, (HealthLifeBean) obj);
            }
        });
    }

    static /* synthetic */ void c(List list, int i, ResponseCallback responseCallback, int i2, HealthLifeBean healthLifeBean) {
        LogUtil.a("HealthLife_TaskUtils", "handleBloodPressureData errorCode ", Integer.valueOf(i2), " object ", healthLifeBean);
        HealthLifeTaskBean healthLifeTaskBean = new HealthLifeTaskBean();
        healthLifeTaskBean.setId(12);
        healthLifeTaskBean.setName(nsf.h(dsl.ZJ_().get(12)));
        healthLifeTaskBean.setDialogImageBean(b("1", 12, list));
        healthLifeTaskBean.setIconResourcesId(azi.lR_().get(12));
        healthLifeTaskBean.setColorResourcesId(azi.lQ_().get(12));
        healthLifeTaskBean.setBackgroundColorResourcesId(azi.lP_().get(12));
        if (healthLifeBean == null) {
            healthLifeTaskBean.setRecordDay(i);
            healthLifeTaskBean.setComplete(0);
        } else {
            healthLifeTaskBean.setRecordDay(healthLifeBean.getRecordDay());
            healthLifeTaskBean.setComplete(healthLifeBean.getComplete());
            healthLifeTaskBean.setHealthLifeBean(healthLifeBean);
            healthLifeTaskBean.setValue(azi.a(healthLifeBean));
        }
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(healthLifeTaskBean);
        responseCallback.onResponse(koq.b(arrayList) ? -1 : 0, arrayList);
    }

    private static HealthLifeBean d(int i) {
        HealthLifeBean healthLifeBean = new HealthLifeBean();
        healthLifeBean.setId(12);
        healthLifeBean.setTimeZone(jdl.q(System.currentTimeMillis()));
        healthLifeBean.setRecordDay(i);
        healthLifeBean.setComplete(0);
        healthLifeBean.setResult(String.valueOf(0));
        return healthLifeBean;
    }

    public static List<HealthLifeTaskBean> a(List<HealthLifeBean> list) {
        if (koq.b(list)) {
            LogUtil.h("HealthLife_TaskUtils", "getTaskBeanList healthLifeBeanList ", list);
            return Collections.emptyList();
        }
        Collections.sort(list, new bay());
        SparseIntArray lR_ = azi.lR_();
        SparseIntArray lQ_ = azi.lQ_();
        SparseIntArray lP_ = azi.lP_();
        SparseIntArray ZJ_ = dsl.ZJ_();
        bad b = bad.b();
        ArrayList<ImageBean> a2 = b.a("2");
        ArrayList<ImageBean> a3 = b.a("1");
        ArrayList arrayList = new ArrayList();
        for (HealthLifeBean healthLifeBean : list) {
            if (healthLifeBean != null) {
                int id = healthLifeBean.getId();
                HealthLifeTaskBean healthLifeTaskBean = new HealthLifeTaskBean();
                healthLifeTaskBean.setId(id);
                healthLifeTaskBean.setComplete(healthLifeBean.getComplete());
                healthLifeTaskBean.setRecordDay(healthLifeBean.getRecordDay());
                if (lR_ != null) {
                    healthLifeTaskBean.setIconResourcesId(lR_.get(id));
                }
                if (lQ_ != null) {
                    healthLifeTaskBean.setColorResourcesId(lQ_.get(id));
                }
                if (lP_ != null) {
                    healthLifeTaskBean.setBackgroundColorResourcesId(lP_.get(id));
                }
                if (ZJ_ != null) {
                    healthLifeTaskBean.setName(nsf.h(ZJ_.get(id)));
                }
                healthLifeTaskBean.setValue(azi.a(healthLifeBean));
                healthLifeTaskBean.setImageBean(b("2", id, a2));
                healthLifeTaskBean.setDialogImageBean(b("1", id, a3));
                healthLifeTaskBean.setHealthLifeBean(healthLifeBean);
                arrayList.add(healthLifeTaskBean);
            }
        }
        return arrayList;
    }

    private static ImageBean b(String str, int i, List<ImageBean> list) {
        if (koq.b(list) || TextUtils.isEmpty(str)) {
            LogUtil.h("HealthLife_TaskUtils", "getImageBean imageBeanList ", list, " scenario ", str);
            return new ImageBean();
        }
        for (ImageBean imageBean : list) {
            if (imageBean != null && str.equals(imageBean.getImageScenario()) && imageBean.getId() == i) {
                return imageBean;
            }
        }
        return new ImageBean();
    }

    public static void d(dsa dsaVar) {
        if (dsaVar == null) {
            ReleaseLogUtil.a("R_HealthLife_TaskUtils", "consInfo is null ");
            bao.e("health_life_task_cons_info", HiJsonUtil.e(new dsa()));
        } else {
            bao.e("health_life_task_cons_info", HiJsonUtil.e(dsaVar));
        }
    }

    public static dsa e() {
        String e = bao.e("health_life_task_cons_info");
        if (TextUtils.isEmpty(e)) {
            return new dsa();
        }
        try {
            return (dsa) HiJsonUtil.b(e, new TypeToken<dsa>() { // from class: bda.2
            }.getType());
        } catch (JsonParseException | IllegalStateException e2) {
            ReleaseLogUtil.c("R_HealthLife_TaskUtils", "getCache exception ", LogAnonymous.b(e2));
            return new dsa();
        }
    }

    public static void d(List<Integer> list) {
        if (koq.b(list)) {
            ReleaseLogUtil.a("R_HealthLife_TaskUtils", "ids is null ");
            bao.e("health_life_task_cons_info", HiJsonUtil.e(new ArrayList()));
        } else {
            HashMap hashMap = new HashMap();
            hashMap.put(Integer.valueOf(DateFormatUtil.b(System.currentTimeMillis())), list);
            bao.e("health_life_shown_tasks", HiJsonUtil.e(hashMap));
        }
    }

    public static List<Integer> a(int i) {
        String e = bao.e("health_life_shown_tasks");
        if (TextUtils.isEmpty(e)) {
            return new ArrayList();
        }
        try {
            Map map = (Map) HiJsonUtil.b(e, new TypeToken<Map<Integer, List<Integer>>>() { // from class: bda.9
            }.getType());
            if (koq.c(map.keySet()) && map.keySet().contains(Integer.valueOf(i))) {
                LogUtil.a("HealthLife_TaskUtils", "get current day ", Integer.valueOf(i), " tasks: ", map.get(Integer.valueOf(i)));
                return (List) map.get(Integer.valueOf(i));
            }
        } catch (JsonParseException | IllegalStateException e2) {
            ReleaseLogUtil.c("R_HealthLife_TaskUtils", "getCache exception ", LogAnonymous.b(e2));
        }
        return new ArrayList();
    }
}
