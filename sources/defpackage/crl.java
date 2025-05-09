package defpackage;

import android.text.TextUtils;
import com.huawei.health.device.kit.hwwsp.TlvDeviceCommand;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.phoneservice.feedbackcommon.network.FeedbackWebConstants;
import health.compact.a.CommonUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class crl {
    private cwl b = new cwl();

    public long a(String str) {
        byte[] d = d(str);
        if (d != null) {
            return f(d);
        }
        return 0L;
    }

    private byte[] d(String str) {
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
                LogUtil.b("TlvOtaUtil", "filePraseByte finally IOException");
                return bArr2;
            }
        } catch (FileNotFoundException unused4) {
            fileInputStream3 = fileInputStream;
            LogUtil.b("TlvOtaUtil", "filePraseByte FileNotFoundException");
            if (fileInputStream3 != null) {
                try {
                    fileInputStream3.close();
                } catch (IOException unused5) {
                    LogUtil.b("TlvOtaUtil", "filePraseByte finally IOException");
                }
            }
            return bArr;
        } catch (IOException unused6) {
            fileInputStream2 = fileInputStream;
            LogUtil.b("TlvOtaUtil", "filePraseByte IOException");
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (IOException unused7) {
                    LogUtil.b("TlvOtaUtil", "filePraseByte finally IOException");
                }
            }
            return bArr;
        } catch (Throwable th2) {
            th = th2;
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException unused8) {
                    LogUtil.b("TlvOtaUtil", "filePraseByte finally IOException");
                }
            }
            throw th;
        }
    }

    public byte[] c(TlvDeviceCommand tlvDeviceCommand) {
        int serviceId = tlvDeviceCommand.getServiceId();
        int commandId = tlvDeviceCommand.getCommandId();
        byte[] dataContent = tlvDeviceCommand.getDataContent();
        ByteBuffer allocate = ByteBuffer.allocate(dataContent.length + 2);
        allocate.put(cvx.a(cvx.e(serviceId)));
        allocate.put(cvx.a(cvx.e(commandId)));
        allocate.put(dataContent);
        LogUtil.a("TlvOtaUtil", "command data:", cvx.d(dataContent));
        allocate.flip();
        return allocate.array();
    }

    private long f(byte[] bArr) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 7; i >= 4; i--) {
            stringBuffer.append(cvx.e(bArr[i]));
        }
        return CommonUtil.y(stringBuffer.toString()) + 256;
    }

    public chc b(byte[] bArr) throws cwg {
        chc chcVar = new chc();
        String d = cvx.d(bArr);
        if (TextUtils.isEmpty(d)) {
            LogUtil.h("TlvOtaUtil", "parserQueryAllow resultDataHex is empty");
            return chcVar;
        }
        return a(chcVar, this.b.a(d.substring(4, d.length())));
    }

    private chc a(chc chcVar, cwe cweVar) {
        if (cweVar == null) {
            LogUtil.h("TlvOtaUtil", "parserQueryAllow tlvFather is null");
            return chcVar;
        }
        List<cwd> e = cweVar.e();
        if (e != null && e.size() > 0) {
            for (cwd cwdVar : e) {
                if (cwdVar != null) {
                    int a2 = CommonUtil.a(cwdVar.e(), 16);
                    String c = cwdVar.c();
                    if (a2 == 4) {
                        chcVar.e(CommonUtil.a(c, 16));
                    } else if (a2 == 127) {
                        chcVar.d(CommonUtil.a(c, 16));
                    } else {
                        LogUtil.h("TlvOtaUtil", "queryAllow default");
                    }
                }
            }
        }
        return chcVar;
    }

    public cgx c(byte[] bArr) throws cwg {
        cgx cgxVar = new cgx();
        String d = cvx.d(bArr);
        if (d == null) {
            LogUtil.h("TlvOtaUtil", "parserParameters resultDataHex is null");
            return cgxVar;
        }
        return a(cgxVar, this.b.a(d.substring(4, d.length())));
    }

    private cgx a(cgx cgxVar, cwe cweVar) {
        if (cweVar == null) {
            LogUtil.h("TlvOtaUtil", "parserParameters tlvFather is null");
            return cgxVar;
        }
        List<cwd> e = cweVar.e();
        if (e != null && !e.isEmpty()) {
            for (cwd cwdVar : e) {
                int w = CommonUtil.w(cwdVar.e());
                String c = cwdVar.c();
                switch (w) {
                    case 1:
                        cgxVar.a(CommonUtil.w(c));
                        break;
                    case 2:
                        cgxVar.e(CommonUtil.w(c));
                        break;
                    case 3:
                        cgxVar.c(CommonUtil.w(c));
                        break;
                    case 4:
                        cgxVar.e(CommonUtil.y(c));
                        break;
                    case 5:
                        cgxVar.c(CommonUtil.y(c) == 1);
                        break;
                    case 6:
                        cgxVar.e(CommonUtil.w(c) == 1);
                        break;
                    default:
                        LogUtil.h("TlvOtaUtil", "parameters default");
                        break;
                }
            }
        }
        return cgxVar;
    }

    public cgv a(byte[] bArr) throws cwg {
        cgv cgvVar = new cgv();
        String d = cvx.d(bArr);
        if (d == null) {
            LogUtil.h("TlvOtaUtil", "parserApplyReport resultDataHex is null");
            return cgvVar;
        }
        return d(cgvVar, this.b.a(d.substring(4, d.length())));
    }

    private cgv d(cgv cgvVar, cwe cweVar) {
        if (cweVar == null) {
            LogUtil.h("TlvOtaUtil", "parserApplyReport tlvFather is null");
            return cgvVar;
        }
        List<cwd> e = cweVar.e();
        if (e != null && !e.isEmpty()) {
            for (cwd cwdVar : e) {
                int w = CommonUtil.w(cwdVar.e());
                String c = cwdVar.c();
                if (w == 1) {
                    cgvVar.a(CommonUtil.y(c));
                } else if (w == 2) {
                    cgvVar.b(CommonUtil.y(c));
                } else if (w == 3) {
                    cgvVar.e(b(c));
                } else {
                    LogUtil.h("TlvOtaUtil", "applyReport default");
                }
            }
        }
        return cgvVar;
    }

    public cgy d(byte[] bArr) throws cwg {
        cgy cgyVar = new cgy();
        String d = cvx.d(bArr);
        if (d == null) {
            LogUtil.h("TlvOtaUtil", "parserPackageSizeReport resultDataHex is null");
            return cgyVar;
        }
        return e(cgyVar, this.b.a(d.substring(4, d.length())));
    }

    private cgy e(cgy cgyVar, cwe cweVar) {
        if (cweVar == null) {
            LogUtil.h("TlvOtaUtil", "parserPackageSizeReport tlvFather is null");
            return cgyVar;
        }
        List<cwd> e = cweVar.e();
        if (e != null && e.size() > 0) {
            for (cwd cwdVar : e) {
                if (cwdVar != null) {
                    int a2 = CommonUtil.a(cwdVar.e(), 16);
                    String c = cwdVar.c();
                    if (a2 == 1) {
                        cgyVar.d(CommonUtil.y(c));
                    } else if (a2 == 2) {
                        cgyVar.c(CommonUtil.y(c));
                    } else {
                        LogUtil.c("TlvOtaUtil", "pkgSizeReport default");
                    }
                }
            }
        }
        return cgyVar;
    }

    public int e(byte[] bArr) throws cwg {
        String d = cvx.d(bArr);
        if (d == null) {
            LogUtil.h("TlvOtaUtil", "parserPackageVerifyReport resultDataHex is null");
            return 0;
        }
        return b(this.b.a(d.substring(4, d.length())));
    }

    public int b(cwe cweVar) {
        if (cweVar == null) {
            LogUtil.h("TlvOtaUtil", "parserPackageVerifyReport tlvFather is null");
            return 0;
        }
        try {
            List<cwd> e = cweVar.e();
            if (e != null && e.size() != 0) {
                cwd cwdVar = e.get(0);
                if (cwdVar == null) {
                    LogUtil.h("TlvOtaUtil", "parserPackageVerifyReport first tlvItem is null");
                    return 0;
                }
                return Integer.parseInt(cwdVar.c(), 16);
            }
            LogUtil.h("TlvOtaUtil", "parserPackageVerifyReport tlvs is null or tlvs.size==0");
            return 0;
        } catch (NumberFormatException unused) {
            LogUtil.b("TlvOtaUtil", "NumberFormatException: parserPackageVerifyReport");
            return 0;
        }
    }

    private List<Integer> b(String str) {
        ArrayList arrayList = new ArrayList(16);
        if (TextUtils.isEmpty(str)) {
            LogUtil.b("TlvOtaUtil", "parseAck , bitmapHex is empty");
            return arrayList;
        }
        byte[] a2 = cvx.a(str);
        StringBuilder sb = new StringBuilder(16);
        for (byte b : a2) {
            sb.append(new StringBuffer(a(b)).reverse().toString());
        }
        String sb2 = sb.toString();
        LogUtil.a("TlvOtaUtil", "parseAck, bufferStr = ", sb2);
        for (int i = 0; i < sb2.length(); i++) {
            if ("0".equalsIgnoreCase(sb2.charAt(i) + "")) {
                arrayList.add(0);
                LogUtil.a("TlvOtaUtil", "errorPackages, error package index = ", Integer.valueOf(i));
            } else {
                arrayList.add(1);
            }
        }
        return arrayList;
    }

    private String a(byte b) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 7; i >= 0; i--) {
            stringBuffer.append((int) ((byte) ((b >> i) & 1)));
        }
        return stringBuffer.toString();
    }

    public int e(String str) {
        if (!c(str) || str == null) {
            return 1;
        }
        try {
            File file = new File(CommonUtil.c(str));
            if (!file.exists() && !file.createNewFile()) {
                LogUtil.a("TlvOtaUtil", "getOTAFileByPath, The file already exists continue");
            }
            return (int) file.length();
        } catch (IOException unused) {
            LogUtil.b("TlvOtaUtil", "IOException getOTAFileByPath() exception");
            return 1;
        }
    }

    private boolean c(String str) {
        return str == null || str.indexOf(FeedbackWebConstants.INVALID_FILE_NAME_PRE) < 0;
    }
}
