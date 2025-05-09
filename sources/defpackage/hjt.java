package defpackage;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.health.healthcloudconfig.listener.DownloadCallback;
import com.huawei.health.servicesui.R$string;
import com.huawei.healthcloud.plugintrack.model.PostureJudgeBean;
import com.huawei.healthcloud.plugintrack.model.SportDetailChartDataType;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwfoundationmodel.trackmodel.MotionPath;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.columnsystem.HealthColumnSystem;
import health.compact.a.CommonUtil;
import health.compact.a.UnitUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class hjt {
    public static Map<Integer, List<Integer>> c(MotionPath motionPath, PostureJudgeBean postureJudgeBean) {
        if (motionPath == null || koq.b(motionPath.requestRealTimePaceList())) {
            return null;
        }
        List<koh> requestRealTimePaceList = motionPath.requestRealTimePaceList();
        HashMap hashMap = new HashMap();
        List<Integer> c = c(postureJudgeBean);
        Iterator<Integer> it = c.iterator();
        while (it.hasNext()) {
            hashMap.put(it.next(), new ArrayList());
        }
        for (koh kohVar : requestRealTimePaceList) {
            if (kohVar != null && kohVar.acquireTime() > 0 && kohVar.a() > 0) {
                int a2 = a(kohVar.a(), c);
                if (hashMap.containsKey(Integer.valueOf(a2)) && hashMap.get(Integer.valueOf(a2)) != null) {
                    ((List) hashMap.get(Integer.valueOf(a2))).add(Integer.valueOf((int) kohVar.acquireTime()));
                }
            }
        }
        d(hashMap, c);
        return hashMap;
    }

    private static void d(Map<Integer, List<Integer>> map, List<Integer> list) {
        List<Integer> list2;
        for (int i = 0; i < list.size() && ((list2 = map.get(list.get(i))) == null || list2.isEmpty()); i++) {
            map.remove(list.get(i));
            LogUtil.a("Track_PostureRangeUtils", "remove Invalid speed data lower", list.get(i));
        }
        for (int size = list.size() - 1; size >= 0; size--) {
            List<Integer> list3 = map.get(list.get(size));
            if (list3 != null && !list3.isEmpty()) {
                return;
            }
            map.remove(list.get(size));
            LogUtil.a("Track_PostureRangeUtils", "remove Invalid speed data upper", list.get(size));
        }
    }

    private static List<Integer> c(PostureJudgeBean postureJudgeBean) {
        ArrayList arrayList = new ArrayList();
        Iterator<PostureJudgeBean.c> it = postureJudgeBean.getPaceList().iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(it.next().a()));
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    private static int a(int i, List<Integer> list) {
        for (int size = list.size() - 1; size >= 0; size--) {
            if (i >= list.get(size).intValue()) {
                return list.get(size).intValue();
            }
        }
        return 0;
    }

    public static void d(nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar, int i, List<gxq> list) {
        if (nreVar == null || koq.b(list)) {
            LogUtil.h("Track_PostureRangeUtils", "addRunningPaceToTableSet with error parameter tableDataSet:", nreVar, " index:", Integer.valueOf(i));
        } else {
            e(nreVar, nreVar.getRowsCount(), i, list);
        }
    }

    public static void e(nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar, int i, int i2, List<gxq> list) {
        if (nreVar == null || i < 0 || koq.b(list)) {
            LogUtil.h("Track_PostureRangeUtils", "putRunningPaceToTableSet with error parameter tableDataSet:", nreVar, " trackRunningSpeed:", Integer.valueOf(i2), " rowIndex:", Integer.valueOf(i));
            return;
        }
        if (BaseApplication.getContext() == null) {
            LogUtil.h("Track_PostureRangeUtils", "putRunningPaceToTableSet with context is null");
            return;
        }
        for (int i3 = 0; i3 < list.size(); i3++) {
            e(nreVar, list.get(i3), i3, i2);
        }
    }

    private static String c(float f, boolean z) {
        if (f <= 0.0f) {
            return "--";
        }
        if (z) {
            return UnitUtil.e((int) f, 1, 0);
        }
        return UnitUtil.e(f, 1, 1);
    }

    private static void e(nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar, gxq gxqVar, int i, int i2) {
        int i3 = i + 1;
        if (i2 == 0) {
            nreVar.putRowHeaderData(i3, 0, new hjc(gxqVar.d()));
            return;
        }
        if (i2 == 1) {
            nreVar.putContentData(i3, 1, new hjc(gxqVar.f()));
            return;
        }
        if (i2 == 2) {
            nreVar.putContentData(i3, 2, new hjc(c(gxqVar.a(), gxqVar.e())));
        } else if (i2 == 3) {
            nreVar.putContentData(i3, 3, new hjc(gxqVar.b()));
        } else {
            LogUtil.h("Track_PostureRangeUtils", "exception index ", Integer.valueOf(i2));
        }
    }

    public static void d(nre<hjj, hjc, hjc, hjc, hjc, hjc> nreVar, SportDetailChartDataType sportDetailChartDataType, Context context) {
        if (context == null) {
            return;
        }
        nreVar.putRowColumnHeaderData(0, 0, new hjc(context.getResources().getString(R$string.IDS_pace)));
        nreVar.putColumnHeaderData(0, 1, new hjj(context.getResources().getString(R$string.sug_total_duration), null));
        nreVar.putColumnHeaderData(0, 2, e(sportDetailChartDataType, context));
        nreVar.putColumnHeaderData(0, 3, a(sportDetailChartDataType, context));
    }

    /* renamed from: hjt$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] c;

        static {
            int[] iArr = new int[SportDetailChartDataType.values().length];
            c = iArr;
            try {
                iArr[SportDetailChartDataType.HANG_TIME.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                c[SportDetailChartDataType.GROUND_CONTACT_TIME.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                c[SportDetailChartDataType.GROUND_HANG_TIME_RATE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private static hjj e(SportDetailChartDataType sportDetailChartDataType, Context context) {
        int i = AnonymousClass3.c[sportDetailChartDataType.ordinal()];
        if (i == 1) {
            return new hjj(context.getResources().getString(R$string.IDS_aw_version2_average_jump_time), null);
        }
        if (i == 2) {
            return new hjj(context.getResources().getString(R$string.IDS_running_posture_avg_ground_contact_time), null);
        }
        if (i == 3) {
            return new hjj(context.getResources().getString(R$string.IDS_motiontrack__average_ground_to_air_ratio), null);
        }
        return new hjj("", null);
    }

    private static hjj a(SportDetailChartDataType sportDetailChartDataType, Context context) {
        int i = AnonymousClass3.c[sportDetailChartDataType.ordinal()];
        if (i == 1) {
            return new hjj(context.getResources().getString(R$string.IDS_aw_version2_hang_time_performance), null);
        }
        if (i == 2) {
            return new hjj(context.getResources().getString(R$string.IDS_aw_version2_contact_time_performance), null);
        }
        if (i == 3) {
            return new hjj(context.getResources().getString(R$string.IDS_aw_version2_hang_time_rate_performance), null);
        }
        return new hjj("", null);
    }

    public static List<gxq> b(Map<Integer, List<Integer>> map, SportDetailChartDataType sportDetailChartDataType, ArrayList<ffs> arrayList, List<PostureJudgeBean> list) {
        ArrayList arrayList2 = new ArrayList();
        List<Integer> c = c(list.get(0));
        if (map == null) {
            return arrayList2;
        }
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            if (entry != null) {
                List<Integer> value = entry.getValue();
                float e = e(value, arrayList, sportDetailChartDataType);
                gxq gxqVar = new gxq(entry.getKey().intValue(), c);
                if (sportDetailChartDataType != SportDetailChartDataType.GROUND_HANG_TIME_RATE) {
                    gxqVar.b(true);
                }
                gxqVar.d(e);
                gxqVar.d(value.size() * 5);
                gxqVar.c(e(e(sportDetailChartDataType, list, gxqVar.c()), e, sportDetailChartDataType));
                arrayList2.add(gxqVar);
            }
        }
        Collections.sort(arrayList2, new Comparator() { // from class: hjv
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return hjt.c((gxq) obj, (gxq) obj2);
            }
        });
        return arrayList2;
    }

    static /* synthetic */ int c(gxq gxqVar, gxq gxqVar2) {
        if (gxqVar == null || gxqVar2 == null) {
            return 1;
        }
        return -Integer.compare(gxqVar.c(), gxqVar2.c());
    }

    public static int e(float f, int i, List<PostureJudgeBean> list, SportDetailChartDataType sportDetailChartDataType) {
        if (i == 0) {
            LogUtil.b("Track_PostureRangeUtils", "avg Pace is 0");
            return 0;
        }
        if (koq.b(list)) {
            LogUtil.b("Track_PostureRangeUtils", "judgeList is empty");
            return 0;
        }
        List<Integer> c = c(list.get(0));
        return e(e(sportDetailChartDataType, list, new gxq(a(i, c), c).c()), f, sportDetailChartDataType);
    }

    private static List<Float> e(SportDetailChartDataType sportDetailChartDataType, List<PostureJudgeBean> list, int i) {
        ArrayList arrayList = new ArrayList();
        int i2 = AnonymousClass3.c[sportDetailChartDataType.ordinal()];
        String str = i2 != 1 ? i2 != 2 ? i2 != 3 ? "" : "contact_hang_time_rate" : "ground_contact_time" : "hang_time";
        if (list != null && !list.isEmpty()) {
            PostureJudgeBean postureJudgeBean = null;
            for (PostureJudgeBean postureJudgeBean2 : list) {
                if (str.equals(postureJudgeBean2.getType())) {
                    postureJudgeBean = postureJudgeBean2;
                }
            }
            if (postureJudgeBean == null) {
                return arrayList;
            }
            for (PostureJudgeBean.c cVar : postureJudgeBean.getPaceList()) {
                if (cVar.a() == i) {
                    return cVar.c();
                }
            }
        }
        return arrayList;
    }

    private static float e(List<Integer> list, ArrayList<ffs> arrayList, SportDetailChartDataType sportDetailChartDataType) {
        Iterator<ffs> it = arrayList.iterator();
        int i = 0;
        float f = 0.0f;
        while (it.hasNext()) {
            ffs next = it.next();
            int g = ((int) next.g()) / 5;
            if (next.g() % 5 > 3) {
                g++;
            }
            if (list.contains(Integer.valueOf(g * 5))) {
                float c = c(next, sportDetailChartDataType);
                if (c > 0.0f) {
                    f += c;
                    i++;
                }
            }
        }
        if (i > 0) {
            return (float) UnitUtil.a(f / i, 1);
        }
        return 0.0f;
    }

    private static float c(ffs ffsVar, SportDetailChartDataType sportDetailChartDataType) {
        int i = AnonymousClass3.c[sportDetailChartDataType.ordinal()];
        if (i == 1) {
            return ffsVar.l();
        }
        if (i == 3) {
            return e(ffsVar.o());
        }
        return ffsVar.b();
    }

    private static int c(List<Float> list, float f) {
        for (int i = 0; i < list.size(); i++) {
            if (f < list.get(i).floatValue()) {
                return i;
            }
        }
        return list.size();
    }

    private static int e(List<Float> list, float f, SportDetailChartDataType sportDetailChartDataType) {
        if (sportDetailChartDataType == SportDetailChartDataType.HANG_TIME) {
            return c(list, f) - 1;
        }
        return 3 - c(list, f);
    }

    public static float[] e(HealthColumnSystem healthColumnSystem, boolean z) {
        if (healthColumnSystem.f() > 4) {
            LogUtil.a("Track_PostureRangeUtils", "getTotalColumnCount > 4) ");
            return new float[]{78.25f, 62.25f, 68.5f, 71.75f};
        }
        LogUtil.a("Track_PostureRangeUtils", "getTotalColumnCount < 4) ");
        return z ? new float[]{1.1f, 1.1f, 0.9f, 0.9f} : new float[]{1.0f, 1.0f, 1.0f, 1.0f};
    }

    public static String e(Context context) {
        String c = CommonUtil.c(drd.d("com.huawei.health_Sport_Common", "posture_judge_update", "json"));
        if (TextUtils.isEmpty(c)) {
            LogUtil.h("Track_PostureRangeUtils", "cloudConfigPath is illegal!");
            return null;
        }
        String e = mrx.e(new File(c));
        LogUtil.h("Track_PostureRangeUtils", "EzPluginHelper.readFileToData jsonData = ", e);
        if (!TextUtils.isEmpty(e)) {
            return e;
        }
        String d = d(context);
        LogUtil.h("Track_PostureRangeUtils", "parseAssestsJsonFile jsonData = ", d);
        return d;
    }

    private static String d(Context context) {
        try {
            return b(context.getAssets().open("posture_judge.json"));
        } catch (IOException unused) {
            LogUtil.b("Track_PostureRangeUtils", "getString IOException");
            return "";
        }
    }

    private static String b(InputStream inputStream) {
        String str;
        str = "";
        try {
            if (inputStream == null) {
                return "";
            }
            try {
                byte[] bArr = new byte[inputStream.available()];
                str = inputStream.read(bArr) > 0 ? new String(bArr, "UTF-8") : "";
                try {
                    inputStream.close();
                } catch (IOException unused) {
                    LogUtil.b("Track_PostureRangeUtils", "getString ioException");
                }
            } catch (IOException unused2) {
                LogUtil.b("Track_PostureRangeUtils", "getString IOException");
            }
            return str;
        } finally {
            try {
                inputStream.close();
            } catch (IOException unused3) {
                LogUtil.b("Track_PostureRangeUtils", "getString ioException");
            }
        }
    }

    public static void d() {
        HashMap hashMap = new HashMap();
        hashMap.put("configType", "postureJudge");
        drd.e(new dql("com.huawei.health_Sport_Common", hashMap), "posture_judge_update", 7, (DownloadCallback<File>) new DownloadCallback() { // from class: hjt.2
            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            public void onFinish(Object obj) {
                LogUtil.a("Track_PostureRangeUtils", "updateNewIndexFile onFinish ");
            }

            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            public void onProgress(long j, long j2, boolean z, String str) {
                LogUtil.a("Track_PostureRangeUtils", "updateNewIndexFile onProgress ", Boolean.valueOf(z), " fileId ", str);
            }

            @Override // com.huawei.health.healthcloudconfig.listener.DownloadCallback
            public void onFail(int i, Throwable th) {
                LogUtil.h("Track_PostureRangeUtils", "updateNewIndexFile onFail ");
            }
        });
    }

    public static float e(int i) {
        if (i <= 0) {
            return 0.0f;
        }
        if (i <= 10) {
            return 0.1f;
        }
        return new BigDecimal(i * 0.01f).setScale(1, 1).floatValue();
    }
}
