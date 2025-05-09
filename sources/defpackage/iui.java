package defpackage;

import android.content.Context;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealthservice.sync.dataswitch.HealthDataSwitch;
import com.huawei.hihealthservice.sync.syncdata.HiSyncBase;
import com.huawei.hwcloudmodel.healthdatacloud.HealthDataCloudFactory;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.HiCommonUtil;
import health.compact.a.HiDateUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes7.dex */
public class iui implements HiSyncBase {

    /* renamed from: a, reason: collision with root package name */
    private iiv f13615a;
    private isf b;
    private HealthDataSwitch d;
    private Context e;
    private int f;
    private HealthDataCloudFactory g;
    private HiSyncOption i;
    private ijr j;
    private int k;
    private Map<Integer, Long> n;
    private int o;
    private List<Integer> l = new ArrayList();
    private boolean h = false;
    private boolean c = false;

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pullDataByTime(long j, long j2) throws iut {
    }

    public iui(Context context, HiSyncOption hiSyncOption, int i) throws iut {
        this.e = context;
        this.i = hiSyncOption;
        this.f = i;
        c();
    }

    private void c() throws iut {
        if (this.e == null) {
            LogUtil.b("Debug_HiSyncBusinessData", "HiSyncBusinessData init context is null");
            return;
        }
        this.h = iuz.i();
        this.j = ijr.d();
        this.f13615a = iiv.d();
        this.b = isf.a(this.e);
        this.g = jbs.a(this.e).d();
        this.d = new HealthDataSwitch(this.e);
        this.l = b();
        this.n = ity.a(this.e).c(this.l);
    }

    private List<Integer> b() {
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(5000);
        arrayList.add(5001);
        return arrayList;
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pullDataByVersion() throws iut {
        LogUtil.a("Debug_HiSyncBusinessData", "pullBusinessDataByVersion() begin !");
        Map<Integer, Long> map = this.n;
        if (map == null || map.isEmpty()) {
            LogUtil.h("Debug_HiSyncBusinessData", "pullDataByVersion() keys is null,stop pullDataByVersion");
        } else {
            c(this.n);
        }
        LogUtil.a("Debug_HiSyncBusinessData", "pullBusinessDataByVersion() end !");
    }

    private void c(Map<Integer, Long> map) throws iut {
        for (Map.Entry<Integer, Long> entry : map.entrySet()) {
            int intValue = entry.getKey().intValue();
            long longValue = entry.getValue().longValue();
            LogUtil.c("Debug_HiSyncBusinessData", "downloadOneTypeDataByVersion type is ", Integer.valueOf(intValue), ", maxVersion is ", Long.valueOf(longValue));
            if (longValue <= 0) {
                LogUtil.h("Debug_HiSyncBusinessData", "downloadOneTypeDataByVersion cloud has no such data, type is ", Integer.valueOf(intValue));
            } else {
                iub iubVar = new iub();
                iubVar.d(intValue);
                igq c = this.j.c(this.f, 0L, intValue);
                if (c == null) {
                    LogUtil.c("Debug_HiSyncBusinessData", " syncAnchorTable is null");
                    iubVar.e(0L);
                    b(iubVar, longValue);
                } else if (c.a() < longValue) {
                    iubVar.e(c.a());
                    b(iubVar, longValue);
                } else {
                    LogUtil.c("Debug_HiSyncBusinessData", " do not need downloadOneTypeDataByVersion data, type is ", Integer.valueOf(intValue), ", DBversion is ", Long.valueOf(c.a()), ", maxVersion is ", Long.valueOf(longValue));
                }
            }
        }
    }

    private void b(iub iubVar, long j) throws iut {
        long b;
        LogUtil.c("Debug_HiSyncBusinessData", " downloadOneTypeDataWithMaxVersion rep = ", iubVar, ", maxVersion = ", Long.valueOf(j));
        int i = 0;
        do {
            b = b(iubVar);
            i++;
            if (b <= -1) {
                return;
            }
            if (!this.j.d(this.f, iubVar.b(), b, 0L)) {
                LogUtil.h("Debug_HiSyncBusinessData", "downloadOneTypeDataWithMaxVersion saveVersionToDB failed!");
                return;
            }
            iubVar.e(b);
            if (ism.h() && !iuz.f()) {
                LogUtil.h("HiH_HiSyncBusinessData", " downloadOneTypeDataWithMaxVersion() backgroud is running");
                return;
            } else if (i >= 20) {
                LogUtil.h("Debug_HiSyncBusinessData", " downloadOneTypeDataWithMaxVersion() pullDataByVersion too many times");
                return;
            }
        } while (b < j);
    }

    private long b(iub iubVar) throws iut {
        iue iueVar = (iue) lqi.d().d(iubVar.getUrl(), this.g.getHeaders(), lql.b(this.g.getBody(iubVar)), iue.class);
        if (!ius.a(iueVar, false)) {
            LogUtil.h("Debug_HiSyncBusinessData", "downOneTypeDataOnce() SyncError checkCloudRsp");
            return -1L;
        }
        List<iuf> a2 = iueVar.a();
        if (a2 == null || a2.isEmpty()) {
            LogUtil.h("Debug_HiSyncBusinessData", " downOneTypeDataOnce() extendedServiceDatas is null or empty");
            return -1L;
        }
        long c = iueVar.c();
        if (c <= iubVar.d()) {
            ReleaseLogUtil.d("Debug_HiSyncBusinessData", "downOneTypeDataOnce downloadVersion is ", Long.valueOf(c), " smaller than currentVersion ", Long.valueOf(iubVar.d()));
            return -1L;
        }
        if (e(a2)) {
            return c;
        }
        return -1L;
    }

    private boolean e(List<iuf> list) {
        HiHealthData a2;
        LogUtil.c("Debug_HiSyncBusinessData", " BusinessData saveData()");
        ArrayList arrayList = new ArrayList(list.size());
        for (iuf iufVar : list) {
            if (iufVar != null && (a2 = this.d.a(iufVar, this.f)) != null) {
                arrayList.add(a2);
            }
        }
        this.b.saveSyncHealthDetailData(arrayList, this.f);
        return true;
    }

    @Override // com.huawei.hihealthservice.sync.syncdata.HiSyncBase
    public void pushData() throws iut {
        LogUtil.a("HiH_HiSyncBusinessData", "push BusinessData begin !");
        if (!ism.m()) {
            LogUtil.h("Debug_HiSyncBusinessData", "pushData() dataPrivacy switch is closed ,can not pushData right now ,push end !");
            return;
        }
        if (this.h) {
            LogUtil.h("Debug_HiSyncBusinessData", "pushData() isOverSea ,push end !");
            return;
        }
        ikv d = iis.d().d(this.f, 0, 0);
        if (d == null) {
            LogUtil.h("Debug_HiSyncBusinessData", "hiHealthContext is null ,push end !");
            return;
        }
        int b = d.b();
        Iterator<Integer> it = this.l.iterator();
        while (it.hasNext()) {
            a(b, it.next().intValue());
        }
        if (!this.c) {
            LogUtil.a("Debug_HiSyncBusinessData", "mHaveUploadData is false, push BusinessData end !");
            return;
        }
        Map<Integer, Long> c = ity.a(this.e).c(this.l);
        if (c == null || c.isEmpty()) {
            LogUtil.h("Debug_HiSyncBusinessData", "downloadTwice error twiceSyncVersion");
        } else {
            c(c);
            LogUtil.a("Debug_HiSyncBusinessData", "push BusinessData end !");
        }
    }

    private void a(int i, int i2) throws iut {
        while (this.k < 2) {
            LogUtil.a("Debug_HiSyncBusinessData", "push uploadBusinessData clientId ,", Integer.valueOf(i));
            List<HiHealthData> b = b(i, iup.d(i2));
            if (HiCommonUtil.d(b) || !d(b, i, i2)) {
                break;
            }
            b(b);
            this.c = true;
        }
        this.k = 0;
    }

    private List<HiHealthData> b(int i, int i2) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(Integer.valueOf(i2));
        return this.f13615a.e(i, arrayList, 0, 50);
    }

