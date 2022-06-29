package uz.gita.paymedemo.utils.boradcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log


class InternetReciver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val connMgr = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val wifi = connMgr
            .getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val mobile = connMgr
            .getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
        if (wifi!!.isAvailable || mobile!!.isAvailable) {
            Log.d("Network Available ", "Flag No 1")
        }
    }
}