package com.huawei.health.device.ui.measure.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.health.device.connectivity.comm.MeasurableDevice;
import com.huawei.health.device.model.HealthDevice;
import com.huawei.health.device.open.data.model.HealthData;
import com.huawei.health.device.ui.DeviceMainActivity;
import com.huawei.health.ecologydevice.manager.ResourceManager;
import com.huawei.health.ecologydevice.ui.BaseFragment;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hihealth.HiUserInfo;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.datepicker.HealthDatePickerDialog;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.numberpicker.HealthNumberPicker;
import com.huawei.ui.commonui.radiobutton.HealthRadioButton;
import defpackage.ceo;
import defpackage.cfe;
import defpackage.cfi;
import defpackage.cgs;
import defpackage.cjx;
import defpackage.ckn;
import defpackage.ckq;
import defpackage.cmq;
import defpackage.cpa;
import defpackage.cxh;
import defpackage.dcz;
import defpackage.nrh;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import health.compact.a.UnitUtil;
import health.compact.a.Utils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.UUID;

/* loaded from: classes3.dex */
public class GuestUserInfoGuideFragment extends BaseFragment implements View.OnClickListener {
    private static final int ADULT_AGE_HEIGHT_LIMIT = 80;
    private static final int ADULT_AGE_LOWER_LIMIT = 18;
    private static final int AGE_DIVISION = 10000;
    private static final String ARGUMENTS_KEY = "HealthData";
    private static final int BIRTHDAY_DIFF = 1;
    private static final int BIRTHDAY_TYPE = 3;
    private static final int DEFAULT_AGE = 23;
    private static final int DEFAULT_DAY = 1;
    private static final int DEFAULT_HEIGHT = 110;
    private static final int DEFAULT_HIUSER_FEMALE_HEIGHT = 170;
    private static final int DEFAULT_HIUSER_HEIGHT = 170;
    private static final int DEFAULT_MONTH = 0;
    private static final int DEFAULT_ONE = 1;
    private static final int DEFAULT_WEIGHT = 70;
    private static final int DEFAULT_YEAR = 1992;
    private static final int DEFAULT_ZERO = 0;
    private static final int GENDER_TYPE = 2;
    private static final String GUEST_MEASURE = "guest_measure";
    private static final String GUEST_USER = "guest_user";
    private static final int HEIGHT_FOR_INDEX_END = 251;
    private static final int HEIGHT_FOR_INDEX_START = 10;
    private static final int HEIGHT_FT_ARRAY_LENGTH = 8;
    private static final int HEIGHT_FT_INS_CONVERY_END = 7;
    private static final int HEIGHT_FT_INS_CONVERY_START = 5;
    private static final int HEIGHT_INS_ARRAY_LENGTH = 12;
    private static final int HEIGHT_OFFSET = 50;
    private static final int HEIGHT_RIDE = 100;
    private static final int HEIGHT_TYPE = 0;
    private static final double LAST_BODY_FAT = 0.5d;
    private static final int MALE = 1;
    private static final int MAX_AGE = 150;
    private static final String TAG = "GuestUserInfoGuideFragment";
    private static final int VALUE_TYPE_BIRTHDAY = 3;
    private static final int VALUE_TYPE_GENDER = 2;
    private static final int WATER_RATE_LIMIT = 100;
    private static final String WEIGHT_BEAN = "weightBean";
    private LinearLayout mBirthdayHorizontalLayout;
    private LinearLayout mBirthdayLayout;
    private HealthTextView mBirthdayTipText;
    private LinearLayout mBirthdayVerticalLayout;
    private int mDay;
    private ContentValues mDeviceInfo;
    private int mFoot;
    private int mGender;
    private String mGenderFemale;
    private HealthRadioButton mGenderFemaleImgView;
    private LinearLayout mGenderLayout;
    private String mGenderMale;
    private HealthRadioButton mGenderMaleImgView;
    private HealthButton mGuestMeasureCompleteText;
    private LinearLayout mHeightLayout;
    private HiUserInfo mHiUserInfo;
    private int mInch;
    private boolean mIsBirthdaySelected;
    private boolean mIsGenderSelected;
    private boolean mIsHeightSelected;
    private HealthDevice.HealthDeviceKind mKind;
    private int mMonth;
    private String mProductId;
    private dcz mProductInfo;
    private RelativeLayout mSettingsGenderFemale;
    private RelativeLayout mSettingsGenderMale;
    private String mUniqueId;
    private cfi mUser;
    private HealthTextView mUserBirthDayText;
    private ImageView mUserBirthdayImage;
    private ImageView mUserGenderImage;
    private HealthTextView mUserGenderText;
    private ImageView mUserHeightImage;
    private HealthTextView mUserHeightText;
    private int mYear;
    private Dialog mDialogGender = null;
    private boolean mIsDeviceMeasure = false;
    private List<Dialog> mDialogContainers = new ArrayList();

