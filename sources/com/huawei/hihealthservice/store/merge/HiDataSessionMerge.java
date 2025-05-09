package com.huawei.hihealthservice.store.merge;

import android.content.Context;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.iic;
import defpackage.ijc;
import defpackage.ivx;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class HiDataSessionMerge implements HiMergeCommon {
    private ijc d;

    public HiDataSessionMerge(Context context) {
        this.d = ijc.d(context);
    }

    @Override // com.huawei.hihealthservice.store.merge.HiMergeCommon
    public boolean merge(HiHealthData hiHealthData, int i, List<Integer> list) {
        List<HiHealthData> c = this.d.c(hiHealthData.getStartTime(), hiHealthData.getEndTime(), list);
        if (c == null || c.isEmpty()) {
            return this.d.b(hiHealthData, i, 0) > 0;
        }
        Iterator<HiHealthData> it = c.iterator();
        while (true) {
            if (it.hasNext()) {
                HiHealthData next = it.next();
                if (next.getClientId() == i) {
                    if (iic.d(hiHealthData.getType(), next.getType()) <= 0) {
                        LogUtil.c("Debug_HiDataSessionMerge", "sessionDataMerge() insertData priority is lower");
                        return true;
                    }
                    LogUtil.c("Debug_HiDataSessionMerge", "sessionDataMerge() merged has happen  type from  ", Integer.valueOf(next.getType()), ",to ", Integer.valueOf(hiHealthData.getType()));
                    next.setType(hiHealthData.getType());
                    next.putInt("merged", 0);
                    next.setSyncStatus(hiHealthData.getSyncStatus());
                }
            } else {
                hiHealthData.setClientId(i);
                hiHealthData.putInt("merged", 0);
                c.add(hiHealthData);
                break;
            }
        }
        Collections.sort(c, new ivx.k());
        HiHealthData hiHealthData2 = c.get(0);
        boolean a2 = this.d.a(hiHealthData2, hiHealthData2.getClientId(), 0);
        for (int i2 = 1; i2 < c.size(); i2++) {
            HiHealthData hiHealthData3 = c.get(i2);
            if (hiHealthData3.getInt("merged") == 0 && !this.d.a(hiHealthData3, hiHealthData3.getClientId(), 1)) {
                a2 = false;
            }
        }
        return a2;
    }
}
