package com.huawei.ui.openservice.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.Pair;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.ImageView;
import com.huawei.haf.router.AppRouterExtras;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.openalliance.ad.constant.Constants;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.button.HealthButton;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.titlebar.CustomTitleBar;
import com.huawei.ui.openservice.db.model.OpenService;

/* loaded from: classes7.dex */
public class ServiceAuthDialog extends Dialog {
    private static final String TAG = "ServiceAuthDialog";

    private ServiceAuthDialog(Context context, int i) {
        super(context, i);
        if (context instanceof Activity) {
            setOwnerActivity((Activity) context);
        }
    }

    public static class Builder {
        private View.OnClickListener buttonClickListener;
        private HealthButton buttonLogin;
        private Context context;
        ServiceAuthDialog dialog;
        private boolean isChecked = true;
        private OpenService service;
        private HealthTextView serviceAuthNote;
        private ImageView serviceIcon;
        private String serviceIconUrl;
        private HealthTextView serviceName;
        private CustomTitleBar titleBar;
        private HealthCheckBox userInfo;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setPositiveButton(View.OnClickListener onClickListener) {
            this.buttonClickListener = onClickListener;
            return this;
        }

        public Builder setService(OpenService openService) {
            this.service = openService;
            return this;
        }

        public ServiceAuthDialog create() {
            this.dialog = new ServiceAuthDialog(this.context, R.style.full_screen_dialog);
            if (!(this.context.getSystemService("layout_inflater") instanceof LayoutInflater)) {
                LogUtil.b(ServiceAuthDialog.TAG, "inflater=null");
                return this.dialog;
            }
            View inflate = ((LayoutInflater) this.context.getSystemService("layout_inflater")).inflate(R.layout.dialog_service_auth, (ViewGroup) null);
            initView(inflate);
            initData();
            Window window = this.dialog.getWindow();
            window.setGravity(8388659);
            window.addFlags(AppRouterExtras.COLDSTART);
            window.setStatusBarColor(0);
            window.getDecorView().setSystemUiVisibility(9216);
            if (this.context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR) instanceof WindowManager) {
                Display defaultDisplay = ((WindowManager) this.context.getSystemService(Constants.NATIVE_WINDOW_SUB_DIR)).getDefaultDisplay();
                int dimensionPixelSize = this.context.getResources().getDimensionPixelSize(R.dimen._2131362466_res_0x7f0a02a2);
                int dimensionPixelSize2 = this.context.getResources().getDimensionPixelSize(R.dimen._2131362466_res_0x7f0a02a2);
                Pair<Integer, Integer> safeRegionWidth = BaseActivity.getSafeRegionWidth();
                int width = defaultDisplay.getWidth();
                int intValue = ((Integer) safeRegionWidth.first).intValue();
                int intValue2 = ((Integer) safeRegionWidth.second).intValue();
                int height = defaultDisplay.getHeight();
                WindowManager.LayoutParams attributes = window.getAttributes();
                attributes.width = (((width - dimensionPixelSize) - dimensionPixelSize2) - intValue) - intValue2;
                attributes.height = height;
                window.setAttributes(attributes);
            }
            this.dialog.setContentView(inflate);
            return this.dialog;
        }

        private void initView(View view) {
            CustomTitleBar customTitleBar = (CustomTitleBar) view.findViewById(R.id.service_auth_title_bar);
            this.titleBar = customTitleBar;
            customTitleBar.setLeftButtonOnClickListener(new ClickListener());
            this.serviceIcon = (ImageView) view.findViewById(R.id.service_icon);
            this.serviceName = (HealthTextView) view.findViewById(R.id.service_name);
            this.serviceAuthNote = (HealthTextView) view.findViewById(R.id.service_auth_note);
            HealthCheckBox healthCheckBox = (HealthCheckBox) view.findViewById(R.id.user_info);
            this.userInfo = healthCheckBox;
            healthCheckBox.setOnCheckedChangeListener(new CheckedChangeListener());
            HealthButton healthButton = (HealthButton) view.findViewById(R.id.btn_agree_enter);
            this.buttonLogin = healthButton;
            healthButton.setOnClickListener(new ClickListener());
        }

        private void initData() {
            OpenService openService = this.service;
            if (openService == null) {
                return;
            }
            String serviceIcon = openService.getServiceIcon();
            this.serviceIconUrl = serviceIcon;
            LogUtil.c(ServiceAuthDialog.TAG, "serviceIconUrl = ", serviceIcon);
            this.serviceIcon.setImageBitmap(BitmapFactory.decodeFile(this.serviceIconUrl));
            this.serviceName.setText(this.service.getProductName());
            this.serviceAuthNote.setText(this.context.getResources().getString(R.string._2130842131_res_0x7f021213, this.service.getServiceProvider()));
            this.userInfo.setChecked(true);
        }

        class CheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
            CheckedChangeListener() {
            }

            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                if (compoundButton == Builder.this.userInfo) {
                    if (z) {
                        Builder.this.buttonLogin.setBackgroundResource(R.drawable._2131427701_res_0x7f0b0175);
                        Builder.this.isChecked = true;
                    } else {
                        Builder.this.buttonLogin.setBackgroundResource(R.drawable._2131427707_res_0x7f0b017b);
                        Builder.this.isChecked = false;
                    }
                }
                ViewClickInstrumentation.clickOnView(compoundButton);
            }
        }

        class ClickListener implements View.OnClickListener {
            ClickListener() {
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (view == Builder.this.buttonLogin) {
                    if (Builder.this.isChecked) {
                        if (Builder.this.dialog != null) {
                            Builder.this.dialog.dismiss();
                        }
                        if (Builder.this.buttonClickListener != null) {
                            Builder.this.buttonClickListener.onClick(view);
                        }
                    } else {
                        LogUtil.a(ServiceAuthDialog.TAG, "checkBox is not checked, do nothing!");
                    }
                } else {
                    LogUtil.a(ServiceAuthDialog.TAG, "click titleBar");
                    Builder.this.dialog.dismiss();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        }
    }
}
