package com.huawei.healthcloud.plugintrack.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import androidx.core.app.ActivityCompat;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.manager.inteface.ViewHolderInterface;
import com.huawei.healthcloud.plugintrack.ui.activity.TrackBaseActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import defpackage.caq;
import defpackage.gtx;
import defpackage.jeg;
import defpackage.ktg;
import defpackage.nsn;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;

/* loaded from: classes.dex */
public abstract class TrackBaseActivity extends BaseActivity implements ActivityCompat.OnRequestPermissionsResultCallback {
    private static final int MSG_HAS_PERMISSIONS = 0;
    private static final int MSG_NO_PERMISSIONS = 1;
    private static final String TAG = "Track_TrackBaseActivity";
    private Context mContext;
    private CustomPermissionAction mPermissionAction;
    protected Handler mPermissionHandler;
    protected ViewHolderInterface mViewHolderInterface = null;
    protected TrackBaseActivity mTrackBaseActivity = null;
    private boolean mIsShowLocationGuideDialog = false;

    protected abstract void initSport();

    protected abstract void initSport(boolean z);

    protected abstract ViewHolderInterface initViewHolder();

    protected boolean isNeedLocationPermission() {
        return true;
    }

    protected boolean isNeedStorgePermission() {
        return true;
    }

    protected boolean isSport() {
        return false;
    }

    protected abstract void setActvityLayoutModel();

    public TrackBaseActivity() {
        this.mPermissionHandler = new b();
        this.mPermissionAction = new a();
    }

