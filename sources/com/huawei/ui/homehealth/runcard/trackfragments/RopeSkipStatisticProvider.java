package com.huawei.ui.homehealth.runcard.trackfragments;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import com.huawei.browse.BrowsingBiEvent;
import com.huawei.btsportdevice.callback.MessageOrStateCallback;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.router.AppRouter;
import com.huawei.health.R;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.health.knit.section.view.BaseSection;
import com.huawei.health.marketing.api.MarketingApi;
import com.huawei.health.marketing.datatype.ResourceBriefInfo;
import com.huawei.health.marketing.datatype.ResourceResultInfo;
import com.huawei.health.marketing.datatype.templates.RopeBackgroundTemplate;
import com.huawei.health.sport.utils.SportSupportUtil;
import com.huawei.health.suggestion.ui.fitness.viewholder.FitnessSessionManager;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiHealthClient;
import com.huawei.hihealth.HiHealthData;
import com.huawei.hihealth.HiSportStatDataAggregateOption;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiAggregateListener;
import com.huawei.hihealth.data.listener.HiSubscribeListener;
import com.huawei.hihealth.data.listener.HiUnSubscribeListener;
import com.huawei.hihealth.util.HiJsonUtil;
import com.huawei.hmf.tasks.OnSuccessListener;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.nfc.PluginPayAdapter;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginsport.multilingualaudio.AudioResDownloadListener;
import com.huawei.pluginsport.multilingualaudio.AudioResProviderInterface;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.homehealth.runcard.trackfragments.RopeSkipStatisticProvider;
import com.huawei.ui.homehealth.runcard.utils.SportMusicUtils;
import defpackage.ash;
import defpackage.cau;
import defpackage.cei;
import defpackage.cjx;
import defpackage.ckw;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.dds;
import defpackage.dks;
import defpackage.dky;
import defpackage.gie;
import defpackage.gnm;
import defpackage.gso;
import defpackage.gwg;
import defpackage.gww;
import defpackage.ixx;
import defpackage.koq;
import defpackage.mxb;
import defpackage.mxc;
import defpackage.nsf;
import defpackage.nsn;
import defpackage.ofr;
import defpackage.owp;
import defpackage.rud;
import health.compact.a.CommonUtil;
import health.compact.a.CommonUtils;
import health.compact.a.LanguageUtil;
import health.compact.a.Services;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes9.dex */
public class RopeSkipStatisticProvider extends BaseKnitDataProvider<HiHealthData> {
    private Context e;
    private boolean h;
    private SectionBean k;
    private RopeSkippingTargetDialog o;
    private gww q;
    private int r;
    private SportMusicUtils s;
    private int t;
    private h p = new h(this);
    private b g = new b(this);
    private d i = new d(this);
    private List<Integer> l = null;
    private Bitmap d = null;

