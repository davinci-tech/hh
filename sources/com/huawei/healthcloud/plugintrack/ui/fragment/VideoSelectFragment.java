package com.huawei.healthcloud.plugintrack.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.PhotoOrVideoSelectChangedListener;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.VideoLoad;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload.OnPhotoSelectChangedListener;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload.RecyclerPreloadView;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload.SelectVideoGridAdapter;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.ui.GridSpacingItemDecoration;
import com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.utils.VideoModel;
import com.huawei.healthcloud.plugintrack.ui.activity.PhotoAndVideoSelectActivity;
import com.huawei.healthcloud.plugintrack.ui.activity.VideoPreviewActivity;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.base.BaseFragment;
import com.huawei.ui.commonui.healthtextview.HealthTextView;
import defpackage.hcl;
import defpackage.koq;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/* loaded from: classes4.dex */
public class VideoSelectFragment extends BaseFragment {
    private Context c;
    private ImageView d;
    private LinearLayout g;
    private RelativeLayout h;
    private RelativeLayout k;
    private PhotoOrVideoSelectChangedListener l;
    private HealthTextView o;
    private View t;
    private int m = 0;
    private SelectVideoGridAdapter e = null;
    private RecyclerPreloadView i = null;

    /* renamed from: a, reason: collision with root package name */
    private boolean f3759a = false;
    private ArrayList<VideoModel> n = new ArrayList<>(16);
    private List<VideoModel> j = new ArrayList(16);
    private OnPhotoSelectChangedListener<VideoModel> f = new OnPhotoSelectChangedListener<VideoModel>() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.VideoSelectFragment.5
        @Override // com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload.OnPhotoSelectChangedListener
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public void onPictureClick(int i, VideoModel videoModel) {
            if (i >= 0 && videoModel != null) {
                VideoPreviewActivity.bdd_(VideoSelectFragment.this.c, new Pair(Boolean.valueOf(!videoModel.isSelected() && isMaxCount()), videoModel), VideoSelectFragment.this.n.size(), i, VideoSelectFragment.this);
            } else {
                LogUtil.h("Track_VideoSelectFragment", "index < 0 or media is null");
            }
        }

        @Override // com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload.OnPhotoSelectChangedListener
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onPictureAdd(VideoModel videoModel) {
            if (VideoSelectFragment.this.n.contains(videoModel)) {
                return;
            }
            VideoSelectFragment.this.n.add(videoModel);
            VideoSelectFragment.e(VideoSelectFragment.this);
            VideoSelectFragment.this.l.setSelectData(VideoSelectFragment.this.n, VideoSelectFragment.this.m);
        }

        @Override // com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload.OnPhotoSelectChangedListener
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onPictureRemove(VideoModel videoModel) {
            if (VideoSelectFragment.this.n.contains(videoModel)) {
                VideoSelectFragment.this.n.remove(videoModel);
                VideoSelectFragment.h(VideoSelectFragment.this);
                VideoSelectFragment.this.l.setSelectData(VideoSelectFragment.this.n, VideoSelectFragment.this.m);
            }
        }

