package com.example.myapplication.appComponents

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun CurvedButton(
    onAddClicked: () -> Unit,
    onRemoveClicked: () -> Unit,
    quantity: Int
) {

    val interactionSource = remember { MutableInteractionSource() }
    Surface(
        shape = RoundedCornerShape(MaterialTheme.shapes.extraLarge.topStart),
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .height(28.dp),
        border = BorderStroke(1.dp, Color(0xff07a8c1))
    ) {
        Row(
            Modifier.wrapContentWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(5.dp))
            Icon(
                imageVector = Icons.Default.Remove, contentDescription = null,
                modifier = Modifier.size(20.dp).clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    onRemoveClicked()


                }
            )

            VerticalDivider(modifier = Modifier.width(1.dp))
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = quantity.toString(), textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.width(5.dp))
            VerticalDivider(modifier = Modifier.width(1.dp))

            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null) {
                        onAddClicked()
                    }
            )
            Spacer(modifier = Modifier.width(5.dp))

        }

    }
}