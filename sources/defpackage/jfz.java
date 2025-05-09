package defpackage;

import android.text.TextUtils;
import com.huawei.callback.BluetoothDataReceiveCallback;
import com.huawei.datatype.DeviceCommand;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import health.compact.a.KeyValDbManager;
import health.compact.a.SharedPreferenceManager;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes5.dex */
public class jfz implements BluetoothDataReceiveCallback {
    private static final Object b = new Object();
    private static jfz e;
    private IBaseResponseCallback c = null;

    private jfz() {
        jfq.c().e(35, this);
    }

    private void d(byte[] bArr) {
        List<cwd> e2;
        try {
            e2 = new cwl().a(cvx.d(bArr).substring(4)).e();
        } catch (cwg unused) {
            LogUtil.b("EcgDurationUtil", "parseDurationTlv TlvException");
        }
        if (e2 != null && !e2.isEmpty()) {
            for (cwd cwdVar : e2) {
                if (CommonUtil.w(cwdVar.e()) == 127) {
                    int w = CommonUtil.w(cwdVar.c());
                    LogUtil.c("EcgDurationUtil", "set switch : ", Integer.valueOf(w));
                    a(w);
                    return;
                }
            }
            a(100001);
            return;
        }
        LogUtil.h("EcgDurationUtil", "Tlv is error.");
    }

    private void a(int i) {
        IBaseResponseCallback iBaseResponseCallback = this.c;
        if (iBaseResponseCallback != null) {
            iBaseResponseCallback.d(i, null);
            this.c = null;
        }
    }

    public static jfz d() {
        jfz jfzVar;
        synchronized (b) {
            if (e == null) {
                e = new jfz();
            }
            jfzVar = e;
        }
        return jfzVar;
    }

    public void d(int i, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("EcgDurationUtil", "setEcgDuration callback is null.");
            return;
        }
        if (i == 0 || i == 1) {
            DeviceCommand deviceCommand = new DeviceCommand();
            deviceCommand.setServiceID(35);
            deviceCommand.setCommandID(16);
            StringBuilder sb = new StringBuilder();
            sb.append(cvx.e(1));
            sb.append(cvx.e(1));
            sb.append(cvx.e(i));
            deviceCommand.setDataLen(sb.length() / 2);
            deviceCommand.setDataContent(cvx.a(sb.toString()));
            LogUtil.a("EcgDurationUtil", "5.35.16 send set cmd : ", sb.toString());
            this.c = iBaseResponseCallback;
            jfq.c().b(deviceCommand);
            return;
        }
        iBaseResponseCallback.d(100007, null);
    }

    public int a() {
        String e2 = KeyValDbManager.b(BaseApplication.getContext()).e("test_ecg_nmpa_key");
        if (!TextUtils.isEmpty(e2) && Boolean.parseBoolean(e2)) {
            String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), Integer.toString(35), "storage_ecg_duration_json");
            try {
                if (!TextUtils.isEmpty(b2)) {
                    return new JSONObject(b2).optInt("get_ecg_authentication_switch_from_device", -1);
                }
            } catch (JSONException unused) {
                LogUtil.b("EcgDurationUtil", "showEcgDurationDialog JSONException");
            }
        }
        return 0;
    }

    @Override // com.huawei.callback.BluetoothDataReceiveCallback
    public void onDataReceived(int i, DeviceInfo deviceInfo, byte[] bArr) {
        if (bArr.length < 4) {
            LogUtil.h("EcgDurationUtil", "error tlv");
            return;
        }
        LogUtil.a("EcgDurationUtil", "get result : ", cvx.d(bArr));
        if (bArr[1] == 16) {
            d(bArr);
        }
    }

    public void e(DeviceInfo deviceInfo) {
        jfq.c().f();
    }
}
