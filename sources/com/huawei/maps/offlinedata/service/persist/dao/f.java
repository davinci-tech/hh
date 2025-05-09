package com.huawei.maps.offlinedata.service.persist.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.huawei.maps.offlinedata.handler.dto.persist.WorldMapOfflineDataItemEntity;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes9.dex */
public final class f implements e {

    /* renamed from: a, reason: collision with root package name */
    private final RoomDatabase f6522a;
    private final EntityInsertionAdapter<WorldMapOfflineDataItemEntity> b;
    private final SharedSQLiteStatement c;

    public f(RoomDatabase roomDatabase) {
        this.f6522a = roomDatabase;
        this.b = new EntityInsertionAdapter<WorldMapOfflineDataItemEntity>(roomDatabase) { // from class: com.huawei.maps.offlinedata.service.persist.dao.f.1
            @Override // androidx.room.EntityInsertionAdapter
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void bind(SupportSQLiteStatement supportSQLiteStatement, WorldMapOfflineDataItemEntity worldMapOfflineDataItemEntity) {
                supportSQLiteStatement.bindLong(1, worldMapOfflineDataItemEntity.getId());
                if (worldMapOfflineDataItemEntity.getCountryId() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, worldMapOfflineDataItemEntity.getCountryId());
                }
                if (worldMapOfflineDataItemEntity.getCountryName() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, worldMapOfflineDataItemEntity.getCountryName());
                }
                if (worldMapOfflineDataItemEntity.getRegionId() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, worldMapOfflineDataItemEntity.getRegionId());
                }
                if (worldMapOfflineDataItemEntity.getRegionName() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, worldMapOfflineDataItemEntity.getRegionName());
                }
                if (worldMapOfflineDataItemEntity.getCityId() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, worldMapOfflineDataItemEntity.getCityId());
                }
                if (worldMapOfflineDataItemEntity.getCityName() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, worldMapOfflineDataItemEntity.getCityName());
                }
                if (worldMapOfflineDataItemEntity.getPolitical() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, worldMapOfflineDataItemEntity.getPolitical());
                }
                if (worldMapOfflineDataItemEntity.getFileId() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, worldMapOfflineDataItemEntity.getFileId());
                }
                if (worldMapOfflineDataItemEntity.getVersion() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, worldMapOfflineDataItemEntity.getVersion());
                }
                if (worldMapOfflineDataItemEntity.getPackageSize() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, worldMapOfflineDataItemEntity.getPackageSize());
                }
                if (worldMapOfflineDataItemEntity.getOriginalSize() == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, worldMapOfflineDataItemEntity.getOriginalSize());
                }
                if (worldMapOfflineDataItemEntity.getFileCheck() == null) {
                    supportSQLiteStatement.bindNull(13);
                } else {
                    supportSQLiteStatement.bindString(13, worldMapOfflineDataItemEntity.getFileCheck());
                }
                if (worldMapOfflineDataItemEntity.getLastModifiedTime() == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindString(14, worldMapOfflineDataItemEntity.getLastModifiedTime());
                }
            }

            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR ABORT INTO `world_map_offline_data_item` (`id`,`countryId`,`countryName`,`regionId`,`regionName`,`cityId`,`cityName`,`political`,`fileId`,`version`,`packageSize`,`originalSize`,`fileCheck`,`lastModifiedTime`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }
        };
        this.c = new SharedSQLiteStatement(roomDatabase) { // from class: com.huawei.maps.offlinedata.service.persist.dao.f.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "delete from world_map_offline_data_item";
            }
        };
    }

    @Override // com.huawei.maps.offlinedata.service.persist.dao.e
    public void a(WorldMapOfflineDataItemEntity worldMapOfflineDataItemEntity) {
        this.f6522a.assertNotSuspendingTransaction();
        this.f6522a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter<WorldMapOfflineDataItemEntity>) worldMapOfflineDataItemEntity);
            this.f6522a.setTransactionSuccessful();
        } finally {
            this.f6522a.endTransaction();
        }
    }

    @Override // com.huawei.maps.offlinedata.service.persist.dao.e
    public void b() {
        this.f6522a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        this.f6522a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f6522a.setTransactionSuccessful();
        } finally {
            this.f6522a.endTransaction();
            this.c.release(acquire);
        }
    }

    @Override // com.huawei.maps.offlinedata.service.persist.dao.e
    public List<WorldMapOfflineDataItemEntity> a() {
        RoomSQLiteQuery roomSQLiteQuery;
        int columnIndexOrThrow;
        int columnIndexOrThrow2;
        int columnIndexOrThrow3;
        int columnIndexOrThrow4;
        int columnIndexOrThrow5;
        int columnIndexOrThrow6;
        int columnIndexOrThrow7;
        int columnIndexOrThrow8;
        int columnIndexOrThrow9;
        int columnIndexOrThrow10;
        int columnIndexOrThrow11;
        int columnIndexOrThrow12;
        int columnIndexOrThrow13;
        int i;
        String string;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from world_map_offline_data_item", 0);
        this.f6522a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f6522a, acquire, false, null);
        try {
            columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "countryId");
            columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "countryName");
            columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "regionId");
            columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "regionName");
            columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "cityId");
            columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "cityName");
            columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "political");
            columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, RecommendConstants.FILE_ID);
            columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "version");
            columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "packageSize");
            columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "originalSize");
            columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "fileCheck");
            roomSQLiteQuery = acquire;
        } catch (Throwable th) {
            th = th;
            roomSQLiteQuery = acquire;
        }
        try {
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "lastModifiedTime");
            ArrayList arrayList = new ArrayList(query.getCount());
            while (query.moveToNext()) {
                WorldMapOfflineDataItemEntity worldMapOfflineDataItemEntity = new WorldMapOfflineDataItemEntity();
                ArrayList arrayList2 = arrayList;
                int i2 = columnIndexOrThrow13;
                worldMapOfflineDataItemEntity.setId(query.getLong(columnIndexOrThrow));
                worldMapOfflineDataItemEntity.setCountryId(query.isNull(columnIndexOrThrow2) ? null : query.getString(columnIndexOrThrow2));
                worldMapOfflineDataItemEntity.setCountryName(query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3));
                worldMapOfflineDataItemEntity.setRegionId(query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4));
                worldMapOfflineDataItemEntity.setRegionName(query.isNull(columnIndexOrThrow5) ? null : query.getString(columnIndexOrThrow5));
                worldMapOfflineDataItemEntity.setCityId(query.isNull(columnIndexOrThrow6) ? null : query.getString(columnIndexOrThrow6));
                worldMapOfflineDataItemEntity.setCityName(query.isNull(columnIndexOrThrow7) ? null : query.getString(columnIndexOrThrow7));
                worldMapOfflineDataItemEntity.setPolitical(query.isNull(columnIndexOrThrow8) ? null : query.getString(columnIndexOrThrow8));
                worldMapOfflineDataItemEntity.setFileId(query.isNull(columnIndexOrThrow9) ? null : query.getString(columnIndexOrThrow9));
                worldMapOfflineDataItemEntity.setVersion(query.isNull(columnIndexOrThrow10) ? null : query.getString(columnIndexOrThrow10));
                worldMapOfflineDataItemEntity.setPackageSize(query.isNull(columnIndexOrThrow11) ? null : query.getString(columnIndexOrThrow11));
                worldMapOfflineDataItemEntity.setOriginalSize(query.isNull(columnIndexOrThrow12) ? null : query.getString(columnIndexOrThrow12));
                worldMapOfflineDataItemEntity.setFileCheck(query.isNull(i2) ? null : query.getString(i2));
                int i3 = columnIndexOrThrow14;
                if (query.isNull(i3)) {
                    i = columnIndexOrThrow;
                    string = null;
                } else {
                    i = columnIndexOrThrow;
                    string = query.getString(i3);
                }
                worldMapOfflineDataItemEntity.setLastModifiedTime(string);
                arrayList2.add(worldMapOfflineDataItemEntity);
                columnIndexOrThrow14 = i3;
                columnIndexOrThrow13 = i2;
                arrayList = arrayList2;
                columnIndexOrThrow = i;
            }
            ArrayList arrayList3 = arrayList;
            query.close();
            roomSQLiteQuery.release();
            return arrayList3;
        } catch (Throwable th2) {
            th = th2;
            query.close();
            roomSQLiteQuery.release();
            throw th;
        }
    }

    public static List<Class<?>> c() {
        return Collections.emptyList();
    }
}
