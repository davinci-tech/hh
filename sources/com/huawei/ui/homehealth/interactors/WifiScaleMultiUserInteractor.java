package com.huawei.ui.homehealth.interactors;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import com.google.gson.Gson;
import com.huawei.health.R;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.ui.measure.activity.WifiDeviceAuthRequestListActivity;
import com.huawei.health.device.ui.measure.activity.WifiDeviceShareActivity;
import com.huawei.health.device.util.EventBus;
import com.huawei.health.device.wifi.interfaces.WiFiMultiUserPushReceiver;
import com.huawei.health.messagecenter.api.MessageCenterApi;
import com.huawei.health.messagecenter.model.MessageObject;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceAuthorizeByMainUserReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetSubUserAuthMsgReq;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceGetSubUserAuthMsgRsp;
import com.huawei.hwcloudmodel.model.unite.WifiDeviceSubUserAuthMsg;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import defpackage.cjx;
import defpackage.cpa;
import defpackage.csb;
import defpackage.csf;
import defpackage.ctk;
import defpackage.ctq;
import defpackage.cud;
import defpackage.jbs;
import defpackage.koq;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class WifiScaleMultiUserInteractor {
    private static final String[] e = {"sub_user_req_authorize_notification", "authorization_agreed_notification", "authorization_rejected_notification", "wifi_scale_auth_refresh", "device_main_share_to_sub_user"};
    private d d;

    public WifiScaleMultiUserInteractor(Context context) {
        c(context);
    }

    private void c(Context context) {
        d dVar = new d(context);
        this.d = dVar;
        EventBus.d(dVar, 0, e);
    }

    public void b() {
        EventBus.a(this.d, e);
    }

    static class d implements EventBus.ICallback {
        private NoTitleCustomAlertDialog b;
        private String c;
        private boolean d = false;
        private WeakReference<Context> e;

        d(Context context) {
            this.e = new WeakReference<>(context);
        }

        @Override // com.huawei.health.device.util.EventBus.ICallback
        public void onEvent(EventBus.b bVar) {
            WeakReference<Context> weakReference = this.e;
            if (weakReference == null) {
                LogUtil.h("WifiScaleMultiUserInteractor", "mContext is null");
                return;
            }
            Context context = weakReference.get();
            if (context == null) {
                LogUtil.h("WifiScaleMultiUserInteractor", "mContext.get() is null");
                return;
            }
            if (bVar == null) {
                LogUtil.h("WifiScaleMultiUserInteractor", "event is null");
                return;
            }
            String e = bVar.e();
            if (TextUtils.isEmpty(e)) {
                LogUtil.h("WifiScaleMultiUserInteractor", "onEvent() action is empty.");
            } else {
                a(bVar, context, e);
            }
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        private void a(EventBus.b bVar, Context context, String str) {
            char c;
            LogUtil.a("WifiScaleMultiUserInteractor", "dealWifiScaleEventAction action: ", str, " mIsReceiveAgreedMsg: ", Boolean.valueOf(this.d));
            str.hashCode();
            switch (str.hashCode()) {
                case -1661195322:
                    if (str.equals("authorization_rejected_notification")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -1057498825:
                    if (str.equals("sub_user_req_authorize_notification")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case -506166836:
                    if (str.equals("authorization_agreed_notification")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 679556689:
                    if (str.equals("device_main_share_to_sub_user")) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                case 852493699:
                    if (str.equals("wifi_scale_auth_refresh")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            if (c == 0) {
                this.c = dcK_(bVar.Kl_());
                return;
            }
            if (c == 1) {
                dcL_(context, bVar.Kl_());
                return;
            }
            if (c == 2) {
                this.c = dcK_(bVar.Kl_());
                csb.a().e(this.c);
                this.d = true;
                return;
            }
            if (c != 3) {
                if (c == 4) {
                    if (!this.d || TextUtils.isEmpty(this.c)) {
                        return;
                    }
                    a(context, context.getResources().getString(R.string.IDS_messagecenter_device_scale), b(this.c), context.getResources().getString(R.string.IDS_hw_device_wifi_authorized_text));
                    e(this.c);
                    this.d = false;
                    return;
                }
                LogUtil.h("WifiScaleMultiUserInteractor", "event action unknown: ", str);
                return;
            }
            try {
                JSONObject jSONObject = new JSONObject(bVar.Kl_().getString("pushContent"));
                String string = jSONObject.getString("userAccount");
                String string2 = context.getResources().getString(R.string.IDS_device_herm_name);
                String t = cpa.t(jSONObject.getString("prodId"));
                this.c = t;
                if ("b29df4e3-b1f7-4e40-960d-4cfb63ccca05".equals(t)) {
                    string2 = context.getResources().getString(R.string.IDS_device_hag2021_name);
                    LogUtil.a("WifiScaleMultiUserInteractor", "HonourDeviceConstants.DEVICE_HAG2021_WEIGHT scalesName: ", string2);
                }
                a(context, context.getResources().getString(R.string.IDS_messagecenter_device_scale), c(this.c), String.format(Locale.ENGLISH, context.getResources().getString(R.string.IDS_device_share_subuser_authorize_1), string, string2));
            } catch (JSONException unused) {
                LogUtil.b("WifiScaleMultiUserInteractor", "EVEBUS_WIFI_DEVICE_SHARE_TO_SUB_USER JSONException");
            }
        }

        private void e(String str) {
            ctk e = ctq.e(str);
            if (e != null) {
                LogUtil.a("WifiScaleMultiUserInteractor", "sendUserInfo to cloud ");
                csf.c(e.d(), cpa.ae(str));
            }
        }

        private String dcK_(Bundle bundle) {
            if (bundle == null) {
                LogUtil.h("WifiScaleMultiUserInteractor", "getProductId fail, bundle is null");
                return "";
            }
            String string = bundle.getString("pushContent");
            if (TextUtils.isEmpty(string)) {
                LogUtil.b("WifiScaleMultiUserInteractor", "getProductId fail, pushContent is null");
                return "";
            }
            WiFiMultiUserPushReceiver.MessageContent messageContent = (WiFiMultiUserPushReceiver.MessageContent) new Gson().fromJson(string, WiFiMultiUserPushReceiver.MessageContent.class);
            if (messageContent == null) {
                LogUtil.h("WifiScaleMultiUserInteractor", "getProductId fail: MessageContent is null");
                return "";
            }
            return cpa.t(messageContent.getProdId());
        }

        private String dcJ_(Bundle bundle) {
            String dcK_ = dcK_(bundle);
            if (TextUtils.isEmpty(dcK_)) {
                LogUtil.h("WifiScaleMultiUserInteractor", "getDeviceId fail, product id from push is null");
                return "";
            }
            HealthDevice a2 = cjx.e().a(dcK_);
            if (!(a2 instanceof ctk)) {
                LogUtil.h("WifiScaleMultiUserInteractor", "getDeviceId fail, not wifiDevice");
                return "";
            }
            return ((ctk) a2).d();
        }

        private void dcL_(final Context context, Bundle bundle) {
            LogUtil.h("WifiScaleMultiUserInteractor", "getRequestAuthorizationDetail enter");
            final String dcJ_ = dcJ_(bundle);
            if (TextUtils.isEmpty(dcJ_)) {
                LogUtil.h("WifiScaleMultiUserInteractor", "getRequestAuthorizationDetail fail, get devId failed");
                return;
            }
            WifiDeviceGetSubUserAuthMsgReq wifiDeviceGetSubUserAuthMsgReq = new WifiDeviceGetSubUserAuthMsgReq();
            wifiDeviceGetSubUserAuthMsgReq.setDevId(dcJ_);
            jbs.a(context).c(wifiDeviceGetSubUserAuthMsgReq, new ICloudOperationResult<CloudCommonReponse>() { // from class: com.huawei.ui.homehealth.interactors.WifiScaleMultiUserInteractor.d.3
                @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void operationResult(CloudCommonReponse cloudCommonReponse, String str, boolean z) {
                    LogUtil.a("WifiScaleMultiUserInteractor", "getRequestAuthorizationDetail request result: ", Boolean.valueOf(z), str);
                    if (z) {
                        d.this.c(context, (WifiDeviceGetSubUserAuthMsgRsp) new Gson().fromJson(str, WifiDeviceGetSubUserAuthMsgRsp.class), dcJ_);
                    }
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c(Context context, WifiDeviceGetSubUserAuthMsgRsp wifiDeviceGetSubUserAuthMsgRsp, String str) {
            List<WifiDeviceSubUserAuthMsg> authMsgs = wifiDeviceGetSubUserAuthMsgRsp.getAuthMsgs();
            if (koq.b(authMsgs) || cud.a(authMsgs) == 0) {
                LogUtil.h("WifiScaleMultiUserInteractor", "new auth msg list is empty");
            } else {
                Collections.sort(authMsgs);
                d(context, authMsgs.get(0), str, cud.a(authMsgs));
            }
        }

        private void d(Context context, final WifiDeviceSubUserAuthMsg wifiDeviceSubUserAuthMsg, final String str, final int i) {
            final Activity activity = BaseApplication.getActivity();
            if (activity == null || activity.isFinishing()) {
                LogUtil.h("WifiScaleMultiUserInteractor", "no activity on top");
                a(context, context.getResources().getString(R.string.IDS_messagecenter_device_scale), d(str), b(context, wifiDeviceSubUserAuthMsg.getSubUserNickName()));
                return;
            }
            activity.runOnUiThread(new Runnable() { // from class: com.huawei.ui.homehealth.interactors.WifiScaleMultiUserInteractor.d.1
                @Override // java.lang.Runnable
                public void run() {
                    if (i == 1) {
                        LogUtil.a("WifiScaleMultiUserInteractor", "one sub user request");
                        d.this.dcN_(activity, wifiDeviceSubUserAuthMsg, str);
                    } else {
                        LogUtil.a("WifiScaleMultiUserInteractor", "multi sub user request");
                        d.this.dcM_(activity, wifiDeviceSubUserAuthMsg, str);
                    }
                }
            });
        }

        private String d(String str) {
            return "messagecenter://wifi_device_auth_list?devId=" + str;
        }

        private String b(String str) {
            return "messagecenter://device_management_page?proId=" + str;
        }

        private String c(String str) {
            return "messagecenter://wifi_device_auth_release?proId=" + str;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void dcM_(final Activity activity, WifiDeviceSubUserAuthMsg wifiDeviceSubUserAuthMsg, final String str) {
            LogUtil.a("WifiScaleMultiUserInteractor", "showMultiRequestDlg");
            String format = String.format(Locale.ENGLISH, activity.getString(R.string.IDS_hw_device_wifi_subuser_request_authorize_multi), wifiDeviceSubUserAuthMsg.getSubUserNickName());
            NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.b;
            if (noTitleCustomAlertDialog != null) {
                if (noTitleCustomAlertDialog.isShowing()) {
                    this.b.dismiss();
                }
                this.b = null;
            }
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(activity);
            builder.e(format).czz_(R$string.IDS_settings_button_cancal, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.interactors.WifiScaleMultiUserInteractor.d.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a("WifiScaleMultiUserInteractor", "dealMultiRequest: clicked cancel");
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).czC_(R$string.IDS_settings_button_ok, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.interactors.WifiScaleMultiUserInteractor.d.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a("WifiScaleMultiUserInteractor", "dealMultiRequest: clicked ok");
                    Intent intent = new Intent(activity, (Class<?>) WifiDeviceAuthRequestListActivity.class);
                    intent.putExtra("dev_id", str);
                    try {
                        activity.startActivity(intent);
                    } catch (ActivityNotFoundException unused) {
                        LogUtil.b("WifiScaleMultiUserInteractor", "showMultiRequestDlg startActivity ActivityNotFoundException.");
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            NoTitleCustomAlertDialog e = builder.e();
            this.b = e;
            e.setCancelable(false);
            this.b.show();
        }

        private String b(Context context, String str) {
            return String.format(Locale.ENGLISH, context.getString(R.string.IDS_hw_device_wifi_authorized_request_user), str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void dcN_(final Activity activity, final WifiDeviceSubUserAuthMsg wifiDeviceSubUserAuthMsg, final String str) {
            LogUtil.a("WifiScaleMultiUserInteractor", "showSingleRequestDlg");
            String format = String.format(Locale.ENGLISH, activity.getString(R.string.IDS_hw_device_wifi_subuser_request_authorize_single), wifiDeviceSubUserAuthMsg.getSubUserNickName());
            NoTitleCustomAlertDialog noTitleCustomAlertDialog = this.b;
            if (noTitleCustomAlertDialog != null) {
                if (noTitleCustomAlertDialog.isShowing()) {
                    this.b.dismiss();
                }
                this.b = null;
            }
            NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(activity);
            builder.e(format).czz_(R.string.IDS_hw_device_wifi_subuser_request_authorize_reject, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.interactors.WifiScaleMultiUserInteractor.d.7
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a("WifiScaleMultiUserInteractor", "dealMultiRequest: clicked cancel");
                    d.this.c((Context) activity, wifiDeviceSubUserAuthMsg, str, false);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).czC_(R.string.IDS_hw_device_wifi_subuser_request_authorize_agree, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.interactors.WifiScaleMultiUserInteractor.d.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a("WifiScaleMultiUserInteractor", "dealMultiRequest: clicked ok");
                    d.this.c((Context) activity, wifiDeviceSubUserAuthMsg, str, true);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
            NoTitleCustomAlertDialog e = builder.e();
            this.b = e;
            e.setCancelable(false);
            this.b.show();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void c(final Context context, WifiDeviceSubUserAuthMsg wifiDeviceSubUserAuthMsg, final String str, final boolean z) {
            LogUtil.a("WifiScaleMultiUserInteractor", "replyAuthRequest, is agree: ", Boolean.valueOf(z));
            WifiDeviceAuthorizeByMainUserReq wifiDeviceAuthorizeByMainUserReq = new WifiDeviceAuthorizeByMainUserReq();
            wifiDeviceAuthorizeByMainUserReq.setSubHuid(wifiDeviceSubUserAuthMsg.getSubHuid());
            wifiDeviceAuthorizeByMainUserReq.setDevId(str);
            wifiDeviceAuthorizeByMainUserReq.setIntent(z ? 1 : 2);
            jbs.a(context).a(wifiDeviceAuthorizeByMainUserReq, new ICloudOperationResult<CloudCommonReponse>() { // from class: com.huawei.ui.homehealth.interactors.WifiScaleMultiUserInteractor.d.9
                @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void operationResult(CloudCommonReponse cloudCommonReponse, String str2, boolean z2) {
                    LogUtil.a("WifiScaleMultiUserInteractor", "getRequestAuthorizationDetail request result: ", Boolean.valueOf(z2), str2);
                    cud.e(context, cloudCommonReponse.getResultCode().intValue(), z2, z);
                    if (z) {
                        Intent intent = new Intent(context, (Class<?>) WifiDeviceShareActivity.class);
                        intent.putExtra("deviceId", str);
                        intent.putExtra("productId", d.this.c);
                        try {
                            context.startActivity(intent);
                        } catch (ActivityNotFoundException unused) {
                            LogUtil.b("WifiScaleMultiUserInteractor", "replyAuthRequest startActivity ActivityNotFoundException.");
                        }
                    }
                }
            });
        }

        private void a(Context context, String str, String str2, String str3) {
            LogUtil.a("WifiScaleMultiUserInteractor", "showAuthNotify detailUri: ", str2);
            MessageObject messageObject = new MessageObject();
            messageObject.setFlag(0);
            messageObject.setExpireTime(System.currentTimeMillis() + 86400000);
            messageObject.setPosition(2);
            messageObject.setNotified(0);
            messageObject.setCreateTime(System.currentTimeMillis());
            messageObject.setMsgId("9145");
            messageObject.setModule("WIFI_NOTIFICATION_MODULE_AUTH");
            messageObject.setMsgContent(str3);
            messageObject.setDetailUri(str2);
            messageObject.setMsgTitle(str);
            messageObject.setWeight(1);
            LogUtil.a("WifiScaleMultiUserInteractor", "start data notification");
            MessageCenterApi messageCenterApi = (MessageCenterApi) Services.c("PluginMessageCenter", MessageCenterApi.class);
            messageCenterApi.showNotification(context, messageObject);
            messageCenterApi.insertMessage(messageObject);
        }
    }
}
