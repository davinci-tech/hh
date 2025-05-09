package com.huawei.maps.offlinedata.service.persist.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.huawei.maps.offlinedata.handler.dto.persist.MapOfflineDataItemEntity;
import com.huawei.ui.main.stories.recommendcloud.constants.RecommendConstants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes9.dex */
public final class b implements a {

    /* renamed from: a, reason: collision with root package name */
    private final RoomDatabase f6512a;
    private final EntityInsertionAdapter<MapOfflineDataItemEntity> b;
    private final SharedSQLiteStatement c;
    private final SharedSQLiteStatement d;

    public b(RoomDatabase roomDatabase) {
        this.f6512a = roomDatabase;
        this.b = new EntityInsertionAdapter<MapOfflineDataItemEntity>(roomDatabase) { // from class: com.huawei.maps.offlinedata.service.persist.dao.b.1
            @Override // androidx.room.EntityInsertionAdapter
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void bind(SupportSQLiteStatement supportSQLiteStatement, MapOfflineDataItemEntity mapOfflineDataItemEntity) {
                supportSQLiteStatement.bindLong(1, mapOfflineDataItemEntity.getId());
                if (mapOfflineDataItemEntity.getDataType() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, mapOfflineDataItemEntity.getDataType());
                }
                if (mapOfflineDataItemEntity.getCountryId() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, mapOfflineDataItemEntity.getCountryId());
                }
                if (mapOfflineDataItemEntity.getCountryCode() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, mapOfflineDataItemEntity.getCountryCode());
                }
                if (mapOfflineDataItemEntity.getCountryName() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, mapOfflineDataItemEntity.getCountryName());
                }
                if (mapOfflineDataItemEntity.getRegionId() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, mapOfflineDataItemEntity.getRegionId());
                }
                if (mapOfflineDataItemEntity.getRegionName() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, mapOfflineDataItemEntity.getRegionName());
                }
                if (mapOfflineDataItemEntity.getCityId() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, mapOfflineDataItemEntity.getCityId());
                }
                if (mapOfflineDataItemEntity.getCityName() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, mapOfflineDataItemEntity.getCityName());
                }
                if (mapOfflineDataItemEntity.getPolitical() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, mapOfflineDataItemEntity.getPolitical());
                }
                if (mapOfflineDataItemEntity.getFileId() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, mapOfflineDataItemEntity.getFileId());
                }
                if (mapOfflineDataItemEntity.getVersion() == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, mapOfflineDataItemEntity.getVersion());
                }
                if (mapOfflineDataItemEntity.getPackageSize() == null) {
                    supportSQLiteStatement.bindNull(13);
                } else {
                    supportSQLiteStatement.bindString(13, mapOfflineDataItemEntity.getPackageSize());
                }
                if (mapOfflineDataItemEntity.getOriginalSize() == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindString(14, mapOfflineDataItemEntity.getOriginalSize());
                }
                if (mapOfflineDataItemEntity.getFileCheck() == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindString(15, mapOfflineDataItemEntity.getFileCheck());
                }
                if (mapOfflineDataItemEntity.getLon() == null) {
                    supportSQLiteStatement.bindNull(16);
                } else {
                    supportSQLiteStatement.bindString(16, mapOfflineDataItemEntity.getLon());
                }
                if (mapOfflineDataItemEntity.getLat() == null) {
                    supportSQLiteStatement.bindNull(17);
                } else {
                    supportSQLiteStatement.bindString(17, mapOfflineDataItemEntity.getLat());
                }
                if (mapOfflineDataItemEntity.getMaxLon() == null) {
                    supportSQLiteStatement.bindNull(18);
                } else {
                    supportSQLiteStatement.bindString(18, mapOfflineDataItemEntity.getMaxLon());
                }
                if (mapOfflineDataItemEntity.getMaxLat() == null) {
                    supportSQLiteStatement.bindNull(19);
                } else {
                    supportSQLiteStatement.bindString(19, mapOfflineDataItemEntity.getMaxLat());
                }
                if (mapOfflineDataItemEntity.getMinLon() == null) {
                    supportSQLiteStatement.bindNull(20);
                } else {
                    supportSQLiteStatement.bindString(20, mapOfflineDataItemEntity.getMinLon());
                }
                if (mapOfflineDataItemEntity.getMinLat() == null) {
                    supportSQLiteStatement.bindNull(21);
                } else {
                    supportSQLiteStatement.bindString(21, mapOfflineDataItemEntity.getMinLat());
                }
                if (mapOfflineDataItemEntity.getLastModifiedTime() == null) {
                    supportSQLiteStatement.bindNull(22);
                } else {
                    supportSQLiteStatement.bindString(22, mapOfflineDataItemEntity.getLastModifiedTime());
                }
            }

            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `map_offline_data_item` (`id`,`dataType`,`countryId`,`countryCode`,`countryName`,`regionId`,`regionName`,`cityId`,`cityName`,`political`,`fileId`,`version`,`packageSize`,`originalSize`,`fileCheck`,`lon`,`lat`,`maxLon`,`maxLat`,`minLon`,`minLat`,`lastModifiedTime`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }
        };
        this.c = new SharedSQLiteStatement(roomDatabase) { // from class: com.huawei.maps.offlinedata.service.persist.dao.b.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "delete from map_offline_data_item";
            }
        };
        this.d = new SharedSQLiteStatement(roomDatabase) { // from class: com.huawei.maps.offlinedata.service.persist.dao.b.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "delete from map_offline_data_item where dataType=?";
            }
        };
    }

    @Override // com.huawei.maps.offlinedata.service.persist.dao.a
    public void a(List<MapOfflineDataItemEntity> list) {
        this.f6512a.assertNotSuspendingTransaction();
        this.f6512a.beginTransaction();
        try {
            this.b.insert(list);
            this.f6512a.setTransactionSuccessful();
        } finally {
            this.f6512a.endTransaction();
        }
    }

    @Override // com.huawei.maps.offlinedata.service.persist.dao.a
    public void b() {
        this.f6512a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        this.f6512a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f6512a.setTransactionSuccessful();
        } finally {
            this.f6512a.endTransaction();
            this.c.release(acquire);
        }
    }

    @Override // com.huawei.maps.offlinedata.service.persist.dao.a
    public void a(String str) {
        this.f6512a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.d.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f6512a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f6512a.setTransactionSuccessful();
        } finally {
            this.f6512a.endTransaction();
            this.d.release(acquire);
        }
    }

    @Override // com.huawei.maps.offlinedata.service.persist.dao.a
    public List<MapOfflineDataItemEntity> a() {
        RoomSQLiteQuery roomSQLiteQuery;
        int i;
        String string;
        int i2;
        String string2;
        String string3;
        String string4;
        String string5;
        String string6;
        String string7;
        String string8;
        String string9;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from map_offline_data_item", 0);
        this.f6512a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f6512a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "dataType");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "countryId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "countryCode");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "countryName");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "regionId");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "regionName");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "cityId");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "cityName");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "political");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, RecommendConstants.FILE_ID);
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "version");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "packageSize");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "originalSize");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "fileCheck");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "lon");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "lat");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "maxLon");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "maxLat");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "minLon");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "minLat");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "lastModifiedTime");
                int i3 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    MapOfflineDataItemEntity mapOfflineDataItemEntity = new MapOfflineDataItemEntity();
                    ArrayList arrayList2 = arrayList;
                    int i4 = columnIndexOrThrow13;
                    mapOfflineDataItemEntity.setId(query.getLong(columnIndexOrThrow));
                    mapOfflineDataItemEntity.setDataType(query.isNull(columnIndexOrThrow2) ? null : query.getString(columnIndexOrThrow2));
                    mapOfflineDataItemEntity.setCountryId(query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3));
                    mapOfflineDataItemEntity.setCountryCode(query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4));
                    mapOfflineDataItemEntity.setCountryName(query.isNull(columnIndexOrThrow5) ? null : query.getString(columnIndexOrThrow5));
                    mapOfflineDataItemEntity.setRegionId(query.isNull(columnIndexOrThrow6) ? null : query.getString(columnIndexOrThrow6));
                    mapOfflineDataItemEntity.setRegionName(query.isNull(columnIndexOrThrow7) ? null : query.getString(columnIndexOrThrow7));
                    mapOfflineDataItemEntity.setCityId(query.isNull(columnIndexOrThrow8) ? null : query.getString(columnIndexOrThrow8));
                    mapOfflineDataItemEntity.setCityName(query.isNull(columnIndexOrThrow9) ? null : query.getString(columnIndexOrThrow9));
                    mapOfflineDataItemEntity.setPolitical(query.isNull(columnIndexOrThrow10) ? null : query.getString(columnIndexOrThrow10));
                    mapOfflineDataItemEntity.setFileId(query.isNull(columnIndexOrThrow11) ? null : query.getString(columnIndexOrThrow11));
                    mapOfflineDataItemEntity.setVersion(query.isNull(columnIndexOrThrow12) ? null : query.getString(columnIndexOrThrow12));
                    mapOfflineDataItemEntity.setPackageSize(query.isNull(i4) ? null : query.getString(i4));
                    int i5 = i3;
                    if (query.isNull(i5)) {
                        i = columnIndexOrThrow;
                        string = null;
                    } else {
                        i = columnIndexOrThrow;
                        string = query.getString(i5);
                    }
                    mapOfflineDataItemEntity.setOriginalSize(string);
                    int i6 = columnIndexOrThrow15;
                    if (query.isNull(i6)) {
                        i2 = i6;
                        string2 = null;
                    } else {
                        i2 = i6;
                        string2 = query.getString(i6);
                    }
                    mapOfflineDataItemEntity.setFileCheck(string2);
                    int i7 = columnIndexOrThrow16;
                    if (query.isNull(i7)) {
                        columnIndexOrThrow16 = i7;
                        string3 = null;
                    } else {
                        columnIndexOrThrow16 = i7;
                        string3 = query.getString(i7);
                    }
                    mapOfflineDataItemEntity.setLon(string3);
                    int i8 = columnIndexOrThrow17;
                    if (query.isNull(i8)) {
                        columnIndexOrThrow17 = i8;
                        string4 = null;
                    } else {
                        columnIndexOrThrow17 = i8;
                        string4 = query.getString(i8);
                    }
                    mapOfflineDataItemEntity.setLat(string4);
                    int i9 = columnIndexOrThrow18;
                    if (query.isNull(i9)) {
                        columnIndexOrThrow18 = i9;
                        string5 = null;
                    } else {
                        columnIndexOrThrow18 = i9;
                        string5 = query.getString(i9);
                    }
                    mapOfflineDataItemEntity.setMaxLon(string5);
                    int i10 = columnIndexOrThrow19;
                    if (query.isNull(i10)) {
                        columnIndexOrThrow19 = i10;
                        string6 = null;
                    } else {
                        columnIndexOrThrow19 = i10;
                        string6 = query.getString(i10);
                    }
                    mapOfflineDataItemEntity.setMaxLat(string6);
                    int i11 = columnIndexOrThrow20;
                    if (query.isNull(i11)) {
                        columnIndexOrThrow20 = i11;
                        string7 = null;
                    } else {
                        columnIndexOrThrow20 = i11;
                        string7 = query.getString(i11);
                    }
                    mapOfflineDataItemEntity.setMinLon(string7);
                    int i12 = columnIndexOrThrow21;
                    if (query.isNull(i12)) {
                        columnIndexOrThrow21 = i12;
                        string8 = null;
                    } else {
                        columnIndexOrThrow21 = i12;
                        string8 = query.getString(i12);
                    }
                    mapOfflineDataItemEntity.setMinLat(string8);
                    int i13 = columnIndexOrThrow22;
                    if (query.isNull(i13)) {
                        columnIndexOrThrow22 = i13;
                        string9 = null;
                    } else {
                        columnIndexOrThrow22 = i13;
                        string9 = query.getString(i13);
                    }
                    mapOfflineDataItemEntity.setLastModifiedTime(string9);
                    arrayList2.add(mapOfflineDataItemEntity);
                    columnIndexOrThrow15 = i2;
                    i3 = i5;
                    columnIndexOrThrow13 = i4;
                    arrayList = arrayList2;
                    columnIndexOrThrow = i;
                }
                ArrayList arrayList3 = arrayList;
                query.close();
                roomSQLiteQuery.release();
                return arrayList3;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    @Override // com.huawei.maps.offlinedata.service.persist.dao.a
    public MapOfflineDataItemEntity a(String str, String str2, String str3) {
        RoomSQLiteQuery roomSQLiteQuery;
        MapOfflineDataItemEntity mapOfflineDataItemEntity;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from map_offline_data_item where cityId=? and dataType=? and version=? limit 1", 3);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        if (str2 == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str2);
        }
        if (str3 == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, str3);
        }
        this.f6512a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f6512a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "dataType");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "countryId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "countryCode");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "countryName");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "regionId");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "regionName");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "cityId");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "cityName");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "political");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, RecommendConstants.FILE_ID);
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "version");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "packageSize");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "originalSize");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "fileCheck");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "lon");
                int columnIndexOrThrow17 = CursorUtil.getColumnIndexOrThrow(query, "lat");
                int columnIndexOrThrow18 = CursorUtil.getColumnIndexOrThrow(query, "maxLon");
                int columnIndexOrThrow19 = CursorUtil.getColumnIndexOrThrow(query, "maxLat");
                int columnIndexOrThrow20 = CursorUtil.getColumnIndexOrThrow(query, "minLon");
                int columnIndexOrThrow21 = CursorUtil.getColumnIndexOrThrow(query, "minLat");
                int columnIndexOrThrow22 = CursorUtil.getColumnIndexOrThrow(query, "lastModifiedTime");
                if (query.moveToFirst()) {
                    MapOfflineDataItemEntity mapOfflineDataItemEntity2 = new MapOfflineDataItemEntity();
                    mapOfflineDataItemEntity2.setId(query.getLong(columnIndexOrThrow));
                    mapOfflineDataItemEntity2.setDataType(query.isNull(columnIndexOrThrow2) ? null : query.getString(columnIndexOrThrow2));
                    mapOfflineDataItemEntity2.setCountryId(query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3));
                    mapOfflineDataItemEntity2.setCountryCode(query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4));
                    mapOfflineDataItemEntity2.setCountryName(query.isNull(columnIndexOrThrow5) ? null : query.getString(columnIndexOrThrow5));
                    mapOfflineDataItemEntity2.setRegionId(query.isNull(columnIndexOrThrow6) ? null : query.getString(columnIndexOrThrow6));
                    mapOfflineDataItemEntity2.setRegionName(query.isNull(columnIndexOrThrow7) ? null : query.getString(columnIndexOrThrow7));
                    mapOfflineDataItemEntity2.setCityId(query.isNull(columnIndexOrThrow8) ? null : query.getString(columnIndexOrThrow8));
                    mapOfflineDataItemEntity2.setCityName(query.isNull(columnIndexOrThrow9) ? null : query.getString(columnIndexOrThrow9));
                    mapOfflineDataItemEntity2.setPolitical(query.isNull(columnIndexOrThrow10) ? null : query.getString(columnIndexOrThrow10));
                    mapOfflineDataItemEntity2.setFileId(query.isNull(columnIndexOrThrow11) ? null : query.getString(columnIndexOrThrow11));
                    mapOfflineDataItemEntity2.setVersion(query.isNull(columnIndexOrThrow12) ? null : query.getString(columnIndexOrThrow12));
                    mapOfflineDataItemEntity2.setPackageSize(query.isNull(columnIndexOrThrow13) ? null : query.getString(columnIndexOrThrow13));
                    mapOfflineDataItemEntity2.setOriginalSize(query.isNull(columnIndexOrThrow14) ? null : query.getString(columnIndexOrThrow14));
                    mapOfflineDataItemEntity2.setFileCheck(query.isNull(columnIndexOrThrow15) ? null : query.getString(columnIndexOrThrow15));
                    mapOfflineDataItemEntity2.setLon(query.isNull(columnIndexOrThrow16) ? null : query.getString(columnIndexOrThrow16));
                    mapOfflineDataItemEntity2.setLat(query.isNull(columnIndexOrThrow17) ? null : query.getString(columnIndexOrThrow17));
                    mapOfflineDataItemEntity2.setMaxLon(query.isNull(columnIndexOrThrow18) ? null : query.getString(columnIndexOrThrow18));
                    mapOfflineDataItemEntity2.setMaxLat(query.isNull(columnIndexOrThrow19) ? null : query.getString(columnIndexOrThrow19));
                    mapOfflineDataItemEntity2.setMinLon(query.isNull(columnIndexOrThrow20) ? null : query.getString(columnIndexOrThrow20));
                    mapOfflineDataItemEntity2.setMinLat(query.isNull(columnIndexOrThrow21) ? null : query.getString(columnIndexOrThrow21));
                    mapOfflineDataItemEntity2.setLastModifiedTime(query.isNull(columnIndexOrThrow22) ? null : query.getString(columnIndexOrThrow22));
                    mapOfflineDataItemEntity = mapOfflineDataItemEntity2;
                } else {
                    mapOfflineDataItemEntity = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return mapOfflineDataItemEntity;
            } catch (Throwable th) {
                th = th;
                query.close();
                roomSQLiteQuery.release();
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            roomSQLiteQuery = acquire;
        }
    }

    public static List<Class<?>> c() {
        return Collections.emptyList();
    }
}
