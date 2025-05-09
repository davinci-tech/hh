package com.huawei.health.knit.section.listener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import androidx.core.util.Consumer;
import androidx.fragment.app.FragmentManager;
import com.google.gson.reflect.TypeToken;
import com.huawei.haf.common.exception.ExceptionUtils;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.configuredpage.api.ConfiguredPageApi;
import com.huawei.health.knit.bff.impl.IResourceRequestCallback;
import com.huawei.health.knit.data.IKnitLifeCycle;
import com.huawei.health.knit.data.KnitDataProvider;
import com.huawei.health.knit.data.KnitDataProviderGroup;
import com.huawei.health.knit.data.MajorProvider;
import com.huawei.health.knit.data.MinorProvider;
import com.huawei.health.knit.section.listener.BasePageResTrigger;
import com.huawei.health.knit.section.model.MarketingIdInfo;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.hmf.tasks.OnFailureListener;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hmf.tasks.Task;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.utils.versioncontrol.VersionControlUtil;
import defpackage.bzs;
import defpackage.eac;
import defpackage.ead;
import defpackage.eag;
import defpackage.eai;
import defpackage.efb;
import defpackage.koq;
import defpackage.moj;
import health.compact.a.ReflectionUtils;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes3.dex */
public class BasePageResTrigger implements IPageResTrigger {
    public static final Parcelable.Creator<BasePageResTrigger> CREATOR = new Parcelable.Creator<BasePageResTrigger>() { // from class: com.huawei.health.knit.section.listener.BasePageResTrigger.3
        @Override // android.os.Parcelable.Creator
        /* renamed from: agx_, reason: merged with bridge method [inline-methods] */
        public BasePageResTrigger createFromParcel(Parcel parcel) {
            return new BasePageResTrigger(parcel);
        }

        @Override // android.os.Parcelable.Creator
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public BasePageResTrigger[] newArray(int i) {
            return new BasePageResTrigger[i];
        }
    };
    private static final int DELAY = 5000;
    public static final String KET_EXTRA_IS_SET_BI_ON_VISIBLE = "key_extra_is_set_bi_on_visible";
    public static final String KEY_DELAY_TIME_MILLIS = "key_delay_time_millis";
    public static final String KEY_EXTRA_MARKETING_ONE_PAGE_TYPE = "key_extra_marketing_one_page_type";
    public static final String KEY_EXTRA_RES_POS_ID_LIST = "key_extra_res_pos_id_list";
    private static final String KEY_SECTION_LIST = "section_list";
    public static final int OFFLINE_SECTIONS_MAX_PRIORITY = 9999;
    private static final String R_TAG = "R_BasePageResTrigger";
    private static final String TAG = "BasePageResTrigger";
    private List<SectionBean> mCacheBeansList;
    private Consumer mConsumer;
    protected WeakReference<Context> mContextRef;
    private long mDelayTimeMillis;
    protected Bundle mExtra;
    protected OnFailureListener mFailureListener;
    private boolean mFirstTimeVisible;
    protected volatile boolean mIsLoadFloatingBox;
    private boolean mIsNeedToLoadEmptyLayout;
    private MajorProvider mMajorProvider;
    protected final MarketingIdInfo mMarketingIdInfo;
    protected List<View> mMarketingViewList;
    private final List<MinorProvider> mMinorProviderList;
    private boolean mNeedRefresh;
    protected final int mResPosId;
    private Timer mTimer;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.huawei.health.knit.section.listener.IPageResTrigger
    public int getPageCategory() {
        return 0;
    }

    @Override // com.huawei.health.knit.section.listener.IPageResTrigger
    public void onPageCreated(Activity activity, View view) {
    }

    @Override // com.huawei.health.knit.section.listener.IPageResTrigger
    public void onPageVisibilityChanged(Activity activity, boolean z, View view) {
    }

    @Override // com.huawei.health.knit.section.listener.IPageResTrigger
    public void setOnFailureListener(OnFailureListener onFailureListener) {
    }

    public BasePageResTrigger(Context context, int i, MarketingIdInfo marketingIdInfo) {
        this.mExtra = new Bundle();
        this.mContextRef = new WeakReference<>(null);
        this.mIsLoadFloatingBox = false;
        this.mCacheBeansList = null;
        this.mMinorProviderList = new ArrayList();
        this.mFirstTimeVisible = true;
        this.mNeedRefresh = false;
        this.mContextRef = new WeakReference<>(context);
        this.mResPosId = i;
        this.mMarketingIdInfo = marketingIdInfo;
        this.mTimer = new Timer();
    }

