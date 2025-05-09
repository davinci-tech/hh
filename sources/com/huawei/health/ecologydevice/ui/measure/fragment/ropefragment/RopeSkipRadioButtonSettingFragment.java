package com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment;

import android.os.Bundle;
import android.view.View;
import com.huawei.health.R;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.dds;
import defpackage.did;
import defpackage.dkv;

/* loaded from: classes3.dex */
public class RopeSkipRadioButtonSettingFragment extends RopeSkipSettingBaseFragment {
    private int mSelectType;
    private int mSelectValue;
    private int mTempoValue;

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.mSelectType = arguments.getInt(RopeSkipSettingBaseFragment.ROPE_RADIO_SELECT_TYPE);
            this.mSelectValue = arguments.getInt(RopeSkipSettingBaseFragment.ROPE_RADIO_SELECT_TYPE_VALUE);
            this.mTempoValue = arguments.getInt(RopeSkipSettingBaseFragment.ROPE_TEMPO_VALUE);
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.RopeSkipSettingBaseFragment
    public void initData() {
        setMarginTop();
        super.initData();
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.RopeSkipSettingBaseFragment
    /* renamed from: setItemClickListener */
    public void m380x1c99b493(View view, int i, dkv dkvVar) {
        if (dkvVar == null) {
            return;
        }
        int i2 = this.mSelectType;
        if (i2 == 0) {
            sendRopeDeviceSetting(2, dkvVar.c());
        } else if (i2 == 1) {
            sendRopeDeviceSetting(3, dkvVar.c());
        } else if (i2 == 2) {
            sendRopeDeviceSetting(8, dkvVar.c());
        } else if (i2 == 3) {
            dds.c().d(2, 4, new int[]{dkvVar.c(), this.mTempoValue});
        } else if (i2 == 4) {
            sendRopeDeviceSetting(10, dkvVar.c());
        } else if (i2 == 5) {
            did.c().b(dds.c().d(), dkvVar.c());
        } else {
            LogUtil.a("RopeSkipSettingBaseFragment", "setItemClickListener default");
        }
        popupFragment(RopeSkipSettingFragment.class);
    }

    private void sendRopeDeviceSetting(int i, int i2) {
        dds.c().d(2, i, new int[]{i2});
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.RopeSkipSettingBaseFragment
    public void initSettingsData() {
        LogUtil.a("RopeSkipSettingBaseFragment", "initSettingsData radio select type is ", Integer.valueOf(this.mSelectType));
        int i = this.mSelectType;
        if (i == 0) {
            setTitle(getString(R.string._2130845816_res_0x7f022078));
            setVoiceCoaching();
            return;
        }
        if (i == 1) {
            setTitle(getString(R.string._2130845817_res_0x7f022079));
            setVoiceCv();
            return;
        }
        if (i == 2) {
            setTitle(getString(R.string._2130845818_res_0x7f02207a));
            setBroadcastFrequency();
            return;
        }
        if (i == 3) {
            setTitle(getString(R.string._2130850443_res_0x7f02328b));
            setTempoType();
        } else if (i == 4) {
            setTitle(getString(R.string._2130850438_res_0x7f023286));
            setMusicJumpMode();
        } else if (i == 5) {
            setTitle(getString(R.string._2130850408_res_0x7f023268));
            setConnectMode();
        } else {
            LogUtil.a("RopeSkipSettingBaseFragment", "initSettingsData default");
        }
    }

    private void setConnectMode() {
        dkv itemBean = getItemBean(3, R.string._2130850409_res_0x7f023269, getConnectModeDetail(3), R.drawable.list_item_background_top_normal);
        itemBean.d(1);
        itemBean.d(this.mSelectValue == 1);
        this.mSettingsItemBeanArrayList.add(itemBean);
        dkv itemBean2 = getItemBean(3, R.string._2130850411_res_0x7f02326b, getConnectModeDetail(4), R.drawable.list_item_background_bottom_normal);
        itemBean2.d(2);
        itemBean2.d(this.mSelectValue == 2);
        this.mSettingsItemBeanArrayList.add(itemBean2);
    }

    private void setVoiceCoaching() {
        this.mSettingsItemBeanArrayList.add(getItemBean(5, getVoiceCoachDetail(2), 2, R.drawable.list_item_background_top_normal, this.mSelectValue == 2));
        this.mSettingsItemBeanArrayList.add(getItemBean(5, getVoiceCoachDetail(1), 1, R.drawable.list_item_background_bottom_normal, this.mSelectValue == 1));
    }

    private void setVoiceCv() {
        this.mSettingsItemBeanArrayList.add(getItemBean(5, getVoiceCvDetail(1), 1, R.drawable.list_item_background_top_normal, this.mSelectValue == 1));
        this.mSettingsItemBeanArrayList.add(getItemBean(5, getVoiceCvDetail(2), 2, R.color._2131297213_res_0x7f0903bd, this.mSelectValue == 2));
        this.mSettingsItemBeanArrayList.add(getItemBean(5, getVoiceCvDetail(3), 3, R.drawable.list_item_background_bottom_normal, this.mSelectValue == 3));
    }

    private void setBroadcastFrequency() {
        this.mSettingsItemBeanArrayList.add(getItemBean(5, getJumpsTimeString(50), 50, R.drawable.list_item_background_top_normal, this.mSelectValue == 50));
        this.mSettingsItemBeanArrayList.add(getItemBean(5, getJumpsTimeString(100), 100, R.color._2131297213_res_0x7f0903bd, this.mSelectValue == 100));
        this.mSettingsItemBeanArrayList.add(getItemBean(5, getJumpsTimeString(150), 150, R.color._2131297213_res_0x7f0903bd, this.mSelectValue == 150));
        this.mSettingsItemBeanArrayList.add(getItemBean(5, getJumpsTimeString(200), 200, R.drawable.list_item_background_bottom_normal, this.mSelectValue == 200));
    }

    private void setTempoType() {
        this.mSettingsItemBeanArrayList.add(getItemBean(5, getTempoDetail(4), 4, R.drawable.list_item_background_top_normal, this.mSelectValue == 4));
        this.mSettingsItemBeanArrayList.add(getItemBean(5, getTempoDetail(3), 3, R.color._2131297213_res_0x7f0903bd, this.mSelectValue == 3));
        this.mSettingsItemBeanArrayList.add(getItemBean(5, getTempoDetail(2), 2, R.drawable.list_item_background_bottom_normal, this.mSelectValue == 2));
    }

    private void setMusicJumpMode() {
        this.mSettingsItemBeanArrayList.add(getItemBean(5, getMusicJumpDetail(2), 2, R.drawable.list_item_background_top_normal, this.mSelectValue == 2));
        this.mSettingsItemBeanArrayList.add(getItemBean(5, getMusicJumpDetail(1), 1, R.color._2131297213_res_0x7f0903bd, this.mSelectValue == 1));
        this.mSettingsItemBeanArrayList.add(getItemBean(5, getMusicJumpDetail(3), 3, R.drawable.list_item_background_bottom_normal, this.mSelectValue == 3));
    }

    private String getJumpsTimeString(int i) {
        return getQuantityStr(R.plurals._2130903338_res_0x7f03012a, i);
    }
}
