package com.eka.care.task.screens


import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.eka.care.task.R
import com.eka.care.task.room.models.EntryModel
import com.eka.care.task.utils.Status
import com.eka.care.task.viewmodels.AddEntryVM
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date

@Composable
fun AddEntry(showProgress: (Boolean) -> Unit, onSuccess: () -> Unit) {
    val viewmodel: AddEntryVM = hiltViewModel()
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }


    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    val snackBar = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBar)
        },
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(contentPadding)
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(painter = painterResource(id = R.drawable.logo), contentDescription = null)

            Spacer(modifier = Modifier.height(30.dp))

            Text("User Information", fontSize = 24.sp, modifier = Modifier.padding(bottom = 16.dp))
            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Name") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next)
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = age,
                onValueChange = { age = it },
                label = { Text("Age") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = dob,
                onValueChange = { dob = it },
                label = { Text("Date of Birth") },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        showDataPicker(context = context) {
                            dob = it
                        }

                    },
                enabled = false,
                colors = TextFieldDefaults.colors(
                    disabledTextColor = Color.Black,
                    disabledIndicatorColor = Color.Gray,
                    disabledLabelColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = address,
                onValueChange = { address = it },
                label = { Text("Address") },
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done)
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    checkValidations(name, age, dob, address, {
                        scope.launch {
                            snackBar.showSnackbar(it)
                        }
                    }) {
                        viewmodel.addEntry(EntryModel(name, age, dob, address)) {
                            showProgress(it.status == Status.LOADING)
                            if (it.status == Status.SUCCESS) {
                                scope.launch {
                                    it.message?.let { it1 -> snackBar.showSnackbar(it1) }
                                }
                                onSuccess()
                            } else if (it.status == Status.ERROR) {
                                scope.launch {
                                    it.message?.let { it1 -> snackBar.showSnackbar(it1) }
                                }
                            }
                        }
                    }
                    // Handle submit action
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Text("Submit", fontSize = 18.sp)
            }
            Spacer(modifier = Modifier.height(50.dp))

        }
    }
}

fun showDataPicker(context: Context, selectedDate: (String) -> Unit) {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDay: Int ->
            val dob = "$selectedDay/${selectedMonth + 1}/$selectedYear"
            selectedDate(dob)
        }, year, month, day
    )
    datePickerDialog.datePicker.maxDate = Date().time

    datePickerDialog.show()

}

fun checkValidations(
    name: String,
    age: String,
    dob: String,
    address: String,
    error: (String) -> Unit,
    success: () -> Unit,
) {
    if (name.isEmpty()) {
        error("Please Enter the name")
    } else if (age.isEmpty()) {
        error("Please Enter the age")
    } else if ((age.toIntOrNull() ?: 0) < 0 || (age.toIntOrNull() ?: 0) > 100) {
        error("Please Enter a valid age")
    } else if (dob.isEmpty()) {
        error("Please Enter the dob")
    } else if (address.isEmpty()) {
        error("Please Enter the address")
    } else {
        success()
    }

}

