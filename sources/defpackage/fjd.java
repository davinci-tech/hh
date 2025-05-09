package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginfitnessadvice.AtomicAction;
import com.huawei.pluginfitnessadvice.BaseChoreographyAction;
import com.huawei.pluginfitnessadvice.TargetConfig;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: classes4.dex */
public class fjd {

    /* renamed from: a, reason: collision with root package name */
    private TargetConfig f12539a;
    private List<String> b;
    private int c;
    private String d;
    private AtomicAction e;
    private String f;
    private boolean g;
    private int h;
    private boolean i;
    private long j = -1;
    private TargetConfig k;
    private int l;
    private String n;
    private List<String> o;

    public int i() {
        return this.h;
    }

    public void e(int i) {
        this.h = i;
    }

    public long f() {
        return this.j;
    }

    public void d(long j) {
        this.j = j;
    }

    public boolean l() {
        return this.g;
    }

    public void d(boolean z) {
        this.g = z;
    }

    public boolean n() {
        return this.i;
    }

    public void b(boolean z) {
        this.i = z;
    }

    public int g() {
        return this.l;
    }

    public void b(int i) {
        this.l = i;
    }

    public String j() {
        return this.f;
    }

    public void e(String str) {
        this.f = str;
    }

    public void b(String str) {
        this.n = str;
    }

    public String o() {
        return this.n;
    }

    public List<String> k() {
        return this.o;
    }

    public String b() {
        return this.d;
    }

    public List<String> a() {
        return this.b;
    }

    public AtomicAction d() {
        return this.e;
    }

    public void b(AtomicAction atomicAction) {
        this.e = atomicAction;
    }

    public TargetConfig h() {
        return this.k;
    }

    private void a(TargetConfig targetConfig) {
        TargetConfig targetConfig2 = new TargetConfig();
        this.k = targetConfig2;
        targetConfig2.setId(targetConfig.getId());
        this.k.setName(targetConfig.getName());
        this.k.setValueH(targetConfig.getValueH());
        this.k.setValueL(targetConfig.getValueL());
        this.k.setValueType(targetConfig.getValueType());
    }

    public TargetConfig e() {
        return this.f12539a;
    }

    private void c(TargetConfig targetConfig) {
        TargetConfig targetConfig2 = new TargetConfig();
        this.f12539a = targetConfig2;
        targetConfig2.setId(targetConfig.getId());
        this.f12539a.setName(targetConfig.getName());
        this.f12539a.setValueType(targetConfig.getValueType());
        this.f12539a.setValueL(targetConfig.getValueL());
        this.f12539a.setValueH(targetConfig.getValueH());
    }

    public void e(Context context, TargetConfig targetConfig, fhx fhxVar) {
        if (context == null) {
            LogUtil.h("Suggestion_CustomCourseActionItemConfig", "setTargetConfigInfo, context is null");
            return;
        }
        List<String> list = this.o;
        if (list == null) {
            this.o = new ArrayList();
        } else {
            list.clear();
        }
        if (targetConfig == null || TextUtils.isEmpty(targetConfig.getId())) {
            LogUtil.h("Suggestion_CustomCourseActionItemConfig", "setIntensityConfigInfo, targetConfig is null or targetConfig.getId is empty");
            b(context);
            return;
        }
        a(targetConfig);
        int m = CommonUtil.m(context, targetConfig.getId());
        if (m == 1) {
            this.n = context.getResources().getString(R.string._2130848438_res_0x7f022ab6);
            b(context, targetConfig.getValueL());
            return;
        }
        if (m == 0) {
            this.n = context.getResources().getString(R.string._2130848437_res_0x7f022ab5);
            d(context, targetConfig.getValueL());
        } else if (m == 3) {
            this.n = context.getResources().getString(R.string._2130848731_res_0x7f022bdb);
            e(context, targetConfig.getValueL());
        } else if (m == 4 || m == 5) {
            this.n = context.getResources().getString(R.string._2130841430_res_0x7f020f56);
            b(context, fhxVar, m == 4, targetConfig);
        } else {
            b(context);
        }
    }

