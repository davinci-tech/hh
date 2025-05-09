package defpackage;

import android.content.Context;
import com.huawei.wear.oversea.packageinfo.PackageUtilApi;
import com.huawei.wear.oversea.router.RouterRequest;

/* loaded from: classes7.dex */
public class ste {
    public static PackageUtilApi a(Context context) {
        suv b = sus.d().b(new RouterRequest(context).e("com.huawei.wallet.nfc").d("CommonWearProvider").b("CommonWearCreateAction"), null);
        if (b.e("WearPackageUtil") instanceof PackageUtilApi) {
            return (PackageUtilApi) b.e("WearPackageUtil");
        }
        return null;
    }
}
