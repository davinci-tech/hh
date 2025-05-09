package defpackage;

import android.database.Cursor;
import com.huawei.android.hicloud.sync.c.a.b.e;
import com.huawei.android.hicloud.sync.service.aidl.SyncData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes8.dex */
public class zy extends e<SyncData> {
    public ArrayList<SyncData> d(String str) {
        abd.a("EtagOperator", "query4VoNormal");
        return b("SELECT luid,etag,uuid,guid,hash,recycle_status FROM  etag where type = ? and recycle_status != -1", new String[]{str});
    }

    public ArrayList<SyncData> d(List<String> list, String str) {
        ArrayList<SyncData> arrayList = new ArrayList<>();
        abd.a("EtagOperator", "query4Luid begin");
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                arrayList.addAll(b("SELECT luid,etag,uuid,guid,hash,recycle_status FROM  etag where guid = ? and type = ?", new String[]{list.get(i), str}));
            }
        }
        return arrayList;
    }

    public void e(List<SyncData> list, String str) throws Exception {
        abd.a("EtagOperator", "batchReplace begin ");
        if (list == null || list.isEmpty()) {
            return;
        }
        abd.a("EtagOperator", "batchReplace , request = " + list.size());
        ArrayList<String[]> arrayList = new ArrayList<>();
        Iterator<SyncData> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(a(it.next(), str));
        }
        a("REPLACE INTO etag(luid,type,etag,uuid,guid,hash,recycle_status) VALUES(?,?,?,?,?,?,?)", arrayList);
    }

    private String[] a(SyncData syncData, String str) {
        String[] strArr = new String[7];
        strArr[0] = syncData.getLuid();
        strArr[1] = str;
        strArr[2] = syncData.getEtag();
        if (syncData.getUuid() == null) {
            strArr[3] = "";
        } else {
            strArr[3] = syncData.getUuid();
        }
        strArr[4] = syncData.getGuid();
        if (syncData.getHash() == null) {
            strArr[5] = "";
        } else {
            strArr[5] = syncData.getHash();
        }
        strArr[6] = "" + syncData.getRecycleStatus();
        return strArr;
    }

    public ArrayList<SyncData> b(List<String> list, String str) {
        ArrayList<SyncData> arrayList = new ArrayList<>();
        abd.a("EtagOperator", "query4Guid begin");
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                arrayList.addAll(b("SELECT luid,etag,uuid,guid,hash,recycle_status FROM  etag where luid = ? and type = ? ", new String[]{list.get(i), str}));
            }
        }
        return arrayList;
    }

    public ArrayList<SyncData> e(String str) {
        abd.a("EtagOperator", "query4Vo");
        return b("SELECT luid,etag,uuid,guid,hash,recycle_status FROM  etag where type = ?", new String[]{str});
    }

    public void a() throws Exception {
        abd.a("EtagOperator", "deleteAll ");
        a("DELETE FROM etag ", (String[]) null);
    }

    public void c(List<String> list, String str) throws aaf {
        abd.a("EtagOperator", "batchDelete batDeleteList begin ");
        if (list == null || list.isEmpty()) {
            return;
        }
        abd.a("EtagOperator", "batchDelete batDeleteList , request = " + list.size());
        ArrayList<String[]> arrayList = new ArrayList<>();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new String[]{it.next(), str});
        }
        a("DELETE FROM etag WHERE luid = ? and type = ?", arrayList);
    }

    public int a(Map<String, String> map, String str) {
        abd.c("EtagOperator", "batUpdate4Luid begin type = " + str);
        ArrayList<String[]> arrayList = new ArrayList<>();
        if (map == null || map.isEmpty()) {
            return -2;
        }
        abd.c("EtagOperator", "luidMapSize = " + map.size());
        for (Map.Entry<String, String> entry : map.entrySet()) {
            abd.c("EtagOperator", "[ luidMap.oldluid = " + entry.getKey() + " luidMap.newluid = " + entry.getValue() + " ]");
            arrayList.add(new String[]{entry.getValue(), entry.getKey(), str});
        }
        abd.c("EtagOperator", "argsListSize = " + arrayList.size());
        return a("update etag set luid = ? where luid = ? and type = ?", arrayList);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.huawei.android.hicloud.sync.c.a.b.e
    /* renamed from: eP_, reason: merged with bridge method [inline-methods] */
    public SyncData a(Cursor cursor) {
        SyncData syncData = new SyncData();
        syncData.setLuid(cursor.getString(0));
        syncData.setEtag(cursor.getString(1));
        syncData.setUuid(cursor.getString(2));
        syncData.setGuid(cursor.getString(3));
        syncData.setHash(cursor.getString(4));
        syncData.setRecycleStatus(cursor.getInt(5));
        return syncData;
    }
}
