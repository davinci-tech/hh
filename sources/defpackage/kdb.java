package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;

/* loaded from: classes5.dex */
public class kdb {
    public static int a(byte[] bArr) throws cwg {
        String d = cvx.d(bArr);
        if (d == null) {
            LogUtil.h("RriServiceUnPackage", "getErrorCode: fitnessInfo is null");
            return -1;
        }
        try {
            int[] c = jru.c(new cwl().a(d.substring(4, d.length())));
            if (c != null && c.length > 0) {
                LogUtil.a("RriServiceUnPackage", "getErrorCode: Error Code: ", Integer.valueOf(c[0]));
                int i = c[0];
                if (i == 100000) {
                    return 0;
                }
                return i;
            }
            LogUtil.h("RriServiceUnPackage", "getErrorCode: Error Code incorrect, fitnessErrorCodes length error");
            return -1;
        } catch (StringIndexOutOfBoundsException unused) {
            LogUtil.b("RriServiceUnPackage", "getErrorCode: StringIndexOutOfBoundsException");
            return -1;
        }
    }

    public static int c(byte[] bArr) throws cwg {
        String d = cvx.d(bArr);
        if (d == null) {
            LogUtil.h("RriServiceUnPackage", "unGetEcgFrameCount: hexResultInfo is null");
            return 0;
        }
        try {
            return d(new cwl().a(d.substring(4, d.length())));
        } catch (StringIndexOutOfBoundsException unused) {
            LogUtil.b("RriServiceUnPackage", "unGetEcgFrameCount: StringIndexOutOfBoundsException");
            return 0;
        }
    }

    public static String d(byte[] bArr) throws cwg {
        String d = cvx.d(bArr);
        String str = "";
        if (d == null) {
            LogUtil.h("RriServiceUnPackage", "unGetEcgAuthor: hexResultInfo is null");
            return "";
        }
        try {
            for (cwd cwdVar : new cwl().a(d.substring(4, d.length())).e()) {
                if (cwdVar == null) {
                    LogUtil.h("RriServiceUnPackage", "unGetEcgAuthor: tlvItem is null, continue.");
                } else if (CommonUtil.a(cwdVar.e(), 16) == 1) {
                    str = cvx.e(cwdVar.c());
                } else {
                    LogUtil.h("RriServiceUnPackage", "unGetEcgAuthor: error command");
                }
            }
        } catch (StringIndexOutOfBoundsException unused) {
            LogUtil.b("RriServiceUnPackage", "unGetEcgAuthor: StringIndexOutOfBoundsException");
        }
        return str;
    }

    private static int d(cwe cweVar) {
        int i = 0;
        for (cwd cwdVar : cweVar.e()) {
            if (cwdVar == null) {
                LogUtil.h("RriServiceUnPackage", "unTlvGetEcgFrameCount tlvItem is null, continue.");
            } else {
                String c = cwdVar.c();
                if (CommonUtil.a(cwdVar.e(), 16) == 1) {
                    i = CommonUtil.a(c, 16);
                } else {
                    LogUtil.h("RriServiceUnPackage", "unTlvGetEcgFrameCount error command");
                }
            }
        }
        return i;
    }
}
