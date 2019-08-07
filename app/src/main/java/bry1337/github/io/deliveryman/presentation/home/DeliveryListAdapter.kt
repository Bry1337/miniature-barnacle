package bry1337.github.io.deliveryman.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import bry1337.github.io.deliveryman.R
import bry1337.github.io.deliveryman.databinding.ItemDeliveryBinding
import bry1337.github.io.deliveryman.injection.module.GlideApp
import bry1337.github.io.deliveryman.model.Delivery
import bry1337.github.io.deliveryman.model.Location
import bry1337.github.io.deliveryman.util.OnBindViewHolder

/**
 * Created by edwardbryan.abergas on 08/07/2019.
 *
 * @author edwardbryan.abergas@gmail.com
 */
typealias DeliveryClickListener = (Delivery) -> Unit
class DeliveryListAdapter : RecyclerView.Adapter<DeliveryListAdapter.DeliveryViewHolder>() {

  private lateinit var clickListener: DeliveryClickListener
  private lateinit var deliveryList: List<Delivery>

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryViewHolder {
    val binding: ItemDeliveryBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
        R.layout.item_delivery, parent, false)
    return DeliveryViewHolder(binding)
  }

  override fun getItemCount(): Int {
    return if (::deliveryList.isInitialized) deliveryList.size else 0
  }

  override fun onBindViewHolder(holder: DeliveryViewHolder, position: Int) {
    holder.onBind(deliveryList[position])
    holder.itemView.setOnClickListener { clickListener(deliveryList[position]) }
  }

  fun updateDeliveryList(deliveryList: List<Delivery>) {
    this.deliveryList = deliveryList
    notifyDataSetChanged()
  }

  fun setDeliveryClickListener(deliveryClickListener: DeliveryClickListener){
    this.clickListener = deliveryClickListener
  }

  class DeliveryViewHolder(private val binding: ItemDeliveryBinding) : RecyclerView.ViewHolder(
      binding.root), OnBindViewHolder<Delivery> {

    private val viewModel = DeliveryViewModel()
    private var ivImage = binding.ivImage

    override fun onBind(obj: Delivery) {
      viewModel.bind(obj)
      binding.viewModel = viewModel
      GlideApp.with(binding.root).load(obj.imageUrl).centerCrop().into(ivImage)
    }
  }

}