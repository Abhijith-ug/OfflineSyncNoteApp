package com.example.offlinesyncnoteapp.presentation.navigation.note_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.offlinesyncnoteapp.presentation.notes.NotesContract

@Composable
fun NotesScreen(
    state: NotesContract.UiState,
    onCreateClick: () -> Unit,
    padding: PaddingValues
) = Box(modifier = Modifier.fillMaxSize().padding(padding)) {

    LazyColumn {

        items(state.notes) { note ->

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {

                Text(
                    text = note.title,
                    style = MaterialTheme.typography.titleMedium
                )

                Text(
                    text = note.content,
                    style = MaterialTheme.typography.bodyMedium
                )

            }

        }

    }

    FloatingActionButton(
        onClick = onCreateClick,
        modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(16.dp)
    ) {
        Text("+")
    }

    state.error?.let {

        Text(
            text = it,
            color = Color.Red,
            modifier = Modifier.align(Alignment.TopCenter)
        )

    }

}