package defpackage;

import android.util.Log;
import com.huawei.hihealth.error.HiHealthError;
import com.huawei.hihealth.listener.ResultCallback;
import java.util.HashSet;
import java.util.Set;

/* loaded from: classes.dex */
public class ifc {

    /* renamed from: a, reason: collision with root package name */
    private static final Set<String> f13331a;

    static {
        HashSet hashSet = new HashSet();
        f13331a = hashSet;
        hashSet.add("Gender");
        hashSet.add("Birthday");
        hashSet.add("Height");
        hashSet.add("Weight");
    }

    public static boolean c(idy idyVar, ResultCallback resultCallback) {
        if ((idyVar.getType() != 10002 && idyVar.getType() != 10006) || idyVar.getStartTime() == idyVar.getEndTime()) {
            return true;
        }
        Log.w("DataValidateUtil", "startTime is not equal to endTime");
        resultCallback.onResultHandler(2, HiHealthError.e(2));
        return false;
    }
}
