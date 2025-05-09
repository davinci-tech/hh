package com.huawei.hms.hmsscankit;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.os.Process;
import android.os.RemoteException;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.huawei.hms.feature.dynamic.DeferredLifecycleHelper;
import com.huawei.hms.feature.dynamic.LifecycleDelegate;
import com.huawei.hms.feature.dynamic.ObjectWrapper;
import com.huawei.hms.feature.dynamic.OnDelegateCreatedListener;
import com.huawei.hms.hmsscankit.api.IOnErrorCallback;
import com.huawei.hms.hmsscankit.api.IOnLightCallback;
import com.huawei.hms.hmsscankit.api.IOnResultCallback;
import com.huawei.hms.hmsscankit.api.IRemoteCreator;
import com.huawei.hms.hmsscankit.api.IRemoteViewDelegate;
import com.huawei.hms.ml.scan.HmsScanAnalyzerOptions;
import com.huawei.hms.ml.scan.HmsScanBase;
import com.huawei.hms.scankit.p.o4;
import com.huawei.hms.scankit.p.w7;
import com.huawei.hms.scankit.p.y3;
import com.huawei.operation.utils.Constants;
import com.huawei.phoneservice.faq.base.constants.TrackConstants$Events;

/* loaded from: classes4.dex */
public class RemoteView extends FrameLayout {
    private static final int MAX_BITMAP_SIZE = 52428800;
    public static final int REQUEST_CODE_PHOTO = 4371;
    private static final String TAG = "ScanKitRemoteView";
    private static boolean flagForGallery = false;
    private static boolean isOnStop = true;
    private Context mContext;
    private boolean mContinuouslyScan;
    private OnErrorCallback mOnErrorCallback;
    private a mRemoteHelper;
    private boolean mReturnedBitmap;

    /* loaded from: classes9.dex */
    public static class Builder {
        Activity mContext;
        HmsScanAnalyzerOptions mFormat;
        Rect mRect;
        boolean mIsCustomed = true;
        boolean mContinuouslyScan = true;
        boolean mReturnedBitmap = false;

        public RemoteView build() {
            Activity activity = this.mContext;
            boolean z = this.mIsCustomed;
            HmsScanAnalyzerOptions hmsScanAnalyzerOptions = this.mFormat;
            return new RemoteView(activity, z, hmsScanAnalyzerOptions == null ? 0 : hmsScanAnalyzerOptions.mode, this.mRect).setContinuouslyScan(this.mContinuouslyScan).enableReturnBitmap(this.mReturnedBitmap);
        }

        public Builder enableReturnBitmap() {
            this.mReturnedBitmap = true;
            return this;
        }

        public Builder setBoundingBox(Rect rect) {
            this.mRect = rect;
            return this;
        }

        public Builder setContext(Activity activity) {
            this.mContext = activity;
            return this;
        }

        public Builder setContinuouslyScan(boolean z) {
            this.mContinuouslyScan = z;
            return this;
        }

        public Builder setFormat(int i, int... iArr) {
            this.mFormat = new HmsScanAnalyzerOptions.Creator().setHmsScanTypes(i, iArr).create();
            return this;
        }
    }

    class a extends DeferredLifecycleHelper<b> {

        /* renamed from: a, reason: collision with root package name */
        private ViewGroup f4625a;
        public Activity b;
        private OnDelegateCreatedListener<b> c;
        private IRemoteViewDelegate d;
        private IOnResultCallback e = null;
        private boolean f;
        private boolean g;
        private int h;
        private IOnLightCallback i;
        private Rect j;
        private Bundle k;
        private boolean l;
        private boolean m;
        private int n;
        private boolean o;
        private boolean p;

        /* renamed from: com.huawei.hms.hmsscankit.RemoteView$a$a, reason: collision with other inner class name */
        class ViewOnClickListenerC0110a implements View.OnClickListener {
            ViewOnClickListenerC0110a() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                a aVar = a.this;
                Activity activity = aVar.b;
                if (activity != null) {
                    RemoteView.this.startPhotoCode(activity);
                }
            }
        }

