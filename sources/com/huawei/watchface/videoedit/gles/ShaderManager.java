package com.huawei.watchface.videoedit.gles;

import android.content.Context;
import android.graphics.Bitmap;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import com.huawei.watchface.utils.HwLog;
import com.huawei.watchface.videoedit.gles.glutils.ShaderUtils;
import com.huawei.watchface.videoedit.utils.Utils;
import java.util.HashMap;
import java.util.Iterator;

/* loaded from: classes9.dex */
public class ShaderManager {
    private static final String BASIC_MVP_VERTEX = "shaders/basic_mvp_matrix_vertex.glsl";
    private static final String BASIC_VERTEX = "shaders/basic_vertex.glsl";
    private static final String[] FBO_NAMES = {Constant.FBO, Constant.FBO_TWO, Constant.BLEND_FBO, Constant.BLUR_FBO, Constant.BLUR_FBO_TWO, Constant.FILTER_FBO, Constant.FILTER_FBO_SECOND, Constant.VIDEO_FBO, Constant.VIDEO_FBO_SECOND, Constant.PRE_MATERIAL_FBO, Constant.NEXT_MATERIAL_FBO, Constant.PRE_MATERIAL_FILTER_FBO, Constant.NEXT_MATERIAL_FILTER_FBO, Constant.TRANSITION_FBO, Constant.RENDER_TO_SCREEN_FBO, Constant.MASK_VIDEO_FBO};
    private Context mContext;
    private HashMap<String, ShaderFileName> mShaderMap = new HashMap<>();
    private HashMap<String, Integer> mVertexShaderCaches = new HashMap<>();
    private HashMap<String, Integer> mFragmentShaderCaches = new HashMap<>();
    private HashMap<String, Integer> mProgramCaches = new HashMap<>();
    private HashMap<String, Integer> mFboCaches = new HashMap<>();
    private HashMap<String, Integer> mFboTexCaches = new HashMap<>();
    private HashMap<String, Integer> mTextureCaches = new HashMap<>();
    private int[] mViewport = new int[4];
    private float[] mVpMatrix = new float[16];

    public ShaderManager(Context context) {
        this.mContext = context;
        HashMap<String, ShaderFileName> hashMap = this.mShaderMap;
        String str = BASIC_MVP_VERTEX;
        hashMap.put(Constant.BASIC, new ShaderFileName(str, "shaders/basic_fragment.glsl"));
        this.mShaderMap.put("frame", new ShaderFileName(str, "shaders/base_fragment.glsl"));
        this.mShaderMap.put(Constant.TEXT, new ShaderFileName(str, "shaders/text_fragment.glsl"));
        this.mShaderMap.put(Constant.MASK, new ShaderFileName(str, "shaders/mask_fragment.glsl"));
        this.mShaderMap.put(Constant.GAUSSIAN, new ShaderFileName(str, "shaders/gaussian_blur_fragment.glsl"));
        HashMap<String, ShaderFileName> hashMap2 = this.mShaderMap;
        String str2 = BASIC_VERTEX;
        hashMap2.put(Constant.VIDEO_DRAW, new ShaderFileName(str2, "shaders/video_basic_fragment.glsl"));
        this.mShaderMap.put(Constant.FILTER, new ShaderFileName(str2, "shaders/look_out_table_fragment.glsl"));
        this.mShaderMap.put(Constant.TINT, new ShaderFileName(str, "shaders/colored_fragment.glsl"));
        this.mShaderMap.put("blur", new ShaderFileName(str, "shaders/text_blur_fragment.glsl"));
        this.mShaderMap.put("color", new ShaderFileName(str, "shaders/pure_color_fragment.glsl"));
        addTransitions();
    }

    private void addSingleTransition(String str, String str2) {
        this.mShaderMap.put(str, new ShaderFileName(BASIC_VERTEX, str2));
    }

