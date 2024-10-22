package com.do55anto5.quinto_code.presenter.components.bottom_sheet

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.PreviewLightDark
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.do55anto5.quinto_code.R
import com.do55anto5.quinto_code.presenter.components.button.PrimaryButton
import com.do55anto5.quinto_code.presenter.theme.QuintoCodeTheme

@Composable
fun BottomSheetUI(
    title: String,
    description: String,
    btnCancel: String,
    btnConfirm: String,
    onCancelClick: () -> Unit,
    onConfirmClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(QuintoCodeTheme.colorScheme.backgroundColor)
            .padding(start = 24.dp, end = 24.dp, bottom = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            Text(
                text = stringResource(R.string.bottom_sheet_title),
                fontWeight = FontWeight.SemiBold,
                fontSize = 22.sp,
                color = QuintoCodeTheme.colorScheme.textColor
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 16.dp),
                color = QuintoCodeTheme.colorScheme.alphaDefaultColor,
                thickness = 2.dp
            )

            Text(
                text = stringResource(R.string.bottom_sheet_description),
                fontSize = 18.sp,
                color = QuintoCodeTheme.colorScheme.textColor
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                content ={
                    PrimaryButton(
                        text = btnCancel,
                        onClick = onCancelClick,
                        modifier = Modifier
                            .weight(1f)
                    )

                    Spacer(modifier = Modifier.size(12.dp))

                    PrimaryButton (
                        text = btnConfirm,
                        onClick = onConfirmClick,
                        modifier = Modifier
                            .weight(1f)
                    )
                }
            )
        }
    )
}

@PreviewLightDark
@Composable
private fun Preview() {
    QuintoCodeTheme {
        BottomSheetUI(
            title = "title",
            description = "description",
            btnCancel = "cancel",
            btnConfirm = "confirm",
            onCancelClick = {},
            onConfirmClick = {}
        )
    }
}