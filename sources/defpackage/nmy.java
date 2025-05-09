package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet;
import com.huawei.ui.commonui.linechart.barchart.HwHealthBarEntry;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import java.util.Calendar;
import java.util.List;

/* loaded from: classes6.dex */
public class nmy extends HwHealthBarDataSet {
    private DataInfos b;

    public nmy(List<HwHealthBarEntry> list, String str, String str2, String str3, DataInfos dataInfos) {
        super(list, str, str2, str3);
        if (dataInfos == null) {
            LogUtil.h("HwHealthUnixBarDataSet", "dataType == null");
        } else {
            this.b = dataInfos;
        }
    }

    @Override // com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet, com.huawei.ui.commonui.linechart.barchart.HwHealthBaseBarDataSet
    public int acquireValuePresentRangeMin(int i) {
        DataInfos dataInfos = this.b;
        if (dataInfos == null) {
            LogUtil.h("HwHealthUnixBarDataSet", "acquireValuePresentRangeMin mDataInfos == null");
            return 0;
        }
        if (dataInfos.isDayData()) {
            if (this.b.isActiveHoursData() || this.b == DataInfos.TemperatureDayDetail) {
                return n(i);
            }
            return j(i);
        }
        if (this.b.isWeekData()) {
            return i(i);
        }
        if (this.b.isMonthData()) {
            return i(i);
        }
        if (this.b.isYearData()) {
            return f(i);
        }
        if (this.b.isAllData()) {
            return g(i);
        }
        return 0;
    }

    public DataInfos a() {
        return this.b;
    }

    @Override // com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet, com.huawei.ui.commonui.linechart.barchart.HwHealthBaseBarDataSet
    public int acquireValuePresentRangeMax(int i) {
        DataInfos dataInfos = this.b;
        if (dataInfos == null) {
            LogUtil.h("HwHealthUnixBarDataSet", "acquireValuePresentRangeMax mDataInfos == null");
            return 0;
        }
        if (dataInfos.isDayData()) {
            if (this.b.isActiveHoursData() || this.b == DataInfos.TemperatureDayDetail) {
                return h(i);
            }
            return b(i);
        }
        if (this.b.isWeekData()) {
            return d(i);
        }
        if (this.b.isMonthData()) {
            return d(i);
        }
        if (this.b.isYearData()) {
            return a(i);
        }
        if (this.b.isAllData()) {
            return c(i);
        }
        return 0;
    }

    private int n(int i) {
        return (int) (jdl.h(i * 60000, 0) / 60000);
    }

    private int h(int i) {
        return (int) (jdl.h(i * 60000, 59) / 60000);
    }

    protected int j(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(i * 60000);
        if (calendar.get(12) < 30) {
            calendar.set(12, 0);
        } else {
            calendar.set(12, 29);
        }
        return (int) (calendar.getTimeInMillis() / 60000);
    }

    protected int b(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(i * 60000);
        if (calendar.get(12) < 30) {
            calendar.set(12, 30);
        } else {
            calendar.set(12, 59);
        }
        return (int) (calendar.getTimeInMillis() / 60000);
    }

    protected int i(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(i * 60000);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return (int) (calendar.getTimeInMillis() / 60000);
    }

    protected int d(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(i * 60000);
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.set(14, 999);
        return (int) (calendar.getTimeInMillis() / 60000);
    }

    protected int f(int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(i * 60000);
        calendar.set(5, 1);
        calendar.set(11, 0);
        calendar.set(12, 0);
        calendar.set(13, 0);
        calendar.set(14, 0);
        return (int) (calendar.getTimeInMillis() / 60000);
    }

    protected int a(int i) {
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

    protected int g(int i) {
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

    protected int c(int i) {
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

    @Override // com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet
    public boolean d(float f, HwHealthBarEntry hwHealthBarEntry) {
        if (hwHealthBarEntry != null) {
            return f >= ((float) nom.f(acquireValuePresentRangeMin(nom.h((int) hwHealthBarEntry.getX())))) && f <= ((float) nom.f(acquireValuePresentRangeMax(nom.h((int) hwHealthBarEntry.getX()))));
        }
        LogUtil.h("HwHealthUnixBarDataSet", "entry == null");
        return false;
    }

    @Override // com.huawei.ui.commonui.linechart.barchart.HwHealthBarDataSet, com.huawei.ui.commonui.linechart.barchart.HwHealthBaseBarDataSet
    public int acquireRangeCenterValue(int i) {
        return (int) ((acquireValuePresentRangeMax(i) + acquireValuePresentRangeMin(i)) / 2.0f);
    }
}
