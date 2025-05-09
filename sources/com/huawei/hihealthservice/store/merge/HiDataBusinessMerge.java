package com.huawei.hihealthservice.store.merge;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiRelationInformation;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.iir;
import defpackage.iiv;
import defpackage.ivx;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class HiDataBusinessMerge implements HiMergeCommon {

    /* renamed from: a, reason: collision with root package name */
    private Context f4201a;
    private iiv b = iiv.d();
    private iir c = iir.d();

    public HiDataBusinessMerge(Context context) {
        this.f4201a = context.getApplicationContext();
    }

    @Override // com.huawei.hihealthservice.store.merge.HiMergeCommon
    public boolean merge(HiHealthData hiHealthData, int i, List<Integer> list) {
        List list2 = (List) new Gson().fromJson(hiHealthData.getRelationInformations(), new TypeToken<List<HiRelationInformation>>() { // from class: com.huawei.hihealthservice.store.merge.HiDataBusinessMerge.5
        }.getType());
        if (hiHealthData.getRelationFlag() && list2.isEmpty()) {
            ReleaseLogUtil.d("Debug_HiDataBusinessMerge", "RelationFlag is true, but relationInformation is empty!");
            return false;
        }
        long d = d(hiHealthData, i);
        if (d < 0) {
            return false;
        }
        boolean z = true;
        int i2 = 0;
        while (i2 < list2.size()) {
            long j = d;
            if (this.c.b(hiHealthData.getType(), (int) d, ((HiRelationInformation) list2.get(i2)).c(), ((HiRelationInformation) list2.get(i2)).b()) < 0) {
                z = false;
            }
            i2++;
            d = j;
        }
        return z;
    }

    private long d(HiHealthData hiHealthData, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(i));
        List<HiHealthData> d = this.b.d(hiHealthData.getStartTime(), hiHealthData.getEndTime(), hiHealthData.getType(), arrayList);
        if (d == null || d.isEmpty()) {
            return this.b.b(hiHealthData, i, 0);
        }
        Iterator<HiHealthData> it = d.iterator();
        while (true) {
            if (it.hasNext()) {
                HiHealthData next = it.next();
                if (next.getClientId() == i) {
                    next.setValue(hiHealthData.getValue());
                    next.setMetaData(hiHealthData.getMetaData());
                    if (hiHealthData.getSyncStatus() != 1 || next.getInt("merged") != 2) {
                        next.putInt("merged", 0);
                    }
                    next.setSyncStatus(hiHealthData.getSyncStatus());
                }
            } else {
                hiHealthData.setClientId(i);
                hiHealthData.putInt("merged", 0);
                d.add(hiHealthData);
                break;
            }
        }
        d(d);
        HiHealthData hiHealthData2 = d.get(0);
        long d2 = this.b.d(hiHealthData2, hiHealthData2.getClientId(), 0);
        if (d2 < 0) {
            return -1L;
        }
        for (int i2 = 1; i2 < d.size(); i2++) {
            HiHealthData hiHealthData3 = d.get(i2);
            if (hiHealthData3.getInt("merged") == 0) {
                d2 = this.b.d(hiHealthData3, hiHealthData3.getClientId(), 1);
                LogUtil.c("Debug_HiDataBusinessMerge", "businessDataMerge() insertOrUpdatePointData unmerge change index = ", Long.valueOf(d2));
                if (d2 < 0) {
                    return -1L;
                }
            }
        }
        return d2;
    }

    private void d(List<HiHealthData> list) {
        Comparator iVar;
        switch (list.get(0).getType()) {
            case 61001:
            case 61002:
                iVar = new ivx.i();
                break;
            default:
                iVar = new ivx.j();
                break;
        }
        Collections.sort(list, iVar);
    }
}
