package io.kiquar.plugin.ocaml.runner

import android.content.Context
import android.app.Activity
import com.rk.file.FileObject
import com.rk.icons.Icon
import com.rk.runner.Runner
import com.rk.file.BuiltinFileType
import com.rk.exec.launchTerminal
import com.rk.exec.TerminalCommand

class OcamlRunner(
    val icon: Icon? = null,
    val supportedExtensions: List<String> = listOf("ml"),
) : Runner() {

    override val id = "ocaml.run.ml"
    override val label = "Run OCaml"

    override fun getIcon(context: Context) = icon

    override fun matcher(fileObject: FileObject): Boolean {
        return supportedExtensions.contains(fileObject.getExtension())
    }

    override suspend fun run(activity: Activity, fileObject: FileObject) {
        val workingDir = fileObject.getParentFile()?.getAbsolutePath()
        val fileName = fileObject.getName()
        val baseName = fileName.substringBeforeLast(".")
        
        launchTerminal(
            activity = activity,
            terminalCommand = TerminalCommand(
                exe = "/bin/ocamlfind",
                args = arrayOf("opt", "-linkpkg", fileName, "-o", baseName, "&&", "./" + baseName),
                id = id,
                workingDir = workingDir,
            ),
        )
    }

    override suspend fun isRunning() = false

    override suspend fun stop() {}
}
