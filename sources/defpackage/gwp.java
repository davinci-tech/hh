package defpackage;

import com.huawei.haf.application.BaseApplication;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import java.util.LinkedHashMap;

/* loaded from: classes4.dex */
public class gwp {
    public static void a(long j, int i, MotionPathSimplify motionPathSimplify, int i2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(8);
        linkedHashMap.put(OpAnalyticsConstants.ERROR_CODE, String.valueOf(101));
        linkedHashMap.put(OpAnalyticsConstants.RESULT_DESCRIBE, "trackSaveError");
        linkedHashMap.put("saveCostTime", String.valueOf(j));
        linkedHashMap.put("saveErrorCode", String.valueOf(i));
        linkedHashMap.put("saveType", String.valueOf(i2));
        e(linkedHashMap, motionPathSimplify);
        e(linkedHashMap);
    }

    public static void d(String str) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(3);
        linkedHashMap.put(OpAnalyticsConstants.ERROR_CODE, String.valueOf(102));
        linkedHashMap.put(OpAnalyticsConstants.RESULT_DESCRIBE, "trackSaveOverTime");
        linkedHashMap.put("saveTrackDuration", str);
        e(linkedHashMap);
    }

    public static void b(int i, int i2) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(3);
        linkedHashMap.put(OpAnalyticsConstants.ERROR_CODE, String.valueOf(103));
        linkedHashMap.put("processCode", String.valueOf(i));
        linkedHashMap.put("processSize", String.valueOf(i2));
        e(linkedHashMap);
    }

    public static void d(MotionPathSimplify motionPathSimplify, int i) {
        LinkedHashMap linkedHashMap = new LinkedHashMap(5);
        linkedHashMap.put(OpAnalyticsConstants.ERROR_CODE, String.valueOf(104));
        linkedHashMap.put("saveErrorCode", String.valueOf(i));
        e(linkedHashMap, motionPathSimplify);
        e(linkedHashMap);
    }

    private static void e(LinkedHashMap<String, String> linkedHashMap, MotionPathSimplify motionPathSimplify) {
        if (motionPathSimplify != null) {
            linkedHashMap.put("saveTrackDuration", String.valueOf(motionPathSimplify.requestTotalTime()));
            linkedHashMap.put("saveTrackType", String.valueOf(motionPathSimplify.requestSportType()));
            linkedHashMap.put("saveTrackUuid", String.valueOf(motionPathSimplify.requestDeviceUuid()));
        }
    }

    private static void e(LinkedHashMap<String, String> linkedHashMap) {
        ivz.d(BaseApplication.e()).e(OperationKey.HEALTH_APP_START_SPORT_2129015.value(), linkedHashMap, false);
    }

    public static void d(int i) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(1);
        linkedHashMap.put(OpAnalyticsConstants.ERROR_CODE, String.valueOf(i));
        OpAnalyticsUtil.getInstance().setKeyProcessEvent(OperationKey.PSI_GEOCODE_SEARCH_80070008.value(), linkedHashMap);
    }

    public static void a() {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(1);
        linkedHashMap.put("startLocation", String.valueOf(1006));
        OpAnalyticsUtil.getInstance().setKeyProcessEvent(OperationKey.PSI_GEOCODE_SEARCH_80070008.value(), linkedHashMap);
    }
}
