package defpackage;

import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class kaz {
    private static kaz d;
    private cwl e = new cwl();

    private kaz() {
    }

    public static kaz c() {
        if (d == null) {
            d = new kaz();
        }
        return d;
    }

    public int[] a(byte[] bArr) throws cwg {
        LogUtil.i("CommandUnpackage", "Enter getErrorCode()");
        String d2 = cvx.d(bArr);
        if (TextUtils.isEmpty(d2) || d2.length() <= 6) {
            return null;
        }
        int[] e = e(this.e.a(d2.substring(6, d2.length())));
        LogUtil.i("CommandUnpackage", "Error Code:", Integer.valueOf(e[0]));
        return e;
    }

    public static int[] e(cwe cweVar) {
        List<cwd> e = cweVar.e();
        int size = e.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            String c = e.get(i).c();
            int w = CommonUtil.w(e.get(i).e());
            if (w != 19) {
                if (w != 127) {
                    LogUtil.a("CommandUnpackage", "unTlvGetErrorCode default ");
                } else if (size > 0) {
                    iArr[0] = CommonUtil.w(c);
                }
            } else if (size > 1) {
                iArr[1] = CommonUtil.w(c);
            }
        }
        return iArr;
    }

    public static int[] b(cwe cweVar) {
        List<cwd> e = cweVar.e();
        int size = e.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            String c = e.get(i).c();
            int w = CommonUtil.w(e.get(i).e());
            if (w != 2) {
                if (w != 127) {
                    LogUtil.a("CommandUnpackage", "unTlvGetRequestFileResponse default");
                } else if (size > 0) {
                    iArr[0] = CommonUtil.w(c);
                }
            } else if (size > 1) {
                iArr[1] = CommonUtil.w(c);
            }
        }
        return iArr;
    }

    public ArrayList c(byte[] bArr) throws cwg {
        ArrayList arrayList = new ArrayList(16);
        LogUtil.i("CommandUnpackage", "unGetFileName enter...");
        String d2 = cvx.d(bArr);
        String[] a2 = (TextUtils.isEmpty(d2) || d2.length() <= 4) ? null : a(this.e.a(d2.substring(4, d2.length())));
        if (a2 != null) {
            for (String str : a2) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    public static String[] a(cwe cweVar) {
        List<cwd> e = cweVar.e();
        String[] strArr = null;
        for (int i = 0; i < e.size(); i++) {
            String c = e.get(i).c();
            if (c != null) {
                String e2 = cvx.e(c);
                if (!TextUtils.isEmpty(e2)) {
                    strArr = e2.split(";");
                }
            }
        }
        return strArr;
    }

    public List<jvp> h(byte[] bArr) throws cwg {
        String d2 = cvx.d(bArr);
        LogUtil.a("CommandUnpackage", "unGetGPSFileName info ", d2);
        if (TextUtils.isEmpty(d2) || d2.length() <= 4) {
            return null;
        }
        String substring = d2.substring(4, d2.length());
        cwe a2 = this.e.a(substring);
        LogUtil.a("CommandUnpackage", "unGetGPSFileName substring:", substring, "unGetGPSFileName tlvFather:", Integer.valueOf(a2.a().size()), " unGetGPSFileName tlv: ", Integer.valueOf(a2.e().size()));
        return j(a2);
    }

    public static List<jvp> j(cwe cweVar) {
        ArrayList arrayList = new ArrayList(16);
        List<cwe> a2 = cweVar.a();
        LogUtil.a("CommandUnpackage", "unGetGPSFileName tlvFathers: ", Integer.valueOf(a2.size()));
        Iterator<cwe> it = a2.iterator();
        while (it.hasNext()) {
            List<cwe> a3 = it.next().a();
            LogUtil.a("CommandUnpackage", "unGetGPSFileName tlvFatherss: ", Integer.valueOf(a3.size()));
            Iterator<cwe> it2 = a3.iterator();
            while (it2.hasNext()) {
                List<cwd> e = it2.next().e();
                jvp jvpVar = new jvp();
                Iterator<cwd> it3 = e.iterator();
                while (it3.hasNext()) {
                    c(arrayList, jvpVar, it3.next());
                }
            }
        }
        return arrayList;
    }

    private static void c(List<jvp> list, jvp jvpVar, cwd cwdVar) {
        String c = cwdVar.c();
        LogUtil.a("CommandUnpackage", "unTLVGPSFileName() TAG ", Integer.valueOf(CommonUtil.w(cwdVar.e())));
        int w = CommonUtil.w(cwdVar.e());
        if (w == 9) {
            jvpVar.d(CommonUtil.w(c));
            return;
        }
        if (w == 10) {
            jvpVar.d(CommonUtil.w(c));
            return;
        }
        if (w != 13) {
            if (w == 14) {
                jvpVar.c(CommonUtil.w(c));
                return;
            } else {
                LogUtil.a("CommandUnpackage", "unTlvGpsFileName default ");
                return;
            }
        }
        if (c != null) {
            String e = cvx.e(c);
            LogUtil.a("CommandUnpackage", "unTLVGPSFileName() nameListsStr ", e);
            if (e == null) {
                return;
            }
            if (new jvy().e()) {
                String[] split = e.split(";");
                jvpVar.e(split[0]);
                if (split.length == 2) {
                    jvpVar.a(split[1]);
                }
            } else {
                jvpVar.e(e);
            }
            list.add(jvpVar);
        }
    }

    public kbb g(byte[] bArr) throws cwg {
        LogUtil.i("CommandUnpackage", "unGetMaintenanceParameters enter...");
        String d2 = cvx.d(bArr);
        if (TextUtils.isEmpty(d2) || d2.length() <= 4) {
            return null;
        }
        return d(this.e.a(d2.substring(4, d2.length())));
    }

    public static kbb d(cwe cweVar) {
        kbb kbbVar = new kbb();
        List<cwd> e = cweVar.e();
        if (e != null && e.size() > 0) {
            for (cwd cwdVar : e) {
                int w = CommonUtil.w(cwdVar.e());
                String c = cwdVar.c();
                if (w == 1) {
                    kbbVar.b(cvx.e(c));
                } else if (w == 2) {
                    kbbVar.b(CommonUtil.w(c) == 1);
                } else if (w == 3) {
                    kbbVar.a(CommonUtil.w(c));
                } else if (w == 4) {
                    kbbVar.c(CommonUtil.w(c));
                } else if (w == 5) {
                    kbbVar.e(CommonUtil.w(c));
                } else {
                    LogUtil.a("CommandUnpackage", "unGetMaintParameters default ");
                }
            }
        }
        return kbbVar;
    }

    public kay i(byte[] bArr) throws cwg {
        kay kayVar;
        String d2 = cvx.d(bArr);
        if (!TextUtils.isEmpty(d2) && d2.length() > 4) {
            try {
                kayVar = c(this.e.a(d2.substring(4, d2.length())));
            } catch (NumberFormatException unused) {
                LogUtil.b("CommandUnpackage", "unQueryFileInformation NumberFormatException.");
            }
            LogUtil.i("CommandUnpackage", "unQueryFileInformation support_response ");
            return kayVar;
        }
        kayVar = null;
        LogUtil.i("CommandUnpackage", "unQueryFileInformation support_response ");
        return kayVar;
    }

    public static kay c(cwe cweVar) {
        kay kayVar = new kay();
        List<cwd> e = cweVar.e();
        if (e != null && e.size() > 0) {
            for (cwd cwdVar : e) {
                int w = CommonUtil.w(cwdVar.e());
                String c = cwdVar.c();
                if (w == 2) {
                    kayVar.d(Long.parseLong(c, 16));
                } else if (w == 3) {
                    kayVar.b(Long.parseLong(c, 16));
                } else if (w == 4) {
                    kayVar.e(CommonUtil.w(c));
                } else if (w == 5) {
                    kayVar.e(Long.parseLong(c, 16));
                } else if (w == 6) {
                    kayVar.c(CommonUtil.w(c));
                } else {
                    LogUtil.a("CommandUnpackage", "unQueryFileInformation default ");
                }
            }
        }
        return kayVar;
    }

    public int[] b(byte[] bArr) throws cwg {
        LogUtil.i("CommandUnpackage", "Enter getAckCode()");
        String d2 = cvx.d(bArr);
        if (TextUtils.isEmpty(d2) || d2.length() <= 4) {
            return null;
        }
        int[] e = e(this.e.a(d2.substring(4, d2.length())));
        LogUtil.i("CommandUnpackage", "Error Code:", Integer.valueOf(e[0]));
        return e;
    }

    public int[] d(byte[] bArr) throws cwg {
        LogUtil.i("CommandUnpackage", "Enter getAckCodeExt()");
        String d2 = cvx.d(bArr);
        if (TextUtils.isEmpty(d2) || d2.length() <= 4) {
            return null;
        }
        int[] b = b(this.e.a(d2.substring(4, d2.length())));
        if (b == null || b.length <= 1) {
            return b;
        }
        LogUtil.i("CommandUnpackage", "getAckCodeExt Error Code:", Integer.valueOf(b[0]), "  apply offset ", Integer.valueOf(b[1]));
        return b;
    }

    public kbe j(byte[] bArr) throws cwg {
        LogUtil.i("CommandUnpackage", "unGPSApplyDataFromDevice enter... data.length ", Integer.valueOf(bArr.length));
        kbe kbeVar = new kbe();
        String d2 = cvx.d(bArr);
        return (TextUtils.isEmpty(d2) || bArr.length < 4) ? kbeVar : d(d2.substring(4, d2.length()));
    }

    private kbe d(String str) {
        LogUtil.i("CommandUnpackage", "unGPSApplyData ,hexString.length() =  " + str.length());
        kbe kbeVar = new kbe();
        if (str.length() > 2) {
            int w = CommonUtil.w(str.substring(0, 2));
            String substring = str.substring(2, str.length());
            LogUtil.i("CommandUnpackage", "unGPSApplyData ,index =", Integer.valueOf(w), " unGPSApplyData ,value =", substring);
            kbeVar.c(w);
            kbeVar.c(cvx.a(substring));
        }
        return kbeVar;
    }

    public kbc e(byte[] bArr) throws cwg {
        if (bArr == null) {
            return new kbc();
        }
        return bArr.length >= 4 ? f(bArr) : new kbc();
    }

    private kbc f(byte[] bArr) {
        kbc kbcVar = new kbc();
        if (bArr.length > 3) {
            byte b = bArr[2];
            byte[] copyOfRange = Arrays.copyOfRange(bArr, 3, bArr.length);
            kbcVar.e(b);
            kbcVar.c(copyOfRange);
        }
        return kbcVar;
    }
}
