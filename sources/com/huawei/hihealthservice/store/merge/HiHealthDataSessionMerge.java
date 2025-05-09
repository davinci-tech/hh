package com.huawei.hihealthservice.store.merge;

import android.content.Context;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.iil;
import defpackage.iis;
import defpackage.ijn;
import defpackage.ivx;
import health.compact.a.HiCommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class HiHealthDataSessionMerge implements HiMergeCommon {
    private ijn c;

    public HiHealthDataSessionMerge(Context context) {
        this.c = ijn.a(context);
    }

    @Override // com.huawei.hihealthservice.store.merge.HiMergeCommon
    public boolean merge(HiHealthData hiHealthData, int i, List<Integer> list) {
        if (hiHealthData.getMergedStatus() != 999) {
            return e(hiHealthData, i, list);
        }
        List<HiHealthData> d = this.c.d(hiHealthData.getStartTime(), hiHealthData.getEndTime(), list);
        if (HiCommonUtil.d(d)) {
            return hiHealthData.getSyncStatus() == 2 || this.c.d(hiHealthData, i, 0) > 0;
        }
        Iterator<HiHealthData> it = d.iterator();
        while (true) {
            if (it.hasNext()) {
                HiHealthData next = it.next();
                if (next.getClientId() == i) {
                    next.setType(hiHealthData.getType());
                    next.putInt("merged", 0);
                    next.setSyncStatus(hiHealthData.getSyncStatus());
                    break;
                }
            } else if (hiHealthData.getSyncStatus() != 2) {
                hiHealthData.putInt("merged", 0);
                hiHealthData.setClientId(i);
                d.add(hiHealthData);
            }
        }
        Collections.sort(d, new ivx.e());
        HiHealthData hiHealthData2 = d.get(0);
        boolean e = this.c.e(hiHealthData2, hiHealthData2.getClientId(), 0);
        for (int i2 = 1; i2 < d.size(); i2++) {
            HiHealthData hiHealthData3 = d.get(i2);
            if (hiHealthData3.getInt("merged") == 0) {
                boolean e2 = this.c.e(hiHealthData3, hiHealthData3.getClientId(), 1);
                LogUtil.c("Debug_HiHealthDataSessionMerge", "healthSessionDataMerge() insertOrUpdateSessionData unmerge change = ", Boolean.valueOf(e2));
                if (!e2) {
                    e = false;
                }
            }
        }
        return e;
    }

    public boolean e(HiHealthData hiHealthData, int i, List<Integer> list) {
        long j;
        if (hiHealthData.getMergedStatus() != 2) {
            if (this.c.e(hiHealthData.getStartTime(), hiHealthData.getEndTime(), i)) {
                j = this.c.a(hiHealthData, hiHealthData.getMergedStatus());
            } else {
                j = this.c.d(hiHealthData, i, hiHealthData.getMergedStatus());
            }
            if (!iil.a(j)) {
                ReleaseLogUtil.c("Debug_HiHealthDataSessionMerge", "sessionData insert fail", hiHealthData);
                return false;
            }
        } else {
            j = 1;
        }
        List<HiHealthData> d = this.c.d(hiHealthData.getStartTime(), hiHealthData.getEndTime(), list);
        if (HiCommonUtil.d(d)) {
            return true;
        }
        if (hiHealthData.getMergedStatus() != 2 && d.size() < 2) {
            return true;
        }
        Collections.sort(d, new ivx.e());
        HiHealthData hiHealthData2 = d.get(0);
        if (hiHealthData2.getMergedStatus() != 0) {
            hiHealthData2.setSyncStatus(0);
            j = this.c.d(hiHealthData2, 0);
        }
        boolean a2 = iil.a(j);
        for (int i2 = 1; i2 < d.size(); i2++) {
            HiHealthData hiHealthData3 = d.get(i2);
            if (hiHealthData3.getInt("merged") == 0) {
                hiHealthData3.setSyncStatus(0);
                if (!iil.a(this.c.d(hiHealthData3, 1))) {
                    a2 = false;
                }
            }
        }
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            iis.d().l(it.next().intValue());
        }
        return a2;
    }
}
