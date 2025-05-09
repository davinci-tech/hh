package defpackage;

import android.graphics.Color;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.model.MotionData;
import com.huawei.healthcloud.plugintrack.ui.map.datapreprocess.MapDataPreprocessor;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.fitnessdatatype.FitnessSleepType;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.utils.HwWorkoutServiceUtils;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes4.dex */
public class hkv extends hkw implements MapDataPreprocessor {
    private static final String f = "Track_PaceMapLineDataPreprocessor";
    private List<Integer> h;
    private int j;
    private static final int e = Color.rgb(107, FitnessSleepType.HW_FITNESS_NOON, HwWorkoutServiceUtils.HEART_RATE_TRUST_VALUE);

    /* renamed from: a, reason: collision with root package name */
    private static final int f13226a = Color.parseColor("#FF00BFC9");

    public hkv(List<MotionData> list, MotionPathSimplify motionPathSimplify) {
        super(list, motionPathSimplify);
    }

    @Override // defpackage.hkw, com.huawei.healthcloud.plugintrack.ui.map.datapreprocess.MapDataPreprocessor
    public List<hkx> preprocess() {
        List<hkx> preprocess = super.preprocess();
        for (int i = 0; i < this.d.size(); i++) {
            hkx hkxVar = preprocess.get(i);
            this.j = 0;
            for (hjf hjfVar : hkxVar.e()) {
                if (hjfVar.e() != 2) {
                    this.j += hjfVar.d().size();
                }
            }
            Map<Integer, Float> paceMap = this.d.get(i).getPaceMap();
            if (!koq.b(this.h)) {
                this.h = null;
            }
            d(hkxVar, paceMap);
            e(hkxVar, i);
        }
        LogUtil.a(f, "checkOutput ", preprocess.toString());
        return preprocess;
    }

    private void e(hkx hkxVar, int i) {
        c(new ArrayList(this.d.get(i).getLbsDataMap().values()), this.d.get(i).getPaceMap(), hkxVar);
    }

    private void c(List<double[]> list, Map<Integer, Float> map, hkx hkxVar) {
        Set<Map.Entry<Integer, Float>> entrySet = map.entrySet();
        int size = map.size();
        Integer[] numArr = new Integer[size];
        Integer[] numArr2 = new Integer[size];
        Iterator<Map.Entry<Integer, Float>> it = entrySet.iterator();
        int i = 0;
        while (it.hasNext()) {
            int intValue = it.next().getKey().intValue();
            if (gvu.a(intValue)) {
                if (intValue > 100000) {
                    numArr[i] = Integer.valueOf((intValue % 100000) + 1);
                    numArr2[i] = Integer.valueOf((intValue / 100000) / 100);
                } else {
                    numArr[i] = Integer.valueOf(intValue + 1);
                    numArr2[i] = Integer.valueOf(i + 1);
                }
                i++;
            }
        }
        a(list, numArr, numArr2, i, hkxVar);
    }

