package defpackage;

import com.huawei.healthcloud.plugintrack.model.MotionData;
import com.huawei.hwfoundationmodel.trackmodel.MotionPathSimplify;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.hwsportmodel.CommonSegment;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/* loaded from: classes4.dex */
public class hlb extends hkw {

    /* renamed from: a, reason: collision with root package name */
    private static final String f13230a = "Track_SegmentLineDataPreprocessor";

    public hlb(List<MotionData> list, MotionPathSimplify motionPathSimplify) {
        super(list, motionPathSimplify);
    }

    @Override // defpackage.hkw, com.huawei.healthcloud.plugintrack.ui.map.datapreprocess.MapDataPreprocessor
    public List<hkx> preprocess() {
        List<hkx> preprocess = super.preprocess();
        for (int i = 0; i < preprocess.size(); i++) {
            if (this.d.size() > i) {
                List<CommonSegment> requestSegmentList = this.d.get(i).requestSegmentList();
                if (koq.e((Object) requestSegmentList, kwv.class)) {
                    d(preprocess, requestSegmentList, i);
                } else {
                    LogUtil.h(f13230a, "Unexpected segment type");
                }
            }
        }
        LogUtil.a(f13230a, "checkOutput ", preprocess.toString());
        return preprocess;
    }

    private void d(List<hkx> list, List<CommonSegment> list2, int i) {
        LinkedList linkedList = new LinkedList();
        for (CommonSegment commonSegment : list2) {
            if (commonSegment instanceof kwv) {
                kwv kwvVar = (kwv) commonSegment;
                a(kwvVar);
                linkedList.offer(kwvVar);
            }
        }
        e(list, linkedList, i);
    }

    private void a(kwv kwvVar) {
        if (e(kwvVar)) {
            kwvVar.c(0L);
            kwvVar.d(0L);
        }
        if (d(kwvVar)) {
            kwvVar.d(kwvVar.i() - 1);
        }
    }

    private boolean e(kwv kwvVar) {
        return kwvVar.d() >= kwvVar.b() || kwvVar.d() >= kwvVar.i() - 1;
    }

    private boolean d(kwv kwvVar) {
        return kwvVar.b() != 0 && kwvVar.b() >= kwvVar.i();
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x00a3, code lost:
    
        if (defpackage.gwe.d(r11[0], r11[1]) != false) goto L19;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void e(java.util.List<defpackage.hkx> r19, java.util.Queue<defpackage.kwv> r20, int r21) {
        /*
            Method dump skipped, instructions count: 272
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.hlb.e(java.util.List, java.util.Queue, int):void");
    }

    private kwv a(Queue<kwv> queue) {
        while (!queue.isEmpty()) {
            kwv poll = queue.poll();
            if (poll != null && (b(poll) || c(poll))) {
                return poll;
            }
        }
        return null;
    }

    private boolean b(kwv kwvVar) {
        return kwvVar.b() > kwvVar.d() && kwvVar.e() > kwvVar.i() && kwvVar.i() > kwvVar.b();
    }

    private boolean c(kwv kwvVar) {
        return kwvVar.d() == kwvVar.b() && kwvVar.i() < kwvVar.e();
    }

    private boolean d(kwv kwvVar, int i) {
        if (kwvVar != null && kwvVar.i() < kwvVar.e()) {
            long j = i;
            if (j >= kwvVar.i() && j <= kwvVar.e()) {
                return true;
            }
        }
        return false;
    }

    private boolean e(kwv kwvVar, int i) {
        if (kwvVar != null && kwvVar.d() < kwvVar.b()) {
            long j = i;
            if (j >= kwvVar.d() && j <= kwvVar.b()) {
                return true;
            }
        }
        return false;
    }

    private void a(int i, List<Integer> list, int i2, List<hjf> list2) {
        if (i2 >= list2.size() || list.size() >= list2.get(i2).d().size()) {
            return;
        }
        list.add(Integer.valueOf(i));
    }

    private void c(List<hjf> list, int i, List<Integer> list2, int i2) {
        if (koq.b(list, i)) {
            LogUtil.h(f13230a, "setTypeAndColors lineIndex out of bounds");
        } else {
            list.get(i).c(list2);
            list.get(i).b(i2);
        }
    }

    private void d(List<double[]> list, int i, hkx hkxVar) {
        while (i >= 0) {
            double[] dArr = list.get(i);
            if (dArr.length >= 2 && !gwe.d(dArr[0], dArr[1])) {
                List<hjd> a2 = hkxVar.a();
                a2.add(new hjd(dArr[0], dArr[1]));
                hkxVar.c(a2);
                return;
            }
            i--;
        }
    }

    private void c(hkx hkxVar) {
        List<hjf> e = hkxVar.e();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < e.size(); i++) {
            e(arrayList, e.get(i).e(), e.get(i).d(), e.get(i).a());
        }
        hkxVar.b(arrayList);
    }

    private void e(List<hjf> list, int i, List<hjd> list2, List<Integer> list3) {
        if (i != 0) {
            if (i == 1 || i == 2) {
                b(i, list3, list2, list);
                return;
            }
            return;
        }
        ArrayList arrayList = new ArrayList();
        int intValue = list3.size() > 0 ? list3.get(0).intValue() : 0;
        for (int i2 = 0; i2 < list3.size(); i2++) {
            if (intValue == list3.get(i2).intValue() && i2 < list2.size()) {
                arrayList.add(list2.get(i2));
            } else {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(Integer.valueOf(intValue));
                arrayList.add(list2.get(i2));
                b(1, arrayList2, arrayList, list);
                int intValue2 = list3.get(i2).intValue();
                ArrayList arrayList3 = new ArrayList();
                arrayList3.add(list2.get(i2));
                intValue = intValue2;
                arrayList = arrayList3;
            }
        }
        if (koq.b(arrayList)) {
            return;
        }
        ArrayList arrayList4 = new ArrayList();
        arrayList4.add(Integer.valueOf(intValue));
        b(1, arrayList4, arrayList, list);
    }

    private void b(int i, List<Integer> list, List<hjd> list2, List<hjf> list3) {
        if (list2 == null || list2.size() <= 1) {
            LogUtil.h(f13230a, "invalid new line");
        } else {
            list3.add(new hjf(i, list, list2));
        }
    }
}
