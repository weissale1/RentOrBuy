package rentorbuy

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


import rentorbuy.ui.RentOrBuyApp
import rentorbuy.ui.theme.RentOrBuyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val backgroundScope = CoroutineScope(Dispatchers.IO)
        backgroundScope.launch {
            // Initialize the Google Mobile Ads SDK on a background thread.
            MobileAds.initialize(this@MainActivity) {}
        }

        setContent {
            RentOrBuyTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        RentOrBuyApp()
                        //BannerAd(context = LocalContext.current)
                    }
                }
            }
        }
    }
}

@Composable
fun BannerAd(context: Context) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // on below line we are adding a spacer.
        Spacer(modifier = Modifier.height(20.dp))
        AndroidView(modifier = Modifier.fillMaxWidth(), factory = { context ->
            AdView(context).apply {
                // on below line specifying ad size
                setAdSize(AdSize.BANNER)
                // on below line specifying ad unit id
                // currently added a test ad unit id.
                adUnitId = "ca-app-pub-3940256099942544/9214589741"
                // calling load ad to load our ad.
                loadAd(AdRequest.Builder().build())
            }
        })
    }
}
