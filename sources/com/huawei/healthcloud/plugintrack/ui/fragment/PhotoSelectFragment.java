package com.huawei.healthcloud.plugintrack.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.bumptech.glide.Glide;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.ImageLoad;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.PhotoOrVideoSelectChangedListener;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload.OnPhotoSelectChangedListener;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload.PictureImageGridAdapter;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload.RecyclerPreloadView;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.ui.GridSpacingItemDecoration;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.PhotoModel;
import com.huawei.healthcloud.plugintrack.ui.activity.PhotoAndVideoSelectActivity;
import com.huawei.healthcloud.plugintrack.ui.activity.PhotoPreviewActivity;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseFragment;
import defpackage.hcl;
import defpackage.koq;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes4.dex */
public class PhotoSelectFragment extends BaseFragment {
    private Context b;
    private RelativeLayout e;
    private LinearLayout h;
    private View n;
    private PhotoOrVideoSelectChangedListener o;
    private int m = 0;
    private PictureImageGridAdapter c = null;
    private RecyclerPreloadView f = null;

    /* renamed from: a, reason: collision with root package name */
    private boolean f3722a = false;
    private ArrayList<PhotoModel> l = new ArrayList<>(16);
    private List<PhotoModel> i = new ArrayList(16);
    private int g = 9;
    private OnPhotoSelectChangedListener<PhotoModel> j = new OnPhotoSelectChangedListener<PhotoModel>() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.PhotoSelectFragment.3
        @Override // com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload.OnPhotoSelectChangedListener
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPictureClick(int i, PhotoModel photoModel) {
            if (i < 0 || photoModel == null) {
                LogUtil.h("Track_PhotoSelectFragment", "index < 0 or media is null");
            } else {
                PhotoPreviewActivity.bcg_(PhotoSelectFragment.this.getContext(), new Pair(Boolean.valueOf(!photoModel.isSelected() && isMaxCount()), photoModel), PhotoSelectFragment.this.l.size(), i, PhotoSelectFragment.this);
            }
        }

        @Override // com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload.OnPhotoSelectChangedListener
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPictureAdd(PhotoModel photoModel) {
            if (PhotoSelectFragment.this.l.contains(photoModel)) {
                return;
            }
            PhotoSelectFragment.this.l.add(photoModel);
            PhotoSelectFragment.a(PhotoSelectFragment.this);
            PhotoSelectFragment.this.o.setSelectData(PhotoSelectFragment.this.l, PhotoSelectFragment.this.m);
        }

        @Override // com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload.OnPhotoSelectChangedListener
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public void onPictureRemove(PhotoModel photoModel) {
            if (PhotoSelectFragment.this.l.contains(photoModel)) {
                PhotoSelectFragment.this.l.remove(photoModel);
                PhotoSelectFragment.c(PhotoSelectFragment.this);
                PhotoSelectFragment.this.o.setSelectData(PhotoSelectFragment.this.l, PhotoSelectFragment.this.m);
            }
        }

