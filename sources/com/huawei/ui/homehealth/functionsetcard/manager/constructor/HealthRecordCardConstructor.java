package com.huawei.ui.homehealth.functionsetcard.manager.constructor;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Parcelable;
import android.util.SparseArray;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.haf.handler.HandlerExecutor;
import com.huawei.health.devicemgr.business.entity.DeviceInfo;
import com.huawei.health.health.utils.functionsetcard.manager.OnCardChangedListener;
import com.huawei.health.health.utils.functionsetcard.manager.constructor.BaseCardConstructor;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData;
import com.huawei.hihealth.HiDataReadOption;
import com.huawei.hihealth.api.HiHealthNativeApi;
import com.huawei.hihealth.data.listener.HiDataReadResultListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.cwi;
import defpackage.jpt;
import defpackage.koq;
import defpackage.oiz;
import health.compact.a.BroadcastManagerUtil;
import health.compact.a.LocalBroadcast;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* loaded from: classes9.dex */
public class HealthRecordCardConstructor extends BaseCardConstructor {
    private static final int HEALTH_CARD_HIHEALTH_TYPE = 700017;
    private static final int PHONE_SERVICE_BINDER_RETRY_TIMES = 5;
    private static final String TAG = "FunctionSet_HealthRecordCardConstructor";
    private BroadcastReceiver mConnectStateChangedReceiver;
    private BroadcastReceiver mHiBroadcastReceiver;

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public int getShowFlag(Object obj) {
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: connectDeviceShowHealthRecord, reason: merged with bridge method [inline-methods] */
    public void m784x127a798f(final int i) {
        if (SharedPreferenceManager.a(String.valueOf(10100), "health_record_auto_show", false)) {
            LogUtil.a(TAG, "health record has been judged");
            return;
        }
        if (!HandlerExecutor.b()) {
            HandlerExecutor.a(new Runnable() { // from class: com.huawei.ui.homehealth.functionsetcard.manager.constructor.HealthRecordCardConstructor$$ExternalSyntheticLambda0
                @Override // java.lang.Runnable
                public final void run() {
                    HealthRecordCardConstructor.this.m784x127a798f(i);
                }
            });
            return;
        }
        if (jpt.d(TAG) == null) {
            LogUtil.a(TAG, "healthRecordDeviceInfo is empty, retryTimes is : ", Integer.valueOf(i));
            if (i > 0) {
                HandlerExecutor.d(new Runnable() { // from class: com.huawei.ui.homehealth.functionsetcard.manager.constructor.HealthRecordCardConstructor$$ExternalSyntheticLambda1
                    @Override // java.lang.Runnable
                    public final void run() {
                        HealthRecordCardConstructor.this.m785x4a6b54ae(i);
                    }
                }, 2000L);
                return;
            }
            return;
        }
        boolean c = cwi.c(jpt.d(TAG), 157);
        LogUtil.a(TAG, "healthRecordDevice isSupportCapability is: ", Boolean.valueOf(c));
        if (c) {
            addHealthRecordCard(true);
        }
    }

    /* renamed from: lambda$connectDeviceShowHealthRecord$1$com-huawei-ui-homehealth-functionsetcard-manager-constructor-HealthRecordCardConstructor, reason: not valid java name */
    /* synthetic */ void m785x4a6b54ae(int i) {
        m784x127a798f(i - 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addHealthRecordCard(boolean z) {
        Activity activity = BaseApplication.getActivity();
        if (activity == null) {
            LogUtil.h(TAG, "topActivity is null");
            return;
        }
        LogUtil.a(TAG, "BaseApplication.getTopActivity(): ", activity);
        String cardId = this.mCardConfig != null ? this.mCardConfig.getCardId() : "";
        if (z) {
            SharedPreferenceManager.e(String.valueOf(10100), "health_record_auto_show", true);
        }
        if (this.mOnCardChangedListener == null) {
            LogUtil.h(TAG, "addHealthRecordCard mOnCardChangedListener is null");
        } else {
            this.mOnCardChangedListener.onShowStatusChanged(cardId, z, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void healthRecordIsShowOrHide() {
        if (SharedPreferenceManager.a(String.valueOf(10100), "health_record_auto_show", false)) {
            LogUtil.a(TAG, "health record has been judged");
            return;
        }
        LogUtil.a(TAG, "judge is show health record");
        HiDataReadOption hiDataReadOption = new HiDataReadOption();
        long currentTimeMillis = System.currentTimeMillis();
        hiDataReadOption.setCount(1);
        hiDataReadOption.setSortOrder(1);
        hiDataReadOption.setStartTime(0L);
        hiDataReadOption.setEndTime(currentTimeMillis);
        hiDataReadOption.setType(new int[]{HEALTH_CARD_HIHEALTH_TYPE});
        HiHealthNativeApi.a(BaseApplication.getContext()).readHiHealthData(hiDataReadOption, new HiDataReadResultListener() { // from class: com.huawei.ui.homehealth.functionsetcard.manager.constructor.HealthRecordCardConstructor.2
            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResultIntent(int i, Object obj, int i2, int i3) {
            }

            @Override // com.huawei.hihealth.data.listener.HiDataReadResultListener
            public void onResult(Object obj, int i, int i2) {
                LogUtil.a(HealthRecordCardConstructor.TAG, "errorCode is", Integer.valueOf(i));
                if (i != 0) {
                    return;
                }
                SparseArray sparseArray = (SparseArray) obj;
                LogUtil.a(HealthRecordCardConstructor.TAG, "healthDataArray size is: ", Integer.valueOf(sparseArray.size()));
                if (sparseArray.size() > 0) {
                    HealthRecordCardConstructor.this.addHealthRecordCard(true);
                }
            }
        });
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.BaseCardConstructor, com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public void setOnChangedListener(OnCardChangedListener onCardChangedListener) {
        super.setOnChangedListener(onCardChangedListener);
        LogUtil.a(TAG, "setOnChangedListener");
        m784x127a798f(5);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.BaseCardConstructor, com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public void release() {
        unRegisterDataSyncComplete();
        unRegisterDeviceConnect();
    }

    public HealthRecordCardConstructor(CardConfig cardConfig) {
        super(cardConfig);
        this.mHiBroadcastReceiver = new HiBroadcastReceiver(this) { // from class: com.huawei.ui.homehealth.functionsetcard.manager.constructor.HealthRecordCardConstructor.1
        };
        this.mConnectStateChangedReceiver = new ConnectStateBroadcastReceiver();
        if (cardConfig == null) {
            LogUtil.h(TAG, "cardConfig is null");
            return;
        }
        boolean a2 = SharedPreferenceManager.a(String.valueOf(10100), "health_record_auto_show", false);
        LogUtil.a(TAG, "is has auto add healthRecord Card : ", Boolean.valueOf(a2));
        if (a2) {
            return;
        }
        registerDataSyncComplete();
        registerDeviceConnect();
    }

    private void registerDataSyncComplete() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.hihealth.action_sync");
        LocalBroadcastManager.getInstance(BaseApplication.getContext()).registerReceiver(this.mHiBroadcastReceiver, intentFilter);
        LogUtil.a(TAG, "data sync register success", this);
    }

    private void unRegisterDataSyncComplete() {
        if (this.mHiBroadcastReceiver != null) {
            LogUtil.a(TAG, "release mBroadcastReceiver broadCast start");
            try {
                LocalBroadcastManager.getInstance(BaseApplication.getContext()).unregisterReceiver(this.mHiBroadcastReceiver);
            } catch (IllegalArgumentException unused) {
                LogUtil.b(TAG, "unregisterReceiver crash Illegal");
            }
        }
    }

    private void registerDeviceConnect() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.bone.action.CONNECTION_STATE_CHANGED");
        BroadcastManagerUtil.bFC_(BaseApplication.getContext(), this.mConnectStateChangedReceiver, intentFilter, LocalBroadcast.c, null);
        LogUtil.a(TAG, "device connect register success", this);
    }

    private void unRegisterDeviceConnect() {
        if (this.mConnectStateChangedReceiver != null) {
            LogUtil.a(TAG, "release mDeviceConnectRegister broadCast start");
            try {
                LocalBroadcastManager.getInstance(BaseApplication.getContext()).unregisterReceiver(this.mConnectStateChangedReceiver);
            } catch (IllegalArgumentException unused) {
                LogUtil.b(TAG, "unregisterReceiver DeviceConnectRegister crash Illegal");
            }
        }
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public FunctionSetSubCardData createCardReader(Context context) {
        if (Utils.o()) {
            this.mCardConfig.setCardNameRes("IDS_health_examination");
        } else {
            this.mCardConfig.setCardNameRes("IDS_health_record");
        }
        return new oiz(context, this.mCardConfig);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.BaseCardConstructor, com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public boolean isSupport(boolean z, int i) {
        return super.isSupport(z, i);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public void subscribeDataChange() {
        LogUtil.a(TAG, "subscribeDataChange");
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(Integer.valueOf(HEALTH_CARD_HIHEALTH_TYPE));
        HiHealthNativeApi.a(this.mContext).subscribeHiHealthData(arrayList, new BaseCardConstructor.InnerHiSubscribeListener(TAG, this));
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public void unSubscribeDataChange() {
        if (koq.b(this.mSuccessList)) {
            LogUtil.h(TAG, "unSubscribeDataChange no type list");
        } else {
            LogUtil.a(TAG, "unSubscribeDataChange");
            HiHealthNativeApi.a(this.mContext).unSubscribeHiHealthData(this.mSuccessList, new BaseCardConstructor.InnerHiUnSubscribeListener(TAG, "unSubscribeDataChange, isSuccess:"));
        }
    }

    static class ConnectStateBroadcastReceiver extends BroadcastReceiver {
        private WeakReference<HealthRecordCardConstructor> mWeakReference;

        private ConnectStateBroadcastReceiver(HealthRecordCardConstructor healthRecordCardConstructor) {
            this.mWeakReference = new WeakReference<>(healthRecordCardConstructor);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            WeakReference<HealthRecordCardConstructor> weakReference = this.mWeakReference;
            if (weakReference == null) {
                LogUtil.h(HealthRecordCardConstructor.TAG, "mWeakReference == null");
                return;
            }
            HealthRecordCardConstructor healthRecordCardConstructor = weakReference.get();
            if (healthRecordCardConstructor == null) {
                LogUtil.h(HealthRecordCardConstructor.TAG, "constructor == null");
                return;
            }
            if (intent == null) {
                ReleaseLogUtil.c(HealthRecordCardConstructor.TAG, "setOnBindDeviceListener BroadcastReceiver intent is null");
                return;
            }
            if (!"com.huawei.bone.action.CONNECTION_STATE_CHANGED".equals(intent.getAction())) {
                ReleaseLogUtil.c(HealthRecordCardConstructor.TAG, "not connection state changed broadcast");
                return;
            }
            Parcelable parcelableExtra = intent.getParcelableExtra("deviceinfo");
            if (!(parcelableExtra instanceof DeviceInfo)) {
                ReleaseLogUtil.c(HealthRecordCardConstructor.TAG, "onReceive object ", parcelableExtra);
            } else if (((DeviceInfo) parcelableExtra).getDeviceConnectState() == 2) {
                ReleaseLogUtil.e(HealthRecordCardConstructor.TAG, "DEVICE_CONNECTED");
                healthRecordCardConstructor.m784x127a798f(5);
            } else {
                ReleaseLogUtil.e(HealthRecordCardConstructor.TAG, "DEVICE_CONNECT_STATUS_OTHER");
            }
        }
    }

    static class HiBroadcastReceiver extends BroadcastReceiver {
        private WeakReference<HealthRecordCardConstructor> mWeakReference;

        private HiBroadcastReceiver(HealthRecordCardConstructor healthRecordCardConstructor) {
            this.mWeakReference = new WeakReference<>(healthRecordCardConstructor);
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                return;
            }
            WeakReference<HealthRecordCardConstructor> weakReference = this.mWeakReference;
            if (weakReference == null) {
                LogUtil.h(HealthRecordCardConstructor.TAG, "mWeakReference == null");
                return;
            }
            HealthRecordCardConstructor healthRecordCardConstructor = weakReference.get();
            if (healthRecordCardConstructor == null) {
                LogUtil.h(HealthRecordCardConstructor.TAG, "constructor == null");
            } else if (intent.getIntExtra("com.huawei.hihealth.action_sync_status", 6) == 2) {
                LogUtil.a(HealthRecordCardConstructor.TAG, "onReceive data sync completed");
                healthRecordCardConstructor.healthRecordIsShowOrHide();
            }
        }
    }
}
