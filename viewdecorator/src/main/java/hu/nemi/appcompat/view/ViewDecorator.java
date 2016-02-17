package hu.nemi.appcompat.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public interface ViewDecorator {
    /**
     * Called to give the decorator the set up the view before being returned by the LayoutInflater
     *
     * @param parent paret of view
     * @param view view to be decorated
     * @param context context in which the view was created
     * @param attrs view's attributes. This can be used to parse custom attributes like font, etc
     * */
    void decorate(View parent, View view, Context context, AttributeSet attrs);
}
