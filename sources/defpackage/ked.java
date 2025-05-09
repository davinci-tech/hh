package defpackage;

import com.huawei.haf.common.exception.ExceptionUtils;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class ked {
    private static int[] c(cwe cweVar) {
        List<cwd> e = cweVar.e();
        int size = e.size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            String c = e.get(i).c();
            if (CommonUtil.w(e.get(i).e()) != 127) {
                ReleaseLogUtil.d("BTSYNC_StressUnPackage", "unTlvGetErrorCode enter default branch");
            } else if (size > 0) {
                iArr[0] = CommonUtil.w(c);
            }
        }
        return iArr;
    }

    public static int c(byte[] bArr) {
        if (bArr != null) {
            try {
                if (bArr.length >= 4) {
                    String d = cvx.d(bArr);
                    int[] c = c(new cwl().a(d.substring(4, d.length())));
                    if (c.length <= 0) {
                        ReleaseLogUtil.d("BTSYNC_StressUnPackage", "errorCodeArray is invalid");
                        return 0;
                    }
                    int i = c[0];
                    if (i == 100000) {
                        return 0;
                    }
                    return i;
                }
            } catch (cwg e) {
                ReleaseLogUtil.c("BTSYNC_StressUnPackage", "getErrorCode tlvException:", ExceptionUtils.d(e));
                return 0;
            }
        }
        ReleaseLogUtil.d("BTSYNC_StressUnPackage", "data is invalid");
        return -1;
    }

    public static kdu a(byte[] bArr) {
        kdu kduVar = new kdu();
        if (bArr == null) {
            return kduVar;
        }
        String d = cvx.d(bArr);
        if (d.length() > 4) {
            String substring = d.substring(4, d.length());
            ArrayList arrayList = new ArrayList(10);
            try {
                List<cwe> a2 = new cwl().a(substring).a();
                if (a2 != null && !a2.isEmpty()) {
                    b(kduVar, arrayList, a2);
                }
                kduVar.d(arrayList);
            } catch (cwg e) {
                ReleaseLogUtil.c("BTSYNC_StressUnPackage", "unPackageStressRecordDetail tlvException:", ExceptionUtils.d(e));
            }
        }
        return kduVar;
    }

    private static void b(kdu kduVar, List<Integer> list, List<cwe> list2) {
        Iterator<cwe> it = list2.iterator();
        while (it.hasNext()) {
            for (cwd cwdVar : it.next().e()) {
                int w = CommonUtil.w(cwdVar.e());
                if (w == 2) {
                    kduVar.e(CommonUtil.w(cwdVar.c()));
                } else if (w == 5) {
                    list.add(Integer.valueOf(CommonUtil.w(cwdVar.c())));
                } else {
                    ReleaseLogUtil.d("BTSYNC_StressUnPackage", "unPackageStressFrameIndexList enter default branch");
                }
            }
        }
    }

    public static kdw d(byte[] bArr) {
        kdw kdwVar = new kdw();
        if (bArr == null) {
            return kdwVar;
        }
        String d = cvx.d(bArr);
        ArrayList arrayList = new ArrayList(10);
        if (d.length() > 4) {
            try {
                e(kdwVar, arrayList, new cwl().a(d.substring(4, d.length())).a());
                kdwVar.b(arrayList);
            } catch (cwg unused) {
                ReleaseLogUtil.c("BTSYNC_StressUnPackage", "unPackageStressRecordDetail tlvException");
            }
        }
        return kdwVar;
    }

    private static void e(kdw kdwVar, List<kdx> list, List<cwe> list2) {
        if (list2 == null || list2.size() <= 0) {
            return;
        }
        for (cwe cweVar : list2) {
            d(kdwVar, cweVar.e());
            c(list, cweVar);
        }
    }

    private static void d(kdw kdwVar, List<cwd> list) {
        for (cwd cwdVar : list) {
            if (CommonUtil.w(cwdVar.e()) == 2) {
                kdwVar.c(CommonUtil.w(cwdVar.c()));
            } else {
                ReleaseLogUtil.d("BTSYNC_StressUnPackage", "for enter default branch");
            }
        }
    }

    private static void c(List<kdx> list, cwe cweVar) {
        Iterator<cwe> it = cweVar.a().iterator();
        while (it.hasNext()) {
            List<cwd> e = it.next().e();
            kdx kdxVar = new kdx();
            for (cwd cwdVar : e) {
                int w = CommonUtil.w(cwdVar.e());
                if (w == 4) {
                    kdxVar.c(CommonUtil.w(cwdVar.c()));
                } else if (w == 5) {
                    kdxVar.b(CommonUtil.w(cwdVar.c()));
                } else if (w == 6) {
                    kdxVar.a(CommonUtil.w(cwdVar.c()));
                } else {
                    ReleaseLogUtil.d("BTSYNC_StressUnPackage", "unPackageStressRecordDetail enter default branch");
                }
            }
            list.add(kdxVar);
        }
    }

    public static kdr e(byte[] bArr) {
        kdr kdrVar = new kdr();
        if (bArr == null) {
            return kdrVar;
        }
        String d = cvx.d(bArr);
        if (d.length() > 4) {
            String substring = d.substring(4, d.length());
            ArrayList arrayList = new ArrayList(10);
            try {
                List<cwe> a2 = new cwl().a(substring).a();
                if (a2 != null && !a2.isEmpty()) {
                    b(kdrVar, arrayList, a2);
                }
                kdrVar.e(arrayList);
            } catch (cwg e) {
                ReleaseLogUtil.c("BTSYNC_StressUnPackage", "unPackageRelaxFrameIndexList tlvException:", ExceptionUtils.d(e));
            }
        }
        return kdrVar;
    }

    private static void b(kdr kdrVar, List<Integer> list, List<cwe> list2) {
        Iterator<cwe> it = list2.iterator();
        while (it.hasNext()) {
            for (cwd cwdVar : it.next().e()) {
                int w = CommonUtil.w(cwdVar.e());
                if (w == 2) {
                    kdrVar.e(CommonUtil.w(cwdVar.c()));
                } else if (w == 5) {
                    list.add(Integer.valueOf(CommonUtil.w(cwdVar.c())));
                } else {
                    ReleaseLogUtil.d("BTSYNC_StressUnPackage", "unPackageRelaxFrameIndexList enter default branch");
                }
            }
        }
    }

    public static kdp b(byte[] bArr) {
        return bArr == null ? new kdp() : kdy.e(bArr);
    }
}
