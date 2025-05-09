package com.huawei.healthcloud.plugintrack.util;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.huawei.basefitnessadvice.api.BaseSportModelApi;
import com.huawei.basefitnessadvice.callback.DeviceCallback;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.os.FileUtils;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.health.algorithm.api.TrackFeatureExtractionApi;
import com.huawei.health.basesport.wearengine.SportHiWearBusinessType;
import com.huawei.health.device.api.OfflineMapApi;
import com.huawei.health.device.callback.DownloadCityCallback;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.h5pro.H5ProClient;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.h5pro.utils.EnvironmentHelper;
import com.huawei.health.userprofilemgr.api.UserProfileMgrApi;
import com.huawei.health.userprofilemgr.model.RouteResultCallBack;
import com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteUtils;
import com.huawei.healthcloud.plugintrack.ui.activity.ClockingRankActivity;
import com.huawei.healthcloud.plugintrack.util.RouteTransferDataHolder;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.network.httpclient.util.PreConnectManager;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.RouteData;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ProgressListener;
import com.huawei.route.RouteOutputForApp;
import com.huawei.route.RouteTrackInfo;
import com.huawei.route.RouteTrackPoint;
import com.huawei.route.RouteType;
import com.huawei.ui.commonui.dialog.CustomProgressDialog;
import com.huawei.ui.commonui.dialog.CustomTextAlertDialog;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.bzs;
import defpackage.cun;
import defpackage.cwi;
import defpackage.fbb;
import defpackage.gzh;
import defpackage.gzi;
import defpackage.hjd;
import defpackage.ixx;
import defpackage.knl;
import defpackage.koq;
import defpackage.lqg;
import defpackage.lqi;
import defpackage.nrh;
import defpackage.nsf;
import defpackage.sqd;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.LogAnonymous;
import health.compact.a.Services;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/* loaded from: classes4.dex */
public class RouteTransferDataHolder {

    /* renamed from: a, reason: collision with root package name */
    private CustomTextAlertDialog f3829a;
    private CustomViewDialog b;
    private CustomViewDialog c;
    private final Context d;
    private boolean f;
    private String g;
    private String h;
    private boolean j;
    private CustomProgressDialog k;
    private RouteTrackInfo l;
    private RouteData m;
    private double n;
    private RouteOutputForApp o;
    private CustomTextAlertDialog p;
    private CustomProgressDialog.Builder r;
    private CustomTextAlertDialog s;
    private final a e = new a(this);
    private final BaseSportModelApi i = (BaseSportModelApi) Services.a("BaseSportModel", BaseSportModelApi.class);

    public RouteTransferDataHolder(Context context) {
        this.d = context;
    }

    public void c(String str, RouteData routeData) {
        if (str == null) {
            return;
        }
        this.m = routeData;
        this.n = routeData.getRouteDistance();
        RouteTrackInfo convertRouteTrackInfo = RouteTrackInfo.convertRouteTrackInfo(routeData);
        this.l = convertRouteTrackInfo;
        convertRouteTrackInfo.setTrackName(c(str));
        RouteTrackInfo routeTrackInfo = this.l;
        routeTrackInfo.setTrackId(knl.e(routeTrackInfo.getTrackName()));
    }

    public RouteTrackInfo g() {
        return this.l;
    }

    public void a(String str) {
        this.h = str;
    }

    public void j() {
        RouteTrackInfo routeTrackInfo;
        TrackFeatureExtractionApi trackFeatureExtractionApi = (TrackFeatureExtractionApi) Services.a("TrackFeatureExtractionAlgorithmService", TrackFeatureExtractionApi.class);
        if (trackFeatureExtractionApi == null || (routeTrackInfo = this.l) == null) {
            return;
        }
        try {
            this.o = trackFeatureExtractionApi.getTrackOutputForApp(routeTrackInfo, 3000, this.n, false);
        } catch (RuntimeException e) {
            ReleaseLogUtil.c("Track_RouteTransferUtils", LogAnonymous.b((Throwable) e));
        }
    }

    public RouteOutputForApp h() {
        return this.o;
    }

    private String c(String str) {
        return (str.endsWith(".gpx") || str.endsWith(".tcx") || str.endsWith(".kml")) ? str.substring(0, str.lastIndexOf(".")) : str;
    }

    public void o() {
        LogUtil.a("Track_RouteTransferUtils", "sendToDevice");
        if (!d()) {
            b(-1);
            a(3, 0, gzi.a());
            return;
        }
        if (!a()) {
            b(0);
            a(3, 1, gzi.a());
        } else if (!d(this.l)) {
            nrh.b(this.d, R.string.IDS_track_route_draw_device_not_support);
        } else if (!i()) {
            u();
            a(3, 2, gzi.a());
        } else {
            b(this.l.getTrackName(), SportHiWearBusinessType.TRAJECTORY_HANDLE_SHAKE.getTypeValue());
        }
    }

    private boolean d(RouteTrackInfo routeTrackInfo) {
        if (routeTrackInfo.getTrackType() != RouteType.EXP.routeType()) {
            return true;
        }
        return c();
    }

