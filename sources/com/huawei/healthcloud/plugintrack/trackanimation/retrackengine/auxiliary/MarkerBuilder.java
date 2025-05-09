package com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.auxiliary;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import com.huawei.health.R;
import com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.InterfaceUpdateReTrack;
import com.huawei.hwlogsmodel.LogUtil;
import defpackage.hag;
import defpackage.hpt;
import health.compact.a.LogAnonymous;
import health.compact.a.UnitUtil;

/* loaded from: classes4.dex */
public class MarkerBuilder {

    /* renamed from: a, reason: collision with root package name */
    private d f3609a;
    private c b;
    private b c;
    private e d;
    private Context e;
    private int f;

    public enum KeyExerciseInfoType {
        HeartRate_Marker,
        Speed_Marker,
        Altitude_Marker,
        Base_Marker
    }

    public enum KeyInfoType {
        START_Marker,
        END_Marker,
        ADVANCE_Marker
    }

    public MarkerBuilder(Context context) {
        AnonymousClass2 anonymousClass2 = null;
        this.e = null;
        this.f = 17;
        this.b = new c(this, anonymousClass2);
        this.f3609a = new d(this, anonymousClass2);
        this.d = new e(this, anonymousClass2);
        this.c = new b(this, anonymousClass2);
        this.e = context;
        LogUtil.a("Track_MarkerBuilder", "MarkerBuilder: constructor has context");
    }

    public MarkerBuilder() {
        AnonymousClass2 anonymousClass2 = null;
        this.e = null;
        this.f = 17;
        this.b = new c(this, anonymousClass2);
        this.f3609a = new d(this, anonymousClass2);
        this.d = new e(this, anonymousClass2);
        this.c = new b(this, anonymousClass2);
        LogUtil.a("Track_MarkerBuilder", "MarkerBuilder: constructor has not context");
    }

    public Bitmap aZb_(double d2) {
        return this.b.aZs_(d2);
    }

    public String c(double d2) {
        return this.b.c(d2);
    }

    public Bitmap aZc_() {
        return this.c.aZu_(InterfaceUpdateReTrack.MarkerType.Image_type, false);
    }

    public String a() {
        return hpt.e("marker_photo.gltf");
    }

    public Bitmap aYW_() {
        return this.c.aZu_(InterfaceUpdateReTrack.MarkerType.Image_type, true);
    }

    public String c() {
        return hpt.e("marker_selected_photo.gltf");
    }

    public Bitmap aZe_() {
        return this.c.aZu_(InterfaceUpdateReTrack.MarkerType.Text_type, false);
    }

    public String d() {
        return hpt.e("marker_text.gltf");
    }

    public Bitmap aYX_() {
        return this.c.aZu_(InterfaceUpdateReTrack.MarkerType.Text_type, true);
    }

    public String e() {
        return hpt.e("marker_selected_text.gltf");
    }

    public Bitmap aZf_() {
        return this.c.aZu_(InterfaceUpdateReTrack.MarkerType.Video_type, false);
    }

    public Bitmap aYY_() {
        return this.c.aZu_(InterfaceUpdateReTrack.MarkerType.Video_type, true);
    }

    public String b() {
        return hpt.e("marker_video.gltf");
    }

