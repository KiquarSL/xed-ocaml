package io.kiquar.plugin.ocaml

import android.content.res.Resources
import com.rk.file.FileType
import com.rk.file.BuiltinFileType

class OpamLanguage(resources: Resources) : FileType {
    override val extensions = listOf("opam")
    override val textmateScope = "source.opam"
    override val name = "opam"
    override val title = "opam"
    override val icon = BuiltinFileType.PROPERTIES.icon
}
