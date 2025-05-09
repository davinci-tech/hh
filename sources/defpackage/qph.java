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
import java.util.List;

/* loaded from: classes6.dex */
public class qph extends pjp<LineChartViewInterface> implements LineChartViewPresenter {
    private LineChartViewInterface b;
    private c d;

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewPresenter
    public void notifyCursorDataAndTime(String str, List<HwHealthMarkerView.a> list) {
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewPresenter
    public void notifyData(int i, int i2) {
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewPresenter
    public void startCalendarWithDataType(Fragment fragment, HealthCalendar healthCalendar, String str, DataInfos dataInfos) {
    }

    static class c extends BaseHandler<qph> {
        private c(qph qphVar) {
            super(Looper.getMainLooper(), qphVar);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dGU_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(qph qphVar, Message message) {
            List list = message.obj instanceof List ? (List) message.obj : null;
            int i = message.what;
            if (i == 0) {
                qphVar.e(message.arg1, list);
            } else if (i == 1) {
                qphVar.a(message.arg1, list);
            } else {
                LogUtil.h("TemperatureBarChartViewPresenterImpl", "MyHandler other msg");
            }
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewPresenter
    public void initPageParams() {
        this.b = a();
        this.d = new c();
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewPresenter
    public void initLeftArrowClick() {
        LogUtil.c("TemperatureBarChartViewPresenterImpl", "Day initLeftArrowClick");
        LineChartViewInterface lineChartViewInterface = this.b;
        if (lineChartViewInterface == null || lineChartViewInterface.getBarChart() == null || this.b.getBarChart().isAnimating()) {
            return;
        }
        this.b.clickLeftArrow();
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewPresenter
    public void initRightArrowClick() {
        LineChartViewInterface lineChartViewInterface = this.b;
        if (lineChartViewInterface == null || lineChartViewInterface.getBarChart() == null || this.b.getBarChart().isAnimating()) {
            return;
        }
        this.b.clickRightArrow();
    }

    @Override // com.huawei.ui.main.stories.fitness.views.bloodsugar.LineChartViewPresenter
    public void notifyMaxAndMin(int i, List<HiHealthData> list) {
        Message obtain = Message.obtain();
        obtain.obj = list;
        obtain.what = 0;
        obtain.arg1 = i;
        this.d.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, List<HiHealthData> list) {
        LineChartViewInterface lineChartViewInterface = this.b;
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
        this.d.sendMessage(obtain);
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
    public void a(int i, List<HiHealthData> list) {
        LineChartViewInterface lineChartViewInterface = this.b;
        if (lineChartViewInterface == null) {
            return;
        }
        lineChartViewInterface.notifyRemindData(i, list);
    }
}
