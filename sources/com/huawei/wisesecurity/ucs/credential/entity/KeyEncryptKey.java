package com.huawei.wisesecurity.ucs.credential.entity;

import android.content.Context;
import com.huawei.hidatamanager.util.Const;
import com.huawei.pluginachievement.manager.model.MedalConstants;
import com.huawei.wisesecurity.kfs.validation.constrains.KfsIntegerRange;
import com.huawei.wisesecurity.kfs.validation.constrains.KfsStringNotEmpty;
import com.huawei.wisesecurity.ucs.credential.nativelib.UcsLib;
import defpackage.ttr;
import defpackage.tue;
import defpackage.twc;
import defpackage.twe;
import defpackage.twf;
import defpackage.twi;
import defpackage.twp;
import defpackage.txm;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes7.dex */
public class KeyEncryptKey {
    private static final int KEK_ALG_GCM = 1;
    private static final String TAG = "KeyEncryptKey";

    @KfsIntegerRange(max = 5, min = 0)
    private int alg;

    @KfsIntegerRange(max = 1, min = 0)
    private int kekAlg;

    @KfsStringNotEmpty
    private String key;
    private int v1;
    private int v2;
    private int version;

    private void checkC1Version(Context context) throws twc {
        if (this.v2 != twi.c("Local-C1-Version", -1, context)) {
            throw new twc(1020L, "kek V2 with C1 version check fail,  please reapply the credential.");
        }
    }

    private void checkSoVersion() throws twc {
        if (this.v1 != ((int) UcsLib.ucsGetSoVersion())) {
            throw new twc(1020L, "kek V1 with so version check fail,  please reapply the credential.");
        }
    }

    private void updateRootKey(Context context) throws twc {
        if (UcsLib.isRootKeyUpdated()) {
            return;
        }
        txm.b(context, new twp());
    }

    public int getVersion() {
        return this.version;
    }

    public int getV2() {
        return this.v2;
    }

    public int getV1() {
        return this.v1;
    }

    public String getKey() {
        return this.key;
    }

    public int getKekAlg() {
        return this.kekAlg;
    }

    public int getAlg() {
        return this.alg;
    }

    public static KeyEncryptKey fromString(Context context, String str) throws twc {
        try {
            KeyEncryptKey keyEncryptKey = new KeyEncryptKey();
            JSONObject jSONObject = new JSONObject(twe.a(str, 0));
            keyEncryptKey.version = jSONObject.getInt("version");
            keyEncryptKey.alg = jSONObject.getInt("alg");
            keyEncryptKey.kekAlg = jSONObject.getInt("kekAlg");
            keyEncryptKey.key = jSONObject.getString(MedalConstants.EVENT_KEY);
            keyEncryptKey.v1 = jSONObject.optInt("v1");
            keyEncryptKey.v2 = jSONObject.optInt("v2");
            tue.d(keyEncryptKey);
            keyEncryptKey.checkVersion(context, keyEncryptKey.version);
            if (keyEncryptKey.kekAlg == 1) {
                return keyEncryptKey;
            }
            throw new twc(1020L, "unsupported kek alg");
        } catch (JSONException e) {
            StringBuilder e2 = twf.e("kek param is not a valid json string : ");
            e2.append(e.getMessage());
            throw new twc(Const.RawDataType.HEALTH_EVENT_RECORD, e2.toString());
        } catch (ttr e3) {
            StringBuilder e4 = twf.e("kek param invalid : ");
            e4.append(e3.getMessage());
            throw new twc(Const.RawDataType.HEALTH_EVENT_RECORD, e4.toString());
        }
    }

    private void checkVersion(Context context, int i) throws twc {
        UcsLib.checkNativeLibrary();
        if (i == 3 || i == 6 || i == 7) {
            return;
        }
        checkSoVersion();
        checkC1Version(context);
        updateRootKey(context);
    }
}
