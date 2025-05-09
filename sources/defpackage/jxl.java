package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class jxl {
    public static int d(cwe cweVar) {
        if (cweVar == null) {
            return 0;
        }
        List<cwe> a2 = cweVar.a();
        int i = 0;
        for (int i2 = 0; i2 < a2.size(); i2++) {
            List<cwd> e = a2.get(i2).e();
            for (int i3 = 0; i3 < e.size(); i3++) {
                String c = e.get(i3).c();
                if (CommonUtil.w(e.get(i3).e()) == 2) {
                    i = CommonUtil.w(c);
                } else {
                    LogUtil.h("BasicUnTlvStatus", "unTlvGetStatusFrameCount is default");
                }
            }
        }
        return i;
    }

    public static jxy c(jxy jxyVar, String str) {
        if (str == null) {
            LogUtil.h("BasicUnTlvStatus", "parseStatusDuration value is null");
            return jxyVar;
        }
        if (str.length() != 12) {
            LogUtil.h("BasicUnTlvStatus", "parseStatusDuration invalid duration", str);
            return jxyVar;
        }
        String substring = str.substring(0, 8);
        String substring2 = str.substring(8, 12);
        try {
            long parseInt = Integer.parseInt(substring, 16);
            int parseInt2 = Integer.parseInt(substring2, 16);
            jxyVar.e(parseInt);
            jxyVar.a(parseInt2 * 60);
        } catch (NumberFormatException unused) {
            LogUtil.b("BasicUnTlvStatus", "parseStatusDuration NumberFormatException");
        }
        return jxyVar;
    }

    public static jxx a(cwe cweVar) {
        jxx jxxVar = new jxx();
        if (cweVar == null) {
            return jxxVar;
        }
        ArrayList arrayList = new ArrayList(16);
        List<cwe> a2 = cweVar.a();
        for (int i = 0; i < a2.size(); i++) {
            List<cwd> e = a2.get(i).e();
            for (int i2 = 0; i2 < e.size(); i2++) {
                String c = e.get(i2).c();
                try {
                } catch (NumberFormatException unused) {
                    LogUtil.b("BasicUnTlvStatus", "tlv.getTag() NumberFormatException");
                }
                if (Integer.parseInt(e.get(i2).e(), 16) == 3) {
                    jxxVar.d(CommonUtil.w(c));
                }
                LogUtil.h("BasicUnTlvStatus", "subDoMainTag is default");
            }
            b(a2.get(i).a(), arrayList);
        }
        jxxVar.c(arrayList);
        return jxxVar;
    }

    private static void b(List<cwe> list, List<jxy> list2) {
        Iterator<cwe> it = list.iterator();
        while (it.hasNext()) {
            List<cwd> e = it.next().e();
            jxy jxyVar = new jxy();
            for (cwd cwdVar : e) {
                String c = cwdVar.c();
                int w = CommonUtil.w(cwdVar.e());
                if (w == 4) {
                    jxyVar.e(CommonUtil.w(c));
                } else if (w == 5) {
                    jxyVar = c(jxyVar, c);
                } else if (w == 6) {
                    jxyVar.c(CommonUtil.w(c));
                } else {
                    LogUtil.h("BasicUnTlvStatus", "unTlvPackStatus is default");
                }
            }
            LogUtil.a("BasicUnTlvStatus", "get status:", jxyVar);
            list2.add(jxyVar);
        }
    }
}
