package defpackage;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import com.huawei.android.hicloud.sync.logic.SyncProcessInterface;
import com.huawei.android.hicloud.sync.logic.c;
import com.huawei.android.hicloud.sync.service.aidl.ISyncServiceCallback;
import com.huawei.android.hicloud.sync.service.aidl.LocalId;
import com.huawei.android.hicloud.sync.service.aidl.SyncData;
import com.huawei.android.hicloud.sync.service.aidl.UnstructData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
class aaq extends c {
    private final Map<String, String> ad;

    aaq(Context context, String str, SyncProcessInterface syncProcessInterface, aau aauVar) {
        super(context, str, syncProcessInterface, aauVar);
        this.ad = new HashMap();
    }

    private void d(List<String> list) {
        List<String> list2;
        if (list == null || list.isEmpty() || (list2 = this.f129a) == null || list2.isEmpty()) {
            return;
        }
        abd.c("CloudSyncV1", "delete local added id, size = " + list.size() + ", list = " + list.toString());
        this.f129a.removeAll(list);
    }

    private void eV_(Bundle bundle, boolean z) throws JSONException {
        if (z) {
            c(new JSONObject(this.F.toString()));
            this.F = new StringBuffer();
        } else {
            this.f129a = abl.fu_(bundle, "ladd");
            this.b = abl.fu_(bundle, "lmod");
            this.c = abl.fu_(bundle, "ldel");
            this.e = abl.fu_(bundle, "Lconflict");
            this.f = abl.fu_(bundle, "cadd");
            this.g = abl.fu_(bundle, "cmod");
            this.h = abl.fu_(bundle, "cdel");
            this.i = abl.fu_(bundle, "cconflict");
            this.j = abl.fu_(bundle, "lmodcdel");
            this.k = bundle.getParcelableArrayList("coperatemap");
        }
        abd.c("CloudSyncV1", "ladd = " + this.f129a.size() + " ,lModifyId = " + this.b.size() + " ,lDeleteId = " + this.c.size() + " ,lConflictId = " + this.e.size());
        abd.c("CloudSyncV1", "cAddGuid = " + this.f.size() + " ,cModifyGuid = " + this.g.size() + " ,cDeleteId = " + this.h.size() + " ,cConflictGuid = " + this.i.size());
        abd.c("CloudSyncV1", "ladd content = " + this.f129a.toString() + " ,lModifyId context = " + this.b.toString() + " ,lDeleteId content = " + this.c.toString() + " ,lConflictId content = " + this.e.toString());
        abd.c("CloudSyncV1", "cAddGuid content = " + this.f.toString() + " ,cModifyGuid content = " + this.g.toString() + " ,cDeleteId content = " + this.h.toString() + " ,cConflictGuid content = " + this.i.toString());
        abl.e(this.ad, this.i, this.e);
        ArrayList<SyncData> arrayList = this.k;
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        Iterator<SyncData> it = this.k.iterator();
        while (it.hasNext()) {
            SyncData next = it.next();
            this.H.put(next.getGuid(), next);
        }
    }