    private void b(final String str, final int i) {
        a(i);
        fbb.e().c(str, i, new IBaseResponseCallback() { // from class: hpa
            @Override // com.huawei.hwbasemgr.IBaseResponseCallback
            /* renamed from: onResponse */
            public final void d(int i2, Object obj) {
                RouteTransferDataHolder.this.d(i, str, i2, obj);
            }
        });
        y();
    }

    public /* synthetic */ void d(int i, String str, int i2, Object obj) {
        ReleaseLogUtil.e("Track_RouteTransferUtils", "startTransferToDevice onResponse", Integer.valueOf(i2), " objData = ", obj);
        Message obtain = Message.obtain();
        obtain.what = 1;
        obtain.arg1 = i2;
        obtain.arg2 = i;
        obtain.obj = str;
        if (obj instanceof int[]) {
            Bundle bundle = new Bundle();
            bundle.putIntArray("pointMaxNum", (int[]) obj);
            obtain.setData(bundle);
        } else if (obj instanceof Integer) {
            obtain.arg1 = ((Integer) obj).intValue();
        }
        this.e.sendMessage(obtain);
        t();
    }

    private void a(int i) {
        if (SportHiWearBusinessType.TRAJECTORY_IMAGE.getTypeValue() == i) {
            this.j = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i, int i2) {
        if (SportHiWearBusinessType.TRAJECTORY_IMAGE_HANDLE_SHAKE.getTypeValue() == i) {
            this.j = i2 == 1;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        b(this.l.getTrackId(), SportHiWearBusinessType.TRAJECTORY_HANDLE_SHAKE.getTypeValue());
    }

    private void a(int i, int i2, String str) {
        if (TextUtils.isEmpty(str)) {
            LogUtil.h("Track_RouteTransferUtils", "routeId is empty, no bi");
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("event", Integer.valueOf(i));
        if (i2 != -1) {
            hashMap.put("failReason", Integer.valueOf(i2));
        }
        hashMap.put("routeID", str);
        ixx.d().d(BaseApplication.e(), "1040090", hashMap, 0);
    }

    public static boolean d() {
        LogUtil.a("Track_RouteTransferUtils", "isBindedDevice");
        return cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, "Track_RouteTransferUtils") != null;
    }

    public static boolean a() {
        LogUtil.a("Track_RouteTransferUtils", "isConnectDevice");
        return cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "Track_RouteTransferUtils") != null;
    }

    public static boolean c() {
        return cwi.c(cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, "Track_RouteTransferUtils"), 216);
    }

