package com.oliversolutions.dev.calcuonline.framework.datasources.local.cache

class HashMapCache {
    private val dataMap = HashMap<String, Any>()
    fun saveData(key: String, value: Any) {
        dataMap[key] = value
    }
    fun getData(key: String): Any? {
        return dataMap[key]
    }
}
