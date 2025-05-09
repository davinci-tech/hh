package com.huawei.ui.homehealth.functionsetcard.manager.constructor;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.huawei.haf.threadpool.ThreadPoolManager;
import com.huawei.health.health.utils.functionsetcard.manager.constructor.BaseCardConstructor;
import com.huawei.health.health.utils.functionsetcard.manager.model.CardConfig;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetSubCardData;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealth.api.HiHealthManager;
import com.huawei.hihealth.data.listener.HiCommonListener;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.login.ui.login.LoginInit;
import defpackage.koq;
import defpackage.oia;
import defpackage.ojd;
import defpackage.owp;
import health.compact.a.HiDateUtil;
import health.compact.a.Utils;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: classes9.dex */
public class PhysiologicalCycleCardConstructor extends BaseCardConstructor {
    private static final int BIRTH_DATE_FOR_AGE = 10000;
    private static final String HUAWEI_GENDER_FEMALE = "1";
    private static final int PHYSIOLOGICAL_CYCLE_AGE_MAX = 55;
    private static final int PHYSIOLOGICAL_CYCLE_AGE_MIN = 16;
    private static final String RELEASE_TAG = "R_PhysiologicalCycleCardConstructor";
    private static final String TAG = "PhysiologicalCycleCardConstructor";
    private boolean mIsCardShow;
    private boolean mIsRegister;
    private boolean mIsSupportPhysiologicalCycle;
    private LocalBroadcastManager mLocalBroadcastManager;
    private BroadcastReceiver mUserInfoBroadcastReceiver;

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public int getShowFlag(Object obj) {
        return 0;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public void subscribeDataChange() {
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public void unSubscribeDataChange() {
    }

    public PhysiologicalCycleCardConstructor(CardConfig cardConfig) {
        super(cardConfig);
        this.mIsRegister = false;
        this.mIsCardShow = false;
        this.mUserInfoBroadcastReceiver = new BroadcastReceiver() { // from class: com.huawei.ui.homehealth.functionsetcard.manager.constructor.PhysiologicalCycleCardConstructor.1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                if (intent == null) {
                    LogUtil.h(PhysiologicalCycleCardConstructor.TAG, "mUserInfoBroadcastReceiver onReceive intent is null");
                    return;
                }
                String action = intent.getAction();
                if ("com.huawei.plugin.account.login".equals(action) || "com.huawei.bone.action.FITNESS_USERINFO_UPDATED".equals(action)) {
                    PhysiologicalCycleCardConstructor.this.queryPhysiologicalCycleSwitch();
                }
            }
        };
        boolean z = !Utils.l();
        this.mIsSupportPhysiologicalCycle = z;
        ReleaseLogUtil.e(RELEASE_TAG, "mIsSupportPhysiologicalCycle: ", Boolean.valueOf(z));
        if (this.mIsRegister || this.mUserInfoBroadcastReceiver == null || !this.mIsSupportPhysiologicalCycle || cardConfig == null || cardConfig.getEditFlag() == 1) {
            return;
        }
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.huawei.plugin.account.login");
        intentFilter.addAction("com.huawei.bone.action.FITNESS_USERINFO_UPDATED");
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(BaseApplication.getContext());
        this.mLocalBroadcastManager = localBroadcastManager;
        localBroadcastManager.registerReceiver(this.mUserInfoBroadcastReceiver, intentFilter);
        this.mIsRegister = true;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.BaseCardConstructor, com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public boolean isSupport(boolean z, int i) {
        super.isSupport(z, i);
        boolean z2 = !Utils.l();
        this.mIsSupportPhysiologicalCycle = z2;
        ReleaseLogUtil.e(RELEASE_TAG, "isSupport mIsSupportPhysiologicalCycle: ", Boolean.valueOf(z2));
        boolean z3 = this.mIsSupportPhysiologicalCycle;
        LogUtil.a(TAG, "isSupport: ", Boolean.valueOf(z3));
        return z3;
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public FunctionSetSubCardData createCardReader(Context context) {
        return new ojd(context, this.mCardConfig);
    }

    @Override // com.huawei.health.health.utils.functionsetcard.manager.constructor.BaseCardConstructor, com.huawei.health.health.utils.functionsetcard.manager.constructor.CardConstructor
    public void release() {
        if (!this.mIsRegister || this.mLocalBroadcastManager == null || this.mUserInfoBroadcastReceiver == null) {
            return;
        }
        ReleaseLogUtil.e(TAG, "release mUserInfoBroadcastReceiver broadCast start");
        try {
            this.mLocalBroadcastManager.unregisterReceiver(this.mUserInfoBroadcastReceiver);
        } catch (IllegalArgumentException unused) {
            ReleaseLogUtil.e(TAG, "unregisterReceiver crash Illegal");
        }
        this.mIsRegister = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void queryPhysiologicalCycleSwitch() {
        ThreadPoolManager.d().execute(new Runnable() { // from class: com.huawei.ui.homehealth.functionsetcard.manager.constructor.PhysiologicalCycleCardConstructor.2
            @Override // java.lang.Runnable
            public void run() {
                PhysiologicalCycleCardConstructor.this.mIsCardShow = false;
                String g = owp.g(BaseApplication.getContext());
                LogUtil.a(PhysiologicalCycleCardConstructor.TAG, "switchString: ", g);
                if (g == null) {
                    PhysiologicalCycleCardConstructor.this.queryHuaweiAccountForPhysiologicalCycleCard();
                    return;
                }
                String cardId = PhysiologicalCycleCardConstructor.this.mCardConfig != null ? PhysiologicalCycleCardConstructor.this.mCardConfig.getCardId() : "";
                LogUtil.a(PhysiologicalCycleCardConstructor.TAG, "queryPhysiologicalCycleSwitch cardId ", cardId);
                oia c = oia.c();
                if (c == null) {
                    LogUtil.h(PhysiologicalCycleCardConstructor.TAG, "cardManager is null");
                    return;
                }
                CardConfig a2 = c.a(PhysiologicalCycleCardConstructor.TAG, cardId);
                if (a2 == null) {
                    LogUtil.h(PhysiologicalCycleCardConstructor.TAG, "queryPhysiologicalCycleSwitch cardConfig is null");
                    return;
                }
                if (a2.getEditFlag() == 1) {
                    LogUtil.a(PhysiologicalCycleCardConstructor.TAG, "queryPhysiologicalCycleSwitch edit by user");
                    return;
                }
                if (g.isEmpty()) {
                    PhysiologicalCycleCardConstructor.this.mIsCardShow = true;
                    PhysiologicalCycleCardConstructor.this.mOnCardChangedListener.onShowStatusChanged(cardId, true, 0);
                    return;
                }
                try {
                    int optInt = new JSONObject(g).optInt("masterSwitch");
                    LogUtil.a(PhysiologicalCycleCardConstructor.TAG, "queryPhysiologicalCycleSwitch masterSwitch ", Integer.valueOf(optInt));
                    if (optInt == 1) {
                        PhysiologicalCycleCardConstructor.this.mIsCardShow = true;
                        PhysiologicalCycleCardConstructor.this.mOnCardChangedListener.onShowStatusChanged(cardId, true, 0);
                        return;
                    }
                } catch (JSONException unused) {
                    LogUtil.b(PhysiologicalCycleCardConstructor.TAG, "queryPhysiologicalCycleSwitch JSONException");
                }
                PhysiologicalCycleCardConstructor.this.queryHuaweiAccountForPhysiologicalCycleCard();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void queryHuaweiAccountForPhysiologicalCycleCard() {
        LogUtil.a(TAG, "queryHuaweiAccountForPhysiologicalCycleCard");
        LoginInit loginInit = LoginInit.getInstance(BaseApplication.getContext());
        if (loginInit == null) {
            LogUtil.h(TAG, "logInit is null");
            queryPersonalInformationForPhysiologicalCycleCard();
            return;
        }
        String gender = loginInit.getGender();
        String birthDate = loginInit.getBirthDate();
        LogUtil.a(TAG, "gender: ", gender, ", birthDate: ", birthDate);
        if (gender == null || birthDate == null) {
            LogUtil.h(TAG, "queryHuaweiAccountForPhysiologicalCycleCard gender or birthDate is null");
            queryPersonalInformationForPhysiologicalCycleCard();
            return;
        }
        try {
            boolean equals = "1".equals(gender);
            int c = (HiDateUtil.c(System.currentTimeMillis()) / 10000) - (Integer.parseInt(birthDate) / 10000);
            boolean z = true;
            boolean z2 = c <= 55 && c >= 16;
            Object[] objArr = new Object[6];
            objArr[0] = "gender: ";
            objArr[1] = gender;
            objArr[2] = ", age: ";
            objArr[3] = Integer.valueOf(c);
            objArr[4] = ", isShow: ";
            objArr[5] = Boolean.valueOf(z2 && equals);
            LogUtil.a(TAG, objArr);
            if (!z2 || !equals) {
                z = false;
            }
            refreshPhysiologicalCycleCard(z);
        } catch (NumberFormatException unused) {
            LogUtil.b(TAG, "queryHuaweiAccountForPhysiologicalCycleCard NumberFormatException");
            queryPersonalInformationForPhysiologicalCycleCard();
        }
    }

    private void queryPersonalInformationForPhysiologicalCycleCard() {
        HiHealthManager.d(BaseApplication.getContext()).fetchUserData(new HiCommonListener() { // from class: com.huawei.ui.homehealth.functionsetcard.manager.constructor.PhysiologicalCycleCardConstructor.3
            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onSuccess(int i, Object obj) {
                if (PhysiologicalCycleCardConstructor.this.mIsCardShow) {
                    LogUtil.h(PhysiologicalCycleCardConstructor.TAG, "queryPersonalInformationForPhysiologicalCycleCard onSuccess card has shown");
                    return;
                }
                if (!(obj instanceof List)) {
                    LogUtil.h(PhysiologicalCycleCardConstructor.TAG, "queryPersonalInformationForPhysiologicalCycleCard onSuccess data instanceof List is false");
                    return;
                }
                List list = (List) obj;
                if (koq.b(list)) {
                    LogUtil.h(PhysiologicalCycleCardConstructor.TAG, "queryPersonalInformationForPhysiologicalCycleCard onSuccess userInfoList is empty");
                    return;
                }
                boolean z = false;
                HiUserInfo hiUserInfo = (HiUserInfo) list.get(0);
                if (hiUserInfo == null) {
                    LogUtil.h(PhysiologicalCycleCardConstructor.TAG, "queryPersonalInformationForPhysiologicalCycleCard onSuccess hiUserInfo is null");
                    return;
                }
                int age = hiUserInfo.getAge();
                boolean z2 = age <= 55 && age >= 16;
                boolean z3 = hiUserInfo.getGender() == 0;
                PhysiologicalCycleCardConstructor physiologicalCycleCardConstructor = PhysiologicalCycleCardConstructor.this;
                if (z2 && z3) {
                    z = true;
                }
                physiologicalCycleCardConstructor.refreshPhysiologicalCycleCard(z);
            }

            @Override // com.huawei.hihealth.data.listener.HiCommonListener
            public void onFailure(int i, Object obj) {
                LogUtil.h(PhysiologicalCycleCardConstructor.TAG, "queryPersonalInformationForPhysiologicalCycleCard onFailure errCode ", Integer.valueOf(i));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshPhysiologicalCycleCard(boolean z) {
        String cardId = this.mCardConfig != null ? this.mCardConfig.getCardId() : "";
        LogUtil.a(TAG, "refreshPhysiologicalCycleCard isShow ", Boolean.valueOf(z), " cardId ", cardId);
        oia c = oia.c();
        if (c == null) {
            LogUtil.h(TAG, "cardManager is null");
            return;
        }
        CardConfig a2 = c.a(TAG, cardId);
        if (a2 == null) {
            LogUtil.h(TAG, "refreshPhysiologicalCycleCard cardConfig is null");
        } else if (a2.getEditFlag() == 1) {
            LogUtil.a(TAG, "refreshPhysiologicalCycleCard edit by user");
        } else {
            this.mOnCardChangedListener.onShowStatusChanged(cardId, z, 0);
        }
    }
}
