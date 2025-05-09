package com.huawei.healthcloud.plugintrack.ui.activity.interactor;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.trackanimation.download.DynamicTrackDownloadUtils;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.IBaseResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.dialog.CustomProgressDialog;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import defpackage.gvv;
import defpackage.gww;
import defpackage.hah;
import defpackage.hak;
import defpackage.han;
import defpackage.hap;
import defpackage.koq;
import defpackage.nrh;
import health.compact.a.LogAnonymous;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

/* loaded from: classes4.dex */
public class MapInteractor {

    /* renamed from: a, reason: collision with root package name */
    private Activity f3680a;
    private CustomProgressDialog b;
    private Context c;
    private String d;
    private CustomProgressDialog.Builder e;
    private List<hak> f;
    private int g;
    private String h;
    private MarkResetListener i;
    private List<hah> j;
    private IBaseResponseCallback k;

    public interface MapItemClickListener {
        void onClickListener(Object obj, int i);
    }

    public interface MarkResetListener {
        void onResetMarker(hah hahVar, int i);
    }

    public MapInteractor(Activity activity, Context context, int i) {
        boolean z;
        this.f = new ArrayList(16);
        this.j = new ArrayList(16);
        this.f3680a = activity;
        this.c = context;
        gww gwwVar = new gww(BaseApplication.getContext(), new StorageParams(), Integer.toString(20002));
        this.g = i;
        this.d = gwwVar.c(i);
        String s = gwwVar.s();
        if (TextUtils.isEmpty(this.d) || !TextUtils.equals(s, "8.0")) {
            String e = han.e().e(hap.a(this.g), this.g);
            this.d = e;
            if (!TextUtils.isEmpty(e)) {
                gwwVar.g("8.0");
            }
            z = true;
        } else {
            z = false;
        }
        String t = gwwVar.t();
        this.h = t;
        if (TextUtils.isEmpty(t)) {
            this.h = han.e().f();
        }
        this.f = (List) gvv.a(this.d, new TypeToken<ArrayList<hak>>() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.interactor.MapInteractor.3
        });
        this.j = (List) gvv.a(this.h, new TypeToken<ArrayList<hah>>() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.interactor.MapInteractor.5
        });
        d();
        if (z) {
            e();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        this.d = han.e().e(hap.a(this.g), this.g);
        this.h = han.e().f();
        this.f = (List) gvv.a(this.d, new TypeToken<ArrayList<hak>>() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.interactor.MapInteractor.4
        });
        this.j = (List) gvv.a(this.h, new TypeToken<ArrayList<hah>>() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.interactor.MapInteractor.1
        });
        c();
    }

    private void c() {
        hah hahVar;
        if (koq.b(this.j)) {
            LogUtil.h("Track_MapInteractor", "resetLastSelectMarkInfo: mark info list is empty");
            return;
        }
        Context context = this.c;
        if (context == null) {
            LogUtil.h("Track_MapInteractor", "resetLastSelectMarkInfo: context is null");
            return;
        }
        int i = 0;
        SharedPreferences sharedPreferences = context.getSharedPreferences("retrack_file", 0);
        int i2 = -1;
        if (sharedPreferences.getInt("new_track_mode_custom_mark_position", -1) == -1) {
            LogUtil.a("Track_MapInteractor", "resetLastSelectMarkInfo: mark is default");
            return;
        }
        String string = sharedPreferences.getString("new_track_mode_custom_mark", null);
        if (TextUtils.isEmpty(string)) {
            LogUtil.a("Track_MapInteractor", "resetLastSelectMarkInfo: last selected mark is null ");
            return;
        }
        try {
            hahVar = (hah) new Gson().fromJson(string, hah.class);
        } catch (JsonSyntaxException e) {
            LogUtil.h("Track_MapInteractor", "resetLastSelectMarkInfo: ", LogAnonymous.b((Throwable) e));
            hahVar = null;
        }
        if (hahVar == null) {
            LogUtil.h("Track_MapInteractor", "resetLastSelectMarkInfo: last selected mark parse error");
            return;
        }
        while (true) {
            if (i < this.j.size()) {
                hah hahVar2 = this.j.get(i);
                if (hahVar2 != null && TextUtils.equals(hahVar2.f(), hahVar.f()) && TextUtils.equals(hahVar2.d(), hahVar.d())) {
                    i2 = i;
                    break;
                }
                i++;
            } else {
                break;
            }
        }
        if (this.i != null && koq.d(this.j, i2)) {
            LogUtil.a("Track_MapInteractor", "resetLastSelectMarkInfo: newIndex = ", Integer.valueOf(i2));
            this.i.onResetMarker(this.j.get(i2), i2);
        } else {
            this.i.onResetMarker(null, i2);
        }
    }

    private void d() {
        SharedPreferences sharedPreferences = this.c.getSharedPreferences(Integer.toString(20002), 0);
        ArrayList arrayList = new ArrayList();
        arrayList.add("new_custom_gaode_map_information");
        arrayList.add("custom_gaode_map_information");
        arrayList.add("custom_google_map_information");
        arrayList.add("custom_hsm_map_information");
        arrayList.add("custom_mark_information");
        SharedPreferences.Editor edit = sharedPreferences.edit();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            edit.remove((String) it.next());
        }
        edit.commit();
        SharedPreferences sharedPreferences2 = this.c.getSharedPreferences("retrack_file", 0);
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add("new_track_gaode_custom_map");
        arrayList2.add("new_track_mode_custom_map_position");
        arrayList2.add("track_mode_custom_mark");
        arrayList2.add("track_gaode_custom_map");
        arrayList2.add("track_google_custom_map");
        arrayList2.add("track_hms_custom_map");
        arrayList2.add("track_mode_custom_map_position");
        arrayList2.add("track_goolge_custom_map_position");
        arrayList2.add("track_hms_custom_map_position");
        arrayList2.add("track_mode_custom_mark_position");
        SharedPreferences.Editor edit2 = sharedPreferences2.edit();
        Iterator it2 = arrayList2.iterator();
        while (it2.hasNext()) {
            edit2.remove((String) it2.next());
        }
        edit2.commit();
    }

    public void d(IBaseResponseCallback iBaseResponseCallback) {
        if (iBaseResponseCallback == null) {
            LogUtil.h("Track_MapInteractor", "responseCallback == null");
            return;
        }
        this.k = iBaseResponseCallback;
        ArrayList arrayList = new ArrayList();
        arrayList.add(0, this.f);
        arrayList.add(1, this.j);
        this.k.d(0, arrayList);
    }

    public void b() {
        LogUtil.a("Track_MapInteractor", "startDownloadConfig");
        han.a();
        han.e().c(true);
        han.e().b((DynamicTrackDownloadUtils.DownloadResponseCallback) new d(this), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        CustomProgressDialog customProgressDialog = this.b;
        if (customProgressDialog != null && customProgressDialog.isShowing()) {
            LogUtil.b("Track_MapInteractor", "startDownloadProgress progress exist");
            return;
        }
        this.b = new CustomProgressDialog(this.f3680a);
        CustomProgressDialog.Builder builder = new CustomProgressDialog.Builder(this.f3680a);
        this.e = builder;
        builder.d(this.f3680a.getString(R.string._2130843873_res_0x7f0218e1)).cyH_(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.interactor.MapInteractor.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Track_MapInteractor", "startDownLoadProgress onclick cancel");
                han.e().c();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        CustomProgressDialog e = this.e.e();
        this.b = e;
        e.setCanceledOnTouchOutside(false);
        if (!this.f3680a.isFinishing()) {
            this.b.show();
            this.e.d(0);
            this.e.e(UnitUtil.e(0.0d, 2, 0));
            this.e.b();
        }
        LogUtil.a("Track_MapInteractor", "mCustomProgressDialog.show()");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        CustomProgressDialog customProgressDialog = this.b;
        if (customProgressDialog == null || !customProgressDialog.isShowing()) {
            return;
        }
        this.e.d(i);
        this.e.e(UnitUtil.e(i, 2, 0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        CustomProgressDialog customProgressDialog = this.b;
        if (customProgressDialog == null || !customProgressDialog.isShowing() || this.f3680a.isFinishing()) {
            return;
        }
        this.b.cancel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(String str) {
        NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(this.f3680a).e(str).czC_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.interactor.MapInteractor.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                han.e().d(MapInteractor.this.g);
                ViewClickInstrumentation.clickOnView(view);
            }
        }).czz_(R.string._2130839709_res_0x7f02089d, new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.interactor.MapInteractor.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LogUtil.a("Track_MapInteractor", "cancal download map");
                ViewClickInstrumentation.clickOnView(view);
            }
        }).e();
        e.setCancelable(false);
        e.show();
    }

    private void e() {
        Context context = this.c;
        if (context == null) {
            LogUtil.h("Track_MapInteractor", "resetLastSelectMapStyle: context is null");
            return;
        }
        int i = this.g;
        if (i != 1) {
            LogUtil.h("Track_MapInteractor", "resetLastSelectMapStyle: mapType is ", Integer.valueOf(i));
            return;
        }
        SharedPreferences sharedPreferences = context.getSharedPreferences("retrack_file", 0);
        int i2 = sharedPreferences.getInt("track_mode_custom_map_position_69", -1);
        if (i2 >= 0) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            if (koq.b(this.f, i2)) {
                edit.putInt("track_mode_custom_map_position_69", -1);
                edit.apply();
                LogUtil.h("Track_MapInteractor", "resetLastSelectMapStyle: mMapInformationList isOutOfBounds");
                return;
            }
            hak hakVar = this.f.get(i2);
            if (hakVar == null) {
                edit.putInt("track_mode_custom_map_position_69", -1);
                edit.apply();
                LogUtil.h("Track_MapInteractor", "resetLastSelectMapStyle: lastCustomMapInformation is null");
            } else {
                edit.putString("track_gaode_custom_map_69", new Gson().toJson(hakVar));
                edit.apply();
            }
        }
    }

    public void e(MarkResetListener markResetListener) {
        this.i = markResetListener;
    }

    static class d implements DynamicTrackDownloadUtils.DownloadResponseCallback {
        private final WeakReference<MapInteractor> b;

        d(MapInteractor mapInteractor) {
            this.b = new WeakReference<>(mapInteractor);
        }

        @Override // com.huawei.healthcloud.plugintrack.trackanimation.download.DynamicTrackDownloadUtils.DownloadResponseCallback
        public void onMobile(int i) {
            MapInteractor mapInteractor = this.b.get();
            if (mapInteractor == null || mapInteractor.f3680a == null) {
                LogUtil.h("Track_MapInteractor", "mapInteractor or mActivity is null in onMobile");
                return;
            }
            mapInteractor.a(String.format(Locale.ENGLISH, mapInteractor.f3680a.getResources().getString(R.string._2130843874_res_0x7f0218e2), mapInteractor.f3680a.getString(R.string.IDS_device_upgrade_file_size_mb, new Object[]{UnitUtil.e(i / 1048576.0d, 1, 1)})));
        }

        @Override // com.huawei.healthcloud.plugintrack.trackanimation.download.DynamicTrackDownloadUtils.DownloadResponseCallback
        public void onStart() {
            MapInteractor mapInteractor = this.b.get();
            if (mapInteractor != null) {
                mapInteractor.g();
            } else {
                LogUtil.h("Track_MapInteractor", "mapInteractor is null in onStart");
            }
        }

        @Override // com.huawei.healthcloud.plugintrack.trackanimation.download.DynamicTrackDownloadUtils.DownloadResponseCallback
        public void onProgress(int i) {
            MapInteractor mapInteractor = this.b.get();
            if (mapInteractor != null) {
                mapInteractor.a(i);
            } else {
                LogUtil.h("Track_MapInteractor", "mapInteractor is null in onProgress");
            }
        }

        @Override // com.huawei.healthcloud.plugintrack.trackanimation.download.DynamicTrackDownloadUtils.DownloadResponseCallback
        public void onSuccess() {
            MapInteractor mapInteractor = this.b.get();
            if (mapInteractor != null) {
                mapInteractor.a();
                mapInteractor.i();
                han.e().c(false);
                return;
            }
            LogUtil.h("Track_MapInteractor", "mapInteractor is null in onSuccess");
        }

        @Override // com.huawei.healthcloud.plugintrack.trackanimation.download.DynamicTrackDownloadUtils.DownloadResponseCallback
        public void onFail() {
            MapInteractor mapInteractor = this.b.get();
            if (mapInteractor != null) {
                mapInteractor.a();
                if (mapInteractor.f3680a != null) {
                    nrh.d(mapInteractor.f3680a, mapInteractor.f3680a.getResources().getString(R.string._2130841543_res_0x7f020fc7));
                    return;
                }
                return;
            }
            LogUtil.h("Track_MapInteractor", "mapInteractor is null in onFail");
        }
    }
}
