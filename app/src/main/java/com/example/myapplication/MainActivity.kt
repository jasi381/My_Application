package com.example.myapplication

import android.os.Bundle
import android.provider.MediaStore.Audio.Radio
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.appComponents.CurvedButton
import com.example.myapplication.appComponents.TopAppBarComponent
import com.example.myapplication.data.Specification
import com.example.myapplication.ui.theme.MyApplicationTheme
import com.example.myapplication.utils.parseJsonFromAsset
import com.example.myapplication.viewModel.SpecificationViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    MainApp()
                }
            }
        }
    }
}


@Composable
fun MainApp() {

    val context = LocalContext.current
    val itemsFromJson = parseJsonFromAsset(context)

    Scaffold(
        topBar = {
            TopAppBarComponent(title = "Make your own Package", onBackClick = {})
        }
    ) { paddingValues ->

        SpecificationList(
            specificationItems = itemsFromJson?.specifications ?: emptyList(),
            paddingValues = paddingValues
        )

    }

}

@Composable
fun SpecificationList(
    specificationItems: List<Specification>,
    paddingValues: PaddingValues,
    vm: SpecificationViewModel = viewModel()
) {

    Column(
        Modifier
            .fillMaxSize()
            .padding(paddingValues)
    ) {
        LazyColumn(
            Modifier
                .background(Color(0xfffefefe))
                .fillMaxSize()
                .weight(1f),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            items(specificationItems) {

                val choosingText =
                    if (it.name.firstOrNull() == "Apartment Size") "Choose 1" else "Choose up to 1"
                Column(
                    Modifier
                ) {
                    Text(
                        text = it.name.firstOrNull()!!,
                        modifier = Modifier.padding(start = 10.dp),
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = choosingText,
                        color= Color.Gray,
                        modifier = Modifier.padding(start = 10.dp),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal
                    )

                    it.list.forEach { item ->
                        Row(
                            Modifier.fillMaxWidth()
                        ) {

                            if (it.name.firstOrNull() == "Apartment Size"){
//                                RadioButton(selected =, onClick = { /*TODO*/ })
                                //Implement radio button here

                            }
                            else {
                                Checkbox(
                                    checked = vm.selectedItems[item._id] ?: false,
                                    onCheckedChange = {
                                        vm.toggleItemSelected(item._id, item.price)
                                    },
                                )
                            }
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ){
                                Text(
                                    text = item.name.firstOrNull()!!,
                                    maxLines = 2,
                                    modifier = Modifier
                                        .width(
                                            LocalConfiguration.current.screenWidthDp.dp * 0.6f
                                        )
                                        .offset(x = (-2).dp),
                                    overflow = TextOverflow.Ellipsis,
                                )
                                Column(
                                    modifier = Modifier.padding(end = 5.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    val quantity = vm.itemQuantities[item._id] ?: 1
                                    Text(text = "₹ ${item.price }",  modifier = Modifier.padding(end = 5.dp))
                                    if (vm.selectedItems[item._id] == true)
                                        CurvedButton(
                                            onAddClicked = {
                                                vm.updateItemQuantity(item._id, quantity + 1)
                                            },
                                            onRemoveClicked = {
                                                if (quantity > 1) {
                                                    vm.updateItemQuantity(item._id, quantity - 1)
                                                }
                                            },
                                            quantity = quantity
                                        )
                                }
                            }
                        }

                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    HorizontalDivider()

                }

            }


        }

        if (vm.totalPrice>0) {
            ElevatedCard(Modifier.padding(horizontal = 2.dp).fillMaxWidth().height(LocalConfiguration.current.screenWidthDp.dp* 0.2f)) {
                Text(
                    text = "Total Price: ${vm.totalPrice}",
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
    }
}

