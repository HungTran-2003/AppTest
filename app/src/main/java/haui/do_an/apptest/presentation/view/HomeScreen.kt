package haui.do_an.apptest.presentation.view

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import haui.do_an.apptest.presentation.UiState
import haui.do_an.apptest.presentation.view.common.ErrorMessage
import haui.do_an.apptest.presentation.view.components.ItemAddress
import haui.do_an.apptest.presentation.viewmodel.HomeViewmodel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewmodel = hiltViewModel()
) {
    var searchText by remember { mutableStateOf("") }
    var loading by remember { mutableStateOf(false) }
    val searchResults by viewModel.address.collectAsState()
    val uiState by viewModel.uiState.collectAsState()
    var showErrorDialog by remember { mutableStateOf(false) }

    val context = LocalContext.current

    when(uiState){
        is UiState.Loading ->{
            loading = true
        }

        is UiState.Error -> {
            loading = false
            showErrorDialog = true
        }
        UiState.Initial -> {
            loading = false
        }
    }

    LazyColumn(
        modifier = modifier
    ) {
        item {
            OutlinedTextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                    viewModel.search(it)  },
                modifier = Modifier.fillMaxWidth()
                    .padding(12.dp),
                shape = RoundedCornerShape(50.dp),
                placeholder = {Text("Enter keyword")},
                leadingIcon = {
                    if (!loading){
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    } else{
                        CircularProgressIndicator()
                    }

                },
                trailingIcon = {
                    if (searchText.isNotEmpty()){
                        IconButton(
                            onClick = {
                                searchText = ""
                                viewModel.resetSearchResult()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear"
                            )
                        }
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    disabledBorderColor = Color.Transparent,
                    errorBorderColor = Color.Transparent,
                    focusedContainerColor = Color(0xFFF5F5F5),   // Nền xám nhạt khi focus
                    unfocusedContainerColor = Color(0xFFF5F5F5), // Nền xám nhạt khi unfocus
                )

            )
        }
        items(searchResults){ address ->
            ItemAddress(
                address = address,
                keyword = searchText,
                onItemClick = {
                    val gmmIntentUri = Uri.parse("google.navigation:q=${address.lat},${address.lon}")
                    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                    mapIntent.setPackage("com.google.android.apps.maps")
                    context.startActivity(mapIntent)
                }
            )
        }
    }

    ErrorMessage(
        show = showErrorDialog,
        message = (uiState as? UiState.Error)?.message ?: "",
        onDismiss = {
            showErrorDialog = false
            viewModel.resetUiState()
        }
    )
}