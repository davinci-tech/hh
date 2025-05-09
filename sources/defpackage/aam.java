package defpackage;

import android.content.Context;
import android.os.Bundle;
import com.huawei.android.hicloud.sync.logic.SyncProcessInterface;
import com.huawei.android.hicloud.sync.logic.c;
import com.huawei.android.hicloud.sync.service.aidl.Ctag;
import com.huawei.android.hicloud.sync.service.aidl.CtagInfo;
import com.huawei.android.hicloud.sync.service.aidl.Etag;
import com.huawei.android.hicloud.sync.service.aidl.ISyncServiceCallback;
import com.huawei.android.hicloud.sync.service.aidl.LocalId;
import com.huawei.android.hicloud.sync.service.aidl.ParcelableMap;
import com.huawei.android.hicloud.sync.service.aidl.SyncData;
import com.huawei.android.hicloud.sync.service.aidl.SyncDataCompatible;
import com.huawei.android.hicloud.sync.service.aidl.UnstructData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
class aam extends c {
    private ArrayList<String> aa;
    private Map<String, SyncData> ab;
    private final Map<String, Etag> ac;
    private final Map<String, Ctag> ad;

    aam(Context context, String str, SyncProcessInterface syncProcessInterface, aau aauVar) {
        super(context, str, syncProcessInterface, aauVar);
        this.ad = new HashMap();
        this.ac = new HashMap();
        this.ab = new HashMap();
        this.aa = new ArrayList<>();
        if (context != null) {
            zz.e(this.C);
        }
    }

    private String b(String str) {
        ArrayList<CtagInfo> c = new aab().c(new String[]{str});
        if (c == null || c.size() == 0 || c.get(0) == null) {
            return null;
        }
        String ctagValue = c.get(0).getCtagValue();
        abd.a("CloudSyncV100", "Local ctag = " + ctagValue);
        return ctagValue;
    }

    private Map<String, SyncData> c(String str) {
        HashMap hashMap = new HashMap(10);
        zy zyVar = new zy();
        ArrayList<SyncData> e = h() ? zyVar.e(str) : zyVar.d(str);
        if (e.size() == 0) {
            abd.c("CloudSyncV100", "Query local etag, dataType = " + str + ", size = 0");
            return hashMap;
        }
        Iterator<SyncData> it = e.iterator();
        while (it.hasNext()) {
            SyncData next = it.next();
            if (next != null) {
                hashMap.put(next.getLuid(), next);
            }
        }
        abd.c("CloudSyncV100", "Query local etag, dataType: " + str + ", size = " + hashMap.size());
        return hashMap;
    }

    private boolean d(String str) {
        Ctag e = e(str);
        if (e == null || e.getStatus() == 203) {
            return true;
        }
        String b = b(str);
        abd.c("CloudSyncV100", "isCloudDataChanged dataType = " + str + ", localCtag : " + b + ", cloudCtag : " + e.getCtag());
        a(abf.d(this.y, b, e.getCtag()));
        return b == null || !b.equals(e.getCtag());
    }

    private void e(List<SyncData> list) {
        abd.c("CloudSyncV100", "batchUpdateFile: saveData.size = " + list.size());
        ArrayList<UnstructData> arrayList = new ArrayList<>();
        ArrayList<UnstructData> arrayList2 = new ArrayList<>();
        for (SyncData syncData : list) {
            if (syncData != null) {
                List<UnstructData> fileList = syncData.getFileList();
                if (fileList != null && !fileList.isEmpty()) {
                    abd.c("CloudSyncV100", "fileList: " + fileList.toString());
                    arrayList.addAll(fileList);
                }
                List<UnstructData> deleteFileList = syncData.getDeleteFileList();
                if (deleteFileList != null && !deleteFileList.isEmpty()) {
                    abd.c("CloudSyncV100", "deleteFileList: " + deleteFileList.toString());
                    arrayList2.addAll(deleteFileList);
                }
            }
        }
        a(arrayList);
        d(arrayList2);
    }

