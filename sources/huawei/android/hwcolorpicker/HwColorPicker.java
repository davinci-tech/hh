package huawei.android.hwcolorpicker;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.util.Log;
import java.lang.ref.WeakReference;

/* loaded from: classes7.dex */
public class HwColorPicker {
    private static final float CURVE_LINEAR_COEF = 5644.5f;
    private static final float CURVE_POWER_COEF = -0.54f;
    private static final PickedColor DEFAULT_COLOR = new PickedColor();
    private static final int DEFAULT_H = 25;
    private static final int DEFAULT_W = 25;
    private static final boolean IS_EMUI_LITE = false;
    public static final int MASK_CLIENT_TYPE = -16777216;
    public static final int MASK_RESULT_INDEX = 65535;
    public static final int MASK_RESULT_STATE = 16711680;
    private static final String TAG = "HwColorPicker";
    private static boolean sIsLibColorPickerExist;

    /* loaded from: classes10.dex */
    public interface AsyncProcessCallBack {
        void onColorPicked(PickedColor pickedColor);
    }

    public static boolean isDeviceSupport() {
        return true;
    }

    private static native int[] processPixels(int[] iArr, int i, int i2);

    static {
        sIsLibColorPickerExist = true;
        try {
            System.loadLibrary("colorpicker");
        } catch (UnsatisfiedLinkError unused) {
            sIsLibColorPickerExist = false;
            Log.e(TAG, "libcolorpicker.so couldn't be found.");
        }
    }

    public static boolean isColorPickerEnable() {
        if (!sIsLibColorPickerExist) {
            Log.w(TAG, "lib colorPicker is not exist!");
        }
        return sIsLibColorPickerExist;
    }

    public static boolean isEnable() {
        return isColorPickerEnable() && isDeviceSupport();
    }

    public static PickedColor processBitmap(Bitmap bitmap) {
        if (!isEnable()) {
            return DEFAULT_COLOR;
        }
        int[] pixelsFromBitmap = getPixelsFromBitmap(bitmap);
        if (pixelsFromBitmap == null) {
            Log.e(TAG, "getPixelsFromBitmap(" + bitmap + "), return null!");
            return DEFAULT_COLOR;
        }
        return processPixels(pixelsFromBitmap);
    }

    public static PickedColor processBitmap(Bitmap bitmap, Rect rect) {
        if (!isEnable()) {
            return DEFAULT_COLOR;
        }
        int[] pixelsFromBitmap = getPixelsFromBitmap(bitmap, rect);
        if (pixelsFromBitmap == null) {
            Log.e(TAG, "getPixelsFromBitmap(" + bitmap + ", " + rect + "), return null!");
            return DEFAULT_COLOR;
        }
        return processPixels(pixelsFromBitmap);
    }

    public static PickedColor processBitmap(Bitmap bitmap, ClientType clientType) {
        if (!isEnable()) {
            return DEFAULT_COLOR;
        }
        int[] pixelsFromBitmap = getPixelsFromBitmap(bitmap);
        if (pixelsFromBitmap == null) {
            Log.e(TAG, "getPixelsFromBitmap(" + bitmap + ", " + clientType + "), return null!");
            return DEFAULT_COLOR;
        }
        return processPixels(pixelsFromBitmap, clientType);
    }

    public static PickedColor processBitmap(Bitmap bitmap, Rect rect, ClientType clientType) {
        if (!isEnable()) {
            return DEFAULT_COLOR;
        }
        int[] pixelsFromBitmap = getPixelsFromBitmap(bitmap, rect);
        if (pixelsFromBitmap == null) {
            Log.e(TAG, "getPixelsFromBitmap(" + bitmap + ", " + rect + ", " + clientType + "), return null!");
            return DEFAULT_COLOR;
        }
        return processPixels(pixelsFromBitmap, clientType);
    }

    public static void processBitmapAsync(Bitmap bitmap, AsyncProcessCallBack asyncProcessCallBack) {
        if (isEnable()) {
            processBitmapAsync(bitmap, null, ClientType.Default, asyncProcessCallBack);
        } else if (asyncProcessCallBack != null) {
            asyncProcessCallBack.onColorPicked(DEFAULT_COLOR);
        }
    }

    public static void processBitmapAsync(Bitmap bitmap, Rect rect, AsyncProcessCallBack asyncProcessCallBack) {
        if (isEnable()) {
            processBitmapAsync(bitmap, rect, ClientType.Default, asyncProcessCallBack);
        } else if (asyncProcessCallBack != null) {
            asyncProcessCallBack.onColorPicked(DEFAULT_COLOR);
        }
    }

    public static void processBitmapAsync(Bitmap bitmap, ClientType clientType, AsyncProcessCallBack asyncProcessCallBack) {
        if (isEnable()) {
            processBitmapAsync(bitmap, null, clientType, asyncProcessCallBack);
        } else if (asyncProcessCallBack != null) {
            asyncProcessCallBack.onColorPicked(DEFAULT_COLOR);
        }
    }

