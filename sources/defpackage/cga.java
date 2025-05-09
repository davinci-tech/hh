package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

/* loaded from: classes3.dex */
public class cga {
    private cwl e = new cwl();

    public cfw b(byte[] bArr) {
        List<cwd> e;
        int i;
        String d = cvx.d(bArr);
        LogUtil.a("WspFileDataParser", "5.44.2 parseFileCheckData:", d);
        if (TextUtils.isEmpty(d) || d.length() < 4) {
            LogUtil.h("WspFileDataParser", "parseFileCheckData dataBytes is error");
            return null;
        }
        try {
            e = this.e.a(d.substring(4)).e();
        } catch (cwg unused) {
            LogUtil.b("WspFileDataParser", "parseFileCheckData error");
        }
        if (e != null && e.size() > 0) {
            String str = "";
            int i2 = 0;
            for (cwd cwdVar : e) {
                try {
                    i = Integer.parseInt(cwdVar.e(), 16);
                } catch (NumberFormatException unused2) {
                    LogUtil.b("WspFileDataParser", "handleParamTlv exception");
                    i = 0;
                }
                String c = cwdVar.c();
                if (i == 1) {
                    i2 = CommonUtil.a(c, 16);
                    LogUtil.a("WspFileDataParser", "parseFileCheckData file id:", Integer.valueOf(i2));
                } else if (i != 3) {
                    LogUtil.a("WspFileDataParser", "parseFileCheckData default");
                } else {
                    str = c;
                }
            }
            cfw cfwVar = new cfw();
            cfwVar.b(i2);
            cfwVar.c(str);
            return cfwVar;
        }
        LogUtil.h("WspFileDataParser", "parseFileCheckData tlv list error");
        return null;
    }

    public cgc e(byte[] bArr) {
        List<cwd> e;
        int i;
        String d = cvx.d(bArr);
        LogUtil.a("WspFileDataParser", "5.44.3 parseFileTransferParameter:", d);
        if (TextUtils.isEmpty(d) || d.length() < 4) {
            LogUtil.h("WspFileDataParser", "parseFileTransferParameter dataBytes is error");
            return null;
        }
        String substring = d.substring(4);
        cgc cgcVar = new cgc();
        try {
            e = this.e.a(substring).e();
        } catch (cwg unused) {
            LogUtil.b("WspFileDataParser", "parseFileTransferParameter error");
        }
        if (e != null && e.size() > 0) {
            for (cwd cwdVar : e) {
                b(cgcVar, Integer.valueOf(cwdVar.e(), 16).intValue(), cwdVar.c());
            }
            LogUtil.a("WspFileDataParser", "5.44.3 parseFileTransferParameter fileId:", Integer.valueOf(cgcVar.c()));
            int e2 = cgcVar.e();
            int a2 = cgcVar.a();
            if (a2 <= 0) {
                i = 0;
            } else if (e2 % a2 == 0) {
                i = (e2 / a2) - 1;
            } else {
                i = e2 / a2;
            }
            cgcVar.d(i);
            LogUtil.a("WspFileDataParser", "5.44.3 parseFileTransferParameter psnMax:", Integer.valueOf(i));
            return cgcVar;
        }
        LogUtil.h("WspFileDataParser", "parseFileTransferParameter tlv list error");
        return null;
    }

    private void b(cgc cgcVar, int i, String str) {
        if (i == 1) {
            cgcVar.e(CommonUtil.a(str, 16));
            LogUtil.a("WspFileDataParser", "handleParamTlv file id:", Integer.valueOf(cgcVar.c()));
            return;
        }
        if (i == 2) {
            cgcVar.a(CommonUtil.a(str, 16));
            LogUtil.a("WspFileDataParser", "handleParamTlv device wait timeout:", Integer.valueOf(cgcVar.d()));
            return;
        }
        int i2 = 0;
        if (i == 3) {
            try {
                i2 = Integer.parseInt(str, 16);
            } catch (NumberFormatException unused) {
                LogUtil.b("WspFileDataParser", "handleParamTlv exception");
            }
            cgcVar.b(i2);
            LogUtil.a("WspFileDataParser", "handleParamTlv unit size:", Integer.valueOf(cgcVar.a()));
            return;
        }
        if (i == 4) {
            cgcVar.c(CommonUtil.a(str, 16));
            LogUtil.a("WspFileDataParser", "handleParamTlv max apply:", Integer.valueOf(cgcVar.e()));
        } else if (i == 5) {
            cgcVar.e(CommonUtil.w(str) == 1);
            LogUtil.a("WspFileDataParser", "handleParamTlv not need encrypt:", Boolean.valueOf(cgcVar.b()));
        } else {
            LogUtil.a("WspFileDataParser", "handleParamTlv default");
        }
    }

    public cge c(String str) {
        if (TextUtils.isEmpty(str) || str.length() < 16) {
            LogUtil.b("WspFileDataParser", "send info is null");
            return null;
        }
        String substring = str.substring(4);
        int a2 = CommonUtil.a(substring.substring(0, 2), 16);
        int a3 = CommonUtil.a(substring.substring(2, 10), 16);
        int a4 = CommonUtil.a(substring.substring(10, 12), 16);
        ReleaseLogUtil.e("R_Weight_WspFileDataParser", "5.44.5 parseFileReceivedData fileID:", Integer.valueOf(a2), ", offset:", Integer.valueOf(a3), ", psn:", Integer.valueOf(a4));
        cge cgeVar = new cge();
        cgeVar.b(a2);
        cgeVar.g(a3);
        cgeVar.j(a4);
        cgeVar.d(substring.substring(12));
        return cgeVar;
    }

