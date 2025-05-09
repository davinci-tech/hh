package com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment;

import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import com.huawei.health.R;
import com.huawei.health.ecologydevice.ui.dialog.RopeSkipEnterNumberDialog;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.numberpicker.HealthNumberPicker;
import com.huawei.ui.commonui.popupview.PopViewList;
import com.huawei.ui.commonui.timepicker.HealthHourSecondNumberPicker;
import defpackage.dds;
import defpackage.dij;
import defpackage.dju;
import defpackage.dkv;
import defpackage.nrh;
import health.compact.a.LanguageUtil;
import health.compact.a.util.LogUtil;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

/* loaded from: classes3.dex */
public class RopeSkipIntermittentSettingFragment extends RopeSkipSettingBaseFragment {
    private static final int EXERCISE_EACH_GROUP_TIMES_DEFAULT = 100;
    private static final String EXERCISE_TIMES_EACH_GROUP_TYPE = "exercise_times_each_group_type";
    private static final String EXERCISE_TIME_EACH_GROUP_TYPE = "exercise_time_each_group_type";
    private static final int GROUP_DEFAULT = 5;
    private static final int GROUP_MAX = 20;
    private static final int GROUP_MIN = 2;
    private static final String GROUP_TYPE = "group_type";
    private static final float MAX_HOUR = 1.5f;
    private static final int MAX_HOUR_HIGH = 3;
    private static final int MAX_NUMBER = 20000;
    private static final int MAX_TIME = 5400;
    private static final int MAX_TIME_HIGH = 10800;
    private static final int NUMBER_TEN_THOUSAND = 2;
    private static final int QUERY_INTERMITTENT_JUMP_SETTING = 16;
    private static final String REST_EACH_GROUP_TYPE = "rest_each_group_type";
    private static final int SELECT_DATE_DEFAULT = 60;
    private static final int SELECT_DATE_MAX = 1800;
    private static final int SELECT_DATE_MAX_HIGH = 3600;
    private static final int SELECT_DATE_MIN = 30;
    private static final int SELECT_REST_DATE_MAX = 900;
    private static final int SELECT_REST_DATE_MIN = 5;
    private static final int TIMES_MODE = 1;
    private static final int TIME_MODE = 0;
    private int mIntermittentMode;
    protected HealthButton mStartSkipButton;
    protected HealthTextView mTipHealthTextView;
    private int mTimeExerciseEachGroup = 60;
    private int mTimeRestEachGroup = 60;
    private int mExerciseEachGroupNumber = 100;
    private int mExerciseGroups = 5;

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.RopeSkipSettingBaseFragment
    public void initView(View view) {
        super.initView(view);
        this.mTipHealthTextView = (HealthTextView) view.findViewById(R.id.voice_settings_tips);
        this.mStartSkipButton = (HealthButton) view.findViewById(R.id.rope_device_btn_start_training);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.RopeSkipSettingBaseFragment
    public void initData() {
        setTitle(getString(R.string._2130845822_res_0x7f02207e));
        setMarginTop();
        super.initData();
        this.mStartSkipButton.setVisibility(0);
        this.mStartSkipButton.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.RopeSkipIntermittentSettingFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RopeSkipIntermittentSettingFragment.this.m375x7831ea2f(view);
            }
        });
    }

    /* renamed from: lambda$initData$0$com-huawei-health-ecologydevice-ui-measure-fragment-ropefragment-RopeSkipIntermittentSettingFragment, reason: not valid java name */
    /* synthetic */ void m375x7831ea2f(View view) {
        LogUtil.d("RopeSkipSettingBaseFragment", "start skip");
        startIndoorEquipDisplay();
        ViewClickInstrumentation.clickOnView(view);
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
            case -1646881830:
                if (b.equals("intermitmode_type")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1432518515:
                if (b.equals(EXERCISE_TIME_EACH_GROUP_TYPE)) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -861049971:
                if (b.equals(REST_EACH_GROUP_TYPE)) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 954123224:
                if (b.equals(EXERCISE_TIMES_EACH_GROUP_TYPE)) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 1282509050:
                if (b.equals(GROUP_TYPE)) {
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
            showIntermitmodeSelectMenu(view);
            return;
        }
        if (c == 1) {
            showSetTargetTimeDialog(R.string._2130845826_res_0x7f022082, i, dkvVar.b(), 30, dij.g(dds.c().j()) ? 3600 : 1800);
            return;
        }
        if (c == 2) {
            showSetTargetTimeDialog(R.string._2130845827_res_0x7f022083, i, dkvVar.b(), 5, 900);
            return;
        }
        if (c == 3) {
            showSetTargetTimesDialog(i);
        } else if (c == 4) {
            showGroupsSetting(i);
        } else {
            LogUtil.d("RopeSkipSettingBaseFragment", "setItemClickListener settingType is null");
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.RopeSkipSettingBaseFragment
    public void initSettingsData() {
        dds.c().e("RopeSkipSettingBaseFragment", this, false);
        dds.c().d(3, 0, new int[]{16});
        setTimeModeData(0);
        setAdapterData();
    }

    private void setTimeModeData(int i) {
        String quantityString;
        int i2;
        String str;
        this.mIntermittentMode = i;
        this.mSettingsItemBeanArrayList.clear();
        dkv itemBean = getItemBean(6, R.string._2130845823_res_0x7f02207f, getString(i == 0 ? R.string._2130845824_res_0x7f022080 : R.string._2130845825_res_0x7f022081), R.drawable.list_item_background_single_normal);
        itemBean.d("intermitmode_type");
        this.mSettingsItemBeanArrayList.add(itemBean);
        this.mSettingsItemBeanArrayList.add(getDividerItemBean());
        if (i == 0) {
            int i3 = this.mTimeExerciseEachGroup;
            if (i3 == 0) {
                i3 = 60;
            }
            this.mTimeExerciseEachGroup = i3;
            quantityString = dju.e(i3);
            i2 = R.string._2130845826_res_0x7f022082;
            str = EXERCISE_TIME_EACH_GROUP_TYPE;
        } else {
            int i4 = this.mExerciseEachGroupNumber;
            if (i4 == 0) {
                i4 = 100;
            }
            this.mExerciseEachGroupNumber = i4;
            Resources resources = getResources();
            int i5 = this.mExerciseEachGroupNumber;
            quantityString = resources.getQuantityString(R.plurals._2130903274_res_0x7f0300ea, i5, Integer.valueOf(i5));
            i2 = R.string._2130845829_res_0x7f022085;
            str = EXERCISE_TIMES_EACH_GROUP_TYPE;
        }
        dkv itemBean2 = getItemBean(6, i2, quantityString, R.drawable.list_item_background_top_normal);
        itemBean2.d(str);
        this.mSettingsItemBeanArrayList.add(itemBean2);
        dkv itemBean3 = getItemBean(6, R.string._2130845827_res_0x7f022083, dju.e(this.mTimeRestEachGroup), R.color._2131297213_res_0x7f0903bd);
        itemBean3.d(REST_EACH_GROUP_TYPE);
        this.mSettingsItemBeanArrayList.add(itemBean3);
        dkv itemBean4 = getItemBean(6, R.string._2130845828_res_0x7f022084, String.valueOf(this.mExerciseGroups), R.drawable.list_item_background_bottom_normal);
        itemBean4.d(GROUP_TYPE);
        this.mSettingsItemBeanArrayList.add(itemBean4);
        refreshView();
    }

    private void refreshView() {
        String valueOf;
        int i;
        if (this.mIntermittentMode == 0) {
            int i2 = this.mTimeExerciseEachGroup;
            int i3 = this.mTimeRestEachGroup;
            int i4 = this.mExerciseGroups;
            if (dij.g(dds.c().j())) {
                valueOf = String.valueOf(3);
                i = MAX_TIME_HIGH;
            } else {
                valueOf = String.valueOf(MAX_HOUR);
                i = MAX_TIME;
            }
            if ((i2 + i3) * i4 > i) {
                refreshDataEnableView(getQuantityStr(R.plurals._2130903518_res_0x7f0301de, i, valueOf));
                return;
            } else {
                refreshDataDisableView();
                return;
            }
        }
        if (this.mExerciseEachGroupNumber * this.mExerciseGroups > 20000) {
            int i5 = LanguageUtil.h(BaseApplication.getContext()) ? 2 : 20000;
            refreshDataEnableView(getQuantityStr(R.plurals._2130903517_res_0x7f0301dd, i5, String.valueOf(i5)));
        } else {
            refreshDataDisableView();
        }
    }

    private void refreshDataEnableView(String str) {
        this.mTipHealthTextView.setVisibility(0);
        this.mTipHealthTextView.setText(str);
        this.mStartSkipButton.setClickable(false);
        this.mStartSkipButton.setTextColor(getResources().getColor(R.color._2131296928_res_0x7f0902a0));
    }

    private void refreshDataDisableView() {
        this.mTipHealthTextView.setVisibility(8);
        this.mStartSkipButton.setClickable(true);
        this.mStartSkipButton.setTextColor(getResources().getColor(R.color._2131296927_res_0x7f09029f));
    }

    private void showIntermitmodeSelectMenu(View view) {
        ArrayList arrayList = new ArrayList(Arrays.asList(getResources().getString(R.string._2130845824_res_0x7f022080), getResources().getString(R.string._2130845825_res_0x7f022081)));
        new PopViewList(this.mainActivity, view, arrayList).e(new PopViewList.PopViewClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.RopeSkipIntermittentSettingFragment$$ExternalSyntheticLambda0
            @Override // com.huawei.ui.commonui.popupview.PopViewList.PopViewClickListener
            public final void setOnClick(int i) {
                RopeSkipIntermittentSettingFragment.this.m377xf423e571(i);
            }
        });
    }

    /* renamed from: lambda$showIntermitmodeSelectMenu$1$com-huawei-health-ecologydevice-ui-measure-fragment-ropefragment-RopeSkipIntermittentSettingFragment, reason: not valid java name */
    /* synthetic */ void m377xf423e571(int i) {
        setTimeModeData(i);
        setAdapterData();
    }

    private void showSetTargetTimeDialog(int i, final int i2, final String str, int i3, int i4) {
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.mainActivity);
        final HealthHourSecondNumberPicker healthHourSecondNumberPicker = new HealthHourSecondNumberPicker(this.mainActivity, i3, i4, 60);
        builder.d(i).czg_(healthHourSecondNumberPicker).czc_(R.string._2130844419_res_0x7f021b03, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.RopeSkipIntermittentSettingFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        }).cze_(R.string._2130844420_res_0x7f021b04, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.RopeSkipIntermittentSettingFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                RopeSkipIntermittentSettingFragment.this.m378x139fa1f5(str, healthHourSecondNumberPicker, i2, view);
            }
        });
        builder.e().show();
    }

    /* renamed from: lambda$showSetTargetTimeDialog$3$com-huawei-health-ecologydevice-ui-measure-fragment-ropefragment-RopeSkipIntermittentSettingFragment, reason: not valid java name */
    /* synthetic */ void m378x139fa1f5(String str, HealthHourSecondNumberPicker healthHourSecondNumberPicker, int i, View view) {
        if (EXERCISE_TIME_EACH_GROUP_TYPE.equals(str)) {
            int selectedTime = healthHourSecondNumberPicker.getSelectedTime();
            this.mTimeExerciseEachGroup = selectedTime;
            setDetailPosition(i, dju.e(selectedTime));
        } else {
            int selectedTime2 = healthHourSecondNumberPicker.getSelectedTime();
            this.mTimeRestEachGroup = selectedTime2;
            setDetailPosition(i, dju.e(selectedTime2));
        }
        setAdapterData();
        refreshView();
        ViewClickInstrumentation.clickOnView(view);
    }

    private void showSetTargetTimesDialog(final int i) {
        RopeSkipEnterNumberDialog e = new RopeSkipEnterNumberDialog.d(this.mainActivity, new RopeSkipEnterNumberDialog.OnConfirmClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.RopeSkipIntermittentSettingFragment$$ExternalSyntheticLambda6
            @Override // com.huawei.health.ecologydevice.ui.dialog.RopeSkipEnterNumberDialog.OnConfirmClickListener
            public final void onConfirmClick(int i2) {
                RopeSkipIntermittentSettingFragment.this.m379x489cb18d(i, i2);
            }
        }).e();
        if (e != null) {
            e.show();
        }
    }

    /* renamed from: lambda$showSetTargetTimesDialog$4$com-huawei-health-ecologydevice-ui-measure-fragment-ropefragment-RopeSkipIntermittentSettingFragment, reason: not valid java name */
    /* synthetic */ void m379x489cb18d(int i, int i2) {
        this.mExerciseEachGroupNumber = i2;
        setDetailPosition(i, getQuantityStr(R.plurals._2130903274_res_0x7f0300ea, i2));
        setAdapterData();
        refreshView();
    }

    private void showGroupsSetting(int i) {
        View inflate = getLayoutInflater().inflate(R.layout.health_picker_setting_dialog, (ViewGroup) null);
        HealthNumberPicker healthNumberPicker = (HealthNumberPicker) inflate.findViewById(R.id.pause_trackline_type_picker);
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        for (int i3 = 2; i3 <= 20; i3++) {
            arrayList.add(String.valueOf(i3));
            if (this.mExerciseGroups == i3) {
                i2 = i3 - 2;
            }
        }
        healthNumberPicker.setDisplayedValues((String[]) arrayList.toArray(new String[arrayList.size()]));
        healthNumberPicker.setMinValue(0);
        healthNumberPicker.setMaxValue(arrayList.size() - 1);
        healthNumberPicker.setValue(i2);
        showCustomViewDialog(i, R.string._2130845828_res_0x7f022084, inflate, healthNumberPicker);
    }

    private void showCustomViewDialog(final int i, int i2, View view, final HealthNumberPicker healthNumberPicker) {
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.mainActivity);
        builder.a(getString(i2)).czg_(view).cze_(R.string._2130844420_res_0x7f021b04, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.RopeSkipIntermittentSettingFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RopeSkipIntermittentSettingFragment.this.m376x58cf5ac9(healthNumberPicker, i, view2);
            }
        }).czc_(R.string._2130844419_res_0x7f021b03, new View.OnClickListener() { // from class: com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.RopeSkipIntermittentSettingFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                RopeSkipIntermittentSettingFragment.lambda$showCustomViewDialog$6(view2);
            }
        });
        builder.e().show();
    }

    /* renamed from: lambda$showCustomViewDialog$5$com-huawei-health-ecologydevice-ui-measure-fragment-ropefragment-RopeSkipIntermittentSettingFragment, reason: not valid java name */
    /* synthetic */ void m376x58cf5ac9(HealthNumberPicker healthNumberPicker, int i, View view) {
        LogUtil.d("RopeSkipSettingBaseFragment", "confirm button is click");
        int value = healthNumberPicker.getValue() + 2;
        this.mExerciseGroups = value;
        setDetailPosition(i, String.valueOf(value));
        setAdapterData();
        refreshView();
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void lambda$showCustomViewDialog$6(View view) {
        LogUtil.d("RopeSkipSettingBaseFragment", "cancel button is click");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void startIndoorEquipDisplay() {
        if (!dds.c().f()) {
            LogUtil.c("RopeSkipSettingBaseFragment", "Rope Device disconnect");
            nrh.b(this.mainActivity, R.string._2130845226_res_0x7f021e2a);
            return;
        }
        int i = this.mIntermittentMode;
        jumpToDisplayActivity(i == 0 ? 1 : 0, i == 0 ? this.mTimeExerciseEachGroup : 0, i == 0 ? 0 : this.mExerciseEachGroupNumber, this.mTimeRestEachGroup, this.mExerciseGroups);
        this.mainActivity.onBackPressed();
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.RopeSkipSettingBaseFragment
    protected void onReceiveMessage(int i, HashMap<Integer, Object> hashMap) {
        if (i != 27) {
            return;
        }
        int intValue = getIntValue(hashMap, 40055).intValue();
        this.mTimeExerciseEachGroup = intValue;
        this.mIntermittentMode = intValue == 0 ? 1 : 0;
        this.mExerciseEachGroupNumber = getIntValue(hashMap, 40053).intValue();
        this.mTimeRestEachGroup = getIntValue(hashMap, 40056).intValue();
        this.mExerciseGroups = getIntValue(hashMap, 40054).intValue();
        sendEmptyHandleMessage(101);
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.RopeSkipSettingBaseFragment
    protected void refreshPage() {
        super.refreshPage();
        if (this.mainActivity == null) {
            LogUtil.c("RopeSkipSettingBaseFragment", "refreshPage mainActivity is null");
        } else {
            setTimeModeData(this.mIntermittentMode);
            setAdapterData();
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.measure.fragment.ropefragment.RopeSkipSettingBaseFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        dds.c().c("RopeSkipSettingBaseFragment", false);
    }
}
