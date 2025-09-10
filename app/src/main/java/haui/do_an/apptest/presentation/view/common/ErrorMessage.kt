package haui.do_an.apptest.presentation.view.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun ErrorMessage(
    show: Boolean,
    message: String,
    onDismiss: () -> Unit
) {
    if (show){
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text("Error") },
            text = { Text(message) },
            confirmButton = {
                TextButton(onClick = onDismiss) {
                    Text("OK")
                }
            }
        )
    }
}