    public static boolean f() {
        return cwi.c(cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, "Track_RouteTransferUtils"), 218);
    }

    public static boolean i() {
        LogUtil.a("Track_RouteTransferUtils", "isSupportRouteDeliver");
        return cwi.c(cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, "Track_RouteTransferUtils"), 70);
    }

    public static boolean b() {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "Track_RouteTransferUtils");
        return cwi.c(deviceInfo, 201) && !(deviceInfo != null && deviceInfo.getProductType() == 99 && CommonUtil.bv());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        if (this.e.hasMessages(4)) {
            LogUtil.a("Track_RouteTransferUtils", "removeTimeOutMessage");
            this.e.removeMessages(4);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(String str, int i, int i2, int i3) {
        LogUtil.a("Track_RouteTransferUtils", "setErrorCodeResult", str, "errorCode", Integer.valueOf(i));
        if (i == 1) {
            if (i2 == SportHiWearBusinessType.TRAJECTORY_IMAGE_HANDLE_SHAKE.getTypeValue()) {
                r();
                return;
            } else {
                c(i3);
                return;
            }
        }
        if (i == 2) {
            m();
            Context context = this.d;
            nrh.c(context, context.getString(R.string._2130840122_res_0x7f020a3a));
            a(3, 5, gzi.a());
            d(4);
            return;
        }
        if (i == 3) {
            i(1);
            a(3, 3, gzi.a());
            d(3);
        } else {
            if (i == 4) {
                m();
                Context context2 = this.d;
                nrh.c(context2, context2.getString(R.string._2130838817_res_0x7f020521));
                a(3, 4, gzi.a());
                return;
            }
            if (i != 5) {
                return;
            }
            m();
            Context context3 = this.d;
            nrh.c(context3, context3.getString(R.string._2130838837_res_0x7f020535));
            a(3, 6, gzi.a());
        }
    }

    public static void e(String str, final ResponseCallback<Boolean> responseCallback) {
        if (responseCallback == null) {
            ReleaseLogUtil.c("Track_RouteTransferUtils", "isRouteInDevice callback is null");
            return;
        }
        if (!a()) {
            ReleaseLogUtil.d("Track_RouteTransferUtils", "Device not connect");
            responseCallback.onResponse(-1, false);
        } else if (!f()) {
            ReleaseLogUtil.d("Track_RouteTransferUtils", "not support Route Draw");
            responseCallback.onResponse(-1, false);
        } else {
            fbb.e().c(str, SportHiWearBusinessType.TRAJECTORY_IMAGE_HANDLE_SHAKE.getTypeValue(), new IBaseResponseCallback() { // from class: hov
                @Override // com.huawei.hwbasemgr.IBaseResponseCallback
                /* renamed from: onResponse */
                public final void d(int i, Object obj) {
                    RouteTransferDataHolder.d(ResponseCallback.this, i, obj);
                }
            });
        }
    }

    public static /* synthetic */ void d(ResponseCallback responseCallback, int i, Object obj) {
        ReleaseLogUtil.e("Track_RouteTransferUtils", "isRouteInDevice return: ", obj);
        if (obj instanceof int[]) {
            responseCallback.onResponse(0, Boolean.valueOf(((int[]) obj)[0] == 3));
        } else if (obj instanceof Integer) {
            responseCallback.onResponse(0, Boolean.valueOf(((Integer) obj).intValue() == 3));
        } else {
            responseCallback.onResponse(0, false);
        }
    }

    public void e(String str, RouteTrackInfo routeTrackInfo, double d) {
        if (routeTrackInfo == null) {
            ReleaseLogUtil.c("Track_RouteTransferUtils", "RouteTrackInfo is null");
            return;
        }
        this.l = routeTrackInfo;
        this.n = d;
        d("m1");
        if (TextUtils.isEmpty(str)) {
            o();
        } else {
            e(str);
        }
    }

    private void e(String str) {
        this.g = str;
        if (!d()) {
            d(0);
            nrh.b(this.d, R.string._2130840120_res_0x7f020a38);
            return;
        }
        if (!a()) {
            d(1);
            nrh.b(this.d, R.string._2130840121_res_0x7f020a39);
        } else {
            if (!f()) {
                d(2);
                if (RunningRouteUtils.g()) {
                    nrh.b(this.d, R.string.IDS_track_route_draw_device_version_not_support);
                    return;
                } else {
                    nrh.b(this.d, R.string.IDS_track_route_draw_device_not_support);
                    return;
                }
            }
            l();
        }
    }

    private void l() {
        if (this.d == null) {
            ReleaseLogUtil.c("Track_RouteTransferUtils", "activity or pathDetailInfo is null");
            return;
        }
        NoTitleCustomAlertDialog.Builder builder = new NoTitleCustomAlertDialog.Builder(this.d);
        builder.e(nsf.h(R.string.IDS_track_route_draw_transmit_to_wearable_device_tip)).czC_(R.string._2130847262_res_0x7f02261e, new View.OnClickListener() { // from class: hpf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RouteTransferDataHolder.this.bnT_(view);
            }
        }).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: hoz
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        builder.e().show();
    }

    public /* synthetic */ void bnT_(View view) {
        d("m2");
        b(this.l.getTrackId(), SportHiWearBusinessType.TRAJECTORY_IMAGE_HANDLE_SHAKE.getTypeValue());
        ViewClickInstrumentation.clickOnView(view);
    }

    private boolean p() {
        RouteTrackInfo routeTrackInfo = this.l;
        return routeTrackInfo != null && routeTrackInfo.getTrackType() == RouteType.DRAW.routeType();
    }

    private void r() {
        LogUtil.a("Track_RouteTransferUtils", " start sendRouteImage:", this.g);
        String str = sqd.c() + "/route_draw";
        LogUtil.a("Track_RouteTransferUtils", " start sendRouteImage filePath:", str);
        File file = new File(str);
        if (!file.exists()) {
            boolean mkdir = file.mkdir();
            Object[] objArr = new Object[2];
            objArr[0] = "CreateFile : ";
            objArr[1] = mkdir ? "Success" : "Failed";
            LogUtil.a("Track_RouteTransferUtils", objArr);
        }
        lqi.d().d(new lqg(this.g, null, file, new ProgressListener<File>() { // from class: com.huawei.healthcloud.plugintrack.util.RouteTransferDataHolder.2
            @Override // com.huawei.networkclient.ProgressListener
            public void onProgress(long j, long j2, boolean z) {
            }

            @Override // com.huawei.networkclient.ProgressListener
            /* renamed from: e, reason: merged with bridge method [inline-methods] */
            public void onFinish(File file2) {
                byte[] c = sqd.c(file2);
                boolean d = FileUtils.d(file2);
                Object[] objArr2 = new Object[2];
                objArr2[0] = "DeleteFile : ";
                objArr2[1] = d ? "Success" : "Failed";
                LogUtil.a("Track_RouteTransferUtils", objArr2);
                if (c != null && c.length != 0) {
                    RouteTransferDataHolder.this.c(c, SportHiWearBusinessType.TRAJECTORY_IMAGE.getTypeValue());
                } else {
                    ReleaseLogUtil.c("Track_RouteTransferUtils", "imageByte is null ");
                    nrh.b(RouteTransferDataHolder.this.d, R.string._2130838817_res_0x7f020521);
                }
            }

            @Override // com.huawei.networkclient.ProgressListener
            public void onFail(Throwable th) {
                LogUtil.b("Track_RouteTransferUtils", " commonDownload onFail:", th);
                nrh.b(RouteTransferDataHolder.this.d, R.string._2130838817_res_0x7f020521);
            }
        }));
    }

    private void x() {
        LogUtil.a("Track_RouteTransferUtils", " start sendRouteImageSmall:", this.h);
        if (TextUtils.isEmpty(this.h)) {
            ReleaseLogUtil.e("Track_RouteTransferUtils", "no Small Image");
            s();
            return;
        }
        String str = sqd.c() + "/route_draw_small";
        ReleaseLogUtil.e("Track_RouteTransferUtils", " start sendRouteImageSmall");
        File file = new File(str);
        if (!file.exists()) {
            boolean mkdir = file.mkdir();
            Object[] objArr = new Object[2];
            objArr[0] = "ImageSmall CreateFile : ";
            objArr[1] = mkdir ? "Success" : "Failed";
            ReleaseLogUtil.e("Track_RouteTransferUtils", objArr);
        }
        lqi.d().d(new lqg(this.h, null, file, new ProgressListener<File>() { // from class: com.huawei.healthcloud.plugintrack.util.RouteTransferDataHolder.5
            @Override // com.huawei.networkclient.ProgressListener
            public void onProgress(long j, long j2, boolean z) {
            }

            @Override // com.huawei.networkclient.ProgressListener
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onFinish(File file2) {
                byte[] c = sqd.c(file2);
                boolean d = FileUtils.d(file2);
                Object[] objArr2 = new Object[2];
                objArr2[0] = "DeleteFile : ";
                objArr2[1] = d ? "Success" : "Failed";
                ReleaseLogUtil.e("Track_RouteTransferUtils", objArr2);
                if (c != null && c.length != 0) {
                    ReleaseLogUtil.e("Track_RouteTransferUtils", "sendRouteImageSmall pointNum = ", Integer.valueOf(c.length));
                    RouteTransferDataHolder.this.i.sendCommandToDevice(c, SportHiWearBusinessType.TRAJECTORY_IMAGE_SMALL.getTypeValue(), new DeviceCallback() { // from class: com.huawei.healthcloud.plugintrack.util.RouteTransferDataHolder.5.5
                        @Override // com.huawei.basefitnessadvice.callback.DeviceCallback
                        public void onSendProgress(long j) {
                        }

                        @Override // com.huawei.basefitnessadvice.callback.DeviceCallback
                        public void onSendResult(int i) {
                            ReleaseLogUtil.e("Track_RouteTransferUtils", "sendRouteImageSmall send result ", Integer.valueOf(i));
                            RouteTransferDataHolder.this.s();
                        }
                    });
                } else {
                    ReleaseLogUtil.c("Track_RouteTransferUtils", "imageByte is null in sendRouteImageSmall");
                    RouteTransferDataHolder.this.s();
                }
            }

            @Override // com.huawei.networkclient.ProgressListener
            public void onFail(Throwable th) {
                ReleaseLogUtil.c("Track_RouteTransferUtils", " commonDownload onFail:", th);
                RouteTransferDataHolder.this.s();
            }
        }));
    }

    private void c(int i) {
        TrackFeatureExtractionApi trackFeatureExtractionApi = (TrackFeatureExtractionApi) Services.a("TrackFeatureExtractionAlgorithmService", TrackFeatureExtractionApi.class);
        if (trackFeatureExtractionApi == null || this.l == null) {
            LogUtil.a("Track_RouteTransferUtils", "sendRouteFile mRouteTrackInfo == ", this.l);
            m();
            Context context = this.d;
            nrh.c(context, context.getString(R.string._2130838817_res_0x7f020521));
            return;
        }
        boolean c = c();
        LogUtil.a("Track_RouteTransferUtils", "sendRouteFile point size ", Long.valueOf(this.l.getPointsNum()), " cp size ", Integer.valueOf(this.l.getMarkerPointsNum()), " isSupportRouteCp = ", Boolean.valueOf(c));
        if (!c) {
            this.l.setVersion(1);
            this.l.setShowAltitude(false);
            this.l.setMarkerPoints(new ArrayList());
            this.l.setMarkerPointsNum(0);
        }
        c(trackFeatureExtractionApi.algTrackImport(this.l, i, this.n), SportHiWearBusinessType.TRAJECTORY_FILE.getTypeValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c(byte[] bArr, final int i) {
        ReleaseLogUtil.e("Track_RouteTransferUtils", "sendRouteData pointNum = ", Integer.valueOf((bArr.length - 12) / 40));
        this.f = false;
        this.i.sendCommandToDevice(bArr, i, new DeviceCallback() { // from class: com.huawei.healthcloud.plugintrack.util.RouteTransferDataHolder.4
            @Override // com.huawei.basefitnessadvice.callback.DeviceCallback
            public void onSendResult(int i2) {
                Message obtain = Message.obtain();
                obtain.what = 2;
                obtain.arg1 = i2;
                obtain.arg2 = i;
                RouteTransferDataHolder.this.e.sendMessage(obtain);
                RouteTransferDataHolder.this.t();
            }

            @Override // com.huawei.basefitnessadvice.callback.DeviceCallback
            public void onSendProgress(long j) {
                Message obtain = Message.obtain();
                obtain.arg1 = RouteTransferDataHolder.this.b(j, i);
                obtain.what = 3;
                RouteTransferDataHolder.this.e.sendMessage(obtain);
                if (j > 0) {
                    RouteTransferDataHolder.this.y();
                }
                if (j == 100 && i == SportHiWearBusinessType.TRAJECTORY_FILE.getTypeValue() && RouteTransferDataHolder.this.k != null) {
                    RouteTransferDataHolder.this.k.dismiss();
                }
            }
        });
        y();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int b(long j, int i) {
        if (!p()) {
            return (int) j;
        }
        int i2 = (int) (j * 0.5d);
        return i == SportHiWearBusinessType.TRAJECTORY_IMAGE.getTypeValue() ? i2 : i2 + 50;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void y() {
        LogUtil.a("Track_RouteTransferUtils", "sendTimeOutMessage");
        t();
        if (this.f) {
            return;
        }
        this.e.sendEmptyMessageDelayed(4, PreConnectManager.CONNECT_INTERNAL);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(int i, int i2) {
        LogUtil.a("Track_RouteTransferUtils", "setFileResult:errorCode", Integer.valueOf(i));
        if (i == 1) {
            if (i2 == SportHiWearBusinessType.TRAJECTORY_IMAGE.getTypeValue()) {
                if (this.j) {
                    x();
                    return;
                } else {
                    s();
                    return;
                }
            }
            CustomProgressDialog customProgressDialog = this.k;
            if (customProgressDialog != null) {
                customProgressDialog.dismiss();
            }
            if ((this.d instanceof ClockingRankActivity) && p()) {
                d("m3");
                ((ClockingRankActivity) this.d).b(true);
            }
            w();
            a(2, -1, gzi.a());
            return;
        }
        m();
        nrh.e(this.d, R.string._2130838817_res_0x7f020521);
    }

    private void u() {
        LogUtil.a("Track_RouteTransferUtils", "showGuideToDescriptionDialog");
        if (this.c == null) {
            String string = this.d.getString(R.string._2130838901_res_0x7f020575);
            View inflate = View.inflate(this.d, R.layout.dialog_not_support_device, null);
            HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.not_support_device_describe);
            bnP_(healthTextView);
            healthTextView.setText(string);
            CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.d);
            builder.czg_(inflate);
            builder.czd_(this.d.getResources().getString(R.string._2130838921_res_0x7f020589), new View.OnClickListener() { // from class: hpj
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RouteTransferDataHolder.this.boa_(view);
                }
            }).czf_(this.d.getResources().getString(R.string.IDS_my_track_detail_supported_devices), new View.OnClickListener() { // from class: hoy
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RouteTransferDataHolder.this.bob_(view);
                }
            });
            CustomViewDialog e = builder.e();
            this.c = e;
            e.findViewById(R.id.custom_dialog_title_layout).setPadding(0, 0, 0, 0);
            this.c.setCancelable(false);
        }
        if (((Activity) this.d).isFinishing()) {
            return;
        }
        this.c.show();
    }

    public /* synthetic */ void boa_(View view) {
        this.c.dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void bob_(View view) {
        n();
        a(4, -1, gzi.a());
        ViewClickInstrumentation.clickOnView(view);
    }

    private void bnP_(TextView textView) {
        LogUtil.a("Track_RouteTransferUtils", "increaseTvHeight");
        if (!LanguageUtil.bi(this.d) || textView == null) {
            return;
        }
        textView.setPadding(textView.getPaddingLeft(), textView.getPaddingTop(), textView.getPaddingRight(), (int) this.d.getResources().getDimension(R.dimen._2131363094_res_0x7f0a0516));
    }

    private void n() {
        LogUtil.a("Track_RouteTransferUtils", "goToDescriptionPage");
        bzs.e().initH5Pro();
        H5ProLaunchOption build = new H5ProLaunchOption.Builder().addCustomizeJsModule("innerapi", bzs.e().getCommonJsModule("innerapi")).setImmerse().showStatusBar().setStatusBarTextBlack(true).setForceDarkMode(0).build();
        LogUtil.a("Track_RouteTransferUtils", "goToDescriptionPage baseUrl", EnvironmentHelper.getInstance().getUrl());
        H5ProClient.startH5LightApp(this.d, EnvironmentHelper.getInstance().getUrl() + "weixinScan/dist/index.html#/runPlanDevices", build);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(int i) {
        LogUtil.b("Track_RouteTransferUtils", "showTrasferProgressDialog:", Integer.valueOf(i), " mIsSendFileOverTime = ", Boolean.valueOf(this.f));
        if (this.f) {
            return;
        }
        CustomProgressDialog customProgressDialog = this.k;
        if (customProgressDialog != null && customProgressDialog.isShowing()) {
            this.r.d(i);
            this.r.c(i);
            return;
        }
        CustomProgressDialog.Builder builder = new CustomProgressDialog.Builder(this.d);
        this.r = builder;
        builder.d(this.d.getResources().getString(R.string._2130838802_res_0x7f020512));
        CustomProgressDialog e = this.r.e();
        this.k = e;
        LinearLayout linearLayout = (LinearLayout) e.findViewById(R.id.AppUpdateDialog_progress_layout);
        if (linearLayout.getChildAt(0).getLayoutParams() instanceof LinearLayout.LayoutParams) {
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) linearLayout.getChildAt(0).getLayoutParams();
            layoutParams.topMargin = 0;
            layoutParams.rightMargin = 0;
        }
        linearLayout.getChildAt(1).setVisibility(8);
        this.k.setCanceledOnTouchOutside(false);
        this.k.setCancelable(false);
        if (((Activity) this.d).isFinishing()) {
            return;
        }
        this.k.show();
    }

    private void w() {
        LogUtil.a("Track_RouteTransferUtils", "showExportSucceededDialog");
        m();
        String string = this.d.getString(p() ? R.string._2130840133_res_0x7f020a45 : R.string._2130840118_res_0x7f020a36);
        String string2 = this.d.getString(R.string._2130838834_res_0x7f020532);
        String string3 = this.d.getString(R.string._2130838808_res_0x7f020518);
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.d);
        builder.b(string3).e(string).cyV_(string2, new View.OnClickListener() { // from class: hpc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RouteTransferDataHolder.this.bnZ_(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        this.s = a2;
        a2.setCancelable(false);
        if (((Activity) this.d).isFinishing()) {
            return;
        }
        this.s.show();
    }

    public /* synthetic */ void bnZ_(View view) {
        this.s.dismiss();
        q();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void q() {
        LogUtil.a("Track_RouteTransferUtils", "linkDownloadOfflineMap");
        if (this.l == null) {
            LogUtil.b("Track_RouteTransferUtils", "mRouteTrackInfo is null");
            return;
        }
        if (Utils.n()) {
            LogUtil.a("Track_RouteTransferUtils", "isTWCountry");
            return;
        }
        if (b()) {
            DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "Track_RouteTransferUtils");
            if (deviceInfo == null) {
                LogUtil.b("Track_RouteTransferUtils", "deviceInfo is null");
                return;
            }
            List<RouteTrackPoint> c = koq.c(this.l.getPoints(), 20);
            if (koq.b(c)) {
                LogUtil.b("Track_RouteTransferUtils", "trackPoints isEmpty");
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (RouteTrackPoint routeTrackPoint : c) {
                arrayList.add(new hjd(routeTrackPoint.getLatitude(), routeTrackPoint.getLongitude()));
            }
            OfflineMapApi offlineMapApi = (OfflineMapApi) Services.c("Device", OfflineMapApi.class);
            offlineMapApi.initOfflineMap(deviceInfo.getUuid());
            offlineMapApi.queryingDownloadCity(arrayList, new DownloadCityCallback() { // from class: hpd
                @Override // com.huawei.health.device.callback.DownloadCityCallback
                public final void onResponse(List list) {
                    RouteTransferDataHolder.this.a((List<String>) list);
                }
            });
            return;
        }
        LogUtil.a("Track_RouteTransferUtils", "isSupportOfflineMap false");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(List<String> list) {
        Message obtain = Message.obtain();
        obtain.obj = list;
        obtain.what = 5;
        this.e.sendMessage(obtain);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final List<String> list) {
        if (koq.b(list)) {
            LogUtil.a("Track_RouteTransferUtils", "showDownloadOfflineMapDialog cityIdList is null");
            return;
        }
        String string = this.d.getString(R.string._2130840162_res_0x7f020a62);
        String string2 = this.d.getString(R.string._2130840163_res_0x7f020a63);
        String string3 = this.d.getString(R.string._2130845098_res_0x7f021daa);
        String string4 = this.d.getString(R.string._2130840161_res_0x7f020a61);
        CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.d);
        builder.b(string4).e(string).cyV_(string2, new View.OnClickListener() { // from class: hpb
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RouteTransferDataHolder.this.bnX_(list, view);
            }
        }).cyS_(string3, new View.OnClickListener() { // from class: hpe
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RouteTransferDataHolder.this.bnY_(view);
            }
        });
        CustomTextAlertDialog a2 = builder.a();
        this.f3829a = a2;
        a2.setCancelable(false);
        if (((Activity) this.d).isFinishing()) {
            return;
        }
        this.f3829a.show();
    }

    public /* synthetic */ void bnX_(List list, View view) {
        this.f3829a.dismiss();
        d((List<String>) list);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void bnY_(View view) {
        this.f3829a.dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void d(List<String> list) {
        if (!CommonUtil.aa(BaseApplication.e())) {
            nrh.b(BaseApplication.e(), R.string._2130841393_res_0x7f020f31);
            LogUtil.h("Track_RouteTransferUtils", "Network not Available");
            return;
        }
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, "Track_RouteTransferUtils");
        if (deviceInfo == null) {
            LogUtil.a("Track_RouteTransferUtils", "linkDownloadOfflineMap deviceInfo is null");
        } else {
            ((OfflineMapApi) Services.c("Device", OfflineMapApi.class)).jumpToOfflineMap(deviceInfo, list);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m() {
        CustomTextAlertDialog customTextAlertDialog = this.s;
        if (customTextAlertDialog != null && customTextAlertDialog.isShowing()) {
            this.s.dismiss();
        }
        CustomTextAlertDialog customTextAlertDialog2 = this.p;
        if (customTextAlertDialog2 != null && customTextAlertDialog2.isShowing()) {
            this.p.dismiss();
        }
        CustomProgressDialog customProgressDialog = this.k;
        if (customProgressDialog != null && customProgressDialog.isShowing()) {
            this.k.dismiss();
        }
        CustomViewDialog customViewDialog = this.c;
        if (customViewDialog != null && customViewDialog.isShowing()) {
            this.c.dismiss();
        }
        CustomViewDialog customViewDialog2 = this.b;
        if (customViewDialog2 != null && customViewDialog2.isShowing()) {
            this.b.dismiss();
        }
        CustomTextAlertDialog customTextAlertDialog3 = this.f3829a;
        if (customTextAlertDialog3 == null || !customTextAlertDialog3.isShowing()) {
            return;
        }
        this.f3829a.dismiss();
    }

    private void i(final int i) {
        LogUtil.a("Track_RouteTransferUtils", "showTipDialog: ", Integer.valueOf(i));
        m();
        if (i == 1) {
            Context context = this.d;
            Toast.makeText(context, context.getString(R.string._2130840123_res_0x7f020a3b), 0).show();
            return;
        }
        if (i == 2) {
            String string = this.d.getString(R.string._2130840119_res_0x7f020a37);
            String string2 = this.d.getString(R.string._2130838795_res_0x7f02050b);
            String string3 = this.d.getString(R.string._2130851021_res_0x7f0234cd);
            CustomTextAlertDialog.Builder builder = new CustomTextAlertDialog.Builder(this.d);
            builder.b(string).e(string2).cyS_(this.d.getResources().getString(R.string._2130839728_res_0x7f0208b0), new View.OnClickListener() { // from class: how
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RouteTransferDataHolder.this.bod_(view);
                }
            }).cyV_(string3, new View.OnClickListener() { // from class: hox
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RouteTransferDataHolder.this.boc_(i, view);
                }
            });
            CustomTextAlertDialog a2 = builder.a();
            this.p = a2;
            a2.setCancelable(false);
            if (((Activity) this.d).isFinishing()) {
                return;
            }
            this.p.show();
        }
    }

    public /* synthetic */ void bod_(View view) {
        this.p.dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void boc_(int i, View view) {
        e(i);
        ViewClickInstrumentation.clickOnView(view);
    }

    private void b(final int i) {
        String string;
        String string2;
        String string3;
        LogUtil.a("Track_RouteTransferUtils", "status: ", Integer.valueOf(i));
        m();
        if (i == -1) {
            string = this.d.getString(R.string.IDS_track_unbound_device);
            string2 = this.d.getString(R.string._2130840120_res_0x7f020a38);
            string3 = this.d.getString(R.string._2130838804_res_0x7f020514);
        } else if (i != 0) {
            string = null;
            string2 = null;
            string3 = null;
        } else {
            string = e() + this.d.getString(R.string._2130838819_res_0x7f020523);
            string2 = this.d.getString(R.string._2130840121_res_0x7f020a39);
            string3 = this.d.getString(R.string._2130838806_res_0x7f020516);
        }
        View inflate = View.inflate(this.d, R.layout.dialog_not_support_device, null);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.not_support_device_describe);
        bnP_(healthTextView);
        HealthTextView healthTextView2 = (HealthTextView) inflate.findViewById(R.id.support_device_list);
        healthTextView2.setVisibility(0);
        healthTextView2.setText(this.d.getResources().getString(R.string.IDS_my_track_detail_to_supported_devices));
        healthTextView.setText(string2);
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.d);
        builder.czh_(inflate, 24, 24).a(string);
        healthTextView2.setOnClickListener(new View.OnClickListener() { // from class: hpi
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RouteTransferDataHolder.this.bnU_(view);
            }
        });
        builder.czd_(this.d.getResources().getString(R.string._2130839728_res_0x7f0208b0), new View.OnClickListener() { // from class: hph
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RouteTransferDataHolder.this.bnV_(view);
            }
        }).czf_(string3, new View.OnClickListener() { // from class: hpk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RouteTransferDataHolder.this.bnW_(i, view);
            }
        });
        CustomViewDialog e = builder.e();
        this.b = e;
        View findViewById = e.findViewById(R.id.dialog_rlyt_content);
        if (findViewById instanceof RelativeLayout) {
            if (LanguageUtil.bc(this.d)) {
                ((RelativeLayout) findViewById).setGravity(21);
            } else {
                ((RelativeLayout) findViewById).setGravity(19);
            }
        }
        this.b.show();
    }

    public /* synthetic */ void bnU_(View view) {
        n();
        a(4, -1, gzi.a());
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void bnV_(View view) {
        this.b.dismiss();
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void bnW_(int i, View view) {
        e(i);
        ViewClickInstrumentation.clickOnView(view);
    }

    public static String e() {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, "Track_RouteTransferUtils");
        if (deviceInfo == null) {
            health.compact.a.util.LogUtil.d("Track_RouteTransferUtils", "getDeviceName   DeviceInfo:null");
            return "";
        }
        LogUtil.a("Track_RouteTransferUtils", "getDeviceName: ", deviceInfo.getDeviceName());
        return deviceInfo.getDeviceName();
    }

    private void e(int i) {
        LogUtil.a("Track_RouteTransferUtils", "excutePositive, tipType: ", Integer.valueOf(i));
        if (i == -1) {
            bnR_((Activity) this.d);
        } else if (i == 0) {
            bnS_((Activity) this.d);
        } else if (i == 2) {
            k();
        }
        CustomTextAlertDialog customTextAlertDialog = this.p;
        if (customTextAlertDialog != null) {
            customTextAlertDialog.dismiss();
        }
    }

    private static void bnR_(Activity activity) {
        try {
            Intent intent = new Intent();
            intent.setClassName(BaseApplication.d(), "com.huawei.ui.device.activity.adddevice.OneKeyScanActivity");
            activity.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            health.compact.a.util.LogUtil.e("Track_RouteTransferUtils", "startScanActivity ActivityNotFoundException");
        }
    }

    private void k() {
        LogUtil.a("Track_RouteTransferUtils", "deleteRoute");
        if (!CommonUtil.aa(this.d)) {
            nrh.b(this.d, R.string._2130839502_res_0x7f0207ce);
        } else {
            if (this.m == null) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            arrayList.add(Long.valueOf(this.m.getRouteVersion()));
            ((UserProfileMgrApi) Services.c("HWUserProfileMgr", UserProfileMgrApi.class)).deleteRoutes(arrayList, new RouteResultCallBack<CloudCommonReponse>() { // from class: com.huawei.healthcloud.plugintrack.util.RouteTransferDataHolder.1
                @Override // com.huawei.health.userprofilemgr.model.RouteResultCallBack
                /* renamed from: a, reason: merged with bridge method [inline-methods] */
                public void onSuccess(CloudCommonReponse cloudCommonReponse) {
                    if (cloudCommonReponse.getResultCode().intValue() != 0) {
                        nrh.e(RouteTransferDataHolder.this.d, R.string._2130843017_res_0x7f021589);
                        LogUtil.c("Track_RouteTransferUtils", "delete resp resultCode:", cloudCommonReponse.getResultCode(), ",resp resultDesc:", cloudCommonReponse.getResultDesc());
                    }
                }

                @Override // com.huawei.health.userprofilemgr.model.RouteResultCallBack
                public void onFailure(Throwable th) {
                    nrh.e(RouteTransferDataHolder.this.d, R.string._2130843017_res_0x7f021589);
                    LogUtil.b("Track_RouteTransferUtils", "delete onFailure:", th.getMessage());
                }
            });
        }
    }

    private void d(int i) {
        RouteTrackInfo routeTrackInfo = this.l;
        if (routeTrackInfo == null) {
            return;
        }
        gzh.d(i, routeTrackInfo.getTrackId(), this.l.getTrackName(), p());
    }

    private void d(String str) {
        RouteTrackInfo routeTrackInfo = this.l;
        if (routeTrackInfo == null) {
            return;
        }
        gzh.d(str, routeTrackInfo.getTrackId(), this.l.getTrackName(), p());
    }

    public static void bnS_(Activity activity) {
        try {
            Intent intent = new Intent();
            intent.setClassName(BaseApplication.d(), "com.huawei.ui.homewear21.home.WearHomeActivity");
            DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.ACTIVE_MAIN_DEVICES_WITHOUT_AW70, null, "Track_RouteTransferUtils");
            if (deviceInfo != null) {
                intent.putExtra("device_id", deviceInfo.getDeviceIdentify());
            }
            intent.setFlags(268435456);
            activity.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b("Track_RouteTransferUtils", "startWearHomeActivity ActivityNotFoundException");
        }
    }

    static class a extends BaseHandler<RouteTransferDataHolder> {
        a(RouteTransferDataHolder routeTransferDataHolder) {
            super(routeTransferDataHolder);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: boe_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(RouteTransferDataHolder routeTransferDataHolder, Message message) {
            LogUtil.a("Track_RouteTransferUtils", "handleMessageWhenReferenceNotNull, MSG: ", Integer.valueOf(message.what));
            int i = message.what;
            if (i == 1) {
                if (message.obj instanceof String) {
                    String str = (String) message.obj;
                    int i2 = 0;
                    if (message.getData().containsKey("pointMaxNum")) {
                        int[] intArray = message.getData().getIntArray("pointMaxNum");
                        if (intArray.length > 0) {
                            message.arg1 = intArray[0];
                        }
                        if (intArray.length > 1) {
                            i2 = intArray[1];
                        }
                    }
                    ReleaseLogUtil.e("Track_RouteTransferUtils", "pointMaxNum:", Integer.valueOf(i2));
                    routeTransferDataHolder.a(message.arg2, i2);
                    routeTransferDataHolder.e(str, message.arg1, message.arg2, Math.max(i2, 200));
                    return;
                }
                return;
            }
            if (i == 2) {
                routeTransferDataHolder.d(message.arg1, message.arg2);
                return;
            }
            if (i == 3) {
                routeTransferDataHolder.j(message.arg1);
                return;
            }
            if (i == 4) {
                c(routeTransferDataHolder);
                return;
            }
            if (i != 5) {
                if (i != 8) {
                    return;
                }
                nrh.e(BaseApplication.e(), R.string._2130838784_res_0x7f020500);
            } else if (koq.e(message.obj, String.class)) {
                routeTransferDataHolder.e((List<String>) message.obj);
            }
        }

        private void c(RouteTransferDataHolder routeTransferDataHolder) {
            routeTransferDataHolder.m();
            routeTransferDataHolder.f = true;
            Context context = routeTransferDataHolder.d;
            if ((context instanceof Activity) && !((Activity) context).isFinishing()) {
                ReleaseLogUtil.c("Track_RouteTransferUtils", "showOverTimeTip");
                nrh.c(context, context.getString(R.string._2130838817_res_0x7f020521));
            }
            LogUtil.a("Track_RouteTransferUtils", "send data device timeout:");
        }
    }
}
