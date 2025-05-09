package com.huawei.ui.main.stories.oa.goodthingsrecommended.provider;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceContentBase;
import com.huawei.health.userlabelmgr.api.UserLabelServiceApi;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.operation.PluginOperation;
import com.huawei.ui.main.R$string;
import com.huawei.ui.main.stories.oa.goodthingsrecommended.provider.GoodThingsGridProvider;
import defpackage.ash;
import defpackage.eil;
import defpackage.koq;
import defpackage.nrv;
import defpackage.riu;
import defpackage.rja;
import defpackage.rjd;
import defpackage.rjg;
import defpackage.rjl;
import defpackage.rjm;
import defpackage.rjo;
import defpackage.rjp;
import defpackage.rjq;
import health.compact.a.Services;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes9.dex */
public abstract class GoodThingsGridProvider extends BaseKnitDataProvider<rjo> {
    private static final String NO_INTERESTING_CLICK_INFO_PRODUCT_SCENARIO = "NO_INTRSTING_CLICK_INFO_RRODUCT_SCENARIO";
    private static final int ONE_PAGE_LIMIT = 3;
    private static final String TAG = "GoodThingsGridProvider";
    private static final String USER_AGE = "GoodThingsGridProvider_user_age";
    private static final String USER_SEX = "GoodThingsGridProvider_user_sex";
    private BaseKnitDataProvider.d mClickSectionListener;
    private int mContentType;
    private HiUserInfo mHiUserInfo;
    private String mPetalOaid;
    protected int mResPosId;
    protected ResourceBriefInfo mResourceBriefInfo;
    private WeakReference<SectionBean> mSectionBeanRef;
    public long mShowStartTime;
    private rjp mTemplate;
    private String[] mUserLabel;
    private List<rjq> shoppingInfoItemList = new ArrayList();
    protected rjl mPaginationReq = new rjl(3);
    public rjm mPaginationRsp = new rjm();

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public /* bridge */ /* synthetic */ void parseParams(Context context, HashMap hashMap, Object obj) {
        parseParams(context, (HashMap<String, Object>) hashMap, (rjo) obj);
    }

