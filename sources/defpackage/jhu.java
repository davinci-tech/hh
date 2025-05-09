package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/* loaded from: classes5.dex */
public class jhu {
    private static int a(int i) {
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
                LogUtil.h("FitnessUnpackFrame", "switchCompressionMotionType default");
                return 255;
        }
    }

    private static int c(byte[] bArr) {
        return jdo.b(jdo.d(bArr[2]));
    }

    private static void d(int i, int i2, int i3, jhx jhxVar) {
        jhxVar.d(i3);
        jhxVar.e(i);
        jhxVar.b(i2);
    }

    private static void a(int i, jia jiaVar) {
        jiaVar.d(i);
    }

    private static void b(List<jhx> list, List<jia> list2, int i, int i2, jig jigVar) {
        if (jigVar == null) {
            LogUtil.h("FitnessUnpackFrame", "frameData is null");
            return;
        }
        int b = jigVar.b();
        int d = jigVar.d();
        int c = jigVar.c();
        int a2 = jigVar.a();
        if (b != 11 && b != 10) {
            jhx jhxVar = new jhx();
            jhxVar.a(i + ((i2 - 1) * 60));
            jhxVar.c(a(b));
            d(d, c, a2, jhxVar);
            list.add(jhxVar);
            return;
        }
        jia jiaVar = new jia();
        jiaVar.e(a(b));
        jiaVar.b(i + ((i2 - 1) * 60));
        a(d, jiaVar);
        list2.add(jiaVar);
    }

    private static boolean a(List<jhx> list, long j) {
        jhx jhxVar = list.get(list.size() - 1);
        if (jhxVar == null) {
            LogUtil.h("FitnessUnpackFrame", "tempSportData is null");
            return false;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd", Locale.ENGLISH);
        return !simpleDateFormat.format(new Date(j)).equals(simpleDateFormat.format(new Date(jhxVar.d() * 1000)));
    }

    private static void b(List<jhx> list, int i, int i2, int i3, jhx jhxVar) {
        jhx jhxVar2 = list.get(list.size() - 1);
        d(i + jhxVar2.e(), i2 + jhxVar2.c(), i3 + jhxVar2.b(), jhxVar);
    }

    private static void c(List<jia> list, int i, jia jiaVar) {
        jiaVar.d(list.get(list.size() - 1).a() + i);
    }

    private static void e(List<jhx> list, List<jia> list2, int i, int i2, jig jigVar) {
        if (jigVar == null) {
            LogUtil.h("FitnessUnpackFrame", "data is null");
            return;
        }
        int b = jigVar.b();
        int d = jigVar.d();
        int c = jigVar.c();
        int a2 = jigVar.a();
        if (b != 11 && b != 10) {
            jhx jhxVar = new jhx();
            long j = i + ((i2 - 1) * 60);
            jhxVar.a(j);
            jhxVar.c(a(b));
            if (list.size() < 1) {
                LogUtil.h("FitnessUnpackFrame", "totalSportDataList size less than 1");
            } else if (a(list, j * 1000)) {
                d(d, c, a2, jhxVar);
            } else {
                b(list, d, c, a2, jhxVar);
            }
            list.add(jhxVar);
            return;
        }
        jia jiaVar = new jia();
        jiaVar.e(a(b));
        jiaVar.b(i + ((i2 - 1) * 60));
        if (list2.size() >= 1) {
            c(list2, d, jiaVar);
        }
        list2.add(jiaVar);
    }

    public static jhv e(cwe cweVar) {
        jhv jhvVar = new jhv();
        List<cwe> a2 = cweVar.a();
        String str = "";
        for (int i = 0; i < a2.size(); i++) {
            List<cwd> e = a2.get(i).e();
            for (int i2 = 0; i2 < e.size(); i2++) {
                if (CommonUtil.w(e.get(i2).e()) == 3) {
                    str = e.get(i2).c();
                } else {
                    LogUtil.h("FitnessUnpackFrame", "unTlvGetFrameData default");
                }
            }
        }
        byte[] a3 = cvx.a(str);
        if (a3.length < 16) {
            return jhvVar;
        }
        int a4 = jdo.a(a3);
        jhvVar.d(a4);
        LogUtil.a("FitnessUnpackFrame", "unGetFrameData(),time:", Integer.valueOf(a4), " date:", new Date(a4 * 1000));
        if (c(a3) == 0) {
            return jhvVar;
        }
        int length = (a3.length - 16) / 16;
        StringBuilder sb = new StringBuilder(16);
        for (int i3 = 16; i3 < a3.length; i3++) {
            sb.append(jdo.d(a3[i3]));
        }
        c(length, sb, a4, jhvVar);
        return jhvVar;
    }

    private static jhv c(int i, StringBuilder sb, int i2, jhv jhvVar) {
        ArrayList arrayList = new ArrayList(16);
        ArrayList arrayList2 = new ArrayList(16);
        for (int i3 = 0; i3 < i; i3++) {
            int i4 = i3 * 128;
            int i5 = i4 + 15;
            int i6 = i4 + 32;
            int i7 = i4 + 56;
            int i8 = i4 + 75;
            b(arrayList, arrayList2, i2, jdo.b(sb.substring(i4, i4 + 7)), new jig(jdo.b(sb.substring(i4 + 8, i5)), jdo.b(sb.substring(i6, i7)), jdo.b(sb.substring(i5, i6)), jdo.b(sb.substring(i7, i8))));
            int i9 = i4 + 90;
            int b = jdo.b(sb.substring(i4 + 83, i9));
            int i10 = i4 + 116;
            int b2 = jdo.b(sb.substring(i10, i4 + 128));
            int i11 = i4 + 101;
            int b3 = jdo.b(sb.substring(i9, i11));
            int b4 = jdo.b(sb.substring(i11, i10));
            int b5 = jdo.b(sb.substring(i8, i4 + 82));
            if (b5 != 0) {
                e(arrayList, arrayList2, i2, b5, new jig(b, b4, b3, b2));
            }
        }
        jhvVar.c(arrayList);
        jhvVar.b(arrayList2);
        return jhvVar;
    }
}
