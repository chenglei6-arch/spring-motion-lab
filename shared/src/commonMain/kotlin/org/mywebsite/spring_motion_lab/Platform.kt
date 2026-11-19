package org.mywebsite.spring_motion_lab

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform