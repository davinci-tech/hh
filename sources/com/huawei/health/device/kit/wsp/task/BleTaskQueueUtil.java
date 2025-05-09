package com.huawei.health.device.kit.wsp.task;

import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cjo;
import defpackage.cjq;

/* loaded from: classes3.dex */
public class BleTaskQueueUtil {

    /* renamed from: a, reason: collision with root package name */
    private int f2225a;
    private ITaskService e;
    private cjo<cjq> d = new cjo<>();
    private boolean b = false;
    private IAsynBleTaskCallback c = new IAsynBleTaskCallback() { // from class: com.huawei.health.device.kit.wsp.task.BleTaskQueueUtil.2
        @Override // com.huawei.health.device.kit.wsp.task.IAsynBleTaskCallback
        public void success(Object obj) {
            BleTaskQueueUtil.this.c();
        }

        @Override // com.huawei.health.device.kit.wsp.task.IAsynBleTaskCallback
        public void failed() {
            BleTaskQueueUtil.this.c();
        }
    };

    public enum TaskType {
        SET_AGE,
        SET_GENDER,
        SET_HEIGHT,
        SET_TIME,
        ENABLE_WEIGHT_SCALE,
        ENABLE_BODY_MEASUREMENT,
        ENABLE_GLUCOSE_MEASUREMENT,
        ENABLE_CLEAR_USER_INFO,
        CLEAR_USER_INFO,
        REQUEST_AUTH,
        AUTH_TOKEN,
        SEND_WORK_KEY,
        BIND_REQUEST,
        SET_USER_INFO,
        GET_MANAGER_INFO,
        SET_MANAGER_INFO,
        SEND_HILINK_CONFIG_INFO,
        SEND_HILINK_CONFIG_INFO_ENCRYPTED,
        GET_DEVICE_SSID,
        GET_ALLOW_RESET_WIFI,
        GET_WEIGHT_REAL_TIME_DATA,
        REQUEST_OTA_UPGRADE,
        OTA_UPGRADE_SHA_CHECK,
        SEND_OTA_PACKAGE_DATA,
        GET_OTA_UPGRADE_RESULT,
        GET_SCALE_VERSION,
        SET_WEIGHT_UNIT,
        GET_WEIGHT_UNIT,
        SET_SYNC_TIME,
        SET_SEND_CMD_PACKAGE,
        GET_WEIGHT_HISTORY_DATA,
        SEND_DEVICE_RESET,
        SEND_DELETE_USER_DATA,
        SEND_GET_USER_DATA,
        SEND_WAKE_UP,
        SEND_SSID,
        SEND_WIFI_PASSWORD,
        OPEN_STATUS,
        DISABLE_NOTIFICATION,
        WRITE_BLE_FILE,
        SEND_OTA_URL,
        NOTIFY_BLE_FILE
    }

    public BleTaskQueueUtil(ITaskService iTaskService) {
        this.e = iTaskService;
    }

    public void d() {
        LogUtil.a("BLETaskQueueUtil", "Enter clearTask and clear TaskFlag:");
        c(3);
        LogUtil.a("BLETaskQueueUtil", "FLAG_GET_MANAGER_INFO", 1, "FLAG_GET_MANAGER_AND_ACCOUNT_INFO = ", 2);
        this.b = false;
        this.d.a();
    }

    public void b(cjq cjqVar) {
        this.d.c(cjqVar);
    }

    public void e() {
        e(true);
    }

    private void e(boolean z) {
        if (this.d.d() == 0) {
            this.b = false;
        }
        if (this.b && z) {
            return;
        }
        cjq b = this.d.b();
        if (b == null) {
            this.b = false;
            return;
        }
        TaskType f = b.f();
        this.b = true;
        switch (AnonymousClass1.e[f.ordinal()]) {
            case 1:
                d(b);
                break;
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
            case 9:
                this.e.write(b, this.c);
                break;
            default:
                d(b, f);
                break;
        }
    }

    private void d(cjq cjqVar, TaskType taskType) {
        switch (taskType) {
            case REQUEST_AUTH:
            case AUTH_TOKEN:
            case SEND_WORK_KEY:
            case BIND_REQUEST:
            case SET_USER_INFO:
            case GET_MANAGER_INFO:
            case SET_MANAGER_INFO:
            case SEND_HILINK_CONFIG_INFO:
            case SEND_HILINK_CONFIG_INFO_ENCRYPTED:
            case GET_DEVICE_SSID:
            case GET_WEIGHT_REAL_TIME_DATA:
            case GET_WEIGHT_HISTORY_DATA:
            case GET_ALLOW_RESET_WIFI:
            case REQUEST_OTA_UPGRADE:
            case OTA_UPGRADE_SHA_CHECK:
            case GET_SCALE_VERSION:
            case SET_WEIGHT_UNIT:
            case GET_WEIGHT_UNIT:
            case SEND_DEVICE_RESET:
            case SEND_DELETE_USER_DATA:
            case SEND_GET_USER_DATA:
            case SET_SYNC_TIME:
            case SEND_WAKE_UP:
            case SEND_SSID:
            case SEND_WIFI_PASSWORD:
            case SEND_OTA_URL:
                e(cjqVar);
                break;
            case ENABLE_WEIGHT_SCALE:
            case ENABLE_BODY_MEASUREMENT:
            case ENABLE_GLUCOSE_MEASUREMENT:
            case ENABLE_CLEAR_USER_INFO:
            case GET_OTA_UPGRADE_RESULT:
            case OPEN_STATUS:
            case NOTIFY_BLE_FILE:
                a(cjqVar);
                break;
        }
    }

    private void d(cjq cjqVar) {
        if (cjqVar == null || cjqVar.b() == null) {
            LogUtil.h("BLETaskQueueUtil", "disableNotification illegal task data!");
        } else {
            this.e.disable(cjqVar, this.c);
        }
    }

    private void a(cjq cjqVar) {
        this.e.enable(cjqVar, this.c);
    }

    private void e(cjq cjqVar) {
        if (cjqVar == null || cjqVar.b() == null) {
            LogUtil.h("BLETaskQueueUtil", "executeEnableAndWriteTask illegal task data!");
            return;
        }
        this.e.enable(cjqVar, new IAsynBleTaskCallback() { // from class: com.huawei.health.device.kit.wsp.task.BleTaskQueueUtil.4
            @Override // com.huawei.health.device.kit.wsp.task.IAsynBleTaskCallback
            public void failed() {
            }

            @Override // com.huawei.health.device.kit.wsp.task.IAsynBleTaskCallback
            public void success(Object obj) {
            }
        });
        try {
            Thread.sleep(100L);
        } catch (InterruptedException e) {
            LogUtil.b("BLETaskQueueUtil", "executeEnableAndWriteTask exception = ", e.getMessage());
        }
        this.e.write(cjqVar, this.c);
    }

    public void c() {
        if (this.d.d() == 0) {
            LogUtil.h("BLETaskQueueUtil", "doNext mTaskQueue.size()==0");
            this.b = false;
        } else {
            try {
                Thread.sleep(100L);
            } catch (InterruptedException e) {
                LogUtil.b("BLETaskQueueUtil", "doNext e = ", e.getMessage());
            }
            e(false);
        }
    }

    public int a() {
        return this.d.d();
    }

    public void d(int i) {
        this.f2225a = i | this.f2225a;
    }

    public void c(int i) {
        this.f2225a = (~i) & this.f2225a;
    }

    public boolean e(int i) {
        return (i & this.f2225a) != 0;
    }
}
