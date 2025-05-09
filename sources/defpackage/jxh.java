package defpackage;

import com.huawei.hwcommonmodel.fitnessdatatype.DataTotalMotion;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateDetect;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class jxh {
    private static cwe g(byte[] bArr) throws cwg {
        String d = cvx.d(bArr);
        return new cwl().a((d == null || d.length() <= 4) ? null : d.substring(4, d.length()));
    }

    public static jxt c(byte[] bArr) throws cwg {
        return a(g(bArr));
    }

    public static jxt a(cwe cweVar) {
        jxt jxtVar = new jxt();
        if (cweVar == null) {
            return jxtVar;
        }
        List<cwe> a2 = cweVar.a();
        for (int i = 0; i < a2.size(); i++) {
            cwe cweVar2 = a2.get(i);
            List<cwd> e = cweVar2.e();
            for (int i2 = 0; i2 < e.size(); i2++) {
                int w = CommonUtil.w(e.get(i2).e());
                if (w == 2) {
                    jxtVar.c(CommonUtil.w(e.get(i2).c()));
                } else if (w == 9) {
                    HeartRateDetect heartRateDetect = new HeartRateDetect();
                    String c = e.get(i2).c();
                    LogUtil.a("BasicUnPackage", "unTlv HeartRateDetect value:", c);
                    d(heartRateDetect, c);
                    jxtVar.a(heartRateDetect);
                } else {
                    LogUtil.h("BasicUnPackage", "unTlvGetHealthDataCurrentDay default");
                }
            }
            Iterator<cwe> it = cweVar2.a().iterator();
            while (it.hasNext()) {
                jxtVar.c().add(c(it.next().e()));
            }
        }
        return jxtVar;
    }

    public static jxs a(byte[] bArr) throws cwg {
        return jxj.d(g(bArr));
    }

    public static jxo b(byte[] bArr) throws cwg {
        return jxj.b(g(bArr));
    }

    public static int f(byte[] bArr) throws cwg {
        return jxp.c(g(bArr));
    }

    public static jxw j(byte[] bArr) throws cwg {
        return jxp.a(g(bArr));
    }

    public static int h(byte[] bArr) throws cwg {
        return jxl.d(g(bArr));
    }

    public static jxx i(byte[] bArr) throws cwg {
        return jxl.a(g(bArr));
    }

    public static int e(byte[] bArr) throws cwg {
        return jxp.d(g(bArr));
    }

    public static jxr d(byte[] bArr) throws cwg {
        return jxp.e(g(bArr));
    }

    private static void d(HeartRateDetect heartRateDetect, String str) {
        if (str.length() == 10) {
            heartRateDetect.setTimestamp(CommonUtil.w(str.substring(0, 8)));
            heartRateDetect.setHeartRate(CommonUtil.w(str.substring(8, 10)));
        }
    }

    private static DataTotalMotion c(List<cwd> list) {
        DataTotalMotion dataTotalMotion = new DataTotalMotion();
        if (list == null) {
            return dataTotalMotion;
        }
        for (cwd cwdVar : list) {
            switch (CommonUtil.w(cwdVar.e())) {
                case 4:
                    dataTotalMotion.setMotion_type(CommonUtil.w(cwdVar.c()));
                    break;
                case 5:
                    dataTotalMotion.setStep(CommonUtil.w(cwdVar.c()));
                    break;
                case 6:
                    dataTotalMotion.setCalorie(CommonUtil.w(cwdVar.c()));
                    break;
                case 7:
                    dataTotalMotion.setDistance(CommonUtil.w(cwdVar.c()));
                    break;
                case 8:
                    dataTotalMotion.setSleep_time(CommonUtil.w(cwdVar.c()));
                    break;
                case 9:
                default:
                    LogUtil.h("BasicUnPackage", "unPackMotion default");
                    break;
                case 10:
                    dataTotalMotion.setHeight(CommonUtil.w(cwdVar.c()));
                    break;
            }
        }
        return dataTotalMotion;
    }
}