    public String j() {
        return hpt.e("marker_selected_video.gltf");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int c(float f) {
        return hag.a(f);
    }

    public Bitmap aYZ_(KeyExerciseInfoType keyExerciseInfoType, int i) {
        this.f = i;
        int i2 = AnonymousClass2.f3610a[keyExerciseInfoType.ordinal()];
        if (i2 == 1) {
            return this.d.aZm_();
        }
        if (i2 == 2) {
            return this.d.aZg_();
        }
        if (i2 == 3) {
            return this.d.aZi_();
        }
        if (i2 != 4) {
            return null;
        }
        return this.d.aZh_();
    }

    public String c(KeyExerciseInfoType keyExerciseInfoType, int i) {
        this.f = i;
        int i2 = AnonymousClass2.f3610a[keyExerciseInfoType.ordinal()];
        if (i2 == 1) {
            return this.d.o();
        }
        if (i2 == 2) {
            return this.d.c();
        }
        if (i2 != 3) {
            return i2 != 4 ? "" : this.d.d();
        }
        return this.d.a();
    }

    public Bitmap aZd_(KeyExerciseInfoType keyExerciseInfoType, int i) {
        this.f = i;
        int i2 = AnonymousClass2.f3610a[keyExerciseInfoType.ordinal()];
        if (i2 == 1) {
            return this.d.aZl_();
        }
        if (i2 == 2) {
            return this.d.aZj_();
        }
        if (i2 != 3) {
            return null;
        }
        return this.d.aZk_();
    }

    public String a(KeyExerciseInfoType keyExerciseInfoType, int i) {
        this.f = i;
        int i2 = AnonymousClass2.f3610a[keyExerciseInfoType.ordinal()];
        if (i2 == 1) {
            return this.d.n();
        }
        if (i2 != 2) {
            return i2 != 3 ? "" : this.d.j();
        }
        return this.d.h();
    }

    public Bitmap aZa_(KeyInfoType keyInfoType, int i, boolean z) {
        int i2 = AnonymousClass2.e[keyInfoType.ordinal()];
        if (i2 == 1) {
            return this.f3609a.aZp_(i);
        }
        if (i2 == 2) {
            return this.f3609a.aZn_(z);
        }
        if (i2 != 3) {
            return null;
        }
        return this.f3609a.aZo_();
    }

    public String d(KeyInfoType keyInfoType, int i, boolean z) {
        int i2 = AnonymousClass2.e[keyInfoType.ordinal()];
        if (i2 != 1) {
            return i2 != 3 ? "" : this.f3609a.e();
        }
        return this.f3609a.e(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Bitmap aYV_(Bitmap bitmap, float f, float f2) {
        int c2 = c(f);
        int c3 = c(f2);
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Matrix matrix = new Matrix();
        matrix.postScale(c2 / width, c3 / height);
        return Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
    }

    class c {

        /* renamed from: a, reason: collision with root package name */
        private int f3612a;
        private int c;

        private c() {
            this.f3612a = 90;
            this.c = 105;
        }

        /* synthetic */ c(MarkerBuilder markerBuilder, AnonymousClass2 anonymousClass2) {
            this();
        }

        public Bitmap aZs_(double d) {
            LogUtil.a("Track_MarkerBuilder", "Go into createKmMarker");
            if (MarkerBuilder.this.e != null) {
                this.f3612a = MarkerBuilder.this.c(23.0f);
                this.c = MarkerBuilder.this.c(26.0f);
                String valueOf = String.valueOf((int) Math.round(b(d) / 1000.0d));
                TextPaint aZr_ = aZr_(MarkerBuilder.this.e.getResources(), valueOf.length());
                aZr_.getTextBounds(valueOf, 0, valueOf.length(), new Rect());
                Bitmap aZq_ = aZq_(MarkerBuilder.this.e.getResources());
                new Canvas(aZq_).drawText(valueOf, aZq_.getWidth() / 2.0f, (((aZq_.getHeight() - aZr_.getFontMetrics().top) - aZr_.getFontMetrics().bottom) / 2.0f) - MarkerBuilder.this.c(2.0f), aZr_);
                LogUtil.a("Track_MarkerBuilder", "Go out createKmMarker");
                return aZq_;
            }
            LogUtil.h("Track_MarkerBuilder", "Context is null");
            return null;
        }

        private double b(double d) {
            return UnitUtil.h() ? UnitUtil.e(d / 1000.0d, 3) * 1000.0d : d;
        }

        public String c(double d) {
            return String.valueOf((int) Math.round(b(d) / 1000.0d));
        }

        private TextPaint aZr_(Resources resources, int i) {
            TextPaint textPaint = new TextPaint();
            textPaint.setTextAlign(Paint.Align.CENTER);
            if (resources != null) {
                textPaint.setColor(resources.getColor(R.color._2131296937_res_0x7f0902a9));
                if (i == 1) {
                    textPaint.setTextSize(resources.getDimensionPixelSize(R.dimen._2131363644_res_0x7f0a073c));
                } else if (i == 2) {
                    textPaint.setTextSize(resources.getDimensionPixelSize(R.dimen._2131363644_res_0x7f0a073c));
                } else {
                    textPaint.setTextSize(resources.getDimensionPixelSize(R.dimen._2131363675_res_0x7f0a075b));
                }
            }
            return textPaint;
        }

        private Bitmap aZq_(Resources resources) {
            if (resources != null) {
                Drawable drawable = resources.getDrawable(R.drawable._2131428711_res_0x7f0b0567);
                Bitmap createBitmap = Bitmap.createBitmap(this.f3612a, this.c, Bitmap.Config.ARGB_8888);
                if (drawable == null) {
                    return createBitmap;
                }
                drawable.setBounds(0, 0, this.f3612a, this.c);
                Canvas canvas = new Canvas(createBitmap);
                drawable.draw(canvas);
                canvas.drawBitmap(createBitmap, 0.0f, 0.0f, new Paint());
                return createBitmap;
            }
            return Bitmap.createBitmap(this.f3612a, this.c, Bitmap.Config.ARGB_8888);
        }
    }

    class d {

        /* renamed from: a, reason: collision with root package name */
        private Bitmap f3613a;
        private String c;
        private String d;
        private Bitmap e;
        private Bitmap f;

        private int d(int i) {
            return i != 222 ? i != 280 ? i != 282 ? i != 257 ? i != 259 ? i != 260 ? R.drawable._2131431243_res_0x7f0b0f4b : R.drawable._2131431240_res_0x7f0b0f48 : R.drawable._2131431239_res_0x7f0b0f47 : R.drawable._2131431244_res_0x7f0b0f4c : R.drawable._2131431242_res_0x7f0b0f4a : R.drawable._2131431241_res_0x7f0b0f49 : R.drawable._2131428715_res_0x7f0b056b;
        }

        private d() {
            this.f = null;
            this.e = null;
            this.f3613a = null;
            this.c = null;
            this.d = null;
        }

        /* synthetic */ d(MarkerBuilder markerBuilder, AnonymousClass2 anonymousClass2) {
            this();
        }

        public Bitmap aZp_(int i) {
            if (this.f == null && MarkerBuilder.this.e != null) {
                MarkerBuilder markerBuilder = MarkerBuilder.this;
                this.f = markerBuilder.aYV_(BitmapFactory.decodeResource(markerBuilder.e.getResources(), d(i)), 24.0f, 28.5f);
            }
            return this.f;
        }

        public String e(int i) {
            if (this.c == null && MarkerBuilder.this.e != null) {
                this.c = b(i);
            }
            return this.c;
        }

        public Bitmap aZn_(boolean z) {
            if (MarkerBuilder.this.e != null) {
                MarkerBuilder markerBuilder = MarkerBuilder.this;
                this.e = markerBuilder.aYV_(BitmapFactory.decodeResource(markerBuilder.e.getResources(), z ? R.drawable._2131431213_res_0x7f0b0f2d : R.drawable._2131431212_res_0x7f0b0f2c), 18.0f, 18.0f);
            }
            return this.e;
        }

        public Bitmap aZo_() {
            if (this.f3613a == null && MarkerBuilder.this.e != null) {
                MarkerBuilder markerBuilder = MarkerBuilder.this;
                this.f3613a = markerBuilder.aYV_(BitmapFactory.decodeResource(markerBuilder.e.getResources(), R.drawable._2131431238_res_0x7f0b0f46), 24.0f, 28.5f);
            }
            return this.f3613a;
        }

        public String e() {
            if (this.d == null && MarkerBuilder.this.e != null) {
                this.d = hpt.e("marker_end.gltf");
            }
            return this.d;
        }

        private String b(int i) {
            if (i == 280) {
                return hpt.e("marker_trail_run.gltf");
            }
            switch (i) {
                case 257:
                    return hpt.e("marker_start_walk.gltf");
                case 258:
                    return hpt.e("marker_start_run.gltf");
                case 259:
                    return hpt.e("marker_start_bike.gltf");
                case 260:
                    return hpt.e("marker_start_climb_hill.gltf");
                default:
                    return hpt.e("marker_start_hiking.gltf");
            }
        }
    }

    class e {
        private String b;
        private String c;
        private String d;
        private String e;
        private Bitmap f;
        private String g;
        private String h;
        private Bitmap i;
        private String j;
        private Bitmap k;
        private Bitmap l;
        private Bitmap m;
        private Bitmap n;
        private Bitmap o;

        private e() {
            this.f = null;
            this.o = null;
            this.l = null;
            this.i = null;
            this.m = null;
            this.n = null;
            this.k = null;
            this.b = null;
            this.e = null;
            this.h = null;
            this.c = null;
            this.d = null;
            this.j = null;
            this.g = null;
        }

        /* synthetic */ e(MarkerBuilder markerBuilder, AnonymousClass2 anonymousClass2) {
            this();
        }

        public Bitmap aZg_() {
            if (this.f == null && MarkerBuilder.this.e != null) {
                MarkerBuilder markerBuilder = MarkerBuilder.this;
                this.f = markerBuilder.aYV_(BitmapFactory.decodeResource(markerBuilder.e.getResources(), R.drawable._2131431222_res_0x7f0b0f36), 31.5f, 31.5f);
            }
            return this.f;
        }

        public String c() {
            if (this.b == null && MarkerBuilder.this.e != null) {
                this.b = hpt.e("marker_altitude.gltf");
            }
            return this.b;
        }

        public Bitmap aZi_() {
            if (this.o == null && MarkerBuilder.this.e != null) {
                MarkerBuilder markerBuilder = MarkerBuilder.this;
                this.o = markerBuilder.aYV_(BitmapFactory.decodeResource(markerBuilder.e.getResources(), R.drawable.retrack_map_marker_heartrate), 31.5f, 31.5f);
            }
            return this.o;
        }

        public String a() {
            if (this.e == null && MarkerBuilder.this.e != null) {
                this.e = hpt.e("marker_heart_rate.gltf");
            }
            return this.e;
        }

        public Bitmap aZm_() {
            if (this.l == null && MarkerBuilder.this.e != null) {
                switch (MarkerBuilder.this.f) {
                    case 16:
                        MarkerBuilder markerBuilder = MarkerBuilder.this;
                        this.l = markerBuilder.aYV_(BitmapFactory.decodeResource(markerBuilder.e.getResources(), R.drawable._2131431225_res_0x7f0b0f39), 31.5f, 31.5f);
                        break;
                    case 17:
                        MarkerBuilder markerBuilder2 = MarkerBuilder.this;
                        this.l = markerBuilder2.aYV_(BitmapFactory.decodeResource(markerBuilder2.e.getResources(), R.drawable._2131431231_res_0x7f0b0f3f), 31.5f, 31.5f);
                        break;
                    case 18:
                        MarkerBuilder markerBuilder3 = MarkerBuilder.this;
                        this.l = markerBuilder3.aYV_(BitmapFactory.decodeResource(markerBuilder3.e.getResources(), R.drawable._2131431232_res_0x7f0b0f40), 31.5f, 31.5f);
                        break;
                    default:
                        MarkerBuilder markerBuilder4 = MarkerBuilder.this;
                        this.l = markerBuilder4.aYV_(BitmapFactory.decodeResource(markerBuilder4.e.getResources(), R.drawable._2131431231_res_0x7f0b0f3f), 31.5f, 31.5f);
                        break;
                }
            }
            return this.l;
        }

        public String o() {
            if (this.h == null && MarkerBuilder.this.e != null) {
                int i = MarkerBuilder.this.f;
                if (i == 16) {
                    this.h = hpt.e("marker_pace.gltf");
                } else if (i == 18) {
                    this.h = hpt.e("marker_step_rate.gltf");
                } else {
                    this.h = hpt.e("marker_speed.gltf");
                }
            }
            return this.h;
        }

        public Bitmap aZh_() {
            if (this.i == null && MarkerBuilder.this.e != null) {
                MarkerBuilder markerBuilder = MarkerBuilder.this;
                this.i = markerBuilder.aYV_(BitmapFactory.decodeResource(markerBuilder.e.getResources(), R.drawable._2131431223_res_0x7f0b0f37), 44.0f, 26.0f);
            }
            return this.i;
        }

        public String d() {
            if (this.c == null && MarkerBuilder.this.e != null) {
                this.c = hpt.e("marker_trail_run.gltf");
            }
            return this.c;
        }

        public Bitmap aZj_() {
            if (this.m == null && MarkerBuilder.this.e != null) {
                MarkerBuilder markerBuilder = MarkerBuilder.this;
                this.m = markerBuilder.aYV_(BitmapFactory.decodeResource(markerBuilder.e.getResources(), R.drawable._2131431226_res_0x7f0b0f3a), 31.5f, 31.5f);
            }
            return this.m;
        }

        public String h() {
            if (this.d == null && MarkerBuilder.this.e != null) {
                this.d = hpt.e("marker_altitude.gltf");
            }
            return this.d;
        }

        public Bitmap aZk_() {
            if (this.n == null && MarkerBuilder.this.e != null) {
                MarkerBuilder markerBuilder = MarkerBuilder.this;
                this.n = markerBuilder.aYV_(BitmapFactory.decodeResource(markerBuilder.e.getResources(), R.drawable.retrack_map_marker_slope_heartrate), 31.5f, 31.5f);
            }
            return this.n;
        }

        public String j() {
            if (this.j == null && MarkerBuilder.this.e != null) {
                this.j = hpt.e("marker_heart_rate.gltf");
            }
            return this.j;
        }

        public Bitmap aZl_() {
            if (this.k == null && MarkerBuilder.this.e != null) {
                switch (MarkerBuilder.this.f) {
                    case 16:
                        MarkerBuilder markerBuilder = MarkerBuilder.this;
                        this.k = markerBuilder.aYV_(BitmapFactory.decodeResource(markerBuilder.e.getResources(), R.drawable._2131431228_res_0x7f0b0f3c), 31.5f, 31.5f);
                        break;
                    case 17:
                        MarkerBuilder markerBuilder2 = MarkerBuilder.this;
                        this.k = markerBuilder2.aYV_(BitmapFactory.decodeResource(markerBuilder2.e.getResources(), R.drawable._2131431229_res_0x7f0b0f3d), 31.5f, 31.5f);
                        break;
                    case 18:
                        MarkerBuilder markerBuilder3 = MarkerBuilder.this;
                        this.k = markerBuilder3.aYV_(BitmapFactory.decodeResource(markerBuilder3.e.getResources(), R.drawable._2131431230_res_0x7f0b0f3e), 31.5f, 31.5f);
                        break;
                    default:
                        MarkerBuilder markerBuilder4 = MarkerBuilder.this;
                        this.k = markerBuilder4.aYV_(BitmapFactory.decodeResource(markerBuilder4.e.getResources(), R.drawable._2131431229_res_0x7f0b0f3d), 31.5f, 31.5f);
                        break;
                }
            }
            return this.k;
        }

        public String n() {
            if (this.g == null && MarkerBuilder.this.e != null) {
                int i = MarkerBuilder.this.f;
                if (i == 16) {
                    this.g = hpt.e("marker_pace.gltf");
                } else if (i == 18) {
                    this.g = hpt.e("marker_step_rate.gltf");
                } else {
                    this.g = hpt.e("marker_speed.gltf");
                }
            }
            return this.g;
        }
    }

    class b {

        /* renamed from: a, reason: collision with root package name */
        private int f3611a;
        private int e;

        private b() {
            this.e = MarkerBuilder.this.c(30.0f);
            this.f3611a = MarkerBuilder.this.c(30.0f);
        }

        /* synthetic */ b(MarkerBuilder markerBuilder, AnonymousClass2 anonymousClass2) {
            this();
        }

        public Bitmap aZu_(InterfaceUpdateReTrack.MarkerType markerType, boolean z) {
            if (MarkerBuilder.this.e != null) {
                if (MarkerBuilder.this.e.getResources() != null) {
                    Drawable aZt_ = aZt_(markerType, z);
                    Bitmap createBitmap = Bitmap.createBitmap(this.e, this.f3611a, Bitmap.Config.ARGB_8888);
                    if (aZt_ == null) {
                        return createBitmap;
                    }
                    aZt_.setBounds(0, 0, this.e, this.f3611a);
                    Canvas canvas = new Canvas(createBitmap);
                    aZt_.draw(canvas);
                    canvas.drawBitmap(createBitmap, 0.0f, 0.0f, new Paint());
                    return createBitmap;
                }
                return Bitmap.createBitmap(this.e, this.f3611a, Bitmap.Config.ARGB_8888);
            }
            LogUtil.h("Track_MarkerBuilder", "createTextMarkerBackground: context is null");
            return Bitmap.createBitmap(this.e, this.f3611a, Bitmap.Config.ARGB_8888);
        }

        private Drawable aZt_(InterfaceUpdateReTrack.MarkerType markerType, boolean z) {
            int i;
            if (MarkerBuilder.this.e != null) {
                Resources resources = MarkerBuilder.this.e.getResources();
                if (z) {
                    int i2 = AnonymousClass2.c[markerType.ordinal()];
                    i = i2 != 1 ? i2 != 2 ? R.drawable._2131431970_res_0x7f0b1222 : R.drawable._2131431972_res_0x7f0b1224 : R.drawable._2131431965_res_0x7f0b121d;
                } else {
                    int i3 = AnonymousClass2.c[markerType.ordinal()];
                    i = i3 != 1 ? i3 != 2 ? R.drawable._2131431971_res_0x7f0b1223 : R.drawable._2131431973_res_0x7f0b1225 : R.drawable._2131431966_res_0x7f0b121e;
                }
                if (i == -1) {
                    return null;
                }
                try {
                    return resources.getDrawable(i, null);
                } catch (Resources.NotFoundException e) {
                    LogUtil.h("Track_MarkerBuilder", "getDrawableByType: ", LogAnonymous.b((Throwable) e));
                    return null;
                }
            }
            LogUtil.h("Track_MarkerBuilder", "getDrawableByType: context is null");
            return null;
        }
    }

    /* renamed from: com.huawei.healthcloud.plugintrack.trackanimation.retrackengine.auxiliary.MarkerBuilder$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {

        /* renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f3610a;
        static final /* synthetic */ int[] c;
        static final /* synthetic */ int[] e;

        static {
            int[] iArr = new int[InterfaceUpdateReTrack.MarkerType.values().length];
            c = iArr;
            try {
                iArr[InterfaceUpdateReTrack.MarkerType.Image_type.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                c[InterfaceUpdateReTrack.MarkerType.Video_type.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            int[] iArr2 = new int[KeyInfoType.values().length];
            e = iArr2;
            try {
                iArr2[KeyInfoType.START_Marker.ordinal()] = 1;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                e[KeyInfoType.ADVANCE_Marker.ordinal()] = 2;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                e[KeyInfoType.END_Marker.ordinal()] = 3;
            } catch (NoSuchFieldError unused5) {
            }
            int[] iArr3 = new int[KeyExerciseInfoType.values().length];
            f3610a = iArr3;
            try {
                iArr3[KeyExerciseInfoType.Speed_Marker.ordinal()] = 1;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f3610a[KeyExerciseInfoType.Altitude_Marker.ordinal()] = 2;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                f3610a[KeyExerciseInfoType.HeartRate_Marker.ordinal()] = 3;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                f3610a[KeyExerciseInfoType.Base_Marker.ordinal()] = 4;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }
}
