package defpackage;

import android.content.Context;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.functionsetcard.dailymoment.operation.IGetDailyMomentContentCallback;
import com.huawei.health.functionsetcard.dailymoment.operation.IGetDailyMomentDataCallback;
import com.huawei.health.functionsetcard.dailymoment.operation.IGetDataCallback;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.marketing.datatype.SingleDailyMomentContent;
import com.huawei.health.marketing.datatype.templates.DailyMomentContent;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hwlogsmodel.LogUtil;
import health.compact.a.Services;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class dnv {
    private static final SecureRandom c = nsg.b();
    private static dnv e;
    private ResourceBriefInfo d;
    private List<SingleDailyMomentContent> b = new ArrayList();

    /* renamed from: a, reason: collision with root package name */
    private Context f11731a = BaseApplication.e();

    private dnv() {
    }

    public static dnv a() {
        dnv dnvVar;
        synchronized (dnv.class) {
            if (e == null) {
                e = new dnv();
            }
            dnvVar = e;
        }
        return dnvVar;
    }

    /* renamed from: dnv$2, reason: invalid class name */
    class AnonymousClass2 implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ IGetDataCallback f11733a;

        AnonymousClass2(IGetDataCallback iGetDataCallback) {
            this.f11733a = iGetDataCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            dnv.this.b(new IGetDailyMomentDataCallback() { // from class: dnv.2.5
                @Override // com.huawei.health.functionsetcard.dailymoment.operation.IGetDailyMomentDataCallback
                public void onComplete(final DailyMomentContent dailyMomentContent) {
                    LogUtil.a("DataManager", "requestMarketingPlatformData complete");
                    if (dailyMomentContent == null) {
                        LogUtil.b("DataManager", "request MarketingPlatformData failed");
                        AnonymousClass2.this.f11733a.onComplete(null, null);
                    } else {
                        efb.e(dailyMomentContent.getMomentContents());
                        jdx.b(new Runnable() { // from class: dnv.2.5.2
                            @Override // java.lang.Runnable
                            public void run() {
                                if (doh.c(dnv.this.f11731a, dailyMomentContent) != 0) {
                                    LogUtil.b("DataManager", "saveDailyMomentData failed");
                                }
                            }
                        });
                        dnv.this.b(dailyMomentContent);
                        AnonymousClass2.this.f11733a.onComplete(dnv.this.e(), dnv.this.d);
                    }
                }
            });
        }
    }

    public void e(boolean z, IGetDataCallback iGetDataCallback) {
        if (z) {
            ThreadPoolManager.d().execute(new AnonymousClass2(iGetDataCallback));
        } else {
            DailyMomentContent b = doh.b(this.f11731a);
            if (b == null) {
                LogUtil.b("DataManager", "queryDailyMomentData failed");
                iGetDataCallback.onComplete(null, null);
                return;
            }
            b(b);
        }
        iGetDataCallback.onComplete(e(), this.d);
    }

    public List<SingleDailyMomentContent> e() {
        List<SingleDailyMomentContent> list = this.b;
        if (list == null || list.size() <= 0) {
            LogUtil.b("DataManager", "mResourcePool is empty");
            return new ArrayList();
        }
        if (this.b.size() <= 3) {
            return new ArrayList(this.b);
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 3; i++) {
            arrayList.add(this.b.get(i));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(final IGetDailyMomentDataCallback iGetDailyMomentDataCallback) {
        LogUtil.a("DataManager", "requestMarketingPlatformData");
        c(new IGetDailyMomentContentCallback() { // from class: dnv.4
            @Override // com.huawei.health.functionsetcard.dailymoment.operation.IGetDailyMomentContentCallback
            public void onComplete(ResourceResultInfo resourceResultInfo) {
                if (resourceResultInfo == null) {
                    LogUtil.b("DataManager", "request marketing data failed");
                    iGetDailyMomentDataCallback.onComplete(null);
                    return;
                }
                List<ResourceBriefInfo> resources = resourceResultInfo.getResources();
                if (koq.b(resources)) {
                    LogUtil.b("DataManager", "allResourceList is empty");
                    iGetDailyMomentDataCallback.onComplete(null);
                    return;
                }
                ArrayList arrayList = new ArrayList();
                for (ResourceBriefInfo resourceBriefInfo : resources) {
                    if (dnv.this.b(resourceBriefInfo)) {
                        arrayList.add(resourceBriefInfo);
                    }
                }
                if (!arrayList.isEmpty()) {
                    ResourceBriefInfo b = dnv.this.b(arrayList);
                    if (b != null && b.getContent() != null) {
                        dnv.this.d = b;
                        DailyMomentContent b2 = dnv.this.b(b.getContent().getContent());
                        if (b2 == null) {
                            LogUtil.b("DataManager", "content is invalid");
                            iGetDailyMomentDataCallback.onComplete(null);
                            return;
                        } else {
                            iGetDailyMomentDataCallback.onComplete(b2);
                            return;
                        }
                    }
                    LogUtil.b("DataManager", "prioritizedResource is invalid");
                    iGetDailyMomentDataCallback.onComplete(null);
                    return;
                }
                LogUtil.b("DataManager", "resources are invalid");
                iGetDailyMomentDataCallback.onComplete(null);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(DailyMomentContent dailyMomentContent) {
        List<SingleDailyMomentContent> momentContents = dailyMomentContent.getMomentContents();
        if (momentContents == null || momentContents.isEmpty()) {
            LogUtil.b("DataManager", "content is empty");
            return;
        }
        List<SingleDailyMomentContent> list = this.b;
        if (list == null) {
            return;
        }
        list.clear();
        c(momentContents);
        final int intValue = dailyMomentContent.getDisplayRule().intValue();
        Collections.sort(this.b, new Comparator<SingleDailyMomentContent>() { // from class: dnv.1
            @Override // java.util.Comparator
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public int compare(SingleDailyMomentContent singleDailyMomentContent, SingleDailyMomentContent singleDailyMomentContent2) {
                int b = dnv.this.b(singleDailyMomentContent, singleDailyMomentContent2);
                if (b != 0) {
                    return b;
                }
                int i = intValue;
                if (i == 1) {
                    return dnv.this.c(singleDailyMomentContent, singleDailyMomentContent2, i);
                }
                if (i == 2) {
                    return dnv.this.a(singleDailyMomentContent, singleDailyMomentContent2, i);
                }
                LogUtil.b("DataManager", "invalid displayRule");
                return 0;
            }
        });
        for (SingleDailyMomentContent singleDailyMomentContent : this.b) {
            LogUtil.a("DataManager", singleDailyMomentContent.toString());
            eil.c(singleDailyMomentContent, this.d);
        }
    }

    private boolean d(SingleDailyMomentContent singleDailyMomentContent) {
        long currentTimeMillis = (System.currentTimeMillis() / 1000) / 60;
        String str = (((currentTimeMillis / 60) + 8) % 24) + ":" + (currentTimeMillis % 60);
        return e(str, singleDailyMomentContent.getAppearTime()) >= 0 && e(str, singleDailyMomentContent.getDisappearTime()) < 0;
    }

    private int e(String str, String str2) {
        int[] a2 = a(str);
        int[] a3 = a(str2);
        if (a2 == null || a3 == null || a2.length != 2 || a3.length != 2) {
            LogUtil.b("DataManager", "time length is invalid");
            return 0;
        }
        int i = a2[0];
        int i2 = a3[0];
        if (i < i2) {
            return -1;
        }
        if (i > i2) {
            return 1;
        }
        int i3 = a2[1];
        int i4 = a3[1];
        if (i3 < i4) {
            return -1;
        }
        return i3 > i4 ? 1 : 0;
    }

    private long c(SingleDailyMomentContent singleDailyMomentContent) {
        String latestShowTime = singleDailyMomentContent.getLatestShowTime();
        if (latestShowTime != null && latestShowTime.length() != 0) {
            try {
                return Long.parseLong(latestShowTime);
            } catch (NumberFormatException unused) {
            }
        }
        return 0L;
    }

    private int[] a(String str) {
        if (str == null || str.length() <= 0 || str.length() > 5) {
            LogUtil.b("DataManager", "time is invalid");
            return new int[0];
        }
        String[] split = str.split("\\:");
        if (split == null || split.length != 2) {
            LogUtil.b("DataManager", "split failed");
            return new int[0];
        }
        int[] iArr = new int[2];
        for (int i = 0; i < split.length; i++) {
            try {
                iArr[i] = Integer.parseInt(split[i]);
            } catch (NumberFormatException e2) {
                LogUtil.b("DataManager", "NumberFormatException", e2.getMessage());
            }
        }
        return iArr;
    }

    private void c(List<SingleDailyMomentContent> list) {
        for (SingleDailyMomentContent singleDailyMomentContent : list) {
            if (singleDailyMomentContent.getTimeRule() != 2 || d(singleDailyMomentContent)) {
                this.b.add(singleDailyMomentContent);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DailyMomentContent b(String str) {
        if (str == null || str.length() == 0) {
            LogUtil.b("DataManager", "resourceJson is invalid");
            return null;
        }
        return (DailyMomentContent) nrv.b(str, DailyMomentContent.class);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean b(ResourceBriefInfo resourceBriefInfo) {
        long currentTimeMillis = System.currentTimeMillis();
        return currentTimeMillis > resourceBriefInfo.getEffectiveTime() && currentTimeMillis < resourceBriefInfo.getExpirationTime() && resourceBriefInfo.getContentType() == 10000;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public ResourceBriefInfo b(List<ResourceBriefInfo> list) {
        ArrayList<ResourceBriefInfo> arrayList = new ArrayList();
        int i = Integer.MIN_VALUE;
        for (ResourceBriefInfo resourceBriefInfo : list) {
            if (resourceBriefInfo.getPriority() > i) {
                i = resourceBriefInfo.getPriority();
                arrayList.clear();
                arrayList.add(resourceBriefInfo);
            } else if (resourceBriefInfo.getPriority() == i) {
                arrayList.add(resourceBriefInfo);
            }
        }
        int size = arrayList.size();
        if (size <= 0) {
            return null;
        }
        if (size == 1) {
            return (ResourceBriefInfo) arrayList.get(0);
        }
        ArrayList arrayList2 = new ArrayList();
        long j = Long.MIN_VALUE;
        for (ResourceBriefInfo resourceBriefInfo2 : arrayList) {
            if (resourceBriefInfo2.getModifyTime() > j) {
                j = resourceBriefInfo2.getModifyTime();
                arrayList2.clear();
                arrayList2.add(resourceBriefInfo2);
            } else if (resourceBriefInfo2.getModifyTime() == j) {
                arrayList2.add(resourceBriefInfo2);
            }
        }
        if (arrayList2.size() <= 0) {
            return null;
        }
        return (ResourceBriefInfo) arrayList2.get(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int b(SingleDailyMomentContent singleDailyMomentContent, SingleDailyMomentContent singleDailyMomentContent2) {
        long c2 = c(singleDailyMomentContent);
        long c3 = c(singleDailyMomentContent2);
        if (c2 != 0 && c3 != 0) {
            return c2 - c3 < 0 ? -1 : 1;
        }
        if (c2 != 0) {
            return 1;
        }
        return c3 != 0 ? -1 : 0;
    }

    private void c(final IGetDailyMomentContentCallback iGetDailyMomentContentCallback) {
        ReleaseLogUtil.e("R_DataManager", "requestMarketResource");
        final MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi == null) {
            ReleaseLogUtil.c("R_DataManager", "get marketingApi failed");
        } else {
            marketingApi.getResourceResultInfo(5001).addOnSuccessListener(new OnSuccessListener<Map<Integer, ResourceResultInfo>>() { // from class: dnv.5
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                /* renamed from: c, reason: merged with bridge method [inline-methods] */
                public void onSuccess(Map<Integer, ResourceResultInfo> map) {
                    ReleaseLogUtil.e("R_DataManager", "requestMarketResource onSuccess");
                    if (map == null || map.isEmpty()) {
                        ReleaseLogUtil.c("R_DataManager", "marketingResponse is invalid");
                        iGetDailyMomentContentCallback.onComplete(null);
                        return;
                    }
                    Map<Integer, ResourceResultInfo> filterMarketingRules = marketingApi.filterMarketingRules(map);
                    if (filterMarketingRules == null || filterMarketingRules.isEmpty()) {
                        ReleaseLogUtil.d("R_DataManager", "filterResultInfoMap is invalid");
                        iGetDailyMomentContentCallback.onComplete(null);
                    } else {
                        if (filterMarketingRules.containsKey(5001)) {
                            ResourceResultInfo resourceResultInfo = filterMarketingRules.get(5001);
                            if (resourceResultInfo == null) {
                                ReleaseLogUtil.d("R_DataManager", "resourceResultInfo result is null");
                                iGetDailyMomentContentCallback.onComplete(null);
                                return;
                            } else {
                                iGetDailyMomentContentCallback.onComplete(resourceResultInfo);
                                return;
                            }
                        }
                        ReleaseLogUtil.d("R_DataManager", "resourceResultInfo not have daily marketing resource");
                        iGetDailyMomentContentCallback.onComplete(null);
                    }
                }
            });
        }
    }

    public static void c() {
        synchronized (dnv.class) {
            if (e != null) {
                e = null;
            }
        }
    }

    private int b(SingleDailyMomentContent singleDailyMomentContent, SingleDailyMomentContent singleDailyMomentContent2, int i) {
        int displayPriority;
        int displayPriority2;
        if (i == 1) {
            if (d(singleDailyMomentContent) && d(singleDailyMomentContent2)) {
                if (!singleDailyMomentContent.getAppearTime().equals(singleDailyMomentContent2.getAppearTime())) {
                    int e2 = e(singleDailyMomentContent.getAppearTime(), singleDailyMomentContent2.getAppearTime());
                    if (e2 != 0) {
                        return e2;
                    }
                    displayPriority = singleDailyMomentContent2.getDisplayPriority();
                    displayPriority2 = singleDailyMomentContent.getDisplayPriority();
                } else {
                    displayPriority = singleDailyMomentContent2.getDisplayPriority();
                    displayPriority2 = singleDailyMomentContent.getDisplayPriority();
                }
            } else {
                if (d(singleDailyMomentContent)) {
                    return -1;
                }
                if (d(singleDailyMomentContent2)) {
                    return 1;
                }
                displayPriority = singleDailyMomentContent2.getDisplayPriority();
                displayPriority2 = singleDailyMomentContent.getDisplayPriority();
            }
            return displayPriority - displayPriority2;
        }
        if (d(singleDailyMomentContent) && d(singleDailyMomentContent2)) {
            if (!singleDailyMomentContent.getAppearTime().equals(singleDailyMomentContent2.getAppearTime())) {
                int e3 = e(singleDailyMomentContent.getAppearTime(), singleDailyMomentContent2.getAppearTime());
                return e3 == 0 ? c.nextInt() : e3;
            }
            return c.nextInt();
        }
        if (d(singleDailyMomentContent)) {
            return -1;
        }
        if (d(singleDailyMomentContent2)) {
            return 1;
        }
        return c.nextInt();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int c(SingleDailyMomentContent singleDailyMomentContent, SingleDailyMomentContent singleDailyMomentContent2, int i) {
        if (singleDailyMomentContent.getTimeRule() == 1 && singleDailyMomentContent2.getTimeRule() == 1) {
            return singleDailyMomentContent2.getDisplayPriority() - singleDailyMomentContent.getDisplayPriority();
        }
        if (singleDailyMomentContent.getTimeRule() == 1) {
            return d(singleDailyMomentContent2) ? 1 : -1;
        }
        if (singleDailyMomentContent2.getTimeRule() == 1) {
            return d(singleDailyMomentContent) ? -1 : 1;
        }
        return b(singleDailyMomentContent, singleDailyMomentContent2, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int a(SingleDailyMomentContent singleDailyMomentContent, SingleDailyMomentContent singleDailyMomentContent2, int i) {
        if (singleDailyMomentContent.getTimeRule() == 1 && singleDailyMomentContent2.getTimeRule() == 1) {
            return c.nextInt();
        }
        if (singleDailyMomentContent.getTimeRule() == 1) {
            if (d(singleDailyMomentContent2)) {
                return 1;
            }
            return c.nextInt();
        }
        if (singleDailyMomentContent2.getTimeRule() == 1) {
            if (d(singleDailyMomentContent)) {
                return -1;
            }
            return c.nextInt();
        }
        return b(singleDailyMomentContent, singleDailyMomentContent2, i);
    }
}
