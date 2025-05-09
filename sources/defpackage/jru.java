package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.util.List;

/* loaded from: classes5.dex */
public class jru {
    public static int e(byte[] bArr) throws cwg {
        int[] c = c(b(bArr));
        if (c.length == 0) {
            LogUtil.h("TlvGeneralUtil", "fitnessIs length is 0");
            return -1;
        }
        LogUtil.a("TlvGeneralUtil", "Error Code:", Integer.valueOf(c[0]));
        int i = c[0];
        if (i == 100000) {
            return 0;
        }
        return i;
    }

    public static cwe b(byte[] bArr) throws cwg {
        String d = cvx.d(bArr);
        return new cwl().a((d == null || d.length() <= 4) ? null : d.substring(4, d.length()));
    }

    public static int[] c(cwe cweVar) {
        if (cweVar == null) {
            return new int[]{-1};
        }
        List<cwd> e = cweVar.e();
        int size = e.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            String c = e.get(i).c();
            if (CommonUtil.w(e.get(i).e()) != 127) {
                LogUtil.h("TlvGeneralUtil", "unTlvGetErrorCode default");
            } else if (size > 0) {
                iArr[0] = CommonUtil.w(c);
            }
        }
        return iArr;
    }
}
