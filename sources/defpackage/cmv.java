package defpackage;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import com.huawei.health.R;
import com.huawei.health.device.manager.DeviceCloudSharePreferencesManager;
import com.huawei.health.device.ui.measure.fragment.HagridDeviceManagerFragment;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.device.wifi.lib.handler.StaticHandler;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import health.compact.a.CommonUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.Utils;
import java.util.Locale;

/* loaded from: classes3.dex */
public class cmv extends StaticHandler<HagridDeviceManagerFragment> {
    private NoTitleCustomAlertDialog c;

    public cmv(HagridDeviceManagerFragment hagridDeviceManagerFragment) {
        super(hagridDeviceManagerFragment);
    }

    @Override // com.huawei.health.device.wifi.lib.handler.StaticHandler
    /* renamed from: IR_, reason: merged with bridge method [inline-methods] */
    public void handleMessage(HagridDeviceManagerFragment hagridDeviceManagerFragment, Message message) {
        if (hagridDeviceManagerFragment == null || hagridDeviceManagerFragment.isDestory() || !hagridDeviceManagerFragment.isAdded()) {
            LogUtil.h("HagridDeviceManagerHandler", "MyHandler object is dead");
        } else if (message == null) {
            LogUtil.h("R_Weight_HagridDeviceManagerHandler", "MyHandler msg is null");
        } else {
            IP_(hagridDeviceManagerFragment, message);
            IQ_(hagridDeviceManagerFragment, message);
        }
    }

    private void IP_(HagridDeviceManagerFragment hagridDeviceManagerFragment, Message message) {
        int i = message.what;
        if (i != 1) {
            if (i == 2) {
                LogUtil.a("HagridDeviceManagerHandler", "MyHandler msg is REFRESH_WLAN_AND_OTA");
                e(hagridDeviceManagerFragment);
                return;
            }
            if (i == 3) {
                if (b(hagridDeviceManagerFragment)) {
                    EventBus.d(new EventBus.b("get_device_ssid", new Bundle()));
                }
            } else {
                if (i == 4) {
                    cnn.b().d(hagridDeviceManagerFragment);
                    return;
                }
                if (i != 5) {
                    if (i == 14) {
                        hagridDeviceManagerFragment.setWeightUnitResult(message.arg1);
                        return;
                    } else {
                        LogUtil.h("R_Weight_HagridDeviceManagerHandler", "MyHandler what is other");
                        return;
                    }
                }
                if (b(hagridDeviceManagerFragment)) {
                    hagridDeviceManagerFragment.setHuidType(3);
                    EventBus.d(new EventBus.b("get_manager_info", new Bundle()));
                }
            }
        }
    }

    private boolean b(HagridDeviceManagerFragment hagridDeviceManagerFragment) {
        if (hagridDeviceManagerFragment.getWeightInteractor() != null) {
            return hagridDeviceManagerFragment.getWeightInteractor().b();
        }
        return false;
    }

    private void IQ_(HagridDeviceManagerFragment hagridDeviceManagerFragment, Message message) {
        switch (message.what) {
            case 6:
                hagridDeviceManagerFragment.refreshSubUserNum();
                break;
            case 7:
                b(hagridDeviceManagerFragment, message.arg1);
                break;
            case 8:
                hagridDeviceManagerFragment.switchToSubUserWifiPage();
                break;
            case 9:
                if (message.arg1 == 1) {
                    cnc.b().Jd_("send_reset_cmd", new Bundle());
                } else {
                    cnc.b().c();
                }
                cnc.b().d();
                break;
            case 10:
                a(hagridDeviceManagerFragment);
                break;
            case 11:
            case 12:
            case 14:
            default:
                LogUtil.h("R_Weight_HagridDeviceManagerHandler", "MyHandler what is other");
                break;
            case 13:
                cnc.b().c();
                break;
            case 15:
                hagridDeviceManagerFragment.gotoHagridDeviceWlanUseGuideFragment();
                break;
            case 16:
                hagridDeviceManagerFragment.connectSuccess();
                break;
        }
    }

