package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* loaded from: classes5.dex */
public class jxm {
    private static int c(int i) {
        switch (i) {
            case 1:
            case 2:
            case 3:
                return 0;
            case 4:
            case 5:
                return 1;
            case 6:
            case 7:
                return 2;
            case 8:
                return 3;
            case 9:
                return 4;
            case 10:
                return 6;
            case 11:
                return 7;
            default:
                LogUtil.h("BasicUnpackFrame", "switchCompressionMotionType default");
                return 255;
        }
    }

    private static int c(byte[] bArr) {
        return jdo.b(jdo.d(bArr[2]));
    }

    private static void d(int i, int i2, int i3, jxu jxuVar) {
        jxuVar.e(i3);
        jxuVar.b(i);
        jxuVar.c(i2);
    }

    private static void b(int i, jxn jxnVar) {
        jxnVar.e(i);
    }

    private static void e(List<jxu> list, List<jxn> list2, int i, int i2, jxz jxzVar) {
        if (jxzVar == null) {
            LogUtil.h("BasicUnpackFrame", "frameData is null");
            return;
        }
        int d = jxzVar.d();
        int e = jxzVar.e();
        int b = jxzVar.b();
        int a2 = jxzVar.a();
        if (d != 11 && d != 10) {
            jxu jxuVar = new jxu();
            jxuVar.e(i + ((i2 - 1) * 60));
            jxuVar.d(c(d));
            d(e, b, a2, jxuVar);
            list.add(jxuVar);
            return;
        }
        jxn jxnVar = new jxn();
        jxnVar.c(c(d));
        jxnVar.e(i + ((i2 - 1) * 60));
        b(e, jxnVar);
        list2.add(jxnVar);
    }

    private static boolean c(List<jxu> list, long j) {
        jxu jxuVar = list.get(list.size() - 1);
        if (jxuVar == null) {
            LogUtil.h("BasicUnpackFrame", "tempSportData is null");
            return false;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd", Locale.ENGLISH);
        return !simpleDateFormat.format(new Date(j)).equals(simpleDateFormat.format(new Date(jxuVar.d() * 1000)));
    }

    private static void a(List<jxu> list, int i, int i2, int i3, jxu jxuVar) {
        jxu jxuVar2 = list.get(list.size() - 1);
        d(i + jxuVar2.c(), i2 + jxuVar2.b(), i3 + jxuVar2.e(), jxuVar);
    }

    private static void d(List<jxn> list, int i, jxn jxnVar) {
        jxnVar.e(list.get(list.size() - 1).d() + i);
    }

    private static void d(List<jxu> list, List<jxn> list2, int i, int i2, jxz jxzVar) {
        if (jxzVar == null) {
            LogUtil.h("BasicUnpackFrame", "data is null");
            return;
        }
        int d = jxzVar.d();
        int e = jxzVar.e();
        int b = jxzVar.b();
        int a2 = jxzVar.a();
        if (d != 11 && d != 10) {
            jxu jxuVar = new jxu();
            long j = i + ((i2 - 1) * 60);
            jxuVar.e(j);
            jxuVar.d(c(d));
            if (list.size() < 1) {
                LogUtil.h("BasicUnpackFrame", "totalSportDataList size less than 1");
            } else if (c(list, j * 1000)) {
                d(e, b, a2, jxuVar);
            } else {
                a(list, e, b, a2, jxuVar);
            }
            list.add(jxuVar);
            return;
        }
        jxn jxnVar = new jxn();
        jxnVar.c(c(d));
        jxnVar.e(i + ((i2 - 1) * 60));
        if (list2.size() >= 1) {
            d(list2, e, jxnVar);
        }
        list2.add(jxnVar);
    }

    public static jxo c(cwe cweVar) {
        List<cwe> a2 = cweVar.a();
        String str = "";
        for (int i = 0; i < a2.size(); i++) {
            List<cwd> e = a2.get(i).e();
            for (int i2 = 0; i2 < e.size(); i2++) {
                if (CommonUtil.w(e.get(i2).e()) == 3) {
                    str = e.get(i2).c();
                } else {
                    LogUtil.h("BasicUnpackFrame", "unTlvGetFrameData default");
                }
            }
        }
        byte[] a3 = cvx.a(str);
        jxo jxoVar = new jxo();
        if (a3.length < 16) {
            return jxoVar;
        }
        int a4 = jdo.a(a3);
        jxoVar.c(a4);
        LogUtil.a("BasicUnpackFrame", "unGetFrameData(),time:", Integer.valueOf(a4), " date:", new Date(a4 * 1000));
        if (c(a3) == 0) {
            return jxoVar;
        }
        int length = (a3.length - 16) / 16;
        StringBuilder sb = new StringBuilder(16);
        for (int i3 = 16; i3 < a3.length; i3++) {
            sb.append(jdo.d(a3[i3]));
        }
        a(length, sb, a4, jxoVar);
        return jxoVar;
    }

    private static jxo a(int i, StringBuilder sb, int i2, jxo jxoVar) {
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(16);
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = i3 * 128;
            int i5 = i4 + 15;
            int i6 = i4 + 32;
            int i7 = i4 + 56;
            int i8 = i4 + 75;
            e(arrayList, arrayList2, i2, jdo.b(sb.substring(i4, i4 + 7)), new jxz(jdo.b(sb.substring(i4 + 8, i5)), jdo.b(sb.substring(i6, i7)), jdo.b(sb.substring(i5, i6)), jdo.b(sb.substring(i7, i8))));
            int i9 = i4 + 90;
            int b = jdo.b(sb.substring(i4 + 83, i9));
            int i10 = i4 + 116;
            int b2 = jdo.b(sb.substring(i10, i4 + 128));
            int i11 = i4 + 101;
            int b3 = jdo.b(sb.substring(i9, i11));
            int b4 = jdo.b(sb.substring(i11, i10));
            int b5 = jdo.b(sb.substring(i8, i4 + 82));
            if (b5 != 0) {
                d(arrayList, arrayList2, i2, b5, new jxz(b, b4, b3, b2));
            }
        }
        jxoVar.b(arrayList);
        jxoVar.e(arrayList2);
        return jxoVar;
    }
}