    /* renamed from: a, reason: collision with root package name */
    private int f9548a = 7;
    private HiHealthData f = new HiHealthData();
    private Handler n = new c(Looper.getMainLooper(), this);
    private String j = "";
    private Handler m = new Handler() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.RopeSkipStatisticProvider.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message == null) {
                LogUtil.h("Track_Provider_Track_RopeSkipStatisticProvider", "mCustomTargetValueHandler msg is null");
                return;
            }
            super.handleMessage(message);
            RopeSkipStatisticProvider.this.t = message.arg1;
            if (message.obj instanceof Float) {
                RopeSkipStatisticProvider.this.r = (int) ((Float) message.obj).floatValue();
            }
            LogUtil.a("Track_Provider_Track_RopeSkipStatisticProvider", "mRopeJumpRefreshHandler, msg.what=", Integer.valueOf(message.what), " mTargetType:", Integer.valueOf(RopeSkipStatisticProvider.this.t), "mTargetValue:", Integer.valueOf(RopeSkipStatisticProvider.this.r));
            boolean z = message.arg2 != 2;
            RopeSkipStatisticProvider ropeSkipStatisticProvider = RopeSkipStatisticProvider.this;
            ropeSkipStatisticProvider.a(ropeSkipStatisticProvider.t, RopeSkipStatisticProvider.this.r, true, z);
            if (RopeSkipStatisticProvider.this.k != null) {
                RopeSkipStatisticProvider.this.k.e(RopeSkipStatisticProvider.this.f);
            }
        }
    };
    private String c = ash.b("rope_background_image_url");
    private String b = ash.b("rope_background_image_url_tahiti");

    /* JADX INFO: Access modifiers changed from: private */
    public int b() {
        return 283;
    }

    public static /* synthetic */ void d(int i, Object obj) {
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return true;
    }

    public RopeSkipStatisticProvider() {
        k();
        this.s = new SportMusicUtils(BaseApplication.getContext());
    }

    private void k() {
        this.q = new gww(BaseApplication.getContext(), new StorageParams(1), Integer.toString(20002));
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        if (this.f != null) {
            String a2 = cau.a(String.valueOf(283));
            double a3 = !TextUtils.isEmpty(a2) ? CommonUtils.a(a2) : 0.0d;
            LogUtil.a("Track_Provider_Track_RopeSkipStatisticProvider", "loadDefaultData() cachedSumCountStr: ", a2, " cachedRopeJumpSumCount: ", Double.valueOf(a3));
            this.f.putDouble("TrackRepoJumpSum", a3);
        }
        HiHealthNativeApi.a(BaseApplication.getContext()).subscribeHiHealthData(4, new a(this));
        sectionBean.e(this.f);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void e(Context context, SectionBean sectionBean) {
        this.k = sectionBean;
        LogUtil.a("Track_Provider_Track_RopeSkipStatisticProvider", "requestTrackStatDistance sportType = ", Integer.valueOf(b()), " timeUnit = ", 7);
        if (!this.h) {
            d();
        }
        l();
        q();
        dds.c().e(this.i);
    }

    private void q() {
        if (this.q.f(283) != 1 || this.q.d(283) == null) {
            if (this.d != null) {
                this.d = null;
                LogUtil.a("Track_Provider_Track_RopeSkipStatisticProvider", "has bitmap before");
                this.k.e(this.f);
                return;
            }
            return;
        }
        this.s.b(this.q, 283, new e(this));
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.IKnitLifeCycle
    public void onDestroy() {
        super.onDestroy();
        v();
    }

    private void v() {
        if (koq.c(this.l)) {
            LogUtil.a("Track_Provider_Track_RopeSkipStatisticProvider", "unRopeSkipSubscribeListener");
            HiHealthNativeApi.a(this.e).unSubscribeHiHealthData(this.l, new HiUnSubscribeListener() { // from class: oqx
                @Override // com.huawei.hihealth.data.listener.HiUnSubscribeListener
                public final void onResult(boolean z) {
                    LogUtil.a("Track_Provider_Track_RopeSkipStatisticProvider", "unRopeSkipSubscribeListener isSuccess = ", Boolean.valueOf(z));
                }
            });
        } else {
            LogUtil.h("Track_Provider_Track_RopeSkipStatisticProvider", "rope skip data listener has unregister.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        HiSportStatDataAggregateOption hiSportStatDataAggregateOption = new HiSportStatDataAggregateOption();
        hiSportStatDataAggregateOption.setSortOrder(1);
        hiSportStatDataAggregateOption.setStartTime(0L);
        hiSportStatDataAggregateOption.setEndTime(System.currentTimeMillis());
        hiSportStatDataAggregateOption.setAggregateType(1);
        hiSportStatDataAggregateOption.setGroupUnitType(7);
        hiSportStatDataAggregateOption.setReadType(0);
        hiSportStatDataAggregateOption.setConstantsKey(new String[]{"TrackRepoJumpSum"});
        hiSportStatDataAggregateOption.setType(new int[]{12});
        hiSportStatDataAggregateOption.setHiHealthTypes(new int[]{283});
        HiHealthNativeApi.a(BaseApplication.getContext()).aggregateSportStatData(hiSportStatDataAggregateOption, this.g);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, HiHealthData hiHealthData) {
        this.e = context == null ? BaseApplication.getContext() : context;
        if (this.o == null) {
            this.o = new RopeSkippingTargetDialog(this.e);
        }
        LogUtil.a("Track_Provider_Track_RopeSkipStatisticProvider", "parseParams");
        d(context, hashMap, hiHealthData);
    }

    private void d(Context context, HashMap<String, Object> hashMap, HiHealthData hiHealthData) {
        if (hashMap == null || hiHealthData == null) {
            LogUtil.h("Track_Provider_Track_RopeSkipStatisticProvider", "setData viewMap or data is null");
            return;
        }
        hashMap.put("SECTION_STYLE", BaseSection.ROPE_SKIPPING_STYLE);
        if (!TextUtils.isEmpty(this.c) && !TextUtils.isEmpty(this.b)) {
            ArrayList arrayList = new ArrayList(2);
            arrayList.add(this.c);
            arrayList.add(this.b);
            hashMap.put("BACKGROUND", arrayList);
            this.h = true;
        } else {
            ArrayList arrayList2 = new ArrayList(2);
            arrayList2.add(Integer.valueOf(R.drawable._2131430541_res_0x7f0b0c8d));
            arrayList2.add(Integer.valueOf(R.drawable._2131430542_res_0x7f0b0c8e));
            hashMap.put("BACKGROUND", arrayList2);
        }
        ArrayList arrayList3 = new ArrayList(2);
        arrayList3.add(Integer.valueOf(R.drawable._2131431944_res_0x7f0b1208));
        arrayList3.add(Integer.valueOf(R.drawable._2131431944_res_0x7f0b1208));
        hashMap.put("MIDDLE_IMAGEVIEW", arrayList3);
        hashMap.put("MIDDLE_IMAGEVIEW_DESC", nsf.h(R.string._2130843708_res_0x7f02183c));
        e(hashMap, hiHealthData);
        a(context, (Map<String, Object>) hashMap);
        if (m()) {
            if (gso.e().o()) {
                hashMap.put("RIGHT_TOP_IMAGE", Integer.valueOf(R.drawable._2131430341_res_0x7f0b0bc5));
            } else {
                hashMap.put("RIGHT_TOP_IMAGE", Integer.valueOf(R.drawable._2131430343_res_0x7f0b0bc7));
            }
            hashMap.put("RIGHT_TOP_IMAGE_DESC", this.e.getResources().getString(R.string.IDS_device_pair_connect));
        }
        d(hashMap);
        if (Utils.j()) {
            hashMap.put("LEFT_IMAGEVIEW", f());
        }
        a(context, hashMap);
    }

    private List<Integer> f() {
        ArrayList arrayList = new ArrayList(4);
        Integer valueOf = Integer.valueOf(R.drawable._2131430573_res_0x7f0b0cad);
        arrayList.add(valueOf);
        arrayList.add(valueOf);
        arrayList.add(Integer.valueOf(R.drawable._2131430459_res_0x7f0b0c3b));
        arrayList.add(Integer.valueOf(R.string._2130842050_res_0x7f0211c2));
        return arrayList;
    }

    private void d(Map<String, Object> map) {
        if (!CommonUtil.bd() || !gwg.i(this.e)) {
            map.put("RIGHT_LAYOUT_VISIBLE", 8);
            return;
        }
        map.put("RIGHT_LAYOUT_VISIBLE", 0);
        String d2 = this.q.d(283);
        if (this.q.f(283) != 1 || d2 == null) {
            map.put("RIGHT_IMAGEVIEW", g());
            return;
        }
        Bitmap bitmap = this.d;
        if (bitmap != null) {
            map.put("RIGHT_IMAGEVIEW", Collections.singletonList(bitmap));
        } else {
            map.put("RIGHT_IMAGEVIEW", g());
        }
    }

    static class e implements IBaseResponseCallback {
        WeakReference<RopeSkipStatisticProvider> c;

        e(RopeSkipStatisticProvider ropeSkipStatisticProvider) {
            this.c = new WeakReference<>(ropeSkipStatisticProvider);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            LogUtil.a("Track_Provider_Track_RopeSkipStatisticProvider", "loadMusicCallback errorCode is ", Integer.valueOf(i));
            RopeSkipStatisticProvider ropeSkipStatisticProvider = this.c.get();
            if (ropeSkipStatisticProvider != null) {
                if (i != 100000 || obj == null) {
                    ropeSkipStatisticProvider.d = null;
                } else if (obj instanceof Bitmap) {
                    ropeSkipStatisticProvider.d = (Bitmap) obj;
                } else {
                    LogUtil.h("Track_Provider_Track_RopeSkipStatisticProvider", "loadMusicCallback objData is error!");
                    ropeSkipStatisticProvider.d = null;
                }
                if (ropeSkipStatisticProvider.k != null) {
                    LogUtil.a("Track_Provider_Track_RopeSkipStatisticProvider", "refresh icon");
                    ropeSkipStatisticProvider.k.e(ropeSkipStatisticProvider.f);
                    return;
                }
                return;
            }
            LogUtil.b("Track_Provider_Track_RopeSkipStatisticProvider", "loadMusicCallback error, ropeProvider is null.");
        }
    }

    private List<Integer> g() {
        ArrayList arrayList = new ArrayList(4);
        Integer valueOf = Integer.valueOf(R.drawable._2131430573_res_0x7f0b0cad);
        arrayList.add(valueOf);
        arrayList.add(valueOf);
        arrayList.add(Integer.valueOf(R.drawable._2131430458_res_0x7f0b0c3a));
        arrayList.add(Integer.valueOf(R.string._2130842049_res_0x7f0211c1));
        return arrayList;
    }

    private void e(HashMap<String, Object> hashMap, HiHealthData hiHealthData) {
        BigDecimal bigDecimal = new BigDecimal(0);
        Object obj = hiHealthData.get("TrackRepoJumpSum");
        if (obj != null) {
            bigDecimal = new BigDecimal(((Double) obj).doubleValue());
        }
        LogUtil.c("Track_Provider_Track_RopeSkipStatisticProvider", "TrackRepoJumpSum = ", Integer.valueOf(bigDecimal.intValue()));
        hashMap.put("ACCUMULATED_DURATION", Integer.valueOf(bigDecimal.intValue()));
        hashMap.put("ACCUMULATED_DURATION_TEXT", BaseApplication.getContext().getResources().getString(R.string._2130844980_res_0x7f021d34));
    }

    private void a(Context context, Map<String, Object> map) {
        if (!LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            this.t = owp.e(this.e, 283);
            int a2 = (int) owp.a(this.e, 283);
            this.r = a2;
            int i = this.t;
            String str = "";
            if (i == 5) {
                str = UnitUtil.e(this.r, 1, 0) + this.e.getResources().getQuantityString(R.plurals._2130903274_res_0x7f0300ea, this.r, "");
            } else if (i == 0) {
                str = UnitUtil.a(a2);
            }
            String a3 = this.o.a(this.t);
            LogUtil.a("Track_Provider_Track_RopeSkipStatisticProvider", "setTargetStyleAndValue() targetTypeStr = ", a3, ", targetValueStr = ", str);
            if (!TextUtils.isEmpty(str)) {
                if (LanguageUtil.b(BaseApplication.getContext())) {
                    a3 = a3 + ": " + str;
                } else {
                    a3 = a3 + " " + str;
                }
            }
            map.put("IS_GOAL_BUTTON_SHOW", true);
            map.put("GOAL_BUTTON", a3);
            return;
        }
        map.put("IS_GOAL_BUTTON_SHOW", false);
    }

    private void a(final Context context, HashMap<String, Object> hashMap) {
        hashMap.put("CLICK_EVENT_LISTENER", new OnClickSectionListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.RopeSkipStatisticProvider.5
            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, int i2) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(int i, String str) {
            }

            @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
            public void onClick(String str) {
                if (nsn.a(500)) {
                    LogUtil.a("Track_Provider_Track_RopeSkipStatisticProvider", "onClick isFastClick");
                    return;
                }
                if ("ACCUMULATED_DURATION_CLICK_VIEW".equals(str)) {
                    RopeSkipStatisticProvider.this.b(context);
                    return;
                }
                if ("LEFT_IMAGEVIEW".equals(str)) {
                    LogUtil.a("Track_Provider_Track_RopeSkipStatisticProvider", "LEFT_IMAGEVIEW on click");
                    gie.a(RopeSkipStatisticProvider.this.e, 283, RopeSkipStatisticProvider.this.t, RopeSkipStatisticProvider.this.r);
                    return;
                }
                if ("RIGHT_IMAGEVIEW".equals(str)) {
                    LogUtil.a("Track_Provider_Track_RopeSkipStatisticProvider", "RIGHT_IMAGEVIEW on click");
                    RopeSkipStatisticProvider.this.j();
                    return;
                }
                if ("MIDDLE_IMAGEVIEW".equals(str)) {
                    LogUtil.c("Track_Provider_Track_RopeSkipStatisticProvider", "MIDDLE_IMAGEVIEW on click");
                    RopeSkipStatisticProvider.this.h();
                } else if ("GOAL_BUTTON".equals(str)) {
                    LogUtil.c("Track_Provider_Track_RopeSkipStatisticProvider", "GOAL_BUTTON on click");
                    RopeSkipStatisticProvider.this.o.dfR_(RopeSkipStatisticProvider.this.b(), RopeSkipStatisticProvider.this.m);
                } else if ("RIGHT_TOP_IMAGE".equals(str)) {
                    RopeSkipStatisticProvider.this.o();
                }
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context) {
        if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            LoginInit.getInstance(BaseApplication.getContext()).browsingToLogin(new IBaseResponseCallback() { // from class: oqp
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    RopeSkipStatisticProvider.d(i, obj);
                }
            }, "");
            return;
        }
        FitnessSessionManager.SessionActivityAction c2 = FitnessSessionManager.e().c();
        if (c2 == null) {
            LogUtil.h("Track_Provider_Track_RopeSkipStatisticProvider", "FitnessSessionManager getSessionForActivityAction null");
        } else {
            c2.startSportHistoryActivity(context, 283, b());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o() {
        LoginInit.getInstance(this.e).browsingToLogin(new IBaseResponseCallback() { // from class: oqw
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                RopeSkipStatisticProvider.this.e(i, obj);
            }
        }, "");
    }

    public /* synthetic */ void e(int i, Object obj) {
        if (i == 0) {
            i();
        } else {
            LogUtil.h("Track_Provider_Track_RopeSkipStatisticProvider", "errorCode is not success", Integer.valueOf(i));
        }
    }

    private void i() {
        if (rud.e()) {
            BrowsingBiEvent browsingBiEvent = LoginInit.getBrowsingBiEvent();
            if (browsingBiEvent != null) {
                browsingBiEvent.showFullServiceDialog(BaseApplication.getContext());
                return;
            }
            return;
        }
        ArrayList<ContentValues> d2 = cjx.e().d(HealthDevice.HealthDeviceKind.HDK_ROPE_SKIPPING);
        if (koq.b(d2)) {
            if (this.e == null) {
                LogUtil.h("Track_Provider_Track_RopeSkipStatisticProvider", "handleDeviceNotConnected: context is null");
                return;
            }
            SectionBean sectionBean = this.k;
            if (sectionBean != null) {
                dky.d(sectionBean.b(), true);
                return;
            }
            return;
        }
        if (d2.size() > 1) {
            d(d2);
        } else {
            dfI_(d2.get(0));
        }
    }

    private void dfI_(ContentValues contentValues) {
        if (cei.b().isRopeDeviceBtConnected()) {
            dfJ_(contentValues);
            return;
        }
        String asString = contentValues.getAsString("productId");
        String asString2 = contentValues.getAsString("uniqueId");
        dcz d2 = ResourceManager.e().d(asString);
        if (d2 == null) {
            LogUtil.h("Track_Provider_Track_RopeSkipStatisticProvider", "handleDeviceNotConnected: productInfo is null");
            return;
        }
        dcz.e m = d2.m();
        if (m == null) {
            LogUtil.h("Track_Provider_Track_RopeSkipStatisticProvider", "handleDeviceNotConnected: introduction is null");
            return;
        }
        if (BleConstants.BLE_THIRD_DEVICE_H5.equals(m.d())) {
            Intent Wx_ = dks.Wx_(d2, asString, asString2);
            if (Wx_ != null) {
                gnm.aPB_(this.e, Wx_);
                return;
            }
            return;
        }
        ckw.a().d(this.e, asString, asString2);
    }

    private void d(ArrayList<ContentValues> arrayList) {
        if (cei.b().isRopeDeviceBtConnected()) {
            String currentMacAddress = cei.b().getCurrentMacAddress();
            Iterator<ContentValues> it = arrayList.iterator();
            while (it.hasNext()) {
                ContentValues next = it.next();
                if (TextUtils.equals(next.getAsString("uniqueId"), currentMacAddress)) {
                    dfJ_(next);
                    return;
                }
            }
            return;
        }
        ckw.a().a(this.e, arrayList);
    }

    private void dfJ_(ContentValues contentValues) {
        if (contentValues == null) {
            LogUtil.b("Track_Provider_Track_RopeSkipStatisticProvider", "jumpToDeviceManager deviceInfo is null.");
            return;
        }
        String asString = contentValues.getAsString("productId");
        String asString2 = contentValues.getAsString("uniqueId");
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        intent.setAction("SWITCH_PLUGINDEVICE");
        bundle.putString("arg1", "DeviceInfoList");
        bundle.putString("productId", asString);
        bundle.putString("uniqueId", asString2);
        intent.setPackage(ofr.d);
        intent.setClassName(ofr.d, "com.huawei.health.device.ui.DeviceMainActivity");
        bundle.putParcelable("commonDeviceInfo", contentValues);
        intent.putExtras(bundle);
        gnm.aPB_(this.e, intent);
    }

    private boolean m() {
        return Utils.d() == 1;
    }

    private void a() {
        AudioResProviderInterface audioResProviderInterface = (AudioResProviderInterface) AppRouter.e(AudioResProviderInterface.ROUTER_PATH_AUDIO_RES_DOWNLOAD, AudioResProviderInterface.class);
        boolean c2 = mxb.a().c(this.e);
        Object[] objArr = new Object[4];
        objArr[0] = "service = ";
        objArr[1] = Boolean.valueOf(audioResProviderInterface != null);
        objArr[2] = "isEnableCurLang = ";
        objArr[3] = Boolean.valueOf(c2);
        LogUtil.a("Track_Provider_Track_RopeSkipStatisticProvider", objArr);
        if (audioResProviderInterface != null && c2) {
            audioResProviderInterface.queryOrDownAudioResource(BaseApplication.getActivity(), mxc.a(this.e, "Sport"), this.e.getResources().getString(R$string.IDS_hwh_base_model_multilingualaudio), new AudioResDownloadListener() { // from class: oqt
                @Override // com.huawei.pluginsport.multilingualaudio.AudioResDownloadListener
                public final void onResult(int i) {
                    RopeSkipStatisticProvider.this.e(i);
                }
            });
        } else {
            this.n.sendEmptyMessage(101);
        }
    }

    public /* synthetic */ void e(int i) {
        LogUtil.a("Track_Provider_Track_RopeSkipStatisticProvider", "result = ", Integer.valueOf(i));
        this.n.sendEmptyMessage(101);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        if (!m()) {
            this.f9548a = 7;
            e();
        } else if (gso.e().o()) {
            this.f9548a = 5;
            e();
        } else if (SportSupportUtil.h()) {
            this.f9548a = 7;
            e();
        } else {
            t();
        }
    }

    private void t() {
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.e);
        builder.b(R.string._2130844950_res_0x7f021d16).d(R.string._2130844951_res_0x7f021d17).cyU_(R.string._2130844952_res_0x7f021d18, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.RopeSkipStatisticProvider.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                RopeSkipStatisticProvider.this.o();
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cyR_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.RopeSkipStatisticProvider.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.a().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        LoginInit.getInstance(this.e).browsingToLogin(new IBaseResponseCallback() { // from class: oqu
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                RopeSkipStatisticProvider.this.a(i, obj);
            }
        }, "");
    }

    public /* synthetic */ void a(int i, Object obj) {
        if (i == 0) {
            c();
        } else {
            LogUtil.h("Track_Provider_Track_RopeSkipStatisticProvider", "errorCode is not success", Integer.valueOf(i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j() {
        ixx.d().d(this.e, AnalyticsValue.MOTION_TRACK_1040014.value(), this.s.e(new HashMap(5), 283), 0);
    }

    private void c() {
        ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).checkUserInfo(this.e, new IBaseResponseCallback() { // from class: oqv
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i, Object obj) {
                RopeSkipStatisticProvider.this.c(i, obj);
            }
        });
    }

    public /* synthetic */ void c(int i, Object obj) {
        if (i != 0) {
            a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2, boolean z, boolean z2) {
        if (z) {
            owp.c(this.e, 283, i);
            float f = i2;
            owp.c(this.e, f, 283);
            owp.e(this.e, 283, z2);
            if (z2) {
                owp.b(this.e, 283, i, f);
            } else {
                owp.b(this.e, 283, i, -1.0f);
            }
        }
    }

    private void e() {
        StorageParams storageParams = new StorageParams();
        String num = Integer.toString(20002);
        String b2 = SharedPreferenceManager.b(this.e, num, "map_tracking_sport_type_sportting");
        ReleaseLogUtil.e("Track_Provider_Track_RopeSkipStatisticProvider", "checkAndStartSport() typeStr = ", b2);
        if (!TextUtils.isEmpty(b2)) {
            SharedPreferenceManager.e(this.e, num, "map_tracking_sport_type_sportting", String.valueOf(283), storageParams);
        }
        if (this.f9548a == 5) {
            p();
        }
        boolean equals = "true".equals(ash.b("ROPE_DISPLAY_PROCESS"));
        if (this.f9548a == 5 && !equals) {
            gso.e().c(283, this.t, this.r);
        } else {
            gso.e().a(this.e, 283, this.t, this.r);
        }
    }

    private void p() {
        String productId = cei.b().getProductId();
        dcz d2 = ResourceManager.e().d(productId);
        if (d2 == null) {
            LogUtil.a("Track_Provider_Track_RopeSkipStatisticProvider", "saveDeviceName productInfo is null");
            return;
        }
        String d3 = dcx.d(productId, d2.n().b());
        SharedPreferences.Editor edit = BaseApplication.getContext().getSharedPreferences(PluginPayAdapter.KEY_DEVICE_INFO_NAME, 0).edit();
        if (edit != null) {
            edit.putString("device_name_key", d3);
            edit.apply();
        }
    }

    static class h implements IBaseResponseCallback {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<RopeSkipStatisticProvider> f9554a;

        h(RopeSkipStatisticProvider ropeSkipStatisticProvider) {
            this.f9554a = new WeakReference<>(ropeSkipStatisticProvider);
        }

        @Override // com.huawei.hwbasemgr.IBaseResponseCallback
        /* renamed from: onResponse */
        public void d(int i, Object obj) {
            RopeSkipStatisticProvider ropeSkipStatisticProvider = this.f9554a.get();
            if (ropeSkipStatisticProvider == null) {
                return;
            }
            LogUtil.a("Track_Provider_Track_RopeSkipStatisticProvider", "onResponse ", obj);
            HiHealthData hiHealthData = new HiHealthData();
            if (koq.e(obj, HiHealthData.class)) {
                List list = (List) obj;
                if (list.size() <= 0) {
                    LogUtil.h("Track_Provider_Track_RopeSkipStatisticProvider", "data size =", Integer.valueOf(list.size()));
                } else {
                    hiHealthData = (HiHealthData) list.get(0);
                }
            } else {
                LogUtil.h("Track_Provider_Track_RopeSkipStatisticProvider", "class type is wrong");
            }
            ropeSkipStatisticProvider.b(hiHealthData);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(HiHealthData hiHealthData) {
        if (LoginInit.getInstance(BaseApplication.getContext()).isBrowseMode()) {
            this.f = new HiHealthData();
        } else if (hiHealthData != null) {
            this.f = hiHealthData.copyData();
        }
        if (s()) {
            LogUtil.a("Track_Provider_Track_RopeSkipStatisticProvider", "update ui");
            this.k.e(this.f);
        }
    }

    private boolean s() {
        if (this.f == null) {
            return false;
        }
        BigDecimal bigDecimal = new BigDecimal(0);
        Object obj = this.f.get("TrackRepoJumpSum");
        if (obj != null) {
            bigDecimal = new BigDecimal(((Double) obj).doubleValue());
        }
        String a2 = cau.a(String.valueOf(283));
        if ((!TextUtils.isEmpty(a2) ? CommonUtils.a(a2) : 0.0d) == bigDecimal.doubleValue()) {
            LogUtil.a("Track_Provider_Track_RopeSkipStatisticProvider", "same rope cache");
            return false;
        }
        LogUtil.a("Track_Provider_Track_RopeSkipStatisticProvider", "refresh rope cache");
        cau.d(String.valueOf(283), String.valueOf(bigDecimal.doubleValue()));
        return true;
    }

    static class c extends BaseHandler<RopeSkipStatisticProvider> {
        c(Looper looper, RopeSkipStatisticProvider ropeSkipStatisticProvider) {
            super(looper, ropeSkipStatisticProvider);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: dfK_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(RopeSkipStatisticProvider ropeSkipStatisticProvider, Message message) {
            if (message.what != 101) {
                return;
            }
            ropeSkipStatisticProvider.n();
        }
    }

    private void d() {
        String b2 = ash.b("rope_background_image_save_time");
        if (TextUtils.isEmpty(b2)) {
            r();
            return;
        }
        if (System.currentTimeMillis() - CommonUtils.g(b2) < 3600000) {
            this.c = ash.b("rope_background_image_url");
            this.b = ash.b("rope_background_image_url_tahiti");
            if (TextUtils.isEmpty(this.c) || TextUtils.isEmpty(this.b)) {
                r();
                return;
            }
            return;
        }
        r();
    }

    private void r() {
        LogUtil.a("Track_Provider_Track_RopeSkipStatisticProvider", "requestBackgroundImage()");
        final MarketingApi marketingApi = (MarketingApi) Services.a("FeatureMarketing", MarketingApi.class);
        if (marketingApi != null) {
            marketingApi.getResourceResultInfo(4019).addOnSuccessListener(new OnSuccessListener<Map<Integer, ResourceResultInfo>>() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.RopeSkipStatisticProvider.4
                @Override // com.huawei.hmf.tasks.OnSuccessListener
                /* renamed from: b, reason: merged with bridge method [inline-methods] */
                public void onSuccess(Map<Integer, ResourceResultInfo> map) {
                    Map<Integer, ResourceResultInfo> filterMarketingRules = marketingApi.filterMarketingRules(map);
                    if (filterMarketingRules == null) {
                        LogUtil.h("Track_Provider_Track_RopeSkipStatisticProvider", "requestBackgroundImage() filterResult is null");
                        return;
                    }
                    LogUtil.a("Track_Provider_Track_RopeSkipStatisticProvider", "on success");
                    ResourceResultInfo resourceResultInfo = filterMarketingRules.get(4019);
                    if (resourceResultInfo == null) {
                        LogUtil.h("Track_Provider_Track_RopeSkipStatisticProvider", "requestBackgroundImage() resultInfo is null");
                    } else {
                        RopeSkipStatisticProvider.this.e(resourceResultInfo.getResources());
                    }
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(List<ResourceBriefInfo> list) {
        if (koq.c(list)) {
            Collections.sort(list, new Comparator<ResourceBriefInfo>() { // from class: com.huawei.ui.homehealth.runcard.trackfragments.RopeSkipStatisticProvider.6
                @Override // java.util.Comparator
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public int compare(ResourceBriefInfo resourceBriefInfo, ResourceBriefInfo resourceBriefInfo2) {
                    if (resourceBriefInfo == null || resourceBriefInfo2 == null) {
                        return -1;
                    }
                    return resourceBriefInfo2.getPriority() - resourceBriefInfo.getPriority();
                }
            });
            for (ResourceBriefInfo resourceBriefInfo : list) {
                if (resourceBriefInfo.getContentType() == 31) {
                    RopeBackgroundTemplate ropeBackgroundTemplate = (RopeBackgroundTemplate) HiJsonUtil.e(resourceBriefInfo.getContent().getContent(), RopeBackgroundTemplate.class);
                    if (ropeBackgroundTemplate == null) {
                        LogUtil.h("Track_Provider_Track_RopeSkipStatisticProvider", "requestBackgroundImage() backgroundTemplate is null");
                    } else {
                        d(ropeBackgroundTemplate.getPicture(), ropeBackgroundTemplate.getTahitiPicture());
                        return;
                    }
                }
            }
            ReleaseLogUtil.c("Track_Provider_Track_RopeSkipStatisticProvider", "requestBackgroundImage() cannot find background image");
        }
    }

    private void d(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        if (!TextUtils.isEmpty(this.c) && !TextUtils.isEmpty(this.b)) {
            if (this.c.equals(str) && this.b.equals(str2)) {
                ash.a("rope_background_image_save_time", String.valueOf(System.currentTimeMillis()));
                return;
            } else {
                b(str, str2);
                return;
            }
        }
        b(str, str2);
    }

    private void b(String str, String str2) {
        this.c = str;
        this.b = str2;
        LogUtil.a("Track_Provider_Track_RopeSkipStatisticProvider", "saveImageDataAndUpdateUi() mBackgroundImageUrl: ", str, ", mBackgroundImageUrlTahiti: ", str2);
        ash.a("rope_background_image_url", this.c);
        ash.a("rope_background_image_url_tahiti", this.b);
        ash.a("rope_background_image_save_time", String.valueOf(System.currentTimeMillis()));
        SectionBean sectionBean = this.k;
        if (sectionBean != null) {
            sectionBean.e(this.f);
        }
    }

    static class b implements HiAggregateListener {
        WeakReference<RopeSkipStatisticProvider> e;

        b(RopeSkipStatisticProvider ropeSkipStatisticProvider) {
            this.e = new WeakReference<>(ropeSkipStatisticProvider);
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResult(List<HiHealthData> list, int i, int i2) {
            RopeSkipStatisticProvider ropeSkipStatisticProvider = this.e.get();
            if (ropeSkipStatisticProvider != null) {
                h hVar = ropeSkipStatisticProvider.p;
                if (hVar == null) {
                    LogUtil.h("Track_Provider_Track_RopeSkipStatisticProvider", "requestTrackStatData callback is null");
                    return;
                } else {
                    hVar.d(i, list);
                    return;
                }
            }
            LogUtil.h("Track_Provider_Track_RopeSkipStatisticProvider", "onResult: ropeSkipStatisticProvider is null");
        }

        @Override // com.huawei.hihealth.data.listener.HiAggregateListener
        public void onResultIntent(int i, List<HiHealthData> list, int i2, int i3) {
            LogUtil.a("Track_Provider_Track_RopeSkipStatisticProvider", "onResultIntent");
        }
    }

    static class d implements MessageOrStateCallback {

        /* renamed from: a, reason: collision with root package name */
        WeakReference<RopeSkipStatisticProvider> f9553a;

        d(RopeSkipStatisticProvider ropeSkipStatisticProvider) {
            this.f9553a = new WeakReference<>(ropeSkipStatisticProvider);
        }

        @Override // com.huawei.btsportdevice.callback.MessageOrStateCallback
        public void onNewMessage(int i, Bundle bundle) {
            LogUtil.a("Track_Provider_Track_RopeSkipStatisticProvider", "onNewMessage");
        }

        @Override // com.huawei.btsportdevice.callback.MessageOrStateCallback
        public void onStateChange(String str) {
            LogUtil.a("Track_Provider_Track_RopeSkipStatisticProvider", "onStateChange: ", str);
            RopeSkipStatisticProvider ropeSkipStatisticProvider = this.f9553a.get();
            if (ropeSkipStatisticProvider != null) {
                if (ropeSkipStatisticProvider.k == null || ropeSkipStatisticProvider.f == null) {
                    return;
                }
                ropeSkipStatisticProvider.k.e(ropeSkipStatisticProvider.f);
                return;
            }
            LogUtil.h("Track_Provider_Track_RopeSkipStatisticProvider", "onStateChange: ropeSkipStatisticProvider is null");
        }
    }

    static class a implements HiSubscribeListener {
        WeakReference<RopeSkipStatisticProvider> e;

        a(RopeSkipStatisticProvider ropeSkipStatisticProvider) {
            this.e = new WeakReference<>(ropeSkipStatisticProvider);
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onResult(List<Integer> list, List<Integer> list2) {
            RopeSkipStatisticProvider ropeSkipStatisticProvider = this.e.get();
            if (ropeSkipStatisticProvider == null) {
                LogUtil.h("Track_Provider_Track_RopeSkipStatisticProvider", "onResult: ropeSkipStatisticProvider is null");
            } else if (koq.c(list)) {
                LogUtil.a("Track_Provider_Track_RopeSkipStatisticProvider", "registerReportDataListener success");
                ropeSkipStatisticProvider.l = list;
            }
        }

        @Override // com.huawei.hihealth.data.listener.HiSubscribeListener
        public void onChange(int i, HiHealthClient hiHealthClient, String str, HiHealthData hiHealthData, long j) {
            LogUtil.a("Track_Provider_Track_RopeSkipStatisticProvider", "onChange", Integer.valueOf(i));
            RopeSkipStatisticProvider ropeSkipStatisticProvider = this.e.get();
            if (ropeSkipStatisticProvider != null) {
                ropeSkipStatisticProvider.l();
            } else {
                LogUtil.h("Track_Provider_Track_RopeSkipStatisticProvider", "onChange: ropeSkipStatisticProvider is null");
            }
        }
    }
}
