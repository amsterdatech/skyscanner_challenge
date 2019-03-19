package com.dutchtechnologies.skyscanner_challenge.mapper

interface Mapper<V, D> {

    fun mapToView(type: D): V

    fun mapFromView(type: V?): D

}