package com.huawei.healthcloud.plugintrack.ui.activity;

import android.app.Activity;
import android.content.ComponentCallbacks2;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.bumptech.glide.Glide;
import com.huawei.haf.handler.BaseHandler;
import com.huawei.haf.language.LanguageInstallHelper;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.ImageLoad;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload.OnPhotoSelectChangedListener;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload.PictureImageGridAdapter;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload.RecyclerPreloadView;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.ui.GridSpacingItemDecoration;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.PhotoModel;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseActivity;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.hcl;
import defpackage.nrz;
import defpackage.nsn;
import health.compact.a.LanguageUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes4.dex */
public class PhotoSelectActivity extends BaseActivity {

    /* renamed from: a, reason: collision with root package name */
    private static int f3655a = -1;
    private RelativeLayout f;
    private HealthTextView g;
    private RelativeLayout j;
    private int k;
    private LinearLayout o;
    private RelativeLayout p;
    private RelativeLayout q;
    protected PictureImageGridAdapter d = null;
    private Context e = null;
    private RecyclerPreloadView m = null;
    private boolean c = false;
    private List<PhotoModel> i = new ArrayList();
    private int s = 0;
    private ArrayList<PhotoModel> t = new ArrayList<>();
    private final LoaderManager.LoaderCallbacks<List<PhotoModel>> h = new LoaderManager.LoaderCallbacks<List<PhotoModel>>() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.PhotoSelectActivity.5
        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoaderReset(Loader<List<PhotoModel>> loader) {
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public Loader<List<PhotoModel>> onCreateLoader(int i, Bundle bundle) {
            LogUtil.a("Track_PhotoSelectActivity", "onCreateLoader");
            return new ImageLoad(PhotoSelectActivity.this.e);
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onLoadFinished(Loader<List<PhotoModel>> loader, List<PhotoModel> list) {
            PhotoSelectActivity.this.i = list;
            PhotoSelectActivity.this.b();
            PhotoSelectActivity.this.d.e(PhotoSelectActivity.this.i);
            if (PhotoSelectActivity.this.i == null || PhotoSelectActivity.this.i.size() == 0) {
                PhotoSelectActivity.this.o.setVisibility(0);
                PhotoSelectActivity.this.m.setVisibility(8);
            } else {
                PhotoSelectActivity.this.o.setVisibility(8);
                PhotoSelectActivity.this.m.setVisibility(0);
                LogUtil.a("Track_PhotoSelectActivity", "mLocalMediaList size is ", Integer.valueOf(PhotoSelectActivity.this.i.size()));
            }
            PhotoSelectActivity.this.c = false;
            PhotoSelectActivity.this.f.setVisibility(8);
        }
    };
    private final ComponentCallbacks2 b = new ComponentCallbacks2() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.PhotoSelectActivity.2
        @Override // android.content.ComponentCallbacks
        public void onConfigurationChanged(Configuration configuration) {
        }

        @Override // android.content.ComponentCallbacks2
        public void onTrimMemory(int i) {
            PhotoSelectActivity.super.onTrimMemory(i);
            if (i == 10 || i == 15 || i == 5) {
                int unused = PhotoSelectActivity.f3655a = i;
                Glide.get(PhotoSelectActivity.this.e).onTrimMemory(i);
            }
            if (i == 20) {
                Glide.get(PhotoSelectActivity.this.e).clearMemory();
            }
        }

        @Override // android.content.ComponentCallbacks
        public void onLowMemory() {
            int unused = PhotoSelectActivity.f3655a = 10;
            PhotoSelectActivity.super.onLowMemory();
            Glide.get(PhotoSelectActivity.this.e).clearMemory();
        }
    };
    private Handler l = new a(Looper.getMainLooper(), this);
    private OnPhotoSelectChangedListener<PhotoModel> n = new OnPhotoSelectChangedListener<PhotoModel>() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.PhotoSelectActivity.4
        @Override // com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload.OnPhotoSelectChangedListener
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onPictureClick(int i, PhotoModel photoModel) {
            if (i < 0 || photoModel == null) {
                return;
            }
            PhotoPreviewActivity.bcg_(PhotoSelectActivity.this.e, new Pair(Boolean.valueOf(!photoModel.isSelected() && isMaxCount()), photoModel), PhotoSelectActivity.this.t.size(), i, null);
        }

