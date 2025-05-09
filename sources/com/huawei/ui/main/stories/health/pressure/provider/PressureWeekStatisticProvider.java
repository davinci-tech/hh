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
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* loaded from: classes9.dex */
public class PressureWeekStatisticProvider extends MinorProvider<qml> {

    /* renamed from: a, reason: collision with root package name */
    private boolean f10219a;
    private boolean b = true;
    private final c c = new c(this);
    private Date d;
    private pwr e;
    private SectionBean g;
    private Date h;
    private qml j;

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, qml qmlVar) {
        LogUtil.a("PressureWeekStatisticProvider", "parseParams");
        hashMap.put("STATISTIC_AVERAGE", qmlVar.dFF_());
        hashMap.put("STATISTIC_MAX_MIN", qmlVar.dFG_());
        d(hashMap, qmlVar);
        a(hashMap, qmlVar);
    }

    protected void d(HashMap<String, Object> hashMap, qml qmlVar) {
        int i;
        if (!qmlVar.a().isDayData()) {
            if (qmlVar.a().isMonthData()) {
                i = 10003;
            } else if (qmlVar.a().isWeekData()) {
                i = 10002;
            } else if (qmlVar.a().isYearData()) {
                i = 10004;
            } else {
                LogUtil.b("PressureWeekStatisticProvider", "setDescForPieChart type error ");
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
            LogUtil.a("PressureWeekStatisticProvider", "setDataForAdvice is nature", Boolean.valueOf(qmlVar.l()));
            hashMap.put("ADVICE_HAVE_DATA", 1);
            hashMap.put("ADVICE", qmlVar.j());
        } else {
            LogUtil.a("PressureWeekStatisticProvider", "setDataForAdvice is not nature", Boolean.valueOf(qmlVar.l()));
            hashMap.put("ADVICE_HAVE_DATA", 0);
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return this.b;
    }

    @Override // com.huawei.health.knit.data.MinorProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        LogUtil.a("PressureWeekStatisticProvider", "loadData");
        this.g = sectionBean;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.knit.data.MinorProvider
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public void onSetSectionBeanData(SectionBean sectionBean, qml qmlVar) {
        if (qmlVar != null) {
            this.b = true;
            this.j = qmlVar;
            this.h = new Date(qmlVar.f());
            this.d = new Date(qmlVar.c() - 1000);
            d();
            if (this.e == null) {
                this.e = new pwr();
            }
            b();
            return;
        }
        this.b = false;
        SectionBean sectionBean2 = this.g;
        if (sectionBean2 != null) {
            sectionBean2.e(this, this.j);
        }
    }

    private void b() {
        if (this.h == null) {
            return;
        }
        this.e.e(this.j.f() / 1000, this.j.c() / 1000, 2, new a(this, 2));
    }

    static class a extends BaseCallback<PressureWeekStatisticProvider> {
        private final int c;

        a(PressureWeekStatisticProvider pressureWeekStatisticProvider, int i) {
            super(pressureWeekStatisticProvider);
            this.c = i;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.huawei.ui.main.stories.health.pressure.provider.BaseCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onCall(PressureWeekStatisticProvider pressureWeekStatisticProvider, int i, Object obj) {
            int i2 = this.c;
            if (i2 == 1) {
                LogUtil.a("PressureWeekStatisticProvider", "week requestAdviceLibData errorCode = ", Integer.valueOf(i));
                if (i == 0) {
                    pressureWeekStatisticProvider.c.obtainMessage(1001, Integer.valueOf(i)).sendToTarget();
                    return;
                }
                return;
            }
            if (i2 != 2) {
                LogUtil.h("PressureWeekStatisticProvider", "error type");
            } else if (obj != null) {
                pressureWeekStatisticProvider.c.obtainMessage(1005, obj).sendToTarget();
            }
        }
    }

    static class c extends BaseHandler<PressureWeekStatisticProvider> {
        c(PressureWeekStatisticProvider pressureWeekStatisticProvider) {
            super(Looper.getMainLooper(), pressureWeekStatisticProvider);
        }

        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dFQ_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(PressureWeekStatisticProvider pressureWeekStatisticProvider, Message message) {
            int i = message.what;
            if (i == 1001) {
                pressureWeekStatisticProvider.c(((Integer) message.obj).intValue());
                LogUtil.a("PressureWeekStatisticProvider", "update pressure advice");
            } else if (i == 1005) {
                ArrayList arrayList = (ArrayList) message.obj;
                pressureWeekStatisticProvider.e((ArrayList<HiStressMetaData>) arrayList);
                pressureWeekStatisticProvider.b(arrayList, arrayList.size() > 0);
            } else if (i == 1006) {
                pressureWeekStatisticProvider.c();
            } else {
                LogUtil.a("PressureWeekStatisticProvider", "no case match!");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(ArrayList<HiStressMetaData> arrayList) {
        int b = pua.b(arrayList);
        if (b > 0) {
            this.j.dFH_(pua.dtH_(BaseApplication.getContext().getString(R$string.IDS_hw_pressure_averge_pressure_of_week), UnitUtil.e(b, 1, 0), pua.c(b)));
        }
        e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(List<HiStressMetaData> list, boolean z) {
        LogUtil.a("PressureWeekStatisticProvider", "upToGradePieChart haveData = ", Boolean.valueOf(z));
        List<sdf> a2 = this.e.a(this.h, 7, list);
        if (z && sdh.d(a2)) {
            this.j.c(pua.e(a2));
            this.c.removeMessages(1006);
            this.c.sendEmptyMessageDelayed(1006, 100L);
        }
        e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.e.d(this.d, 13, new a(this, 1));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(int i) {
        String a2 = UnitUtil.a("M/d", this.h.getTime());
        String a3 = UnitUtil.a("M/d", this.d.getTime());
        String format = String.format(Locale.ENGLISH, BaseApplication.getContext().getString(R$string.IDS_pressure_week_analysis), a2, a3, this.e.e());
        if (this.f10219a) {
            this.j.e(format);
        } else {
            LogUtil.a("PressureWeekStatisticProvider", "is not nature week");
        }
        e();
    }

    private void e() {
        SectionBean sectionBean = this.g;
        if (sectionBean != null) {
            sectionBean.e(this, this.j);
        }
    }

    private void d() {
        this.f10219a = false;
        int f = jec.f(new Date(this.j.f()));
        LogUtil.a("PressureWeekStatisticProvider", "getDayOfWeek", Integer.valueOf(f));
        if (Utils.o()) {
            if (f == 1) {
                this.f10219a = true;
            }
        } else if (f == 2) {
            this.f10219a = true;
        }
        this.j.c(this.f10219a);
        LogUtil.a("PressureWeekStatisticProvider", "isNature = ", Boolean.valueOf(this.f10219a));
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        this.g = null;
    }
}
