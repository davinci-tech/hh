package com.huawei.ui.main.stories.fitness.views.heartrate.card;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.huawei.health.R;
import com.huawei.health.knit.api.ICustomCalculator;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseScrollBarLineChart;
import com.huawei.ui.commonui.linechart.icommon.IStorageModel;
import com.huawei.ui.commonui.linechart.icommon.IStorageModelProvider;
import com.huawei.ui.commonui.linechart.icommon.LogicalUnit;
import com.huawei.ui.commonui.linechart.model.StorageGenericModel;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.heartrate.BradycardiaAlarmActivity;
import com.huawei.ui.main.stories.fitness.activity.heartrate.WarningHRActivity;
import com.huawei.ui.main.stories.fitness.views.base.chart.icommon.IFocusObserverItem;
import com.huawei.ui.main.stories.fitness.views.base.chart.icommon.IScrollChartVisitor;
import com.huawei.ui.main.stories.fitness.views.heartrate.business.WarningHRDetailView;
import com.huawei.ui.main.stories.fitness.views.heartrate.card.ReminderCardView;
import defpackage.koq;
import defpackage.nnl;
import defpackage.nom;
import defpackage.nsf;
import defpackage.psk;
import defpackage.pxz;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes6.dex */
public class ReminderCardView extends HeartRateCardView implements IFocusObserverItem {

    /* renamed from: a, reason: collision with root package name */
    private volatile boolean f9996a;
    private DetailViewWithData b;
    private Handler c;
    private DetailViewWithoutData d;
    private DetailViewContainer e;
    private OnFocusAreaChangeListener f;
    private String g;
    private boolean h;
    private ICustomCalculator i;
    private ICustomCalculator j;
    private IScrollChartVisitor l;
    private String m;
    private WeakReference<ReminderCardView> o;

