package com.huawei.ucd.gles.engine;

import android.content.Context;
import android.opengl.GLES20;
import java.util.Iterator;

/* loaded from: classes9.dex */
public class Stage extends Group {
    public Stage(Context context) {
        super(context);
    }

    @Override // com.huawei.ucd.gles.engine.Group
    protected void onChildAdded(final Actor actor) {
        actor.setRequestRenderListener(getRequestRenderListener());
        if (this.mIsResumed) {
            actor.onResume();
        }
        if (actor.isSurfaceCreated()) {
            return;
        }
        actor.runOnceBeforeDraw(new Runnable() { // from class: com.huawei.ucd.gles.engine.Stage.1
            @Override // java.lang.Runnable
            public void run() {
                actor.onSurfaceCreated();
                actor.onSurfaceChanged(Stage.this.mSurfaceWidth, Stage.this.mSurfaceHeight);
            }
        });
        actor.requestRender();
    }

    @Override // com.huawei.ucd.gles.engine.Actor, com.huawei.ucd.helper.gles.IGLActor
    public void onDrawFrame() {
        GLES20.glClear(16640);
        for (int i = 0; i < this.mChildren.size(); i++) {
            this.mChildren.get(i).onDrawFrame();
        }
    }

    public void d(Actor... actorArr) {
        addChild(actorArr);
    }

    @Override // com.huawei.ucd.gles.engine.Actor
    public void onResume() {
        super.onResume();
        Iterator<Actor> it = this.mChildren.iterator();
        while (it.hasNext()) {
            it.next().onResume();
        }
    }

    @Override // com.huawei.ucd.gles.engine.Actor
    public void onPause() {
        Iterator<Actor> it = this.mChildren.iterator();
        while (it.hasNext()) {
            it.next().onPause();
        }
    }

    @Override // com.huawei.ucd.gles.engine.Actor
    public void onDestroy() {
        Iterator<Actor> it = this.mChildren.iterator();
        while (it.hasNext()) {
            it.next().onDestroy();
        }
        this.mChildren.clear();
    }
}
