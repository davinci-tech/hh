package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import java.util.Calendar;

/* loaded from: classes6.dex */
public class pyc {
    public static int b(int i, DataInfos dataInfos) {
        if (dataInfos == null) {
            LogUtil.h("DataSetUtils", "acquireValuePresentRangeMin dataInfos = null");
            return 0;
        }
        if (dataInfos.isDayData()) {
            return c(i);
        }
        if (dataInfos.isWeekData()) {
            return h(i);
        }
        if (dataInfos.isMonthData()) {
            return h(i);
        }
        if (dataInfos.isYearData()) {
            return j(i);
        }
        if (dataInfos.isAllData()) {
            return g(i);
        }
        LogUtil.h("DataSetUtils", "mDataInfos is no match for min !");
        return 0;
    }

    public static int e(int i, DataInfos dataInfos) {
        if (dataInfos == null) {
            LogUtil.h("DataSetUtils", "acquireValuePresentRangeMax dataInfos = null");
            return 0;
        }
        if (dataInfos.isDayData()) {
            return a(i);
        }
        if (dataInfos.isWeekData()) {
            return e(i);
        }
        if (dataInfos.isMonthData()) {
            return e(i);
        }
        if (dataInfos.isYearData()) {
            return d(i);
        }
        if (dataInfos.isAllData()) {
            return b(i);
        }
        LogUtil.h("DataSetUtils", "mDataInfos is no match for max !");
        return 0;
    }

    private static int c(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(i * 60000);
        if (calendar.get(12) < 30) {
            calendar.set(12, 0);
        } else {
            calendar.set(12, 29);
        }
        return (int) (calendar.getTimeInMillis() / 60000);
    }

    private static int a(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(i * 60000);
        if (calendar.get(12) < 30) {
            calendar.set(12, 30);
        } else {
            calendar.set(12, 59);
        }
        return (int) (calendar.getTimeInMillis() / 60000);
    }

    private static int h(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(i * 60000);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return (int) (calendar.getTimeInMillis() / 60000);
    }

    private static int e(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(i * 60000);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.set(14, 999);
        return (int) (calendar.getTimeInMillis() / 60000);
    }

    private static int j(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(i * 60000);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return (int) (calendar.getTimeInMillis() / 60000);
    }

    private static int d(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(i * 60000);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        calendar.add(2, 1);
        calendar.add(14, -1);
        return (int) (calendar.getTimeInMillis() / 60000);
    }

    private static int g(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(i * 60000);
        calendar.set(2, 0);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return (int) (calendar.getTimeInMillis() / 60000);
    }

    private static int b(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(i * 60000);
        calendar.set(2, 0);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        calendar.add(1, 1);
        calendar.add(14, -1);
        return (int) (calendar.getTimeInMillis() / 60000);
    }
}
