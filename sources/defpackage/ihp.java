package defpackage;

import com.huawei.hihealthservice.db.table.DBSessionCommon;

/* loaded from: classes4.dex */
public class ihp extends DBSessionCommon {
    private ihp() {
    }

    /* loaded from: classes7.dex */
    static class d {
        private static final ihp c = new ihp();
    }

    public static ihp c() {
        return d.c;
    }

    public static String a() {
        return getSessionCreateTableSQL("sample_session_core");
    }

    public static String b() {
        return getSessionCommonIndexSQL("CoreSessionIndex", "sample_session_core");
    }

    @Override // com.huawei.hihealthservice.db.table.DBCommon
    public String getTableName() {
        return "sample_session_core";
    }
}
