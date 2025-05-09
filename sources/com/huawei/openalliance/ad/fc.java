package com.huawei.openalliance.ad;

import android.content.Context;
import com.huawei.openalliance.ad.db.bean.PlacementRecord;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public class fc extends ep implements fy {
    private static final byte[] c = new byte[0];
    private static final byte[] d = new byte[0];
    private static fc e;

    @Override // com.huawei.openalliance.ad.fy
    public void b(String str) {
        a(PlacementRecord.class, fi.PLACEMENT_BY_ID_WHERE, new String[]{str});
    }

    @Override // com.huawei.openalliance.ad.fy
    public void b(PlacementRecord placementRecord) {
        if (placementRecord == null) {
            return;
        }
        synchronized (d) {
            String a2 = placementRecord.a();
            if (a(a2) != null) {
                a(PlacementRecord.class, placementRecord.d(this.f6846a), fi.PLACEMENT_BY_ID_WHERE, new String[]{a2});
            } else {
                a(placementRecord);
            }
        }
    }

    public void a(PlacementRecord placementRecord) {
        a(PlacementRecord.class, placementRecord.d(this.f6846a));
    }

    @Override // com.huawei.openalliance.ad.fy
    public List<String> a() {
        return a(a(PlacementRecord.class, new String[]{"contentId"}, null, null, null, null));
    }

    @Override // com.huawei.openalliance.ad.fy
    public PlacementRecord a(String str) {
        List a2 = a(PlacementRecord.class, null, fi.PLACEMENT_BY_ID_WHERE, new String[]{str}, null, null);
        if (a2.isEmpty()) {
            return null;
        }
        return (PlacementRecord) a2.get(0);
    }

    private List<String> a(List<PlacementRecord> list) {
        if (list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<PlacementRecord> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().a());
        }
        return arrayList;
    }

    public static fc a(Context context) {
        fc fcVar;
        synchronized (c) {
            if (e == null) {
                e = new fc(context);
            }
            fcVar = e;
        }
        return fcVar;
    }

    protected fc(Context context) {
        super(context);
    }
}
