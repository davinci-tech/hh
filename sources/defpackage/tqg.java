package defpackage;

/* loaded from: classes8.dex */
public class tqg {
    public static String[] d() {
        return new String[]{"id", "app_Id", "scope_Id", "name", "uri", "permissions"};
    }

    public static String c() {
        return "create table  IF NOT EXISTS tb_wear_engine_scope_info(id INTEGER PRIMARY KEY AUTOINCREMENT,app_Id TEXT,scope_Id INTEGER,name TEXT,uri TEXT,permissions TEXT)";
    }
}
