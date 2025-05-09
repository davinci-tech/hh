package com.huawei.hms.network.file.a.k.b;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.network.file.core.util.Utils;
import java.util.List;

/* loaded from: classes4.dex */
public class i extends c<com.huawei.hms.network.file.a.d> {
    public void d(List<com.huawei.hms.network.file.a.d> list, String str) {
        if (Utils.isEmpty(list)) {
            return;
        }
        for (com.huawei.hms.network.file.a.d dVar : list) {
            if (dVar.p() != null) {
                a(a2(dVar, str), "taskId=? and manager=?", new String[]{String.valueOf(dVar.f()), str});
            }
        }
    }

    void c(List<com.huawei.hms.network.file.a.d> list, String str) {
        a((List) list, str);
    }

    List<com.huawei.hms.network.file.a.d> b(long j, String str) {
        return b("requestId=? and manager=?", new String[]{String.valueOf(j), str});
    }

    @Override // com.huawei.hms.network.file.a.k.b.c
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public ContentValues a(com.huawei.hms.network.file.a.d dVar, String str) {
        return a2(dVar, str);
    }

    void a(long j, String str) {
        a("requestId=? and manager=?", new String[]{String.valueOf(j), str});
    }

    @Override // com.huawei.hms.network.file.a.k.b.c
    public String a() {
        return "download_taskinfo";
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.huawei.hms.network.file.a.k.b.c
    public com.huawei.hms.network.file.a.d a(Cursor cursor) {
        long j = cursor.getLong(cursor.getColumnIndex("start"));
        long j2 = cursor.getLong(cursor.getColumnIndex("end"));
        byte[] a2 = a(cursor, "filePath");
        return new com.huawei.hms.network.file.a.d(cursor.getLong(cursor.getColumnIndex("finished")), j, j2, a2 != null ? StringUtils.byte2Str(a2) : "", cursor.getLong(cursor.getColumnIndex("taskId")));
    }

    /* renamed from: a, reason: avoid collision after fix types in other method */
    ContentValues a2(com.huawei.hms.network.file.a.d dVar, String str) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("requestId", Long.valueOf(dVar.p().getId()));
        contentValues.put("taskId", Long.valueOf(dVar.f()));
        contentValues.put("end", Long.valueOf(dVar.y()));
        contentValues.put("start", Long.valueOf(dVar.A()));
        c.a(contentValues, "filePath", StringUtils.str2Byte(Utils.nullStrToEmpty(dVar.z())));
        contentValues.put("finished", Long.valueOf(dVar.c()));
        contentValues.put("manager", str);
        return contentValues;
    }

    public i(SQLiteDatabase sQLiteDatabase) {
        super(sQLiteDatabase);
    }
}
