package com.acclivousbyte.bassam.koinDI

import com.acclivousbyte.bassam.viewModel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel {
        HomeViewModel(get())
    }

}