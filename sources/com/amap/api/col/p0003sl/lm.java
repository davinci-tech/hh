package com.amap.api.col.p0003sl;

import android.util.Base64;
import com.huawei.hms.network.base.common.trans.FileBinary;
import java.nio.charset.StandardCharsets;

/* loaded from: classes2.dex */
public final class lm {
    public static boolean a(byte[] bArr) {
        if (bArr != null) {
            try {
                mt mtVar = new mt();
                mtVar.b.put("Content-Type", FileBinary.HEAD_VALUE_CONTENT_TYPE_OCTET_STREAM);
                mtVar.b.put("aps_c_src", Base64.encodeToString(mt.a().getBytes(), 2));
                mtVar.b.put("aps_c_key", Base64.encodeToString(mt.b().getBytes(), 2));
                mtVar.d = bArr;
                if (ld.f1321a) {
                    mtVar.f1340a = "http://cgicol.amap.com/collection/collectData?src=baseCol&ver=v74&";
                } else {
                    mtVar.f1340a = (ld.b ? "https://" : "http://").concat("cgicol.amap.com/collection/collectData?src=baseCol&ver=v74&");
                }
                mu a2 = mh.b().a(mtVar);
                byte[] bArr2 = (a2 == null || a2.f1341a != 200) ? null : a2.c;
                if (bArr2 != null) {
                    if ("true".equals(new String(bArr2, StandardCharsets.UTF_8))) {
                        return true;
                    }
                }
            } catch (Exception e) {
                ms.a(e);
            }
        }
        return false;
    }
}