    private void b(Context context, fhx fhxVar, boolean z, TargetConfig targetConfig) {
        if (fhxVar == null) {
            this.o.add(context.getResources().getString(R.string._2130848598_res_0x7f022b56));
        } else {
            e(context, fhxVar, z, targetConfig.getValueL(), targetConfig.getValueH());
        }
    }

    private void b(Context context) {
        this.n = context.getResources().getString(R.string._2130848617_res_0x7f022b69);
        this.o.add(context.getResources().getString(R.string._2130848598_res_0x7f022b56));
    }

    private void e(Context context, List<String> list) {
        if (list.size() == 0) {
            list.add(context.getResources().getString(R.string._2130848598_res_0x7f022b56));
        }
    }

    private void b(Context context, double d) {
        if (d > 0.0d) {
            long hours = TimeUnit.SECONDS.toHours((long) d);
            if (hours > 0) {
                this.o.add(context.getResources().getQuantityString(R.plurals._2130903133_res_0x7f03005d, (int) hours, Long.valueOf(hours)));
            }
            long minutes = TimeUnit.SECONDS.toMinutes((long) (d % 3600.0d));
            if (minutes > 0) {
                this.o.add(context.getResources().getQuantityString(R.plurals._2130903289_res_0x7f0300f9, (int) minutes, Long.valueOf(minutes)));
            }
            long j = (long) ((d - (hours * 3600)) - (minutes * 60));
            if (j > 0) {
                this.o.add(context.getResources().getQuantityString(R.plurals._2130903475_res_0x7f0301b3, (int) j, UnitUtil.e(j, 1, 0)));
            }
        }
        e(context, this.o);
    }

    private void d(Context context, double d) {
        if (d > 0.0d) {
            if (UnitUtil.h()) {
                double e = UnitUtil.e(d, 2) / 1760.0d;
                if (e > 0.0d) {
                    this.o.add(context.getResources().getQuantityString(R.plurals._2130903240_res_0x7f0300c8, (int) e, UnitUtil.e(e, 1, 2)));
                }
            } else {
                double d2 = d / 1000.0d;
                if (d2 > 0.0d) {
                    this.o.add(context.getResources().getQuantityString(R.plurals._2130903239_res_0x7f0300c7, (int) d2, UnitUtil.e(d2, 1, 2)));
                }
            }
        }
        e(context, this.o);
    }

    private void e(Context context, double d) {
        if (d >= 1.0d) {
            this.o.add(context.getResources().getQuantityString(R.plurals._2130903474_res_0x7f0301b2, (int) d, UnitUtil.e(d, 1, 0)));
        }
        e(context, this.o);
    }

    private void e(Context context, fhx fhxVar, boolean z, double d, double d2) {
        if ((d > 0.0d || d2 > 0.0d) && fhxVar != null) {
            LogUtil.a("Suggestion_CustomCourseActionItemConfig", "getTargetHeartRateValue, mHeartRateZoneConfig.getMaxThreshold()=" + fhxVar.e());
            if (z) {
                int a2 = (int) UnitUtil.a((fhxVar.e() * d) / 100.0d, 0);
                this.o.add(context.getResources().getQuantityString(R.plurals._2130903322_res_0x7f03011a, a2, Integer.valueOf(a2)));
            } else {
                int a3 = (int) UnitUtil.a((fhxVar.e() * d2) / 100.0d, 0);
                this.o.add(context.getResources().getQuantityString(R.plurals._2130903323_res_0x7f03011b, a3, Integer.valueOf(a3)));
            }
        }
        e(context, this.o);
    }

    public void c(Context context, TargetConfig targetConfig, fhx fhxVar, fhx fhxVar2, int i, ffg ffgVar) {
        if (context == null) {
            LogUtil.h("Suggestion_CustomCourseActionItemConfig", "setIntensityConfigInfo, context is null");
            return;
        }
        List<String> list = this.b;
        if (list == null) {
            this.b = new ArrayList();
        } else {
            list.clear();
        }
        if (targetConfig == null || TextUtils.isEmpty(targetConfig.getId())) {
            LogUtil.h("Suggestion_CustomCourseActionItemConfig", "setIntensityConfigInfo, intensityConfig is null or intensityConfig.getId is empty");
            a(context);
        } else {
            c(targetConfig);
            b(CommonUtil.m(context, targetConfig.getId()), context, targetConfig, fhxVar, fhxVar2, i, ffgVar);
        }
    }

