package defpackage;

import android.text.TextUtils;
import com.huawei.android.hicloud.sync.service.aidl.Ctag;
import com.huawei.android.hicloud.sync.service.aidl.CtagInfo;
import com.huawei.android.hicloud.sync.service.aidl.Etag;
import com.huawei.android.hicloud.sync.service.aidl.ParcelableMap;
import com.huawei.android.hicloud.sync.service.aidl.SyncData;
import com.huawei.android.hicloud.sync.service.aidl.UnstructData;
import com.huawei.android.hicloud.sync.service.aidl.UnstructDataV107;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public class aap {
    public static ArrayList<String> a(JSONArray jSONArray) throws JSONException {
        ArrayList<String> arrayList = new ArrayList<>();
        if (jSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            arrayList.add(String.valueOf(i));
        }
        return arrayList;
    }

    public static aaz<Integer, List<String>> b(JSONArray jSONArray) throws JSONException {
        aaz<Integer, List<String>> aazVar = new aaz<>();
        if (jSONArray == null) {
            return aazVar;
        }
        HashMap hashMap = new HashMap();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            if (jSONObject != null) {
                int e = abl.e(jSONObject, "errorCode");
                hashMap.put(Integer.valueOf(e), abl.d(abl.d(jSONObject, "idList")));
            }
        }
        aazVar.e(hashMap);
        return aazVar;
    }

    public static SyncData c(String str, String str2) {
        SyncData syncData = new SyncData();
        syncData.setGuid(str);
        syncData.setEtag(str2);
        return syncData;
    }

    public static ArrayList<SyncData> c(JSONArray jSONArray) throws JSONException {
        ArrayList<SyncData> arrayList = new ArrayList<>();
        if (jSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            if (jSONObject != null) {
                SyncData syncData = new SyncData();
                syncData.setLuid(abl.d(jSONObject, "luid"));
                syncData.setGuid(abl.d(jSONObject, "guid"));
                syncData.setUnstruct_uuid(abl.d(jSONObject, "unstruct_uuid"));
                syncData.setEtag(abl.d(jSONObject, "etag"));
                syncData.setData(abl.d(jSONObject, "data"));
                syncData.setUuid(abl.d(jSONObject, "uuid"));
                syncData.setStatus(abl.e(jSONObject, "status"));
                syncData.setHash(abl.d(jSONObject, "hash"));
                JSONArray c = abl.c(jSONObject, "downFileList");
                JSONArray c2 = abl.c(jSONObject, "deleteFileList");
                JSONArray c3 = abl.c(jSONObject, "filelist");
                syncData.setDownFileList(g(c));
                syncData.setDeleteFileList(g(c2));
                syncData.setFileList(g(c3));
                syncData.setRecycleStatus(jSONObject.optInt("recycleStatus"));
                syncData.setRecycleTime(jSONObject.optLong("recycleTime"));
                if (aal.a() >= 107) {
                    if (!TextUtils.isEmpty(jSONObject.optString("extensionData"))) {
                        syncData.setExtensionData(jSONObject.optString("extensionData"));
                    }
                    if (!TextUtils.isEmpty(jSONObject.optString("extraParam"))) {
                        syncData.setExtraParam(jSONObject.optString("extraParam"));
                    }
                }
                arrayList.add(syncData);
            }
        }
        return arrayList;
    }

    public static ParcelableMap<String, Etag> d(JSONArray jSONArray) throws JSONException {
        ParcelableMap<String, Etag> parcelableMap = new ParcelableMap<>();
        if (jSONArray == null) {
            return parcelableMap;
        }
        HashMap hashMap = new HashMap();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            if (jSONObject != null) {
                Etag etag = new Etag();
                String d = abl.d(jSONObject, "guid");
                String d2 = abl.d(jSONObject, "uuid");
                String d3 = abl.d(jSONObject, "etag");
                int e = abl.e(jSONObject, "status");
                int e2 = abl.e(jSONObject, "operation");
                etag.setUuid(d2);
                etag.setEtag(d3);
                etag.setStatus(e);
                etag.setOperation(e2);
                hashMap.put(d, etag);
            }
        }
        parcelableMap.setMap(hashMap);
        return parcelableMap;
    }

    public static ArrayList<UnstructData> g(JSONArray jSONArray) throws JSONException {
        ArrayList<UnstructData> arrayList = new ArrayList<>();
        if (jSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            if (jSONObject != null) {
                UnstructData unstructData = new UnstructData();
                unstructData.setId(abl.d(jSONObject, "id"));
                unstructData.setUnstruct_uuid(abl.d(jSONObject, "unstruct_uuid"));
                unstructData.setName(abl.d(jSONObject, "name"));
                unstructData.setHash(abl.d(jSONObject, "hash"));
                arrayList.add(unstructData);
            }
        }
        return arrayList;
    }

    public static SyncData d(String str, String str2, String str3) {
        SyncData syncData = new SyncData();
        syncData.setGuid(str);
        syncData.setEtag(str3);
        syncData.setLuid(str2);
        return syncData;
    }

    public static ArrayList<SyncData> a(String str) throws Exception {
        abd.a("SyncLogicUtil", "parseParceDataFromJson");
        ArrayList<SyncData> arrayList = new ArrayList<>();
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject != null) {
                    SyncData syncData = new SyncData();
                    syncData.setGuid(jSONObject.getString("guid"));
                    syncData.setData(jSONObject.getString("data"));
                    syncData.setUnstruct_uuid(jSONObject.getString("unstructUuid"));
                    syncData.setRecycleStatus(jSONObject.optInt("recycleStatus"));
                    syncData.setRecycleTime(jSONObject.optLong("recycleTime"));
                    if (aal.a() >= 107) {
                        if (!TextUtils.isEmpty(jSONObject.optString("extensionData"))) {
                            syncData.setExtensionData(jSONObject.optString("extensionData"));
                        }
                        if (!TextUtils.isEmpty(jSONObject.optString("extraParam"))) {
                            syncData.setExtraParam(jSONObject.optString("extraParam"));
                        }
                    }
                    arrayList.add(syncData);
                }
            }
            return arrayList;
        } catch (JSONException e) {
            abd.b("SyncLogicUtil", "parseQueryResultParceDataFromJson error : JSONException " + e.getMessage());
            throw e;
        }
    }

    public static ParcelableMap<String, Ctag> e(JSONArray jSONArray) throws JSONException {
        ParcelableMap<String, Ctag> parcelableMap = new ParcelableMap<>();
        if (jSONArray == null) {
            return parcelableMap;
        }
        HashMap hashMap = new HashMap();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            if (jSONObject != null) {
                Ctag ctag = new Ctag();
                String d = abl.d(jSONObject, "guid");
                String d2 = abl.d(jSONObject, "ctag");
                int e = abl.e(jSONObject, "status");
                String d3 = abl.d(jSONObject, "syncToken");
                boolean c = abl.c(jSONObject, "expired", true);
                ctag.setCtag(d2);
                ctag.setStatus(e);
                ctag.setSyncToken(d3);
                ctag.setExpired(c);
                hashMap.put(d, ctag);
            }
        }
        parcelableMap.setMap(hashMap);
        return parcelableMap;
    }

    public static ArrayList<CtagInfo> b(JSONObject jSONObject) throws JSONException {
        ArrayList<CtagInfo> arrayList = new ArrayList<>();
        JSONArray c = abl.c(jSONObject, "updateCtagList");
        if (c == null) {
            return arrayList;
        }
        for (int i = 0; i < c.length(); i++) {
            JSONObject jSONObject2 = c.getJSONObject(i);
            if (jSONObject2 != null) {
                CtagInfo ctagInfo = new CtagInfo();
                String d = abl.d(jSONObject2, "ctagName");
                String d2 = abl.d(jSONObject2, "ctagValue");
                int e = abl.e(jSONObject2, "status");
                ctagInfo.setCtagName(d);
                ctagInfo.setCtagValue(d2);
                ctagInfo.setStatus(e);
                arrayList.add(ctagInfo);
            }
        }
        return arrayList;
    }

    public static List<UnstructDataV107> b(List<UnstructData> list) {
        if (list == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(8);
        for (UnstructData unstructData : list) {
            arrayList.add(new UnstructDataV107(aal.a(), unstructData.getId(), unstructData.getUnstruct_uuid(), unstructData.getName(), unstructData.getHash(), unstructData.getOptType()));
        }
        return arrayList;
    }
}
