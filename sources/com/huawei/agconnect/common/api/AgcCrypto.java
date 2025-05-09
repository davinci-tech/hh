package com.huawei.agconnect.common.api;

import android.text.TextUtils;
import com.huawei.agconnect.credential.obs.i;
import com.huawei.agconnect.credential.obs.k;
import com.huawei.agconnect.credential.obs.l;
import com.huawei.agconnect.credential.obs.p;
import com.huawei.agconnect.datastore.annotation.ICrypto;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes2.dex */
public class AgcCrypto implements p, ICrypto {
    private static final AgcCrypto INSTANCE = new AgcCrypto();
    private final List<ICrypto> cryptoList;

    @Override // com.huawei.agconnect.credential.obs.p, com.huawei.agconnect.datastore.annotation.ICrypto
    public String encrypt(String str) {
        return this.cryptoList.get(0).encrypt(str);
    }

    @Override // com.huawei.agconnect.credential.obs.p, com.huawei.agconnect.datastore.annotation.ICrypto
    public String decrypt(String str) {
        Iterator<ICrypto> it = this.cryptoList.iterator();
        while (it.hasNext()) {
            String decrypt = it.next().decrypt(str);
            if (!TextUtils.isEmpty(decrypt)) {
                return decrypt;
            }
        }
        return str;
    }

    public static ICrypto get() {
        return INSTANCE;
    }

    public AgcCrypto() {
        ArrayList arrayList = new ArrayList();
        this.cryptoList = arrayList;
        arrayList.add(new l());
        arrayList.add(new k());
        arrayList.add(new i());
    }
}
