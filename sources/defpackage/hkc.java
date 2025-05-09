package defpackage;

import android.content.Context;
import com.huawei.healthcloud.plugintrack.ui.sporttypeconfig.bean.HwSportTypeInfo;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* loaded from: classes4.dex */
public class hkc {
    public static boolean e(int i) {
        return i == 258 || i == 259 || i == 257 || i == 260 || i == 512 || i == 262 || i == 217;
    }

    public static String[] a(Context context, int i) {
        hln c = hln.c(context);
        LogUtil.a("Track_TrackHistoryUtil", "SportType= ", Integer.valueOf(i));
        if (i == 0) {
            return new String[]{"stat1", "stat2", "stat3", "stat4", "stat5", "stat6"};
        }
        HwSportTypeInfo d = c.d(i);
        if (d == null) {
            return new String[0];
        }
        ArrayList<String> trackRequestDataBase = d.getHistoryList().getTrackRequestDataBase(i);
        return (trackRequestDataBase == null || trackRequestDataBase.isEmpty()) ? new String[0] : (String[]) trackRequestDataBase.toArray(new String[trackRequestDataBase.size()]);
    }

    public static int[] c(Context context, int i) {
        hln c = hln.c(context);
        LogUtil.a("Track_TrackHistoryUtil", "SportType= ", Integer.valueOf(i));
        if (i == 0) {
            return new int[]{1, 2, 3, 4, 5, 6};
        }
        HwSportTypeInfo d = c.d(i);
        if (d == null || d.getHistoryList() == null) {
            LogUtil.b("Track_TrackHistoryUtil", "hwSportTypeInfo is null");
            return new int[0];
        }
        List<Integer> allRequestId = d.getHistoryList().getAllRequestId();
        if (allRequestId == null || allRequestId.isEmpty()) {
            return new int[0];
        }
        int[] iArr = new int[allRequestId.size()];
        for (int i2 = 0; i2 < allRequestId.size(); i2++) {
            iArr[i2] = allRequestId.get(i2).intValue();
        }
        return iArr;
    }

    public static boolean d(int i) {
        return Arrays.asList(281, 257, 264, Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_CROSS_COUNTRY_RACE), 258, 259, 262, Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_OPEN_AREA_SWIM), 260, 282, Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_CROSS_TRAINER), Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_ROW_MACHINE), Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_TREAD_MACHINE), Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_INDOOR_BIKE), 217, 218, 219).contains(Integer.valueOf(i));
    }

    public static String d(Context context, int i) {
        if (i == 1) {
            return context.getString(R$string.IDS_grade_evaluation_Good);
        }
        if (i == 2) {
            return context.getString(R$string.IDS_grade_evaluation_Great);
        }
        if (i == 3) {
            return context.getString(R$string.IDS_grade_evaluation_Perfect);
        }
        if (i != 4) {
            return i != 5 ? "--" : context.getString(R$string.IDS_grade_evaluation_Amazing);
        }
        return context.getString(R$string.IDS_grade_evaluation_Crazy);
    }

    public static boolean b(int i) {
        return Arrays.asList(257, 258, 259, 262, Integer.valueOf(OldToNewMotionPath.SPORT_TYPE_BASKETBALL), 512, Integer.valueOf(HeartRateThresholdConfig.HEART_RATE_LIMIT), 217).contains(Integer.valueOf(i));
    }
}
