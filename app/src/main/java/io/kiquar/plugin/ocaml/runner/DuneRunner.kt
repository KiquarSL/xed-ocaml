package io.kiquar.plugin.ocaml.runner

import android.content.Context
import android.app.Activity
import com.rk.file.FileObject
import com.rk.icons.Icon
import com.rk.runner.Runner
import com.rk.file.BuiltinFileType
import com.rk.exec.launchTerminal
import com.rk.exec.TerminalCommand

class DuneRunner(
    val icon: Icon? = BuiltinFileType.PROPERTIES.icon,
    val supportedExtensions: List<String> = listOf("opam"),
) : Runner() {

    override val id = "ocaml.dune.run"
    override val label = "Dune Run"

    override fun getIcon(context: Context) = icon

    override fun matcher(fileObject: FileObject): Boolean {
        return supportedExtensions.contains(fileObject.getExtension())
    }

    override suspend fun run(activity: Activity, fileObject: FileObject) {
        val workingDir = fileObject.getParentFile()?.getAbsolutePath()
        val ext = fileObject.getExtension()
        val projectName = workingDir?.substringAfterLast("/")
        
        val command = when (ext) {
            "opam" -> TerminalCommand(
                exe = "opam",
                args = arrayOf("exec", "--", "dune", "exec", projectName ?: "."),
                id = id,
                workingDir = workingDir,
            )
            else -> TerminalCommand(
                exe = "dune",
                args = arrayOf("exec", projectName ?: "."),
                id = id,
                workingDir = workingDir,
            )
        }
        
        launchTerminal(activity = activity, terminalCommand = command)
    }

    override suspend fun isRunning() = false

    override suspend fun stop() {}
}
