package com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.open.data.model.CommonUiResponse;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hihealthservice.old.model.OldToNewMotionPath;
import com.huawei.hms.kit.awareness.barrier.internal.e.a;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateThresholdConfig;
import com.huawei.hwcommonmodel.fitnessdatatype.HeartRateZoneMgr;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.numberpicker.HealthNumberPicker;
import com.huawei.ui.commonui.recycleview.HealthLinearLayoutManager;
import com.huawei.ui.commonui.seekbar.HealthSeekBar;
import com.huawei.ui.commonui.switchbutton.HealthSwitchButton;
import defpackage.dds;
import defpackage.dei;
import defpackage.did;
import defpackage.dij;
import defpackage.dis;
import defpackage.dkv;
import defpackage.ixx;
import defpackage.nsn;
import health.compact.a.HiDateUtil;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class RopeSkipSettingFragment extends RopeSkipSettingBaseFragment {
    private static final int BIRTH_DATE_MONTH_DAY_LENGTH = 4;
    private static final int BPM_HUNDRED_FIFTY = 150;
    private static final int BPM_HUNDRED_TWENTY = 120;
    private static final int BPM_MAX = 180;
    private static final int BPM_MIN = 60;
    private static final String CONNECT_MODE_TYPE = "connect_mode_type";
    private static final int HALF_VOLUME = 50;
    private static final int HEART_RATE_TOP_TIP_DEFAULT_AGE = 18;
    private static final int HEART_RATE_TOP_TIP_MAX = 220;
    private static final float HEART_RATE_TOP_TIP_MAX_RATIO = 1.15f;
    private static final float HEART_RATE_TOP_TIP_MIN_RATIO = 0.85f;
    private static final String HEART_RATE_TOP_TYPE = "heart_rate_top_type";
    private static final String METRONOME_VOICE_TEMPO_TYPE = "metronome_voice_tempo_type";
    private static final String MUSIC_JUMP_TYPE = "music_jump_type";
    private static final int MUTE_VOLUME = 0;
    private static final int MUTE_VOLUME_THUMBOFFSET = 1;
    private static final int OP_BIT_RESERVE_HEART_RATE = 1;
    private static final int OP_BIT_SWITCH_ENQUIRE = 1;
    private static final int ROTATION_ANGLE = 180;
    private static final int SEEKBAR_THUMBOFFSET = 0;
    private static final int SETTING_ENQUIRE_VALUE = 703;
    private static final String SWITCH_BAR_HEART_RATE_REMIND_TYPE = "switch_bar_heart_rate_remind_type";
    private static final String SWITCH_BAR_METRONOME_TYPE = "switch_bar_metronome_type";
    private static final String SWITCH_BAR_VIBRATE_TYPE = "switch_bar_vibrate_type";
    private static final String SWITCH_BAR_VOLUME_ASSISTANT_TYPE = "switch_bar_volume_assistant_type";
    private static final int TEMPO_TYPE_ELEMENTS_HIGH_SIZE = 2;
    private static final String TEMPO_VOICE_TYPE = "tempo_voice_type";
    private static final String VOICE_COACHING_TYPE = "voice_coaching_type";
    private static final String VOICE_CV_TYPE = "voice_cv_type";
    private static final String VOICE_FREQUENCY_TYPE = "voice_frequency_type";
    private static final int VOLUME_ASSISTANT_TYPE_ELEMENTS_HIGH_SIZE = 3;
    private int[] mBpmArray;
    private HealthSeekBar mHealthSeekBar;
    private int mHeartRateRemindMaxValue;
    private int mHeartRateRemindMinValue;
    private int mHeartRateRemindValue;
    private boolean mIsHeartRateRemindEnable;
    private boolean mIsMetronomeEnable;
    private boolean mIsSilentModeEnable;
    private boolean mIsVibrateEnable;
    private boolean mIsVolumeAssistantEnable;
    private int mMetronomeTempoValue;
    private int mSeekBarProgress;
    private HealthSwitchButton mSilentModeSwitchButton;
    private int mSwitchValue;
    private int mVolumeRemindFrequencyValue;
    private int mVolumeValue;
    private static final int[] BPM_ARRAY = {60, 120, 150, 180};
    private static final int[] BPM_PRO_ARRAY = {60, 70, 80, 90, 100, 110, 120, OldToNewMotionPath.SPORT_TYPE_TENNIS, 140, 150, 160, 170, 180, 190, 200, a.C};
    private int mVolumeCoachingValue = 1;
    private int mVolumeCvValue = 1;
    private int mTempoTypeValue = 4;
    private int mMusicJumpValue = 2;
    private int mConnectMode = 2;

    private int getHeartRateMax(int i) {
        if (i == 0) {
            i = 18;
        }
        int i2 = (int) ((220 - i) * HEART_RATE_TOP_TIP_MAX_RATIO);
        if (i2 > 220) {
            return 220;
        }
        return i2;
    }

    private int getHeartRateMin(int i) {
        if (i == 0) {
            i = 18;
        }
        return (int) ((220 - i) * HEART_RATE_TOP_TIP_MIN_RATIO);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.RopeSkipSettingBaseFragment
    protected void createChildView(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        this.child = layoutInflater.inflate(R.layout.fragment_rope_settings_scrollview, viewGroup, false);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.RopeSkipSettingBaseFragment
    public void initView(View view) {
        super.initView(view);
        this.mSilentModeSwitchButton = (HealthSwitchButton) view.findViewById(R.id.voice_settings_switch);
        HealthSeekBar healthSeekBar = (HealthSeekBar) view.findViewById(R.id.voice_settings_seekbar);
        this.mHealthSeekBar = healthSeekBar;
        healthSeekBar.setTouchable(true);
        if (dij.o(Constants.URDU_LANG)) {
            this.mHealthSeekBar.setRotation(180.0f);
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.RopeSkipSettingBaseFragment
    public void initData() {
        setTitle(getString(R.string._2130845965_res_0x7f02210d));
        this.mLayoutManager = new HealthLinearLayoutManager(this.mainActivity) { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.RopeSkipSettingFragment.1
            @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public boolean canScrollVertically() {
                return false;
            }
        };
        this.mHealthSeekBar.setOnSeekBarChangeListener(new HealthSeekBar.OnSeekBarChangeListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.RopeSkipSettingFragment.2
            @Override // com.huawei.ui.commonui.seekbar.HealthSeekBar.OnSeekBarChangeListener
            public void onProgressChanged(HealthSeekBar healthSeekBar, int i, boolean z) {
                if (z) {
                    RopeSkipSettingFragment.this.setSeekBarView(i);
                    RopeSkipSettingFragment.this.mSeekBarProgress = i;
                }
            }

            @Override // com.huawei.ui.commonui.seekbar.HealthSeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(HealthSeekBar healthSeekBar) {
                LogUtil.a("RopeSkipSettingBaseFragment", "onStartTrackingTouch");
            }

            @Override // com.huawei.ui.commonui.seekbar.HealthSeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(HealthSeekBar healthSeekBar) {
                LogUtil.a("RopeSkipSettingBaseFragment", "onStopTrackingTouch mSeekBarProgress = ", Integer.valueOf(RopeSkipSettingFragment.this.mSeekBarProgress));
                RopeSkipSettingFragment ropeSkipSettingFragment = RopeSkipSettingFragment.this;
                ropeSkipSettingFragment.ropeVolumeSetting(ropeSkipSettingFragment.mSeekBarProgress);
            }
        });
        this.mSilentModeSwitchButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.RopeSkipSettingFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RopeSkipSettingFragment.this.m381x8dda3a2(view);
            }
        });
        super.initData();
    }

    /* renamed from: lambda$initData$0$com-huawei-health-ecologydevice-ui-measure-fragment-ropefragment-RopeSkipSettingFragment, reason: not valid java name */
    /* synthetic */ void m381x8dda3a2(View view) {
        LogUtil.a("RopeSkipSettingBaseFragment", "setOnClickListener mSilentModeSwitchButton click");
        this.mIsSilentModeEnable = this.mSilentModeSwitchButton.isChecked();
        this.mSwitchValue ^= 1;
        ropeSwitchSetting();
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        LogUtil.a("RopeSkipSettingBaseFragment", "onHiddenChanged hidden = ", Boolean.valueOf(z));
        if (z) {
            return;
        }
        int b = did.c().b(dds.c().d());
        LogUtil.a("RopeSkipSettingBaseFragment", "onHiddenChanged connectMode = ", Integer.valueOf(b));
        if (b != this.mConnectMode) {
            this.mConnectMode = b;
            sendEmptyHandleMessage(101);
        }
        did.c().d(dds.c().j(), dds.c().d());
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        setBiAnalytics();
        return super.onBackPressed();
    }

    private void setBiAnalytics() {
        LogUtil.a("RopeSkipSettingBaseFragment", "setBiAnalytics");
        String e = dij.e(dds.c().j());
        String b = dis.b(dds.c().d());
        HashMap hashMap = new HashMap(10);
        hashMap.put("prodId", e);
        hashMap.put("macAddress", b);
        hashMap.put("silentMode", Boolean.valueOf(this.mIsSilentModeEnable));
        hashMap.put("volumeAssistant", Boolean.valueOf(this.mIsVolumeAssistantEnable));
        hashMap.put("soundCadence", Boolean.valueOf(this.mIsMetronomeEnable));
        hashMap.put("soundMotor", Boolean.valueOf(this.mIsVibrateEnable));
        hashMap.put("heartRateRemind", Boolean.valueOf(this.mIsHeartRateRemindEnable));
        ixx.d().d(this.mainActivity, AnalyticsValue.HEALTH_SKIPPING_SETTING_SWITCH.value(), hashMap, 0);
        HashMap hashMap2 = new HashMap(10);
        hashMap2.put("prodId", e);
        hashMap2.put("macAddress", b);
        hashMap2.put("volume", Integer.valueOf(this.mVolumeValue));
        hashMap2.put("voiceRemindFrequency", Integer.valueOf(this.mVolumeRemindFrequencyValue));
        hashMap2.put("soundCadenceValue", Integer.valueOf(this.mMetronomeTempoValue));
        hashMap2.put("heartRateRemindValue", Integer.valueOf(this.mHeartRateRemindValue));
        ixx.d().d(this.mainActivity, AnalyticsValue.HEALTH_SKIPPING_SETTING_VALUE.value(), hashMap2, 0);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.RopeSkipSettingBaseFragment
    /* renamed from: setItemClickListener */
    public void m380x1c99b493(View view, int i, dkv dkvVar) {
        char c;
        if (dkvVar == null || dkvVar.e() == 1) {
            return;
        }
        String b = dkvVar.b();
        b.hashCode();
        switch (b.hashCode()) {
            case -1580764343:
                if (b.equals(SWITCH_BAR_VOLUME_ASSISTANT_TYPE)) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1019296706:
                if (b.equals(SWITCH_BAR_METRONOME_TYPE)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -753287176:
                if (b.equals(METRONOME_VOICE_TEMPO_TYPE)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -182976059:
                if (b.equals(SWITCH_BAR_HEART_RATE_REMIND_TYPE)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case -70795679:
                if (b.equals(SWITCH_BAR_VIBRATE_TYPE)) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 537215353:
                if (b.equals(VOICE_CV_TYPE)) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case 553615082:
                if (b.equals(VOICE_FREQUENCY_TYPE)) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case 772472593:
                if (b.equals(MUSIC_JUMP_TYPE)) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case 1523108804:
                if (b.equals(VOICE_COACHING_TYPE)) {
                    c = '\b';
                    break;
                }
                c = 65535;
                break;
            case 1665823553:
                if (b.equals(CONNECT_MODE_TYPE)) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 1795524043:
                if (b.equals(TEMPO_VOICE_TYPE)) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 1810252842:
                if (b.equals(HEART_RATE_TOP_TYPE)) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
            case 1:
            case 3:
            case 4:
                clickSwitchBarRefreshPage(view, dkvVar);
                break;
            case 2:
                showMetronomeVoiceTypeSetting(i, METRONOME_VOICE_TEMPO_TYPE);
                break;
            case 5:
                switchToRadioSelectFragment(1, this.mVolumeCvValue);
                break;
            case 6:
                switchToRadioSelectFragment(2, this.mVolumeRemindFrequencyValue);
                break;
            case 7:
                switchToRadioSelectFragment(4, this.mMusicJumpValue);
                break;
            case '\b':
                switchToRadioSelectFragment(0, this.mVolumeCoachingValue);
                break;
            case '\t':
                switchToRadioSelectFragment(5, this.mConnectMode);
                break;
            case '\n':
                switchToRadioSelectFragment(3, this.mTempoTypeValue);
                break;
            case 11:
                showHeartRateTopSetting(i, HEART_RATE_TOP_TYPE);
                break;
            default:
                LogUtil.a("RopeSkipSettingBaseFragment", "settingType is no equals");
                break;
        }
    }

    private void switchToRadioSelectFragment(int i, int i2) {
        if (nsn.o()) {
            LogUtil.h("RopeSkipSettingBaseFragment", "switchToRadioSelectFragment click too fast");
            return;
        }
        RopeSkipRadioButtonSettingFragment ropeSkipRadioButtonSettingFragment = new RopeSkipRadioButtonSettingFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(RopeSkipSettingBaseFragment.ROPE_RADIO_SELECT_TYPE, i);
        bundle.putInt(RopeSkipSettingBaseFragment.ROPE_RADIO_SELECT_TYPE_VALUE, i2);
        bundle.putInt(RopeSkipSettingBaseFragment.ROPE_TEMPO_VALUE, this.mMetronomeTempoValue);
        ropeSkipRadioButtonSettingFragment.setArguments(bundle);
        addFragment(ropeSkipRadioButtonSettingFragment);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void clickSwitchBarRefreshPage(View view, dkv dkvVar) {
        char c;
        if (view instanceof HealthSwitchButton) {
            HealthSwitchButton healthSwitchButton = (HealthSwitchButton) view;
            LogUtil.a("RopeSkipSettingBaseFragment", "clickSwitchBarRefreshPage settingType is ", dkvVar.b());
            String b = dkvVar.b();
            b.hashCode();
            switch (b.hashCode()) {
                case -1580764343:
                    if (b.equals(SWITCH_BAR_VOLUME_ASSISTANT_TYPE)) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case -1019296706:
                    if (b.equals(SWITCH_BAR_METRONOME_TYPE)) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case -182976059:
                    if (b.equals(SWITCH_BAR_HEART_RATE_REMIND_TYPE)) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case -70795679:
                    if (b.equals(SWITCH_BAR_VIBRATE_TYPE)) {
                        c = 3;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
            if (c == 0) {
                this.mIsVolumeAssistantEnable = healthSwitchButton.isChecked();
                this.mSwitchValue ^= 2;
            } else if (c == 1) {
                this.mIsMetronomeEnable = healthSwitchButton.isChecked();
                this.mSwitchValue ^= 4;
            } else if (c == 2) {
                this.mIsHeartRateRemindEnable = healthSwitchButton.isChecked();
                this.mSwitchValue ^= 16;
            } else if (c == 3) {
                this.mIsVibrateEnable = healthSwitchButton.isChecked();
                this.mSwitchValue ^= 8;
            } else {
                LogUtil.a("RopeSkipSettingBaseFragment", "switchbar type is no equals");
            }
            ropeSwitchSetting();
        }
    }

    private void ropeSwitchSetting() {
        dds.c().d(1, 1, new int[]{this.mSwitchValue});
        setData();
        setAdapterData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ropeVolumeSetting(int i) {
        LogUtil.c("RopeSkipSettingBaseFragment", "volume is ", Integer.valueOf(i));
        if (this.mIsSilentModeEnable) {
            LogUtil.a("RopeSkipSettingBaseFragment", "ropeVolumeSetting mIsSilentModeEnable is true");
            this.mSilentModeSwitchButton.setChecked(false);
            this.mIsSilentModeEnable = false;
            this.mSwitchValue ^= 1;
            dds.c().d(1, 1, new int[]{this.mSwitchValue});
        }
        this.mVolumeValue = i;
        dds.c().d(2, 1, new int[]{this.mVolumeValue});
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.RopeSkipSettingBaseFragment
    public void initSettingsData() {
        dei.c().a(getActivity(), new CommonUiResponse() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.RopeSkipSettingFragment$$ExternalSyntheticLambda3
            @Override // com.huawei.health.ecologydevice.open.data.model.CommonUiResponse
            public final void onResponse(int i, Object obj) {
                RopeSkipSettingFragment.this.m382x15666b1e(i, (HiUserInfo) obj);
            }
        });
        setData();
        dds.c().e("RopeSkipSettingBaseFragment", this, false);
        dds.c().d(1, 0, new int[]{1});
        dds.c().d(2, 0, new int[]{SETTING_ENQUIRE_VALUE});
    }

    /* renamed from: lambda$initSettingsData$1$com-huawei-health-ecologydevice-ui-measure-fragment-ropefragment-RopeSkipSettingFragment, reason: not valid java name */
    /* synthetic */ void m382x15666b1e(int i, HiUserInfo hiUserInfo) {
        int userAge = getUserAge(hiUserInfo);
        this.mHeartRateRemindMinValue = getHeartRateMin(userAge);
        this.mHeartRateRemindMaxValue = getHeartRateMax(userAge);
    }

    private int getUserAge(HiUserInfo hiUserInfo) {
        if (hiUserInfo == null) {
            return 0;
        }
        String valueOf = String.valueOf(hiUserInfo.getBirthday());
        if (valueOf.length() <= 4) {
            LogUtil.h("RopeSkipSettingBaseFragment", "getUserAge birthDate is empty or length is illegal");
            return 0;
        }
        String valueOf2 = String.valueOf(HiDateUtil.c(System.currentTimeMillis()));
        if (valueOf2.length() <= 4) {
            LogUtil.h("RopeSkipSettingBaseFragment", "getUserAge currentDateStr length is illegal");
            return 0;
        }
        try {
            return Integer.parseInt(valueOf2.substring(valueOf2.length() - 4)) < Integer.parseInt(valueOf.substring(valueOf.length() - 4)) ? hiUserInfo.getAge() - 1 : hiUserInfo.getAge();
        } catch (NumberFormatException unused) {
            LogUtil.b("RopeSkipSettingBaseFragment", "getUserAge NumberFormatException");
            return 0;
        }
    }

    private void setData() {
        this.mSettingsItemBeanArrayList.clear();
        this.mSwitchValue = 0;
        boolean z = this.mIsSilentModeEnable;
        if (z) {
            this.mSwitchValue = 1;
        }
        this.mSilentModeSwitchButton.setChecked(z);
        this.mHealthSeekBar.setProgress(this.mVolumeValue);
        setSeekBarView(this.mVolumeValue);
        this.mSettingsItemBeanArrayList.add(getItemBean(1, R.string._2130845819_res_0x7f02207b, "", -1));
        this.mSettingsItemBeanArrayList.add(getSwitchBarItemBean(R.string._2130845819_res_0x7f02207b, "", this.mIsVibrateEnable, SWITCH_BAR_VIBRATE_TYPE));
        if (this.mIsVibrateEnable) {
            this.mSwitchValue ^= 8;
        }
        this.mSettingsItemBeanArrayList.add(getItemBean(1, R.string._2130845814_res_0x7f022076, "", -1));
        this.mSettingsItemBeanArrayList.add(getSwitchBarItemBean(R.string._2130845815_res_0x7f022077, "", this.mIsVolumeAssistantEnable, SWITCH_BAR_VOLUME_ASSISTANT_TYPE));
        if (this.mIsVolumeAssistantEnable) {
            this.mSwitchValue ^= 2;
            setVolumeEnableItem();
        }
        this.mSettingsItemBeanArrayList.add(getItemBean(1, R.string._2130845820_res_0x7f02207c, "", -1));
        this.mSettingsItemBeanArrayList.add(getSwitchBarItemBean(R.string._2130845820_res_0x7f02207c, "", this.mIsMetronomeEnable, SWITCH_BAR_METRONOME_TYPE));
        if (this.mIsMetronomeEnable) {
            this.mSwitchValue ^= 4;
            setMetronomeEnableItem();
        }
        setMusicJumpItem();
        this.mSettingsItemBeanArrayList.add(getItemBean(1, R.string._2130843139_res_0x7f021603, "", -1));
        this.mSettingsItemBeanArrayList.add(getSwitchBarItemBean(R.string._2130844085_res_0x7f0219b5, getString(R.string._2130843140_res_0x7f021604, Integer.valueOf(this.mHeartRateRemindValue)), this.mIsHeartRateRemindEnable, SWITCH_BAR_HEART_RATE_REMIND_TYPE));
        if (this.mIsHeartRateRemindEnable) {
            this.mSwitchValue ^= 16;
            setHeartRateRemindItem();
        }
        if (dij.g(dds.c().j())) {
            setConnectModeItem();
        }
        setRecycleViewHeight();
    }

    private void setMusicJumpItem() {
        if (dij.g(dds.c().j())) {
            this.mSettingsItemBeanArrayList.add(getItemBean(1, R.string._2130850436_res_0x7f023284, "", -1));
            dkv itemBean = getItemBean(2, R.string._2130850438_res_0x7f023286, getMusicJumpDetail(this.mMusicJumpValue), R.drawable.list_item_background_single_normal);
            itemBean.d(MUSIC_JUMP_TYPE);
            this.mSettingsItemBeanArrayList.add(itemBean);
        }
    }

    private void setConnectModeItem() {
        this.mSettingsItemBeanArrayList.add(getItemBean(1, R.string._2130850407_res_0x7f023267, "", -1));
        int b = did.c().b(dds.c().d());
        this.mConnectMode = b;
        dkv itemBean = getItemBean(2, R.string._2130850408_res_0x7f023268, getConnectModeDetail(b), R.drawable.list_item_background_single_normal);
        itemBean.d(CONNECT_MODE_TYPE);
        this.mSettingsItemBeanArrayList.add(itemBean);
    }

    private void setRecycleViewHeight() {
        int dimensionPixelOffset = getResources().getDimensionPixelOffset(R.dimen._2131363092_res_0x7f0a0514);
        int dimensionPixelOffset2 = getResources().getDimensionPixelOffset(R.dimen._2131362865_res_0x7f0a0431);
        int dimensionPixelOffset3 = getResources().getDimensionPixelOffset(R.dimen._2131362883_res_0x7f0a0443);
        int assistantEnableHeight = this.mIsVolumeAssistantEnable ? getAssistantEnableHeight(dimensionPixelOffset2, dimensionPixelOffset) : dimensionPixelOffset2;
        int tempoEnableHeight = this.mIsMetronomeEnable ? getTempoEnableHeight(dimensionPixelOffset2, dimensionPixelOffset) : dimensionPixelOffset2;
        if (this.mIsHeartRateRemindEnable) {
            dimensionPixelOffset2 += dimensionPixelOffset;
        }
        int i = dimensionPixelOffset3 + assistantEnableHeight + tempoEnableHeight + dimensionPixelOffset2;
        if (dij.g(dds.c().j())) {
            i = i + getResources().getDimensionPixelOffset(R.dimen._2131362889_res_0x7f0a0449) + getResources().getDimensionPixelOffset(R.dimen._2131362883_res_0x7f0a0443);
        }
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, i);
        int dimensionPixelOffset4 = getResources().getDimensionPixelOffset(R.dimen._2131362886_res_0x7f0a0446);
        layoutParams.setMarginStart(dimensionPixelOffset4);
        layoutParams.setMarginEnd(dimensionPixelOffset4);
        this.mHealthRecycleView.setLayoutParams(layoutParams);
    }

    private int getAssistantEnableHeight(int i, int i2) {
        if (dij.g(dds.c().j())) {
            i2 *= 3;
        }
        return i + i2;
    }

    private int getTempoEnableHeight(int i, int i2) {
        if (dij.g(dds.c().j())) {
            i2 *= 2;
        }
        return i + i2;
    }

    private dkv getSwitchBarItemBean(int i, String str, boolean z, String str2) {
        dkv itemBean = getItemBean(4, i, str, (SWITCH_BAR_VIBRATE_TYPE.equals(str2) || !z) ? R.drawable.list_item_background_single_normal : R.drawable.list_item_background_top_normal);
        itemBean.d(z);
        itemBean.d(str2);
        return itemBean;
    }

    private void setHeartRateRemindItem() {
        dkv itemBean = getItemBean(2, R.string._2130841803_res_0x7f0210cb, getQuantityStr(R.plurals._2130903340_res_0x7f03012c, this.mHeartRateRemindValue), R.drawable.list_item_background_bottom_normal);
        itemBean.d(HEART_RATE_TOP_TYPE);
        this.mSettingsItemBeanArrayList.add(itemBean);
    }

    private void setMetronomeEnableItem() {
        if (dij.g(dds.c().j())) {
            dkv itemBean = getItemBean(2, R.string._2130850443_res_0x7f02328b, getTempoDetail(this.mTempoTypeValue), R.color._2131297213_res_0x7f0903bd);
            itemBean.d(TEMPO_VOICE_TYPE);
            this.mSettingsItemBeanArrayList.add(itemBean);
        }
        dkv itemBean2 = getItemBean(2, R.string._2130845821_res_0x7f02207d, getQuantityStr(R.plurals._2130903520_res_0x7f0301e0, this.mMetronomeTempoValue), R.drawable.list_item_background_bottom_normal);
        itemBean2.d(METRONOME_VOICE_TEMPO_TYPE);
        this.mSettingsItemBeanArrayList.add(itemBean2);
    }

    private void setVolumeEnableItem() {
        if (dij.g(dds.c().j())) {
            dkv itemBean = getItemBean(2, R.string._2130845816_res_0x7f022078, getVoiceCoachDetail(this.mVolumeCoachingValue), R.color._2131297213_res_0x7f0903bd);
            itemBean.d(VOICE_COACHING_TYPE);
            this.mSettingsItemBeanArrayList.add(itemBean);
            dkv itemBean2 = getItemBean(2, R.string._2130845817_res_0x7f022079, getVoiceCvDetail(this.mVolumeCvValue), R.color._2131297213_res_0x7f0903bd);
            itemBean2.d(VOICE_CV_TYPE);
            this.mSettingsItemBeanArrayList.add(itemBean2);
        }
        dkv itemBean3 = getItemBean(2, R.string._2130845818_res_0x7f02207a, getQuantityStr(R.plurals._2130903338_res_0x7f03012a, this.mVolumeRemindFrequencyValue), R.drawable.list_item_background_bottom_normal);
        itemBean3.d(VOICE_FREQUENCY_TYPE);
        this.mSettingsItemBeanArrayList.add(itemBean3);
    }

    private void showHeartRateTopSetting(int i, String str) {
        View inflate = getLayoutInflater().inflate(R.layout.health_picker_setting_dialog, (ViewGroup) null);
        HealthNumberPicker healthNumberPicker = (HealthNumberPicker) inflate.findViewById(R.id.pause_trackline_type_picker);
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        for (int i3 = this.mHeartRateRemindMinValue; i3 <= this.mHeartRateRemindMaxValue; i3++) {
            arrayList.add(getResources().getQuantityString(R.plurals._2130903340_res_0x7f03012c, i3, Integer.valueOf(i3)));
            if (this.mHeartRateRemindValue == i3) {
                i2 = i3 - this.mHeartRateRemindMinValue;
            }
        }
        healthNumberPicker.setDisplayedValues((String[]) arrayList.toArray(new String[arrayList.size()]));
        healthNumberPicker.setMinValue(0);
        healthNumberPicker.setMaxValue(arrayList.size() - 1);
        healthNumberPicker.setValue(i2);
        showCustomViewDialog(R.string._2130841803_res_0x7f0210cb, inflate, healthNumberPicker, i, str);
    }

    private void showMetronomeVoiceTypeSetting(int i, String str) {
        ArrayList arrayList = new ArrayList();
        this.mBpmArray = dij.g(dds.c().j()) ? BPM_PRO_ARRAY : BPM_ARRAY;
        int i2 = 0;
        for (int i3 = 0; i3 < this.mBpmArray.length; i3++) {
            Resources resources = getResources();
            int i4 = this.mBpmArray[i3];
            arrayList.add(resources.getQuantityString(R.plurals._2130903520_res_0x7f0301e0, i4, Integer.valueOf(i4)));
            if (this.mMetronomeTempoValue == this.mBpmArray[i3]) {
                i2 = i3;
            }
        }
        View inflate = getLayoutInflater().inflate(R.layout.health_picker_setting_dialog, (ViewGroup) null);
        HealthNumberPicker healthNumberPicker = (HealthNumberPicker) inflate.findViewById(R.id.pause_trackline_type_picker);
        healthNumberPicker.setDisplayedValues((String[]) arrayList.toArray(new String[arrayList.size()]));
        healthNumberPicker.setMinValue(0);
        healthNumberPicker.setMaxValue(arrayList.size() - 1);
        healthNumberPicker.setValue(i2);
        showCustomViewDialog(R.string._2130845821_res_0x7f02207d, inflate, healthNumberPicker, i, str);
    }

    private void showCustomViewDialog(int i, View view, final HealthNumberPicker healthNumberPicker, final int i2, final String str) {
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.mainActivity);
        builder.a(getString(i)).czg_(view).cze_(R.string._2130844420_res_0x7f021b04, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.RopeSkipSettingFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RopeSkipSettingFragment.this.m384xaea919df(healthNumberPicker, str, i2, view2);
            }
        }).czc_(R.string._2130844419_res_0x7f021b03, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.RopeSkipSettingFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RopeSkipSettingFragment.lambda$showCustomViewDialog$3(view2);
            }
        });
        builder.e().show();
    }

    /* renamed from: lambda$showCustomViewDialog$2$com-huawei-health-ecologydevice-ui-measure-fragment-ropefragment-RopeSkipSettingFragment, reason: not valid java name */
    /* synthetic */ void m384xaea919df(HealthNumberPicker healthNumberPicker, String str, int i, View view) {
        LogUtil.a("RopeSkipSettingBaseFragment", "showCustomViewDialog confirm position", Integer.valueOf(healthNumberPicker.getValue()));
        if (METRONOME_VOICE_TEMPO_TYPE.equals(str)) {
            int[] iArr = this.mBpmArray;
            if (iArr == null || iArr.length < healthNumberPicker.getValue()) {
                ViewClickInstrumentation.clickOnView(view);
                return;
            }
            int i2 = this.mBpmArray[healthNumberPicker.getValue()];
            this.mMetronomeTempoValue = i2;
            setDetailPosition(i, getQuantityStr(R.plurals._2130903520_res_0x7f0301e0, i2));
            dds.c().d(2, 4, new int[]{this.mTempoTypeValue, this.mMetronomeTempoValue});
            dds.c().a(this.mTempoTypeValue, this.mMetronomeTempoValue);
        } else if (HEART_RATE_TOP_TYPE.equals(str)) {
            int value = healthNumberPicker.getValue() + this.mHeartRateRemindMinValue;
            this.mHeartRateRemindValue = value;
            setDetailPosition(i, getQuantityStr(R.plurals._2130903340_res_0x7f03012c, value));
            setDetailPosition(i - 1, getString(R.string._2130843140_res_0x7f021604, Integer.valueOf(this.mHeartRateRemindValue)));
            dds.c().d(2, 5, new int[]{this.mHeartRateRemindValue});
        } else {
            LogUtil.a("RopeSkipSettingBaseFragment", "showCustomViewDialog type no equals");
        }
        setAdapterData();
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void lambda$showCustomViewDialog$3(View view) {
        LogUtil.a("RopeSkipSettingBaseFragment", "showCustomViewDialog cancel");
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.RopeSkipSettingBaseFragment
    protected void onReceiveMessage(int i, final HashMap<Integer, Object> hashMap) {
        if (i == 25) {
            this.mIsSilentModeEnable = getBooleanValue(hashMap, 40017).booleanValue();
            this.mIsVolumeAssistantEnable = getBooleanValue(hashMap, 40018).booleanValue();
            this.mIsMetronomeEnable = getBooleanValue(hashMap, 40019).booleanValue();
            this.mIsVibrateEnable = getBooleanValue(hashMap, 40020).booleanValue();
            this.mIsHeartRateRemindEnable = getBooleanValue(hashMap, 40021).booleanValue();
            LogUtil.a("RopeSkipSettingBaseFragment", "onReceiveMessage fitnessData mIsSilentModeEnable is ", Boolean.valueOf(this.mIsSilentModeEnable), ", mIsVolumeAssistantEnable is ", Boolean.valueOf(this.mIsVolumeAssistantEnable));
            sendEmptyHandleMessage(101);
            return;
        }
        if (i != 26) {
            return;
        }
        this.mVolumeValue = getIntValue(hashMap, 40024).intValue();
        if (dij.g(dds.c().j())) {
            this.mVolumeCoachingValue = getIntValue(hashMap, 40025).intValue();
            this.mVolumeCvValue = getIntValue(hashMap, 40026).intValue();
            this.mTempoTypeValue = getIntValue(hashMap, 40027).intValue();
            this.mMusicJumpValue = getIntValue(hashMap, 40069).intValue();
            LogUtil.a("RopeSkipSettingBaseFragment", "onReceiveMessage fitnessData mVolumeCoachingValue is ", Integer.valueOf(this.mVolumeCoachingValue), ", mVolumeCvValue is ", Integer.valueOf(this.mVolumeCvValue), ", mMusicJumpValue is ", Integer.valueOf(this.mMusicJumpValue));
        }
        this.mVolumeRemindFrequencyValue = getIntValue(hashMap, 40041).intValue();
        this.mMetronomeTempoValue = getIntValue(hashMap, 40028).intValue();
        this.mHeartRateRemindValue = getIntValue(hashMap, 40029).intValue();
        LogUtil.a("RopeSkipSettingBaseFragment", "onReceiveMessage fitnessData mVolumeValue is ", Integer.valueOf(this.mVolumeValue), ", mVolumeRemindFrequencyValue is ", Integer.valueOf(this.mVolumeRemindFrequencyValue));
        sendEmptyHandleMessage(101);
        dei.c().b(new CommonUiResponse() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.RopeSkipSettingFragment$$ExternalSyntheticLambda4
            @Override // com.huawei.health.ecologydevice.open.data.model.CommonUiResponse
            public final void onResponse(int i2, Object obj) {
                RopeSkipSettingFragment.this.m383x845b20f5(hashMap, i2, (HeartRateZoneMgr) obj);
            }
        });
    }

    /* renamed from: lambda$onReceiveMessage$4$com-huawei-health-ecologydevice-ui-measure-fragment-ropefragment-RopeSkipSettingFragment, reason: not valid java name */
    /* synthetic */ void m383x845b20f5(HashMap hashMap, int i, HeartRateZoneMgr heartRateZoneMgr) {
        if (heartRateZoneMgr == null) {
            LogUtil.h("RopeSkipSettingBaseFragment", "getHeartRateZoneMgr null");
        } else {
            setHeartRateZoneToRope(hashMap, heartRateZoneMgr);
        }
    }

    private void setHeartRateZoneToRope(HashMap<Integer, Object> hashMap, HeartRateZoneMgr heartRateZoneMgr) {
        HeartRateThresholdConfig postureType = heartRateZoneMgr.getPostureType(4);
        if (postureType == null) {
            LogUtil.a("RopeSkipSettingBaseFragment", "setHeartRateZoneToRope otherHeart is null");
            return;
        }
        LogUtil.a("RopeSkipSettingBaseFragment", "setHeartRateZoneToRope");
        int[] iArr = {1, postureType.getAerobicBaseThreshold(), postureType.getAerobicAdvanceThreshold(), postureType.getLacticAcidThreshold(), postureType.getAnaerobicBaseThreshold(), postureType.getAnaerobicAdvanceThreshold(), postureType.getMaxThreshold()};
        if (Arrays.equals(new int[]{getIntValue(hashMap, 40030).intValue(), getIntValue(hashMap, 40031).intValue(), getIntValue(hashMap, 40032).intValue(), getIntValue(hashMap, 40033).intValue(), getIntValue(hashMap, 40034).intValue(), getIntValue(hashMap, 40035).intValue(), getIntValue(hashMap, 40036).intValue()}, iArr)) {
            return;
        }
        LogUtil.a("RopeSkipSettingBaseFragment", "setHeartRateZoneToRope setFitnessRopeConfig");
        dds.c().d(2, 6, iArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSeekBarView(int i) {
        if (i == 0) {
            this.mHealthSeekBar.setThumb(getResources().getDrawable(R.drawable._2131432074_res_0x7f0b128a));
            this.mHealthSeekBar.setThumbOffset(1);
        } else if (i > 0 && this.mHealthSeekBar.getThumbOffset() != 0) {
            setThumb();
        } else {
            LogUtil.a("RopeSkipSettingBaseFragment", "setSeekBarView else");
        }
        if (i > 0 && i < 50) {
            this.mHealthSeekBar.setProgressDrawable(getResources().getDrawable(R.drawable._2131432002_res_0x7f0b1242));
        } else {
            this.mHealthSeekBar.setProgressDrawable(getResources().getDrawable(R.drawable._2131432001_res_0x7f0b1241));
        }
    }

    private void setThumb() {
        if (LanguageUtil.bc(this.mainActivity) && !dij.o(Constants.URDU_LANG)) {
            this.mHealthSeekBar.setThumb(getResources().getDrawable(R.drawable._2131432073_res_0x7f0b1289));
        } else {
            this.mHealthSeekBar.setThumb(getResources().getDrawable(R.drawable._2131432072_res_0x7f0b1288));
        }
        this.mHealthSeekBar.setThumbOffset(0);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.RopeSkipSettingBaseFragment
    protected void refreshPage() {
        super.refreshPage();
        if (this.mainActivity == null) {
            LogUtil.h("RopeSkipSettingBaseFragment", "refreshPage mainActivity is null");
        } else {
            setData();
            setAdapterData();
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.RopeSkipSettingBaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        dds.c().c("RopeSkipSettingBaseFragment", false);
    }
}
