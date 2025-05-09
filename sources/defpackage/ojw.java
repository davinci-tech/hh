package defpackage;

import android.text.TextUtils;
import androidx.core.util.Consumer;
import com.google.android.gms.common.util.BiConsumer;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.hwcommonmodel.utils.DateFormatUtil;
import com.huawei.hwdevice.phoneprocess.mgr.exercise.HwExerciseConstants;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.homehealth.healthheadlinecard.HealthHeadLinesInfoManager;
import com.tencent.open.SocialConstants;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class ojw {
    private static CopyOnWriteArrayList<enq> e = new CopyOnWriteArrayList<>();
    private static CopyOnWriteArrayList<oly> d = new CopyOnWriteArrayList<>();
    private static long b = System.currentTimeMillis();

    ojw() {
    }

    public static void d() {
        LogUtil.a("AudioRequestUtils", "initHistoryList.");
        b = System.currentTimeMillis();
        e = new CopyOnWriteArrayList<>();
        d = new CopyOnWriteArrayList<>();
    }

    public static void a(BiConsumer<List<enq>, List<oly>> biConsumer, String str, int i, String str2) {
        LogUtil.a("AudioRequestUtils", "requestHistoryListData. mediaId = ", str, "; historyAudioItemList.size = ", Integer.valueOf(e.size()), "; pageNum = ", Integer.valueOf(i), "; audioTime = ", str2);
        if (!j(d)) {
            LogUtil.a("AudioRequestUtils", "first media is not for today. refresh historyList.");
            d();
        }
        int i2 = -29;
        if (i == 0) {
            if (c(e, str)) {
                LogUtil.a("AudioRequestUtils", "has cacheHistoryInfo. no need to request.");
                oli.a().e(e, d);
                biConsumer.accept(e, d);
                return;
            }
            if (!TextUtils.isEmpty(str2)) {
                try {
                    int e2 = jdl.e(DateFormatUtil.d(str2, DateFormatUtil.DateFormatType.DATE_FORMAT_YYYY_MM_DD).getTime(), b);
                    if (e2 > 30) {
                        i2 = (-(((e2 / 30) + 1) * 30)) + 1;
                    }
                } catch (ParseException e3) {
                    ReleaseLogUtil.e("R_AudioRequestUtils", "requestHistoryListData ", ExceptionUtils.d(e3));
                }
            }
            a(a(b, i2), b, biConsumer, str, true);
            return;
        }
        if (i * 30 < e.size()) {
            LogUtil.a("AudioRequestUtils", "has cacheHistoryInfo. no need to request.");
            oli.a().e(e, d);
            biConsumer.accept(e, d);
            return;
        }
        a(a(b, -29), b, biConsumer, str, false);
    }

    /* renamed from: ojw$1, reason: invalid class name */
    class AnonymousClass1 extends UiCallback<List<oma>> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ long f15743a;
        final /* synthetic */ BiConsumer b;
        final /* synthetic */ String c;
        final /* synthetic */ boolean d;

        AnonymousClass1(BiConsumer biConsumer, long j, boolean z, String str) {
            this.b = biConsumer;
            this.f15743a = j;
            this.d = z;
            this.c = str;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.b("AudioRequestUtils", "getListHistoryData failed, errorCode: ", Integer.valueOf(i), " errorInfo: ", str);
            this.b.accept(null, null);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onSuccess(List<oma> list) {
            if (koq.b(list)) {
                ReleaseLogUtil.c("AudioRequestUtils", "getListHistoryData request failed, workoutHistoryList is empty!");
                return;
            }
            Collections.sort(list, new Comparator() { // from class: ojy
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    int compare;
                    compare = Long.compare(gib.a(((oma) obj2).b()), gib.a(((oma) obj).b()));
                    return compare;
                }
            });
            List<enq> e = ojw.e(list);
            List<oly> a2 = ojw.a(list);
            ojw.e.addAll(e);
            ojw.d.addAll(a2);
            long unused = ojw.b = ojw.a(this.f15743a, -1);
            if (!this.d || ojw.c(e, this.c)) {
                oli.a().e(ojw.e, ojw.d);
                this.b.accept(ojw.e, ojw.d);
            } else {
                LogUtil.a("AudioRequestUtils", "not contain audio. continue request. mediaId = ", this.c);
                ojw.a(ojw.a(ojw.b, -29), ojw.b, this.b, this.c, this.d);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void a(long j, long j2, BiConsumer<List<enq>, List<oly>> biConsumer, String str, boolean z) {
        HealthHeadLinesInfoManager.d().d(new AnonymousClass1(biConsumer, j, z, str), j, j2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean c(List<enq> list, String str) {
        if (koq.b(list)) {
            LogUtil.h("AudioRequestUtils", "audioItemList empty in isContainAudio");
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            LogUtil.a("AudioRequestUtils", "mediaId is empty. choose the first mediaId.");
            return true;
        }
        Iterator<enq> it = list.iterator();
        while (it.hasNext()) {
            if (TextUtils.equals(it.next().h(), str)) {
                return true;
            }
        }
        return false;
    }

    public static void a() {
        LogUtil.a("AudioRequestUtils", "updateHistoryList. mLastHistoryQueryTime = ", Long.valueOf(b));
        a(a(b, -29), b, new BiConsumer<List<enq>, List<oly>>() { // from class: ojw.5
            @Override // com.google.android.gms.common.util.BiConsumer
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void accept(List<enq> list, List<oly> list2) {
                oli.a().b(list, list2, oli.a().g(), null);
            }
        }, "", false);
    }

    public static void e(final Consumer<JSONArray> consumer, final BiConsumer<List<enq>, List<oly>> biConsumer, List<oly> list) {
        LogUtil.a("AudioRequestUtils", "requestUserBehaviorList");
        if (koq.c(list)) {
            biConsumer.accept(c(list), list);
            return;
        }
        HealthHeadLinesInfoManager d2 = HealthHeadLinesInfoManager.d();
        if (d2 == null) {
            LogUtil.b("AudioRequestUtils", "infoManager is null");
        } else {
            d2.b(new UiCallback<List<oly>>() { // from class: ojw.4
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.b("AudioRequestUtils", "requestUserBehaviorList failed, errorCode: ", Integer.valueOf(i), " errorInfo: ", str);
                    Consumer.this.accept(null);
                    biConsumer.accept(null, null);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<oly> list2) {
                    if (koq.b(list2)) {
                        ReleaseLogUtil.c("AudioRequestUtils", "requestUserBehaviorList request failed, audioWorkoutDetails is empty!");
                        return;
                    }
                    LogUtil.a("AudioRequestUtils", "audioWorkoutDetails: ", list2);
                    biConsumer.accept(ojw.c(list2), list2);
                    Consumer.this.accept(ojw.b(list2));
                }
            });
        }
    }

    public static void c(Consumer<JSONArray> consumer, BiConsumer<List<enq>, List<oly>> biConsumer, List<oly> list) {
        LogUtil.a("AudioRequestUtils", "requestUserPlayRecord");
        if (koq.c(list)) {
            biConsumer.accept(c(list), list);
            return;
        }
        HealthHeadLinesInfoManager d2 = HealthHeadLinesInfoManager.d();
        if (d2 == null) {
            LogUtil.b("AudioRequestUtils", "infoManager is null");
        } else {
            d2.f(new AnonymousClass2(consumer, biConsumer));
        }
    }

    /* renamed from: ojw$2, reason: invalid class name */
    class AnonymousClass2 extends UiCallback<List<oly>> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ BiConsumer f15744a;
        final /* synthetic */ Consumer c;

        AnonymousClass2(Consumer consumer, BiConsumer biConsumer) {
            this.c = consumer;
            this.f15744a = biConsumer;
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        public void onFailure(int i, String str) {
            LogUtil.b("AudioRequestUtils", "getUserPlayRecord failed, errorCode: ", Integer.valueOf(i), " errorInfo: ", str);
            this.c.accept(null);
            this.f15744a.accept(null, null);
        }

        @Override // com.huawei.basefitnessadvice.callback.UiCallback
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onSuccess(List<oly> list) {
            if (koq.b(list)) {
                ReleaseLogUtil.c("AudioRequestUtils", "getUserPlayRecord request failed, audioWorkoutDetails is empty!");
                return;
            }
            LogUtil.a("AudioRequestUtils", "audioWorkoutDetails: ", list);
            Collections.sort(list, new Comparator() { // from class: ojv
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    int compare;
                    compare = Long.compare(((oly) obj2).c(), ((oly) obj).c());
                    return compare;
                }
            });
            this.f15744a.accept(ojw.c(list), list);
            this.c.accept(ojw.b(list));
        }
    }

    public static void a(String str, final Consumer<JSONArray> consumer, final BiConsumer<List<enq>, List<oly>> biConsumer, List<oly> list) {
        LogUtil.a("AudioRequestUtils", "requestLecturerInfo");
        if (koq.c(list)) {
            biConsumer.accept(c(list), list);
            return;
        }
        HealthHeadLinesInfoManager d2 = HealthHeadLinesInfoManager.d();
        if (d2 == null) {
            LogUtil.b("AudioRequestUtils", "infoManager is null");
        } else {
            d2.b(str, new UiCallback<List<oly>>() { // from class: ojw.3
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str2) {
                    LogUtil.b("AudioRequestUtils", "requestLecturerInfo failed, errorCode: ", Integer.valueOf(i), " errorInfo: ", str2);
                    BiConsumer.this.accept(null, null);
                    consumer.accept(null);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: d, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<oly> list2) {
                    if (koq.b(list2)) {
                        ReleaseLogUtil.c("AudioRequestUtils", "getLecturerInfo request failed, topicLists is empty!");
                        return;
                    }
                    BiConsumer.this.accept(ojw.c(list2), list2);
                    consumer.accept(ojw.b(list2));
                }
            });
        }
    }

    public static void c(String str, final BiConsumer<List<omb>, Integer> biConsumer, final int i) {
        LogUtil.a("AudioRequestUtils", "requestWorkoutsByTopicId");
        HealthHeadLinesInfoManager d2 = HealthHeadLinesInfoManager.d();
        if (d2 == null) {
            LogUtil.b("AudioRequestUtils", "infoManager is null");
        } else {
            d2.e(str, new HealthHeadLinesInfoManager.RequestCallBack<List<omb>, Integer>() { // from class: ojw.8
                @Override // com.huawei.ui.homehealth.healthheadlinecard.HealthHeadLinesInfoManager.RequestCallBack
                public void onFailure(int i2, String str2) {
                    LogUtil.b("AudioRequestUtils", "requestWorkoutsByTopicId failed, errorCode: ", Integer.valueOf(i2), " errorInfo: ", str2);
                    BiConsumer.this.accept(null, null);
                }

                @Override // com.huawei.ui.homehealth.healthheadlinecard.HealthHeadLinesInfoManager.RequestCallBack
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onSuccess(List<omb> list, Integer num) {
                    if (koq.b(list)) {
                        ReleaseLogUtil.c("AudioRequestUtils", "requestWorkoutsByTopicId request failed, audioWorkoutByTopicList is empty!");
                        BiConsumer.this.accept(null, null);
                    } else if (num != null && i * 20 > num.intValue()) {
                        LogUtil.b("AudioRequestUtils", "requestWorkoutsByTopicId redundant, cause request size exceed totalSize, ", "pageNum = ", Integer.valueOf(i), ", totalSize = ", num);
                        BiConsumer.this.accept(null, null);
                    } else {
                        BiConsumer.this.accept(list, num);
                    }
                }
            }, i);
        }
    }

    public static void e(final Consumer<Long> consumer) {
        LogUtil.a("AudioRequestUtils", "requestAccumulatedDuration");
        HealthHeadLinesInfoManager d2 = HealthHeadLinesInfoManager.d();
        if (d2 == null) {
            LogUtil.b("AudioRequestUtils", "infoManager is null");
        } else {
            d2.a(new UiCallback<Long>() { // from class: ojw.7
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.b("AudioRequestUtils", "requestAccumulatedDuration failed, errorCode: ", Integer.valueOf(i), " errorInfo: ", str);
                    Consumer.this.accept(null);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onSuccess(Long l) {
                    Consumer.this.accept(l);
                }
            });
        }
    }

    public static List<oly> d(List<omb> list) {
        LogUtil.a("AudioRequestUtils", "dealTopicList");
        ArrayList arrayList = new ArrayList();
        for (omb ombVar : list) {
            if (ombVar == null) {
                LogUtil.b("AudioRequestUtils", "workoutTopicList is null");
                return Collections.emptyList();
            }
            oly e2 = ombVar.e();
            if (e2 == null) {
                LogUtil.b("AudioRequestUtils", "audioWorkoutDetail is null");
                return Collections.emptyList();
            }
            arrayList.add(e2);
        }
        return arrayList;
    }

    private static void c(oly olyVar, enq enqVar) {
        omi d2;
        if (olyVar == null) {
            return;
        }
        enqVar.e(olyVar.f());
        if (TextUtils.isEmpty(enqVar.d()) && (d2 = olyVar.d()) != null) {
            enqVar.e(d2.c());
        }
    }

    public static List<enq> c(List<oly> list) {
        ArrayList arrayList = new ArrayList(0);
        if (koq.b(list)) {
            LogUtil.h("AudioRequestUtils", "convertDetailListToAudioItemList failed, cause detailList is empty!");
            return arrayList;
        }
        for (oly olyVar : list) {
            if (olyVar != null) {
                enq enqVar = new enq();
                c(olyVar, enqVar);
                omd a2 = olyVar.a();
                if (a2 != null) {
                    enqVar.h(a2.a());
                    enqVar.j(a2.c());
                    enqVar.d(a2.d());
                }
                d(enqVar, olyVar, a2);
                olw e2 = olyVar.e();
                if (e2 != null) {
                    enqVar.d(e2.d());
                    enqVar.g(e2.e());
                }
                omf m = olyVar.m();
                if (m != null) {
                    enqVar.b(m.c());
                }
                enqVar.c("headLine");
                enqVar.a("huaweihealth://huaweihealth.app/openwith?type=1&address=com.huawei.health.h5.health-headlines?h5pro=true&urlType=4&pName=com.huawei.health&isImmerse");
                arrayList.add(enqVar);
            }
        }
        return arrayList;
    }

    public static List<oly> a(List<oma> list) {
        if (trg.d(list)) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            oma omaVar = list.get(i);
            if (omaVar != null) {
                String b2 = omaVar.b();
                oly d2 = omaVar.d();
                if (d2 != null) {
                    d2.b(b2);
                    arrayList.add(d2);
                }
            }
        }
        return arrayList;
    }

    public static List<enq> e(List<oma> list) {
        ArrayList arrayList = new ArrayList();
        if (koq.b(list)) {
            return arrayList;
        }
        StringBuilder sb = new StringBuilder();
        for (oma omaVar : list) {
            enq enqVar = new enq();
            oly d2 = omaVar.d();
            if (d2 != null) {
                enqVar.e(omaVar.b());
                omd a2 = d2.a();
                if (a2 != null) {
                    enqVar.h(a2.a());
                    enqVar.j(a2.c());
                    enqVar.d(a2.d());
                }
                d(enqVar, d2, a2);
                olw e2 = d2.e();
                if (e2 != null) {
                    enqVar.d(e2.d());
                    enqVar.g(e2.e());
                }
                omf m = d2.m();
                if (m != null) {
                    enqVar.b(m.c());
                }
                enqVar.c("headLine");
                enqVar.a("huaweihealth://huaweihealth.app/openwith?type=1&address=com.huawei.health.h5.health-headlines?h5pro=true&urlType=4&pName=com.huawei.health&isImmerse");
                sb.append(" title=");
                sb.append(enqVar.n());
                sb.append(" date=");
                sb.append(enqVar.d());
                arrayList.add(enqVar);
            }
        }
        ReleaseLogUtil.e("R_AudioRequestUtils", sb.toString());
        return arrayList;
    }

    private static void d(enq enqVar, oly olyVar, omd omdVar) {
        String b2;
        String j;
        String str;
        if (!olyVar.h().equals("")) {
            b2 = olyVar.h();
        } else {
            b2 = olyVar.b();
        }
        if (!olyVar.g().equals("")) {
            j = olyVar.g();
        } else {
            j = olyVar.j();
        }
        if (omdVar == null || TextUtils.isEmpty(omdVar.e())) {
            str = b2 + " " + j;
        } else {
            str = omdVar.e();
        }
        enqVar.f(str);
    }

    public static JSONArray b(List<oly> list) {
        String str;
        String str2;
        String str3;
        long j;
        String str4;
        String str5;
        String str6;
        String str7;
        LogUtil.a("AudioRequestUtils", "transformListToJson");
        JSONArray jSONArray = new JSONArray();
        if (list == null) {
            return jSONArray;
        }
        Iterator<oly> it = list.iterator();
        while (it.hasNext()) {
            oly next = it.next();
            String str8 = null;
            int i = 0;
            if (next == null) {
                LogUtil.b("AudioRequestUtils", "workoutDetail is empty");
                return null;
            }
            long o = next.o();
            int l = next.l();
            String f = next.f();
            omd a2 = next.a();
            olw e2 = next.e();
            omf m = next.m();
            ome i2 = next.i();
            omi d2 = next.d();
            if (a2 != null) {
                String c = a2.c();
                String a3 = a2.a();
                String d3 = a2.d();
                long b2 = a2.b();
                str = d3;
                j = b2;
                str3 = c;
                str2 = a3;
            } else {
                str = null;
                str2 = null;
                str3 = null;
                j = 0;
            }
            if (m != null) {
                String d4 = m.d();
                str4 = m.c();
                str8 = d4;
            } else {
                str4 = null;
            }
            if (e2 != null) {
                String e3 = e2.e();
                String c2 = e2.c();
                str5 = str;
                int d5 = (int) e2.d();
                str7 = e3;
                i = d5;
                str6 = c2;
            } else {
                str5 = str;
                str6 = null;
                str7 = null;
            }
            Iterator<oly> it2 = it;
            if (str6 != null) {
                oli.a().c(str6, str3);
            }
            String b3 = i2 != null ? i2.b() : null;
            String c3 = d2 != null ? d2.c() : null;
            JSONArray jSONArray2 = jSONArray;
            String str9 = str6;
            String str10 = str5;
            String str11 = str4;
            String str12 = str2;
            String str13 = str3;
            String str14 = str7;
            LogUtil.a("AudioRequestUtils", "workoutId=", str13, " title=", str12, " audioId=", str9, " audioDuration=", Integer.valueOf(i), " name=", b3, " playRecord=", Long.valueOf(o), " status=", Integer.valueOf(l), " onlineDate=", c3, " createTime=", Long.valueOf(j), " historyDate=", f);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(HwExerciseConstants.METHOD_PARAM_WORKOUT_ID, str13);
                jSONObject.put("title", str12);
                jSONObject.put(SocialConstants.PARAM_APP_DESC, str10);
                jSONObject.put("detailPng", str8);
                jSONObject.put("listPng", str11);
                jSONObject.put("audioId", str9);
                jSONObject.put("audioUrl", str14);
                jSONObject.put("audioDuration", i);
                jSONObject.put("name", b3);
                jSONObject.put("playRecord", o);
                jSONObject.put("status", l);
                if (!TextUtils.isEmpty(f)) {
                    jSONObject.put("onlineDate", f);
                } else if (TextUtils.isEmpty(c3)) {
                    jSONObject.put("onlineDate", gib.j(j));
                } else {
                    jSONObject.put("onlineDate", c3);
                }
            } catch (JSONException unused) {
                LogUtil.b("AudioRequestUtils", "convertDetailListToJsonArray exception happened ");
            }
            jSONArray = jSONArray2;
            jSONArray.put(jSONObject);
            it = it2;
        }
        return jSONArray;
    }

    private static List<oly> c(List<oly> list, int i, int i2) {
        if (koq.b(list)) {
            return new ArrayList();
        }
        int i3 = i * i2;
        int i4 = i2 + i3;
        return (i3 >= list.size() || i3 > i4 || i4 < 0 || i3 < 0) ? new ArrayList() : list.subList(i3, Math.min(i4, list.size()));
    }

    public static JSONArray a(List<oly> list, int i, int i2) {
        List<oly> c = c(list, i, i2);
        LogUtil.a("AudioRequestUtils", "pageNum = ", Integer.valueOf(i), "; detailListByPage = ", Integer.valueOf(c.size()));
        return b(c);
    }

    public static boolean j(List<oly> list) {
        oly olyVar;
        if (koq.b(list) || (olyVar = list.get(0)) == null) {
            return false;
        }
        long d2 = d(olyVar);
        long currentTimeMillis = System.currentTimeMillis();
        boolean d3 = jdl.d(d2, currentTimeMillis);
        if (!d3) {
            LogUtil.a("AudioRequestUtils", "checkIsSameDayOnSaveMediaInfo return false, cause is not same day, lastSaveTime = ", DateFormatUtil.b(d2, DateFormatUtil.DateFormatType.DEFAULT_DATE_FORMAT), ", nowTime = ", DateFormatUtil.b(currentTimeMillis, DateFormatUtil.DateFormatType.DEFAULT_DATE_FORMAT));
        }
        return d3;
    }

    public static long a(long j, int i) {
        return jec.d(new Date(j), i).getTime();
    }

    public static long d(oly olyVar) {
        if (olyVar == null) {
            LogUtil.h("AudioRequestUtils", "workoutDetail is null in getTimeInWorkoutDetail.");
            return 0L;
        }
        omi d2 = olyVar.d();
        if (d2 == null) {
            LogUtil.h("AudioRequestUtils", "timeDaoBean is null in getTimeInWorkoutDetail.");
            return 0L;
        }
        return dpg.d(d2.c());
    }
}
