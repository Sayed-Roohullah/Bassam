package com.acclivousbyte.bassam.koinDI

import com.acclivousbyte.bassam.utils.Repository
import org.koin.dsl.module

val repoModule = module {
    single { Repository(get()) }
}