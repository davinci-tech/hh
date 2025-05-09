package com.huawei.android.hicloud.sync.logic;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.RemoteException;
import android.text.TextUtils;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.huawei.android.hicloud.sync.service.aidl.ISyncServiceCallback;
import com.huawei.android.hicloud.sync.service.aidl.LocalId;
import com.huawei.android.hicloud.sync.service.aidl.SyncData;
import com.huawei.android.hicloud.sync.service.aidl.UnstructData;
import com.huawei.android.hicloud.sync.util.ArrayUtils;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import defpackage.aag;
import defpackage.aal;
import defpackage.aap;
import defpackage.aas;
import defpackage.aau;
import defpackage.aaw;
import defpackage.aaz;
import defpackage.abd;
import defpackage.abe;
import defpackage.abf;
import defpackage.abl;
import defpackage.zt;
import defpackage.zu;
import defpackage.zy;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public abstract class c extends aas {
    public final aau G;
    JSONArray K;
    String L;
    public int M;
    public final Map<String, SyncData> H = new HashMap(100);
    public final Map<String, LocalId> I = new HashMap();
    public final Map<String, String> J = new HashMap();
    private final Handler N = new a();
    public final ISyncServiceCallback O = new d();

    class a extends Handler {
        a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                abd.c("CloudSyncBase", "Begin handleMessage msg.what = " + message.what);
                Bundle bundle = (Bundle) message.obj;
                if (bundle == null) {
                    abd.d("CloudSyncBase", "Receive bundle is null");
                    return;
                }
                bundle.setClassLoader(c.class.getClassLoader());
                int i = bundle.getInt("resultCode");
                boolean z = bundle.getBoolean("is_in_batches", false);
                abd.c("CloudSyncBase", "sendInBatches: " + z);
                if (!z || c.this.d(bundle)) {
                    if (c.this.a(message, bundle, z, i)) {
                        abd.d("CloudSyncBase", "handle message, parse partical result");
                        return;
                    }
                    if (i == 0) {
                        c.this.a(message, bundle, z);
                        return;
                    }
                    abd.d("CloudSyncBase", "handle message, result error, result = " + i);
                    c.this.a(i);
                }
            } catch (Exception e) {
                abd.d("CloudSyncBase", "handle message exception " + e.getMessage());
                c.this.a("HandleMessage", e.getMessage(), 5001);
            }
        }
    }

    class d extends ISyncServiceCallback.Stub {
        d() {
        }

        @Override // com.huawei.android.hicloud.sync.service.aidl.ISyncServiceCallback
        public void handlerEventMsg(int i, int i2, int i3, Bundle bundle) throws RemoteException {
            StringBuilder sb = new StringBuilder("handlerEventMsg, mHandler is null: ");
            sb.append(c.this.N == null);
            abd.c("CloudSyncBase", sb.toString());
            if (c.this.N == null || c.this.a(i, bundle)) {
                return;
            }
            c.this.N.sendMessage(c.this.N.obtainMessage(i, i2, i3, bundle));
        }
    }

    public c(Context context, String str, SyncProcessInterface syncProcessInterface, aau aauVar) {
        this.C = context;
        this.w = syncProcessInterface;
        this.G = aauVar;
        abd.c("CloudSyncBase", "new CloudSyncBase, version code: 13.3.0.303");
    }

    private void b(boolean z) {
        abd.c("CloudSyncBase", "uploadLocalData.");
        ArrayList<SyncData> arrayList = new ArrayList<>(10);
        if (!abl.c(this.t)) {
            List<zt> a2 = a(this.t);
            if (a2 != null) {
                abd.c("CloudSyncBase", "dataQueryByID appUpdateDataNum = " + a2.size());
                for (zt ztVar : a2) {
                    SyncData syncData = new SyncData();
                    syncData.setLuid(ztVar.e());
                    syncData.setData(ztVar.a());
                    syncData.setUnstruct_uuid(ztVar.f());
                    syncData.setFileList(ztVar.c());
                    syncData.setUuid(ztVar.j());
                    syncData.setRecycleStatus(ztVar.h());
                    syncData.setRecycleTime(ztVar.g());
                    if (this.J.get(ztVar.e()) != null) {
                        abd.c("CloudSyncBase", "set guid , id = " + ztVar.e() + " , guid = " + this.J.get(ztVar.e()));
                        syncData.setGuid(this.J.get(ztVar.e()));
                    }
                    if (aal.a() >= 107) {
                        syncData.setVersion(aal.a());
                        syncData.setExtensionData(ztVar.d());
                        syncData.setExtraParam(ztVar.b());
                    }
                    arrayList.add(syncData);
                }
                c(arrayList);
            } else {
                abd.c("CloudSyncBase", "dataQueryByID appUpdateData is null");
            }
        }
        ArrayList arrayList2 = new ArrayList(10);
        if (!abl.c(this.s)) {
            List<zt> a3 = a(this.s);
            if (a3 != null) {
                abd.c("CloudSyncBase", "dataQueryByID AppAddDataNum = " + a3.size());
                for (zt ztVar2 : a3) {
                    SyncData syncData2 = new SyncData();
                    syncData2.setLuid(ztVar2.e());
                    syncData2.setData(ztVar2.a());
                    syncData2.setFileList(ztVar2.c());
                    syncData2.setUuid(ztVar2.j());
                    syncData2.setRecycleStatus(ztVar2.h());
                    syncData2.setRecycleTime(ztVar2.g());
                    if (aal.a() >= 107) {
                        syncData2.setVersion(aal.a());
                        syncData2.setExtensionData(ztVar2.d());
                        syncData2.setExtraParam(ztVar2.b());
                    }
                    arrayList2.add(syncData2);
                }
            } else {
                abd.c("CloudSyncBase", "dataQueryByID AppAddData is null");
            }
        }
        ArrayList<SyncData> arrayList3 = new ArrayList<>(10);
        if (!abl.c(this.v)) {
            List<zt> a4 = a(this.v);
            if (a4 != null) {
                abd.c("CloudSyncBase", "dataQueryByID AppLostDataNum = " + a4.size());
                for (zt ztVar3 : a4) {
                    SyncData syncData3 = new SyncData();
                    syncData3.setLuid(ztVar3.e());
                    syncData3.setData(ztVar3.a());
                    syncData3.setFileList(ztVar3.c());
                    syncData3.setUuid(ztVar3.j());
                    syncData3.setRecycleStatus(ztVar3.h());
                    syncData3.setRecycleTime(ztVar3.g());
                    syncData3.setUnstruct_uuid(ztVar3.f());
                    if (aal.a() >= 107) {
                        syncData3.setVersion(aal.a());
                        syncData3.setExtensionData(ztVar3.d());
                        syncData3.setExtraParam(ztVar3.b());
                    }
                    arrayList3.add(syncData3);
                }
                c(arrayList3);
            } else {
                abd.d("CloudSyncBase", "dataQueryByID lostData is null! ");
            }
        }
        if (s()) {
            abd.c("CloudSyncBase", "Upload data, haveFile = " + z + ", add.size = " + arrayList2.size() + " , modify.size = " + arrayList.size() + " , delete.size = " + this.u.size() + " , lost.size = " + arrayList3.size());
            if (ArrayUtils.isEmpty(arrayList3) || !aaw.i(this.C)) {
                a(this.x, this.y, arrayList2, arrayList, this.u, z, this.O);
            } else {
                abd.c("CloudSyncBase", "uploadDataWithLost");
                a(this.x, this.y, arrayList2, arrayList, this.u, arrayList3, z, this.O);
            }
            a(z);
        }
    }

    private void c(Bundle bundle) {
        if (bundle.containsKey("traceId")) {
            this.L = bundle.getString("traceId");
            abd.c("CloudSyncBase", "getTraceIdAndReportLastSync traceId = " + this.L);
        }
        abe e = abe.e(this.C);
        String h = e.h();
        String e2 = e.e();
        String j = e.j();
        String i = e.i();
        if (TextUtils.isEmpty(j) || TextUtils.isEmpty(i) || TextUtils.isEmpty(h) || TextUtils.isEmpty(e2)) {
            return;
        }
        a(abf.b(h, e2, j, i));
        e.a();
    }

    private ArrayList<SyncData> d(Bundle bundle, boolean z) {
        ArrayList<SyncData> arrayList = new ArrayList<>();
        try {
            if (z) {
                arrayList = aap.a(this.F.toString());
                this.F = new StringBuffer();
            } else {
                arrayList = bundle.getParcelableArrayList("cdata");
            }
        } catch (Exception e) {
            abd.b("CloudSyncBase", "getCloudData err: " + e.getMessage());
        }
        return arrayList;
    }

    private void e(Bundle bundle, boolean z) {
        c(bundle);
        if (b(bundle, z)) {
            r();
            i();
        }
    }

    private void f(Bundle bundle, boolean z) {
        ArrayList<UnstructData> arrayList = new ArrayList<>();
        ArrayList<UnstructData> arrayList2 = new ArrayList<>();
        aaz<Integer, List<String>> aazVar = new aaz<>();
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject(this.F.toString());
                JSONArray jSONArray = jSONObject.getJSONArray("sucUnstructData");
                JSONArray jSONArray2 = jSONObject.getJSONArray("failUnstructData");
                JSONArray c = abl.c(jSONObject, "fail_error_code_map");
                arrayList = aap.g(jSONArray);
                arrayList2 = aap.g(jSONArray2);
                aazVar = aap.b(c);
            } catch (JSONException unused) {
                abd.b("CloudSyncBase", "download save result error : JSONException");
            }
            this.F = new StringBuffer();
        } else {
            arrayList = bundle.getParcelableArrayList("unstructresult");
            arrayList2 = bundle.getParcelableArrayList("unstructfailresult");
            try {
                aazVar = (aaz) bundle.getSerializable("fail_error_code_map");
            } catch (ClassCastException e) {
                abd.b("CloudSyncBase", "download save result error:" + e.toString());
            }
        }
        ArrayList<UnstructData> arrayList3 = arrayList2;
        Map<Integer, List<String>> hashMap = new HashMap<>();
        if (aazVar != null && (hashMap = aazVar.b()) != null && !hashMap.isEmpty()) {
            abd.c("CloudSyncBase", "UnstructData failIdMap = " + hashMap.toString());
            this.E = false;
        }
        Map<Integer, List<String>> map = hashMap;
        if (((arrayList == null || arrayList.size() <= 0) && (arrayList3 == null || arrayList3.size() <= 0)) || a((List<UnstructData>) arrayList, (List<UnstructData>) arrayList3, map, true, 0)) {
            a(arrayList);
            boolean z2 = bundle.getBoolean("isCloudDataParticalSuc", false);
            int i = bundle.getInt("cloudDataParticalErrCode", -1);
            if (z2) {
                d(i);
            } else {
                i();
            }
        }
    }

    private void g(Bundle bundle, boolean z) {
        ArrayList<UnstructData> arrayList = new ArrayList<>();
        ArrayList<UnstructData> arrayList2 = new ArrayList<>();
        aaz<Integer, List<String>> aazVar = new aaz<>();
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject(this.F.toString());
                JSONArray jSONArray = jSONObject.getJSONArray("sucDownUnstructData");
                JSONArray jSONArray2 = jSONObject.getJSONArray("failDownUnstructData");
                JSONArray c = abl.c(jSONObject, "fail_error_code_map");
                arrayList = aap.g(jSONArray);
                arrayList2 = aap.g(jSONArray2);
                aazVar = aap.b(c);
            } catch (JSONException unused) {
                abd.b("CloudSyncBase", "download unstruct result error : JSONException");
            }
            this.F = new StringBuffer();
        } else {
            arrayList = bundle.getParcelableArrayList("unstructresult");
            arrayList2 = bundle.getParcelableArrayList("unstructfailresult");
            try {
                aazVar = (aaz) bundle.getSerializable("fail_error_code_map");
            } catch (ClassCastException e) {
                abd.b("CloudSyncBase", "download save result error:" + e.toString());
            }
        }
        ArrayList<UnstructData> arrayList3 = arrayList;
        ArrayList<UnstructData> arrayList4 = arrayList2;
        Map<Integer, List<String>> hashMap = new HashMap<>();
        if (aazVar != null && (hashMap = aazVar.b()) != null && !hashMap.isEmpty()) {
            abd.c("CloudSyncBase", "failMap : " + hashMap.toString());
            this.E = false;
        }
        a(arrayList3);
        a((List<UnstructData>) arrayList3, (List<UnstructData>) arrayList4, hashMap, false, 0);
        this.G.e(this.O);
    }

    private void h(Bundle bundle, boolean z) {
        abd.a("CloudSyncBase", "processQueryCloudDataResult");
        ArrayList<SyncData> d2 = d(bundle, z);
        if (d2 == null || d2.isEmpty()) {
            abd.b("CloudSyncBase", "processQueryCloudDataResult cloudData is null or bundle parse err empty");
            int i = bundle.getInt("resultCode", 5002);
            a("ProcessQueryCloudDataResult", "cloudData is null or bundle parse err empty", i != 0 ? i : 5002);
            return;
        }
        if (bundle.getBoolean("isCloudDataParticalSuc", false)) {
            d(d2);
            HashMap hashMap = new HashMap();
            a(bundle, hashMap);
            f(!hashMap.isEmpty() ? hashMap.keySet().iterator().next().intValue() : -1);
        } else {
            d(d2);
            j();
        }
        this.l.clear();
        this.m.clear();
        this.n.clear();
    }

    private void p() {
        List<String> b = b(this.h);
        a(abf.c(this.y, h(), this.h.size(), b == null ? 0 : b.size()));
        if (b == null) {
            abd.b("CloudSyncBase", "App delete struct data result is null");
            return;
        }
        this.h.clear();
        abd.c("CloudSyncBase", "App delete struct data result.size = " + b.size());
        c(b);
    }

    private void q() {
        int size = this.D.size();
        int i = this.B;
        if (size >= i) {
            f(new ArrayList(this.D.subList(0, i)));
        } else {
            f(new ArrayList(this.D));
        }
    }

    private void r() {
        this.D.addAll(this.f);
        this.D.addAll(this.g);
        this.D.addAll(this.i);
    }

    private boolean s() {
        if (aaw.e(this.x, this.C)) {
            return true;
        }
        abd.c("CloudSyncBase", "Does not meet the sync condition, stop sync");
        a(-3);
        return false;
    }

    private void t() {
        this.p.clear();
        this.o.clear();
        this.r.clear();
        this.q.clear();
        for (String str : this.f129a) {
            if (this.I.get(str) == null) {
                abd.b("CloudSyncBase", "lIdsMap get add is null");
            } else if (abl.c(this.I.get(str).getHaveFile())) {
                this.p.add(str);
            } else {
                this.o.add(str);
            }
        }
        abd.c("CloudSyncBase", "addFileId.size = " + this.p.size() + ", addId.size = " + this.o.size());
        for (String str2 : this.b) {
            if (this.I.get(str2) == null) {
                abd.b("CloudSyncBase", "lIdsMap get update is null");
            } else if (abl.c(this.I.get(str2).getHaveFile())) {
                this.r.add(str2);
            } else {
                this.q.add(str2);
            }
        }
        abd.c("CloudSyncBase", "modifyFileId.size = " + this.r.size() + ", modifyId.size = " + this.q.size());
    }

    private void u() {
        String c = abl.c();
        abd.c("CloudSyncBase", "recordServiceDisconnectedTime serviceDisconnectedTime = " + c + ", traceId = " + this.L);
        abe.e(this.C).a(this.x, this.y, c, this.L);
    }

    private boolean v() {
        boolean z;
        if (abl.c(this.r)) {
            z = false;
        } else {
            Iterator<String> it = this.r.iterator();
            while (it.hasNext()) {
                this.t.add(it.next());
                if (this.B == this.t.size() + this.u.size()) {
                    b(true);
                    return true;
                }
            }
            z = true;
        }
        if (!abl.c(this.p)) {
            Iterator<String> it2 = this.p.iterator();
            while (it2.hasNext()) {
                this.s.add(it2.next());
                if (this.B == this.s.size() + this.t.size() + this.u.size()) {
                    b(true);
                    return true;
                }
            }
            z = true;
        }
        if (!abl.c(this.d)) {
            Iterator<String> it3 = this.d.iterator();
            while (it3.hasNext()) {
                this.v.add(it3.next());
                if (this.B == this.s.size() + this.t.size() + this.u.size() + this.v.size()) {
                    b(true);
                    return true;
                }
            }
            z = true;
        }
        if (abl.c(this.p) && abl.c(this.r) && abl.c(this.d)) {
            return z;
        }
        b(true);
        return true;
    }

    private boolean w() {
        boolean z;
        if (abl.c(this.c)) {
            z = false;
        } else {
            Iterator<String> it = this.c.iterator();
            while (it.hasNext()) {
                this.u.add(it.next());
                if (this.B == this.u.size()) {
                    b(false);
                    return true;
                }
            }
            z = true;
        }
        if (!abl.c(this.q)) {
            Iterator<String> it2 = this.q.iterator();
            while (it2.hasNext()) {
                this.t.add(it2.next());
                if (this.B == this.t.size() + this.u.size()) {
                    b(false);
                    return true;
                }
            }
            z = true;
        }
        if (!abl.c(this.o)) {
            Iterator<String> it3 = this.o.iterator();
            while (it3.hasNext()) {
                this.s.add(it3.next());
                if (this.B == this.s.size() + this.t.size() + this.u.size()) {
                    b(false);
                    return true;
                }
            }
            z = true;
        }
        if (!abl.c(this.d)) {
            Iterator<String> it4 = this.d.iterator();
            while (it4.hasNext()) {
                this.v.add(it4.next());
                if (this.B == this.s.size() + this.t.size() + this.u.size() + this.v.size()) {
                    b(false);
                    return true;
                }
            }
            z = true;
        }
        if (abl.c(this.o) && abl.c(this.q) && abl.c(this.c) && abl.c(this.d)) {
            return z;
        }
        b(false);
        return true;
    }

    protected abstract void a(Bundle bundle);

    protected abstract void a(Bundle bundle, boolean z);

    protected abstract void a(String str, String str2, List<SyncData> list, List<SyncData> list2, List<String> list3, List<SyncData> list4, boolean z, ISyncServiceCallback iSyncServiceCallback);

    protected abstract void a(ArrayList<UnstructData> arrayList);

    protected abstract void a(List<SyncData> list, List<String> list2, int i);

    protected abstract ArrayList<String> b(ArrayList<String> arrayList);

    protected abstract void b(Bundle bundle);

    protected abstract void b(List<SyncData> list, List<String> list2);

    protected abstract boolean b(Bundle bundle, boolean z);

    protected abstract void c(ArrayList<SyncData> arrayList);

    protected abstract void c(List<String> list);

    protected abstract void f();

    protected abstract void f(int i);

    public void i() {
        abd.c("CloudSyncBase", "Download data from cloud, needDownloadGuids.size = " + this.D.size());
        if (!this.D.isEmpty()) {
            q();
            return;
        }
        List<String> list = this.h;
        if (list == null || list.isEmpty()) {
            f();
        } else {
            p();
        }
    }

    protected abstract void j();

    protected abstract void k();

    public void l() {
        if (d()) {
            t();
            m();
        }
    }

    public void m() {
        abd.c("CloudSyncBase", "process localdata to cloud.");
        this.u = new ArrayList(10);
        this.t = new ArrayList(10);
        this.s = new ArrayList(10);
        this.v = new ArrayList(10);
        if (w() || v()) {
            return;
        }
        abd.c("CloudSyncBase", "dataSyncEnd: isAllUnstructDataSuccess = " + this.E);
        a(!this.E ? 122 : 0);
        abd.c("CloudSyncBase", "end process localdata to cloud.");
    }

    protected abstract void n();

    public List<SyncData> o() {
        List<zu> a2 = a(this.l, this.m);
        g(a2);
        if (a2 == null) {
            abd.b("CloudSyncBase", "App update struct data result is null");
            return null;
        }
        List<SyncData> e = e(a2);
        if (s()) {
            return e;
        }
        return null;
    }

    private List<SyncData> e(List<zu> list) {
        abd.c("CloudSyncBase", "App update result size = " + list.size());
        ArrayList arrayList = new ArrayList(100);
        for (zu zuVar : list) {
            SyncData syncData = this.H.get(zuVar.c());
            if (syncData != null) {
                syncData.setLuid(zuVar.a());
                syncData.setRecycleStatus(zuVar.e());
                syncData.setDeleteFileList(zuVar.b());
                syncData.setDownFileList(zuVar.d());
                arrayList.add(syncData);
            } else {
                abd.b("CloudSyncBase", "Update result data is null, guid = " + zuVar.c());
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(Message message, Bundle bundle, boolean z, int i) {
        try {
            int i2 = message.what;
            if (i2 != 10002) {
                if (i2 != 10004) {
                    if (i2 == 10007 && bundle.getBoolean("fail_error_need_update_sync_result", false)) {
                        abd.d("CloudSyncBase", "handle message, upload partial success, and result error, result = " + i);
                        i(bundle, z);
                        return true;
                    }
                } else if (bundle.getBoolean("isCloudDataParticalSuc", false)) {
                    abd.d("CloudSyncBase", "handle message, after download partial success");
                    f(bundle, z);
                    return true;
                }
            } else if (bundle.getBoolean("isCloudDataParticalSuc", false)) {
                abd.d("CloudSyncBase", "handle message, download partial success, and result error, result = " + i);
                h(bundle, z);
                return true;
            }
        } catch (Exception e) {
            abd.b("CloudSyncBase", "parseParticalResult err " + e.getMessage());
        }
        return false;
    }

    private void d(ArrayList<SyncData> arrayList) {
        this.l = new ArrayList<>(10);
        this.m = new ArrayList<>(10);
        this.n = new ArrayList<>(10);
        Iterator<SyncData> it = arrayList.iterator();
        while (it.hasNext()) {
            SyncData next = it.next();
            if (this.f.contains(next.getGuid())) {
                this.l.add(next);
            } else if (this.g.contains(next.getGuid())) {
                next.setLuid(this.H.get(next.getGuid()).getLuid());
                this.m.add(next);
            } else if (this.i.contains(next.getGuid())) {
                next.setLuid(this.H.get(next.getGuid()).getLuid());
                this.n.add(next);
            }
        }
    }

    private void i(Bundle bundle, boolean z) {
        ArrayList<SyncData> c;
        ArrayList<String> d2;
        ArrayList<SyncData> arrayList;
        new ArrayList();
        new ArrayList();
        new ArrayList();
        aaz<Integer, List<String>> aazVar = new aaz<>();
        if (z) {
            try {
                JSONObject jSONObject = new JSONObject(this.F.toString());
                JSONArray jSONArray = jSONObject.getJSONArray("modifyData");
                JSONArray jSONArray2 = jSONObject.getJSONArray("addData");
                String d3 = abl.d(jSONObject, "deleteData");
                JSONArray c2 = abl.c(jSONObject, "failErrorCodeMap");
                ArrayList<SyncData> c3 = aap.c(jSONArray);
                c = aap.c(jSONArray2);
                d2 = abl.d(d3);
                aaz<Integer, List<String>> b = aap.b(c2);
                this.F = new StringBuffer();
                aazVar = b;
                arrayList = c3;
            } catch (JSONException e) {
                String str = "processUploadResult error: " + e.getMessage();
                abd.b("CloudSyncBase", str);
                a("ProcessUploadResult", str, 5001);
                return;
            }
        } else {
            arrayList = bundle.getParcelableArrayList("cmod");
            c = bundle.getParcelableArrayList("cadd");
            d2 = abl.fu_(bundle, "cdel");
            try {
                aazVar = (aaz) bundle.getSerializable("fail_error_code_map");
            } catch (ClassCastException e2) {
                abd.b("CloudSyncBase", "download save result error:" + e2.toString());
            }
        }
        Map<Integer, List<String>> hashMap = new HashMap<>();
        if (aazVar != null) {
            hashMap = aazVar.b() != null ? aazVar.b() : new HashMap<>();
            if (!hashMap.isEmpty()) {
                abd.c("CloudSyncBase", "processUpload failMap.size = " + hashMap.size() + ",  failMap = " + hashMap.toString());
                this.E = false;
            }
        }
        ArrayList arrayList2 = new ArrayList();
        if (c == null) {
            abd.d("CloudSyncBase", "addData is null, new arrayList");
            a("ProcessUploadResult", "addData is null, new arrayList", 5001);
            return;
        }
        if (arrayList == null) {
            abd.d("CloudSyncBase", "modifyData is null, new arrayList");
            a("ProcessUploadResult", "modifyData is null, new arrayList", 5001);
            return;
        }
        arrayList2.addAll(c);
        arrayList2.addAll(arrayList);
        ArrayList<String> b2 = b(d2);
        List<String> arrayList3 = new ArrayList<>(b2);
        if (b(c, arrayList, b2, hashMap) && s()) {
            int i = bundle.getInt("resultCode");
            boolean z2 = bundle.getBoolean("fail_error_need_update_sync_result", false);
            if (aal.a() >= 100 && z2) {
                a(arrayList2, arrayList3, i);
            } else {
                b(arrayList2, arrayList3);
            }
        }
    }

    public void c(Bundle bundle, boolean z) {
        a(abf.d(this.x, e()));
        JSONArray jSONArray = this.K;
        if (jSONArray != null && jSONArray.length() > 0) {
            abd.c("CloudSyncBase", "processEndSync: reportInfoArray = " + this.K.toString());
            this.G.a(this.x, this.K.toString(), false, this.O);
            this.K = null;
        } else {
            this.G.e(this.O);
        }
        abd.c("CloudSyncBase", "processEndSync: Call app SyncEnd");
        abl.c(this.w);
    }

    public boolean e(int i) {
        abd.c("CloudSyncBase", "isHiCloudVersionMatch, AppAbilityVersion: " + aal.a());
        if (i != 0 && i >= aal.a()) {
            return true;
        }
        abd.d("CloudSyncBase", "HiCloud version not match");
        abd.c("CloudSyncBase", "Call app HiCloudVersionMatch");
        this.w.hiCloudVersionTooLow(i);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(int i, Bundle bundle) {
        if (i != 10014) {
            return false;
        }
        abd.c("CloudSyncBase", "SendTimeOutMessage msg.what = " + i);
        long j = bundle.getLong("timeOut", 300000L);
        String string = bundle.getString("timeOutMethod", "");
        Message message = new Message();
        message.what = i;
        Bundle bundle2 = new Bundle();
        bundle2.putInt("resultCode", 0);
        bundle2.putString("timeOutMethod", string);
        message.obj = bundle2;
        this.N.sendMessageDelayed(message, j);
        return true;
    }

    public void d(int i) {
        abd.c("CloudSyncBase", "ifParticalSucNeedToStopDown returnCode = " + i);
        a(this.y, i);
    }

    public boolean h() {
        return aaw.c(this.x, this.y, this.C) && aal.e() && (this.M >= 104);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean d(Bundle bundle) {
        this.N.removeMessages(PrebakedEffectId.RT_CALENDAR_DATE);
        aau.e(this.O, 300000L, "allBatchReceiveOver");
        boolean z = bundle.getBoolean("is_send_over", false);
        abd.c("CloudSyncBase", "sendOverFlag: " + z);
        if (z) {
            return true;
        }
        try {
            byte[] byteArray = bundle.getByteArray("batches_data_bytes_key");
            if (byteArray != null) {
                this.F.append(new String(byteArray, "UTF-8"));
            }
            return false;
        } catch (UnsupportedEncodingException e) {
            abd.d("CloudSyncBase", "isAllBatchesReceiveOver: " + e.toString());
            this.F = new StringBuffer();
            return true;
        }
    }

    private void e(Bundle bundle) {
        abd.c("CloudSyncBase", "onExceedLimitResult");
        aaz aazVar = new aaz();
        String string = bundle.getString("get_exceed_from_channel", "exceed_from_local");
        abd.c("CloudSyncBase", "limists from = " + string);
        try {
            aazVar = (aaz) bundle.getSerializable("get_datatypes_limits_result");
        } catch (ClassCastException e) {
            abd.b("CloudSyncBase", "onExceedLimitResult get map error:" + e.toString());
        }
        Map<String, Long> hashMap = new HashMap<>();
        if (aazVar != null) {
            hashMap = aazVar.b();
        }
        if ("exceed_from_service".equals(string)) {
            abl.b(this.C, this.x, hashMap);
        }
        a(this.x, hashMap);
        abd.c("CloudSyncBase", "onExceedLimitResult do unbind servie");
        this.G.e(this.O);
    }

    public String c(int i) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("isCloudDataParticalSuc", true);
            jSONObject.put("cloudDataParticalErrCode", i);
        } catch (Exception e) {
            abd.b("CloudSyncBase", "buildNotifyMsg err " + e.getMessage());
        }
        return jSONObject.toString();
    }

    public void g() {
        this.G.e(this.O);
    }

    private void g(List<zu> list) {
        String str;
        int i;
        ArrayList<SyncData> arrayList = this.l;
        int i2 = 0;
        int size = arrayList == null ? 0 : arrayList.size();
        ArrayList<SyncData> arrayList2 = this.m;
        int size2 = arrayList2 == null ? 0 : arrayList2.size();
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("dataType", this.y);
            if (h()) {
                int d2 = d((List<SyncData>) this.l);
                int d3 = d((List<SyncData>) this.m);
                if (list != null) {
                    Iterator<zu> it = list.iterator();
                    i = 0;
                    while (it.hasNext()) {
                        if (it.next().e() == -1) {
                            i++;
                        } else {
                            i2++;
                        }
                    }
                } else {
                    i = 0;
                }
                jSONObject.put("addedNum", size - d2);
                jSONObject.put("recycleAddedNum", d2);
                jSONObject.put("modifiedNum", size2 - d3);
                jSONObject.put("recycleModifiedNum", d3);
                jSONObject.put("AppUpdateResultNum", i2);
                jSONObject.put("AppRecycleUpdateResultNum", i);
            } else {
                jSONObject.put("addedNum", size);
                jSONObject.put("modifiedNum", size2);
                if (list != null) {
                    i2 = list.size();
                }
                jSONObject.put("AppUpdateResultNum", i2);
            }
            abf.d(jSONObject);
            str = jSONObject.toString();
        } catch (JSONException unused) {
            abd.b("CloudSyncBase", "reportUpdateStructData error: JSONException");
            str = "";
        }
        abd.c("CloudSyncBase", "updateStructData: " + str);
        a(abf.e(str));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Message message, Bundle bundle, boolean z) {
        this.N.removeMessages(PrebakedEffectId.RT_CALENDAR_DATE);
        switch (message.what) {
            case 10001:
                e(bundle, z);
                break;
            case 10002:
                h(bundle, z);
                break;
            case 10003:
                m();
                break;
            case 10004:
                f(bundle, z);
                break;
            case 10005:
                g(bundle, z);
                break;
            case 10006:
            case 10016:
            case 10018:
            case 10019:
            case 10020:
            case 10021:
            default:
                abd.d("CloudSyncBase", "Receive error msg, what = " + message.what);
                a("ProcessMessage", "Receive error msg", 5001);
                break;
            case 10007:
                i(bundle, z);
                break;
            case 10008:
                l();
                break;
            case 10009:
                c(bundle, z);
                break;
            case 10010:
                b(bundle);
                break;
            case 10011:
                a(bundle);
                break;
            case 10012:
                k();
                break;
            case 10013:
                u();
                break;
            case PrebakedEffectId.RT_CALENDAR_DATE /* 10014 */:
                f(bundle);
                break;
            case 10015:
                e(bundle);
                break;
            case 10017:
                a(bundle, z);
                break;
            case 10022:
                n();
                break;
        }
    }

    private int d(List<SyncData> list) {
        int i = 0;
        if (list != null) {
            Iterator<SyncData> it = list.iterator();
            while (it.hasNext()) {
                if (it.next().getRecycleStatus() == -1) {
                    i++;
                }
            }
        }
        return i;
    }

    private void f(List<String> list) {
        if (s()) {
            this.G.d(this.x, this.y, list, this.O);
            this.D.removeAll(list);
        }
    }

    private void f(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        String string = bundle.getString("timeOutMethod", "");
        abd.c("CloudSyncBase", "ReceiveTimeOutMessage msg.what = 10014, method = " + string);
        if (!TextUtils.equals(string, "endSync") && !TextUtils.equals(string, "endSyncV100")) {
            a(-10);
        } else {
            c(new Bundle(), false);
        }
    }

    private boolean a(Bundle bundle, Map<Integer, List<String>> map) {
        try {
            aaz aazVar = (aaz) bundle.getSerializable("fail_error_code_map");
            if (aazVar == null) {
                return true;
            }
            map.putAll(aazVar.b() != null ? aazVar.b() : new HashMap<>());
            if (map.isEmpty()) {
                return true;
            }
            abd.c("CloudSyncBase", "getParticalCloudData failMap.size = " + map.size() + ",  failMap = " + map.toString());
            return true;
        } catch (Exception e) {
            abd.b("CloudSyncBase", "getParticalCloudData err: " + e.getMessage());
            return false;
        }
    }

    protected void a(String str, String str2, List<SyncData> list, List<SyncData> list2, List<String> list3, boolean z, ISyncServiceCallback iSyncServiceCallback) {
        this.G.d(str, str2, list, list2, list3, z, iSyncServiceCallback);
    }

    private void a(boolean z) {
        if (z) {
            Iterator<String> it = this.t.iterator();
            while (it.hasNext()) {
                this.r.remove(it.next());
            }
            Iterator<String> it2 = this.s.iterator();
            while (it2.hasNext()) {
                this.p.remove(it2.next());
            }
            Iterator<String> it3 = this.v.iterator();
            while (it3.hasNext()) {
                this.d.remove(it3.next());
            }
            return;
        }
        Iterator<String> it4 = this.u.iterator();
        while (it4.hasNext()) {
            this.c.remove(it4.next());
        }
        Iterator<String> it5 = this.t.iterator();
        while (it5.hasNext()) {
            this.q.remove(it5.next());
        }
        Iterator<String> it6 = this.s.iterator();
        while (it6.hasNext()) {
            this.o.remove(it6.next());
        }
        Iterator<String> it7 = this.v.iterator();
        while (it7.hasNext()) {
            this.d.remove(it7.next());
        }
    }

    public void a(String str, String str2, int i, int i2, int i3) {
        if (a(str, str2, i, i2)) {
            b(str, str2, i3);
        }
    }

    public boolean a(String str, String str2, int i, int i2) {
        this.x = str;
        this.y = str2;
        this.z = i;
        this.B = abl.a(i2);
        return s();
    }

    private boolean b(List<SyncData> list, List<SyncData> list2, List<String> list3, Map<Integer, List<String>> map) {
        abd.c("CloudSyncBase", "Call app updateSyncResult begin");
        a(list, list2, list3, map);
        try {
            this.w.updateSyncResult(this.y, list, list2, list3, map);
            abd.c("CloudSyncBase", "Call app updateSyncResult end");
            return true;
        } catch (aag e) {
            a("updateSyncResult", e);
            return false;
        }
    }

    public void a(String str, String str2, List<UnstructData> list) {
        abd.c("CloudSyncBase", "downUnstructFile ,syncType = " + str + " , unstructArry = " + list.toString());
        this.x = str;
        this.y = str2;
        if (!aaw.e(this.x, this.C)) {
            abd.c("CloudSyncBase", "downUnstructFile ,switch is close");
            a((List<UnstructData>) null, list, (Map<Integer, List<String>>) null, false, -3);
        } else {
            this.G.e(str, str2, list, this.O);
        }
    }

    protected void b(String str, String str2, int i) {
        abd.c("CloudSyncBase", "notifySyncModel dataType = " + str2 + ", syncModel = " + i);
        aal.c();
        n();
    }

    private boolean a(List<UnstructData> list, List<UnstructData> list2, Map<Integer, List<String>> map, boolean z, int i) {
        String str;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("dataType", this.y);
            jSONObject.put("isSync", z);
            jSONObject.put("sucUnStructDataNum", list == null ? 0 : list.size());
            jSONObject.put("failUnStructDataNum", list2 == null ? 0 : list2.size());
            jSONObject.put("failErrorCodeMap", map == null ? 0 : map.toString());
            abf.d(jSONObject);
            str = jSONObject.toString();
        } catch (JSONException unused) {
            abd.b("CloudSyncBase", "unStructDownEnd error: JSONException");
            str = "";
        }
        abd.c("CloudSyncBase", "Call app unStructDownEnd: " + str);
        a(abf.c(str));
        try {
            try {
                this.w.onUnstructDataDownloadEnd(this.y, list, list2, map, z, i);
                if (z) {
                    return true;
                }
            } catch (aag e) {
                if (z) {
                    a("onUnstructDataDownloadEnd", e);
                    if (!z) {
                        abd.c("CloudSyncBase", "unStructDownEnd: Call app SyncEnd");
                        abl.c(this.w);
                    }
                    return false;
                }
                abd.b("CloudSyncBase", "onUnstructDataDownloadEnd error: code = " + e.b() + ", msg = " + e.getMessage());
                if (z) {
                    return true;
                }
            }
            abd.c("CloudSyncBase", "unStructDownEnd: Call app SyncEnd");
            abl.c(this.w);
            return true;
        } catch (Throwable th) {
            if (!z) {
                abd.c("CloudSyncBase", "unStructDownEnd: Call app SyncEnd");
                abl.c(this.w);
            }
            throw th;
        }
    }

    public void a(String str, List<String> list, List<String> list2) {
        abd.c("CloudSyncBase", "App call: endSync");
        this.G.c(str, list, list2, this.O);
    }

    public void a(int i, String str) {
        abd.c("CloudSyncBase", "report: returnCode = " + i + ", errorReason: " + str);
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("business_id", "local_app_report");
            jSONObject.put("returnCode", i);
            jSONObject.put(HiAnalyticsConstant.HaKey.BI_KEY_ERRORREASON, str);
            abf.d(jSONObject);
            if (this.K == null) {
                this.K = new JSONArray();
            }
            this.K.put(jSONObject);
        } catch (JSONException unused) {
            abd.c("CloudSyncBase", "report error: JSONException");
        }
    }

    public void a(String str, int i, String str2) {
        abd.c("CloudSyncBase", "reportSyncRsn syncType = " + str);
        this.A = i;
        try {
            String packageName = this.C.getPackageName();
            this.G.c(str, packageName, this.C.getPackageManager().getPackageInfo(packageName, 0).versionName, str2, this.O);
        } catch (Exception e) {
            abd.b("CloudSyncBase", "reportSyncRsn error: " + e.toString());
        }
    }

    public void a(String str, String str2, int i, JSONObject jSONObject) {
        this.A = i;
        try {
            String packageName = this.C.getPackageName();
            String str3 = this.C.getPackageManager().getPackageInfo(packageName, 0).versionName;
            JSONObject jSONObject2 = new JSONObject();
            if (jSONObject != null) {
                jSONObject2 = new JSONObject(jSONObject.toString());
            }
            if (i != -1) {
                jSONObject2.put("syncRsn", i);
            }
            abf.d(jSONObject2);
            abd.a("CloudSyncBase", "reportSyncInfo = " + jSONObject2.toString());
            this.G.b(str, str2, packageName, str3, jSONObject2.toString(), this.O);
        } catch (Exception e) {
            abd.b("CloudSyncBase", "reportSyncInfo error: " + e.toString());
        }
    }

    @Override // defpackage.aas
    public void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        this.G.a(this.x, str, true, this.O);
    }

    private void a(List<SyncData> list, List<SyncData> list2, List<String> list3, Map<Integer, List<String>> map) {
        String str;
        int size = list == null ? 0 : list.size();
        int size2 = list2 == null ? 0 : list2.size();
        int size3 = list3 != null ? list3.size() : 0;
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("dataType", this.y);
            if (h()) {
                int d2 = d(list);
                int d3 = d(list2);
                jSONObject.put("addedNum", size - d2);
                jSONObject.put("recycleAddedNum", d2);
                jSONObject.put("modifiedNum", size2 - d3);
                jSONObject.put("recycleModifiedNum", d3);
            } else {
                jSONObject.put("addedNum", size);
                jSONObject.put("modifiedNum", size2);
                jSONObject.put("deletedNum", size3);
            }
            if (map != null) {
                jSONObject.put("failErrorCodeMap", map.toString());
            }
            abf.d(jSONObject);
            str = jSONObject.toString();
        } catch (JSONException unused) {
            abd.b("CloudSyncBase", "reportUpdateSyncResult error: JSONException");
            str = "";
        }
        abd.c("CloudSyncBase", "Call app updateSyncResult, " + str);
        a(abf.b(str));
    }

    public int a(Map<String, String> map, String str) {
        if (map != null && map.size() != 0) {
            abd.c("CloudSyncBase", "EtagOperator to batUpdate4Luid");
            int a2 = new zy().a(map, str);
            abd.c("CloudSyncBase", "contact recycle replaceEtagLuid, result = " + a2);
            if (a2 != 0) {
                return -1;
            }
            return a2;
        }
        abd.d("CloudSyncBase", "replaceEtagLuid luidMap is null or size is 0");
        return -2;
    }

    public void a(Context context, String str, List<String> list) {
        HashMap hashMap = new HashMap();
        for (String str2 : list) {
            long c = abe.e(context).c(str, str2);
            if (-1 != c) {
                hashMap.put(str2, Long.valueOf(c));
            }
        }
        this.x = str;
        if (abl.d(this.C, str, list, hashMap)) {
            abd.c("CloudSyncBase", "getExceedLimitNum: " + str + " in 24 hours, get limists from sp");
            a(hashMap);
            return;
        }
        abd.c("CloudSyncBase", "clear sp limits");
        abe.e(context).b(str, list);
        abe.e(context).a(str, list);
        abd.c("CloudSyncBase", "getExceedLimitNum: " + str + " upper 24 hours, get limists from server");
        this.G.b(str, list, this.O);
    }

    private void a(Map<String, Long> map) {
        try {
            Bundle bundle = new Bundle();
            bundle.putInt("resultCode", 0);
            bundle.putString("get_exceed_from_channel", "exceed_from_local");
            aaz aazVar = new aaz();
            aazVar.e(map);
            bundle.putSerializable("get_datatypes_limits_result", aazVar);
            this.O.handlerEventMsg(10015, 0, 0, bundle);
        } catch (Exception e) {
            abd.b("CloudSyncBase", "getExceedLimitsFromSp error: " + e.getMessage());
        }
    }
}