    private void a(List<double[]> list, Integer[] numArr, Integer[] numArr2, int i, hkx hkxVar) {
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < i && i2 < numArr.length && i2 < numArr2.length; i2++) {
            if (numArr2[i2].intValue() != 0) {
                int intValue = numArr[i2].intValue();
                if (intValue > list.size()) {
                    break;
                }
                if (intValue == list.size() && list.size() > 0) {
                    intValue--;
                }
                hjd b = b(list, intValue);
                if (b == null) {
                    break;
                } else {
                    arrayList.add(b);
                }
            }
        }
        hkxVar.c(arrayList);
    }

    private hjd b(List<double[]> list, int i) {
        while (i >= 0) {
            double[] dArr = list.get(i);
            if (dArr.length >= 2) {
                hjd hjdVar = new hjd(dArr[0], dArr[1]);
                if (!gwe.c(hjdVar)) {
                    return hjdVar;
                }
            }
            i--;
        }
        return null;
    }

    private void d(hkx hkxVar, Map<Integer, Float> map) {
        int g = hkxVar.g();
        List<hjf> e2 = hkxVar.e();
        int i = 0;
        for (int i2 = 0; i2 < e2.size(); i2++) {
            hjf hjfVar = e2.get(i2);
            if (hjfVar.e() == 2) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(Integer.valueOf(BaseApplication.getContext().getResources().getColor(R.color._2131298897_res_0x7f090a51)));
                hjfVar.c(arrayList);
                hjfVar.b(2);
            } else {
                ArrayList arrayList2 = new ArrayList();
                if (g == 266) {
                    arrayList2.add(Integer.valueOf(f13226a));
                    hjfVar.c(arrayList2);
                    hjfVar.b(1);
                } else if (map == null || map.size() == 0 || d(map)) {
                    arrayList2.add(Integer.valueOf(e));
                    hjfVar.c(arrayList2);
                    hjfVar.b(1);
                } else if (map.size() == 1) {
                    arrayList2.add(Integer.valueOf(e(map, g)));
                    hjfVar.c(arrayList2);
                    hjfVar.b(1);
                } else {
                    c(map, g);
                    int size = hjfVar.d().size();
                    d(b(hkxVar, i2, i), arrayList2, hjfVar);
                    i += size;
                }
            }
        }
    }

    private boolean d(Map<Integer, Float> map) {
        boolean z = false;
        for (Map.Entry<Integer, Float> entry : map.entrySet()) {
            if (entry.getKey().intValue() <= 0) {
                return true;
            }
            if (entry.getKey().intValue() % 100000 != 0) {
                z = true;
            }
        }
        return !z;
    }

    private int e(Map<Integer, Float> map, int i) {
        Iterator<Map.Entry<Integer, Float>> it = map.entrySet().iterator();
        float f2 = 0.0f;
        while (it.hasNext()) {
            f2 = it.next().getValue().floatValue();
        }
        return gwe.c(f2, i);
    }

    private List<Integer> b(hkx hkxVar, int i, int i2) {
        if (this.h == null) {
            LogUtil.h(f, "mTotalColorList is null");
            return new ArrayList();
        }
        List<hjd> d = hkxVar.e().get(i).d();
        int size = d.size();
        int i3 = size / 2500;
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        if (i3 <= 1) {
            arrayList.addAll(d);
            while (true) {
                if (i2 >= this.h.size() || arrayList2.size() == arrayList.size()) {
                    break;
                }
                if (koq.b(this.h, i2)) {
                    LogUtil.b(f, "mTotalColorList is out of bound");
                    break;
                }
                arrayList2.add(this.h.get(i2));
                i2++;
            }
        } else {
            for (int i4 = 0; i4 < size; i4++) {
                if (i4 % i3 == 0 || i4 == size - 1) {
                    arrayList.add(d.get(i4));
                    e(i2, this.h, arrayList2, i4);
                }
            }
        }
        int size2 = arrayList.size();
        int size3 = arrayList2.size();
        if (size2 > 1 && size3 < size2) {
            int intValue = size3 == 0 ? e : arrayList2.get(size3 - 1).intValue();
            for (int i5 = 0; i5 < arrayList.size() - size3; i5++) {
                arrayList2.add(Integer.valueOf(intValue));
            }
        }
        hkxVar.e().get(i).d(arrayList);
        return arrayList2;
    }

    private void c(Map<Integer, Float> map, int i) {
        int intValue;
        if (koq.b(this.h)) {
            Set<Map.Entry<Integer, Float>> entrySet = map.entrySet();
            float[] fArr = new float[map.size()];
            float[] fArr2 = new float[map.size()];
            Integer[] numArr = new Integer[map.size()];
            int i2 = 0;
            for (Map.Entry<Integer, Float> entry : entrySet) {
                if (entry.getKey() != null) {
                    if (entry.getKey().intValue() > 100000) {
                        fArr[i2] = entry.getKey().intValue() % 100000;
                    } else {
                        fArr[i2] = entry.getKey().intValue();
                    }
                    float floatValue = entry.getValue().floatValue();
                    fArr2[i2] = floatValue;
                    numArr[i2] = Integer.valueOf(gwe.c(floatValue, i));
                    i2++;
                }
            }
            e(fArr);
            List<Integer> e2 = gwe.e(fArr, numArr);
            this.h = e2;
            if (e2 == null) {
                LogUtil.h(f, "colorList is null");
                return;
            }
            LogUtil.a(f, "colorList ", Integer.valueOf(e2.size()), " pointSize ", Integer.valueOf(this.j));
            int size = this.h.size();
            if (size < this.j) {
                if (size == 0) {
                    intValue = e;
                } else {
                    intValue = this.h.get(size - 1).intValue();
                }
                for (int i3 = 0; i3 < this.j - size; i3++) {
                    this.h.add(Integer.valueOf(intValue));
                }
            }
        }
    }

    private void e(float[] fArr) {
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < fArr.length; i3++) {
            int i4 = (int) fArr[i3];
            while (i < this.c.size() && this.c.get(i).intValue() <= i4) {
                i2++;
                i++;
            }
            float f2 = fArr[i3];
            float f3 = i2;
            if (f2 > f3) {
                fArr[i3] = f2 - f3;
            }
        }
    }

    private void e(int i, List<Integer> list, List<Integer> list2, int i2) {
        int i3 = i2 + i;
        if (i3 < list.size()) {
            list2.add(list.get(i3));
        }
    }

    private void d(List<Integer> list, List<Integer> list2, hjf hjfVar) {
        if (koq.b(list)) {
            list2.add(Integer.valueOf(e));
            hjfVar.c(list2);
            hjfVar.b(1);
        } else if (list.size() == 1) {
            list2.add(list.get(0));
            hjfVar.c(list2);
            hjfVar.b(1);
        } else {
            hjfVar.c(list);
            hjfVar.b(0);
        }
    }
}
