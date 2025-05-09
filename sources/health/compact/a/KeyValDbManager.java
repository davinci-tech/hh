package health.compact.a;

import android.content.Context;
import com.huawei.hiai.awareness.AwarenessConstants;
import com.huawei.hwbasemgr.HwBaseManager;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwdataaccessmodel.utils.StorageDataCallback;
import com.tencent.mmkv.MMKV;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class KeyValDbManager extends HwBaseManager {
    private static volatile KeyValDbManager e;
    private List<String> b;
    private String d;

    private KeyValDbManager(Context context) {
        super(context);
        this.d = "";
        this.b = new ArrayList();
        com.huawei.hwlogsmodel.LogUtil.a("KeyValDbManager", "Enter KeyValDbManager");
        b();
        this.b.add("key_wether_to_auth");
        this.b.add("user_id");
    }

    public static KeyValDbManager b(Context context) {
        if (e == null) {
            synchronized (KeyValDbManager.class) {
                if (e == null) {
                    if (context == null) {
                        e = new KeyValDbManager(BaseApplication.getContext());
                    } else {
                        e = new KeyValDbManager(context.getApplicationContext());
                    }
                }
            }
        }
        return e;
    }

    private void b() {
        WhiteBoxManager d = WhiteBoxManager.d();
        this.d = d.d(1, 34) + d.d(1, 1034) + d.d(1, 2034);
    }

    /* JADX WARN: Code restructure failed: missing block: B:18:0x0046, code lost:
    
        if (r0 == null) goto L17;
     */
    /* JADX WARN: Code restructure failed: missing block: B:5:0x0036, code lost:
    
        if (r0 != null) goto L16;
     */
    /* JADX WARN: Code restructure failed: missing block: B:6:0x004b, code lost:
    
        com.huawei.hwlogsmodel.LogUtil.c("KeyValDbManager", "getValue, key = ", r7, ", value = ", r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0056, code lost:
    
        return r3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0048, code lost:
    
        r0.close();
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:23:0x005b  */
    /* JADX WARN: Type inference failed for: r3v0 */
    /* JADX WARN: Type inference failed for: r3v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r3v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String d(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "key = '"
            r0.<init>(r1)
            r0.append(r7)
            java.lang.String r1 = "'"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "getValue, whereSql = "
            java.lang.Object[] r1 = new java.lang.Object[]{r1, r0}
            java.lang.String r2 = "KeyValDbManager"
            com.huawei.hwlogsmodel.LogUtil.c(r2, r1)
            r1 = 1
            r3 = 0
            java.lang.String r4 = "keyvaldb"
            r5 = 2
            android.database.Cursor r0 = r6.queryStorageData(r4, r5, r0)     // Catch: java.lang.Throwable -> L39 android.database.SQLException -> L3b
            if (r0 == 0) goto L36
            boolean r4 = r0.moveToFirst()     // Catch: android.database.SQLException -> L3c java.lang.Throwable -> L57
            if (r4 == 0) goto L36
            java.lang.String r1 = r0.getString(r1)     // Catch: android.database.SQLException -> L3c java.lang.Throwable -> L57
            r3 = r1
        L36:
            if (r0 == 0) goto L4b
            goto L48
        L39:
            r7 = move-exception
            goto L59
        L3b:
            r0 = r3
        L3c:
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L57
            java.lang.String r4 = "getValue, queryStorage error"
            r5 = 0
            r1[r5] = r4     // Catch: java.lang.Throwable -> L57
            com.huawei.hwlogsmodel.LogUtil.b(r2, r1)     // Catch: java.lang.Throwable -> L57
            if (r0 == 0) goto L4b
        L48:
            r0.close()
        L4b:
            java.lang.String r0 = "getValue, key = "
            java.lang.String r1 = ", value = "
            java.lang.Object[] r7 = new java.lang.Object[]{r0, r7, r1, r3}
            com.huawei.hwlogsmodel.LogUtil.c(r2, r7)
            return r3
        L57:
            r7 = move-exception
            r3 = r0
        L59:
            if (r3 == 0) goto L5e
            r3.close()
        L5e:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: health.compact.a.KeyValDbManager.d(java.lang.String):java.lang.String");
    }

    public String e(String str) {
        if (str == null) {
            com.huawei.hwlogsmodel.LogUtil.h("KeyValDbManager", "getValue, key is null");
            return null;
        }
        MMKV e2 = MmkvUtil.e("keyvaldb_unencrypt", null);
        String decodeString = e2.decodeString(str);
        if (decodeString == null) {
            decodeString = d(str);
            if (decodeString == null) {
                e2.encode(str, "empty_value");
            } else {
                e2.encode(str, decodeString);
            }
        }
        String str2 = "empty_value".equals(decodeString) ? null : decodeString;
        if (this.b.contains(str)) {
            com.huawei.hwlogsmodel.LogUtil.c("KeyValDbManager", "getValue, key = ", str, ", value = ", str2);
        }
        return str2;
    }

    @Deprecated
    public void d(String str, String str2, StorageDataCallback storageDataCallback) {
        StorageResult storageResult = new StorageResult();
        if (str == null || str2 == null) {
            com.huawei.hwlogsmodel.LogUtil.h("KeyValDbManager", "setValue, key or value is null so return PARAMS_ERROR.");
            storageResult.a(AwarenessConstants.ERROR_UNKNOWN_CODE);
        } else {
            com.huawei.hwlogsmodel.LogUtil.c("KeyValDbManager", "Enter setValue() with key=", str, ", value=", str2);
            boolean encode = MmkvUtil.e("keyvaldb_unencrypt", null).encode(str, str2);
            Object[] objArr = new Object[2];
            objArr[0] = encode ? "setValue success." : "setValue failed key =";
            objArr[1] = str;
            com.huawei.hwlogsmodel.LogUtil.a("KeyValDbManager", objArr);
            storageResult.a(encode ? 0 : -1);
        }
        if (this.b.contains(str)) {
            com.huawei.hwlogsmodel.LogUtil.a("KeyValDbManager", "setValue, key = ", str, ", value = ", str2);
        }
        if (storageDataCallback != null) {
            storageDataCallback.onProcessed(storageResult);
        }
    }

    public void e(String str, String str2) {
        if (str == null || str2 == null) {
            com.huawei.hwlogsmodel.LogUtil.h("KeyValDbManager", "setValue, key or value is null so return PARAMS_ERROR.");
            return;
        }
        com.huawei.hwlogsmodel.LogUtil.c("KeyValDbManager", "Enter setValue() with key=", str, ", value=", str2);
        Object[] objArr = new Object[2];
        objArr[0] = MmkvUtil.e("keyvaldb_unencrypt", null).encode(str, str2) ? "setValue success." : "setValue failed, key = ";
        objArr[1] = str;
        com.huawei.hwlogsmodel.LogUtil.a("KeyValDbManager", objArr);
    }

    @Deprecated
    public void e(String str, String str2, StorageParams storageParams, StorageDataCallback storageDataCallback) {
        StorageResult storageResult = new StorageResult();
        if (str == null || str2 == null) {
            com.huawei.hwlogsmodel.LogUtil.h("KeyValDbManager", "setEncryptValue, key or value is null so return PARAMS_ERROR.");
            storageResult.a(AwarenessConstants.ERROR_UNKNOWN_CODE);
        } else if (storageParams == null || storageParams.e() == 0) {
            com.huawei.hwlogsmodel.LogUtil.h("KeyValDbManager", "setEncryptValue, storageParams is not encryptDataType.");
            storageResult.a(AwarenessConstants.ERROR_UNKNOWN_CODE);
        } else {
            com.huawei.hwlogsmodel.LogUtil.c("KeyValDbManager", "Enter setEncryptValue() with key=", str, ", value=", str2, ", dataKey=", Integer.valueOf(storageParams.e()));
            boolean encode = MmkvUtil.e("keyvaldb_encrypt", this.d).encode(str, str2);
            Object[] objArr = new Object[2];
            objArr[0] = encode ? "setEncryptValue success." : "setValue failed key =";
            objArr[1] = str;
            com.huawei.hwlogsmodel.LogUtil.a("KeyValDbManager", objArr);
            storageResult.a(encode ? 0 : -1);
        }
        if (storageDataCallback != null) {
            storageDataCallback.onProcessed(storageResult);
        }
    }

    public void a(String str, String str2, StorageParams storageParams) {
        if (str == null || str2 == null) {
            com.huawei.hwlogsmodel.LogUtil.h("KeyValDbManager", "setEncryptValue, key or value is null so return PARAMS_ERROR.");
            return;
        }
        if (storageParams == null || storageParams.e() == 0) {
            com.huawei.hwlogsmodel.LogUtil.h("KeyValDbManager", "setEncryptValue, storageParams is not encryptDataType.");
            return;
        }
        com.huawei.hwlogsmodel.LogUtil.c("KeyValDbManager", "Enter setEncryptValue() with key=", str, ", value=", str2, ", dataKey=", Integer.valueOf(storageParams.e()));
        Object[] objArr = new Object[2];
        objArr[0] = MmkvUtil.e("keyvaldb_encrypt", this.d).encode(str, str2) ? "setEncryptValue success." : "setValue failed key =";
        objArr[1] = str;
        com.huawei.hwlogsmodel.LogUtil.a("KeyValDbManager", objArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:10:0x005d  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x008c A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:38:0x0091  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x003a  */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [android.database.Cursor] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private java.lang.String e(java.lang.String r8, health.compact.a.StorageParams r9) {
        /*
            r7 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "key = '"
            r0.<init>(r1)
            r0.append(r8)
            java.lang.String r1 = "'"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.String r1 = "getEncryptValue, whereSql = "
            java.lang.Object[] r1 = new java.lang.Object[]{r1, r0}
            java.lang.String r2 = "KeyValDbManager"
            com.huawei.hwlogsmodel.LogUtil.c(r2, r1)
            r1 = 0
            r3 = 1
            r4 = 0
            java.lang.String r5 = "keyvaldb"
            r6 = 2
            android.database.Cursor r0 = r7.queryStorageData(r5, r6, r0)     // Catch: java.lang.Throwable -> L3e android.database.SQLException -> L40
            if (r0 == 0) goto L37
            boolean r5 = r0.moveToFirst()     // Catch: android.database.SQLException -> L41 java.lang.Throwable -> L8d
            if (r5 == 0) goto L37
            java.lang.String r5 = r0.getString(r3)     // Catch: android.database.SQLException -> L41 java.lang.Throwable -> L8d
            goto L38
        L37:
            r5 = r4
        L38:
            if (r0 == 0) goto L50
            r0.close()
            goto L50
        L3e:
            r8 = move-exception
            goto L8f
        L40:
            r0 = r4
        L41:
            java.lang.Object[] r5 = new java.lang.Object[r3]     // Catch: java.lang.Throwable -> L8d
            java.lang.String r6 = "getEncryptValue, queryStorage error"
            r5[r1] = r6     // Catch: java.lang.Throwable -> L8d
            com.huawei.hwlogsmodel.LogUtil.b(r2, r5)     // Catch: java.lang.Throwable -> L8d
            if (r0 == 0) goto L4f
            r0.close()
        L4f:
            r5 = r4
        L50:
            java.lang.String r0 = "getEncryptValue, key = "
            java.lang.String r6 = ", value = "
            java.lang.Object[] r8 = new java.lang.Object[]{r0, r8, r6, r5}
            com.huawei.hwlogsmodel.LogUtil.c(r2, r8)
            if (r5 == 0) goto L8c
            if (r9 == 0) goto L76
            int r8 = r9.e()     // Catch: java.lang.Exception -> L80 java.lang.RuntimeException -> L8a
            if (r8 == 0) goto L76
            android.content.Context r8 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()     // Catch: java.lang.Exception -> L80 java.lang.RuntimeException -> L8a
            health.compact.a.HwEncryptUtil r8 = health.compact.a.HwEncryptUtil.c(r8)     // Catch: java.lang.Exception -> L80 java.lang.RuntimeException -> L8a
            int r9 = r9.e()     // Catch: java.lang.Exception -> L80 java.lang.RuntimeException -> L8a
            java.lang.String r5 = r8.a(r9, r5)     // Catch: java.lang.Exception -> L80 java.lang.RuntimeException -> L8a
            goto L8c
        L76:
            java.lang.Object[] r8 = new java.lang.Object[r3]     // Catch: java.lang.Exception -> L80 java.lang.RuntimeException -> L8a
            java.lang.String r9 = "getEncryptValue, storageParams is not encryptDataType."
            r8[r1] = r9     // Catch: java.lang.Exception -> L80 java.lang.RuntimeException -> L8a
            com.huawei.hwlogsmodel.LogUtil.a(r2, r8)     // Catch: java.lang.Exception -> L80 java.lang.RuntimeException -> L8a
            return r4
        L80:
            java.lang.String r8 = "getEncryptValue decryptData Exception"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            com.huawei.hwlogsmodel.LogUtil.b(r2, r8)
            return r4
        L8a:
            r8 = move-exception
            throw r8
        L8c:
            return r5
        L8d:
            r8 = move-exception
            r4 = r0
        L8f:
            if (r4 == 0) goto L94
            r4.close()
        L94:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: health.compact.a.KeyValDbManager.e(java.lang.String, health.compact.a.StorageParams):java.lang.String");
    }

    public String d(String str, StorageParams storageParams) {
        if (str == null) {
            com.huawei.hwlogsmodel.LogUtil.h("KeyValDbManager", "getEncryptValue, key is null");
            return null;
        }
        if (storageParams == null || storageParams.e() == 0) {
            com.huawei.hwlogsmodel.LogUtil.a("KeyValDbManager", "getEncryptValue, storageParams is not encryptDataType.");
            return null;
        }
        MMKV e2 = MmkvUtil.e("keyvaldb_encrypt", this.d);
        String decodeString = e2.decodeString(str);
        if (decodeString == null && !"server_token".equals(str)) {
            decodeString = e(str, storageParams);
            if (decodeString == null) {
                e2.encode(str, "empty_value");
            } else {
                e2.encode(str, decodeString);
            }
        }
        String str2 = "empty_value".equals(decodeString) ? null : decodeString;
        com.huawei.hwlogsmodel.LogUtil.c("KeyValDbManager", "getEncryptValue, key = ", str, ", value = ", str2);
        return str2;
    }

    public int c(String str) {
        MMKV e2 = MmkvUtil.e("keyvaldb_encrypt", this.d);
        if (e2.contains(str)) {
            e2.encode(str, "empty_value");
        } else {
            MMKV e3 = MmkvUtil.e("keyvaldb_unencrypt", null);
            if (e3.contains(str)) {
                e3.encode(str, "empty_value");
            } else {
                com.huawei.hwlogsmodel.LogUtil.a("KeyValDbManager", "deleteKey, key is not in mmkv.");
            }
        }
        com.huawei.hwlogsmodel.LogUtil.a("KeyValDbManager", "deleteKey, key:", str);
        return 0;
    }

    @Override // com.huawei.hwbasemgr.HwBaseManager
    public Integer getModuleId() {
        return 20007;
    }
}
