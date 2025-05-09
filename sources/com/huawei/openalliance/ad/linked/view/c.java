package com.huawei.openalliance.ad.linked.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.huawei.health.R;
import com.huawei.hianalytics.visual.autocollect.instrument.ViewClickInstrumentation;
import com.huawei.openalliance.ad.activity.PPSActivity;
import com.huawei.openalliance.ad.gs;
import com.huawei.openalliance.ad.gt;
import com.huawei.openalliance.ad.hd;
import com.huawei.openalliance.ad.he;
import com.huawei.openalliance.ad.hf;
import com.huawei.openalliance.ad.ho;
import com.huawei.openalliance.ad.media.MediaPlayerAgent;
import com.huawei.openalliance.ad.media.listener.MediaErrorListener;
import com.huawei.openalliance.ad.views.PPSWebView;
import com.huawei.openalliance.ad.views.VideoView;
import com.huawei.openalliance.ad.views.linkscroll.LinkScrollView;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes9.dex */
public class c extends RelativeLayout implements he {

    /* renamed from: a, reason: collision with root package name */
    private gs f7178a;
    private hf b;
    private List<View> c;
    private LinkedAppDetailView d;
    private LinkScrollView e;
    private com.huawei.openalliance.ad.views.d f;
    private int g;
    private final View.OnClickListener h;
    private a i;
    private final MediaErrorListener j;

    public interface a {
        void a();

        void a(MediaPlayerAgent mediaPlayerAgent, int i, int i2, int i3);
    }

    public void setPlayModeChangeListener(PPSActivity.b bVar) {
        hf hfVar = this.b;
        if (hfVar instanceof b) {
            ((b) hfVar).setPlayModeChangeListener(bVar);
        }
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
    }

    public gs getNativeAd() {
        return this.f7178a;
    }

    public hf getLinkedNativeVideoView() {
        return this.b;
    }

    public void d() {
        LinkScrollView linkScrollView = this.e;
        if (linkScrollView != null) {
            linkScrollView.a();
            this.e = null;
        }
    }

    public void c() {
        hf hfVar = this.b;
        if (hfVar != null) {
            ((b) hfVar).e();
        }
    }

    public void b() {
        hf hfVar = this.b;
        if (hfVar instanceof b) {
            ((b) hfVar).f();
        }
    }

