package com.huawei.hihealthservice.store.stat;

import android.content.Context;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.type.HiHealthDataType;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.ifl;
import defpackage.igo;
import defpackage.ijj;
import defpackage.ijt;
import defpackage.ikr;
import defpackage.iks;
import defpackage.isf;
import defpackage.iwg;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes7.dex */
public class HiHeartRateAndRestHeartRateStat extends HiStatCommon {
    private ijj b;
    private iks c;
    private ijt d;
    private ikr e;

    public HiHeartRateAndRestHeartRateStat(Context context) {
        super(context);
        this.d = ijt.b();
        this.e = ikr.b(this.mContext);
        this.c = iks.e();
        this.b = ijj.a(this.mContext);
    }

    @Override // com.huawei.hihealthservice.store.stat.HiStatCommon
    public boolean stat(HiHealthData hiHealthData) {
        if (hiHealthData == null) {
            return false;
        }
        LogUtil.c("Debug_HiHeartRateAndRestHeartRateStat", "stat()");
        return a(hiHealthData);
    }

    public void b(int i, List<Integer> list, long j, long j2) {
        if (list == null) {
            return;
        }
        LogUtil.c("Debug_HiHeartRateAndRestHeartRateStat", "statDBOldHeartRateData");
        ArrayList arrayList = new ArrayList(10);
        List<HiHealthData> c = ijj.a(this.mContext).c(list, j, j2, 2002);
        List<HiHealthData> c2 = ijj.a(this.mContext).c(list, j, j2, 2018);
        if (!CollectionUtils.d(c)) {
            arrayList.addAll(c);
        }
        if (!CollectionUtils.d(c2)) {
            arrayList.addAll(c2);
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ((HiHealthData) it.next()).setUserId(i);
        }
        isf a2 = isf.a(this.mContext);
        a2.prepareRealTimeHealthDataStat(arrayList);
        a2.doRealTimeHealthDataStat();
    }

    private boolean a(HiHealthData hiHealthData) {
        int userId = hiHealthData.getUserId();
        int e = this.e.e(0, userId, 0);
        if (e <= 0) {
            LogUtil.h("Debug_HiHeartRateAndRestHeartRateStat", "statHeartRateDataByUser()  statClient <= 0");
            return false;
        }
        List<Integer> a2 = this.c.a(userId);
        if (HiCommonUtil.d(a2)) {
            LogUtil.h("Debug_HiHeartRateAndRestHeartRateStat", "statHeartRateDataByUser()  statClients <= 0");
            return false;
        }
        long t = HiDateUtil.t(hiHealthData.getStartTime());
        long f = HiDateUtil.f(hiHealthData.getStartTime());
        igo igoVar = new igo();
        igoVar.d(t);
        igoVar.j(userId);
        igoVar.g(hiHealthData.getSyncStatus());
        igoVar.c(46015);
        igoVar.h(8);
        igoVar.b(e);
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setStartTime(t);
        hiDataReadOption.setEndTime(f);
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setCount(1);
        b(this.b.b(hiDataReadOption, 2105, a2, (ifl) null), igoVar);
        String[] strArr = {"avgRestingHeartRate", "maxHeartRate", "minHeartRate"};
        int[] iArr = {3, 4, 5};
        return a(this.b.b(hiDataReadOption, 2002, a2, (ifl) null), this.b.b(hiDataReadOption, 2018, a2, (ifl) null), igoVar) && c(this.d.b(a2, t, f, 3, 2002, strArr, iArr, 0), this.d.b(a2, t, f, 3, 2018, strArr, iArr, 0), igoVar);
    }

    private boolean c(List<HiHealthData> list, List<HiHealthData> list2, igo igoVar) {
        if (HiCommonUtil.d(list) && HiCommonUtil.d(list2)) {
            iwg.a(igoVar, HiHealthDataType.h());
            LogUtil.h("Debug_HiHeartRateAndRestHeartRateStat", "saveHeartRateStat()  statDatasOld and statDatasNew are null");
            return false;
        }
        if (HiCommonUtil.d(list) && !HiCommonUtil.d(list2)) {
            return d(igoVar, list2.get(0).getDouble("avgRestingHeartRate"), 46018) && d(igoVar, list2.get(0).getDouble("maxHeartRate"), 46016) && d(igoVar, list2.get(0).getDouble("minHeartRate"), 46017);
        }
        if (!HiCommonUtil.d(list) && HiCommonUtil.d(list2)) {
            iwg.a(igoVar, new int[]{46018});
            return d(igoVar, list.get(0).getDouble("maxHeartRate"), 46016) && d(igoVar, list.get(0).getDouble("minHeartRate"), 46017);
        }
        LogUtil.c("Debug_HiHeartRateAndRestHeartRateStat", "statDatasOld and statDatasNew is null or empty");
        HiHealthData hiHealthData = list.get(0);
        HiHealthData hiHealthData2 = list2.get(0);
        double d = hiHealthData.getDouble("maxHeartRate");
        double d2 = hiHealthData2.getDouble("maxHeartRate");
        double d3 = hiHealthData.getDouble("minHeartRate");
        double d4 = hiHealthData2.getDouble("minHeartRate");
        double d5 = hiHealthData2.getDouble("avgRestingHeartRate");
        if (d <= d2) {
            d = d2;
        }
        if (d3 < d4) {
            d4 = d3;
        }
        return d(igoVar, d5, 46018) && d(igoVar, d, 46016) && ((d4 > 0.5d ? 1 : (d4 == 0.5d ? 0 : -1)) > 0 ? d(igoVar, d4, 46017) : true);
    }

    private boolean a(List<HiHealthData> list, List<HiHealthData> list2, igo igoVar) {
        double value;
        if (HiCommonUtil.d(list) && HiCommonUtil.d(list2)) {
            LogUtil.h("Debug_HiHeartRateAndRestHeartRateStat", "saveLastHeartRateStat()  statLastDatas and StatLastDatas2 are null");
            return iwg.d(igoVar.e(), igoVar.d(), 46015, new int[]{46019});
        }
        if (!HiCommonUtil.d(list) && HiCommonUtil.d(list2)) {
            return d(igoVar, list.get(0).getValue(), 46019);
        }
        if (HiCommonUtil.d(list) && !HiCommonUtil.d(list2)) {
            return d(igoVar, list2.get(0).getValue(), 46019);
        }
        LogUtil.c("Debug_HiHeartRateAndRestHeartRateStat", "statLastDatas and statLastDatasRest is null or empty");
        if (list.get(0).getStartTime() > list2.get(0).getStartTime()) {
            value = list.get(0).getValue();
        } else {
            value = list2.get(0).getValue();
        }
        return d(igoVar, value, 46019);
    }

    private boolean b(List<HiHealthData> list, igo igoVar) {
        if (HiCommonUtil.d(list)) {
            LogUtil.h("Debug_HiHeartRateAndRestHeartRateStat", "saveLastRestHeartRateStat() statLastDatasRest is null");
            return iwg.d(igoVar.e(), igoVar.d(), 46015, new int[]{46020});
        }
        return d(igoVar, list.get(0).getValue(), 46020);
    }

    private boolean d(igo igoVar, double d, int i) {
        if (d <= 1.0E-6d) {
            LogUtil.h("Debug_HiHeartRateAndRestHeartRateStat", "saveOneStat()  statValue <= 0 statType = ", Integer.valueOf(i));
            return false;
        }
        igoVar.a(d);
        igoVar.d(i);
        return this.mDataStatManager.a(igoVar);
    }
}
