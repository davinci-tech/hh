package com.huawei.healthcloud.plugintrack.runningroute.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.text.Editable;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.amap.api.location.AMapLocation;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.huawei.basefitnessadvice.callback.UiCallback;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.constant.GetDeviceInfoMode;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.trackprocess.model.GpsPoint;
import com.huawei.healthcloud.plugintrack.golf.bean.GolfGetLocation;
import com.huawei.healthcloud.plugintrack.runningroute.bean.RouteCloudFactory;
import com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteUtils;
import com.huawei.hianalytics.visual.autocollect.HAWebViewInterface;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.framework.common.hianalytics.WiseOpenHianalyticsData;
import com.huawei.hms.support.api.entity.common.CommonConstant;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.networkclient.ResultCallback;
import com.huawei.operation.OpAnalyticsUtil;
import com.huawei.operation.OperationKey;
import com.huawei.operation.utils.Constants;
import com.huawei.pluginfitnessadvice.Attribute;
import com.huawei.route.RouteMarkerPoint;
import com.huawei.route.RouteTrackInfo;
import com.huawei.route.RouteTrackPoint;
import com.huawei.route.RouteType;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.dialog.HealthButtonBarLayout;
import com.huawei.ui.commonui.edittext.HealthEditText;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.ash;
import defpackage.cun;
import defpackage.emb;
import defpackage.emc;
import defpackage.emf;
import defpackage.emh;
import defpackage.emi;
import defpackage.emk;
import defpackage.emm;
import defpackage.emn;
import defpackage.emo;
import defpackage.emp;
import defpackage.emq;
import defpackage.emr;
import defpackage.ems;
import defpackage.emt;
import defpackage.emu;
import defpackage.emv;
import defpackage.emw;
import defpackage.emx;
import defpackage.emy;
import defpackage.emz;
import defpackage.enb;
import defpackage.enc;
import defpackage.ene;
import defpackage.eni;
import defpackage.ffy;
import defpackage.gtl;
import defpackage.gwf;
import defpackage.gzi;
import defpackage.hbb;
import defpackage.hjd;
import defpackage.ixx;
import defpackage.jdm;
import defpackage.koq;
import defpackage.ktj;
import defpackage.lqi;
import defpackage.lql;
import defpackage.nrv;
import defpackage.nsn;
import defpackage.nsy;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

/* loaded from: classes4.dex */
public class RunningRouteUtils {
    private static RunningRouteUtils b;
    private static hjd c;
    private final RouteCloudFactory e = new RouteCloudFactory(BaseApplication.getContext());
    private static final Object d = new Object();

    /* renamed from: a, reason: collision with root package name */
    private static final String[] f3568a = {"M0DV", "M0DY", "M0DZ", "M0E1", "M0E2", "M0E6", "M0E9", "M0EA"};

    public interface GuideDialogCallBack {
        void showRecommendedRoute();
    }

    public static /* synthetic */ String a(String str, String str2) {
        return str2;
    }

    public static boolean a(int i) {
        return i == 258;
    }

    private RunningRouteUtils() {
    }

    public static RunningRouteUtils a(Context context) {
        if (b == null) {
            synchronized (d) {
                RunningRouteUtils runningRouteUtils = new RunningRouteUtils();
                b = runningRouteUtils;
                LogUtil.a("Track_RunningRouteUtils", "RunningRouteUtils instance hashcode: ", Integer.valueOf(runningRouteUtils.hashCode()));
            }
        }
        return b;
    }

    public void d(emk emkVar, ResultCallback<emt> resultCallback) {
        lqi.d().b(emkVar.getUrl(), this.e.getHeaders(), lql.b(this.e.getBody(emkVar)), emt.class, resultCallback);
    }

    public void d(emm emmVar, ResultCallback<emo> resultCallback) {
        lqi.d().b(emmVar.getUrl(), this.e.getHeaders(), lql.b(this.e.getBody(emmVar)), emo.class, resultCallback);
    }

    public void a(emq emqVar, ResultCallback<emp> resultCallback) {
        lqi.d().b(emqVar.getUrl(), this.e.getHeaders(), lql.b(this.e.getBody(emqVar)), emp.class, resultCallback);
    }

    public void e(emn emnVar, ResultCallback<CloudCommonReponse> resultCallback) {
        lqi.d().b(emnVar.getUrl(), this.e.getHeaders(), lql.b(this.e.getBody(emnVar)), CloudCommonReponse.class, resultCallback);
    }