    public void a(PPSWebView pPSWebView) {
        ho.a("LinkedLandView", "registerPPSWebView");
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.linked_pps_web_view);
        com.huawei.openalliance.ad.views.d customEmuiActionBar = pPSWebView.getCustomEmuiActionBar();
        this.f = customEmuiActionBar;
        if (customEmuiActionBar != null) {
            try {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
                layoutParams.addRule(10, -1);
                layoutParams.addRule(2, R.id.linked_native_view);
                addView(this.f, layoutParams);
                this.f.post(new Runnable() { // from class: com.huawei.openalliance.ad.linked.view.c.3
                    @Override // java.lang.Runnable
                    public void run() {
                        c cVar = c.this;
                        cVar.g = cVar.f.getHeight();
                        if (c.this.g > 0) {
                            c.this.e.setPaddingRelative(0, c.this.g, 0, 0);
                            c.this.e.setBarHeight(c.this.g);
                        }
                    }
                });
            } catch (Throwable th) {
                ho.b("LinkedLandView", "setCustomeActionBar error: %s", th.getClass().getSimpleName());
            }
        }
        frameLayout.addView(pPSWebView);
        this.e.setWebView(pPSWebView.findViewById(R.id.hiad_webview));
    }

    public void a(gt gtVar) {
        ho.a("LinkedLandView", "registerLinkedAd");
        if (gtVar instanceof gs) {
            this.f7178a = (gs) gtVar;
            String e = gtVar.e();
            hf hfVar = this.b;
            if (hfVar != null) {
                hfVar.b(e);
            }
            LinkedAppDetailView linkedAppDetailView = this.d;
            if (linkedAppDetailView != null) {
                linkedAppDetailView.a(e);
            }
        }
        a(getContext());
    }

    public void a(Context context) {
        this.b = new b(context);
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.linked_native_view);
        a(context, viewGroup);
        hf hfVar = this.b;
        if (hfVar instanceof b) {
            viewGroup.addView((b) hfVar);
            ((b) this.b).setVideoReleaseListener(this.i);
            this.b.setLinkedLandView(this);
            this.b.setLinkedNativeAd(this.f7178a);
            setNativeVideoViewClickable(this.b);
            this.d = this.b.h();
        }
        g();
    }

    public void a() {
        e();
    }

    private void setviewGroupclickListener(ViewGroup viewGroup) {
        ho.b("LinkedLandView", "setviewGroupclickListener");
        if (viewGroup == null) {
            return;
        }
        viewGroup.setOnClickListener(new View.OnClickListener() { // from class: com.huawei.openalliance.ad.linked.view.c.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                hd linkedVideoControlBridge = c.this.b.getLinkedVideoControlBridge();
                if (linkedVideoControlBridge != null) {
                    linkedVideoControlBridge.y();
                    linkedVideoControlBridge.w();
                }
                ViewClickInstrumentation.clickOnView(view);
            }
        });
    }

    private void setNativeVideoViewClickable(hf hfVar) {
        if (hfVar instanceof b) {
            ArrayList arrayList = new ArrayList();
            arrayList.add((b) hfVar);
            a(arrayList);
        }
    }

    private void g() {
        ArrayList arrayList = new ArrayList();
        hf hfVar = this.b;
        if (hfVar instanceof b) {
            arrayList.add((b) hfVar);
        }
        this.c = arrayList;
        a(arrayList);
    }

    private void f() {
        List<View> list = this.c;
        if (list == null || list.isEmpty()) {
            return;
        }
        for (View view : this.c) {
            if (view != null) {
                view.setOnClickListener(null);
            }
        }
        setOnClickListener(null);
    }

    private void e() {
        hf hfVar = this.b;
        if (hfVar != null) {
            hfVar.g();
        }
        f();
    }

    private void b(Context context) {
        LayoutInflater.from(context).inflate(R.layout.hiad_linked_land_view, this);
        this.e = (LinkScrollView) findViewById(R.id.hiad_landpage_scroll_view);
    }

    private void a(List<View> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (View view : list) {
            if (view instanceof b) {
                ((b) view).setCoverClickListener(this.h);
            } else if (view != null) {
                view.setOnClickListener(this.h);
            }
        }
    }

    private void a(Context context, ViewGroup viewGroup) {
        gs gsVar = this.f7178a;
        if (gsVar == null || gsVar.f() == null || this.f7178a.f().getVideoRatio() == null) {
            return;
        }
        Float videoRatio = this.f7178a.f().getVideoRatio();
        if (videoRatio.floatValue() >= 1.0f) {
            return;
        }
        VideoView videoView = this.b.getVideoView();
        float b = com.huawei.openalliance.ad.utils.d.b(context) / 1.7777778f;
        float floatValue = videoRatio.floatValue();
        ViewGroup.LayoutParams layoutParams = videoView.getLayoutParams();
        layoutParams.width = (int) (floatValue * b);
        layoutParams.height = (int) b;
        videoView.setLayoutParams(layoutParams);
        this.b.setVideoView(videoView);
        setviewGroupclickListener(viewGroup);
    }

    public c(Context context) {
        super(context);
        this.h = new View.OnClickListener() { // from class: com.huawei.openalliance.ad.linked.view.c.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                ViewClickInstrumentation.clickOnView(view);
            }
        };
        this.i = new a() { // from class: com.huawei.openalliance.ad.linked.view.c.4
            @Override // com.huawei.openalliance.ad.linked.view.c.a
            public void a(MediaPlayerAgent mediaPlayerAgent, int i, int i2, int i3) {
                ho.c("LinkedLandView", "onError, code: %s", Integer.valueOf(i2));
                if (c.this.e != null) {
                    c.this.e.scrollTo(0, 0);
                }
                if (c.this.b != null) {
                    c.this.b.i();
                }
            }

            @Override // com.huawei.openalliance.ad.linked.view.c.a
            public void a() {
                ho.a("LinkedLandView", "onVideoComplete");
                if (c.this.f7178a != null) {
                    if ((c.this.f7178a.j() == 1 || c.this.f7178a.j() == 18) && c.this.b != null) {
                        c.this.b.i();
                    }
                }
            }
        };
        this.j = new MediaErrorListener() { // from class: com.huawei.openalliance.ad.linked.view.c.5
            @Override // com.huawei.openalliance.ad.media.listener.MediaErrorListener
            public void onError(MediaPlayerAgent mediaPlayerAgent, int i, int i2, int i3) {
                if (c.this.b != null) {
                    c.this.b.i();
                }
            }
        };
        b(context);
    }
}
