package defpackage;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Message;
import android.text.TextUtils;
import com.huawei.health.device.wifi.interfaces.CommBaseCallbackInterface;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.device.activity.adddevice.PairingGuideActivity;
import com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler;
import com.huawei.ui.homehealth.qrcode.util.QrCodeDataBase;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

/* loaded from: classes6.dex */
public class ooi extends QrCodeBaseHandler {
    private oog b;

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public void handleMessage(Message message) {
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public void mainHandleMessage(Message message, Activity activity) {
    }

    public ooi(Activity activity, CommBaseCallbackInterface commBaseCallbackInterface) {
        super(activity, commBaseCallbackInterface);
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public QrCodeDataBase parser(String str, Object obj) {
        if (!(obj instanceof String)) {
            opf.deU_(this.mActivity.get());
            return null;
        }
        String str2 = (String) obj;
        LogUtil.c("ScaleQrCodeHandler", "parser newQrResultFull = ", str2);
        if (str2.startsWith("&")) {
            try {
                str2 = URLDecoder.decode(str2.substring(1), "UTF-8");
            } catch (UnsupportedEncodingException unused) {
                LogUtil.b("ScaleQrCodeHandler", "parser UnsupportedEncodingException");
            }
        }
        if (str != null) {
            oog oogVar = new oog(str);
            int parser = oogVar.parser(str2);
            if (parser == -3) {
                LogUtil.b("R_QrCode_ScaleQrCodeHandler", "QR_CODE_DATA_NULL");
            } else if (parser == -2) {
                LogUtil.b("R_QrCode_ScaleQrCodeHandler", "PARSER_ERROR_CODE");
            } else {
                if (parser == 0) {
                    return oogVar;
                }
                LogUtil.b("R_QrCode_ScaleQrCodeHandler", "error: ", Integer.valueOf(parser));
            }
            opf.deU_(this.mActivity.get());
        }
        return null;
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public boolean verify(QrCodeDataBase qrCodeDataBase) {
        if (!(qrCodeDataBase instanceof oog)) {
            LogUtil.b("R_QrCode_ScaleQrCodeHandler", "QrCodeDataBase type error");
            return false;
        }
        this.b = (oog) qrCodeDataBase;
        return !TextUtils.isEmpty(r2.e());
    }

    @Override // com.huawei.ui.homehealth.qrcode.util.QrCodeBaseHandler
    public void execute() {
        Activity activity = this.mActivity.get();
        if (activity == null) {
            LogUtil.b("R_QrCode_ScaleQrCodeHandler", "execute: activity is null");
            return;
        }
        String e = this.b.e();
        if (TextUtils.isEmpty(e)) {
            LogUtil.h("ScaleQrCodeHandler", "productId is null");
            return;
        }
        Intent intent = new Intent(activity, (Class<?>) PairingGuideActivity.class);
        intent.putExtra("kind_id", "HDK_WEIGHT");
        intent.putExtra("pair_guide", "2");
        intent.putExtra("is_scan_to_pair_guide", true);
        ArrayList<String> arrayList = new ArrayList<>(16);
        arrayList.add(e);
        intent.putStringArrayListExtra("uuid_list", arrayList);
        try {
            activity.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("ScaleQrCodeHandler", "execute startActivity ActivityNotFoundException.");
        }
        activity.finish();
    }
}
