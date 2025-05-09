package defpackage;

import android.util.SparseArray;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.skinner.internal.OnRegisterSkinAttrsListener;
import java.util.List;

/* loaded from: classes4.dex */
public class hps {
    public static void a(long j, long j2, final IBaseResponseCallback iBaseResponseCallback) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(j, j2);
        hiDataReadOption.setType(new int[]{OnRegisterSkinAttrsListener.REGISTER_BY_SCAN});
        HiHealthManager.d(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: hps.5

            /* renamed from: a, reason: collision with root package name */
            final knu f13310a = new knu();

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                IBaseResponseCallback iBaseResponseCallback2;
                if (i != 0 && (iBaseResponseCallback2 = IBaseResponseCallback.this) != null) {
                    iBaseResponseCallback2.d(i, this.f13310a);
                }
                hps.c(obj, this.f13310a, IBaseResponseCallback.this);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void c(Object obj, knu knuVar, IBaseResponseCallback iBaseResponseCallback) {
        if (!(obj instanceof SparseArray)) {
            LogUtil.b("Track_TrackDataRequestUtil", "getTrackDetailData onResult data not instanceof SparseArray");
            return;
        }
        SparseArray sparseArray = (SparseArray) obj;
        if (sparseArray.size() <= 0) {
            LogUtil.b("Track_TrackDataRequestUtil", "getTrackDetailData onResult map size is null");
            return;
        }
        Object obj2 = sparseArray.get(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN);
        if (!koq.e(obj2, HiHealthData.class)) {
            LogUtil.b("Track_TrackDataRequestUtil", "getTrackDetailData onResult obj not instanceof List");
            return;
        }
        List list = (List) obj2;
        if (list.isEmpty()) {
            LogUtil.b("Track_TrackDataRequestUtil", "getTrackDetailData onResult list is empty");
            return;
        }
        HiHealthData hiHealthData = (HiHealthData) list.get(0);
        if (hiHealthData == null) {
            LogUtil.b("Track_TrackDataRequestUtil", "getTrackDetailData onResult hiHealthData is null");
            return;
        }
        MotionPathSimplify motionPathSimplify = new MotionPathSimplify();
        String e = kwz.e(hiHealthData, motionPathSimplify);
        motionPathSimplify.saveDeviceType(hiHealthData.getInt("trackdata_deviceType"));
        motionPathSimplify.saveProductId(hiHealthData.getString("device_prodid"));
        if (e == null) {
            LogUtil.b("Track_TrackDataRequestUtil", "getTrackDetailData onResult fileUrl is null");
            return;
        }
        knuVar.e(e);
        knuVar.b(motionPathSimplify);
        if (iBaseResponseCallback == null) {
            LogUtil.b("Track_TrackDataRequestUtil", "getTrackDetailData callback is null");
        } else {
            iBaseResponseCallback.d(0, knuVar);
        }
    }

    public static void b(long j, long j2, final IBaseResponseCallback iBaseResponseCallback) {
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(j, j2);
        hiDataReadOption.setType(new int[]{OnRegisterSkinAttrsListener.REGISTER_BY_SCAN});
        HiHealthManager.d(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: hps.2
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                if (IBaseResponseCallback.this == null) {
                    LogUtil.b("Track_TrackDataRequestUtil", "requestTrackDetailData callback is null");
                    return;
                }
                if (!(obj instanceof SparseArray)) {
                    LogUtil.h("Track_TrackDataRequestUtil", "requestTrackDetailData onResult data not instanceof SparseArray.");
                    IBaseResponseCallback.this.d(-1, null);
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                if (sparseArray.size() <= 0) {
                    LogUtil.h("Track_TrackDataRequestUtil", "requestTrackDetailData onResult map is empty.");
                    IBaseResponseCallback.this.d(-1, null);
                } else {
                    IBaseResponseCallback.this.d(0, sparseArray.get(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN));
                }
            }
        });
    }
}
