package defpackage;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.os.Bundle;
import android.os.OperationCanceledException;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.android.airsharing.api.IEventListener;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.userprofilemgr.model.BaseResponseCallback;
import com.huawei.hiai.awareness.client.AwarenessEnvelope;
import com.huawei.hiai.awareness.client.AwarenessManager;
import com.huawei.hiai.awareness.client.AwarenessRequest;
import com.huawei.hiai.awareness.client.AwarenessServiceConnection;
import com.huawei.hiai.awareness.client.OnEnvelopeReceiver;
import com.huawei.hiai.awareness.client.ProfileLabel;
import com.huawei.hidatamanager.HiDataManager;
import com.huawei.hidatamanager.util.Const;
import com.huawei.hms.network.embedded.y5;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class dxy {
    private static volatile dxy d;

    /* renamed from: a, reason: collision with root package name */
    private AwarenessManager f11902a;
    private volatile boolean f;
    private HiDataManager h;
    private Context j;
    private static final Object e = new Object();
    private static HashMap<String, String> c = new HashMap<>();
    private static HashMap<String, String> b = new HashMap<>();

    static {
        c.put("pengine_daily_work_hour", "content://com.huawei.pengine.UserProfileProvider/DailyAverageWorkingHour");
        c.put("pengine_sport_frequency", "content://com.huawei.pengine.UserProfileProvider/SportsFrequency");
        c.put("pengine_sport_type_preference", "content://com.huawei.pengine.UserProfileProvider/SportsTypePreference");
        c.put("pengine_work_day", "content://com.huawei.pengine.UserProfileProvider/WorkDay");
        c.put("pengine_commuting_mode", "content://com.huawei.pengine.UserProfileProvider/CommutingMode");
        c.put("pengine_leave_home_time", "content://com.huawei.pengine.UserProfileProvider/LeaveHomeTime");
        c.put("pengine_arrive_home_time", "content://com.huawei.pengine.UserProfileProvider/ArriveHomeTime");
        c.put("pengine_arrive_company_time", "content://com.huawei.pengine.UserProfileProvider/ArriveCompanyTime");
        c.put("pengine_leave_company_time", "content://com.huawei.pengine.UserProfileProvider/LeaveCompanyTime");
        c.put("pengine_commuting_duration", "content://com.huawei.pengine.UserProfileProvider/CommutingDuration");
        b.put("pengine_daily_work_hour", "DailyAverageWorkingHour");
        b.put("pengine_sport_frequency", "SportsFrequency");
        b.put("pengine_sport_type_preference", "SportsTypePreference");
        b.put("pengine_work_day", "WorkDay");
        b.put("pengine_commuting_mode", "CommutingMode");
        b.put("pengine_leave_home_time", "LeaveHomeTime");
        b.put("pengine_arrive_home_time", "ArriveHomeTime");
        b.put("pengine_arrive_company_time", "ArriveCompanyTime");
        b.put("pengine_leave_company_time", "LeaveCompanyTime");
        b.put("pengine_commuting_duration", "CommutingDuration");
    }

    private dxy(Context context) {
        this.j = context;
        i();
    }

    public static dxy b() {
        LogUtil.a("HiH_PengineUserLabelMgr", "QueryMemberManager getInstance");
        if (d == null) {
            synchronized (e) {
                if (d == null) {
                    d = new dxy(BaseApplication.e());
                }
            }
        }
        return d;
    }

    private void i() {
        if (f()) {
            HiDataManager hiDataManager = HiDataManager.getInstance();
            this.h = hiDataManager;
            hiDataManager.init(this.j);
            this.f11902a = new AwarenessManager(this.j);
        }
    }

    private boolean f() {
        if (CommonUtil.ax()) {
            return true;
        }
        LogUtil.a("HiH_PengineUserLabelMgr", "CommonUtil.isEmui90(), version = ", Integer.valueOf(CommonUtil.ah()));
        return false;
    }

    public void d() {
        HiDataManager hiDataManager = this.h;
        if (hiDataManager != null) {
            hiDataManager.destroy();
        }
    }

    public void b(String str) {
        HiDataManager hiDataManager = this.h;
        if (hiDataManager == null) {
            return;
        }
        if (str == null) {
            LogUtil.h("HiH_PengineUserLabelMgr", "saveTrackDataToOdmf JsonData = null");
        } else {
            LogUtil.a("HiH_PengineUserLabelMgr", "saveBindingDeviceToOdmf, result = ", Integer.valueOf(hiDataManager.inputSourceData(Const.RawDataType.HEALTH_EVENT_RECORD, str)));
        }
    }

    public void c(String str) {
        HiDataManager hiDataManager = this.h;
        if (hiDataManager == null) {
            return;
        }
        if (str == null) {
            LogUtil.h("HiH_PengineUserLabelMgr", "saveBindingDeviceToOdmf JsonData = null");
        } else {
            LogUtil.a("HiH_PengineUserLabelMgr", "saveBindingDeviceToOdmf, result = ", Integer.valueOf(hiDataManager.inputSourceData(1000L, str)));
        }
    }

    public void g() {
        LogUtil.a("HiH_PengineUserLabelMgr", "UploadSportEvent enter");
        if (this.h != null) {
            o();
            l();
            h();
        }
    }

    private void o() {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put("121.525");
        jSONArray.put("25.0392");
        JSONArray jSONArray2 = new JSONArray();
        jSONArray2.put(10);
        jSONArray2.put(20);
        jSONArray2.put(10);
        jSONArray2.put(10);
        jSONArray2.put(20);
        jSONArray2.put(30);
        JSONArray jSONArray3 = new JSONArray();
        jSONArray3.put(3);
        jSONArray3.put(4);
        jSONArray3.put(3);
        jSONArray3.put(4);
        jSONArray3.put(5);
        jSONArray3.put(6);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("SportType", 257);
            jSONObject.put("SportStartTime", "2018-10-10 10:10:10");
            jSONObject.put("DeviceID", "xxxxxx");
            jSONObject.put("SportStartGPS", jSONArray);
            jSONObject.put("SportDuration", 3600);
            jSONObject.put("HeartDistribution", jSONArray2);
            jSONObject.put("SportSpeedDistribution", jSONArray3);
            jSONObject.put("HeatQuantity", 1200);
            LogUtil.a("HiH_PengineUserLabelMgr", "uploadWalkEvent, jsonObjectWalk = ", jSONObject.toString());
        } catch (JSONException unused) {
            LogUtil.b("HiH_PengineUserLabelMgr", "uploadWalkEvent jsonObjectWalk JSONException");
        }
        LogUtil.a("HiH_PengineUserLabelMgr", "uploadWalkEvent sportType = 257 , resultWalk = ", Integer.valueOf(this.h.inputSourceData(Const.RawDataType.HEALTH_EVENT_RECORD, jSONObject.toString())));
    }

    private void l() {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put("21.525");
        jSONArray.put("55.0392");
        JSONArray jSONArray2 = new JSONArray();
        jSONArray2.put(20);
        jSONArray2.put(10);
        jSONArray2.put(20);
        jSONArray2.put(10);
        jSONArray2.put(20);
        jSONArray2.put(20);
        JSONArray jSONArray3 = new JSONArray();
        jSONArray3.put(1);
        jSONArray3.put(2);
        jSONArray3.put(3);
        jSONArray3.put(4);
        jSONArray3.put(5);
        jSONArray3.put(6);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("SportType", 258);
            jSONObject.put("SportStartTime", "2018-11-11 11:11:11");
            jSONObject.put("DeviceID", "11111111");
            jSONObject.put("SportStartGPS", jSONArray);
            jSONObject.put("SportDuration", 600);
            jSONObject.put("HeartDistribution", jSONArray2);
            jSONObject.put("SportSpeedDistribution", jSONArray3);
            jSONObject.put("HeatQuantity", 2300);
            LogUtil.a("HiH_PengineUserLabelMgr", "upLoadRunEvent, jsonObjectRun = ", jSONObject.toString());
        } catch (JSONException unused) {
            LogUtil.b("HiH_PengineUserLabelMgr", "upLoadRunEvent jsonObjectRun JSONException");
        }
        LogUtil.a("HiH_PengineUserLabelMgr", "upLoadRunEvent sportType = 258 , resultRun = ", Integer.valueOf(this.h.inputSourceData(Const.RawDataType.HEALTH_EVENT_RECORD, jSONObject.toString())));
    }

    private void h() {
        JSONArray jSONArray = new JSONArray();
        jSONArray.put("22.123");
        jSONArray.put("82.0312");
        JSONArray jSONArray2 = new JSONArray();
        jSONArray2.put(30);
        jSONArray2.put(10);
        jSONArray2.put(20);
        jSONArray2.put(10);
        jSONArray2.put(10);
        jSONArray2.put(20);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("SportType", 1001);
            jSONObject.put("SportStartTime", "2018-12-12 12:12:12");
            jSONObject.put("DeviceID", "22222222");
            jSONObject.put("SportStartGPS", jSONArray);
            jSONObject.put("SportDuration", y5.h);
            jSONObject.put("HeartDistribution", jSONArray2);
            jSONObject.put("SportSpeedDistribution", (Object) null);
            jSONObject.put("HeatQuantity", IEventListener.EVENT_ID_MSDP_PORT_NUMBER);
            LogUtil.a("HiH_PengineUserLabelMgr", "upLoadExerciseEvent, jsonObjectExercise = ", jSONObject.toString());
        } catch (JSONException unused) {
            LogUtil.b("HiH_PengineUserLabelMgr", "upLoadExerciseEvent jsonObjectExercise JSONException");
        }
        LogUtil.a("HiH_PengineUserLabelMgr", "upLoadExerciseEvent sportType = 1001 , resultExercise = ", Integer.valueOf(this.h.inputSourceData(Const.RawDataType.HEALTH_EVENT_RECORD, jSONObject.toString())));
    }

    public void c() {
        LogUtil.a("HiH_PengineUserLabelMgr", "UploadBindingDevice enter");
        if (this.h == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("DeviceName", "Huawei Watch 2");
            jSONObject.put("DeviceID", "xxxxxx");
            jSONObject.put("DeviceType", 0);
            jSONObject.put("DeviceActiveTime", "2018-10-19 23:52:18");
            jSONObject.put("DeviceBrand", "Huawei");
            LogUtil.a("HiH_PengineUserLabelMgr", "UploadBindingDevice, jsonObject = ", jSONObject.toString());
        } catch (JSONException unused) {
            LogUtil.b("HiH_PengineUserLabelMgr", "UploadBindingDevice JSONException");
        }
        LogUtil.a("HiH_PengineUserLabelMgr", "UploadBindingDevice DeviceName = Huawei Watch 2 , result = ", Integer.valueOf(this.h.inputSourceData(1000L, jSONObject.toString())));
    }

    public void e() {
        LogUtil.a("HiH_PengineUserLabelMgr", "UploadSleepData enter");
        if (this.h == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        jSONArray.put(5);
        jSONArray.put(45);
        jSONArray.put(50);
        try {
            jSONObject.put("SleepStartTime", "2018-11-19 23:52:18");
            jSONObject.put("SleepEndTime", "2018-11-20 08:05:18");
            jSONObject.put("SleepQualityDistribution", jSONArray);
            LogUtil.a("HiH_PengineUserLabelMgr", "UploadSleepData, jsonObject = ", jSONObject.toString());
        } catch (JSONException unused) {
            LogUtil.b("HiH_PengineUserLabelMgr", "UploadSleepData JSONException");
        }
        LogUtil.a("HiH_PengineUserLabelMgr", "UploadSleepData , result = ", Integer.valueOf(this.h.inputSourceData(Const.RawDataType.HEALTH_SLEEP_RECORD, jSONObject.toString())));
    }

    public HashMap<String, String> a() {
        LogUtil.a("HiH_PengineUserLabelMgr", "getLabelByKey enter");
        HashMap<String, String> hashMap = new HashMap<>();
        if (this.h == null) {
            return hashMap;
        }
        ContentResolver contentResolver = this.j.getContentResolver();
        for (Map.Entry<String, String> entry : c.entrySet()) {
            Uri parse = Uri.parse(entry.getValue());
            Cursor cursor = null;
            try {
                try {
                    cursor = contentResolver.query(parse, null, null, null, null);
                    if (cursor != null) {
                        while (cursor.moveToNext()) {
                            String string = cursor.getString(cursor.getColumnIndex("uriValue"));
                            if (string != null) {
                                hashMap.put(entry.getKey(), string);
                            }
                        }
                    }
                } catch (Throwable th) {
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } catch (SQLException | OperationCanceledException e2) {
                LogUtil.b("HiH_PengineUserLabelMgr", "getLabelByKey SQLException | OperationCanceledException e = ", e2.getClass().getName());
                if (cursor != null) {
                }
            }
            if (cursor != null) {
                cursor.close();
            } else {
                LogUtil.h("HiH_PengineUserLabelMgr", "getLabelByKey cursor is null", " uri = ", parse);
            }
        }
        return hashMap;
    }

    public void e(BaseResponseCallback<HashMap<String, String>> baseResponseCallback) {
        if (this.h == null) {
            LogUtil.h("HiH_PengineUserLabelMgr", "getLabels mHiDataManager == null");
            a(-1, baseResponseCallback, null);
            return;
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final boolean[] zArr = {false};
        ThreadPoolManager.d().execute(new Runnable() { // from class: dxy.1
            @Override // java.lang.Runnable
            public void run() {
                zArr[0] = dxy.this.f11902a.connectService(new AwarenessServiceConnection() { // from class: dxy.1.3
                    @Override // com.huawei.hiai.awareness.client.AwarenessServiceConnection
                    public void onConnected() {
                        LogUtil.a("HiH_PengineUserLabelMgr", "isConnected true");
                        dxy.this.f = true;
                        if (countDownLatch != null) {
                            countDownLatch.countDown();
                        }
                    }

                    @Override // com.huawei.hiai.awareness.client.AwarenessServiceConnection
                    public void onDisconnected() {
                        LogUtil.a("HiH_PengineUserLabelMgr", "isConnected false");
                        dxy.this.f = false;
                        if (countDownLatch != null) {
                            countDownLatch.countDown();
                        }
                    }
                });
            }
        });
        try {
            LogUtil.a("HiH_PengineUserLabelMgr", "isCountZero= ", Boolean.valueOf(countDownLatch.await(5L, TimeUnit.SECONDS)), "isConnected= ", Boolean.valueOf(this.f));
        } catch (InterruptedException e2) {
            ReleaseLogUtil.c("HiH_PengineUserLabelMgr", "interruptedException", ExceptionUtils.d(e2));
        } catch (Exception e3) {
            ReleaseLogUtil.c("HiH_PengineUserLabelMgr", "exception:", ExceptionUtils.d(e3));
        }
        LogUtil.a("HiH_PengineUserLabelMgr", "getLabels isConnectService:", Boolean.valueOf(zArr[0]));
        if (!zArr[0]) {
            a(-1, baseResponseCallback, null);
        } else {
            b(baseResponseCallback);
        }
    }

    private void b(final BaseResponseCallback<HashMap<String, String>> baseResponseCallback) {
        final ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Map.Entry<String, String> entry : b.entrySet()) {
            arrayList.add(entry.getKey());
            arrayList2.add(entry.getValue());
        }
        if (koq.b(arrayList)) {
            LogUtil.h("HiH_PengineUserLabelMgr", "keyList isEmpty");
            a(-1, baseResponseCallback, null);
            return;
        }
        final String[] strArr = (String[]) arrayList2.toArray(new String[arrayList2.size()]);
        LogUtil.a("HiH_PengineUserLabelMgr", "getLabels addLabels:", Arrays.toString(strArr));
        ProfileLabel create = ProfileLabel.create(300000001);
        create.putArg("labels", strArr);
        try {
            AwarenessRequest profileLabel = AwarenessRequest.getProfileLabel(create, new OnEnvelopeReceiver.Stub() { // from class: dxy.5
                @Override // com.huawei.hiai.awareness.client.OnEnvelopeReceiver
                public void onReceive(AwarenessEnvelope awarenessEnvelope) {
                    dxy.this.j();
                    if (awarenessEnvelope != null) {
                        dxy.this.e(awarenessEnvelope, baseResponseCallback, strArr, arrayList);
                    } else {
                        LogUtil.h("HiH_PengineUserLabelMgr", "awarenessEnvelope is null");
                        dxy.this.a(-1, baseResponseCallback, null);
                    }
                }
            });
            if (this.f) {
                ReleaseLogUtil.e("HiH_PengineUserLabelMgr", "isDispatch: ", Boolean.valueOf(this.f11902a.dispatch(profileLabel)));
            }
        } catch (Exception e2) {
            ReleaseLogUtil.c("HiH_PengineUserLabelMgr", "Exception", ExceptionUtils.d(e2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(AwarenessEnvelope awarenessEnvelope, BaseResponseCallback<HashMap<String, String>> baseResponseCallback, String[] strArr, List<String> list) {
        Bundle args = awarenessEnvelope.getArgs();
        LogUtil.a("HiH_PengineUserLabelMgr", "parseEnvelope begin");
        if (args == null) {
            LogUtil.h("HiH_PengineUserLabelMgr", "args is null");
            a(-1, baseResponseCallback, null);
            return;
        }
        byte[] byteArray = args.getByteArray("Content");
        if (byteArray == null) {
            LogUtil.h("HiH_PengineUserLabelMgr", "bytes == null ");
            a(-1, baseResponseCallback, null);
            return;
        }
        try {
            List list2 = (List) new Gson().fromJson(new String(byteArray), new TypeToken<List<String>>() { // from class: dxy.2
            }.getType());
            if (koq.b(list2)) {
                LogUtil.h("HiH_PengineUserLabelMgr", "list isEmpty ");
                a(-1, baseResponseCallback, null);
                return;
            }
            if (list2.size() != list.size()) {
                LogUtil.h("HiH_PengineUserLabelMgr", "data mismatch");
                a(-1, baseResponseCallback, null);
                return;
            }
            HashMap<String, String> hashMap = new HashMap<>();
            for (int i = 0; i < list2.size(); i++) {
                String str = list.get(i);
                String str2 = (String) list2.get(i);
                LogUtil.a("HiH_PengineUserLabelMgr", "key: ", str, " label: ", strArr[i], " content: ", str2);
                if (!TextUtils.isEmpty(str2)) {
                    hashMap.put(str, str2);
                }
            }
            a(0, baseResponseCallback, hashMap);
        } catch (Exception e2) {
            ReleaseLogUtil.c("HiH_PengineUserLabelMgr", "awarenessEnvelope exception" + ExceptionUtils.d(e2));
            a(-1, baseResponseCallback, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, BaseResponseCallback<HashMap<String, String>> baseResponseCallback, HashMap<String, String> hashMap) {
        LogUtil.a("HiH_PengineUserLabelMgr", "callBack errorCode: ", Integer.valueOf(i));
        if (baseResponseCallback == null) {
            LogUtil.a("HiH_PengineUserLabelMgr", "callback == null ");
            return;
        }
        if (hashMap == null) {
            hashMap = new HashMap<>();
        }
        baseResponseCallback.onResponse(i, hashMap);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        LogUtil.a("HiH_PengineUserLabelMgr", "disconnectService");
        AwarenessManager awarenessManager = this.f11902a;
        if (awarenessManager != null) {
            awarenessManager.disconnectService();
        }
    }
}
