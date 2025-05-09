package defpackage;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsmartinteractmgr.data.SmartMsgConstant;
import com.huawei.hwsmartinteractmgr.data.SmartMsgDbObject;
import com.huawei.login.ui.login.LoginInit;
import health.compact.a.DbManager;

/* loaded from: classes.dex */
public class kvs extends HwBaseManager {
    private static ContentResolver c;
    private static volatile kvs e;
    private Context b;
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static final Uri f14635a = Uri.parse(DbManager.e + "module_20009_smart_msg");

    private boolean b(char c2) {
        return c2 == '0' || c2 == '1';
    }

    private kvs(Context context) {
        super(context);
        this.b = context.getApplicationContext();
        synchronized (d) {
            c = this.b.getContentResolver();
        }
        jdx.b(new Runnable() { // from class: kvs.5
            @Override // java.lang.Runnable
            public void run() {
                kvs.this.e();
            }
        });
    }

    public static kvs b(Context context) {
        if (e == null) {
            synchronized (d) {
                if (e == null) {
                    if (context == null) {
                        e = new kvs(BaseApplication.getContext());
                    } else {
                        e = new kvs(context);
                    }
                }
            }
        }
        return e;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        LogUtil.a("SmartCenterDbManager", "createTable | create new table: ", getTableFullName(SmartMsgConstant.TABLE_NAME));
        createStorageDataTable(SmartMsgConstant.TABLE_NAME, 2, "id integer primary key autoincrement,msgType integer not null check(msgType >= 10000),msgSrc integer not null check(msgSrc > 0),msgContentType integer not null check(msgContentType > 0),msgContent text not null,showMethod text,createTime integer not null,updateTime integer,expireTime integer,status integer not null check(status > 0),huid text,showTime text,priority integer not null,showCount integer default 0");
    }

    public boolean a(SmartMsgDbObject smartMsgDbObject) {
        synchronized (d) {
            LogUtil.a("SmartCenterDbManager", "Enter insert | Message: ");
            if (e(smartMsgDbObject) != 0) {
                return false;
            }
            smartMsgDbObject.setHuid(LoginInit.getInstance(this.b).getAccountInfo(1011));
            if (insertStorageData(SmartMsgConstant.TABLE_NAME, 2, bSk_(smartMsgDbObject)) == 0) {
                LogUtil.a("SmartCenterDbManager", "insert | insert a new message success");
                return true;
            }
            LogUtil.a("SmartCenterDbManager", "insert | insert a new message failed");
            return false;
        }
    }

    public boolean e(int i, SmartMsgDbObject smartMsgDbObject) {
        synchronized (d) {
            LogUtil.a("SmartCenterDbManager", "Enter deleteMessageById");
            if (smartMsgDbObject == null) {
                return false;
            }
            ContentValues bSk_ = bSk_(smartMsgDbObject);
            bSk_.remove("createTime");
            bSk_.remove("huid");
            if (updateStorageData(SmartMsgConstant.TABLE_NAME, 2, bSk_, "id = '" + i + "'") == 0) {
                LogUtil.a("SmartCenterDbManager", "insert | update content of message success");
                return true;
            }
            LogUtil.a("SmartCenterDbManager", "insert | update content of message failed");
            return false;
        }
    }

    public boolean bSl_(int i, ContentValues contentValues) {
        synchronized (d) {
            LogUtil.a("SmartCenterDbManager", "Enter deleteMessageById");
            if (contentValues == null) {
                return false;
            }
            contentValues.put("updateTime", Long.valueOf(System.currentTimeMillis()));
            if (updateStorageData(SmartMsgConstant.TABLE_NAME, 2, contentValues, "id = '" + i + "'") == 0) {
                LogUtil.a("SmartCenterDbManager", "insert | update content of message success");
                return true;
            }
            LogUtil.a("SmartCenterDbManager", "insert | update content of message failed");
            return false;
        }
    }

    public int d(int i) {
        int deleteStorageData;
        synchronized (d) {
            LogUtil.a("SmartCenterDbManager", "Enter deleteMessageById");
            deleteStorageData = deleteStorageData(SmartMsgConstant.TABLE_NAME, 2, "msgType = '" + i + "'");
            if (deleteStorageData == 0) {
                LogUtil.a("SmartCenterDbManager", "deleteMessageById success");
            }
        }
        return deleteStorageData;
    }