        class b implements View.OnClickListener {
            b() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                a aVar = a.this;
                Activity activity = aVar.b;
                if (activity != null) {
                    RemoteView.this.startPhotoCode(activity);
                }
            }
        }

        a(Activity activity, ViewGroup viewGroup, boolean z, int i, Rect rect) {
            this.f4625a = viewGroup;
            this.b = activity;
            this.f = z;
            this.h = i;
            this.j = rect;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean g() {
            IRemoteViewDelegate iRemoteViewDelegate = this.d;
            if (iRemoteViewDelegate == null) {
                return false;
            }
            try {
                iRemoteViewDelegate.turnOnLight();
                return true;
            } catch (RemoteException unused) {
                o4.b(TrackConstants$Events.EXCEPTION, "RemoteException");
                return false;
            }
        }

        @Override // com.huawei.hms.feature.dynamic.DeferredLifecycleHelper
        public void createDelegate(OnDelegateCreatedListener<b> onDelegateCreatedListener) {
            Bundle bundle;
            IRemoteCreator d;
            this.c = onDelegateCreatedListener;
            if (onDelegateCreatedListener == null || getDelegate() != null) {
                return;
            }
            this.d = null;
            try {
                bundle = new Bundle();
                boolean z = this.f;
                if (!z && this.h == 0 && this.j == null) {
                    o4.d(RemoteView.TAG, "!mCustomed && mFormatValue == 0 && mRect == null");
                } else {
                    bundle.putBoolean(DetailRect.CUSTOMED_FLAG, z);
                    bundle.putInt(DetailRect.FORMAT_FLAG, this.h);
                    Rect rect = this.j;
                    if (rect != null) {
                        bundle.putParcelable(DetailRect.RECT_FLAG, rect);
                    }
                }
                boolean z2 = this.l;
                if (z2) {
                    bundle.putBoolean(DetailRect.SCAN_OFFSCEEN_FLAG, z2);
                }
                boolean z3 = this.g;
                if (z3) {
                    bundle.putBoolean(DetailRect.DEEPLINK_JUMP_FLAG, z3);
                    bundle.putAll(this.k);
                }
                bundle.putInt(DetailRect.TYPE_TRANS, 3);
                bundle.putBoolean(DetailRect.RETURN_BITMAP, this.m);
                bundle.putAll(y3.a(this.b));
                bundle.putBoolean(DetailRect.SCAN_NEW_UI, true);
                bundle.putInt(DetailRect.SCAN_VIEWTYPE_FLAG, this.n);
                bundle.putBoolean(DetailRect.SCAN_CAMERA_PERMISSION, this.o);
                bundle.putBoolean(HmsScanBase.SCAN_GUIDE_FLAG, this.p);
                d = g.d(this.b);
            } catch (RemoteException unused) {
                o4.b(TrackConstants$Events.EXCEPTION, "RemoteException");
            }
            if (d == null) {
                return;
            }
            this.d = d.newRemoteViewDelegate(ObjectWrapper.wrap(this.b), ObjectWrapper.wrap(bundle));
            IRemoteViewDelegate iRemoteViewDelegate = this.d;
            if (iRemoteViewDelegate == null) {
                return;
            }
            try {
                IOnResultCallback iOnResultCallback = this.e;
                if (iOnResultCallback != null) {
                    iRemoteViewDelegate.setOnResultCallback(iOnResultCallback);
                    this.d.setOnClickListener(ObjectWrapper.wrap(new ViewOnClickListenerC0110a()));
                }
                this.d.setOnClickListener(ObjectWrapper.wrap(new b()));
                IOnLightCallback iOnLightCallback = this.i;
                if (iOnLightCallback != null) {
                    this.d.setOnLightVisbleCallBack(iOnLightCallback);
                }
            } catch (RemoteException unused2) {
                o4.b(TrackConstants$Events.EXCEPTION, "RemoteException");
            }
            this.c.onDelegateCreated(new b(this.f4625a, this.d));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void d() {
            IRemoteViewDelegate iRemoteViewDelegate = this.d;
            if (iRemoteViewDelegate != null) {
                try {
                    iRemoteViewDelegate.pauseContinuouslyScan();
                } catch (RemoteException unused) {
                    o4.b(TrackConstants$Events.EXCEPTION, "RemoteException");
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void e() {
            IRemoteViewDelegate iRemoteViewDelegate = this.d;
            if (iRemoteViewDelegate != null) {
                try {
                    iRemoteViewDelegate.resumeContinuouslyScan();
                } catch (RemoteException unused) {
                    o4.b(TrackConstants$Events.EXCEPTION, "RemoteException");
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean f() {
            IRemoteViewDelegate iRemoteViewDelegate = this.d;
            if (iRemoteViewDelegate == null) {
                return false;
            }
            try {
                iRemoteViewDelegate.turnOffLight();
                return true;
            } catch (RemoteException unused) {
                o4.b(TrackConstants$Events.EXCEPTION, "RemoteException");
                return false;
            }
        }

        public void b(boolean z) {
            this.l = z;
        }

        public void c(boolean z) {
            this.m = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void b() {
            if (this.b != null) {
                this.b = null;
            }
            if (this.f4625a != null) {
                this.f4625a = null;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public boolean c() {
            IRemoteViewDelegate iRemoteViewDelegate = this.d;
            if (iRemoteViewDelegate == null) {
                return false;
            }
            try {
                return iRemoteViewDelegate.getLightStatus();
            } catch (RemoteException unused) {
                o4.b(TrackConstants$Events.EXCEPTION, "RemoteException");
                return false;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(boolean z) {
            this.g = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(Bundle bundle) {
            this.k = bundle;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(IOnResultCallback iOnResultCallback) {
            this.e = iOnResultCallback;
            IRemoteViewDelegate iRemoteViewDelegate = this.d;
            if (iRemoteViewDelegate != null) {
                try {
                    iRemoteViewDelegate.setOnResultCallback(iOnResultCallback);
                } catch (RemoteException unused) {
                    o4.b(TrackConstants$Events.EXCEPTION, "RemoteException");
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(IOnErrorCallback iOnErrorCallback) {
            IRemoteViewDelegate iRemoteViewDelegate = this.d;
            if (iRemoteViewDelegate != null) {
                try {
                    iRemoteViewDelegate.setOnErrorCallback(iOnErrorCallback);
                } catch (RemoteException unused) {
                    o4.b(TrackConstants$Events.EXCEPTION, "RemoteException");
                }
            }
        }

        a(Activity activity, ViewGroup viewGroup, boolean z, int i, Rect rect, int i2) {
            this.f4625a = viewGroup;
            this.b = activity;
            this.f = z;
            this.h = i;
            this.j = rect;
            this.n = i2;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(IOnLightCallback iOnLightCallback) {
            this.i = iOnLightCallback;
            IRemoteViewDelegate iRemoteViewDelegate = this.d;
            if (iRemoteViewDelegate != null) {
                try {
                    iRemoteViewDelegate.setOnLightVisbleCallBack(iOnLightCallback);
                } catch (RemoteException unused) {
                    o4.b(TrackConstants$Events.EXCEPTION, "RemoteException");
                }
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Code restructure failed: missing block: B:21:0x0042, code lost:
        
            com.huawei.hms.scankit.p.o4.e("ScanUtil", "input image is too large:" + r3.getWidth());
         */
        /* JADX WARN: Removed duplicated region for block: B:15:0x0060 A[Catch: Error -> 0x0064, Exception -> 0x006a, RemoteException -> 0x0070, IllegalStateException -> 0x0076, TRY_LEAVE, TryCatch #2 {RemoteException -> 0x0070, Error -> 0x0064, IllegalStateException -> 0x0076, Exception -> 0x006a, blocks: (B:7:0x000b, B:9:0x0017, B:12:0x0025, B:13:0x005c, B:15:0x0060, B:21:0x0042, B:22:0x0059), top: B:6:0x000b }] */
        /* JADX WARN: Removed duplicated region for block: B:19:? A[RETURN, SYNTHETIC] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void a(int r3, int r4, android.content.Intent r5) {
            /*
                r2 = this;
                java.lang.String r0 = "ScanKitRemoteView"
                r1 = -1
                if (r4 != r1) goto L7b
                if (r5 == 0) goto L7b
                r4 = 4371(0x1113, float:6.125E-42)
                if (r3 != r4) goto L7b
                com.huawei.hms.hmsscankit.RemoteView r3 = com.huawei.hms.hmsscankit.RemoteView.this     // Catch: java.lang.Error -> L64 java.lang.Exception -> L6a android.os.RemoteException -> L70 java.lang.IllegalStateException -> L76
                android.content.Context r3 = com.huawei.hms.hmsscankit.RemoteView.access$1300(r3)     // Catch: java.lang.Error -> L64 java.lang.Exception -> L6a android.os.RemoteException -> L70 java.lang.IllegalStateException -> L76
                android.graphics.Bitmap r3 = com.huawei.hms.scankit.p.w7.a(r3, r5)     // Catch: java.lang.Error -> L64 java.lang.Exception -> L6a android.os.RemoteException -> L70 java.lang.IllegalStateException -> L76
                if (r3 == 0) goto L40
                int r4 = r3.getWidth()     // Catch: java.lang.Error -> L64 java.lang.Exception -> L6a android.os.RemoteException -> L70 java.lang.IllegalStateException -> L76
                int r5 = r3.getHeight()     // Catch: java.lang.Error -> L64 java.lang.Exception -> L6a android.os.RemoteException -> L70 java.lang.IllegalStateException -> L76
                int r4 = r4 * r5
                r5 = 52428800(0x3200000, float:4.7019774E-37)
                if (r4 <= r5) goto L25
                goto L40
            L25:
                com.huawei.hms.hmsscankit.RemoteView r4 = com.huawei.hms.hmsscankit.RemoteView.this     // Catch: java.lang.Error -> L64 java.lang.Exception -> L6a android.os.RemoteException -> L70 java.lang.IllegalStateException -> L76
                android.content.Context r4 = com.huawei.hms.hmsscankit.RemoteView.access$1300(r4)     // Catch: java.lang.Error -> L64 java.lang.Exception -> L6a android.os.RemoteException -> L70 java.lang.IllegalStateException -> L76
                com.huawei.hms.ml.scan.HmsScanAnalyzerOptions$Creator r5 = new com.huawei.hms.ml.scan.HmsScanAnalyzerOptions$Creator     // Catch: java.lang.Error -> L64 java.lang.Exception -> L6a android.os.RemoteException -> L70 java.lang.IllegalStateException -> L76
                r5.<init>()     // Catch: java.lang.Error -> L64 java.lang.Exception -> L6a android.os.RemoteException -> L70 java.lang.IllegalStateException -> L76
                r1 = 1
                com.huawei.hms.ml.scan.HmsScanAnalyzerOptions$Creator r5 = r5.setPhotoMode(r1)     // Catch: java.lang.Error -> L64 java.lang.Exception -> L6a android.os.RemoteException -> L70 java.lang.IllegalStateException -> L76
                com.huawei.hms.ml.scan.HmsScanAnalyzerOptions r5 = r5.create()     // Catch: java.lang.Error -> L64 java.lang.Exception -> L6a android.os.RemoteException -> L70 java.lang.IllegalStateException -> L76
                int r1 = r2.h     // Catch: java.lang.Error -> L64 java.lang.Exception -> L6a android.os.RemoteException -> L70 java.lang.IllegalStateException -> L76
                com.huawei.hms.ml.scan.HmsScan[] r3 = com.huawei.hms.hmsscankit.f.a(r4, r3, r5, r1)     // Catch: java.lang.Error -> L64 java.lang.Exception -> L6a android.os.RemoteException -> L70 java.lang.IllegalStateException -> L76
                goto L5c
            L40:
                if (r3 == 0) goto L59
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Error -> L64 java.lang.Exception -> L6a android.os.RemoteException -> L70 java.lang.IllegalStateException -> L76
                java.lang.String r5 = "input image is too large:"
                r4.<init>(r5)     // Catch: java.lang.Error -> L64 java.lang.Exception -> L6a android.os.RemoteException -> L70 java.lang.IllegalStateException -> L76
                java.lang.String r5 = "ScanUtil"
                int r3 = r3.getWidth()     // Catch: java.lang.Error -> L64 java.lang.Exception -> L6a android.os.RemoteException -> L70 java.lang.IllegalStateException -> L76
                r4.append(r3)     // Catch: java.lang.Error -> L64 java.lang.Exception -> L6a android.os.RemoteException -> L70 java.lang.IllegalStateException -> L76
                java.lang.String r3 = r4.toString()     // Catch: java.lang.Error -> L64 java.lang.Exception -> L6a android.os.RemoteException -> L70 java.lang.IllegalStateException -> L76
                com.huawei.hms.scankit.p.o4.e(r5, r3)     // Catch: java.lang.Error -> L64 java.lang.Exception -> L6a android.os.RemoteException -> L70 java.lang.IllegalStateException -> L76
            L59:
                r3 = 0
                com.huawei.hms.ml.scan.HmsScan[] r3 = new com.huawei.hms.ml.scan.HmsScan[r3]     // Catch: java.lang.Error -> L64 java.lang.Exception -> L6a android.os.RemoteException -> L70 java.lang.IllegalStateException -> L76
            L5c:
                com.huawei.hms.hmsscankit.api.IOnResultCallback r4 = r2.e     // Catch: java.lang.Error -> L64 java.lang.Exception -> L6a android.os.RemoteException -> L70 java.lang.IllegalStateException -> L76
                if (r4 == 0) goto L7b
                r4.onResult(r3)     // Catch: java.lang.Error -> L64 java.lang.Exception -> L6a android.os.RemoteException -> L70 java.lang.IllegalStateException -> L76
                goto L7b
            L64:
                java.lang.String r3 = "Exception in error"
                com.huawei.hms.scankit.p.o4.b(r0, r3)
                goto L7b
            L6a:
                java.lang.String r3 = "Exception in remoteview"
                com.huawei.hms.scankit.p.o4.b(r0, r3)
                goto L7b
            L70:
                java.lang.String r3 = "RemoteException in remoteview"
                com.huawei.hms.scankit.p.o4.b(r0, r3)
                goto L7b
            L76:
                java.lang.String r3 = "IllegalStateException in remoteview"
                com.huawei.hms.scankit.p.o4.b(r0, r3)
            L7b:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.hmsscankit.RemoteView.a.a(int, int, android.content.Intent):void");
        }

        a(Activity activity, ViewGroup viewGroup, boolean z, int i, Rect rect, int i2, boolean z2, boolean z3) {
            this.f4625a = viewGroup;
            this.b = activity;
            this.f = z;
            this.h = i;
            this.j = rect;
            this.n = i2;
            this.o = z2;
            this.p = z3;
        }
    }

    static class b implements LifecycleDelegate {

        /* renamed from: a, reason: collision with root package name */
        private ViewGroup f4628a;
        private View b;
        private IRemoteViewDelegate c;

        b(ViewGroup viewGroup, IRemoteViewDelegate iRemoteViewDelegate) {
            this.f4628a = viewGroup;
            this.c = iRemoteViewDelegate;
        }

        @Override // com.huawei.hms.feature.dynamic.LifecycleDelegate
        public void onCreate(Bundle bundle) {
            try {
                this.c.onCreate(bundle);
                this.b = (View) ObjectWrapper.unwrap(this.c.getView());
                this.f4628a.removeAllViews();
                this.f4628a.addView(this.b);
            } catch (RemoteException unused) {
                o4.b(TrackConstants$Events.EXCEPTION, "RemoteException");
            }
        }

        @Override // com.huawei.hms.feature.dynamic.LifecycleDelegate
        public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
            return new View(null);
        }

        @Override // com.huawei.hms.feature.dynamic.LifecycleDelegate
        public void onDestroy() {
            try {
                this.c.onDestroy();
            } catch (RemoteException unused) {
                o4.b(TrackConstants$Events.EXCEPTION, "RemoteException");
            }
        }

        @Override // com.huawei.hms.feature.dynamic.LifecycleDelegate
        public void onDestroyView() {
        }

        @Override // com.huawei.hms.feature.dynamic.LifecycleDelegate
        public void onInflate(Activity activity, Bundle bundle, Bundle bundle2) {
        }

        @Override // com.huawei.hms.feature.dynamic.LifecycleDelegate
        public void onLowMemory() {
        }

        @Override // com.huawei.hms.feature.dynamic.LifecycleDelegate
        public void onPause() {
            try {
                this.c.onPause();
            } catch (RemoteException unused) {
                o4.b(TrackConstants$Events.EXCEPTION, "RemoteException");
            }
        }

        @Override // com.huawei.hms.feature.dynamic.LifecycleDelegate
        public void onResume() {
            try {
                if (RemoteView.isOnStop) {
                    this.c.onResume();
                } else if (RemoteView.flagForGallery) {
                    this.c.onStart();
                } else {
                    this.c.onResume();
                }
            } catch (RemoteException unused) {
                o4.b(TrackConstants$Events.EXCEPTION, "RemoteException");
            }
        }

        @Override // com.huawei.hms.feature.dynamic.LifecycleDelegate
        public void onSaveInstanceState(Bundle bundle) {
        }

        @Override // com.huawei.hms.feature.dynamic.LifecycleDelegate
        public void onStart() {
            try {
                this.c.onStart();
            } catch (RemoteException unused) {
                o4.b(TrackConstants$Events.EXCEPTION, "RemoteException");
            }
        }

        @Override // com.huawei.hms.feature.dynamic.LifecycleDelegate
        public void onStop() {
            try {
                this.c.onStop();
            } catch (RemoteException unused) {
                o4.b(TrackConstants$Events.EXCEPTION, "RemoteException");
            }
        }
    }

    RemoteView(Activity activity, boolean z, int i, Rect rect) {
        super(activity);
        this.mContinuouslyScan = true;
        this.mReturnedBitmap = false;
        this.mOnErrorCallback = null;
        this.mContext = activity;
        this.mRemoteHelper = new a(activity, this, z, i, rect);
    }

    private boolean checkPhotoPermission(Activity activity) {
        return Build.VERSION.SDK_INT >= 33 ? w7.b((Context) activity) || activity.checkPermission("android.permission.READ_MEDIA_IMAGES", Process.myPid(), Process.myUid()) == 0 : w7.b((Context) activity) || activity.checkPermission("android.permission.READ_EXTERNAL_STORAGE", Process.myPid(), Process.myUid()) == 0;
    }

    private void requestPhotoPermission(Activity activity) {
        if (Build.VERSION.SDK_INT >= 33) {
            activity.requestPermissions(new String[]{"android.permission.READ_MEDIA_IMAGES"}, REQUEST_CODE_PHOTO);
        } else {
            activity.requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, REQUEST_CODE_PHOTO);
        }
    }

    private void startActivityForGallery(Activity activity) {
        try {
            Intent intent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            if (w7.g(activity)) {
                if (w7.a(new Intent(), "com.android.gallery3d", activity) != null && w7.a("com.android.gallery3d", activity)) {
                    o4.d(TAG, "Start Gallery:com.android.gallery3d");
                    intent.setPackage("com.android.gallery3d");
                } else if (w7.a(new Intent(), "com.huawei.photos", activity) != null && w7.a("com.huawei.photos", activity)) {
                    intent.setPackage("com.huawei.photos");
                    o4.d(TAG, "Start Gallery:com.huawei.photos");
                }
            }
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, Constants.IMAGE_TYPE);
            flagForGallery = true;
            activity.startActivityForResult(intent, REQUEST_CODE_PHOTO);
        } catch (Exception unused) {
            o4.b(TAG, "startPhotoCode Exception");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startPhotoCode(Activity activity) {
        if (checkPhotoPermission(activity)) {
            startActivityForGallery(activity);
            return;
        }
        o4.d(TAG, "no photo permission");
        if (this.mOnErrorCallback == null) {
            requestPhotoPermission(activity);
        } else {
            o4.b(TAG, "no photo permission, report error2");
            this.mOnErrorCallback.onError(2);
        }
    }

    RemoteView enableReturnBitmap(boolean z) {
        this.mReturnedBitmap = z;
        this.mRemoteHelper.c(z);
        return this;
    }

    public boolean getLightStatus() {
        a aVar = this.mRemoteHelper;
        if (aVar != null) {
            return aVar.c();
        }
        return false;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        Log.i(TAG, "onActivityResult: ");
        a aVar = this.mRemoteHelper;
        if (aVar != null) {
            aVar.a(i, i2, intent);
        }
    }

    public void onCreate(Bundle bundle) {
        Log.i(TAG, "onCreate:");
        Context context = this.mContext;
        if ((context instanceof Activity) && ((Activity) context).getWindow() != null) {
            ((Activity) this.mContext).getWindow().setFlags(16777216, 16777216);
        }
        this.mRemoteHelper.onCreate(bundle);
    }

    public final void onDestroy() {
        Log.i(TAG, "onDestroy: ");
        a aVar = this.mRemoteHelper;
        if (aVar != null) {
            aVar.onDestroy();
            this.mRemoteHelper.b();
            this.mRemoteHelper = null;
        }
    }

    public final void onPause() {
        Log.i(TAG, "onPause: ");
        a aVar = this.mRemoteHelper;
        if (aVar != null) {
            aVar.onPause();
        }
        a aVar2 = this.mRemoteHelper;
        if (aVar2 != null && flagForGallery) {
            aVar2.onStop();
        }
        isOnStop = false;
    }

    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr, Activity activity) {
        if (i == 4371 && iArr.length == 1 && iArr[0] == 0) {
            startPhotoCode(activity);
        }
    }

    public final void onResume() {
        Log.i(TAG, "onResume: ");
        a aVar = this.mRemoteHelper;
        if (aVar != null) {
            aVar.onResume();
        }
        flagForGallery = false;
    }

    public final void onStart() {
        Log.i(TAG, "onStart: ");
        a aVar = this.mRemoteHelper;
        if (aVar != null) {
            aVar.onStart();
        }
        flagForGallery = false;
    }

    public final void onStop() {
        Log.i(TAG, "onStop: ");
        a aVar = this.mRemoteHelper;
        if (aVar != null && !flagForGallery) {
            aVar.onStop();
        }
        isOnStop = true;
    }

    public void pauseContinuouslyScan() {
        a aVar = this.mRemoteHelper;
        if (aVar != null) {
            aVar.d();
        }
    }

    public void resumeContinuouslyScan() {
        a aVar = this.mRemoteHelper;
        if (aVar != null) {
            aVar.e();
        }
    }

    public void selectPictureFromLocalFile() {
        startPhotoCode((Activity) this.mContext);
    }

    RemoteView setContinuouslyScan(boolean z) {
        this.mContinuouslyScan = z;
        return this;
    }

    public void setOnErrorCallback(OnErrorCallback onErrorCallback) {
        this.mOnErrorCallback = onErrorCallback;
        a aVar = this.mRemoteHelper;
        if (aVar != null) {
            aVar.a(new c(this.mOnErrorCallback));
        }
    }

    public void setOnLightVisibleCallback(OnLightVisibleCallBack onLightVisibleCallBack) {
        a aVar = this.mRemoteHelper;
        if (aVar != null) {
            aVar.a(new d(onLightVisibleCallBack));
        }
    }

    public void setOnResultCallback(OnResultCallback onResultCallback) {
        a aVar = this.mRemoteHelper;
        if (aVar != null) {
            aVar.a(new e(onResultCallback, this.mContinuouslyScan));
        }
    }

    public boolean switchLight() {
        if (this.mRemoteHelper != null) {
            return !getLightStatus() ? this.mRemoteHelper.g() : this.mRemoteHelper.f();
        }
        return false;
    }

    RemoteView(Activity activity, boolean z, int i, Rect rect, int i2) {
        super(activity);
        this.mContinuouslyScan = true;
        this.mReturnedBitmap = false;
        this.mOnErrorCallback = null;
        this.mContext = activity;
        this.mRemoteHelper = new a(activity, this, z, i, rect, i2);
    }

    RemoteView(Activity activity, boolean z, int i, Rect rect, int i2, boolean z2, boolean z3) {
        super(activity);
        this.mContinuouslyScan = true;
        this.mReturnedBitmap = false;
        this.mOnErrorCallback = null;
        this.mContext = activity;
        this.mRemoteHelper = new a(activity, this, z, i, rect, i2, z2, z3);
    }

    RemoteView(Activity activity, boolean z, int i, Rect rect, boolean z2) {
        super(activity);
        this.mContinuouslyScan = true;
        this.mReturnedBitmap = false;
        this.mOnErrorCallback = null;
        this.mContext = activity;
        a aVar = new a(activity, this, z, i, rect);
        this.mRemoteHelper = aVar;
        aVar.b(z2);
    }

    RemoteView(Activity activity, boolean z, Bundle bundle) {
        super(activity);
        this.mContinuouslyScan = true;
        this.mReturnedBitmap = false;
        this.mOnErrorCallback = null;
        this.mContext = activity;
        a aVar = new a(activity, this, false, 0, null);
        this.mRemoteHelper = aVar;
        aVar.a(z);
        this.mRemoteHelper.a(bundle);
    }
}
