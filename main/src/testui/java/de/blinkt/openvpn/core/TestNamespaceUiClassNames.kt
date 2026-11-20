/*
 * Copyright (c) 2012-2018 Arne Schwabe
 * Distributed under the GNU GPL v2 with additional terms. For full terms see the file doc/LICENSE.txt
 */

package de.blinkt.openvpn.core

import org.junit.Test

// The activities exist only in the ui flavor, so this check lives in testui.
class TestNamespaceUiClassNames {
    @Test
    fun uiActivityClassesCanBeLoaded() {
        Class.forName(NamespaceClassNames.className(".activities.MainActivity"))
        Class.forName(NamespaceClassNames.className(".activities.CredentialsPopup"))
    }
}
