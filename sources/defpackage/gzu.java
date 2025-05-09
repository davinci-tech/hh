package defpackage;

import android.os.Bundle;
import com.google.gson.JsonSyntaxException;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.utils.StringUtils;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class gzu {
    private static final Object b = new Object();
    private static final Object d = new Object();
    private static final Object e = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static final Object f13023a = new Object();
    private boolean c = false;
    private e j = null;
    private String[] i = null;
    private boolean f = false;
    private boolean g = false;
    private gzz h = new gzz(false);

    public static char a(int i, int i2) {
        if (i2 < 3) {
            return 'A';
        }
        if (i2 < 4) {
            return i < 300 ? 'B' : 'A';
        }
        if (i2 < 5) {
            return i < 600 ? 'B' : 'A';
        }
        if (i2 < 6) {
            return i < 720 ? 'B' : 'A';
        }
        if (i2 < 7) {
            return i < 300 ? 'C' : 'B';
        }
        if (i2 < 10) {
            return i < 480 ? 'C' : 'B';
        }
        if (i2 == 10) {
            return i < 540 ? 'C' : 'B';
        }
        if (i2 < 15) {
            return 'B';
        }
        return (i2 >= 20 || i < 360) ? 'D' : 'C';
    }

    public boolean e() {
        return this.f;
    }

    private void c(final int i, int i2) {
        this.h.c(i, i2, new IBaseResponseCallback() { // from class: gzu.5
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i3, Object obj) {
                if (obj != null) {
                    gzu.this.h.d(i, true);
                    LogUtil.a("Track_NonPrimalAlgorithm", "configSportRecordLastWeek have sport record.");
                } else {
                    gzu.this.h.d(i, false);
                }
                synchronized (gzu.b) {
                    LogUtil.a("Track_NonPrimalAlgorithm", "configSportRecordLastWeek notifyAll()");
                    gzu.b.notifyAll();
                }
            }
        });
    }

    private void j(int i) {
        this.h.c(1, i, new IBaseResponseCallback() { // from class: gzu.3
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                if (koq.e(obj, HiHealthData.class)) {
                    List list = (List) obj;
                    gzu.this.h.a(gzu.this.e((List<HiHealthData>) list));
                    if (list.size() >= 1 && gzu.c((List<HiHealthData>) list)) {
                        if (gzu.this.h.e() == 'A') {
                            gzu.this.h.c('C');
                        } else if (gzu.this.h.e() == 'B') {
                            gzu.this.h.c('C');
                        } else {
                            gzu.this.h.c('D');
                        }
                    }
                } else {
                    LogUtil.a("Track_NonPrimalAlgorithm", "getTodaySportRecord no have sport record.");
                    gzu.this.h.a('A');
                }
                synchronized (gzu.d) {
                    LogUtil.a("Track_NonPrimalAlgorithm", "getTodaySportRecord notifyAll()");
                    gzu.d.notifyAll();
                }
            }
        });
    }

    private void i(int i) {
        long v = jdl.v(System.currentTimeMillis());
        gzz.b(v, jdl.e(v), i, new IBaseResponseCallback() { // from class: gzu.4
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                if (obj != null) {
                    gzu.this.h.b(gzu.this.e((List<HiHealthData>) (koq.e(obj, HiHealthData.class) ? (List) obj : null)));
                } else {
                    LogUtil.a("Track_NonPrimalAlgorithm", "getYesterdaySportRecord no have sport record.");
                    gzu.this.h.b('A');
                }
                synchronized (gzu.e) {
                    LogUtil.a("Track_NonPrimalAlgorithm", "getYesterdaySportRecord notifyAll()");
                    gzu.this.c = true;
                    gzu.e.notifyAll();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public char e(List<HiHealthData> list) {
        char c;
        Map<String, Integer> wearSportData;
        Map<String, String> extendTrackMap;
        int i;
        Integer num;
        if (list == null || list.size() <= 0) {
            LogUtil.h("Track_NonPrimalAlgorithm", "getBeforeSportRecoverTime trackData is null.");
            return (char) 0;
        }
        Iterator<HiHealthData> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                c = 0;
                break;
            }
            HiHealthData next = it.next();
            try {
                HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(next.getMetaData(), HiTrackMetaData.class);
                wearSportData = hiTrackMetaData.getWearSportData();
                extendTrackMap = hiTrackMetaData.getExtendTrackMap();
            } catch (JsonSyntaxException unused) {
                LogUtil.h("Track_NonPrimalAlgorithm", "getBeforeSportRecoverTime trackMetaData is error");
            }
            if (!extendTrackMap.isEmpty() && extendTrackMap.containsKey("eteAlgoKey")) {
                String str = extendTrackMap.get("eteAlgoKey");
                if (StringUtils.i(str)) {
                    i = CommonUtil.h(str);
                    if (wearSportData != null && wearSportData.containsKey("recovery_time") && (num = wearSportData.get("recovery_time")) != null) {
                        c = this.h.d(num.intValue(), next.getEndTime(), i);
                        break;
                    }
                }
            }
            i = 0;
            if (wearSportData != null) {
                c = this.h.d(num.intValue(), next.getEndTime(), i);
                break;
            }
            continue;
        }
        if (c != 0) {
            return c;
        }
        HiTrackMetaData hiTrackMetaData2 = (HiTrackMetaData) HiJsonUtil.e(list.get(0).getMetaData(), HiTrackMetaData.class);
        return a((int) hiTrackMetaData2.getAvgPace(), hiTrackMetaData2.getTotalDistance() / 1000);
    }

    public static boolean c(List<HiHealthData> list) {
        if (list == null || list.size() <= 0) {
            LogUtil.h("Track_NonPrimalAlgorithm", "getBeforeSportRecoverTime trackData is null.");
            return false;
        }
        Iterator<HiHealthData> it = list.iterator();
        double d2 = 0.0d;
        int i = 0;
        int i2 = 0;
        while (it.hasNext()) {
            try {
                double totalDistance = r6.getTotalDistance() / 1000.0d;
                Map<String, Integer> wearSportData = ((HiTrackMetaData) HiJsonUtil.e(it.next().getMetaData(), HiTrackMetaData.class)).getWearSportData();
                if (wearSportData != null && wearSportData.containsKey("etraining_effect") && wearSportData.get("etraining_effect") != null) {
                    i2 = wearSportData.get("etraining_effect").intValue();
                }
                if (i2 > i) {
                    i = i2;
                }
                if (totalDistance > d2) {
                    d2 = totalDistance;
                }
            } catch (JsonSyntaxException unused) {
                LogUtil.h("Track_NonPrimalAlgorithm", "analyzeTrackSimplifyData trackMetaData is error");
            }
        }
        return i >= 2 || d2 >= 1.0d;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h(int i) {
        long currentTimeMillis = System.currentTimeMillis();
        gzz.b(jdl.d(currentTimeMillis, -6), currentTimeMillis, i, new IBaseResponseCallback() { // from class: gzu.2
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public void d(int i2, Object obj) {
                if (koq.e(obj, HiHealthData.class)) {
                    List list = (List) obj;
                    int size = list.size();
                    if (size > 4) {
                        size = 4;
                    }
                    float f = 0.0f;
                    for (int i3 = 0; i3 < size; i3++) {
                        f += ((HiTrackMetaData) HiJsonUtil.e(((HiHealthData) list.get(i3)).getMetaData(), HiTrackMetaData.class)).getAvgPace();
                    }
                    gzu.this.h.e((f / size) / 60.0f);
                } else {
                    LogUtil.a("Track_NonPrimalAlgorithm", "getLast4TimesRecordAveragePace no have sport record.");
                }
                synchronized (gzu.f13023a) {
                    LogUtil.a("Track_NonPrimalAlgorithm", "getLast4TimesRecordAveragePace notifyAll()");
                    gzu.f13023a.notifyAll();
                }
            }
        });
    }

    private void b(String[] strArr, float f, float f2, int i) {
        Bundle bundle = new Bundle();
        try {
            bundle.putString("voiceType", "sportSmartCoach");
            bundle.putStringArray("beforeSportFirstVoicePath", strArr);
            bundle.putFloat("sectionLower", f);
            bundle.putFloat("sectionUpper", f2);
            bundle.putInt("sportDuration", i);
            hab.b(new gxi(27, bundle));
        } catch (NumberFormatException e2) {
            LogUtil.b("Track_NonPrimalAlgorithm", "voiceConstructorPlay exception = ", LogAnonymous.b((Throwable) e2));
        }
    }

    public void c(int i, String[] strArr) {
        String[] strArr2;
        if (strArr == null || strArr.length <= 0) {
            LogUtil.h("Track_NonPrimalAlgorithm", "beforeSportNonPrimalVoicePlay firstPathList is empty.");
            return;
        }
        LogUtil.a("Track_NonPrimalAlgorithm", "beforeSportNonPrimalVoicePlay", this.i, " ", Boolean.valueOf(this.f));
        int i2 = 0;
        if (this.f) {
            String[] strArr3 = this.i;
            if (strArr3 == null || strArr3.length <= 0) {
                LogUtil.h("Track_NonPrimalAlgorithm", "mIsSuggestedRest mPathIdList is empty.");
                return;
            }
            String[] strArr4 = new String[strArr.length + strArr3.length];
            int length = strArr.length;
            int i3 = 0;
            int i4 = 0;
            while (i3 < length) {
                strArr4[i4] = strArr[i3];
                i3++;
                i4++;
            }
            String[] strArr5 = this.i;
            int length2 = strArr5.length;
            while (i2 < length2) {
                strArr4[i4] = had.d().d(strArr5[i2]);
                i2++;
                i4++;
            }
            hab.b(strArr4);
            return;
        }
        hab.b(strArr);
        e eVar = this.j;
        if (eVar == null || (strArr2 = this.i) == null) {
            LogUtil.h("Track_NonPrimalAlgorithm", "beforeSportNonPrimalVoicePlay mPathIdList is empty.");
            if (this.h.c(7)) {
                a(i);
            } else {
                b(i);
            }
            e eVar2 = this.j;
            if (eVar2 != null) {
                b(this.i, eVar2.c, this.j.e, this.j.b);
            } else {
                LogUtil.a("Track_NonPrimalAlgorithm", "enter defaultVoicePlayBeforeSport");
                g(i);
            }
            a(this.j.e, this.j.c, this.j.b * 60000);
            this.f = false;
            return;
        }
        b(strArr2, eVar.c, this.j.e, this.j.b);
    }

    public void d(int i) {
        c(7, i);
        hac.a().e(this.h.b() >= 24);
        Object obj = b;
        synchronized (obj) {
            try {
                obj.wait(5000L);
            } catch (InterruptedException e2) {
                LogUtil.b("Track_NonPrimalAlgorithm", "query last week sport data", LogAnonymous.b((Throwable) e2));
            }
        }
        if (this.h.c(7)) {
            j(i);
            i(i);
            e(i);
            return;
        }
        b(i);
    }

    private void b(int i) {
        if (this.h.b() >= 25) {
            this.j = new e(this.h.m(), this.h.f(), "fatLoss", false, null);
        } else {
            this.j = new e(this.h.m(), this.h.f(), "recovery", false, null);
        }
        if (i == 264) {
            this.i = new String[]{"L132", "L143"};
        } else {
            this.i = new String[]{"L132", "L134"};
        }
    }

    private void e(int i) {
        Object obj = d;
        synchronized (obj) {
            try {
                obj.wait(5000L);
            } catch (InterruptedException e2) {
                LogUtil.b("Track_NonPrimalAlgorithm", "query today sport data", LogAnonymous.b((Throwable) e2));
            }
        }
        if (!this.c) {
            Object obj2 = e;
            synchronized (obj2) {
                try {
                    obj2.wait(5000L);
                } catch (InterruptedException e3) {
                    LogUtil.b("Track_NonPrimalAlgorithm", "query yesterday sport data", LogAnonymous.b((Throwable) e3));
                }
            }
        }
        gzz gzzVar = this.h;
        gzzVar.e((char) Math.max((int) gzzVar.j(), (int) this.h.h()));
        LogUtil.a("Track_NonPrimalAlgorithm", "dealLastWeekHaveSportData recoverTime ", Character.valueOf(this.h.d()));
        if (this.h.d() == 'D') {
            this.i = new String[]{"L131"};
            this.f = true;
        } else {
            a(i);
        }
    }

    public void a(int i) {
        if (this.h.b() >= 25) {
            c(i);
        } else {
            f(i);
        }
    }

    private void c(int i) {
        this.j = new e(this.h.m(), this.h.f(), "fatLoss", true, String.valueOf(this.h.d()));
        if (this.h.d() == 'A') {
            if (i == 264) {
                this.i = new String[]{"L144", "L143"};
                return;
            } else {
                this.i = new String[]{"L139"};
                return;
            }
        }
        if (this.h.d() == 'B') {
            if (i == 264) {
                this.i = new String[]{"L144", "L143"};
                return;
            } else {
                this.i = new String[]{"L139"};
                return;
            }
        }
        if (this.h.d() != 'C') {
            LogUtil.a("Track_NonPrimalAlgorithm", "dealFatLossHaveSportData wrong branch.");
        } else if (i == 264) {
            this.i = new String[]{"L135", "L143"};
        } else {
            this.i = new String[]{"L135", "L134"};
        }
    }

    private void f(int i) {
        String str;
        if (this.h.d() == 'A' || this.h.d() == 'B') {
            str = "cardio";
        } else {
            if (this.h.d() != 'C') {
                LogUtil.a("Track_NonPrimalAlgorithm", "dealFatLossHaveSportData wrong branch.");
                return;
            }
            str = "recovery";
        }
        this.j = new e(this.h.m(), this.h.f(), str, true, String.valueOf(this.h.d()));
        if (i == 264) {
            this.i = new String[]{"L144", "L143"};
        } else {
            this.i = new String[]{"L139"};
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public float d(float f) {
        int i = (int) (f * 60.0f);
        int i2 = i / 60;
        float f2 = i % 30 >= 15 ? (float) (i2 + 0.5d) : i2;
        if (f2 < 5.0f) {
            f2 = 5.0f;
        }
        if (f2 > 8.5f) {
            return 8.5f;
        }
        return f2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(float f, float f2, long j) {
        synchronized (this) {
            if (!this.g) {
                hac.a().b(f);
                hac.a().d(f2);
                hac.a().a(j);
                this.g = true;
            }
        }
    }

    private void g(int i) {
        e eVar = new e(i);
        this.j = eVar;
        b(this.i, eVar.c, this.j.e, this.j.b);
    }

    public class e {
        private int b;
        private float c;
        private float e;

        private e(int i) {
            if (i == 264) {
                gzu.this.i = new String[]{"L144", "L143"};
                this.e = 8.0f;
                this.c = 5.0f;
                this.b = 30;
                return;
            }
            gzu.this.i = new String[]{"L139"};
            this.e = 12.0f;
            this.c = 6.0f;
            this.b = 30;
        }

        private e(String str, int i, String str2, boolean z, String str3) {
            try {
                if (hab.a() == 264) {
                    this.e = Float.parseFloat(gzv.a(str, i, str2, false));
                    this.c = Float.parseFloat(gzv.a(str, i, str2, true));
                } else {
                    this.e = Float.parseFloat(gzv.d(str, i, str2, z, false));
                    String d = gzv.d(str, i, str2, z, true);
                    if ("-1".equals(d)) {
                        gzu.this.h(hab.a());
                        synchronized (gzu.f13023a) {
                            try {
                                gzu.f13023a.wait(5000L);
                                float g = gzu.this.h.g();
                                this.c = g;
                                this.c = gzu.this.d(g);
                            } catch (InterruptedException e) {
                                LogUtil.b("Track_NonPrimalAlgorithm", "NonPrimalParamData exception = ", LogAnonymous.b((Throwable) e));
                            }
                        }
                    } else {
                        this.c = Float.parseFloat(d);
                    }
                    if (this.c > this.e) {
                        this.c = Float.parseFloat(gzv.d(str, i, str2, !z, true));
                    }
                }
                String e2 = gzv.e(!z, str2, str3);
                int parseInt = Integer.parseInt("".equals(e2) ? gzv.e(!z, "cardio", str3) : e2);
                this.b = parseInt;
                gzu.this.a(this.e, this.c, parseInt * 60000);
            } catch (NumberFormatException e3) {
                LogUtil.b("NonPrimalParamData", LogAnonymous.b((Throwable) e3));
            }
        }
    }
}
