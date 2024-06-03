package com.beyondeye.kbloc.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisallowComposableCalls
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.screen.Screen
import com.beyondeye.kbloc.compose.internal.BindBlocs
import com.beyondeye.kbloc.compose.internal.BlocStore
import com.beyondeye.kbloc.compose.internal.rememberNewBlocForScreen
import com.beyondeye.kbloc.core.BlocBase
import kotlinx.coroutines.CoroutineScope

/**
 * define multiple bloc providers to make multiple Blocs available to some composable subtree
 * The syntax for defining the list is as follows:
 * BlocProviders().BlocProvider { scope -> BlocA() }.BlocProvider { scope -> BlocB() }.forContent { content() }
 * where content() is a composable function() for which we want the blocs made available
 * Any number of BlocProvider definitions is supported
 */
@Composable
public fun Screen.BlocProviders():_BlocProviderList {
    return _BlocProviderList(this)
}


/**
 * [blist] is a list of triples (Bloc:BlocBase<*>,bloc_tag:String,bloc_key:String)
 * if [resetOnScreenStart] is True then recreate bloc when entering (navigating to) the screen
 *                   otherwise bloc content is persistent between screens
 */
public class _BlocProviderList(public val screen: Screen, public val blist:MutableList<Triple<BlocBase<*>,String,String>> = mutableListOf()) {
    @Composable
    public inline fun <reified BlocA: BlocBase<*>> BlocProvider(
        blocTag: String? = null,
        resetOnScreenStart:Boolean=false,
        crossinline create: @DisallowComposableCalls (cscope: CoroutineScope) -> BlocA
    )    : _BlocProviderList
    {
        val (b,bkey)=screen.rememberNewBlocForScreen(blocTag,resetOnScreenStart,create)
        blist.add(Triple(b,blocTag?:"",bkey))
        return this
    }

    /**
     * separate method for most common use case without tag
     */
    @Composable
    public inline fun <reified BlocA: BlocBase<*>> BlocProviderExt(
        externallyProvidedBlock:BlocA,
    ): _BlocProviderList {
        return BlocProviderExtForTag(null,externallyProvidedBlock)
    }
    @Composable
    public inline fun <reified BlocA: BlocBase<*>> BlocProviderExtForTag(
        blocTag: String?,
        externallyProvidedBlock:BlocA,
    ): _BlocProviderList {
        val b= remember { externallyProvidedBlock }
        val bkey = BlocStore.getBlocKeyForUnboundBloc<BlocA>(blocTag)
        blist.add(Triple(b,blocTag?:"",bkey))
        return this
    }

    @Composable
    public fun ForContent(content:@Composable ()->Unit) {
        BindBlocs(blist,content)
        blist.clear()
    }
}
