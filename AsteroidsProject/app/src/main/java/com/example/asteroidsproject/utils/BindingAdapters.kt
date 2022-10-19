package com.example.asteroidsproject.utils

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.asteroidsproject.R
import com.example.asteroidsproject.model.Asteroid
import com.example.asteroidsproject.model.PictureOfDay
import com.example.asteroidsproject.ui.adapter.AsteroidRecyclerAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("statusIcon")
fun bindAsteroidStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.ic_status_potentially_hazardous)
    } else {
        imageView.setImageResource(R.drawable.ic_status_normal)
    }
}

@BindingAdapter("asteroidStatusImage")
fun bindDetailsStatusImage(imageView: ImageView, isHazardous: Boolean) {
    if (isHazardous) {
        imageView.setImageResource(R.drawable.asteroid_hazardous)
    } else {
        imageView.setImageResource(R.drawable.asteroid_safe)
    }
}

@BindingAdapter("astronomicalUnitText")
fun bindTextViewToAstronomicalUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.astronomical_unit_format), number)
}

@BindingAdapter("kmUnitText")
fun bindTextViewToKmUnit(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_unit_format), number)
}

@BindingAdapter("velocityText")
fun bindTextViewToDisplayVelocity(textView: TextView, number: Double) {
    val context = textView.context
    textView.text = String.format(context.getString(R.string.km_s_unit_format), number)
}

//@BindingAdapter("pictureOfDayImage")
//fun bindPictureOfDay(imageView: ImageView, pictureOfDay: PictureOfDay?) {
//    if (pictureOfDay != null && pictureOfDay.url.isNotBlank()) {
//        val imgUri = pictureOfDay.url.toUri().buildUpon().scheme("https").build()
//        Picasso.with(imageView.context)
//            .load(imgUri)
//            .placeholder(R.drawable.loading_animation)
//            .error(R.drawable.ic_broken_image)
//            .fit()
//            .centerCrop()
//            .into(imageView)
//
//        val contentDescription =
//            String.format(
//                imageView.context.getString(R.string.nasa_picture_of_day_content_description_format),
//                pictureOfDay.title
//            )
//        imageView.contentDescription = contentDescription
//    } else {
//        imageView.setImageResource(R.drawable.ic_broken_image)
//        imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
//        imageView.contentDescription =
//            imageView.context.getString(R.string.this_is_nasa_s_picture_of_day_showing_nothing_yet)
//    }
//
//}

@BindingAdapter("pictureOfDayImage")
fun bindPictureOfDay(imageView: ImageView, pictureOfDay: PictureOfDay?) {
    val context = imageView.context
    if (pictureOfDay != null && pictureOfDay.url.isNotBlank()) {
        Picasso.with(context)
            .load(pictureOfDay.url)
            .placeholder(R.drawable.placeholder_picture_of_day)
            .error(R.drawable.ic_broken_image)
            .fit()
            .centerCrop()
            .into(imageView)

        val contentDescription =
            String.format(
                context.getString(R.string.nasa_picture_of_day_content_description_format),
                pictureOfDay.title
            )
        imageView.contentDescription = contentDescription
    } else {
        imageView.setImageResource(R.drawable.ic_broken_image)
        imageView.scaleType = ImageView.ScaleType.CENTER_INSIDE
        imageView.contentDescription =
            context.getString(R.string.this_is_nasa_s_picture_of_day_showing_nothing_yet)
    }
}

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Asteroid>?) {
    val adapter = recyclerView.adapter as AsteroidRecyclerAdapter
    adapter.submitList(data)
}

/*
@BindingAdapter("asteroidApiStatus")
fun bindStatus(statusImageView: ImageView, status: AsteroidApiStatus?) {
    when (status) {
        AsteroidApiStatus.LOADING -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.loading_animation)
        }
        AsteroidApiStatus.ERROR -> {
            statusImageView.visibility = View.VISIBLE
            statusImageView.setImageResource(R.drawable.ic_connection_error)
        }
        AsteroidApiStatus.DONE -> {
            statusImageView.visibility = View.GONE
        }
    }
}*/
