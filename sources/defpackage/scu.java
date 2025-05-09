package defpackage;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import com.google.gson.JsonSyntaxException;
import com.huawei.health.R;
import com.huawei.health.healthdatamgr.api.HealthDataMgrApi;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.data.model.HiTrackMetaData;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.sport.base.GridBanner;
import com.huawei.ui.main.R$string;
import defpackage.nql;
import health.compact.a.Services;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes7.dex */
public class scu {
    public static void dWa_(long j, long j2, int i, int i2, final Handler handler, final int i3) {
        if (i != 286 || i2 < dpg.a()) {
            handler.obtainMessage(i3, null).sendToTarget();
        } else {
            ((HealthDataMgrApi) Services.c("HWHealthDataMgr", HealthDataMgrApi.class)).requestTrackSimplifyData(j, j2, i, new IBaseResponseCallback() { // from class: scv
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i4, Object obj) {
                    scu.dVZ_(handler, i3, i4, obj);
                }
            });
        }
    }

    static /* synthetic */ void dVZ_(Handler handler, int i, int i2, Object obj) {
        if (i2 != 0) {
            LogUtil.b("GolfBriefHelper", "requestGolfBriefData not success");
            return;
        }
        List list = (List) obj;
        if (list.isEmpty()) {
            LogUtil.b("GolfBriefHelper", "requestGolfBriefData size 0");
            return;
        }
        List<HiHealthData> e = e((List<HiHealthData>) list, 18);
        List<HiHealthData> e2 = e((List<HiHealthData>) list, 9);
        ArrayList arrayList = new ArrayList();
        arrayList.add(b(e));
        arrayList.add(b(e2));
        handler.sendMessage(handler.obtainMessage(i, arrayList));
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x008c A[EDGE_INSN: B:29:0x008c->B:30:0x008c BREAK  A[LOOP:0: B:6:0x0012->B:32:0x0012], SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0012 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static java.util.List<com.huawei.hihealth.HiHealthData> e(java.util.List<com.huawei.hihealth.HiHealthData> r6, int r7) {
        /*
            java.lang.String r0 = "GolfBriefHelper"
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            boolean r2 = defpackage.koq.b(r6)
            if (r2 == 0) goto Le
            return r1
        Le:
            java.util.Iterator r6 = r6.iterator()
        L12:
            boolean r2 = r6.hasNext()
            if (r2 == 0) goto L8c
            java.lang.Object r2 = r6.next()
            com.huawei.hihealth.HiHealthData r2 = (com.huawei.hihealth.HiHealthData) r2
            if (r2 != 0) goto L21
            goto L12
        L21:
            java.lang.String r3 = r2.getMetaData()
            java.lang.Class<com.huawei.hihealth.data.model.HiTrackMetaData> r4 = com.huawei.hihealth.data.model.HiTrackMetaData.class
            java.lang.Object r3 = com.huawei.hihealth.util.HiJsonUtil.e(r3, r4)     // Catch: com.google.gson.JsonSyntaxException -> L82
            com.huawei.hihealth.data.model.HiTrackMetaData r3 = (com.huawei.hihealth.data.model.HiTrackMetaData) r3     // Catch: com.google.gson.JsonSyntaxException -> L82
            int r4 = r3.getSportType()
            r5 = 286(0x11e, float:4.01E-43)
            if (r4 == r5) goto L36
            goto L12
        L36:
            java.util.Map r3 = r3.getExtendTrackMap()
            java.lang.String r4 = "totalHoles"
            java.lang.Object r3 = r3.get(r4)
            java.lang.String r3 = (java.lang.String) r3
            boolean r4 = android.text.TextUtils.isEmpty(r3)     // Catch: java.lang.NumberFormatException -> L4d
            if (r4 != 0) goto L56
            int r3 = java.lang.Integer.parseInt(r3)     // Catch: java.lang.NumberFormatException -> L4d
            goto L58
        L4d:
            java.lang.String r3 = "getGolfDataByHoles NumberFormatException"
            java.lang.Object[] r3 = new java.lang.Object[]{r3}
            com.huawei.hwlogsmodel.LogUtil.h(r0, r3)
        L56:
            r3 = 18
        L58:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "getGolfDataByHoles, requireHoles="
            r4.<init>(r5)
            r4.append(r7)
            java.lang.String r5 = ", totalHoles="
            r4.append(r5)
            r4.append(r3)
            java.lang.String r4 = r4.toString()
            java.lang.Object[] r4 = new java.lang.Object[]{r4}
            com.huawei.hwlogsmodel.LogUtil.h(r0, r4)
            if (r3 != r7) goto L7a
            r1.add(r2)
        L7a:
            int r2 = r1.size()
            r3 = 5
            if (r2 < r3) goto L12
            goto L8c
        L82:
            java.lang.String r2 = "getGolfDataByHoles trackMetaData is jsonSyntaxException"
            java.lang.Object[] r2 = new java.lang.Object[]{r2}
            com.huawei.hwlogsmodel.LogUtil.h(r0, r2)
            goto L12
        L8c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.scu.e(java.util.List, int):java.util.List");
    }

    private static rdg b(List<HiHealthData> list) {
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        int i = Integer.MAX_VALUE;
        int i2 = 0;
        for (int i3 = 0; i3 < list.size(); i3++) {
            HiHealthData hiHealthData = list.get(i3);
            if (hiHealthData != null) {
                try {
                    HiTrackMetaData hiTrackMetaData = (HiTrackMetaData) HiJsonUtil.e(hiHealthData.getMetaData(), HiTrackMetaData.class);
                    String str = hiTrackMetaData.getExtendTrackMap().get("golfSwingCount");
                    Integer d = nsn.d(str);
                    int intValue = d != null ? d.intValue() : 0;
                    i = Math.min(intValue, i);
                    f += intValue;
                    String str2 = hiTrackMetaData.getExtendTrackMap().get("putts");
                    Integer d2 = nsn.d(str2);
                    float intValue2 = d2 != null ? d2.intValue() : 0;
                    String str3 = hiTrackMetaData.getExtendTrackMap().get("fairwayHits");
                    Integer d3 = nsn.d(str3);
                    int intValue3 = d3 != null ? d3.intValue() : 0;
                    String str4 = hiTrackMetaData.getExtendTrackMap().get("fairwayLeft");
                    Integer d4 = nsn.d(str4);
                    int intValue4 = d4 != null ? d4.intValue() : 0;
                    String str5 = hiTrackMetaData.getExtendTrackMap().get("fairwayRight");
                    float intValue5 = intValue3 / ((intValue3 + intValue4) + (nsn.d(str5) != null ? r1.intValue() : 0));
                    String str6 = hiTrackMetaData.getExtendTrackMap().get("gir");
                    Double b = nsn.b(str6);
                    f4 = (float) (f4 + (b != null ? b.doubleValue() : 0.0d));
                    i2++;
                    LogUtil.a("GolfBriefHelper", "calculateGolfBrief() current hiHealthData, coursesCount = ", Integer.valueOf(i2), ", swingCountStr = ", str, ", puttsStr = ", str2, ", fairWayHitsStr = ", str3, ", fairWayLeftStr = ", str4, ", fairWayRightStr = ", str5, ", girStr = ", str6);
                    f3 += intValue5;
                    f2 += intValue2;
                } catch (JsonSyntaxException unused) {
                    LogUtil.h("GolfBriefHelper", "calculateGolfBrief trackMetaData is jsonSyntaxException");
                }
            }
        }
        int e = (int) e(f, i2);
        int e2 = (int) e(f2, i2);
        float e3 = e(f3 * 100.0f, i2);
        float e4 = e(f4, i2);
        int i4 = i == Integer.MAX_VALUE ? 0 : i;
        LogUtil.a("GolfBriefHelper", "calculateGolfBrief(), size = ", Integer.valueOf(list.size()), ", coursesCount = ", Integer.valueOf(i2), ", bestCount = ", Integer.valueOf(i4), ", avgCount = ", Integer.valueOf(e), ", avgPutters = ", Integer.valueOf(e2), ", avgFairwayHitsRatio = ", Float.valueOf(e3), ", avgGIR = ", Float.valueOf(e4), ", bestCount = ", Integer.valueOf(i4));
        return new rdg(i4, e, e2, e3, e4);
    }

    private static float e(float f, int i) {
        if (i == 0) {
            LogUtil.b("GolfBriefHelper", "courseCount  =  0");
            return 0.0f;
        }
        return Math.round(f / i);
    }

    public static void dVY_(Context context, List<rdg> list, View view) {
        if (list != null) {
            ((GridBanner) view.findViewById(R.id.golf_year_brief_banner)).setData(e(context, list));
        }
    }

    private static List<nql> e(Context context, List<rdg> list) {
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            LogUtil.h("GolfBriefHelper", "extractGridBannerData() golfBriefs is null");
            return arrayList;
        }
        rdg rdgVar = list.get(0);
        rdg rdgVar2 = list.get(1);
        String string = context.getString(R$string.IDS_golf_best_swing);
        String string2 = context.getString(R$string.IDS_golf_average_swing);
        String string3 = context.getString(R$string.IDS_golf_average_putt);
        String string4 = context.getString(R$string.IDS_golf_average_fairway_rate);
        String string5 = context.getString(R$string.IDS_golf_average_gir);
        arrayList.add(new nql(context.getString(R$string.IDS_golf_course_recent_five_rounds_link, context.getResources().getQuantityString(R.plurals._2130903335_res_0x7f030127, 5, 5), context.getResources().getQuantityString(R.plurals._2130903336_res_0x7f030128, 18, 18)), new ArrayList<nql.e>(string, string2, string3, string4, string5) { // from class: scu.2

            /* renamed from: a, reason: collision with root package name */
            final /* synthetic */ String f17027a;
            final /* synthetic */ String b;
            final /* synthetic */ String c;
            final /* synthetic */ String d;
            final /* synthetic */ String e;

            {
                this.b = string;
                this.f17027a = string2;
                this.c = string3;
                this.d = string4;
                this.e = string5;
                add(new nql.e(rdg.this.d(), string));
                add(new nql.e(rdg.this.c(), string2));
                add(new nql.e(rdg.this.e(), string3));
                add(new nql.e(rdg.this.b(), string4));
                add(new nql.e(rdg.this.a(), string5));
            }
        }));
        arrayList.add(new nql(context.getString(R$string.IDS_golf_course_recent_five_rounds_link, context.getResources().getQuantityString(R.plurals._2130903335_res_0x7f030127, 5, 5), context.getResources().getQuantityString(R.plurals._2130903336_res_0x7f030128, 9, 9)), new ArrayList<nql.e>(string, string2, string3, string4, string5) { // from class: scu.1

            /* renamed from: a, reason: collision with root package name */
            final /* synthetic */ String f17026a;
            final /* synthetic */ String b;
            final /* synthetic */ String c;
            final /* synthetic */ String d;
            final /* synthetic */ String e;

            {
                this.e = string;
                this.d = string2;
                this.b = string3;
                this.c = string4;
                this.f17026a = string5;
                add(new nql.e(rdg.this.d(), string));
                add(new nql.e(rdg.this.c(), string2));
                add(new nql.e(rdg.this.e(), string3));
                add(new nql.e(rdg.this.b(), string4));
                add(new nql.e(rdg.this.a(), string5));
            }
        }));
        return arrayList;
    }
}
