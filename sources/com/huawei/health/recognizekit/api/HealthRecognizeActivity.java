package com.huawei.health.recognizekit.api;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.SurfaceTexture;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.bundle.AppBundleLauncher;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.haf.router.AppRouterUtils;
import com.huawei.haf.router.routes.AppRoute$$Info$$HuaweiHealth;
import com.huawei.health.R;
import com.huawei.health.h5pro.core.H5ProLaunchOption;
import com.huawei.health.h5pro.core.H5ProWebView;
import com.huawei.health.recognizekit.api.HealthRecognizeActivity;
import com.huawei.health.recognizekit.base.RecognizeBiApi;
import com.huawei.health.recognizekit.h5.RecognizeH5Helper;
import com.huawei.health.recognizekit.impl.RecognizeHelper;
import com.huawei.health.recognizekit.view.CenterLayoutManager;
import com.huawei.health.recognizekit.view.OverLayerTopView;
import com.huawei.health.recognizekit.view.RecognizeModeRecyclerAdapter;
import com.huawei.health.recognizekit.view.TabItemDecoration;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hms.mlsdk.asr.MLAsrConstants;
import com.huawei.hwcommonmodel.camera.CameraStateListener;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwdevice.mainprocess.mgr.hwmusiccontrolmgr.datatype.MusicSong;
import com.huawei.openalliance.ad.constant.ParamConstants;
import com.huawei.ui.commonui.R$drawable;
import com.huawei.ui.commonui.R$string;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.bubblelayout.HealthBubbleLayout;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomAlertDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import defpackage.bzs;
import defpackage.fau;
import defpackage.faz;
import defpackage.fba;
import defpackage.fbc;
import defpackage.fbd;
import defpackage.fbf;
import defpackage.ixx;
import defpackage.jcn;
import defpackage.mph;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.LanguageUtil;
import health.compact.a.LogUtil;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPreferenceManager;
import java.io.FileNotFoundException;
import java.lang.ref.WeakReference;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes.dex */
public class HealthRecognizeActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private RelativeLayout f2956a;
    private OverLayerTopView aa;
    private final fbf ab;
    private RecognizeHelper ac;
    private final RecognizeHelper.RecognizeListener ad;
    private RecognizeModeRecyclerAdapter ae;
    private final fbd af;
    private final faz ag;
    private LinearLayout ah;
    private final RecognizeHelper.RecognizeListener ai;
    private HealthRecycleView aj;
    private RectF ak;
    private TextureView al;
    private HealthBubbleLayout am;
    private HealthTextView an;
    private H5ProWebView ao;
    private HealthTextView ap;
    private HealthTextView ar;
    private HealthTextView as;
    private int b;
    private jcn e;
    private boolean h;
    private boolean i;
    private ImageView k;
    private ImageView l;
    private ImageView m;
    private ImageView n;
    private ImageView o;
    private int p;
    private int q;
    private ImageView r;
    private int s;
    private Context t;
    private int u;
    private int v;
    private HealthBubbleLayout w;
    private RecognizeBiApi x;
    private final fau z;
    private boolean g = false;
    private boolean j = false;
    private boolean f = false;
    private final TextureView.SurfaceTextureListener y = new TextureView.SurfaceTextureListener() { // from class: com.huawei.health.recognizekit.api.HealthRecognizeActivity.5
        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            LogUtil.c("R_HealthRecognizeActivity", "onSurfaceTextureAvailable, openCamera... width=", Integer.valueOf(i), " height=", Integer.valueOf(i2));
            HealthRecognizeActivity.this.g();
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            LogUtil.c("R_HealthRecognizeActivity", "onSurfaceTextureSizeChanged, width=", Integer.valueOf(i), " height=", Integer.valueOf(i2));
            if (HealthRecognizeActivity.this.e != null) {
                HealthRecognizeActivity.this.e.a(i, i2);
            }
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            LogUtil.c("R_HealthRecognizeActivity", "onSurfaceTextureDestroyed");
            return true;
        }
    };
    private final CameraStateListener c = new AnonymousClass3();
    private final RecognizeH5Helper.IH5Wrapper d = new AnonymousClass1();

    public HealthRecognizeActivity() {
        RecognizeHelper.RecognizeListener recognizeListener = new RecognizeHelper.RecognizeListener() { // from class: fad
            @Override // com.huawei.health.recognizekit.impl.RecognizeHelper.RecognizeListener
            public final void onRecognized(String str) {
                HealthRecognizeActivity.this.d(str);
            }
        };
        this.ad = recognizeListener;
        RecognizeHelper.RecognizeListener recognizeListener2 = new RecognizeHelper.RecognizeListener() { // from class: faj
            @Override // com.huawei.health.recognizekit.impl.RecognizeHelper.RecognizeListener
            public final void onRecognized(String str) {
                HealthRecognizeActivity.this.e(str);
            }
        };
        this.ai = recognizeListener2;
        this.z = new fau(recognizeListener);
        this.ag = new faz(recognizeListener2);
        this.ab = new fbf();
        this.af = new fbd();
    }

    /* renamed from: com.huawei.health.recognizekit.api.HealthRecognizeActivity$3, reason: invalid class name */
    public class AnonymousClass3 implements CameraStateListener {
        AnonymousClass3() {
        }

        @Override // com.huawei.hwcommonmodel.camera.CameraStateListener
        public void onPreviewFrame(Image image, int i) {
            if (image == null) {
                LogUtil.e("R_HealthRecognizeActivity", "image is null");
                return;
            }
            if (image.getPlanes() == null) {
                LogUtil.e("R_HealthRecognizeActivity", "image plane is null");
                return;
            }
            LogUtil.c("R_HealthRecognizeActivity", "frame captured, orientationAngle: " + i);
            ByteBuffer buffer = image.getPlanes()[0].getBuffer();
            int remaining = buffer.remaining();
            byte[] bArr = new byte[remaining];
            buffer.get(bArr);
            final Bitmap decodeByteArray = BitmapFactory.decodeByteArray(bArr, 0, remaining, null);
            if (i != 0) {
                decodeByteArray = fbc.avg_(decodeByteArray, i);
            }
            if (HealthRecognizeActivity.this.p == 1) {
                ((faz) HealthRecognizeActivity.this.ac).auQ_(HealthRecognizeActivity.this.aa.getCenterRect());
            }
            HealthRecognizeActivity.this.g = false;
            HealthRecognizeActivity.this.runOnUiThread(new Runnable() { // from class: fap
                @Override // java.lang.Runnable
                public final void run() {
                    HealthRecognizeActivity.AnonymousClass3.this.auI_(decodeByteArray);
                }
            });
            image.close();
        }

        public /* synthetic */ void auI_(Bitmap bitmap) {
            HealthRecognizeActivity.this.auy_(bitmap);
        }

        @Override // com.huawei.hwcommonmodel.camera.CameraStateListener
        public void onCameraOpened(int i) {
            LogUtil.c("R_HealthRecognizeActivity", "cameraOpened, sensorOrientation: " + i);
        }

        @Override // com.huawei.hwcommonmodel.camera.CameraStateListener
        public void onCameraError(String str) {
            LogUtil.a("R_HealthRecognizeActivity", "onCameraError: " + str);
        }
    }

    /* renamed from: com.huawei.health.recognizekit.api.HealthRecognizeActivity$1, reason: invalid class name */
    public class AnonymousClass1 implements RecognizeH5Helper.IH5Wrapper {
        AnonymousClass1() {
        }

        @Override // com.huawei.health.recognizekit.h5.RecognizeH5Helper.IH5Wrapper
        public void inputManual() {
            LogUtil.c("R_HealthRecognizeActivity", "abandon shooting pic, finishing...");
            HealthRecognizeActivity.this.finish();
        }

        @Override // com.huawei.health.recognizekit.h5.RecognizeH5Helper.IH5Wrapper
        public void reShooting() {
            LogUtil.c("R_HealthRecognizeActivity", "dismiss fail dialog, shooting again...");
            HealthRecognizeActivity.this.runOnUiThread(new Runnable() { // from class: fan
                @Override // java.lang.Runnable
                public final void run() {
                    HealthRecognizeActivity.AnonymousClass1.this.c();
                }
            });
        }

        public /* synthetic */ void c() {
            HealthRecognizeActivity.this.d(true);
        }

        @Override // com.huawei.health.recognizekit.h5.RecognizeH5Helper.IH5Wrapper
        public void finish() {
            LogUtil.c("R_HealthRecognizeActivity", "finishing shooting page...");
            HealthRecognizeActivity.this.finish();
        }
    }

    public /* synthetic */ void d(final String str) {
        runOnUiThread(new Runnable() { // from class: fah
            @Override // java.lang.Runnable
            public final void run() {
                HealthRecognizeActivity.this.c(str);
            }
        });
    }

    public /* synthetic */ void c(String str) {
        if (str == null || str.equals("recognize_failed")) {
            LogUtil.a("R_HealthRecognizeActivity", "recognize failed, load embedded H5 dialog");
            this.x.onRecognizeResultEvent(this.g, false);
            k();
            if (!this.g) {
                this.e.j();
            }
            o();
            this.g = false;
            return;
        }
        this.x.onRecognizeResultEvent(this.g, true);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("recognizeResult", str);
            jSONObject.put("picSource", this.g ? MusicSong.SORT_TYPE_ALBUM : "camera");
        } catch (JSONException e2) {
            LogUtil.e("R_HealthRecognizeActivity", e2);
        }
        String jSONObject2 = jSONObject.toString();
        LogUtil.c("R_HealthRecognizeActivity", "recognize success, finishing recognize activity, result: " + jSONObject2);
        if (this.f) {
            setResult(-1, new Intent().putExtra("result", jSONObject2));
            finish();
        } else {
            d(true);
            b(jSONObject2);
        }
        this.g = false;
    }

    public /* synthetic */ void e(final String str) {
        runOnUiThread(new Runnable() { // from class: fag
            @Override // java.lang.Runnable
            public final void run() {
                HealthRecognizeActivity.this.a(str);
            }
        });
    }

    public /* synthetic */ void a(String str) {
        this.aa.e();
        if (!this.g) {
            this.e.j();
        }
        if (!fba.b(str)) {
            LogUtil.c("R_HealthRecognizeActivity", "nutrition recognize fail");
            t();
        } else {
            LogUtil.c("R_HealthRecognizeActivity", "nutrition recognize success");
            fba.a(this, str);
            finish();
        }
        this.g = false;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        requestWindowFeature(1);
        super.onCreate(bundle);
        setContentView(R.layout.activity_scan_kit);
        this.f = getIntent().getBooleanExtra("isForResult", false);
        ReleaseLogUtil.b("R_HealthRecognizeActivity", "will send result via onActivityResult: " + this.f);
        if (this.f) {
            this.b = getIntent().getIntExtra("from", 1);
        } else {
            Uri zs_ = AppRouterUtils.zs_(getIntent());
            if (zs_ != null) {
                this.b = CommonUtil.e(zs_.getQueryParameter("from"), 1);
            }
        }
        c();
        a();
        RecognizeH5Helper.a(this.d);
    }

    private void c() {
        this.al = (TextureView) findViewById(R.id.id_texture_camera);
        this.e = new jcn(this, this.al, this.c, false, 256);
        ImageView imageView = (ImageView) findViewById(R.id.id_back_arrow);
        this.k = imageView;
        imageView.setImageResource(LanguageUtil.bc(this) ? R$drawable.ic_back_white_reverse : R$drawable.ic_back_white);
        this.o = (ImageView) findViewById(R.id.id_tip_btn);
        this.l = (ImageView) findViewById(R.id.id_shot);
        this.m = (ImageView) findViewById(R.id.id_album);
        this.r = (ImageView) findViewById(R.id.id_tips_bubble);
        this.n = (ImageView) findViewById(R.id.id_background_pic);
        this.ap = (HealthTextView) findViewById(R.id.id_title);
        this.an = (HealthTextView) findViewById(R.id.id_tips_big);
        this.as = (HealthTextView) findViewById(R.id.id_tips_small);
        this.ar = (HealthTextView) findViewById(R.id.tips_text);
        this.am = (HealthBubbleLayout) findViewById(R.id.id_bubble_container);
        RectF rectF = new RectF();
        this.ak = rectF;
        rectF.set(this.am.getLeft(), this.am.getTop(), this.am.getRight(), this.am.getBottom());
        this.w = (HealthBubbleLayout) findViewById(R.id.recognize_mode_tips);
        this.ao = (H5ProWebView) findViewById(R.id.id_scan_failed);
        this.ah = (LinearLayout) findViewById(R.id.recognize_tab);
        this.aa = (OverLayerTopView) findViewById(R.id.id_overLayerTopView);
        this.t = this;
        e();
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.id_title_bar_container);
        int r = nsn.r(this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(0, r, 0, 0);
        relativeLayout.setLayoutParams(layoutParams);
        h();
        boolean a2 = SharedPreferenceManager.a(AppRoute$$Info$$HuaweiHealth.M_31, "isFirstEnterRecognize", true);
        this.i = a2;
        if (a2) {
            SharedPreferenceManager.e(AppRoute$$Info$$HuaweiHealth.M_31, "isFirstEnterRecognize", false);
            n();
        } else {
            f();
        }
    }

    private void n() {
        this.m.setVisibility(8);
        this.l.setVisibility(8);
        this.ah.setVisibility(8);
        ((ViewStub) findViewById(R.id.recognize_guide_view_stub)).inflate();
        this.f2956a = (RelativeLayout) findViewById(R.id.recognize_guide_relativeLayout);
        ((CustomTitleBar) findViewById(R.id.recognize_guide_title_bar)).setLeftButtonOnClickListener(new View.OnClickListener() { // from class: fac
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HealthRecognizeActivity.this.auE_(view);
            }
        });
        ((HealthButton) findViewById(R.id.recognize_guide_start_button)).setOnClickListener(new c(this));
        b();
    }

    public /* synthetic */ void auE_(View view) {
        finish();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (!e(PermissionUtil.PermissionType.CAMERA)) {
            d(PermissionUtil.PermissionType.CAMERA);
        } else {
            this.x.onEnterEvent(this.b);
            l();
        }
    }

    private void e() {
        HealthRecycleView healthRecycleView = (HealthRecycleView) findViewById(R.id.recognize_tab_recycleView);
        this.aj = healthRecycleView;
        healthRecycleView.setIsScroll(false);
        this.aj.addItemDecoration(new TabItemDecoration());
        new LinearSnapHelper().attachToRecyclerView(this.aj);
        this.aj.setLayoutManager(new CenterLayoutManager(this, 0, false));
        ArrayList arrayList = new ArrayList();
        arrayList.add(getString(R.string._2130850380_res_0x7f02324c));
        arrayList.add(getString(R.string._2130850381_res_0x7f02324d));
        RecognizeModeRecyclerAdapter recognizeModeRecyclerAdapter = new RecognizeModeRecyclerAdapter(arrayList);
        this.ae = recognizeModeRecyclerAdapter;
        this.aj.setAdapter(recognizeModeRecyclerAdapter);
        this.ae.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() { // from class: fab
            @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter.OnItemClickListener
            public final void onItemClicked(RecyclerHolder recyclerHolder, int i, Object obj) {
                HealthRecognizeActivity.this.e(recyclerHolder, i, obj);
            }
        });
        this.aj.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.huawei.health.recognizekit.api.HealthRecognizeActivity.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                if (i == 0 && HealthRecognizeActivity.this.h) {
                    LogUtil.c("R_HealthRecognizeActivity", "tab scroll finish");
                    HealthRecognizeActivity.this.h = false;
                    int n = nsn.n() / 2;
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) HealthRecognizeActivity.this.aj.getLayoutManager();
                    int findLastCompletelyVisibleItemPosition = (linearLayoutManager.findLastCompletelyVisibleItemPosition() - linearLayoutManager.findFirstCompletelyVisibleItemPosition()) / 2;
                    int min = Math.min(findLastCompletelyVisibleItemPosition + 1, HealthRecognizeActivity.this.ae.getItemCount() - 1);
                    for (int max = Math.max(0, findLastCompletelyVisibleItemPosition - 1); max <= min; max++) {
                        View findViewByPosition = HealthRecognizeActivity.this.aj.getLayoutManager().findViewByPosition(max);
                        if (n == findViewByPosition.getLeft() + (findViewByPosition.getWidth() / 2)) {
                            HealthRecognizeActivity.this.e(max);
                        }
                    }
                }
            }
        });
        this.aj.setOnTouchListener(new View.OnTouchListener() { // from class: fal
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return HealthRecognizeActivity.this.auD_(view, motionEvent);
            }
        });
    }

    public /* synthetic */ void e(RecyclerHolder recyclerHolder, int i, Object obj) {
        LogUtil.c("R_HealthRecognizeActivity", "tab onClick");
        if (this.p == i) {
            return;
        }
        e(i);
    }

    public /* synthetic */ boolean auD_(View view, MotionEvent motionEvent) {
        this.h = true;
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e(final int i) {
        if (i == 0) {
            this.p = i;
            h();
            i();
        } else if (i == 1) {
            if (mph.b().isPluginAvaiable()) {
                this.p = i;
                m();
                this.x.onEnterEvent(this.b);
                i();
                return;
            }
            i();
            mph.b().launchActivity(BaseApplication.e(), new Intent(), new AppBundleLauncher.InstallCallback() { // from class: faf
                @Override // com.huawei.haf.bundle.AppBundleLauncher.InstallCallback
                public final boolean call(Context context, Intent intent) {
                    return HealthRecognizeActivity.this.auH_(i, context, intent);
                }
            });
        }
    }

    public /* synthetic */ boolean auH_(final int i, Context context, Intent intent) {
        mph.b().a(null);
        runOnUiThread(new Runnable() { // from class: fak
            @Override // java.lang.Runnable
            public final void run() {
                HealthRecognizeActivity.this.d(i);
            }
        });
        return false;
    }

    public /* synthetic */ void d(int i) {
        this.p = i;
        m();
        this.x.onEnterEvent(this.b);
        i();
    }

    private void i() {
        this.ae.b(this.p);
        this.ae.notifyDataSetChanged();
        this.aj.smoothScrollToPosition(this.p);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        LogUtil.c("R_HealthRecognizeActivity", "onResume");
        LogUtil.c("R_HealthRecognizeActivity", "isFromAlbum:", Boolean.valueOf(this.g), ", isFirstEnter:", Boolean.valueOf(this.i));
        if (!this.g && !this.i) {
            d(false);
        }
        Intent intent = getIntent();
        if (intent != null) {
            String stringExtra = intent.getStringExtra("ocrResult");
            if (!TextUtils.isEmpty(stringExtra) && stringExtra.equals("default")) {
                LogUtil.c("R_HealthRecognizeActivity", "onResume recognize album img fail");
                m();
                this.p = 1;
                i();
                t();
            }
        }
        if (e(PermissionUtil.PermissionType.CAMERA)) {
            if (this.al.isAvailable()) {
                LogUtil.c("R_HealthRecognizeActivity", "texture view is available, open camera...");
                g();
                return;
            } else {
                this.al.setSurfaceTextureListener(this.y);
                return;
            }
        }
        LogUtil.c("R_HealthRecognizeActivity", "onResume without camera permission, will not open camera...");
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (this.ao.getVisibility() == 0) {
            d(true);
        } else {
            super.onBackPressed();
        }
    }

    private void a() {
        this.k.setOnClickListener(new View.OnClickListener() { // from class: fai
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HealthRecognizeActivity.this.auC_(view);
            }
        });
        this.o.setOnClickListener(new View.OnClickListener() { // from class: faq
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HealthRecognizeActivity.this.auz_(view);
            }
        });
        this.l.setOnClickListener(new View.OnClickListener() { // from class: ezz
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HealthRecognizeActivity.this.auA_(view);
            }
        });
        this.m.setOnClickListener(new View.OnClickListener() { // from class: faa
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HealthRecognizeActivity.this.auB_(view);
            }
        });
    }

    public /* synthetic */ void auC_(View view) {
        if (this.ao.getVisibility() == 0) {
            d(true);
        } else {
            finish();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void auz_(View view) {
        if (this.am.getVisibility() == 8) {
            this.am.setVisibility(0);
            this.as.setVisibility(8);
            this.x.onTipsEvent();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void auA_(View view) {
        if (!e(PermissionUtil.PermissionType.CAMERA)) {
            d(PermissionUtil.PermissionType.CAMERA);
            ViewClickInstrumentation.clickOnView(view);
        } else {
            auw_(null);
            fbc.a(this);
            this.e.d();
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    public /* synthetic */ void auB_(View view) {
        d();
        ViewClickInstrumentation.clickOnView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void auy_(Bitmap bitmap) {
        LogUtil.c("R_HealthRecognizeActivity", "start recognizing...");
        auw_(bitmap);
        this.ac.recognize(bitmap);
        this.x.onRecognizeEvent(this.g);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(boolean z) {
        LogUtil.c("R_HealthRecognizeActivity", "setUIToStandbyMod");
        this.al.setVisibility(0);
        this.ap.setVisibility(0);
        this.o.setVisibility(0);
        this.ah.setVisibility(0);
        this.m.setVisibility(0);
        this.l.setVisibility(0);
        this.an.setVisibility(8);
        this.as.setVisibility(0);
        this.as.setText(this.u);
        this.am.setVisibility(8);
        this.ao.setVisibility(8);
        this.n.setVisibility(8);
        if (z) {
            this.e.j();
        }
    }

    private void auw_(Bitmap bitmap) {
        this.an.setVisibility(0);
        this.as.setVisibility(0);
        this.as.setText(this.v);
        if (this.g && bitmap != null) {
            this.n.setVisibility(0);
            this.al.setVisibility(8);
            this.n.setImageBitmap(bitmap);
        }
        this.ah.setVisibility(8);
        this.ap.setVisibility(8);
        this.o.setVisibility(8);
        this.m.setVisibility(8);
        this.l.setVisibility(8);
        this.aa.b();
    }

    private void o() {
        this.ap.setVisibility(0);
        this.o.setVisibility(8);
        this.an.setVisibility(8);
        this.as.setVisibility(8);
        this.n.setVisibility(8);
        this.al.setVisibility(0);
        this.aa.e();
    }

    private void h() {
        this.ap.setText(R.string._2130850382_res_0x7f02324e);
        this.r.setImageResource(R.drawable._2131431162_res_0x7f0b0efa);
        this.ar.setText(R.string._2130850373_res_0x7f023245);
        this.as.setText(R.string._2130850373_res_0x7f023245);
        this.u = R.string._2130850373_res_0x7f023245;
        this.v = R.string._2130850377_res_0x7f023249;
        this.aa.e(false);
        this.ac = this.z;
        this.x = this.ab;
    }

    private void m() {
        j();
        this.ap.setText(R.string._2130845471_res_0x7f021f1f);
        this.r.setImageResource(this.s);
        this.ar.setText(R.string._2130845472_res_0x7f021f20);
        this.as.setText(R.string._2130845472_res_0x7f021f20);
        this.u = R.string._2130845472_res_0x7f021f20;
        this.v = R.string._2130845474_res_0x7f021f22;
        this.aa.e(true);
        this.ac = this.ag;
        this.x = this.af;
    }

    private void k() {
        if (!this.j) {
            StringBuilder sb = new StringBuilder("#/photoRecoFailed?from=");
            sb.append(this.b);
            sb.append("&scannedType=");
            sb.append(this.g ? "2" : "1");
            bzs.e().loadEmbeddedH5(this.ao, "com.huawei.health.h5.diet-diary", new H5ProLaunchOption.Builder().addPath(sb.toString()));
            this.j = true;
        }
        this.ao.setVisibility(0);
    }

    private void t() {
        o();
        View inflate = View.inflate(this, R.layout.nutritiontable_recognize_fail_tips_dialog, null);
        HealthTextView healthTextView = (HealthTextView) inflate.findViewById(R.id.ocr_recognize_fail_dialog_tips);
        ((ImageView) inflate.findViewById(R.id.ocr_recognize_fail_dialog_img)).setImageResource(this.q);
        healthTextView.setText(getString(R$string.IDS_ocr_recognize_fail_tips, new Object[]{1, 2, 3, 4}));
        CustomAlertDialog.Builder builder = new CustomAlertDialog.Builder(this);
        builder.cyp_(inflate);
        HealthButton healthButton = (HealthButton) inflate.findViewById(R.id.hw_nutrition_ocr_reshoot_bt);
        healthButton.setText(getString(R$string.IDS_ocr_reshoot_after_fail).toUpperCase());
        final CustomAlertDialog c2 = builder.c();
        c2.show();
        healthButton.setOnClickListener(new View.OnClickListener() { // from class: ezx
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HealthRecognizeActivity.this.auF_(c2, view);
            }
        });
        c2.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: fae
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                HealthRecognizeActivity.this.auG_(dialogInterface);
            }
        });
    }

    public /* synthetic */ void auF_(CustomAlertDialog customAlertDialog, View view) {
        customAlertDialog.dismiss();
        this.x.onRecognizeResultEvent(false, false);
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void auG_(DialogInterface dialogInterface) {
        d(false);
    }

    private void j() {
        char c2;
        String language = getResources().getConfiguration().locale.getLanguage();
        LogUtil.c("R_HealthRecognizeActivity", "getImgResourceIdByLanguage language=", language);
        if (TextUtils.isEmpty(language)) {
            return;
        }
        String lowerCase = language.toLowerCase(Locale.ENGLISH);
        lowerCase.hashCode();
        int hashCode = lowerCase.hashCode();
        if (hashCode == 3201) {
            if (lowerCase.equals("de")) {
                c2 = 0;
            }
            c2 = 65535;
        } else if (hashCode != 3371) {
            if (hashCode == 3886 && lowerCase.equals(MLAsrConstants.LAN_ZH)) {
                c2 = 2;
            }
            c2 = 65535;
        } else {
            if (lowerCase.equals("it")) {
                c2 = 1;
            }
            c2 = 65535;
        }
        if (c2 == 0) {
            this.s = R.drawable._2131430937_res_0x7f0b0e19;
            this.q = R.drawable._2131431164_res_0x7f0b0efc;
        } else if (c2 == 1) {
            this.s = R.drawable._2131430939_res_0x7f0b0e1b;
            this.q = R.drawable._2131431166_res_0x7f0b0efe;
        } else if (c2 == 2) {
            this.s = R.drawable._2131430936_res_0x7f0b0e18;
            this.q = R.drawable._2131431163_res_0x7f0b0efb;
        } else {
            this.s = R.drawable._2131430938_res_0x7f0b0e1a;
            this.q = R.drawable._2131431165_res_0x7f0b0efd;
        }
    }

    private void d(PermissionUtil.PermissionType permissionType) {
        LogUtil.c("R_HealthRecognizeActivity", "permission not granted, requesting: " + permissionType);
        PermissionUtil.b(this, permissionType, new e(permissionType));
    }

    /* loaded from: classes7.dex */
    public static class e extends CustomPermissionAction {
        private final PermissionUtil.PermissionType b;
        private final WeakReference<HealthRecognizeActivity> c;

        private e(HealthRecognizeActivity healthRecognizeActivity, PermissionUtil.PermissionType permissionType) {
            super(healthRecognizeActivity);
            this.c = new WeakReference<>(healthRecognizeActivity);
            this.b = permissionType;
        }

        @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onGranted() {
            HealthRecognizeActivity healthRecognizeActivity = this.c.get();
            if (healthRecognizeActivity == null) {
                LogUtil.a("R_HealthRecognizeActivity", "activity is null in WeakReferenceCustomPermissionAction");
                return;
            }
            if (this.b.equals(PermissionUtil.PermissionType.MEDIA_VIDEO_IMAGES)) {
                LogUtil.c("R_HealthRecognizeActivity", "storage permission granted, jump to album...");
                healthRecognizeActivity.d();
            } else if (this.b.equals(PermissionUtil.PermissionType.CAMERA)) {
                healthRecognizeActivity.x.onEnterEvent(healthRecognizeActivity.b);
                healthRecognizeActivity.l();
                LogUtil.c("R_HealthRecognizeActivity", "camera permission granted, do nothing...");
            }
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onDenied(String str) {
            super.onDenied(str);
            if (this.b.equals(PermissionUtil.PermissionType.MEDIA_VIDEO_IMAGES)) {
                LogUtil.c("R_HealthRecognizeActivity", "storage permission denied...");
            } else if (this.b.equals(PermissionUtil.PermissionType.CAMERA)) {
                LogUtil.c("R_HealthRecognizeActivity", "camera permission denied...");
            }
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
            HealthRecognizeActivity healthRecognizeActivity = this.c.get();
            if (healthRecognizeActivity == null) {
                LogUtil.a("R_HealthRecognizeActivity", "activity is null in WeakReferenceCustomPermissionAction");
                return;
            }
            if (permissionType.equals(PermissionUtil.PermissionType.MEDIA_VIDEO_IMAGES)) {
                LogUtil.c("R_HealthRecognizeActivity", "storage permission FOREVER denied, show guide dialog");
                nsn.e(healthRecognizeActivity, permissionType);
            } else if (permissionType.equals(PermissionUtil.PermissionType.CAMERA)) {
                LogUtil.c("R_HealthRecognizeActivity", "camera permission FOREVER denied, show guide dialog");
                View.OnClickListener onClickListener = new View.OnClickListener() { // from class: fao
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        HealthRecognizeActivity.e.auJ_(view);
                    }
                };
                nsn.cLJ_(healthRecognizeActivity, permissionType, onClickListener, onClickListener);
            }
        }

        public static /* synthetic */ void auJ_(View view) {
            LogUtil.a("R_HealthRecognizeActivity", "CAMERA permission forever denied, user is making choice...");
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void l() {
        if (SharedPreferenceManager.a(AppRoute$$Info$$HuaweiHealth.M_31, "isFirstShowMultiModeTips", true)) {
            this.w.setVisibility(0);
            SharedPreferenceManager.e(AppRoute$$Info$$HuaweiHealth.M_31, "isFirstShowMultiModeTips", false);
        }
    }

    private boolean e(PermissionUtil.PermissionType permissionType) {
        return PermissionUtil.e(this, permissionType) == PermissionUtil.PermissionResult.GRANTED;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (this.i) {
            return super.dispatchTouchEvent(motionEvent);
        }
        if (motionEvent.getAction() == 0) {
            if (this.w.getVisibility() == 0) {
                this.w.setVisibility(8);
            }
            if (this.am.getVisibility() == 0 && !this.ak.contains(motionEvent.getX(), motionEvent.getY())) {
                d(true);
                return true;
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d() {
        LogUtil.c("R_HealthRecognizeActivity", "goToAlbum");
        this.x.onAlbumEvent();
        nsn.cKT_(this, 20230505);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.c("R_HealthRecognizeActivity", "onActivityResult requestCode: ", Integer.valueOf(i));
        if (i == 20230505 && i2 == -1 && intent != null && intent.getData() != null) {
            Uri data = intent.getData();
            int avf_ = fbc.avf_(data);
            if (this.p == 0) {
                try {
                    Bitmap ave_ = fba.ave_(BitmapFactory.decodeStream(getContentResolver().openInputStream(data)));
                    this.g = true;
                    auy_(fbc.avg_(ave_, avf_));
                    return;
                } catch (FileNotFoundException e2) {
                    d(true);
                    LogUtil.e("R_HealthRecognizeActivity", "FileNotFoundException, " + e2);
                    return;
                }
            }
            Intent intent2 = new Intent(this, (Class<?>) NutritionTableAlbumImgCropActivity.class);
            intent2.setData(data);
            intent2.putExtra(ParamConstants.Param.ORIENTATION, avf_);
            startActivity(intent2);
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        this.e.e(this.al.getWidth(), this.al.getHeight(), true);
    }

    private void b(String str) {
        LogUtil.c("R_HealthRecognizeActivity", "enter launchDishH5");
        bzs.e().initH5Pro();
        H5ProLaunchOption.Builder builder = new H5ProLaunchOption.Builder();
        builder.showStatusBar();
        builder.setImmerse();
        builder.addCustomizeArg("scanResult", str);
        builder.addPath("#/identify?from=1");
        bzs.e().loadH5ProApp(BaseApplication.e(), "com.huawei.health.h5.diet-diary", builder);
    }

    private void b() {
        HashMap hashMap = new HashMap();
        hashMap.put("from", String.valueOf(this.b));
        hashMap.put("event", "0");
        hashMap.put("scannedType", "");
        hashMap.put("result", "");
        hashMap.put("type", "");
        ixx.d().d(BaseApplication.e(), AnalyticsValue.RECOGNIZE_FOOD_EVENT_VALUE.value(), hashMap, 0);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        jcn jcnVar = this.e;
        if (jcnVar != null) {
            jcnVar.a();
        }
        super.onPause();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        fbc.d();
        this.z.release();
        this.ag.release();
        RecognizeH5Helper.c();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity
    public void initViewTahiti() {
        super.initViewTahiti();
        LogUtil.c("R_HealthRecognizeActivity", "enter initViewTahiti");
        RecognizeModeRecyclerAdapter recognizeModeRecyclerAdapter = this.ae;
        if (recognizeModeRecyclerAdapter != null) {
            recognizeModeRecyclerAdapter.notifyDataSetChanged();
        }
        OverLayerTopView overLayerTopView = this.aa;
        if (overLayerTopView != null) {
            overLayerTopView.a();
        }
        LogUtil.c("R_HealthRecognizeActivity", "refresh center rectF");
        Point auZ_ = fba.auZ_(this.t);
        int i = auZ_.x;
        int i2 = auZ_.y;
        LogUtil.c("R_HealthRecognizeActivity", "initViewTahiti screenWidth=", Integer.valueOf(i), " screenHeight=", Integer.valueOf(i2));
        if (this.al.isAvailable()) {
            LogUtil.c("R_HealthRecognizeActivity", "initViewTahiti textureView isAvailable()");
            this.e.a();
            this.e.e(i, i2, true);
        } else {
            LogUtil.c("R_HealthRecognizeActivity", "initViewTahiti textureView setListener");
            this.al.setSurfaceTextureListener(this.y);
        }
    }

    /* loaded from: classes7.dex */
    static class c implements View.OnClickListener {

        /* renamed from: a, reason: collision with root package name */
        private final WeakReference<HealthRecognizeActivity> f2958a;

        c(HealthRecognizeActivity healthRecognizeActivity) {
            this.f2958a = new WeakReference<>(healthRecognizeActivity);
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            HealthRecognizeActivity healthRecognizeActivity = this.f2958a.get();
            if (healthRecognizeActivity != null && !nsn.o()) {
                healthRecognizeActivity.i = false;
                healthRecognizeActivity.d(false);
                healthRecognizeActivity.f2956a.setVisibility(8);
                healthRecognizeActivity.f2956a = null;
                healthRecognizeActivity.f();
                LogUtil.c("R_HealthRecognizeActivity", "guide view dismissed, going feature...");
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            LogUtil.a("R_HealthRecognizeActivity", "InnerClickListener activity is null or fast click");
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
