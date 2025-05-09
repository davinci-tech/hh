package com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment;

import android.text.TextUtils;
import android.view.View;
import com.huawei.health.R;
import defpackage.dds;
import defpackage.dkv;
import defpackage.nrh;
import health.compact.a.util.LogUtil;

/* loaded from: classes3.dex */
public class RopeSkipIntermittentTrainingFragment extends RopeSkipSettingBaseFragment {
    private static final int DEEP_ENHANCED_FAT_BURNING_MODE_REST_DATE = 60;
    private static final int DEEP_FAT_BURNING_JUMP_DATE = 180;
    private static final int DEEP_FAT_BURNING_JUMP_MINUTE = 20;
    private static final int EASY_FAT_BURNING_JUMP_DATE = 90;
    private static final int EASY_FAT_BURNING_JUMP_MINUTE = 10;
    private static final int ENHANCED_FAT_BURNING_JUMP_DATE = 300;
    private static final int ENHANCED_FAT_BURNING_JUMP_MINUTE = 30;
    private static final int EXERCISE_EACH_GROUP_TIMES_DEFAULT = 0;
    private static final int GROUP_DEFAULT = 5;
    private static final int SPEEDING_EASY_FAT_BURNING_MODE_REST_DATE = 30;
    private static final int SPEEDING_EXERCISE_DATE = 30;
    private static final int SPEEDING_EXERCISE_MINUTE = 5;

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.RopeSkipSettingBaseFragment
    public void initData() {
        setTitle(getString(R.string._2130845806_res_0x7f02206e));
        super.initData();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.RopeSkipSettingBaseFragment
    /* renamed from: setItemClickListener */
    public void m380x1c99b493(View view, int i, dkv dkvVar) {
        char c;
        if (dkvVar == null || dkvVar.e() == 1) {
            return;
        }
        LogUtil.d("RopeSkipSettingBaseFragment", "setItemClickListener settingType is ", dkvVar.b());
        String b = dkvVar.b();
        b.hashCode();
        switch (b.hashCode()) {
            case -1744977625:
                if (b.equals("speeding_mode")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1623402023:
                if (b.equals("enhanced_fat_burning_jump_mode")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -750159515:
                if (b.equals("easy_fat_burning_jump_mode")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case -706952536:
                if (b.equals("intermitmode_custom_type")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 2120033999:
                if (b.equals("deep_fat_burning_jump_mode")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c == 0) {
            startIndoorEquipDisplay(30, 30);
            return;
        }
        if (c == 1) {
            startIndoorEquipDisplay(300, 60);
            return;
        }
        if (c == 2) {
            startIndoorEquipDisplay(90, 30);
            return;
        }
        if (c == 3) {
            addFragment(new RopeSkipIntermittentSettingFragment());
        } else if (c == 4) {
            startIndoorEquipDisplay(180, 60);
        } else {
            LogUtil.d("RopeSkipSettingBaseFragment", "setItemClickListener settingType is null");
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.RopeSkipSettingBaseFragment
    public void initSettingsData() {
        setTimeModeData();
    }

    private void setTimeModeData() {
        this.mSettingsItemBeanArrayList.clear();
        String str = getQuantityStr(R.plurals._2130903339_res_0x7f03012b, 5) + "  ";
        this.mSettingsItemBeanArrayList.add(getItemBean(7, R.string._2130845966_res_0x7f02210e, str + getGroupString(5), R.drawable.list_item_background_single_normal, "speeding_mode"));
        this.mSettingsItemBeanArrayList.add(getItemBean(7, R.string._2130845967_res_0x7f02210f, str + getGroupString(10), R.drawable.list_item_background_single_normal, "easy_fat_burning_jump_mode"));
        this.mSettingsItemBeanArrayList.add(getItemBean(7, R.string._2130845968_res_0x7f022110, str + getGroupString(20), R.drawable.list_item_background_single_normal, "deep_fat_burning_jump_mode"));
        this.mSettingsItemBeanArrayList.add(getItemBean(7, R.string._2130845969_res_0x7f022111, str + getGroupString(30), R.drawable.list_item_background_single_normal, "enhanced_fat_burning_jump_mode"));
        this.mSettingsItemBeanArrayList.add(getItemBean(7, R.string._2130845822_res_0x7f02207e, "", -1, "intermitmode_custom_type"));
    }

    private String getGroupString(int i) {
        String quantityStr = getQuantityStr(R.plurals._2130903232_res_0x7f0300c0, i);
        return TextUtils.isEmpty(quantityStr) ? "" : quantityStr.replace(" ", "");
    }

    private void startIndoorEquipDisplay(int i, int i2) {
        if (!dds.c().f()) {
            LogUtil.c("RopeSkipSettingBaseFragment", "Rope Device disconnect");
            nrh.b(this.mainActivity, R.string._2130845226_res_0x7f021e2a);
        } else {
            jumpToDisplayActivity(1, i, 0, i2, 5);
        }
    }
}
