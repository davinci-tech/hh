package defpackage;

import android.text.TextUtils;
import com.huawei.devicesdk.entity.SendMode;
import health.compact.a.LogUtil;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class spy {
    public static bir b(cvq cvqVar) {
        if (d(cvqVar) || cvqVar.getConfigInfoList() == null) {
            LogUtil.a("ConvertCommandUtil", "getCommandTlv getCommandTlv error");
            return null;
        }
        List<cvn> configInfoList = cvqVar.getConfigInfoList();
        bms bmsVar = new bms();
        bmsVar.e(55, 1);
        bmsVar.d(1, cvqVar.getSrcPkgName()).d(2, cvqVar.getWearPkgName()).b(3);
        for (cvn cvnVar : configInfoList) {
            if (cvnVar != null) {
                bmsVar.c(4).c(5, (int) cvnVar.a()).d(6, cvnVar.e());
                if (cvnVar.b() != null) {
                    bmsVar.e(7, cvnVar.b());
                }
                Long c = cvnVar.c();
                if (c != null) {
                    bmsVar.b(8, c.longValue());
                    LogUtil.c("ConvertCommandUtil", "getCommandTlv lose weight:", c);
                }
                bmsVar.c(bmsVar.c());
            }
        }
        return e(bmsVar.b(bmsVar.b()).d());
    }

    public static bir e(cvp cvpVar) {
        if (d(cvpVar)) {
            LogUtil.a("ConvertCommandUtil", "getCommandTlv sampleEvent error");
            return null;
        }
        bms bmsVar = new bms();
        bmsVar.e(55, 2).d(1, cvpVar.getSrcPkgName()).d(2, cvpVar.getWearPkgName()).i(3, (int) cvpVar.e()).j(4, cvpVar.a());
        if (cvpVar.c() != null) {
            bmsVar.d(5, cvpVar.c());
        }
        return e(bmsVar.d());
    }

    public static bir c(cvu cvuVar) {
        if (d(cvuVar) || cvuVar.a() == null) {
            LogUtil.a("ConvertCommandUtil", "getCommandTlv SamplePoint error");
            return null;
        }
        bms bmsVar = new bms();
        bmsVar.e(55, 3).d(1, cvuVar.getSrcPkgName()).d(2, cvuVar.getWearPkgName()).i(3, (int) cvuVar.e());
        if (cvuVar.d() != -1) {
            bmsVar.i(4, (int) cvuVar.d());
        }
        if (cvuVar.b() != -1) {
            bmsVar.i(5, (int) cvuVar.b());
        }
        bmsVar.b(6);
        for (cvv cvvVar : cvuVar.a()) {
            if (cvvVar != null) {
                bmsVar.c(7).c(8, (int) cvvVar.a());
                if (cvvVar.d() != null) {
                    bmsVar.e(9, cvvVar.d());
                }
                bmsVar.c(bmsVar.c());
            }
        }
        bmsVar.b(bmsVar.b());
        byte[] c = cvuVar.c();
        if (c != null) {
            bmsVar.d(10, c);
        }
        return e(bmsVar.d());
    }

    public static bir b(cvi cviVar) {
        LogUtil.c("ConvertCommandUtil", "getCommandTlv start");
        if (d(cviVar) || cviVar.b() == null) {
            LogUtil.a("ConvertCommandUtil", "getCommandTlv DictionaryPointInfo error");
            return null;
        }
        bms bmsVar = new bms();
        bmsVar.e(55, 4).d(1, cviVar.getSrcPkgName()).d(2, cviVar.getWearPkgName()).i(3, (int) cviVar.d());
        bmsVar.b(4);
        List<cvo> b = cviVar.b();
        int c = cviVar.c();
        Iterator<cvo> it = b.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            cvo next = it.next();
            if (next != null) {
                bmsVar.c(5);
                bmsVar.b(6, next.c());
                bmsVar.b(7, next.d());
                if (next.e() != -1) {
                    bmsVar.b(8, next.e());
                }
                bmsVar.d(c(next.a())).e(13, next.b());
                bmsVar.c(bmsVar.c());
                c--;
                if (c <= 0) {
                    LogUtil.a("ConvertCommandUtil", "overload all data is " + b.size() + " current is " + cviVar.c());
                    break;
                }
            }
        }
        bmsVar.b(bmsVar.b());
        return e(bmsVar.d());
    }

    private static byte[] c(List<cvv> list) {
        if (list == null || list.isEmpty()) {
            return new byte[0];
        }
        bms bmsVar = new bms();
        bmsVar.b(9);
        for (cvv cvvVar : list) {
            bmsVar.c(10).c(11, (int) cvvVar.a()).e(12, cvvVar.d());
            bmsVar.c(bmsVar.c());
        }
        return bmsVar.b();
    }

    private static bir e(byte[] bArr) {
        bir birVar = new bir();
        birVar.e(bArr);
        birVar.b(SendMode.PROTOCOL_TYPE_5A);
        return birVar;
    }

    private static boolean d(cvr cvrVar) {
        if (cvrVar != null) {
            return TextUtils.isEmpty(cvrVar.getSrcPkgName()) || TextUtils.isEmpty(cvrVar.getWearPkgName());
        }
        LogUtil.a("ConvertCommandUtil", "sampleBase is null.");
        return true;
    }
}
