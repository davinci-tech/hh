package com.huawei.ui.device.adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import defpackage.nyr;
import defpackage.obs;
import health.compact.a.SharedPreferenceManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* loaded from: classes6.dex */
public class ScanDeviceAdapter extends RecyclerView.Adapter<RecyclerHolder> {
    private static final Map<String, Integer> b = Collections.unmodifiableMap(new HashMap<String, Integer>() { // from class: com.huawei.ui.device.adapter.ScanDeviceAdapter.3
        {
            put("HDK_WEIGHT", Integer.valueOf(R.mipmap._2131821409_res_0x7f110361));
            put("HDK_BLOOD_SUGAR", Integer.valueOf(R.mipmap._2131821408_res_0x7f110360));
            put("HDK_BLOOD_PRESSURE", Integer.valueOf(R.mipmap._2131821407_res_0x7f11035f));
            put("HDK_HEART_RATE", Integer.valueOf(R.mipmap._2131821414_res_0x7f110366));
            put("HDK_BODY_TEMPERATURE", Integer.valueOf(R.mipmap._2131821421_res_0x7f11036d));
            put("HDK_BLOOD_OXYGEN", Integer.valueOf(R.mipmap._2131821406_res_0x7f11035e));
            put("HDK_ROPE_SKIPPING", Integer.valueOf(R.mipmap._2131821416_res_0x7f110368));
            put("HDK_TREADMILL", Integer.valueOf(R.mipmap._2131821422_res_0x7f11036e));
            put("HDK_EXERCISE_BIKE", Integer.valueOf(R.mipmap._2131821413_res_0x7f110365));
            put("HDK_ROWING_MACHINE", Integer.valueOf(R.mipmap._2131821417_res_0x7f110369));
            put("HDK_ELLIPTICAL_MACHINE", Integer.valueOf(R.mipmap._2131821412_res_0x7f110364));
            put("HDK_WALKING_MACHINE", Integer.valueOf(R.mipmap._2131821423_res_0x7f11036f));
            put("HDK_ECG", Integer.valueOf(R.mipmap._2131821411_res_0x7f110363));
            put("HDK_SMART_PILLOW", Integer.valueOf(R.mipmap._2131821419_res_0x7f11036b));
            put("HDK_MASSAGE_GUN", Integer.valueOf(R.mipmap._2131821415_res_0x7f110367));
            put("SMART_HEADPHONES", Integer.valueOf(R.mipmap._2131821418_res_0x7f11036a));
        }
    });

