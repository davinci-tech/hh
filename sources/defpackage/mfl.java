package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hwlogsmodel.LogUtil;
import java.io.File;
import java.io.IOException;

/* loaded from: classes6.dex */
public class mfl {
    public static boolean e(Context context, String str) {
        String str2;
        if (TextUtils.isEmpty(str) || context == null) {
            return false;
        }
        try {
            str2 = context.getFilesDir().getCanonicalPath() + File.separator + "achievemedal" + File.separator + str + File.separator;
        } catch (IOException unused) {
            LogUtil.b("PLGACHIEVE_AnimationUtil", "fileIsExist IOException");
            str2 = "";
        }
        LogUtil.c("PLGACHIEVE_AnimationUtil", "path=", str2);
        if ("".equals(str2)) {
            return false;
        }
        StringBuilder sb = new StringBuilder(str2);
        StringBuilder sb2 = new StringBuilder(str2);
        StringBuilder sb3 = new StringBuilder(str2);
        StringBuilder sb4 = new StringBuilder(str2);
        sb.append("texture.jpg");
        if (!mlb.d(sb.toString())) {
            return false;
        }
        sb3.append("medal.nXYZ");
        if (!mlb.d(sb3.toString())) {
            return false;
        }
        sb2.append("medal.tST");
        if (!mlb.d(sb2.toString())) {
            return false;
        }
        sb4.append("medal.vXYZ");
        return mlb.d(sb4.toString());
    }
}
