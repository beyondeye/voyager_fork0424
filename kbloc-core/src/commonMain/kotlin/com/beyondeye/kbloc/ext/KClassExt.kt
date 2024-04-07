package com.beyondeye.kbloc.ext
/**
 * the code in this file  is partially derived from KClassExt in https://github.com/InsertKoinIO/koin
 */
import cafe.adriel.voyager.core.platform.multiplatformName
import kotlin.reflect.KClass


/**
 * Give full class qualifier
 * TODO search usage of this: if possible, override this with some user defined shorter key, like screenkey
 */
public fun KClass<*>.getFullName(): String {
    return multiplatformName ?: error("Default key not found, please provide your own key")
}

/*
public fun KClass<*>.getFullName(): String {
    return return classNames[this] ?: saveCache()
}
 */


/*
//TODO add here some processing of name to make it shorter/ more efficient (like hashids?)
//also how I can check that KBlocPlatformTools.getClassName return a unique name for each class i am using?
private fun KClass<*>.saveCache(): String {
    val name = KBlocPlatformTools.getClassName(this)
    classNames[this] = name
    return name
}

private val classNames: MutableMap<KClass<*>, String> = KBlocPlatformTools.safeHashMap()
 */
