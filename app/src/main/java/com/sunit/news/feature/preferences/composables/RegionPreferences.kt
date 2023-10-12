package com.sunit.news.feature.preferences.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sunit.news.R
import com.sunit.news.feature.preferences.PreferencesViewModel
import com.sunit.news.feature.preferences.models.PreferencesUiState
import com.sunit.news.util.Constants.CountryCodeToName
import com.sunit.news.util.Constants.NameToCountryCode

const val REGION_PREFERENCES_ROUTE = "region_preferences"

@Composable
fun RegionPreferences(
    preferencesViewModel: PreferencesViewModel = hiltViewModel()
) {
    val preferencesUiState by preferencesViewModel.preferencesUiState.collectAsStateWithLifecycle()

    when (preferencesUiState) {
        is PreferencesUiState.Loading -> {}
        is PreferencesUiState.Success -> {
            val countryCode =
                (preferencesUiState as PreferencesUiState.Success).editablePreferences.countryCode
            val countryName = CountryCodeToName[countryCode]
            if (countryName != null) {
                RegionsDropdown(
                    selectedOptionText = countryName,
                    regions = NameToCountryCode.keys.toList(),
                    onClick = {
                        preferencesViewModel.updateCountryCode(countryCode = it)
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegionsDropdown(
    selectedOptionText: String,
    regions: List<String>,
    onClick: (countryCode: String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded },
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            modifier = Modifier
                .menuAnchor()
                .fillMaxWidth(),
            readOnly = true,
            value = selectedOptionText,
            onValueChange = {},
            label = { Text(stringResource(id = R.string.region_setting)) },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            regions.forEach { countryName ->
                DropdownMenuItem(
                    text = { Text(countryName) },
                    onClick = {
                        expanded = false
                        NameToCountryCode[countryName]?.let { onClick(it) }
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}
