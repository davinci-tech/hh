package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import com.google.gson.JsonSyntaxException;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.model.RelativeSportData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.skinner.internal.OnRegisterSkinAttrsListener;
import defpackage.enk;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class enk {
    private static final String b = Integer.toString(20002);

    public static void c(long j, long j2) {
        LogUtil.a("Track_SportDataCacheUtil", "enter queryTrackDetail startTime is ", Long.valueOf(j), " endTime is ", Long.valueOf(j2));
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        hiDataReadOption.setTimeInterval(j, j2);
        hiDataReadOption.setType(new int[]{OnRegisterSkinAttrsListener.REGISTER_BY_SCAN});
        HiHealthManager.d(BaseApplication.e()).readHiHealthData(hiDataReadOption, new AnonymousClass2());
    }

    /* renamed from: enk$2, reason: invalid class name */
    class AnonymousClass2 implements HiDataReadResultListener {
        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResultIntent(int i, Object obj, int i2, int i3) {
        }

        AnonymousClass2() {
        }

        @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
        public void onResult(Object obj, int i, int i2) {
            if (!(obj instanceof SparseArray)) {
                LogUtil.h("Track_SportDataCacheUtil", "onResult !(data instanceof SparseArray)");
                return;
            }
            SparseArray sparseArray = (SparseArray) obj;
            if (sparseArray.size() <= 0) {
                LogUtil.h("Track_SportDataCacheUtil", "gotoTrackDetail map size is 0");
                return;
            }
            Object obj2 = sparseArray.get(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN);
            if (koq.e(obj2, HiHealthData.class)) {
                List list = (List) obj2;
                if (koq.b(list)) {
                    LogUtil.h("Track_SportDataCacheUtil", "gotoTrackDetail list is null");
                    return;
                }
                final HiHealthData hiHealthData = (HiHealthData) list.get(0);
                if (hiHealthData == null) {
                    LogUtil.h("Track_SportDataCacheUtil", "gotoTrackDetail hiHealthData is null");
                } else {
                    ThreadPoolManager.d().execute(new Runnable() { // from class: enl
                        @Override // java.lang.Runnable
                        public final void run() {
                            enk.AnonymousClass2.e(HiHealthData.this);
                        }
                    });
                }
            }
        }

        static /* synthetic */ void e(HiHealthData hiHealthData) {
            MotionPathSimplify motionPathSimplify = new MotionPathSimplify();
            kwz.e(hiHealthData, motionPathSimplify);
            motionPathSimplify.saveDeviceType(hiHealthData.getInt("trackdata_deviceType"));
            motionPathSimplify.saveProductId(hiHealthData.getString("device_prodid"));
            if (motionPathSimplify.hasTrackPoint()) {
                enk.d(motionPathSimplify);
            } else {
                enk.ars_(motionPathSimplify, null);
            }
        }
    }

    public static void d() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: eno
            @Override // java.lang.Runnable
            public final void run() {
                enk.j();
            }
        });
    }

    static /* synthetic */ void j() {
        ReleaseLogUtil.d("Track_SportDataCacheUtil", "clearAllCache");
        String str = b;
        SharedPreferenceManager.d(str, "sportDataCache");
        SharedPreferenceManager.d(str, "sportSaveTimeCache");
        SharedPreferenceManager.d(str, "sportTimeCache");
        nrf.b("sportBitmapCache", true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void ars_(MotionPathSimplify motionPathSimplify, Bitmap bitmap) {
        long requestStartTime = motionPathSimplify.requestStartTime();
        if (c() > requestStartTime) {
            ReleaseLogUtil.d("Track_SportDataCacheUtil", "previous data, discarding!!");
            return;
        }
        String a2 = nrv.a(motionPathSimplify);
        ReleaseLogUtil.e("Track_SportDataCacheUtil", "saveSportDataCache simplySp: ", Long.valueOf(requestStartTime));
        Context e = BaseApplication.e();
        String str = b;
        SharedPreferenceManager.e(e, str, "sportDataCache", a2, (StorageParams) null);
        SharedPreferenceManager.e(str, "sportSaveTimeCache", System.currentTimeMillis());
        SharedPreferenceManager.e(str, "sportTimeCache", requestStartTime);
        nrf.b("sportBitmapCache", true);
        if (bitmap != null) {
            ReleaseLogUtil.e("Track_SportDataCacheUtil", "setSportBitmapCache");
            art_(bitmap);
        }
    }

    public static long c() {
        return SharedPreferenceManager.b(b, "sportTimeCache", 0L);
    }

    public static long a() {
        return SharedPreferenceManager.b(b, "sportSaveTimeCache", 0L);
    }

    public static MotionPathSimplify e() {
        try {
            return (MotionPathSimplify) HiJsonUtil.e(SharedPreferenceManager.b(BaseApplication.e(), b, "sportDataCache"), MotionPathSimplify.class);
        } catch (JsonSyntaxException e) {
            LogUtil.b("Track_SportDataCacheUtil", "getSportDataCache JsonSyntaxException, e is ", e.getMessage());
            return null;
        }
    }

    public static Bitmap arr_() {
        return nrf.cJp_("sportBitmapCache", true);
    }

    private static void art_(Bitmap bitmap) {
        nrf.cJr_("sportBitmapCache", bitmap, Bitmap.CompressFormat.PNG, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(final MotionPathSimplify motionPathSimplify) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (motionPathSimplify.requestSportType() == 512 && koq.c(motionPathSimplify.requestChildSportItems())) {
            for (RelativeSportData relativeSportData : motionPathSimplify.requestChildSportItems()) {
                if (relativeSportData.isHasDetailInfo()) {
                    arrayList.add(Long.valueOf(relativeSportData.getStartTime()));
                    arrayList2.add(Long.valueOf(relativeSportData.getEndTime()));
                }
            }
        } else {
            arrayList.add(Long.valueOf(motionPathSimplify.requestStartTime()));
            arrayList2.add(Long.valueOf(motionPathSimplify.requestEndTime()));
        }
        LogUtil.a("Track_SportDataCacheUtil", "enter getTrackDraw");
        eme.b().getTrackDraw(arrayList, arrayList2, new IBaseResponseCallback() { // from class: enn
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                enk.d(MotionPathSimplify.this, i, obj);
            }
        });
    }

    static /* synthetic */ void d(MotionPathSimplify motionPathSimplify, int i, Object obj) {
        if (i == 0 && (obj instanceof Bitmap)) {
            ars_(motionPathSimplify, (Bitmap) obj);
        } else {
            LogUtil.a("Track_SportDataCacheUtil", "track pic is not back hasTrack is true");
            ars_(motionPathSimplify, null);
        }
    }
}
