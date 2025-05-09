package com.huawei.wisesecurity.ucs.credential;

import android.content.Context;
import androidx.core.location.LocationRequestCompat;
import com.huawei.hidatamanager.util.Const;
import com.huawei.wisesecurity.kfs.crypto.codec.Decoder;
import com.huawei.wisesecurity.kfs.validation.constrains.KfsLongRange;
import com.huawei.wisesecurity.kfs.validation.constrains.KfsStringNotEmpty;
import com.huawei.wisesecurity.ucs.credential.entity.AccessKey;
import com.huawei.wisesecurity.ucs.credential.entity.KeyEncryptKey;
import com.huawei.wisesecurity.ucs.credential.nativelib.UcsLib;
import com.huawei.wisesecurity.ucs_credential.k;
import defpackage.ttm;
import defpackage.ttr;
import defpackage.tue;
import defpackage.tvz;
import defpackage.twb;
import defpackage.twc;
import defpackage.twf;
import defpackage.two;
import defpackage.twv;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class Credential {
    private static final String AK = "accessKey";
    private static final String DK = "dataKey";
    private static final byte[] EMPTY_BYTES = new byte[0];
    private static final String EXPIRE_TIME = "expireTime";
    private static final String KEK = "kek";
    private static final String SK = "secretKey";
    private static final String TAG = "Credential";

    @KfsStringNotEmpty
    private String accessKey;
    private AccessKey ak;

    @KfsStringNotEmpty
    private String dataKey;

    @KfsLongRange(max = LocationRequestCompat.PASSIVE_INTERVAL, min = 1)
    private long expireTime;
    private KeyEncryptKey kek;

    @KfsStringNotEmpty
    private String rawKek;

    @KfsStringNotEmpty
    private String secretKey;

    public String toString() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(SK, this.secretKey);
            jSONObject.put(AK, this.accessKey);
            jSONObject.put(DK, this.dataKey);
            jSONObject.put(KEK, this.rawKek);
            jSONObject.put("expireTime", this.expireTime);
            return jSONObject.toString();
        } catch (JSONException e) {
            twb.e(TAG, "Credential toString exception : {0}", e.getMessage());
            return "";
        }
    }

    public byte[] getSecretKeyBytes() {
        return base64DecodeForNative(this.secretKey);
    }

    public String getSecretKey() {
        return this.secretKey;
    }

    public String getRawKek() {
        return this.rawKek;
    }

    public int getKekVersion() {
        return this.kek.getVersion();
    }

    public String getKekString() {
        return this.kek.getKey();
    }

    public byte[] getKekBytes() {
        return base64DecodeForNative(this.kek.getKey());
    }

    public int getKekAlg() {
        return this.kek.getKekAlg();
    }

    public long getExpireTime() {
        return this.expireTime;
    }

    public byte[] getDataKeyBytes() {
        return base64DecodeForNative(this.dataKey);
    }

    public String getDataKey() {
        return this.dataKey;
    }

    public String getAppPkgName() {
        return this.ak.getAppPkgName();
    }

    public String getAppCertFP() {
        return this.ak.getAppCertFP();
    }

    public int getAlg() {
        return this.kek.getAlg();
    }

    public int getAkskVersion() {
        return this.ak.getAkskVersion();
    }

    public String getAccessKey() {
        return this.accessKey;
    }

    public void checkParam() throws twc {
        try {
            tue.d(this);
        } catch (ttr e) {
            StringBuilder e2 = twf.e("credential get param exception : ");
            e2.append(e.getMessage());
            throw new tvz(e2.toString());
        }
    }

    private static String getCty(int i) {
        return i != 0 ? i != 3 ? i != 6 ? i != 7 ? "" : "Huks_EC" : "AndroidKS_EC" : "AndroidKS" : "Kid";
    }

    public static Credential fromString(Context context, String str, twv twvVar) throws twc {
        try {
            Credential credential = new Credential();
            JSONObject jSONObject = new JSONObject(str);
            credential.secretKey = jSONObject.optString(SK);
            credential.dataKey = jSONObject.optString(DK);
            credential.accessKey = jSONObject.optString(AK);
            credential.rawKek = jSONObject.optString(KEK);
            credential.expireTime = jSONObject.optLong("expireTime");
            credential.kek = KeyEncryptKey.fromString(context, credential.rawKek);
            credential.checkParam();
            credential.ak = AccessKey.fromString(credential.accessKey);
            twvVar.b(credential.getAppPkgName()).b(credential.getAkskVersion()).c(getCty(credential.getKekVersion()));
            if (credential.ak.hasAkskVersion()) {
                UcsLib.checkNativeLibrary();
                StringBuilder sb = new StringBuilder();
                if (!UcsLib.checkPkgNameCertFP(context, credential.ak.getAppPkgName(), credential.ak.getAppCertFP(), sb)) {
                    String str2 = "check AppPkgName appCertFP fail " + sb.toString();
                    twb.e(TAG, str2, new Object[0]);
                    throw new twc(1023L, str2);
                }
            }
            k.b(credential).b(credential, context);
            return credential;
        } catch (JSONException e) {
            twb.e(TAG, "parse credentialStr get json exception : {0}", e.getMessage());
            StringBuilder e2 = twf.e("parse credentialStr get json exception : ");
            e2.append(e.getMessage());
            throw new twc(Const.RawDataType.HEALTH_SLEEP_RECORD, e2.toString());
        } catch (twc e3) {
            twb.e(TAG, "parse credentialStr get UCS exception : errorCode : {0} errorMsg : {1}", Long.valueOf(e3.b()), e3.getMessage());
            throw e3;
        } catch (Exception e4) {
            StringBuilder e5 = twf.e("parse credentialStr get exception : ");
            e5.append(e4.getMessage());
            String sb2 = e5.toString();
            throw two.e(TAG, sb2, new Object[0], 2001L, sb2);
        }
    }

    private byte[] base64DecodeForNative(String str) {
        try {
            return Decoder.BASE64.decode(str);
        } catch (ttm unused) {
            return EMPTY_BYTES;
        }
    }

    private Credential() {
    }
}
