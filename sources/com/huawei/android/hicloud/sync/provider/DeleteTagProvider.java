package com.huawei.android.hicloud.sync.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import com.huawei.android.hicloud.sync.service.aidl.CtagInfo;
import com.huawei.android.hicloud.sync.service.aidl.SyncData;
import defpackage.aaa;
import defpackage.aab;
import defpackage.abd;
import defpackage.abe;
import defpackage.abl;
import defpackage.zy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: classes8.dex */
public class DeleteTagProvider extends ContentProvider {
    private void a(JSONArray jSONArray) throws Exception {
        if (jSONArray == null) {
            abd.c("DeleteTagProvider", "updateEtagList error: etagJsonArray is null");
            return;
        }
        abd.c("DeleteTagProvider", "updateEtagList: etagJsonArray.length = " + jSONArray.length());
        HashMap hashMap = new HashMap();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            if (jSONObject != null) {
                SyncData syncData = new SyncData();
                syncData.setLuid(abl.d(jSONObject, "luid"));
                syncData.setEtag(abl.d(jSONObject, "etag"));
                syncData.setUuid(abl.d(jSONObject, "uuid"));
                syncData.setGuid(abl.d(jSONObject, "guid"));
                syncData.setHash(abl.d(jSONObject, "hash"));
                String d = abl.d(jSONObject, "type");
                if (hashMap.containsKey(d)) {
                    hashMap.get(d).add(syncData);
                } else {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(syncData);
                    hashMap.put(d, arrayList);
                }
            }
        }
        b(hashMap);
    }

    private void b(JSONArray jSONArray) throws Exception {
        if (jSONArray == null) {
            abd.c("DeleteTagProvider", "updateCtagList error: ctagJsonArray is null");
            return;
        }
        abd.c("DeleteTagProvider", "updateCtagList: ctagJsonArray.length = " + jSONArray.length());
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = jSONArray.getJSONObject(i);
            if (jSONObject != null) {
                CtagInfo ctagInfo = new CtagInfo();
                ctagInfo.setCtagName(abl.d(jSONObject, "ctag_name"));
                ctagInfo.setCtagValue(abl.d(jSONObject, "ctag_value"));
                arrayList.add(ctagInfo);
            }
        }
        abd.c("DeleteTagProvider", "ctagInfoList : " + arrayList.toString());
        new aab().c(arrayList);
    }

    @Override // android.content.ContentProvider
    public Bundle call(String str, String str2, Bundle bundle) {
        abd.c("DeleteTagProvider", "call: method = " + str);
        if (!abl.d(getContext(), getCallingPackage())) {
            throw new IllegalArgumentException("Application does not have permission");
        }
        try {
            if ("migrate_data".equals(str)) {
                JSONObject jSONObject = new JSONObject(bundle.getString("persistentData"));
                JSONArray jSONArray = jSONObject.getJSONArray("ctag");
                JSONArray jSONArray2 = jSONObject.getJSONArray("etag");
                b(jSONArray);
                a(jSONArray2);
            } else if ("clear_prepare".equals(str)) {
                abd.c("DeleteTagProvider", "logout clear all prepare");
                aaa.a(getContext());
                abe.e(getContext()).b();
            }
        } catch (Exception e) {
            abd.c("DeleteTagProvider", "call error: " + e.toString());
        }
        return super.call(str, str2, bundle);
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        abd.c("DeleteTagProvider", "delete");
        abd.a("DeleteTagProvider", "delete, uri = " + uri);
        if (!abl.d(getContext(), getCallingPackage())) {
            abd.b("DeleteTagProvider", "deleteTag error: not hasPermission");
            return -3;
        }
        if (uri != null) {
            String authority = uri.getAuthority();
            abd.a("DeleteTagProvider", "delete, authority = " + authority);
            if (authority != null && authority.contains(".hicloud.deleteTagProvider")) {
                String path = uri.getPath();
                abd.a("DeleteTagProvider", "delete, path = " + path);
                if (path != null && path.contains("delete_tag")) {
                    return aaa.b(getContext());
                }
            }
        }
        throw new IllegalArgumentException("unknown_uri " + uri);
    }

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        return null;
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        return false;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        return null;
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        return 0;
    }

    private void b(Map<String, List<SyncData>> map) throws Exception {
        zy zyVar = new zy();
        for (Map.Entry<String, List<SyncData>> entry : map.entrySet()) {
            zyVar.e(entry.getValue(), entry.getKey());
        }
    }
}