    private BasePageResTrigger(Parcel parcel) {
        this.mExtra = new Bundle();
        this.mContextRef = new WeakReference<>(null);
        this.mIsLoadFloatingBox = false;
        this.mCacheBeansList = null;
        this.mMinorProviderList = new ArrayList();
        this.mFirstTimeVisible = true;
        this.mNeedRefresh = false;
        this.mResPosId = parcel.readInt();
        this.mMarketingIdInfo = (MarketingIdInfo) parcel.readParcelable(MarketingIdInfo.class.getClassLoader());
    }

    public void setLoadFloatingBox(boolean z) {
        this.mIsLoadFloatingBox = z;
    }

    @Override // com.huawei.health.knit.section.listener.IPageResTrigger
    public List<SectionBean> onPageLoadOfflineSections(String str, boolean z) {
        JSONObject jSONObject;
        JSONArray optJSONArray;
        ArrayList arrayList = new ArrayList();
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e) {
            LogUtil.b(TAG, "onPageLoadOfflineSections jsonObject exception, cause ", e.getCause());
            jSONObject = null;
        }
        if (jSONObject == null || (optJSONArray = jSONObject.optJSONArray(KEY_SECTION_LIST)) == null) {
            return arrayList;
        }
        boolean z2 = z && !Utils.o();
        int length = optJSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject == null) {
                return arrayList;
            }
            if (isActive(optJSONObject, z2)) {
                String optString = optJSONObject.optString("dataUrl");
                if (!TextUtils.isEmpty(optString)) {
                    String[] split = optString.split(";");
                    if (split.length != 0) {
                        sectionBeanGeneration(arrayList, optJSONObject, split);
                    }
                }
            }
        }
        relateMajorAndMinorProvider(arrayList);
        Iterator<SectionBean> it = arrayList.iterator();
        while (it.hasNext()) {
            it.next().w();
        }
        return arrayList;
    }

    private boolean isActive(JSONObject jSONObject, boolean z) {
        boolean optBoolean = jSONObject.optBoolean("isOnlyOffline");
        String optString = jSONObject.optString("versionControl");
        if (z && optBoolean) {
            LogUtil.a(TAG, " isActive() isChinaOnLine:", Boolean.valueOf(z), ", config isOnlyOffline:", Boolean.valueOf(optBoolean), ", config versionControl:", optString);
            return false;
        }
        if (TextUtils.isEmpty(optString)) {
            return true;
        }
        boolean isVersioned = VersionControlUtil.isVersioned(optString);
        LogUtil.a(TAG, "jsonObjects: ", jSONObject.toString(), ", isVersioned: ", Boolean.valueOf(isVersioned));
        return isVersioned;
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x006a  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0078  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0088  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void sectionBeanGeneration(java.util.List<com.huawei.health.knit.section.model.SectionBean> r12, org.json.JSONObject r13, java.lang.String[] r14) {
        /*
            r11 = this;
            java.lang.String r0 = "priority"
            int r3 = r13.optInt(r0)
            java.lang.String r0 = "template"
            int r0 = r13.optInt(r0)
            java.lang.String r1 = "isShare"
            java.lang.String r10 = r13.optString(r1)
            int r1 = r14.length
            r2 = 1
            java.lang.String r4 = "groupId"
            if (r1 <= r2) goto L4e
            com.huawei.health.knit.data.KnitDataProvider r8 = r11.getDpGroup(r14)
            int r13 = r13.optInt(r4)
            r8.setGroupId(r13)
            android.os.Bundle r13 = r11.mExtra
            r8.setExtra(r13)
            if (r3 == 0) goto L3b
            eec r13 = new eec
            int r4 = r11.getPageType()
            int r6 = r11.mResPosId
            r1 = r13
            r2 = r0
            r5 = r8
            r7 = r10
            r1.<init>(r2, r3, r4, r5, r6, r7)
            goto L4a
        L3b:
            eec r13 = new eec
            r6 = 9999(0x270f, float:1.4012E-41)
            int r7 = r11.getPageType()
            int r9 = r11.mResPosId
            r4 = r13
            r5 = r0
            r4.<init>(r5, r6, r7, r8, r9, r10)
        L4a:
            r12.add(r13)
            goto La9
        L4e:
            r1 = 0
            r14 = r14[r1]
            java.lang.String r14 = defpackage.eai.a(r14)
            boolean r1 = android.text.TextUtils.isEmpty(r14)
            if (r1 != 0) goto L66
            java.lang.Object r14 = health.compact.a.ReflectionUtils.e(r14)
            boolean r1 = r14 instanceof com.huawei.health.knit.data.KnitDataProvider
            if (r1 == 0) goto L66
            com.huawei.health.knit.data.KnitDataProvider r14 = (com.huawei.health.knit.data.KnitDataProvider) r14
            goto L67
        L66:
            r14 = 0
        L67:
            r8 = r14
            if (r8 == 0) goto L76
            int r13 = r13.optInt(r4)
            r8.setGroupId(r13)
            android.os.Bundle r13 = r11.mExtra
            r8.setExtra(r13)
        L76:
            if (r3 == 0) goto L88
            com.huawei.health.knit.section.model.SectionBean r13 = new com.huawei.health.knit.section.model.SectionBean
            int r4 = r11.getPageType()
            int r6 = r11.mResPosId
            r1 = r13
            r2 = r0
            r5 = r8
            r7 = r10
            r1.<init>(r2, r3, r4, r5, r6, r7)
            goto L97
        L88:
            com.huawei.health.knit.section.model.SectionBean r13 = new com.huawei.health.knit.section.model.SectionBean
            r6 = 9999(0x270f, float:1.4012E-41)
            int r7 = r11.getPageType()
            int r9 = r11.mResPosId
            r4 = r13
            r5 = r0
            r4.<init>(r5, r6, r7, r8, r9, r10)
        L97:
            java.lang.String r14 = "add sectionBean. templateId = "
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.Object[] r14 = new java.lang.Object[]{r14, r0}
            java.lang.String r0 = "BasePageResTrigger"
            com.huawei.hwlogsmodel.LogUtil.a(r0, r14)
            r12.add(r13)
        La9:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.health.knit.section.listener.BasePageResTrigger.sectionBeanGeneration(java.util.List, org.json.JSONObject, java.lang.String[]):void");
    }

    private KnitDataProvider getDpGroup(String[] strArr) {
        ArrayList arrayList = new ArrayList();
        KnitDataProviderGroup knitDataProviderGroup = null;
        boolean z = false;
        for (String str : strArr) {
            String a2 = eai.a(str);
            if (!TextUtils.isEmpty(a2)) {
                Object e = ReflectionUtils.e(a2);
                if (!z && (e instanceof KnitDataProviderGroup)) {
                    z = true;
                    knitDataProviderGroup = (KnitDataProviderGroup) e;
                } else if (e instanceof KnitDataProvider) {
                    arrayList.add((KnitDataProvider) e);
                }
            }
        }
        if (knitDataProviderGroup == null) {
            return new KnitDataProviderGroup().e(arrayList);
        }
        return knitDataProviderGroup.e(arrayList);
    }

    private void relateMajorAndMinorProvider(List<SectionBean> list) {
        HashMap hashMap = new HashMap();
        for (SectionBean sectionBean : list) {
            KnitDataProvider c2 = sectionBean.c();
            if (c2 != null) {
                int groupId = c2.getGroupId();
                List list2 = (List) hashMap.get(Integer.valueOf(groupId));
                if (list2 == null) {
                    list2 = new ArrayList();
                    hashMap.put(Integer.valueOf(groupId), list2);
                }
                list2.add(sectionBean);
            }
        }
        this.mMinorProviderList.clear();
        Iterator it = hashMap.values().iterator();
        while (it.hasNext()) {
            Iterator it2 = ((List) it.next()).iterator();
            while (it2.hasNext()) {
                KnitDataProvider c3 = ((SectionBean) it2.next()).c();
                if (c3 instanceof MajorProvider) {
                    this.mMajorProvider = (MajorProvider) c3;
                } else if (c3 instanceof MinorProvider) {
                    this.mMinorProviderList.add((MinorProvider) c3);
                } else {
                    LogUtil.i(TAG, "not major or minor provider");
                }
            }
            if (this.mMajorProvider != null) {
                Iterator<MinorProvider> it3 = this.mMinorProviderList.iterator();
                while (it3.hasNext()) {
                    this.mMajorProvider.registerMinorProvider(it3.next());
                }
            }
        }
    }

    @Override // com.huawei.health.knit.section.listener.IPageResTrigger
    public MajorProvider getMajorProvider() {
        return this.mMajorProvider;
    }

    @Override // com.huawei.health.knit.section.listener.IPageResTrigger
    public List<MinorProvider> getMinorProviderList() {
        return this.mMinorProviderList;
    }

    protected void loadMarketingTwo(Consumer<List<SectionBean>> consumer, FragmentManager fragmentManager) {
        ArrayList<Integer> arrayList;
        ArrayList arrayList2 = new ArrayList();
        int i = this.mResPosId;
        if (i != -1) {
            arrayList2.add(Integer.valueOf(i));
        }
        Bundle bundle = this.mExtra;
        if (bundle != null) {
            arrayList = bundle.getIntegerArrayList(KEY_EXTRA_RES_POS_ID_LIST);
            this.mDelayTimeMillis = this.mExtra.getLong(KEY_DELAY_TIME_MILLIS);
        } else {
            arrayList = null;
        }
        Bundle bundle2 = this.mExtra;
        boolean z = bundle2 != null && bundle2.getBoolean(KET_EXTRA_IS_SET_BI_ON_VISIBLE, false);
        if (!koq.b(arrayList)) {
            arrayList2.addAll(arrayList);
        }
        boolean e = eac.e(getPageCategory());
        if (efb.h() && eag.d(getPageType())) {
            requestDataByBff(consumer, arrayList2, z, e);
        } else {
            loadMarketing(consumer, fragmentManager, arrayList2, z, e);
        }
    }

    private void loadMarketing(Consumer<List<SectionBean>> consumer, FragmentManager fragmentManager, List<Integer> list, boolean z, boolean z2) {
        MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi == null) {
            consumer.accept(null);
            return;
        }
        String d = eac.d(list);
        String b2 = eac.e().b(d);
        Map<Integer, ResourceResultInfo> map = (Map) moj.b(b2, new TypeToken<Map<Integer, ResourceResultInfo>>() { // from class: com.huawei.health.knit.section.listener.BasePageResTrigger.1
        }.getType());
        boolean z3 = (!z2 || map == null || map.isEmpty() || fragmentManager == null) ? false : true;
        if (z3) {
            LogUtil.a(TAG, "use cache data:", Integer.valueOf(this.mResPosId), " resPosIdList: ", list);
            handleMarketingSection(map, marketingApi, list, consumer, fragmentManager, z);
        }
        b bVar = new b(this, fragmentManager, consumer, list, marketingApi, z);
        if (z3) {
            c cVar = new c(this, false);
            cVar.b(bVar, marketingApi, list, d, b2);
            this.mTimer.schedule(cVar, 5000L);
        } else {
            if (this.mDelayTimeMillis > 0) {
                c cVar2 = new c(this, false);
                cVar2.b(bVar, marketingApi, list, d, b2);
                this.mTimer.schedule(cVar2, this.mDelayTimeMillis);
                return;
            }
            requestRes(bVar, marketingApi, list, d, b2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestRes(b bVar, MarketingApi marketingApi, List<Integer> list, String str, String str2) {
        ReleaseLogUtil.b(R_TAG, "begin to request market");
        Task<Map<Integer, ResourceResultInfo>> resourceResultInfo = marketingApi.getResourceResultInfo(list);
        bVar.b(str, str2);
        resourceResultInfo.addOnSuccessListener(bVar);
        OnFailureListener onFailureListener = this.mFailureListener;
        if (onFailureListener != null) {
            resourceResultInfo.addOnFailureListener(onFailureListener);
        }
    }

    private void requestDataByBff(Consumer<List<SectionBean>> consumer, List<Integer> list, boolean z, boolean z2) {
        String d = eac.d(list);
        String b2 = eac.e().b(d);
        if (!z2 || TextUtils.isEmpty(b2)) {
            if (this.mDelayTimeMillis > 0) {
                c cVar = new c(this, true);
                cVar.b(consumer, d, list, z, z2);
                this.mTimer.schedule(cVar, this.mDelayTimeMillis);
                return;
            }
            requestResource(consumer, d, list, z, z2);
            return;
        }
        ReleaseLogUtil.b(TAG, "use cache data:", Integer.valueOf(this.mResPosId), " resPosIdList: ", list);
        try {
            m422xeadfba93(consumer, list, z, new JSONObject(b2));
        } catch (JSONException e) {
            ReleaseLogUtil.a(TAG, "use cache handleResponse failed with exception:", Integer.valueOf(this.mResPosId), " resPosIdList: ", list, " exception: ", ExceptionUtils.d(e));
        }
        c cVar2 = new c(this, true);
        cVar2.b(consumer, d, list, z, true);
        this.mTimer.schedule(cVar2, 5000L);
    }

    static class c extends TimerTask {

        /* renamed from: a, reason: collision with root package name */
        private boolean f2607a;
        private boolean b;
        private String c;
        private Consumer<List<SectionBean>> d;
        private String e;
        private MarketingApi f;
        private List<Integer> g;
        private final WeakReference<BasePageResTrigger> h;
        private boolean i;
        private b j;

        c(BasePageResTrigger basePageResTrigger, boolean z) {
            this.h = new WeakReference<>(basePageResTrigger);
            this.f2607a = z;
        }

        public void b(Consumer<List<SectionBean>> consumer, String str, List<Integer> list, boolean z, boolean z2) {
            this.d = consumer;
            this.c = str;
            this.g = list;
            this.b = z;
            this.i = z2;
        }

        public void b(b bVar, MarketingApi marketingApi, List<Integer> list, String str, String str2) {
            this.j = bVar;
            this.f = marketingApi;
            this.g = list;
            this.c = str;
            this.e = str2;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            BasePageResTrigger basePageResTrigger = this.h.get();
            if (basePageResTrigger == null) {
                ReleaseLogUtil.b(BasePageResTrigger.R_TAG, "trigger is null");
            } else if (this.f2607a) {
                basePageResTrigger.requestResource(this.d, this.c, this.g, this.b, this.i);
            } else {
                basePageResTrigger.requestRes(this.j, this.f, this.g, this.c, this.e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestResource(final Consumer<List<SectionBean>> consumer, final String str, final List<Integer> list, final boolean z, final boolean z2) {
        ReleaseLogUtil.b(TAG, "begin to request bff");
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        final String b2 = eac.e().b(str);
        eag.e(this.mContextRef, list, new IResourceRequestCallback() { // from class: com.huawei.health.knit.section.listener.BasePageResTrigger.5
            @Override // com.huawei.health.knit.bff.impl.IResourceRequestCallback
            public void onSuccess(JSONObject jSONObject) {
                LogUtil.a(BasePageResTrigger.TAG, "bff total time: ", Long.valueOf(SystemClock.elapsedRealtime() - elapsedRealtime));
                if (jSONObject == null) {
                    LogUtil.a(BasePageResTrigger.TAG, "response is null");
                    consumer.accept(null);
                    return;
                }
                LogUtil.a(BasePageResTrigger.TAG, "response: ", jSONObject.toString());
                if (z2) {
                    eac.e().e(str, jSONObject.toString());
                    if (jSONObject.toString().equals(b2)) {
                        ReleaseLogUtil.b(BasePageResTrigger.TAG, "bff response is not changed", list);
                        return;
                    } else if (!TextUtils.isEmpty(b2)) {
                        ReleaseLogUtil.b(BasePageResTrigger.TAG, "bff response has changed");
                        return;
                    }
                }
                BasePageResTrigger.this.m422xeadfba93(consumer, list, z, jSONObject);
            }

            @Override // com.huawei.health.knit.bff.impl.IResourceRequestCallback
            public void onFailure(int i, String str2) {
                LogUtil.a(BasePageResTrigger.TAG, "errCode: ", Integer.valueOf(i), ", errInfo: ", str2);
                BasePageResTrigger basePageResTrigger = BasePageResTrigger.this;
                basePageResTrigger.m422xeadfba93(consumer, list, z, basePageResTrigger.getEmptyResponse());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: handleResponse, reason: merged with bridge method [inline-methods] */
    public void m422xeadfba93(final Consumer<List<SectionBean>> consumer, final List<Integer> list, final boolean z, final JSONObject jSONObject) {
        if (HandlerExecutor.c()) {
            ThreadPoolManager.d().execute(new Runnable() { // from class: edl
                @Override // java.lang.Runnable
                public final void run() {
                    BasePageResTrigger.this.m422xeadfba93(consumer, list, z, jSONObject);
                }
            });
            return;
        }
        final ArrayList arrayList = new ArrayList(0);
        ead.a(jSONObject, list, z, this, consumer, arrayList);
        HandlerExecutor.e(new Runnable() { // from class: edn
            @Override // java.lang.Runnable
            public final void run() {
                BasePageResTrigger.this.m423xf0e385f2(arrayList);
            }
        });
    }

    /* renamed from: lambda$handleResponse$1$com-huawei-health-knit-section-listener-BasePageResTrigger, reason: not valid java name */
    public /* synthetic */ void m423xf0e385f2(List list) {
        this.mMarketingViewList = new ArrayList(list);
    }

    static class b implements OnSuccessListener<Map<Integer, ResourceResultInfo>> {

        /* renamed from: a, reason: collision with root package name */
        private String f2606a;
        private String b;
        private final WeakReference<FragmentManager> c;
        private final Consumer<List<SectionBean>> d;
        private boolean e;
        private MarketingApi f;
        private final List<Integer> g;
        private final WeakReference<BasePageResTrigger> h;

        public b(BasePageResTrigger basePageResTrigger, FragmentManager fragmentManager, Consumer<List<SectionBean>> consumer, List<Integer> list, MarketingApi marketingApi, boolean z) {
            this.h = new WeakReference<>(basePageResTrigger);
            this.c = new WeakReference<>(fragmentManager);
            this.d = consumer;
            this.g = list;
            this.f = marketingApi;
            this.e = z;
        }

        public void b(String str, String str2) {
            this.b = str;
            this.f2606a = str2;
        }

        @Override // com.huawei.hmf.tasks.OnSuccessListener
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onSuccess(final Map<Integer, ResourceResultInfo> map) {
            final BasePageResTrigger basePageResTrigger = this.h.get();
            if (basePageResTrigger == null) {
                return;
            }
            if (map == null) {
                LogUtil.b(BasePageResTrigger.TAG, "no section map for ", this.g);
                this.d.accept(null);
                return;
            }
            if (eac.e(basePageResTrigger.getPageCategory())) {
                String e = moj.e(map);
                eac.e().e(this.b, e);
                if (!e.equals(this.f2606a)) {
                    if (basePageResTrigger.mFirstTimeVisible && !TextUtils.isEmpty(this.f2606a)) {
                        basePageResTrigger.mNeedRefresh = true;
                        basePageResTrigger.mConsumer = new Consumer() { // from class: com.huawei.health.knit.section.listener.BasePageResTrigger.b.2
                            @Override // androidx.core.util.Consumer
                            public void accept(Object obj) {
                                LogUtil.a(BasePageResTrigger.R_TAG, "begin consumer");
                                BasePageResTrigger basePageResTrigger2 = basePageResTrigger;
                                if (basePageResTrigger2 != null) {
                                    basePageResTrigger2.handleMarketingSection(map, b.this.f, b.this.g, b.this.d, (FragmentManager) b.this.c.get(), b.this.e);
                                } else {
                                    ReleaseLogUtil.a(BasePageResTrigger.R_TAG, "consumer accept");
                                }
                            }
                        };
                        ReleaseLogUtil.b(BasePageResTrigger.R_TAG, "first request, show next visible");
                        return;
                    }
                } else {
                    ReleaseLogUtil.a(BasePageResTrigger.R_TAG, "resource not changed: ", this.g);
                    return;
                }
            }
            basePageResTrigger.handleMarketingSection(map, this.f, this.g, this.d, this.c.get(), this.e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleMarketingSection(Map<Integer, ResourceResultInfo> map, MarketingApi marketingApi, List<Integer> list, Consumer<List<SectionBean>> consumer, FragmentManager fragmentManager, boolean z) {
        Map<Integer, ResourceResultInfo> filterMarketingRules = marketingApi.filterMarketingRules(map);
        if (filterMarketingRules == null) {
            LogUtil.b(TAG, "no section map for ", list, ", after ruling");
            consumer.accept(null);
            return;
        }
        Context context = this.mContextRef.get();
        if (context == null) {
            LogUtil.b(TAG, "loadMarketingTwo failed, cause context is null!");
            return;
        }
        this.mMarketingViewList = marketingApi.getMarketingViewList(context, filterMarketingRules, fragmentManager);
        Object[] objArr = new Object[1];
        StringBuilder sb = new StringBuilder("views: ");
        List<View> list2 = this.mMarketingViewList;
        sb.append(list2 != null ? list2.size() : -1);
        objArr[0] = sb.toString();
        LogUtil.a(R_TAG, objArr);
        List<SectionBean> arrayList = new ArrayList<>();
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            int intValue = it.next().intValue();
            ResourceResultInfo resourceResultInfo = filterMarketingRules.get(Integer.valueOf(intValue));
            if (resourceResultInfo == null) {
                LogUtil.b(TAG, "no resourceResultInfo for " + intValue);
            } else {
                LogUtil.a(TAG, "resourceResultInfo: ", resourceResultInfo.toString());
                List<ResourceBriefInfo> resources = resourceResultInfo.getResources();
                if (koq.b(resources)) {
                    LogUtil.b(TAG, "no briefInfoList for ", Integer.valueOf(intValue));
                } else {
                    List<SectionBean> a2 = ead.a(resources, this.mMarketingViewList, z, getPageType(), this.mResPosId);
                    LogUtil.a(TAG, "request successfully sectionBeans size = ", Integer.valueOf(a2.size()));
                    arrayList.addAll(a2);
                }
            }
        }
        consumer.accept(arrayList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public JSONObject getEmptyResponse() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.put("configurationData", (Object) null);
            jSONObject2.put("data", jSONObject);
        } catch (JSONException unused) {
            LogUtil.a(TAG, "json exception");
        }
        return jSONObject2;
    }

    private void loadMarketingOne(Consumer<List<SectionBean>> consumer) {
        ConfiguredPageApi configuredPageApi = (ConfiguredPageApi) Services.a("Main", ConfiguredPageApi.class);
        if (configuredPageApi == null) {
            return;
        }
        Context context = this.mContextRef.get();
        if (context == null) {
            LogUtil.b(TAG, "loadMarketingOne failed, cause context is null!");
            return;
        }
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        int i = this.mExtra.getInt(KEY_EXTRA_MARKETING_ONE_PAGE_TYPE, 0);
        configuredPageApi.initOperationConfiguredPage(i, linearLayout, null);
        ResourceBriefInfo.Builder builder = new ResourceBriefInfo.Builder();
        builder.setContentType(0).setPriority(0);
        SectionBean sectionBean = new SectionBean(new ResourceBriefInfo(builder), (View) linearLayout, i, 0, false);
        ArrayList arrayList = new ArrayList();
        arrayList.add(sectionBean);
        consumer.accept(arrayList);
    }

    public void setCacheBeansList(List<SectionBean> list) {
        if (koq.b(list)) {
            return;
        }
        List<SectionBean> list2 = this.mCacheBeansList;
        if (list2 == null) {
            this.mCacheBeansList = new ArrayList();
        } else {
            list2.clear();
        }
        this.mCacheBeansList.addAll(list);
    }

    @Override // com.huawei.health.knit.section.listener.IPageResTrigger
    public boolean isUsingCacheBeanList() {
        return koq.c(this.mCacheBeansList);
    }

    @Override // com.huawei.health.knit.section.listener.IPageResTrigger
    public void onPageLoadOnlineSections(Consumer<List<SectionBean>> consumer, FragmentManager fragmentManager) {
        if (this.mCacheBeansList != null) {
            consumer.accept(new ArrayList(this.mCacheBeansList));
        } else if (Utils.o() && !bzs.e().switchToMarketingTwo()) {
            loadMarketingOne(consumer);
        } else {
            loadMarketingTwo(consumer, fragmentManager);
        }
    }

    @Override // com.huawei.health.knit.section.listener.IPageResTrigger
    public int getResPosId() {
        return this.mResPosId;
    }

    @Override // com.huawei.health.knit.section.listener.IPageResTrigger
    public IPageResTrigger setExtra(Bundle bundle) {
        if (this.mExtra != null && bundle != null && !bundle.isEmpty()) {
            this.mExtra.putAll(bundle);
        }
        return this;
    }

    @Override // com.huawei.health.knit.section.listener.IPageResTrigger
    public Bundle getExtra() {
        return this.mExtra;
    }

    @Override // com.huawei.health.knit.section.listener.IPageResTrigger
    public boolean isNeedToLoadEmptyLayout() {
        return this.mIsNeedToLoadEmptyLayout;
    }

    @Override // com.huawei.health.knit.section.listener.IPageResTrigger
    public IPageResTrigger setIsNeedToLoadEmptyLayout(boolean z) {
        this.mIsNeedToLoadEmptyLayout = z;
        return this;
    }

    public int getPageType() {
        int i = this.mResPosId;
        if (i == 4015) {
            return 5;
        }
        if (i == 4016) {
            return 6;
        }
        if (i == 4019) {
            return 390;
        }
        if (i == 4020) {
            return 23;
        }
        if (i == 4040) {
            return 410;
        }
        if (i == 4060) {
            return 412;
        }
        if (i == 4061) {
            return 411;
        }
        LogUtil.h(TAG, "getPageType failed unknown resPosId = ", Integer.valueOf(i));
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.mResPosId);
        parcel.writeParcelable(this.mMarketingIdInfo, i);
    }

    @Override // com.huawei.health.knit.section.listener.IPageResTrigger
    public void onDestroy(Activity activity) {
        clearMarketingViewList();
        clearCacheBeansList();
    }

    @Override // com.huawei.health.knit.data.IKnitLifeCycle
    public void onCreate() {
        List<View> list = this.mMarketingViewList;
        if (list == null) {
            LogUtil.a(TAG, "onCreate with activity");
            return;
        }
        for (KeyEvent.Callback callback : list) {
            if (callback instanceof IKnitLifeCycle) {
                ((IKnitLifeCycle) callback).onCreate();
            }
        }
    }

    @Override // com.huawei.health.knit.data.IKnitLifeCycle
    public void onResume() {
        List<View> list = this.mMarketingViewList;
        if (list == null) {
            LogUtil.a(TAG, "onResume with activity");
            return;
        }
        for (KeyEvent.Callback callback : list) {
            if (callback instanceof IKnitLifeCycle) {
                ((IKnitLifeCycle) callback).onResume();
            }
        }
    }

    @Override // com.huawei.health.knit.data.IKnitLifeCycle
    public void onPause() {
        List<View> list = this.mMarketingViewList;
        if (list == null) {
            LogUtil.a(TAG, "onPause with activity");
            return;
        }
        for (KeyEvent.Callback callback : list) {
            if (callback instanceof IKnitLifeCycle) {
                ((IKnitLifeCycle) callback).onPause();
            }
        }
    }

    @Override // com.huawei.health.knit.data.IKnitLifeCycle
    public void onStop() {
        List<View> list = this.mMarketingViewList;
        if (list == null) {
            LogUtil.a(TAG, "onStop with activity");
            return;
        }
        for (KeyEvent.Callback callback : list) {
            if (callback instanceof IKnitLifeCycle) {
                ((IKnitLifeCycle) callback).onStop();
            }
        }
    }

    @Override // com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        clearMarketingViewList();
        clearCacheBeansList();
    }

    private void clearMarketingViewList() {
        List<View> list = this.mMarketingViewList;
        if (list == null) {
            LogUtil.a(TAG, "no need to clearMarketingViewList, mMarketingViewList is null!");
            return;
        }
        for (KeyEvent.Callback callback : list) {
            if (callback instanceof IKnitLifeCycle) {
                ((IKnitLifeCycle) callback).onDestroy();
            }
        }
        this.mMarketingViewList.clear();
        this.mMarketingViewList = null;
        this.mConsumer = null;
    }

    private void clearCacheBeansList() {
        List<SectionBean> list = this.mCacheBeansList;
        if (list == null) {
            LogUtil.a(TAG, "no need to clearCacheBeansList, mCacheBeansList is null!");
        } else {
            list.clear();
            this.mCacheBeansList = null;
        }
    }

    @Override // com.huawei.health.knit.section.listener.IPageResTrigger, com.huawei.health.knit.data.IKnitLifeCycle
    public void onActivityResult(int i, int i2, Intent intent) {
        LogUtil.a(R_TAG, "onActivityResult");
        List<View> list = this.mMarketingViewList;
        if (list == null) {
            LogUtil.a(TAG, "onDestroy with activity");
            return;
        }
        for (KeyEvent.Callback callback : list) {
            if (callback instanceof IKnitLifeCycle) {
                ((IKnitLifeCycle) callback).onActivityResult(i, i2, intent);
            }
        }
    }

    private void refreshResource(boolean z) {
        if (!z && eac.e(getPageCategory())) {
            this.mFirstTimeVisible = false;
            return;
        }
        if (this.mNeedRefresh && z && !this.mFirstTimeVisible && eac.e(getPageCategory())) {
            this.mNeedRefresh = false;
            Consumer consumer = this.mConsumer;
            if (consumer != null) {
                consumer.accept(null);
                this.mConsumer = null;
            }
        }
    }

    @Override // com.huawei.health.knit.data.IKnitLifeCycle
    public void setUserVisibleHint(boolean z) {
        LogUtil.a(R_TAG, "setUserVisibleHint,", Boolean.valueOf(z), Integer.valueOf(getPageType()));
        refreshResource(z);
        List<View> list = this.mMarketingViewList;
        if (list == null) {
            LogUtil.a(TAG, "setUserVisibleHint with activity");
            return;
        }
        for (KeyEvent.Callback callback : list) {
            if (callback instanceof IKnitLifeCycle) {
                ((IKnitLifeCycle) callback).setUserVisibleHint(z);
            }
        }
    }

    @Override // com.huawei.health.knit.data.IKnitLifeCycle
    public void onConfigurationChanged() {
        List<View> list = this.mMarketingViewList;
        if (list == null) {
            LogUtil.a(TAG, "onConfigurationChanged with activity");
            return;
        }
        for (KeyEvent.Callback callback : list) {
            if (callback instanceof IKnitLifeCycle) {
                ((IKnitLifeCycle) callback).onConfigurationChanged();
            }
        }
    }

    @Override // com.huawei.health.knit.data.IKnitLifeCycle
    public void onMultiWindowModeChanged() {
        List<View> list = this.mMarketingViewList;
        if (list == null) {
            LogUtil.a(TAG, "onConfigurationChanged with activity");
            return;
        }
        for (KeyEvent.Callback callback : list) {
            if (callback instanceof IKnitLifeCycle) {
                ((IKnitLifeCycle) callback).onMultiWindowModeChanged();
            }
        }
    }
}
