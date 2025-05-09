package com.huawei.healthcloud.plugintrack.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.ui.activity.AbstractSportActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.dialog.NoTitleCustomAlertDialog;
import com.huawei.ui.commonui.linechart.utils.AsyncSelectorSerialize;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import defpackage.caq;
import defpackage.ktg;
import java.lang.ref.WeakReference;
import java.util.Map;

/* loaded from: classes4.dex */
public abstract class AbstractSportActivity extends BaseActivity {
    private static final int PRE_CONDITION_ERROR_CODE_PERMISSION = 1;
    private static final String TAG = "Track_AbstractSportActivity";
    private Context mContext;
    private boolean mIsShowLocationGuideDialog;
    protected AsyncSelectorSerialize mSportPreCheckCondition = new AsyncSelectorSerialize(new Handler(Looper.getMainLooper())) { // from class: com.huawei.healthcloud.plugintrack.ui.activity.AbstractSportActivity.5
        @Override // com.huawei.ui.commonui.linechart.utils.AsyncSelectorSerialize
        public void onFailed(int i) {
            LogUtil.h(AbstractSportActivity.TAG, "sport pre condition check failed with error code:", Integer.valueOf(i));
            AbstractSportActivity.this.finish();
        }

        @Override // com.huawei.ui.commonui.linechart.utils.AsyncSelectorSerialize
        public void onSuccess(Map map) {
            LogUtil.a(AbstractSportActivity.TAG, "sport pre condition check success.", map);
            AbstractSportActivity.this.doAfterPreCheck();
        }
    };

    protected abstract void doAfterPreCheck();

    protected abstract PermissionUtil.PermissionType getPermissionType();

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
        initPreCheckAction();
        this.mSportPreCheckCondition.run();
    }

    protected void initPreCheckAction() {
        addPermissionCheck();
    }

    private void addPermissionCheck() {
        final b bVar = new b(this);
        this.mSportPreCheckCondition.add(new AsyncSelectorSerialize.Action() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.AbstractSportActivity.1
            @Override // com.huawei.ui.commonui.linechart.utils.AsyncSelectorSerialize.Action
            public int getFailedValue() {
                return 1;
            }

            @Override // com.huawei.ui.commonui.linechart.utils.AsyncSelectorSerialize.Action
            public void execute(Map map) {
                PermissionUtil.PermissionType permissionType = AbstractSportActivity.this.getPermissionType();
                if (!AbstractSportActivity.this.isNeedObtainLocationPermission(permissionType) || !PermissionUtil.c()) {
                    PermissionUtil.b(AbstractSportActivity.this.mContext, permissionType, bVar);
                } else if (!caq.c(AbstractSportActivity.this.mContext)) {
                    PermissionUtil.b(AbstractSportActivity.this.mContext, permissionType, bVar);
                } else {
                    AbstractSportActivity abstractSportActivity = AbstractSportActivity.this;
                    abstractSportActivity.showOpenBackGroundPermissionGuide(abstractSportActivity.mContext, permissionType, bVar);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showOpenBackGroundPermissionGuide(Context context, final PermissionUtil.PermissionType permissionType, final CustomPermissionAction customPermissionAction) {
        if (this.mIsShowLocationGuideDialog) {
            LogUtil.h(TAG, "showOpenBackGroundPermissionGuide has show");
            ktg.c().d("sportTrackTempLocation");
            finish();
        } else {
            LogUtil.a(TAG, "Show Open Background Permission Guide Dialog.");
            NoTitleCustomAlertDialog e = new NoTitleCustomAlertDialog.Builder(context).e(context.getString(R.string._2130843342_res_0x7f0216ce)).czz_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.AbstractSportActivity.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a(AbstractSportActivity.TAG, "showOpenBackGroundPermissionGuide cancel");
                    ktg.c().d("sportTrackTempLocation");
                    AbstractSportActivity.this.mSportPreCheckCondition.postError();
                    ViewClickInstrumentation.clickOnView(view);
                }
            }).czC_(R.string._2130842176_res_0x7f021240, new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.AbstractSportActivity.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a(AbstractSportActivity.TAG, "showOpenBackGroundPermissionGuide onClick");
                    PermissionUtil.b(AbstractSportActivity.this.mContext, permissionType, customPermissionAction);
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
    public void handleOnDenied(CustomPermissionAction customPermissionAction) {
        LogUtil.h(TAG, "TrackBaseActivity CustomPermissionAction onDenied.");
        PermissionUtil.PermissionType permissionType = getPermissionType();
        if (isNeedObtainLocationPermission(permissionType) && PermissionUtil.c()) {
            if (caq.c(this.mContext)) {
                showOpenBackGroundPermissionGuide(this.mContext, permissionType, customPermissionAction);
                return;
            } else {
                this.mSportPreCheckCondition.postError();
                return;
            }
        }
        this.mSportPreCheckCondition.postError();
    }

    public static class b extends CustomPermissionAction {
        private final WeakReference<AbstractSportActivity> b;

        public b(AbstractSportActivity abstractSportActivity) {
            super(abstractSportActivity);
            this.b = new WeakReference<>(abstractSportActivity);
        }

        @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onGranted() {
            LogUtil.a(AbstractSportActivity.TAG, "TrackBaseActivity CustomPermissionAction onGranted.");
            AbstractSportActivity abstractSportActivity = this.b.get();
            if (abstractSportActivity == null) {
                LogUtil.a(AbstractSportActivity.TAG, "AbstractSportActivity is null in onGranted");
            } else {
                abstractSportActivity.mSportPreCheckCondition.next(null);
            }
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onDenied(String str) {
            super.onDenied(str);
            AbstractSportActivity abstractSportActivity = this.b.get();
            if (abstractSportActivity != null) {
                abstractSportActivity.handleOnDenied(this);
            } else {
                LogUtil.a(AbstractSportActivity.TAG, "AbstractSportActivity is null in onDenied");
            }
        }

        @Override // com.huawei.ui.commonui.utils.CustomPermissionAction, com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onForeverDenied(PermissionUtil.PermissionType permissionType) {
            final AbstractSportActivity abstractSportActivity = this.b.get();
            if (abstractSportActivity == null) {
                LogUtil.a(AbstractSportActivity.TAG, "AbstractSportActivity is null in onForeverDenied");
            } else {
                super.onForeverDenied(permissionType, new View.OnClickListener() { // from class: hcs
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        AbstractSportActivity.b.ban_(AbstractSportActivity.this, view);
                    }
                }, new View.OnClickListener() { // from class: hcp
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        AbstractSportActivity.b.bao_(AbstractSportActivity.this, view);
                    }
                });
            }
        }

        public static /* synthetic */ void ban_(AbstractSportActivity abstractSportActivity, View view) {
            abstractSportActivity.mSportPreCheckCondition.postError();
            ViewClickInstrumentation.clickOnView(view);
        }

        public static /* synthetic */ void bao_(AbstractSportActivity abstractSportActivity, View view) {
            abstractSportActivity.mSportPreCheckCondition.postError();
            ViewClickInstrumentation.clickOnView(view);
        }
    }
}
