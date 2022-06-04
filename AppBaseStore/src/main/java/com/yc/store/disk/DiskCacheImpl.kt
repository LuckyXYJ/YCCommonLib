package com.yc.store.disk

import android.util.Log
import com.tencent.mmkv.MMKV
import com.yc.store.ICacheable
import java.io.File

class DiskCacheImpl(var builder: Builder) : ICacheable {

    private var mmkv: MMKV = MMKV.mmkvWithID(builder.fileName) ?: MMKV.defaultMMKV()!!
    private var fileName: String? = builder.fileName

    companion object {
        var rootPath: String? = null
        fun initRootPath(path: String) {
            rootPath = MMKV.initialize(path)
        }
    }

    class Builder {
        var fileName: String? = null
        fun setFileId(name: String): Builder {
            fileName = name
            return this
        }

        fun build(): DiskCacheImpl {
            return DiskCacheImpl(this)
        }
    }


    override fun saveInt(key: String, value: Int) {
        mmkv.encode(key, value)
    }

    override fun readInt(key: String, default: Int): Int {
        return mmkv.decodeInt(key, default)
    }

    override fun saveFloat(key: String, value: Float) {
        mmkv.encode(key, value)
    }

    override fun readFloat(key: String, default: Float): Float {
        return mmkv.decodeFloat(key, default)
    }

    override fun saveLong(key: String, value: Long) {
        mmkv.encode(key, value)
    }

    override fun readLong(key: String, default: Long): Long {
        return mmkv.decodeLong(key, default)
    }

    override fun saveString(key: String, value: String) {
        mmkv.encode(key, value)
    }

    override fun readString(key: String, default: String): String {
        return mmkv.decodeString(key, default) ?: default
    }

    override fun saveBoolean(key: String, value: Boolean) {
        mmkv.encode(key, value)
    }

    override fun readBoolean(key: String, default: Boolean): Boolean {
        return mmkv.decodeBool(key, default)
    }

    override fun removeKey(key: String) {
        mmkv.removeValueForKey(key)
    }

    override fun totalSize(): Long {
        return mmkv.totalSize()
    }

    override fun clearData() {
        File(rootPath, fileName).apply {
            if (exists()) {
                Log.d("DiskCacheImpl","before fileSize:" +
                        "${this.length() / 1024}K,path:${this.absolutePath}")
            }
        }
        mmkv.clearAll()
        File(rootPath, fileName).apply {
            if (exists()) {
                Log.d("DiskCacheImpl","after fileSize:" +
                        "${this.length() / 1024}K,path:${this.absolutePath}")
            }
        }
    }
}