    public void a(emr emrVar, ResultCallback<ems> resultCallback) {
        lqi.d().b(emrVar.getUrl(), this.e.getHeaders(), lql.b(this.e.getBody(emrVar)), ems.class, resultCallback);
    }

    public void d(emi emiVar, ResultCallback<CloudCommonReponse> resultCallback) {
        lqi.d().b(emiVar.getUrl(), this.e.getHeaders(), lql.b(this.e.getBody(emiVar)), CloudCommonReponse.class, resultCallback);
    }

    public void e(emh emhVar, ResultCallback<CloudCommonReponse> resultCallback) {
        lqi.d().b(emhVar.getUrl(), this.e.getHeaders(), lql.b(this.e.getBody(emhVar)), CloudCommonReponse.class, resultCallback);
    }

    public void c(emb embVar, ResultCallback<CloudCommonReponse> resultCallback) {
        lqi.d().b(embVar.getUrl(), this.e.getHeaders(), lql.b(this.e.getBody(embVar)), CloudCommonReponse.class, resultCallback);
    }

    public void a(emx emxVar, ResultCallback<enb> resultCallback) {
        lqi.d().b(emxVar.getUrl(), this.e.getHeaders(), lql.b(this.e.getBody(emxVar)), enb.class, resultCallback);
    }

    public void c(ene eneVar, ResultCallback<emz> resultCallback) {
        lqi.d().b(eneVar.getUrl(), this.e.getHeaders(), lql.b(this.e.getBody(eneVar)), emz.class, resultCallback);
    }

    public void e(emy emyVar, ResultCallback<emw> resultCallback) {
        lqi.d().b(emyVar.getUrl(), this.e.getHeaders(), lql.b(this.e.getBody(emyVar)), emw.class, resultCallback);
    }

    public void a(emu emuVar, ResultCallback<emv> resultCallback) {
        lqi.d().b(emuVar.getUrl(), this.e.getHeaders(), lql.b(this.e.getBody(emuVar)), emv.class, resultCallback);
    }

    public static hjd a(final GolfGetLocation.GetLocationCallback getLocationCallback) {
        if (getLocationCallback != null) {
            GolfGetLocation.getInstance().getLocation(new GolfGetLocation.GetLocationCallback() { // from class: com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteUtils.2
                @Override // com.huawei.healthcloud.plugintrack.golf.bean.GolfGetLocation.GetLocationCallback
                public void onSuccess(hjd hjdVar) {
                    if (hjdVar == null) {
                        GolfGetLocation.GetLocationCallback.this.onFailure(-1, "");
                        return;
                    }
                    LogUtil.a("Track_RunningRouteUtils", "result lat: ", Double.valueOf(hjdVar.b), ", lng: " + hjdVar.d);
                    hjd unused = RunningRouteUtils.c = hjdVar;
                    SharedPreferenceManager.e(BaseApplication.getContext(), "USER_LOCATION_LAT_LNG", "USER_LOCATION_LAT_LNG", nrv.a(RunningRouteUtils.c), (StorageParams) null);
                    GolfGetLocation.GetLocationCallback.this.onSuccess(hjdVar);
                }

                @Override // com.huawei.healthcloud.plugintrack.golf.bean.GolfGetLocation.GetLocationCallback
                public void onFailure(int i, String str) {
                    GolfGetLocation.GetLocationCallback.this.onFailure(i, str);
                }
            });
        }
        return a();
    }

    public static hjd a() {
        hjd c2 = c();
        if (!b(c2)) {
            LogUtil.a("Track_RunningRouteUtils", "get location by gps failed, use location in sp");
            String b2 = SharedPreferenceManager.b(BaseApplication.getContext(), "USER_LOCATION_LAT_LNG", "USER_LOCATION_LAT_LNG");
            LogUtil.a("Track_RunningRouteUtils", "get location from sp = ", b2);
            if (!TextUtils.isEmpty(b2)) {
                try {
                    return (hjd) nrv.b(b2, hjd.class);
                } catch (Exception unused) {
                    LogUtil.a("Track_RunningRouteUtils", "parse location fail");
                }
            }
            if (b(c)) {
                LogUtil.a("Track_RunningRouteUtils", "use gaode, sCurLatLng = (", Double.valueOf(c.b), ", ", Double.valueOf(c.d), Constants.RIGHT_BRACKET_ONLY);
                SharedPreferenceManager.e(BaseApplication.getContext(), "USER_LOCATION_LAT_LNG", "USER_LOCATION_LAT_LNG", nrv.a(c), (StorageParams) null);
                return c;
            }
            LogUtil.a("Track_RunningRouteUtils", "use location in default");
            return RunningRouteConstants.d;
        }
        c = c2;
        LogUtil.a("Track_RunningRouteUtils", "get location success, sCurLatLng = (", Double.valueOf(c2.b), ", ", Double.valueOf(c.d), Constants.RIGHT_BRACKET_ONLY);
        return c;
    }

