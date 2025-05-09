package com.huawei.featureuserprofile.route.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.huawei.featureuserprofile.route.activity.RouteImportActivity;
import com.huawei.featureuserprofile.route.adapter.RouteImportAdapter;
import com.huawei.featureuserprofile.route.bean.KomootRouteBean;
import com.huawei.featureuserprofile.route.bean.KomootRouteData;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.health.userprofilemgr.model.RouteResultCallBack;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.framework.network.restclient.Response;
import com.huawei.hms.framework.network.restclient.ResultCallback;
import com.huawei.hwcloudmodel.model.CloudCommonReponse;
import com.huawei.hwcloudmodel.model.unite.RouteData;
import com.huawei.operation.utils.Constants;
import com.huawei.route.GetRouteDetailRsp;
import com.huawei.thirdpartservice.komootapi.KomootProviderApi;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import defpackage.btx;
import defpackage.koq;
import defpackage.nrh;
import defpackage.nsf;
import health.compact.a.CommonUtil;
import health.compact.a.Services;
import health.compact.a.util.LogUtil;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public class RouteImportActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private boolean f2010a;
    private LinearLayout b;
    private List<Long> c;
    private RouteImportAdapter d;
    private KomootProviderApi g;
    private RelativeLayout h;
    private RelativeLayout j;
    private RelativeLayout m;
    private HealthRecycleView o;
    private int p;
    private HealthTextView q;
    private CustomTitleBar r;
    private Map<Long, KomootRouteBean.Tour> s;
    private int u;
    private CustomViewDialog v;
    private HashMap<String, InputStream> w = new LinkedHashMap();
    private HashMap<String, Integer> l = new HashMap<>();
    private HashMap<String, String> x = new LinkedHashMap();
    private List<String> e = new ArrayList();
    private int k = 5;
    private int n = 0;
    private final String[] f = {"All", "hike", "touringbicycle", "e_touringbicycle", "mtb", "e_mtb", "racebike", "e_racebike", "jogging", "mtb_easy", "e_mtb_easy", "mtb_advanced", "e_mtb_advanced"};
    private String t = "All";
    private final Handler i = new d();

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.hw_import_user_route_activity);
        a();
        uI_(bundle);
    }

    private void a() {
        Intent intent = getIntent();
        if (intent != null) {
            this.k = intent.getIntExtra("maxCapacity", 5);
        }
        this.r = (CustomTitleBar) findViewById(R.id.title_bar);
        this.b = (LinearLayout) findViewById(R.id.ll_category);
        d();
        this.q = (HealthTextView) findViewById(R.id.sub_title);
        this.j = (RelativeLayout) findViewById(R.id.rel_route_loading);
        this.h = (RelativeLayout) findViewById(R.id.rel_route_empty);
        this.o = (HealthRecycleView) findViewById(R.id.rv_route);
        this.m = (RelativeLayout) findViewById(R.id.rv_loading);
        a(false);
        this.r.setLeftButtonVisibility(0);
        this.r.setLeftButtonDrawable(getDrawable(R.drawable._2131429371_res_0x7f0b07fb), nsf.h(R.string._2130850617_res_0x7f023339));
        this.o.setLayoutManager(new LinearLayoutManager(this));
        RouteImportAdapter routeImportAdapter = new RouteImportAdapter(this, this.o, new RouteImportAdapter.CallBack() { // from class: com.huawei.featureuserprofile.route.activity.RouteImportActivity.2
            @Override // com.huawei.featureuserprofile.route.adapter.RouteImportAdapter.CallBack
            public void onItemCheckedChanged() {
                RouteImportActivity.this.c();
            }

            @Override // com.huawei.featureuserprofile.route.adapter.RouteImportAdapter.CallBack
            public void onLoadingMore() {
                RouteImportActivity routeImportActivity = RouteImportActivity.this;
                routeImportActivity.d(routeImportActivity.n);
            }
        });
        this.d = routeImportAdapter;
        routeImportAdapter.c(this.k);
        this.o.setAdapter(this.d);
        g();
        this.r.setRightButtonDrawable(getDrawable(R.drawable._2131429706_res_0x7f0b094a), nsf.h(R.string._2130841395_res_0x7f020f33));
        this.r.setRightButtonOnClickListener(new View.OnClickListener() { // from class: bts
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RouteImportActivity.this.uL_(view);
            }
        });
    }

    public /* synthetic */ void uL_(View view) {
        b();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final int i) {
        LogUtil.b("RouteImportActivity", "loadMoreData" + i + " , Pages=" + this.u);
        if (i < this.u) {
            new Handler().post(new Runnable() { // from class: btj
                @Override // java.lang.Runnable
                public final void run() {
                    RouteImportActivity.this.e(i);
                }
            });
        } else {
            this.d.a();
        }
    }

    public /* synthetic */ void e(int i) {
        d(this.t, i);
    }

    private void d() {
        for (String str : this.f) {
            a(str);
        }
    }

    private void uI_(Bundle bundle) {
        this.g = (KomootProviderApi) Services.c("FeatureDataOpen", KomootProviderApi.class);
        if (!CommonUtil.aa(this)) {
            c(true);
            nrh.b(this, R.string._2130841393_res_0x7f020f31);
        } else {
            d(true);
            uJ_(bundle);
            f();
        }
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putString("SelectedType", this.t);
        bundle.putParcelableArray("SelectedCache", a(this.d.e()));
    }

    private KomootRouteBean.Tour[] a(Map<Long, KomootRouteBean.Tour> map) {
        ArrayList arrayList = new ArrayList();
        Iterator<Map.Entry<Long, KomootRouteBean.Tour>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getValue());
        }
        KomootRouteBean.Tour[] tourArr = new KomootRouteBean.Tour[arrayList.size()];
        for (int i = 0; i < arrayList.size(); i++) {
            tourArr[i] = (KomootRouteBean.Tour) arrayList.get(i);
        }
        return tourArr;
    }

    private void uJ_(Bundle bundle) {
        if (bundle == null) {
            return;
        }
        this.t = bundle.getString("SelectedType");
        h();
        Parcelable[] parcelableArray = bundle.getParcelableArray("SelectedCache");
        if (parcelableArray instanceof KomootRouteBean.Tour[]) {
            KomootRouteBean.Tour[] tourArr = (KomootRouteBean.Tour[]) parcelableArray;
            if (this.d == null || koq.b(Arrays.asList(tourArr))) {
                return;
            }
            this.d.b(Arrays.asList(tourArr));
        }
    }

    private void a(final String str) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.item_route_category, (ViewGroup) this.b, false);
        TextView textView = (TextView) inflate.findViewById(R.id.btn_route_category);
        textView.setTag(str);
        textView.setText(btx.e(this, str));
        textView.setOnClickListener(new View.OnClickListener() { // from class: btn
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RouteImportActivity.this.uK_(str, view);
            }
        });
        if ("All".equalsIgnoreCase(str)) {
            textView.setBackgroundResource(R.drawable._2131431298_res_0x7f0b0f82);
        }
        this.b.addView(inflate);
        this.b.invalidate();
    }

    public /* synthetic */ void uK_(String str, View view) {
        if (this.f2010a) {
            LogUtil.c("RouteImportActivity", "category selected return because is loading more");
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        a(true);
        this.n = 0;
        this.t = str;
        d(str, 0);
        h();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void h() {
        RouteImportAdapter routeImportAdapter = this.d;
        if (routeImportAdapter != null) {
            routeImportAdapter.e(this.t);
        }
        for (int i = 0; i < this.b.getChildCount(); i++) {
            View childAt = this.b.getChildAt(i);
            if (childAt instanceof TextView) {
                ((TextView) childAt).setBackgroundResource(childAt.getTag().toString().equalsIgnoreCase(this.t) ? R.drawable._2131431298_res_0x7f0b0f82 : R.drawable._2131431297_res_0x7f0b0f81);
            }
        }
    }

    private void g() {
        RouteImportAdapter routeImportAdapter = this.d;
        if (routeImportAdapter == null) {
            return;
        }
        this.s = routeImportAdapter.c();
        int size = this.d.e().size();
        this.r.setTitleText(size == 0 ? getString(R.string._2130838799_res_0x7f02050f) : getResources().getQuantityString(R.plurals._2130903091_res_0x7f030033, size, Integer.valueOf(size), Integer.valueOf(Math.min(this.k, 5))));
        this.r.setRightButtonVisibility(this.s.size() == 0 ? 4 : 0);
    }

    private void d(boolean z) {
        this.j.setVisibility(z ? 0 : 8);
        b(!z);
    }

    private void a(boolean z) {
        this.m.setVisibility(z ? 0 : 8);
        this.o.setVisibility(z ? 4 : 0);
        if (this.h.getVisibility() == 0) {
            this.h.setVisibility(8);
        }
    }

    private void c(boolean z) {
        boolean z2 = z && "All".equalsIgnoreCase(this.t) && this.n == 0;
        this.h.setVisibility(z2 ? 0 : 8);
        b(!z2);
    }

    private void b(boolean z) {
        this.b.setVisibility(z ? 0 : 8);
        this.q.setVisibility(z ? 0 : 8);
        this.o.setVisibility(z ? 0 : 8);
    }

    private void i() {
        if (getSystemService("layout_inflater") instanceof LayoutInflater) {
            CustomViewDialog e = new CustomViewDialog.Builder(this).c(false).czg_(((LayoutInflater) getSystemService("layout_inflater")).inflate(R.layout.dialog_route_uploading, (ViewGroup) null)).e();
            this.v = e;
            e.setCancelable(false);
            this.v.show();
        }
    }

    private void e() {
        if (this.v.isShowing()) {
            this.v.dismiss();
        }
    }

    private void b() {
        if (!CommonUtil.aa(this)) {
            nrh.b(this, R.string._2130841393_res_0x7f020f31);
            return;
        }
        if (this.f2010a) {
            LogUtil.c("RouteImportActivity", "executeUploadRoad return because is loading more");
            return;
        }
        RouteImportAdapter routeImportAdapter = this.d;
        if (routeImportAdapter == null) {
            return;
        }
        Map<Long, KomootRouteBean.Tour> c = routeImportAdapter.c();
        this.s = c;
        if (c.isEmpty()) {
            return;
        }
        i();
        Iterator<Map.Entry<Long, KomootRouteBean.Tour>> it = this.s.entrySet().iterator();
        while (it.hasNext()) {
            d(it.next());
        }
    }

    private void f() {
        btx.b(new RouteResultCallBack<GetRouteDetailRsp>() { // from class: com.huawei.featureuserprofile.route.activity.RouteImportActivity.4
            @Override // com.huawei.health.userprofilemgr.model.RouteResultCallBack
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public void onSuccess(GetRouteDetailRsp getRouteDetailRsp) {
                LogUtil.b("RouteImportActivity", "queryRouteSummary onSuccess :" + getRouteDetailRsp.toString());
                Message obtainMessage = RouteImportActivity.this.i.obtainMessage();
                obtainMessage.what = 0;
                obtainMessage.obj = getRouteDetailRsp;
                RouteImportActivity.this.i.sendMessage(obtainMessage);
            }

            @Override // com.huawei.health.userprofilemgr.model.RouteResultCallBack
            public void onFailure(Throwable th) {
                LogUtil.e("RouteImportActivity", "queryRouteSummary onFailure :" + th.toString());
                Message obtainMessage = RouteImportActivity.this.i.obtainMessage();
                obtainMessage.what = 0;
                obtainMessage.obj = -1;
                RouteImportActivity.this.i.sendMessage(obtainMessage);
            }
        });
    }

    private void d(String str, int i) {
        this.f2010a = true;
        LogUtil.d("RouteImportActivity", "getRoadListFromService type=" + str + " , page=" + i);
        if ("All".equalsIgnoreCase(str)) {
            str = "";
        }
        this.g.getRoadList(str, i, new ResultCallback<String>() { // from class: com.huawei.featureuserprofile.route.activity.RouteImportActivity.1
            @Override // com.huawei.hms.framework.network.restclient.ResultCallback
            public void onResponse(Response<String> response) {
                String obj = response.getRawResponse().getBody().toString();
                LogUtil.b("RouteImportActivity", "getRoadList response code:" + response.getCode());
                Message obtainMessage = RouteImportActivity.this.i.obtainMessage();
                obtainMessage.what = 1;
                obtainMessage.obj = obj;
                RouteImportActivity.this.i.sendMessage(obtainMessage);
            }

            @Override // com.huawei.hms.framework.network.restclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.e("RouteImportActivity", "getRoadList onFailure:" + th.toString());
                Message obtainMessage = RouteImportActivity.this.i.obtainMessage();
                obtainMessage.what = 1;
                obtainMessage.obj = -1;
                RouteImportActivity.this.i.sendMessage(obtainMessage);
            }
        });
    }

    private void d(Map.Entry<Long, KomootRouteBean.Tour> entry) {
        if (entry == null) {
            e();
            return;
        }
        final String l = entry.getKey().toString();
        KomootRouteBean.Tour value = entry.getValue();
        final String d2 = btx.d(value, b(value));
        LogUtil.d("RouteImportActivity", "getRoadFromService ID=" + btx.e(l) + ", Name = " + btx.e(value.getName()));
        this.g.getRoad(l, new ResultCallback<String>() { // from class: com.huawei.featureuserprofile.route.activity.RouteImportActivity.3
            @Override // com.huawei.hms.framework.network.restclient.ResultCallback
            public void onResponse(Response<String> response) {
                String body = response.getRawResponse().getBody();
                LogUtil.b("RouteImportActivity", "getRoad response code:" + response.getCode());
                Message obtainMessage = RouteImportActivity.this.i.obtainMessage();
                obtainMessage.what = 2;
                obtainMessage.obj = body;
                Bundle bundle = new Bundle();
                bundle.putString("id", l);
                bundle.putString("name", d2);
                obtainMessage.setData(bundle);
                RouteImportActivity.this.i.sendMessage(obtainMessage);
            }

            @Override // com.huawei.hms.framework.network.restclient.ResultCallback
            public void onFailure(Throwable th) {
                LogUtil.e("RouteImportActivity", "getRoad onFailure:" + th.toString());
                Message obtainMessage = RouteImportActivity.this.i.obtainMessage();
                obtainMessage.what = 2;
                obtainMessage.obj = -1;
                Bundle bundle = new Bundle();
                bundle.putString("id", l);
                bundle.putString("name", d2);
                obtainMessage.setData(bundle);
                RouteImportActivity.this.i.sendMessage(obtainMessage);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        g();
        if (this.d.e().size() == 5) {
            nrh.d(this, getResources().getQuantityString(R.plurals._2130903092_res_0x7f030034, 5, 5));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uF_(Message message) {
        if (message == null) {
            return;
        }
        if ((message.obj instanceof Integer) && ((Integer) message.obj).intValue() == -1) {
            LogUtil.c("RouteImportActivity", "handleRouteSummaryRsp receive msg fail");
        }
        if (message.obj instanceof GetRouteDetailRsp) {
            GetRouteDetailRsp getRouteDetailRsp = (GetRouteDetailRsp) message.obj;
            List<RouteData> routeDatas = getRouteDetailRsp.getRouteDatas();
            LogUtil.b("RouteImportActivity", "handleRouteSummaryRsp receive msg rsp:" + getRouteDetailRsp.toString());
            e(routeDatas);
        }
        d(this.t, this.n);
    }

    private void e(List<RouteData> list) {
        if (koq.b(list)) {
            return;
        }
        Iterator<RouteData> it = list.iterator();
        while (it.hasNext()) {
            this.e.add(it.next().getRouteName());
        }
        this.c = btx.a(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uG_(Message message) {
        this.f2010a = false;
        d(false);
        a(false);
        if (message == null) {
            return;
        }
        if ((message.obj instanceof Integer) && ((Integer) message.obj).intValue() == -1) {
            LogUtil.c("RouteImportActivity", "handleUpdateRoadList receive msg fail");
        }
        if (message.obj instanceof String) {
            KomootRouteBean a2 = btx.a((String) message.obj);
            int totalElements = a2.getTotalElements();
            this.p = totalElements;
            c(totalElements == 0);
            this.u = a2.getTotalPages();
            this.n = a2.getPageNumber();
            LogUtil.b("RouteImportActivity", "handleUpdateRoadList Type=" + this.t + " , Total=" + this.p + " , Pages=" + this.u + ", Number=" + this.n);
            List<KomootRouteData> e = btx.e(a2, this.c);
            if (koq.b(e) && this.n == 0) {
                this.o.setVisibility(8);
                this.h.setVisibility(0);
                d(this.n + 1);
                return;
            }
            this.h.setVisibility(8);
            this.o.setVisibility(0);
            RouteImportAdapter routeImportAdapter = this.d;
            if (routeImportAdapter != null) {
                routeImportAdapter.b(e, this.n == 0);
            }
            g();
            this.n++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uH_(Message message) {
        if (message == null) {
            e();
            return;
        }
        String string = message.getData().getString("id");
        String string2 = message.getData().getString("name");
        if (message.obj instanceof String) {
            LogUtil.d("RouteImportActivity", "handleUploadRoad receive msg road id = " + btx.e(string));
            this.w.put(string2, new ByteArrayInputStream(((String) message.obj).getBytes(StandardCharsets.UTF_8)));
        }
        if ((message.obj instanceof Integer) && ((Integer) message.obj).intValue() == -1) {
            LogUtil.c("RouteImportActivity", "handleUploadRoad receive msg road fail , id =" + btx.e(string));
            this.x.put(string, string2);
        }
        if (this.w.size() + this.x.size() == this.s.size()) {
            if (!this.w.isEmpty()) {
                LogUtil.b("RouteImportActivity", "handleUploadRoad start road upload -->" + this.w.size());
                btx.a(this.w, new RouteResultCallBack<CloudCommonReponse>() { // from class: com.huawei.featureuserprofile.route.activity.RouteImportActivity.5
                    @Override // com.huawei.health.userprofilemgr.model.RouteResultCallBack
                    /* renamed from: a, reason: merged with bridge method [inline-methods] */
                    public void onSuccess(CloudCommonReponse cloudCommonReponse) {
                        LogUtil.d("RouteImportActivity", "handleUploadRoad onSuccess : " + cloudCommonReponse.getResultCode());
                        RouteImportActivity.this.e(cloudCommonReponse);
                    }

                    @Override // com.huawei.health.userprofilemgr.model.RouteResultCallBack
                    public void onFailure(Throwable th) {
                        LogUtil.e("RouteImportActivity", "handleUploadRoad onFailure :" + th.getMessage());
                        RouteImportActivity.this.e((CloudCommonReponse) null);
                    }
                });
                return;
            }
            LogUtil.c("RouteImportActivity", "handleUploadRoad UploadMap is empty");
            e((CloudCommonReponse) null);
        }
    }

    private String b(KomootRouteBean.Tour tour) {
        if (!koq.b(this.e)) {
            Iterator<String> it = this.e.iterator();
            while (it.hasNext()) {
                d(it.next());
            }
        }
        String name = tour.getName();
        if (!this.l.containsKey(name)) {
            this.l.put(name, 0);
            return "";
        }
        String c = btx.c(tour.getDate());
        if (name.contains(c)) {
            return b(name);
        }
        return " " + c + b(name + " " + c);
    }

    private void d(String str) {
        if (this.l.containsKey(str)) {
            HashMap<String, Integer> hashMap = this.l;
            hashMap.put(str, hashMap.get(str));
        } else {
            this.l.put(str, 0);
        }
    }

    private String b(String str) {
        StringBuilder sb = new StringBuilder();
        if (this.l.containsKey(str)) {
            HashMap<String, Integer> hashMap = this.l;
            hashMap.put(str, Integer.valueOf(hashMap.get(str).intValue() + 1));
            sb.append(Constants.LEFT_BRACKET_ONLY);
            sb.append(this.l.get(str));
            sb.append(Constants.RIGHT_BRACKET_ONLY);
            String str2 = str + sb.toString();
            if (this.l.containsKey(str2)) {
                sb.append(b(str2));
            }
        } else {
            this.l.put(str, 0);
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(CloudCommonReponse cloudCommonReponse) {
        this.w.clear();
        this.x.clear();
        this.l.clear();
        e();
        if (cloudCommonReponse == null) {
            nrh.d(this, getString(R.string._2130838835_res_0x7f020533));
            LogUtil.c("RouteImportActivity", "handleUploadRoadResponse data is null");
            return;
        }
        int intValue = cloudCommonReponse.getResultCode().intValue();
        if (intValue == 0) {
            nrh.d(this, getString(R.string.IDS_device_wifi_my_qrcode_add_member_success_title));
            setResult(-1);
            finish();
            return;
        }
        if (intValue != 1001) {
            switch (intValue) {
                case 31013:
                    nrh.d(this, getString(R.string._2130838848_res_0x7f020540));
                    break;
                case 31014:
                    nrh.d(this, getString(R.string._2130838814_res_0x7f02051e));
                    break;
                case 31015:
                    nrh.d(this, getString(R.string._2130838815_res_0x7f02051f));
                    break;
                default:
                    nrh.d(this, getString(R.string._2130838835_res_0x7f020533));
                    LogUtil.e("RouteImportActivity", "handleUploadRoadResponse:" + cloudCommonReponse.getResultDesc());
                    break;
            }
            return;
        }
        nrh.d(this, getString(R.string._2130838835_res_0x7f020533));
    }

    static class d extends BaseHandler<RouteImportActivity> {
        private d(RouteImportActivity routeImportActivity) {
            super(routeImportActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: uM_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(RouteImportActivity routeImportActivity, Message message) {
            int i = message.what;
            if (i == 0) {
                routeImportActivity.uF_(message);
            } else if (i == 1) {
                routeImportActivity.uG_(message);
            } else {
                if (i != 2) {
                    return;
                }
                routeImportActivity.uH_(message);
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
