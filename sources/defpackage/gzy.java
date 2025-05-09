package defpackage;

import android.os.Bundle;
import android.text.TextUtils;
import com.google.gson.JsonSyntaxException;
import com.huawei.healthcloud.plugintrack.PluginSportTrackAdapter;
import com.huawei.hihealth.HiAggregateOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiUserPreference;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.Vo2maxDetail;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.utils.StringUtils;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class gzy {
    private static final Object d = new Object();
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static final Object f13029a = new Object();
    private static final Object b = new Object();
    private static final Object c = new Object();
    private boolean f = false;
    private boolean g = true;
    private b h = null;
    private String[] n = null;
    private boolean j = false;
    private boolean i = false;
    private IBaseResponseCallback m = new IBaseResponseCallback() { // from class: gzy.4
        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            Vo2maxDetail vo2maxDetail = obj instanceof Vo2maxDetail ? (Vo2maxDetail) obj : null;
            gzy.this.k.c(gzy.this.a(vo2maxDetail != null ? vo2maxDetail.getVo2maxValue() : 0));
            synchronized (gzy.f13029a) {
                LogUtil.a("Track_PrimalAlgorithm", "configVo2max notifyAll()");
                gzy.f13029a.notifyAll();
            }
        }
    };
    private gzz k = new gzz(true);

    public boolean i() {
        return this.j;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final int i) {
        HiAggregateOption hiAggregateOption = new HiAggregateOption();
        long currentTimeMillis = System.currentTimeMillis();
        long t = HiDateUtil.t(currentTimeMillis);
        long j = i;
        long f = i == 0 ? HiDateUtil.f(currentTimeMillis) : HiDateUtil.t(currentTimeMillis);
        hiAggregateOption.setStartTime(t - (j * 86400000));
        hiAggregateOption.setEndTime(f);
        hiAggregateOption.setReadType(0);
        hiAggregateOption.setAggregateType(3);
        hiAggregateOption.setType(new int[]{46018});
        hiAggregateOption.setConstantsKey(new String[]{"restHeartRate"});
        HiHealthManager.d(BaseApplication.getContext()).aggregateHiHealthData(hiAggregateOption, new HiAggregateListener() { // from class: gzy.2
            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResultIntent(int i2, List<HiHealthData> list, int i3, int i4) {
            }

            @Override // com.huawei.hihealth.data.listener.HiAggregateListener
            public void onResult(List<HiHealthData> list, int i2, int i3) {
                int i4;
                if (list != null) {
                    i4 = 0;
                    for (int i5 = 0; i5 < list.size(); i5++) {
                        i4 = (int) (i4 + list.get(i5).getFloat("restHeartRate"));
                    }
                    if (list.size() > 0) {
                        i4 = Math.round((i4 * 1.0f) / list.size());
                    }
                } else {
                    LogUtil.a("Track_PrimalAlgorithm", "there is no average rest heart rate!");
                    i4 = 0;
                }
                gzy.this.k.c(i, i4);
                int i6 = i;
                if (i6 == 5) {
                    synchronized (gzy.d) {
                        LogUtil.a("Track_PrimalAlgorithm", "getRestHeartRateAverage notifyAll()");
                        gzy.d.notifyAll();
                    }
                    return;
                }
                if (i6 == 0) {
                    gzy.this.d(5);
                } else {
                    LogUtil.a("Track_PrimalAlgorithm", "getRestHeartRateAverage dayCount = ", Integer.valueOf(i6));
                }
            }
        });
    }

    private void i(final int i, int i2) {
        this.k.c(i, i2, new IBaseResponseCallback() { // from class: gzy.1
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i3, Object obj) {
                if (obj != null) {
                    gzy.this.k.d(i, true);
                    gzy.this.k.c(gzy.this.b((List<HiHealthData>) (koq.e(obj, HiHealthData.class) ? (List) obj : null)));
                } else {
                    LogUtil.a("Track_PrimalAlgorithm", "getSportRecordLastWeek no have sport record.");
                    gzy.this.k.d(i, false);
                }
                synchronized (gzy.e) {
                    LogUtil.a("Track_PrimalAlgorithm", "getSportRecordLastWeek notifyAll()");
                    gzy.e.notifyAll();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public char b(List<HiHealthData> list) {
        char c2;
        Map<String, Integer> wearSportData;
        Map<String, String> extendTrackMap;
        int i;
        Integer num;
        if (list == null || list.size() <= 0) {
            LogUtil.h("Track_PrimalAlgorithm", "getBeforeSportRecoverTime trackData is null.");
            return (char) 0;
        }
        Iterator<HiHealthData> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                c2 = 0;
                break;
            }
            HiHealthData next = it.next();
            try {
                HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(next.getMetaData(), HiTrackMetaData.class);
                wearSportData = hiTrackMetaData.getWearSportData();
                extendTrackMap = hiTrackMetaData.getExtendTrackMap();
            } catch (JsonSyntaxException e2) {
                LogUtil.h("Track_PrimalAlgorithm", "getBeforeSportRecoverTime trackMetaData is error", LogAnonymous.b((Throwable) e2));
            }
            if (!extendTrackMap.isEmpty() && extendTrackMap.containsKey("eteAlgoKey")) {
                String str = extendTrackMap.get("eteAlgoKey");
                if (StringUtils.i(str)) {
                    i = CommonUtil.h(str);
                    if (!wearSportData.isEmpty() && wearSportData.containsKey("recovery_time") && (num = wearSportData.get("recovery_time")) != null && num.intValue() != 0) {
                        c2 = this.k.d(num.intValue(), next.getEndTime(), i);
                        break;
                    }
                }
            }
            i = 0;
            if (!wearSportData.isEmpty()) {
                c2 = this.k.d(num.intValue(), next.getEndTime(), i);
                break;
            }
            continue;
        }
        if (c2 != 0) {
            return c2;
        }
        HiTrackMetaData hiTrackMetaData2 = (HiTrackMetaData) HiJsonUtil.e(list.get(0).getMetaData(), HiTrackMetaData.class);
        char a2 = gzu.a((int) hiTrackMetaData2.getAvgPace(), hiTrackMetaData2.getTotalDistance() / 1000);
        this.g = false;
        return a2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(int i) {
        int d2 = haj.d(i, haj.a(this.k.c(), this.k.f()));
        return d2 == 0 || d2 == 1 || d2 == 2;
    }

    private void e(int i, int i2) {
        this.k.c(i, i2, new IBaseResponseCallback() { // from class: gzy.5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i3, Object obj) {
                if (obj != null) {
                    List list = koq.e(obj, HiHealthData.class) ? (List) obj : null;
                    if (list != null) {
                        gzy.this.k.d(list.size());
                    } else {
                        LogUtil.h("Track_PrimalAlgorithm", "getSportCountLastMonth trackData is null.");
                        return;
                    }
                } else {
                    LogUtil.a("Track_PrimalAlgorithm", "getSportCountLastMonth no have sport record.");
                }
                synchronized (gzy.c) {
                    LogUtil.a("Track_PrimalAlgorithm", "getSportCountLastMonth notifyAll()");
                    gzy.c.notifyAll();
                }
            }
        });
    }

    private void e(int i) {
        this.k.c(1, i, new IBaseResponseCallback() { // from class: gzy.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                if (obj != null) {
                    List list = koq.e(obj, HiHealthData.class) ? (List) obj : null;
                    if (list == null) {
                        LogUtil.h("Track_PrimalAlgorithm", "configTodayMoreSecondSport list is null.");
                        return;
                    } else if (list.size() >= 1 && gzu.c((List<HiHealthData>) list)) {
                        if (gzy.this.k.e() == 'A') {
                            gzy.this.k.c('C');
                        } else if (gzy.this.k.e() == 'B') {
                            gzy.this.k.c('C');
                        } else {
                            gzy.this.k.c('D');
                        }
                    }
                } else {
                    LogUtil.a("Track_PrimalAlgorithm", "configTodayMoreSecondSport no have sport record.");
                }
                synchronized (gzy.b) {
                    LogUtil.a("Track_PrimalAlgorithm", "configTodayMoreSecondSport notifyAll()");
                    gzy.this.f = true;
                    gzy.b.notifyAll();
                }
            }
        });
    }

    private void e(String[] strArr, int i, int i2, int i3) {
        Bundle bundle = new Bundle();
        try {
            bundle.putString("voiceType", "sportSmartCoach");
            bundle.putStringArray("beforeSportFirstVoicePath", strArr);
            bundle.putFloat("sectionLower", i);
            bundle.putFloat("sectionUpper", i2);
            bundle.putInt("sportDuration", i3);
            hab.b(new gxi(26, bundle));
        } catch (NumberFormatException e2) {
            LogUtil.b("Track_PrimalAlgorithm", "voiceConstructorPlay exception = ", LogAnonymous.b((Throwable) e2));
        }
    }

    public void d(String[] strArr) {
        String[] strArr2;
        if (strArr == null || strArr.length <= 0) {
            LogUtil.h("Track_PrimalAlgorithm", "beforeSportPrimalVoicePlay firstPathList is empty.");
            return;
        }
        LogUtil.a("Track_PrimalAlgorithm", "beforeSportPrimalVoicePlay", this.n, " ", Boolean.valueOf(this.j));
        int i = 0;
        if (this.j) {
            String[] strArr3 = this.n;
            if (strArr3 == null || strArr3.length <= 0) {
                LogUtil.h("Track_PrimalAlgorithm", "mIsSuggestedRest mPathIdList is empty.");
                return;
            }
            String[] strArr4 = new String[strArr.length + strArr3.length];
            int length = strArr.length;
            int i2 = 0;
            int i3 = 0;
            while (i2 < length) {
                strArr4[i3] = strArr[i2];
                i2++;
                i3++;
            }
            String[] strArr5 = this.n;
            int length2 = strArr5.length;
            while (i < length2) {
                strArr4[i3] = had.d().d(strArr5[i]);
                i++;
                i3++;
            }
            hab.b(strArr4);
            return;
        }
        hab.b(strArr);
        b bVar = this.h;
        if (bVar == null || (strArr2 = this.n) == null) {
            LogUtil.h("Track_PrimalAlgorithm", "beforeSportPrimalVoicePlay mPathIdList is empty.");
            if (this.k.c(7)) {
                this.n = new String[]{"L138"};
                this.h = new b("fatLoss", true, String.valueOf(this.k.e()), 0);
            } else {
                this.n = new String[]{"L132", "L136"};
                this.h = new b("fatLoss", false, null, 0);
            }
            if (this.h.b != 0 || this.h.f13032a != 0 || this.h.d != 0) {
                e(this.n, this.h.b, this.h.d, this.h.f13032a);
            } else {
                LogUtil.a("Track_PrimalAlgorithm", "enter defaultVoicePlayBeforeSport");
                j();
            }
            e(this.h.b, this.h.d, this.h.f13032a * 60000);
            this.j = false;
            return;
        }
        e(strArr2, bVar.b, this.h.d, this.h.f13032a);
    }

    public void c(int i) {
        d(0);
        hac.a().e(this.k.b() >= 24);
        Object obj = d;
        synchronized (obj) {
            try {
                obj.wait(5000L);
            } catch (InterruptedException e2) {
                LogUtil.b("Track_PrimalAlgorithm", "query last five day rest heart rate", LogAnonymous.b((Throwable) e2));
            }
        }
        int b2 = this.k.b(0) - this.k.b(5);
        if (b2 >= 10) {
            this.n = new String[]{"L204"};
            this.j = true;
            return;
        }
        i(7, i);
        PluginSportTrackAdapter c2 = gso.e().c();
        if (c2 != null) {
            c2.readLastVo2max(this.m);
        }
        int i2 = b2 > 5 ? -5 : 0;
        this.k.a(i2);
        Object obj2 = e;
        synchronized (obj2) {
            try {
                obj2.wait(5000L);
            } catch (InterruptedException e3) {
                LogUtil.b("Track_PrimalAlgorithm", "query last week sport data", LogAnonymous.b((Throwable) e3));
            }
        }
        if (this.k.c(7)) {
            e(i);
            d(i, i2);
        } else {
            b(i, i2);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x009a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void b(int r14, int r15) {
        /*
            r13 = this;
            java.lang.Object r0 = defpackage.gzy.f13029a
            monitor-enter(r0)
            r1 = 1
            r2 = 0
            r3 = 2
            r4 = 5000(0x1388, double:2.4703E-320)
            r0.wait(r4)     // Catch: java.lang.Throwable -> Lc java.lang.InterruptedException -> Lf
            goto L21
        Lc:
            r14 = move-exception
            goto Lb7
        Lf:
            r6 = move-exception
            java.lang.Object[] r7 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> Lc
            java.lang.String r8 = "query vo2max level "
            r7[r2] = r8     // Catch: java.lang.Throwable -> Lc
            java.lang.String r6 = health.compact.a.LogAnonymous.b(r6)     // Catch: java.lang.Throwable -> Lc
            r7[r1] = r6     // Catch: java.lang.Throwable -> Lc
            java.lang.String r6 = "Track_PrimalAlgorithm"
            com.huawei.hwlogsmodel.LogUtil.b(r6, r7)     // Catch: java.lang.Throwable -> Lc
        L21:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lc
            gzz r0 = r13.k
            int r0 = r0.b()
            r6 = 25
            if (r0 < r6) goto L4e
            gzz r0 = r13.k
            boolean r0 = r0.l()
            if (r0 == 0) goto L4e
            gzy$b r14 = new gzy$b
            java.lang.String r8 = "fatLoss"
            r9 = 0
            r10 = 0
            r12 = 0
            r6 = r14
            r7 = r13
            r11 = r15
            r6.<init>(r8, r9, r10, r11)
            r13.h = r14
            java.lang.String r14 = "L132"
            java.lang.String r15 = "L136"
            java.lang.String[] r14 = new java.lang.String[]{r14, r15}
            r13.n = r14
            goto Lb4
        L4e:
            r0 = 30
            r13.e(r0, r14)
            java.lang.Object r14 = defpackage.gzy.c
            monitor-enter(r14)
            r14.wait(r4)     // Catch: java.lang.Throwable -> L5a java.lang.InterruptedException -> L5c
            goto L6e
        L5a:
            r15 = move-exception
            goto Lb5
        L5c:
            r0 = move-exception
            java.lang.Object[] r3 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L5a
            java.lang.String r4 = "query last one month sport data "
            r3[r2] = r4     // Catch: java.lang.Throwable -> L5a
            java.lang.String r0 = health.compact.a.LogAnonymous.b(r0)     // Catch: java.lang.Throwable -> L5a
            r3[r1] = r0     // Catch: java.lang.Throwable -> L5a
            java.lang.String r0 = "Track_PrimalAlgorithm"
            com.huawei.hwlogsmodel.LogUtil.b(r0, r3)     // Catch: java.lang.Throwable -> L5a
        L6e:
            monitor-exit(r14)     // Catch: java.lang.Throwable -> L5a
            java.lang.String r14 = defpackage.gzv.b()     // Catch: java.lang.NumberFormatException -> L7e
            boolean r0 = android.text.TextUtils.isEmpty(r14)     // Catch: java.lang.NumberFormatException -> L7e
            if (r0 != 0) goto L8e
            int r14 = java.lang.Integer.parseInt(r14)     // Catch: java.lang.NumberFormatException -> L7e
            goto L8f
        L7e:
            r14 = move-exception
            java.lang.String r0 = "dealLastWeekHaveNoSportData exception = "
            java.lang.String r14 = health.compact.a.LogAnonymous.b(r14)
            java.lang.Object[] r14 = new java.lang.Object[]{r0, r14}
            java.lang.String r0 = "Track_PrimalAlgorithm"
            com.huawei.hwlogsmodel.LogUtil.b(r0, r14)
        L8e:
            r14 = 4
        L8f:
            gzz r0 = r13.k
            int r0 = r0.i()
            if (r0 < r14) goto L9a
            java.lang.String r14 = "cardio"
            goto L9c
        L9a:
            java.lang.String r14 = "recovery"
        L9c:
            r2 = r14
            gzy$b r14 = new gzy$b
            r3 = 0
            r4 = 0
            r6 = 0
            r0 = r14
            r1 = r13
            r5 = r15
            r0.<init>(r2, r3, r4, r5)
            r13.h = r14
            java.lang.String r14 = "L132"
            java.lang.String r15 = "L136"
            java.lang.String[] r14 = new java.lang.String[]{r14, r15}
            r13.n = r14
        Lb4:
            return
        Lb5:
            monitor-exit(r14)     // Catch: java.lang.Throwable -> L5a
            throw r15
        Lb7:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> Lc
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.gzy.b(int, int):void");
    }

    private void d(int i, int i2) {
        Object obj = f13029a;
        synchronized (obj) {
            try {
                obj.wait(5000L);
            } catch (InterruptedException e2) {
                LogUtil.b("Track_PrimalAlgorithm", "query vo2max level ", LogAnonymous.b((Throwable) e2));
            }
        }
        if (!this.f) {
            Object obj2 = b;
            synchronized (obj2) {
                try {
                    obj2.wait(5000L);
                } catch (InterruptedException e3) {
                    LogUtil.b("Track_PrimalAlgorithm", "query today sport data ", LogAnonymous.b((Throwable) e3));
                }
            }
        }
        LogUtil.a("Track_PrimalAlgorithm", "dealLastWeekHaveSportData recoverTime ", Character.valueOf(this.k.e()));
        if (this.k.e() == 'D') {
            this.n = new String[]{"L131"};
            this.j = true;
        } else if (this.k.b() >= 25 && this.k.l()) {
            LogUtil.a("Track_PrimalAlgorithm", "dealLastWeekHaveSportData dealFatLossHasHistory");
            b(i2);
        } else {
            c(i, i2);
        }
    }

    private void b(int i) {
        if (!this.g) {
            this.n = new String[]{"L138"};
        } else if (this.k.e() == 'A') {
            this.n = new String[]{"L140", "empty_500", "L138"};
        } else if (this.k.e() == 'B') {
            this.n = new String[]{"L138"};
        } else if (this.k.e() == 'C') {
            this.n = new String[]{"L137", "L135", "L136"};
        } else {
            this.n = new String[]{"L131"};
            this.j = true;
            return;
        }
        this.h = new b("fatLoss", true, String.valueOf(this.k.e()), i);
    }

    private void c(int i, int i2) {
        String str;
        if (!this.g) {
            this.n = new String[]{"L138"};
            str = "fatLoss";
        } else {
            if (this.k.e() == 'A') {
                a(i, i2);
                return;
            }
            if (this.k.e() == 'B') {
                this.n = new String[]{"L142", "L138"};
                str = "cardio";
            } else if (this.k.e() == 'C') {
                this.n = new String[]{"L137", "L135", "L136"};
                str = "recovery";
            } else {
                this.n = new String[]{"L131"};
                this.j = true;
                return;
            }
        }
        this.h = new b(str, true, String.valueOf(this.k.e()), i2);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0028  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void a(int r14, int r15) {
        /*
            r13 = this;
            java.lang.String r0 = defpackage.gzv.b()     // Catch: java.lang.NumberFormatException -> Lf
            boolean r1 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.NumberFormatException -> Lf
            if (r1 != 0) goto L1f
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.NumberFormatException -> Lf
            goto L20
        Lf:
            r0 = move-exception
            java.lang.String r1 = "dealNoFatLossMinimumecoverTime exception = "
            java.lang.String r0 = health.compact.a.LogAnonymous.b(r0)
            java.lang.Object[] r0 = new java.lang.Object[]{r1, r0}
            java.lang.String r1 = "Track_PrimalAlgorithm"
            com.huawei.hwlogsmodel.LogUtil.b(r1, r0)
        L1f:
            r0 = 4
        L20:
            r1 = 30
            r13.e(r1, r14)
            java.lang.Object r14 = defpackage.gzy.c
            monitor-enter(r14)
            r1 = 5000(0x1388, double:2.4703E-320)
            r3 = 1
            r4 = 0
            r5 = 2
            r14.wait(r1)     // Catch: java.lang.Throwable -> L31 java.lang.InterruptedException -> L33
            goto L45
        L31:
            r15 = move-exception
            goto L7c
        L33:
            r1 = move-exception
            java.lang.Object[] r2 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> L31
            java.lang.String r6 = "dealNoFatLossMinimumecoverTime exception = "
            r2[r4] = r6     // Catch: java.lang.Throwable -> L31
            java.lang.String r1 = health.compact.a.LogAnonymous.b(r1)     // Catch: java.lang.Throwable -> L31
            r2[r3] = r1     // Catch: java.lang.Throwable -> L31
            java.lang.String r1 = "Track_PrimalAlgorithm"
            com.huawei.hwlogsmodel.LogUtil.b(r1, r2)     // Catch: java.lang.Throwable -> L31
        L45:
            gzz r1 = r13.k     // Catch: java.lang.Throwable -> L31
            int r1 = r1.i()     // Catch: java.lang.Throwable -> L31
            if (r1 < r0) goto L50
            java.lang.String r0 = "cardio"
            goto L52
        L50:
            java.lang.String r0 = "fatLoss"
        L52:
            r8 = r0
            r0 = 3
            java.lang.String[] r0 = new java.lang.String[r0]     // Catch: java.lang.Throwable -> L31
            java.lang.String r1 = "L140"
            r0[r4] = r1     // Catch: java.lang.Throwable -> L31
            java.lang.String r1 = "L209"
            r0[r3] = r1     // Catch: java.lang.Throwable -> L31
            java.lang.String r1 = "L138"
            r0[r5] = r1     // Catch: java.lang.Throwable -> L31
            r13.n = r0     // Catch: java.lang.Throwable -> L31
            gzy$b r0 = new gzy$b     // Catch: java.lang.Throwable -> L31
            gzz r1 = r13.k     // Catch: java.lang.Throwable -> L31
            r9 = 1
            char r1 = r1.e()     // Catch: java.lang.Throwable -> L31
            java.lang.String r10 = java.lang.String.valueOf(r1)     // Catch: java.lang.Throwable -> L31
            r12 = 0
            r6 = r0
            r7 = r13
            r11 = r15
            r6.<init>(r8, r9, r10, r11)     // Catch: java.lang.Throwable -> L31
            r13.h = r0     // Catch: java.lang.Throwable -> L31
            monitor-exit(r14)     // Catch: java.lang.Throwable -> L31
            return
        L7c:
            monitor-exit(r14)     // Catch: java.lang.Throwable -> L31
            throw r15
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.gzy.a(int, int):void");
    }

    private void j() {
        b bVar = new b();
        this.h = bVar;
        e(this.n, bVar.b, this.h.d, this.h.f13032a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(int i, int i2, long j) {
        synchronized (this) {
            if (!this.i) {
                hac.a().b(i);
                hac.a().d(i2);
                hac.a().a(j);
                this.i = true;
            }
        }
    }

    public class b {

        /* renamed from: a, reason: collision with root package name */
        private int f13032a;
        private int b;
        private int d;

        private b() {
            if (gzy.this.n == null || gzy.this.n.length <= 0) {
                gzy.this.n = new String[]{"L138"};
            }
            int e = e();
            int c = c();
            this.b = (int) (e * 0.6f);
            this.d = (int) (((e - c) * 0.75f) + c);
            this.f13032a = 30;
        }

        private b(String str, boolean z, String str2, int i) {
            int e = e();
            int c = c();
            try {
                String b = gzv.b(true, str, false);
                if (!TextUtils.isEmpty(b)) {
                    this.b = (int) ((Float.parseFloat(b) / 100.0f) * e);
                }
                String b2 = gzv.b(true, str, true);
                if (!TextUtils.isEmpty(b2)) {
                    this.d = (int) (((Float.parseFloat(b2) / 100.0f) * (e - c)) + c);
                }
                hac.a().c(e);
                String e2 = gzv.e(!z, str, str2);
                int parseInt = Integer.parseInt("".equals(e2) ? gzv.e(!z, "cardio", str2) : e2) + i;
                this.f13032a = parseInt;
                gzy.this.e(this.b, this.d, parseInt * 60000);
            } catch (NumberFormatException e3) {
                LogUtil.b("Track_PrimalAlgorithm", "PrimalParamData numberFormatException = ", LogAnonymous.b((Throwable) e3));
            }
        }

        private int e() {
            HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("custom.UserPreference_HeartZone_Config");
            int f = 220 - gzy.this.k.f();
            if (userPreference == null) {
                return f;
            }
            try {
                if (userPreference.getValue() == null) {
                    return f;
                }
                String[] split = userPreference.getValue().split(",")[1].split("\\|");
                return split.length >= 6 ? Integer.parseInt(split[5]) : f;
            } catch (NumberFormatException e) {
                LogUtil.b("Track_PrimalAlgorithm", "getMaxHeartRate numberFormatException = ", LogAnonymous.b((Throwable) e));
                return f;
            }
        }

        private int c() {
            try {
                String c = gzv.c();
                int parseInt = TextUtils.isEmpty(c) ? 60 : Integer.parseInt(c);
                HiUserPreference userPreference = HiHealthManager.d(BaseApplication.getContext()).getUserPreference("custom.UserPreference_Rest_HeartRate");
                return userPreference != null ? Integer.parseInt(userPreference.getValue()) : parseInt;
            } catch (NumberFormatException e) {
                LogUtil.b("Track_PrimalAlgorithm", "getHrrHeartRate() exception = ", LogAnonymous.b((Throwable) e));
                return 60;
            }
        }
    }
}
