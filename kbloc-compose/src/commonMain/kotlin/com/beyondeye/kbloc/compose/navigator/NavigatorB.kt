package com.beyondeye.kbloc.compose.navigator

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.lifecycle.ScreenDisposable
import cafe.adriel.voyager.core.lifecycle.ScreenLifecycleStore
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.NavigatorContent
import cafe.adriel.voyager.navigator.NavigatorDisposeBehavior
import cafe.adriel.voyager.navigator.OnBackPressed
import cafe.adriel.voyager.navigator.compositionUniqueId
import com.beyondeye.kbloc.compose.internal.BlocStore
import com.beyondeye.kbloc.compose.internal.LocalBlocStoreOwner
import com.beyondeye.kbloc.core.Bloc

/**
 * handle dispose of blocs associated (created inside) to a specific screen, when the screen is removed from navigation stack
 */
private class BlocStoreScreenLifecycleOwner(val bso: BlocStore) : ScreenDisposable {
    override fun onDispose(screen: Screen) {
        bso.remove(screen)
    }
}

/**
 * Navigator with support for automatic removal of [Bloc]s associated to a specific screen
 * when the screen is removed from the screen stack.
 */
@Composable
public fun NavigatorB(
    screen: Screen,
    disposeBehavior: NavigatorDisposeBehavior = NavigatorDisposeBehavior(),
    onBackPressed: OnBackPressed = { true },
    key: String = compositionUniqueId(),
    content: NavigatorContent = { CurrentScreen() }
) {
    NavigatorB(
        screens = listOf(screen),
        disposeBehavior = disposeBehavior,
        onBackPressed = onBackPressed,
        key = key,
        content = content
    )
}

/**
 * Navigator with support for automatic removal of [Bloc]s associated to a specific screen
 * when the screen is removed from the screen stack.
 * see https://voyager.adriel.cafe/lifecycle#screendisposable-for-all-screens
 */
@Composable
public fun NavigatorB(
    screens: List<Screen>,
    disposeBehavior: NavigatorDisposeBehavior = NavigatorDisposeBehavior(),
    onBackPressed: OnBackPressed = { true },
    key: String = compositionUniqueId(),
    content: NavigatorContent = { CurrentScreen() }
) {
    Navigator(
        screens,
        disposeBehavior,
        onBackPressed,
        key
    ) { navigator ->
        val bso = LocalBlocStoreOwner.current
        remember(navigator.lastItem) {
            // add handler that whenever last screen changes, add onDispose handler for it
            ScreenLifecycleStore.get(navigator.lastItem) { screenKey->
                BlocStoreScreenLifecycleOwner(bso.blocStore)
            }
        }
        content(navigator)
    }
}
