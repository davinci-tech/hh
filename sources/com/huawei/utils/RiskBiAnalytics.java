package com.huawei.utils;

import android.text.TextUtils;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import health.compact.a.CommonUtil;
import health.compact.a.HuaweiHealth;
import health.compact.a.Utils;
import java.util.LinkedHashMap;

/* loaded from: classes7.dex */
public class RiskBiAnalytics {

    public enum FitPLANRiskType {
        RISK_TYPE_SEND_PLAN_DEVICE("1"),
        RISK_TYPE_FINISH_PLAN("2"),
        RISK_TYPE_COACH_ADVICE_EMPTY("3"),
        RISK_TYPE_COURSE_CHECK_IN("4"),
        RISK_TYPE_CREATE_PLAN("5");

        private final String mRiskType;

        FitPLANRiskType(String str) {
            this.mRiskType = str;
        }

        public String value() {
            return this.mRiskType;
        }
    }

    public static void c(String str, Object... objArr) {
        StringBuilder sb = new StringBuilder();
        for (Object obj : objArr) {
            sb.append(obj);
        }
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(10);
        a(linkedHashMap);
        linkedHashMap.put("FitPlanType", str);
        linkedHashMap.put("keyWords", "FitPlan");
        linkedHashMap.put("messages", sb.toString());
        OpAnalyticsUtil.getInstance().setEvent2nd(OperationKey.HEALTH_APP_AI_PLAN_RISK_WARNING_85070038.value(), linkedHashMap);
    }

    public static void a(LinkedHashMap<String, String> linkedHashMap) {
        if (CommonUtil.as()) {
            String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1011);
            if (!TextUtils.isEmpty(accountInfo) && !HuaweiHealth.b().equals(accountInfo) && accountInfo.length() > 4) {
                int length = accountInfo.length();
                int i = length / 3;
                String substring = accountInfo.substring(0, i);
                int i2 = length / 2;
                String substring2 = accountInfo.substring(i, i2);
                String substring3 = accountInfo.substring(i2, length);
                linkedHashMap.put("HF", substring);
                linkedHashMap.put("HS", substring2);
                linkedHashMap.put("HT", substring3);
            }
            if (Utils.o()) {
                return;
            }
            linkedHashMap.put(OpAnalyticsConstants.DEVICE_SERIAL_NUMBER, CommonUtil.i());
        }
    }
}
