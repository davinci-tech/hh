package com.huawei.health.suggestion.ui.tabfragments.provider;

import android.content.Context;
import com.google.gson.reflect.TypeToken;
import com.huawei.health.R;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.knit.section.view.BaseSection;
import com.huawei.health.suggestion.ui.fitness.viewholder.FitnessSessionManager;
import com.huawei.health.suggestion.ui.tabfragments.provider.GolfStatisticProvider;
import com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessAchieveProvider;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSportStatDataAggregateOption;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.dictionary.utils.DicDataTypeUtil;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.operation.ble.BleConstants;
import defpackage.bzs;
import defpackage.bzw;
import defpackage.cau;
import defpackage.edu;
import defpackage.ffy;
import defpackage.gdx;
import defpackage.geb;
import defpackage.gee;
import defpackage.ixu;
import defpackage.ixx;
import defpackage.koq;
import defpackage.nrv;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes8.dex */
public class GolfStatisticProvider extends FitnessAchieveProvider {
    private SectionBean d;
    private Context e;
    private final b b = new b(this);

    /* renamed from: a, reason: collision with root package name */
    private a f3385a = new a(this);
    private BaseKnitDataProvider.d c = null;

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getCourseCategory() {
        return 0;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getPageType() {
        return 0;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public int getType() {
        return 263;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public boolean isCustomActive(Context context) {
        return true;
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessAchieveProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public /* bridge */ /* synthetic */ void parseParams(Context context, HashMap hashMap, Object obj) {
        parseParams(context, (HashMap<String, Object>) hashMap, (geb) obj);
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessAchieveProvider
    public String getEntranceTitle() {
        return BaseApplication.getContext().getString(R.string._2130851546_res_0x7f0236da);
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessAchieveProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void e(Context context, SectionBean sectionBean) {
        this.d = sectionBean;
        LogUtil.a("Track_Provider_Track_GolfStatisticProvider", "requestTrackStatData");
        HiSportStatDataAggregateOption hiSportStatDataAggregateOption = new HiSportStatDataAggregateOption();
        hiSportStatDataAggregateOption.setSortOrder(1);
        hiSportStatDataAggregateOption.setStartTime(0L);
        hiSportStatDataAggregateOption.setEndTime(System.currentTimeMillis());
        hiSportStatDataAggregateOption.setAggregateType(1);
        hiSportStatDataAggregateOption.setGroupUnitType(7);
        hiSportStatDataAggregateOption.setReadType(0);
        int[] iArr = {42405, 5, DicDataTypeUtil.DataType.SUM_GOLF_SWING_COUNT.value()};
        hiSportStatDataAggregateOption.setConstantsKey(new String[]{"Track_2205", "Track_2865", "Track_286_swing_count"});
        hiSportStatDataAggregateOption.setType(iArr);
        hiSportStatDataAggregateOption.setHiHealthTypes(new int[]{286, 30001});
        HiHealthNativeApi.a(BaseApplication.getContext()).aggregateSportStatData(hiSportStatDataAggregateOption, this.b);
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessAchieveProvider
    public void parseParams(Context context, HashMap<String, Object> hashMap, geb gebVar) {
        if (context == null) {
            context = BaseApplication.getContext();
        }
        this.e = context;
        LogUtil.a("Track_Provider_Track_GolfStatisticProvider", "parseParams");
        c(this.e, hashMap, gebVar);
    }

    private void c(Context context, HashMap<String, Object> hashMap, geb gebVar) {
        if (hashMap == null || gebVar == null) {
            LogUtil.h("Track_Provider_Track_GolfStatisticProvider", "setData viewMap or data is null");
            return;
        }
        hashMap.put("SECTION_STYLE", BaseSection.GOLF_STYLE);
        hashMap.put("RIGHT_TOP_BUTTON", context.getResources().getString(R.string._2130845481_res_0x7f021f29));
        hashMap.put("BACKGROUNDCOLOR", Integer.valueOf(context.getColor(R.color._2131296666_res_0x7f09019a)));
        hashMap.put("RIGHT_TOP_BUTTON_ICON", Integer.valueOf(R.drawable._2131430175_res_0x7f0b0b1f));
        hashMap.put("LEFT_TOP_VALUE", Long.valueOf(gebVar.d().a()));
        hashMap.put("LEFT_BOTTOM_TEXT", context.getResources().getString(R.string._2130845480_res_0x7f021f28));
        String quantityString = context.getResources().getQuantityString(R.plurals._2130903327_res_0x7f03011f, gebVar.d().e(), Integer.valueOf(gebVar.d().e()));
        if (LanguageUtil.h(this.e)) {
            hashMap.put("RIGHT_COMBINE_TEXT", ffy.awT_(context, String.valueOf(gebVar.d().e()), quantityString, R.style.sug_card_bg, R.style.sug_card_normal));
        } else {
            hashMap.put("RIGHT_COMBINE_TEXT", quantityString);
        }
        if (this.d != null) {
            for (gdx gdxVar : gebVar.c()) {
                gdxVar.b(this.d.k());
                gdxVar.a(context, getEntranceTitle());
            }
        }
        hashMap.put("SECTION16_9CARD_01_BANNER_DATA", a(gebVar));
        c(context, hashMap);
    }

    private List<edu> a(geb gebVar) {
        ArrayList arrayList = new ArrayList();
        ArrayList<gdx> c = gebVar.c();
        if (koq.b(c)) {
            LogUtil.b("Track_Provider_Track_GolfStatisticProvider", "createBannerItems failed cause bannerBeanList is empty");
            return arrayList;
        }
        for (gdx gdxVar : c) {
            arrayList.add(new edu(gdxVar.i(), gdxVar.a(), gdxVar.d(), gdxVar.f(), gdxVar.aLH_(), gdxVar.g()));
        }
        return arrayList;
    }

    /* renamed from: com.huawei.health.suggestion.ui.tabfragments.provider.GolfStatisticProvider$3, reason: invalid class name */
    public class AnonymousClass3 extends BaseKnitDataProvider.d {
        final /* synthetic */ Context e;

        public static /* synthetic */ void c(int i, Object obj) {
        }

        AnonymousClass3(Context context) {
            this.e = context;
        }

        @Override // com.huawei.health.knit.data.BaseKnitDataProvider.d, com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(String str) {
            super.onClick(str);
            if (nsn.a(500)) {
                LogUtil.a("Track_Provider_Track_GolfStatisticProvider", "onClick isFastClick");
                return;
            }
            if (LoginInit.getInstance(this.e).isBrowseMode()) {
                LoginInit.getInstance(this.e).browsingToLogin(new IBaseResponseCallback() { // from class: gei
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj) {
                        GolfStatisticProvider.AnonymousClass3.c(i, obj);
                    }
                }, "");
                return;
            }
            if ("ACCUMULATED_DURATION_CLICK_VIEW".equals(str)) {
                LogUtil.a("Track_Provider_Track_GolfStatisticProvider", "jump to SportHistoryActivity");
                FitnessSessionManager.SessionActivityAction c = FitnessSessionManager.e().c();
                if (c == null) {
                    LogUtil.h("Track_Provider_Track_GolfStatisticProvider", "FitnessSessionManager getSessionForActivityAction null");
                    return;
                }
                c.startSportHistoryActivity(this.e, 286, 286);
                HashMap hashMap = new HashMap();
                hashMap.put("click", 1);
                hashMap.put(BleConstants.SPORT_TYPE, 286);
                ixx.d().d(BaseApplication.getContext(), AnalyticsValue.GOLF_COURSE_MAP_CLICK_EVENT.value(), hashMap, 0);
                return;
            }
            if ("ITEM_BUTTON_TEXT".equals(str)) {
                LogUtil.a("Track_Provider_Track_GolfStatisticProvider", "jump to H5 course map");
                GolfStatisticProvider.this.d(this.e);
            }
        }
    }

    private void c(Context context, HashMap<String, Object> hashMap) {
        if (this.c == null) {
            this.c = new AnonymousClass3(context);
        }
        hashMap.put("CLICK_EVENT_LISTENER", this.c);
    }

    private void e(Context context) {
        if (context == null) {
            LogUtil.h("Track_Provider_Track_GolfStatisticProvider", "jumpToGolfCourseMap(): context is null");
            return;
        }
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        builder.addCustomizeJsModule(RemoteMessageConst.NOTIFICATION, bzs.e().getCommonJsModule(RemoteMessageConst.NOTIFICATION));
        builder.addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi"));
        builder.addCustomizeJsModule("achievement", bzw.e().getCommonJsModule("achievement"));
        builder.setImmerse();
        builder.showStatusBar();
        bzs.e().loadH5ProApp(context, "com.huawei.health.h5.golf", builder);
    }

    private void b(geb gebVar) {
        BigDecimal bigDecimal = new BigDecimal(0);
        BigDecimal bigDecimal2 = new BigDecimal(0);
        for (HiHealthData hiHealthData : gebVar.e()) {
            if (hiHealthData != null) {
                int i = hiHealthData.getInt("hihealth_type");
                if (i == 286) {
                    Object obj = hiHealthData.get("Track_2865");
                    if (obj instanceof Double) {
                        bigDecimal = bigDecimal.add(BigDecimal.valueOf(((Double) obj).doubleValue()));
                    }
                    Object obj2 = hiHealthData.get("Track_286_swing_count");
                    if (obj2 instanceof Double) {
                        bigDecimal2 = bigDecimal2.add(BigDecimal.valueOf(((Double) obj2).doubleValue()));
                    }
                } else if (i == 30001) {
                    Object obj3 = hiHealthData.get("Track_2205");
                    if (obj3 instanceof Double) {
                        bigDecimal = bigDecimal.add(BigDecimal.valueOf(((Double) obj3).doubleValue()));
                    }
                }
            }
        }
        gee geeVar = new gee();
        geeVar.d(bigDecimal2.intValue());
        geeVar.e(bigDecimal.longValue());
        gebVar.c(geeVar);
    }

    static class a implements IBaseResponseCallback {
        WeakReference<GolfStatisticProvider> e;

        a(GolfStatisticProvider golfStatisticProvider) {
            this.e = new WeakReference<>(golfStatisticProvider);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            List list;
            GolfStatisticProvider golfStatisticProvider = this.e.get();
            if (golfStatisticProvider == null) {
                return;
            }
            LogUtil.a("Track_Provider_Track_GolfStatisticProvider", "onResponse ", obj);
            if (koq.e(obj, HiHealthData.class)) {
                list = (List) obj;
                LogUtil.a("Track_Provider_Track_GolfStatisticProvider", list);
            } else {
                LogUtil.h("Track_Provider_Track_GolfStatisticProvider", "class type is wrong");
                list = null;
            }
            golfStatisticProvider.e((List<HiHealthData>) list);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<HiHealthData> list) {
        if (this.mAchieveBean == null) {
            LogUtil.b("Track_Provider_Track_GolfStatisticProvider", "updateGolfNumber failed, cause no mAchieveBean");
            return;
        }
        if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            this.mAchieveBean.a(Collections.singletonList(new HiHealthData()));
        } else if (list != null) {
            this.mAchieveBean.a(list);
        } else {
            this.mAchieveBean.a(Collections.singletonList(new HiHealthData()));
        }
        b(this.mAchieveBean);
        if (this.d == null || !b()) {
            return;
        }
        this.d.e(this.mAchieveBean);
    }

    private boolean b() {
        geb gebVar = (geb) ixu.e(cau.a(String.valueOf(getType())), new TypeToken<geb>() { // from class: com.huawei.health.suggestion.ui.tabfragments.provider.GolfStatisticProvider.4
        });
        if (this.mAchieveBean != null && this.mAchieveBean.equals(gebVar)) {
            LogUtil.h("Track_Provider_Track_GolfStatisticProvider", "same data");
            return false;
        }
        LogUtil.a("Track_Provider_Track_GolfStatisticProvider", "refresh cache is ", nrv.b(this.mAchieveBean));
        cau.d(String.valueOf(getType()), nrv.b(this.mAchieveBean));
        return true;
    }

    static class b implements HiAggregateListener {
        WeakReference<GolfStatisticProvider> d;

        b(GolfStatisticProvider golfStatisticProvider) {
            this.d = new WeakReference<>(golfStatisticProvider);
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            GolfStatisticProvider golfStatisticProvider = this.d.get();
            if (golfStatisticProvider == null) {
                LogUtil.h("Track_Provider_Track_GolfStatisticProvider", "onResult: GolfStatisticProvider is null");
                return;
            }
            LogUtil.a("Track_Provider_Track_GolfStatisticProvider", "onResult: errorCode " + i + " data: " + list);
            a aVar = golfStatisticProvider.f3385a;
            if (aVar == null) {
                LogUtil.h("Track_Provider_Track_GolfStatisticProvider", "requestTrackStatData callback is null");
            } else if (list == null || list.size() == 0) {
                LogUtil.h("Track_Provider_Track_GolfStatisticProvider", "datas is null");
                aVar.d(0, null);
            } else {
                aVar.d(i, list);
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            LogUtil.a("Track_Provider_Track_GolfStatisticProvider", "onResultIntent");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(Context context) {
        e(context);
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        ixx.d().d(BaseApplication.getContext(), AnalyticsValue.GOLF_COURSE_MAP_CLICK_EVENT.value(), hashMap, 0);
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessAchieveProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider
    public String getSubViewTitle() {
        return "";
    }

    @Override // com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessAchieveProvider, com.huawei.health.suggestion.ui.tabfragments.provider.abstractproviders.FitnessEntranceProvider, com.huawei.health.knit.data.BaseKnitDataProvider
    public String getLogTag() {
        return "Track_Provider_Track_GolfStatisticProvider";
    }
}
