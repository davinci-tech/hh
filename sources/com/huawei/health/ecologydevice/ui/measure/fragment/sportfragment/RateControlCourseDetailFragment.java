package com.huawei.health.ecologydevice.ui.measure.fragment.sportfragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.huawei.health.R;
import com.huawei.health.device.api.IndoorEquipManagerApi;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.health.hearratecontrol.callback.ConfigCallBack;
import com.huawei.health.sport.model.WorkoutRecord;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwbasemgr.ResponseCallback;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.operation.ble.BleConstants;
import com.huawei.pluginfitnessadvice.ChoreographedMultiActions;
import com.huawei.pluginfitnessadvice.FitWorkout;
import com.huawei.profile.coordinator.ProfileRequestConstants;
import com.huawei.ui.commonui.imageview.HealthImageView;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.czl;
import defpackage.dja;
import defpackage.gnm;
import defpackage.koq;
import defpackage.nrh;
import defpackage.nsn;
import health.compact.a.Services;
import health.compact.a.util.LogUtil;
import java.util.List;

/* loaded from: classes3.dex */
public class RateControlCourseDetailFragment extends BaseFragment implements Handler.Callback {
    public static final String COURSE_ID = "course_id";
    private static final String INDOOR_EQUIP_CONNECTED_ACTIVITY = "com.huawei.indoorequip.activity.IndoorEquipConnectedActivity";
    private static final String KEY_PROTOCOL = "PROTOCOL_FROM_QRCODE";
    private static final String KEY_SOURCE = "START_SPORT_SOURCE";
    private static final String PROTOCOL_FTMP = "2";
    private static final int QUERY_TIMEOUT_MSG = 1;
    private static final long QUERY_TIMEOUT_TIME = 2000;
    private static final String SCAN_BTN_APP = "ScanBtnAPP";
    private static final String TAG = "RateControlCourseDetailFragment";
    private static final float WIDTH_HEIGHT_PROPORTIONS = 0.56f;
    private RateControlStageListAdapter mActionsListAdapter;
    private HealthImageView mBannerImage;
    private String mCourseId;
    private FitWorkout mFitWorkout;
    private final Handler mHandler = new Handler(Looper.getMainLooper(), this);
    private volatile boolean mIsTimeout = false;
    private HealthRecycleView mRvStage;
    private int mType;

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mType = arguments.getInt("deviceType");
            this.mCourseId = arguments.getString("course_id");
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        ViewGroup viewGroup2 = onCreateView instanceof ViewGroup ? (ViewGroup) onCreateView : null;
        this.child = layoutInflater.inflate(R.layout.fragment_rate_control_course_detail, viewGroup, false);
        if (viewGroup2 != null) {
            viewGroup2.addView(this.child);
            initView(this.child);
            initData();
            return viewGroup2;
        }
        return this.child;
    }

    private void initView(View view) {
        this.mBannerImage = (HealthImageView) view.findViewById(R.id.iv_actions_banner);
        this.mRvStage = (HealthRecycleView) view.findViewById(R.id.rv_actions_list);
        view.findViewById(R.id.action_start_training).setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.sportfragment.RateControlCourseDetailFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RateControlCourseDetailFragment.this.m411xad0af52e(view2);
            }
        });
    }

    /* renamed from: lambda$initView$1$com-huawei-health-ecologydevice-ui-measure-fragment-sportfragment-RateControlCourseDetailFragment, reason: not valid java name */
    /* synthetic */ void m411xad0af52e(View view) {
        if (nsn.o()) {
            LogUtil.d(TAG, "click too fast");
            ViewClickInstrumentation.clickOnView(view);
        } else if (!((IndoorEquipManagerApi) Services.c("PluginLinkIndoorEquip", IndoorEquipManagerApi.class)).isDeviceBtConnected()) {
            LogUtil.c(TAG, "Device disconnect");
            nrh.b(this.mainActivity, R.string._2130845226_res_0x7f021e2a);
            ViewClickInstrumentation.clickOnView(view);
        } else {
            this.mIsTimeout = false;
            this.mHandler.sendEmptyMessageDelayed(1, QUERY_TIMEOUT_TIME);
            dja.b(new ResponseCallback() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.sportfragment.RateControlCourseDetailFragment$$ExternalSyntheticLambda0
                @Override // com.huawei.hwbasemgr.ResponseCallback
                public final void onResponse(int i, Object obj) {
                    RateControlCourseDetailFragment.this.m410x8e50bd6d(i, (Boolean) obj);
                }
            });
            ViewClickInstrumentation.clickOnView(view);
        }
    }

    /* renamed from: lambda$initView$0$com-huawei-health-ecologydevice-ui-measure-fragment-sportfragment-RateControlCourseDetailFragment, reason: not valid java name */
    /* synthetic */ void m410x8e50bd6d(int i, Boolean bool) {
        String str = TAG;
        LogUtil.d(str, "checkWearStatus: status = ", Integer.valueOf(i), ", data = ", bool);
        this.mHandler.removeMessages(1);
        if (this.mIsTimeout) {
            LogUtil.d(str, "checkWearStatus: query was already timeout, abandon the outdated result");
            return;
        }
        if (i == -2) {
            dja.VT_(this.mainActivity, this.mType);
            return;
        }
        if (i == -3) {
            dja.VU_(this.mainActivity, this.mType);
            return;
        }
        if (i == 1) {
            startIndoorEquipActivity();
            return;
        }
        if (i == 2) {
            dja.VV_(this.mainActivity);
        } else if (i == -4) {
            dja.VW_(this.mainActivity);
        } else {
            startIndoorEquipActivity();
        }
    }

    private void initData() {
        this.mCustomTitleBar.setTitleBarBackgroundColor(getResources().getColor(R.color._2131296690_res_0x7f0901b2));
        this.mRvStage.setLayoutManager(new LinearLayoutManager(this.mainActivity));
        RateControlStageListAdapter rateControlStageListAdapter = new RateControlStageListAdapter(this.mainActivity, this.mType);
        this.mActionsListAdapter = rateControlStageListAdapter;
        this.mRvStage.setAdapter(rateControlStageListAdapter);
        czl.a(this.mType, this.mCourseId, (ConfigCallBack<FitWorkout>) new ConfigCallBack() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.sportfragment.RateControlCourseDetailFragment$$ExternalSyntheticLambda1
            @Override // com.huawei.health.hearratecontrol.callback.ConfigCallBack
            public final void onResponse(Object obj) {
                RateControlCourseDetailFragment.this.m409xddd6db74((FitWorkout) obj);
            }
        });
    }

    /* renamed from: lambda$initData$2$com-huawei-health-ecologydevice-ui-measure-fragment-sportfragment-RateControlCourseDetailFragment, reason: not valid java name */
    /* synthetic */ void m409xddd6db74(FitWorkout fitWorkout) {
        if (fitWorkout == null || this.mainActivity == null) {
            LogUtil.c(TAG, "fitWorkout or mainActivity is null");
            return;
        }
        this.mFitWorkout = fitWorkout;
        setTitle(fitWorkout.acquireName());
        Glide.with(this.mainActivity).load(this.mFitWorkout.acquireMidPicture()).transform(new RoundedCorners(getResources().getDimensionPixelOffset(R.dimen._2131362869_res_0x7f0a0435))).into(this.mBannerImage);
        List<ChoreographedMultiActions> courseActions = fitWorkout.getCourseActions();
        if (koq.b(courseActions)) {
            return;
        }
        this.mActionsListAdapter.setData(courseActions.get(0).getActionList());
    }

    private void startIndoorEquipActivity() {
        Bundle arguments = getArguments();
        if (arguments == null || this.mFitWorkout == null) {
            return;
        }
        Intent intent = new Intent();
        intent.putExtra(KEY_PROTOCOL, "2");
        String string = arguments.getString("uniqueId");
        if (!TextUtils.isEmpty(string)) {
            intent.putExtra("BLE_FROM_QRCODE", string.replace(":", ""));
        }
        intent.putExtra("DEVICE_TYPE_INDEX", this.mType == 265 ? BleConstants.SPORT_TYPE_BIKE : "31");
        intent.putExtra("BLENAME_FROM_QRCODE", arguments.getString("name"));
        intent.putExtra(KEY_SOURCE, SCAN_BTN_APP);
        intent.putExtra("courseId", this.mCourseId);
        intent.putExtra(WorkoutRecord.Extend.COURSE_TARGET_VALUE, this.mFitWorkout.acquireDuration() + 1);
        intent.putExtra("sn", arguments.getString("sn"));
        intent.putExtra(ProfileRequestConstants.MANU, arguments.getString(ProfileRequestConstants.MANU));
        intent.putExtra("model", arguments.getString("model"));
        intent.putExtra("productId", arguments.getString("productId"));
        intent.setClassName(BaseApplication.getAppPackage(), INDOOR_EQUIP_CONNECTED_ACTIVITY);
        gnm.aPB_(this.mainActivity, intent);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        this.mHandler.removeCallbacksAndMessages(null);
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        int i = message.what;
        LogUtil.d(TAG, "handleMessage: msg.what = ", Integer.valueOf(i));
        if (i == 1) {
            this.mIsTimeout = true;
            dja.VX_(this.mainActivity);
        }
        return true;
    }
}