    private void b(int i, Context context, TargetConfig targetConfig, fhx fhxVar, fhx fhxVar2, int i2, ffg ffgVar) {
        if (i == 14) {
            if (ggx.e(context, gij.b())) {
                this.d = context.getResources().getString(R.string._2130848714_res_0x7f022bca);
                e(context, false, fhxVar, fhxVar2, targetConfig.getValueL());
                return;
            } else {
                this.d = context.getResources().getString(R.string._2130848703_res_0x7f022bbf);
                e(context, (int) targetConfig.getValueL(), ffgVar);
                return;
            }
        }
        if (BaseChoreographyAction.c.b(i)) {
            this.d = context.getResources().getString(R.string._2130848714_res_0x7f022bca);
            e(context, i != 17 ? i == 4 : i2 == 0, fhxVar, fhxVar2, targetConfig.getValueL());
            return;
        }
        if (i == 13) {
            this.d = context.getResources().getString(R.string._2130848703_res_0x7f022bbf);
            e(context, (int) targetConfig.getValueL(), ffgVar);
            return;
        }
        if (i == 15) {
            this.d = context.getResources().getString(R.string._2130848717_res_0x7f022bcd);
            a(context, targetConfig.getValueL(), targetConfig.getValueH());
        } else if (i == 16) {
            this.d = context.getResources().getString(R.string._2130848716_res_0x7f022bcc);
            d(context, targetConfig.getValueL(), targetConfig.getValueH());
        } else if (i == 1) {
            this.d = context.getResources().getString(R.string._2130848715_res_0x7f022bcb);
            a(context, fhxVar, targetConfig);
        } else {
            LogUtil.h("Suggestion_CustomCourseActionItemConfig", "setIntensityConfigInfo, intensityConfigId = ", Integer.valueOf(i));
            a(context);
        }
    }

    private void a(Context context) {
        this.d = context.getResources().getString(R.string._2130848599_res_0x7f022b57);
        this.b.add(context.getResources().getString(R.string._2130848598_res_0x7f022b56));
    }

