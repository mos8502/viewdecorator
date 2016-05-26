package hu.nemi.appcompat.view;

import android.util.AttributeSet;
import android.view.View;

public class CompositingViewFactory implements ViewFactory {
    private ViewFactory[] factories;

    public CompositingViewFactory(ViewFactory... factories) {
        this.factories = factories;
    }

    @Override
    public View createView(View parent, String name, AttributeSet attrs) {
        View view = null;

        for(int i = 0; i < factories.length && view == null; ++i) {
            view = factories[i].createView(parent, name, attrs);
        }

        return view;
    }
}
