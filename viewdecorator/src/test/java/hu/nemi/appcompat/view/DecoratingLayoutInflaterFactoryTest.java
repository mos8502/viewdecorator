package hu.nemi.appcompat.view;

import android.content.Context;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.view.View;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class DecoratingLayoutInflaterFactoryTest {
    static final String VIEW_NAME = "foo";

    @Mock AppCompatDelegate appCompatDelegate;
    @Mock ViewDecorator viewDecorator;
    @Mock View parent;
    @Mock View view;
    @Mock Context context;
    @Mock AttributeSet attrs;

    DecoratingLayoutInflaterFactory decoratingLayoutInflaterFactory;

    @Before
    public void setup() {

        decoratingLayoutInflaterFactory = new DecoratingLayoutInflaterFactory(appCompatDelegate,
                viewDecorator);
    }

    @Test
    public void testOnCreateView() throws Exception {
        doReturn(view).when(appCompatDelegate).createView(
                eq(parent), eq(VIEW_NAME), eq(context), eq(attrs));

        View actual = decoratingLayoutInflaterFactory.onCreateView(parent, VIEW_NAME, context, attrs);
        assertEquals(view, actual);
        verify(viewDecorator).decorate(eq(parent), eq(view), eq(context), eq(attrs));
    }

    @Test
    public void testOnCreateViewViewNotCreated() {
        decoratingLayoutInflaterFactory.onCreateView(parent, VIEW_NAME, context, attrs);
        verifyZeroInteractions(viewDecorator);
    }
}