    private void addTransitions() {
        addSingleTransition(Constant.STICKER_BLACK, "shaders/sticker_black.glsl");
        addSingleTransition(Constant.STICKER_WHITE, "shaders/sticker_white.glsl");
        addSingleTransition(Constant.STICKER_BLEND, "shaders/sticker_blend.glsl");
        addSingleTransition(Constant.STICKER_DISSOLVE, "shaders/sticker_dissolve.glsl");
        addSingleTransition(Constant.BLUR_TRANSITION, "shaders/blur.glsl");
        addSingleTransition(Constant.LEFT_TRANSITION, "shaders/move_left.glsl");
        addSingleTransition(Constant.RIGHT_TRANSITION, "shaders/move_right.glsl");
        addSingleTransition(Constant.UP_TRANSITION, "shaders/move_up.glsl");
        addSingleTransition(Constant.DOWN_TRANSITION, "shaders/move_down.glsl");
        addSingleTransition(Constant.ZOOM_IN_TRANSITION, "shaders/zoom_in.glsl");
        addSingleTransition(Constant.ZOOM_OUT_TRANSITION, "shaders/zoom_out.glsl");
        addSingleTransition(Constant.ROTATION_TRANSITION, "shaders/rotation.glsl");
        addSingleTransition(Constant.DIRECT_DRAW, "shaders/base_fragment.glsl");
        addSingleTransition(Constant.SINGLE_FRAME, "shaders/single_frame_fragment.glsl");
    }

    public int getFboId(String str) {
        return getFboId(str, 1080, Constant.FBO_HEIGHT);
    }

    public int getFboTextureId(String str) {
        return getFboTextureId(str, 1080, Constant.FBO_HEIGHT);
    }

    public int getFboId(String str, int i, int i2) {
        if (!this.mFboCaches.containsKey(str)) {
            createAndSaveFbo(str, i, i2);
        }
        return this.mFboCaches.get(str).intValue();
    }

    private int getFboTextureId(String str, int i, int i2) {
        if (!this.mFboTexCaches.containsKey(str)) {
            createAndSaveFbo(str, i, i2);
        }
        return this.mFboTexCaches.get(str).intValue();
    }

    private void createAndSaveFbo(String str, int i, int i2) {
        HwLog.d(HwLog.TAG, "createAndSaveFbo, name: " + str + ", width: " + i + ", height: " + i2);
        int[] iArr = new int[1];
        int[] iArr2 = new int[1];
        Utils.createFbos(iArr, iArr2, i, i2);
        this.mFboCaches.put(str, Integer.valueOf(iArr[0]));
        this.mFboTexCaches.put(str, Integer.valueOf(iArr2[0]));
    }

    public void prepareFbos(int i, int i2) {
        clearFbo();
        for (String str : FBO_NAMES) {
            createAndSaveFbo(str, i, i2);
        }
    }

    static class ShaderFileName {
        private String fragment;
        private String vertex;

        private ShaderFileName(String str, String str2) {
            this.vertex = str;
            this.fragment = str2;
        }

        String getVertex() {
            return this.vertex;
        }

        String getFragment() {
            return this.fragment;
        }
    }

    public int getTexture2D(String str, boolean z) {
        Bitmap picture;
        if (this.mTextureCaches.containsKey(str)) {
            return this.mTextureCaches.get(str).intValue();
        }
        int[] iArr = new int[1];
        Utils.createTextures(iArr);
        if (z) {
            picture = Utils.getPictureFromAssets(this.mContext, str);
        } else {
            picture = Utils.getPicture(str);
        }
        if (picture == null) {
            HwLog.d(HwLog.TAG, "getTexture2D bitmap is null, name: " + str);
            return -1;
        }
        GLES20.glBindTexture(3553, iArr[0]);
        GLUtils.texImage2D(3553, 0, picture, 0);
        GLES20.glGenerateMipmap(3553);
        picture.recycle();
        GLES20.glBindTexture(3553, 0);
        this.mTextureCaches.put(str, Integer.valueOf(iArr[0]));
        return iArr[0];
    }

    public void updateCashViewport(int i, int i2, int i3, int i4) {
        int[] iArr = this.mViewport;
        iArr[0] = i;
        iArr[1] = i2;
        iArr[2] = i3;
        iArr[3] = i4;
    }

    public void applyCachedViewport() {
        int[] iArr = this.mViewport;
        GLES20.glViewport(iArr[0], iArr[1], iArr[2], iArr[3]);
    }

    public float getViewportRatio() {
        int[] iArr = this.mViewport;
        int i = iArr[2];
        int i2 = iArr[3];
        if (i * i2 == 0) {
            return -1.0f;
        }
        return i / i2;
    }

