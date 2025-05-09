package com.huawei.ui.device.adapter;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.LruCache;
import android.view.View;
import android.widget.ImageView;
import com.google.android.gms.wearable.PutDataRequest;
import com.huawei.health.R;
import com.huawei.health.devicemgr.api.mainprocess.DownloadManagerApi;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.adapter.BaseRecyclerAdapter;
import com.huawei.ui.commonui.adapter.RecyclerHolder;
import defpackage.nrf;
import defpackage.nyq;
import defpackage.obb;
import health.compact.a.Services;
import java.util.List;

/* loaded from: classes6.dex */
public class RightListAdapter extends BaseRecyclerAdapter<nyq> {

    /* renamed from: a, reason: collision with root package name */
    private static final int[] f9279a = {R.layout.item_all_device_right_band, R.layout.item_all_device_right_info, R.layout.item_all_device_right_info};
    private String b;
    private int d;
    private LruCache<String, Bitmap> e;

    public RightListAdapter(List<nyq> list) {
        super(list, f9279a);
        this.d = BaseApplication.getContext().getResources().getColor(R.color._2131296651_res_0x7f09018b);
        LogUtil.a("RightListAdapter", "RightListAdapter init");
        this.e = new LruCache<String, Bitmap>(((int) (Runtime.getRuntime().maxMemory() / 1024)) / 8) { // from class: com.huawei.ui.device.adapter.RightListAdapter.3
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.util.LruCache
            /* renamed from: cTn_, reason: merged with bridge method [inline-methods] */
            public int sizeOf(String str, Bitmap bitmap) {
                int width = (bitmap.getWidth() * bitmap.getHeight()) / 1024;
                if (width == 0) {
                    return 1;
                }
                return width;
            }
        };
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return super.getItemId(i);
    }

    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter, androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        nyq nyqVar = get(i);
        if (nyqVar == null) {
            return 1;
        }
        int t = nyqVar.t();
        if (t == 1 || t == 2) {
            return 0;
        }
        return t == 3 ? 1 : 2;
    }

    @Override // com.huawei.ui.commonui.adapter.BaseRecyclerAdapter
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public void convert(RecyclerHolder recyclerHolder, int i, nyq nyqVar) {
        LogUtil.a("RightListAdapter", "start convert position: ", Integer.valueOf(i));
        if (nyqVar != null) {
            if (getItemViewType(i) == 0) {
                LogUtil.a("RightListAdapter", "kind: ", nyqVar.e());
                recyclerHolder.b(R.id.right_kind_name, nyqVar.e());
            } else if (getItemViewType(i) == 1) {
                b(recyclerHolder, i, nyqVar);
            } else {
                b(recyclerHolder, nyqVar, i);
            }
        }
    }

    private void b(RecyclerHolder recyclerHolder, int i, nyq nyqVar) {
        LogUtil.a("RightListAdapter", "name: ", nyqVar.h(), " description: ", nyqVar.c(), " icon_path :", nyqVar.i());
        recyclerHolder.b(R.id.select_device_content, nyqVar.h());
        recyclerHolder.b(R.id.select_device_summary, nyqVar.c());
        e(recyclerHolder, i);
        d(recyclerHolder, nyqVar);
    }

    private void e(RecyclerHolder recyclerHolder, int i) {
        int i2 = i + 1;
        if (i2 >= getItemCount()) {
            recyclerHolder.a(R.id.select_device_summary_line, 4);
            recyclerHolder.a(R.id.band_margin, 8);
        } else if (getItemViewType(i2) == 0) {
            recyclerHolder.a(R.id.select_device_summary_line, 4);
            recyclerHolder.a(R.id.band_margin, 0);
        } else {
            recyclerHolder.a(R.id.select_device_summary_line, 0);
        }
    }

    private void b(RecyclerHolder recyclerHolder, nyq nyqVar, int i) {
        if (!TextUtils.isEmpty(this.b)) {
            recyclerHolder.c(R.id.select_device_content, obb.cTM_(this.d, nyqVar.h(), this.b));
            recyclerHolder.c(R.id.select_device_summary, obb.cTM_(this.d, nyqVar.c(), this.b));
        } else {
            recyclerHolder.b(R.id.select_device_content, nyqVar.h());
            recyclerHolder.b(R.id.select_device_summary, nyqVar.c());
        }
        e(recyclerHolder, i);
        d(recyclerHolder, nyqVar);
    }

    private void d(RecyclerHolder recyclerHolder, nyq nyqVar) {
        Resources resources = recyclerHolder.itemView.getResources();
        View cwK_ = recyclerHolder.cwK_(R.id.select_device_icon);
        if (cwK_ == null || resources == null || !(cwK_ instanceof ImageView)) {
            return;
        }
        ImageView imageView = (ImageView) cwK_;
        if (nyqVar.d() == 1) {
            if (nyqVar.f().startsWith(PutDataRequest.WEAR_URI_SCHEME)) {
                Bitmap cJK_ = nrf.cJK_(BitmapFactory.decodeResource(resources, nyqVar.l()), imageView);
                int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen._2131362589_res_0x7f0a031d);
                imageView.setPadding(0, dimensionPixelOffset, 0, dimensionPixelOffset);
                recyclerHolder.cwM_(R.id.select_device_icon, cJK_);
                return;
            }
            imageView.setPadding(0, 0, 0, 0);
            recyclerHolder.d(R.id.select_device_icon, nyqVar.l());
        }
        imageView.setPadding(0, 0, 0, 0);
        if (nyqVar.i() != null) {
            if (this.e.get(nyqVar.i()) != null) {
                LogUtil.a("RightListAdapter", "handleDeviceItem has cache");
                recyclerHolder.cwM_(R.id.select_device_icon, this.e.get(nyqVar.i()));
                return;
            }
            Bitmap loadImageByImagePath = ((DownloadManagerApi) Services.c("DownloadDeviceResource", DownloadManagerApi.class)).loadImageByImagePath(nyqVar.i());
            if (loadImageByImagePath != null) {
                recyclerHolder.cwM_(R.id.select_device_icon, loadImageByImagePath);
                this.e.put(nyqVar.i(), loadImageByImagePath);
                LogUtil.a("RightListAdapter", "handleDeviceItem add cache");
            }
        }
    }

    public void a(String str) {
        this.b = str;
    }
}
