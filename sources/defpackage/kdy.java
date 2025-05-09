package defpackage;

import com.huawei.haf.common.exception.ExceptionUtils;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class kdy {
    public static kdp e(byte[] bArr) {
        kdp kdpVar = new kdp();
        if (bArr == null) {
            return kdpVar;
        }
        String d = cvx.d(bArr);
        ArrayList arrayList = new ArrayList(16);
        if (d.length() > 4) {
            try {
                c(d.substring(4, d.length()), kdpVar, arrayList);
                kdpVar.d(arrayList);
            } catch (cwg e) {
                ReleaseLogUtil.c("BTSYNC_StressMgrUtil", "getRelaxRecordDetails tlvException:", ExceptionUtils.d(e));
            }
        }
        return kdpVar;
    }

    private static void c(String str, kdp kdpVar, List<kds> list) throws cwg {
        List<cwe> a2 = new cwl().a(str).a();
        if (a2 == null || a2.isEmpty()) {
            return;
        }
        for (cwe cweVar : a2) {
            a(kdpVar, cweVar.e());
            d(list, cweVar.a());
        }
    }

    private static void d(List<kds> list, List<cwe> list2) throws cwg {
        Iterator<cwe> it = list2.iterator();
        while (it.hasNext()) {
            List<cwd> e = it.next().e();
            kds kdsVar = new kds();
            for (cwd cwdVar : e) {
                int w = CommonUtil.w(cwdVar.e());
                if (w == 4) {
                    kdsVar.d(CommonUtil.w(cwdVar.c()));
                } else if (w == 5) {
                    kdsVar.b(CommonUtil.w(cwdVar.c()));
                } else if (w == 6) {
                    kdsVar.e(CommonUtil.w(cwdVar.c()));
                } else if (w == 7) {
                    kdsVar.a(CommonUtil.w(cwdVar.c()));
                } else {
                    ReleaseLogUtil.d("BTSYNC_StressMgrUtil", "buildTlvChild child enter default branch");
                }
            }
            list.add(kdsVar);
        }
    }

    private static void a(kdp kdpVar, List<cwd> list) throws cwg {
        for (cwd cwdVar : list) {
            if (CommonUtil.w(cwdVar.e()) == 2) {
                kdpVar.b(CommonUtil.w(cwdVar.c()));
            } else {
                ReleaseLogUtil.d("BTSYNC_StressMgrUtil", "buildTlvList father enter default branch");
            }
        }
    }
}
