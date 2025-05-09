package defpackage;

import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class puk {
    public static List<pus> d() {
        ArrayList arrayList = new ArrayList(10);
        e(arrayList);
        return arrayList;
    }

    public static int e(List<pus> list) {
        int d = d(list, "");
        LogUtil.a("DataMediaFilesHelper", "downloadWorkoutMediaFiles totalLength:", Integer.valueOf(d));
        return d;
    }

    private static int d(List<pus> list, String str) {
        LogUtil.a("DataMediaFilesHelper", "parseMultiLanguageLength 1");
        int i = 0;
        for (pur purVar : pup.d(pul.a(str))) {
            if (!pul.c(purVar.b(), pul.e(pul.c(purVar.b())), purVar.d())) {
                LogUtil.a("DataMediaFilesHelper", "parseMultiLanguageLength 2");
                i = (int) (i + purVar.a());
                if (list != null) {
                    pus pusVar = new pus();
                    pusVar.b(purVar.d());
                    pusVar.e(pul.d(purVar.b()));
                    pusVar.c(purVar.a());
                    list.add(pusVar);
                }
            }
        }
        return i;
    }
}
