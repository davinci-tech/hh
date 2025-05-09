package defpackage;

import android.content.Context;
import android.os.RemoteException;
import android.text.TextUtils;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.hihealth.HiDataUpdateOption;
import com.huawei.hihealth.HiThirdIdentity;
import com.huawei.hihealth.IDataOperateListener;
import com.huawei.hihealthservice.updatemanager.command.UpdateCommand;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.operation.ble.BleConstants;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import health.compact.a.utils.StringUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class ivp implements UpdateCommand {
    @Override // com.huawei.hihealthservice.updatemanager.command.UpdateCommand
    public void execute(HiDataUpdateOption hiDataUpdateOption, IDataOperateListener iDataOperateListener, Context context) throws RemoteException {
        if (hiDataUpdateOption == null || context == null) {
            return;
        }
        String string = hiDataUpdateOption.getString(BleConstants.KEY_PATH);
        if (StringUtils.g(string)) {
            LogUtil.b("HiH_ParseKitConfigData", "execute path is null");
            return;
        }
        String c = c(string);
        LogUtil.a("HiH_ParseKitConfigData", "execute jsonString is =", c);
        if (StringUtils.i(c)) {
            try {
                JSONArray jSONArray = new JSONArray(c);
                if (jSONArray.length() > 0) {
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i);
                        String string2 = jSONObject.getString("package_name");
                        String string3 = jSONObject.getString(HiAnalyticsConstant.HaKey.BI_KEY_FINGERPRINT);
                        int i2 = jSONObject.getInt("level");
                        HiThirdIdentity hiThirdIdentity = new HiThirdIdentity();
                        hiThirdIdentity.setPackageName(string2);
                        hiThirdIdentity.setFingerprint(string3);
                        hiThirdIdentity.setLevel(i2);
                        ijw.d().c(hiThirdIdentity);
                    }
                }
            } catch (JSONException unused) {
                ReleaseLogUtil.c("HiH_ParseKitConfigData", "ParseKitConfigData.execute: JSONException");
            }
        }
    }

    private String c(String str) {
        String str2;
        FileInputStream fileInputStream;
        String d;
        str2 = "";
        FileInputStream fileInputStream2 = null;
        try {
            try {
                d = CommonUtil.d(str);
            } catch (Throwable th) {
                th = th;
                fileInputStream = null;
            }
        } catch (IOException e) {
            e = e;
        }
        if (TextUtils.isEmpty(d)) {
            return "";
        }
        if (!FileUtils.a(new File(d), d)) {
            LogUtil.h("HiH_ParseKitConfigData", "path_crossing getStringFromFile file is invalid, legalPath ", d);
            return "";
        }
        fileInputStream = new FileInputStream(d);
        try {
            byte[] bArr = new byte[fileInputStream.available()];
            str2 = fileInputStream.read(bArr) > 0 ? new String(bArr, "UTF-8") : "";
            try {
                fileInputStream.close();
            } catch (IOException e2) {
                ReleaseLogUtil.c("HiH_ParseKitConfigData", "getStringFromFile close IOException =", e2.getMessage());
            }
        } catch (IOException e3) {
            e = e3;
            fileInputStream2 = fileInputStream;
            ReleaseLogUtil.c("HiH_ParseKitConfigData", "getStringFromFile IOException = ", e.getMessage());
            if (fileInputStream2 != null) {
                try {
                    fileInputStream2.close();
                } catch (IOException e4) {
                    ReleaseLogUtil.c("HiH_ParseKitConfigData", "getStringFromFile close IOException =", e4.getMessage());
                }
            }
            return str2;
        } catch (Throwable th2) {
            th = th2;
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e5) {
                    ReleaseLogUtil.c("HiH_ParseKitConfigData", "getStringFromFile close IOException =", e5.getMessage());
                }
            }
            throw th;
        }
        return str2;
    }
}