        @Override // com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload.OnPhotoSelectChangedListener
        public boolean isMaxCount() {
            return PhotoSelectFragment.this.l.size() >= 3 || PhotoSelectFragment.this.m >= PhotoSelectFragment.this.g;
        }
    };
    private final LoaderManager.LoaderCallbacks<List<PhotoModel>> d = new LoaderManager.LoaderCallbacks<List<PhotoModel>>() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.PhotoSelectFragment.2
        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoaderReset(Loader<List<PhotoModel>> loader) {
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public Loader<List<PhotoModel>> onCreateLoader(int i, Bundle bundle) {
            return new ImageLoad(PhotoSelectFragment.this.b);
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        /* renamed from: e, reason: merged with bridge method [inline-methods] */
        public void onLoadFinished(Loader<List<PhotoModel>> loader, List<PhotoModel> list) {
            PhotoSelectFragment.this.i = list;
            PhotoSelectFragment.this.a();
            PhotoSelectFragment.this.c.e(PhotoSelectFragment.this.i);
            if (PhotoSelectFragment.this.i == null || PhotoSelectFragment.this.i.size() == 0) {
                PhotoSelectFragment.this.h.setVisibility(0);
                PhotoSelectFragment.this.f.setVisibility(8);
            } else {
                PhotoSelectFragment.this.h.setVisibility(8);
                PhotoSelectFragment.this.f.setVisibility(0);
            }
            PhotoSelectFragment.this.f3722a = false;
            PhotoSelectFragment.this.e.setVisibility(8);
        }
    };

    static /* synthetic */ int a(PhotoSelectFragment photoSelectFragment) {
        int i = photoSelectFragment.m;
        photoSelectFragment.m = i + 1;
        return i;
    }

    static /* synthetic */ int c(PhotoSelectFragment photoSelectFragment) {
        int i = photoSelectFragment.m;
        photoSelectFragment.m = i - 1;
        return i;
    }

    public void a(ArrayList<PhotoModel> arrayList, int i, int i2) {
        this.l = arrayList;
        this.m = i;
        this.g = i2;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.n == null) {
            this.n = layoutInflater.inflate(R.layout.trackalbum_photo_select_fragment, viewGroup, false);
        }
        FragmentActivity activity = getActivity();
        if (!(activity instanceof PhotoAndVideoSelectActivity)) {
            LogUtil.h("Track_PhotoSelectFragment", "Object is not instanceof PhotoAndVideoSelectActivity");
            return this.n;
        }
        this.b = (PhotoAndVideoSelectActivity) activity;
        this.f = (RecyclerPreloadView) this.n.findViewById(R.id.picture_recycler);
        this.h = (LinearLayout) this.n.findViewById(R.id.no_photo);
        this.e = (RelativeLayout) this.n.findViewById(R.id.image_select_londing_rl);
        c();
        b();
        return this.n;
    }

    private void c() {
        this.f.setListenter(new RecyclerPreloadView.ScrollFastListener() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.PhotoSelectFragment.1
            @Override // com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload.RecyclerPreloadView.ScrollFastListener
            public void scrollFast() {
                if (hcl.d(PhotoSelectFragment.this.b) && (PhotoSelectFragment.this.b instanceof Activity)) {
                    Glide.with((Activity) PhotoSelectFragment.this.b).pauseRequests();
                }
            }

            @Override // com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload.RecyclerPreloadView.ScrollFastListener
            public void scrollSlow() {
                if (hcl.d(PhotoSelectFragment.this.b) && (PhotoSelectFragment.this.b instanceof Activity)) {
                    Glide.with((Activity) PhotoSelectFragment.this.b).resumeRequests();
                }
            }
        });
    }

    private void b() {
        this.f.setNestedScrollingEnabled(false);
        this.f.addItemDecoration(new GridSpacingItemDecoration(4, hcl.e(this.b, 2.0f), false));
        this.f.setLayoutManager(new GridLayoutManager(this.b, 4));
        this.f.setHasFixedSize(true);
        RecyclerView.ItemAnimator itemAnimator = this.f.getItemAnimator();
        if (itemAnimator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
            this.f.setItemAnimator(null);
        }
        PictureImageGridAdapter pictureImageGridAdapter = new PictureImageGridAdapter(this.b);
        this.c = pictureImageGridAdapter;
        pictureImageGridAdapter.d(this.j);
        e();
        this.f.setAdapter(this.c);
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.f3722a || !this.c.c()) {
            return;
        }
        e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a() {
        ArrayList<PhotoModel> arrayList = this.l;
        if (arrayList == null || this.i == null) {
            LogUtil.h("Track_PhotoSelectFragment", "mSelectImageList or mLocalMediaList is null");
            return;
        }
        if (arrayList.size() <= 0 || this.i.size() <= 0) {
            LogUtil.h("Track_PhotoSelectFragment", "mSelectImageList.size or mLocalMediaList.size <= 0");
            return;
        }
        Collections.sort(this.l, new Comparator<PhotoModel>() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.PhotoSelectFragment.4
            @Override // java.util.Comparator
            /* renamed from: d, reason: merged with bridge method [inline-methods] */
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
            if (this.i.get(i).getRowId() == this.l.get(i2).getRowId()) {
                this.i.get(i).setSelected(true);
                arrayList2.add(this.i.get(i));
                i2++;
                if (i2 >= this.l.size()) {
                    this.l = arrayList2;
                    break;
                }
            }
            i++;
        }
        this.l = arrayList2;
    }

    private void e() {
        this.f3722a = true;
        this.e.setVisibility(0);
        LoaderManager.getInstance(this).initLoader(R.id.loader_id_media_store_data, null, this.d);
    }

    public void c(PhotoOrVideoSelectChangedListener photoOrVideoSelectChangedListener) {
        this.o = photoOrVideoSelectChangedListener;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a("Track_PhotoSelectFragment", "onActivityResult.");
        if (intent == null) {
            LogUtil.h("Track_PhotoSelectFragment", "intent is null");
            return;
        }
        int intExtra = intent.getIntExtra("index", 0);
        if (koq.b(this.i, intExtra)) {
            LogUtil.h("Track_PhotoSelectFragment", "mLocalMediaList is Out Of Bounds");
            return;
        }
        if (i2 != -1) {
            LogUtil.h("Track_PhotoSelectFragment", "requestCode is not OK");
            return;
        }
        if (i == 4097) {
            boolean booleanExtra = intent.getBooleanExtra("result", false);
            this.i.get(intExtra).setSelected(booleanExtra);
            if (booleanExtra) {
                this.j.onPictureAdd(this.i.get(intExtra));
            } else {
                this.j.onPictureRemove(this.i.get(intExtra));
            }
            this.c.notifyItemChanged(intExtra);
            return;
        }
        LogUtil.h("Track_PhotoSelectFragment", "requestCode not image preview request code");
    }
}
