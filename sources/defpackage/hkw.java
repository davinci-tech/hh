package defpackage;

import android.graphics.Color;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.model.MotionData;
import com.huawei.healthcloud.plugintrack.ui.fragment.TrackScreenFrag;
import com.huawei.healthcloud.plugintrack.ui.map.datapreprocess.MapDataPreprocessor;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.FitnessSleepType;
import com.huawei.hwcommonmodel.fitnessdatatype.FitnessSportType;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.HwWorkoutServiceUtils;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes4.dex */
public class hkw implements MapDataPreprocessor {
    private static final String j = "Track_LineDataPreprocessor";
    protected int b;
    protected List<Integer> c = new ArrayList();
    protected List<MotionData> d;
    private static final int e = Color.rgb(107, FitnessSleepType.HW_FITNESS_NOON, HwWorkoutServiceUtils.HEART_RATE_TRUST_VALUE);

    /* renamed from: a, reason: collision with root package name */
    private static final int f13227a = Color.rgb(49, FitnessSportType.HW_FITNESS_SPORT_ALL, 251);
    private static final int h = Color.parseColor("#FFFFA11C");
    private static final int i = Color.parseColor("#FF00BFC9");
    private static final int g = Color.parseColor("#FFFF1745");

    public hkw(List<MotionData> list, MotionPathSimplify motionPathSimplify) {
        this.d = list;
        this.b = motionPathSimplify.requestSportType();
    }

    @Override // com.huawei.healthcloud.plugintrack.ui.map.datapreprocess.MapDataPreprocessor
    public List<hkx> preprocess() {
        ArrayList arrayList = new ArrayList();
        for (MotionData motionData : this.d) {
            if (motionData != null) {
                Map<Long, double[]> lbsDataMap = motionData.getLbsDataMap();
                a(lbsDataMap);
                hkx hkxVar = new hkx();
                hkxVar.a(motionData.getSportType());
                c(lbsDataMap, hkxVar);
                d(hkxVar);
                arrayList.add(hkxVar);
            }
        }
        LogUtil.a(j, "checkOutput ", arrayList.toString());
        return arrayList;
    }

    private void a(Map<Long, double[]> map) {
        Iterator<Map.Entry<Long, double[]>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Long, double[]> next = it.next();
            if (next == null) {
                LogUtil.h(j, "entry is null");
            } else {
                double[] value = next.getValue();
                if (value == null || value.length < 2) {
                    LogUtil.h(j, "values is null or length < 2");
                } else if (!gwe.c(new hjd(value[0], value[1]))) {
                    return;
                } else {
                    it.remove();
                }
            }
        }
    }

    private void c(Map<Long, double[]> map, hkx hkxVar) {
        StringBuilder sb;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        StringBuilder sb2 = new StringBuilder();
        char c = 0;
        boolean z = false;
        int i2 = 0;
        for (Map.Entry<Long, double[]> entry : map.entrySet()) {
            if (entry == null) {
                LogUtil.h(j, "entry is null");
                sb = sb2;
            } else {
                double[] value = entry.getValue();
                if (value == null || value.length < 2) {
                    sb = sb2;
                    LogUtil.h(j, "values is null or length < 2");
                } else if (gwe.d(value[c]) || gwe.a(value[1])) {
                    sb = sb2;
                    sb.append(entry.getKey());
                    sb.append("=");
                    sb.append(Arrays.toString(value));
                } else {
                    StringBuilder sb3 = sb2;
                    hjd hjdVar = new hjd(value[c], value[1]);
                    if (z) {
                        if (!gwe.c(hjdVar)) {
                            arrayList2.add(hjdVar);
                            b(arrayList, arrayList2, 2, hkxVar.g());
                            arrayList2 = new ArrayList();
                            arrayList2.add(hjdVar);
                            z = false;
                        } else {
                            this.c.add(Integer.valueOf(i2));
                        }
                    } else if (gwe.c(hjdVar) && arrayList2.size() != 0) {
                        hjd hjdVar2 = arrayList2.get(arrayList2.size() - 1);
                        b(arrayList, arrayList2, 1, hkxVar.g());
                        arrayList2 = new ArrayList();
                        arrayList2.add(new hjd(hjdVar2.b, hjdVar2.d));
                        this.c.add(Integer.valueOf(i2));
                        z = true;
                    } else {
                        arrayList2.add(hjdVar);
                    }
                    i2++;
                    sb2 = sb3;
                    c = 0;
                }
            }
            sb2 = sb;
            c = 0;
        }
        StringBuilder sb4 = sb2;
        if (arrayList2.size() > 1) {
            b(arrayList, arrayList2, 1, hkxVar.g());
        }
        LogUtil.b(j, "invalidLbs: ", sb4.toString());
        hkxVar.b(arrayList);
    }

    private void b(List<hjf> list, List<hjd> list2, int i2, int i3) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(b(i3, i2)));
        list.add(new hjf(i2, arrayList, list2));
    }

    private int b(int i2, int i3) {
        if (i3 == 2) {
            return BaseApplication.getContext().getResources().getColor(R.color._2131298897_res_0x7f090a51);
        }
        if (this.b == 512) {
            return c(i2);
        }
        if (i2 == 266) {
            return f13227a;
        }
        if (i2 == 222) {
            return TrackScreenFrag.c;
        }
        return gwh.w;
    }

    private int c(int i2) {
        if (i2 == 258) {
            return h;
        }
        if (i2 == 266) {
            return i;
        }
        if (i2 == 259) {
            return g;
        }
        return e;
    }

    private void d(hkx hkxVar) {
        List<hjf> e2 = hkxVar.e();
        Iterator<hjf> it = e2.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            hjf next = it.next();
            if (next.e() != 2) {
                List<hjd> d = next.d();
                if (d.size() > 0) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(d.get(0));
                    hkxVar.a(arrayList);
                    break;
                }
            }
        }
        for (int size = e2.size() - 1; size >= 0; size--) {
            hjf hjfVar = e2.get(size);
            if (hjfVar.e() != 2) {
                List<hjd> d2 = hjfVar.d();
                if (d2.size() > 0) {
                    ArrayList arrayList2 = new ArrayList();
                    arrayList2.add(d2.get(d2.size() - 1));
                    hkxVar.d(arrayList2);
                    return;
                }
            }
        }
    }
}
