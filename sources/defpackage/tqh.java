package defpackage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/* loaded from: classes8.dex */
public class tqh {
    private SQLiteDatabase b;
    private String[] c;
    private String e;

    public tqh(Context context, String str, String[] strArr) {
        this.e = str;
        this.c = (String[]) strArr.clone();
        this.b = tqk.e(context).getWritableDatabase();
    }

    public long fex_(ContentValues contentValues) {
        return this.b.insert(this.e, null, contentValues);
    }

    public int a(String str, String[] strArr) {
        return this.b.delete(this.e, str, strArr);
    }

    public int fez_(ContentValues contentValues, String str, String[] strArr) {
        return this.b.update(this.e, contentValues, str, strArr);
    }

    public Cursor fey_(String str, String[] strArr, String str2, String str3, String str4) {
        return this.b.query(this.e, this.c, str, strArr, str2, str3, str4);
    }
}