    private void e(HagridDeviceManagerFragment hagridDeviceManagerFragment) {
        if (cpa.ah(hagridDeviceManagerFragment.getProductId()) || cpa.r(hagridDeviceManagerFragment.getProductId())) {
            return;
        }
        boolean z = false;
        if (hagridDeviceManagerFragment.getWiFiDevice() != null && hagridDeviceManagerFragment.getHuid() != null && hagridDeviceManagerFragment.getHuid().length == 0) {
            LogUtil.a("HagridDeviceManagerHandler", "mWifiDevice != null but mHuid = null");
            if (hagridDeviceManagerFragment.getWiFiDevice().b().k() == 1) {
                LogUtil.a("HagridDeviceManagerHandler", "mHuid = null but isMainCloudUser");
                csf.i(hagridDeviceManagerFragment.getWiFiDevice().d());
                hagridDeviceManagerFragment.getWiFiDevice().doRemoveBond();
                hagridDeviceManagerFragment.setWiFiDevice(null);
                hagridDeviceManagerFragment.setDevId(null);
                hagridDeviceManagerFragment.setMainUser(false);
                i(hagridDeviceManagerFragment);
                return;
            }
        }
        if (hagridDeviceManagerFragment.getAdapter() == null) {
            LogUtil.h("R_Weight_HagridDeviceManagerHandler", "mAdapter is null.");
            return;
        }
        if (hagridDeviceManagerFragment.getHuid() != null && hagridDeviceManagerFragment.getHuid().length > 0) {
            z = true;
        }
        c(hagridDeviceManagerFragment, z);
        if (!z) {
            c(hagridDeviceManagerFragment);
            return;
        }
        if (!hagridDeviceManagerFragment.isMainUser()) {
            LogUtil.a("HagridDeviceManagerHandler", "current user is not main user and show shareItem");
            cnu createSettingItem = hagridDeviceManagerFragment.createSettingItem(14, b(R.string.IDS_device_user_manager), "", "");
            createSettingItem.b(true);
            c(hagridDeviceManagerFragment, createSettingItem);
            hagridDeviceManagerFragment.dealWithLoadingResult(!hagridDeviceManagerFragment.isClickDeviceVersionUpdateItem());
            return;
        }
        LogUtil.h("R_Weight_HagridDeviceManagerHandler", "RefreshWlan no new items.");
    }

    private void c(HagridDeviceManagerFragment hagridDeviceManagerFragment, cnu cnuVar) {
        boolean c = hagridDeviceManagerFragment.getAdapter().c(13, cnuVar);
        LogUtil.a("HagridDeviceManagerHandler", "replaceFlag: ", Boolean.valueOf(c));
        if (c) {
            hagridDeviceManagerFragment.getAdapter().c(12);
        }
    }

    private String b(int i) {
        return BaseApplication.getContext().getResources().getString(i);
    }

    private void c(HagridDeviceManagerFragment hagridDeviceManagerFragment) {
        String format;
        LogUtil.a("HagridDeviceManagerHandler", "No manager.");
        if (nsn.ae(BaseApplication.getContext())) {
            format = String.format(Locale.ENGLISH, hagridDeviceManagerFragment.getMainActivity().getResources().getString(R.string.IDS_pad_device_auto_sync_data), new Object[0]);
        } else {
            format = String.format(Locale.ENGLISH, hagridDeviceManagerFragment.getMainActivity().getResources().getString(R.string.IDS_device_request_wlan_auto_sync_data_sub_content), new Object[0]);
        }
        LogUtil.a("HagridDeviceManagerHandler", "refreshWlan wlanItem does not have Manager and show config network");
        cnu createSettingItem = hagridDeviceManagerFragment.createSettingItem(13, b(R.string.IDS_device_wifi_config_network), format, "");
        createSettingItem.b(true);
        hagridDeviceManagerFragment.getAdapter().c(14, createSettingItem);
        hagridDeviceManagerFragment.getAdapter().a(createSettingItem);
        hagridDeviceManagerFragment.dealWithLoadingResult(hagridDeviceManagerFragment.isClickDeviceVersionUpdateItem());
    }

    private void i(HagridDeviceManagerFragment hagridDeviceManagerFragment) {
        LogUtil.a("HagridDeviceManagerHandler", "update item manager to ble");
        hagridDeviceManagerFragment.getAdapter().b(12);
        hagridDeviceManagerFragment.showClaimDataView();
    }

    private void c(HagridDeviceManagerFragment hagridDeviceManagerFragment, boolean z) {
        DeviceCloudSharePreferencesManager deviceCloudSharePreferencesManager = new DeviceCloudSharePreferencesManager(cpp.a());
        StringBuffer stringBuffer = new StringBuffer(16);
        stringBuffer.append("device_has_manager");
        stringBuffer.append(hagridDeviceManagerFragment.getUniqueId());
        LogUtil.a("HagridDeviceManagerHandler", "saveDeviceManagerStatus uniqueId: ", CommonUtil.l(hagridDeviceManagerFragment.getUniqueId()));
        deviceCloudSharePreferencesManager.b(stringBuffer.toString(), z);
    }

