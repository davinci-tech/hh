package defpackage;

import android.graphics.Bitmap;
import android.util.Log;
import com.huawei.animationkit.computationalwallpaper.generator.DynamicWallpaper;
import com.huawei.animationkit.computationalwallpaper.generator.OnWallpaperGenerateListener;
import com.huawei.animationkit.computationalwallpaper.generator.StaticWallpaper;
import com.huawei.animationkit.computationalwallpaper.utils.AsyncExecutor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes8.dex */
public class adh {
    private static final adh d = new adh();

    /* renamed from: a, reason: collision with root package name */
    private b f172a;
    private OnWallpaperGenerateListener<StaticWallpaper> c;
    private final adc e = adc.b();

    private adh() {
    }

    public static adh c() {
        return d;
    }

    public List<StaticWallpaper> fR_(Bitmap bitmap) throws abv {
        return b(this.e.fN_(bitmap));
    }

    public void d(OnWallpaperGenerateListener<StaticWallpaper> onWallpaperGenerateListener) {
        this.c = onWallpaperGenerateListener;
    }

    public void fS_(Bitmap bitmap) {
        if (this.f172a == null) {
            this.f172a = new b();
        }
        this.f172a.execute(bitmap);
    }

    public List<StaticWallpaper> b(List<DynamicWallpaper> list) throws abv {
        try {
            ArrayList arrayList = new ArrayList();
            Iterator<DynamicWallpaper> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(b(it.next()));
            }
            return arrayList;
        } catch (abv e) {
            Log.e("StaticWallpaperGenerator", "generate static wallpaper failed.", e);
            throw e;
        } catch (RuntimeException e2) {
            Log.e("StaticWallpaperGenerator", "generate static wallpaper failed.", e2);
            throw new abv(e2);
        }
    }

    private StaticWallpaper b(DynamicWallpaper dynamicWallpaper) throws abv {
        return new StaticWallpaper(dynamicWallpaper.getPreview(), dynamicWallpaper.getClock(), dynamicWallpaper.getTag());
    }

    class b extends AsyncExecutor<Bitmap, List<StaticWallpaper>> {
        private b() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.animationkit.computationalwallpaper.utils.AsyncExecutor
        /* renamed from: fT_, reason: merged with bridge method [inline-methods] */
        public List<StaticWallpaper> runInBackground(Bitmap bitmap) throws abv {
            return adh.this.fR_(bitmap);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.huawei.animationkit.computationalwallpaper.utils.AsyncExecutor
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public void finishOnUiThread(List<StaticWallpaper> list, abv abvVar) {
            adh.this.c.onWallpaperGenerate(list, abvVar);
        }
    }
}
