package com.huawei.hms.update.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.ResourceLoaderUtil;
import com.huawei.openalliance.ad.constant.ParamConstants;
import java.text.NumberFormat;

/* loaded from: classes9.dex */
public class DownloadProgress extends AbstractDialog {
    private ProgressBar c;
    private TextView d;
    private ImageView e;
    private FrameLayout f;
    private OnCancelListener h;
    private boolean i;
    private int g = 0;
    private DialogInterface.OnKeyListener j = new b(null);

    public interface OnCancelListener {
        void onCancel();
    }

    class a implements View.OnClickListener {
        a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (DownloadProgress.this.h != null) {
                DownloadProgress.this.h.onCancel();
            }
        }
    }

    @Override // com.huawei.hms.update.ui.AbstractDialog
    protected int getDialogThemeId() {
        return a(getActivity()) != 0 ? 0 : 3;
    }

    public void intProgress(int i) {
        this.g = i;
    }

    public void setOnCancelListener(OnCancelListener onCancelListener) {
        this.h = onCancelListener;
    }

    public void setShowCancelBtn(boolean z) {
        this.i = z;
    }

    void a(int i) {
        ProgressBar progressBar;
        Activity activity = getActivity();
        if (activity == null || activity.isFinishing()) {
            HMSLog.w("DownloadProgress", "In setDownloading, The activity is null or finishing.");
        } else {
            if (this.d == null || (progressBar = this.c) == null) {
                return;
            }
            progressBar.setProgress(i);
            this.d.setText(NumberFormat.getPercentInstance().format(i / 100.0f));
        }
    }

    @Override // com.huawei.hms.update.ui.AbstractDialog
    public AlertDialog onCreateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), getDialogThemeId());
        View inflate = View.inflate(getActivity(), ResourceLoaderUtil.getLayoutId("hms_download_progress"), null);
        inflate.setBackgroundColor(getActivity().getResources().getColor(ResourceLoaderUtil.getColorId("hw_cloud_dialog_bg")));
        int dimensionPixelOffset = getActivity().getResources().getDimensionPixelOffset(ResourceLoaderUtil.getDimenId("progress_layout_padding12"));
        int dimensionPixelOffset2 = getActivity().getResources().getDimensionPixelOffset(ResourceLoaderUtil.getDimenId("progress_layout_padding24"));
        inflate.setPaddingRelative(dimensionPixelOffset2, dimensionPixelOffset2, this.i ? dimensionPixelOffset : dimensionPixelOffset2, dimensionPixelOffset);
        builder.setView(inflate);
        builder.setCancelable(false);
        builder.setOnKeyListener(this.j);
        View findViewById = inflate.findViewById(ResourceLoaderUtil.getIdId("download_info_progress"));
        if (findViewById instanceof ProgressBar) {
            this.c = (ProgressBar) findViewById;
        }
        View findViewById2 = inflate.findViewById(ResourceLoaderUtil.getIdId("hms_message_text"));
        if (findViewById2 instanceof TextView) {
            TextView textView = (TextView) findViewById2;
            textView.setTextColor(getActivity().getResources().getColor(ResourceLoaderUtil.getColorId("hw_cloud_dialog_title_text_color")));
            textView.setText(getActivity().getResources().getString(ResourceLoaderUtil.getStringId("hms_downloading_loading")));
        }
        View findViewById3 = inflate.findViewById(ResourceLoaderUtil.getIdId("hms_progress_text"));
        if (findViewById3 instanceof TextView) {
            TextView textView2 = (TextView) findViewById3;
            this.d = textView2;
            textView2.setTextColor(getActivity().getResources().getColor(ResourceLoaderUtil.getColorId("hw_cloud_dialog_subtitle_text_color")));
            this.d.setPaddingRelative(0, 0, this.i ? getActivity().getResources().getDimensionPixelOffset(ResourceLoaderUtil.getDimenId("progress_text_paddingEnd")) : 0, 0);
        }
        inflate.findViewById(ResourceLoaderUtil.getIdId(ParamConstants.BtnParams.DOWN_CANCEL_BTN)).setVisibility(this.i ? 0 : 8);
        View findViewById4 = inflate.findViewById(ResourceLoaderUtil.getIdId("hms_progress_pause"));
        if (findViewById4 instanceof ImageView) {
            ImageView imageView = (ImageView) findViewById4;
            this.e = imageView;
            imageView.setImageResource(ResourceLoaderUtil.getDrawableId("hw_public_cancel"));
        }
        View findViewById5 = inflate.findViewById(ResourceLoaderUtil.getIdId(ParamConstants.BtnParams.DOWN_CANCEL_BTN));
        if (findViewById5 instanceof FrameLayout) {
            FrameLayout frameLayout = (FrameLayout) findViewById5;
            this.f = frameLayout;
            frameLayout.setOnClickListener(new a());
        }
        a(this.g);
        return builder.create();
    }

    private static int a(Context context) {
        if (context == null) {
            return 0;
        }
        return context.getResources().getIdentifier("androidhwext:style/Theme.Emui", null, null);
    }

    static class b implements DialogInterface.OnKeyListener {
        /* synthetic */ b(a aVar) {
            this();
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            return i == 4 && keyEvent.getRepeatCount() == 0;
        }

        private b() {
        }
    }
}
