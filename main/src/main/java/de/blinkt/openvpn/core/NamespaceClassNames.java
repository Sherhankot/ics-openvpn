/*
 * Copyright (c) 2012-2016 Arne Schwabe
 * Distributed under the GNU GPL v2 with additional terms. For full terms see the file doc/LICENSE.txt
 */

package de.blinkt.openvpn.core;

import de.blinkt.openvpn.BuildConfig;

/**
 * Component class names always belong to the namespace. A build's applicationId
 * may differ from the namespace, for example when a fork uses its own applicationId.
 * Context.getPackageName() returns the applicationId.
 */
public final class NamespaceClassNames {
    private NamespaceClassNames() {
    }

    public static String namespace() {
        String buildConfigClassName = BuildConfig.class.getName();
        return buildConfigClassName.substring(0, buildConfigClassName.length() - ".BuildConfig".length());
    }

    public static String className(String relative) {
        if (!relative.startsWith(".")) {
            throw new IllegalArgumentException("Relative class name must start with a dot");
        }
        return namespace() + relative;
    }

    public static String relativeName(String className) {
        String namespace = namespace();
        if (className.startsWith(namespace + ".")) {
            return className.substring(namespace.length());
        }
        return className;
    }
}
