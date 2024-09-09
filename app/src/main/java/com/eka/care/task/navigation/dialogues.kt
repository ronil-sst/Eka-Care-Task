package com.eka.care.task.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.AlertDialogDefaults
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProgressDialogue() {
    BasicAlertDialog(onDismissRequest = {

    }) {
        Surface(
            modifier = Modifier
                .border(
                    0.dp,
                    color = Color.Transparent,
                    shape = RoundedCornerShape(10.dp)
                )
                .height(100.dp)
                .width(50.dp),
            color = Color.Transparent,

            tonalElevation = AlertDialogDefaults.TonalElevation,
            shape = RoundedCornerShape(10.dp)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(100.dp)
                    .background(Color.Transparent)
                    .width(50.dp)
                    .border(
                        0.dp,
                        color = Color.Transparent,
                        shape = RoundedCornerShape(10.dp)
                    )
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .height(100.dp)
                        .width(150.dp)
                        .clip(
                            shape = RoundedCornerShape(20.dp)
                        )
                        .background(Color.White)


                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .height(50.dp)
                            .width(50.dp)
                    )
                }
            }
        }
    }
}