package defpackage;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.TextUtils;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.healthcloud.plugintrack.util.HotTrackDrawCustomTarget;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;

/* loaded from: classes4.dex */
public class hjr {
    public static boolean a(MotionPathSimplify motionPathSimplify) {
        if (motionPathSimplify == null || motionPathSimplify.requestExtendDataMap() == null) {
            return false;
        }
        return "2".equals(motionPathSimplify.requestExtendDataMap().get("routeType"));
    }

    public static void d(String str, final HotTrackDrawCustomTarget<Bitmap> hotTrackDrawCustomTarget) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Track_RouteDrawUtils", "hotPathId is empty");
        } else {
            emc.d().getHotPathDetail(str, new UiCallback<enc>() { // from class: hjr.4
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str2) {
                    LogUtil.b("Track_RouteDrawUtils", "can't get hotPathDetail & errorCode = ", Integer.valueOf(i), " & errorInfo is ", str2);
                    HotTrackDrawCustomTarget.this.onLoadFailed(null);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void onSuccess(enc encVar) {
                    LogUtil.c("Track_RouteDrawUtils", "debug onSuccess");
                    if (encVar == null) {
                        LogUtil.h("Track_RouteDrawUtils", "hotPathDetail is null");
                        HotTrackDrawCustomTarget.this.onLoadFailed(null);
                        return;
                    }
                    HotTrackDrawCustomTarget.this.onGetHotTrackDetailInfo(encVar);
                    enm m = encVar.m();
                    if (m == null) {
                        LogUtil.h("Track_RouteDrawUtils", "pathImageInfo is null");
                        HotTrackDrawCustomTarget.this.onLoadFailed(null);
                    } else {
                        nrf.b(m.c(), HotTrackDrawCustomTarget.this);
                    }
                }
            });
        }
    }

    public static hjd c(hjd hjdVar) {
        if (hjdVar == null) {
            return null;
        }
        return ktl.b(hjdVar.b, hjdVar.d) == 1 ? gwf.c(hjdVar) : hjdVar;
    }

    public static int e(MotionPathSimplify motionPathSimplify) {
        return a(motionPathSimplify) ? 1 : 0;
    }

    public static Bitmap bhb_(int i, Bitmap bitmap) {
        if (bitmap == null || i == 0) {
            return null;
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), bitmap.getConfig());
        PorterDuffColorFilter porterDuffColorFilter = new PorterDuffColorFilter(i, PorterDuff.Mode.SRC_IN);
        Paint paint = new Paint();
        paint.setColorFilter(porterDuffColorFilter);
        new Canvas(createBitmap).drawBitmap(bitmap, 0.0f, 0.0f, paint);
        return createBitmap;
    }
}
