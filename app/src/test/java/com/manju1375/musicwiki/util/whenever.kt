package com.manju1375.musicwiki.util

import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing

/**
     * Method used so that we don't have to keep writing back-ticks when setting up mocks
     * during unit testing.
     */
    fun <T> whenever(methodCall: T): OngoingStubbing<T> = Mockito.`when`(methodCall)