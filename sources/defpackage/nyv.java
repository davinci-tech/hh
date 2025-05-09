package defpackage;

import com.huawei.ui.device.declaration.xmlparser.DeclarationConstants;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes6.dex */
public class nyv {
    public nyu c(nzf nzfVar) {
        if (nzfVar == null) {
            LogUtil.c("DeclarationConverter", "declaration is null");
            return new nyu();
        }
        nyu nyuVar = new nyu();
        nzd b = nzfVar.b();
        if (b != null) {
            nyuVar.b(b.b());
        } else {
            LogUtil.c("DeclarationConverter", "content is null");
        }
        nzj n = nzfVar.n();
        if (n != null) {
            DeclarationConstants.Position c = n.c();
            nyuVar.a(DeclarationConstants.Position.END.compareTo(c) == 0);
            nyuVar.d(DeclarationConstants.Position.START.compareTo(c) == 0);
            nyuVar.e(n.e());
            nyuVar.f(n.d());
        } else {
            LogUtil.c("DeclarationConverter", "title is null");
        }
        nyuVar.b(nzfVar.g());
        nyuVar.d(nzfVar.a());
        nyuVar.d(1);
        nyuVar.i(nzfVar.c());
        nyuVar.e(nzfVar.e());
        nyuVar.b(nzfVar.h());
        nyuVar.a(nzfVar.d());
        nyuVar.c(nzfVar.i());
        return nyuVar;
    }

    public List<nyu> c(List<nzf> list) {
        if (list == null || list.size() < 1) {
            return new ArrayList(0);
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (nzf nzfVar : list) {
            if (nzfVar != null) {
                arrayList.add(c(nzfVar));
            }
        }
        nuj.b(arrayList);
        return arrayList;
    }
}
