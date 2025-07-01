package com.poc.videoplayerkmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform