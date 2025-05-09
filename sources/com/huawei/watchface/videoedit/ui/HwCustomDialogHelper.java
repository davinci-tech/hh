package com.huawei.watchface.videoedit.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.watchface.R$color;
import com.huawei.watchface.R$dimen;
import com.huawei.watchface.R$id;
import com.huawei.watchface.R$layout;
import com.huawei.watchface.videoedit.utils.ScreenUtils;
import java.util.ArrayList;
import java.util.Arrays;

/* loaded from: classes9.dex */
public class HwCustomDialogHelper {
    private static final ArrayList<Integer> DIALOG_TYPE_SUPPORT_LIST = new ArrayList<>(Arrays.asList(Integer.valueOf(R$layout.watchface_video_dialog_horizontal_progress)));
    private static final int PADDING_BOTTOM = ScreenUtils.dp2px(20);
    private ImageView mCancelImageView;
    private DialogInterface.OnCancelListener mCancelListener;
    private TextView mContentTextView;
    private Context mContext;
    private int mLayoutId;
    private ProgressBar mProgressBar;
    private TextView mProgressTextView;
    private View mCustomLayout = null;
    private AlertDialog mAlertDialog = creatCustomDialog();

    public HwCustomDialogHelper(Context context, int i) {
        this.mLayoutId = i;
        this.mContext = context;
    }

    private AlertDialog creatCustomDialog() {
        if (DIALOG_TYPE_SUPPORT_LIST.contains(Integer.valueOf(this.mLayoutId))) {
            this.mCustomLayout = LayoutInflater.from(this.mContext).inflate(this.mLayoutId, (ViewGroup) null);
            AlertDialog create = new AlertDialog.Builder(this.mContext).setView(this.mCustomLayout).create();
            initView();
            return create;
        }
        return new AlertDialog.Builder(this.mContext).create();
    }

    private void initView() {
        if (this.mCustomLayout != null && this.mLayoutId == R$layout.watchface_video_dialog_horizontal_progress) {
            initProgressDialogView();
        }
    }

    private void initProgressDialogView() {
        int dimension = (int) this.mContext.getResources().getDimension(R$dimen.emui_text_size_body2);
        int color = this.mContext.getResources().getColor(R$color.watch_face_emui_button_text_color);
        View view = this.mCustomLayout;
        int i = PADDING_BOTTOM;
        view.setPadding(i, i, i, i);
        TextView textView = (TextView) this.mCustomLayout.findViewById(R$id.content);
        this.mContentTextView = textView;
        textView.setTextColor(color);
        float f = dimension;
        this.mContentTextView.setTextSize(0, f);
        this.mProgressBar = (ProgressBar) this.mCustomLayout.findViewById(R$id.progress);
        this.mProgressBar.setProgressBackgroundTintList(new ColorStateList(new int[][]{new int[0]}, new int[]{this.mContext.getResources().getColor(R$color.emui_color_gray_4)}));
        TextView textView2 = (TextView) this.mCustomLayout.findViewById(R$id.progress_percent);
        this.mProgressTextView = textView2;
        textView2.setTextSize(0, f);
        this.mProgressTextView.setTextColor(color);
        this.mCancelImageView = (ImageView) this.mCustomLayout.findViewById(R$id.cancel);
        this.mCancelImageView.setColorFilter(this.mContext.getResources().getColor(R$color.emui_color_gray_8));
        this.mCancelImageView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.watchface.videoedit.ui.HwCustomDialogHelper$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                HwCustomDialogHelper.this.m967x374a84bd(view2);
            }
        });
    }

    /* renamed from: lambda$initProgressDialogView$0$com-huawei-watchface-videoedit-ui-HwCustomDialogHelper, reason: not valid java name */
    /* synthetic */ void m967x374a84bd(View view) {
        this.mAlertDialog.dismiss();
        if (this.mCancelImageView != null) {
            this.mCancelListener.onCancel(this.mAlertDialog);
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public AlertDialog getAlertDialog() {
        return this.mAlertDialog;
    }

    public void setProgress(int i) {
        if (this.mLayoutId != R$layout.watchface_video_dialog_horizontal_progress) {
            return;
        }
        this.mProgressBar.setProgress(i);
        this.mProgressTextView.setText(i + "%");
    }

    public void setContent(String str) {
        this.mContentTextView.setText(str);
    }

    public void setCancelButtonContentDescription(String str) {
        ImageView imageView = this.mCancelImageView;
        if (imageView != null) {
            imageView.setContentDescription(str);
        }
    }

    public void setCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        this.mCancelListener = onCancelListener;
    }
}