    private void eT_(Bundle bundle, boolean z) throws ClassCastException, JSONException {
        Map<? extends String, ? extends Etag> map;
        Map<? extends String, ? extends Ctag> map2;
        if (z) {
            b(new JSONObject(this.F.toString()));
            this.F = new StringBuffer();
            return;
        }
        ParcelableMap parcelableMap = (ParcelableMap) bundle.getParcelable("cloud_ctag_map");
        ParcelableMap parcelableMap2 = (ParcelableMap) bundle.getParcelable("cloud_etag_map");
        if (parcelableMap != null && (map2 = parcelableMap.getMap()) != null) {
            this.ad.putAll(map2);
            abd.c("CloudSyncV100", "CloudCtag, CloudCtagMap.size = " + this.ad.size());
        }
        if (parcelableMap2 == null || (map = parcelableMap2.getMap()) == null) {
            return;
        }
        this.ac.clear();
        this.ac.putAll(map);
        abd.c("CloudSyncV100", "CloudEtag, CloudEtagMap.size = " + this.ac.size());
    }

    private void f(List<LocalId> list) {
        Iterator<LocalId> it = list.iterator();
        while (it.hasNext()) {
            this.f129a.add(it.next().getId());
        }
    }

    private void h(List<LocalId> list) {
        for (LocalId localId : list) {
            String id = localId.getId();
            if (!this.ab.containsKey(id)) {
                this.f129a.add(id);
            } else if (localId.getDirty() == 1) {
                this.b.add(id);
            }
        }
    }

    private void r() {
        for (Map.Entry<String, Etag> entry : this.ac.entrySet()) {
            String key = entry.getKey();
            Etag value = entry.getValue();
            if (value == null || value.getEtag() == null) {
                abd.b("CloudSyncV100", "Etag is null, guid = " + key);
            } else {
                SyncData c = aap.c(key, value.getEtag());
                if (value.getOperation() != 2) {
                    this.f.add(key);
                    this.H.put(key, c);
                }
            }
        }
    }

    private int s() {
        Ctag ctag;
        abd.c("CloudSyncV100", "updateLocalCtag");
        try {
            if (this.ad.containsKey(this.y) && (ctag = this.ad.get(this.y)) != null) {
                String ctag2 = ctag.getCtag();
                CtagInfo ctagInfo = new CtagInfo();
                ctagInfo.setCtagName(this.y);
                ctagInfo.setCtagValue(ctag2);
                ctagInfo.setSyncToken(ctag.getSyncToken());
                abd.c("CloudSyncV100", "updateLocalCtag, dataType: " + this.y + ", cTagValue: " + ctag2 + ", syncToken: " + ctag.getSyncToken());
                ArrayList arrayList = new ArrayList();
                arrayList.add(ctagInfo);
                n(arrayList);
            }
            return 0;
        } catch (Exception e) {
            abd.b("CloudSyncV100", "updateLocalCtag exception: " + e.getClass().getName());
            a(5);
            return 5;
        }
    }

