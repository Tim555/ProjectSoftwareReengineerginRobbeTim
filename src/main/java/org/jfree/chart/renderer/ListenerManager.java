package org.jfree.chart.renderer;

import org.jfree.chart.event.RendererChangeEvent;
import org.jfree.chart.event.RendererChangeListener;
import org.jfree.chart.util.Args;
import org.jfree.chart.util.ObjectUtils;
import org.jfree.chart.util.ShapeUtils;

import javax.swing.event.EventListenerList;
import java.io.Serializable;
import java.util.Arrays;
import java.util.EventListener;
import java.util.List;

public class ListenerManager implements Serializable {

    /** Storage for registered change listeners. */
    private transient EventListenerList listenerList;


    /**
     * Abstractrenderer
     */
    private AbstractRenderer abstractRenderer;


    public ListenerManager(AbstractRenderer abstractRenderer) {
        this.listenerList = new EventListenerList();
        this.abstractRenderer = abstractRenderer;
    }

    /**
     * Get listenerslist
     */
    public EventListenerList getListenerList() {
        return this.listenerList;
    }


    /**
     * Setter
     * @param listenerList
     */
    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }


    /**
     * Registers an object to receive notification of changes to the renderer.
     *
     * @param listener  the listener ({@code null} not permitted).
     *
     * @see #removeChangeListener(RendererChangeListener)
     */
    public void addChangeListener(RendererChangeListener listener) {
        Args.nullNotPermitted(listener, "listener");
        this.listenerList.add(RendererChangeListener.class, listener);
    }


    /**
     * Deregisters an object so that it no longer receives
     * notification of changes to the renderer.
     *
     * @param listener  the object ({@code null} not permitted).
     *
     * @see #addChangeListener(RendererChangeListener)
     */
    public void removeChangeListener(RendererChangeListener listener) {
        Args.nullNotPermitted(listener, "listener");
        this.listenerList.remove(RendererChangeListener.class, listener);
    }


    /**
     * Returns {@code true} if the specified object is registered with
     * the dataset as a listener.  Most applications won't need to call this
     * method, it exists mainly for use by unit testing code.
     *
     * @param listener  the listener.
     *
     * @return A boolean.
     */
    public boolean hasListener(EventListener listener) {
        List list = Arrays.asList(this.listenerList.getListenerList());
        return list.contains(listener);
    }

    /**
     * Sends a {@link RendererChangeEvent} to all registered listeners.
     *
     * @since 1.0.5
     */
    public void fireChangeEvent() {

        // the commented out code would be better, but only if
        // RendererChangeEvent is immutable, which it isn't.  See if there is
        // a way to fix this...

        //if (this.event == null) {
        //    this.event = new RendererChangeEvent(this);
        //}
        //notifyListeners(this.event);

        notifyListeners(new RendererChangeEvent(this.abstractRenderer));
    }


    /**
     * Notifies all registered listeners that the renderer has been modified.
     *
     * @param event  information about the change event.
     */
    public void notifyListeners(RendererChangeEvent event) {
        Object[] ls = this.listenerList.getListenerList();
        for (int i = ls.length - 2; i >= 0; i -= 2) {
            if (ls[i] == RendererChangeListener.class) {
                ((RendererChangeListener) ls[i + 1]).rendererChanged(event);
            }
        }
    }

    public ListenerManager clone () {
        ListenerManager clone = new ListenerManager(this.abstractRenderer);

        clone.setListenerList(this.getListenerList());

        return clone;
    }

    public boolean equals(Object obj) {
        return true;
//        if (obj == this) {
//            return true;
//        }
//        if (!(obj instanceof ListenerManager)) {
//            return false;
//        }
//        ListenerManager that = (ListenerManager) obj;
//
//        if (!this.listenerList.equals(that.getListenerList())) {
//            return false;
//        }
//
//        return true;
    }

}