    /* JADX WARN: Code restructure failed: missing block: B:25:0x0048, code lost:
    
        if (r4 != null) goto L24;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x005e, code lost:
    
        return r1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x005a, code lost:
    
        r4.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0058, code lost:
    
        if (r4 == null) goto L25;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public java.util.List<com.huawei.hwsmartinteractmgr.data.SmartMsgDbObject> c(java.lang.String r12, int r13) {
        /*
            r11 = this;
            java.lang.Object r0 = defpackage.kvs.d
            monitor-enter(r0)
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: java.lang.Throwable -> L65
            r2 = 10
            r1.<init>(r2)     // Catch: java.lang.Throwable -> L65
            r2 = 0
            r3 = 1
            if (r12 != 0) goto L1b
            java.lang.Object[] r12 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L65
            java.lang.String r13 = "huid = null"
            r12[r2] = r13     // Catch: java.lang.Throwable -> L65
            java.lang.String r13 = "SmartCenterDbManager"
            com.huawei.hwlogsmodel.LogUtil.b(r13, r12)     // Catch: java.lang.Throwable -> L65
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L65
            return r1
        L1b:
            r4 = 0
            android.content.ContentResolver r5 = defpackage.kvs.c     // Catch: java.lang.Throwable -> L4b android.database.SQLException -> L4d
            android.net.Uri r6 = defpackage.kvs.f14635a     // Catch: java.lang.Throwable -> L4b android.database.SQLException -> L4d
            java.lang.String[] r9 = new java.lang.String[]{r12}     // Catch: java.lang.Throwable -> L4b android.database.SQLException -> L4d
            r7 = 0
            java.lang.String r8 = "huid=?"
            r10 = 0
            android.database.Cursor r4 = r5.query(r6, r7, r8, r9, r10)     // Catch: java.lang.Throwable -> L4b android.database.SQLException -> L4d
            if (r4 == 0) goto L48
        L2e:
            boolean r12 = r4.moveToNext()     // Catch: java.lang.Throwable -> L4b android.database.SQLException -> L4d
            if (r12 == 0) goto L48
            com.huawei.hwsmartinteractmgr.data.SmartMsgDbObject r12 = r11.bSj_(r4)     // Catch: java.lang.Throwable -> L4b android.database.SQLException -> L4d
            java.lang.String r5 = r12.getShowMethod()     // Catch: java.lang.Throwable -> L4b android.database.SQLException -> L4d
            char r5 = r5.charAt(r13)     // Catch: java.lang.Throwable -> L4b android.database.SQLException -> L4d
            r6 = 49
            if (r5 != r6) goto L2e
            r1.add(r12)     // Catch: java.lang.Throwable -> L4b android.database.SQLException -> L4d
            goto L2e
        L48:
            if (r4 == 0) goto L5d
            goto L5a
        L4b:
            r12 = move-exception
            goto L5f
        L4d:
            java.lang.Object[] r12 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L4b
            java.lang.String r13 = "getMessageByMode queryStorage error"
            r12[r2] = r13     // Catch: java.lang.Throwable -> L4b
            java.lang.String r13 = "SmartCenterDbManager"
            com.huawei.hwlogsmodel.LogUtil.b(r13, r12)     // Catch: java.lang.Throwable -> L4b
            if (r4 == 0) goto L5d
        L5a:
            r4.close()     // Catch: java.lang.Throwable -> L65
        L5d:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L65
            return r1
        L5f:
            if (r4 == 0) goto L64
            r4.close()     // Catch: java.lang.Throwable -> L65
        L64:
            throw r12     // Catch: java.lang.Throwable -> L65
        L65:
            r12 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L65
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kvs.c(java.lang.String, int):java.util.List");
    }

    private int e(SmartMsgDbObject smartMsgDbObject) {
        if (smartMsgDbObject == null) {
            LogUtil.b("SmartCenterDbManager", "messageObject null");
            return -1;
        }
        if (smartMsgDbObject.getShowTime() == null) {
            return -1;
        }
        if (smartMsgDbObject.getShowTime().length() % 8 != 0) {
            LogUtil.b("SmartCenterDbManager", "getShowTime error");
            return -1;
        }
        String showMethod = smartMsgDbObject.getShowMethod();
        if (showMethod == null || showMethod.length() != 6) {
            LogUtil.b("SmartCenterDbManager", "getShowMethod error");
            return -1;
        }
        if (!b(showMethod.charAt(0))) {
            LogUtil.b("SmartCenterDbManager", "getShowMethod().charAt(0) error");
            return -1;
        }
        if (!b(showMethod.charAt(1))) {
            LogUtil.b("SmartCenterDbManager", "getShowMethod().charAt(1) error");
            return -1;
        }
        if (b(showMethod.charAt(2))) {
            return 0;
        }
        LogUtil.b("SmartCenterDbManager", "getShowMethod().charAt(2) error");
        return -1;
    }

    private SmartMsgDbObject bSj_(Cursor cursor) {
        SmartMsgDbObject smartMsgDbObject = new SmartMsgDbObject();
        smartMsgDbObject.setId(cursor.getInt(cursor.getColumnIndex("id")));
        smartMsgDbObject.setMsgType(cursor.getInt(cursor.getColumnIndex("msgType")));
        smartMsgDbObject.setMsgSrc(cursor.getInt(cursor.getColumnIndex(SmartMsgConstant.MSG_SRC)));
        smartMsgDbObject.setMsgContentType(cursor.getInt(cursor.getColumnIndex(SmartMsgConstant.MSG_CONTENT_TYPE)));
        smartMsgDbObject.setMsgContent(cursor.getString(cursor.getColumnIndex("msgContent")));
        smartMsgDbObject.setShowMethod(cursor.getString(cursor.getColumnIndex(SmartMsgConstant.MSG_SHOW_METHOD)));
        smartMsgDbObject.setCreateTime(cursor.getLong(cursor.getColumnIndex("createTime")));
        smartMsgDbObject.setUpdateTime(cursor.getLong(cursor.getColumnIndex("updateTime")));
        smartMsgDbObject.setExpireTime(cursor.getLong(cursor.getColumnIndex("expireTime")));
        smartMsgDbObject.setStatus(cursor.getInt(cursor.getColumnIndex("status")));
        smartMsgDbObject.setHuid(cursor.getString(cursor.getColumnIndex("huid")));
        smartMsgDbObject.setShowTime(cursor.getString(cursor.getColumnIndex(SmartMsgConstant.MSG_SHOW_TIME)));
        smartMsgDbObject.setMessagePriority(cursor.getInt(cursor.getColumnIndex("priority")));
        smartMsgDbObject.setShowCount(cursor.getInt(cursor.getColumnIndex(SmartMsgConstant.MSG_SHOW_COUNT)));
        return smartMsgDbObject;
    }

    private ContentValues bSk_(SmartMsgDbObject smartMsgDbObject) {
        ContentValues contentValues = new ContentValues();
        if (smartMsgDbObject == null) {
            return contentValues;
        }
        contentValues.put("msgType", Integer.valueOf(smartMsgDbObject.getMsgType()));
        contentValues.put(SmartMsgConstant.MSG_SRC, Integer.valueOf(smartMsgDbObject.getMsgSrc()));
        contentValues.put(SmartMsgConstant.MSG_CONTENT_TYPE, Integer.valueOf(smartMsgDbObject.getMsgContentType()));
        contentValues.put("msgContent", smartMsgDbObject.getMsgContent());
        contentValues.put(SmartMsgConstant.MSG_SHOW_METHOD, smartMsgDbObject.getShowMethod());
        contentValues.put("createTime", Long.valueOf(System.currentTimeMillis()));
        if (smartMsgDbObject.getUpdateTime() > 0) {
            contentValues.put("updateTime", Long.valueOf(smartMsgDbObject.getUpdateTime()));
        } else {
            contentValues.put("updateTime", Long.valueOf(System.currentTimeMillis()));
        }
        contentValues.put("expireTime", Long.valueOf(smartMsgDbObject.getExpireTime()));
        contentValues.put("status", Integer.valueOf(smartMsgDbObject.getStatus()));
        contentValues.put("huid", smartMsgDbObject.getHuid());
        contentValues.put(SmartMsgConstant.MSG_SHOW_TIME, smartMsgDbObject.getShowTime());
        contentValues.put("priority", Integer.valueOf(smartMsgDbObject.getMessagePriority()));
        contentValues.put(SmartMsgConstant.MSG_SHOW_COUNT, Integer.valueOf(smartMsgDbObject.getShowCount()));
        return contentValues;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 20009;
    }

    /* JADX WARN: Code restructure failed: missing block: B:14:0x0049, code lost:
    
        if (r13 != null) goto L34;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0080, code lost:
    
        if (r13 == null) goto L35;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:39:0x008c A[Catch: all -> 0x0090, TryCatch #0 {, blocks: (B:4:0x0003, B:6:0x0014, B:7:0x001f, B:15:0x0085, B:17:0x0082, B:39:0x008c, B:40:0x008f), top: B:3:0x0003 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public com.huawei.hwsmartinteractmgr.data.SmartMsgDbObject a(int r13) {
        /*
            r12 = this;
            java.lang.Object r0 = defpackage.kvs.d
            monitor-enter(r0)
            android.content.Context r1 = r12.b     // Catch: java.lang.Throwable -> L90
            com.huawei.login.ui.login.LoginInit r1 = com.huawei.login.ui.login.LoginInit.getInstance(r1)     // Catch: java.lang.Throwable -> L90
            r2 = 1011(0x3f3, float:1.417E-42)
            java.lang.String r1 = r1.getAccountInfo(r2)     // Catch: java.lang.Throwable -> L90
            r2 = 0
            r3 = 1
            r4 = 0
            if (r1 != 0) goto L21
            java.lang.Object[] r13 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L90
            java.lang.String r1 = "getMessageByType huid is null"
            r13[r2] = r1     // Catch: java.lang.Throwable -> L90
            java.lang.String r1 = "SmartCenterDbManager"
            com.huawei.hwlogsmodel.LogUtil.a(r1, r13)     // Catch: java.lang.Throwable -> L90
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L90
            return r4
        L21:
            r5 = 2
            android.content.ContentResolver r6 = defpackage.kvs.c     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e java.lang.IllegalArgumentException -> L5d android.database.SQLException -> L74
            android.net.Uri r7 = defpackage.kvs.f14635a     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e java.lang.IllegalArgumentException -> L5d android.database.SQLException -> L74
            java.lang.String[] r10 = new java.lang.String[r5]     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e java.lang.IllegalArgumentException -> L5d android.database.SQLException -> L74
            java.lang.String r13 = java.lang.String.valueOf(r13)     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e java.lang.IllegalArgumentException -> L5d android.database.SQLException -> L74
            r10[r2] = r13     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e java.lang.IllegalArgumentException -> L5d android.database.SQLException -> L74
            r10[r3] = r1     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e java.lang.IllegalArgumentException -> L5d android.database.SQLException -> L74
            r8 = 0
            java.lang.String r9 = "msgType=? and huid=?"
            r11 = 0
            android.database.Cursor r13 = r6.query(r7, r8, r9, r10, r11)     // Catch: java.lang.Throwable -> L4c java.lang.Exception -> L4e java.lang.IllegalArgumentException -> L5d android.database.SQLException -> L74
            if (r13 == 0) goto L49
            boolean r1 = r13.moveToFirst()     // Catch: java.lang.IllegalArgumentException -> L47 java.lang.Exception -> L4f android.database.SQLException -> L75 java.lang.Throwable -> L87
            if (r1 == 0) goto L49
            com.huawei.hwsmartinteractmgr.data.SmartMsgDbObject r1 = r12.bSj_(r13)     // Catch: java.lang.IllegalArgumentException -> L47 java.lang.Exception -> L4f android.database.SQLException -> L75 java.lang.Throwable -> L87
            r4 = r1
            goto L49
        L47:
            r1 = move-exception
            goto L60
        L49:
            if (r13 == 0) goto L85
            goto L82
        L4c:
            r13 = move-exception
            goto L8a
        L4e:
            r13 = r4
        L4f:
            java.lang.Object[] r1 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L87
            java.lang.String r3 = "getMessageByType Exception."
            r1[r2] = r3     // Catch: java.lang.Throwable -> L87
            java.lang.String r2 = "SmartCenterDbManager"
            com.huawei.hwlogsmodel.LogUtil.b(r2, r1)     // Catch: java.lang.Throwable -> L87
            if (r13 == 0) goto L85
            goto L82
        L5d:
            r13 = move-exception
            r1 = r13
            r13 = r4
        L60:
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> L87
            java.lang.String r6 = "getMessageByType IllegalArgumentException "
            r5[r2] = r6     // Catch: java.lang.Throwable -> L87
            java.lang.String r1 = r1.getMessage()     // Catch: java.lang.Throwable -> L87
            r5[r3] = r1     // Catch: java.lang.Throwable -> L87
            java.lang.String r1 = "SmartCenterDbManager"
            com.huawei.hwlogsmodel.LogUtil.b(r1, r5)     // Catch: java.lang.Throwable -> L87
            if (r13 == 0) goto L85
            goto L82
        L74:
            r13 = r4
        L75:
            java.lang.Object[] r1 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L87
            java.lang.String r3 = "getMessageByType queryStorage error"
            r1[r2] = r3     // Catch: java.lang.Throwable -> L87
            java.lang.String r2 = "SmartCenterDbManager"
            com.huawei.hwlogsmodel.LogUtil.b(r2, r1)     // Catch: java.lang.Throwable -> L87
            if (r13 == 0) goto L85
        L82:
            r13.close()     // Catch: java.lang.Throwable -> L90
        L85:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L90
            return r4
        L87:
            r1 = move-exception
            r4 = r13
            r13 = r1
        L8a:
            if (r4 == 0) goto L8f
            r4.close()     // Catch: java.lang.Throwable -> L90
        L8f:
            throw r13     // Catch: java.lang.Throwable -> L90
        L90:
            r13 = move-exception
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L90
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.kvs.a(int):com.huawei.hwsmartinteractmgr.data.SmartMsgDbObject");
    }
}
