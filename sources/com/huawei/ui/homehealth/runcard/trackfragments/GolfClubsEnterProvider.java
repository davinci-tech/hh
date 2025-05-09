package com.huawei.ui.homehealth.runcard.trackfragments;

import android.content.Context;
import android.view.View;
import com.huawei.health.R;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.knit.data.BaseKnitDataProvider;
import com.huawei.health.knit.section.model.SectionBean;
import com.huawei.healthcloud.plugintrack.golf.device.GolfDeviceProxy;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import com.huawei.pluginfitnessadvice.h5pro.SeriesCourseH5Repository;
import com.huawei.ui.commonui.listener.OnClickSectionListener;
import com.huawei.ui.homehealth.runcard.trackfragments.GolfClubsEnterProvider;
import defpackage.bzs;
import defpackage.bzw;
import defpackage.nrh;
import defpackage.nsn;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.HashMap;

/* loaded from: classes9.dex */
public class GolfClubsEnterProvider extends BaseKnitDataProvider<Integer> {
    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public boolean isActive(Context context) {
        return true;
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    public void loadDefaultData(SectionBean sectionBean) {
        sectionBean.e((Object) 0);
    }

    /* renamed from: com.huawei.ui.homehealth.runcard.trackfragments.GolfClubsEnterProvider$5, reason: invalid class name */
    public class AnonymousClass5 implements OnClickSectionListener {
        final /* synthetic */ Context c;

        public static /* synthetic */ void c(int i, Object obj) {
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

        AnonymousClass5(Context context) {
            this.c = context;
        }

        @Override // com.huawei.ui.commonui.listener.OnClickSectionListener
        public void onClick(String str) {
            if (nsn.a(500)) {
                LogUtil.a("Track_Provider_Track_GolfClubsEnterProvider", "onClick isFastClick");
                return;
            }
            if (LoginInit.getInstance(this.c).isBrowseMode()) {
                LoginInit.getInstance(this.c).browsingToLogin(new IBaseResponseCallback() { // from class: oqq
                    @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                    /* renamed from: onResponse */
                    public final void d(int i, Object obj) {
                        GolfClubsEnterProvider.AnonymousClass5.c(i, obj);
                    }
                }, "");
                return;
            }
            if ("TITLE_SHOW_MORE_CLICK_EVENT".equals(str)) {
                if (GolfDeviceProxy.getInstance().isSupportGolfClub()) {
                    GolfClubsEnterProvider.this.b(this.c);
                } else if (GolfDeviceProxy.getInstance().isDeviceConnected()) {
                    nrh.e(this.c, R.string._2130839688_res_0x7f020888);
                } else {
                    nrh.e(this.c, R.string._2130843052_res_0x7f0215ac);
                }
            }
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    @Override // com.huawei.health.knit.data.BaseKnitDataProvider, com.huawei.health.knit.data.KnitDataProvider
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public void parseParams(Context context, HashMap<String, Object> hashMap, Integer num) {
        super.parseParams(context, hashMap, num);
        hashMap.put("CLICK_EVENT_LISTENER", new AnonymousClass5(context));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(Context context) {
        bzs.e().initH5Pro();
        bzs e = bzs.e();
        H5ProLaunchOption.Builder showStatusBar = new H5ProLaunchOption.Builder().addPath("#/clubData").setImmerse().showStatusBar();
        showStatusBar.addCustomizeJsModule(RemoteMessageConst.NOTIFICATION, bzs.e().getCommonJsModule(RemoteMessageConst.NOTIFICATION));
        showStatusBar.addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi"));
        showStatusBar.addCustomizeJsModule("achievement", bzw.e().getCommonJsModule("achievement"));
        SeriesCourseH5Repository.registerService();
        e.loadH5ProApp(context, "com.huawei.health.h5.golf", showStatusBar);
        ReleaseLogUtil.e(getLogTag(), "gotoGolfClubInfoH5View success");
    }
}
