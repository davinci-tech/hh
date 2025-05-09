package com.huawei.maps.offlinedata.service.persist;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomMasterTable;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.huawei.maps.offlinedata.service.persist.dao.c;
import com.huawei.maps.offlinedata.service.persist.dao.d;
import com.huawei.maps.offlinedata.service.persist.dao.e;
import com.huawei.maps.offlinedata.service.persist.dao.f;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes9.dex */
public final class OfflineMapDatabase_Impl extends OfflineMapDatabase {

    /* renamed from: a, reason: collision with root package name */
    private volatile com.huawei.maps.offlinedata.service.persist.dao.a f6507a;
    private volatile c b;
    private volatile e c;

    @Override // androidx.room.RoomDatabase
    public SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration databaseConfiguration) {
        return databaseConfiguration.sqliteOpenHelperFactory.create(SupportSQLiteOpenHelper.Configuration.builder(databaseConfiguration.context).name(databaseConfiguration.name).callback(new RoomOpenHelper(databaseConfiguration, new RoomOpenHelper.Delegate(2) { // from class: com.huawei.maps.offlinedata.service.persist.OfflineMapDatabase_Impl.1
            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPostMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void createAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `map_offline_data_item` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `dataType` TEXT, `countryId` TEXT, `countryCode` TEXT, `countryName` TEXT, `regionId` TEXT, `regionName` TEXT, `cityId` TEXT, `cityName` TEXT, `political` TEXT, `fileId` TEXT, `version` TEXT, `packageSize` TEXT, `originalSize` TEXT, `fileCheck` TEXT, `lon` TEXT, `lat` TEXT, `maxLon` TEXT, `maxLat` TEXT, `minLon` TEXT, `minLat` TEXT, `lastModifiedTime` TEXT)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `world_map_offline_data_item` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `countryId` TEXT, `countryName` TEXT, `regionId` TEXT, `regionName` TEXT, `cityId` TEXT, `cityName` TEXT, `political` TEXT, `fileId` TEXT, `version` TEXT, `packageSize` TEXT, `originalSize` TEXT, `fileCheck` TEXT, `lastModifiedTime` TEXT)");
                supportSQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS `offline_data_task` (`id` TEXT NOT NULL, `dataType` TEXT, `countryId` TEXT, `countryName` TEXT, `regionId` TEXT, `regionName` TEXT, `cityId` TEXT, `cityName` TEXT, `political` TEXT, `version` TEXT, `taskId` TEXT, `taskState` TEXT, `itemState` TEXT, `downloadProgress` INTEGER, `transmitProgress` INTEGER, `lastModifyTime` TEXT, PRIMARY KEY(`id`))");
                supportSQLiteDatabase.execSQL(RoomMasterTable.CREATE_QUERY);
                supportSQLiteDatabase.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '47bb49ea40c88cc318512611ba051495')");
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void dropAllTables(SupportSQLiteDatabase supportSQLiteDatabase) {
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `map_offline_data_item`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `world_map_offline_data_item`");
                supportSQLiteDatabase.execSQL("DROP TABLE IF EXISTS `offline_data_task`");
                if (OfflineMapDatabase_Impl.this.mCallbacks != null) {
                    int size = OfflineMapDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) OfflineMapDatabase_Impl.this.mCallbacks.get(i)).onDestructiveMigration(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onCreate(SupportSQLiteDatabase supportSQLiteDatabase) {
                if (OfflineMapDatabase_Impl.this.mCallbacks != null) {
                    int size = OfflineMapDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) OfflineMapDatabase_Impl.this.mCallbacks.get(i)).onCreate(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onOpen(SupportSQLiteDatabase supportSQLiteDatabase) {
                OfflineMapDatabase_Impl.this.mDatabase = supportSQLiteDatabase;
                OfflineMapDatabase_Impl.this.internalInitInvalidationTracker(supportSQLiteDatabase);
                if (OfflineMapDatabase_Impl.this.mCallbacks != null) {
                    int size = OfflineMapDatabase_Impl.this.mCallbacks.size();
                    for (int i = 0; i < size; i++) {
                        ((RoomDatabase.Callback) OfflineMapDatabase_Impl.this.mCallbacks.get(i)).onOpen(supportSQLiteDatabase);
                    }
                }
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public void onPreMigrate(SupportSQLiteDatabase supportSQLiteDatabase) {
                DBUtil.dropFtsSyncTriggers(supportSQLiteDatabase);
            }

            @Override // androidx.room.RoomOpenHelper.Delegate
            public RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase supportSQLiteDatabase) {
                HashMap hashMap = new HashMap(22);
                hashMap.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                hashMap.put("dataType", new TableInfo.Column("dataType", "TEXT", false, 0, null, 1));
                hashMap.put("countryId", new TableInfo.Column("countryId", "TEXT", false, 0, null, 1));
                hashMap.put("countryCode", new TableInfo.Column("countryCode", "TEXT", false, 0, null, 1));
                hashMap.put("countryName", new TableInfo.Column("countryName", "TEXT", false, 0, null, 1));
                hashMap.put("regionId", new TableInfo.Column("regionId", "TEXT", false, 0, null, 1));
                hashMap.put("regionName", new TableInfo.Column("regionName", "TEXT", false, 0, null, 1));
                hashMap.put("cityId", new TableInfo.Column("cityId", "TEXT", false, 0, null, 1));
                hashMap.put("cityName", new TableInfo.Column("cityName", "TEXT", false, 0, null, 1));
                hashMap.put("political", new TableInfo.Column("political", "TEXT", false, 0, null, 1));
                hashMap.put(RecommendConstants.FILE_ID, new TableInfo.Column(RecommendConstants.FILE_ID, "TEXT", false, 0, null, 1));
                hashMap.put("version", new TableInfo.Column("version", "TEXT", false, 0, null, 1));
                hashMap.put("packageSize", new TableInfo.Column("packageSize", "TEXT", false, 0, null, 1));
                hashMap.put("originalSize", new TableInfo.Column("originalSize", "TEXT", false, 0, null, 1));
                hashMap.put("fileCheck", new TableInfo.Column("fileCheck", "TEXT", false, 0, null, 1));
                hashMap.put("lon", new TableInfo.Column("lon", "TEXT", false, 0, null, 1));
                hashMap.put("lat", new TableInfo.Column("lat", "TEXT", false, 0, null, 1));
                hashMap.put("maxLon", new TableInfo.Column("maxLon", "TEXT", false, 0, null, 1));
                hashMap.put("maxLat", new TableInfo.Column("maxLat", "TEXT", false, 0, null, 1));
                hashMap.put("minLon", new TableInfo.Column("minLon", "TEXT", false, 0, null, 1));
                hashMap.put("minLat", new TableInfo.Column("minLat", "TEXT", false, 0, null, 1));
                hashMap.put("lastModifiedTime", new TableInfo.Column("lastModifiedTime", "TEXT", false, 0, null, 1));
                TableInfo tableInfo = new TableInfo("map_offline_data_item", hashMap, new HashSet(0), new HashSet(0));
                TableInfo read = TableInfo.read(supportSQLiteDatabase, "map_offline_data_item");
                if (!tableInfo.equals(read)) {
                    return new RoomOpenHelper.ValidationResult(false, "map_offline_data_item(com.huawei.maps.offlinedata.handler.dto.persist.MapOfflineDataItemEntity).\n Expected:\n" + tableInfo + "\n Found:\n" + read);
                }
                HashMap hashMap2 = new HashMap(14);
                hashMap2.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, 1));
                hashMap2.put("countryId", new TableInfo.Column("countryId", "TEXT", false, 0, null, 1));
                hashMap2.put("countryName", new TableInfo.Column("countryName", "TEXT", false, 0, null, 1));
                hashMap2.put("regionId", new TableInfo.Column("regionId", "TEXT", false, 0, null, 1));
                hashMap2.put("regionName", new TableInfo.Column("regionName", "TEXT", false, 0, null, 1));
                hashMap2.put("cityId", new TableInfo.Column("cityId", "TEXT", false, 0, null, 1));
                hashMap2.put("cityName", new TableInfo.Column("cityName", "TEXT", false, 0, null, 1));
                hashMap2.put("political", new TableInfo.Column("political", "TEXT", false, 0, null, 1));
                hashMap2.put(RecommendConstants.FILE_ID, new TableInfo.Column(RecommendConstants.FILE_ID, "TEXT", false, 0, null, 1));
                hashMap2.put("version", new TableInfo.Column("version", "TEXT", false, 0, null, 1));
                hashMap2.put("packageSize", new TableInfo.Column("packageSize", "TEXT", false, 0, null, 1));
                hashMap2.put("originalSize", new TableInfo.Column("originalSize", "TEXT", false, 0, null, 1));
                hashMap2.put("fileCheck", new TableInfo.Column("fileCheck", "TEXT", false, 0, null, 1));
                hashMap2.put("lastModifiedTime", new TableInfo.Column("lastModifiedTime", "TEXT", false, 0, null, 1));
                TableInfo tableInfo2 = new TableInfo("world_map_offline_data_item", hashMap2, new HashSet(0), new HashSet(0));
                TableInfo read2 = TableInfo.read(supportSQLiteDatabase, "world_map_offline_data_item");
                if (!tableInfo2.equals(read2)) {
                    return new RoomOpenHelper.ValidationResult(false, "world_map_offline_data_item(com.huawei.maps.offlinedata.handler.dto.persist.WorldMapOfflineDataItemEntity).\n Expected:\n" + tableInfo2 + "\n Found:\n" + read2);
                }
                HashMap hashMap3 = new HashMap(16);
                hashMap3.put("id", new TableInfo.Column("id", "TEXT", true, 1, null, 1));
                hashMap3.put("dataType", new TableInfo.Column("dataType", "TEXT", false, 0, null, 1));
                hashMap3.put("countryId", new TableInfo.Column("countryId", "TEXT", false, 0, null, 1));
                hashMap3.put("countryName", new TableInfo.Column("countryName", "TEXT", false, 0, null, 1));
                hashMap3.put("regionId", new TableInfo.Column("regionId", "TEXT", false, 0, null, 1));
                hashMap3.put("regionName", new TableInfo.Column("regionName", "TEXT", false, 0, null, 1));
                hashMap3.put("cityId", new TableInfo.Column("cityId", "TEXT", false, 0, null, 1));
                hashMap3.put("cityName", new TableInfo.Column("cityName", "TEXT", false, 0, null, 1));
                hashMap3.put("political", new TableInfo.Column("political", "TEXT", false, 0, null, 1));
                hashMap3.put("version", new TableInfo.Column("version", "TEXT", false, 0, null, 1));
                hashMap3.put("taskId", new TableInfo.Column("taskId", "TEXT", false, 0, null, 1));
                hashMap3.put("taskState", new TableInfo.Column("taskState", "TEXT", false, 0, null, 1));
                hashMap3.put("itemState", new TableInfo.Column("itemState", "TEXT", false, 0, null, 1));
                hashMap3.put("downloadProgress", new TableInfo.Column("downloadProgress", "INTEGER", false, 0, null, 1));
                hashMap3.put("transmitProgress", new TableInfo.Column("transmitProgress", "INTEGER", false, 0, null, 1));
                hashMap3.put("lastModifyTime", new TableInfo.Column("lastModifyTime", "TEXT", false, 0, null, 1));
                TableInfo tableInfo3 = new TableInfo("offline_data_task", hashMap3, new HashSet(0), new HashSet(0));
                TableInfo read3 = TableInfo.read(supportSQLiteDatabase, "offline_data_task");
                if (!tableInfo3.equals(read3)) {
                    return new RoomOpenHelper.ValidationResult(false, "offline_data_task(com.huawei.maps.offlinedata.handler.dto.persist.OfflineDataTaskEntity).\n Expected:\n" + tableInfo3 + "\n Found:\n" + read3);
                }
                return new RoomOpenHelper.ValidationResult(true, null);
            }
        }, "47bb49ea40c88cc318512611ba051495", "122ee7cf83459163c3e4f078c0a591b6")).build());
    }

    @Override // androidx.room.RoomDatabase
    public InvalidationTracker createInvalidationTracker() {
        return new InvalidationTracker(this, new HashMap(0), new HashMap(0), "map_offline_data_item", "world_map_offline_data_item", "offline_data_task");
    }

    @Override // androidx.room.RoomDatabase
    public void clearAllTables() {
        super.assertNotMainThread();
        SupportSQLiteDatabase writableDatabase = super.getOpenHelper().getWritableDatabase();
        try {
            super.beginTransaction();
            writableDatabase.execSQL("DELETE FROM `map_offline_data_item`");
            writableDatabase.execSQL("DELETE FROM `world_map_offline_data_item`");
            writableDatabase.execSQL("DELETE FROM `offline_data_task`");
            super.setTransactionSuccessful();
        } finally {
            super.endTransaction();
            writableDatabase.query("PRAGMA wal_checkpoint(FULL)").close();
            if (!writableDatabase.inTransaction()) {
                writableDatabase.execSQL("VACUUM");
            }
        }
    }

    @Override // androidx.room.RoomDatabase
    public Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
        HashMap hashMap = new HashMap();
        hashMap.put(com.huawei.maps.offlinedata.service.persist.dao.a.class, com.huawei.maps.offlinedata.service.persist.dao.b.c());
        hashMap.put(c.class, d.c());
        hashMap.put(e.class, f.c());
        return hashMap;
    }

    @Override // androidx.room.RoomDatabase
    public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
        return new HashSet();
    }

    @Override // androidx.room.RoomDatabase
    public List<Migration> getAutoMigrations(Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> map) {
        return Arrays.asList(new Migration[0]);
    }

    @Override // com.huawei.maps.offlinedata.service.persist.OfflineMapDatabase
    public com.huawei.maps.offlinedata.service.persist.dao.a a() {
        com.huawei.maps.offlinedata.service.persist.dao.a aVar;
        if (this.f6507a != null) {
            return this.f6507a;
        }
        synchronized (this) {
            if (this.f6507a == null) {
                this.f6507a = new com.huawei.maps.offlinedata.service.persist.dao.b(this);
            }
            aVar = this.f6507a;
        }
        return aVar;
    }

    @Override // com.huawei.maps.offlinedata.service.persist.OfflineMapDatabase
    public c b() {
        c cVar;
        if (this.b != null) {
            return this.b;
        }
        synchronized (this) {
            if (this.b == null) {
                this.b = new d(this);
            }
            cVar = this.b;
        }
        return cVar;
    }

    @Override // com.huawei.maps.offlinedata.service.persist.OfflineMapDatabase
    public e c() {
        e eVar;
        if (this.c != null) {
            return this.c;
        }
        synchronized (this) {
            if (this.c == null) {
                this.c = new f(this);
            }
            eVar = this.c;
        }
        return eVar;
    }
}
