package com.huawei.wisesecurity.ucs.credential.entity;

import com.huawei.hidatamanager.util.Const;
import com.huawei.wisesecurity.kfs.validation.constrains.KfsStringNotEmpty;
import defpackage.ttr;
import defpackage.tue;
import defpackage.twc;
import defpackage.twe;
import defpackage.twf;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class AccessKey {
    private static final String AKSK_VERSION = "akskVersion";
    private static final String APP_CERT_FP = "appCertFP";
    private static final String APP_PKG_NAME = "appPkgName";
    private int akskVersion;

    @KfsStringNotEmpty
    private String appCertFP;

    @KfsStringNotEmpty
    private String appPkgName;

    public boolean hasAkskVersion() {
        return this.akskVersion >= 1;
    }

    public String getAppPkgName() {
        return this.appPkgName;
    }

    public String getAppCertFP() {
        return this.appCertFP;
    }

    public int getAkskVersion() {
        return this.akskVersion;
    }

    public static AccessKey fromString(String str) throws twc {
        try {
            AccessKey accessKey = new AccessKey();
            JSONObject jSONObject = new JSONObject(twe.a(str, 0));
            accessKey.akskVersion = jSONObject.optInt(AKSK_VERSION);
            accessKey.appPkgName = jSONObject.optString(APP_PKG_NAME);
            accessKey.appCertFP = jSONObject.optString(APP_CERT_FP);
            if (accessKey.hasAkskVersion()) {
                tue.d(accessKey);
            }
            return accessKey;
        } catch (JSONException e) {
            StringBuilder e2 = twf.e("accessKey param is not a valid json string : ");
            e2.append(e.getMessage());
            throw new twc(Const.RawDataType.HEALTH_EVENT_RECORD, e2.toString());
        } catch (ttr e3) {
            StringBuilder e4 = twf.e("accessKey param invalid : ");
            e4.append(e3.getMessage());
            throw new twc(Const.RawDataType.HEALTH_EVENT_RECORD, e4.toString());
        }
    }
}
