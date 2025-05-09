package defpackage;

import android.text.TextUtils;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwdevice.outofprocess.datatype.DataOtaParametersV2;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes5.dex */
public class jkw {
    public static String e(DeviceInfo deviceInfo, String str) {
        String str2 = new String(str);
        if (deviceInfo == null || TextUtils.isEmpty(str2)) {
            LogUtil.h("OtaUtil", "getSwitchByDevice() identify or userPreference is empty");
            return "";
        }
        if ("true".equals(str2) || "false".equals(str2) || "1".equals(str2) || "2".equals(str2)) {
            return str2;
        }
        String deviceUdid = deviceInfo.getDeviceUdid();
        String a2 = knl.a(deviceUdid);
        String deviceIdentify = deviceInfo.getDeviceIdentify();
        String a3 = knl.a(deviceIdentify);
        for (String str3 : str2.substring(1, str2.length() - 1).split(",")) {
            String[] split = str3.split("=");
            if (split.length >= 2) {
                String trim = split[0].trim();
                if (trim.equals(a2) || trim.equals(deviceUdid) || trim.equals(deviceIdentify) || trim.equals(a3)) {
                    return split[1];
                }
            }
        }
        return "";
    }

    public static long a(String str, String str2) {
        byte[] b = b(str2);
        if (b != null) {
            return e(str, b);
        }
        return 0L;
    }

