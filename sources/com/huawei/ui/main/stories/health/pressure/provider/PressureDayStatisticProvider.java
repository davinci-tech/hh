package com.huawei.ui.main.stories.health.pressure.provider;

import android.content.Context;
import android.os.Looper;
import android.os.Message;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.knit.data.MinorProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiStressMetaData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.main.R$string;
import defpackage.jec;
import defpackage.kpl;
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

/* loaded from: classes9.dex */
public class PressureDayStatisticProvider extends MinorProvider<qml> {

    /* renamed from: a, reason: collision with root package name */
    private qml f10210a;
    private SectionBean c;
    private pwr d;
    private Date h;
    private boolean b = true;
    private final c e = new c(this);

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, qml qmlVar) {
        LogUtil.a("PressureDayStatisticProvider", "parseParams");
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
                LogUtil.b("PressureDayStatisticProvider", "setDescForPieChart type error ");
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
        hashMap.put("ADVICE_HAVE_DATA", 1);
        hashMap.put("ADVICE", qmlVar.j());
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return this.b;
    }

    @Override // com.huawei.health.knit.data.MinorProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        LogUtil.a("PressureDayStatisticProvider", "loadData");
        this.c = sectionBean;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.huawei.health.knit.data.MinorProvider
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onSetSectionBeanData(SectionBean sectionBean, qml qmlVar) {
        if (qmlVar != null && qmlVar.i()) {
            this.b = true;
            this.f10210a = qmlVar;
            this.h = new Date(qmlVar.f());
            if (this.d == null) {
                this.d = new pwr();
            }
            a();
            return;
        }
        this.b = false;
        SectionBean sectionBean2 = this.c;
        if (sectionBean2 != null) {
            sectionBean2.e(this, this.f10210a);
        }
    }

    private void a() {
        Date date = this.h;
        if (date == null) {
            return;
        }
        this.d.a(jec.a(date), 1, new b(this, 2));
    }

    static class b extends BaseCallback<PressureDayStatisticProvider> {
        private final int e;

        b(PressureDayStatisticProvider pressureDayStatisticProvider, int i) {
            super(pressureDayStatisticProvider);
            this.e = i;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @Override // com.huawei.ui.main.stories.health.pressure.provider.BaseCallback
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onCall(PressureDayStatisticProvider pressureDayStatisticProvider, int i, Object obj) {
            int i2 = this.e;
            if (i2 == 1) {
                Message obtain = Message.obtain();
                obtain.obj = obj;
                obtain.arg1 = i;
                obtain.what = 1004;
                pressureDayStatisticProvider.e.sendMessage(obtain);
                return;
            }
            if (i2 == 2) {
                if (obj != null) {
                    pressureDayStatisticProvider.e.obtainMessage(1005, obj).sendToTarget();
                }
            } else if (i2 != 3) {
                LogUtil.h("PressureDayStatisticProvider", "error type");
            } else if (i == 0) {
                pressureDayStatisticProvider.e.obtainMessage(1001, Integer.valueOf(i)).sendToTarget();
            }
        }
    }

    static class c extends BaseHandler<PressureDayStatisticProvider> {
        c(PressureDayStatisticProvider pressureDayStatisticProvider) {
            super(Looper.getMainLooper(), pressureDayStatisticProvider);
        }

        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dFJ_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(PressureDayStatisticProvider pressureDayStatisticProvider, Message message) {
            int i = message.what;
            if (i == 1001) {
                pressureDayStatisticProvider.a(((Integer) message.obj).intValue());
            }
            switch (i) {
                case 1004:
                    if (message.arg1 != 0) {
                        LogUtil.h("PressureDayStatisticProvider", "get SCORE values err , Code = ", Integer.valueOf(message.arg1));
                        break;
                    } else {
                        List list = (List) message.obj;
                        if (list.size() == 1) {
                            LogUtil.a("PressureDayStatisticProvider", "SCORE = ", Integer.valueOf(((HiStressMetaData) list.get(0)).fetchStressScore()));
                            pressureDayStatisticProvider.e(((HiStressMetaData) list.get(0)).fetchStressScore());
                            break;
                        } else {
                            LogUtil.a("PressureDayStatisticProvider", "SCORE values is 0");
                            pressureDayStatisticProvider.e(0);
                            break;
                        }
                    }
                case 1005:
                    if (message.obj instanceof List) {
                        pressureDayStatisticProvider.d((List<HiHealthData>) message.obj);
                        break;
                    }
                    break;
                case 1006:
                    pressureDayStatisticProvider.c();
                    break;
                default:
                    LogUtil.h("PressureDayStatisticProvider", "MyHandler handleMessage switch default");
                    break;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(List<HiHealthData> list) {
        LogUtil.a("PressureDayStatisticProvider", "now it updateDiagram");
        List<sdf> c2 = this.d.c(list);
        d();
        this.f10210a.dFI_(pua.dtI_(c2, BaseApplication.getContext().getString(R$string.IDS_hw_pressure_averge_pressure_of_day), 10001));
        if (list.size() > 0) {
            this.e.removeMessages(1006);
            this.e.sendEmptyMessageDelayed(1006, 100L);
            this.f10210a.c(pua.e(this.d.c(list)));
        }
        e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        this.f10210a.e(this.d.e());
        e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        Date date = this.h;
        if (date == null) {
            return;
        }
        this.d.d(jec.b(date), 7, new b(this, 3));
    }

    private void d() {
        Date date = this.h;
        if (date != null) {
            kpl.c().a(jec.l(date), jec.k(this.h), new b(this, 1));
        } else {
            LogUtil.a("PressureDayStatisticProvider", "getScoreValue mStartDateTimeDate is null ");
            e(0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i) {
        if (i > 0) {
            this.f10210a.dFH_(pua.dtH_(BaseApplication.getContext().getString(R$string.IDS_hw_pressure_averge_pressure_of_day), UnitUtil.e(i, 1, 0), sdh.e(i)));
        }
        e();
    }

    private void e() {
        SectionBean sectionBean = this.c;
        if (sectionBean != null) {
            sectionBean.e(this, this.f10210a);
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        this.c = null;
    }
}
