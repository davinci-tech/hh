package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.multisimservice.model.MultiSimDeviceInfo;
import com.huawei.multisimservice.model.SimInfo;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class kty {
    private static boolean d;

    private static int b(String str, int i) {
        try {
            return Integer.parseInt(str, i);
        } catch (NumberFormatException unused) {
            LogUtil.b("MultiSimUnPackage", "NumberFormatException");
            return 0;
        }
    }

    private static int[] b(cwe cweVar) {
        List<cwd> e = cweVar.e();
        int size = e.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            String c = e.get(i).c();
            if (b(e.get(i).e(), 16) == 127) {
                iArr[0] = b(c, 16);
            } else {
                LogUtil.h("MultiSimUnPackage", "dealErrorCode default");
            }
        }
        return iArr;
    }

    public static int b(byte[] bArr) throws Exception {
        if (bArr == null) {
            return -1;
        }
        int[] b = b(new cwl().a(cvx.d(bArr).substring(4)));
        LogUtil.a("MultiSimUnPackage", "Error Code:", Integer.valueOf(b[0]));
        int i = b[0];
        if (i == 100000) {
            return 0;
        }
        return i;
    }

    private static MultiSimDeviceInfo e(cwe cweVar) {
        MultiSimDeviceInfo multiSimDeviceInfo = new MultiSimDeviceInfo();
        List<cwe> a2 = cweVar.a();
        for (int i = 0; i < a2.size(); i++) {
            List<cwd> e = a2.get(i).e();
            for (int i2 = 0; i2 < e.size(); i2++) {
                String c = e.get(i2).c();
                int b = b(e.get(i2).e(), 16);
                if (b == 2) {
                    multiSimDeviceInfo.setDeviceType(b(c, 16));
                } else if (b == 3) {
                    multiSimDeviceInfo.setDeviceIMEI(cvx.e(c));
                } else if (b == 4) {
                    multiSimDeviceInfo.setDeviceSerialNumber(cvx.e(c));
                } else if (b == 5) {
                    multiSimDeviceInfo.setProductName(cvx.e(c));
                } else if (b == 6) {
                    multiSimDeviceInfo.setEID(cvx.e(c));
                } else if (b == 12) {
                    boolean z = b(c, 16) == 0;
                    d = z;
                    LogUtil.a("MultiSimUnPackage", "mIsEsimPowerDownStatus:", Boolean.valueOf(z));
                } else {
                    LogUtil.h("MultiSimUnPackage", "dealMultiSimDeviceInfo default:", Integer.valueOf(b));
                }
            }
            multiSimDeviceInfo.setSimInfoList(e(a2, i));
        }
        return multiSimDeviceInfo;
    }

    private static ArrayList<SimInfo> e(List<cwe> list, int i) {
        ArrayList<SimInfo> arrayList = new ArrayList<>(10);
        if (list == null || i < 0 || i >= list.size()) {
            LogUtil.h("MultiSimUnPackage", "getSimInfoArrayList tlvFather or flag is not valid");
            return arrayList;
        }
        List<cwe> a2 = list.get(i).a();
        for (int i2 = 0; i2 < a2.size(); i2++) {
            List<cwe> a3 = a2.get(i2).a();
            for (int i3 = 0; i3 < a3.size(); i3++) {
                List<cwd> e = a3.get(i3).e();
                SimInfo simInfo = new SimInfo();
                for (int i4 = 0; i4 < e.size(); i4++) {
                    d(simInfo, e.get(i4).c(), b(e.get(i4).e(), 16));
                }
                LogUtil.a("MultiSimUnPackage", " profileInfo:", simInfo);
                arrayList.add(simInfo);
            }
        }
        return arrayList;
    }

    private static void d(SimInfo simInfo, String str, int i) {
        switch (i) {
            case 9:
                simInfo.setIMSI(cvx.e(str));
                break;
            case 10:
                simInfo.setICCID(cvx.e(str));
                break;
            case 11:
                simInfo.setActive(b(str, 16) != 1);
                break;
            default:
                LogUtil.h("MultiSimUnPackage", "setSimInfoValue unkown type:", Integer.valueOf(i));
                break;
        }
    }

    public static MultiSimDeviceInfo j(byte[] bArr) throws Exception {
        if (bArr == null) {
            return null;
        }
        d = false;
        return e(new cwl().a(cvx.d(bArr).substring(4)));
    }

    public static int a(byte[] bArr) throws Exception {
        if (bArr == null) {
            return -1;
        }
        List<cwd> e = new cwl().a(cvx.d(bArr).substring(4)).e();
        int size = e.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            String c = e.get(i).c();
            if (b(e.get(i).e(), 16) == 1) {
                iArr[0] = b(c, 16);
            } else {
                LogUtil.h("MultiSimUnPackage", "parseRemoveStatus default");
            }
        }
        LogUtil.a("MultiSimUnPackage", "Error Code:", Integer.valueOf(iArr[0]));
        return iArr[0];
    }

    public static int c(byte[] bArr) {
        cwe cweVar;
        if (bArr == null || bArr.length < 4) {
            LogUtil.h("MultiSimUnPackage", "parseBatteryThreshold dataInfos is null");
            return -1;
        }
        String d2 = cvx.d(bArr);
        if (d2 == null || d2.length() < 4) {
            LogUtil.h("MultiSimUnPackage", "parseBatteryThreshold() tlvHex is null or error");
            return -1;
        }
        try {
            cweVar = new cwl().a(d2.substring(4));
        } catch (cwg unused) {
            LogUtil.h("MultiSimUnPackage", "parseBatteryThreshold TlvException");
            cweVar = null;
        }
        if (cweVar == null) {
            LogUtil.h("MultiSimUnPackage", "parseBatteryThreshold tlvFather is null");
            return -1;
        }
        List<cwd> e = cweVar.e();
        if (e == null || e.isEmpty()) {
            LogUtil.h("MultiSimUnPackage", "parseBatteryThreshold tlvs is null or empty");
            return -1;
        }
        for (cwd cwdVar : e) {
            if (b(cwdVar.e(), 16) == 1) {
                return b(cwdVar.c(), 16);
            }
            LogUtil.h("MultiSimUnPackage", "parseBatteryThreshold Tlv element type is not power result");
        }
        return -1;
    }

    public static int e(byte[] bArr) throws Exception {
        if (bArr == null) {
            return -1;
        }
        List<cwd> e = new cwl().a(cvx.d(bArr).substring(4)).e();
        if (e == null || e.size() <= 0) {
            return -1;
        }
        return b(e.get(0).c(), 16);
    }

    public static jdf d(byte[] bArr) throws Exception {
        LogUtil.a("MultiSimUnPackage", "parseEsimMateData");
        if (bArr == null) {
            return null;
        }
        cwe a2 = new cwl().a(cvx.d(bArr).substring(4));
        List<cwe> a3 = a2.a();
        List<cwd> e = a2.e();
        jdf jdfVar = new jdf();
        if (e != null && e.size() > 0) {
            for (cwd cwdVar : e) {
                LogUtil.a("MultiSimUnPackage", "parseEsimMateData deal with tlvs element");
                int b = b(cwdVar.e(), 16);
                if (b == 1) {
                    LogUtil.c("MultiSimUnPackage", "authResult", Integer.valueOf(b(cwdVar.c(), 16)));
                    jdfVar.e(b(cwdVar.c(), 16));
                } else if (b == 2) {
                    LogUtil.c("MultiSimUnPackage", "SPN", Integer.valueOf(b(cwdVar.c(), 16)));
                    jdfVar.b(b(cwdVar.c(), 16));
                } else {
                    LogUtil.h("MultiSimUnPackage", "parseEsimMateData default");
                }
            }
        }
        jdc jdcVar = new jdc();
        if (a3 != null && a3.size() > 0) {
            b(a3, jdfVar, jdcVar);
        }
        return jdfVar;
    }

    private static void b(List<cwe> list, jdf jdfVar, jdc jdcVar) {
        LogUtil.a("MultiSimUnPackage", "parseEsimMateData deal with fathertlvList");
        for (cwd cwdVar : list.get(0).e()) {
            LogUtil.a("MultiSimUnPackage", "parseEsimMateData deal with tlvs element");
            switch (b(cwdVar.e(), 16)) {
                case 4:
                    LogUtil.c("MultiSimUnPackage", "ICCID:", cvx.e(cwdVar.c()));
                    jdcVar.b(cvx.e(cwdVar.c()));
                    break;
                case 5:
                    LogUtil.c("MultiSimUnPackage", "SPN:", cvx.e(cwdVar.c()));
                    jdcVar.i(cvx.e(cwdVar.c()));
                    break;
                case 6:
                    LogUtil.c("MultiSimUnPackage", "profile name:", cvx.e(cwdVar.c()));
                    jdcVar.c(cvx.e(cwdVar.c()));
                    break;
                case 7:
                    LogUtil.c("MultiSimUnPackage", "profile class:", cvx.e(cwdVar.c()));
                    jdcVar.d(cvx.e(cwdVar.c()));
                    break;
                case 8:
                    LogUtil.c("MultiSimUnPackage", "ICON TYPE:", Integer.valueOf(CommonUtil.h(cwdVar.c())));
                    jdcVar.b(CommonUtil.h(cwdVar.c()));
                    break;
                case 9:
                    LogUtil.a("MultiSimUnPackage", "ICON:", cwdVar.c());
                    jdcVar.c(cvx.a(cwdVar.c()));
                    break;
                case 10:
                    LogUtil.c("MultiSimUnPackage", "CONFINFO:", cvx.e(cwdVar.c()));
                    jdcVar.a(cvx.e(cwdVar.c()));
                    break;
                case 11:
                    LogUtil.c("MultiSimUnPackage", "PO:", cvx.e(cwdVar.c()));
                    jdcVar.e(cvx.e(cwdVar.c()));
                    break;
                case 12:
                    LogUtil.c("MultiSimUnPackage", "PPR:", cvx.e(cwdVar.c()));
                    jdcVar.d(cvx.a(cwdVar.c()));
                    break;
                default:
                    LogUtil.h("MultiSimUnPackage", "setEsimProfile default");
                    break;
            }
        }
        jdfVar.b(jdcVar);
    }

    public static boolean a() {
        return d;
    }
}
