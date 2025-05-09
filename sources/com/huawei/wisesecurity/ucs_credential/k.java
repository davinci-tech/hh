package com.huawei.wisesecurity.ucs_credential;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hidatamanager.util.Const;
import com.huawei.wisesecurity.ucs.credential.Credential;
import defpackage.twc;
import defpackage.twg;
import defpackage.two;
import defpackage.tws;
import defpackage.txk;
import defpackage.txo;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes7.dex */
public abstract class k {
    public static byte[] a(Credential credential) throws twc {
        String kekString = credential.getKekString();
        Map<String, byte[]> map = txk.f17403a;
        if (TextUtils.isEmpty(kekString)) {
            throw two.e("KekStore", "getKek param is null.", new Object[0], Const.RawDataType.HEALTH_EVENT_RECORD, "getKek param is null.");
        }
        if (((ConcurrentHashMap) txk.f17403a).containsKey(kekString)) {
            return (byte[]) ((ConcurrentHashMap) txk.f17403a).get(kekString);
        }
        throw new twc(2001L, "kek is empty");
    }

    public abstract byte[] a(Credential credential, Context context) throws twc;

    public void b(Credential credential, Context context) throws twc {
        String kekString = credential.getKekString();
        if (((ConcurrentHashMap) txk.f17403a).containsKey(kekString)) {
            return;
        }
        byte[] a2 = a(credential, context);
        if (TextUtils.isEmpty(kekString) || a2 == null) {
            throw two.e("KekStore", "putKek param is null.", new Object[0], Const.RawDataType.HEALTH_EVENT_RECORD, "putKek param is null.");
        }
        ((ConcurrentHashMap) txk.f17403a).put(kekString, a2);
    }

    public static k b(Credential credential) throws twc {
        int kekVersion = credential.getKekVersion();
        return kekVersion == 3 ? new twg() : (kekVersion == 6 || kekVersion == 7) ? new txo() : new tws();
    }
}
