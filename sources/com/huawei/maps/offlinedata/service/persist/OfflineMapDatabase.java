package com.huawei.maps.offlinedata.service.persist;

import android.content.Context;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;
import com.huawei.maps.offlinedata.service.persist.dao.c;
import com.huawei.maps.offlinedata.service.persist.dao.e;

/* loaded from: classes5.dex */
public abstract class OfflineMapDatabase extends RoomDatabase {

    /* renamed from: a, reason: collision with root package name */
    private static volatile OfflineMapDatabase f6506a;
    private static final Migration b = new Migration(1, 2) { // from class: com.huawei.maps.offlinedata.service.persist.OfflineMapDatabase.1
        @Override // androidx.room.migration.Migration
        public void migrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            supportSQLiteDatabase.execSQL("ALTER TABLE offline_data_task ADD COLUMN lastModifyTime TEXT");
        }
    };

    public abstract com.huawei.maps.offlinedata.service.persist.dao.a a();

    public abstract c b();

    public abstract e c();

    public static OfflineMapDatabase a(Context context) {
        if (f6506a == null) {
            synchronized (OfflineMapDatabase.class) {
                if (f6506a == null) {
                    f6506a = b(context);
                }
            }
        }
        return f6506a;
    }

    private static OfflineMapDatabase b(Context context) {
        return (OfflineMapDatabase) Room.databaseBuilder(context, OfflineMapDatabase.class, "offline_data_map.db").addMigrations(b).build();
    }
}