    private boolean d(List<HiHealthData> list, int i, int i2) throws iut {
        if (!this.h) {
            int i3 = this.o + 1;
            this.o = i3;
            iuz.e(i3, this.i.getSyncManual());
        } else {
            int i4 = this.o + 1;
            this.o = i4;
            if (5 < i4) {
                this.k += 2;
                return false;
            }
        }
        List<iuf> d = this.d.d(list, i2);
        if (d == null || d.isEmpty()) {
            LogUtil.h("Debug_HiSyncBusinessData", " addHealthData() extendedServiceDataList is null or empty cloudType is ", Integer.valueOf(i2), " clientId is ", Integer.valueOf(i));
            return false;
        }
        iua iuaVar = new iua();
        iuaVar.c(d);
        while (this.k < 2) {
            if (!ius.a((iuc) lqi.d().d(iuaVar.getUrl(), this.g.getHeaders(), lql.b(this.g.getBody(iuaVar)), iuc.class), false)) {
                this.k++;
            } else {
                ReleaseLogUtil.e("Debug_HiSyncBusinessData", "addBusinessData OK ! uploadCount is ", Integer.valueOf(this.o), " type is ", Integer.valueOf(i2), ", date size = ", Integer.valueOf(list.size()));
                return true;
            }
        }
        ReleaseLogUtil.e("Debug_HiSyncBusinessData", "addBusinessData failed ! uploadCount is ", Integer.valueOf(this.o), " type is ", Integer.valueOf(i2), ", date size = ", Integer.valueOf(list.size()));
        return false;
    }

    private void b(List<HiHealthData> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        int i = 0;
        for (HiHealthData hiHealthData : list) {
            long dataId = hiHealthData.getDataId();
            long j = hiHealthData.getLong("modified_time");
            if (this.f13615a.e(dataId, j)) {
                i = this.f13615a.a(dataId, j);
            }
            LogUtil.a("Debug_HiSyncBusinessData", "uploadBusinessDataDone dataId is ", Long.valueOf(dataId), " Time is ", HiDateUtil.m(hiHealthData.getCreateTime()), " modifiedTime is ", HiDateUtil.m(j), " update ans is ", Integer.valueOf(i));
        }
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}
