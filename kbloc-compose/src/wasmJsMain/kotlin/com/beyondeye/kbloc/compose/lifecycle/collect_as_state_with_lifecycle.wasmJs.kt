package com.beyondeye.kbloc.compose.lifecycle

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import kotlinx.coroutines.flow.Flow
import kotlin.coroutines.CoroutineContext

/**
 * same as [collectAsState] but automatically pause flow collection when app is paused
 * see https://medium.com/androiddevelopers/consuming-flows-safely-in-jetpack-compose-cde014d0d5a3
 * this is a multiplatform function, platform specific implementation
 * this was not present in original voyager
 */
@Composable
public actual fun <T : R, R> Flow<T>.mp_collectAsStateWithLifecycle(
    initial: R,
    context: CoroutineContext
): State<R> {
    TODO("Not yet implemented")
}
