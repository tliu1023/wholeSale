package com.example.wholesale;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

public class SearchBarEditText extends androidx.appcompat.widget.AppCompatEditText {
    private Drawable deleteIcon, searchIcon;
    // private int length = 0;
    public SearchBarEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        // length = text.length();
        setTextColor(Color.BLACK);
        setDeleteIconVisibility(hasFocus() && text.length() > 0);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        setDeleteIconVisibility(focused && super.length()> 0);
    }

    private void setDeleteIconVisibility(boolean b) {
        if(b){
            setCompoundDrawablesWithIntrinsicBounds(searchIcon, null, deleteIcon, null);
        }else{
            setCompoundDrawablesWithIntrinsicBounds(searchIcon, null, null, null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_UP:
                Drawable d = deleteIcon;
                if (d != null && event.getX() <= (getWidth() - getPaddingRight())
                        && event.getX() >= (getWidth() - getPaddingRight() - d.getBounds().width())) {
                    setText("");
                }
                setCompoundDrawablesWithIntrinsicBounds(searchIcon, null, null, null);
        }
        return super.onTouchEvent(event);
    }

    private void init() {
        deleteIcon = getResources().getDrawable(R.drawable.searchbar_delete);
        searchIcon = getResources().getDrawable(R.drawable.searchbar_search);


        setCompoundDrawablesWithIntrinsicBounds(searchIcon, null, null, null);
    }
}
