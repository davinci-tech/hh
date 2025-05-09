package defpackage;

import com.huawei.datatype.PayApduInfo;
import com.huawei.datatype.PayOpenChannelInfo;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;

/* loaded from: classes5.dex */
public class jkx {
    private cwl e = new cwl();

    public jky a(int i, byte[] bArr, int i2) {
        jky jkyVar = new jky();
        jkyVar.e(i);
        String d = cvx.d(bArr);
        if (d == null || d.length() < 4) {
            LogUtil.h("HwPayParseDevResponse", "parseOpenChannel messageHex is error");
            return jkyVar;
        }
        try {
            cwe a2 = this.e.a(d.substring(4, d.length()));
            PayOpenChannelInfo payOpenChannelInfo = new PayOpenChannelInfo();
            jkyVar.e(0);
            Integer num = null;
            for (cwd cwdVar : a2.e()) {
                int a3 = CommonUtil.a(cwdVar.e(), 16);
                if (a3 == 3) {
                    payOpenChannelInfo.setChannelId(CommonUtil.a(cwdVar.c(), 16));
                } else if (a3 == 4) {
                    payOpenChannelInfo.setApdu(cwdVar.c());
                } else if (a3 == 127) {
                    num = Integer.valueOf(i2);
                } else {
                    LogUtil.h("HwPayParseDevResponse", "parseOpenChannel default");
                }
            }
            if (num == null) {
                jkyVar.e(payOpenChannelInfo);
            } else {
                jkyVar.e(num);
            }
        } catch (cwg e) {
            LogUtil.b("HwPayParseDevResponse", "parseOpenChannel ", e.getMessage());
        }
        return jkyVar;
    }

    public jky d(int i, byte[] bArr, int i2) {
        jky jkyVar = new jky();
        jkyVar.e(i);
        String d = cvx.d(bArr);
        if (d == null || d.length() < 4) {
            LogUtil.h("HwPayParseDevResponse", "parseExecuteApdu messageHex is error");
            return jkyVar;
        }
        String substring = d.substring(4, d.length());
        try {
            PayApduInfo payApduInfo = new PayApduInfo();
            Integer num = null;
            for (cwd cwdVar : this.e.a(substring).e()) {
                int a2 = CommonUtil.a(cwdVar.e(), 16);
                if (a2 == 1) {
                    payApduInfo.setReqid(CommonUtil.a(cwdVar.c(), 16));
                } else if (a2 == 2) {
                    payApduInfo.setApdu(cwdVar.c());
                } else if (a2 == 3) {
                    payApduInfo.setChannelId(CommonUtil.a(cwdVar.c(), 16));
                } else if (a2 == 127) {
                    num = Integer.valueOf(i2);
                } else {
                    LogUtil.h("HwPayParseDevResponse", "parseExecuteApdu default");
                }
            }
            if (num == null) {
                jkyVar.e(0);
                jkyVar.e(payApduInfo);
            } else {
                jkyVar.e(num);
            }
        } catch (cwg e) {
            LogUtil.b("HwPayParseDevResponse", "parseExecuteApdu error : ", e.getMessage());
        }
        return jkyVar;
    }

    public jky b(int i, byte[] bArr, int i2) {
        jky jkyVar = new jky();
        jkyVar.e(i);
        String d = cvx.d(bArr);
        if (d == null || d.length() < 4) {
            LogUtil.h("HwPayParseDevResponse", "parseCloseChannel messageHex is error");
            return jkyVar;
        }
        try {
            for (cwd cwdVar : this.e.a(d.substring(4, d.length())).e()) {
                int a2 = CommonUtil.a(cwdVar.e(), 16);
                if (a2 == 2) {
                    jkyVar.e(0);
                    jkyVar.e(cwdVar.c());
                } else if (a2 == 127) {
                    jkyVar.e(Integer.valueOf(i2));
                } else {
                    LogUtil.h("HwPayParseDevResponse", "parseCloseChannel default");
                }
            }
        } catch (cwg e) {
            LogUtil.b("HwPayParseDevResponse", "parseCloseChannel error : ", e.getMessage());
        }
        return jkyVar;
    }
}
