package com.huawei.ui.homehealth.runcard.trackfragments;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import com.huawei.health.R;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.healthcloud.plugintrack.golf.bean.GolfCourseData;
import com.huawei.healthcloud.plugintrack.golf.device.GolfDataCallback;
import com.huawei.healthcloud.plugintrack.golf.device.GolfDeviceProxy;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.homehealth.interactors.GolfUpdateMapInteractor;
import com.huawei.ui.homehealth.runcard.trackfragments.GolfUpdateReminderProvider;
import defpackage.cau;
import defpackage.ixx;
import defpackage.nsn;
import health.compact.a.CommonUtils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes9.dex */
public class GolfUpdateReminderProvider extends BaseKnitDataProvider<Integer> {
    private SectionBean b;
    private int c;

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        ReleaseLogUtil.e("Track_Provider_GolfUpdateProvider", "loadDefaultData");
        if (GolfDeviceProxy.getInstance().isDeviceConnected() && GolfDeviceProxy.getInstance().isSupportGolf()) {
            this.c = CommonUtils.e(cau.a("count"), 0);
        } else {
            this.c = 0;
        }
        this.b = sectionBean;
        sectionBean.e(Integer.valueOf(this.c));
        setIsActive(this.c > 0);
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: loadData */
    public void e(Context context, SectionBean sectionBean) {
        if (GolfDeviceProxy.getInstance().isDeviceConnected() && GolfDeviceProxy.getInstance().isSupportGolf()) {
            GolfDeviceProxy.getInstance().getDeviceDownloadedCoursesForNative(new a(this, null));
            LogUtil.a("Track_Provider_GolfUpdateProvider", "loadData(): request course data by device.");
        } else if (this.c != 0) {
            this.c = 0;
            setIsActive(false);
            cau.d("count", String.valueOf(this.c));
            this.b.e(Integer.valueOf(this.c));
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDataLite(Context context) {
        if (GolfDeviceProxy.getInstance().isDeviceConnected() && GolfDeviceProxy.getInstance().isSupportGolf()) {
            GolfDeviceProxy.getInstance().getDeviceDownloadedCoursesForNative(new a(this, null));
            LogUtil.a("Track_Provider_GolfUpdateProvider", "loadDataLite(): request course data by device.");
        } else {
            this.c = 0;
        }
        setIsActive(this.c > 0);
        this.b.e(Integer.valueOf(this.c));
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, Integer num) {
        if (hashMap == null || num == null) {
            LogUtil.h("Track_Provider_GolfUpdateProvider", "setData viewMap or data is null");
            return;
        }
        Resources resources = BaseApplication.getContext().getResources();
        int i = this.c;
        hashMap.put("LEFT_TEXT", resources.getQuantityString(R.plurals._2130903328_res_0x7f030120, i, Integer.valueOf(i)));
        hashMap.put("RIGHT_BUTTON", resources.getString(R.string._2130845482_res_0x7f021f2a));
        a(context, hashMap);
    }

    /* renamed from: com.huawei.ui.homehealth.runcard.trackfragments.GolfUpdateReminderProvider$2, reason: invalid class name */
    public class AnonymousClass2 implements OnClickSectionListener {
        final /* synthetic */ Context d;

        public static /* synthetic */ void e(int i, Object obj) {
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i) {
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i, int i2) {
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(int i, String str) {
        }

        AnonymousClass2(Context context) {
            this.d = context;
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(String str) {
            if (nsn.a(500)) {
                LogUtil.a("Track_Provider_GolfUpdateProvider", "onClick isFastClick");
                return;
            }
            if (LoginInit.getInstance(this.d).isBrowseMode()) {
                LoginInit.getInstance(this.d).browsingToLogin(new IBaseResponseCallback() { // from class: oqs
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj) {
                        GolfUpdateReminderProvider.AnonymousClass2.e(i, obj);
                    }
                }, "");
                return;
            }
            if ("CLICK_VIEW".equals(str)) {
                LogUtil.a("Track_Provider_GolfUpdateProvider", "jump to H5 golf courseMap");
                GolfUpdateMapInteractor.e(this.d, "0");
            } else if ("ITEM_BUTTON_TEXT".equals(str)) {
                LogUtil.a("Track_Provider_GolfUpdateProvider", "jump to H5 golf courseMap, and update all");
                GolfUpdateMapInteractor.e(this.d, "1");
                HashMap hashMap = new HashMap();
                hashMap.put("click", 1);
                hashMap.put("updateMessage", Integer.valueOf(GolfUpdateReminderProvider.this.c));
                hashMap.put("type", 1);
                ixx.d().d(BaseApplication.getContext(), AnalyticsValue.GOLF_COURSE_MAP_CLICK_EVENT.value(), hashMap, 0);
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    private void a(Context context, HashMap<String, Object> hashMap) {
        hashMap.put("CLICK_EVENT_LISTENER", new AnonymousClass2(context));
    }

    public class a implements GolfDataCallback {
        private a() {
        }

        /* synthetic */ a(GolfUpdateReminderProvider golfUpdateReminderProvider, AnonymousClass2 anonymousClass2) {
            this();
        }

        @Override // com.huawei.healthcloud.plugintrack.golf.device.GolfDataCallback
        public void onReceived(final List<GolfCourseData> list) {
            LogUtil.a("Track_Provider_GolfUpdateProvider", "CourseDataUpdateListener: onReceived(), course data list size is ", Integer.valueOf(list.size()));
            new Thread(new Runnable() { // from class: oqo
                @Override // java.lang.Runnable
                public final void run() {
                    GolfUpdateReminderProvider.a.this.c(list);
                }
            }).start();
        }

        public /* synthetic */ void c(List list) {
            GolfUpdateReminderProvider.this.c = GolfUpdateMapInteractor.b(list, GolfUpdateMapInteractor.a(list)).size();
            GolfUpdateReminderProvider golfUpdateReminderProvider = GolfUpdateReminderProvider.this;
            golfUpdateReminderProvider.setIsActive(golfUpdateReminderProvider.c > 0);
            GolfUpdateReminderProvider.this.b.e(Integer.valueOf(GolfUpdateReminderProvider.this.c));
        }
    }
}
