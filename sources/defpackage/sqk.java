package defpackage;

import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Base64;
import android.util.SparseArray;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.huawei.basefitnessadvice.model.FitnessSummaryRecord;
import com.huawei.health.courseplanservice.api.RecordApi;
import com.huawei.health.device.fatscale.multiusers.MultiUsersManager;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.devicemgr.api.constant.HwGetDevicesMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.devicemgr.business.entity.HwGetDevicesParameter;
import com.huawei.health.ecologydevice.manager.MassageGunConfig;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.AddEvaluationResultReq;
import com.huawei.hwcloudmodel.model.unite.DelEvaluationResultReq;
import com.huawei.hwcloudmodel.model.unite.GetEvaluationResultReq;
import com.huawei.hwcloudmodel.model.unite.UserEvaluationResult;
import com.huawei.hwcloudmodel.utils.GetEvaluationResultRsp;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.adapter.SportsStatisticsCallback;
import com.huawei.operation.beans.WebViewConfig;
import com.huawei.operation.ble.BleConstants;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginachievement.manager.model.ParsedFieldTag;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class sqk {
    public static final String c = "PluginOperationAdapterUtil";

    public static void d(long j, ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        jbs.a(BaseApplication.getContext()).d(new DelEvaluationResultReq(j), iCloudOperationResult);
    }

    public static void a(long j, final IBaseResponseCallback iBaseResponseCallback) {
        d(j, new ICloudOperationResult<CloudCommonReponse>() { // from class: sqk.5
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void operationResult(CloudCommonReponse cloudCommonReponse, String str, boolean z) {
                int i;
                String str2;
                if (z) {
                    IBaseResponseCallback.this.d(0, str);
                    return;
                }
                if (cloudCommonReponse != null) {
                    i = cloudCommonReponse.getResultCode().intValue();
                    str2 = cloudCommonReponse.getResultDesc();
                } else {
                    i = Constants.CODE_UNKNOWN_ERROR;
                    str2 = "unknown error";
                }
                IBaseResponseCallback.this.d(i, str2);
            }
        });
    }

    public static boolean a() {
        String str = c;
        LogUtil.a(str, "isBindDevice");
        List<DeviceInfo> b = jfq.c().b(HwGetDevicesMode.ALL_DEVICES, (HwGetDevicesParameter) null, str);
        if (b != null) {
            LogUtil.a(str, "isBindDevice deviceInfoList size ", Integer.valueOf(b.size()));
            if (b.size() > 0) {
                return true;
            }
        }
        return sqg.c(HealthDevice.HealthDeviceKind.HDK_UNKNOWN);
    }

    public static String c() {
        cfi currentUser = MultiUsersManager.INSTANCE.getCurrentUser();
        String i = MultiUsersManager.INSTANCE.getMainUser().i();
        if (!TextUtils.isEmpty(currentUser.i())) {
            return (TextUtils.isEmpty(i) || i.equals(currentUser.i())) ? "0" : currentUser.i();
        }
        LogUtil.a(c, "getCurrentUserUuid is null");
        MultiUsersManager.INSTANCE.setCurrentUser(MultiUsersManager.INSTANCE.getMainUser());
        return "0";
    }

    public static void a(long j, long j2, int i, IBaseResponseCallback iBaseResponseCallback) {
        String str = c;
        LogUtil.a(str, "getHealthData mStartTime = ", Long.valueOf(j), " mEndTime = ", Long.valueOf(j2));
        long j3 = j2 - j;
        double d = (j3 * 1.0d) / 8.64E7d;
        LogUtil.c(str, "dayCount = ", Double.valueOf(d));
        if (j3 < 0 || d > 3.0d) {
            iBaseResponseCallback.d(1001, null);
            return;
        }
        int[] iArr = {4, 5, 8};
        if (b(new int[]{7, 10}, i)) {
            e(sqg.c(j, j2, i), i, iBaseResponseCallback);
        } else if (b(iArr, i)) {
            sqg.a(sqg.d(j, j2, i), i, iBaseResponseCallback);
        } else {
            iBaseResponseCallback.d(1001, null);
        }
    }

    public static void e(HiDataReadOption hiDataReadOption, final int i, final IBaseResponseCallback iBaseResponseCallback) {
        HiHealthManager.d(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: sqk.1
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i2, Object obj, int i3, int i4) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i2, int i3) {
                LogUtil.c(sqk.c, "getHealthData data = ", obj);
                if (obj == null) {
                    IBaseResponseCallback.this.d(0, null);
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray.size() <= 0) {
                    LogUtil.h(sqk.c, "getHealthData The return run data is empty");
                    IBaseResponseCallback.this.d(0, null);
                } else {
                    sqg.ekE_(i, sparseArray, IBaseResponseCallback.this);
                }
            }
        });
    }

    public static boolean b(int[] iArr, int i) {
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    public static void b(String str, String str2, int i, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a(c, "getHealthStat startDay = ", str, " endDay = ", str2);
        if (!sqg.d(str, str2, 10)) {
            iBaseResponseCallback.d(1001, null);
            return;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        try {
            Date parse = simpleDateFormat.parse(str);
            Date parse2 = simpleDateFormat.parse(str2);
            long e = sqg.e(parse);
            long a2 = sqg.a(parse2);
            if (b(new int[]{3, 9}, i)) {
                sqg.a(sqg.d(e, a2, i), i, iBaseResponseCallback);
            } else {
                iBaseResponseCallback.d(1001, null);
            }
        } catch (ParseException e2) {
            LogUtil.b(c, e2.getMessage());
            iBaseResponseCallback.d(1001, null);
        }
    }

    public static void a(final String str, String str2, final String str3, final IBaseResponseCallback iBaseResponseCallback) {
        String str4 = c;
        LogUtil.a(str4, "getCourseRecommend");
        if (iBaseResponseCallback == null) {
            LogUtil.h(str4, "getCourseRecommend context or callback is null");
        } else if (dcg.d(str) == null) {
            LogUtil.a(str4, "courseRecommended is null");
            ResourceManager.e().TL_(new Handler(Looper.getMainLooper()), new MassageGunConfig.MassageGunCallback() { // from class: sqn
                @Override // com.huawei.health.ecologydevice.manager.MassageGunConfig.MassageGunCallback
                public final void onMassageGunCallback(String str5) {
                    sqk.c(IBaseResponseCallback.this, str, str3, str5);
                }
            });
        } else {
            LogUtil.a(str4, "The course recommendation information already exists");
            e(str, str3, iBaseResponseCallback);
        }
    }

    static /* synthetic */ void c(IBaseResponseCallback iBaseResponseCallback, String str, String str2, String str3) {
        boolean a2 = dcg.a(str3);
        LogUtil.a(c, "handMassageGunMap result ", Boolean.valueOf(a2));
        if (!a2) {
            iBaseResponseCallback.d(Constants.CODE_UNKNOWN_ERROR, null);
        } else {
            e(str, str2, iBaseResponseCallback);
        }
    }

    public static void e(String str, String str2, IBaseResponseCallback iBaseResponseCallback) {
        String a2 = dcg.a(str2, str);
        if (!TextUtils.isEmpty(a2)) {
            iBaseResponseCallback.d(0, a2);
        } else {
            iBaseResponseCallback.d(Constants.CODE_UNKNOWN_ERROR, null);
        }
    }

    public static void a(final IBaseResponseCallback iBaseResponseCallback) {
        HiHealthManager.d(BaseApplication.getContext()).fetchUserData(new HiCommonListener() { // from class: sqk.3
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                if (obj == null) {
                    IBaseResponseCallback.this.d(0, null);
                    return;
                }
                List list = (List) obj;
                if (list.size() <= 0) {
                    LogUtil.h(sqk.c, "getUserInfo The return run data is empty");
                    IBaseResponseCallback.this.d(0, null);
                    return;
                }
                int height = ((HiUserInfo) list.get(0)).getHeight();
                int unitType = ((HiUserInfo) list.get(0)).getUnitType();
                float weight = ((HiUserInfo) list.get(0)).getWeight();
                long createTime = ((HiUserInfo) list.get(0)).getCreateTime();
                JSONArray jSONArray = new JSONArray();
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("height", height);
                    jSONObject.put("unitType", unitType);
                    jSONObject.put("weight", weight);
                    jSONObject.put(ParsedFieldTag.TASK_MODIFY_TIME, createTime);
                    jSONArray.put(jSONObject);
                    JSONObject jSONObject2 = new JSONObject();
                    try {
                        jSONObject2.put("data", jSONArray);
                        IBaseResponseCallback.this.d(0, jSONObject2);
                    } catch (JSONException e) {
                        LogUtil.b(sqk.c, "getUserInfo dataObject JSONException! exception = ", e.getMessage());
                        IBaseResponseCallback.this.d(Constants.CODE_UNKNOWN_ERROR, null);
                    }
                } catch (JSONException e2) {
                    LogUtil.b(sqk.c, "getUserInfo jsonObject JSONException! exception = ", e2.getMessage());
                    IBaseResponseCallback.this.d(Constants.CODE_UNKNOWN_ERROR, null);
                }
            }
        });
    }

    public static WebViewConfig d() {
        String str;
        try {
            if (!Utils.o()) {
                str = BaseApplication.getContext().getFilesDir().getCanonicalPath() + File.separator + "lightcloud" + File.separator + "servicefw";
            } else {
                str = BaseApplication.getContext().getFilesDir().getCanonicalPath() + File.separator + "lightcloud" + File.separator + "servicefwo";
            }
        } catch (IOException unused) {
            LogUtil.b(c, "IOException");
            str = "";
        }
        String str2 = c;
        LogUtil.a(str2, "queryWebViewConfig");
        String t = CommonUtil.t(str + File.separator + "WebViewConfig.txt");
        if (TextUtils.isEmpty(t)) {
            LogUtil.h(str2, "queryWebViewConfig str is null");
            return null;
        }
        try {
            return (WebViewConfig) HiJsonUtil.e(t, WebViewConfig.class);
        } catch (JsonSyntaxException e) {
            LogUtil.b(c, "JsonSyntaxException, exception is ", e.getMessage());
            return null;
        } catch (Exception unused2) {
            LogUtil.b(c, "queryWebViewConfig error");
            return null;
        }
    }

    public static void c(long j, final IBaseResponseCallback iBaseResponseCallback) {
        b(j, new ICloudOperationResult<GetEvaluationResultRsp>() { // from class: sqk.2
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void operationResult(GetEvaluationResultRsp getEvaluationResultRsp, String str, boolean z) {
                int i;
                String str2;
                if (z) {
                    String json = new Gson().toJson(getEvaluationResultRsp);
                    try {
                        IBaseResponseCallback.this.d(0, Base64.encodeToString(json.getBytes("UTF-8"), 0));
                        return;
                    } catch (Exception unused) {
                        LogUtil.a(sqk.c, "Base64.encodeToString error:", json);
                        return;
                    }
                }
                if (getEvaluationResultRsp != null) {
                    i = getEvaluationResultRsp.getResultCode().intValue();
                    str2 = getEvaluationResultRsp.getResultDesc();
                } else {
                    i = Constants.CODE_UNKNOWN_ERROR;
                    str2 = "unknown error";
                }
                IBaseResponseCallback.this.d(i, str2);
            }
        });
    }

    public static void b(long j, ICloudOperationResult<GetEvaluationResultRsp> iCloudOperationResult) {
        jbs.a(BaseApplication.getContext()).d(new GetEvaluationResultReq(j), iCloudOperationResult);
    }

    public static void c(long j, long j2, final String str, final SportsStatisticsCallback sportsStatisticsCallback) {
        RecordApi recordApi = (RecordApi) Services.a("CoursePlanService", RecordApi.class);
        if (recordApi == null) {
            LogUtil.h(c, "getFitnessSumData recordApi is null.");
        } else {
            recordApi.acquireSummaryFitnessRecord(j, j2, new IBaseResponseCallback() { // from class: sqk.4
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public void d(int i, Object obj) {
                    LogUtil.a(sqk.c, "onResponse errorCode", Integer.valueOf(i));
                    if (obj == null) {
                        LogUtil.h(sqk.c, "get fitness data is null");
                        return;
                    }
                    FitnessSummaryRecord fitnessSummaryRecord = obj instanceof FitnessSummaryRecord ? (FitnessSummaryRecord) obj : null;
                    if (fitnessSummaryRecord != null && fitnessSummaryRecord.acquireRecordsCount() > 0) {
                        LogUtil.a(sqk.c, "record.acquireRecordsCount() :", Long.valueOf(fitnessSummaryRecord.acquireRecordsCount()));
                        SportsStatisticsCallback.this.callSportSumDataJsFunction(i, fitnessSummaryRecord, str);
                    } else {
                        LogUtil.a(sqk.c, "record is null or count = 0");
                        SportsStatisticsCallback.this.callSportSumDataJsFunction(i, "", str);
                    }
                }
            });
        }
    }

    public static void b(long j, String str, final IBaseResponseCallback iBaseResponseCallback) {
        b(j, str, new ICloudOperationResult<CloudCommonReponse>() { // from class: sqk.7
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void operationResult(CloudCommonReponse cloudCommonReponse, String str2, boolean z) {
                int i;
                String str3;
                if (z) {
                    IBaseResponseCallback.this.d(0, str2);
                    return;
                }
                if (cloudCommonReponse != null) {
                    i = cloudCommonReponse.getResultCode().intValue();
                    str3 = cloudCommonReponse.getResultDesc();
                } else {
                    i = Constants.CODE_UNKNOWN_ERROR;
                    str3 = "unknown error";
                }
                IBaseResponseCallback.this.d(i, str3);
            }
        });
    }

    public static void b(long j, String str, ICloudOperationResult<CloudCommonReponse> iCloudOperationResult) {
        UserEvaluationResult userEvaluationResult = new UserEvaluationResult(j, str);
        ArrayList arrayList = new ArrayList();
        arrayList.add(userEvaluationResult);
        jbs.a(BaseApplication.getContext()).e(new AddEvaluationResultReq(arrayList), iCloudOperationResult);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:8:0x003a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void c(java.lang.String r4, java.lang.String r5, com.huawei.hwbasemgr.IBaseResponseCallback r6) {
        /*
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            org.json.JSONObject r1 = new org.json.JSONObject     // Catch: org.json.JSONException -> L1b
            r1.<init>(r5)     // Catch: org.json.JSONException -> L1b
            java.lang.String r5 = "type"
            int r5 = r1.getInt(r5)     // Catch: org.json.JSONException -> L1b
            java.lang.String r2 = "value"
            org.json.JSONObject r0 = r1.getJSONObject(r2)     // Catch: org.json.JSONException -> L19
            goto L2d
        L19:
            r1 = move-exception
            goto L1e
        L1b:
            r5 = move-exception
            r1 = r5
            r5 = 0
        L1e:
            java.lang.String r2 = defpackage.sqk.c
            java.lang.String r3 = "saveMeasureData fail exception = "
            java.lang.String r1 = r1.getMessage()
            java.lang.Object[] r1 = new java.lang.Object[]{r3, r1}
            com.huawei.hwlogsmodel.LogUtil.b(r2, r1)
        L2d:
            defpackage.dks.o(r4)
            cjx r1 = defpackage.cjx.e()
            com.huawei.health.device.model.HealthDevice r4 = r1.a(r4)
            if (r4 == 0) goto L60
            dfd r1 = new dfd
            r1.<init>(r5)
            r1.e(r6)
            r6 = 2104(0x838, float:2.948E-42)
            if (r5 != r6) goto L51
            ded r6 = new ded
            r6.<init>()
            c(r0, r6)
            r1.onDataChanged(r4, r6)
        L51:
            r6 = 2103(0x837, float:2.947E-42)
            if (r5 != r6) goto L60
            dec r5 = new dec
            r5.<init>()
            a(r0, r5)
            r1.onDataChanged(r4, r5)
        L60:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.sqk.c(java.lang.String, java.lang.String, com.huawei.hwbasemgr.IBaseResponseCallback):void");
    }

    private static void c(JSONObject jSONObject, ded dedVar) {
        double d;
        try {
            d = jSONObject.getDouble(BleConstants.TEMPERATURE);
        } catch (JSONException e) {
            LogUtil.b(c, "saveBodyTempData fail exception = ", e.getMessage());
            d = 0.0d;
        }
        dedVar.setStartTime(System.currentTimeMillis());
        dedVar.setEndTime(System.currentTimeMillis());
        dedVar.a((float) d);
    }

    private static void a(JSONObject jSONObject, dec decVar) {
        double d;
        try {
            d = jSONObject.getDouble(BleConstants.BLOOD_OXYGEN);
        } catch (JSONException e) {
            LogUtil.b(c, "saveBloodOxygenData fail exception = ", e.getMessage());
            d = 0.0d;
        }
        decVar.setStartTime(System.currentTimeMillis());
        decVar.setEndTime(System.currentTimeMillis());
        decVar.c((int) d);
    }
}