    public int getShader(String str) {
        int loadShader;
        int i;
        if (this.mProgramCaches.containsKey(str)) {
            return this.mProgramCaches.get(str).intValue();
        }
        if (this.mContext == null) {
            HwLog.e(HwLog.TAG, "no context");
            return -1;
        }
        if (!this.mShaderMap.containsKey(str)) {
            HwLog.e(HwLog.TAG, "shaderMap not contains current shader: " + str);
            return -1;
        }
        ShaderFileName shaderFileName = this.mShaderMap.get(str);
        if (this.mVertexShaderCaches.containsKey(shaderFileName.getVertex())) {
            loadShader = this.mVertexShaderCaches.get(shaderFileName.getVertex()).intValue();
        } else {
            loadShader = ShaderUtils.loadShader(this.mContext, 35633, shaderFileName.getVertex());
            if (loadShader == -1) {
                HwLog.e(HwLog.TAG, "create vertex shader failed. ");
            } else {
                this.mVertexShaderCaches.put(shaderFileName.getVertex(), Integer.valueOf(loadShader));
            }
        }
        if (this.mFragmentShaderCaches.containsKey(shaderFileName.getFragment())) {
            i = this.mFragmentShaderCaches.get(shaderFileName.getFragment()).intValue();
        } else {
            int loadShader2 = ShaderUtils.loadShader(this.mContext, 35632, shaderFileName.getFragment());
            if (loadShader2 == -1) {
                HwLog.e(HwLog.TAG, "create fragment shader failed");
            } else {
                this.mFragmentShaderCaches.put(shaderFileName.getFragment(), Integer.valueOf(loadShader2));
            }
            i = loadShader2;
        }
        int createProgram = ShaderUtils.createProgram(loadShader, i);
        if (createProgram == -1) {
            HwLog.e(HwLog.TAG, "create program failed");
        } else {
            this.mProgramCaches.put(str, Integer.valueOf(createProgram));
        }
        Utils.checkGlError("get shader.");
        return createProgram;
    }

    public void updateVpMatrix(float[] fArr) {
        this.mVpMatrix = (float[]) fArr.clone();
    }

    public float[] getVpMatrix() {
        return (float[]) this.mVpMatrix.clone();
    }

    public void clean() {
        Iterator<Integer> it = this.mProgramCaches.values().iterator();
        while (it.hasNext()) {
            GLES20.glDeleteProgram(it.next().intValue());
        }
        this.mProgramCaches.clear();
        Iterator<Integer> it2 = this.mVertexShaderCaches.values().iterator();
        while (it2.hasNext()) {
            GLES20.glDeleteShader(it2.next().intValue());
        }
        this.mVertexShaderCaches.clear();
        Iterator<Integer> it3 = this.mFragmentShaderCaches.values().iterator();
        while (it3.hasNext()) {
            GLES20.glDeleteShader(it3.next().intValue());
        }
        this.mFragmentShaderCaches.clear();
        int[] iArr = new int[this.mTextureCaches.size()];
        Iterator<Integer> it4 = this.mTextureCaches.values().iterator();
        int i = 0;
        while (it4.hasNext()) {
            iArr[i] = it4.next().intValue();
            i++;
        }
        GLES20.glDeleteTextures(i, iArr, 0);
        this.mTextureCaches.clear();
        clearFbo();
    }

    void clearFbo() {
        if (this.mFboTexCaches.size() * this.mFboCaches.size() == 0) {
            return;
        }
        int[] iArr = new int[this.mFboTexCaches.size()];
        Iterator<Integer> it = this.mFboTexCaches.values().iterator();
        int i = 0;
        while (it.hasNext()) {
            iArr[i] = it.next().intValue();
            i++;
        }
        GLES20.glDeleteTextures(i, iArr, 0);
        this.mFboTexCaches.clear();
        int[] iArr2 = new int[this.mFboCaches.size()];
        Iterator<Integer> it2 = this.mFboCaches.values().iterator();
        int i2 = 0;
        while (it2.hasNext()) {
            iArr2[i2] = it2.next().intValue();
            i2++;
        }
        GLES20.glDeleteFramebuffers(i2, iArr2, 0);
        this.mFboCaches.clear();
    }

    public void clearTargetFbo(String str) {
        if (this.mFboCaches.containsKey(str)) {
            GLES20.glDeleteFramebuffers(1, new int[]{this.mFboCaches.get(str).intValue()}, 0);
            this.mFboCaches.remove(str);
        }
        if (this.mFboTexCaches.containsKey(str)) {
            GLES20.glDeleteTextures(1, new int[]{this.mFboTexCaches.get(str).intValue()}, 0);
        }
    }
}
