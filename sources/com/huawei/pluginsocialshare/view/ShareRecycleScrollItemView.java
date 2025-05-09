package com.huawei.pluginsocialshare.view;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.request.RequestOptions;
import com.huawei.health.R;
import com.huawei.health.socialshare.model.socialsharebean.ShareDataInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.utils.PermissionUtil;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginsocialshare.view.ShareRecycleScrollItemView;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import com.huawei.ui.commonui.utils.CustomPermissionAction;
import defpackage.mud;
import defpackage.mut;
import defpackage.mvp;
import defpackage.nrf;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.hwlogsmodel.ReleaseLogUtil;
import java.io.File;
import java.lang.ref.WeakReference;

/* loaded from: classes6.dex */
public class ShareRecycleScrollItemView extends RelativeLayout {

    /* renamed from: a, reason: collision with root package name */
    private ImageView f8531a;
    private ImageView b;
    private boolean c;
    private ImageView d;
    private RelativeLayout e;
    private ShareDataInfo f;
    private TextView i;
    private CustomViewDialog j;

    public ShareRecycleScrollItemView(Context context) {
        super(context);
        c(context);
    }

    public ShareRecycleScrollItemView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        c(context);
    }

    public ShareRecycleScrollItemView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        c(context);
    }

    private void c(Context context) {
        View inflate = View.inflate(context, R.layout.hw_health_edit_share_gridview_item, null);
        this.b = (ImageView) inflate.findViewById(R.id.hw_health_edit_share_download);
        this.e = (RelativeLayout) inflate.findViewById(R.id.hw_health_edit_share_downloading);
        this.i = (TextView) inflate.findViewById(R.id.hw_health_edit_share_new);
        this.f8531a = (ImageView) inflate.findViewById(R.id.hw_health_edit_share_gridview_img);
        ImageView imageView = (ImageView) inflate.findViewById(R.id.hw_health_edit_share_select);
        this.d = imageView;
        nrf.cIn_(R.mipmap.hw_health_edit_share_select_img, imageView);
        addView(inflate);
    }

    public void setShowSelected(boolean z) {
        if (z) {
            this.d.setVisibility(0);
            this.c = true;
        } else {
            this.d.setVisibility(8);
            this.c = false;
        }
    }

    @Override // android.view.View
    public boolean isSelected() {
        return this.c;
    }

    public void setShowNew(boolean z) {
        if (z) {
            this.i.setVisibility(0);
        } else {
            this.i.setVisibility(8);
        }
    }

    public void setShowDown(boolean z) {
        if (z) {
            this.b.setVisibility(0);
        } else {
            this.b.setVisibility(8);
        }
    }

    public void setDownloading(Activity activity, boolean z) {
        if (z && activity != null) {
            activity.runOnUiThread(new Runnable() { // from class: mwn
                @Override // java.lang.Runnable
                public final void run() {
                    ShareRecycleScrollItemView.this.d();
                }
            });
        } else {
            this.e.setVisibility(8);
        }
    }

    public /* synthetic */ void d() {
        this.e.setVisibility(0);
        this.i.setVisibility(8);
        this.b.setVisibility(8);
    }

    public void setContentViewImage(ShareDataInfo shareDataInfo) {
        this.f = shareDataInfo;
        this.i.setVisibility(8);
        if (this.c) {
            this.d.setVisibility(0);
        } else {
            this.d.setVisibility(8);
        }
        if (shareDataInfo instanceof mvp) {
            mvp mvpVar = (mvp) shareDataInfo;
            this.b.setVisibility(8);
            this.i.setVisibility(8);
            if (mvpVar.getId() != -1) {
                this.f8531a.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            }
            nrf.cIn_(mvpVar.e(), this.f8531a);
            return;
        }
        crm_(this.f8531a, shareDataInfo);
    }

    public ShareDataInfo getShareDataInfo() {
        return this.f;
    }

    private void crm_(ImageView imageView, ShareDataInfo shareDataInfo) {
        if (shareDataInfo == null || TextUtils.isEmpty(shareDataInfo.getPath())) {
            LogUtil.a("Share_ShareRecycleScrollItemView", "loadItemImageViewByPath params null");
            return;
        }
        File file = new File(CommonUtil.c(shareDataInfo.getPath()));
        if (shareDataInfo instanceof mut) {
            if (!file.exists()) {
                this.b.setVisibility(0);
                this.i.setVisibility(0);
            } else {
                this.b.setVisibility(8);
                this.i.setVisibility(8);
            }
            nrf.cIv_(shareDataInfo.getUrl(), new RequestOptions().centerCrop(), imageView);
            return;
        }
        if (file.exists()) {
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            RequestOptions centerInside = new RequestOptions().centerInside();
            LogUtil.a("Share_ShareRecycleScrollItemView", "file exit");
            this.b.setVisibility(8);
            this.i.setVisibility(8);
            nrf.cIt_(file, centerInside, imageView);
        }
    }

    public void crn_(final Activity activity) {
        Object systemService = activity.getSystemService("layout_inflater");
        if (!(systemService instanceof LayoutInflater)) {
            LogUtil.b("Share_ShareRecycleScrollItemView", "object is not instanceof Inflater");
            return;
        }
        View inflate = ((LayoutInflater) systemService).inflate(R.layout.hw_health_edit_share_camera_dialog, (ViewGroup) null);
        if (this.j == null) {
            this.j = new CustomViewDialog.Builder(activity).czg_(inflate).e();
        }
        this.j.show();
        ((HealthTextView) inflate.findViewById(R.id.hw_health_edit_share_caerma)).setOnClickListener(new View.OnClickListener() { // from class: mwk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ShareRecycleScrollItemView.this.cro_(activity, view);
            }
        });
        ((HealthTextView) inflate.findViewById(R.id.hw_health_edit_share_gallery)).setOnClickListener(new View.OnClickListener() { // from class: mwm
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ShareRecycleScrollItemView.this.crp_(activity, view);
            }
        });
    }

    public /* synthetic */ void cro_(Activity activity, View view) {
        PermissionUtil.b(activity, PermissionUtil.PermissionType.CAMERA, new c(activity));
        CustomViewDialog customViewDialog = this.j;
        if (customViewDialog != null) {
            customViewDialog.dismiss();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void crp_(Activity activity, View view) {
        nsn.cKT_(activity, 2);
        CustomViewDialog customViewDialog = this.j;
        if (customViewDialog != null) {
            customViewDialog.dismiss();
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    static class c extends CustomPermissionAction {
        private final WeakReference<Activity> d;

        private c(Activity activity) {
            super(activity);
            this.d = new WeakReference<>(activity);
        }

        @Override // com.huawei.hwcommonmodel.utils.permission.PermissionsResultAction
        public void onGranted() {
            Activity activity = this.d.get();
            if (activity == null) {
                ReleaseLogUtil.d("Share_ShareRecycleScrollItemView", "activity is null in WeakReferenceCustomPermissionAction");
            } else {
                mud.cpE_(activity);
            }
        }
    }
}
