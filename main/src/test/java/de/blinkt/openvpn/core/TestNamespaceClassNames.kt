/*
 * Copyright (c) 2012-2018 Arne Schwabe
 * Distributed under the GNU GPL v2 with additional terms. For full terms see the file doc/LICENSE.txt
 */

package de.blinkt.openvpn.core

import android.content.ComponentName
import android.os.Build
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class TestNamespaceClassNames {
    @Test
    fun namespaceComesFromBuildConfig() {
        assertEquals("de.blinkt.openvpn", NamespaceClassNames.namespace())
    }

    @Test
    fun remoteActionsAreRelativeToNamespaceForBothApplicationIds() {
        val actions = listOf("ConnectVPN", "DisconnectVPN", "PauseVPN", "ResumeVPN", "SetDefaultVPN")
        for (applicationId in listOf("ru.arogaz.openvpn", "de.blinkt.openvpn")) {
            for (action in actions) {
                val component = ComponentName(applicationId, "de.blinkt.openvpn.api.$action")
                assertEquals(".api.$action", NamespaceClassNames.relativeName(component.className))
            }
        }
    }

    @Test
    fun shortClassNameDoesNotUseNamespaceForForkApplicationId() {
        val component = ComponentName("ru.arogaz.openvpn", "de.blinkt.openvpn.api.ConnectVPN")
        assertNotEquals(".api.ConnectVPN", component.shortClassName)
    }

    @Test
    fun classesOutsideNamespaceAreUnchanged() {
        assertEquals("com.example.Foo", NamespaceClassNames.relativeName("com.example.Foo"))
        assertEquals("de.blinkt.openvpnx.Foo", NamespaceClassNames.relativeName("de.blinkt.openvpnx.Foo"))
    }

    @Test(expected = IllegalArgumentException::class)
    fun classNameRequiresLeadingDot() {
        NamespaceClassNames.className("activities.MainActivity")
    }

    @Test
    fun uiActivityClassesCanBeLoaded() {
        Class.forName(NamespaceClassNames.className(".activities.MainActivity"))
        Class.forName(NamespaceClassNames.className(".activities.CredentialsPopup"))
    }
}