    private void p() {
        Iterator<SyncData> it = this.l.iterator();
        while (it.hasNext()) {
            List<String> list = this.f129a;
            if (list == null || list.isEmpty()) {
                abd.c("CloudSyncV1", "localAddedId size is 0, no need addCompare.");
                return;
            }
            SyncData next = it.next();
            if (next == null) {
                abd.b("CloudSyncV1", "compareAddedData, cloud data is null");
            } else {
                zv a2 = a(next);
                if (a2 == null) {
                    abd.b("CloudSyncV1", "App compare added data result is null, guid = " + next.getGuid());
                    return;
                }
                String a3 = a2.a();
                String d = a2.d();
                abd.c("CloudSyncV1", "compareAddedData, mode = " + a2.c() + ", id = " + a3 + ", new id = " + a2.d());
                if (1 == a2.c()) {
                    it.remove();
                    if (TextUtils.isEmpty(d)) {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(a3);
                        d((List<String>) arrayList);
                        this.b.add(a3);
                        this.J.put(a3, next.getGuid());
                    } else {
                        ArrayList arrayList2 = new ArrayList();
                        arrayList2.add(a3);
                        d((List<String>) arrayList2);
                        LocalId localId = this.I.get(a3);
                        abd.c("CloudSyncV1", "addCompare, oldData = " + localId);
                        localId.setId(d);
                        this.I.put(d, localId);
                        this.b.add(d);
                        this.J.put(d, next.getGuid());
                    }
                } else if (2 == a2.c()) {
                    it.remove();
                    ArrayList arrayList3 = new ArrayList();
                    arrayList3.add(a3);
                    d((List<String>) arrayList3);
                    this.m.add(next);
                    next.setLuid(a3);
                } else if (3 == a2.c() && a3 != null) {
                    ArrayList arrayList4 = new ArrayList();
                    arrayList4.add(a3);
                    d((List<String>) arrayList4);
                    LocalId localId2 = this.I.get(a3);
                    abd.c("CloudSyncV1", "addCompare old id object ,change = " + localId2);
                    localId2.setId(a2.d());
                    this.I.put(a2.d(), localId2);
                    this.f129a.add(a2.d());
                }
            }
        }
    }

    private boolean q() {
        ArrayList<SyncData> arrayList;
        List<String> list = this.f129a;
        return (list == null || list.isEmpty() || (arrayList = this.l) == null || arrayList.isEmpty()) ? false : true;
    }

    private void r() {
        Iterator<SyncData> it = this.n.iterator();
        while (it.hasNext()) {
            SyncData next = it.next();
            String str = this.ad.get(next.getGuid());
            zv a2 = a(str, next);
            abd.c("CloudSyncV1", "compareConflictData cloud data = " + next.toString());
            if (a2 == null) {
                abd.b("CloudSyncV1", "conflict compare return null ,cloud data guid = " + str);
                return;
            }
            abd.c("CloudSyncV1", "compareConflictData reslult = " + a2);
            if (1 == a2.c()) {
                this.b.add(str);
            } else if (2 == a2.c()) {
                this.m.add(next);
            } else if (3 == a2.c()) {
                abd.c("CloudSyncV1", "compareConflictData LOCAL_CLOUD_SAVE,new localid = " + a2.d() + " , oldLocalid =" + str);
                LocalId localId = this.I.get(str);
                StringBuilder sb = new StringBuilder("conflictCompare old id object ,change = ");
                sb.append(localId);
                abd.c("CloudSyncV1", sb.toString());
                localId.setId(a2.d());
                this.I.put(a2.d(), localId);
                this.f129a.add(a2.d());
                this.l.add(next);
            } else if (5 == a2.c()) {
                abd.c("CloudSyncV1", "compareConflictData CLOUD_ADD");
                this.l.add(next);
            } else if (6 == a2.c()) {
                this.c.add(str);
            }
        }
    }

    private boolean s() {
        return !this.n.isEmpty();
    }

