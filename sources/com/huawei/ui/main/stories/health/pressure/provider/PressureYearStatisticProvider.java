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
import defpackage.pua;
import defpackage.pwr;
import defpackage.qml;
import defpackage.sdf;
import defpackage.sdh;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes9.dex */
public class PressureYearStatisticProvider extends MinorProvider<qml> {
    private pwr b;
    private qml c;
    private SectionBean f;
    private boolean d = true;
    private Date j = null;
    private Date e = null;

    /* renamed from: a, reason: collision with root package name */
    private final e f10220a = new e(this);

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, qml qmlVar) {
        LogUtil.a("PressureYearStatisticProvider", "parseParams");
        hashMap.put("STATISTIC_AVERAGE", qmlVar.dFF_());
        hashMap.put("STATISTIC_MAX_MIN", qmlVar.dFG_());
        d(hashMap, qmlVar);
        hashMap.put("ADVICE_HAVE_DATA", 0);
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
                LogUtil.b("PressureYearStatisticProvider", "setDescForPieChart type error ");
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

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return this.d;
    }

    @Override // com.huawei.health.knit.data.MinorProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        LogUtil.a("PressureYearStatisticProvider", "loadData");
        this.f = sectionBean;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.knit.data.MinorProvider
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void onSetSectionBeanData(SectionBean sectionBean, qml qmlVar) {
        if (qmlVar != null) {
            this.d = true;
            this.c = qmlVar;
            Date date = new Date(qmlVar.f());
            this.j = date;
            this.e = b(date);
            if (this.b == null) {
                this.b = new pwr();
            }
            b();
            return;
        }
        this.d = false;
        SectionBean sectionBean2 = this.f;
        if (sectionBean2 != null) {
            sectionBean2.e(this, this.c);
        }
    }

    private void b() {
        if (this.j == null) {
            return;
        }
        this.b.e(this.c.f() / 1000, this.c.c() / 1000, 4, new d(this, 2));
    }

    static class d extends BaseCallback<PressureYearStatisticProvider> {
        private final int b;

        d(PressureYearStatisticProvider pressureYearStatisticProvider, int i) {
            super(pressureYearStatisticProvider);
            this.b = i;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.huawei.ui.main.stories.health.pressure.provider.BaseCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onCall(PressureYearStatisticProvider pressureYearStatisticProvider, int i, Object obj) {
            int i2 = this.b;
            if (i2 == 1) {
                LogUtil.a("PressureYearStatisticProvider", "month requestAdviceLibData errorCode = ", Integer.valueOf(i));
                if (i == 0) {
                    pressureYearStatisticProvider.f10220a.obtainMessage(1001, Integer.valueOf(i)).sendToTarget();
                    return;
                }
                return;
            }
            if (i2 != 2) {
                LogUtil.h("PressureYearStatisticProvider", "error type");
            } else if (obj != null) {
                pressureYearStatisticProvider.f10220a.obtainMessage(1005, obj).sendToTarget();
            }
        }
    }

    static class e extends BaseHandler<PressureYearStatisticProvider> {
        e(PressureYearStatisticProvider pressureYearStatisticProvider) {
            super(Looper.getMainLooper(), pressureYearStatisticProvider);
        }

        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dFR_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(PressureYearStatisticProvider pressureYearStatisticProvider, Message message) {
            if (message.what == 1005) {
                ArrayList arrayList = (ArrayList) message.obj;
                pressureYearStatisticProvider.c(arrayList, arrayList.size() > 0);
            } else {
                LogUtil.a("PressureYearStatisticProvider", "no case match!");
            }
        }
    }

    private void e(List<sdf> list) {
        int d2 = pua.d(list);
        if (d2 > 0) {
            this.c.dFH_(pua.dtH_(BaseApplication.getContext().getString(R$string.IDS_hw_pressure_averge_pressure_of_year), UnitUtil.e(d2, 1, 0), pua.c(d2)));
        }
        d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(List<HiStressMetaData> list, boolean z) {
        LogUtil.a("PressureYearStatisticProvider", "upToGradePieChart haveData = ", Boolean.valueOf(z));
        List<sdf> e2 = this.b.e(this.e, list);
        if (z && sdh.d(e2)) {
            this.c.c(pua.e(e2));
            e(e2);
        }
        d();
    }

    private void d() {
        SectionBean sectionBean = this.f;
        if (sectionBean != null) {
            sectionBean.e(this, this.c);
        }
    }

    private Date b(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(5, 1);
        return calendar.getTime();
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        this.f = null;
    }
}
