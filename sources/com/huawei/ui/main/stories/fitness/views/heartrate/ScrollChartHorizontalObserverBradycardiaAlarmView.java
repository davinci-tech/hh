package com.huawei.ui.main.stories.fitness.views.heartrate;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import com.huawei.ui.commonui.linechart.HwHealthChartHolder;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.icommon.IStorageModelProvider;
import com.huawei.ui.commonui.linechart.icommon.LogicalUnit;
import com.huawei.ui.commonui.linechart.model.StorageGenericModel;
import com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView;
import com.huawei.ui.main.stories.fitness.views.base.chart.icommon.IFocusObserverItem;
import com.huawei.ui.main.stories.fitness.views.base.chart.icommon.IScrollChartVisitor;
import defpackage.koq;
import defpackage.nnl;
import defpackage.nom;
import defpackage.psk;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class ScrollChartHorizontalObserverBradycardiaAlarmView extends ScrollChartHorizontalObserverHRView implements IFocusObserverItem {

    /* renamed from: a, reason: collision with root package name */
    private IScrollChartVisitor f9989a;
    private OnFocusAreaChangeListener b;
    private Handler d;

    public interface OnFocusAreaChangeListener {
        void onFocusAreaChange(List<nnl> list);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.icommon.IFocusObserverItem
    public View onCreateDetailView() {
        return null;
    }

    public ScrollChartHorizontalObserverBradycardiaAlarmView(Context context, ObserveredClassifiedView observeredClassifiedView, String str) {
        super(context, observeredClassifiedView, str);
        this.f9989a = null;
        this.b = null;
        this.d = new Handler(Looper.getMainLooper()) { // from class: com.huawei.ui.main.stories.fitness.views.heartrate.ScrollChartHorizontalObserverBradycardiaAlarmView.5
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message == null) {
                    return;
                }
                if (message.what == 1) {
                    ScrollChartHorizontalObserverBradycardiaAlarmView.this.a((HwHealthBaseScrollBarLineChart) message.obj);
                }
                super.handleMessage(message);
            }
        };
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.IScrollChartOuterObserver
    public void onRangeShow(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, int i, int i2) {
        if (this.d.hasMessages(1)) {
            this.d.removeMessages(1);
        }
        Message obtainMessage = this.d.obtainMessage(1);
        obtainMessage.obj = hwHealthBaseScrollBarLineChart;
        this.d.sendMessageDelayed(obtainMessage, 300L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart) {
        IScrollChartVisitor iScrollChartVisitor = this.f9989a;
        if (iScrollChartVisitor == null) {
            return;
        }
        iScrollChartVisitor.visitShowModels(hwHealthBaseScrollBarLineChart, HwHealthChartHolder.BRADYCARDIA_WARN, this.mHost.getStepDataType(), new LogicalUnit() { // from class: com.huawei.ui.main.stories.fitness.views.heartrate.ScrollChartHorizontalObserverBradycardiaAlarmView.3
            @Override // com.huawei.ui.commonui.linechart.icommon.LogicalUnit
            public float calculate(List<? extends HwHealthBaseEntry> list) {
                if (koq.b(list)) {
                    return 0.0f;
                }
                ScrollChartHorizontalObserverBradycardiaAlarmView.this.c(list);
                return 0.0f;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<? extends HwHealthBaseEntry> list) {
        if (this.b == null) {
            return;
        }
        ArrayList arrayList = new ArrayList(list.size());
        Iterator<? extends HwHealthBaseEntry> it = list.iterator();
        while (it.hasNext()) {
            IStorageModel acquireModel = ((IStorageModelProvider) ((HwHealthBaseEntry) it.next())).acquireModel();
            if (!(acquireModel instanceof StorageGenericModel)) {
                LogUtil.c("ScrollChartHorizontalObserverBradycardiaAlarmView", "storageModel not instance of StorageGenericModel,logic error");
            } else {
                List<Object> e = ((StorageGenericModel) acquireModel).e("BRADYCARDIA_DETAIL");
                if (!koq.b(e) && koq.e((Object) e, psk.class)) {
                    if (e.size() != 1) {
                        LogUtil.c("ScrollChartHorizontalObserverBradycardiaAlarmView", "details on one pint size not zero,warning!!!");
                    } else {
                        psk pskVar = (psk) e.get(0);
                        nnl nnlVar = new nnl();
                        nnlVar.a(nom.f((int) TimeUnit.MILLISECONDS.toMinutes(pskVar.getStartX())));
                        nnlVar.e(nom.f((int) TimeUnit.MILLISECONDS.toMinutes(pskVar.getEndX())));
                        arrayList.add(nnlVar);
                    }
                }
            }
        }
        this.b.onFocusAreaChange(arrayList);
    }

    public void setScrollChartVisitor(IScrollChartVisitor iScrollChartVisitor) {
        this.f9989a = iScrollChartVisitor;
    }

    public void setOnFocusAreaChangeListener(OnFocusAreaChangeListener onFocusAreaChangeListener) {
        this.b = onFocusAreaChangeListener;
    }
}
