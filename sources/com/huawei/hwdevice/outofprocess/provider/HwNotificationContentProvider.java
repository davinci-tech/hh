package com.huawei.hwdevice.outofprocess.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.StaleDataException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.text.TextUtils;
import com.huawei.health.device.model.RecordAction;
import com.huawei.hwcommonmodel.application.BaseApplication;
import defpackage.jdz;
import health.compact.a.CommonUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.LogUtil;

/* loaded from: classes.dex */
public class HwNotificationContentProvider extends ContentProvider {

    /* renamed from: a, reason: collision with root package name */
    public static final String f6321a;
    private static UriMatcher c;
    private e b;
    private SQLiteDatabase d;

    @Override // android.content.ContentProvider
    public String getType(Uri uri) {
        return null;
    }

    static {
        String str = BaseApplication.getAppPackage() + ".HwNotificationContentProvider";
        f6321a = str;
        c = null;
        UriMatcher uriMatcher = new UriMatcher(-1);
        c = uriMatcher;
        uriMatcher.addURI(str, "NotificationList", 1);
        c.addURI(str, "NotificationList/#", 2);
        c.addURI(str, "MidwareAuthority", 3);
        c.addURI(str, "NotificationFlags", 4);
        c.addURI(str, "NotificationCollaboration", 5);
        c.addURI(str, "ConnectDeviceInfo", 6);
        c.addURI(str, "ClockState", 7);
    }

    @Override // android.content.ContentProvider
    public boolean onCreate() {
        LogUtil.c("HwNotificationContentProvider", "enter HwNotificationContentProvider  onCreate");
        this.b = new e(getContext());
        return true;
    }