    /* renamed from: a, reason: collision with root package name */
    private BaseRecyclerAdapter.OnItemClickListener f9281a;
    private AsyncListDiffer<nyr> c;
    private int d;
    private boolean e = false;
    private RecyclerHolder h;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i == 0 ? 0 : 1;
    }

    static class a extends DiffUtil.ItemCallback<nyr> {
        private a() {
        }

        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        /* renamed from: d, reason: merged with bridge method [inline-methods] */
        public boolean areItemsTheSame(nyr nyrVar, nyr nyrVar2) {
            LogUtil.a("ScanDeviceAdapter", "areItemsTheSame: ", nyrVar, "  ", nyrVar2);
            if (nyrVar == null || nyrVar2 == null) {
                return true;
            }
            return nyrVar.equals(nyrVar2);
        }

        @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public boolean areContentsTheSame(nyr nyrVar, nyr nyrVar2) {
            LogUtil.a("ScanDeviceAdapter", "areContentsTheSame: ", nyrVar, "  ", nyrVar2);
            if (nyrVar == null || nyrVar2 == null) {
                return true;
            }
            return nyrVar.equals(nyrVar2);
        }
    }

    public ScanDeviceAdapter(BaseRecyclerAdapter.OnItemClickListener<nyr> onItemClickListener) {
        if (this.c == null) {
            this.c = new AsyncListDiffer<>(this, new a());
        }
        this.d = R.layout.activity_one_key_scan_item;
        this.f9281a = onItemClickListener;
        e((List<nyr>) null);
        LogUtil.a("ScanDeviceAdapter", "ScanDeviceAdapter init");
    }

    public ScanDeviceAdapter(BaseRecyclerAdapter.OnItemClickListener<nyr> onItemClickListener, boolean z) {
        if (this.c == null) {
            this.c = new AsyncListDiffer<>(this, new a());
        }
        this.d = R.layout.activity_three_fold_one_key_scan_item;
        this.f9281a = onItemClickListener;
        e((List<nyr>) null);
        LogUtil.a("ScanDeviceAdapter", "ThreeFold ScanDeviceAdapter isThreeFoldFontsFlag : ", Boolean.valueOf(z));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: cTq_, reason: merged with bridge method [inline-methods] */
    public RecyclerHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(this.d, viewGroup, false);
        if (i == 0) {
            inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_scan_radarview, viewGroup, false);
        }
        RecyclerHolder recyclerHolder = new RecyclerHolder(inflate);
        cTo_(recyclerHolder.itemView);
        LogUtil.a("ScanDeviceAdapter", "onCreateViewHolder: initAnim");
        return recyclerHolder;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(final RecyclerHolder recyclerHolder, int i) {
        if (i == 0) {
            if (this.h != null) {
                return;
            }
            this.h = recyclerHolder;
            BaseRecyclerAdapter.OnItemClickListener onItemClickListener = this.f9281a;
            if (onItemClickListener != null) {
                onItemClickListener.onItemClicked(recyclerHolder, i, null);
                return;
            }
            return;
        }
        final nyr nyrVar = this.c.getCurrentList().get(i);
        e(recyclerHolder, nyrVar);
        LogUtil.a("ScanDeviceAdapter", "onBindViewHolder");
        if (recyclerHolder.itemView != null) {
            recyclerHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.ui.device.adapter.ScanDeviceAdapter.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    int indexOf = ScanDeviceAdapter.this.c.getCurrentList().indexOf(nyrVar);
                    if (ScanDeviceAdapter.this.f9281a != null) {
                        ScanDeviceAdapter.this.f9281a.onItemClicked(recyclerHolder, indexOf, nyrVar);
                    }
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
        if (this.e) {
            cTp_(recyclerHolder.itemView);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.c.getCurrentList().size();
    }

    private void cTp_(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "translationY", 100.0f, 0.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new obs(0.2f, 0.0f, 0.2f, 1.0f));
        animatorSet.setDuration(350L);
        animatorSet.setStartDelay(50L);
        animatorSet.play(ofFloat);
        animatorSet.start();
    }

    private void cTo_(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "translationY", 100.0f, 0.0f);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, "scaleX", 0.8f, 1.0f);
        ObjectAnimator ofFloat3 = ObjectAnimator.ofFloat(view, "scaleY", 0.8f, 1.0f);
        ObjectAnimator ofFloat4 = ObjectAnimator.ofFloat(view, "alpha", 0.0f, 1.0f, 1.0f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setInterpolator(new obs(0.2f, 0.0f, 0.2f, 1.0f));
        animatorSet.setDuration(300L);
        animatorSet.playTogether(ofFloat, ofFloat2, ofFloat3, ofFloat4);
        animatorSet.start();
    }

    public void e(final RecyclerHolder recyclerHolder, final nyr nyrVar) {
        if (nyrVar != null) {
            recyclerHolder.b(R.id.scan_device_name, nyrVar.b());
            if ("wear_watch".equals(nyrVar.i())) {
                recyclerHolder.d(R.id.scan_device_icon, R.mipmap._2131821424_res_0x7f110370);
            } else if ("wear_band".equals(nyrVar.i())) {
                recyclerHolder.d(R.id.scan_device_icon, R.mipmap._2131821405_res_0x7f11035d);
            } else if ("SPORTS_GENIE".equals(nyrVar.i())) {
                recyclerHolder.d(R.id.scan_device_icon, R.mipmap._2131821420_res_0x7f11036c);
            } else {
                LogUtil.a("ScanDeviceAdapter", "Other third devices");
                Map<String, Integer> map = b;
                if (map.containsKey(nyrVar.i())) {
                    recyclerHolder.d(R.id.scan_device_icon, map.get(nyrVar.i()).intValue());
                } else {
                    LogUtil.a("ScanDeviceAdapter", "Invalid device kindId");
                }
            }
            recyclerHolder.cwP_(R.id.scan_device_connect, new View.OnClickListener() { // from class: com.huawei.ui.device.adapter.ScanDeviceAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    LogUtil.a("ScanDeviceAdapter", "convert onClick deviceName:", nyrVar.b());
                    nyr nyrVar2 = nyrVar;
                    if (nyrVar2 != null) {
                        SharedPreferenceManager.c(String.valueOf(10), "app_big_data_device_name", nyrVar2.b());
                    }
                    ScanDeviceAdapter.this.f9281a.onItemClicked(recyclerHolder, ScanDeviceAdapter.this.c.getCurrentList().indexOf(nyrVar), nyrVar);
                    ViewClickInstrumentation.clickOnView(view);
                }
            });
        }
    }

    public void e(List<nyr> list) {
        if (this.e) {
            return;
        }
        if (list == null && (this.c.getCurrentList().size() > 1 || this.c.getCurrentList().size() == 0)) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(null);
            this.c.submitList(arrayList);
        } else if (list != null) {
            list.add(0, null);
            this.c.submitList(list);
        }
    }

    public void b(boolean z) {
        LogUtil.a("ScanDeviceAdapter", "setUpValue:", Boolean.valueOf(z));
        this.e = z;
    }

    public void c(BaseRecyclerAdapter.OnItemClickListener onItemClickListener) {
        this.f9281a = onItemClickListener;
    }

    public List<nyr> a() {
        return this.c.getCurrentList();
    }
}
