package defpackage;

import android.database.Cursor;
import com.huawei.android.hicloud.sync.c.a.b.e;
import com.huawei.android.hicloud.sync.service.aidl.CtagInfo;
import com.huawei.android.hicloud.sync.service.aidl.CtagInfoCompatible;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
public class aab extends e<CtagInfo> {
    public ArrayList<CtagInfoCompatible> b() {
        ArrayList<CtagInfo> b = b("SELECT ctag_name, ctag_value, sync_token FROM ctag", null);
        ArrayList<CtagInfoCompatible> arrayList = new ArrayList<>(b.size());
        Iterator<CtagInfo> it = b.iterator();
        while (it.hasNext()) {
            arrayList.add(new CtagInfoCompatible(it.next(), aal.a()));
        }
        return arrayList;
    }

    public void c(List<CtagInfo> list) throws Exception {
        abd.a("CtagOperator", "replaceData begin");
        if (list == null || list.isEmpty()) {
            return;
        }
        abd.a("CtagOperator", "replaceData, request: " + list.toString());
        ArrayList<String[]> arrayList = new ArrayList<>();
        for (CtagInfo ctagInfo : list) {
            if (ctagInfo != null) {
                arrayList.add(d(ctagInfo));
            }
        }
        a("REPLACE INTO ctag(ctag_name,ctag_value, sync_token) VALUES(?,?,?)", arrayList);
    }

    private String[] d(CtagInfo ctagInfo) {
        String[] strArr = new String[3];
        strArr[0] = ctagInfo.getCtagName();
        strArr[1] = ctagInfo.getCtagValue();
        String syncToken = ctagInfo.getSyncToken();
        if (syncToken == null) {
            syncToken = "";
        }
        strArr[2] = syncToken;
        return strArr;
    }

    public void c() throws Exception {
        abd.a("CtagOperator", "deleteAll ");
        a("DELETE FROM ctag ", (String[]) null);
    }

    public ArrayList<CtagInfo> c(String[] strArr) {
        return b("SELECT ctag_name, ctag_value, sync_token FROM ctag WHERE ctag_name = ? ", strArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.huawei.android.hicloud.sync.c.a.b.e
    /* renamed from: eO_, reason: merged with bridge method [inline-methods] */
    public CtagInfo a(Cursor cursor) {
        CtagInfo ctagInfo = new CtagInfo();
        ctagInfo.setCtagName(cursor.getString(0));
        ctagInfo.setCtagValue(cursor.getString(1));
        if (cursor.getColumnCount() == 3) {
            ctagInfo.setSyncToken(cursor.getString(2));
        }
        return ctagInfo;
    }
}
