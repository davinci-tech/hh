package defpackage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.huawei.hwcommonmodel.application.BaseApplication;
import com.huawei.hwlogsmodel.LogUtil;
import com.huawei.ui.commonui.viewpager.HealthPagerAdapter;
import com.huawei.ui.commonui.viewpager.HealthViewPager;
import java.io.File;
import java.util.ArrayList;

/* loaded from: classes3.dex */
public class dgo extends HealthPagerAdapter {

    /* renamed from: a, reason: collision with root package name */
    private Context f11647a;
    private ArrayList<String> c;
    private ArrayList<Object> d;
    private LruCache<String, Bitmap> e;

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getItemPosition(Object obj) {
        return -2;
    }

    public dgo(ArrayList<String> arrayList, Context context) {
        this.c = new ArrayList<>(10);
        this.d = new ArrayList<>(10);
        this.e = new LruCache<>(1048576);
        this.c = arrayList;
        if (context == null) {
            this.f11647a = BaseApplication.getContext();
        } else {
            this.f11647a = context.getApplicationContext();
        }
    }

    public dgo(Context context, ArrayList<Object> arrayList) {
        this.c = new ArrayList<>(10);
        this.d = new ArrayList<>(10);
        this.e = new LruCache<>(1048576);
        if (arrayList == null) {
            LogUtil.h("PluginDevice_ImgPagerAdapter", "imgArray is null");
            return;
        }
        if (context == null) {
            this.f11647a = BaseApplication.getContext();
        } else {
            this.f11647a = context.getApplicationContext();
        }
        this.d = arrayList;
    }

    public void c(ArrayList<String> arrayList) {
        this.c = arrayList;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public int getCount() {
        ArrayList<String> arrayList = this.c;
        if (arrayList != null && arrayList.size() > 0) {
            return this.c.size();
        }
        ArrayList<Object> arrayList2 = this.d;
        if (arrayList2 != null) {
            return arrayList2.size();
        }
        LogUtil.a("PluginDevice_ImgPagerAdapter", "getCount else");
        return 0;
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == ((View) obj);
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        ImageView imageView = obj instanceof ImageView ? (ImageView) obj : null;
        if (imageView == null || imageView.getDrawable() == null) {
            return;
        }
        imageView.setImageDrawable(null);
        Uu_(imageView);
        if (viewGroup != null) {
            viewGroup.removeView(imageView);
        }
    }

    @Override // com.huawei.uikit.hwviewpager.widget.HwPagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        ArrayList<Object> arrayList = this.d;
        Bitmap bitmap = null;
        if (arrayList == null || this.c == null) {
            LogUtil.h("PluginDevice_ImgPagerAdapter", "mImgPaths is null or mImgArrays is null");
            return null;
        }
        if (arrayList.size() == 0 && i >= this.c.size()) {
            LogUtil.h("PluginDevice_ImgPagerAdapter", "position is Beyond mImgPaths.size()");
            return null;
        }
        if (this.c.size() == 0 && i >= this.d.size()) {
            LogUtil.h("PluginDevice_ImgPagerAdapter", "position is Beyond mImgArrays.size()");
            return null;
        }
        LogUtil.h("PluginDevice_ImgPagerAdapter", "instantiateItem default");
        ImageView imageView = new ImageView(this.f11647a);
        if (this.d.size() > 0) {
            LogUtil.a("PluginDevice_ImgPagerAdapter", "mCurrentImgId Image");
            Object obj = this.d.get(i);
            if (obj instanceof Integer) {
                imageView.setImageResource(((Integer) obj).intValue());
            }
            if (obj instanceof String) {
                imageView.setImageBitmap(dcx.TK_((String) obj));
            }
        } else if (this.e.get(this.c.get(i)) == null) {
            String str = this.c.get(i);
            bitmap = dcx.TK_(str);
            if (new File(str).exists() && bitmap != null) {
                LogUtil.a("PluginDevice_ImgPagerAdapter", "cache Image");
                this.e.put(str, bitmap);
            }
        } else {
            String str2 = this.c.get(i);
            LogUtil.a("PluginDevice_ImgPagerAdapter", "load exists Image");
            bitmap = this.e.get(str2);
        }
        if (bitmap == null) {
            LogUtil.a("PluginDevice_ImgPagerAdapter", "ImgPagerAdapter instantiateItem:bm == null");
        } else {
            imageView.setImageBitmap(bitmap);
        }
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        if (viewGroup instanceof HealthViewPager) {
            ((HealthViewPager) viewGroup).addView(imageView, 0);
        }
        return imageView;
    }

    public void Uu_(ImageView imageView) {
        Bitmap bitmap;
        if (imageView == null) {
            return;
        }
        Drawable drawable = imageView.getDrawable();
        if (!(drawable instanceof BitmapDrawable) || (bitmap = ((BitmapDrawable) drawable).getBitmap()) == null || bitmap.isRecycled()) {
            return;
        }
        bitmap.recycle();
        LogUtil.a("PluginDevice_ImgPagerAdapter", "ImgPagerAdapter bitmap is recycle");
    }
}