    private void t() {
        List<String> list = this.f;
        if (list != null) {
            list.clear();
        }
        List<String> list2 = this.g;
        if (list2 != null) {
            list2.clear();
        }
        List<String> list3 = this.h;
        if (list3 != null) {
            list3.clear();
        }
        Map<String, SyncData> map = this.H;
        if (map != null) {
            map.clear();
        }
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public void a(Bundle bundle) {
        this.M = bundle.getInt("hicloud_new_version");
        abd.c("CloudSyncV100", "newVersion = " + this.M);
        if (e(this.M)) {
            String b = b(this.y);
            abd.c("CloudSyncV100", "localCtag :" + b);
            this.G.d(this.x, this.y, b, this.z, this.A, this.O);
        }
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public void b(Bundle bundle) {
        abd.b("CloudSyncV100", "V2 version should not reach processGetOldVersion.");
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public void f(int i) {
        abd.c("CloudSyncV100", "processParitcalSucQueryResult errcode = " + i);
        List<SyncData> o = o();
        if (o != null && e(o, (List<String>) null) == 0) {
            d(o);
            this.G.c(this.x, this.y, c(i), o, null, false, this.O);
        }
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public void k() {
        super.l();
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public void l() {
        String str;
        boolean h = h();
        try {
            if ("contact".equals(this.y)) {
                Thread.sleep(300L);
            }
        } catch (InterruptedException e) {
            abd.c("CloudSyncV100", "Before app query localId wait error, " + e.getMessage());
        }
        List<LocalId> b = b(!h ? 1 : 0);
        if (b == null) {
            abd.b("CloudSyncV100", "App query localId dataType = " + this.y + ", localIdList is null");
            return;
        }
        abd.c("CloudSyncV100", "App query localId dataType = " + this.y + ", localIdListNum = " + b.size());
        this.I.clear();
        for (LocalId localId : b) {
            this.I.put(localId.getId(), localId);
        }
        this.f129a.clear();
        this.b.clear();
        this.c.clear();
        this.d.clear();
        i(b);
        if (!h) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("dataType", this.y);
                jSONObject.put("localIdNum", b.size());
                jSONObject.put("localETagNum", this.ab.size());
                jSONObject.put("addedNum", this.f129a.size());
                jSONObject.put("modifiedNum", this.b.size());
                jSONObject.put("deletedNum", this.c.size());
                jSONObject.put("lostNum", this.d.size());
                abf.d(jSONObject);
                str = jSONObject.toString();
            } catch (JSONException unused) {
                abd.b("CloudSyncV100", "local_changes error: JSONException");
                str = "";
            }
            abd.c("CloudSyncV100", "local_changes: " + str);
            a(abf.d(str));
        }
        if (this.M >= 103) {
            d(b, Boolean.valueOf(h));
        } else {
            super.l();
        }
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public void n() {
        this.G.a(this.O);
    }

    private void g(List<LocalId> list) {
        HashSet hashSet = new HashSet(list.size());
        Iterator<LocalId> it = list.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().getId());
        }
        HashSet hashSet2 = new HashSet(this.ab.keySet());
        hashSet2.removeAll(hashSet);
        this.c.addAll(hashSet2);
    }

    private void n(List<CtagInfo> list) {
        if (list == null) {
            return;
        }
        abd.c("CloudSyncV100", "updateCtagInfoList, list size = " + list.size());
        try {
            new aab().c(list);
        } catch (Exception e) {
            abd.b("CloudSyncV100", "Exception occour e =" + e.getClass().getName());
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:30:0x00d3 A[Catch: ClassCastException -> 0x0109, JSONException -> 0x0127, TryCatch #2 {ClassCastException -> 0x0109, JSONException -> 0x0127, blocks: (B:3:0x000d, B:5:0x0019, B:7:0x0021, B:8:0x0043, B:10:0x004c, B:13:0x0058, B:14:0x0065, B:16:0x006b, B:19:0x007d, B:20:0x0081, B:22:0x0087, B:24:0x0093, B:28:0x00a1, B:30:0x00d3, B:31:0x0101), top: B:2:0x000d }] */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0107 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:35:0x0108 A[RETURN] */
    @Override // com.huawei.android.hicloud.sync.logic.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean b(android.os.Bundle r20, boolean r21) {
        /*
            Method dump skipped, instructions count: 332
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.aam.b(android.os.Bundle, boolean):boolean");
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public void f() {
        if (s() == 0) {
            abd.c("CloudSyncV100", "afterDownloadData");
            if (aaw.i(this.C)) {
                this.G.c(this.x, this.y, this.O);
            } else {
                l();
            }
        }
    }

    private void b(JSONObject jSONObject) throws JSONException {
        JSONArray c = abl.c(jSONObject, "cloud_ctag_map");
        JSONArray c2 = abl.c(jSONObject, "cloud_etag_map");
        ParcelableMap<String, Ctag> e = aap.e(c);
        ParcelableMap<String, Etag> d = aap.d(c2);
        Map<String, Ctag> map = e.getMap();
        if (map != null) {
            this.ad.putAll(map);
        }
        Map<String, Etag> map2 = d.getMap();
        if (map2 != null) {
            this.ac.clear();
            this.ac.putAll(map2);
        }
    }

    private void c(boolean z) {
        abd.c("CloudSyncV100", "identifyCloudDataStatus, isIncrementalQuery: " + z);
        this.ab = c(this.y);
        abd.c("CloudSyncV100", "cloud etag size = " + this.ac.size());
        if (this.ab.isEmpty()) {
            abd.c("CloudSyncV100", "Set all data as cloud added");
            r();
            return;
        }
        Map<String, SyncData> hashMap = new HashMap<>(10);
        for (Map.Entry<String, SyncData> entry : this.ab.entrySet()) {
            hashMap.put(entry.getValue().getGuid(), entry.getValue());
        }
        Set<String> hashSet = new HashSet<>(this.ac.size());
        for (Map.Entry<String, Etag> entry2 : this.ac.entrySet()) {
            String key = entry2.getKey();
            Etag value = entry2.getValue();
            SyncData syncData = hashMap.get(key);
            if (value == null || value.getEtag() == null) {
                abd.b("CloudSyncV100", "Cloud Etag is null, guid = " + key);
            } else {
                hashSet.add(key);
                if (hashMap.containsKey(key) && syncData != null) {
                    if (z && value.getOperation() == 2) {
                        this.h.add(syncData.getLuid());
                    } else if (!value.getEtag().equals(syncData.getEtag())) {
                        SyncData d = aap.d(key, syncData.getLuid(), value.getEtag());
                        this.g.add(key);
                        this.H.put(key, d);
                    }
                } else if (!z || value.getOperation() != 2) {
                    SyncData c = aap.c(key, value.getEtag());
                    this.f.add(key);
                    this.H.put(key, c);
                }
            }
        }
        if (!z) {
            a(hashMap, hashSet);
        }
        abd.c("CloudSyncV100", "cloud_changes :  " + ("dataType = " + this.y + ", isRecycle = " + h() + ", cloudAdded = " + this.f.size() + ", cloudModified = " + this.g.size() + ", cloudDeleted = " + this.h.size()));
    }

    protected void d(List<SyncData> list) {
        List<UnstructData> deleteFileList;
        ArrayList<UnstructData> arrayList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            SyncData syncData = list.get(i);
            if (syncData != null && (deleteFileList = syncData.getDeleteFileList()) != null && !deleteFileList.isEmpty()) {
                arrayList.addAll(deleteFileList);
            }
        }
        d(arrayList);
    }

    private ArrayList<CtagInfo> eU_(Bundle bundle, boolean z) {
        ArrayList arrayList = new ArrayList();
        try {
            if (z) {
                arrayList = aap.b(new JSONObject(this.F.toString()));
                this.F = new StringBuffer();
            } else if (aal.a() >= 101) {
                ArrayList parcelableArrayList = bundle.getParcelableArrayList("update_ctag_list");
                if (parcelableArrayList != null) {
                    ArrayList arrayList2 = new ArrayList(parcelableArrayList.size());
                    arrayList2.addAll(parcelableArrayList);
                    arrayList = arrayList2;
                }
            } else {
                arrayList = bundle.getParcelableArrayList("update_ctag_list");
            }
        } catch (JSONException unused) {
            abd.b("CloudSyncV100", "getUpdateCtagList: JSONException");
        }
        return arrayList;
    }

    private void d(ArrayList<UnstructData> arrayList) {
        if (arrayList != null && !arrayList.isEmpty()) {
            new aad().c(arrayList, this.y);
        } else {
            abd.c("CloudSyncV100", "batchDeleteFile: sucUnstructData is null or empty.");
        }
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public void j() {
        List<SyncData> o = o();
        if (o != null && e(o, (List<String>) null) == 0) {
            d(o);
            this.G.a(this.x, this.y, o, (List<String>) null, false, this.O);
        }
    }

    private void a(Map<String, SyncData> map, Set<String> set) {
        if (map != null && set != null) {
            HashSet hashSet = new HashSet(map.keySet());
            hashSet.removeAll(set);
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                this.h.add(map.get((String) it.next()).getLuid());
            }
            return;
        }
        abd.b("CloudSyncV100", "Identify cloud deleted data, variables is null");
    }

    private void i(List<LocalId> list) {
        Map<String, SyncData> c = c(this.y);
        this.ab = c;
        if (c.isEmpty()) {
            f(list);
            return;
        }
        h(list);
        g(list);
        j(list);
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public void a(Bundle bundle, boolean z) {
        abd.c("CloudSyncV100", "afterGetSyncLostList");
        ArrayList<String> arrayList = new ArrayList<>();
        if (z) {
            try {
                arrayList = abl.d(abl.d(new JSONObject(this.F.toString()), "lostRecordIdList"));
            } catch (JSONException unused) {
                abd.b("CloudSyncV100", "afterGetSyncLostList error: JSONException");
            }
            this.F = new StringBuffer();
        } else {
            arrayList = abl.fu_(bundle, "get_sync_lost_list_result");
        }
        if (arrayList != null && arrayList.size() > 0) {
            abd.c("CloudSyncV100", "recordIdList = " + arrayList.toString());
            this.aa.addAll(arrayList);
        }
        l();
    }

    private Ctag e(String str) {
        if (this.ad.containsKey(str)) {
            return this.ad.get(str);
        }
        return null;
    }

    private void j(List<LocalId> list) {
        ArrayList<String> b = b(this.aa);
        abd.c("CloudSyncV100", "identifyLocalLost:lost data luid list: " + b.toString());
        Iterator<LocalId> it = list.iterator();
        while (it.hasNext()) {
            String id = it.next().getId();
            if (b.contains(id)) {
                this.d.add(id);
            }
        }
    }

    protected void c(String str, String str2, List<SyncData> list, List<SyncData> list2, List<String> list3, boolean z, ISyncServiceCallback iSyncServiceCallback) {
        abd.c("CloudSyncV100", "processUploadData V100");
        this.G.d(str, str2, list, list2, list3, z, iSyncServiceCallback);
    }

    private void d(List<LocalId> list, Boolean bool) {
        int size;
        int size2;
        int size3;
        String str;
        if (bool.booleanValue()) {
            ArrayList arrayList = new ArrayList(10);
            ArrayList arrayList2 = new ArrayList(10);
            ArrayList arrayList3 = new ArrayList(10);
            ArrayList arrayList4 = new ArrayList(10);
            d(list, arrayList, arrayList2, arrayList3, arrayList4);
            abd.c("CloudSyncV100", "start identify data, normalLocalIdList size:" + arrayList.size() + ", normalLocalIds size:" + arrayList2.size() + ", normalLocalEtagList size:" + arrayList4.size());
            size2 = 0;
            if (arrayList4.isEmpty()) {
                size = arrayList.size();
                size3 = 0;
            } else {
                int i = 0;
                for (LocalId localId : arrayList) {
                    if (!arrayList4.contains(localId.getId())) {
                        i++;
                    } else if (localId.getDirty() == 1) {
                        size2++;
                    }
                }
                arrayList4.removeAll(arrayList2);
                int i2 = i;
                size3 = arrayList4.size();
                size = i2;
            }
            abd.a("CloudSyncV100", "end identify data");
            int size4 = this.f129a.size();
            int size5 = this.b.size();
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("dataType", this.y);
                jSONObject.put("localIdNum", list.size());
                jSONObject.put("localETagNum", this.ab.size());
                jSONObject.put("addedNum", size);
                jSONObject.put("recycleAddedNum", size4 - size);
                jSONObject.put("modifiedNum", size2);
                jSONObject.put("recycleModifiedNum", size5 - size2);
                jSONObject.put("deletedNum", size3);
                jSONObject.put("lostNum", this.d.size());
                jSONObject.put("normalNum", arrayList2.size());
                jSONObject.put("recycleNum", arrayList3.size());
                abf.d(jSONObject);
                str = jSONObject.toString();
            } catch (JSONException unused) {
                abd.b("CloudSyncV100", "local_changes error: JSONException");
                str = "";
            }
            abd.c("CloudSyncV100", "local_changes: " + str);
            a(abf.d(str));
        } else {
            size = this.f129a.size();
            size2 = this.b.size();
            size3 = this.c.size();
        }
        this.G.c(this.x, this.y, size, size3, size2, this.O);
    }

    protected void b(String str, String str2, List<SyncData> list, List<SyncData> list2, List<String> list3, List<SyncData> list4, boolean z, ISyncServiceCallback iSyncServiceCallback) {
        abd.c("CloudSyncV100", "processUploadDataWithLost");
        ArrayList arrayList = new ArrayList(8);
        if (list != null && !list.isEmpty()) {
            for (SyncData syncData : list) {
                arrayList.add(new SyncDataCompatible(syncData.getVersion(), syncData.getLuid(), syncData.getGuid(), syncData.getUnstruct_uuid(), syncData.getEtag(), syncData.getData(), syncData.getDownFileList(), syncData.getDeleteFileList(), syncData.getFileList(), syncData.getRecycleStatus(), syncData.getRecycleTime()));
            }
        }
        ArrayList arrayList2 = new ArrayList(8);
        if (list2 != null && !list2.isEmpty()) {
            for (SyncData syncData2 : list2) {
                arrayList2.add(new SyncDataCompatible(syncData2.getVersion(), syncData2.getLuid(), syncData2.getGuid(), syncData2.getUnstruct_uuid(), syncData2.getEtag(), syncData2.getData(), syncData2.getDownFileList(), syncData2.getDeleteFileList(), syncData2.getFileList(), syncData2.getRecycleStatus(), syncData2.getRecycleTime()));
            }
        }
        ArrayList arrayList3 = new ArrayList(8);
        if (list4 != null && !list4.isEmpty()) {
            for (SyncData syncData3 : list4) {
                arrayList3.add(new SyncDataCompatible(syncData3.getVersion(), syncData3.getLuid(), syncData3.getGuid(), syncData3.getUnstruct_uuid(), syncData3.getEtag(), syncData3.getData(), syncData3.getDownFileList(), syncData3.getDeleteFileList(), syncData3.getFileList(), syncData3.getRecycleStatus(), syncData3.getRecycleTime()));
            }
        }
        this.G.a(str, str2, arrayList, arrayList2, list3, arrayList3, z, iSyncServiceCallback);
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public void c(List<String> list) {
        if (e((List<SyncData>) null, list) == 0) {
            i();
        }
    }

    protected int e(List<SyncData> list, List<String> list2) {
        abd.c("CloudSyncV100", "updateLocalEtag");
        zy zyVar = new zy();
        if (list2 != null) {
            try {
                if (list2.size() > 0) {
                    abd.c("CloudSyncV100", "updateLocaldb deleteData mDataType = " + this.y + ", deleteList.size = " + list2.size());
                    zyVar.c(list2, this.y);
                    new aad().a(list2, this.y);
                }
            } catch (Exception e) {
                abd.b("CloudSyncV100", "updateLocalEtag exception: " + e.getClass().getName());
                a(5);
                return 5;
            }
        }
        if (list != null && list.size() > 0) {
            abd.c("CloudSyncV100", "updateLocaldb mSaveResult mDataType = " + this.y + ", saveData.size = " + list.size());
            zyVar.e(list, this.y);
        }
        return 0;
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public ArrayList<String> b(ArrayList<String> arrayList) {
        ArrayList<String> arrayList2 = new ArrayList<>();
        if (arrayList != null && !arrayList.isEmpty()) {
            ArrayList<SyncData> d = new zy().d(arrayList, this.y);
            if (d.size() != arrayList.size()) {
                abd.c("CloudSyncV100", " updateSyncResult eTagList size error : etagList.size = " + d.size());
                return arrayList2;
            }
            Iterator<SyncData> it = d.iterator();
            while (it.hasNext()) {
                arrayList2.add(it.next().getLuid());
            }
        }
        return arrayList2;
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public void c(ArrayList<SyncData> arrayList) {
        abd.c("CloudSyncV100", "setCloudDataGuid : cloudData.size = " + arrayList.size());
        if (arrayList.size() > 0) {
            zy zyVar = new zy();
            Iterator<SyncData> it = arrayList.iterator();
            while (it.hasNext()) {
                SyncData next = it.next();
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(next.getLuid());
                ArrayList<SyncData> b = zyVar.b(arrayList2, this.y);
                if (b.size() > 0) {
                    next.setGuid(b.get(0).getGuid());
                }
            }
        }
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public void b(List<SyncData> list, List<String> list2) {
        e(list);
        if (e(list, list2) == 0) {
            m();
        }
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public void c(Bundle bundle, boolean z) {
        n(eU_(bundle, z));
        super.c(bundle, z);
    }

    private void d(List<LocalId> list, List<LocalId> list2, List<String> list3, List<String> list4, List<String> list5) {
        abd.c("CloudSyncV100", "enter filteringRecycleData");
        for (LocalId localId : list) {
            if (localId.getRecycleStatus() != -1) {
                list2.add(localId);
                list3.add(localId.getId());
            } else {
                list4.add(localId.getId());
            }
        }
        for (Map.Entry<String, SyncData> entry : this.ab.entrySet()) {
            if (entry.getValue().getRecycleStatus() != -1) {
                list5.add(entry.getKey());
            }
        }
        abd.a("CloudSyncV100", "exit filteringRecycleData");
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public void a(String str, String str2, List<SyncData> list, List<SyncData> list2, List<String> list3, boolean z, ISyncServiceCallback iSyncServiceCallback) {
        abd.c("CloudSyncV100", "uploadData");
        ArrayList<SyncData> b = new zy().b(list3, this.y);
        if (b.size() != list3.size()) {
            abd.b("CloudSyncV100", " processUpdateData delete size error : etagList.size = " + b.size() + ", delete.size = " + list3.size());
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<SyncData> it = b.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getGuid());
        }
        abd.c("CloudSyncV100", "deleteGuidList.size = " + arrayList.size());
        c(str, str2, list, list2, arrayList, z, iSyncServiceCallback);
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public void a(String str, String str2, List<SyncData> list, List<SyncData> list2, List<String> list3, List<SyncData> list4, boolean z, ISyncServiceCallback iSyncServiceCallback) {
        abd.c("CloudSyncV100", "uploadDataWithLost");
        ArrayList<SyncData> b = new zy().b(list3, this.y);
        if (b.size() != list3.size()) {
            abd.b("CloudSyncV100", " uploadDataWithLost delete size error : etagList.size = " + b.size() + ", delete.size = " + list3.size());
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<SyncData> it = b.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getGuid());
        }
        abd.c("CloudSyncV100", "uploadDataWithLost deleteGuidList.size = " + arrayList.size());
        b(str, str2, list, list2, arrayList, list4, z, iSyncServiceCallback);
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public void a(List<SyncData> list, List<String> list2, int i) {
        e(list);
        e(list, list2);
        abd.d("CloudSyncV100", "partial success, result error, result = " + i);
        a(i);
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public void a(ArrayList<UnstructData> arrayList) {
        if (arrayList != null && !arrayList.isEmpty()) {
            abd.c("CloudSyncV100", "updateLocaldb, dataType = " + this.y + ", sucUnstructData.size = " + arrayList.size());
            new aad().e(arrayList, this.y);
            return;
        }
        abd.d("CloudSyncV100", "updateLocaldb, dataType = " + this.y + ", sucUnstructData is null or empty");
    }

    @Override // com.huawei.android.hicloud.sync.logic.c
    public void a(String str, List<String> list, List<String> list2) {
        abd.c("CloudSyncV100", "App call: endSyncV100");
        ArrayList arrayList = new ArrayList();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                arrayList.add(b(list.get(i)));
            }
            this.G.a(str, list, list2, arrayList, this.O);
        }
    }
}