    public static void processBitmapAsync(Bitmap bitmap, Rect rect, ClientType clientType, AsyncProcessCallBack asyncProcessCallBack) {
        if (isEnable()) {
            new ColorPickingTask(asyncProcessCallBack).execute(new WeakReference(new TaskParams(bitmap, rect, clientType)));
        } else if (asyncProcessCallBack != null) {
            asyncProcessCallBack.onColorPicked(DEFAULT_COLOR);
        }
    }

    public enum ClientType {
        Default(0),
        Extend(1),
        Debug(1),
        Music(2);

        private int index;

        ClientType(int i) {
            this.index = i;
        }
    }

    /* loaded from: classes10.dex */
    public enum StateType {
        UnDefined(-1),
        OK(0),
        Error(1),
        None(2),
        Common(3),
        Single(4),
        Gray(5);

        private int index;

        StateType(int i) {
            this.index = i;
        }
    }

    public enum ResultType {
        Domain(ClientType.Default, 0),
        DomainDark(ClientType.Default, 1),
        DomainDarkB(ClientType.Default, 2),
        DomainDarkC(ClientType.Default, 3),
        DomainLight(ClientType.Default, 4),
        DomainLightB(ClientType.Default, 5),
        DomainLightC(ClientType.Default, 6),
        Second(ClientType.Default, 7),
        SecondDark(ClientType.Default, 8),
        SecondLight(ClientType.Default, 9),
        Widget(ClientType.Default, 10),
        Shadow(ClientType.Default, 11),
        OriginRgb1(ClientType.Extend, 0),
        OriginRgb2(ClientType.Extend, 1),
        OriginRgb3(ClientType.Extend, 2),
        OriginNum1(ClientType.Extend, 3),
        OriginNum2(ClientType.Extend, 4),
        OriginNum3(ClientType.Extend, 5),
        MergedRgb1(ClientType.Extend, 6),
        MergedRgb2(ClientType.Extend, 7),
        MergedRgb3(ClientType.Extend, 8),
        MergedNum1(ClientType.Extend, 9),
        MergedNum2(ClientType.Extend, 10),
        MergedNum3(ClientType.Extend, 11),
        Music_Domain(ClientType.Music, 0),
        Music_Light(ClientType.Music, 1),
        Music_Widget(ClientType.Music, 2),
        Music_Title(ClientType.Music, 3);

        private ClientType clientType;
        private int flag;
        private int index;

        ResultType(ClientType clientType, int i) {
            this.clientType = clientType;
            int i2 = 65535 & i;
            this.index = i2;
            this.flag = i2 | ((clientType.index << 24) & (-16777216));
        }

        public static int getRequestedColorNum(int i) {
            int i2 = 0;
            for (ResultType resultType : values()) {
                if (i == resultType.clientType.index) {
                    i2++;
                }
            }
            return i2;
        }

        @Override // java.lang.Enum
        public String toString() {
            return name() + "[" + this.clientType + ", " + this.index + ", " + String.format("0x%08x", Integer.valueOf(this.flag)) + "]";
        }
    }

    public static class PickedColor {
        private int mClientType;
        private int[] mColorPicked;
        private int mResultState;

        private PickedColor() {
            this.mClientType = -1;
            this.mResultState = -1;
        }

        private PickedColor(int[] iArr) {
            this.mClientType = -1;
            this.mResultState = -1;
            if (iArr == null || iArr.length <= 0) {
                throw new RuntimeException("Illegal result, colorResult is null or Empty!");
            }
            int i = iArr[0];
            int i2 = ((-16777216) & i) >> 24;
            this.mClientType = i2;
            this.mResultState = (i & HwColorPicker.MASK_RESULT_STATE) >> 16;
            int requestedColorNum = ResultType.getRequestedColorNum(i2);
            if (iArr.length != requestedColorNum + 1) {
                Log.e(HwColorPicker.TAG, "colorResult's length : " + iArr.length + ", requestedNum : " + requestedColorNum + ", mClientType : " + this.mClientType + ", mResultState : " + this.mResultState);
                throw new RuntimeException("Illegal result, colorResult's length must be (requestedNum + 1)!");
            }
            int[] iArr2 = new int[requestedColorNum];
            this.mColorPicked = iArr2;
            System.arraycopy(iArr, 1, iArr2, 0, requestedColorNum);
        }

        public int getState() {
            return this.mResultState;
        }

        @Deprecated
        public int getDomainColor() {
            return get(ResultType.Domain);
        }

        @Deprecated
        public int getWidgetColor() {
            return get(ResultType.Widget);
        }

        @Deprecated
        public int getShadowColor() {
            return get(ResultType.Shadow);
        }

        public int get(ResultType resultType) {
            int[] iArr = this.mColorPicked;
            if (iArr == null || iArr.length <= 0) {
                return 0;
            }
            int i = resultType.index & 65535;
            int[] iArr2 = this.mColorPicked;
            if (i >= iArr2.length) {
                return 0;
            }
            return iArr2[i];
        }

