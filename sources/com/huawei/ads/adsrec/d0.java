package com.huawei.ads.adsrec;

import com.huawei.ads.adsrec.db.table.AdContentRspRecord;
import com.huawei.ads.adsrec.db.table.AdIECImpRecord;
import com.huawei.ads.adsrec.db.table.MaterialSummaryRecord;
import defpackage.vb;
import defpackage.vg;
import defpackage.wk;
import java.util.List;
import org.json.JSONArray;

/* loaded from: classes2.dex */
public interface d0 {
    List<AdIECImpRecord> a(String str);

    List<vb> a(wk wkVar);

    vg a(String str, String str2);

    void a(AdContentRspRecord adContentRspRecord);

    void a(List<String[]> list);

    void a(List<vg> list, int i);

    void a(JSONArray jSONArray);

    AdContentRspRecord b(String str);

    List<MaterialSummaryRecord> b(String str, String str2);

    void b(List<String[]> list);

    void c(List<vg> list);
}