    protected void init() {
        ViewHolderInterface initViewHolder = initViewHolder();
        this.mViewHolderInterface = initViewHolder;
        if (initViewHolder != null) {
            initViewHolder.setEventListener();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PermissionUtil.PermissionType getPermissionType() {
        if (isNeedLocationPermission()) {
            return caq.d();
        }
        if (isNeedStorgePermission()) {
            return PermissionUtil.PermissionType.MEDIA_VIDEO_IMAGES;
        }
        return PermissionUtil.PermissionType.NONE;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
        if (isSport() && bundle != null) {
            finish();
            return;
        }
        this.mTrackBaseActivity = this;
        if (nsn.ae(this.mContext) || isHasPermissions()) {
            this.mPermissionHandler.sendEmptyMessage(0);
        } else {
            checkPermission();
        }
        setActvityLayoutModel();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isHasPermissions() {
        boolean e = PermissionUtil.e(BaseApplication.getContext(), new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"});
        boolean e2 = PermissionUtil.e(BaseApplication.getContext(), new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE"});
        boolean j = PermissionUtil.j();
        LogUtil.a(TAG, "isHasLocation:", Boolean.valueOf(e), " isHasStorage:", Boolean.valueOf(e2), " isUseOnlyAllowedLocation():", Boolean.valueOf(j));
        return e && e2 && j;
    }

    /* loaded from: classes4.dex */
    static class b extends BaseHandler<TrackBaseActivity> {
        private b(TrackBaseActivity trackBaseActivity) {
            super(trackBaseActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: bcB_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(TrackBaseActivity trackBaseActivity, Message message) {
            LogUtil.a(TrackBaseActivity.TAG, "PermissionHandler handleMessageWhenReferenceNotNull message.what ", Integer.valueOf(message.what));
            int i = message.what;
            if (i != 0) {
                if (i != 1) {
                    return;
                }
                trackBaseActivity.finish();
            } else {
                trackBaseActivity.init();
                if (gtx.c(BaseApplication.getContext()).aq()) {
                    trackBaseActivity.initSport(true);
                } else {
                    trackBaseActivity.initSport();
                }
            }
        }
    }

    private void checkPermission() {
        PermissionUtil.PermissionType permissionType = getPermissionType();
        if (isNeedObtainLocationPermission(permissionType) && PermissionUtil.d()) {
            if (caq.c(this.mContext)) {
                showOpenBackGroundPermissionGuide(this.mContext, permissionType);
                return;
            } else {
                PermissionUtil.b(this.mContext, permissionType, this.mPermissionAction);
                return;
            }
        }
        PermissionUtil.b(this.mContext, permissionType, this.mPermissionAction);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showOpenBackGroundPermissionGuide(Context context, final PermissionUtil.PermissionType permissionType) {
        if (this.mIsShowLocationGuideDialog) {
            LogUtil.h(TAG, "showOpenBackGroundPermissionGuide has show");
            ktg.c().d("sportTrackTempLocation");
            finish();
        } else {
            LogUtil.a(TAG, "Show Open Background Permission Guide Dialog.");
            NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(context).e(context.getString(R.string._2130843342_res_0x7f0216ce)).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.TrackBaseActivity.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a(TrackBaseActivity.TAG, "showOpenBackGroundPermissionGuide cancel");
                    ktg.c().d("sportTrackTempLocation");
                    TrackBaseActivity.this.finish();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).czC_(R.string._2130842176_res_0x7f021240, new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.TrackBaseActivity.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a(TrackBaseActivity.TAG, "showOpenBackGroundPermissionGuide onClick");
                    PermissionUtil.b(TrackBaseActivity.this.mContext, permissionType, TrackBaseActivity.this.mPermissionAction);
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).e();
            e.setCancelable(false);
            e.show();
            this.mIsShowLocationGuideDialog = true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isNeedObtainLocationPermission(PermissionUtil.PermissionType permissionType) {
        return permissionType == PermissionUtil.PermissionType.LOCATION || permissionType == PermissionUtil.PermissionType.LOCATION_WITH_BACKGROUND;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isApplyBackgroundLocationPermission() {
        return (!isNeedLocationPermission() || PermissionUtil.j() || PermissionUtil.e(this.mContext, PermissionUtil.PermissionType.LOCATION_WITH_BACKGROUND) == PermissionUtil.PermissionResult.GRANTED) ? false : true;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
    }

    @Override // android.app.Activity, android.content.ComponentCallbacks
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        jeg.d().b(this.mPermissionAction);
        this.mPermissionAction = null;
    }

    /* loaded from: classes4.dex */
    public static class a extends CustomPermissionAction {
        private final WeakReference<TrackBaseActivity> c;

        private a(TrackBaseActivity trackBaseActivity) {
            super(trackBaseActivity);
            this.c = new WeakReference<>(trackBaseActivity);
        }

        @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onGranted() {
            TrackBaseActivity trackBaseActivity = this.c.get();
            if (trackBaseActivity == null || trackBaseActivity.isDestroyed() || trackBaseActivity.isFinishing()) {
                ReleaseLogUtil.d(TrackBaseActivity.TAG, "activity is null in WeakCustomPermissionAction");
                return;
            }
            LogUtil.a(TrackBaseActivity.TAG, "TrackBaseActivity CustomPermissionAction onGranted.");
            if (!trackBaseActivity.isHasPermissions() && trackBaseActivity.isApplyBackgroundLocationPermission()) {
                PermissionUtil.b(trackBaseActivity, PermissionUtil.PermissionType.LOCATION_WITH_BACKGROUND, trackBaseActivity.mPermissionAction);
            } else if (trackBaseActivity.mPermissionHandler != null) {
                trackBaseActivity.mPermissionHandler.sendEmptyMessage(0);
            }
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onDenied(String str) {
            super.onDenied(str);
            TrackBaseActivity trackBaseActivity = this.c.get();
            if (trackBaseActivity == null || trackBaseActivity.isDestroyed() || trackBaseActivity.isFinishing()) {
                ReleaseLogUtil.d(TrackBaseActivity.TAG, "activity is null in WeakCustomPermissionAction");
                return;
            }
            LogUtil.h(TrackBaseActivity.TAG, "TrackBaseActivity CustomPermissionAction onDenied.");
            if (!trackBaseActivity.isHasPermissions() || trackBaseActivity.mPermissionHandler == null) {
                PermissionUtil.PermissionType permissionType = trackBaseActivity.getPermissionType();
                if (trackBaseActivity.isNeedObtainLocationPermission(permissionType) && PermissionUtil.d()) {
                    if (caq.c(trackBaseActivity)) {
                        trackBaseActivity.showOpenBackGroundPermissionGuide(trackBaseActivity, permissionType);
                        return;
                    } else {
                        trackBaseActivity.finish();
                        return;
                    }
                }
                trackBaseActivity.finish();
                return;
            }
            trackBaseActivity.mPermissionHandler.sendEmptyMessage(0);
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
            final TrackBaseActivity trackBaseActivity = this.c.get();
            if (trackBaseActivity == null || trackBaseActivity.isDestroyed() || trackBaseActivity.isFinishing()) {
                ReleaseLogUtil.d(TrackBaseActivity.TAG, "activity is null in WeakCustomPermissionAction");
                return;
            }
            LogUtil.h(TrackBaseActivity.TAG, "TrackBaseActivity CustomPermissionAction onForeverDenied. ", "permissionType: ", permissionType);
            if (permissionType == PermissionUtil.PermissionType.LOCATION_WITH_BACKGROUND) {
                nsn.cLJ_(trackBaseActivity, permissionType, new View.OnClickListener() { // from class: hfd
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        TrackBaseActivity.a.bcC_(TrackBaseActivity.this, view);
                    }
                }, new View.OnClickListener() { // from class: hfi
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        TrackBaseActivity.a.bcD_(TrackBaseActivity.this, view);
                    }
                });
            } else if (caq.b(permissionType)) {
                caq.b(trackBaseActivity, permissionType, PermissionUtil.PermissionResult.FOREVER_DENIED, true);
            }
        }

        public static /* synthetic */ void bcC_(TrackBaseActivity trackBaseActivity, View view) {
            trackBaseActivity.finish();
            ViewClickInstrumentation.clickOnView(view);
        }

        public static /* synthetic */ void bcD_(TrackBaseActivity trackBaseActivity, View view) {
            nsn.ak(trackBaseActivity);
            trackBaseActivity.finish();
            ViewClickInstrumentation.clickOnView(view);
        }
    }
}
