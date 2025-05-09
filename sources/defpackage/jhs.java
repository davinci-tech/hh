package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class jhs {
    public static int c(byte[] bArr) throws bmk {
        bmq c;
        if (bArr == null || bArr.length <= 2 || (c = new bmt().c(bArr, 2)) == null) {
            return 0;
        }
        List<bmq> a2 = c.a();
        int i = 0;
        for (int i2 = 0; i2 < a2.size(); i2++) {
            List<bmu> d = a2.get(i2).d();
            for (int i3 = 0; i3 < d.size(); i3++) {
                byte[] c2 = d.get(i3).c();
                if (d.get(i3).a() == 2) {
                    i = cvx.c(c2, -1);
                } else {
                    LogUtil.h("FitnessUnTlvStatus", "unTlvGetStatusFrameCount is default");
                }
            }
        }
        return i;
    }

    public static jih c(jih jihVar, String str) {
        if (str == null) {
            LogUtil.h("FitnessUnTlvStatus", "parseStatusDuration value is null");
            return jihVar;
        }
        if (str.length() != 12) {
            LogUtil.h("FitnessUnTlvStatus", "parseStatusDuration invalid duration", str);
            return jihVar;
        }
        String substring = str.substring(0, 8);
        String substring2 = str.substring(8, 12);
        try {
            long parseInt = Integer.parseInt(substring, 16);
            int parseInt2 = Integer.parseInt(substring2, 16);
            jihVar.c(parseInt);
            jihVar.d(parseInt2 * 60);
        } catch (NumberFormatException unused) {
            LogUtil.b("FitnessUnTlvStatus", "parseStatusDuration NumberFormatException");
        }
        return jihVar;
    }

    public static jif e(byte[] bArr) throws bmk {
        bmq c;
        jif jifVar = new jif();
        if (bArr == null || bArr.length <= 2 || (c = new bmt().c(bArr, 2)) == null) {
            return jifVar;
        }
        ArrayList arrayList = new ArrayList(16);
        List<bmq> a2 = c.a();
        for (int i = 0; i < a2.size(); i++) {
            List<bmu> d = a2.get(i).d();
            for (int i2 = 0; i2 < d.size(); i2++) {
                byte[] c2 = d.get(i2).c();
                if (d.get(i2).a() == 3) {
                    jifVar.b(cvx.c(c2, -1));
                } else {
                    LogUtil.h("FitnessUnTlvStatus", "subDoMainTag is default");
                }
            }
            c(a2.get(i).a(), arrayList);
        }
        jifVar.e(arrayList);
        return jifVar;
    }

    private static void c(List<bmq> list, List<jih> list2) {
        Iterator<bmq> it = list.iterator();
        while (it.hasNext()) {
            List<bmu> d = it.next().d();
            jih jihVar = new jih();
            for (bmu bmuVar : d) {
                byte[] c = bmuVar.c();
                byte a2 = bmuVar.a();
                if (a2 == 4) {
                    jihVar.a(cvx.c(c, -1));
                } else if (a2 == 5) {
                    jihVar = c(jihVar, cvx.d(c));
                } else if (a2 == 6) {
                    jihVar.b(cvx.c(c, -1));
                } else {
                    LogUtil.h("FitnessUnTlvStatus", "unTlvPackStatus is default");
                }
            }
            LogUtil.a("FitnessUnTlvStatus", "get status:", jihVar);
            list2.add(jihVar);
        }
    }
}