        @Override // com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload.OnPhotoSelectChangedListener
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onPictureAdd(PhotoModel photoModel) {
            if (!PhotoSelectActivity.this.t.contains(photoModel)) {
                PhotoSelectActivity.this.t.add(photoModel);
                PhotoSelectActivity.j(PhotoSelectActivity.this);
            }
            PhotoSelectActivity.this.l.sendEmptyMessage(1);
        }

        @Override // com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload.OnPhotoSelectChangedListener
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onPictureRemove(PhotoModel photoModel) {
            if (PhotoSelectActivity.this.t.contains(photoModel)) {
                PhotoSelectActivity.this.t.remove(photoModel);
                PhotoSelectActivity.h(PhotoSelectActivity.this);
            }
            PhotoSelectActivity.this.l.sendEmptyMessage(1);
        }

        @Override // com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload.OnPhotoSelectChangedListener
        public boolean isMaxCount() {
            return PhotoSelectActivity.this.t.size() >= 3 || PhotoSelectActivity.this.s >= PhotoSelectActivity.this.k;
        }
    };

    static /* synthetic */ int h(PhotoSelectActivity photoSelectActivity) {
        int i = photoSelectActivity.s;
        photoSelectActivity.s = i - 1;
        return i;
    }

    static /* synthetic */ int j(PhotoSelectActivity photoSelectActivity) {
        int i = photoSelectActivity.s;
        photoSelectActivity.s = i + 1;
        return i;
    }

    static class a extends BaseHandler<PhotoSelectActivity> {
        public a(Looper looper, PhotoSelectActivity photoSelectActivity) {
            super(looper, photoSelectActivity);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.haf.handler.BaseHandler
        /* renamed from: bck_, reason: merged with bridge method [inline-methods] */
        public void handleMessageWhenReferenceNotNull(PhotoSelectActivity photoSelectActivity, Message message) {
            if (message.what != 1) {
                return;
            }
            photoSelectActivity.g.setText(photoSelectActivity.getResources().getString(R.string._2130839943_res_0x7f020987, Integer.valueOf(photoSelectActivity.t.size()), 3));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        ArrayList<PhotoModel> arrayList = this.t;
        if (arrayList == null || this.i == null || arrayList.size() <= 0 || this.i.size() <= 0) {
            return;
        }
        Collections.sort(this.t, new Comparator<PhotoModel>() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.PhotoSelectActivity.1
            @Override // java.util.Comparator
            /* renamed from: b, reason: merged with bridge method [inline-methods] */
            public int compare(PhotoModel photoModel, PhotoModel photoModel2) {
                if (photoModel.getRowId() == photoModel2.getRowId()) {
                    return 0;
                }
                return photoModel.getRowId() > photoModel2.getRowId() ? -1 : 1;
            }
        });
        ArrayList<PhotoModel> arrayList2 = new ArrayList<>();
        int i = 0;
        int i2 = 0;
        while (true) {
            if (i >= this.i.size()) {
                break;
            }
            if (this.i.get(i).getRowId() == this.t.get(i2).getRowId()) {
                this.i.get(i).setSelected(true);
                arrayList2.add(this.i.get(i));
                i2++;
                if (i2 >= this.t.size()) {
                    this.t = arrayList2;
                    break;
                }
            }
            i++;
        }
        this.t = arrayList2;
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        LogUtil.a("Track_PhotoSelectActivity", "onCreate");
        super.onCreate(bundle);
        setContentView(R.layout.trackalbum_photo_select_activity);
        this.e = this;
        d();
        e();
        this.m = (RecyclerPreloadView) findViewById(R.id.picture_recycler);
        this.o = (LinearLayout) findViewById(R.id.no_photo);
        this.m.setListenter(new RecyclerPreloadView.ScrollFastListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.PhotoSelectActivity.3
            @Override // com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload.RecyclerPreloadView.ScrollFastListener
            public void scrollFast() {
                if (hcl.d(PhotoSelectActivity.this.e)) {
                    Glide.with((Activity) PhotoSelectActivity.this.e).pauseRequests();
                }
            }

            @Override // com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload.RecyclerPreloadView.ScrollFastListener
            public void scrollSlow() {
                if (hcl.d(PhotoSelectActivity.this.e)) {
                    Glide.with((Activity) PhotoSelectActivity.this.e).resumeRequests();
                }
            }
        });
        this.m.setNestedScrollingEnabled(false);
        this.m.addItemDecoration(new GridSpacingItemDecoration(4, hcl.e(this, 2.0f), false));
        this.m.setLayoutManager(new GridLayoutManager(this, 4));
        this.m.setHasFixedSize(true);
        RecyclerView.ItemAnimator itemAnimator = this.m.getItemAnimator();
        if (itemAnimator != null && (itemAnimator instanceof SimpleItemAnimator)) {
            ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
            this.m.setItemAnimator(null);
        }
        PictureImageGridAdapter pictureImageGridAdapter = new PictureImageGridAdapter(this);
        this.d = pictureImageGridAdapter;
        pictureImageGridAdapter.d(this.n);
        a();
        this.m.setAdapter(this.d);
        registerComponentCallbacks(this.b);
    }

    private void d() {
        Intent intent = getIntent();
        if (intent != null) {
            try {
                this.k = intent.getIntExtra("key_data_max_photo_count", 9);
                this.s = intent.getIntExtra("total_selected_image_num_key", 0);
                this.t = intent.getParcelableArrayListExtra("selected_image_path_key");
            } catch (ArrayIndexOutOfBoundsException e) {
                LogUtil.b("Track_PhotoSelectActivity", "get list error:", e.getMessage());
            }
        }
    }

    private void e() {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.image_select_title_bar);
        this.q = relativeLayout;
        RelativeLayout relativeLayout2 = (RelativeLayout) relativeLayout.findViewById(R.id.base_health_right_l);
        this.p = relativeLayout2;
        relativeLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.PhotoSelectActivity.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putParcelableArrayListExtra("selected_image_list", PhotoSelectActivity.this.t);
                PhotoSelectActivity.this.setResult(-1, intent);
                PhotoSelectActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        ImageView imageView = (ImageView) this.q.findViewById(R.id.btn_left);
        Drawable drawable = this.e.getResources().getDrawable(R.drawable._2131431952_res_0x7f0b1210);
        if (LanguageUtil.bc(this.e)) {
            drawable = nrz.cKm_(this.e, drawable);
        }
        imageView.setImageDrawable(drawable);
        RelativeLayout relativeLayout3 = (RelativeLayout) this.q.findViewById(R.id.btn_left_l);
        this.j = relativeLayout3;
        relativeLayout3.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.activity.PhotoSelectActivity.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PhotoSelectActivity.this.finish();
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        c();
        HealthTextView healthTextView = (HealthTextView) this.q.findViewById(R.id.image_limit_text);
        this.g = healthTextView;
        if (this.t != null) {
            healthTextView.setText(getResources().getString(R.string._2130839943_res_0x7f020987, Integer.valueOf(this.t.size()), 3));
        } else {
            healthTextView.setText(getResources().getString(R.string._2130839943_res_0x7f020987, 0, 3));
        }
        this.f = (RelativeLayout) findViewById(R.id.image_select_londing_rl);
    }

    private void c() {
        View findViewById = this.q.findViewById(R.id.statusbar_panel);
        if (findViewById == null || !(findViewById.getLayoutParams() instanceof RelativeLayout.LayoutParams)) {
            return;
        }
        ((RelativeLayout.LayoutParams) findViewById.getLayoutParams()).height = nsn.r(this);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (this.c || !this.d.c()) {
            return;
        }
        a();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        unregisterComponentCallbacks(this.b);
        super.onDestroy();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a("Track_PhotoSelectActivity", "onActivityResult.");
        if (i2 == -1 && i == 4097 && intent != null) {
            boolean booleanExtra = intent.getBooleanExtra("result", false);
            int intExtra = intent.getIntExtra("index", 0);
            List<PhotoModel> list = this.i;
            if (list == null || intExtra >= list.size()) {
                return;
            }
            this.i.get(intExtra).setSelected(booleanExtra);
            if (booleanExtra) {
                this.n.onPictureAdd(this.i.get(intExtra));
            } else {
                this.n.onPictureRemove(this.i.get(intExtra));
            }
            this.d.notifyItemChanged(intExtra);
        }
    }

    private void a() {
        this.c = true;
        this.f.setVisibility(0);
        LoaderManager.getInstance(this).initLoader(R.id.loader_id_media_store_data, null, this.h);
    }

    @Override // com.huawei.ui.commonui.base.BaseActivity, androidx.activity.ComponentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        LanguageInstallHelper.updateResources(this);
        super.onConfigurationChanged(configuration);
    }
}