    public Optional<cfy> c(byte[] bArr) {
        List<cwd> e;
        String d = cvx.d(bArr);
        if (TextUtils.isEmpty(d) || d.length() < 4) {
            return Optional.empty();
        }
        try {
            e = this.e.a(d.substring(4)).e();
        } catch (cwg unused) {
            LogUtil.b("WspFileDataParser", "parseFileResultNotifyData error");
        }
        if (e != null && e.size() > 0) {
            int i = 0;
            int i2 = 0;
            for (cwd cwdVar : e) {
                int intValue = Integer.valueOf(cwdVar.e(), 16).intValue();
                String c = cwdVar.c();
                if (intValue == 1) {
                    i = CommonUtil.a(c, 16);
                    LogUtil.a("WspFileDataParser", "parseFileResultNotifyData file_id:", Integer.valueOf(i));
                } else if (intValue == 127) {
                    i2 = CommonUtil.a(c, 16);
                    LogUtil.a("WspFileDataParser", "parseFileResultNotifyData status:", Integer.valueOf(i2));
                } else {
                    LogUtil.a("WspFileDataParser", "parseFileResultNotifyData default");
                }
            }
            cfy cfyVar = new cfy();
            cfyVar.b(i);
            cfyVar.e(i2);
            return Optional.ofNullable(cfyVar);
        }
        LogUtil.h("WspFileDataParser", "parseFileResultNotifyData tlv list error");
        return Optional.empty();
    }

    public cfx a(byte[] bArr) {
        List<cwd> e;
        String d = cvx.d(bArr);
        LogUtil.a("WspFileDataParser", "5.44.7 parseFileInfoData:", d);
        if (TextUtils.isEmpty(d) || d.length() < 4) {
            LogUtil.h("WspFileDataParser", "parseFileInfoData dataBytes is error");
            return null;
        }
        try {
            e = this.e.a(d.substring(4)).e();
        } catch (cwg unused) {
            LogUtil.b("WspFileDataParser", "parseFileInfoData error");
        }
        if (e != null && e.size() > 0) {
            cfx cfxVar = new cfx();
            Iterator<cwd> it = e.iterator();
            while (it.hasNext()) {
                e(it.next(), cfxVar);
            }
            return cfxVar;
        }
        LogUtil.h("WspFileDataParser", "parseFileInfoData tlv list error");
        return null;
    }

    private void e(cwd cwdVar, cfx cfxVar) {
        int intValue = Integer.valueOf(cwdVar.e(), 16).intValue();
        String c = cwdVar.c();
        LogUtil.a("WspFileDataParser", "this code:", Integer.valueOf(intValue));
        if (cfxVar == null) {
            cfxVar = new cfx();
        }
        if (intValue == 1) {
            String e = cvx.e(c);
            cfxVar.d(e);
            LogUtil.a("WspFileDataParser", "parseFileInfoData fileName:", e);
            return;
        }
        if (intValue == 2) {
            int w = CommonUtil.w(c);
            cfxVar.i(w);
            LogUtil.a("WspFileDataParser", "parseFileInfoData fileType:", Integer.valueOf(w));
            return;
        }
        if (intValue == 3) {
            int w2 = CommonUtil.w(c);
            cfxVar.b(w2);
            LogUtil.a("WspFileDataParser", "parseFileInfoData fileId:", Integer.valueOf(w2));
        } else if (intValue == 4) {
            int w3 = CommonUtil.w(c);
            cfxVar.f(w3);
            LogUtil.a("WspFileDataParser", "parseFileInfoData fileSize:", Integer.valueOf(w3));
        } else {
            if (intValue == 127) {
                int w4 = CommonUtil.w(c);
                cfxVar.e(w4);
                LogUtil.a("WspFileDataParser", "parseFileInfoData error code:", Integer.valueOf(w4));
                return;
            }
            LogUtil.a("WspFileDataParser", "parseFileInfoData default");
        }
    }

    public cfz d(byte[] bArr) {
        String d = cvx.d(bArr);
        LogUtil.a("WspFileDataParser", "5.44.8 parseFileInfoRequestData:", d);
        if (TextUtils.isEmpty(d) || d.length() < 4) {
            LogUtil.h("WspFileDataParser", "parseFileInfoRequestData dataBytes is error");
            return null;
        }
        String substring = d.substring(4);
        cfz cfzVar = new cfz();
        try {
            List<cwd> e = this.e.a(substring).e();
            if (e != null && e.size() > 0) {
                Iterator<cwd> it = e.iterator();
                while (it.hasNext()) {
                    b(cfzVar, it.next());
                }
            }
        } catch (cwg unused) {
            LogUtil.b("WspFileDataParser", "parseFileInfoRequestData error");
        }
        return cfzVar;
    }

    private void b(cfz cfzVar, cwd cwdVar) {
        String c = cwdVar.c();
        if (TextUtils.isEmpty(c)) {
            return;
        }
        int intValue = Integer.valueOf(cwdVar.e(), 16).intValue();
        if (intValue != 5) {
            if (intValue == 127) {
                cfzVar.e(CommonUtil.a(c, 16));
                return;
            } else {
                LogUtil.h("WspFileDataParser", "file id not find");
                return;
            }
        }
        try {
            c = new String(cvx.a(c), "utf-8");
        } catch (UnsupportedEncodingException unused) {
            LogUtil.b("WspFileDataParser", "byte to string exception");
        }
        if (c.contains(",")) {
            for (String str : c.split(",")) {
                LogUtil.c("WspFileDataParser", "log file name is:", str);
                cfzVar.i().add(str);
            }
            return;
        }
        cfzVar.i().add(c);
    }
}
