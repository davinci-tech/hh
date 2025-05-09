package defpackage;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.R;
import com.huawei.health.device.wifi.interfaces.CommBaseCallbackInterface;
import com.huawei.hwcloudmodel.callback.ICloudOperationResult;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.CommonDialog21;
import com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler;
import com.huawei.ui.homehealth.qrcode.util.QrCodeDataBase;
import com.huawei.ui.main.stories.healthzone.model.GetHealthZoneVerifyCodeUserReq;
import java.util.regex.PatternSyntaxException;

/* loaded from: classes6.dex */
public class ooa extends QrCodeBaseHandler {
    private CommonDialog21 b;
    private onz d;

    public ooa(Activity activity, CommBaseCallbackInterface commBaseCallbackInterface) {
        super(activity, commBaseCallbackInterface);
        initHandlerThread("FamilyQrCodeHandler");
    }

    private String d(String str) {
        try {
            LogUtil.c("FamilyQrCodeHandler", "getQrContent qrResult = ", str);
            return str.split("&")[1].substring(2);
        } catch (IndexOutOfBoundsException | PatternSyntaxException e) {
            LogUtil.b("R_QrCode_FamilyQrCodeHandler", "getQrContent exception = ", e.getMessage());
            if (this.mActivity != null) {
                opf.deU_(this.mActivity.get());
            }
            return null;
        }
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public QrCodeDataBase parser(String str, Object obj) {
        if (str == null || !(obj instanceof String)) {
            return null;
        }
        onz onzVar = new onz(str);
        int parser = onzVar.parser(d((String) obj));
        if (parser == -3) {
            LogUtil.b("R_QrCode_FamilyQrCodeHandler", "QR_CODE_DATA_NULL");
        } else if (parser == -2) {
            LogUtil.b("R_QrCode_FamilyQrCodeHandler", "PARSER_ERROR_CODE");
        } else {
            if (parser == 0) {
                LogUtil.a("FamilyQrCodeHandler", "parse success");
                return onzVar;
            }
            LogUtil.b("R_QrCode_FamilyQrCodeHandler", "error: ", Integer.valueOf(parser));
        }
        opf.deU_(this.mActivity.get());
        return null;
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public boolean verify(QrCodeDataBase qrCodeDataBase) {
        if (!(qrCodeDataBase instanceof onz)) {
            LogUtil.b("R_QrCode_FamilyQrCodeHandler", "QrCodeDataBase type error");
            return false;
        }
        this.d = (onz) qrCodeDataBase;
        return !TextUtils.isEmpty(r2.a());
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public void execute() {
        this.mMainThreadHandler.sendEmptyMessage(20001);
        this.mHandler.sendEmptyMessage(2001);
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public void handleMessage(Message message) {
        if (message == null) {
            LogUtil.b("R_QrCode_FamilyQrCodeHandler", "handleMessage msg is null");
        } else {
            if (message.what != 2001) {
                return;
            }
            b();
            LogUtil.a("FamilyQrCodeHandler", "get user id");
        }
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public void mainHandleMessage(Message message, Activity activity) {
        if (message == null || activity == null) {
            LogUtil.b("R_QrCode_FamilyQrCodeHandler", "mainHandleMessage msg or activity is null");
            return;
        }
        switch (message.what) {
            case 20001:
                LogUtil.a("FamilyQrCodeHandler", "show scanning dialog");
                ddT_(R.string.IDS_device_wifi_my_qrcode_add_member_loading, activity);
                break;
            case 20002:
                d();
                opf.deU_(this.mActivity.get());
                break;
            case 20003:
                nrh.b(activity.getApplicationContext(), R.string._2130841762_res_0x7f0210a2);
                d();
                ddS_(activity);
                break;
            case 20004:
                d();
                Bundle bundle = new Bundle();
                bundle.putString("verifyCode", this.d.a());
                AppRouter.b("/PluginHealthZone/FamilyHealthTempActivity").zF_(bundle).c(activity);
                activity.finish();
                break;
            case 20005:
                d();
                opf.deV_(this.mActivity.get());
                break;
        }
    }

    private void ddT_(int i, Activity activity) {
        String string = activity.getString(i);
        if (this.b == null) {
            this.b = CommonDialog21.a(activity);
        }
        this.b.setCancelable(false);
        this.b.e(string);
        this.b.a();
    }

    private void d() {
        CommonDialog21 commonDialog21 = this.b;
        if (commonDialog21 == null || !commonDialog21.isShowing()) {
            return;
        }
        this.b.dismiss();
        this.b = null;
    }

    private void ddS_(Activity activity) {
        if (activity == null) {
            return;
        }
        activity.finish();
    }

    private void b() {
        LogUtil.a("FamilyQrCodeHandler", "get user id from cloud");
        GetHealthZoneVerifyCodeUserReq getHealthZoneVerifyCodeUserReq = new GetHealthZoneVerifyCodeUserReq();
        getHealthZoneVerifyCodeUserReq.setVerifyCode(this.d.a());
        rbm.a(getHealthZoneVerifyCodeUserReq, new ICloudOperationResult<rbb>() { // from class: ooa.1
            @Override // com.huawei.hwcloudmodel.callback.ICloudOperationResult
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void operationResult(rbb rbbVar, String str, boolean z) {
                if (z) {
                    if (rbbVar == null || rbbVar.getResultCode().intValue() != 0) {
                        ooa.this.mMainThreadHandler.sendEmptyMessage(20002);
                        return;
                    } else {
                        ooa.this.mMainThreadHandler.sendMessage(Message.obtain(ooa.this.mMainThreadHandler, 20004, Long.valueOf(rbbVar.c().longValue())));
                        return;
                    }
                }
                if (rbbVar == null || rbbVar.getResultCode().intValue() != 30000004) {
                    ooa.this.mMainThreadHandler.sendEmptyMessage(20003);
                } else {
                    ooa.this.mMainThreadHandler.sendEmptyMessage(20005);
                }
            }
        });
    }
}
