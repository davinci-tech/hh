package com.huawei.hihealthservice.store.merge;

import android.content.Context;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ijo;
import defpackage.ijt;
import defpackage.ikk;
import defpackage.ivx;
import health.compact.a.HiCommonUtil;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class HiHealthDataPointStressMerge implements HiMergeCommon {

    /* renamed from: a, reason: collision with root package name */
    private ijt f4207a = ijt.b();
    private ikk d;
    private ijo e;

    private boolean d(int i) {
        if (i == 2021) {
            return true;
        }
        switch (i) {
            case 2034:
            case 2035:
            case 2036:
            case 2037:
                return true;
            default:
                return false;
        }
    }

    public HiHealthDataPointStressMerge(Context context) {
        this.e = ijo.b(context);
        this.d = ikk.a(context);
    }

    @Override // com.huawei.hihealthservice.store.merge.HiMergeCommon
    public boolean merge(HiHealthData hiHealthData, int i, List<Integer> list) {
        boolean z;
        if (d(hiHealthData.getType())) {
            return c(hiHealthData, i, list);
        }
        if (!b(hiHealthData)) {
            return true;
        }
        List<HiHealthData> b = this.e.b(hiHealthData.getStartTime(), hiHealthData.getEndTime(), 2019, 2020, list);
        boolean z2 = false;
        if (HiCommonUtil.d(b)) {
            return this.e.b(hiHealthData, i, 0) > 0;
        }
        LogUtil.c("Debug_HiHealthDataPointStressMerge", "merge() get old data size = ", Integer.valueOf(b.size()));
        Iterator<HiHealthData> it = b.iterator();
        while (true) {
            if (!it.hasNext()) {
                z = true;
                break;
            }
            HiHealthData next = it.next();
            if (next.getClientId() == i) {
                if (next.getType() != 2019 ? !c(hiHealthData, next) : !d(hiHealthData, next)) {
                    next.setValue(hiHealthData.getValue());
                    next.setType(hiHealthData.getType());
                    next.putInt("merged", 0);
                    next.setSyncStatus(hiHealthData.getSyncStatus());
                    z = false;
                } else {
                    z = true;
                    z2 = true;
                }
            }
        }
        if (z2) {
            return true;
        }
        return b(hiHealthData, i, b, z);
    }

    private boolean b(HiHealthData hiHealthData, int i, List<HiHealthData> list, boolean z) {
        if (z) {
            hiHealthData.setClientId(i);
            hiHealthData.putInt("merged", 0);
            list.add(hiHealthData);
        }
        HiHealthData e = e(list);
        return b(this.e.e(e, e.getClientId(), 2019, 2020, 0), list);
    }

    private boolean b(boolean z, List<HiHealthData> list) {
        for (HiHealthData hiHealthData : list) {
            if (hiHealthData.getInt("merged") == 0) {
                boolean e = this.e.e(hiHealthData, hiHealthData.getClientId(), 2019, 2020, 1);
                LogUtil.c("Debug_HiHealthDataPointStressMerge", "stress pointDataMerge() insertOrUpdatePointData unmerge change = ", Boolean.valueOf(e));
                if (!e) {
                    z = false;
                }
            }
        }
        return z;
    }

    private boolean c(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
        return 2020 == hiHealthData.getType() && hiHealthData.getValue() <= hiHealthData2.getValue();
    }

    private boolean d(HiHealthData hiHealthData, HiHealthData hiHealthData2) {
        return 2020 == hiHealthData.getType() || hiHealthData.getValue() <= hiHealthData2.getValue();
    }

    private boolean b(HiHealthData hiHealthData) {
        return 2019 == hiHealthData.getType() || 2020 == hiHealthData.getType();
    }

    private HiHealthData e(List<HiHealthData> list) {
        int i;
        int i2 = 0;
        HiHealthData hiHealthData = list.get(0);
        while (i < list.size()) {
            HiHealthData hiHealthData2 = list.get(i);
            if (hiHealthData.getType() == hiHealthData2.getType()) {
                i = hiHealthData2.getValue() <= hiHealthData.getValue() ? i + 1 : 1;
                i2 = i;
                hiHealthData = hiHealthData2;
            } else {
                if (2019 != hiHealthData2.getType()) {
                }
                i2 = i;
                hiHealthData = hiHealthData2;
            }
        }
        return list.remove(i2);
    }

    private boolean c(HiHealthData hiHealthData, int i, List<Integer> list) {
        boolean c = this.e.c(hiHealthData, i, 0);
        if (c) {
            List<Integer> a2 = this.f4207a.a(hiHealthData.getStartTime(), hiHealthData.getEndTime(), hiHealthData.getType(), list);
            if (a2 == null || a2.size() < 2) {
                return true;
            }
            Collections.sort(a2, new ivx.a());
            if (a2.get(0).intValue() == i) {
                a2.remove(0);
                this.e.e(hiHealthData.getStartTime(), hiHealthData.getStartTime(), hiHealthData.getType(), a2, 1);
            } else {
                this.e.c(hiHealthData.getStartTime(), hiHealthData.getEndTime(), hiHealthData.getType(), i, 1);
            }
        }
        return c;
    }
}
