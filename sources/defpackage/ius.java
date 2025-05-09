package defpackage;

import android.util.SparseArray;
import com.huawei.haf.application.BaseApplication;
import com.huawei.hihealthservice.sync.syncerrormgr.ErrorHandlingBase;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.operation.OpAnalyticsConstants;
import com.huawei.operation.OperationKey;
import com.huawei.skinner.internal.OnRegisterSkinAttrsListener;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.LinkedHashMap;

/* loaded from: classes.dex */
public class ius {

    /* renamed from: a, reason: collision with root package name */
    private static final SparseArray<ErrorHandlingBase> f13618a;
    private static int c;

    static {
        SparseArray<ErrorHandlingBase> sparseArray = new SparseArray<>(10);
        f13618a = sparseArray;
        c = 0;
        sparseArray.put(1, new iuo());
        sparseArray.put(99, new iuo());
        sparseArray.put(100, new iuo());
        sparseArray.put(101, new iuo());
        sparseArray.put(999, new iuo());
        sparseArray.put(1001, new iul());
        sparseArray.put(1002, new iuo());
        sparseArray.put(1003, new iuo());
        sparseArray.put(1004, new iuo());
        sparseArray.put(1005, new iuo());
        sparseArray.put(30001, new iun());
        sparseArray.put(30002, new iuo());
        sparseArray.put(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN, new ium());
        sparseArray.put(30004, new ium());
        sparseArray.put(30005, new iuo());
        sparseArray.put(30006, new iuo());
        sparseArray.put(30007, new iuo());
        sparseArray.put(201001, new iuo());
        sparseArray.put(9999, new iuo());
    }

    public static boolean a(CloudCommonReponse cloudCommonReponse, boolean z) throws iut {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(8);
        if (cloudCommonReponse == null) {
            linkedHashMap.put("msgCnt", Integer.toString(1));
            linkedHashMap.put("errorCode", Integer.toString(-1));
            ivz.d(BaseApplication.e()).e(OperationKey.HEALTH_APP_SYNC_CLOUD_FAILED_85070023.value(), linkedHashMap, false);
            ReleaseLogUtil.c("HiH_SyncErrorMgr", "checkCloudAns rsp is null");
            throw new iut("SYNC_EX: CLOUD_NO_ANS ");
        }
        int intValue = cloudCommonReponse.getResultCode().intValue();
        if (intValue == 0) {
            return true;
        }
        if (30005 == intValue) {
            iuz.h();
        }
        linkedHashMap.put("msgCnt", Integer.toString(1));
        linkedHashMap.put("errorCode", Integer.toString(intValue));
        linkedHashMap.put(OpAnalyticsConstants.REQUEST_INTERFACE, cloudCommonReponse.getClass().toString());
        linkedHashMap.put(OpAnalyticsConstants.RESULT_DESCRIBE, cloudCommonReponse.getResultDesc());
        ivz.d(BaseApplication.e()).e(OperationKey.HEALTH_APP_SYNC_CLOUD_FAILED_85070023.value(), linkedHashMap, false);
        ReleaseLogUtil.c("HiH_SyncErrorMgr", "checkCloudAns rsp is ", cloudCommonReponse.getClass(), ", resultCode is ", Integer.valueOf(intValue));
        ErrorHandlingBase errorHandlingBase = f13618a.get(intValue);
        if (errorHandlingBase == null) {
            throw new iut("SYNC_EX: UNDEFINE_ERROR ");
        }
        errorHandlingBase.dealError(cloudCommonReponse);
        return false;
    }
}
