package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.SparseArray;
import androidx.core.content.ContextCompat;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.health.R;
import com.huawei.health.trackprocess.model.GpsPoint;
import com.huawei.healthcloud.plugintrack.ui.map.InterfaceHiMap;
import com.huawei.healthcloud.plugintrack.util.HotTrackDrawCustomTarget;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.FitnessSleepType;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.HwWorkoutServiceUtils;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.skinner.internal.OnRegisterSkinAttrsListener;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Utils;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/* loaded from: classes4.dex */
public class hpu {
    private static int e = Color.rgb(107, FitnessSleepType.HW_FITNESS_NOON, HwWorkoutServiceUtils.HEART_RATE_TRUST_VALUE);
    private static final Object c = new Object();

    private static float c(float f, int i, float f2, float f3) {
        return (i - ((f2 - f3) * f)) / 2.0f;
    }

    private static float d(float f, float f2) {
        if (Math.abs(f) < 1.0E-7f || Math.abs(f2) < 1.0E-7f) {
            LogUtil.a("Track_TrackDrawUtil", "getRatio: width or height is 0");
            return 0.0f;
        }
        float f3 = 1100.0f / f;
        float f4 = 650.0f / f2;
        return f3 < f4 ? f3 : f4;
    }

    private static void boj_(List<PointF> list, Canvas canvas, Paint paint, List<Integer> list2) {
        LogUtil.a("Track_TrackDrawUtil", "paintTrackMap colorList size: ", Integer.valueOf(list2.size()), "pointList size : ", Integer.valueOf(list.size()));
        synchronized (c) {
            for (int i = 0; i < list.size() - 1; i++) {
                boi_(list, canvas, list2, i);
            }
        }
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(-1);
        Paint paint2 = new Paint();
        paint2.setStyle(Paint.Style.FILL);
        paint2.setColor(-1);
        paint2.setAlpha(255);
        paint2.setStrokeWidth(20.0f);
        paint.setAlpha(255);
        paint.setAntiAlias(true);
        canvas.drawCircle(list.get(0).x, list.get(0).y, 30.0f, paint);
        canvas.drawCircle(list.get(list.size() - 1).x, list.get(list.size() - 1).y, 30.0f, paint);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.parseColor("#FF8CD600"));
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setAntiAlias(true);
        paint2.setColor(Color.parseColor("#FFFAB22A"));
        canvas.drawCircle(list.get(0).x, list.get(0).y, 30.0f, paint);
        canvas.drawCircle(list.get(list.size() - 1).x, list.get(list.size() - 1).y, 30.0f, paint2);
    }

    private static void boi_(List<PointF> list, Canvas canvas, List<Integer> list2, int i) {
        Paint paint = new Paint();
        PointF pointF = list.get(i);
        PointF pointF2 = list.get(i + 1);
        Path path = new Path();
        if (pointF == null || c(pointF.x, pointF.y) || pointF2 == null) {
            return;
        }
        path.moveTo(pointF.x, pointF.y);
        path.lineTo(pointF2.x, pointF2.y);
        int i2 = e;
        if (koq.d(list2, i)) {
            i2 = list2.get(i).intValue();
        }
        paint.setColor(i2);
        paint.setStrokeWidth(20.0f);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setStrokeJoin(Paint.Join.ROUND);
        path.close();
        canvas.drawPath(path, paint);
        paint.reset();
    }

    private static boolean c(float f, float f2) {
        return Math.abs(((double) f) + 80.0d) < 1.0000000116860974E-7d && Math.abs(((double) f2) - 90.0d) < 1.0000000116860974E-7d;
    }

    public static void a(List<Long> list, List<Long> list2, Context context, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("Track_TrackDrawUtil", "getTrackDraw callback is null");
        } else {
            if (b(list, list2, context, iBaseResponseCallback)) {
                return;
            }
            b(list, list2, context, iBaseResponseCallback, new TreeMap());
        }
    }

    private static void c(List<Long> list, int i, IBaseResponseCallback iBaseResponseCallback, Map<Integer, Float> map, Map<Long, Map<Long, double[]>> map2) {
        ArrayList arrayList = new ArrayList(20);
        for (Map.Entry<Long, Map<Long, double[]>> entry : map2.entrySet()) {
            if (list.size() > 0 && entry.getKey().equals(list.get(0))) {
                arrayList.add(entry.getValue());
            } else if (list.size() > 1 && entry.getKey().equals(list.get(1))) {
                arrayList.add(entry.getValue());
            } else if (list.size() > 2 && entry.getKey().equals(list.get(2))) {
                arrayList.add(entry.getValue());
            } else {
                LogUtil.c("Track_TrackDrawUtil", "Track more");
            }
        }
        ArrayList arrayList2 = new ArrayList(20);
        a b = b(arrayList, arrayList2);
        if (b == null) {
            LogUtil.a("Track_TrackDrawUtil", "saveDataAndDraw: bound is null");
            iBaseResponseCallback.d(-1, null);
            return;
        }
        ArrayList arrayList3 = new ArrayList(20);
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList3.addAll((List) it.next());
        }
        e = Color.rgb(107, FitnessSleepType.HW_FITNESS_NOON, HwWorkoutServiceUtils.HEART_RATE_TRUST_VALUE);
        e((ArrayList<PointF>) arrayList3, map, i, iBaseResponseCallback, b);
    }

    private static void b(final List<Long> list, List<Long> list2, final Context context, final IBaseResponseCallback iBaseResponseCallback, final Map<Long, Map<Long, double[]>> map) {
        for (int i = 0; i < list.size(); i++) {
            HiDataReadOption hiDataReadOption = new HiDataReadOption();
            hiDataReadOption.setTimeInterval(list.get(i).longValue(), list2.get(i).longValue());
            hiDataReadOption.setType(new int[]{OnRegisterSkinAttrsListener.REGISTER_BY_SCAN});
            HiHealthManager.d(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: hpu.1
                @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                public void onResultIntent(int i2, Object obj, int i3, int i4) {
                }

                @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
                public void onResult(Object obj, int i2, int i3) {
                    IBaseResponseCallback iBaseResponseCallback2;
                    if (i2 != 0 && (iBaseResponseCallback2 = IBaseResponseCallback.this) != null) {
                        iBaseResponseCallback2.d(-1, null);
                        LogUtil.b("Track_TrackDrawUtil", "MSG_TYPE_TRACK_RECOVER_TIME_RECOMMEND Not return valid data");
                    }
                    LogUtil.a("Track_TrackDrawUtil", "onResult: errorCode = ", Integer.valueOf(i2), " anchor = ", Integer.valueOf(i3));
                    hpu.e(obj, context, IBaseResponseCallback.this, (List<Long>) list, (Map<Long, Map<Long, double[]>>) map);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e(Object obj, Context context, IBaseResponseCallback iBaseResponseCallback, List<Long> list, Map<Long, Map<Long, double[]>> map) {
        List<HiHealthData> a2 = a(obj);
        if (koq.b(a2)) {
            LogUtil.h("Track_TrackDrawUtil", "getTrackDetailData list is null or empty");
            iBaseResponseCallback.d(-1, null);
            return;
        }
        HiHealthData hiHealthData = a2.get(0);
        if (hiHealthData == null) {
            LogUtil.h("Track_TrackDrawUtil", "getTrackDetailData onResult hiHealthData is null");
            iBaseResponseCallback.d(-1, null);
            return;
        }
        MotionPathSimplify motionPathSimplify = new MotionPathSimplify();
        String metaData = hiHealthData.getMetaData();
        motionPathSimplify.saveStartTime(hiHealthData.getStartTime());
        HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) trk.b(metaData, HiTrackMetaData.class);
        if (hiTrackMetaData != null) {
            motionPathSimplify.saveSportType(hiTrackMetaData.getSportType());
            motionPathSimplify.savePaceMap(new TreeMap(hiTrackMetaData.getPaceMap()));
        }
        String sequenceFileUrl = hiHealthData.getSequenceFileUrl();
        if (TextUtils.isEmpty(sequenceFileUrl)) {
            LogUtil.h("Track_TrackDrawUtil", "getTrackDetailData onResult fileUrl is empty");
            iBaseResponseCallback.d(-1, null);
            return;
        }
        File file = new File(context.getFilesDir().getPath() + File.separator + sequenceFileUrl);
        if (!file.exists()) {
            iBaseResponseCallback.d(-1, null);
            LogUtil.h("Track_TrackDrawUtil", "getTrackDetailData file not exist");
            return;
        }
        LogUtil.a("Track_TrackDrawUtil", "getTrackDetailDataResult: motionFile size is ", Long.valueOf(file.length()));
        MotionPath c2 = gwk.c(context, sequenceFileUrl);
        if (c2 != null) {
            c(motionPathSimplify, c2, list, map, iBaseResponseCallback);
        } else {
            LogUtil.h("Track_TrackDrawUtil", "getTrackDetailData motion path is null");
            iBaseResponseCallback.d(-1, null);
        }
    }

    private static List<HiHealthData> a(Object obj) {
        if (!(obj instanceof SparseArray)) {
            LogUtil.h("Track_TrackDrawUtil", "handleNullResult onResult data not instanceof SparseArray");
            return null;
        }
        SparseArray sparseArray = (SparseArray) obj;
        if (sparseArray.size() <= 0) {
            LogUtil.h("Track_TrackDrawUtil", "handleNullResult onResult map size is null");
            return null;
        }
        Object obj2 = sparseArray.get(OnRegisterSkinAttrsListener.REGISTER_BY_SCAN);
        if (!koq.e(obj2, HiHealthData.class)) {
            LogUtil.h("Track_TrackDrawUtil", "handleNullResult onResult obj not instanceof List");
            return null;
        }
        List<HiHealthData> arrayList = new ArrayList<>();
        if (obj2 instanceof List) {
            arrayList = (List) obj2;
        }
        if (!koq.b(arrayList)) {
            return arrayList;
        }
        LogUtil.h("Track_TrackDrawUtil", "handleNullResult onResult list is null or empty");
        return null;
    }

    private static void c(MotionPathSimplify motionPathSimplify, MotionPath motionPath, List<Long> list, Map<Long, Map<Long, double[]>> map, IBaseResponseCallback iBaseResponseCallback) {
        LogUtil.a("Track_TrackDrawUtil", "motionPath.requestLbsDataMap()", Integer.valueOf(motionPath.requestLbsDataMap().size()));
        map.put(Long.valueOf(motionPathSimplify.requestStartTime()), motionPath.requestLbsDataMap());
        int requestSportType = list.size() > 1 ? 512 : motionPathSimplify.requestSportType();
        Map<Integer, Float> requestPaceMap = motionPathSimplify.requestPaceMap();
        if (map.size() == list.size()) {
            LogUtil.a("Track_TrackDrawUtil", "sTempBuffer.size()", Integer.valueOf(map.size()), "startTime.size()", Integer.valueOf(list.size()));
            c(list, requestSportType, iBaseResponseCallback, requestPaceMap, map);
        } else {
            LogUtil.a("Track_TrackDrawUtil", "size is different ,sTempBuffer.size()", Integer.valueOf(map.size()), "startTime.size()", Integer.valueOf(list.size()));
            iBaseResponseCallback.d(-1, null);
        }
    }

    private static boolean b(List<Long> list, List<Long> list2, Context context, IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.b("Track_TrackDrawUtil", "callback == null");
            return true;
        }
        if (context == null) {
            LogUtil.b("Track_TrackDrawUtil", "getTrackDraw:context ==null");
            iBaseResponseCallback.d(-1, null);
            return true;
        }
        if (list != null && list2 != null && list.size() == list2.size()) {
            return false;
        }
        LogUtil.b("Track_TrackDrawUtil", "getTrackDraw:time is not correct");
        iBaseResponseCallback.d(-1, null);
        return true;
    }

    public static void b(List<Map<Long, double[]>> list, IBaseResponseCallback iBaseResponseCallback) {
        ArrayList arrayList = new ArrayList(20);
        a b = b(list, arrayList);
        if (b == null) {
            iBaseResponseCallback.d(-1, null);
            return;
        }
        ArrayList arrayList2 = new ArrayList(20);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            arrayList2.addAll((List) it.next());
        }
        e = Color.rgb(66, 150, 248);
        e((ArrayList<PointF>) arrayList2, (Map<Integer, Float>) null, 257, iBaseResponseCallback, b);
    }

    private static void e(ArrayList<PointF> arrayList, Map<Integer, Float> map, int i, IBaseResponseCallback iBaseResponseCallback, a aVar) {
        try {
            Bitmap createBitmap = Bitmap.createBitmap(1190, 740, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            Paint paint = new Paint();
            canvas.drawBitmap(createBitmap, new Matrix(), paint);
            paint.setStrokeWidth(20.0f);
            if (Utils.o()) {
                paint.setColor(Color.parseColor("#FF4296F8"));
            } else {
                paint.setColor(Color.parseColor("#FF8CD600"));
            }
            paint.setAntiAlias(true);
            paint.setStrokeJoin(Paint.Join.ROUND);
            paint.setStyle(Paint.Style.STROKE);
            List arrayList2 = new ArrayList(20);
            if (map == null || map.size() < 1 || Utils.o() || i == 512) {
                arrayList2.add(Integer.valueOf(e));
            } else {
                arrayList2 = e(arrayList.size(), map, i);
            }
            if (arrayList.size() == 0) {
                LogUtil.b("Track_TrackDrawUtil", "pointListFormat is null");
                iBaseResponseCallback.d(-1, null);
                return;
            }
            int size = arrayList.size() > 10000 ? (int) (arrayList.size() * 0.1f) : 200;
            if (arrayList2.size() > arrayList.size() && Math.abs(arrayList.size() - arrayList2.size()) > size) {
                LogUtil.h("Track_TrackDrawUtil", "listAll.size() - colorList.size() > ", Integer.valueOf(size));
                iBaseResponseCallback.d(303, null);
                return;
            }
            ArrayList<PointF> c2 = c(arrayList, aVar);
            if (koq.b(c2, 2)) {
                ReleaseLogUtil.c("Track_TrackDrawUtil", "pointListFormat size is empty ");
                iBaseResponseCallback.d(-1, null);
            } else {
                boj_(c2, canvas, paint, arrayList2);
                iBaseResponseCallback.d(0, createBitmap);
            }
        } catch (OutOfMemoryError unused) {
            ReleaseLogUtil.c("Track_TrackDrawUtil", "error is oom,catch it");
            iBaseResponseCallback.d(-1, null);
        }
    }

    private static ArrayList<PointF> c(ArrayList<PointF> arrayList, a aVar) {
        if (koq.b(arrayList)) {
            return new ArrayList<>(0);
        }
        float d = d(aVar.c - aVar.b, aVar.f13312a - aVar.d);
        ArrayList<PointF> arrayList2 = new ArrayList<>(20);
        if (Math.abs(d) < 1.0E-7f) {
            LogUtil.b("Track_TrackDrawUtil", "adaptToCanvas: ratio is 0");
            return new ArrayList<>(0);
        }
        float c2 = c(d, 1190, aVar.c, aVar.b);
        float c3 = c(d, 740, aVar.f13312a, aVar.d);
        Iterator<PointF> it = arrayList.iterator();
        while (it.hasNext()) {
            PointF next = it.next();
            arrayList2.add(new PointF(((next.x - aVar.b) * d) + c2, ((aVar.f13312a - next.y) * d) + c3));
        }
        return arrayList2;
    }

    private static List<Integer> e(int i, Map<Integer, Float> map, int i2) {
        List<Integer> list;
        int intValue;
        Set<Map.Entry<Integer, Float>> entrySet = map.entrySet();
        float[] fArr = new float[map.size()];
        float[] fArr2 = new float[map.size()];
        Integer[] numArr = new Integer[map.size()];
        int i3 = 0;
        for (Map.Entry<Integer, Float> entry : entrySet) {
            if (entry.getKey().intValue() > 100000) {
                fArr[i3] = entry.getKey().intValue() % 100000;
            } else {
                fArr[i3] = Float.valueOf(entry.getKey().intValue()).floatValue();
            }
            float floatValue = entry.getValue().floatValue();
            fArr2[i3] = floatValue;
            numArr[i3] = Integer.valueOf(gwe.c(floatValue, i2));
            i3++;
        }
        try {
            list = gwe.e(fArr, numArr);
        } catch (OutOfMemoryError unused) {
            LogUtil.b("Track_TrackDrawUtil", "get color List is oom");
            list = null;
        }
        if (list != null) {
            LogUtil.a("Track_TrackDrawUtil", "colorList ", Integer.valueOf(list.size()), " pointSize ", Integer.valueOf(i));
            int size = list.size();
            if (size < i) {
                if (size == 0) {
                    intValue = e;
                } else {
                    intValue = list.get(size - 1).intValue();
                }
                for (int i4 = 0; i4 < i - size; i4++) {
                    list.add(Integer.valueOf(intValue));
                }
            }
            return list;
        }
        LogUtil.b("Track_TrackDrawUtil", "color list is null");
        return new ArrayList(0);
    }

    private static a b(List<Map<Long, double[]>> list, List<List<PointF>> list2) {
        if (koq.b(list)) {
            return null;
        }
        a aVar = new a(-90.0f, -180.0f, 90.0f, 180.0f);
        for (Map<Long, double[]> map : list) {
            if (map == null || map.size() == 0) {
                LogUtil.h("Track_TrackDrawUtil", "multi buffer lbsDataMap is null ");
            } else {
                ArrayList arrayList = new ArrayList(20);
                for (Map.Entry<Long, double[]> entry : map.entrySet()) {
                    if (entry.getValue().length < 2) {
                        LogUtil.b("Track_TrackDrawUtil", "wrong data");
                    } else {
                        float f = (float) entry.getValue()[0];
                        float f2 = (float) entry.getValue()[1];
                        if (!c(f2, f) && f2 <= 180.0f && f2 > -180.0f && f > -90.0f && f < 90.0f) {
                            a(arrayList, f, f2, aVar);
                        }
                    }
                }
                list2.add(arrayList);
            }
        }
        return aVar;
    }

    private static void a(List<PointF> list, float f, float f2, a aVar) {
        if (f2 > aVar.c) {
            aVar.c = f2;
        }
        if (f2 < aVar.b) {
            aVar.b = f2;
        }
        if (f > aVar.f13312a) {
            aVar.f13312a = f;
        }
        if (f < aVar.d) {
            aVar.d = f;
        }
        list.add(new PointF(f2, f));
    }

    /* loaded from: classes8.dex */
    static class a {

        /* renamed from: a, reason: collision with root package name */
        private float f13312a;
        private float b;
        private float c;
        private float d;

        public a(float f, float f2, float f3, float f4) {
            this.f13312a = f;
            this.c = f2;
            this.d = f3;
            this.b = f4;
        }
    }

    public static boolean c(Map<String, String> map) {
        boolean containsKey = map.containsKey("hotPathId");
        LogUtil.a("Track_TrackDrawUtil", "initRunRouteView : isHotPathIdExists = ", Boolean.valueOf(containsKey));
        return containsKey;
    }

    public static void d(String str, final HotTrackDrawCustomTarget<Drawable> hotTrackDrawCustomTarget) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Track_TrackDrawUtil", "hotPathId is empty");
        } else {
            emc.d().getHotPathDetail(str, new UiCallback<enc>() { // from class: hpu.2
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str2) {
                    LogUtil.b("Track_TrackDrawUtil", "can't get hotPathDetail & errorCode = ", Integer.valueOf(i), " & errorInfo is ", str2);
                    HotTrackDrawCustomTarget.this.onLoadFailed(null);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void onSuccess(enc encVar) {
                    if (encVar == null) {
                        LogUtil.h("Track_TrackDrawUtil", "hotPathDetail is null");
                        HotTrackDrawCustomTarget.this.onLoadFailed(null);
                        return;
                    }
                    HotTrackDrawCustomTarget.this.onGetHotTrackDetailInfo(encVar);
                    enm m = encVar.m();
                    if (m == null) {
                        LogUtil.h("Track_TrackDrawUtil", "pathImageInfo is null");
                        HotTrackDrawCustomTarget.this.onLoadFailed(null);
                    } else {
                        String b = m.b();
                        LogUtil.a("Track_TrackDrawUtil", "defaultImageUrl = ", b);
                        nrf.d(b, HotTrackDrawCustomTarget.this);
                    }
                }
            });
        }
    }

    public static void d(List<GpsPoint> list, String str, InterfaceHiMap interfaceHiMap) {
        if (koq.b(list)) {
            LogUtil.h("Track_TrackDrawUtil", "drawHotTrackPath list is null");
            return;
        }
        if (interfaceHiMap == null) {
            LogUtil.h("Track_TrackDrawUtil", "map is null");
            return;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<GpsPoint> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(gwe.c(it.next()));
        }
        List<hjd> c2 = gwe.c(arrayList, str, interfaceHiMap.getMapEngineType());
        hle hleVar = new hle();
        hleVar.e(c2);
        hleVar.c(ContextCompat.getColor(BaseApplication.getContext(), R.color._2131298058_res_0x7f09070a));
        hleVar.a(false);
        hleVar.b(hag.a(4.0f));
        interfaceHiMap.drawLines(hleVar);
    }
}
