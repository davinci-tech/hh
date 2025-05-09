package defpackage;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.huawei.ui.openservice.db.OpenServiceDbHelper;

/* loaded from: classes3.dex */
public class cny {

    /* renamed from: a, reason: collision with root package name */
    private String[] f805a;
    private SQLiteDatabase c;
    private String e;

    public cny(Context context, String str, String[] strArr) {
        this.e = str;
        this.f805a = (String[]) strArr.clone();
        this.c = OpenServiceDbHelper.getInstance(context).getWritableDatabase();
    }

    public Cursor Jo_(String str, String[] strArr, String str2, String str3, String str4) {
        return this.c.query(this.e, this.f805a, str, strArr, str2, str3, str4);
    }
}
