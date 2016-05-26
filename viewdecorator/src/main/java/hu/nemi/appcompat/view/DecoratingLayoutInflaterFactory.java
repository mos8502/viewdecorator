package hu.nemi.appcompat.view;

import android.content.Context;
import android.support.v4.view.LayoutInflaterFactory;
import android.util.AttributeSet;
import android.view.View;

class DecoratingLayoutInflaterFactory implements LayoutInflaterFactory {
    private ViewDecorator viewDecorator;
    private ViewFactory viewFactory;

    public DecoratingLayoutInflaterFactory(ViewFactory viewFactory, ViewDecorator viewDecorator) {
        this.viewDecorator = viewDecorator;
        this.viewFactory = viewFactory;
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        View view = viewFactory.createView(parent, name, attrs);
        if(view != null) {
            viewDecorator.decorate(parent, view, context, attrs);
        }
        return view;
    }
}
