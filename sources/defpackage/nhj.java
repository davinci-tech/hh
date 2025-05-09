package defpackage;

import android.text.TextUtils;
import android.util.Pair;
import android.util.SparseIntArray;
import com.apprichtap.haptic.base.PrebakedEffectId;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.utils.CollectionUtils;
import com.huawei.haf.design.pattern.ObserverManagerUtil;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.h5pro.core.H5ProWebView;
import com.huawei.health.h5pro.vengine.H5ProExecJsValueCbk;
import com.huawei.health.health.constants.ObserveLabels;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.health.messagecenter.model.MessageConstant;
import com.huawei.hihealth.HiSyncOption;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.openalliance.ad.constant.ParamConstants;
import health.compact.a.CommonUtil;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* loaded from: classes6.dex */
public class nhj {

    /* renamed from: a, reason: collision with root package name */
    public static Pair<Long, String> f15285a;
    private static final SparseIntArray b;
    private static final Long c = 120000L;
    private static final SparseIntArray d;
    private static Boolean e;
    private static boolean g;
    private static boolean h;
    private static boolean i;

    static {
        SparseIntArray sparseIntArray = new SparseIntArray();
        d = sparseIntArray;
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        b = sparseIntArray2;
        sparseIntArray.append(131111, R.string._2130847225_res_0x7f0225f9);
        sparseIntArray.append(131112, R.string._2130847226_res_0x7f0225fa);
        sparseIntArray.append(131113, R.string._2130847227_res_0x7f0225fb);
        sparseIntArray.append(131311, R.string._2130847228_res_0x7f0225fc);
        sparseIntArray.append(131312, R.string._2130847229_res_0x7f0225fd);
        sparseIntArray.append(131313, R.string._2130847230_res_0x7f0225fe);
        sparseIntArray.append(131411, R.string._2130847231_res_0x7f0225ff);
        sparseIntArray.append(131412, R.string._2130847232_res_0x7f022600);
        sparseIntArray.append(131413, R.string._2130847233_res_0x7f022601);
        sparseIntArray.append(141111, R.string._2130847234_res_0x7f022602);
        sparseIntArray.append(141112, R.string._2130847235_res_0x7f022603);
        sparseIntArray.append(141113, R.string._2130847236_res_0x7f022604);
        sparseIntArray.append(141311, R.string._2130847237_res_0x7f022605);
        sparseIntArray.append(141312, R.string._2130847238_res_0x7f022606);
        sparseIntArray.append(141313, R.string._2130847239_res_0x7f022607);
        sparseIntArray.append(141411, R.string._2130847240_res_0x7f022608);
        sparseIntArray.append(141412, R.string._2130847241_res_0x7f022609);
        sparseIntArray.append(141413, R.string._2130847242_res_0x7f02260a);
        sparseIntArray.append(151111, R.string._2130847243_res_0x7f02260b);
        sparseIntArray.append(151112, R.string._2130847244_res_0x7f02260c);
        sparseIntArray.append(151113, R.string._2130847245_res_0x7f02260d);
        sparseIntArray.append(151311, R.string._2130847246_res_0x7f02260e);
        sparseIntArray.append(151312, R.string._2130847247_res_0x7f02260f);
        sparseIntArray.append(151313, R.string._2130847248_res_0x7f022610);
        sparseIntArray.append(151411, R.string._2130847249_res_0x7f022611);
        sparseIntArray.append(151412, R.string._2130847250_res_0x7f022612);
        sparseIntArray.append(151413, R.string._2130847251_res_0x7f022613);
        sparseIntArray.append(161211, R.string._2130847252_res_0x7f022614);
        sparseIntArray2.append(131211, R.plurals._2130903402_res_0x7f03016a);
        sparseIntArray2.append(141211, R.plurals._2130903403_res_0x7f03016b);
        sparseIntArray2.append(151211, R.plurals._2130903404_res_0x7f03016c);
        sparseIntArray2.append(161212, R.plurals._2130903405_res_0x7f03016d);
        sparseIntArray.append(171211, R.string._2130847388_res_0x7f02269c);
        sparseIntArray.append(171212, R.string._2130847388_res_0x7f02269c);
        sparseIntArray.append(171213, R.string._2130847388_res_0x7f02269c);
        sparseIntArray.append(171214, R.string._2130847388_res_0x7f02269c);
        sparseIntArray.append(171215, R.string._2130847388_res_0x7f02269c);
        sparseIntArray.append(172211, R.string._2130847389_res_0x7f02269d);
        sparseIntArray.append(172212, R.string._2130847389_res_0x7f02269d);
        sparseIntArray.append(173211, R.string._2130847390_res_0x7f02269e);
        sparseIntArray.append(173212, R.string._2130847390_res_0x7f02269e);
        sparseIntArray.append(174211, R.string._2130847391_res_0x7f02269f);
        sparseIntArray.append(174212, R.string._2130847391_res_0x7f02269f);
        sparseIntArray.append(174213, R.string._2130847391_res_0x7f02269f);
        sparseIntArray.append(174214, R.string._2130847391_res_0x7f02269f);
        sparseIntArray.append(171311, R.string._2130847392_res_0x7f0226a0);
        sparseIntArray.append(171312, R.string._2130847393_res_0x7f0226a1);
        sparseIntArray.append(171313, R.string._2130847394_res_0x7f0226a2);
        sparseIntArray.append(172311, R.string._2130847395_res_0x7f0226a3);
        sparseIntArray.append(172312, R.string._2130847396_res_0x7f0226a4);
        sparseIntArray.append(172313, R.string._2130847397_res_0x7f0226a5);
        sparseIntArray.append(173311, R.string._2130847398_res_0x7f0226a6);
        sparseIntArray.append(173312, R.string._2130847399_res_0x7f0226a7);
        sparseIntArray.append(173313, R.string._2130847400_res_0x7f0226a8);
        sparseIntArray.append(174311, R.string._2130847401_res_0x7f0226a9);
        sparseIntArray.append(174314, R.string._2130847401_res_0x7f0226a9);
        sparseIntArray.append(174315, R.string._2130847401_res_0x7f0226a9);
        sparseIntArray.append(174312, R.string._2130847402_res_0x7f0226aa);
        sparseIntArray.append(174313, R.string._2130847403_res_0x7f0226ab);
        sparseIntArray.append(171411, R.string._2130847404_res_0x7f0226ac);
        sparseIntArray.append(171412, R.string._2130847405_res_0x7f0226ad);
        sparseIntArray.append(171413, R.string._2130847406_res_0x7f0226ae);
        sparseIntArray.append(172411, R.string._2130847407_res_0x7f0226af);
        sparseIntArray.append(172412, R.string._2130847408_res_0x7f0226b0);
        sparseIntArray.append(172413, R.string._2130847409_res_0x7f0226b1);
        sparseIntArray.append(173411, R.string._2130847410_res_0x7f0226b2);
        sparseIntArray.append(173412, R.string._2130847411_res_0x7f0226b3);
        sparseIntArray.append(173413, R.string._2130847412_res_0x7f0226b4);
        sparseIntArray.append(174411, R.string._2130847413_res_0x7f0226b5);
        sparseIntArray.append(174412, R.string._2130847414_res_0x7f0226b6);
        sparseIntArray.append(174413, R.string._2130847415_res_0x7f0226b7);
        sparseIntArray.append(181211, R.string._2130847375_res_0x7f02268f);
        sparseIntArray.append(181212, R.string._2130847375_res_0x7f02268f);
        sparseIntArray.append(181213, R.string._2130847375_res_0x7f02268f);
        sparseIntArray.append(181214, R.string._2130847375_res_0x7f02268f);
        sparseIntArray.append(181215, R.string._2130847375_res_0x7f02268f);
        sparseIntArray.append(182311, R.string._2130847376_res_0x7f022690);
        sparseIntArray.append(182312, R.string._2130847377_res_0x7f022691);
        sparseIntArray.append(182313, R.string._2130847378_res_0x7f022692);
        sparseIntArray.append(183311, R.string._2130847379_res_0x7f022693);
        sparseIntArray.append(183312, R.string._2130847380_res_0x7f022694);
        sparseIntArray.append(183313, R.string._2130847381_res_0x7f022695);
        sparseIntArray.append(182411, R.string._2130847382_res_0x7f022696);
        sparseIntArray.append(182412, R.string._2130847383_res_0x7f022697);
        sparseIntArray.append(182413, R.string._2130847384_res_0x7f022698);
        sparseIntArray.append(183411, R.string._2130847385_res_0x7f022699);
        sparseIntArray.append(183412, R.string._2130847386_res_0x7f02269a);
        sparseIntArray.append(183413, R.string._2130847387_res_0x7f02269b);
    }