    public static hjd c() {
        if (ActivityCompat.checkSelfPermission(BaseApplication.getContext(), "android.permission.ACCESS_FINE_LOCATION") != 0 && ActivityCompat.checkSelfPermission(BaseApplication.getContext(), "android.permission.ACCESS_COARSE_LOCATION") != 0) {
            LogUtil.a("Track_RunningRouteUtils", "no location permission, request...");
            return null;
        }
        LocationManager locationManager = (LocationManager) BaseApplication.getContext().getSystemService("location");
        LogUtil.a("Track_RunningRouteUtils", "begin to use gps provider");
        Location lastKnownLocation = locationManager.getLastKnownLocation(GeocodeSearch.GPS);
        if (lastKnownLocation == null) {
            LogUtil.a("Track_RunningRouteUtils", "gps provider location is null, begin to use network provider");
            lastKnownLocation = locationManager.getLastKnownLocation(HAWebViewInterface.NETWORK);
        }
        hjd aTB_ = new gtl(BaseApplication.getContext(), 0).aTB_(lastKnownLocation);
        LogUtil.a("Track_RunningRouteUtils", "curLocation gotten by Android: (lat: ", aTB_.b + ", lon: ", aTB_.d + Constants.RIGHT_BRACKET_ONLY);
        return aTB_;
    }

    private static boolean b(hjd hjdVar) {
        return hjdVar != null && Math.abs(hjdVar.b) > 1.0E-6d && Math.abs(hjdVar.d) > 1.0E-6d;
    }

    public static void d(Context context, GpsPoint gpsPoint) {
        LogUtil.a("Track_RunningRouteUtils", "showFirstPopularRoutesDialog enter");
        hjd a2 = a();
        if (gpsPoint == null && a2 == null) {
            LogUtil.b("Track_RunningRouteUtils", "no latlng jump");
            return;
        }
        if (gpsPoint == null) {
            gpsPoint = new GpsPoint(a2.b, a2.d);
        }
        boolean b2 = jdm.b(context, "com.baidu.BaiduMap");
        boolean b3 = jdm.b(context, "com.autonavi.minimap");
        boolean b4 = jdm.b(context, "com.tencent.map");
        if (!b2 && !b3 && !b4) {
            LogUtil.a("Track_RunningRouteUtils", "no map install");
            Toast.makeText(context, context.getString(R.string._2130845887_res_0x7f0220bf), 0).show();
            return;
        }
        if (b2 && !b3 && !b4) {
            d(0, 1);
            e(context, gpsPoint);
            return;
        }
        if (!b2 && b3 && !b4) {
            d(0, 0);
            b(context, gpsPoint);
        } else if (!b2 && !b3 && b4) {
            d(0, 2);
            a(context, gpsPoint);
        } else {
            d(context, gpsPoint, b2, b3, b4);
        }
    }

