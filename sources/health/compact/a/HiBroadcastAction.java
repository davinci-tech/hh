package health.compact.a;

import android.content.IntentFilter;

/* loaded from: classes.dex */
public class HiBroadcastAction {
    private HiBroadcastAction() {
    }

    public static IntentFilter bwi_() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.hihealth.action_account_login_datas_switch_finish");
        intentFilter.addAction("com.huawei.hihealth.action_data_refresh");
        intentFilter.addAction("com.huawei.hihealth.action_receive_push");
        intentFilter.addAction("com.huawei.hihealth.action_sqlite_upgrade_done");
        intentFilter.addAction("com.huawei.hihealth.action_sqlite_upgrade_exception");
        intentFilter.addAction("com.huawei.hihealth.action_sqlite_upgrade_working");
        intentFilter.addAction("com.huawei.hihealth.action_sync");
        intentFilter.addAction("com.huawei.hihealth.action_sync_data");
        intentFilter.addAction("com.huawei.hihealth.action_sync_total_sport_data");
        intentFilter.addAction("com.huawei.hihealth.action_sync_type");
        intentFilter.addAction("com.huawei.hihealth.action_sync_process");
        intentFilter.addAction("com.huawei.hihealth.action_sync_status");
        intentFilter.addAction("com.huawei.hihealth.action_user_goal_change");
        intentFilter.addAction("com.huawei.hihealth.user_preference");
        intentFilter.addAction("com.huawei.hihealth.binding_device");
        return intentFilter;
    }
}
