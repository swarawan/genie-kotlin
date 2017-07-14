package com.swarawan.geniekotlin.database.local


/**
 * Created by rioswarawan on 7/13/17.
 */
class GlobalPreference {

    companion object {
        private val instance: CachePreference by lazy {
            CachePreference("Global")
        }

        @Synchronized fun <T> read(key: String, tClass: Class<T>): Any? {
            return instance.read(key, tClass)
        }

        @Synchronized fun <T> write(key: String, value: T, tClass: Class<T>) {
            return instance.write(key, value, tClass)
        }

        @Synchronized fun clear() {
            return instance.clear()
        }
    }
}