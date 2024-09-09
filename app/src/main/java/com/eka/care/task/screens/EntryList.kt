package com.eka.care.task.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.eka.care.task.navigation.Screens
import com.eka.care.task.navigation.getStateFlow
import com.eka.care.task.navigation.navigateTo
import com.eka.care.task.room.models.EntryModel
import com.eka.care.task.ui.theme.AppColors
import com.eka.care.task.viewmodels.EntryListVM
import kotlinx.coroutines.flow.collectLatest

@Composable
fun EntryList(
    showProgress: (Boolean) -> Unit,
    navController: NavHostController,
    viewmodel: EntryListVM = hiltViewModel(),
) {

    val value = remember {
        navController.getStateFlow("needRefresh", false)?.value
    }

    if (value == true) {
        viewmodel.getAllEntries()
    }
    val entries by viewmodel.entries.collectAsState()
    LaunchedEffect(key1 = true) {
        viewmodel.showLoading.collectLatest {
            showProgress(it)
        }
    }
    val snackBar = remember { SnackbarHostState() }
    Scaffold(
        floatingActionButton = {
            Button(
                onClick = { navController.navigateTo(Screens.AdEntry) },
                modifier = Modifier.padding(5.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "Add")
                }
            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBar)
        },
    ) { contentPadding ->
        if (entries.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = "No Data Found")
            }
        } else {

            LazyColumn(
                modifier = Modifier
                    .padding(contentPadding)
                    .fillMaxSize()
//                .scrollable(scrollableState, Orientation.Vertical)
            ) {
                items(entries.size) {
                    val item = entries[it]
                    UserModel(item)
                }
            }
        }
    }

}

@Composable
fun UserModel(model: EntryModel) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(6.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(
                AppColors.accentColor
                    .copy(.2f)
            )
            .padding(6.dp)
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .clip(CircleShape)
                .height(50.dp)
                .background(AppColors.backgroundColor.copy(.5f))
                .aspectRatio(1f)
        ) {
            Text(
                text = model.name.first().toString().toUpperCase(Locale.current),
                color = Color.Black,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.width(5.dp))
        Column {
            Text(
                text = "Name: ${model.name}",
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Age :${model.age}",
                fontSize = 15.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Address: ${model.address}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Date of birth : ${model.dob}",
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Black
            )
        }

    }
}

@Preview
@Composable
private fun MyPreview() {
    UserModel(EntryModel("Ronil", "19", "Jagatpura near akshayapatra", "30 dec 2001"))
}