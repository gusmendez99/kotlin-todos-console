package com.gustavomendez.lab3

import com.gustavomendez.lab3.models.ToDo
import com.gustavomendez.lab3.models.ToDoList


fun getMainMenu(isEmptyList:Boolean): String {

    if(isEmptyList) {
        return """
        MENU:
        1. Crear lista de tareas
        2. Salir
    """.trimIndent()
    }
    return """
        MENU:
        1. Crear lista de tareas
        2. Ver todas las listas de tareas
        3. Seleccionar una lista de tareas
        4. Salir
    """.trimIndent()
}


fun getToDoListMenu(listName:String): String {
    return """
        MENU LISTA:
        1. Deseleccionar lista actual: ${listName}
        2. Agregar una tarea
        3. Completar una tarea
        4. Ver todas las tareas en esta lista
        5. Eliminar tarea
        6. Salir
    """.trimIndent()
}


fun main(args: Array<String>) {
    //Properties
    val toDoListBoard:ArrayList<ToDoList> = ArrayList()
    var wantsToContinue = true

    do {
        println(getMainMenu(toDoListBoard.isEmpty()))
        print("Ingrese una opcion del menu (1 - ${if(toDoListBoard.isEmpty()) 2 else 4}): ")
        val option = readLine()!!.toInt()

        when (option) {
            1 -> {
                print("Ingrese el nombre de su nueva lista de tareas: ")
                val name = readLine()!!
                toDoListBoard.add(ToDoList(name))
                println("LISTA AGREGADA EXITOSAMENTE")
            }
            2 -> {
                if(toDoListBoard.isEmpty()) {
                    wantsToContinue = false
                } else {
                    var toDosLists:String = ""
                    for(i in toDoListBoard.indices){
                        toDosLists += "${i + 1}. ${toDoListBoard.get(i).name}\n"
                    }
                    println(toDosLists)
                }
            }
            3 -> {
                println("Listas:")
                var toDosLists:String = ""
                for(i in toDoListBoard.indices){
                    toDosLists += "${i + 1}. ${toDoListBoard.get(i).name}\n"
                }
                println(toDosLists)
                print("Ingrese el numero de lista a seleccionar: ")
                val pos = readLine()!!.toInt()

                if(toDoListBoard.get(pos - 1) != null) {
                    var currentToDoListIndex:Int = pos

                    do {

                        //Menu de la lista actual
                        println(getToDoListMenu(toDoListBoard.get(pos - 1).name))
                        print("Ingrese una opcion del menu (1 - 5): ")
                        val option = readLine()!!.toInt()

                        when (option) {
                            1 -> {
                                currentToDoListIndex = -1
                                println("LISTA DESELECCIONADA\n")
                            }
                            2 -> {
                                //Agregar tarea
                                print("Ingrese el titulo de la nueva tareas: ")
                                val toDoName = readLine()!!
                                toDoListBoard.get(pos - 1).addToDo(ToDo(toDoName, false))
                                println("TAREA AGREGADA EXITOSAMENTE")
                            }
                            3 -> {
                                if(toDoListBoard.get(pos - 1).getIncompleteToDos().isEmpty()) {
                                    println("NO HAY TAREAS PENDIENTES")
                                } else {
                                    println("TAREAS:")
                                    println(toDoListBoard.get(pos - 1).getIncompleteToDosString())
                                    print("Ingrese el numero de tarea a completar: ")
                                    val toDoIndex = readLine()!!.toInt()
                                    //Cambiar estado de tarea

                                    val toDoIncomplete:ToDo = toDoListBoard.get(pos - 1).getIncompleteToDos().get(toDoIndex - 1)

                                    val originalIndex:Int = toDoListBoard.get(pos - 1).getAllToDos().indexOf(toDoIncomplete)
                                    toDoListBoard.get(pos - 1).getToDo(originalIndex)!!.changeState()
                                    println("TAREA COMPLETADA EXITOSAMENTE!")
                                }


                            }
                            4 -> {
                                println("TAREAS:")
                                println(toDoListBoard.get(pos - 1).getAllToDosString())
                            }
                            5 -> {
                                println("TAREAS:")
                                println(toDoListBoard.get(pos - 1).getAllToDosString())
                                print("Ingrese el numero de tarea a eliminar: ")
                                val toDoIndex = readLine()!!.toInt()
                                //Eliminar tarea
                                if(toDoListBoard.get(pos - 1).getToDo(toDoIndex - 1) != null) {
                                    toDoListBoard.get(pos - 1).deleteToDo(toDoIndex - 1)
                                    println("TAREA ELIMINADA EXITOSAMENTE!")
                                } else {
                                    println("Error al eliminar tarea, intente de nuevo...")
                                }
                            }
                            6 -> {
                                currentToDoListIndex = -1
                                wantsToContinue = false
                            }
                        }
                    } while (currentToDoListIndex >= 0)




                } else {
                    println("Numero de lista no existe, intente de nuevo...")
                }
            }
            4 -> {
                wantsToContinue = false
            }
        }





    } while (wantsToContinue)


}