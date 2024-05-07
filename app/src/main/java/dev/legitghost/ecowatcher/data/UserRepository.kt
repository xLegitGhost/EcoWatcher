package dev.legitghost.ecowatcher.data

import androidx.lifecycle.LiveData

class UserRepository (private val userDao : UserDao) {

        val readAllData : LiveData<List<User>> = userDao.getAllUsers()

        suspend fun addUser(user: User) {
            userDao.addUser(user)
        }
}