    private void t() {
        List<zv> c = c();
        if (c == null) {
            abd.b("CloudSyncV1", "App process local modified cloud deleted conflict, result is null");
            return;
        }
        abd.c("CloudSyncV1", "App process local modified cloud deleted conflict, result = " + c.toString());
        for (zv zvVar : c) {
            int c2 = zvVar.c();
            if (c2 == 7) {
                String a2 = zvVar.a();
                this.h.add(a2);
                this.f129a.remove(a2);
            } else if (c2 != 8) {
                abd.b("CloudSyncV1", "App process local modified cloud deleted conflict, result is wrong");
                a(-7);
            }
        }
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public void a(Bundle bundle) {
        abd.b("CloudSyncV1", "V1 version should not reach processGetNewVersion.");
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public void a(Bundle bundle, boolean z) {
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public void a(String str, String str2, List<SyncData> list, List<SyncData> list2, List<String> list3, List<SyncData> list4, boolean z, ISyncServiceCallback iSyncServiceCallback) {
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public ArrayList<String> b(ArrayList<String> arrayList) {
        return arrayList;
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public void b(Bundle bundle) {
        int i = bundle.getInt("hicloud_old_version");
        aal.a(i);
        if (e(i)) {
            d(this.x, this.y, this.z, this.B);
        }
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public void c(List<String> list) {
        this.G.a(this.x, this.y, (List<SyncData>) null, list, false, this.O);
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public void f() {
        this.G.b(this.x, this.y, this.O);
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public void f(int i) {
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public void k() {
        abd.b("CloudSyncV1", "V1 version should not reach processRiskManagementResult.");
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public void n() {
        this.G.b(this.O);
    }

    private void c(JSONObject jSONObject) {
        abd.a("CloudSyncV1", "parseBeginSyncParceDataFromJson");
        try {
            String string = jSONObject.getString("cadd");
            String string2 = jSONObject.getString("cmod");
            String string3 = jSONObject.getString("cdel");
            String string4 = jSONObject.getString("cconflict");
            String string5 = jSONObject.getString("ladd");
            String string6 = jSONObject.getString("lmod");
            String string7 = jSONObject.getString("ldel");
            String string8 = jSONObject.getString("Lconflict");
            String string9 = jSONObject.getString("lmodcdel");
            this.f = abl.d(string);
            this.g = abl.d(string2);
            this.h = abl.d(string3);
            this.i = abl.d(string4);
            this.f129a = abl.d(string5);
            this.b = abl.d(string6);
            this.c = abl.d(string7);
            this.e = abl.d(string8);
            this.j = abl.d(string9);
            JSONArray jSONArray = jSONObject.getJSONArray("coperatemap");
            this.k.clear();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                if (jSONObject2 != null) {
                    SyncData syncData = new SyncData();
                    syncData.setGuid(jSONObject2.getString("guid"));
                    syncData.setEtag(jSONObject2.getString("etag"));
                    syncData.setLuid(jSONObject2.getString("luid"));
                    this.k.add(syncData);
                }
            }
        } catch (JSONException unused) {
            abd.b("CloudSyncV1", "parseBeginSyncParceDataFromJson error : JSONException");
        }
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public void c(ArrayList<SyncData> arrayList) {
        abd.b("CloudSyncV1", "V1 version should not reach setCloudDataGuid.");
    }

    public void d(String str, String str2, int i, int i2) {
        if (a(str, str2, i, i2)) {
            List<LocalId> b = b(1);
            if (b == null) {
                abd.b("CloudSyncV1", "App query local id list is null, syncType = " + str + ", dataType = " + str2);
                return;
            }
            abd.c("CloudSyncV1", "lIds  = " + b.toString() + " ,lIds size = " + b.size());
            aal.c(2);
            aal.d(false);
            this.G.d(str, str2, b, i, this.A, this.O);
            this.I.clear();
            for (LocalId localId : b) {
                this.I.put(localId.getId(), localId);
            }
        }
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public boolean b(Bundle bundle, boolean z) {
        try {
            eV_(bundle, z);
            if (!a()) {
                return false;
            }
            if (this.j.isEmpty()) {
                return true;
            }
            t();
            return true;
        } catch (JSONException e) {
            abd.b("CloudSyncV1", "beginSyncResult error : JSONException, " + e.getMessage());
            this.F = new StringBuffer();
            a("beginSyncResult", e.getMessage(), 5001);
            return false;
        }
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public void b(List<SyncData> list, List<String> list2) {
        this.G.a(this.x, this.y, list, list2, true, this.O);
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public void a(List<SyncData> list, List<String> list2, int i) {
        this.G.a(this.x, this.y, list, list2, true, this.O);
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public void a(ArrayList<UnstructData> arrayList) {
        abd.b("CloudSyncV1", "V1 version should not reach updateFileOperator.");
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public void j() {
        if (q()) {
            p();
        }
        if (s()) {
            r();
        }
        List<SyncData> o = o();
        if (o == null) {
            return;
        }
        this.G.a(this.x, this.y, o, (List<String>) null, false, this.O);
    }
}
