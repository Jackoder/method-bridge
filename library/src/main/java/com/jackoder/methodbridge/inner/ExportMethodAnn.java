package com.jackoder.methodbridge.inner;

import com.jackoder.methodbridge.ann.ExportMethod;
import com.jackoder.methodbridge.inner.base.Ann;

/**
 * @author Jackoder
 * @version 2015/12/30.
 */
public final class ExportMethodAnn extends Ann<ExportMethod> {

    public final String[] names;

    public ExportMethodAnn(ExportMethod annotation) {
        super(annotation);
        String[] names;
        if (hackSuccess()) {
            names = (String[]) getElement(NAME);
            cleanup();
        } else {
            names = annotation.name();
        }

        boolean none = (names.length == 1) && isEmpty(names[0]);
        if (none) {
            this.names = new String[0];
        } else {
            this.names = names;
        }
    }

    private static boolean isEmpty(CharSequence cs) {
        return cs == null || cs.length() == 0;
    }
}
