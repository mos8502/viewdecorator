package hu.nemi.appcompat.view;

import android.content.Context;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.view.View;

public class AppCompatViewFactory implements ViewFactory {
    private AppCompatDelegate appCompatDelegate;
    private Context context;

    public AppCompatViewFactory(AppCompatDelegate appCompatDelegate, Context context) {
        this.appCompatDelegate = appCompatDelegate;
        this.context = context;
    }

    @Override
    public View createView(View parent, String name, AttributeSet attrs) {
        return appCompatDelegate.createView(parent, name, context, attrs);
    }
}
