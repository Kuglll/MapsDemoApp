package com.example.mapsdemoapp.ui.shared


import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mapsdemoapp.R

@Composable
fun Dialog(
    modifier: Modifier = Modifier,
    title: String,
    description: String? = null,
    onConfirmClick: () -> Unit,
    confirmText: String,
    dismissText: String? = null,
    onDismissClick: () -> Unit = {},
    onDismissRequest: () -> Unit,
) {
    AlertDialog(
        modifier = modifier,
        onDismissRequest = onDismissRequest,
        shape = RoundedCornerShape(2.dp),
        title = {
            Text(
                modifier = Modifier.padding(bottom = 20.dp),
                text = title,
                style = MaterialTheme.typography.h6,
            )
        },
        text = {
            description?.let {
                Text(
                    text = description,
                    style = MaterialTheme.typography.body1
                )
            }
        },
        confirmButton = {
            TextButton(
                onClick = onConfirmClick,
            ) {
                Text(
                    text = confirmText.uppercase(),
                    style = MaterialTheme.typography.button
                )
            }
        },
        dismissButton = {
            dismissText?.let {
                TextButton(onClick = onDismissClick) {
                    Text(
                        text = dismissText.uppercase(),
                        color = Color.Gray,
                        style = MaterialTheme.typography.button
                    )
                }
            }
        },
    )
}

@Composable
fun Dialog(
    modifier: Modifier = Modifier,
    @StringRes titleId: Int,
    @StringRes descriptionId: Int? = null,
    onConfirmClick: () -> Unit,
    @StringRes confirmTextId: Int = R.string.ok,
    @StringRes dismissTextId: Int? = null,
    onDismissClick: () -> Unit = {},
    onDismissRequest: () -> Unit,
) {
    Dialog(
        modifier = modifier,
        title = stringResource(id = titleId),
        description = descriptionId?.let { stringResource(it) },
        onConfirmClick = onConfirmClick,
        confirmTextId = confirmTextId,
        dismissTextId = dismissTextId,
        onDismissClick = onDismissClick,
        onDismissRequest = onDismissRequest,
    )
}

@Composable
fun Dialog(
    modifier: Modifier = Modifier,
    title: String,
    description: String? = null,
    onConfirmClick: () -> Unit,
    @StringRes confirmTextId: Int = R.string.ok,
    @StringRes dismissTextId: Int? = null,
    onDismissClick: () -> Unit = {},
    onDismissRequest: () -> Unit,
) {
    Dialog(
        modifier = modifier,
        title = title,
        description = description,
        onConfirmClick = onConfirmClick,
        confirmText = stringResource(id = confirmTextId),
        dismissText = dismissTextId?.let { stringResource(id = it) },
        onDismissClick = onDismissClick,
        onDismissRequest = onDismissRequest,
    )
}

@Composable
fun ErrorDialog(
    modifier: Modifier = Modifier,
    title: String,
    description: String? = null,
    confirmText: Int = R.string.ok,
    onConfirmClick: () -> Unit,
    onDismissRequest: () -> Unit,
) = Dialog(
    title = title,
    description = description,
    onConfirmClick = onConfirmClick,
    confirmTextId = confirmText,
    modifier = modifier,
    onDismissRequest = onDismissRequest,
)

@Composable
fun ErrorDialog(
    modifier: Modifier = Modifier,
    @StringRes titleId: Int,
    @StringRes descriptionId: Int? = null,
    confirmText: Int = R.string.ok,
    onConfirmClick: () -> Unit,
    onDismissRequest: () -> Unit,
) = Dialog(
    titleId = titleId,
    descriptionId = descriptionId,
    onConfirmClick = onConfirmClick,
    confirmTextId = confirmText,
    modifier = modifier,
    onDismissRequest = onDismissRequest,
)
