package defpackage;

import android.database.Cursor;
import com.huawei.android.hicloud.sync.c.a.b.e;
import com.huawei.android.hicloud.sync.service.aidl.UnstructData;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
public class aad extends e<UnstructData> {
    public ArrayList<UnstructData> b(List<String> list, String str) {
        ArrayList<UnstructData> arrayList = new ArrayList<>();
        abd.a("FileOperator", "query4Guid begin");
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                arrayList.addAll(b("SELECT itemid,type,hash,name,fileDir FROM  file where itemid = ? and type = ? ", new String[]{list.get(i), str}));
            }
        }
        return arrayList;
    }

    public void c(List<UnstructData> list, String str) {
        if (list != null) {
            try {
                if (list.isEmpty()) {
                    return;
                }
                abd.a("FileOperator", "batchDelete , request = " + list.toString());
                ArrayList<String[]> arrayList = new ArrayList<>();
                for (UnstructData unstructData : list) {
                    arrayList.add(new String[]{unstructData.getId(), str, unstructData.getName()});
                }
                a("DELETE FROM file WHERE itemid = ? and type = ? and name = ?", arrayList);
            } catch (Exception e) {
                abd.b("FileOperator", "deleteData , type = " + str + " exception occour = " + e.getClass().getName());
            }
        }
    }

    public void e(List<UnstructData> list, String str) {
        try {
            abd.a("FileOperator", "replaceData begin");
            if (list != null && !list.isEmpty()) {
                abd.a("FileOperator", "replaceData , request = " + list.toString());
                ArrayList<String[]> arrayList = new ArrayList<>();
                for (UnstructData unstructData : list) {
                    if (unstructData != null) {
                        arrayList.add(d(unstructData, str));
                    }
                }
                a("REPLACE INTO file(itemid,type,hash,name,fileDir) VALUES(?,?,?,?,?)", arrayList);
            }
        } catch (Exception e) {
            abd.b("FileOperator", "replaceData , type = " + str + " exception occour = " + e.getClass().getName());
        }
    }

    private String[] d(UnstructData unstructData, String str) {
        String[] strArr = new String[5];
        strArr[0] = unstructData.getId();
        strArr[1] = str;
        strArr[2] = unstructData.getHash();
        strArr[3] = unstructData.getName();
        String unstruct_uuid = unstructData.getUnstruct_uuid();
        if (unstruct_uuid == null) {
            unstruct_uuid = "";
        }
        strArr[4] = unstruct_uuid;
        return strArr;
    }

    public void a(List<String> list, String str) throws Exception {
        if (list == null || list.isEmpty()) {
            return;
        }
        abd.a("FileOperator", "batchDelete , request = " + list.toString());
        ArrayList<String[]> arrayList = new ArrayList<>();
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(new String[]{it.next(), str});
        }
        a("DELETE FROM file WHERE itemid = ? and type = ?", arrayList);
    }

    public void c() throws Exception {
        abd.a("FileOperator", "deleteAll ");
        a("DELETE FROM file ", (String[]) null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.huawei.android.hicloud.sync.c.a.b.e
    /* renamed from: eQ_, reason: merged with bridge method [inline-methods] */
    public UnstructData a(Cursor cursor) {
        UnstructData unstructData = new UnstructData();
        unstructData.setId(cursor.getString(0));
        unstructData.setHash(cursor.getString(2));
        unstructData.setName(cursor.getString(3));
        if (cursor.getColumnCount() == 5) {
            unstructData.setUnstruct_uuid(cursor.getString(4));
        }
        return unstructData;
    }
}
