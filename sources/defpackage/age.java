package defpackage;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.huawei.appgallery.markethomecountrysdk.b.a.a.e;
import com.huawei.appgallery.serviceverifykit.api.ServiceVerifyKit;
import java.util.Iterator;

/* loaded from: classes3.dex */
public class age {
    public static String d(Context context) {
        String str;
        try {
            ServiceVerifyKit.Builder builder = new ServiceVerifyKit.Builder();
            builder.b(context).hN_(new Intent("com.huawei.appmarket.appmarket.intent.action.AppDetail.withdetailId"), ServiceVerifyKit.Builder.ComponentType.ACTIVITY);
            e b = afy.b(context);
            Iterator<String> it = b.b().iterator();
            while (it.hasNext()) {
                builder.a(b.a(), it.next());
            }
            if (afz.e(context) == 2) {
                builder.a("com.huawei.appmarketwear", "CE1EF7188F820973C191227D95D54311ED3A65EC83E37009E898A1C058BBC775");
            }
            str = builder.d();
        } catch (Throwable unused) {
            str = null;
        }
        try {
            Log.d("ServiceVerifyKitUtils", "genVerifiedPackageName, get market packageName from verify kit is: " + str);
        } catch (Throwable unused2) {
            Log.e("ServiceVerifyKitUtils", "genVerifiedPackageName error");
            return str;
        }
        return str;
    }
}
