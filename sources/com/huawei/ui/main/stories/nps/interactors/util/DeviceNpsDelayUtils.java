package com.huawei.ui.main.stories.nps.interactors.util;

import android.text.TextUtils;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.stories.nps.interactors.HwNpsManager;
import com.huawei.ui.main.stories.nps.interactors.db.QuestionSurveyTable;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/* loaded from: classes7.dex */
public class DeviceNpsDelayUtils {
    private static final String DEVICE_NPS_COMMIT_STATUS_KEY = "deviceNpsCommitStatus";
    private static final String DEVICE_NPS_DELAY_STATUS_KEY = "deviceNpsDelayStatus";
    private static final String MODULE_ID = Integer.toString(10004);
    private static final Set<String> SUPPORT_DELAY_DEVICE_CERT_MODEL = Collections.unmodifiableSet(new HashSet<String>() { // from class: com.huawei.ui.main.stories.nps.interactors.util.DeviceNpsDelayUtils.1
        private static final long serialVersionUID = -5057621303730981577L;

        {
            add("MDS-AL00");
            add("MDS-AL10");
            add("SGA-B19");
            add("FDS-B49");
            add("CLB-B19");
            add("FRG-B19");
            add("PNX-B19");
            add("PNX-B19B");
        }
    });
    private static final String TAG = "DeviceNpsDelayUtils";
    private static final String TAG_RELEASE = "Nps_DeviceNpsDelayUtils";

    public static boolean isDelayNps(String str) {
        boolean z = false;
        if (!isSupportDelayOnceNps(str)) {
            ReleaseLogUtil.d(TAG, "device not suppport delay");
            return false;
        }
        QuestionSurveyTable questionSurveyTable = HwNpsManager.getInstance().getQuestionSurveyTable();
        if (questionSurveyTable == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "isDelayNps questionSurveyTable is null");
            HwNpsManager.getInstance().setBiNpsEvent(AnalyticsValue.NPS_ELIGIBLE_TIME.value(), "5", HwNpsManager.NPS_TRIGGER_ERROR_QUESTION_TABLE);
            return false;
        }
        int times = questionSurveyTable.getTimes();
        if (times != 1) {
            ReleaseLogUtil.d(TAG, "requestTimes not suppport delay");
            return false;
        }
        String deviceId = questionSurveyTable.getDeviceId();
        boolean npsStatus = getNpsStatus(deviceId, DEVICE_NPS_COMMIT_STATUS_KEY, times);
        boolean npsStatus2 = getNpsStatus(deviceId, DEVICE_NPS_DELAY_STATUS_KEY, times);
        boolean isTimeDayThanNinety = HwNpsManager.getInstance().isTimeDayThanNinety(questionSurveyTable.getLastSurveyTime());
        if (!npsStatus && !npsStatus2 && !isTimeDayThanNinety) {
            z = true;
        }
        ReleaseLogUtil.e(TAG, "firstNpsCommitStatus is ", Boolean.valueOf(npsStatus), ", firstNpsDelayStatus is ", Boolean.valueOf(npsStatus2), ", isDelay is ", Boolean.valueOf(z));
        return z;
    }

    public static void updateNpsCommitStatus(boolean z) {
        QuestionSurveyTable questionSurveyTable = HwNpsManager.getInstance().getQuestionSurveyTable();
        if (questionSurveyTable == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "updateNpsCommitStatus questionSurveyTable is null");
            HwNpsManager.getInstance().setBiNpsEvent(AnalyticsValue.NPS_ELIGIBLE_TIME.value(), "5", HwNpsManager.NPS_TRIGGER_ERROR_QUESTION_TABLE);
        } else {
            updataNpsStatus(DEVICE_NPS_COMMIT_STATUS_KEY, questionSurveyTable.getDeviceId(), questionSurveyTable.getTimes(), z);
        }
    }

    public static void updateNpsDelayStatus(boolean z) {
        QuestionSurveyTable questionSurveyTable = HwNpsManager.getInstance().getQuestionSurveyTable();
        if (questionSurveyTable == null) {
            ReleaseLogUtil.d(TAG_RELEASE, "updateNpsDelayStatus questionSurveyTable is null");
            HwNpsManager.getInstance().setBiNpsEvent(AnalyticsValue.NPS_ELIGIBLE_TIME.value(), "5", HwNpsManager.NPS_TRIGGER_ERROR_QUESTION_TABLE);
        } else {
            updataNpsStatus(DEVICE_NPS_DELAY_STATUS_KEY, questionSurveyTable.getDeviceId(), questionSurveyTable.getTimes(), z);
        }
    }

    public static boolean getNpsStatus(String str, String str2, int i) {
        String str3 = str2 + "_" + str + "_" + i;
        boolean a2 = SharedPreferenceManager.a(MODULE_ID, str3, false);
        ReleaseLogUtil.e(TAG_RELEASE, "getNpsStatus key is ", str3, ", status is ", Boolean.valueOf(a2));
        return a2;
    }

    public static void updataNpsStatus(String str, String str2, int i, boolean z) {
        String str3 = str + "_" + str2 + "_" + i;
        ReleaseLogUtil.e(TAG_RELEASE, "updataNpsStatus key is ", str3, " status is ", Boolean.valueOf(z));
        SharedPreferenceManager.e(MODULE_ID, str3, z);
    }

    private static boolean isSupportDelayOnceNps(String str) {
        if (TextUtils.isEmpty(str)) {
            ReleaseLogUtil.d(TAG, "isDelayByCertModel CertModel empty");
            return false;
        }
        String upperCase = str.toUpperCase(Locale.ENGLISH);
        LogUtil.a(TAG, "isDelayByCertModel model is ", upperCase);
        if (SUPPORT_DELAY_DEVICE_CERT_MODEL.contains(upperCase)) {
            ReleaseLogUtil.e(TAG_RELEASE, "isDelayByCertModel true");
            return true;
        }
        ReleaseLogUtil.e(TAG_RELEASE, "isDelayByCertModel false");
        return false;
    }
}
