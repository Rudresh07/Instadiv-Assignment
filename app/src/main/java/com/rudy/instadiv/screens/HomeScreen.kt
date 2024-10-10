package com.rudy.instadiv.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.rudy.instadiv.component.TopBar
import com.rudy.instadiv.ui.theme.InstadivTheme
import com.rudy.instadiv.ui.theme.background
import com.rudy.instadiv.ui.theme.selectedItem
import com.rudy.instadiv.ui.theme.submitBtn
import com.rudy.instadiv.ui.theme.unselectedItem
import com.rudy.instadiv.viewmodel.TagRepository
import com.rudy.instadiv.viewmodel.TagViewModel

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HomeScreen(onSubmit: (String)->Unit)
{
    val vm = remember { TagViewModel(TagRepository()) }
    val tags by vm.tags.collectAsState()
    val selectedTag by vm.selectedTag.collectAsState()
    val context = LocalContext.current
    val isLoading by vm.isLoading.collectAsState()


    Scaffold(modifier = Modifier
        .fillMaxSize(),
        containerColor = background
        ){ innerPadding ->

        if (isLoading)
        {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }

        else
        {
            Column (modifier = Modifier
                .fillMaxWidth()
                .padding(innerPadding),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Spacer(modifier = Modifier.weight(1f))
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(innerPadding),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {

                    tags.forEach { tag ->
                        TagItem(
                            tag = tag,
                            isSelected = tag == selectedTag,
                            onClick = { vm.selectTag(tag) }
                        )
                    }

                }

                Spacer(modifier = Modifier.weight(1f))
                SubmitBtn(modifier = Modifier
                    .padding(2.dp),
                    onClick = { if(selectedTag!=null){
                        selectedTag?.let { onSubmit(it) }
                    }
                    else{
                        Toast.makeText(context,"Please select a tag",Toast.LENGTH_SHORT).show()
                    }
                    }
                )

                Spacer(modifier = Modifier.height(20.dp))
            }
        }

    }
}

@Composable
fun TagItem(
    tag: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(20.dp),
        color = if (isSelected) selectedItem else unselectedItem,
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(4.dp)
    ) {
        Text(
            text = tag,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
            color = if (isSelected) Color.White else Color.Black
        )
    }
}

@Composable
fun SubmitBtn(
    modifier: Modifier,
    onClick: () -> Unit
)
{
Surface(shape = RoundedCornerShape(12.dp),
    color = submitBtn,
    modifier = modifier
        .clickable(onClick = onClick)) {
    Text("Submit", modifier = Modifier.padding(10.dp), fontWeight = FontWeight.Bold)
}
}

