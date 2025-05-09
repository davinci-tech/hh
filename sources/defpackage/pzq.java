package defpackage;

import androidx.fragment.app.Fragment;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.calendarview.HealthCalendarActivity;
import com.huawei.ui.commonui.linechart.view.HwHealthMarkerView;
import com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewInterface;
import com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewPresenter;
import java.util.Date;
import java.util.List;

/* loaded from: classes6.dex */
public class pzq extends pjp<LineChartViewInterface> implements LineChartViewPresenter {

    /* renamed from: a, reason: collision with root package name */
    private LineChartViewInterface f16359a;

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewPresenter
    public void notifyMaxAndMin(int i, List<HiHealthData> list) {
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewPresenter
    public void notifyRemindData(int i, List<HiHealthData> list) {
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewPresenter
    public void initPageParams() {
        this.f16359a = a();
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewPresenter
    public void notifyData(int i, int i2) {
        LineChartViewInterface lineChartViewInterface = this.f16359a;
        if (lineChartViewInterface == null || lineChartViewInterface.getLineChart() == null) {
            return;
        }
        long j = i * 60000;
        this.f16359a.setDayAndWeek(this.f16359a.getLineChart().formatRangeText(i, i2), dpg.m(j), jec.ab(new Date(j)));
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewPresenter
    public void initLeftArrowClick() {
        LogUtil.c("BloodSugarLineChartViewPresenterImpl", "Day initLeftArrowClick");
        LineChartViewInterface lineChartViewInterface = this.f16359a;
        if (lineChartViewInterface == null || lineChartViewInterface.getLineChart() == null || this.f16359a.getLineChart().isAnimating()) {
            return;
        }
        this.f16359a.clickLeftArrow();
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewPresenter
    public void initRightArrowClick() {
        LineChartViewInterface lineChartViewInterface = this.f16359a;
        if (lineChartViewInterface == null || lineChartViewInterface.getLineChart() == null || this.f16359a.getLineChart().isAnimating()) {
            return;
        }
        this.f16359a.clickRightArrow();
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewPresenter
    public void notifyCursorDataAndTime(String str, List<HwHealthMarkerView.a> list) {
        if (this.f16359a == null) {
            return;
        }
        if (koq.c(list)) {
            this.f16359a.notifyNumerical(str, list);
        } else {
            this.f16359a.notifyNumerical("--", null);
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewPresenter
    public void startCalendar(Fragment fragment, HealthCalendar healthCalendar) {
        LogUtil.a("BloodSugarLineChartViewPresenterImpl", "initCalendarArrow~~~");
        HealthCalendarActivity.e(fragment, healthCalendar);
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0045  */
    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewPresenter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void startCalendarWithDataType(androidx.fragment.app.Fragment r3, com.huawei.ui.commonui.calendarview.HealthCalendar r4, java.lang.String r5, com.huawei.ui.commonui.linechart.common.DataInfos r6) {
        /*
            r2 = this;
            int r0 = r5.hashCode()
            r1 = 1164722869(0x456c42b5, float:3780.1692)
            if (r0 == r1) goto L16
            r1 = 1214310707(0x4860e933, float:230308.8)
            if (r0 == r1) goto Lf
            goto L26
        Lf:
            java.lang.String r0 = "BLOOD_SUGAR_FINGER_TIP"
            boolean r5 = r5.equals(r0)
            goto L26
        L16:
            java.lang.String r0 = "BLOOD_SUGAR_CONTINUE"
            boolean r5 = r5.equals(r0)
            if (r5 == 0) goto L26
            r5 = 47503(0xb98f, float:6.6566E-41)
            int[] r5 = new int[]{r5}
            goto L2d
        L26:
            r5 = 9
            int[] r5 = new int[r5]
            r5 = {x0050: FILL_ARRAY_DATA , data: [2008, 2009, 2010, 2011, 2012, 2013, 2015, 2014, 2106} // fill-array
        L2d:
            android.os.Bundle r0 = new android.os.Bundle
            r0.<init>()
            java.lang.String r1 = "calendar"
            r0.putSerializable(r1, r4)
            com.huawei.ui.commonui.calendarview.HealthDataMarkDateTrigger r4 = new com.huawei.ui.commonui.calendarview.HealthDataMarkDateTrigger
            r4.<init>(r5)
            java.lang.String r5 = "markDateTrigger"
            r0.putParcelable(r5, r4)
            com.huawei.ui.commonui.linechart.common.DataInfos r4 = com.huawei.ui.commonui.linechart.common.DataInfos.BloodSugarDayDetail
            if (r6 != r4) goto L4b
            java.lang.String r4 = "isSetGrayUnmarkedDate"
            r5 = 1
            r0.putBoolean(r4, r5)
        L4b:
            com.huawei.ui.commonui.calendarview.HealthCalendarActivity.cxl_(r3, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.pzq.startCalendarWithDataType(androidx.fragment.app.Fragment, com.huawei.ui.commonui.calendarview.HealthCalendar, java.lang.String, com.huawei.ui.commonui.linechart.common.DataInfos):void");
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewPresenter
    public long prossCalendarSelect(HealthCalendar healthCalendar) {
        return healthCalendar.transformCalendar().getTimeInMillis();
    }
}
