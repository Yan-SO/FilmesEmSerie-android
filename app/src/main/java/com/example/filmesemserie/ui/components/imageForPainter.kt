package com.example.filmesemserie.ui.components

import android.graphics.BitmapFactory
import android.util.Base64
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.example.filmesemserie.R
import com.example.filmesemserie.models.ItemModel

@Composable
fun imageForPainter(item: ItemModel):Painter {
    val image = if (item.imagem != null) {
        val imageBytes = Base64.decode(item.imagem, Base64.DEFAULT)
        val imageBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)?.asImageBitmap()
        BitmapPainter(imageBitmap ?: ImageBitmap(1, 1))
    } else {
        painterResource(R.drawable.item_sem_imagem)
    }
    return image
}