    public interface OnFocusAreaChangeListener {
        void onFocusAreaChange(List<nnl> list);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public ReminderCardView(android.content.Context r3, com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView r4, boolean r5) {
        /*
            r2 = this;
            if (r5 == 0) goto L9
            int r0 = com.huawei.ui.main.R$string.IDS_low_heart_rate_reminder
            java.lang.String r0 = defpackage.nsf.h(r0)
            goto Lf
        L9:
            int r0 = com.huawei.ui.main.R$string.IDS_high_heart_rate_reminder
            java.lang.String r0 = defpackage.nsf.h(r0)
        Lf:
            int r1 = com.huawei.ui.main.R$string.IDS_main_watch_heart_rate_unit_string
            java.lang.String r1 = r3.getString(r1)
            r2.<init>(r3, r4, r0, r1)
            r3 = 0
            r2.h = r3
            r4 = 0
            r2.j = r4
            r2.i = r4
            r2.l = r4
            r2.f = r4
            r2.f9996a = r3
            com.huawei.ui.main.stories.fitness.views.heartrate.card.ReminderCardView$1 r3 = new com.huawei.ui.main.stories.fitness.views.heartrate.card.ReminderCardView$1
            android.os.Looper r4 = android.os.Looper.getMainLooper()
            r3.<init>(r4)
            r2.c = r3
            r2.h = r5
            if (r5 == 0) goto L3e
            java.lang.String r3 = "BRADYCARDIA_DETAIL"
            r2.g = r3
            java.lang.String r3 = "BRADYCARDIA"
            r2.m = r3
            goto L46
        L3e:
            java.lang.String r3 = "HR_WARNING_DETAIL"
            r2.g = r3
            java.lang.String r3 = "HIGH_WARN"
            r2.m = r3
        L46:
            com.huawei.ui.main.stories.fitness.views.heartrate.card.ReminderCardView$DetailViewContainer r3 = new com.huawei.ui.main.stories.fitness.views.heartrate.card.ReminderCardView$DetailViewContainer
            android.content.Context r4 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            r3.<init>(r4)
            r2.e = r3
            com.huawei.ui.main.stories.fitness.views.heartrate.card.ReminderCardView$DetailViewWithData r3 = new com.huawei.ui.main.stories.fitness.views.heartrate.card.ReminderCardView$DetailViewWithData
            android.content.Context r4 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            boolean r5 = r2.h
            r3.<init>(r4, r5)
            r2.b = r3
            com.huawei.ui.main.stories.fitness.views.heartrate.card.ReminderCardView$DetailViewWithoutData r3 = new com.huawei.ui.main.stories.fitness.views.heartrate.card.ReminderCardView$DetailViewWithoutData
            android.content.Context r4 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            boolean r5 = r2.h
            r3.<init>(r4, r5)
            r2.d = r3
            java.lang.ref.WeakReference r3 = new java.lang.ref.WeakReference
            r3.<init>(r2)
            r2.o = r3
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.fitness.views.heartrate.card.ReminderCardView.<init>(android.content.Context, com.huawei.ui.main.stories.fitness.views.base.chart.ObserveredClassifiedView, boolean):void");
    }

    public boolean b() {
        return this.h;
    }

    public void setHashHistoryData(boolean z) {
        this.f9996a = z;
        this.d.c(this.f9996a);
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.ScrollChartObserverView
    public void onRangeShow(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart, int i, int i2) {
        c(hwHealthBaseScrollBarLineChart);
        if (this.c.hasMessages(1)) {
            this.c.removeMessages(1);
        }
        Message obtainMessage = this.c.obtainMessage(1);
        obtainMessage.obj = hwHealthBaseScrollBarLineChart;
        this.c.sendMessageDelayed(obtainMessage, 300L);
    }

    private void c(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart) {
        ICustomCalculator iCustomCalculator = this.j;
        if (iCustomCalculator == null || this.i == null) {
            setContentText("--");
            return;
        }
        float calculate = iCustomCalculator.calculate(hwHealthBaseScrollBarLineChart, this.mHost.getStepDataType());
        float calculate2 = this.i.calculate(hwHealthBaseScrollBarLineChart, this.mHost.getStepDataType());
        if (calculate >= calculate2 && calculate2 > 0.0f) {
            setContentText(UnitUtil.e(calculate2, 1, 0) + Constants.LINK + UnitUtil.e(calculate, 1, 0));
            return;
        }
        setContentText("--");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(HwHealthBaseScrollBarLineChart hwHealthBaseScrollBarLineChart) {
        IScrollChartVisitor iScrollChartVisitor = this.l;
        if (iScrollChartVisitor == null) {
            return;
        }
        iScrollChartVisitor.visitShowModels(hwHealthBaseScrollBarLineChart, this.m, this.mHost.getStepDataType(), new LogicalUnit() { // from class: qav
            @Override // com.huawei.ui.commonui.linechart.icommon.LogicalUnit
            public final float calculate(List list) {
                return ReminderCardView.this.c(list);
            }
        });
    }

    public /* synthetic */ float c(List list) {
        if (this.o.get() == null) {
            return 0.0f;
        }
        if (koq.b(list)) {
            this.b.a((List<WarningHRDetailView.d>) null);
            this.e.dxK_(this.d);
            this.d.c(this.f9996a);
            return 0.0f;
        }
        if (!(((HwHealthBaseEntry) list.get(0)) instanceof IStorageModelProvider)) {
            return 0.0f;
        }
        a(list);
        this.e.dxK_(this.b);
        b((List<? extends HwHealthBaseEntry>) list);
        return 0.0f;
    }

    private void a(List<? extends HwHealthBaseEntry> list) {
        ArrayList arrayList = new ArrayList(16);
        Iterator<? extends HwHealthBaseEntry> it = list.iterator();
        while (it.hasNext()) {
            IStorageModel acquireModel = ((IStorageModelProvider) ((HwHealthBaseEntry) it.next())).acquireModel();
            if (!(acquireModel instanceof StorageGenericModel)) {
                throw new RuntimeException("storageModel not instance of StorageGenericModel,logic error");
            }
            List<Object> e = ((StorageGenericModel) acquireModel).e(this.g);
            if (e == null) {
                LogUtil.a("HealthHeartRate_ReminderCardView", "loadExtensionView objects is null.");
            } else {
                for (Object obj : e) {
                    if (obj instanceof psk) {
                        psk pskVar = (psk) obj;
                        WarningHRDetailView.d dVar = new WarningHRDetailView.d(getContext(), pskVar.getStartX(), pskVar.getEndX());
                        dVar.a(pskVar.c());
                        dVar.c(pskVar.e());
                        if (pskVar.b() != null && pskVar.b().size() > 0) {
                            dVar.d(pskVar.b());
                        }
                        arrayList.add(dVar);
                    }
                }
            }
        }
        Collections.sort(arrayList, new Comparator() { // from class: qat
            @Override // java.util.Comparator
            public final int compare(Object obj2, Object obj3) {
                return ReminderCardView.c((WarningHRDetailView.d) obj2, (WarningHRDetailView.d) obj3);
            }
        });
        this.b.a(arrayList);
    }

    public static /* synthetic */ int c(WarningHRDetailView.d dVar, WarningHRDetailView.d dVar2) {
        return dVar.queryModelStartTime() <= dVar2.queryModelStartTime() ? 1 : -1;
    }

    private void b(List<? extends HwHealthBaseEntry> list) {
        if (this.f != null) {
            ArrayList arrayList = new ArrayList(16);
            Iterator<? extends HwHealthBaseEntry> it = list.iterator();
            while (it.hasNext()) {
                IStorageModel acquireModel = ((IStorageModelProvider) ((HwHealthBaseEntry) it.next())).acquireModel();
                if (!(acquireModel instanceof StorageGenericModel)) {
                    throw new RuntimeException("storageModel not instance of StorageGenericModel,logic error");
                }
                List<Object> e = ((StorageGenericModel) acquireModel).e(this.g);
                if (!koq.b(e) && koq.e((Object) e, psk.class)) {
                    if (e.size() != 1) {
                        LogUtil.b("HealthHeartRate_ReminderCardView", "details on one pint size not zero,warning!!!");
                        return;
                    }
                    psk pskVar = (psk) e.get(0);
                    nnl nnlVar = new nnl();
                    nnlVar.a(nom.f((int) TimeUnit.MILLISECONDS.toMinutes(pskVar.getStartX())));
                    nnlVar.e(nom.f((int) TimeUnit.MILLISECONDS.toMinutes(pskVar.getEndX())));
                    arrayList.add(nnlVar);
                }
            }
            this.f.onFocusAreaChange(arrayList);
        }
    }

    public void b(ICustomCalculator iCustomCalculator) {
        this.j = iCustomCalculator;
    }

    public void d(ICustomCalculator iCustomCalculator) {
        this.i = iCustomCalculator;
    }

    public void d(IScrollChartVisitor iScrollChartVisitor) {
        this.l = iScrollChartVisitor;
    }

    public void setOnFocusAreaChangeListener(OnFocusAreaChangeListener onFocusAreaChangeListener) {
        this.f = onFocusAreaChangeListener;
    }

    @Override // com.huawei.ui.main.stories.fitness.views.base.chart.icommon.IFocusObserverItem
    public View onCreateDetailView() {
        return this.e;
    }

    static class DetailViewContainer extends LinearLayout {
        DetailViewContainer(Context context) {
            super(context);
        }

        public void dxK_(View view) {
            removeAllViews();
            addView(view, -1, -1);
        }
    }

    public static class DetailViewWithoutData extends LinearLayout {

        /* renamed from: a, reason: collision with root package name */
        private int f9998a;
        private HealthTextView b;
        private int c;
        private ConstraintLayout d;
        private String e;
        private int f;
        private HealthTextView g;
        private boolean h;
        private HealthButton i;
        private boolean j;
        private HealthTextView k;
        private int l;
        private int m;

        DetailViewWithoutData(Context context, boolean z) {
            super(context);
            this.i = null;
            this.j = false;
            this.h = z;
            if (z) {
                this.c = R$string.IDS_resting_heart_rate_low_string;
                this.f = 0;
                this.l = R$string.IDS_resting_heart_rate_low_redirect_string;
                this.f9998a = R$string.IDS_heart_rate_low_ecg_tips;
                this.m = R$string.IDS_heart_rate_low_tips;
            } else {
                this.c = R$string.IDS_resting_heart_rate_high_string;
                this.f = 1;
                this.l = R$string.IDS_resting_heart_rate_high_redirect_string;
                this.f9998a = R$string.IDS_heart_rate_high_ecg_tips;
                this.m = R$string.IDS_heart_rate_high_tips;
            }
            a();
        }

        private void a() {
            inflate(getContext(), R.layout.focus_view_detail_warning_hr_without_data, this);
            HealthButton healthButton = (HealthButton) findViewById(R.id.view_more_data);
            this.i = healthButton;
            healthButton.setOnClickListener(new View.OnClickListener() { // from class: qau
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ReminderCardView.DetailViewWithoutData.this.dxM_(view);
                }
            });
            this.e = getResources().getString(this.c, 50, 100, 10);
            this.g = (HealthTextView) findViewById(R.id.text_rest_heart_rate_tips);
            this.b = (HealthTextView) findViewById(R.id.text_ecg_measurement_reminder);
            this.d = (ConstraintLayout) findViewById(R.id.ecg_measurement_reminder_layout);
            this.k = (HealthTextView) findViewById(R.id.point1);
            this.b.setVisibility(8);
            this.d.setVisibility(8);
            this.k.setVisibility(8);
            if (pxz.c() || !c()) {
                d();
            } else {
                e();
            }
        }

        public /* synthetic */ void dxM_(View view) {
            if (this.h) {
                BradycardiaAlarmActivity.b(getContext());
            } else {
                WarningHRActivity.e(getContext());
            }
            ViewClickInstrumentation.clickOnView(view);
        }

        private boolean c() {
            return this.h ? pxz.e() : pxz.b();
        }

        private void h() {
            this.b.setVisibility(0);
            this.d.setVisibility(0);
            this.k.setVisibility(0);
        }

        private void b() {
            this.b.setVisibility(8);
            this.d.setVisibility(8);
            this.k.setVisibility(8);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d() {
            if (this.g != null) {
                LogUtil.a("HealthHeartRate_ReminderCardView", "device disconnected or device not support heartRate unusuallyAlarm");
                this.g.setText(this.e);
            } else {
                LogUtil.h("HealthHeartRate_ReminderCardView", "mHeartRateTipTextView is null");
            }
            b();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e() {
            String string;
            LogUtil.a("HealthHeartRate_ReminderCardView", "DetailViewWithoutData device connect,prepare the redirection link");
            String string2 = getResources().getString(R$string.IDS_resting_heart_rate_redirect_setting_string);
            String string3 = getResources().getString(R$string.IDS_heart_rate_ecg_view);
            if (pxz.d()) {
                if (this.j) {
                    h();
                }
                string = getResources().getString(this.m, UnitUtil.e(50.0d, 1, 0), getResources().getQuantityString(R.plurals._2130903088_res_0x7f030030, 100, 100), getResources().getQuantityString(R.plurals._2130903047_res_0x7f030007, 10, 10), string2);
                pxz.a(getResources().getString(this.f9998a, string3), string3, this.b, 0, pxz.j() ? 2 : 1);
            } else {
                string = getResources().getString(this.l, 50, 100, 10, string2);
                b();
            }
            pxz.a(string, string2, this.g, this.f, 0);
        }

        public void c(boolean z) {
            this.j = z;
            if (z) {
                this.i.setVisibility(0);
                if (pxz.d()) {
                    h();
                    return;
                } else {
                    b();
                    return;
                }
            }
            this.i.setVisibility(8);
            b();
            this.j = false;
        }
    }

    public void d() {
        ReminderCardView reminderCardView = this.o.get();
        if (reminderCardView == null) {
            return;
        }
        DetailViewWithData detailViewWithData = reminderCardView.b;
        DetailViewWithoutData detailViewWithoutData = reminderCardView.d;
        detailViewWithData.c();
        detailViewWithoutData.d();
    }

    public void a() {
        ReminderCardView reminderCardView = this.o.get();
        if (reminderCardView == null) {
            return;
        }
        DetailViewWithData detailViewWithData = reminderCardView.b;
        DetailViewWithoutData detailViewWithoutData = reminderCardView.d;
        detailViewWithData.d();
        detailViewWithoutData.e();
    }

    public static class DetailViewWithData extends LinearLayout {

        /* renamed from: a, reason: collision with root package name */
        private String f9997a;
        private LinearLayout b;
        private int c;
        private HealthTextView d;
        private ConstraintLayout e;
        private boolean f;
        private boolean g;
        private HealthTextView h;
        private ImageView i;
        private LinearLayout j;
        private HealthTextView l;
        private int o;

        DetailViewWithData(Context context, boolean z) {
            super(context);
            this.f = false;
            this.g = z;
            if (z) {
                this.c = R$string.IDS_resting_heart_rate_low_string;
                this.o = R$string.IDS_resting_heart_rate_low_redirect_string;
            } else {
                this.c = R$string.IDS_resting_heart_rate_high_string;
                this.o = R$string.IDS_resting_heart_rate_high_redirect_string;
            }
            e();
        }

        private void e() {
            inflate(getContext(), R.layout.focus_view_detail_warning_hr, this);
            this.b = (LinearLayout) findViewById(R.id.warning_card_list_layout);
            this.j = (LinearLayout) findViewById(R.id.warning_more_layout);
            this.i = (ImageView) findViewById(R.id.image_view_detail);
            if (LanguageUtil.bc(getContext())) {
                this.i.setBackground(getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
            } else {
                this.i.setBackground(getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
            }
            this.j.setOnClickListener(new View.OnClickListener() { // from class: qax
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ReminderCardView.DetailViewWithData.this.dxL_(view);
                }
            });
            this.f9997a = nsf.b(this.c, 50, 100, 10);
            this.h = (HealthTextView) findViewById(R.id.text_rest_heart_rate_tips);
            this.d = (HealthTextView) findViewById(R.id.text_ecg_measurement_reminder);
            this.e = (ConstraintLayout) findViewById(R.id.ecg_measurement_reminder_layout);
            this.l = (HealthTextView) findViewById(R.id.point1);
            this.d.setVisibility(8);
            this.e.setVisibility(8);
            this.l.setVisibility(8);
            if (pxz.c() || !pxz.e()) {
                c();
            } else {
                d();
            }
        }

        public /* synthetic */ void dxL_(View view) {
            if (this.g) {
                BradycardiaAlarmActivity.b(getContext());
            } else {
                WarningHRActivity.e(getContext());
            }
            ViewClickInstrumentation.clickOnView(view);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c() {
            if (this.h != null) {
                LogUtil.a("HealthHeartRate_ReminderCardView", "device disconnected or device not support heartRate downAlarm");
                this.h.setText(this.f9997a);
            } else {
                LogUtil.h("HealthHeartRate_ReminderCardView", "mHeartRateTipTextView is null");
            }
            b();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d() {
            String string;
            int i;
            int i2;
            LogUtil.a("HealthHeartRate_ReminderCardView", "DetailViewWithData device connect,prepare the redirection link");
            String string2 = getResources().getString(R$string.IDS_resting_heart_rate_redirect_setting_string);
            String string3 = getResources().getString(R$string.IDS_heart_rate_ecg_view);
            if (pxz.d()) {
                if (this.f) {
                    a();
                }
                String quantityString = getResources().getQuantityString(R.plurals._2130903088_res_0x7f030030, 100, 100);
                String quantityString2 = getResources().getQuantityString(R.plurals._2130903041_res_0x7f030001, 10, 10);
                if (this.g) {
                    i = R$string.IDS_heart_rate_low_tips;
                    i2 = R$string.IDS_heart_rate_low_ecg_tips;
                } else {
                    i = R$string.IDS_heart_rate_high_tips;
                    i2 = R$string.IDS_heart_rate_high_ecg_tips;
                }
                string = getResources().getString(i, UnitUtil.e(50.0d, 1, 0), quantityString, quantityString2, string2);
                pxz.a(getResources().getString(i2, string3), string3, this.d, 0, pxz.j() ? 2 : 1);
            } else {
                string = getResources().getString(this.o, 50, 100, 10, string2);
                b();
            }
            pxz.a(string, string2, this.h, 1, 0);
        }

        public void a(List<WarningHRDetailView.d> list) {
            this.b.removeAllViews();
            if (list == null || list.size() == 0) {
                invalidate();
                return;
            }
            int i = 0;
            for (WarningHRDetailView.d dVar : list) {
                WarningHRDetailView warningHRDetailView = new WarningHRDetailView(getContext());
                warningHRDetailView.load(dVar);
                this.b.addView(warningHRDetailView, -1, -2);
                i++;
                if (i > 5) {
                    break;
                }
            }
            invalidate();
            if (pxz.d()) {
                a();
            } else {
                b();
            }
            this.f = true;
        }

        private void a() {
            this.d.setVisibility(0);
            this.e.setVisibility(0);
            this.l.setVisibility(0);
        }

        private void b() {
            this.d.setVisibility(8);
            this.e.setVisibility(8);
            this.l.setVisibility(8);
        }
    }
}
