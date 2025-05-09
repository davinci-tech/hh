package defpackage;

import android.os.Handler;
import android.os.Message;
import com.huawei.health.knit.section.chart.BloodOxygenLineChart;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.linechart.common.HwHealthBaseEntry;
import com.huawei.ui.commonui.linechart.view.HwHealthMarkerView;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenDayDetailFragmentPresenter;
import com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenDayDetailFragmentView;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class pjw extends pjp<BloodOxygenDayDetailFragmentView> implements BloodOxygenDayDetailFragmentPresenter {

    /* renamed from: a, reason: collision with root package name */
    private BloodOxygenDayDetailFragmentView f16162a;
    private List<String> c;
    private b e;
    private Date d = null;
    private long b = 0;

    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenDayDetailFragmentPresenter
    public void initLeftArrowClick() {
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenDayDetailFragmentPresenter
    public void initRightArrowClick() {
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenDayDetailFragmentPresenter
    public void notifyAltitude(int i, List<HiHealthData> list) {
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenDayDetailFragmentPresenter
    public void notifyLakelouise(int i, List<HiHealthData> list) {
    }

    static class b extends Handler {
        private final WeakReference<pjw> e;

        private b(pjw pjwVar) {
            this.e = new WeakReference<>(pjwVar);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            String str;
            long j;
            if (message == null) {
                LogUtil.h("R_BloodOxygen_BloodOxygenDayDetailFragmentPresenterImpl", "handleMessage: msg is null");
                return;
            }
            super.handleMessage(message);
            pjw pjwVar = this.e.get();
            if (pjwVar == null) {
                LogUtil.h("R_BloodOxygen_BloodOxygenDayDetailFragmentPresenterImpl", "handleMessage: presenterImpl is null");
                return;
            }
            int i = message.what;
            if (i == 1) {
                dqj_(message, pjwVar);
                return;
            }
            if (i == 2) {
                if (message.arg1 == 0 && (message.obj instanceof Long)) {
                    j = ((Long) message.obj).longValue();
                    str = String.valueOf(message.arg2);
                } else {
                    str = "--";
                    j = 0;
                }
                if (pjwVar.f16162a != null) {
                    pjwVar.f16162a.setBloodOxygenLatest(str, j);
                    return;
                }
                return;
            }
            if (i == 3) {
                List<HiHealthData> arrayList = new ArrayList<>(16);
                if (message.arg1 == 0 && (message.obj instanceof List)) {
                    arrayList = (List) message.obj;
                }
                if (pjwVar.f16162a != null) {
                    pjwVar.f16162a.setBloodOxygenData(arrayList);
                    return;
                }
                return;
            }
            LogUtil.a("R_BloodOxygen_BloodOxygenDayDetailFragmentPresenterImpl", "MyHandler what unkonw ");
        }

        /* JADX WARN: Removed duplicated region for block: B:10:0x003d  */
        /* JADX WARN: Removed duplicated region for block: B:13:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private void dqj_(android.os.Message r3, defpackage.pjw r4) {
            /*
                r2 = this;
                int r0 = r3.arg1
                if (r0 != 0) goto L34
                java.lang.Object r0 = r3.obj
                boolean r0 = r0 instanceof java.util.List
                if (r0 == 0) goto L34
                java.lang.Object r3 = r3.obj
                java.util.List r3 = (java.util.List) r3
                boolean r0 = defpackage.koq.c(r3)
                if (r0 == 0) goto L29
                java.lang.Object r0 = java.util.Collections.max(r3)
                java.lang.Integer r0 = (java.lang.Integer) r0
                java.lang.String r0 = r0.toString()
                java.lang.Object r3 = java.util.Collections.min(r3)
                java.lang.Integer r3 = (java.lang.Integer) r3
                java.lang.String r3 = r3.toString()
                goto L37
            L29:
                java.lang.String r3 = "handleMessage scoreList is empty"
                java.lang.Object[] r3 = new java.lang.Object[]{r3}
                java.lang.String r0 = "BloodOxygenDayDetailFragmentPresenterImpl"
                com.huawei.hwlogsmodel.LogUtil.h(r0, r3)
            L34:
                java.lang.String r0 = "--"
                r3 = r0
            L37:
                com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenDayDetailFragmentView r1 = defpackage.pjw.d(r4)
                if (r1 == 0) goto L44
                com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenDayDetailFragmentView r4 = defpackage.pjw.d(r4)
                r4.setBloodOxygenMaxAndMin(r0, r3)
            L44:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: pjw.b.dqj_(android.os.Message, pjw):void");
        }
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenDayDetailFragmentPresenter
    public void initPageParams() {
        this.f16162a = a();
        this.b = jec.n(jec.e());
        this.d = new Date(this.b * 1000);
        this.e = new b();
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenDayDetailFragmentPresenter
    public void initBloodOxygenInterval() {
        if (this.f16162a == null) {
            return;
        }
        this.c = new ArrayList(16);
        String e = UnitUtil.e(70.0d, 2, 0);
        String e2 = UnitUtil.e(70.0d, 1, 0);
        String e3 = UnitUtil.e(89.0d, 2, 0);
        String e4 = UnitUtil.e(90.0d, 2, 0);
        this.c.add(e);
        this.c.add(e2);
        this.c.add(e3);
        this.c.add(e4);
        this.f16162a.setBloodOxygenInterval(this.c);
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenDayDetailFragmentPresenter
    public void notifyData(int i, int i2) {
        BloodOxygenDayDetailFragmentView bloodOxygenDayDetailFragmentView = this.f16162a;
        if (bloodOxygenDayDetailFragmentView == null) {
            return;
        }
        BloodOxygenLineChart bloodOxygenLineChart = bloodOxygenDayDetailFragmentView.getBloodOxygenLineChart();
        if (bloodOxygenLineChart == null) {
            LogUtil.h("BloodOxygenDayDetailFragmentPresenterImpl", "the currentBloodOxygenLineChart is NULL");
            return;
        }
        long j = i * 60000;
        this.f16162a.setDayAndWeek(bloodOxygenLineChart.formatRangeText(i, i2), dpg.m(j), jec.ab(new Date(j)), j);
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenDayDetailFragmentPresenter
    public void notifySourceAndTime(String str, List<HwHealthMarkerView.a> list) {
        String c;
        int size = list.size() - 1;
        HwHealthBaseEntry hwHealthBaseEntry = list.get(size).b;
        String string = BaseApplication.getContext().getString(R$string.IDS_hw_health_blood_oxygen_elevation);
        if (hwHealthBaseEntry == null) {
            this.f16162a.notifyNumerical(str, "--", string + " --");
            return;
        }
        int i = 0;
        int i2 = Integer.MAX_VALUE;
        for (T t : list.get(0).e.getEntriesForXValue(hwHealthBaseEntry.getX())) {
            if (t.getY() != 0.0f) {
                i = (int) t.getY();
            }
            if (t.getData() instanceof ecm) {
                ecm ecmVar = (ecm) t.getData();
                if (ecmVar.d() != Integer.MAX_VALUE) {
                    i2 = ecmVar.d();
                }
            }
        }
        String str2 = i2 != Integer.MAX_VALUE ? string + " " + i2 : string + " --";
        if (i != 0) {
            c = UnitUtil.e(i, 1, 0);
        } else {
            c = c(list.get(size).b);
        }
        this.f16162a.notifyNumerical(str, c, str2);
    }

    private String c(HwHealthBaseEntry hwHealthBaseEntry) {
        return (hwHealthBaseEntry == null || ((int) hwHealthBaseEntry.getY()) == Integer.MIN_VALUE) ? "--" : UnitUtil.e(hwHealthBaseEntry.getY(), 1, 0);
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenDayDetailFragmentPresenter
    public void notifyMaxAndMinBloodOxy(int i, List<HiHealthData> list) {
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.arg1 = -1;
        if (list == null) {
            this.e.sendMessage(obtain);
            return;
        }
        if (i == 0 && !list.isEmpty()) {
            ArrayList arrayList = new ArrayList(16);
            Iterator<HiHealthData> it = list.iterator();
            while (it.hasNext()) {
                int intValue = it.next().getIntValue();
                if (intValue > 0 && intValue <= 100) {
                    arrayList.add(Integer.valueOf(intValue));
                }
            }
            obtain.arg1 = 0;
            obtain.obj = arrayList;
        }
        this.e.sendMessage(obtain);
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenDayDetailFragmentPresenter
    public void notifyLatestBloodOxygen(int i, List<HiHealthData> list) {
        Message obtain = Message.obtain();
        obtain.what = 2;
        if (list == null) {
            obtain.arg1 = -1;
            this.e.sendMessage(obtain);
            return;
        }
        if (i != 0 || list.isEmpty()) {
            obtain.arg1 = -1;
            this.e.sendMessage(obtain);
            return;
        }
        int i2 = 0;
        long j = 0;
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData != null && hiHealthData.getStartTime() > j) {
                j = hiHealthData.getStartTime();
                i2 = hiHealthData.getIntValue();
            }
        }
        if (j > 0 && i2 > 0) {
            obtain.arg1 = 0;
            obtain.arg2 = i2;
            obtain.obj = Long.valueOf(j);
        } else {
            obtain.arg1 = 100001;
        }
        this.e.sendMessage(obtain);
    }

    @Override // com.huawei.ui.main.stories.fitness.activity.bloodoxygen.dayfragment.BloodOxygenDayDetailFragmentPresenter
    public void notifyRemindBloodOxygen(int i, List<HiHealthData> list) {
        Message obtain = Message.obtain();
        obtain.what = 3;
        if (list == null) {
            obtain.arg1 = -1;
            this.e.sendMessage(obtain);
            return;
        }
        if (i == 0 && !list.isEmpty()) {
            obtain.arg1 = 0;
            obtain.obj = list;
        } else {
            obtain.arg1 = -1;
        }
        this.e.sendMessage(obtain);
    }
}
