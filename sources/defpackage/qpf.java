package defpackage;

import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import androidx.fragment.app.Fragment;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.calendarview.HealthCalendar;
import com.huawei.ui.commonui.calendarview.HealthCalendarActivity;
import com.huawei.ui.commonui.calendarview.HealthDataMarkDateTrigger;
import com.huawei.ui.commonui.linechart.common.DataInfos;
import com.huawei.ui.commonui.linechart.view.HwHealthMarkerView;
import com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewInterface;
import com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewPresenter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/* loaded from: classes6.dex */
public class qpf extends pjp<LineChartViewInterface> implements LineChartViewPresenter {
    private a c;
    private LineChartViewInterface e;

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewPresenter
    public void startCalendarWithDataType(Fragment fragment, HealthCalendar healthCalendar, String str, DataInfos dataInfos) {
    }

    static class a extends BaseHandler<qpf> {
        private a(qpf qpfVar) {
            super(Looper.getMainLooper(), qpfVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dGV_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(qpf qpfVar, Message message) {
            List list = message.obj instanceof List ? (List) message.obj : null;
            int i = message.what;
            if (i == 0) {
                qpfVar.b(message.arg1, list);
            } else if (i == 1) {
                qpfVar.e(message.arg1, list);
            } else {
                LogUtil.h("TemperatureLineChartViewPresenterImpl", "MyHandler other msg");
            }
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewPresenter
    public void initPageParams() {
        this.e = a();
        this.c = new a();
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewPresenter
    public void notifyData(int i, int i2) {
        LineChartViewInterface lineChartViewInterface = this.e;
        if (lineChartViewInterface == null || lineChartViewInterface.getLineChart() == null) {
            return;
        }
        long j = i * 60000;
        this.e.setDayAndWeek(this.e.getLineChart().formatRangeText(i, i2), dpg.m(j), jec.ab(new Date(j)));
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewPresenter
    public void initLeftArrowClick() {
        LogUtil.c("TemperatureLineChartViewPresenterImpl", "Day initLeftArrowClick");
        LineChartViewInterface lineChartViewInterface = this.e;
        if (lineChartViewInterface == null || lineChartViewInterface.getLineChart() == null || this.e.getLineChart().isAnimating()) {
            return;
        }
        this.e.clickLeftArrow();
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewPresenter
    public void initRightArrowClick() {
        LineChartViewInterface lineChartViewInterface = this.e;
        if (lineChartViewInterface == null || lineChartViewInterface.getLineChart() == null || this.e.getLineChart().isAnimating()) {
            return;
        }
        this.e.clickRightArrow();
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewPresenter
    public void notifyCursorDataAndTime(String str, List<HwHealthMarkerView.a> list) {
        if (this.e == null) {
            return;
        }
        if (koq.c(list)) {
            this.e.notifyNumerical(str, list);
        } else {
            this.e.notifyNumerical("--", null);
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewPresenter
    public void notifyMaxAndMin(int i, List<HiHealthData> list) {
        ArrayList arrayList = new ArrayList(16);
        qpr.d(list, arrayList);
        Message obtain = Message.obtain();
        obtain.obj = arrayList;
        obtain.what = 0;
        obtain.arg1 = i;
        this.c.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i, List<HiHealthData> list) {
        LineChartViewInterface lineChartViewInterface = this.e;
        if (lineChartViewInterface == null) {
            return;
        }
        lineChartViewInterface.notifyMaxAndMin(i, list);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewPresenter
    public void notifyRemindData(int i, List<HiHealthData> list) {
        Message obtain = Message.obtain();
        obtain.obj = list;
        obtain.what = 1;
        obtain.arg1 = i;
        this.c.sendMessage(obtain);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewPresenter
    public void startCalendar(Fragment fragment, HealthCalendar healthCalendar) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("calendar", healthCalendar);
        bundle.putParcelable("markDateTrigger", new HealthDataMarkDateTrigger(new int[]{2104, HiHealthDataType.b, DicDataTypeUtil.DataType.SKIN_TEMPERATURE.value()}, new int[]{qpk.d().j(), qpk.d().h()}));
        HealthCalendarActivity.cxl_(fragment, bundle);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewPresenter
    public long prossCalendarSelect(HealthCalendar healthCalendar) {
        return healthCalendar.transformCalendar().getTimeInMillis();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, List<HiHealthData> list) {
        LineChartViewInterface lineChartViewInterface = this.e;
        if (lineChartViewInterface == null) {
            return;
        }
        lineChartViewInterface.notifyRemindData(i, list);
    }
}
