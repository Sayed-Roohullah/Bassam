package com.acclivousbyte.bassam.koinDI

import com.acclivousbyte.bassam.viewModel.PersonDetailVewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val persondetailModule = module {
    viewModel {
        PersonDetailVewModel(get())
    }

}