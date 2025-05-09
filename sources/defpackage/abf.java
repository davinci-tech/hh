package defpackage;

import android.os.Looper;
import android.os.Process;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public class abf {
    public static String b(String str) {
        return c("local_update_sync_result", 0, str, "json");
    }

    public static String c(String str) {
        return c("local_update_unstruct_data", 0, str, "json");
    }

    private static String c(String str, int i, String str2, String str3) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("business_id", str);
            jSONObject.put("returnCode", i);
            jSONObject.put("syncInfo", str2);
            jSONObject.put("syncInfoType", str3);
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject);
            return jSONArray.toString();
        } catch (JSONException unused) {
            abd.b("ReportUtil", "parseReportInfo " + str + " error: JSONException");
            return "";
        }
    }

    public static String e(String str) {
        return c("local_update_struct_data", 0, str, "json");
    }

    public static String a(String str, String str2, String str3) {
        String str4;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("syncType", str);
            jSONObject.put("dataTypeList", str2);
            jSONObject.put("dataTypeResultList", str3);
            d(jSONObject);
            str4 = jSONObject.toString();
        } catch (JSONException unused) {
            abd.b("ReportUtil", "parseEndSyncReportInfo error: JSONException");
            str4 = "";
        }
        return c("local_end_sync_result", 0, str4, "json");
    }

    public static String e(String str, int i) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("syncType", str);
            jSONObject.put("syncRsn", i);
        } catch (Exception e) {
            abd.b("ReportUtil", "reportSyncRsn err " + e.getMessage());
        }
        d(jSONObject);
        return c("local_syncRsn", 0, jSONObject.toString(), "");
    }

    public static String d(String str, String str2, String str3) {
        String str4;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("dataType", str);
            jSONObject.put("localCtag", str2);
            jSONObject.put("cloudCtag", str3);
            d(jSONObject);
            str4 = jSONObject.toString();
        } catch (JSONException unused) {
            abd.b("ReportUtil", "parseCtagInfoReportInfo error: JSONException");
            str4 = "";
        }
        return c("local_ctag_info", 0, str4, "json");
    }

    public static String a(String str, String str2, boolean z, boolean z2, boolean z3, int i, int i2, int i3, int i4, int i5) {
        String str3;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("dataType", str);
            jSONObject.put("syncToken", str2);
            jSONObject.put("isExpired", z);
            jSONObject.put("isIncrementallyQuery", z2);
            jSONObject.put("isRecycle", z3);
            jSONObject.put("localEtag", i);
            jSONObject.put("cloudEtag", i2);
            jSONObject.put("cloudAdded", i3);
            jSONObject.put("cloudModified", i4);
            jSONObject.put("cloudDeleted", i5);
            d(jSONObject);
            str3 = jSONObject.toString();
        } catch (JSONException unused) {
            abd.b("ReportUtil", "parseCloudChangesReportInfo error: JSONException");
            str3 = "";
        }
        return c("local_cloud_changes", 0, str3, "json");
    }

    public static String c(String str, boolean z, int i, int i2) {
        String str2;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("dataType", str);
            jSONObject.put("isSyncRecycleOn", z);
            jSONObject.put("cloudDeletedId", i);
            jSONObject.put("deleteResult", i2);
            d(jSONObject);
            str2 = jSONObject.toString();
        } catch (JSONException unused) {
            abd.b("ReportUtil", "parseCloudDeleteReportInfo error: JSONException");
            str2 = "";
        }
        return c("local_cloud_delete", 0, str2, "json");
    }

    public static String d(String str) {
        return c("local_local_changes", 0, str, "json");
    }

    public static String d(String str, List<Object> list) {
        String str2 = "";
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("syncType", str);
            if (list == null) {
                jSONObject.put("updateDataResultList", "");
            } else {
                jSONObject.put("updateDataResultList", list.toString());
            }
            d(jSONObject);
            str2 = jSONObject.toString();
        } catch (JSONException unused) {
            abd.b("ReportUtil", "parseUpdateDataResultsReportInfo error: JSONException");
        }
        return c("local_update_data_results", 0, str2, "json");
    }

    public static String b(String str, String str2, String str3, String str4) {
        String str5;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("syncType", str);
            jSONObject.put("dataType", str2);
            jSONObject.put("serviceDisconnectedTime", str3);
            jSONObject.put("traceId", str4);
            d(jSONObject);
            str5 = jSONObject.toString();
        } catch (JSONException unused) {
            abd.b("ReportUtil", "parseServiceDisconnectedTimeReportInfo error: JSONException");
            str5 = "";
        }
        return c("local_service_disconnected_time", 5, str5, "json");
    }

    public static String b(String str, String str2, int i, String str3) {
        String str4;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("dataType", str);
            jSONObject.put("interfacesName", str2);
            jSONObject.put("errorCode", i);
            jSONObject.put("errorMsg", str3);
            d(jSONObject);
            str4 = jSONObject.toString();
        } catch (Exception e) {
            abd.b("ReportUtil", "parseAppExceptionReportInfo error: " + e.getMessage());
            str4 = "";
        }
        return c("local_app_exception", 23, str4, "json");
    }

    public static String b(String str, String str2, String str3, int i) {
        String str4;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("dataType", str);
            jSONObject.put("interfacesName", str2);
            jSONObject.put("errorMsg", str3);
            jSONObject.put("errorCode", i);
            d(jSONObject);
            str4 = jSONObject.toString();
        } catch (JSONException unused) {
            abd.b("ReportUtil", "parseAppExceptionReportInfo error: JSONException");
            str4 = "";
        }
        return c("sdk_inner_exception", 25, str4, "json");
    }

    public static void d(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        try {
            jSONObject.put("3dAppTid", String.valueOf(Process.myTid()));
            jSONObject.put("3dAppPid", String.valueOf(Process.myPid()));
            jSONObject.put("is3dAppMainTid", String.valueOf(Thread.currentThread() == Looper.getMainLooper().getThread()));
        } catch (Exception e) {
            abd.b("ReportUtil", "appendProcessId err " + e.getMessage());
        }
    }
}
