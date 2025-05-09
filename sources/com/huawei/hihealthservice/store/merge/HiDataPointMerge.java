package com.huawei.hihealthservice.store.merge;

import android.content.Context;
import com.huawei.hihealth.HiHealthData;
import defpackage.iil;
import defpackage.iis;
import defpackage.iiy;
import defpackage.ijc;
import defpackage.ivu;
import defpackage.ivx;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class HiDataPointMerge implements HiMergeCommon {

    /* renamed from: a, reason: collision with root package name */
    private ijc f4203a;
    private iiy d;
    private Context e;

    public HiDataPointMerge(Context context) {
        this.e = context.getApplicationContext();
        this.d = iiy.e(context);
        this.f4203a = ijc.d(context);
    }

    @Override // com.huawei.hihealthservice.store.merge.HiMergeCommon
    public boolean merge(HiHealthData hiHealthData, int i, List<Integer> list) {
        long startTime;
        long startTime2;
        List<Integer> e;
        if (hiHealthData.getMergedStatus() != 999) {
            return b(hiHealthData, i, list);
        }
        if (c(hiHealthData, i, 0) && (e = ivu.b(this.e, hiHealthData.getType()).e((startTime = hiHealthData.getStartTime() - 120000), (startTime2 = 120000 + hiHealthData.getStartTime()), hiHealthData.getType(), list)) != null && e.size() >= 2) {
            if (b(e) == i) {
                e.remove(0);
                this.d.c(startTime, startTime2, hiHealthData.getType(), e, 1);
            } else {
                this.d.c(hiHealthData.getStartTime(), hiHealthData.getEndTime(), hiHealthData.getType(), i, 1);
            }
        }
        return true;
    }

    private int b(List<Integer> list) {
        Collections.sort(list, new ivx.a());
        return list.get(0).intValue();
    }

    public boolean b(HiHealthData hiHealthData, int i, List<Integer> list) {
        long startTime;
        long startTime2;
        List<Integer> e;
        if (c(hiHealthData, i, hiHealthData.getMergedStatus()) && (e = ivu.b(this.e, hiHealthData.getType()).e((startTime = hiHealthData.getStartTime() - 120000), (startTime2 = 120000 + hiHealthData.getStartTime()), hiHealthData.getType(), list)) != null && e.size() >= 2) {
            List<HiHealthData> c = this.d.c(startTime, startTime2, new int[]{hiHealthData.getType()}, list);
            if (HiCommonUtil.d(c)) {
                return true;
            }
            int b = b(e);
            for (HiHealthData hiHealthData2 : c) {
                if (b == hiHealthData2.getClientId() && hiHealthData2.getMergedStatus() != 0) {
                    this.d.c(hiHealthData2.getDataId(), 0, 0);
                    this.f4203a.c(hiHealthData2, b, 0);
                }
                if (b != hiHealthData2.getClientId() && hiHealthData2.getMergedStatus() != 1) {
                    this.d.c(hiHealthData2.getDataId(), 0, 1);
                    this.f4203a.c(hiHealthData2, hiHealthData2.getClientId(), 0);
                }
            }
            Iterator<Integer> it = e.iterator();
            while (it.hasNext()) {
                iis.d().l(it.next().intValue());
            }
        }
        return true;
    }

    public boolean c(HiHealthData hiHealthData, int i, int i2) {
        int c = HiDateUtil.c(hiHealthData.getStartTime());
        boolean z = HiDateUtil.c(System.currentTimeMillis()) == c && 901 == hiHealthData.getType();
        if (hiHealthData.getType() >= 901 && hiHealthData.getType() <= 906) {
            hiHealthData.setStartTime(HiDateUtil.t(hiHealthData.getStartTime()));
            hiHealthData.setEndTime(HiDateUtil.f(hiHealthData.getEndTime()));
        }
        Double d = this.d.d(hiHealthData.getStartTime(), hiHealthData.getEndTime(), hiHealthData.getType(), i);
        if (d == null) {
            if (z) {
                ReleaseLogUtil.d("HiH_HiDataPointMerge", "saveSptDt insDev staDt=", Integer.valueOf(c), ",old=null");
            }
            return iil.a(this.d.c(hiHealthData, i, i2));
        }
        if (d.doubleValue() >= hiHealthData.getValue()) {
            return false;
        }
        if (hiHealthData.getSyncStatus() == 0) {
            this.f4203a.c(hiHealthData, i, 0);
        }
        if (z) {
            ReleaseLogUtil.e("HiH_HiDataPointMerge", "saveSptDt insDev staDt=", Integer.valueOf(c), ", old!=null");
        }
        return iil.a(this.d.a(hiHealthData, i, i2));
    }
}
