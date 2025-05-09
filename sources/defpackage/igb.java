package defpackage;

import android.os.RemoteException;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.IAggregateListener;
import com.huawei.hihealth.IAggregateListenerEx;
import com.huawei.hihealth.IAuthorizationListener;
import com.huawei.hihealth.ICommonListener;
import com.huawei.hihealth.IDataClientListener;
import com.huawei.hihealth.IDataOperateListener;
import com.huawei.hihealth.IDataReadResultListener;
import com.huawei.hihealth.IDeleteListenerEx;
import com.huawei.hihealth.IMultiDataClientListener;
import com.huawei.hihealth.IRegisterClientListener;
import com.huawei.hihealth.ISubscribeListener;
import com.huawei.hihealth.IUnSubscribeListener;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class igb {
    public static void b(IDataOperateListener iDataOperateListener, int i, List list) {
        if (iDataOperateListener == null) {
            return;
        }
        try {
            iDataOperateListener.onResult(i, list);
        } catch (RemoteException e) {
            ReleaseLogUtil.d("HiH_HiHealthBinderCallbackResult", "IDataOperateListener setResult  Re = ", e.getMessage(), ",errorCode = ", Integer.valueOf(i), ", message = ", list);
        } catch (Exception e2) {
            ReleaseLogUtil.d("HiH_HiHealthBinderCallbackResult", "IDataOperateListener setResult Exception", ExceptionUtils.d(e2));
        }
    }

    public static void b(ICommonListener iCommonListener, int i, List list) {
        if (iCommonListener == null) {
            return;
        }
        try {
            iCommonListener.onSuccess(i, list);
        } catch (RemoteException e) {
            ReleaseLogUtil.d("HiH_HiHealthBinderCallbackResult", "ICommonListener setSuccess  Re = ", e.getMessage(), ",intent = ", Integer.valueOf(i), ", data = ", list);
        } catch (Exception unused) {
            ReleaseLogUtil.d("HiH_HiHealthBinderCallbackResult", "ICommonListener setSuccess Exception");
        }
    }

    public static void c(ICommonListener iCommonListener, int i, List list) {
        if (iCommonListener == null) {
            return;
        }
        try {
            iCommonListener.onFailure(i, list);
        } catch (RemoteException e) {
            ReleaseLogUtil.d("HiH_HiHealthBinderCallbackResult", "ICommonListener setFail  eR = ", e.getMessage(), ",errCode = ", Integer.valueOf(i), ", errMsg = ", list);
        } catch (Exception unused) {
            ReleaseLogUtil.d("HiH_HiHealthBinderCallbackResult", "ICommonListener setFail Exception");
        }
    }

    public static void d(IAggregateListener iAggregateListener, List list, int i, int i2) {
        if (iAggregateListener == null) {
            return;
        }
        try {
            iAggregateListener.onResult(list, i, i2);
        } catch (RemoteException e) {
            ReleaseLogUtil.d("HiH_HiHealthBinderCallbackResult", "IAggregateListener onResult  e = ", e.getMessage(), ",errorCode = ", Integer.valueOf(i), ", resultType = ", Integer.valueOf(i2));
        }
    }

    public static void e(IAggregateListenerEx iAggregateListenerEx, List list, int i, int i2) {
        if (iAggregateListenerEx == null) {
            return;
        }
        try {
            iAggregateListenerEx.onResult(list, i, i2);
        } catch (RemoteException e) {
            ReleaseLogUtil.d("HiH_HiHealthBinderCallbackResult", "IAggregateListenerEx onResult  e = ", e.getMessage(), ",errorCode = ", Integer.valueOf(i), ", resultType = ", Integer.valueOf(i2));
        }
    }

    public static void c(IDataClientListener iDataClientListener, List list) {
        if (iDataClientListener == null) {
            return;
        }
        try {
            iDataClientListener.onResult(list);
        } catch (RemoteException e) {
            ReleaseLogUtil.d("HiH_HiHealthBinderCallbackResult", "IDataClientListener onResult  e = ", e.getMessage(), ",clientList = ", list);
        }
    }

    public static void e(IMultiDataClientListener iMultiDataClientListener, Map map) {
        if (iMultiDataClientListener == null) {
            return;
        }
        try {
            iMultiDataClientListener.onMultiTypeResult(map);
        } catch (RemoteException e) {
            ReleaseLogUtil.d("HiH_HiHealthBinderCallbackResult", "IMultiDataClientListener onResult  e = ", e.getMessage(), ",clientMap = ", map);
        }
    }

    public static void c(IMultiDataClientListener iMultiDataClientListener, List list) {
        if (iMultiDataClientListener == null) {
            return;
        }
        try {
            iMultiDataClientListener.onMergeTypeResult(list);
        } catch (RemoteException e) {
            ReleaseLogUtil.d("HiH_HiHealthBinderCallbackResult", "IMultiDataClientListener onResult  e = ", e.getMessage(), ",clientList = ", list);
        }
    }

    public static void b(IDataReadResultListener iDataReadResultListener, List list, int i, int i2) {
        if (iDataReadResultListener == null) {
            return;
        }
        try {
            iDataReadResultListener.onResult(list, i, i2);
        } catch (RemoteException e) {
            ReleaseLogUtil.d("HiH_HiHealthBinderCallbackResult", "IDataReadResultListener onResult  e = ", e.getMessage(), ",errorCode = ", Integer.valueOf(i), ", resultType = ", Integer.valueOf(i2));
        }
    }

    public static void a(IRegisterClientListener iRegisterClientListener, HiHealthClient hiHealthClient) {
        if (iRegisterClientListener == null) {
            return;
        }
        try {
            iRegisterClientListener.onResult(hiHealthClient);
        } catch (RemoteException e) {
            ReleaseLogUtil.d("HiH_HiHealthBinderCallbackResult", "IRegisterClientListener onResult  e = ", e.getMessage(), ",client = ", hiHealthClient);
        }
    }

    public static void b(ISubscribeListener iSubscribeListener, List list, List list2) {
        if (iSubscribeListener == null) {
            return;
        }
        try {
            iSubscribeListener.onResult(list, list2);
        } catch (RemoteException e) {
            ReleaseLogUtil.d("HiH_HiHealthBinderCallbackResult", "ISubscribeListener onResult  e = ", e.getMessage(), ",successList = ", list, ",failList = ", list2);
        }
    }

    public static void a(IUnSubscribeListener iUnSubscribeListener, boolean z) {
        if (iUnSubscribeListener == null) {
            return;
        }
        try {
            iUnSubscribeListener.onResult(z);
        } catch (RemoteException e) {
            ReleaseLogUtil.d("HiH_HiHealthBinderCallbackResult", "IUnSubscribeListener onResult  e = ", e.getMessage(), ",isSuccess = ", Boolean.valueOf(z));
        }
    }

    public static void e(IAuthorizationListener iAuthorizationListener, int i, List list) {
        if (iAuthorizationListener == null) {
            return;
        }
        try {
            iAuthorizationListener.onResult(i, list);
        } catch (RemoteException e) {
            ReleaseLogUtil.d("HiH_HiHealthBinderCallbackResult", "IUnSubscribeListener onResult  e = ", e.getMessage(), ",code = ", Integer.valueOf(i), ", authorList = ", list);
        }
    }

    public static void e(IDeleteListenerEx iDeleteListenerEx, Map map) {
        if (iDeleteListenerEx == null) {
            return;
        }
        try {
            iDeleteListenerEx.onResult(map);
        } catch (RemoteException e) {
            ReleaseLogUtil.d("HiH_HiHealthBinderCallbackResult", "IDeleteListenerEx onResult  e = ", e.getMessage(), ", callbackMap = ", map);
        }
    }
}
