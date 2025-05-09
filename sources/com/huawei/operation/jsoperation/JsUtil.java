package com.huawei.operation.jsoperation;

import android.text.TextUtils;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.activity.WebViewActivity;
import com.huawei.operation.utils.WebViewUtils;
import health.compact.a.HiDateUtil;
import java.text.ParseException;

/* loaded from: classes5.dex */
public class JsUtil {
    public static final String ANNUAL_YEAR = "annual";
    public static final String APP_ID = "appID";
    public static final int BLOOD_PRESSURE_DATA = 5;
    public static final String BREATHE_RHYTHM = "breathe_rhythm";
    public static final String BREATHE_TIME = "breathe_time";
    public static final String DATA_LIST = "datas";
    public static final String DURATION = "duration";
    public static final String END_TIME = "endTime";
    public static final String FUNCTION_RES = "functionRes";
    public static final String MANUFACTURER = "manufacturer";
    public static final String RESULT_CODE = "result_code";
    public static final String SCORE = "score";
    public static final String SERVICE_NAME = "serviceName";
    public static final String START_TIME = "startTime";
    public static final int SUGAR_DATA = 4;
    public static final String SUGGESTION_DATE = "date";
    public static final String SUGGESTION_TOTAL_CALORIE = "totalcalorie";
    public static final String SUGGESTION_TOTAL_DURATION = "totalduration";
    public static final String SUMMARIES_KEY = "summaries";
    private static final String TAG = "PluginOperation_JsUtil";
    public static final int WEIGHT_FAT_DATA = 8;

    /* loaded from: classes9.dex */
    public static final class DataFunc {
        public static final String ANNUAL_REPORT_DATA = "ANNUAL_REPORT_DATA";
        public static final String FITNESS_DATA = "FITNESS_DATA";
        public static final String HEALTH_DATA = "HEALTH_DATA";
        public static final String HEALTH_STAT = "HEALTH_STAT";
        public static final String INSERT_HEALTH_DATA = "INSERT_HEALTH_DATA";
        public static final String MOTION_PATH_DATA = "MOTION_PATH_DATA";
        public static final String REGISTER_DATA_CLIENT = "REGISTER_DATA_CLIENT";
        public static final String SPORT_DATA = "SPORT_DATA";
        public static final String USER_INFO_DATA = "USER_INFO_DATA";
    }

    /* loaded from: classes9.dex */
    public static final class ServiceType {
        public static final String DATA = "DATA";
        public static final String STRESS = "STRESS";
    }

    /* loaded from: classes9.dex */
    public static final class StressFunc {
        public static final String BREATHE_CONTROL = "BREATHE_CONTROL";
        public static final String CALIBRATION_CONTROL = "CALIBRATION_CONTROL";
        public static final String CHECK_CALIBRATION = "CHECK_CALIBRATION";
        public static final String CHECK_CONNECTED = "CHECK_CONNECTED";
        public static final String GAME_CONTROL = "GAME_CONTROL";
        public static final String RELAX_CONTROL = "RELAX_CONTROL";
        public static final String RESET_CALIBRATION = "RESET_CALIBRATION";
        public static final String STRESS_CONTROL = "STRESS_CONTROL";
    }

    private JsUtil() {
    }

    private static <T> String getJsFunction(String str, int i, T t) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        if (t == null) {
            return WebViewUtils.getUrlForHtml(str, i + " , null");
        }
        return WebViewUtils.getUrlForHtml(str, i + " , " + HiJsonUtil.e(t));
    }

    public static <T> void runJsFunc(final WebViewActivity webViewActivity, String str, int i, T t) {
        final String jsFunction = getJsFunction(str, i, t);
        if (webViewActivity == null || TextUtils.isEmpty(jsFunction)) {
            return;
        }
        webViewActivity.runOnUiThread(new Runnable() { // from class: com.huawei.operation.jsoperation.JsUtil.1
            @Override // java.lang.Runnable
            public void run() {
                WebViewActivity.this.startLoadJs(jsFunction);
            }
        });
    }

    public static boolean checkAuth(WebViewActivity webViewActivity, String str, boolean z) {
        if (z) {
            return true;
        }
        runJsFunc(webViewActivity, str, 1003, "not auth");
        return false;
    }

    public static boolean checkParamIsLegal(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            try {
                int parseInt = Integer.parseInt(str);
                int parseInt2 = Integer.parseInt(str2);
                if (parseInt > parseInt2) {
                    return false;
                }
                try {
                    int b = HiDateUtil.b(parseInt, parseInt2, "yyyyMMdd");
                    if (b >= 1 && b <= 10) {
                        return true;
                    }
                    LogUtil.a(TAG, "JsUtil checkParamIsLegal dayCounts = ", Integer.valueOf(b));
                    return false;
                } catch (ParseException e) {
                    LogUtil.b(TAG, "JsUtil checkParamIsLegal ParseException :", e.getMessage());
                    return false;
                }
            } catch (NumberFormatException e2) {
                LogUtil.b(TAG, "JsUtil checkParamIsLegal NumberFormatException :", e2.getMessage());
            }
        }
        return false;
    }
}
