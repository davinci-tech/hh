package com.huawei.hihealth.util;

import android.content.Context;
import android.content.Intent;
import com.huawei.hihealth.HiDeviceInfo;
import health.compact.a.HiBroadcastManager;
import health.compact.a.HuaweiHealth;
import health.compact.a.util.LogUtil;

/* loaded from: classes.dex */
public class HiBroadcastUtil {
    private HiBroadcastUtil() {
    }

    public static void b(Context context, int i) {
        if (context == null) {
            return;
        }
        LogUtil.d("HiH_HiBroadcastUtil", "sendAccountSwitchFinishBroadcast");
        Intent intent = new Intent("com.huawei.hihealth.action_account_login_datas_switch_finish");
        intent.setPackage(context.getPackageName());
        intent.putExtra("time", System.currentTimeMillis());
        intent.putExtra("account_switch_status", i);
        bwL_(context, intent);
    }

    public static void c(Context context) {
        if (context == null) {
            return;
        }
        LogUtil.d("HiH_HiBroadcastUtil", "sendSyncBeginBroadcast");
        Intent intent = new Intent("com.huawei.hihealth.action_sync");
        intent.setPackage(context.getPackageName());
        intent.putExtra("com.huawei.hihealth.action_sync_status", 0);
        bwL_(context, intent);
    }

    public static void d(Context context, double d) {
        if (context == null) {
            return;
        }
        LogUtil.d("HiH_HiBroadcastUtil", "sendSyncProcessBroadcast process is ", Double.valueOf(d));
        Intent intent = new Intent("com.huawei.hihealth.action_sync");
        intent.setPackage(context.getPackageName());
        intent.putExtra("com.huawei.hihealth.action_sync_status", 1);
        intent.putExtra("com.huawei.hihealth.action_sync_process", d);
        bwL_(context, intent);
    }

    public static void b(Context context) {
        if (context == null) {
            return;
        }
        LogUtil.d("HiH_HiBroadcastUtil", "sendPullSportDataFinishBroadcast");
        Intent intent = new Intent("com.huawei.hihealth.action_sync");
        intent.setPackage(context.getPackageName());
        intent.putExtra("com.huawei.hihealth.action_sync_status", 1000);
        bwL_(context, intent);
    }

    public static void f(Context context) {
        if (context == null) {
            return;
        }
        LogUtil.d("HiH_HiBroadcastUtil", "sendSyncDoneBroadcast");
        Intent intent = new Intent("com.huawei.hihealth.action_sync");
        intent.setPackage(context.getPackageName());
        intent.putExtra("com.huawei.hihealth.action_sync_status", 2);
        bwL_(context, intent);
    }

    public static void j(Context context) {
        if (context == null) {
            return;
        }
        LogUtil.d("HiH_HiBroadcastUtil", "sendSyncFailedBroadcast");
        Intent intent = new Intent("com.huawei.hihealth.action_sync");
        intent.setPackage(context.getPackageName());
        intent.putExtra("com.huawei.hihealth.action_sync_status", 3);
        bwL_(context, intent);
    }

    public static void h(Context context) {
        if (context == null) {
            return;
        }
        LogUtil.d("HiH_HiBroadcastUtil", "sendWifiDeviceUnitBroadcast");
        Intent intent = new Intent();
        intent.setPackage(context.getPackageName());
        intent.setAction("com.huawei.health.action.ACTION_WIFI_DEVICE_UNIT_ACTION");
        context.sendBroadcast(intent, (context.getPackageName() + ".permission.LOCAL_BROADCAST").intern());
    }

    public static void e(Context context) {
        if (context == null) {
            return;
        }
        LogUtil.d("HiH_HiBroadcastUtil", "sendPushDataSyncDone");
        Intent intent = new Intent();
        intent.setPackage(context.getPackageName());
        intent.setAction("com.huawei.health.action.ACTION_PUSH_DATA_DONE_ACTION");
        context.sendBroadcast(intent, (context.getPackageName() + ".permission.LOCAL_BROADCAST").intern());
    }