    public GoodThingsGridProvider() {
        setIsActive(false);
        ThreadPoolManager.d().execute(new Runnable() { // from class: rjt
            @Override // java.lang.Runnable
            public final void run() {
                GoodThingsGridProvider.this.readCachParams();
            }
        });
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadData(Context context, SectionBean sectionBean) {
        super.loadData(context, sectionBean);
        if (isActive(null)) {
            return;
        }
        this.mSectionBeanRef = new WeakReference<>(sectionBean);
        if (processMarkingResource(sectionBean) && isNeedLoadData()) {
            LogUtil.a(TAG, "loadData");
            getNetData(sectionBean);
        }
    }

    private String[] getUserLabels() {
        String accountInfo = LoginInit.getInstance(null).getAccountInfo(1011);
        List<String> labels = ((UserLabelServiceApi) Services.c("HWUserLabelMgr", UserLabelServiceApi.class)).getLabels(new ArrayList<String>() { // from class: com.huawei.ui.main.stories.oa.goodthingsrecommended.provider.GoodThingsGridProvider.2
            {
                add("health_sport_sleep_care");
                add("health_sport_sleep_quality");
                add("health_sport_weight_care");
                add("health_sport_weight_size");
                add("health_sport_heart_rate");
                add("health_sport_blood_oxygen");
            }
        }, accountInfo);
        return (String[]) labels.toArray(new String[labels.size()]);
    }

    private void generateUserInfoLabel() {
        HiHealthManager.d(BaseApplication.getContext()).fetchUserData(new b(this));
    }

    static final class b implements HiCommonListener {
        private WeakReference<GoodThingsGridProvider> d;

        public b(GoodThingsGridProvider goodThingsGridProvider) {
            this.d = null;
            this.d = new WeakReference<>(goodThingsGridProvider);
        }

        @Override // com.huawei.hihealth.data.listener.HiCommonListener
        public void onSuccess(int i, Object obj) {
            if (!(obj instanceof List) || i != 0) {
                LogUtil.h(GoodThingsGridProvider.TAG, "generateBmiLabel null");
                return;
            }
            List list = (List) obj;
            if (list.isEmpty()) {
                LogUtil.h(GoodThingsGridProvider.TAG, "generateBmiLabel userInfos empty");
                return;
            }
            LogUtil.a(GoodThingsGridProvider.TAG, "generateBmiLabel, userInfos.size() = ", Integer.valueOf(list.size()));
            HiUserInfo hiUserInfo = (HiUserInfo) list.get(0);
            GoodThingsGridProvider goodThingsGridProvider = this.d.get();
            if (goodThingsGridProvider != null) {
                goodThingsGridProvider.mHiUserInfo = hiUserInfo;
                if (goodThingsGridProvider.mHiUserInfo == null || !goodThingsGridProvider.mHiUserInfo.isGenderValid()) {
                    return;
                }
                ash.a(GoodThingsGridProvider.USER_SEX, "" + goodThingsGridProvider.mHiUserInfo.getGender());
                ash.a(GoodThingsGridProvider.USER_AGE, "" + goodThingsGridProvider.mHiUserInfo.getAge());
                return;
            }
            LogUtil.b(GoodThingsGridProvider.TAG, "DataListener onSuccess abort, provider is null!");
        }

        @Override // com.huawei.hihealth.data.listener.HiCommonListener
        public void onFailure(int i, Object obj) {
            LogUtil.b(GoodThingsGridProvider.TAG, "enter generateBmiLabel onFailure, errCode = ", Integer.valueOf(i), " errMsg = ", obj);
        }
    }

    protected void getNetData(final SectionBean sectionBean) {
        LogUtil.a(TAG, "getNetData");
        rjd.b().getShoppingInfoList(buildGetShoppingInfoListReq(), new UiCallback<rjg>() { // from class: com.huawei.ui.main.stories.oa.goodthingsrecommended.provider.GoodThingsGridProvider.5
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str) {
                LogUtil.a(GoodThingsGridProvider.TAG, "getShoppingInfoList onFailure ", str);
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(rjg rjgVar) {
                List<rjq> e = rjgVar.e();
                LogUtil.a(GoodThingsGridProvider.TAG, "getShoppingInfoList success list is null ", Boolean.valueOf(koq.b(e)));
                if (koq.b(e)) {
                    return;
                }
                GoodThingsGridProvider.this.setIsActive(true);
                rjo rjoVar = new rjo();
                rjoVar.a(e);
                GoodThingsGridProvider.this.mPaginationRsp = rjgVar.d();
                sectionBean.e(rjoVar);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void readCachParams() {
        this.mPetalOaid = riu.c().get("OAID");
        this.mUserLabel = getUserLabels();
        generateUserInfoLabel();
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x005d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected defpackage.rjf buildGetShoppingInfoListReq() {
        /*
            r7 = this;
            java.lang.String r0 = com.huawei.operation.utils.PhoneInfoUtils.getPhoneType()
            android.content.Context r1 = com.huawei.hwcommonmodel.application.BaseApplication.getContext()
            com.huawei.login.ui.login.LoginInit r1 = com.huawei.login.ui.login.LoginInit.getInstance(r1)
            java.lang.String r1 = r1.getDeviceId()
            com.huawei.hihealth.HiUserInfo r2 = r7.mHiUserInfo
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L29
            boolean r2 = r2.isGenderValid()
            if (r2 == 0) goto L29
            com.huawei.hihealth.HiUserInfo r2 = r7.mHiUserInfo
            int r2 = r2.getGender()
            com.huawei.hihealth.HiUserInfo r5 = r7.mHiUserInfo
            int r5 = r5.getAge()
            goto L4b
        L29:
            java.lang.String r2 = "GoodThingsGridProvider_user_sex"
            java.lang.String r2 = defpackage.ash.b(r2)
            java.lang.String r5 = "GoodThingsGridProvider_user_age"
            java.lang.String r5 = defpackage.ash.b(r5)
            int r2 = java.lang.Integer.parseInt(r2)     // Catch: java.lang.NumberFormatException -> L3e
            int r5 = java.lang.Integer.parseInt(r5)     // Catch: java.lang.NumberFormatException -> L3f
            goto L4b
        L3e:
            r2 = r3
        L3f:
            java.lang.String r5 = "USER_SEX or USER_AGE NumberFormatException"
            java.lang.Object[] r5 = new java.lang.Object[]{r5}
            java.lang.String r6 = "GoodThingsGridProvider"
            com.huawei.hwlogsmodel.LogUtil.b(r6, r5)
            r5 = r4
        L4b:
            if (r2 < 0) goto L51
            if (r2 <= r3) goto L50
            goto L51
        L50:
            r3 = r2
        L51:
            if (r5 >= r4) goto L54
            goto L55
        L54:
            r4 = r5
        L55:
            java.lang.String r2 = r7.mPetalOaid
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 == 0) goto L61
            java.lang.String r2 = "0"
            r7.mPetalOaid = r2
        L61:
            rjp r2 = r7.mTemplate
            java.lang.String r2 = r2.f()
            r5 = 0
            java.lang.String r5 = health.compact.a.HiDateUtil.d(r5)
            rjf$a r6 = new rjf$a
            r6.<init>()
            rjb$d r0 = r6.e(r0)
            rjf$a r0 = (rjf.a) r0
            rjb$d r0 = r0.d(r1)
            rjf$a r0 = (rjf.a) r0
            java.lang.String r1 = r7.mPetalOaid
            rjb$d r0 = r0.b(r1)
            rjf$a r0 = (rjf.a) r0
            rjf$a r0 = r0.c(r3)
            java.lang.String[] r1 = r7.mUserLabel
            rjf$a r0 = r0.e(r1)
            rjf$a r0 = r0.a(r2)
            rjf$a r0 = r0.c(r5)
            rjf$a r0 = r0.e(r4)
            rjl r1 = r7.mPaginationReq
            rjf$a r0 = r0.c(r1)
            rjp r1 = r7.mTemplate
            int r1 = r1.e()
            rjf$a r0 = r0.a(r1)
            rjf r0 = r0.c()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.oa.goodthingsrecommended.provider.GoodThingsGridProvider.buildGetShoppingInfoListReq():rjf");
    }

    public void parseParams(Context context, HashMap<String, Object> hashMap, rjo rjoVar) {
        super.parseParams(context, hashMap, (HashMap<String, Object>) rjoVar);
        LogUtil.a(TAG, "parseParams");
        hashMap.put("TITLE", this.mTemplate.b());
        hashMap.put("SUBTITLE", this.mTemplate.h());
        if (this.mContentType == 102) {
            hashMap.put("RECYCLER_LIST_ITEM_RESID", Integer.valueOf(R.layout.section1_1list_02_item_02));
            hashMap.put("RECYCLER_LAYOUT_MANAGER_ORITENTION", 0);
            hashMap.put("HORIZONTAL_DATA_ITEM_SIZE", Integer.valueOf(this.shoppingInfoItemList.size()));
            this.shoppingInfoItemList.addAll(rjoVar.c());
            rjoVar.a(this.shoppingInfoItemList);
            LogUtil.a(TAG, "Horizontal list size: ", Integer.valueOf(rjoVar.e().size()));
        } else {
            this.shoppingInfoItemList.clear();
            LogUtil.a(TAG, "Vertical list size: ", Integer.valueOf(rjoVar.e().size()));
        }
        rjoVar.d(this.mTemplate.i());
        hashMap.put("HORIZONTAL_DATA_HAS_NEXT", Boolean.valueOf(TextUtils.isEmpty(this.mPaginationRsp.b())));
        hashMap.put("RECYCLER_LIST", rjoVar.e());
        ResourceBriefInfo resourceBriefInfo = this.mResourceBriefInfo;
        if (resourceBriefInfo != null) {
            hashMap.put("RESOURCE_NAME", resourceBriefInfo.getResourceName());
            hashMap.put("RESOURCE_ID", this.mResourceBriefInfo.getResourceId());
            hashMap.put("algId", "");
            hashMap.put("smartRecommend", Boolean.valueOf(this.mResourceBriefInfo.getSmartRecommend()));
        }
        hashMap.put("pullOrder", 1);
        hashMap.put("POSITION_ID", Integer.valueOf(this.mResPosId));
        if (this.mTemplate.a() == 0) {
            hashMap.put("SUBHEADER_ACTION_TEXT", null);
        }
        setClickListener(context, hashMap, rjoVar);
    }

    private void setClickListener(final Context context, HashMap<String, Object> hashMap, final rjo rjoVar) {
        BaseKnitDataProvider.d dVar = new BaseKnitDataProvider.d() { // from class: com.huawei.ui.main.stories.oa.goodthingsrecommended.provider.GoodThingsGridProvider.3
            @Override // com.huawei.health.knit.data.BaseKnitDataProvider.d, com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
                super.onClick(str);
                if ("SUBHEADER_ACTION_CLICKED".equals(str)) {
                    GoodThingsGridProvider.this.onNoInterestButtonClicked();
                } else if ("BOTTOM_BUTTON_CLICKED".equals(str)) {
                    GoodThingsGridProvider.this.onChangeBatchButtonClicked();
                }
            }

            @Override // com.huawei.health.knit.data.BaseKnitDataProvider.d, com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
                LogUtil.a(GoodThingsGridProvider.TAG, "onItem clicked");
                if (koq.b(rjoVar.c(), i)) {
                    return;
                }
                GoodThingsGridProvider.this.jumpToShoppingInfoH5(context, rjoVar.c().get(i), i);
            }
        };
        this.mClickSectionListener = dVar;
        hashMap.put("CLICK_EVENT_LISTENER", dVar);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void jumpToShoppingInfoH5(Context context, rjq rjqVar, int i) {
        if (rjqVar.b() == null) {
            LogUtil.h(TAG, "jumpToShoppingInfoH5 url is null");
            return;
        }
        String b2 = rjqVar.b().b();
        StringBuilder sb = new StringBuilder();
        sb.append(b2);
        sb.append(b2.contains("?") ? "&h5pro=true&isImmerse=true&isStartAtBottomOfStatusBar=true" : "?h5pro=true&isImmerse=true&isStartAtBottomOfStatusBar=true");
        PluginOperation.getInstance(context).startOperationWebPage(sb.toString());
        eil.c(this.mResPosId, this.mResourceBriefInfo, this.mShowStartTime, this.mTemplate.b(), false, i);
        this.mShowStartTime = System.currentTimeMillis();
    }

    protected void onNoInterestButtonClicked() {
        LogUtil.a(TAG, "onNoInterstButtonClicked");
        updateButtonClickedInfo();
        WeakReference<SectionBean> weakReference = this.mSectionBeanRef;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        setIsActive(false);
        this.mSectionBeanRef.get().e((Object) null);
    }

    protected void updateButtonClickedInfo() {
        rja rjaVar;
        String b2 = ash.b(getSharedPreferenceKey());
        if (TextUtils.isEmpty(b2)) {
            rjaVar = new rja(0, System.currentTimeMillis());
        } else {
            rjaVar = (rja) nrv.b(b2, rja.class);
        }
        rjaVar.e(rjaVar.b() + 1);
        rjaVar.e(System.currentTimeMillis());
        ash.a(getSharedPreferenceKey(), nrv.a(rjaVar));
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    protected void updatePaginationRsp() {
        /*
            r4 = this;
            rjm r0 = r4.mPaginationRsp
            java.lang.String r0 = r0.b()
            r1 = 1
            if (r0 == 0) goto L1f
            rjm r0 = r4.mPaginationRsp
            java.lang.String r0 = r0.b()
            int r0 = java.lang.Integer.parseInt(r0)     // Catch: java.lang.NumberFormatException -> L14
            goto L20
        L14:
            java.lang.String r2 = "nextStr NumberFormatException "
            java.lang.Object[] r0 = new java.lang.Object[]{r2, r0}
            java.lang.String r2 = "GoodThingsGridProvider"
            com.huawei.hwlogsmodel.LogUtil.b(r2, r0)
        L1f:
            r0 = r1
        L20:
            if (r0 >= r1) goto L23
            goto L24
        L23:
            r1 = r0
        L24:
            rjl r0 = r4.mPaginationReq
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = ""
            r2.<init>(r3)
            r2.append(r1)
            java.lang.String r1 = r2.toString()
            r0.a(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.ui.main.stories.oa.goodthingsrecommended.provider.GoodThingsGridProvider.updatePaginationRsp():void");
    }

    protected void onChangeBatchButtonClicked() {
        LogUtil.a(TAG, "onChangeBatchButtonClicked");
        updatePaginationRsp();
        WeakReference<SectionBean> weakReference = this.mSectionBeanRef;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        getNetData(this.mSectionBeanRef.get());
    }

    protected String getSharedPreferenceKey() {
        return NO_INTERESTING_CLICK_INFO_PRODUCT_SCENARIO + this.mTemplate.f() + this.mResPosId;
    }

    private boolean isNeedLoadData() {
        if (this.mTemplate.a() == 0) {
            return true;
        }
        String b2 = ash.b(getSharedPreferenceKey());
        if (TextUtils.isEmpty(b2)) {
            return true;
        }
        rja rjaVar = (rja) nrv.b(b2, rja.class);
        return System.currentTimeMillis() - rjaVar.d() > (rjaVar.b() >= this.mTemplate.c() ? ((long) this.mTemplate.d()) * 2678400000L : 86400000L);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        LogUtil.a(TAG, "loadDefaultData");
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("RECYCLER_LIST_ITEM_RESID", Integer.valueOf(R.layout.section1_1list_02_item_01));
        hashMap.put("BOTTOM_TEXT", BaseApplication.getContext().getResources().getString(R$string.IDS_good_things_recommend_change_batch));
        hashMap.put("IS_SUPPORT_SHARE", false);
        sectionBean.a(hashMap);
    }

    private boolean processMarkingResource(SectionBean sectionBean) {
        ResourceContentBase content;
        rjp rjpVar;
        LogUtil.a(TAG, "processMarkingResource");
        ResourceBriefInfo m = sectionBean.m();
        if (m == null) {
            return false;
        }
        this.mResourceBriefInfo = m;
        this.mContentType = m.getContentType();
        this.mResPosId = sectionBean.k();
        int i = this.mContentType;
        if ((i != 102 && i != 103) || (content = m.getContent()) == null || content.getContent() == null || (rjpVar = (rjp) nrv.b(content.getContent(), rjp.class)) == null) {
            return false;
        }
        this.mTemplate = rjpVar;
        this.mPaginationReq.c(rjpVar.j());
        return true;
    }
}
