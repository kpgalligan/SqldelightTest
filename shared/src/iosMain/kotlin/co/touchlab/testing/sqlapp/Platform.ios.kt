package co.touchlab.testing.sqlapp

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import co.touchlab.testing.Database
import platform.UIKit.UIDevice
import kotlin.random.Random

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}

actual fun getPlatform(): Platform = IOSPlatform()

actual class DriverFactory {
    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(Database.Schema, "test.db")
    }
}

fun doDbStuff(){
    val database = createDatabase(DriverFactory())
    database.tableQueries.insertBreed("Hello Dog ${Random.nextInt(3000)}")
    println(database.tableQueries.selectAll().executeAsList().joinToString { it.name })
}