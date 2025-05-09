package com.huawei.watchface.videoedit.gles.drawer;

import android.opengl.Matrix;
import com.huawei.watchface.videoedit.gles.glutils.TimeRange;
import com.huawei.watchface.videoedit.sysc.Optional;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes9.dex */
public class RenderNode {
    private static final int DEFAULT_CAPACITY = 2;
    private List<RenderNode> children;
    protected float[] modelMatrix = new float[16];
    protected float[] vpMatrix = new float[16];
    private int width = 0;
    private int height = 0;
    private String id = "";
    private TimeRange timeRange = new TimeRange(0, 0);

    public RenderNode() {
        Matrix.setIdentityM(this.modelMatrix, 0);
        this.children = new ArrayList(2);
    }

    public List<RenderNode> getChildren() {
        return this.children;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public TimeRange getTimeRange() {
        return this.timeRange;
    }

    public void draw(long j) {
        Iterator<RenderNode> it = this.children.iterator();
        while (it.hasNext()) {
            it.next().draw(j);
        }
    }

    public void release() {
        Iterator<RenderNode> it = this.children.iterator();
        while (it.hasNext()) {
            it.next().release();
        }
    }

    public float[] getVpMatrix() {
        return (float[]) this.vpMatrix.clone();
    }

    public float[] getModelMatrix() {
        return (float[]) this.modelMatrix.clone();
    }

    public <T> Optional<T> getData() {
        return Optional.ofNullable(null);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }
}
