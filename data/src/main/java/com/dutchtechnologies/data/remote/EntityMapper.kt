package com.dutchtechnologies.data.remote

interface EntityMapper<in M, out E> {

    fun mapFromRemote(type: M): E

}
