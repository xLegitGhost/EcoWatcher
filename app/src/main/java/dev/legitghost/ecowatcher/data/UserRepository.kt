package dev.legitghost.ecowatcher.data

import androidx.lifecycle.LiveData
import dev.legitghost.ecowatcher.data.Entitys.User

class UserRepository (private val userDao : UserDao) {

        val readAllData : LiveData<List<User>> = userDao.getAllUsers()

        suspend fun addUser(user: User) {
            userDao.addUser(user)
        }
}