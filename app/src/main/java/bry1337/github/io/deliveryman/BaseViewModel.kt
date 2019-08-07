package bry1337.github.io.deliveryman

import androidx.lifecycle.ViewModel
import bry1337.github.io.deliveryman.injection.component.DaggerViewModelInjector
import bry1337.github.io.deliveryman.injection.component.ViewModelInjector
import bry1337.github.io.deliveryman.injection.module.NetworkModule
import bry1337.github.io.deliveryman.presentation.home.HomeViewModel

/**
 * Created by edwardbryan.abergas on 08/07/2019.
 *
 * @author edwardbryan.abergas@gmail.com
 */
abstract class BaseViewModel : ViewModel() {

  private val injector: ViewModelInjector = DaggerViewModelInjector.builder().networkModule(NetworkModule).build()

  init {
    inject()
  }

  private fun inject() {
    when (this) {
      is HomeViewModel -> injector.inject(this)
    }
  }
}