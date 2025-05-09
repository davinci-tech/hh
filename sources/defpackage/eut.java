package defpackage;

import com.google.gson.JsonSyntaxException;
import com.huawei.health.suggestion.RunCourseRecommendCallback;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.CommonUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes8.dex */
public class eut implements IBaseResponseCallback {
    private RunCourseRecommendCallback c;

    eut(RunCourseRecommendCallback runCourseRecommendCallback) {
        this.c = runCourseRecommendCallback;
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x004e  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x0032  */
    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
    /* renamed from: onResponse */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void d(int r8, java.lang.Object r9) {
        /*
            r7 = this;
            java.lang.String r8 = "onResponse"
            java.lang.Object[] r8 = new java.lang.Object[]{r8}
            java.lang.String r0 = "Suggest_MyMonthDataCallback"
            com.huawei.hwlogsmodel.LogUtil.a(r0, r8)
            r8 = 0
            java.lang.Class<com.huawei.hihealth.HiHealthData> r1 = com.huawei.hihealth.HiHealthData.class
            boolean r1 = defpackage.koq.e(r9, r1)     // Catch: java.lang.ClassCastException -> L17
            if (r1 == 0) goto L27
            java.util.List r9 = (java.util.List) r9     // Catch: java.lang.ClassCastException -> L17
            goto L28
        L17:
            java.lang.String r9 = "parseTrackSimplifyData classCastException "
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            com.huawei.hwlogsmodel.LogUtil.h(r0, r9)
            com.huawei.health.suggestion.RunCourseRecommendCallback r9 = r7.c
            java.lang.String r1 = "classCastException"
            r9.onFailure(r8, r1)
        L27:
            r9 = 0
        L28:
            r2 = r9
            fij r3 = new fij
            r3.<init>()
            r4 = 0
            if (r2 != 0) goto L4e
            java.lang.String r9 = "parseTrackSimplifyData datas is null"
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r9)
            r3.a(r8)
            r3.e(r4)
            r3.a(r4)
            com.huawei.health.suggestion.RunCourseRecommendCallback r8 = r7.c
            java.lang.String r9 = defpackage.eva.c(r3)
            r8.onSuccess(r9)
            return
        L4e:
            int r9 = r2.size()
            if (r9 != 0) goto L70
            java.lang.String r9 = "parseTrackSimplifyData datas size is 0"
            java.lang.Object[] r9 = new java.lang.Object[]{r9}
            com.huawei.hwlogsmodel.LogUtil.a(r0, r9)
            r3.a(r8)
            r3.e(r4)
            r3.a(r4)
            com.huawei.health.suggestion.RunCourseRecommendCallback r8 = r7.c
            java.lang.String r9 = defpackage.eva.c(r3)
            r8.onSuccess(r9)
            return
        L70:
            r4 = 0
            r5 = 0
            r1 = r7
            r1.a(r2, r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.eut.d(int, java.lang.Object):void");
    }

    private void a(List<HiHealthData> list, fij fijVar, int i, long j) {
        ArrayList arrayList = new ArrayList();
        d(list, arrayList);
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        long j2 = 0;
        long j3 = 0;
        for (e eVar : arrayList) {
            if (eVar != null) {
                i2++;
                j2 += eVar.e();
                i3 = (int) (i3 + eVar.c());
                if (eVar.c() <= 21079.5d) {
                    j3 += eVar.e();
                    i4++;
                }
            }
        }
        if (i2 == 0 || CommonUtil.c(j2)) {
            fijVar.a(i);
            fijVar.e(j);
            fijVar.a(0L);
            this.c.onSuccess(eva.c(fijVar));
            return;
        }
        fijVar.a(i3 / i2);
        fijVar.e(i3 / j2);
        if (i4 == 0) {
            fijVar.a(0L);
        } else {
            fijVar.a(j3 / i4);
            this.c.onSuccess(eva.c(fijVar));
        }
    }

    private void d(List<HiHealthData> list, List<e> list2) {
        if (koq.c(list)) {
            for (HiHealthData hiHealthData : list) {
                if (hiHealthData != null) {
                    e eVar = new e();
                    try {
                        HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiTrackMetaData.class);
                        LogUtil.a("Suggest_MyMonthDataCallback", " sport getSportType", Integer.valueOf(hiTrackMetaData.getSportType()));
                        if (hiTrackMetaData.getSportType() == 258 || hiTrackMetaData.getSportType() == 264) {
                            eVar.a(hiTrackMetaData.getTotalDistance());
                            eVar.c(hiTrackMetaData.getTotalTime());
                        }
                        list2.add(eVar);
                    } catch (JsonSyntaxException unused) {
                        LogUtil.h("Suggest_MyMonthDataCallback", "parseTrackSimplifyData trackMetaData is jsonSyntaxException");
                    }
                }
            }
        }
    }

    public static class e {

        /* renamed from: a, reason: collision with root package name */
        private float f12344a;
        private long c;

        public float c() {
            return this.f12344a;
        }

        public void c(long j) {
            this.c = j;
        }

        public long e() {
            return this.c;
        }

        public void a(float f) {
            this.f12344a = f;
        }
    }
}
