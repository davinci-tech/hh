package defpackage;

import android.os.RemoteException;
import health.compact.a.LogUtil;
import java.io.UnsupportedEncodingException;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class soi {
    private soj c = soj.a();

    public void e(soo sooVar, sol solVar) {
        if (sooVar == null || solVar == null || !solVar.at()) {
            LogUtil.a("Unite_KitResponseManagement", "toKitFileConsultInfo callback is null");
            return;
        }
        LogUtil.c("Unite_KitResponseManagement", "enter toKitFileConsultInfo");
        try {
            try {
                solVar.p().onResponse(10001, b(0, sooVar.c(), solVar.v(), solVar.x().getBytes("UTF-8")));
            } catch (RemoteException unused) {
                LogUtil.e("Unite_KitResponseManagement", "toKitFileConsultInfo remote exception");
            }
        } catch (UnsupportedEncodingException unused2) {
            LogUtil.e("Unite_KitResponseManagement", "UnsupportedEncodingException : no support utf-8");
            try {
                solVar.p().onResponse(10001, "");
            } catch (RemoteException unused3) {
                LogUtil.e("Unite_KitResponseManagement", "toKitFileConsultInfo remote exception");
            }
        }
    }

    public void a(sol solVar, byte[] bArr, int i, int i2) {
        if (solVar == null || !solVar.at()) {
            LogUtil.a("Unite_KitResponseManagement", "toKitFrameData callback is null.");
            return;
        }
        String e = sov.e(sov.b(solVar), i);
        sok sokVar = this.c.d().get(e);
        if (sokVar == null) {
            LogUtil.a("Unite_KitResponseManagement", "toKitFrameData retryFileInfo is null.");
            return;
        }
        if (i2 != sokVar.b() + 1) {
            LogUtil.a("Unite_KitResponseManagement", "this frame has return to kit, return.");
            return;
        }
        sokVar.d(i2);
        this.c.d().put(e, sokVar);
        LogUtil.c("Unite_KitResponseManagement", "toKitFrameData enter");
        solVar.q(solVar.aq() + 1);
        String b = b(0, solVar.aq(), bArr);
        LogUtil.c("Unite_KitResponseManagement", "toKitFrameData:", iyq.d(b));
        try {
            solVar.p().onResponse(10002, b);
        } catch (RemoteException unused) {
            LogUtil.e("Unite_KitResponseManagement", "toKitFrameData remote exception");
        } catch (Exception unused2) {
            LogUtil.e("Unite_KitResponseManagement", "toKitFrameData occur exception");
        }
    }

    public String b(int i, int i2, byte[] bArr) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("errorCode", i);
            jSONObject.put("index", i2);
            jSONObject.put("value", blq.d(bArr));
        } catch (JSONException unused) {
            LogUtil.e("Unite_KitResponseManagement", "toFileTransferInfoJson exception");
        }
        LogUtil.c("Unite_KitResponseManagement", "toFileTransferInfoJson json is ", jSONObject.toString());
        return jSONObject.toString();
    }

    private String b(int i, int i2, int i3, byte[] bArr) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("errorCode", i);
            jSONObject.put("maxTransferUnit", i2);
            jSONObject.put("fileSize", i3);
            jSONObject.put("crc", blq.d(bArr));
        } catch (JSONException unused) {
            LogUtil.e("Unite_KitResponseManagement", "toFileConsultInfoJson exception");
        }
        LogUtil.c("Unite_KitResponseManagement", "toFileConsultInfoJson json is ", jSONObject.toString());
        return jSONObject.toString();
    }
}
