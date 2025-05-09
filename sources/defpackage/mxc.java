package defpackage;

import android.content.Context;
import com.huawei.haf.router.AppRouter;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginsport.multilingualaudio.AudioResProviderInterface;
import java.io.File;

/* loaded from: classes6.dex */
public class mxc {
    public static String e(Context context) {
        return mxb.c();
    }

    public static mxh a(Context context, String str) {
        return mxh.d(e(context), mxb.a().b(mxb.c()), str, context.getResources().getConfiguration().locale);
    }

    public static boolean d(String str, String str2) {
        AudioResProviderInterface audioResProviderInterface = (AudioResProviderInterface) AppRouter.e(AudioResProviderInterface.ROUTER_PATH_AUDIO_RES_PROVIDER, AudioResProviderInterface.class);
        if (audioResProviderInterface != null) {
            mxa audioResPath = audioResProviderInterface.getAudioResPath(str, mxb.a().b(mxb.c()), str2);
            String e = audioResPath.e();
            String d = audioResPath.d();
            String c = audioResPath.c();
            String b = audioResPath.b();
            return d != null && e != null && c != null && b != null && new File(e).exists() && new File(d).exists() && new File(c).exists() && new File(b).exists();
        }
        LogUtil.h("MultilingualAudioUtil", "service is null");
        return false;
    }

    public static String e(String str, String str2) {
        return drd.d(str2, str + "_" + mxb.a().b(mxb.c()), (String) null);
    }
}