    private void b(HagridDeviceManagerFragment hagridDeviceManagerFragment, int i) {
        if (hagridDeviceManagerFragment.getAdapter() != null) {
            cnu a2 = hagridDeviceManagerFragment.getAdapter().a(12);
            if (a2 != null) {
                a2.a(i > 0);
                hagridDeviceManagerFragment.getAdapter().notifyDataSetChanged();
                return;
            }
            return;
        }
        LogUtil.h("HagridDeviceManagerHandler", "refreshAuthRequestNum is null, num:", Integer.valueOf(i));
    }

    public void a(HagridDeviceManagerFragment hagridDeviceManagerFragment) {
        LogUtil.a("HagridDeviceManagerHandler", "showConfigureNetworkDialog starting");
        if (hagridDeviceManagerFragment.getMainActivity() == null) {
            LogUtil.h("HagridDeviceManagerHandler", "showConfigureNetworkDialog getActivity is null");
            return;
        }
        if (!b(hagridDeviceManagerFragment)) {
            LogUtil.h("HagridDeviceManagerHandler", "please connect bluetooth!!!");
            return;
        }
        if (!"b29df4e3-b1f7-4e40-960d-4cfb63ccca05".equals(hagridDeviceManagerFragment.getProductId())) {
            LogUtil.h("HagridDeviceManagerHandler", "this device is not HAG2021 scale");
            return;
        }
        if (!(ceo.d().d(hagridDeviceManagerFragment.getUniqueId(), false) instanceof ctk) && !coy.e(hagridDeviceManagerFragment.getUniqueId())) {
            if (coy.JU_(hagridDeviceManagerFragment.getHuid(), hagridDeviceManagerFragment.getMainActivity(), "HagridDeviceManagerHandler")) {
                LogUtil.h("HagridDeviceManagerHandler", "showConfigureNetworkDialog current user is mainUser");
                return;
            } else {
                d(hagridDeviceManagerFragment);
                return;
            }
        }
        LogUtil.h("HagridDeviceManagerHandler", "is not bleDevice or have manage");
    }

    private void d(final HagridDeviceManagerFragment hagridDeviceManagerFragment) {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.c;
        if (noTitleCustomAlertDialog != null) {
            if (noTitleCustomAlertDialog.isShowing()) {
                this.c.dismiss();
            }
            this.c = null;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(hagridDeviceManagerFragment.getMainActivity());
        builder.e(Utils.o() ? R.string._2130840657_res_0x7f020c51 : R.string.IDS_device_manage_network_tip).czC_(R.string.IDS_wlan_config_device, new View.OnClickListener() { // from class: cnd
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                cmv.this.IS_(hagridDeviceManagerFragment, view);
            }
        }).czz_(R.string.IDS_device_hygride_button_cancel, new View.OnClickListener() { // from class: cmz
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                cmv.IO_(HagridDeviceManagerFragment.this, view);
            }
        });
        NoTitleCustomAlertDialog e = builder.e();
        this.c = e;
        e.setCancelable(false);
        this.c.show();
    }

    /* synthetic */ void IS_(HagridDeviceManagerFragment hagridDeviceManagerFragment, View view) {
        LogUtil.a("HagridDeviceManagerHandler", "configCustomAlertDialog is Positive");
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "hagridConfigureTheNetwork" + hagridDeviceManagerFragment.getUniqueId(), String.valueOf(System.currentTimeMillis()), (StorageParams) null);
        if (b(hagridDeviceManagerFragment)) {
            hagridDeviceManagerFragment.configWifi();
            this.c.dismiss();
        } else {
            nrh.d(BaseApplication.getContext(), b(R.string.IDS_plugin_device_weight_device_not_connect));
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void IO_(HagridDeviceManagerFragment hagridDeviceManagerFragment, View view) {
        LogUtil.a("HagridDeviceManagerHandler", "configCustomAlertDialog is Negative");
        SharedPreferenceManager.e(BaseApplication.getContext(), String.valueOf(10000), "hagridConfigureTheNetwork" + hagridDeviceManagerFragment.getUniqueId(), String.valueOf(System.currentTimeMillis()), (StorageParams) null);
        ViewClickInstrumentation.clickOnView(view);
    }

    public void c() {
        NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.c;
        if (noTitleCustomAlertDialog != null && noTitleCustomAlertDialog.isShowing()) {
            this.c.dismiss();
        }
        this.c = null;
    }
}
