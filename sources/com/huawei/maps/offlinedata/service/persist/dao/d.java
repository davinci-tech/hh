package com.huawei.maps.offlinedata.service.persist.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.huawei.maps.offlinedata.handler.dto.persist.OfflineDataTaskEntity;
import com.huawei.operation.utils.Constants;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes9.dex */
public final class d implements c {

    /* renamed from: a, reason: collision with root package name */
    private final RoomDatabase f6516a;
    private final EntityInsertionAdapter<OfflineDataTaskEntity> b;
    private final SharedSQLiteStatement c;
    private final SharedSQLiteStatement d;
    private final SharedSQLiteStatement e;
    private final SharedSQLiteStatement f;

    public d(RoomDatabase roomDatabase) {
        this.f6516a = roomDatabase;
        this.b = new EntityInsertionAdapter<OfflineDataTaskEntity>(roomDatabase) { // from class: com.huawei.maps.offlinedata.service.persist.dao.d.1
            @Override // androidx.room.EntityInsertionAdapter
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void bind(SupportSQLiteStatement supportSQLiteStatement, OfflineDataTaskEntity offlineDataTaskEntity) {
                if (offlineDataTaskEntity.getId() == null) {
                    supportSQLiteStatement.bindNull(1);
                } else {
                    supportSQLiteStatement.bindString(1, offlineDataTaskEntity.getId());
                }
                if (offlineDataTaskEntity.getDataType() == null) {
                    supportSQLiteStatement.bindNull(2);
                } else {
                    supportSQLiteStatement.bindString(2, offlineDataTaskEntity.getDataType());
                }
                if (offlineDataTaskEntity.getCountryId() == null) {
                    supportSQLiteStatement.bindNull(3);
                } else {
                    supportSQLiteStatement.bindString(3, offlineDataTaskEntity.getCountryId());
                }
                if (offlineDataTaskEntity.getCountryName() == null) {
                    supportSQLiteStatement.bindNull(4);
                } else {
                    supportSQLiteStatement.bindString(4, offlineDataTaskEntity.getCountryName());
                }
                if (offlineDataTaskEntity.getRegionId() == null) {
                    supportSQLiteStatement.bindNull(5);
                } else {
                    supportSQLiteStatement.bindString(5, offlineDataTaskEntity.getRegionId());
                }
                if (offlineDataTaskEntity.getRegionName() == null) {
                    supportSQLiteStatement.bindNull(6);
                } else {
                    supportSQLiteStatement.bindString(6, offlineDataTaskEntity.getRegionName());
                }
                if (offlineDataTaskEntity.getCityId() == null) {
                    supportSQLiteStatement.bindNull(7);
                } else {
                    supportSQLiteStatement.bindString(7, offlineDataTaskEntity.getCityId());
                }
                if (offlineDataTaskEntity.getCityName() == null) {
                    supportSQLiteStatement.bindNull(8);
                } else {
                    supportSQLiteStatement.bindString(8, offlineDataTaskEntity.getCityName());
                }
                if (offlineDataTaskEntity.getPolitical() == null) {
                    supportSQLiteStatement.bindNull(9);
                } else {
                    supportSQLiteStatement.bindString(9, offlineDataTaskEntity.getPolitical());
                }
                if (offlineDataTaskEntity.getVersion() == null) {
                    supportSQLiteStatement.bindNull(10);
                } else {
                    supportSQLiteStatement.bindString(10, offlineDataTaskEntity.getVersion());
                }
                if (offlineDataTaskEntity.getTaskId() == null) {
                    supportSQLiteStatement.bindNull(11);
                } else {
                    supportSQLiteStatement.bindString(11, offlineDataTaskEntity.getTaskId());
                }
                if (offlineDataTaskEntity.getTaskState() == null) {
                    supportSQLiteStatement.bindNull(12);
                } else {
                    supportSQLiteStatement.bindString(12, offlineDataTaskEntity.getTaskState());
                }
                if (offlineDataTaskEntity.getItemState() == null) {
                    supportSQLiteStatement.bindNull(13);
                } else {
                    supportSQLiteStatement.bindString(13, offlineDataTaskEntity.getItemState());
                }
                if (offlineDataTaskEntity.getDownloadProgress() == null) {
                    supportSQLiteStatement.bindNull(14);
                } else {
                    supportSQLiteStatement.bindLong(14, offlineDataTaskEntity.getDownloadProgress().intValue());
                }
                if (offlineDataTaskEntity.getTransmitProgress() == null) {
                    supportSQLiteStatement.bindNull(15);
                } else {
                    supportSQLiteStatement.bindLong(15, offlineDataTaskEntity.getTransmitProgress().intValue());
                }
                if (offlineDataTaskEntity.getLastModifyTime() == null) {
                    supportSQLiteStatement.bindNull(16);
                } else {
                    supportSQLiteStatement.bindString(16, offlineDataTaskEntity.getLastModifyTime());
                }
            }

            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "INSERT OR REPLACE INTO `offline_data_task` (`id`,`dataType`,`countryId`,`countryName`,`regionId`,`regionName`,`cityId`,`cityName`,`political`,`version`,`taskId`,`taskState`,`itemState`,`downloadProgress`,`transmitProgress`,`lastModifyTime`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            }
        };
        this.c = new SharedSQLiteStatement(roomDatabase) { // from class: com.huawei.maps.offlinedata.service.persist.dao.d.2
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "update offline_data_task set itemState = ? , taskState = ? where id = ?";
            }
        };
        this.d = new SharedSQLiteStatement(roomDatabase) { // from class: com.huawei.maps.offlinedata.service.persist.dao.d.3
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "update offline_data_task set downloadProgress = ? , transmitProgress = ? where id = ?";
            }
        };
        this.e = new SharedSQLiteStatement(roomDatabase) { // from class: com.huawei.maps.offlinedata.service.persist.dao.d.4
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "delete from offline_data_task where id = ?";
            }
        };
        this.f = new SharedSQLiteStatement(roomDatabase) { // from class: com.huawei.maps.offlinedata.service.persist.dao.d.5
            @Override // androidx.room.SharedSQLiteStatement
            public String createQuery() {
                return "delete from offline_data_task";
            }
        };
    }

    @Override // com.huawei.maps.offlinedata.service.persist.dao.c
    public void a(OfflineDataTaskEntity offlineDataTaskEntity) {
        this.f6516a.assertNotSuspendingTransaction();
        this.f6516a.beginTransaction();
        try {
            this.b.insert((EntityInsertionAdapter<OfflineDataTaskEntity>) offlineDataTaskEntity);
            this.f6516a.setTransactionSuccessful();
        } finally {
            this.f6516a.endTransaction();
        }
    }

    @Override // com.huawei.maps.offlinedata.service.persist.dao.c
    public void a(String str, String str2, String str3) {
        this.f6516a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.c.acquire();
        if (str2 == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str2);
        }
        if (str3 == null) {
            acquire.bindNull(2);
        } else {
            acquire.bindString(2, str3);
        }
        if (str == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, str);
        }
        this.f6516a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f6516a.setTransactionSuccessful();
        } finally {
            this.f6516a.endTransaction();
            this.c.release(acquire);
        }
    }

    @Override // com.huawei.maps.offlinedata.service.persist.dao.c
    public void a(String str, int i, int i2) {
        this.f6516a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.d.acquire();
        acquire.bindLong(1, i);
        acquire.bindLong(2, i2);
        if (str == null) {
            acquire.bindNull(3);
        } else {
            acquire.bindString(3, str);
        }
        this.f6516a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f6516a.setTransactionSuccessful();
        } finally {
            this.f6516a.endTransaction();
            this.d.release(acquire);
        }
    }

    @Override // com.huawei.maps.offlinedata.service.persist.dao.c
    public void a(String str) {
        this.f6516a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.e.acquire();
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f6516a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f6516a.setTransactionSuccessful();
        } finally {
            this.f6516a.endTransaction();
            this.e.release(acquire);
        }
    }

    @Override // com.huawei.maps.offlinedata.service.persist.dao.c
    public void b() {
        this.f6516a.assertNotSuspendingTransaction();
        SupportSQLiteStatement acquire = this.f.acquire();
        this.f6516a.beginTransaction();
        try {
            acquire.executeUpdateDelete();
            this.f6516a.setTransactionSuccessful();
        } finally {
            this.f6516a.endTransaction();
            this.f.release(acquire);
        }
    }

    @Override // com.huawei.maps.offlinedata.service.persist.dao.c
    public List<OfflineDataTaskEntity> a() {
        RoomSQLiteQuery roomSQLiteQuery;
        int i;
        String string;
        int i2;
        Integer valueOf;
        int i3;
        Integer valueOf2;
        String string2;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from offline_data_task order by lastModifyTime desc", 0);
        this.f6516a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f6516a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "dataType");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "countryId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "countryName");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "regionId");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "regionName");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "cityId");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "cityName");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "political");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "version");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "taskId");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "taskState");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "itemState");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "downloadProgress");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "transmitProgress");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "lastModifyTime");
                int i4 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    OfflineDataTaskEntity offlineDataTaskEntity = new OfflineDataTaskEntity();
                    if (query.isNull(columnIndexOrThrow)) {
                        i = columnIndexOrThrow;
                        string = null;
                    } else {
                        i = columnIndexOrThrow;
                        string = query.getString(columnIndexOrThrow);
                    }
                    offlineDataTaskEntity.setId(string);
                    offlineDataTaskEntity.setDataType(query.isNull(columnIndexOrThrow2) ? null : query.getString(columnIndexOrThrow2));
                    offlineDataTaskEntity.setCountryId(query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3));
                    offlineDataTaskEntity.setCountryName(query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4));
                    offlineDataTaskEntity.setRegionId(query.isNull(columnIndexOrThrow5) ? null : query.getString(columnIndexOrThrow5));
                    offlineDataTaskEntity.setRegionName(query.isNull(columnIndexOrThrow6) ? null : query.getString(columnIndexOrThrow6));
                    offlineDataTaskEntity.setCityId(query.isNull(columnIndexOrThrow7) ? null : query.getString(columnIndexOrThrow7));
                    offlineDataTaskEntity.setCityName(query.isNull(columnIndexOrThrow8) ? null : query.getString(columnIndexOrThrow8));
                    offlineDataTaskEntity.setPolitical(query.isNull(columnIndexOrThrow9) ? null : query.getString(columnIndexOrThrow9));
                    offlineDataTaskEntity.setVersion(query.isNull(columnIndexOrThrow10) ? null : query.getString(columnIndexOrThrow10));
                    offlineDataTaskEntity.setTaskId(query.isNull(columnIndexOrThrow11) ? null : query.getString(columnIndexOrThrow11));
                    offlineDataTaskEntity.setTaskState(query.isNull(columnIndexOrThrow12) ? null : query.getString(columnIndexOrThrow12));
                    offlineDataTaskEntity.setItemState(query.isNull(columnIndexOrThrow13) ? null : query.getString(columnIndexOrThrow13));
                    int i5 = i4;
                    if (query.isNull(i5)) {
                        i2 = i5;
                        valueOf = null;
                    } else {
                        i2 = i5;
                        valueOf = Integer.valueOf(query.getInt(i5));
                    }
                    offlineDataTaskEntity.setDownloadProgress(valueOf);
                    int i6 = columnIndexOrThrow15;
                    if (query.isNull(i6)) {
                        i3 = i6;
                        valueOf2 = null;
                    } else {
                        i3 = i6;
                        valueOf2 = Integer.valueOf(query.getInt(i6));
                    }
                    offlineDataTaskEntity.setTransmitProgress(valueOf2);
                    int i7 = columnIndexOrThrow16;
                    if (query.isNull(i7)) {
                        columnIndexOrThrow16 = i7;
                        string2 = null;
                    } else {
                        columnIndexOrThrow16 = i7;
                        string2 = query.getString(i7);
                    }
                    offlineDataTaskEntity.setLastModifyTime(string2);
                    arrayList.add(offlineDataTaskEntity);
                    columnIndexOrThrow15 = i3;
                    i4 = i2;
                    columnIndexOrThrow = i;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
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

    @Override // com.huawei.maps.offlinedata.service.persist.dao.c
    public OfflineDataTaskEntity b(String str) {
        RoomSQLiteQuery roomSQLiteQuery;
        OfflineDataTaskEntity offlineDataTaskEntity;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from offline_data_task where taskId = ?", 1);
        if (str == null) {
            acquire.bindNull(1);
        } else {
            acquire.bindString(1, str);
        }
        this.f6516a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f6516a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "dataType");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "countryId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "countryName");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "regionId");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "regionName");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "cityId");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "cityName");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "political");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "version");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "taskId");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "taskState");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "itemState");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "downloadProgress");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "transmitProgress");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "lastModifyTime");
                if (query.moveToFirst()) {
                    OfflineDataTaskEntity offlineDataTaskEntity2 = new OfflineDataTaskEntity();
                    offlineDataTaskEntity2.setId(query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow));
                    offlineDataTaskEntity2.setDataType(query.isNull(columnIndexOrThrow2) ? null : query.getString(columnIndexOrThrow2));
                    offlineDataTaskEntity2.setCountryId(query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3));
                    offlineDataTaskEntity2.setCountryName(query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4));
                    offlineDataTaskEntity2.setRegionId(query.isNull(columnIndexOrThrow5) ? null : query.getString(columnIndexOrThrow5));
                    offlineDataTaskEntity2.setRegionName(query.isNull(columnIndexOrThrow6) ? null : query.getString(columnIndexOrThrow6));
                    offlineDataTaskEntity2.setCityId(query.isNull(columnIndexOrThrow7) ? null : query.getString(columnIndexOrThrow7));
                    offlineDataTaskEntity2.setCityName(query.isNull(columnIndexOrThrow8) ? null : query.getString(columnIndexOrThrow8));
                    offlineDataTaskEntity2.setPolitical(query.isNull(columnIndexOrThrow9) ? null : query.getString(columnIndexOrThrow9));
                    offlineDataTaskEntity2.setVersion(query.isNull(columnIndexOrThrow10) ? null : query.getString(columnIndexOrThrow10));
                    offlineDataTaskEntity2.setTaskId(query.isNull(columnIndexOrThrow11) ? null : query.getString(columnIndexOrThrow11));
                    offlineDataTaskEntity2.setTaskState(query.isNull(columnIndexOrThrow12) ? null : query.getString(columnIndexOrThrow12));
                    offlineDataTaskEntity2.setItemState(query.isNull(columnIndexOrThrow13) ? null : query.getString(columnIndexOrThrow13));
                    offlineDataTaskEntity2.setDownloadProgress(query.isNull(columnIndexOrThrow14) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow14)));
                    offlineDataTaskEntity2.setTransmitProgress(query.isNull(columnIndexOrThrow15) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow15)));
                    offlineDataTaskEntity2.setLastModifyTime(query.isNull(columnIndexOrThrow16) ? null : query.getString(columnIndexOrThrow16));
                    offlineDataTaskEntity = offlineDataTaskEntity2;
                } else {
                    offlineDataTaskEntity = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return offlineDataTaskEntity;
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

    @Override // com.huawei.maps.offlinedata.service.persist.dao.c
    public List<OfflineDataTaskEntity> a(List<String> list) {
        RoomSQLiteQuery roomSQLiteQuery;
        int i;
        String string;
        int i2;
        Integer valueOf;
        int i3;
        Integer valueOf2;
        String string2;
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("select * from offline_data_task where itemState in (");
        int size = list.size();
        StringUtil.appendPlaceholders(newStringBuilder, size);
        newStringBuilder.append(Constants.RIGHT_BRACKET_ONLY);
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size);
        int i4 = 1;
        for (String str : list) {
            if (str == null) {
                acquire.bindNull(i4);
            } else {
                acquire.bindString(i4, str);
            }
            i4++;
        }
        this.f6516a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f6516a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "dataType");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "countryId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "countryName");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "regionId");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "regionName");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "cityId");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "cityName");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "political");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "version");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "taskId");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "taskState");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "itemState");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "downloadProgress");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "transmitProgress");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "lastModifyTime");
                int i5 = columnIndexOrThrow14;
                ArrayList arrayList = new ArrayList(query.getCount());
                while (query.moveToNext()) {
                    OfflineDataTaskEntity offlineDataTaskEntity = new OfflineDataTaskEntity();
                    if (query.isNull(columnIndexOrThrow)) {
                        i = columnIndexOrThrow;
                        string = null;
                    } else {
                        i = columnIndexOrThrow;
                        string = query.getString(columnIndexOrThrow);
                    }
                    offlineDataTaskEntity.setId(string);
                    offlineDataTaskEntity.setDataType(query.isNull(columnIndexOrThrow2) ? null : query.getString(columnIndexOrThrow2));
                    offlineDataTaskEntity.setCountryId(query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3));
                    offlineDataTaskEntity.setCountryName(query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4));
                    offlineDataTaskEntity.setRegionId(query.isNull(columnIndexOrThrow5) ? null : query.getString(columnIndexOrThrow5));
                    offlineDataTaskEntity.setRegionName(query.isNull(columnIndexOrThrow6) ? null : query.getString(columnIndexOrThrow6));
                    offlineDataTaskEntity.setCityId(query.isNull(columnIndexOrThrow7) ? null : query.getString(columnIndexOrThrow7));
                    offlineDataTaskEntity.setCityName(query.isNull(columnIndexOrThrow8) ? null : query.getString(columnIndexOrThrow8));
                    offlineDataTaskEntity.setPolitical(query.isNull(columnIndexOrThrow9) ? null : query.getString(columnIndexOrThrow9));
                    offlineDataTaskEntity.setVersion(query.isNull(columnIndexOrThrow10) ? null : query.getString(columnIndexOrThrow10));
                    offlineDataTaskEntity.setTaskId(query.isNull(columnIndexOrThrow11) ? null : query.getString(columnIndexOrThrow11));
                    offlineDataTaskEntity.setTaskState(query.isNull(columnIndexOrThrow12) ? null : query.getString(columnIndexOrThrow12));
                    offlineDataTaskEntity.setItemState(query.isNull(columnIndexOrThrow13) ? null : query.getString(columnIndexOrThrow13));
                    int i6 = i5;
                    if (query.isNull(i6)) {
                        i2 = i6;
                        valueOf = null;
                    } else {
                        i2 = i6;
                        valueOf = Integer.valueOf(query.getInt(i6));
                    }
                    offlineDataTaskEntity.setDownloadProgress(valueOf);
                    int i7 = columnIndexOrThrow15;
                    if (query.isNull(i7)) {
                        i3 = i7;
                        valueOf2 = null;
                    } else {
                        i3 = i7;
                        valueOf2 = Integer.valueOf(query.getInt(i7));
                    }
                    offlineDataTaskEntity.setTransmitProgress(valueOf2);
                    int i8 = columnIndexOrThrow16;
                    if (query.isNull(i8)) {
                        columnIndexOrThrow16 = i8;
                        string2 = null;
                    } else {
                        columnIndexOrThrow16 = i8;
                        string2 = query.getString(i8);
                    }
                    offlineDataTaskEntity.setLastModifyTime(string2);
                    arrayList.add(offlineDataTaskEntity);
                    columnIndexOrThrow15 = i3;
                    i5 = i2;
                    columnIndexOrThrow = i;
                }
                query.close();
                roomSQLiteQuery.release();
                return arrayList;
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

    @Override // com.huawei.maps.offlinedata.service.persist.dao.c
    public OfflineDataTaskEntity a(String str, String str2) {
        RoomSQLiteQuery roomSQLiteQuery;
        OfflineDataTaskEntity offlineDataTaskEntity;
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire("select * from offline_data_task where cityId = ? and dataType=?", 2);
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
        this.f6516a.assertNotSuspendingTransaction();
        Cursor query = DBUtil.query(this.f6516a, acquire, false, null);
        try {
            int columnIndexOrThrow = CursorUtil.getColumnIndexOrThrow(query, "id");
            int columnIndexOrThrow2 = CursorUtil.getColumnIndexOrThrow(query, "dataType");
            int columnIndexOrThrow3 = CursorUtil.getColumnIndexOrThrow(query, "countryId");
            int columnIndexOrThrow4 = CursorUtil.getColumnIndexOrThrow(query, "countryName");
            int columnIndexOrThrow5 = CursorUtil.getColumnIndexOrThrow(query, "regionId");
            int columnIndexOrThrow6 = CursorUtil.getColumnIndexOrThrow(query, "regionName");
            int columnIndexOrThrow7 = CursorUtil.getColumnIndexOrThrow(query, "cityId");
            int columnIndexOrThrow8 = CursorUtil.getColumnIndexOrThrow(query, "cityName");
            int columnIndexOrThrow9 = CursorUtil.getColumnIndexOrThrow(query, "political");
            int columnIndexOrThrow10 = CursorUtil.getColumnIndexOrThrow(query, "version");
            int columnIndexOrThrow11 = CursorUtil.getColumnIndexOrThrow(query, "taskId");
            int columnIndexOrThrow12 = CursorUtil.getColumnIndexOrThrow(query, "taskState");
            int columnIndexOrThrow13 = CursorUtil.getColumnIndexOrThrow(query, "itemState");
            int columnIndexOrThrow14 = CursorUtil.getColumnIndexOrThrow(query, "downloadProgress");
            roomSQLiteQuery = acquire;
            try {
                int columnIndexOrThrow15 = CursorUtil.getColumnIndexOrThrow(query, "transmitProgress");
                int columnIndexOrThrow16 = CursorUtil.getColumnIndexOrThrow(query, "lastModifyTime");
                if (query.moveToFirst()) {
                    OfflineDataTaskEntity offlineDataTaskEntity2 = new OfflineDataTaskEntity();
                    offlineDataTaskEntity2.setId(query.isNull(columnIndexOrThrow) ? null : query.getString(columnIndexOrThrow));
                    offlineDataTaskEntity2.setDataType(query.isNull(columnIndexOrThrow2) ? null : query.getString(columnIndexOrThrow2));
                    offlineDataTaskEntity2.setCountryId(query.isNull(columnIndexOrThrow3) ? null : query.getString(columnIndexOrThrow3));
                    offlineDataTaskEntity2.setCountryName(query.isNull(columnIndexOrThrow4) ? null : query.getString(columnIndexOrThrow4));
                    offlineDataTaskEntity2.setRegionId(query.isNull(columnIndexOrThrow5) ? null : query.getString(columnIndexOrThrow5));
                    offlineDataTaskEntity2.setRegionName(query.isNull(columnIndexOrThrow6) ? null : query.getString(columnIndexOrThrow6));
                    offlineDataTaskEntity2.setCityId(query.isNull(columnIndexOrThrow7) ? null : query.getString(columnIndexOrThrow7));
                    offlineDataTaskEntity2.setCityName(query.isNull(columnIndexOrThrow8) ? null : query.getString(columnIndexOrThrow8));
                    offlineDataTaskEntity2.setPolitical(query.isNull(columnIndexOrThrow9) ? null : query.getString(columnIndexOrThrow9));
                    offlineDataTaskEntity2.setVersion(query.isNull(columnIndexOrThrow10) ? null : query.getString(columnIndexOrThrow10));
                    offlineDataTaskEntity2.setTaskId(query.isNull(columnIndexOrThrow11) ? null : query.getString(columnIndexOrThrow11));
                    offlineDataTaskEntity2.setTaskState(query.isNull(columnIndexOrThrow12) ? null : query.getString(columnIndexOrThrow12));
                    offlineDataTaskEntity2.setItemState(query.isNull(columnIndexOrThrow13) ? null : query.getString(columnIndexOrThrow13));
                    offlineDataTaskEntity2.setDownloadProgress(query.isNull(columnIndexOrThrow14) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow14)));
                    offlineDataTaskEntity2.setTransmitProgress(query.isNull(columnIndexOrThrow15) ? null : Integer.valueOf(query.getInt(columnIndexOrThrow15)));
                    offlineDataTaskEntity2.setLastModifyTime(query.isNull(columnIndexOrThrow16) ? null : query.getString(columnIndexOrThrow16));
                    offlineDataTaskEntity = offlineDataTaskEntity2;
                } else {
                    offlineDataTaskEntity = null;
                }
                query.close();
                roomSQLiteQuery.release();
                return offlineDataTaskEntity;
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
