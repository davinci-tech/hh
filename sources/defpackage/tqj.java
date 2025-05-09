package defpackage;

import com.huawei.pluginachievement.manager.model.MedalConstants;

/* loaded from: classes8.dex */
public final class tqj {
    public static String[] c() {
        return new String[]{MedalConstants.EVENT_KEY, "app_uid", "user_id", "app_id", "permission", "open_status", "encrypted_string"};
    }

    public static String e() {
        return "create table  IF NOT EXISTS tb_wear_engine_auth_info(key TEXT primary key not null,app_uid integer,user_id TEXT,app_id integer,permission TEXT,open_status integer,encrypted_string TEXT)";
    }
}
