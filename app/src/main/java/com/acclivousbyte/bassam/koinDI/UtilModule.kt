package com.acclivousbyte.bassam.koinDI

import com.acclivousbyte.bassam.utils.MaterialDialogHelper
import com.acclivousbyte.bassam.utils.Repository
import org.koin.dsl.module

val utilModule = module {

    single { MaterialDialogHelper() }

    single { Repository(get()) }

}