    @Override // android.content.ContentProvider
    public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2) {
        String str3;
        Exception e2;
        String str4;
        try {
            this.d = this.b.getReadableDatabase();
            int match = c.match(uri);
            if (match == 1) {
                c(strArr, str, strArr2, str2);
            } else if (match == 2) {
                try {
                    str4 = uri.getPathSegments().get(2);
                } catch (Exception e3) {
                    e2 = e3;
                    str4 = "";
                }
                try {
                    LogUtil.c("HwNotificationContentProvider", "query NotificationList/# name: ", str4);
                } catch (Exception e4) {
                    e2 = e4;
                    LogUtil.e("HwNotificationContentProvider", "query NotificationList/# name exception: ", LogAnonymous.b((Throwable) e2));
                    str = RecordAction.ACT_NAME_TAG + str4;
                    str3 = "NotificationList";
                    return this.d.query(str3, strArr, str, strArr2, null, null, str2);
                }
                str = RecordAction.ACT_NAME_TAG + str4;
            } else {
                if (match == 3) {
                    str3 = "MidwareAuthority";
                } else if (match == 4) {
                    str3 = "NotificationFlags";
                } else if (match == 6) {
                    str3 = "ConnectDeviceInfo";
                } else {
                    if (match != 7) {
                        throw new IllegalArgumentException("This is a unKnow Uri" + uri.toString());
                    }
                    str3 = "ClockState";
                }
                return this.d.query(str3, strArr, str, strArr2, null, null, str2);
            }
            str3 = "NotificationList";
            return this.d.query(str3, strArr, str, strArr2, null, null, str2);
        } catch (Exception e5) {
            LogUtil.e("HwNotificationContentProvider", "HwNotificationContentProvider query parse name exception: ", LogAnonymous.b((Throwable) e5));
            return null;
        }
    }

    @Override // android.content.ContentProvider
    public Uri insert(Uri uri, ContentValues contentValues) {
        this.d = this.b.getWritableDatabase();
        int match = c.match(uri);
        if (match == 1) {
            long insert = this.d.insert("NotificationList", "name", contentValues);
            Uri withAppendedPath = Uri.withAppendedPath(uri, "/" + insert);
            LogUtil.c("HwNotificationContentProvider", "TABLE_SUB_SWITCH_LIST inserted row id: ", Long.valueOf(insert), ",inserted row: ", contentValues.toString());
            if (!TextUtils.equals("connect_wear", contentValues.getAsString("name"))) {
                getContext().getContentResolver().notifyChange(uri, null);
            }
            return withAppendedPath;
        }
        if (match == 5) {
            getContext().getContentResolver().notifyChange(uri, null);
            LogUtil.c("HwNotificationContentProvider", "TABLE_COLLABORATION insert notifyChange!");
            return uri;
        }
        if (match == 7) {
            LogUtil.c("HwNotificationContentProvider", "insert clock state");
            if (d()) {
                LogUtil.c("HwNotificationContentProvider", "insert ClockState table exist");
                try {
                    LogUtil.c("HwNotificationContentProvider", "insert clock state count: ", Long.valueOf(this.d.insert("ClockState", null, contentValues)));
                } catch (SQLException unused) {
                    LogUtil.e("HwNotificationContentProvider", "update ClockState table exist update error");
                }
            } else {
                LogUtil.c("HwNotificationContentProvider", "insert ClockState table not exist");
                try {
                    this.d.execSQL("CREATE TABLE IF NOT EXISTS ClockState( deviceMac TEXT not null PRIMARY KEY,clockValue TEXT)");
                    LogUtil.c("HwNotificationContentProvider", "insert clock state count: ", Long.valueOf(this.d.insert("ClockState", null, contentValues)));
                } catch (SQLException unused2) {
                    LogUtil.e("HwNotificationContentProvider", "update ClockState table not exist error");
                }
            }
            return uri;
        }
        throw new IllegalArgumentException("This is a unKnow Uri" + uri.toString());
    }

    @Override // android.content.ContentProvider
    public int delete(Uri uri, String str, String[] strArr) {
        Exception e2;
        String str2;
        this.d = this.b.getWritableDatabase();
        int match = c.match(uri);
        String str3 = "";
        if (match == 1) {
            e(null, str, strArr);
            int delete = this.d.delete("NotificationList", str, strArr);
            if (strArr != null && strArr.length > 0) {
                str3 = strArr[0];
            }
            LogUtil.c("HwNotificationContentProvider", "TABLE_SUB_SWITCH_LIST delete pkgName:", str3);
            getContext().getContentResolver().notifyChange(uri, null);
            return delete;
        }
        if (match != 2) {
            return 0;
        }
        try {
            str2 = uri.getPathSegments().get(2);
        } catch (Exception e3) {
            e2 = e3;
        }
        try {
            LogUtil.c("HwNotificationContentProvider", "delete NotificationList/# name" + str2);
        } catch (Exception e4) {
            e2 = e4;
            str3 = str2;
            LogUtil.e("HwNotificationContentProvider", "delete NotificationList/# exception: ", LogAnonymous.b((Throwable) e2));
            str2 = str3;
            return this.d.delete("NotificationList", RecordAction.ACT_NAME_TAG + str2, strArr);
        }
        return this.d.delete("NotificationList", RecordAction.ACT_NAME_TAG + str2, strArr);
    }

    @Override // android.content.ContentProvider
    public int update(Uri uri, ContentValues contentValues, String str, String[] strArr) {
        boolean z;
        this.d = this.b.getWritableDatabase();
        int match = c.match(uri);
        if (match != 3) {
            if (match == 4) {
                try {
                    boolean containsKey = contentValues.containsKey("isNotify");
                    LogUtil.c("HwNotificationContentProvider", "TABLE_NOTIFICATION_FLAGS update: values= ", contentValues.toString(), " isHarmonyOS30SendNotifyExist:", Boolean.valueOf(containsKey));
                    if (containsKey) {
                        z = contentValues.getAsBoolean("isNotify").booleanValue();
                        LogUtil.c("HwNotificationContentProvider", "TABLE_NOTIFICATION_FLAGS update: isHarmonyOS30SendNotify= ", Boolean.valueOf(z));
                        contentValues.remove("isNotify");
                    } else {
                        z = false;
                    }
                    LogUtil.c("HwNotificationContentProvider", "TABLE_NOTIFICATION_FLAGS update: ContentValues= ", contentValues.toString());
                    this.d.update("NotificationFlags", contentValues, str, strArr);
                    if (z) {
                        e(strArr);
                    }
                    bIS_(uri, strArr);
                } catch (SQLException e2) {
                    LogUtil.e("HwNotificationContentProvider", "TABLE_NOTIFICATION_FLAGS update exception: ", LogAnonymous.b((Throwable) e2));
                }
            } else if (match == 6) {
                if (!c("ConnectDeviceInfo", "isSupportNotify")) {
                    try {
                        this.d.execSQL(" DROP TABLE IF EXISTS ConnectDeviceInfo");
                        this.d.execSQL(" CREATE TABLE IF NOT EXISTS ConnectDeviceInfo ( serialIndex INTEGER  PRIMARY KEY  , DeviceType INTEGER  , isSupportNotify INTEGER  ) ");
                        this.d.execSQL("insert into ConnectDeviceInfo (serialIndex,DeviceType,isSupportNotify) select 1,-3,0 where not exists (select serialIndex,DeviceType,isSupportNotify from ConnectDeviceInfo where serialIndex=1)");
                        LogUtil.c("HwNotificationContentProvider", "update ConnectDeviceInfo table IS_NOT_EXIST but reCreated");
                    } catch (SQLException | StaleDataException | IllegalArgumentException e3) {
                        LogUtil.e("HwNotificationContentProvider", "update ConnectDeviceInfo table IS_NOT_EXIST SQLException: ", LogAnonymous.b(e3));
                    }
                }
                try {
                    int update = this.d.update("ConnectDeviceInfo", contentValues, str, strArr);
                    LogUtil.c("HwNotificationContentProvider", "TABLE_CONNECT_DEVICE_INFO update: values= ", contentValues.toString());
                    getContext().getContentResolver().notifyChange(uri, null);
                    LogUtil.c("HwNotificationContentProvider", "TABLE_CONNECT_DEVICE_INFO update notifyChange!");
                    return update;
                } catch (SQLException e4) {
                    LogUtil.e("HwNotificationContentProvider", "SqlUtil.TABLE_CONNECT_DEVICE_INFO update exception: ", LogAnonymous.b((Throwable) e4));
                }
            } else if (match == 7) {
                if (d()) {
                    LogUtil.c("HwNotificationContentProvider", "update ClockState table exist");
                    try {
                        return this.d.update("ClockState", contentValues, str, strArr);
                    } catch (SQLException unused) {
                        LogUtil.e("HwNotificationContentProvider", "update ClockState table exist update error");
                    }
                } else {
                    LogUtil.c("HwNotificationContentProvider", "update ClockState table not exist");
                    try {
                        this.d.execSQL("CREATE TABLE IF NOT EXISTS ClockState( deviceMac TEXT not null PRIMARY KEY,clockValue TEXT)");
                        this.d.insert("ClockState", null, contentValues);
                        return this.d.update("ClockState", contentValues, str, strArr);
                    } catch (SQLException unused2) {
                        LogUtil.e("HwNotificationContentProvider", "update ClockState table not exist error");
                    }
                }
            }
        } else if (c("MidwareAuthority", "value")) {
            LogUtil.c("HwNotificationContentProvider", "TABLE_MIDWARE update:TABLE_MIDWARE exist!");
            try {
                return this.d.update("MidwareAuthority", contentValues, str, strArr);
            } catch (SQLException e5) {
                LogUtil.e("HwNotificationContentProvider", "TABLE_MIDWARE update exception: ", LogAnonymous.b((Throwable) e5));
            }
        } else {
            LogUtil.c("HwNotificationContentProvider", "TABLE_MIDWARE update:TABLE_MIDWARE not exist");
            try {
                this.d.execSQL(" CREATE TABLE IF NOT EXISTS MidwareAuthority ( value NVARCHAR(300)  NOT NULL  PRIMARY KEY  ) ");
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("value", "false");
                this.d.insert("MidwareAuthority", "value", contentValues2);
                return this.d.update("MidwareAuthority", contentValues, str, strArr);
            } catch (SQLException e6) {
                LogUtil.e("HwNotificationContentProvider", "TABLE_MIDWARE update exception: ", LogAnonymous.b((Throwable) e6));
            }
        }
        return 0;
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x0036, code lost:
    
        if (r3 == null) goto L19;
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x004a, code lost:
    
        return r4;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean d() {
        /*
            r7 = this;
            java.lang.String r0 = "isClockStateTableExist enter"
            java.lang.Object[] r0 = new java.lang.Object[]{r0}
            java.lang.String r1 = "HwNotificationContentProvider"
            health.compact.a.LogUtil.c(r1, r0)
            r0 = 1
            r2 = 0
            r3 = 0
            android.database.sqlite.SQLiteDatabase r4 = r7.d     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3b
            java.lang.String r5 = "select count(*) as tableCount from sqlite_master where type ='table' and name = 'ClockState'"
            android.database.Cursor r3 = r4.rawQuery(r5, r3)     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3b
            boolean r4 = r3.moveToNext()     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3b
            if (r4 == 0) goto L25
            int r4 = r3.getInt(r2)     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3b
            if (r4 <= 0) goto L25
            r4 = r0
            goto L26
        L25:
            r4 = r2
        L26:
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3c
            java.lang.String r6 = "isClockStateTableExist result:"
            r5[r2] = r6     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3c
            java.lang.Boolean r6 = java.lang.Boolean.valueOf(r4)     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3c
            r5[r0] = r6     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3c
            health.compact.a.LogUtil.c(r1, r5)     // Catch: java.lang.Throwable -> L39 java.lang.Exception -> L3c
            if (r3 == 0) goto L4a
            goto L47
        L39:
            r0 = move-exception
            goto L4b
        L3b:
            r4 = r2
        L3c:
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> L39
            java.lang.String r5 = "isClockStateTableExist error"
            r0[r2] = r5     // Catch: java.lang.Throwable -> L39
            health.compact.a.LogUtil.e(r1, r0)     // Catch: java.lang.Throwable -> L39
            if (r3 == 0) goto L4a
        L47:
            r3.close()
        L4a:
            return r4
        L4b:
            if (r3 == 0) goto L50
            r3.close()
        L50:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hwdevice.outofprocess.provider.HwNotificationContentProvider.d():boolean");
    }

    private void bIS_(Uri uri, String[] strArr) {
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        String str = strArr[0];
        LogUtil.c("HwNotificationContentProvider", "TABLE_NOTIFICATION_FLAGS notifyAuthorizedChange selectionArg: ", str, " ", uri.getPath());
        if ("authorized".equals(str)) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
    }

    private void e(String[] strArr) {
        if (strArr == null || strArr.length <= 0) {
            return;
        }
        String str = strArr[0];
        LogUtil.c("HwNotificationContentProvider", "TABLE_NOTIFICATION_FLAGS notifyNotificationCollaborationChange selectionArg: ", str);
        if ("authorized".equals(str)) {
            getContext().getContentResolver().notifyChange(Uri.parse(jdz.c), null);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:13:0x0064, code lost:
    
        return r8;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0061, code lost:
    
        if (r4 == null) goto L22;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private boolean c(java.lang.String r8, java.lang.String r9) {
        /*
            r7 = this;
            java.lang.String r0 = "Enter isTableExist,tableName: "
            java.lang.String r1 = ",columnName: "
            java.lang.Object[] r0 = new java.lang.Object[]{r0, r8, r1, r9}
            java.lang.String r1 = "HwNotificationContentProvider"
            health.compact.a.LogUtil.c(r1, r0)
            r0 = 2
            r2 = 1
            r3 = 0
            r4 = 0
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L4f
            java.lang.String r6 = "SELECT * FROM "
            r5.<init>(r6)     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L4f
            r5.append(r8)     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L4f
            java.lang.String r8 = " LIMIT 0"
            r5.append(r8)     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L4f
            java.lang.String r8 = r5.toString()     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L4f
            android.database.sqlite.SQLiteDatabase r5 = r7.d     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L4f
            android.database.Cursor r4 = r5.rawQuery(r8, r4)     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L4f
            if (r4 == 0) goto L35
            int r8 = r4.getColumnIndex(r9)     // Catch: java.lang.Throwable -> L4d java.lang.Exception -> L4f
            r9 = -1
            if (r8 == r9) goto L35
            r8 = r2
            goto L36
        L35:
            r8 = r3
        L36:
            java.lang.Object[] r9 = new java.lang.Object[r0]     // Catch: java.lang.Exception -> L4b java.lang.Throwable -> L4d
            java.lang.String r5 = "isTableExist, result: "
            r9[r3] = r5     // Catch: java.lang.Exception -> L4b java.lang.Throwable -> L4d
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r8)     // Catch: java.lang.Exception -> L4b java.lang.Throwable -> L4d
            r9[r2] = r5     // Catch: java.lang.Exception -> L4b java.lang.Throwable -> L4d
            health.compact.a.LogUtil.c(r1, r9)     // Catch: java.lang.Exception -> L4b java.lang.Throwable -> L4d
            if (r4 == 0) goto L64
        L47:
            r4.close()
            goto L64
        L4b:
            r9 = move-exception
            goto L52
        L4d:
            r8 = move-exception
            goto L65
        L4f:
            r8 = move-exception
            r9 = r8
            r8 = r3
        L52:
            java.lang.Object[] r0 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> L4d
            java.lang.String r5 = "isTableExist exception: "
            r0[r3] = r5     // Catch: java.lang.Throwable -> L4d
            java.lang.String r9 = health.compact.a.LogAnonymous.b(r9)     // Catch: java.lang.Throwable -> L4d
            r0[r2] = r9     // Catch: java.lang.Throwable -> L4d
            health.compact.a.LogUtil.e(r1, r0)     // Catch: java.lang.Throwable -> L4d
            if (r4 == 0) goto L64
            goto L47
        L64:
            return r8
        L65:
            if (r4 == 0) goto L6a
            r4.close()
        L6a:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hwdevice.outofprocess.provider.HwNotificationContentProvider.c(java.lang.String, java.lang.String):boolean");
    }

    private void e(String[] strArr, String str, String[] strArr2) {
        if (CommonUtil.bv()) {
            return;
        }
        if (str == null && strArr2 == null) {
            LogUtil.d("HwNotificationContentProvider", "printSelectionLog selection or selectionArgs is null");
            return;
        }
        StringBuilder sb = new StringBuilder(0);
        sb.append("selection:");
        sb.append(str);
        if (strArr2 != null) {
            for (String str2 : strArr2) {
                sb.append(" selectionArgs: ");
                sb.append(str2);
            }
        }
        if (strArr != null) {
            for (String str3 : strArr) {
                sb.append(" projections: ");
                sb.append(str3);
            }
        }
        LogUtil.c("HwNotificationContentProvider", "printQueryCheckLog: ", sb.toString());
    }

    /* JADX WARN: Code restructure failed: missing block: B:30:0x007c, code lost:
    
        if (r11 == null) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:31:0x007e, code lost:
    
        r11.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:?, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void c(java.lang.String[] r11, java.lang.String r12, java.lang.String[] r13, java.lang.String r14) {
        /*
            r10 = this;
            r10.e(r11, r12, r13)
            boolean r0 = health.compact.a.CommonUtil.bv()
            if (r0 == 0) goto La
            return
        La:
            java.lang.String r0 = "HwNotificationContentProvider"
            if (r13 == 0) goto Lb2
            int r1 = r13.length
            if (r1 != 0) goto L13
            goto Lb2
        L13:
            android.database.sqlite.SQLiteDatabase r2 = r10.d     // Catch: android.database.StaleDataException -> L9e java.lang.IllegalArgumentException -> La0 android.database.sqlite.SQLiteException -> La2
            java.lang.String r3 = "NotificationList"
            r7 = 0
            r8 = 0
            r4 = r11
            r5 = r12
            r6 = r13
            r9 = r14
            android.database.Cursor r11 = r2.query(r3, r4, r5, r6, r7, r8, r9)     // Catch: android.database.StaleDataException -> L9e java.lang.IllegalArgumentException -> La0 android.database.sqlite.SQLiteException -> La2
            r12 = 1
            r14 = 0
            if (r11 == 0) goto L82
            int r1 = r11.getCount()     // Catch: java.lang.Throwable -> L92
            if (r1 != 0) goto L2c
            goto L82
        L2c:
            boolean r1 = r11.moveToNext()     // Catch: java.lang.Throwable -> L92
            if (r1 == 0) goto L7c
            java.lang.String r1 = "name"
            java.lang.Integer r1 = defpackage.jdz.bGi_(r1, r11, r0)     // Catch: java.lang.Throwable -> L92
            if (r1 != 0) goto L4b
            java.lang.Object[] r12 = new java.lang.Object[r12]     // Catch: java.lang.Throwable -> L92
            java.lang.String r13 = "queryTableSubSwitchListCheck columnIndex is null"
            r12[r14] = r13     // Catch: java.lang.Throwable -> L92
            health.compact.a.LogUtil.a(r0, r12)     // Catch: java.lang.Throwable -> L92
            if (r11 == 0) goto L4a
            r11.close()     // Catch: android.database.StaleDataException -> L9e java.lang.IllegalArgumentException -> La0 android.database.sqlite.SQLiteException -> La2
        L4a:
            return
        L4b:
            int r1 = r1.intValue()     // Catch: java.lang.Throwable -> L92
            java.lang.String r1 = r11.getString(r1)     // Catch: java.lang.Throwable -> L92
            r2 = 4
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch: java.lang.Throwable -> L92
            java.lang.String r3 = "queryTableSubSwitchListCheck name: "
            r2[r14] = r3     // Catch: java.lang.Throwable -> L92
            r2[r12] = r1     // Catch: java.lang.Throwable -> L92
            java.lang.String r3 = " selectionArgs: "
            r4 = 2
            r2[r4] = r3     // Catch: java.lang.Throwable -> L92
            r3 = r13[r14]     // Catch: java.lang.Throwable -> L92
            r4 = 3
            r2[r4] = r3     // Catch: java.lang.Throwable -> L92
            health.compact.a.LogUtil.c(r0, r2)     // Catch: java.lang.Throwable -> L92
            r2 = r13[r14]     // Catch: java.lang.Throwable -> L92
            boolean r1 = android.text.TextUtils.equals(r2, r1)     // Catch: java.lang.Throwable -> L92
            if (r1 == 0) goto L2c
            java.lang.Object[] r12 = new java.lang.Object[r12]     // Catch: java.lang.Throwable -> L92
            java.lang.String r13 = "queryTableSubSwitchListCheck name is found"
            r12[r14] = r13     // Catch: java.lang.Throwable -> L92
            health.compact.a.LogUtil.c(r0, r12)     // Catch: java.lang.Throwable -> L92
        L7c:
            if (r11 == 0) goto Lb1
            r11.close()     // Catch: android.database.StaleDataException -> L9e java.lang.IllegalArgumentException -> La0 android.database.sqlite.SQLiteException -> La2
            goto Lb1
        L82:
            java.lang.Object[] r12 = new java.lang.Object[r12]     // Catch: java.lang.Throwable -> L92
            java.lang.String r13 = "queryTableSubSwitchListCheck cursor is null"
            r12[r14] = r13     // Catch: java.lang.Throwable -> L92
            health.compact.a.LogUtil.a(r0, r12)     // Catch: java.lang.Throwable -> L92
            if (r11 == 0) goto L91
            r11.close()     // Catch: android.database.StaleDataException -> L9e java.lang.IllegalArgumentException -> La0 android.database.sqlite.SQLiteException -> La2
        L91:
            return
        L92:
            r12 = move-exception
            if (r11 == 0) goto L9d
            r11.close()     // Catch: java.lang.Throwable -> L99
            goto L9d
        L99:
            r11 = move-exception
            r12.addSuppressed(r11)     // Catch: android.database.StaleDataException -> L9e java.lang.IllegalArgumentException -> La0 android.database.sqlite.SQLiteException -> La2
        L9d:
            throw r12     // Catch: android.database.StaleDataException -> L9e java.lang.IllegalArgumentException -> La0 android.database.sqlite.SQLiteException -> La2
        L9e:
            r11 = move-exception
            goto La3
        La0:
            r11 = move-exception
            goto La3
        La2:
            r11 = move-exception
        La3:
            java.lang.String r12 = "queryTableSubSwitchListCheck Exception: "
            java.lang.String r11 = health.compact.a.LogAnonymous.b(r11)
            java.lang.Object[] r11 = new java.lang.Object[]{r12, r11}
            health.compact.a.LogUtil.e(r0, r11)
        Lb1:
            return
        Lb2:
            java.lang.String r11 = "queryTableSubSwitchListCheck selectionArgs is null or length is 0."
            java.lang.Object[] r11 = new java.lang.Object[]{r11}
            health.compact.a.LogUtil.d(r0, r11)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hwdevice.outofprocess.provider.HwNotificationContentProvider.c(java.lang.String[], java.lang.String, java.lang.String[], java.lang.String):void");
    }

    static class e extends SQLiteOpenHelper {
        e(Context context) {
            super(context, "notification.db", (SQLiteDatabase.CursorFactory) null, 4);
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            try {
                sQLiteDatabase.execSQL(" CREATE TABLE IF NOT EXISTS NotificationList ( name NVARCHAR(300)  NOT NULL  PRIMARY KEY  ) ");
                ContentValues contentValues = new ContentValues();
                contentValues.put("name", "start");
                sQLiteDatabase.insert("NotificationList", "name", contentValues);
                sQLiteDatabase.execSQL(" CREATE TABLE IF NOT EXISTS MidwareAuthority ( value NVARCHAR(300)  NOT NULL  PRIMARY KEY  ) ");
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("value", "false");
                sQLiteDatabase.insert("MidwareAuthority", "value", contentValues2);
                sQLiteDatabase.execSQL(" CREATE TABLE IF NOT EXISTS NotificationFlags ( name TEXT  NOT NULL  PRIMARY KEY  , value INTEGER  ) ");
                sQLiteDatabase.execSQL("insert into NotificationFlags (name,value) select 'authorized',-1 where not exists (select name,value from NotificationFlags where name='authorized')");
                sQLiteDatabase.execSQL("insert into NotificationFlags (name,value) select 'is_MessageAlert',0 where not exists (select name,value from NotificationFlags where name='is_MessageAlert')");
                sQLiteDatabase.execSQL("insert into NotificationFlags (name,value) select 'is_Forbidden',-1 where not exists (select name,value from NotificationFlags where name='is_Forbidden')");
                sQLiteDatabase.execSQL(" CREATE TABLE IF NOT EXISTS ConnectDeviceInfo ( serialIndex INTEGER  PRIMARY KEY  , DeviceType INTEGER  , isSupportNotify INTEGER  ) ");
                sQLiteDatabase.execSQL("insert into ConnectDeviceInfo (serialIndex,DeviceType,isSupportNotify) select 1,-3,0 where not exists (select serialIndex,DeviceType,isSupportNotify from ConnectDeviceInfo where serialIndex=1)");
                sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS ClockState( deviceMac TEXT not null PRIMARY KEY,clockValue TEXT)");
                LogUtil.c("HwNotificationContentProvider", "DataBaseHelperNo onCreate");
            } catch (SQLException e) {
                LogUtil.e("HwNotificationContentProvider", "DataBaseHelperNo onCreate SQLException ", LogAnonymous.b((Throwable) e));
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            LogUtil.c("HwNotificationContentProvider", "DataBaseHelperNo onUpgrade");
            LogUtil.c("HwNotificationContentProvider", "onUpgrade oldVersion=", Integer.valueOf(i), " newVersion=", Integer.valueOf(i2));
            if (i >= 4 || i2 != 4) {
                return;
            }
            try {
                sQLiteDatabase.execSQL(" CREATE TABLE IF NOT EXISTS NotificationFlags ( name TEXT  NOT NULL  PRIMARY KEY  , value INTEGER  ) ");
                sQLiteDatabase.execSQL("insert into NotificationFlags (name,value) select 'authorized',-1 where not exists (select name,value from NotificationFlags where name='authorized')");
                sQLiteDatabase.execSQL("insert into NotificationFlags (name,value) select 'is_MessageAlert',0 where not exists (select name,value from NotificationFlags where name='is_MessageAlert')");
                sQLiteDatabase.execSQL("insert into NotificationFlags (name,value) select 'is_Forbidden',-1 where not exists (select name,value from NotificationFlags where name='is_Forbidden')");
                sQLiteDatabase.execSQL(" CREATE TABLE IF NOT EXISTS ConnectDeviceInfo ( serialIndex INTEGER  PRIMARY KEY  , DeviceType INTEGER  , isSupportNotify INTEGER  ) ");
                sQLiteDatabase.execSQL("insert into ConnectDeviceInfo (serialIndex,DeviceType,isSupportNotify) select 1,-3,0 where not exists (select serialIndex,DeviceType,isSupportNotify from ConnectDeviceInfo where serialIndex=1)");
                LogUtil.c("HwNotificationContentProvider", "DataBaseHelperNo onUpgrade success");
            } catch (SQLException e) {
                LogUtil.e("HwNotificationContentProvider", "DataBaseHelperNo onUpgrade SQLException", LogAnonymous.b((Throwable) e));
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            LogUtil.a("HwNotificationContentProvider", "HwNotificationContentProvider onDowngrade: nowVersion = ", Integer.valueOf(i2), ",lastVersion = ", Integer.valueOf(i));
        }
    }
}
