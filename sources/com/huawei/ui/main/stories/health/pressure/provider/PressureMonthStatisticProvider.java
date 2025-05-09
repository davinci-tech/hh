package com.huawei.ui.main.stories.health.pressure.provider;

import android.content.Context;
import android.os.Looper;
import android.os.Message;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.knit.data.MinorProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hihealth.data.model.HiStressMetaData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import defpackage.jec;
import defpackage.pua;
import defpackage.pwr;
import defpackage.qml;
import defpackage.sdf;
import defpackage.sdh;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* loaded from: classes9.dex */
public class PressureMonthStatisticProvider extends MinorProvider<qml> {

    /* renamed from: a, reason: collision with root package name */
    private pwr f10216a;
    private boolean b;
    private Date c;
    private qml g;
    private Date h;
    private SectionBean j;
    private boolean e = true;
    private final d d = new d(this);

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, qml qmlVar) {
        LogUtil.a("PressureMonthStatisticProvider", "parseParams");
        hashMap.put("STATISTIC_AVERAGE", qmlVar.dFF_());
        hashMap.put("STATISTIC_MAX_MIN", qmlVar.dFG_());
        b(hashMap, qmlVar);
        a(hashMap, qmlVar);
    }

    protected void b(HashMap<String, Object> hashMap, qml qmlVar) {
        int i;
        if (!qmlVar.a().isDayData()) {
            if (qmlVar.a().isMonthData()) {
                i = 10003;
            } else if (qmlVar.a().isWeekData()) {
                i = 10002;
            } else if (qmlVar.a().isYearData()) {
                i = 10004;
            } else {
                LogUtil.b("PressureMonthStatisticProvider", "setDescForPieChart type error ");
            }
            String[] a2 = pua.a(i);
            ArrayList arrayList = new ArrayList(2);
            arrayList.add(a2[0]);
            arrayList.add(a2[1]);
            hashMap.put("PIE_CHART_DESC", arrayList);
            hashMap.put("PIE_CHART_ITEM", qmlVar.h());
        }
        i = 10001;
        String[] a22 = pua.a(i);
        ArrayList arrayList2 = new ArrayList(2);
        arrayList2.add(a22[0]);
        arrayList2.add(a22[1]);
        hashMap.put("PIE_CHART_DESC", arrayList2);
        hashMap.put("PIE_CHART_ITEM", qmlVar.h());
    }

    private void a(HashMap<String, Object> hashMap, qml qmlVar) {
        if (qmlVar.l()) {
            LogUtil.a("PressureMonthStatisticProvider", "setDataForAdvice is nature", Boolean.valueOf(qmlVar.l()));
            hashMap.put("ADVICE_HAVE_DATA", 1);
            hashMap.put("ADVICE", qmlVar.j());
        } else {
            LogUtil.a("PressureMonthStatisticProvider", "setDataForAdvice is not nature", Boolean.valueOf(qmlVar.l()));
            hashMap.put("ADVICE_HAVE_DATA", 0);
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return this.e;
    }

    @Override // com.huawei.health.knit.data.MinorProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        LogUtil.a("PressureMonthStatisticProvider", "loadData");
        this.j = sectionBean;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.knit.data.MinorProvider
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onSetSectionBeanData(SectionBean sectionBean, qml qmlVar) {
        if (qmlVar != null) {
            this.e = true;
            this.g = qmlVar;
            this.h = new Date(qmlVar.f());
            this.c = new Date(qmlVar.c() - 1000);
            d();
            if (this.f10216a == null) {
                this.f10216a = new pwr();
            }
            e();
            return;
        }
        this.e = false;
        SectionBean sectionBean2 = this.j;
        if (sectionBean2 != null) {
            sectionBean2.e(this, this.g);
        }
    }

    private void e() {
        if (this.h == null) {
            return;
        }
        this.f10216a.e(this.g.f() / 1000, this.g.c() / 1000, 3, new c(this, 2));
    }

    static class c extends BaseCallback<PressureMonthStatisticProvider> {

        /* renamed from: a, reason: collision with root package name */
        private final int f10217a;

        c(PressureMonthStatisticProvider pressureMonthStatisticProvider, int i) {
            super(pressureMonthStatisticProvider);
            this.f10217a = i;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.huawei.ui.main.stories.health.pressure.provider.BaseCallback
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onCall(PressureMonthStatisticProvider pressureMonthStatisticProvider, int i, Object obj) {
            int i2 = this.f10217a;
            if (i2 == 1) {
                LogUtil.a("PressureMonthStatisticProvider", "month requestAdviceLibData errorCode = ", Integer.valueOf(i));
                if (i == 0) {
                    pressureMonthStatisticProvider.d.obtainMessage(1001, Integer.valueOf(i)).sendToTarget();
                    return;
                }
                return;
            }
            if (i2 != 2) {
                LogUtil.h("PressureMonthStatisticProvider", "error type");
            } else if (obj != null) {
                pressureMonthStatisticProvider.d.obtainMessage(1005, obj).sendToTarget();
            }
        }
    }

    static class d extends BaseHandler<PressureMonthStatisticProvider> {
        d(PressureMonthStatisticProvider pressureMonthStatisticProvider) {
            super(Looper.getMainLooper(), pressureMonthStatisticProvider);
        }

        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dFO_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(PressureMonthStatisticProvider pressureMonthStatisticProvider, Message message) {
            int i = message.what;
            if (i == 1001) {
                pressureMonthStatisticProvider.e(((Integer) message.obj).intValue());
                LogUtil.a("PressureMonthStatisticProvider", "update pressure advice");
            } else if (i == 1005) {
                ArrayList arrayList = (ArrayList) message.obj;
                pressureMonthStatisticProvider.b(arrayList);
                pressureMonthStatisticProvider.b(arrayList, arrayList.size() > 0);
            } else if (i == 1006) {
                pressureMonthStatisticProvider.c();
            } else {
                LogUtil.a("PressureMonthStatisticProvider", "no case match!");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(ArrayList<HiStressMetaData> arrayList) {
        int b = pua.b(arrayList);
        if (b > 0) {
            this.g.dFH_(pua.dtH_(BaseApplication.getContext().getString(R$string.IDS_hw_pressure_averge_pressure_of_month), UnitUtil.e(b, 1, 0), pua.c(b)));
        }
        b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<HiStressMetaData> list, boolean z) {
        int r;
        LogUtil.a("PressureMonthStatisticProvider", "upToGradePieChart,isNature= ", Boolean.valueOf(this.b), " haveData = ", Boolean.valueOf(z));
        if (this.b) {
            r = jec.r(this.h);
        } else {
            r = (jec.r(this.h) - (jec.d(this.h) - 1)) + jec.d(this.c);
        }
        List<sdf> a2 = this.f10216a.a(this.h, r, list);
        if (z && sdh.d(a2)) {
            this.g.c(pua.e(a2));
            this.d.removeMessages(1006);
            this.d.sendEmptyMessageDelayed(1006, 100L);
        }
        b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.f10216a.d(this.c, 59, new c(this, 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        String a2 = UnitUtil.a("M/d", this.h.getTime());
        String a3 = UnitUtil.a("M/d", this.c.getTime());
        String format = String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R$string.IDS_pressure_month_analysis), a2, a3, this.f10216a.e());
        if (this.b) {
            this.g.e(format);
        } else {
            LogUtil.a("PressureMonthStatisticProvider", "is not nature month");
        }
        b();
    }

    private void b() {
        SectionBean sectionBean = this.j;
        if (sectionBean != null) {
            sectionBean.e(this, this.g);
        }
    }

    private void d() {
        this.b = false;
        long f = this.g.f();
        Date date = new Date(f);
        LogUtil.a("PressureMonthStatisticProvider", "currentClickMonth = ", date);
        if (jec.f(date.getTime()) == f) {
            this.b = true;
            this.g.c(true);
        } else {
            this.b = false;
            this.g.c(false);
            LogUtil.a("PressureMonthStatisticProvider", "judgeNatureMonth,isNature = ", Boolean.valueOf(this.b));
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        this.j = null;
    }
}
