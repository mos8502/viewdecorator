package hu.nemi.appcompat.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.view.View;

class DecoratingLayoutInflaterFactory implements LayoutInflaterFactory {
    private AppCompatDelegate delegate;
    private ViewDecorator target;

    public DecoratingLayoutInflaterFactory(@NonNull AppCompatDelegate delegate,
                                           @NonNull ViewDecorator target) {
        this.delegate = delegate;
        this.target = target;
    }


    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        View view = delegate.createView(parent, name, context, attrs);
        if(view != null) {
            target.decorate(parent, view, context, attrs);
        }
        return view;
    }
}
