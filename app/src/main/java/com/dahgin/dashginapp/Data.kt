package com.dahgin.dashginapp

class Data {

    companion object{
        var tasks : MutableList<Task> = arrayListOf(
            Task("Task1", "Write code.", false, 2022, 6, 12, 2,8),
            Task("Task2", "Write code.", false, 2022, 6, 12, 2,8),
            Task("Task3", "Write code.", false, 2022, 6, 12, 2,8),
        )
    }
}