package com.judjingm.uikit.ui.navigation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.judjingm.uikit.R
import com.judjingm.uikit.theme.CoreTrainTheme

// UI elements
@Composable
fun NavBottomBarPlusButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(70.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(MaterialTheme.colorScheme.secondaryContainer)
            .clickable { onClick.invoke() },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_plus_large),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSecondaryContainer
        )
    }
}

@Composable
fun NavBottomDefaultElement(
    itemIndex: Int,
    onClick: () -> Unit,
    selectedItemIndex: Int,
    text: String,
    painter: Painter,
    onFocusTint: Color,
    outFocusTint: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier, contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(selectedItemIndex == itemIndex) {
            Box(
                modifier = modifier
                    .size(width = 70.dp, height = 50.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(MaterialTheme.colorScheme.secondaryContainer)

            )
        }
        Column(
            modifier = modifier
                .width(75.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }) { onClick.invoke() },
            horizontalAlignment = Alignment.CenterHorizontally,
        )
        {
            Icon(
                modifier = modifier,
                painter = painter,
                contentDescription = null,
                tint = if (itemIndex == selectedItemIndex) onFocusTint else outFocusTint
            )
            Text(
                text = text,
                style = MaterialTheme.typography.bodySmall,
                color = if (itemIndex == selectedItemIndex) onFocusTint else outFocusTint,
                modifier = modifier.padding(bottom = 2.dp)
            )

        }
    }
}

@Composable
fun CustomBottomNavBar(
    itemOnFocusTint: Color,
    itemOutOfFocusTint: Color,
    firstItemText: String,
    secondItemText: String,
    thirdItemText: String,
    fourthItemText: String,
    firstItemPainter: Painter,
    secondItemPainter: Painter,
    thirdItemPainter: Painter,
    fourthItemPainter: Painter,
    firstItemClick: () -> Unit,
    secondItemClick: () -> Unit,
    onPlusButtonClick: () -> Unit,
    thirdItemClick: () -> Unit,
    fourthItemClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val selectedIconIndex = rememberSaveable {
        mutableIntStateOf(0)
    }

    Box(modifier = modifier.wrapContentHeight()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(65.dp)
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(MaterialTheme.colorScheme.surfaceContainer)
                .align(Alignment.BottomCenter)
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(9.dp),
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            NavBottomDefaultElement(
                itemIndex = 0,
                text = firstItemText,
                selectedItemIndex = selectedIconIndex.intValue,
                onClick = {
                    selectedIconIndex.intValue = 0
                    firstItemClick.invoke()
                },
                painter = firstItemPainter,
                onFocusTint = itemOnFocusTint,
                outFocusTint = itemOutOfFocusTint,
            )

            NavBottomDefaultElement(
                itemIndex = 1,
                text = secondItemText,
                selectedItemIndex = selectedIconIndex.intValue,
                onClick = {
                    selectedIconIndex.intValue = 1
                    secondItemClick.invoke()
                },
                painter = secondItemPainter,
                onFocusTint = itemOnFocusTint,
                outFocusTint = itemOutOfFocusTint,
            )

            NavBottomBarPlusButton(
                modifier = Modifier.align(alignment = Alignment.Bottom),
                onClick = { onPlusButtonClick.invoke() }
            )

            NavBottomDefaultElement(
                itemIndex = 2,
                text = thirdItemText,
                selectedItemIndex = selectedIconIndex.intValue,
                onClick = {
                    selectedIconIndex.intValue = 2
                    thirdItemClick.invoke()
                },
                painter = thirdItemPainter,
                onFocusTint = itemOnFocusTint,
                outFocusTint = itemOutOfFocusTint,
            )

            NavBottomDefaultElement(
                itemIndex = 3,
                text = fourthItemText,
                selectedItemIndex = selectedIconIndex.intValue,
                onClick = {
                    selectedIconIndex.intValue = 3
                    fourthItemClick.invoke()
                },
                painter = fourthItemPainter,
                onFocusTint = itemOnFocusTint,
                outFocusTint = itemOutOfFocusTint,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavBottomBarPlusButtonPreview() {
    CoreTrainTheme {
        NavBottomBarPlusButton { }
    }
}

@Preview
@Composable
fun NavBottomDefaultElementPreview() {
    CoreTrainTheme {
        NavBottomDefaultElement(
            itemIndex = 0,
            onClick = { },
            selectedItemIndex = 1,
            text = "Home",
            painter = painterResource(id = R.drawable.baseline_home_filled_24),
            onFocusTint = MaterialTheme.colorScheme.primary,
            outFocusTint = MaterialTheme.colorScheme.tertiary,
        )
    }
}

@Preview(showBackground = true, apiLevel = 26)
@Composable
fun CustomBottomNavBarPreview() {
    CoreTrainTheme {
        CustomBottomNavBar(
            itemOnFocusTint = MaterialTheme.colorScheme.onSecondaryContainer,
            itemOutOfFocusTint = MaterialTheme.colorScheme.onSurfaceVariant,
            firstItemText = "First",
            firstItemPainter = painterResource(id = R.drawable.baseline_home_filled_24),
            secondItemText = "Second",
            secondItemPainter = painterResource(id = R.drawable.baseline_home_filled_24),
            thirdItemText = "Third",
            thirdItemPainter = painterResource(id = R.drawable.baseline_home_filled_24),
            fourthItemText = "Fourth",
            fourthItemPainter = painterResource(id = R.drawable.baseline_home_filled_24),
            firstItemClick = {},
            secondItemClick = {},
            onPlusButtonClick = {},
            thirdItemClick = {},
            fourthItemClick = {}
        )
    }
}
