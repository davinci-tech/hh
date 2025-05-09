package defpackage;

import android.content.Context;
import com.google.android.gms.fitness.FitnessStatusCodes;
import com.huawei.android.hicloud.sync.logic.SyncProcessInterface;
import com.huawei.android.hicloud.sync.service.aidl.LocalId;
import com.huawei.android.hicloud.sync.service.aidl.SyncData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class aas {
    public int A;
    public Context C;
    public SyncProcessInterface w;
    public String x;
    public String y;
    public int z;

    /* renamed from: a, reason: collision with root package name */
    public List<String> f129a = new ArrayList(10);
    public List<String> b = new ArrayList(10);
    public List<String> c = new ArrayList(10);
    public List<String> d = new ArrayList(10);
    List<String> e = new ArrayList(10);
    public List<String> f = new ArrayList(10);
    public List<String> g = new ArrayList(10);
    public List<String> h = new ArrayList(10);
    public List<String> i = new ArrayList(10);
    List<String> j = new ArrayList(10);
    ArrayList<SyncData> k = new ArrayList<>(10);
    public ArrayList<SyncData> l = null;
    public ArrayList<SyncData> m = null;
    public ArrayList<SyncData> n = null;
    public final ArrayList<String> o = new ArrayList<>(10);
    public final ArrayList<String> p = new ArrayList<>(10);
    public final ArrayList<String> q = new ArrayList<>(10);
    public final ArrayList<String> r = new ArrayList<>(10);
    public List<String> s = null;
    public List<String> t = null;
    public List<String> u = null;
    public List<String> v = null;
    public int B = 0;
    public final ArrayList<String> D = new ArrayList<>();
    public boolean E = true;
    public StringBuffer F = new StringBuffer();

    public void a(String str, aag aagVar) {
        abd.b("SyncProcessBase", "Call app processAplicationException: interfacesName = " + str + ", code = " + aagVar.b() + ", msg = " + aagVar.getMessage());
        a(abf.b(this.y, str, aagVar.b(), aagVar.getMessage()));
        a(-6);
    }

    List<LocalId> b(int i) {
        abd.c("SyncProcessBase", "Call app queryLocalIds start: dataType = " + this.y + ", status = " + i);
        try {
            return this.w.queryLocalIds(this.y, i);
        } catch (aag e) {
            a("queryLocalIds", e);
            return null;
        } catch (Exception e2) {
            a("queryLocalIds", e2);
            return null;
        } finally {
            abd.c("SyncProcessBase", "Call app queryLocalIds end: dataType = " + this.y);
        }
    }

    List<zv> c() {
        try {
            abd.c("SyncProcessBase", "Call app processLocalModifyCloudDelete start: dataType = " + this.y);
            return this.w.processLocalModifyCloudDelete(this.y, this.j);
        } catch (aag e) {
            a("processLocalModifyCloudDelete", e);
            return null;
        } catch (Exception e2) {
            a("processLocalModifyCloudDelete", e2);
            return null;
        } finally {
            abd.c("SyncProcessBase", "Call app processLocalModifyCloudDelete end: dataType = " + this.y);
        }
    }

    public boolean d() {
        try {
            try {
                try {
                    abd.c("SyncProcessBase", "Call app onUploadSyncStart start: dataType = " + this.y);
                    this.w.onUploadSyncStart(this.y);
                    abd.c("SyncProcessBase", "Call app onUploadSyncStart end: dataType = " + this.y);
                    return true;
                } catch (aag e) {
                    a("onUploadSyncStart", e);
                    abd.c("SyncProcessBase", "Call app onUploadSyncStart end: dataType = " + this.y);
                    return false;
                }
            } catch (Exception e2) {
                a("onUploadSyncStart", e2);
                abd.c("SyncProcessBase", "Call app onUploadSyncStart end: dataType = " + this.y);
                return false;
            }
        } catch (Throwable th) {
            abd.c("SyncProcessBase", "Call app onUploadSyncStart end: dataType = " + this.y);
            throw th;
        }
    }

    public List<Object> e() {
        try {
            abd.c("SyncProcessBase", "Call app getUpdateDataResults start: dataType = " + this.y);
            return this.w.getUpdateDataResults();
        } catch (aag e) {
            a("getUpdateDataResults", e);
            return null;
        } catch (Exception e2) {
            a("getUpdateDataResults", e2);
            return null;
        } finally {
            abd.c("SyncProcessBase", "Call app getUpdateDataResults end: dataType = " + this.y);
        }
    }

    void a(String str, Exception exc) {
        abd.b("SyncProcessBase", "Call app processAppUnCatchedException: interfacesName = " + str + ", msg = " + exc.getMessage());
        a(abf.b(this.y, str, FitnessStatusCodes.DATA_TYPE_NOT_FOUND, exc.getMessage()));
        a(-6);
    }

    public void a(String str, String str2, int i) {
        abd.b("SyncProcessBase", "Call app processSdkInnerException: interfacesName = " + str + ", code = " + i + ", msg = " + str2);
        a(abf.b(this.y, str, str2, i));
        a(i);
    }

    public List<String> b(List<String> list) {
        try {
            ArrayList arrayList = new ArrayList(list);
            abd.c("SyncProcessBase", "Call app deleteData start: dataType = " + this.y + ", idList.size = " + arrayList.size());
            return this.w.deleteData(this.y, arrayList);
        } catch (aag e) {
            a("deleteData", e);
            return null;
        } catch (Exception e2) {
            a("deleteData", e2);
            return null;
        } finally {
            abd.c("SyncProcessBase", "Call app deleteData end: dataType = " + this.y);
        }
    }

    zv a(SyncData syncData) {
        abd.c("SyncProcessBase", "Call app addCompare start: dataType = " + this.y + ", localAddedId.size = " + this.f129a.size() + ", data = " + syncData.toString());
        try {
            return this.w.addCompare(this.y, this.f129a, syncData);
        } catch (aag e) {
            a("addCompare", e);
            return null;
        } catch (Exception e2) {
            a("addCompare", e2);
            return null;
        } finally {
            abd.c("SyncProcessBase", "Call app addCompare end: dataType = " + this.y);
        }
    }

    zv a(String str, SyncData syncData) {
        abd.c("SyncProcessBase", "Call app conflictCompare start: dataType = " + this.y);
        try {
            return this.w.conflictCompare(this.y, str, syncData);
        } catch (aag e) {
            a("conflictCompare", e);
            return null;
        } catch (Exception e2) {
            a("conflictCompare", e2);
            return null;
        } finally {
            abd.c("SyncProcessBase", "Call app conflictCompare end: dataType = " + this.y);
        }
    }

    boolean b() {
        abd.c("SyncProcessBase", "Call App onDownloadSyncStart start: dataType = " + this.y);
        HashMap hashMap = new HashMap(10);
        a(hashMap, "cadd", this.f);
        a(hashMap, "cmod", this.g);
        a(hashMap, "cdel", this.h);
        try {
            try {
                try {
                    this.w.onDownloadSyncStart(this.y, hashMap);
                    abd.c("SyncProcessBase", "Call App onDownloadSyncStart end: dataType = " + this.y);
                    return true;
                } catch (aag e) {
                    a("onDownloadSyncStart", e);
                    abd.c("SyncProcessBase", "Call App onDownloadSyncStart end: dataType = " + this.y);
                    return false;
                }
            } catch (Exception e2) {
                a("onDownloadSyncStart", e2);
                abd.c("SyncProcessBase", "Call App onDownloadSyncStart end: dataType = " + this.y);
                return false;
            }
        } catch (Throwable th) {
            abd.c("SyncProcessBase", "Call App onDownloadSyncStart end: dataType = " + this.y);
            throw th;
        }
    }

    public List<zu> a(List<SyncData> list, List<SyncData> list2) {
        abd.c("SyncProcessBase", "Call app updateStructData start: dataType = " + this.y + ", addDataList size = " + list.size() + " , updateDataList size = " + list2.size());
        List<zu> list3 = null;
        try {
            list3 = this.w.updateStructData(this.y, list, list2);
        } catch (aag e) {
            a("updateStructData", e);
        } catch (Exception e2) {
            a("updateStructData", e2);
            return null;
        } finally {
            abd.c("SyncProcessBase", "Call app updateStructData end: dataType = " + this.y);
        }
        return list3;
    }

    public List<zt> a(List<String> list) {
        abd.c("SyncProcessBase", "Call app dataQueryByID start: dataType = " + this.y + ", size = " + list.size());
        try {
            return this.w.dataQueryByID(this.y, list);
        } catch (aag e) {
            a("dataQueryByID", e);
            return null;
        } catch (Exception e2) {
            a("dataQueryByID", e2);
            return null;
        } finally {
            abd.c("SyncProcessBase", "Call app dataQueryByID end: dataType = " + this.y);
        }
    }

    boolean a() {
        abd.c("SyncProcessBase", "Call app onDownloadSyncStart start: dataType = " + this.y);
        HashMap hashMap = new HashMap(10);
        a(hashMap, "ladd", this.f129a);
        a(hashMap, "lmod", this.b);
        a(hashMap, "ldel", this.c);
        a(hashMap, "Lconflict", this.e);
        a(hashMap, "cadd", this.f);
        a(hashMap, "cmod", this.g);
        a(hashMap, "cdel", this.h);
        a(hashMap, "cconflict", this.i);
        try {
            try {
                try {
                    this.w.onDownloadSyncStart(this.y, hashMap);
                    abd.c("SyncProcessBase", "Call app onDownloadSyncStart end: dataType = " + this.y);
                    return true;
                } catch (aag e) {
                    a("onDownloadSyncStart", e);
                    abd.c("SyncProcessBase", "Call app onDownloadSyncStart end: dataType = " + this.y);
                    return false;
                }
            } catch (Exception e2) {
                a("onDownloadSyncStart", e2);
                abd.c("SyncProcessBase", "Call app onDownloadSyncStart end: dataType = " + this.y);
                return false;
            }
        } catch (Throwable th) {
            abd.c("SyncProcessBase", "Call app onDownloadSyncStart end: dataType = " + this.y);
            throw th;
        }
    }

    private void a(Map<String, Integer> map, String str, List<String> list) {
        if (map == null || str == null) {
            return;
        }
        map.put(str, Integer.valueOf(list == null ? 0 : list.size()));
    }

    public void a(int i) {
        abd.c("SyncProcessBase", "Call app onDataSyncEnd start: dataType = " + this.y + ", resultCode = " + i);
        try {
            this.w.onDataSyncEnd(this.y, i);
        } catch (aag e) {
            abd.b("SyncProcessBase", "Call app processAplicationException: interfacesName = onDataSyncEnd, code = " + e.b() + ", msg = " + e.getMessage());
            a(abf.b(this.y, "onDataSyncEnd", e.b(), e.getMessage()));
        } catch (Exception e2) {
            a(abf.b(this.y, "onDataSyncEnd", FitnessStatusCodes.DATA_TYPE_NOT_FOUND, e2.getMessage()));
        } finally {
            abd.c("SyncProcessBase", "Call app onDataSyncEnd end: dataType = " + this.y);
        }
        this.E = true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r6v5 */
    public void a(String str, int i) {
        abd.c("SyncProcessBase", "Call app onDataSyncEnd start: dataType = " + str + ", resultCode = " + i);
        try {
            this.w.onDataSyncEnd(str, i);
        } catch (Exception e) {
            a(abf.b(this.y, "onDataSyncEnd", FitnessStatusCodes.DATA_TYPE_NOT_FOUND, e.getMessage()));
        } catch (aag e2) {
            abd.b("SyncProcessBase", "Call app processAplicationException: interfacesName = onDataSyncEnd, code = " + e2.b() + ", msg = " + e2.getMessage());
            a(abf.b(this.y, "onDataSyncEnd", e2.b(), e2.getMessage()));
        } finally {
            abd.c("SyncProcessBase", "Call app onDataSyncEnd end: dataType = " + str);
        }
        str = 1;
        this.E = true;
    }

    public void a(String str, Map<String, Long> map) {
        try {
            StringBuilder sb = new StringBuilder("Call App: onExceedLimitResult start: ");
            sb.append(map == null ? null : map.toString());
            abd.c("SyncProcessBase", sb.toString());
            this.w.onGetExceedLimitsResult(str, map);
        } catch (aag e) {
            abd.b("SyncProcessBase", "Call App: onExceedLimitResult error: code = " + e.b() + ", msg = " + e.getMessage());
        } catch (Exception e2) {
            abd.b("SyncProcessBase", "Call App: onExceedLimitResult error: " + e2.getMessage());
        } finally {
            abd.c("SyncProcessBase", "Call App: onExceedLimitResult end: syncType = " + str);
        }
    }

    protected void a(String str) {
        throw null;
    }
}
