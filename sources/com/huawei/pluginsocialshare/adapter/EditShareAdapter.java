package com.huawei.pluginsocialshare.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.huawei.haf.application.BaseApplication;
import com.huawei.haf.common.utils.NetworkUtil;
import com.huawei.health.R;
import com.huawei.health.health.utils.functionsetcard.reader.FunctionSetBeanReader;
import com.huawei.health.socialshare.model.socialsharebean.ShareDataInfo;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.hwcommonmodel.constants.AnalyticsValue;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.pluginsocialshare.adapter.EditShareAdapter;
import com.huawei.pluginsocialshare.cloud.bean.DownloadCallback;
import com.huawei.pluginsocialshare.view.ShareRecycleScrollItemView;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import com.huawei.ui.commonui.checkbox.HealthCheckBox;
import com.huawei.ui.commonui.dialog.CustomViewDialog;
import com.huawei.ui.commonui.recycleview.HealthRecycleView;
import defpackage.ixx;
import defpackage.jcf;
import defpackage.koq;
import defpackage.mut;
import defpackage.mva;
import defpackage.mvp;
import defpackage.nrh;
import defpackage.nsf;
import defpackage.nsn;
import health.compact.a.CommonUtil;
import health.compact.a.EnvironmentInfo;
import health.compact.a.ReleaseLogUtil;
import health.compact.a.SharedPreferenceManager;
import health.compact.a.StorageParams;
import health.compact.a.UnitUtil;
import java.io.File;
import java.lang.ref.WeakReference;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.json.JSONObject;

/* loaded from: classes6.dex */
public class EditShareAdapter extends RecyclerView.Adapter<RecyclerHolder> {
    private d b;
    private Activity c;
    private int[][] f;
    private ShareRecycleScrollItemView g;
    private int h;
    private Context j;
    private HealthRecycleView k;
    private OnBackgroundChangeListener l;
    private int[][] m;
    private OnMoreListener n;
    private int o;
    private List<ShareDataInfo> p;
    private int s;
    private List<ShareDataInfo> e = new ArrayList();
    private ShareDataInfo d = new mvp();

