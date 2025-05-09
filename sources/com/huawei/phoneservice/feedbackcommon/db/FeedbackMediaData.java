package com.huawei.phoneservice.feedbackcommon.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.huawei.operation.ble.BleConstants;
import com.huawei.phoneservice.faq.base.util.i;
import com.huawei.phoneservice.feedbackcommon.entity.MediaEntity;
import defpackage.ueu;
import defpackage.uhy;
import defpackage.uib;
import kotlin.jvm.JvmStatic;

/* loaded from: classes5.dex */
public final class FeedbackMediaData extends SQLiteOpenHelper {

    /* renamed from: a, reason: collision with root package name */
    public static final c f8306a = new c(null);
    private static SQLiteDatabase b;
    private static FeedbackMediaData e;

    public final void a(String str, Long l) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("duration", l);
        cfg_(contentValues, "attach", str);
    }

    public final void e(String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("cache", str2);
        cfg_(contentValues, "attach", str);
    }

    public final void c(MediaEntity mediaEntity) {
        uhy.e((Object) mediaEntity, "");
        ContentValues contentValues = new ContentValues();
        contentValues.put("attach", mediaEntity.attach);
        contentValues.put("cache", mediaEntity.cache);
        contentValues.put("createTime", mediaEntity.createTime);
        contentValues.put("duration", mediaEntity.duration);
        contentValues.put(BleConstants.KEY_PATH, mediaEntity.path);
        contentValues.put("uri", mediaEntity.strUri);
        contentValues.put("type", mediaEntity.type);
        contentValues.put("updateTime", mediaEntity.updateTime);
        contentValues.put("url", mediaEntity.url);
        SQLiteDatabase sQLiteDatabase = b;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.insert("table_media_entity", null, contentValues);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i == 1 || i == 2) {
            if (sQLiteDatabase != null) {
                try {
                    sQLiteDatabase.execSQL("ALTER TABLE \"table_media_entity\" ADD COLUMN \"uri\" String");
                } catch (SQLException e2) {
                    i.e("FeedbackMediaData", e2.getMessage());
                    return;
                }
            }
            if (sQLiteDatabase == null) {
                return;
            }
        } else if (i != 3 || sQLiteDatabase == null) {
            return;
        }
        sQLiteDatabase.execSQL("create table if not exists table_media_entity(_id INTEGER primary key autoincrement,attach String ,cache String ,createTime long ,duration long ,path String ,uri String ,type String ,updateTime long ,url String);");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        uhy.e((Object) sQLiteDatabase, "");
        sQLiteDatabase.execSQL("create table if not exists table_media_entity(_id INTEGER primary key autoincrement,attach String ,cache String ,createTime long ,duration long ,path String ,uri String ,type String ,updateTime long ,url String);");
    }

    public final MediaEntity d(String str) {
        SQLiteDatabase sQLiteDatabase = b;
        Cursor query = sQLiteDatabase != null ? sQLiteDatabase.query("table_media_entity", null, "path= ?", new String[]{str}, null, null, "updateTime desc") : null;
        Boolean valueOf = query != null ? Boolean.valueOf(query.moveToFirst()) : null;
        uhy.d(valueOf);
        if (valueOf.booleanValue()) {
            return cff_(query);
        }
        query.close();
        return null;
    }

    public final MediaEntity b(String str) {
        uhy.e((Object) str, "");
        return d("attach", str);
    }

    private final void cfg_(ContentValues contentValues, String str, String str2) {
        String[] strArr = {str2};
        SQLiteDatabase sQLiteDatabase = b;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.update("table_media_entity", contentValues, str + "=?", strArr);
        }
    }

    private final MediaEntity cff_(Cursor cursor) {
        MediaEntity mediaEntity = new MediaEntity();
        mediaEntity.attach = cursor.getString(cursor.getColumnIndex("attach"));
        mediaEntity.cache = cursor.getString(cursor.getColumnIndex("cache"));
        mediaEntity.createTime = Long.valueOf(cursor.getLong(cursor.getColumnIndex("createTime")));
        mediaEntity.duration = Long.valueOf(cursor.getLong(cursor.getColumnIndex("duration")));
        mediaEntity.path = cursor.getString(cursor.getColumnIndex(BleConstants.KEY_PATH));
        mediaEntity.strUri = cursor.getString(cursor.getColumnIndex("uri"));
        mediaEntity.type = cursor.getString(cursor.getColumnIndex("type"));
        mediaEntity.updateTime = Long.valueOf(cursor.getLong(cursor.getColumnIndex("updateTime")));
        mediaEntity.url = cursor.getString(cursor.getColumnIndex("url"));
        return mediaEntity;
    }

    @JvmStatic
    public static final FeedbackMediaData a(Context context) {
        return f8306a.c(context);
    }

    private final MediaEntity d(String str, String str2) {
        Cursor cursor;
        SQLiteDatabase sQLiteDatabase = b;
        if (sQLiteDatabase != null) {
            cursor = sQLiteDatabase.query("table_media_entity", null, str + "= ?", new String[]{str2}, null, null, null);
        } else {
            cursor = null;
        }
        Boolean valueOf = cursor != null ? Boolean.valueOf(cursor.moveToFirst()) : null;
        uhy.d(valueOf);
        if (valueOf.booleanValue()) {
            return cff_(cursor);
        }
        cursor.close();
        return null;
    }

    public static final class c {
        @JvmStatic
        public final FeedbackMediaData c(Context context) {
            uhy.e((Object) context, "");
            if (FeedbackMediaData.e == null) {
                synchronized (FeedbackMediaData.class) {
                    if (FeedbackMediaData.e == null) {
                        c cVar = FeedbackMediaData.f8306a;
                        Context applicationContext = context.getApplicationContext();
                        uhy.a(applicationContext, "");
                        FeedbackMediaData.e = new FeedbackMediaData(applicationContext);
                    }
                    ueu ueuVar = ueu.d;
                }
            }
            return FeedbackMediaData.e;
        }

        public /* synthetic */ c(uib uibVar) {
            this();
        }

        private c() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FeedbackMediaData(Context context) {
        super(context, "db_media_data", (SQLiteDatabase.CursorFactory) null, 4);
        uhy.e((Object) context, "");
        synchronized (FeedbackMediaData.class) {
            if (b == null) {
                b = getWritableDatabase();
            }
            ueu ueuVar = ueu.d;
        }
    }
}
