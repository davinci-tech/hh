package com.huawei.ucd.gles.engine.android;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import com.huawei.ucd.gles.engine.Stage;
import com.huawei.ucd.helper.gles.IGLActor;
import defpackage.nkg;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* loaded from: classes9.dex */
public class StageView extends GLSurfaceView {

    /* renamed from: a, reason: collision with root package name */
    private GLSurfaceView.EGLConfigChooser f8740a;
    protected Stage c;
    private GLSurfaceView.Renderer d;

    public StageView(Context context) {
        super(context);
        this.d = new GLSurfaceView.Renderer() { // from class: com.huawei.ucd.gles.engine.android.StageView.2
            @Override // android.opengl.GLSurfaceView.Renderer
            public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
                StageView.this.c.onSurfaceCreated();
            }

            @Override // android.opengl.GLSurfaceView.Renderer
            public void onSurfaceChanged(GL10 gl10, int i, int i2) {
                StageView.this.c.onSurfaceChanged(i, i2);
            }

            @Override // android.opengl.GLSurfaceView.Renderer
            public void onDrawFrame(GL10 gl10) {
                StageView.this.c.onDrawFrame();
            }
        };
        a(context);
    }

    public StageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.d = new GLSurfaceView.Renderer() { // from class: com.huawei.ucd.gles.engine.android.StageView.2
            @Override // android.opengl.GLSurfaceView.Renderer
            public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
                StageView.this.c.onSurfaceCreated();
            }

            @Override // android.opengl.GLSurfaceView.Renderer
            public void onSurfaceChanged(GL10 gl10, int i, int i2) {
                StageView.this.c.onSurfaceChanged(i, i2);
            }

            @Override // android.opengl.GLSurfaceView.Renderer
            public void onDrawFrame(GL10 gl10) {
                StageView.this.c.onDrawFrame();
            }
        };
        a(context);
    }

    private void a(Context context) {
        Stage stage = new Stage(context);
        this.c = stage;
        stage.setRequestRenderListener(new IGLActor.RequestRenderListener() { // from class: com.huawei.ucd.gles.engine.android.StageView.3
            @Override // com.huawei.ucd.helper.gles.IGLActor.RequestRenderListener
            public void onRequestRenderCalled() {
                StageView.this.requestRender();
            }
        });
        setEGLContextClientVersion(2);
        setPreserveEGLContextOnPause(true);
    }

    @Override // android.opengl.GLSurfaceView
    public void setEGLConfigChooser(GLSurfaceView.EGLConfigChooser eGLConfigChooser) {
        super.setEGLConfigChooser(eGLConfigChooser);
        this.f8740a = eGLConfigChooser;
    }

    public void a() {
        if (this.f8740a == null) {
            this.f8740a = new nkg();
        }
        setRenderer(this.d);
        setRenderMode(0);
    }

    @Override // android.opengl.GLSurfaceView
    public void setRenderMode(int i) {
        super.setRenderMode(i);
    }

    public Stage getStage() {
        return this.c;
    }

    @Override // android.opengl.GLSurfaceView
    public void onPause() {
        super.onPause();
        this.c.onPause();
    }

    @Override // android.opengl.GLSurfaceView
    public void onResume() {
        super.onResume();
        this.c.onResume();
    }

    @Override // android.opengl.GLSurfaceView, android.view.SurfaceView, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c();
    }

    public void c() {
        if (this.d != null) {
            this.d = null;
        }
        Stage stage = this.c;
        if (stage != null) {
            stage.onDestroy();
            super.onDetachedFromWindow();
            this.c = null;
        }
    }

    public void setTranslucent() {
        setEGLConfigChooser(8, 8, 8, 8, 16, 0);
        getHolder().setFormat(-3);
        setZOrderOnTop(true);
    }

    public void setTranslucent(GLSurfaceView.EGLConfigChooser eGLConfigChooser) {
        setEGLConfigChooser(eGLConfigChooser);
        getHolder().setFormat(-3);
        setZOrderOnTop(true);
    }
}
