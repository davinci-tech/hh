package defpackage;

import com.huawei.health.servicesui.R$string;

/* loaded from: classes3.dex */
public class ehy {
    public static String d(int i) {
        if (i == 0) {
            return arx.b().getResources().getString(R$string.IDS_COURSE_DIFFICULTY_L2);
        }
        if (i == 1) {
            return arx.b().getResources().getString(R$string.IDS_COURSE_DIFFICULTY_L3);
        }
        if (i != 2) {
            return i != 6 ? "--" : arx.b().getResources().getString(R$string.IDS_COURSE_DIFFICULTY_L1);
        }
        return arx.b().getResources().getString(R$string.IDS_COURSE_DIFFICULTY_L4);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(14:0|1|(2:5|(11:7|8|(1:12)|13|14|15|(2:(1:20)|21)|22|(2:(1:27)(2:30|(1:32))|28)|33|34))|38|8|(2:10|12)|13|14|15|(3:17|(0)|21)|22|(3:24|(0)(0)|28)|33|34) */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00ae, code lost:
    
        r9 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x00af, code lost:
    
        com.huawei.hwlogsmodel.LogUtil.b("CourseUtil", "MissingFormatArgumentException when format courseInfo. exception = ", r9.getMessage());
     */
    /* JADX WARN: Removed duplicated region for block: B:20:0x005c A[Catch: MissingFormatArgumentException -> 0x00ae, TryCatch #0 {MissingFormatArgumentException -> 0x00ae, blocks: (B:14:0x0041, B:17:0x0054, B:20:0x005c, B:21:0x005f, B:22:0x0071, B:24:0x0077, B:27:0x007f, B:28:0x0092, B:30:0x0083, B:32:0x008d), top: B:13:0x0041 }] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x007f A[Catch: MissingFormatArgumentException -> 0x00ae, TryCatch #0 {MissingFormatArgumentException -> 0x00ae, blocks: (B:14:0x0041, B:17:0x0054, B:20:0x005c, B:21:0x005f, B:22:0x0071, B:24:0x0077, B:27:0x007f, B:28:0x0092, B:30:0x0083, B:32:0x008d), top: B:13:0x0041 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0083 A[Catch: MissingFormatArgumentException -> 0x00ae, TryCatch #0 {MissingFormatArgumentException -> 0x00ae, blocks: (B:14:0x0041, B:17:0x0054, B:20:0x005c, B:21:0x005f, B:22:0x0071, B:24:0x0077, B:27:0x007f, B:28:0x0092, B:30:0x0083, B:32:0x008d), top: B:13:0x0041 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static java.lang.String e(com.huawei.health.marketing.datatype.SingleGridContent r9, int r10) {
        /*
            java.lang.String r0 = r9.getExtend()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            r3 = 1
            r4 = 0
            if (r2 != 0) goto L32
            java.lang.String r2 = ","
            boolean r5 = r0.contains(r2)
            if (r5 == 0) goto L32
            java.lang.String[] r0 = r0.split(r2)
            int r2 = r0.length
            r5 = 2
            if (r2 != r5) goto L32
            r2 = r0[r4]
            android.content.Context r5 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            r0 = r0[r3]
            int r0 = health.compact.a.CommonUtil.m(r5, r0)
            java.lang.String r0 = d(r0)
            goto L34
        L32:
            r2 = 0
            r0 = r2
        L34:
            r5 = 19
            if (r10 != r5) goto L41
            boolean r6 = android.text.TextUtils.isEmpty(r0)
            if (r6 != 0) goto L41
            r1.append(r0)
        L41:
            int r0 = r9.getCostTime()     // Catch: java.util.MissingFormatArgumentException -> Lae
            float r0 = (float) r0     // Catch: java.util.MissingFormatArgumentException -> Lae
            java.lang.String r0 = defpackage.moe.k(r0)     // Catch: java.util.MissingFormatArgumentException -> Lae
            boolean r6 = android.text.TextUtils.isEmpty(r0)     // Catch: java.util.MissingFormatArgumentException -> Lae
            java.lang.String r7 = "    "
            java.lang.String r8 = "0"
            if (r6 != 0) goto L71
            boolean r6 = android.text.TextUtils.equals(r0, r8)     // Catch: java.util.MissingFormatArgumentException -> Lae
            if (r6 != 0) goto L71
            if (r10 != r5) goto L5f
            r1.append(r7)     // Catch: java.util.MissingFormatArgumentException -> Lae
        L5f:
            int r9 = r9.getCostTime()     // Catch: java.util.MissingFormatArgumentException -> Lae
            java.lang.Object[] r0 = new java.lang.Object[]{r0}     // Catch: java.util.MissingFormatArgumentException -> Lae
            r6 = 2130903305(0x7f030109, float:1.7413424E38)
            java.lang.String r9 = defpackage.ffy.b(r6, r9, r0)     // Catch: java.util.MissingFormatArgumentException -> Lae
            r1.append(r9)     // Catch: java.util.MissingFormatArgumentException -> Lae
        L71:
            boolean r9 = android.text.TextUtils.isEmpty(r2)     // Catch: java.util.MissingFormatArgumentException -> Lae
            if (r9 != 0) goto Lbe
            boolean r9 = android.text.TextUtils.equals(r2, r8)     // Catch: java.util.MissingFormatArgumentException -> Lae
            if (r9 != 0) goto Lbe
            if (r10 != r5) goto L83
            r1.append(r7)     // Catch: java.util.MissingFormatArgumentException -> Lae
            goto L92
        L83:
            java.lang.String r9 = r1.toString()     // Catch: java.util.MissingFormatArgumentException -> Lae
            boolean r9 = android.text.TextUtils.isEmpty(r9)     // Catch: java.util.MissingFormatArgumentException -> Lae
            if (r9 != 0) goto L92
            java.lang.String r9 = "  "
            r1.append(r9)     // Catch: java.util.MissingFormatArgumentException -> Lae
        L92:
            android.content.Context r9 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()     // Catch: java.util.MissingFormatArgumentException -> Lae
            int r9 = health.compact.a.CommonUtil.m(r9, r2)     // Catch: java.util.MissingFormatArgumentException -> Lae
            java.lang.Object[] r10 = new java.lang.Object[r3]     // Catch: java.util.MissingFormatArgumentException -> Lae
            double r5 = (double) r9     // Catch: java.util.MissingFormatArgumentException -> Lae
            java.lang.String r0 = health.compact.a.UnitUtil.e(r5, r3, r4)     // Catch: java.util.MissingFormatArgumentException -> Lae
            r10[r4] = r0     // Catch: java.util.MissingFormatArgumentException -> Lae
            r0 = 2130903380(0x7f030154, float:1.7413576E38)
            java.lang.String r9 = defpackage.ffy.b(r0, r9, r10)     // Catch: java.util.MissingFormatArgumentException -> Lae
            r1.append(r9)     // Catch: java.util.MissingFormatArgumentException -> Lae
            goto Lbe
        Lae:
            r9 = move-exception
            java.lang.String r10 = "MissingFormatArgumentException when format courseInfo. exception = "
            java.lang.String r9 = r9.getMessage()
            java.lang.Object[] r9 = new java.lang.Object[]{r10, r9}
            java.lang.String r10 = "CourseUtil"
            com.huawei.hwlogsmodel.LogUtil.b(r10, r9)
        Lbe:
            java.lang.String r9 = r1.toString()
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.ehy.e(com.huawei.health.marketing.datatype.SingleGridContent, int):java.lang.String");
    }
}