        public String toString() {
            StringBuffer stringBuffer = new StringBuffer("PickedColor{");
            stringBuffer.append(this.mClientType).append(", ").append(this.mResultState).append(", [");
            int[] iArr = this.mColorPicked;
            if (iArr != null) {
                int length = iArr.length;
                for (int i = 0; i < length; i++) {
                    stringBuffer.append(String.format("0x%08x", Integer.valueOf(this.mColorPicked[i])));
                    if (i != length - 1) {
                        stringBuffer.append(", ");
                    }
                }
            }
            stringBuffer.append("]}");
            return stringBuffer.toString();
        }
    }

    /* loaded from: classes10.dex */
    static class TaskParams {
        Bitmap bitmap;
        ClientType clientType;
        Rect rect;

        TaskParams(Bitmap bitmap, Rect rect, ClientType clientType) {
            ClientType clientType2 = ClientType.Default;
            this.bitmap = bitmap;
            this.rect = rect;
            this.clientType = clientType;
        }
    }

    /* loaded from: classes10.dex */
    static class ColorPickingTask extends AsyncTask<WeakReference<TaskParams>, Integer, PickedColor> {
        AsyncProcessCallBack mCallBack;

        private ColorPickingTask(AsyncProcessCallBack asyncProcessCallBack) {
            this.mCallBack = asyncProcessCallBack;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        @SafeVarargs
        public final PickedColor doInBackground(WeakReference<TaskParams>... weakReferenceArr) {
            TaskParams taskParams = weakReferenceArr[0].get();
            if (taskParams == null) {
                return HwColorPicker.DEFAULT_COLOR;
            }
            if (taskParams.rect == null) {
                return HwColorPicker.processBitmap(taskParams.bitmap, taskParams.clientType);
            }
            return HwColorPicker.processBitmap(taskParams.bitmap, taskParams.rect, taskParams.clientType);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(PickedColor pickedColor) {
            AsyncProcessCallBack asyncProcessCallBack = this.mCallBack;
            if (asyncProcessCallBack != null) {
                asyncProcessCallBack.onColorPicked(pickedColor);
            }
        }
    }

    private static int[] getPixelsFromBitmap(Bitmap bitmap) {
        if (bitmap == null) {
            Log.e(TAG, "bitmap is null, can't be processed!");
            return null;
        }
        return getPixelsFormFixedSizeBitmap(Bitmap.createScaledBitmap(bitmap, 25, 25, false));
    }

    private static int[] getPixelsFromBitmap(Bitmap bitmap, Rect rect) {
        if (bitmap == null || rect == null) {
            Log.e(TAG, "bitmap is null or rect is null, can't be processed!");
            return null;
        }
        Rect rect2 = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        if (!rect2.intersect(rect)) {
            Log.e(TAG, "rect and bitmap's rect has not intersection, can't be processed!");
            return null;
        }
        int i = rect2.left;
        int i2 = rect2.top;
        int i3 = rect2.right - rect2.left;
        int i4 = rect2.bottom - rect2.top;
        if (isSmallSizeRect(i3, i4)) {
            return createSubBitmapAndScale(bitmap, i, i2, i3, i4);
        }
        return scaleAndCreateSubBitmap(bitmap, i, i2, i3, i4);
    }

    private static boolean isSmallSizeRect(int i, int i2) {
        return Float.compare(((float) (Math.pow((double) i, -0.5400000214576721d) * 5644.5d)) - ((float) i2), 0.0f) > 0;
    }

    private static int[] createSubBitmapAndScale(Bitmap bitmap, int i, int i2, int i3, int i4) {
        return getPixelsFormFixedSizeBitmap(Bitmap.createScaledBitmap(Bitmap.createBitmap(bitmap, i, i2, i3, i4), 25, 25, false));
    }

    private static int[] scaleAndCreateSubBitmap(Bitmap bitmap, int i, int i2, int i3, int i4) {
        float f = i3;
        float f2 = i4;
        return getPixelsFormFixedSizeBitmap(Bitmap.createBitmap(Bitmap.createScaledBitmap(bitmap, Math.round((bitmap.getWidth() * 25) / f), Math.round((bitmap.getHeight() * 25) / f2), false), Math.round((i * 25) / f), Math.round((i2 * 25) / f2), 25, 25));
    }

    private static int[] getPixelsFormFixedSizeBitmap(Bitmap bitmap) {
        int[] iArr = new int[625];
        bitmap.getPixels(iArr, 0, 25, 0, 0, 25, 25);
        return iArr;
    }

    private static PickedColor processPixels(int[] iArr) {
        if (!isEnable()) {
            return DEFAULT_COLOR;
        }
        return new PickedColor(processPixels(iArr, iArr.length, ClientType.Default.index));
    }

    private static PickedColor processPixels(int[] iArr, ClientType clientType) {
        if (!isEnable()) {
            return DEFAULT_COLOR;
        }
        int length = iArr.length;
        if (clientType == null) {
            clientType = ClientType.Default;
        }
        return new PickedColor(processPixels(iArr, length, clientType.index));
    }
}