    private void showCustomTitleBar() {
        super.setTitle(getActivity().getResources().getString(R.string.IDS_hw_device_weight_guest_measurement));
    }

    private void initView() {
        initImageViews();
        HealthButton healthButton = (HealthButton) this.child.findViewById(R.id.guest_info_fragment_device_complete_btn);
        this.mGuestMeasureCompleteText = healthButton;
        healthButton.setOnClickListener(this);
        boolean z = getArguments().getBoolean("deviceMgrMeasure", false);
        this.mIsDeviceMeasure = z;
        if (z) {
            this.mGuestMeasureCompleteText.setText(R.string.IDS_device_measureactivity_guide_start_measure);
        } else {
            this.mGuestMeasureCompleteText.setText(R.string._2130841938_res_0x7f021152);
        }
        setLanguageImage();
        this.mGenderMale = getResources().getString(R.string._2130841523_res_0x7f020fb3);
        this.mGenderFemale = getResources().getString(R.string._2130841525_res_0x7f020fb5);
    }

    private void initData() {
        MeasurableDevice e;
        this.mProductId = getArguments().getString("productId");
        ContentValues contentValues = (ContentValues) getArguments().getParcelable("commonDeviceInfo");
        this.mDeviceInfo = contentValues;
        if (contentValues != null) {
            this.mProductId = contentValues.getAsString("productId");
            this.mUniqueId = this.mDeviceInfo.getAsString("uniqueId");
        } else {
            LogUtil.h(TAG, "initData mDeviceInfo is empty");
        }
        if (TextUtils.isEmpty(this.mProductId)) {
            LogUtil.h(TAG, "initData mProductId is empty");
            this.mProductId = getArguments().getString("productId");
        }
        if (!TextUtils.isEmpty(this.mProductId) && TextUtils.isEmpty(this.mUniqueId) && (e = ceo.d().e(this.mProductId, false)) != null) {
            this.mUniqueId = e.getUniqueId();
        }
        if (TextUtils.isEmpty(this.mProductId) || TextUtils.isEmpty(this.mUniqueId)) {
            LogUtil.h(TAG, "initData mProductId or mUniqueId is empty");
        }
        dcz d = ResourceManager.e().d(this.mProductId);
        this.mProductInfo = d;
        if (d != null) {
            this.mKind = d.l();
        } else {
            LogUtil.h(TAG, "initData mProductInfo is null");
        }
        this.mHiUserInfo.setHeight(170);
        this.mHiUserInfo.setWeight(70.0f);
        this.mHiUserInfo.setAge(23);
        this.mHiUserInfo.setGender(1);
        showCustomTitleBar();
    }

