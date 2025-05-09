package com.huawei.health.ecologydevice.ui.measure.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.R;
import com.huawei.health.device.api.HonourDeviceConstantsApi;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.measure.activity.DeviceUsageDescriptionActivity;
import com.huawei.health.ecologydevice.util.FAUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.dcq;
import defpackage.dcx;
import defpackage.dcz;
import defpackage.koq;
import health.compact.a.Services;
import health.compact.a.Utils;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public class DeviceUsageDescriptionActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private List<dcz.d> f2326a;
    private ArrayList<String> b = new ArrayList<>(10);
    private UsageDescriptionAdapter c;
    private RelativeLayout d;
    private String e;
    private ListView h;
    private List<dcz.d> i;
    private dcz j;

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_device_usage_description);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        String stringExtra = intent.getStringExtra("productId");
        this.e = stringExtra;
        if (stringExtra == null) {
            finish();
            return;
        }
        this.d = (RelativeLayout) findViewById(R.id.list_load_progress);
        LogUtil.a("EcologyDevice_DeviceUsageDescriptionActivity", "onCreate mProductId = ", this.e);
        dcz d = ResourceManager.e().d(this.e);
        this.j = d;
        if (d == null) {
            this.d.setVisibility(0);
            b();
        } else {
            this.d.setVisibility(8);
            c();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        this.h = (ListView) findViewById(R.id.user_guide_list_view);
        dcz d = ResourceManager.e().d(this.e);
        this.j = d;
        if (d != null) {
            this.f2326a = d.e();
            if ("db83f5aa-a63d-4041-b930-24b0eac65680".equals(this.e) && this.f2326a.size() > 3) {
                List<dcz.d> list = this.f2326a;
                this.f2326a = list.subList(3, list.size());
            }
            this.i = this.j.ad();
            HonourDeviceConstantsApi honourDeviceConstantsApi = (HonourDeviceConstantsApi) Services.c("PluginDevice", HonourDeviceConstantsApi.class);
            if (!koq.b(this.i) && !honourDeviceConstantsApi.isHuaweiOrHonourDevice(this.e) && !honourDeviceConstantsApi.isHonourDevice(this.e)) {
                e(this.i);
                return;
            } else if (!koq.b(this.f2326a)) {
                e(this.f2326a);
                return;
            } else {
                LogUtil.a("EcologyDevice_DeviceUsageDescriptionActivity", "onCreate mDescriptionList is null");
                return;
            }
        }
        finish();
    }

    private void e(List<dcz.d> list) {
        Iterator<dcz.d> it = list.iterator();
        while (it.hasNext()) {
            dcz.d next = it.next();
            this.b.add(dcq.b().a(this.e, next == null ? null : next.e()));
        }
        UsageDescriptionAdapter usageDescriptionAdapter = new UsageDescriptionAdapter(list, this);
        this.c = usageDescriptionAdapter;
        this.h.setAdapter((ListAdapter) usageDescriptionAdapter);
    }

    private void b() {
        if (!Utils.i() || Utils.f()) {
            return;
        }
        ThreadPoolManager.d().execute(new Runnable() { // from class: dgm
            @Override // java.lang.Runnable
            public final void run() {
                DeviceUsageDescriptionActivity.this.d();
            }
        });
    }

    public /* synthetic */ void d() {
        FAUtil.c(new FAUtil.ResultCallback() { // from class: dgi
            @Override // com.huawei.health.ecologydevice.util.FAUtil.ResultCallback
            public final void onResult(boolean z) {
                DeviceUsageDescriptionActivity.this.b(z);
            }
        });
    }

    public /* synthetic */ void b(boolean z) {
        if (z) {
            e();
        }
    }

    private void e() {
        runOnUiThread(new Runnable() { // from class: com.huawei.health.ecologydevice.ui.measure.activity.DeviceUsageDescriptionActivity.4
            @Override // java.lang.Runnable
            public void run() {
                DeviceUsageDescriptionActivity.this.d.setVisibility(8);
                DeviceUsageDescriptionActivity.this.c();
            }
        });
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        LogUtil.a("EcologyDevice_DeviceUsageDescriptionActivity", "onDestroy to enter");
    }

    public class UsageDescriptionAdapter extends BaseAdapter {

        /* renamed from: a, reason: collision with root package name */
        private LayoutInflater f2327a;
        private List<dcz.d> c;
        private LruCache<String, Bitmap> d = new LruCache<>(1048576);

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public UsageDescriptionAdapter(List<dcz.d> list, Context context) {
            this.c = list;
            this.f2327a = LayoutInflater.from(context);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            List<dcz.d> list = this.c;
            if (list == null) {
                return 0;
            }
            return list.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            if (koq.b(this.c, i)) {
                return null;
            }
            return this.c.get(i);
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            Bitmap bitmap;
            e eVar = null;
            Object[] objArr = 0;
            if (view == null) {
                e eVar2 = new e();
                View inflate = this.f2327a.inflate(R.layout.device_usage_description_item, (ViewGroup) null);
                eVar2.d = (HealthTextView) inflate.findViewById(R.id.device_description_tv);
                eVar2.c = (ImageView) inflate.findViewById(R.id.device_description_image);
                inflate.setTag(eVar2);
                eVar = eVar2;
                view = inflate;
            } else if (view.getTag() != null && (view.getTag() instanceof e)) {
                eVar = (e) view.getTag();
            } else {
                LogUtil.h("EcologyDevice_DeviceUsageDescriptionActivity", "invalid view");
            }
            if (eVar == null) {
                LogUtil.h("EcologyDevice_DeviceUsageDescriptionActivity", "getView holder is null");
                return view;
            }
            if (!koq.b(this.c, i)) {
                eVar.d.setText(dcx.d(DeviceUsageDescriptionActivity.this.e, this.c.get(i).c()));
                if (!koq.b(DeviceUsageDescriptionActivity.this.b) && !koq.b(DeviceUsageDescriptionActivity.this.b, i)) {
                    if (this.d.get((String) DeviceUsageDescriptionActivity.this.b.get(i)) == null) {
                        String str = (String) DeviceUsageDescriptionActivity.this.b.get(i);
                        bitmap = dcx.TK_(str);
                        if (new File(str).exists() && bitmap != null) {
                            LogUtil.a("EcologyDevice_DeviceUsageDescriptionActivity", "getView cache Image");
                            this.d.put(str, bitmap);
                        }
                    } else {
                        String str2 = (String) DeviceUsageDescriptionActivity.this.b.get(i);
                        LogUtil.a("EcologyDevice_DeviceUsageDescriptionActivity", "getView load exists Image");
                        bitmap = this.d.get(str2);
                    }
                    if (bitmap == null) {
                        LogUtil.h("EcologyDevice_DeviceUsageDescriptionActivity", "getView bitmap is null");
                    } else {
                        eVar.c.setImageBitmap(bitmap);
                    }
                    eVar.c.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                    return view;
                }
                LogUtil.h("EcologyDevice_DeviceUsageDescriptionActivity", "getView mImagePathList is null or position isOutOfBounds mImagePathList");
                return view;
            }
            LogUtil.h("EcologyDevice_DeviceUsageDescriptionActivity", "getView isOutOfBounds");
            return view;
        }

        class e {
            private ImageView c;
            private HealthTextView d;

            private e() {
            }
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
