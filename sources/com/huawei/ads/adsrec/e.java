package com.huawei.ads.adsrec;

import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.huawei.ads.adsrec.db.table.AdContentRspRecord;
import com.huawei.ads.adsrec.db.table.AdCreativeContentRecord;
import com.huawei.ads.adsrec.db.table.AdEventRecord;
import com.huawei.ads.adsrec.db.table.AdIECFeedbackRecord;
import com.huawei.ads.adsrec.db.table.AdIECImpRecord;
import com.huawei.ads.adsrec.db.table.AdSlotMapRecord;
import com.huawei.ads.adsrec.db.table.AdTraceRecord;
import com.huawei.ads.adsrec.db.table.DsContentRelRecord;
import com.huawei.ads.adsrec.db.table.MaterialSummaryRecord;
import com.huawei.ads.adsrec.db.table.SlotRecord;
import com.huawei.ads.fund.db.BaseDao;
import com.huawei.ads.fund.db.BaseDbHelper;
import com.huawei.ads.fund.util.ListUtil;
import com.huawei.ads.fund.util.StringUtils;
import com.huawei.openplatform.abl.log.HiAdLog;
import defpackage.uw;
import defpackage.uy;
import defpackage.vb;
import defpackage.vg;
import defpackage.vm;
import defpackage.vs;
import defpackage.wc;
import defpackage.wk;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes2.dex */
public class e extends BaseDao implements d0 {
    @Override // com.huawei.ads.fund.db.IDao
    public String getTag() {
        return "AdRecDao";
    }

    @Override // com.huawei.ads.fund.db.IDao
    public BaseDbHelper getDbHelper() {
        return vm.c(this.context);
    }

    @Override // com.huawei.ads.fund.db.BaseDao, com.huawei.ads.fund.db.IDao
    public void emptyTables(String str) {
        super.emptyTables(str);
        delete(AdCreativeContentRecord.class, null, null);
        delete(AdEventRecord.class, null, null);
        delete(AdIECFeedbackRecord.class, null, null);
        delete(AdIECImpRecord.class, null, null);
        delete(AdSlotMapRecord.class, null, null);
        delete(SlotRecord.class, null, null);
        delete(AdContentRspRecord.class, null, null);
        delete(AdTraceRecord.class, null, null);
        delete(MaterialSummaryRecord.class, null, null);
    }

