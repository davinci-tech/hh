package health.compact.a;

import android.net.Uri;
import android.util.SparseArray;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.provider.cursor.HealthCursor;
import com.huawei.health.weight.WeightApi;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiDataAggregateProOption;
import com.huawei.hihealth.HiDataFilter;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiDataReadProOption;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiDataHiDeviceInfoListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwidauth.datatype.DeviceInfo;
import com.huawei.ui.main.stories.health.weight.callback.DietDiaryCbk;
import defpackage.ezf;
import defpackage.grz;
import defpackage.jdl;
import defpackage.koq;
import defpackage.quh;
import defpackage.quj;
import health.compact.a.HealthFaDataHelper;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/* loaded from: classes.dex */
public class HealthFaDataHelper {
    private static final int[] b = {DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD_BED_TIME.value(), DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD_RISING_TIME.value(), DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD_FALL_ASLEEP_TIME.value(), DicDataTypeUtil.DataType.SLEEP_ON_OFF_BED_RECORD_WAKEUP_TIME.value()};
    private static final String[] d = {"data_session_manual_sleep_go_to_bed_time_key", "data_session_manual_sleep_get_up_time_key", "data_session_manual_sleep_sleep_time_key", "data_session_manual_sleep_wake_time_key"};

    private HealthFaDataHelper() {
    }

    public static HealthCursor atU_(Uri uri) {
        int i;
        String jsonObject;
        try {
            i = Integer.parseInt(uri.getQueryParameter("type"));
        } catch (NumberFormatException unused) {
            com.huawei.hwlogsmodel.LogUtil.b("HealthFaDataHelper", "getHealthCursorForFa numberFormatException");
            i = 0;
        }
        com.huawei.hwlogsmodel.LogUtil.a("HealthFaDataHelper", "getHealthCursorForFa type ", Integer.valueOf(i));
        HashMap hashMap = new HashMap(3);
        hashMap.put("version_code", Integer.valueOf(BaseApplication.c()));
        boolean m = SharedPerferenceUtils.m(BaseApplication.e());
        hashMap.put("is_browse_mode", Boolean.valueOf(m));
        hashMap.put("is_oversea", Boolean.valueOf(CloudUtils.d()));
        boolean a2 = AuthorizationUtils.a(com.huawei.hwcommonmodel.application.BaseApplication.getContext());
        hashMap.put("is_authorized", Boolean.valueOf(a2));
        if (!m && a2) {
            if (i != 3) {
                switch (i) {
                    case 10:
                        jsonObject = b().toString();
                        break;
                    case 11:
                        jsonObject = a().toString();
                        break;
                    case 12:
                        jsonObject = d().toString();
                        break;
                    case 13:
                        jsonObject = g().toString();
                        break;
                    case 14:
                        jsonObject = e().toString();
                        break;
                    default:
                        com.huawei.hwlogsmodel.LogUtil.h("HealthFaDataHelper", "getHealthCursorForFa type error");
                        jsonObject = "";
                        break;
                }
            } else {
                jsonObject = c().toString();
            }
            hashMap.put("health_data", jsonObject);
        }
        return new ezf(hashMap);
    }

