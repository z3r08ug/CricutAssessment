package com.z3r08ug.cricutassessment

/*
Design Explanation:
FileSystemEntry (Sealed Class): Uses a sealed class to define file system entries.
This approach enforces type safety and makes it clear which subclasses are allowed.
Error Handling: Incorporates error handling to manage common file system errors,
such as attempting to access non-existent files or directories.
Encapsulation: Methods that manipulate internal states such as createFile,
readFile, and writeFile are well encapsulated, ensuring that operations like
finding the parent directory or entry are handled internally and appropriately.
Usage of Kotlin Features: Demonstrates effective use of Kotlin's language features
like data classes for files, sealed classes for type-safe hierarchical data, and
exception handling for error management.
 */

sealed class FileSystemEntry(val name: String) {
    class File(name: String, var content: String) : FileSystemEntry(name)
    class Directory(name: String, val children: MutableMap<String, FileSystemEntry> = mutableMapOf()) : FileSystemEntry(name)
}

class InMemoryFileSystem {
    private val root = FileSystemEntry.Directory("/")

    fun createFile(path: String, content: String) {
        val parent = getParentDirectory(path) as? FileSystemEntry.Directory
            ?: throw IllegalArgumentException("Invalid path or directory does not exist")
        val fileName = path.substringAfterLast("/")
        if (fileName in parent.children) {
            throw IllegalArgumentException("File already exists")
        }
        parent.children[fileName] = FileSystemEntry.File(fileName, content)
    }

    fun readFile(path: String): String {
        val file = findEntry(path) as? FileSystemEntry.File
            ?: throw IllegalArgumentException("File does not exist or is a directory")
        return file.content
    }

    fun writeFile(path: String, content: String) {
        val file = findEntry(path) as? FileSystemEntry.File
            ?: throw IllegalArgumentException("File does not exist or is a directory")
        file.content = content
    }

    fun listDirectory(path: String): List<String> {
        val directory = findEntry(path) as? FileSystemEntry.Directory
            ?: throw IllegalArgumentException("Directory does not exist or is a file")
        return directory.children.keys.toList()
    }

    private fun getParentDirectory(path: String): FileSystemEntry? {
        val parentPath = path.substringBeforeLast("/", "")
        return if (parentPath.isEmpty()) root else findEntry(parentPath)
    }

    private fun findEntry(path: String): FileSystemEntry? {
        val nodes = path.trimStart('/').split('/')
        return nodes.fold(root as FileSystemEntry?) { current, name ->
            if (current is FileSystemEntry.Directory) current.children[name] else null
        }
    }
}

//Example of the file system being used
fun main() {
    val fs = InMemoryFileSystem()
    try {
        fs.createFile("/usr/bin/script.sh", "echo Tom Brady, GOAT!")
        println("File Content: '${fs.readFile("/usr/bin/script.sh")}'")
        fs.writeFile("/usr/bin/script.sh", "# Updated script\n echo Hello, GOAT!")
        println("Updated File Content: '${fs.readFile("/usr/bin/script.sh")}'")
        println("Directory Listing: ${fs.listDirectory("/usr/bin")}")
    } catch (e: Exception) {
        println("Error: ${e.message}")
    }
}