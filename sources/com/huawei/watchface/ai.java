package com.huawei.watchface;

import android.text.TextUtils;
import com.huawei.health.R;
import com.huawei.secure.android.common.encrypt.aes.AesGcm;
import com.huawei.secure.android.common.encrypt.utils.BaseKeyUtil;
import com.huawei.secure.android.common.util.HexUtil;
import com.huawei.watchface.utils.CommonUtils;
import com.huawei.watchface.utils.DensityUtil;
import com.huawei.watchface.utils.HwLog;

/* loaded from: classes7.dex */
public class ai {

    /* renamed from: a, reason: collision with root package name */
    private static final String f10891a = "ai";
    private static String b;

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            HwLog.w(f10891a, "decryptStrByGCM -- encodeResult is null");
        }
        if (b == null) {
            synchronized (ai.class) {
                if (b == null) {
                    b = a();
                }
            }
        }
        return AesGcm.decrypt(str, b);
    }

    public static String a() {
        byte[] bArr;
        try {
            bArr = BaseKeyUtil.exportRootKey(DensityUtil.getStringById(R$string.AesGcmUpper), DensityUtil.b(R.array._2130968654_res_0x7f04004e)[0], DensityUtil.getStringById(R$string.next_secret), DensityUtil.getStringById(R$string.agSalt), 32, CommonUtils.z());
        } catch (IllegalArgumentException e) {
            HwLog.i(f10891a, "generateRootKey() e: " + HwLog.printException((Exception) e));
            bArr = null;
        }
        return bArr == null ? "" : HexUtil.byteArray2HexStr(bArr);
    }
}