    private static JsonObject b() {
        final WeightApi weightApi = (WeightApi) Services.a("Main", WeightApi.class);
        if (weightApi == null) {
            return new JsonObject();
        }
        final JsonObject jsonObject = new JsonObject();
        boolean isJoinDietDiary = weightApi.isJoinDietDiary();
        jsonObject.addProperty("isJoinDietDiary", Boolean.valueOf(isJoinDietDiary));
        if (!isJoinDietDiary) {
            return jsonObject;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        weightApi.getWeightControlSetting(new DietDiaryCbk<quj>() { // from class: health.compact.a.HealthFaDataHelper.4
            @Override // com.huawei.ui.main.stories.health.weight.callback.DietDiaryCbk
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public void onSuccess(quj qujVar) {
                JsonObject.this.addProperty("weightControlSetting", HiJsonUtil.e(qujVar));
                countDownLatch.countDown();
            }

            @Override // com.huawei.ui.main.stories.health.weight.callback.DietDiaryCbk
            public void onFailure(int i, String str) {
                com.huawei.hwlogsmodel.LogUtil.h("HealthFaDataHelper", "getWeightControlSetting onFailure");
                countDownLatch.countDown();
            }
        });
        final int b2 = DateFormatUtil.b(System.currentTimeMillis());
        ReleaseLogUtil.b("HealthFaDataHelper", "getDietData dayFormat ", Integer.valueOf(b2));
        grz.d(b2, b2, 3000L, new ResponseCallback() { // from class: eyz
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i, Object obj) {
                HealthFaDataHelper.d(WeightApi.this, b2, jsonObject, countDownLatch, i, (List) obj);
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException unused) {
            com.huawei.hwlogsmodel.LogUtil.b("HealthFaDataHelper", "interrupted while waiting for getDietData");
        }
        com.huawei.hwlogsmodel.LogUtil.c("HealthFaDataHelper", "getDietData ", jsonObject.toString());
        return jsonObject;
    }

    public static /* synthetic */ void d(WeightApi weightApi, int i, JsonObject jsonObject, CountDownLatch countDownLatch, int i2, List list) {
        quh quhVar;
        com.huawei.hwlogsmodel.LogUtil.a("HealthFaDataHelper", "getDietData errorCode ", Integer.valueOf(i2), " list ", list);
        if (koq.c(list) && (quhVar = (quh) list.get(0)) != null) {
            if (quhVar.d() == null || quhVar.d().c() == 0.0f) {
                com.huawei.hwlogsmodel.LogUtil.a("HealthFaDataHelper", "getDietData has no goal.");
                weightApi.refreshDietOverview(i);
            }
            jsonObject.addProperty("dietRecord", HiJsonUtil.e(quhVar));
        }
        countDownLatch.countDown();
    }

    private static JsonObject a() {
        WeightApi weightApi = (WeightApi) Services.a("Main", WeightApi.class);
        if (weightApi == null) {
            return new JsonObject();
        }
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("fastingLiteStatus", weightApi.getFastingLiteStatus());
        jsonObject.addProperty("fastingLiteViewData", weightApi.getFastingLiteViewData());
        com.huawei.hwlogsmodel.LogUtil.c("HealthFaDataHelper", "getFastingLiteData ", jsonObject.toString());
        return jsonObject;
    }

    private static JsonObject d() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final JsonObject jsonObject = new JsonObject();
        long currentTimeMillis = System.currentTimeMillis();
        long b2 = b(currentTimeMillis, 0, 0, 0, 0);
        long b3 = b(currentTimeMillis, 23, 59, 59, 999);
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setType(new int[]{DicDataTypeUtil.DataType.BREATH_TRAIN_SET.value()});
        hiAggregateOption.setConstantsKey(new String[]{"breathTrain"});
        hiAggregateOption.setAggregateType(2);
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setStartTime(b2);
        hiAggregateOption.setEndTime(b3);
        HiHealthNativeApi.a(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: health.compact.a.HealthFaDataHelper.2
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                com.huawei.hwlogsmodel.LogUtil.a("HealthFaDataHelper", "getBreathTrainData onResult errorCode ", Integer.valueOf(i));
                if (koq.b(list)) {
                    com.huawei.hwlogsmodel.LogUtil.h("HealthFaDataHelper", "getBreathTrainData dataList is empty");
                    countDownLatch.countDown();
                    return;
                }
                HiHealthData hiHealthData = list.get(0);
                if (hiHealthData == null) {
                    com.huawei.hwlogsmodel.LogUtil.h("HealthFaDataHelper", "getBreathTrainData hiHealthData is null");
                    countDownLatch.countDown();
                } else {
                    jsonObject.addProperty("breathCount", Integer.valueOf(hiHealthData.getInt("breathTrain")));
                    countDownLatch.countDown();
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException unused) {
            com.huawei.hwlogsmodel.LogUtil.b("HealthFaDataHelper", "interrupted while waiting for getBreathTrainData");
        }
        com.huawei.hwlogsmodel.LogUtil.c("HealthFaDataHelper", "getBreathTrainData ", jsonObject.toString());
        return jsonObject;
    }

    private static long b(long j, int i, int i2, int i3, int i4) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.set(11, i);
        calendar.set(12, i2);
        calendar.set(13, i3);
        calendar.set(14, i4);
        return calendar.getTime().getTime();
    }

    private static JsonObject c() {
        CountDownLatch countDownLatch = new CountDownLatch(2);
        JsonObject jsonObject = new JsonObject();
        JsonObject jsonObject2 = new JsonObject();
        HiHealthNativeApi.a(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).aggregateHiHealthData(h(), new AnonymousClass5(jsonObject, countDownLatch, jsonObject2));
        try {
            countDownLatch.await(1000L, TimeUnit.MILLISECONDS);
            if (jsonObject2.has("fallAsleepTime") && jsonObject2.has("wakeupTime")) {
                jsonObject.addProperty("fallAsleepTime", Long.valueOf(jsonObject2.get("fallAsleepTime").getAsLong()));
                jsonObject.addProperty("wakeupTime", Long.valueOf(jsonObject2.get("wakeupTime").getAsLong()));
            }
        } catch (InterruptedException unused) {
            com.huawei.hwlogsmodel.LogUtil.b("HealthFaDataHelper", "getSleepData InterruptedException");
        } catch (NumberFormatException unused2) {
            com.huawei.hwlogsmodel.LogUtil.b("HealthFaDataHelper", "NumberFormatException occurs");
        }
        com.huawei.hwlogsmodel.LogUtil.c("HealthFaDataHelper", "sleepData: ", jsonObject.toString());
        return jsonObject;
    }

    /* renamed from: health.compact.a.HealthFaDataHelper$5, reason: invalid class name */
    public class AnonymousClass5 implements HiAggregateListener {
        final /* synthetic */ JsonObject c;
        final /* synthetic */ CountDownLatch d;
        final /* synthetic */ JsonObject e;

        AnonymousClass5(JsonObject jsonObject, CountDownLatch countDownLatch, JsonObject jsonObject2) {
            this.c = jsonObject;
            this.d = countDownLatch;
            this.e = jsonObject2;
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            com.huawei.hwlogsmodel.LogUtil.a("HealthFaDataHelper", "getSleepData success");
            if (koq.c(list)) {
                final HiHealthData hiHealthData = list.get(0);
                HealthFaDataHelper.e(this.c, hiHealthData);
                ThreadPoolManager d = ThreadPoolManager.d();
                final CountDownLatch countDownLatch = this.d;
                final JsonObject jsonObject = this.e;
                d.execute(new Runnable() { // from class: eyy
                    @Override // java.lang.Runnable
                    public final void run() {
                        HealthFaDataHelper.d(countDownLatch, jsonObject, hiHealthData);
                    }
                });
            } else {
                com.huawei.hwlogsmodel.LogUtil.h("HealthFaDataHelper", "getSleepData data is empty");
                this.d.countDown();
            }
            this.d.countDown();
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            com.huawei.hwlogsmodel.LogUtil.a("HealthFaDataHelper", "getSleepData onResultIntent", Integer.valueOf(i), " errorCode ", Integer.valueOf(i2));
            this.d.countDown();
            this.d.countDown();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(final CountDownLatch countDownLatch, final JsonObject jsonObject, HiHealthData hiHealthData) {
        HiHealthNativeApi.a(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).aggregateHiHealthDataPro(b(hiHealthData), new HiAggregateListener() { // from class: health.compact.a.HealthFaDataHelper.1
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i, int i2) {
                com.huawei.hwlogsmodel.LogUtil.a("HealthFaDataHelper", "getSleepDataPro success");
                if (koq.c(list)) {
                    HealthFaDataHelper.d(list, JsonObject.this);
                } else {
                    com.huawei.hwlogsmodel.LogUtil.h("HealthFaDataHelper", "getSleepDataPro data is empty");
                }
                countDownLatch.countDown();
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
                countDownLatch.countDown();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(List<HiHealthData> list, JsonObject jsonObject) {
        if (jsonObject == null || list == null) {
            com.huawei.hwlogsmodel.LogUtil.h("HealthFaDataHelper", "parsesProSleepData error, healthDataList or json is null");
            return;
        }
        HiHealthData hiHealthData = null;
        for (HiHealthData hiHealthData2 : list) {
            if (hiHealthData2 != null && (hiHealthData == null || hiHealthData.getLong("data_session_manual_sleep_wake_time_key") < hiHealthData2.getLong("data_session_manual_sleep_wake_time_key"))) {
                hiHealthData = hiHealthData2;
            }
        }
        if (hiHealthData == null) {
            return;
        }
        jsonObject.addProperty("fallAsleepTime", Long.valueOf(hiHealthData.getLong("data_session_manual_sleep_sleep_time_key")));
        jsonObject.addProperty("wakeupTime", Long.valueOf(hiHealthData.getLong("data_session_manual_sleep_wake_time_key")));
    }

    private static HiAggregateOption h() {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        int b2 = DateFormatUtil.b(System.currentTimeMillis());
        hiAggregateOption.setTimeInterval(String.valueOf(20140101), String.valueOf(b2), HiDataReadOption.TimeFormatType.DATE_FORMAT_YYYY_MM_DD);
        hiAggregateOption.setCount(1);
        hiAggregateOption.setGroupUnitSize(1);
        hiAggregateOption.setReadType(0);
        hiAggregateOption.setSortOrder(1);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(3);
        hiAggregateOption.setType(new int[]{44102, 44103, 44101, 44108, 44001, 44002, 44110, 44105});
        hiAggregateOption.setConstantsKey(new String[]{"core_sleep_deep_key", "core_sleep_shallow_key", "core_sleep_wake_dream_key", "sleep_core_sleep_noon_duration_key", "sleep_deep_key", "sleep_shallow_key", "sleep_device_category", "sleep_duration_sum"});
        return hiAggregateOption;
    }

    private static HiDataAggregateProOption b(HiHealthData hiHealthData) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        hiAggregateOption.setTimeInterval(DateFormatUtil.a(jdl.t(jdl.b(hiHealthData.getStartTime(), -3)), DateFormatUtil.DateFormatType.DATE_FORMAT_MILS), DateFormatUtil.a(jdl.e(jdl.b(hiHealthData.getStartTime(), 3)), DateFormatUtil.DateFormatType.DATE_FORMAT_MILS), HiDataReadOption.TimeFormatType.DATE_FORMAT_MILLISECONDS);
        hiAggregateOption.setType(b);
        hiAggregateOption.setConstantsKey(d);
        hiAggregateOption.setAggregateType(1);
        hiAggregateOption.setGroupUnitType(0);
        HiDataFilter.DataFilterExpression dataFilterExpression = new HiDataFilter.DataFilterExpression("data_session_manual_sleep_get_up_time_key", HiDataFilter.DataFilterExpression.BIGGER_EQUAL, jdl.t(hiHealthData.getStartTime()));
        HiDataFilter.DataFilterExpression dataFilterExpression2 = new HiDataFilter.DataFilterExpression("data_session_manual_sleep_get_up_time_key", HiDataFilter.DataFilterExpression.LESS_EQUAL, jdl.e(hiHealthData.getStartTime()));
        ArrayList arrayList = new ArrayList();
        arrayList.add(dataFilterExpression);
        arrayList.add(dataFilterExpression2);
        return HiDataAggregateProOption.builder().c(hiAggregateOption).b(HiJsonUtil.e(new HiDataFilter(arrayList, Collections.singletonList(0)))).c((String) null).c();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(JsonObject jsonObject, HiHealthData hiHealthData) {
        int i = hiHealthData.getInt("core_sleep_deep_key");
        int i2 = hiHealthData.getInt("core_sleep_shallow_key");
        int i3 = hiHealthData.getInt("core_sleep_wake_dream_key");
        int i4 = hiHealthData.getInt("sleep_core_sleep_noon_duration_key");
        int i5 = hiHealthData.getInt("sleep_deep_key");
        int i6 = hiHealthData.getInt("sleep_shallow_key");
        int round = Math.round(hiHealthData.getFloat("sleep_device_category"));
        int i7 = hiHealthData.getInt("sleep_duration_sum");
        jsonObject.addProperty("cordDeepTime", Integer.valueOf(i));
        jsonObject.addProperty("cordShallowTime", Integer.valueOf(i2));
        jsonObject.addProperty("cordRemTime", Integer.valueOf(i3));
        jsonObject.addProperty("cordNoonTime", Integer.valueOf(i4));
        jsonObject.addProperty("deepTime", Integer.valueOf(i5));
        jsonObject.addProperty("shallowTime", Integer.valueOf(i6));
        jsonObject.addProperty("startTime", Long.valueOf(hiHealthData.getStartTime()));
        jsonObject.addProperty("endTime", Long.valueOf(hiHealthData.getEndTime()));
        jsonObject.addProperty("deviceCategory", Integer.valueOf(round));
        jsonObject.addProperty("totalSleepDuration", Integer.valueOf(i7));
    }

    private static JsonArray g() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final JsonArray jsonArray = new JsonArray();
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(0L, System.currentTimeMillis());
        hiDataReadOption.setType(new int[]{2034});
        hiDataReadOption.setSortOrder(1);
        HiHealthNativeApi.a(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).readHiHealthDataPro(HiDataReadProOption.builder().e(hiDataReadOption).d(true).e(), new HiDataReadResultListener() { // from class: health.compact.a.HealthFaDataHelper.3
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                com.huawei.hwlogsmodel.LogUtil.a("HealthFaDataHelper", "getStressData success");
                List d2 = HealthFaDataHelper.d(obj, 2034);
                if (koq.c(d2)) {
                    HealthFaDataHelper.b(JsonArray.this, d2);
                    com.huawei.hwlogsmodel.LogUtil.h("HealthFaDataHelper", "getStressData list size:", Integer.valueOf(d2.size()));
                } else {
                    com.huawei.hwlogsmodel.LogUtil.h("HealthFaDataHelper", "getStressData is empty!");
                }
                countDownLatch.countDown();
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
                com.huawei.hwlogsmodel.LogUtil.a("HealthFaDataHelper", "getStressData onResultIntent : read failed errorCode is", Integer.valueOf(i2));
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException unused) {
            com.huawei.hwlogsmodel.LogUtil.b("HealthFaDataHelper", "getStressData InterruptedException");
        }
        return jsonArray;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(JsonArray jsonArray, List<HiHealthData> list) {
        for (HiHealthData hiHealthData : list) {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("startTime", Long.valueOf(hiHealthData.getStartTime()));
            jsonObject.addProperty("endTime", Long.valueOf(hiHealthData.getStartTime()));
            jsonObject.addProperty("value", Double.valueOf(hiHealthData.getValue()));
            jsonArray.add(jsonObject);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<HiHealthData> d(Object obj, int i) {
        ArrayList arrayList = new ArrayList();
        if (!(obj instanceof SparseArray)) {
            return arrayList;
        }
        SparseArray sparseArray = (SparseArray) obj;
        if (sparseArray.size() <= 0) {
            return arrayList;
        }
        Object obj2 = sparseArray.get(i);
        return !(obj2 instanceof List) ? arrayList : (List) obj2;
    }

    private static JsonArray e() {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final JsonArray jsonArray = new JsonArray();
        HiHealthNativeApi.a(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).readDeviceInfo(new HiDataHiDeviceInfoListener() { // from class: health.compact.a.HealthFaDataHelper.9
            @Override // com.huawei.hihealth.data.listener.HiDataHiDeviceInfoListener
            public void onResult(List<HiDeviceInfo> list) {
                com.huawei.hwlogsmodel.LogUtil.h("HealthFaDataHelper", "getDevices success");
                if (koq.c(list)) {
                    HealthFaDataHelper.a(JsonArray.this, list);
                } else {
                    com.huawei.hwlogsmodel.LogUtil.h("HealthFaDataHelper", "getDevices is empty!");
                }
                countDownLatch.countDown();
            }
        });
        try {
            countDownLatch.await();
        } catch (InterruptedException unused) {
            com.huawei.hwlogsmodel.LogUtil.b("HealthFaDataHelper", "getDevices InterruptedException");
        }
        return jsonArray;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(JsonArray jsonArray, List<HiDeviceInfo> list) {
        for (HiDeviceInfo hiDeviceInfo : list) {
            int deviceTypeMapping = hiDeviceInfo.getDeviceTypeMapping();
            if (deviceTypeMapping == 0 || deviceTypeMapping == 1) {
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("deviceName", hiDeviceInfo.getDeviceName());
                jsonObject.addProperty(DeviceInfo.TAG_DEVICE_ID, hiDeviceInfo.getDeviceUniqueCode());
                jsonObject.addProperty("deviceType", Integer.valueOf(hiDeviceInfo.getDeviceType()));
                jsonArray.add(jsonObject);
            }
        }
    }
}
