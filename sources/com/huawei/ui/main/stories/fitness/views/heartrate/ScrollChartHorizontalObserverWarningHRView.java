package com.huawei.ui.main.stories.fitness.views.heartrate;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.view.View;
import com.huawei.hwlogsmodel.LogUtil;
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
import com.huawei.ui.main.stories.fitness.views.heartrate.ScrollChartHorizontalObserverWarningHRView;
import defpackage.koq;
import defpackage.nnl;
import defpackage.nom;
import defpackage.psk;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class ScrollChartHorizontalObserverWarningHRView extends ScrollChartHorizontalObserverHRView implements IFocusObserverItem {

    /* renamed from: a, reason: collision with root package name */
    private Handler f9991a;
    private OnFocusAreaChangeListener c;
    private IScrollChartVisitor d;

    public interface OnFocusAreaChangeListener {
        void onFocusAreaChange(List<nnl> list);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.icommon.IFocusObserverItem
    public View onCreateDetailView() {
        return null;
    }

    public ScrollChartHorizontalObserverWarningHRView(Context context, ObserveredClassifiedView observeredClassifiedView, String str) {
        super(context, observeredClassifiedView, str);
        this.d = null;
        this.c = null;
        this.f9991a = new Handler(Looper.getMainLooper()) { // from class: com.huawei.ui.main.stories.fitness.views.heartrate.ScrollChartHorizontalObserverWarningHRView.5
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                if (message == null) {
                    return;
                }
                if (message.what == 1 && (message.obj instanceof e)) {
                    ScrollChartHorizontalObserverWarningHRView.this.c(((e) message.obj).b());
                }
                super.handleMessage(message);
            }
        };
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.IScrollChartOuterObserver
    public void onRangeShow(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, int i, int i2) {
        if (this.f9991a.hasMessages(1)) {
            this.f9991a.removeMessages(1);
        }
        Message obtainMessage = this.f9991a.obtainMessage(1);
        obtainMessage.obj = new e(hwHealthBaseScrollBarLineChart);
        this.f9991a.sendMessageDelayed(obtainMessage, 300L);
    }

    static class e {
        private HwHealthBaseScrollBarLineChart d;

        e(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart) {
            this.d = hwHealthBaseScrollBarLineChart;
        }

        public HwHealthBaseScrollBarLineChart b() {
            return this.d;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart) {
        IScrollChartVisitor iScrollChartVisitor = this.d;
        if (iScrollChartVisitor == null) {
            return;
        }
        iScrollChartVisitor.visitShowModels(hwHealthBaseScrollBarLineChart, HwHealthChartHolder.HIGH_WARN, this.mHost.getStepDataType(), new LogicalUnit() { // from class: qaq
            @Override // com.huawei.ui.commonui.linechart.icommon.LogicalUnit
            public final float calculate(List list) {
                return ScrollChartHorizontalObserverWarningHRView.this.c(list);
            }
        });
    }

    public /* synthetic */ float c(List list) {
        if (koq.b(list)) {
            return 0.0f;
        }
        e(list);
        return 0.0f;
    }

    private void e(List<? extends HwHealthBaseEntry> list) {
        if (this.c == null) {
            return;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (Parcelable parcelable : list) {
            if (parcelable instanceof IStorageModelProvider) {
                IStorageModel acquireModel = ((IStorageModelProvider) parcelable).acquireModel();
                if (!(acquireModel instanceof StorageGenericModel)) {
                    LogUtil.h("ScrollChartHorizontalObserverWarningHRView", "storageModel not instanceof StorageGenericModel,logic error");
                } else {
                    List<Object> e2 = ((StorageGenericModel) acquireModel).e("HR_WARNING_DETAIL");
                    if (!koq.b(e2) && koq.e((Object) e2, psk.class)) {
                        if (e2.size() != 1) {
                            LogUtil.h("ScrollChartHorizontalObserverWarningHRView", "details on one pint size not zero,warning!!!");
                        } else {
                            psk pskVar = (psk) e2.get(0);
                            nnl nnlVar = new nnl();
                            nnlVar.a(nom.f((int) TimeUnit.MILLISECONDS.toMinutes(pskVar.getStartX())));
                            nnlVar.e(nom.f((int) TimeUnit.MILLISECONDS.toMinutes(pskVar.getEndX())));
                            arrayList.add(nnlVar);
                        }
                    }
                }
            }
        }
        this.c.onFocusAreaChange(arrayList);
    }

    public void setOnFocusAreaChangeListener(OnFocusAreaChangeListener onFocusAreaChangeListener) {
        this.c = onFocusAreaChangeListener;
    }

    public void setScrollChartVisitor(IScrollChartVisitor iScrollChartVisitor) {
        this.d = iScrollChartVisitor;
    }
}
