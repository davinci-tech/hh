package defpackage;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextUtils;
import android.widget.ImageView;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.MonthlyReportTemplate;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.marketing.datatype.SingleMonth;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hwcloudmodel.callback.GrsQueryCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.pluginachievement.impl.AchieveCallback;
import com.huawei.pluginachievement.manager.model.RecentMonthRecordFromDB;
import com.huawei.pluginachievement.manager.model.RecentWeekRecordFromDB;
import defpackage.mkx;
import health.compact.a.GRSManager;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* loaded from: classes6.dex */
public class mkx {
    private static List<SingleMonth> d;
    public static final int c = Color.parseColor("#FFA3E5A3");
    public static final int e = Color.parseColor("#FF47CC47");

    public static ArrayList<String> d(long j, boolean z) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        if (z) {
            calendar.setFirstDayOfWeek(1);
        } else {
            calendar.setFirstDayOfWeek(2);
        }
        ArrayList<String> arrayList = new ArrayList<>();
        calendar.add(5, calendar.getFirstDayOfWeek() - calendar.get(7));
        arrayList.add(g(calendar.getTimeInMillis()));
        for (int i = 0; i < 6; i++) {
            calendar.add(5, 1);
            arrayList.add(g(calendar.getTimeInMillis()));
        }
        return arrayList;
    }

    private static String g(long j) {
        if (LanguageUtil.j(BaseApplication.getContext()) || LanguageUtil.p(BaseApplication.getContext())) {
            return i(j);
        }
        return j(j);
    }

    private static String i(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        switch (calendar.get(7)) {
            case 1:
                return BaseApplication.getContext().getResources().getString(R.string._2130840976_res_0x7f020d90);
            case 2:
                return BaseApplication.getContext().getResources().getString(R.string._2130840977_res_0x7f020d91);
            case 3:
                return BaseApplication.getContext().getResources().getString(R.string._2130840978_res_0x7f020d92);
            case 4:
                return BaseApplication.getContext().getResources().getString(R.string._2130840979_res_0x7f020d93);
            case 5:
                return BaseApplication.getContext().getResources().getString(R.string._2130840980_res_0x7f020d94);
            case 6:
                return BaseApplication.getContext().getResources().getString(R.string._2130840981_res_0x7f020d95);
            case 7:
                return BaseApplication.getContext().getResources().getString(R.string._2130840982_res_0x7f020d96);
            default:
                return "";
        }
    }

    private static String j(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        switch (calendar.get(7)) {
            case 1:
                return UnitUtil.e(7.0d, 1, 0);
            case 2:
                return UnitUtil.e(1.0d, 1, 0);
            case 3:
                return UnitUtil.e(2.0d, 1, 0);
            case 4:
                return UnitUtil.e(3.0d, 1, 0);
            case 5:
                return UnitUtil.e(4.0d, 1, 0);
            case 6:
                return UnitUtil.e(5.0d, 1, 0);
            case 7:
                return UnitUtil.e(6.0d, 1, 0);
            default:
                return "";
        }
    }

    public static ArrayList<String> b(long j, long j2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j2);
        int date = calendar.getTime().getDate();
        int[] iArr = {1, 8, 15, 22};
        ArrayList<String> arrayList = new ArrayList<>(date);
        for (int i = 1; i <= date; i++) {
            boolean z = false;
            for (int i2 = 0; i2 < 4; i2++) {
                if (i == iArr[i2]) {
                    z = true;
                }
            }
            if (z || i == date) {
                arrayList.add(mlg.a(((i - 1) * 86400000) + j, 3));
            } else {
                arrayList.add("");
            }
        }
        return arrayList;
    }

    public static long e(long j, int i, boolean z) {
        if (z) {
            return b(j, i);
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(2);
        calendar.setTimeInMillis(j);
        if (i == 1) {
            calendar.set(11, 0);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
            calendar.set(7, 2);
            return calendar.getTimeInMillis();
        }
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.set(14, 999);
        calendar.set(7, 1);
        return calendar.getTimeInMillis();
    }

    public static long b(long j, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(1);
        calendar.setTimeInMillis(j);
        if (i == 1) {
            calendar.set(11, 0);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
            calendar.set(7, 1);
            return calendar.getTimeInMillis();
        }
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.set(14, 999);
        calendar.set(7, 7);
        return calendar.getTimeInMillis();
    }

    public static long d(int i, long j, int i2) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        calendar.add(2, i);
        if (i2 == 1) {
            calendar.set(5, calendar.getActualMinimum(5));
            calendar.set(11, 0);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
        } else {
            calendar.set(5, calendar.getActualMaximum(5));
            calendar.set(11, 23);
            calendar.set(12, 59);
            calendar.set(13, 59);
            calendar.set(14, 999);
        }
        return calendar.getTimeInMillis();
    }

    public static long b(int i, int i2, boolean z) {
        Calendar calendar = Calendar.getInstance();
        if (z) {
            calendar.setFirstDayOfWeek(1);
        } else {
            calendar.setFirstDayOfWeek(2);
        }
        calendar.add(5, i2 * (-7));
        if (i == 1) {
            calendar.set(11, 0);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
            if (z) {
                calendar.set(7, 1);
            } else {
                calendar.set(7, 2);
            }
        } else {
            calendar.set(11, 23);
            calendar.set(12, 59);
            calendar.set(13, 59);
            calendar.set(14, 999);
            if (z) {
                calendar.set(7, 7);
            } else {
                calendar.set(7, 1);
            }
        }
        return calendar.getTimeInMillis();
    }

    public static long d(int i, int i2, long j, boolean z) {
        Calendar calendar = Calendar.getInstance();
        if (z) {
            calendar.setFirstDayOfWeek(1);
        } else {
            calendar.setFirstDayOfWeek(2);
        }
        calendar.setTimeInMillis(j);
        calendar.add(5, i2 * (-7));
        if (i == 1) {
            calendar.set(11, 0);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
            if (z) {
                calendar.set(7, 1);
            } else {
                calendar.set(7, 2);
            }
        } else {
            calendar.set(11, 23);
            calendar.set(12, 59);
            calendar.set(13, 59);
            calendar.set(14, 999);
            if (z) {
                calendar.set(7, 7);
            } else {
                calendar.set(7, 1);
            }
        }
        return calendar.getTimeInMillis();
    }

    public static int a(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        return calendar.get(2) + 1;
    }

    public static int d(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        return calendar.get(5);
    }

    public static String f(long j) {
        return j <= 0 ? "" : mlg.a(j, 2);
    }

    public static <T> HiUserInfo ckg_(T t, HiUserInfo hiUserInfo, Handler handler, int i) {
        if (t == null) {
            return hiUserInfo;
        }
        try {
            if (!koq.e(t, HiUserInfo.class)) {
                return hiUserInfo;
            }
            List list = (List) t;
            if (list.isEmpty()) {
                LogUtil.c("AchieveReportUtil", "userInfos empty");
                return hiUserInfo;
            }
            LogUtil.a("AchieveReportUtil", "fetchUserData onSuccess");
            HiUserInfo hiUserInfo2 = (HiUserInfo) list.get(0);
            try {
                if (LoginInit.getInstance(BaseApplication.getContext()).isLoginedByWear()) {
                    String accountInfo = LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1002);
                    if (!TextUtils.isEmpty(accountInfo)) {
                        hiUserInfo2.setName(accountInfo);
                    } else {
                        LogUtil.a("AchieveReportUtil", "updateUserNameFromLocal userName is null");
                    }
                    hiUserInfo2.setHeadImgUrl(LoginInit.getInstance(BaseApplication.getContext()).getAccountInfo(1003));
                }
                if (handler != null) {
                    handler.sendEmptyMessage(i);
                    return hiUserInfo2;
                }
                LogUtil.a("AchieveReportUtil", "Handler is null");
                return hiUserInfo2;
            } catch (ClassCastException unused) {
                hiUserInfo = hiUserInfo2;
                LogUtil.b("AchieveReportUtil", "updateUserNameFromLocal data ClassCastException");
                return hiUserInfo;
            }
        } catch (ClassCastException unused2) {
        }
    }

    public static int e(long j) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        return calendar.getActualMaximum(5);
    }

    public static long c(long j, int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(j);
        if (i == 1) {
            calendar.set(5, calendar.getMinimum(5));
            calendar.set(11, 0);
            calendar.set(12, 0);
            calendar.set(13, 0);
            calendar.set(14, 0);
            return calendar.getTimeInMillis();
        }
        calendar.set(5, calendar.getActualMaximum(5));
        calendar.set(11, 23);
        calendar.set(12, 59);
        calendar.set(13, 59);
        calendar.set(14, 999);
        return calendar.getTimeInMillis();
    }

    public static ArrayList<String> b(RecentMonthRecordFromDB recentMonthRecordFromDB) {
        if (recentMonthRecordFromDB == null) {
            LogUtil.h("AchieveReportUtil", "getWeeklyCaloriesDateList currentMonthRecord is null.");
            return new ArrayList<>();
        }
        Map<Long, Float> acquireWeekCalorie = recentMonthRecordFromDB.acquireWeekCalorie();
        if (acquireWeekCalorie == null || acquireWeekCalorie.size() <= 0) {
            LogUtil.h("AchieveReportUtil", "setWeeklyCaloriesBarChart weekCaloriesMap is null.");
            return new ArrayList<>();
        }
        Map<Long, Number> b = b();
        b.putAll(acquireWeekCalorie);
        ArrayList<String> arrayList = new ArrayList<>(8);
        Iterator<Map.Entry<Long, Number>> it = b.entrySet().iterator();
        while (it.hasNext()) {
            long longValue = it.next().getKey().longValue();
            long b2 = b(longValue);
            long c2 = c(longValue);
            if (Math.abs(b2 - c2) <= 86400000) {
                arrayList.add(mlg.a(b2, 3));
            } else {
                arrayList.add(mlg.a(b2, 3) + Constants.LINK + d(c2));
            }
        }
        return arrayList;
    }

    public static float d(RecentMonthRecordFromDB recentMonthRecordFromDB) {
        float f = 0.0f;
        if (recentMonthRecordFromDB == null) {
            LogUtil.h("AchieveReportUtil", "getWeeklyCaloriesDateList currentMonthRecord is null.");
            return 0.0f;
        }
        Map<Long, Float> acquireWeekCalorie = recentMonthRecordFromDB.acquireWeekCalorie();
        if (acquireWeekCalorie == null || acquireWeekCalorie.size() <= 0) {
            LogUtil.h("AchieveReportUtil", "setWeeklyCaloriesBarChart weekCaloriesMap is null.");
            return 0.0f;
        }
        Iterator<Map.Entry<Long, Float>> it = acquireWeekCalorie.entrySet().iterator();
        while (it.hasNext()) {
            f = Math.max(f, it.next().getValue().floatValue());
        }
        return f / 1000.0f;
    }

    public static ArrayList<String> e(RecentWeekRecordFromDB recentWeekRecordFromDB) {
        if (recentWeekRecordFromDB == null) {
            LogUtil.h("AchieveReportUtil", "getWeekExerciseTimeDateList currentWeekRecord is null.");
            return new ArrayList<>();
        }
        Map<Long, Long> acquireSevenDayTime = recentWeekRecordFromDB.acquireSevenDayTime();
        if (acquireSevenDayTime == null || acquireSevenDayTime.size() <= 0) {
            LogUtil.h("AchieveReportUtil", "getWeekExerciseTimeDateList weekExerciseTimeMap is null.");
            return new ArrayList<>();
        }
        Map<Long, Number> b = b();
        b.putAll(acquireSevenDayTime);
        ArrayList<String> arrayList = new ArrayList<>(8);
        Iterator<Map.Entry<Long, Number>> it = b.entrySet().iterator();
        while (it.hasNext()) {
            arrayList.add(g(it.next().getKey().longValue()));
        }
        return arrayList;
    }

    public static Map<Long, Number> b() {
        return new TreeMap(new Comparator<Long>() { // from class: mkx.2
            @Override // java.util.Comparator
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public int compare(Long l, Long l2) {
                if (l.longValue() > l2.longValue()) {
                    return 1;
                }
                return l.longValue() < l2.longValue() ? -1 : 0;
            }
        });
    }

    public static float a(RecentWeekRecordFromDB recentWeekRecordFromDB) {
        float f = 0.0f;
        if (recentWeekRecordFromDB == null) {
            LogUtil.h("AchieveReportUtil", "getWeekExerciseMaxTime currentWeekRecord is null.");
            return 0.0f;
        }
        Map<Long, Long> acquireSevenDayTime = recentWeekRecordFromDB.acquireSevenDayTime();
        if (acquireSevenDayTime == null || acquireSevenDayTime.size() <= 0) {
            LogUtil.h("AchieveReportUtil", "getWeekExerciseMaxTime weekExerciseTimeMap is null.");
            return 0.0f;
        }
        Iterator<Map.Entry<Long, Long>> it = acquireSevenDayTime.entrySet().iterator();
        while (it.hasNext()) {
            f = Math.max(f, it.next().getValue().longValue() / 60.0f);
        }
        return f;
    }

    public static long b(long j) {
        long d2 = d(0, j, 1);
        long d3 = d(1, 0, j, Utils.o());
        return d3 <= d2 ? d2 : d3;
    }

    public static long c(long j) {
        long d2 = d(0, j, 2);
        long d3 = d(2, 0, j, Utils.o());
        return d3 > d2 ? d2 : d3;
    }

    public static void ckb_(final ImageView imageView, final String str, final Drawable drawable, final boolean z) {
        if (imageView == null || drawable == null) {
            LogUtil.h("AchieveReportUtil", "loadImage imageView ||defaultRes is null");
            return;
        }
        if (z && LanguageUtil.bc(BaseApplication.getContext())) {
            drawable = nrz.cKm_(BaseApplication.getContext(), drawable);
        }
        if (TextUtils.isEmpty(str)) {
            imageView.setBackground(drawable);
            return;
        }
        if (c(str)) {
            imageView.setTag(str);
        }
        GRSManager.a(BaseApplication.getContext()).e("domainContentcenterDbankcdnNew", new GrsQueryCallback() { // from class: mkx.1
            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackSuccess(String str2) {
                LogUtil.c("AchieveReportUtil", "imageViewUrl ", str2 + str);
                mkx.cjZ_(imageView, str2 + str, str, drawable, z);
            }

            @Override // com.huawei.hwcloudmodel.callback.GrsQueryCallback
            public void onCallBackFail(int i) {
                LogUtil.b("AchieveReportUtil", "onCallBackFail errorCode = ", Integer.valueOf(i));
            }
        });
    }

    private static boolean c(String str) {
        return str.contains("com.huawei.health.h5.annual-report-");
    }

    public static void ckc_(ImageView imageView, String str, Drawable drawable, boolean z) {
        if (imageView == null || drawable == null) {
            LogUtil.h("AchieveReportUtil", "loadImageFromMarket imageView ||defaultRes is null");
            return;
        }
        if (z && LanguageUtil.bc(BaseApplication.getContext())) {
            drawable = nrz.cKm_(BaseApplication.getContext(), drawable);
        }
        if (TextUtils.isEmpty(str)) {
            imageView.setBackground(drawable);
        } else {
            cjZ_(imageView, str, "", drawable, z);
        }
    }

    /* renamed from: mkx$4, reason: invalid class name */
    class AnonymousClass4 extends CustomTarget<Drawable> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ boolean f15047a;
        final /* synthetic */ String b;
        final /* synthetic */ Drawable c;
        final /* synthetic */ WeakReference d;

        @Override // com.bumptech.glide.request.target.Target
        public void onLoadCleared(Drawable drawable) {
        }

        AnonymousClass4(WeakReference weakReference, String str, boolean z, Drawable drawable) {
            this.d = weakReference;
            this.b = str;
            this.f15047a = z;
            this.c = drawable;
        }

        @Override // com.bumptech.glide.request.target.Target
        /* renamed from: ckj_, reason: merged with bridge method [inline-methods] */
        public void onResourceReady(final Drawable drawable, Transition<? super Drawable> transition) {
            final WeakReference weakReference = this.d;
            final String str = this.b;
            final boolean z = this.f15047a;
            HandlerExecutor.e(new Runnable() { // from class: mkv
                @Override // java.lang.Runnable
                public final void run() {
                    mkx.AnonymousClass4.cki_(weakReference, str, z, drawable);
                }
            });
        }

        static /* synthetic */ void cki_(WeakReference weakReference, String str, boolean z, Drawable drawable) {
            ImageView imageView = (ImageView) weakReference.get();
            if (imageView != null) {
                if (mkx.cka_(imageView, str)) {
                    if (z && LanguageUtil.bc(BaseApplication.getContext())) {
                        imageView.setBackground(nrz.cKm_(BaseApplication.getContext(), drawable));
                        return;
                    } else {
                        imageView.setBackground(drawable);
                        return;
                    }
                }
                return;
            }
            LogUtil.h("AchieveReportUtil", "displayImage(onResourceReady): innerImageView is null");
        }

        @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
        public void onLoadFailed(Drawable drawable) {
            LogUtil.h("AchieveReportUtil", "loadRoundRectangle onLoadFailed");
            final Drawable drawable2 = this.c;
            if (drawable2 == null) {
                super.onLoadFailed(drawable);
            } else {
                final WeakReference weakReference = this.d;
                HandlerExecutor.e(new Runnable() { // from class: mky
                    @Override // java.lang.Runnable
                    public final void run() {
                        mkx.AnonymousClass4.ckh_(weakReference, drawable2);
                    }
                });
            }
        }

        static /* synthetic */ void ckh_(WeakReference weakReference, Drawable drawable) {
            ImageView imageView = (ImageView) weakReference.get();
            if (imageView == null) {
                LogUtil.h("AchieveReportUtil", "displayImage(onLoadFailed): innerImageView is null");
            } else {
                imageView.setBackground(drawable);
            }
        }
    }

    public static void cjZ_(ImageView imageView, String str, String str2, Drawable drawable, boolean z) {
        nrf.cIr_(imageView, str, new AnonymousClass4(new WeakReference(imageView), str2, z, drawable));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean cka_(ImageView imageView, String str) {
        if (TextUtils.isEmpty(str) || !c(str)) {
            return true;
        }
        return str.equals(imageView.getTag() instanceof String ? (String) imageView.getTag() : "");
    }

    public static void ckd_(ImageView imageView, String str, String str2, Drawable drawable) {
        if (nsn.ag(BaseApplication.getContext())) {
            ckb_(imageView, str2, drawable, false);
        } else {
            ckb_(imageView, str, drawable, false);
        }
    }

    public static void ckf_(ImageView imageView, String str, String str2, Drawable drawable) {
        if (nsn.ag(BaseApplication.getContext())) {
            ckb_(imageView, str2, drawable, true);
        } else {
            ckb_(imageView, str, drawable, true);
        }
    }

    public static void cke_(ImageView imageView, String str, String str2, Drawable drawable) {
        if (nsn.ag(BaseApplication.getContext())) {
            ckc_(imageView, str2, drawable, true);
        } else {
            ckc_(imageView, str, drawable, true);
        }
    }

    public static void e(final AchieveCallback achieveCallback) {
        ThreadPoolManager.d().execute(new Runnable() { // from class: mkx.3
            @Override // java.lang.Runnable
            public void run() {
                final MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
                if (marketingApi == null) {
                    AchieveCallback.this.onResponse(-1, null);
                    return;
                }
                Task<Map<Integer, ResourceResultInfo>> resourceResultInfo = marketingApi.getResourceResultInfo(1014);
                resourceResultInfo.addOnSuccessListener(new OnSuccessListener<Map<Integer, ResourceResultInfo>>() { // from class: mkx.3.2
                    @Override // com.huawei.hmf.tasks.OnSuccessListener
                    /* renamed from: b, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(Map<Integer, ResourceResultInfo> map) {
                        LogUtil.a("AchieveReportUtil", "requestMarketResource onSuccess");
                        if (map == null || map.isEmpty()) {
                            LogUtil.b("AchieveReportUtil", "marketingResponse is invalid");
                            AchieveCallback.this.onResponse(-1, null);
                            return;
                        }
                        Map<Integer, ResourceResultInfo> filterMarketingRules = marketingApi.filterMarketingRules(map);
                        if (filterMarketingRules == null || filterMarketingRules.isEmpty()) {
                            LogUtil.b("AchieveReportUtil", "filterResultInfoMap is invalid");
                            AchieveCallback.this.onResponse(-1, null);
                        } else if (filterMarketingRules.get(1014) != null) {
                            List unused = mkx.d = mkx.b(filterMarketingRules.get(1014));
                            AchieveCallback.this.onResponse(200, mkx.d);
                        } else {
                            AchieveCallback.this.onResponse(-1, null);
                            LogUtil.h("AchieveReportUtil", "resultMap does not contain search default page resource id");
                        }
                    }
                });
                resourceResultInfo.addOnFailureListener(new OnFailureListener() { // from class: mkx.3.1
                    @Override // com.huawei.hmf.tasks.OnFailureListener
                    public void onFailure(Exception exc) {
                        LogUtil.b("AchieveReportUtil", "requestMarketResource onFailure");
                        AchieveCallback.this.onResponse(-1, null);
                    }
                });
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<SingleMonth> b(ResourceResultInfo resourceResultInfo) {
        MonthlyReportTemplate monthlyReportTemplate;
        ArrayList arrayList = new ArrayList(10);
        if (resourceResultInfo == null) {
            return arrayList;
        }
        List<ResourceBriefInfo> a2 = a(resourceResultInfo.getResources());
        if (koq.b(a2)) {
            return arrayList;
        }
        for (ResourceBriefInfo resourceBriefInfo : a2) {
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis > resourceBriefInfo.getEffectiveTime() && currentTimeMillis < resourceBriefInfo.getExpirationTime() && resourceBriefInfo.getContentType() == 60) {
                String content = resourceBriefInfo.getContent().getContent();
                LogUtil.a("AchieveReportUtil", "text link content: ", content);
                if (!TextUtils.isEmpty(content) && (monthlyReportTemplate = (MonthlyReportTemplate) kps.e(content, MonthlyReportTemplate.class)) != null) {
                    return monthlyReportTemplate.getGridContents();
                }
            }
        }
        return arrayList;
    }

    private static List<ResourceBriefInfo> a(List<ResourceBriefInfo> list) {
        Collections.sort(list, new Comparator<ResourceBriefInfo>() { // from class: mkx.5
            @Override // java.util.Comparator
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public int compare(ResourceBriefInfo resourceBriefInfo, ResourceBriefInfo resourceBriefInfo2) {
                if (resourceBriefInfo.getEffectiveTime() > resourceBriefInfo2.getEffectiveTime()) {
                    return -1;
                }
                return resourceBriefInfo.getEffectiveTime() == resourceBriefInfo2.getEffectiveTime() ? 0 : 1;
            }
        });
        return list;
    }

    public static List<SingleMonth> c() {
        return d;
    }
}
