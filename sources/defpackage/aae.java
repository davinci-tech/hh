package defpackage;

import android.database.Cursor;
import com.huawei.android.hicloud.sync.c.a.b.e;
import java.util.ArrayList;

/* loaded from: classes8.dex */
public class aae extends e<zx> {
    public ArrayList<zx> e(String str) {
        return b("select scene as c, startTime as s, endTime as e, times, errCode as err from pre_records where syncType = ? order by endTime desc limit 20", new String[]{str});
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.huawei.android.hicloud.sync.c.a.b.e
    /* renamed from: eS_, reason: merged with bridge method [inline-methods] */
    public zx a(Cursor cursor) {
        zx zxVar = new zx();
        zxVar.d(cursor.getInt(0));
        zxVar.e(cursor.getLong(1));
        zxVar.a(cursor.getLong(2));
        zxVar.b(cursor.getInt(3));
        zxVar.e(cursor.getString(4));
        return zxVar;
    }
}
