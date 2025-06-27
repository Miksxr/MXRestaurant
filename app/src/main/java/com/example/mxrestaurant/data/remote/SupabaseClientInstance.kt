package com.example.mxrestaurant.data.remote

import io.github.jan.supabase.auth.Auth
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

object SupabaseClientInstance {

    val client = createSupabaseClient(
        supabaseUrl = "https://sjeszhdvjhlqfuclblnu.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InNqZXN6aGR2amhscWZ1Y2xibG51Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDk3OTk1MTIsImV4cCI6MjA2NTM3NTUxMn0.vBZgFbTFObe6_dvZaSpfTwQVv7kCYq1uSOa8cWPABoA"
    ) {
        install(Auth)
        install(Postgrest)
    }
}