package com.huawei.ui.device.activity.pairing;

import android.text.TextUtils;
import android.widget.ImageView;
import com.huawei.health.R;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hihealth.HiDataSourceFetchOption;
import com.huawei.hihealth.HiDeviceInfo;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataClientListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import defpackage.cwi;
import defpackage.jfu;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class DevicePairGuideUtil {

    /* renamed from: a, reason: collision with root package name */
    private static String f9193a;

    public interface ShowKidDialogCallback {
        void onResult(boolean z);
    }

    public static void e(String str) {
        f9193a = str;
    }

    public static String d() {
        return f9193a;
    }

    public static void cRF_(int i, ImageView imageView) {
        if (imageView == null) {
            LogUtil.a("DevicePairGuideUtil", "pairResultDeviceImage is null");
            return;
        }
        if (i != 5) {
            if (i != 18 && i != 7) {
                if (i != 8) {
                    if (i != 23 && i != 24 && i != 36 && i != 37) {
                        switch (i) {
                            case 10:
                                break;
                            case 11:
                                imageView.setImageResource(R.drawable._2131430609_res_0x7f0b0cd1);
                                break;
                            case 12:
                            case 13:
                            case 14:
                            case 15:
                                break;
                            default:
                                int i2 = jfu.c(i).i();
                                if (i2 != -1) {
                                    if (i2 != 1) {
                                        if (i2 != 2) {
                                            if (i2 != 6) {
                                                if (i2 == 3) {
                                                    imageView.setImageResource(R.drawable._2131430609_res_0x7f0b0cd1);
                                                    break;
                                                } else {
                                                    LogUtil.h("DevicePairGuideUtil", "setImageResourceByType default.");
                                                    break;
                                                }
                                            } else {
                                                imageView.setImageResource(R.drawable._2131430635_res_0x7f0b0ceb);
                                                break;
                                            }
                                        } else {
                                            imageView.setImageResource(R.drawable._2131430655_res_0x7f0b0cff);
                                            break;
                                        }
                                    } else {
                                        imageView.setImageResource(R.drawable._2131430608_res_0x7f0b0cd0);
                                        break;
                                    }
                                }
                                break;
                        }
                        return;
                    }
                }
            }
            imageView.setImageResource(R.drawable._2131430608_res_0x7f0b0cd0);
            return;
        }
        imageView.setImageResource(R.drawable._2131430655_res_0x7f0b0cff);
    }

    public static void a(final DeviceInfo deviceInfo, final ShowKidDialogCallback showKidDialogCallback) {
        if (deviceInfo == null || showKidDialogCallback == null) {
            LogUtil.h("DevicePairGuideUtil", "deviceInfo == null or callback == null");
            return;
        }
        boolean isKidAccount = LoginInit.getInstance(BaseApplication.getContext()).isKidAccount();
        boolean c = cwi.c(deviceInfo, 110);
        LogUtil.a("DevicePairGuideUtil", "isKidAccount:", Boolean.valueOf(isKidAccount), ", isUnSupportKid:", Boolean.valueOf(c));
        if (isKidAccount && c) {
            HiHealthNativeApi.a(BaseApplication.getContext()).fetchDataSource(HiDataSourceFetchOption.builder().a(0).e(), new HiDataClientListener() { // from class: com.huawei.ui.device.activity.pairing.DevicePairGuideUtil.2
                @Override // com.huawei.hihealth.data.listener.HiDataClientListener
                public void onResult(List<HiHealthClient> list) {
                    String str;
                    if (list == null) {
                        LogUtil.h("DevicePairGuideUtil", "clientList is null need show kid dialog");
                        ShowKidDialogCallback.this.onResult(true);
                        return;
                    }
                    String deviceName = deviceInfo.getDeviceName();
                    if (TextUtils.isEmpty(deviceName)) {
                        LogUtil.h("DevicePairGuideUtil", "currentDeviceName is empty.");
                        ShowKidDialogCallback.this.onResult(false);
                        return;
                    }
                    LogUtil.a("DevicePairGuideUtil", "currentDeviceName:", deviceName);
                    String[] split = deviceName.split("\\-");
                    if (split.length > 2) {
                        str = deviceName.replace(Constants.LINK + split[split.length - 1], "");
                    } else if (split.length == 2) {
                        str = split[0];
                    } else {
                        ShowKidDialogCallback.this.onResult(false);
                        return;
                    }
                    if (DevicePairGuideUtil.d(str, list)) {
                        ShowKidDialogCallback.this.onResult(false);
                    } else {
                        ShowKidDialogCallback.this.onResult(true);
                    }
                }
            });
        } else {
            showKidDialogCallback.onResult(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean d(String str, List<HiHealthClient> list) {
        boolean z;
        Iterator<HiHealthClient> it = list.iterator();
        while (true) {
            z = false;
            if (!it.hasNext()) {
                break;
            }
            HiDeviceInfo hiDeviceInfo = it.next().getHiDeviceInfo();
            if (hiDeviceInfo != null) {
                String deviceName = hiDeviceInfo.getDeviceName();
                if (TextUtils.isEmpty(deviceName)) {
                    continue;
                } else {
                    String[] split = deviceName.split("\\-");
                    LogUtil.a("DevicePairGuideUtil", "deviceName:", deviceName);
                    if (split.length > 2) {
                        deviceName = deviceName.replace(Constants.LINK + split[split.length - 1], "");
                    }
                    if (split.length == 2) {
                        deviceName = split[0];
                    }
                    if (str.equalsIgnoreCase(deviceName)) {
                        LogUtil.a("DevicePairGuideUtil", "device equals deviceName:", deviceName, ", currentDeviceName:", str);
                        z = true;
                        break;
                    }
                }
            }
        }
        LogUtil.a("DevicePairGuideUtil", "isBindDevice：", Boolean.valueOf(z));
        return z;
    }
}
