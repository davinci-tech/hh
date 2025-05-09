package defpackage;

import com.huawei.pluginachievement.manager.model.MedalConstants;
import com.tencent.open.SocialOperation;

/* loaded from: classes8.dex */
public class tqi {
    public static String[] e() {
        return new String[]{MedalConstants.EVENT_KEY, "app_uid", "package_name", "app_id", "user_id", "app_name", "app_icon", "version", SocialOperation.GAME_SIGNATURE, "cloud_code", "create_time"};
    }

    public static String a() {
        return "create table  IF NOT EXISTS tb_wear_engine_app_info(key TEXT primary key not null,app_uid integer,package_name TEXT,app_id integer,user_id TEXT,app_name TEXT,app_icon BLOB,version TEXT,signature TEXT,cloud_code integer not null,create_time TEXT not null)";
    }
}
