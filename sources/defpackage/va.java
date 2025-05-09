package defpackage;

import android.content.Context;
import com.huawei.ads.adsrec.c0;
import com.huawei.ads.adsrec.db.table.MaterialSummaryRecord;
import com.huawei.ads.adsrec.e;
import com.huawei.ads.fund.util.ListUtil;
import com.huawei.openplatform.abl.log.HiAdLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes2.dex */
public class va implements c0 {

    /* renamed from: a, reason: collision with root package name */
    private final Map<String, Set<String>> f17627a = new HashMap();

    @Override // com.huawei.ads.adsrec.c0
    public void a(Context context, vt vtVar, vh vhVar) {
        if (context == null || vtVar == null || vhVar == null) {
            HiAdLog.w("SplashAdFilter", "valid args");
            return;
        }
        List<vg> j = vhVar.j();
        if (ListUtil.isEmpty(j)) {
            return;
        }
        d(context, vtVar);
        for (vg vgVar : j) {
            List<vb> a2 = vgVar.a();
            if (!ListUtil.isEmpty(a2)) {
                ArrayList arrayList = new ArrayList();
                for (vb vbVar : a2) {
                    if (vbVar != null && (vbVar.s() == 0 || c(vgVar.h(), vbVar.f()))) {
                        arrayList.add(vbVar);
                    }
                }
                vgVar.b(arrayList);
            }
        }
    }

    private boolean c(String str, String str2) {
        Set<String> set = this.f17627a.get(str);
        if (set != null && !set.isEmpty()) {
            return set.contains(str2);
        }
        HiAdLog.w("SplashAdFilter", "not such material with slot:%s", str);
        return false;
    }

    private boolean e(MaterialSummaryRecord materialSummaryRecord, int i) {
        materialSummaryRecord.i();
        materialSummaryRecord.c();
        if (materialSummaryRecord.i() <= 0 || materialSummaryRecord.c() <= 0) {
            return false;
        }
        return i == 1 ? materialSummaryRecord.i() <= materialSummaryRecord.c() : materialSummaryRecord.i() > materialSummaryRecord.c();
    }

    private void d(Context context, vt vtVar) {
        String str;
        Integer n = vtVar.n();
        if (n == null) {
            str = "orientation empty";
        } else {
            List<MaterialSummaryRecord> b = new e(context).b(vtVar.a(), String.valueOf(vtVar.h()));
            if (!ListUtil.isEmpty(b)) {
                for (MaterialSummaryRecord materialSummaryRecord : b) {
                    if (e(materialSummaryRecord, n.intValue())) {
                        String e = materialSummaryRecord.e();
                        Set<String> set = this.f17627a.get(e);
                        if (set == null) {
                            set = new HashSet<>();
                            this.f17627a.put(e, set);
                        }
                        set.add(materialSummaryRecord.a());
                    }
                }
                return;
            }
            str = "materialSummaryRecords empty";
        }
        HiAdLog.w("SplashAdFilter", str);
    }
}
