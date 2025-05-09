package com.huawei.phoneservice.feedbackcommon.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.huawei.phoneservice.faq.base.util.i;
import com.huawei.phoneservice.feedbackcommon.entity.ProblemEntity;
import defpackage.ueu;
import defpackage.uhy;
import defpackage.uib;
import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.JvmStatic;

/* loaded from: classes5.dex */
public final class FeedbackProblemData extends SQLiteOpenHelper {

    /* renamed from: a, reason: collision with root package name */
    private static FeedbackProblemData f8307a;
    public static final e b = new e(null);
    private static SQLiteDatabase d;

    public final void a(ProblemEntity problemEntity) {
        uhy.e((Object) problemEntity, "");
        i.e("FeedbackDataManager", "saveFailProblem", new Object[0]);
        String a2 = problemEntity.a();
        uhy.a(a2, "");
        if (e(a2)) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("problemId", problemEntity.a());
        SQLiteDatabase sQLiteDatabase = d;
        uhy.d(sQLiteDatabase);
        sQLiteDatabase.insert("table_feedback_problem", null, contentValues);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if ((i == 1 || i == 2) && sQLiteDatabase != null) {
            sQLiteDatabase.execSQL("create table if not exists table_feedback_problem(_id INTEGER primary key autoincrement,problemId String);");
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        uhy.e((Object) sQLiteDatabase, "");
        sQLiteDatabase.execSQL("create table if not exists table_feedback_problem(_id INTEGER primary key autoincrement,problemId String);");
    }

    public final List<ProblemEntity> a() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase sQLiteDatabase = d;
        Cursor query = sQLiteDatabase != null ? sQLiteDatabase.query("table_feedback_problem", null, null, null, null, null, null) : null;
        while (true) {
            Boolean valueOf = query != null ? Boolean.valueOf(query.moveToNext()) : null;
            uhy.d(valueOf);
            if (!valueOf.booleanValue()) {
                query.close();
                return arrayList;
            }
            ProblemEntity problemEntity = new ProblemEntity();
            problemEntity.a(query.getString(query.getColumnIndex("problemId")));
            arrayList.add(problemEntity);
        }
    }

    public final void d(String str) {
        i.e("FeedbackDataManager", "deleteFailProblem", new Object[0]);
        SQLiteDatabase sQLiteDatabase = d;
        uhy.d(sQLiteDatabase);
        sQLiteDatabase.delete("table_feedback_problem", "problemId=?", new String[]{str});
    }

    private final boolean e(String str) {
        SQLiteDatabase sQLiteDatabase = d;
        uhy.d(sQLiteDatabase);
        Cursor query = sQLiteDatabase.query("table_feedback_problem", null, "problemId= ?", new String[]{str}, null, null, null);
        boolean z = false;
        while (query.moveToFirst()) {
            z = true;
        }
        query.close();
        return z;
    }

    @JvmStatic
    public static final FeedbackProblemData a(Context context) {
        return b.b(context);
    }

    public static final class e {
        @JvmStatic
        public final FeedbackProblemData b(Context context) {
            if (FeedbackProblemData.f8307a == null) {
                synchronized (FeedbackProblemData.class) {
                    if (FeedbackProblemData.f8307a == null) {
                        e eVar = FeedbackProblemData.b;
                        FeedbackProblemData.f8307a = new FeedbackProblemData(context != null ? context.getApplicationContext() : null);
                    }
                    ueu ueuVar = ueu.d;
                }
            }
            return FeedbackProblemData.f8307a;
        }

        public /* synthetic */ e(uib uibVar) {
            this();
        }

        private e() {
        }
    }

    public FeedbackProblemData(Context context) {
        super(context, "db_feedback_data", (SQLiteDatabase.CursorFactory) null, 3);
        synchronized (FeedbackMediaData.class) {
            if (d == null) {
                d = getWritableDatabase();
            }
            ueu ueuVar = ueu.d;
        }
    }
}