    private static byte[] b(String str) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2 = null;
        FileInputStream fileInputStream3 = null;
        if (((int) new File(str).length()) == 0) {
            return null;
        }
        byte[] bArr = new byte[8];
        try {
            try {
                fileInputStream = new FileInputStream(str);
            } catch (Throwable th) {
                th = th;
                fileInputStream = null;
            }
        } catch (FileNotFoundException unused) {
        } catch (IOException unused2) {
        }
        try {
            byte[] bArr2 = fileInputStream.read(bArr) >= 8 ? bArr : null;
            try {
                fileInputStream.close();
                return bArr2;
            } catch (IOException unused3) {
                LogUtil.b("OtaUtil", "filePraseByte finally IOException");
                return bArr2;
            }
        } catch (FileNotFoundException unused4) {
            fileInputStream3 = fileInputStream;
            LogUtil.b("OtaUtil", "filePraseByte FileNotFoundException");
            if (fileInputStream3 != null) {
                try {
                    fileInputStream3.close();
                } catch (IOException unused5) {
                    LogUtil.b("OtaUtil", "filePraseByte finally IOException");
                }
            }
            return bArr;
        } catch (IOException unused6) {
            fileInputStream2 = fileInputStream;
            LogUtil.b("OtaUtil", "filePraseByte IOException");
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (IOException unused7) {
                    LogUtil.b("OtaUtil", "filePraseByte finally IOException");
                }
            }
            return bArr;
        } catch (Throwable th2) {
            th = th2;
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException unused8) {
                    LogUtil.b("OtaUtil", "filePraseByte finally IOException");
                }
            }
            throw th;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:5:0x0056, code lost:
    
        if (r3 > 0) goto L9;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static long e(java.lang.String r3, byte[] r4) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            r1 = 7
            r1 = r4[r1]
            java.lang.String r1 = defpackage.cvx.e(r1)
            r0.append(r1)
            r1 = 6
            r1 = r4[r1]
            java.lang.String r1 = defpackage.cvx.e(r1)
            r0.append(r1)
            r1 = 5
            r1 = r4[r1]
            java.lang.String r1 = defpackage.cvx.e(r1)
            r0.append(r1)
            r1 = 4
            r4 = r4[r1]
            java.lang.String r4 = defpackage.cvx.e(r4)
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            jkj r0 = defpackage.jkj.d()
            com.huawei.health.devicemgr.business.entity.DeviceInfo r3 = r0.e(r3)
            r0 = 144(0x90, float:2.02E-43)
            boolean r0 = defpackage.cwi.c(r3, r0)
            java.lang.String r1 = "OtaUtil"
            if (r0 == 0) goto L59
            if (r3 == 0) goto L59
            int r3 = r3.getDeviceOtaSignatureLength()
            java.lang.String r0 = "getOtaInfoComponentSize signatureLength:"
            java.lang.Integer r2 = java.lang.Integer.valueOf(r3)
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r2}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            if (r3 <= 0) goto L59
            goto L5b
        L59:
            r3 = 256(0x100, float:3.59E-43)
        L5b:
            java.lang.String r0 = "getOtaInfoComponentSize defaultSignatureLength:"
            java.lang.Integer r2 = java.lang.Integer.valueOf(r3)
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r2}
            com.huawei.hwlogsmodel.LogUtil.a(r1, r0)
            long r0 = health.compact.a.CommonUtil.y(r4)
            long r3 = (long) r3
            long r0 = r0 + r3
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.jkw.e(java.lang.String, byte[]):long");
    }

    public static int[] c(byte[] bArr) throws cwg {
        String d = cvx.d(bArr);
        if (d == null || d.length() <= 6) {
            return new int[]{100001};
        }
        int[] a2 = a(new cwl().a(d.substring(6, d.length())));
        LogUtil.a("OtaUtil", "Error getErrorCode Code:", Integer.valueOf(a2[0]));
        return a2;
    }

    private static int[] a(cwe cweVar) {
        if (cweVar == null) {
            return new int[]{100001};
        }
        List<cwd> e = cweVar.e();
        if (e == null || e.size() == 0) {
            return new int[]{100001};
        }
        int size = e.size();
        int[] iArr = new int[size];
        for (cwd cwdVar : e) {
            if (cwdVar != null) {
                String c = cwdVar.c();
                int a2 = CommonUtil.a(cwdVar.e(), 16);
                if (a2 != 19) {
                    if (a2 != 127) {
                        LogUtil.h("OtaUtil", "unTlvGetErrorCode: unknow case");
                    } else if (size > 0) {
                        iArr[0] = CommonUtil.a(c, 16);
                    }
                } else if (size > 1) {
                    iArr[1] = CommonUtil.a(c, 16);
                }
            }
        }
        return iArr;
    }

    public static jkp j(byte[] bArr) throws cwg {
        LogUtil.a("OtaUtil", "5.9.1 Enter unQueryAllow");
        String d = cvx.d(bArr);
        if (d == null || d.length() <= 4) {
            return new jkp();
        }
        return d(new cwl().a(d.substring(4, d.length())));
    }

    private static jkp d(cwe cweVar) {
        List<cwd> e;
        LogUtil.a("OtaUtil", "5.9.1 unQueryAllow enter");
        jkp jkpVar = new jkp();
        if (cweVar != null && (e = cweVar.e()) != null && e.size() > 0) {
            for (cwd cwdVar : e) {
                if (cwdVar != null) {
                    int a2 = CommonUtil.a(cwdVar.e(), 16);
                    String c = cwdVar.c();
                    if (a2 == 4) {
                        jkpVar.a(CommonUtil.a(c, 16));
                        LogUtil.a("OtaUtil", "dataOtaQueryAllow.setBatteryThreshold:", Integer.valueOf(CommonUtil.a(c, 16)));
                    } else if (a2 == 127) {
                        jkpVar.b(CommonUtil.a(c, 16));
                        LogUtil.a("OtaUtil", "dataOtaQueryAllow.setErrorCode:", Integer.valueOf(CommonUtil.a(c, 16)));
                    } else {
                        LogUtil.h("OtaUtil", "unQueryAllow: no such case");
                    }
                }
            }
        }
        return jkpVar;
    }

    public static DataOtaParametersV2 a(byte[] bArr) throws cwg {
        String d = cvx.d(bArr);
        if (d == null || d.length() <= 4) {
            return new DataOtaParametersV2();
        }
        return b(new cwl().a(d.substring(4, d.length())));
    }

    public static int d(byte[] bArr) throws cwg {
        LogUtil.a("OtaUtil", "5.9.7 Enter dataInfos: ", cvx.d(bArr));
        String d = cvx.d(bArr);
        if (d == null || d.length() <= 4) {
            return 0;
        }
        return e(new cwl().a(d.substring(4, d.length())));
    }

    private static int e(cwe cweVar) {
        cwd cwdVar;
        if (cweVar == null) {
            return 0;
        }
        try {
            List<cwd> e = cweVar.e();
            if (e == null || e.size() == 0 || (cwdVar = e.get(0)) == null) {
                return 0;
            }
            return Integer.parseInt(cwdVar.c(), 16);
        } catch (NumberFormatException unused) {
            LogUtil.b("OtaUtil", "unOtaStatus: NumberFormatException");
            return 0;
        }
    }

    public static int b(byte[] bArr) throws cwg {
        cwd e = e(bArr, "5.9.19");
        if (e == null) {
            LogUtil.h("OtaUtil", "parseDeviceTransMode tlv is null");
            return 255;
        }
        if (CommonUtil.a(e.e(), 16) != 1) {
            LogUtil.h("OtaUtil", "parseDeviceTransMode type not equals 1");
            return 255;
        }
        int a2 = CommonUtil.a(e.c(), 16);
        LogUtil.a("OtaUtil", "parseDeviceTransMode value:", Integer.valueOf(a2));
        return a2;
    }

    private static cwd e(byte[] bArr, String str) throws cwg {
        String d = cvx.d(bArr);
        if (d == null || d.length() <= 4) {
            LogUtil.h("OtaUtil", "parseTlvByDataInfos resultDataHex is null ", str);
            return null;
        }
        List<cwd> e = new cwl().a(d.substring(4, d.length())).e();
        if (e == null || e.isEmpty()) {
            LogUtil.h("OtaUtil", "parseTlvByDataInfos tlvList is empty ", str);
            return null;
        }
        return e.get(0);
    }

    public static jkr e(byte[] bArr) throws cwg {
        List<cwd> a2 = a(bArr, "5.9.21");
        jkr jkrVar = new jkr();
        if (a2 == null || a2.isEmpty()) {
            LogUtil.h("OtaUtil", "parseDeviceChangeLogMessage tlv is null");
            return jkrVar;
        }
        for (cwd cwdVar : a2) {
            int a3 = CommonUtil.a(cwdVar.e(), 16);
            LogUtil.c("OtaUtil", "parseDeviceChangeLogMessage itemType=", Integer.valueOf(a3));
            if (a3 == 3) {
                int a4 = CommonUtil.a(cwdVar.c(), 16);
                LogUtil.c("OtaUtil", "parseDeviceChangeLogMessage result=", Integer.valueOf(a4));
                jkrVar.b(a4);
            }
            if (a3 == 4) {
                String e = cvx.e(cwdVar.c());
                LogUtil.c("OtaUtil", "parseDeviceChangeLogMessage url=", e);
                jkrVar.e(e);
            }
        }
        return jkrVar;
    }

    private static List<cwd> a(byte[] bArr, String str) throws cwg {
        String d = cvx.d(bArr);
        ArrayList arrayList = new ArrayList();
        if (d == null || d.length() <= 4) {
            LogUtil.h("OtaUtil", "parseTlvByDataInfos resultDataHex is null ", str);
            return arrayList;
        }
        return new cwl().a(d.substring(4, d.length())).e();
    }

    private static DataOtaParametersV2 b(cwe cweVar) {
        if (cweVar == null) {
            LogUtil.h("OtaUtil", "dealOtaParametersV2 tlvFather is null");
            return null;
        }
        DataOtaParametersV2 dataOtaParametersV2 = new DataOtaParametersV2();
        List<cwd> e = cweVar.e();
        if (e != null && !e.isEmpty()) {
            for (cwd cwdVar : e) {
                int w = CommonUtil.w(cwdVar.e());
                String c = cwdVar.c();
                switch (w) {
                    case 1:
                        dataOtaParametersV2.setAppWaitTimeout(CommonUtil.w(c));
                        LogUtil.a("OtaUtil", "dataOtaParametersV2.setAppWaitTimeout:", Integer.valueOf(CommonUtil.w(c)));
                        break;
                    case 2:
                        dataOtaParametersV2.setDeviceRestartTimeout(CommonUtil.w(c));
                        LogUtil.a("OtaUtil", "dataOtaParametersV2.setDeviceRestartTimeout:", Integer.valueOf(CommonUtil.w(c)));
                        break;
                    case 3:
                        dataOtaParametersV2.setOtaUnitSize(CommonUtil.w(c));
                        LogUtil.a("OtaUtil", "dataOtaParametersV2.setOtaUnitSize:", Integer.valueOf(CommonUtil.w(c)));
                        break;
                    case 4:
                        dataOtaParametersV2.setOtaInterval(CommonUtil.y(c));
                        LogUtil.a("OtaUtil", "dataOtaParametersV2.setOtaInterval:", Long.valueOf(CommonUtil.y(c)));
                        break;
                    case 5:
                        dataOtaParametersV2.setAckEnable(CommonUtil.y(c));
                        LogUtil.a("OtaUtil", "dataOtaParametersV2.setAckEnable:", Long.valueOf(CommonUtil.y(c)));
                        break;
                    case 6:
                        dataOtaParametersV2.setOffsetEnable(CommonUtil.w(c) == 1);
                        LogUtil.a("OtaUtil", "dataOtaParametersV2.setOffsetEnable:", Integer.valueOf(CommonUtil.w(c)));
                        break;
                    default:
                        LogUtil.b("OtaUtil", "dealOtaParametersV2 default");
                        break;
                }
            }
        }
        return dataOtaParametersV2;
    }
}
