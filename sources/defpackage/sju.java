package defpackage;

import android.content.Context;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hmf.annotation.ApiDefine;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.thirdpartservice.wechatdevice.WechatDeviceProviderApi;
import com.huawei.ui.thirdpartservice.interactors.callback.SupportDeviceResultCallback;
import java.util.List;

@ApiDefine(uri = WechatDeviceProviderApi.class)
/* loaded from: classes7.dex */
public class sju implements WechatDeviceProviderApi {
    private List<sjy> e;

    @Override // com.huawei.thirdpartservice.wechatdevice.WechatDeviceProviderApi
    public void checkSupportWechatDevice(final DeviceInfo deviceInfo, final WechatDeviceProviderApi.CheckResultListener<Boolean> checkResultListener) {
        LogUtil.a("WechatDeviceProviderApi", "isBindWechat  deviceInfo hiLinkDeviceId= ", deviceInfo.getHiLinkDeviceId());
        sid.b(new ICloudOperationResult() { // from class: sjz
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            public final void operationResult(Object obj, String str, boolean z) {
                sju.a(WechatDeviceProviderApi.CheckResultListener.this, deviceInfo, (List) obj, str, z);
            }
        });
    }

    static /* synthetic */ void a(WechatDeviceProviderApi.CheckResultListener checkResultListener, DeviceInfo deviceInfo, List list, String str, boolean z) {
        if (z) {
            if (CollectionUtils.d(list)) {
                checkResultListener.onError(1, "Support list is empty");
                return;
            } else {
                checkResultListener.onResult(Boolean.valueOf(list.contains(deviceInfo.getHiLinkDeviceId())));
                return;
            }
        }
        checkResultListener.onError(2, str);
    }

    @Override // com.huawei.thirdpartservice.wechatdevice.WechatDeviceProviderApi
    public void isBindMaxCount(final WechatDeviceProviderApi.CheckResultListener<Boolean> checkResultListener) {
        List<sjy> list = this.e;
        if (list != null) {
            LogUtil.a("WechatDeviceProviderApi", "AuthorizedDevices count:", Integer.valueOf(a(list).size()));
            checkResultListener.onResult(Boolean.valueOf(a(this.e).size() >= 5));
        } else {
            sid.d(new SupportDeviceResultCallback() { // from class: sju.5
                @Override // com.huawei.ui.thirdpartservice.interactors.callback.SupportDeviceResultCallback
                public void obtainBindDeviceList(List<sjy> list2) {
                    sju.this.e = list2;
                    sju sjuVar = sju.this;
                    LogUtil.a("WechatDeviceProviderApi", "AuthorizedDevices count:", Integer.valueOf(sjuVar.a(sjuVar.e).size()));
                    WechatDeviceProviderApi.CheckResultListener checkResultListener2 = checkResultListener;
                    sju sjuVar2 = sju.this;
                    checkResultListener2.onResult(Boolean.valueOf(sjuVar2.a(sjuVar2.e).size() >= 5));
                }

                @Override // com.huawei.ui.thirdpartservice.interactors.callback.SupportDeviceResultCallback
                public void onError(int i, String str) {
                    checkResultListener.onError(i, str);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<sjy> a(List<sjy> list) {
        for (sjy sjyVar : list) {
            boolean a2 = sii.a(sjyVar);
            sjyVar.setLocalBound(a2);
            LogUtil.a("WechatDeviceProviderApi", "Update authorized device = ", sjyVar.getDeviceName(), " , is local bound = ", Boolean.valueOf(a2));
        }
        return list;
    }

    @Override // com.huawei.thirdpartservice.wechatdevice.WechatDeviceProviderApi
    public void isBindWechat(final DeviceInfo deviceInfo, final WechatDeviceProviderApi.CheckResultListener<Boolean> checkResultListener) {
        LogUtil.a("WechatDeviceProviderApi", "isBindWechat  deviceInfo uuid= ", deviceInfo.getUuid());
        List<sjy> list = this.e;
        if (list != null) {
            a(list, deviceInfo, checkResultListener);
        } else {
            sid.d(new SupportDeviceResultCallback() { // from class: sju.3
                @Override // com.huawei.ui.thirdpartservice.interactors.callback.SupportDeviceResultCallback
                public void obtainBindDeviceList(List<sjy> list2) {
                    sju.this.e = list2;
                    sju.this.a(list2, deviceInfo, checkResultListener);
                }

                @Override // com.huawei.ui.thirdpartservice.interactors.callback.SupportDeviceResultCallback
                public void onError(int i, String str) {
                    checkResultListener.onError(i, str);
                }
            });
        }
    }

    @Override // com.huawei.thirdpartservice.wechatdevice.WechatDeviceProviderApi
    public void destroy() {
        this.e = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<sjy> list, DeviceInfo deviceInfo, WechatDeviceProviderApi.CheckResultListener<Boolean> checkResultListener) {
        if (!CollectionUtils.d(list) && sii.e(deviceInfo, list) != null) {
            checkResultListener.onResult(true);
        }
        checkResultListener.onResult(false);
    }

    @Override // com.huawei.thirdpartservice.wechatdevice.WechatDeviceProviderApi
    public void bindWechat(Context context, DeviceInfo deviceInfo) {
        LogUtil.a("WechatDeviceProviderApi", "isBindWechat  deviceInfo deviceName= ", deviceInfo.getDeviceName(), ",mDeviceIdentify:", deviceInfo.getDeviceIdentify());
        destroy();
        AppRouter.b("/FeatureDataOpen/WeChatDeviceAuthorizeActivity").e("name", deviceInfo.getDeviceName()).e("identify", deviceInfo.getDeviceIdentify()).e("isAuthorized", false).c(context);
    }

    @Override // com.huawei.thirdpartservice.wechatdevice.WechatDeviceProviderApi
    public void jumpToWechatDeviceActivity(Context context) {
        destroy();
        LogUtil.a("WechatDeviceProviderApi", "jumpToWechatDeviceActivity");
        AppRouter.b("/FeatureDataOpen/WeChatDeviceActivity").c(context);
    }
}
