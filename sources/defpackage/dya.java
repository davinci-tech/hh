package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.SparseArray;
import com.huawei.health.healthdatamgr.api.HealthDataMgrApi;
import com.huawei.health.hwuserlabelmgr.manager.UpdateTrackDataListener;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hwfoundationmodel.trackmodel.HeartRateData;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwidauth.datatype.DeviceInfo;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.skinner.internal.OnRegisterSkinAttrsListener;
import health.compact.a.HiDateUtil;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class dya {
    private static UpdateTrackDataListener b;
    private static final Object c = new Object();
    private static volatile List<Integer> d;

    public static void d(final Context context) {
        LogUtil.a("UserLabelHelper", "saveThirtyDaysTrackDataToOdmf enter");
        jdx.b(new Runnable() { // from class: dya.2
            @Override // java.lang.Runnable
            public void run() {
                long currentTimeMillis = System.currentTimeMillis();
                long currentTimeMillis2 = System.currentTimeMillis();
                HiDataReadOption hiDataReadOption = new HiDataReadOption();
                hiDataReadOption.setStartTime(currentTimeMillis - 2592000000L);
                hiDataReadOption.setEndTime(currentTimeMillis2);
                hiDataReadOption.setType(new int[]{30002});
                HiHealthManager.d(context).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: dya.2.5
                    @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                    public void onResult(Object obj, int i, int i2) {
                        if (i != 0 || obj == null) {
                            return;
                        }
                        SparseArray sparseArray = (SparseArray) obj;
                        if (!(sparseArray.get(30002) instanceof List)) {
                            LogUtil.h("UserLabelHelper", "saveThirtyDaysTrackDataToOdmf is not list");
                            return;
                        }
                        List list = (List) sparseArray.get(30002);
                        if (list == null) {
                            return;
                        }
                        Iterator it = list.iterator();
                        while (it.hasNext()) {
                            dya.a(context, (HiHealthData) it.next());
                        }
                    }

                    @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                    public void onResultIntent(int i, Object obj, int i2, int i3) {
                        LogUtil.c("UserLabelHelper", "saveThirtyDaysTrackDataToOdmf onResultIntent");
                    }
                });
                SharedPreferences.Editor edit = context.getSharedPreferences("user label last modify time", 0).edit();
                edit.putLong("user label last modify time", System.currentTimeMillis());
                edit.commit();
            }
        });
    }

    public static void a(final Context context, HiHealthData hiHealthData) {
        long startTime = hiHealthData.getStartTime();
        long endTime = hiHealthData.getEndTime();
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(startTime, endTime);
        hiDataReadOption.setType(new int[]{OnRegisterSkinAttrsListener.REGISTER_BY_SCAN});
        HiHealthManager.d(context).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: dya.3
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                HiHealthData hiHealthData2;
                if (obj instanceof SparseArray) {
                    SparseArray sparseArray = (SparseArray) obj;
                    if (sparseArray.size() > 0) {
                        Object obj2 = sparseArray.get(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN);
                        if (obj2 instanceof List) {
                            List list = (List) obj2;
                            if (koq.b(list) || (hiHealthData2 = (HiHealthData) list.get(0)) == null) {
                                return;
                            }
                            MotionPathSimplify motionPathSimplify = new MotionPathSimplify();
                            String b2 = dya.b(context, hiHealthData2, eme.b().readTemporaryMotionPath(context, ((HealthDataMgrApi) Services.c("HWHealthDataMgr", HealthDataMgrApi.class)).convertHiDataToTrackData(hiHealthData2, motionPathSimplify)), motionPathSimplify);
                            if (b2 == null) {
                                return;
                            }
                            dxy.b().b(b2);
                            return;
                        }
                        return;
                    }
                    LogUtil.a("UserLabelHelper", "map.size() == 0");
                }
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
                LogUtil.c("UserLabelHelper", "saveTrackDataToOdmf onResultIntent");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String b(Context context, HiHealthData hiHealthData, MotionPath motionPath, MotionPathSimplify motionPathSimplify) {
        if (motionPath == null || motionPathSimplify == null || context == null || hiHealthData == null) {
            LogUtil.a("UserLabelHelper", "createTrackDataToJson null");
            return null;
        }
        int requestSportType = motionPathSimplify.requestSportType();
        String m = HiDateUtil.m(hiHealthData.getStartTime());
        String string = hiHealthData.getString("device_uniquecode");
        long requestTotalTime = motionPathSimplify.requestTotalTime() / 1000;
        double ceil = Math.ceil(motionPathSimplify.requestTotalCalories() / 1000.0d);
        String d2 = d(motionPath);
        String e = e(hiHealthData);
        LogUtil.a("UserLabelHelper", "sportSpeedDistribution = ", e);
        String a2 = a(motionPath, (int) requestTotalTime, motionPathSimplify.getExtendDataString("isTrustHeartRate"), requestSportType);
        LogUtil.a("UserLabelHelper", "heartDistribution = ", a2);
        boolean z = motionPathSimplify.requestDuplicated() == 0;
        int requestAbnormalTrack = motionPathSimplify.requestAbnormalTrack();
        LogUtil.a("UserLabelHelper", "abnormalTrack = ", Integer.valueOf(requestAbnormalTrack));
        boolean z2 = requestAbnormalTrack != 0 ? false : z;
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("SportType", requestSportType);
            jSONObject.put("SportStartTime", m);
            jSONObject.put(DeviceInfo.TAG_DEVICE_ID, string);
            jSONObject.put("SportStartGPS", d2);
            jSONObject.put("SportDuration", requestTotalTime);
            jSONObject.put("HeartDistribution", a2);
            jSONObject.put("SportSpeedDistribution", e);
            jSONObject.put("HeatQuantity", ceil);
            jSONObject.put("isValid", z2);
            return jSONObject.toString();
        } catch (JSONException unused) {
            LogUtil.b("UserLabelHelper", "createTrackDataToJson JSONException");
            return null;
        }
    }

    public static void c(Context context) {
        LogUtil.a("UserLabelHelper", "subscribeTrackDataChanged");
        if (context == null) {
            LogUtil.a("UserLabelHelper", "subscribeTrackDataChanged, context = null");
            return;
        }
        synchronized (c) {
            if (d == null) {
                d = new ArrayList(1);
                d.add(4);
                b = new UpdateTrackDataListener(context);
                HiHealthNativeApi.a(context.getApplicationContext()).subscribeHiHealthData(d, b);
            }
        }
    }

    public static void a(Context context) {
        LogUtil.a("UserLabelHelper", "unSubscribeTrackData");
        if (context == null) {
            LogUtil.a("UserLabelHelper", "unSubscribeTrackData, context = null");
            return;
        }
        synchronized (c) {
            if (d != null) {
                HiHealthNativeApi.a(context.getApplicationContext()).unSubscribeHiHealthData(b.c(), new c("UserLabelHelper", "unSubscribeTrackData, isSuccess:"));
                d = null;
            }
        }
    }

    private static String d(MotionPath motionPath) {
        if (motionPath == null) {
            return null;
        }
        Map<Long, double[]> requestLbsDataMap = motionPath.requestLbsDataMap();
        if (requestLbsDataMap == null || requestLbsDataMap.size() == 0) {
            LogUtil.a("UserLabelHelper", "getSportStartGps No GPS");
            return null;
        }
        double[] dArr = requestLbsDataMap.get(0L);
        if (dArr == null || dArr.length < 2) {
            return null;
        }
        double d2 = dArr[0];
        double d3 = dArr[1];
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(String.valueOf(d3));
        jSONArray.put(String.valueOf(d2));
        return jSONArray.toString();
    }

    private static String e(HiHealthData hiHealthData) {
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        int i6;
        if (hiHealthData == null) {
            return null;
        }
        JSONArray jSONArray = new JSONArray();
        int i7 = 0;
        try {
            JSONObject jSONObject = new JSONObject(new JSONObject(hiHealthData.getMetaData()).get("paceMap").toString());
            Iterator<String> keys = jSONObject.keys();
            i2 = 0;
            i3 = 0;
            i4 = 0;
            i5 = 0;
            i6 = 0;
            while (keys.hasNext()) {
                try {
                    if (keys.next() instanceof String) {
                        double d2 = jSONObject.getDouble(keys.next());
                        if (d2 < 300.0d) {
                            i7++;
                        } else if (d2 < 360.0d) {
                            i2++;
                        } else if (d2 < 420.0d) {
                            i3++;
                        } else if (d2 < 480.0d) {
                            i4++;
                        } else if (d2 < 540.0d) {
                            i5++;
                        } else {
                            i6++;
                        }
                    }
                } catch (JSONException unused) {
                    i = i7;
                    i7 = i6;
                    LogUtil.b("UserLabelHelper", "getSportSpeedDistribution JSONException");
                    i6 = i7;
                    i7 = i;
                    jSONArray.put(i7);
                    jSONArray.put(i2);
                    jSONArray.put(i3);
                    jSONArray.put(i4);
                    jSONArray.put(i5);
                    jSONArray.put(i6);
                    return jSONArray.toString();
                }
            }
        } catch (JSONException unused2) {
            i = 0;
            i2 = 0;
            i3 = 0;
            i4 = 0;
            i5 = 0;
        }
        jSONArray.put(i7);
        jSONArray.put(i2);
        jSONArray.put(i3);
        jSONArray.put(i4);
        jSONArray.put(i5);
        jSONArray.put(i6);
        return jSONArray.toString();
    }

    private static String a(MotionPath motionPath, int i, String str, int i2) {
        if (motionPath == null) {
            return null;
        }
        ArrayList<HeartRateData> requestHeartRateList = motionPath.requestHeartRateList();
        if (koq.b(requestHeartRateList)) {
            return null;
        }
        int[] c2 = ffw.c(requestHeartRateList, 100, i, str, ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).getLocalUserInfo(), eme.b().getHeartPostureType(i2));
        if (c2 == null || c2.length < 5) {
            return null;
        }
        int i3 = c2[0];
        int i4 = c2[1];
        int i5 = c2[2];
        int i6 = c2[3];
        int i7 = c2[4];
        int i8 = i3 + i4 + i5 + i6 + i7;
        if (i8 >= i) {
            i = i8;
        }
        if (i == 0) {
            return null;
        }
        int i9 = (i3 * 100) / i;
        int i10 = (i4 * 100) / i;
        int i11 = (i5 * 100) / i;
        int i12 = (i6 * 100) / i;
        int i13 = (i7 * 100) / i;
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(i13);
        jSONArray.put(i12);
        jSONArray.put(i11);
        jSONArray.put(i10);
        jSONArray.put(i9);
        jSONArray.put(((((100 - i9) - i10) - i11) - i12) - i13);
        return jSONArray.toString();
    }

    static class c implements HiUnSubscribeListener {
        private String b;
        private String e;

        c(String str, String str2) {
            this.e = str;
            this.b = str2;
        }

        @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
        public void onResult(boolean z) {
            LogUtil.a(this.e, this.b, Boolean.valueOf(z));
        }
    }
}