    private static void d(final Context context, final GpsPoint gpsPoint, boolean z, boolean z2, boolean z3) {
        View inflate = View.inflate(context, R.layout.dialog_map_choose, null);
        CustomAlertDialog.Builder cyl_ = new CustomAlertDialog.Builder(context).cyp_(inflate).cyl_(R.string.IDS_device_release_user_profile_log_collect_cancel, 0, new DialogInterface.OnClickListener() { // from class: gzo
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                RunningRouteUtils.aXl_(dialogInterface, i);
            }
        }, false);
        View findViewById = inflate.findViewById(R.id.baidu_map_layout);
        View findViewById2 = inflate.findViewById(R.id.gaode_map_layout);
        View findViewById3 = inflate.findViewById(R.id.tencent_map_layout);
        final CustomAlertDialog c2 = cyl_.c();
        c2.setCancelable(false);
        if (z) {
            nsy.cMA_(findViewById, 0);
            findViewById.setOnClickListener(new View.OnClickListener() { // from class: gzm
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RunningRouteUtils.aXm_(context, gpsPoint, c2, view);
                }
            });
        }
        if (z2) {
            nsy.cMA_(findViewById2, 0);
            findViewById2.setOnClickListener(new View.OnClickListener() { // from class: gzq
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RunningRouteUtils.aXn_(context, gpsPoint, c2, view);
                }
            });
        }
        if (z3) {
            nsy.cMA_(findViewById3, 0);
            findViewById3.setOnClickListener(new View.OnClickListener() { // from class: gzn
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    RunningRouteUtils.aXo_(context, gpsPoint, c2, view);
                }
            });
        }
        if (c2.isShowing()) {
            return;
        }
        c2.show();
        c2.setCanceledOnTouchOutside(true);
    }

    public static /* synthetic */ void aXl_(DialogInterface dialogInterface, int i) {
        LogUtil.a("Track_RunningRouteUtils", "showMapChooseDialog click cancel");
        ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
    }

    public static /* synthetic */ void aXm_(Context context, GpsPoint gpsPoint, CustomAlertDialog customAlertDialog, View view) {
        d(1, 1);
        e(context, gpsPoint);
        customAlertDialog.cancel();
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void aXn_(Context context, GpsPoint gpsPoint, CustomAlertDialog customAlertDialog, View view) {
        d(1, 0);
        b(context, gpsPoint);
        customAlertDialog.cancel();
        ViewClickInstrumentation.clickOnView(view);
    }

    public static /* synthetic */ void aXo_(Context context, GpsPoint gpsPoint, CustomAlertDialog customAlertDialog, View view) {
        d(1, 2);
        a(context, gpsPoint);
        customAlertDialog.cancel();
        ViewClickInstrumentation.clickOnView(view);
    }

    public static void b(final Context context, final String str, final String str2) {
        b(str, str2, 1);
        View inflate = View.inflate(context, R.layout.dialog_feedback_text, null);
        final boolean[] zArr = {false};
        final HealthEditText healthEditText = (HealthEditText) inflate.findViewById(R.id.edit_desc);
        final CustomAlertDialog c2 = new CustomAlertDialog.Builder(context).cyp_(inflate).e(R.string._2130845877_res_0x7f0220b5).cyn_(R.string.IDS_device_release_user_profile_log_collect_cancel, new DialogInterface.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteUtils.8
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                RunningRouteUtils.b(str, str2, 3);
                LogUtil.a("Track_RunningRouteUtils", "showFeedBackDialog click cancel");
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }).cyo_(R.string._2130841395_res_0x7f020f33, new DialogInterface.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteUtils.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                LogUtil.a("Track_RunningRouteUtils", "showFeedBackDialog click agree");
                RunningRouteUtils.b(str, str2, 2);
                if (zArr[0]) {
                    RunningRouteUtils.d(context, healthEditText.getText().toString(), str);
                }
                ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
            }
        }).c();
        final HealthButton healthButton = (HealthButton) c2.findViewById(R.id.dialog_btn_positive);
        healthButton.setTextColor(context.getColor(R.color._2131296928_res_0x7f0902a0));
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteUtils.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Track_RunningRouteUtils", "showFeedBackDialog click agree");
                RunningRouteUtils.b(str, str2, 2);
                if (zArr[0]) {
                    RunningRouteUtils.d(context, healthEditText.getText().toString(), str);
                    c2.dismiss();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        ((HealthButtonBarLayout) c2.findViewById(R.id.button_bar)).setDividerDrawable(ContextCompat.getDrawable(context, R.drawable._2131427925_res_0x7f0b0255));
        healthEditText.addTextChangedListener(new TextWatcher() { // from class: com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteUtils.9
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (!TextUtils.isEmpty(charSequence)) {
                    zArr[0] = true;
                    healthButton.setTextColor(context.getColor(R.color._2131297805_res_0x7f09060d));
                } else {
                    zArr[0] = false;
                    healthButton.setTextColor(context.getColor(R.color._2131296928_res_0x7f0902a0));
                }
            }
        });
        c2.setCancelable(false);
        if (c2.isShowing()) {
            return;
        }
        c2.show();
        c2.setCanceledOnTouchOutside(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(String str, String str2, int i) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("event", Integer.valueOf(i));
        hashMap.put("routeID", str);
        hashMap.put("routeIName", str2);
        ixx.d().d(com.huawei.haf.application.BaseApplication.e(), "1040089", hashMap, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void d(final Context context, String str, String str2) {
        emc.d().feedbackHotPathInfo(new emn.b().c(str).d(str2).c(), new UiCallback<CloudCommonReponse>() { // from class: com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteUtils.6
            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            public void onFailure(int i, String str3) {
                LogUtil.a("Track_RunningRouteUtils", "feedBackRouteInfo fail");
                Context context2 = context;
                Toast.makeText(context2, context2.getString(R.string._2130841884_res_0x7f02111c), 0).show();
            }

            @Override // com.huawei.basefitnessadvice.callback.UiCallback
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
            public void onSuccess(CloudCommonReponse cloudCommonReponse) {
                LogUtil.a("Track_RunningRouteUtils", "feedBackRouteInfo success");
                Context context2 = context;
                Toast.makeText(context2, context2.getString(R.string._2130845882_res_0x7f0220ba), 0).show();
            }
        });
    }

    public static void b(Context context, GpsPoint gpsPoint) {
        if (!jdm.b(context, "com.autonavi.minimap")) {
            Toast.makeText(context, "please install map", 1).show();
            return;
        }
        if (!(context instanceof Activity)) {
            LogUtil.b("Track_RunningRouteUtils", "not activity");
            return;
        }
        if (a() == null) {
            LogUtil.a("Track_RunningRouteUtils", "latLng is null");
            return;
        }
        StringBuffer stringBuffer = new StringBuffer("androidamap://viewReGeo?sourceApplication=amap&lat=");
        stringBuffer.append(gpsPoint.getLatitude()).append("&lon=").append(gpsPoint.getLongitude()).append("&keywords=&dev=0&style=2");
        Intent intent = new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse(stringBuffer.toString()));
        intent.setPackage("com.autonavi.minimap");
        nsn.cLM_(intent, "com.autonavi.minimap", context, context.getResources().getString(R.string._2130837686_res_0x7f0200b6));
    }

    public static void e(Context context, GpsPoint gpsPoint) {
        if (!jdm.b(context, "com.baidu.BaiduMap")) {
            Toast.makeText(context, "please install map", 1).show();
            return;
        }
        if (!(context instanceof Activity)) {
            LogUtil.b("Track_RunningRouteUtils", "not activity");
            return;
        }
        if (a() == null) {
            LogUtil.a("Track_RunningRouteUtils", "latLng is null");
            return;
        }
        hjd d2 = d(gpsPoint);
        nsn.cLM_(new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("baidumap://map/geocoder?location=" + d2.b + "," + d2.d)), "com.baidu.BaiduMap", context, context.getResources().getString(R.string._2130845888_res_0x7f0220c0));
    }

    public static void a(Context context, GpsPoint gpsPoint) {
        if (!jdm.b(context, "com.tencent.map")) {
            Toast.makeText(context, "please install map", 1).show();
            return;
        }
        if (!(context instanceof Activity)) {
            LogUtil.b("Track_RunningRouteUtils", "not activity");
            return;
        }
        if (a() == null) {
            LogUtil.a("Track_RunningRouteUtils", "latLng is null");
            return;
        }
        nsn.cLM_(new Intent(CommonConstant.ACTION.HWID_SCHEME_URL, Uri.parse("qqmap://map/routeplan?type=drive&from=&fromcoord=&to=目的地&tocoord=" + gpsPoint.getLatitude() + "," + gpsPoint.getLongitude() + "&policy=0&referer=appName")), "com.tencent.map", context, context.getResources().getString(R.string._2130845889_res_0x7f0220c1));
    }

    private static void d(int i, int i2) {
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        if (i2 == 0) {
            hashMap.put("mapType", "高德");
        } else if (i2 == 1) {
            hashMap.put("mapType", "百度");
        } else if (i2 == 2) {
            hashMap.put("mapType", "腾讯");
        }
        hashMap.put("event", Integer.valueOf(i));
        ixx.d().d(com.huawei.haf.application.BaseApplication.e(), "1040086", hashMap, 0);
    }

    public static hjd d(GpsPoint gpsPoint) {
        double longitude = gpsPoint.getLongitude();
        double latitude = gpsPoint.getLatitude();
        double sqrt = Math.sqrt((longitude * longitude) + (latitude * latitude)) + (Math.sin(latitude * 52.35987755982988d) * 2.0E-5d);
        double atan2 = Math.atan2(latitude, longitude) + (Math.cos(longitude * 52.35987755982988d) * 3.0E-6d);
        return new hjd((Math.sin(atan2) * sqrt) + 0.006d, (Math.cos(atan2) * sqrt) + 0.0065d);
    }

    public static void d(final HealthTextView healthTextView, String str) {
        if (healthTextView == null) {
            LogUtil.a("Track_RunningRouteUtils", "setUserNameAnonymization, textView is null");
            return;
        }
        if (str == null) {
            healthTextView.setText("*");
            return;
        }
        if (str.length() == 0 || str.length() == 1) {
            healthTextView.setText("*");
            return;
        }
        if (str.length() == 2) {
            healthTextView.setText(str.charAt(0) + "*");
            return;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(str.charAt(0));
        for (int i = 1; i < str.length() - 1; i++) {
            sb.append("*");
        }
        sb.append(str.charAt(str.length() - 1));
        healthTextView.setText(sb.toString());
        healthTextView.post(new Runnable() { // from class: com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteUtils.10
            @Override // java.lang.Runnable
            public void run() {
                while (RunningRouteUtils.aXk_(HealthTextView.this, sb.toString())) {
                    sb.replace(2, 3, "");
                }
                HealthTextView.this.setText(sb.toString());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean aXk_(TextView textView, String str) {
        if (textView == null) {
            LogUtil.b("Track_RunningRouteUtils", "textView is null");
            return false;
        }
        TextPaint paint = textView.getPaint();
        paint.setTextSize(textView.getTextSize());
        return ((int) paint.measureText(str)) > textView.getWidth();
    }

    public static List<Attribute> e(Context context) {
        ArrayList arrayList = new ArrayList(10);
        arrayList.add(new Attribute(1, context.getString(R.string._2130840130_res_0x7f020a42)));
        arrayList.add(new Attribute(2, context.getString(R.string._2130840088_res_0x7f020a18)));
        arrayList.add(new Attribute(3, context.getString(R.string._2130840087_res_0x7f020a17)));
        arrayList.add(new Attribute(4, context.getString(R.string._2130840129_res_0x7f020a41)));
        return arrayList;
    }

    public static double a(hjd hjdVar, hjd hjdVar2) {
        if (hjdVar == null || hjdVar2 == null) {
            LogUtil.a("Track_RunningRouteUtils", "latLng is null");
            return 0.0d;
        }
        LogUtil.c("Track_RunningRouteUtils", "latLng1 = (", Double.valueOf(hjdVar.b), ",", Double.valueOf(hjdVar.d), "), latLng2 = (", Double.valueOf(hjdVar2.b), ",", Double.valueOf(hjdVar2.d), Constants.RIGHT_BRACKET_ONLY);
        double d2 = (hjdVar.b * 3.141592653589793d) / 180.0d;
        double d3 = (hjdVar2.b * 3.141592653589793d) / 180.0d;
        double d4 = (hjdVar.d * 3.141592653589793d) / 180.0d;
        double d5 = (hjdVar2.d * 3.141592653589793d) / 180.0d;
        return Math.round(((Math.asin(Math.sqrt(Math.pow(Math.sin((d2 - d3) / 2.0d), 2.0d) + ((Math.cos(d2) * Math.cos(d3)) * Math.pow(Math.sin((d4 - d5) / 2.0d), 2.0d)))) * 2.0d) * 6378137.0d) * 10000.0d) / 10000.0d;
    }

    public static hjd b(GpsPoint gpsPoint) {
        if (gpsPoint == null) {
            LogUtil.a("Track_RunningRouteUtils", "gpsPoint is null");
            return null;
        }
        return new hjd(gpsPoint.getLatitude(), gpsPoint.getLongitude());
    }

    public static String b() {
        return ash.b("COUNTRY_ID");
    }

    public static void e(String str) {
        ash.a("COUNTRY_ID", str);
    }

    public static String a(int i, int i2, double d2) {
        return b(i, i2, d2, 2);
    }

    public static String b(int i, int i2, double d2, int i3) {
        boolean h = UnitUtil.h();
        double d3 = d2 / 1000.0d;
        double e = UnitUtil.e(d3, 3);
        if (h) {
            d3 = e;
        }
        if (h) {
            i = i2;
        }
        return ffy.b(i, (int) d3, UnitUtil.e(d3, 1, i3));
    }

    public static String a(long j) {
        return (j / 60) + "'" + (j % 60) + "''";
    }

    public static Bitmap aXp_(String str) {
        byte[] decode = Base64.decode(str, 0);
        return BitmapFactory.decodeByteArray(decode, 0, decode.length);
    }

    public static void aXj_(Bitmap bitmap, Context context) {
        final Dialog dialog = new Dialog(context);
        ImageView imageView = new ImageView(context);
        imageView.setImageBitmap(bitmap);
        dialog.setContentView(imageView);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.show();
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteUtils.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                dialog.cancel();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    public static void a(int i, String str) {
        if (TextUtils.isEmpty(str)) {
            str = "true";
        }
        HashMap hashMap = new HashMap();
        hashMap.put("click", 1);
        hashMap.put("result", str);
        hashMap.put("from", Integer.valueOf(i));
        ixx.d().d(com.huawei.haf.application.BaseApplication.e(), "1040084", hashMap, 0);
    }

    public static boolean h() {
        return SharedPreferenceManager.a(Integer.toString(20002), "routeAutoRedPointShow", true);
    }

    public static void f() {
        SharedPreferenceManager.e(Integer.toString(20002), "routeAutoRedPointShow", false);
    }

    public static void e() {
        hjd a2 = a();
        if ((!ktj.e(BaseApplication.getContext()) && a2 == null) || k()) {
            LogUtil.a("Track_RunningRouteUtils", "location is invalid");
            a(0, "PositioningFailed");
            a2 = RunningRouteConstants.d;
        }
        gzi.b(a2);
        j();
    }

    private static void j() {
        if (TextUtils.isEmpty(b())) {
            emc.d().getCityInfoWithGps(new emq.d().e(gzi.f().b).b(gzi.f().d).b(), new UiCallback<emp>() { // from class: com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteUtils.4
                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                public void onFailure(int i, String str) {
                    LogUtil.h("Track_RunningRouteUtils", "initCountryIdWithGps failed, errorCode = ", Integer.valueOf(i), ", errorInfo = ", str);
                }

                @Override // com.huawei.basefitnessadvice.callback.UiCallback
                /* renamed from: e, reason: merged with bridge method [inline-methods] */
                public void onSuccess(emp empVar) {
                    String a2 = empVar.a();
                    RunningRouteUtils.e(a2);
                    LogUtil.a("Track_RunningRouteUtils", "countryId = ", a2);
                }
            });
        }
    }

    private static boolean k() {
        hjd f = gzi.f();
        return f != null && hbb.e(f.b, f.d);
    }

    public static RouteTrackInfo d(enc encVar) {
        RouteTrackInfo routeTrackInfo = new RouteTrackInfo();
        if (encVar == null) {
            LogUtil.b("Track_RunningRouteUtils", "detailInfo is null");
            return routeTrackInfo;
        }
        routeTrackInfo.setTrackId(encVar.h());
        routeTrackInfo.setTrackType((encVar.g() == 1 ? RouteType.DRAW : RouteType.DEFAULT).routeType());
        routeTrackInfo.setTrackName(encVar.n());
        routeTrackInfo.setTotalTime((long) ((encVar.r() / 1000.0d) * 420.0d));
        boolean z = !TextUtils.equals(AMapLocation.COORD_TYPE_WGS84, encVar.e());
        a(encVar.k(), z, routeTrackInfo);
        e(encVar.a(), z, routeTrackInfo);
        return routeTrackInfo;
    }

    private static void a(List<GpsPoint> list, boolean z, RouteTrackInfo routeTrackInfo) {
        if (koq.b(list)) {
            ReleaseLogUtil.c("Track_RunningRouteUtils", "TrackPoint is empty");
            routeTrackInfo.setPoints(new ArrayList());
            return;
        }
        ArrayList arrayList = new ArrayList(list.size());
        for (GpsPoint gpsPoint : list) {
            if (gpsPoint == null) {
                ReleaseLogUtil.c("Track_RunningRouteUtils", "gpsPoint is null in setTrackPoint");
            } else {
                RouteTrackPoint routeTrackPoint = new RouteTrackPoint();
                hjd a2 = a(z, gpsPoint);
                routeTrackPoint.setLatitude(a2.b);
                routeTrackPoint.setLongitude(a2.d);
                routeTrackPoint.setAltitude(gpsPoint.getAltitude());
                arrayList.add(routeTrackPoint);
            }
        }
        routeTrackInfo.setPoints(arrayList);
        routeTrackInfo.setPointsNum(arrayList.size());
    }

    private static void e(emf emfVar, boolean z, RouteTrackInfo routeTrackInfo) {
        if (emfVar == null || koq.b(emfVar.e())) {
            LogUtil.h("Track_RunningRouteUtils", "no cpPoint");
            routeTrackInfo.setMarkerPoints(new ArrayList());
            return;
        }
        List<GpsPoint> e = emfVar.e();
        ArrayList arrayList = new ArrayList(e.size());
        for (GpsPoint gpsPoint : e) {
            if (gpsPoint == null) {
                ReleaseLogUtil.c("Track_RunningRouteUtils", "cpGps is null in setMarkerPoint");
            } else {
                RouteMarkerPoint routeMarkerPoint = new RouteMarkerPoint();
                hjd a2 = a(z, gpsPoint);
                routeMarkerPoint.setLatitude(a2.b);
                routeMarkerPoint.setLongitude(a2.d);
                routeMarkerPoint.setAltitude(gpsPoint.getAltitude());
                routeMarkerPoint.setSerial(gpsPoint.getIndex());
                routeMarkerPoint.setIndexOfTrackPoint(gpsPoint.getPathIndex());
                arrayList.add(routeMarkerPoint);
            }
        }
        routeTrackInfo.setTargetNum(emfVar.b());
        routeTrackInfo.setMarkerPoints(arrayList);
        routeTrackInfo.setMarkerPointsNum(arrayList.size());
    }

    private static hjd a(boolean z, GpsPoint gpsPoint) {
        hjd hjdVar = new hjd(gpsPoint.getLatitude(), gpsPoint.getLongitude());
        return z ? gwf.d(hjdVar) : hjdVar;
    }

    public static List<String> c(List<eni> list, List<Integer> list2) {
        if (koq.b(list) || koq.b(list2)) {
            LogUtil.b("Track_RunningRouteUtils", "pathAttributes or attributeIds is empty");
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList(list2.size());
        Map map = (Map) list.stream().collect(Collectors.toMap(new Function() { // from class: gzp
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Integer.valueOf(((eni) obj).d());
            }
        }, new Function() { // from class: gzr
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((eni) obj).b();
            }
        }, new BinaryOperator() { // from class: gzs
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return RunningRouteUtils.a((String) obj, (String) obj2);
            }
        }));
        for (Integer num : list2) {
            if (num.intValue() == 0) {
                LogUtil.h("Track_RunningRouteUtils", "attributeId == 0 means all");
            } else if (map.containsKey(num)) {
                arrayList.add((String) map.get(num));
            }
        }
        return arrayList;
    }

    public static boolean g() {
        DeviceInfo deviceInfo = cun.c().getDeviceInfo(GetDeviceInfoMode.CONNECTED_MAIN_DEVICES_WITHOUT_AW70, null, "Track_RunningRouteUtils");
        if (deviceInfo == null) {
            ReleaseLogUtil.d("Track_RunningRouteUtils", "deviceInfo is null");
            return false;
        }
        String hiLinkDeviceId = deviceInfo.getHiLinkDeviceId();
        if (TextUtils.isEmpty(hiLinkDeviceId)) {
            ReleaseLogUtil.d("Track_RunningRouteUtils", "hiLinkDeviceId is null");
            return false;
        }
        for (String str : f3568a) {
            if (hiLinkDeviceId.contains(str)) {
                return true;
            }
        }
        return false;
    }

    private static boolean i() {
        return SharedPreferenceManager.a(Integer.toString(20002), "displayed_guide_dialog", false);
    }

    private static void n() {
        SharedPreferenceManager.e(Integer.toString(20002), "displayed_guide_dialog", true);
    }

    public static void b(Context context, final GuideDialogCallBack guideDialogCallBack) {
        if (context == null) {
            LogUtil.a("Track_RunningRouteUtils", "context is null in showRouteDrawGuideDialog");
        } else {
            if (i()) {
                LogUtil.a("Track_RunningRouteUtils", "isDisplayedGuideDialog is true");
                return;
            }
            new CustomAlertDialog.Builder(context).cyp_(View.inflate(context, R.layout.dialog_route_draw_guide, null)).cyo_(R.string._2130847260_res_0x7f02261c, new DialogInterface.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.runningroute.utils.RunningRouteUtils.5
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    GuideDialogCallBack guideDialogCallBack2 = GuideDialogCallBack.this;
                    if (guideDialogCallBack2 != null) {
                        guideDialogCallBack2.showRecommendedRoute();
                    }
                    ViewClickInstrumentation.clickOnDialog(dialogInterface, i);
                }
            }).e(R.string._2130847258_res_0x7f02261a).e(true).c().show();
            n();
        }
    }

    public static void b(final int i, final long j) {
        if (j < 1000) {
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: gzj
            @Override // java.lang.Runnable
            public final void run() {
                RunningRouteUtils.a(i, j);
            }
        });
    }

    public static /* synthetic */ void a(int i, long j) {
        LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<>(2);
        linkedHashMap.put("errorCode", String.valueOf(i));
        linkedHashMap.put(WiseOpenHianalyticsData.UNION_COSTTIME, String.valueOf(j));
        OpAnalyticsUtil.getInstance().setKeyProcessEvent(OperationKey.HEALTH_APP_RUN_ROUTE_COST_TIME_2129017.value(), linkedHashMap);
    }
}