    public static boolean n() {
        return Utils.i() && !Utils.c(BaseApplication.e());
    }

    public static void b(List<Integer> list, long j) {
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncId(j);
        hiSyncOption.setForceSync(true);
        hiSyncOption.setSyncDataTypes(list);
        hiSyncOption.setSyncMethod(2);
        hiSyncOption.setSyncScope(1);
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        HiHealthNativeApi.a(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).synCloud(hiSyncOption, null);
    }

    public static boolean e(List<Integer> list, String str) {
        if (CollectionUtils.d(list) || TextUtils.isEmpty(str)) {
            ReleaseLogUtil.a("SummaryUtil", "isSyncDataType syncDataTypeList ", list, " syncDataResultType ", str);
            return false;
        }
        int h2 = CommonUtil.h(str);
        if (h2 != 0) {
            return list.contains(Integer.valueOf(h2)) || h2 == 20000;
        }
        List list2 = (List) HiJsonUtil.b(str, new TypeToken<List<Integer>>() { // from class: nhj.5
        }.getType());
        if (CollectionUtils.d(list2)) {
            ReleaseLogUtil.a("SummaryUtil", "isSyncDataType syncDataResultTypeList ", list2, " syncDataResultType ", str, " syncDataTypeList ", list);
            return false;
        }
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            if (list.contains((Integer) it.next())) {
                return true;
            }
        }
        return false;
    }

    public static List<Integer> f() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(20000);
        return arrayList;
    }

    public static List<Integer> d() {
        ArrayList arrayList = new ArrayList(7);
        arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.SLEEP_RECORD.value()));
        arrayList.add(9);
        arrayList.add(3);
        arrayList.add(10007);
        arrayList.add(10010);
        arrayList.add(7);
        arrayList.add(16);
        return arrayList;
    }

    public static List<Integer> i() {
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.SLEEP_RECORD.value()));
        arrayList.add(10010);
        arrayList.add(7);
        arrayList.add(16);
        return arrayList;
    }

    public static List<Integer> c() {
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.SPORT_GOAL_ACHIEVEMENT_DATA.value()));
        arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.ACTIVE_HOUR.value()));
        arrayList.add(10008);
        arrayList.add(9);
        return arrayList;
    }

    public static void b(final String str, H5ProWebView h5ProWebView) {
        if (h5ProWebView == null) {
            ReleaseLogUtil.a(str, "refreshH5 mWebView is null");
        } else {
            ReleaseLogUtil.b(str, "refreshH5");
            h5ProWebView.execJs("window.nativeEvent&&window.nativeEvent('onResume')", new H5ProExecJsValueCbk() { // from class: nho
                @Override // com.huawei.health.h5pro.vengine.H5ProExecJsValueCbk
                public final void onReceiveValue(String str2) {
                    ReleaseLogUtil.b(str, "refreshH5 onReceiveValue string ", str2);
                }
            });
        }
    }

    public static void c(String str, H5ProWebView h5ProWebView, List<H5ProWebView> list) {
        if (h5ProWebView == null) {
            ReleaseLogUtil.a(str, "onDetachedFromWindow mWebView is null");
            return;
        }
        h5ProWebView.onDetachedFromWindow();
        if (CollectionUtils.d(list)) {
            ReleaseLogUtil.a(str, "onDetachedFromWindow h5ProWebViewList ", list);
            return;
        }
        for (H5ProWebView h5ProWebView2 : list) {
            if (h5ProWebView2 == null) {
                ReleaseLogUtil.a(str, "onDetachedFromWindow webView is null");
            } else {
                h5ProWebView2.onDetachedFromWindow();
            }
        }
    }

    public static boolean c(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            ReleaseLogUtil.a("R_SummaryUtil", "isSupportShowSummary name ", str2);
            return false;
        }
        nht nhtVar = (nht) HiJsonUtil.e(str, nht.class);
        if (nhtVar == null) {
            ReleaseLogUtil.a("R_SummaryUtil", "isSupportShowSummary summaryResponse is null name ", str2);
            return false;
        }
        nhp b2 = nhtVar.b();
        if (b2 == null) {
            ReleaseLogUtil.a("R_SummaryUtil", "isSupportShowSummary highlights is null");
            return false;
        }
        List<nhn> b3 = b2.b();
        if (CollectionUtils.d(b3)) {
            ReleaseLogUtil.a("R_SummaryUtil", "isSupportShowSummary list ", b3);
            return false;
        }
        for (nhn nhnVar : b3) {
            if (nhnVar == null) {
                ReleaseLogUtil.a("R_SummaryUtil", "isSupportShowSummary domainTopCardId is null");
            } else if (Objects.equals(str2, nhnVar.b())) {
                return true;
            }
        }
        return false;
    }

    public static int c(nhp nhpVar, String str) {
        if (nhpVar == null) {
            ReleaseLogUtil.a("SummaryUtil", "getCardId highlights is null");
            return 0;
        }
        List<nhn> b2 = nhpVar.b();
        if (CollectionUtils.d(b2)) {
            ReleaseLogUtil.a("SummaryUtil", "getCardId domainTopCardIdList ", b2);
            return 0;
        }
        for (nhn nhnVar : b2) {
            if (nhnVar == null) {
                LogUtil.a("SummaryUtil", "getCardId domainTopCardId is null");
            } else if (!TextUtils.isEmpty(str) && !str.equals(nhnVar.b())) {
                LogUtil.a("SummaryUtil", "getCardId name ", str, " domainTopCardId ", nhnVar);
            } else {
                List<Integer> e2 = nhnVar.e();
                if (CollectionUtils.d(e2)) {
                    LogUtil.a("SummaryUtil", "getCardId cardIdList ", e2, " domainTopCardId ", nhnVar, " name ", str);
                } else {
                    Integer num = e2.get(0);
                    if (num == null) {
                        LogUtil.a("SummaryUtil", "getCardId cardIdInteger is null");
                    } else {
                        return num.intValue();
                    }
                }
            }
        }
        return 0;
    }

    private static nhr e(nhp nhpVar, int i2) {
        if (nhpVar == null) {
            ReleaseLogUtil.a("SummaryUtil", "getDomainCard highlights is null");
            return null;
        }
        List<nhr> a2 = nhpVar.a();
        if (CollectionUtils.d(a2)) {
            ReleaseLogUtil.a("SummaryUtil", "getDomainCard domainCardList ", a2);
            return null;
        }
        for (nhr nhrVar : a2) {
            if (nhrVar == null) {
                LogUtil.a("SummaryUtil", "getCardId domainCard is null");
            } else if (i2 == nhrVar.e()) {
                return nhrVar;
            }
        }
        LogUtil.a("SummaryUtil", "getCardId domainCard is null");
        return null;
    }

    public static String c(nhp nhpVar, int i2) {
        nhr e2 = e(nhpVar, i2);
        if (e2 == null) {
            ReleaseLogUtil.a("SummaryUtil", "getActiveRecordText domainCard is null");
            return "";
        }
        nhq c2 = e2.c();
        if (c2 == null) {
            ReleaseLogUtil.a("SummaryUtil", "getActiveRecordText data is null");
            return "";
        }
        int e3 = c2.e();
        String d2 = e2.d();
        String a2 = e2.a();
        if (!"week".equals(d2) || TextUtils.isEmpty(a2)) {
            return nsf.h(d.get(e3));
        }
        if (a2.equals("activityRingChart")) {
            int d3 = c2.d();
            if (d3 <= 0) {
                return nsf.h(d.get(e3));
            }
            return nsf.a(b.get(e3), d3, UnitUtil.e(d3, 1, 0));
        }
        int c3 = c2.c();
        return nsf.a(b.get(e3), c3, UnitUtil.e(c3, 1, 0));
    }

    public static String d(nhp nhpVar, int i2) {
        nhr e2 = e(nhpVar, i2);
        if (e2 == null) {
            ReleaseLogUtil.a("SummaryUtil", "getBloodPressureText domainCard is null");
            return "";
        }
        nhq c2 = e2.c();
        if (c2 == null) {
            ReleaseLogUtil.a("SummaryUtil", "getBloodPressureText data is null");
            return "";
        }
        int e3 = c2.e();
        String d2 = e2.d();
        if (e2.a().equals("dualDotLineChart") && "week".equals(d2)) {
            return nsf.b(d.get(e3), UnitUtil.e(c2.b(), 1, 0), UnitUtil.e(c2.a(), 1, 0));
        }
        return nsf.h(d.get(e3));
    }

    public static String a(nhp nhpVar, int i2) {
        nhr e2 = e(nhpVar, i2);
        if (e2 == null) {
            ReleaseLogUtil.a("SummaryUtil", "getEmotionHealthText domainCard is null");
            return "";
        }
        nhq c2 = e2.c();
        if (c2 == null) {
            ReleaseLogUtil.a("SummaryUtil", "getEmotionHealthText data is null");
            return "";
        }
        int e3 = c2.e();
        String d2 = e2.d();
        if ("dotLineChart".equals(e2.a()) && "week".equals(d2)) {
            return nsf.b(d.get(e3), UnitUtil.e(c2.c(), (e3 < 171211 || e3 > 171215) ? 2 : 1, 0));
        }
        return nsf.h(d.get(e3));
    }

    public static void b(String str) {
        f15285a = new Pair<>(Long.valueOf(System.currentTimeMillis()), str);
    }

    public static Pair<Long, String> cuW_() {
        return f15285a;
    }

    public static void l() {
        i = true;
    }

    public static boolean j() {
        return i;
    }

    public static void o() {
        g = true;
    }

    public static boolean k() {
        return g;
    }

    public static void t() {
        h = true;
    }

    public static boolean m() {
        return h;
    }

    public static void q() {
        f15285a = null;
        i = false;
        g = false;
        h = false;
    }

    public static List<Integer> b() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(5);
        return arrayList;
    }

    public static void p() {
        long s = s();
        long currentTimeMillis = System.currentTimeMillis();
        long e2 = e();
        Long l = c;
        if (currentTimeMillis - s < l.longValue() || currentTimeMillis - e2 < l.longValue()) {
            LogUtil.a("SummaryUtil", "Less than the number of minutes since the last sync, given by the condition");
            return;
        }
        b(currentTimeMillis);
        LogUtil.c("SummaryUtil", "start sync");
        d(Long.valueOf(currentTimeMillis), Long.valueOf(s));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(long j) {
        LogUtil.c("SummaryUtil", "setLongForSharedPreferences key ", "SLEEP_SUMMARY_LAST_SYNC_TIME", " value ", Long.valueOf(j));
        SharedPreferenceManager.e(Integer.toString(PrebakedEffectId.RT_COIN_DROP), "SLEEP_SUMMARY_LAST_SYNC_TIME", j);
    }

    private static long s() {
        return SharedPreferenceManager.b(Integer.toString(PrebakedEffectId.RT_COIN_DROP), "SLEEP_SUMMARY_LAST_SYNC_TIME", 0L);
    }

    public static void b(final List<String> list, final int i2) {
        if (CollectionUtils.d(list)) {
            LogUtil.a("SummaryUtil", "list is empty");
        } else if (!n()) {
            LogUtil.a("SummaryUtil", "is not Support Summary");
        } else {
            ThreadPoolManager.d().execute(new Runnable() { // from class: nhm
                @Override // java.lang.Runnable
                public final void run() {
                    nhj.d((List<String>) list, i2);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(final List<String> list, final int i2) {
        nhf.c(new ResponseCallback() { // from class: nhi
            @Override // com.huawei.hwbasemgr.ResponseCallback
            public final void onResponse(int i3, Object obj) {
                nhj.d(i2, list, i3, (nhp) obj);
            }
        });
    }

    static /* synthetic */ void d(int i2, List list, int i3, nhp nhpVar) {
        LogUtil.c("SummaryUtil", "getSummaryToCard totalCardSize ", Integer.valueOf(i2), " errorCode ", Integer.valueOf(i3), " highlights ", nhpVar);
        HashMap hashMap = new HashMap();
        if (list.contains("activityRings")) {
            int c2 = c(nhpVar, "activityRings");
            String c3 = c(nhpVar, c2);
            if (!TextUtils.isEmpty(c3)) {
                hashMap.put("activityRings", new Pair(Integer.valueOf(c2), c3));
            }
        }
        if (list.contains(MessageConstant.BLOOD_PRESSURE_TYPE)) {
            int c4 = c(nhpVar, MessageConstant.BLOOD_PRESSURE_TYPE);
            String d2 = d(nhpVar, c4);
            if (!TextUtils.isEmpty(d2)) {
                hashMap.put(MessageConstant.BLOOD_PRESSURE_TYPE, new Pair(Integer.valueOf(c4), d2));
            }
        }
        if (list.contains("emotionalHealth")) {
            int c5 = c(nhpVar, "emotionalHealth");
            String a2 = a(nhpVar, c5);
            if (!TextUtils.isEmpty(a2)) {
                hashMap.put("emotionalHealth", new Pair(Integer.valueOf(c5), a2));
            }
        }
        a(hashMap, i3, i2);
    }

    public static void a(HashMap<String, Pair<Integer, String>> hashMap, int i2, int i3) {
        HashMap hashMap2 = new HashMap();
        ArrayList arrayList = new ArrayList(hashMap.keySet());
        Collections.shuffle(arrayList);
        int round = Math.round(i3 * 0.25f);
        if (round > arrayList.size()) {
            round = arrayList.size();
        }
        if (round == 0) {
            LogUtil.a("SummaryUtil", "size is zero, keyList.size is : ", Integer.valueOf(arrayList.size()), "totalSize is : ", Integer.valueOf(i3));
            return;
        }
        for (int i4 = 0; i4 < round; i4++) {
            hashMap2.put((String) arrayList.get(i4), hashMap.get(arrayList.get(i4)));
        }
        ObserverManagerUtil.c(ObserveLabels.SUMMARY_DATA_INIT, Integer.valueOf(i2), hashMap2);
    }

    public static long e() {
        return SharedPreferenceManager.b(Integer.toString(10000), "last_cold_start_sync_time", 0L);
    }

    private static void d(Long l, final Long l2) {
        d(l, new HiCommonListener() { // from class: nhj.3
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i2, Object obj) {
                LogUtil.c("SummaryUtil", "sync success ");
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i2, Object obj) {
                LogUtil.c("SummaryUtil", "sync fail , time back", l2);
                nhj.b(l2.longValue());
            }
        });
    }

    private static void d(Long l, HiCommonListener hiCommonListener) {
        if (n()) {
            HiHealthNativeApi.a(com.huawei.hwcommonmodel.application.BaseApplication.getContext()).synCloud(a(Boolean.TRUE.equals(e) ? i() : d(), l), hiCommonListener);
            LogUtil.c("SummaryUtil", " syncSleepSummary start:", l);
        }
    }

    private static HiSyncOption a(List<Integer> list, Long l) {
        HiSyncOption hiSyncOption = new HiSyncOption();
        hiSyncOption.setSyncId(l.longValue());
        hiSyncOption.setForceSync(true);
        hiSyncOption.setSyncDataTypes(list);
        hiSyncOption.setSyncMethod(2);
        hiSyncOption.setSyncModel(2);
        hiSyncOption.setSyncAction(0);
        hiSyncOption.setSyncScope(1);
        return hiSyncOption;
    }

    public static void c(Object obj) {
        e = Boolean.valueOf((obj instanceof Boolean) && ((Boolean) obj).booleanValue());
    }

    public static Boolean a() {
        return e;
    }

    public static void c(String str, boolean z) {
        HashMap hashMap = new HashMap(3);
        hashMap.put("event", 0);
        hashMap.put(FunctionSetBeanReader.BI_ELEMENT, "m0");
        hashMap.put(ParamConstants.Param.VIEW_TYPE, "day_view");
        if (z) {
            hashMap.put("entrance", "e1");
        }
        iyb iybVar = new iyb();
        iybVar.d(hashMap);
        if (!TextUtils.isEmpty(str)) {
            HashMap hashMap2 = new HashMap(1);
            hashMap2.put("pageId", str);
            iybVar.e(hashMap2);
        }
        ixx.d().a(BaseApplication.e(), AnalyticsValue.SUMMARY_EXPOSURE_H2010222003.value(), iybVar, 0);
    }

    public static List<Integer> h() {
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(Integer.valueOf(DicDataTypeUtil.DataType.SLEEP_RECORD.value()));
        arrayList.add(9);
        arrayList.add(3);
        arrayList.add(10007);
        return arrayList;
    }
}
