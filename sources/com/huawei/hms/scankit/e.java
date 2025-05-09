package com.huawei.hms.scankit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.hms.feature.DynamicModuleInitializer;
import com.huawei.hms.feature.dynamic.IObjectWrapper;
import com.huawei.hms.feature.dynamic.ObjectWrapper;
import com.huawei.hms.hmsscankit.DetailRect;
import com.huawei.hms.hmsscankit.api.IOnErrorCallback;
import com.huawei.hms.hmsscankit.api.IOnLightCallback;
import com.huawei.hms.hmsscankit.api.IOnResultCallback;
import com.huawei.hms.hmsscankit.api.IRemoteViewDelegate;
import com.huawei.hms.ml.scan.HmsScan;
import com.huawei.hms.scankit.p.e5;
import com.huawei.hms.scankit.p.o4;
import com.huawei.hms.scankit.p.r3;
import com.huawei.hms.scankit.p.r6;
import com.huawei.hms.scankit.p.w3;
import com.huawei.hms.scankit.p.w7;
import com.huawei.hms.scankit.util.OpencvJNI;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.watchface.videoedit.gles.Constant;
import java.util.Iterator;

/* loaded from: classes9.dex */
public class e extends IRemoteViewDelegate.Stub implements e5, SensorEventListener {
    private static final String y = "e";
    protected static boolean z = false;
    protected int b;
    protected Context c;
    protected ProviderRemoteView d;
    protected TextureView e;
    protected com.huawei.hms.scankit.b f;
    protected IOnResultCallback g;
    protected SensorManager h;
    protected View.OnClickListener i;
    protected Boolean l;
    protected AlertDialog m;
    protected Rect n;
    private IObjectWrapper o;
    protected boolean p;
    private OrientationEventListener q;
    private boolean r;
    protected boolean s;
    protected IOnLightCallback v;
    protected LinearLayout w;

    /* renamed from: a, reason: collision with root package name */
    private volatile w3 f5715a = null;
    protected boolean j = false;
    protected final Float k = Float.valueOf(40.0f);
    protected boolean t = true;
    private Point u = null;
    boolean x = false;