    public static void b(Context context, int i, int i2) {
        if (context == null) {
            return;
        }
        LogUtil.d("HiH_HiBroadcastUtil", "sendDownloadDataResult syncType=", Integer.valueOf(i), ", result=", Integer.valueOf(i2));
        Intent intent = new Intent("com.huawei.hihealth.action_download_data_result");
        intent.setPackage(context.getPackageName());
        intent.putExtra("com.huawei.hihealth.action_download_data_result_code", i2);
        bwL_(context, intent);
    }

    public static void a(Context context, String str, long j, boolean z) {
        if (context == null) {
            return;
        }
        LogUtil.d("HiH_HiBroadcastUtil", "sendSyncDataResult syncId=", Long.valueOf(j), ", isSuccess=", Boolean.valueOf(z), " types=", str);
        Intent intent = new Intent("com.huawei.hihealth.action_sync_data_result");
        intent.setPackage(context.getPackageName());
        intent.putExtra("sync_data_result_type", str);
        intent.putExtra("sync_data_result_id", j);
        intent.putExtra("sync_data_result_success", z);
        bwL_(context, intent);
    }

    public static void g(Context context) {
        if (context == null) {
            return;
        }
        LogUtil.d("HiH_HiBroadcastUtil", "sendSyncTotalSportDataBroadcast");
        Intent intent = new Intent("com.huawei.hihealth.action_sync_total_sport_data");
        intent.setPackage(context.getPackageName());
        bwL_(context, intent);
    }

    public static void d(Context context, int i, int i2) {
        if (context == null) {
            return;
        }
        LogUtil.d("HiH_HiBroadcastUtil", "sendSyncDataBroadcast syncDataType is ", Integer.valueOf(i), " syncStatus is ", Integer.valueOf(i2));
        Intent intent = new Intent("com.huawei.hihealth.action_sync_data");
        intent.setPackage(context.getPackageName());
        intent.putExtra("com.huawei.hihealth.action_sync_type", i);
        intent.putExtra("com.huawei.hihealth.action_sync_status", i2);
        bwL_(context, intent);
    }

    public static void c(Context context, int i) {
        if (context == null) {
            return;
        }
        LogUtil.d("HiH_HiBroadcastUtil", "sendDataRefreshBroadcast refreshType = ", Integer.valueOf(i));
        Intent intent = new Intent("com.huawei.hihealth.action_data_refresh");
        intent.setPackage(context.getPackageName());
        intent.putExtra("refresh_type", i);
        bwL_(context, intent);
    }

    public static void d(Context context) {
        if (context == null) {
            return;
        }
        LogUtil.d("HiH_HiBroadcastUtil", "sendShowPrivicyBroadcast...");
        Intent intent = new Intent("com.huawei.hihealth.action_change_to_cloud_version");
        intent.setPackage(context.getPackageName());
        bwL_(context, intent);
    }

    public static void b(Context context, int i, String str) {
        if (context == null) {
            return;
        }
        LogUtil.d("HiH_HiBroadcastUtil", "sendDataRefreshBroadcast refreshType = ", Integer.valueOf(i));
        Intent intent = new Intent("com.huawei.hihealth.action_data_refresh");
        intent.setPackage(context.getPackageName());
        intent.putExtra("refresh_type", i);
        intent.putExtra("device_uniquecode", str);
        bwL_(context, intent);
    }

    public static void i(Context context) {
        if (context == null) {
            LogUtil.c("HiH_HiBroadcastUtil", "sendTodaySportDataRefreshBroadcast: context is null");
        } else {
            d(context, 0);
        }
    }

