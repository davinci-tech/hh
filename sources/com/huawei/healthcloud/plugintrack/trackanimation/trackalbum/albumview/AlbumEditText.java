package com.huawei.healthcloud.plugintrack.trackanimation.trackalbum.albumview;

import android.content.Context;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import com.huawei.ui.commonui.edittext.HealthEditText;

/* loaded from: classes4.dex */
public class AlbumEditText extends HealthEditText {
    private OnDelKeyEventListener e;

    public interface OnDelKeyEventListener {
        void onDeleteClick();
    }

    public AlbumEditText(Context context) {
        super(context);
    }

    public AlbumEditText(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public AlbumEditText(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    @Override // com.huawei.ui.commonui.edittext.HealthEditText, com.huawei.uikit.hwedittext.widget.HwEditText
    public void setTextCursorColor(int i) {
        super.setTextCursorColor(i);
    }

    @Override // android.widget.TextView, android.view.View
    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        return new b(super.onCreateInputConnection(editorInfo), true);
    }

    class b extends InputConnectionWrapper {
        public b(InputConnection inputConnection, boolean z) {
            super(inputConnection, z);
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public boolean sendKeyEvent(KeyEvent keyEvent) {
            if (keyEvent.getAction() == 0 && keyEvent.getKeyCode() == 67 && AlbumEditText.this.e != null) {
                AlbumEditText.this.e.onDeleteClick();
                return true;
            }
            return super.sendKeyEvent(keyEvent);
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public boolean deleteSurroundingText(int i, int i2) {
            if (i == 1 && i2 == 0) {
                return sendKeyEvent(new KeyEvent(0, 67)) && sendKeyEvent(new KeyEvent(1, 67));
            }
            return super.deleteSurroundingText(i, i2);
        }
    }

    public void setDelKeyEventListener(OnDelKeyEventListener onDelKeyEventListener) {
        this.e = onDelKeyEventListener;
    }

    @Override // com.huawei.uikit.phone.hwedittext.widget.HwEditText, android.widget.TextView, android.view.View
    public Parcelable onSaveInstanceState() {
        super.onSaveInstanceState();
        return View.BaseSavedState.EMPTY_STATE;
    }
}
