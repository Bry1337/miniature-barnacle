package bry1337.github.io.deliveryman.injection.component

import bry1337.github.io.deliveryman.injection.module.NetworkModule
import bry1337.github.io.deliveryman.presentation.home.HomeViewModel
import dagger.Component
import javax.inject.Singleton

/**
 * Created by edwardbryan.abergas on 08/07/2019.
 *
 * @author edwardbryan.abergas@gmail.com
 */
@Singleton
@Component(modules = [(NetworkModule::class)])
interface ViewModelInjector {

  fun inject(homeViewModel: HomeViewModel)

  @Component.Builder
  interface Builder {
    fun build(): ViewModelInjector

    fun networkModule(networkModule: NetworkModule): Builder
  }
}