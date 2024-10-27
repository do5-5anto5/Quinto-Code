package com.do55anto5.quinto_code.presenter.components.bottom_sheet

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.do55anto5.quinto_code.R
import com.do55anto5.quinto_code.presenter.components.button.PrimaryButton
import com.do55anto5.quinto_code.presenter.theme.QuintoCodeTheme

@Composable
fun PhotoBottomSheetUI(
    bitmap: Bitmap?,
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit
) {
    if (bitmap == null) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                stringResource(R.string.empty_image_camera_screen),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = QuintoCodeTheme.colorScheme.textColor
            )
        }
        Spacer(modifier = Modifier.height(50.dp))
    } else {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp, bottom = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = stringResource(R.string.title_photo_bottom_sheet),
                style = TextStyle(
                    fontSize = 24.sp,
                    lineHeight = 28.8.sp,
                    fontWeight = FontWeight(700),
                    color = QuintoCodeTheme.colorScheme.textColor
                )
            )

            HorizontalDivider(
                modifier = Modifier
                    .padding(vertical = 24.dp),
                color = Color(0xffeeeeee)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                    Image(
                        bitmap = bitmap.asImageBitmap(),
                        contentDescription = null,
                        modifier = Modifier
                            .size(width = 120.dp, height = 120.dp)
                            .clip(RoundedCornerShape(30.dp)),
                        contentScale = ContentScale.Crop
                    )
            }

            HorizontalDivider(
                modifier = Modifier
                    .padding(vertical = 24.dp),
                color = Color(0xffeeeeee)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                PrimaryButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .height(58.dp)
                        .weight(1f),
                    text = stringResource(R.string.button_text_cancel_photo_bottom_sheet)
                )

                Spacer(modifier = Modifier.width(12.dp))

                PrimaryButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .height(58.dp)
                        .weight(1f),
                    text = stringResource(R.string.button_text_save_photo_bottom_sheet)
                )
            }
        }
    }
}