    public static void d(Context context, int i) {
        if (context == null) {
            return;
        }
        String packageName = context.getPackageName();
        try {
            if (HuaweiHealth.b().equals(packageName)) {
                Intent intent = new Intent("com.huawei.hihealth.action_today_sport_data_refresh");
                LogUtil.d("HiH_HiBroadcastUtil", "sendDaemonServiceBroadcast is health start DaemonService");
                intent.setPackage(packageName);
                intent.setClassName(packageName, "com.huawei.health.manager.DaemonService");
                intent.putExtra("refresh_type", i);
                ReleaseLogUtil.b("HiH_HiBroadcastUtil", "sendDaemonServiceBroadcast componentName ", context.startService(intent));
            }
        } catch (IllegalStateException e) {
            e = e;
            ReleaseLogUtil.c("HiH_HiBroadcastUtil", "sendDaemonServiceBroadcast Exception", e.getClass().getSimpleName());
        } catch (NoSuchMethodError e2) {
            e = e2;
            ReleaseLogUtil.c("HiH_HiBroadcastUtil", "sendDaemonServiceBroadcast Exception", e.getClass().getSimpleName());
        } catch (SecurityException e3) {
            e = e3;
            ReleaseLogUtil.c("HiH_HiBroadcastUtil", "sendDaemonServiceBroadcast Exception", e.getClass().getSimpleName());
        } catch (Exception e4) {
            ReleaseLogUtil.c("HiH_HiBroadcastUtil", "sendDaemonServiceBroadcast Exception", e4.getClass().getSimpleName());
        }
    }

    public static void a(Context context) {
        if (context == null) {
            return;
        }
        LogUtil.d("HiH_HiBroadcastUtil", "sendReceivePushBroadcast");
        Intent intent = new Intent("com.huawei.hihealth.action_receive_push");
        intent.setPackage(context.getPackageName());
        bwL_(context, intent);
    }

    public static void a(Context context, String str) {
        if (context == null) {
            return;
        }
        LogUtil.d("HiH_HiBroadcastUtil", "sendUserPreferenceBroadcast");
        Intent intent = new Intent("com.huawei.hihealth.user_preference");
        intent.setPackage(context.getPackageName());
        intent.putExtra("user_preference_key", str);
        bwL_(context, intent);
    }

    public static void d(Context context, HiDeviceInfo hiDeviceInfo) {
        LogUtil.d("HiH_HiBroadcastUtil", "sendBindingDeviceBroadcast enter ");
        if (context != null && hiDeviceInfo != null) {
            HiDeviceInfo hiDeviceInfo2 = new HiDeviceInfo(hiDeviceInfo.getDeviceType());
            hiDeviceInfo2.setDeviceName(hiDeviceInfo.getDeviceName());
            hiDeviceInfo2.setDeviceUniqueCode(hiDeviceInfo.getDeviceUniqueCode());
            String valueOf = String.valueOf(System.currentTimeMillis());
            String deviceUniqueCode = hiDeviceInfo2.getDeviceUniqueCode();
            if (deviceUniqueCode != null) {
                StringBuilder sb = new StringBuilder(deviceUniqueCode);
                if (deviceUniqueCode.length() >= 4) {
                    sb.replace(2, deviceUniqueCode.length() - 2, valueOf);
                } else {
                    sb.append(valueOf);
                }
                hiDeviceInfo2.setDeviceUniqueCode(sb.toString());
                Intent intent = new Intent("com.huawei.hihealth.binding_device");
                intent.setPackage(context.getPackageName());
                intent.putExtra("devicinfo", hiDeviceInfo2);
                bwL_(context, intent);
                return;
            }
            LogUtil.d("HiH_HiBroadcastUtil", "sendBindingDeviceBroadcast, deviceUniqueCode = null ");
            return;
        }
        LogUtil.d("HiH_HiBroadcastUtil", "sendBindingDeviceBroadcast, deviceInfo = null ");
    }

    private static void bwL_(Context context, Intent intent) {
        HiBroadcastManager.bwk_(context, intent);
    }
}