    private void setLanguageImage() {
        if (LanguageUtil.bc(getActivity())) {
            this.mUserGenderImage.setBackground(getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
            this.mUserHeightImage.setBackground(getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
            this.mUserBirthdayImage.setBackground(getResources().getDrawable(R.drawable._2131427841_res_0x7f0b0201));
        } else {
            this.mUserGenderImage.setBackground(getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
            this.mUserHeightImage.setBackground(getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
            this.mUserBirthdayImage.setBackground(getResources().getDrawable(R.drawable._2131427842_res_0x7f0b0202));
        }
    }

    private void initImageViews() {
        this.mUserGenderImage = (ImageView) this.child.findViewById(R.id.user_info_fragment_set_gender_image);
        this.mUserHeightImage = (ImageView) this.child.findViewById(R.id.user_info_fragment_set_height_image);
        if (nsn.s()) {
            this.mUserBirthdayImage = (ImageView) this.child.findViewById(R.id.user_info_fragment_set_birthday_image_vertical);
        } else {
            this.mUserBirthdayImage = (ImageView) this.child.findViewById(R.id.user_info_fragment_set_birthday_image);
        }
    }

    private void initLayout() {
        this.mGenderLayout = (LinearLayout) this.child.findViewById(R.id.hw_show_userinfo_gender_layout);
        this.mHeightLayout = (LinearLayout) this.child.findViewById(R.id.hw_show_userinfo_height_layout);
        this.mBirthdayLayout = (LinearLayout) this.child.findViewById(R.id.hw_show_userinfo_birthday_layout);
        this.mGenderLayout.setOnClickListener(this);
        this.mHeightLayout.setOnClickListener(this);
        this.mBirthdayLayout.setOnClickListener(this);
        this.mBirthdayVerticalLayout = (LinearLayout) this.child.findViewById(R.id.hw_show_birthday_vertical_layout);
        this.mBirthdayHorizontalLayout = (LinearLayout) this.child.findViewById(R.id.hw_show_birthday_horizontal_layout);
        if (nsn.s()) {
            this.mBirthdayVerticalLayout.setVisibility(0);
            this.mBirthdayHorizontalLayout.setVisibility(8);
        } else {
            this.mBirthdayVerticalLayout.setVisibility(8);
            this.mBirthdayHorizontalLayout.setVisibility(0);
        }
    }

    private void initInfoText() {
        this.mUserGenderText = (HealthTextView) this.child.findViewById(R.id.hw_show_userinfo_gender);
        this.mUserHeightText = (HealthTextView) this.child.findViewById(R.id.hw_show_userinfo_height);
        this.mBirthdayTipText = (HealthTextView) this.child.findViewById(R.id.hw_show_userinfo_birthday_tip);
        if (nsn.s()) {
            this.mUserBirthDayText = (HealthTextView) this.child.findViewById(R.id.hw_show_userinfo_birthday_vertical);
        } else {
            this.mUserBirthDayText = (HealthTextView) this.child.findViewById(R.id.hw_show_userinfo_birthday);
        }
    }

    private void showHeightInchEmuiDialog() {
        View inflate = getLayoutInflater().inflate(R.layout.health_healthdata_inch_height_dialog_v9, (ViewGroup) null);
        HealthNumberPicker healthNumberPicker = (HealthNumberPicker) inflate.findViewById(R.id.height_ft_number_picker_v9);
        String[] strArr = new String[8];
        int i = 0;
        while (i < 8) {
            StringBuilder sb = new StringBuilder();
            int i2 = i + 1;
            sb.append(UnitUtil.e(i2, 1, 0));
            sb.append(getString(R.string._2130841417_res_0x7f020f49));
            strArr[i] = sb.toString();
            i = i2;
        }
        healthNumberPicker.setDisplayedValues(strArr);
        healthNumberPicker.setMinValue(0);
        healthNumberPicker.setMaxValue(7);
        HealthNumberPicker healthNumberPicker2 = (HealthNumberPicker) inflate.findViewById(R.id.height_inch_number_picker_v9);
        String[] strArr2 = new String[12];
        for (int i3 = 0; i3 < 12; i3++) {
            strArr2[i3] = UnitUtil.e(i3, 1, 0) + " " + getString(R.string._2130841897_res_0x7f021129);
        }
        healthNumberPicker2.setDisplayedValues(strArr2);
        healthNumberPicker2.setMinValue(0);
        healthNumberPicker2.setMaxValue(11);
        int[] iArr = {5, 7};
        HiUserInfo hiUserInfo = this.mHiUserInfo;
        if (hiUserInfo != null && hiUserInfo.getHeight() > 50) {
            int[] j = UnitUtil.j(this.mHiUserInfo.getHeight() / 100.0d);
            if (j[0] > 0 && j[1] >= 0) {
                iArr = j;
            }
        }
        healthNumberPicker.setValue(iArr[0] - 1);
        healthNumberPicker2.setValue(iArr[1]);
        heightInchEmuiDialogBuilder(inflate, healthNumberPicker, healthNumberPicker2);
    }

    private void heightInchEmuiDialogBuilder(View view, final HealthNumberPicker healthNumberPicker, final HealthNumberPicker healthNumberPicker2) {
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.mainActivity);
        builder.a(getString(R.string._2130841526_res_0x7f020fb6)).czg_(view).cze_(R.string._2130841938_res_0x7f021152, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.GuestUserInfoGuideFragment$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                GuestUserInfoGuideFragment.this.m136xf2298b01(healthNumberPicker, healthNumberPicker2, view2);
            }
        }).czc_(R.string._2130841939_res_0x7f021153, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.GuestUserInfoGuideFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                GuestUserInfoGuideFragment.lambda$heightInchEmuiDialogBuilder$1(view2);
            }
        });
        CustomViewDialog e = builder.e();
        e.show();
        this.mDialogContainers.add(e);
    }

    /* renamed from: lambda$heightInchEmuiDialogBuilder$0$com-huawei-health-device-ui-measure-fragment-GuestUserInfoGuideFragment, reason: not valid java name */
    /* synthetic */ void m136xf2298b01(HealthNumberPicker healthNumberPicker, HealthNumberPicker healthNumberPicker2, View view) {
        this.mFoot = healthNumberPicker.getValue() + 1;
        this.mInch = healthNumberPicker2.getValue();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(getResources().getString(R.string._2130841895_res_0x7f021127, UnitUtil.e(this.mFoot, 1, 0)));
        stringBuffer.append(" ");
        stringBuffer.append(getResources().getString(R.string._2130841896_res_0x7f021128, UnitUtil.e(this.mInch, 1, 0)));
        this.mUserHeightText.setText(stringBuffer.toString());
        double d = UnitUtil.d(this.mFoot, 1);
        double d2 = UnitUtil.d(this.mInch, 0);
        this.mIsHeightSelected = true;
        saveHeightValue((int) Math.rint((d * 100.0d) + d2));
        refreshButtonStatus();
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void lambda$heightInchEmuiDialogBuilder$1(View view) {
        LogUtil.a(TAG, "heightInchEmuiDialogBuilder click NegativeButton");
        ViewClickInstrumentation.clickOnView(view);
    }

    private void showHeightValueDialog(final int i) {
        View inflate = getLayoutInflater().inflate(R.layout.health_healthdata_userinfo_dialog_set_v9, (ViewGroup) null);
        final HealthNumberPicker healthNumberPicker = (HealthNumberPicker) inflate.findViewById(R.id.userinfo_number_picker_v9);
        ArrayList<String> createHeightArrays = createHeightArrays();
        healthNumberPicker.setDisplayedValues((String[]) createHeightArrays.toArray(new String[createHeightArrays.size()]));
        healthNumberPicker.setMinValue(0);
        healthNumberPicker.setMaxValue(createHeightArrays.size() - 1);
        HiUserInfo hiUserInfo = this.mHiUserInfo;
        if (hiUserInfo != null && hiUserInfo.getHeight() >= 50) {
            LogUtil.c(TAG, "mHiUserInfo.getHeight()=", Integer.valueOf(this.mHiUserInfo.getHeight()));
            healthNumberPicker.setValue(this.mHiUserInfo.getHeight() - 10);
        } else {
            healthNumberPicker.setValue(110);
        }
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.mainActivity);
        builder.a(getString(R.string._2130841526_res_0x7f020fb6)).czg_(inflate).cze_(R.string._2130841938_res_0x7f021152, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.GuestUserInfoGuideFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GuestUserInfoGuideFragment.this.m138x5fe69232(healthNumberPicker, i, view);
            }
        }).czc_(R.string._2130841939_res_0x7f021153, new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.GuestUserInfoGuideFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GuestUserInfoGuideFragment.lambda$showHeightValueDialog$3(view);
            }
        });
        CustomViewDialog e = builder.e();
        e.show();
        this.mDialogContainers.add(e);
    }

    /* renamed from: lambda$showHeightValueDialog$2$com-huawei-health-device-ui-measure-fragment-GuestUserInfoGuideFragment, reason: not valid java name */
    /* synthetic */ void m138x5fe69232(HealthNumberPicker healthNumberPicker, int i, View view) {
        LogUtil.a(TAG, "get hselect=", Integer.valueOf(healthNumberPicker.getValue()));
        this.mUserHeightText.setText(getString(R.string._2130837631_res_0x7f02007f, UnitUtil.e(healthNumberPicker.getValue() + 10, 1, 0)));
        this.mIsHeightSelected = true;
        saveValue(healthNumberPicker.getValue() + 10, i);
        refreshButtonStatus();
        ViewClickInstrumentation.clickOnView(view);
    }

    static /* synthetic */ void lambda$showHeightValueDialog$3(View view) {
        LogUtil.a(TAG, "showHeightValueDialog click NegativeButton");
        ViewClickInstrumentation.clickOnView(view);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (layoutInflater == null) {
            LogUtil.h(TAG, "onCreateView inflater is null");
            return null;
        }
        ViewGroup viewGroup2 = super.onCreateView(layoutInflater, viewGroup, bundle) instanceof ViewGroup ? (ViewGroup) super.onCreateView(layoutInflater, viewGroup, bundle) : null;
        this.child = layoutInflater.inflate(R.layout.hygride_product_guest_measure_layout, viewGroup, false);
        this.mHiUserInfo = new HiUserInfo();
        this.mUser = new cfi();
        initView();
        initData();
        initLayout();
        initInfoText();
        if (viewGroup2 != null) {
            viewGroup2.addView(this.child);
        }
        return viewGroup2;
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        this.mIsHeightSelected = false;
        this.mIsBirthdaySelected = false;
        this.mIsGenderSelected = false;
        this.mUserGenderText.setText("");
        this.mUserBirthDayText.setText("");
        this.mUserHeightText.setText("");
        this.mBirthdayTipText.setVisibility(8);
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        for (Dialog dialog : this.mDialogContainers) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }
        }
    }

    private void setUserData() {
        this.mUser.d(UUID.randomUUID().toString());
        this.mUser.b(getString(R.string.IDS_hw_device_weight_guest_measurement));
        this.mUser.d(this.mHiUserInfo.getHeight());
        this.mUser.a((byte) this.mHiUserInfo.getGender());
        this.mUser.b(this.mHiUserInfo.getBirthday());
        this.mUser.a(this.mHiUserInfo.getAge());
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.mGuestMeasureCompleteText) {
            if (nsn.o()) {
                LogUtil.h(TAG, "clickRequestDeviceShareItem click too fast.");
                ViewClickInstrumentation.clickOnView(view);
                return;
            } else if (this.mIsGenderSelected && this.mIsBirthdaySelected && this.mIsHeightSelected) {
                setUserData();
                if (this.mIsDeviceMeasure) {
                    startBleMeasure();
                } else {
                    gotoWeightDetailActivity(getActivity(), this.mUser);
                }
            } else {
                nrh.b(getActivity(), R.string.IDS_device_guest_measure_tip);
            }
        } else if (view == this.mGenderLayout) {
            showGenderPickerDialog();
        } else if (view == this.mHeightLayout) {
            if (UnitUtil.h()) {
                LogUtil.a(TAG, "enter showInchDialog");
                showHeightInchEmuiDialog();
            } else {
                LogUtil.a(TAG, "enter showValueSetDialog()");
                showHeightValueDialog(0);
            }
        } else if (view == this.mBirthdayLayout) {
            showDateDialog();
        } else {
            LogUtil.h(TAG, "Click invalid.");
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void saveHeightValue(int i) {
        this.mHiUserInfo.setHeight(i);
    }

    private void saveValue(int i, int i2) {
        if (i2 == 0) {
            this.mHiUserInfo.setHeight(i);
        } else if (i2 == 2) {
            setUserGender(i);
        } else if (i2 == 3) {
            this.mHiUserInfo.setBirthday(i);
        } else {
            LogUtil.h(TAG, "saveValue invalid.");
        }
        this.mHiUserInfo.setCreateTime(System.currentTimeMillis());
        this.mHiUserInfo.setModifiedIntent(536870912);
    }

    private void setUserGender(int i) {
        LogUtil.c(TAG, "gender==", Integer.valueOf(i));
        HiUserInfo hiUserInfo = this.mHiUserInfo;
        if (hiUserInfo != null) {
            hiUserInfo.setGender(i);
        }
    }

    private ArrayList<String> createHeightArrays() {
        ArrayList<String> arrayList = new ArrayList<>(10);
        for (int i = 10; i < HEIGHT_FOR_INDEX_END; i++) {
            arrayList.add(getResources().getString(R.string._2130837631_res_0x7f02007f, UnitUtil.e(i, 1, 0)));
        }
        return arrayList;
    }

    private void startBleMeasure() {
        if (!(ceo.d().d(this.mUniqueId, true) instanceof cxh)) {
            showSelectBindDeviceDialog();
        } else {
            gotoMeasureFragment();
        }
    }

    private void showSelectBindDeviceDialog() {
        Activity activity = BaseApplication.getActivity();
        if (activity instanceof DeviceMainActivity) {
            ((DeviceMainActivity) activity).a(this.mProductId);
        }
    }

    private void gotoMeasureFragment() {
        HealthDevice.HealthDeviceKind healthDeviceKind = this.mKind;
        if (healthDeviceKind == null) {
            LogUtil.h(TAG, "gotoMeasureFragment mKind is null");
            return;
        }
        BaseFragment a2 = ckq.a(healthDeviceKind.name());
        if (a2 != null) {
            Bundle bundle = new Bundle();
            bundle.putString("view", "measure");
            bundle.putString("kind", this.mKind.name());
            bundle.putString("productId", this.mProductId);
            bundle.putParcelable("commonDeviceInfo", this.mDeviceInfo);
            bundle.putString("goback", "hygride");
            bundle.putBoolean("status", false);
            bundle.putBoolean("deviceMgrMeasure", false);
            bundle.putInt("type", -1);
            bundle.putBoolean(GUEST_MEASURE, true);
            bundle.putSerializable(GUEST_USER, this.mUser);
            a2.setArguments(bundle);
            switchFragment(a2);
        }
    }

    private void gotoWeightDetailActivity(Context context, cfi cfiVar) {
        if (context == null) {
            LogUtil.h(TAG, "gotoWeightDetailActivity context is null");
            return;
        }
        HealthDevice c = cjx.e().c(this.mUniqueId);
        Serializable serializable = getArguments().getSerializable(ARGUMENTS_KEY);
        if (serializable == null) {
            LogUtil.a(TAG, "gotoWeightDetailActivity serializable is null");
            return;
        }
        if (!(serializable instanceof HealthData)) {
            LogUtil.a(TAG, "saveWeightData Failed device null");
            return;
        }
        ckn.e(true);
        cfe c2 = ckn.c(c, (HealthData) serializable, this.mUser, 10006);
        if (c2 == null) {
            LogUtil.h(TAG, "weightBean is null");
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(BaseApplication.getAppPackage(), "com.huawei.ui.main.stories.health.activity.healthdata.WeightDetailActivity");
        constructIntentForWeight(c2, cfiVar, intent);
        try {
            context.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            LogUtil.b(TAG, "gotoWeightDetailActivity startActivity ActivityNotFoundException occur.");
        }
        popupFragment(HagridDeviceManagerFragment.class);
    }

    private void constructIntentForWeight(cfe cfeVar, cfi cfiVar, Intent intent) {
        cfeVar.a(cpa.f.get(this.mProductId).intValue());
        cfeVar.e(this.mUniqueId);
        intent.putExtra(WEIGHT_BEAN, cfeVar);
        intent.putExtra(GUEST_MEASURE, true);
        intent.putExtra("weight", cfeVar.ax());
        intent.putExtra("bodyFat", cfeVar.a());
        intent.putExtra("weightTime", cfeVar.au());
        intent.putExtra("deleteTime", cfeVar.au());
        if (cfeVar.ap() > 0.0d && cfeVar.ap() <= 100.0d) {
            intent.putExtra("water", cfeVar.ap());
        } else {
            intent.putExtra("water", cfeVar.al());
        }
        intent.putExtra("deleteEndTime", cfeVar.av());
        intent.putExtra("BITag", 1);
        intent.putExtra("resistance", cfeVar.ae());
        if (cfeVar.t() > 0) {
            intent.putExtra("userHeight", cfeVar.t());
        } else {
            intent.putExtra("userHeight", cfiVar.d());
        }
        intent.putExtra("isShowBodyFat", cfeVar.a() >= 0.5d);
        intent.putExtra("isShowInput", false);
        intent.putExtra("dataType", cfeVar.l());
    }

    private void showDateDialog() {
        if (this.mYear <= 0) {
            this.mYear = DEFAULT_YEAR;
            this.mMonth = 0;
            this.mDay = 1;
        }
        HealthDatePickerDialog healthDatePickerDialog = new HealthDatePickerDialog(getActivity(), new HealthDatePickerDialog.DateSelectedListener() { // from class: com.huawei.health.device.ui.measure.fragment.GuestUserInfoGuideFragment$$ExternalSyntheticLambda2
            @Override // com.huawei.ui.commonui.datepicker.HealthDatePickerDialog.DateSelectedListener
            public final void onDateSelected(int i, int i2, int i3) {
                GuestUserInfoGuideFragment.this.m137xc568ba4c(i, i2, i3);
            }
        }, new GregorianCalendar(this.mYear, this.mMonth, this.mDay));
        int i = Calendar.getInstance().get(1);
        healthDatePickerDialog.e(i - 150, i);
        healthDatePickerDialog.c(true);
        healthDatePickerDialog.show();
    }

    /* renamed from: lambda$showDateDialog$4$com-huawei-health-device-ui-measure-fragment-GuestUserInfoGuideFragment, reason: not valid java name */
    /* synthetic */ void m137xc568ba4c(int i, int i2, int i3) {
        if (isBirthdayValid(i, i2, i3)) {
            this.mIsBirthdaySelected = true;
            refreshBirthday(i, i2, i3);
        }
    }

    private boolean isBirthdayValid(int i, int i2, int i3) {
        if (isMoreTodayCalendar(i, i2, i3)) {
            nrh.c(this.mainActivity.getApplicationContext(), getString(R.string._2130837709_res_0x7f0200cd));
            return false;
        }
        int i4 = (cpa.r(this.mProductId) || cpa.w(this.mProductId) || cpa.v(this.mProductId) || !Utils.o()) ? 80 : 65;
        int i5 = (!cgs.d(this.mUniqueId) || Utils.o()) ? 18 : 6;
        int age = getAge(i, i2, i3);
        if (age < i5 || age > i4) {
            this.mBirthdayTipText.setText(String.format(Locale.ENGLISH, getString(R.string.IDS_device_age_tips, Integer.valueOf(i5), Integer.valueOf(i4)), new Object[0]));
            this.mBirthdayTipText.setVisibility(0);
            return true;
        }
        this.mBirthdayTipText.setVisibility(8);
        return true;
    }

    private void refreshBirthday(int i, int i2, int i3) {
        this.mYear = i;
        this.mMonth = i2;
        this.mDay = i3;
        saveValue(parseIntData(this.mYear + String.format(Locale.ENGLISH, "%02d", Integer.valueOf(this.mMonth + 1)) + String.format(Locale.ENGLISH, "%02d", Integer.valueOf(this.mDay))), 3);
        this.mHiUserInfo.setAge(getAge(i, i2, i3));
        updateBirthdayTextView();
        refreshButtonStatus();
    }

    private int getAge(int i, int i2, int i3) {
        if (isMoreTodayCalendar(i, i2, i3)) {
            return 0;
        }
        Calendar calendar = Calendar.getInstance();
        int i4 = calendar.get(1) - i;
        int i5 = calendar.get(2) - i2;
        return (i5 < 0 || (i5 == 0 && calendar.get(5) - i3 < 0)) ? i4 - 1 : i4;
    }

    private void updateBirthdayTextView() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(this.mYear, this.mMonth, this.mDay);
        this.mUserBirthDayText.setText(UnitUtil.a(calendar.getTime(), 20));
        refreshButtonStatus();
    }

    private boolean isMoreTodayCalendar(int i, int i2, int i3) {
        Calendar calendar = Calendar.getInstance();
        int i4 = calendar.get(1);
        int i5 = calendar.get(2);
        int i6 = calendar.get(5);
        if (i > i4) {
            return true;
        }
        if (i != i4 || i2 <= i5) {
            return i == i4 && i2 == i5 && i3 > i6;
        }
        return true;
    }

    private boolean isOverLimit(int i, int i2, int i3) {
        Calendar calendar = Calendar.getInstance();
        int i4 = calendar.get(1);
        int i5 = calendar.get(2);
        int i6 = calendar.get(5);
        if (i < i4) {
            return true;
        }
        if (i != i4 || i2 >= i5) {
            return i == i4 && i2 == i5 && i3 < i6;
        }
        return true;
    }

    private void showGenderPickerDialog() {
        LogUtil.a(TAG, "showGenderPickerDialog()");
        View inflate = getActivity().getSystemService("layout_inflater") instanceof LayoutInflater ? ((LayoutInflater) getActivity().getSystemService("layout_inflater")).inflate(R.layout.hw_show_guest_settings_gender_view, (ViewGroup) null) : null;
        if (inflate != null) {
            CustomViewDialog.Builder builder = new CustomViewDialog.Builder(getActivity());
            builder.a(getString(R.string._2130837629_res_0x7f02007d)).czg_(inflate).czd_(getString(R.string._2130841939_res_0x7f021153), new View.OnClickListener() { // from class: com.huawei.health.device.ui.measure.fragment.GuestUserInfoGuideFragment$$ExternalSyntheticLambda3
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    GuestUserInfoGuideFragment.lambda$showGenderPickerDialog$5(view);
                }
            });
            this.mDialogGender = builder.e();
            if (!initializeGenderDialogLayout(inflate)) {
                this.mDialogGender = null;
                LogUtil.h(TAG, "initializeGenderDialogLayout is fail");
            } else {
                this.mDialogGender.show();
            }
        }
    }

    static /* synthetic */ void lambda$showGenderPickerDialog$5(View view) {
        LogUtil.a(TAG, "showGenderPickerDialog click negative button.");
        ViewClickInstrumentation.clickOnView(view);
    }

    public void dealGenderClicked(int i) {
        refreshGenderImageView(i);
        Dialog dialog = this.mDialogGender;
        if (dialog != null) {
            dialog.dismiss();
        }
        this.mIsGenderSelected = true;
        saveValue(i, 2);
        this.mGender = i;
        refreshGenderText(i);
    }

    private boolean initializeGenderDialogLayout(View view) {
        LogUtil.a(TAG, "initializeGenderDialogLayout()");
        if (view == null) {
            LogUtil.h(TAG, "initializeGenderDialogLayout contentView is null");
            return false;
        }
        this.mGenderMaleImgView = (HealthRadioButton) view.findViewById(R.id.settings_gender_imgview1);
        this.mGenderFemaleImgView = (HealthRadioButton) view.findViewById(R.id.settings_gender_imgview2);
        this.mSettingsGenderMale = (RelativeLayout) view.findViewById(R.id.settings_gender_view_layout1);
        this.mSettingsGenderFemale = (RelativeLayout) view.findViewById(R.id.settings_gender_view_layout2);
        this.mSettingsGenderMale.setOnClickListener(new cmq(this, 1));
        this.mSettingsGenderFemale.setOnClickListener(new cmq(this, 0));
        refreshGenderImageView(1);
        refreshGenderImageViewLayout();
        return true;
    }

    private void refreshButtonStatus() {
        String trim = this.mUserGenderText.getText().toString().trim();
        String trim2 = this.mUserBirthDayText.getText().toString().trim();
        String trim3 = this.mUserHeightText.getText().toString().trim();
        if (!TextUtils.isEmpty(trim) && !TextUtils.isEmpty(trim2) && !TextUtils.isEmpty(trim3)) {
            this.mGuestMeasureCompleteText.setEnabled(true);
        } else {
            this.mGuestMeasureCompleteText.setEnabled(false);
        }
    }

    private void refreshGenderImageView(int i) {
        if (i == 0) {
            this.mGenderMaleImgView.setChecked(false);
            this.mGenderFemaleImgView.setChecked(true);
        } else if (i == 1) {
            this.mGenderMaleImgView.setChecked(true);
            this.mGenderFemaleImgView.setChecked(false);
        } else if (i == 2) {
            this.mGenderMaleImgView.setChecked(false);
            this.mGenderFemaleImgView.setChecked(false);
        } else {
            this.mGenderMaleImgView.setChecked(true);
            this.mGenderFemaleImgView.setChecked(false);
        }
    }

    private void refreshGenderText(int i) {
        if (i == 0) {
            this.mUserGenderText.setText(this.mGenderFemale);
            if (!this.mIsHeightSelected) {
                this.mHiUserInfo.setHeight(170);
            }
        } else if (i == 1) {
            this.mUserGenderText.setText(this.mGenderMale);
            if (!this.mIsHeightSelected) {
                this.mHiUserInfo.setHeight(170);
            }
        } else {
            this.mUserGenderText.setText(this.mGenderMale);
        }
        refreshButtonStatus();
    }

    private void refreshGenderImageViewLayout() {
        refreshGenderImageView(this.mGender);
    }

    private int parseIntData(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return -1;
        }
    }

    @Override // com.huawei.health.ecologydevice.ui.BaseFragment
    public boolean onBackPressed() {
        popupFragment(HagridDeviceManagerFragment.class);
        return false;
    }
}