        @Override // com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload.OnPhotoSelectChangedListener
        public boolean isMaxCount() {
            return VideoSelectFragment.this.n.size() >= 1 || VideoSelectFragment.this.m >= 9;
        }
    };
    private final LoaderManager.LoaderCallbacks<List<VideoModel>> b = new LoaderManager.LoaderCallbacks<List<VideoModel>>() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.VideoSelectFragment.4
        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public void onLoaderReset(Loader<List<VideoModel>> loader) {
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        public Loader<List<VideoModel>> onCreateLoader(int i, Bundle bundle) {
            return new VideoLoad(VideoSelectFragment.this.c);
        }

        @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void onLoadFinished(Loader<List<VideoModel>> loader, List<VideoModel> list) {
            VideoSelectFragment.this.j = list;
            VideoSelectFragment.this.e();
            VideoSelectFragment.this.e.b(VideoSelectFragment.this.j);
            if (VideoSelectFragment.this.j == null || VideoSelectFragment.this.j.size() == 0) {
                VideoSelectFragment.this.g.setVisibility(0);
                VideoSelectFragment.this.k.setVisibility(8);
                VideoSelectFragment.this.i.setVisibility(8);
            } else {
                VideoSelectFragment.this.g.setVisibility(8);
                VideoSelectFragment.this.i.setVisibility(0);
            }
            VideoSelectFragment.this.f3759a = false;
            VideoSelectFragment.this.h.setVisibility(8);
        }
    };

    static /* synthetic */ int e(VideoSelectFragment videoSelectFragment) {
        int i = videoSelectFragment.m;
        videoSelectFragment.m = i + 1;
        return i;
    }

    static /* synthetic */ int h(VideoSelectFragment videoSelectFragment) {
        int i = videoSelectFragment.m;
        videoSelectFragment.m = i - 1;
        return i;
    }

    public void b(ArrayList<VideoModel> arrayList, int i) {
        this.n = arrayList;
        this.m = i;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        if (this.t == null) {
            this.t = layoutInflater.inflate(R.layout.trackalbum_video_select_fragment, viewGroup, false);
        }
        FragmentActivity activity = getActivity();
        if (!(activity instanceof PhotoAndVideoSelectActivity)) {
            LogUtil.h("Track_VideoSelectFragment", "Object is not instanceof PhotoAndVideoSelectActivity");
            return this.t;
        }
        this.c = (PhotoAndVideoSelectActivity) activity;
        a();
        c();
        d();
        return this.t;
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        if (this.e == null) {
            LogUtil.h("Track_VideoSelectFragment", "mAdapter is null");
            return;
        }
        if (z) {
            PhotoAndVideoSelectActivity photoAndVideoSelectActivity = getActivity() instanceof PhotoAndVideoSelectActivity ? (PhotoAndVideoSelectActivity) getActivity() : null;
            List<VideoModel> list = this.j;
            if (list == null || list.size() <= 0) {
                return;
            }
            this.e.e(photoAndVideoSelectActivity.c());
        }
    }

    private void a() {
        this.i = (RecyclerPreloadView) this.t.findViewById(R.id.video_recycler);
        this.g = (LinearLayout) this.t.findViewById(R.id.no_video);
        this.h = (RelativeLayout) this.t.findViewById(R.id.image_select_londing_rl);
        this.d = (ImageView) this.t.findViewById(R.id.cancel_image);
        this.k = (RelativeLayout) this.t.findViewById(R.id.select_video_tips_rl);
        HealthTextView healthTextView = (HealthTextView) this.t.findViewById(R.id.select_video_tips_text);
        this.o = healthTextView;
        healthTextView.setText(this.c.getString(R.string._2130840067_res_0x7f020a03, 10));
    }

    private void c() {
        this.d.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.VideoSelectFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                VideoSelectFragment.this.k.setVisibility(8);
                ViewClickInstrumentation.clickOnView(view);
            }
        });
        this.i.setListenter(new RecyclerPreloadView.ScrollFastListener() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.VideoSelectFragment.1
            @Override // com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload.RecyclerPreloadView.ScrollFastListener
            public void scrollFast() {
                if (hcl.d(VideoSelectFragment.this.c) && (VideoSelectFragment.this.c instanceof Activity)) {
                    Glide.with((Activity) VideoSelectFragment.this.c).pauseRequests();
                }
            }

            @Override // com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.photoselect.recyclepreload.RecyclerPreloadView.ScrollFastListener
            public void scrollSlow() {
                if (hcl.d(VideoSelectFragment.this.c) && (VideoSelectFragment.this.c instanceof Activity)) {
                    Glide.with((Activity) VideoSelectFragment.this.c).resumeRequests();
                }
            }
        });
    }

    private void d() {
        this.i.setNestedScrollingEnabled(false);
        this.i.addItemDecoration(new GridSpacingItemDecoration(4, hcl.e(this.c, 2.0f), false));
        this.i.setLayoutManager(new GridLayoutManager(this.c, 4));
        this.i.setHasFixedSize(true);
        RecyclerView.ItemAnimator itemAnimator = this.i.getItemAnimator();
        if (itemAnimator instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
            this.i.setItemAnimator(null);
        }
        SelectVideoGridAdapter selectVideoGridAdapter = new SelectVideoGridAdapter(this.c);
        this.e = selectVideoGridAdapter;
        selectVideoGridAdapter.b(this.f);
        b();
        this.i.setAdapter(this.e);
    }

    @Override // com.huawei.ui.commonui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (this.f3759a || !this.e.b()) {
            return;
        }
        b();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        ArrayList<VideoModel> arrayList = this.n;
        int i = 0;
        if (arrayList == null || this.j == null) {
            LogUtil.h("mSelectVideoList or mLocalMediaList is null", new Object[0]);
            return;
        }
        if (arrayList.size() <= 0 || this.j.size() <= 0) {
            LogUtil.h("mSelectVideoList or mLocalMediaList is null", new Object[0]);
            return;
        }
        Collections.sort(this.n, new Comparator<VideoModel>() { // from class: com.huawei.healthcloud.plugintrack.ui.fragment.VideoSelectFragment.3
            @Override // java.util.Comparator
            /* renamed from: c, reason: merged with bridge method [inline-methods] */
            public int compare(VideoModel videoModel, VideoModel videoModel2) {
                if (videoModel.getId() == videoModel2.getId()) {
                    return 0;
                }
                return videoModel.getId() > videoModel2.getId() ? -1 : 1;
            }
        });
        ArrayList<VideoModel> arrayList2 = new ArrayList<>(16);
        int i2 = 0;
        while (true) {
            if (i >= this.j.size()) {
                break;
            }
            if (this.j.get(i).getId() == this.n.get(i2).getId()) {
                this.j.get(i).setIsSelected(true);
                arrayList2.add(this.j.get(i));
                i2++;
                if (i2 >= this.n.size()) {
                    this.n = arrayList2;
                    break;
                }
            }
            i++;
        }
        this.n = arrayList2;
    }

    private void b() {
        this.f3759a = true;
        this.h.setVisibility(0);
        LoaderManager.getInstance(this).initLoader(0, null, this.b);
    }

    public void a(PhotoOrVideoSelectChangedListener photoOrVideoSelectChangedListener) {
        this.l = photoOrVideoSelectChangedListener;
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        LogUtil.a("Track_VideoSelectFragment", "onActivityResult.");
        if (intent == null) {
            LogUtil.h("Track_VideoSelectFragment", "intent is null.");
            return;
        }
        int intExtra = intent.getIntExtra("index", 0);
        if (koq.b(this.j, intExtra)) {
            LogUtil.h("Track_VideoSelectFragment", "mLocalMediaList is out of bound.");
            return;
        }
        if (i2 != -1) {
            LogUtil.h("Track_VideoSelectFragment", "requestCode is not ok");
            return;
        }
        if (i == 4098) {
            boolean booleanExtra = intent.getBooleanExtra("result", false);
            this.j.get(intExtra).setIsSelected(booleanExtra);
            if (booleanExtra) {
                this.f.onPictureAdd(this.j.get(intExtra));
            } else {
                this.f.onPictureRemove(this.j.get(intExtra));
            }
            this.e.notifyItemChanged(intExtra);
        }
    }
}
