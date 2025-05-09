package com.huawei.wisecloud.drmclient.utils;

import android.content.Context;
import com.huawei.secure.android.common.encrypt.aes.AesGcm;
import com.huawei.secure.android.common.encrypt.utils.BaseKeyUtil;
import com.huawei.wisecloud.drmclient.R$array;
import com.huawei.wisecloud.drmclient.R$string;

/* loaded from: classes7.dex */
public class AesGcmUtils {
    private static final String DRM_NEXT_SECRET = "648e4c2039b6c9185d100884621ec3fc62d96eb8005d1d0a7b48171607395fc6";
    private static String secret;

    public static String decryptStrByGCM(String str, Context context) {
        if (secret == null) {
            secret = generateRootKey(context);
        }
        return AesGcm.decrypt(str, secret);
    }

    public static String generateRootKey(Context context) {
        return context == null ? "" : HexUtil.byteArray2HexStr(BaseKeyUtil.exportRootKey(context.getResources().getString(R$string.drm_AesGcmUpper), context.getResources().getStringArray(R$array.drm_RootKey)[0], DRM_NEXT_SECRET, context.getResources().getString(R$string.drm_agSalt), 32, true));
    }
}