    private void e(Context context, boolean z, fhx fhxVar, fhx fhxVar2, double d) {
        if (fhxVar == null && fhxVar2 == null) {
            this.b.add(context.getResources().getString(R.string._2130848598_res_0x7f022b56));
        } else {
            d(context, z, fhxVar, fhxVar2, d);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:9:0x005b  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void d(android.content.Context r6, boolean r7, defpackage.fhx r8, defpackage.fhx r9, double r10) {
        /*
            r5 = this;
            r0 = 0
            int r0 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r0 < 0) goto Lbf
            java.lang.String r0 = " highHeartRate:"
            java.lang.String r1 = "lowHeartRate:"
            java.lang.String r2 = "Suggestion_CustomCourseActionItemConfig"
            r3 = 1
            r4 = 0
            if (r7 == 0) goto L2c
            if (r8 == 0) goto L47
            int r7 = (int) r10
            int r9 = r8.a(r7, r3)
            int r4 = r8.a(r7, r4)
            java.lang.Integer r7 = java.lang.Integer.valueOf(r9)
            java.lang.Integer r8 = java.lang.Integer.valueOf(r4)
            java.lang.Object[] r7 = new java.lang.Object[]{r1, r7, r0, r8}
            com.huawei.hwlogsmodel.LogUtil.a(r2, r7)
            r8 = r9
            goto L48
        L2c:
            if (r9 == 0) goto L47
            int r7 = (int) r10
            int r8 = r9.a(r7, r3)
            int r4 = r9.a(r7, r4)
            java.lang.Integer r7 = java.lang.Integer.valueOf(r8)
            java.lang.Integer r9 = java.lang.Integer.valueOf(r4)
            java.lang.Object[] r7 = new java.lang.Object[]{r1, r7, r0, r9}
            com.huawei.hwlogsmodel.LogUtil.a(r2, r7)
            goto L48
        L47:
            r8 = r4
        L48:
            java.lang.String r7 = "setIntensityRelativeHeartRateZoneValue, valueL = "
            java.lang.Double r9 = java.lang.Double.valueOf(r10)
            java.lang.Object[] r7 = new java.lang.Object[]{r7, r9}
            com.huawei.hwlogsmodel.LogUtil.a(r2, r7)
            r0 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r7 = (r10 > r0 ? 1 : (r10 == r0 ? 0 : -1))
            if (r7 < 0) goto L5c
            double r10 = r10 - r0
        L5c:
            int r7 = (int) r10
            android.content.res.Resources r9 = r6.getResources()
            int r7 = r7 + r3
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            java.lang.Object[] r7 = new java.lang.Object[]{r7}
            r10 = 2130842942(0x7f02153e, float:1.7290994E38)
            java.lang.String r7 = r9.getString(r10, r7)
            android.content.res.Resources r9 = r6.getResources()
            java.lang.String r10 = " "
            java.lang.Object[] r7 = new java.lang.Object[]{r7, r10}
            r10 = 2130843702(0x7f021836, float:1.7292535E38)
            java.lang.String r7 = r9.getString(r10, r7)
            java.util.List<java.lang.String> r9 = r5.b
            r9.add(r7)
            r7 = 2130903483(0x7f0301bb, float:1.7413785E38)
            if (r8 >= r4) goto La6
            java.util.List<java.lang.String> r9 = r5.b
            android.content.res.Resources r10 = r6.getResources()
            java.lang.Integer r8 = java.lang.Integer.valueOf(r8)
            java.lang.Integer r11 = java.lang.Integer.valueOf(r4)
            java.lang.Object[] r8 = new java.lang.Object[]{r8, r11}
            java.lang.String r7 = r10.getQuantityString(r7, r4, r8)
            r9.add(r7)
            goto Lbf
        La6:
            java.util.List<java.lang.String> r9 = r5.b
            android.content.res.Resources r10 = r6.getResources()
            java.lang.Integer r11 = java.lang.Integer.valueOf(r4)
            java.lang.Integer r0 = java.lang.Integer.valueOf(r8)
            java.lang.Object[] r11 = new java.lang.Object[]{r11, r0}
            java.lang.String r7 = r10.getQuantityString(r7, r8, r11)
            r9.add(r7)
        Lbf:
            java.util.List<java.lang.String> r7 = r5.b
            r5.e(r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.fjd.d(android.content.Context, boolean, fhx, fhx, double):void");
    }

    private void e(Context context, int i, ffg ffgVar) {
        if (i > 0) {
            String string = context.getResources().getString(R.string._2130843969_res_0x7f021941, Integer.valueOf(i));
            if (ffgVar == null) {
                e(context, this.b);
                return;
            }
            int b = ffgVar.b(i, true);
            int b2 = ffgVar.b(i, false);
            LogUtil.a("Suggestion_CustomCourseActionItemConfig", "low:", Integer.valueOf(b), " high:", Integer.valueOf(b2));
            String a2 = a(context, b);
            String a3 = a(context, b2);
            LogUtil.a("Suggestion_CustomCourseActionItemConfig", "getIntensityPaceZoneValue, paceSectionSelected = ", ffgVar, ", lowPace = ", a2, ", highPace = ", a3);
            this.b.add(context.getResources().getString(R.string._2130843702_res_0x7f021836, string, " "));
            if (b > b2) {
                this.b.add(c(context, b, a3, a2));
            } else {
                this.b.add(c(context, b2, a2, a3));
            }
        }
        e(context, this.b);
    }

    private String c(Context context, int i, String str, String str2) {
        String string = context.getResources().getString(R.string._2130845600_res_0x7f021fa0, str, str2);
        if (UnitUtil.h()) {
            return context.getResources().getQuantityString(R.plurals._2130903281_res_0x7f0300f1, i, string);
        }
        return context.getResources().getQuantityString(R.plurals._2130903280_res_0x7f0300f0, i, string);
    }

    public static String a(Context context, double d) {
        if (UnitUtil.h()) {
            d = UnitUtil.d(d, 3);
        }
        int round = (int) Math.round(d);
        int i = round / 60;
        if (d <= 0.0010000000474974513d) {
            return context.getResources().getString(R.string._2130849885_res_0x7f02305d);
        }
        if (LanguageUtil.r(context)) {
            return UnitUtil.d(round);
        }
        String string = context.getResources().getString(R.string._2130847575_res_0x7f022757, UnitUtil.e(i, 1, 0), Integer.valueOf(round % 60));
        return LanguageUtil.b(context) ? string.substring(0, string.length() - 2).replace("'", ":") : string;
    }

    private void a(Context context, double d, double d2) {
        if (d > 0.0d && d2 > 0.0d) {
            double d3 = d / 1000.0d;
            if (UnitUtil.h()) {
                d3 = UnitUtil.d(d3, 3);
            }
            long minutes = TimeUnit.SECONDS.toMinutes((long) d3);
            long round = Math.round(d3 - (minutes * 60));
            double d4 = d2 / 1000.0d;
            if (UnitUtil.h()) {
                d4 = UnitUtil.d(d4, 3);
            }
            this.b.add(c(context, 1, context.getResources().getString(R.string._2130847575_res_0x7f022757, UnitUtil.e(minutes, 1, 0), Long.valueOf(round)), context.getResources().getString(R.string._2130847575_res_0x7f022757, UnitUtil.e(TimeUnit.SECONDS.toMinutes((long) d4), 1, 0), Long.valueOf(Math.round(d4 - (60 * r0))))));
        }
        e(context, this.b);
    }

    private void d(Context context, double d, double d2) {
        if (d > 0.0d && d2 > 0.0d) {
            long j = (long) d;
            this.b.add(context.getResources().getQuantityString(R.plurals._2130903316_res_0x7f030114, (int) j, Long.valueOf(j), Long.valueOf((long) d2)));
        }
        e(context, this.b);
    }

    private void a(Context context, fhx fhxVar, TargetConfig targetConfig) {
        if (fhxVar == null) {
            this.b.add(context.getResources().getString(R.string._2130848598_res_0x7f022b56));
        } else {
            e(context, fhxVar, targetConfig.getValueL(), targetConfig.getValueH());
        }
    }

    private void e(Context context, fhx fhxVar, double d, double d2) {
        if (d > 0.0d && d2 > 0.0d && fhxVar != null) {
            int a2 = (int) UnitUtil.a((fhxVar.e() * d) / 100.0d, 0);
            this.b.add(context.getResources().getQuantityString(R.plurals._2130903483_res_0x7f0301bb, a2, Integer.valueOf(a2), Integer.valueOf((int) UnitUtil.a((fhxVar.e() * d2) / 100.0d, 0))));
        }
        e(context, this.b);
    }

    public int c() {
        return this.c;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void a(String str) {
        char c;
        if (TextUtils.isEmpty(str)) {
            this.c = R.drawable._2131429899_res_0x7f0b0a0b;
            return;
        }
        LogUtil.a("Suggestion_CustomCourseActionItemConfig", "setColumnIcon, actionId = ", str);
        str.hashCode();
        switch (str.hashCode()) {
            case 77802175:
                if (str.equals("RD001")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 77802176:
            case 77802177:
            default:
                c = 65535;
                break;
            case 77802178:
                if (str.equals("RD004")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 77802179:
                if (str.equals("RD005")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
        }
        if (c == 0) {
            this.c = R.drawable._2131429900_res_0x7f0b0a0c;
        } else if (c == 1 || c == 2) {
            this.c = R.drawable._2131429898_res_0x7f0b0a0a;
        } else {
            this.c = R.drawable._2131429899_res_0x7f0b0a0b;
        }
    }
}