    @Override // com.huawei.ads.adsrec.d0
    public void c(List<vg> list) {
        if (ListUtil.isEmpty(list)) {
            return;
        }
        ArrayList<SlotRecord> arrayList = new ArrayList(list.size());
        Iterator<vg> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().j());
        }
        if (ListUtil.isEmpty(arrayList)) {
            HiAdLog.w("AdRecDao", "all slotRecords: empty");
            return;
        }
        ArrayList arrayList2 = new ArrayList(arrayList.size());
        for (SlotRecord slotRecord : arrayList) {
            if (slotRecord != null) {
                arrayList2.add(d(slotRecord));
            }
        }
        insertOrUpdate(arrayList2);
    }

    @Override // com.huawei.ads.adsrec.d0
    public void b(List<String[]> list) {
        batchExecute(c1.i, list);
    }

    @Override // com.huawei.ads.adsrec.d0
    public List<MaterialSummaryRecord> b(String str, String str2) {
        return query(MaterialSummaryRecord.class, null, "pkgName=? and adType=?", new String[]{str, str2}, null, null);
    }

    public List<DsContentRelRecord> e(String str, long j) {
        String[] strArr = {String.valueOf(j - 604800000), String.valueOf(j), str, "1"};
        String str2 = c1.l;
        Arrays.toString(strArr);
        return rawQuery(DsContentRelRecord.class, str2, strArr);
    }

    @Override // com.huawei.ads.adsrec.d0
    public AdContentRspRecord b(String str) {
        List query = query(AdContentRspRecord.class, null, "pkgName=?", new String[]{str}, null, null);
        if (ListUtil.isEmpty(query)) {
            return null;
        }
        return (AdContentRspRecord) query.get(0);
    }

    @Override // com.huawei.ads.adsrec.d0
    public void a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return;
        }
        int length = jSONArray.length();
        ArrayList arrayList = new ArrayList(length);
        for (int i = 0; i < length; i++) {
            arrayList.add(jSONArray.optString(i));
        }
        deleteSet(AdCreativeContentRecord.class, "contentId=?", arrayList);
        deleteSet(AdSlotMapRecord.class, "contentId=?", arrayList);
    }

    @Override // com.huawei.ads.adsrec.d0
    public void a(List<vg> list, int i) {
        if (ListUtil.isEmpty(list)) {
            HiAdLog.w("AdRecDao", "iu all slots: empty");
            return;
        }
        for (vg vgVar : list) {
            if (vgVar != null) {
                a(vgVar, i);
            }
        }
    }

    @Override // com.huawei.ads.adsrec.d0
    public void a(List<String[]> list) {
        batchExecute(c1.j, list);
    }

    @Override // com.huawei.ads.adsrec.d0
    public void a(AdContentRspRecord adContentRspRecord) {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(b(adContentRspRecord));
        insertOrUpdate(arrayList);
    }

    public void e(uy uyVar) {
        if (uyVar != null) {
            AdEventRecord b = uyVar.b();
            insert(AdEventRecord.class, b.toRecord());
            d(b);
        }
    }

    public void d() {
        delete(AdTraceRecord.class, "uptime<?", new String[]{String.valueOf(System.currentTimeMillis())});
    }

    public List<DsContentRelRecord> c(String str, long j) {
        String[] strArr = {String.valueOf(j - 604800000), String.valueOf(j), str};
        String str2 = c1.m;
        Arrays.toString(strArr);
        return rawQuery(DsContentRelRecord.class, str2, strArr);
    }

    @Override // com.huawei.ads.adsrec.d0
    public List<AdIECImpRecord> a(String str) {
        return query(AdIECImpRecord.class, null, "pkgName=?", new String[]{str}, null, null);
    }

    @Override // com.huawei.ads.adsrec.d0
    public List<vb> a(wk wkVar) {
        BaseDbHelper baseDbHelper;
        ArrayList arrayList = new ArrayList(5);
        Cursor cursor = null;
        try {
            baseDbHelper = getDbHelper();
            try {
                cursor = baseDbHelper.rawQuery(wkVar.b(), wkVar.e());
                if (cursor != null) {
                    while (cursor.moveToNext()) {
                        try {
                            AdCreativeContentRecord adCreativeContentRecord = new AdCreativeContentRecord();
                            AdSlotMapRecord adSlotMapRecord = new AdSlotMapRecord();
                            adCreativeContentRecord.toBean(cursor);
                            adSlotMapRecord.toBean(cursor);
                            arrayList.add(new vb(adSlotMapRecord, adCreativeContentRecord));
                        } catch (Throwable th) {
                            HiAdLog.e(getTag(), "queryContents:" + th.getClass().getSimpleName());
                        }
                    }
                }
            } catch (Throwable th2) {
                th = th2;
                try {
                    HiAdLog.e(getTag(), "queryContents exception: %s", th.getClass().getSimpleName());
                    return arrayList;
                } finally {
                    closeCursor(cursor);
                    releaseDbHelper(baseDbHelper);
                }
            }
        } catch (Throwable th3) {
            th = th3;
            baseDbHelper = null;
        }
        return arrayList;
    }

    public String a(String str, AdEventRecord adEventRecord, int i) {
        try {
            JSONArray jSONArray = StringUtils.isBlank(str) ? new JSONArray() : new JSONArray(str);
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("timestamp", StringUtils.toLong(adEventRecord.f()));
            if (i == 0) {
                jSONObject.put("range", adEventRecord.h());
            }
            jSONArray.put(jSONObject);
            return jSONArray.toString();
        } catch (Throwable th) {
            HiAdLog.printSafeStackTrace(3, th);
            HiAdLog.e("AdRecDao", "build json array error");
            return null;
        }
    }

    @Override // com.huawei.ads.adsrec.d0
    public vg a(String str, String str2) {
        List query = query(SlotRecord.class, null, "pkgName=? and slotId=?", new String[]{str, str2}, null, null);
        return !ListUtil.isEmpty(query) ? new vg((SlotRecord) query.get(0)) : new vg(str, str2);
    }

    private void a(AdEventRecord adEventRecord) {
        executeSql(c1.f1675a, new Object[]{adEventRecord.e(), adEventRecord.g(), adEventRecord.d()});
        String[] strArr = {adEventRecord.e(), adEventRecord.d()};
        List query = query(AdIECImpRecord.class, null, "pkgName=? and contentId=?", strArr, null, null);
        if (!ListUtil.isEmpty(query)) {
            AdIECImpRecord adIECImpRecord = (AdIECImpRecord) query.get(0);
            adIECImpRecord.c(adEventRecord.a());
            adIECImpRecord.a(adIECImpRecord.d() + 1);
            adIECImpRecord.b(System.currentTimeMillis());
            update(AdIECImpRecord.class, adIECImpRecord.toRecord(), "pkgName=? and contentId=?", strArr);
            return;
        }
        AdIECImpRecord adIECImpRecord2 = new AdIECImpRecord();
        adIECImpRecord2.e(adEventRecord.e());
        adIECImpRecord2.b(adEventRecord.d());
        adIECImpRecord2.c(adEventRecord.a());
        adIECImpRecord2.a(1);
        insert(AdIECImpRecord.class, adIECImpRecord2.toRecord());
    }

    private void e(AdEventRecord adEventRecord) {
        if (wc.d(this.context).b(adEventRecord.g()) == 0) {
            return;
        }
        String[] strArr = {adEventRecord.d()};
        List query = query(AdIECFeedbackRecord.class, null, "contentId=?", strArr, null, null);
        if (ListUtil.isEmpty(query)) {
            AdIECFeedbackRecord adIECFeedbackRecord = new AdIECFeedbackRecord();
            adIECFeedbackRecord.c(adEventRecord.d());
            adIECFeedbackRecord.a(1);
            insert(AdIECFeedbackRecord.class, adIECFeedbackRecord.toRecord());
        } else {
            AdIECFeedbackRecord adIECFeedbackRecord2 = (AdIECFeedbackRecord) query.get(0);
            adIECFeedbackRecord2.a(1);
            adIECFeedbackRecord2.e(System.currentTimeMillis());
            update(AdIECFeedbackRecord.class, adIECFeedbackRecord2.toRecord(), "contentId=?", strArr);
        }
        delete(AdSlotMapRecord.class, null, null);
        delete(AdCreativeContentRecord.class, null, null);
        delete(MaterialSummaryRecord.class, null, null);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void d(AdEventRecord adEventRecord) {
        char c;
        String b = adEventRecord.b();
        b.hashCode();
        b.hashCode();
        switch (b.hashCode()) {
            case -1849019858:
                if (b.equals("repeatedClick")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 104396:
                if (b.equals("imp")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 94750088:
                if (b.equals("click")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 342989069:
                if (b.equals("userclose")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1602549234:
                if (b.equals("repeatedImp")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c != 0) {
            if (c != 1) {
                if (c != 2) {
                    if (c == 3) {
                        e(adEventRecord);
                        return;
                    } else if (c != 4) {
                        return;
                    }
                }
            }
            a(adEventRecord);
            d(adEventRecord, 0);
            return;
        }
        b(adEventRecord);
        d(adEventRecord, 1);
    }

    private wk b(AdContentRspRecord adContentRspRecord) {
        return new wk(adContentRspRecord.getTableName(), null, null, "pkgName=?", new String[]{adContentRspRecord.a()}, adContentRspRecord.toRecord(this.context));
    }

    private String[] c(String str, int i) {
        List<String> a2 = u.a(str, i);
        if (!ListUtil.isEmpty(a2)) {
            return (String[]) a2.toArray(new String[a2.size()]);
        }
        IUtilCallback d = uw.d();
        return d != null ? d.getAllowCachedTradeModeList(str, String.valueOf(i)) : e1.f1676a;
    }

    private boolean c(String str, String[] strArr) {
        Arrays.toString(strArr);
        if (!ListUtil.isEmpty(strArr)) {
            for (String str2 : strArr) {
                if (TextUtils.equals(str2, str)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void a(vg vgVar, int i) {
        AdCreativeContentRecord d;
        List<vb> a2 = vgVar.a();
        if (ListUtil.isEmpty(a2)) {
            return;
        }
        ArrayList arrayList = new ArrayList(a2.size());
        ArrayList arrayList2 = new ArrayList(a2.size());
        String[] c = c(vgVar.e(), i);
        ArrayList arrayList3 = new ArrayList(a2.size());
        for (vb vbVar : a2) {
            if (vbVar != null && vbVar.s() == 0 && (d = vbVar.d()) != null && c(d.h(), c)) {
                arrayList.add(c(d));
                AdSlotMapRecord i2 = vbVar.i();
                if (i2 != null) {
                    i2.b(0);
                    i2.e(0);
                    arrayList2.add(b(i2));
                }
                arrayList3.add(d(vbVar.q()));
                e(vbVar);
            }
        }
        insertOrUpdate(arrayList);
        insertOrUpdate(arrayList2);
        insertOrUpdate(arrayList3);
    }

    private void d(AdEventRecord adEventRecord, int i) {
        wk wkVar;
        if (vs.e()) {
            String d = adEventRecord.d();
            String c = adEventRecord.c();
            if (ListUtil.isEmpty(query(DsContentRelRecord.class, null, "clientRequestId=? and contentId=?", new String[]{c, d}, null, null))) {
                return;
            }
            String a2 = w0.a(c);
            List query = query(AdTraceRecord.class, null, "uniqueId=? and contentId=?", new String[]{a2, d}, null, "1");
            query.size();
            ArrayList arrayList = new ArrayList();
            if (ListUtil.isEmpty(query)) {
                AdTraceRecord adTraceRecord = new AdTraceRecord();
                adTraceRecord.b(true);
                adTraceRecord.h(a2);
                adTraceRecord.b(d);
                List query2 = query(AdCreativeContentRecord.class, null, "contentId=?", new String[]{d}, null, null);
                if (!ListUtil.isEmpty(query2) && query2.get(0) != null) {
                    adTraceRecord.d(((AdCreativeContentRecord) query2.get(0)).h());
                }
                List query3 = query(MaterialSummaryRecord.class, null, "contentId=? and pkgName=? and slotId=?", new String[]{d, adEventRecord.e(), adEventRecord.g()}, null, "1");
                if (!ListUtil.isEmpty(query3) && query3.get(0) != null) {
                    adTraceRecord.a(((MaterialSummaryRecord) query3.get(0)).f());
                }
                adTraceRecord.a(System.currentTimeMillis());
                if (i == 0) {
                    adTraceRecord.e(a(null, adEventRecord, i));
                }
                if (i == 1) {
                    adTraceRecord.c(a(null, adEventRecord, i));
                }
                wkVar = new wk("AdTraceRecord", null, null, "uniqueId=? and contentId=?", new String[]{a2, d}, adTraceRecord.toRecord());
            } else {
                AdTraceRecord adTraceRecord2 = (AdTraceRecord) query.get(0);
                if (i == 0) {
                    String a3 = a(adTraceRecord2.d(), adEventRecord, i);
                    if (!StringUtils.isBlank(a3)) {
                        adTraceRecord2.e(a3);
                    }
                } else if (i == 1) {
                    String a4 = a(adTraceRecord2.e(), adEventRecord, i);
                    if (!StringUtils.isBlank(a4)) {
                        adTraceRecord2.c(a4);
                    }
                }
                List query4 = query(MaterialSummaryRecord.class, null, "contentId=? and pkgName=? and slotId=?", new String[]{d, adEventRecord.e(), adEventRecord.g()}, null, "1");
                if (!ListUtil.isEmpty(query4) && query4.get(0) != null) {
                    adTraceRecord2.a(((MaterialSummaryRecord) query4.get(0)).f());
                }
                adTraceRecord2.a(System.currentTimeMillis());
                wkVar = new wk("AdTraceRecord", null, null, "uniqueId=? and contentId=?", new String[]{a2, d}, adTraceRecord2.toRecord());
            }
            arrayList.add(wkVar);
            insertOrUpdate(arrayList);
        }
    }

    private void b(AdEventRecord adEventRecord) {
        executeSql(c1.b, new Object[]{adEventRecord.e(), adEventRecord.g(), adEventRecord.d()});
    }

    private void e(vb vbVar) {
        executeSql(c1.c, new Object[]{Long.valueOf(System.currentTimeMillis()), vbVar.r(), vbVar.f()});
    }

    private wk d(SlotRecord slotRecord) {
        return new wk(slotRecord.getTableName(), null, null, "pkgName=? and slotId=?", new String[]{slotRecord.d(), slotRecord.e()}, slotRecord.toRecord(this.context));
    }

    private wk d(MaterialSummaryRecord materialSummaryRecord) {
        return new wk(materialSummaryRecord.getTableName(), null, null, "pkgName=? and adType=? and slotId=? and contentId=?", new String[]{materialSummaryRecord.d(), materialSummaryRecord.b(), materialSummaryRecord.e(), materialSummaryRecord.a()}, materialSummaryRecord.toRecord(this.context));
    }

    private wk b(AdSlotMapRecord adSlotMapRecord) {
        return new wk(adSlotMapRecord.getTableName(), null, null, "pkgName=? and slotId=? and contentId=?", new String[]{adSlotMapRecord.a(), adSlotMapRecord.c(), adSlotMapRecord.b()}, adSlotMapRecord.toRecord(this.context));
    }

    private wk c(AdCreativeContentRecord adCreativeContentRecord) {
        return new wk(adCreativeContentRecord.getTableName(), null, null, "contentId=?", new String[]{adCreativeContentRecord.e()}, adCreativeContentRecord.toRecord(this.context));
    }

    public e(Context context) {
        super(context);
    }
}