    class a implements View.OnTouchListener {
        a() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            e.this.f.b(motionEvent);
            return true;
        }
    }

    class b extends OrientationEventListener {
        b(Context context) {
            super(context);
        }

        @Override // android.view.OrientationEventListener
        public void onOrientationChanged(int i) {
            int rotation = ((Activity) e.this.c).getWindowManager().getDefaultDisplay().getRotation();
            boolean b = w7.b();
            boolean e = w7.e();
            if (w7.e(e.this.c) && !b) {
                e.this.a(90);
                return;
            }
            if (w7.b((Activity) e.this.c) && !e) {
                e.this.a(90);
                return;
            }
            if (rotation == 0) {
                e.this.a(90);
                return;
            }
            if (rotation == 1) {
                e.this.a(0);
            } else if (rotation == 2) {
                e.this.a(270);
            } else {
                if (rotation != 3) {
                    return;
                }
                e.this.a(180);
            }
        }
    }

    class c implements View.OnClickListener {
        c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            e.this.m.dismiss();
        }
    }

    public e(Context context, int i, Object obj, IObjectWrapper iObjectWrapper, boolean z2, boolean z3, boolean z4) {
        this.b = 0;
        this.p = false;
        this.c = context;
        this.b = i;
        this.o = iObjectWrapper;
        if (obj instanceof Rect) {
            this.n = (Rect) obj;
        } else {
            this.n = null;
        }
        this.p = z2;
        this.r = z3;
        this.s = z4;
    }

    protected void b(Point point, boolean z2) {
        int i;
        int i2;
        float f;
        float f2;
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.e.getLayoutParams();
        float f3 = point.x;
        float f4 = point.y;
        boolean b2 = w7.b();
        o4.d(y, "initSurfaceViewSize: isPortraitScreen: " + w7.c((Activity) this.c) + " inMultiWindow: " + w7.f(this.c) + " isInMultiWindowFreeform: " + w7.b((Activity) this.c) + " isPadOrFold: " + w7.j(this.c) + " isFoldStateExpand: " + w7.h(this.c) + " isPad: " + w7.i(this.c) + " inMagicWindow: " + w7.e(this.c) + " ignore: " + b2 + " screen: " + point.toString() + " width: " + layoutParams.width + " height: " + layoutParams.height + " inMagicWindow " + w7.e(this.c) + " ignore " + b2 + " isInit " + z2 + " isSpecialExpectSize " + this.f.b());
        if (w7.c((Activity) this.c) || (w7.e(this.c) && !(w7.e(this.c) && b2))) {
            z = false;
            int i3 = 1280;
            if ("ceres-c3".equals(Build.DEVICE)) {
                i = 1280;
                i2 = 1280;
            } else {
                i = 1080;
                i2 = Constant.FBO_HEIGHT;
            }
            if (z2 && (w7.f(this.c) || w7.b((Activity) this.c) || w7.e(this.c))) {
                i = 1280;
                i2 = 1280;
            }
            if (this.f.b()) {
                i2 = 1280;
            } else {
                i3 = i;
            }
            float f5 = i3;
            float f6 = f3 / f5;
            float f7 = i2;
            float f8 = f4 / f7;
            if (f6 > f8) {
                layoutParams.width = -1;
                layoutParams.height = (int) (f7 * f6);
                layoutParams.gravity = 17;
            } else {
                layoutParams.height = -1;
                layoutParams.width = (int) (f5 * f8);
                layoutParams.gravity = 17;
            }
        } else {
            z = true;
            float f9 = 1280.0f;
            if (z2 && (w7.f(this.c) || w7.b((Activity) this.c) || w7.e(this.c))) {
                f = 1280.0f;
                f2 = 1280.0f;
            } else {
                f = 1920.0f;
                f2 = 1080.0f;
            }
            if (this.f.b()) {
                f2 = 1280.0f;
            } else {
                f9 = f;
            }
            float f10 = f3 / f9;
            float f11 = f4 / f2;
            if (f10 > f11) {
                layoutParams.width = -1;
                layoutParams.height = (int) (f2 * f10);
                layoutParams.gravity = 17;
            } else {
                layoutParams.height = -1;
                layoutParams.width = (int) (f9 * f11);
                layoutParams.gravity = 17;
            }
        }
        this.e.setLayoutParams(layoutParams);
    }

    protected void c() {
        Object systemService = this.c.getSystemService("sensor");
        if (systemService instanceof SensorManager) {
            SensorManager sensorManager = (SensorManager) systemService;
            this.h = sensorManager;
            Iterator<Sensor> it = sensorManager.getSensorList(-1).iterator();
            while (it.hasNext()) {
                if (5 == it.next().getType()) {
                    this.j = true;
                    return;
                }
            }
        }
    }

    protected ProviderRemoteView d() {
        return new ProviderRemoteView(DynamicModuleInitializer.getContext() == null ? this.c : DynamicModuleInitializer.getContext(), true);
    }

    @Override // com.huawei.hms.hmsscankit.api.IRemoteViewDelegate
    public HmsScan[] decodeWithBitmap(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) throws RemoteException {
        boolean z2;
        if (!r3.A) {
            OpencvJNI.init();
        }
        Bundle bundle = (iObjectWrapper2 == null || !(ObjectWrapper.unwrap(iObjectWrapper2) instanceof Bundle)) ? new Bundle() : (Bundle) ObjectWrapper.unwrap(iObjectWrapper2);
        boolean z3 = true;
        boolean z4 = false;
        if (iObjectWrapper2 == null || !(ObjectWrapper.unwrap(iObjectWrapper2) instanceof Bundle)) {
            z2 = false;
        } else {
            z2 = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper2)).getBoolean(DetailRect.USE_APK, false);
            z4 = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper2)).getBoolean(DetailRect.SUPPORT_ROLLBACK, false);
            z3 = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper2)).getBoolean(DetailRect.PARSE_RESULT, true);
        }
        r3.f = z3;
        if (z4 && !r3.f5870a && z2) {
            return new HmsScan[]{r6.b()};
        }
        if (this.f5715a == null) {
            try {
                this.f5715a = new w3(bundle, DetailRect.PHOTO_MODE);
            } catch (RuntimeException unused) {
                o4.b(y, "RuntimeException");
            } catch (Exception unused2) {
                o4.b(y, "Exception");
            }
        }
        return a(iObjectWrapper, iObjectWrapper2);
    }

    protected void e() {
        ProviderRemoteView d = d();
        this.d = d;
        this.e = (TextureView) d.findViewById(R.id.surfaceView);
        com.huawei.hms.scankit.b bVar = new com.huawei.hms.scankit.b(this.c, this.e, null, this.n, this.b, this.o, this.p, "CustomizedView", true);
        this.f = bVar;
        bVar.b(this.s);
        c();
        a((Point) null, true);
    }

    protected void f() {
        try {
            com.huawei.hms.scankit.b bVar = this.f;
            if (bVar == null || bVar.a() == null) {
                return;
            }
            this.f.a().a("off");
        } catch (RuntimeException unused) {
            o4.b(y, "offFlashRuntimeException");
        } catch (Exception unused2) {
            o4.b(y, "offFlashException");
        }
    }

    public void g() {
        try {
            com.huawei.hms.scankit.b bVar = this.f;
            if (bVar == null || bVar.a() == null) {
                return;
            }
            this.f.a().a("torch");
        } catch (RuntimeException unused) {
            o4.b(y, "openFlashRuntimeException");
        } catch (Exception unused2) {
            o4.b(y, "openFlashException");
        }
    }

    @Override // com.huawei.hms.hmsscankit.api.IRemoteViewDelegate
    public boolean getLightStatus() throws RemoteException {
        return b();
    }

    @Override // com.huawei.hms.hmsscankit.api.IRemoteViewDelegate
    public IObjectWrapper getView() {
        return ObjectWrapper.wrap(this.d);
    }

    protected void h() {
        AlertDialog create = new AlertDialog.Builder(this.c).create();
        this.m = create;
        create.show();
        View inflate = LayoutInflater.from(DynamicModuleInitializer.getContext() == null ? this.c : DynamicModuleInitializer.getContext()).inflate(R.layout.scankit_dialog_layout, (ViewGroup) null);
        Window window = this.m.getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.y = 60;
        window.setAttributes(attributes);
        window.setBackgroundDrawable(new ColorDrawable(0));
        window.setContentView(inflate);
        window.setGravity(80);
        inflate.findViewById(R.id.dialog_sure_btn).setOnClickListener(new c());
    }

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    @Override // com.huawei.hms.hmsscankit.api.IRemoteViewDelegate
    public void onCreate(Bundle bundle) {
        Context context = this.c;
        if ((context instanceof Activity) && ((Activity) context).getWindow() != null) {
            ((Activity) this.c).getWindow().setFlags(16777216, 16777216);
        }
        Context context2 = this.c;
        if (context2 != null && context2.getPackageManager() != null) {
            this.t = this.c.getPackageManager().hasSystemFeature("android.hardware.camera.flash");
            o4.d("Scankit", "initlight hasFlash " + this.t);
        }
        e();
        this.f.a(this);
        this.d.setOnTouchListener(new a());
        IOnResultCallback iOnResultCallback = this.g;
        if (iOnResultCallback != null) {
            this.f.a(iOnResultCallback);
        }
        this.f.a(this.r);
        this.f.c();
        b bVar = new b(this.c);
        this.q = bVar;
        if (bVar.canDetectOrientation()) {
            this.q.enable();
        } else {
            this.q.disable();
        }
    }

    @Override // com.huawei.hms.hmsscankit.api.IRemoteViewDelegate
    public void onDestroy() {
        try {
            this.f.d();
            OrientationEventListener orientationEventListener = this.q;
            if (orientationEventListener != null && orientationEventListener.canDetectOrientation()) {
                this.q.disable();
            }
            if (this.c != null) {
                this.c = null;
            }
            AlertDialog alertDialog = this.m;
            if (alertDialog == null || !alertDialog.isShowing()) {
                return;
            }
            this.m.dismiss();
            this.m = null;
        } catch (RuntimeException unused) {
            o4.b(y, "onDestroyRuntimeException");
        } catch (Exception unused2) {
            o4.b(y, "onDestroyException");
        }
    }

    @Override // com.huawei.hms.hmsscankit.api.IRemoteViewDelegate
    public void onPause() {
        try {
            this.f.e();
            this.h.unregisterListener(this);
        } catch (RuntimeException unused) {
            o4.b(y, "onPauseRuntimeException");
        } catch (Exception unused2) {
            o4.b(y, "onPauseException");
        }
    }

    @Override // com.huawei.hms.hmsscankit.api.IRemoteViewDelegate
    public void onResume() {
        try {
            this.f.f();
            SensorManager sensorManager = this.h;
            sensorManager.registerListener(this, sensorManager.getDefaultSensor(5), 2);
        } catch (RuntimeException unused) {
            o4.b(y, "onResumeRuntimeException");
        } catch (Exception unused2) {
            o4.b(y, "onResumeException");
        }
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (this.j && sensorEvent.sensor.getType() == 5 && this.t) {
            Boolean valueOf = Boolean.valueOf(sensorEvent.values[0] > this.k.floatValue());
            this.l = valueOf;
            if (valueOf.booleanValue()) {
                if (sensorEvent.values[0] > 600.0f) {
                    if (this.w != null && !b()) {
                        this.w.setVisibility(8);
                    }
                    IOnLightCallback iOnLightCallback = this.v;
                    if (iOnLightCallback != null) {
                        try {
                            iOnLightCallback.onVisibleChanged(false);
                            return;
                        } catch (RemoteException unused) {
                            o4.e(y, "onSensorChanged RemoteException");
                            return;
                        }
                    }
                    return;
                }
                return;
            }
            if (w7.a(this.c)) {
                if (this.w != null) {
                    o4.d("Scankit", "initlight onSensorChanged open");
                    this.w.setVisibility(0);
                }
                IOnLightCallback iOnLightCallback2 = this.v;
                if (iOnLightCallback2 != null) {
                    try {
                        iOnLightCallback2.onVisibleChanged(true);
                    } catch (RemoteException unused2) {
                        o4.e(y, "onSensorChanged RemoteException");
                    }
                }
            }
        }
    }

    @Override // com.huawei.hms.hmsscankit.api.IRemoteViewDelegate
    public void onStart() {
        try {
            this.f.g();
        } catch (RuntimeException unused) {
            o4.b(y, "onStartRuntimeException");
        } catch (Exception unused2) {
            o4.b(y, "onStartException");
        }
    }

    @Override // com.huawei.hms.hmsscankit.api.IRemoteViewDelegate
    public void onStop() {
        try {
            this.f.h();
        } catch (RuntimeException unused) {
            o4.b(y, "onStopRuntimeException");
        } catch (Exception unused2) {
            o4.b(y, "onStopException");
        }
    }

    @Override // com.huawei.hms.hmsscankit.api.IRemoteViewDelegate
    public void pauseContinuouslyScan() throws RemoteException {
        com.huawei.hms.scankit.b bVar = this.f;
        if (bVar != null) {
            bVar.i();
        }
        this.x = true;
    }

    @Override // com.huawei.hms.hmsscankit.api.IRemoteViewDelegate
    public void resumeContinuouslyScan() throws RemoteException {
        this.x = false;
    }

    @Override // com.huawei.hms.hmsscankit.api.IRemoteViewDelegate
    public void setOnClickListener(IObjectWrapper iObjectWrapper) {
        if (iObjectWrapper != null) {
            this.i = (View.OnClickListener) ObjectWrapper.unwrap(iObjectWrapper);
        }
    }

    @Override // com.huawei.hms.hmsscankit.api.IRemoteViewDelegate
    public void setOnErrorCallback(IOnErrorCallback iOnErrorCallback) throws RemoteException {
        com.huawei.hms.scankit.b bVar = this.f;
        if (bVar != null) {
            bVar.a(iOnErrorCallback);
        }
    }

    @Override // com.huawei.hms.hmsscankit.api.IRemoteViewDelegate
    public void setOnLightVisbleCallBack(IOnLightCallback iOnLightCallback) throws RemoteException {
        this.v = iOnLightCallback;
    }

    @Override // com.huawei.hms.hmsscankit.api.IRemoteViewDelegate
    public void setOnResultCallback(IOnResultCallback iOnResultCallback) {
        this.g = iOnResultCallback;
        com.huawei.hms.scankit.b bVar = this.f;
        if (bVar != null) {
            bVar.a(iOnResultCallback);
        }
    }

    @Override // com.huawei.hms.hmsscankit.api.IRemoteViewDelegate
    public void turnOffLight() throws RemoteException {
        f();
    }

    @Override // com.huawei.hms.hmsscankit.api.IRemoteViewDelegate
    public void turnOnLight() throws RemoteException {
        g();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(int i) {
        com.huawei.hms.scankit.b bVar = this.f;
        if (bVar == null || bVar.a() == null) {
            return;
        }
        try {
            Point a2 = a(this.c);
            if (i != this.f.a().d()) {
                this.f.a().b(i);
            }
            if (this.f.a().i()) {
                Point point = this.u;
                if (point == null || point.x != a2.x) {
                    a(a2, false);
                }
            }
        } catch (NullPointerException unused) {
            o4.e(y, "adjustCameraOrientation: nullpoint");
        } catch (Exception unused2) {
            o4.e(y, "adjustCameraOrientation: Exception");
        }
    }

    private static Point a(Context context) {
        Display defaultDisplay = ((WindowManager) context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay();
        Point point = new Point();
        if (!w7.f(context) && !w7.e(context)) {
            defaultDisplay.getRealSize(point);
        } else {
            Log.i(y, "initSurfaceView: is in MultiWindowMode");
            defaultDisplay.getSize(point);
        }
        return point;
    }

    protected void a(Point point, boolean z2) {
        try {
            if (this.c.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR) != null) {
                if (point == null) {
                    point = a(this.c);
                }
                this.u = point;
                b(point, z2);
            }
        } catch (NullPointerException unused) {
            o4.e(y, "initSurfaceView: nullpoint");
        } catch (Exception unused2) {
            o4.e(y, "initSurfaceView: Exception");
        }
    }

    @Override // com.huawei.hms.scankit.p.e5
    public boolean a(HmsScan[] hmsScanArr) {
        AlertDialog alertDialog;
        if (hmsScanArr == null || hmsScanArr.length <= 0 || (alertDialog = this.m) == null || !alertDialog.isShowing()) {
            return false;
        }
        this.m.dismiss();
        return false;
    }

    @Override // com.huawei.hms.scankit.p.e5
    public boolean a() {
        return this.x;
    }

    private HmsScan[] a(IObjectWrapper iObjectWrapper, IObjectWrapper iObjectWrapper2) {
        boolean z2;
        int i;
        if (iObjectWrapper == null) {
            o4.b("ScankitRemoteS", "bitmap is null");
            return new HmsScan[0];
        }
        if (iObjectWrapper2 == null || !(ObjectWrapper.unwrap(iObjectWrapper2) instanceof Bundle)) {
            z2 = false;
            i = 0;
        } else {
            i = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper2)).getInt(DetailRect.FORMAT_FLAG);
            int i2 = ((Bundle) ObjectWrapper.unwrap(iObjectWrapper2)).getInt(DetailRect.TYPE_TRANS, 0);
            DetailRect.HMSSCAN_SDK_VALUE = i2;
            z2 = i2 >= 2;
            if (z2) {
                i = w7.b(i);
            }
        }
        HmsScan[] b2 = r6.a().b((Bitmap) ObjectWrapper.unwrap(iObjectWrapper), i, true, this.f5715a);
        if (!z2) {
            b2 = w7.a(b2);
        }
        if (b2.length == 0) {
            h();
        } else {
            HmsScan hmsScan = b2[0];
            if (hmsScan != null && TextUtils.isEmpty(hmsScan.originalValue)) {
                h();
            }
        }
        return b2;
    }

    protected boolean b() {
        try {
            return this.f.a().h().equals("torch");
        } catch (RuntimeException unused) {
            o4.b(y, "getFlashStatusRuntimeException");
            return false;
        } catch (Exception unused2) {
            o4.b(y, "getFlashStatusException");
            return false;
        }
    }
}
