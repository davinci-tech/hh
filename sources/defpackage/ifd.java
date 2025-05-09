package defpackage;

import android.content.Context;
import android.util.Log;
import com.huawei.hihealth.HiHealthKitApi;
import com.huawei.hihealth.HiHealthKitExtendApi;
import com.huawei.hihealth.listener.CapabilityResultCallback;
import com.huawei.hihealth.option.HiHealthCapabilityQuery;
import com.huawei.hihealthkit.context.OutOfBandContext;

/* loaded from: classes8.dex */
public class ifd {
    public static void e(Context context, HiHealthCapabilityQuery hiHealthCapabilityQuery, CapabilityResultCallback capabilityResultCallback) {
        Log.i("HiHealthylivingApi", "enter query");
        if (capabilityResultCallback == null) {
            Log.w("HiHealthylivingApi", "callback is null");
            return;
        }
        if (context == null) {
            Log.w("HiHealthylivingApi", "context is null");
            capabilityResultCallback.onResultHandler(4, "context is null");
            return;
        }
        if (hiHealthCapabilityQuery == null) {
            Log.w("HiHealthylivingApi", "query is null");
            capabilityResultCallback.onResultHandler(2, "query is null");
        } else if (!ife.a(context, ife.e())) {
            capabilityResultCallback.onResultHandler(1, "Health application does not exist");
        } else if (context instanceof OutOfBandContext) {
            HiHealthKitExtendApi.d((OutOfBandContext) context).c(hiHealthCapabilityQuery, capabilityResultCallback);
        } else {
            HiHealthKitApi.c(context).c(hiHealthCapabilityQuery, capabilityResultCallback);
        }
    }
}