    /* renamed from: a, reason: collision with root package name */
    private int f8518a = -1;
    private Handler i = new Handler() { // from class: com.huawei.pluginsocialshare.adapter.EditShareAdapter.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message == null) {
                LogUtil.h("share_EditShareAdapter", "receive empty msg");
                return;
            }
            int i = message.what;
            if (i != 1) {
                if (i == 2) {
                    LogUtil.a("share_EditShareAdapter", "MSG_DOWNLOAD_THEME_FAIL");
                    nrh.e(EditShareAdapter.this.c.getApplicationContext(), R.string._2130850096_res_0x7f023130);
                    return;
                }
                if (i == 3) {
                    if (message.obj instanceof ShareRecycleScrollItemView) {
                        ShareRecycleScrollItemView shareRecycleScrollItemView = (ShareRecycleScrollItemView) message.obj;
                        if (shareRecycleScrollItemView.getShareDataInfo() instanceof mut) {
                            mut mutVar = (mut) shareRecycleScrollItemView.getShareDataInfo();
                            EditShareAdapter editShareAdapter = EditShareAdapter.this;
                            editShareAdapter.d(mutVar, shareRecycleScrollItemView, editShareAdapter.b);
                            return;
                        }
                        return;
                    }
                    return;
                }
                LogUtil.h("share_EditShareAdapter", "msg unknown");
                return;
            }
            if (message.obj instanceof ShareRecycleScrollItemView) {
                ShareRecycleScrollItemView shareRecycleScrollItemView2 = (ShareRecycleScrollItemView) message.obj;
                shareRecycleScrollItemView2.setShowSelected(true);
                shareRecycleScrollItemView2.setDownloading(EditShareAdapter.this.c, false);
                shareRecycleScrollItemView2.setShowDown(false);
                shareRecycleScrollItemView2.setShowNew(false);
                if (EditShareAdapter.this.g != null) {
                    EditShareAdapter.this.g.setShowSelected(false);
                }
                EditShareAdapter.this.g = shareRecycleScrollItemView2;
                EditShareAdapter.this.o = message.arg1;
                if (EditShareAdapter.this.l != null) {
                    EditShareAdapter.this.l.onBackgroundChange(EditShareAdapter.this.h, message.arg1, shareRecycleScrollItemView2);
                }
            }
        }
    };

    /* loaded from: classes.dex */
    public interface OnBackgroundChangeListener {
        void onBackgroundChange(int i, int i2, View view);
    }

    public interface OnMoreListener {
        void onLoadingMore();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        return i;
    }

    public EditShareAdapter(Activity activity, HealthRecycleView healthRecycleView, int i, OnBackgroundChangeListener onBackgroundChangeListener, int i2) {
        this.h = i2;
        if (healthRecycleView == null) {
            LogUtil.h("share_EditShareAdapter", "recyclerView == null");
            return;
        }
        if (this.p == null) {
            this.p = new ArrayList();
        }
        this.j = activity.getApplicationContext();
        this.c = activity;
        this.k = healthRecycleView;
        if (healthRecycleView.getItemAnimator() instanceof SimpleItemAnimator) {
            ((SimpleItemAnimator) this.k.getItemAnimator()).setSupportsChangeAnimations(false);
        }
        this.k.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.huawei.pluginsocialshare.adapter.EditShareAdapter.5
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i3, int i4) {
                super.onScrolled(recyclerView, i3, i4);
                if (recyclerView.canScrollHorizontally(1) || EditShareAdapter.this.n == null) {
                    return;
                }
                EditShareAdapter.this.n.onLoadingMore();
            }
        });
        this.l = onBackgroundChangeListener;
        this.s = nsn.c(this.j, 80.0f);
    }

    public void b(int i) {
        this.o = i;
        int a2 = e.a(this.k);
        boolean c = e.c(this.k);
        if (a2 > 1 && this.h == 1 && c) {
            this.f = e.d(a2, getItemCount());
            this.m = e.a(a2, getItemCount());
        }
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: cpr_, reason: merged with bridge method [inline-methods] */
    public RecyclerHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new RecyclerHolder(LayoutInflater.from(this.j).inflate(R.layout.item_share_layout, viewGroup, false));
    }

    public void e(List<ShareDataInfo> list) {
        this.e = list;
    }

    public List<ShareDataInfo> c() {
        return this.e;
    }

    public void c(List<ShareDataInfo> list, int i, boolean z) {
        if (koq.b(list)) {
            ReleaseLogUtil.a("share_EditShareAdapter", "updateList shareDataList is null");
            return;
        }
        ShareDataInfo shareDataInfo = koq.d(list, i) ? list.get(i) : null;
        if (z && this.h == 1 && ((list.size() > this.e.size() + 6 && !nsn.ag(this.j)) || (list.size() > this.e.size() + 12 && nsn.ag(this.j)))) {
            list.removeAll(this.e);
        }
        this.p.clear();
        this.p.addAll(list);
        if (shareDataInfo != null && z && !koq.b(this.p)) {
            this.p.remove(shareDataInfo);
            this.p.remove(shareDataInfo);
            this.p.add(1, shareDataInfo);
            this.o = this.p.indexOf(shareDataInfo);
        } else {
            this.o = i;
        }
        int a2 = e.a(this.k);
        boolean c = e.c(this.k);
        if (a2 > 1 && this.h == 1 && c) {
            this.f = e.d(a2, getItemCount());
            this.m = e.a(a2, getItemCount());
        }
        notifyDataSetChanged();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public void onBindViewHolder(RecyclerHolder recyclerHolder, int i) {
        ShareRecycleScrollItemView shareRecycleScrollItemView = (ShareRecycleScrollItemView) recyclerHolder.cwK_(R.id.item_single_share);
        int adapterPosition = recyclerHolder.getAdapterPosition();
        if (koq.d(this.p, i) && i != this.f8518a) {
            shareRecycleScrollItemView.setContentViewImage(this.p.get(i));
        }
        ShareDataInfo shareDataInfo = shareRecycleScrollItemView.getShareDataInfo();
        if (shareDataInfo != null && shareDataInfo.getId() != -2) {
            b(shareRecycleScrollItemView, adapterPosition, shareDataInfo);
        }
        if (shareRecycleScrollItemView.getLayoutParams() instanceof RelativeLayout.LayoutParams) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) shareRecycleScrollItemView.getLayoutParams();
            layoutParams.height = this.s;
            layoutParams.width = this.s;
            int dimensionPixelSize = this.j.getResources().getDimensionPixelSize(R.dimen._2131363316_res_0x7f0a05f4);
            int dimensionPixelSize2 = this.j.getResources().getDimensionPixelSize(R.dimen._2131362576_res_0x7f0a0310);
            int dimensionPixelSize3 = this.j.getResources().getDimensionPixelSize(R.dimen._2131362581_res_0x7f0a0315);
            if (!(this.k.getLayoutManager() instanceof GridLayoutManager) || ((GridLayoutManager) this.k.getLayoutManager()).getSpanCount() == 1) {
                dimensionPixelSize = 0;
            }
            layoutParams.setMargins(dimensionPixelSize3, dimensionPixelSize, dimensionPixelSize2, 0);
            shareRecycleScrollItemView.setLayoutParams(layoutParams);
        }
        String a2 = e.a(i, this.h, this.k, this.m, this.f);
        int i2 = this.o;
        if (i2 == adapterPosition) {
            OnBackgroundChangeListener onBackgroundChangeListener = this.l;
            if (onBackgroundChangeListener != null && i2 != 0) {
                onBackgroundChangeListener.onBackgroundChange(this.h, i2, shareRecycleScrollItemView);
            }
            if (this.o != 0 || this.h != 1) {
                ShareRecycleScrollItemView shareRecycleScrollItemView2 = this.g;
                if (shareRecycleScrollItemView2 != null) {
                    shareRecycleScrollItemView2.setShowSelected(false);
                }
                shareRecycleScrollItemView.setShowSelected(true);
                e.cpt_(shareRecycleScrollItemView, a2, true);
                this.g = shareRecycleScrollItemView;
            } else {
                shareRecycleScrollItemView.setShowSelected(false);
                e.cpt_(shareRecycleScrollItemView, a2, false);
            }
        } else {
            e.cpt_(shareRecycleScrollItemView, a2, false);
            shareRecycleScrollItemView.setShowSelected(false);
        }
        shareRecycleScrollItemView.setDownloading(this.c, shareDataInfo != null && shareDataInfo.getId() == -2);
    }

    private void b(final ShareRecycleScrollItemView shareRecycleScrollItemView, final int i, final ShareDataInfo shareDataInfo) {
        shareRecycleScrollItemView.setOnClickListener(new View.OnClickListener() { // from class: mtx
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditShareAdapter.this.cpn_(shareDataInfo, i, shareRecycleScrollItemView, view);
            }
        });
    }

    public /* synthetic */ void cpn_(ShareDataInfo shareDataInfo, int i, ShareRecycleScrollItemView shareRecycleScrollItemView, View view) {
        if (!(view instanceof ShareRecycleScrollItemView)) {
            ViewClickInstrumentation.clickOnView(view);
            return;
        }
        HashMap hashMap = new HashMap(2);
        hashMap.put("click", 1);
        hashMap.put("type", Integer.valueOf(this.h));
        hashMap.put(FunctionSetBeanReader.BI_ELEMENT, Integer.valueOf(shareDataInfo.getId()));
        ixx.d().d(this.j.getApplicationContext(), AnalyticsValue.EDIT_SHARE_TEMPLATE_INDEX_2040104.value(), hashMap, 0);
        ShareRecycleScrollItemView shareRecycleScrollItemView2 = (ShareRecycleScrollItemView) view;
        boolean z = !(i == 0 || EnvironmentInfo.k()) || EnvironmentInfo.k();
        ShareRecycleScrollItemView shareRecycleScrollItemView3 = this.g;
        if (shareRecycleScrollItemView3 != null && z) {
            shareRecycleScrollItemView3.setShowSelected(false);
        }
        if ((shareRecycleScrollItemView2.getShareDataInfo() instanceof mut) && !new File(shareRecycleScrollItemView2.getShareDataInfo().getPath()).exists()) {
            if (((mut) shareRecycleScrollItemView2.getShareDataInfo()).e()) {
                LogUtil.a("share_EditShareAdapter", "itemView is downloading");
                ViewClickInstrumentation.clickOnView(view);
                return;
            } else {
                d dVar = new d(shareRecycleScrollItemView2, i, this.c, this.i);
                this.b = dVar;
                a(shareRecycleScrollItemView2, dVar, i);
            }
        } else {
            if (z || this.h != 1) {
                a(shareRecycleScrollItemView2, i);
            } else {
                shareRecycleScrollItemView2.setShowSelected(false);
                e.cpt_(shareRecycleScrollItemView, e.a(i, this.h, this.k, this.m, this.f), false);
            }
            this.o = i;
            OnBackgroundChangeListener onBackgroundChangeListener = this.l;
            if (onBackgroundChangeListener != null) {
                onBackgroundChangeListener.onBackgroundChange(this.h, i, shareRecycleScrollItemView);
            }
        }
        ViewClickInstrumentation.clickOnView(view);
    }

    private void a(ShareRecycleScrollItemView shareRecycleScrollItemView, int i) {
        ShareRecycleScrollItemView shareRecycleScrollItemView2 = this.g;
        if (shareRecycleScrollItemView2 != null) {
            shareRecycleScrollItemView2.setShowSelected(false);
            e.cpt_(shareRecycleScrollItemView, e.a(i, this.h, this.k, this.m, this.f), false);
        }
        ShareRecycleScrollItemView shareRecycleScrollItemView3 = this.g;
        if (shareRecycleScrollItemView3 != null && shareRecycleScrollItemView3.getShareDataInfo() == shareRecycleScrollItemView.getShareDataInfo() && this.h == 3) {
            this.g = null;
            LogUtil.a("share_EditShareAdapter", "cancel stick");
        } else {
            shareRecycleScrollItemView.setShowSelected(true);
            e.cpt_(shareRecycleScrollItemView, e.a(i, this.h, this.k, this.m, this.f), true);
            this.g = shareRecycleScrollItemView;
            e(i);
        }
    }

    public void a() {
        ShareRecycleScrollItemView shareRecycleScrollItemView = this.g;
        if (shareRecycleScrollItemView != null) {
            shareRecycleScrollItemView.setShowSelected(false);
        }
    }

    private void a(ShareRecycleScrollItemView shareRecycleScrollItemView, DownloadCallback downloadCallback, int i) {
        LogUtil.a("share_EditShareAdapter", "enter changeBackgroundView");
        if (shareRecycleScrollItemView.getShareDataInfo() instanceof mut) {
            mut mutVar = (mut) shareRecycleScrollItemView.getShareDataInfo();
            if (CommonUtil.aa(BaseApplication.e()) && !NetworkUtil.m()) {
                if (this.i != null) {
                    Message obtain = Message.obtain();
                    obtain.what = 3;
                    obtain.obj = shareRecycleScrollItemView;
                    this.i.sendMessage(obtain);
                    return;
                }
                return;
            }
            if (NetworkUtil.m()) {
                LogUtil.a("share_EditShareAdapter", "changeView :", Integer.valueOf(i));
                shareRecycleScrollItemView.setDownloading(this.c, true);
                ArrayList arrayList = new ArrayList();
                mutVar.c(true);
                arrayList.add(mutVar);
                new mva().cqd_(arrayList, true, this.c, downloadCallback);
                return;
            }
            nrh.b(this.j, R.string._2130841392_res_0x7f020f30);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void d(final mut mutVar, final ShareRecycleScrollItemView shareRecycleScrollItemView, final DownloadCallback downloadCallback) {
        String b = SharedPreferenceManager.b(this.j, Integer.toString(20002), "lastTimeShowDialog");
        if (!TextUtils.isEmpty(b) && "-1".equals(b)) {
            shareRecycleScrollItemView.setDownloading(this.c, true);
            ArrayList arrayList = new ArrayList();
            mutVar.c(true);
            arrayList.add(mutVar);
            new mva().cqd_(arrayList, true, this.c, downloadCallback);
            return;
        }
        View inflate = View.inflate(this.j, R.layout.reminder_with_checkbox_dialog, null);
        TextView textView = (TextView) inflate.findViewById(R.id.reminder_context);
        ((HealthCheckBox) inflate.findViewById(R.id.no_more_reminder_checkbox)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.huawei.pluginsocialshare.adapter.EditShareAdapter$$ExternalSyntheticLambda1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                EditShareAdapter.this.cpo_(compoundButton, z);
            }
        });
        textView.setText(a(mutVar.getImageSize()));
        CustomViewDialog.Builder builder = new CustomViewDialog.Builder(this.c);
        builder.czg_(inflate).czc_(R.string._2130841130_res_0x7f020e2a, new View.OnClickListener() { // from class: muf
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditShareAdapter.this.cpp_(view);
            }
        }).cze_(R.string._2130841131_res_0x7f020e2b, new View.OnClickListener() { // from class: muc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EditShareAdapter.this.cpq_(shareRecycleScrollItemView, mutVar, downloadCallback, view);
            }
        });
        CustomViewDialog e2 = builder.e();
        e2.setCancelable(false);
        e2.show();
    }

    /* synthetic */ void cpo_(CompoundButton compoundButton, boolean z) {
        c(String.valueOf(z ? -1L : System.currentTimeMillis()));
        ViewClickInstrumentation.clickOnView(compoundButton);
    }

    public /* synthetic */ void cpp_(View view) {
        c(String.valueOf(System.currentTimeMillis()));
        ViewClickInstrumentation.clickOnView(view);
    }

    public /* synthetic */ void cpq_(ShareRecycleScrollItemView shareRecycleScrollItemView, mut mutVar, DownloadCallback downloadCallback, View view) {
        shareRecycleScrollItemView.setDownloading(this.c, true);
        ArrayList arrayList = new ArrayList();
        mutVar.c(true);
        arrayList.add(mutVar);
        new mva().cqd_(arrayList, true, this.c, downloadCallback);
        c(String.valueOf(-1));
        ViewClickInstrumentation.clickOnView(view);
    }

    private void c(String str) {
        SharedPreferenceManager.e(this.j, Integer.toString(20002), "lastTimeShowDialog", str, new StorageParams());
    }

    private String a(int i) {
        double d2 = i / 1048576.0d;
        if (d2 < 0.1d) {
            d2 = 0.1d;
        }
        return String.format(Locale.ENGLISH, this.j.getString(R.string._2130843018_res_0x7f02158a), this.j.getString(R.string.IDS_device_upgrade_file_size_mb, UnitUtil.e(d2, 1, 1)));
    }

    public void d() {
        this.d.setId(-2);
        if (this.p.contains(this.d)) {
            return;
        }
        this.p.add(this.d);
        int size = this.p.size() - 1;
        this.f8518a = size;
        notifyItemChanged(size);
    }

    public void e() {
        if (koq.b(this.p)) {
            LogUtil.h("share_EditShareAdapter", "mShareDataInfoList is null");
            return;
        }
        if (this.p.remove(this.d)) {
            int a2 = e.a(this.k);
            boolean c = e.c(this.k);
            if (a2 > 1 && this.h == 1 && c) {
                this.f = e.d(a2, getItemCount());
                this.m = e.a(a2, getItemCount());
            }
            notifyDataSetChanged();
        }
        this.f8518a = -1;
    }

    public EditShareAdapter b(List<ShareDataInfo> list) {
        LogUtil.a("share_EditShareAdapter", "enter enableMoreLoad");
        e();
        int size = this.p.size();
        if (koq.b(list) && this.p.containsAll(this.e)) {
            LogUtil.h("share_EditShareAdapter", "enableMoreLoad no more");
            return this;
        }
        if (koq.b(list)) {
            LogUtil.a("share_EditShareAdapter", "add healthdata");
            this.e.removeAll(this.p);
            this.p.addAll(this.e);
        } else {
            this.p.removeAll(list);
            this.p.addAll(list);
        }
        notifyItemRangeChanged(size, getItemCount() - size);
        return this;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.p.size();
    }

    public List<ShareDataInfo> b() {
        return this.p;
    }

    public EditShareAdapter e(OnMoreListener onMoreListener) {
        this.n = onMoreListener;
        return this;
    }

    private void e(int i) {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) this.k.getLayoutManager();
        int findLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
        for (int findFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition(); findFirstVisibleItemPosition <= findLastVisibleItemPosition && this.k.findViewHolderForAdapterPosition(findFirstVisibleItemPosition) != null && (this.k.findViewHolderForAdapterPosition(findFirstVisibleItemPosition) instanceof RecyclerHolder); findFirstVisibleItemPosition++) {
            RecyclerHolder recyclerHolder = (RecyclerHolder) this.k.findViewHolderForAdapterPosition(findFirstVisibleItemPosition);
            if (recyclerHolder != null) {
                ShareRecycleScrollItemView shareRecycleScrollItemView = (ShareRecycleScrollItemView) recyclerHolder.cwK_(R.id.item_single_share);
                if (i != findFirstVisibleItemPosition) {
                    e.cpt_(shareRecycleScrollItemView, e.a(findFirstVisibleItemPosition, this.h, this.k, this.m, this.f), false);
                }
            }
        }
    }

    static class e {
        /* JADX INFO: Access modifiers changed from: private */
        public static int[][] a(int i, int i2) {
            if (i <= 1 || i2 == 0) {
                return null;
            }
            int i3 = i2 / i;
            if (i2 % i != 0) {
                i3++;
            }
            int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i, i3);
            int i4 = 0;
            for (int[] iArr2 : iArr) {
                int i5 = 0;
                while (true) {
                    if (i5 < iArr2.length) {
                        iArr2[i5] = i4;
                        i4++;
                        i5++;
                    }
                }
            }
            return iArr;
        }

        public static int[][] d(int i, int i2) {
            if (i <= 1 || i2 == 0) {
                return null;
            }
            int i3 = i2 / i;
            if (i2 % i != 0) {
                i3++;
            }
            int[][] iArr = (int[][]) Array.newInstance((Class<?>) Integer.TYPE, i, i3);
            int i4 = 0;
            for (int i5 = 0; i5 < iArr[0].length; i5++) {
                for (int[] iArr2 : iArr) {
                    iArr2[i5] = i4;
                    i4++;
                }
            }
            return iArr;
        }

        /* JADX WARN: Code restructure failed: missing block: B:17:0x001c, code lost:
        
            r1 = r1 + 1;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        private static int[] d(int[][] r5, int r6) {
            /*
                if (r5 == 0) goto L1f
                if (r6 >= 0) goto L5
                goto L1f
            L5:
                r0 = 0
                r1 = r0
            L7:
                int r2 = r5.length
                if (r1 >= r2) goto L1f
                r2 = r0
            Lb:
                r3 = r5[r1]
                int r4 = r3.length
                if (r2 >= r4) goto L1c
                r3 = r3[r2]
                if (r3 != r6) goto L19
                int[] r5 = new int[]{r1, r2}
                return r5
            L19:
                int r2 = r2 + 1
                goto Lb
            L1c:
                int r1 = r1 + 1
                goto L7
            L1f:
                r5 = 0
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: com.huawei.pluginsocialshare.adapter.EditShareAdapter.e.d(int[][], int):int[]");
        }

        private static int a(int[] iArr, int[][] iArr2) {
            if (iArr == null || iArr2 == null || iArr.length < 2) {
                return -1;
            }
            int i = iArr[0];
            int i2 = iArr[iArr2.length - 1];
            if (i < 0 || i >= iArr2.length || i2 < 0) {
                return -1;
            }
            int[] iArr3 = iArr2[i];
            if (i2 < iArr3.length) {
                return iArr3[i2];
            }
            return -1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void cpt_(View view, String str, boolean z) {
            if (TextUtils.isEmpty(str)) {
                return;
            }
            if (nsf.h(R.string._2130850654_res_0x7f02335e).equals(str)) {
                jcf.bEA_(view, str, Button.class);
            } else {
                jcf.bEK_(view, nsf.b(R.string._2130850655_res_0x7f02335f, str), z, Button.class);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String a(int i, int i2, HealthRecycleView healthRecycleView, int[][] iArr, int[][] iArr2) {
            if (i2 != 1) {
                return String.valueOf(i + 1);
            }
            if (i == 0) {
                return nsf.h(R.string._2130850654_res_0x7f02335e);
            }
            int a2 = a(healthRecycleView);
            boolean c = c(healthRecycleView);
            if (a2 == 1 || !c) {
                return String.valueOf(i);
            }
            return String.valueOf(a(d(iArr2, i), iArr));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static boolean c(HealthRecycleView healthRecycleView) {
            return healthRecycleView != null && ((LinearLayoutManager) healthRecycleView.getLayoutManager()).getOrientation() == 0;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static int a(HealthRecycleView healthRecycleView) {
            if (healthRecycleView == null || !(healthRecycleView.getLayoutManager() instanceof GridLayoutManager)) {
                return 1;
            }
            return ((GridLayoutManager) healthRecycleView.getLayoutManager()).getSpanCount();
        }
    }

    static class d implements DownloadCallback {

        /* renamed from: a, reason: collision with root package name */
        private WeakReference<Activity> f8520a;
        private ShareRecycleScrollItemView b;
        private Handler c;
        private int e;

        d(ShareRecycleScrollItemView shareRecycleScrollItemView, int i, Activity activity, Handler handler) {
            this.b = shareRecycleScrollItemView;
            this.f8520a = new WeakReference<>(activity);
            this.e = i;
            this.c = handler;
        }

        @Override // com.huawei.pluginsocialshare.cloud.bean.DownloadCallback
        public void onSuccess(JSONObject jSONObject, ShareDataInfo shareDataInfo) {
            Activity activity = this.f8520a.get();
            if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
                LogUtil.h("share_EditShareAdapter", "WeakDownloadCallback  onSuccess activity is null, finish or destroyed");
                return;
            }
            if (this.c != null) {
                Message obtain = Message.obtain();
                obtain.what = 1;
                obtain.obj = this.b;
                obtain.arg1 = this.e;
                this.c.sendMessage(obtain);
            }
        }

        @Override // com.huawei.pluginsocialshare.cloud.bean.DownloadCallback
        public void onFailure(int i, String str) {
            Activity activity = this.f8520a.get();
            if (activity == null || activity.isFinishing() || activity.isDestroyed()) {
                LogUtil.h("share_EditShareAdapter", "WeakDownloadCallback  onFailure activity is null, finish or destroyed");
            } else if (this.c != null) {
                Message obtain = Message.obtain();
                obtain.what = 2;
                this.c.sendMessage(obtain);
            }
        }
    }
}
